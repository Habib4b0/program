package com.stpl.gtn.gtn2o.ws.module.contractheader.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.ws.contract.bean.GtnWsContractMasterBean;
import com.stpl.gtn.gtn2o.ws.entity.contract.ContractMaster;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.contract.GtnWsContractHeaderResponse;

/**
 *
 * @author Karthikeyan.Subraman
 */
@Service()
public class GtnWsContractHeaderValidationService {
    public GtnWsContractHeaderValidationService(){
        /**
         * empty constructor
         */
    }

	@Autowired
	private org.hibernate.SessionFactory sessionFactory;

	public synchronized org.hibernate.SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public synchronized void setSessionFactory(org.hibernate.SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	public void checkContractIdNameExist(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnUIFrameworkWebserviceResponse gtnResponse) throws GtnFrameworkGeneralException {

		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			boolean isContractExist = false;
			boolean isContractNoExist = false;

			GtnWsContractMasterBean itemMasterBean = gtnWsRequest.getGtnWsContractHeaderRequest()
					.getGtnWsContractMasterBean();
			int systemId = itemMasterBean.getContractMasterSid();
			Criteria cr = session.createCriteria(ContractMaster.class)
					.add(Restrictions.eq("contractId", itemMasterBean.getContractId()))
					.add(Restrictions.ne("inboundStatus", 'D'));
			if (systemId > 0) {
				cr.add(Restrictions.ne("contractMasterSid", systemId));
			}
			ProjectionList proj = Projections.projectionList();
			proj.add(Projections.countDistinct("contractId"));
			cr.setProjection(proj);
			List<Long> results = cr.list();
			if (results != null && !results.isEmpty()) {
				isContractExist = (long) results.get(0) > 0;
			}
			Criteria cr2 = session.createCriteria(ContractMaster.class)
					.add(Restrictions.eq("contractNo", itemMasterBean.getContractNo()))
					.add(Restrictions.ne("inboundStatus", 'D'));
			if (systemId > 0) {
				cr2.add(Restrictions.ne("contractMasterSid", systemId));
			}
			ProjectionList proj2 = Projections.projectionList();
			proj2.add(Projections.countDistinct("contractNo"));
			cr2.setProjection(proj2);

			List<Long> results2 = cr2.list();
			if (results2 != null && !results2.isEmpty()) {
				isContractNoExist = (long) results2.get(0) > 0;
			}

			tx.commit();
			GtnWsContractHeaderResponse imResponse = new GtnWsContractHeaderResponse();
			imResponse.setContractIdDuplicate(isContractExist);
			imResponse.setContractNoDuplicate(isContractNoExist);
			gtnResponse.setGtnWsContractHeaderResponse(imResponse);
		} catch (Exception e) {
			tx.rollback();
			throw new GtnFrameworkGeneralException("Exception in checkContractIdNameExist ", e);
		} finally {
			session.close();
		}

	}

}
