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
            if (projectionSelection.isIsCustomHierarchy()) {
                String parentId = obj[NumericConstants.FOUR] != null ? obj[NumericConstants.FOUR].toString() : StringUtils.EMPTY;
                key = obj[NumericConstants.ZERO].toString() + "$" + parentId;
            } else {
                key = key.substring(key.indexOf('-') + 1);
            }

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
          String discountId=projectionSelection.isIsCustomHierarchy()?StringUtils.EMPTY:obj[5].toString();
        String header = commonLogic.getHeaderForExcel(freq, obj,discountId,StringUtils.EMPTY);
            boolean isActuals = "0".equals(String.valueOf(obj[NumericConstants.THREE]));
            String hierarchyNo = String.valueOf(obj[NumericConstants.ZERO]).trim();
            hierarchyNo = hierarchyNo.contains(",") ? hierarchyNo.split(",")[0].trim() : hierarchyNo;
            String hierarchyIndicator = String.valueOf(hierarchyLevelDetails.get(hierarchyNo.trim()).get(4));
            String parentHierarchy= String.valueOf(obj[NumericConstants.FOUR]).trim();
            parentHierarchy=parentHierarchy.replaceAll("\\s","");
            discountProjectionDTO.setParentHierarchyNo(parentHierarchy);
            discountProjectionDTO.setHierarchyLevel(String.valueOf(hierarchyLevelDetails.get(hierarchyNo).get(1)));
            if (Constant.TRADINGPARTNER.equalsIgnoreCase(discountProjectionDTO.getHierarchyLevel()) || Constant.TRADING_PARTNER.equals(discountProjectionDTO.getHierarchyLevel())) {
                 discountProjectionDTO.setGroup(String.valueOf(obj[NumericConstants.FIFTEEN]));
            } else {
                discountProjectionDTO.setGroup(StringUtils.EMPTY);
            }
         if (CommonUtil.isValueEligibleForLoading()) {

            excelFormattedColumns(discountProjectionDTO, projectionSelection, hierarchyNo, hierarchyIndicator, hierarchyLevelDetails);

        } else {
            discountProjectionDTO.setLevelName(CommonUtil.getDisplayFormattedName(hierarchyNo, hierarchyIndicator, hierarchyLevelDetails, projectionSelection.getSessionDTO(), projectionSelection.getDisplayFormat()));

        }
            if (CommonUtil.isValueEligibleForLoading()) {
                discountProjectionDTO.setDeductionInclusion(obj[NumericConstants.FOURTEEN] != null ? String.valueOf(obj[NumericConstants.FOURTEEN]) : StringUtils.EMPTY);
            }
        if (doubleProjectedAndHistoryCombinedUniqueList.contains(header) && !Constant.NULL.equals(discountProjectionDTO.getDeductionInclusion())) {
            setActualsProj(discountProjectionDTO, isActuals, header, projectionSelection, obj);
        }else{
            setActualsProjForDeductionInclusion(discountProjectionDTO, isActuals, header, projectionSelection, obj);
        }
           
    }

    private void setActualsProj(DiscountProjectionDTO discountProjectionDTO, boolean isActuals, String header, ProjectionSelectionDTO projectionSelection, Object[] obj) {
       String value;
       
        if (isActuals) {
            value=CommonUtil.getConversionFormattedValue(projectionSelection, obj[NumericConstants.SIX], false);
            discountProjectionDTO.addStringProperties(header + ACTUAL_RATE, commonLogic.getFormattedValue(PERCENTAGE_FORMAT, Constant.NULL.equals(String.valueOf(obj[NumericConstants.SEVEN])) ? DASH : String.valueOf(obj[NumericConstants.SEVEN])));
            discountProjectionDTO.addStringProperties(header + ACTUAL_AMOUNT,Constant.NULL.equals(value)?DASH:value);
            discountProjectionDTO.addStringProperties(header + Constant.ACTUALRPU,Constant.NULL.equals(String.valueOf(obj[NumericConstants.EIGHT])) ? DASH : String.valueOf(obj[NumericConstants.EIGHT]));
        } else {
          getProjectionData(projectionSelection,obj,discountProjectionDTO,header);
        }
    }
    
     private void getProjectionData(ProjectionSelectionDTO projectionSelection, Object[] obj, DiscountProjectionDTO discountProjectionDTO, String header) {
    	  String value=CommonUtil.getConversionFormattedValue(projectionSelection, obj[NumericConstants.NINE], false);
          String projectedValue=commonLogic.getFormattedValue(PERCENTAGE_FORMAT, Constant.NULL.equals(String.valueOf(obj[NumericConstants.TEN])) ? DASH : String.valueOf(obj[NumericConstants.TEN]));
          discountProjectionDTO.addStringProperties(header + PROJECTED_RATE,CommonUtils.forecastConfigDataHide(projectionSelection.getFrequency(), projectionSelection.getForecastConfigPeriods(),
                      header, projectedValue) );
          projectedValue=Constant.NULL.equals(value)?DASH:value;
          discountProjectionDTO.addStringProperties(header + PROJECTED_AMOUNT,CommonUtils.forecastConfigDataHide(projectionSelection.getFrequency(), projectionSelection.getForecastConfigPeriods(),
                      header, projectedValue) );
          projectedValue= Constant.NULL.equals(String.valueOf(obj[NumericConstants.ELEVEN])) ? DASH : String.valueOf(obj[NumericConstants.ELEVEN]);
          discountProjectionDTO.addStringProperties(header + Constant.PROJECTEDRPU,CommonUtils.forecastConfigDataHide(projectionSelection.getFrequency(), projectionSelection.getForecastConfigPeriods(),
                      header, projectedValue) );
          discountProjectionDTO.addStringProperties(header + Constant.GROWTH, Constant.NULL.equals(String.valueOf(obj[NumericConstants.FOURTEEN])) ? DASH : String.valueOf(obj[NumericConstants.FOURTEEN]));
		
	}
	private void setActualsProjForDeductionInclusion(DiscountProjectionDTO discountProjectionDTO, boolean isActuals, String header, ProjectionSelectionDTO projectionSelection, Object[] obj) {
        if (isActuals) {
            discountProjectionDTO.addStringProperties(header + ACTUAL_RATE, StringUtils.EMPTY);
            discountProjectionDTO.addStringProperties(header + ACTUAL_AMOUNT, StringUtils.EMPTY);
            discountProjectionDTO.addStringProperties(header + Constant.ACTUALRPU, StringUtils.EMPTY);
        } else {
            projectionSelection.getForecastConfigPeriods();
            discountProjectionDTO.addStringProperties(header + PROJECTED_RATE,  StringUtils.EMPTY);
            discountProjectionDTO.addStringProperties(header + PROJECTED_AMOUNT,  StringUtils.EMPTY);
            discountProjectionDTO.addStringProperties(header + Constant.PROJECTEDRPU,  StringUtils.EMPTY);
            discountProjectionDTO.addStringProperties(header + Constant.GROWTH,  StringUtils.EMPTY);
        }
    }
     
        public void excelFormattedColumns(DiscountProjectionDTO discountProjectionDTO, ProjectionSelectionDTO projectionSelection, String hierarchyNo, String hierarchyIndicator, Map<String, List> hierarchyLevelDetails) {

        List<String> levelName = CommonUtil.getFormattedDisplayName(hierarchyNo, hierarchyIndicator, hierarchyLevelDetails, projectionSelection.getSessionDTO(), projectionSelection.getDisplayFormat());
        discountProjectionDTO.setLevelName(levelName.toString());
        LOGGER.info("Size============={}" , levelName.size());
        LOGGER.info("List============={}" , levelName);
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
     
}
