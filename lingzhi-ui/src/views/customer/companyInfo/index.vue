<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
    >
      <el-form-item label="公司名称" prop="companyName">
        <el-input
          v-model="queryParams.companyName"
          placeholder="请输入公司名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="公司简称" prop="companyNameShorter">
        <el-input
          v-model="queryParams.companyNameShorter"
          placeholder="请输入公司简称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="客户来源" prop="customerSourceId">
        <el-select
          v-model="queryParams.customerSourceId"
          clearable
          placeholder="请选择客户来源"
        >
          <el-option
            v-for="item in customerSourceList"
            :key="item.id"
            :label="item.sourceName"
            :value="item.id"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="所属国家区域" prop="regionId">
        <el-select
          v-model="queryParams.regionId"
          clearable
          placeholder="请选择国家区域"
        >
          <el-option
            v-for="item in regionList"
            :key="item.id"
            :label="item.regionWholeName"
            :value="item.id"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="公司规模" prop="companySizeKey">
        <el-select
          v-model="queryParams.companySizeKey"
          clearable
          placeholder="请选择公司规模"
        >
          <el-option
            v-for="item in companySizeOptions"
            :key="item.dictValue"
            :label="item.dictLabel"
            :value="item.dictValue"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="客户类别" prop="customerCategoryId">
        <el-select
          v-model="queryParams.customerCategoryId"
          clearable
          placeholder="请选择客户类别"
        >
          <el-option
            v-for="item in categoryList"
            :key="item.id"
            :label="item.categoryName"
            :value="item.id"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="客户所属分组" prop="customerGourpId">
        <el-select
          v-model="queryParams.customerGourpId"
          clearable
          placeholder="请选择客户所属分组"
        >
          <el-option
            v-for="item in groupList"
            :key="item.id"
            :label="item.groupName"
            :value="item.id"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="客户所属公海分组" prop="customerPublicGourpId">
        <el-select
          v-model="queryParams.customerPublicGourpId"
          clearable
          placeholder="请选择客户所属公海分组"
        >
          <el-option
            v-for="item in publicGroupList"
            :key="item.id"
            :label="item.publicGroupName"
            :value="item.id"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="客户状态" prop="customerStatusId">
        <el-select
          v-model="queryParams.customerStatusId"
          clearable
          placeholder="请选择客户状态"
        >
          <el-option
            v-for="item in statusList"
            :key="item.id"
            :label="item.statusName"
            :value="item.id"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="客户标签" prop="customerLabelId">
        <el-select
          v-model="queryParams.customerLabelId"
          clearable
          placeholder="请选择客户标签"
        >
          <el-option
            v-for="item in labelList"
            :key="item.id"
            :label="item.labelName"
            :value="item.id"
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
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="id" align="center" prop="id" /> -->
      <el-table-column label="客户编码" align="center" prop="customerCode" />
      <el-table-column label="公司名称" align="center" prop="companyName" />
      <el-table-column
        label="公司简称"
        align="center"
        prop="companyNameShorter"
      />
      <el-table-column
        label="客户来源"
        align="center"
        prop="customerSourceName"
      />
      <el-table-column
        label="客户年采购额"
        align="center"
        prop="customerProcurementValue"
      />
      <el-table-column
        label="客户采购意向"
        align="center"
        prop="purchaseIntentionValue"
      />
      <!-- <el-table-column
        label="主营产品类别id"
        align="center"
        prop="mainProductCategoryId"
      /> -->
      <el-table-column label="所属国家区域" align="center" prop="regionName" />
      <el-table-column label="所属时区" align="center" prop="timeZoneName" />
      <el-table-column
        label="公司规模"
        align="center"
        prop="companySizeValue"
      />
      <el-table-column
        label="客户类别"
        align="center"
        prop="customerCategoryName"
      />
      <el-table-column
        label="客户所属分组"
        align="center"
        prop="customerGourpName"
      />
      <el-table-column
        label="客户所属公海分组"
        align="center"
        prop="customerPublicGourpName"
      />
      <el-table-column
        label="客户状态"
        align="center"
        prop="customerStatusName"
      />
      <el-table-column
        label="客户标签"
        align="center"
        prop="customerLabelName"
      />
      <el-table-column
        label="客户星级"
        align="center"
        prop="customerStarLevel"
      />
      <el-table-column
        label="下次跟进时间"
        align="center"
        prop="nextFollowTime"
        width="180"
      >
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.nextFollowTime, "{y}-{m}-{d}") }}</span>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="companyRemarks" />
      <el-table-column
        label="座机前缀"
        align="center"
        prop="regionPhonePrefix"
      />
      <el-table-column label="座机" align="center" prop="phone" />
      <el-table-column label="图片" align="center" prop="companyImageUrl" />
      <el-table-column
        label="主页地址"
        align="center"
        prop="companyHomepageUrl"
      />
      <el-table-column label="传真" align="center" prop="customerFax" />
      <!-- <el-table-column label="所属租户id" align="center" prop="tenantId" /> -->
      <el-table-column
        label="创建人昵称"
        align="center"
        prop="createdUserName"
      />
      <!-- <el-table-column label="创建人账号" align="center" prop="createdUserAccount" /> -->
      <el-table-column
        label="创建时间"
        align="center"
        prop="createdTime"
        width="180"
      >
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createdTime, "{y}-{m}-{d}") }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="更新人昵称"
        align="center"
        prop="updatedUserName"
      />
      <!-- <el-table-column label="更新人账号" align="center" prop="updatedUserAccount" /> -->
      <el-table-column
        label="更新时间"
        align="center"
        prop="updatedTime"
        width="180"
      >
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updatedTime, "{y}-{m}-{d}") }}</span>
        </template>
      </el-table-column>
      <!-- <el-table-column label="删除标志 0未删除 1已删除" align="center" prop="isDelete" /> -->
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
            >修改</el-button
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

    <!-- 添加或修改客户公司基础信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="80%" append-to-body>
      <div class="el-dialog-div">
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
          <el-form-item label="公司名称" prop="companyName">
            <el-input v-model="form.companyName" placeholder="请输入公司名称" />
          </el-form-item>
          <el-form-item label="公司简称" prop="companyNameShorter">
            <el-input
              v-model="form.companyNameShorter"
              placeholder="请输入公司简称"
            />
          </el-form-item>
          <el-form-item label="客户来源" prop="customerSourceId">
            <el-select
              v-model="form.customerSourceId"
              clearable
              placeholder="请选择客户来源"
            >
              <el-option
                v-for="item in customerSourceList"
                :key="item.id"
                :label="item.sourceName"
                :value="item.id"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="客户年采购额" prop="customerProcurementKey">
            <el-select
              v-model="form.customerProcurementKey"
              clearable
              placeholder="请选择客户年采购额"
            >
              <el-option
                v-for="item in customerProcurementOptions"
                :key="item.dictValue"
                :label="item.dictLabel"
                :value="item.dictValue"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="客户采购意向" prop="purchaseIntentionKey">
            <el-select
              v-model="form.purchaseIntentionKey"
              clearable
              placeholder="请选择客户采购意向"
            >
              <el-option
                v-for="item in purchaseIntentionOptions"
                :key="item.dictValue"
                :label="item.dictLabel"
                :value="item.dictValue"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <div
            v-for="(item, index) in form.mainProductCategoryIdList"
            :key="index"
            style="width: 100%"
          >
            <label class="el-form-item__label" style="width: 80px"
              >主营产品类别</label
            >
            <el-form-item
              :prop="'mainProductCategoryIdList.' + index + '.label'"
            >
              <el-row :gutter="20">
                <el-col :span="1" style="width: 80%">
                  <el-cascader
                    v-model="form.mainProductCategoryIdList[index]"
                    :options="categoryTreeList"
                    :props="{
                      value: 'id',
                      label: 'label',
                      checkStrictly: true,
                      emitPath: false,
                    }"
                    clearable
                  ></el-cascader>
                </el-col>
                <el-col :span="1"
                  ><i
                    class="el-icon-delete"
                    v-if="index > 0"
                    @click="delProductCategory(index)"
                  ></i
                ></el-col>
                <el-col :span="1"
                  ><i
                    class="el-icon-plus"
                    @click="addProductCategory(index)"
                  ></i
                ></el-col>
              </el-row>
            </el-form-item>
          </div>
          <!-- <el-form-item label="主营产品类别" prop="mainProductCategoryId">
            <el-input
              v-model="form.mainProductCategoryId"
              placeholder="请选择主营产品类别"
            />
            <el-cascader
            v-model="form.pCategoryId"
            :options="categoryTreeList"
            :props="{ value: 'id', label: 'label', checkStrictly: true,emitPath: false }"
            clearable
          ></el-cascader>
          </el-form-item> -->
          <el-form-item label="所属国家区域" prop="regionId">
            <el-select
              v-model="form.regionId"
              clearable
              placeholder="请选择国家区域"
            >
              <el-option
                v-for="item in regionList"
                :key="item.id"
                :label="item.regionWholeName"
                :value="item.id"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="所属时区" prop="timeZoneId">
            <el-select
              v-model="form.timeZoneId"
              clearable
              placeholder="请选择所属时区"
            >
              <el-option
                v-for="item in timeZoneList"
                :key="item.id"
                :label="item.zoneName"
                :value="item.id"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="公司规模" prop="companySizeKey">
            <el-select
              v-model="form.companySizeKey"
              clearable
              placeholder="请选择公司规模"
            >
              <el-option
                v-for="item in companySizeOptions"
                :key="item.dictValue"
                :label="item.dictLabel"
                :value="item.dictValue"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="客户类别" prop="customerCategoryId">
            <el-select
              v-model="form.customerCategoryId"
              clearable
              placeholder="请选择客户类别"
            >
              <el-option
                v-for="item in categoryList"
                :key="item.id"
                :label="item.categoryName"
                :value="item.id"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="客户所属分组" prop="customerGourpId">
            <el-select
              v-model="form.customerGourpId"
              clearable
              placeholder="请选择客户分组"
            >
              <el-option
                v-for="item in groupList"
                :key="item.id"
                :label="item.groupName"
                :value="item.id"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="客户所属公海分组" prop="customerPublicGourpId">
            <el-select
              v-model="form.customerPublicGourpId"
              clearable
              placeholder="请选择客户公海分组"
            >
              <el-option
                v-for="item in publicGroupList"
                :key="item.id"
                :label="item.publicGroupName"
                :value="item.id"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="客户状态" prop="customerStatusId">
            <el-select
              v-model="form.customerStatusId"
              clearable
              placeholder="请选择客户状态"
            >
              <el-option
                v-for="item in statusList"
                :key="item.id"
                :label="item.statusName"
                :value="item.id"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="客户标签" prop="customerLabelId">
            <el-select
              v-model="form.customerLabelId"
              clearable
              placeholder="请选择客户标签"
            >
              <el-option
                v-for="item in labelList"
                :key="item.id"
                :label="item.labelName"
                :value="item.id"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="客户星级" prop="customerStarLevelKey">
            <el-select
              v-model="form.customerStarLevelKey"
              clearable
              placeholder="请选择客户星级"
            >
              <el-option
                v-for="item in customerStarLevelOptions"
                :key="item.dictValue"
                :label="item.dictLabel"
                :value="item.dictValue"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="下次跟进时间" prop="nextFollowTime">
            <el-date-picker
              clearable
              size="small"
              v-model="form.nextFollowTime"
              type="date"
              value-format="yyyy-MM-dd"
              placeholder="选择下次跟进时间"
            >
            </el-date-picker>
          </el-form-item>
          <el-form-item label="备注" prop="companyRemarks">
            <el-input
              v-model="form.companyRemarks"
              type="textarea"
              placeholder="请输入内容"
            />
          </el-form-item>
          <el-form-item label="座机前缀" prop="regionPhonePrefix">
            <el-input
              v-model="form.regionPhonePrefix"
              placeholder="请输入座机前缀"
            />
          </el-form-item>
          <el-form-item label="座机" prop="phone">
            <el-input v-model="form.phone" placeholder="请输入座机" />
          </el-form-item>
          <el-form-item label="图片" prop="companyImageUrl">
            <el-input
              v-model="form.companyImageUrl"
              type="textarea"
              placeholder="请输入内容"
            />
          </el-form-item>
          <el-form-item label="主页地址" prop="companyHomepageUrl">
            <el-input
              v-model="form.companyHomepageUrl"
              type="textarea"
              placeholder="请输入内容"
            />
          </el-form-item>
          <el-form-item label="传真" prop="customerFax">
            <el-input v-model="form.customerFax" placeholder="请输入传真" />
          </el-form-item>
        </el-form>
        <el-form
          ref="form"
          :model="form.contactsList"
          :rules="rules"
          label-width="80px"
          style="
            width: 50%;
            padding-left: 10px;
            padding-right: 10px;
            float: left;
          "
        >
          <div
            v-for="(item, index) in form.contactsList"
            :key="index"
            style="width: 100%"
            class="my-border"
          >
            <h3>联系人</h3>

            <el-form-item
              label="昵称"
              :prop="'contactsList.' + index + '.contactNickName'"
            >
              <el-input
                v-model="form.contactsList[index].contactNickName"
                placeholder="请输入昵称"
              />
            </el-form-item>
            <el-form-item
              label="邮箱"
              :prop="'contactsList.' + index + '.contactEmail'"
            >
              <el-input
                v-model="form.contactsList[index].contactEmail"
                placeholder="请输入邮箱"
              />
            </el-form-item>
            <el-form-item
              label="职级"
              :prop="'contactsList.' + index + '.contactRankKey'"
            >
              <el-select
                v-model="form.contactsList[index].contactRankKey"
                clearable
                placeholder="请选择职级"
              >
                <el-option
                  v-for="item in contactGenderOptions"
                  :key="item.dictValue"
                  :label="item.dictLabel"
                  :value="item.dictValue"
                >
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item
              label="职位"
              :prop="'contactsList.' + index + '.contactPosition'"
            >
              <el-input
                v-model="form.contactsList[index].contactPosition"
                placeholder="请输入职位"
              />
            </el-form-item>
            <div
              v-for="(phoneItem, phoneIndex) in form.contactsList[index]
                .contactsPhoneList"
              :key="phoneItem"
              style="width: 100%"
            >
              <label class="el-form-item__label" style="width: 80px"
                >联系电话</label
              >
              <el-form-item
                :prop="
                  'contactsList.' +
                  index +
                  '.contactsPhoneList.' +
                  phoneIndex +
                  '.regionId'
                "
              >
                <el-row :gutter="20">
                  <el-col :span="1" style="width: 30%"
                    ><el-select
                      v-model="
                        form.contactsList[index].contactsPhoneList[phoneIndex]
                          .regionId
                      "
                      clearable
                      placeholder="请选择"
                    >
                      <el-option
                        v-for="item in regionList"
                        :key="item.id"
                        :label="item.regionChineseName + item.regionPhonePrefix"
                        :value="item.id"
                      >
                      </el-option> </el-select
                  ></el-col>
                  <el-col :span="1" style="width: 50%"
                    ><el-input
                      v-model="
                        form.contactsList[index].contactsPhoneList[phoneIndex]
                          .phoneNumber
                      "
                      placeholder="请输入电话号码"
                    />
                  </el-col>
                  <el-col :span="1"
                    ><i
                      class="el-icon-delete"
                      v-if="phoneIndex > 0"
                      @click="delContactPhone(index, phoneIndex)"
                    ></i
                  ></el-col>
                  <el-col :span="1"
                    ><i class="el-icon-plus" @click="addContactPhone(index)"></i
                  ></el-col>
                </el-row>
              </el-form-item>
            </div>

            <div
              v-for="(phoneItem, platformIndex) in form.contactsList[index]
                .contactsSocialPlatformList"
              :key="platformIndex"
              style="width: 100%"
            >
              <label class="el-form-item__label" style="width: 80px"
                >社交平台</label
              >
              <el-form-item
                :prop="
                  'contactsList.' +
                  index +
                  '.contactsSocialPlatformList.' +
                  platformIndex +
                  '.platformName'
                "
              >
                <el-row :gutter="20">
                  <el-col :span="1" style="width: 30%"
                    ><el-select
                      v-model="
                        form.contactsList[index].contactsSocialPlatformList[
                          platformIndex
                        ].platformName
                      "
                      clearable
                      placeholder="请选择"
                    >
                      <el-option
                        v-for="item in socialPlatformList"
                        :key="item.platformCode"
                        :label="item.platformName"
                        :value="item.platformName"
                      >
                      </el-option> </el-select
                  ></el-col>
                  <el-col :span="1" style="width: 50%"
                    ><el-input
                      v-model="
                        form.contactsList[index].contactsSocialPlatformList[
                          platformIndex
                        ].platformAccount
                      "
                      placeholder="请输入社交账号"
                    />
                  </el-col>
                  <el-col :span="1"
                    ><i
                      class="el-icon-delete"
                      v-if="platformIndex > 0"
                      @click="delContactPlatform(index, platformIndex)"
                    ></i
                  ></el-col>
                  <el-col :span="1"
                    ><i
                      class="el-icon-plus"
                      @click="addContactPlatform(index)"
                    ></i
                  ></el-col>
                </el-row>
              </el-form-item>
            </div>
            <el-form-item
              label="生日"
              :prop="'contactsList.' + index + '.contactBirthday'"
            >
              <el-date-picker
                clearable
                size="small"
                v-model="form.contactsList[index].contactBirthday"
                type="date"
                value-format="yyyy-MM-dd"
                placeholder="请选择生日"
              >
              </el-date-picker>
            </el-form-item>
            <el-form-item
              label="性别"
              :prop="'contactsList.' + index + '.contactGenderKey'"
            >
              <el-select
                v-model="form.contactsList[index].contactGenderKey"
                clearable
                placeholder="请选择性别"
              >
                <el-option
                  v-for="item in sexOptions"
                  :key="item.dictValue"
                  :label="item.dictLabel"
                  :value="item.dictValue"
                >
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item
              label="备注"
              :prop="'contactsList.' + index + '.remarks'"
            >
              <el-input
                v-model="form.contactsList[index].remarks"
                placeholder="请输入备注"
              />
            </el-form-item>
            <el-form-item
              label="图片"
              :prop="'contactsList.' + index + '.imageUrl'"
            >
              <el-input
                v-model="form.contactsList[index].imageUrl"
                placeholder="请选择图片"
              />
            </el-form-item>
            <el-form-item
              label="邮箱或电话"
              :prop="'contactsList.' + index + '.contactEamilPhone'"
            >
              <el-input
                v-model="form.contactsList[index].contactEamilPhone"
                placeholder="请输入邮箱或电话"
              />
            </el-form-item>
            <el-form-item
              label="客户地址"
              :prop="'contactsList.' + index + '.contactAddress'"
            >
              <el-input
                v-model="form.contactsList[index].contactAddress"
                placeholder="请输入客户地址"
              /> </el-form-item
            >　
            <el-button
              v-if="index > 0"
              @click="delContact(index)"
              style="margin-right: 80%; background: #f56c6c; color: #ffffff"
              >删除</el-button
            >　
          </div>
          <el-button @click="addContact()">添加</el-button>
        </el-form>
      </div>

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
} from "@/api/customer/companyInfo";
import { selectSourceList } from "@/api/customer/customerSource";
import { selectRegionList } from "@/api/basic/region";
import { selectGroupList } from "@/api/customer/customerGroup";
import { selectPublicGroupList } from "@/api/customer/customerPublicGroup";
import { selectLabelList } from "@/api/customer/customerLabel";
import { selectStatusList } from "@/api/customer/customerStatus";
import { selectCategoryList } from "@/api/customer/customerCategory";
import { selectPlatformList } from "@/api/basic/socialPlatform";
import { selectTimeZoneList } from "@/api/basic/timeZone";
import { selectCategoryTreeList } from "@/api/basic/productCategory";

export default {
  name: "Info",
  components: {},
  data() {
    return {
      //客户来源List
      customerSourceList: [],
      //国家区域list
      regionList: [],
      //客户分组list
      groupList: [],
      //客户公海分组list
      publicGroupList: [],
      //客户状态list
      statusList: [],
      //客户标签list
      labelList: [],
      //客户类别list
      categoryList: [],
      //社交平台List
      socialPlatformList: [],
      //世界时区List
      timeZoneList: [],
      //产品类别树形结构
      categoryTreeList: [],
      //客户年采购额数据字典
      customerProcurementOptions: [],
      //客户采购意向数据字典
      purchaseIntentionOptions: [],
      //客户公司规模数据字典
      companySizeOptions: [],
      //客户星级数据字典
      customerStarLevelOptions: [],
      //客户联系人职级数据字典
      contactGenderOptions: [],
      //性别数据字典
      sexOptions: [],
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
      // 客户公司基础信息表格数据
      infoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        customerCode: null,
        companyName: null,
        companyNameShorter: null,
        customerSourceId: null,
        customerProcurementKey: null,
        purchaseIntentionKey: null,
        mainProductCategoryId: null,
        regionId: null,
        timeZoneId: null,
        companySizeKey: null,
        customerCategoryId: null,
        customerGourpId: null,
        customerPublicGourpId: null,
        customerStatusId: null,
        customerLabelId: null,
        customerStarLevelKey: null,
        nextFollowTime: null,
        companyRemarks: null,
        regionPhonePrefix: null,
        phone: null,
        companyImageUrl: null,
        companyHomepageUrl: null,
        customerFax: null,
        tenantId: null,
        createdUserName: null,
        createdUserAccount: null,
        createdTime: null,
        updatedUserName: null,
        updatedUserAccount: null,
        updatedTime: null,
        isDelete: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        customerCode: [
          { required: true, message: "客户编码不能为空", trigger: "blur" },
        ],
      },
    };
  },
  created() {
    this.getList();
    this.getCustomerSourceList();
    this.getRegionList();
    this.getStatusList();
    this.getCategoryList();
    this.getLabelList();
    this.getGroupList();
    this.getPublicGroupList();
    this.getSocialPlatformList();
    this.getCategoryTreeList();
    this.getTimeZoneList();
    this.getDicts("customer_procurement_key").then((response) => {
      this.customerProcurementOptions = response.data;
    });
    this.getDicts("purchase_intention_key").then((response) => {
      this.purchaseIntentionOptions = response.data;
    });
    this.getDicts("company_size_key").then((response) => {
      this.companySizeOptions = response.data;
    });
    this.getDicts("customer_star_level_key").then((response) => {
      this.customerStarLevelOptions = response.data;
    });
    this.getDicts("contact_rank_key").then((response) => {
      this.contactGenderOptions = response.data;
    });
    this.getDicts("sys_user_sex").then((response) => {
      this.sexOptions = response.data;
    });
  },
  methods: {
    /** 查询客户公司基础信息列表 */
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
        id: null,
        customerCode: null,
        companyName: null,
        companyNameShorter: null,
        customerSourceId: null,
        customerProcurementKey: null,
        purchaseIntentionKey: null,
        mainProductCategoryIdList: [null],
        regionId: null,
        timeZoneId: null,
        companySizeKey: null,
        customerCategoryId: null,
        customerGourpId: null,
        customerPublicGourpId: null,
        customerStatusId: null,
        customerLabelId: null,
        customerStarLevelKey: null,
        nextFollowTime: null,
        companyRemarks: null,
        regionPhonePrefix: null,
        phone: null,
        companyImageUrl: null,
        companyHomepageUrl: null,
        customerFax: null,
        tenantId: null,
        createdUserName: null,
        createdUserAccount: null,
        createdTime: null,
        updatedUserName: null,
        updatedUserAccount: null,
        updatedTime: null,
        isDelete: null,
        contactsList: [
          {
            contactNickName: null,
            contactEmail: null,
            contactRankKey: null,
            contactPosition: null,
            contactBirthday: null,
            contactGenderKey: null,
            remarks: null,
            imageUrl: null,
            contactAddress: null,
            contactEamilPhone: null,
            contactsPhoneList: [
              {
                regionId: null,
                regionChineseName: null,
                regionPhonePrefix: null,
                phoneNumber: null,
              },
            ],
            contactsSocialPlatformList: [
              {
                platformCode: null,
                platformName: null,
                platformAccount: null,
              },
            ],
          },
        ],
      };
      // this.resetForm("form");
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
      this.title = "添加客户公司基础信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids;
      getInfo(id).then((response) => {
        this.form = response.data;
        if (
          this.form.mainProductCategoryIdList == null ||
          this.form.mainProductCategoryIdList.length < 1
        ) {
          this.form.mainProductCategoryIdList = [null];
        }
        this.open = true;
        this.title = "修改客户公司基础信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.id != null) {
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
      const ids = row.id || this.ids;
      this.$confirm(
        '是否确认删除客户公司基础信息编号为"' + ids + '"的数据项?',
        "警告",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        }
      )
        .then(function () {
          return delInfo(ids);
        })
        .then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        });
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm("是否确认导出所有客户公司基础信息数据项?", "警告", {
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
    /** 查询客户来源列表 */
    getCustomerSourceList() {
      this.loading = true;
      selectSourceList().then((response) => {
        this.customerSourceList = response.rows;
        this.loading = false;
      });
    },
    /** 查询世界区域列表 */
    getRegionList() {
      this.loading = true;
      selectRegionList().then((response) => {
        this.regionList = response.rows;
        this.loading = false;
      });
    },
    /** 查询客户状态列表 */
    getStatusList() {
      this.loading = true;
      selectStatusList().then((response) => {
        this.statusList = response.rows;
        this.loading = false;
      });
    },
    /** 查询客户类别列表 */
    getCategoryList() {
      selectCategoryList().then((response) => {
        this.categoryList = response.rows;
      });
    },
    /** 查询客户标签列表 */
    getLabelList() {
      this.loading = true;
      selectLabelList().then((response) => {
        this.labelList = response.rows;
        this.loading = false;
      });
    },
    /** 查询客户分组列表 */
    getGroupList() {
      this.loading = true;
      selectGroupList().then((response) => {
        this.groupList = response.rows;
        this.loading = false;
      });
    },
    /** 查询客户公海分组列表 */
    getPublicGroupList() {
      this.loading = true;
      selectPublicGroupList().then((response) => {
        this.publicGroupList = response.rows;
        this.loading = false;
      });
    },
    /** 查询社交平台列表 */
    getSocialPlatformList() {
      selectPlatformList().then((response) => {
        this.socialPlatformList = response.rows;
      });
    },
    /** 查询世界时区列表 */
    getTimeZoneList() {
      selectTimeZoneList().then((response) => {
        this.timeZoneList = response.rows;
      });
    },
    /** 查询产品类别树形结构 */
    getCategoryTreeList() {
      selectCategoryTreeList().then((response) => {
        this.categoryTreeList = response.rows;
      });
    },
    /** 表单添加联系人 */
    addContact() {
      this.form.contactsList.push({
        contactNickName: "",
        contactEmail: "",
        contactRankKey: "",
        contactPosition: "",
        contactBirthday: "",
        contactGenderKey: "",
        remarks: "",
        imageUrl: "",
        contactAddress: "",
        contactEamilPhone: "",
        contactsPhoneList: [
          {
            phoneNumber: "",
          },
        ],
        contactsSocialPlatformList: [{}],
      });
    },
    /** 表单删除联系人 */
    delContact(index) {
      this.form.contactsList.splice(index, 1);
    },
    /** 表单添加联系人手机号 */
    addContactPhone(index) {
      this.form.contactsList[index].contactsPhoneList.push({
        regionId: null,
        regionChineseName: null,
        regionPhonePrefix: null,
        phoneNumber: null,
      });
    },
    /** 表单删除联系人手机号 */
    delContactPhone(index, phoneIndex) {
      this.form.contactsList[index].contactsPhoneList.splice(phoneIndex, 1);
    },
    /** 表单添加联系人社交账号 */
    addContactPlatform(index) {
      this.form.contactsList[index].contactsSocialPlatformList.push({
        platformCode: null,
        platformName: null,
        platformAccount: null,
      });
    },
    /** 表单删除联系人社交账号 */
    delContactPlatform(index, platformIndex) {
      this.form.contactsList[index].contactsSocialPlatformList.splice(
        platformIndex,
        1
      );
    },
    /** 表单添加客户主营产品类别 */
    addProductCategory() {
      this.form.mainProductCategoryIdList.push({});
    },
    /** 表单删除客户主营产品类别 */
    delProductCategory(index) {
      this.form.mainProductCategoryIdList.splice(index, 1);
    },
    /** 客户相关字段数值转换 */
    customerFormat(row, column, cellValue, index) {
      console.log(
        "=======================循环开始============================"
      );
      /** 客户来源数值转换 */
      this.customerSourceList.forEach((cs) => {
        console.log("客户来源=======================");
        console.log(cs.id);
        console.log(row.customerSourceId);
        if (cs.id == row.customerSourceId) {
          console.log("Jinqule=================");
          row.customerSourceId = cs.sourceName;
        }
      });
      console.log("sdasfagaaaaaaaaaaaaaaaaaaaaaaa");
      console.log(row.customerSourceId);

      // /** 客户年采购额字典数值类型转换 */
      // this.customerProcurementOptions.forEach(d => {
      //   if(row.customerProcurementKey == d.dictValue){
      //     row.customerProcurementKey = d.dictLabel;
      //   }
      // });
      // /** 客户采购意向数值转换 */
      // this.purchaseIntentionOptions.forEach(p => {
      //   if(row.purchaseIntentionKey == p.dictValue){
      //     row.purchaseIntentionKey = p.dictLabel
      //   }
      // });
      // /** 客户所属国家数值转换 */
      // this.regionList.forEach(r => {
      //   if(row.regionId == r.id){
      //     row.regionId = r.regionName;
      //   }
      // });
      // /** 客户所属时区数值转换 */
      // this.timeZoneList.forEach(t => {
      //   if(row.timeZoneId == t.id){
      //     row.timeZoneId = t.zoneName;
      //   }
      // });
      // /** 客户公司规模数值转换 */
      // this.companySizeOptions.forEach(s => {
      //   if(row.companySizeKey == s.dictValue){
      //     row.companySizeKey = s.dictLabel;
      //   }
      // });
      // /** 客户类别数值转换 */
      // this.categoryList.forEach(c => {
      //   if(row.customerCategoryId == c.id){
      //     row.customerCategoryId = c.categoryName;
      //   }
      // });
      // /** 客户所属分组数值转换 */
      // this.groupList.forEach(g => {
      //   if(row.customerGourpId == g.id){
      //     row.customerGourpId = g.groupName;
      //   }
      // });
      // /** 客户公海分组数值转换 */
      // this.publicGroupList.forEach(p => {
      //   if(row.customerPublicGourpId == p.id){
      //     row.customerPublicGourpId = p.publicGroupName;
      //   }
      // });
      // /** 客户状态数值转换 */
      // this.statusList.forEach(s => {
      //   if(row.customerStatusId == s.id){
      //     row.customerSourceId = s.sourceName;
      //   }
      // });
      // /** 客户标签数值转换 */
      // this.labelList.forEach(l => {
      //   if(row.customerLabelId == l.id){
      //     row.customerLabelId = l.labelName;
      //   }
      // });
      // /** 客户星级数值转换 */
      // this.customerStarLevelOptions.forEach(s => {
      //   if(row.customerStarLevelKey == s.dictValue){
      //     row.customerStarLevelKey = s.dictLabel;
      //   }
      // });
    },
  },
};
</script>

<style scoped>
.edit-content-wrap {
  overflow-x: hidden;
  overflow-y: auto;
  position: relative;
  width: 100%;
  padding: 10px 20px;
  -webkit-box-flex: 1;
  -ms-flex: 1;
  flex: 1;
}
.mm-row {
  font-family: "Monospaced Number", Chinese Quote, -apple-system,
    BlinkMacSystemFont, Segoe UI, Roboto, PingFang SC, Hiragino Sans GB,
    Microsoft YaHei, Helvetica Neue, Helvetica, Arial, sans-serif;
  font-size: 14px;
  line-height: 1.5;
  color: #5d5d5d;
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
  margin: 0;
  padding: 0;
  list-style: none;
  position: relative;
}

.el-dialog-div {
  height: 60vh;
  overflow-y: auto;
  overflow-x: hidden;
}
.my-border {
  border: 1px solid silver;
}
.clolos1 {
  width: 13px;
  height: 30px;
  background: #29affb;
  line-height: 30px;
  padding: 0 10px;
  border-radius: 15px;
  color: #fff;
  position: absolute;
  margin-left: 65.35%;
  margin-top: 8.48%;
  z-index: 1;
}
</style>
