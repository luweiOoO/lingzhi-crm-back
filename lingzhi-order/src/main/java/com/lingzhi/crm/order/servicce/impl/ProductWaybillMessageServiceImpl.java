package com.lingzhi.crm.order.servicce.impl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import com.lingzhi.crm.common.core.domain.BaseEntity;
import com.lingzhi.crm.common.utils.DateUtils;
import com.lingzhi.crm.common.utils.StringUtils;
import com.lingzhi.crm.order.constants.WaybillConstants;
import com.lingzhi.crm.order.domin.ProductWaybillMessage;
import com.lingzhi.crm.order.dto.WaybillMessageParamsDto;
import com.lingzhi.crm.order.servicce.IProductWaybillMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import com.lingzhi.crm.order.mapper.ProductWaybillMessageMapper;
import org.springframework.web.multipart.MultipartFile;

/**
 * 运单信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-02-10
 */
@Service
public class ProductWaybillMessageServiceImpl implements IProductWaybillMessageService
{
    @Autowired
    private ProductWaybillMessageMapper productWaybillMessageMapper;

    /**
     * 查询运单信息
     * 
     * @param id 运单信息ID
     * @return 运单信息
     */
    @Override
    public ProductWaybillMessage selectProductWaybillMessageById(Long id)
    {
        return productWaybillMessageMapper.selectProductWaybillMessageById(id);
    }

    /**
     * 查询运单信息列表
     * 
     * @param waybillMessageParamsDto 运单信息
     * @return 运单信息
     */
    @Override
    public List<ProductWaybillMessage> selectProductWaybillMessageList(WaybillMessageParamsDto waybillMessageParamsDto)
    {
        return productWaybillMessageMapper.selectProductWaybillMessageList(waybillMessageParamsDto);
    }

    /**
     * 新增运单信息
     * 
     * @param productWaybillMessage 运单信息
     * @return 结果
     */
    @Override
    public int insertProductWaybillMessage(ProductWaybillMessage productWaybillMessage)
    {
        productWaybillMessage.setCreateTime(DateUtils.getNowDate());
        return productWaybillMessageMapper.insertProductWaybillMessage(productWaybillMessage);
    }

    /**
     * 修改运单信息
     * 
     * @param productWaybillMessage 运单信息
     * @return 结果
     */
    @Override
    public int updateProductWaybillMessage(ProductWaybillMessage productWaybillMessage)
    {
        productWaybillMessage.setUpdateTime(DateUtils.getNowDate());
        return productWaybillMessageMapper.updateProductWaybillMessage(productWaybillMessage);
    }

    /**
     * 批量删除运单信息
     * 
     * @param ids 需要删除的运单信息ID
     * @return 结果
     */
    @Override
    public int deleteProductWaybillMessageByIds(Long[] ids)
    {
        return productWaybillMessageMapper.deleteProductWaybillMessageByIds(ids);
    }

    /**
     * 删除运单信息信息
     * 
     * @param id 运单信息ID
     * @return 结果
     */
    @Override
    public int deleteProductWaybillMessageById(Long id)
    {
        return productWaybillMessageMapper.deleteProductWaybillMessageById(id);
    }

    @Override
    public int importWaybill(MultipartFile file) throws Exception {
        InputStream inputStream = file.getInputStream();
        Reader read = new InputStreamReader(inputStream,"GBK");
        BufferedReader bReader = new BufferedReader(read);
        List<ProductWaybillMessage> successList = new LinkedList<>();
        List<List<String>> failList = new LinkedList<>();
        int i = 0;
        //csv文件按行读取并整合数据
        while(true){
            String line = bReader.readLine();
            if(line == null){
                break;
            }
            String[] arrStr = line.split(",");
            //将第一行数据转换为一个对象
            List<String> lineDateList = Arrays.asList(arrStr);
            if(i == 0){
                //保存第一行标题数据
                failList.add(lineDateList);
            }
            //第二行开始转换运单数据
            if(i > 0){
                //校验参数
                if(checkImportWaybillParams(lineDateList)){
                    transformImportSuccessWaybillList(successList,lineDateList);
                }else {
                    //失败逻辑处理
                    failList.add(lineDateList);
                }

            }
            i++;
        }
        //整合成功的运单信息列表进行入库处理
        if(successList.size() > 0){
            //提取出导入成功的运单号列表并去重
            List<String> insertWaybillNoList = successList.stream().map(ProductWaybillMessage::getWaybillNo).distinct().collect(Collectors.toList());
            //根据本次导入的运单号查询出数据库中已存在的运单号列表
            List<String> oldWaybillNoList = productWaybillMessageMapper.selectWayBillNoListByNoList(insertWaybillNoList);
            //需新增的运单信息列表
            List<ProductWaybillMessage> insertWaybillList;
            //需更新的运单信息列表
            List<ProductWaybillMessage> updateWaybillList = new LinkedList<>();
            //若数据库中有此次导入的运单号，则需更新这些运单号
            if(oldWaybillNoList != null && oldWaybillNoList.size() > 0){
                //筛选出需更新的运单信息列表
                updateWaybillList = successList.stream().filter(waybill -> oldWaybillNoList.contains(waybill.getWaybillNo())).collect(Collectors.toList());
                //筛选出需新增的运单信息列表
                insertWaybillList = successList.stream().filter(waybill -> !oldWaybillNoList.contains(waybill.getWaybillNo())).collect(Collectors.toList());
            }else {
                insertWaybillList = successList;
            }
            if(insertWaybillList.size() > 0){
                //批量新增操作
                productWaybillMessageMapper.insertProductWaybillMessageBatch(insertWaybillList);
            }
            if(updateWaybillList.size() > 0){
                updateWaybillList.forEach(BaseEntity::setUpdateInfo);
                //批量更新操作
                productWaybillMessageMapper.updateProductWaybillMessageByWaybillNoBatch(updateWaybillList);
            }
        }
        //若存在整合失败的运单信息列表则进行导出操作
        if(failList.size() > 1){
            //TODO list中第一条为表头字段,其余为数据 需导出CSV文件
        }
        return successList.size();
    }

    @Override
    public boolean checkWaybillNoList(List<String> waybillNoList) throws Exception {
        //根据输入运单号列表查询已录入的订单号列表
        List<String> recordWaybillNoList = productWaybillMessageMapper.selectRecordNoList(waybillNoList);
        //过滤出系统中未录入的运单号
        List<String> notRecordNoList = waybillNoList.stream().filter(waybillNo -> !recordWaybillNoList.contains(waybillNo)).collect(Collectors.toList());
        //若有未录入运单号则抛出异常
        if (notRecordNoList != null && notRecordNoList.size() > 0) {
            throw new Exception("单据号: " + notRecordNoList.toString() + " 未录入");
        }
        return true;
    }

    @Override
    public BigDecimal sumWaybillFareByNoList(List<String> waybillNoList) {
        return productWaybillMessageMapper.sumWaybillFareByNoList(waybillNoList);
    }

    /**
     * 将导入文件读取的单条waybill数据添加到waybillMap中
     * @param successWaybillList
     * @param lineDateList
     * @return
     */
    private List<ProductWaybillMessage> transformImportSuccessWaybillList(List<ProductWaybillMessage> successWaybillList,List<String> lineDateList){
        ProductWaybillMessage waybillMessage = new ProductWaybillMessage();
        //运单号
        waybillMessage.setWaybillNo(lineDateList.get(2));
        //付款账号
        waybillMessage.setPayAccount(lineDateList.get(0));
        //发件日期
        waybillMessage.setDeliveryDate(StringUtils.isNotEmpty(lineDateList.get(3))
                ? DateUtils.parseDate(lineDateList.get(3)) : null);
        //发件人
        waybillMessage.setDeliveryName(lineDateList.get(15));
        //收件人
        waybillMessage.setReceiveName(lineDateList.get(20));
        //收件人公司
        waybillMessage.setReceiveCompany(lineDateList.get(21));
        //收件地址1
        waybillMessage.setReceiveAddressA(lineDateList.get(22));
        //收件地址2
        waybillMessage.setReceiveAddressB(lineDateList.get(23));
        //收件地址邮编
        waybillMessage.setReceiveAddressPostalCode(lineDateList.get(24));
        //账单日期
        waybillMessage.setPaymentDate(StringUtils.isNotEmpty(lineDateList.get(25))
                ? DateUtils.parseDate(lineDateList.get(25)) : null);
        //货物件数
        waybillMessage.setGoodsNumber(StringUtils.isNotEmpty(lineDateList.get(26)) ? Integer.getInteger(lineDateList.get(26)) : 0);
        //重量
        waybillMessage.setGoodsWeight(StringUtils.isNotEmpty(lineDateList.get(27)) ? new BigDecimal(lineDateList.get(27)) : BigDecimal.ZERO);
        //货物价值
        waybillMessage.setGoodsPrice(StringUtils.isNotEmpty(lineDateList.get(28)) ? new BigDecimal(lineDateList.get(28)) : BigDecimal.ZERO);
        //运单金额
        waybillMessage.setWaybillFare(StringUtils.isNotEmpty(lineDateList.get(45)) ? new BigDecimal(lineDateList.get(45)) : BigDecimal.ZERO);
        //创建人信息
        waybillMessage.setCreateInfo();

        successWaybillList.add(waybillMessage);
        return successWaybillList;
    }

    /**
     * 校验从csv文件中读取的单条运单信息
     * 需不为空的字段 2:运单号/3:发件日期/45:金额合计(人民币)
     * @param lineDateList
     * @return
     */
    private boolean checkImportWaybillParams(List<String> lineDateList){
        if(lineDateList == null
                || lineDateList.size() != WaybillConstants.WAYBILL_CSV_DATE_LENGTH
                || StringUtils.isEmpty(lineDateList.get(2))
                || StringUtils.isEmpty(lineDateList.get(3))
                || StringUtils.isEmpty(lineDateList.get(45))){
            return false;
        }
        return true;
    }
}
