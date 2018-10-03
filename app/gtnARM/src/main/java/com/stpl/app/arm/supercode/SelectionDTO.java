/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.supercode;

import com.stpl.app.arm.common.dto.SessionDTO;
import com.stpl.app.arm.dataselection.dto.DataSelectionDTO;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

/**
 *
 * @author Abhiram.Giri
 */
public interface SelectionDTO {

    public List<String[]> getSummaryvariables();

    public List<String[]> getSummarydeductionVariables();

    public List<String> getSummarycolumnList();

    public int getSummarydeductionLevel();

    public String getSummarydeductionLevelDes();

    public String getSummarymonth();

    public int getProjectionMasterSid();

    public int getCompanyMasterSid();

    public String getSummarydeductionValues();

    public DataSelectionDTO getDataSelectionDTO();

    public List<String> getSelectedAdjustmentType();

    public List<String> getSelectedAdjustmentTypeValues();

    public String getDetailLevel();

    public List<String> getDetailreserveAcount();

    public List<String> getDetailvariables();

    public String getSummaryglDate();

    public String getFromDate();

    public String getToDate();

    public String getDeductionLevelValue();

    public String getSaleslevelFilterValue();

    public String getDateType();

    public String getPrice();

    public int getLevelNo();

    public String getStatus();

    public int getRateDeductionLevel();

    public String getRateDeductionLevelName();

    public String getRateDeductionValue();

    public String getRateDeductionView();

    public int getRateBasisValue();

    public String getRateBasisName();

    public int getRateFrequencyValue();

    public String getRateFrequencyName();

    public String getRatePeriodValue();

    public List<List> getRateColumnList();

    public String getRateLevelName();

    /**
     * Generate button Validation
     *
     * @return
     */
    public boolean isRateGenerateAllowed();

    public String getModuleName();

    public List<String[]> getSummaryfrequencyList();

    public String getSummarydemandview();

    public String getSummarydemandtoDate();

    public String getSummarydemandfromDate();

    public String getSummarydemandfrequency();

    public int getSummarydemandMultipleLevel();

    public Map<Integer, String> getSummarydemandLevel();

    public void setSummarydemandLevel(Map<Integer, String> summarydemandLevel);

    public Object getProcedureInputs(String feild);

    public void setProcedureInputs(String feild, Object value);

    public List<String[]> getSalesVariables();

    public String[] getVariableVisibleColumns();

    public String getInventoryreserveDate();

    public String getTableName();

    public void setTableName(String tableName);

    public String getRateBasis();

    public void setRateBasis(String rateBasis);

    public String getRateFrequency();

    public void setRateFrequency(String rateFrequency);

    public void setSummarydemandMultipleLevel(int summarydemandMultipleLevel);

    public String getRatePeriod();

    public String getSummaryviewType();

    public int getSummarylevelFilterNo();

    public String getSummarylevelFilterValue();

    public int getRateslevelFilterNo();

    String getInventoryDetails();

    public void setUserId(Integer userId);

    public Integer getUserId();

    public Integer getSessionId();

    public void setSessionId(Integer sessionId);

    public SortedMap<String, Integer> getMasterSids();

    SessionDTO getSessionDTO();

    public void setSummaryvariables(List<String[]> summaryVariables);

    public Map<Integer, String> getSaleshierarchy();

    public Map<Integer, String> getRateshierarchy();

    public Map<Integer, String> getSummeryhierarchy();

    public int getSummaryvalueSid();

    public List<String> getDetailamountFilter();

    public List<String> getInventorycolumnList();

    public List<String> getReturnsdatavariables();

    public boolean isCancelOverride();

    public List<String> getReturnsdataSelectedvariables();

    public int getRatesOverrideFlag();

    public boolean isExcelDetials();
}
