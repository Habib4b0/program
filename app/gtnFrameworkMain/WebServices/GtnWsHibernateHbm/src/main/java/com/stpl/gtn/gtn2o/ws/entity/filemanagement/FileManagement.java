package com.stpl.gtn.gtn2o.ws.entity.filemanagement;
// Generated Jun 28, 2017 5:44:21 PM by Hibernate Tools 4.3.1


import com.stpl.gtn.gtn2o.ws.entity.HelperTable;
import com.stpl.gtn.gtn2o.ws.entity.companymaster.CompanyMaster;
import java.util.Date;


public class FileManagement  implements java.io.Serializable {


     private int fileManagementSid;
     private String version;
     private CompanyMaster companyMaster;
     private HelperTable helperTable;
     private String forecastName;
     private String forecastSource;
     private String fileSource;
     private Date fromPeriod;
     private Date toPeriod;
     private int createdBy;
     private Date createdDate;
     private int modifiedBy;
     private Date modifiedDate;
     private int versionNo;
     private Integer fileType;
     private Integer company;

    public FileManagement() {
    }

	
    public FileManagement(int fileManagementSid, int createdBy, Date createdDate, int modifiedBy, Date modifiedDate, int versionNo) {
        this.fileManagementSid = fileManagementSid;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.modifiedBy = modifiedBy;
        this.modifiedDate = modifiedDate;
        this.versionNo = versionNo;
    }
    public FileManagement(int fileManagementSid, CompanyMaster companyMaster, HelperTable helperTable, String forecastName, String forecastSource, String fileSource, Date fromPeriod, Date toPeriod, int createdBy, Date createdDate, int modifiedBy, Date modifiedDate, int versionNo, Integer fileType, Integer company) {
       this.fileManagementSid = fileManagementSid;
       this.companyMaster = companyMaster;
       this.helperTable = helperTable;
       this.forecastName = forecastName;
       this.forecastSource = forecastSource;
       this.fileSource = fileSource;
       this.fromPeriod = fromPeriod;
       this.toPeriod = toPeriod;
       this.createdBy = createdBy;
       this.createdDate = createdDate;
       this.modifiedBy = modifiedBy;
       this.modifiedDate = modifiedDate;
       this.versionNo = versionNo;
       this.fileType = fileType;
       this.company = company;
    }
   
    public int getFileManagementSid() {
        return this.fileManagementSid;
    }
    
    public void setFileManagementSid(int fileManagementSid) {
        this.fileManagementSid = fileManagementSid;
    }
    public String getVersion() {
        return this.version;
    }
    
    public void setVersion(String version) {
        this.version = version;
    }
    public CompanyMaster getCompanyMaster() {
        return this.companyMaster;
    }
    
    public void setCompanyMaster(CompanyMaster companyMaster) {
        this.companyMaster = companyMaster;
    }
    public HelperTable getHelperTable() {
        return this.helperTable;
    }
    
    public void setHelperTable(HelperTable helperTable) {
        this.helperTable = helperTable;
    }
    public String getForecastName() {
        return this.forecastName;
    }
    
    public void setForecastName(String forecastName) {
        this.forecastName = forecastName;
    }
    public String getForecastSource() {
        return this.forecastSource;
    }
    
    public void setForecastSource(String forecastSource) {
        this.forecastSource = forecastSource;
    }
    public String getFileSource() {
        return this.fileSource;
    }
    
    public void setFileSource(String fileSource) {
        this.fileSource = fileSource;
    }
    public Date getFromPeriod() {
        return this.fromPeriod;
    }
    
    public void setFromPeriod(Date fromPeriod) {
        this.fromPeriod = fromPeriod;
    }
    public Date getToPeriod() {
        return this.toPeriod;
    }
    
    public void setToPeriod(Date toPeriod) {
        this.toPeriod = toPeriod;
    }
    public int getCreatedBy() {
        return this.createdBy;
    }
    
    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }
    public Date getCreatedDate() {
        return this.createdDate;
    }
    
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    public int getModifiedBy() {
        return this.modifiedBy;
    }
    
    public void setModifiedBy(int modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
    public Date getModifiedDate() {
        return this.modifiedDate;
    }
    
    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
    public int getVersionNo() {
        return this.versionNo;
    }
    
    public void setVersionNo(int versionNo) {
        this.versionNo = versionNo;
    }
    public Integer getFileType() {
        return this.fileType;
    }
    
    public void setFileType(Integer fileType) {
        this.fileType = fileType;
    }
    public Integer getCompany() {
        return this.company;
    }
    
    public void setCompany(Integer company) {
        this.company = company;
    }




}


