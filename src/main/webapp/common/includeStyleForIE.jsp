<%@page import="com.eazytec.util.PropertyReader"%>

<!-- Bootstrap css -->
<c:if test="${prefSkin == ''}">
<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/styles/bootstrap/main.css'/>" />
</c:if>
<c:if test="${prefSkin != ''}">
<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/styles/bootstrap/${prefSkin}.css'/>" />
</c:if>
<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/styles/lib/themes/msgBoxLight.css'/>" />

<!-- NVD graphs css -->
<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/styles/bootstrap/nvd-charts.css'/>" />

<!-- fullcalendar css -->
<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/styles/bootstrap/fullcalendar/fullcalendar.css'/>" />
<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/styles/bootstrap/fullcalendar/fullcalendar.print.css'/>" />
	

<%--  <link rel="stylesheet" type="text/css" media="all" href="<c:url value='/styles/lib/bootstrap-2.2.1.min.css'/>" />
<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/styles/lib/bootstrap-responsive-2.2.1.css'/>" />
<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/styles/style.css'/>" /> --%>


<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/styles/easybpm/jqueryUI/jquery-ui-1.8.24.css'/>" />
<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/styles/lib/themes/msgBoxLight.css'/>" />
<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/styles/easybpm/jqueryUI/jquery-ui-timepicker-addon.css'/>" />

<link href='http://fonts.googleapis.com/css?family=PT+Sans:regular,italic,bold,bolditalic&amp;subset=latin,latin-ext,cyrillic' rel='stylesheet' type='text/css'>
<!-- basic layout css start -->
<%-- <link rel="stylesheet" type="text/css" media="all" href="<c:url value='/styles/easybpm/basicLayout/reset.css'/>" />
<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/styles/easybpm/basicLayout/text.css'/>" / --%>


<%-- <link rel="stylesheet" type="text/css" media="all" href="<c:url value='/styles/easybpm/basicLayout/nav.css'/>" />
<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/styles/easybpm/basicLayout/tabs.css'/>" />
<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/styles/easybpm/basicLayout/table.css'/>" /> --%>
<!-- basic layout css end -->

<!-- jqgrid css start -->
<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/jqgrid/css/ui.jqgrid.css'/>" />
<!-- jqgrid css end -->

<!-- <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.0/themes/base/jquery-ui.css" /> -->

<!-- Task Related -->
<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/styles/easybpm/task/detailedview.css'/>" />

<!--General  -->
<%-- <link rel="stylesheet" type="text/css" media="all" href="<c:url value='/styles/easybpm/general/general.css'/>" /> --%>

<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/styles/easybpm/general/prettyXmlView.css'/>" />
<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/styles/easybpm/jqueryUI/autoSuggest.css'/>" />

<!-- Common -->
<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/styles/easybpm/common/common.css'/>"/>

<!-- Form -->
<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/styles/easybpm/form/local.css'/>" />
<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/styles/easybpm/form/form.css'/>" />

<!-- ckeditor -->
<%-- <link rel="stylesheet" type="text/css" media="all" href="<c:url value='/styles/ckeditor/samples/sample.css'/>" /> --%>

<!-- Jquery Fullcalender -->
<%-- 
<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/scripts/fullcalender/css/fullcalendar.css'/>" />
<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/scripts/fullcalender/css/fullcalendar.print.css'/>" media='print'/> --%>

<!-- print preview-->
<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/scripts/print/css/print-preview.css'/>" media="print"/>

<!-- chat -->
<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/styles/easybpm/chat/comet.chat.css'/>" />