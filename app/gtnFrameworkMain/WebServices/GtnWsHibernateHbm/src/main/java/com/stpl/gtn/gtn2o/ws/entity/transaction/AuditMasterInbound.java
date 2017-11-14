package com.stpl.gtn.gtn2o.ws.entity.transaction;

import java.util.Date;

@SuppressWarnings("serial")
public class AuditMasterInbound implements java.io.Serializable {

	private int auditInboundSid;
	private String applicationProcess;
	private String fileName;
	private Integer sentRecordCount;
	private Integer receivedRecordCount;
	private Double sentRecordAmount;
	private String sentRecordAmountAttribute;
	private Double receivedRecordAmount;
	private String receivedRecordAmountAttr;
	private Integer invalidRecordCount;
	private Double invalidRecordAmount;
	private Double validRecordAmount;
	private Double discrepancyAmount;
	private Date interfaceRunStartDate;
	private Date interfaceRunEndDate;
	private Integer addCount;
	private Integer changeCount;
	private Integer deleteCount;
	private String status;
	private String unprocessedRecords;
	private String batchId;
	private String source;
	private String createdBy;
	private Date createdDate;
	private String modifiedBy;
	private Date modifiedDate;
	private Double units;
	private Double salesAmount;
	private Double deductionAmount;

	public AuditMasterInbound() {
	}

	public AuditMasterInbound(int auditInboundSid, String createdBy, Date createdDate, String modifiedBy, Date modifiedDate) {
		this.auditInboundSid = auditInboundSid;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
	}

	public AuditMasterInbound(int auditInboundSid, String applicationProcess, String fileName, Integer sentRecordCount,
			Integer receivedRecordCount, Double sentRecordAmount, String sentRecordAmountAttribute,
			Double receivedRecordAmount, String receivedRecordAmountAttr, Integer invalidRecordCount,
			Double invalidRecordAmount, Double validRecordAmount, Double discrepancyAmount, Date interfaceRunStartDate,
			Date interfaceRunEndDate, Integer addCount, Integer changeCount, Integer deleteCount, String status,
			String unprocessedRecords, String batchId, String source, String createdBy, Date createdDate, String modifiedBy,
			Date modifiedDate, Double units, Double salesAmount, Double deductionAmount) {
		this.auditInboundSid = auditInboundSid;
		this.applicationProcess = applicationProcess;
		this.fileName = fileName;
		this.sentRecordCount = sentRecordCount;
		this.receivedRecordCount = receivedRecordCount;
		this.sentRecordAmount = sentRecordAmount;
		this.sentRecordAmountAttribute = sentRecordAmountAttribute;
		this.receivedRecordAmount = receivedRecordAmount;
		this.receivedRecordAmountAttr = receivedRecordAmountAttr;
		this.invalidRecordCount = invalidRecordCount;
		this.invalidRecordAmount = invalidRecordAmount;
		this.validRecordAmount = validRecordAmount;
		this.discrepancyAmount = discrepancyAmount;
		this.interfaceRunStartDate = interfaceRunStartDate;
		this.interfaceRunEndDate = interfaceRunEndDate;
		this.addCount = addCount;
		this.changeCount = changeCount;
		this.deleteCount = deleteCount;
		this.status = status;
		this.unprocessedRecords = unprocessedRecords;
		this.batchId = batchId;
		this.source = source;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
		this.units = units;
		this.salesAmount = salesAmount;
		this.deductionAmount = deductionAmount;
	}

	public int getAuditInboundSid() {
		return this.auditInboundSid;
	}

	public void setAuditInboundSid(int auditInboundSid) {
		this.auditInboundSid = auditInboundSid;
	}

	public String getApplicationProcess() {
		return this.applicationProcess;
	}

	public void setApplicationProcess(String applicationProcess) {
		this.applicationProcess = applicationProcess;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Integer getSentRecordCount() {
		return this.sentRecordCount;
	}

	public void setSentRecordCount(Integer sentRecordCount) {
		this.sentRecordCount = sentRecordCount;
	}

	public Integer getReceivedRecordCount() {
		return this.receivedRecordCount;
	}

	public void setReceivedRecordCount(Integer receivedRecordCount) {
		this.receivedRecordCount = receivedRecordCount;
	}

	public Double getSentRecordAmount() {
		return this.sentRecordAmount;
	}

	public void setSentRecordAmount(Double sentRecordAmount) {
		this.sentRecordAmount = sentRecordAmount;
	}

	public String getSentRecordAmountAttribute() {
		return this.sentRecordAmountAttribute;
	}

	public void setSentRecordAmountAttribute(String sentRecordAmountAttribute) {
		this.sentRecordAmountAttribute = sentRecordAmountAttribute;
	}

	public Double getReceivedRecordAmount() {
		return this.receivedRecordAmount;
	}

	public void setReceivedRecordAmount(Double receivedRecordAmount) {
		this.receivedRecordAmount = receivedRecordAmount;
	}

	public String getReceivedRecordAmountAttr() {
		return this.receivedRecordAmountAttr;
	}

	public void setReceivedRecordAmountAttr(String receivedRecordAmountAttr) {
		this.receivedRecordAmountAttr = receivedRecordAmountAttr;
	}

	public Integer getInvalidRecordCount() {
		return this.invalidRecordCount;
	}

	public void setInvalidRecordCount(Integer invalidRecordCount) {
		this.invalidRecordCount = invalidRecordCount;
	}

	public Double getInvalidRecordAmount() {
		return this.invalidRecordAmount;
	}

	public void setInvalidRecordAmount(Double invalidRecordAmount) {
		this.invalidRecordAmount = invalidRecordAmount;
	}

	public Double getValidRecordAmount() {
		return this.validRecordAmount;
	}

	public void setValidRecordAmount(Double validRecordAmount) {
		this.validRecordAmount = validRecordAmount;
	}

	public Double getDiscrepancyAmount() {
		return this.discrepancyAmount;
	}

	public void setDiscrepancyAmount(Double discrepancyAmount) {
		this.discrepancyAmount = discrepancyAmount;
	}

	public Date getInterfaceRunStartDate() {
		return this.interfaceRunStartDate;
	}

	public void setInterfaceRunStartDate(Date interfaceRunStartDate) {
		this.interfaceRunStartDate = interfaceRunStartDate;
	}

	public Date getInterfaceRunEndDate() {
		return this.interfaceRunEndDate;
	}

	public void setInterfaceRunEndDate(Date interfaceRunEndDate) {
		this.interfaceRunEndDate = interfaceRunEndDate;
	}

	public Integer getAddCount() {
		return this.addCount;
	}

	public void setAddCount(Integer addCount) {
		this.addCount = addCount;
	}

	public Integer getChangeCount() {
		return this.changeCount;
	}

	public void setChangeCount(Integer changeCount) {
		this.changeCount = changeCount;
	}

	public Integer getDeleteCount() {
		return this.deleteCount;
	}

	public void setDeleteCount(Integer deleteCount) {
		this.deleteCount = deleteCount;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUnprocessedRecords() {
		return this.unprocessedRecords;
	}

	public void setUnprocessedRecords(String unprocessedRecords) {
		this.unprocessedRecords = unprocessedRecords;
	}

	public String getBatchId() {
		return this.batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Double getUnits() {
		return this.units;
	}

	public void setUnits(Double units) {
		this.units = units;
	}

	public Double getSalesAmount() {
		return this.salesAmount;
	}

	public void setSalesAmount(Double salesAmount) {
		this.salesAmount = salesAmount;
	}

	public Double getDeductionAmount() {
		return this.deductionAmount;
	}

	public void setDeductionAmount(Double deductionAmount) {
		this.deductionAmount = deductionAmount;
	}

}
