<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="区域英文名称" prop="regionEnglishName">
        <el-input
          v-model="queryParams.regionEnglishName"
          placeholder="请输入区域英文名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="区域中文名称" prop="regionChineseName">
        <el-input
          v-model="queryParams.regionChineseName"
          placeholder="请输入区域中文名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="座机前缀" prop="regionPhonePrefix">
        <el-input
          v-model="queryParams.regionPhonePrefix"
          placeholder="请输入座机前缀"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="所属父级区域" prop="pRegionId">
        <el-input
          v-model="queryParams.pRegionId"
          placeholder="请选择父级区域"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="区域层级" prop="regionLevel">
        <el-input
          v-model="queryParams.regionLevel"
          placeholder="请输入区域层级"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <!-- <el-form-item label="创建人昵称" prop="createdUserName">
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
      </el-form-item> -->
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
          v-hasPermi="['system:region:add']"
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
          v-hasPermi="['system:region:edit']"
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
          v-hasPermi="['system:region:remove']"
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
          v-hasPermi="['system:region:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="regionList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="id" align="center" prop="id" /> -->
      <el-table-column label="区域英文名称" align="center" prop="regionEnglishName" />
      <el-table-column label="区域中文名称" align="center" prop="regionChineseName" />
      <el-table-column label="座机前缀" align="center" prop="regionPhonePrefix" />
      <el-table-column label="所属父级区域" align="center" prop="pRegionId" />
      <el-table-column label="区域层级" align="center" prop="regionLevel" />
      <!-- <el-table-column label="创建人昵称" align="center" prop="createdUserName" />
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
      </el-table-column> -->
      <!-- <el-table-column label="删除标志 0未删除 1已删除" align="center" prop="isDelete" /> -->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:region:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:region:remove']"
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

    <!-- 添加或修改国家地区信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="区域英文名称" prop="regionEnglishName">
          <el-input v-model="form.regionEnglishName" placeholder="请输入区域英文名称" />
        </el-form-item>
        <el-form-item label="区域中文名称" prop="regionChineseName">
          <el-input v-model="form.regionChineseName" placeholder="请输入区域中文名称" />
        </el-form-item>
        <el-form-item label="座机前缀" prop="regionPhonePrefix">
          <el-input v-model="form.regionPhonePrefix" placeholder="请输入座机前缀" />
        </el-form-item>
        <el-form-item label="所属父级区域" prop="pRegionId">
          <el-input v-model="form.pRegionId" placeholder="请选择父级区域" />
        </el-form-item>
        <!-- <el-form-item label="区域层级" prop="regionLevel">
          <el-input v-model="form.regionLevel" placeholder="请输入区域层级" />
        </el-form-item> -->
        <!-- <el-form-item label="创建人昵称" prop="createdUserName">
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
        </el-form-item> -->
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listRegion, getRegion, delRegion, addRegion, updateRegion, exportRegion } from "@/api/basic/region";

export default {
  name: "Region",
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
      // 国家地区信息表格数据
      regionList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        regionEnglishName: null,
        regionChineseName: null,
        regionPhonePrefix: null,
        pRegionId: null,
        regionLevel: null,
        createdUserName: null,
        createdUserAccount: null,
        createdTime: null,
        updatedUserName: null,
        updatedUserAccount: null,
        updatedTime: null,
        isDelete: null
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
    /** 查询国家地区信息列表 */
    getList() {
      this.loading = true;
      listRegion(this.queryParams).then(response => {
        this.regionList = response.rows;
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
        regionEnglishName: null,
        regionChineseName: null,
        regionPhonePrefix: null,
        pRegionId: null,
        regionLevel: null,
        createdUserName: null,
        createdUserAccount: null,
        createdTime: null,
        updatedUserName: null,
        updatedUserAccount: null,
        updatedTime: null,
        isDelete: null
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
      this.title = "添加国家地区信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getRegion(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改国家地区信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateRegion(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addRegion(this.form).then(response => {
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
      this.$confirm('是否确认删除国家地区信息编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delRegion(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有国家地区信息数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          this.exportLoading = true;
          return exportRegion(queryParams);
        }).then(response => {
          this.download(response.msg);
          this.exportLoading = false;
        })
    }
  }
};
</script>
