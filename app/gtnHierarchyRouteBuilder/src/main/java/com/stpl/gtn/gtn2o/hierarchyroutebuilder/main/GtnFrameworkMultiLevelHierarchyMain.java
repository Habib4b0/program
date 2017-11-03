package com.stpl.gtn.gtn2o.hierarchyroutebuilder.main;

import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.hierarchyroutebuilder.service.GtnFrameworkHierarchyService;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.service.GtnFrameworkHierarchyServiceImpl;

public class GtnFrameworkMultiLevelHierarchyMain {
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		List<Integer> entityList = Arrays.asList(101, 102, 103);
		GtnFrameworkHierarchyService hierarchyService = new GtnFrameworkHierarchyServiceImpl();
		String query = hierarchyService.creatQueryForMultiLevelHierarchy(entityList);
		System.out.println(query);

	}
}
