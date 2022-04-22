<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
    >
      <el-form-item label="订单编号" prop="orderNo">
        <el-input
          v-model="queryParams.orderNo"
          placeholder="请输入订单编号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="下单日期" prop="orderDate">
        <el-date-picker
          v-model="startAndEndOrderDate"
          type="datetimerange"
          :picker-options="pickerOptions"
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          value-format="yyyy-MM-dd HH:mm:ss"
          @change="GetOrderOrDeliveryTime"
          align="right"
        >
        </el-date-picker>
      </el-form-item>
      <el-form-item label="发货日期" prop="planDeliveryDate">
        <el-date-picker
          v-model="startAndEndDeliveryDate"
          type="datetimerange"
          :picker-options="pickerOptions"
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          value-format="yyyy-MM-dd HH:mm:ss"
          @change="GetOrderOrDeliveryTime"
          align="right"
        >
        </el-date-picker>
      </el-form-item>
      <!-- <el-form-item label="计划发货日期" prop="planDeliveryDate">
        <el-date-picker
          clearable
          size="small"
          v-model="queryParams.planDeliveryDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择计划发货日期"
        >
        </el-date-picker>
      </el-form-item> -->
      <el-form-item label="客户国别" prop="customerCountry">
        <el-input
          v-model="queryParams.customerCountry"
          placeholder="请输入客户国别"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="客户名称" prop="customerName">
        <el-input
          v-model="queryParams.customerName"
          placeholder="请输入客户名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="付款渠道" prop="paymentChannel">
        <el-input
          v-model="queryParams.paymentChannel"
          placeholder="请输入付款渠道"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="阿里订单号" prop="alibabaOrderNo">
        <el-input
          v-model="queryParams.alibabaOrderNo"
          placeholder="请输入阿里订单号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="快递类别" prop="expressCategory">
        <el-input
          v-model="queryParams.expressCategory"
          placeholder="请输入快递类别"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="业务员" prop="orderSalesName">
        <el-input
          v-model="queryParams.orderSalesName"
          placeholder="请输入订单所属业务员姓名"
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
      <!-- <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['order:order:add']"
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
          v-hasPermi="['order:order:edit']"
          >修改</el-button
        >
      </el-col> -->
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['order:order:remove']"
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
          v-hasPermi="['order:order:export']"
        >导出</el-button>
      </el-col> -->
      <right-toolbar
        :showSearch.sync="showSearch"
        @queryTable="getList"
      ></right-toolbar>
    </el-row>

    <el-table
      v-loading="loading"
      :data="orderList"
      @selection-change="handleSelectionChange"
      tooltip-effect="dark"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column fixed label="订单编号" align="center" prop="orderNo" />
      <el-table-column
        label="下单日期"
        align="center"
        prop="orderDate"
        width="180"
      >
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.orderDate, "{y}-{m}-{d}") }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="计划发货日期"
        align="center"
        prop="planDeliveryDate"
        width="180"
      >
        <template slot-scope="scope">
          <span>{{
            parseTime(scope.row.planDeliveryDate, "{y}-{m}-{d}")
          }}</span>
        </template>
      </el-table-column>
      <el-table-column label="客户国别" align="center" prop="customerCountry" />
      <el-table-column label="客户名称" align="center" prop="customerName" />
      <el-table-column
        label="到账金额进度"
        align="center"
        prop="arrivalMoneyPercent"
      />
      <el-table-column
        label="货值总价(外币)"
        align="center"
        prop="goodsAmountPriceDollar"
      />
      <el-table-column
        label="货值总价(人民币)"
        align="center"
        prop="goodsAmountPriceRmb"
      />
      <el-table-column
        label="美元兑人民币实时汇率"
        align="center"
        prop="dollarRmbExchangeRate"
      />
      <el-table-column label="付款渠道" align="center" prop="paymentChannel" />
      <el-table-column
        label="付款手续费"
        align="center"
        prop="paymentServiceCharge"
      />
      <el-table-column
        label="阿里订单号"
        align="center"
        prop="alibabaOrderNo"
      />
      <el-table-column label="快递类别" align="center" prop="expressCategory" />
      <el-table-column
        label="订单质量要求"
        align="center"
        prop="orderQualityRequire"
        show-overflow-tooltip
      />
      <el-table-column
        label="订单包装要求"
        align="center"
        prop="orderPackageRequire"
        show-overflow-tooltip
      />
      <el-table-column
        label="订单发货日期要求"
        align="center"
        prop="orderDeliveryDateRequire"
        show-overflow-tooltip
      />
      <el-table-column
        label="订单所属业务员姓名"
        align="center"
        prop="orderSalesName"
      />
      <el-table-column
        label="订单运单号(多个用逗号隔开)"
        align="center"
        prop="waybillNos"
      />
      <el-table-column label="创建人" align="center" prop="createName" />
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
            @click="(row, col, cellValue) => showRightHandle(scope.row)"
            v-hasPermi="['order:order:edit']"
            >详情</el-button
          >
          <!-- <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['order:order:edit']"
            >详情</el-button
          > -->
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['order:order:remove']"
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
    <!-- 添加或修改生产指示小单基础信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="订单编号" prop="orderNo">
          <el-input v-model="form.orderNo" placeholder="请输入订单编号" />
        </el-form-item>
        <el-form-item label="下单日期" prop="orderDate">
          <el-date-picker
            clearable
            size="small"
            v-model="form.orderDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择下单日期"
          >
          </el-date-picker>
        </el-form-item>
        <el-form-item label="计划发货日期" prop="planDeliveryDate">
          <el-date-picker
            clearable
            size="small"
            v-model="form.planDeliveryDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择计划发货日期"
          >
          </el-date-picker>
        </el-form-item>
        <el-form-item label="客户国别" prop="customerCountry">
          <el-input
            v-model="form.customerCountry"
            placeholder="请输入客户国别"
          />
        </el-form-item>
        <el-form-item label="客户名称" prop="customerName">
          <el-input v-model="form.customerName" placeholder="请输入客户名称" />
        </el-form-item>
        <el-form-item label="到账金额百分比" prop="arrivalMoneyPercent">
          <el-input
            v-model="form.arrivalMoneyPercent"
            placeholder="请输入到账金额百分比"
          />
        </el-form-item>
        <el-form-item label="货值总价(外币)" prop="goodsAmountPriceDollar">
          <el-input
            v-model="form.goodsAmountPriceDollar"
            placeholder="请输入货值总价(外币)"
          />
        </el-form-item>
        <el-form-item label="货值总价(人民币)" prop="goodsAmountPriceRmb">
          <el-input
            v-model="form.goodsAmountPriceRmb"
            placeholder="请输入货值总价(人民币)"
          />
        </el-form-item>
        <el-form-item label="美元兑人民币实时汇率" prop="dollarRmbExchangeRate">
          <el-input
            v-model="form.dollarRmbExchangeRate"
            placeholder="请输入美元兑人民币实时汇率"
          />
        </el-form-item>
        <el-form-item label="付款渠道" prop="paymentChannel">
          <el-input
            v-model="form.paymentChannel"
            placeholder="请输入付款渠道"
          />
        </el-form-item>
        <el-form-item label="付款手续费" prop="paymentServiceCharge">
          <el-input
            v-model="form.paymentServiceCharge"
            placeholder="请输入付款手续费"
          />
        </el-form-item>
        <el-form-item label="阿里订单号" prop="alibabaOrderNo">
          <el-input
            v-model="form.alibabaOrderNo"
            placeholder="请输入阿里订单号"
          />
        </el-form-item>
        <el-form-item label="快递类别" prop="expressCategory">
          <el-input
            v-model="form.expressCategory"
            placeholder="请输入快递类别"
          />
        </el-form-item>
        <el-form-item label="订单质量要求" prop="orderQualityRequire">
          <el-input
            v-model="form.orderQualityRequire"
            type="textarea"
            placeholder="请输入内容"
          />
        </el-form-item>
        <el-form-item label="订单包装要求" prop="orderPackageRequire">
          <el-input
            v-model="form.orderPackageRequire"
            type="textarea"
            placeholder="请输入内容"
          />
        </el-form-item>
        <el-form-item label="订单发货日期要求" prop="orderDeliveryDateRequire">
          <el-input
            v-model="form.orderDeliveryDateRequire"
            type="textarea"
            placeholder="请输入内容"
          />
        </el-form-item>
        <el-form-item label="订单所属业务员id" prop="orderSalesId">
          <el-input
            v-model="form.orderSalesId"
            placeholder="请输入订单所属业务员id"
          />
        </el-form-item>
        <el-form-item label="订单所属业务员姓名" prop="orderSalesName">
          <el-input
            v-model="form.orderSalesName"
            placeholder="请输入订单所属业务员姓名"
          />
        </el-form-item>
        <el-form-item label="订单运单号(多个用逗号隔开)" prop="waybillNos">
          <el-input
            v-model="form.waybillNos"
            type="textarea"
            placeholder="请输入内容"
          />
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
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注" />
        </el-form-item>
        <el-form-item label="删除标记 Y已删除 N未删除" prop="delFlag">
          <el-input
            v-model="form.delFlag"
            placeholder="请输入删除标记 Y已删除 N未删除"
          />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <right-panel :show.sync="showRight" width="900px">
      <order-detail
        ref="send"
        :info="selectedData"
        @refresh="getOrderById(selectedData.id)"
        @close="rightPanelClose"
      />
      <!-- <order-detail ref="send" :info="selectedData" :dictList="mx_dictList" @refresh="getOrderById(selectedData.id)" @close="rightPanelClose" /> -->
    </right-panel>
  </div>
</template>

<script>
import {
  listOrder,
  getOrder,
  delOrder,
  addOrder,
  updateOrder,
  exportOrder,
  importOrder,
} from "@/api/order/order";
import FileUpload from "@/components/FileUpload";
// import RightPanel from "./components/RightPanel.vue";
import { downLoadZip } from "@/utils/zipdownload";
import RightPanel from "@/components/MyRightPanel/RightPanel";
import orderDetail from "./components/orderDetail";

export default {
  name: "Order",
  components: { FileUpload, RightPanel, orderDetail },
  data() {
    return {
      startAndEndOrderDate: "",
      startAndEndDeliveryDate: "",
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
      //选中的指示单
      selectedData: {},
      //右侧指示单详情页弹出框
      showRight: false,
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
      // 生产指示小单基础信息表格数据
      orderList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        orderNo: null,
        orderDate: null,
        startOrderDate: null,
        endOrderDate: null,
        planDeliveryDate: null,
        startPlanDeliveryDate: null,
        endPlanDeliveryDate: null,
        customerCountry: null,
        customerName: null,
        arrivalMoneyPercent: null,
        goodsAmountPriceDollar: null,
        goodsAmountPriceRmb: null,
        dollarRmbExchangeRate: null,
        paymentChannel: null,
        paymentServiceCharge: null,
        alibabaOrderNo: null,
        expressCategory: null,
        orderQualityRequire: null,
        orderPackageRequire: null,
        orderDeliveryDateRequire: null,
        orderSalesId: null,
        orderSalesName: null,
        waybillNos: null,
        createId: null,
        createName: null,
        updateId: null,
        updateName: null,
      },
      // 表单参数
      form: {},
      //导入生产指示单接口地址
      uploadUrl: process.env.VUE_APP_BASE_API + "/order/importOrder",
      // 表单校验
      rules: {},
    };
  },
  created() {
    this.getList();
  },
  watch: {
    showRight(val) {
      if (!val) {
        this.selectedData = {};
      }
    },
  },
  computed: {
    uploadData() {
      return {};
    },
  },
  methods: {
    GetOrderOrDeliveryTime() {
      if (this.startAndEndOrderDate != null) {
        this.queryParams.startOrderDate = this.startAndEndOrderDate[0];
        this.queryParams.endOrderDate = this.startAndEndOrderDate[1];
      }
      if (this.startAndEndDeliveryDate != null) {
        this.queryParams.startPlanDeliveryDate =
          this.startAndEndDeliveryDate[0];
        this.queryParams.endPlanDeliveryDate = this.startAndEndDeliveryDate[1];
      }
    },
    rightPanelClose() {
      this.showRight = false;
    },
    /** 展示单据详情页 */
    showRightHandle(row) {
      this.showRight = true;
      this.selectedData = row;
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
    /** 查询生产指示小单基础信息列表 */
    getList() {
      this.loading = true;
      listOrder(this.queryParams).then((response) => {
        if (response.code == 200) {
          this.$message.success(response.msg);
          this.orderList = response.rows;
          this.total = response.total;
          this.loading = false;
        } else {
          this.$message.error(response.msg);
        }
      });
    },
    getOrderById(id) {
      getOrder(id).then((response) => {
        if (response.code == 200) {
          this.$message.success(response.msg);
          this.selectedData = response.data;
        } else {
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
        orderNo: null,
        orderDate: null,
        planDeliveryDate: null,
        customerCountry: null,
        customerName: null,
        arrivalMoneyPercent: null,
        goodsAmountPriceDollar: null,
        goodsAmountPriceRmb: null,
        dollarRmbExchangeRate: null,
        paymentChannel: null,
        paymentServiceCharge: null,
        alibabaOrderNo: null,
        expressCategory: null,
        orderQualityRequire: null,
        orderPackageRequire: null,
        orderDeliveryDateRequire: null,
        orderSalesId: null,
        orderSalesName: null,
        waybillNos: null,
        createId: null,
        createName: null,
        createTime: null,
        updateId: null,
        updateName: null,
        updateTime: null,
        remark: null,
        delFlag: null,
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
      this.title = "添加生产指示小单基础信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids;
      getOrder(id).then((response) => {
        if (response.code == 200) {
          this.$message.success(response.msg);
          this.form = response.data;
          this.open = true;
          this.title = "修改生产指示小单基础信息";
        } else {
          this.$message.error(response.msg);
        }
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.id != null) {
            updateOrder(this.form).then((response) => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addOrder(this.form).then((response) => {
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
        '是否确认删除生产指示单号为"' + row.orderNo + '"的数据项?',
        "警告",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        }
      )
        .then(function () {
          return delOrder(ids);
        })
        .then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        });
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm("是否确认导出所有生产指示小单基础信息数据项?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.exportLoading = true;
          return exportOrder(queryParams);
        })
        .then((response) => {
          this.download(response.msg);
          this.exportLoading = false;
        });
    },
    /** 导入按钮操作 */
    handleImportOrder(file) {
      this.importOrder(file).then((response) => {
        this.msgSuccess("上传成功");
      });
    },
    /** 导出模板操作 */
    handleDownload() {
      downLoadZip(
        (process.env.NODE_ENV === "development" ? "/oms-system" : "") +
          "/inventory/downTemplate",
        "模板",
        { username: getName() }
      );
    },
  },
};
</script>
