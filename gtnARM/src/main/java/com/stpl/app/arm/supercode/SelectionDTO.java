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
import java.util.TreeMap;

/**
 *
 * @author Abhiram.Giri
 */
public interface SelectionDTO {

    public List<String[]> getSummary_variables();

    public List<String[]> getSummary_deductionVariables();

    public List<String> getSummary_columnList();

    public int getSummary_deductionLevel();

    public String getSummary_deductionLevelDes();

    public List<Integer> getSummary_deductionVariableIds();

    public String getSummary_month();

    public int getProjectionMasterSid();

    public int getCompanyMasterSid();

    public String getSummary_deductionValues();

    public DataSelectionDTO getDataSelectionDTO();

    public List<String> getSelectedAdjustmentType();
    
    public List<String> getSelectedAdjustmentTypeValues();

    public String getDetail_Level();

    public List<String> getDetail_reserveAcount();

    public List<String> getDetail_variables();

    public String getSummary_glDate();

    public String getFromDate();

    public String getToDate();

    public String getDeductionLevelValue();

    public String getSales_levelFilterValue();

    public String getDateType();

    public String getPrice();

    public int getLevelNo();

    public String getStatus();

    public int getRate_DeductionLevel();

    public String getRate_DeductionLevelName();

    public String getRate_DeductionValue();

    public String getRate_DeductionView();

    public int getRate_Basis();

    public String getRate_BasisName();

    public int getRate_Frequency();

    public String getRate_FrequencyName();

    public String getRate_Period();

    public List<List> getRate_ColumnList();

    public String getRate_LevelName();

    /**
     * Generate button Validation
     *
     * @return
     */
    public boolean isRateGenerateAllowed();

    public String getModuleName();

    public List<String[]> getSummary_frequencyList();

    public String getSummary_demand_view();

    public String getSummary_demand_toDate();

    public String getSummary_demand_fromDate();

    public String getSummary_demand_frequency();

    public int getSummary_demand_MultipleLevel();

    public Map<Integer, String> getSummary_demand_Level();

    public void setSummary_demand_Level(Map<Integer, String> summary_demand_Level);

    public Object getProcedureInputs(String feild);

    public void setProcedureInputs(String feild, Object value);

    public boolean isFieldInput(String feild);

    public List<String[]> getSales_variables();

    public String[] getVariableVisibleColumns();

    public String getInventory_reserveDate();

    public String getTableName();

    public void setTableName(String tableName);

    public String getRateBasis();

    public void setRateBasis(String rateBasis);

    public String getRateFrequency();

    public void setRateFrequency(String rateFrequency);

    public void setSummary_demand_MultipleLevel(int summary_demand_MultipleLevel);

    public String getRatePeriod();

    public String getSummary_viewType();

    public int getSummary_levelFilterNo();

    public String getSummary_levelFilterValue();

    public int getRates_levelFilterNo();

    String getInventory_Details();

    public void setUserId(Integer userId);

    public Integer getUserId();

    public Integer getSessionId();

    public void setSessionId(Integer sessionId);

    public TreeMap<String, Integer> getMasterSids();

    public void setSummary_glList(List<String> summary_glList);

    public List<String> getSummary_glList();

    SessionDTO getSessionDTO();

    public void setSummary_variables(List<String[]> summary_variables);

    public Map<Integer, String> getSales_hierarchy();

    public Map<Integer, String> getRates_hierarchy();

    public Map<Integer, String> getSummery_hierarchy();

    public int getSummary_valueSid();
    
    public List<String> getDetail_amountFilter();
    
    public List<String> getInventory_columnList();
}
