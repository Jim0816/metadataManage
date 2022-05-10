<template>
  <div>
    <el-form :inline="true">
      <el-form-item>
        <el-input
                v-model="searchForm.fieldName"
                placeholder="字段名称"
                clearable
        >
        </el-input>
      </el-form-item>

      <el-form-item>
        <el-button @click="getFieldList">搜索</el-button>
      </el-form-item>

      <el-form-item>
        <!--<el-button type="primary" @click="dialogVisible = true" v-if="hasAuth('sys:field:save')">新增</el-button>-->
        <el-button  type="primary" @click="addForm = {}; dialogVisible = true; " >新增</el-button>
      </el-form-item>
      <el-form-item>
        <el-popconfirm title="这是确定批量删除吗？" @confirm="delHandle(null)">
          <!--<el-button type="danger" slot="reference" :disabled="delBtlStatu" v-if="hasAuth('sys:user:delete')">批量删除</el-button>-->
          <el-button type="danger" slot="reference" :disabled="delBtlStatu" >批量删除</el-button>
        </el-popconfirm>
      </el-form-item>
    </el-form>

    <el-table
            ref="multipleTable"
            :data="tableData"
            tooltip-effect="dark"
            style="width: 100%"
            border
            stripe
            @selection-change="handleSelectionChange">

      <el-table-column
              type="selection"
              width="55">
      </el-table-column>

      <el-table-column
              prop="id"
              label="ID"
              width="50">
      </el-table-column>

      <el-table-column
              prop="fieldName"
              label="字段名称"
              min-width="50">
      </el-table-column>

      <el-table-column
              prop="fieldName"
              label="所属模型"
              min-width="50">
      </el-table-column>

      <el-table-column
              prop="fieldType"
              label="字段类型"
              width="80">
      </el-table-column>

      <el-table-column
              prop="length"
              label="字段长度"
              width="80">
      </el-table-column>

      <el-table-column
              prop="isRequire"
              label="是否必须"
              width="80">
        <template slot-scope="scope">
          <el-tag size="small" v-if="scope.row.isRequire === 1" type="success">是</el-tag>
          <el-tag size="small" v-else-if="scope.row.isRequire === 0" type="danger">否</el-tag>
        </template>
      </el-table-column>

      <el-table-column
              prop="isUnique"
              label="是否唯一"
              width="80">
        <template slot-scope="scope">
          <el-tag size="small" v-if="scope.row.isUnique === 1" type="success">是</el-tag>
          <el-tag size="small" v-else-if="scope.row.isUnique === 0" type="danger">否</el-tag>
        </template>
      </el-table-column>

      <el-table-column
              prop="defaultValue"
              label="默认值">
      </el-table-column>

      <el-table-column
              prop="remark"
              label="备注">
      </el-table-column>

      <el-table-column
              prop="icon"
              width="260px"
              label="操作">

        <template slot-scope="scope">
          <!--					<el-button type="text" @click="view(scope.row)" v-if="hasAuth('sys:field:view')">查看</el-button>-->
          <el-button type="text" @click="view(scope.row)" >查看</el-button>
          <el-divider direction="vertical"></el-divider>

          <!--					<el-button type="text" @click="editHandle(scope.row.id)" v-if="hasAuth('sys:field:update')">编辑</el-button>-->
          <el-button type="text" @click="editHandle(scope.row)" >编辑</el-button>
          <el-divider direction="vertical"></el-divider>

          <template>
            <!--						<el-popconfirm title="这是一段内容确定删除吗？" @confirm="delHandle(scope.row.id)" v-if="hasAuth('sys:field:delete')">-->
            <el-popconfirm title="这是一段内容确定删除吗？" @confirm="delHandle(scope.row.id)" >
              <el-button type="text" slot="reference">删除</el-button>
            </el-popconfirm>
          </template>

        </template>
      </el-table-column>

    </el-table>

    <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            layout="total, sizes, prev, pager, next, jumper"
            :page-sizes="[10, 20, 50, 100]"
            :current-page="current"
            :page-size="size"
            :total="total">
    </el-pagination>


    <!--新增对话框-->
    <el-dialog
            title="新增字段"
            :visible.sync="dialogVisible"
            width="600px"
            :before-close="handleClose">

      <el-form :model="addForm" :rules="addFormRules" ref="addForm">
        <el-form-item label="字段名称"  prop="fieldName" label-width="100px">
          <!--          <el-input v-model="addForm.fieldName" autocomplete="off" placeholder="请输入英文名称" οninput="value=value.replace(/[^\w\.\s\/]/ig,'')" ></el-input>-->
          <el-input v-focus v-model="addForm.fieldName"  autocomplete="off" placeholder="请输入字段名称"></el-input>
        </el-form-item>


        <el-form-item label="字段类型" prop="fieldType" label-width="100px" >
          <el-select v-model="addForm.fieldType" placeholder="请选择字段类型" size="small">
            <el-option label="string" value="string"></el-option>
            <el-option label="int" value="int"></el-option>
            <el-option label="long" value="long"></el-option>
            <el-option label="float" value="float"></el-option>
            <el-option label="array" value="array"></el-option>
          </el-select>
        </el-form-item>


        <el-form-item label="字段长度" prop="length"  label-width="100px" >
          <!--          <el-input-number v-model="addForm.length" @change="handleChange" :min="1" :max="100" size="small"></el-input-number>-->
          <el-input-number v-model="addForm.length"  :min="1" :max="100" size="small"></el-input-number>
        </el-form-item>


        <el-form-item label="是否必须"  prop="isRequire" label-width="105px">
          <el-radio-group v-model="addForm.isRequire">
            <el-radio :label="1">是</el-radio>
            <el-radio :label="0">否</el-radio>
          </el-radio-group>
        </el-form-item>

        <el-form-item label="是否唯一"  prop="isUnique" label-width="105px">
          <el-radio-group v-model="addForm.isUnique">
            <el-radio :label="0">是</el-radio>
            <el-radio :label="1">否</el-radio>
          </el-radio-group>
        </el-form-item>

        <el-form-item label="默认值"  prop="defaultValue" label-width="95px">
          <el-input v-model="addForm.defaultValue" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="备注"  prop="remark" label-width="95px">
          <el-input v-model="addForm.remark" autocomplete="off"></el-input>
        </el-form-item>

      </el-form>


      <div slot="footer" class="dialog-footer">
        <el-button @click="resetForm('addForm')">取 消</el-button>
        <el-button type="primary" @click="submitForm('addForm')">确 定</el-button>
      </div>
    </el-dialog>

    <!-- 查看对话框 -->
    <el-dialog title="查看字段" :visible.sync="viewDialogFormVisible" width="500px">
      <el-form :model="fieldForm" label-width="80px">
        <el-form-item label="字段名称">
          <el-input v-model="fieldForm.fieldName" size="small" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="字段类型">
          <el-select v-model="fieldForm.fieldType" placeholder="请选择字段类型" size="small" :disabled="true">
            <el-option label="string" value="string"></el-option>
            <el-option label="int" value="int"></el-option>
            <el-option label="long" value="long"></el-option>
            <el-option label="float" value="float"></el-option>
            <el-option label="array" value="array"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="字段长度">
          <el-input-number v-model="fieldForm.length" :disabled="true" @change="handleChange" :min="1" :max="100" size="small"></el-input-number>
        </el-form-item>
        <el-form-item label="是否必填">
          <el-radio-group v-model="fieldForm.isRequire" size="small" :disabled="true">
            <el-radio-button label="1">是</el-radio-button>
            <el-radio-button label="0">否</el-radio-button>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="是否唯一">
          <el-radio-group v-model="fieldForm.isUnique" size="small" :disabled="true">
            <el-radio-button label="1">是</el-radio-button>
            <el-radio-button label="0">否</el-radio-button>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="默认值">
          <el-input v-model="fieldForm.defaultValue" size="small" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="备注">
          <el-input type="textarea" :rows="2" v-model="fieldForm.remark" size="small" :disabled="true"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <!--<el-button @click="viewDialogFormVisible=false">取 消</el-button>
        <el-button type="primary" @click="submitRoleHandle('roleForm')">确 定</el-button>-->
      </div>
    </el-dialog>

    <!--编辑对话框-->
    <el-dialog
            title="编辑字段"
            :visible.sync="editDialogVisible"
            width="500px">
      <el-form :model="editForm" label-width="80px">
        <el-form-item label="字段名称">
          <el-input v-model="editForm.fieldName" size="small"></el-input>
        </el-form-item>
        <el-form-item label="字段类型">
          <el-select v-model="editForm.fieldType" placeholder="请选择字段类型" size="small">
            <el-option label="string" value="string"></el-option>
            <el-option label="int" value="int"></el-option>
            <el-option label="long" value="long"></el-option>
            <el-option label="float" value="float"></el-option>
            <el-option label="array" value="array"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="字段长度">
          <el-input-number v-model="editForm.length" :min="1" :max="100" size="small"></el-input-number>
        </el-form-item>
        <el-form-item label="是否必填">
          <el-radio-group v-model="editForm.isRequire" size="small">
            <el-radio-button label="1">是</el-radio-button>
            <el-radio-button label="0">否</el-radio-button>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="是否唯一">
          <el-radio-group v-model="editForm.isUnique" size="small">
            <el-radio-button label="1">是</el-radio-button>
            <el-radio-button label="0">否</el-radio-button>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="默认值">
          <el-input v-model="editForm.defaultValue" size="small"></el-input>
        </el-form-item>
        <el-form-item label="备注">
          <el-input type="textarea" :rows="2" v-model="editForm.remark" size="small"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="viewDialogFormVisible=false">取 消</el-button>
        <el-button type="primary" @click="submitFieldHandle(editForm)">确 定</el-button>
      </div>
    </el-dialog>


  </div>
</template>

<script>
  import {  page, save, remove, update} from '@/api/field'
  import {checkNameReg} from '@/utils/common'
  export default {
    name: "Field",
    data() {
      return {
        searchForm: {},
        delBtlStatu: true,
        // 分页数据
        total: 0,
        size: 10,
        current: 1,
        dialogVisible: false,
        editDialogVisible: false,
        addForm: {},
        tableData: [],
        addFormRules: {
          fieldName: [
            {required: true, message: '请输入字段名称', trigger: 'blur'},
            {validator:checkNameReg}
          ],
          fieldType:[
            {required: true, message: '请选择字段类型', trigger: 'change'},
          ],
          length: [
            {required: true, message: '请输入字段长度', trigger: 'blur'}
          ],
          isRequire: [
            {required: true, message: '请选择是否必须', trigger: 'change'}
          ],
          isUnique: [
            {required: true, message: '请选择是否唯一', trigger: 'change'}
          ],
          defaultValue:[
            {required: false, message: '请输入默认值', trigger: 'blur'}
          ],
          remark:[
            {required: false, message: '请输入备注', trigger: 'blur'}
          ]
        },
        multipleSelection: [],
        viewDialogFormVisible: false,
        defaultProps: {
          children: 'children',
          label: 'name'
        },
        fieldForm: {},
        editForm: {},
        roleTreeData:  [],
        treeCheckedKeys: [],
        checkStrictly: true
      }
    },
    // 使用directives注册v-focus指令,自动获取焦点
    directives: {
      focus: {
        inserted: function(el) {
          el.querySelector('input').focus()
        }
      }
    },
    created() {
      this.getFieldList()
    },
    methods: {
      getFieldList(){
        this.searchForm['current'] = this.current;
        this.searchForm['size'] = this.size;
        page(this.searchForm).then(res => {
          this.tableData = res.data.data.records
          this.size = res.data.data.size
          this.current = res.data.data.current
          this.total = res.data.data.total
        })
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
        this.getFieldList()
      },
      handleCurrentChange(val) {
        console.log(`当前页: ${val}`);
        this.current = val
        this.getFieldList()
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
        this.dialogVisible = false
        this.addForm = {}
      },
      handleClose() {
        this.resetForm('addForm')
      },
      // 新增确认
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            save(this.addForm).then(res => {
              if (res.data.code == 200){
                // 取消弹窗
                this.dialogVisible = false
                this.getFieldList()
              }
            })
            // alert('submit!');
          } else {
            // console.log('error submit!!');
            return false;
          }
        });
        // // 1.提交前校验数据是否合法(字段名称是数字、字母和下划线组成,)
        // let patt = /^\w+$/ ;
        // // 2.合法则提交
        // if(patt.test(this.addForm.fieldName)){
        //   save(this.addForm).then(res => {
        //     // console.log('打印response状态码', res.data.code)
        //     // console.log('打印response返回信息', res.data.msg)
        //     if (res.data.code == 200){
        //       // 取消弹窗
        //       this.dialogVisible = false
        //       this.getFieldList()
        //     }
        //   })
        // }else{
        //   // 弹出不合法的消息提示
        //   this.$message({
        //     showClose: true,
        //     message: '字段名称不合法',
        //     type: 'error'
        //   });
        // }
      },
      // 编辑按钮
      editHandle(row) {
        this.editDialogVisible = true
        // 深拷贝
        this.editForm = JSON.parse(JSON.stringify(row))
      },
      // 编辑确认
      submitFieldHandle(){
        // 行中展示的id和数据库的id相同
        console.log(this.editForm)
        update(this.editForm).then(res =>{
          if (res.data.code == 200){
            this.$message({
              showClose: true,
              message: '操作成功',
              type: 'success',
              onClose:() => {
                this.getFieldList()
              }
            });
            this.editDialogVisible = false
          }
        })
      },
      // 删除按钮
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
            message: '操作成功',
            type: 'success',
            onClose:() => {
              this.getFieldList()
            }
          });
        })
      },
      // 查看按钮
      view (row) {
        this.viewDialogFormVisible = true
        this.fieldForm = row;
      },
      // 查看确定按钮：查看应该展示所有的信息（可能表格没有展示完全）
      submitRoleHandle(formName) {
        this.viewDialogFormVisible = false
      },
      handleChange(){},
    }
  }
</script>

<style scoped>
  .el-pagination {
    float: right;
    margin-top: 22px;
  }
</style>