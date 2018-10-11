/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.domain.global.compliancededuction;

import com.stpl.app.model.CdrModel;
import com.liferay.portal.kernel.exception.PortalException;

/**
 *
 * @author ahalya
 */
public interface ComplianceDeductionDao {
    
    CdrModel getCdrModelBySystemId(int crModelId)
			throws  PortalException;
    CdrModel updateCdrModel(CdrModel cdrModel)
			throws  PortalException;
    
 CdrModel createCdrModel(int crModelId)
         throws  PortalException;
    
 CdrModel deleteCdrModel(int crModelId)
         throws  PortalException;
    
}
