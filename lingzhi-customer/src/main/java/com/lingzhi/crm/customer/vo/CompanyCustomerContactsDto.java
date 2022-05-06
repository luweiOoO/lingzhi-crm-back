package com.lingzhi.crm.customer.vo;

import com.lingzhi.crm.customer.domain.CustomerCompanyContacts;
import com.lingzhi.crm.customer.domain.CustomerContactsPhone;
import lombok.Data;

import java.util.List;

@Data
public class CompanyCustomerContactsDto extends CustomerCompanyContacts {

    /**
     * 联系人电话列表
     */
    private List<CustomerContactsPhone> contactsPhoneList;
}
