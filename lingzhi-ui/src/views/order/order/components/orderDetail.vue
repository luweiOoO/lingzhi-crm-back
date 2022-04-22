<template>
  <div class="right-panel-content-box">
    <div class="header">
      <div class="text">{{title}}</div>
      <i class="el-icon-close" @click="close" />
    </div>
    <div class="center-box">
      <div class="content-box  label-align-both">
        <el-form :rules="rules" class="search-form" ref="queryForm" :model="info" label-width="80px">
          <div class="center-title">
            <div class="center-title-text">基本信息</div>
          </div>
          <el-row :gutter="32">
            <el-col :span="12">
              <el-form-item label="订单编号" prop="orderNo">
                <span class="info-label">{{info.orderNo}}</span>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="客户名称" prop="customerName">
                <!-- <span class="info-label">{{info.customerName}}</span> -->
                <span class="info-label">{{info.customerCountry}}/{{info.customerName}}</span>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="32">
            <el-col :span="12">
              <el-form-item label="阿里订单号" prop="alibabaOrderNo">
                <span class="info-label">{{info.alibabaOrderNo}}</span>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="货值总价(人民币)" prop="goodsAmountPriceRmb">
                <span class="info-label">{{info.goodsAmountPriceRmb}}</span>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="32">
            <el-col :span="12">
              <el-form-item label="付款渠道" prop="paymentChannel">
                <span class="info-label">{{info.paymentChannel}}</span>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="快递类别" prop="expressCategory">
                <span class="info-label">{{info.expressCategory}}</span>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row class="line"></el-row>

          <!-- <div class="center-title">
            <div class="center-title-text">收货信息</div>
          </div>

          <el-row :gutter="32">
            <el-col :span="12">
              <el-form-item label="收货人" prop="orderContactName">
                <span class="info-label">{{info.orderContactName}}</span>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="收货电话" prop="orderContactPhone1">
                <span class="info-label">{{info.orderContactPhone1}}</span>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="32">
            <el-col :span="12">
              <el-form-item label="收货地址" prop="orderContactAddress">
                <span class="info-label">{{info.orderContactAddress}}</span>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row class="line"></el-row> -->

          <div class="center-title">
            <div class="center-title-text">运单信息</div>
            <div class="btns">
              <el-button v-if="!isEdit" size="mini" @click="beginEdit" type="primary">绑定</el-button>
              <template v-else>
                <el-button size="mini" type="success" @click="save">保存</el-button>
                <el-button size="mini" @click="cancel">取消</el-button>
              </template>
            </div>
          </div>
          <el-row :gutter="32">
            <el-col :span="12">
              <el-form-item label="运单号" prop="waybillNos">
                <el-input :disabled="!isEdit" v-model="info.waybillNos" />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="运费金额" prop="waybillFare">
                <el-input :disabled=true v-model="info.waybillFare" />
                
                <!-- <span class="info-label">{{info.waybillFare}}"</span> -->
              </el-form-item>
            </el-col>
          </el-row>
          <el-row class="line"></el-row>

          <div class="center-title">
            <div class="center-title-text">产品信息</div>
          </div>

          <el-row :gutter="32">
            <el-table :data="tableList" highlight-current-row stripe ref="Table">
              <el-table-column label="品名" align="center" prop="productName" fixed="left" min-width="180" show-overflow-tooltip />
              <el-table-column label="完成长度" align="center" prop="productLength" min-width="180" show-overflow-tooltip />
              <el-table-column label="颜色" align="center" prop="productColour" width="100" show-overflow-tooltip />
              <el-table-column label="造型" align="center" prop="procuctModel" width="120" show-overflow-tooltip />
              <el-table-column label="规格" align="center" prop="productStandard" width="120" show-overflow-tooltip />
              <el-table-column label="数量" align="center" prop="productNum" width="120" show-overflow-tooltip />
              <el-table-column label="重量" align="center" prop="productWeight" width="120" show-overflow-tooltip />
              <el-table-column label="出场单价(人民币)" align="center" prop="productCostUnitPriceRmb" width="120" show-overflow-tooltip />
              <el-table-column label="贸易单价(外币)" align="center" prop="productSellUnitPriceDollar" min-width="120" show-overflow-tooltip />
              <el-table-column label="贸易价格(外币)" align="center" prop="productSellAmountDollar" min-width="120" show-overflow-tooltip />
              <el-table-column label="出场价格总计" align="center" prop="remarks" min-width="120" show-overflow-tooltip />
            </el-table>
            <pagination v-show="total>0" :total="total" :fixed-bottom="false" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize" @pagination="getList" />
          </el-row>
        </el-form>
      </div>
    </div>
  </div>
</template>

<script>

import { listOrderDetail } from "@/api/order/orderDetail";
import { bindWaybill, getOrder } from "@/api/order/order";
export default {
  name: 'send',
  components: {},
  props: {
    info: {
      type: Object,
      default: () => { }
    },
    dictList: {
      type: Object,
      default: () => { }
    }
  },
  data() {
    return {
      isEdit: false,
      loading: false,
      tableList: [],
      total: 0,
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        orderId: ''
      },
      rules: {
        strategyCode: [
          { required: true, message: "策略编码不能为空", trigger: "blur" }
        ],
      }
    }
  },

  computed: {
    getDate(){
      return (d)=>{
        return d ? d.split(' ')[0] : ''
      }
    },
    title() {
      return '订单信息'
    },
  },
  created() {
  },
  watch: {
    info(val) {
      if (val.id) {
        this.loading = false
        this.isEdit = false
        this.getList()
      }
    }
  },
  methods: {
    beginEdit(){
      this.isEdit = true
    },
    getOrderById(id){
      getOrder(id).then(res=>{
        if(res.code == 200){
          this.$message.success(res.msg)
          this.info = res.data
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    save(){
      this.isEdit = false
      bindWaybill(JSON.stringify(this.info)).then(res=>{
        if (res.code == 200) {
          this.$message.success(res.msg)
          // this.$emit('close')
          this.$emit('refresh')
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    cancel(){
      this.isEdit = false
      this.$set(this.params, 'extendContent', JSON.parse(this.oldExtendContent))
    },
    getList() {
      listOrderDetail(this.queryParams).then(res => {
        this.tableList = res.rows
        this.total = res.total
      })
    },

    close() {
      this.$emit('close')
    },


    formRest() {
      this.params = {
        oriOrderCode: this.info.oriOrderCode,
        oriOrderId: this.info.oriOrderId,
        whCode: '',
        extendContent: {
          logisticsMode: 'car_receive',
          params1: '',
          params2: '',
          params3: '',
          params4: '',
        }
      }
      this.clearValidate()
    },
    clearValidate() {
      this.$refs.queryForm.clearValidate();
    }
  }
}
</script>
<style lang="scss">
.right-panel-content-box {
  .el-form-item__label {
    font-weight: bold;
    text-align: left;
  }
  .el-input,
  .el-select {
    width: 100%;
  }
}
.rightPanel-poper.el-tooltip__popper {
  z-index: 40001 !important;
  background: #f7f8e7;
  color: #000;
  .popper__arrow {
    background: #f7f8e7;
    display: none;
    &::after {
      border-color: #f7f8e7;
    }
  }
}
</style>
<style lang="scss" scoped>
.right-panel-content-box {
  box-sizing: border-box;
  height: 100%;
  display: flex;
  padding: 0 20px;
  flex-direction: column;
  // /deep/ div {
  //   scroll-behavior: auto !important;
  // }
  .header {
    background: #f5f5f5;
    height: 51px;
    border-bottom: solid 1px #ccc;
    display: flex;
    justify-content: space-between;
    padding: 0 20px;
    margin: 0 -20px;
    flex-grow: 0;
    flex-shrink: 0;
    align-items: center;
    .text {
      font-size: 14px;
      font-family: Microsoft YaHei;
      font-weight: bold;
      line-height: 19px;
      color: #333333;
    }
    i {
      cursor: pointer;
    }
  }
  .center-box {
    flex: 1;
    width: 100%;
    overflow-x: hidden;
    overflow-y: auto;
    box-sizing: border-box;
    .search-box {
      padding: 32px 0 0px 0;
    }
    .content-box {
      text-align: left;
      padding: 24px 0px;
      padding-right: 20px;
      .info-label {
        font-size: 14px;
        color: #999;
      }
      .line {
        height: 0px;
        border-bottom: 1px solid #e5e5e5;
        opacity: 1;
        margin: 16px 0 32px 0;
      }
      .center-title {
        padding-bottom: 20px;
        display: flex;
        justify-content: space-between;
        .center-title-text {
          font-size: 16px;
          font-family: Microsoft YaHei;
          font-weight: bold;
          line-height: 21px;
          color: #333333;
          opacity: 1;
          position: relative;
          padding-left: 8px;
          &::before {
            left: 0;
            top: 2px;
            position: absolute;
            display: inline-block;
            content: ' ';
            width: 4px;
            height: 16px;
            background: #00bfa5;
            opacity: 1;
          }
        }
      }
    }
  }
  .bottom-box {
    height: 60px;
    .btns {
      padding-top: 0px;
    }
  }
}
</style>
