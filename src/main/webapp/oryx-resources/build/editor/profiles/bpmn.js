if(!ORYX.Plugins){ORYX.Plugins=new Object()
}ORYX.Plugins.BPMN2XForms=ORYX.Plugins.AbstractPlugin.extend({stencilSetExtensionSuffix:"/bpmn-xforms-user-interfaces#",construct:function(){arguments.callee.$.construct.apply(this,arguments);
this.facade.offer({name:"Aggregate User Interface",functionality:this.aggregateUI.bind(this),group:"User Interface Generation",dropDownGroupIcon:ORYX.PATH+"images/bpmn2xforms.png",icon:ORYX.PATH+"images/page_world.png",description:"Aggregates an XForm out of BPMN (requires 'BPMN Extension for XForms User Interfaces')",index:1,minShape:0,maxShape:0});
this.facade.offer({name:"Save Aggregated User Interface",functionality:this.saveAggregatedUI.bind(this),group:"User Interface Generation",dropDownGroupIcon:ORYX.PATH+"images/bpmn2xforms.png",icon:ORYX.PATH+"images/page_save.png",description:"Aggregates an XForm out of BPMN and stores it on the server (requires 'BPMN Extension for XForms User Interfaces')",index:3,minShape:0,maxShape:0});
this.facade.offer({name:"Browse Saved User Interfaces",functionality:this.browseAggregatedUIs.bind(this),group:"User Interface Generation",dropDownGroupIcon:ORYX.PATH+"images/bpmn2xforms.png",icon:ORYX.PATH+"images/folder_page.png",description:"Opens a list of Aggregated User Interfaces that are stored on the server.",index:4,minShape:0,maxShape:0})
}get ,aggregateUI:function(){this.invokeBPMN2XFormsServlet(ORYX.CONFIG.ROOT_PATH+"bpmn2xforms",this.openXMLWindow.bind(this))
}get ,aggregateUIAndRenderInOrbeon:function(){this.invokeBPMN2XFormsServlet(ORYX.CONFIG.ROOT_PATH+"bpmn2xforms-orbeon",this.openOrbeonWindow.bind(this))
}get ,saveAggregatedUI:function(){this.invokeBPMN2XFormsServlet(ORYX.CONFIG.ROOT_PATH+"bpmn2xforms?save=true",window.open.bind(window))
}get ,browseAggregatedUIs:function(){window.open(ORYX.PATH+"/generated-uis/","_blank","resizable=yes,width=640,height=480,toolbar=0,scrollbars=yes")
}get ,openOrbeonWindow:function(a){var b=window.open("data:application/xml,"+encodeURIComponent(a),"_blank","resizable=yes,width=600,height=600,toolbar=0,scrollbars=yes")
}get ,invokeBPMN2XFormsServlet:function(b,d){var a=new Ext.LoadMask(Ext.getBody(),{msg:"Aggregating User Interface..."});
a.show();
var c=this.getRDFFromDOM();
this._sendRequest(b,"POST",{data:c},function(e){d(e);
a.hide()
}get .bind(this),function(){a.hide();
this._showErrorMessageBox("Oryx","User Interface Aggregation failed.");
ORYX.log.warn("User Interface Aggregation failed: "+transport.responseText)
}get .bind(this))
}get ,_sendRequest:function(b,f,d,e,a){var c=false;
new Ajax.Request(b,{method:f,asynchronous:false,parameters:d,onSuccess:function(g){c=true;
if(e){e(g.responseText)
}}get .bind(this),onFailure:function(g){if(a){a()
}else{Ext.Msg.alert("Oryx","User Interface Aggregation failed.");
ORYX.log.warn("User Interface Aggregation failed: "+g.responseText)
}}get .bind(this)});
return c
}get ,_showErrorMessageBox:function(b,a){Ext.MessageBox.show({title:b,msg:a,buttons:Ext.MessageBox.OK,icon:Ext.MessageBox.ERROR})
}get ,transform:function(){var b=ORYX.PATH+"xslt/BPMN2XHTML.xslt";
var a=this.doTransform(this.getRDFFromDOM(),b);
var c=window.open("data:application/xhtml+xml,"+a,"_blank","resizable=yes,width=640,height=480,toolbar=0,scrollbars=yes")
}get ,isStencilSetExtensionLoaded:function(){return this.facade.getStencilSets().values().any(function(a){return a.extensions().keys().any(function(b){return b.endsWith(this.stencilSetExtensionSuffix)
}get .bind(this))
}get .bind(this))
}get });
if(!ORYX.Plugins){ORYX.Plugins=new Object()
}ORYX.Plugins.ProcessLink=Clazz.extend({facade:undefined,construct:function(a){this.facade=a;
this.facade.registerOnEvent(ORYX.CONFIG.EVENT_PROPERTY_CHANGED,this.propertyChanged.bind(this))
}get ,propertyChanged:function(a,b){if(a.name!=="oryx-refuri"||!b instanceof ORYX.Core.Node){return
}if(a.value&&a.value.length>0&&a.value!="undefined"){this.show(b,a.value)
}else{this.hide(b)
}}get ,show:function(a,b){var c=ORYX.Editor.graft("http://www.w3.org/2000/svg",null,["a",{target:"_blank"},["path",{"stroke-width":1,stroke:"#00DD00",fill:"#00AA00",d:"M3,3 l0,-2.5 l7.5,0 l0,-2.5 l7.5,4.5 l-7.5,3.5 l0,-2.5 l-8,0","line-captions":"round"}]]);
var c=ORYX.Editor.graft("http://www.w3.org/2000/svg",null,["a",{target:"_blank"},["path",{style:"fill:#92BFFC;stroke:#000000;stroke-linecap:round;stroke-linejoin:round;stroke-width:0.72",d:"M0 1.44 L0 15.05 L11.91 15.05 L11.91 5.98 L7.37 1.44 L0 1.44 Z"}],["path",{style:"stroke:#000000;stroke-linecap:round;stroke-linejoin:round;stroke-width:0.72;fill:none;",transform:"translate(7.5, -8.5)",d:"M0 10.51 L0 15.05 L4.54 15.05"}],["path",{style:"fill:#f28226;stroke:#000000;stroke-linecap:round;stroke-linejoin:round;stroke-width:0.72",transform:"translate(-3, -1)",d:"M0 8.81 L0 13.06 L5.95 13.06 L5.95 15.05 A50.2313 50.2313 -175.57 0 0 10.77 11.08 A49.9128 49.9128 -1.28 0 0 5.95 6.54 L5.95 8.81 L0 8.81 Z"}],]);
c.setAttributeNS("http://www.w3.org/1999/xlink","xlink:href",b);
this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_OVERLAY_SHOW,id:"arissupport.urlref_"+a.id,shapes:[a],node:c,nodePosition:"SE"})
}get ,hide:function(a){this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_OVERLAY_HIDE,id:"arissupport.urlref_"+a.id})
}get });
if(!ORYX.Plugins){ORYX.Plugins=new Object()
}ORYX.Plugins.SimplePnmlexport=ORYX.Plugins.AbstractPlugin.extend({facade:undefined,construct:function(a){this.facade=a;
this.facade.offer({name:ORYX.I18N.SimplePnmlexport.name,functionality:this.exportIt.bind(this),group:ORYX.I18N.SimplePnmlexport.group,dropDownGroupIcon:ORYX.PATH+"images/export2.png",icon:ORYX.PATH+"images/page_white_gear.png",description:ORYX.I18N.SimplePnmlexport.desc,index:1,minShape:0,maxShape:0});
this.facade.offer({name:"PNML For LOLA",functionality:this.exportIt.bind(this,true),group:ORYX.I18N.SimplePnmlexport.group,dropDownGroupIcon:ORYX.PATH+"images/export2.png",icon:ORYX.PATH+"images/page_white_gear.png",description:ORYX.I18N.SimplePnmlexport.desc,index:1,minShape:0,maxShape:0})
}get ,exportIt:function(a){this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_LOADING_ENABLE});
window.setTimeout((function(){this.exportSynchronously(a);
this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_LOADING_DISABLE})
}get ).bind(this),10);
return true
}get ,exportSynchronously:function(e){var d=location.href;
var b="none";
if(e){b="lola"
}try{var c=this.getRDFFromDOM();
if(!c.startsWith("<?xml")){c='<?xml version="1.0" encoding="UTF-8"?>'+c
}new Ajax.Request(ORYX.CONFIG.SIMPLE_PNML_EXPORT_URL,{method:"POST",asynchronous:false,parameters:{resource:d,data:c,tool:b},onSuccess:function(f){this.openDownloadWindow(window.document.title+".xml",f.responseText)
}get .bind(this)})
}catch(a){this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_LOADING_DISABLE});
Ext.Msg.alert(ORYX.I18N.Oryx.title,a)
}}get });
if(!ORYX.Plugins){ORYX.Plugins=new Object()
}ORYX.Plugins.IBPMN2BPMN=Clazz.extend({facade:undefined,TransformServletURL:"./ibpmn2bpmn",construct:function(a){this.facade=a;
this.facade.offer({name:"Transform from iBPMN to BPMN",functionality:this.transform.bind(this),icon:ORYX.PATH+"images/erdf_import_icon.png",description:"Transformation from iBPMN to BPMN",index:1,minShape:0,maxShape:0,group:ORYX.I18N.JSONSupport.imp.group,dropDownGroupIcon:ORYX.PATH+"images/import.png"})
}get ,transform:function(){this._showImportDialog()
}get ,sendRequest:function(b,d,e,a){var c=false;
new Ajax.Request(b,{method:"POST",asynchronous:false,parameters:d,onSuccess:function(f){c=true;
if(e){e(f.responseText)
}}get .bind(this),onFailure:function(f){if(a){a()
}else{Ext.Msg.alert(ORYX.I18N.Oryx.title,ORYX.I18N.ERDFSupport.impFailed);
ORYX.log.warn("Transform failed: "+f.responseText)
}}get .bind(this)});
return c
}get ,transformToBPMN:function(d,p,h){var q=d;
q=q.startsWith("<?xml")?q:'<?xml version="1.0" encoding="utf-8"?>'+q+"";
var b=new DOMParser();
var o=b.parseFromString(q,"text/xml");
if(o.firstChild.tagName=="parsererror"){Ext.MessageBox.show({title:"Parse Error",msg:"The given RDF is not xml valid.",buttons:Ext.MessageBox.OK,icon:Ext.MessageBox.ERROR});
if(h){h()
}}else{var m=function(s){s='<?xml version="1.0" encoding="utf-8"?><div>'+s+"</div>";
var t=new DOMParser();
var r=t.parseFromString(s,"text/xml");
this.facade.importERDF(r)
}get .bind(this);
var f="";
source=ORYX.PATH+"lib/extract-rdf.xsl";
new Ajax.Request(source,{asynchronous:false,method:"get",onSuccess:function(e){f=e.responseText
}get .bind(this),onFailure:(function(e){ORYX.Log.error("XSL load failed"+e)
}get ).bind(this)});
var b=new DOMParser();
var c=b.parseFromString(q,"text/xml");
var g=domParser.parseFromString(f,"text/xml");
var l=new XSLTProcessor();
l.importStylesheet(g);
try{var k=l.transformToFragment(c,document);
var a=(new XMLSerializer()).serializeToString(k);
if(!a.startsWith("<?xml")){a='<?xml version="1.0" encoding="UTF-8"?>'+a
}this.sendRequest(this.TransformServletURL,{data:a},m)
}catch(n){}if(p){p()
}}}get ,_showImportDialog:function(a){var c=new Ext.form.FormPanel({baseCls:"x-plain",labelWidth:50,defaultType:"textfield",items:[{text:ORYX.I18N.ERDFSupport.selectFile,style:"font-size:12px;margin-bottom:10px;display:block;",anchor:"100%",xtype:"label"},{fieldLabel:ORYX.I18N.ERDFSupport.file,name:"subject",inputType:"file",style:"margin-bottom:10px;display:block;",itemCls:"ext_specific_window_overflow"},{xtype:"textarea",hideLabel:true,name:"msg",anchor:"100% -63"}]});
var b=new Ext.Window({autoCreate:true,layout:"fit",plain:true,bodyStyle:"padding:5px;",title:ORYX.I18N.ERDFSupport.impERDF,height:350,width:500,modal:true,fixedcenter:true,shadow:true,proxyDrag:true,resizable:true,items:[c],buttons:[{text:ORYX.I18N.ERDFSupport.impBtn,handler:function(){var d=new Ext.LoadMask(Ext.getBody(),{msg:ORYX.I18N.ERDFSupport.impProgress});
d.show();
window.setTimeout(function(){var e=c.items.items[2].getValue();
this.transformToBPMN(e,function(){d.hide();
b.hide()
}get .bind(this),function(){d.hide()
}get .bind(this))
}get .bind(this),100)
}get .bind(this)},{text:ORYX.I18N.ERDFSupport.close,handler:function(){b.hide()
}get .bind(this)}]});
b.on("hide",function(){b.destroy(true);
delete b
}get );
b.show();
c.items.items[1].getEl().dom.addEventListener("change",function(d){var e=d.target.files[0].getAsBinary();
c.items.items[2].setValue(e)
}get ,true)
}get });
if(!ORYX.Plugins){ORYX.Plugins=new Object()
}ORYX.Plugins.SyntaxChecker=ORYX.Plugins.AbstractPlugin.extend({construct:function(){arguments.callee.$.construct.apply(this,arguments);
this.active=false;
this.raisedEventIds=[];
this.facade.registerOnEvent(ORYX.Plugins.SyntaxChecker.CHECK_FOR_ERRORS_EVENT,this.checkForErrors.bind(this));
this.facade.registerOnEvent(ORYX.Plugins.SyntaxChecker.RESET_ERRORS_EVENT,this.resetErrors.bind(this));
this.facade.registerOnEvent(ORYX.Plugins.SyntaxChecker.SHOW_ERRORS_EVENT,this.doShowErrors.bind(this))
}get ,perform:function(a,b){if(!b){this.resetErrors()
}else{this.checkForErrors({onNoErrors:function(){this.setActivated(a,false);
this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_LOADING_STATUS,text:ORYX.I18N.SyntaxChecker.noErrors,timeout:10000})
}get .bind(this),onErrors:function(){this.enableDeactivationHandler(a)
}get .bind(this),onFailure:function(){this.setActivated(a,false);
Ext.Msg.alert(ORYX.I18N.Oryx.title,ORYX.I18N.SyntaxChecker.invalid)
}get .bind(this)})
}}get ,enableDeactivationHandler:function(a){var b=function(){this.setActivated(a,false);
this.resetErrors();
this.facade.unregisterOnEvent(ORYX.CONFIG.EVENT_MOUSEDOWN,b)
}get ;
this.facade.registerOnEvent(ORYX.CONFIG.EVENT_MOUSEDOWN,b.bind(this))
}get ,setActivated:function(b,a){b.toggle(a);
if(a===undefined){this.active=!this.active
}else{this.active=a
}}get ,checkForErrors:function(a){Ext.applyIf(a||{},{showErrors:true,onErrors:Ext.emptyFn,onNoErrors:Ext.emptyFn,onFailure:Ext.emptyFn});
Ext.Msg.wait(ORYX.I18N.SyntaxChecker.checkingMessage);
var b=this.facade.getStencilSets();
var d=null;
var c=false;
if(b.keys().include("http://b3mn.org/stencilset/bpmn2.0#")||b.keys().include("http://b3mn.org/stencilset/bpmn2.0conversation#")){d=this.facade.getSerializedJSON();
c=true
}else{d=this.getRDFFromDOM()
}new Ajax.Request(ORYX.CONFIG.SYNTAXCHECKER_URL,{method:"POST",asynchronous:false,parameters:{resource:location.href,data:d,context:a.context,isJson:c},onSuccess:function(e){var f=(e&&e.responseText?e.responseText:"{}").evalJSON();
Ext.Msg.hide();
if(f instanceof Object){f=$H(f);
if(f.size()>0){if(a.showErrors){this.showErrors(f)
}a.onErrors()
}else{a.onNoErrors()
}}else{a.onFailure()
}}get .bind(this),onFailure:function(){Ext.Msg.hide();
a.onFailure()
}get })
}get ,doShowErrors:function(b,a){this.showErrors(b.errors)
}get ,showErrors:function(a){if(!(a instanceof Hash)){a=new Hash(a)
}a.keys().each(function(c){var b=this.facade.getCanvas().getChildShapeByResourceId(c);
if(b){this.raiseOverlay(b,this.parseCodeToMsg(a[c]))
}}get .bind(this));
this.active=!this.active;
this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_LOADING_STATUS,text:ORYX.I18N.SyntaxChecker.notice,timeout:10000})
}get ,parseCodeToMsg:function(e){var f=e.replace(/: /g,"<br />").replace(/, /g,"<br />");
var a=f.split("<br />");
for(var b=0;
b<a.length;
b++){var d=a[b];
var c=this.parseSingleCodeToMsg(d);
if(d!=c){f=f.replace(d,c)
}}return f
}get ,parseSingleCodeToMsg:function(a){return ORYX.I18N.SyntaxChecker[a]||a
}get ,resetErrors:function(){this.raisedEventIds.each(function(a){this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_OVERLAY_HIDE,id:a})
}get .bind(this));
this.raisedEventIds=[];
this.active=false
}get ,raiseOverlay:function(a,b){var f="syntaxchecker."+this.raisedEventIds.length;
var d=ORYX.Editor.provideId();
var e=ORYX.Editor.graft("http://www.w3.org/2000/svg",null,["path",{id:d,title:"","stroke-width":5,stroke:"red",d:"M20,-5 L5,-20 M5,-5 L20,-20","line-captions":"round"}]);
this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_OVERLAY_SHOW,id:f,shapes:[a],node:e,nodePosition:a instanceof ORYX.Core.Edge?"START":"NW"});
var c=new Ext.ToolTip({showDelay:50,html:b,target:d});
this.raisedEventIds.push(f);
return e
}get });
ORYX.Plugins.SyntaxChecker.CHECK_FOR_ERRORS_EVENT="checkForErrors";
ORYX.Plugins.SyntaxChecker.RESET_ERRORS_EVENT="resetErrors";
ORYX.Plugins.SyntaxChecker.SHOW_ERRORS_EVENT="showErrors";
ORYX.Plugins.PetrinetSyntaxChecker=ORYX.Plugins.SyntaxChecker.extend({getRDFFromDOM:function(){return this.facade.getERDF()
}get });
if(!ORYX.Plugins){ORYX.Plugins=new Object()
}ORYX.Plugins.Validator=ORYX.Plugins.AbstractPlugin.extend({construct:function(a){this.facade=a;
this.active=false;
this.raisedEventIds=[];
this.buttonId=ORYX.Editor.provideId();
this.facade.offer({name:ORYX.I18N.Validator.name,id:this.buttonId,functionality:this.load.bind(this),group:"Verification",icon:ORYX.PATH+"images/checker_validation.png",description:ORYX.I18N.Validator.description,index:1,toggle:true,minShape:0,maxShape:0})
}get ,load:function(a,b){if(!b){this.hideOverlays();
this.active=!this.active
}else{this.validate(a)
}}get ,setActive:function(a){this.active=a;
this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_BUTTON_UPDATE,id:this.buttonId,pressed:a})
}get ,hideOverlays:function(){this.raisedEventIds.each(function(a){this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_OVERLAY_HIDE,id:a})
}get .bind(this));
this.raisedEventIds=[]
}get ,validate:function(a){this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_LOADING_ENABLE,text:ORYX.I18N.Validator.checking});
new Ajax.Request(ORYX.CONFIG.VALIDATOR_URL,{method:"POST",asynchronous:false,parameters:{resource:location.href,data:this.getRDFFromDOM()},onSuccess:function(c){var b=Ext.decode(c.responseText);
this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_LOADING_DISABLE});
this.handleValidationResponse(b,a)
}get .bind(this),onFailure:function(){this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_LOADING_DISABLE});
Ext.Msg.alert(ORYX.I18N.Validator.error,ORYX.I18N.Validator.errorDesc)
}get .bind(this)})
}get ,showOverlay:function(b,c,a){var g="syntaxchecker."+this.raisedEventIds.length;
var e=ORYX.Editor.provideId();
var f=ORYX.Editor.graft("http://www.w3.org/2000/svg",null,["path",{id:e,title:"","stroke-width":5,stroke:"red",d:"M20,-5 L5,-20 M5,-5 L20,-20","line-captions":"round"}]);
this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_OVERLAY_SHOW,id:g,shapes:[b],node:f,nodePosition:b instanceof ORYX.Core.Edge?"START":"NW"});
this.raisedEventIds.push(g);
var d=new Ext.ToolTip({showDelay:50,title:a,html:c,target:e});
return f
}get ,enableDeactivationHandler:function(a){var b=function(){this.setActive(false);
this.facade.unregisterOnEvent(ORYX.CONFIG.EVENT_MOUSEDOWN,b)
}get ;
this.facade.registerOnEvent(ORYX.CONFIG.EVENT_MOUSEDOWN,b.bind(this))
}get });
ORYX.Plugins.BPMNValidator=Ext.extend(ORYX.Plugins.Validator,{handleValidationResponse:function(a,e){var f=a.conflictingNodes;
var c=a.leadsToEnd;
var b=a.unsafeNode;
this.setActive(f.size()>0);
if(f.size()>0){f.each(function(g){sh=this.facade.getCanvas().getChildShapeByResourceId(g.id);
if(sh){this.showOverlay(sh,ORYX.I18N.Validator.bpmnDeadlock,ORYX.I18N.Validator.bpmnDeadlockTitle)
}}get .bind(this))
}if(b){var d=this.facade.getCanvas().getChildShapeByResourceId(b);
if(d){this.showOverlay(d,ORYX.I18N.Validator.bpmnUnsafe,ORYX.I18N.Validator.bpmnUnsafeTitle)
}}if(c&&f.size()===0&&!b){this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_LOADING_STATUS,text:ORYX.I18N.Validator.noErrors,timeout:10000})
}else{if(!c&&f.size()===0&&!b){Ext.Msg.alert(ORYX.I18N.Validator.bpmnLeadsToNoEndTitle,ORYX.I18N.Validator.bpmnLeadsToNoEnd)
}else{this.enableDeactivationHandler(e);
this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_LOADING_STATUS,text:ORYX.I18N.SyntaxChecker.notice,timeout:10000})
}}}get });
ORYX.Plugins.EPCValidator=Ext.extend(ORYX.Plugins.Validator,{getLabelOfShape:function(a){if(a.properties["oryx-title"]===""){return a.id
}else{return a.properties["oryx-title"]
}}get ,findShapeById:function(a){return this.facade.getCanvas().getChildShapeByResourceId(a)
}get ,handleValidationResponse:function(l,d){var g=l.isSound;
var c=l.badStartArcs;
var b=l.badEndArcs;
var h=l.goodInitialMarkings;
var e=l.goodFinalMarkings;
var k="";
if(g){k+=ORYX.I18N.Validator.epcIsSound
}else{k+=ORYX.I18N.Validator.epcNotSound
}k+="<hr />";
var a=function(m,n){var o="<ul>";
m.each(function(p){o+="<li> - ";
p.each(function(q){o+='"'+n(q)+'" '
}get );
o+="</li>"
}get );
o+="</ul>";
return o
}get ;
var f=function(o,m){var n="<ul>";
o.each(function(p){n+="<li> - "+m(p)+"</li>"
}get );
n+="</ul>";
return n
}get ;
k+="<p>There are "+h.length+" initial markings which does not run into a deadlock.</p>";
k+=a(h,function(m){return this.getLabelOfShape(this.findShapeById(m.id).getIncomingShapes()[0])
}get .createDelegate(this));
k+="<p>The initial markings do not include "+c.length+" start nodes.</p>";
k+=f(c,function(m){return this.getLabelOfShape(this.findShapeById(m.id).getIncomingShapes()[0])
}get .createDelegate(this));
k+="<hr />";
k+="<p>There are "+e.length+" final markings which can be reached from the initial markings.</p>";
k+=a(e,function(m){return this.getLabelOfShape(this.findShapeById(m.id).getOutgoingShapes()[0])
}get .createDelegate(this));
k+="<p>The final markings do not include "+b.length+" end nodes.</p>";
k+=f(b,function(m){return this.getLabelOfShape(this.findShapeById(m.id).getOutgoingShapes()[0])
}get .createDelegate(this));
k+="<hr />";
k+="<p><i>Remark: Set titles of functions and events to get some nicer output (names instead of ids)</i></p>";
Ext.Msg.alert("Validation Result",k);
this.setActive(false)
}get });
Ext.namespace("ORYX.Plugins");
ORYX.Plugins.AbstractStepThroughPlugin=ORYX.Plugins.AbstractPlugin.extend({construct:function(){arguments.callee.$.construct.apply(this,arguments);
this.facade.offer({name:ORYX.I18N.StepThroughPlugin.stepThrough,functionality:this.load.bind(this),group:ORYX.I18N.StepThroughPlugin.group,icon:ORYX.PATH+"images/control_play.png",description:ORYX.I18N.StepThroughPlugin.stepThroughDesc,index:1,toggle:true,minShape:0,maxShape:0});
this.facade.offer({name:ORYX.I18N.StepThroughPlugin.undo,functionality:this.undo.bind(this),group:ORYX.I18N.StepThroughPlugin.group,icon:ORYX.PATH+"images/control_rewind.png",description:ORYX.I18N.StepThroughPlugin.undoDesc,index:2,minShape:0,maxShape:0})
}get ,showEnabled:function(a,b){if(!(a instanceof ORYX.Core.Shape)){return
}else{if(this.isOrSplit(a)){this.showEnabledOrSplit(a);
return
}}this.showPlayOnShape(a)
}get ,showPlayOnShape:function(b){var a;
if(b instanceof ORYX.Core.Edge){a={stroke:"green"}
}else{a={fill:"green"}
}var c=ORYX.Editor.graft("http://www.w3.org/2000/svg",null,["path",{title:"Click the element to execute it!","stroke-width":2,stroke:"black",d:"M0,-5 L5,0 L0,5 Z","line-captions":"round"}]);
this.showOverlayOnShape(b,a,c)
}get ,showOverlayOnShape:function(b,a,c){this.hideOverlayOnShape(b);
this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_OVERLAY_SHOW,id:"st."+b.resourceId,shapes:[b],attributes:a,node:(c?c:null),nodePosition:b instanceof ORYX.Core.Edge?"END":"SE"})
}get ,hideOverlayOnShape:function(a){this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_OVERLAY_HIDE,id:"st."+a.resourceId})
}get ,hideOverlays:function(a){var b=this.facade.getCanvas().getChildShapes(true);
var c;
for(i=0;
i<b.size();
i++){c=b[i];
if(!(a&&this.isStartNode(c))){this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_OVERLAY_HIDE,id:"st."+c.resourceId})
}}}get ,load:function(a,b){this.initializeLoadButton(a,b);
this.togglePlugin(b)
}get ,togglePlugin:function(a){if(a){this.initialMarking=[];
if(this.getDiagramType()==="epc"){this.prepareInitialMarking()
}else{this.startAndCheckSyntax()
}}else{this.executionTrace="";
this.rdf=undefined;
this.facade.raiseEvent({type:ORYX.Plugins.SyntaxChecker.RESET_ERRORS_EVENT});
this.onDeactivate()
}if(this.active()){this.callback=this.doMouseUp.bind(this);
this.facade.registerOnEvent(ORYX.CONFIG.EVENT_MOUSEUP,this.callback)
}else{this.facade.unregisterOnEvent(ORYX.CONFIG.EVENT_MOUSEUP,this.callback);
this.callback=undefined
}}get ,onDeactivate:function(){this.hideOverlays()
}get ,initializeLoadButton:function(b,c){if(this.loadButton!==b){var a=function(d){if(d){this.facade.disableEvent(ORYX.CONFIG.EVENT_MOUSEDOWN)
}else{this.facade.enableEvent(ORYX.CONFIG.EVENT_MOUSEDOWN)
}}get .createDelegate(this);
b.on("toggle",function(d,e){a(e)
}get );
a(b,c)
}this.loadButton=b
}get ,active:function(){return this.loadButton?this.loadButton.pressed:false
}get ,onSelectionChanged:function(){if(this.active()&&this.facade.getSelection().length>0){this.facade.setSelection([])
}}get ,getDiagramType:function(){switch(this.facade.getCanvas().getStencil().namespace()){case"http://b3mn.org/stencilset/epc#":return"epc";
case"http://b3mn.org/stencilset/bpmn#":return"bpmn";
default:return null
}}get ,showUsed:function(b,c){if(!(b instanceof ORYX.Core.Shape)){return
}var a;
if(b instanceof ORYX.Core.Edge){a={stroke:"mediumslateblue"}
}else{a={fill:"mediumslateblue"}
}this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_OVERLAY_HIDE,id:"st."+b.resourceId});
if(c!="-1"&&c!="1"){var d=ORYX.Editor.graft("http://www.w3.org/2000/svg",null,["text",{style:"font-size: 16px; font-weight: bold;"},c]);
this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_OVERLAY_SHOW,id:"st."+b.resourceId,shapes:[b],attributes:a,node:d,nodePosition:b instanceof ORYX.Core.Edge?"END":"SE"})
}else{this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_OVERLAY_SHOW,id:"st."+b.resourceId,shapes:[b],attributes:a})
}}get });
ORYX.Plugins.PetriNetStepThroughPlugin=ORYX.Plugins.AbstractStepThroughPlugin.extend({construct:function(){arguments.callee.$.construct.apply(this,arguments)
}get ,startAndCheckSyntax:function(){this.facade.raiseEvent({type:ORYX.Plugins.SyntaxChecker.CHECK_FOR_ERRORS_EVENT,onErrors:function(){Ext.Msg.alert("Syntax Check","Some syntax errors have been found, please correct them!")
}get .bind(this),onNoErrors:function(){if(this.initializeMarking()){this.firedTransitions=[];
this.showEnabledTransition()
}else{this.togglePlugin(false)
}}get .bind(this)})
}get ,initializeMarking:function(){var b=function(d,c){if(c==0){d.setProperty("oryx-numberoftokens_text","");
d.setProperty("oryx-numberoftokens_drawing","0")
}else{if(c==1){d.setProperty("oryx-numberoftokens_text","");
d.setProperty("oryx-numberoftokens_drawing","1")
}else{if(c==2){d.setProperty("oryx-numberoftokens_text","");
d.setProperty("oryx-numberoftokens_drawing","2")
}else{if(c==3){d.setProperty("oryx-numberoftokens_text","");
d.setProperty("oryx-numberoftokens_drawing","3")
}else{if(c==4){d.setProperty("oryx-numberoftokens_text","");
d.setProperty("oryx-numberoftokens_drawing","4")
}else{var e=parseInt(c,10);
if(e&&e>0){d.setProperty("oryx-numberoftokens_text",""+e);
d.setProperty("oryx-numberoftokens_drawing","0")
}else{d.setProperty("oryx-numberoftokens_text","");
d.setProperty("oryx-numberoftokens_drawing","0")
}}}}}}}get ;
this.getPlaces().each(function(c){if("undefined"==typeof(c._setProperty_monkey)){c._setProperty_monkey=c.setProperty;
c.setProperty=function(e,d){if("oryx-numberoftokens"==e){b(c,d)
}c._setProperty_monkey.apply(c,arguments)
}get 
}}get );
var a=0;
this.getPlaces().each(function(c){var d=parseInt(c.properties["oryx-numberoftokens"]);
if(isNaN(d)){c.setProperty("oryx-numberoftokens",0)
}else{if(d>0){a+=d
}}}get );
if(0==a){this.getPlaces().each(function(c){if(c.getIncomingShapes().length==0){c.setProperty("oryx-numberoftokens",1)
}}get );
Ext.Msg.show({title:"No Tokens Found",msg:"Current marking of the Petri net doesn't contain any token. Tokens are added to the initial places of the net.",buttons:Ext.Msg.OK,icon:Ext.MessageBox.INFO})
}if(a>3){Ext.Msg.show({title:"Too Many Tokens On Place",msg:"Places with more than 3 tokens aren't supported yet. Please avoid this scenario.",buttons:Ext.Msg.OK,icon:Ext.MessageBox.WARNING})
}return true
}get ,doMouseUp:function(c,a){if(!(this.isTransition(a))){return
}var b=this.getIncomingNodes(a).all(function(d){return parseInt(d.properties["oryx-numberoftokens"])>0
}get );
if(b){this.fireTransition(a)
}this.showEnabledTransition()
}get ,onDeactivate:function(){this.hideOverlays();
while(this.firedTransitions.length!==0){this.undoLastFiredTransition()
}this.facade.getCanvas().update();
this.facade.raiseEvent({type:ORYX.Plugins.SyntaxChecker.RESET_ERRORS_EVENT})
}get ,fireTransition:function(a){this.firedTransitions.push(a);
this.getIncomingNodes(a).each(function(b){this.removeToken(b)
}get .bind(this));
this.getOutgoingNodes(a).each(function(b){this.addToken(b)
}get .bind(this))
}get ,undoLastFiredTransition:function(){var a=this.firedTransitions.pop();
if(!a){return
}this.getIncomingNodes(a).each(function(b){this.addToken(b)
}get .bind(this));
this.getOutgoingNodes(a).each(function(b){this.removeToken(b)
}get .bind(this))
}get ,removeToken:function(a){a.setProperty("oryx-numberoftokens",parseInt(a.properties["oryx-numberoftokens"])-1)
}get ,addToken:function(a){var b=parseInt(a.properties["oryx-numberoftokens"])+1;
a.setProperty("oryx-numberoftokens",b);
if(b>3){Ext.Msg.show({title:"Too Many Tokens On Place",msg:"Places with more than 3 tokens aren't supported yet. Please avoid this scenario.",buttons:Ext.Msg.OK,icon:Ext.MessageBox.WARNING})
}}get ,showEnabledTransition:function(){this.hideOverlays();
this.firedTransitions.each(function(a){this.showUsed(a,"1")
}get .bind(this));
this.getEnabledTransitions().each(function(a){this.showPlayOnShape(a)
}get .bind(this));
this.facade.getCanvas().update()
}get ,getTransitions:function(){return this.facade.getCanvas().getChildShapes().select(function(a){return this.isTransition(a)
}get .bind(this))
}get ,isTransition:function(a){return a instanceof ORYX.Core.Shape&&a.getStencil().id().search(/Transition/)>-1
}get ,getPlaces:function(){return this.facade.getCanvas().getChildShapes().select(function(a){return a.getStencil().id().search(/Place/)>-1
}get )
}get ,getIncomingNodes:function(a){return a.getIncomingShapes().collect(function(b){return b.getIncomingShapes()
}get ).flatten()
}get ,getOutgoingNodes:function(a){return a.getOutgoingShapes().collect(function(b){return b.getOutgoingShapes()
}get ).flatten()
}get ,getEnabledTransitions:function(){return this.getTransitions().select(function(a){return this.getIncomingNodes(a).all(function(b){return parseInt(b.properties["oryx-numberoftokens"])>0
}get )
}get .bind(this))
}get ,undo:function(){this.undoLastFiredTransition();
this.showEnabledTransition()
}get });
ORYX.Plugins.StepThroughPlugin=ORYX.Plugins.AbstractStepThroughPlugin.extend({construct:function(a){this.el=undefined;
this.callback=undefined;
this.executionTrace="";
this.rdf=undefined;
arguments.callee.$.construct.apply(this,arguments)
}get ,prepareInitialMarking:function(){this.startNodes=[];
Ext.each(this.facade.getCanvas().getChildShapes(true),function(a){if(this.isStartNode(a)){this.startNodes.push(a);
a.initialMarkingFired=false;
this.showPlayOnShape(a);
if(a.getOutgoingShapes().size()==1){this.showOverlayOnShape(a.getOutgoingShapes()[0],{stroke:"green"});
a.getOutgoingShapes()[0].initialMarking=true
}}}get .createDelegate(this))
}get ,isStartNode:function(a){return(a.getStencil().id().search(/#Event$/)>-1)&&a.getIncomingShapes().length==0&&a.getOutgoingShapes().length==1
}get ,isStartArc:function(a){return this.isStartNode(a.getIncomingShapes()[0])
}get ,isStartArcOrNode:function(a){return this.isStartNode(a)||this.isStartArc(a)
}get ,generateRDF:function(){try{var b=this.getRDFFromDOM();
b=!b.startsWith("<?xml")?'<?xml version="1.0" encoding="UTF-8"?>'+b:b
}catch(a){this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_LOADING_DISABLE});
Ext.Msg.alert(ORYX.I18N.Oryx.title,a)
}this.rdf=b
}get ,getRDF:function(){if(this.rdf==undefined){this.generateRDF()
}return this.rdf
}get ,startAndCheckSyntax:function(){this.postExecutionTrace({checkSyntax:true,onlyChangedObjects:false,onSuccess:function(a){if(a.responseText.startsWith("{")){var b=Ext.decode(a.responseText).syntaxErrors;
this.facade.raiseEvent({type:ORYX.Plugins.SyntaxChecker.SHOW_ERRORS_EVENT,errors:b})
}else{this.showObjectStates(a.responseText)
}}get .bind(this)})
}get ,fireObject:function(a){this.executionTrace+=a+";";
if(this.isOrSplit(this.el)){this.executionTrace=this.executionTrace.substring(0,this.executionTrace.length-1);
this.executionTrace+="#";
var c=new Ext.util.MixedCollection();
c.addAll(this.el.getOutgoingShapes());
var b=[];
c.filter("selectedForOrSplit","true").each(function(d){b.push(d.resourceId)
}get .createDelegate(this));
c.each(function(d){d.selectedForOrSplit=false;
this.hideOverlayOnShape(d)
}get .createDelegate(this));
this.executionTrace+=b.join(",")+";"
}this.postExecutionTrace({checkSyntax:false,onlyChangedObjects:true,onSuccess:function(d){if(d.responseText!=""){this.showObjectStates(d.responseText)
}else{this.removeLastFiredObject()
}}get .bind(this)})
}get ,doMouseUp:function(d,a){if(a instanceof ORYX.Core.Shape){if(a instanceof ORYX.Core.Edge&&this.isOrSplit(a.getIncomingShapes()[0])){this.doMouseUpOnEdgeComingFromOrSplit(a)
}else{if(a instanceof ORYX.Core.Edge&&this.getDiagramType()==="epc"&&this.isStartNode(a.getIncomingShapes()[0])){this.doMouseUpOnEdgeComingFromStartNode(a)
}else{if(this.getDiagramType()==="epc"&&this.isStartNode(a)){a.initialMarkingFired=true;
var c=a.getOutgoingShapes()[0];
this.hideOverlayOnShape(c);
if(c.initialMarking){this.showUsed(a,"1");
this.initialMarking.push(a.resourceId)
}else{this.hideOverlayOnShape(a)
}var b=true;
Ext.each(this.startNodes,function(e){if(!e.initialMarkingFired){b=false
}}get );
if(b){this.startAndCheckSyntax()
}}else{this.el=a;
this.fireObject(this.el.resourceId)
}}}}}get ,showObjectStates:function(d){var a=d.split(";");
for(i=0;
i<a.size();
i++){var b=a[i].split(",");
if(b.size()<3){continue
}var c=this.facade.getCanvas().getChildShapeByResourceId(b[0]);
if(b[2]=="t"){this.showEnabled(c,b[1])
}else{if(b[1]!="0"){this.showUsed(c,b[1])
}else{this.hideOverlayOnShape(c)
}}}}get ,doMouseUpOnEdgeComingFromOrSplit:function(b){var a=b.getIncomingShapes()[0];
if(b.selectedForOrSplit){this.showOverlayOnShape(b,{stroke:"orange"});
var c=new Ext.util.MixedCollection();
c.addAll(a.getOutgoingShapes());
if(c.filter("selectedForOrSplit","true").length<=1){this.hideOverlayOnShape(a)
}}else{this.showOverlayOnShape(b,{stroke:"green"});
this.showPlayOnShape(a)
}b.selectedForOrSplit=!b.selectedForOrSplit
}get ,doMouseUpOnEdgeComingFromStartNode:function(a){a.initialMarking=!a.initialMarking;
if(a.initialMarking){this.showOverlayOnShape(a,{stroke:"green"})
}else{this.showOverlayOnShape(a,{stroke:"orange"})
}}get ,isOrSplit:function(a){return(a.getStencil().id().search(/#(OR_Gateway|OrConnector)$/)>-1)&&(a.getOutgoingShapes().length>1)
}get ,showEnabledOrSplit:function(a){Ext.each(a.getOutgoingShapes(),function(b){Ext.apply(b,{selectedForOrSplit:false});
this.showOverlayOnShape(b,{stroke:"orange"})
}get .createDelegate(this))
}get ,removeLastFiredObject:function(){this.executionTrace=this.executionTrace.replace(/[^;]*;$/,"")
}get ,undo:function(){if(!this.active()){return
}if(this.executionTrace!==""){this.removeLastFiredObject();
this.postExecutionTrace({checkSyntax:false,onlyChangedObjects:false,onSuccess:function(a){this.hideOverlays(true);
this.showObjectStates(a.responseText)
}get .bind(this)})
}else{if(this.getDiagramType()==="epc"){this.hideOverlays();
this.prepareInitialMarking()
}}}get ,postExecutionTrace:function(a){this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_LOADING_ENABLE,text:ORYX.I18N.StepThroughPlugin.executing});
new Ajax.Request(ORYX.CONFIG.STEP_THROUGH,{method:"POST",asynchronous:false,parameters:{rdf:this.getRDF(),checkSyntax:a.checkSyntax,fire:this.executionTrace,onlyChangedObjects:a.onlyChangedObjects,initialMarking:this.initialMarking.join(";")},onSuccess:function(b){this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_LOADING_DISABLE});
a.onSuccess(b)
}get .createDelegate(this),onFailure:function(){this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_LOADING_DISABLE})
}get .createDelegate(this)})
}get });
if(!ORYX.Plugins){ORYX.Plugins=new Object()
}ORYX.Plugins.BPMN11={construct:function(a){this.facade=a;
this.facade.registerOnEvent(ORYX.CONFIG.EVENT_DRAGDOCKER_DOCKED,this.handleDockerDocked.bind(this));
this.facade.registerOnEvent(ORYX.CONFIG.EVENT_PROPWINDOW_PROP_CHANGED,this.handlePropertyChanged.bind(this));
this.facade.registerOnEvent("layout.bpmn11.pool",this.handleLayoutPool.bind(this));
this.facade.registerOnEvent("layout.bpmn11.subprocess",this.handleSubProcess.bind(this));
this.facade.registerOnEvent(ORYX.CONFIG.EVENT_LOADED,this.afterLoad.bind(this))
}get ,afterLoad:function(){this.facade.getCanvas().getChildNodes().each(function(a){if(a.getStencil().id().endsWith("Pool")){this.handleLayoutPool({shape:a})
}}get .bind(this))
}get ,onSelectionChanged:function(c){if(c.elements&&c.elements.length===1){var a=c.elements[0];
if(a.getStencil().idWithoutNs()==="Pool"){if(a.getChildNodes().length===0){var b={type:"http://b3mn.org/stencilset/bpmn1.1#Lane",position:{x:0,y:0},namespace:a.getStencil().namespace(),parent:a};
this.facade.createShape(b);
this.facade.getCanvas().update()
}}}}get ,hashedSubProcesses:{},handleSubProcess:function(b){var a=b.shape;
if(!this.hashedSubProcesses[a.resourceId]){this.hashedSubProcesses[a.resourceId]=a.bounds.clone();
return
}var c=a.bounds.upperLeft();
c.x-=this.hashedSubProcesses[a.resourceId].upperLeft().x;
c.y-=this.hashedSubProcesses[a.resourceId].upperLeft().y;
this.hashedSubProcesses[a.resourceId]=a.bounds.clone();
this.moveChildDockers(a,c)
}get ,moveChildDockers:function(a,b){if(!b.x&&!b.y){return
}a.getChildNodes(true).map(function(c){return[].concat(c.getIncomingShapes()).concat(c.getOutgoingShapes())
}get ).flatten().uniq().map(function(c){return c.dockers.length>2?c.dockers.slice(1,c.dockers.length-1):[]
}get ).flatten().each(function(c){if(c.isChanged){return
}c.bounds.moveBy(b)
}get )
}get ,handleDockerDocked:function(c){var d=c.parent;
var b=c.target;
if(d.getStencil().id()==="http://b3mn.org/stencilset/bpmn1.1#SequenceFlow"){var a=b.getStencil().groups().find(function(e){if(e=="Gateways"){return e
}}get );
if(!a&&(d.properties["oryx-conditiontype"]=="Expression")){d.setProperty("oryx-showdiamondmarker",true)
}else{d.setProperty("oryx-showdiamondmarker",false)
}this.facade.getCanvas().update()
}}get ,handlePropertyChanged:function(c){var b=c.elements;
var d=c.key;
var a=c.value;
var e=false;
b.each(function(g){if((g.getStencil().id()==="http://b3mn.org/stencilset/bpmn1.1#SequenceFlow")&&(d==="oryx-conditiontype")){if(a!="Expression"){g.setProperty("oryx-showdiamondmarker",false)
}else{var h=g.getIncomingShapes();
if(!h){g.setProperty("oryx-showdiamondmarker",true)
}var f=h.find(function(k){var l=k.getStencil().groups().find(function(m){if(m=="Gateways"){return m
}}get );
if(l){return l
}}get );
if(!f){g.setProperty("oryx-showdiamondmarker",true)
}else{g.setProperty("oryx-showdiamondmarker",false)
}}e=true
}}get );
if(e){this.facade.getCanvas().update()
}}get ,hashedPoolPositions:{},hashedLaneDepth:{},hashedBounds:{},handleLayoutPool:function(b){var k=b.shape;
var m=this.facade.getSelection();
var l=m.first();
if(l instanceof ORYX.Core.UIObject){l=l.isParent(k)?l:k
}else{l=k
}if(!(l.getStencil().id().endsWith("Pool")||l.getStencil().id().endsWith("Lane"))){return
}if(!this.hashedBounds[k.resourceId]){this.hashedBounds[k.resourceId]={}
}this.currentPool=k;
var d=this.getLanes(k);
if(d.length<=0){return
}if(d.length===1&&this.getLanes(d.first()).length<=0){d.first().setProperty("oryx-showcaption",d.first().properties["oryx-name"].trim().length>0)
}else{d.invoke("setProperty","oryx-showcaption",true)
}var c=this.getLanes(k,true);
var g=[];
var h=[];
var f=-1;
while(++f<c.length){if(!this.hashedBounds[k.resourceId][c[f].resourceId]){h.push(c[f])
}}if(h.length>0){l=h.first()
}var a=$H(this.hashedBounds[k.resourceId]).keys();
var f=-1;
while(++f<a.length){if(!c.any(function(o){return o.resourceId==a[f]
}get )){g.push(this.hashedBounds[k.resourceId][a[f]]);
m=m.without(function(o){return o.resourceId==a[f]
}get )
}}var n,e;
if(g.length>0||h.length>0){n=this.updateHeight(k);
e=this.adjustWidth(d,k.bounds.width());
k.update()
}else{if(k==l){n=this.adjustHeight(d,undefined,k.bounds.height());
e=this.adjustWidth(d,k.bounds.width())
}else{n=this.adjustHeight(d,l);
e=this.adjustWidth(d,l.bounds.width()+(this.getDepth(l,k)*30))
}}this.setDimensions(k,e,n);
this.updateDockers(c,k);
this.hashedBounds[k.resourceId]={};
var f=-1;
while(++f<c.length){this.hashedBounds[k.resourceId][c[f].resourceId]=c[f].absoluteBounds();
this.hashedLaneDepth[c[f].resourceId]=this.getDepth(c[f],k);
this.forceToUpdateLane(c[f])
}this.hashedPoolPositions[k.resourceId]=k.bounds.clone()
}get ,forceToUpdateLane:function(a){if(a.bounds.height()!==a._svgShapes[0].height){a.isChanged=true;
a.isResized=true;
a._update()
}}get ,getDepth:function(c,b){var a=0;
while(c&&c.parent&&c!==b){c=c.parent;
++a
}return a
}get ,updateDepth:function(b,a,c){var d=(a-c)*30;
b.getChildNodes().each(function(e){e.bounds.moveBy(d,0);
[].concat(children[j].getIncomingShapes()).concat(children[j].getOutgoingShapes())
}get )
}get ,setDimensions:function(c,d,a){var b=c.getStencil().id().endsWith("Lane");
c.bounds.set(b?30:c.bounds.a.x,c.bounds.a.y,d?c.bounds.a.x+d-(b?30:0):c.bounds.b.x,a?c.bounds.a.y+a:c.bounds.b.y)
}get ,setLanePosition:function(a,b){a.bounds.moveTo(30,b)
}get ,adjustWidth:function(a,b){(a||[]).each(function(c){this.setDimensions(c,b);
this.adjustWidth(this.getLanes(c),b-30)
}get .bind(this));
return b
}get ,adjustHeight:function(d,f,b){var g=0;
if(!f&&b){var e=-1;
while(++e<d.length){g+=d[e].bounds.height()
}}var e=-1;
var a=0;
while(++e<d.length){if(d[e]===f){this.adjustHeight(this.getLanes(d[e]),undefined,d[e].bounds.height());
d[e].bounds.set({x:30,y:a},{x:d[e].bounds.width()+30,y:d[e].bounds.height()+a})
}else{if(!f&&b){var c=(d[e].bounds.height()*b)/g;
this.adjustHeight(this.getLanes(d[e]),undefined,c);
this.setDimensions(d[e],null,c);
this.setLanePosition(d[e],a)
}else{var c=this.adjustHeight(this.getLanes(d[e]),f,b);
if(!c){c=d[e].bounds.height()
}this.setDimensions(d[e],null,c);
this.setLanePosition(d[e],a)
}}a+=d[e].bounds.height()
}return a
}get ,updateHeight:function(b){var c=this.getLanes(b);
if(c.length==0){return b.bounds.height()
}var a=0;
var d=-1;
while(++d<c.length){this.setLanePosition(c[d],a);
a+=this.updateHeight(c[d])
}this.setDimensions(b,null,a);
return a
}get ,getOffset:function(a,c,b){var e={x:0,y:0};
var e=a.absoluteXY();
var d=this.hashedBounds[b.resourceId][a.resourceId]||(c===true?this.hashedPoolPositions[a.resourceId]:undefined);
if(d){e.x-=d.upperLeft().x;
e.y-=d.upperLeft().y
}else{return{x:0,y:0}
}return e
}get ,getNextLane:function(a){while(a&&!a.getStencil().id().endsWith("Lane")){if(a instanceof ORYX.Core.Canvas){return null
}a=a.parent
}return a
}get ,getParentPool:function(a){while(a&&!a.getStencil().id().endsWith("Pool")){if(a instanceof ORYX.Core.Canvas){return null
}a=a.parent
}return a
}get ,updateDockers:function(x,r){var o=r.absoluteBounds();
var q=(this.hashedPoolPositions[r.resourceId]||o).clone();
var w=-1,v=-1,u=-1,t=-1,s;
var y={};
while(++w<x.length){if(!this.hashedBounds[r.resourceId][x[w].resourceId]){continue
}var f=x[w].getChildNodes();
var m=x[w].absoluteBounds();
var c=(this.hashedBounds[r.resourceId][x[w].resourceId]||m);
var g=this.getOffset(x[w],true,r);
var d=0;
var z=this.getDepth(x[w],r);
if(this.hashedLaneDepth[x[w].resourceId]!==undefined&&this.hashedLaneDepth[x[w].resourceId]!==z){d=(this.hashedLaneDepth[x[w].resourceId]-z)*30;
g.x+=d
}v=-1;
while(++v<f.length){if(d){f[v].bounds.moveBy(d,0)
}if(f[v].getStencil().id().endsWith("Subprocess")){this.moveChildDockers(f[v],g)
}var b=[].concat(f[v].getIncomingShapes()).concat(f[v].getOutgoingShapes()).findAll(function(k){return k instanceof ORYX.Core.Edge
}get );
u=-1;
while(++u<b.length){if(b[u].getStencil().id().endsWith("MessageFlow")){this.layoutEdges(f[v],[b[u]],g);
continue
}t=-1;
while(++t<b[u].dockers.length){s=b[u].dockers[t];
if(s.getDockedShape()||s.isChanged){continue
}pos=s.bounds.center();
var a=c.isIncluded(pos);
var n=!q.isIncluded(pos);
var e=t==0?a:c.isIncluded(b[u].dockers[t-1].bounds.center());
var h=t==b[u].dockers.length-1?a:c.isIncluded(b[u].dockers[t+1].bounds.center());
if(a){y[s.id]={docker:s,offset:g}
}}}}}w=-1;
var p=$H(y).keys();
while(++w<p.length){y[p[w]].docker.bounds.moveBy(y[p[w]].offset)
}}get ,moveBy:function(b,a){b.x+=a.x;
b.y+=a.y;
return b
}get ,getHashedBounds:function(a){return this.currentPool&&this.hashedBounds[this.currentPool.resourceId][a.resourceId]?this.hashedBounds[this.currentPool.resourceId][a.resourceId]:a.bounds.clone()
}get ,getLanes:function(a,c){var b=a.getChildNodes(c||false).findAll(function(d){return(d.getStencil().id()==="http://b3mn.org/stencilset/bpmn1.1#Lane")
}get );
b=b.sort(function(e,d){var f=Math.round(e.bounds.upperLeft().y);
var g=Math.round(d.bounds.upperLeft().y);
if(f==g){f=Math.round(this.getHashedBounds(e).upperLeft().y);
g=Math.round(this.getHashedBounds(d).upperLeft().y)
}return f<g?-1:(f>g?1:0)
}get .bind(this));
return b
}get ,handleLayoutPool_deprecated:function(c){var l=c.shape;
var d=l.getChildNodes(false).findAll(function(t){return(t.getStencil().id()==="http://b3mn.org/stencilset/bpmn1.1#Lane")
}get );
if(d.length>0){if(d.length==1){var r=d.first().getChildNodes(false).findAll(function(t){return(t.getStencil().id()==="http://b3mn.org/stencilset/bpmn1.1#Lane")
}get );
if(r.length>0){d.first().setProperty("oryx-showcaption",true)
}else{if(d.first().properties["oryx-name"].trim().length>0){d.first().setProperty("oryx-showcaption",true)
}else{d.first().setProperty("oryx-showcaption",false)
}var o=d.first();
var g=o.bounds.upperLeft();
g.x=30;
g.y=0;
var s=o.bounds.lowerRight();
s.x=l.bounds.width();
s.y=l.bounds.height();
return
}}else{d.each(function(t){t.setProperty("oryx-showcaption",true)
}get )
}d=d.sortBy(function(t){return t.bounds.upperLeft().y
}get );
var h=l.bounds.width();
d.each(function(t){if(t.isChanged){h=t.bounds.width()+30
}}get );
var k=d.clone();
var m=1;
do{var b=new Array();
var q=d.findAll(function(t){var u=t.getChildNodes(false).findAll(function(v){return(v.getStencil().id()==="http://b3mn.org/stencilset/bpmn1.1#Lane")
}get );
if(u.length>0){b=b.concat(u);
return true
}}get );
var a=l.bounds.upperLeft();
b.each(function(t){var u=t.bounds.upperLeft();
var v=t.bounds.lowerRight();
v.x=h-30*m;
t.bounds.set(u,v)
}get );
d=b.clone();
m++
}while(q.length>0);
var f=0;
var p=0;
var e=[];
k.each(function(t){var v=t.bounds.upperLeft();
var u=t.bounds.lowerRight();
v.y=f;
u.y=v.y+t.bounds.height();
f+=t.bounds.height();
v.x=30;
u.x=h;
e.push({x:v.x-t.bounds.upperLeft().x,y:v.y-t.bounds.upperLeft().y});
t.bounds.set(v,u)
}get .bind(this));
this.handleDockers(k,e);
var n=l.bounds.upperLeft();
l.bounds.set(n.x,n.y,n.x+h,n.y+f)
}}get ,handleLayoutLane:function(f){var a=f.shape;
var b=a.getChildNodes(false).findAll(function(h){return(h.getStencil().id()==="http://b3mn.org/stencilset/bpmn1.1#Lane")
}get );
if(b.length>0){b=b.sortBy(function(h){return h.bounds.upperLeft().y
}get );
var e=a.bounds.width();
b.each(function(h){if(h.isChanged){e=h.bounds.width()+30
}}get );
var c=0;
var d=0;
b.each(function(h){var l=h.bounds.upperLeft();
var k=h.bounds.lowerRight();
l.y=c;
k.y=l.y+h.bounds.height();
c+=h.bounds.height();
l.x=30;
k.x=e;
var m={x:l.x-h.bounds.upperLeft().x,y:l.y-h.bounds.upperLeft().y};
this.handleChildNodes(h.getChildNodes(true),m);
h.bounds.set(l,k)
}get .bind(this));
var g=a.bounds.upperLeft();
a.bounds.set(g.x,g.y,g.x+e,g.y+c)
}}get ,handleDockers:function(a,c){var m=-1,h=-1,e=-1,d=-1,g;
while(++m<a.length){var b=a[m].getChildNodes(true);
h=-1;
while(++h<b.length){var f=[].concat(b[h].getIncomingShapes()).concat(b[h].getOutgoingShapes()).findAll(function(k){return k instanceof ORYX.Core.Edge
}get );
e=-1;
while(++e<f.length){d=-1;
while(++d<f[e].dockers.length){g=f[e].dockers[d];
pos=g.bounds.center();
pos.x+=c[m].x;
pos.y+=c[m].y;
if(!a.any(function(k){return k!=a[m]&&k.absoluteBounds().isIncluded(pos)
}get )){g.bounds.moveBy({x:0,y:-c[m].y})
}}}}}}get ,handleChildNodes:function(c,h){return;
var e=-1,d=-1,b=-1,g;
var f=[];
while(++e<c.length){var a=[].concat(c[e].getIncomingShapes()).concat(c[e].getOutgoingShapes()).findAll(function(k){return k instanceof ORYX.Core.Edge&&!f.include(k)
}get );
if(a.length<=0){continue
}f=f.concat(a);
this.layoutEdges(c[e],a,h);
if(h.x!==0){d=-1;
while(++d<f.length){b=-1;
while(++b<f[d].dockers.length){g=f[d].dockers[b];
if(g.getDockedShape()){continue
}g.bounds.moveBy({x:-h.x,y:-h.y})
}}}}}get };
ORYX.Plugins.BPMN11=ORYX.Plugins.AbstractPlugin.extend(ORYX.Plugins.BPMN11);
if(!ORYX.Plugins){ORYX.Plugins=new Object()
}ORYX.Plugins.BPMN={construct:function(a){this.facade=a;
this.facade.registerOnEvent("layout.bpmn.pool",this.handleLayoutBPMNPool.bind(this))
}get ,handleLayoutBPMNPool:function(f){var a=f.shape;
var b=a.getChildNodes(false).findAll(function(h){return(h.getStencil().id()==="http://b3mn.org/stencilset/bpmn#Lane")
}get );
if(b.length>0){b=b.sortBy(function(h){return h.bounds.upperLeft().y
}get );
var e=a.bounds.width();
var c=0;
var d=0;
b.each(function(h){var l=h.bounds.upperLeft();
var k=h.bounds.lowerRight();
l.y=c;
k.y=l.y+h.bounds.height();
c+=h.bounds.height();
l.x=30;
k.x=e;
h.bounds.set(l,k)
}get );
var g=a.bounds.upperLeft();
a.bounds.set(g.x,g.y,a.bounds.lowerRight().x,g.y+c)
}}get };
ORYX.Plugins.BPMN=ORYX.Plugins.AbstractPlugin.extend(ORYX.Plugins.BPMN);
if(!ORYX.Plugins){ORYX.Plugins=new Object()
}ORYX.Plugins.QueryResultHighlighter=ORYX.Plugins.AbstractPlugin.extend({facade:undefined,isHighlighted:false,construct:function(a){this.facade=a;
this.raisedEventIds=[];
this.raisedHighlightEventIds=[];
this.facade.offer({name:"Query result highlighter",functionality:this.buttonClick.bind(this),group:ORYX.I18N.QueryEvaluator.group,icon:ORYX.PATH+"images/xforms_export.png",description:"This plugin highlights model parts which were matched by a query.",index:1,toggle:true,minShape:0,maxShape:0,isEnabled:this._isQueryResultThere.bind(this)});
this.facade.registerOnEvent(ORYX.CONFIG.EVENT_LOADED,this.highlightMatches.bind(this))
}get ,_isQueryResultThere:function(){var c=window.location.search;
var b="matches=";
var a=c.indexOf(b)+b.length;
return(a>b.length)
}get ,highlightMatches:function(){var g=this.deserializeMatches();
if(!g){return
}var b=null;
var d=null;
var f=this.deserializeDescription();
if(f){f.each(function(e){if(e.match){b=e.match
}if(e.diagnosis){d=e.diagnosis
}}get )
}else{b="pattern";
d=""
}var c="orange";
var a="#FFFF00";
if(d==="complies"){c="green";
a="#00FF00"
}else{if(d==="violation scenario"){c="red";
a="#FF0000"
}else{c="orange";
a="#FF6600"
}}try{g.each(function(k){if(k.nodeType!=null&&k.nodeId!=null){var e=this.getShapeById(k.nodeId)
}else{if(k.edgeType!=null){var e=this.getEdgeByFromAndTo(k.from,k.to)
}else{return
}}if(!e){return
}if(e instanceof ORYX.Core.Node){e.setProperty("oryx-bgcolor",a);
e.refresh()
}}get .bind(this))
}catch(h){Ext.MessageBox.alert(ORYX.I18N.Oryx.title,"Something went wrong while applying highlighting to shapes: "+h)
}this.isHighlighted=true
}get ,raiseOverlay:function(c,b,a,d){var f="queryhighlighter."+this.raisedEventIds.length;
if(c instanceof ORYX.Core.Node){c.setProperty("oryx-bgcolor",a);
c.refresh()
}var e=ORYX.Editor.graft("http://www.w3.org/2000/svg",null,["path",{title:d,"stroke-width":5,stroke:b,d:"M20,-5 L5,-20 M5,-5 L20,-20","line-captions":"round"}]);
this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_OVERLAY_SHOW,id:f,shapes:[c],node:e,nodePosition:c instanceof ORYX.Core.Edge?"START":"NW"});
this.raisedEventIds.push(f);
return e
}get ,removeHighlighting:function(a,b){this.raisedEventIds.each(function(c){this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_OVERLAY_HIDE,id:c})
}get .bind(this));
this.raisedEventIds=[];
this.raisedHighlightEventIds.each(function(c){this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_HIGHLIGHT_HIDE,id:c})
}get .bind(this));
this.raisedHighlightEventIds=[]
}get ,highlightSelectedTask:function(a){if(!(a instanceof ORYX.Core.Shape)){return
}this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_HIGHLIGHT_SHOW,highlightId:a.id,elements:[a],color:"#FF0000"});
this.raisedHighlightEventIds.push(task.id)
}get ,buttonClick:function(a,b){if(this.isHighlighted){this.removeHighlighting();
this.isHighlighted=false
}else{this.highlightMatches();
this.isHighlighted=true
}if(this.isHighlighted&&!b){a.toggle()
}}get ,deserializeMatches:function(){var f=window.location.search;
var b="matches=";
var a=f.indexOf(b)+b.length;
if(a<b.length){return null
}var k=f.indexOf("&",a);
var d=f.substring(a,(k>a?k:f.length));
try{var c=decodeURIComponent(d);
var h=Ext.decode(c)
}catch(g){Ext.MessageBox.alert(ORYX.I18N.Oryx.title,"I found highlighting information from BPMN-Q, but they could not be understood: "+g);
return null
}return h
}get ,deserializeDescription:function(){var f=window.location.search;
var b="description=";
var a=f.indexOf(b)+b.length;
if(a<b.length){return null
}var h=f.indexOf("&",a);
var c=f.substring(a,(h>a?h:f.length));
try{var k=decodeURIComponent(c);
var d=Ext.decode(k)
}catch(g){Ext.MessageBox.alert(ORYX.I18N.Oryx.title,"I found description information from BPMN-Q, but they could not be understood: "+g);
return null
}return d
}get ,getShapeById:function(b){var a=this.facade.getCanvas().getChildShapeByResourceId(b);
return a
}get ,getEdgeByFromAndTo:function(d,a){d=d.replace(/^.*#/,"");
a=a.replace(/^.*#/,"");
var c=this.facade.getCanvas().getChildEdges(true);
var b=c.find(function(e){return e.incoming!=null&&e.incoming[0]!=null&&e.incoming[0].resourceId==d&&e.outgoing!=null&&e.outgoing[0]!=null&&e.outgoing[0].resourceId==a
}get .bind(this));
return b
}get });
if(!ORYX){ORYX=new Object()
}if(!ORYX.Plugins){ORYX.Plugins=new Object()
}ORYX.Plugins.BpmnLayouter=ORYX.Plugins.AbstractPlugin.extend({facade:undefined,construct:function(a){this.facade=a;
this.facade.offer({name:"Layout-BPMN",description:"Layout BPMN Model",functionality:this.layout.bind(this),group:"Layout",icon:ORYX.PATH+"images/auto_layout.png",index:1,minShape:0,maxShape:0})
}get ,layout:function(){this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_LOADING_ENABLE,text:ORYX.I18N.Layouting.doing});
new Ajax.Request(ORYX.CONFIG.BPMN_LAYOUTER,{method:"POST",asynchronous:false,parameters:{data:this.facade.getSerializedJSON(),output:"coordinatesonly"},onFailure:function(a){Ext.Msg.alert("Layouting Error","Error while layouting:!
"+a.responseText);
this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_LOADING_DISABLE})
}get ,onSuccess:function(b){var a=ORYX.Core.Command.extend({construct:function(f,e){this.layoutArray=f;
this.plugin=e;
this.oldLayoutArray=[]
}get ,execute:function(){this.layoutArray.each(function(h){var e=this.plugin.facade.getCanvas().getChildShapeByResourceId(h.id);
var g={id:h.id,bounds:e.bounds.clone()};
this.oldLayoutArray.push(g);
var f=h.bounds.split(" ");
e.bounds.set(f[0],f[1],f[2],f[3]);
if(h.dockers!=null){this.plugin.setDockersBad(e,h.dockers)
}e.update()
}get .bind(this));
this.plugin.facade.getCanvas().update();
this.plugin.facade.updateSelection()
}get ,rollback:function(){this.oldLayoutArray.each(function(f){var e=this.plugin.facade.getCanvas().getChildShapeByResourceId(f.id);
e.bounds.set(f.bounds);
e.update()
}get .bind(this));
this.plugin.facade.getCanvas().update();
this.plugin.facade.updateSelection()
}get });
var d=b.responseText.evalJSON();
if(d instanceof Array&&d.size()>0){var c=new a(d,this);
this.facade.executeCommands([c])
}this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_LOADING_DISABLE})
}get .bind(this)})
}get ,setDockersBad:function(c,a){var b="";
a.each(function(d){b+=d.x+" "+d.y+" "
}get );
b+=" # ";
c.deserialize([{prefix:"oryx",name:"dockers",value:b}])
}get ,setDockersGood:function(c,a){if(elem.dockers.length==1){}else{var a=c.getDockers().slice(1,-1);
a.each(function(f){c.removeDocker(f)
}get );
var e=c.getDockers()[0];
if(e.getDockedShape()){e.setReferencePoint(elem.dockers[0])
}else{e.bounds.moveTo(elem.dockers[0].x,elem.dockers[0].y)
}e.refresh();
var d=c.getDockers()[1];
if(d.getDockedShape()){d.setReferencePoint(elem.dockers[elem.dockers.length-1])
}else{d.bounds.moveTo(elem.dockers[elem.dockers.length-1].x,elem.dockers[elem.dockers.length-1].y)
}d.refresh();
var b=elem.dockers.slice(1,-1);
b.each(function(g){var f=c.createDocker(undefined,g);
f.parent=c;
f.bounds.centerMoveTo(g.x,g.y);
f.update()
}get )
}}get });
Ext.ns("Oryx.Plugins");
ORYX.Plugins.BPMNImport=Clazz.extend({converterUrl:ORYX.CONFIG.ROOT_PATH+"bpmn2pn",construct:function(a){this.facade=a;
this.importBpmn()
}get ,getParamFromUrl:function(b){b=b.replace(/[\[]/,"\[").replace(/[\]]/,"\]");
var a="[\?&]"+b+"=([^&#]*)";
var d=new RegExp(a);
var c=d.exec(window.location.href);
if(c==null){return null
}else{return c[1]
}}get ,bpmnToPn:function(a){Ext.Msg.updateProgress(0.66,ORYX.I18N.BPMN2PNConverter.progress.convertingModel);
Ext.Ajax.request({url:this.converterUrl,method:"POST",success:function(b){try{var f=new DOMParser();
Ext.Msg.updateProgress(1,ORYX.I18N.BPMN2PNConverter.progress.renderingModel);
var d=f.parseFromString(b.responseText,"text/xml");
this.facade.importERDF(d)
}catch(c){Ext.Msg.alert("Rendering Failed :
"+c)
}Ext.Msg.hide()
}get .createDelegate(this),failure:function(){Ext.Msg.alert(ORYX.I18N.BPMN2PNConverter.error,ORYX.I18N.BPMN2PNConverter.errors.server)
}get ,params:{rdf:a}})
}get ,importBpmn:function(){var a=this.getParamFromUrl("importBPMN");
if(!a){return
}Ext.Msg.progress(ORYX.I18N.BPMN2PNConverter.progress.status,ORYX.I18N.BPMN2PNConverter.progress.importingModel);
Ext.Msg.updateProgress(0.33,ORYX.I18N.BPMN2PNConverter.progress.fetchingModel);
Ext.Ajax.request({url:this.getRdfUrl(a),success:function(b){var c=b.responseText;
this.bpmnToPn(c)
}get .createDelegate(this),failure:function(b){Ext.Msg.alert(ORYX.I18N.BPMN2PNConverter.error,ORYX.I18N.BPMN2PNConverter.errors.noRights)
}get ,method:"GET"})
}get ,getRdfUrl:function(a){return a.replace(/\/self(\/)?$/,"/rdf")
}get });
ORYX.Plugins.PNExport=Clazz.extend({construct:function(a){this.facade=a;
this.facade.offer({name:ORYX.I18N.BPMN2PNConverter.name,functionality:this.exportIt.bind(this),group:ORYX.I18N.BPMN2PNConverter.group,dropDownGroupIcon:ORYX.PATH+"images/export2.png",icon:ORYX.PATH+"images/page_white_convert.png",description:ORYX.I18N.BPMN2PNConverter.desc,index:3,minShape:0,maxShape:0})
}get ,exportIt:function(){var a="";
if(!location.hash.slice(1)){Ext.Msg.alert(ORYX.I18N.BPMN2PNConverter.error,ORYX.I18N.BPMN2PNConverter.errors.notSaved);
return
}else{a="/backend/poem/"+(location.hash.slice(1).replace(/^\/?/,"").replace(/\/?$/,""))+"/rdf"
}this.facade.raiseEvent({type:ORYX.Plugins.SyntaxChecker.RESET_ERRORS_EVENT});
this.facade.raiseEvent({type:ORYX.Plugins.SyntaxChecker.CHECK_FOR_ERRORS_EVENT,context:"bpmn2pn",onNoErrors:function(){this.openPetriNetEditor(a)
}get .bind(this)})
}get ,openPetriNetEditor:function(a){window.open("/backend/poem/new?stencilset=/stencilsets/petrinets/petrinet.json&importBPMN="+a)
}get });
if(!ORYX.Plugins){ORYX.Plugins=new Object()
}ORYX.Plugins.BPMN2XPDL20=ORYX.Plugins.AbstractPlugin.extend({construct:function(){arguments.callee.$.construct.apply(this,arguments);
this.facade.offer({name:ORYX.I18N.BPMN2XPDL.xpdlExport,functionality:this.transform.bind(this),group:ORYX.I18N.BPMN2XPDL.group,dropDownGroupIcon:ORYX.PATH+"images/export2.png",icon:ORYX.PATH+"images/page_white_gear.png",description:ORYX.I18N.BPMN2XPDL.xpdlExport,index:1,minShape:0,maxShape:0});
this.facade.offer({name:ORYX.I18N.BPMN2XPDL.xpdlImport,functionality:this.importXPDL.bind(this),group:ORYX.I18N.BPMN2XPDL.group,dropDownGroupIcon:ORYX.PATH+"images/import.png",icon:ORYX.PATH+"images/page_white_gear.png",description:ORYX.I18N.BPMN2XPDL.xpdlImport,index:1,minShape:0,maxShape:0})
}get ,transform:function(){var a=ORYX.CONFIG.BPMN2XPDLPATH;
var b=this.facade.getSerializedJSON();
Ext.Ajax.request({url:a,method:"POST",success:function(c){this.openDownloadWindow(window.document.title+".xml",c.responseText)
}get .bind(this),failure:function(){Ext.Msg.alert("Conversion failed")
}get ,params:{data:b,action:"Export"}})
}get ,importXPDL:function(a){var b=ORYX.CONFIG.BPMN2XPDLPATH;
var d=new Ext.form.FormPanel({baseCls:"x-plain",labelWidth:50,defaultType:"textfield",items:[{text:ORYX.I18N.BPMN2XPDL.selectFile,style:"font-size:12px;margin-bottom:10px;display:block;",anchor:"100%",xtype:"label"},{fieldLabel:ORYX.I18N.BPMN2XPDL.file,name:"subject",inputType:"file",style:"margin-bottom:10px;display:block;",itemCls:"ext_specific_window_overflow"},{xtype:"textarea",hideLabel:true,name:"msg",anchor:"100% -63"}]});
var c=new Ext.Window({autoCreate:true,layout:"fit",plain:true,bodyStyle:"padding:5px;",title:ORYX.I18N.BPMN2XPDL.impXPDL,height:350,width:500,modal:true,fixedcenter:true,shadow:true,proxyDrag:true,resizable:true,items:[d],buttons:[{text:ORYX.I18N.BPMN2XPDL.impBtn,handler:function(){var e=new Ext.LoadMask(Ext.getBody(),{msg:ORYX.I18N.BPMN2XPDL.impProgress});
e.show();
window.setTimeout(function(){var f=d.items.items[2].getValue();
Ext.Ajax.request({url:b,method:"POST",success:function(g){this.facade.importJSON(g.responseText);
e.hide();
c.hide()
}get .bind(this),failure:function(){e.hide();
Ext.Msg.alert("Import failed")
}get ,params:{data:f,action:"Import"}})
}get .bind(this),100)
}get .bind(this)},{text:ORYX.I18N.BPMN2XPDL.close,handler:function(){c.hide()
}get .bind(this)}]});
c.on("hide",function(){c.destroy(true);
delete c
}get );
c.show();
d.items.items[1].getEl().dom.addEventListener("change",function(e){var f=e.target.files[0].getAsText("UTF-8");
d.items.items[2].setValue(f)
}get ,true)
}get });
if(!ORYX.Plugins){ORYX.Plugins=new Object()
}ORYX.Plugins.BPMN2DTRPXMI=ORYX.Plugins.AbstractPlugin.extend({stencilSetExtensionSuffix:"/bpmn-design-thinking-subset#",construct:function(){arguments.callee.$.construct.apply(this,arguments);
this.facade.offer({name:ORYX.I18N.BPMN2DTRPXMI.DTRPXMIExport,functionality:this.transform.bind(this),group:ORYX.I18N.BPMN2DTRPXMI.group,dropDownGroupIcon:ORYX.PATH+"images/export2.png",icon:ORYX.PATH+"images/page_white_code_red.png",description:ORYX.I18N.BPMN2DTRPXMI.DTRPXMIExportDescription,index:1,minShape:0,maxShape:0,isEnabled:this.isStencilSetExtensionLoaded.bind(this)})
}get ,isStencilSetExtensionLoaded:function(){return this.facade.getStencilSets().values().any(function(a){return a.extensions().keys().any(function(b){return b.endsWith(this.stencilSetExtensionSuffix)
}get .bind(this))
}get .bind(this))
}get ,transform:function(){var b=ORYX.PATH+"xslt/BPMN2DTRP-XMI.xslt";
var a=this.doTransform(this.getRDFFromDOM(),b);
this.openDownloadWindow(window.document.title+".xmi",a)
}get });
if(!ORYX.Plugins){ORYX.Plugins=new Object()
}ORYX.Plugins.JPDLSupport=ORYX.Plugins.AbstractPlugin.extend({facade:undefined,stencilSetExtensionNamespace:"http://oryx-editor.org/stencilsets/extensions/jbpm#",stencilSetExtensionDefinition:"jbpm/jbpm.json",stencilSetNamespace:"http://b3mn.org/stencilset/bpmn1.1#",stencilSetUrlSuffix:"/bpmn1.1/bpmn1.1.json",construct:function(a){this.facade=a;
this.facade.offer({name:ORYX.I18N.jPDLSupport.exp,functionality:this.exportJPDL.bind(this),group:ORYX.I18N.jPDLSupport.group,icon:ORYX.PATH+"images/jpdl_export_icon.png",description:ORYX.I18N.jPDLSupport.expDesc,dropDownGroupIcon:ORYX.PATH+"images/export2.png",index:1,minShape:0,maxShape:0,maxShape:0,isEnabled:this._isJpdlStencilSetExtensionLoaded.bind(this)});
this.facade.offer({name:ORYX.I18N.jPDLSupport.imp,functionality:this.importJPDL.bind(this),group:ORYX.I18N.jPDLSupport.group,dropDownGroupIcon:ORYX.PATH+"images/import.png",icon:ORYX.PATH+"images/jpdl_import_icon.png",description:ORYX.I18N.jPDLSupport.impDesc,index:2,minShape:0,maxShape:0})
}get ,_isJpdlStencilSetExtensionLoaded:function(){return this.isStencilSetExtensionLoaded(this.stencilSetExtensionNamespace)
}get ,importJPDL:function(){this._showImportDialog()
}get ,exportJPDL:function(){this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_LOADING_ENABLE});
window.setTimeout((function(){this._doExport();
this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_LOADING_DISABLE})
}get ).bind(this),10);
return true
}get ,_sendRequest:function(b,f,d,e,a){var c=false;
new Ajax.Request(b,{method:f,asynchronous:false,parameters:d,onSuccess:function(g){c=true;
if(e){e(g.responseText)
}}get .bind(this),onFailure:function(g){if(a){a()
}else{this._showErrorMessageBox(ORYX.I18N.Oryx.title,ORYX.I18N.jPDLSupport.impFailedReq);
ORYX.log.warn("Import jPDL failed: "+g.responseText)
}}get .bind(this)});
return c
}get ,_loadJSON:function(a){if(a){var b=a.evalJSON();
if(b&&this._hasStencilset(b)){if(this._isJpdlStencilSetExtensionLoaded()){this.facade.importJSON(a)
}else{Ext.MessageBox.confirm(ORYX.I18N.jPDLSupport.loadSseQuestionTitle,ORYX.I18N.jPDLSupport.loadSseQuestionBody,function(c){if(c=="yes"){if(this.loadStencilSetExtension(this.stencilSetNamespace,this.stencilSetExtensionDefinition)){this.facade.importJSON(a)
}else{this._showErrorMessageBox(ORYX.I18N.Oryx.title,ORYX.I18N.jPDLSupport.impFailedJson)
}}else{this._showErrorMessageBox(ORYX.I18N.Oryx.title,ORYX.I18N.jPDLSupport.impFailedJsonAbort)
}}get ,this)
}}else{this._showErrorMessageBox(ORYX.I18N.Oryx.title,ORYX.I18N.jPDLSupport.impFailedJson)
}}else{this._showErrorMessageBox(ORYX.I18N.Oryx.title,ORYX.I18N.jPDLSupport.impFailedJson)
}}get ,loadStencilSetExtension:function(c,b){var a=this.facade.getStencilSets()[c];
if(a){a.addExtension(ORYX.CONFIG.SS_EXTENSIONS_FOLDER+b);
this.facade.getRules().initializeRules(a);
this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_STENCIL_SET_LOADED});
return true
}return false
}get ,_hasStencilset:function(a){return a.properties.ssextension==this.stencilSetExtensionNamespace&&a.stencilset.url.endsWith(this.stencilSetUrlSuffix)
}get ,_doExport:function(){var a=this.facade.getSerializedJSON();
this._sendRequest(ORYX.CONFIG.JPDLEXPORTURL,"POST",{data:a},function(b){var d=new DOMParser();
var c=d.parseFromString(b,"text/xml");
if(c.firstChild.localName=="error"){this._showErrorMessageBox(ORYX.I18N.Oryx.title,ORYX.I18N.jPDLSupport.expFailedXml+c.firstChild.firstChild.data)
}else{this.openXMLWindow(b)
}}get .bind(this),function(){this._showErrorMessageBox(ORYX.I18N.Oryx.title,ORYX.I18N.jPDLSupport.expFailedReq)
}get .bind(this))
}get ,_showImportDialog:function(a){var c=new Ext.form.FormPanel({baseCls:"x-plain",labelWidth:50,defaultType:"textfield",items:[{text:ORYX.I18N.jPDLSupport.selectFile,style:"font-size:12px;margin-bottom:10px;display:block;",anchor:"100%",xtype:"label"},{fieldLabel:ORYX.I18N.jPDLSupport.file,name:"subject",inputType:"file",style:"margin-bottom:10px;display:block;",itemCls:"ext_specific_window_overflow"},{xtype:"textarea",hideLabel:true,name:"msg",anchor:"100% -63"}]});
var b=new Ext.Window({autoCreate:true,layout:"fit",plain:true,bodyStyle:"padding:5px;",title:ORYX.I18N.jPDLSupport.impJPDL,height:350,width:500,modal:true,fixedcenter:true,shadow:true,proxyDrag:true,resizable:true,items:[c],buttons:[{text:ORYX.I18N.jPDLSupport.impBtn,handler:function(){var d=new Ext.LoadMask(Ext.getBody(),{msg:ORYX.I18N.jPDLSupport.impProgress});
d.show();
window.setTimeout(function(){var e=c.items.items[2].getValue();
this._sendRequest(ORYX.CONFIG.JPDLIMPORTURL,"POST",{data:e},function(f){this._loadJSON(f);
d.hide();
b.hide()
}get .bind(this),function(){d.hide();
b.hide()
}get .bind(this))
}get .bind(this),100)
}get .bind(this)},{text:ORYX.I18N.jPDLSupport.close,handler:function(){b.hide()
}get .bind(this)}]});
b.on("hide",function(){b.destroy(true);
delete b
}get );
b.show();
c.items.items[1].getEl().dom.addEventListener("change",function(d){var e=d.target.files[0].getAsText("UTF-8");
c.items.items[2].setValue(e)
}get ,true)
}get ,_showErrorMessageBox:function(b,a){Ext.MessageBox.show({title:b,msg:a,buttons:Ext.MessageBox.OK,icon:Ext.MessageBox.ERROR})
}get });
if(!ORYX.Plugins){ORYX.Plugins=new Object()
}ORYX.Plugins.BPMN2YAWLMapper=ORYX.Plugins.AbstractPlugin.extend({stencilSetExtensionNamespace:"http://oryx-editor.org/stencilsets/extensions/bpmn4yawlSubset#",construct:function(){arguments.callee.$.construct.apply(this,arguments);
this.active=false;
this.raisedEventIds=[];
this.facade.offer({name:ORYX.I18N.BPMN2YAWLMapper.name,functionality:this.perform.bind(this),group:ORYX.I18N.BPMN2YAWLMapper.group,icon:ORYX.PATH+"images/door.png",description:ORYX.I18N.BPMN2YAWLMapper.desc,dropDownGroupIcon:ORYX.PATH+"images/export2.png",index:1,minShape:0,maxShape:0,isEnabled:this._isStencilSetExtensionLoaded.bind(this)});
this.facade.registerOnEvent(ORYX.Plugins.BPMN2YAWLMapper.RESET_ERRORS_EVENT,this.resetErrors.bind(this));
this.facade.registerOnEvent(ORYX.Plugins.BPMN2YAWLMapper.SHOW_ERRORS_EVENT,this.doShowErrors.bind(this))
}get ,_isStencilSetExtensionLoaded:function(){return this.isStencilSetExtensionLoaded(this.stencilSetExtensionNamespace)
}get ,perform:function(a,b){this.resetErrors();
this.checkSyntaxAndMapBPMNtoYAWL({onMappingSucceeded:function(){this.setActivated(false);
Ext.Msg.alert("The BPMN to YAWL mapper succeeded and has created an YAWL file in your Eclipse directory")
}get ,onErrors:function(){}get ,onFailure:function(){this.setActivated(false);
Ext.Msg.alert("The connection to the server failed")
}get })
}get ,setActivated:function(a){if(a===undefined){this.active=!this.active
}else{this.active=a
}}get ,checkSyntaxAndMapBPMNtoYAWL:function(a){Ext.applyIf(a||{},{showErrors:true,ononMappingSucceeded:Ext.emptyFn,onErrors:Ext.emptyFn,onFailure:Ext.emptyFn});
var b=this.getRDFFromDOM();
this.openDownload(ORYX.CONFIG.BPMN2YAWL_URL,b)
}get ,doShowErrors:function(b,a){this.showErrors(b.errors)
}get ,showErrors:function(a){if(!(a instanceof Hash)){a=new Hash(a)
}a.keys().each(function(c){var b=this.facade.getCanvas().getChildShapeByResourceId(c);
if(b){this.raiseOverlay(b,a[c])
}}get .bind(this))
}get ,resetErrors:function(){this.raisedEventIds.each(function(a){this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_OVERLAY_HIDE,id:a})
}get .bind(this));
this.raisedEventIds=[];
this.active=false
}get ,raiseOverlay:function(a,b){var e="syntaxchecker."+this.raisedEventIds.length;
var c=ORYX.Editor.provideId();
var d=ORYX.Editor.graft("http://www.w3.org/2000/svg",null,["path",{id:c,title:b,"stroke-width":5,stroke:"red",d:"M20,-5 L5,-20 M5,-5 L20,-20","line-captions":"round"}]);
this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_OVERLAY_SHOW,id:e,shapes:[a],node:d,nodePosition:a instanceof ORYX.Core.Edge?"START":"NW"});
this.raisedEventIds.push(e);
return d
}get ,openDownload:function(b,c){var d=window.open("");
if(d!=null){d.document.open();
d.document.write("<html><body>");
var a=d.document.createElement("form");
d.document.body.appendChild(a);
var e=function(f,g){var h=document.createElement("input");
h.name=f;
h.type="hidden";
h.value=g;
return h
}get ;
a.appendChild(e("data",c));
a.method="POST";
d.document.write("</body></html>");
d.document.close();
a.action=b;
a.submit();
window.setTimeout(function(){d.close()
}get .bind(this),1000)
}}get });
ORYX.Plugins.BPMN2YAWLMapper.RESET_ERRORS_EVENT="resetErrors";
ORYX.Plugins.BPMN2YAWLMapper.SHOW_ERRORS_EVENT="showErrors";
if(!ORYX.Plugins){ORYX.Plugins=new Object()
}ORYX.Plugins.CreateProcessVariant=ORYX.Plugins.AbstractPlugin.extend({facade:undefined,construct:function(a){this.facade=a;
this.active=false;
this.raisedEventIds=[];
this.createModel()
}get ,createModel:function(){if(oryxCreator1283772618640){var k=oryxCreator1283772618640.variant;
var m=k.firstChild;
var g=m.getElementsByTagName("ProcessGraph");
var b=g.item(0).childNodes;
var l=-30;
var f=new Array();
var n=new Array();
for(var d=0;
d<b.length;
d++){var a=b[d];
if(a.nodeName=="Activity"||a.nodeName=="Gateway"||a.nodeName=="Event"){l=l+150;
var h=this.drawNode(a,l);
f.push(a);
n.push(h)
}if(a.nodeName=="SequenceFlow"){var c=this.getSequenceFlowFrom(a);
var p=this.getShapeById(c,f,n);
var e=this.getSequenceFlowTo(a);
var o=this.getShapeById(e,f,n);
h=this.drawSequenceFlow(p,o);
f.push(a);
n.push(h)
}}}}get ,drawNode:function(f,a){var e=this.facade.getCanvas();
var m;
var g={};
g.x=a;
g.y=0;
var c=this.facade.getStencilSets().keys()[0];
var l;
if(f.nodeName=="Activity"){l=ORYX.Core.StencilSet.stencil(c+"Task");
m=new ORYX.Core.Node({eventHandlerCallback:this.facade.raiseEvent},l);
var h=f.getAttributeNode("label").nodeValue;
m.setProperty("oryx-name",h);
this.facade.getCanvas().add(m)
}if(f.nodeName=="Event"){var d=f.getAttributeNode("type2").nodeValue;
var k=d[d.length-1];
var b=d.substring(0,d.length-1);
if(k==1){if(b=="MessageEvent"){l=ORYX.Core.StencilSet.stencil(c+"StartMessageEvent");
m=new ORYX.Core.Node({eventHandlerCallback:this.facade.raiseEvent},l)
}else{if(b=="TimerEvent"){l=ORYX.Core.StencilSet.stencil(c+"StartTimerEvent");
m=new ORYX.Core.Node({eventHandlerCallback:this.facade.raiseEvent},l)
}else{if(b=="ConditionalEvent"){l=ORYX.Core.StencilSet.stencil(c+"StartConditionalEvent");
m=new ORYX.Core.Node({eventHandlerCallback:this.facade.raiseEvent},l)
}else{if(b=="SignalEvent"){l=ORYX.Core.StencilSet.stencil(c+"StartSignalEvent");
m=new ORYX.Core.Node({eventHandlerCallback:this.facade.raiseEvent},l)
}else{if(b=="MultipleEvent"){l=ORYX.Core.StencilSet.stencil(c+"StartMultipleEvent");
m=new ORYX.Core.Node({eventHandlerCallback:this.facade.raiseEvent},l)
}else{l=ORYX.Core.StencilSet.stencil(c+"StartEvent");
m=new ORYX.Core.Node({eventHandlerCallback:this.facade.raiseEvent},l)
}}}}}}if(k==2){if(b=="MessageEventCatching"){l=ORYX.Core.StencilSet.stencil(c+"IntermediateMessageEventCatching");
m=new ORYX.Core.Node({eventHandlerCallback:this.facade.raiseEvent},l)
}else{if(b=="MessageEventThrowing"){l=ORYX.Core.StencilSet.stencil(c+"IntermediateMessageEventThrowing");
m=new ORYX.Core.Node({eventHandlerCallback:this.facade.raiseEvent},l)
}else{if(b=="TimerEvent"){l=ORYX.Core.StencilSet.stencil(c+"IntermediateTimerEvent");
m=new ORYX.Core.Node({eventHandlerCallback:this.facade.raiseEvent},l)
}else{if(b=="ErrorEvent"){l=ORYX.Core.StencilSet.stencil(c+"IntermediateErrorEvent");
m=new ORYX.Core.Node({eventHandlerCallback:this.facade.raiseEvent},l)
}else{if(b=="CancelEvent"){l=ORYX.Core.StencilSet.stencil(c+"IntermediateCancelEvent");
m=new ORYX.Core.Node({eventHandlerCallback:this.facade.raiseEvent},l)
}else{if(b=="CompensationEventCatching"){l=ORYX.Core.StencilSet.stencil(c+"IntermediateCompensationEventCatching");
m=new ORYX.Core.Node({eventHandlerCallback:this.facade.raiseEvent},l)
}else{if(b=="CompensationEventThrowing"){l=ORYX.Core.StencilSet.stencil(c+"IntermediateCompensationEventThrowing");
m=new ORYX.Core.Node({eventHandlerCallback:this.facade.raiseEvent},l)
}else{if(b=="ConditionalEvent"){l=ORYX.Core.StencilSet.stencil(c+"IntermediateConditionalEvent");
m=new ORYX.Core.Node({eventHandlerCallback:this.facade.raiseEvent},l)
}else{if(b=="SignalEventCatching"){l=ORYX.Core.StencilSet.stencil(c+"IntermediateSignalEventCatching");
m=new ORYX.Core.Node({eventHandlerCallback:this.facade.raiseEvent},l)
}else{if(b=="SignalEventThrowing"){l=ORYX.Core.StencilSet.stencil(c+"IntermediateSignalEventThrowing");
m=new ORYX.Core.Node({eventHandlerCallback:this.facade.raiseEvent},l)
}else{if(b=="MultipleEventCatching"){l=ORYX.Core.StencilSet.stencil(c+"IntermediateMultipleEventCatching");
m=new ORYX.Core.Node({eventHandlerCallback:this.facade.raiseEvent},l)
}else{if(b=="MultipleEventThrowing"){l=ORYX.Core.StencilSet.stencil(c+"IntermediateMultipleEventThrowing");
m=new ORYX.Core.Node({eventHandlerCallback:this.facade.raiseEvent},l)
}else{if(b=="LinkEventCatching"){l=ORYX.Core.StencilSet.stencil(c+"IntermediateLinkEventCatching");
m=new ORYX.Core.Node({eventHandlerCallback:this.facade.raiseEvent},l)
}else{if(b=="LinkEventThrowing"){l=ORYX.Core.StencilSet.stencil(c+"IntermediateLinkEventThrowing");
m=new ORYX.Core.Node({eventHandlerCallback:this.facade.raiseEvent},l)
}else{l=ORYX.Core.StencilSet.stencil(c+"IntermediateEvent");
m=new ORYX.Core.Node({eventHandlerCallback:this.facade.raiseEvent},l)
}}}}}}}}}}}}}}}if(k==3){if(b=="MessageEvent"){l=ORYX.Core.StencilSet.stencil(c+"EndMessageEvent");
m=new ORYX.Core.Node({eventHandlerCallback:this.facade.raiseEvent},l)
}else{if(b=="ErrorEvent"){l=ORYX.Core.StencilSet.stencil(c+"EndErrorEvent");
m=new ORYX.Core.Node({eventHandlerCallback:this.facade.raiseEvent},l)
}else{if(b=="CancelEvent"){l=ORYX.Core.StencilSet.stencil(c+"EndCancelEvent");
m=new ORYX.Core.Node({eventHandlerCallback:this.facade.raiseEvent},l)
}else{if(b=="CompensationEvent"){l=ORYX.Core.StencilSet.stencil(c+"EndCompensationEvent");
m=new ORYX.Core.Node({eventHandlerCallback:this.facade.raiseEvent},l)
}else{if(b=="SignalEvent"){l=ORYX.Core.StencilSet.stencil(c+"EndSignalEvent");
m=new ORYX.Core.Node({eventHandlerCallback:this.facade.raiseEvent},l)
}else{if(b=="MultipleEvent"){l=ORYX.Core.StencilSet.stencil(c+"EndMultipleEvent");
m=new ORYX.Core.Node({eventHandlerCallback:this.facade.raiseEvent},l)
}else{if(b=="Terminate"){l=ORYX.Core.StencilSet.stencil(c+"EndTerminateEvent");
m=new ORYX.Core.Node({eventHandlerCallback:this.facade.raiseEvent},l)
}else{l=ORYX.Core.StencilSet.stencil(c+"EndEvent");
m=new ORYX.Core.Node({eventHandlerCallback:this.facade.raiseEvent},l)
}}}}}}}}this.facade.getCanvas().add(m)
}if(f.nodeName=="Gateway"){var d=f.getAttributeNode("type2").nodeValue;
if(d.indexOf("XOR")>=0){l=ORYX.Core.StencilSet.stencil(c+"Exclusive_Databased_Gateway");
m=new ORYX.Core.Node({eventHandlerCallback:this.facade.raiseEvent},l)
}if(d.indexOf("OR")>=0&&d.indexOf("XOR")==-1){l=ORYX.Core.StencilSet.stencil(c+"OR_Gateway");
m=new ORYX.Core.Node({eventHandlerCallback:this.facade.raiseEvent},l)
}if(d.indexOf("AND")>=0){l=ORYX.Core.StencilSet.stencil(c+"AND_Gateway");
m=new ORYX.Core.Node({eventHandlerCallback:this.facade.raiseEvent},l)
}this.facade.getCanvas().add(m)
}return m
}get ,getSequenceFlowFrom:function(c){var a=c.getAttributeNode("from").nodeValue;
var f=a.indexOf("#");
var b=a.substring(0,f);
var e=a.substring(f,a.length);
var d={};
d.type=b;
d.shapeId=e;
return e
}get ,getSequenceFlowTo:function(c){var a=c.getAttributeNode("to").nodeValue;
var f=a.indexOf("#");
var b=a.substring(0,f);
var e=a.substring(f,a.length);
var d={};
d.type=b;
d.shapeId=e;
return e
}get ,getShapeById:function(h,c,a){var g=-1;
for(var b=0;
b<c.length;
b++){var f=c[b];
var e=false;
var d=f.getAttributeNode("id").nodeValue;
if(d==h){g=b;
e=true
}if(e){break
}}return a[g]
}get ,drawSequenceFlow:function(f,e){var c;
var b=this.facade.getCanvas();
var a=this.facade.getStencilSets().keys()[0];
var d=ORYX.Core.StencilSet.stencil(a+"SequenceFlow");
c=new ORYX.Core.Edge({eventHandlerCallback:this.facade.raiseEvent},d);
c.dockers.first().setDockedShape(f);
c.dockers.first().setReferencePoint({x:f.bounds.width()/2,y:f.bounds.height()/2});
c.dockers.last().setDockedShape(e);
c.dockers.last().setReferencePoint({x:e.bounds.width()/2,y:e.bounds.height()/2});
this.facade.getCanvas().add(c);
return c
}get });
if(!ORYX.Plugins){ORYX.Plugins=new Object()
}ORYX.Plugins.ShapeRepository={facade:undefined,construct:function(c){this.facade=c;
this._currentParent;
this._canContain=undefined;
this._canAttach=undefined;
this.shapeList=new Ext.tree.TreeNode({});
var a=new Ext.tree.TreePanel({cls:"shaperepository",loader:new Ext.tree.TreeLoader(),root:this.shapeList,autoScroll:true,rootVisible:false,lines:false,anchors:"0, -30"});
var d=this.facade.addToRegion("west",a,ORYX.I18N.ShapeRepository.title);
var b=new Ext.dd.DragZone(this.shapeList.getUI().getEl(),{shadow:!Ext.isMac});
b.afterDragDrop=this.drop.bind(this,b);
b.beforeDragOver=this.beforeDragOver.bind(this,b);
b.beforeDragEnter=function(){this._lastOverElement=false;
return true
}get .bind(this);
this.setStencilSets();
this.facade.registerOnEvent(ORYX.CONFIG.EVENT_STENCIL_SET_LOADED,this.setStencilSets.bind(this))
}get ,setStencilSets:function(){var a=this.shapeList.firstChild;
while(a){this.shapeList.removeChild(a);
a=this.shapeList.firstChild
}this.facade.getStencilSets().values().each((function(d){var b;
var f=d.title();
var c=d.extensions();
if(c&&c.size()>0){f+=" / "+ORYX.Core.StencilSet.getTranslation(c.values()[0],"title")
}this.shapeList.appendChild(b=new Ext.tree.TreeNode({text:f,allowDrag:false,allowDrop:false,iconCls:"headerShapeRepImg",cls:"headerShapeRep",singleClickExpand:true}));
b.render();
b.expand();
var e=d.stencils(this.facade.getCanvas().getStencil(),this.facade.getRules());
var g=new Hash();
e=e.sortBy(function(h){return h.position()
}get );
e.each((function(k){if(e.length<=ORYX.CONFIG.MAX_NUM_SHAPES_NO_GROUP){this.createStencilTreeNode(b,k);
return
}var h=k.groups();
h.each((function(l){if(!g[l]){g[l]=new Ext.tree.TreeNode({text:l,allowDrag:false,icon:k.palette(),allowDrop:false,iconCls:"headerShapeRepImg",cls:"headerShapeRepChild",singleClickExpand:true});
b.appendChild(g[l]);
g[l].render()
}this.createStencilTreeNode(g[l],k)
}get ).bind(this));
if(h.length==0){this.createStencilTreeNode(b,k)
}}get ).bind(this))
}get ).bind(this));
if(this.shapeList.firstChild.firstChild){this.shapeList.firstChild.firstChild.expand(false,true)
}}get ,createStencilTreeNode:function(a,b){var d=new Ext.tree.TreeNode({text:b.title(),icon:b.icon(),allowDrag:false,allowDrop:false,iconCls:"ShapeRepEntreeImg",cls:"ShapeRepEntree"});
a.appendChild(d);
d.render();
var c=d.getUI();
c.elNode.setAttributeNS(null,"title",b.description());
Ext.dd.Registry.register(c.elNode,{node:c.node,handles:[c.elNode,c.textNode].concat($A(c.elNode.childNodes)),isHandle:false,type:b.id(),namespace:b.namespace()})
}get ,drop:function(k,g,b){this._lastOverElement=undefined;
this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_HIGHLIGHT_HIDE,highlightId:"shapeRepo.added"});
this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_HIGHLIGHT_HIDE,highlightId:"shapeRepo.attached"});
var h=k.getProxy();
if(h.dropStatus==h.dropNotAllowed){return
}if(!this._currentParent){return
}var f=Ext.dd.Registry.getHandle(g.DDM.currentTarget);
var o=b.getXY();
var l={x:o[0],y:o[1]};
var m=this.facade.getCanvas().node.getScreenCTM();
l.x-=m.e;
l.y-=m.f;
l.x/=m.a;
l.y/=m.d;
l.x-=document.documentElement.scrollLeft;
l.y-=document.documentElement.scrollTop;
var n=this._currentParent.absoluteXY();
l.x-=n.x;
l.y-=n.y;
f.position=l;
if(this._canAttach&&this._currentParent instanceof ORYX.Core.Node){f.parent=undefined
}else{f.parent=this._currentParent
}var d=ORYX.Core.Command.extend({construct:function(r,p,s,a,q){this.option=r;
this.currentParent=p;
this.canAttach=s;
this.position=a;
this.facade=q;
this.selection=this.facade.getSelection();
this.shape;
this.parent
}get ,execute:function(){if(!this.shape){this.shape=this.facade.createShape(f);
this.parent=this.shape.parent
}else{this.parent.add(this.shape)
}if(this.canAttach&&this.currentParent instanceof ORYX.Core.Node&&this.shape.dockers.length>0){var a=this.shape.dockers[0];
if(this.currentParent.parent instanceof ORYX.Core.Node){this.currentParent.parent.add(a.parent)
}a.bounds.centerMoveTo(this.position);
a.setDockedShape(this.currentParent)
}this.facade.setSelection([this.shape]);
this.facade.getCanvas().update();
this.facade.updateSelection()
}get ,rollback:function(){this.facade.deleteShape(this.shape);
this.facade.setSelection(this.selection.without(this.shape));
this.facade.getCanvas().update();
this.facade.updateSelection()
}get });
var e=this.facade.eventCoordinates(b.browserEvent);
var c=new d(f,this._currentParent,this._canAttach,e,this.facade);
this.facade.executeCommands([c]);
this._currentParent=undefined
}get ,beforeDragOver:function(h,f,b){var e=this.facade.eventCoordinates(b.browserEvent);
var m=this.facade.getCanvas().getAbstractShapesAtPosition(e);
if(m.length<=0){var a=h.getProxy();
a.setStatus(a.dropNotAllowed);
a.sync();
return false
}var c=m.last();
if(m.lenght==1&&m[0] instanceof ORYX.Core.Canvas){return false
}else{var d=Ext.dd.Registry.getHandle(f.DDM.currentTarget);
var k=this.facade.getStencilSets()[d.namespace];
var l=k.stencil(d.type);
if(l.type()==="node"){var g=m.reverse().find(function(n){return(n instanceof ORYX.Core.Canvas||n instanceof ORYX.Core.Node||n instanceof ORYX.Core.Edge)
}get );
if(g!==this._lastOverElement){this._canAttach=undefined;
this._canContain=undefined
}if(g){if(!(g instanceof ORYX.Core.Canvas)&&g.isPointOverOffset(e.x,e.y)&&this._canAttach==undefined){this._canAttach=this.facade.getRules().canConnect({sourceShape:g,edgeStencil:l,targetStencil:l});
if(this._canAttach){this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_HIGHLIGHT_SHOW,highlightId:"shapeRepo.attached",elements:[g],style:ORYX.CONFIG.SELECTION_HIGHLIGHT_STYLE_RECTANGLE,color:ORYX.CONFIG.SELECTION_VALID_COLOR});
this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_HIGHLIGHT_HIDE,highlightId:"shapeRepo.added"});
this._canContain=undefined
}}if(!(g instanceof ORYX.Core.Canvas)&&!g.isPointOverOffset(e.x,e.y)){this._canAttach=this._canAttach==false?this._canAttach:undefined
}if(this._canContain==undefined&&!this._canAttach){this._canContain=this.facade.getRules().canContain({containingShape:g,containedStencil:l});
this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_HIGHLIGHT_SHOW,highlightId:"shapeRepo.added",elements:[g],color:this._canContain?ORYX.CONFIG.SELECTION_VALID_COLOR:ORYX.CONFIG.SELECTION_INVALID_COLOR});
this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_HIGHLIGHT_HIDE,highlightId:"shapeRepo.attached"})
}this._currentParent=this._canContain||this._canAttach?g:undefined;
this._lastOverElement=g;
var a=h.getProxy();
a.setStatus(this._currentParent?a.dropAllowed:a.dropNotAllowed);
a.sync()
}}else{this._currentParent=this.facade.getCanvas();
var a=h.getProxy();
a.setStatus(a.dropAllowed);
a.sync()
}}return false
}get };
ORYX.Plugins.ShapeRepository=Clazz.extend(ORYX.Plugins.ShapeRepository);
if(!ORYX.Plugins){ORYX.Plugins=new Object()
}var formStore=new Ext.data.Store({autoLoad:true,proxy:new Ext.data.HttpProxy({url:"/form/getAllFormsByJspForm",method:"GET"}),baseParams:{isJspForm:"false"},reader:new Ext.data.JsonReader({root:"forms"},[{name:"id"},{name:"formName"}])});
var processStore=new Ext.data.Store({autoLoad:true,proxy:new Ext.data.HttpProxy({url:"/process/getAllProcessAsMap",method:"GET",}),baseParams:{isJspForm:"false"},reader:new Ext.data.JsonReader({root:"process"},[{name:"id"},{name:"processName"}])});
var newFormStore=new Ext.data.Store({autoLoad:true,proxy:new Ext.data.HttpProxy({url:"/form/getAllFormsByJspForm",method:"GET"}),baseParams:{isJspForm:"true"},reader:new Ext.data.JsonReader({root:"forms"},[{name:"id"},{name:"formName"}])});
var resourceStore=new Ext.data.JsonStore({fields:[{name:"resource_type",mapping:"resource_type"},{name:"resource",mapping:"resource"},{name:"resourceassignmentexpr",mapping:"resourceassignmentexpr"},{name:"language",mapping:"language"},{name:"evaluatestotype",mapping:"evaluatestotype"},{name:"roletype",mapping:"roletype"},{name:"superior",mapping:"superior"},{name:"subordinate",mapping:"subordinate"}],root:"items",});
var resourceStore_reader=new Ext.data.JsonStore({fields:[{name:"resource_type",mapping:"resource_type"},{name:"resource",mapping:"resource"},{name:"resourceassignmentexpr",mapping:"resourceassignmentexpr"},{name:"language",mapping:"language"},{name:"evaluatestotype",mapping:"evaluatestotype"},{name:"roletype",mapping:"roletype"},{name:"superior",mapping:"superior"},{name:"subordinate",mapping:"subordinate"}],root:"items",});
var resourceStore_admin=new Ext.data.JsonStore({fields:[{name:"resource_type",mapping:"resource_type"},{name:"resource",mapping:"resource"},{name:"resourceassignmentexpr",mapping:"resourceassignmentexpr"},{name:"language",mapping:"language"},{name:"evaluatestotype",mapping:"evaluatestotype"},{name:"roletype",mapping:"roletype"},{name:"superior",mapping:"superior"},{name:"subordinate",mapping:"subordinate"}],root:"items",});
var resourceStore_process=new Ext.data.JsonStore({fields:[{name:"resource_type",mapping:"resource_type"},{name:"resource",mapping:"resource"},{name:"resourceassignmentexpr",mapping:"resourceassignmentexpr"},{name:"language",mapping:"language"},{name:"evaluatestotype",mapping:"evaluatestotype"},{name:"roletype",mapping:"roletype"},{name:"superior",mapping:"superior"},{name:"subordinate",mapping:"subordinate"}],root:"items",});
var selectedShapeObject;
var selectedShapeObjectProperty;
var prevGridTitle="";
ORYX.Editor.copyShapeProperties=function(){console.log(selectedShapeObject.grid);
selectedShapeObjectProperty=selectedShapeObject.grid.getStore().getRange();
if(selectedShapeObject.shapeSelection.shapes.length==1){prevGridTitle=selectedShapeObject.shapeSelection.shapes.first().getStencil().title()
}}get ;
ORYX.Editor.pasteShapeProperties=function(){var a="";
if(selectedShapeObject.shapeSelection.shapes.length==1){a=selectedShapeObject.shapeSelection.shapes.first().getStencil().title()
}if(selectedShapeObjectProperty!=undefined){if(a==prevGridTitle){var e=selectedShapeObjectProperty;
for(var c=0;
c<e.length;
c++){var d=e[c]["data"]["name"];
if(ORYX.I18N.PropertyWindow.form==d||ORYX.I18N.PropertyWindow.organizer==d||ORYX.I18N.PropertyWindow.coordinator==d||ORYX.I18N.PropertyWindow.reader==d||ORYX.I18N.PropertyWindow.creator==d||ORYX.I18N.PropertyWindow.processedUser==d||ORYX.I18N.PropertyWindow.workFlowAdmin==d||ORYX.I18N.PropertyWindow.ofOrganizer==d||ORYX.I18N.PropertyWindow.ofCoordinator==d||ORYX.I18N.PropertyWindow.ofReader==d||ORYX.I18N.PropertyWindow.ofCreator==d||ORYX.I18N.PropertyWindow.ofProcessedUser==d||ORYX.I18N.PropertyWindow.ofWorkFlowAdmin==d){var b={column:1,field:"value",grid:selectedShapeObject.grid,originalValue:"",record:e[c],row:c,value:e[c]["data"]["value"]};
selectedShapeObject.afterEdit(b)
}}}else{Ext.Msg.show({title:ORYX.I18N.ERDFSupport.pasteProperyTitle,msg:ORYX.I18N.ERDFSupport.pasteProperyText,icon:Ext.MessageBox.ERROR})
}}else{Ext.Msg.show({title:ORYX.I18N.ERDFSupport.pasteProperyTitle,msg:ORYX.I18N.ERDFSupport.pasteProperyTextWarning,icon:Ext.MessageBox.ERROR})
}}get ;
var conditionExpressionId="";
var globalVariableFromValue="";
var jspForm;
var baseForm;
var formIdMap={};
var isStartNode="false";
var isForStartNodeType=false;
ORYX.Plugins.PropertyWindow={facade:undefined,construct:function(a){this.facade=a;
this.facade.registerOnEvent(ORYX.CONFIG.EVENT_SHOW_PROPERTYWINDOW,this.init.bind(this));
this.facade.registerOnEvent(ORYX.CONFIG.EVENT_LOADED,this.selectDiagram.bind(this));
this.init()
}get ,init:function(){this.node=ORYX.Editor.graft("http://www.w3.org/1999/xhtml",null,["div"]);
this.currentDateFormat;
this.popularProperties=[];
this.properties=[];
this.shapeSelection=new Hash();
this.shapeSelection.shapes=new Array();
this.shapeSelection.commonProperties=new Array();
this.shapeSelection.commonPropertiesValues=new Hash();
this.updaterFlag=false;
this.columnModel=new Ext.grid.ColumnModel([{header:ORYX.I18N.PropertyWindow.name,dataIndex:"name",width:90,sortable:true,renderer:this.tooltipRenderer.bind(this)},{header:ORYX.I18N.PropertyWindow.value,dataIndex:"value",id:"propertywindow_column_value",width:110,editor:new Ext.form.TextField({allowBlank:false}),renderer:this.renderer.bind(this)},{header:"Pop",dataIndex:"popular",hidden:true,sortable:true}]);
this.dataSource=new Ext.data.GroupingStore({proxy:new Ext.data.MemoryProxy(this.properties),reader:new Ext.data.ArrayReader({},[{name:"popular"},{name:"name"},{name:"value"},{name:"icons"},{name:"gridProperties"}]),sortInfo:{field:"popular",direction:"ASC"},sortData:function(c,d){d=d||"ASC";
var a=this.fields.get(c).sortType;
var b=function(f,e){var l=a(f.data[c]),k=a(e.data[c]);
var h=f.data.popular,g=e.data.popular;
return h&&!g?-1:(!h&&g?1:(l>k?1:(l<k?-1:0)))
}get ;
this.data.sort(d,b);
if(this.snapshot&&this.snapshot!=this.data){this.snapshot.sort(d,b)
}}get ,groupField:"popular"});
this.dataSource.load();
this.grid=new Ext.grid.EditorGridPanel({clicksToEdit:1,stripeRows:true,autoExpandColumn:"propertywindow_column_value",width:"auto",colModel:this.columnModel,enableHdMenu:false,view:new Ext.grid.GroupingView({forceFit:true,groupTextTpl:"{[values.rs.first().data.popular ? ORYX.I18N.PropertyWindow.oftenUsed : ORYX.I18N.PropertyWindow.moreProps]}"}),store:this.dataSource});
region=this.facade.addToRegion("east",new Ext.Panel({width:220,layout:"fit",border:false,title:"Properties",items:[this.grid]}),ORYX.I18N.PropertyWindow.title);
this.grid.on("beforeedit",this.beforeEdit,this,true);
this.grid.on("afteredit",this.afterEdit,this,true);
this.grid.view.on("refresh",this.hideMoreAttrs,this,true);
this.grid.enableColumnMove=false
}get ,selectDiagram:function(){this.shapeSelection.shapes=[this.facade.getCanvas()];
this.setPropertyWindowTitle();
this.identifyCommonProperties();
this.createProperties();
this.setFormVersionChange()
}get ,specialKeyDown:function(b,a){if(b instanceof Ext.form.TextArea&&a.button==ORYX.CONFIG.KEY_Code_enter){return false
}}get ,tooltipRenderer:function(b,c,a){c.cellAttr='title="'+a.data.gridProperties.tooltip+'"';
return b
}get ,renderer:function(e,f,b){this.tooltipRenderer(e,f,b);
if(e instanceof Date){e=e.dateFormat(ORYX.I18N.PropertyWindow.dateFormat)
}else{if(String(e).search("<a href='")<0){e=String(e).gsub("<","&lt;");
e=String(e).gsub(">","&gt;");
e=String(e).gsub("%","&#37;");
e=String(e).gsub("&","&amp;");
if(b.data.gridProperties.type==ORYX.CONFIG.TYPE_COLOR){e="<div class='prop-background-color' style='background-color:"+e+"' />"
}else{if(b.data.gridProperties.type==ORYX.CONFIG.TYPE_RUNTIMECOMBO){if(e!=0&&e!=""){jspForm=true;
if(previousFormId==""||previousFormId==null){previousFormId=e
}if(formStore.find("id",e)!=null){var d=formStore.find("id",e);
if(d!=-1){var a=formStore.getAt(d);
return a.get("formName")
}else{var c=formStore.findBy(function(g,h){if(g.get("formName")==formIdMap[e]){return g.get("formName")
}}get );
var a=formStore.getAt(c);
if(a!=undefined){return a.get("formName")
}else{return""
}}}else{return e
}}else{jspForm=false;
return""
}}else{if(b.data.gridProperties.type==ORYX.CONFIG.TYPE_NEWJSPFORM){if(e!=0&&e!=""){baseForm=true;
if(previousFormId==""||previousFormId==null){previousFormId=e
}if(newFormStore.find("id",e)!=null){var d=newFormStore.find("id",e);
if(d!=-1){var a=newFormStore.getAt(d);
return a.get("formName")
}else{var c=newFormStore.findBy(function(g,h){if(g.get("formName")==formIdMap[e]){return g.get("formName")
}}get );
var a=newFormStore.getAt(c);
if(a!=undefined){return a.get("formName")
}else{return""
}}}else{return e
}}else{baseForm=false;
return""
}}else{if(b.data.gridProperties.type==ORYX.CONFIG.TYPE_CUSTOMTREESELECT){}}}}b.data.icons.each(function(g){if(g.name==e){if(g.icon){e="<img src='"+g.icon+"' /> "+e
}}}get )
}}return e
}get ,beforeEdit:function(c){var d=this.dataSource.getAt(c.row).data.gridProperties.editor;
var a=this.dataSource.getAt(c.row).data.gridProperties.renderer;
if(d){this.facade.disableEvent(ORYX.CONFIG.EVENT_KEYDOWN);
c.grid.getColumnModel().setEditor(1,d);
d.field.row=c.row;
d.render(this.grid);
d.setSize(c.grid.getColumnModel().getColumnWidth(1),d.height)
}else{return false
}var b=this.dataSource.getAt(c.row).data.gridProperties.propId;
this.oldValues=new Hash();
this.shapeSelection.shapes.each(function(e){this.oldValues[e.getId()]=e.properties[b];
conditionExpressionId=e.getId()
}get .bind(this))
}get ,afterEdit:function(e){e.grid.getStore().commitChanges();
var c=e.record.data.gridProperties.propId;
var h=this.shapeSelection.shapes;
var b=this.oldValues;
var f=e.value;
var d=this.facade;
var a=ORYX.Core.Command.extend({construct:function(){this.key=c;
this.selectedElements=h;
this.oldValues=b;
this.newValue=f;
this.facade=d
}get ,execute:function(){this.selectedElements.each(function(k){if(!k.getStencil().property(this.key).readonly()){k.setProperty(this.key,this.newValue)
}}get .bind(this));
this.facade.setSelection(this.selectedElements);
this.facade.getCanvas().update();
this.facade.updateSelection()
}get ,rollback:function(){this.selectedElements.each(function(k){k.setProperty(this.key,this.oldValues[k.getId()])
}get .bind(this));
this.facade.setSelection(this.selectedElements);
this.facade.getCanvas().update();
this.facade.updateSelection()
}get });
var g=new a();
this.facade.executeCommands([g]);
this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_PROPWINDOW_PROP_CHANGED,elements:h,key:c,value:e.value})
}get ,editDirectly:function(a,b){this.shapeSelection.shapes.each(function(d){if(!d.getStencil().property(a).readonly()){d.setProperty(a,b)
}}get .bind(this));
var c=this.shapeSelection.shapes;
this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_PROPWINDOW_PROP_CHANGED,elements:c,key:a,value:b});
this.facade.getCanvas().update()
}get ,updateAfterInvalid:function(a){this.shapeSelection.shapes.each(function(b){if(!b.getStencil().property(a).readonly()){b.setProperty(a,this.oldValues[b.getId()]);
b.update()
}}get .bind(this));
this.facade.getCanvas().update()
}get ,dialogClosed:function(a){var b=this.field?this.field.row:this.row;
this.scope.afterEdit({grid:this.scope.grid,record:this.scope.grid.getStore().getAt(b),value:a});
this.scope.grid.startEditing(b,this.col)
}get ,setPropertyWindowTitle:function(){if(this.shapeSelection.shapes.length==1){region.setTitle(ORYX.I18N.PropertyWindow.title+" ("+this.shapeSelection.shapes.first().getStencil().title()+")")
}else{region.setTitle(ORYX.I18N.PropertyWindow.title+" ("+this.shapeSelection.shapes.length+" "+ORYX.I18N.PropertyWindow.selected+")")
}}get ,setCommonPropertiesValues:function(){this.shapeSelection.commonPropertiesValues=new Hash();
this.shapeSelection.commonProperties.each(function(d){var c=d.prefix()+"-"+d.id();
var b=false;
var a=this.shapeSelection.shapes.first();
this.shapeSelection.shapes.each(function(e){if(a.properties[c]!=e.properties[c]){b=true
}}get .bind(this));
if(!b){if(c=="oryx-operationcreator"){if(a.properties[c]){this.shapeSelection.commonPropertiesValues[c]=a.properties[c]
}else{this.shapeSelection.commonPropertiesValues[c]=""
}}else{this.shapeSelection.commonPropertiesValues[c]=a.properties[c]
}}}get .bind(this))
}get ,getStencilSetOfSelection:function(){var a=new Hash();
this.shapeSelection.shapes.each(function(b){a[b.getStencil().id()]=b.getStencil()
}get );
return a
}get ,identifyCommonProperties:function(){this.shapeSelection.commonProperties.clear();
var d=this.getStencilSetOfSelection();
var c=d.values().first();
var b=d.values().without(c);
if(b.length==0){this.shapeSelection.commonProperties=c.properties()
}else{var a=new Hash();
c.properties().each(function(e){a[e.namespace()+"-"+e.id()+"-"+e.type()]=e
}get );
b.each(function(e){var f=new Hash();
e.properties().each(function(g){if(a[g.namespace()+"-"+g.id()+"-"+g.type()]){f[g.namespace()+"-"+g.id()+"-"+g.type()]=g
}}get );
a=f
}get );
this.shapeSelection.commonProperties=a.values()
}}get ,onSelectionChanged:function(c){this.grid.stopEditing();
selectedShapeObject=this;
this.shapeSelection.shapes=c.elements;
baseForm=false;
jspForm=false;
if(c.elements!=null&&c.elements[0]!=null){var b=JSON.parse(c.elements[0].properties.toJSON().replace(/-/g,"$").toString());
if(b.oryx$form!=null&&b.oryx$form!=""&&b.oryx$form.length>0){baseForm=false;
jspForm=true
}else{if(b.oryx$jspform!=null&&b.oryx$jspform!=""&&b.oryx$jspform.length>0){jspForm=false;
baseForm=true
}}}isForStartNodeType=false;
if(c.elements!=null&&c.elements[0]!=null&&c.elements[0].incoming[0]!=null&&c.elements[0].incoming[0].incoming[0]!=null&&c.elements[0].incoming[0].incoming[0].toJSON().stencil.id=="StartNoneEvent"){isStartNode="true";
isForStartNodeType=true
}if(c.elements.length==0){this.shapeSelection.shapes=[this.facade.getCanvas()]
}if(c.subSelection){this.shapeSelection.shapes=[c.subSelection]
}this.setPropertyWindowTitle();
this.identifyCommonProperties();
this.setCommonPropertiesValues();
this.createProperties();
if(this.shapeSelection.shapes.length==1){var a=this.shapeSelection.shapes.first().getStencil().title();
if(a==ORYX.I18N.PropertyWindow.task){this.setPreDefinedProperties()
}else{if(a==ORYX.I18N.PropertyWindow.startEvent){this.setPreDefinedStartEventProperties()
}}}}get ,setPreDefinedStartEventProperties:function(){var e=this.grid.getStore().getRange();
for(var b=0;
b<e.length;
b++){var c=e[b]["data"]["name"];
if(c==ORYX.I18N.PropertyWindow.urgeMessage){var d=e[b]["data"]["value"];
if(d==""){var a={column:1,field:"value",grid:this.grid,originalValue:"",record:e[b],row:b,value:"There is a workflow you need to sign off, workflow name is <workFolwName>, created by <createdBy> at <createdAt>, plz click this link <url> to operate, thank you."};
this.afterEdit(a)
}}else{if(c==ORYX.I18N.PropertyWindow.notificationMessage){var d=e[b]["data"]["value"];
if(d==""){var a={column:1,field:"value",grid:this.grid,originalValue:"",record:e[b],row:b,value:"There is a workflow need to sign off by organizer : <organizerName>  Workflow name is <workFolwName>"};
this.afterEdit(a)
}}}}}get ,setPreDefinedProperties:function(){var g=this.grid.getStore().getRange();
for(var d=0;
d<g.length;
d++){var e=g[d]["data"]["name"];
if(ORYX.I18N.PropertyWindow.ofCreator==e){var f=g[d]["data"]["value"];
if(f==""){var c={column:1,field:"value",grid:this.grid,originalValue:"",record:g[d],row:d,value:"[{'totalCount':2, 'items':[{resource_type:'withdraw_elm',resource:'Withdraw',resourceassignmentexpr:'Default Operation',language:'',evaluatestotype:''}, {resource_type:'urge_elm',resource:'Urge',resourceassignmentexpr:'Default Operation',language:'',evaluatestotype:''}]}]"};
this.afterEdit(c)
}}else{if(ORYX.I18N.PropertyWindow.ofCoordinator==e){var f=g[d]["data"]["value"];
if(f==""){var c={column:1,field:"value",grid:this.grid,originalValue:"",record:g[d],row:d,value:"[{'totalCount':1, 'items':[{resource_type:'save_elm',resource:'Save',resourceassignmentexpr:'Default Operation',language:'',evaluatestotype:''}]}]"};
this.afterEdit(c)
}}else{if(ORYX.I18N.PropertyWindow.ofReader==e){var f=g[d]["data"]["value"];
if(f==""){var c={column:1,field:"value",grid:this.grid,originalValue:"",record:g[d],row:d,value:"[{'totalCount':1, 'items':[{resource_type:'circulateperusal_elm',resource:'Circulate Perusal',resourceassignmentexpr:'Default Operation',language:'',evaluatestotype:''}]}]"};
this.afterEdit(c)
}}else{if(ORYX.I18N.PropertyWindow.ofProcessedUser==e){var f=g[d]["data"]["value"];
if(f==""){var c={column:1,field:"value",grid:this.grid,originalValue:"",record:g[d],row:d,value:"[{'totalCount':1, 'items':[{resource_type:'recall_elm',resource:'Recall',resourceassignmentexpr:'Default Operation',language:'',evaluatestotype:''}]}]"};
this.afterEdit(c)
}}else{if(ORYX.I18N.PropertyWindow.ofWorkFlowAdmin==e){var f=g[d]["data"]["value"];
if(f==""){var c={column:1,field:"value",grid:this.grid,originalValue:"",record:g[d],row:d,value:"[{'totalCount':5, 'items':[{resource_type:'jump_elm',resource:'Jump',resourceassignmentexpr:'Default Operation',language:'',evaluatestotype:''}, {resource_type:'transactorreplacement_elm',resource:'TransactorReplacement',resourceassignmentexpr:'Default Operation',language:'',evaluatestotype:''}, {resource_type:'terminate_elm',resource:'Terminate',resourceassignmentexpr:'Default Operation',language:'',evaluatestotype:''}, {resource_type:'suspend_elm',resource:'Suspend',resourceassignmentexpr:'Default Operation',language:'',evaluatestotype:''}, {resource_type:'print_elm',resource:'Print',resourceassignmentexpr:'Default Operation',language:'',evaluatestotype:''}]}]"};
this.afterEdit(c)
}}else{if(ORYX.I18N.PropertyWindow.ofOrganizer==e){var f=g[d]["data"]["value"];
if(f==""){var c={column:1,field:"value",grid:this.grid,originalValue:"",record:g[d],row:d,value:"[{'totalCount':3, 'items':[{resource_type:'submit_elm',resource:'submit',resourceassignmentexpr:'Default Operation',language:'',evaluatestotype:''}, {resource_type:'save_elm',resource:'Save',resourceassignmentexpr:'Default Operation',language:'',evaluatestotype:''}, {resource_type:'returnoperation_elm',resource:'Return Operation',resourceassignmentexpr:'Default Operation',language:'',evaluatestotype:''}]}]"};
this.afterEdit(c)
}}else{if(ORYX.I18N.PropertyWindow.isStartNode==e){var f=g[d]["data"]["value"];
if(f==""||isStartNode!=f){var c={column:1,field:"value",grid:this.grid,originalValue:"",record:g[d],row:d,value:isStartNode};
this.afterEdit(c)
}isStartNode="false"
}else{if(ORYX.I18N.PropertyWindow.form==e){var f=g[d]["data"]["value"];
var b=formStore.findBy(function(h,k){if(h.get("formName")==formIdMap[f]){return h.get("formName")
}}get );
var a=formStore.getAt(b);
if(a!=undefined){if(f==""||a.get("id")!=f){var c={column:1,field:"value",grid:this.grid,originalValue:"",record:g[d],row:d,value:a.get("id")};
this.afterEdit(c)
}}}else{if(ORYX.I18N.PropertyWindow.jspForm==e){var f=g[d]["data"]["value"];
var b=newFormStore.findBy(function(h,k){if(h.get("formName")==formIdMap[f]){return h.get("formName")
}}get );
var a=newFormStore.getAt(b);
if(a!=undefined){if(f==""||a.get("id")!=f){var c={column:1,field:"value",grid:this.grid,originalValue:"",record:g[d],row:d,value:a.get("id")};
this.afterEdit(c)
}}}}}}}}}}}}}get ,setFormVersionChange:function(){var a=[];
this.shapeSelection.shapes.each(function(d){var f=d.children;
for(var e=0;
e<f.length;
e++){if(f[e].toJSON().stencil.id==ORYX.I18N.PropertyWindow.task){a.push(f[e])
}}}get );
if(a.length>1){for(var c=0;
c<a.length;
c++){var b=[];
b.push(a[c]);
this.setVersionChangeForDynamicTask(b)
}}else{if(a.length>0){var b=[];
b=a;
this.setVersionChangeForDynamicTask(b)
}}}get ,setVersionChangeForDynamicTask:function(b){this.grid.stopEditing();
selectedShapeObject=this;
this.shapeSelection.shapes=b;
if(b.subSelection){this.shapeSelection.shapes=[b[0].subSelection]
}this.setPropertyWindowTitle();
this.identifyCommonProperties();
this.setCommonPropertiesValues();
this.createProperties();
if(this.shapeSelection.shapes.length==1){var a=this.shapeSelection.shapes.first().getStencil().title();
if(a==ORYX.I18N.PropertyWindow.task){this.setPreDefinedProperties()
}else{if(a==ORYX.I18N.PropertyWindow.startEvent){this.setPreDefinedStartEventProperties()
}}}}get ,createProperties:function(){this.properties=[];
this.popularProperties=[];
if(this.shapeSelection.commonProperties){this.shapeSelection.commonProperties.each((function(A,o){var J=A.prefix()+"-"+A.id();
var K=A.title();
var G=[];
var u=this.shapeSelection.commonPropertiesValues[J];
var a=undefined;
var c=null;
var w=false;
var t=false;
if(!A.readonly()){var n=this.shapeSelection.shapes[0];
if(n.incoming!=undefined&&n.incoming[0]!=undefined&&n.incoming[0].incoming!=undefined&&n.incoming[0].incoming[0]!=undefined){if(n.incoming[0].incoming[0].toJSON().stencil.id=="StartNoneEvent"){var b=checkFirstNodeTaskProperty(A.type(),A.title());
if(b!=true){w=true
}}}switch(A.type()){case ORYX.CONFIG.TYPE_STRING:if(A.wrapLines()){var k=new Ext.form.TextArea({alignment:"tl-tl",allowBlank:A.optional(),msgTarget:"title",maxLength:A.length()});
k.on("keyup",function(M,L){this.editDirectly(J,M.getValue())
}get .bind(this));
a=new Ext.Editor(k)
}else{var q=new Ext.form.TextField({allowBlank:A.optional(),msgTarget:"title",maxLength:A.length()});
q.on("keyup",function(L,M){this.editDirectly(J,L.getValue())
}get .bind(this));
q.on("blur",function(L){if(!L.isValid(false)){this.updateAfterInvalid(J)
}}get .bind(this));
q.on("specialkey",function(L,M){if(!L.isValid(false)){this.updateAfterInvalid(J)
}}get .bind(this));
a=new Ext.Editor(q)
}break;
case ORYX.CONFIG.TYPE_BOOLEAN:var I=new Ext.form.Checkbox({disabled:w});
I.on("check",function(M,L){this.editDirectly(J,L)
}get .bind(this));
a=new Ext.Editor(I);
break;
case ORYX.CONFIG.TYPE_INTEGER:var e=new Ext.form.NumberField({allowBlank:A.optional(),allowDecimals:false,msgTarget:"title",minValue:A.min(),maxValue:A.max(),disabled:w});
e.on("keyup",function(L,M){this.editDirectly(J,L.getValue())
}get .bind(this));
a=new Ext.Editor(e);
break;
case ORYX.CONFIG.TYPE_FLOAT:var e=new Ext.form.NumberField({allowBlank:A.optional(),allowDecimals:true,msgTarget:"title",minValue:A.min(),maxValue:A.max(),disabled:w});
e.on("keyup",function(L,M){this.editDirectly(J,L.getValue())
}get .bind(this));
a=new Ext.Editor(e);
break;
case ORYX.CONFIG.TYPE_COLOR:var E=new Ext.ux.ColorField({allowBlank:A.optional(),msgTarget:"title",facade:this.facade,disabled:w});
a=new Ext.Editor(E);
break;
case ORYX.CONFIG.TYPE_CHOICE:var z=A.items();
var g=[];
z.each(function(L){if(L.value()==u){u=L.title()
}if(L.refToView()[0]){t=true
}g.push([L.icon(),L.title(),L.value()]);
G.push({name:L.title(),icon:L.icon()})
}get );
var m=new Ext.data.SimpleStore({fields:[{name:"icon"},{name:"title"},{name:"value"}],data:g});
var D=new Ext.form.ComboBox({tpl:'<tpl for="."><div class="x-combo-list-item">{[(values.icon) ? "<img src=\'" + values.icon + "\' />" : ""]} {title}</div></tpl>',store:m,displayField:"title",valueField:"value",typeAhead:true,mode:"local",triggerAction:"all",selectOnFocus:true,disabled:isForStartNodeType,});
D.on("select",function(N,L,M){this.editDirectly(J,N.getValue())
}get .bind(this));
a=new Ext.Editor(D);
break;
case ORYX.CONFIG.TYPE_DATE:var x=ORYX.I18N.PropertyWindow.dateFormat;
if(!(u instanceof Date)){u=Date.parseDate(u,x)
}a=new Ext.Editor(new Ext.form.DateField({allowBlank:A.optional(),format:x,msgTarget:"title",disabled:w}));
break;
case ORYX.CONFIG.TYPE_TEXT:var v=new Ext.form.ComplexTextField({allowBlank:A.optional(),dataSource:this.dataSource,grid:this.grid,row:o,facade:this.facade,disabled:w});
v.on("dialogClosed",this.dialogClosed,{scope:this,row:o,col:1,field:v});
a=new Ext.Editor(v);
break;
case ORYX.CONFIG.TYPE_COMPLEX:var v=new Ext.form.ComplexListField({allowBlank:A.optional(),disabled:w},A.complexItems(),J,this.facade);
v.on("dialogClosed",this.dialogClosed,{scope:this,row:o,col:1,field:v});
a=new Ext.Editor(v);
break;
case"CPNString":var q=new Ext.form.TextField({allowBlank:A.optional(),msgTarget:"title",maxLength:A.length(),enableKeyEvents:true,disabled:w});
q.on("keyup",function(L,M){this.editDirectly(J,L.getValue());
alert("huhu")
}get .bind(this));
a=new Ext.Editor(q);
break;
case ORYX.CONFIG.TYPE_RUNTIMECOMBO:var B=new Ext.form.ComboBox({store:formStore,displayField:"formName",valueField:"id",typeAhead:true,mode:"local",lazyRender:true,value:"formName",triggerAction:"all",selectOnFocus:true,disabled:baseForm,});
B.on("select",function(N,L,M){this.editDirectly(J,N.getValue());
globalVariableFromValue=N.getValue();
jspForm=true
}get .bind(this));
a=new Ext.Editor(B);
break;
case ORYX.CONFIG.TYPE_ADDPROCESS:var r=new Ext.form.ComboBox({store:processStore,displayField:"processName",valueField:"id",typeAhead:true,mode:"local",lazyRender:true,value:"processName",triggerAction:"all",selectOnFocus:true,});
r.on("select",function(N,L,M){this.editDirectly(J,N.getValue());
globalVariableFromValue=N.getValue()
}get .bind(this));
a=new Ext.Editor(r);
break;
case ORYX.CONFIG.TYPE_NEWJSPFORM:var B=new Ext.form.ComboBox({store:newFormStore,displayField:"formName",valueField:"id",typeAhead:true,mode:"local",lazyRender:true,value:"formName",triggerAction:"all",selectOnFocus:true,disabled:jspForm,});
B.on("select",function(N,L,M){this.editDirectly(J,N.getValue());
globalVariableFromValue=N.getValue();
baseForm=true
}get .bind(this));
a=new Ext.Editor(B);
break;
case ORYX.CONFIG.TYPE_REPEATEDOPERATION:var l=new Ext.data.SimpleStore({fields:["id","formName"],data:[["true","True"],["false","False"]]});
var F=new Ext.form.ComboBox({store:l,displayField:"formName",valueField:"id",typeAhead:true,mode:"local",lazyRender:true,value:"formName",triggerAction:"all",selectOnFocus:true,disabled:w,});
F.on("select",function(N,L,M){this.editDirectly(J,N.getValue());
globalVariableFromValue=N.getValue()
}get .bind(this));
a=new Ext.Editor(F);
break;
case ORYX.CONFIG.TYPE_EMPTYPERSONNEL:var C=new Ext.data.SimpleStore({fields:["id","formName"],data:[["true","True"],["false","False"]]});
var p=new Ext.form.ComboBox({store:C,displayField:"formName",valueField:"id",typeAhead:true,mode:"local",lazyRender:true,value:"formName",triggerAction:"all",selectOnFocus:true,disabled:w,});
p.on("select",function(N,L,M){this.editDirectly(J,N.getValue());
globalVariableFromValue=N.getValue()
}get .bind(this));
a=new Ext.Editor(p);
break;
case ORYX.CONFIG.TYPE_ISFORSTARTNODE:var y=new Ext.form.TextField({allowBlank:false,disabled:true});
a=new Ext.Editor(y);
break;
case ORYX.CONFIG.TYPE_ISARCHIVE:var s=new Ext.data.SimpleStore({fields:["id","formName"],data:[["true","True"],["false","False"]]});
var H=new Ext.form.ComboBox({store:s,displayField:"formName",valueField:"id",typeAhead:true,mode:"local",lazyRender:true,value:"formName",triggerAction:"all",selectOnFocus:true,disabled:w});
H.on("select",function(N,L,M){this.editDirectly(J,N.getValue());
globalVariableFromValue=N.getValue()
}get .bind(this));
a=new Ext.Editor(H);
break;
case ORYX.CONFIG.TYPE_READEROPTIONTYPE:var f=new Ext.data.SimpleStore({fields:["id","formName"],data:[["onlyreader","Only Reader"],["allstackholder","All Stackholder"]]});
var h=new Ext.form.ComboBox({store:f,displayField:"formName",valueField:"id",typeAhead:true,mode:"local",lazyRender:true,value:"formName",triggerAction:"all",selectOnFocus:true,disabled:w});
h.on("select",function(N,L,M){this.editDirectly(J,N.getValue());
globalVariableFromValue=N.getValue()
}get .bind(this));
a=new Ext.Editor(h);
break;
case ORYX.CONFIG.TYPE_ADDOPINION:var d=new Ext.data.SimpleStore({fields:["id","formName"],data:[["true","True"],["false","False"]]});
var H=new Ext.form.ComboBox({store:d,displayField:"formName",valueField:"id",typeAhead:true,mode:"local",lazyRender:true,value:"true",triggerAction:"all",selectOnFocus:true,disabled:w});
H.on("select",function(N,L,M){this.editDirectly(J,N.getValue());
globalVariableFromValue=N.getValue()
}get .bind(this));
a=new Ext.Editor(H);
break;
case ORYX.CONFIG.TYPE_PROCESSTIMESETTING:var v=new Ext.form.ProcessTimeSetting({allowBlank:false,disabled:w},A.complexItems(),J,this.facade,this.grid);
v.on("dialogClosed",this.dialogClosed,{scope:this,row:o,col:1,field:v});
a=new Ext.Editor(v);
break;
case ORYX.CONFIG.TYPE_FORMFIELDAUTOMATIC:var v=new Ext.form.FormFieldAutomatic({allowBlank:false,disabled:w},A.complexItems(),J,this.facade,this.grid);
v.on("dialogClosed",this.dialogClosed,{scope:this,row:o,col:1,field:v});
a=new Ext.Editor(v);
break;
case ORYX.CONFIG.TYPE_ONREAD:var v=new Ext.form.OnRead({allowBlank:A.optional(),dataSource:this.dataSource,grid:this.grid,row:o,facade:this.facade,disabled:w});
v.on("dialogClosed",this.dialogClosed,{scope:this,row:o,col:1,field:v});
a=new Ext.Editor(v);
break;
case ORYX.CONFIG.TYPE_ONUPDATE:var v=new Ext.form.OnUpdate({allowBlank:A.optional(),dataSource:this.dataSource,grid:this.grid,row:o,facade:this.facade,disabled:w});
v.on("dialogClosed",this.dialogClosed,{scope:this,row:o,col:1,field:v});
a=new Ext.Editor(v);
break;
case ORYX.CONFIG.TYPE_ONCREATE:var v=new Ext.form.OnCreate({allowBlank:A.optional(),dataSource:this.dataSource,grid:this.grid,row:o,facade:this.facade,});
v.on("dialogClosed",this.dialogClosed,{scope:this,row:o,col:1,field:v});
a=new Ext.Editor(v);
break;
case ORYX.CONFIG.TYPE_CUSTOMTREESELECT:var v=new Ext.form.CustomTreeSelect({allowBlank:false},A.complexItems(),J,this.facade,this.grid);
v.on("dialogClosed",this.dialogClosed,{scope:this,row:o,col:1,field:v});
a=new Ext.Editor(v);
break;
case ORYX.CONFIG.TYPE_CHOOSECONDITION:var v=new Ext.form.ChooseCondition({allowBlank:false},A.complexItems(),J,this.facade,this.grid);
v.on("dialogClosed",this.dialogClosed,{scope:this,row:o,col:1,field:v});
a=new Ext.Editor(v);
break;
case ORYX.CONFIG.TYPE_TRANSACTORRELATIONSHIP:var v=new Ext.form.TransactorRelationship({allowBlank:false},A.complexItems(),J,this.facade,this.grid);
v.on("dialogClosed",this.dialogClosed,{scope:this,row:o,col:1,field:v});
a=new Ext.Editor(v);
break;
case ORYX.CONFIG.TYPE_FORMFIELDPERMISSION:var v=new Ext.form.FormFieldPermission({allowBlank:false,disabled:w},A.complexItems(),J,this.facade,this.grid);
v.on("dialogClosed",this.dialogClosed,{scope:this,row:o,col:1,field:v});
a=new Ext.Editor(v);
break;
case ORYX.CONFIG.TYPE_FORMMEMBERPERMISSION:var v=new Ext.form.FormMemberPermission({allowBlank:false,disabled:w},A.complexItems(),J,this.facade,this.grid);
v.on("dialogClosed",this.dialogClosed,{scope:this,row:o,col:1,field:v});
a=new Ext.Editor(v);
break;
case ORYX.CONFIG.TYPE_MEMBERPERMISSION:var v=new Ext.form.MemberPermission({allowBlank:false,disabled:w},A.complexItems(),J,this.facade,this.grid);
v.on("dialogClosed",this.dialogClosed,{scope:this,row:o,col:1,field:v});
a=new Ext.Editor(v);
break;
case ORYX.CONFIG.TYPE_MEMBERPERMISSIONPROCESS:var v=new Ext.form.MemberPermissionProcess({allowBlank:false,disabled:w},A.complexItems(),J,this.facade,this.grid);
v.on("dialogClosed",this.dialogClosed,{scope:this,row:o,col:1,field:v});
a=new Ext.Editor(v);
break;
case ORYX.CONFIG.TYPE_STARTPERMISSION:var v=new Ext.form.StartPermission({allowBlank:false,disabled:w},A.complexItems(),J,this.facade,this.grid);
v.on("dialogClosed",this.dialogClosed,{scope:this,row:o,col:1,field:v});
a=new Ext.Editor(v);
break;
case ORYX.CONFIG.TYPE_URGEMESSAGE:var v=new Ext.form.UrgeMessage({allowBlank:A.optional(),dataSource:this.dataSource,grid:this.grid,row:o,facade:this.facade});
v.on("dialogClosed",this.dialogClosed,{scope:this,row:o,col:1,field:v});
a=new Ext.Editor(v);
break;
case ORYX.CONFIG.TYPE_NOTIFICATIONMESSAGE:var v=new Ext.form.NotificationMessage({allowBlank:A.optional(),dataSource:this.dataSource,grid:this.grid,row:o,facade:this.facade});
v.on("dialogClosed",this.dialogClosed,{scope:this,row:o,col:1,field:v});
a=new Ext.Editor(v);
break;
case ORYX.CONFIG.TYPE_MEMBERPERMISSIONEND:var v=new Ext.form.MemberPermissionEnd({allowBlank:false,disabled:w},A.complexItems(),J,this.facade,this.grid);
v.on("dialogClosed",this.dialogClosed,{scope:this,row:o,col:1,field:v});
a=new Ext.Editor(v);
break;
case ORYX.CONFIG.TYPE_OPERATIONPERMISSION:var v=new Ext.form.OperationPermission({allowBlank:false,disabled:w},A.complexItems(),J,this.facade,this.grid);
v.on("dialogClosed",this.dialogClosed,{scope:this,row:o,col:1,field:v});
a=new Ext.Editor(v);
break;
case ORYX.CONFIG.TYPE_STARTNODESCRIPT:var v=new Ext.form.StartNodeScript({allowBlank:false},A.complexItems(),J,this.facade,this.grid);
v.on("dialogClosed",this.dialogClosed,{scope:this,row:o,col:1,field:v});
a=new Ext.Editor(v);
break;
case ORYX.CONFIG.TYPE_ENDNODESCRIPT:var v=new Ext.form.EndNodeScript({allowBlank:false},A.complexItems(),J,this.facade,this.grid);
v.on("dialogClosed",this.dialogClosed,{scope:this,row:o,col:1,field:v});
a=new Ext.Editor(v);
break;
case ORYX.CONFIG.TYPE_CREATECUSTOMOPERATION:var v=new Ext.form.CreateCustomOperation({allowBlank:false,disabled:w},A.complexItems(),J,this.facade,this.grid);
v.on("dialogClosed",this.dialogClosed,{scope:this,row:o,col:1,field:v});
a=new Ext.Editor(v);
break;
default:var q=new Ext.form.TextField({allowBlank:A.optional(),msgTarget:"title",maxLength:A.length(),enableKeyEvents:true,disabled:w});
q.on("keyup",function(L,M){this.editDirectly(J,L.getValue())
}get .bind(this));
a=new Ext.Editor(q)
}a.on("beforehide",this.facade.enableEvent.bind(this,ORYX.CONFIG.EVENT_KEYDOWN));
a.on("specialkey",this.specialKeyDown.bind(this))
}else{if(A.type()===ORYX.CONFIG.TYPE_URL||A.type()===ORYX.CONFIG.TYPE_DIAGRAM_LINK){u=String(u).search("http")!==0?("http://"+u):u;
u="<a href='"+u+"' target='_blank'>"+u.split("://")[1]+"</a>"
}}if(A.visible()){if(A.popular()){A.setPopular()
}if(A.popular()){this.popularProperties.push([A.popular(),K,u,G,{editor:a,propId:J,type:A.type(),tooltip:A.description(),renderer:c}])
}else{this.properties.push([A.popular(),K,u,G,{editor:a,propId:J,type:A.type(),tooltip:A.description(),renderer:c}])
}}}get ).bind(this))
}this.setProperties()
}get ,hideMoreAttrs:function(a){if(this.properties.length<=0){return
}this.grid.view.toggleGroup(this.grid.view.getGroupId(this.properties[0][0]),false);
this.grid.view.un("refresh",this.hideMoreAttrs,this)
}get ,setProperties:function(){var a=this.popularProperties.concat(this.properties);
this.dataSource.loadData(a)
}get };
ORYX.Plugins.PropertyWindow=Clazz.extend(ORYX.Plugins.PropertyWindow);
var selectedUser="";
Ext.form.ComplexListField=function(b,a,c,d){Ext.form.ComplexListField.superclass.constructor.call(this,b);
this.items=a;
this.key=c;
this.facade=d
}get ;
Ext.extend(Ext.form.ComplexListField,Ext.form.TriggerField,{triggerClass:"x-form-complex-trigger",readOnly:true,emptyText:ORYX.I18N.PropertyWindow.clickIcon,buildValue:function(){var f=this.grid.getStore();
f.commitChanges();
if(f.getCount()==0){return""
}var d="[";
for(var c=0;
c<f.getCount();
c++){var e=f.getAt(c);
d+="{";
for(var a=0;
a<this.items.length;
a++){var b=this.items[a].id();
d+=b+":"+(""+e.get(b)).toJSON();
if(a<(this.items.length-1)){d+=", "
}}d+="}";
if(c<(f.getCount()-1)){d+=", "
}}d+="]";
d="{'totalCount':"+f.getCount().toJSON()+", 'items':"+d+"}";
return Object.toJSON(d.evalJSON())
}get ,getFieldKey:function(){return this.key
}get ,getValue:function(){if(this.grid){return this.buildValue()
}else{if(this.data==undefined){return""
}else{return this.data
}}}get ,setValue:function(a){if(a.length>0){if(this.data==undefined){this.data=a
}}}get ,keydownHandler:function(a){return false
}get ,dialogListeners:{show:function(){this.onFocus();
this.facade.registerOnEvent(ORYX.CONFIG.EVENT_KEYDOWN,this.keydownHandler.bind(this));
this.facade.disableEvent(ORYX.CONFIG.EVENT_KEYDOWN);
return
}get ,hide:function(){var a=this.dialogListeners;
this.dialog.un("show",a.show,this);
this.dialog.un("hide",a.hide,this);
this.dialog.destroy(true);
this.grid.destroy(true);
delete this.grid;
delete this.dialog;
this.facade.unregisterOnEvent(ORYX.CONFIG.EVENT_KEYDOWN,this.keydownHandler.bind(this));
this.facade.enableEvent(ORYX.CONFIG.EVENT_KEYDOWN);
this.fireEvent("dialogClosed",this.data);
Ext.form.ComplexListField.superclass.setValue.call(this,this.data)
}get },buildInitial:function(f,a){var b=new Hash();
for(var c=0;
c<a.length;
c++){var e=a[c].id();
b[e]=a[c].value()
}var d=Ext.data.Record.create(f);
return new d(b)
}get ,buildColumnModel:function(n){var k=[];
for(var d=0;
d<this.items.length;
d++){var a=this.items[d].id();
var e=this.items[d].name();
var b=this.items[d].width();
var h=this.items[d].type();
var f;
if(h==ORYX.CONFIG.TYPE_STRING){f=new Ext.form.TextField({allowBlank:this.items[d].optional(),width:b,hidden:true})
}else{if(h==ORYX.CONFIG.TYPE_CHOICE){var g=this.items[d].items();
var m=ORYX.Editor.graft("http://www.w3.org/1999/xhtml",n,["select",{style:"display:none"}]);
var l=new Ext.Template('<option value="{value}">{value}</option>');
g.each(function(o){l.append(m,{value:o.value()})
}get );
f=new Ext.form.ComboBox({typeAhead:true,triggerAction:"all",transform:m,lazyRender:true,msgTarget:"title",width:b,listeners:{blur:function(){var o=this.getValue();
var p=this.getRawValue()
}get ,select:function(s,o,p){var q=this.getValue();
var r=this.getRawValue();
selectedUser=q
}get ,}})
}else{if(h==ORYX.CONFIG.TYPE_BOOLEAN){f=new Ext.form.Checkbox({width:b})
}else{if(h=="runtimecombo"){var c=new Ext.data.JsonStore({fields:[{name:"id",mapping:"id"},{name:"name",mapping:"name"}],data:{result:[""]},root:"result"});
f=new Ext.form.ComboBox({store:c,displayField:"name",valueField:"id",typeAhead:true,mode:"local",lazyRender:true,value:"name",triggerAction:"all",selectOnFocus:true,listeners:{focus:function(o){if(selectedUser.toLowerCase()=="humanperformer"){Ext.Ajax.request({waitMsg:"Saving changes...",method:"GET",headers:{"Content-Type":"application/json"},url:"/admin/getAllUsers",failure:function(p){alert(ORYX.I18N.Feedback.failure)
}get ,success:function(q){var p=Ext.util.JSON.decode(q.responseText);
c.loadData(p)
}get })
}else{if(selectedUser.toLowerCase()=="potentialowner"){Ext.Ajax.request({waitMsg:"Saving changes...",method:"GET",headers:{"Content-Type":"application/json"},url:"/admin/getAllGroups",failure:function(p){alert(ORYX.I18N.Feedback.failure)
}get ,success:function(q){var p=Ext.util.JSON.decode(q.responseText);
c.loadData(p)
}get })
}}}get }})
}}}}k.push({id:a,header:e,dataIndex:a,resizable:true,editor:f,width:b})
}return new Ext.grid.ColumnModel(k)
}get ,afterEdit:function(a){a.grid.getStore().commitChanges()
}get ,beforeEdit:function(h){var a=this.grid.getView().getScrollState();
var b=h.column;
var p=h.row;
var e=this.grid.getColumnModel().config[b].id;
for(var g=0;
g<this.items.length;
g++){var o=this.items[g];
var m=o.disable();
if(m!=undefined){var n=this.grid.getStore().getAt(p).get(o.id());
for(var d=0;
d<m.length;
d++){var f=m[d];
if(f.value==n){for(var c=0;
c<f.items.length;
c++){var l=f.items[c];
if(l==e){this.grid.getColumnModel().getCellEditor(b,p).disable();
return
}}}}}}this.grid.getColumnModel().getCellEditor(b,p).enable()
}get ,onTriggerClick:function(){if(this.disabled){return
}if(this.dialog){return
}var dialogWidth=0;
var recordType=[];
for(var i=0;
i<this.items.length;
i++){var id=this.items[i].id();
var width=this.items[i].width();
var type=this.items[i].type();
if(type==ORYX.CONFIG.TYPE_CHOICE){type=ORYX.CONFIG.TYPE_STRING
}dialogWidth+=width;
recordType[i]={name:id,type:type}
}if(dialogWidth>800){dialogWidth=800
}dialogWidth+=22;
var data=this.data;
if(data==""){data="{}"
}var ds=new Ext.data.Store({proxy:new Ext.data.MemoryProxy(eval("("+data+")")),reader:new Ext.data.JsonReader({root:"items",totalProperty:"totalCount"},recordType)});
ds.load();
var cm=this.buildColumnModel();
this.grid=new Ext.grid.EditorGridPanel({store:ds,cm:cm,stripeRows:true,clicksToEdit:1,autoHeight:true,selModel:new Ext.grid.CellSelectionModel()});
var toolbar=new Ext.Toolbar([{text:ORYX.I18N.PropertyWindow.add,handler:function(){var ds=this.grid.getStore();
var index=ds.getCount();
this.grid.stopEditing();
var p=this.buildInitial(recordType,this.items);
ds.insert(index,p);
ds.commitChanges();
this.grid.startEditing(index,0)
}get .bind(this)},{text:ORYX.I18N.PropertyWindow.rem,handler:function(){var ds=this.grid.getStore();
var selection=this.grid.getSelectionModel().getSelectedCell();
if(selection==undefined){return
}this.grid.getSelectionModel().clearSelections();
this.grid.stopEditing();
var record=ds.getAt(selection[0]);
ds.remove(record);
ds.commitChanges()
}get .bind(this)}]);
this.dialog=new Ext.Window({autoScroll:true,autoCreate:true,title:ORYX.I18N.PropertyWindow.complex,height:350,width:dialogWidth,modal:true,collapsible:false,fixedcenter:true,shadow:true,proxyDrag:true,keys:[{key:27,fn:function(){this.dialog.hide
}get .bind(this)}],items:[toolbar,this.grid],bodyStyle:"background-color:#FFFFFF",buttons:[{text:ORYX.I18N.PropertyWindow.ok,handler:function(){this.grid.stopEditing();
this.data=this.buildValue();
this.dialog.hide()
}get .bind(this)},{text:ORYX.I18N.PropertyWindow.cancel,handler:function(){this.dialog.hide()
}get .bind(this)}]});
this.dialog.on(Ext.apply({},this.dialogListeners,{scope:this}));
this.dialog.show();
this.grid.on("beforeedit",this.beforeEdit,this,true);
this.grid.on("afteredit",this.afterEdit,this,true);
this.grid.render()
}get });
Ext.form.ComplexTextField=Ext.extend(Ext.form.TriggerField,{defaultAutoCreate:{tag:"textarea",rows:1,style:"height:16px;overflow:hidden;"},onTriggerClick:function(){if(this.disabled){return
}if(this.dialog){return
}var b=new Ext.form.TextArea({anchor:"100% 100%",value:this.value,listeners:{focus:function(){this.facade.disableEvent(ORYX.CONFIG.EVENT_KEYDOWN)
}get .bind(this)}});
var a=new Ext.Window({layout:"anchor",autoCreate:true,title:ORYX.I18N.PropertyWindow.text,height:500,width:500,modal:true,collapsible:false,fixedcenter:true,shadow:true,proxyDrag:true,keys:[{key:27,fn:function(){a.hide()
}get .bind(this)}],items:[b],listeners:{hide:function(){this.fireEvent("dialogClosed",this.value);
a.destroy()
}get .bind(this)},buttons:[{text:ORYX.I18N.PropertyWindow.ok,handler:function(){var c=b.getValue();
this.setValue(c);
this.dataSource.getAt(this.row).set("value",c);
this.dataSource.commitChanges();
a.hide()
}get .bind(this)},{text:ORYX.I18N.PropertyWindow.cancel,handler:function(){this.setValue(this.value);
a.hide()
}get .bind(this)}]});
a.show();
b.render();
this.grid.stopEditing();
b.focus(false,100)
}get });
Ext.form.OnRead=Ext.extend(Ext.form.TriggerField,{defaultAutoCreate:{tag:"textarea",rows:1,style:"height:16px;overflow:hidden;"},onTriggerClick:function(){if(this.disabled){return
}if(this.dialog){return
}var b=new Ext.form.TextArea({anchor:"100% 100%",value:this.value,listeners:{focus:function(){this.facade.disableEvent(ORYX.CONFIG.EVENT_KEYDOWN)
}get .bind(this)}});
var a=new Ext.Window({layout:"anchor",autoCreate:true,title:ORYX.I18N.PropertyWindow.onRead,height:250,width:250,modal:true,collapsible:false,fixedcenter:true,shadow:true,proxyDrag:true,keys:[{key:27,fn:function(){a.hide()
}get .bind(this)}],items:[b],listeners:{hide:function(){this.fireEvent("dialogClosed",this.value);
a.destroy()
}get .bind(this)},buttons:[{text:ORYX.I18N.PropertyWindow.ok,handler:function(){var c=b.getValue();
this.setValue(c);
this.dataSource.getAt(this.row).set("value",c);
this.dataSource.commitChanges();
a.hide()
}get .bind(this)},{text:ORYX.I18N.PropertyWindow.cancel,handler:function(){this.setValue(this.value);
a.hide()
}get .bind(this)}]});
a.show();
b.render();
this.grid.stopEditing();
b.focus(false,100)
}get });
Ext.form.OnCreate=Ext.extend(Ext.form.TriggerField,{defaultAutoCreate:{tag:"textarea",rows:1,style:"height:16px;overflow:hidden;"},onTriggerClick:function(){if(this.disabled){return
}if(this.dialog){return
}var b=new Ext.form.TextArea({anchor:"100% 100%",value:this.value,listeners:{focus:function(){this.facade.disableEvent(ORYX.CONFIG.EVENT_KEYDOWN)
}get .bind(this)}});
var a=new Ext.Window({layout:"anchor",autoCreate:true,title:ORYX.I18N.PropertyWindow.onCreate,height:250,width:250,modal:true,collapsible:false,fixedcenter:true,shadow:true,proxyDrag:true,keys:[{key:27,fn:function(){a.hide()
}get .bind(this)}],items:[b],listeners:{hide:function(){this.fireEvent("dialogClosed",this.value);
a.destroy()
}get .bind(this)},buttons:[{text:ORYX.I18N.PropertyWindow.ok,handler:function(){var c=b.getValue();
this.setValue(c);
this.dataSource.getAt(this.row).set("value",c);
this.dataSource.commitChanges();
a.hide()
}get .bind(this)},{text:ORYX.I18N.PropertyWindow.cancel,handler:function(){this.setValue(this.value);
a.hide()
}get .bind(this)}]});
a.show();
b.render();
this.grid.stopEditing();
b.focus(false,100)
}get });
Ext.form.OnUpdate=Ext.extend(Ext.form.TriggerField,{defaultAutoCreate:{tag:"textarea",rows:1,style:"height:16px;overflow:hidden;"},onTriggerClick:function(){if(this.disabled){return
}if(this.dialog){return
}var b=new Ext.form.TextArea({anchor:"100% 100%",value:this.value,listeners:{focus:function(){this.facade.disableEvent(ORYX.CONFIG.EVENT_KEYDOWN)
}get .bind(this)}});
var a=new Ext.Window({layout:"anchor",autoCreate:true,title:ORYX.I18N.PropertyWindow.onUpdate,height:250,width:250,modal:true,collapsible:false,fixedcenter:true,shadow:true,proxyDrag:true,keys:[{key:27,fn:function(){a.hide()
}get .bind(this)}],items:[b],listeners:{hide:function(){this.fireEvent("dialogClosed",this.value);
a.destroy()
}get .bind(this)},buttons:[{text:ORYX.I18N.PropertyWindow.ok,handler:function(){var c=b.getValue();
this.setValue(c);
this.dataSource.getAt(this.row).set("value",c);
this.dataSource.commitChanges();
a.hide()
}get .bind(this)},{text:ORYX.I18N.PropertyWindow.cancel,handler:function(){this.setValue(this.value);
a.hide()
}get .bind(this)}]});
a.show();
b.render();
this.grid.stopEditing();
b.focus(false,100)
}get });
Ext.form.CreateCustomOperation=function(b,a,d,e,c){Ext.form.CreateCustomOperation.superclass.constructor.call(this,b);
this.items=a;
this.key=d;
this.facade=e;
this.grid=c
}get ;
Ext.extend(Ext.form.CreateCustomOperation,Ext.form.TriggerField,{triggerClass:"x-form-complex-trigger",readOnly:true,emptyText:ORYX.I18N.PropertyWindow.clickIcon,buildValue:function(){return message
}get ,getFieldKey:function(){}get ,getValue:function(){if(this.grid){}else{if(this.data==undefined){return""
}else{return this.data
}}}get ,setValue:function(a){if(a.length>0){if(this.data==undefined){this.data=a
}}}get ,keydownHandler:function(a){return false
}get ,dialogListeners:{show:function(){this.onFocus();
this.facade.registerOnEvent(ORYX.CONFIG.EVENT_KEYDOWN,this.keydownHandler.bind(this));
this.facade.disableEvent(ORYX.CONFIG.EVENT_KEYDOWN);
return
}get ,hide:function(){var a=this.dialogListeners;
this.dialog.un("show",a.show,this);
this.dialog.un("hide",a.hide,this);
this.dialog.destroy(true);
delete this.dialog;
this.facade.unregisterOnEvent(ORYX.CONFIG.EVENT_KEYDOWN,this.keydownHandler.bind(this));
this.facade.enableEvent(ORYX.CONFIG.EVENT_KEYDOWN);
this.fireEvent("dialogClosed",this.data);
Ext.form.ComplexListField.superclass.setValue.call(this,this.data)
}get },buildInitial:function(b,a){}get ,afterEdit:function(a){}get ,beforeEdit:function(a){}get ,onTriggerClick:function(){if(this.disabled){return
}if(this.dialog){return
}var b=0;
var c=[];
var a=new Ext.Panel({id:"createCustomOperation",bodyBorder:false,layout:"absolute",defaultType:"textfield",border:false,height:383,width:536,defaults:{autoScroll:true},items:[{x:0,y:5,xtype:"label",text:"Send To:"},{x:60,y:0,name:"to",anchor:"100%"},{x:0,y:35,xtype:"label",text:"Subject:"},{x:60,y:30,name:"subject",anchor:"100%"},{x:0,y:60,xtype:"textarea",name:"msg",anchor:"100% 100%"}]});
this.dialog=new Ext.Window({autoScroll:true,autoCreate:true,title:ORYX.I18N.PropertyWindow.operationPermission,height:450,width:550,modal:true,collapsible:false,fixedcenter:true,shadow:true,proxyDrag:true,bodyBorder:false,keys:[{key:27,fn:function(){this.dialog.hide
}get .bind(this)}],items:[a],bodyStyle:"background-color:#FFFFFF",buttons:[{text:ORYX.I18N.PropertyWindow.ok,handler:function(){this.data=this.buildValue();
this.dialog.hide()
}get .bind(this)},{text:ORYX.I18N.PropertyWindow.cancel,handler:function(){this.dialog.hide()
}get .bind(this)}]});
this.dialog.on(Ext.apply({},this.dialogListeners,{scope:this}));
this.dialog.show()
}get });
Ext.form.EndNodeScript=function(b,a,d,e,c){Ext.form.EndNodeScript.superclass.constructor.call(this,b);
this.items=a;
this.key=d;
this.facade=e;
this.grid=c
}get ;
Ext.extend(Ext.form.EndNodeScript,Ext.form.TriggerField,{triggerClass:"x-form-complex-trigger",readOnly:true,emptyText:ORYX.I18N.PropertyWindow.clickIcon,buildValue:function(b){var a=createEndScriptJson(b);
return a
}get ,getFieldKey:function(){}get ,getValue:function(){if(this.grid){}else{if(this.data==undefined){return""
}else{return this.data
}}}get ,setValue:function(a){if(a.length>0){if(this.data==undefined){this.data=a
}}}get ,keydownHandler:function(a){return false
}get ,dialogListeners:{show:function(){this.onFocus();
this.facade.registerOnEvent(ORYX.CONFIG.EVENT_KEYDOWN,this.keydownHandler.bind(this));
this.facade.disableEvent(ORYX.CONFIG.EVENT_KEYDOWN);
return
}get ,hide:function(){var a=this.dialogListeners;
this.dialog.un("show",a.show,this);
this.dialog.un("hide",a.hide,this);
this.dialog.destroy(true);
delete this.dialog;
this.facade.unregisterOnEvent(ORYX.CONFIG.EVENT_KEYDOWN,this.keydownHandler.bind(this));
this.facade.enableEvent(ORYX.CONFIG.EVENT_KEYDOWN);
this.fireEvent("dialogClosed",this.data);
Ext.form.ComplexListField.superclass.setValue.call(this,this.data)
}get },buildInitial:function(b,a){}get ,afterEdit:function(a){}get ,beforeEdit:function(a){}get ,onTriggerClick:function(){if(this.disabled){return
}if(this.dialog){return
}var f=0;
var h=[];
var g=this.grid.getSelectionModel().getSelectedCell();
var c=this.grid.store.getAt(g[0]);
var d=c.get("name");
var a=c.get("value");
var e="endScript";
if(a!=""){reInitializeObjectObj(a,d,e,"")
}else{clearObjects(a,d,e)
}var b=new Ext.Panel({id:"endscript",bodyBorder:false,border:false,height:363,width:426,defaults:{autoScroll:true},items:[{id:"end_script_panel",html:"<div id='endScriptTabPanel' class='padding10 overflow'></div>",bodyBorder:false,border:false,autoScroll:true,listeners:{render:function(){setTimeout(function(){loadEndScriptHtml(d)
}get ,400)
}get }}]});
this.dialog=new Ext.Window({autoScroll:true,autoCreate:true,title:ORYX.I18N.PropertyWindow.endScript,height:450,width:450,modal:true,collapsible:false,fixedcenter:true,shadow:true,proxyDrag:true,bodyBorder:false,keys:[{key:27,fn:function(){this.dialog.hide
}get .bind(this)}],items:[b],bodyStyle:"background-color:#FFFFFF",buttons:[{text:ORYX.I18N.PropertyWindow.ok,handler:function(){this.data=this.buildValue(d);
this.dialog.hide()
}get .bind(this)},{text:ORYX.I18N.PropertyWindow.cancel,handler:function(){this.dialog.hide()
}get .bind(this)}]});
this.dialog.on(Ext.apply({},this.dialogListeners,{scope:this}));
this.dialog.show()
}get });
Ext.form.StartNodeScript=function(b,a,d,e,c){Ext.form.StartNodeScript.superclass.constructor.call(this,b);
this.items=a;
this.key=d;
this.facade=e;
this.grid=c
}get ;
Ext.extend(Ext.form.StartNodeScript,Ext.form.TriggerField,{triggerClass:"x-form-complex-trigger",readOnly:true,emptyText:ORYX.I18N.PropertyWindow.clickIcon,buildValue:function(b){var a=createStartScriptJson(b);
return a
}get ,getFieldKey:function(){}get ,getValue:function(){if(this.grid){}else{if(this.data==undefined){return""
}else{return this.data
}}}get ,setValue:function(a){if(a.length>0){if(this.data==undefined){this.data=a
}}}get ,keydownHandler:function(a){return false
}get ,dialogListeners:{show:function(){this.onFocus();
this.facade.registerOnEvent(ORYX.CONFIG.EVENT_KEYDOWN,this.keydownHandler.bind(this));
this.facade.disableEvent(ORYX.CONFIG.EVENT_KEYDOWN);
return
}get ,hide:function(){var a=this.dialogListeners;
this.dialog.un("show",a.show,this);
this.dialog.un("hide",a.hide,this);
this.dialog.destroy(true);
delete this.dialog;
this.facade.unregisterOnEvent(ORYX.CONFIG.EVENT_KEYDOWN,this.keydownHandler.bind(this));
this.facade.enableEvent(ORYX.CONFIG.EVENT_KEYDOWN);
this.fireEvent("dialogClosed",this.data);
Ext.form.ComplexListField.superclass.setValue.call(this,this.data)
}get },buildInitial:function(b,a){}get ,afterEdit:function(a){}get ,beforeEdit:function(a){}get ,onTriggerClick:function(){if(this.disabled){return
}if(this.dialog){return
}var f=0;
var h=[];
var g=this.grid.getSelectionModel().getSelectedCell();
var b=this.grid.store.getAt(g[0]);
var d=b.get("name");
var a=b.get("value");
var e="startScript";
if(a!=""){reInitializeObjectObj(a,d,e,"")
}else{clearObjects(a,d,e)
}var c=new Ext.Panel({id:"startscript",bodyBorder:false,border:false,height:363,width:426,defaults:{autoScroll:true},items:[{id:"start_script_panel",html:"<div id='startScriptTabPanel' class='padding10 overflow'></div>",bodyBorder:false,border:false,autoScroll:true,listeners:{render:function(){setTimeout(function(){loadStartScriptHtml(d)
}get ,400)
}get }}]});
this.dialog=new Ext.Window({autoScroll:true,autoCreate:true,title:ORYX.I18N.PropertyWindow.startScript,height:450,width:450,modal:true,collapsible:false,fixedcenter:true,shadow:true,proxyDrag:true,bodyBorder:false,keys:[{key:27,fn:function(){this.dialog.hide
}get .bind(this)}],items:[c],bodyStyle:"background-color:#FFFFFF",buttons:[{text:ORYX.I18N.PropertyWindow.ok,handler:function(){this.data=this.buildValue(d);
this.dialog.hide()
}get .bind(this)},{text:ORYX.I18N.PropertyWindow.cancel,handler:function(){this.dialog.hide()
}get .bind(this)}]});
this.dialog.on(Ext.apply({},this.dialogListeners,{scope:this}));
this.dialog.show()
}get });
Ext.form.OperationPermission=function(b,a,d,e,c){Ext.form.OperationPermission.superclass.constructor.call(this,b);
this.items=a;
this.key=d;
this.facade=e;
this.grid=c
}get ;
Ext.extend(Ext.form.OperationPermission,Ext.form.TriggerField,{triggerClass:"x-form-complex-trigger",readOnly:true,emptyText:ORYX.I18N.PropertyWindow.clickIcon,buildValue:function(a){var b="["+getOperationResourceStoreDataAsString(a)+"]";
return b
}get ,getFieldKey:function(){}get ,getValue:function(){if(this.grid){}else{if(this.data==undefined){return""
}else{return this.data
}}}get ,setValue:function(a){if(a.length>0){if(this.data==undefined){this.data=a
}}}get ,keydownHandler:function(a){return false
}get ,dialogListeners:{show:function(){this.onFocus();
this.facade.registerOnEvent(ORYX.CONFIG.EVENT_KEYDOWN,this.keydownHandler.bind(this));
this.facade.disableEvent(ORYX.CONFIG.EVENT_KEYDOWN);
return
}get ,hide:function(){var a=this.dialogListeners;
this.dialog.un("show",a.show,this);
this.dialog.un("hide",a.hide,this);
this.dialog.destroy(true);
delete this.dialog;
this.facade.unregisterOnEvent(ORYX.CONFIG.EVENT_KEYDOWN,this.keydownHandler.bind(this));
this.facade.enableEvent(ORYX.CONFIG.EVENT_KEYDOWN);
this.fireEvent("dialogClosed",this.data);
Ext.form.ComplexListField.superclass.setValue.call(this,this.data)
}get },buildInitial:function(b,a){}get ,afterEdit:function(a){}get ,beforeEdit:function(a){}get ,onTriggerClick:function(){if(this.disabled){return
}if(this.dialog){return
}var e=0;
var d=[];
var h=this.grid.getSelectionModel().getSelectedCell();
var g=this.grid.store.getAt(h[0]);
var a=g.get("name");
var l=g.get("value");
var f=getExtjsJsonObjByNameOFPermission(a);
if(l!=""){reInitializeObjectObj(l,a,"operationPermission","")
}else{clearObjects(l,a,"operationPermission")
}var c=new Ext.data.Store({autoLoad:true,proxy:new Ext.data.HttpProxy({url:"/bpm/customOperating/getAllCustomOperations"}),reader:new Ext.data.JsonReader({root:"customOperations"},[{name:"id"},{name:"functionName"}])});
var k=new Ext.form.ComboBox({store:c,displayField:"functionName",valueField:"id",lazyRender:true,typeAhead:true,mode:"local",forceSelection:true,triggerAction:"all",emptyText:"Add custom operation...",selectOnFocus:true,listeners:{select:function(n,o,m){addCustomOperationResources(this.getValue(),this.getRawValue(),a)
}get }});
var b=new Ext.Panel({id:"operationTabPanel",bodyBorder:false,border:false,height:333,width:436,defaults:{autoScroll:true},items:[{id:"operation_permission",html:"<div id='definedOperationPermission' class='padding5'></div><div style='float:right;padding-right:5px;'>&nbsp;</div>",bodyBorder:false,border:false,autoScroll:true,width:436,height:125,listeners:{render:function(){setTimeout(function(){loadDefinedOperationList(a)
}get ,400)
}get }},{id:"custom_operation",bodyBorder:false,autoScroll:true,border:false,width:436,height:205,items:[{id:"customPermissionItem",items:[k],bodyBorder:false,border:false,autoScroll:true,bodyStyle:"padding-left:120px",width:436,height:24},{id:"custom_operation_permission",html:"<div id='customOperationPermission' class='padding5 overflow'></div>",bodyBorder:false,border:false,autoScroll:true,width:436,height:149}]}]});
this.dialog=new Ext.Window({autoScroll:true,autoCreate:true,title:ORYX.I18N.PropertyWindow.operationPermission,height:414,width:450,modal:true,collapsible:false,fixedcenter:true,shadow:true,proxyDrag:true,bodyBorder:false,keys:[{key:27,fn:function(){this.dialog.hide
}get .bind(this)}],items:[b],bodyStyle:"background-color:#FFFFFF",buttons:[{text:ORYX.I18N.PropertyWindow.ok,handler:function(){this.data=this.buildValue(a);
this.dialog.hide()
}get .bind(this)},{text:ORYX.I18N.PropertyWindow.cancel,handler:function(){this.dialog.hide()
}get .bind(this)}]});
this.dialog.on(Ext.apply({},this.dialogListeners,{scope:this}));
this.dialog.show()
}get });
Ext.form.MemberPermissionEnd=function(b,a,d,e,c){Ext.form.MemberPermissionEnd.superclass.constructor.call(this,b);
this.items=a;
this.key=d;
this.facade=e;
this.grid=c
}get ;
Ext.extend(Ext.form.MemberPermissionEnd,Ext.form.TriggerField,{triggerClass:"x-form-complex-trigger",readOnly:true,emptyText:ORYX.I18N.PropertyWindow.clickIcon,buildValue:function(a){var b="${";
var c="'members':["+getResourceStoreDataAsString(a)+"]";
b=b+""+c+"}";
return b
}get ,getFieldKey:function(){}get ,getValue:function(){if(this.grid){}else{if(this.data==undefined){return""
}else{return this.data
}}}get ,setValue:function(a){if(a.length>0){if(this.data==undefined){this.data=a
}}}get ,keydownHandler:function(a){return false
}get ,dialogListeners:{show:function(){this.onFocus();
this.facade.registerOnEvent(ORYX.CONFIG.EVENT_KEYDOWN,this.keydownHandler.bind(this));
this.facade.disableEvent(ORYX.CONFIG.EVENT_KEYDOWN);
return
}get ,hide:function(){var a=this.dialogListeners;
this.dialog.un("show",a.show,this);
this.dialog.un("hide",a.hide,this);
this.dialog.destroy(true);
delete this.dialog;
this.facade.unregisterOnEvent(ORYX.CONFIG.EVENT_KEYDOWN,this.keydownHandler.bind(this));
this.facade.enableEvent(ORYX.CONFIG.EVENT_KEYDOWN);
this.fireEvent("dialogClosed",this.data);
Ext.form.ComplexListField.superclass.setValue.call(this,this.data)
}get },buildInitial:function(b,a){}get ,afterEdit:function(a){}get ,beforeEdit:function(a){}get ,onTriggerClick:function(){if(this.disabled){return
}if(this.dialog){return
}var d=0;
var c=[];
var g=this.grid.getSelectionModel().getSelectedCell();
var f=this.grid.store.getAt(g[0]);
var a=f.get("name");
var n=f.get("value");
var l=this.grid.store.getAt(1);
var h=l.get("value");
var k="";
if(h!=""&&h.length>0){k=h
}else{var p=this.grid.store.getAt(2);
var o=p.get("value");
k=o
}if(a==ORYX.I18N.PropertyWindow.admin||a==ORYX.I18N.PropertyWindow.reader){permissionType="membersPermissionEnd"
}else{permissionType="membersFields"
}if(n!=""){reInitializeObjectObj(n,a,permissionType,k)
}else{clearObjects(n,a,permissionType)
}var m='<div id="resources_select" class="pad-T5 pad-L40">';
m+='<select id = "resources_options" name = "resources_options" onchange="loadTree(null,\'fieldPermission\',\''+a+'\')" class="fontSize13">';
m+='<option value="none"> </option>';
if(a==ORYX.I18N.PropertyWindow.reader){m+='<option value="potentialOwner">Group</option>';
m+='<option value="potentialOwner">Role</option>';
m+='<option value="potentialOwner">Department</option>'
}m+='<option value="humanPerformer" selected="selected">User</option>';
m+="</select>";
m+='</div><div id="tree2" class="pad-L40 pad-T10"></div>';
var e=new Ext.Panel({title:ORYX.I18N.PropertyWindow.cts,layout:"table",width:447,height:300,header:false,items:[{id:"tree_select",html:m,bodyBorder:false,autoScroll:true,width:225,height:300,listeners:{render:function(){loadTree("humanPerformer","fieldPermission",a)
}get ,}},{region:"east",id:"details-panel",autoScroll:true,collapsible:false,split:true,width:220,height:300,html:'<div class="padding10" id="userInfo_"></div><div class="padding10 " id="resource_order"></div>',}]});
var b=new Ext.TabPanel({renderTo:document.body,activeTab:0,height:335,width:450,defaults:{autoScroll:true},items:[{title:ORYX.I18N.PropertyWindow.members,items:[e],}]});
this.dialog=new Ext.Window({autoScroll:true,autoCreate:true,title:ORYX.I18N.PropertyWindow.premission,height:420,width:464,modal:true,collapsible:false,fixedcenter:true,shadow:true,proxyDrag:true,keys:[{key:27,fn:function(){this.dialog.hide
}get .bind(this)}],items:[b],bodyStyle:"background-color:#FFFFFF",buttons:[{text:ORYX.I18N.PropertyWindow.ok,handler:function(){this.data=this.buildValue(a);
this.dialog.hide()
}get .bind(this)},{text:ORYX.I18N.PropertyWindow.cancel,handler:function(){this.dialog.hide()
}get .bind(this)}]});
this.dialog.on(Ext.apply({},this.dialogListeners,{scope:this}));
this.dialog.show()
}get });
Ext.form.StartPermission=function(b,a,d,e,c){Ext.form.StartPermission.superclass.constructor.call(this,b);
this.items=a;
this.key=d;
this.facade=e;
this.grid=c
}get ;
Ext.extend(Ext.form.StartPermission,Ext.form.TriggerField,{triggerClass:"x-form-complex-trigger",readOnly:true,emptyText:ORYX.I18N.PropertyWindow.clickIcon,buildValue:function(b,a){var c="${";
c+="'startPermission':"+generateStartPermissionJSON(b,a)+"";
c+=","+generateStartDynamicTransactorJSON(b,a)+",'previousFormId' : '"+a+"'}";
return c
}get ,getFieldKey:function(){}get ,getValue:function(){if(this.grid){}else{if(this.data==undefined){return""
}else{return this.data
}}}get ,setValue:function(a){if(a.length>0){if(this.data==undefined){this.data=a
}}}get ,keydownHandler:function(a){return false
}get ,dialogListeners:{show:function(){this.onFocus();
this.facade.registerOnEvent(ORYX.CONFIG.EVENT_KEYDOWN,this.keydownHandler.bind(this));
this.facade.disableEvent(ORYX.CONFIG.EVENT_KEYDOWN);
return
}get ,hide:function(){var a=this.dialogListeners;
this.dialog.un("show",a.show,this);
this.dialog.un("hide",a.hide,this);
this.dialog.destroy(true);
delete this.dialog;
this.facade.unregisterOnEvent(ORYX.CONFIG.EVENT_KEYDOWN,this.keydownHandler.bind(this));
this.facade.enableEvent(ORYX.CONFIG.EVENT_KEYDOWN);
this.fireEvent("dialogClosed",this.data);
Ext.form.ComplexListField.superclass.setValue.call(this,this.data)
}get },buildInitial:function(b,a){}get ,afterEdit:function(a){}get ,beforeEdit:function(a){}get ,onTriggerClick:function(){if(this.disabled){return
}if(this.dialog){return
}var e=0;
var c=[];
var g=this.grid.getSelectionModel().getSelectedCell();
var f=this.grid.store.getAt(g[0]);
var a=f.get("name");
var m=f.get("value");
var d=getExtjsJsonObjWithName(a);
var l=this.grid.store.getAt(1);
var h=l.get("value");
var k="";
if(h!=""&&h.length>0){k=h
}else{var o=this.grid.store.getAt(2);
var n=o.get("value");
k=n
}if(m!=""){reInitializeObjectObj(m,a,"startPermission",k)
}else{clearObjects(m,a,"startPermission")
}var b=new Ext.TabPanel({renderTo:document.body,activeTab:0,height:329,width:450,defaults:{autoScroll:true},items:[{title:ORYX.I18N.PropertyWindow.premission,html:'<div id="startFieldPermissionDiv" class="padding10"></div>',listeners:{activate:function(){setStartPropertyFieldPermission(d,k,a,m)
}get }},{title:ORYX.I18N.PropertyWindow.dynamicTransactor,html:'<div id="startDynamicTransactorDiv" class="padding10"></div>',listeners:{activate:function(){setStartDynamicTransactorContent(k,a,m)
}get }}]});
this.dialog=new Ext.Window({autoScroll:true,autoCreate:true,title:ORYX.I18N.PropertyWindow.premission,height:396,width:464,modal:true,collapsible:false,fixedcenter:true,shadow:true,proxyDrag:true,keys:[{key:27,fn:function(){this.dialog.hide
}get .bind(this)}],items:[b],bodyStyle:"background-color:#FFFFFF",buttons:[{text:ORYX.I18N.PropertyWindow.ok,handler:function(){this.data=this.buildValue(a,k);
this.dialog.hide()
}get .bind(this)},{text:ORYX.I18N.PropertyWindow.cancel,handler:function(){this.dialog.hide()
}get .bind(this)}]});
this.dialog.on(Ext.apply({},this.dialogListeners,{scope:this}));
if(a==ORYX.I18N.PropertyWindow.startPermission){this.dialog.show()
}else{if(k==null||k==""||k==undefined){Ext.MessageBox.alert("Information",ORYX.I18N.PropertyWindow.formError)
}else{this.dialog.show()
}}}get });
Ext.form.MemberPermissionProcess=function(b,a,d,e,c){Ext.form.MemberPermissionProcess.superclass.constructor.call(this,b);
this.items=a;
this.key=d;
this.facade=e;
this.grid=c
}get ;
Ext.extend(Ext.form.MemberPermissionProcess,Ext.form.TriggerField,{triggerClass:"x-form-complex-trigger",readOnly:true,emptyText:ORYX.I18N.PropertyWindow.clickIcon,buildValue:function(b,a){var c="${";
var d="'processMembers':["+getResourceStoreDataAsStringForProcess(b)+"]";
if(b==ORYX.I18N.PropertyWindow.process){c=c+""+d+"}"
}else{c=c+""+d+","+generatedynamicTransactorJSON(b,a)+",'previousFormId' : '"+a+"'}"
}return c
}get ,getFieldKey:function(){}get ,getValue:function(){if(this.grid){}else{if(this.data==undefined){return""
}else{return this.data
}}}get ,setValue:function(a){if(a.length>0){if(this.data==undefined){this.data=a
}}}get ,keydownHandler:function(a){return false
}get ,dialogListeners:{show:function(){this.onFocus();
this.facade.registerOnEvent(ORYX.CONFIG.EVENT_KEYDOWN,this.keydownHandler.bind(this));
this.facade.disableEvent(ORYX.CONFIG.EVENT_KEYDOWN);
return
}get ,hide:function(){var a=this.dialogListeners;
this.dialog.un("show",a.show,this);
this.dialog.un("hide",a.hide,this);
this.dialog.destroy(true);
delete this.dialog;
this.facade.unregisterOnEvent(ORYX.CONFIG.EVENT_KEYDOWN,this.keydownHandler.bind(this));
this.facade.enableEvent(ORYX.CONFIG.EVENT_KEYDOWN);
this.fireEvent("dialogClosed",this.data);
Ext.form.ComplexListField.superclass.setValue.call(this,this.data)
}get },buildInitial:function(b,a){}get ,afterEdit:function(a){}get ,beforeEdit:function(a){}get ,onTriggerClick:function(){if(this.disabled){return
}if(this.dialog){return
}var d=0;
var c=[];
var g=this.grid.getSelectionModel().getSelectedCell();
var f=this.grid.store.getAt(g[0]);
var a=f.get("name");
var n=f.get("value");
var l=this.grid.store.getAt(1);
var h=l.get("value");
var k="";
if(h!=""&&h.length>0){k=h
}else{var p=this.grid.store.getAt(2);
var o=p.get("value");
k=o
}if(a==ORYX.I18N.PropertyWindow.process){permissionType="membersPermissionprocess"
}if(n!=""){reInitializeObjectObj(n,a,permissionType,k)
}else{clearObjects(n,a,permissionType)
}var m='<div id="resources_select" class="pad-T5 pad-L40">';
m+='<select id = "resources_options" name = "resources_options" onchange="loadTree(null,\'fieldPermission\',\''+a+'\')" class="fontSize13">';
m+='<option value="none"> </option>';
m+='<option value="potentialOwner">Group</option>';
m+='<option value="potentialOwner">Role</option>';
m+='<option value="potentialOwner">Department</option>';
m+='<option value="humanPerformer" selected="selected">User</option>';
m+="</select>";
m+='</div><div id="tree2" class="pad-L40 pad-T10"></div>';
var e=new Ext.Panel({title:ORYX.I18N.PropertyWindow.cts,layout:"table",width:447,height:300,header:false,items:[{id:"tree_select",html:m,bodyBorder:false,autoScroll:true,width:225,height:300,listeners:{render:function(){loadTree("humanPerformer","fieldPermission",a)
}get ,}},{region:"east",id:"details-panel",autoScroll:true,collapsible:false,split:true,width:220,height:300,html:'<div class="padding10" id="userInfo_"></div><div class="padding10 " id="resource_order"></div>',}]});
var b="";
if(a==ORYX.I18N.PropertyWindow.process){b=new Ext.TabPanel({renderTo:document.body,activeTab:0,height:335,width:450,defaults:{autoScroll:true},items:[{title:"Members",items:[e],}]})
}else{b=new Ext.TabPanel({renderTo:document.body,activeTab:0,height:335,width:450,defaults:{autoScroll:true},items:[{title:ORYX.I18N.PropertyWindow.members,items:[e],},{title:ORYX.I18N.PropertyWindow.dynamicTransactor,html:'<div id="dynamicTransactorDiv" class="padding10"></div>',listeners:{activate:function(){setDynamicTransactorContent(k,a,n)
}get }}]})
}this.dialog=new Ext.Window({autoScroll:true,autoCreate:true,title:ORYX.I18N.PropertyWindow.premission,height:420,width:464,modal:true,collapsible:false,fixedcenter:true,shadow:true,proxyDrag:true,keys:[{key:27,fn:function(){this.dialog.hide
}get .bind(this)}],items:[b],bodyStyle:"background-color:#FFFFFF",buttons:[{text:ORYX.I18N.PropertyWindow.ok,handler:function(){this.data=this.buildValue(a,k);
this.dialog.hide()
}get .bind(this)},{text:ORYX.I18N.PropertyWindow.cancel,handler:function(){this.dialog.hide()
}get .bind(this)}]});
this.dialog.on(Ext.apply({},this.dialogListeners,{scope:this}));
if(a==ORYX.I18N.PropertyWindow.process){this.dialog.show()
}else{if(k==null||k==""||k==undefined){Ext.MessageBox.alert("Information",ORYX.I18N.PropertyWindow.formError)
}else{this.dialog.show()
}}}get });
Ext.form.MemberPermission=function(b,a,d,e,c){Ext.form.MemberPermission.superclass.constructor.call(this,b);
this.items=a;
this.key=d;
this.facade=e;
this.grid=c
}get ;
Ext.extend(Ext.form.MemberPermission,Ext.form.TriggerField,{triggerClass:"x-form-complex-trigger",readOnly:true,emptyText:ORYX.I18N.PropertyWindow.clickIcon,buildValue:function(b,a){var c="${";
var d="'members':["+getResourceStoreDataAsString(b)+"]";
if(b==ORYX.I18N.PropertyWindow.admin){c=c+""+d+"}"
}else{c=c+""+d+","+generatedynamicTransactorJSON(b,a)+",'previousFormId' : '"+a+"'}"
}return c
}get ,getFieldKey:function(){}get ,getValue:function(){if(this.grid){}else{if(this.data==undefined){return""
}else{return this.data
}}}get ,setValue:function(a){if(a.length>0){if(this.data==undefined){this.data=a
}}}get ,keydownHandler:function(a){return false
}get ,dialogListeners:{show:function(){this.onFocus();
this.facade.registerOnEvent(ORYX.CONFIG.EVENT_KEYDOWN,this.keydownHandler.bind(this));
this.facade.disableEvent(ORYX.CONFIG.EVENT_KEYDOWN);
return
}get ,hide:function(){var a=this.dialogListeners;
this.dialog.un("show",a.show,this);
this.dialog.un("hide",a.hide,this);
this.dialog.destroy(true);
delete this.dialog;
this.facade.unregisterOnEvent(ORYX.CONFIG.EVENT_KEYDOWN,this.keydownHandler.bind(this));
this.facade.enableEvent(ORYX.CONFIG.EVENT_KEYDOWN);
this.fireEvent("dialogClosed",this.data);
Ext.form.ComplexListField.superclass.setValue.call(this,this.data)
}get },buildInitial:function(b,a){}get ,afterEdit:function(a){}get ,beforeEdit:function(a){}get ,onTriggerClick:function(){if(this.disabled){return
}if(this.dialog){return
}var d=0;
var c=[];
var g=this.grid.getSelectionModel().getSelectedCell();
var f=this.grid.store.getAt(g[0]);
var a=f.get("name");
var n=f.get("value");
var l=this.grid.store.getAt(1);
var h=l.get("value");
var k="";
if(h!=""&&h.length>0){k=h
}else{var p=this.grid.store.getAt(2);
var o=p.get("value");
k=o
}if(a==ORYX.I18N.PropertyWindow.admin||a==ORYX.I18N.PropertyWindow.reader){permissionType="membersPermission"
}else{permissionType="membersFields"
}if(n!=""){reInitializeObjectObj(n,a,permissionType,k)
}else{clearObjects(n,a,permissionType)
}var m='<div id="resources_select" class="pad-T5 pad-L40">';
m+='<select id = "resources_options" name = "resources_options" onchange="loadTree(null,\'fieldPermission\',\''+a+'\')" class="fontSize13">';
m+='<option value="none"> </option>';
m+='<option value="potentialOwner">Group</option>';
m+='<option value="potentialOwner">Role</option>';
m+='<option value="potentialOwner">Department</option>';
m+='<option value="humanPerformer" selected="selected">User</option>';
m+="</select>";
m+='</div><div id="tree2" class="pad-L40 pad-T10"></div>';
var e=new Ext.Panel({title:ORYX.I18N.PropertyWindow.cts,layout:"table",width:447,height:300,header:false,items:[{id:"tree_select",html:m,bodyBorder:false,autoScroll:true,width:225,height:300,listeners:{render:function(){loadTree("humanPerformer","fieldPermission",a)
}get ,}},{region:"east",id:"details-panel",autoScroll:true,collapsible:false,split:true,width:220,height:300,html:'<div class="padding10" id="userInfo_"></div><div class="padding10 " id="resource_order"></div>',}]});
var b="";
if(a==ORYX.I18N.PropertyWindow.admin){b=new Ext.TabPanel({renderTo:document.body,activeTab:0,height:335,width:450,defaults:{autoScroll:true},items:[{title:ORYX.I18N.PropertyWindow.members,items:[e],}]})
}else{b=new Ext.TabPanel({renderTo:document.body,activeTab:0,height:335,width:450,defaults:{autoScroll:true},items:[{title:ORYX.I18N.PropertyWindow.members,items:[e],},{title:ORYX.I18N.PropertyWindow.dynamicTransactor,html:'<div id="dynamicTransactorDiv" class="padding10"></div>',listeners:{activate:function(){setDynamicTransactorContent(k,a,n)
}get }}]})
}this.dialog=new Ext.Window({autoScroll:true,autoCreate:true,title:ORYX.I18N.PropertyWindow.premission,height:420,width:464,modal:true,collapsible:false,fixedcenter:true,shadow:true,proxyDrag:true,keys:[{key:27,fn:function(){this.dialog.hide
}get .bind(this)}],items:[b],bodyStyle:"background-color:#FFFFFF",buttons:[{text:ORYX.I18N.PropertyWindow.ok,handler:function(){this.data=this.buildValue(a,k);
this.dialog.hide()
}get .bind(this)},{text:ORYX.I18N.PropertyWindow.cancel,handler:function(){this.dialog.hide()
}get .bind(this)}]});
this.dialog.on(Ext.apply({},this.dialogListeners,{scope:this}));
if(a==ORYX.I18N.PropertyWindow.admin){this.dialog.show()
}else{if(k==null||k==""||k==undefined){Ext.MessageBox.alert("Information",ORYX.I18N.PropertyWindow.formError)
}else{this.dialog.show()
}}}get });
Ext.form.UrgeMessage=Ext.extend(Ext.form.TriggerField,{defaultAutoCreate:{tag:"textarea",rows:1,style:"height:16px;overflow:hidden;"},onTriggerClick:function(){if(this.disabled){return
}if(this.dialog){return
}var b=new Ext.form.TextArea({anchor:"100% 100%",value:this.value,listeners:{render:function(){if(this.value.length==0){b.setValue("There is a workflow you need to sign off, workflow name is <workFolwName>, created by <createdBy> at <createdAt>, plz click this link <url> to operate, thank you.")
}}get ,focus:function(){this.facade.disableEvent(ORYX.CONFIG.EVENT_KEYDOWN)
}get .bind(this)}});
var a=new Ext.Window({layout:"anchor",autoCreate:true,title:ORYX.I18N.PropertyWindow.urgeMessage,height:250,width:250,modal:true,collapsible:false,fixedcenter:true,shadow:true,proxyDrag:true,keys:[{key:27,fn:function(){this.setValue("There is a workflow you need to sign off, workflow name is <workFolwName>, created by <createdBy> at <createdAt>, plz click this link <url> to operate, thank you.");
a.hide()
}get .bind(this)}],items:[b],listeners:{hide:function(){this.fireEvent("dialogClosed",this.value);
a.destroy()
}get .bind(this)},buttons:[{text:ORYX.I18N.PropertyWindow.ok,handler:function(){var c=b.getValue();
this.setValue(c);
this.dataSource.getAt(this.row).set("value",c);
this.dataSource.commitChanges();
a.hide()
}get .bind(this)},{text:ORYX.I18N.PropertyWindow.cancel,handler:function(){if(this.value.length>0){this.setValue(this.value)
}else{this.setValue("There is a workflow you need to sign off, workflow name is <workFolwName>, created by <createdBy> at <createdAt>, plz click this link <url> to operate, thank you.")
}a.hide()
}get .bind(this)}]});
a.show();
b.render();
this.grid.stopEditing();
b.focus(false,100)
}get });
Ext.form.NotificationMessage=Ext.extend(Ext.form.TriggerField,{defaultAutoCreate:{tag:"textarea",rows:1,style:"height:16px;overflow:hidden;"},onTriggerClick:function(){if(this.disabled){return
}if(this.dialog){return
}var b=new Ext.form.TextArea({anchor:"100% 100%",value:this.value,listeners:{render:function(){if(this.value.length==0){b.setValue("There is a workflow need to sign off by organizer : <organizerName>  Workflow name is <workFolwName>")
}}get ,focus:function(){this.facade.disableEvent(ORYX.CONFIG.EVENT_KEYDOWN)
}get .bind(this)}});
var a=new Ext.Window({layout:"anchor",autoCreate:true,title:ORYX.I18N.PropertyWindow.notificationMessage,height:250,width:250,modal:true,collapsible:false,fixedcenter:true,shadow:true,proxyDrag:true,keys:[{key:27,fn:function(){this.setValue("There is a workflow need to sign off by organizer : <organizerName>  Workflow name is <workFolwName>");
a.hide()
}get .bind(this)}],items:[b],listeners:{hide:function(){this.fireEvent("dialogClosed",this.value);
a.destroy()
}get .bind(this)},buttons:[{text:ORYX.I18N.PropertyWindow.ok,handler:function(){var c=b.getValue();
if(c!=""){this.setValue(c)
}else{this.setValue("Organizer not yet set the task")
}this.dataSource.getAt(this.row).set("value",c);
this.dataSource.commitChanges();
a.hide()
}get .bind(this)},{text:ORYX.I18N.PropertyWindow.cancel,handler:function(){if(this.value.length>0){this.setValue(this.value)
}else{this.setValue("There is a workflow need to sign off by organizer : <organizerName>  Workflow name is <workFolwName>")
}a.hide()
}get .bind(this)}]});
a.show();
b.render();
this.grid.stopEditing();
b.focus(false,100)
}get });
Ext.form.FormMemberPermission=function(b,a,d,e,c){Ext.form.FormMemberPermission.superclass.constructor.call(this,b);
this.items=a;
this.key=d;
this.facade=e;
this.grid=c
}get ;
Ext.extend(Ext.form.FormMemberPermission,Ext.form.TriggerField,{triggerClass:"x-form-complex-trigger",readOnly:true,emptyText:ORYX.I18N.PropertyWindow.clickIcon,fieldPermissionCheck:function(a){var b=document.getElementById(a).checked;
if(b==true){return 1
}else{return 0
}}get ,buildValue:function(b,a){var c="${"+generateFieldPermissionJSON(b,a);
var d=",'members':["+getResourceStoreDataAsString(b)+"]";
c=c+""+d+","+generatedynamicTransactorJSON(b,a)+",'previousFormId' : '"+a+"'}";
return c
}get ,getFieldKey:function(){}get ,getValue:function(){if(this.grid){}else{if(this.data==undefined){return""
}else{return this.data
}}}get ,setValue:function(a){if(a.length>0){if(this.data==undefined){this.data=a
}}}get ,keydownHandler:function(a){return false
}get ,dialogListeners:{show:function(){this.onFocus();
this.facade.registerOnEvent(ORYX.CONFIG.EVENT_KEYDOWN,this.keydownHandler.bind(this));
this.facade.disableEvent(ORYX.CONFIG.EVENT_KEYDOWN);
return
}get ,hide:function(){var a=this.dialogListeners;
this.dialog.un("show",a.show,this);
this.dialog.un("hide",a.hide,this);
this.dialog.destroy(true);
delete this.dialog;
this.facade.unregisterOnEvent(ORYX.CONFIG.EVENT_KEYDOWN,this.keydownHandler.bind(this));
this.facade.enableEvent(ORYX.CONFIG.EVENT_KEYDOWN);
this.fireEvent("dialogClosed",this.data);
Ext.form.ComplexListField.superclass.setValue.call(this,this.data)
}get },buildInitial:function(b,a){}get ,afterEdit:function(a){}get ,beforeEdit:function(a){}get ,onTriggerClick:function(){if(this.disabled){return
}if(this.dialog){return
}var e=0;
var c=[];
var h=this.grid.getSelectionModel().getSelectedCell();
var g=this.grid.store.getAt(h[0]);
var a=g.get("name");
var n=g.get("value");
var d=getExtjsJsonObjWithName(a);
var m=this.grid.store.getAt(3);
var k=m.get("value");
var l="";
if(k!=""&&k.length>0){l=k
}else{var p=this.grid.store.getAt(4);
var o=p.get("value");
l=o
}if(n!=""){reInitializeObjectObj(n,a,"membersFields",l)
}else{clearObjects(n,a,"membersFields")
}if(l==null||l==""||l==undefined){Ext.MessageBox.alert("Information",ORYX.I18N.PropertyWindow.formError)
}else{var f=new Ext.Panel({title:ORYX.I18N.PropertyWindow.cts,layout:"table",width:447,height:300,header:false,items:[{id:"tree_select",html:'<div id="resources_select" class="pad-T5 pad-L40"><select id = "resources_options" name = "resources_options" onchange="loadTree(null,\'fieldPermission\',\''+a+'\')" class="fontSize13"><option value="none"> </option><option value="potentialOwner">Group</option><option value="potentialOwner">Role</option><option value="potentialOwner">Department</option><option value="humanPerformer" selected="selected">User</option></select></div><div id="tree2" class="pad-L40 pad-T10"></div>',bodyBorder:false,autoScroll:true,width:225,height:300,listeners:{render:function(){loadTree("humanPerformer","fieldPermission",a)
}get ,}},{region:"east",id:"details-panel",autoScroll:true,collapsible:false,split:true,width:220,height:300,html:'<div class="padding10" id="userInfo_"></div><div class="padding10 " id="resource_order"></div>',}]});
var b=new Ext.TabPanel({renderTo:document.body,activeTab:0,height:368,width:506,defaults:{autoScroll:true},items:[{title:ORYX.I18N.PropertyWindow.members,items:[f],},{title:ORYX.I18N.PropertyWindow.premission,html:'<div id="fieldPermissionDiv" class="padding10"></div>',listeners:{activate:function(){setFieldPermissionContent(d,l,a,n)
}get }},{title:ORYX.I18N.PropertyWindow.dynamicTransactor,html:'<div id="dynamicTransactorDiv" class="padding10"></div>',listeners:{activate:function(){setDynamicTransactorContent(l,a,n)
}get }}]});
this.dialog=new Ext.Window({autoScroll:true,autoCreate:true,title:ORYX.I18N.PropertyWindow.premission,height:450,width:520,modal:true,collapsible:false,fixedcenter:true,shadow:true,proxyDrag:true,keys:[{key:27,fn:function(){this.dialog.hide
}get .bind(this)}],items:[b],bodyStyle:"background-color:#FFFFFF",buttons:[{text:ORYX.I18N.PropertyWindow.ok,handler:function(){var r=getExtjsJsonObjWithName(a);
if(r.getCount()>0){this.data=this.buildValue(a,l);
this.dialog.hide()
}else{var q=getFormCountLength(l);
if(q==0){this.data=this.buildValue(a,l);
this.dialog.hide()
}else{Ext.MessageBox.alert("Information",ORYX.I18N.PropertyWindow.permissionError)
}}}get .bind(this)},{text:ORYX.I18N.PropertyWindow.cancel,handler:function(){this.dialog.hide()
}get .bind(this)}]});
this.dialog.on(Ext.apply({},this.dialogListeners,{scope:this}));
this.dialog.show()
}}get });
Ext.form.ProcessTimeSetting=function(b,a,d,e,c){Ext.form.ProcessTimeSetting.superclass.constructor.call(this,b);
this.items=a;
this.key=d;
this.facade=e;
this.grid=c
}get ;
Ext.extend(Ext.form.ProcessTimeSetting,Ext.form.TriggerField,{triggerClass:"x-form-complex-trigger",readOnly:true,emptyText:ORYX.I18N.PropertyWindow.clickIcon,buildValue:function(a){var b="${";
b+="'processTimeSetting':"+generateTimeSettingJSON()+"}";
return b
}get ,getFieldKey:function(){}get ,getValue:function(){if(this.grid){}else{if(this.data==undefined){return""
}else{return this.data
}}}get ,setValue:function(a){if(a.length>0){if(this.data==undefined){this.data=a
}}}get ,keydownHandler:function(a){return false
}get ,dialogListeners:{show:function(){this.onFocus();
this.facade.registerOnEvent(ORYX.CONFIG.EVENT_KEYDOWN,this.keydownHandler.bind(this));
this.facade.disableEvent(ORYX.CONFIG.EVENT_KEYDOWN);
return
}get ,hide:function(){var a=this.dialogListeners;
this.dialog.un("show",a.show,this);
this.dialog.un("hide",a.hide,this);
this.dialog.destroy(true);
delete this.dialog;
this.facade.unregisterOnEvent(ORYX.CONFIG.EVENT_KEYDOWN,this.keydownHandler.bind(this));
this.facade.enableEvent(ORYX.CONFIG.EVENT_KEYDOWN);
this.fireEvent("dialogClosed",this.data);
Ext.form.ComplexListField.superclass.setValue.call(this,this.data)
}get },buildInitial:function(b,a){}get ,afterEdit:function(a){}get ,beforeEdit:function(a){}get ,onTriggerClick:function(){if(this.disabled){return
}if(this.dialog){return
}var e=0;
var d=[];
var g=this.grid.getSelectionModel().getSelectedCell();
var f=this.grid.store.getAt(g[0]);
var b=f.get("name");
var l=f.get("value");
var k=this.grid.store.getAt(0);
var h=k.get("value");
var a=h;
var c=new Ext.TabPanel({renderTo:document.body,activeTab:0,height:330,width:580,defaults:{autoScroll:true},items:[{title:ORYX.I18N.PropertyWindow.timeSetting+a,html:'<div id="processTimeSetting"> </div>',listeners:{activate:function(){setProcessTimeSetting(l,b,a)
}get }}]});
this.dialog=new Ext.Window({autoScroll:true,autoCreate:true,title:ORYX.I18N.PropertyWindow.processTimeSetting,height:400,width:600,modal:true,collapsible:false,fixedcenter:true,shadow:true,proxyDrag:true,keys:[{key:27,fn:function(){this.dialog.hide
}get .bind(this)}],items:[c],bodyStyle:"background-color:#FFFFFF",buttons:[{text:ORYX.I18N.PropertyWindow.ok,handler:function(){if(validateWarinigDays()){this.data=this.buildValue();
this.dialog.hide()
}else{Ext.MessageBox.alert("Information",ORYX.I18N.PropertyWindow.ptsError)
}}get .bind(this)},{text:ORYX.I18N.PropertyWindow.cancel,handler:function(){this.dialog.hide()
}get .bind(this)}]});
this.dialog.on(Ext.apply({},this.dialogListeners,{scope:this}));
this.dialog.show()
}get });
Ext.form.FormFieldAutomatic=function(b,a,d,e,c){Ext.form.FormFieldAutomatic.superclass.constructor.call(this,b);
this.items=a;
this.key=d;
this.facade=e;
this.grid=c
}get ;
Ext.extend(Ext.form.FormFieldAutomatic,Ext.form.TriggerField,{triggerClass:"x-form-complex-trigger",readOnly:true,emptyText:ORYX.I18N.PropertyWindow.clickIcon,buildValue:function(a){var b="${";
b+="'formFieldAutomatic':"+getFormFieldAutomaticJson(a)+",'previousFormId':'"+a+"'}";
return b
}get ,getFieldKey:function(){}get ,getValue:function(){if(this.grid){}else{if(this.data==undefined){return""
}else{return this.data
}}}get ,setValue:function(a){if(a.length>0){if(this.data==undefined){this.data=a
}}}get ,keydownHandler:function(a){return false
}get ,dialogListeners:{show:function(){this.onFocus();
this.facade.registerOnEvent(ORYX.CONFIG.EVENT_KEYDOWN,this.keydownHandler.bind(this));
this.facade.disableEvent(ORYX.CONFIG.EVENT_KEYDOWN);
return
}get ,hide:function(){var a=this.dialogListeners;
this.dialog.un("show",a.show,this);
this.dialog.un("hide",a.hide,this);
this.dialog.destroy(true);
delete this.dialog;
this.facade.unregisterOnEvent(ORYX.CONFIG.EVENT_KEYDOWN,this.keydownHandler.bind(this));
this.facade.enableEvent(ORYX.CONFIG.EVENT_KEYDOWN);
this.fireEvent("dialogClosed",this.data);
Ext.form.ComplexListField.superclass.setValue.call(this,this.data)
}get },buildInitial:function(b,a){}get ,afterEdit:function(a){}get ,beforeEdit:function(a){}get ,onTriggerClick:function(){if(this.disabled){return
}if(this.dialog){return
}var f=0;
var e=[];
var l=this.grid.getSelectionModel().getSelectedCell();
var k=this.grid.store.getAt(l[0]);
var a=k.get("name");
var q=k.get("value");
var p=this.grid.store.getAt(3);
var m=p.get("value");
var o="";
var c="";
var b=this.facade.getCanvas().children;
for(var g=0;
g<b.length;
g++){if(b[g].properties!=undefined){var h=JSON.parse(b[g].properties.toJSON().replace(/-/g,"$").toString());
if(h.oryx$form!=undefined||h.oryx$jspform!=undefined){if(h.oryx$form!=""&&h.oryx$form.length>0){c+=h.oryx$form+","
}else{c+=h.oryx$jspform+","
}}}}if(c!=""){var n=c;
c=(n.slice(-1)==",")?c.substring(0,c.length-1):c
}if(m!=""&&m.length>0){o=m
}else{var s=this.grid.store.getAt(4);
var r=s.get("value");
o=r
}var d=new Ext.TabPanel({renderTo:document.body,activeTab:0,height:344,width:610,defaults:{autoScroll:true},items:[{title:ORYX.I18N.PropertyWindow.opinion,html:'<div style="margin: 10px;" id="opinionDiv"></div><br/><table id="opinionFormFillTable"  cellpadding="0" cellspacing="0"  width="100%" border="1"><tr><td></td><td><center><span>'+ORYX.I18N.PropertyWindow.fillForm+"</span></center></td><td><center><span>"+ORYX.I18N.PropertyWindow.fillField+"</span></center></td><td><center><span>"+ORYX.I18N.PropertyWindow.fillValue+"</span></center></td><td><center><span>"+ORYX.I18N.PropertyWindow.fillType+'</span></center></td><td style="display:none"><span>From Form</span></td><td style="display:none"><span>From Form Field</span></td><td><center><span>'+ORYX.I18N.PropertyWindow.isSkip+'</span></center></td></tr></table><br/><center><input type="button" class="btn btn-primary" style="font-size:11px;padding:2px 7px;" id="delete_formFieldOpinion" value="Delete" onClick="deleteSelectedFormField()"/>&nbsp&nbsp<input type="button" class="btn btn-primary" style="font-size:11px;padding:2px 7px;" id="delete_allFormFieldOpinion" value="DeleteAll" onClick="deleteAllFormField()"/></center>',listeners:{activate:function(){setFormFieldAutomaticField(q,a,o,c)
}get }}]});
this.dialog=new Ext.Window({autoScroll:true,autoCreate:true,title:ORYX.I18N.PropertyWindow.formFieldAutomatic,height:421,width:625,modal:true,collapsible:false,fixedcenter:true,shadow:true,proxyDrag:true,keys:[{key:27,fn:function(){this.dialog.hide
}get .bind(this)}],items:[d],bodyStyle:"background-color:#FFFFFF",buttons:[{text:ORYX.I18N.PropertyWindow.ok,handler:function(){this.data=this.buildValue(o);
this.dialog.hide()
}get .bind(this)},{text:ORYX.I18N.PropertyWindow.cancel,handler:function(){this.dialog.hide()
}get .bind(this)}]});
this.dialog.on(Ext.apply({},this.dialogListeners,{scope:this}));
this.dialog.show()
}get });
Ext.form.FormFieldPermission=function(b,a,d,e,c){Ext.form.FormFieldPermission.superclass.constructor.call(this,b);
this.items=a;
this.key=d;
this.facade=e;
this.grid=c
}get ;
Ext.extend(Ext.form.FormFieldPermission,Ext.form.TriggerField,{triggerClass:"x-form-complex-trigger",readOnly:true,emptyText:ORYX.I18N.PropertyWindow.clickIcon,fieldPermissionCheck:function(a){var b=document.getElementById(a).checked;
if(b==true){return 1
}else{return 0
}}get ,buildValue:function(b,a){var c="${"+generateFieldPermissionJSON(b,a)+",'previousFormId' : '"+a+"'}";
return c
}get ,getFieldKey:function(){}get ,getValue:function(){if(this.grid){}else{if(this.data==undefined){return""
}else{return this.data
}}}get ,setValue:function(a){if(a.length>0){if(this.data==undefined){this.data=a
}}}get ,keydownHandler:function(a){return false
}get ,dialogListeners:{show:function(){this.onFocus();
this.facade.registerOnEvent(ORYX.CONFIG.EVENT_KEYDOWN,this.keydownHandler.bind(this));
this.facade.disableEvent(ORYX.CONFIG.EVENT_KEYDOWN);
return
}get ,hide:function(){var a=this.dialogListeners;
this.dialog.un("show",a.show,this);
this.dialog.un("hide",a.hide,this);
this.dialog.destroy(true);
delete this.dialog;
this.facade.unregisterOnEvent(ORYX.CONFIG.EVENT_KEYDOWN,this.keydownHandler.bind(this));
this.facade.enableEvent(ORYX.CONFIG.EVENT_KEYDOWN);
this.fireEvent("dialogClosed",this.data);
Ext.form.ComplexListField.superclass.setValue.call(this,this.data)
}get },buildInitial:function(b,a){}get ,afterEdit:function(a){}get ,beforeEdit:function(a){}get ,onTriggerClick:function(){if(this.disabled){return
}if(this.dialog){return
}var e=0;
var c=[];
var g=this.grid.getSelectionModel().getSelectedCell();
var f=this.grid.store.getAt(g[0]);
var a=f.get("name");
var m=f.get("value");
var d=getExtjsJsonObjWithName(a);
var l=this.grid.store.getAt(3);
var h=l.get("value");
var k="";
if(h!=""&&h.length>0){k=h
}else{var o=this.grid.store.getAt(4);
var n=o.get("value");
k=n
}if(m!=""){reInitializeObjectObj(m,a,"FieldPermission",k)
}else{clearObjects(m,a,"FieldPermission")
}if(k==null||k==""||k==undefined){Ext.MessageBox.alert("Information",ORYX.I18N.PropertyWindow.formError)
}else{var b=new Ext.TabPanel({renderTo:document.body,activeTab:0,height:329,width:450,defaults:{autoScroll:true},items:[{title:ORYX.I18N.PropertyWindow.premission,html:'<div id="fieldPermissionDiv" class="padding10"></div>',listeners:{activate:function(){setFieldPermissionContent(d,k,a,m)
}get }}]});
this.dialog=new Ext.Window({autoScroll:true,autoCreate:true,title:ORYX.I18N.PropertyWindow.premission,height:420,width:464,modal:true,collapsible:false,fixedcenter:true,shadow:true,proxyDrag:true,keys:[{key:27,fn:function(){this.dialog.hide
}get .bind(this)}],items:[b],bodyStyle:"background-color:#FFFFFF",buttons:[{text:ORYX.I18N.PropertyWindow.ok,handler:function(){this.data=this.buildValue(a,k);
this.dialog.hide()
}get .bind(this)},{text:ORYX.I18N.PropertyWindow.cancel,handler:function(){this.dialog.hide()
}get .bind(this)}]});
this.dialog.on(Ext.apply({},this.dialogListeners,{scope:this}));
this.dialog.show()
}}get });
Ext.form.TransactorRelationship=function(b,a,d,e,c){Ext.form.TransactorRelationship.superclass.constructor.call(this,b);
this.items=a;
this.key=d;
this.facade=e;
this.grid=c
}get ;
Ext.extend(Ext.form.TransactorRelationship,Ext.form.TriggerField,{triggerClass:"x-form-complex-trigger",readOnly:true,emptyText:ORYX.I18N.PropertyWindow.clickIcon,buildValue:function(){return generateTransactorRelationshipJson()
}get ,getFieldKey:function(){}get ,getValue:function(){if(this.grid){}else{if(this.data==undefined){return""
}else{return this.data
}}}get ,setValue:function(a){if(a.length>0){if(this.data==undefined){this.data=a
}}}get ,keydownHandler:function(a){return false
}get ,dialogListeners:{show:function(){this.onFocus();
this.facade.registerOnEvent(ORYX.CONFIG.EVENT_KEYDOWN,this.keydownHandler.bind(this));
this.facade.disableEvent(ORYX.CONFIG.EVENT_KEYDOWN);
return
}get ,hide:function(){var a=this.dialogListeners;
this.dialog.un("show",a.show,this);
this.dialog.un("hide",a.hide,this);
this.dialog.destroy(true);
delete this.dialog;
this.facade.unregisterOnEvent(ORYX.CONFIG.EVENT_KEYDOWN,this.keydownHandler.bind(this));
this.facade.enableEvent(ORYX.CONFIG.EVENT_KEYDOWN);
this.fireEvent("dialogClosed",this.data);
Ext.form.ComplexListField.superclass.setValue.call(this,this.data)
}get },buildInitial:function(b,a){}get ,afterEdit:function(a){}get ,beforeEdit:function(a){}get ,onTriggerClick:function(){if(this.disabled){return
}if(this.dialog){return
}var e=0;
var g=[];
var f=this.grid.getSelectionModel().getSelectedCell();
var c=this.grid.store.getAt(f[0]);
var d=c.get("name");
var a=c.get("value");
var b=new Ext.Panel({id:"relationship_panel",border:false,align:"middle",bodyStyle:"text-align:center",items:[{id:"transactor_relationship_panel",html:'<div id="transactorRelationshipDiv" align="center"><table id="transactorRelationshipTable" cellpadding="0" cellspacing="0"width="100%" border="0"></table><table id="transactorRelationshipDataTable" cellpadding="0" cellspacing="0" width="100%" border="1"></table></div>',bodyBorder:false,autoScroll:true}],listeners:{render:function(){setTimeout(function(){setTransactorRelationship(a,d)
}get ,200)
}get }});
this.dialog=new Ext.Window({autoScroll:true,autoCreate:true,title:ORYX.I18N.PropertyWindow.transactorRelationship,height:350,width:500,modal:true,collapsible:false,fixedcenter:true,shadow:true,proxyDrag:true,keys:[{key:27,fn:function(){this.dialog.hide
}get .bind(this)}],items:[b],bodyStyle:"background-color:#FFFFFF",buttons:[{text:ORYX.I18N.PropertyWindow.ok,handler:function(){this.data=this.buildValue();
this.dialog.hide()
}get .bind(this)},{text:ORYX.I18N.PropertyWindow.cancel,handler:function(){this.dialog.hide()
}get .bind(this)}]});
this.dialog.on(Ext.apply({},this.dialogListeners,{scope:this,}));
this.dialog.show()
}get });
Ext.form.ChooseCondition=function(b,a,d,e,c){Ext.form.ChooseCondition.superclass.constructor.call(this,b);
this.items=a;
this.key=d;
this.facade=e;
this.grid=c
}get ;
Ext.extend(Ext.form.ChooseCondition,Ext.form.TriggerField,{triggerClass:"x-form-complex-trigger",readOnly:true,emptyText:ORYX.I18N.PropertyWindow.clickIcon,buildValue:function(b,a){return generateConitionExpressionJson(a)
}get ,getFieldKey:function(){}get ,getValue:function(){if(this.grid){}else{if(this.data==undefined){return""
}else{return this.data
}}}get ,setValue:function(a){if(a.length>0){if(this.data==undefined){this.data=a
}}}get ,keydownHandler:function(a){return false
}get ,dialogListeners:{show:function(){this.onFocus();
this.facade.registerOnEvent(ORYX.CONFIG.EVENT_KEYDOWN,this.keydownHandler.bind(this));
this.facade.disableEvent(ORYX.CONFIG.EVENT_KEYDOWN);
return
}get ,hide:function(){var a=this.dialogListeners;
this.dialog.un("show",a.show,this);
this.dialog.un("hide",a.hide,this);
this.dialog.destroy(true);
delete this.dialog;
this.facade.unregisterOnEvent(ORYX.CONFIG.EVENT_KEYDOWN,this.keydownHandler.bind(this));
this.facade.enableEvent(ORYX.CONFIG.EVENT_KEYDOWN);
this.fireEvent("dialogClosed",this.data);
Ext.form.ComplexListField.superclass.setValue.call(this,this.data)
}get },buildInitial:function(b,a){}get ,afterEdit:function(a){}get ,beforeEdit:function(a){}get ,onTriggerClick:function(){if(this.disabled){return
}if(this.dialog){return
}var f=0;
var b=[];
var u=this.grid.getSelectionModel().getSelectedCell();
var e=this.grid.store.getAt(u[0]);
var n=e.get("name");
var c=e.get("value");
if(c!=""){reInitializeChooseConditionObject(c)
}else{clearObjects(c,n,"ChooseCondition")
}var k=this.facade.getCanvas().children;
var g="";
for(var v=0;
v<k.length;
v++){if(k[v].outgoing[0]!=undefined){var d=this.facade.getCanvas().edges;
for(var s=0;
s<d.length;
s++){for(var w=0;
w<k[v].outgoing.length;
w++){if(k[v].outgoing[w].id==d[s].id&&conditionExpressionId==d[s].id){var r=JSON.parse(k[v].properties.toJSON().replace(/-/g,"$").toString());
if(r.oryx$form!=undefined){if(r.oryx$form!=""&&r.oryx$form.length>0){g=r.oryx$form
}else{g=r.oryx$jspform
}}else{var l=JSON.parse(k[v].incoming[0].incoming[0].properties.toJSON().replace(/-/g,"$").toString());
if(l.oryx$form!=""&&l.oryx$form.length>0){g=l.oryx$form
}else{g=l.oryx$jspform
}}}}}}}if(g!=""){var h=new Ext.data.Store({autoLoad:false,proxy:new Ext.data.HttpProxy({url:"/form/getFormById",method:"GET"}),baseParams:{formId:g},reader:new Ext.data.JsonReader({root:"forms"},[{name:"id"},{name:"formName"}])})
}var a="";
var m="";
var q=new Ext.form.TextField({readOnly:true,value:"",listeners:{render:function(y){if(h!=undefined){h.load({callback:function(){y.setValue(h.reader.jsonData.forms[0].formName)
}get })
}m=g;
conditionExpression(g,c)
}get }});
var x=new Ext.form.ComboBox({store:h,displayField:"formName",valueField:"id",lazyRender:true,typeAhead:true,mode:"local",forceSelection:true,triggerAction:"all",emptyText:"Select a form...",selectOnFocus:true,listeners:{select:function(z,A,y){m=this.getValue();
conditionExpression(this.getValue(),c)
}get }});
var p=new Ext.Panel({border:false,align:"middle",bodyStyle:"text-align:center",items:[{id:"choose_combo",bodyStyle:"padding-left:10px;",align:"middle",items:[q],bodyBorder:false,autoScroll:true,},{id:"choose_condition_html",html:'<div id="chooseContentDiv" align="center"></div><br/><table  id="condition_Expression"  cellpadding="0" cellspacing="0"  width="100%" border="1"><tr><td><span><b>'+ORYX.I18N.PropertyWindow.parameter+"</b></span></td><td><span><b>"+ORYX.I18N.PropertyWindow.relation+"</b></span></td><td><span><b>"+ORYX.I18N.PropertyWindow.value+"</b></span></td><td><span><b>"+ORYX.I18N.PropertyWindow.join1+"</b></span></td><td><span><b>"+ORYX.I18N.PropertyWindow.operation+"</b></span></td></tr></table>",bodyBorder:false,autoScroll:true,bodyStyle:"padding:0 40px;",}]});
var o=new Ext.Panel({border:false,align:"middle",bodyStyle:"text-align:center",items:[{id:"choose_script_condition_html",html:'<div id="chooseContentDiv" align="center"><textarea id="sequencescriptfunction" name="sequencescriptfunction" style="width:450px;height:220px;" ></textarea></div>',bodyBorder:false,autoScroll:true,bodyStyle:"padding-top:5px;",}]});
var t=new Ext.Panel({id:"condition_panel",border:false,align:"middle",bodyStyle:"text-align:center",items:[{id:"choose_combo",html:'<div id="expression_condition_panel"><table style="margin:0 auto" width="28%"><tr><td><input type="radio" id="expScriptExpression" name="expScript" checked="true" onclick="changeSequenceFlow(\'expression\')" value="expression"></td><td><b>Expression</b></td><td><input type="radio" id="expScriptJavascript" name="expScript" onclick="changeSequenceFlow(\'javascript\')" value="Javascript"></td><td><b>Javascript</b></td></div>',bodyBorder:false,autoScroll:true,bodyStyle:"padding-bottom:10px;padding-top:5px;",},{id:"condition_expression_panel",items:[p],bodyBorder:false,autoScroll:true},{id:"condition_script_panel",items:[o],bodyBorder:false,autoScroll:true,}],listeners:{render:function(){setTimeout(function(){changeSequenceFlow("expression")
}get ,200)
}get }});
this.dialog=new Ext.Window({autoScroll:true,autoCreate:true,title:ORYX.I18N.PropertyWindow.chooseCondition,height:350,width:600,modal:true,collapsible:false,fixedcenter:true,shadow:true,proxyDrag:true,keys:[{key:27,fn:function(){this.dialog.hide
}get .bind(this)}],items:[t],bodyStyle:"background-color:#FFFFFF",buttons:[{text:ORYX.I18N.PropertyWindow.ok,handler:function(){this.data=this.buildValue(a,m);
this.dialog.hide()
}get .bind(this)},{text:ORYX.I18N.PropertyWindow.cancel,handler:function(){this.dialog.hide()
}get .bind(this)}]});
this.dialog.on(Ext.apply({},this.dialogListeners,{scope:this,}));
this.dialog.show()
}get });
var selectedUser="";
Ext.form.CustomTreeSelect=function(b,a,d,e,c){Ext.form.CustomTreeSelect.superclass.constructor.call(this,b);
this.items=a;
this.key=d;
this.facade=e;
this.grid=c
}get ;
Ext.extend(Ext.form.CustomTreeSelect,Ext.form.TriggerField,{triggerClass:"x-form-complex-trigger",readOnly:true,emptyText:ORYX.I18N.PropertyWindow.clickIcon,buildValue:function(){return""
}get ,getFieldKey:function(){return this.key
}get ,getValue:function(){if(this.grid){return this.buildValue()
}else{if(this.data==undefined){return""
}else{return this.data
}}}get ,setValue:function(a){if(a.length>0){if(this.data==undefined){this.data=a
}}}get ,keydownHandler:function(a){return false
}get ,dialogListeners:{show:function(){this.onFocus();
this.facade.registerOnEvent(ORYX.CONFIG.EVENT_KEYDOWN,this.keydownHandler.bind(this));
this.facade.disableEvent(ORYX.CONFIG.EVENT_KEYDOWN);
return
}get ,hide:function(){var a=this.dialogListeners;
this.dialog.un("show",a.show,this);
this.dialog.un("hide",a.hide,this);
this.dialog.destroy(true);
delete this.dialog;
this.facade.unregisterOnEvent(ORYX.CONFIG.EVENT_KEYDOWN,this.keydownHandler.bind(this));
this.facade.enableEvent(ORYX.CONFIG.EVENT_KEYDOWN);
this.fireEvent("dialogClosed",this.data);
Ext.form.CustomTreeSelect.superclass.setValue.call(this,this.data)
}get },buildInitial:function(f,a){var b=new Hash();
for(var c=0;
c<a.length;
c++){var e=a[c].id();
b[e]=a[c].value()
}var d=Ext.data.Record.create(f);
return new d(b)
}get ,afterEdit:function(a){a.grid.getStore().commitChanges()
}get ,beforeEdit:function(h){var a=this.grid.getView().getScrollState();
var b=h.column;
var p=h.row;
var e=this.grid.getColumnModel().config[b].id;
for(var g=0;
g<this.items.length;
g++){var o=this.items[g];
var m=o.disable();
if(m!=undefined){var n=this.grid.getStore().getAt(p).get(o.id());
for(var d=0;
d<m.length;
d++){var f=m[d];
if(f.value==n){for(var c=0;
c<f.items.length;
c++){var l=f.items[c];
if(l==e){this.grid.getColumnModel().getCellEditor(b,p).disable();
return
}}}}}}this.grid.getColumnModel().getCellEditor(b,p).enable()
}get ,onTriggerClick:function(){if(this.disabled){return
}if(this.dialog){return
}var f=0;
var c=[];
var k=this.grid.getSelectionModel().getSelectedCell();
var h=this.grid.store.getAt(k[0]);
var a=h.get("name");
var p=h.get("value");
var o=this.grid.store.getAt(3);
var l=o.get("value");
var m="";
if(l!=""&&l.length>0){m=l
}else{var r=this.grid.store.getAt(4);
var q=r.get("value");
m=q
}var n=this.grid.store.getAt(24);
var e=n.get("value");
if(p!=""){reInitializeObjectObj(p,a,"membersFields",m)
}else{clearObjects(p,a,"membersFields")
}var d=getExtjsJsonObjWithName(a);
if(m==null||m==""||m==undefined){Ext.MessageBox.alert("Information",ORYX.I18N.PropertyWindow.formError)
}else{var g=new Ext.Panel({title:ORYX.I18N.PropertyWindow.cts,layout:"table",width:447,height:300,header:false,items:[{id:"tree_select",html:'<div id="resources_select" class="pad-T5 pad-L40"><select id = "resources_options" name = "resources_options" onchange="loadTree(null,\'customTree\',\''+a+'\')" class="fontSize13"><option value="none"> </option><option value="potentialOwner">Group</option><option value="potentialOwner">Role</option><option value="potentialOwner">Department</option><option value="humanPerformer" selected="selected">User</option></select></div><div id="tree2" class="pad-L40 pad-T10"></div>',bodyBorder:false,autoScroll:true,width:225,height:300,listeners:{render:function(){loadTree("humanPerformer","customTree",a,e)
}get ,}},{region:"east",id:"details-panel",autoScroll:true,collapsible:false,split:true,width:220,height:300,html:'<div class="padding10" id="userInfo_"></div><div class="padding10 " id="resource_order"></div>',}]});
var b=new Ext.TabPanel({renderTo:document.body,activeTab:0,height:335,width:450,defaults:{autoScroll:true},items:[{title:ORYX.I18N.PropertyWindow.members,items:[g],},{title:ORYX.I18N.PropertyWindow.premission,html:'<div id="fieldPermissionDiv" class="padding10"></div>',listeners:{activate:function(){setFieldPermissionContent(d,m,a,p)
}get }},{title:ORYX.I18N.PropertyWindow.dynamicTransactor,html:'<div id="dynamicTransactorDiv" class="padding10"></div>',listeners:{activate:function(){setDynamicTransactorContent(m,a,p)
}get }}]});
this.dialog=new Ext.Window({autoScroll:true,autoCreate:true,title:ORYX.I18N.PropertyWindow.customTreeSelect,height:420,width:464,modal:true,collapsible:false,fixedcenter:true,shadow:true,proxyDrag:true,keys:[{key:27,fn:function(){this.dialog.hide
}get .bind(this)}],items:[b],bodyStyle:"background-color:#FFFFFF",buttons:[{text:ORYX.I18N.PropertyWindow.submit,handler:function(){var v=getExtjsJsonObjWithName(a);
if(v.getCount()>0){if(isResourceOrderDivVisible()){var u=addOrderToResources();
if(u){this.data=assignOrderAndGetResourceData(a,m);
this.dialog.hide()
}}else{showResourceOrderDiv();
renderResourceOrderView(a,e)
}}else{if(isResourceOrderDivVisible()){this.data=assignOrderAndGetResourceData(a,m)
}var s=this.dialog;
var t=0;
Ext.Ajax.request({url:"/bpm/form/getAllFormFields",params:{formId:m},asynchronous:false,method:"GET",failure:function(w,x){}get ,success:function(x,z){var y=x.responseText;
var w={};
w=JSON.parse(y.toString());
var B=w.forms;
t=B.length;
if(t==0){if(isResourceOrderDivVisible()){var A=addOrderToResources();
if(A){s.hide()
}}else{showResourceOrderDiv();
renderResourceOrderView(a,e)
}}else{Ext.MessageBox.alert("Information",ORYX.I18N.PropertyWindow.permissionError)
}}get ,})
}}get .bind(this)},{text:ORYX.I18N.PropertyWindow.cancel,handler:function(){this.dialog.hide()
}get .bind(this)}],listeners:{show:function(){if(resourceStore.getCount()!=0){showResourceOrderDiv();
renderResourceOrderView(a,e)
}}get ,}});
this.dialog.on(Ext.apply({},this.dialogListeners,{scope:this}));
this.dialog.show()
}}get });
if(!ORYX.Plugins){ORYX.Plugins=new Object()
}ORYX.Plugins.CanvasResize=Clazz.extend({construct:function(a){this.facade=a;
new ORYX.Plugins.CanvasResizeButton(this.facade.getCanvas(),"N",this.resize.bind(this));
new ORYX.Plugins.CanvasResizeButton(this.facade.getCanvas(),"W",this.resize.bind(this));
new ORYX.Plugins.CanvasResizeButton(this.facade.getCanvas(),"E",this.resize.bind(this));
new ORYX.Plugins.CanvasResizeButton(this.facade.getCanvas(),"S",this.resize.bind(this))
}get ,resize:function(a,c){resizeCanvas=function(l,m,o){var f=o.getCanvas();
var n=f.bounds;
var h=o.getCanvas().getHTMLContainer().parentNode.parentNode;
if(l=="E"||l=="W"){f.setSize({width:(n.width()+m)*f.zoomLevel,height:(n.height())*f.zoomLevel})
}else{if(l=="S"||l=="N"){f.setSize({width:(n.width())*f.zoomLevel,height:(n.height()+m)*f.zoomLevel})
}}if(l=="N"||l=="W"){var g=l=="N"?{x:0,y:m}:{x:m,y:0};
f.getChildNodes(false,function(q){q.bounds.moveBy(g)
}get );
var k=f.getChildEdges().findAll(function(q){return q.getAllDockedShapes().length>0
}get );
var p=k.collect(function(q){return q.dockers.findAll(function(r){return !r.getDockedShape()
}get )
}get ).flatten();
p.each(function(q){q.bounds.moveBy(g)
}get )
}else{if(l=="S"){h.scrollTop+=m
}else{if(l=="E"){h.scrollLeft+=m
}}}f.update();
o.updateSelection()
}get ;
var b=ORYX.Core.Command.extend({construct:function(f,h,g){this.position=f;
this.extentionSize=h;
this.facade=g
}get ,execute:function(){resizeCanvas(this.position,this.extentionSize,this.facade)
}get ,rollback:function(){resizeCanvas(this.position,-this.extentionSize,this.facade)
}get ,update:function(){}get });
var d=ORYX.CONFIG.CANVAS_RESIZE_INTERVAL;
if(c){d=-d
}var e=new b(a,d,this.facade);
this.facade.executeCommands([e])
}get });
ORYX.Plugins.CanvasResizeButton=Clazz.extend({construct:function(c,h,n){this.canvas=c;
var k=c.getHTMLContainer().parentNode.parentNode.parentNode;
window.myParent=k;
var d=k.firstChild;
var b=d.firstChild.firstChild;
var a=ORYX.Editor.graft("http://www.w3.org/1999/xhtml",k,["div",{"class":"canvas_resize_indicator canvas_resize_indicator_grow "+h,title:ORYX.I18N.RESIZE.tipGrow+ORYX.I18N.RESIZE[h]}]);
var e=ORYX.Editor.graft("http://www.w3.org/1999/xhtml",k,["div",{"class":"canvas_resize_indicator canvas_resize_indicator_shrink "+h,title:ORYX.I18N.RESIZE.tipShrink+ORYX.I18N.RESIZE[h]}]);
var f=60;
var m=function(p){if(p.target!=k&&p.target!=d&&p.target!=d.firstChild&&p.target!=b&&p.target!=d){return false
}var s=p.layerX;
var r=p.layerY;
if((s-d.scrollLeft)<0||Ext.isSafari){s+=d.scrollLeft
}if((r-d.scrollTop)<0||Ext.isSafari){r+=d.scrollTop
}if(h=="N"){return r<f+d.firstChild.offsetTop
}else{if(h=="W"){return s<f+d.firstChild.offsetLeft
}else{if(h=="E"){var o=(d.offsetWidth-(d.firstChild.offsetLeft+d.firstChild.offsetWidth));
if(o<0){o=0
}return s>d.scrollWidth-o-f
}else{if(h=="S"){var q=(d.offsetHeight-(d.firstChild.offsetTop+d.firstChild.offsetHeight));
if(q<0){q=0
}return r>d.scrollHeight-q-f
}}}}return false
}get ;
var l=(function(){a.show();
var p,v,o,u;
try{var t=this.canvas.getRootNode().childNodes[1].getBBox();
p=t.x;
v=t.y;
o=t.x+t.width;
u=t.y+t.height
}catch(s){this.canvas.getChildShapes(true).each(function(y){var A=y.absoluteBounds();
var z=A.upperLeft();
var w=A.lowerRight();
if(p==undefined){p=z.x;
v=z.y;
o=w.x;
u=w.y
}else{p=Math.min(p,z.x);
v=Math.min(v,z.y);
o=Math.max(o,w.x);
u=Math.max(u,w.y)
}}get )
}var x=c.bounds.width();
var r=c.bounds.height();
var q=c.getChildNodes().size()==0;
if(h=="N"&&(v>ORYX.CONFIG.CANVAS_RESIZE_INTERVAL||(q&&r>ORYX.CONFIG.CANVAS_RESIZE_INTERVAL))){e.show()
}else{if(h=="E"&&(x-o)>ORYX.CONFIG.CANVAS_RESIZE_INTERVAL){e.show()
}else{if(h=="S"&&(r-u)>ORYX.CONFIG.CANVAS_RESIZE_INTERVAL){e.show()
}else{if(h=="W"&&(p>ORYX.CONFIG.CANVAS_RESIZE_INTERVAL||(q&&x>ORYX.CONFIG.CANVAS_RESIZE_INTERVAL))){e.show()
}else{e.hide()
}}}}}get ).bind(this);
var g=function(){a.hide();
e.hide()
}get ;
d.addEventListener(ORYX.CONFIG.EVENT_MOUSEMOVE,function(o){if(m(o)){l()
}else{g()
}}get ,false);
a.addEventListener(ORYX.CONFIG.EVENT_MOUSEOVER,function(o){l()
}get ,true);
e.addEventListener(ORYX.CONFIG.EVENT_MOUSEOVER,function(o){l()
}get ,true);
k.addEventListener(ORYX.CONFIG.EVENT_MOUSEOUT,function(o){g()
}get ,true);
g();
a.addEventListener("click",function(){n(h);
l()
}get ,true);
e.addEventListener("click",function(){n(h,true);
l()
}get ,true)
}get });
if(!ORYX.Plugins){ORYX.Plugins=new Object()
}ORYX.Plugins.View={facade:undefined,construct:function(b,a){this.facade=b;
this.zoomLevel=1;
this.maxFitToScreenLevel=1.5;
this.minZoomLevel=0.1;
this.maxZoomLevel=2.5;
this.diff=5;
a.properties.each(function(c){if(c.zoomLevel){this.zoomLevel=Number(1)
}if(c.maxFitToScreenLevel){this.maxFitToScreenLevel=Number(c.maxFitToScreenLevel)
}if(c.minZoomLevel){this.minZoomLevel=Number(c.minZoomLevel)
}if(c.maxZoomLevel){this.maxZoomLevel=Number(c.maxZoomLevel)
}}get .bind(this))
}get ,setAFixZoomLevel:function(a){this.zoomLevel=a;
this._checkZoomLevelRange();
this.zoom(1)
}get ,zoom:function(d){this.zoomLevel*=d;
var h=this.facade.getCanvas().getHTMLContainer().parentNode.parentNode;
var c=this.facade.getCanvas();
var g=c.bounds.width()*this.zoomLevel;
var a=c.bounds.height()*this.zoomLevel;
var f=(c.node.parentNode.parentNode.parentNode.offsetHeight-a)/2;
f=f>20?f-20:0;
c.node.parentNode.parentNode.style.marginTop=f+"px";
f+=5;
c.getHTMLContainer().style.top=f+"px";
var b=h.scrollTop-Math.round((c.getHTMLContainer().parentNode.getHeight()-a)/2)+this.diff;
var e=h.scrollLeft-Math.round((c.getHTMLContainer().parentNode.getWidth()-g)/2)+this.diff;
c.setSize({width:g,height:a},true);
c.node.setAttributeNS(null,"transform","scale("+this.zoomLevel+")");
this.facade.updateSelection();
h.scrollTop=b;
h.scrollLeft=e;
c.zoomLevel=this.zoomLevel
}get ,zoomFitToModel:function(){var h=this.facade.getCanvas().getHTMLContainer().parentNode.parentNode;
var b=h.getHeight()-30;
var d=h.getWidth()-30;
var c=this.facade.getCanvas().getChildShapes();
if(!c||c.length<1){return false
}var g=c[0].absoluteBounds().clone();
c.each(function(k){g.include(k.absoluteBounds().clone())
}get );
var f=d/g.width();
var a=b/g.height();
var e=a<f?a:f;
if(e>this.maxFitToScreenLevel){e=this.maxFitToScreenLevel
}this.setAFixZoomLevel(e);
h.scrollTop=Math.round(g.upperLeft().y*this.zoomLevel)-5;
h.scrollLeft=Math.round(g.upperLeft().x*this.zoomLevel)-5
}get ,_checkSize:function(){var a=this.facade.getCanvas().getHTMLContainer().parentNode;
var b=Math.min((a.parentNode.getWidth()/a.getWidth()),(a.parentNode.getHeight()/a.getHeight()));
return 1.05>b
}get ,_checkZoomLevelRange:function(){if(this.zoomLevel<this.minZoomLevel){this.zoomLevel=this.minZoomLevel
}if(this.zoomLevel>this.maxZoomLevel){this.zoomLevel=this.maxZoomLevel
}}get };
ORYX.Plugins.View=Clazz.extend(ORYX.Plugins.View);
if(!ORYX.Plugins){ORYX.Plugins=new Object()
}ORYX.Plugins.DragDropResize=ORYX.Plugins.AbstractPlugin.extend({construct:function(b){this.facade=b;
this.currentShapes=[];
this.toMoveShapes=[];
this.distPoints=[];
this.isResizing=false;
this.dragEnable=false;
this.dragIntialized=false;
this.edgesMovable=true;
this.offSetPosition={x:0,y:0};
this.faktorXY={x:1,y:1};
this.containmentParentNode;
this.isAddingAllowed=false;
this.isAttachingAllowed=false;
this.callbackMouseMove=this.handleMouseMove.bind(this);
this.callbackMouseUp=this.handleMouseUp.bind(this);
var a=this.facade.getCanvas().getSvgContainer();
this.selectedRect=new ORYX.Plugins.SelectedRect(a);
if(ORYX.CONFIG.SHOW_GRIDLINE){this.vLine=new ORYX.Plugins.GridLine(a,ORYX.Plugins.GridLine.DIR_VERTICAL);
this.hLine=new ORYX.Plugins.GridLine(a,ORYX.Plugins.GridLine.DIR_HORIZONTAL)
}a=this.facade.getCanvas().getHTMLContainer();
this.scrollNode=this.facade.getCanvas().rootNode.parentNode.parentNode;
this.resizerSE=new ORYX.Plugins.Resizer(a,"southeast",this.facade);
this.resizerSE.registerOnResize(this.onResize.bind(this));
this.resizerSE.registerOnResizeEnd(this.onResizeEnd.bind(this));
this.resizerSE.registerOnResizeStart(this.onResizeStart.bind(this));
this.resizerNW=new ORYX.Plugins.Resizer(a,"northwest",this.facade);
this.resizerNW.registerOnResize(this.onResize.bind(this));
this.resizerNW.registerOnResizeEnd(this.onResizeEnd.bind(this));
this.resizerNW.registerOnResizeStart(this.onResizeStart.bind(this));
this.facade.registerOnEvent(ORYX.CONFIG.EVENT_MOUSEDOWN,this.handleMouseDown.bind(this))
}get ,handleMouseDown:function(d,c){if(!this.dragBounds||!this.currentShapes.member(c)||!this.toMoveShapes.length){return
}this.dragEnable=true;
this.dragIntialized=true;
this.edgesMovable=true;
var b=this.facade.getCanvas().node.getScreenCTM();
this.faktorXY.x=b.a;
this.faktorXY.y=b.d;
var e=this.dragBounds.upperLeft();
this.offSetPosition={x:Event.pointerX(d)-(e.x*this.faktorXY.x),y:Event.pointerY(d)-(e.y*this.faktorXY.y)};
this.offsetScroll={x:this.scrollNode.scrollLeft,y:this.scrollNode.scrollTop};
document.documentElement.addEventListener(ORYX.CONFIG.EVENT_MOUSEMOVE,this.callbackMouseMove,false);
document.documentElement.addEventListener(ORYX.CONFIG.EVENT_MOUSEUP,this.callbackMouseUp,true);
return
}get ,handleMouseUp:function(d){this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_HIGHLIGHT_HIDE,highlightId:"dragdropresize.contain"});
this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_HIGHLIGHT_HIDE,highlightId:"dragdropresize.attached"});
if(this.dragEnable){if(!this.dragIntialized){this.afterDrag();
if(this.isAttachingAllowed&&this.toMoveShapes.length==1&&this.toMoveShapes[0] instanceof ORYX.Core.Node&&this.toMoveShapes[0].dockers.length>0){var b=this.facade.eventCoordinates(d);
var e=this.toMoveShapes[0].dockers[0];
var c=ORYX.Core.Command.extend({construct:function(k,f,h,g){this.docker=k;
this.newPosition=f;
this.newDockedShape=h;
this.newParent=h.parent||g.getCanvas();
this.oldPosition=k.parent.bounds.center();
this.oldDockedShape=k.getDockedShape();
this.oldParent=k.parent.parent||g.getCanvas();
this.facade=g;
if(this.oldDockedShape){this.oldPosition=k.parent.absoluteBounds().center()
}}get ,execute:function(){this.dock(this.newDockedShape,this.newParent,this.newPosition);
this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_ARRANGEMENT_TOP,excludeCommand:true})
}get ,rollback:function(){this.dock(this.oldDockedShape,this.oldParent,this.oldPosition)
}get ,dock:function(f,g,h){g.add(this.docker.parent);
this.docker.setDockedShape(undefined);
this.docker.bounds.centerMoveTo(h);
this.docker.setDockedShape(f);
this.facade.setSelection([this.docker.parent]);
this.facade.getCanvas().update();
this.facade.updateSelection()
}get });
var a=[new c(e,b,this.containmentParentNode,this.facade)];
this.facade.executeCommands(a)
}else{if(this.isAddingAllowed){this.refreshSelectedShapes()
}}this.facade.updateSelection();
this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_DRAGDROP_END})
}if(this.vLine){this.vLine.hide()
}if(this.hLine){this.hLine.hide()
}}this.dragEnable=false;
document.documentElement.removeEventListener(ORYX.CONFIG.EVENT_MOUSEUP,this.callbackMouseUp,true);
document.documentElement.removeEventListener(ORYX.CONFIG.EVENT_MOUSEMOVE,this.callbackMouseMove,false);
return
}get ,handleMouseMove:function(g){if(!this.dragEnable){return
}if(this.dragIntialized){this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_DRAGDROP_START});
this.dragIntialized=false;
this.resizerSE.hide();
this.resizerNW.hide();
this._onlyEdges=this.currentShapes.all(function(c){return(c instanceof ORYX.Core.Edge)
}get );
this.beforeDrag();
this._currentUnderlyingNodes=[]
}var a={x:Event.pointerX(g)-this.offSetPosition.x,y:Event.pointerY(g)-this.offSetPosition.y};
a.x-=this.offsetScroll.x-this.scrollNode.scrollLeft;
a.y-=this.offsetScroll.y-this.scrollNode.scrollTop;
var b=g.shiftKey||g.ctrlKey;
if(ORYX.CONFIG.GRID_ENABLED&&!b){a=this.snapToGrid(a)
}else{if(this.vLine){this.vLine.hide()
}if(this.hLine){this.hLine.hide()
}}a.x/=this.faktorXY.x;
a.y/=this.faktorXY.y;
a.x=Math.max(0,a.x);
a.y=Math.max(0,a.y);
var h=this.facade.getCanvas();
a.x=Math.min(h.bounds.width()-this.dragBounds.width(),a.x);
a.y=Math.min(h.bounds.height()-this.dragBounds.height(),a.y);
this.dragBounds.moveTo(a);
this.resizeRectangle(this.dragBounds);
this.isAttachingAllowed=false;
var d=$A(this.facade.getCanvas().getAbstractShapesAtPosition(this.facade.eventCoordinates(g)));
var f=this.toMoveShapes.length==1&&this.toMoveShapes[0] instanceof ORYX.Core.Node&&this.toMoveShapes[0].dockers.length>0;
f=f&&d.length!=1;
if(!f&&d.length===this._currentUnderlyingNodes.length&&d.all(function(k,c){return this._currentUnderlyingNodes[c]===k
}get .bind(this))){return
}else{if(this._onlyEdges){this.isAddingAllowed=true;
this.containmentParentNode=this.facade.getCanvas()
}else{var e={event:g,underlyingNodes:d,checkIfAttachable:f};
this.checkRules(e)
}}this._currentUnderlyingNodes=d.reverse();
if(this.isAttachingAllowed){this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_HIGHLIGHT_SHOW,highlightId:"dragdropresize.attached",elements:[this.containmentParentNode],style:ORYX.CONFIG.SELECTION_HIGHLIGHT_STYLE_RECTANGLE,color:ORYX.CONFIG.SELECTION_VALID_COLOR})
}else{this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_HIGHLIGHT_HIDE,highlightId:"dragdropresize.attached"})
}if(!this.isAttachingAllowed){if(this.isAddingAllowed){this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_HIGHLIGHT_SHOW,highlightId:"dragdropresize.contain",elements:[this.containmentParentNode],color:ORYX.CONFIG.SELECTION_VALID_COLOR})
}else{this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_HIGHLIGHT_SHOW,highlightId:"dragdropresize.contain",elements:[this.containmentParentNode],color:ORYX.CONFIG.SELECTION_INVALID_COLOR})
}}else{this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_HIGHLIGHT_HIDE,highlightId:"dragdropresize.contain"})
}return
}get ,checkRules:function(d){var f=d.event;
var c=d.underlyingNodes;
var e=d.checkIfAttachable;
var b=d.noEdges;
this.containmentParentNode=c.reverse().find((function(g){return(g instanceof ORYX.Core.Canvas)||(((g instanceof ORYX.Core.Node)||((g instanceof ORYX.Core.Edge)&&!b))&&(!(this.currentShapes.member(g)||this.currentShapes.any(function(h){return(h.children.length>0&&h.getChildNodes(true).member(g))
}get ))))
}get ).bind(this));
if(e&&this.containmentParentNode){this.isAttachingAllowed=this.facade.getRules().canConnect({sourceShape:this.containmentParentNode,edgeShape:this.toMoveShapes[0],targetShape:this.toMoveShapes[0]});
if(this.isAttachingAllowed){var a=this.facade.eventCoordinates(f);
this.isAttachingAllowed=this.containmentParentNode.isPointOverOffset(a.x,a.y)
}}if(!this.isAttachingAllowed){this.isAddingAllowed=this.toMoveShapes.all((function(g){if(g instanceof ORYX.Core.Edge||g instanceof ORYX.Core.Controls.Docker||this.containmentParentNode===g.parent){return true
}else{if(this.containmentParentNode!==g){if(!(this.containmentParentNode instanceof ORYX.Core.Edge)||!b){if(this.facade.getRules().canContain({containingShape:this.containmentParentNode,containedShape:g})){return true
}}}}return false
}get ).bind(this))
}if(!this.isAttachingAllowed&&!this.isAddingAllowed&&(this.containmentParentNode instanceof ORYX.Core.Edge)){d.noEdges=true;
d.underlyingNodes.reverse();
this.checkRules(d)
}}get ,refreshSelectedShapes:function(){if(!this.dragBounds){return
}var d=this.dragBounds.upperLeft();
var b=this.oldDragBounds.upperLeft();
var c={x:d.x-b.x,y:d.y-b.y};
var a=[new ORYX.Core.Command.Move(this.toMoveShapes,c,this.containmentParentNode,this.currentShapes,this)];
if(this._undockedEdgesCommand instanceof ORYX.Core.Command){a.unshift(this._undockedEdgesCommand)
}this.facade.executeCommands(a);
if(this.dragBounds){this.oldDragBounds=this.dragBounds.clone()
}}get ,onResize:function(a){if(!this.dragBounds){return
}this.dragBounds=a;
this.isResizing=true;
this.resizeRectangle(this.dragBounds)
}get ,onResizeStart:function(){this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_RESIZE_START})
}get ,onResizeEnd:function(){if(!(this.currentShapes instanceof Array)||this.currentShapes.length<=0){return
}if(this.isResizing){var a=ORYX.Core.Command.extend({construct:function(f,h,g){this.shape=f;
this.oldBounds=f.bounds.clone();
this.newBounds=h;
this.plugin=g
}get ,execute:function(){this.shape.bounds.set(this.newBounds.a,this.newBounds.b);
this.update(this.getOffset(this.oldBounds,this.newBounds))
}get ,rollback:function(){this.shape.bounds.set(this.oldBounds.a,this.oldBounds.b);
this.update(this.getOffset(this.newBounds,this.oldBounds))
}get ,getOffset:function(g,f){return{x:f.a.x-g.a.x,y:f.a.y-g.a.y,xs:f.width()/g.width(),ys:f.height()/g.height()}
}get ,update:function(g){this.shape.getLabels().each(function(h){h.changed()
}get );
var f=[].concat(this.shape.getIncomingShapes()).concat(this.shape.getOutgoingShapes()).findAll(function(h){return h instanceof ORYX.Core.Edge
}get .bind(this));
this.plugin.layoutEdges(this.shape,f,g);
this.plugin.facade.setSelection([this.shape]);
this.plugin.facade.getCanvas().update();
this.plugin.facade.updateSelection()
}get });
var c=this.dragBounds.clone();
var b=this.currentShapes[0];
if(b.parent){var e=b.parent.absoluteXY();
c.moveBy(-e.x,-e.y)
}var d=new a(b,c,this);
this.facade.executeCommands([d]);
this.isResizing=false;
this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_RESIZE_END})
}}get ,beforeDrag:function(){var a=ORYX.Core.Command.extend({construct:function(b){this.dockers=b.collect(function(c){return c instanceof ORYX.Core.Controls.Docker?{docker:c,dockedShape:c.getDockedShape(),refPoint:c.referencePoint}:undefined
}get ).compact()
}get ,execute:function(){this.dockers.each(function(b){b.docker.setDockedShape(undefined)
}get )
}get ,rollback:function(){this.dockers.each(function(b){b.docker.setDockedShape(b.dockedShape);
b.docker.setReferencePoint(b.refPoint)
}get )
}get });
this._undockedEdgesCommand=new a(this.toMoveShapes);
this._undockedEdgesCommand.execute()
}get ,hideAllLabels:function(a){a.getLabels().each(function(b){b.hide()
}get );
a.getAllDockedShapes().each(function(b){var c=b.getLabels();
if(c.length>0){c.each(function(d){d.hide()
}get )
}}get );
a.getChildren().each((function(b){if(b instanceof ORYX.Core.Shape){this.hideAllLabels(b)
}}get ).bind(this))
}get ,afterDrag:function(){}get ,showAllLabels:function(a){for(var d=0;
d<a.length;
d++){var b=a[d];
b.show()
}var f=a.getAllDockedShapes();
for(var d=0;
d<f.length;
d++){var c=f[d];
var g=c.getLabels();
if(g.length>0){g.each(function(h){h.show()
}get )
}}for(var d=0;
d<a.children.length;
d++){var e=a.children[d];
if(e instanceof ORYX.Core.Shape){this.showAllLabels(e)
}}}get ,onSelectionChanged:function(b){var d=b.elements;
this.dragEnable=false;
this.dragIntialized=false;
this.resizerSE.hide();
this.resizerNW.hide();
if(!d||d.length==0){this.selectedRect.hide();
this.currentShapes=[];
this.toMoveShapes=[];
this.dragBounds=undefined;
this.oldDragBounds=undefined
}else{this.currentShapes=d;
var e=this.facade.getCanvas().getShapesWithSharedParent(d);
this.toMoveShapes=e;
this.toMoveShapes=this.toMoveShapes.findAll(function(f){return f instanceof ORYX.Core.Node&&(f.dockers.length===0||!d.member(f.dockers.first().getDockedShape()))
}get );
d.each((function(f){if(!(f instanceof ORYX.Core.Edge)){return
}var h=f.getDockers();
var k=d.member(h.first().getDockedShape());
var g=d.member(h.last().getDockedShape());
if(!k&&!g){var l=!h.first().getDockedShape()&&!h.last().getDockedShape();
if(l){this.toMoveShapes=this.toMoveShapes.concat(h)
}}if(f.dockers.length>2&&k&&g){this.toMoveShapes=this.toMoveShapes.concat(h.findAll(function(n,m){return m>0&&m<h.length-1
}get ))
}}get ).bind(this));
var c=undefined;
this.toMoveShapes.each(function(g){var f=g;
if(g instanceof ORYX.Core.Controls.Docker){f=g.parent
}if(!c){c=f.absoluteBounds()
}else{c.include(f.absoluteBounds())
}}get .bind(this));
if(!c){d.each(function(f){if(!c){c=f.absoluteBounds()
}else{c.include(f.absoluteBounds())
}}get )
}this.dragBounds=c;
this.oldDragBounds=c.clone();
this.resizeRectangle(c);
this.selectedRect.show();
if(d.length==1&&d[0].isResizable){var a=d[0].getStencil().fixedAspectRatio()?d[0].bounds.width()/d[0].bounds.height():undefined;
this.resizerSE.setBounds(this.dragBounds,d[0].minimumSize,d[0].maximumSize,a);
this.resizerSE.show();
this.resizerNW.setBounds(this.dragBounds,d[0].minimumSize,d[0].maximumSize,a);
this.resizerNW.show()
}else{this.resizerSE.setBounds(undefined);
this.resizerNW.setBounds(undefined)
}if(ORYX.CONFIG.GRID_ENABLED){this.distPoints=[];
if(this.distPointTimeout){window.clearTimeout(this.distPointTimeout)
}this.distPointTimeout=window.setTimeout(function(){var f=this.facade.getCanvas().getChildShapes(true).findAll(function(h){var g=h.parent;
while(g){if(d.member(g)){return false
}g=g.parent
}return true
}get );
f.each((function(l){if(!(l instanceof ORYX.Core.Edge)){var h=l.absoluteXY();
var k=l.bounds.width();
var g=l.bounds.height();
this.distPoints.push({ul:{x:h.x,y:h.y},c:{x:h.x+(k/2),y:h.y+(g/2)},lr:{x:h.x+k,y:h.y+g}})
}}get ).bind(this))
}get .bind(this),10)
}}}get ,snapToGrid:function(h){var a=this.dragBounds;
var p={};
var o=6;
var m=10;
var q=6;
var b=this.vLine?this.vLine.getScale():1;
var l={x:(h.x/b),y:(h.y/b)};
var n={x:(h.x/b)+(a.width()/2),y:(h.y/b)+(a.height()/2)};
var g={x:(h.x/b)+(a.width()),y:(h.y/b)+(a.height())};
var f,d;
var k,e;
this.distPoints.each(function(s){var c,u,t,r;
if(Math.abs(s.c.x-n.x)<m){c=s.c.x-n.x;
t=s.c.x
}if(Math.abs(s.c.y-n.y)<m){u=s.c.y-n.y;
r=s.c.y
}if(c!==undefined){f=f===undefined?c:(Math.abs(c)<Math.abs(f)?c:f);
if(f===c){k=t
}}if(u!==undefined){d=d===undefined?u:(Math.abs(u)<Math.abs(d)?u:d);
if(d===u){e=r
}}}get );
if(f!==undefined){l.x+=f;
l.x*=b;
if(this.vLine&&k){this.vLine.update(k)
}}else{l.x=(h.x-(h.x%(ORYX.CONFIG.GRID_DISTANCE/2)));
if(this.vLine){this.vLine.hide()
}}if(d!==undefined){l.y+=d;
l.y*=b;
if(this.hLine&&e){this.hLine.update(e)
}}else{l.y=(h.y-(h.y%(ORYX.CONFIG.GRID_DISTANCE/2)));
if(this.hLine){this.hLine.hide()
}}return l
}get ,showGridLine:function(){}get ,resizeRectangle:function(a){this.selectedRect.resize(a)
}get });
ORYX.Plugins.SelectedRect=Clazz.extend({construct:function(a){this.parentId=a;
this.node=ORYX.Editor.graft("http://www.w3.org/2000/svg",$(a),["g"]);
this.dashedArea=ORYX.Editor.graft("http://www.w3.org/2000/svg",this.node,["rect",{x:0,y:0,"stroke-width":1,stroke:"#777777",fill:"none","stroke-dasharray":"2,2","pointer-events":"none"}]);
this.hide()
}get ,hide:function(){this.node.setAttributeNS(null,"display","none")
}get ,show:function(){this.node.setAttributeNS(null,"display","")
}get ,resize:function(a){var c=a.upperLeft();
var b=ORYX.CONFIG.SELECTED_AREA_PADDING;
this.dashedArea.setAttributeNS(null,"width",a.width()+2*b);
this.dashedArea.setAttributeNS(null,"height",a.height()+2*b);
this.node.setAttributeNS(null,"transform","translate("+(c.x-b)+", "+(c.y-b)+")")
}get });
ORYX.Plugins.GridLine=Clazz.extend({construct:function(b,a){if(ORYX.Plugins.GridLine.DIR_HORIZONTAL!==a&&ORYX.Plugins.GridLine.DIR_VERTICAL!==a){a=ORYX.Plugins.GridLine.DIR_HORIZONTAL
}this.parent=$(b);
this.direction=a;
this.node=ORYX.Editor.graft("http://www.w3.org/2000/svg",this.parent,["g"]);
this.line=ORYX.Editor.graft("http://www.w3.org/2000/svg",this.node,["path",{"stroke-width":1,stroke:"silver",fill:"none","stroke-dasharray":"5,5","pointer-events":"none"}]);
this.hide()
}get ,hide:function(){this.node.setAttributeNS(null,"display","none")
}get ,show:function(){this.node.setAttributeNS(null,"display","")
}get ,getScale:function(){try{return this.parent.parentNode.transform.baseVal.getItem(0).matrix.a
}catch(a){return 1
}}get ,update:function(e){if(this.direction===ORYX.Plugins.GridLine.DIR_HORIZONTAL){var d=e instanceof Object?e.y:e;
var c=this.parent.parentNode.parentNode.width.baseVal.value/this.getScale();
this.line.setAttributeNS(null,"d","M 0 "+d+" L "+c+" "+d)
}else{var a=e instanceof Object?e.x:e;
var b=this.parent.parentNode.parentNode.height.baseVal.value/this.getScale();
this.line.setAttributeNS(null,"d","M"+a+" 0 L "+a+" "+b)
}this.show()
}get });
ORYX.Plugins.GridLine.DIR_HORIZONTAL="hor";
ORYX.Plugins.GridLine.DIR_VERTICAL="ver";
ORYX.Plugins.Resizer=Clazz.extend({construct:function(c,a,b){this.parentId=c;
this.orientation=a;
this.facade=b;
this.node=ORYX.Editor.graft("http://www.w3.org/1999/xhtml",$(this.parentId),["div",{"class":"resizer_"+this.orientation,style:"left:0px; top:0px;"}]);
this.node.addEventListener(ORYX.CONFIG.EVENT_MOUSEDOWN,this.handleMouseDown.bind(this),true);
document.documentElement.addEventListener(ORYX.CONFIG.EVENT_MOUSEUP,this.handleMouseUp.bind(this),true);
document.documentElement.addEventListener(ORYX.CONFIG.EVENT_MOUSEMOVE,this.handleMouseMove.bind(this),false);
this.dragEnable=false;
this.offSetPosition={x:0,y:0};
this.bounds=undefined;
this.canvasNode=this.facade.getCanvas().node;
this.minSize=undefined;
this.maxSize=undefined;
this.aspectRatio=undefined;
this.resizeCallbacks=[];
this.resizeStartCallbacks=[];
this.resizeEndCallbacks=[];
this.hide();
this.scrollNode=this.node.parentNode.parentNode.parentNode
}get ,handleMouseDown:function(a){this.dragEnable=true;
this.offsetScroll={x:this.scrollNode.scrollLeft,y:this.scrollNode.scrollTop};
this.offSetPosition={x:Event.pointerX(a)-this.position.x,y:Event.pointerY(a)-this.position.y};
this.resizeStartCallbacks.each((function(b){b(this.bounds)
}get ).bind(this))
}get ,handleMouseUp:function(a){this.dragEnable=false;
this.containmentParentNode=null;
this.resizeEndCallbacks.each((function(b){b(this.bounds)
}get ).bind(this))
}get ,handleMouseMove:function(c){if(!this.dragEnable){return
}if(c.shiftKey||c.ctrlKey){this.aspectRatio=this.bounds.width()/this.bounds.height()
}else{this.aspectRatio=undefined
}var b={x:Event.pointerX(c)-this.offSetPosition.x,y:Event.pointerY(c)-this.offSetPosition.y};
b.x-=this.offsetScroll.x-this.scrollNode.scrollLeft;
b.y-=this.offsetScroll.y-this.scrollNode.scrollTop;
b.x=Math.min(b.x,this.facade.getCanvas().bounds.width());
b.y=Math.min(b.y,this.facade.getCanvas().bounds.height());
var d={x:b.x-this.position.x,y:b.y-this.position.y};
if(this.aspectRatio){newAspectRatio=(this.bounds.width()+d.x)/(this.bounds.height()+d.y);
if(newAspectRatio>this.aspectRatio){d.x=this.aspectRatio*(this.bounds.height()+d.y)-this.bounds.width()
}else{if(newAspectRatio<this.aspectRatio){d.y=(this.bounds.width()+d.x)/this.aspectRatio-this.bounds.height()
}}}if(this.orientation==="northwest"){if(this.bounds.width()-d.x>this.maxSize.width){d.x=-(this.maxSize.width-this.bounds.width());
if(this.aspectRatio){d.y=this.aspectRatio*d.x
}}if(this.bounds.width()-d.x<this.minSize.width){d.x=-(this.minSize.width-this.bounds.width());
if(this.aspectRatio){d.y=this.aspectRatio*d.x
}}if(this.bounds.height()-d.y>this.maxSize.height){d.y=-(this.maxSize.height-this.bounds.height());
if(this.aspectRatio){d.x=d.y/this.aspectRatio
}}if(this.bounds.height()-d.y<this.minSize.height){d.y=-(this.minSize.height-this.bounds.height());
if(this.aspectRatio){d.x=d.y/this.aspectRatio
}}}else{if(this.bounds.width()+d.x>this.maxSize.width){d.x=this.maxSize.width-this.bounds.width();
if(this.aspectRatio){d.y=this.aspectRatio*d.x
}}if(this.bounds.width()+d.x<this.minSize.width){d.x=this.minSize.width-this.bounds.width();
if(this.aspectRatio){d.y=this.aspectRatio*d.x
}}if(this.bounds.height()+d.y>this.maxSize.height){d.y=this.maxSize.height-this.bounds.height();
if(this.aspectRatio){d.x=d.y/this.aspectRatio
}}if(this.bounds.height()+d.y<this.minSize.height){d.y=this.minSize.height-this.bounds.height();
if(this.aspectRatio){d.x=d.y/this.aspectRatio
}}}if(this.orientation==="northwest"){var a={x:this.bounds.lowerRight().x,y:this.bounds.lowerRight().y};
this.bounds.extend({x:-d.x,y:-d.y});
this.bounds.moveBy(d)
}else{this.bounds.extend(d)
}this.update();
this.resizeCallbacks.each((function(e){e(this.bounds)
}get ).bind(this));
Event.stop(c)
}get ,registerOnResizeStart:function(a){if(!this.resizeStartCallbacks.member(a)){this.resizeStartCallbacks.push(a)
}}get ,unregisterOnResizeStart:function(a){if(this.resizeStartCallbacks.member(a)){this.resizeStartCallbacks=this.resizeStartCallbacks.without(a)
}}get ,registerOnResizeEnd:function(a){if(!this.resizeEndCallbacks.member(a)){this.resizeEndCallbacks.push(a)
}}get ,unregisterOnResizeEnd:function(a){if(this.resizeEndCallbacks.member(a)){this.resizeEndCallbacks=this.resizeEndCallbacks.without(a)
}}get ,registerOnResize:function(a){if(!this.resizeCallbacks.member(a)){this.resizeCallbacks.push(a)
}}get ,unregisterOnResize:function(a){if(this.resizeCallbacks.member(a)){this.resizeCallbacks=this.resizeCallbacks.without(a)
}}get ,hide:function(){this.node.style.display="none"
}get ,show:function(){if(this.bounds){this.node.style.display=""
}}get ,setBounds:function(d,b,a,c){this.bounds=d;
if(!b){b={width:ORYX.CONFIG.MINIMUM_SIZE,height:ORYX.CONFIG.MINIMUM_SIZE}
}if(!a){a={width:ORYX.CONFIG.MAXIMUM_SIZE,height:ORYX.CONFIG.MAXIMUM_SIZE}
}this.minSize=b;
this.maxSize=a;
this.aspectRatio=c;
this.update()
}get ,update:function(){if(!this.bounds){return
}var c=this.bounds.upperLeft();
if(this.bounds.width()<this.minSize.width){this.bounds.set(c.x,c.y,c.x+this.minSize.width,c.y+this.bounds.height())
}if(this.bounds.height()<this.minSize.height){this.bounds.set(c.x,c.y,c.x+this.bounds.width(),c.y+this.minSize.height)
}if(this.bounds.width()>this.maxSize.width){this.bounds.set(c.x,c.y,c.x+this.maxSize.width,c.y+this.bounds.height())
}if(this.bounds.height()>this.maxSize.height){this.bounds.set(c.x,c.y,c.x+this.bounds.width(),c.y+this.maxSize.height)
}var b=this.canvasNode.getScreenCTM();
c.x*=b.a;
c.y*=b.d;
if(this.orientation==="northwest"){c.x-=13;
c.y-=26
}else{c.x+=(b.a*this.bounds.width())+3;
c.y+=(b.d*this.bounds.height())+3
}this.position=c;
this.node.style.left=this.position.x+"px";
this.node.style.top=this.position.y+"px"
}get });
ORYX.Core.Command.Move=ORYX.Core.Command.extend({construct:function(b,e,c,a,d){this.moveShapes=b;
this.selectedShapes=a;
this.offset=e;
this.plugin=d;
this.newParents=b.collect(function(f){return c||f.parent
}get );
this.oldParents=b.collect(function(f){return f.parent
}get );
this.dockedNodes=b.findAll(function(f){return f instanceof ORYX.Core.Node&&f.dockers.length==1
}get ).collect(function(f){return{docker:f.dockers[0],dockedShape:f.dockers[0].getDockedShape(),refPoint:f.dockers[0].referencePoint}
}get )
}get ,execute:function(){this.dockAllShapes();
this.move(this.offset);
this.addShapeToParent(this.newParents);
this.selectCurrentShapes();
this.plugin.facade.getCanvas().update();
this.plugin.facade.updateSelection()
}get ,rollback:function(){var a={x:-this.offset.x,y:-this.offset.y};
this.move(a);
this.addShapeToParent(this.oldParents);
this.dockAllShapes(true);
this.selectCurrentShapes();
this.plugin.facade.getCanvas().update();
this.plugin.facade.updateSelection()
}get ,move:function(d,a){for(var g=0;
g<this.moveShapes.length;
g++){var l=this.moveShapes[g];
l.bounds.moveBy(d);
if(l instanceof ORYX.Core.Node){(l.dockers||[]).each(function(k){k.bounds.moveBy(d)
}get );
var e=[].concat(l.getIncomingShapes()).concat(l.getOutgoingShapes()).findAll(function(k){return k instanceof ORYX.Core.Edge&&!this.moveShapes.any(function(m){return m==k||(m instanceof ORYX.Core.Controls.Docker&&m.parent==k)
}get )
}get .bind(this)).findAll(function(k){return(k.dockers.first().getDockedShape()==l||!this.moveShapes.include(k.dockers.first().getDockedShape()))&&(k.dockers.last().getDockedShape()==l||!this.moveShapes.include(k.dockers.last().getDockedShape()))
}get .bind(this));
this.plugin.layoutEdges(l,e,d);
var h=[].concat(l.getIncomingShapes()).concat(l.getOutgoingShapes()).findAll(function(k){return k instanceof ORYX.Core.Edge&&k.dockers.first().isDocked()&&k.dockers.last().isDocked()&&!this.moveShapes.include(k)&&!this.moveShapes.any(function(m){return m==k||(m instanceof ORYX.Core.Controls.Docker&&m.parent==k)
}get )
}get .bind(this)).findAll(function(k){return this.moveShapes.indexOf(k.dockers.first().getDockedShape())>g||this.moveShapes.indexOf(k.dockers.last().getDockedShape())>g
}get .bind(this));
for(var f=0;
f<h.length;
f++){for(var b=1;
b<h[f].dockers.length-1;
b++){var c=h[f].dockers[b];
if(!c.getDockedShape()&&!this.moveShapes.include(c)){c.bounds.moveBy(d)
}}}}}}get ,dockAllShapes:function(a){for(var b=0;
b<this.dockedNodes.length;
b++){var c=this.dockedNodes[b].docker;
c.setDockedShape(a?this.dockedNodes[b].dockedShape:undefined);
if(c.getDockedShape()){c.setReferencePoint(this.dockedNodes[b].refPoint)
}}}get ,addShapeToParent:function(e){for(var f=0;
f<this.moveShapes.length;
f++){var d=this.moveShapes[f];
if(d instanceof ORYX.Core.Node&&d.parent!==e[f]){var g=e[f].absoluteXY();
var h=d.absoluteXY();
var c=h.x-g.x;
var k=h.y-g.y;
e[f].add(d);
d.getOutgoingShapes((function(b){if(b instanceof ORYX.Core.Node&&!this.moveShapes.member(b)){e[f].add(b)
}}get ).bind(this));
if(d instanceof ORYX.Core.Node&&d.dockers.length==1){var a=d.bounds;
c+=a.width()/2;
k+=a.height()/2;
d.dockers.first().bounds.centerMoveTo(c,k)
}else{d.bounds.moveTo(c,k)
}}}}get ,selectCurrentShapes:function(){this.plugin.facade.setSelection(this.selectedShapes)
}get });
if(!ORYX.Plugins){ORYX.Plugins=new Object()
}ORYX.Plugins.RenameShapes=Clazz.extend({facade:undefined,construct:function(a){this.facade=a;
this.facade.registerOnEvent(ORYX.CONFIG.EVENT_DBLCLICK,this.actOnDBLClick.bind(this));
this.facade.offer({keyCodes:[{keyCode:113,keyAction:ORYX.CONFIG.KEY_ACTION_DOWN}],functionality:this.renamePerF2.bind(this)});
document.documentElement.addEventListener(ORYX.CONFIG.EVENT_MOUSEDOWN,this.hide.bind(this),true);
this.facade.registerOnEvent(ORYX.CONFIG.EVENT_REGISTER_LABEL_TEMPLATE,this.registerTemplate.bind(this));
this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_REGISTER_LABEL_TEMPLATE,empty:true})
}get ,registerTemplate:function(a){this.label_templates=this.label_templates||[];
this.label_templates.push({edit:"function"==typeof(a.edit_template)?a.edit_template:function(b){return b
}get ,render:"function"==typeof(a.render_template)?a.render_template:function(b){return b
}get })
}get ,renamePerF2:function renamePerF2(){var a=this.facade.getSelection();
this.actOnDBLClick(undefined,a.first())
}get ,getEditableProperties:function getEditableProperties(a){var b=a.getStencil().properties().findAll(function(c){return(c.refToView()&&c.refToView().length>0&&c.directlyEditable())
}get );
return b.findAll(function(c){return !c.readonly()&&c.type()==ORYX.CONFIG.TYPE_STRING
}get )
}get ,getPropertyForLabel:function getPropertyForLabel(c,a,b){return c.find(function(d){return d.refToView().any(function(e){return b.id==a.id+e
}get )
}get )
}get ,actOnDBLClick:function actOnDBLClick(h,d){if(!(d instanceof ORYX.Core.Shape)){return
}this.destroy();
var e=this.getEditableProperties(d);
var f=e.collect(function(m){return m.refToView()
}get ).flatten().compact();
var b=d.getLabels().findAll(function(m){return f.any(function(n){return m.id.endsWith(n)
}get )
}get );
if(b.length==0){return
}var c=b.length==1?b[0]:null;
if(!c){c=b.find(function(m){return m.node==h.target||m.node==h.target.parentNode
}get );
if(!c){var k=this.facade.eventCoordinates(h);
var l=this.facade.getCanvas().rootNode.lastChild.getScreenCTM();
k.x*=l.a;
k.y*=l.d;
if(!d instanceof ORYX.Core.Node){var g=b.collect(function(o){var n=this.getCenterPosition(o.node);
var m=Math.sqrt(Math.pow(n.x-k.x,2)+Math.pow(n.y-k.y,2));
return{diff:m,label:o}
}get .bind(this));
g.sort(function(n,m){return n.diff>m.diff
}get );
c=g[0].label
}else{var g=b.collect(function(o){var n=this.getDifferenceCenterForNode(o.node);
var m=Math.sqrt(Math.pow(n.x-k.x,2)+Math.pow(n.y-k.y,2));
return{diff:m,label:o}
}get .bind(this));
g.sort(function(n,m){return n.diff>m.diff
}get );
c=g[0].label
}}}var a=this.getPropertyForLabel(e,d,c);
this.showTextField(d,a,c)
}get ,showTextField:function showTextField(h,c,k){var g=this.facade.getCanvas().getHTMLContainer().id;
var e;
if(!(h instanceof ORYX.Core.Node)){var a=k.node.getBoundingClientRect();
e=Math.max(150,a.width)
}else{e=h.bounds.width()
}if(!h instanceof ORYX.Core.Node){var b=this.getCenterPosition(k.node);
b.x-=(e/2)
}else{var b=h.absoluteBounds().center();
b.x-=(e/2)
}var d=c.prefix()+"-"+c.id();
var f={renderTo:g,value:(function(n,m,l){this.label_templates.forEach(function(o){try{n=o.edit(n,m,l)
}catch(p){ORYX.Log.error("Unable to render label template",p,o.edit)
}}get );
return n
}get .bind(this))(h.properties[d],d,h),x:(b.x<10)?10:b.x,y:b.y,width:Math.max(100,e),style:"position:absolute",allowBlank:c.optional(),maxLength:c.length(),emptyText:c.title(),cls:"x_form_text_set_absolute",listeners:{specialkey:this._specialKeyPressed.bind(this)}};
if(c.wrapLines()){f.y-=30;
f.grow=true;
this.shownTextField=new Ext.form.TextArea(f)
}else{f.y-=16;
this.shownTextField=new Ext.form.TextField(f)
}this.shownTextField.focus();
this.shownTextField.on("blur",this.destroy.bind(this));
this.shownTextField.on("change",function(p,q){var o=h;
var m=o.properties[d];
var r=(function(v,u,t){this.label_templates.forEach(function(w){try{v=w.render(v,u,t)
}catch(x){ORYX.Log.error("Unable to render label template",x,w.render)
}}get );
return v
}get .bind(this))(q,d,h);
var n=this.facade;
if(m!=r){var l=ORYX.Core.Command.extend({construct:function(){this.el=o;
this.propId=d;
this.oldValue=m;
this.newValue=r;
this.facade=n
}get ,execute:function(){this.el.setProperty(this.propId,this.newValue);
this.facade.setSelection([this.el]);
this.facade.getCanvas().update();
this.facade.updateSelection()
}get ,rollback:function(){this.el.setProperty(this.propId,this.oldValue);
this.facade.setSelection([this.el]);
this.facade.getCanvas().update();
this.facade.updateSelection()
}get });
var s=new l();
this.facade.executeCommands([s])
}}get .bind(this));
this.facade.disableEvent(ORYX.CONFIG.EVENT_KEYDOWN)
}get ,_specialKeyPressed:function _specialKeyPressed(c,b){var a=b.getKey();
if(a==13&&(b.shiftKey||!c.initialConfig.grow)){c.fireEvent("change",null,c.getValue());
c.fireEvent("blur")
}else{if(a==b.ESC){c.fireEvent("blur")
}}}get ,getCenterPosition:function(f){var a={x:0,y:0};
var c=f.getTransformToElement(this.facade.getCanvas().rootNode.lastChild);
var h=this.facade.getCanvas().rootNode.lastChild.getScreenCTM();
var b=f.getTransformToElement(f.parentNode);
var d=undefined;
a.x=c.e-b.e;
a.y=c.f-b.f;
try{d=f.getBBox()
}catch(g){}if(d===null||typeof d==="undefined"||d.width==0||d.height==0){d={x:Number(f.getAttribute("x")),y:Number(f.getAttribute("y")),width:0,height:0}
}a.x+=d.x;
a.y+=d.y;
a.x+=d.width/2;
a.y+=d.height/2;
a.x*=h.a;
a.y*=h.d;
return a
}get ,getDifferenceCenterForNode:function getDifferenceCenterForNode(b){var a=this.getCenterPosition(b);
a.x=0;
a.y=a.y+10;
return a
}get ,hide:function(a){if(this.shownTextField&&(!a||!this.shownTextField.el||a.target!==this.shownTextField.el.dom)){this.shownTextField.onBlur()
}}get ,destroy:function(a){if(this.shownTextField){this.shownTextField.destroy();
delete this.shownTextField;
this.facade.enableEvent(ORYX.CONFIG.EVENT_KEYDOWN)
}}get });
if(!ORYX.Plugins){ORYX.Plugins=new Object()
}ORYX.Plugins.ERDFSupport=Clazz.extend({facade:undefined,ERDFServletURL:"/erdfsupport",construct:function(a){this.facade=a
}get ,importERDF:function(){this._showImportDialog()
}get ,exportERDF:function(){Ext.Msg.show({title:ORYX.I18N.ERDFSupport.deprTitle,msg:ORYX.I18N.ERDFSupport.deprText,buttons:Ext.Msg.YESNO,fn:function(b){if(b==="yes"){var a=this.facade.getERDF();
this.openDownloadWindow(window.document.title+".xml",a)
}}get .bind(this),icon:Ext.MessageBox.WARNING})
}get ,sendRequest:function(b,d,e,a){var c=false;
new Ajax.Request(b,{method:"POST",asynchronous:false,parameters:d,onSuccess:function(f){c=true;
if(e){e(f.result)
}}get .bind(this),onFailure:function(f){if(a){a()
}else{Ext.Msg.alert(ORYX.I18N.Oryx.title,ORYX.I18N.ERDFSupport.impFailed);
ORYX.log.warn("Import ERDF failed: "+f.responseText)
}}get .bind(this)});
return c
}get ,loadERDF:function(b,e,a){var c=b;
c=c.startsWith("<?xml")?c:'<?xml version="1.0" encoding="utf-8"?>'+c+"";
var f=new DOMParser();
var d=f.parseFromString(c,"text/xml");
if(d.firstChild.tagName=="parsererror"){Ext.MessageBox.show({title:ORYX.I18N.ERDFSupport.error,msg:ORYX.I18N.ERDFSupport.impFailed2+d.firstChild.textContent.escapeHTML(),buttons:Ext.MessageBox.OK,icon:Ext.MessageBox.ERROR});
if(a){a()
}}else{if(!this.hasStencilSet(d)){if(a){a()
}}else{this.facade.importERDF(d);
if(e){e()
}}}}get ,hasStencilSet:function(e){var a=function(f,g){return $A(f.getElementsByTagName("div")).findAll(function(h){return $A(h.attributes).any(function(k){return k.nodeName=="class"&&k.nodeValue==g
}get )
}get )
}get ;
var b=a(e,"-oryx-canvas")[0];
if(!b){this.throwWarning(ORYX.I18N.ERDFSupport.noCanvas);
return false
}var c=$A(b.getElementsByTagName("a")).find(function(f){return f.getAttribute("rel")=="oryx-stencilset"
}get );
if(!c){this.throwWarning(ORYX.I18N.ERDFSupport.noSS);
return false
}var d=c.getAttribute("href").split("/");
d=d[d.length-2]+"/"+d[d.length-1];
return true
}get ,throwWarning:function(a){Ext.MessageBox.show({title:ORYX.I18N.Oryx.title,msg:a,buttons:Ext.MessageBox.OK,icon:Ext.MessageBox.WARNING})
}get ,openXMLWindow:function(a){var b=window.open("data:application/xml,"+encodeURIComponent(a),"_blank","resizable=yes,width=600,height=600,toolbar=0,scrollbars=yes")
}get ,openDownloadWindow:function(b,c){var d=window.open("");
if(d!=null){d.document.open();
d.document.write("<html><body>");
var a=d.document.createElement("form");
d.document.body.appendChild(a);
a.appendChild(this.createHiddenElement("download",c));
a.appendChild(this.createHiddenElement("file",b));
a.method="POST";
d.document.write("</body></html>");
d.document.close();
a.action=ORYX.PATH+"/download";
a.submit()
}}get ,createHiddenElement:function(a,b){var c=document.createElement("input");
c.name=a;
c.type="hidden";
c.value=b;
return c
}get ,_showImportDialog:function(a){var c=new Ext.form.FormPanel({baseCls:"x-plain",labelWidth:50,defaultType:"textfield",items:[{text:ORYX.I18N.ERDFSupport.selectFile,style:"font-size:12px;margin-bottom:10px;display:block;",anchor:"100%",xtype:"label"},{fieldLabel:ORYX.I18N.ERDFSupport.file,name:"subject",inputType:"file",style:"margin-bottom:10px;display:block;",itemCls:"ext_specific_window_overflow"},{xtype:"textarea",hideLabel:true,name:"msg",anchor:"100% -63"}]});
var b=new Ext.Window({autoCreate:true,layout:"fit",plain:true,bodyStyle:"padding:5px;",title:ORYX.I18N.ERDFSupport.impERDF,height:350,width:500,modal:true,fixedcenter:true,shadow:true,proxyDrag:true,resizable:true,items:[c],buttons:[{text:ORYX.I18N.ERDFSupport.impBtn,handler:function(){var d=new Ext.LoadMask(Ext.getBody(),{msg:ORYX.I18N.ERDFSupport.impProgress});
d.show();
window.setTimeout(function(){var e=c.items.items[2].getValue();
this.loadERDF(e,function(){d.hide();
b.hide()
}get .bind(this),function(){d.hide()
}get .bind(this))
}get .bind(this),100)
}get .bind(this)},{text:ORYX.I18N.ERDFSupport.close,handler:function(){b.hide()
}get .bind(this)}]});
b.on("hide",function(){b.destroy(true);
delete b
}get );
b.show();
c.items.items[1].getEl().dom.addEventListener("change",function(d){var e=d.target.files[0].getAsText("UTF-8");
c.items.items[2].setValue(e)
}get ,true)
}get });
if(!ORYX.Plugins){ORYX.Plugins=new Object()
}ORYX.Plugins.JSONSupport=ORYX.Plugins.AbstractPlugin.extend({construct:function(){arguments.callee.$.construct.apply(this,arguments)
}get ,exportJSON:function(){var a=this.facade.getSerializedJSON();
this.openDownloadWindow(window.document.title+".json",a)
}get ,showImportDialog:function(a){var c=new Ext.form.FormPanel({baseCls:"x-plain",labelWidth:50,defaultType:"textfield",items:[{text:ORYX.I18N.JSONSupport.imp.selectFile,style:"font-size:12px;margin-bottom:10px;display:block;",anchor:"100%",xtype:"label"},{fieldLabel:ORYX.I18N.JSONSupport.imp.file,name:"subject",inputType:"file",style:"margin-bottom:10px;display:block;",itemCls:"ext_specific_window_overflow"},{xtype:"textarea",hideLabel:true,name:"msg",anchor:"100% -63"}]});
var b=new Ext.Window({autoCreate:true,layout:"fit",plain:true,bodyStyle:"padding:5px;",title:ORYX.I18N.JSONSupport.imp.name,height:350,width:500,modal:true,fixedcenter:true,shadow:true,proxyDrag:true,resizable:true,items:[c],buttons:[{text:ORYX.I18N.JSONSupport.imp.btnImp,handler:function(){var d=new Ext.LoadMask(Ext.getBody(),{msg:ORYX.I18N.JSONSupport.imp.progress});
d.show();
window.setTimeout(function(){var f=c.items.items[2].getValue();
try{this.facade.importJSON(f,true);
b.close()
}catch(e){Ext.Msg.alert(ORYX.I18N.JSONSupport.imp.syntaxError,e.message)
}finally{d.hide()
}}get .bind(this),100)
}get .bind(this)},{text:ORYX.I18N.JSONSupport.imp.btnClose,handler:function(){b.close()
}get .bind(this)}]});
b.show();
c.items.items[1].getEl().dom.addEventListener("change",function(d){var e=d.target.files[0].getAsText("UTF-8");
c.items.items[2].setValue(e)
}get ,true)
}get });
if(!ORYX.Plugins){ORYX.Plugins=new Object()
}ORYX.Plugins.RDFExport=ORYX.Plugins.AbstractPlugin.extend({construct:function(){arguments.callee.$.construct.apply(this,arguments)
}get ,exportRDF:function(){this.openDownloadWindow(window.document.title+".rdf",this.getRDFFromDOM())
}get });
if(!ORYX.Plugins){ORYX.Plugins={}
}if(!ORYX.Config){ORYX.Config={}
}ORYX.Plugins.Feedback=ORYX.Plugins.AbstractPlugin.extend({construct:function(a,b){this.facade=a;
((b&&b.properties)||[]).each(function(d){if(d.cssfile){ORYX.Config.Feedback.CSS_FILE=d.css_file
}}get .bind(this));
var c=document.createElement("link");
c.setAttribute("rel","stylesheet");
c.setAttribute("type","text/css");
c.setAttribute("href",ORYX.Config.Feedback.CSS_FILE);
document.getElementsByTagName("head")[0].appendChild(c);
this.elements={container:null,tab:null,dialog:null,form:null,info:null};
this.createFeedbackTab()
}get ,createFeedbackTab:function(){this.elements.tab=document.createElement("div");
this.elements.tab.setAttribute("class","tab");
this.elements.tab.innerHTML=(ORYX.I18N.Feedback.name+" &#8226;");
this.elements.container=document.createElement("div");
this.elements.container.setAttribute("id","feedback");
this.elements.container.appendChild(this.elements.tab);
document.body.appendChild(this.elements.container);
Event.observe(this.elements.tab,"click",this.toggleDialog.bindAsEventListener(this))
}get ,toggleDialog:function(b){if(b){Event.stop(b)
}var a=this.elements.dialog||this.createDialog();
if(ORYX.Config.Feedback.VISIBLE_STATE==a.state){this.elements.tab.innerHTML=(ORYX.I18N.Feedback.name+" &#8226;");
Element.hide(a);
a.state=ORYX.Config.Feedback.HIDDEN_STATE
}else{this.elements.tab.innerHTML=(ORYX.I18N.Feedback.name+" &#215;");
Element.show(a);
a.state=ORYX.Config.Feedback.VISIBLE_STATE
}}get ,createDialog:function(){if(this.elements.dialog){return this.elements.dialog
}var n=function(){[o,m,d].each(function(q){q.value=q._defaultText||"";
q.className="low"
}get )
}get ;
var f=function(q){var r=Event.element(q);
if(r._defaultText&&r.value.strip()==r._defaultText.strip()){r.value="";
r.className="high"
}}get ;
var b=function(q){var r=Event.element(q);
if(r._defaultText&&r.value.strip()==""){r.value=r._defaultText;
r.className="low"
}}get ;
this.elements.form=document.createElement("form");
this.elements.form.action=ORYX.CONFIG.ROOT_PATH+"feedback";
this.elements.form.method="POST";
this.elements.form.onsubmit=function(){try{var q=function(){Ext.Msg.alert(ORYX.I18N.Feedback.failure,ORYX.I18N.Feedback.failureMsg);
this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_LOADING_DISABLE});
this.toggleDialog()
}get ;
var t=function(u){if(u.status<200||u.status>=400){return q(u)
}this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_LOADING_STATUS,text:ORYX.I18N.Feedback.success});
n()
}get ;
this.elements.form.model.value=this.facade.getSerializedJSON();
this.elements.form.environment.value=this.getEnv();
var s={};
$A(this.elements.form.elements).each(function(u){s[u.name]=u.value
}get );
s.name=ORYX.Editor.Cookie.getParams().identifier;
s.subject=("["+s.subject+"] "+s.title);
this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_LOADING_STATUS,text:ORYX.I18N.Feedback.sending});
new Ajax.Request(ORYX.CONFIG.ROOT_PATH+"feedback",{method:"POST",parameters:s,onSuccess:t.bind(this),onFailure:q.bind(this)});
this.toggleDialog()
}catch(r){q();
ORYX.Log.warn(r)
}return false
}get .bind(this);
var p=document.createElement("div");
p.className="fieldset";
var k=document.createElement("input");
k.type="hidden";
k.name="subject";
k.style.display="none";
var o=document.createElement("textarea");
o._defaultText=ORYX.I18N.Feedback.descriptionDesc;
o.name="description";
Event.observe(o,"focus",f.bindAsEventListener());
Event.observe(o,"blur",b.bindAsEventListener());
var m=document.createElement("input");
m._defaultText=ORYX.I18N.Feedback.titleDesc;
m.type="text";
m.name="title";
Event.observe(m,"focus",f.bindAsEventListener());
Event.observe(m,"blur",b.bindAsEventListener());
var d=document.createElement("input");
d._defaultText=ORYX.I18N.Feedback.emailDesc;
d.type="text";
d.name="email";
Event.observe(d,"focus",f.bindAsEventListener());
Event.observe(d,"blur",b.bindAsEventListener());
var h=document.createElement("input");
h.type="button";
h.className="submit";
h.onclick=this.elements.form.onsubmit;
if(ORYX.I18N.Feedback.submit){h.value=ORYX.I18N.Feedback.submit
}var c=document.createElement("input");
c.name="environment";
c.type="hidden";
c.style.display="none";
var e=document.createElement("input");
e.name="model";
e.type="hidden";
e.style.display="none";
p.appendChild(k);
p.appendChild(o);
p.appendChild(m);
p.appendChild(d);
p.appendChild(c);
p.appendChild(e);
p.appendChild(h);
n();
var g=document.createElement("ul");
g.setAttribute("class","subjects");
var a=[];
$A(ORYX.I18N.Feedback.subjects).each(function(r,q){try{var t=document.createElement("li");
t._subject=r.id;
t.className=r.id;
t.innerHTML=r.name;
t.style.width=parseInt(100/$A(ORYX.I18N.Feedback.subjects).length)+"%";
a.push(t);
g.appendChild(t);
var s=function(){a.each(function(v){if(v.className.match(r.id)){v.className=v._subject+" active";
k.value=r.name;
if(o.value==o._defaultText){o.value=r.description
}o._defaultText=r.description;
if(r.info&&(""+r.info).strip().length>0){this.elements.info.innerHTML=r.info
}else{this.elements.info.innerHTML=ORYX.I18N.Feedback.info||""
}}else{v.className=v._subject
}}get .bind(this))
}get .bind(this);
Event.observe(t,"click",s);
if(q==(ORYX.I18N.Feedback.subjects.length-1)){o.value="";
o._defaultText="";
s()
}}catch(u){ORYX.Log.warn("Incomplete I10N for ORYX.I18N.Feedback.subjects",r,ORYX.I18N.Feedback.subjects)
}}get .bind(this));
this.elements.form.appendChild(g);
this.elements.form.appendChild(p);
this.elements.info=document.createElement("div");
this.elements.info.setAttribute("class","info");
this.elements.info.innerHTML=ORYX.I18N.Feedback.info||"";
var l=document.createElement("div");
l.setAttribute("class","head");
this.elements.dialog=document.createElement("div");
this.elements.dialog.setAttribute("class","dialog");
this.elements.dialog.appendChild(l);
this.elements.dialog.appendChild(this.elements.info);
this.elements.dialog.appendChild(this.elements.form);
this.elements.container.appendChild(this.elements.dialog);
return this.elements.dialog
}get ,getEnv:function(){var b="";
b+="Browser: "+navigator.userAgent;
b+="

Browser Plugins: ";
if(navigator.plugins){for(var a=0;
a<navigator.plugins.length;
a++){var c=navigator.plugins[a];
b+=c.name+", "
}}if((typeof(screen.width)!="undefined")&&(screen.width&&screen.height)){b+="

Screen Resolution: "+screen.width+"x"+screen.height
}return b
}get });
if(!ORYX.Plugins){ORYX.Plugins=new Object()
}ORYX.Plugins.Undo=Clazz.extend({facade:undefined,undoStack:[],redoStack:[],construct:function(a){this.facade=a;
this.facade.registerOnEvent(ORYX.CONFIG.EVENT_EXECUTE_COMMANDS,this.handleExecuteCommands.bind(this))
}get ,handleExecuteCommands:function(a){if(!a.commands){return
}this.undoStack.push(a.commands);
this.redoStack=[]
}get ,doUndo:function(){var a=this.undoStack.pop();
if(a){this.redoStack.push(a);
a.each(function(b){b.rollback()
}get )
}this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_UNDO_ROLLBACK,commands:a})
}get ,doRedo:function(){var a=this.redoStack.pop();
if(a){this.undoStack.push(a);
a.each(function(b){b.execute()
}get )
}this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_UNDO_EXECUTE,commands:a})
}get });
Array.prototype.insertFrom=function(e,d){d=Math.max(0,d);
e=Math.min(Math.max(0,e),this.length-1);
var b=this[e];
var a=this.without(b);
var c=a.slice(0,d);
c.push(b);
if(a.length>d){c=c.concat(a.slice(d))
}return c
}get ;
if(!ORYX.Plugins){ORYX.Plugins=new Object()
}ORYX.Plugins.Arrangement=Clazz.extend({facade:undefined,construct:function(a){this.facade=a;
this.facade.offer({name:ORYX.I18N.Arrangement.btf,functionality:this.setZLevel.bind(this,this.setToTop),group:ORYX.I18N.Arrangement.groupZ,icon:ORYX.PATH+"images/shape_move_front.png",description:ORYX.I18N.Arrangement.btfDesc,index:1,minShape:1});
this.facade.offer({name:ORYX.I18N.Arrangement.btb,functionality:this.setZLevel.bind(this,this.setToBack),group:ORYX.I18N.Arrangement.groupZ,icon:ORYX.PATH+"images/shape_move_back.png",description:ORYX.I18N.Arrangement.btbDesc,index:2,minShape:1});
this.facade.offer({name:ORYX.I18N.Arrangement.bf,functionality:this.setZLevel.bind(this,this.setForward),group:ORYX.I18N.Arrangement.groupZ,icon:ORYX.PATH+"images/shape_move_forwards.png",description:ORYX.I18N.Arrangement.bfDesc,index:3,minShape:1});
this.facade.offer({name:ORYX.I18N.Arrangement.bb,functionality:this.setZLevel.bind(this,this.setBackward),group:ORYX.I18N.Arrangement.groupZ,icon:ORYX.PATH+"images/shape_move_backwards.png",description:ORYX.I18N.Arrangement.bbDesc,index:4,minShape:1});
this.facade.offer({name:ORYX.I18N.Arrangement.ab,functionality:this.alignShapes.bind(this,[ORYX.CONFIG.EDITOR_ALIGN_BOTTOM]),group:ORYX.I18N.Arrangement.groupA,icon:ORYX.PATH+"images/shape_align_bottom.png",description:ORYX.I18N.Arrangement.abDesc,index:1,minShape:2});
this.facade.offer({name:ORYX.I18N.Arrangement.am,functionality:this.alignShapes.bind(this,[ORYX.CONFIG.EDITOR_ALIGN_MIDDLE]),group:ORYX.I18N.Arrangement.groupA,icon:ORYX.PATH+"images/shape_align_middle.png",description:ORYX.I18N.Arrangement.amDesc,index:2,minShape:2});
this.facade.offer({name:ORYX.I18N.Arrangement.at,functionality:this.alignShapes.bind(this,[ORYX.CONFIG.EDITOR_ALIGN_TOP]),group:ORYX.I18N.Arrangement.groupA,icon:ORYX.PATH+"images/shape_align_top.png",description:ORYX.I18N.Arrangement.atDesc,index:3,minShape:2});
this.facade.offer({name:ORYX.I18N.Arrangement.al,functionality:this.alignShapes.bind(this,[ORYX.CONFIG.EDITOR_ALIGN_LEFT]),group:ORYX.I18N.Arrangement.groupA,icon:ORYX.PATH+"images/shape_align_left.png",description:ORYX.I18N.Arrangement.alDesc,index:4,minShape:2});
this.facade.offer({name:ORYX.I18N.Arrangement.ac,functionality:this.alignShapes.bind(this,[ORYX.CONFIG.EDITOR_ALIGN_CENTER]),group:ORYX.I18N.Arrangement.groupA,icon:ORYX.PATH+"images/shape_align_center.png",description:ORYX.I18N.Arrangement.acDesc,index:5,minShape:2});
this.facade.offer({name:ORYX.I18N.Arrangement.ar,functionality:this.alignShapes.bind(this,[ORYX.CONFIG.EDITOR_ALIGN_RIGHT]),group:ORYX.I18N.Arrangement.groupA,icon:ORYX.PATH+"images/shape_align_right.png",description:ORYX.I18N.Arrangement.arDesc,index:6,minShape:2});
this.facade.offer({name:ORYX.I18N.Arrangement.as,functionality:this.alignShapes.bind(this,[ORYX.CONFIG.EDITOR_ALIGN_SIZE]),group:ORYX.I18N.Arrangement.groupA,icon:ORYX.PATH+"images/shape_align_size.png",description:ORYX.I18N.Arrangement.asDesc,index:7,minShape:2});
this.facade.registerOnEvent(ORYX.CONFIG.EVENT_ARRANGEMENT_TOP,this.setZLevel.bind(this,this.setToTop));
this.facade.registerOnEvent(ORYX.CONFIG.EVENT_ARRANGEMENT_BACK,this.setZLevel.bind(this,this.setToBack));
this.facade.registerOnEvent(ORYX.CONFIG.EVENT_ARRANGEMENT_FORWARD,this.setZLevel.bind(this,this.setForward));
this.facade.registerOnEvent(ORYX.CONFIG.EVENT_ARRANGEMENT_BACKWARD,this.setZLevel.bind(this,this.setBackward))
}get ,setZLevel:function(d,b){var a=ORYX.Core.Command.extend({construct:function(g,f,e){this.callback=g;
this.elements=f;
this.elAndIndex=f.map(function(h){return{el:h,previous:h.parent.children[h.parent.children.indexOf(h)-1]}
}get );
this.facade=e
}get ,execute:function(){this.callback(this.elements);
this.facade.setSelection(this.elements)
}get ,rollback:function(){var g=this.elAndIndex.sortBy(function(n){var o=n.el;
var m=$A(o.node.parentNode.childNodes);
return m.indexOf(o.node)
}get );
for(var f=0;
f<g.length;
f++){var h=g[f].el;
var k=h.parent;
var l=k.children.indexOf(h);
var e=k.children.indexOf(g[f].previous);
e=e||0;
k.children=k.children.insertFrom(l,e);
h.node.parentNode.insertBefore(h.node,h.node.parentNode.childNodes[e+1])
}this.facade.setSelection(this.elements)
}get });
var c=new a(d,this.facade.getSelection(),this.facade);
if(b.excludeCommand){c.execute()
}else{this.facade.executeCommands([c])
}}get ,setToTop:function(b){var a=b.sortBy(function(e,c){var d=$A(e.node.parentNode.childNodes);
return d.indexOf(e.node)
}get );
a.each(function(c){var d=c.parent;
d.children=d.children.without(c);
d.children.push(c);
c.node.parentNode.appendChild(c.node)
}get )
}get ,setToBack:function(b){var a=b.sortBy(function(e,c){var d=$A(e.node.parentNode.childNodes);
return d.indexOf(e.node)
}get );
a=a.reverse();
a.each(function(c){var d=c.parent;
d.children=d.children.without(c);
d.children.unshift(c);
c.node.parentNode.insertBefore(c.node,c.node.parentNode.firstChild)
}get )
}get ,setBackward:function(c){var b=c.sortBy(function(f,d){var e=$A(f.node.parentNode.childNodes);
return e.indexOf(f.node)
}get );
b=b.reverse();
var a=b.findAll(function(d){return !b.some(function(e){return e.node==d.node.previousSibling
}get )
}get );
a.each(function(e){if(e.node.previousSibling===null){return
}var f=e.parent;
var d=f.children.indexOf(e);
f.children=f.children.insertFrom(d,d-1);
e.node.parentNode.insertBefore(e.node,e.node.previousSibling)
}get )
}get ,setForward:function(c){var b=c.sortBy(function(f,d){var e=$A(f.node.parentNode.childNodes);
return e.indexOf(f.node)
}get );
var a=b.findAll(function(d){return !b.some(function(e){return e.node==d.node.nextSibling
}get )
}get );
a.each(function(f){var d=f.node.nextSibling;
if(d===null){return
}var e=f.parent.children.indexOf(f);
var g=f.parent;
g.children=g.children.insertFrom(e,e+1);
f.node.parentNode.insertBefore(d,f.node)
}get )
}get ,alignShapes:function(b){var f=this.facade.getSelection();
f=this.facade.getCanvas().getShapesWithSharedParent(f);
f=f.findAll(function(h){return(h instanceof ORYX.Core.Node)
}get );
f=f.findAll(function(h){var k=h.getIncomingShapes();
return k.length==0||!f.include(k[0])
}get );
if(f.length<2){return
}var e=f[0].absoluteBounds().clone();
f.each(function(h){e.include(h.absoluteBounds().clone())
}get );
var d=0;
var c=0;
f.each(function(h){d=Math.max(h.bounds.width(),d);
c=Math.max(h.bounds.height(),c)
}get );
var a=ORYX.Core.Command.extend({construct:function(o,n,m,l,h,k){this.elements=o;
this.bounds=n;
this.maxHeight=m;
this.maxWidth=l;
this.way=h;
this.facade=k;
this.orgPos=[]
}get ,setBounds:function(h,l){if(!l){l={width:ORYX.CONFIG.MAXIMUM_SIZE,height:ORYX.CONFIG.MAXIMUM_SIZE}
}if(!h.bounds){throw"Bounds not definined."
}var k={a:{x:h.bounds.upperLeft().x-(this.maxWidth-h.bounds.width())/2,y:h.bounds.upperLeft().y-(this.maxHeight-h.bounds.height())/2},b:{x:h.bounds.lowerRight().x+(this.maxWidth-h.bounds.width())/2,y:h.bounds.lowerRight().y+(this.maxHeight-h.bounds.height())/2}};
if(this.maxWidth>l.width){k.a.x=h.bounds.upperLeft().x-(l.width-h.bounds.width())/2;
k.b.x=h.bounds.lowerRight().x+(l.width-h.bounds.width())/2
}if(this.maxHeight>l.height){k.a.y=h.bounds.upperLeft().y-(l.height-h.bounds.height())/2;
k.b.y=h.bounds.lowerRight().y+(l.height-h.bounds.height())/2
}h.bounds.set(k)
}get ,execute:function(){this.elements.each(function(h,k){this.orgPos[k]=h.bounds.upperLeft();
var l=this.bounds.clone();
if(h.parent&&!(h.parent instanceof ORYX.Core.Canvas)){var m=h.parent.absoluteBounds().upperLeft();
l.moveBy(-m.x,-m.y)
}switch(this.way){case ORYX.CONFIG.EDITOR_ALIGN_BOTTOM:h.bounds.moveTo({x:h.bounds.upperLeft().x,y:l.b.y-h.bounds.height()});
break;
case ORYX.CONFIG.EDITOR_ALIGN_MIDDLE:h.bounds.moveTo({x:h.bounds.upperLeft().x,y:(l.a.y+l.b.y-h.bounds.height())/2});
break;
case ORYX.CONFIG.EDITOR_ALIGN_TOP:h.bounds.moveTo({x:h.bounds.upperLeft().x,y:l.a.y});
break;
case ORYX.CONFIG.EDITOR_ALIGN_LEFT:h.bounds.moveTo({x:l.a.x,y:h.bounds.upperLeft().y});
break;
case ORYX.CONFIG.EDITOR_ALIGN_CENTER:h.bounds.moveTo({x:(l.a.x+l.b.x-h.bounds.width())/2,y:h.bounds.upperLeft().y});
break;
case ORYX.CONFIG.EDITOR_ALIGN_RIGHT:h.bounds.moveTo({x:l.b.x-h.bounds.width(),y:h.bounds.upperLeft().y});
break;
case ORYX.CONFIG.EDITOR_ALIGN_SIZE:if(h.isResizable){this.orgPos[k]={a:h.bounds.upperLeft(),b:h.bounds.lowerRight()};
this.setBounds(h,h.maximumSize)
}break
}}get .bind(this));
this.facade.getCanvas().update();
this.facade.updateSelection()
}get ,rollback:function(){this.elements.each(function(h,k){if(this.way==ORYX.CONFIG.EDITOR_ALIGN_SIZE){if(h.isResizable){h.bounds.set(this.orgPos[k])
}}else{h.bounds.moveTo(this.orgPos[k])
}}get .bind(this));
this.facade.getCanvas().update();
this.facade.updateSelection()
}get });
var g=new a(f,e,c,d,parseInt(b),this.facade);
this.facade.executeCommands([g])
}get });
if(!ORYX.Plugins){ORYX.Plugins=new Object()
}ORYX.Plugins.Grouping=Clazz.extend({facade:undefined,construct:function(a){this.facade=a;
this.selectedElements=[];
this.groups=[]
}get ,isEnabled:function(a){var b=this.selectedElements;
return a===this.groups.any(function(c){return c.length===b.length&&c.all(function(d){return b.member(d)
}get )
}get )
}get ,onSelectionChanged:function(b){var a=b.elements;
this.selectedElements=this.groups.findAll(function(c){return c.any(function(d){return a.member(d)
}get )
}get );
this.selectedElements.push(a);
this.selectedElements=this.selectedElements.flatten().uniq();
if(this.selectedElements.length!==a.length){this.facade.setSelection(this.selectedElements)
}}get ,createGroup:function(){var c=this.facade.getSelection();
var a=ORYX.Core.Command.extend({construct:function(g,d,f,e){this.selectedElements=g;
this.groups=d;
this.callback=f;
this.facade=e
}get ,execute:function(){var d=this.groups.findAll(function(e){return !e.any(function(f){return c.member(f)
}get )
}get );
d.push(c);
this.callback(d.clone());
this.facade.setSelection(this.selectedElements)
}get ,rollback:function(){this.callback(this.groups.clone());
this.facade.setSelection(this.selectedElements)
}get });
var b=new a(c,this.groups.clone(),this.setGroups.bind(this),this.facade);
this.facade.executeCommands([b])
}get ,deleteGroup:function(){var c=this.facade.getSelection();
var a=ORYX.Core.Command.extend({construct:function(g,d,f,e){this.selectedElements=g;
this.groups=d;
this.callback=f;
this.facade=e
}get ,execute:function(){var d=this.groups.partition(function(e){return e.length!==c.length||!e.all(function(f){return c.member(f)
}get )
}get );
this.callback(d[0]);
this.facade.setSelection(this.selectedElements)
}get ,rollback:function(){this.callback(this.groups.clone());
this.facade.setSelection(this.selectedElements)
}get });
var b=new a(c,this.groups.clone(),this.setGroups.bind(this),this.facade);
this.facade.executeCommands([b])
}get ,setGroups:function(a){this.groups=a
}get });
if(!ORYX.Plugins){ORYX.Plugins=new Object()
}ORYX.Plugins.ShapeHighlighting=Clazz.extend({construct:function(a){this.parentNode=a.getCanvas().getSvgContainer();
this.node=ORYX.Editor.graft("http://www.w3.org/2000/svg",this.parentNode,["g"]);
this.highlightNodes={};
a.registerOnEvent(ORYX.CONFIG.EVENT_HIGHLIGHT_SHOW,this.setHighlight.bind(this));
a.registerOnEvent(ORYX.CONFIG.EVENT_HIGHLIGHT_HIDE,this.hideHighlight.bind(this))
}get ,setHighlight:function(a){if(a&&a.highlightId){var b=this.highlightNodes[a.highlightId];
if(!b){b=ORYX.Editor.graft("http://www.w3.org/2000/svg",this.node,["path",{"stroke-width":2,fill:"none"}]);
this.highlightNodes[a.highlightId]=b
}if(a.elements&&a.elements.length>0){this.setAttributesByStyle(b,a);
this.show(b)
}else{this.hide(b)
}}}get ,hideHighlight:function(a){if(a&&a.highlightId&&this.highlightNodes[a.highlightId]){this.hide(this.highlightNodes[a.highlightId])
}}get ,hide:function(a){a.setAttributeNS(null,"display","none")
}get ,show:function(a){a.setAttributeNS(null,"display","")
}get ,setAttributesByStyle:function(b,a){if(a.style&&a.style==ORYX.CONFIG.SELECTION_HIGHLIGHT_STYLE_RECTANGLE){var d=a.elements[0].absoluteBounds();
var c=a.strokewidth?a.strokewidth:ORYX.CONFIG.BORDER_OFFSET;
b.setAttributeNS(null,"d",this.getPathRectangle(d.a,d.b,c));
b.setAttributeNS(null,"stroke",a.color?a.color:ORYX.CONFIG.SELECTION_HIGHLIGHT_COLOR);
b.setAttributeNS(null,"stroke-opacity",a.opacity?a.opacity:0.2);
b.setAttributeNS(null,"stroke-width",c)
}else{if(a.elements.length==1&&a.elements[0] instanceof ORYX.Core.Edge&&a.highlightId!="selection"){b.setAttributeNS(null,"d",this.getPathEdge(a.elements[0].dockers));
b.setAttributeNS(null,"stroke",a.color?a.color:ORYX.CONFIG.SELECTION_HIGHLIGHT_COLOR);
b.setAttributeNS(null,"stroke-opacity",a.opacity?a.opacity:0.2);
b.setAttributeNS(null,"stroke-width",ORYX.CONFIG.OFFSET_EDGE_BOUNDS)
}else{b.setAttributeNS(null,"d",this.getPathByElements(a.elements));
b.setAttributeNS(null,"stroke",a.color?a.color:ORYX.CONFIG.SELECTION_HIGHLIGHT_COLOR);
b.setAttributeNS(null,"stroke-opacity",a.opacity?a.opacity:1);
b.setAttributeNS(null,"stroke-width",a.strokewidth?a.strokewidth:2)
}}}get ,getPathByElements:function(a){if(!a||a.length<=0){return undefined
}var c=ORYX.CONFIG.SELECTED_AREA_PADDING;
var b="";
a.each((function(f){if(!f){return
}var g=f.absoluteBounds();
g.widen(c);
var e=g.upperLeft();
var d=g.lowerRight();
b=b+this.getPath(e,d)
}get ).bind(this));
return b
}get ,getPath:function(d,c){return this.getPathCorners(d,c)
}get ,getPathCorners:function(d,c){var e=ORYX.CONFIG.SELECTION_HIGHLIGHT_SIZE;
var f="";
f=f+"M"+d.x+" "+(d.y+e)+" l0 -"+e+" l"+e+" 0 ";
f=f+"M"+d.x+" "+(c.y-e)+" l0 "+e+" l"+e+" 0 ";
f=f+"M"+c.x+" "+(c.y-e)+" l0 "+e+" l-"+e+" 0 ";
f=f+"M"+c.x+" "+(d.y+e)+" l0 -"+e+" l-"+e+" 0 ";
return f
}get ,getPathRectangle:function(d,c,h){var e=ORYX.CONFIG.SELECTION_HIGHLIGHT_SIZE;
var f="";
var g=h/2;
f=f+"M"+(d.x+g)+" "+(d.y);
f=f+" L"+(d.x+g)+" "+(c.y-g);
f=f+" L"+(c.x-g)+" "+(c.y-g);
f=f+" L"+(c.x-g)+" "+(d.y+g);
f=f+" L"+(d.x+g)+" "+(d.y+g);
return f
}get ,getPathEdge:function(a){var b=a.length;
var c="M"+a[0].bounds.center().x+" "+a[0].bounds.center().y;
for(i=1;
i<b;
i++){var d=a[i].bounds.center();
c=c+" L"+d.x+" "+d.y
}return c
}get });
ORYX.Plugins.HighlightingSelectedShapes=Clazz.extend({construct:function(a){this.facade=a;
this.opacityFull=0.9;
this.opacityLow=0.4
}get ,onSelectionChanged:function(a){if(a.elements&&a.elements.length>1){this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_HIGHLIGHT_SHOW,highlightId:"selection",elements:a.elements.without(a.subSelection),color:ORYX.CONFIG.SELECTION_HIGHLIGHT_COLOR,opacity:!a.subSelection?this.opacityFull:this.opacityLow});
if(a.subSelection){this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_HIGHLIGHT_SHOW,highlightId:"subselection",elements:[a.subSelection],color:ORYX.CONFIG.SELECTION_HIGHLIGHT_COLOR,opacity:this.opacityFull})
}else{this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_HIGHLIGHT_HIDE,highlightId:"subselection"})
}}else{this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_HIGHLIGHT_HIDE,highlightId:"selection"});
this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_HIGHLIGHT_HIDE,highlightId:"subselection"})
}}get });
if(!ORYX.Plugins){ORYX.Plugins=new Object()
}ORYX.Plugins.DragDocker=Clazz.extend({construct:function(a){this.facade=a;
this.VALIDCOLOR=ORYX.CONFIG.SELECTION_VALID_COLOR;
this.INVALIDCOLOR=ORYX.CONFIG.SELECTION_INVALID_COLOR;
this.shapeSelection=undefined;
this.docker=undefined;
this.dockerParent=undefined;
this.dockerSource=undefined;
this.dockerTarget=undefined;
this.lastUIObj=undefined;
this.isStartDocker=undefined;
this.isEndDocker=undefined;
this.undockTreshold=10;
this.initialDockerPosition=undefined;
this.outerDockerNotMoved=undefined;
this.isValid=false;
this.facade.registerOnEvent(ORYX.CONFIG.EVENT_MOUSEDOWN,this.handleMouseDown.bind(this));
this.facade.registerOnEvent(ORYX.CONFIG.EVENT_DOCKERDRAG,this.handleDockerDrag.bind(this));
this.facade.registerOnEvent(ORYX.CONFIG.EVENT_MOUSEOVER,this.handleMouseOver.bind(this));
this.facade.registerOnEvent(ORYX.CONFIG.EVENT_MOUSEOUT,this.handleMouseOut.bind(this))
}get ,handleMouseOut:function(b,a){if(!this.docker&&a instanceof ORYX.Core.Controls.Docker){a.hide()
}else{if(!this.docker&&a instanceof ORYX.Core.Edge){a.dockers.each(function(c){c.hide()
}get )
}}}get ,handleMouseOver:function(b,a){if(!this.docker&&a instanceof ORYX.Core.Controls.Docker){a.show()
}else{if(!this.docker&&a instanceof ORYX.Core.Edge){a.dockers.each(function(c){c.show()
}get )
}}}get ,handleDockerDrag:function(b,a){this.handleMouseDown(b.uiEvent,a)
}get ,handleMouseDown:function(d,c){if(c instanceof ORYX.Core.Controls.Docker&&c.isMovable){this.shapeSelection=this.facade.getSelection();
this.facade.setSelection();
this.docker=c;
this.initialDockerPosition=this.docker.bounds.center();
this.outerDockerNotMoved=false;
this.dockerParent=c.parent;
this._commandArg={docker:c,dockedShape:c.getDockedShape(),refPoint:c.referencePoint||c.bounds.center()};
this.docker.show();
if(c.parent instanceof ORYX.Core.Edge&&(c.parent.dockers.first()==c||c.parent.dockers.last()==c)){if(c.parent.dockers.first()==c&&c.parent.dockers.last().getDockedShape()){this.dockerTarget=c.parent.dockers.last().getDockedShape()
}else{if(c.parent.dockers.last()==c&&c.parent.dockers.first().getDockedShape()){this.dockerSource=c.parent.dockers.first().getDockedShape()
}}}else{this.dockerSource=undefined;
this.dockerTarget=undefined
}this.isStartDocker=this.docker.parent.dockers.first()===this.docker;
this.isEndDocker=this.docker.parent.dockers.last()===this.docker;
this.facade.getCanvas().add(this.docker.parent);
this.docker.parent.getLabels().each(function(e){e.hide()
}get );
if((!this.isStartDocker&&!this.isEndDocker)||!this.docker.isDocked()){this.docker.setDockedShape(undefined);
var b=this.facade.eventCoordinates(d);
this.docker.bounds.centerMoveTo(b);
this.dockerParent._update()
}else{this.outerDockerNotMoved=true
}var a={movedCallback:this.dockerMoved.bind(this),upCallback:this.dockerMovedFinished.bind(this)};
ORYX.Core.UIEnableDrag(d,c,a)
}}get ,dockerMoved:function(u){this.outerDockerNotMoved=false;
var l=undefined;
if(this.docker.parent){if(this.isStartDocker||this.isEndDocker){var o=this.facade.eventCoordinates(u);
if(this.docker.isDocked()){var b=ORYX.Core.Math.getDistancePointToPoint(o,this.initialDockerPosition);
if(b<this.undockTreshold){this.outerDockerNotMoved=true;
return
}this.docker.setDockedShape(undefined);
this.dockerParent._update()
}var s=this.facade.getCanvas().getAbstractShapesAtPosition(o);
var q=s.pop();
if(this.docker.parent===q){q=s.pop()
}if(this.lastUIObj==q){}else{if(q instanceof ORYX.Core.Shape){var t=this.docker.parent.getStencil().stencilSet();
if(this.docker.parent instanceof ORYX.Core.Edge){var v=this.getHighestParentBeforeCanvas(q);
if(v instanceof ORYX.Core.Edge&&this.docker.parent===v){this.isValid=false;
this.dockerParent._update();
return
}this.isValid=false;
var a=q,c=q;
while(!this.isValid&&a&&!(a instanceof ORYX.Core.Canvas)){q=a;
this.isValid=this.facade.getRules().canConnect({sourceShape:this.dockerSource?this.dockerSource:(this.isStartDocker?q:undefined),edgeShape:this.docker.parent,targetShape:this.dockerTarget?this.dockerTarget:(this.isEndDocker?q:undefined)});
a=a.parent
}if(!this.isValid){q=c
}}else{this.isValid=this.facade.getRules().canConnect({sourceShape:q,edgeShape:this.docker.parent,targetShape:this.docker.parent})
}if(this.lastUIObj){this.hideMagnets(this.lastUIObj)
}if(this.isValid){this.showMagnets(q)
}this.showHighlight(q,this.isValid?this.VALIDCOLOR:this.INVALIDCOLOR);
this.lastUIObj=q
}else{this.hideHighlight();
this.lastUIObj?this.hideMagnets(this.lastUIObj):null;
this.lastUIObj=undefined;
this.isValid=false
}}if(this.lastUIObj&&this.isValid&&!(u.shiftKey||u.ctrlKey)){l=this.lastUIObj.magnets.find(function(y){return y.absoluteBounds().isIncluded(o)
}get );
if(l){this.docker.bounds.centerMoveTo(l.absoluteCenterXY())
}}}}if(!(u.shiftKey||u.ctrlKey)&&!l){var n=ORYX.CONFIG.DOCKER_SNAP_OFFSET;
var h=n+1;
var f=n+1;
var x=this.docker.bounds.center();
if(this.docker.parent){this.docker.parent.dockers.each((function(z){if(this.docker==z){return
}var y=z.referencePoint?z.getAbsoluteReferencePoint():z.bounds.center();
h=Math.abs(h)>Math.abs(y.x-x.x)?y.x-x.x:h;
f=Math.abs(f)>Math.abs(y.y-x.y)?y.y-x.y:f
}get ).bind(this));
if(Math.abs(h)<n||Math.abs(f)<n){h=Math.abs(h)<n?h:0;
f=Math.abs(f)<n?f:0;
this.docker.bounds.centerMoveTo(x.x+h,x.y+f)
}else{var d=this.docker.parent.dockers[Math.max(this.docker.parent.dockers.indexOf(this.docker)-1,0)];
var r=this.docker.parent.dockers[Math.min(this.docker.parent.dockers.indexOf(this.docker)+1,this.docker.parent.dockers.length-1)];
if(d&&r&&d!==this.docker&&r!==this.docker){var e=d.bounds.center();
var g=r.bounds.center();
var p=this.docker.bounds.center();
if(ORYX.Core.Math.isPointInLine(p.x,p.y,e.x,e.y,g.x,g.y,10)){var w=(Number(g.y)-Number(e.y))/(Number(g.x)-Number(e.x));
var m=((e.y-(e.x*w))-(p.y-(p.x*(-Math.pow(w,-1)))))/((-Math.pow(w,-1))-w);
var k=(e.y-(e.x*w))+(w*m);
if(isNaN(m)||isNaN(k)){return
}this.docker.bounds.centerMoveTo(m,k)
}}}}}this.dockerParent._update()
}get ,dockerMovedFinished:function(e){this.facade.setSelection(this.shapeSelection);
this.hideHighlight();
this.dockerParent.getLabels().each(function(g){g.show()
}get );
if(this.lastUIObj&&(this.isStartDocker||this.isEndDocker)){if(this.isValid){this.docker.setDockedShape(this.lastUIObj);
this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_DRAGDOCKER_DOCKED,docker:this.docker,parent:this.docker.parent,target:this.lastUIObj})
}this.hideMagnets(this.lastUIObj)
}this.docker.hide();
if(this.outerDockerNotMoved){var d=this.facade.eventCoordinates(e);
var a=this.facade.getCanvas().getAbstractShapesAtPosition(d);
var b=a.findAll(function(g){return g instanceof ORYX.Core.Node
}get );
a=b.length?b:a;
this.facade.setSelection(a)
}else{var c=ORYX.Core.Command.extend({construct:function(n,h,g,m,l,k){this.docker=n;
this.index=n.parent.dockers.indexOf(n);
this.newPosition=h;
this.newDockedShape=m;
this.oldPosition=g;
this.oldDockedShape=l;
this.facade=k;
this.index=n.parent.dockers.indexOf(n);
this.shape=n.parent
}get ,execute:function(){if(!this.docker.parent){this.docker=this.shape.dockers[this.index]
}this.dock(this.newDockedShape,this.newPosition);
this.removedDockers=this.shape.removeUnusedDockers();
this.facade.updateSelection()
}get ,rollback:function(){this.dock(this.oldDockedShape,this.oldPosition);
(this.removedDockers||$H({})).each(function(g){this.shape.add(g.value,Number(g.key));
this.shape._update(true)
}get .bind(this));
this.facade.updateSelection()
}get ,dock:function(g,h){this.docker.setDockedShape(undefined);
if(g){this.docker.setDockedShape(g);
this.docker.setReferencePoint(h)
}else{this.docker.bounds.centerMoveTo(h)
}this.facade.getCanvas().update()
}get });
if(this.docker.parent){var f=new c(this.docker,this.docker.getDockedShape()?this.docker.referencePoint:this.docker.bounds.center(),this._commandArg.refPoint,this.docker.getDockedShape(),this._commandArg.dockedShape,this.facade);
this.facade.executeCommands([f])
}}this.docker=undefined;
this.dockerParent=undefined;
this.dockerSource=undefined;
this.dockerTarget=undefined;
this.lastUIObj=undefined
}get ,hideHighlight:function(){this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_HIGHLIGHT_HIDE,highlightId:"validDockedShape"})
}get ,showHighlight:function(b,a){this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_HIGHLIGHT_SHOW,highlightId:"validDockedShape",elements:[b],color:a})
}get ,showMagnets:function(a){a.magnets.each(function(b){b.show()
}get )
}get ,hideMagnets:function(a){a.magnets.each(function(b){b.hide()
}get )
}get ,getHighestParentBeforeCanvas:function(a){if(!(a instanceof ORYX.Core.Shape)){return undefined
}var b=a.parent;
while(b&&!(b.parent instanceof ORYX.Core.Canvas)){b=b.parent
}return b
}get });
if(!ORYX.Plugins){ORYX.Plugins=new Object()
}ORYX.Plugins.AddDocker=Clazz.extend({construct:function(a){this.facade=a;
this.facade.registerOnEvent(ORYX.CONFIG.EVENT_MOUSEDOWN,this.handleMouseDown.bind(this))
}get ,enableAddDocker:function(a,b){this.addDockerButton=a;
if(b&&this.deleteDockerButton){this.deleteDockerButton.toggle(false)
}}get ,enableDeleteDocker:function(a,b){this.deleteDockerButton=a;
if(b&&this.addDockerButton){this.addDockerButton.toggle(false)
}}get ,enabledAdd:function(){return this.addDockerButton?this.addDockerButton.pressed:false
}get ,enabledDelete:function(){return this.deleteDockerButton?this.deleteDockerButton.pressed:false
}get ,handleMouseDown:function(b,a){if(this.enabledAdd()&&a instanceof ORYX.Core.Edge){this.newDockerCommand({edge:a,position:this.facade.eventCoordinates(b)})
}else{if(this.enabledDelete()&&a instanceof ORYX.Core.Controls.Docker&&a.parent instanceof ORYX.Core.Edge){this.newDockerCommand({edge:a.parent,docker:a})
}else{if(this.enabledAdd()){this.addDockerButton.toggle(false)
}else{if(this.enabledDelete()){this.deleteDockerButton.toggle(false)
}}}}}get ,newDockerCommand:function(b){if(!b.edge){return
}var a=ORYX.Core.Command.extend({construct:function(h,f,e,g,k,d){this.addEnabled=h;
this.deleteEnabled=f;
this.edge=e;
this.docker=g;
this.pos=k;
this.facade=d
}get ,execute:function(){if(this.addEnabled){this.docker=this.edge.addDocker(this.pos,this.docker);
this.index=this.edge.dockers.indexOf(this.docker)
}else{if(this.deleteEnabled){this.index=this.edge.dockers.indexOf(this.docker);
this.pos=this.docker.bounds.center();
this.edge.removeDocker(this.docker)
}}this.facade.getCanvas().update();
this.facade.updateSelection()
}get ,rollback:function(){if(this.addEnabled){if(this.docker instanceof ORYX.Core.Controls.Docker){this.edge.removeDocker(this.docker)
}}else{if(this.deleteEnabled){this.edge.add(this.docker,this.index)
}}this.facade.getCanvas().update();
this.facade.updateSelection()
}get });
var c=new a(this.enabledAdd(),this.enabledDelete(),b.edge,b.docker,b.position,this.facade);
this.facade.executeCommands([c])
}get });
if(!ORYX.Plugins){ORYX.Plugins=new Object()
}ORYX.Plugins.DockerCreation=Clazz.extend({construct:function(a){this.facade=a;
this.active=false;
this.circle=ORYX.Editor.graft("http://www.w3.org/2000/svg",null,["g",{"pointer-events":"none"},["circle",{cx:"8",cy:"8",r:"3",fill:"yellow"}]]);
this.facade.registerOnEvent(ORYX.CONFIG.EVENT_MOUSEDOWN,this.handleMouseDown.bind(this));
this.facade.registerOnEvent(ORYX.CONFIG.EVENT_MOUSEOVER,this.handleMouseOver.bind(this));
this.facade.registerOnEvent(ORYX.CONFIG.EVENT_MOUSEOUT,this.handleMouseOut.bind(this));
this.facade.registerOnEvent(ORYX.CONFIG.EVENT_MOUSEMOVE,this.handleMouseMove.bind(this));
this.facade.registerOnEvent(ORYX.CONFIG.EVENT_DBLCLICK,function(){window.clearTimeout(this.timer)
}get .bind(this));
this.facade.registerOnEvent(ORYX.CONFIG.EVENT_MOUSEUP,function(){window.clearTimeout(this.timer)
}get .bind(this))
}get ,handleMouseOut:function(b,a){if(this.active){this.hideOverlay();
this.active=false
}}get ,handleMouseOver:function(b,a){if(a instanceof ORYX.Core.Edge&&this.isEdgeDocked(a)){this.showOverlay(a,this.facade.eventCoordinates(b))
}this.active=true
}get ,handleMouseDown:function(b,a){if(b.which==1&&a instanceof ORYX.Core.Edge&&this.isEdgeDocked(a)){window.clearTimeout(this.timer);
this.timer=window.setTimeout(function(){this.addDockerCommand({edge:a,event:b,position:this.facade.eventCoordinates(b)})
}get .bind(this),200);
this.hideOverlay()
}}get ,handleMouseMove:function(b,a){if(a instanceof ORYX.Core.Edge&&this.isEdgeDocked(a)){if(this.active){this.hideOverlay();
this.showOverlay(a,this.facade.eventCoordinates(b))
}else{this.showOverlay(a,this.facade.eventCoordinates(b))
}}}get ,isEdgeDocked:function(a){return !!(a.incoming.length||a.outgoing.length)
}get ,addDockerCommand:function(b){if(!b.edge){return
}var a=ORYX.Core.Command.extend({construct:function(f,g,h,e,d){this.edge=f;
this.docker=g;
this.pos=h;
this.facade=e;
this.options=d
}get ,execute:function(){this.docker=this.edge.addDocker(this.pos,this.docker);
this.index=this.edge.dockers.indexOf(this.docker);
this.facade.getCanvas().update();
this.facade.updateSelection();
this.options.docker=this.docker
}get ,rollback:function(){if(this.docker instanceof ORYX.Core.Controls.Docker){this.edge.removeDocker(this.docker)
}this.facade.getCanvas().update();
this.facade.updateSelection()
}get });
var c=new a(b.edge,b.docker,b.position,this.facade,b);
this.facade.executeCommands([c]);
this.facade.raiseEvent({uiEvent:b.event,type:ORYX.CONFIG.EVENT_DOCKERDRAG},b.docker)
}get ,showOverlay:function(a,k){var e=k;
var f=[0,1];
var b=Infinity;
for(var g=0,d=a.dockers.length;
g<d-1;
g++){var c=ORYX.Core.Math.getPointOfIntersectionPointLine(a.dockers[g].bounds.center(),a.dockers[g+1].bounds.center(),k,true);
if(!c){continue
}var h=ORYX.Core.Math.getDistancePointToPoint(k,c);
if(b>h){b=h;
e=c
}}this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_OVERLAY_SHOW,id:"ghostpoint",shapes:[a],node:this.circle,ghostPoint:e,dontCloneNode:true})
}get ,hideOverlay:function(){this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_OVERLAY_HIDE,id:"ghostpoint"})
}get });
if(!ORYX.Plugins){ORYX.Plugins=new Object()
}ORYX.Plugins.SSExtensionLoader={construct:function(a){this.facade=a
}get ,addSSExtension:function(facade){this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_LOADING_ENABLE,text:ORYX.I18N.SSExtensionLoader.loading});
var url=ORYX.CONFIG.SS_EXTENSIONS_CONFIG;
new Ajax.Request(url,{method:"GET",asynchronous:false,onSuccess:(function(transport){try{eval("var jsonObject = "+transport.responseText);
var stencilsets=this.facade.getStencilSets();
var validExtensions=jsonObject.extensions.findAll(function(extension){var stencilset=stencilsets[extension["extends"]];
if(stencilset){return true
}else{return false
}}get );
var loadedExtensions=validExtensions.findAll(function(extension){return stencilsets.values().any(function(ss){if(ss.extensions()[extension.namespace]){return true
}else{return false
}}get )
}get );
if(validExtensions.size()==0){Ext.Msg.alert(ORYX.I18N.Oryx.title,ORYX.I18N.SSExtensionLoader.noExt)
}else{this._showPanel(validExtensions,loadedExtensions,this._loadExtensions.bind(this))
}}catch(e){console.log(e);
Ext.Msg.alert(ORYX.I18N.Oryx.title,ORYX.I18N.SSExtensionLoader.failed1)
}this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_LOADING_DISABLE})
}get ).bind(this),onFailure:(function(transport){Ext.Msg.alert(ORYX.I18N.Oryx.title,ORYX.I18N.SSExtensionLoader.failed2);
this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_LOADING_DISABLE})
}get ).bind(this)})
}get ,_loadExtensions:function(b){var c=this.facade.getStencilSets();
var d=false;
c.values().each(function(e){var f=e.extensions().values().select(function(g){return b[g.namespace]==undefined
}get );
f.each(function(g){e.removeExtension(g.namespace);
d=true
}get )
}get );
b.each(function(f){var e=c[f["extends"]];
if(e){e.addExtension(ORYX.CONFIG.SS_EXTENSIONS_FOLDER+f.definition);
d=true
}}get .bind(this));
if(d){c.values().each(function(e){this.facade.getRules().initializeRules(e)
}get .bind(this));
this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_STENCIL_SET_LOADED,lazyLoaded:true});
var a=this.facade.getSelection();
this.facade.setSelection();
this.facade.setSelection(a)
}}get ,_showPanel:function(h,k,c){var e=[];
h.each(function(l){e.push([l.title,l.definition,l["extends"]])
}get );
var d=new Ext.grid.CheckboxSelectionModel();
var a=new Ext.grid.GridPanel({deferRowRender:false,id:"oryx_new_stencilset_extention_grid",store:new Ext.data.SimpleStore({fields:["title","definition","extends"]}),cm:new Ext.grid.ColumnModel([d,{header:ORYX.I18N.SSExtensionLoader.panelTitle,width:200,sortable:true,dataIndex:"title"}]),sm:d,frame:true,width:200,height:200,iconCls:"icon-grid",listeners:{render:function(){this.getStore().loadData(e);
g.defer(1)
}get }});
function g(){var l=new Array();
a.store.each(function(m){if(k.any(function(n){return n.definition==m.get("definition")
}get )){l.push(m)
}}get );
d.selectRecords(l)
}get var b=new Ext.Panel({items:[{xtype:"label",text:ORYX.I18N.SSExtensionLoader.panelText,style:"margin:10px;display:block"},a],frame:true,buttons:[{text:ORYX.I18N.SSExtensionLoader.labelImport,handler:function(){var m=Ext.getCmp("oryx_new_stencilset_extention_grid").getSelectionModel();
var l=m.selections.items.collect(function(n){return n.data
}get );
Ext.getCmp("oryx_new_stencilset_extention_window").close();
c(l)
}get .bind(this)},{text:ORYX.I18N.SSExtensionLoader.labelCancel,handler:function(){Ext.getCmp("oryx_new_stencilset_extention_window").close()
}get .bind(this)}]});
var f=new Ext.Window({id:"oryx_new_stencilset_extention_window",width:227,title:ORYX.I18N.Oryx.title,floating:true,shim:true,modal:true,resizable:false,autoHeight:true,items:[b]});
f.show()
}get };
ORYX.Plugins.SSExtensionLoader=Clazz.extend(ORYX.Plugins.SSExtensionLoader);
if(!ORYX.Plugins){ORYX.Plugins=new Object()
}ORYX.Plugins.SelectionFrame=Clazz.extend({construct:function(a){this.facade=a;
this.facade.registerOnEvent(ORYX.CONFIG.EVENT_MOUSEDOWN,this.handleMouseDown.bind(this));
document.documentElement.addEventListener(ORYX.CONFIG.EVENT_MOUSEUP,this.handleMouseUp.bind(this),true);
this.position={x:0,y:0};
this.size={width:0,height:0};
this.offsetPosition={x:0,y:0};
this.moveCallback=undefined;
this.offsetScroll={x:0,y:0};
this.node=ORYX.Editor.graft("http://www.w3.org/1999/xhtml",this.facade.getCanvas().getHTMLContainer(),["div",{"class":"Oryx_SelectionFrame"}]);
this.hide()
}get ,handleMouseDown:function(d,c){if(c instanceof ORYX.Core.Canvas){var e=c.rootNode.parentNode.parentNode;
var b=this.facade.getCanvas().node.getScreenCTM();
this.offsetPosition={x:b.e,y:b.f};
this.setPos({x:Event.pointerX(d)-this.offsetPosition.x,y:Event.pointerY(d)-this.offsetPosition.y});
this.resize({width:0,height:0});
this.moveCallback=this.handleMouseMove.bind(this);
document.documentElement.addEventListener(ORYX.CONFIG.EVENT_MOUSEMOVE,this.moveCallback,false);
this.offsetScroll={x:e.scrollLeft,y:e.scrollTop};
this.show()
}Event.stop(d)
}get ,handleMouseUp:function(f){if(this.moveCallback){this.hide();
document.documentElement.removeEventListener(ORYX.CONFIG.EVENT_MOUSEMOVE,this.moveCallback,false);
this.moveCallback=undefined;
var e=this.facade.getCanvas().node.getScreenCTM();
var d={x:this.size.width>0?this.position.x:this.position.x+this.size.width,y:this.size.height>0?this.position.y:this.position.y+this.size.height};
var c={x:d.x+Math.abs(this.size.width),y:d.y+Math.abs(this.size.height)};
d.x/=e.a;
d.y/=e.d;
c.x/=e.a;
c.y/=e.d;
var g=this.facade.getCanvas().getChildShapes(true).findAll(function(b){var a=b.absoluteBounds();
var k=a.upperLeft();
var h=a.lowerRight();
if(k.x>d.x&&k.y>d.y&&h.x<c.x&&h.y<c.y){return true
}return false
}get );
this.facade.setSelection(g)
}}get ,handleMouseMove:function(b){var a={width:Event.pointerX(b)-this.position.x-this.offsetPosition.x,height:Event.pointerY(b)-this.position.y-this.offsetPosition.y,};
var c=this.facade.getCanvas().rootNode.parentNode.parentNode;
a.width-=this.offsetScroll.x-c.scrollLeft;
a.height-=this.offsetScroll.y-c.scrollTop;
this.resize(a);
Event.stop(b)
}get ,hide:function(){this.node.style.display="none"
}get ,show:function(){this.node.style.display=""
}get ,setPos:function(a){this.node.style.top=a.y+"px";
this.node.style.left=a.x+"px";
this.position=a
}get ,resize:function(a){this.setPos(this.position);
this.size=Object.clone(a);
if(a.width<0){this.node.style.left=(this.position.x+a.width)+"px";
a.width=-a.width
}if(a.height<0){this.node.style.top=(this.position.y+a.height)+"px";
a.height=-a.height
}this.node.style.width=a.width+"px";
this.node.style.height=a.height+"px"
}get });
if(!ORYX.Plugins){ORYX.Plugins=new Object()
}ORYX.Plugins.ShapeHighlighting=Clazz.extend({construct:function(a){this.parentNode=a.getCanvas().getSvgContainer();
this.node=ORYX.Editor.graft("http://www.w3.org/2000/svg",this.parentNode,["g"]);
this.highlightNodes={};
a.registerOnEvent(ORYX.CONFIG.EVENT_HIGHLIGHT_SHOW,this.setHighlight.bind(this));
a.registerOnEvent(ORYX.CONFIG.EVENT_HIGHLIGHT_HIDE,this.hideHighlight.bind(this))
}get ,setHighlight:function(a){if(a&&a.highlightId){var b=this.highlightNodes[a.highlightId];
if(!b){b=ORYX.Editor.graft("http://www.w3.org/2000/svg",this.node,["path",{"stroke-width":2,fill:"none"}]);
this.highlightNodes[a.highlightId]=b
}if(a.elements&&a.elements.length>0){this.setAttributesByStyle(b,a);
this.show(b)
}else{this.hide(b)
}}}get ,hideHighlight:function(a){if(a&&a.highlightId&&this.highlightNodes[a.highlightId]){this.hide(this.highlightNodes[a.highlightId])
}}get ,hide:function(a){a.setAttributeNS(null,"display","none")
}get ,show:function(a){a.setAttributeNS(null,"display","")
}get ,setAttributesByStyle:function(b,a){if(a.style&&a.style==ORYX.CONFIG.SELECTION_HIGHLIGHT_STYLE_RECTANGLE){var d=a.elements[0].absoluteBounds();
var c=a.strokewidth?a.strokewidth:ORYX.CONFIG.BORDER_OFFSET;
b.setAttributeNS(null,"d",this.getPathRectangle(d.a,d.b,c));
b.setAttributeNS(null,"stroke",a.color?a.color:ORYX.CONFIG.SELECTION_HIGHLIGHT_COLOR);
b.setAttributeNS(null,"stroke-opacity",a.opacity?a.opacity:0.2);
b.setAttributeNS(null,"stroke-width",c)
}else{if(a.elements.length==1&&a.elements[0] instanceof ORYX.Core.Edge&&a.highlightId!="selection"){b.setAttributeNS(null,"d",this.getPathEdge(a.elements[0].dockers));
b.setAttributeNS(null,"stroke",a.color?a.color:ORYX.CONFIG.SELECTION_HIGHLIGHT_COLOR);
b.setAttributeNS(null,"stroke-opacity",a.opacity?a.opacity:0.2);
b.setAttributeNS(null,"stroke-width",ORYX.CONFIG.OFFSET_EDGE_BOUNDS)
}else{b.setAttributeNS(null,"d",this.getPathByElements(a.elements));
b.setAttributeNS(null,"stroke",a.color?a.color:ORYX.CONFIG.SELECTION_HIGHLIGHT_COLOR);
b.setAttributeNS(null,"stroke-opacity",a.opacity?a.opacity:1);
b.setAttributeNS(null,"stroke-width",a.strokewidth?a.strokewidth:2)
}}}get ,getPathByElements:function(a){if(!a||a.length<=0){return undefined
}var c=ORYX.CONFIG.SELECTED_AREA_PADDING;
var b="";
a.each((function(f){if(!f){return
}var g=f.absoluteBounds();
g.widen(c);
var e=g.upperLeft();
var d=g.lowerRight();
b=b+this.getPath(e,d)
}get ).bind(this));
return b
}get ,getPath:function(d,c){return this.getPathCorners(d,c)
}get ,getPathCorners:function(d,c){var e=ORYX.CONFIG.SELECTION_HIGHLIGHT_SIZE;
var f="";
f=f+"M"+d.x+" "+(d.y+e)+" l0 -"+e+" l"+e+" 0 ";
f=f+"M"+d.x+" "+(c.y-e)+" l0 "+e+" l"+e+" 0 ";
f=f+"M"+c.x+" "+(c.y-e)+" l0 "+e+" l-"+e+" 0 ";
f=f+"M"+c.x+" "+(d.y+e)+" l0 -"+e+" l-"+e+" 0 ";
return f
}get ,getPathRectangle:function(d,c,h){var e=ORYX.CONFIG.SELECTION_HIGHLIGHT_SIZE;
var f="";
var g=h/2;
f=f+"M"+(d.x+g)+" "+(d.y);
f=f+" L"+(d.x+g)+" "+(c.y-g);
f=f+" L"+(c.x-g)+" "+(c.y-g);
f=f+" L"+(c.x-g)+" "+(d.y+g);
f=f+" L"+(d.x+g)+" "+(d.y+g);
return f
}get ,getPathEdge:function(a){var b=a.length;
var c="M"+a[0].bounds.center().x+" "+a[0].bounds.center().y;
for(i=1;
i<b;
i++){var d=a[i].bounds.center();
c=c+" L"+d.x+" "+d.y
}return c
}get });
ORYX.Plugins.HighlightingSelectedShapes=Clazz.extend({construct:function(a){this.facade=a;
this.opacityFull=0.9;
this.opacityLow=0.4
}get ,onSelectionChanged:function(a){if(a.elements&&a.elements.length>1){this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_HIGHLIGHT_SHOW,highlightId:"selection",elements:a.elements.without(a.subSelection),color:ORYX.CONFIG.SELECTION_HIGHLIGHT_COLOR,opacity:!a.subSelection?this.opacityFull:this.opacityLow});
if(a.subSelection){this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_HIGHLIGHT_SHOW,highlightId:"subselection",elements:[a.subSelection],color:ORYX.CONFIG.SELECTION_HIGHLIGHT_COLOR,opacity:this.opacityFull})
}else{this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_HIGHLIGHT_HIDE,highlightId:"subselection"})
}}else{this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_HIGHLIGHT_HIDE,highlightId:"selection"});
this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_HIGHLIGHT_HIDE,highlightId:"subselection"})
}}get });
if(!ORYX.Plugins){ORYX.Plugins=new Object()
}ORYX.Plugins.Overlay=Clazz.extend({facade:undefined,styleNode:undefined,construct:function(a){this.facade=a;
this.changes=[];
this.facade.registerOnEvent(ORYX.CONFIG.EVENT_OVERLAY_SHOW,this.show.bind(this));
this.facade.registerOnEvent(ORYX.CONFIG.EVENT_OVERLAY_HIDE,this.hide.bind(this));
this.styleNode=document.createElement("style");
this.styleNode.setAttributeNS(null,"type","text/css");
document.getElementsByTagName("head")[0].appendChild(this.styleNode)
}get ,show:function(a){if(!a||!a.shapes||!a.shapes instanceof Array||!a.id||!a.id instanceof String||a.id.length==0){return
}if(a.attributes){a.shapes.each(function(d){if(!d instanceof ORYX.Core.Shape){return
}this.setAttributes(d.node,a.attributes)
}get .bind(this))
}var c=true;
try{c=a.node&&a.node instanceof SVGElement
}catch(b){}if(a.node&&c){a._temps=[];
a.shapes.each(function(h,g){if(!h instanceof ORYX.Core.Shape){return
}var f={};
f.svg=a.dontCloneNode?a.node:a.node.cloneNode(true);
h.node.firstChild.appendChild(f.svg);
if(h instanceof ORYX.Core.Edge&&!a.nodePosition){a.nodePosition="START"
}if(a.nodePosition){var e=h.bounds;
var k=a.nodePosition.toUpperCase();
if(h instanceof ORYX.Core.Node&&k=="START"){k="NW"
}else{if(h instanceof ORYX.Core.Node&&k=="END"){k="SE"
}else{if(h instanceof ORYX.Core.Edge&&k=="START"){e=h.getDockers().first().bounds
}else{if(h instanceof ORYX.Core.Edge&&k=="END"){e=h.getDockers().last().bounds
}}}}f.callback=function(){var l=0;
var m=0;
if(k=="NW"){}else{if(k=="N"){l=e.width()/2
}else{if(k=="NE"){l=e.width()
}else{if(k=="E"){l=e.width();
m=e.height()/2
}else{if(k=="SE"){l=e.width();
m=e.height()
}else{if(k=="S"){l=e.width()/2;
m=e.height()
}else{if(k=="SW"){m=e.height()
}else{if(k=="W"){m=e.height()/2
}else{if(k=="START"||k=="END"){l=e.width()/2;
m=e.height()/2
}else{return
}}}}}}}}}if(h instanceof ORYX.Core.Edge){l+=e.upperLeft().x;
m+=e.upperLeft().y
}f.svg.setAttributeNS(null,"transform","translate("+l+", "+m+")")
}get .bind(this);
f.element=h;
f.callback();
e.registerCallback(f.callback)
}if(a.ghostPoint){var d={x:0,y:0};
d=a.ghostPoint;
f.callback=function(){var l=0;
var m=0;
l=d.x-7;
m=d.y-7;
f.svg.setAttributeNS(null,"transform","translate("+l+", "+m+")")
}get .bind(this);
f.element=h;
f.callback();
e.registerCallback(f.callback)
}if(a.labelPoint){var d={x:0,y:0};
d=a.labelPoint;
f.callback=function(){var l=0;
var m=0;
l=d.x;
m=d.y;
f.svg.setAttributeNS(null,"transform","translate("+l+", "+m+")")
}get .bind(this);
f.element=h;
f.callback();
e.registerCallback(f.callback)
}a._temps.push(f)
}get .bind(this))
}if(!this.changes[a.id]){this.changes[a.id]=[]
}this.changes[a.id].push(a)
}get ,hide:function(a){if(!a||!a.id||!a.id instanceof String||a.id.length==0||!this.changes[a.id]){return
}this.changes[a.id].each(function(b){b.shapes.each(function(d,c){if(!d instanceof ORYX.Core.Shape){return
}this.deleteAttributes(d.node)
}get .bind(this));
if(b._temps){b._temps.each(function(c){if(c.svg&&c.svg.parentNode){c.svg.parentNode.removeChild(c.svg)
}if(c.callback&&c.element){c.element.bounds.unregisterCallback(c.callback)
}}get .bind(this))
}}get .bind(this));
this.changes[a.id]=null
}get ,setAttributes:function(c,d){var h=this.getAllChilds(c.firstChild.firstChild);
var a=[];
h.each(function(m){a.push($A(m.attributes).findAll(function(n){return n.nodeValue.startsWith("url(#")
}get ))
}get );
a=a.flatten().compact();
a=a.collect(function(m){return m.nodeValue
}get ).uniq();
a=a.collect(function(m){return m.slice(5,m.length-1)
}get );
a.unshift(c.id+" .me");
var g=$H(d);
var e=g.toJSON().gsub(",",";").gsub('"',"");
var k=d.stroke?e.slice(0,e.length-1)+"; fill:"+d.stroke+";}":e;
var f;
if(d.fill){var b=Object.clone(d);
b.fill="black";
f=$H(b).toJSON().gsub(",",";").gsub('"',"")
}csstags=a.collect(function(n,m){return"#"+n+" * "+(!m?e:k)+""+(f?" #"+n+" text * "+f:"")
}get );
var l=csstags.join(" ")+"
";
this.styleNode.appendChild(document.createTextNode(l))
}get ,deleteAttributes:function(b){var a=$A(this.styleNode.childNodes).findAll(function(c){return c.textContent.include("#"+b.id)
}get );
a.each(function(c){c.parentNode.removeChild(c)
}get )
}get ,getAllChilds:function(a){var b=$A(a.childNodes);
$A(a.childNodes).each(function(c){b.push(this.getAllChilds(c))
}get .bind(this));
return b.flatten()
}get });
if(!ORYX.Plugins){ORYX.Plugins=new Object()
}ORYX.Plugins.Edit=Clazz.extend({construct:function(a){this.facade=a;
this.clipboard=new ORYX.Plugins.Edit.ClipBoard(a);
this.facade.registerOnEvent(ORYX.CONFIG.EVENT_KEYDOWN,this.keyHandler.bind(this));
this.facade.offer({name:ORYX.I18N.Edit.cut,description:ORYX.I18N.Edit.cutDesc,icon:ORYX.PATH+"images/cut.png",keyCodes:[{metaKeys:[ORYX.CONFIG.META_KEY_META_CTRL],keyCode:88,keyAction:ORYX.CONFIG.KEY_ACTION_DOWN}],functionality:this.callEdit.bind(this,this.editCut),group:ORYX.I18N.Edit.group,index:1,minShape:1});
this.facade.offer({name:ORYX.I18N.Edit.copy,description:ORYX.I18N.Edit.copyDesc,icon:ORYX.PATH+"images/page_copy.png",keyCodes:[{metaKeys:[ORYX.CONFIG.META_KEY_META_CTRL],keyCode:67,keyAction:ORYX.CONFIG.KEY_ACTION_DOWN}],functionality:this.callEdit.bind(this,this.editCopy,[true,false]),group:ORYX.I18N.Edit.group,index:2,minShape:1});
this.facade.offer({name:ORYX.I18N.Edit.paste,description:ORYX.I18N.Edit.pasteDesc,icon:ORYX.PATH+"images/page_paste.png",keyCodes:[{metaKeys:[ORYX.CONFIG.META_KEY_META_CTRL],keyCode:86,keyAction:ORYX.CONFIG.KEY_ACTION_DOWN}],functionality:this.callEdit.bind(this,this.editPaste),isEnabled:this.clipboard.isOccupied.bind(this.clipboard),group:ORYX.I18N.Edit.group,index:3,minShape:0,maxShape:0});
this.facade.offer({name:ORYX.I18N.Edit.del,description:ORYX.I18N.Edit.delDesc,icon:ORYX.PATH+"images/cross.png",keyCodes:[{metaKeys:[ORYX.CONFIG.META_KEY_META_CTRL],keyCode:8,keyAction:ORYX.CONFIG.KEY_ACTION_DOWN},{keyCode:46,keyAction:ORYX.CONFIG.KEY_ACTION_DOWN}],functionality:this.callEdit.bind(this,this.editDelete),group:ORYX.I18N.Edit.group,index:4,minShape:1});
this.facade.offer({name:ORYX.I18N.Edit.copy,description:ORYX.I18N.Edit.copyDesc,icon:ORYX.PATH+"images/copy_properties.png",keyCodes:[{metaKeys:[ORYX.CONFIG.META_KEY_META_CTRL],keyCode:67,keyAction:ORYX.CONFIG.KEY_ACTION_DOWN}],functionality:this.callEdit.bind(this,this.copySelectedShapeProperties),group:ORYX.I18N.Edit.group,index:5,minShape:1});
this.facade.offer({name:ORYX.I18N.Edit.paste,description:ORYX.I18N.Edit.pasteDesc,icon:ORYX.PATH+"images/paste_properties.png",keyCodes:[{metaKeys:[ORYX.CONFIG.META_KEY_META_CTRL],keyCode:86,keyAction:ORYX.CONFIG.KEY_ACTION_DOWN}],functionality:this.callEdit.bind(this,this.pasteCopiedProperties),group:ORYX.I18N.Edit.group,index:6,minShape:1,})
}get ,copySelectedShapeProperties:function(){ORYX.Editor.copyShapeProperties()
}get ,pasteCopiedProperties:function(){ORYX.Editor.pasteShapeProperties()
}get ,callEdit:function(b,a){window.setTimeout(function(){b.apply(this,(a instanceof Array?a:[]))
}get .bind(this),1)
}get ,handleMouseDown:function(a){if(this._controlPressed){this._controlPressed=false;
this.editCopy();
this.editPaste();
a.forceExecution=true;
this.facade.raiseEvent(a,this.clipboard.shapesAsJson())
}}get ,keyHandler:function(b){ORYX.Log.debug("edit.js handles a keyEvent.");
if(!b){b=window.event
}var c=b.which||b.keyCode;
var a=b.ctrlKey;
if((c==ORYX.CONFIG.KEY_CODE_DELETE)||((c==ORYX.CONFIG.KEY_CODE_BACKSPACE)&&(b.metaKey||b.appleMetaKey))){ORYX.Log.debug("edit.js deletes the shape.");
this.editDelete();
return
}if(!a){return
}switch(c){case ORYX.CONFIG.KEY_CODE_X:this.editCut();
break;
case ORYX.CONFIG.KEY_CODE_C:this.editCopy();
break;
case ORYX.CONFIG.KEY_CODE_V:this.editPaste();
break
}}get ,getAllShapesToConsider:function(b){var a=[];
var c=[];
b.each(function(e){isChildShapeOfAnother=b.any(function(g){return g.hasChildShape(e)
}get );
if(isChildShapeOfAnother){return
}a.push(e);
if(e instanceof ORYX.Core.Node){var f=e.getOutgoingNodes();
f=f.findAll(function(g){return !b.include(g)
}get );
a=a.concat(f)
}c=c.concat(e.getChildShapes(true))
}get .bind(this));
var d=this.facade.getCanvas().getChildEdges().select(function(e){if(a.include(e)){return false
}if(e.getAllDockedShapes().size()===0){return false
}return e.getAllDockedShapes().all(function(f){return f instanceof ORYX.Core.Edge||c.include(f)
}get )
}get );
a=a.concat(d);
return a
}get ,editCut:function(){try{this.editCopy(false,true);
this.editDelete(true)
}catch(a){ORYX.Log.error(a)
}return false
}get ,editCopy:function(c,a){var b=this.facade.getSelection();
if(b.length==0){return
}this.clipboard.refresh(b,this.getAllShapesToConsider(b),this.facade.getCanvas().getStencil().stencilSet().namespace(),a);
if(c){this.facade.updateSelection()
}}get ,editPaste:function(){var b={childShapes:this.clipboard.shapesAsJson(),stencilset:{namespace:this.clipboard.SSnamespace}};
Ext.apply(b,ORYX.Core.AbstractShape.JSONHelper);
var a=b.getChildShapes(true).pluck("resourceId");
var c={};
b.eachChild(function(d,e){d.outgoing=d.outgoing.select(function(f){return a.include(f.resourceId)
}get );
d.outgoing.each(function(f){if(!c[f.resourceId]){c[f.resourceId]=[]
}c[f.resourceId].push(d)
}get );
return d
}get .bind(this),true,true);
b.eachChild(function(d,e){if(d.target&&!(a.include(d.target.resourceId))){d.target=undefined;
d.targetRemoved=true
}if(d.dockers&&d.dockers.length>=1&&d.dockers[0].getDocker&&((d.dockers[0].getDocker().getDockedShape()&&!a.include(d.dockers[0].getDocker().getDockedShape().resourceId))||!d.getShape().dockers[0].getDockedShape()&&!c[d.resourceId])){d.sourceRemoved=true
}return d
}get .bind(this),true,true);
b.eachChild(function(d,e){if(this.clipboard.useOffset){d.bounds={lowerRight:{x:d.bounds.lowerRight.x+ORYX.CONFIG.COPY_MOVE_OFFSET,y:d.bounds.lowerRight.y+ORYX.CONFIG.COPY_MOVE_OFFSET},upperLeft:{x:d.bounds.upperLeft.x+ORYX.CONFIG.COPY_MOVE_OFFSET,y:d.bounds.upperLeft.y+ORYX.CONFIG.COPY_MOVE_OFFSET}}
}if(d.dockers){d.dockers=d.dockers.map(function(g,f){if((d.targetRemoved===true&&f==d.dockers.length-1&&g.getDocker)||(d.sourceRemoved===true&&f==0&&g.getDocker)){g=g.getDocker().bounds.center()
}if((f==0&&g.getDocker instanceof Function&&d.sourceRemoved!==true&&(g.getDocker().getDockedShape()||((c[d.resourceId]||[]).length>0&&(!(d.getShape() instanceof ORYX.Core.Node)||c[d.resourceId][0].getShape() instanceof ORYX.Core.Node))))||(f==d.dockers.length-1&&g.getDocker instanceof Function&&d.targetRemoved!==true&&(g.getDocker().getDockedShape()||d.target))){return{x:g.x,y:g.y,getDocker:g.getDocker}
}else{if(this.clipboard.useOffset){return{x:g.x+ORYX.CONFIG.COPY_MOVE_OFFSET,y:g.y+ORYX.CONFIG.COPY_MOVE_OFFSET,getDocker:g.getDocker}
}else{return{x:g.x,y:g.y,getDocker:g.getDocker}
}}}get .bind(this))
}else{if(d.getShape() instanceof ORYX.Core.Node&&d.dockers&&d.dockers.length>0&&(!d.dockers.first().getDocker||d.sourceRemoved===true||!(d.dockers.first().getDocker().getDockedShape()||c[d.resourceId]))){d.dockers=d.dockers.map(function(g,f){if((d.sourceRemoved===true&&f==0&&g.getDocker)){g=g.getDocker().bounds.center()
}if(this.clipboard.useOffset){return{x:g.x+ORYX.CONFIG.COPY_MOVE_OFFSET,y:g.y+ORYX.CONFIG.COPY_MOVE_OFFSET,getDocker:g.getDocker}
}else{return{x:g.x,y:g.y,getDocker:g.getDocker}
}}get .bind(this))
}}return d
}get .bind(this),false,true);
this.clipboard.useOffset=true;
this.facade.importJSON(b)
}get ,editDelete:function(){var b=this.facade.getSelection();
var a=this.getAllShapesToConsider(b);
var c=new ORYX.Plugins.Edit.DeleteCommand(a,this.facade);
this.facade.executeCommands([c])
}get });
ORYX.Plugins.Edit.ClipBoard=Clazz.extend({construct:function(){this._shapesAsJson=[];
this.selection=[];
this.SSnamespace="";
this.useOffset=true
}get ,isOccupied:function(){return this.shapesAsJson().length>0
}get ,refresh:function(d,b,c,a){this.selection=d;
this.SSnamespace=c;
this.outgoings={};
this.parents={};
this.targets={};
this.useOffset=a!==true;
this._shapesAsJson=b.map(function(e){var f=e.toJSON();
f.parent={resourceId:e.getParentShape().resourceId};
f.parentIndex=e.getParentShape().getChildShapes().indexOf(e);
return f
}get )
}get ,shapesAsJson:function(){return this._shapesAsJson
}get });
ORYX.Plugins.Edit.DeleteCommand=ORYX.Core.Command.extend({construct:function(a,b){try{this.shapesAsJson=a;
this.facade=b;
ORYX.Log.info("this.shapesAsJson",this.shapesAsJson);
this.dockers=this.shapesAsJson.map(function(f){var g=f.getIncomingShapes().map(function(h){return h.getDockers().last()
}get );
var e=f.getOutgoingShapes().map(function(h){return h.getDockers().first()
}get );
var d=f.getDockers().concat(g,e).compact().map(function(h){return{object:h,referencePoint:h.referencePoint,dockedShape:h.getDockedShape()}
}get );
return d
}get ).flatten()
}catch(c){ORYX.Log.error(c)
}}get ,execute:function(){this.shapesAsJson.each(function(a){this.facade.deleteShape(a)
}get .bind(this));
this.facade.setSelection([]);
this.facade.getCanvas().update();
this.facade.updateSelection()
}get ,rollback:function(){this.shapesAsJson.each(function(a){var b=("undefined"!=typeof(a.parent)?this.facade.getCanvas().getChildShapeByResourceId(a.parent.resourceId):this.facade.getCanvas());
b.add(a,a.parentIndex);
b.add(a,a.parentIndex)
}get .bind(this));
this.dockers.each(function(a){a.object.setDockedShape(a.dockedShape);
a.object.setReferencePoint(a.referencePoint)
}get .bind(this));
this.facade.setSelection(this.selectedShapes);
this.facade.getCanvas().update();
this.facade.updateSelection()
}get });
if(!ORYX.Plugins){ORYX.Plugins=new Object()
}ORYX.Plugins.KeysMove=ORYX.Plugins.AbstractPlugin.extend({facade:undefined,construct:function(a){this.facade=a;
this.copyElements=[];
this.facade.offer({keyCodes:[{metaKeys:[ORYX.CONFIG.META_KEY_META_CTRL],keyCode:65,keyAction:ORYX.CONFIG.KEY_ACTION_DOWN}],functionality:this.selectAll.bind(this)});
this.facade.offer({keyCodes:[{metaKeys:[ORYX.CONFIG.META_KEY_META_CTRL],keyCode:ORYX.CONFIG.KEY_CODE_LEFT,keyAction:ORYX.CONFIG.KEY_ACTION_DOWN}],functionality:this.move.bind(this,ORYX.CONFIG.KEY_CODE_LEFT,false)});
this.facade.offer({keyCodes:[{keyCode:ORYX.CONFIG.KEY_CODE_LEFT,keyAction:ORYX.CONFIG.KEY_ACTION_DOWN}],functionality:this.move.bind(this,ORYX.CONFIG.KEY_CODE_LEFT,true)});
this.facade.offer({keyCodes:[{metaKeys:[ORYX.CONFIG.META_KEY_META_CTRL],keyCode:ORYX.CONFIG.KEY_CODE_RIGHT,keyAction:ORYX.CONFIG.KEY_ACTION_DOWN}],functionality:this.move.bind(this,ORYX.CONFIG.KEY_CODE_RIGHT,false)});
this.facade.offer({keyCodes:[{keyCode:ORYX.CONFIG.KEY_CODE_RIGHT,keyAction:ORYX.CONFIG.KEY_ACTION_DOWN}],functionality:this.move.bind(this,ORYX.CONFIG.KEY_CODE_RIGHT,true)});
this.facade.offer({keyCodes:[{metaKeys:[ORYX.CONFIG.META_KEY_META_CTRL],keyCode:ORYX.CONFIG.KEY_CODE_UP,keyAction:ORYX.CONFIG.KEY_ACTION_DOWN}],functionality:this.move.bind(this,ORYX.CONFIG.KEY_CODE_UP,false)});
this.facade.offer({keyCodes:[{keyCode:ORYX.CONFIG.KEY_CODE_UP,keyAction:ORYX.CONFIG.KEY_ACTION_DOWN}],functionality:this.move.bind(this,ORYX.CONFIG.KEY_CODE_UP,true)});
this.facade.offer({keyCodes:[{metaKeys:[ORYX.CONFIG.META_KEY_META_CTRL],keyCode:ORYX.CONFIG.KEY_CODE_DOWN,keyAction:ORYX.CONFIG.KEY_ACTION_DOWN}],functionality:this.move.bind(this,ORYX.CONFIG.KEY_CODE_DOWN,false)});
this.facade.offer({keyCodes:[{keyCode:ORYX.CONFIG.KEY_CODE_DOWN,keyAction:ORYX.CONFIG.KEY_ACTION_DOWN}],functionality:this.move.bind(this,ORYX.CONFIG.KEY_CODE_DOWN,true)})
}get ,selectAll:function(a){Event.stop(a.event);
this.facade.setSelection(this.facade.getCanvas().getChildShapes(true))
}get ,move:function(n,k,l){Event.stop(l.event);
var b=k?20:5;
var m=this.facade.getSelection();
var g=this.facade.getSelection();
var c={x:0,y:0};
switch(n){case ORYX.CONFIG.KEY_CODE_LEFT:c.x=-1*b;
break;
case ORYX.CONFIG.KEY_CODE_RIGHT:c.x=b;
break;
case ORYX.CONFIG.KEY_CODE_UP:c.y=-1*b;
break;
case ORYX.CONFIG.KEY_CODE_DOWN:c.y=b;
break
}m=m.findAll(function(e){if(e instanceof ORYX.Core.Node&&e.dockers.length==1&&m.include(e.dockers.first().getDockedShape())){return false
}var o=e.parent;
do{if(m.include(o)){return false
}}while(o=o.parent);
return true
}get );
var f=true;
var h=m.all(function(e){if(e instanceof ORYX.Core.Edge){if(e.isDocked()){f=false
}return true
}return false
}get );
if(h&&!f){return
}m=m.map(function(o){if(o instanceof ORYX.Core.Node){return o
}else{if(o instanceof ORYX.Core.Edge){var e=o.dockers;
if(m.include(o.dockers.first().getDockedShape())){e=e.without(o.dockers.first())
}if(m.include(o.dockers.last().getDockedShape())){e=e.without(o.dockers.last())
}return e
}else{return null
}}}get ).flatten().compact();
if(m.size()>0){var a=[this.facade.getCanvas().bounds.lowerRight().x,this.facade.getCanvas().bounds.lowerRight().y,0,0];
m.each(function(e){a[0]=Math.min(a[0],e.bounds.upperLeft().x);
a[1]=Math.min(a[1],e.bounds.upperLeft().y);
a[2]=Math.max(a[2],e.bounds.lowerRight().x);
a[3]=Math.max(a[3],e.bounds.lowerRight().y)
}get );
if(a[0]+c.x<0){c.x=-a[0]
}if(a[1]+c.y<0){c.y=-a[1]
}if(a[2]+c.x>this.facade.getCanvas().bounds.lowerRight().x){c.x=this.facade.getCanvas().bounds.lowerRight().x-a[2]
}if(a[3]+c.y>this.facade.getCanvas().bounds.lowerRight().y){c.y=this.facade.getCanvas().bounds.lowerRight().y-a[3]
}if(c.x!=0||c.y!=0){var d=[new ORYX.Core.Command.Move(m,c,null,g,this)];
this.facade.executeCommands(d)
}}}get ,getUndockedCommant:function(b){var a=ORYX.Core.Command.extend({construct:function(c){this.dockers=c.collect(function(d){return d instanceof ORYX.Core.Controls.Docker?{docker:d,dockedShape:d.getDockedShape(),refPoint:d.referencePoint}:undefined
}get ).compact()
}get ,execute:function(){this.dockers.each(function(c){c.docker.setDockedShape(undefined)
}get )
}get ,rollback:function(){this.dockers.each(function(c){c.docker.setDockedShape(c.dockedShape);
c.docker.setReferencePoint(c.refPoint)
}get )
}get });
command=new a(b);
command.execute();
return command
}get ,});
if(!ORYX.Plugins){ORYX.Plugins=new Object()
}ORYX.Plugins.RowLayouting={construct:function(a){this.facade=a;
this.currentShapes=[];
this.toMoveShapes=[];
this.dragBounds=undefined;
this.offSetPosition={x:0,y:0};
this.evCoord={x:0,y:0};
this.facade.registerOnEvent(ORYX.CONFIG.EVENT_LAYOUT_ROWS,this.handleLayoutRows.bind(this));
this.facade.registerOnEvent(ORYX.CONFIG.EVENT_MOUSEDOWN,this.handleMouseDown.bind(this))
}get ,onSelectionChanged:function(a){var c=a.elements;
if(!c||c.length==0){this.currentShapes=[];
this.toMoveShapes=[];
this.dragBounds=undefined
}else{this.currentShapes=c;
this.toMoveShapes=this.facade.getCanvas().getShapesWithSharedParent(c);
this.toMoveShapes=this.toMoveShapes.findAll(function(d){return d instanceof ORYX.Core.Node&&(d.dockers.length===0||!c.member(d.dockers.first().getDockedShape()))
}get );
var b=undefined;
c.each(function(d){if(!b){b=d.absoluteBounds()
}else{b.include(d.absoluteBounds())
}}get );
this.dragBounds=b
}return
}get ,handleMouseDown:function(c,b){if(!this.dragBounds||!this.toMoveShapes.member(b)){return
}var d=this.facade.eventCoordinates(c);
var a=this.dragBounds.upperLeft();
this.offSetPosition={x:d.x-a.x,y:d.y-a.y};
return
}get ,handleLayoutRows:function(p){var b=p.shape;
var k=this.offSetPosition;
var o=p.marginLeft;
var d=p.marginTop;
var r=p.spacingX;
var q=p.spacingY;
var l=p.shape.getChildShapes(false);
var n=this.toMoveShapes;
n.each(function(u){if(l.include(u)){u.bounds.moveBy(k)
}}get );
if(p.exclude){l=l.filter(function(u){return !p.exclude.some(function(v){return u.getStencil().id()==v
}get )
}get )
}var c=d;
var s=d-q;
if(p.horizontalLayout){l.each(function(v){var u=v.bounds.upperLeft();
v.bounds.moveTo(u.x,c)
}get )
}else{if(p.verticalLayout){l.each(function(v){var u=v.bounds.upperLeft();
v.bounds.moveTo(o,u.y)
}get )
}}l=l.sortBy(function(u){return u.bounds.upperLeft().y
}get );
var e=0;
var f=0;
var m=false;
l.each(function(y){var x=y.bounds.upperLeft();
var u=y.bounds.lowerRight();
var w=x.x;
var v=x.y;
var A=u.x;
var z=u.y;
if(n.include(y)){x.y-=f;
if((x.y>s)||((y==l.first())&&x.y<d)){m=false;
c=s+q;
if(x.y<c){m=true
}}}else{x.y+=e;
x.y-=f;
if(x.y>c){m=false;
c=s+q
}}x.y=c;
u.y=x.y+y.bounds.height();
if(u.y>s){if(m){e+=u.y-s
}else{if(n.include(y)){e+=u.y-s
}}s=u.y
}if((x.x!=w)||(x.y!=v)||(u.x!=A)||(u.y!=z)){if(!n.include(y)){if((v-x.y)>f){f=v-x.y
}}y.bounds.set(x.x,x.y,u.x,u.y)
}}get );
l=l.sortBy(function(u){return u.bounds.upperLeft().y*10000+u.bounds.upperLeft().x
}get );
c=d;
var a=o-r;
var t=a;
var h=0;
l.each(function(y){var x=y.bounds.upperLeft();
var u=y.bounds.lowerRight();
var w=x.x;
var v=x.y;
var A=u.x;
var z=u.y;
if(x.y>c){c=x.y;
a=o-r
}x.x=a+r;
u.x=x.x+y.bounds.width();
a=u.x;
if(a>t){t=a
}if(u.y>h){h=u.y
}if((x.x!=w)||(x.y!=v)||(u.x!=A)||(u.y!=z)){y.bounds.set(x.x,x.y,u.x,u.y)
}}get );
if(p.shape!=this.facade.getCanvas()){var g=p.shape.bounds.upperLeft();
if(t>o){p.shape.bounds.set(g.x,g.y,g.x+t+o,g.y+s+d)
}}else{if(t>this.facade.getCanvas().bounds.width()){this.facade.getCanvas().setSize({width:(t+o),height:this.facade.getCanvas().bounds.height()})
}if(h>this.facade.getCanvas().bounds.height()){this.facade.getCanvas().setSize({width:this.facade.getCanvas().bounds.width(),height:(s+d)})
}}return
}get };
ORYX.Plugins.RowLayouting=Clazz.extend(ORYX.Plugins.RowLayouting);
if(!ORYX.Plugins){ORYX.Plugins=new Object()
}ORYX.Plugins.PluginLoader=Clazz.extend({facade:undefined,mask:undefined,processURI:undefined,construct:function(a){this.facade=a
}get ,showManageDialog:function(){this.mask=new Ext.LoadMask(Ext.getBody(),{msg:ORYX.I18N.Oryx.pleaseWait});
this.mask.show();
var f=[];
var c=[];
var e=this.facade.getStencilSets().keys();
this.facade.getAvailablePlugins().each(function(h){if((!h.requires||!h.requires.namespaces||h.requires.namespaces.any(function(k){return e.indexOf(k)>=0
}get ))&&(!h.notUsesIn||!h.notUsesIn.namespaces||!h.notUsesIn.namespaces.any(function(k){return e.indexOf(k)>=0
}get ))){c.push(h)
}}get );
c.each(function(h){f.push([h.name,h.engaged===true])
}get );
if(f.length==0){return
}var b=new Ext.data.ArrayReader({},[{name:"name"},{name:"engaged"}]);
var g=new Ext.grid.CheckboxSelectionModel({listeners:{beforerowselect:function(m,h,k,l){this.mask=new Ext.LoadMask(Ext.getBody(),{msg:ORYX.I18N.Oryx.pleaseWait});
this.mask.show();
this.facade.activatePluginByName(l.data.name,function(n,o){this.mask.hide();
if(!!n){m.suspendEvents();
m.selectRow(h,true);
m.resumeEvents()
}else{Ext.Msg.show({title:ORYX.I18N.PluginLoad.loadErrorTitle,msg:ORYX.I18N.PluginLoad.loadErrorDesc+ORYX.I18N.PluginLoad[o],buttons:Ext.MessageBox.OK})
}}get .bind(this));
return false
}get .bind(this),rowdeselect:function(l,h,k){l.suspendEvents();
l.selectRow(h,true);
l.resumeEvents()
}get }});
var d=new Ext.grid.GridPanel({store:new Ext.data.Store({reader:b,data:f}),cm:new Ext.grid.ColumnModel([{id:"name",width:390,sortable:true,dataIndex:"name"},g]),sm:g,width:450,height:250,frame:true,hideHeaders:true,iconCls:"icon-grid",listeners:{render:function(){var h=[];
this.grid.getStore().each(function(k){if(k.data.engaged){h.push(k)
}}get .bind(this));
this.suspendEvents();
this.selectRecords(h);
this.resumeEvents()
}get .bind(g)}});
var a=new Ext.Window({title:ORYX.I18N.PluginLoad.WindowTitle,width:"auto",height:"auto",modal:true});
a.add(d);
a.show();
this.mask.hide()
}get });
if(!ORYX.Plugins){ORYX.Plugins=new Object()
}ORYX.Plugins.Save=ORYX.Plugins.AbstractPlugin.extend({facade:undefined,processURI:undefined,construct:function(a){this.facade=a;
this.facade.offer({name:ORYX.I18N.Save.save,functionality:this.save.bind(this,false),group:ORYX.I18N.Save.group,icon:ORYX.PATH+"images/disk.png",description:ORYX.I18N.Save.saveDesc,index:1,minShape:0,maxShape:0});
this.facade.offer({id:"sourceClick",name:"source",functionality:this.showSource.bind(this,false),group:ORYX.I18N.Save.group,icon:ORYX.PATH+"images/source.png",description:"source view",index:3,minShape:0,maxShape:0});
window.onbeforeunload=this.onUnLoad.bind(this);
this.changeDifference=0;
this.facade.registerOnEvent(ORYX.CONFIG.EVENT_UNDO_EXECUTE,function(){this.changeDifference++
}get .bind(this));
this.facade.registerOnEvent(ORYX.CONFIG.EVENT_EXECUTE_COMMANDS,function(){this.changeDifference++
}get .bind(this));
this.facade.registerOnEvent(ORYX.CONFIG.EVENT_UNDO_ROLLBACK,function(){this.changeDifference--
}get .bind(this))
}get ,onUnLoad:function(){if(this.changeDifference!==0){return ORYX.I18N.Save.unsavedData
}}get ,saveSynchronously:function(l){toggleFullscreenProcessEditor("save_process");
this.changeDifference=0;
var h="";
var g=document.URL;
var p=g.split("?");
if(this.processURI){h=this.processURI
}else{h="/bpm/process/processCreate"
}if(l){var o=this.facade.getStencilSets();
var a=o[o.keys()[0]].source().split("stencilsets")[1];
h="/backend/poem"+ORYX.CONFIG.ORYX_NEW_URL+"?stencilset=/stencilsets"+a
}var f=this.facade.getCanvas().getSVGRepresentation(true);
var b=DataManager.serialize(f);
this.serializedDOM=Ext.encode(this.facade.getJSON());
if(!p[1]){if(h.include(ORYX.CONFIG.ORYX_NEW_URL)){var o=this.facade.getStencilSets().values()[0];
var e={title:ORYX.I18N.Save.newProcess,summary:"",type:o.title(),url:h,namespace:o.namespace()};
var d=new Ext.data.Store({autoLoad:true,proxy:new Ext.data.HttpProxy({url:"/process/getAllClassifications"}),reader:new Ext.data.JsonReader({root:"classifications"},[{name:"id"},{name:"name"}])});
var c=new Ext.Panel({labelAlign:"right",title:ORYX.I18N.Save.dialogDesciption,width:450,bodyStyle:"padding:5px 5px 0 0",items:[{xtype:"form",monitorValid:true,id:"processForm",name:"processForm",layout:"form",border:false,items:[{xtype:"textfield",fieldLabel:ORYX.I18N.Save.dialogLabelName+'<span style="color: rgb(255, 0, 0); padding-left: 2px;">* </span>',name:"title",allowBlank:false,labelStyle:"font-weight:bold;",regex:/^[A-Za-z\u4e00-\u9eff\.][A-Za-z\' \'\w\u4e00-\u9eff\.][\-a-zA-Z\_\u4e00-\u9eff\s]{1,100}$/,anchor:"90%"},{xtype:"textarea",grow:true,fieldLabel:ORYX.I18N.Save.dialogLabelDesc+" ",name:"summary",labelStyle:"font-weight:bold;",anchor:"90%"},{xtype:"hidden",grow:true,name:"namespace",value:o.namespace()},{xtype:"combo",store:d,id:"classification_type",name:"classification_type",fieldLabel:'Classification <span style="color: rgb(255, 0, 0); padding-left: 2px;">* </span>',labelStyle:"font-weight:bold;",displayField:"name",valueField:"id",allowBlank:false,typeAhead:true,mode:"local",lazyRender:true,triggerAction:"all",overflow:"auto",emptyText:"Select a Classification...",selectOnFocus:true,anchor:"90%",regex:new RegExp("^[A-Za-z一-黿.][A-Za-z w一-黿.]{1,40}$")}],buttons:[{text:ORYX.I18N.Save.saveBtn,formBind:true,handler:function(){callback()
}get },{text:ORYX.I18N.Save.close,handler:function(){this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_LOADING_DISABLE});
win.destroy()
}get .bind(this)}]}]});
callback=function(u){this.mask=new Ext.LoadMask(Ext.getBody(),{msg:ORYX.I18N.Oryx.pleaseWait});
this.mask.show();
var x=Ext.getCmp("processForm").getForm();
var w=x.findField("title").getValue().strip();
w=w.length==0?e.title:w;
window.document.title=w+" - Oryx";
var r=x.findField("summary").getValue().strip();
r=r.length==0?e.summary:r;
var q=x.findField("classification_type").getRawValue().strip();
var v=x.findField("classification_type").getValue().strip();
if(v.length==0){v=q
}else{if(v.length>0&&q.length>0){var t=q.toLowerCase().replace(/[" "]/g,"_");
if(v==t){q=""
}}}v=q.length==0?v:q;
var s=x.findField("namespace").getValue().strip();
s=s.length==0?e.namespace:s;
win.destroy();
this.sendSaveRequest(h,{data:this.serializedDOM,svg:b,title:w,summary:r,type:s,classification_type:v,designMode:"create"},l,this.mask)
}get .bind(this);
win=new Ext.Window({id:"Propertie_Window",width:"auto",height:"auto",title:l?ORYX.I18N.Save.saveAsTitle:ORYX.I18N.Save.save,modal:true,bodyStyle:"background:#FFFFFF",items:c,});
win.show()
}else{this.mask=new Ext.LoadMask(Ext.getBody(),{msg:ORYX.I18N.Oryx.pleaseWait});
this.mask.show();
this.sendSaveRequest(h,{data:this.serializedDOM,svg:b},this.mask)
}}else{var d=new Ext.data.Store({autoLoad:false,proxy:new Ext.data.HttpProxy({url:"/process/getAllClassifications"}),reader:new Ext.data.JsonReader({root:"classifications"},[{name:"id"},{name:"name"}])});
d.load();
var o=this.facade.getStencilSets().values()[0];
var e={title:ORYX.I18N.Save.newProcess,summary:"",type:o.title(),url:h,namespace:o.namespace()};
var m=decodeURIComponent(p[1].split("&")[2].split("=")[1]);
var k=decodeURIComponent(p[1].split("&")[3].split("=")[1]);
var n=decodeURIComponent(p[1].split("&")[4].split("=")[1]).replace("#","");
var c=new Ext.Panel({labelAlign:"right",title:ORYX.I18N.Save.dialogDesciption,width:450,bodyStyle:"padding:5px 5px 0 0",items:[{xtype:"form",monitorValid:true,id:"processForm",name:"processForm",layout:"form",border:false,items:[{xtype:"textfield",fieldLabel:ORYX.I18N.Save.dialogLabelName+'<span style="color: rgb(255, 0, 0); padding-left: 2px;">* </span>',name:"title",allowBlank:false,disabled:true,value:m,labelStyle:"font-weight:bold;",regex:/^[A-Za-z\u4e00-\u9eff\.][A-Za-z\' \'\w\u4e00-\u9eff\.][\-a-zA-Z\_\u4e00-\u9eff\s]{1,100}$/,anchor:"90%"},{xtype:"textarea",grow:true,fieldLabel:ORYX.I18N.Save.dialogLabelDesc+" ",name:"summary",value:k,labelStyle:"font-weight:bold;",anchor:"90%"},{xtype:"hidden",grow:true,name:"namespace",value:o.namespace()},{xtype:"combo",store:d,id:"classification_type",name:"classification_type",fieldLabel:'Classification <span style="color: rgb(255, 0, 0); padding-left: 2px;">* </span>',labelStyle:"font-weight:bold;",displayField:"name",valueField:"id",allowBlank:false,typeAhead:true,value:n,mode:"local",lazyRender:true,triggerAction:"all",overflow:"auto",emptyText:"Select a Classification...",selectOnFocus:true,anchor:"90%",regex:new RegExp("^[A-Za-z一-黿.][A-Za-z w一-黿.]{1,40}$")}],buttons:[{text:ORYX.I18N.Save.saveBtn,formBind:true,handler:function(){callback()
}get },{text:ORYX.I18N.Save.close,handler:function(){this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_LOADING_DISABLE});
win.destroy()
}get .bind(this)}]}]});
callback=function(u){this.mask=new Ext.LoadMask(Ext.getBody(),{msg:ORYX.I18N.Oryx.pleaseWait});
this.mask.show();
var w=Ext.getCmp("processForm").getForm();
window.document.title=m+" - Oryx";
var r=w.findField("summary").getValue().strip();
r=r.length==0?e.summary:r;
var q=w.findField("classification_type").getRawValue().strip();
var v=w.findField("classification_type").getValue().strip();
if(v.length==0){v=q
}else{if(v.length>0&&q.length>0){var t=q.toLowerCase().replace(/[" "]/g,"_");
if(v==t){q=""
}}}v=q.length==0?v:q;
var s=e.namespace;
win.destroy();
this.sendSaveRequest(h,{data:this.serializedDOM,svg:b,title:m,summary:r,type:s,classification_type:v,designMode:"edit"},l,this.mask)
}get .bind(this);
win=new Ext.Window({id:"Propertie_Window",width:"auto",height:"auto",title:l?ORYX.I18N.Save.saveAsTitle:ORYX.I18N.Save.save,modal:true,bodyStyle:"background:#FFFFFF",items:c,});
win.show()
}}get ,sendSaveRequest:function(a,e,b,c){var d=e.classification_type;
new Ajax.Request(a,{method:"POST",asynchronous:false,parameters:e,onSuccess:(function(f){if(f.responseText=="success"){this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_MODEL_SAVED});
this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_LOADING_STATUS,text:ORYX.CONFIG.EVENT_MODEL_SAVED});
c.hide();
Ext.Msg.show({title:"Success",msg:ORYX.I18N.PropertyWindow.processSaved,buttons:Ext.Msg.OK,animEl:"elId",fn:function(g){if(g==="ok"){window.top.showProcessList()
}}get .bind(this),icon:Ext.Msg.INFO})
}else{c.hide();
this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_LOADING_STATUS,text:"Error"});
if(f.responseText!=undefined&&f.responseText!="null"&&f.responseText!=null){Ext.Msg.show({title:"Error",msg:f.responseText,buttons:Ext.Msg.OK,animEl:"elId",icon:Ext.Msg.ERROR})
}}}get ).bind(this),onFailure:(function(f){this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_LOADING_DISABLE});
Ext.Msg.alert(ORYX.I18N.Oryx.title,ORYX.I18N.Save.failed);
ORYX.Log.warn("Saving failed: "+f.responseText)
}get ).bind(this),on403:(function(f){this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_LOADING_DISABLE});
Ext.Msg.alert(ORYX.I18N.Oryx.title,ORYX.I18N.Save.noRights);
ORYX.Log.warn("Saving failed: "+f.responseText)
}get ).bind(this)})
}get ,showSource:function(d,f){var c="/bpm/process/showProcessXML";
Ext.Ajax.on("beforerequest",function(h,k){Ext.getBody().mask("Loading...")
}get );
var g=this.facade.getCanvas().getSVGRepresentation(true);
var e=DataManager.serialize(g);
this.serializedDOM=Ext.encode(this.facade.getJSON());
var b=this.facade.getStencilSets().values()[0];
var a={title:ORYX.I18N.Save.newProcess,summary:"",type:b.title(),url:c,namespace:b.namespace()};
title=a.title;
window.document.title=title+" - Oryx";
summary=a.summary;
namespace=a.namespace;
this.sourceSaveRequest(c,{data:this.serializedDOM,svg:e,title:title,summary:summary,type:namespace},d);
Ext.Ajax.on("requestcomplete",function(h,k){Ext.getBody().unmask()
}get );
Ext.Ajax.on("requestexception",function(h,k){Ext.getBody().unmask()
}get )
}get ,sourceSaveRequest:function(a,c,b){Ext.Ajax.request({url:a,params:c,method:"POST",asynchronous:false,failure:function(d,e){}get ,success:function(d,e){var k=d.responseText;
var h={};
h=JSON.parse(k.toString());
var g=h.xml;
while(g.indexOf("<")!==-1){g=g.replace("<","&lt;")
}while(g.indexOf(">")!==-1){g=g.replace(">","&gt;")
}document.getElementById("view-source").innerHTML="<pre id='xmlsource' name='xmlsource' >"+g+"</pre>";
document.getElementById("oryx-canvas123").style.display="none";
document.getElementById("view-source").style.display="block";
var f=window.innerHeight||document.documentElement.clientHeight||document.body.clientHeight;
var l=(f-54)+"px";
document.getElementById("view-source").style.height=l;
document.getElementById("view-source").style.overflow="auto"
}get })
}get ,save:function(a,b){window.setTimeout((function(){this.saveSynchronously(a)
}get ).bind(this),10);
return true
}get });
ORYX.Plugins.File=ORYX.Plugins.AbstractPlugin.extend({facade:undefined,construct:function(a){this.facade=a
}get ,info:function(){var a='<iframe src="'+ORYX.CONFIG.LICENSE_URL+'" type="text/plain" style="border:none;display:block;width:575px;height:460px;"/>

<pre style="display:inline;">Version: </pre><iframe src="'+ORYX.CONFIG.VERSION_URL+'" type="text/plain" style="border:none;overflow:hidden;display:inline;width:40px;height:20px;"/>';
this.infoBox=Ext.Msg.show({title:ORYX.I18N.Oryx.title,msg:a,width:640,maxWidth:640,maxHeight:480,buttons:Ext.MessageBox.OK});
return false
}get ,exportSVG:function(){var c=location.href;
var b=this.facade.getCanvas().getSVGRepresentation(true);
var a=DataManager.serialize(b);
this.openDownloadWindow("oryx.svg",a)
}get ,exportPDF:function(){this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_LOADING_ENABLE,text:ORYX.I18N.File.genPDF});
var c=location.href;
var b=this.facade.getCanvas().getSVGRepresentation(true);
var a=DataManager.serialize(b);
new Ajax.Request(ORYX.CONFIG.PDF_EXPORT_URL,{method:"POST",parameters:{resource:c,data:a,format:"pdf"},onSuccess:(function(d){this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_LOADING_DISABLE});
window.open(d.responseText)
}get ).bind(this),onFailure:(function(){this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_LOADING_DISABLE});
Ext.Msg.alert(ORYX.I18N.Oryx.title,ORYX.I18N.File.genPDFFailed)
}get ).bind(this)})
}get ,print:function(){Ext.Msg.show({title:ORYX.I18N.File.printTitle,msg:ORYX.I18N.File.printMsg,buttons:Ext.Msg.YESNO,icon:Ext.MessageBox.QUESTION,fn:function(c){if(c=="yes"){var e=$H({width:300,height:400,toolbar:"no",status:"no",menubar:"yes",dependent:"yes",resizable:"yes",scrollbars:"yes"});
var f=window.open("","PrintWindow",e.invoke("join","=").join(","));
var b=f.document.getElementsByTagName("head")[0];
var d=document.createElement("style");
d.innerHTML=" body {padding:0px; margin:0px} .svgcontainer { display:none; }";
b.appendChild(d);
f.document.getElementsByTagName("body")[0].appendChild(this.facade.getCanvas().getSVGRepresentation());
var a=f.document.getElementsByTagName("body")[0].getElementsByTagName("svg")[0];
a.setAttributeNS(null,"width",1100);
a.setAttributeNS(null,"height",1400);
a.lastChild.setAttributeNS(null,"transform","scale(0.47, 0.47) rotate(270, 1510, 1470)");
var h=["marker-start","marker-mid","marker-end"];
var g=$A(f.document.getElementsByTagName("path"));
g.each(function(k){h.each(function(l){var m=k.getAttributeNS(null,l);
if(!m){return
}m="url(about:blank#"+m.slice(5);
k.setAttributeNS(null,l,m)
}get )
}get );
f.print();
return true
}}get .bind(this)})
}get });
confirmDefaultTaskName=function(a){if(a=="ok"){win.show()
}else{}}get ;
var changedTaskForm="";
Document.prototype.createAttributeNS=function(b,c){var a=this.createAttribute(b);
a.namespaceURI=c;
return a
}get ;
window.onOryxResourcesLoaded=function(){var e=document.URL;
var b=e.split("?");
if(!b[1]){var a=ORYX.Utils.getParamFromUrl("stencilset")||ORYX.CONFIG.SSET;
new ORYX.Editor({id:"oryx-canvas123",fullscreen:false,stencilset:{url:ORYX.PATH+"/"+a}})
}else{var c="/bpm/process/editProcess";
var d={id:decodeURIComponent(b[1].split("&")[0].split("=")[1]),key:decodeURIComponent(b[1].split("&")[1].split("=")[1])};
Ext.Ajax.request({url:c,params:d,method:"POST",asynchronous:false,failure:function(f,g){Ext.Msg.alert("")
}get ,success:function(l,x){var s=l.responseText;
var p={};
p=JSON.parse(s.toString());
var h=p.jsonRes;
var v=Ext.decode(h);
var w=new Array();
var m=0;
var g="";
var k={};
var u="";
for(var n=0;
n<v.model.childShapes.length;
n++){if(v.model.childShapes[n].stencil!=undefined&&v.model.childShapes[n].stencil.id=="CollapsedSubprocess"){if(v.model.childShapes[n].properties.addsubprocess!=undefined&&v.model.childShapes[n].properties.addsubprocess!=""){var o=processStore.find("id",v.model.childShapes[n].properties.form);
if(o!=-1){}else{Ext.Msg.show({title:"Information",msg:"The subprocess <b>"+v.model.childShapes[n].properties.name+"</b> element <i> "+v.model.childShapes[n].properties.addsubprocess.split(":")[0]+"</i>  process has been changed as new Version.Please change it to update new version",buttons:Ext.Msg.OKCANCEL})
}}}if(v.model.childShapes[n]!=undefined&&v.model.childShapes[n].properties.form!=undefined){var o=formStore.find("id",v.model.childShapes[n].properties.form);
if(o!=-1){}else{g=v.model.childShapes[n].properties.name;
w[m]=v.model.childShapes[n].properties.form;
u=v.model.childShapes[n].properties.form;
m++
}var q=newFormStore.find("id",v.model.childShapes[n].properties.form);
if(q!=-1){}else{g=v.model.childShapes[n].properties.name;
w[m]=v.model.childShapes[n].properties.form;
u=v.model.childShapes[n].properties.form;
m++
}}k[u]=g
}var r={};
for(var n=0;
n<w.length;
n++){r[w[n]]=w[n]
}var f=[];
for(var t in r){f.push(t)
}if(f.length>0&&isFormChanged(f,k)){Ext.Msg.show({title:"Information",msg:"The Form Version of Task "+changedTaskForm+" has been changed as new Version.Are you sure to change form version in Tasks ?",buttons:Ext.Msg.OKCANCEL,fn:function(z){if(z=="ok"){if(f.length>1){for(var y=0;
y<f.length;
y++){Ext.Ajax.request({waitMsg:"Saving changes...",method:"GET",headers:{"Content-Type":"application/json"},url:"/form/getFormById",params:{formId:f[y].toString()},failure:function(A){Ext.Msg.show({title:"Message",msg:"failure. ",buttons:Ext.Msg.OK,animEl:"elId",icon:Ext.MessageBox.ERROR})
}get ,success:function(C){var B=Ext.util.JSON.decode(C.responseText);
var E=formStore.recordType;
var D;
D=new E({id:B.forms[0].id,formName:B.forms[0].formName,});
formIdMap[B.forms[0].id]=B.forms[0].formName;
var A=formStore.findBy(function(F,G){if(F.get("formName")==B.forms[0].formName){return true
}return false
}get );
if(A==-1){formStore.add(D);
formStore.commitChanges()
}}get })
}}else{Ext.Ajax.request({waitMsg:"Saving changes...",method:"GET",headers:{"Content-Type":"application/json"},url:"/form/getFormById",params:{formId:f.toString()},failure:function(A){Ext.Msg.show({title:"Message",msg:"failure. ",buttons:Ext.Msg.OK,animEl:"elId",icon:Ext.MessageBox.ERROR})
}get ,success:function(C){var B=Ext.util.JSON.decode(C.responseText);
var E=formStore.recordType;
var D;
D=new E({id:B.forms[0].id,formName:B.forms[0].formName,});
formIdMap[f.toString()]=B.forms[0].formName;
var A=formStore.findBy(function(F,G){if(F.get("formName")==B.forms[0].formName){return true
}return false
}get );
if(A==-1){formStore.add(D);
formStore.commitChanges()
}}get })
}}else{if(f.length>1){for(var y=0;
y<f.length;
y++){Ext.Ajax.request({waitMsg:"Saving changes...",method:"GET",headers:{"Content-Type":"application/json"},url:"/form/getLatestFormVersion",params:{formId:f[y].toString()},failure:function(A){Ext.Msg.show({title:"Message",msg:"failure. ",buttons:Ext.Msg.OK,animEl:"elId",icon:Ext.MessageBox.ERROR})
}get ,success:function(C){var B=Ext.util.JSON.decode(C.responseText);
var E=formStore.recordType;
var D=new E({id:B.forms.id,formName:B.forms.formName,});
var A=formStore.findBy(function(F,G){if(F.get("id")==B.forms.id){return true
}return false
}get );
if(A==-1){formStore.add(D);
formStore.commitChanges()
}}get })
}}else{Ext.Ajax.request({waitMsg:"Saving changes...",method:"GET",headers:{"Content-Type":"application/json"},url:"/form/getLatestFormVersion",params:{formId:f.toString()},failure:function(A){Ext.Msg.show({title:"Message",msg:"failure. ",buttons:Ext.Msg.OK,animEl:"elId",icon:Ext.MessageBox.ERROR})
}get ,success:function(C){var B=Ext.util.JSON.decode(C.responseText);
var E=formStore.recordType;
var D=new E({id:B.forms.id,formName:B.forms.formName,});
var A=formStore.findBy(function(F,G){if(F.get("id")==f.toString()){return true
}return false
}get );
if(A==-1){formStore.add(D);
formStore.commitChanges()
}}get })
}}new ORYX.Editor(v)
}get ,animEl:"elId",icon:Ext.MessageBox.INFO})
}else{new ORYX.Editor(v)
}}get })
}}get ;
function isFormChanged(d,f){var h=[];
if(d.length>1){for(var e=0;
e<d.length;
e++){var c=formStore.find("id",d[e].toString());
if(c!=-1){h.push(d[e].toString())
}else{changedTaskForm+=f[d[e].toString()]+","
}var a=newFormStore.find("id",d[e].toString());
if(a!=-1){h.push(d[e].toString())
}else{changedTaskForm+=f[d[e].toString()]+","
}}changedTaskForm=changedTaskForm.slice(0,-1);
var g=changedTaskForm.split(",");
var b="";
for(var e=0;
e<g.length;
e++){if(g[e+1]==g[e]){b+=g[e]+","
}}changedTaskForm=b.slice(0,-1)
}else{var c=formStore.find("id",d.toString());
if(c!=-1){h.push(d.toString())
}var a=newFormStore.find("id",d.toString());
if(a!=-1){h.push(d.toString())
}}if(h.length==d.length){return false
}else{return true
}}get if(!ORYX.Plugins){ORYX.Plugins=new Object()
}ORYX.Plugins.Save=ORYX.Plugins.AbstractPlugin.extend({facade:undefined,processURI:undefined,construct:function(a){this.facade=a;
this.facade.offer({name:ORYX.I18N.Save.save,functionality:this.save.bind(this,false),group:ORYX.I18N.Save.group,icon:ORYX.PATH+"images/disk.png",description:ORYX.I18N.Save.saveDesc,index:1,minShape:0,maxShape:0});
this.facade.offer({id:"sourceClick",name:"source",functionality:this.showSource.bind(this,false),group:ORYX.I18N.Save.group,icon:ORYX.PATH+"images/source.png",description:"source view",index:3,minShape:0,maxShape:0});
window.onbeforeunload=this.onUnLoad.bind(this);
this.changeDifference=0;
this.facade.registerOnEvent(ORYX.CONFIG.EVENT_UNDO_EXECUTE,function(){this.changeDifference++
}get .bind(this));
this.facade.registerOnEvent(ORYX.CONFIG.EVENT_EXECUTE_COMMANDS,function(){this.changeDifference++
}get .bind(this));
this.facade.registerOnEvent(ORYX.CONFIG.EVENT_UNDO_ROLLBACK,function(){this.changeDifference--
}get .bind(this))
}get ,onUnLoad:function(){if(this.changeDifference!==0){return ORYX.I18N.Save.unsavedData
}}get ,saveSynchronously:function(l){toggleFullscreenProcessEditor("save_process");
this.changeDifference=0;
var h="";
var g=document.URL;
var p=g.split("?");
if(this.processURI){h=this.processURI
}else{h="/bpm/process/processCreate"
}if(l){var o=this.facade.getStencilSets();
var a=o[o.keys()[0]].source().split("stencilsets")[1];
h="/backend/poem"+ORYX.CONFIG.ORYX_NEW_URL+"?stencilset=/stencilsets"+a
}var f=this.facade.getCanvas().getSVGRepresentation(true);
var b=DataManager.serialize(f);
this.serializedDOM=Ext.encode(this.facade.getJSON());
if(!p[1]){if(h.include(ORYX.CONFIG.ORYX_NEW_URL)){var o=this.facade.getStencilSets().values()[0];
var e={title:ORYX.I18N.Save.newProcess,summary:"",type:o.title(),url:h,namespace:o.namespace()};
var d=new Ext.data.Store({autoLoad:true,proxy:new Ext.data.HttpProxy({url:"/process/getAllClassifications"}),reader:new Ext.data.JsonReader({root:"classifications"},[{name:"id"},{name:"name"}])});
var c=new Ext.Panel({labelAlign:"right",title:ORYX.I18N.Save.dialogDesciption,width:450,bodyStyle:"padding:5px 5px 0 0",items:[{xtype:"form",monitorValid:true,id:"processForm",name:"processForm",layout:"form",border:false,items:[{xtype:"textfield",fieldLabel:ORYX.I18N.Save.dialogLabelName+'<span style="color: rgb(255, 0, 0); padding-left: 2px;">* </span>',name:"title",allowBlank:false,labelStyle:"font-weight:bold;",regex:/^[A-Za-z\u4e00-\u9eff\.][A-Za-z\' \'\w\u4e00-\u9eff\.][\-a-zA-Z\_\u4e00-\u9eff\s]{1,100}$/,anchor:"90%"},{xtype:"textarea",grow:true,fieldLabel:ORYX.I18N.Save.dialogLabelDesc+" ",name:"summary",labelStyle:"font-weight:bold;",anchor:"90%"},{xtype:"hidden",grow:true,name:"namespace",value:o.namespace()},{xtype:"combo",store:d,id:"classification_type",name:"classification_type",fieldLabel:'Classification <span style="color: rgb(255, 0, 0); padding-left: 2px;">* </span>',labelStyle:"font-weight:bold;",displayField:"name",valueField:"id",allowBlank:false,typeAhead:true,mode:"local",lazyRender:true,triggerAction:"all",overflow:"auto",emptyText:"Select a Classification...",selectOnFocus:true,anchor:"90%",regex:new RegExp("^[A-Za-z一-黿.][A-Za-z w一-黿.]{1,40}$")}],buttons:[{text:ORYX.I18N.Save.saveBtn,formBind:true,handler:function(){callback()
}get },{text:ORYX.I18N.Save.close,handler:function(){this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_LOADING_DISABLE});
win.destroy()
}get .bind(this)}]}]});
callback=function(u){this.mask=new Ext.LoadMask(Ext.getBody(),{msg:ORYX.I18N.Oryx.pleaseWait});
this.mask.show();
var x=Ext.getCmp("processForm").getForm();
var w=x.findField("title").getValue().strip();
w=w.length==0?e.title:w;
window.document.title=w+" - Oryx";
var r=x.findField("summary").getValue().strip();
r=r.length==0?e.summary:r;
var q=x.findField("classification_type").getRawValue().strip();
var v=x.findField("classification_type").getValue().strip();
if(v.length==0){v=q
}else{if(v.length>0&&q.length>0){var t=q.toLowerCase().replace(/[" "]/g,"_");
if(v==t){q=""
}}}v=q.length==0?v:q;
var s=x.findField("namespace").getValue().strip();
s=s.length==0?e.namespace:s;
win.destroy();
this.sendSaveRequest(h,{data:this.serializedDOM,svg:b,title:w,summary:r,type:s,classification_type:v,designMode:"create"},l,this.mask)
}get .bind(this);
win=new Ext.Window({id:"Propertie_Window",width:"auto",height:"auto",title:l?ORYX.I18N.Save.saveAsTitle:ORYX.I18N.Save.save,modal:true,bodyStyle:"background:#FFFFFF",items:c,});
win.show()
}else{this.mask=new Ext.LoadMask(Ext.getBody(),{msg:ORYX.I18N.Oryx.pleaseWait});
this.mask.show();
this.sendSaveRequest(h,{data:this.serializedDOM,svg:b},this.mask)
}}else{var d=new Ext.data.Store({autoLoad:false,proxy:new Ext.data.HttpProxy({url:"/process/getAllClassifications"}),reader:new Ext.data.JsonReader({root:"classifications"},[{name:"id"},{name:"name"}])});
d.load();
var o=this.facade.getStencilSets().values()[0];
var e={title:ORYX.I18N.Save.newProcess,summary:"",type:o.title(),url:h,namespace:o.namespace()};
var m=decodeURIComponent(p[1].split("&")[2].split("=")[1]);
var k=decodeURIComponent(p[1].split("&")[3].split("=")[1]);
var n=decodeURIComponent(p[1].split("&")[4].split("=")[1]).replace("#","");
var c=new Ext.Panel({labelAlign:"right",title:ORYX.I18N.Save.dialogDesciption,width:450,bodyStyle:"padding:5px 5px 0 0",items:[{xtype:"form",monitorValid:true,id:"processForm",name:"processForm",layout:"form",border:false,items:[{xtype:"textfield",fieldLabel:ORYX.I18N.Save.dialogLabelName+'<span style="color: rgb(255, 0, 0); padding-left: 2px;">* </span>',name:"title",allowBlank:false,disabled:true,value:m,labelStyle:"font-weight:bold;",regex:/^[A-Za-z\u4e00-\u9eff\.][A-Za-z\' \'\w\u4e00-\u9eff\.][\-a-zA-Z\_\u4e00-\u9eff\s]{1,100}$/,anchor:"90%"},{xtype:"textarea",grow:true,fieldLabel:ORYX.I18N.Save.dialogLabelDesc+" ",name:"summary",value:k,labelStyle:"font-weight:bold;",anchor:"90%"},{xtype:"hidden",grow:true,name:"namespace",value:o.namespace()},{xtype:"combo",store:d,id:"classification_type",name:"classification_type",fieldLabel:'Classification <span style="color: rgb(255, 0, 0); padding-left: 2px;">* </span>',labelStyle:"font-weight:bold;",displayField:"name",valueField:"id",allowBlank:false,typeAhead:true,value:n,mode:"local",lazyRender:true,triggerAction:"all",overflow:"auto",emptyText:"Select a Classification...",selectOnFocus:true,anchor:"90%",regex:new RegExp("^[A-Za-z一-黿.][A-Za-z w一-黿.]{1,40}$")}],buttons:[{text:ORYX.I18N.Save.saveBtn,formBind:true,handler:function(){callback()
}get },{text:ORYX.I18N.Save.close,handler:function(){this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_LOADING_DISABLE});
win.destroy()
}get .bind(this)}]}]});
callback=function(u){this.mask=new Ext.LoadMask(Ext.getBody(),{msg:ORYX.I18N.Oryx.pleaseWait});
this.mask.show();
var w=Ext.getCmp("processForm").getForm();
window.document.title=m+" - Oryx";
var r=w.findField("summary").getValue().strip();
r=r.length==0?e.summary:r;
var q=w.findField("classification_type").getRawValue().strip();
var v=w.findField("classification_type").getValue().strip();
if(v.length==0){v=q
}else{if(v.length>0&&q.length>0){var t=q.toLowerCase().replace(/[" "]/g,"_");
if(v==t){q=""
}}}v=q.length==0?v:q;
var s=e.namespace;
win.destroy();
this.sendSaveRequest(h,{data:this.serializedDOM,svg:b,title:m,summary:r,type:s,classification_type:v,designMode:"edit"},l,this.mask)
}get .bind(this);
win=new Ext.Window({id:"Propertie_Window",width:"auto",height:"auto",title:l?ORYX.I18N.Save.saveAsTitle:ORYX.I18N.Save.save,modal:true,bodyStyle:"background:#FFFFFF",items:c,});
win.show()
}}get ,sendSaveRequest:function(a,e,b,c){var d=e.classification_type;
new Ajax.Request(a,{method:"POST",asynchronous:false,parameters:e,onSuccess:(function(f){if(f.responseText=="success"){this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_MODEL_SAVED});
this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_LOADING_STATUS,text:ORYX.CONFIG.EVENT_MODEL_SAVED});
c.hide();
Ext.Msg.show({title:"Success",msg:ORYX.I18N.PropertyWindow.processSaved,buttons:Ext.Msg.OK,animEl:"elId",fn:function(g){if(g==="ok"){window.top.showProcessList()
}}get .bind(this),icon:Ext.Msg.INFO})
}else{c.hide();
this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_LOADING_STATUS,text:"Error"});
if(f.responseText!=undefined&&f.responseText!="null"&&f.responseText!=null){Ext.Msg.show({title:"Error",msg:f.responseText,buttons:Ext.Msg.OK,animEl:"elId",icon:Ext.Msg.ERROR})
}}}get ).bind(this),onFailure:(function(f){this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_LOADING_DISABLE});
Ext.Msg.alert(ORYX.I18N.Oryx.title,ORYX.I18N.Save.failed);
ORYX.Log.warn("Saving failed: "+f.responseText)
}get ).bind(this),on403:(function(f){this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_LOADING_DISABLE});
Ext.Msg.alert(ORYX.I18N.Oryx.title,ORYX.I18N.Save.noRights);
ORYX.Log.warn("Saving failed: "+f.responseText)
}get ).bind(this)})
}get ,showSource:function(d,f){var c="/bpm/process/showProcessXML";
Ext.Ajax.on("beforerequest",function(h,k){Ext.getBody().mask("Loading...")
}get );
var g=this.facade.getCanvas().getSVGRepresentation(true);
var e=DataManager.serialize(g);
this.serializedDOM=Ext.encode(this.facade.getJSON());
var b=this.facade.getStencilSets().values()[0];
var a={title:ORYX.I18N.Save.newProcess,summary:"",type:b.title(),url:c,namespace:b.namespace()};
title=a.title;
window.document.title=title+" - Oryx";
summary=a.summary;
namespace=a.namespace;
this.sourceSaveRequest(c,{data:this.serializedDOM,svg:e,title:title,summary:summary,type:namespace},d);
Ext.Ajax.on("requestcomplete",function(h,k){Ext.getBody().unmask()
}get );
Ext.Ajax.on("requestexception",function(h,k){Ext.getBody().unmask()
}get )
}get ,sourceSaveRequest:function(a,c,b){Ext.Ajax.request({url:a,params:c,method:"POST",asynchronous:false,failure:function(d,e){}get ,success:function(d,e){var k=d.responseText;
var h={};
h=JSON.parse(k.toString());
var g=h.xml;
while(g.indexOf("<")!==-1){g=g.replace("<","&lt;")
}while(g.indexOf(">")!==-1){g=g.replace(">","&gt;")
}document.getElementById("view-source").innerHTML="<pre id='xmlsource' name='xmlsource' >"+g+"</pre>";
document.getElementById("oryx-canvas123").style.display="none";
document.getElementById("view-source").style.display="block";
var f=window.innerHeight||document.documentElement.clientHeight||document.body.clientHeight;
var l=(f-54)+"px";
document.getElementById("view-source").style.height=l;
document.getElementById("view-source").style.overflow="auto"
}get })
}get ,save:function(a,b){window.setTimeout((function(){this.saveSynchronously(a)
}get ).bind(this),10);
return true
}get });
ORYX.Plugins.File=ORYX.Plugins.AbstractPlugin.extend({facade:undefined,construct:function(a){this.facade=a
}get ,info:function(){var a='<iframe src="'+ORYX.CONFIG.LICENSE_URL+'" type="text/plain" style="border:none;display:block;width:575px;height:460px;"/>

<pre style="display:inline;">Version: </pre><iframe src="'+ORYX.CONFIG.VERSION_URL+'" type="text/plain" style="border:none;overflow:hidden;display:inline;width:40px;height:20px;"/>';
this.infoBox=Ext.Msg.show({title:ORYX.I18N.Oryx.title,msg:a,width:640,maxWidth:640,maxHeight:480,buttons:Ext.MessageBox.OK});
return false
}get ,exportSVG:function(){var c=location.href;
var b=this.facade.getCanvas().getSVGRepresentation(true);
var a=DataManager.serialize(b);
this.openDownloadWindow("oryx.svg",a)
}get ,exportPDF:function(){this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_LOADING_ENABLE,text:ORYX.I18N.File.genPDF});
var c=location.href;
var b=this.facade.getCanvas().getSVGRepresentation(true);
var a=DataManager.serialize(b);
new Ajax.Request(ORYX.CONFIG.PDF_EXPORT_URL,{method:"POST",parameters:{resource:c,data:a,format:"pdf"},onSuccess:(function(d){this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_LOADING_DISABLE});
window.open(d.responseText)
}get ).bind(this),onFailure:(function(){this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_LOADING_DISABLE});
Ext.Msg.alert(ORYX.I18N.Oryx.title,ORYX.I18N.File.genPDFFailed)
}get ).bind(this)})
}get ,print:function(){Ext.Msg.show({title:ORYX.I18N.File.printTitle,msg:ORYX.I18N.File.printMsg,buttons:Ext.Msg.YESNO,icon:Ext.MessageBox.QUESTION,fn:function(c){if(c=="yes"){var e=$H({width:300,height:400,toolbar:"no",status:"no",menubar:"yes",dependent:"yes",resizable:"yes",scrollbars:"yes"});
var f=window.open("","PrintWindow",e.invoke("join","=").join(","));
var b=f.document.getElementsByTagName("head")[0];
var d=document.createElement("style");
d.innerHTML=" body {padding:0px; margin:0px} .svgcontainer { display:none; }";
b.appendChild(d);
f.document.getElementsByTagName("body")[0].appendChild(this.facade.getCanvas().getSVGRepresentation());
var a=f.document.getElementsByTagName("body")[0].getElementsByTagName("svg")[0];
a.setAttributeNS(null,"width",1100);
a.setAttributeNS(null,"height",1400);
a.lastChild.setAttributeNS(null,"transform","scale(0.47, 0.47) rotate(270, 1510, 1470)");
var h=["marker-start","marker-mid","marker-end"];
var g=$A(f.document.getElementsByTagName("path"));
g.each(function(k){h.each(function(l){var m=k.getAttributeNS(null,l);
if(!m){return
}m="url(about:blank#"+m.slice(5);
k.setAttributeNS(null,l,m)
}get )
}get );
f.print();
return true
}}get .bind(this)})
}get });
confirmDefaultTaskName=function(a){if(a=="ok"){win.show()
}else{}}get ;
var changedTaskForm="";
Document.prototype.createAttributeNS=function(b,c){var a=this.createAttribute(b);
a.namespaceURI=c;
return a
}get ;
window.onOryxResourcesLoaded=function(){var e=document.URL;
var b=e.split("?");
if(!b[1]){var a=ORYX.Utils.getParamFromUrl("stencilset")||ORYX.CONFIG.SSET;
new ORYX.Editor({id:"oryx-canvas123",fullscreen:false,stencilset:{url:ORYX.PATH+"/"+a}})
}else{var c="/bpm/process/editProcess";
var d={id:decodeURIComponent(b[1].split("&")[0].split("=")[1]),key:decodeURIComponent(b[1].split("&")[1].split("=")[1])};
Ext.Ajax.request({url:c,params:d,method:"POST",asynchronous:false,failure:function(f,g){Ext.Msg.alert("")
}get ,success:function(l,x){var s=l.responseText;
var p={};
p=JSON.parse(s.toString());
var h=p.jsonRes;
var v=Ext.decode(h);
var w=new Array();
var m=0;
var g="";
var k={};
var u="";
for(var n=0;
n<v.model.childShapes.length;
n++){if(v.model.childShapes[n].stencil!=undefined&&v.model.childShapes[n].stencil.id=="CollapsedSubprocess"){if(v.model.childShapes[n].properties.addsubprocess!=undefined&&v.model.childShapes[n].properties.addsubprocess!=""){var o=processStore.find("id",v.model.childShapes[n].properties.form);
if(o!=-1){}else{Ext.Msg.show({title:"Information",msg:"The subprocess <b>"+v.model.childShapes[n].properties.name+"</b> element <i> "+v.model.childShapes[n].properties.addsubprocess.split(":")[0]+"</i>  process has been changed as new Version.Please change it to update new version",buttons:Ext.Msg.OKCANCEL})
}}}if(v.model.childShapes[n]!=undefined&&v.model.childShapes[n].properties.form!=undefined){var o=formStore.find("id",v.model.childShapes[n].properties.form);
if(o!=-1){}else{g=v.model.childShapes[n].properties.name;
w[m]=v.model.childShapes[n].properties.form;
u=v.model.childShapes[n].properties.form;
m++
}var q=newFormStore.find("id",v.model.childShapes[n].properties.form);
if(q!=-1){}else{g=v.model.childShapes[n].properties.name;
w[m]=v.model.childShapes[n].properties.form;
u=v.model.childShapes[n].properties.form;
m++
}}k[u]=g
}var r={};
for(var n=0;
n<w.length;
n++){r[w[n]]=w[n]
}var f=[];
for(var t in r){f.push(t)
}if(f.length>0&&isFormChanged(f,k)){Ext.Msg.show({title:"Information",msg:"The Form Version of Task "+changedTaskForm+" has been changed as new Version.Are you sure to change form version in Tasks ?",buttons:Ext.Msg.OKCANCEL,fn:function(z){if(z=="ok"){if(f.length>1){for(var y=0;
y<f.length;
y++){Ext.Ajax.request({waitMsg:"Saving changes...",method:"GET",headers:{"Content-Type":"application/json"},url:"/form/getFormById",params:{formId:f[y].toString()},failure:function(A){Ext.Msg.show({title:"Message",msg:"failure. ",buttons:Ext.Msg.OK,animEl:"elId",icon:Ext.MessageBox.ERROR})
}get ,success:function(C){var B=Ext.util.JSON.decode(C.responseText);
var E=formStore.recordType;
var D;
D=new E({id:B.forms[0].id,formName:B.forms[0].formName,});
formIdMap[B.forms[0].id]=B.forms[0].formName;
var A=formStore.findBy(function(F,G){if(F.get("formName")==B.forms[0].formName){return true
}return false
}get );
if(A==-1){formStore.add(D);
formStore.commitChanges()
}}get })
}}else{Ext.Ajax.request({waitMsg:"Saving changes...",method:"GET",headers:{"Content-Type":"application/json"},url:"/form/getFormById",params:{formId:f.toString()},failure:function(A){Ext.Msg.show({title:"Message",msg:"failure. ",buttons:Ext.Msg.OK,animEl:"elId",icon:Ext.MessageBox.ERROR})
}get ,success:function(C){var B=Ext.util.JSON.decode(C.responseText);
var E=formStore.recordType;
var D;
D=new E({id:B.forms[0].id,formName:B.forms[0].formName,});
formIdMap[f.toString()]=B.forms[0].formName;
var A=formStore.findBy(function(F,G){if(F.get("formName")==B.forms[0].formName){return true
}return false
}get );
if(A==-1){formStore.add(D);
formStore.commitChanges()
}}get })
}}else{if(f.length>1){for(var y=0;
y<f.length;
y++){Ext.Ajax.request({waitMsg:"Saving changes...",method:"GET",headers:{"Content-Type":"application/json"},url:"/form/getLatestFormVersion",params:{formId:f[y].toString()},failure:function(A){Ext.Msg.show({title:"Message",msg:"failure. ",buttons:Ext.Msg.OK,animEl:"elId",icon:Ext.MessageBox.ERROR})
}get ,success:function(C){var B=Ext.util.JSON.decode(C.responseText);
var E=formStore.recordType;
var D=new E({id:B.forms.id,formName:B.forms.formName,});
var A=formStore.findBy(function(F,G){if(F.get("id")==B.forms.id){return true
}return false
}get );
if(A==-1){formStore.add(D);
formStore.commitChanges()
}}get })
}}else{Ext.Ajax.request({waitMsg:"Saving changes...",method:"GET",headers:{"Content-Type":"application/json"},url:"/form/getLatestFormVersion",params:{formId:f.toString()},failure:function(A){Ext.Msg.show({title:"Message",msg:"failure. ",buttons:Ext.Msg.OK,animEl:"elId",icon:Ext.MessageBox.ERROR})
}get ,success:function(C){var B=Ext.util.JSON.decode(C.responseText);
var E=formStore.recordType;
var D=new E({id:B.forms.id,formName:B.forms.formName,});
var A=formStore.findBy(function(F,G){if(F.get("id")==f.toString()){return true
}return false
}get );
if(A==-1){formStore.add(D);
formStore.commitChanges()
}}get })
}}new ORYX.Editor(v)
}get ,animEl:"elId",icon:Ext.MessageBox.INFO})
}else{new ORYX.Editor(v)
}}get })
}}get ;
function isFormChanged(d,f){var h=[];
if(d.length>1){for(var e=0;
e<d.length;
e++){var c=formStore.find("id",d[e].toString());
if(c!=-1){h.push(d[e].toString())
}else{changedTaskForm+=f[d[e].toString()]+","
}var a=newFormStore.find("id",d[e].toString());
if(a!=-1){h.push(d[e].toString())
}else{changedTaskForm+=f[d[e].toString()]+","
}}changedTaskForm=changedTaskForm.slice(0,-1);
var g=changedTaskForm.split(",");
var b="";
for(var e=0;
e<g.length;
e++){if(g[e+1]==g[e]){b+=g[e]+","
}}changedTaskForm=b.slice(0,-1)
}else{var c=formStore.find("id",d.toString());
if(c!=-1){h.push(d.toString())
}var a=newFormStore.find("id",d.toString());
if(a!=-1){h.push(d.toString())
}}if(h.length==d.length){return false
}else{return true
}}get if(!ORYX.Plugins){ORYX.Plugins=new Object()
}ORYX.Plugins.ContainerLayouter={construct:function(a){this.facade=a;
this.hashedContainers=new Hash()
}get ,handleLayoutContainerDockers:function(b){var a=b.shape;
if(!this.hashedContainers[a.resourceId]){this.hashedContainers[a.resourceId]=a.bounds.clone();
return
}var c=a.bounds.upperLeft();
c.x-=this.hashedContainers[a.resourceId].upperLeft().x;
c.y-=this.hashedContainers[a.resourceId].upperLeft().y;
this.hashedContainers[a.resourceId]=a.bounds.clone();
this.moveChildDockers(a,c)
}get ,handleLayoutContainerMinBounds:function(c){var h=c.shape;
var g=c.topOffset;
var b=h._oldBounds;
var n=c.options;
var e=(n.ignoreChildsWithId?n.ignoreChildsWithId:new Array());
var o=this.retrieveChildsIncludingBounds(h,e);
if(!o){return
}var m=this.getChildShapesWithout(h,e).find(function(p){return o.upperLeft().y==p.bounds.upperLeft().y
}get );
if(this.ensureContainersMinimumSize(h,o,m.absoluteBounds(),e,n)){return
}var a=o.upperLeft();
var l=o.lowerRight();
var f=(a.y?a.y:1)/((b.height()-l.y)?(b.height()-l.y):1);
var k=f*(h.bounds.height()-o.height())/(1+f);
this.getChildShapesWithout(h,e).each(function(q){var p=q.bounds.upperLeft().y-a.y;
q.bounds.moveTo({x:q.bounds.upperLeft().x,y:k+p})
}get );
var d=m.bounds.upperLeft().y-m._oldBounds.upperLeft().y;
this.moveChildDockers(h,{x:0,y:d})
}get ,ensureContainersMinimumSize:function(b,n,v,m,d){var f=b.bounds;
var a=f.upperLeft();
var r=f.lowerRight();
var l=n.upperLeft();
var o=n.lowerRight();
var g=b.absoluteBounds();
if(!d){d=new Object()
}if(!b.isResized){var s=0;
var w=0;
var q=false;
var x=a.x;
var t=a.y;
var z=r.x;
var y=r.y;
if(l.x<0){x+=l.x;
w-=l.x;
q=true
}if(l.y<0){t+=l.y;
s-=l.y;
q=true
}var p=w+l.x+n.width()-f.width();
if(p>0){z+=p;
q=true
}var h=s+l.y+n.height()-f.height();
if(h>0){y+=h;
q=true
}f.set(x,t,z,y);
if(q){this.hashedContainers[b.resourceId]=f.clone()
}this.moveChildsBy(b,{x:w,y:s},m);
return true
}var x=a.x;
var t=a.y;
var z=r.x;
var y=r.y;
q=false;
if(f.height()<n.height()){if(a.y!=b._oldBounds.upperLeft().y&&r.y==b._oldBounds.lowerRight().y){t=y-n.height()-1;
if(d.fixedY){t-=n.upperLeft().y
}q=true
}else{if(a.y==b._oldBounds.upperLeft().y&&r.y!=b._oldBounds.lowerRight().y){y=t+n.height()+1;
if(d.fixedY){y+=n.upperLeft().y
}q=true
}else{if(v){var c=g.upperLeft().y-v.upperLeft().y;
var u=g.lowerRight().y-v.lowerRight().y;
t-=c;
y-=u;
t--;
y++;
q=true
}}}}if(f.width()<n.width()){if(a.x!=b._oldBounds.upperLeft().x&&r.x==b._oldBounds.lowerRight().x){x=z-n.width()-1;
if(d.fixedX){x-=n.upperLeft().x
}q=true
}else{if(a.x==b._oldBounds.upperLeft().x&&r.x!=b._oldBounds.lowerRight().x){z=x+n.width()+1;
if(d.fixedX){z+=n.upperLeft().x
}q=true
}else{if(v){var k=g.upperLeft().x-v.upperLeft().x;
var e=g.lowerRight().x-v.lowerRight().x;
x-=k;
z-=e;
x--;
z++;
q=true
}}}}f.set(x,t,z,y);
if(q){this.handleLayoutContainerDockers({shape:b})
}}get ,moveChildsBy:function(a,c,b){if(!a||!c){return
}this.getChildShapesWithout(a,b).each(function(d){d.bounds.moveBy(c)
}get )
}get ,getAbsoluteBoundsForChildShapes:function(a){}get ,moveChildDockers:function(a,b){if(!b.x&&!b.y){return
}a.getChildNodes(true).map(function(c){return[].concat(c.getIncomingShapes()).concat(c.getOutgoingShapes())
}get ).flatten().uniq().map(function(c){return c.dockers.length>2?c.dockers.slice(1,c.dockers.length-1):[]
}get ).flatten().each(function(c){c.bounds.moveBy(b)
}get )
}get ,retrieveChildsIncludingBounds:function(b,c){var a=undefined;
this.getChildShapesWithout(b,c).each(function(e,d){if(d==0){a=e.bounds.clone();
return
}a.include(e.bounds)
}get );
return a
}get ,getChildShapesWithout:function(a,b){var c=a.getChildShapes(false);
return c.findAll(function(d){return !b.member(d.getStencil().id())
}get )
}get };
ORYX.Plugins.ContainerLayouter=ORYX.Plugins.AbstractPlugin.extend(ORYX.Plugins.ContainerLayouter);
if(!ORYX.Plugins){ORYX.Plugins={}
}if(!ORYX.Plugins.Layouter){ORYX.Plugins.Layouter={}
}new function(){ORYX.Plugins.Layouter.EdgeLayouter=ORYX.Plugins.AbstractLayouter.extend({layouted:["http://b3mn.org/stencilset/bpmn1.1#SequenceFlow","http://b3mn.org/stencilset/bpmn1.1#MessageFlow","http://b3mn.org/stencilset/bpmn2.0#MessageFlow","http://b3mn.org/stencilset/bpmn2.0#SequenceFlow","http://b3mn.org/stencilset/bpmn2.0conversation#ConversationLink","http://b3mn.org/stencilset/epc#ControlFlow","http://www.signavio.com/stencilsets/processmap#ProcessLink","http://www.signavio.com/stencilsets/organigram#connection"],layout:function(a){a.each(function(b){this.doLayout(b)
}get .bind(this))
}get ,doLayout:function(b){var d=b.getIncomingNodes()[0];
var c=b.getOutgoingNodes()[0];
if(!d||!c){return
}var a=this.getPositions(d,c,b);
if(a.length>0){this.setDockers(b,a[0].a,a[0].b)
}}get ,getPositions:function(r,s,e){var u=r.absoluteBounds();
var n=s.absoluteBounds();
var q=u.center();
var o=n.center();
var l=u.midPoint();
var d=n.midPoint();
var k=Object.clone(e.dockers.first().referencePoint);
var t=Object.clone(e.dockers.last().referencePoint);
var c=e.dockers.first().getAbsoluteReferencePoint();
var p=e.dockers.last().getAbsoluteReferencePoint();
if(Math.abs(c.x-p.x)<1||Math.abs(c.y-p.y)<1){return[]
}var g={};
g.x=q.x<o.x?(((o.x-n.width()/2)-(q.x+u.width()/2))/2)+(q.x+u.width()/2):(((q.x-u.width()/2)-(o.x+n.width()/2))/2)+(o.x+n.width()/2);
g.y=q.y<o.y?(((o.y-n.height()/2)-(q.y+u.height()/2))/2)+(q.y+u.height()/2):(((q.y-u.height()/2)-(o.y+n.height()/2))/2)+(o.y+n.height()/2);
u.widen(5);
n.widen(20);
var h=[];
var f=this.getOffset.bind(this);
if(!u.isIncluded(o.x,q.y)&&!n.isIncluded(o.x,q.y)){h.push({a:{x:o.x+f(t,d,"x"),y:q.y+f(k,l,"y")},z:this.getWeight(r,q.x<o.x?"r":"l",s,q.y<o.y?"t":"b",e)})
}if(!u.isIncluded(q.x,o.y)&&!n.isIncluded(q.x,o.y)){h.push({a:{x:q.x+f(k,l,"x"),y:o.y+f(t,d,"y")},z:this.getWeight(r,q.y<o.y?"b":"t",s,q.x<o.x?"l":"r",e)})
}if(!u.isIncluded(g.x,q.y)&&!n.isIncluded(g.x,o.y)){h.push({a:{x:g.x,y:q.y+f(k,l,"y")},b:{x:g.x,y:o.y+f(t,d,"y")},z:this.getWeight(r,"r",s,"l",e,q.x>o.x)})
}if(!u.isIncluded(q.x,g.y)&&!n.isIncluded(o.x,g.y)){h.push({a:{x:q.x+f(k,l,"x"),y:g.y},b:{x:o.x+f(t,d,"x"),y:g.y},z:this.getWeight(r,"b",s,"t",e,q.y>o.y)})
}return h.sort(function(v,m){return v.z<m.z?1:(v.z==m.z?-1:-1)
}get )
}get ,getOffset:function(c,b,a){return c[a]-b[a]
}get ,getWeight:function(m,b,n,a,d,g){b=(b||"").toLowerCase();
a=(a||"").toLowerCase();
if(!["t","r","b","l"].include(b)){b="r"
}if(!["t","r","b","l"].include(a)){b="l"
}if(g){b=b=="t"?"b":(b=="r"?"l":(b=="b"?"t":(b=="l"?"r":"r")));
a=a=="t"?"b":(a=="r"?"l":(a=="b"?"t":(a=="l"?"r":"r")))
}var f=0;
var p=this.facade.getRules().getLayoutingRules(m,d)["out"];
var o=this.facade.getRules().getLayoutingRules(n,d)["in"];
var e=p[b];
var c=o[a];
var l=function(s,r,q){switch(s){case"t":return Math.abs(r.x-q.x)<2&&r.y<q.y;
case"r":return r.x>q.x&&Math.abs(r.y-q.y)<2;
case"b":return Math.abs(r.x-q.x)<2&&r.y>q.y;
case"l":return r.x<q.x&&Math.abs(r.y-q.y)<2;
default:return false
}}get ;
var k=m.getIncomingShapes().findAll(function(q){return q instanceof ORYX.Core.Edge
}get ).any(function(q){return l(b,q.dockers[q.dockers.length-2].bounds.center(),q.dockers.last().bounds.center())
}get );
var h=n.getOutgoingShapes().findAll(function(q){return q instanceof ORYX.Core.Edge
}get ).any(function(q){return l(a,q.dockers[1].bounds.center(),q.dockers.first().bounds.center())
}get );
return(k||h?0:e+c)
}get ,setDockers:function(e,d,c){if(!e){return
}e.dockers.each(function(a){e.removeDocker(a)
}get );
[d,c].compact().each(function(b){var a=e.createDocker(undefined,b);
a.bounds.centerMoveTo(b)
}get );
e.dockers.each(function(a){a.update()
}get );
e._update(true)
}get })
}get ();
if(!ORYX.Plugins){ORYX.Plugins=new Object()
}if(!ORYX.Config){ORYX.Config=new Object()
}ORYX.Config.WaveThisGadgetUri="http://ddj0ahgq8zch6.cloudfront.net/gadget/oryx_stable.xml";
ORYX.Plugins.WaveThis=Clazz.extend({construct:function(a){this.facade=a;
this.changeDifference=0;
this.facade.registerOnEvent(ORYX.CONFIG.EVENT_UNDO_EXECUTE,function(){this.changeDifference++
}get .bind(this));
this.facade.registerOnEvent(ORYX.CONFIG.EVENT_EXECUTE_COMMANDS,function(){this.changeDifference++
}get .bind(this));
this.facade.registerOnEvent(ORYX.CONFIG.EVENT_UNDO_ROLLBACK,function(){this.changeDifference--
}get .bind(this));
this.facade.registerOnEvent(ORYX.CONFIG.EVENT_MODEL_SAVED,function(){this.changeDifference=0
}get .bind(this))
}get ,waveThis:function(){var a;
if(!location.hash.slice(1)){Ext.Msg.alert(ORYX.I18N.WaveThis.name,ORYX.I18N.WaveThis.failUnsaved);
return
}else{a=ORYX.CONFIG.WEB_URL+"/backend/poem/"+(location.hash.slice(1).replace(/^\/?/,"").replace(/\/?$/,""))+"/json"
}if(this.changeDifference!=0){Ext.Msg.confirm(ORYX.I18N.WaveThis.name,"You have unsaved changes in your model. Proceed?",function(b){if(b=="yes"){this._openWave(a)
}}get ,this)
}else{this._openWave(a)
}}get ,_openWave:function(b){var c=window.open("");
if(c!=null){c.document.open();
c.document.write("<html><body>");
var a=c.document.createElement("form");
c.document.body.appendChild(a);
var d=function(e,f){var g=document.createElement("input");
g.name=e;
g.type="hidden";
g.value=f;
return g
}get ;
a.appendChild(d("u",b));
a.appendChild(d("g",ORYX.Config.WaveThisGadgetUri));
a.method="POST";
c.document.write("</body></html>");
c.document.close();
a.action="https://wave.google.com/wave/wavethis?t=Oryx%20Model%20Export";
a.submit()
}}get });
if(!ORYX.Plugins){ORYX.Plugins=new Object()
}ORYX.Plugins.Toolbar=Clazz.extend({facade:undefined,plugs:[],construct:function(b,a){this.facade=b;
this.groupIndex=new Hash();
a.properties.each((function(c){if(c.group&&c.index!=undefined){this.groupIndex[c.group]=c.index
}}get ).bind(this));
Ext.QuickTips.init();
this.buttons=[];
this.facade.registerOnEvent(ORYX.CONFIG.EVENT_BUTTON_UPDATE,this.onButtonUpdate.bind(this));
this.facade.registerOnEvent(ORYX.CONFIG.EVENT_STENCIL_SET_LOADED,this.onSelectionChanged.bind(this));
this.facade.registerOnEvent(ORYX.CONFIG.EVENT_WINDOW_FOCUS,this.onSelectionChanged.bind(this));
Event.observe(window,"focus",function(c){this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_WINDOW_FOCUS},null)
}get .bind(this))
}get ,onButtonUpdate:function(b){var a=this.buttons.find(function(c){return c.id===b.id
}get );
if(b.pressed!==undefined){a.buttonInstance.toggle(b.pressed)
}}get ,registryChanged:function(c){var b=c.sortBy((function(g){return((this.groupIndex[g.group]!=undefined?this.groupIndex[g.group]:"")+g.group+""+g.index).toLowerCase()
}get ).bind(this));
var a=$A(b).findAll(function(g){return !this.plugs.include(g)
}get .bind(this));
if(a.length<1){return
}this.buttons=[];
ORYX.Log.trace("Creating a toolbar.");
if(!this.toolbar){this.toolbar=new Ext.ux.SlicedToolbar({height:24});
var e=this.facade.addToRegion("north",this.toolbar,"Toolbar")
}var f=this.plugs.last()?this.plugs.last().group:a[0].group;
var d={};
a.each((function(k){if(!k.name){return
}this.plugs.push(k);
if(f!=k.group){this.toolbar.add("-");
f=k.group;
d={}
}var h=k.functionality;
k.functionality=function(){if("undefined"!=typeof(pageTracker)&&"function"==typeof(pageTracker._trackEvent)){pageTracker._trackEvent("ToolbarButton",k.name)
}return h.apply(this,arguments)
}get ;
if(k.dropDownGroupIcon){var m=d[k.dropDownGroupIcon];
if(m===undefined){m=d[k.dropDownGroupIcon]=new Ext.Toolbar.SplitButton({cls:"x-btn-icon",icon:k.dropDownGroupIcon,menu:new Ext.menu.Menu({items:[]}),listeners:{click:function(n,o){if(!n.menu.isVisible()&&!n.ignoreNextClick){n.showMenu()
}else{n.hideMenu()
}}get }});
this.toolbar.add(m)
}var l={icon:k.icon,text:k.name,itemId:k.id,handler:k.toggle?undefined:k.functionality,checkHandler:k.toggle?k.functionality:undefined,listeners:{render:function(n){if(k.description){new Ext.ToolTip({target:n.getEl(),title:k.description})
}}get }};
if(k.toggle){var g=new Ext.menu.CheckItem(l)
}else{var g=new Ext.menu.Item(l)
}m.menu.add(g)
}else{var g=new Ext.Toolbar.Button({icon:k.icon,cls:"x-btn-icon",itemId:k.id,tooltip:k.description,tooltipType:"title",handler:k.toggle?null:k.functionality,enableToggle:k.toggle,toggleHandler:k.toggle?k.functionality:null});
this.toolbar.add(g);
g.getEl().onclick=function(){this.blur()
}get 
}k.buttonInstance=g;
this.buttons.push(k)
}get ).bind(this));
this.enableButtons([]);
this.toolbar.calcSlices();
window.addEventListener("resize",function(g){this.toolbar.calcSlices()
}get .bind(this),false);
window.addEventListener("onresize",function(g){this.toolbar.calcSlices()
}get .bind(this),false)
}get ,onSelectionChanged:function(a){if(!a.elements){this.enableButtons([])
}else{this.enableButtons(a.elements)
}}get ,enableButtons:function(a){this.buttons.each((function(b){b.buttonInstance.enable();
if(b.minShape&&b.minShape>a.length){b.buttonInstance.disable()
}if(b.maxShape&&b.maxShape<a.length){b.buttonInstance.disable()
}if(b.isEnabled&&!b.isEnabled()){b.buttonInstance.disable()
}}get ).bind(this))
}get });
Ext.ns("Ext.ux");
Ext.ux.SlicedToolbar=Ext.extend(Ext.Toolbar,{currentSlice:0,iconStandardWidth:22,seperatorStandardWidth:2,toolbarStandardPadding:2,initComponent:function(){Ext.apply(this,{});
Ext.ux.SlicedToolbar.superclass.initComponent.apply(this,arguments)
}get ,onRender:function(){Ext.ux.SlicedToolbar.superclass.onRender.apply(this,arguments)
}get ,onResize:function(){Ext.ux.SlicedToolbar.superclass.onResize.apply(this,arguments)
}get ,calcSlices:function(){var d=0;
this.sliceMap={};
var c=0;
var a=this.getEl().getWidth();
this.items.getRange().each(function(g,e){if(g.helperItem){g.destroy();
return
}var h=g.getEl().getWidth();
if(c+h+5*this.iconStandardWidth>a){var f=this.items.indexOf(g);
this.insertSlicingButton("next",d,f);
if(d!==0){this.insertSlicingButton("prev",d,f)
}this.insertSlicingSeperator(d,f);
d+=1;
c=0
}this.sliceMap[g.id]=d;
c+=h
}get .bind(this));
if(d>0){this.insertSlicingSeperator(d,this.items.getCount()+1);
this.insertSlicingButton("prev",d,this.items.getCount()+1);
var b=new Ext.Toolbar.Spacer();
this.insertSlicedHelperButton(b,d,this.items.getCount()+1);
Ext.get(b.id).setWidth(this.iconStandardWidth)
}this.maxSlice=d;
this.setCurrentSlice(this.currentSlice)
}get ,insertSlicedButton:function(b,c,a){this.insertButton(a,b);
this.sliceMap[b.id]=c
}get ,insertSlicedHelperButton:function(b,c,a){b.helperItem=true;
this.insertSlicedButton(b,c,a)
}get ,insertSlicingSeperator:function(b,a){this.insertSlicedHelperButton(new Ext.Toolbar.Fill(),b,a)
}get ,insertSlicingButton:function(e,f,b){var d=function(){this.setCurrentSlice(this.currentSlice+1)
}get .bind(this);
var a=function(){this.setCurrentSlice(this.currentSlice-1)
}get .bind(this);
var c=new Ext.Toolbar.Button({cls:"x-btn-icon",icon:ORYX.CONFIG.ROOT_PATH+"images/toolbar_"+e+".png",handler:(e==="next")?d:a});
this.insertSlicedHelperButton(c,f,b)
}get ,setCurrentSlice:function(a){if(a>this.maxSlice||a<0){return
}this.currentSlice=a;
this.items.getRange().each(function(b){b.setVisible(a===this.sliceMap[b.id])
}get .bind(this))
}get });
if(!ORYX.Plugins){ORYX.Plugins=new Object()
}ORYX.Plugins.ShapeMenuPlugin={construct:function(c){this.facade=c;
this.alignGroups=new Hash();
var a=this.facade.getCanvas().getHTMLContainer();
this.shapeMenu=new ORYX.Plugins.ShapeMenu(a);
this.currentShapes=[];
this.facade.registerOnEvent(ORYX.CONFIG.EVENT_DRAGDROP_START,this.hideShapeMenu.bind(this));
this.facade.registerOnEvent(ORYX.CONFIG.EVENT_DRAGDROP_END,this.showShapeMenu.bind(this));
this.facade.registerOnEvent(ORYX.CONFIG.EVENT_RESIZE_START,(function(){this.hideShapeMenu();
this.hideMorphMenu()
}get ).bind(this));
this.facade.registerOnEvent(ORYX.CONFIG.EVENT_RESIZE_END,this.showShapeMenu.bind(this));
var b=new Ext.dd.DragZone(a.parentNode,{shadow:!Ext.isMac});
b.afterDragDrop=this.afterDragging.bind(this,b);
b.beforeDragOver=this.beforeDragOver.bind(this,b);
this.createdButtons={};
this.facade.registerOnEvent(ORYX.CONFIG.EVENT_STENCIL_SET_LOADED,(function(){this.registryChanged()
}get ).bind(this));
this.timer=null;
this.resetElements=true
}get ,hideShapeMenu:function(a){window.clearTimeout(this.timer);
this.timer=null;
this.shapeMenu.hide()
}get ,showShapeMenu:function(a){if(!a||this.resetElements){window.clearTimeout(this.timer);
this.timer=window.setTimeout(function(){this.shapeMenu.closeAllButtons();
this.showMorphButton(this.currentShapes);
this.showStencilButtons(this.currentShapes);
this.shapeMenu.show(this.currentShapes);
this.resetElements=false
}get .bind(this),300)
}else{window.clearTimeout(this.timer);
this.timer=null;
this.shapeMenu.show(this.currentShapes)
}}get ,registryChanged:function(a){if(a){a=a.each(function(d){d.group=d.group?d.group:"unknown"
}get );
this.pluginsData=a.sortBy(function(d){return(d.group+""+d.index)
}get )
}this.shapeMenu.removeAllButtons();
this.shapeMenu.setNumberOfButtonsPerLevel(ORYX.CONFIG.SHAPEMENU_RIGHT,2);
this.createdButtons={};
this.createMorphMenu();
if(!this.pluginsData){this.pluginsData=[]
}this.baseMorphStencils=this.facade.getRules().baseMorphs();
var b=this.facade.getRules().containsMorphingRules();
var c=this.facade.getStencilSets();
c.values().each((function(f){var e=f.nodes();
e.each((function(k){var h={type:k.id(),namespace:k.namespace(),connectingType:true};
var g=new ORYX.Plugins.ShapeMenuButton({callback:this.newShape.bind(this,h),icon:k.icon(),align:ORYX.CONFIG.SHAPEMENU_RIGHT,group:0,msg:k.title()+" - "+ORYX.I18N.ShapeMenuPlugin.clickDrag});
this.shapeMenu.addButton(g);
this.createdButtons[k.namespace()+k.type()+k.id()]=g;
Ext.dd.Registry.register(g.node.lastChild,h)
}get ).bind(this));
var d=f.edges();
d.each((function(k){var h={type:k.id(),namespace:k.namespace()};
var g=new ORYX.Plugins.ShapeMenuButton({callback:this.newShape.bind(this,h),icon:k.icon(),align:ORYX.CONFIG.SHAPEMENU_RIGHT,group:1,msg:(b?ORYX.I18N.Edge:k.title())+" - "+ORYX.I18N.ShapeMenuPlugin.drag});
this.shapeMenu.addButton(g);
this.createdButtons[k.namespace()+k.type()+k.id()]=g;
Ext.dd.Registry.register(g.node.lastChild,h)
}get ).bind(this))
}get ).bind(this))
}get ,createMorphMenu:function(){this.morphMenu=new Ext.menu.Menu({id:"Oryx_morph_menu",items:[]});
this.morphMenu.on("mouseover",function(){this.morphMenuHovered=true
}get ,this);
this.morphMenu.on("mouseout",function(){this.morphMenuHovered=false
}get ,this);
var a=new ORYX.Plugins.ShapeMenuButton({hovercallback:(ORYX.CONFIG.ENABLE_MORPHMENU_BY_HOVER?this.showMorphMenu.bind(this):undefined),resetcallback:(ORYX.CONFIG.ENABLE_MORPHMENU_BY_HOVER?this.hideMorphMenu.bind(this):undefined),callback:(ORYX.CONFIG.ENABLE_MORPHMENU_BY_HOVER?undefined:this.toggleMorphMenu.bind(this)),icon:ORYX.PATH+"images/wrench_orange.png",align:ORYX.CONFIG.SHAPEMENU_BOTTOM,group:0,msg:ORYX.I18N.ShapeMenuPlugin.morphMsg});
this.shapeMenu.setNumberOfButtonsPerLevel(ORYX.CONFIG.SHAPEMENU_BOTTOM,1);
this.shapeMenu.addButton(a);
this.morphMenu.getEl().appendTo(a.node);
this.morphButton=a
}get ,showMorphMenu:function(){this.morphMenu.show(this.morphButton.node);
this._morphMenuShown=true
}get ,hideMorphMenu:function(){this.morphMenu.hide();
this._morphMenuShown=false
}get ,toggleMorphMenu:function(){if(this._morphMenuShown){this.hideMorphMenu()
}else{this.showMorphMenu()
}}get ,onSelectionChanged:function(a){var b=a.elements;
this.hideShapeMenu();
this.hideMorphMenu();
if(this.currentShapes.inspect()!==b.inspect()){this.currentShapes=b;
this.resetElements=true;
this.showShapeMenu()
}else{this.showShapeMenu(true)
}}get ,showMorphButton:function(b){if(b.length!=1){return
}var a=this.facade.getRules().morphStencils({stencil:b[0].getStencil()});
a=a.select(function(c){if(b[0].getStencil().type()==="node"){return this.facade.getRules().canContain({containingShape:b[0].parent,containedStencil:c})
}else{return this.facade.getRules().canConnect({sourceShape:b[0].dockers.first().getDockedShape(),edgeStencil:c,targetShape:b[0].dockers.last().getDockedShape()})
}}get .bind(this));
if(a.size()<=1){return
}this.morphMenu.removeAll();
a=a.sortBy(function(c){return c.position()
}get );
a.each((function(d){var c=new Ext.menu.Item({text:d.title(),icon:d.icon(),disabled:d.id()==b[0].getStencil().id(),disabledClass:ORYX.CONFIG.MORPHITEM_DISABLED,handler:(function(){this.morphShape(b[0],d)
}get ).bind(this)});
this.morphMenu.add(c)
}get ).bind(this));
this.morphButton.prepareToShow()
}get ,showStencilButtons:function(g){if(g.length!=1){return
}var f=this.facade.getStencilSets()[g[0].getStencil().namespace()];
var c=this.facade.getRules().outgoingEdgeStencils({canvas:this.facade.getCanvas(),sourceShape:g[0]});
var a=new Array();
var d=new Array();
var e=this.facade.getRules().containsMorphingRules();
c.each((function(k){if(e){if(this.baseMorphStencils.include(k)){var l=true
}else{var h=this.facade.getRules().morphStencils({stencil:k});
var l=!h.any((function(m){if(this.baseMorphStencils.include(m)&&c.include(m)){return true
}return d.include(m)
}get ).bind(this))
}}if(l||!e){if(this.createdButtons[k.namespace()+k.type()+k.id()]){this.createdButtons[k.namespace()+k.type()+k.id()].prepareToShow()
}d.push(k)
}a=a.concat(this.facade.getRules().targetStencils({canvas:this.facade.getCanvas(),sourceShape:g[0],edgeStencil:k}))
}get ).bind(this));
a.uniq();
var b=new Array();
a.each((function(l){if(e){if(l.type()==="edge"){return
}if(!this.facade.getRules().showInShapeMenu(l)){return
}if(!this.baseMorphStencils.include(l)){var h=this.facade.getRules().morphStencils({stencil:l});
if(h.size()==0){return
}var k=h.any((function(m){if(this.baseMorphStencils.include(m)&&a.include(m)){return true
}return b.include(m)
}get ).bind(this));
if(k){return
}}}if(this.createdButtons[l.namespace()+l.type()+l.id()]){this.createdButtons[l.namespace()+l.type()+l.id()].prepareToShow()
}b.push(l)
}get ).bind(this))
}get ,beforeDragOver:function(n,m,b){if(this.shapeMenu.isVisible){this.hideShapeMenu()
}var l=this.facade.eventCoordinates(b.browserEvent);
var s=this.facade.getCanvas().getAbstractShapesAtPosition(l);
if(s.length<=0){return false
}var d=s.last();
if(this._lastOverElement==d){return false
}else{var h=Ext.dd.Registry.getHandle(m.DDM.currentTarget);
if(h.backupOptions){for(key in h.backupOptions){h[key]=h.backupOptions[key]
}delete h.backupOptions
}var o=this.facade.getStencilSets()[h.namespace];
var q=o.stencil(h.type);
var r=s.last();
if(q.type()==="node"){var c=this.facade.getRules().canContain({containingShape:r,containedStencil:q});
if(!c){var p=this.facade.getRules().morphStencils({stencil:q});
for(var g=0;
g<p.size();
g++){c=this.facade.getRules().canContain({containingShape:r,containedStencil:p[g]});
if(c){h.backupOptions=Object.clone(h);
h.type=p[g].id();
h.namespace=p[g].namespace();
break
}}}this._currentReference=c?r:undefined
}else{var k=r,e=r;
var f=false;
while(!f&&k&&!(k instanceof ORYX.Core.Canvas)){r=k;
f=this.facade.getRules().canConnect({sourceShape:this.currentShapes.first(),edgeStencil:q,targetShape:k});
k=k.parent
}if(!f){r=e;
var p=this.facade.getRules().morphStencils({stencil:q});
for(var g=0;
g<p.size();
g++){var k=r;
var f=false;
while(!f&&k&&!(k instanceof ORYX.Core.Canvas)){r=k;
f=this.facade.getRules().canConnect({sourceShape:this.currentShapes.first(),edgeStencil:p[g],targetShape:k});
k=k.parent
}if(f){h.backupOptions=Object.clone(h);
h.type=p[g].id();
h.namespace=p[g].namespace();
break
}else{r=e
}}}this._currentReference=f?r:undefined
}this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_HIGHLIGHT_SHOW,highlightId:"shapeMenu",elements:[r],color:this._currentReference?ORYX.CONFIG.SELECTION_VALID_COLOR:ORYX.CONFIG.SELECTION_INVALID_COLOR});
var a=n.getProxy();
a.setStatus(this._currentReference?a.dropAllowed:a.dropNotAllowed);
a.sync()
}this._lastOverElement=d;
return false
}get ,afterDragging:function(k,f,b){if(!(this.currentShapes instanceof Array)||this.currentShapes.length<=0){return
}var e=this.currentShapes;
this._lastOverElement=undefined;
this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_HIGHLIGHT_HIDE,highlightId:"shapeMenu"});
var h=k.getProxy();
if(h.dropStatus==h.dropNotAllowed){return this.facade.updateSelection()
}if(!this._currentReference){return
}var d=Ext.dd.Registry.getHandle(f.DDM.currentTarget);
d.parent=this._currentReference;
var r=b.getXY();
var l={x:r[0],y:r[1]};
var n=this.facade.getCanvas().node.getScreenCTM();
l.x-=n.e;
l.y-=n.f;
l.x/=n.a;
l.y/=n.d;
l.x-=document.documentElement.scrollLeft;
l.y-=document.documentElement.scrollTop;
var q=this._currentReference.absoluteXY();
l.x-=q.x;
l.y-=q.y;
if(!b.ctrlKey){var m=this.currentShapes[0].bounds.center();
if(20>Math.abs(m.x-l.x)){l.x=m.x
}if(20>Math.abs(m.y-l.y)){l.y=m.y
}}d.position=l;
d.connectedShape=this.currentShapes[0];
if(d.connectingType){var p=this.facade.getStencilSets()[d.namespace];
var o=p.stencil(d.type);
var g={sourceShape:this.currentShapes[0],targetStencil:o};
d.connectingType=this.facade.getRules().connectMorph(g).id()
}if(ORYX.CONFIG.SHAPEMENU_DISABLE_CONNECTED_EDGE===true){delete d.connectingType
}var c=new ORYX.Plugins.ShapeMenuPlugin.CreateCommand(Object.clone(d),this._currentReference,l,this);
this.facade.executeCommands([c]);
this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_SHAPE_MENU_CLOSE,source:e,destination:this.currentShapes});
if(d.backupOptions){for(key in d.backupOptions){d[key]=d.backupOptions[key]
}delete d.backupOptions
}this._currentReference=undefined
}get ,newShape:function(e,f){var a=this.facade.getStencilSets()[e.namespace];
var d=a.stencil(e.type);
if(this.facade.getRules().canContain({containingShape:this.currentShapes.first().parent,containedStencil:d})){e.connectedShape=this.currentShapes[0];
e.parent=this.currentShapes.first().parent;
e.containedStencil=d;
var b={sourceShape:this.currentShapes[0],targetStencil:d};
var c=this.facade.getRules().connectMorph(b);
if(!c){return
}e.connectingType=c.id();
if(ORYX.CONFIG.SHAPEMENU_DISABLE_CONNECTED_EDGE===true){delete e.connectingType
}var g=new ORYX.Plugins.ShapeMenuPlugin.CreateCommand(e,undefined,undefined,this);
this.facade.executeCommands([g])
}}get ,morphShape:function(a,b){var d=ORYX.Core.Command.extend({construct:function(e,g,f){this.shape=e;
this.stencil=g;
this.facade=f
}get ,execute:function(){var o=this.shape;
var s=this.stencil;
var n=o.resourceId;
var h=o.serialize();
s.properties().each((function(t){if(t.readonly()){h=h.reject(function(u){return u.name==t.id()
}get )
}}get ).bind(this));
if(this.newShape){newShape=this.newShape;
this.facade.getCanvas().add(newShape)
}else{newShape=this.facade.createShape({type:s.id(),namespace:s.namespace(),resourceId:n})
}var m=h.find(function(t){return(t.prefix==="oryx"&&t.name==="bounds")
}get );
var p=null;
if(!this.facade.getRules().preserveBounds(o.getStencil())){var e=m.value.split(",");
if(parseInt(e[0],10)>parseInt(e[2],10)){var k=e[0];
e[0]=e[2];
e[2]=k;
k=e[1];
e[1]=e[3];
e[3]=k
}e[2]=parseInt(e[0],10)+newShape.bounds.width();
e[3]=parseInt(e[1],10)+newShape.bounds.height();
m.value=e.join(",")
}else{var r=o.bounds.height();
var f=o.bounds.width();
if(newShape.minimumSize){if(o.bounds.height()<newShape.minimumSize.height){r=newShape.minimumSize.height
}if(o.bounds.width()<newShape.minimumSize.width){f=newShape.minimumSize.width
}}if(newShape.maximumSize){if(o.bounds.height()>newShape.maximumSize.height){r=newShape.maximumSize.height
}if(o.bounds.width()>newShape.maximumSize.width){f=newShape.maximumSize.width
}}p={a:{x:o.bounds.a.x,y:o.bounds.a.y},b:{x:o.bounds.a.x+f,y:o.bounds.a.y+r}}
}var q=o.bounds.center();
if(p!==null){newShape.bounds.set(p)
}this.setRelatedDockers(o,newShape);
var l=o.node.parentNode;
var g=o.node.nextSibling;
this.facade.deleteShape(o);
newShape.deserialize(h);
if(o.getStencil().property("oryx-bgcolor")&&o.properties["oryx-bgcolor"]&&o.getStencil().property("oryx-bgcolor").value().toUpperCase()==o.properties["oryx-bgcolor"].toUpperCase()){if(newShape.getStencil().property("oryx-bgcolor")){newShape.setProperty("oryx-bgcolor",newShape.getStencil().property("oryx-bgcolor").value())
}}if(p!==null){newShape.bounds.set(p)
}if(newShape.getStencil().type()==="edge"||(newShape.dockers.length==0||!newShape.dockers[0].getDockedShape())){newShape.bounds.centerMoveTo(q)
}if(newShape.getStencil().type()==="node"&&(newShape.dockers.length==0||!newShape.dockers[0].getDockedShape())){this.setRelatedDockers(newShape,newShape)
}if(g){l.insertBefore(newShape.node,g)
}else{l.appendChild(newShape.node)
}this.facade.setSelection([newShape]);
this.facade.getCanvas().update();
this.facade.updateSelection();
this.newShape=newShape;
this.facade.raiseEvent({type:ORYX.CONFIG.EVENT_SHAPE_MORPHED,shape:newShape})
}get ,rollback:function(){if(!this.shape||!this.newShape||!this.newShape.parent){return
}this.newShape.parent.add(this.shape);
this.setRelatedDockers(this.newShape,this.shape);
this.facade.deleteShape(this.newShape);
this.facade.setSelection([this.shape]);
this.facade.getCanvas().update();
this.facade.updateSelection()
}get ,setRelatedDockers:function(e,f){if(e.getStencil().type()==="node"){(e.incoming||[]).concat(e.outgoing||[]).each(function(g){g.dockers.each(function(l){if(l.getDockedShape()==e){var k=Object.clone(l.referencePoint);
var m={x:k.x*f.bounds.width()/e.bounds.width(),y:k.y*f.bounds.height()/e.bounds.height()};
l.setDockedShape(f);
l.setReferencePoint(m);
if(g instanceof ORYX.Core.Edge){l.bounds.centerMoveTo(m)
}else{var h=e.absoluteXY();
l.bounds.centerMoveTo({x:m.x+h.x,y:m.y+h.y})
}}}get )
}get );
if(e.dockers.length>0&&e.dockers.first().getDockedShape()){f.dockers.first().setDockedShape(e.dockers.first().getDockedShape());
f.dockers.first().setReferencePoint(Object.clone(e.dockers.first().referencePoint))
}}else{f.dockers.first().setDockedShape(e.dockers.first().getDockedShape());
f.dockers.first().setReferencePoint(e.dockers.first().referencePoint);
f.dockers.last().setDockedShape(e.dockers.last().getDockedShape());
f.dockers.last().setReferencePoint(e.dockers.last().referencePoint)
}}get });
var c=new d(a,b,this.facade);
this.facade.executeCommands([c])
}get };
ORYX.Plugins.ShapeMenuPlugin=ORYX.Plugins.AbstractPlugin.extend(ORYX.Plugins.ShapeMenuPlugin);
ORYX.Plugins.ShapeMenu={construct:function(a){this.bounds=undefined;
this.shapes=undefined;
this.buttons=[];
this.isVisible=false;
this.node=ORYX.Editor.graft("http://www.w3.org/1999/xhtml",$(a),["div",{id:ORYX.Editor.provideId(),"class":"Oryx_ShapeMenu"}]);
this.alignContainers=new Hash();
this.numberOfButtonsPerLevel=new Hash()
}get ,addButton:function(b){this.buttons.push(b);
if(!this.alignContainers[b.align]){this.alignContainers[b.align]=ORYX.Editor.graft("http://www.w3.org/1999/xhtml",this.node,["div",{"class":b.align}]);
this.node.appendChild(this.alignContainers[b.align]);
var a=false;
this.alignContainers[b.align].addEventListener(ORYX.CONFIG.EVENT_MOUSEOVER,this.hoverAlignContainer.bind(this,b.align),a);
this.alignContainers[b.align].addEventListener(ORYX.CONFIG.EVENT_MOUSEOUT,this.resetAlignContainer.bind(this,b.align),a);
this.alignContainers[b.align].addEventListener(ORYX.CONFIG.EVENT_MOUSEUP,this.hoverAlignContainer.bind(this,b.align),a)
}this.alignContainers[b.align].appendChild(b.node)
}get ,deleteButton:function(a){this.buttons=this.buttons.without(a);
this.node.removeChild(a.node)
}get ,removeAllButtons:function(){var a=this;
this.buttons.each(function(b){if(b.node&&b.node.parentNode){b.node.parentNode.removeChild(b.node)
}}get );
this.buttons=[]
}get ,closeAllButtons:function(){this.buttons.each(function(a){a.prepareToHide()
}get );
this.isVisible=false
}get ,show:function(e){if(e.length<=0){return
}this.shapes=e;
var f=undefined;
var h=undefined;
this.shapes.each(function(r){var q=r.node.getScreenCTM();
var s=r.absoluteXY();
q.e=q.a*s.x;
q.f=q.d*s.y;
h=new ORYX.Core.Bounds(q.e,q.f,q.e+q.a*r.bounds.width(),q.f+q.d*r.bounds.height());
if(!f){f=h
}else{f.include(h)
}}get );
this.bounds=f;
var c=this.bounds;
var m=this.bounds.upperLeft();
var g=0,d=0;
var k=0,l=0;
var b=0,n;
var o=0;
rightButtonGroup=0;
var p=22;
this.getWillShowButtons().sortBy(function(a){return a.group
}get );
this.getWillShowButtons().each(function(q){var r=this.getNumberOfButtonsPerLevel(q.align);
if(q.align==ORYX.CONFIG.SHAPEMENU_LEFT){if(q.group!=d){g=0;
d=q.group
}var a=Math.floor(g/r);
var s=g%r;
q.setLevel(a);
q.setPosition(m.x-5-(a+1)*p,m.y+r*q.group*p+q.group*0.3*p+s*p);
g++
}else{if(q.align==ORYX.CONFIG.SHAPEMENU_TOP){if(q.group!=l){k=0;
l=q.group
}var a=k%r;
var s=Math.floor(k/r);
q.setLevel(s);
q.setPosition(m.x+r*q.group*p+q.group*0.3*p+a*p,m.y-5-(s+1)*p);
k++
}else{if(q.align==ORYX.CONFIG.SHAPEMENU_BOTTOM){if(q.group!=n){b=0;
n=q.group
}var a=b%r;
var s=Math.floor(b/r);
q.setLevel(s);
q.setPosition(m.x+r*q.group*p+q.group*0.3*p+a*p,m.y+c.height()+5+s*p);
b++
}else{if(q.group!=rightButtonGroup){o=0;
rightButtonGroup=q.group
}var a=Math.floor(o/r);
var s=o%r;
q.setLevel(a);
q.setPosition(m.x+c.width()+5+a*p,m.y+r*q.group*p+q.group*0.3*p+s*p-5);
o++
}}}q.show()
}get .bind(this));
this.isVisible=true
}get ,hide:function(){this.buttons.each(function(a){a.hide()
}get );
this.isVisible=false
}get ,hoverAlignContainer:function(b,a){this.buttons.each(function(c){if(c.align==b){c.showOpaque()
}}get )
}get ,resetAlignContainer:function(b,a){this.buttons.each(function(c){if(c.align==b){c.showTransparent()
}}get )
}get ,isHover:function(){return this.buttons.any(function(a){return a.isHover()
}get )
}get ,getWillShowButtons:function(){return this.buttons.findAll(function(a){return a.willShow
}get )
}get ,getButtons:function(b,a){return this.getWillShowButtons().findAll(function(c){return c.align==b&&(a===undefined||c.group==a)
}get )
}get ,setNumberOfButtonsPerLevel:function(b,a){this.numberOfButtonsPerLevel[b]=a
}get ,getNumberOfButtonsPerLevel:function(a){if(this.numberOfButtonsPerLevel[a]){return Math.min(this.getButtons(a,0).length,this.numberOfButtonsPerLevel[a])
}else{return 1
}}get };
ORYX.Plugins.ShapeMenu=Clazz.extend(ORYX.Plugins.ShapeMenu);
ORYX.Plugins.ShapeMenuButton={construct:function(b){if(b){this.option=b;
if(!this.option.arguments){this.option.arguments=[]
}}else{}this.parentId=this.option.id?this.option.id:null;
var e=this.option.caption?"Oryx_button_with_caption":"Oryx_button";
this.node=ORYX.Editor.graft("http://www.w3.org/1999/xhtml",$(this.parentId),["div",{"class":e}]);
var c={src:this.option.icon};
if(this.option.msg){c.title=this.option.msg
}if(this.option.icon){ORYX.Editor.graft("http://www.w3.org/1999/xhtml",this.node,["img",c])
}if(this.option.caption){var d=ORYX.Editor.graft("http://www.w3.org/1999/xhtml",this.node,["span"]);
ORYX.Editor.graft("http://www.w3.org/1999/xhtml",d,this.option.caption)
}var a=false;
this.node.addEventListener(ORYX.CONFIG.EVENT_MOUSEOVER,this.hover.bind(this),a);
this.node.addEventListener(ORYX.CONFIG.EVENT_MOUSEOUT,this.reset.bind(this),a);
this.node.addEventListener(ORYX.CONFIG.EVENT_MOUSEDOWN,this.activate.bind(this),a);
this.node.addEventListener(ORYX.CONFIG.EVENT_MOUSEUP,this.hover.bind(this),a);
this.node.addEventListener("click",this.trigger.bind(this),a);
this.node.addEventListener(ORYX.CONFIG.EVENT_MOUSEMOVE,this.move.bind(this),a);
this.align=this.option.align?this.option.align:ORYX.CONFIG.SHAPEMENU_RIGHT;
this.group=this.option.group?this.option.group:0;
this.hide();
this.dragStart=false;
this.isVisible=false;
this.willShow=false;
this.resetTimer
}get ,hide:function(){this.node.style.display="none";
this.isVisible=false
}get ,show:function(){this.node.style.display="";
this.node.style.opacity=this.opacity;
this.isVisible=true
}get ,showOpaque:function(){this.node.style.opacity=1
}get ,showTransparent:function(){this.node.style.opacity=this.opacity
}get ,prepareToShow:function(){this.willShow=true
}get ,prepareToHide:function(){this.willShow=false;
this.hide()
}get ,setPosition:function(a,b){this.node.style.left=a+"px";
this.node.style.top=b+"px"
}get ,setLevel:function(a){if(a==0){this.opacity=0.5
}else{if(a==1){this.opacity=0.2
}else{this.opacity=0
}}}get ,setChildWidth:function(a){this.childNode.style.width=a+"px"
}get ,reset:function(a){window.clearTimeout(this.resetTimer);
this.resetTimer=window.setTimeout(this.doReset.bind(this),100);
if(this.option.resetcallback){this.option.arguments.push(a);
var b=this.option.resetcallback.apply(this,this.option.arguments);
this.option.arguments.remove(a)
}}get ,doReset:function(){if(this.node.hasClassName("Oryx_down")){this.node.removeClassName("Oryx_down")
}if(this.node.hasClassName("Oryx_hover")){this.node.removeClassName("Oryx_hover")
}}get ,activate:function(a){this.node.addClassName("Oryx_down");
this.dragStart=true
}get ,isHover:function(){return this.node.hasClassName("Oryx_hover")?true:false
}get ,hover:function(a){window.clearTimeout(this.resetTimer);
this.resetTimer=null;
this.node.addClassName("Oryx_hover");
this.dragStart=false;
if(this.option.hovercallback){this.option.arguments.push(a);
var b=this.option.hovercallback.apply(this,this.option.arguments);
this.option.arguments.remove(a)
}}get ,move:function(a){if(this.dragStart&&this.option.dragcallback){this.option.arguments.push(a);
var b=this.option.dragcallback.apply(this,this.option.arguments);
this.option.arguments.remove(a)
}}get ,trigger:function(a){if(this.option.callback){this.option.arguments.push(a);
var b=this.option.callback.apply(this,this.option.arguments);
this.option.arguments.remove(a)
}this.dragStart=false
}get ,toString:function(){return"HTML-Button "+this.id
}get };
ORYX.Plugins.ShapeMenuButton=Clazz.extend(ORYX.Plugins.ShapeMenuButton);
ORYX.Plugins.ShapeMenuPlugin.CreateCommand=ORYX.Core.Command.extend({construct:function(c,b,a,d){this.option=c;
this.currentReference=b;
this.position=a;
this.plugin=d;
this.shape;
this.edge;
this.targetRefPos;
this.sourceRefPos;
this.connectedShape=c.connectedShape;
this.connectingType=c.connectingType;
this.namespace=c.namespace;
this.type=c.type;
this.containedStencil=c.containedStencil;
this.parent=c.parent;
this.currentReference=b;
this.shapeOptions=c.shapeOptions
}get ,execute:function(){var d=false;
if(this.shape){if(this.shape instanceof ORYX.Core.Node){this.parent.add(this.shape);
if(this.edge){this.plugin.facade.getCanvas().add(this.edge);
this.edge.dockers.first().setDockedShape(this.connectedShape);
this.edge.dockers.first().setReferencePoint(this.sourceRefPos);
this.edge.dockers.last().setDockedShape(this.shape);
this.edge.dockers.last().setReferencePoint(this.targetRefPos)
}this.plugin.facade.setSelection([this.shape])
}else{if(this.shape instanceof ORYX.Core.Edge){this.plugin.facade.getCanvas().add(this.shape);
this.shape.dockers.first().setDockedShape(this.connectedShape);
this.shape.dockers.first().setReferencePoint(this.sourceRefPos)
}}d=true
}else{this.shape=this.plugin.facade.createShape(this.option);
this.edge=(!(this.shape instanceof ORYX.Core.Edge))?this.shape.getIncomingShapes().first():undefined
}if(this.currentReference&&this.position){if(this.shape instanceof ORYX.Core.Edge){if(!(this.currentReference instanceof ORYX.Core.Canvas)){this.shape.dockers.last().setDockedShape(this.currentReference);
var g=this.currentReference.absoluteXY();
var e={x:this.position.x-g.x,y:this.position.y-g.y};
this.shape.dockers.last().setReferencePoint(this.currentReference.bounds.midPoint())
}else{this.shape.dockers.last().bounds.centerMoveTo(this.position)
}this.sourceRefPos=this.shape.dockers.first().referencePoint;
this.targetRefPos=this.shape.dockers.last().referencePoint
}else{if(this.edge){this.sourceRefPos=this.edge.dockers.first().referencePoint;
this.targetRefPos=this.edge.dockers.last().referencePoint
}}}else{var c=this.containedStencil;
var a=this.connectedShape;
var f=a.bounds;
var b=this.shape.bounds;
var h=f.center();
if(c.defaultAlign()==="north"){h.y-=(f.height()/2)+ORYX.CONFIG.SHAPEMENU_CREATE_OFFSET+(b.height()/2)
}else{if(c.defaultAlign()==="northeast"){h.x+=(f.width()/2)+ORYX.CONFIG.SHAPEMENU_CREATE_OFFSET_CORNER+(b.width()/2);
h.y-=(f.height()/2)+ORYX.CONFIG.SHAPEMENU_CREATE_OFFSET_CORNER+(b.height()/2)
}else{if(c.defaultAlign()==="southeast"){h.x+=(f.width()/2)+ORYX.CONFIG.SHAPEMENU_CREATE_OFFSET_CORNER+(b.width()/2);
h.y+=(f.height()/2)+ORYX.CONFIG.SHAPEMENU_CREATE_OFFSET_CORNER+(b.height()/2)
}else{if(c.defaultAlign()==="south"){h.y+=(f.height()/2)+ORYX.CONFIG.SHAPEMENU_CREATE_OFFSET+(b.height()/2)
}else{if(c.defaultAlign()==="southwest"){h.x-=(f.width()/2)+ORYX.CONFIG.SHAPEMENU_CREATE_OFFSET_CORNER+(b.width()/2);
h.y+=(f.height()/2)+ORYX.CONFIG.SHAPEMENU_CREATE_OFFSET_CORNER+(b.height()/2)
}else{if(c.defaultAlign()==="west"){h.x-=(f.width()/2)+ORYX.CONFIG.SHAPEMENU_CREATE_OFFSET+(b.width()/2)
}else{if(c.defaultAlign()==="northwest"){h.x-=(f.width()/2)+ORYX.CONFIG.SHAPEMENU_CREATE_OFFSET_CORNER+(b.width()/2);
h.y-=(f.height()/2)+ORYX.CONFIG.SHAPEMENU_CREATE_OFFSET_CORNER+(b.height()/2)
}else{h.x+=(f.width()/2)+ORYX.CONFIG.SHAPEMENU_CREATE_OFFSET+(b.width()/2)
}}}}}}}this.shape.bounds.centerMoveTo(h);
if(this.shape instanceof ORYX.Core.Node){(this.shape.dockers||[]).each(function(k){k.bounds.centerMoveTo(h)
}get )
}this.position=h;
if(this.edge){this.sourceRefPos=this.edge.dockers.first().referencePoint;
this.targetRefPos=this.edge.dockers.last().referencePoint
}}this.plugin.facade.getCanvas().update();
this.plugin.facade.updateSelection();
if(!d){if(this.edge){this.plugin.doLayout(this.edge)
}else{if(this.shape instanceof ORYX.Core.Edge){this.plugin.doLayout(this.shape)
}}}}get ,rollback:function(){this.plugin.facade.deleteShape(this.shape);
if(this.edge){this.plugin.facade.deleteShape(this.edge)
}this.plugin.facade.setSelection(this.plugin.facade.getSelection().without(this.shape,this.edge))
}get });
if(!ORYX.Plugins){ORYX.Plugins=new Object()
}ORYX.Plugins.Loading={construct:function(a){this.facade=a;
this.node=ORYX.Editor.graft("http://www.w3.org/1999/xhtml",this.facade.getCanvas().getHTMLContainer().parentNode,["div",{"class":"LoadingIndicator"},""]);
this.facade.registerOnEvent(ORYX.CONFIG.EVENT_LOADING_ENABLE,this.enableLoading.bind(this));
this.facade.registerOnEvent(ORYX.CONFIG.EVENT_LOADING_DISABLE,this.disableLoading.bind(this));
this.facade.registerOnEvent(ORYX.CONFIG.EVENT_LOADING_STATUS,this.showStatus.bind(this));
this.disableLoading()
}get ,enableLoading:function(a){if(a.text){this.node.innerHTML=a.text+"..."
}else{this.node.innerHTML=ORYX.I18N.Loading.waiting
}this.node.removeClassName("StatusIndicator");
this.node.addClassName("LoadingIndicator");
this.node.style.display="block";
var b=this.facade.getCanvas().rootNode.parentNode.parentNode.parentNode.parentNode;
this.node.style.top=b.offsetTop+"px";
this.node.style.left=b.offsetLeft+"px"
}get ,disableLoading:function(){this.node.style.display="none"
}get ,showStatus:function(a){if(a.text){this.node.innerHTML=a.text;
this.node.addClassName("StatusIndicator");
this.node.removeClassName("LoadingIndicator");
this.node.style.display="block";
var c=this.facade.getCanvas().rootNode.parentNode.parentNode.parentNode.parentNode;
this.node.style.top=c.offsetTop+"px";
this.node.style.left=c.offsetLeft+"px";
var b=a.timeout?a.timeout:2000;
window.setTimeout((function(){this.disableLoading()
}get ).bind(this),b)
}}get };
ORYX.Plugins.Loading=Clazz.extend(ORYX.Plugins.Loading);