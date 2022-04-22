<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="租户名称" prop="tenantName">
        <el-input
          v-model="queryParams.tenantName"
          placeholder="请输入租户名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="租户有效期" prop="validTime">
        <el-date-picker clearable size="small"
          v-model="queryParams.validTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择租户有效期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="租户所有人账号id" prop="ownerUserId">
        <el-input
          v-model="queryParams.ownerUserId"
          placeholder="请输入租户所有人账号id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <!-- <el-form-item label="删除标志 0未删除 1已删除" prop="isDelete">
        <el-input
          v-model="queryParams.isDelete"
          placeholder="请输入删除标志 0未删除 1已删除"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <el-form-item label="租户状态 0启用 1停用" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择租户状态 0启用 1停用" clearable size="small">
          <el-option label="请选择字典生成" value="" />
        </el-select>
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
          v-hasPermi="['system:tenant:add']"
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
          v-hasPermi="['system:tenant:edit']"
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
          v-hasPermi="['system:tenant:remove']"
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
          v-hasPermi="['system:tenant:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="tenantList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="租户id" align="center" prop="tenantId" />
      <el-table-column label="租户名称" align="center" prop="tenantName" />
      <el-table-column label="租户有效期" align="center" prop="validTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.validTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="租户所有人账号id" align="center" prop="ownerUserId" />
      <!-- <el-table-column label="删除标志 0未删除 1已删除" align="center" prop="isDelete" /> -->
      <el-table-column label="租户状态 0启用 1停用" align="center" prop="status" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:tenant:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:tenant:remove']"
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

    <!-- 添加或修改租户信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="租户名称" prop="tenantName">
          <el-input v-model="form.tenantName" placeholder="请输入租户名称" />
        </el-form-item>
        <el-form-item label="租户有效期" prop="validTime">
          <el-date-picker clearable size="small"
            v-model="form.validTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择租户有效期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="租户所有人账号id" prop="ownerUserId">
          <el-input v-model="form.ownerUserId" placeholder="请输入租户所有人账号id" />
        </el-form-item>
        <el-form-item label="删除标志 0未删除 1已删除" prop="isDelete">
          <el-input v-model="form.isDelete" placeholder="请输入删除标志 0未删除 1已删除" />
        </el-form-item>
        <el-form-item label="租户状态 0启用 1停用">
          <el-radio-group v-model="form.status">
            <el-radio label="1">请选择字典生成</el-radio>
          </el-radio-group>
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
import { listTenant, getTenant, delTenant, addTenant, updateTenant, exportTenant } from "@/api/system/tenant";

export default {
  name: "Tenant",
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
      // 租户信息表格数据
      tenantList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        tenantName: null,
        validTime: null,
        ownerUserId: null,
        isDelete: null,
        status: null
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
    /** 查询租户信息列表 */
    getList() {
      this.loading = true;
      listTenant(this.queryParams).then(response => {
        this.tenantList = response.rows;
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
        tenantId: null,
        tenantName: null,
        validTime: null,
        ownerUserId: null,
        isDelete: null,
        status: 0
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
      this.ids = selection.map(item => item.tenantId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加租户信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const tenantId = row.tenantId || this.ids
      getTenant(tenantId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改租户信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.tenantId != null) {
            updateTenant(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addTenant(this.form).then(response => {
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
      const tenantIds = row.tenantId || this.ids;
      this.$confirm('是否确认删除租户信息编号为"' + tenantIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delTenant(tenantIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有租户信息数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          this.exportLoading = true;
          return exportTenant(queryParams);
        }).then(response => {
          this.download(response.msg);
          this.exportLoading = false;
        })
    }
  }
};
</script>
