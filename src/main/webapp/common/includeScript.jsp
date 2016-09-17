<c:choose>
	<c:when test="${prefLang == null || prefLang == 'en' || prefLang == ''}">
		<script type="text/javascript" src="<c:url value='/scripts/easybpm/form/translation_en.js'/>"></script>
	</c:when>
	<c:otherwise>
		<script type="text/javascript" src="<c:url value='/scripts/easybpm/form/translation_zh_cn.js'/>"></script>
	</c:otherwise>
</c:choose>
<%@page import="com.eazytec.util.PropertyReader"%>
<%
if("production".equalsIgnoreCase(PropertyReader.getInstance().getPropertyFromFile("String", "deployment.mode"))) { %>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/bpm-all.js"></script>
	<script type="text/javascript" src="<c:url value='/scripts/ckeditor/ckeditor.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/scripts/easybpm/form/form.js'/>"></script>
	<!-- jstree start -->
	<script type="text/javascript" src="<c:url value='/scripts/easybpm/jstree/jquery.jstree.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/scripts/easybpm/jstree/jstree-common.js'/>"></script>
	<!-- jstree end -->
<% } else { %>

<script type="text/javascript" src="<c:url value='/scripts/boostrap/jquery.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/scripts/boostrap/bootstrap.js'/>"></script>
<script type="text/javascript" src="<c:url value='/scripts/boostrap/moment.js'/>"></script>
	<!-- Flot charts -->
<script type="text/javascript" src="<c:url value='/scripts/boostrap/flot/jquery.flot.js'/>"></script>
<script type="text/javascript" src="<c:url value='/scripts/boostrap/flot/jquery.flot.selection.js'/>"></script>	
<script type="text/javascript" src="<c:url value='/scripts/boostrap/flot/jquery.flot.pie.js'/>"></script>

	<!-- Google Visualization JS -->
<script type="text/javascript" src="<c:url value='/scripts/boostrap/google/jsapi'/>"></script>

	<!-- Easy Pie Chart JS -->
<script type="text/javascript" src="<c:url value='/scripts/boostrap/pie-charts/jquery.easy-pie-chart.js'/>"></script>

<!-- Added for the notification bar -->
<script src="<c:url value='/scripts/jquery.noty.packaged.min.js'/>" type="text/javascript"></script>

<!-- Ie8 Support placeholder -->
<script src="<c:url value='/scripts/placeholder/placeholders.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/scripts/placeholder/placeholders.jquery.js'/>" type="text/javascript"></script>

	<!-- Sparkline charts -->
<script type="text/javascript" src="<c:url value='/scripts/boostrap/sparkline.js'/>"></script>

	<!-- Datatables JS -->
<%-- <script type="text/javascript" src="<c:url value='/scripts/boostrap/jquery.dataTables.js'/>"></script> --%>

	<!-- Calendar Js -->
<%-- <script type="text/javascript" src="<c:url value='/scripts/boostrap/fullcalendar/jquery-ui-1.10.2.custom.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/scripts/boostrap/fullcalendar/fullcalendar.min.js'/>"></script>
 --%>
<script type="text/javascript" src="<c:url value='/scripts/easybpm/jqueryUI/jquery-ui-1.8.22.js'/>"></script>
	<!-- Tiny scrollbar js -->
<script type="text/javascript" src="<c:url value='/scripts/boostrap/tiny-scrollbar.js'/>"></script>

	<!-- Custom Js -->
<%-- <script type="text/javascript" src="<c:url value='/scripts/boostrap/custom-index.js'/>"></script>	 --%>
<%-- <script type="text/javascript" src="<c:url value='/scripts/boostrap/custom-calendar.js'/>"></script> --%>

<%-- <script type="text/javascript" src="<c:url value='/scripts/lib/jquery-1.7.2.min.js'/>"></script> --%>	
<script type="text/javascript" src="<c:url value='/scripts/lib/jquery.smart_autocomplete.js'/>"></script>


	<!-- Easy User Wizard -->
<script type="text/javascript" src="<c:url value='/scripts/boostrap/wizard/bwizard.js'/>"></script>

<script type="text/javascript" src="<c:url value='/scripts/easybpm/jqueryUI/jquery-ui-timepicker-addon.js'/>"></script>
<%-- <script type="text/javascript" src="<c:url value='/scripts/lib/bootstrap-2.2.1.min.js'/>"></script> --%>
<script type="text/javascript" src="<c:url value='/scripts/lib/plugins/jquery.cookie.js'/>"></script>
<script type="text/javascript" src="<c:url value='/scripts/lib/plugins/jquery.msgBox.js'/>"></script>
<%-- <script type="text/javascript" src="<c:url value='/scripts/lib/plugins/jquery.msgBox.min.js'/>"></script> --%>
<script type="text/javascript" src="<c:url value='/scripts/easybpm/listview/listview.js'/>"></script>
<script type="text/javascript" src="<c:url value='/scripts/script.js'/>"></script>
<script type="text/javascript" src="<c:url value='/scripts/lib/plugins/jquery.form.js'/>"></script>

<script type="text/javascript" src="<c:url value='/scripts/easybpm/layout/ajax_loader.js'/>"></script>
<script type="text/javascript" src="<c:url value='/scripts/lib/plugins/jquery.fullscreen-min.js'/>"></script>

<!-- backbone js start  -->
<script type="text/javascript" src="<c:url value='/scripts/lib/plugins/underscore-min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/scripts/lib/plugins/backbone.js'/>"></script>
<script type="text/javascript" src="<c:url value='/scripts/app.js'/>"></script>
<!-- backbone js end  -->

<!-- <script src="http://code.jquery.com/jquery-1.8.3.js"></script>
<script src="http://code.jquery.com/ui/1.10.0/jquery-ui.js"></script> -->

<script type="text/javascript" src="<c:url value='/jqgrid/js/jquery.jqGrid.src.js'/>"></script>
<!-- jqgrid js end -->

<!-- basic layout js start  -->
<%-- <script type="text/javascript" src="<c:url value='/scripts/easybpm/jqueryUI/jquery.ui.accordion.min.js'/>"></script> --%>
<script type="text/javascript" src="<c:url value='/scripts/easybpm/jqueryUI/setup.js'/>"></script>
<!-- basic layout js end -->

<!-- Task Related -->
<script type="text/javascript" src="<c:url value='/scripts/easybpm/layout/layout.js'/>"></script>

<!-- form builder js start  -->
<%-- <script type="text/javascript" src="<c:url value='/scripts/easybpm/form/rbl_base.js'/>"></script>
<script type="text/javascript" src="<c:url value='/scripts/easybpm/form/rbl_form.js'/>"></script>
<script type="text/javascript" src="<c:url value='/scripts/easybpm/form/rbl_col.js'/>"></script>
<script type="text/javascript" src="<c:url value='/scripts/easybpm/form/rbl_elt.js'/>"></script> 
<script type="text/javascript" src="<c:url value='/scripts/easybpm/form/rbl_build.js'/>"></script> --%>
<script type="text/javascript" src="<c:url value='/scripts/ckeditor/ckeditor.js'/>"></script>
<script type="text/javascript" src="<c:url value='/scripts/easybpm/form/form.js'/>"></script>

<!-- CK editor -->
<!-- form builder js end  -->
<!-- jstree start -->
<script type="text/javascript" src="<c:url value='/scripts/easybpm/jstree/jquery.jstree.js'/>"></script>
<script type="text/javascript" src="<c:url value='/scripts/easybpm/jstree/jstree-common.js'/>"></script>
<!-- jstree end -->

<!--General  -->
<script type="text/javascript" src="<c:url value='/scripts/easybpm/general/general.js'/>"></script>
<script type="text/javascript" src="<c:url value='/scripts/easybpm/general/prettyXMLView.js'/>"></script>


<script type="text/javascript" src="<c:url value='/scripts/easybpm/jqueryUI/jquery.autoSuggest.js'/>"></script>

<script type="text/javascript" src="<c:url value='/scripts/easybpm/operatingfunction.js'/>"></script>

<!-- Jquery fullcalender -->
<script type="text/javascript" src="<c:url value='/scripts/fullcalender/js/fullcalendar.js'/>"></script>
<%-- <script type="text/javascript" src="<c:url value='/scripts/fullcalender/js/fullcalendar.min.js'/>"></script> --%>

<!-- office automation -->
<script type="text/javascript" src="<c:url value='/scripts/easybpm/oa/admin.js'/>"></script>
<script type="text/javascript" src="<c:url value='/scripts/easybpm/oa/schedule.js'/>"></script>
<script type="text/javascript" src="<c:url value='/scripts/easybpm/oa/dms.js'/>"></script>
<script type="text/javascript" src="<c:url value='/scripts/easybpm/oa/mail.js'/>"></script>
<script type="text/javascript" src="<c:url value='/scripts/easybpm/oa/quartz.js'/>"></script>
<script type="text/javascript" src="<c:url value='/scripts/easybpm/oa/pdf.js'/>"></script>
<script type="text/javascript" src="<c:url value='/scripts/easybpm/oa/compatibility.js'/>"></script>
<!-- print preview-->
<script type="text/javascript" src="<c:url value='/scripts/print/js/jquery.print-preview.js'/>"></script>

<!-- chat -->
<script type="text/javascript" src='/org/cometd.js'></script>
<script type="text/javascript" src='/org/cometd/AckExtension.js'></script>
<script type="text/javascript" src='/org/cometd/ReloadExtension.js'></script>
<script type="text/javascript" src='/org/cometd/jquery.cometd.js'></script>
<script type="text/javascript" src='/org/cometd/jquery.cometd-reload.js'></script>
<script type="text/javascript" src='/org/cometd/chat.window.js'></script>
<script type="text/javascript" src='/org/cometd/comet.chat.js'></script>

<% } %>
<!-- jqgrid js start  -->
<c:choose>
	<c:when test="${prefLang == null || prefLang == 'en' || prefLang == ''}">
		<script type="text/javascript" src="<c:url value='/jqgrid/js/i18n/grid.locale-en.js'/>"></script>
	</c:when>
	<c:otherwise>
		<script type="text/javascript" src="<c:url value='/jqgrid/js/i18n/grid.locale-cn.js'/>"></script>
	</c:otherwise>
</c:choose>