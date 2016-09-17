package com.eazytec.service;

import java.util.List;
import java.util.Set;

import javax.jws.WebService;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.json.JSONObject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.eazytec.dto.UserDTO;
import com.eazytec.exceptions.ApiException;
import com.eazytec.exceptions.EazyBpmException;
import com.eazytec.model.User;
import com.eazytec.service.UserExistsException;

/**
 * Web Service interface so hierarchy of Generic Manager isn't carried through.
 * @author madan
 */
@WebService
@Path("/users")
public interface UserService {
    /**
     * Retrieves a user by userId.  An exception is thrown if user not found
     *
     * @param userId the identifier for the user
     * @return User
     */
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    User getUser(@PathParam("id") String userId);
    
    
    /**
     * Get user by user id
     * 
     * @param id
     * @return
     */
    User getUserById(@PathParam("id")String id);

    /**
     * Finds a user by their username.
     *
     * @param username the user's username used to login
     * @return User a populated user object
     */
    User getUserByUsername(@PathParam("username") String username);

    /**
     * Retrieves a list of all users.
     *
     * @return List
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    List<User> getUsers();

    /**
     * Saves a user's information
     *
     * @param user the user's information
     * @return updated user
     * @throws UserExistsException thrown when user already exists
     */
    User saveUser(User user) throws UserExistsException;
    
    /**
     * save user via API
     * @param user
     * @throws UserExistsException
     */
    @POST
    User saveUserForAPI(User user) throws UserExistsException;

    /**
     * Removes a user from the database by their userId
     *
     * @param userId the user's id
     */
    void removeUser(String userId);
    
    /**
     * 
     * @param usernames
     * @return
     * @throws UsernameNotFoundException
     */
    public List<User> getUserByUsernames(List<String> usernames) throws UsernameNotFoundException;
    
    /**
     * 
     * @param usernames
     * @return
     * @throws UsernameNotFoundException
     */
    public List<User> getDistinctUserByUsernames(List<String> usernames) throws UsernameNotFoundException;
    /**
     * get all users with some of fields by dto object
     * @return
     */
    public List<UserDTO> getAllUsersDTO();
    
    /**
     * Get all user dto by deparment
     * @param departmentId
     * @return
     */ 
    @GET
    @Path("/department/{departmentId}")
    @Produces(MediaType.APPLICATION_JSON)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<UserDTO> getAllUsersByDepartment(@PathParam("departmentId") String departmentId);
    
    /**
     * Get all user dto by deparment
     * @param departmentId
     * @return
     */ 
    @GET
    @Path("/role/{roleId}")
    @Produces(MediaType.APPLICATION_JSON)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<UserDTO> getAllUsersByRole(@PathParam("roleId") String roleId);
    
   
    
    /**
     * Get all user dto by deparment
     * @param departmentId
     * @return
     */ 
    @GET
    @Path("/group/{groupId}")
    @Produces(MediaType.APPLICATION_JSON)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<UserDTO> getAllUsersByGroup(@PathParam("groupId")String groupId);
    
	public List<Object[]> getUserName(String userName);


	String getUserIdByUserRole(String name);


	String getUserRoleByUserId(String userId);    
	
	/**
	 * Get the department admin's
	 * @param user
	 * @return
	 * @throws EazyBpmException
	 */
	List<String> getUserAdminDepartments(User user)throws EazyBpmException;
	
	/**
	 * Get the group admin's
	 * @param user
	 * @return
	 * @throws EazyBpmException
	 */
	List<String> getUserAdminGroups(User user)throws EazyBpmException;
	
	
	List<String> getUserAdminRoles(User user)throws EazyBpmException;
	
	
	/**
	 * Delete the user via API for given userid
	 * @param deleteUserName
	 * @param userName
	 */
	 @DELETE
	 @Path("{deleteUserName}")
	 @PreAuthorize("hasRole('ROLE_ADMIN')")
	 Response deleteUserForAPI(@PathParam("deleteUserName") String deleteUserName, @QueryParam("userName") String userName);
	 
	 /**
	  * Get All user names for given role id
	  * @param roleId
	  * @return
	  */
	 @GET
	 @Path("/role/permission/{roleId}")
	 @Produces(MediaType.APPLICATION_JSON)
	 @PreAuthorize("hasRole('ROLE_ADMIN')")
	 List<String> getAllUsersByRolePermission(@PathParam("roleId") String roleId);
	 
	 /**
	  * Get All user list who is having given module permission
	  * @param moduleName
	  * @return
	  */
	 @GET
	 @Path("/module/permission/{moduleName}")
	 @Produces(MediaType.APPLICATION_JSON)
	 @PreAuthorize("hasRole('ROLE_ADMIN')")
	 Set<String> getUserForModulePermission(@PathParam("moduleName") String moduleName);
	 
	 /**
	  * Get users for given menu permission
	  * @param menuName
	  * @return
	  */
	 @GET
	 @Path("/menu/permission/{menuName}")
	 @Produces(MediaType.APPLICATION_JSON)
	 @PreAuthorize("hasRole('ROLE_ADMIN')")
	 List<String> getUserForMenuPermission(@PathParam("menuName") String menuName);
    
}
