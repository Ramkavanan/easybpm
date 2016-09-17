var MAX_COL = 4 ;
var nb_col = 0 ;
var rblColArray = new Array();
var lastCOL = "" ;

function rbl_col( _id ) { 

	var e_id    = _id ;
	var e_name   ; 

	var e_label  ;
	var e_layout ;
	var e_css_class  ;

	var e_width  ;
	var e_height ;

  this.saveForm = function() { 
		this.e_id = e_id  ;

		this.e_name 	=  $("#c_name").val()  ;

		this.e_width	=  $("#c_width").val()  ;
		this.e_height	=  $("#c_height").val()  ;
		
		this.e_layout =  $("#c_layout").val()  ;
		this.e_css_class  = $("#c_css_class").val() ;
  } 

  this.showForm = function() { 
  	
		$("#c_id").val(  e_id )  ;
		$("#c_name").val( this.e_name )  ;
		
		$("#c_width").val( this.e_width )  ;
		$("#c_height").val( this.e_height )  ;
		
		$("#c_css_class").val( this.e_css_class ) ;
		$("#c_layout").val( this.e_layout ) ;
  } 
	
  this.drawCol = function() { 
		var ma_col ;
		
		ma_col = $("#" + e_id) ;
		 
		if ( ma_col.length == 0 ) {
			alert("column null !" ) ;
		}	
		else {
			ma_col.attr('id', e_id)
	  			  .attr('name',this.e_name) ;

			ma_col.removeClass( ) ;
			ma_col.addClass(this.e_css_class) ;
			ma_col.addClass('elt_head');
			ma_col.addClass('selected');
			ma_col.parent().css("width", this.e_width) ;
			ma_col.parent().css("height", this.e_height) ;
		 
			ma_col.parent().removeClass("cLab1 cLab2") ;
			ma_col.parent().addClass( this.e_layout ) ;
		 
	  }
	  
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
	
	
	this.startXml = function( ) { 
		var str = "" ;
		
		str = "<column id='" + e_id +"'" ;
		
		if ( this.e_width  && this.e_width.length > 0  ) str += " width='" + this.e_width + "'" ;
		if ( this.e_height && this.e_height.length > 0 ) str += " height='" + this.e_height + "'"  ;
		if ( this.e_layout && this.e_layout.length > 0 ) str += " layout='" + this.e_layout + "'" ;
		if ( this.e_name && this.e_name.length > 0 )     str +=  " name='" + this.e_name + "'" ;
		if ( this.e_css_class && this.e_css_class.length > 0 ) str += " class='" + this.e_css_class + "'" ;
									
		str += ">\r\n" ;
		
		return str ;	
	}	
	this.endXml = function( ) { 

		var str = "" ;
		
		str = "</column>\r\n" ;
		
		return str ;	
	}
	this.startHtml = function( ) { 
		var str = "" ;
		
		str = "<ul id='" + e_id +"'" ;
		
		if ( this.e_name && this.e_name.length > 0 )     str +=  " name='" + this.e_name + "'" ;
		
		str += " style='" ;
		if ( this.e_width  && this.e_width.length > 0  ) str += " width: " + this.e_width + ";" ;
		if ( this.e_height && this.e_height.length > 0 ) str += " height: " + this.e_height + ";"  ;
		str += "'" ;
		
		str += " class='connectedSortable" ;
		if ( this.e_layout && this.e_layout.length > 0 ) str += " "  + this.e_layout  ;
		if ( this.e_css_class && this.e_css_class.length > 0 ) str += " " +this.e_css_class ;
		str += "'" ;
		
		str += ">" ;
		
		lastCOL = str.replace( "id='" +e_id +"'" ,"id='xIDx'") ;
		
		return str ;	
	}	
	this.endHtml = function( ) { 
		var str = "" ;
		
		str = "</ul>" ;
		
		return str ;
	}
}

/**
 * Display the dialog box associated with the selected element
 */
function showColPanel( _no ) {
    $("#generalProperties").load("/images/html/bpm/"+ LANG +"/dialog_col.html",
			function() { 
				rblColArray[ _no ].showForm() ;
				//$('#dialog_col').dialog('open');
			});
}

/**
 * Sauvegarde des proproetees du formulaire
 * Saving proproetees form
 */
function saveColPanel(_no) {
	
	if ( rblColArray[ _no ]  != null) {
		rblColArray[ _no ].saveForm() ;
		rblColArray[ _no ].drawCol() ;
	} else {
		alert ("Column " + _no + " null") ; 
	}

}

/**
 * Adding a column
 */
function addCol() {
	if (nb_col < MAX_COL) {
		$(".connectedSortable ul")
		 .each( function(){
					$(this).removeClass('p-' + nb_col + '-col') ;
					$(this).addClass('p-' + (nb_col+1) + '-col') ;
				});
				
		nb_col = nb_col + 1 ;
		var id_col = nb_col ;
		if ( $("#"   + IDFS).find("#sortable" + id_col).length != 0 ) {
			if ( $("#" + IDFS).find("#sortable1" ).length == 0 ) id_col = 1 ; else
			if ( $("#" + IDFS).find("#sortable2" ).length == 0 ) id_col = 2 ; else
			if ( $("#" + IDFS).find("#sortable3" ).length == 0 ) id_col = 3 ;
	  }
	  
	  var _id = "f_col_" + id_col ;
		$("#" + IDFS).append( "<ul id='sortable" + id_col + 
									 "' id_n='" + _id +
									 "' class='connectedSortable p-" + nb_col + 
									 "-col cLab1'><li onClick='selectAllFields(\""+id_col+"\");' id='" + _id +  
									 "' class='elt_head' name='Column"+id_col+"'><span><b>Column " + id_col +
									 "</b></span>"
									 +"<div class='button-container displayNone' style='background-color: rgb(245, 245, 245);'>" 
									 /*+"<img src='/images/easybpm/form/JFgear.png' class='form-propbutton' title='Properties' alt='Props'>" */
									 +"<img src='/images/easybpm/common/delete.png' class='index-cross' title='Delete' alt='X' onClick='deleteElement();'>"
									 + "</div></li>" ) ;
		resizeColWidth(nb_col);
		rblColArray[ _id ] = new rbl_col( _id );
		showColPanel( _id) ;
		initDrop();
	}else {
		$.msgBox({
			title:"Message",
		    content: "Maximum number of columns reaches (" + MAX_COL + ") !",
		});
  }
}

function resetColumnBgcolor(){
	for(var i=1;i<=4;i++){
		$("#sortable"+i).css("background","white");
	}
}

function selectAllFields(col){
	resetColumnBgcolor();
	$("#sortable"+col).css("background","rgb(245, 245, 245)");
}

/**
 * Deleting a column
 */
function supCol( _no ) {
	$("#" + _no).parent( ).find('li')
											  .each( function(){ supElt( $(this).attr('id') ) ;	});
											  
	$("#" + _no).parent( ).fadeOut(500, function() { $(this).remove(); });

	delete rblColArray[ _no ] ;
	
	/*$(".connectedSortable")
	 .each( function(){
				$(this).removeClass('p-' + nb_col + '-col') ;
				$(this).addClass('p-' + (nb_col-1) + '-col') ;
			});*/
			
	nb_col = nb_col - 1 ;
	resizeColWidth(nb_col);
	$("#propertiesDiv").hide();
	/*$("#fieldProperties").css('background-color','#fff');*/
}

function resizeColWidth(nb_col){
	if(nb_col == 1){
		$(".p-1-col").css("width","95%");
	}else if(nb_col == 2){
		$(".p-1-col").css("width","45%");
		$(".p-2-col").css("width","45%");
	}else if(nb_col == 3){
		$(".p-1-col").css("width","30%");
		$(".p-2-col").css("width","30%");
		$(".p-3-col").css("width","30%");
	}else if(nb_col == 4){
		$(".p-1-col").css("width","23%");
		$(".p-2-col").css("width","23%");
		$(".p-3-col").css("width","23%");
	}
}

function loadCol( elt, id_col , total_col , isXml) {
	var _id = $(elt).attr('id') ;
	nb_col = total_col;
	rblColArray[ _id ] = new rbl_col( _id );

	rblColArray[ _id ].e_name =  $(elt).attr('name') ; 
	rblColArray[ _id ].e_layout = $(elt).attr('layout') ;
	if(isXml){
		rblColArray[ _id ].e_css_class = $(elt).attr('class') ;
	}
	

	rblColArray[ _id ].e_width = $(elt).attr('width') ;
	rblColArray[ _id ].e_height = $(elt).attr('height');
				
	$("#" + IDFS).append( "<ul id='sortable" + id_col + 
									 "' id_n='" + _id +
									 "' class='connectedSortable p-"+total_col+"-col " + rblColArray[ _id ].e_layout +
									 "'><li onClick='selectAllFields(\""+id_col+"\");' id='" + _id +  
									 "' class='elt_head'><span><b>Column " + id_col +
									 "</b></span>" +
									 "<div class='button-container displayNone' style='background-color: rgb(245, 245, 245);'>" +
									 "<img src='/images/easybpm/common/delete.png' class='index-cross' title='Delete' alt='X' onClick='deleteElement();'></li>" ) ;
				
				
	rblColArray[ _id ].drawCol() ;
}
