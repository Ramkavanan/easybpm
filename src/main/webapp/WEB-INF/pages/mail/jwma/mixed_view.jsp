<%-- Mixed Mode --%>
<form method="post" action="<%= htmlhelper.getControllerUrl() %>">
	<input type="hidden" name="acton" value="">
	<input type="hidden" name="todo" value="">
    <table width="90%" cellpadding="1" cellspacing="0" border="0">
        <tr>
        	<td bgcolor="#000000" colspan="2">
              <img align="left" src="images/folder_small.png" alt="<fmt:message key="folder.folder"/>" width="20" height="20">
              <font face="Arial,Helvetica" size="+1" color="#ffffff"><b><%= htmlhelper.getPathHierarchyNavigator(store,folder) %></b></font>
            </td>
   		</tr>
        <tr>
			<td width="20%" height="100%" bgcolor="#eeeeee" align="left" valign="top">
				<table cellpadding="0" cellspacing="0" border="0" width="100%" align="left">
					<tr bgcolor="#dadada">
                        <td height="25" width="100%">
                          <font face="Arial,Helvetica"><b><fmt:message key="folder.folders"/></b></font>
                          &nbsp;&nbsp;<font size="-1" face="Arial, Helvetica, sans-serif" color="#ffffff">
              (<a href="<%= htmlhelper.getControllerUrl() %>?acton=folder&amp;todo=display_unsubscribed"><fmt:message key="folder.unsubscribed"/></a>)
			        </font>
                        </td>
					</tr>
					<% if(folder.hasSubfolders()) {
						//retrieve the folders
						JwmaFolder[] subfolders=folder.listSubfolders();
					%>
						<%-- loop over folders --%>
						<%	for (int index=0;index<subfolders.length;index++) { %>
						<tr>
							<td width="100%" height="25" align="left" valign="top" bgcolor="#eeeeee">
								<input type="checkbox" name="paths" value="<%= subfolders[index].getPath() %>">
								<font face="Arial,Helvetica" size=-1><b>
								<a href="<%= htmlhelper.getFolderDisplayAction(subfolders[index]) %>"><%= subfolders[index].getName() %></a>
								</b></font>
							</td>    
						</tr>
						<% } %>
						<%-- end loop over folders --%>
					<% } else { %>
						<tr>
							<td width="100%" height="100%" align="left" bgcolor="#eeeeee">&nbsp;</td>
						</tr>
					<% } %>
				</table>
			</td>
			<td bgcolor="#FFFFFF" width="100%" height="100%" align="left" valign="top"> 
                <%-- Insert message list --%>
                <% if(folder.hasMessages()) { %>
                  <%@ include file="embedded_messagelist.jsp" %>
                <% } else { %>
                  <i>
                    <fmt:message key="folder.nomessages">
                      <fmt:message key="folder.folder"/>
                    </fmt:message>
                 </i>
                <% } %>
            </td>
		</tr>
		<tr>
			<td colspan="2" width="100%" height="25" align="left" bgcolor="#000000" nowrap>
				<input type="button" name="delete" value="<fmt:message key="form.delete"/>" onclick="submitDelete(this.form,'folder');"> &nbsp;&nbsp;
				<input type="button" name="move" value="<fmt:message key="form.moveto"/>" onclick="submitMove(this.form,'folder');">
				<%= htmlhelper.getDestinationsSelect(store.listFolderMoveTargets()) %>
				&nbsp;&nbsp;
				<input type="button" name="create" value="<fmt:message key="form.createnew"/> <fmt:message key="folder.folder"/>" onclick="submitCreate(this.form);">
				<input type="hidden" name="type" value="<%= JwmaFolder.TYPE_MIXED %>">
				<input type="text" name="aname" value="" size="20">
			</td>
		</tr>
	</table>
</form>	
<%-- End Mixed Mode --%>