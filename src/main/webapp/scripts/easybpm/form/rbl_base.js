/**
 * Search the browser language
 */
if (navigator.browserLanguage)
  var xlang = navigator.browserLanguage;
else
  var xlang = navigator.language;

var LANG = 'us' ;
if (xlang.indexOf('fr') > -1) LANG = 'fr' ;


/**
 * Deleting an element of the form (element or simple element SELECTING column)
 */
function deleteElement( ) {
	var _no = $(selected).attr('id');
	if ( _no.indexOf("f_col_") == 0) {
		supCol ( _no );
		$("#generalProperties").empty();
		$("#advancedProperties").empty();
	}
	else {
		supElt( _no );
		$("#generalProperties").empty();
		$("#advancedProperties").empty();
	}
}


function initDrop( ) {
	$("ul.connectedSortable")
		.sortable({
			connectWith: '.connectedSortable',
			items: 'li.elt'
		 })
		.disableSelection()
		.resizable(
		{
			stop: function(event, ui) { 
				var _id = $(this).attr("id_n") ; 
				if (rblColArray[ _id ]) {
					rblColArray[ _id ].e_width  = $(this).css("width") ;
					rblColArray[ _id ].e_height = $(this).css("height") ;
				}
			}
		});
		
	$('ul#formSubMenu li').draggable(
		{
		cursor: 'move',
	  	helper: 'clone',
		opacity: '0.5', 
		zIndex: 1000,
	  	revert: 'invalid'
		}
	);
	
	$('.connectedSortable').droppable(
	  {
		cursor: 'move',
		accept: 'ul#formSubMenu li', 
		helper: 'clone', 
		opacity: '0.5', 
		drop: function(event, ui) {
			$("#properties_error").hide();
			addNewElement( ui, this);
		}
	  }
	);
	
}


/**
 * Saving a form element
 */
function saveInputPanel(_no) {
	if ( rblEltArray[ _no]  != null) {
		rblEltArray[ _no].saveForm() ;
	} else {
		alert ("elt " + _no + " null") ; 
	}
	changeInput( _no ) ; //in rbl_elt.js
	callBuildXml();
	callHTMLGeneration();
}


/**
 * Display the dialog box associated with the selected element
 * Changing a form element (element or simple element selected column)
 */
function showControlPanel( _no, mode) {
	$("#propertiesDiv").show();
	$("#propertyTab-content").show();
	if (  !_no  || _no.length < 1) {
		 _no = $(selected).attr('id');
	}
	if ( _no.indexOf("f_col_") != 0) 
	{
		if (_no.length > 2) {
			if (_no.charAt(0) == 'f' && _no.charAt(1) == '_' ) {
				_no = _no.substr(2) ;
			}
		}
	}
	
	if ( _no.indexOf("f_col_") == 0)  
	{
    		$("#generalProperties").load("/images/html/bpm/"+ LANG +"/dialog_col.html",
			function() { 
				$("#e_id").val( _no );
				if(rblColArray[ _no]!=undefined){
					rblColArray[ _no].showForm();
				}
				$("#advancedProperties").empty();
			});
	} 
	else 
	{
		var typeToAdd = "" ;
		var title = "";
		var advPropertyType="notAtextBox";
		if ( rblEltArray[ _no]  != null) 
		{
			typeToAdd = rblEltArray[ _no ].getType();
		}
		
		typeToAdd = typeToAdd.toLowerCase();
		if (typeToAdd.length == 0) typeToAdd = "text";
		/*added by megala*/
		title = setTitle(typeToAdd, title, _no);
		if (typeToAdd == "checkbox") {typeToAdd = "radio";}
		if (typeToAdd == "password") {typeToAdd = "text";advPropertyType="textBox";}
		if (typeToAdd == "file") {typeToAdd = "upload";}
		if (typeToAdd == "email"){typeToAdd = "text";advPropertyType="textBox";}
		if(typeToAdd=="text" || typeToAdd=="textarea" ){advPropertyType="textBox";}
		var typeToAddHtml = "";
		if(typeToAdd.indexOf('button') > -1){
			typeToAddHtml = "/images/html/bpm/"+ LANG +"/input_button.html";
		}else{
			typeToAddHtml = "/images/html/bpm/"+ LANG +"/input_" + typeToAdd + ".html";
		}	
		$("#generalProperties").load(typeToAddHtml,
		  function() { 
			$("#e_id").val( _no ) ;
			if ( rblEltArray[ _no]  != null) 
			{
				rblEltArray[ _no].showForm() ;
			}
			/*added by megala*/
			setNameAndLabel(title, _no);
			if(typeToAdd.indexOf('button') > -1){
				if(rblEltArray[ _no ].e_val_defaut == null){
					if(typeToAdd.indexOf('submitbutton') > -1){
						$('#val_defaut').val('Submit');
					}else if(typeToAdd.indexOf('userdefinebutton') > -1){
						$('#val_defaut').val('New');
					}
				}else{
					$('#val_defaut').val(rblEltArray[ _no ].e_val_defaut);
				}
			}else if(typeToAdd.indexOf('textarea') > -1){
				if(rblEltArray[ _no ].e_size == null){
					$('#size').val('2');
				}else{
					$('#size').val(rblEltArray[ _no ].e_size);
				}
				if(rblEltArray[ _no ].e_maxlength == null){
					$('#maxlength').val('18');
				}else{
					$('#maxlength').val(rblEltArray[ _no ].e_maxlength);
			
				}
			}
			
			if(mode == "create"){
				saveInputPanel( _no ) ; //in rbl_base.js
			}
		});
		if(advPropertyType=="notAtextBox"){
			 if(typeToAdd.indexOf('userdefinebutton') > -1){
      		   $("#advancedProperties").load("/images/html/bpm/"+ LANG +"/input_userdefined_button.html",
                     function() { 
                     setEventHandlerValues(_no);
                     $('#btnId').val(rblEltArray[ _no ].e_btnId);
                     $('#btnStyle').val(rblEltArray[ _no ].e_btnStyle);
                });     
			   }else{
				   $("#advancedProperties").load("/images/html/bpm/"+ LANG +"/input_advanced_properties.html",
	                  function() { 
	                   //$("#e_id").val( _no ) ;
	                   setEventHandlerValues(_no);
	               });  
			   }
           }else{
                   $("#advancedProperties").load("/images/html/bpm/"+ LANG +"/input_new_advanced_properties.html",
                       function() { 
                       //$("#e_id").val( _no ) ;
                       setEventHandlerValues(_no);
                   });             
           }
	}
}

function setTitle(typeToAdd, title, _no){
	if(rblEltArray[ _no ].e_label == null){
		if (typeToAdd == "text") {title="Text Box";}
		if (typeToAdd == "textarea") {title= "Text Area";}
		if(typeToAdd == "select") {title = "Select Box";}
		if(typeToAdd == "radio") {title = "Radio Button";}
		if (typeToAdd == "date"){title = "Date Picker";}
		if (typeToAdd == "checkbox") {title="Check Box";}
		if (typeToAdd == "password") {title="Password";}
		if (typeToAdd == "file") {title="File Upload";}
		if (typeToAdd == "email"){title = "E-mail";}
	}else{
		title = rblEltArray[ _no ].e_label;
	}
	return title;
}

function setNameAndLabel(title, _no){
	if(title != ""){
		var name = title.toLowerCase().replace(/[" "]/g,"");
		name = name.trim();
		
		var id = _no.split("_");
		//name = name + "_" + id[1];
		$('#name').val(name);
		rblEltArray[ _no ].e_name = name;
		$('#title').val(title);
	}
	if(title == 'Check Box' || title == 'Radio Button'){
		if(rblEltArray[ _no ].e_val_liste == null){
			$('#val_liste').val('option1=Option1;option2=Option2;')
		}
	}
	if(title == 'E-mail') $('#mask').val('email');
}

function setEventHandlerValues(_no){
	var clickFunc = rblEltArray[ _no ].e_onClick;
	var blurFunc = rblEltArray[ _no ].e_onBlur;
	var changeFunc = rblEltArray[ _no ].e_onChange;
	var focusFunc = rblEltArray[ _no ].e_onFocus;
	$("#onClick").val(clickFunc);
	$("#onBlur").val(blurFunc);
	$("#onChange").val(changeFunc);
	$("#onFocus").val(focusFunc);
}

function enableRowSelectable(id) {
	    $(id).live("click", 
		function(){
	    	$("#properties_error").hide();
			$(selected).removeClass("form-line");
			$(selected).removeClass("question-selected");
			$(selected).children('.button-container').removeClass("displayBlock");
			$(selected).children('.button-container').addClass("displayNone");
			
			selected = this;
			showControlPanel("","edit");
			$(this).addClass("form-line");
			$(this).addClass("question-selected");
			$(this).children('.button-container').removeClass("displayNone");
			$(this).children('.button-container').addClass("displayBlock");
		});
}

function escapeH( ss ) {
	var str = ss ;
	var findReplace = [[/&/g, "&amp;"], [/</g, "&lt;"], [/>/g, "&gt;"], [/"/g, "&quot;"]]

	for(item in findReplace)
		str = str.replace(item[0], item[1]);

	return str ;
}

