package com.stpl.gtn.gtn2o.hierarchyroutebuilder.dao;

import java.util.List;

public interface GtnFrameworkQueryEngineService {

	@SuppressWarnings("rawtypes")
	List executeSelectQuery(String query);

}
