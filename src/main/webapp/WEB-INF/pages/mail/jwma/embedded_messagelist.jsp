<%-- Message List --%>


<table width="100%" cellpadding="1" cellspacing="1" border="0" height="100%" vspace="0" hspace="0">
    <tr bgcolor="#dadada">
    	<td height="25" nowrap colspan="7" width="100%">
			<font color="#000000" face="Arial, Helvetica, sans-serif"><b><fmt:message key="folder.messages"/></b></font>
			<font size="-1" face="Arial, Helvetica, sans-serif" color="#000000">
            	(<fmt:message key="folder.sortedby"/><%= htmlhelper.getSortCriteriaSelect(prefs,sorthandler) %>)
            </font>
		</td>
	</tr>
    <tr bgcolor="#dadada">
        <td nowrap height="20" width="1%">
          <font color="#000000" face="Arial,Helvetica"><b><fmt:message key="message.number"/></b></font>
        </td>

        <td nowrap height="20" width="1%">
          <font color="#000000" face="Arial,Helvetica"><b><fmt:message key="folder.flags"/></b></font>
        </td>

        <td nowrap height="20" width="1%">
          <font color="#000000" face="Arial,Helvetica"><b><fmt:message key="folder.attachment"/></b></font>
        </td>

        <td nowrap height="20" width="40%">
          <font color="#000000" face="Arial,Helvetica"><b><fmt:message key="folder.who"/></b></font>
        </td>

        <td nowrap height="20" width=40%">
          <font color="#000000" face="Arial,Helvetica"><b><fmt:message key="message.subject"/></b></font>
        </td>

        <td nowrap height="20" width="16%">
          <font color="#000000" face="Arial,Helvetica"><b><fmt:message key="message.date"/></b></font>
        </td>
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
					//clean up whitespace
					msgsubject.trim();
				}
		%> 
        <tr> 
            
        <td bgcolor="#eeeeee" height="20" width="1%"> 
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
			<font face="Arial,Helvetica" size="-1">
            <%-- <%= ((msg.isMultipart())? bundle.getString("folder.message.attachment"):"&nbsp;") %> --%>
			</font>
		</td>    
        <td bgcolor="#eeeeee" width="40%">
			<font face="Arial,Helvetica" size="-1"> 
            <%= ((msg.isReceived())? msg.getFrom():("<i>"+msg.getTo()+"</i>")) %> 
            </font>
		</td>
        <td bgcolor="#eeeeee" width="40%">
			<font face="Arial,Helvetica" size="-1"> 
            <% if (msg.isDraft()) { %>
            <a href="#bpm/mail/jwma/getMessage" onclick="_execute('target','acton=message&amp;todo=composedraft&amp;number=<%= msg.getMessageNumber() %>');">
                <%= msgsubject %>
            </a>
            <% } else { %>
            <a href="#bpm/mail/jwma/getMessage" onclick="_execute('target','acton=message&amp;todo=display&amp;number=<%= msg.getMessageNumber() %>');">
                <%= msgsubject %>
            </a>
            <% } %>
			</font>
		</td>
            
        <td bgcolor="#eeeeee" nowrap width="16%">
			<font face="Arial,Helvetica" size="-1"> 
            <%= date %>
			</font>
		</td>
      <td bgcolor="#eeeeee" height="20" width="1%">
            <%= htmlhelper.getSizeString(msg.getSize()) %>
        </td>
		</tr>
        <%	}//for end %>
		<%-- end loop --%> 
		<%-- A cell that swallows the rest size --%>
		<tr>
			<td bgcolor="#eeeeee" colspan="7">&nbsp;</td>
		</tr>
        
    <tr bgcolor="#dadada"> 
        <td colspan="6" height="25"> 
            <input type="button" name="delete" value="<fmt:message key="form.delete"/>" onclick="submitDelete(this.form,'message');">
                &nbsp;&nbsp; 
                <input type="button" name="move" value="<fmt:message key="form.moveto"/>" onclick="submitMove(this.form,'message');">
                <%= htmlhelper.getDestinationsSelect(store.listMessageMoveTargets()) %>	
            </td>
        </tr>
    </table>


<%-- End Message List --%>