/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.discountProjection.logic;

import com.stpl.app.gtnforecasting.dto.DiscountProjectionDTO;
import com.stpl.app.gtnforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.logic.CommonLogic;
import static com.stpl.app.gtnforecasting.logic.DiscountProjectionLogic.ACTUAL_AMOUNT;
import static com.stpl.app.gtnforecasting.logic.DiscountProjectionLogic.ACTUAL_RATE;
import static com.stpl.app.gtnforecasting.logic.DiscountProjectionLogic.PROJECTED_AMOUNT;
import static com.stpl.app.gtnforecasting.logic.DiscountProjectionLogic.PROJECTED_RATE;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.utils.CommonUtil;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import static com.stpl.app.gtnforecasting.utils.Constant.DASH;
import com.stpl.ifs.ui.util.NumericConstants;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Nimisha.Rakesh
 */
public class NMDiscountExcelLogic {
    
    private final Map<String, DiscountProjectionDTO> resultMap = new HashMap<>();
    private final List<String> hierarchyKeys = new ArrayList<>();
    private final List<String> hierarchyValues = new ArrayList<>();
    private final CommonLogic commonLogic = new CommonLogic();
    private static final DecimalFormat PERCENTAGE_FORMAT = new DecimalFormat("#,##0.00%");
    private static final String DF_LEVEL_NAME = "dfLevelName";
    private static final String DF_LEVEL_NUMBER = "dfLevelNumber";
    public static final Logger LOGGER = LoggerFactory.getLogger(NMDiscountExcelLogic.class);

    public void getCustomizedExcelData(List<Object[]> discountExcelList, ProjectionSelectionDTO projectionSelection, List doubleProjectedAndHistoryCombinedUniqueList) {
        SessionDTO sessionDTO = projectionSelection.getSessionDTO();
        Character freq = projectionSelection.getFrequency().charAt(0);
        Map<String, List> hierarchyLevelDetails = sessionDTO.getHierarchyLevelDetails();
        for (Iterator<Object[]> it = discountExcelList.listIterator(); it.hasNext();) {
            Object[] obj = it.next();
            String key = obj[NumericConstants.ZERO].toString();
            String hierKey = key.substring(0,key.lastIndexOf('.'));
            key = key.substring(key.indexOf('-') + 1);
           
            DiscountProjectionDTO discountProjectionDTO = resultMap.get(key);
            if (discountProjectionDTO == null) {
                 getHierarchy(hierKey,projectionSelection);
                 getParentLevels(projectionSelection);
                discountProjectionDTO = new DiscountProjectionDTO();
                setActualsProjectionValues(discountProjectionDTO, freq, obj, projectionSelection, hierarchyLevelDetails, doubleProjectedAndHistoryCombinedUniqueList);
                resultMap.put(key, discountProjectionDTO);
                hierarchykeys(key);
            } else {
                setActualsProjectionValues(discountProjectionDTO, freq, obj, projectionSelection, hierarchyLevelDetails, doubleProjectedAndHistoryCombinedUniqueList);
            }

        }
    }
    public void getCustomizedExcelDataCustom(List<Object[]> discountExcelList, ProjectionSelectionDTO projectionSelection, List doubleProjectedAndHistoryCombinedUniqueList) {
        SessionDTO sessionDTO = projectionSelection.getSessionDTO();
        Character freq = projectionSelection.getFrequency().charAt(0);
        Map<String, List> hierarchyLevelDetails = sessionDTO.getDiscountHierarchyLevelDetails();
        for (Iterator<Object[]> it = discountExcelList.listIterator(); it.hasNext();) {
            Object[] obj = it.next();
            String key = obj[NumericConstants.ZERO].toString();
                key = key.substring(key.indexOf('-') + 1);
           
            DiscountProjectionDTO discountProjectionDTO = resultMap.get(key);
            if (discountProjectionDTO == null) {
                discountProjectionDTO = new DiscountProjectionDTO();
                setActualsProjectionValues(discountProjectionDTO, freq, obj, projectionSelection, hierarchyLevelDetails, doubleProjectedAndHistoryCombinedUniqueList);
                resultMap.put(key, discountProjectionDTO);
                hierarchykeys(key);
            } else {
                setActualsProjectionValues(discountProjectionDTO, freq, obj, projectionSelection, hierarchyLevelDetails, doubleProjectedAndHistoryCombinedUniqueList);
            }

        }
    }
    
    public String getHierarchy(String key, ProjectionSelectionDTO projectionSelection) {

        if (key.contains(".")) {
            int count = StringUtils.countMatches(key, ".");
            String keyValue = key.substring(0, key.lastIndexOf('.'));
            int forecastLevel = projectionSelection.getHierarchyIndicator().equalsIgnoreCase("C") ? Integer.parseInt(projectionSelection.getSessionDTO().getCustomerLevelNumber()) : Integer.parseInt(projectionSelection.getSessionDTO().getProductLevelNumber());
            if (!projectionSelection.isIsCustomHierarchy()) {
            if (count >= forecastLevel) {
                hierarchyValues.add(keyValue);
            }
            }else {
                hierarchyValues.add(keyValue);
            }
            getHierarchy(keyValue, projectionSelection);
            return keyValue;
        }
        return StringUtils.EMPTY;
    }

    public void getParentLevels(ProjectionSelectionDTO projectionSelection) {
        Map<String, List> hierarchyLevelDetails = projectionSelection.getSessionDTO().getHierarchyLevelDetails();
        Collections.reverse(hierarchyValues);
        for (int i = 0; i < hierarchyValues.size(); i++) {
            StringBuilder hierarchy = new StringBuilder(hierarchyValues.get(i)).append(".");
            StringBuilder hierKeyValue = new StringBuilder(hierarchy.substring(hierarchy.indexOf("-") + 1));
            DiscountProjectionDTO discountProjectionDTO = resultMap.get(hierKeyValue.toString());
            if (discountProjectionDTO == null) {
                discountProjectionDTO = new DiscountProjectionDTO();
                String hierarchyIndicator = String.valueOf(hierarchyLevelDetails.get(hierarchy.toString()).get(4));
                excelFormattedColumns(discountProjectionDTO, projectionSelection, hierarchy.toString(), hierarchyIndicator, hierarchyLevelDetails);
                resultMap.put(hierKeyValue.toString(), discountProjectionDTO);
                hierarchykeys(hierKeyValue.toString());
            } 
        }
        }
    
     private void hierarchykeys(String key) {
        hierarchyKeys.add(key);
    }

    public Map<String, DiscountProjectionDTO> getResultMap() {
        return resultMap;
    }

    public List<String> getHierarchyKeys() {
        return hierarchyKeys;
    }

    private void setActualsProjectionValues(DiscountProjectionDTO discountProjectionDTO, Character freq, Object[] obj, ProjectionSelectionDTO projectionSelection, Map<String, List> hierarchyLevelDetails, List doubleProjectedAndHistoryCombinedUniqueList) {
        if (obj[NumericConstants.ONE] == null) {
            String hierarchy = String.valueOf(obj[NumericConstants.ZERO]).trim();
            excelFormattedColumns(discountProjectionDTO, projectionSelection, hierarchy, String.valueOf(hierarchyLevelDetails.get(hierarchy.trim()).get(4)), hierarchyLevelDetails);
            return;
        }
        String discount = getValue(String.valueOf(obj[NumericConstants.FIVE]), StringUtils.EMPTY);
        String discountId = projectionSelection.isIsCustomHierarchy() ? StringUtils.EMPTY : discount;
        String header = commonLogic.getHeaderForExcel(freq, obj, discountId, StringUtils.EMPTY);
        String column = commonLogic.getHeaderForExcelDiscount(freq, obj, StringUtils.EMPTY);
        boolean isActuals = "0".equals(String.valueOf(obj[NumericConstants.THREE]));
        String hierarchyNo = String.valueOf(obj[NumericConstants.ZERO]).trim();
        hierarchyNo = hierarchyNo.contains(",") ? hierarchyNo.split(",")[0].trim() : hierarchyNo;
        String hierarchyIndicator = String.valueOf(hierarchyLevelDetails.get(hierarchyNo.trim()).get(4));
        String parentHierarchy = String.valueOf(obj[NumericConstants.FOUR]).trim();
        parentHierarchy = parentHierarchy.replaceAll("\\s", "");
        discountProjectionDTO.setParentHierarchyNo(parentHierarchy);
        discountProjectionDTO.setHierarchyLevel(String.valueOf(hierarchyLevelDetails.get(hierarchyNo).get(1)));
        if (Constant.TRADINGPARTNER.equalsIgnoreCase(discountProjectionDTO.getHierarchyLevel()) || Constant.TRADING_PARTNER.equals(discountProjectionDTO.getHierarchyLevel())) {
            discountProjectionDTO.setGroup(String.valueOf(obj[NumericConstants.FIFTEEN]));
        } else {
            discountProjectionDTO.setGroup(StringUtils.EMPTY);
        }
        if (CommonUtil.isValueEligibleForLoading()) {
            excelFormattedColumns(discountProjectionDTO, projectionSelection, hierarchyNo, hierarchyIndicator, hierarchyLevelDetails);
            discountProjectionDTO.setDeductionInclusion(projectionSelection.isIsCustomHierarchy() ? getValue(String.valueOf(obj[NumericConstants.TWELVE]), StringUtils.EMPTY)
                    : getValue(String.valueOf(obj[NumericConstants.FOURTEEN]), StringUtils.EMPTY));
        } else {
            discountProjectionDTO.setLevelName(CommonUtil.getDisplayFormattedName(hierarchyNo,  hierarchyLevelDetails, projectionSelection.getSessionDTO(), projectionSelection.getDisplayFormat()));

        }
        if (doubleProjectedAndHistoryCombinedUniqueList.contains(header) && !Constant.NULL.equals(discountProjectionDTO.getDeductionInclusion())) {
            setActualsProj(discountProjectionDTO, isActuals, header, projectionSelection, obj, column);
        } else{
            setActualsProjForDeductionInclusion(discountProjectionDTO, isActuals, header, projectionSelection);
        }
           
    }
    
    private void setActualsProj(DiscountProjectionDTO discountProjectionDTO, boolean isActuals, String header, ProjectionSelectionDTO projectionSelection, Object[] obj, String column) {
        String value;
        if (isActuals) {
            value = CommonUtil.getConversionFormattedValue(projectionSelection, obj[NumericConstants.SIX], false);
            discountProjectionDTO.addStringProperties(header + ACTUAL_RATE, commonLogic.getFormattedValue(PERCENTAGE_FORMAT, getValue(String.valueOf(obj[NumericConstants.SEVEN]),DASH)));
            discountProjectionDTO.addStringProperties(header + ACTUAL_AMOUNT, Constant.NULL.equals(value) ? DASH : value);
            discountProjectionDTO.addStringProperties(header + Constant.ACTUALRPU, getValue(String.valueOf(obj[NumericConstants.EIGHT]),DASH));
            if (!projectionSelection.isIsCustomHierarchy()) {
                discountProjectionDTO.addStringProperties(header + "ActualSales", getValue(String.valueOf(obj[NumericConstants.SEVENTEEN]),DASH));
                discountProjectionDTO.addStringProperties(header + "ActualUnits", getValue(String.valueOf(obj[NumericConstants.EIGHTEEN]),DASH));
            }
        } else {
            getProjectionData(projectionSelection, obj, discountProjectionDTO, header, column);
        }
    }
    
     private void getProjectionData(ProjectionSelectionDTO projectionSelection, Object[] obj, DiscountProjectionDTO discountProjectionDTO, String header,String column) {
    	  String value=CommonUtil.getConversionFormattedValue(projectionSelection, obj[NumericConstants.NINE], false);
          String projectedValue=commonLogic.getFormattedValue(PERCENTAGE_FORMAT, Constant.NULL.equals(String.valueOf(obj[NumericConstants.TEN])) ? DASH : String.valueOf(obj[NumericConstants.TEN]));
          discountProjectionDTO.addStringProperties(header + PROJECTED_RATE,CommonUtils.forecastConfigDataHide(projectionSelection.getFrequency(), projectionSelection.getForecastConfigPeriods(),
                      column, projectedValue) );
          projectedValue=Constant.NULL.equals(value)?DASH:value;
          discountProjectionDTO.addStringProperties(header + PROJECTED_AMOUNT,CommonUtils.forecastConfigDataHide(projectionSelection.getFrequency(), projectionSelection.getForecastConfigPeriods(),
                      column, projectedValue) );
          projectedValue= Constant.NULL.equals(String.valueOf(obj[NumericConstants.ELEVEN])) ? DASH : String.valueOf(obj[NumericConstants.ELEVEN]);
          discountProjectionDTO.addStringProperties(header + Constant.PROJECTEDRPU,CommonUtils.forecastConfigDataHide(projectionSelection.getFrequency(), projectionSelection.getForecastConfigPeriods(),
                      column, projectedValue) );
          discountProjectionDTO.addStringProperties(header + Constant.GROWTH, Constant.NULL.equals(String.valueOf(obj[NumericConstants.FOURTEEN])) ? DASH : String.valueOf(obj[NumericConstants.FOURTEEN]));
          
          // Added Growth sum column
          discountProjectionDTO.addStringProperties(header + Constant.GROWTH_SUM, Constant.NULL.equals(String.valueOf(obj[NumericConstants.FOURTEEN])) ? DASH : String.valueOf(obj[NumericConstants.FOURTEEN]));
          
          if(!projectionSelection.isIsCustomHierarchy()){
          discountProjectionDTO.addStringProperties(header + "ProjectedSales", Constant.NULL.equals(String.valueOf(obj[NumericConstants.NINETEEN])) ? DASH : String.valueOf(obj[NumericConstants.NINETEEN]));
          discountProjectionDTO.addStringProperties(header + "ProjectedUnits", Constant.NULL.equals(String.valueOf(obj[NumericConstants.TWENTY])) ? DASH : String.valueOf(obj[NumericConstants.TWENTY]));
          }
		
	}
	private void setActualsProjForDeductionInclusion(DiscountProjectionDTO discountProjectionDTO, boolean isActuals, String header, ProjectionSelectionDTO projectionSelection) {
        if (isActuals) {
            discountProjectionDTO.addStringProperties(header + ACTUAL_RATE, StringUtils.EMPTY);
            discountProjectionDTO.addStringProperties(header + ACTUAL_AMOUNT, StringUtils.EMPTY);
            discountProjectionDTO.addStringProperties(header + Constant.ACTUALRPU, StringUtils.EMPTY);
            discountProjectionDTO.addStringProperties(header + "ActualSales", StringUtils.EMPTY);
            discountProjectionDTO.addStringProperties(header + "ActualUnits", StringUtils.EMPTY);
        } else {
            projectionSelection.getForecastConfigPeriods();
            discountProjectionDTO.addStringProperties(header + PROJECTED_RATE,  StringUtils.EMPTY);
            discountProjectionDTO.addStringProperties(header + PROJECTED_AMOUNT,  StringUtils.EMPTY);
            discountProjectionDTO.addStringProperties(header + Constant.PROJECTEDRPU,  StringUtils.EMPTY);
            discountProjectionDTO.addStringProperties(header + Constant.GROWTH,  StringUtils.EMPTY);
            discountProjectionDTO.addStringProperties(header + "ProjectedSales",  StringUtils.EMPTY);
            discountProjectionDTO.addStringProperties(header + "ProjectedUnits",  StringUtils.EMPTY);
            
            //Added Growth Sum column
            discountProjectionDTO.addStringProperties(header + Constant.GROWTH_SUM,  StringUtils.EMPTY);
        }
    }
     
        public void excelFormattedColumns(DiscountProjectionDTO discountProjectionDTO, ProjectionSelectionDTO projectionSelection, String hierarchyNo, String hierarchyIndicator, Map<String, List> hierarchyLevelDetails) {

        List<String> levelName = CommonUtil.getFormattedDisplayName(hierarchyNo,  hierarchyLevelDetails, projectionSelection.getSessionDTO(), projectionSelection.getDisplayFormat());
        discountProjectionDTO.setLevelName(levelName.toString());
        if (projectionSelection.getDisplayFormat().length == 1 && projectionSelection.getDisplayFormat().length > 0) {
            int index = (int) projectionSelection.getDisplayFormat()[0];
            if (index == 0) {

                discountProjectionDTO.addStringProperties(DF_LEVEL_NUMBER, levelName.get(0));
            } else {
                discountProjectionDTO.addStringProperties(DF_LEVEL_NAME, levelName.get(0));
            }
        } else {
            discountProjectionDTO.addStringProperties(DF_LEVEL_NAME, levelName.get(0));
            discountProjectionDTO.addStringProperties(DF_LEVEL_NUMBER, levelName.get(0));
            if (levelName.size() == 2) {
                discountProjectionDTO.addStringProperties(DF_LEVEL_NAME, levelName.get(1));
                discountProjectionDTO.addStringProperties(DF_LEVEL_NUMBER, levelName.get(0));
            }
        }
    }
            
    private String getValue(String value, String defaultValue) {
        return Constant.NULL.equals(value) ? defaultValue : value;
    }
}
