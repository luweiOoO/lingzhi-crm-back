<template>
  <div class="app-container">
    <el-container style="height: 500px; border: 1px solid #eee">
      <el-aside
        width="200px"
        style="background-color: rgb(238, 241, 246); padding: 0px 0px"
      >
        <div style="width: 100%; height: 30px">
          <el-button
            style="height: 100%; width: 50px"
            icon="el-icon-message"
          ></el-button>
          <el-button
            style="height: 100%; width: 50px"
            icon="el-icon-s-custom"
          ></el-button>
          <el-button
            style="height: 100%; width: 50px"
            icon="el-icon-connection"
          ></el-button>
        </div>
        <div style="width: 100%; height: 40px">
          <el-button style="height: 100%; width: 60%; padding: 10px 10%"
            >写信</el-button
          >
        </div>
        <div style="width: 100%; height: 40px">
          <el-button
            style="height: 100%; width: 100%; padding: 10px 10%; border: none"
            >收件箱</el-button
          >
        </div>
        <div style="width: 100%; height: 40px">
          <el-button
            style="height: 100%; width: 100%; padding: 10px 10%; border: none"
            >待处理邮件</el-button
          >
        </div>
        <div style="width: 100%; height: 40px">
          <el-button
            style="height: 100%; width: 100%; padding: 10px 10%; border: none"
            >未读邮件</el-button
          >
        </div>
        <div style="width: 100%; height: 40px">
          <el-button
            style="height: 100%; width: 100%; padding: 10px 10%; border: none"
            >草稿箱</el-button
          >
        </div>
        <div style="width: 100%; height: 40px">
          <el-button
            style="height: 100%; width: 100%; padding: 10px 10%; border: none"
            >发件箱</el-button
          >
        </div>
        <el-menu :default-openeds="['1', '3']">
          <el-submenu index="1">
            <template slot="title"
              ><i class="el-icon-message"></i>导航一</template
            >
            <el-menu-item-group>
              <el-menu-item index="1-1">选项1</el-menu-item>
              <el-menu-item index="1-2">选项2</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="分组2">
              <el-menu-item index="1-3">选项3</el-menu-item>
            </el-menu-item-group>
            <el-submenu index="1-4">
              <template slot="title">选项4</template>
              <el-menu-item index="1-4-1">选项4-1</el-menu-item>
            </el-submenu>
          </el-submenu>
          <el-submenu index="2">
            <template slot="title"><i class="el-icon-menu"></i>导航二</template>
            <el-menu-item-group>
              <template slot="title">分组一</template>
              <el-menu-item index="2-1">选项1</el-menu-item>
              <el-menu-item index="2-2">选项2</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="分组2">
              <el-menu-item index="2-3">选项3</el-menu-item>
            </el-menu-item-group>
            <el-submenu index="2-4">
              <template slot="title">选项4</template>
              <el-menu-item index="2-4-1">选项4-1</el-menu-item>
            </el-submenu>
          </el-submenu>
          <el-submenu index="3">
            <template slot="title"
              ><i class="el-icon-setting"></i>导航三</template
            >
            <el-menu-item-group>
              <template slot="title">分组一</template>
              <el-menu-item index="3-1">选项1</el-menu-item>
              <el-menu-item index="3-2">选项2</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="分组2">
              <el-menu-item index="3-3">选项3</el-menu-item>
            </el-menu-item-group>
            <el-submenu index="3-4">
              <template slot="title">选项4</template>
              <el-menu-item index="3-4-1">选项4-1</el-menu-item>
            </el-submenu>
          </el-submenu>
        </el-menu>
      </el-aside>
      <el-container>
        <el-main style="width: auto">
          <el-table
            v-loading="loading"
            :data="infoList"
            highlight-current-row
            @current-change="getMailDetail"
            @selection-change="handleSelectionChange"
          >
            <el-table-column type="selection" width="55" align="center" />
            <el-table-column
              label="邮件接收人"
              align="center"
              prop="receiver"
            />
            <el-table-column
              label="邮件主题"
              align="center"
              prop="mailSubject"
            />
            <el-table-column
              label="邮件发送人"
              align="center"
              prop="mailSender"
            />
          </el-table>
        </el-main>
        <!-- <el-main
          style="
            width: 6px;
            height: 100%;
            cursor: col-resize;
            background: rgb(248, 248, 249);
            border-top: none;
            border-bottom: none;
          "
        >
        </el-main> -->
        <el-main style="width: 50%" v-if="this.showDetail">
          <!-- <div class="content">
            <div class="subject">
              <p style="font-weight: bold; margin-bottom: 5px">
                {{ header.subject.join(",") }}
              </p>
              <p :title="header.from">
                <span>发件人：</span
                >发件人{{ header.from.join(",").replace(/"/g, "") }}
              </p>
              <p>
                <span>时间：</span
                >{{
                  (header.date.length &&
                    this.$moment(header.date.join(",")).format(
                      "YYYY-MM-DD HH:mm:ss"
                    )) ||
                  ""
                }}
              </p>
              <p :title="header.to">
                <span>收件人：</span
                >{{ header.to.join(",").substr(0, 45).replace(/"/g, "") }}
              </p>
              <p v-if="hasAttachment">
                <span>附件：</span
                >{{
                  body.attachment
                    .map((x) => x.name)
                    .join("、")
                    .substr(0, 45)
                }}
              </p>
            </div>
            <div
              class="attachment clearfix"
              ref="attachment"
              v-if="hasAttachment"
            >
              <div
                style="user-select: none"
                title="点击打开附件"
                @click="open(attach)"
                :key="attach.name"
                class="attach"
                v-for="attach in body.attachment"
              >
                {{ attach.name }}
              </div>
            </div>
            <div class="body detail-body" ref="body" v-if="isIframe">
              <webview
                autosize="on"
                minwidth="576"
                height="832"
                :src="htmlSrc"
                frameborder="0"
                ref="iframe"
                id="iframe"
              ></webview>
            </div>
            <div
              class="body detail-body"
              v-html="body.bodyHtml"
              v-else-if="isHtml"
            ></div>
            <div class="body detail-body" v-else>{{ body.bodyText }}</div>
          </div> -->
          <el-form
            ref="form"
            :model="form"
            :rules="rules"
            label-width="80px"
            style="
              width: 50%;
              padding-left: 10px;
              padding-right: 10px;
              float: left;
            "
          >
          <el-button icon="el-icon-arrow-left" @click="hideMailDetail">返回</el-button>
            <h1
              style="
                min-width: 100px;
                color: #2a2a2a;
                font-size: 18px;
                white-space: nowrap;
              "
            >
              {{ form.mailSubject }}
            </h1>
            <!-- <el-form-item label="主题：" prop="companyName" size="mini">
            </el-form-item> -->
            <el-form-item
              label="发件人: "
              prop="companyName"
              style="white-space: nowrap"
            >
              {{ form.mailSender }}
            </el-form-item>
            <el-form-item
              label="收件人: "
              prop="companyNameShorter"
              style="white-space: nowrap"
            >
              {{ form.receiver }}
            </el-form-item>
            <el-form-item
              label="发送时间: "
              prop="companyNameShorter"
              style="white-space: nowrap"
            >
              {{ form.sendTime }}
            </el-form-item>
            <!-- <el-form-item label="大小" prop="companyNameShorter">
              <el-input :disabled="true" placeholder="请输入公司简称" />
            </el-form-item>
            <el-form-item label="内容" prop="companyNameShorter">
              <el-input :disabled="true" placeholder="请输入公司简称" />
            </el-form-item> -->
            <div class="body detail-body" ref="body" v-if="false">
              <webview
                autosize="on"
                minwidth="576"
                height="832"
                :src="htmlSrc"
                frameborder="0"
                ref="iframe"
                id="iframe"
              ></webview>
            </div>
            <div
              class="body detail-body"
              v-html="form.mailPlainText"
              v-else-if="true"
            ></div>
            <div class="body detail-body" v-else>{{ form.mailPlainText }}</div>
          </el-form>
        </el-main>
      </el-container>
    </el-container>

    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />
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
} from "@/api/mail/mailInfo";

export default {
  name: "Info",
  components: {},
  data() {
    return {
      // 是否显示邮件详情
      showDetail: false,
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
      // 邮件基础信息表格数据
      infoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        mailType: null,
        mailPlainText: null,
        mailSubject: null,
        mailSender: null,
        readFlag: null,
        sendStatus: null,
        folderId: null,
        viewCount: null,
        deleteFlag: null,
      },
      // 表单参数
      form: {
        mailSender: "",
        receiver: "",
        mailSubject: "",
        sendTime: "",
        mailPlainText: ""
      },
      // 表单校验
      rules: {},
    };
  },
  created() {
    this.getList();
    
  },
  methods: {
    /** 隐藏邮件详情 */
    hideMailDetail(){
      this.showDetail = false;
      this.reset();
    },
    /** 查询邮件详情 */
    getMailDetail(currentRow){
      getInfo(currentRow.mailId).then((response) => {
        this.form = response.data;
      });
      this.showDetail = true;
    },
    /** 查询邮件基础信息列表 */
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
        mailId: null,
        mailType: null,
        mailPlainText: null,
        mailSubject: null,
        mailSender: null,
        receiver: null,
        readFlag: null,
        sendStatus: null,
        folderId: null,
        viewCount: null,
        deleteFlag: null,
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
      this.ids = selection.map((item) => item.mailId);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加邮件基础信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const mailId = row.mailId || this.ids;
      getInfo(mailId).then((response) => {
        this.form = response.data;
        this.open = true;
        this.title = "修改邮件基础信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.mailId != null) {
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
      const mailIds = row.mailId || this.ids;
      this.$confirm(
        '是否确认删除邮件基础信息编号为"' + mailIds + '"的数据项?',
        "警告",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        }
      )
        .then(function () {
          return delInfo(mailIds);
        })
        .then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        });
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm("是否确认导出所有邮件基础信息数据项?", "警告", {
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

<style>
.main-plus-nav .mail-nav {
  display: -webkit-box;
  display: -ms-flexbox;
  display: flex;
  -webkit-box-orient: vertical;
  -webkit-box-direction: normal;
  -ms-flex-direction: column;
  flex-direction: column;
  position: relative;
  height: 100%;
  background: #fff;
}
.details {
  padding: 10px;
  font-size: 15px;
  box-sizing: border-box;
  margin-top: 1px;
  overflow-y: auto;
  height: calc(100% - 51px);
}
.details > .header {
  height: 35px;
  padding-left: 10px;
  border-bottom: 1px solid #409eff;
  line-height: 30px;
}
.details .content {
  margin-top: 10px;
  box-sizing: border-box;
  height: calc(100% - 81px);
}
.details .content .subject {
  padding: 10px;
  box-sizing: border-box;
  background: #f9f9f9;
}
.details .content .subject p {
  height: 20px;
  line-height: 20px;
  padding-top: 2px;
}
.details .content .subject p span {
  color: #777777;
  font-size: 14px;
}
.details .content .body,
.details .content .detail-body {
  padding: 10px;
  box-sizing: border-box;
  line-height: 18px;
  height: calc(100% - 85px);
  /* transform: scale3d(1, 1.1, 1); */
  overflow: hidden;
  transform-origin: 0 0 0;
}
.details .attachment {
  border-top: 1px solid #b7b5b5;
  border-bottom: 1px solid #b7b5b5;
  margin-top: 10px;
  padding: 3px 0;
  box-sizing: border-box;
  line-height: 30px;
}
.details .attachment .attach {
  border-radius: 5px;
  margin-right: 10px;
  padding: 0 10px;
  float: left;
}
.details .attachment .attach:hover {
  background-color: #deebf7;
  cursor: pointer;
}
</style>
