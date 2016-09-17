package com.eazytec.bpm.admin.menu.service;

import java.util.List;
import java.util.Set;

import javax.jws.WebService;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.eazytec.model.Menu;
import com.eazytec.model.Module;
import com.eazytec.service.MenuExistsException;

/**
 * This interface represents the basic "menu" object operations
 *
 * @author mathi
 */

@WebService
@Path("/menus")
public interface MenuService {
	
	/**
     * Retrieves a Menu by menuId.  An exception is thrown if Menu not found
     *
     * @param menuId the identifier for the menu
     * @return Menu
     */
    @GET
    @Path("{id}")
    Menu getMenu(@PathParam("id") String menuId);

    /**
     * Finds a menu by their menuName.
     *
     * @param menuName the menu's menuName
     * @return Menu a populated menu object
     */
    Menu getMenuByName(@PathParam("menuName") String menuName);

    /**
     * Retrieves a list of all Menus.
     *
     * @return List
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<Menu> getMenus();

    /**
     * Saves a Menu's information
     *
     * @param menu the Menu's information
     * @return updated menu
     * @throws MenuExistsException thrown when menu already exists
     */
    @POST
    Menu saveMenu(Menu menu) throws MenuExistsException;

    /**
     * Removes a menu from the database by their menuId
     *
     * @param menuId the menu's id
     */
    @DELETE
    void removeMenu(String menuId);
    
    /**
     * Finds a menu by their id.
     *
     * @param id the menu's id used to login
     * @return Menu a populated menu object
     */
    Menu getMenuById(String id);
    
    /**
     * Finds a menus.
     *
     * @return Menu a populated menu List
     */
    List<Menu> getTopMenus();
    
    /**
     * Finds a menus.
     *
     * @return Menu a populated menu List
     */
    List<Menu> getParentSideMenus();
    
    /**
     * Finds a menus for top menu.
     *
     * @return Menu a populated menu List
     */
    List<Menu> getSideMenus(Menu topMenu);
    
    /**
     * get a menus for id list.
     *
     * @return Menu a populated menu List
     */
    List<Menu> getMenus(List<String> ids);
    
    /**
     * get all active menus for id list.
     *
     * @return Menu a populated menu List
     */
    List<Menu> getActiveMenus();
    
    /**
     * get menus that not assigned in role.
     *
     * @return Menu a populated menu List
     */
    List<Menu> getMenuNotInRoles(Set<Menu> menus);
    
    List<Menu> getAllChildMenuForParent(String id);
    
    /**
     * delete the menu by id
     * 
     * @param id
     */
    void deleteMenu(String id);
    
    /**
     * get header menus script
     * 
     * @param menus
     * @return
     */
    String getUserMenuScript(String templateName, List<Menu> menus);
}
