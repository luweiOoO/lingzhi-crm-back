package com.lingzhi.crm.customer.mapper;

import java.util.List;
import com.lingzhi.crm.customer.domain.CustomerCompanyProductRelation;
import org.apache.ibatis.annotations.Param;

/**
 * 客户公司主营产品关联Mapper接口
 * 
 * @author ruoyi
 * @date 2021-06-03
 */
public interface CustomerCompanyProductRelationMapper 
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
     * 删除客户公司主营产品关联
     * 
     * @param id 客户公司主营产品关联ID
     * @return 结果
     */
    public int deleteCustomerCompanyProductRelationById(Long id);

    /**
     * 批量删除客户公司主营产品关联
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCustomerCompanyProductRelationByIds(Long[] ids);

    /**
     * 根据公司id删除公司所绑主营产品相关信息
     * @param companyId
     * @return
     */
    int delProductByCompanyId(Long companyId);

    /**
     * 根据客户公司id和产品idList批量新增公司产品关联关系
     * @param companyId 客户公司id
     * @param productIdList 产品idList
     * @return
     */
    int addCompanyProductRelationByCompanyAndProducts(@Param("companyId") Long companyId,@Param("productIdList") List<Long> productIdList);

    /**
     * 根据公司id获取公司主营产品idList
     * @param companyId
     * @return
     */
    List<Long> getProductIdListByCompanyId(Long companyId);
}
