package com.aetins.customerportal.web.dao;


import java.util.List;

import com.aetins.customerportal.web.common.IGenericDao;
import com.aetins.customerportal.web.entity.CpUserInfo;

/**
 * @author satendra
 *
 */
public interface CpUserInfoDAO extends IGenericDao<CpUserInfo, Long> {
    /**
     * @param cpUserInfo
     */
    public void saveUserInfo(CpUserInfo cpUserInfo);

    
    /**@author viswa karthick
     * @param userName
     * @return CpUserInfo
     */
    public CpUserInfo getCpUserInfo(String userName);
    
    /**
     * <i>fetch cpUserInfoList based up on userGroup</i>
     * @author avinash
     * @param userGroup
     * @return cpusergrouplist
     * 
     */
    public List<CpUserInfo> getCpUsersByGroup(String userGroup);
   
}
