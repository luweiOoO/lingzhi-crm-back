<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
    >
      <el-form-item label="运单号" prop="waybillNo">
        <el-input
          v-model="queryParams.waybillNo"
          placeholder="请输入运单号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="付款账号" prop="payAccount">
        <el-input
          v-model="queryParams.payAccount"
          placeholder="请输入付款账号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <!-- <el-form-item label="发件日期" prop="deliveryDate">
        <el-date-picker
          clearable
          size="small"
          v-model="queryParams.deliveryDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择发件日期"
        >
        </el-date-picker>
      </el-form-item> -->
      <el-form-item label="发件日期" prop="deliveryDate">
        <el-date-picker
          v-model="startAndEndDeliveryDate"
          type="datetimerange"
          :picker-options="pickerOptions"
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          value-format="yyyy-MM-dd HH:mm:ss"
          @change="GetDeliveryTime"
          align="right"
        >
        </el-date-picker>
      </el-form-item>
      <el-form-item label="发件人" prop="deliveryName">
        <el-input
          v-model="queryParams.deliveryName"
          placeholder="请输入发件人"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="收件人" prop="receiveName">
        <el-input
          v-model="queryParams.receiveName"
          placeholder="请输入收件人"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="收件公司" prop="receiveCompany">
        <el-input
          v-model="queryParams.receiveCompany"
          placeholder="请输入收件公司"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
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
          v-hasPermi="['order:waybill:add']"
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
          v-hasPermi="['order:waybill:edit']"
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
          v-hasPermi="['order:waybill:remove']"
          >删除</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="primary"
          size="mini"
          :loading="importDialog"
          @click="handleImport"
          class="mr-6"
        >
          <svg-icon icon-class="icon_import" class-name="CSGIcon" />
          <span class="btn_font ml-8">导入</span>
        </el-button>
      </el-col>
      <!-- <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
		  :loading="exportLoading"
          @click="handleExport"
          v-hasPermi="['order:waybill:export']"
        >导出</el-button>
      </el-col> -->
      <right-toolbar
        :showSearch.sync="showSearch"
        @queryTable="getList"
      ></right-toolbar>
    </el-row>

    <el-table
      v-loading="loading"
      :data="waybillList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="id" align="center" prop="id" /> -->
      <el-table-column label="运单号" align="center" prop="waybillNo" />
      <el-table-column label="付款账号" align="center" prop="payAccount" />
      <el-table-column
        label="发件日期"
        align="center"
        prop="deliveryDate"
        width="180"
      >
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.deliveryDate, "{y}-{m}-{d}") }}</span>
        </template>
      </el-table-column>
      <el-table-column label="发件人" align="center" prop="deliveryName" />
      <el-table-column label="收件人" align="center" prop="receiveName" />
      <el-table-column label="收件公司" align="center" prop="receiveCompany" />
      <el-table-column
        label="收件地址1"
        align="center"
        prop="receiveAddressA"
      />
      <el-table-column
        label="收件地址2"
        align="center"
        prop="receiveAddressB"
      />
      <el-table-column
        label="收件地址邮编"
        align="center"
        prop="receiveAddressPostalCode"
      />
      <el-table-column
        label="账单日期"
        align="center"
        prop="paymentDate"
        width="180"
      >
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.paymentDate, "{y}-{m}-{d}") }}</span>
        </template>
      </el-table-column>
      <el-table-column label="货物件数" align="center" prop="goodsNumber" />
      <el-table-column label="重量" align="center" prop="goodsWeight" />
      <el-table-column label="货物价值" align="center" prop="goodsPrice" />
      <el-table-column label="运单金额" align="center" prop="waybillFare" />
      <!-- <el-table-column label="创建人id" align="center" prop="createId" /> -->
      <el-table-column label="创建人" align="center" prop="createName" />
      <!-- <el-table-column label="最近一次更新人id" align="center" prop="updateId" /> -->
      <el-table-column
        label="最近一次更新人"
        align="center"
        prop="updateName"
      />
      <el-table-column label="备注" align="center" prop="remark" />
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
            v-hasPermi="['order:waybill:edit']"
            >修改</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['order:waybill:remove']"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <!-- 导入弹窗 -->
    <right-panel width="380px" :show.sync="importDialog">
      <file-upload
        label="生产指示单"
        :uploadUrl="uploadUrl"
        :uploadData="uploadData"
        @ok="uploadFinish"
        @downloadTempHandle="handleDownload"
      />
    </right-panel>

    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改运单信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="运单号" prop="waybillNo">
          <el-input v-model="form.waybillNo" placeholder="请输入运单号" />
        </el-form-item>
        <el-form-item label="付款账号" prop="payAccount">
          <el-input v-model="form.payAccount" placeholder="请输入付款账号" />
        </el-form-item>
        <el-form-item label="发件日期" prop="deliveryDate">
          <el-date-picker
            clearable
            size="small"
            v-model="form.deliveryDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择发件日期"
          >
          </el-date-picker>
        </el-form-item>
        <el-form-item label="发件人" prop="deliveryName">
          <el-input v-model="form.deliveryName" placeholder="请输入发件人" />
        </el-form-item>
        <el-form-item label="收件人" prop="receiveName">
          <el-input v-model="form.receiveName" placeholder="请输入收件人" />
        </el-form-item>
        <el-form-item label="收件公司" prop="receiveCompany">
          <el-input
            v-model="form.receiveCompany"
            placeholder="请输入收件公司"
          />
        </el-form-item>
        <el-form-item label="收件地址1" prop="receiveAddressA">
          <el-input
            v-model="form.receiveAddressA"
            placeholder="请输入收件地址1"
          />
        </el-form-item>
        <el-form-item label="收件地址2" prop="receiveAddressB">
          <el-input
            v-model="form.receiveAddressB"
            placeholder="请输入收件地址2"
          />
        </el-form-item>
        <el-form-item label="收件地址邮编" prop="receiveAddressPostalCode">
          <el-input
            v-model="form.receiveAddressPostalCode"
            placeholder="请输入收件地址邮编"
          />
        </el-form-item>
        <el-form-item label="账单日期" prop="paymentDate">
          <el-date-picker
            clearable
            size="small"
            v-model="form.paymentDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择账单日期"
          >
          </el-date-picker>
        </el-form-item>
        <el-form-item label="货物件数" prop="goodsNumber">
          <el-input v-model="form.goodsNumber" placeholder="请输入货物件数" />
        </el-form-item>
        <el-form-item label="重量" prop="goodsWeight">
          <el-input v-model="form.goodsWeight" placeholder="请输入重量" />
        </el-form-item>
        <el-form-item label="货物价值" prop="goodsPrice">
          <el-input v-model="form.goodsPrice" placeholder="请输入货物价值" />
        </el-form-item>
        <el-form-item label="运单金额" prop="waybillFare">
          <el-input v-model="form.waybillFare" placeholder="请输入运单金额" />
        </el-form-item>
        <el-form-item label="创建人id" prop="createId">
          <el-input v-model="form.createId" placeholder="请输入创建人id" />
        </el-form-item>
        <el-form-item label="创建人姓名" prop="createName">
          <el-input v-model="form.createName" placeholder="请输入创建人姓名" />
        </el-form-item>
        <el-form-item label="最近一次更新人id" prop="updateId">
          <el-input
            v-model="form.updateId"
            placeholder="请输入最近一次更新人id"
          />
        </el-form-item>
        <el-form-item label="最近一次更新人姓名" prop="updateName">
          <el-input
            v-model="form.updateName"
            placeholder="请输入最近一次更新人姓名"
          />
        </el-form-item>
        <el-form-item label="删除标记" prop="delFlag">
          <el-input v-model="form.delFlag" placeholder="请输入删除标记" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注" />
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
import {
  listWaybill,
  getWaybill,
  delWaybill,
  addWaybill,
  updateWaybill,
  exportWaybill,
} from "@/api/order/waybill";
import FileUpload from "@/components/FileUpload";
import RightPanel from "./components/RightPanel.vue";
import { downLoadZip } from "@/utils/zipdownload";

export default {
  name: "Waybill",
  components: { FileUpload, RightPanel },
  data() {
    return {
      startAndEndDeliveryDate: '',
      pickerOptions: {
        shortcuts: [
          {
            text: "最近一周",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit("pick", [start, end]);
            },
          },
          {
            text: "最近一个月",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
              picker.$emit("pick", [start, end]);
            },
          },
          {
            text: "最近三个月",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
              picker.$emit("pick", [start, end]);
            },
          },
        ],
      },
      // 导入弹出框
      importDialog: false,
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
      // 运单信息表格数据
      waybillList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        waybillNo: null,
        payAccount: null,
        deliveryDate: null,
        startDeliveryDate: null,
        endDeliveryDate: null,
        deliveryName: null,
        receiveName: null,
        receiveCompany: null,
        receiveAddressA: null,
        receiveAddressB: null,
        receiveAddressPostalCode: null,
        paymentDate: null,
        goodsNumber: null,
        goodsWeight: null,
        goodsPrice: null,
        waybillFare: null,
        createId: null,
        createName: null,
        updateId: null,
        updateName: null,
      },
      // 表单参数
      form: {},
      //导入运单信息接口地址
      uploadUrl: process.env.VUE_APP_BASE_API + "/waybill/importWaybill",
      // 表单校验
      rules: {},
    };
  },
  created() {
    this.getList();
  },
  computed: {
    uploadData() {
      return {};
    },
  },
  methods: {
    GetDeliveryTime() {
      if (this.startAndEndDeliveryDate != null) {
        this.queryParams.startDeliveryDate = this.startAndEndDeliveryDate[0];
        this.queryParams.endDeliveryDate = this.startAndEndDeliveryDate[1];
      }
    },
    /** 导入按钮操作 */
    handleImport() {
      this.importDialog = true;
    },
    /** 导入完成回调 */
    uploadFinish(data) {
      this.importDialog = false;
      if (data != false) {
        this.handleQuery();
      }
    },
    /** 查询运单信息列表 */
    getList() {
      this.loading = true;
      listWaybill(this.queryParams).then((response) => {
        if(response.code == 200){
          this.$message.success(response.msg);
          this.waybillList = response.rows;
          this.total = response.total;
          this.loading = false;
        }else{
          this.$message.error(response.msg);
        }
        
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
        waybillNo: null,
        payAccount: null,
        deliveryDate: null,
        deliveryName: null,
        receiveName: null,
        receiveCompany: null,
        receiveAddressA: null,
        receiveAddressB: null,
        receiveAddressPostalCode: null,
        paymentDate: null,
        goodsNumber: null,
        goodsWeight: null,
        goodsPrice: null,
        waybillFare: null,
        createId: null,
        createName: null,
        createTime: null,
        updateId: null,
        updateName: null,
        updateTime: null,
        delFlag: null,
        remark: null,
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
      this.ids = selection.map((item) => item.id);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加运单信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids;
      getWaybill(id).then((response) => {
        if(response.code == 200){
          this.$message.success(response.msg);
          this.form = response.data;
          this.open = true;
          this.title = "修改运单信息";
        }else {
          this.$message.error(response.msg);
        }
        
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.id != null) {
            updateWaybill(this.form).then((response) => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addWaybill(this.form).then((response) => {
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
      this.$confirm(
        '是否确认删除运单信息编号为"' + ids + '"的数据项?',
        "警告",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        }
      )
        .then(function () {
          return delWaybill(ids);
        })
        .then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        });
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm("是否确认导出所有运单信息数据项?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.exportLoading = true;
          return exportWaybill(queryParams);
        })
        .then((response) => {
          this.download(response.msg);
          this.exportLoading = false;
        });
    },
  },
};
</script>
