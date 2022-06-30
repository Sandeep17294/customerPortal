package com.aetins.customerportal.web.dao;

import java.util.List;

import com.aetins.customerportal.web.common.IGenericDao;
import com.aetins.customerportal.web.entity.CpSecurityImgMaster;

/**
 * @author satendra
 *
 */
public interface CpSecurityImgMasterDAO  extends IGenericDao<CpSecurityImgMaster, Long>{
    /**
     * @return
     */
    public List<CpSecurityImgMaster> listSecurityImgMaster();



}
