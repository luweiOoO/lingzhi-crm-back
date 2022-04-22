package com.lingzhi.crm.customer.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.lingzhi.crm.common.constant.Constants;
import com.lingzhi.crm.common.core.domain.model.LoginUser;
import com.lingzhi.crm.common.utils.SecurityUtils;
import com.lingzhi.crm.common.utils.StringUtils;
import com.lingzhi.crm.customer.service.ICustomerCompanyContactsService;
import com.lingzhi.crm.customer.service.ICustomerCompanyProductRelationService;
import com.lingzhi.crm.customer.vo.CompanyCustomerAddInVO;
import com.lingzhi.crm.customer.vo.CompanyCustomerContactsDetailOutVO;
import com.lingzhi.crm.customer.vo.CompanyCustomerDetailOutVO;
import com.lingzhi.crm.system.service.ISysDictDataService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lingzhi.crm.customer.mapper.CustomerCompanyInfoMapper;
import com.lingzhi.crm.customer.domain.CustomerCompanyInfo;
import com.lingzhi.crm.customer.service.ICustomerCompanyInfoService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 客户公司基础信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-06-01
 */
@Service
public class CustomerCompanyInfoServiceImpl implements ICustomerCompanyInfoService 
{
    @Autowired
    private CustomerCompanyInfoMapper customerCompanyInfoMapper;
    @Autowired
    private ICustomerCompanyProductRelationService companyProductRelationService;
    @Autowired
    private ICustomerCompanyContactsService companyContactsService;
    @Autowired
    private ISysDictDataService dictDataService;

    /**
     * 查询客户公司基础信息
     * 
     * @param id 客户公司基础信息ID
     * @return 客户公司基础信息
     */
    @Override
    public CustomerCompanyInfo selectCustomerCompanyInfoById(Long id)
    {
        return customerCompanyInfoMapper.selectCustomerCompanyInfoById(id);
    }

    /**
     * 查询客户公司基础信息列表
     * 
     * @param customerCompanyInfo 客户公司基础信息
     * @return 客户公司基础信息
     */
    @Override
    public List<CustomerCompanyInfo> selectCustomerCompanyInfoList(CustomerCompanyInfo customerCompanyInfo)
    {
        return customerCompanyInfoMapper.selectCustomerCompanyInfoList(customerCompanyInfo);
    }

    /**
     * 新增客户公司基础信息
     * 
     * @param customerCompanyInfo 客户公司基础信息
     * @return 结果
     */
    @Override
    public int insertCustomerCompanyInfo(CustomerCompanyInfo customerCompanyInfo)
    {
        return customerCompanyInfoMapper.insertCustomerCompanyInfo(customerCompanyInfo);
    }

    /**
     * 修改客户公司基础信息
     * 
     * @param customerCompanyInfo 客户公司基础信息
     * @return 结果
     */
    @Override
    public int updateCustomerCompanyInfo(CustomerCompanyInfo customerCompanyInfo)
    {
        return customerCompanyInfoMapper.updateCustomerCompanyInfo(customerCompanyInfo);
    }

    /**
     * 批量删除客户公司基础信息
     * 
     * @param ids 需要删除的客户公司基础信息ID
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteCustomerCompanyInfoByIds(Long[] ids)
    {
        //1批量逻辑客户公司信息
        customerCompanyInfoMapper.deleteCustomerCompanyInfoByIds(ids);
        //2批量逻辑删除公司联系人和联系电话及社交平台等基本信息
        List<Long> customerIdList = Arrays.asList(ids);
        List<Long> contactIdList = companyContactsService.getContactIdListByCustomerId(customerIdList);
        companyContactsService.deleteCustomerCompanyContactsByIds(contactIdList);
        return 1;
    }

    /**
     * 删除客户公司基础信息信息
     * 
     * @param id 客户公司基础信息ID
     * @return 结果
     */
    @Override
    public int deleteCustomerCompanyInfoById(Long id)
    {
        return customerCompanyInfoMapper.deleteCustomerCompanyInfoById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addCompanyAndContacts(CompanyCustomerAddInVO inVO) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        CustomerCompanyInfo companyInfo = new CustomerCompanyInfo();
        BeanUtils.copyProperties(inVO,companyInfo);
        companyInfo.setCreatedTime(new Date());
        companyInfo.setCreatedUserAccount(loginUser.getUser().getUserName());
        companyInfo.setCreatedUserName(loginUser.getUser().getNickName());
        //TODO 设置客户唯一编码
        companyInfo.setCustomerCode("11111");
        //TODO 设置tenantID
        //新增客户公司基本信息
        customerCompanyInfoMapper.insertCustomerCompanyInfo(companyInfo);
        //新增客户公司和主营产品关联关系
        companyProductRelationService.addCompanyProductRelationByCompanyAndProducts(companyInfo.getId(),inVO.getMainProductCategoryIdList());
        //新增客户公司和联系人关联信息
        companyContactsService.addCompanyContactsAndPhonePlatform(companyInfo.getId(),inVO.getContactsList());

        return 1;
    }

    @Override
    public CompanyCustomerDetailOutVO getCompanyDetailById(Long companyId) {
        if(companyId == null){
            return new CompanyCustomerDetailOutVO();
        }
        //查询客户公司部分相关详情信息
        CompanyCustomerDetailOutVO customerDetailOutVO = customerCompanyInfoMapper.getCompanyDetailById(companyId);
        //查询客户主营产品类别
        List<Long> productIdList = companyProductRelationService.getProductCategoryIdListByCompanyId(companyId);
        customerDetailOutVO.setMainProductCategoryIdList(productIdList);
        //查询客户联系人相关信息
        List<CompanyCustomerContactsDetailOutVO> contactsDetailList = companyContactsService.getContactsDetailListByCompanyId(customerDetailOutVO.getId());
        customerDetailOutVO.setContactsList(contactsDetailList);

        return customerDetailOutVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int editCompanyDetail(CompanyCustomerDetailOutVO params) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        CustomerCompanyInfo companyInfo = new CustomerCompanyInfo();
        BeanUtils.copyProperties(params,companyInfo);
        //设置更新人账号信息
        companyInfo.setUpdatedUserName(loginUser.getUser().getNickName());
        companyInfo.setUpdatedUserAccount(loginUser.getUsername());
        //更新客户公司主体信息
        customerCompanyInfoMapper.updateCustomerCompanyInfo(companyInfo);
        //更新客户公司主营产品相关信息
        companyProductRelationService.addCompanyProductRelationByCompanyAndProducts(companyInfo.getId(),params.getMainProductCategoryIdList());
        //更新客户联系人相关信息
        companyContactsService.editCustomerContactsDetail(companyInfo.getId(), params.getContactsList());
        return 1;
    }

    @Override
    public List<CompanyCustomerDetailOutVO> selectCustomerCompanyInfoDetailList(CustomerCompanyInfo companyInfo) {
        List<CompanyCustomerDetailOutVO> detailOutVOList = customerCompanyInfoMapper.getCompanyDetailList(companyInfo);
        detailOutVOList.forEach(detail -> {
            //对客户基本信息的各个字典key赋对应值
            detail.setCustomerProcurementValue(StringUtils.isEmpty(detail.getCustomerProcurementKey()) ? "" : dictDataService.selectDictLabel(Constants.CUSTOMER_PROCUREMENT_TYPE,detail.getCustomerProcurementKey()));
            detail.setPurchaseIntentionValue(StringUtils.isEmpty(detail.getPurchaseIntentionKey()) ? "" : dictDataService.selectDictLabel(Constants.CUSTOMER_PURCHASE_INTENTION_TYPE,detail.getPurchaseIntentionKey()));
            detail.setCustomerStarLevelValue(StringUtils.isEmpty(detail.getCustomerStarLevelKey()) ? "" : dictDataService.selectDictLabel(Constants.CUSTOMER_STAR_LEVEL_TYPE,detail.getCustomerStarLevelKey()));
            detail.setCompanySizeValue(StringUtils.isEmpty(detail.getCompanySizeKey()) ? "" : dictDataService.selectDictLabel(Constants.CUSTOMER_STAR_LEVEL_TYPE,detail.getCompanySizeKey()));
        });
        return detailOutVOList;
    }
}
