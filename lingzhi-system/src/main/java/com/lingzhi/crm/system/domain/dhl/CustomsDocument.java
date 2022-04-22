package com.lingzhi.crm.system.domain.dhl;

import lombok.Data;

/**
 * 清关单据清单节点
 * "该节点用于录入快件随附的清关单据类型和清关单据ID。
 * 该节点还包含多个下级字段，详见如下说明："
 */
@Data
public class CustomsDocument {

    /**
     * 清关单据类型
     * "该字段的常用可选值为（逗号左边是可选值，逗号右边是可选值的含义）：
     * - 972, T2LF Dispense Paiement Droits
     * - AHC, Analysis and Health Certificate
     * - ATA, ATA Carnet
     * - ATR, Preference Certificate ATR
     * - CHD, CHED-D (Common Health Entry Document for Feed and Food of Non-Animal Origin)
     * - CHP, CHED-P (Common Health Entry Document for Products)
     * - CIT, CITES Certificate
     * - COO, Generic Certificate of Origin
     * - DEX, Duty Exemption Certificate
     * - EU1, Preference Certificate EUR1
     * - EU2, Preferential Declaration of Origin on the Invoice - EUR2
     * - EUS, End Use Authorization
     * - FMA, Certificate of Origin (Form A)
     * - PHY, Phytosanitary Certificate
     * - VET, Veterinary Entry Document
     * - VEX, VAT Exemption Certificate
     * - CRL, Control Document
     * - CSD, Consolidated Customs Entry
     * - PPY, Proof Of Payment
     * - CI2, Export-only Invoice
     * - CIV, Customs Invoice Value
     * - DOV, Invoice
     * - INV, Commercial Invoice
     * - PFI, Pro-Forma
     * - ALC, Agricultural License
     * - HLC, Health Products Regulatory Authority (HPRA) Licensing Requirements
     * - JLC, Justice License
     * - LIC, Specific Export Licenses
     * - LNP, License or Permit
     * - PLI, Permits & Licenses
     * - DLI, Driver's License
     * - NID, National Identity Card
     * - PAS, Passport
     * - CHA, Power of Attorney
     * - CPA, Consignee Power of Attorney
     * - POA, Power of Attorney (Customer-based)
     * - BEX, Branch Letter of Exemption
     * - DGD, Dangerous Goods Declaration
     * - IPA, Intellectual Property Authorization
     * - T2M, T2M Transport Accompanying Document
     * - TAD, TAD Transport Accompanying Document T1
     * - TCS, Transportation Charges Statement
     * - ROD, Receipt on Delivery
     * - EXL, DCE Export of Customs Data
     * - HWB, House Waybill"
     */
    private String typeCode;

    /**
     * 清关单据的ID
     */
    private String value;
}
