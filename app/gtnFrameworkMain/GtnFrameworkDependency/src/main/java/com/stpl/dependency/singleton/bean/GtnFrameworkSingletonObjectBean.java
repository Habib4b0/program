package com.stpl.dependency.singleton.bean;

import java.util.List;

public class GtnFrameworkSingletonObjectBean {

	private static GtnFrameworkSingletonObjectBean instance = null;
	
	private List<Object[]> periodConfigResultList;
	
	private List<Object[]> validateServiceRegistryRegisteredWsResultList;
	
	private List<Object[]> relationshipLevelValuesResultList;
	
	private GtnFrameworkSingletonObjectBean(){
		
	}
	
	public static GtnFrameworkSingletonObjectBean getInstance(){
		if(instance == null){
			instance = new GtnFrameworkSingletonObjectBean();
		}
		return instance;
	}

	public List<Object[]> getPeriodConfigResultList() {
		return periodConfigResultList;
	}

	public void setPeriodConfigResultList(List<Object[]> periodConfigResultList) {
		this.periodConfigResultList = periodConfigResultList;
	}

	public List<Object[]> getValidateServiceRegistryRegisteredWsResultList() {
		return validateServiceRegistryRegisteredWsResultList;
	}

	public void setValidateServiceRegistryRegisteredWsResultList(
			List<Object[]> validateServiceRegistryRegisteredWsResultList) {
		this.validateServiceRegistryRegisteredWsResultList = validateServiceRegistryRegisteredWsResultList;
	}

	public List<Object[]> getRelationshipLevelValuesResultList() {
		return relationshipLevelValuesResultList;
	}

	public void setRelationshipLevelValuesResultList(List<Object[]> relationshipLevelValuesResultList) {
		this.relationshipLevelValuesResultList = relationshipLevelValuesResultList;
	}
	
}
