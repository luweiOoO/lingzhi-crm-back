package com.lingzhi.crm.basic.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import com.lingzhi.crm.basic.vo.TreeSelect;
import com.lingzhi.crm.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lingzhi.crm.basic.mapper.ProductCategoryMapper;
import com.lingzhi.crm.basic.domain.ProductCategory;
import com.lingzhi.crm.basic.service.IProductCategoryService;

/**
 * 产品类别信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-06-01
 */
@Service
public class ProductCategoryServiceImpl implements IProductCategoryService 
{
    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    /**
     * 查询产品类别信息
     * 
     * @param id 产品类别信息ID
     * @return 产品类别信息
     */
    @Override
    public ProductCategory selectProductCategoryById(Long id)
    {
        return productCategoryMapper.selectProductCategoryById(id);
    }

    /**
     * 查询产品类别信息列表
     * 
     * @param productCategory 产品类别信息
     * @return 产品类别信息
     */
    @Override
    public List<ProductCategory> selectProductCategoryList(ProductCategory productCategory)
    {
        return productCategoryMapper.selectProductCategoryList(productCategory);
    }

    /**
     * 新增产品类别信息
     * 
     * @param productCategory 产品类别信息
     * @return 结果
     */
    @Override
    public int insertProductCategory(ProductCategory productCategory)
    {
        return productCategoryMapper.insertProductCategory(productCategory);
    }

    /**
     * 修改产品类别信息
     * 
     * @param productCategory 产品类别信息
     * @return 结果
     */
    @Override
    public int updateProductCategory(ProductCategory productCategory)
    {
        return productCategoryMapper.updateProductCategory(productCategory);
    }

    /**
     * 批量删除产品类别信息
     * 
     * @param ids 需要删除的产品类别信息ID
     * @return 结果
     */
    @Override
    public int deleteProductCategoryByIds(Long[] ids)
    {
        return productCategoryMapper.deleteProductCategoryByIds(ids);
    }

    /**
     * 删除产品类别信息信息
     * 
     * @param id 产品类别信息ID
     * @return 结果
     */
    @Override
    public int deleteProductCategoryById(Long id)
    {
        return productCategoryMapper.deleteProductCategoryById(id);
    }

    @Override
    public List<TreeSelect> getCategoryTreeList() {
        //查询出所有未删除的产品类别
        List<ProductCategory> categoryList = productCategoryMapper.selectProductCategoryList(new ProductCategory());
        List<TreeSelect> treeSelects = buildCategoryTree(categoryList);
        return treeSelects;

    }

    List<TreeSelect> buildCategoryTree(List<ProductCategory> categoryList){
        List<ProductCategory> returnList = new ArrayList<ProductCategory>();
        List<Long> tempList = new ArrayList<Long>();
        for (ProductCategory category : categoryList)
        {
            tempList.add(category.getId());
        }
        for (Iterator<ProductCategory> iterator = categoryList.iterator(); iterator.hasNext();)
        {
            ProductCategory category = (ProductCategory) iterator.next();
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if (!tempList.contains(category.getpCategoryId()))
            {
                recursionFn(categoryList, category);
                returnList.add(category);
            }
        }
        if (returnList.isEmpty())
        {
            returnList = categoryList;
        }
        return returnList.stream().map(TreeSelect::new).collect(Collectors.toList());
    }

    /**
     * 递归列表
     */
    private void recursionFn(List<ProductCategory> list, ProductCategory t)
    {
        // 得到子节点列表
        List<ProductCategory> childList = getChildList(list, t);
        t.setChildren(childList);
        for (ProductCategory tChild : childList)
        {
            if (hasChild(list, tChild))
            {
                recursionFn(list, tChild);
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List<ProductCategory> getChildList(List<ProductCategory> list, ProductCategory t)
    {
        List<ProductCategory> tlist = new ArrayList<ProductCategory>();
        Iterator<ProductCategory> it = list.iterator();
        while (it.hasNext())
        {
            ProductCategory n = (ProductCategory) it.next();
            if (StringUtils.isNotNull(n.getpCategoryId()) && n.getpCategoryId().longValue() == t.getId().longValue())
            {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<ProductCategory> list, ProductCategory t)
    {
        return getChildList(list, t).size() > 0 ? true : false;
    }
}
