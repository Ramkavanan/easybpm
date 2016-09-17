<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript">
$(window).resize(function() {
    $("#processPreviewPopupDialog").dialog("option", "position", "center");
});

$(document).ready(function(){
    $("#processPreviewPopupDialog").dialog("option", "position", "center");
});

</script>
 <form id="urgeForm" method="post" data-remote="true" accept-charset="UTF-8" onSubmit="_execute('target','');" class="padding5">
<div id="jump-full">
		 <input type="hidden" id="organizers" name="organizers" value="${organizers}">
	     <div class="controls">
	         	<textarea rows="6" cols="50" name="notificationMessage" id="notificationMessage" class="textarea-miniwidth" >${notificationMessage}</textarea>
	     </div>
	     <fieldset class="control-group">
	     <label class="checkbox inline style3 style18">
              <input type="checkbox" id="Mail" name="Mail" checked="checked">
               <fmt:message key="MAIL"/>
         </label>
		<label class="checkbox inline style3 style18">
              <input type="checkbox" id="Sms" name="Sms">
               <fmt:message key="SMS"/>
         </label>
         <label class="checkbox inline style3 style18">
              <input type="checkbox" id="internalMsg" name="internalMsg">
               <fmt:message key="Internal Message"/>
         </label>
        
         </fieldset>
          <br></br>
	<input type="button" onclick="sendUrgeNotification();" value="Send" class="btn btn-primary">
</div>
</form>
