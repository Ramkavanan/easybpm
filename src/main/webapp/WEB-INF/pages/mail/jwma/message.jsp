<%@ page session="true" import="dtw.webmail.model.*" %>

<%-- Import taglibs and i18n bundle --%>
<%@ include file="/common/taglibs.jsp"%>
<%
	JwmaHtmlHelper htmlhelper=(JwmaHtmlHelper) session.getValue("jwma.htmlhelper");
    JwmaStoreInfo store=(JwmaStoreInfo) session.getValue("jwma.storeinfo");
	JwmaInboxInfo inbox=(JwmaInboxInfo) session.getValue("jwma.inboxinfo");
	JwmaPreferences prefs=(JwmaPreferences) session.getValue("jwma.preferences");
	JwmaMessage message=(JwmaMessage) session.getValue("jwma.message");
	JwmaFolder folder=(JwmaFolder) session.getValue("jwma.folder");
    JwmaContacts ctdb=(JwmaContacts) session.getValue("jwma.contacts");
	String inserthandler="onChange=\"this.form.to.value=this.value;\"";
	JwmaError error=null;
    Object o=session.getValue("jwma.error");
	if (o!=null) {
		error=(JwmaError) o;
    }
	//Prepare datestring
	String date=prefs.getDateFormat().format(message.getDate());
	if (message.isSent()) {
		date="<i>"+date+"</i>";
	}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN"
        "http://www.w3.org/TR/REC-html40/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta http-equiv="Pragma" content="no-cache">
    <title><fmt:message key="message.message"/></title>

</head>

<body bgcolor="#ffffff" link="#666666" vlink="#666666" alink="#FFFFFF">
<script type="text/javascript">
		var headerwindow=null;

		function submitDelete(aform) {
			//enhance: check for selected folders or mailboxes
			aform.todo.value="delete";
			aform.numbers.value="actual";
			aform.submit();
		}
		function submitMove(aform){
			aform.todo.value="move";
			aform.numbers.value="actual";
    		aform.submit();
		}
		function submitReply(aform){
			aform.action="bpm/mail/jwma/showReplyMail";
			aform.todo.value="compose";
			aform.reply.value="true";
      		aform.forward.value="false";
      		//$("#mailMessage").submit();
			//aform.submit();
		}
		function submitForward(aform){
			aform.action="bpm/mail/jwma/showReplyMail";
			aform.todo.value="compose";
			aform.forward.value="true";
       		aform.reply.value="false";
       		//$("#mailMessage").submit();
			//aform.submit();
		}
		function popup(pagename){
          var h=300;
          var w=600;
          var properties="height="+h+",width="+w+",resizeable=1,border=0,status=0,scrollbars=yes";
          myWind=window.open(pagename,"",properties);
        }//popup

	</script>

<%-- Display inlined error --%>
<% if (error!=null && !error.isDisplayed()) {
    String[] errors=error.getDescriptions();
    for (int i=0;i<errors.length;i++) {
%>
	<font size="+1" color="#ff0000"><b><fmt:message key="<%= errors[i] %>"/></b></font><br>
<%    error.setDisplayed(true);
    }
 }
%>
<%-- End Display inlined error --%>
<%-- Display message --%>
<div class="span12 box">
	<%@ include file="/common/messages.jsp" %>
	<div class="page-header">
		<div class="pull-left">
			<h2><%= message.getSubject() != null ? message.getSubject() : "(no subject)" %></h2>
		</div>
		<div class="height10"></div>
	    <div align="right"><a class="padding10" style="text-decoration: underline;padding: 10p" id="backToPreviousPage" href="javascript:void(0);" data-role="button" data-theme="b"  onClick="backToPreviousPage()" data-icon=""><strong><fmt:message key="Back"/></strong></a> </div>   
	</div>

	<div class="row-fluid">
		<div class="widget overflow">
			<div class="widget-body" >
				<form  id="mailMessage" method="post" method="post" action="" onSubmit="_execute('target','')" class="form-horizontal no-margin">
					<input type="hidden" name="acton" value="message">
				    <input type="hidden" name="todo" value="">
				    <input type="hidden" name="numbers" value="">
				    <input type="hidden" name="reply" value="">
				    <input type="hidden" name="forward" value="">
				    <input type="hidden" name="from" value="${from}">
				    <div class="span12">
				   		<div class="span8">
					    	<div class="control-group">
					    		<eazytec:label styleClass="control-label" key="message.from"/>
								<div class="controls">
									<label class="form-label">: ${fromAddress}</label> 
								</div>
							</div>
						    <div class="control-group">
					    		<eazytec:label styleClass="control-label" key="message.to"/>
								<div class="controls">
									<label class="form-label">: ${to}</label> 
								</div>
							</div>
						    <div class="control-group">
					    		<eazytec:label styleClass="control-label" key="compose.cc"/>
								<div class="controls">
									<label class="form-label">: ${cc}</label> 
								</div>
							</div>
							<div class="control-group">
					    		<eazytec:label styleClass="control-label" key="compose.bcc"/>
								<div class="controls">
									<label class="form-label">: ${bcc}</label> 
								</div>
							</div>
						</div>
						<div class="span4">
							<button name="replybutton" class="btn btn-primary" onClick="submitReply(this.form);"><fmt:message key="message.reply"/></button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					         <button name="forwardbutton" class="btn btn-primary" onClick="submitForward(this.form);"><fmt:message key="message.forward"/></button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					         <button type="button" name="deletemail" class="btn btn-primary" onClick="deleteMail('${from}','<%= message.getMessageNumber() %>');"><fmt:message key="button.delete"/></button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</div>
					</div>
					<div class="span8">
						<div class="control-group">
				    		<eazytec:label styleClass="control-label" key="message.subject"/>
							<div class="controls">
								<label class="form-label"> : <%= message.getSubject() != null ? message.getSubject() : "(no subject)" %></label> 
							</div>
						</div>
					</div>
					
					<c:if test="${folder == 'sent-later'}">
			    		<div class="span8">
						<div class="control-group">
				    		<eazytec:label styleClass="control-label" key="compose.sentLaterTime"/>
							<div class="controls">
								<label class="form-label"> : ${sentDate} </label> 
							</div>
						</div>
					</div>
			   		</c:if>
					<div class="span8">
						<div class="row-fluid">
				    		<% if (message.isSinglepart()) { %>
						          <pre><%= message.getBody() %></pre>
						        <% } else {
						              JwmaMessagePart[] parts=message.getMessageParts();
						              for (int i=0;i<parts.length;i++) {
						                if (prefs.isDisplayingInlined()) {
						        %>
						                  <%= htmlhelper.displayPartInlined(session, parts[i],prefs,"display") %>
						        <%      } else { %>
						
						                  <%= htmlhelper.getPartDescription(parts[i],"display") %>
						        <%
						                }
				              		  }
					           		}
						    %>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	
	
	
	<%-- <div class="span11 scroll">
		<div class="pad-L20">
			<form id="mailMessage" method="post" action="" onSubmit="_execute('target','')">
			  <input type="hidden" name="acton" value="message">
			  <input type="hidden" name="todo" value="">
			  <input type="hidden" name="numbers" value="">
			  <input type="hidden" name="reply" value="">
			  <input type="hidden" name="forward" value="">
			  <input type="hidden" name="from" value="${from}">
			  <table border="0" cellspacing="1" cellpadding="1">
			    <tr>
			      <td align="left" valign="top" width="143" bgcolor="#dddddd">
			        <b><fmt:message key="message.from"/>:</b>
			      </td>
			      <td colspan="2" align="left" valign="top" bgcolor="#eeeeee">
			      	${fromAddress}
			        <%= message.getFrom() %>
			      </td>
			      <td/>
			      <td/>
			      <td>
			      	 <button name="replybutton" class="btn btn-primary normalButton padding0 line-height0" onClick="submitReply(this.form);"><fmt:message key="message.reply"/></button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			         <button name="forwardbutton" class="btn btn-primary normalButton padding0 line-height0" onClick="submitForward(this.form);"><fmt:message key="message.forward"/></button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			         <button type="button" name="deletemail" class="btn btn-primary normalButton padding0 line-height0" onClick="deleteMail('${from}','<%= message.getMessageNumber() %>');"><fmt:message key="button.delete"/></button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</td>
			    </tr>
			    <tr>
			      <td align="left" valign=top width="143" bgcolor="#dddddd">
			        <b><fmt:message key="message.to"/>:</b>
			      </td>
			      <td colspan="2" align="left" valign=top bgcolor="#eeeeee">
			        <%= message.getTo() %>
			        ${to}
			      </td>
			    </tr>
			    <tr>
			      <td align="left" valign=top width="143" bgcolor="#dddddd">
			        <b><fmt:message key="compose.cc"/>:</b>
			      </td>
			      <td colspan="2" align="left" valign=top bgcolor="#eeeeee">
			        <%= message.getCCTo() %>
			        ${cc}
			      </td>
			    </tr>
			    <tr>
			      <td align="left" valign=top width="143" bgcolor="#dddddd">
			        <b><fmt:message key="compose.bcc"/>:</b>
			      </td>
			      <td colspan="2" align="left" valign=top bgcolor="#eeeeee">
			        <%= message.getBCCTo() %>
			        ${bcc}
			      </td>
			    </tr>
			    <tr>
			      <td  align=left valign=top width="143" bgcolor="#dddddd" height="22">
			        <b><fmt:message key="message.subject"/>:</b>
			      </td>
			      <td  align=left valign=top width="587" bgcolor="#eeeeee" height="22">
			        <em><%= message.getSubject() != null ? message.getSubject() : "(no subject)" %></em>
			      </td>
			    </tr>
			    <c:if test="${folder == 'sent-later'}">
			     <tr>
			    	<td align="left" valign=top width="143" bgcolor="#dddddd">
				      <b><fmt:message key="compose.sentLaterTime"/>:</b>
				      </td>
				      <td colspan="2" align="left" valign=top bgcolor="#eeeeee">
						${sentDate}
			      </td>
			    </tr>
			    </c:if>
			    <tr>
			    	<td  align="left" valign="top" colspan="3" bgcolor="#eeeeee">
				        <% if (message.isSinglepart()) { %>
				          <pre><%= message.getBody() %></pre>
				        <% } else {
				              JwmaMessagePart[] parts=message.getMessageParts();
				              for (int i=0;i<parts.length;i++) {
				                if (prefs.isDisplayingInlined()) {
				        %>
				                  <%= htmlhelper.displayPartInlined(session, parts[i],prefs,"display") %>
				        <%      } else { %>
				
				                  <%= htmlhelper.getPartDescription(parts[i],"display") %>
				        <%
				                }
				              }
				           }
						    %>
			      </td>
			      
			    </tr>
			   		<tr><td>&nbsp;</td></tr>
			    <tr>
				<td colspan="3">
			      	 <button name="replybutton" class="btn btn-primary normalButton padding0 line-height0" onClick="submitReply(this.form);"><fmt:message key="message.reply"/></button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			         <button name="forwardbutton" class="btn btn-primary normalButton padding0 line-height0" onClick="submitForward(this.form);"><fmt:message key="message.forward"/></button>&nbsp;
			         <input type="text" id="to" name="to" class="mini white-background" onclick="showUserSelection('User', 'multi' , 'to', this, '');" readonly="readonly"/>
			        <input type="checkbox" name="toall" value="true">
			        <font face="Arial, Helvetica, sans-serif" color="#ffffff" size="-1">
			          <fmt:message key="message.toggle.toall"/>
			        </font>
			        <input type="checkbox" name="togglequote" value="true">
			        <font face="Arial, Helvetica, sans-serif" color="#ffffff" size="-1">
			          <fmt:message key="message.toggle.autoquote"/>
			        </font>
			    </tr>
<!-- 			    <tr> -->
<!-- 			      <td colspan="3" align="left" bgcolor="#000000" height="24" nowrap> -->

			        <!-- <input type="text" name="to" size="30"> -->
			        <%= htmlhelper.getFrequentSelect(ctdb,inserthandler) %>&nbsp;&nbsp;
			        <input type="checkbox" name="withattachments" value="true">
			        <font face="Arial, Helvetica, sans-serif" color="#ffffff" size="-1">
			          <fmt:message key="message.toggle.withattachments"/>
			        </font>
<!-- 			      </td> -->
<!-- 			    </tr> -->
			  </table>
			</form>
		</div>
	</div> --%>
</div>
</body>
</html>
