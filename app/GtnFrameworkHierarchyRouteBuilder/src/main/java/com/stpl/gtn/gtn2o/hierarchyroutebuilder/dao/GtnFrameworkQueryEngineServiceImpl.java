package com.stpl.gtn.gtn2o.hierarchyroutebuilder.dao;

import java.util.List;

import com.stpl.app.service.HelperTableLocalServiceUtil;

public class GtnFrameworkQueryEngineServiceImpl implements GtnFrameworkQueryEngineService {

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> executeSelectQuery(String query) {

		 return HelperTableLocalServiceUtil.executeSelectQuery(query);
	}

}
