<%@ page import="com.eazytec.common.util.CommonUtil"%>
<%
String loggedInUserName = CommonUtil.getLoggedInUserId();
%>
<%@ include file="/common/taglibs.jsp"%>
<head>
    <title><fmt:message key="documentForm.title"/></title>
    <meta name="heading" content="<fmt:message key='documentForm.title'/>"/>
</head>

<script type="text/javascript">
/* 
	 $("#save").click(function(event) { 
		var word = $('#word').val();
		if(word = "" || word.length <=0) {
			openMessageBox("Error","Opinion should not be Empty","error", true);
		    event.preventDefault();
		}
	});

 function displayunicode(e){ 
 var unicode=e.keyCode? e.keyCode : e.charCode 
 alert(unicode) 
 }   */
//  <form> 
//     <textarea type="text" onkeypress="this.value=this.value + 'onkeypress '"></textarea><br/>
//  <input type="text" size="2" maxlength="1" onkeyup="displayunicode(event); this.select()" /> 
//  </form> 

</script>

<%-- <div class="span12 box">
 	<h2><fmt:message key="User Opinion"/>
    </h2>
    <spring:bind path="userOpinion.*">
        <%@ include file="/common/messages.jsp" %>
    </spring:bind>  
</div>
	<div class="span11">
	<div style="padding-left:100px;">
 	
		<form:form commandName="userOpinion" method="post" action="bpm/opinion/saveUserOpinion" id="userOpinion" autocomplete="off" 
			   modelAttribute="userOpinion" enctype="multipart/form-data" cssClass="form-horizontal"  onSubmit="_execute('target','')">
			<c:if test="${not empty userOpinion.id}">
			<form:hidden path="id"/>
			</c:if>
			<form:hidden path="userId" value="<%=loggedInUserName%>"/>
			<datalist id="searchUserOpinionList"> </datalist>
			<table>
				<tr>
					<td width="271" height="40"><eazytec:label styleClass="control-label style3 style18" key="userOpinion.word"  />
			   		<td width="436" class="uneditable-input1 pad-T10">
			   		<form:input path="word" rows="4" cols="27" class="medium" id="searchUserOpinion" onkeyup="getUserOpinion(event);" list="searchUserOpinionList" autocomplete="off" name="searchUserOpinion"/>
			   			<form:input path="word" rows="4" cols="27" class="medium" id="word" name="userOpinion"/>
			   		</td>
			   		
				</tr>
							
				<tr>
	   				<td></td>
    				<td width="436">
               				<c:choose>
                                <c:when test ="${userOpinion.id != null}">
                   					<button type="submit" class="btn btn-primary normalButton padding0 line-height0  margin20" name="save" onclick="bCancel=false;" id="save">              
                           	    	 <fmt:message key="button.update"/>          	    	  
			    					</button>
                       			</c:when>
                                <c:otherwise>
                                	 <br></br>	
    	           					<button type="submit" class="btn btn-primary normalButton padding0 line-height0  margin20" name="save" onclick="bCancel=false;" id="save" >              
                               		 <fmt:message key="button.save"/>
                               		 </button>
                               	</c:otherwise>
                               	</c:choose>
                                <button type="button" class="btn btn-primary normalButton padding0 line-height0" name="next" onclick="backToPreviousPage();" id="cancelButton" style="cursor: pointer;">
			        				<fmt:message key="button.cancel"/>
								</button>
                       			
                    		
				    </td>
				</tr>
			</table>
		</form:form>
	</div>
</div> --%>

<div class="page-header">
	
		<h2><fmt:message key="userOpinion.title"/></h2>
		<spring:bind path="userOpinion.*">
        	<%@ include file="/common/messages.jsp" %>
    	</spring:bind> 
	
	
</div>
<div align="right"><a class="padding10" style="text-decoration: underline;" id="backToPreviousPage" href="javascript:void(0);" data-role="button" data-theme="b"  onClick="showListViews('USEROPINION','User Opinion');" data-icon=""><strong><fmt:message key="button.back"/></strong></a> 
</div>
<div class="height10"></div>
<div class="widget">
	<div class="widget-body">
		<form:form commandName="userOpinion" method="post" action="bpm/opinion/saveUserOpinion" id="userOpinion" autocomplete="off"  modelAttribute="userOpinion" enctype="multipart/form-data" cssClass="form-horizontal no-margin"  onSubmit="_execute('target','')">
			<c:if test="${not empty userOpinion.id}">
			<form:hidden path="id"/>
			</c:if>
			<form:hidden path="userId" value="<%=loggedInUserName%>"/>
			<datalist id="searchUserOpinionList"> </datalist>
			
			<div class="control-group">
				<eazytec:label styleClass="control-label" key="userOpinion.word"  />
				<div class="controls">
					<%-- <form:input path="word" rows="4" class="span6" id="searchUserOpinion" onkeyup="getUserOpinion(event);" list="searchUserOpinionList" autocomplete="off" name="searchUserOpinion"/> --%>
					<form:textarea path="word" rows="4" class="span6" id="word" name="userOpinion"/>
				</div>
			</div>
			
			<div class="control-group">
				<div class="form-actions no-margin">
					<c:choose>
						<c:when test ="${userOpinion.id != null}">
							<button type="submit" class="btn btn-primary " name="save" onclick="bCancel=false;" id="save">              
								<fmt:message key="button.update"/>          	    	  
							</button>
						</c:when>
						<c:otherwise>
							<button type="submit" class="btn btn-primary " name="save" onclick="bCancel=false;" id="save">              
								<fmt:message key="button.save"/>
							</button>
						</c:otherwise>
					</c:choose>
					<button type="button" class="btn btn-primary" name="next" onclick="showListViews('USEROPINION','User Opinion');" id="cancelButton" class="cursor_pointer;">
						<fmt:message key="button.cancel"/>
					</button>
					<div class="clearfix"></div>
				</div>
			</div>
		</form:form>
	</div>
</div>
