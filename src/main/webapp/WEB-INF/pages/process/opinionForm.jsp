<%@ include file="/common/taglibs.jsp"%>


<div>
	<spring:bind path="opinion.*">
		<%@ include file="/common/messages.jsp"%>
	</spring:bind>


	<div>
		<form:form id="opinion" commandName="opinion" method="post"
			autocomplete="off" modelAttribute="opinion">
			<form:hidden path="id"/>
			<label class="style3 style18 ">Opinion</label>
			<table id="opinionTable" class="table table-condensed no-margin">
				<c:if test="${isFormReadOnly != true}">
					<textarea id="textarea_autocomplete_field" autocomplete="off"
						cols="25" rows="5" style=" width: 500px;"></textarea>
					<tr>
						<td style="padding: 0;" width="436" class="border-none">
							<button type="button"
								class="btn btn-primary normalButton padding0 line-height0"
								name="save" onclick="saveOpinion()" id="saveButton">
								<fmt:message key="button.save" />
							</button>
						</td>
					</tr>
					<tr>
						<td class="border-none"><br></td>
					</tr>
				</c:if>
				<c:forEach items="${opinionList}" var="opinionMsg">
					<tr id='${opinionMsg.id}'>
						<td width="80%" class="border-none"><label class="style3 style18 ">Submitted
								By :&nbsp;${opinionMsg.userId} <br> Opinion :
								&nbsp;${opinionMsg.message} <br> Submit Date :
								&nbsp;${opinionMsg.submittedOn} <br> Task Name :
								&nbsp;${opinionMsg.taskName} <br>
						</label> &nbsp;
							<!-- <hr width="100%"> --></td>
						<c:if
							test="${opinionMsg.taskId == taskId && opinionMsg.userId == loggedInUser}">
							<td class="border-none"><a class="padding10" onclick="deleteOpinion('${opinionMsg.id}')" href="javascript:void(0);" id="deleteOpinion">Delete</a></td>
						</c:if>
					</tr>
				</c:forEach>
			</table>
		</form:form>
	</div>
</div>

<script type="text/javascript">
	$(document).ready(
			function() {
				var jazz_musicians = [];
				$.ajax({
					type : 'GET',
					async : false,
					url : 'bpm/opinion/getUserOpinionsByUser',
					success : function(response) {
						if($.browser.version  < 12){
							$.each(response, function(index, item) {
							jazz_musicians[jazz_musicians.length] = "<a href='#' onclick='opinionOnClick();'><font size='3' color='white'>"+item+"</font></a>";

	           					 });
						} else {
							jazz_musicians = response;
						}
						
						
					}
				});

				$("#textarea_autocomplete_field").smartAutoComplete({
					source : jazz_musicians,
					maxResults : 5,
					delay : 200
				});
				$("#textarea_autocomplete_field").bind(
						{
							keyIn : function(ev) {
								var tag_list = ev.smartAutocompleteData.query
										.split(",");
								//pass the modified query to default event
								ev.smartAutocompleteData.query = $
										.trim(tag_list[tag_list.length - 1]);
							},
							itemSelect : function(ev, selected_item) {
								var options = $(this).smartAutoComplete();
								//get the text from selected item
								var selected_value = $(selected_item).text();
								var cur_list = $(this).val().split(",");
								cur_list[cur_list.length - 1] = selected_value;
								$(this).val(cur_list.join(","));
								//set item selected property.25
								options.setItemSelected(true);
								//hide results container
								$(this).trigger('lostFocus');
								//prevent default event handler from executing
								ev.preventDefault();
							},
						});
			});
			function opinionOnClick(){
			}
	function deleteOpinion(opinionId) {
		$.ajax({
			type : 'get',
			async : false,
			url : 'bpm/opinion/deleteOpinion?opinionId=' + opinionId,
			success : function(data) {
				$('#' + opinionId).remove();
			}
		});
	}

	function saveOpinion() {
		var message = document.getElementById("textarea_autocomplete_field").value;
		var taskId = $('#taskId').val();
		var processInsId = $('#processInsId').val();
		var taskName = $('#taskName').val();
		//var regex = /^[^*|\":<>[\]{}`\\()';@&$]+$/;
		if (message.trim() == '') {
			validateMessageBox(form.title.message, form.msg.opnionMandatory , false);
		} /* else if (!regex.test(message)) {
			$
					.msgBox({
						title : "Message",
						content : "Numbers and special characters are not allowed for Opinion"
					});
		} */ else {
			var userFullName = $('#userFullName').val();
			$
					.ajax({
						type : 'post',
						async : false,
						url : '/bpm/opinion/saveOpinion?message=' + message
								+ '&taskId=' + taskId + '&processInsId='
								+ processInsId + '&taskName='
								+ taskName +'&userFullName='+userFullName,
						success : function(data) {
							document
									.getElementById("textarea_autocomplete_field").value = "";
							var table = document.getElementById("opinionTable");
							var row = table.insertRow(1);
							var cell1 = row.insertCell(0);
							var elemenet1 = document.createElement('label');
							elemenet1.setAttribute("class", "style3 style18");
							cell1.setAttribute("width", "80%");
							cell1.setAttribute("class","border-none");
							row.setAttribute("id", data.id);
							elemenet1.innerHTML = "<br>" + "Submitted By :"
									+ data.userFullName + "<br>Opinion :  "
									+ data.message + "<br>Submit Date :  "
									+ data.submitStr + "<br>Task Name :  "
									+ data.taskName
									+ "<br>&nbsp;<hr width='100%'>";
							cell1.appendChild(elemenet1);
							var cell2 = row.insertCell(1);
							var elemenet2 = document.createElement('a');
							cell2.setAttribute("class","border-none");
							cell2.setAttribute("width", "20%");
							elemenet2.setAttribute("onclick", "deleteOpinion('"
									+ data.id + "')");
							elemenet2
									.setAttribute("href", "javascript:void(0)");
							elemenet2
							.setAttribute("class", "padding10");
							elemenet2.innerHTML = "Delete";

							cell2.appendChild(elemenet2);

							/* var row1 = table.insertRow(1);
							var cell2 = row1.insertCell(0);

							var elemenet2 = document.createElement('label');
							elemenet2.setAttribute("class", "style3 style18");
							elemenet2.innerHTML = "Opinion :  " + data.message;
							cell2.appendChild(elemenet2);

							var row2 = table.insertRow(1);
							var cell3 = row2.insertCell(0);

							var elemenet3 = document.createElement('label');
							elemenet3.setAttribute("class", "style3 style18");

							elemenet3.innerHTML = "Submitted By :"
								+ data.userId;
							cell3.appendChild(elemenet3);

							var row3 = table.insertRow(1);
							var cell4 = row3.insertCell(0);

							var elemenet4 = document.createElement('label');
							elemenet4.innerHTML = "&nbsp;<hr width='100%'>";
							cell4.appendChild(elemenet4);
							 */
						}
					});
		}
	}
</script>
<v:javascript formName="opinion" staticJavascript="false" />
<script type="text/javascript"
	src="<c:url value="/scripts/validator.jsp"/>"></script>
