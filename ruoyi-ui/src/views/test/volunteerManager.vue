<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="名字" prop="vtName">
        <el-input
          v-model="queryParams.vtName"
          placeholder="请输入名字"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="身份证号" prop="vtCardno">
        <el-input
          v-model="queryParams.vtCardno"
          placeholder="请输入身份证号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="手机号" prop="vtPhone">
        <el-input
          v-model="queryParams.vtPhone"
          placeholder="请输入手机号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="参加日期" prop="vtJoinDate">
        <el-date-picker clearable
                        v-model="queryParams.vtJoinDate"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="请选择参加日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="现居住地址" prop="volAddress">
        <el-input
          v-model="queryParams.volAddress"
          placeholder="请输入现居住地址"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['test:volunteer:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['test:volunteer:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['test:volunteer:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="info" plain icon="el-icon-upload2" size="mini" @click="handleImport">导入</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport" v-hasPermi="['test:volunteer:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="volunteerList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="志愿者id" align="center" prop="vtId" />
      <el-table-column label="剩余总积分--- 废弃字段" align="center" prop="vtIntegralCount" />
      <el-table-column label="头像" align="center" prop="vtAvatar" />
      <el-table-column label="名字" align="center" prop="vtName" />
      <el-table-column label="身份证号" align="center" prop="vtCardno" />
      <el-table-column label="手机号" align="center" prop="vtPhone" />
      <el-table-column label="村庄id" align="center" prop="vtVillage" />
      <el-table-column label="楼栋主键编号" align="center" prop="vtBuild" />
      <el-table-column label="参加日期" align="center" prop="vtJoinDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.vtJoinDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="志愿者类型id" align="center" prop="vtType" />
      <el-table-column label="备注" align="center" prop="vtRemark" />
      <el-table-column label="删除状态 0否  ；1是" align="center" prop="isDel" />
      <el-table-column label="创建者id" align="center" prop="createId" />
      <el-table-column label="创建者" align="center" prop="createUser" />
      <el-table-column label="更新人" align="center" prop="updateUser" />
      <el-table-column label="更新id" align="center" prop="updateId" />
      <el-table-column label="现居住地址" align="center" prop="volAddress" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['test:volunteer:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['test:volunteer:remove']"
          >删除</el-button>
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

    <!-- 添加或修改test对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="剩余总积分--- 废弃字段" prop="vtIntegralCount">
          <el-input v-model="form.vtIntegralCount" placeholder="请输入剩余总积分--- 废弃字段" />
        </el-form-item>
        <el-form-item label="头像" prop="vtAvatar">
          <el-input v-model="form.vtAvatar" placeholder="请输入头像" />
        </el-form-item>
        <el-form-item label="名字" prop="vtName">
          <el-input v-model="form.vtName" placeholder="请输入名字" />
        </el-form-item>
        <el-form-item label="身份证号" prop="vtCardno">
          <el-input v-model="form.vtCardno" placeholder="请输入身份证号" />
        </el-form-item>
        <el-form-item label="手机号" prop="vtPhone">
          <el-input v-model="form.vtPhone" placeholder="请输入手机号" />
        </el-form-item>


        <el-form-item label="参加日期" prop="vtJoinDate">
          <el-date-picker clearable
                          v-model="form.vtJoinDate"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择参加日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="备注" prop="vtRemark">
          <el-input v-model="form.vtRemark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="删除状态 0否  ；1是" prop="isDel">
          <el-input v-model="form.isDel" placeholder="请输入删除状态 0否  ；1是" />
        </el-form-item>
        <el-form-item label="现居住地址" prop="volAddress">
          <el-input v-model="form.volAddress" placeholder="请输入现居住地址" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
    <!-- 数据导入对话框 -->
    <el-dialog
      :title="upload.title"
      :visible.sync="upload.open"
      width="400px"
      append-to-body
    >
      <el-upload
        ref="upload"
        :limit="1"
        accept=".xlsx, .xls"
        :headers="upload.headers"
        :action="
          upload.url + '?updateSupport=' +  upload.updateSupport +
            '&excelType=' + excelType "
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
          <el-checkbox v-model="upload.updateSupport"/>
          是否更新已经存在的数据
          <el-link type="info" style="font-size:12px" @click="importTemplate"
          >下载模板</el-link
          >
        </div>
        <div class="el-upload__tip" style="color:red" slot="tip">
          提示：仅允许导入“xls”或“xlsx”格式文件！
        </div>
      </el-upload>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitFileForm">确 定</el-button>
        <el-button @click="upload.open = false">取 消</el-button>
      </div>
    </el-dialog>




  </div>



</template>

<script>
import {importTemplate,listVolunteer, getVolunteer, delVolunteer, addVolunteer, updateVolunteer,exportVolunteer } from "@/api/test/volunteer";

import { getToken } from "@/utils/auth";

export default {
    name: "Volunteer",
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
          headers: { Authorization: "Bearer " + getToken() },
          // 上传的地址
          url: process.env.VUE_APP_BASE_API + "/test/volunteer/importData"
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
        // test表格数据
        volunteerList: [],

        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          vtIntegralCount: null,
          vtAvatar: null,
          vtName: null,
          vtCardno: null,
          vtPhone: null,
          vtVillage: null,
          vtBuild: null,
          vtJoinDate: null,
          vtType: null,
          vtRemark: null,
          isDel: null,
          createId: null,
          createUser: null,
          updateUser: null,
          updateId: null,
          volAddress: null
        },

        // 表单参数
        form: {},
        // 表单校验
        rules: {
        }
      };
    },
    created() {
      this.getList();
    },
    methods: {
      /** 查询test列表 */
      getList() {
        this.loading = true;
        listVolunteer(this.queryParams).then(response => {
          this.volunteerList = response.rows;
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
          vtId: null,
          vtIntegralCount: null,
          vtAvatar: null,
          vtName: null,
          vtCardno: null,
          vtPhone: null,
          vtVillage: null,
          vtBuild: null,
          vtJoinDate: null,
          vtType: null,
          vtRemark: null,
          isDel: null,
          createId: null,
          createUser: null,
          createTime: null,
          updateUser: null,
          updateTime: null,
          updateId: null,
          volAddress: null
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
        this.ids = selection.map(item => item.vtId)
        this.single = selection.length!==1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        this.open = true;
        this.title = "添加test";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const vtId = row.vtId || this.ids
        getVolunteer(vtId).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改test";
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.vtId != null) {
              updateVolunteer(this.form).then(response => {
                this.$modal.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              addVolunteer(this.form).then(response => {
                this.$modal.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              });
            }
          }
        });
      },



      /** 删除按钮操作 */
      handleDelete(row) {
        const vtIds = row.vtId || this.ids;
        this.$modal.confirm('是否确认删除test编号为"' + vtIds + '"的数据项？').then(function() {
          return delVolunteer(vtIds);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {});
      },


      /** 导入按钮操作 */
      handleImport(command) {
        this.upload.title = "志愿者数据导入";
        this.excelType = "volunteer";
        this.upload.open = true;
      },
      /** 下载模板操作 */
      importTemplate() {
        this.download('test/volunteer/importTemplate', {
        }, `volunteer_template_${new Date().getTime()}.xlsx`)
      },
      // 文件上传中处理
      handleFileUploadProgress(event, file, fileList) {
        this.upload.isUploading = true;
        this.loading = this.$loading({
          lock: true,
          text: "数据处理中，请稍后...",
          spinner: "el-icon-loading",
          background: "rgba(0, 0, 0, 0.7)"
        });
        //console.log("+++handleFileSuccess"+this.upload.isUploading);
      },
      // 文件上传成功处理
      handleFileSuccess(response, file, fileList) {

        this.upload.open = false;
        this.upload.isUploading = false;

        this.$refs.upload.clearFiles();
        this.loading.close();
        this.$alert(response.msg, "导入结果", {dangerouslyUseHTMLString: true});
        //this.download(response.msg);
        this.getList();
      },
      // 提交上传文件
      submitFileForm() {
        this.$refs.upload.submit();
      },




      /** 导出按钮操作 */
      handleExport() {
        this.download('test/volunteer/exportVolunteer', {
          ...this.queryParams
        }, `volunteer_${new Date().getTime()}.xlsx`)
      }


    }
  };
</script>
