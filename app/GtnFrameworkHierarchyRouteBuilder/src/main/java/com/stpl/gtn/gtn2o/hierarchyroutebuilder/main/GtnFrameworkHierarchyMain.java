package com.stpl.gtn.gtn2o.hierarchyroutebuilder.main;

import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkEntityBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkRouteBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.service.GtnFrameworkHierarchyService;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.service.GtnFrameworkHierarchyServiceImpl;

public class GtnFrameworkHierarchyMain {
	public static void main(String[] args) {

		GtnFrameworkHierarchyService hierarchyService = new GtnFrameworkHierarchyServiceImpl();
		GtnFrameworkEntityBean companyEntity = new GtnFrameworkEntityBean(1, "Company", 4);
		GtnFrameworkEntityBean contractEntity = new GtnFrameworkEntityBean(2, "Contract", 4);
		GtnFrameworkRouteBean routeBean = hierarchyService.getRoutePath(companyEntity.getEntityMasterSid(),
				contractEntity.getEntityMasterSid());
		String query = hierarchyService.createQuery(routeBean);
		System.out.println(routeBean.getPathList());
		System.out.println(query);

	}
}
