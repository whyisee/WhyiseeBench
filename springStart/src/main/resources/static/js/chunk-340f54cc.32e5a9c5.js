(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-340f54cc"],{2017:function(e,o,t){"use strict";var n=t("cafe"),s=t.n(n);s.a},"368c":function(e,o,t){},"420a":function(e,o,t){"use strict";var n=t("368c"),s=t.n(n);s.a},"9ed6":function(e,o,t){"use strict";t.r(o);var n=function(){var e=this,o=e.$createElement,t=e._self._c||o;return t("div",{staticClass:"login-container"},[t("el-form",{ref:"loginForm",staticClass:"login-form",attrs:{model:e.loginForm,rules:e.loginRules,"auto-complete":"on","label-position":"left"}},[t("div",{staticClass:"title-container"},[t("h3",{staticClass:"title"},[e._v("登 录")])]),t("el-form-item",{attrs:{prop:"loginName"}},[t("span",{staticClass:"svg-container"},[t("svg-icon",{attrs:{"icon-class":"user"}})],1),t("el-input",{ref:"loginName",attrs:{placeholder:"Username",name:"loginName",type:"text",tabindex:"1","auto-complete":"on"},model:{value:e.loginForm.loginName,callback:function(o){e.$set(e.loginForm,"loginName",o)},expression:"loginForm.loginName"}})],1),t("el-form-item",{attrs:{prop:"password"}},[t("span",{staticClass:"svg-container"},[t("svg-icon",{attrs:{"icon-class":"password"}})],1),t("el-input",{key:e.passwordType,ref:"password",attrs:{type:e.passwordType,placeholder:"Password",name:"password",tabindex:"2","auto-complete":"on"},nativeOn:{keyup:function(o){return!o.type.indexOf("key")&&e._k(o.keyCode,"enter",13,o.key,"Enter")?null:e.handleLogin(o)}},model:{value:e.loginForm.password,callback:function(o){e.$set(e.loginForm,"password",o)},expression:"loginForm.password"}}),t("span",{staticClass:"show-pwd",on:{click:e.showPwd}},[t("svg-icon",{attrs:{"icon-class":"password"===e.passwordType?"eye":"eye-open"}})],1)],1),t("el-button",{staticStyle:{width:"100%","margin-bottom":"30px"},attrs:{loading:e.loading,type:"primary"},nativeOn:{click:function(o){return o.preventDefault(),e.handleLogin(o)}}},[e._v("Login")])],1)],1)},s=[],a=t("61f7"),r={name:"Login",data:function(){var e=function(e,o,t){Object(a["b"])(o)?t():t(new Error("Please enter the correct user name"))},o=function(e,o,t){o.length<6?t(new Error("The password can not be less than 6 digits")):t()};return{loginForm:{loginName:"admin",password:"123456"},loginRules:{loginName:[{required:!0,trigger:"blur",validator:e}],password:[{required:!0,trigger:"blur",validator:o}]},loading:!1,passwordType:"password",redirect:"/dashboard"}},watch:{$route:{handler:function(e){this.redirect=e.query&&e.query.redirect},immediate:!0}},methods:{showPwd:function(){var e=this;"password"===this.passwordType?this.passwordType="":this.passwordType="password",this.$nextTick((function(){e.$refs.password.focus()}))},handleLogin:function(){var e=this;this.$refs.loginForm.validate((function(o){if(!o)return console.log("error submit!!"),!1;e.loading=!0,e.$store.dispatch("user/login",e.loginForm).then((function(){e.$router.push({path:e.redirect||"/dashboard"}),e.loading=!1})).catch((function(){e.loading=!1}))}))}}},i=r,l=(t("2017"),t("420a"),t("2877")),c=Object(l["a"])(i,n,s,!1,null,"0e05451e",null);o["default"]=c.exports},cafe:function(e,o,t){}}]);