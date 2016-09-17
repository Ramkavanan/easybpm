function callBuildXml(){
	xmlStr  = build_xml_elt();
	$("#xmlsource").text( xmlStr );
	setFormValues();
}

function callHTMLGeneration(){
	htmlStr = build_html();
	$("#htmlsource").text( htmlStr ) ;
	setFormValues();
}

/**
 * Construction of the form as XML string
 */ 
function build_xml_elt( ) { 
	var str = "" ;

	str = str + "<?xml version='1.0' encoding='utf-8'?>\r\n" ;
	
	if (rblFormArray[ IDFORM ] ) {
		str = str + rblFormArray[ IDFORM ].startXml() + "\r\n" ;
	
		var _col   = 0 ;
		var _ligne = 0 ;
		var _id_c ;
		
		$("#" + IDFORM).find("ul")
						.each( function(){

							_id_c = $(this).attr("id_n") ;
							str = str + rblColArray[ _id_c ].startXml() ;

							$(this).find("li")
									.each( function(){
										var _id  = $(this).attr('id') ;
										var _x   = _id.indexOf("f_col_") ;
										if ( _x == 0) {
											_col   = _col + 1 ;
											_ligne = 0 ;
										} 
										else {
											_ligne = _ligne + 1 ;
												_id = _id.substr(2) ;
												if ( rblEltArray[ _id ] != null ) {
													str = str + "  "+ rblEltArray[ _id ].drawXml(_col, _ligne) +"\r\n";
											  }
											}								
									});
											
							str = str + rblColArray[ _id_c ].endXml() ;
						});
			
		str = str + rblFormArray[ IDFORM ].endXml() ;
	}
	else {
			alert ( 'form not defined' );
	}
	return str ;
} 

/**
 * Construction of the HTML form as a string
 */ 
function build_html( ) { 
	var str = "" ;
	if (rblFormArray[ IDFORM ] ) {
		str = str + rblFormArray[ IDFORM ].startHtml() + "\r\n" ;
		
		var _col   = 0 ;
		var _ligne = 0 ;
		var _id_c ;
		$("#" + IDFORM)
			.find("ul")
			.each( function(){

				_id_c = $(this).attr("id_n") ;
				str = str + rblColArray[ _id_c ].startHtml().replace("connectedSortable", "previewConnectedSortable p-1-col")  + "\r\n";
				$(this).find("li")
						.each( function(){
								var _id  = $(this).attr('id') ;
								var _x   = _id.indexOf("f_col_") ;
								if ( _x == 0) {
									_col   = _col + 1 ;
									_ligne = 0 ;
								} 
								else {
									_ligne = _ligne + 1 ;
									_id = _id.substr(2) ;
									if ( rblEltArray[ _id ] != null ) {
										str += "<li id='f_" + _id +"' >\r\n  ";
										str += rblEltArray[ _id ].drawElement0(true) ;
										if (rblEltArray[ _id ].e_sub_type != "wizard") str += "\r\n</li>\r\n" ;
									}
								}								
						});
					
				/* chain ends with a </ div> if this is the last Desk mounted (then pass already closed) */
				if ( str.indexOf('</div>', str.length - 8) < 0 )
					str = str + rblColArray[ _id_c ].endHtml()  + "\r\n" ;
			});
			str = str + rblFormArray[ IDFORM ].endHtml() + "\r\n" ;
			
			var newClass =  'p-' + _col + '-col';
			str = str.replace(/p-1-col/g, newClass);
	}
	else {
		alert ( 'form not defined' );
	}
	return str ;
} 

/**
 * Loading XML form
 */ 
function load_xml() {
	var xmlHTML = $("#xmlsource").text();
	var xmlDoc =  $.parseXML( xmlHTML );
	var nu_col = 1;
	var total_col = 0;
	$("#main-f1").find('form').remove();
	$(xmlDoc).find('form').each(function(){
		loadForm( this);
		var name = $(this).find('name').text();
		$(xmlDoc).find('column').each(function(){
			total_col++ ;
		});
	});
	$(xmlDoc).find('column').each(function(){
		loadCol( this, nu_col ,total_col,true) ;

		$(this).find('formProperty').each(function(){
			//added by megala:
			var rbl_array = "";
			$(this).find('value').each(function(){
				rbl_array += loadElementValues(this, nu_col, nbelt);
			});
			loadElement( this, nu_col, nbelt, rbl_array);
			nbelt++ ;
		});
		nu_col++ ;
	});
	initDrop();
	//$("#tabs").tabs("select", "tab-content-1");
	var tabContent = document.getElementById('tab-content-1');
    tabContent.style.display = 'block';
    $(this).addClass('selected');
    $(this).siblings().removeClass('selected');	
    $(tabContent).siblings().css('display','none');
	$(".connectedSortable li").removeClass("selected");
	callHTMLGeneration();
	var height = $("#target").height();
	$("#main-f1").height(parseInt(height)-235);
	/*$(document.getElementById('sourceView')).css('width','96.9%');*/
	$("#tabs-3").removeClass("selected");
	$("#tabs-1").addClass("selected");
}

/**
 * Loading HTML form
 * added by rajmohan
 */ 
function load_html() {
	var xmlHTML = $("#htmlsource").text();
	var xmlDoc = new DOMParser().parseFromString(xmlHTML, 'text/html');
	var nu_col = 1;
	var total_col = 0;
	$("#main-f1").find('form').remove();
	$(xmlDoc).find('form').each(function(){
		var name = $(xmlDoc).find('legend').text();
		$(this).attr('label',name);
		loadForm( this);
		var name = $(this).find('name').text();
		$(xmlDoc).find('ul').each(function(){
			total_col++ ;
		});
	});

	$(xmlDoc).find('ul').each(function(){
		loadCol( this, nu_col ,total_col,false) ;

		$(this).find('li').each(function(){
			var html_Eml_Type="";
			var rbl_array = "";
				
			var label="";
			var label_id="";
			var title="";
			$(this).find('label').each(function(){
				label=$(this).text().replace(/["*"]/g,"");
				label_id=$(this).attr('for');
			});
			
			$(this).find('img').each(function(){
				title=$(this).attr('title');
			});
			
			$(this).find('textarea').each(function(){
				html_Eml_Type="textarea";
				$(this).attr('label',label);
				$(this).attr('title',title);
				loadHTMLElement( this, nu_col, nbelt, rbl_array ,html_Eml_Type);
				nbelt++ ;
			});
			var tempCount=0;
			$(this).find('input').each(function(){
				tempCount++;
			});
			var radioCount=0;
			$(this).find('input').each(function(){
				radioCount++;
				html_Eml_Type="input";
				$(this).attr('label',label);
				$(this).attr('title',title);
				if($(this).attr('type')=="radio" || $(this).attr('type')=="checkbox"){
					rbl_array += loadElementValuesInHtml(this, nu_col, nbelt,"radio");
				}
				
				if(radioCount==tempCount){
					loadHTMLElement( this, nu_col, nbelt, rbl_array ,html_Eml_Type);
					tempCount=0;
				}
				nbelt++ ;
			});
			
			$(this).find('select').each(function(){
				html_Eml_Type="select";
				$(this).attr('title',title);
				$(this).attr('label',label);
				$(this).attr('type','enum');
				$(this).find('option').each(function(){
					rbl_array += loadElementValuesInHtml(this, nu_col, nbelt,"option");
				});
				loadHTMLElement( this, nu_col, nbelt, rbl_array ,html_Eml_Type);
				nbelt++ ;
			});
			
		});
			nu_col++ ;
		});
	initDrop();
	var tabContent = document.getElementById('tab-content-1');
    tabContent.style.display = 'block';
    var hidePalette = document.getElementById("hiddedPalette");
    var paletteList = document.getElementById("paletteList");
    hidePalette.style.display = 'none';
	paletteList.style.display = 'block';
	$("#sourceView").css('width','85%');
    $(this).addClass('selected');
    $(this).siblings().removeClass('selected');	
    $(tabContent).siblings().css('display','none');
	$(".connectedSortable li").removeClass("selected");
	callBuildXml();
	var height = $("#target").height();
	$("#main-f1").height(parseInt(height)-235);
	/*$(document.getElementById('sourceView')).css('width','96.9%');*/
	$("#tabs-2").removeClass("selected");
	$("#tabs-1").addClass("selected");
}

/*function parseXML(val) {
	alert("val ======= > "+val);
    if (document.implementation && document.implementation.createDocument) {
        xmlDoc = new DOMParser().parseFromString(val, 'text/xml');
    }
    else if (window.ActiveXObject) {
        xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
        xmlDoc.loadXML(val);
    }
    else
    {
        alert('Your browser can\'t handle this script');
        return null;
    }
    return xmlDoc;
}*/

/**
 * Loading XML form
 */ 
function save_xml( surl ) { 

	var bVal ;
	bVal = rblFormArray[ IDFORM ].e_file ;
	if ( !(bVal && bVal.length > 0) ) {
		alert("You must specify the file name in the dialog form before backup !") ;
		return ;
	}
	
	$.ajax({
		type: "POST",
		url: "php/save_xml.php",
		cache: false,
		data: ({file: bVal,
				data : build_xml_elt()}),
		dataType: "text",
		complete : function(data, status) {
			alert("xml backup : " + bVal + "/" + status ) ;
	}});

	$.ajax({
		type: "POST",
		url: "php/save_html.php",
		cache: false,
		data: ({file: bVal.replace("xml","html"),
				data : build_html()}),
		dataType: "text",
		complete : function(data, status) {
			alert("html backup : " + bVal + "/" + status ) ;
	}});

}

/**
 * Display the dialog box associated with the selected element
 */
function showFilePanel( ) {

	$("#dialog_file").load("php/liste_file.php",
			function() { 
				$('#dialog_file').dialog('open');
			});
}
