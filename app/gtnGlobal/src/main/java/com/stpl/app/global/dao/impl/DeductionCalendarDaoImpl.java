/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.dao.impl;

import com.stpl.app.model.DeductionCalendarMaster;
import com.stpl.app.service.DeductionCalendarMasterLocalServiceUtil;
import com.stpl.domain.global.DeductionCalendar.DeductionCalendarDao;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;

/**
 *
 * @author shyam.d
 */
public class DeductionCalendarDaoImpl implements DeductionCalendarDao {

    @Override
    public DeductionCalendarMaster saveDeductionCalendarMaster(DeductionCalendarMaster deductionCalendarMaster) throws SystemException, PortalException {
        return DeductionCalendarMasterLocalServiceUtil.addDeductionCalendarMaster(deductionCalendarMaster);
    }
    
}
