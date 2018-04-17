package com.stpl.gtn.gtn2o.ui.framework.component.grid.bean;

public class QueryBean {

    String countQuery;
    String dataQuery;
    String leftDataQuery;
    Object[] dataQueryInputs;
    Object[] leftDataQueryInputs;
   Object[] countQueryInputs;
    public String getLeftDataQuery() {
        return leftDataQuery;
    }

    public void setLeftDataQuery(String leftDataQuery) {
        this.leftDataQuery = leftDataQuery;
    }

    public Object[] getLeftDataQueryInputs() {
        return leftDataQueryInputs;
    }

    public void setLeftDataQueryInputs(Object[] leftDataQueryInputs) {
        this.leftDataQueryInputs = leftDataQueryInputs;
    }
 

    public QueryBean(String countQuery, String dataQuery, Object[] dataQueryInputs, Object[] countQueryInputs) {
        super();
        this.countQuery = countQuery;
        this.dataQuery = dataQuery;
        this.dataQueryInputs = dataQueryInputs;
        this.countQueryInputs = countQueryInputs;
    }
    

    public String getCountQuery() {
        return countQuery;
    }

    public void setCountQuery(String countQuery) {
        this.countQuery = countQuery;
    }

    public String getDataQuery() {
        return dataQuery;
    }

    public void setDataQuery(String dataQuery) {
        this.dataQuery = dataQuery;
    }

    public Object[] getDataQueryInputs() {
        return dataQueryInputs;
    }

    public void setDataQueryInputs(Object[] dataQueryInputs) {
        this.dataQueryInputs = dataQueryInputs;
    }

    public Object[] getCountQueryInputs() {
        return countQueryInputs;
    }

    public void setCountQueryInputs(Object[] countQueryInputs) {
        this.countQueryInputs = countQueryInputs;
    }

}
