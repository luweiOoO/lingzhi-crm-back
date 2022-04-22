<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="所属订单id" prop="orderId">
        <el-input
          v-model="queryParams.orderId"
          placeholder="请输入所属订单id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="所属订单编码" prop="orderNo">
        <el-input
          v-model="queryParams.orderNo"
          placeholder="请输入所属订单编码"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="产品名称" prop="productName">
        <el-input
          v-model="queryParams.productName"
          placeholder="请输入产品名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="产品完成长度要求" prop="productLength">
        <el-input
          v-model="queryParams.productLength"
          placeholder="请输入产品完成长度要求"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="产品颜色要求" prop="productColour">
        <el-input
          v-model="queryParams.productColour"
          placeholder="请输入产品颜色要求"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="产品造型要求" prop="procuctModel">
        <el-input
          v-model="queryParams.procuctModel"
          placeholder="请输入产品造型要求"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="产品规格要求" prop="productStandard">
        <el-input
          v-model="queryParams.productStandard"
          placeholder="请输入产品规格要求"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="产品数量" prop="productNum">
        <el-input
          v-model="queryParams.productNum"
          placeholder="请输入产品数量"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="产品重量" prop="productWeight">
        <el-input
          v-model="queryParams.productWeight"
          placeholder="请输入产品重量"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="出厂单价(人民币)" prop="productCostUnitPriceRmb">
        <el-input
          v-model="queryParams.productCostUnitPriceRmb"
          placeholder="请输入出厂单价(人民币)"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="贸易单价(外币)" prop="productSellUnitPriceDollar">
        <el-input
          v-model="queryParams.productSellUnitPriceDollar"
          placeholder="请输入贸易单价(外币)"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="贸易总价(外币)" prop="productSellAmountDollar">
        <el-input
          v-model="queryParams.productSellAmountDollar"
          placeholder="请输入贸易总价(外币)"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建人id" prop="createId">
        <el-input
          v-model="queryParams.createId"
          placeholder="请输入创建人id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建人姓名" prop="createName">
        <el-input
          v-model="queryParams.createName"
          placeholder="请输入创建人姓名"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="最近一次更新人id" prop="updateId">
        <el-input
          v-model="queryParams.updateId"
          placeholder="请输入最近一次更新人id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="最近一次更新人姓名" prop="updateName">
        <el-input
          v-model="queryParams.updateName"
          placeholder="请输入最近一次更新人姓名"
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
          v-hasPermi="['order:orderDetail:add']"
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
          v-hasPermi="['order:orderDetail:edit']"
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
          v-hasPermi="['order:orderDetail:remove']"
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
          v-hasPermi="['order:orderDetail:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="orderDetailList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="id" />
      <el-table-column label="所属订单id" align="center" prop="orderId" />
      <el-table-column label="所属订单编码" align="center" prop="orderNo" />
      <el-table-column label="产品名称" align="center" prop="productName" />
      <el-table-column label="产品完成长度要求" align="center" prop="productLength" />
      <el-table-column label="产品颜色要求" align="center" prop="productColour" />
      <el-table-column label="产品造型要求" align="center" prop="procuctModel" />
      <el-table-column label="产品规格要求" align="center" prop="productStandard" />
      <el-table-column label="产品数量" align="center" prop="productNum" />
      <el-table-column label="产品重量" align="center" prop="productWeight" />
      <el-table-column label="出厂单价(人民币)" align="center" prop="productCostUnitPriceRmb" />
      <el-table-column label="贸易单价(外币)" align="center" prop="productSellUnitPriceDollar" />
      <el-table-column label="贸易总价(外币)" align="center" prop="productSellAmountDollar" />
      <el-table-column label="创建人id" align="center" prop="createId" />
      <el-table-column label="创建人姓名" align="center" prop="createName" />
      <el-table-column label="最近一次更新人id" align="center" prop="updateId" />
      <el-table-column label="最近一次更新人姓名" align="center" prop="updateName" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['order:orderDetail:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['order:orderDetail:remove']"
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

    <!-- 添加或修改生产指示单产品详情对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="所属订单id" prop="orderId">
          <el-input v-model="form.orderId" placeholder="请输入所属订单id" />
        </el-form-item>
        <el-form-item label="所属订单编码" prop="orderNo">
          <el-input v-model="form.orderNo" placeholder="请输入所属订单编码" />
        </el-form-item>
        <el-form-item label="产品名称" prop="productName">
          <el-input v-model="form.productName" placeholder="请输入产品名称" />
        </el-form-item>
        <el-form-item label="产品完成长度要求" prop="productLength">
          <el-input v-model="form.productLength" placeholder="请输入产品完成长度要求" />
        </el-form-item>
        <el-form-item label="产品颜色要求" prop="productColour">
          <el-input v-model="form.productColour" placeholder="请输入产品颜色要求" />
        </el-form-item>
        <el-form-item label="产品造型要求" prop="procuctModel">
          <el-input v-model="form.procuctModel" placeholder="请输入产品造型要求" />
        </el-form-item>
        <el-form-item label="产品规格要求" prop="productStandard">
          <el-input v-model="form.productStandard" placeholder="请输入产品规格要求" />
        </el-form-item>
        <el-form-item label="产品数量" prop="productNum">
          <el-input v-model="form.productNum" placeholder="请输入产品数量" />
        </el-form-item>
        <el-form-item label="产品重量" prop="productWeight">
          <el-input v-model="form.productWeight" placeholder="请输入产品重量" />
        </el-form-item>
        <el-form-item label="出厂单价(人民币)" prop="productCostUnitPriceRmb">
          <el-input v-model="form.productCostUnitPriceRmb" placeholder="请输入出厂单价(人民币)" />
        </el-form-item>
        <el-form-item label="贸易单价(外币)" prop="productSellUnitPriceDollar">
          <el-input v-model="form.productSellUnitPriceDollar" placeholder="请输入贸易单价(外币)" />
        </el-form-item>
        <el-form-item label="贸易总价(外币)" prop="productSellAmountDollar">
          <el-input v-model="form.productSellAmountDollar" placeholder="请输入贸易总价(外币)" />
        </el-form-item>
        <el-form-item label="创建人id" prop="createId">
          <el-input v-model="form.createId" placeholder="请输入创建人id" />
        </el-form-item>
        <el-form-item label="创建人姓名" prop="createName">
          <el-input v-model="form.createName" placeholder="请输入创建人姓名" />
        </el-form-item>
        <el-form-item label="最近一次更新人id" prop="updateId">
          <el-input v-model="form.updateId" placeholder="请输入最近一次更新人id" />
        </el-form-item>
        <el-form-item label="最近一次更新人姓名" prop="updateName">
          <el-input v-model="form.updateName" placeholder="请输入最近一次更新人姓名" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注" />
        </el-form-item>
        <el-form-item label="删除标志 Y已删除 N未删除" prop="delFlag">
          <el-input v-model="form.delFlag" placeholder="请输入删除标志 Y已删除 N未删除" />
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
import { listOrderDetail, getOrderDetail, delOrderDetail, addOrderDetail, updateOrderDetail, exportOrderDetail } from "@/api/order/orderDetail";

export default {
  name: "OrderDetail",
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
      // 生产指示单产品详情表格数据
      orderDetailList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        orderId: null,
        orderNo: null,
        productName: null,
        productLength: null,
        productColour: null,
        procuctModel: null,
        productStandard: null,
        productNum: null,
        productWeight: null,
        productCostUnitPriceRmb: null,
        productSellUnitPriceDollar: null,
        productSellAmountDollar: null,
        createId: null,
        createName: null,
        updateId: null,
        updateName: null,
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
    /** 查询生产指示单产品详情列表 */
    getList() {
      this.loading = true;
      listOrderDetail(this.queryParams).then(response => {
        this.orderDetailList = response.rows;
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
        orderId: null,
        orderNo: null,
        productName: null,
        productLength: null,
        productColour: null,
        procuctModel: null,
        productStandard: null,
        productNum: null,
        productWeight: null,
        productCostUnitPriceRmb: null,
        productSellUnitPriceDollar: null,
        productSellAmountDollar: null,
        createId: null,
        createName: null,
        createTime: null,
        updateId: null,
        updateName: null,
        updateTime: null,
        remark: null,
        delFlag: null
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
      this.title = "添加生产指示单产品详情";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getOrderDetail(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改生产指示单产品详情";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateOrderDetail(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addOrderDetail(this.form).then(response => {
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
      this.$confirm('是否确认删除生产指示单产品详情编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delOrderDetail(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有生产指示单产品详情数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          this.exportLoading = true;
          return exportOrderDetail(queryParams);
        }).then(response => {
          this.download(response.msg);
          this.exportLoading = false;
        })
    }
  }
};
</script>
