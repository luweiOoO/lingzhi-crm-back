package com.lingzhi.crm.basic.service;

import java.util.List;
import com.lingzhi.crm.basic.domain.ProductCategory;
import com.lingzhi.crm.basic.vo.TreeSelect;

/**
 * 产品类别信息Service接口
 * 
 * @author ruoyi
 * @date 2021-06-01
 */
public interface IProductCategoryService 
{
    /**
     * 查询产品类别信息
     * 
     * @param id 产品类别信息ID
     * @return 产品类别信息
     */
    public ProductCategory selectProductCategoryById(Long id);

    /**
     * 查询产品类别信息列表
     * 
     * @param productCategory 产品类别信息
     * @return 产品类别信息集合
     */
    public List<ProductCategory> selectProductCategoryList(ProductCategory productCategory);

    /**
     * 新增产品类别信息
     * 
     * @param productCategory 产品类别信息
     * @return 结果
     */
    public int insertProductCategory(ProductCategory productCategory);

    /**
     * 修改产品类别信息
     * 
     * @param productCategory 产品类别信息
     * @return 结果
     */
    public int updateProductCategory(ProductCategory productCategory);

    /**
     * 批量删除产品类别信息
     * 
     * @param ids 需要删除的产品类别信息ID
     * @return 结果
     */
    public int deleteProductCategoryByIds(Long[] ids);

    /**
     * 删除产品类别信息信息
     * 
     * @param id 产品类别信息ID
     * @return 结果
     */
    public int deleteProductCategoryById(Long id);

    /**
     * 查询所有产品类别树形结构
     * @return
     */
    List<TreeSelect> getCategoryTreeList();
}
