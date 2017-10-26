package com.stpl.app.service.persistence;

public interface CompanyTradeClassFinder {
    public java.util.List getTradeClassDetails(
        java.lang.String companySystemId, java.lang.String tradeClassSysId);

    public java.util.List getCompanyparentDetails(
        java.lang.String companySystemId, java.lang.String parentSysId);
}
