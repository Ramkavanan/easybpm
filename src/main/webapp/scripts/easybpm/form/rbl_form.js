var rblFormArray = new Array();
var IDFORM = "form-1" ;
var IDFS   = "fs-1"   ;
var CURFS = IDFS ;
var PRVFS = "" ;

function rbl_form( _id, _name ) { 

	var e_id    = _id ;
	var e_name   ; 
	var e_file  ;
	var e_label  ;
	var e_action ;
	var e_method     ;
	var e_css_class  ;
	var e_width   ;
	var e_height  ;
	var e_desc ;
	

  this.init = function(_name, _label, _action, _method, _layout, _css, _desc) { 
		this.e_id		  = e_id  ;
		this.e_desc		= _desc;
		this.e_name 	=  _name  ;
		this.e_label 	=  _label ;
		this.e_action	=  _action ;
		this.e_method =  _method  ;
		this.e_layout =  _layout ;
		this.e_css_class  = _css ;
  }
  
  this.saveForm = function() { 
		this.e_id		  = e_id  ;
		this.e_name 	=  $("#e_name").val().replace('\'',"`") ;
		this.e_desc 	=  $("#e_desc").val()  ;
		this.e_file 	=  $("#e_file").val() ;
		this.e_label 	=  $("#e_name").val().replace('\'',"`") ;

		this.e_action	=  $("#e_action").val()  ;
		this.e_method =  $("#e_method").val()  ;
		this.e_layout =  $("#e_layout").val()  ;
		this.e_css_class  = $("#e_css_class").val() ;
		
		this.e_width   = $("#e_width" ).val() ;
		this.e_height  = $("#e_height").val() ;
  } 

  this.showForm = function() { 
		$("#e_name").val( this.e_name )  ;
		$("#e_desc").val( this.e_desc )  ;
		$("#e_label").val( this.e_label ) ;
		$("#e_file").val( this.e_file ) ;
		$("#e_action").val( this.e_action )  ;
		$("#e_method").val( this.e_method )  ;
		$("#e_css_class").val( this.e_css_class ) ;
		$("#e_layout").val( this.e_layout ) ;
		$("#e_width").val( this.e_width ) ;
		$("#e_height").val( this.e_height ) ;
  } 
	
  this.drawForm = function() { 
	var mon_form ;
	var lgd ;
	mon_form = $("#" + this.e_id) ;
	if (this.e_css_class && this.e_css_class > 0) {
		$("#main-f1").removeClass().addClass(this.e_css_class) ;
	}
	$("#main-f1").css("height:435px;overflow-x:hidden;overflow-y:auto;padding-left: 10px;");
	if ( mon_form.length == 0 ) 
	{
		var ss = "" ;
		/*if (this.e_label && this.e_label.length > 0) {
			ss = "<legend>" + this.e_label + "</legend>" ;
		}*/
		$("#main-f1").append("<form" + this.drawName()  
									+ this.drawMethod()  
									+ this.drawAction()  
									+ this.drawClass()  
									+ ">" 
									+ "<fieldset id='" + IDFS + "' style='padding: 0px 1px; margin: 0px 0px ;'>" 
									/*+ ss */
									+ "</fieldset>"
									+"</form>")  ;
	}	
	else 
	{
	  	mon_form.attr('id',this.e_id)
	  			    .attr('name',this.e_name)
	  			    .attr('method',this.e_method)
	  			    .attr('action',this.e_action) ;

			mon_form.removeClass( ) ;
			mon_form.addClass(this.e_css_class) ;

			/*lgd = mon_form.find("#" + IDFS ).find('legend') ;
			if (this.e_name && this.e_name.length > 0) {
				if (lgd.length > 0) {
					lgd.text( this.e_name ) ;
			  } else {
			  	mon_form.find("#" + IDFS ).prepend("<legend>"+ this.e_name +"</legend>") ;
			  }
			} else {
				if (lgd.length > 0) {
					mon_form.find("#" + IDFS ).remove('legend') ;
				}
			}*/
	}
	  
  }

  this.drawStyle = function() { 
	var str = "" ;
	if (this.e_width && this.e_width.length > 0) {
		str += "width:" + this.e_width +";";
	} 
	if (this.e_height && this.e_height.length > 0) {
		str += "height:" + this.e_height + ";" ;
	} 

	if ( (this.e_width && this.e_width.length > 0) || 
		 (this.e_height && this.e_height.length > 0) ) {
		 str = " style='" + str + "'" ;
	}

	return str ;
  }
  this.drawWidth = function() { 
	var str = "" ;
	if (this.e_width && this.e_width.length > 0) {
		str += " width='" + this.e_width +"'";
	} 
	if (this.e_height && this.e_height.length > 0) {
		str += " height='" + this.e_height +"'";
	} 
	return str ;
  }
  
  this.drawName = function() { 
	var str = "" ;

	str = " id='" + e_id + "'" ;
	
	if (this.e_name.length > 0 )
	{
		str = str +  " name='" + this.e_name + "'" ;
	}
	
	return str ;
  }
	
  this.drawMethod = function() { 
	var str = "" ;

	if (this.e_method.length > 0 )
	{
		str = " method='" + this.e_method + "'" ;
	} 
	else {
		str = " method='POST'" ;
  	}
		
	return str ;
  }

  this.drawAction = function() { 
	var str = "" ;

	if (this.e_action.length > 0 )
	{
		str = " action='" + this.e_action + "'" ;
	} 

	return str ;
  }
	
  this.drawLayout = function() { 
	var str = "" ;

	if (this.e_layout && this.e_layout.length > 0 )
	{
		str = " layout='" + this.e_layout + "'" ;
	} 
	
	return str ;
  }
  this.drawClass = function() { 
	var str = "" ;
	if (this.e_css_class && this.e_css_class.length > 0 )
	{
		str = " class='" + this.e_css_class + "'" ;
	} 
		
	return str ;
  }
  this.drawLabel = function() { 
	var str = "" ;

	if (this.e_label && this.e_label.length > 0 )
	{
		str = " label='" + this.e_label + "'" ;
	} 
		
	return str ;
  }
  this.drawDesc = function() { 
		var str = "" ;

		if (this.e_desc && this.e_desc.length > 0 )
		{
			str = " desc='" + this.e_desc + "'" ;
		} 
			
		return str ;
	  }
  
  this.startXml = function() { 
	var str = "" ;

	str = str + "<form initiator = '" + this.e_name + "'" + this.drawName()
	/*str = str + "<form" + this.drawName()*/
						+ this.drawMethod()  
						+ this.drawAction()  
						+ this.drawLayout()  
						+ this.drawClass()  
						+ this.drawLabel()  
						+ this.drawWidth()  
						+ this.drawDesc()
						+ ">" + "\r\n" + "<extensionElements>" ;
						/*+ ">" + "\r\n" ;*/
	
	return str ;
  }
  this.endXml = function() { 
		var str = "" ;
		str = "</extensionElements>" + "\r\n" + "</form>" ;
		/*str = "</form>";*/
		return str ;
	}

  this.startHtml = function() { 
	var str = "" ;

	/* this.drawName()   */

	str = str + "<form" +
						" id='"+this.e_name+"' name='" + this.e_name +"'"
						+ this.drawMethod()  
						+ this.drawAction()  
						+ this.drawClass()  
						+ this.drawStyle()  
						+ ">" + "\r\n";

	str += "<fieldset id='x" + IDFS + "'>" 
	if (this.e_name && this.e_name.length > 0) {
			str += "<legend>" + this.e_name + "</legend>" ;
	}
		
	CURFS =	"x" + IDFS ;
	PRVFS = "" ;
	
	return str ;
  }
  this.endHtml = function() { 
	var str = "" ;
	str = "</fieldset></form>" ;
	return str ;
  }	
} 

/**
 * Display the dialog box associated with the selected element
 */
function showFormPanel(isEditFormPalette) {
		$("#propertiesDiv").show();
		$("#propertyTab-content").show();
	    $("#generalProperties").load("images/html/bpm/"+ LANG +"/dialog_form.html?b",
				function() { 
					rblFormArray[ IDFORM ].showForm();
					$("#advancedProperties").empty();
					//$('#dialog_form').dialog('open');
					 if(isEditFormPalette == "true"){
					    	$("#e_name").attr("disabled", "disabled");
					 }
				});
}

/**
 * Display all form list
 */
function addFormPanel( ) {
    $("#propertiesDiv").show();
	$("#propertyTab-content").show();
    $("#generalProperties").load("images/html/bpm/"+ LANG +"/add_sub_form.html?b",
	function() { 
		rblFormArray[ IDFORM ].showForm();
		$("#advancedProperties").empty();
	});
}

/**
 * Display all form list
 */
function addTemplatePanel( ) {
    $("#propertiesDiv").show();
	$("#propertyTab-content").show();
    $("#generalProperties").load("images/html/bpm/"+ LANG +"/add_template.html?b",
	function() { 
		rblFormArray[ IDFORM ].showForm();
		$("#advancedProperties").empty();
	});
}

/**
 * Saving proproetees form
 */
function saveFormPanel( ) {
	if ( rblFormArray[ IDFORM ]  != null) {
		rblFormArray[ IDFORM ].saveForm() ;
		rblFormArray[ IDFORM ].drawForm() ;
		callBuildXml();
		callHTMLGeneration();
	} else {
		alert ("Formulaire   null") ; 
	}

}

/**
 * Initialization of the first form
 */
function initFormData( ) {
	if ( rblFormArray[ IDFORM ]  == null) 
	{
		rblFormArray[ IDFORM ] = new rbl_form( IDFORM, IDFORM ) ;
	}
	
	rblFormArray[ IDFORM ].init( "", "Form 1", "#", "POST", "", "","") ;
	rblFormArray[ IDFORM ].drawForm();
}

function loadForm( elt ) {
	if ( rblFormArray[ IDFORM ]  == null) 
	{
		rblFormArray[ IDFORM ] = new rbl_form( IDFORM, IDFORM ) ;
	}
	rblFormArray[ IDFORM ].init( $(elt).attr('name'), $(elt).attr('name'), $(elt).attr('action'), $(elt).attr('method'), "", $(elt).attr('class'), $(elt).attr('desc')) ;
	//rblFormArray[ IDFORM ].e_file = file ;
	rblFormArray[ IDFORM ].e_width = $(elt).attr('width') ;
	rblFormArray[ IDFORM ].e_height = $(elt).attr('height') ;
	rblFormArray[ IDFORM ].drawForm();
	drawMainDiv() ;
}
function drawMainDiv() { 
	var str = "" ;
	var aa = $("#main-f1") ;
	
	str = "97%" ;
	if (rblFormArray[ IDFORM ].e_width && rblFormArray[ IDFORM ].e_width.length > 0) {
		str = rblFormArray[ IDFORM ].e_width ;
		if (str.indexOf('px') > 0)  {
			str = (20 + parseInt(str) ) + "px" ;
		}
	}
	aa.css("width", str ) ;
	
	str = "" ;
	if (rblFormArray[ IDFORM ].e_height && rblFormArray[ IDFORM ].e_height.length > 0) {
		str = rblFormArray[ IDFORM ].e_height ;
		if (str.indexOf('px') > 0)  {
			str = (40 + parseInt(str) ) + "px" ;
		}
	}
	aa.css("height", str ) ;
	aa.css("overflow-x:hidden;overflow-y:auto;padding-left: 10px;");
 }


/* properties script */

function generateReadOnly(){
	if($("#readOnly").hasClass('displayNone')){
		$("#readOnly").removeClass('displayNone');
		$("#readOnly").addClass('displayBlock');
	}else if($("#readOnly").hasClass('displayBlock')){
		$("#readOnly").removeClass('displayBlock');
		$("#readOnly").addClass('displayNone');
	}
}

function generateRequired(){
	if($("#isRequired").hasClass('displayNone')){
		$("#isRequired").removeClass('displayNone');
		$("#isRequired").addClass('displayBlock');
	}else if($("#isRequired").hasClass('displayBlock')){
		$("#isRequired").removeClass('displayBlock');
		$("#isRequired").addClass('displayNone');
	}
}

function setRequriedAndReadOnly(){
	if($('#required').is(":checked")){
		if($("#readOnly").hasClass('displayBlock')){
			$("#readOnly").removeClass('displayBlock');
			$("#readOnly").addClass('displayNone');
		}
	}else if($('#readonly').is(":checked")){
		if($("#isRequired").hasClass('displayBlock')){
			$("#isRequired").removeClass('displayBlock');
			$("#isRequired").addClass('displayNone');
		}
	}
}

function formTabActions(tabId){
    var tabId = tabId.split('-');
    var hidePalette = document.getElementById("hiddedPalette");
    var paletteList = document.getElementById("paletteList");
    var hiddedPaletteList = document.getElementById("hiddedFormPalette");
    if(tabId == 1){
    	hidePalette.style.display = 'none';
    	paletteList.style.display = 'block';
    	$("#sourceView").css('width','85%');
    }
    if(tabId == 2){
    	hidePalette.style.display = 'block';
    	paletteList.style.display = 'none';
    	hiddedPaletteList.style.display = 'none';
    	$("#sourceView").css('width','96.9%');
    	var height = $("#target").height();
    	$("#htmlsource").css('height',parseInt(height)-110);
    	$("#propertiesDiv").hide();
    	/*$("#fieldProperties").css('background-color','#fff');*/
    	callHTMLGeneration();
    }
    if(tabId == 3){
    	hidePalette.style.display = 'block';
    	paletteList.style.display = 'none';
    	hiddedPaletteList.style.display = 'none';
    	
    	var height = $("#target").height();
    	$("#xmlsource").css('height',parseInt(height)-105);
    	$("#sourceView").css('width','96.9%');
    	$("#propertiesDiv").hide();
    	/*$("#fieldProperties").css('background-color','#fff');*/
   		callBuildXml();
    }
    if(tabId == 4){
    	hidePalette.style.display = 'block';
    	paletteList.style.display = 'none';
    	hiddedPaletteList.style.display = 'none';
    	$("#sourceView").css('width','96.9%');
    	var height = $("#target").height();
    	$("#previewdst").css('height',parseInt(height)-105);
    	$("#propertiesDiv").hide();
    	/*$("#fieldProperties").css('background-color','#fff');*/
	    xmlStr  = build_html() ;
		$("#previewdst").html( xmlStr ) ;
    }
}

function setPropertiesTabs(){
	$('#propertyTab-content').addClass('js');
	$("#propertyTab li").each(function(){
		$(this).click(function(){
			var tabId = $(this).attr('id');
			tabId = tabId.split('-');
			var tabContent = document.getElementById('propertyTab-content-' + tabId[1]);
		    tabContent.style.display = 'block';
		    $(this).addClass('selected');
		    $(this).siblings().removeClass('selected');	
		    $(tabContent).siblings().css('display','none');
		});
	});
}

function propertiesMsgBox(title, content, type, focusId){
	$.msgBox({
		title:title,
		content:content,
	    type: type,
	    buttons: [{ value: "Ok" }],
	    success: function (result) {
	        if (result == "Ok") {
	        	if(type == "error"){
	        	 $("#"+focusId).focus();
	        	}
	        }
	    }
	});
}