<template>
  <div class="container">
    <el-form :inline="true">
      <el-form-item>
        <el-input
            v-model="searchForm.username"
            placeholder=""
            clearable
        >
        </el-input>
      </el-form-item>

      <el-form-item>
        <el-button @click="getFieldTreeList">搜索</el-button>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="addDialogVisible = true">新增</el-button>
      </el-form-item>
      <el-form-item>
        <el-popconfirm title="这是确定批量删除吗？" @confirm="delHandle(null)">
          <el-button type="danger" slot="reference" :disabled="delBtlStatu" v-if="hasAuth('sys:user:delete')">批量删除</el-button>
        </el-popconfirm>
      </el-form-item>
    </el-form>
    <div class="center">
      <el-card class="box-card" v-for="(tree, index) in treeData" shadow="hover">
        <div slot="header" class="clearfix">
          <span><el-tag style="font-size: 15px;font-weight: bolder;cursor: pointer">{{tree.fieldTreeName}}</el-tag></span>
          <el-button style="float: right; padding: 5px 0" type="text" @click="removeFieldTree(tree)">删除</el-button>
          <!--<el-button style="float: right; margin-right:10px; padding: 5px 0" type="text" @click="toggleFullscreen(index)">编辑</el-button>-->
          <el-button style="float: right; margin-right:5px; padding: 5px 0" type="text" @click="viewFieldTree(tree)">查看</el-button>
        </div>
        <div :id="'field-tree-'+index" style="float:left;" :style="{width: treeSize.width + 'px',height:treeSize.height + 'px'}">
          <TreeChart style="float:left;" @getTreeMapData="getTreeMapData" v-bind:tree="tree" v-bind:treeSize="treeSize"/>
        </div>
      </el-card>
    </div>

    <div class="bottom">
      <el-pagination
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              layout="total, sizes, prev, pager, next, jumper"
              :page-sizes="[10, 20, 50, 100]"
              :current-page="current"
              :page-size="size"
              :total="total">
      </el-pagination>
    </div>


    <!--查看字段树-->
    <el-dialog
        title="字段树-数据结构"
        :visible.sync="viewDialogVisible"
        :before-close="handleClose">
      <div style="width:100%;height: 500px;overflow-y: scroll;" >
        <JsonView :json="jsonData"></JsonView>
      </div>
    </el-dialog>

    <!--新增对话框-->
    <el-dialog
            title="新增字段树"
            :visible.sync="addDialogVisible"
            :before-close="handleCloseAdd">
            <div style="width:100%;height: 130px;overflow-y: auto;" >
              <el-form :model="addRootForm" ref="addRootForm" :rules="addRootFormRules" label-width="80px">
                <el-form-item label="树名称" prop="name">
                  <el-input v-model="addRootForm.name" size="small" autocomplete="off" placeholder="请输入字段树名称（根节点名称）"></el-input>
                </el-form-item>
              </el-form>
              <div slot="footer" class="dialog-footer">
                <el-button type="primary" style="float:right;margin-right: 10px;" @click="addRootNode('addRootForm')">确 定</el-button>
                <el-button @click="addDialogVisible=false" style="float:right;margin-right: 20px;">取 消</el-button>
              </div>
            </div>
      <!-- <el-dialog
              width="30%"
              title="添加根节点"
              :visible.sync="addRootDialogVisible"
              append-to-body>
        <div style="width:100%;height: 130px;overflow-y: auto;" >
          <el-form :model="addRootForm" ref="addRootForm" :rules="addRootFormRules" label-width="80px">
            <el-form-item label="树名称" prop="name">
              <el-input v-model="addRootForm.name" size="small" autocomplete="off" placeholder="请输入字段树名称（根节点名称）"></el-input>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button type="primary" style="float:right;margin-right: 10px;" @click="addRootNode('addRootForm')">确 定</el-button>
            <el-button @click="addRootDialogVisible=false" style="float:right;margin-right: 20px;">取 消</el-button>
          </div>
        </div>
      </el-dialog> -->
      <!-- <div style="width:100%;height: 500px;overflow-y: scroll;" >
        <div style="float: left;width: 100%;height: 500px;overflow: hidden;">
          <el-card class="box-card-1" shadow="hover">
            <div slot="header" class="clearfix">
              <span><h2>结构图</h2></span>
            </div>
            <div style="float:left;width:100%;height:400px;" id="field-tree-999" @dblclick="toggleFullscreen(999)">
              <div style="float: left;width: 100%;height: 40px;">
                <el-button type="primary" @click="addRootDialogVisible = true" v-show="showAddRootBtn">添加根节点</el-button>
              </div>
              <TreeChart style="float:left;" @getTreeMapData="getTreeMapData" v-bind:tree="tree" v-bind:treeChangeFlag="treeChangeFlag"/>
            </div>
          </el-card>
        </div>
        <div style="float: left;margin-top:40px;width: 100%;overflow: hidden;">
          <el-card class="box-card-1" shadow="hover">
            <div slot="header" class="clearfix">
              <span><h2>数据结构</h2></span>
            </div>
            <div style="float:left;width:100%;">
              <JsonView :json="jsonData"></JsonView>
            </div>
          </el-card>
        </div>
        <div style="float: left;width: 100%;height: 30px;"></div>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" style="float:right;margin-right: 10px;">确 定</el-button>
          <el-button @click="addDialogVisible=false" style="float:right;margin-right: 20px;">取 消</el-button>
        </div>
      </div> -->
    </el-dialog>

    <!-- 添加节点 -->
    <!--<el-drawer
            title="操作节点"
            :visible.sync="drawer"
            direction="ltr">
      <div style="float:left;width: 100%;height: 100%;">
        <div style="float: left;width: 100%;height: 450px;background: red;"></div>
        <div style="float: left;margin-top:50px;width: 100%;height: 50px;background: yellow;"></div>
      </div>
    </el-drawer>-->


    <!--操作节点-->
    <el-dialog
            :visible.sync="viewNodeDialog">
      <div style="width:100%;height: 500px;overflow-y: auto;" >
        <div style="float: left;width: 100%">
          <el-descriptions class="margin-top" title="本节点信息" :column="2">
            <el-descriptions-item label="节点类型"><el-tag size="small">{{selectNode.nodeType == 1 ? '根节点' : (selectNode.nodeType == 2 ? '中间节点' : '字段节点')}}</el-tag></el-descriptions-item>
            <el-descriptions-item label="名称">{{selectNode.nodeType == 3 ? selectNode.fieldInfo.fieldName : selectNode.nodeName}}</el-descriptions-item>
            <el-descriptions-item v-if="selectNode.nodeType == 3" label="字段类型">{{selectNode.fieldInfo.fieldType}}</el-descriptions-item>
            <el-descriptions-item v-if="selectNode.nodeType == 3" label="字段长度">{{selectNode.fieldInfo.length}}</el-descriptions-item>
            <el-descriptions-item v-if="selectNode.nodeType == 3" label="是否必填">{{selectNode.fieldInfo.isRequire == 1 ? '是' : '否'}}</el-descriptions-item>
            <el-descriptions-item v-if="selectNode.nodeType == 3" label="是否唯一">{{selectNode.fieldInfo.isUnique == 1 ? '是' : '否'}}</el-descriptions-item>
            <el-descriptions-item v-if="selectNode.nodeType == 3" label="默认值">{{selectNode.fieldInfo.defaultValue}}</el-descriptions-item>
            <el-descriptions-item v-if="selectNode.nodeType == 3" label="备注">{{selectNode.fieldInfo.remark}}</el-descriptions-item>
          </el-descriptions>
        </div>
        <div v-if="selectNode.nodeType != 1" style="float: left;margin-top: 20px; width: 100%;height: 50px;">
          <el-descriptions title="修改本节点"></el-descriptions>
          <el-select v-model="curNodeFieldId" filterable placeholder="请选择本节点字段" style="width: 100%;height: 100%;">
            <el-option
                    v-for="item in options"
                    :key="item.id"
                    :label="item.fieldName"
                    :value="item.id">
              <span style="float: left">{{item.fieldName}}</span>
              <span style="float: right; margin-right:5px; color: #8492a6; font-size: 13px">默认值: {{item.defaultvalue == '' ? '无' : item.defaultvalue}}</span>
              <span style="float: right; margin-right:10px; color: #8492a6; font-size: 13px">唯一: {{item.Unique == 1 ? '是' : '否'}}</span>
              <span style="float: right; margin-right:15px; color: #8492a6; font-size: 13px">必须: {{item.isRequire == 1 ? '是' : '否'}}</span>
              <span style="float: right; margin-right:20px; color: #8492a6; font-size: 13px">长度: {{item.length}}</span>
              <span style="float: right; margin-right:25px; color: #8492a6; font-size: 13px">类型: {{item.fieldType}}</span>
            </el-option>
          </el-select>
        </div>
        <div style="float: left;margin-top: 60px; width: 100%;height: 50px;">
          <el-descriptions title="添加子节点"></el-descriptions>
          <el-select v-model="childNodeFieldIds" multiple filterable placeholder="请选择子节字段" style="width: 100%;height: 100%;">
            <el-option
                    v-for="item in options"
                    :key="item.id"
                    :label="item.fieldName"
                    :value="item.id">
              <span style="float: left">{{item.fieldName}}</span>
              <span style="float: right; margin-right:5px; color: #8492a6; font-size: 13px">默认值: {{item.defaultvalue == '' ? '无' : item.defaultvalue}}</span>
              <span style="float: right; margin-right:10px; color: #8492a6; font-size: 13px">唯一: {{item.Unique == 1 ? '是' : '否'}}</span>
              <span style="float: right; margin-right:15px; color: #8492a6; font-size: 13px">必须: {{item.isRequire == 1 ? '是' : '否'}}</span>
              <span style="float: right; margin-right:20px; color: #8492a6; font-size: 13px">长度: {{item.length}}</span>
              <span style="float: right; margin-right:25px; color: #8492a6; font-size: 13px">类型: {{item.fieldType}}</span>
            </el-option>
          </el-select>
        </div>

        <div slot="footer" class="dialog-footer">
          <el-popconfirm @confirm="this.removeNode" title="删除本节点可能会删除其所有字节点，确定删除吗？">
          <el-button slot="reference" type="danger" style="float:right;margin-top:60px;margin-right: 10px;">删除</el-button>
          </el-popconfirm>
          <el-button type="primary" style="float:right;margin-top:60px;margin-right: 10px;" @click="this.submitNode">提交</el-button>
          
        </div>
      </div>
    </el-dialog>

  </div>

</template>

<script>
import Element from "element-ui"
import screenfull from "screenfull"
import { page, remove, list, hasFieldTreeName, addNode, updateNode} from '@/api/fieldTree'
import { list as fieldList} from '@/api/field'
import TreeChart from '@/components/TreeChart'
import JsonView from '@/components/JsonView'
import {checkNameReg} from '@/utils/common'
export default {
  name: "FieldTree",
  components: {
    TreeChart,
    JsonView
  },
  watch: {
    tree: {
      handler (n, o) {
        if (n.nodes.length > 0){
          this.showAddRootBtn = false
        }else{
          this.showAddRootBtn = true
        }
      },
      deep: true
    }
  },
  data() {
    return {
      curNodeFieldId: '',
      childNodeFieldIds: '',
      value: '',
      options: [],
      viewNodeDialog: false,
      selectNode: {},
      showAddRootBtn: true,
      addRootForm:{},
      searchForm: {},
      delBtlStatu: true,
      total: 0,
      size: 10,
      current: 1,
      addRootFormRules: {
        name: [
          {required: true, message: '请输入字段树名称（根节点名称）', trigger: 'blur'},
          {validator:checkNameReg}
        ]
      },
      viewDialogVisible: false,
      addDialogVisible: false,
      jsonData: {
        user:{
          name: "jim",
          age: 20,
          address: {
            city: "江西",
            code: "330000"
          }
        }
      },
      multipleSelection: [],
      roleDialogFormVisible: false,
      treeData:[],
      tree: {
        rootId: "",
        fieldTreeName: "",
        nodes: [],
        links: []
      },
      treeChangeFlag: false,
      treeSize: {width: 350, height: 200},
      editNode: {

      }
    }
  },
  created() {
    this.getDict()
    this.getFieldTreeList()
  },
  methods: {
    submitNode(){
      //console.log(this.selectNode.id, this.curNodeFieldId, this.childNodeFieldIds)
      // nodeId, nodeFieldId, childFieldIds
      // {"id": 1, "nodeType": 1, "parentId": -1, "fieldInfoId":-1, "defaultName": "root"}
      
      let data = {nodeId: this.selectNode.id, nodeFieldId: this.curNodeFieldId === '' || this.curNodeFieldId == '' ? -1 : this.curNodeFieldId, childFieldIds: this.childNodeFieldIds}
      console.log(data)
      updateNode(data).then(res => {
        if (res.data.code == 200){
          this.getFieldTreeList()
          this.$message({
            type: 'success',
            message: '操作成功!',
          });
          this.viewNodeDialog = false
        }
      })

    },
    removeNode(){
      let ids = []
      ids.push(this.selectNode.id)
      console.log(ids)
      remove(ids).then(res => {
        if (res.data.code == 200){
          this.getFieldTreeList()
          this.$message({
            type: 'success',
            message: '删除成功!',
          });
          this.viewNodeDialog = false
        }
      })
    },
    getDict(){
      fieldList().then(res => {
          console.log(res.data.data)
          this.options = res.data.data
        })
    },
    // 删除字段树
    removeFieldTree(tree){
      this.$confirm('此操作将永久删除该字段树, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        var ids = []
        ids.push(Number(tree.rootId))
        console.log(ids)
        remove(ids).then(res => {
          console.log(res)
          if (res.data.code == 200){
            this.getFieldTreeList()
            this.$message({
              type: 'success',
              message: '删除成功!',
            });
          }
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    // 获取子组件tree中的数据
    getTreeMapData(data){
      this.selectNode = data
      this.viewNodeDialog = true;
    },
    // nodeType: 1根节点 2中间节点 3叶子节点（字段）
    buildNode(nodeType, parentId, fieldInfoId, name){
      let id = this.tree.nodes.length + 1
      let data = {}
      if (nodeType == 1 || nodeType == 2){
        // 根节点 | 中间节点
        data = {id: id, nodeType: nodeType, parentId: parentId, fieldInfoId: fieldInfoId, defaultName: name, nodeName: name}
      }else if(nodeType == 3){
        // 叶子节点
        data = {id: id, nodeType: nodeType, parentId: parentId, fieldInfoId: fieldInfoId, defaultName: name, fieldInfo: {fieldName: name}}
      }
      return {
        id: id.toString(),
        data: data
      }
    },
    // 字段树添加根节点
    addRootNode(formName){
      this.$refs[formName].validate((valid) => {
        if (valid){
          // 判断该名称是否存在
          hasFieldTreeName({fieldTreeName: this.addRootForm.name}).then(res => {
            let count = res.data.data
            if (count > 0){
              Element.Message.warning('字段树名称已经存在')
            }else{
              // 创建根节点
              // {"id": 1, "nodeType": 1, "parentId": -1, "fieldInfoId":-1, "defaultName": "root"}
              let node = {id: 1, nodeType: 1, parentId: -1, fieldInfoId: -1, defaultName: this.addRootForm.name}
              let nodeList = []
              nodeList.push(node)
              addNode(nodeList).then(res => {
                //console.log(res)
                if (res.data.code == 200){
                  this.getFieldTreeList()
                  this.$message({
                    type: 'success',
                    message: '创建成功!',
                  });
                }
              });
              // this.tree.fieldTreeName = this.addRootForm.name
              // let node = this.buildNode(1, -1, -1, this.addRootForm.name)
              // this.tree.rootId = node.id
              // this.tree.nodes.push(node)
              // this.addRootDialogVisible = false
              // this.treeChangeFlag = !this.treeChangeFlag
            }
          });
        }
      })
      this.addDialogVisible = false
    },
    toggleFullscreen(index){
      const obj = document.getElementById("field-tree-"+index);
      if (!screenfull.isEnabled) {
        return false
      }
      screenfull.request(obj)
    },
    //查看字段树
    viewFieldTree(tree){
      this.viewDialogVisible = true
      this.jsonData = tree
    },
    // 拖拽移动
    move(e) {
      const odiv = e.currentTarget // 获取元素
      console.log(odiv)
      // 算出鼠标相对元素的位置
      const disX = e.clientX - odiv.offsetLeft
      const disY = e.clientY - odiv.offsetTop
      document.onmousemove = (e) => { // 鼠标按下并移动的事件
        // 用鼠标的位置减去鼠标相对元素的位置，得到元素的位置
        const left = e.clientX - disX
        const top = e.clientY - disY
        // 绑定元素位置到positionX和positionY上面
        this.positionX = top
        this.positionY = left
        // 移动当前元素
        odiv.style.left = left + 'px'
        odiv.style.top = top + 'px'
      }
      document.onmouseup = () => {
        document.onmousemove = null
        document.onmouseup = null
      }
    },
    toggleSelection(rows) {
      if (rows) {
        rows.forEach(row => {
          this.$refs.multipleTable.toggleRowSelection(row);
        });
      } else {
        this.$refs.multipleTable.clearSelection();
      }
    },
    handleSelectionChange(val) {
      console.log("勾选")
      console.log(val)
      this.multipleSelection = val;

      this.delBtlStatu = val.length == 0
    },

    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.size = val
      this.getFieldTreeList()
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.current = val
      this.getFieldTreeList()
    },

    resetForm(formName) {
      this.$refs[formName].resetFields();
      this.dialogVisible = false
      this.editForm = {}
    },
    handleClose() {
      this.viewDialogVisible = false
    },
    handleCloseAdd(){
      this.addDialogVisible = false
    },

    // 将字段树转换为该组件适配的格式
    transformTree(fieldTree){
      let nodes = []
      let links = []
      this.dfsTree(fieldTree, nodes, links)
      return {
        "rootId": fieldTree.node.id.toString(),
        "fieldTreeName": fieldTree.node.nodeName,
        "nodes": nodes,
        "links": links
      }
    },
    dfsTree(node, nodes, links){
      nodes.push({id: node.node.id.toString(), data: node.node})
      for (let i = 0 ; i < node.children.length ; i++){
        let child = node.children[i]
        links.push({from: node.node.id.toString(), to: child.node.id.toString()})
        this.dfsTree(node.children[i], nodes, links)
      }
    },
    getFieldTreeList() {
      this.searchForm['current'] = this.current;
      this.searchForm['size'] = this.size;
      page(this.searchForm).then(res => {
        let treeList = []
        for (let i = 0 ; i < res.data.data.records.length ; i++){
          treeList.push(this.transformTree(res.data.data.records[i]))
        }
        this.treeData = treeList
        this.size = res.data.data.size
        this.current = res.data.data.current
        this.total = res.data.data.total
      })
    },

    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$axios.post('/sys/user/' + (this.editForm.id?'update' : 'save'), this.editForm)
              .then(res => {

                this.$message({
                  showClose: true,
                  message: '恭喜你，操作成功',
                  type: 'success',
                  onClose:() => {
                    this.getFieldTreeList()
                  }
                });

                this.dialogVisible = false
              })
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    editHandle(id) {
      this.$axios.get('/sys/user/info/' + id).then(res => {
        this.editForm = res.data.data

        this.dialogVisible = true
      })
    },
    delHandle(id) {
      var ids = []

      if (id) {
        ids.push(id)
      } else {
        this.multipleSelection.forEach(row => {
          ids.push(row.id)
        })
      }

      console.log(ids)

      remove(ids).then(res => {
        console.log(res.data.data)
        this.$message({
          showClose: true,
          message: '恭喜你，操作成功',
          type: 'success',
          onClose:() => {
            this.getFieldTreeList()
          }
        });
      })
    },

    roleHandle (id) {
      this.roleDialogFormVisible = true

      this.$axios.get('/sys/user/info/' + id).then(res => {
        this.roleForm = res.data.data

        let roleIds = []
        res.data.data.sysRoles.forEach(row => {
          roleIds.push(row.id)
        })

        this.$refs.roleTree.setCheckedKeys(roleIds)
      })
    },
    submitRoleHandle(formName) {
      var roleIds = this.$refs.roleTree.getCheckedKeys()

      console.log(roleIds)

      this.$axios.post('/sys/user/role/' + this.roleForm.id, roleIds).then(res => {
        this.$message({
          showClose: true,
          message: '恭喜你，操作成功',
          type: 'success',
          onClose:() => {
            this.getFieldTreeList()
          }
        });

        this.roleDialogFormVisible = false
      })
    },
    repassHandle(id, username) {

      this.$confirm('将重置用户【' + username + '】的密码, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios.post("/sys/user/repass", id).then(res => {
          this.$message({
            showClose: true,
            message: '恭喜你，操作成功',
            type: 'success',
            onClose: () => {
            }
          });
        })
      })
    }
  }
}
</script>

<style scoped>
.container{
  position: relative;
  width: 100%;
}

.center{
  float: left;
  width: 100%;
}

.box-card{
  float: left;
  margin-top: 20px;
  margin-left: 20px;
  width: 390px;
  height: 300px;
}

.box-card-1{
  float: left;
  width: 100%;
  height: 100%;
}

.bottom{
  float: left;
  width: 100%;
  height: 50px;
  margin-bottom: 20px;
}

.el-pagination {
  float: right;
  margin-top: 22px;
}




</style>
