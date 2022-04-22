package com.lingzhi.crm.system.service.impl;

import com.lingzhi.crm.system.domain.dhl.*;
import com.lingzhi.crm.system.service.IDhlService;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;


@Service
public class DhlServiceImpl implements IDhlService {

    /**
     * Message-Reference-Date 发送请求的时间
     * Message-Reference 报文唯一标识
     * Webstore-Platform-Name 客户网店平台名称
     * Webstore-Platform-Version 客户网店平台版本号
     * Shipping-System-Platform-Name 客户发件系统名称
     * Shipping-System-Platform-Version 客户发件系统版本号
     * Plugin-Name 客户系统插件名称
     * Plugin-Version 插件版本号
     * @param param
     * @return
     */

    @Override
    public Object createShipment(CreateShipmentRequestParam param) {

        CreateShipmentRequestParam createShipmentRequestParam = new CreateShipmentRequestParam();


        //创建连接
        AsyncHttpClient client = new DefaultAsyncHttpClient();
        client.prepare("POST","https://express.api.dhl.com/mydhlapi/shipments")
                .setHeader("content-type","application/json")
                .setHeader("Message-Reference","SOME_STRING_VALUE")
                .setHeader("Message-Reference-Date","SOME_STRING_VALUE")
                .setHeader("Plugin-Name","SOME_STRING_VALUE")
                .setHeader("Plugin-Version","SOME_STRING_VALUE")
                .setHeader("Shipping-System-Platform-Name","SOME_STRING_VALUE")
                .setHeader("Shipping-System-Platform—Version","SOME_STRING_VALUE")
                .setHeader("Webstore-Platform-Name","SOME_STRING_VALUE")
                .setHeader("Webstore-Platform-Version","SOME_STRING_VALUE")
                .setHeader("Authorization","Basic REPLACE_BASIC_AUTH")
                .setBody(createShipmentRequestParam.toString());

        return null;
    }



    public CreateShipmentRequestParam createParam(){
        CreateShipmentRequestParam requestParam = new CreateShipmentRequestParam();
        //发件日期
        requestParam.setPlannedShippingDateAndTime("2022-04-20 21:24:35");

        //------预约取件信息start-------
        ShipmentPickupParam pickup = new ShipmentPickupParam();
        //是否启用预约取件
        pickup.setRequested(true);
        //当日最晚取件时间
        pickup.setCloseTime("17:00");
        pickup.setLocation("1699产业园C1座502");
        //取件特殊说明信息
        SpecialInstruction specialInstruction = new SpecialInstruction();
        specialInstruction.setValue("取件特殊说明");
        List<SpecialInstruction> specialInstructions = Collections.singletonList(specialInstruction);
        pickup.setSpecialInstructions(specialInstructions);
        //预约取件地址节点
        pickup.setPickupDetails(new ShipperDetails());
        //远程取件预约请求方信息
        pickup.setPickupRequestorDetails(new ShipperDetails());
        requestParam.setPickup(pickup);
        //---------预约取件信息end-----------

        //是否返回OOD网址链接
        requestParam.setRequestOndemandDeliveryURL(false);
        //是否返回预估运费
        requestParam.setGetRateEstimates(true);
        //Global产品编码
        requestParam.setProductCode("P");
        //本地产品编码
        requestParam.setLocalProductCode("P");

        //------运单/发票格式、样式节点 start-----------
        OutputImageProperties outputImageProperties = new OutputImageProperties();
        //运单格式
        outputImageProperties.setEncodingFormat("pdf");
        //运单打印精度
        outputImageProperties.setPrinterDPI(200);
        ImageOptions imageOptions = new ImageOptions();
        //单据类型
        imageOptions.setTypeCode("label");
        //单据样式
        imageOptions.setTemplateName("ECOM26_84_A4_001");
        //是否在运单上隐藏DHL账号
        imageOptions.setHideAccountNumber(false);
        //运单底联的份数 从固定值中选取
        imageOptions.setNumberOfCopies(1);
        //清关发票类型
        imageOptions.setInvoiceType("commercial");
        //清关发票的语言
        imageOptions.setLanguageCode("eng");
        //是否在运单上打印DHL logo
        imageOptions.setRenderDHLLogo(true);
        //该字段可用于在运单转运联上备注快件信息
        imageOptions.setLabelCustomerDataText("该字段可用于在运单转运联上备注快件信息");
        imageOptions.setLabelFreeText("该字段可用于在运单转运联上备注快件信息");
        outputImageProperties.setImageOptions(Collections.singletonList(imageOptions));

        //客户条形码节点
        CustomerBarcodeParam customerBarcodeParam = new CustomerBarcodeParam();
        //条形码
        customerBarcodeParam.setContent("barcode");
        //条形码下方文字
        customerBarcodeParam.setTextBelowBarcode("barcodeText");
        //条形码类型
        customerBarcodeParam.setSymbologyCode("93");
        outputImageProperties.setCustomerBarcodes(Collections.singletonList(customerBarcodeParam));

        //客户商标节点
        CustomerLogoParam logoParam = new CustomerLogoParam();
        //客户商标影像的类型
        logoParam.setFileFormat("GIF");
        //客户商标的Base64编码流
        logoParam.setFileFormat("base64");
        outputImageProperties.setCustomerLogos(Collections.singletonList(logoParam));


        //拆分成三份：运单转运联，底联，以及由收据+发票组成的PDF文件
        outputImageProperties.setSplitTransportAndWaybillDocLabels(true);
        //不拆分，运单转运联，底联，Receipt以及发票集中在一个PDF文件下
        outputImageProperties.setAllDocumentsInOneImage(true);
        //适用于一票多件时，拆分为每个单独的Transport Label以及单独的运单底联
        outputImageProperties.setSplitDocumentsByPages(true);
        ///拆分成三份：运单（转运联+底联），收据以及发票
        outputImageProperties.setSplitInvoiceAndReceipt(true);

        requestParam.setOutputImageProperties(outputImageProperties);
        //------运单/发票格式、样式节点 end-----------

        //-----------------格外的快件信息节点 start --------------
        AdditionalInformation additionalInformation = new AdditionalInformation();
        additionalInformation.setTypeCode("pickupDetails");
        additionalInformation.setRequested(true);
        requestParam.setGetAdditionalInformation(Collections.singletonList(additionalInformation));
        //-----------------格外的快件信息节点 end-----------------

        //------------------预计派送日期 start-------------
        EstimatedDeliveryDate estimatedDeliveryDate = new EstimatedDeliveryDate();
        estimatedDeliveryDate.setRequested(true);
        estimatedDeliveryDate.setTypeCode("QDDC");
        requestParam.setEstimatedDeliveryDate(estimatedDeliveryDate);
        //------------------预计派送日期 end -------------

        //-----------------运费及税金支付账号节点 start-----------
        Account account = new Account();
        //DHL账号
        account.setNumber("DHL账号");
        //DHL账号列表
        account.setTypeCode("shipper");
        requestParam.setAccounts(Collections.singletonList(account));
        //-----------------运费及税金支付账号节点 end-------------

        //---------------快件参考信息节点 start------------------
        CustomerReference customerReference = new CustomerReference();
        //快件参考信息类型
        customerReference.setTypeCode("CU");
        //快件参考信息内容
        customerReference.setValue("AAA");
        requestParam.setCustomerReferences(Collections.singletonList(customerReference));
        //---------------快件参考信息节点 end-------------------

        return requestParam;
    }
}
