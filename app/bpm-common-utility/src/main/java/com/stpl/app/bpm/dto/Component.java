package com.stpl.app.bpm.dto;

import java.util.LinkedHashSet;
import java.util.Set;

public class Component {

	private String category;
	private String screenName;
	private String componentName;
	private String tableName;
	private String columnName;
	private String methodology;
	private String salesOrUnits;
	private Set<String> components;
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getComponentName() {
		return componentName;
	}
	public void setComponentName(String componentName) {
		this.componentName = componentName;
		if(components==null){
			components = new LinkedHashSet<String>();
		}
		components.add(componentName);
	}
	
	public Set<String> getComponentList(){
		return components;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getMethodology() {
		return methodology;
	}
	public void setMethodology(String methodology) {
		this.methodology = methodology;
	}
	public String getSalesOrUnits() {
		return salesOrUnits;
	}
	public void setSalesOrUnits(String salesOrUnits) {
		this.salesOrUnits = salesOrUnits;
	}
	public String getScreenName() {
		return screenName;
	}
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	
		
}
