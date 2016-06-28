/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.domain.global.ComplianceDeduction;

import com.stpl.app.model.CdrModel;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;

/**
 *
 * @author ahalya
 */
public interface ComplianceDeductionDao {
    
    CdrModel getCdrModelBySystemId(int crModelId)
			throws SystemException, PortalException;
    CdrModel updateCdrModel(CdrModel cdrModel)
			throws SystemException, PortalException;
    
 CdrModel createCdrModel(int crModelId)
         throws SystemException, PortalException;
    
 CdrModel deleteCdrModel(int crModelId)
         throws SystemException, PortalException;
    
}
