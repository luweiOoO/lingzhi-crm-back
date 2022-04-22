package com.lingzhi.crm.order.servicce.impl;

import java.util.List;

import com.lingzhi.crm.common.utils.DateUtils;
import com.lingzhi.crm.order.domin.ProductSmallOrderDetail;
import com.lingzhi.crm.order.servicce.IProductSmallOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lingzhi.crm.order.mapper.ProductSmallOrderDetailMapper;

/**
 * 生产指示单产品详情Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-02-10
 */
@Service
public class ProductSmallOrderDetailServiceImpl implements IProductSmallOrderDetailService
{
    @Autowired
    private ProductSmallOrderDetailMapper productSmallOrderDetailMapper;

    /**
     * 查询生产指示单产品详情
     * 
     * @param id 生产指示单产品详情ID
     * @return 生产指示单产品详情
     */
    @Override
    public ProductSmallOrderDetail selectProductSmallOrderDetailById(Long id)
    {
        return productSmallOrderDetailMapper.selectProductSmallOrderDetailById(id);
    }

    /**
     * 查询生产指示单产品详情列表
     * 
     * @param productSmallOrderDetail 生产指示单产品详情
     * @return 生产指示单产品详情
     */
    @Override
    public List<ProductSmallOrderDetail> selectProductSmallOrderDetailList(ProductSmallOrderDetail productSmallOrderDetail)
    {
        return productSmallOrderDetailMapper.selectProductSmallOrderDetailList(productSmallOrderDetail);
    }

    /**
     * 新增生产指示单产品详情
     * 
     * @param productSmallOrderDetail 生产指示单产品详情
     * @return 结果
     */
    @Override
    public int insertProductSmallOrderDetail(ProductSmallOrderDetail productSmallOrderDetail)
    {
        productSmallOrderDetail.setCreateTime(DateUtils.getNowDate());
        return productSmallOrderDetailMapper.insertProductSmallOrderDetail(productSmallOrderDetail);
    }

    /**
     * 修改生产指示单产品详情
     * 
     * @param productSmallOrderDetail 生产指示单产品详情
     * @return 结果
     */
    @Override
    public int updateProductSmallOrderDetail(ProductSmallOrderDetail productSmallOrderDetail)
    {
        productSmallOrderDetail.setUpdateTime(DateUtils.getNowDate());
        return productSmallOrderDetailMapper.updateProductSmallOrderDetail(productSmallOrderDetail);
    }

    /**
     * 批量删除生产指示单产品详情
     * 
     * @param ids 需要删除的生产指示单产品详情ID
     * @return 结果
     */
    @Override
    public int deleteProductSmallOrderDetailByIds(Long[] ids)
    {
        return productSmallOrderDetailMapper.deleteProductSmallOrderDetailByIds(ids);
    }

    /**
     * 删除生产指示单产品详情信息
     * 
     * @param id 生产指示单产品详情ID
     * @return 结果
     */
    @Override
    public int deleteProductSmallOrderDetailById(Long id)
    {
        return productSmallOrderDetailMapper.deleteProductSmallOrderDetailById(id);
    }

    @Override
    public int insertProductSmallOrderDetailBatch(List<ProductSmallOrderDetail> orderDetailList) {
        return productSmallOrderDetailMapper.insertOrderDetailBatch(orderDetailList);
    }
}
