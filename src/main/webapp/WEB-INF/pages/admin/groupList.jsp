<%@ include file="/common/taglibs.jsp" %>

<%-- <head>
    <title><fmt:message key="groupList.title"/></title>
    <meta name="menu" content="AdminMenu"/>
</head>
<%@ include file="/common/messages.jsp" %>
<%= request.getAttribute("script") %>
<script type="text/javascript">
 $(document).ready(function(){
       $('#grid_header_links').html($('#header_links').html());
});
</script>

<div id="header_links" align="right" style="display:none">
	<a style="padding:10px;" id="createUser" href="#bpm/admin/groupForm" data-role="button" data-theme="b"  onClick="_execute('target','');"  data-icon=""><fmt:message key="button.createNew"/></a>
	<a style="padding:10px;" id="deleteUsers" href="javascript:void(0);" data-role="button" data-theme="b"  onClick="deleteGroups()"  data-icon=""><fmt:message key="button.delete"/></a>
</div> --%>
 <%@ include file="/common/messages.jsp" %>
<div class="page-header">
	<div class="pull-left">
		<h2><fmt:message key="user.manageGroups"/></h2>
	</div>

<div class="height10"></div>
<div id="header_links" align="right">
	<a class="padding3" id="createUser" href="#bpm/admin/groupForm" data-role="button" data-theme="b"  onClick="_execute('target','');"  data-icon=""><button class="btn"><fmt:message key="button.createNew"/></button></a>
	<a class="padding3" id="deleteUsers" href="javascript:void(0);" data-role="button" data-theme="b"  onClick="deleteGroups()"  data-icon=""><button class="btn"><fmt:message key="button.delete"/></button></a>
</div>
</div>
<div><%= request.getAttribute("script") %></div>


<!--
<div class="span10">
    <h2><fmt:message key="groupList.heading"/></h2>

    <form method="get" action="${ctx}/admin/groups" id="searchForm" class="form-search">
    <div id="search" class="input-append">
        <input type="text" size="20" name="q" id="query" value="${param.q}"
               placeholder="<fmt:message key="search.enterTerms"/>" class="input-medium search-query"/>
        <button id="button.search" class="btn" type="submit">
            <i class="icon-search"></i> <fmt:message key="button.search"/>
        </button>
    </div>
    </form>

    <div id="actions" class="form-actions">
        <a class="btn btn-primary" href="<c:url value='/admin/newGroup'/>">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>

        <a class="btn" href="<c:url value='/mainMenu'/>">
            <i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

    <display:table name="groupList" cellspacing="0" cellpadding="0" requestURI=""
                   defaultsort="1" id="groups" pagesize="25" class="table table-condensed table-striped table-hover" export="true">
        <display:column property="name" escapeXml="true" sortable="true" titleKey="group.groupname" style="width: 25%"
                        url="/admin/editGroup?from=list" paramId="id" paramProperty="id"/>
        <display:column property="description" escapeXml="true" sortable="true" titleKey="group.decsription"
                        style="width: 34%"/>
       

        <display:setProperty name="paging.banner.item_name"><fmt:message key="groupList.group"/></display:setProperty>
        <display:setProperty name="paging.banner.items_name"><fmt:message key="groupList.groups"/></display:setProperty>

        <display:setProperty name="export.excel.filename" value="Group List.xls"/>
        <display:setProperty name="export.csv.filename" value="Group List.csv"/>
        <display:setProperty name="export.pdf.filename" value="Group List.pdf"/>
    </display:table>
</div>-->