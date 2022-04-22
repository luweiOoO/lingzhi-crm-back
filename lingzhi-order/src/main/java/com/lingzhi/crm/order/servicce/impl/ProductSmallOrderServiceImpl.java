package com.lingzhi.crm.order.servicce.impl;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.*;

import com.lingzhi.crm.common.annotation.Excel;
import com.lingzhi.crm.common.core.text.Convert;
import com.lingzhi.crm.common.utils.DateUtils;
import com.lingzhi.crm.common.utils.StringUtils;
import com.lingzhi.crm.common.utils.poi.ExcelUtil;
import com.lingzhi.crm.common.utils.reflect.ReflectUtils;
import com.lingzhi.crm.order.domin.ProductSmallOrder;
import com.lingzhi.crm.order.domin.ProductSmallOrderDetail;
import com.lingzhi.crm.order.dto.SmallOrderSearchParamsDto;
import com.lingzhi.crm.order.servicce.IProductSmallOrderDetailService;
import com.lingzhi.crm.order.servicce.IProductSmallOrderService;
import com.lingzhi.crm.order.servicce.IProductWaybillMessageService;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import com.lingzhi.crm.order.mapper.ProductSmallOrderMapper;
import org.springframework.web.multipart.MultipartFile;

/**
 * 生产指示小单基础信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-02-10
 */
@Service
public class ProductSmallOrderServiceImpl implements IProductSmallOrderService
{

    private static final Logger log = LoggerFactory.getLogger(ProductSmallOrderServiceImpl.class);

    @Autowired
    private ProductSmallOrderMapper productSmallOrderMapper;
    @Autowired
    private IProductSmallOrderDetailService productSmallOrderDetailService;
    @Autowired
    private IProductWaybillMessageService productWaybillMessageService;

    /**
     * 查询生产指示小单基础信息
     * 
     * @param id 生产指示小单基础信息ID
     * @return 生产指示小单基础信息
     */
    @Override
    public ProductSmallOrder selectProductSmallOrderById(Long id)
    {
        return productSmallOrderMapper.selectProductSmallOrderById(id);
    }

    /**
     * 查询生产指示小单基础信息列表
     * 
     * @param smallOrderSearchParamsDto 生产指示小单基础信息
     * @return 生产指示小单基础信息
     */
    @Override
    public List<ProductSmallOrder> selectProductSmallOrderList(SmallOrderSearchParamsDto smallOrderSearchParamsDto)
    {
        return productSmallOrderMapper.selectProductSmallOrderList(smallOrderSearchParamsDto);
    }

    /**
     * 新增生产指示小单基础信息
     * 
     * @param productSmallOrder 生产指示小单基础信息
     * @return 结果
     */
    @Override
    public int insertProductSmallOrder(ProductSmallOrder productSmallOrder)
    {
        productSmallOrder.setCreateTime(DateUtils.getNowDate());
        return productSmallOrderMapper.insertProductSmallOrder(productSmallOrder);
    }

    /**
     * 修改生产指示小单基础信息
     * 
     * @param productSmallOrder 生产指示小单基础信息
     * @return 结果
     */
    @Override
    public int updateProductSmallOrder(ProductSmallOrder productSmallOrder)
    {
        productSmallOrder.setUpdateTime(DateUtils.getNowDate());
        return productSmallOrderMapper.updateProductSmallOrder(productSmallOrder);
    }

    /**
     * 批量删除生产指示小单基础信息
     * 
     * @param ids 需要删除的生产指示小单基础信息ID
     * @return 结果
     */
    @Override
    public int deleteProductSmallOrderByIds(Long[] ids)
    {
        return productSmallOrderMapper.deleteProductSmallOrderByIds(ids);
    }

    /**
     * 删除生产指示小单基础信息信息
     * 
     * @param id 生产指示小单基础信息ID
     * @return 结果
     */
    @Override
    public int deleteProductSmallOrderById(Long id)
    {
        return productSmallOrderMapper.deleteProductSmallOrderById(id);
    }

    @Override
    public boolean importProductSmallOrder(MultipartFile file) throws Exception {
        InputStream inputStream = file.getInputStream();

        String sheetName = "";
        //生产指示单表中产品信息起始行号
        int rowNum = 4;
        //生产指示单表中产品信息截至行号(-1表示未截至)
        int productEndRowNum = -1;
        ExcelUtil<ProductSmallOrderDetail> excelUtil = new ExcelUtil<ProductSmallOrderDetail>(ProductSmallOrderDetail.class);

        Excel.Type type = Excel.Type.IMPORT;
        Workbook wb = WorkbookFactory.create(inputStream);
        ProductSmallOrder productSmallOrder = new ProductSmallOrder();
        List<ProductSmallOrderDetail> list = new ArrayList<ProductSmallOrderDetail>();
        Sheet sheet = null;
        if (StringUtils.isNotEmpty(sheetName))
        {
            // 如果指定sheet名,则取指定sheet中的内容.
            sheet = wb.getSheet(sheetName);
        }
        else
        {
            // 如果传入的sheet名不存在则默认指向第1个sheet.
            sheet = wb.getSheetAt(0);
        }

        if (sheet == null)
        {
            throw new IOException("文件sheet不存在");
        }

        int rows = sheet.getPhysicalNumberOfRows();

        if (rows > 0)
        {
            // 定义一个map用于存放excel列的序号和field.
            Map<String, Integer> cellMap = new HashMap<String, Integer>();
            // 获取表头
            Row heard = sheet.getRow(rowNum);
            for (int i = 0; i < heard.getPhysicalNumberOfCells(); i++)
            {
                Cell cell = heard.getCell(i);
                if (StringUtils.isNotNull(cell))
                {
                    String value = excelUtil.getCellValue(heard, i).toString();
                    cellMap.put(value, i);
                }
                else
                {
                    cellMap.put(null, i);
                }
            }
            // 有数据时才处理 得到类的所有field.
            Field[] allFields = ProductSmallOrderDetail.class.getDeclaredFields();
            // 定义一个map用于存放列的序号和field.
            Map<Integer, Field> fieldsMap = new HashMap<Integer, Field>();
            for (int col = 0; col < allFields.length; col++)
            {
                Field field = allFields[col];
                Excel attr = field.getAnnotation(Excel.class);
                if (attr != null && (attr.type() == Excel.Type.ALL || attr.type() == type))
                {
                    // 设置类的私有字段属性可访问.
                    field.setAccessible(true);
                    Integer column = cellMap.get(attr.name());
                    if (column != null)
                    {
                        fieldsMap.put(column, field);
                    }
                }
            }
            for (int i = 0; i < rows; i++)
            {
                // 从第rowNum+1行开始取数据,默认第rowNum行是表头.
                Row row = sheet.getRow(i);
                if(row == null)
                {
                    continue;
                }
                ProductSmallOrderDetail entity = null;
                //前四行为生产指示单主表信息
                if(i <= rowNum){
                    if(i == 0){
                        // 国别/客户
                        Object customerMess = excelUtil.getCellValue(row, 8);
                        if(customerMess != null){
                            String customerMessString = Convert.toStr(customerMess);
                            //国别和客户信息根据分隔符进行拆分
                            String[] customerMessArray = customerMessString.split("/");
                            if(customerMessArray.length == 2){
                                //赋值
                                productSmallOrder.setCustomerCountry(customerMessArray[0]);
                                productSmallOrder.setCustomerName(customerMessArray[1]);
                            }
                        }
                    }else if(i == 1){
//                        //订单编号
//                        cellValue = excelUtil.getCellValue(row, 9);
//                        System.out.println(cellValue);
                    }else if(i == 2){
                        //下单日期
                        Object orderDateObject = excelUtil.getCellValue(row, 9);
                        if(orderDateObject != null){
                            Date orderDate = DateUtils.parseDate(orderDateObject);
                            productSmallOrder.setOrderDate(orderDate);
                        }
                        //工厂单号
                        Object orderNoObject = excelUtil.getCellValue(row,0);
                        if(orderNoObject != null){
                            String orderNo =  Convert.toStr(orderNoObject);
                            int orderNoDiv = -1;
                            if(orderNo.contains(":")){
                                orderNoDiv = orderNo.indexOf(":");
                            }else if(orderNo.contains("：")){
                                orderNoDiv = orderNo.indexOf("：");
                            }
                            if(orderNoDiv > 0){
                                orderNo = orderNo.substring(orderNoDiv + 1);
                                orderNo = orderNo.trim();
                                productSmallOrder.setOrderNo(orderNo);
                            }

                        }
                        //到账金额比例
                        Object arrivalMoneyPercentObject = excelUtil.getCellValue(row,4);
                        if(arrivalMoneyPercentObject != null){
                            String arrivalMoneyPercentPri = Convert.toStr(arrivalMoneyPercentObject);
                            int startIndex = -1;
                            if(arrivalMoneyPercentPri.contains("(")){
                                startIndex = arrivalMoneyPercentPri.indexOf("(");
                            }else if(arrivalMoneyPercentPri.contains("（")){
                                startIndex = arrivalMoneyPercentPri.indexOf("（");
                            }
                            int endIndex = arrivalMoneyPercentPri.indexOf("%");
                            if(startIndex > 0 && endIndex > 0){
                                arrivalMoneyPercentPri = arrivalMoneyPercentPri.substring(startIndex + 1,endIndex);
                                arrivalMoneyPercentPri = arrivalMoneyPercentPri.trim();
                                productSmallOrder.setArrivalMoneyPercent(new BigDecimal(arrivalMoneyPercentPri));
                            }
                        }
                    }else if(i == 3){
                        //发货日期
                        Object planDeliveryDateObject = excelUtil.getCellValue(row, 9);
                        if(planDeliveryDateObject != null){
                            Date planDeliveryDate = DateUtils.parseDate(planDeliveryDateObject);
                            productSmallOrder.setPlanDeliveryDate(planDeliveryDate);
                        }
                    }
                }else {
                    Object firstColumnObject = excelUtil.getCellValue(row, 0);
                    if(firstColumnObject != null){
                        String firstColumnString = Convert.toStr(firstColumnObject);
                        //读取到的值为"货值总计"则表示订单产品详情已读取完成
                        if("货值总计".equals(firstColumnString)){
                            //产品详情列表结束位置
                            productEndRowNum = i;
                            //货值总价(美元)
                            Object goodsAmountPriceDollarObject = excelUtil.getCellValue(row, 8);
                            if(goodsAmountPriceDollarObject != null){
                                BigDecimal goodsAmountPriceDollar = Convert.toBigDecimal(goodsAmountPriceDollarObject);
                                productSmallOrder.setGoodsAmountPriceDollar(goodsAmountPriceDollar);
                                //TODO 获取实时汇率并计算货值总价(人民币)
                            }

                        }
                    }
                    //获取付款渠道
                    if(productEndRowNum > 0 && productEndRowNum + 1 == i){
                        Object paymentChannelObject = excelUtil.getCellValue(row, 1);
                        if(paymentChannelObject != null){
                            String paymentChannel = Convert.toStr(paymentChannelObject);
                            productSmallOrder.setPaymentChannel(paymentChannel);
                        }
                    }
                    //获取阿里巴巴订单号
                    if(productEndRowNum > 0 && productEndRowNum + 2 == i){
                        Object alibabaOrderNoObject = excelUtil.getCellValue(row, 1);
                        if(alibabaOrderNoObject != null){
                            String alibabaOrderNo = Convert.toStr(alibabaOrderNoObject);
                            productSmallOrder.setAlibabaOrderNo(alibabaOrderNo);
                        }
                    }
                    //获取快递类别
                    if(productEndRowNum > 0 && productEndRowNum + 3 == i){
                        Object expressCategoryObject = excelUtil.getCellValue(row, 1);
                        if(expressCategoryObject != null){
                            String expressCategory = Convert.toStr(expressCategoryObject);
                            productSmallOrder.setExpressCategory(expressCategory);
                        }
                    }
                    //获取订单质量要求
                    if(productEndRowNum > 0 && productEndRowNum + 4 == i){
                        Object orderQualityRequireObject = excelUtil.getCellValue(row, 2);
                        if(orderQualityRequireObject != null){
                            String orderQualityRequire = Convert.toStr(orderQualityRequireObject);
                            productSmallOrder.setOrderQualityRequire(orderQualityRequire);
                        }
                    }
                    //获取订单包装要求
                    if(productEndRowNum > 0 && productEndRowNum + 5 == i){
                        Object orderPackageRequireObject = excelUtil.getCellValue(row, 2);
                        if(orderPackageRequireObject != null){
                            String orderPackageRequire = Convert.toStr(orderPackageRequireObject);
                            productSmallOrder.setOrderPackageRequire(orderPackageRequire);
                        }
                    }
                    //获取订单发货日期要求
                    if(productEndRowNum > 0 && productEndRowNum + 6 == i){
                        Object orderDeliveryDateRequireObject = excelUtil.getCellValue(row, 2);
                        if(orderDeliveryDateRequireObject != null){
                            String orderDeliveryDateRequire = Convert.toStr(orderDeliveryDateRequireObject);
                            productSmallOrder.setOrderDeliveryDateRequire(orderDeliveryDateRequire);
                        }
                    }
                    //获取业务员姓名
                    if(productEndRowNum > 0 && productEndRowNum + 7 == i){
                        Object orderSalesNameObject = excelUtil.getCellValue(row, 1);
                        if(orderSalesNameObject != null){
                            String orderSalesName = Convert.toStr(orderSalesNameObject);
                            productSmallOrder.setOrderSalesName(orderSalesName);
                        }
                    }

                    //读取产品详情列表
                    if(productEndRowNum == -1){
                        for (Map.Entry<Integer, Field> entry : fieldsMap.entrySet())
                        {

                            Object val = excelUtil.getCellValue(row, entry.getKey());

                            // 如果不存在实例则新建.
                            entity = (entity == null ? ProductSmallOrderDetail.class.newInstance() : entity);
                            // 从map中得到对应列的field.
                            Field field = fieldsMap.get(entry.getKey());
                            // 取得类型,并根据对象类型设置值.
                            Class<?> fieldType = field.getType();
                            if (String.class == fieldType)
                            {
                                String s = Convert.toStr(val);
                                if (StringUtils.endsWith(s, ".0"))
                                {
                                    val = StringUtils.substringBefore(s, ".0");
                                }
                                else
                                {
                                    String dateFormat = field.getAnnotation(Excel.class).dateFormat();
                                    if (StringUtils.isNotEmpty(dateFormat))
                                    {
                                        val = DateUtils.parseDateToStr(dateFormat, (Date) val);
                                    }
                                    else
                                    {
                                        val = Convert.toStr(val);
                                    }
                                }
                            }
                            else if ((Integer.TYPE == fieldType || Integer.class == fieldType) && StringUtils.isNumeric(Convert.toStr(val)))
                            {
                                val = Convert.toInt(val);
                            }
                            else if (Long.TYPE == fieldType || Long.class == fieldType)
                            {
                                val = Convert.toLong(val);
                            }
                            else if (Double.TYPE == fieldType || Double.class == fieldType)
                            {
                                val = Convert.toDouble(val);
                            }
                            else if (Float.TYPE == fieldType || Float.class == fieldType)
                            {
                                val = Convert.toFloat(val);
                            }
                            else if (BigDecimal.class == fieldType)
                            {
                                val = Convert.toBigDecimal(val);
                            }
                            else if (Date.class == fieldType)
                            {
                                if (val instanceof String)
                                {
                                    val = DateUtils.parseDate(val);
                                }
                                else if (val instanceof Double)
                                {
                                    val = DateUtil.getJavaDate((Double) val);
                                }
                            }
                            else if (Boolean.TYPE == fieldType || Boolean.class == fieldType)
                            {
                                val = Convert.toBool(val, false);
                            }
                            if (StringUtils.isNotNull(fieldType))
                            {
                                Excel attr = field.getAnnotation(Excel.class);
                                String propertyName = field.getName();
                                if (StringUtils.isNotEmpty(attr.targetAttr()))
                                {
                                    propertyName = field.getName() + "." + attr.targetAttr();
                                }
                                else if (StringUtils.isNotEmpty(attr.readConverterExp()))
                                {
                                    val = ExcelUtil.reverseByExp(Convert.toStr(val), attr.readConverterExp(), attr.separator());
                                }
                                else if (StringUtils.isNotEmpty(attr.dictType()))
                                {
                                    val = ExcelUtil.reverseDictByExp(Convert.toStr(val), attr.dictType(), attr.separator());
                                }
                                ReflectUtils.invokeSetter(entity, propertyName, val);
                            }
                        }
                    }
                    if(productEndRowNum == -1){
                        list.add(entity);
                    }
                }

            }
        }
        productSmallOrder.setOrderDetailList(list);
        //新增指示单主表信息及产品列表
        insertSmallOrderAndDetailList(productSmallOrder);
        return true;
    }

    @Override
    public void insertSmallOrderAndDetailList(ProductSmallOrder productSmallOrder) throws Exception {
        if(StringUtils.isEmpty(productSmallOrder.getOrderNo())){
            throw new Exception("生产指示单号不能为空");
        }
        productSmallOrder.setCreateInfo();
        //新增生产指示单主表信息
        productSmallOrderMapper.insertProductSmallOrder(productSmallOrder);
        List<ProductSmallOrderDetail> orderDetailList = productSmallOrder.getOrderDetailList();
        if(orderDetailList != null && orderDetailList.size() > 0){
            orderDetailList.forEach(detail -> {
                detail.setOrderNo(productSmallOrder.getOrderNo());
                detail.setOrderId(productSmallOrder.getId());
                detail.setCreateInfo();
            });
            //批量新增产品信息
            productSmallOrderDetailService.insertProductSmallOrderDetailBatch(orderDetailList);
        }
    }

    @Override
    public void bindWaybill(ProductSmallOrder productSmallOrder) throws Exception {
        log.info("生产指示单绑定运单号---入参:{}",productSmallOrder);
        //运单编号不为空
        if(StringUtils.isNotEmpty(productSmallOrder.getWaybillNos())){
            List<String> waybillNoList = Arrays.asList(productSmallOrder.getWaybillNos().split(","));
            log.info("生产指示单绑定运单号---运单号列表:{}",waybillNoList);
            //若绑定的运单号都已录入则进行绑定并计算
            if(waybillNoList.size() > 0 && productWaybillMessageService.checkWaybillNoList(waybillNoList)){
                BigDecimal sumWaybillFare = productWaybillMessageService.sumWaybillFareByNoList(waybillNoList);
                log.info("生产指示单绑定运单号---汇总运费:{}",sumWaybillFare);
                productSmallOrder.setWaybillFare(sumWaybillFare);
                productSmallOrder.setUpdateInfo();
                //更新生产指示单信息
                productSmallOrderMapper.updateProductSmallOrder(productSmallOrder);
            }

        }
    }


}
