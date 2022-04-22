<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
    >
      <el-form-item label="邮箱" prop="emailAddress">
        <el-input
          v-model="queryParams.emailAddress"
          placeholder="请输入邮箱"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="归属人" prop="userId">
        <el-select
          v-model="queryParams.userId"
          clearable
          placeholder="请选择归属人"
        >
          <el-option
            v-for="item in userList"
            :key="item.userId"
            :label="item.nickName + '<' + item.email + '>'"
            :value="item.userId"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button
          type="primary"
          icon="el-icon-search"
          size="mini"
          @click="handleQuery"
          >搜索</el-button
        >
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery"
          >重置</el-button
        >
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
          v-hasPermi="['system:info:add']"
          >新增</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:info:edit']"
          >修改</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:info:remove']"
          >删除</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          :loading="exportLoading"
          @click="handleExport"
          v-hasPermi="['system:info:export']"
          >导出</el-button
        >
      </el-col>
      <right-toolbar
        :showSearch.sync="showSearch"
        @queryTable="getList"
      ></right-toolbar>
    </el-row>

    <el-table
      v-loading="loading"
      :data="infoList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column label="邮箱地址" align="center" prop="emailAddress" />
      <el-table-column label="归属人" align="center" prop="userName" />
      <el-table-column label="检测情况" align="center" prop="validFlag" />
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:info:edit']"
            >检测</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:info:edit']"
            >修改</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="bindChange(scope.row)"
            v-hasPermi="['system:info:edit']"
            >改绑</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="unBind(scope.row)"
            v-hasPermi="['system:info:edit']"
            >解绑</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:info:remove']"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 邮箱改绑对话框 -->
    <el-dialog
      title="改绑"
      :visible.sync="showBindChange"
      width="600px"
      append-to-body
    >
      <el-form ref="bindParams" :model="bindParams" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="选择成员" prop="userId">
              <el-select
                v-model="bindParams.userId"
                placeholder="请选择成员"
              >
                <el-option
                  v-for="item in userList"
                  :key="item.userId"
                  :label="item.nickName + '<' + item.email + '>'"
                  :value="item.userId"
                >
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitEmailBind">确 定</el-button>
        <el-button @click="cacelBindChange">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 邮箱解绑对话框 -->
    <el-dialog
      title="解绑"
      :visible.sync="showUnBind"
      width="600px"
      append-to-body
    >
      <el-form ref="bindParams" :model="bindParams" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="12">
           <span>确定要解绑邮箱 {{bindParams.emailAddress}} 吗?</span>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitEmailBind">确 定</el-button>
        <el-button @click="cacelUnBind">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 添加或修改邮箱账户信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="归属人" prop="validFlag">
          <el-select v-model="form.userId" clearable placeholder="请选择归属人">
            <el-option
              v-for="item in userList"
              :key="item.userId"
              :label="item.nickName + '<' + item.email + '>'"
              :value="item.userId"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="邮箱" prop="emailAddress">
          <el-input v-model="form.emailAddress" placeholder="请输入邮箱" />
        </el-form-item>
        <el-form-item label="密码" prop="emailPassword">
          <el-input v-model="form.emailPassword" placeholder="请输入密码" />
        </el-form-item>
        <a v-if="!this.manualConfig" @click="showManualConfig">手动配置</a>
        <el-form-item
          v-if="this.manualConfig"
          label="协议类型"
          prop="receiveProtocol"
        >
          <el-radio-group v-model="form.receiveProtocol">
            <el-radio label="IMAP"></el-radio>
            <el-radio label="POP3"></el-radio>
            <el-radio label="Exchange"></el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item
          v-if="this.manualConfig"
          label="收邮件服务器"
          prop="receiveHost"
        >
          <el-input
            style="width: 50%"
            v-model="form.receiveHost"
            placeholder="收邮件服务器"
          />
          <span> : </span>
          <el-input
            style="width: 20%"
            v-model="form.receivePort"
            placeholder="端口"
          />
          <span></span>
          <el-radio-group v-model="form.receiveSsl">
            <el-checkbox label="SSL"></el-checkbox>
          </el-radio-group>
        </el-form-item>
        <el-form-item
          v-if="this.manualConfig"
          label="发邮件服务器"
          prop="receiveProtocol"
        >
          <el-input
            style="width: 50%"
            v-model="form.sendHost"
            placeholder="发邮件服务器"
          />
          <span> : </span>
          <el-input
            style="width: 20%"
            v-model="form.sendPort"
            placeholder="端口"
          />
          <span></span>
          <el-radio-group v-model="form.sendSsl">
            <el-checkbox label="SSL"></el-checkbox>
          </el-radio-group>
        </el-form-item>
        <a v-if="this.manualConfig" @click="showManualConfig">收起手动配置</a>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  listInfo,
  getInfo,
  delInfo,
  addInfo,
  updateInfo,
  exportInfo,
  bindChange,
} from "@/api/mail/emailInfo";
import { listUser } from "@/api/system/user";

export default {
  name: "Info",
  components: {},
  data() {
    return {
      //手动配置
      manualConfig: false,
      // 用户列表
      userList: [],
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
      // 邮箱账户信息表格数据
      infoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        emailAddress: null,
        userId: null,
      },
      // 修改邮箱绑定人请求参数
      bindParams: {
        emailId: null,
        userId: null,
        emailAddress: null,
      },
      //绑定人
      bindUserId: null,
      //改绑表单显示
      showBindChange: false,
      //解绑表单显示
      showUnBind: false,
      // 表单参数
      form: {},
      // 表单校验
      rules: {},
    };
  },
  created() {
    this.getList();
    this.getUserList();
  },
  methods: {
    /** 邮箱改绑 */
    bindChange(row) {
      this.showBindChange = true;
      this.bindParams.emailId = row.emailId;
      this.bindParams.userId = row.userId;
    },
    /** 邮箱解绑 */
    unBind(row) {
      this.showUnBind = true;
      this.bindParams.emailAddress = row.emailAddress;
      this.bindParams.emailId = row.emailId;
      this.bindParams.userId = null;
    },
    /** 取消改绑邮箱 */
    cacelBindChange() {
      this.showBindChange = false;
      this.resetBindParams();
    },
    /** 取消解绑邮箱 */
    cacelUnBind() {
      this.showUnBind = false;
      this.resetBindParams();
    },
    /** 邮箱绑定修改提交 */
    submitEmailBind() {
      this.showBindChange = false;
      this.showUnBind = false;
      bindChange(this.bindParams).then((response) => {
        this.loading = false;
      });
      this.getList();
    },
    /** 重置改绑邮箱请求参数 */
    resetBindParams(){
      this.bindParams = {
        emailId: null,
        userId: null,
        emailAddress: null,
      }
      this.resetForm("bindParams")
    },
    /** 显示或隐藏手动配置内容 */
    showManualConfig() {
      this.manualConfig = !this.manualConfig;
    },
    /** 查询所有用户列表 */
    getUserList() {
      listUser().then((response) => {
        this.userList = response.rows;
      });
    },
    /** 查询邮箱账户信息列表 */
    getList() {
      this.loading = true;
      listInfo(this.queryParams).then((response) => {
        this.infoList = response.rows;
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
        emailId: null,
        emailAddress: null,
        emailPassword: null,
        emailServerType: null,
        enableFlag: null,
        receiveHost: null,
        receivePort: null,
        receiveProtocol: null,
        receiveSsl: null,
        receiveServer: null,
        receiveServerPwdEnc: null,
        receiveServerUserName: null,
        sendPort: null,
        sendServer: null,
        sendSsl: null,
        smtpHost: null,
        smtpPort: null,
        smtpSsl: null,
        smtpPwdEnc: null,
        smtpUserName: null,
        userId: null,
        validFlag: null,
        deleteFlag: null,
        createdTime: null,
        createdUserName: null,
        createdUserAccount: null,
        updatedTime: null,
        updatedUserName: null,
        updatedUserAccount: null,
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
      this.ids = selection.map((item) => item.emailId);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加邮箱账户信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const emailId = row.emailId || this.ids;
      getInfo(emailId).then((response) => {
        this.form = response.data;
        this.open = true;
        this.title = "修改邮箱账户信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.emailId != null) {
            updateInfo(this.form).then((response) => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addInfo(this.form).then((response) => {
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
      const emailIds = row.emailId || this.ids;
      this.$confirm(
        '是否确认删除邮箱账户信息编号为"' + emailIds + '"的数据项?',
        "警告",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        }
      )
        .then(function () {
          return delInfo(emailIds);
        })
        .then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        });
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm("是否确认导出所有邮箱账户信息数据项?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.exportLoading = true;
          return exportInfo(queryParams);
        })
        .then((response) => {
          this.download(response.msg);
          this.exportLoading = false;
        });
    },
  },
};
</script>
