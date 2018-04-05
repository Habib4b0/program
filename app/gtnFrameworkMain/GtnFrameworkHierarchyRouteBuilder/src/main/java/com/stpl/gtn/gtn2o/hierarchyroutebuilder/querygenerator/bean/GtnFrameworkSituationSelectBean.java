package com.stpl.gtn.gtn2o.hierarchyroutebuilder.querygenerator.bean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.bean.GtnFrameworkQueryGeneratorBean;

@Service
@Scope(value = "prototype")
public class GtnFrameworkSituationSelectBean {


	private String columnName;
	private String aliasName;

	public GtnFrameworkSituationSelectBean() {
		super();
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getAliasName() {
		return aliasName;
	}

	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}

	public void addSelectClause(GtnFrameworkQueryGeneratorBean queryBean) {
		queryBean.addSelectClauseBean(null, aliasName, Boolean.FALSE,
				columnName);
	}

}
