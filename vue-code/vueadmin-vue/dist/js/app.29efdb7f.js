(function(e){function t(t){for(var o,r,i=t[0],s=t[1],u=t[2],c=0,d=[];c<i.length;c++)r=i[c],Object.prototype.hasOwnProperty.call(n,r)&&n[r]&&d.push(n[r][0]),n[r]=0;for(o in s)Object.prototype.hasOwnProperty.call(s,o)&&(e[o]=s[o]);m&&m(t);while(d.length)d.shift()();return l.push.apply(l,u||[]),a()}function a(){for(var e,t=0;t<l.length;t++){for(var a=l[t],o=!0,r=1;r<a.length;r++){var i=a[r];0!==n[i]&&(o=!1)}o&&(l.splice(t--,1),e=s(s.s=a[0]))}return e}var o={},r={app:0},n={app:0},l=[];function i(e){return s.p+"js/"+({}[e]||e)+"."+{"chunk-09953c79":"dbe941b8","chunk-0d59d52a":"0e7f9258","chunk-18a3ebe8":"a3f85e9a","chunk-6b5651b9":"8ded6c9d","chunk-c61a80e8":"7ca47f6d","chunk-f3c76ad4":"93b8450b"}[e]+".js"}function s(t){if(o[t])return o[t].exports;var a=o[t]={i:t,l:!1,exports:{}};return e[t].call(a.exports,a,a.exports,s),a.l=!0,a.exports}s.e=function(e){var t=[],a={"chunk-09953c79":1,"chunk-0d59d52a":1,"chunk-18a3ebe8":1,"chunk-6b5651b9":1,"chunk-c61a80e8":1,"chunk-f3c76ad4":1};r[e]?t.push(r[e]):0!==r[e]&&a[e]&&t.push(r[e]=new Promise((function(t,a){for(var o="css/"+({}[e]||e)+"."+{"chunk-09953c79":"0ca22599","chunk-0d59d52a":"6558cc43","chunk-18a3ebe8":"f3285dab","chunk-6b5651b9":"cff10d2f","chunk-c61a80e8":"d730971f","chunk-f3c76ad4":"4faf29af"}[e]+".css",n=s.p+o,l=document.getElementsByTagName("link"),i=0;i<l.length;i++){var u=l[i],c=u.getAttribute("data-href")||u.getAttribute("href");if("stylesheet"===u.rel&&(c===o||c===n))return t()}var d=document.getElementsByTagName("style");for(i=0;i<d.length;i++){u=d[i],c=u.getAttribute("data-href");if(c===o||c===n)return t()}var m=document.createElement("link");m.rel="stylesheet",m.type="text/css",m.onload=t,m.onerror=function(t){var o=t&&t.target&&t.target.src||n,l=new Error("Loading CSS chunk "+e+" failed.\n("+o+")");l.code="CSS_CHUNK_LOAD_FAILED",l.request=o,delete r[e],m.parentNode.removeChild(m),a(l)},m.href=n;var f=document.getElementsByTagName("head")[0];f.appendChild(m)})).then((function(){r[e]=0})));var o=n[e];if(0!==o)if(o)t.push(o[2]);else{var l=new Promise((function(t,a){o=n[e]=[t,a]}));t.push(o[2]=l);var u,c=document.createElement("script");c.charset="utf-8",c.timeout=120,s.nc&&c.setAttribute("nonce",s.nc),c.src=i(e);var d=new Error;u=function(t){c.onerror=c.onload=null,clearTimeout(m);var a=n[e];if(0!==a){if(a){var o=t&&("load"===t.type?"missing":t.type),r=t&&t.target&&t.target.src;d.message="Loading chunk "+e+" failed.\n("+o+": "+r+")",d.name="ChunkLoadError",d.type=o,d.request=r,a[1](d)}n[e]=void 0}};var m=setTimeout((function(){u({type:"timeout",target:c})}),12e4);c.onerror=c.onload=u,document.head.appendChild(c)}return Promise.all(t)},s.m=e,s.c=o,s.d=function(e,t,a){s.o(e,t)||Object.defineProperty(e,t,{enumerable:!0,get:a})},s.r=function(e){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(e,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(e,"__esModule",{value:!0})},s.t=function(e,t){if(1&t&&(e=s(e)),8&t)return e;if(4&t&&"object"===typeof e&&e&&e.__esModule)return e;var a=Object.create(null);if(s.r(a),Object.defineProperty(a,"default",{enumerable:!0,value:e}),2&t&&"string"!=typeof e)for(var o in e)s.d(a,o,function(t){return e[t]}.bind(null,o));return a},s.n=function(e){var t=e&&e.__esModule?function(){return e["default"]}:function(){return e};return s.d(t,"a",t),t},s.o=function(e,t){return Object.prototype.hasOwnProperty.call(e,t)},s.p="/",s.oe=function(e){throw console.error(e),e};var u=window["webpackJsonp"]=window["webpackJsonp"]||[],c=u.push.bind(u);u.push=t,u=u.slice();for(var d=0;d<u.length;d++)t(u[d]);var m=c;l.push([0,"chunk-vendors"]),a()})({0:function(e,t,a){e.exports=a("56d7")},"034f":function(e,t,a){"use strict";a("85ec")},"0836":function(e,t,a){"use strict";a.r(t);var o=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("el-menu",{staticClass:"el-menu-vertical-demo",attrs:{"default-active":this.$store.state.menus.editableTabsValue,"background-color":"#545c64","text-color":"#fff","active-text-color":"#ffd04b"}},[a("router-link",{attrs:{to:"/index"}},[a("el-menu-item",{attrs:{index:"Index"},on:{click:function(t){return e.selectMenu({name:"Index",title:"首页"})}}},[a("template",{slot:"title"},[a("i",{staticClass:"el-icon-s-home"}),a("span",{attrs:{slot:"title"},slot:"title"},[e._v("首页")])])],2)],1),e._l(e.menuList,(function(t){return a("el-submenu",{attrs:{index:t.name}},[a("template",{slot:"title"},[a("i",{class:t.icon}),a("span",[e._v(e._s(t.title))])]),e._l(t.children,(function(t){return a("router-link",{attrs:{to:t.path}},[a("el-menu-item",{attrs:{index:t.name},on:{click:function(a){return e.selectMenu(t)}}},[a("template",{slot:"title"},[a("i",{class:t.icon}),a("span",{attrs:{slot:"title"},slot:"title"},[e._v(e._s(t.title))])])],2)],1)}))],2)}))],2)},r=[],n={name:"SideMenu",data:function(){return{}},computed:{menuList:{get:function(){return this.$store.state.menus.menuList}}},methods:{selectMenu:function(e){this.$store.commit("addTab",e)}}},l=n,i=(a("55d9"),a("2877")),s=Object(i["a"])(l,o,r,!1,null,"28c93edc",null);t["default"]=s.exports},"0cd2":function(e,t,a){},"12ab":function(e,t,a){},"16ae":function(e,t,a){"use strict";a.r(t);var o=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("el-tabs",{attrs:{type:"card",closable:""},on:{"tab-remove":e.removeTab,"tab-click":e.clickTab},model:{value:e.editableTabsValue,callback:function(t){e.editableTabsValue=t},expression:"editableTabsValue"}},e._l(e.editableTabs,(function(e,t){return a("el-tab-pane",{key:e.name,attrs:{label:e.title,name:e.name}})})),1)},r=[],n=(a("4de4"),a("4160"),a("b0c0"),a("159b"),{name:"Tabs",data:function(){return{}},computed:{editableTabs:{get:function(){return this.$store.state.menus.editableTabs},set:function(e){this.$store.state.menus.editableTabs=e}},editableTabsValue:{get:function(){return this.$store.state.menus.editableTabsValue},set:function(e){this.$store.state.menus.editableTabsValue=e}}},methods:{removeTab:function(e){var t=this.editableTabs,a=this.editableTabsValue;"Index"!==a&&(a===e&&t.forEach((function(o,r){if(o.name===e){var n=t[r+1]||t[r-1];n&&(a=n.name)}})),this.editableTabsValue=a,this.editableTabs=t.filter((function(t){return t.name!==e})),this.$router.push({name:a}))},clickTab:function(e){this.$router.push({name:e.name})}}}),l=n,i=a("2877"),s=Object(i["a"])(l,o,r,!1,null,"435bce88",null);t["default"]=s.exports},"1a5d":function(e,t,a){var o={"./Home.vue":["bb51"],"./Index.vue":["d504"],"./Login.vue":["a55b","chunk-09953c79"],"./UserCenter.vue":["6494","chunk-6b5651b9"],"./api/ApiList.vue":["f83f","chunk-18a3ebe8"],"./inc/SideMenu.vue":["0836"],"./inc/Tabs.vue":["16ae"],"./model/Field.vue":["01fb","chunk-f3c76ad4"],"./model/FieldTree.vue":["3afc","chunk-c61a80e8"],"./model/Model.vue":["070a","chunk-0d59d52a"],"./sys/Menu.vue":["e3c9"],"./sys/Role.vue":["7ad5"],"./sys/User.vue":["45d1"]};function r(e){if(!a.o(o,e))return Promise.resolve().then((function(){var t=new Error("Cannot find module '"+e+"'");throw t.code="MODULE_NOT_FOUND",t}));var t=o[e],r=t[0];return Promise.all(t.slice(1).map(a.e)).then((function(){return a(r)}))}r.keys=function(){return Object.keys(o)},r.id="1a5d",e.exports=r},"29bd":function(e,t,a){"use strict";a("7155")},4360:function(e,t,a){"use strict";var o=a("2b0e"),r=a("2f62");a("c740"),a("b0c0");o["default"].use(r["a"]);var n={state:{menuList:[],permList:[],hasRoutes:!1,editableTabsValue:"Index",editableTabs:[{title:"首页",name:"Index"}]},mutations:{setMenuList:function(e,t){e.menuList=t},setPermList:function(e,t){e.permList=t},changeRouteStatus:function(e,t){e.hasRoutes=t},addTab:function(e,t){var a=e.editableTabs.findIndex((function(e){return e.name===t.name}));-1===a&&e.editableTabs.push({title:t.title,name:t.name}),e.editableTabsValue=t.name},resetState:function(e){e.menuList=[],e.permList=[],e.hasRoutes=!1,e.editableTabsValue="Index",e.editableTabs=[{title:"首页",name:"Index"}]}},actions:{}};o["default"].use(r["a"]);t["a"]=new r["a"].Store({state:{token:""},mutations:{SET_TOKEN:function(e,t){e.token=t,localStorage.setItem("token",t)}},actions:{},modules:{menus:n}})},"45d1":function(e,t,a){"use strict";a.r(t);var o=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("el-form",{attrs:{inline:!0}},[a("el-form-item",[a("el-input",{attrs:{placeholder:"用户名",clearable:""},model:{value:e.searchForm.username,callback:function(t){e.$set(e.searchForm,"username",t)},expression:"searchForm.username"}})],1),a("el-form-item",[a("el-button",{on:{click:e.getUserList}},[e._v("搜索")])],1),a("el-form-item",[e.hasAuth("sys:user:save")?a("el-button",{attrs:{type:"primary"},on:{click:function(t){e.dialogVisible=!0}}},[e._v("新增")]):e._e()],1),a("el-form-item",[a("el-popconfirm",{attrs:{title:"这是确定批量删除吗？"},on:{confirm:function(t){return e.delHandle(null)}}},[e.hasAuth("sys:user:delete")?a("el-button",{attrs:{slot:"reference",type:"danger",disabled:e.delBtlStatu},slot:"reference"},[e._v("批量删除")]):e._e()],1)],1)],1),a("el-table",{ref:"multipleTable",staticStyle:{width:"100%"},attrs:{data:e.tableData,"tooltip-effect":"dark",border:"",stripe:""},on:{"selection-change":e.handleSelectionChange}},[a("el-table-column",{attrs:{type:"selection",width:"55"}}),a("el-table-column",{attrs:{label:"头像",width:"50"},scopedSlots:e._u([{key:"default",fn:function(e){return[a("el-avatar",{attrs:{size:"small",src:e.row.avatar}})]}}])}),a("el-table-column",{attrs:{prop:"username",label:"用户名",width:"120"}}),a("el-table-column",{attrs:{prop:"code",label:"角色名称"},scopedSlots:e._u([{key:"default",fn:function(t){return e._l(t.row.sysRoles,(function(t){return a("el-tag",{attrs:{size:"small",type:"info"}},[e._v(e._s(t.name))])}))}}])}),a("el-table-column",{attrs:{prop:"email",label:"邮箱"}}),a("el-table-column",{attrs:{prop:"phone",label:"手机号"}}),a("el-table-column",{attrs:{prop:"statu",label:"状态"},scopedSlots:e._u([{key:"default",fn:function(t){return[1===t.row.statu?a("el-tag",{attrs:{size:"small",type:"success"}},[e._v("正常")]):0===t.row.statu?a("el-tag",{attrs:{size:"small",type:"danger"}},[e._v("禁用")]):e._e()]}}])}),a("el-table-column",{attrs:{prop:"created",width:"200",label:"创建时间"}}),a("el-table-column",{attrs:{prop:"icon",width:"260px",label:"操作"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-button",{attrs:{type:"text"},on:{click:function(a){return e.roleHandle(t.row.id)}}},[e._v("分配角色")]),a("el-divider",{attrs:{direction:"vertical"}}),a("el-button",{attrs:{type:"text"},on:{click:function(a){return e.repassHandle(t.row.id,t.row.username)}}},[e._v("重置密码")]),a("el-divider",{attrs:{direction:"vertical"}}),a("el-button",{attrs:{type:"text"},on:{click:function(a){return e.editHandle(t.row.id)}}},[e._v("编辑")]),a("el-divider",{attrs:{direction:"vertical"}}),[a("el-popconfirm",{attrs:{title:"这是一段内容确定删除吗？"},on:{confirm:function(a){return e.delHandle(t.row.id)}}},[a("el-button",{attrs:{slot:"reference",type:"text"},slot:"reference"},[e._v("删除")])],1)]]}}])})],1),a("el-pagination",{attrs:{layout:"total, sizes, prev, pager, next, jumper","page-sizes":[10,20,50,100],"current-page":e.current,"page-size":e.size,total:e.total},on:{"size-change":e.handleSizeChange,"current-change":e.handleCurrentChange}}),a("el-dialog",{attrs:{title:"提示",visible:e.dialogVisible,width:"600px","before-close":e.handleClose},on:{"update:visible":function(t){e.dialogVisible=t}}},[a("el-form",{ref:"editForm",attrs:{model:e.editForm,rules:e.editFormRules}},[a("el-form-item",{attrs:{label:"用户名",prop:"username","label-width":"100px"}},[a("el-input",{attrs:{autocomplete:"off"},model:{value:e.editForm.username,callback:function(t){e.$set(e.editForm,"username",t)},expression:"editForm.username"}}),a("el-alert",{staticStyle:{"line-height":"12px"},attrs:{title:"初始密码为888888",closable:!1,type:"info"}})],1),a("el-form-item",{attrs:{label:"邮箱",prop:"email","label-width":"100px"}},[a("el-input",{attrs:{autocomplete:"off"},model:{value:e.editForm.email,callback:function(t){e.$set(e.editForm,"email",t)},expression:"editForm.email"}})],1),a("el-form-item",{attrs:{label:"手机号",prop:"phone","label-width":"100px"}},[a("el-input",{attrs:{autocomplete:"off"},model:{value:e.editForm.phone,callback:function(t){e.$set(e.editForm,"phone",t)},expression:"editForm.phone"}})],1),a("el-form-item",{attrs:{label:"状态",prop:"statu","label-width":"100px"}},[a("el-radio-group",{model:{value:e.editForm.statu,callback:function(t){e.$set(e.editForm,"statu",t)},expression:"editForm.statu"}},[a("el-radio",{attrs:{label:0}},[e._v("禁用")]),a("el-radio",{attrs:{label:1}},[e._v("正常")])],1)],1)],1),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(t){return e.resetForm("editForm")}}},[e._v("取 消")]),a("el-button",{attrs:{type:"primary"},on:{click:function(t){return e.submitForm("editForm")}}},[e._v("确 定")])],1)],1),a("el-dialog",{attrs:{title:"分配角色",visible:e.roleDialogFormVisible,width:"600px"},on:{"update:visible":function(t){e.roleDialogFormVisible=t}}},[a("el-form",{attrs:{model:e.roleForm}},[a("el-tree",{ref:"roleTree",attrs:{data:e.roleTreeData,"show-checkbox":"","check-strictly":e.checkStrictly,"node-key":"id","default-expand-all":!0,props:e.defaultProps}})],1),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(t){e.roleDialogFormVisible=!1}}},[e._v("取 消")]),a("el-button",{attrs:{type:"primary"},on:{click:function(t){return e.submitRoleHandle("roleForm")}}},[e._v("确 定")])],1)],1)],1)},r=[],n=(a("4160"),a("159b"),{name:"Role",data:function(){return{searchForm:{},delBtlStatu:!0,total:0,size:10,current:1,dialogVisible:!1,editForm:{},tableData:[],editFormRules:{username:[{required:!0,message:"请输入用户名称",trigger:"blur"}],email:[{required:!0,message:"请输入邮箱",trigger:"blur"}],statu:[{required:!0,message:"请选择状态",trigger:"blur"}]},multipleSelection:[],roleDialogFormVisible:!1,defaultProps:{children:"children",label:"name"},roleForm:{},roleTreeData:[],treeCheckedKeys:[],checkStrictly:!0}},created:function(){var e=this;this.getUserList(),this.$axios.get("/sys/role/list").then((function(t){e.roleTreeData=t.data.data.records}))},methods:{toggleSelection:function(e){var t=this;e?e.forEach((function(e){t.$refs.multipleTable.toggleRowSelection(e)})):this.$refs.multipleTable.clearSelection()},handleSelectionChange:function(e){console.log("勾选"),console.log(e),this.multipleSelection=e,this.delBtlStatu=0==e.length},handleSizeChange:function(e){console.log("每页 ".concat(e," 条")),this.size=e,this.getUserList()},handleCurrentChange:function(e){console.log("当前页: ".concat(e)),this.current=e,this.getUserList()},resetForm:function(e){this.$refs[e].resetFields(),this.dialogVisible=!1,this.editForm={}},handleClose:function(){this.resetForm("editForm")},getUserList:function(){var e=this;this.$axios.get("/sys/user/list",{params:{username:this.searchForm.username,current:this.current,size:this.size}}).then((function(t){e.tableData=t.data.data.records,e.size=t.data.data.size,e.current=t.data.data.current,e.total=t.data.data.total}))},submitForm:function(e){var t=this;this.$refs[e].validate((function(e){if(!e)return console.log("error submit!!"),!1;t.$axios.post("/sys/user/"+(t.editForm.id?"update":"save"),t.editForm).then((function(e){t.$message({showClose:!0,message:"恭喜你，操作成功",type:"success",onClose:function(){t.getUserList()}}),t.dialogVisible=!1}))}))},editHandle:function(e){var t=this;this.$axios.get("/sys/user/info/"+e).then((function(e){t.editForm=e.data.data,t.dialogVisible=!0}))},delHandle:function(e){var t=this,a=[];e?a.push(e):this.multipleSelection.forEach((function(e){a.push(e.id)})),console.log(a),this.$axios.post("/sys/user/delete",a).then((function(e){t.$message({showClose:!0,message:"恭喜你，操作成功",type:"success",onClose:function(){t.getUserList()}})}))},roleHandle:function(e){var t=this;this.roleDialogFormVisible=!0,this.$axios.get("/sys/user/info/"+e).then((function(e){t.roleForm=e.data.data;var a=[];e.data.data.sysRoles.forEach((function(e){a.push(e.id)})),t.$refs.roleTree.setCheckedKeys(a)}))},submitRoleHandle:function(e){var t=this,a=this.$refs.roleTree.getCheckedKeys();console.log(a),this.$axios.post("/sys/user/role/"+this.roleForm.id,a).then((function(e){t.$message({showClose:!0,message:"恭喜你，操作成功",type:"success",onClose:function(){t.getUserList()}}),t.roleDialogFormVisible=!1}))},repassHandle:function(e,t){var a=this;this.$confirm("将重置用户【"+t+"】的密码, 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((function(){a.$axios.post("/sys/user/repass",e).then((function(e){a.$message({showClose:!0,message:"恭喜你，操作成功",type:"success",onClose:function(){}})}))}))}}}),l=n,i=(a("7fcb"),a("2877")),s=Object(i["a"])(l,o,r,!1,null,"6871a9c9",null);t["default"]=s.exports},"55d9":function(e,t,a){"use strict";a("12ab")},"56d7":function(e,t,a){"use strict";a.r(t);a("e260"),a("e6cf"),a("cca6"),a("a79d");var o=a("2b0e"),r=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{attrs:{id:"app"}},[a("router-view")],1)},n=[],l=(a("b0c0"),{name:"App",watch:{$route:function(e,t){if(console.log("to"),console.log(e),"/login"!=e.path){var a={name:e.name,title:e.meta.title};this.$store.commit("addTab",a)}}}}),i=l,s=(a("034f"),a("2877")),u=Object(s["a"])(i,r,n,!1,null,null,null),c=u.exports,d=a("a18c"),m=a("4360"),f=a("5c96"),p=a.n(f),h=(a("0fae"),a("a27e"));a("c975");o["default"].mixin({methods:{hasAuth:function(e){var t=this.$store.state.menus.permList;return console.log(t),t.indexOf(e)>-1}}}),o["default"].prototype.$axios=h["a"],o["default"].config.productionTip=!1,o["default"].use(p.a),new o["default"]({router:d["a"],store:m["a"],render:function(e){return e(c)}}).$mount("#app")},7155:function(e,t,a){},"7ad5":function(e,t,a){"use strict";a.r(t);var o=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("el-form",{attrs:{inline:!0}},[a("el-form-item",[a("el-input",{attrs:{placeholder:"名称",clearable:""},model:{value:e.searchForm.name,callback:function(t){e.$set(e.searchForm,"name",t)},expression:"searchForm.name"}})],1),a("el-form-item",[a("el-button",{on:{click:e.getRoleList}},[e._v("搜索")])],1),a("el-form-item",[a("el-button",{attrs:{type:"primary"},on:{click:function(t){e.dialogVisible=!0}}},[e._v("新增")])],1),a("el-form-item",[a("el-popconfirm",{attrs:{title:"这是确定批量删除吗？"},on:{confirm:function(t){return e.delHandle(null)}}},[a("el-button",{attrs:{slot:"reference",type:"danger",disabled:e.delBtlStatu},slot:"reference"},[e._v("批量删除")])],1)],1)],1),a("el-table",{ref:"multipleTable",staticStyle:{width:"100%"},attrs:{data:e.tableData,"tooltip-effect":"dark",border:"",stripe:""},on:{"selection-change":e.handleSelectionChange}},[a("el-table-column",{attrs:{type:"selection",width:"55"}}),a("el-table-column",{attrs:{prop:"name",label:"名称",width:"120"}}),a("el-table-column",{attrs:{prop:"code",label:"唯一编码","show-overflow-tooltip":""}}),a("el-table-column",{attrs:{prop:"remark",label:"描述","show-overflow-tooltip":""}}),a("el-table-column",{attrs:{prop:"statu",label:"状态"},scopedSlots:e._u([{key:"default",fn:function(t){return[1===t.row.statu?a("el-tag",{attrs:{size:"small",type:"success"}},[e._v("正常")]):0===t.row.statu?a("el-tag",{attrs:{size:"small",type:"danger"}},[e._v("禁用")]):e._e()]}}])}),a("el-table-column",{attrs:{prop:"icon",label:"操作"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-button",{attrs:{type:"text"},on:{click:function(a){return e.permHandle(t.row.id)}}},[e._v("分配权限")]),a("el-divider",{attrs:{direction:"vertical"}}),a("el-button",{attrs:{type:"text"},on:{click:function(a){return e.editHandle(t.row.id)}}},[e._v("编辑")]),a("el-divider",{attrs:{direction:"vertical"}}),[a("el-popconfirm",{attrs:{title:"这是一段内容确定删除吗？"},on:{confirm:function(a){return e.delHandle(t.row.id)}}},[a("el-button",{attrs:{slot:"reference",type:"text"},slot:"reference"},[e._v("删除")])],1)]]}}])})],1),a("el-pagination",{attrs:{layout:"total, sizes, prev, pager, next, jumper","page-sizes":[10,20,50,100],"current-page":e.current,"page-size":e.size,total:e.total},on:{"size-change":e.handleSizeChange,"current-change":e.handleCurrentChange}}),a("el-dialog",{attrs:{title:"提示",visible:e.dialogVisible,width:"600px","before-close":e.handleClose},on:{"update:visible":function(t){e.dialogVisible=t}}},[a("el-form",{ref:"editForm",staticClass:"demo-editForm",attrs:{model:e.editForm,rules:e.editFormRules,"label-width":"100px"}},[a("el-form-item",{attrs:{label:"角色名称",prop:"name","label-width":"100px"}},[a("el-input",{attrs:{autocomplete:"off"},model:{value:e.editForm.name,callback:function(t){e.$set(e.editForm,"name",t)},expression:"editForm.name"}})],1),a("el-form-item",{attrs:{label:"唯一编码",prop:"code","label-width":"100px"}},[a("el-input",{attrs:{autocomplete:"off"},model:{value:e.editForm.code,callback:function(t){e.$set(e.editForm,"code",t)},expression:"editForm.code"}})],1),a("el-form-item",{attrs:{label:"描述",prop:"remark","label-width":"100px"}},[a("el-input",{attrs:{autocomplete:"off"},model:{value:e.editForm.remark,callback:function(t){e.$set(e.editForm,"remark",t)},expression:"editForm.remark"}})],1),a("el-form-item",{attrs:{label:"状态",prop:"statu","label-width":"100px"}},[a("el-radio-group",{model:{value:e.editForm.statu,callback:function(t){e.$set(e.editForm,"statu",t)},expression:"editForm.statu"}},[a("el-radio",{attrs:{label:0}},[e._v("禁用")]),a("el-radio",{attrs:{label:1}},[e._v("正常")])],1)],1),a("el-form-item",[a("el-button",{attrs:{type:"primary"},on:{click:function(t){return e.submitForm("editForm")}}},[e._v("立即创建")]),a("el-button",{on:{click:function(t){return e.resetForm("editForm")}}},[e._v("重置")])],1)],1)],1),a("el-dialog",{attrs:{title:"分配权限",visible:e.permDialogVisible,width:"600px"},on:{"update:visible":function(t){e.permDialogVisible=t}}},[a("el-form",{attrs:{model:e.permForm}},[a("el-tree",{ref:"permTree",attrs:{data:e.permTreeData,"show-checkbox":"","default-expand-all":!0,"node-key":"id","check-strictly":!0,props:e.defaultProps}})],1),a("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(t){e.permDialogVisible=!1}}},[e._v("取 消")]),a("el-button",{attrs:{type:"primary"},on:{click:function(t){return e.submitPermFormHandle("permForm")}}},[e._v("确 定")])],1)],1)],1)},r=[],n=(a("4160"),a("b0c0"),a("159b"),{name:"Role",data:function(){return{searchForm:{},delBtlStatu:!0,total:0,size:10,current:1,dialogVisible:!1,editForm:{},tableData:[],editFormRules:{name:[{required:!0,message:"请输入角色名称",trigger:"blur"}],code:[{required:!0,message:"请输入唯一编码",trigger:"blur"}],statu:[{required:!0,message:"请选择状态",trigger:"blur"}]},multipleSelection:[],permDialogVisible:!1,permForm:{},defaultProps:{children:"children",label:"name"},permTreeData:[]}},created:function(){var e=this;this.getRoleList(),this.$axios.get("/sys/menu/list").then((function(t){e.permTreeData=t.data.data}))},methods:{toggleSelection:function(e){var t=this;e?e.forEach((function(e){t.$refs.multipleTable.toggleRowSelection(e)})):this.$refs.multipleTable.clearSelection()},handleSelectionChange:function(e){console.log("勾选"),console.log(e),this.multipleSelection=e,this.delBtlStatu=0==e.length},handleSizeChange:function(e){console.log("每页 ".concat(e," 条")),this.size=e,this.getRoleList()},handleCurrentChange:function(e){console.log("当前页: ".concat(e)),this.current=e,this.getRoleList()},resetForm:function(e){this.$refs[e].resetFields(),this.dialogVisible=!1,this.editForm={}},handleClose:function(){this.resetForm("editForm")},getRoleList:function(){var e=this;this.$axios.get("/sys/role/list",{params:{name:this.searchForm.name,current:this.current,size:this.size}}).then((function(t){e.tableData=t.data.data.records,e.size=t.data.data.size,e.current=t.data.data.current,e.total=t.data.data.total}))},submitForm:function(e){var t=this;this.$refs[e].validate((function(a){if(!a)return console.log("error submit!!"),!1;t.$axios.post("/sys/role/"+(t.editForm.id?"update":"save"),t.editForm).then((function(a){t.$message({showClose:!0,message:"恭喜你，操作成功",type:"success",onClose:function(){t.getRoleList()}}),t.dialogVisible=!1,t.resetForm(e)}))}))},editHandle:function(e){var t=this;this.$axios.get("/sys/role/info/"+e).then((function(e){t.editForm=e.data.data,t.dialogVisible=!0}))},delHandle:function(e){var t=this,a=[];e?a.push(e):this.multipleSelection.forEach((function(e){a.push(e.id)})),console.log(a),this.$axios.post("/sys/role/delete",a).then((function(e){t.$message({showClose:!0,message:"恭喜你，操作成功",type:"success",onClose:function(){t.getRoleList()}})}))},permHandle:function(e){var t=this;this.permDialogVisible=!0,this.$axios.get("/sys/role/info/"+e).then((function(e){t.$refs.permTree.setCheckedKeys(e.data.data.menuIds),t.permForm=e.data.data}))},submitPermFormHandle:function(e){var t=this,a=this.$refs.permTree.getCheckedKeys();console.log(a),this.$axios.post("/sys/role/perm/"+this.permForm.id,a).then((function(a){t.$message({showClose:!0,message:"恭喜你，操作成功",type:"success",onClose:function(){t.getRoleList()}}),t.permDialogVisible=!1,t.resetForm(e)}))}}}),l=n,i=(a("e539"),a("2877")),s=Object(i["a"])(l,o,r,!1,null,"745364b8",null);t["default"]=s.exports},"7fcb":function(e,t,a){"use strict";a("0cd2")},"85ec":function(e,t,a){},a18c:function(e,t,a){"use strict";a("4160"),a("b0c0"),a("d3b7"),a("159b");var o=a("2b0e"),r=a("8c4f"),n=a("bb51"),l=a("d504"),i=(a("45d1"),a("7ad5"),a("e3c9"),a("a27e")),s=a("4360");a("f395");o["default"].use(r["a"]);var u=[{path:"/",name:"Home",component:n["default"],children:[{path:"/index",name:"Index",meta:{title:"首页"},component:l["default"]},{path:"/userCenter",name:"UserCenter",meta:{title:"个人中心"},component:function(){return a.e("chunk-6b5651b9").then(a.bind(null,"6494"))}}]},{path:"/login",name:"Login",component:function(){return a.e("chunk-09953c79").then(a.bind(null,"a55b"))}}],c=new r["a"]({mode:"history",base:"/",routes:u});c.beforeEach((function(e,t,a){var o=s["a"].state.menus.hasRoutes,r=localStorage.getItem("token");"/login"==e.path?a():r?r&&!o&&i["a"].get("/sys/menu/nav",{headers:{Authorization:localStorage.getItem("token")}}).then((function(e){console.log(e.data.data),s["a"].commit("setMenuList",e.data.data.nav),s["a"].commit("setPermList",e.data.data.authoritys),console.log(s["a"].state.menus.menuList);var t=c.options.routes;e.data.data.nav.forEach((function(e){e.children&&e.children.forEach((function(e){var a=d(e);a&&t[0].children.push(a)}))})),console.log("newRoutes"),console.log(t),c.addRoutes(t),o=!0,s["a"].commit("changeRouteStatus",o)})):a({path:"/login"}),a()}));var d=function(e){if(!e.component)return null;var t={name:e.name,path:e.path,meta:{icon:e.icon,title:e.title},component:function(){return a("1a5d")("./"+e.component+".vue")}};return console.log(t),t};t["a"]=c},a27e:function(e,t,a){"use strict";a("d3b7");var o=a("bc3a"),r=a.n(o),n=a("a18c"),l=a("5c96"),i=a.n(l);r.a.defaults.baseURL="http://localhost:8081";var s=r.a.create({timeout:5e3,headers:{"Content-Type":"application/json; charset=utf-8"}});s.interceptors.request.use((function(e){return e.headers["Authorization"]=localStorage.getItem("token"),e})),s.interceptors.response.use((function(e){console.log("response ->"+e);var t=e.data;return 200===t.code?e:(i.a.Message.error(t.msg?t.msg:"系统异常"),Promise.reject(e.data.msg))}),(function(e){return console.log(e),e.response.data&&(e.massage=e.response.data.msg),401===e.response.status&&n["a"].push("/login"),i.a.Message.error(e.massage,{duration:3e3}),Promise.reject(e)})),t["a"]=s},bb51:function(e,t,a){"use strict";a.r(t);var o=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("el-container",[a("el-aside",{attrs:{width:"200px"}},[a("SideMenu")],1),a("el-container",[a("el-header",[a("strong",[e._v("HEPS数据管理系统")]),a("div",{staticClass:"header-avatar"},[a("el-avatar",{attrs:{size:"medium",src:e.userInfo.avatar}}),a("el-dropdown",[a("span",{staticClass:"el-dropdown-link"},[e._v(" "+e._s(e.userInfo.username)),a("i",{staticClass:"el-icon-arrow-down el-icon--right"})]),a("el-dropdown-menu",{attrs:{slot:"dropdown"},slot:"dropdown"},[a("el-dropdown-item",[a("router-link",{attrs:{to:{name:"UserCenter"}}},[e._v("个人中心")])],1),a("el-dropdown-item",{nativeOn:{click:function(t){return e.logout(t)}}},[e._v("退出")])],1)],1)],1)]),a("el-main",[a("Tabs"),a("div",{staticStyle:{margin:"0 15px"}},[a("router-view")],1)],1)],1)],1)},r=[],n=a("0836"),l=a("16ae"),i={name:"Home",components:{SideMenu:n["default"],Tabs:l["default"]},data:function(){return{userInfo:{id:"",username:"",avatar:""}}},created:function(){this.getUserInfo()},methods:{getUserInfo:function(){var e=this;this.$axios.get("/sys/userInfo").then((function(t){e.userInfo=t.data.data}))},logout:function(){var e=this;this.$axios.post("/logout").then((function(t){localStorage.clear(),sessionStorage.clear(),e.$store.commit("resetState"),e.$router.push("/login")}))}}},s=i,u=(a("29bd"),a("2877")),c=Object(u["a"])(s,o,r,!1,null,"352d0d68",null);t["default"]=c.exports},d504:function(e,t,a){"use strict";a.r(t);var o=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[e._v("main")])},r=[],n={name:"Index"},l=n,i=a("2877"),s=Object(i["a"])(l,o,r,!1,null,"40533328",null);t["default"]=s.exports},e3c9:function(e,t,a){"use strict";a.r(t);var o=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("el-form",{attrs:{inline:!0}},[a("el-form-item",[a("el-button",{attrs:{type:"primary"},on:{click:function(t){e.dialogVisible=!0}}},[e._v("新增")])],1)],1),a("el-table",{staticStyle:{width:"100%","margin-bottom":"20px"},attrs:{data:e.tableData,"row-key":"id",border:"",stripe:"","default-expand-all":"","tree-props":{children:"children",hasChildren:"hasChildren"}}},[a("el-table-column",{attrs:{prop:"name",label:"名称",sortable:"",width:"180"}}),a("el-table-column",{attrs:{prop:"perms",label:"权限编码",sortable:"",width:"180"}}),a("el-table-column",{attrs:{prop:"icon",label:"图标"}}),a("el-table-column",{attrs:{prop:"type",label:"类型"},scopedSlots:e._u([{key:"default",fn:function(t){return[0===t.row.type?a("el-tag",{attrs:{size:"small"}},[e._v("目录")]):1===t.row.type?a("el-tag",{attrs:{size:"small",type:"success"}},[e._v("菜单")]):2===t.row.type?a("el-tag",{attrs:{size:"small",type:"info"}},[e._v("按钮")]):e._e()]}}])}),a("el-table-column",{attrs:{prop:"path",label:"菜单URL"}}),a("el-table-column",{attrs:{prop:"component",label:"菜单组件"}}),a("el-table-column",{attrs:{prop:"orderNum",label:"排序号"}}),a("el-table-column",{attrs:{prop:"statu",label:"状态"},scopedSlots:e._u([{key:"default",fn:function(t){return[1===t.row.statu?a("el-tag",{attrs:{size:"small",type:"success"}},[e._v("正常")]):0===t.row.statu?a("el-tag",{attrs:{size:"small",type:"danger"}},[e._v("禁用")]):e._e()]}}])}),a("el-table-column",{attrs:{prop:"icon",label:"操作"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-button",{attrs:{type:"text"},on:{click:function(a){return e.editHandle(t.row.id)}}},[e._v("编辑")]),a("el-divider",{attrs:{direction:"vertical"}}),[a("el-popconfirm",{attrs:{title:"这是一段内容确定删除吗？"},on:{confirm:function(a){return e.delHandle(t.row.id)}}},[a("el-button",{attrs:{slot:"reference",type:"text"},slot:"reference"},[e._v("删除")])],1)]]}}])})],1),a("el-dialog",{attrs:{title:"提示",visible:e.dialogVisible,width:"600px","before-close":e.handleClose},on:{"update:visible":function(t){e.dialogVisible=t}}},[a("el-form",{ref:"editForm",staticClass:"demo-editForm",attrs:{model:e.editForm,rules:e.editFormRules,"label-width":"100px"}},[a("el-form-item",{attrs:{label:"上级菜单",prop:"parentId"}},[a("el-select",{attrs:{placeholder:"请选择上级菜单"},model:{value:e.editForm.parentId,callback:function(t){e.$set(e.editForm,"parentId",t)},expression:"editForm.parentId"}},[e._l(e.tableData,(function(t){return[a("el-option",{attrs:{label:t.name,value:t.id}}),e._l(t.children,(function(t){return[a("el-option",{attrs:{label:t.name,value:t.id}},[a("span",[e._v(e._s("- "+t.name))])])]}))]}))],2)],1),a("el-form-item",{attrs:{label:"菜单名称",prop:"name","label-width":"100px"}},[a("el-input",{attrs:{autocomplete:"off"},model:{value:e.editForm.name,callback:function(t){e.$set(e.editForm,"name",t)},expression:"editForm.name"}})],1),a("el-form-item",{attrs:{label:"权限编码",prop:"perms","label-width":"100px"}},[a("el-input",{attrs:{autocomplete:"off"},model:{value:e.editForm.perms,callback:function(t){e.$set(e.editForm,"perms",t)},expression:"editForm.perms"}})],1),a("el-form-item",{attrs:{label:"图标",prop:"icon","label-width":"100px"}},[a("el-input",{attrs:{autocomplete:"off"},model:{value:e.editForm.icon,callback:function(t){e.$set(e.editForm,"icon",t)},expression:"editForm.icon"}})],1),a("el-form-item",{attrs:{label:"菜单URL",prop:"path","label-width":"100px"}},[a("el-input",{attrs:{autocomplete:"off"},model:{value:e.editForm.path,callback:function(t){e.$set(e.editForm,"path",t)},expression:"editForm.path"}})],1),a("el-form-item",{attrs:{label:"菜单组件",prop:"component","label-width":"100px"}},[a("el-input",{attrs:{autocomplete:"off"},model:{value:e.editForm.component,callback:function(t){e.$set(e.editForm,"component",t)},expression:"editForm.component"}})],1),a("el-form-item",{attrs:{label:"类型",prop:"type","label-width":"100px"}},[a("el-radio-group",{model:{value:e.editForm.type,callback:function(t){e.$set(e.editForm,"type",t)},expression:"editForm.type"}},[a("el-radio",{attrs:{label:0}},[e._v("目录")]),a("el-radio",{attrs:{label:1}},[e._v("菜单")]),a("el-radio",{attrs:{label:2}},[e._v("按钮")])],1)],1),a("el-form-item",{attrs:{label:"状态",prop:"statu","label-width":"100px"}},[a("el-radio-group",{model:{value:e.editForm.statu,callback:function(t){e.$set(e.editForm,"statu",t)},expression:"editForm.statu"}},[a("el-radio",{attrs:{label:0}},[e._v("禁用")]),a("el-radio",{attrs:{label:1}},[e._v("正常")])],1)],1),a("el-form-item",{attrs:{label:"排序号",prop:"orderNum","label-width":"100px"}},[a("el-input-number",{attrs:{min:1,label:"排序号"},model:{value:e.editForm.orderNum,callback:function(t){e.$set(e.editForm,"orderNum",t)},expression:"editForm.orderNum"}},[e._v("1")])],1),a("el-form-item",[a("el-button",{attrs:{type:"primary"},on:{click:function(t){return e.submitForm("editForm")}}},[e._v("立即创建")]),a("el-button",{on:{click:function(t){return e.resetForm("editForm")}}},[e._v("重置")])],1)],1)],1)],1)},r=[],n={name:"Menu",data:function(){return{dialogVisible:!1,editForm:{},editFormRules:{parentId:[{required:!0,message:"请选择上级菜单",trigger:"blur"}],name:[{required:!0,message:"请输入名称",trigger:"blur"}],perms:[{required:!0,message:"请输入权限编码",trigger:"blur"}],type:[{required:!0,message:"请选择状态",trigger:"blur"}],orderNum:[{required:!0,message:"请填入排序号",trigger:"blur"}],statu:[{required:!0,message:"请选择状态",trigger:"blur"}]},tableData:[]}},created:function(){this.getMenuTree()},methods:{getMenuTree:function(){var e=this;this.$axios.get("/sys/menu/list").then((function(t){e.tableData=t.data.data}))},submitForm:function(e){var t=this;this.$refs[e].validate((function(e){if(!e)return console.log("error submit!!"),!1;t.$axios.post("/sys/menu/"+(t.editForm.id?"update":"save"),t.editForm).then((function(e){t.$message({showClose:!0,message:"恭喜你，操作成功",type:"success",onClose:function(){t.getMenuTree()}}),t.dialogVisible=!1}))}))},editHandle:function(e){var t=this;this.$axios.get("/sys/menu/info/"+e).then((function(e){t.editForm=e.data.data,t.dialogVisible=!0}))},resetForm:function(e){this.$refs[e].resetFields(),this.dialogVisible=!1,this.editForm={}},handleClose:function(){this.resetForm("editForm")},delHandle:function(e){var t=this;this.$axios.post("/sys/menu/delete/"+e).then((function(e){t.$message({showClose:!0,message:"恭喜你，操作成功",type:"success",onClose:function(){t.getMenuTree()}})}))}}},l=n,i=a("2877"),s=Object(i["a"])(l,o,r,!1,null,"3d3fe584",null);t["default"]=s.exports},e539:function(e,t,a){"use strict";a("f922")},f922:function(e,t,a){}});
//# sourceMappingURL=app.29efdb7f.js.map