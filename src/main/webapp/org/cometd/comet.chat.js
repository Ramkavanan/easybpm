var oldMailCount = 0;
var oldMessageCount = 0;
(function($){
	$.cometChat = {
		
		_connected:false,
		count:0,
		loginUserName:undefined,
		loginUserFullName:undefined,
		loginUserMailId:undefined,
		loginUserImage:undefined,
		loginUserPosition:undefined,
		_disconnecting:undefined,
		_chatSubscription:undefined,
		_membersSubscription:undefined,
		_countSubscription:undefined,
		memberListContainerID:undefined, //'id' of a 'div' or 'span' tag which keeps list of online users.
		
		/**
		 * This method can be invoked to disconnect from the chat server.
		 * When user logging off or user close the browser window, user should
		 * be disconnected from cometd server.
		 */
		leave: function() {
			$.cometd.batch(function() {
                $.cometChat._unsubscribe();
            });
            $.cometd.disconnect();
            
            $.cometChat.loginUserName  = null;
            $.cometChat.loginUserFullName  = null;
            $.cometChat.loginUserMailId = null;
	    $.cometChat.loginUserImage = null;
	    $.cometChat.loginUserPosition = null;
            $.cometChat._disconnecting = true;
        },
		
        /**
         * Handshake with the server. When user logging into your system, you can call this method
         * to connect that user to cometd server. With that user will subscribe with tow channel.
         * '/chat/demo' and '/members/demo' and start to listen to those channels. 
         */
		join: function(username, fullName, emailId, profilePic, userOccupasion) {
			$.cometChat._disconnecting = false;
			$.cometChat.loginUserName  = username;
			$.cometChat.loginUserFullName = fullName;
			$.cometChat.loginUserMailId = emailId;
			$.cometChat.loginUserImage = profilePic;
			$.cometChat.loginUserPosition = userOccupasion;
            var cometdURL = location.protocol + "//" + location.host + config.contextPath + "/cometd";

            $.cometd.configure({
                url: cometdURL,
                logLevel: 'error'
            });
            $.cometd.websocketEnabled = false;
            $.cometd.handshake();
		},
		
		/**
		 * Send the text message to peer as a private message. Private messages
		 * are visible only for relevant peer users.
		 */
		send:function(textMessage, peerUserName, peerUserFullName, peerUserEmailId, userImage, userPosition) {
			if (!textMessage || !textMessage.length) return;
            $.cometd.publish('/service/privatechat', {
                room: '/chat/demo',
                user: $.cometChat.loginUserName,
                userFullName : $.cometChat.loginUserFullName,
                userEmailId : $.cometChat.loginUserMailId,
		loginUserImage: $.cometChat.loginUserImage,
		loginUserPosition: $.cometChat.loginUserPosition,
                chat: textMessage,
                peer: peerUserName,
                peerFullName: peerUserFullName,
                peerEmailId : peerUserEmailId,
		peerUserImage: userImage,
		peerUserPosition: userPosition,
            });
        },
        
		 /**
         * Updates the members list.
         * This function is called when a message arrives on channel /chat/members
         */
        members:function(message) {
            var sb = [];
            var onlineUsersCount = 0;
            clearOnlineUserData();
            $.each(message.data, function() {
            	if ($.cometChat.loginUserName == this.user) { //login user
            		/*sb[sb.length] = "<span style=\";color: #FF0000;\">" + this + "</span><br>";*/
            	} else { //peer users
            		var name = this.userFullName;
            		if(name.length > 20){
            			name = name.substring(0,18);
            			name = name + ' ...';
            		}
            		sb[sb.length] = "<li style='padding-top:14px;'><img style='float: left;height:25px;width:25px;' alt=''  src='"+this.loginUserImage+"'><a href='#' style='display:-moz-inline-box; padding:2px; position:relative;' data-lastname='"+this.userFullName+"' data-status='online' onclick=\"javascript:createWindow('" + $.cometChat.loginUserName + "', '" +$.cometChat.loginUserFullName + "', '"+$.cometChat.loginUserMailId+"', '" +$.cometChat.loginUserImage + "', '" +$.cometChat.loginUserPosition + "', '" + this.user + "', '"+this.userFullName+"', '"+this.userEmailId+"','"+this.loginUserImage+"','"+this.loginUserPosition+"');\" > <span>"+this.userFullName+"</span></a>&nbsp;&nbsp;&nbsp;<small style='padding:4px;'>"+this.loginUserPosition+"</small></li>";
            		loadOnlineUserData($.cometChat.loginUserName, $.cometChat.loginUserFullName, $.cometChat.loginUserMailId,  $.cometChat.loginUserImage ,$.cometChat.loginUserPosition, this.user, this.userFullName, this.userEmailId, this.loginUserImage, this.loginUserPosition);
            		onlineUsersCount++;
            		
            	}
            });
            $('#' + $.cometChat.memberListContainerID).html(sb.join("")); 
            $('#userCounter').html(onlineUsersCount);
                    
        },
        
        /**
         * This function will be invoked every time when '/count/demo' channel receive a message.
         */
         
         mailCount:function(message){
         	if(count == 2500){
         		count=0;
         	}
         	var mailCount = message.data[$.cometChat.loginUserName].mailCount;
         	var messageCount = message.data[$.cometChat.loginUserName].messageCount;
         	var toDoListCount = message.data[$.cometChat.loginUserName].toDoListCount;
         	var toReadCount = message.data[$.cometChat.loginUserName].toReadCount;
         	var isNewMail = message.data[$.cometChat.loginUserName].isNewMail;
         	//console.log(message.data[$.cometChat.loginUserName]);
         	//console.log(oldMailCount);
         	if ($('#mailCount').length > 0) {
        		$('#mailCount').html('' +mailCount+'');
	       	}else{
	       		$('#parent-ff8081813cb4dd84013cb4f24a5f0000').append(' <span id="mailCount" class="label info-label label-warning">'+mailCount+'</span>');
	       	}if ($('#messageCount').length > 0) {
	       		$('#messageCount').html('' +messageCount+'');
	    	}else{
	    		$('#parent-a5699bf2-51a9-11e3-bfeb-e069959ac2ac').append(' <span id="messageCount" class="label info-label label-important">'+messageCount+'</span>');
	    	}
			if(oldMailCount != mailCount){
				if(oldMailCount < mailCount && mailCount != 0){
					generateMailRemainder('bottomRight','Hi, You got a new Mail!','Mail');   
				} 
				oldMailCount = mailCount;
			} 
			/*if(isNewMail && oldMailCount == mailCount){
				generateMailRemainder('bottomRight','Hi, You got a new Mail!','Mail');   
			}*/
			if(oldMessageCount != messageCount){
			
				if(oldMessageCount < messageCount && messageCount != 0 ){
					generateMailRemainder('bottomLeft','Hi, You got a new Message!','Message');   
				} 
				oldMessageCount = messageCount;
			} 

		

			/*if($('#toDoListCount').length > 0){
	    		$('#toDoListCount').html('(' +toDoListCount+')');
	    	} else {
	    		$('#72c8234a-87f5-11e2-eeee-e069959ac296').append(' <span id="toDoListCount">('+toDoListCount+')</span>');
	    	}
	    	if($('#toReadCount').length > 0){
	    		$('#toReadCount').html('(' +toReadCount+')');
	    	} else {
	    		$('#ff8081813eeb4537013eeb4b964cffff').append(' <span id="toReadCount">('+toReadCount+')</span>');
	    	}*/
         },
        
        /**
         * This function will be invoked every time when '/chat/demo' channel receives a message.
         */
		receive :function(message) {
            var fromUser = message.data.user;
            var fromUserFullName = message.data.userFullName;
            var fromUserEmailId = message.data.userEmailId;
	    var fromUserImage = message.data.loginUserImage;
	    var fromUserPosition = message.data.loginUserPosition;
            var text     = message.data.chat;
            var toUser   = message.data.peer;
            var toUserFullName   = message.data.peerFullName;
            var toUserEmailId = message.data.peerEmailId;
		var toUserImage = message.data.peerUserImage;
		var toUserPosition = message.data.peerUserPosition;
            //Handle receiving messages
            if ($.cometChat.loginUserName == toUser) {
            	//'toUser' is the loginUser and 'fromUser' is the peer user.
            	var chatReceivingWindow = createWindow(toUser, toUserFullName, toUserEmailId, toUserImage, toUserPosition, fromUser, fromUserFullName, fromUserEmailId, fromUserImage, fromUserPosition);
            	chatReceivingWindow.appendMessage(fromUser, fromUserFullName, text, $.cometChat.loginUserName, $.cometChat.loginUserFullName, toUser, toUserFullName);
            }
            
            //Handle sending messages
            if ($.cometChat.loginUserName == fromUser) {
            	//'fromUser' is the loginUser and 'toUser' is the peer user.
            	var	chatSendingWindow = createWindow(fromUser, fromUserFullName, fromUserEmailId, fromUserImage, fromUserPosition, toUser, toUserFullName, toUserEmailId,toUserImage, toUserPosition);
            	chatSendingWindow.appendMessage(fromUser, fromUserFullName, text, $.cometChat.loginUserName, $.cometChat.loginUserFullName, toUser, toUserFullName);
            }
            
            var chatId = toUser+'_'+fromUser;
            var windowId = chatId+'_window';
            var footerId = chatId+'_footer';
            var headerId = chatId+'_header';
            
            var isVisible = $('#'+messageId).is(':visible');
        	if(isVisible == false){
        		$('#'+headerId).addClass('chat-header-active');
        	}
        },
        
        _unsubscribe: function() {
            if ($.cometChat._chatSubscription) {
                $.cometd.unsubscribe($.cometChat._chatSubscription);
            }
            $.cometChat._chatSubscription = null;
            
            if ($.cometChat._membersSubscription) {
                $.cometd.unsubscribe($.cometChat._membersSubscription);
            }
            $.cometChat._membersSubscription = null;
            
            if ($.cometChat._countSubscription) {
                $.cometd.unsubscribe($.cometChat._countSubscription);
            }
            $.cometChat._countSubscription = null;
        },
        
        _connectionEstablished: function() {
            // connection establish (maybe not for first time), so just
            // tell local user and update membership
            $.cometd.publish('/service/members', {
                user: $.cometChat.loginUserName,
                userFullName: $.cometChat.loginUserFullName,
                userEmailId : $.cometChat.loginUserMailId,
		loginUserImage: $.cometChat.loginUserImage,
		loginUserPosition: $.cometChat.loginUserPosition,
                room: '/chat/demo'
            });
        },
        
        _getCount: function() {
        
      	    $.cometd.publish('/count/getCount', {
                user: $.cometChat.loginUserName,
                room: '/countRoom/demo'
            });
            	if(count == 10){
            	$.ajax({
        		url: 'bpm/mail/getCount',
                type: 'GET',
                dataType: 'html',
                contentType: 'application/json; charset=UTF-8',
        		async: false,
        		data: '',
        		success : function(response) {
        			if(response.length > 0){
        				var obj = $.parseJSON(response);
        				if ($.cometChat.loginUserName == obj['user']) { //login user
        					/*if ($('#totalCount').length > 0) {
        						 $('#totalCount').html(obj['totalCount']);
        					}else{
        						$('#mail_header_menu').append(' <span id="totalCount" class="circle"> '+obj['totalCount']+'</span>');
        					}*/
        					/*if ($('#mailCount').length > 0) {
        						$('#mailCount').html('&nbsp;&nbsp;(' +obj['unreadEmailCount']+')');
	       					}else{
	       						$('#ff8081813f8b5aaf013f8b6565e40000').append(' <span id="mailCount">&nbsp;&nbsp;('+obj['unreadEmailCount']+')</span>');
	       					}if ($('#messageCount').length > 0) {
	       						$('#messageCount').html('&nbsp;&nbsp;(' +obj['unreadMessageCount']+')');
	    					}else{
	    						$('#21cc4b72-51aa-11e3-bfeb-e069959ac2ac').append(' <span id="messageCount">&nbsp;&nbsp;('+obj['unreadMessageCount']+')</span>');
	    					}*/
	    					if($('#toDoListCount').length > 0){
					    		$('#toDoListCount').html('(' +obj['toDoListCount']+')');
					    	} else {
					    		$('#72c8234a-87f5-11e2-eeee-e069959ac296').append(' <span id="toDoListCount">('+obj['toDoListCount']+')</span>');
					    	}
					    	if($('#toReadCount').length > 0){
					    		$('#toReadCount').html('(' +obj['toReadCount']+')');
					    	} else {
					    		$('#ff8081813eeb4537013eeb4b964cffff').append(' <span id="toReadCount">('+obj['toReadCount']+')</span>');
					    	}
                    	}
        			}
        			}
        	});
        	}
        	count++;
        	
        },
        
        _connectionBroken: function() {
            $('#' + $.cometChat.memberListContainerID).empty();
        },
        
        _connectionClosed: function() {
           /* $.cometChat.receive({
                data: {
                    user: 'system',
                    chat: 'Connection to Server Closed'
                }
            });*/
        },
        
        _metaConnect: function(message) {
            if ($.cometChat._disconnecting) {
            	$.cometChat._connected = false;
            	$.cometChat._connectionClosed();
            } else {
                var wasConnected = $.cometChat._connected;
                $.cometChat._connected = message.successful === true;
                if (!wasConnected && $.cometChat._connected) {
                	$.cometChat._connectionEstablished();
                } else if (wasConnected && !$.cometChat._connected) {
                	$.cometChat._connectionBroken();
                } 
            }
           
        },
        
        _subscribe: function() {
			$.cometChat._chatSubscription    = $.cometd.subscribe('/chat/demo',    $.cometChat.receive); //channel handling chat messages
			$.cometChat._membersSubscription = $.cometd.subscribe('/members/demo', $.cometChat.members); //channel handling members.
			$.cometChat._countSubscription = $.cometd.subscribe('/countRoom/demo', $.cometChat.mailCount); //channel handling counts.
        },
        
        _connectionInitialized: function() {
            // first time connection for this client, so subscribe tell everybody.
            $.cometd.batch(function() {
            	$.cometChat._subscribe();
            });
        },
        
		_metaHandshake: function (message) {
            if (message.successful) {
            	$.cometChat._connectionInitialized();
            }
        },
        
		_initListeners: function() {
			$.cometd.addListener('/meta/handshake', $.cometChat._metaHandshake);
	        	$.cometd.addListener('/meta/connect',   $.cometChat._metaConnect);
	        	$.cometd.addListener('/meta/connect',   $.cometChat._getCount);
	        
	        $(window).unload(function() {
	        	$.cometd.reload();
                $.cometd.disconnect();
	        });
		},		
			
		onLoad: function(config) {
			$.cometChat.memberListContainerID = config.memberListContainerID;
			$.cometChat._initListeners();
		}		
	};
	
})(jQuery);