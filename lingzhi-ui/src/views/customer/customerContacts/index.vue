<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="所属公司id" prop="customerCompanyId">
        <el-input
          v-model="queryParams.customerCompanyId"
          placeholder="请输入所属公司id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="昵称" prop="contactNickName">
        <el-input
          v-model="queryParams.contactNickName"
          placeholder="请输入昵称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="邮箱" prop="contactEmail">
        <el-input
          v-model="queryParams.contactEmail"
          placeholder="请输入邮箱"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="职级 1普通职员 2中层管理者 3高层管理者" prop="contactRank">
        <el-input
          v-model="queryParams.contactRank"
          placeholder="请输入职级 1普通职员 2中层管理者 3高层管理者"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="职位" prop="contactPosition">
        <el-input
          v-model="queryParams.contactPosition"
          placeholder="请输入职位"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="生日" prop="contactBirthday">
        <el-date-picker clearable size="small"
          v-model="queryParams.contactBirthday"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择生日">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="性别 0未知 1男 2女" prop="contactGender">
        <el-input
          v-model="queryParams.contactGender"
          placeholder="请输入性别 0未知 1男 2女"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="备注" prop="remarks">
        <el-input
          v-model="queryParams.remarks"
          placeholder="请输入备注"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="联系人住址" prop="contactAddress">
        <el-input
          v-model="queryParams.contactAddress"
          placeholder="请输入联系人住址"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="联系人邮箱或电话" prop="contactEamilPhone">
        <el-input
          v-model="queryParams.contactEamilPhone"
          placeholder="请输入联系人邮箱或电话"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建人昵称" prop="createdUserName">
        <el-input
          v-model="queryParams.createdUserName"
          placeholder="请输入创建人昵称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建人账号" prop="createdUserAccount">
        <el-input
          v-model="queryParams.createdUserAccount"
          placeholder="请输入创建人账号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建时间" prop="createdTime">
        <el-date-picker clearable size="small"
          v-model="queryParams.createdTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择创建时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="更新人昵称" prop="updatedUserName">
        <el-input
          v-model="queryParams.updatedUserName"
          placeholder="请输入更新人昵称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="更新人账号" prop="updatedUserAccount">
        <el-input
          v-model="queryParams.updatedUserAccount"
          placeholder="请输入更新人账号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="更新时间" prop="updatedTime">
        <el-date-picker clearable size="small"
          v-model="queryParams.updatedTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择更新时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="删除标志 0未删除 1已删除" prop="isDelete">
        <el-input
          v-model="queryParams.isDelete"
          placeholder="请输入删除标志 0未删除 1已删除"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="所属租户id" prop="tenantId">
        <el-input
          v-model="queryParams.tenantId"
          placeholder="请输入所属租户id"
          clearable
          size="small"
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
          v-hasPermi="['system:contacts:add']"
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
          v-hasPermi="['system:contacts:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:contacts:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
		  :loading="exportLoading"
          @click="handleExport"
          v-hasPermi="['system:contacts:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="contactsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="id" />
      <el-table-column label="所属公司id" align="center" prop="customerCompanyId" />
      <el-table-column label="昵称" align="center" prop="contactNickName" />
      <el-table-column label="邮箱" align="center" prop="contactEmail" />
      <el-table-column label="职级 1普通职员 2中层管理者 3高层管理者" align="center" prop="contactRank" />
      <el-table-column label="职位" align="center" prop="contactPosition" />
      <el-table-column label="生日" align="center" prop="contactBirthday" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.contactBirthday, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="性别 0未知 1男 2女" align="center" prop="contactGender" />
      <el-table-column label="备注" align="center" prop="remarks" />
      <el-table-column label="图片地址" align="center" prop="imageUrl" />
      <el-table-column label="联系人住址" align="center" prop="contactAddress" />
      <el-table-column label="联系人邮箱或电话" align="center" prop="contactEamilPhone" />
      <el-table-column label="创建人昵称" align="center" prop="createdUserName" />
      <el-table-column label="创建人账号" align="center" prop="createdUserAccount" />
      <el-table-column label="创建时间" align="center" prop="createdTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createdTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="更新人昵称" align="center" prop="updatedUserName" />
      <el-table-column label="更新人账号" align="center" prop="updatedUserAccount" />
      <el-table-column label="更新时间" align="center" prop="updatedTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updatedTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="删除标志 0未删除 1已删除" align="center" prop="isDelete" />
      <el-table-column label="所属租户id" align="center" prop="tenantId" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:contacts:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:contacts:remove']"
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

    <!-- 添加或修改客户公司联系人信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="所属公司id" prop="customerCompanyId">
          <el-input v-model="form.customerCompanyId" placeholder="请输入所属公司id" />
        </el-form-item>
        <el-form-item label="昵称" prop="contactNickName">
          <el-input v-model="form.contactNickName" placeholder="请输入昵称" />
        </el-form-item>
        <el-form-item label="邮箱" prop="contactEmail">
          <el-input v-model="form.contactEmail" placeholder="请输入邮箱" />
        </el-form-item>
        <el-form-item label="职级 1普通职员 2中层管理者 3高层管理者" prop="contactRank">
          <el-input v-model="form.contactRank" placeholder="请输入职级 1普通职员 2中层管理者 3高层管理者" />
        </el-form-item>
        <el-form-item label="职位" prop="contactPosition">
          <el-input v-model="form.contactPosition" placeholder="请输入职位" />
        </el-form-item>
        <el-form-item label="生日" prop="contactBirthday">
          <el-date-picker clearable size="small"
            v-model="form.contactBirthday"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择生日">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="性别 0未知 1男 2女" prop="contactGender">
          <el-input v-model="form.contactGender" placeholder="请输入性别 0未知 1男 2女" />
        </el-form-item>
        <el-form-item label="备注" prop="remarks">
          <el-input v-model="form.remarks" placeholder="请输入备注" />
        </el-form-item>
        <el-form-item label="图片地址" prop="imageUrl">
          <el-input v-model="form.imageUrl" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="联系人住址" prop="contactAddress">
          <el-input v-model="form.contactAddress" placeholder="请输入联系人住址" />
        </el-form-item>
        <el-form-item label="联系人邮箱或电话" prop="contactEamilPhone">
          <el-input v-model="form.contactEamilPhone" placeholder="请输入联系人邮箱或电话" />
        </el-form-item>
        <el-form-item label="创建人昵称" prop="createdUserName">
          <el-input v-model="form.createdUserName" placeholder="请输入创建人昵称" />
        </el-form-item>
        <el-form-item label="创建人账号" prop="createdUserAccount">
          <el-input v-model="form.createdUserAccount" placeholder="请输入创建人账号" />
        </el-form-item>
        <el-form-item label="创建时间" prop="createdTime">
          <el-date-picker clearable size="small"
            v-model="form.createdTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择创建时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="更新人昵称" prop="updatedUserName">
          <el-input v-model="form.updatedUserName" placeholder="请输入更新人昵称" />
        </el-form-item>
        <el-form-item label="更新人账号" prop="updatedUserAccount">
          <el-input v-model="form.updatedUserAccount" placeholder="请输入更新人账号" />
        </el-form-item>
        <el-form-item label="更新时间" prop="updatedTime">
          <el-date-picker clearable size="small"
            v-model="form.updatedTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择更新时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="删除标志 0未删除 1已删除" prop="isDelete">
          <el-input v-model="form.isDelete" placeholder="请输入删除标志 0未删除 1已删除" />
        </el-form-item>
        <el-form-item label="所属租户id" prop="tenantId">
          <el-input v-model="form.tenantId" placeholder="请输入所属租户id" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listContacts, getContacts, delContacts, addContacts, updateContacts, exportContacts } from "@/api/customer/customerContacts";

export default {
  name: "Contacts",
  components: {
  },
  data() {
    return {
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
      // 客户公司联系人信息表格数据
      contactsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        customerCompanyId: null,
        contactNickName: null,
        contactEmail: null,
        contactRank: null,
        contactPosition: null,
        contactBirthday: null,
        contactGender: null,
        remarks: null,
        imageUrl: null,
        contactAddress: null,
        contactEamilPhone: null,
        createdUserName: null,
        createdUserAccount: null,
        createdTime: null,
        updatedUserName: null,
        updatedUserAccount: null,
        updatedTime: null,
        isDelete: null,
        tenantId: null
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
    /** 查询客户公司联系人信息列表 */
    getList() {
      this.loading = true;
      listContacts(this.queryParams).then(response => {
        this.contactsList = response.rows;
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
        id: null,
        customerCompanyId: null,
        contactNickName: null,
        contactEmail: null,
        contactRank: null,
        contactPosition: null,
        contactBirthday: null,
        contactGender: null,
        remarks: null,
        imageUrl: null,
        contactAddress: null,
        contactEamilPhone: null,
        createdUserName: null,
        createdUserAccount: null,
        createdTime: null,
        updatedUserName: null,
        updatedUserAccount: null,
        updatedTime: null,
        isDelete: null,
        tenantId: null
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
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加客户公司联系人信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getContacts(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改客户公司联系人信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateContacts(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addContacts(this.form).then(response => {
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
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除客户公司联系人信息编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delContacts(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有客户公司联系人信息数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          this.exportLoading = true;
          return exportContacts(queryParams);
        }).then(response => {
          this.download(response.msg);
          this.exportLoading = false;
        })
    }
  }
};
</script>
