<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="mainMenu.title"/></title>
    <meta name="menu" content="MainMenu"/>
	<link rel="stylesheet" media="screen and (device-height: 600px)" />
</head>
<body>
		<div class="span10" style="height:100%; width:100%;">
  		<div class="grid-top" style="background-image:url(/images/easybpm/common/grid_top.jpg);">
  		<span  class="style14 panel-left-text">Mailbox Settings</span> </div>
 		<div class="span12 box content-ckeditor mar-T0" >
			<div id="target" class="span10">
			  <table width="117%" height="334" border="1">
			  <tr>
				<td width="273" height="47">
				<span class="style21">User Name</span></td>
				    <td width="530">:<input type="text" name="textfield" id="textfield"> </td>
				</tr>
				<tr> <td height="47"><span class="style21">E-mail Address</span></td>
				    <td>: <input type="text" name="textfield" id="textfield">      </td>
				</tr>
				<tr><td height="43"><span class="style21">E-mail Password</span></td>
				    <td>: <input type="text" name="textfield" id="textfield"></td>
				  </tr>
				  <tr>
				    <td height="45"><span class="style21">Outgoing Mail Server</span></td>
				    <td>: <input type="text" name="textfield" id="textfield"></td>
				  </tr>
				  <tr>
				    <td height="46"><span class="style21">Incoming Mail Server</span></td>
				    <td>: <input type="text" name="textfield" id="textfield"></td>
				  </tr>
				  <tr>
				    <td height="90"><span class="style21">Delete message on the server at the same time</span></td>
				     <td width="436">: 
				      <input type="radio" id="radio-1-1" name="radio-1-set" class="regular-radio" checked /><label for="radio-1-1"></label><span>Delete</span>&nbsp;
					<input type="radio" id="radio-1-2" name="radio-1-set" class="regular-radio" /><label for="radio-1-2"></label>Don't Delete
				   </td>
				  </tr>
			</table>
			
			<label for="editor1">
							
						</label>
						<textarea cols="80" id="editor1" name="editor1" rows="10"></textarea>
						<script>
			
							CKEDITOR.replace( 'editor1', {
								/*
								 * Style sheet for the contents
								 */
								contentsCss: 'assets/outputxhtml/outputxhtml.css',
								fullPage: false,
								/*
								 * Core styles.
								 */
								coreStyles_bold: {
									element: 'span',
									attributes: { 'class': 'Bold' }
								},
								coreStyles_italic: {
									element: 'span',
									attributes: { 'class': 'Italic' }
								},
								coreStyles_underline: {
									element: 'span',
									attributes: { 'class': 'Underline' }
								},
								coreStyles_strike: {
									element: 'span',
									attributes: { 'class': 'StrikeThrough' },
									overrides: 'strike'
								},
								coreStyles_subscript: {
									element: 'span',
									attributes: { 'class': 'Subscript' },
									overrides: 'sub'
								},
								coreStyles_superscript: {
									element: 'span',
									attributes: { 'class': 'Superscript' },
									overrides: 'sup'
								},
			
								/*
								 * Font face.
								 */
			
								// List of fonts available in the toolbar combo. Each font definition is
								// separated by a semi-colon (;). We are using class names here, so each font
								// is defined by {Combo Label}/{Class Name}.
								font_names: 'Comic Sans MS/FontComic;Courier New/FontCourier;Times New Roman/FontTimes',
			
								// Define the way font elements will be applied to the document. The "span"
								// element will be used. When a font is selected, the font name defined in the
								// above list is passed to this definition with the name "Font", being it
								// injected in the "class" attribute.
								// We must also instruct the editor to replace span elements that are used to
								// set the font (Overrides).
								font_style: {
									element: 'span',
									attributes: { 'class': '#(family)' },
									overrides: [
										{
											element: 'span',
											attributes: {
												'class': /^Font(?:Comic|Courier|Times)$/
											}
										}
									]
								},
			
								/*
								 * Font sizes.
								 */
								fontSize_sizes: 'Smaller/FontSmaller;Larger/FontLarger;8pt/FontSmall;14pt/FontBig;Double Size/FontDouble',
								fontSize_style: {
									element: 'span',
									attributes: { 'class': '#(size)' },
									overrides: [
										{
											element: 'span',
											attributes: {
												'class': /^Font(?:Smaller|Larger|Small|Big|Double)$/
											}
										}
									]
								} ,
			
								/*
								 * Font colors.
								 */
								colorButton_enableMore: false,
			
								colorButton_colors: 'FontColor1/FF9900,FontColor2/0066CC,FontColor3/F00',
								colorButton_foreStyle: {
									element: 'span',
									attributes: { 'class': '#(color)' },
									overrides: [
										{
											element: 'span',
											attributes: {
												'class': /^FontColor(?:1|2|3)$/
											}
										}
									]
								},
			
								colorButton_backStyle: {
									element: 'span',
									attributes: { 'class': '#(color)BG' },
									overrides: [
										{
											element: 'span',
											attributes: {
												'class': /^FontColor(?:1|2|3)BG$/
											}
										}
									]
								},
			
								/*
								 * Indentation.
								 */
								indentClasses: [ 'Indent1', 'Indent2', 'Indent3' ],
			
								/*
								 * Paragraph justification.
								 */
								justifyClasses: [ 'JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyFull' ],
			
								/*
								 * Styles combo.
								 */
								stylesSet: [
									{ name: 'Strong Emphasis', element: 'strong' },
									{ name: 'Emphasis', element: 'em' },
			
									{ name: 'Computer Code', element: 'code' },
									{ name: 'Keyboard Phrase', element: 'kbd' },
									{ name: 'Sample Text', element: 'samp' },
									{ name: 'Variable', element: 'var' },
			
									{ name: 'Deleted Text', element: 'del' },
									{ name: 'Inserted Text', element: 'ins' },
			
									{ name: 'Cited Work', element: 'cite' },
									{ name: 'Inline Quotation', element: 'q' }
								]
							});
			
						</script>
			</div>
		</div>
	</div>
    <div class="clear"></div>
</body>