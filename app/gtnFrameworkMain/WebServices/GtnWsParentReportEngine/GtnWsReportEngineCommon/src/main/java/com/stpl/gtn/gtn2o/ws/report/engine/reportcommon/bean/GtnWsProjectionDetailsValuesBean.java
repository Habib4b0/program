package com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.bean;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GtnWsProjectionDetailsValuesBean implements Serializable {

	private int periodSid;
	private int quarter;
	private int semiAnnual;
	private int year;
	private Double salesActuals;
	private Double salesProjection;
	private Double salesUnitsActuals;
	private Double salesUnitsProjection;
	private List<GtnWsDiscountBean> discountBean;
	private Double exfactoryActuals;
	private Double exfactoryProjection;

	public int getPeriodSid() {
		return periodSid;
	}

	public void setPeriodSid(int periodSid) {
		this.periodSid = periodSid;
	}

	public int getQuarter() {
		return quarter;
	}

	public void setQuarter(int quarter) {
		this.quarter = quarter;
	}

	public int getSemiAnnual() {
		return semiAnnual;
	}

	public void setSemiAnnual(int semiAnnual) {
		this.semiAnnual = semiAnnual;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Double getSalesActuals() {
		return salesActuals;
	}

	public void setSalesActuals(Double salesActuals) {
		this.salesActuals = salesActuals;
	}

	public Double getSalesProjection() {
		return salesProjection;
	}

	public void setSalesProjection(Double salesProjection) {
		this.salesProjection = salesProjection;
	}

	public Double getSalesUnitsProjection() {
		return salesUnitsProjection;
	}

	public void setSalesUnitsProjection(Double salesUnitsProjection) {
		this.salesUnitsProjection = salesUnitsProjection;
	}

	public List<GtnWsDiscountBean> getDiscountBean() {
		return discountBean;
	}

	public void addDiscountBean(GtnWsDiscountBean discountBean) {
		if (this.discountBean == null) {
			this.discountBean = new ArrayList<>();
		}
		this.discountBean.add(discountBean);
	}

	public Double getSalesUnitsActuals() {
		return salesUnitsActuals;
	}

	public void setSalesUnitsActuals(Double salesUnitsActuals) {
		this.salesUnitsActuals = salesUnitsActuals;
	}

	public Double getExfactoryActuals() {
		return exfactoryActuals;
	}

	public void setExfactoryActuals(Double exfactoryActuals) {
		this.exfactoryActuals = exfactoryActuals;
	}

	public Double getExfactoryProjection() {
		return exfactoryProjection;
	}

	public void setExfactoryProjection(Double exfactoryProjection) {
		this.exfactoryProjection = exfactoryProjection;
	}

	private void writeObject(java.io.ObjectOutputStream s) throws java.io.IOException {
		s.defaultWriteObject();
	}

	// Dont delete. this Method is called during Serialization
	private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
		ois.defaultReadObject();
	}

}
