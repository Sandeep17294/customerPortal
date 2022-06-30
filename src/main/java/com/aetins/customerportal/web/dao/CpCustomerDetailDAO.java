package com.aetins.customerportal.web.dao;

import com.aetins.customerportal.web.common.IGenericDao;
import com.aetins.customerportal.web.entity.CpCustomerDetail;

/**
 * @author satendra
 *
 */
public interface CpCustomerDetailDAO  extends IGenericDao<CpCustomerDetail, Long>{
    /**
     * @param cpCustomerDetail
     */
    public void saveCustomerDetail(CpCustomerDetail cpCustomerDetail);
    
    /**
     * find by custRefNo
     * @param custRefNo
     * @return
     */
    public CpCustomerDetail findByCustRef(long custRefNo);
}
