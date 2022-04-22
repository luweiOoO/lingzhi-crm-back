package com.lingzhi.crm.order.mapper;

import com.lingzhi.crm.order.domin.ProductWaybillMessage;
import com.lingzhi.crm.order.dto.WaybillMessageParamsDto;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * 运单信息Mapper接口
 * 
 * @author ruoyi
 * @date 2022-02-10
 */
public interface ProductWaybillMessageMapper 
{
    /**
     * 查询运单信息
     * 
     * @param id 运单信息ID
     * @return 运单信息
     */
    public ProductWaybillMessage selectProductWaybillMessageById(Long id);

    /**
     * 查询运单信息列表
     * 
     * @param waybillMessageParamsDto 运单信息
     * @return 运单信息集合
     */
    public List<ProductWaybillMessage> selectProductWaybillMessageList(WaybillMessageParamsDto waybillMessageParamsDto);

    /**
     * 新增运单信息
     * 
     * @param productWaybillMessage 运单信息
     * @return 结果
     */
    public int insertProductWaybillMessage(ProductWaybillMessage productWaybillMessage);

    /**
     * 修改运单信息
     * 
     * @param productWaybillMessage 运单信息
     * @return 结果
     */
    public int updateProductWaybillMessage(ProductWaybillMessage productWaybillMessage);

    /**
     * 删除运单信息
     * 
     * @param id 运单信息ID
     * @return 结果
     */
    public int deleteProductWaybillMessageById(Long id);

    /**
     * 批量删除运单信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteProductWaybillMessageByIds(Long[] ids);

    /**
     * 根据指定运单号列表查询出数据表中已存在的运单号列表
     * @param waybillNoList
     * @return
     */
    public List<String> selectWayBillNoListByNoList(@Param("waybillNoList") List<String> waybillNoList);

    /**
     * 批量新增运单信息
     * @param waybillList
     * @return
     */
    public int insertProductWaybillMessageBatch(@Param("waybillList") List<ProductWaybillMessage> waybillList);

    /**
     * 批量更新运单信息(根据运单号)
     * @param waybillList
     * @return
     */
    public int updateProductWaybillMessageByWaybillNoBatch(@Param("waybillList") List<ProductWaybillMessage> waybillList);

    /**
     * 根据输入的运单号列表查询出库内已录入的运单号列表
     * @param waybillNoList
     * @return
     */
    public List<String> selectRecordNoList(@Param("waybillNoList") List<String> waybillNoList);

    /**
     * 根据运单号列表汇总计算运费
     * @param waybillNoList
     * @return
     */
    public BigDecimal sumWaybillFareByNoList(@Param("waybillNoList") List<String> waybillNoList);
}
