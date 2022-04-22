package com.lingzhi.crm.order.servicce;

import com.lingzhi.crm.order.domin.ProductSmallOrder;
import com.lingzhi.crm.order.dto.SmallOrderSearchParamsDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 生产指示小单基础信息Service接口
 * 
 * @author ruoyi
 * @date 2022-02-10
 */
public interface IProductSmallOrderService 
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
     * 批量删除生产指示小单基础信息
     * 
     * @param ids 需要删除的生产指示小单基础信息ID
     * @return 结果
     */
    public int deleteProductSmallOrderByIds(Long[] ids);

    /**
     * 删除生产指示小单基础信息信息
     * 
     * @param id 生产指示小单基础信息ID
     * @return 结果
     */
    public int deleteProductSmallOrderById(Long id);

    /**
     * 生产指示单导入
     * @param file
     * @return
     */
    public boolean importProductSmallOrder(MultipartFile file) throws Exception;

    /**
     * 新增生产指示单及产品详情
     * @param productSmallOrder
     */
    public void insertSmallOrderAndDetailList(ProductSmallOrder productSmallOrder) throws Exception;

    /**
     * 生产指示单绑定运单编码(多个运单编码则用逗号隔开)
     * @param productSmallOrder waybillNos为输入的运单号(多个时用逗号隔开)
     */
    public void bindWaybill(ProductSmallOrder productSmallOrder) throws Exception;
}
