<template>
  <div class="upload-file">

    <div class="header">
      <div class="text">导入{{ label }}</div>
      <i class="el-icon-close" @click="cancelHandler" />
    </div>
    <div class="center-box">
      <div class="router-box">
        <div class="router">
          <div class="step step1">
            <span class="step-icon" v-if="step==0">1</span>
            <span class="bingo" v-else></span>
            <span class="text">上传文件</span>
          </div>
          <div class="arrow"></div>
          <div class="step step2">
            <span class="step-icon" :class="{ gray: step== 0}">2</span>
            <span class="text">确认导入</span>
          </div>
        </div>
      </div>
      <div class="tips-box">
        <div class="tips">Tips：</div>
        <div class="tips-content">
          <span class='red'>*</span><span>请按照模版的格式导入数据，模版的表头不可更改或删除。</span>
        </div>
        <div class="download-template" @click="downloadTmp"><i class="download-icon"></i><span>下载模板</span></div>
      </div>
      <el-upload :action="uploadUrl" :auto-upload="false" :data="uploadData" :before-upload="handleBeforeUpload" :on-change="fileChange" :on-remove="fileRemove" :file-list="fileList" :limit="1" :on-error="handleUploadError" :on-exceed="handleExceed" :on-success="handleUploadSuccess" :show-file-list="true" :headers="headers" class="upload-file-uploader" ref="upload">
        <!-- 上传按钮 -->
        <el-button size="small" trigger class="upload-btn" icon="el-icon-upload2" v-if="!uploading">选取文件</el-button>
        <el-button size="small" class="upload-btn" type="primary" v-else :loading="true">上传中,请耐心等待...</el-button>
      </el-upload>
      <!-- <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="ok" v-if="!uploading">确 定</el-button>
      </span> -->
      <div class="error-box" v-if="faildFileUrl">
        <div class="text1">本次共导入{{ failMsgObj.total }}条：</div>
        <div class="text2">
          <div class="left">
            <span>成功{{ failMsgObj.succNum }}条，</span><span class="red">失败{{ failMsgObj.failNum }}条，可修改后再次上传</span>
          </div>
          <div class="right" ><a download="导入结果.xlsx" :href="faildFileUrl">下载异常数据</a></div>
        </div>
      </div>
    </div>
    <div class="bottom-box">
      <div class="btns">
        <el-button size="small" @click="cancelHandler">取消</el-button>
        <el-button size="small" type="primary" @click="submitUpload">确认导入</el-button>
      </div>
    </div>
  </div>

</template>

<script>
import { getToken, getName } from "@/utils/auth";
// import { checkStatus } from "@/api/oms/storage/inventory";

export default {
  props: {
    // 值
    value: [String, Object, Array],
    // 大小限制(MB)
    fileSize: {
      type: Number,
      default: 5
    },
    // 文件类型, 例如['png', 'jpg', 'jpeg']
    fileType: {
      type: Array,
      default: () => ["xls", "xlsx","csv"]
    },
    // 是否显示提示
    isShowTip: {
      type: Boolean,
      default: true
    },
    /** 标题 */
    label: {
      type: String,
      default: '手工单'
    },
    /** 文件上传url */
    uploadUrl:{
      type: String,
      default:''
    },
    uploadData: {
      type: Object,
      default: () => ({})
    }

  },
  data() {
    return {
      headers: {
        Authorization: "Bearer " + getToken(),
        // username: getName()
      },
      fileList: [], // 文件列表
      uploading: false, // 上传中?
      inter: null, // setinterval轮询句柄
      faildFileUrl: "", // 上传失败后需要下载文件的url
      step: 0,
      failMsgObj: {}, // 导入报错信息
    };
  },
  computed: {
    // 是否显示提示
    showTip() {
      return this.isShowTip && (this.fileType || this.fileSize);
    },
    // 列表
    list() {
      let temp = 1;
      if (this.value) {
        // 首先将值转为数组
        const list = Array.isArray(this.value) ? this.value : [this.value];
        // 然后将数组转为对象数组
        return list.map(item => {
          if (typeof item === "string") {
            item = { name: item, url: item };
          }
          item.uid = item.uid || new Date().getTime() + temp++;
          return item;
        });
      } else {
        this.fileList = [];
        return [];
      }
    }
  },
  methods: {
    downloadTmp(){
      this.$emit('downloadTempHandle')
    },
    /** 提交 */
    submitUpload() {
      this.$refs.upload.submit();
    },
    /** 文件添加，上传成功，上传失败回调 */
    fileChange(file,fileList){
      this.step = fileList.length > 0 ? 1 :0
    },
    /** 从文件列表移除文件回调 */
    fileRemove(file,fileList){
      this.step = fileList.length > 0 ? 1 :0
    },
    // 上传前校检格式和大小
    handleBeforeUpload(file) {
      // 校检文件类型
      if (this.fileType) {
        let fileExtension = "";
        if (file.name.lastIndexOf(".") > -1) {
          fileExtension = file.name.slice(file.name.lastIndexOf(".") + 1);
        }
        const isTypeOk = this.fileType.some(type => {
          if (file.type.indexOf(type) > -1) return true;
          if (fileExtension && fileExtension.indexOf(type) > -1) return true;
          return false;
        });
        if (!isTypeOk) {
          this.msgError(
            `文件格式不正确, 请上传${this.fileType.join("/")}格式文件!`
          );
          return false;
        }
      }
      // 校检文件大小
      // if (this.fileSize) {
      //   const isLt = file.size / 1024 / 1024 < this.fileSize;
      //   if (!isLt) {
      //     this.msgError(`上传文件大小不能超过 ${this.fileSize} MB!`);
      //     return false;
      //   }
      // }
      this.uploading = true;
      return true;
    },
    // 文件个数超出
    handleExceed() {
      this.uploading = false;
      this.msgError(`只允许上传单个文件`);
    },
    // 上传失败
    handleUploadError(err) {
      // 清除轮询器
      if (this.inter) clearInterval(this.inter);

      this.uploading = false;
      this.fileList = [];
      this.msgError("上传失败, 请重试");
    },
    // 上传成功回调
    handleUploadSuccess(res, file) {
      this.fileList = [];
      if (res.code == 200) {
        // if (res.data.result == 0) {
        //   // 导入失败提示一下
        //   this.msgError(res.data.msg);
        //   this.uploading = false;
        //   if (res.data.url) {
        //     this.faildFileUrl = res.data.url;
        //   }
        // }
        // if (res.data.result == 1) {
        //   this.checkStatus(res.data.executeTaskKey);
        // }
        // if (res.data.result == 2) {
          this.msgSuccess("导入成功");
          this.uploading = false;
          // this.$emit("input", res.data.url);
          this.ok()
        // }
      } else {
        // this.msgError(res.msg);
        this.msgError(res.msg)

        this.uploading = false;
      }
    },
    // 关闭当前弹出框
    ok() {
      this.faildFileUrl = ''
      this.$emit("ok");
    },
    // 取消
    cancelHandler() {
      this.faildFileUrl = ''
      this.$emit("ok", false);
    },
    // 轮询检查当前导入文件的状态
    // async checkStatus(key) {
    //   try {
    //     const res = await checkStatus({ key });
    //     if (res.data.result == 0) {

    //       // 导入失败提示一下
    //       this.msgError(res.data.msg);
    //       this.uploading = false;
    //       this.failMsgObj = JSON.parse(res.data.failMsg)
    //       if (res.data.url) {
    //         this.faildFileUrl = res.data.url;
    //       }
    //     }
    //     if (res.data.result == 1) {
    //       // nothing
    //       this.checkStatus(key)
    //     }
    //     if (res.data.result == 2) {

    //       const codes = res.data.orderCodes ? res.data.orderCodes.join(',') : ''
    //       this.$alert(`${this.label == '调拨单' ? '调拨单号' : '来源单号'}：${codes}`, '导入成功', {
    //         confirmButtonText: '确定',
    //         customClass: 'message_box_alert success',
    //         callback: action => { }
    //       });

    //       // this.msgSuccess("导入成功");
    //       this.uploading = false;
    //       this.ok();
    //     }
    //   } catch (error) {
    //     this.msgError('导入失败，请联系管理员');
    //   }
    // }
  },
  beforeDestroy() {
    if (this.inter) {
      clearInterval(this.inter);
    }
  },
  created() {
    this.fileList = this.list;
  }
};
</script>
<style lang="scss">
.upload-file {
  .el-upload {
    text-align: left;
  }
}
</style>
<style scoped lang="scss">
.upload-file {
  height: 100%;
  display: flex;
  padding: 0 20px;
  flex-direction: column;
  .header {
    background: #f5f5f5;
    height: 51px;
    border-bottom: solid 1px #ccc;
    display: flex;
    justify-content: space-between;
    padding: 0 20px;
    margin: 0 -20px;
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
    box-sizing: border-box;
    padding: 28px 0 0 12px;
    border-bottom: solid 1px #e5e5e5;
    .router-box {
      margin-bottom: 32px;
      .router {
        background: #ebf0f5;
        border-radius: 16px;
        width: 100%;
        height: 32px;
        display: flex;
        align-items: center;
        justify-content: space-around;
        .step {
          display: flex;
          align-items: center;
          .bingo {
            width: 24px;
            height: 24px;
            background: url("~@/assets/icons/icon_finished@2x.png") no-repeat;
            background-size: contain;
          }
          .step-icon {
            margin-right: 8px;
            background: url("~@/assets/icons/bg_num_blue.png") no-repeat;
            background-size: contain;
            width: 24px;
            height: 24px;
            text-align: center;
            line-height: 24px;
            font-size: 14px;
            font-family: Noto Sans S Chinese;
            font-weight: 500;
            line-height: 24px;
            color: #ffffff;
            opacity: 1;
            &.gray {
              background: url("~@/assets/icons/bg_num_white.png") no-repeat;
              background-size: 100%;
              color: #9badbc;
            }
          }
          .text {
            font-size: 14px;
            font-family: Microsoft YaHei;
            font-weight: 400;
            line-height: 19px;
            color: #5a6f82;
            opacity: 1;
          }
        }
        .arrow {
          width: 16px;
          height: 16px;
          background: url("~@/assets/icons/icon_step_arr@2x.png") no-repeat;
          background-size: contain;
        }
      }
    }
    .upload-btn {
      margin-top: 17px;
      color: #3d82ea;
      border: 1px solid #3d82ea;
      &:hover {
        color: #fff;
        background: #3d82ea;
      }
      &.is-loading {
        color: #fff;
      }
    }

    .tips-box {

      .tips {
        font-size: 14px;
        font-family: Microsoft YaHei;
        font-weight: 400;
        line-height: 20px;
        height: 20px;
        color: #5a6f82;
        opacity: 1;
      }
      .tips-content {
        padding-top: 16px;

        .red {
          color: #ff4656;
        }
        span {
          font-size: 12px;
          font-family: Microsoft YaHei;
          font-weight: 400;
          line-height: 12px;
          color: #999999;
        }
      }
      .download-template {
        cursor: pointer;
        padding-top: 8px;
        .download-icon {
          display: inline-block;
          background: url("~@/assets/icons/icon_download@2x.png") no-repeat;
          background-size: contain;
          width: 12px;
          height: 12px;
          margin-right: 4px;
          vertical-align: -2px;
        }
        span {
          font-size: 12px;
          font-family: Microsoft YaHei;
          font-weight: 400;
          line-height: 12px;
          color: #3d82ea;
          opacity: 1;
        }
      }
    }

    // <div class="error-box">
    //     <div class="text1">本次共导入50条：</div>
    //     <div class="text2">
    //       <div class="left">
    //         <span>成功48条，</span><span class="red">失败2条，可修改后再次上传</span>
    //       </div>
    //       <div class="right">下载异常数据</div>
    //     </div>
    //   </div>

    .error-box {
      margin-left: -12px;
      margin-top: 24px;
      height: 72px;
      padding: 16px;
      font-size: 12px;
      font-family: Microsoft YaHei;
      font-weight: 400;
      line-height: 18px;
      color: #5a6f82;
      opacity: 1;
      background: #F7F8E7;
      .text1 {
        margin-bottom: 8px;
      }
      .text2 {
        display: flex;
        justify-content: space-between;
        align-items: center;
        .left {
          &.red {
            color: #ff4656;
          }
        }
        .right {
          cursor: pointer;
          font-family: Microsoft YaHei;
          font-weight: 400;
          line-height: 12px;
          color: #3d82ea;
          opacity: 1;
        }
      }
    }
  }
  .bottom-box {
    height: 160px;
    padding-left: 12px;
    .btns {
      padding-top: 48px;
      display: flex;
    }
  }
}

.upload-file-uploader {
  padding-top: 5px;
  margin-bottom: 24px;
}
.upload-file-list .el-upload-list__item {
  border: 1px solid #e4e7ed;
  line-height: 2;
  margin-bottom: 10px;
  position: relative;
}
.upload-file-list .ele-upload-list__item-content {
  display: flex;
  justify-content: space-between;
  align-items: center;
  color: inherit;
}
.ele-upload-list__item-content-action .el-link {
  margin-right: 10px;
}
.red {
  color: #ff4656;
}
</style>
