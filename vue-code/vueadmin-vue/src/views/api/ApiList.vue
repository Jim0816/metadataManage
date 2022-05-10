<template>
  <div>
    <el-form :inline="true">
      <el-form-item>
        <el-input
            v-model="searchForm.name"
            placeholder="请输入接口名称"
            clearable
        >
        </el-input>
      </el-form-item>

      <el-form-item>
        <el-button @click="getApiList">搜索</el-button>
      </el-form-item>

      <el-form-item>
        <!--				<el-button type="primary" @click="dialogVisible = true" v-if="hasAuth('sys:field:save')">新增</el-button>-->
        <el-button type="primary" @click="dialogVisible = true" >新增</el-button>
      </el-form-item>
      <el-form-item>
        <el-popconfirm title="这是确定批量删除吗？" @confirm="delHandle(null)">
          <!--					<el-button type="danger" slot="reference" :disabled="delBtlStatu" v-if="hasAuth('sys:user:delete')">批量删除</el-button>-->
          <el-button type="danger" slot="reference" :disabled="delBtlStatu" >批量删除</el-button>
        </el-popconfirm>
      </el-form-item>
    </el-form>

    <el-table
        :data="tableData"
        style="width: 100%;margin-bottom: 20px;"
        row-key="id"
        border
        stripe
        default-expand-all
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
          prop="name"
          label="名称"
          width="120">
      </el-table-column>

      <el-table-column
          prop="httpType"
          label="请求类型"
          width="100">
      </el-table-column>

      <el-table-column
          prop="opType"
          label="操作类型"
          width="100">
      </el-table-column>

      <el-table-column
          prop="model"
          label="操作对象"
          width="100">
      </el-table-column>

      <el-table-column
          prop="params"
          label="接口参数"
          width="200">
      </el-table-column>

      <el-table-column
          prop="filter"
          label="过滤条件"
          width="200">
      </el-table-column>

      <el-table-column
              prop="other"
              label="查询设置"
              width="200">
      </el-table-column>

      <el-table-column
          prop="createUser"
          label="创建者"
          width="100">
      </el-table-column>

      <el-table-column
          prop="accessUser"
          label="授权用户">
      </el-table-column>

      <el-table-column
              prop="status"
              label="状态">
        <template slot-scope="scope">
          <el-tag size="small" v-if="scope.row.status === 1" type="success">正常使用</el-tag>
          <el-tag size="small" v-else-if="scope.row.status === 0" type="danger">禁用</el-tag>
        </template>
      </el-table-column>

      <el-table-column
          prop="icon"
          label="操作"
          width="260px">

        <template slot-scope="scope">
          <el-button type="text" @click="editHandle(scope.row.id)">编辑</el-button>
          <el-divider direction="vertical"></el-divider>

          <template>
            <el-popconfirm title="这是一段内容确定删除吗？" @confirm="delHandle(scope.row.id)">
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
        title="新增接口"
        :visible.sync="dialogVisible"
        width="800px">
      <el-dialog
              width="40%"
              title="添加参数"
              :visible.sync="addParamDialogVisible"
              append-to-body>
        <div style="width:100%;height: 200px;overflow-y: auto;" >
          <el-form :model="paramForm" label-width="80px">
            <el-form-item label="参数类型" prop="type">
              <el-select v-model="paramForm.type" placeholder="请选择参数类型" style="width: 100%;">
                <el-option label="模型" value="model"></el-option>
                <el-option label="字段" value="字段"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="参数结构" prop="type">
              <el-select v-model="paramForm.name" filterable placeholder="请选择" style="width: 100%;">
                <el-option
                        v-for="item in paramOptions"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button type="primary" style="float:right;margin-right: 10px;">确 定</el-button>
            <el-button @click="addParamDialogVisible=false" style="float:right;margin-right: 20px;">取 消</el-button>
          </div>
        </div>
      </el-dialog>
      <el-form :model="apiForm" ref="apiForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="接口名称" prop="name">
          <el-input v-model="apiForm.name"></el-input>
        </el-form-item>
        <el-form-item label="请求类型" prop="httpType">
          <el-select v-model="apiForm.httpType" placeholder="请选择接口请求类型" :disabled="true">
            <el-option label="post请求" value="post"></el-option>
            <el-option label="get请求" value="get"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="操作类型" prop="opType">
          <el-select v-model="apiForm.opType" placeholder="请选择接口操作类型">
            <el-option label="put" value="put"></el-option>
            <el-option label="post" value="post"></el-option>
            <el-option label="delete" value="delete"></el-option>
            <el-option label="get" value="get"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="接口参数" prop="params">
          <div style="width: 100%;">
            <div style="width: 100%;height: 40px;">
              <el-button type="primary" size="mini" @click="addParam">添加参数</el-button>
            </div>
            <div style="margin-top:15px; width: 100%;">
              <el-card class="box-card" style="width: 100%;height: 100%;">
                <el-table
                        :data="apiForm.params"
                        style="width: 100%">
                  <el-table-column
                          label="参数名称"
                          width="180">
                    <template slot-scope="scope">
                      <span style="margin-left: 10px">{{ scope.row.name }}</span>
                    </template>
                  </el-table-column>
                  <el-table-column
                          label="参数类型"
                          width="180">
                    <template slot-scope="scope">
                      <el-popover trigger="hover" placement="top">
                        <p>姓名: {{ scope.row.type }}</p>
                        <p>住址: {{ scope.row.detail }}</p>
                        <div slot="reference" class="name-wrapper">
                          <el-tag v-if="scope.row.type == 'model'" size="medium">{{ scope.row.type }}</el-tag>
                          <el-tag v-if="scope.row.type == 'field'" type="success" size="medium">{{ scope.row.type }}</el-tag>
                        </div>
                      </el-popover>
                    </template>
                  </el-table-column>
                  <el-table-column label="操作">
                    <template slot-scope="scope">
                      <el-button
                              size="mini"
                              @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                      <el-button
                              size="mini"
                              type="danger"
                              @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                    </template>
                  </el-table-column>
                </el-table>
              </el-card>
            </div>
          </div>
        </el-form-item>
        <el-form-item label="条件">
          <el-input type="textarea" v-model="apiForm.filter"></el-input>
        </el-form-item>
        <el-form-item v-show="apiForm.opType == 'get'" label="分页" prop="page">
          <el-input v-model="apiForm.name"></el-input>
        </el-form-item>
        <el-form-item v-show="apiForm.opType == 'get'" label="排序" prop="sort">
          <el-input v-model="apiForm.name"></el-input>
        </el-form-item>
        <el-form-item label="备注">
          <el-input type="textarea" v-model="apiForm.remark"></el-input>
        </el-form-item>
        <el-form-item label="权限列表">
          <el-select v-model="value1" multiple placeholder="请选择" style="width: 100%;">
            <el-option
                    v-for="item in userOptions"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="resetForm('editForm')">取 消</el-button>
        <el-button type="primary" @click="submitForm('editForm')">确 定</el-button>
      </div>
    </el-dialog>

  </div>

</template>

<script>
import {  page,  remove } from '@/api/api'
export default {
  name: "Api",
  data() {
    return {
      total: 0,
      size: 10,
      current: 1,

      searchForm: {},
      delBtlStatu: true,
      dialogVisible: false,
      addParamDialogVisible: false,
      editForm: {

      },
      userOptions: [{
        value: '100000',
        label: 'zhangsan@ihep.ac.cn'
      }],
      paramOptions: [{
        value: '10000',
        label: 'user'
      },{
        value: '10001',
        label: 'dept'
      }],
      value1: [],
      apiForm: {
        name: '',
        remark: '',
        httpType: 'post',
        opType: '',
        modelNames: '',
        params: [{
          name: 'user',
          type: 'model',
          detail: '{}'
        },{
          name: 'current',
          type: 'field',
          detail: 'int'
        }],
        filter: 'dept.name = #{deptName} and dept.id = user.dept_id',
        status: 1,
        accessUser: [],
        createUser: -1,
      },
      paramForm:{
        id: -1,
        name: '',
        type: '',
        detail: ''
      },
      editFormRules: {
        name: [
          {required: true, message: '请输入名称', trigger: 'blur'}
        ],
        httpType: [
          {required: true, message: '请选择请求类型', trigger: 'blur'}
        ],
        opType: [
          {required: true, message: '请选择操作类型', trigger: 'blur'}
        ],
        model: [
          {required: true, message: '请输入model', trigger: 'blur'}
        ],
        modelNames: [
          {required: true, message: '请输入modelNames', trigger: 'blur'}
        ],
        data: [
          {required: true, message: '请输入data', trigger: 'blur'}
        ],
        filter: [
          {required: true, message: '请输入filter', trigger: 'blur'}
        ],
        status: [
          {required: true, message: '请选择状态', trigger: 'blur'}
        ],
        createUser: [
          {required: true, message: '请输入接口创建用户ID', trigger: 'blur'}
        ],
        accessUser: [
          {required: true, message: '请输入accessUser', trigger: 'blur'}
        ],
      },
      tableData: []
    }
  },
  created() {
    this.getApiList();
  },
  methods: {
    getApiList() {
      this.searchForm['current'] = this.current;
      this.searchForm['size'] = this.size;
      page(this.searchForm).then(res => {
        console.log(res.data.data)
        this.tableData = res.data.data.records
        this.size = res.data.data.size
        this.current = res.data.data.current
        this.total = res.data.data.total
      })
    },
    // 添加参数
    addParam(){
      this.addParamDialogVisible = true;
    },

    resetForm(formName) {
      this.$refs[formName].resetFields();
      this.dialogVisible = false
      this.editForm = {}
    },

    handleClose() {
      this.resetForm('editForm')
    },

    handleSelectionChange(val) {
      this.multipleSelection = val;
      this.delBtlStatu = val.length == 0
    },

    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.size = val
      this.getApiList()
    },

    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.current = val
      this.getApiList()
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
            this.getApiList()
          }
        });
      })
    },
  }
}
</script>

<style scoped>
.el-pagination {
  float: right;
  margin-top: 22px;
}
</style>
