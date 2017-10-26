/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.domain.global.DeductionCalendar;

import com.stpl.app.model.DeductionCalendarMaster;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;

/**
 *
 * @author shyam.d
 */
public interface DeductionCalendarDao {
    
    DeductionCalendarMaster saveDeductionCalendarMaster(DeductionCalendarMaster deductionCalendarMaster)
			throws SystemException, PortalException;
    
}
