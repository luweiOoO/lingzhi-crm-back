package com.lingzhi.crm.order.servicce;

import com.lingzhi.crm.order.domin.ProductWaybillMessage;
import com.lingzhi.crm.order.dto.WaybillMessageParamsDto;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

/**
 * 运单信息Service接口
 * 
 * @author ruoyi
 * @date 2022-02-10
 */
public interface IProductWaybillMessageService 
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
     * 批量删除运单信息
     * 
     * @param ids 需要删除的运单信息ID
     * @return 结果
     */
    public int deleteProductWaybillMessageByIds(Long[] ids);

    /**
     * 删除运单信息信息
     * 
     * @param id 运单信息ID
     * @return 结果
     */
    public int deleteProductWaybillMessageById(Long id);

    /**
     * 导入运单信息(csv文件)
     * @param file 文件
     * @return 导入成功数量
     */
    int importWaybill(MultipartFile file) throws Exception;

    /**
     * 核对运单号是否已录入
     * @param waybillNoList
     * @return
     */
    boolean checkWaybillNoList(List<String> waybillNoList) throws Exception;

    /**
     * 根据运单号列表计算汇总运费
     * @param waybillNoList
     * @return
     */
    BigDecimal sumWaybillFareByNoList(List<String> waybillNoList);
}
