var rblEltArray = new Array();
var nbelt  = 0 ;

function rbl_elt( _id, _type, _sub_type ) { 
	var e_id    = _id ;
	var e_type  = _type.toLowerCase();
	var e_sub_type = _sub_type.toLowerCase();
	var e_name  = _id ;
	
	var e_label = "" ;
	var e_size  = "10" ;

	var e_typeint  = "S" ;
	var e_val_liste = "" ;

	var e_mask     = "" ;
	var e_freemask = "" ;
	var e_regexp   = "" ;
	
	var e_maxlength  = "" ;
	var e_accesskey  = "" ;
	
	var e_help_text  = "" ;
	var e_val_defaut = "" ;
	
	var e_memorytable = "" ;
	var e_group       = "" ;
	var e_css_class   = "" ;
	
	var e_required = false ;
	var e_readonly = false ;
	var e_autoskip = true ;
	
	var e_onClick = "";
	var e_onBlur = "";
	var e_onChange = "";
	var e_onFocus = "";
	var e_btnStyle = "";
	var e_btnId = "";
	
	this.getType = function() { 
		var s  = e_sub_type ; 
		return s ;
	}
 
    this.saveForm = function() { 
		this.e_sub_type		= e_sub_type  ;
		this.e_type = e_type;
		this.e_id		= e_id  ;

		this.e_name 	=  $("#name").val().replace('\'',"`") ;
		this.e_label 	=  $("#title").val().replace('\'',"`") ;
		this.e_size		=  $("#size").val()  ;
		this.e_typeint	=  $("#type_interne").val()  ;
		
		this.e_mask		 = $("#mask").val();
		this.e_freemask	 = $("#freemask").val();
		this.e_regexp	 = $("#regexp").val();
		this.e_maxlength = $("#maxlength").val();
		this.e_accesskey = $("#accesskey").val()  ;
		this.e_val_liste = $("#val_liste").val()  ;
		
		this.e_val_defaut = $("#val_defaut").val();
		this.e_help_text  = $("#help_text").val() ;
		this.e_group	  = $("#group").val() ;
		this.e_css_class  = $("#css_class").val() ;
		
		this.e_required = $("#required").attr('checked') ;
		this.e_autoskip = $("#autoskip").attr('checked') ;
		this.e_readonly = $("#readonly").attr('checked') ;
		
		this.e_onClick = $("#onClick").val();
		this.e_onChange = $("#onChange").val();
		this.e_onBlur = $("#onBlur").val();
		this.e_onFocus = $("#onFocus").val();
		this.e_btnStyle = $("#btnStyle").val();
		this.e_btnId = $("#btnId").val();
    } 

    this.showForm = function() { 
		$("#type_html").val( this.e_sub_type )  ;

		$("#name").val( this.e_name )  ;
		$("#title").val( this.e_label ) ;
		$("#size").val( this.e_size )  ;
		$("#type_interne").val( this.e_typeint )  ;
		
		$("#mask").val(this.e_mask);
		$("#freemask").val( this.e_freemask ) ;
		$("#regexp").val( this.e_regexp );
		$("#maxlength").val( this.e_maxlength );
		$("#accesskey").val( this.e_accesskey )  ;

		$("#val_liste").val( this.e_val_liste )  ;
		
		$("#val_defaut").val( this.e_val_defaut );
		$("#help_text").val( this.e_help_text ) ;
		$("#group").val( this.e_group ) ;
		$("#css_class").val( this.e_css_class ) ;
		$("#required").attr('checked', this.e_required) ;
		$("#autoskip").attr('checked', this.e_autoskip) ;
		$("#readonly").attr('checked', this.e_readonly) ;
		
		$("#onClick").val( this.e_onClick);
		$("#onChange").val( this.e_onChange);
		$("#onBlur").val( this.e_onBlur);
		$("#onFocus").val( this.e_onFocus);
		$("#btnStyle").val(this.e_btnStyle);
		$("#btnId").val(this.e_btnId);
    } 
	
    this.drawElement = function() { 
		var str = "" ;
		str = this.drawElement0(false) ;
		return str ;
	}
    this.drawElement0 = function(isHt) { 
		var str = "" ;
		str = str + this.drawBeforeElt( isHt ) ;
		switch ( e_sub_type.toUpperCase() ) {
			case 'WIZARD' :
				str =  this.drawWizard(isHt) ;
			break ;
			case 'IMAGE' :
				str =  this.drawImage( )  ;
			break ;
			case 'LABEL' :
				str =  this.drawText( ) + "&nbsp;" ;
			break ;
/*			case 'HTML_LABEL' :
				str =  this.drawText( ) + "&nbsp;" ;
			break ;*/
			case 'PASSWORD' :
			case 'FILE' :
			case 'EMAIL' :
			case 'TEXT' :
				str = str + this.drawInputText() ;
			break ;
			/*added by megala
			case 'FULLNAMETEXT' :
				str = str + this.drawFullNameText();
			break ;
			case 'ADDRESS' :
				str = str + this.drawAddressText();
			break ;*/
			case 'BUTTON' :
				str = this.drawButton(isHt) + "&nbsp;" ;
			break ;
			case 'SUBMITBUTTON' :
				str = this.drawSubmitButton(isHt) + "&nbsp;" ;
			break ;
			case 'USERDEFINEBUTTON' :
				str = this.drawUserDefinedButton(isHt) + "&nbsp;" ;
			break ;
			case 'TEXTAREA' :
				str = str + this.drawTextarea() ;
			break ;
			case 'DATE' :
				str = str + this.drawDate(isHt) ;
			break ;
			case 'RADIO' :
			case 'CHECKBOX' :
				str = str + this.drawRadio() ;
			break ;
			case 'SELECT' :
			case 'LISTE' :
				str = str + this.drawListe() ;
			break ;
			default :
		}
		str = str + this.drawAfterElt( isHt ) ;

		return str ;
	}
	
	this.drawBeforeElt = function(isHt) { 
		var str = "" ;

		str = this.drawLabel(isHt) ;
	
		return str ;
	}	
	this.drawAfterElt = function(isHt) { 
		var str = "" ;
		var reg=new RegExp('\'', "g");
		if ( this.e_help_text && this.e_help_text.length > 0 && this.e_sub_type != "wizard" && this.e_sub_type != "label" && this.e_sub_type != "image" && this.e_sub_type != "button") {
			str = "<img class='imghelp'  src='/images/easybpm/form/rbl/help-browser.png' border='0' title='" + this.e_help_text.replace(reg,"`") +"' alt='" + this.e_help_text.replace(reg,"`")+ "' width='16' height='16' hspace='10'>" ;
		}
		if(isHt == false){
			str += "<div class='button-container displayNone' style='background-color: rgb(245, 245, 245);'>" +
			       /*"<img src='/images/easybpm/form/JFgear.png' class='form-propbutton' title='Properties' alt='Props'>" + */
			       "<img src='/images/easybpm/common/delete.png' class='index-cross' title='Delete' alt='X' onClick='deleteElement();'>" + "</div>";
		}
		
		return str ;
	}
	
	this.drawText = function( ) { 
		var str = "" ;
		
		str += "<label" + this.drawClass() + ">" 
										+ this.drawAide()
										+ " value='"
										+ this.drawValue() 
										+ "'" 
										+ "</label>" ;
		
		return str ;	
	}
	
	this.drawButton = function(isHt) { 
		var str = "" ;
		
		str = "<input type='button' class='btn btn-primary normalButton'"
						+ this.drawName() 
						+ this.drawSize() 
						+ this.drawClass() 
						+ this.drawOnClick()
						+ " value='"
						+ this.drawValue() 
						+ "'" ;
						
		if ( isHt == false) str += " onclick='return false;'" ;
		
		str += "/>" ;
		return str ;	
	}
	
	this.drawSubmitButton = function(isHt) { 
		var str = "" ;
		
		str = "<input type='submit' class='btn btn-primary normalButton'"
						+ this.drawName() 
						+ this.drawSize() 
						+ this.drawClass() 
						+ this.drawOnClick()
						+ " value='"
						+ this.drawValue() 
						+ "'" ;
						
		if ( isHt == false) str += " onclick='return false;'" ;
		
		str += "/>" ;
		return str ;	
	}
	
	this.drawUserDefinedButton = function(isHt) { 
		var str = "" ;
		str = "<input type='button' class='btn btn-primary normalButton'"
						+ this.drawUserDefinedBtnId()
						+ this.drawBtnStyle()
						+ this.drawName() 
						+ this.drawSize() 
						+ this.drawClass()
						+ this.drawOnClick()
						+ "value='"
						+ this.drawValue() 
						+ "'" ;
						
		if ( isHt == false) str += " onclick='return false;'" ;
		
		str += "/>" ;
		return str ;	
	}

	this.drawImage = function() {
		var str = "" ;
		
		str = "<img border='0' src='" + this.e_label + "' " 
						+ this.drawClass() 
						+ "/>" ;
		return str ;	
	}
	
	this.drawInputText = function() { 
		var str = "" ;
		str = "<input type='" + e_sub_type + "' "
							    + this.drawName() 
								+ this.drawAutoskip() 
								+ this.drawReadonly() 
								+ this.drawRequired() 
								+ this.drawSize() 
								+ this.drawMaxlength() 
								+ this.drawMask() 
								+ this.drawClass()
								+ this.drawOnClick()
								+ this.drawOnBlur()
								+ this.drawOnFocus()
								+ " value='"
								+ this.drawValue() 
								+ "'" 
								+ "/>" ;
		return str ;	
	}
	/*added by megala
	this.drawFullNameText = function() {
		var str = "";
		e_type = "text";
		str = "<span class='form-sub-label-container'><input type='"   + e_type + "' "
		      + this.drawName() 
		      + this.drawAutoskip() 
		      + this.drawReadonly() 
		      + this.drawRequired() 
		      + this.drawSize() 
		      + this.drawMaxlength() 
		      + this.drawMask() 
		      + this.drawClass() 
		      + "/> <label id='sublabel_first' class='form-sub-label'>First Name</label></span>";
		str += "<span class='form-sub-label-container'><input type='"   + e_type + "' "
		       + this.drawName() 
		       + this.drawAutoskip() 
		       + this.drawReadonly() 
		       + this.drawRequired() 
		       + this.drawSize() 
		       + this.drawMaxlength() 
		       + this.drawMask() 
		       + this.drawClass() 
		       + "/><label id='sublabel_first' class='form-sub-label'>Last Name</label></span>" ;
		return str ;
	}
	// to add the address fields
	this.drawAddressText = function() {
		var str = "";
		e_type = "text";
		str = "<table cellspacing='0' cellpadding='0' border='0' class='form-address-table'>\r\n"
		       + "<tr> <td colspan='2'> <span class='form-sub-label-container'><input type='"   + e_type + "' "
		       + this.drawName() 
		       + this.drawAutoskip() 
		       + this.drawReadonly() 
		       + this.drawRequired() 
		       + this.drawSize() 
		       + this.drawMaxlength() 
		       + this.drawMask() 
		       + " class='form-address-line'/><label id='sublabel_addr_line1' class='form-sub-label'>Street Address</label></span></td></tr>\r\n"
		       + "<tr> <td colspan='2'> <span class='form-sub-label-container'><input type='"   + e_type + "' " 
		       + this.drawName() 
		       + this.drawAutoskip() 
		       + this.drawReadonly() 
		       + this.drawRequired() 
		       + this.drawSize() 
		       + this.drawMaxlength() 
		       + this.drawMask() 
		       + " class='form-address-line'/><label id='sublabel_addr_line1' class='form-sub-label'>Street Address Line 2</label></span></td></tr>\r\n"
		       + "<tr> <td width='50%'> <span class='form-sub-label-container'><input type='"   + e_type + "' " 
		       + this.drawName() 
		       + this.drawAutoskip() 
		       + this.drawReadonly() 
		       + this.drawRequired() 
		       + this.drawSize() 
		       + this.drawMaxlength() 
		       + this.drawMask() 
                       + " class='form-address-city'/><label id='sublabel_city' class='form-sub-label'>City</label></span></td>"
		       + "<td width='50%'> <span class='form-sub-label-container'><input type='"   + e_type + "' " 
		       + this.drawName() 
		       + this.drawAutoskip() 
		       + this.drawReadonly() 
		       + this.drawRequired() 
		       + this.drawSize() 
		       + this.drawMaxlength()
		       + this.drawMask()
                       + " class='form-address-state'/><label id='sublabel_state' class='form-sub-label'>State</label></span></td></tr>\r\n"
		       + "<tr> <td width='50%'> <span class='form-sub-label-container'><input type='"   + e_type + "' " 
		       + this.drawName() 
		       + this.drawAutoskip() 
		       + this.drawReadonly()
		       + this.drawRequired()
		       + this.drawMaxlength() 
		       + this.drawMask()
                       + " class='form-address-postal'size='9'/><label id='sublabel_city' class='form-sub-label'>Postal / Zip Code</label></span></td></tr>";
		return str ;
	}*/
	this.drawDate = function(isHt) { 
		var str = "" ;
		
		str = "<input type='date' " + this.drawName() 
									+ this.drawAutoskip() 
									+ this.drawReadonly() 
									+ this.drawRequired() 
									+ this.drawSize() 
									+ this.drawOnClick()
									+ this.drawOnBlur()
                                    + this.drawOnChange()
									+ this.drawMaxlength() 
									+ this.drawMask() 
									+ this.drawClass() 
									+ "/>" ;
		if (isHt) {
			var z = this.e_mask ;
			if ( !(z && z.length > 0) ) z = this.e_freemask ;
			str += "<img src='/images/easybpm/form/rbl/_cal.gif' border='0' hspace='2'/>" ;		
		}
		
		return str ;	
	}

	this.drawWizard = function(isHt) { 
		var str = "" ;
		var ss ;

		if (isHt && e_sub_type == "wizard") {
			str += "</li></ul><div class='btWiz'>" ;
		}
		
		/* button previous */
		if ( this.e_autoskip == true  ) {
			if ( this.e_val_defaut && this.e_val_defaut.length > 0 ) {
				ss = this.e_val_defaut ;
			} else {
				ss = "Prev" ;
			}
			str += "<input type='button' class='btP' value='" + ss + "' onclick=\"javascript:rbl_onoff('" + PRVFS  +"');\">" ;
		}

		PRVFS = CURFS ;
		CURFS =	'x' + this.e_id ;

		/* button Next */
		if ( this.e_readonly == true ) {
			if ( this.e_val_liste && this.e_val_liste.length > 0 ) {
				ss = this.e_val_liste ;
			} else {
				ss = "Next" ;
			}
			str += "<input type='button' class='btN' value='" + ss + "' onclick=\"javascript:rbl_onoff('" + CURFS  +"');\">" ;
		}
		if (isHt) {
			str += "</div>" ;
			if ( this.e_readonly == true ) {
				str += "</fieldset><fieldset id='x" + this.e_id +"' style='display:none;'>" ;
				/*if (this.e_name && this.e_name.length > 0) {
						str += "<legend><b class='fontSize13'>" + this.e_name + "</b></legend>" ;
				}*/
				str += lastCOL.replace('xIDx','c'+this.e_id) ;
			}
		}
		
		return str ;	
	}
	
	this.drawListe = function() { 
		var str = "" ;
		var elt = this.e_val_liste.split(";") ;
		var grpOpen = false ;
		
		str = "<select "	+ this.drawName()	
							+ this.drawReadonly() 
							+ this.drawRequired() 
							+ this.drawSize()
                            + this.drawOnClick()
                            + this.drawOnBlur()
                            + this.drawOnChange()
							+ this.drawClass() 
							+ ">" ;
							
		for ( k = 0 ; k < elt.length ; k++) {
			var val = elt[k].split("=") ;
			if (val.length > 1) {
				if (val[0] == "$G$") {
					if (grpOpen) {
						str += "</optgroup>" ;
				  }
					str += "<optgroup label='" + val[1] +"'>" ;
					grpOpen = true ;
			  } else {
					str += "<option value='" + val[0] +"' > " + val[1] + "</option>";
				}
			}
		}
		if (grpOpen) {
			str += "</optgroup>" ;
			grpOpen = false ;
	  }
		
		str = str + "</select>" ;
		
		return str ;
	}	
	
	this.drawTextarea = function() { 
		var str = "" ;
		
		str += "<textarea "	+ this.drawName()	
							+ this.drawReadonly() 
							+ this.drawRequired() 
							+ this.drawSizeArea()
							+ this.drawOnClick()
                            + this.drawOnBlur()
                            + this.drawOnFocus()
							+ this.drawClass()
							+ " value='"
							+ this.drawValue() 
							+ "'"
							+ "></textarea>" ;
							
		return str ;
	}	
	
	this.drawRadio = function() { 
		var str = "" ;
		var elt = this.e_val_liste.split(";") ;
		for ( k = 0 ; k < elt.length ; k++) {
			var val = elt[k].split("=") ;
			if (val.length > 1) {
				str = str + "<input type='" + e_sub_type + "' "
							    + this.drawName()	
							    + this.drawReadonly() 
							    + this.drawRequired()
							    + this.drawOnChange()
							    + this.drawOnClick()
							    + this.drawOnBlur()
							    + this.drawClass() 
							    + " text='" + val[1] +"' "
							    + "value='" + val[0] +"' /> " + val[1] ;
				if (this.e_autoskip) str += "<br/>";
			}
		}
		
		return str ;
	}	
	
    this.drawName = function() { 
		var str = "" ;

		str = " id='" + e_id + "'" ;
		
		if (this.e_name && this.e_name.length > 0 )
		{
			str = str +  " name='" + this.e_name + "' " ;
		}
		
		return str ;
	}

    this.drawValue = function() { 
		var str = "" ;

		if (this.e_val_defaut && this.e_val_defaut.length > 0 )
		{
			str = str +  this.e_val_defaut ;
		}
		
		return str ;
	}
	
	this.drawMask = function() { 
		var str = "" ;

		if (this.e_mask && this.e_mask.length > 0 )
		{
			str = " mask='" + this.e_mask + "' " ;
		}
		
		if (this.e_freemask && this.e_freemask.length > 0 )
		{
			str = " freemask='" + this.e_freemask + "' " ;
		}

		if (this.e_regexp && this.e_regexp.length > 0 )
		{
			str = " regexp='" + this.e_regexp + "' " ;
		}
		
		return str ;
	}

  this.drawSizeArea = function() { 
		var str = "" ;
		if (this.e_size && this.e_size.length > 0 )
		{
			str = " rows='" + this.e_size + "' " ;
		}
		if (this.e_maxlength && this.e_maxlength.length > 0 )
		{
			str += " cols='" + this.e_maxlength + "' " ;
		}
		return str ;
	}
	
    this.drawSize = function() { 
		var str = "" ;

		if (this.e_size && this.e_size.length > 0 )
		{
			str = " size='" + this.e_size + "' " ;
		}
		
		return str ;
	}

  this.drawValListe = function() { 
		var str = "" ;

		if (this.e_val_liste && this.e_val_liste.length > 0 )
		{
			/*str += " val_liste='" ;*/
			
			var elt = this.e_val_liste.split(";") ;
		
			for ( k = 0 ; k < elt.length ; k++) {
				var val = elt[k].split("=") ;
				if (val.length > 1) {
					//str += val[0] + "=" + val[1] + ";" ;
					str += "<value name= '"+ val[0] +"' id= '"+ val[0] +"' value= '"+val[1]+"'/>" + "\r\n";
				}
		  }
		  /*str += "'" ;*/
		}
		
		return str ;
	}
	
    this.drawMaxlength = function() { 
		var str = "" ;

		if (this.e_maxlength && this.e_maxlength.length > 0 )
		{
			str = " maxlength='" + this.e_maxlength + "' " ;
		}
		
		return str ;
	}
	 
  this.drawLabel = function(isHt) { 
		var str = "" ;
		
		if (isHt == false) {
			str += "<img src='/images/easybpm/form/arrow.png' alt='move' width='20' height='20' class='handle' />"  ;
		}
		
		str += "<label for='" + e_id +"'" + this.drawAccesskey() + ">" ;
		if (this.e_accesskey && this.e_accesskey.length > 0) {
			str += this.e_label.replace( this.e_accesskey, "<u>" + this.e_accesskey + "</u>" ) ;
		} else {
			str += this.e_label  ;
		}
		if ( this.e_required == "checked" || this.e_required == true) {
			str += "<font size='2' face='Verdana, Arial, Helvetica' color='#FF0066' style='padding-right: 8px;'>*</font>" ;
		}else{
			str += "<img class='imgstar' src='/images/easybpm/form/rbl/star_transparent.png' border='0' hspace='0'>" ;
		}
		str += "</label>" ;
		/*if (isHt == false) {
		str += "<div class='button-container displayNone' style='background-color: rgb(245, 245, 245);'>" +
		       "<img src='img/JFgear.png' class='form-propbutton' title='Properties' alt='Props'>" + 
		       "<img src='img/blank.gif' class='form-delbutton index-cross' title='Delete' alt='X' onClick='deleteElement();'>" + "</div>";
		}*/
		if (isHt) str += "\r\n  " ;
		
		return str ;
	}
	
	this.drawAccesskey = function() { 
		var str = "" ;

		if (this.e_accesskey && this.e_accesskey.length > 0 )
		{
			str = " accesskey='" + this.e_accesskey + "' " ;
		}
		
		return str ;
	}

    this.drawClass = function() { 
		var str = "" ;

		if ( this.e_css_class && this.e_css_class.length > 0 ) 
		{
			str = " class='" + this.e_css_class + "' " ;
		}
			
		return str ;
	}
	
    this.drawRequired = function() { 
		var str = "" ;
		if ( this.e_required == "checked" || this.e_required == true) 
		{
			str = " required='true' " ;
		}
			
		return str ;
	}	
	
    this.drawReadonly = function() { 
		var str = "" ;
		if ( this.e_readonly == "checked" || this.e_readonly == true) 
		{
			str = " readonly='true' " ;
		}
			
		return str ;
	}	

	this.drawAutoskip = function() { 
		var str = "" ;

		if ( this.e_autoskip == true ) 
		{
			str = " autoskip='O' " ;
		}
			
		return str ;
	}

	this.drawAide = function(isHtml) { 
		var str = "" ;

		if ( this.e_help_text && this.e_help_text.length > 0 ) 
		{
			if (isHtml) {
				str = " a faire " ;
			} 
			else {
				str = str + this.e_help_text.replace(/^\s+/g,'').replace(/\s+$/g,'')  ;
			}
		}
			
		return str ;
	}
	
	this.drawOnClick = function(){
		var str = "";
		if ( this.e_onClick && this.e_onClick.length > 0 ){
			str = " onclick= '"+this.e_onClick+"'";
		}
		return str;
	}
	
	this.drawBtnStyle = function(){
		var str = "";
		if ( this.e_btnStyle && this.e_btnStyle.length > 0 ){
			str = " style= '"+this.e_btnStyle+"'";
		}
		return str;
	}
	
	this.drawOnFocus = function(){
               var str = "";
               if ( this.e_onFocus && this.e_onFocus.length > 0 ){
                       str = " onfocus= '"+this.e_onFocus+"'";
               }
               return str;
       }
	
	this.drawOnBlur = function(){
		var str = "";
		if ( this.e_onBlur && this.e_onBlur.length > 0 ){
			str = " onblur= '"+this.e_onBlur+"'";
		}
		return str;
	}
	
	this.drawOnChange = function(){
		var str = "";
		if ( this.e_onChange && this.e_onChange.length > 0 ){
			str = " onchange= '"+this.e_onChange+"'";
		}
		return str;
	}
	
	this.drawUserDefinedBtnId = function(){
		var str = "";
		if ( this.e_btnId && this.e_btnId.length > 0 ){
			str = " btnId= '"+this.e_btnId+"'";
		}
		return str;
	}

	this.drawXml = function(_col, _ligne) { 
		var str = "" ;
		/*str = "<element type_html='" + this.e_sub_type +"'" 
								  + " idco='" + _col + "' idli='" + _ligne + "' " ;*/
		str = "<formProperty type ='" + this.e_type +"' subtype= '" + e_sub_type + "'" 
		  + " idco='" + _col + "' idli='" + _ligne + "' " ;
					
		if (this.e_label && this.e_label.length > 0)
			str += " label='" + this.e_label + "' " ;
			
		str += this.drawName()
				 + this.drawUserDefinedBtnId()
				 + this.drawBtnStyle()
				 + this.drawAutoskip()
				 + this.drawReadonly()
				 + this.drawRequired()
				 + this.drawSize()
				 + this.drawMaxlength()
				 + this.drawMask()
				 + this.drawClass()
				 + this.drawAccesskey()
				 + this.drawOnClick()
				 + this.drawOnChange()
				 + this.drawOnFocus()
				 + this.drawOnBlur()
				 /*+ this.drawValListe() */
				 + " val_defaut='"  
				 + this.drawValue() + "'" 
				 + ">"
				 + this.drawAide(false) 
				 /*+ "</element>";*/
				/* + " val_defaut='"  
				 + this.drawValue() + "'" 
				 + ">"
				 + this.drawAide(false);*/
				 
		str +=  "\r\n" +this.drawValListe()
				+ "</formProperty>";
		
		return str ;	
	}
	
} 

/**
 * change all values in input form element
 */
function changeInput( _no ) {

	var typeToAdd = rblEltArray[ _no ].getType() ;
	var eltToAdd  = rblEltArray[ _no ].drawElement() ;
	$("#f_" + _no).html( eltToAdd );
	if (typeToAdd == 'date') {
		$("#f_" + _no).find("input").datepicker({
			showOn: 'button', 
			buttonImage: '/images/easybpm/form/rbl/_cal.gif', 
			buttonImageOnly: true,
			dateFormat:  rblEltArray[ _no ].e_mask.replace(/yy/g,"y") ,
			showMonthAfterYear: false
		});
	}
	
}

/**
 * Deleting an element
 */
function supElt( _no ) {
	
		if (_no && _no.length > 2) {
			if (_no.charAt(0) == 'f' && _no.charAt(1) == '_' ) {
				_no = _no.substr(2) ;
			}
		}

		$("#f_" + _no).fadeOut(500, function() { $(this).remove(); });
		delete rblEltArray[ _no ] ;
		callBuildXml();
}

/**
 * Adding an element
 */
function addNewElement( ui, dest ) {
	$("#propertiesDiv").show();
	$("#propertyTab-content").show();
	var typeToAdd = ui.draggable.attr('id');
	var type = ui.draggable.attr('type');
	var labelName = typeToAdd.toLowerCase() ;
	//added by megala
	if (labelName == "checkbox") labelName = "Check Box";
	if (labelName == "password") labelName = "Password";
	if (labelName == "file") labelName = "File Upload";
	if (labelName == "text") labelName = "Text Box";
	if (labelName == "select") labelName = "Select Box";
	if (labelName == "textarea") labelName = "Text Area";
	if (labelName == "radio") labelName = "Radio Button";
	if (labelName == "date") labelName = "Date Picker";
	if (labelName == "email") labelName = "E-mail";
	var form_name = rblFormArray[ IDFORM ].e_name;
	nbelt++ ;
	var _id = "nb_" + nbelt ;
	var eltToAdd =	"" ;
	switch (typeToAdd) {
		case 'Menu':
			eltToAdd = "<select ><option value='sss'>Enter the options here</option></select>";
			break ;
		case 'SubmitButton':
			eltToAdd = "<input type=button size=15 value=''/>";
			break ;
		default:
			eltToAdd = "<label>"+labelName+"</label><input type=text size=15 value=''/>";
			break;
	}
	$(dest).append("<li onClick='resetColumnBgcolor();' id='f_" + _id +"' class='elt' style='margin-top:20px;'>" + 
				      		"<img src='/images/easybpm/form/arrow.png' alt='move' width='16' height='16' class='handle' />" + eltToAdd);
	rblEltArray[ _id ] = new rbl_elt( _id, type, typeToAdd );
	showControlPanel("f_" + _id, "create");
}
 
function loadElement( elt, ncol, nbel, listValue) {
	var _id = "nb_" + nbel ;
	rblEltArray[ _id ] = new rbl_elt( _id, $(elt).attr('type'), $(elt).attr('subtype'));
	rblEltArray[ _id ].e_id  = _id ;
	rblEltArray[ _id ].e_sub_type  = ( ( $(elt).attr('subtype') ) ? $(elt).attr('subtype').toLowerCase() : "text" ) ;
	rblEltArray[ _id ].e_type  = ( ( $(elt).attr('type') ) ? $(elt).attr('type').toLowerCase() : "string" ) ;
	rblEltArray[ _id ].e_name 	=  ( ( $(elt).attr('name') ) ? $(elt).attr('name') : _id ) ;
	rblEltArray[ _id ].e_label 	=  ( ( $(elt).attr('label') ) ? $(elt).attr('label') : "" ) ;
	rblEltArray[ _id ].e_size	=  ( ( $(elt).attr('size') ) ? $(elt).attr('size') : "" ) ;

	rblEltArray[ _id ].e_typeint =  ( ( $(elt).attr('type_interne') ) ? $(elt).attr('type_interne') : "" ) ; 
		
	rblEltArray[ _id ].e_mask	 =   ( ( $(elt).attr('mask') ) ? $(elt).attr('mask') : "" ) ;  
	rblEltArray[ _id ].e_freemask =  ( ( $(elt).attr('freemask') ) ? $(elt).attr('freemask') : "" ) ;  
	rblEltArray[ _id ].e_regexp	  =  ( ( $(elt).attr('regexp') ) ? $(elt).attr('regexp') : "" ) ;  
	rblEltArray[ _id ].e_maxlength = ( ( $(elt).attr('maxlength') ) ? $(elt).attr('maxlength') : ( ( $(elt).attr('max_length') ) ? $(elt).attr('max_length') : "" ) ) ;  
	rblEltArray[ _id ].e_accesskey = ("") ;
	rblEltArray[ _id ].e_val_liste = listValue;
	
	rblEltArray[ _id ].e_val_defaut =  ( ( $(elt).attr('val_defaut') ) ? $(elt).attr('val_defaut') : "" ) ;  
	rblEltArray[ _id ].e_css_class = ( ( $(elt).attr('class') ) ? $(elt).attr('class') : "" ) ; 
	rblEltArray[ _id ].e_onClick = ( ( $(elt).attr('onclick') ) ? $(elt).attr('onclick') : "" ) ; 
	rblEltArray[ _id ].e_onBlur = ( ( $(elt).attr('onblur') ) ? $(elt).attr('onblur') : "" ) ;
	rblEltArray[ _id ].e_onFocus = ( ( $(elt).attr('onfocus') ) ? $(elt).attr('onfocus') : "" ) ;
	rblEltArray[ _id ].e_onChange = ( ( $(elt).attr('onchange') ) ? $(elt).attr('onchange') : "" ) ; 
	rblEltArray[ _id ].e_help_text = $.trim($(elt).text());
	
	rblEltArray[ _id ].e_btnStyle = ( ( $(elt).attr('style') ) ? $(elt).attr('style') : "" ) ; 
	rblEltArray[ _id ].e_btnId = ( ( $(elt).attr('btnId') ) ? $(elt).attr('btnId') : "" ) ; 
	 
	var zz = $(elt).attr('autoskip') ;
	rblEltArray[ _id ].e_autoskip = ( zz == 'O' || zz == 'Y' ) ;

	zz = $(elt).attr('required') ;
	zz = ( ( $(elt).attr('required') ) ? $(elt).attr('required') : $(elt).attr('obligatoire') ) ;  
	rblEltArray[ _id ].e_required = ( zz == 'O' || zz == 'true' ) ;

	zz = $(elt).attr('readonly') ;
	rblEltArray[ _id ].e_readonly = ( zz == 'O' || zz == 'true' ) ;

	$('#sortable' + ncol).append("<li onClick='resetColumnBgcolor();' id='f_" + _id +"' class='elt'>" + 
						   "<img src='/images/easybpm/form/arrow.png' alt='move' width='16' height='16' class='handle' />" ) ;
	changeInput( _id ) ;
}

function loadHTMLElement( elt, ncol, nbel, listValue,htmlTagType) {
	var _id = "nb_" + nbel ;
	var _type = "";
	var el_sub_type=$(elt).attr('type');
	if(htmlTagType=='input'){
		if(el_sub_type=="submit"){
			_sub_type="submitbutton";
		}else if(el_sub_type=="text"){
			var mask=$(elt).attr('mask');
			if(mask!=null){
				_sub_type="date";
			}else{
				_sub_type=el_sub_type;
			}
		}else{
			_sub_type=el_sub_type;
		}
	}else if(htmlTagType=='option'){
		_sub_type="liste";
	}else{
		_sub_type=htmlTagType;
	}
	
	rblEltArray[ _id ] = new rbl_elt( _id, _type, _sub_type );
	rblEltArray[ _id ].e_id  = _id ;
	if(el_sub_type=="radio" || el_sub_type=="checkbox"){
		rblEltArray[ _id ].e_type  = "enum";
	}else{
		rblEltArray[ _id ].e_type  = ( ( $(elt).attr('type') ) ? $(elt).attr('type') : "string" ) ;
	}
	rblEltArray[ _id ].e_sub_type = _sub_type;
	rblEltArray[ _id ].e_name 	=  ( ( $(elt).attr('name') ) ? $(elt).attr('name') : _id ) ;
	rblEltArray[ _id ].e_label 	=  ( ( $(elt).attr('label') ) ? $(elt).attr('label') : "" ) ;
	rblEltArray[ _id ].e_size	=  ( ( $(elt).attr('rows') ) ? $(elt).attr('rows') : "" ) ;

	rblEltArray[ _id ].e_typeint =  ( ( $(elt).attr('type_interne') ) ? $(elt).attr('type_interne') : "" ) ; 
		
	rblEltArray[ _id ].e_mask	 =   ( ( $(elt).attr('mask') ) ? $(elt).attr('mask') : "" ) ;  
	rblEltArray[ _id ].e_freemask =  ( ( $(elt).attr('freemask') ) ? $(elt).attr('freemask') : "" ) ;  

	rblEltArray[ _id ].e_regexp       =  ( ( $(elt).attr('regexp') ) ? $(elt).attr('regexp') : "" ) ;
	rblEltArray[ _id ].e_onClick = ( ( $(elt).attr('onclick') ) ? $(elt).attr('onclick') : "" ) ; 
	rblEltArray[ _id ].e_onBlur = ( ( $(elt).attr('onblur') ) ? $(elt).attr('onblur') : "" ) ; 
	rblEltArray[ _id ].e_onFocus = ( ( $(elt).attr('onfocus') ) ? $(elt).attr('onfocus') : "" ) ; 
	rblEltArray[ _id ].e_onChange = ( ( $(elt).attr('onchange') ) ? $(elt).attr('onchange') : "" ) ; 
	rblEltArray[ _id ].e_maxlength = ( ( $(elt).attr('cols') ) ? $(elt).attr('cols') : "" ) ;  
	rblEltArray[ _id ].e_accesskey = ("") ;  
	rblEltArray[ _id ].e_val_liste = listValue;
	
	rblEltArray[ _id ].e_btnStyle = ( ( $(elt).attr('style') ) ? $(elt).attr('style') : "" ) ; 
	rblEltArray[ _id ].e_btnId = ( ( $(elt).attr('btnId') ) ? $(elt).attr('btnId') : "" ) ; 

	//rblEltArray[ _id ].e_val_defaut =( ( $(elt).text() ) ? $(elt).text() : "" ) ;
	rblEltArray[ _id ].e_val_defaut =  ( ( $(elt).attr('value') ) ? $(elt).attr('value') : "" ) ;  
	rblEltArray[ _id ].e_css_class = ( ( $(elt).attr('class') ) ? $(elt).attr('class') : "" ) ;
	rblEltArray[ _id ].e_help_text = $.trim($(elt).attr('title'));
	 
	var zz = $(elt).attr('autoskip') ;
	rblEltArray[ _id ].e_autoskip = ( zz == 'O' || zz == 'Y' ) ;

	zz = $(elt).attr('required') ;
	if(zz == 'required'){
		rblEltArray[ _id ].e_required = 'checked';
	}
	/*	zz = ( ( $(elt).attr('required') ) ? $(elt).attr('required') : $(elt).attr('obligatoire') ) ;  
	rblEltArray[ _id ].e_required = ( zz == 'O' || zz == 'true' ) ;*/

	zz = $(elt).attr('readonly') ;
	/*	rblEltArray[ _id ].e_readonly = ( zz == 'O' || zz == 'true' ) ;*/
	if(zz == 'readonly'){
		rblEltArray[ _id ].e_readonly = 'checked';
	}

	$('#sortable' + ncol).append("<li onClick='resetColumnBgcolor();' id='f_" + _id +"' class='elt'>" + 
						   "<img src='/images/easybpm/form/arrow.png' alt='move' width='16' height='16' class='handle' />" ) ;
	changeInput( _id ) ;

}

function loadElementValues(elt, ncol, nbel){
	var value = ( ( $(elt).attr('name') ) ? $(elt).attr('name') : "" ) ;
	var id = ( ($(elt).attr('value')) ? $(elt).attr('value') : "");
	var val_list = value + '=' + id + ';' ; 
	return val_list; 
}

function loadElementValuesInHtml(elt, ncol, nbel,eleType){
	var value = ( ( $(elt).attr('value') ) ? $(elt).attr('value') : "" ) ;
	var id ="";
	if(eleType=="radio"){
		id = ( ( $(elt).attr('text') ) ? $(elt).attr('text') : "" ) ;
	}else{
		id = ( ( $(elt).text() ) ? $(elt).text().trim() : "" ) ; 
	}

	var val_list = value + '=' + id + ';' ; 
	return val_list; 
}

function selectButtonToBeAddInForm(buttonId, name, style, func, createdBy, loggedInUser){
	var form_name = rblFormArray[ IDFORM ].e_name;
	nbelt++ ;
	var eltToAdd =	"" ;
	var _id = "nb_" + nbelt ;
	var eltToAdd = "<input type=button size=15 value='' style='"+style+"'/>";
	$(".connectedSortable").append("<li onClick='resetColumnBgcolor();' id='f_" + _id +"' class='elt' style='margin-top:20px;'>" + 
      		"<img src='/images/easybpm/form/arrow.png' alt='move' width='16' height='16' class='handle' />" );
	rblEltArray[ _id ] = new rbl_elt( _id, "button", "userdefinebutton" );
	showUserDefinedBtnControlPanel("f_" + _id, "create", name, style, func, createdBy, loggedInUser, buttonId);
}

function showUserDefinedBtnControlPanel(_no, mode, name, style, func, createdBy, loggedInUser, buttonId){
	this.e_onClick = func;
	this.e_btnStyle = style;
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
	var typeToAdd = "" ;
	var title = "";
	if ( rblEltArray[ _no]  != null) {
		typeToAdd = rblEltArray[ _no ].getType();
	}
	
	typeToAdd = typeToAdd.toLowerCase();
	if (typeToAdd.length == 0) typeToAdd = "text";
	title = setTitle(typeToAdd, title, _no);
	var typeToAddHtml = "";
	if(typeToAdd.indexOf('button') > -1){
		typeToAddHtml = "/images/html/bpm/"+ LANG +"/input_button.html";
	}	
	$("#generalProperties").load(typeToAddHtml,
	  function() { 
		$("#e_id").val( _no ) ;
		if ( rblEltArray[ _no]  != null) 
		{
			rblEltArray[ _no].showForm() ;
		}
		setNameAndLabel(title, _no);
		$('#val_defaut').val(name);
	});
	 $("#advancedProperties").load("/images/html/bpm/"+ LANG +"/input_userdefined_button.html",
             function() { 
             setEventHandlerValues(_no);
             $('#_btnId').val(buttonId);
             $('#btnId').val(buttonId);
             $('#_btnStyle').val(style);
             $('#btnStyle').val(style);
             $('#onClick').val(func);
             if(mode == "create"){
     			saveInputPanel( _no ) ; //in rbl_base.js
     		}
      }); 
	 /*if(createdBy != loggedInUser){
		 $("#advancedProperties").hide();
		 $("#generalProperties").hide();
	 }*/
}