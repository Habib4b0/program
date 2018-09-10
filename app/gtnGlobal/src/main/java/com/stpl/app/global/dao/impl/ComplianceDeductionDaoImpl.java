/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.dao.impl;

import com.stpl.app.model.CdrModel;
import com.stpl.app.service.CdrModelLocalServiceUtil;
import com.stpl.domain.global.ComplianceDeduction.ComplianceDeductionDao;
import com.liferay.portal.kernel.exception.PortalException;

/**
 *
 * @author ahalya
 */
public class ComplianceDeductionDaoImpl implements  ComplianceDeductionDao{

    @Override
    public CdrModel getCdrModelBySystemId(int crModelId) throws PortalException {

   return CdrModelLocalServiceUtil.getCdrModel(crModelId);
    
    }

    @Override
    public CdrModel updateCdrModel(CdrModel cdrModel) throws PortalException {
      return CdrModelLocalServiceUtil.updateCdrModel(cdrModel);
    }

    @Override
    public CdrModel createCdrModel(int crModelId) throws PortalException {

     return CdrModelLocalServiceUtil.createCdrModel(crModelId);
    }

    @Override
    public CdrModel deleteCdrModel(int crModelId) throws PortalException {

     return CdrModelLocalServiceUtil.deleteCdrModel(crModelId);
    }
    
}
