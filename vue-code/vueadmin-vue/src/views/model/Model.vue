<template>

  <div>
    <el-form :inline="true">
      <el-form-item>
        <el-input
            v-model="searchForm.modelName"
            placeholder="模型名称"
            clearable
        >
        </el-input>
      </el-form-item>

      <el-form-item>
        <el-button @click="getModelList">搜索</el-button>
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
      <el-form-item>
        <!--				<el-button type="primary" @click="dialogVisible = true" v-if="hasAuth('sys:field:save')">新增</el-button>-->
        <el-button type="success" @click="uploadTemplate" >上传</el-button>
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
          prop="modelName"
          label="模型名称">
      </el-table-column>

      <el-table-column
          prop="parentModelName"
          label="父模型">
      </el-table-column>

      <el-table-column
              label="字段树"
              width="180">
        <template slot-scope="scope">
          <el-button type="success" icon="el-icon-share">{{ scope.row.fieldTreeName }}</el-button>
        </template>
      </el-table-column>

      <el-table-column
              label="索引"
              width="200">
        <template slot-scope="scope">
          <JsonView :json="scope.row.jsonIndex" closed="true"></JsonView>
        </template>
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
          <el-button type="text" @click="editHandle(scope.row.id)" >编辑</el-button>
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

    <!-- 上传模版对话框 -->
    <el-dialog
      title="上传模版"
      :visible.sync="uploadTemplateVisible"
      width="800px"
      :before-close="closeUploadTemplate">

      <el-upload
        drag
          :limit="1"
          action="https://jsonplaceholder.typicode.com/posts/"
          ref="upload"
          accept=".json"
          :file-list="fileList"
          :on-success="onSuccess"
          :on-remove="onRemove">
            <i class="el-icon-upload"></i>
            <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
            <div class="el-upload__tip" slot="tip">上传json文件，且只能上传 1 个文件</div>
      </el-upload>
      <el-button type="primary" @click="save">确定</el-button>
    
    </el-dialog>

    <!--新增对话框-->
    <el-dialog
        title="新增模型"
        :visible.sync="dialogVisible"
        width="600px"
        :before-close="closeUploadTemplate">

      <el-form :model="editForm" :rules="editFormRules" ref="editForm">

        <el-form-item label="模型名称"  prop="modelName" label-width="100px">
          <el-input v-model="editForm.modelName" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="字段树ID" prop="fieldTreeId" label-width="105px" >
          <el-input-number v-model="editForm.fieldTreeId" @change="handleChange" :min="0" :max="10000" ></el-input-number>
        </el-form-item>

        <el-form-item label="索引" prop="index" label-width="105px" >
          <el-input-number v-model="editForm.index" @change="handleChange" :min="0" :max="10000" ></el-input-number>
        </el-form-item>

        <el-form-item label="父模型ID" prop="parentModelId" label-width="105px" >
          <el-input-number v-model="editForm.parentModelId" @change="handleChange" :min="0" :max="10000" ></el-input-number>
        </el-form-item>

        <el-form-item label="备注"  prop="remark" label-width="100px">
          <el-input v-model="editForm.remark" autocomplete="off"></el-input>
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
import { page, remove, saveByProperties } from '@/api/model'
import JsonView from '@/components/JsonView'
import FileSaver from 'file-saver'

export default {
  name: "Model",
  components: {
    JsonView
  },
  data() {
    return {
      fileList: [],
      uploadData: {},
      uploadTemplateVisible: false,
      searchForm: {},
      delBtlStatu: true,
      // 分页数据
      total: 0,
      size: 10,
      current: 1,

      dialogVisible: false,
      editForm: {},

      tableData: [],
      editFormRules: {
        modelName: [
          {required: true, message: '请输入模型名称', trigger: 'blur'}
        ],
        fieldTreeId:[
          {required: true, message: '请输入字段树ID', trigger: 'blur'}
        ],
        index: [
          {required: true, message: '请输入索引', trigger: 'blur'}
        ],
        parentModelId: [
          {required: true, message: '请输入父模型ID', trigger: 'blur'}
        ],
        remark:[
          {required: false, message: '请输入备注', trigger: 'blur'}
        ]
      },
    }
  },
  created() {
    this.getModelList()
  },
  methods: {
    onSuccess(res, file, fileList) {
      let reader = new FileReader()
      reader.readAsText(file.raw)
      reader.onload = ((e) => {
          this.uploadData = e.target.result
          //console.log(this.uploadData)
          //console.log(this.uploadData)
      })
    },
    onRemove(file) {
      this.fileList = []
    },
    save() {
      //this.$emit('uploadParent', this.uploadData)
      //console.log()
      let data = JSON.parse(this.uploadData)
      console.log(data)
      saveByProperties(data).then(res => {
        console.log(res.data)
        if(res.data.code == 200){
          this.getModelList();
          this.$message({
            showClose: true,
            message: '操作成功',
            type: 'success',
          });
        }else{
          this.$message({
            showClose: true,
            message: '操作失败',
            type: 'error',
          });
        }
        this.uploadTemplateVisible = false
      })

    },
    uploadTemplate(){
      this.uploadTemplateVisible = true
    },
    closeUploadTemplate(){
      this.uploadTemplateVisible = false
    },
    getModelList() {
      this.searchForm['current'] = this.current;
      this.searchForm['size'] = this.size;

      page(this.searchForm).then(res => {
        console.log(res.data)
        this.tableData = res.data.data.records
        this.size = res.data.data.size
        this.current = res.data.data.current
        this.total = res.data.data.total
      });
      // console.log(this.tableData)
      // console.log('分页条件查询模型')
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
      this.getModelList()
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.current = val
      this.getModelList()
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
      this.dialogVisible = false
      this.editForm = {}
    },
    handleClose() {
      this.resetForm('editForm')
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
                    this.getUserList()
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
          message: '操作成功',
          type: 'success',
          onClose:() => {
            this.getModelList()
          }
        });
      })
    },
    handleChange(){}
  }
}
</script>

<style scoped>

.el-pagination {
  float: right;
  margin-top: 22px;
}

.container{
  float: left;
  width: 100%;
  height: 100px;
  background: red;
}
</style>