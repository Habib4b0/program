/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.adjustmentdetails.service;

import static com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean.logger;
import com.stpl.gtn.gtn2o.ws.entity.adjustmentDetails.ArmViewMaster;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.adjustmentdetails.GtnWsAdjusmentDetailsRequest;
import com.stpl.gtn.gtn2o.ws.request.adjustmentdetails.GtnWsAdjustmentDetailsSaveViewMasterRequest;
import com.stpl.gtn.gtn2o.ws.request.transaction.GtnWsTransactionRequest;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GtnWsAdjustmentDetailsSaveViewService {

    @Autowired
    private org.hibernate.SessionFactory sessionFactory;

    @Autowired
    private GtnWsSqlService gtnWsSqlService;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public org.hibernate.SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public int saveCustViewMaster(GtnUIFrameworkWebserviceRequest gtnWsRequest) {
        GtnWsAdjustmentDetailsSaveViewMasterRequest request = gtnWsRequest.getGtnWsAdjustmentDetailsSaveViewMasterRequest();
        Session session = getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        int customViewMasterSid = 0;
        try {
            if (request.getArmViewMasterSid() == null || request.getArmViewMasterSid() == 0) {
                ArmViewMaster master = new ArmViewMaster();
                master.setViewName(request.getViewName());
                master.setViewType(request.getViewName());
                master.setCreatedDate(new Date());
                master.setCreatedBy(gtnWsRequest.getGtnWsGeneralRequest().getUserId());
                customViewMasterSid = (int) session.save(master);
            } else {
                ArmViewMaster master = session.get(ArmViewMaster.class, request.getArmViewMasterSid());
                master.setViewName(request.getViewName());
                master.setViewType(request.getViewName());
                master.setModifiedDate(new Date());
                master.setModifiedBy(gtnWsRequest.getGtnWsGeneralRequest().getUserId());
                session.update(master);
                customViewMasterSid = request.getArmViewMasterSid();
            }
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            logger.error(e.getMessage(), e);
        } finally {
            session.close();
        }
        return customViewMasterSid;
    }

    public String saveCustViewDetails(GtnUIFrameworkWebserviceRequest gtnWsRequest, int armAdjustmentMasterSid) {
        List input = new ArrayList();
        GtnWsAdjusmentDetailsRequest request = gtnWsRequest.getGtnWsAdjusmentDetailsRequest();
        input.add(armAdjustmentMasterSid);
        input.add(request.getAdjustmentType());
        input.add(request.getAdjustmentLevel());
        input.add(request.getGlCompany());
        input.add(request.getBusinessUnit());
        input.add(request.getWorkFlowId());
        input.add(request.getWorkFlowName());
        input.add(request.getCustomerNo());
        input.add(request.getItemNo());
        input.add(request.getDeductionLevel());
        if (request.getCreatedDate() != null) {
            input.add(simpleDateFormat.format(request.getCreatedDate()));
        } else {
            input.add(request.getCreatedDate());
        }
        input.add(request.getCustomerName());
        input.add(request.getItemName());
        input.add(request.getDeductionValue());
        if (request.getGlDate() != null) {
            input.add(simpleDateFormat.format(request.getGlDate()));
        } else {
            input.add(request.getGlDate());
        }
        input.add(request.getOriginalBatchId());
        input.add(request.getBrandName());
        input.add(request.getRedemptionPeriodStartDate());
        input.add(request.getPostingIndicator());
        input.add(request.getTransactionLevel());
        input.add(request.getAccountCategory());
        input.add(request.getAccountType());
        input.add(request.getAccount());
        input.add(request.getRedemptionPeriodEndDate());
        return gtnWsSqlService.getNullReplacedQuery(input, "insertAdjustmentDetails");
    }

    public String updateReprocessDetails(GtnUIFrameworkWebserviceRequest gtnWsRequest) {
        List input = new ArrayList();
        GtnWsTransactionRequest request = gtnWsRequest.getGtnWsTransactionRequest();
        input.add(StringUtils.join(request.getReprocessIds(), ","));
        input.add(gtnWsRequest.getGtnWsGeneralRequest().getUserId());
        input.add(gtnWsRequest.getGtnWsGeneralRequest().getSessionId());
        return gtnWsSqlService.getNullReplacedQuery(input, "updateReprocessFlag");
    }
}
