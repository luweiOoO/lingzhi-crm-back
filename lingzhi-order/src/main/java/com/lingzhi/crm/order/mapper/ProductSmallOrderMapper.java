package com.lingzhi.crm.order.mapper;

import com.lingzhi.crm.common.domain.ProductSalesManOrderImportEntity;
import com.lingzhi.crm.order.domin.ProductSmallOrder;
import com.lingzhi.crm.order.dto.SalesmanOrderSearchParam;
import com.lingzhi.crm.order.dto.SmallOrderSearchParamsDto;

import java.util.List;

/**
 * 生产指示小单基础信息Mapper接口
 * 
 * @author ruoyi
 * @date 2022-02-10
 */
public interface ProductSmallOrderMapper 
{
    /**
     * 查询生产指示小单基础信息
     * 
     * @param id 生产指示小单基础信息ID
     * @return 生产指示小单基础信息
     */
    public ProductSmallOrder selectProductSmallOrderById(Long id);

    /**
     * 查询生产指示小单基础信息列表
     * 
     * @param smallOrderSearchParamsDto 生产指示小单基础信息
     * @return 生产指示小单基础信息集合
     */
    public List<ProductSmallOrder> selectProductSmallOrderList(SmallOrderSearchParamsDto smallOrderSearchParamsDto);

    /**
     * 新增生产指示小单基础信息
     * 
     * @param productSmallOrder 生产指示小单基础信息
     * @return 结果
     */
    public int insertProductSmallOrder(ProductSmallOrder productSmallOrder);

    /**
     * 修改生产指示小单基础信息
     * 
     * @param productSmallOrder 生产指示小单基础信息
     * @return 结果
     */
    public int updateProductSmallOrder(ProductSmallOrder productSmallOrder);

    /**
     * 删除生产指示小单基础信息
     * 
     * @param id 生产指示小单基础信息ID
     * @return 结果
     */
    public int deleteProductSmallOrderById(Long id);

    /**
     * 批量删除生产指示小单基础信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteProductSmallOrderByIds(Long[] ids);

    /**
     * 查询业务员订单信息
     * @param param
     * @return
     */
    public List<ProductSalesManOrderImportEntity> selectSalesManOrderList(SalesmanOrderSearchParam param);
}
