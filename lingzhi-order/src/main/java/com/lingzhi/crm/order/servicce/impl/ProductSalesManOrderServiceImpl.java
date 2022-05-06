package com.lingzhi.crm.order.servicce.impl;

import com.lingzhi.crm.common.core.domain.entity.SysUser;
import com.lingzhi.crm.common.utils.ObjectUtils;
import com.lingzhi.crm.common.utils.StringUtils;
import com.lingzhi.crm.common.utils.poi.ExcelUtil;
import com.lingzhi.crm.customer.service.ICustomerCompanyContactsService;
import com.lingzhi.crm.order.domin.ProductSmallOrder;
import com.lingzhi.crm.common.domain.ProductSalesManOrderImportEntity;
import com.lingzhi.crm.order.domin.ProductWaybillMessage;
import com.lingzhi.crm.order.dto.SalesmanOrderSearchParam;
import com.lingzhi.crm.order.dto.SmallOrderSearchParamsDto;
import com.lingzhi.crm.order.dto.WaybillMessageParamsDto;
import com.lingzhi.crm.order.servicce.IProductSalesManOrderService;
import com.lingzhi.crm.order.servicce.IProductSmallOrderService;
import com.lingzhi.crm.order.servicce.IProductWaybillMessageService;
import com.lingzhi.crm.system.service.ISysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 业务员订单相关接口实现类
 */
@Service
@Transactional
public class ProductSalesManOrderServiceImpl implements IProductSalesManOrderService {


    private static final Logger logger = LoggerFactory.getLogger(ProductSalesManOrderServiceImpl.class);

    @Autowired
    private IProductSmallOrderService productSmallOrderService;
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private ICustomerCompanyContactsService customerCompanyContactsService;
    @Autowired
    private IProductWaybillMessageService productWaybillMessageService;

    @Override
    public int importSalesManOrder(MultipartFile file){

        try {
            InputStream inputStream = file.getInputStream();
            ExcelUtil<ProductSalesManOrderImportEntity> excelUtil = new ExcelUtil<>(ProductSalesManOrderImportEntity.class);
            //导入数据并转换为实体类
            List<ProductSalesManOrderImportEntity> orderImportEntityList = excelUtil.importExcel(inputStream,0);
            //过滤掉为null的数据
            orderImportEntityList = orderImportEntityList.stream().filter(obj -> !ObjectUtils.checkObjAllFieldsIsNull(obj)).collect(Collectors.toList());
            if(orderImportEntityList.size() < 1){
                throw new Exception("导入数据不能为空");
            }
            //遍历处理数据
            orderImportEntityList.forEach(obj -> {
                try {
                    //校验参数
                    this.checkImportParam(obj);
                    //对导入的业务员订单数据进行处理
                    this.handleImportData(obj);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }catch (Exception e){


        }
        return 1;
    }

    @Override
    public List<ProductSalesManOrderImportEntity> selectSalesManOrderList(SalesmanOrderSearchParam param) {
        List<ProductSalesManOrderImportEntity> orderList = new LinkedList<>();
        orderList = productSmallOrderService.getSalesManOrderList(param);
        //查询
        return orderList;
    }

    /**
     * 校验导入的数据
     * @param entity
     */
    private void checkImportParam(ProductSalesManOrderImportEntity entity) throws Exception {
        if(StringUtils.isEmpty(entity.getSalesmanName())){
            throw new Exception("业务员姓名不能为空");
        }
        if(StringUtils.isEmpty(entity.getOrderNo())){
            throw new Exception("订单号不能为空");
        }
        if(StringUtils.isEmpty(entity.getPaymentDate())){
            throw new Exception("到账日期不能为空");
        }
        if(StringUtils.isEmpty(entity.getPaymentChannel())){
            throw new Exception("付款方式不能为空");
        }
        if(entity.getGoodsAmountPriceDollar() == null){
            throw new Exception("到账金额不能为空");
        }
        if(StringUtils.isEmpty(entity.getCustomerName())
                || StringUtils.isEmpty(entity.getCustomerCompanyName())
                || StringUtils.isEmpty(entity.getCustomerNickName())
                || StringUtils.isEmpty(entity.getCustomerAddress())
                || StringUtils.isEmpty(entity.getCustomerCountry())
                || StringUtils.isEmpty(entity.getCustomerPhone())
                || StringUtils.isEmpty(entity.getCustomerSource())){
            throw new Exception("客户相关信息不能为空");
        }
    }


    /**
     * 对导入的业务员订单数据进行处理
     * @param entity
     */
    private void handleImportData(ProductSalesManOrderImportEntity entity) throws Exception {

        //------------------1、处理订单信息start------------------------------------

        SmallOrderSearchParamsDto smallOrderParam = new SmallOrderSearchParamsDto();
        smallOrderParam.setOrderNo(entity.getOrderNo());
        List<ProductSmallOrder> smallOrderList = productSmallOrderService.selectProductSmallOrderList(smallOrderParam);
        ProductSmallOrder productSmallOrder = new ProductSmallOrder();
        //订单数据处理标识 若为true则进行新增 false则进行更新
        boolean handleOrderFlag = true;
        //查询不到此订单号则进行新增
        if(smallOrderList == null || smallOrderList.size() < 1){
            productSmallOrder.setCreateInfo();
        }else {
            handleOrderFlag = false;
            //已有此订单号则进行更新
            productSmallOrder = smallOrderList.get(0);
            productSmallOrder.setUpdateInfo();
        }

        //对本次导入的订单相关字段进行更新
        productSmallOrder.setOrderNo(entity.getOrderNo());
        productSmallOrder.setCustomerCountry(entity.getCustomerCountry());
        productSmallOrder.setCustomerName(entity.getCustomerName());
        if(StringUtils.isNotEmpty(entity.getArrivalMoneyPercent())){
            String arrivalMoneyPercentString = entity.getArrivalMoneyPercent();
            if(arrivalMoneyPercentString.contains("%")){
                arrivalMoneyPercentString = arrivalMoneyPercentString.substring(0,arrivalMoneyPercentString.indexOf("%"));
            }
            BigDecimal arrivalMoneyPercent = new BigDecimal(arrivalMoneyPercentString).divide(new BigDecimal(100),2, RoundingMode.HALF_UP);
            productSmallOrder.setArrivalMoneyPercent(arrivalMoneyPercent);
        }
        productSmallOrder.setGoodsAmountPriceDollar(entity.getGoodsAmountPriceDollar());
        if(StringUtils.isNotEmpty(entity.getPaymentChannel())){
            productSmallOrder.setPaymentChannel(entity.getPaymentChannel());
        }
        if(StringUtils.isNotEmpty(entity.getExpressCategory())){
            productSmallOrder.setExpressCategory(entity.getExpressCategory());
        }
        if(StringUtils.isNotEmpty(entity.getRemark())){
            productSmallOrder.setRemark(entity.getRemark());
        }
        //根据业务员姓名查询库中是否已存在,不存在则报错
        SysUser sysUser = sysUserService.selectUserByUserName(entity.getSalesmanName());
        if(sysUser == null){
            throw new Exception("系统未维护业务员账号: " + entity.getSalesmanName());
        }else {
            productSmallOrder.setOrderSalesName(sysUser.getUserName());
            productSmallOrder.setOrderSalesId(sysUser.getUserId());
        }
        //进行订单的数据处理
        if(handleOrderFlag){
            productSmallOrderService.insertProductSmallOrder(productSmallOrder);
        }else {
            productSmallOrderService.updateProductSmallOrder(productSmallOrder);
        }
        //---------------------------1、处理订单信息end-----------------------------

        //---------------------------2、处理客户信息start---------------------------
        customerCompanyContactsService.insertOrUpdateCustomerContact(entity);
        //---------------------------2、处理客户信息end-----------------------------

        //---------------------------3、处理运单信息start---------------------------
        //查询是否已录入该运单号
        WaybillMessageParamsDto waybillParam = new WaybillMessageParamsDto();
        waybillParam.setWaybillNo(entity.getWaybillNos());
        List<ProductWaybillMessage> productWaybillMessages = productWaybillMessageService.selectProductWaybillMessageList(waybillParam);
        ProductWaybillMessage waybillMessage;
        if(productWaybillMessages != null && productWaybillMessages.size() > 0 && entity.getWaybillFare() != null){
            waybillMessage = productWaybillMessages.get(0);
            waybillMessage.setWaybillFare(entity.getWaybillFare());
            waybillMessage.setUpdateInfo();
            productWaybillMessageService.updateProductWaybillMessage(waybillMessage);
        }else if(productWaybillMessages == null || productWaybillMessages.size() < 1){
            waybillMessage = new ProductWaybillMessage();
            waybillMessage.setWaybillFare(entity.getWaybillFare());
            waybillMessage.setWaybillNo(entity.getWaybillNos());
            waybillMessage.setCreateInfo();
            productWaybillMessageService.insertProductWaybillMessage(waybillMessage);
        }
        //---------------------------3、处理运单信息end-----------------------------
    }
}
