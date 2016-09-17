package com.eazytec.service;

import com.eazytec.model.Role;

import javax.jws.WebService;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.util.List;

/**
 * Web Service interface so hierarchy of Generic Manager isn't carried through.
 * @author madan
 */
public interface RoleService {
    /**
     * Retrieves a role by roleId.  An exception is thrown if role not found
     *
     * @param roleId the identifier for the role
     * @return Role
     */
    @GET
    @Path("{id}")
    Role getRole(@PathParam("id") String roleId);

    /**
     * Finds a role by their rolename.
     *
     * @param rolename the role's rolename used to login
     * @return Role a populated role object
     */
    Role getRoleByName(@PathParam("rolename") String rolename);

    /**
     * Retrieves a list of all roles.
     *
     * @return List
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<Role> getRoles();

    /**
     * Saves a role's information
     *
     * @param role the role's information
     * @return updated role
     * @throws RoleExistsException thrown when role already exists
     */
    @POST
    Role saveRole(Role role) throws RoleExistsException;

    /**
     * Removes a role from the database by their roleId
     *
     * @param roleId the role's id
     */
    @DELETE
    void removeRole(String roleId);
}
