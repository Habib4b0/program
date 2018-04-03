package com.stpl.gtn.gtn2o.hierarchyroutebuilder.querygenerator.bean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.bean.GtnFrameworkQueryGeneratorBean;
import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.querygenerator.GtnFrameworkOperatorType;

@Service
@Scope(value = "prototype")
public class HierarchySitiationFilterBean {

	private String leftColumn;
	private String rightColumn;
	private String operationType;
	private int hierarchySituationTableid;

	public HierarchySitiationFilterBean() {
		super();
	}

	public String getLeftColumn() {
		return leftColumn;
	}

	public void setLeftColumn(String leftColumn) {
		this.leftColumn = leftColumn;
	}

	public String getRightColumn() {
		return rightColumn;
	}

	public void setRightColumn(String rightColumn) {
		this.rightColumn = rightColumn;
	}

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	public int getHierarchySituationTableid() {
		return hierarchySituationTableid;
	}

	public void setHierarchySituationTableid(int hierarchySituationTableid) {
		this.hierarchySituationTableid = hierarchySituationTableid;
	}

	public void addWhereClause(GtnFrameworkQueryGeneratorBean queryBean) {
		queryBean.addWhereClauseBean(leftColumn, rightColumn, GtnFrameworkOperatorType.valueOf(operationType),
				GtnFrameworkDataType.NULL_ALLOWED, null);

	}

}
