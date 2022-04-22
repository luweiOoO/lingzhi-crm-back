package com.lingzhi.crm.customer.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lingzhi.crm.customer.mapper.CustomerCompanyProductRelationMapper;
import com.lingzhi.crm.customer.domain.CustomerCompanyProductRelation;
import com.lingzhi.crm.customer.service.ICustomerCompanyProductRelationService;

/**
 * 客户公司主营产品关联Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-06-03
 */
@Service
public class CustomerCompanyProductRelationServiceImpl implements ICustomerCompanyProductRelationService 
{
    @Autowired
    private CustomerCompanyProductRelationMapper customerCompanyProductRelationMapper;

    /**
     * 查询客户公司主营产品关联
     * 
     * @param id 客户公司主营产品关联ID
     * @return 客户公司主营产品关联
     */
    @Override
    public CustomerCompanyProductRelation selectCustomerCompanyProductRelationById(Long id)
    {
        return customerCompanyProductRelationMapper.selectCustomerCompanyProductRelationById(id);
    }

    /**
     * 查询客户公司主营产品关联列表
     * 
     * @param customerCompanyProductRelation 客户公司主营产品关联
     * @return 客户公司主营产品关联
     */
    @Override
    public List<CustomerCompanyProductRelation> selectCustomerCompanyProductRelationList(CustomerCompanyProductRelation customerCompanyProductRelation)
    {
        return customerCompanyProductRelationMapper.selectCustomerCompanyProductRelationList(customerCompanyProductRelation);
    }

    /**
     * 新增客户公司主营产品关联
     * 
     * @param customerCompanyProductRelation 客户公司主营产品关联
     * @return 结果
     */
    @Override
    public int insertCustomerCompanyProductRelation(CustomerCompanyProductRelation customerCompanyProductRelation)
    {
        return customerCompanyProductRelationMapper.insertCustomerCompanyProductRelation(customerCompanyProductRelation);
    }

    /**
     * 修改客户公司主营产品关联
     * 
     * @param customerCompanyProductRelation 客户公司主营产品关联
     * @return 结果
     */
    @Override
    public int updateCustomerCompanyProductRelation(CustomerCompanyProductRelation customerCompanyProductRelation)
    {
        return customerCompanyProductRelationMapper.updateCustomerCompanyProductRelation(customerCompanyProductRelation);
    }

    /**
     * 批量删除客户公司主营产品关联
     * 
     * @param ids 需要删除的客户公司主营产品关联ID
     * @return 结果
     */
    @Override
    public int deleteCustomerCompanyProductRelationByIds(Long[] ids)
    {
        return customerCompanyProductRelationMapper.deleteCustomerCompanyProductRelationByIds(ids);
    }

    /**
     * 删除客户公司主营产品关联信息
     * 
     * @param id 客户公司主营产品关联ID
     * @return 结果
     */
    @Override
    public int deleteCustomerCompanyProductRelationById(Long id)
    {
        return customerCompanyProductRelationMapper.deleteCustomerCompanyProductRelationById(id);
    }

    @Override
    public int addCompanyProductRelationByCompanyAndProducts(Long companyId, List<Long> productIdList) {
        if(companyId == null){
            return 0;
        }
        //先删除已绑定相关产品类别信息
        customerCompanyProductRelationMapper.delProductByCompanyId(companyId);
        if(productIdList == null || productIdList.size() < 1){
            return 0;
        }
        //筛选出非null值的产品类别idList
        List<Long> filterProductIdList = productIdList.stream().filter(Objects::nonNull).collect(Collectors.toList());
        if(filterProductIdList.size() < 1){
            return 0;
        }

        //重新插入相关产品类别信息
        return customerCompanyProductRelationMapper.addCompanyProductRelationByCompanyAndProducts(companyId,filterProductIdList);
    }

    @Override
    public List<Long> getProductCategoryIdListByCompanyId(Long companyId) {
        return customerCompanyProductRelationMapper.getProductIdListByCompanyId(companyId);
    }
}
