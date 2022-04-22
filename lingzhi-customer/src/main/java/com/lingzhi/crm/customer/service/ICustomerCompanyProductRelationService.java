package com.lingzhi.crm.customer.service;

import java.util.List;
import com.lingzhi.crm.customer.domain.CustomerCompanyProductRelation;

/**
 * 客户公司主营产品关联Service接口
 * 
 * @author ruoyi
 * @date 2021-06-03
 */
public interface ICustomerCompanyProductRelationService 
{
    /**
     * 查询客户公司主营产品关联
     * 
     * @param id 客户公司主营产品关联ID
     * @return 客户公司主营产品关联
     */
    public CustomerCompanyProductRelation selectCustomerCompanyProductRelationById(Long id);

    /**
     * 查询客户公司主营产品关联列表
     * 
     * @param customerCompanyProductRelation 客户公司主营产品关联
     * @return 客户公司主营产品关联集合
     */
    public List<CustomerCompanyProductRelation> selectCustomerCompanyProductRelationList(CustomerCompanyProductRelation customerCompanyProductRelation);

    /**
     * 新增客户公司主营产品关联
     * 
     * @param customerCompanyProductRelation 客户公司主营产品关联
     * @return 结果
     */
    public int insertCustomerCompanyProductRelation(CustomerCompanyProductRelation customerCompanyProductRelation);

    /**
     * 修改客户公司主营产品关联
     * 
     * @param customerCompanyProductRelation 客户公司主营产品关联
     * @return 结果
     */
    public int updateCustomerCompanyProductRelation(CustomerCompanyProductRelation customerCompanyProductRelation);

    /**
     * 批量删除客户公司主营产品关联
     * 
     * @param ids 需要删除的客户公司主营产品关联ID
     * @return 结果
     */
    public int deleteCustomerCompanyProductRelationByIds(Long[] ids);

    /**
     * 删除客户公司主营产品关联信息
     * 
     * @param id 客户公司主营产品关联ID
     * @return 结果
     */
    public int deleteCustomerCompanyProductRelationById(Long id);

    /**
     * 根据客户公司id和产品idList新增公司产品关联数据
     * @param companyId
     * @param productIdList
     * @return
     */
    int addCompanyProductRelationByCompanyAndProducts(Long companyId,List<Long> productIdList);

    /**
     * 根据公司id获取公司主营产品类别idList
     * @param companyId
     * @return
     */
    List<Long> getProductCategoryIdListByCompanyId(Long companyId);
}
