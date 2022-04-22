package com.lingzhi.crm.order.mapper;

import com.lingzhi.crm.order.domin.ProductSmallOrderDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 生产指示单产品详情Mapper接口
 * 
 * @author ruoyi
 * @date 2022-02-10
 */
public interface ProductSmallOrderDetailMapper 
{
    /**
     * 查询生产指示单产品详情
     * 
     * @param id 生产指示单产品详情ID
     * @return 生产指示单产品详情
     */
    public ProductSmallOrderDetail selectProductSmallOrderDetailById(Long id);

    /**
     * 查询生产指示单产品详情列表
     * 
     * @param productSmallOrderDetail 生产指示单产品详情
     * @return 生产指示单产品详情集合
     */
    public List<ProductSmallOrderDetail> selectProductSmallOrderDetailList(ProductSmallOrderDetail productSmallOrderDetail);

    /**
     * 新增生产指示单产品详情
     * 
     * @param productSmallOrderDetail 生产指示单产品详情
     * @return 结果
     */
    public int insertProductSmallOrderDetail(ProductSmallOrderDetail productSmallOrderDetail);

    /**
     * 修改生产指示单产品详情
     * 
     * @param productSmallOrderDetail 生产指示单产品详情
     * @return 结果
     */
    public int updateProductSmallOrderDetail(ProductSmallOrderDetail productSmallOrderDetail);

    /**
     * 删除生产指示单产品详情
     * 
     * @param id 生产指示单产品详情ID
     * @return 结果
     */
    public int deleteProductSmallOrderDetailById(Long id);

    /**
     * 批量删除生产指示单产品详情
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteProductSmallOrderDetailByIds(Long[] ids);

    /**
     * 批量新增产品详情信息
     * @param orderDetailList
     * @return
     */
    public int insertOrderDetailBatch(@Param("orderDetailList") List<ProductSmallOrderDetail> orderDetailList);
}
