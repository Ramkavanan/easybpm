<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Compose Mail</title>
</head>
<body>

	<div class="span10" class="height-per-100 width-per-100">
		<div class="grid-top" style="background-image: url(images/easybpm/common/grid_top.jpg);">
			<span class="style14 panel-left-text">Compose </span>
			<div class="panel-right-btn">
				<img src="images/buttons/send.png">&nbsp; 
				<img src="images/buttons/saveToDraft.png">&nbsp; 
				<img src="images/buttons/cancel_button.jpg">
			</div>
		</div>
		<div class="span12 box content-ckeditor mar-T0" >
			<div id="target" class="table-create-user">
				<table width="607" border="1">
					<tr>
						<td width="70" height="25"><span class="style18 style3">To</span></td>

						<td width="500">
							<form name="form1" method="post" action="">
								<label> 
									<input class="mail-text" type="text" name="text" id="text">
								</label>
							</form>
						</td>
					</tr>
					<tr>
						<td width="60"></td>
						<td width="70" height="40">
							<span class="style22 style19 style3">Add Cc</span>&nbsp;&nbsp;&nbsp;
							<span class="style22 style19 style3">Add Bcc</span>
						</td>
					</tr>
					<tr>
						<td width="70" height="25"><span class="style3 style18">Subject</span></td>

						<td width="500">
							<form name="form1" method="post" action="">
								<label> 
									<input class="mail-text" type="text" name="text" id="text">
								</label>
							</form>
						</td>
					</tr>
					<tr>
						<td width="60"></td>
						<td width="70" height="40"><span class="style22 style19 style3">Attach file</span></td>
					</tr>
				</table>
				<textarea cols="80" id="editor1" name="editor1" rows="10"> Test </textarea>
				<script>
					CKEDITOR.replace('editor1', {
						fullPage : true,
						extraPlugins : 'wysiwygarea'
					});
				</script>
			</div>
		</div>
	</div>
</body>
</html>