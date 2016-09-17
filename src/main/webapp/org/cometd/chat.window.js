
function ChatWindow(config) {
	
    var _self = this;
    var _peerUserName;
    var _peerUserFullName;
    var _peerUserEmailId;
    var _peerUserImage;
    var _peerUserPosition;
    var _loginUserName;
    var _loginUserFullName;
    var _loginUserEmailId;
    var _loginUserImage;
    var _loginUserPosition;
    var _config;
    var _noOfWindows = 0;
    this._windowWidth  = 200;
    this._windowHeight = 250;
    this.lastUser      = null;
    this.windowArray   = [];
    var messageCount = 0;
    
    this.getWindowLeftPosition = function() {
    	return this.windowArray.length*this._windowWidth;
    },
    
    this.getPeerUserName = function() {
    	return this._peerUserName;
    };
    
    this.getPeerUserFullName = function() {
    	return this._peerUserFullName;
    };
    
    this.getPeerUserEmailId = function() {
    	return this._peerUserEmailId;
    };
    
    this.getPeerUserImage = function() {
	return this._peerUserImage;
    };

    this.getPeerUserPosition = function() {
	return this._peerUserPosition;
    };

    this.getLoginUserName = function() {
    	return this._loginUserName;
    };
    
    this.getLoginUserFullName = function() {
    	return this._loginUserFullName;
    };
    
    this.getLoginUserEmailId = function() {
    	return this._loginUserEmailId;
    };
    
    this.getLoginUserImage = function() {
	return this._loginUserImage;
    };

    this.getLoginUserPosition = function() {
	return this._loginUserPosition;
    };

    this.getMessageContainerID = function() {
    	return this.getLoginUserName().replace(/ /g, '_') + "_" + this.getPeerUserName().replace(/ /g, '_');
    };
    
    this.getTextInputID = function() {
    	return this.getLoginUserName().replace(/ /g, '_') + "_" + this.getPeerUserName().replace(/ /g, '_') + "_chatInput";
    };
    
    this.getWindowID = function() {
    	return this.getLoginUserName().replace(/ /g, '_') + "_" + this.getPeerUserName().replace(/ /g, '_') + "_window";
    };
    
    this.hide = function(_self) {
    	$("#" + _self.getWindowID()).css("display", "none");
    };
    
    this.show = function() {
    	$("#" + this.getWindowID()).css("display", "block");
    };
    
    /**
     * Returns whether the chat window is currently visible or not
     */
    this.isVisible = function() {
    	return $("#" + this.getWindowID()).css("display") == "none"?false:true;
    };
    
    this.addOnClickListener = function(el, fnHandler, context) {
        $(el).bind("click", context, function(evt) {
        	windowClose(context);
        	if(context != undefined) {
                fnHandler(context);
            } else {
                fnHandler();
            }
            var msg = $("#" + context.getMessageContainerID()).html();
            if(messageCount>0){
            	sendChatHistory(context.getLoginUserName(),context.getLoginUserFullName(), context.getLoginUserEmailId(), context.getPeerUserName(), context.getPeerUserFullName(), context.getPeerUserEmailId(), msg);
            	messageCount = 0;
            }
            return false;
        });
    };
    
    this.addOnMinimize = function(el, context) {
        $(el).bind("click", context, function(evt) {
        	windowId = context.getWindowID();
        	messageId = context.getMessageContainerID();
        	footerId = context.getMessageContainerID()+"_footer";
        	headerId = context.getMessageContainerID()+"_header";
        	minimizeWindow(windowId, messageId, headerId, footerId);
           /* if(context != undefined) {
                //fnHandler(context);
            } else {
                //fnHandler();
            }*/
            return false;
        });
    };
    
    this.appendMessage = function(fromUser, fromUserFullName, text, loginUser, loginUserFullName, toUser, toUserFullName) {
    	
    	var userNameCssClass    = "";
    	var textMessageCssClass = "";
    	
    	if (fromUser == loginUser) {
    		userNameCssClass    = "fromUserName";
        	textMessageCssClass = "fromMessage";
    	} else {
    		userNameCssClass    = "toUserName";
        	textMessageCssClass = "toMessage";
    	}
    	
    	if (this.lastUser == fromUser) {
    		fromUserFullName = "... ";
    	} else {
    		this.lastUser = fromUser;
    		fromUserFullName += ' : ';
    	}
    	var chatContainer = $("#" + this.getMessageContainerID());
    	var sb = [];
    	sb[sb.length] = '<span class="' + userNameCssClass + '">' + fromUserFullName + '</span>';
    	sb[sb.length] = '<span class="' + textMessageCssClass + '">' + text + '</span><br/>';
    	chatContainer.append(sb.join(""));  
    	chatContainer[0].scrollTop = chatContainer[0].scrollHeight - chatContainer.outerHeight();
    	messageCount = messageCount+1;
    };
    
    this.focusTextInput = function() {
    	$("#" + this.getTextInputID()).focus();
    },
    
    this.getWindowBody = function() {
    	
    	var bodyDIV = document.createElement("div");
    	bodyDIV.setAttribute("id", this.getMessageContainerID()); 
    	bodyDIV.style.width     = this._windowWidth + "px";
    	bodyDIV.style.height    = "180px";
    	bodyDIV.style.position  = 'absolute';
    	bodyDIV.style.left      = 0;
    	bodyDIV.style.bottom    = "40px";
    	bodyDIV.style.overflowY = 'auto';
    	return bodyDIV;
    };
    
    this.getWindowFooter = function() {
    	
    	var footerDIV = document.createElement("div");
    	footerDIV.style.width  = this._windowWidth + "px";
    	footerDIV.style.height = "40px";
    	//footerDIV.style.backgroundColor = '#31B404'; 
    	footerDIV.style.position = 'absolute';
    	footerDIV.style.left     = 0;
    	footerDIV.style.bottom   = 0;
    	footerDIV.setAttribute("id", this.getMessageContainerID()+"_footer");
    	
    	//create text input
    	var textInput = document.createElement("textarea");
    	textInput.setAttribute("id", this.getTextInputID());
//    	textInput.setAttribute("type", "textarea");
    	textInput.setAttribute("name", "chatInput");
    	textInput.setAttribute("class", "chatInput");
    	textInput.style.height = "30px";
    	
    	$(textInput).attr('autocomplete', 'off');
        $(textInput).keydown(function(e) {
            if (e.keyCode == 13) {
            	var message = $(textInput).val();
            	if(message.length>=1){
            		$.cometChat.send($(textInput).val(), _self.getPeerUserName(), _self.getPeerUserFullName(), _self.getPeerUserEmailId(), _self.getPeerUserImage, _self.getPeerUserPosition);
            	}
            	e.preventDefault();
            	$(textInput).val('');
            	$(textInput).focus();
				FitToContent(this, document.documentElement.clientHeight);
            }
            var footerHeight = footerDIV.style.height;
			footerHeight = footerHeight.substring(0,footerHeight.length-2);
			if(footerHeight<90){
				FitToContent(this, document.documentElement.clientHeight);
			}
			if(footerHeight>=90){
				textInput.style.overflowY = 'auto';
			}
        });
        
    	footerDIV.appendChild(textInput);
    	
    	return footerDIV;
    };
    
    this.getWindowHeader = function() {
    	var headerDIV = document.createElement("div");
    	headerDIV.style.width  = this._windowWidth + "px";
    	//headerDIV.onclick = function() { minimizeWindow(windowId,messageId, footerId);};
    	headerDIV.style.height = "30px";
    	//headerDIV.style.backgroundColor = '#31B404'; 
    	headerDIV.style.position = 'relative';
    	headerDIV.style.top      = 0;
    	headerDIV.style.left     = 0;
    	headerDIV.setAttribute("class", "chatBoxHeaderBackground");
    	headerDIV.setAttribute("id", this.getMessageContainerID()+"_header");
    	this.addOnMinimize(headerDIV, this);

    	var textUserName = document.createElement("span");
    	textUserName.setAttribute("class", "windowTitle");
    	if(this.getPeerUserFullName().length < 20){
    		textUserName.innerHTML = this.getPeerUserFullName();
    	} else {
    		var name = this.getPeerUserFullName().substring(0,18);
    		textUserName.innerHTML = name+' ...';
    	}
    	
    	var textMinimize = document.createElement("span");
    	textMinimize.setAttribute("class", "windowClose");
    	textMinimize.innerHTML = "-";
    	//textMinimize.onclick = function() { minimizeWindow(windowId, messageId, headerId, footerId);};
    	//textMinimize.onclick = function () {minimizeWindow("'"+windowId+"','"+messageId+"','"+headerId+"', '"+footerId+"'"); } ;
    	//textMinimize.setAttribute("onclick", " minimizeWindow('"+windowId+"','"+messageId+"','"+headerId+"', '"+footerId+"')");
    	this.addOnMinimize(textMinimize, this);
    	
    	var textClose = document.createElement("span");
    	textClose.setAttribute("class", "windowClose");
    	textClose.innerHTML = "x";
    	this.addOnClickListener(textClose, this.hide, this);
    	
    	headerDIV.appendChild(textUserName);
    	headerDIV.appendChild(textClose);
    	headerDIV.appendChild(textMinimize);
    	
    	return headerDIV;
    };
    
    this.getWindowHTML = function() {
    	var windowDIV = document.createElement("div");
    	windowDIV.setAttribute("id", this.getWindowID());
    	windowDIV.style.width  = this._windowWidth + "px"; 
    	windowDIV.style.height = this._windowHeight +"px";
    	windowDIV.style.backgroundColor = '#FFFFFF'; 
    	windowDIV.style.position = 'fixed';
    	windowDIV.style.bottom   = 0;
    	if(this._noOfWindows > 1){
    		windowDIV.style.right = ((noOfCurrentlyOpenedWindows-1)*15) - (noOfCurrentlyOpenedWindows-2) + "%" ;
    	}else{
    		windowDIV.style.right    =  noOfCurrentlyOpenedWindows*1 + "%";
    	}
    	 
    	windowDIV.style.zIndex   = 100;
    	windowDIV.style.border   = '1px solid #A1A1A1'; 
    	
    	windowDIV.appendChild(this.getWindowHeader()); 
    	windowDIV.appendChild(this.getWindowBody());
    	windowDIV.appendChild(this.getWindowFooter()); 
    	
    	return windowDIV;
    };
    
    this.initWindow = function(config,noOfChatWindows) {
    	this._config = config;
    	this._peerUserName    = config.peerUserName;
    	this._peerUserFullName    = config.peerUserFullName;
    	this._peerUserEmailId    = config.peerUserEmailId;
	this._peerUserImage	= config.peerUserImage;
	this._peerUserPosition	= config.peerUserPosition;
    	this._loginUserName   = config.loginUserName;
    	this._loginUserFullName   = config.loginUserFullName;
    	this._loginUserEmailId   = config.loginUserEmailId;
	this._loginUserImage	= config.loginUserImage
	this._loginUserPosition	= config.loginUserPosition
    	this.windowArray      = config.windowArray;
    	this._noOfWindows = noOfChatWindows;
    	
    	var body = document.getElementsByTagName('body')[0];
    	body.appendChild(this.getWindowHTML()); 
    	//focus text input just after opening window
    	this.focusTextInput();
    };
    
    function minimizeWindow(windowId, messageId, headerId, footerId){
    	var isVisible = $('#'+messageId).is(':visible');
    	if(isVisible){
    		$('#'+windowId).height(30);
    		$('#'+messageId).hide();
    		$('#'+footerId).hide();
    	} else {
    		$('#'+headerId).removeClass('chat-header-active');
    		$('#'+windowId).height(250);
    		$('#'+messageId).show();
    		$('#'+footerId).show();
    	}
    }
    
    function windowClose(context){
    	for(var i=0;i<chatWindowArray.length;i++){
    		var windowObj = chatWindowArray[i];     
    		if(windowObj.peerUserName == context.getPeerUserName()) {
    			for(var j=0;j<chatWindowPosition.length;j++){
    				if(context.getWindowID() == chatWindowPosition[j]){
    					for(var k =j+1;k<chatWindowPosition.length;k++) {
					    	var windowDIV = document.getElementById(chatWindowPosition[k]);
					    	var rightlen =  document.getElementById(chatWindowPosition[k]).style.right;
					    	rightlen =  rightlen.substring(0, rightlen.length - 1);
					    	windowDIV.style.right = rightlen - 14 + "%";
						}
    					if ( ~j ) {
    	    		    	chatWindowPosition.splice(j, 1);
    					}
    				}
    			}
    		}
    	}
        noOfCurrentlyOpenedWindows=noOfCurrentlyOpenedWindows-1;
    }
}

	function FitToContent(id, maxHeight)
	{
	   var text = id && id.style ? id : document.getElementById(id);
   	   var messagedivid = text.id;
   	   messagedivid = messagedivid.substring(0,messagedivid.length-10)
   	   var messagediv = document.getElementById(messagedivid);
	   if (text.clientHeight == text.scrollHeight) {
	      text.style.height = "30px";
	      text.parentElement.style.height = "40px";
	      messagediv.style.height = "180px";
	      messagediv.style.bottom = "40px";
	   }
	   var adjustedHeight = text.clientHeight;
	   if ( !maxHeight || maxHeight > adjustedHeight )
	   {
	      adjustedHeight = Math.max(text.scrollHeight, adjustedHeight);
	      if ( maxHeight )
	         adjustedHeight = Math.min(maxHeight, adjustedHeight);
	      if ( adjustedHeight > text.clientHeight ){
	    	  if(adjustedHeight >90){
	    		  adjustedHeight = 90;
	    	  }
	    	  text.style.height = adjustedHeight + "px";
	      	  text.parentElement.style.height = adjustedHeight + 10 + "px";
	      	  messagediv.style.height = 180 - adjustedHeight + "px";
	      	  messagediv.style.bottom = 40 + adjustedHeight + "px";
	      	  messagediv.scrollTop = messagediv.scrollHeight;
	      }
	   }
	}
