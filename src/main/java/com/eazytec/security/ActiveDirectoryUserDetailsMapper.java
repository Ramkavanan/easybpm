package com.eazytec.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.userdetails.LdapUserDetailsMapper;

import com.eazytec.bpm.common.util.I18nUtil;
import com.eazytec.model.Role;
import com.eazytec.model.User;
import com.eazytec.service.UserService;

/**
* This class allows Spring to do it's thing with respect to mapping user details from
* LDAP to the Spring's security framework. However, this class allows us to specify whether
* to use the user's user name from LDAP
*
* @author Vinoth
*
*/
public class ActiveDirectoryUserDetailsMapper extends LdapUserDetailsMapper {
	
	@Autowired
	private UserService userService;

    protected boolean useEmailAddressAsUsername = true;

    protected boolean additiveRoleNameSubstitutions = false;

    protected Map<String, String[]> roleNameSubstitutions;

    @Override
    public UserDetails mapUserFromContext(DirContextOperations ctx, String username, Collection<? extends GrantedAuthority> authorities) {
        Collection<GrantedAuthority> newAuthorities = new HashSet<GrantedAuthority>();

        if (roleNameSubstitutions != null && ! roleNameSubstitutions.isEmpty()) {
            for (GrantedAuthority authority : authorities) {
                if (roleNameSubstitutions.containsKey(authority.getAuthority())) {
                    String[] roles = roleNameSubstitutions.get(authority.getAuthority());
                    for (String role : roles) {
                        newAuthorities.add(new SimpleGrantedAuthority(role.trim()));
                    }
                    if (additiveRoleNameSubstitutions) {
                        newAuthorities.add(authority);
                    }
                } else {
                    newAuthorities.add(authority);
                }
            }
        } else {
            newAuthorities.addAll(authorities);
        }

        String email = (String)ctx.getObjectAttribute("mail");
        UserDetails userDetails = null;
        if (useEmailAddressAsUsername) {
            if ((String)ctx.getObjectAttribute("givenName") != null) {
                userDetails = super.mapUserFromContext(ctx, (String)ctx.getObjectAttribute("givenName"), newAuthorities);
            }
        }

        if (userDetails == null) {
            userDetails = super.mapUserFromContext(ctx, username, newAuthorities);
        }
        
        String password = userDetails.getPassword();
        if (password == null) {
            password = userDetails.getUsername();
        }
        
       User dbuser= userService.getUserById(userDetails.getUsername());
       
       if(null == dbuser){
    	   throw new BadCredentialsException(I18nUtil.getMessageProperty("aduser.not.avaliable"));
       }
        
      /*  User user = new User(userDetails.getUsername(),userDetails.getUsername(), password);
        user.setFirstName((String)ctx.getObjectAttribute("givenName"));
        user.setLastName((String)ctx.getObjectAttribute("sn"));
        user.setEmail(email);
        user.getRoles().add(new Role("ROLE_DEFAULT"));
        user.getRoles().add(new Role("ROLE_USER"));*/
        return dbuser;
    }

	/**
	* The LDAP server may contain a user name other than an email address. If the email address should be used to map to a user, then
	* set this to true. The principal will be set to the user's email address returned from the LDAP server.
	* @param value
	*/
    public void setUseEmailAddressAsUsername(boolean value) {
        this.useEmailAddressAsUsername = value;
    }

    public void setRoleNameSubstitutions(Map<String, String[]> roleNameSubstitutions) {
        this.roleNameSubstitutions = roleNameSubstitutions;
    }

    /**
	* This should be used in conjunction with the roleNameSubstitutions property.
	* If this is set to true, this will add the mapped roles to the list of original granted authorities. If set to false, this will replace the original granted
	* authorities with the mapped ones. Defaults to false.
	*
	* @param additiveRoleNameSubstitutions
	*/
    public void setAdditiveRoleNameSubstitutions(boolean additiveRoleNameSubstitutions) {
        this.additiveRoleNameSubstitutions = additiveRoleNameSubstitutions;
    }
}