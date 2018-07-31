/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;
import com.stpl.gtn.gtn2o.ws.entity.HelperTable;
import com.stpl.gtn.gtn2o.ws.entity.projectionmaster.ProjectionMaster;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import org.springframework.stereotype.Service;

/**
 *
 * @author STPL
 */
@Service
public class GtnWsReturnsDatabaseService {

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsReturnsDatabaseService.class);

        
	public GtnWsReturnsDatabaseService() {
		super();
	}

	@SuppressWarnings("unchecked")
	public HelperTable getHelperTableByDescription(String description, String listName, Session session) {
		Criteria cr = session.createCriteria(HelperTable.class).add(Restrictions.eq("description", description))
				.add(Restrictions.eq("listName", listName));
		List<HelperTable> results = cr.list();
		if (results != null && !results.isEmpty()) {
			return results.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public ProjectionMaster getProjectionMaster(int projectionId, Session session) {
		Criteria cr = session.createCriteria(ProjectionMaster.class)
				.add(Restrictions.eq("projectionMasterSid", projectionId));
		List<ProjectionMaster> results = cr.list();
		if (results != null && !results.isEmpty()) {
			return results.get(0);
		}
		return null;
	}


}
