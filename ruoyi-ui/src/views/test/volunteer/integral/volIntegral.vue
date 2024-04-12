<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="名字" prop="vtName">
        <el-input
          v-model="queryParams.vtName"
          placeholder="请输入志愿者名字"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="存取状态" prop="inAccessStatus">
        <el-select v-model="queryParams.inAccessStatus" placeholder="请选择积分存取状态" clearable size="small">
          <el-option
            v-for="dict in vtIntegralOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <!--表格上的增删改查-->
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd" v-hasPermi="['system:integral:add']">新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate" v-hasPermi="['system:integral:edit']">修改
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <!-- <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:integral:remove']"
        >删除
        </el-button> -->
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" :loading="exportLoading" @click="handleExport" v-hasPermi="['system:integral:export']">导出
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="info"
          plain
          icon="el-icon-upload2"
          size="mini"
          @click="handleImport"
        >导入</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="integralList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="志愿者积分编号"   v-if="false"  align="center" prop="inId"/>
      <el-table-column label="志愿者id"  v-if="false"   align="center" prop="vtId"/>
      <el-table-column  label="姓名" align="center" prop="vtName"/>
      <el-table-column  label="身份证号" align="center" prop="vtCardNo"/>
      <el-table-column label="积分值" align="center" prop="inIntegralNum"/>
      <el-table-column label="存取状态" align="center" prop="inAccessStatus" :formatter="vtIntegralFormat"/><!--:formatter="vtIntegralFormat"-->
      <el-table-column label="存取时间" align="center" prop="accessTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.accessTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="创建者" align="center" prop="createUser"/>
      <el-table-column label="更新者" align="center" prop="updateUser"/>
      <el-table-column label="备注" align="center" prop="volRemark"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:integral:edit']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:integral:remove']"
          >删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改【请填写功能名称】对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
    <!--    <el-input v-model="form.inVtName"  style="display: none;"/>-->
        <el-input v-model="form.vtId" style="display: none;" />
        <el-row>
          <el-col :span="24">
            <el-form-item label="名字"   prop="inIntegralNum">
              <el-input v-model="form.vtName"  readonly  @click.native="openChoosePeople" placeholder="请选择"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label="积分值" prop="inIntegralNum">
              <el-input v-model="form.inIntegralNum" placeholder="请输入志愿者积分值"/>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="24">
            <el-form-item label="存取状态" prop="inAccessStatus">
              <el-select v-model="form.inAccessStatus" style="width: 100%" placeholder="请选择积分存取状态" >
                <el-option
                  v-for="dict in vtIntegralOptions"
                  :key="dict.dictValue"
                  :label="dict.dictLabel"
                  :value="parseInt(dict.dictValue)">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="存取时间" prop="accessTime">
          <el-date-picker clearable size="small"  style="width: 100%"
                          v-model="form.accessTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="选择存取时间">
          </el-date-picker>
        </el-form-item>
        <el-row>
          <el-col :span="24">
            <el-form-item label="备注" prop="volRemark">
              <el-input v-model="form.volRemark" placeholder="请输入备注"/>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 数据导入对话框 -->
    <el-dialog :title="upload.title" :visible.sync="upload.open" width="400px" append-to-body>
      <el-upload
        ref="upload"
        :limit="1"
        accept=".xlsx, .xls"
        :headers="upload.headers"
        :action="upload.url + '?updateSupport=' + upload.updateSupport +'&excelType='+ excelType"
        :disabled="upload.isUploading"
        :on-progress="handleFileUploadProgress"
        :on-success="handleFileSuccess"
        :auto-upload="false"
        drag
      >
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">
          将文件拖到此处，或
          <em>点击上传</em>
        </div>
        <div class="el-upload__tip" slot="tip">
          <el-link type="info" style="font-size:12px" @click="importTemplate">下载模板</el-link>
        </div>
        <div class="el-upload__tip" style="color:red" slot="tip">提示：仅允许导入“xls”或“xlsx”格式文件！</div>
      </el-upload>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitFileForm">确 定</el-button>
        <el-button @click="upload.open = false">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 人员选择弹窗 -->
    <choose-volunteer ref="import" @volunteer="volunteer" />

  </div>
</template>


<script>
//import {importTemplate,listIntegral, getIntegral, delIntegral, addIntegral, updateIntegral, exportIntegral} from "@/api/system/integral";
//import { getToken } from "@/utils/auth";
//import chooseVolunteer from "../../iframe/chooseVolunteer";

let loading=null;
export default {
    components: { chooseVolunteer },
    name: "Integral",


    data() {
      return {
        excelType: "",
        upload: {
          // 是否显示弹出层（数据导入）
          open: false,
          // 弹出层标题（数据导入）
          title: "",
          // 是否禁用上传
          isUploading: false,
          // 是否更新已经存在的数据
          updateSupport: 0,
          // 设置上传的请求头部
          headers: {Authorization: "Bearer " + getToken()},
          // 上传的地址
          url: process.env.VUE_APP_BASE_API + "/system/integral/importData"
        },
        uploadPath: process.env.VUE_APP_BASE_API + "/common/upload",

        // 遮罩层
        loading: true,
        // 导出遮罩层
        exportLoading: false,
        // 选中数组
        ids: [],
        // 非单个禁用
        single: true,
        // 非多个禁用
        multiple: true,
        // 显示搜索条件
        showSearch: true,
        // 总条数
        total: 0,
        // 【积分明细】表格数据
        integralList: [],
        // 弹出层标题
        title: "",

        // 是否显示弹出层
        open: false,
        //字典
        vtIntegralOptions:[],
        // 设置上传的请求头部
        headers: {Authorization: "Bearer " + getToken()},
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          vtId: null,
          vtName:null,
          vtCardNo:null,
          inTel:null,
          inIntegralNum: null,
          inAccessStatus: null,
          accessTime:null,
          createId: null,
          createUser: null,
          updateUser: null,
          updateId: null,
        },
        // 表单参数
        form: {
          accessTime:null,
        },
        // 表单校验
        rules: {
          vtName: [
            {required: true, message: "志愿者姓名不能为空", trigger: "blur"}
          ],
          inIntegralNum: [
            {required: true, message: "积分不能为空", trigger: "blur"}
          ],
          inAccessStatus: [
            {required: true, message: "存取状态不能为空", trigger: "blur"}
          ],
          accessTime: [
            {required: true, message: "存取时间不能为空", trigger: "blur"}
          ]
        }
      };
    },


    created() {
      this.getList();
      this.getDicts("vt_integral").then(response=>{
        this.vtIntegralOptions = response.data;
      });

    },
    methods: {

      /** 导入按钮操作 */
      handleImport(command) {
        this.upload.title = "志愿者积分数据导入";
        this.excelType = "volunteer"
        this.upload.open = true;
      },
      // 提交上传文件
      submitFileForm() {
        this.$refs.upload.submit();
      },


      /** 下载模板操作 */
      importTemplate() {
        importTemplate(this.excelType).then(response => {
          this.download(response.msg);
        });
      },
      // 文件上传中处理
      handleFileUploadProgress(event, file, fileList) {
        this.upload.isUploading = true;
        this.loading = this.$loading({
          lock: true,
          text: '数据处理中，请稍后...',
          spinner: 'el-icon-loading',
          background: 'rgba(0, 0, 0, 0.7)'
        });
      },
      // 文件上传成功处理
      handleFileSuccess(response, file, fileList) {
        this.upload.open = false;
        this.upload.isUploading = false;
        this.$refs.upload.clearFiles();
        this.loading.close();
        //this.$alert(response.msg, "导入结果", {dangerouslyUseHTMLString: true});
        this.download(response.msg);
        this.getList();
      },



      /** 查询【积分明细】列表 */
      getList() {
        this.loading = true;
        listIntegral(this.queryParams).then(response => {
          this.integralList = response.rows;
          this.total = response.total;
          this.loading = false;
        });
      },

      // 取消按钮
      cancel() {
        this.open = false;
        this.reset();
      },
      // 表单重置
      reset() {
        this.form = {
          inId: null,
          vtId: null,
          vtName: null,
          vtCardNo:null,
          inIntegralNum: null,
          inAccessStatus: null,
          createId: null,
          createUser: null,
          createTime: null,
          updateUser: null,
          updateTime: null,
          updateId: null,
          volRemark: null
        };
        this.resetForm("form");
      },
      /** 搜索按钮操作 */
      handleQuery() {
        this.queryParams.pageNum = 1;
        this.getList();
      },
      /** 重置按钮操作 */
      resetQuery() {
        this.resetForm("queryForm");
        this.handleQuery();
      },
      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.inId)
        this.single = selection.length !== 1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        this.open = true;
        this.title = "添加志愿者积分";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const inId = row.inId || this.ids
        //listIntegral
        getIntegral(inId).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改志愿者积分";
        });
      },

      volunteer(e){
        this.$forceUpdate();
        this.$set(this.form, 'vtId', e.vtId);
        this.$set(this.form, 'vtName', e.vtName+"("+e.vtId+")");
        this.$refs['form'].clearValidate();
      },
      openChoosePeople() {

        this.$refs.import.show();
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.inId != null) {
              updateIntegral(this.form).then(response => {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              addIntegral(this.form).then(response => {
                this.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              });
            }
          }
        });
      },
      /** 删除按钮操作 */
      handleDelete(row) {
        const inIds = row.inId || this.ids;
        this.$confirm('是否确认删除"' + row.vtName + '"的积分信息?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function () {
          return delIntegral(inIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(()=>{}) // 添加错误捕获
      },
      /** 导出按钮操作 */
      handleExport() {
        const queryParams = this.queryParams;
        this.$confirm('是否确认导出所有【积分明细】数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          this.exportLoading = true;
          return exportIntegral(queryParams);
        }).then(response => {
          this.download(response.msg);
          this.exportLoading = false;
        })
      },

      //字典翻译
      vtIntegralFormat(row,column){
        return this.selectDictLabel(this.vtIntegralOptions,row.inAccessStatus);
      }

    }



  };
</script>
