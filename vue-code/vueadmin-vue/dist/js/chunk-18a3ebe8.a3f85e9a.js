(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-18a3ebe8"],{"5d63":function(e,t,l){"use strict";l("e416")},e416:function(e,t,l){},f83f:function(e,t,l){"use strict";l.r(t);var r=function(){var e=this,t=e.$createElement,l=e._self._c||t;return l("div",[l("el-form",{attrs:{inline:!0}},[l("el-form-item",[l("el-input",{attrs:{placeholder:"请输入接口名称",clearable:""},model:{value:e.searchForm.fieldName,callback:function(t){e.$set(e.searchForm,"fieldName",t)},expression:"searchForm.fieldName"}})],1),l("el-form-item",[l("el-button",{on:{click:e.getFieldList}},[e._v("搜索")])],1),l("el-form-item",[l("el-button",{attrs:{type:"primary"},on:{click:function(t){e.dialogVisible=!0}}},[e._v("新增")])],1),l("el-form-item",[l("el-popconfirm",{attrs:{title:"这是确定批量删除吗？"},on:{confirm:function(t){return e.delHandle(null)}}},[l("el-button",{attrs:{slot:"reference",type:"danger",disabled:e.delBtlStatu},slot:"reference"},[e._v("批量删除")])],1)],1)],1),l("el-table",{staticStyle:{width:"100%","margin-bottom":"20px"},attrs:{data:e.tableData,"row-key":"id",border:"",stripe:"","default-expand-all":"","tree-props":{children:"children",hasChildren:"hasChildren"}}},[l("el-table-column",{attrs:{type:"selection",width:"55"}}),l("el-table-column",{attrs:{prop:"id",label:"ID",width:"50"}}),l("el-table-column",{attrs:{prop:"name",label:"名称",width:"120"}}),l("el-table-column",{attrs:{prop:"httpType",label:"请求类型",width:"100"}}),l("el-table-column",{attrs:{prop:"opType",label:"操作类型",width:"100"}}),l("el-table-column",{attrs:{prop:"model",label:"操作对象",width:"100"}}),l("el-table-column",{attrs:{prop:"data",label:"接口参数",width:"200"}}),l("el-table-column",{attrs:{prop:"filter",label:"过滤条件",width:"200"}}),l("el-table-column",{attrs:{prop:"other",label:"查询设置",width:"200"}}),l("el-table-column",{attrs:{prop:"createUser",label:"创建者",width:"100"}}),l("el-table-column",{attrs:{prop:"accessUser",label:"授权用户"}}),l("el-table-column",{attrs:{prop:"status",label:"状态"},scopedSlots:e._u([{key:"default",fn:function(t){return[1===t.row.status?l("el-tag",{attrs:{size:"small",type:"success"}},[e._v("正常使用")]):0===t.row.status?l("el-tag",{attrs:{size:"small",type:"danger"}},[e._v("禁用")]):e._e()]}}])}),l("el-table-column",{attrs:{prop:"icon",label:"操作",width:"260px"},scopedSlots:e._u([{key:"default",fn:function(t){return[l("el-button",{attrs:{type:"text"},on:{click:function(l){return e.editHandle(t.row.id)}}},[e._v("编辑")]),l("el-divider",{attrs:{direction:"vertical"}}),[l("el-popconfirm",{attrs:{title:"这是一段内容确定删除吗？"},on:{confirm:function(l){return e.delHandle(t.row.id)}}},[l("el-button",{attrs:{slot:"reference",type:"text"},slot:"reference"},[e._v("删除")])],1)]]}}])})],1),l("el-pagination",{attrs:{layout:"total, sizes, prev, pager, next, jumper","page-sizes":[10,20,50,100],"current-page":e.current,"page-size":e.size,total:e.total},on:{"size-change":e.handleSizeChange,"current-change":e.handleCurrentChange}}),l("el-dialog",{attrs:{title:"新增接口",visible:e.dialogVisible,width:"600px","before-close":e.handleClose},on:{"update:visible":function(t){e.dialogVisible=t}}},[l("el-form",{ref:"editForm",attrs:{model:e.editForm,rules:e.editFormRules}},[l("el-form-item",{attrs:{label:"名称",prop:"name","label-width":"100px"}},[l("el-input",{attrs:{autocomplete:"off"},model:{value:e.editForm.name,callback:function(t){e.$set(e.editForm,"name",t)},expression:"editForm.name"}})],1),l("el-form-item",{attrs:{label:"remark",prop:"remark","label-width":"95px"}},[l("el-input",{attrs:{autocomplete:"off"},model:{value:e.editForm.remark,callback:function(t){e.$set(e.editForm,"remark",t)},expression:"editForm.remark"}})],1),l("el-form-item",{attrs:{label:"请求类型",rules:e.editFormRules,prop:"httpType","label-width":"105px"}},[l("el-radio-group",{model:{value:e.editForm.httpType,callback:function(t){e.$set(e.editForm,"httpType",t)},expression:"editForm.httpType"}},[l("el-radio",{attrs:{label:0}},[e._v("get")]),l("el-radio",{attrs:{label:1}},[e._v("post")])],1)],1),l("el-form-item",{attrs:{label:"opType",rules:e.editFormRules,"label-width":"100px"}},[l("el-select",{attrs:{placeholder:"请选择操作类型",size:"small"},model:{value:e.editForm.opType,callback:function(t){e.$set(e.editForm,"opType",t)},expression:"editForm.opType"}},[l("el-option",{attrs:{label:"put",value:"put"}}),l("el-option",{attrs:{label:"post",value:"post"}}),l("el-option",{attrs:{label:"delete",value:"delete"}}),l("el-option",{attrs:{label:"get",value:"get"}})],1)],1),l("el-form-item",{attrs:{label:"model",prop:"model",rules:e.editFormRules,"label-width":"95px"}},[l("el-input",{attrs:{autocomplete:"off"},model:{value:e.editForm.model,callback:function(t){e.$set(e.editForm,"model",t)},expression:"editForm.model"}})],1),l("el-form-item",{attrs:{label:"modelNames",prop:"modelNames",rules:e.editFormRules,"label-width":"95px"}},[l("el-input",{attrs:{autocomplete:"off"},model:{value:e.editForm.modelNames,callback:function(t){e.$set(e.editForm,"modelNames",t)},expression:"editForm.modelNames"}})],1),l("el-form-item",{attrs:{label:"data",prop:"data",rules:e.editFormRules,"label-width":"95px"}},[l("el-input",{attrs:{autocomplete:"off"},model:{value:e.editForm.data,callback:function(t){e.$set(e.editForm,"data",t)},expression:"editForm.data"}})],1),l("el-form-item",{attrs:{label:"filter",prop:"filter",rules:e.editFormRules,"label-width":"95px"}},[l("el-input",{attrs:{autocomplete:"off"},model:{value:e.editForm.filter,callback:function(t){e.$set(e.editForm,"filter",t)},expression:"editForm.filter"}})],1),l("el-form-item",{attrs:{label:"状态",prop:"status",rules:e.editFormRules,"label-width":"105px"}},[l("el-radio-group",{model:{value:e.editForm.status,callback:function(t){e.$set(e.editForm,"status",t)},expression:"editForm.status"}},[l("el-radio",{attrs:{label:0}},[e._v("正在使用")]),l("el-radio",{attrs:{label:1}},[e._v("禁用")])],1)],1),l("el-form-item",{attrs:{label:"createUser",prop:"createUser",rules:e.editFormRules,"label-width":"105px"}},[l("el-input-number",{attrs:{min:0,max:1e4},on:{change:e.handleChange},model:{value:e.editForm.createUser,callback:function(t){e.$set(e.editForm,"createUser",t)},expression:"editForm.createUser"}})],1),l("el-form-item",{attrs:{label:"允许访问",prop:"accessUser",rules:e.editFormRules,"label-width":"105px"}},[l("el-input-number",{attrs:{min:0,max:1e4},on:{change:e.handleChange},model:{value:e.editForm.accessUser,callback:function(t){e.$set(e.editForm,"accessUser",t)},expression:"editForm.accessUser"}})],1)],1),l("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[l("el-button",{on:{click:function(t){return e.resetForm("editForm")}}},[e._v("取 消")]),l("el-button",{attrs:{type:"primary"},on:{click:function(t){return e.submitForm("editForm")}}},[e._v("确 定")])],1)],1)],1)},a=[],o={name:"Menu",data:function(){return{total:0,size:10,current:1,searchForm:{},delBtlStatu:!0,dialogVisible:!1,editForm:{},editFormRules:{name:[{required:!0,message:"请输入名称",trigger:"blur"}],httpType:[{required:!0,message:"请选择请求类型",trigger:"blur"}],opType:[{required:!0,message:"请选择操作类型",trigger:"blur"}],model:[{required:!0,message:"请输入model",trigger:"blur"}],modelNames:[{required:!0,message:"请输入modelNames",trigger:"blur"}],data:[{required:!0,message:"请输入data",trigger:"blur"}],filter:[{required:!0,message:"请输入filter",trigger:"blur"}],status:[{required:!0,message:"请选择状态",trigger:"blur"}],createUser:[{required:!0,message:"请输入接口创建用户ID",trigger:"blur"}],accessUser:[{required:!0,message:"请输入accessUser",trigger:"blur"}]},tableData:[]}},created:function(){},methods:{getFieldList:function(){var e=this;this.$axios.get("/sys/user/list",{params:{username:this.searchForm.username,current:this.current,size:this.size}}).then((function(t){e.tableData=t.data.data.records,e.size=t.data.data.size,e.current=t.data.data.current,e.total=t.data.data.total}))},resetForm:function(e){this.$refs[e].resetFields(),this.dialogVisible=!1,this.editForm={}},handleClose:function(){this.resetForm("editForm")},handleSizeChange:function(e){console.log("每页 ".concat(e," 条")),this.size=e,this.getUserList()},handleCurrentChange:function(e){console.log("当前页: ".concat(e)),this.current=e,this.getUserList()}}},s=o,i=(l("5d63"),l("2877")),n=Object(i["a"])(s,r,a,!1,null,"2c71955c",null);t["default"]=n.exports}}]);
//# sourceMappingURL=chunk-18a3ebe8.a3f85e9a.js.map