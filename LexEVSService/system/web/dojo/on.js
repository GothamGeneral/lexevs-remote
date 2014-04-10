/*
	Copyright (c) 2004-2011, The Dojo Foundation All Rights Reserved.
	Available via Academic Free License >= 2.1 OR the modified BSD license.
	see: http://dojotoolkit.org/license for details
*/

//>>built
define("dojo/on",["./has!dom-addeventlistener?:./aspect","./_base/kernel","./has"],function(_1,_2,_3){"use strict";if(1){var _4=window.ScriptEngineMajorVersion;_3.add("jscript",_4&&(_4()+ScriptEngineMinorVersion()/10));_3.add("event-orientationchange",_3("touch")&&!_3("android"));}var on=function(_5,_6,_7,_8){if(_5.on){return _5.on(_6,_7);}return on.parse(_5,_6,_7,_9,_8,this);};on.pausable=function(_a,_b,_c,_d){var _e;var _f=on(_a,_b,function(){if(!_e){return _c.apply(this,arguments);}},_d);_f.pause=function(){_e=true;};_f.resume=function(){_e=false;};return _f;};on.once=function(_10,_11,_12,_13){var _14=on(_10,_11,function(){_14.remove();return _12.apply(this,arguments);});return _14;};on.parse=function(_15,_16,_17,_18,_19,_1a){if(_16.call){return _16.call(_1a,_15,_17);}if(_16.indexOf(",")>-1){var _1b=_16.split(/\s*,\s*/);var _1c=[];var i=0;var _1d;while(_1d=_1b[i++]){_1c.push(_18(_15,_1d,_17,_19,_1a));}_1c.remove=function(){for(var i=0;i<_1c.length;i++){_1c[i].remove();}};return _1c;}return _18(_15,_16,_17,_19,_1a);};var _1e=/^touch/;function _9(_1f,_20,_21,_22,_23){var _24=_20.match(/(.*):(.*)/);if(_24){_20=_24[2];_24=_24[1];return on.selector(_24,_20).call(_23,_1f,_21);}if(_3("touch")){if(_1e.test(_20)){_21=_25(_21);}if(!_3("event-orientationchange")&&(_20=="orientationchange")){_20="resize";_1f=window;_21=_25(_21);}}if(_1f.addEventListener){var _26=_20 in _27,_28=_26?_27[_20]:_20;_1f.addEventListener(_28,_21,_26);return {remove:function(){_1f.removeEventListener(_28,_21,_26);}};}_20="on"+_20;if(_29&&_1f.attachEvent){return _29(_1f,_20,_21);}throw new Error("Target must be an event emitter");};on.selector=function(_2a,_2b,_2c){return function(_2d,_2e){var _2f=this;var _30=typeof _2a=="function"?_2a:function _30(_31){_2f=_2f&&_2f.matches?_2f:_2.query;while(!_2f.matches(_31,_2a,_2d)){if(_31==_2d||_2c===false||!(_31=_31.parentNode)||_31.nodeType!=1){return;}}return _31;};var _32=_2b.bubble;if(_32){return on(_2d,_32(_30),_2e);}return on(_2d,_2b,function(_33){var _34=_30(_33.target);return _34&&_2e.call(_34,_33);});};};function _35(){this.cancelable=false;};function _36(){this.bubbles=false;};var _37=[].slice,_38=on.emit=function(_39,_3a,_3b){var _3c=_37.call(arguments,2);var _3d="on"+_3a;if("parentNode" in _39){var _3e=_3c[0]={};for(var i in _3b){_3e[i]=_3b[i];}_3e.preventDefault=_35;_3e.stopPropagation=_36;_3e.target=_39;_3e.type=_3a;_3b=_3e;}do{_39[_3d]&&_39[_3d].apply(_39,_3c);}while(_3b&&_3b.bubbles&&(_39=_39.parentNode));return _3b&&_3b.cancelable&&_3b;};var _27={};if(_3("dom-addeventlistener")){_27={focusin:"focus",focusout:"blur"};if(_3("opera")){_27.keydown="keypress";}on.emit=function(_3f,_40,_41){if(_3f.dispatchEvent&&document.createEvent){var _42=document.createEvent("HTMLEvents");_42.initEvent(_40,!!_41.bubbles,!!_41.cancelable);for(var i in _41){var _43=_41[i];if(!(i in _42)){_42[i]=_41[i];}}return _3f.dispatchEvent(_42)&&_42;}return _38.apply(on,arguments);};}else{on._fixEvent=function(evt,_44){if(!evt){var w=_44&&(_44.ownerDocument||_44.document||_44).parentWindow||window;evt=w.event;}if(!evt){return (evt);}if(!evt.target){evt.target=evt.srcElement;evt.currentTarget=(_44||evt.srcElement);if(evt.type=="mouseover"){evt.relatedTarget=evt.fromElement;}if(evt.type=="mouseout"){evt.relatedTarget=evt.toElement;}if(!evt.stopPropagation){evt.stopPropagation=_45;evt.preventDefault=_46;}switch(evt.type){case "keypress":var c=("charCode" in evt?evt.charCode:evt.keyCode);if(c==10){c=0;evt.keyCode=13;}else{if(c==13||c==27){c=0;}else{if(c==3){c=99;}}}evt.charCode=c;_47(evt);break;}}return evt;};var _48=function(_49){this.handle=_49;};_48.prototype.remove=function(){delete _dojoIEListeners_[this.handle];};var _4a=function(_4b){return function(evt){evt=on._fixEvent(evt,this);return _4b.call(this,evt);};};var _29=function(_4c,_4d,_4e){_4e=_4a(_4e);if(((_4c.ownerDocument?_4c.ownerDocument.parentWindow:_4c.parentWindow||_4c.window||window)!=top||_3("jscript")<5.8)&&!_3("config-_allow_leaks")){if(typeof _dojoIEListeners_=="undefined"){_dojoIEListeners_=[];}var _4f=_4c[_4d];if(!_4f||!_4f.listeners){var _50=_4f;_4c[_4d]=_4f=Function("event","var callee = arguments.callee; for(var i = 0; i<callee.listeners.length; i++){var listener = _dojoIEListeners_[callee.listeners[i]]; if(listener){listener.call(this,event);}}");_4f.listeners=[];_4f.global=this;if(_50){_4f.listeners.push(_dojoIEListeners_.push(_50)-1);}}var _51;_4f.listeners.push(_51=(_4f.global._dojoIEListeners_.push(_4e)-1));return new _48(_51);}return _1.after(_4c,_4d,_4e,true);};var _47=function(evt){evt.keyChar=evt.charCode?String.fromCharCode(evt.charCode):"";evt.charOrCode=evt.keyChar||evt.keyCode;};var _45=function(){this.cancelBubble=true;};var _46=on._preventDefault=function(){this.bubbledKeyCode=this.keyCode;if(this.ctrlKey){try{this.keyCode=0;}catch(e){}}this.defaultPrevented=true;this.returnValue=false;};}if(_3("touch")){var _52=function(){};var _53=window.orientation;var _25=function(_54){return function(_55){var _56=_55.corrected;if(!_56){var _57=_55.type;try{delete _55.type;}catch(e){}if(_55.type){_52.prototype=_55;var _56=new _52;_56.preventDefault=function(){_55.preventDefault();};_56.stopPropagation=function(){_55.stopPropagation();};}else{_56=_55;_56.type=_57;}_55.corrected=_56;if(_57=="resize"){if(_53==window.orientation){return null;}_53=window.orientation;_56.type="orientationchange";return _54.call(this,_56);}if(!("rotation" in _56)){_56.rotation=0;_56.scale=1;}var _58=_56.changedTouches[0];for(var i in _58){delete _56[i];_56[i]=_58[i];}}return _54.call(this,_56);};};}return on;});