<%-- Message List --%>
<form method="post" action="<%= htmlhelper.getControllerUrl() %>">
<input type="hidden" name="acton" value="message">
<input type="hidden" name="todo" value="">
    <table width="90%" cellpadding="1" cellspacing="1" border="0">
        <tr bgcolor="#000000">
            <td nowrap colspan="7" width="100%">
              <img src="images/mailbox_small.png" width="20" height="20" alt="<fmt:message key="folder.mailbox"/>">
                <b><font color="#FFFFFF" face="Arial, Helvetica, sans-serif"><fmt:message key="folder.messages"/></font></b>
				<font size="-1" face="Arial, Helvetica, sans-serif" color="#ffffff">
                  (<fmt:message key="folder.sortedby"/> <%= htmlhelper.getSortCriteriaSelect(prefs,sorthandler) %>)
                </font>
			</td>
        </tr>
        <tr bgcolor="#dadada">
            <td nowrap width="1%">
              <font color="#000000" face="Arial,Helvetica"><b><fmt:message key="message.number"/></b></font>
            </td>
            <td nowrap width="1%">
              <font color="#000000" face="Arial,Helvetica"><b><fmt:message key="folder.flags"/></b></font>
            </td>
            <td nowrap width="1%">
              <font color="#000000" face="Arial,Helvetica"><b><fmt:message key="folder.attachment"/></b></font>
            </td>
            <td nowrap width="40%">
              <font color="#000000" face="Arial,Helvetica"><b><fmt:message key="folder.who"/></b></font>
            </td>
            <td nowrap width="40%">
              <font color="#000000" face="Arial,Helvetica"><b><fmt:message key="message.subject"/></b></font>
            </td>
            <td nowrap width="16%">
              <font color="#000000" face="Arial,Helvetica"><b><fmt:message key="message.date"/></b>
            </font></td>
            <td nowrap width="1%">
              <font color="#000000" face="Arial,Helvetica"><b><fmt:message key="message.size"/></b></font>
            </td>
        </tr>
        <%-- loop over messages --%>
		<% 	JwmaMessageInfo[] infos=folder.listMessageInfos();	
	   		for (int index=0;index<infos.length;index++) {
				JwmaMessageInfo msg=infos[index];
				//Prepare datestring
				String date=prefs.getDateFormat().format(msg.getDate());
				if (msg.isSent()) {
					date="<i>"+date+"</i>";
				}
				//Prepare subject
				String msgsubject=msg.getSubject();
				if(msgsubject==null || msgsubject.equals("")) {
					msgsubject="<em>"+"form.no"+" "+"message.subject"+"</em>";
				} else {
					//just clean out whitespace
					msgsubject.trim(); 
				}
		%> 
        <tr> 
            <td bgcolor="#eeeeee" width="1%"> 
                <input type="checkbox" name="numbers" value="<%= msg.getMessageNumber() %>">
            </td>
            <td bgcolor="#eeeeee" nowrap width="1%">
              <font face="Arial,Helvetica" size="-1">
                <% if (msg.isNew()) { %><fmt:message key="folder.message.new"/><% } %>
                <% if (msg.isRead()) { %><fmt:message key="folder.message.read"/><% } %>
                <% if (msg.isAnswered()) { %><fmt:message key="folder.message.answered"/><% } %>
                <% if (msg.isDeleted()) { %><fmt:message key="folder.message.deleted"/><% } %>
                <% if (msg.isDraft()) { %><fmt:message key="folder.message.draft"/><% } %>
              </font>
			</td>
            <td bgcolor="#eeeeee" nowrap width="1%">
				<%-- <font face="Arial,Helvetica" size="-1">
                <%= ((msg.isMultipart())? bundle.getString("folder.message.attachment"):"&nbsp;") %>
				</font> --%>
			</td>
            <td bgcolor="#eeeeee" width="40%"> 
				<font face="Arial,Helvetica" size="-1"> 
                <%= ((msg.isReceived())? msg.getFrom():("<i>"+msg.getTo()+"</i>")) %>
                </font>
			</td>
            <td bgcolor="#dddddd" width="40%">
				<font face="Arial,Helvetica" size="-1">
            <% if (msg.isDraft()) { %>
            <a href="<%= htmlhelper.getControllerUrl() %>?acton=message&amp;todo=composedraft&amp;number=<%= msg.getMessageNumber() %>">
                <%= msgsubject %>
            </a>
            <% } else { %>
            <a href="<%= htmlhelper.getControllerUrl() %>?acton=message&amp;todo=display&amp;number=<%= msg.getMessageNumber() %>">
                <%= msgsubject %>
            </a>
            <% } %>
				</font>
			</td>
            <td bgcolor="#eeeeee" nowrap width="16%">
				<font face="Arial,Helvetica" size="-1"><%= date %></font>
			</td>
      <td bgcolor="#eeeeee" height="20" width="1%">
            <%= htmlhelper.getSizeString(msg.getSize()) %>
        </td>
        </tr>
        <%	}//for end %> <%-- end loop --%>
        <tr> 
            <td colspan="7" bgcolor="#000000" width="100%">
                <input type="button" name="delete" value="<fmt:message key="form.delete"/>" onclick="submitDelete(this.form,'message');">
                &nbsp;&nbsp; 
                <input type="button" name="move" value="<fmt:message key="form.moveto"/>" onclick="submitMove(this.form,'message');">
                <%= htmlhelper.getDestinationsSelect(store.listMessageMoveTargets()) %>	
            </td>
        </tr>
    </table>
</form>

<%-- End Message List --%>