/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.discountprojectionresults.logic;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionList;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.gtnforecasting.dao.SalesProjectionDAO;
import com.stpl.app.gtnforecasting.dao.impl.SalesProjectionDAOImpl;
import com.stpl.app.gtnforecasting.discountprojectionresults.dto.DiscountProjectionResultsDTO;
import com.stpl.app.gtnforecasting.discountprojectionresults.utils.HeaderUtils;
import com.stpl.app.gtnforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.logic.CommonLogic;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import static com.stpl.app.gtnforecasting.utils.Constant.ANNUALLY;
import static com.stpl.app.gtnforecasting.utils.Constant.LabelConstants.MANDATED_DISCOUNT;
import static com.stpl.app.gtnforecasting.utils.Constant.LabelConstants.PERIOD;
import static com.stpl.app.gtnforecasting.utils.Constant.LabelConstants.SUPPLEMENTAL_DISCOUNT;
import com.stpl.app.service.MProjectionSelectionLocalServiceUtil;
import static com.stpl.app.utils.Constants.CommonConstants.ACTION_VIEW;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.QueryUtil;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author pvinoth
 */
public class DPRLogic {

    private static final Logger LOGGER = LoggerFactory.getLogger(DPRLogic.class);
    private static final DecimalFormat CUR_ZERO = new DecimalFormat("$#,##0");
    private static final DecimalFormat CUR_TWO = new DecimalFormat("$#,##0.00");
    private static final DecimalFormat PER_THREE = new DecimalFormat("#,##0.000");
    private static final String CURRENCY = "$";
    private static final String PERCENTAGE = Constant.PERCENT;
    private static final DecimalFormat UNITVOLUME = new DecimalFormat("#,##0.0");
    private List<DiscountProjectionResultsDTO> projectionTotalList = new ArrayList<>();
    protected Object[] dprOrderedArgs;
    protected List<Object[]> totalPrcResultList = new ArrayList<>();
    public static final String SUPPLEMENTAL = "supplemental";
    public static final String SUPPLEMENTAL_DISCOUNT1 = "SupplementalDiscount";

    public List<DiscountProjectionResultsDTO> getConfiguredDPResults(Object parentId, int start, int offset, ProjectionSelectionDTO projSelDTO) {
        List<DiscountProjectionResultsDTO> resultList;
        if (!projSelDTO.isIsFilter() || (parentId instanceof DiscountProjectionResultsDTO)) {
            projSelDTO.setYear(Constant.ALL);

            if (projSelDTO.getActualsOrProjections().equals(Constant.BOTH)) {
                projSelDTO.setActualsOrProjections("Actuals and Projections");
            }
            if (ANNUALLY.equalsIgnoreCase(projSelDTO.getFrequency())) {
                projSelDTO.setFrequencyDivision(1);
            } else if (Constant.SEMIANNUALLY_SMALL.equalsIgnoreCase(projSelDTO.getFrequency())) {
                projSelDTO.setFrequencyDivision(NumericConstants.TWO);
            } else if (Constant.QUARTERLY_SMALL.equalsIgnoreCase(projSelDTO.getFrequency())) {
                projSelDTO.setFrequencyDivision(NumericConstants.FOUR);
            } else if (Constant.MONTHLY_SMALL.equalsIgnoreCase(projSelDTO.getFrequency())) {
                projSelDTO.setFrequencyDivision(NumericConstants.TWELVE);
            }
            if (parentId instanceof DiscountProjectionResultsDTO) {
                DiscountProjectionResultsDTO parentDto = (DiscountProjectionResultsDTO) parentId;
                projSelDTO.setLevelNo(parentDto.getLevelNo());
                projSelDTO.setTreeLevelNo(parentDto.getTreeLevelNo());
                projSelDTO.setLevelValue(parentDto.getLevelValue());
                projSelDTO.setRelationshipLevelName(parentDto.getRelationshipLevelName());
                projSelDTO.setParentNode(parentDto.getParentNode());
                projSelDTO.setHierarchyNo(parentDto.getHierarchyNo());
                if (projSelDTO.isIsFilter()) {
                    projSelDTO.setHierarchyNo(StringUtils.EMPTY);
                }
                projSelDTO.setCustomLevelNo(parentDto.getCustomLevelNo());
                if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equalsIgnoreCase(parentDto.getHierarchyIndicator())) {
                    projSelDTO.setCustomerHierarchyNo(projSelDTO.getHierarchyNo());
                    projSelDTO.setProductHierarchyNo(parentDto.getProductHierarchyNo());
                } else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equalsIgnoreCase(parentDto.getHierarchyIndicator())) {
                    projSelDTO.setProductHierarchyNo(projSelDTO.getHierarchyNo());
                    projSelDTO.setCustomerHierarchyNo(parentDto.getCustomerHierarchyNo());
                }
                projSelDTO.setHierarchyIndicator(parentDto.getHierarchyIndicator());
                projSelDTO.setIsProjectionTotal(parentDto.getProjectionTotal() == 1);
                projSelDTO.setGroup(parentDto.getGroup());
                projSelDTO.setIsTotal(parentDto.getOnExpandTotalRow() == 1);
                projSelDTO.setParentLevelValue(parentDto.getParentLevelName());
                projSelDTO.setMandatedDTO(parentDto.getManDTO() != null ? parentDto.getManDTO() : new DiscountProjectionResultsDTO());
                projSelDTO.setSupplementalDTO(parentDto.getSuppDTO() != null ? parentDto.getSuppDTO() : new DiscountProjectionResultsDTO());
                projSelDTO.setDprDTOList(parentDto.getDprDTOList() != null ? parentDto.getDprDTOList() : new ArrayList<DiscountProjectionResultsDTO>());
            } else {
                projSelDTO.setIsProjectionTotal(true);
                projSelDTO.setIsTotal(true);
                if (projSelDTO.isCustomFlag()) {
                    String indicator = CommonLogic.getIndicator(1, projSelDTO.getCustomId());
                    projSelDTO.setHierarchyIndicator(indicator);
                    projSelDTO.setLevelNo(0);
                    projSelDTO.setTreeLevelNo(0);
                    projSelDTO.setCustomLevelNo(0);;
                } else if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(projSelDTO.getHierarchyIndicator())) {
                    projSelDTO.setLevelNo(projSelDTO.getCustomerLevelNo() - 1);
                    projSelDTO.setTreeLevelNo(projSelDTO.getCustomerLevelNo() - 1);
                } else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(projSelDTO.getHierarchyIndicator())) {
                    projSelDTO.setLevelNo(projSelDTO.getProductLevelNo() - 1);
                    projSelDTO.setTreeLevelNo(projSelDTO.getProductLevelNo() - 1);
                } else {
                    projSelDTO.setLevelNo(0);
                    projSelDTO.setTreeLevelNo(0);
                }
                projSelDTO.setGroup(StringUtils.EMPTY);
                projSelDTO.setHierarchyNo(StringUtils.EMPTY);
                projSelDTO.setProductHierarchyNo(StringUtils.EMPTY);
                projSelDTO.setCustomerHierarchyNo(StringUtils.EMPTY);
                projSelDTO.setLevelValue(StringUtils.EMPTY);
                projSelDTO.setRelationshipLevelName(StringUtils.EMPTY);
            }
            resultList = getDPResults(start, offset, projSelDTO);
        } else {
            projSelDTO.setHierarchyNo(StringUtils.EMPTY);
            projSelDTO.setIsProjectionTotal(false);
            projSelDTO.setIsTotal(true);
            projSelDTO.setLevelNo(projSelDTO.getFilterLevelNo());
            projSelDTO.setTreeLevelNo(projSelDTO.getFilterLevelNo());
            resultList = configureLevels(start, offset, projSelDTO);
        }
        return resultList;
    }

    public List<DiscountProjectionResultsDTO> getDPResults(int start, int offset, ProjectionSelectionDTO projSelDTO) {
        int neededRecord = offset;
        int started = start;
        int mayBeAdded = 0;
        List<DiscountProjectionResultsDTO> projDTOList = new ArrayList<>();
        boolean tempCustomFlag = false;

        if (neededRecord > 0 && projSelDTO.getPivotView().contains(PERIOD.getConstant())) {
            DiscountProjectionResultsDTO mandatedDisc = null;
            DiscountProjectionResultsDTO supplDisc = null;
            if (projSelDTO.isIsProjectionTotal() && projSelDTO.isIsTotal()) {
                setProjectionTotalList(getConfiguredResultsTotal(projSelDTO));
                if (!getProjectionTotalList().isEmpty()) {
                    projDTOList.addAll(getProjectionTotalList());
                    neededRecord -= getProjectionTotalList().size();
                }
            }
            String discount = projSelDTO.getMandatedOrSupp();
            if (neededRecord > 0 && projSelDTO.isIsTotal() && !projSelDTO.isIsProjectionTotal()) {
                if (mandatedDisc == null || supplDisc == null) {
                    List<DiscountProjectionResultsDTO> mandOrSupplemental = getMandSuppDiscount(projSelDTO);
                    if (mandOrSupplemental != null && !mandOrSupplemental.isEmpty()) {
                        mandatedDisc = mandOrSupplemental.get(0);
                        supplDisc = mandOrSupplemental.get(1);
                    }

                }
                if (discount.equals(Constant.BOTH)) {
                    boolean toadd = false;
                    if (projSelDTO.isIsProjectionTotal() && start < NumericConstants.FOUR) {
                        toadd = true;
                    } else if (!projSelDTO.isIsProjectionTotal() && start < 1) {
                        toadd = true;
                    } else {
                        toadd = false;
                    }
                    if (toadd && !projSelDTO.isIsProjectionTotal()) {
                        projDTOList.add(mandatedDisc);
                        neededRecord--;
                        started++;
                    }
                    mayBeAdded++;
                }
                if (neededRecord > 0 && (discount.equals(Constant.BOTH) || discount.equals(Constant.SUPPLEMENTAL))) {
                    boolean toadd = false;
                    if (projSelDTO.isIsProjectionTotal() && start < NumericConstants.FIVE) {
                        toadd = true;
                    } else if (!projSelDTO.isIsProjectionTotal() && start < NumericConstants.TWO) {
                        toadd = true;
                    } else {
                        toadd = false;
                    }
                    if (toadd && !projSelDTO.isIsProjectionTotal()) {
                        projDTOList.add(supplDisc);
                        neededRecord--;
                        started++;
                    }
                    mayBeAdded++;
                }
            }

            if (MANDATED_DISCOUNT.getConstant().equals(projSelDTO.getLevelValue()) || SUPPLEMENTAL_DISCOUNT.getConstant().equals(projSelDTO.getLevelValue())) {
                List<DiscountProjectionResultsDTO> programCodeList;
                programCodeList = getProgramCodeDiscount(projSelDTO);
                if (programCodeList != null && !programCodeList.isEmpty()) {
                    for (int k = start; k < programCodeList.size() && neededRecord > 0; neededRecord--, k++) {
                        projDTOList.add(programCodeList.get(k));
                        started++;
                    }
                }
            }
        } else {
            if (projSelDTO.isIsProjectionTotal() && projSelDTO.isIsTotal()) {
                List<DiscountProjectionResultsDTO> projectionDtoList;
                List<DiscountProjectionResultsDTO> totalDTO = new ArrayList<>();
                if (start < 1) {
                    DiscountProjectionResultsDTO dto = new DiscountProjectionResultsDTO();
                    dto.setLevelValue(Constant.PROJECTION_TOTAL);
                    dto.setRelationshipLevelName(Constant.PROJECTION_TOTAL);
                    dto.setParent(0);
                    totalDTO.add(dto);
                    neededRecord--;
                }
                mayBeAdded++;
                projectionDtoList = getConfiguredResultsTotal(projSelDTO);
                int maybeAddedRecord = start - mayBeAdded;
                if (maybeAddedRecord < 0) {
                    maybeAddedRecord = 0;
                }
                for (int k = maybeAddedRecord; k < projectionDtoList.size() && neededRecord > 0; neededRecord--, k++) {
                    totalDTO.add(projectionDtoList.get(k));
                    started++;
                }
                mayBeAdded += projectionDtoList.size();
                projDTOList.addAll(totalDTO);
            }

            List<DiscountProjectionResultsDTO> projectionDtoList = new ArrayList<>();
            if (!projSelDTO.isIsProjectionTotal()) {

                projectionDtoList = getCustomizedPivotChildNodes(projectionDtoList, projSelDTO);
                for (int k = started; k < projectionDtoList.size() && neededRecord > 0; neededRecord--, k++) {
                    projDTOList.add(projectionDtoList.get(k));
                }
                mayBeAdded += projectionDtoList.size();
            }
        }
        if (neededRecord > 0 && projSelDTO.isIsTotal() && !projSelDTO.isIsFilter()) {
            int mayBeAddedRecord = start - mayBeAdded;
            if (mayBeAddedRecord < 0) {
                mayBeAddedRecord = 0;
            }
            if (projSelDTO.isCustomFlag()) {
                projSelDTO.setCustomLevelNo(projSelDTO.getCustomLevelNo() + 1);
                int i = CommonLogic.getIndicatorCount(projSelDTO.getCustomId());
                if (i >= projSelDTO.getCustomLevelNo()) {
                    tempCustomFlag = true;
                }
            }
            if (projSelDTO.isCustomFlag() && tempCustomFlag) {
                String indicator = CommonLogic.getIndicator(projSelDTO.getCustomLevelNo(), projSelDTO.getCustomId());
                projSelDTO.setHierarchyIndicator(indicator);
                projSelDTO.setLevelNo(projSelDTO.getCustomLevelNo());
                projSelDTO.setHierarchyNo(StringUtils.EMPTY);
                projSelDTO.setProductHierarchyNo(StringUtils.EMPTY);
                projSelDTO.setCustomerHierarchyNo(StringUtils.EMPTY);
            }
            if (projSelDTO.isCustomFlag()) {
                if (tempCustomFlag) {
                    List<DiscountProjectionResultsDTO> nextLevelValueList = configureLevels(mayBeAddedRecord, neededRecord, projSelDTO);
                    projDTOList.addAll(nextLevelValueList);
                }
            } else {
                projSelDTO.setLevelNo(projSelDTO.getLevelNo() + 1);
                projSelDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo() + 1);
                List<DiscountProjectionResultsDTO> nextLevelValueList = configureLevels(mayBeAddedRecord, neededRecord, projSelDTO);
                projDTOList.addAll(nextLevelValueList);
            }
        }
        return projDTOList;
    }

    public List<DiscountProjectionResultsDTO> getMandSuppDiscount(ProjectionSelectionDTO projSelDTO) {
        List<Object> list = null;
        List<DiscountProjectionResultsDTO> projDTOList = getCustomizedMandSuppDisc(list, projSelDTO);
        return projDTOList;
    }

    public List<DiscountProjectionResultsDTO> getProgramCodeDiscount(ProjectionSelectionDTO projSelDTO) {
        LOGGER.debug("inside getProgramCodeDiscount :: ");
        List<Object> list;
        Map<Integer, String> freq = new HashMap<>();
        freq.put(1, Constant.YEAR_SPACE);
        freq.put(NumericConstants.TWO, Constant.SEMI_ANNUAL);
        freq.put(NumericConstants.FOUR, Constant.QUARTER);
        freq.put(NumericConstants.TWELVE, Constant.MONTH_SPACE);
        String frequency = freq.get(projSelDTO.getFrequencyDivision());
        String freqChar = frequencyDPR(frequency);
        list = (List<Object>) CommonLogic.executeSelectQuery(getProgramCodeQuery(projSelDTO.getHierarchyNo(), frequency, projSelDTO, freqChar));
        List<DiscountProjectionResultsDTO> projDTOList = getCustomizedPC(list, projSelDTO);
        return projDTOList;
    }

    public List<Object> getProgramCodeName(ProjectionSelectionDTO projSelDTO) {
        List<Object> list;
        list = (List<Object>) CommonLogic.executeSelectQuery(getProgramCodeNameQuery(projSelDTO.getProjectionId()));
        return list;
    }

    public List<DiscountProjectionResultsDTO> getCustomizedMandSuppDisc(List<Object> list, ProjectionSelectionDTO projSelDTO) {
        List<DiscountProjectionResultsDTO> projDtoList = new ArrayList<>();
        DiscountProjectionResultsDTO mandatedDTO = projSelDTO.getMandatedDTO();
        DiscountProjectionResultsDTO suppDTO = projSelDTO.getSupplementalDTO();
        mandatedDTO.setLevelNo(projSelDTO.getLevelNo());
        mandatedDTO.setHierarchyNo(projSelDTO.getHierarchyNo());
        mandatedDTO.setHierarchyIndicator(projSelDTO.getHierarchyIndicator());
        mandatedDTO.setLevelValue(MANDATED_DISCOUNT.getConstant());
        mandatedDTO.setRelationshipLevelName(MANDATED_DISCOUNT.getConstant());
        mandatedDTO.setParentLevelName(projSelDTO.getLevelValue());

        suppDTO.setLevelNo(projSelDTO.getLevelNo());
        suppDTO.setHierarchyNo(projSelDTO.getHierarchyNo());
        suppDTO.setHierarchyIndicator(projSelDTO.getHierarchyIndicator());
        suppDTO.setLevelValue(SUPPLEMENTAL_DISCOUNT.getConstant());
        suppDTO.setRelationshipLevelName(SUPPLEMENTAL_DISCOUNT.getConstant());
        suppDTO.setParentLevelName(projSelDTO.getLevelValue());

        mandatedDTO.setParent(1);
        suppDTO.setParent(1);
        int frequencyDivision;
        if (Constant.QUARTERLY_SMALL.equalsIgnoreCase(projSelDTO.getFrequency())) {
            frequencyDivision = NumericConstants.FOUR;
        } else if (Constant.MONTHLY_SMALL.equalsIgnoreCase(projSelDTO.getFrequency())) {
            frequencyDivision = NumericConstants.TWELVE;
        } else if (Constant.SEMIANNUALLY_SMALL.equalsIgnoreCase(projSelDTO.getFrequency())) {
            frequencyDivision = NumericConstants.TWO;
        } else {
            frequencyDivision = 1;
        }
        String salesOrUnits = projSelDTO.getMandatedOrSupp();
        if (list != null && !list.isEmpty()) {
            boolean actualFlag = false;
            for (Object list1 : list) {
                String columnName;
                final Object[] obj = (Object[]) list1;
                int year = Integer.parseInt(String.valueOf(obj[NumericConstants.TWO]));
                int period = Integer.parseInt(String.valueOf(obj[NumericConstants.THREE]));
                List<String> common = HeaderUtils.getCommonColumnHeader(frequencyDivision, year, period, false);
                String commonColumn = common.get(0);
                if (Constant.MANDATED.equalsIgnoreCase(salesOrUnits) || Constant.BOTH_SMALL.equalsIgnoreCase(salesOrUnits)) {

                    String mandAmt = StringUtils.EMPTY + obj[0];
                    columnName = actualFlag ? Constant.ACTUALS_AMOUNT : Constant.PROJECTIONS_AMOUNT;
                    if (!actualFlag) {
                        mandAmt = CommonUtils.forecastConfigDataHide(projSelDTO.getFrequency(), projSelDTO.getForecastConfigPeriods(), commonColumn, mandAmt);
                    }
                    mandAmt = getFormattedValue(CUR_ZERO, mandAmt);
                    mandatedDTO.addStringProperties(commonColumn + columnName, mandAmt);

                    String mandRate = StringUtils.EMPTY + obj[1];
                    columnName = actualFlag ? Constant.ACTUALS_RATE : Constant.PROJECTIONS_RATE;
                    if (!actualFlag) {
                        mandRate = CommonUtils.forecastConfigDataHide(projSelDTO.getFrequency(), projSelDTO.getForecastConfigPeriods(), commonColumn, mandRate);
                    }
                    mandRate = getFormattedValue(CUR_ZERO, mandRate);
                    mandatedDTO.addStringProperties(commonColumn + columnName, mandRate);

                }
                if (SUPPLEMENTAL.equalsIgnoreCase(salesOrUnits) || Constant.BOTH_SMALL.equalsIgnoreCase(salesOrUnits)) {
                    String actual = StringUtils.EMPTY + obj[0];
                    actual = getFormattedValue(UNITVOLUME, actual);
                    suppDTO.addStringProperties(commonColumn + Constant.ACTUALS, actual);
                    String projection = StringUtils.EMPTY + obj[1];
                    if (!actualFlag) {
                        projection = CommonUtils.forecastConfigDataHide(projSelDTO.getFrequency(), projSelDTO.getForecastConfigPeriods(), commonColumn, projection);
                    }
                    projection = getFormattedValue(UNITVOLUME, projection);
                    suppDTO.addStringProperties(commonColumn + Constant.PROJECTIONS, projection);

                }
            }

        }
        projDtoList.add(mandatedDTO);
        projDtoList.add(suppDTO);
        return projDtoList;
    }

    public List<DiscountProjectionResultsDTO> getCustomizedPC(List<Object> list, ProjectionSelectionDTO projSelDTO) {
        List<DiscountProjectionResultsDTO> pcDTO = new ArrayList<>();
        if (list != null && !list.isEmpty()) {
            int frequencyDivision = projSelDTO.getFrequencyDivision();
            DiscountProjectionResultsDTO contractDTO = null;
            boolean actualFlag = false;
            String lastValue = StringUtils.EMPTY;
            String tempAnnual = StringUtils.EMPTY;
            double annualMandamt = 0.0;
            double annualMandrate = 0.0;
            double annualMandrpu = 0.0;
            double annualMandProjamt = 0.0;
            double annualMandProjrate = 0.0;
            double annualMandProjrpu = 0.0;
            double annualSuppamt = 0.0;
            double annualSupprate = 0.0;
            double annualSupprpu = 0.0;
            double annualSuppProjamt = 0.0;
            double annualSuppProjrate = 0.0;
            double annualSuppProjrpu = 0.0;
            List<String> annualTotal = new ArrayList<>();

            List<Object> rightHeaderColumns = projSelDTO.getRightHeaderDoubleColumns();
            for (int i = 0; i < list.size(); i++) {
                String columnName;
                final Object[] obj = (Object[]) list.get(i);
                if (lastValue.isEmpty() || !lastValue.equalsIgnoreCase(String.valueOf(obj[NumericConstants.TWO]))) {
                    if (contractDTO != null) {
                        contractDTO.setLevelNo(projSelDTO.getLevelNo());
                        contractDTO.setHierarchyNo(projSelDTO.getHierarchyNo());
                        contractDTO.setHierarchyIndicator(projSelDTO.getHierarchyIndicator());
                        contractDTO.setLevelValue(lastValue);
                        contractDTO.setRelationshipLevelName(contractDTO.getLevelValue());
                        contractDTO.setParentLevelName(projSelDTO.getLevelValue());
                        contractDTO.setParent(0);
                        pcDTO.add(contractDTO);
                    }
                    contractDTO = new DiscountProjectionResultsDTO();
                }
                actualFlag = Integer.parseInt(String.valueOf(obj[NumericConstants.ELEVEN])) == 0;
                lastValue = String.valueOf(obj[NumericConstants.TWO]);
                int year = Integer.parseInt(String.valueOf(obj[NumericConstants.FOUR]));
                int period = projSelDTO.getFrequencyDivision() == 1 ? 0 : Integer.parseInt(String.valueOf(obj[NumericConstants.TWELVE]));
                String annualColumn = StringUtils.EMPTY;
                if (!ANNUALLY.equalsIgnoreCase(projSelDTO.getFrequency())) {
                    annualTotal = HeaderUtils.getCommonColumnHeader(1, year, 0, false);
                }
                if (annualTotal != null && !annualTotal.isEmpty() && !tempAnnual.equals(annualTotal.get(0))) {
                    annualMandamt = 0.0;
                    annualMandrate = 0.0;
                    annualMandrpu = 0.0;
                    annualMandProjamt = 0.0;
                    annualMandProjrate = 0.0;
                    annualMandProjrpu = 0.0;
                    annualSuppamt = 0.0;
                    annualSupprate = 0.0;
                    annualSupprpu = 0.0;
                    annualSuppProjamt = 0.0;
                    annualSuppProjrate = 0.0;
                    annualSuppProjrpu = 0.0;
                }
                if (annualTotal != null && !annualTotal.isEmpty()) {
                    annualColumn = annualTotal.get(0);
                    tempAnnual = annualColumn;
                }
                List<String> common = HeaderUtils.getCommonColumnHeader(frequencyDivision, year, period, false);
                String commonColumn = common.get(0);

                if (MANDATED_DISCOUNT.getConstant().equals(projSelDTO.getLevelValue())) {
                    String mandAmt = StringUtils.EMPTY + obj[NumericConstants.FIVE];
                    String mandRate = StringUtils.EMPTY + obj[NumericConstants.SIX];
                    String mandRPU = StringUtils.EMPTY + obj[NumericConstants.NINE];

                    if (!actualFlag) {
                        mandAmt = CommonUtils.forecastConfigDataHide(projSelDTO.getFrequency(), projSelDTO.getForecastConfigPeriods(), commonColumn, mandAmt);
                        mandRate = CommonUtils.forecastConfigDataHide(projSelDTO.getFrequency(), projSelDTO.getForecastConfigPeriods(), commonColumn, mandRate);
                        mandRPU = CommonUtils.forecastConfigDataHide(projSelDTO.getFrequency(), projSelDTO.getForecastConfigPeriods(), commonColumn, mandRPU);
                    }

                    mandAmt = getFormatValue(NumericConstants.TWO, mandAmt, CURRENCY);
                    columnName = actualFlag ? Constant.ACTUALS_AMOUNT : Constant.PROJECTIONS_AMOUNT;
                    contractDTO.addStringProperties(commonColumn + columnName, mandAmt);

                    mandRate = getFormatValue(NumericConstants.TWO, mandRate, PERCENTAGE);
                    columnName = actualFlag ? Constant.ACTUALS_RATE : Constant.PROJECTIONS_RATE;
                    contractDTO.addStringProperties(commonColumn + columnName, mandRate);

                    mandRPU = getFormatValue(NumericConstants.TWO, mandRPU, CURRENCY);
                    columnName = actualFlag ? Constant.ACTUALS_RPU : Constant.PROJECTIONS_RPU;
                    contractDTO.addStringProperties(commonColumn + columnName, mandRPU);

                    if (!annualColumn.equals(StringUtils.EMPTY) && commonColumn.contains(annualColumn) && rightHeaderColumns.toString().contains(commonColumn)) {
                        if (actualFlag) {
                            annualMandamt = annualMandamt + (obj[NumericConstants.FIVE] != null ? Double.parseDouble(String.valueOf(obj[NumericConstants.FIVE])) : 0);
                            mandAmt = getFormatValue(NumericConstants.TWO, String.valueOf(annualMandamt), CURRENCY);
                            annualMandrate = annualMandrate + (obj[NumericConstants.SIX] != null ? Double.parseDouble(String.valueOf(obj[NumericConstants.SIX])) : 0);
                            mandRate = getFormatValue(NumericConstants.TWO, String.valueOf(annualMandrate), PERCENTAGE);
                            annualMandrpu = annualMandrpu + (obj[NumericConstants.NINE] != null ? Double.parseDouble(String.valueOf(obj[NumericConstants.NINE])) : 0);
                            mandRPU = getFormatValue(NumericConstants.TWO, String.valueOf(annualMandrpu), CURRENCY);
                        } else {
                            annualMandProjamt = annualMandProjamt + (obj[NumericConstants.FIVE] != null ? Double.parseDouble(String.valueOf(obj[NumericConstants.FIVE])) : 0);
                            mandAmt = getFormatValue(NumericConstants.TWO, String.valueOf(annualMandProjamt), CURRENCY);
                            annualMandProjrate = annualMandProjrate + (obj[NumericConstants.SIX] != null ? Double.parseDouble(String.valueOf(obj[NumericConstants.SIX])) : 0);
                            mandRate = getFormatValue(NumericConstants.TWO, String.valueOf(annualMandProjrate), PERCENTAGE);
                            annualMandProjrpu = annualMandProjrpu + (obj[NumericConstants.NINE] != null ? Double.parseDouble(String.valueOf(obj[NumericConstants.NINE])) : 0);
                            mandRPU = getFormatValue(NumericConstants.TWO, String.valueOf(annualMandProjrpu), CURRENCY);
                        }
                        if (!actualFlag) {
                            mandAmt = CommonUtils.forecastConfigDataHide(projSelDTO.getFrequency(), projSelDTO.getForecastConfigPeriods(), commonColumn, mandAmt);
                            mandRate = CommonUtils.forecastConfigDataHide(projSelDTO.getFrequency(), projSelDTO.getForecastConfigPeriods(), commonColumn, mandRate);
                            mandRPU = CommonUtils.forecastConfigDataHide(projSelDTO.getFrequency(), projSelDTO.getForecastConfigPeriods(), commonColumn, mandRPU);
                        }

                        columnName = actualFlag ? Constant.ACTUALS_AMOUNT : Constant.PROJECTIONS_AMOUNT;
                        contractDTO.addStringProperties(annualColumn + columnName, mandAmt);

                        columnName = actualFlag ? Constant.ACTUALS_RATE : Constant.PROJECTIONS_RATE;
                        contractDTO.addStringProperties(annualColumn + columnName, mandRate);

                        columnName = actualFlag ? Constant.ACTUALS_RPU : Constant.PROJECTIONS_RPU;
                        contractDTO.addStringProperties(annualColumn + columnName, mandRPU);
                    }
                } else if (SUPPLEMENTAL_DISCOUNT.getConstant().equals(projSelDTO.getLevelValue())) {
                    String suppAmt = StringUtils.EMPTY + obj[NumericConstants.SEVEN];
                    String suppRate = StringUtils.EMPTY + obj[NumericConstants.EIGHT];
                    String suppRPU = StringUtils.EMPTY + obj[NumericConstants.TEN];

                    if (!actualFlag) {
                        suppAmt = CommonUtils.forecastConfigDataHide(projSelDTO.getFrequency(), projSelDTO.getForecastConfigPeriods(), commonColumn, suppAmt);
                        suppRate = CommonUtils.forecastConfigDataHide(projSelDTO.getFrequency(), projSelDTO.getForecastConfigPeriods(), commonColumn, suppRate);
                        suppRPU = CommonUtils.forecastConfigDataHide(projSelDTO.getFrequency(), projSelDTO.getForecastConfigPeriods(), commonColumn, suppRPU);
                    }

                    suppAmt = getFormatValue(NumericConstants.TWO, suppAmt, CURRENCY);
                    columnName = actualFlag ? Constant.ACTUALS_AMOUNT : Constant.PROJECTIONS_AMOUNT;
                    contractDTO.addStringProperties(commonColumn + columnName, suppAmt);

                    suppRate = getFormatValue(NumericConstants.TWO, suppRate, PERCENTAGE);
                    columnName = actualFlag ? Constant.ACTUALS_RATE : Constant.PROJECTIONS_RATE;
                    contractDTO.addStringProperties(commonColumn + columnName, suppRate);

                    suppRPU = getFormatValue(NumericConstants.TWO, suppRPU, CURRENCY);
                    columnName = actualFlag ? Constant.ACTUALS_RPU : Constant.PROJECTIONS_RPU;
                    contractDTO.addStringProperties(commonColumn + columnName, suppRPU);

                    if (!annualColumn.equals(StringUtils.EMPTY) && commonColumn.contains(annualColumn) && rightHeaderColumns.toString().contains(commonColumn)) {
                        if (actualFlag) {
                            annualSuppamt = annualSuppamt + (obj[NumericConstants.SEVEN] != null ? Double.parseDouble(String.valueOf(obj[NumericConstants.SEVEN])) : 0);
                            suppAmt = getFormatValue(NumericConstants.TWO, String.valueOf(annualSuppamt), CURRENCY);
                            annualSupprate = annualSupprate + (obj[NumericConstants.EIGHT] != null ? Double.parseDouble(String.valueOf(obj[NumericConstants.EIGHT])) : 0);
                            suppRate = getFormatValue(NumericConstants.TWO, String.valueOf(annualSupprate), PERCENTAGE);
                            annualSupprpu = annualSupprpu + (obj[NumericConstants.TEN] != null ? Double.parseDouble(String.valueOf(obj[NumericConstants.TEN])) : 0);
                            suppRPU = getFormatValue(NumericConstants.TWO, String.valueOf(annualSupprpu), CURRENCY);
                        } else {
                            annualSuppProjamt = annualSuppProjamt + (obj[NumericConstants.SEVEN] != null ? Double.parseDouble(String.valueOf(obj[NumericConstants.SEVEN])) : 0);
                            suppAmt = getFormatValue(NumericConstants.TWO, String.valueOf(annualSuppProjamt), CURRENCY);
                            annualSuppProjrate = annualSuppProjrate + (obj[NumericConstants.EIGHT] != null ? Double.parseDouble(String.valueOf(obj[NumericConstants.EIGHT])) : 0);
                            suppRate = getFormatValue(NumericConstants.TWO, String.valueOf(annualSuppProjrate), PERCENTAGE);
                            annualSuppProjrpu = annualSuppProjrpu + (obj[NumericConstants.TEN] != null ? Double.parseDouble(String.valueOf(obj[NumericConstants.TEN])) : 0);
                            suppRPU = getFormatValue(NumericConstants.TWO, String.valueOf(annualSuppProjrpu), CURRENCY);
                        }
                        if (!actualFlag) {
                            suppAmt = CommonUtils.forecastConfigDataHide(projSelDTO.getFrequency(), projSelDTO.getForecastConfigPeriods(), commonColumn, suppAmt);
                            suppRate = CommonUtils.forecastConfigDataHide(projSelDTO.getFrequency(), projSelDTO.getForecastConfigPeriods(), commonColumn, suppRate);
                            suppRPU = CommonUtils.forecastConfigDataHide(projSelDTO.getFrequency(), projSelDTO.getForecastConfigPeriods(), commonColumn, suppRPU);
                        }

                        columnName = actualFlag ? Constant.ACTUALS_AMOUNT : Constant.PROJECTIONS_AMOUNT;
                        contractDTO.addStringProperties(annualColumn + columnName, suppAmt);

                        columnName = actualFlag ? Constant.ACTUALS_RATE : Constant.PROJECTIONS_RATE;
                        contractDTO.addStringProperties(annualColumn + columnName, suppRate);
                        columnName = actualFlag ? Constant.ACTUALS_RPU : Constant.PROJECTIONS_RPU;
                        contractDTO.addStringProperties(annualColumn + columnName, suppRPU);
                    }
                }
                if (i == list.size() - 1) {
                    contractDTO.setLevelNo(projSelDTO.getLevelNo());
                    contractDTO.setHierarchyNo(projSelDTO.getHierarchyNo());
                    contractDTO.setHierarchyIndicator(projSelDTO.getHierarchyIndicator());
                    contractDTO.setLevelValue(lastValue);
                    contractDTO.setRelationshipLevelName(contractDTO.getLevelValue());
                    contractDTO.setParentLevelName(projSelDTO.getLevelValue());
                    contractDTO.setParent(0);
                    pcDTO.add(contractDTO);
                }
            }
        } else {
            for (String value : projSelDTO.getProgramCodeList()) {
                DiscountProjectionResultsDTO contractDTO = new DiscountProjectionResultsDTO();
                contractDTO.setLevelNo(projSelDTO.getLevelNo());
                contractDTO.setHierarchyNo(projSelDTO.getHierarchyNo());
                contractDTO.setHierarchyIndicator(projSelDTO.getHierarchyIndicator());
                contractDTO.setLevelValue(value);
                contractDTO.setRelationshipLevelName(contractDTO.getLevelValue());
                contractDTO.setParentLevelName(projSelDTO.getLevelValue());
                contractDTO.setParent(0);
                pcDTO.add(contractDTO);

            }
        }

        return pcDTO;
    }

    public int getConfiguredDPResultsCount(Object parentId, ProjectionSelectionDTO projSelDTO, boolean isLevelCount, ProjectionSelectionDTO initialProjSelDTO) {
        int count = 0;
        if (!projSelDTO.isIsFilter() || (parentId instanceof DiscountProjectionResultsDTO)) {
            projSelDTO.setYear(Constant.ALL);

            if (projSelDTO.getActualsOrProjections().equals(Constant.BOTH)) {
                projSelDTO.setActualsOrProjections("Actuals and Projections");
            }
            if (parentId instanceof DiscountProjectionResultsDTO) {
                DiscountProjectionResultsDTO parentDto = (DiscountProjectionResultsDTO) parentId;
                projSelDTO.setLevelNo(parentDto.getLevelNo());
                projSelDTO.setTreeLevelNo(parentDto.getTreeLevelNo());
                projSelDTO.setLevelValue(parentDto.getLevelValue());
                projSelDTO.setRelationshipLevelName(parentDto.getRelationshipLevelName());
                projSelDTO.setParentNode(parentDto.getParentNode());
                projSelDTO.setHierarchyNo(parentDto.getHierarchyNo());
                projSelDTO.setCustomLevelNo(parentDto.getCustomLevelNo());
                if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equalsIgnoreCase(parentDto.getHierarchyIndicator())) {
                    projSelDTO.setCustomerHierarchyNo(projSelDTO.getHierarchyNo());
                    projSelDTO.setProductHierarchyNo(parentDto.getProductHierarchyNo());
                } else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equalsIgnoreCase(parentDto.getHierarchyIndicator())) {
                    projSelDTO.setProductHierarchyNo(projSelDTO.getHierarchyNo());
                    projSelDTO.setCustomerHierarchyNo(parentDto.getCustomerHierarchyNo());
                }
                projSelDTO.setHierarchyIndicator(parentDto.getHierarchyIndicator());
                projSelDTO.setIsProjectionTotal(parentDto.getProjectionTotal() == 1);
                projSelDTO.setGroup(parentDto.getGroup());
                projSelDTO.setIsTotal(parentDto.getOnExpandTotalRow() == 1);
            } else {
                projSelDTO.setLevelValue(StringUtils.EMPTY);
                projSelDTO.setIsProjectionTotal(true);
                projSelDTO.setIsTotal(true);
                projSelDTO.setHierarchyIndicator(initialProjSelDTO.getHierarchyIndicator());
                if (projSelDTO.isCustomFlag()) {
                    String indicator = CommonLogic.getIndicator(1, projSelDTO.getCustomId());
                    projSelDTO.setHierarchyIndicator(indicator);
                    projSelDTO.setLevelNo(0);
                    projSelDTO.setCustomLevelNo(0);
                    projSelDTO.setTreeLevelNo(0);
                } else if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(projSelDTO.getHierarchyIndicator())) {
                    projSelDTO.setLevelNo(initialProjSelDTO.getCustomerLevelNo() - 1);
                    projSelDTO.setTreeLevelNo(initialProjSelDTO.getCustomerLevelNo() - 1);
                } else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(projSelDTO.getHierarchyIndicator())) {
                    projSelDTO.setLevelNo(initialProjSelDTO.getProductLevelNo() - 1);
                    projSelDTO.setTreeLevelNo(initialProjSelDTO.getProductLevelNo() - 1);
                } else {
                    projSelDTO.setLevelNo(0);
                }
                projSelDTO.setGroup(StringUtils.EMPTY);
                projSelDTO.setHierarchyNo(StringUtils.EMPTY);
                projSelDTO.setProductHierarchyNo(StringUtils.EMPTY);
                projSelDTO.setCustomerHierarchyNo(StringUtils.EMPTY);
            }
            count += getProjectionResultsCount(projSelDTO, isLevelCount);
        } else if (isLevelCount) {
            projSelDTO.setIsProjectionTotal(false);
            projSelDTO.setIsTotal(true);
            projSelDTO.setLevelNo(projSelDTO.getFilterLevelNo());
            count += configureLevelsCount(projSelDTO);
        }
        return count;
    }

    public int getProjectionResultsCount(ProjectionSelectionDTO projSelDTO, boolean isLevelCount) {
        projSelDTO.getProgramCodeList().clear();
        int count = 0;
        //configureLevelsCount and configureLevelsCountMethodCalled  used to  reduce unwanted query Hits
        int configureLevelsCount = 0;
        boolean configureLevelsCountMethodCalled = false;

        boolean tempCustomFlag = false;
        boolean mdFlag = false;
        if (projSelDTO.getPivotView().contains(PERIOD.getConstant())) {
            String salesOrUnit = projSelDTO.getMandatedOrSupp();
            count += projSelDTO.isIsProjectionTotal() == true ? 1 : 0;

            if (MANDATED_DISCOUNT.getConstant().equals(projSelDTO.getLevelValue()) || SUPPLEMENTAL_DISCOUNT.getConstant().equals(projSelDTO.getLevelValue())) {
                List<Object> list;
                list = getProgramCodeCount(projSelDTO.getProjectionId(), projSelDTO.getHierarchyNo());
                if (list != null && !list.isEmpty()) {
                    for (Object object : list) {
                        projSelDTO.getProgramCodeList().add(String.valueOf(object));
                    }
                    count = count + list.size();
                }
                mdFlag = true;
            } else if ((Constant.SUPPLEMENTAL).equalsIgnoreCase(salesOrUnit)) {
                count += 1;
            } else {
                count += NumericConstants.TWO;
            }

        } else if (projSelDTO.isIsProjectionTotal()) {
            count = count + 1 + projSelDTO.getPeriodList().size();
        } else {
            count = count + projSelDTO.getPeriodList().size();
        }
        if (projSelDTO.isIsTotal() && isLevelCount && !projSelDTO.isIsFilter()) {
            if (projSelDTO.isCustomFlag()) {
                projSelDTO.setCustomLevelNo(projSelDTO.getCustomLevelNo() + 1);
                int i = CommonLogic.getIndicatorCount(projSelDTO.getCustomId());
                if (i >= projSelDTO.getCustomLevelNo()) {
                    tempCustomFlag = true;
                }
            }
            if (projSelDTO.isCustomFlag() && tempCustomFlag) {
                String indicator = CommonLogic.getIndicator(projSelDTO.getCustomLevelNo(), projSelDTO.getCustomId());
                projSelDTO.setHierarchyIndicator(indicator);
                projSelDTO.setLevelNo(projSelDTO.getCustomLevelNo());
                projSelDTO.setTreeLevelNo(projSelDTO.getCustomLevelNo());
                projSelDTO.setHierarchyNo(StringUtils.EMPTY);
                projSelDTO.setProductHierarchyNo(StringUtils.EMPTY);
                projSelDTO.setCustomerHierarchyNo(StringUtils.EMPTY);
            }
            if (projSelDTO.isCustomFlag()) {
                if (tempCustomFlag) {
                    configureLevelsCount = configureLevelsCount(projSelDTO);
                    configureLevelsCountMethodCalled = true;
                    count = count + configureLevelsCount;
                }
            } else {
                projSelDTO.setLevelNo(projSelDTO.getLevelNo() + 1);
                projSelDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo() + 1);
                configureLevelsCount = configureLevelsCount(projSelDTO);
                configureLevelsCountMethodCalled = true;
                count = count + configureLevelsCount;
            }
        } else if (!mdFlag && !projSelDTO.isIsFilter()) {
            int levelNo = projSelDTO.getLevelNo();
            projSelDTO.setLevelNo(levelNo);
            configureLevelsCount = configureLevelsCount(projSelDTO);
            configureLevelsCountMethodCalled = true;
            count = count + configureLevelsCount;

        }
        // If configureLevelsCountMethod is Called then taking  count from variable configureLevelsCount and no need to hit DB
        projSelDTO.setLevelCount(configureLevelsCountMethodCalled ? configureLevelsCount : configureLevelsCount(projSelDTO));
        return count;
    }

    public List<DiscountProjectionResultsDTO> configureLevels(int start, int offset, ProjectionSelectionDTO projSelDTO) {
        if (projSelDTO.getTreeLevelNo() == 0) {
            projSelDTO.setTreeLevelNo(projSelDTO.getLevelNo() + 1);
        }
        int neededRecord = offset;
        CommonLogic comm = new CommonLogic();
        List<DiscountProjectionResultsDTO> resultList = new ArrayList<>();
        Map<String, List> levelMap = null;
        if (projSelDTO.isCustomFlag()) {
            projSelDTO.setHierarchyIndicator(comm.getHiearchyIndicatorFromCustomView(projSelDTO));
        }
        levelMap = projSelDTO.getSessionDTO().getHierarchyLevelDetails();
        if (projSelDTO.isCustomFlag()) {
            projSelDTO.setTreeLevelNo(projSelDTO.getCustomLevelNo());
            List obj = comm.getHiearchyNoForCustomView(projSelDTO, start, offset);
            for (Object object : obj) {
                String hierarchyNo = String.valueOf(object);

                if (levelMap.containsKey(hierarchyNo)) {
                    List levelValues = levelMap.get(object);
                    Integer levelNo = Integer.valueOf((String) levelValues.get(2));
                    DiscountProjectionResultsDTO dto = new DiscountProjectionResultsDTO();

                    dto.setLevelNo(levelNo);
                    dto.setTreeLevelNo(projSelDTO.getTreeLevelNo());
                    dto.setCustomLevelNo(projSelDTO.getCustomLevelNo());
                    dto.setGroup(String.valueOf(levelValues.get(0)));
                    dto.setLevelValue(String.valueOf(levelValues.get(0)));
                    dto.setHierarchyNo(hierarchyNo);
                    dto.setRelationshipLevelName(String.valueOf(levelValues.get(0)));
                    dto.setHierarchyIndicator(projSelDTO.getHierarchyIndicator());
                    if (projSelDTO.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
                        dto.setCustomerHierarchyNo(dto.getHierarchyNo());
                        dto.setProductHierarchyNo(projSelDTO.getProductHierarchyNo());
                    } else if (projSelDTO.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
                        dto.setProductHierarchyNo(dto.getHierarchyNo());
                        dto.setCustomerHierarchyNo(projSelDTO.getCustomerHierarchyNo());
                    }
                    if (Constant.PERIOD.equals(projSelDTO.getPivotView())) {
                        dto = getChildNodeValues(dto, projSelDTO);
                    } else {
                        dto = getPivotChildNodeValues(dto, projSelDTO);
                    }
                    dto.setOnExpandTotalRow(1);
                    dto.setParent(1);
                    resultList.add(dto);
                    neededRecord--;
                }
            }
        } else {
            if (neededRecord > 0) {
                List<String> hierarchyNoList = comm.getHiearchyNoAsList(projSelDTO, start, offset);
                Map<String, List> relationshipLevelDetailsMap = projSelDTO.getSessionDTO().getHierarchyLevelDetails();

                for (int i = 0; i < hierarchyNoList.size() && neededRecord > 0; neededRecord--, i++) {
                    DiscountProjectionResultsDTO dto = new DiscountProjectionResultsDTO();
                    dto.setLevelNo(Integer.valueOf(relationshipLevelDetailsMap.get(hierarchyNoList.get(i)).get(2).toString()));
                    dto.setTreeLevelNo(dto.getLevelNo());
                    dto.setGroup(projSelDTO.getSessionDTO().getLevelValueDiscription(hierarchyNoList.get(i), projSelDTO.getHierarchyIndicator()));
                    dto.setLevelValue(projSelDTO.getSessionDTO().getLevelValueDiscription(hierarchyNoList.get(i), projSelDTO.getHierarchyIndicator()));
                    dto.setHierarchyNo(hierarchyNoList.get(i));
                    dto.setHierarchyIndicator(projSelDTO.getHierarchyIndicator());
                    dto.setRelationshipLevelName(projSelDTO.getSessionDTO().getLevelValueDiscription(dto.getHierarchyNo(), dto.getHierarchyIndicator()));
                    if (dto.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
                        dto.setCustomerHierarchyNo(dto.getHierarchyNo());
                        dto.setProductHierarchyNo(projSelDTO.getProductHierarchyNo());
                    } else if (dto.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
                        dto.setProductHierarchyNo(dto.getHierarchyNo());
                        dto.setCustomerHierarchyNo(projSelDTO.getCustomerHierarchyNo());
                    }
                    if (Constant.PERIOD.equals(projSelDTO.getPivotView())) {
                        dto = getChildNodeValues(dto, projSelDTO);
                    } else {
                        dto = getPivotChildNodeValues(dto, projSelDTO);
                    }
                    dto.setCustomLevelNo(projSelDTO.getCustomLevelNo());
                    dto.setOnExpandTotalRow(1);
                    dto.setParent(1);
                    resultList.add(dto);
                }
            }
        }
        return resultList;
    }

    public int configureLevelsCount(ProjectionSelectionDTO projSelDTO) {
        int levelCount = 0;
        CommonLogic commonLogic = new CommonLogic();
        if (projSelDTO.getTreeLevelNo() == 0) {
            projSelDTO.setTreeLevelNo(projSelDTO.getLevelNo() + 1);
        }
        if (projSelDTO.isCustomFlag()) {
            projSelDTO.setTreeLevelNo(projSelDTO.getCustomLevelNo());
            levelCount = commonLogic.getCountForCustomView(projSelDTO);
        } else {
            levelCount = commonLogic.getCount(projSelDTO);
        }

        projSelDTO.setLevelCount(levelCount);
        return levelCount;
    }

    public List<DiscountProjectionResultsDTO> getProjectionTotal(Object[] orderedArgs, ProjectionSelectionDTO projSelDTO) {
        List<DiscountProjectionResultsDTO> projDTOList = new ArrayList<>();
        List<Object[]> gtsList;
        boolean viewFlag = ACTION_VIEW.getConstant().equalsIgnoreCase(projSelDTO.getSessionDTO().getAction());
        // Procedure called only in  Tab Change
        if (projSelDTO.getSessionDTO().isDprRefreshReqd() || !CommonLogic.checkProcedureInputIsSame(orderedArgs, dprOrderedArgs)) {
            if (viewFlag) {
                orderedArgs = ArrayUtils.removeElement(orderedArgs, projSelDTO.getUserId());
                orderedArgs = ArrayUtils.removeElement(orderedArgs, projSelDTO.getSessionDTO().getSessionId());
            }
            gtsList = CommonLogic.callProcedure(viewFlag ? "PRC_M_DISCOUNT_PROJ_TOTAL_VIEW" : "PRC_M_DISCOUNT_PROJ_TOTAL", orderedArgs);
            dprOrderedArgs = new Object[orderedArgs.length];
            System.arraycopy(orderedArgs, 0, dprOrderedArgs, 0, orderedArgs.length);
            totalPrcResultList.clear();
            totalPrcResultList.addAll(gtsList);
        } else {
            gtsList = totalPrcResultList;
        }
        projSelDTO.getSessionDTO().setDprRefreshReqd(false);
        if (gtsList != null) {
            projDTOList = getCustomizedProjectionTotal(gtsList, projSelDTO);
        }

        return projDTOList;
    }

    public List<DiscountProjectionResultsDTO> getProjectionPivotTotal(Object[] orderedArgs, ProjectionSelectionDTO projSelDTO) {
        List<DiscountProjectionResultsDTO> projDTOList;
        List<Object[]> gtsList;
        boolean viewFlag = ACTION_VIEW.getConstant().equalsIgnoreCase(projSelDTO.getSessionDTO().getAction());
        // Procedure called only in  Tab Change
        if (projSelDTO.getSessionDTO().isDprRefreshReqd() || !CommonLogic.checkProcedureInputIsSame(orderedArgs, dprOrderedArgs)) {
            if (viewFlag) {
                orderedArgs = ArrayUtils.removeElement(orderedArgs, projSelDTO.getUserId());
                orderedArgs = ArrayUtils.removeElement(orderedArgs, projSelDTO.getSessionDTO().getSessionId());
            }
            gtsList = CommonLogic.callProcedure(viewFlag ? "PRC_M_DISCOUNT_PROJ_TOTAL_VIEW" : "PRC_M_DISCOUNT_PROJ_TOTAL", orderedArgs);
            dprOrderedArgs = new Object[orderedArgs.length];
            System.arraycopy(orderedArgs, 0, dprOrderedArgs, 0, orderedArgs.length);
            totalPrcResultList.clear();
            totalPrcResultList.addAll(gtsList);
        } else {
            gtsList = totalPrcResultList;
        }
        projSelDTO.getSessionDTO().setDprRefreshReqd(false);
        projDTOList = getCustomizedProjectionPivotTotal(gtsList, projSelDTO);
        if (projSelDTO.getProjectionOrder().equalsIgnoreCase(Constant.DESCENDING)) {
            Collections.reverse(projDTOList);
        }
        return projDTOList;
    }

    public List<DiscountProjectionResultsDTO> getCustomizedProjectionTotal(List<Object[]> list, ProjectionSelectionDTO projSelDTO) {
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        String mandSupp = projSelDTO.getMandatedOrSupp();
        String freq = projSelDTO.getFrequency();
        List<DiscountProjectionResultsDTO> projDTOList = new ArrayList<>();
        DiscountProjectionResultsDTO projTotal = new DiscountProjectionResultsDTO();
        DiscountProjectionResultsDTO mandatedDTO = new DiscountProjectionResultsDTO();
        DiscountProjectionResultsDTO supplDTO = new DiscountProjectionResultsDTO();

        projTotal.setParent(0);
        projTotal.setLevelValue(Constant.PROJECTION_TOTAL);
        projTotal.setRelationshipLevelName(Constant.PROJECTION_TOTAL);

        mandatedDTO.setParent(0);
        mandatedDTO.setLevelValue(MANDATED_DISCOUNT.getConstant());
        mandatedDTO.setRelationshipLevelName(MANDATED_DISCOUNT.getConstant());

        supplDTO.setParent(0);
        supplDTO.setLevelValue(SUPPLEMENTAL_DISCOUNT.getConstant());
        supplDTO.setRelationshipLevelName(SUPPLEMENTAL_DISCOUNT.getConstant());
        double amt = 0.0;
        double rate = 0.0;
        double rpu = 0.0;
        double projamt = 0.0;
        double projrate = 0.0;
        double projrpu = 0.0;
        double annualMandamt = 0.0;
        double annualMandrate = 0.0;
        double annualMandrpu = 0.0;
        double annualMandProjamt = 0.0;
        double annualMandProjrate = 0.0;
        double annualMandProjrpu = 0.0;
        double annualSuppamt = 0.0;
        double annualSupprate = 0.0;
        double annualSupprpu = 0.0;
        double annualSuppProjamt = 0.0;
        double annualSuppProjrate = 0.0;
        double annualSuppProjrpu = 0.0;
        String tempAnnualColumn = StringUtils.EMPTY;
        List<Object> rightHeaderColumns = projSelDTO.getRightHeaderDoubleColumns();
        List<String> annualTotal = new ArrayList<>();

        if (list != null && !list.isEmpty()) {
            boolean annualFlag = false;
            boolean actualFlag = false;
            if (ANNUALLY.equalsIgnoreCase(freq)) {
                annualFlag = true;
            }
            for (Object list1 : list) {
                final Object[] obj = (Object[]) list1;
                String type;
                String columnName;
                if (annualFlag) {
                    type = String.valueOf(obj[NumericConstants.TEN]);
                } else {
                    type = String.valueOf(obj[NumericConstants.ELEVEN]);
                }
                int year = annualFlag ? Integer.parseInt(String.valueOf(obj[NumericConstants.NINE])) : Integer.parseInt(String.valueOf(obj[NumericConstants.TEN]));
                int period = annualFlag ? 0 : Integer.parseInt(String.valueOf(obj[NumericConstants.NINE]));
                String annualColumn = StringUtils.EMPTY;
                if (!annualFlag) {
                    annualTotal = HeaderUtils.getCommonColumnHeader(1, year, 0, false);
                }
                if (annualTotal != null && !annualTotal.isEmpty() && !tempAnnualColumn.equals(annualTotal.get(0))) {
                    amt = 0.0;
                    rate = 0.0;
                    rpu = 0.0;
                    annualMandamt = 0.0;
                    annualMandrate = 0.0;
                    annualMandrpu = 0.0;
                    annualSuppamt = 0.0;
                    annualSupprate = 0.0;
                    annualSupprpu = 0.0;
                    projamt = 0.0;
                    projrate = 0.0;
                    projrpu = 0.0;
                    annualMandProjamt = 0.0;
                    annualMandProjrate = 0.0;
                    annualMandProjrpu = 0.0;
                    annualSuppProjamt = 0.0;
                    annualSuppProjrate = 0.0;
                    annualSuppProjrpu = 0.0;
                }
                if (annualTotal != null && !annualTotal.isEmpty()) {
                    annualColumn = annualTotal.get(0);
                    tempAnnualColumn = annualColumn;
                }
                List<String> common = HeaderUtils.getCommonColumnHeader(frequencyDivision, year, period, false);
                String commonColumn = common.get(0);
                if ("Actual".equalsIgnoreCase(type)) {
                    actualFlag = true;
                } else {
                    actualFlag = false;
                }
                String totalAmt = StringUtils.EMPTY + obj[NumericConstants.SIX];
                String totalRate = StringUtils.EMPTY + obj[NumericConstants.SEVEN];
                String totalRPU = StringUtils.EMPTY + obj[NumericConstants.EIGHT];

                if (!actualFlag) {
                    totalAmt = CommonUtils.forecastConfigDataHide(freq, projSelDTO.getForecastConfigPeriods(), commonColumn, totalAmt);
                    totalRate = CommonUtils.forecastConfigDataHide(freq, projSelDTO.getForecastConfigPeriods(), commonColumn, totalRate);
                    totalRPU = CommonUtils.forecastConfigDataHide(freq, projSelDTO.getForecastConfigPeriods(), commonColumn, totalRPU);
                }

                totalAmt = getFormatValue(NumericConstants.TWO, totalAmt, CURRENCY);
                columnName = actualFlag ? Constant.ACTUALS_AMOUNT : Constant.PROJECTIONS_AMOUNT;
                projTotal.addStringProperties(commonColumn + columnName, totalAmt);

                totalRate = getFormatValue(NumericConstants.TWO, totalRate, PERCENTAGE);
                columnName = actualFlag ? Constant.ACTUALS_RATE : Constant.PROJECTIONS_RATE;
                projTotal.addStringProperties(commonColumn + columnName, totalRate);

                totalRPU = getFormatValue(NumericConstants.TWO, totalRPU, CURRENCY);
                columnName = actualFlag ? Constant.ACTUALS_RPU : Constant.PROJECTIONS_RPU;
                projTotal.addStringProperties(commonColumn + columnName, totalRPU);

                if (!annualColumn.equals(StringUtils.EMPTY) && commonColumn.contains(annualColumn) && rightHeaderColumns.toString().contains(commonColumn)) {
                    if (actualFlag) {
                        amt = amt + (obj[NumericConstants.SIX] != null ? Double.parseDouble(String.valueOf(obj[NumericConstants.SIX])) : 0);
                        totalAmt = getFormatValue(NumericConstants.TWO, String.valueOf(amt), CURRENCY);
                        rate = rate + (obj[NumericConstants.SEVEN] != null ? Double.parseDouble(String.valueOf(obj[NumericConstants.SEVEN])) : 0);
                        totalRate = getFormatValue(NumericConstants.TWO, String.valueOf(rate), PERCENTAGE);
                        rpu = rpu + (obj[NumericConstants.EIGHT] != null ? Double.parseDouble(String.valueOf(obj[NumericConstants.EIGHT])) : 0);
                        totalRPU = getFormatValue(NumericConstants.TWO, String.valueOf(rpu), CURRENCY);
                    } else {
                        projamt = projamt + (obj[NumericConstants.SIX] != null ? Double.parseDouble(String.valueOf(obj[NumericConstants.SIX])) : 0);
                        totalAmt = getFormatValue(NumericConstants.TWO, String.valueOf(projamt), CURRENCY);
                        projrate = projrate + (obj[NumericConstants.SEVEN] != null ? Double.parseDouble(String.valueOf(obj[NumericConstants.SEVEN])) : 0);
                        totalRate = getFormatValue(NumericConstants.TWO, String.valueOf(projrate), PERCENTAGE);
                        projrpu = projrpu + (obj[NumericConstants.EIGHT] != null ? Double.parseDouble(String.valueOf(obj[NumericConstants.EIGHT])) : 0);
                        totalRPU = getFormatValue(NumericConstants.TWO, String.valueOf(projrpu), CURRENCY);
                    }
                    if (!actualFlag) {
                        totalAmt = CommonUtils.forecastConfigDataHide(freq, projSelDTO.getForecastConfigPeriods(), commonColumn, totalAmt);
                        totalRate = CommonUtils.forecastConfigDataHide(freq, projSelDTO.getForecastConfigPeriods(), commonColumn, totalRate);
                        totalRPU = CommonUtils.forecastConfigDataHide(freq, projSelDTO.getForecastConfigPeriods(), commonColumn, totalRPU);
                    }
                    columnName = actualFlag ? Constant.ACTUALS_AMOUNT : Constant.PROJECTIONS_AMOUNT;
                    projTotal.addStringProperties(annualColumn + columnName, totalAmt);

                    columnName = actualFlag ? Constant.ACTUALS_RATE : Constant.PROJECTIONS_RATE;
                    projTotal.addStringProperties(annualColumn + columnName, totalRate);

                    columnName = actualFlag ? Constant.ACTUALS_RPU : Constant.PROJECTIONS_RPU;
                    projTotal.addStringProperties(annualColumn + columnName, totalRPU);
                }

                if (Constant.MANDATED.equalsIgnoreCase(mandSupp) || Constant.BOTH_SMALL.equalsIgnoreCase(mandSupp)) {

                    String mandAmt = StringUtils.EMPTY + obj[0];
                    String mandRate = StringUtils.EMPTY + obj[1];
                    String mandRPU = StringUtils.EMPTY + obj[NumericConstants.TWO];

                    if (!actualFlag) {
                        mandAmt = CommonUtils.forecastConfigDataHide(freq, projSelDTO.getForecastConfigPeriods(), commonColumn, mandAmt);
                        mandRate = CommonUtils.forecastConfigDataHide(freq, projSelDTO.getForecastConfigPeriods(), commonColumn, mandRate);
                        mandRPU = CommonUtils.forecastConfigDataHide(freq, projSelDTO.getForecastConfigPeriods(), commonColumn, mandRPU);
                    }
                    mandAmt = getFormatValue(NumericConstants.TWO, mandAmt, CURRENCY);
                    columnName = actualFlag ? Constant.ACTUALS_AMOUNT : Constant.PROJECTIONS_AMOUNT;
                    mandatedDTO.addStringProperties(commonColumn + columnName, mandAmt);

                    mandRate = getFormatValue(NumericConstants.TWO, mandRate, PERCENTAGE);
                    columnName = actualFlag ? Constant.ACTUALS_RATE : Constant.PROJECTIONS_RATE;
                    mandatedDTO.addStringProperties(commonColumn + columnName, mandRate);

                    mandRPU = getFormatValue(NumericConstants.TWO, mandRPU, CURRENCY);
                    columnName = actualFlag ? Constant.ACTUALS_RPU : Constant.PROJECTIONS_RPU;
                    mandatedDTO.addStringProperties(commonColumn + columnName, mandRPU);
                    if (!annualColumn.equals(StringUtils.EMPTY) && commonColumn.contains(annualColumn) && rightHeaderColumns.toString().contains(commonColumn)) {
                        if (actualFlag) {
                            annualMandamt = annualMandamt + (obj[0] != null ? Double.parseDouble(String.valueOf(obj[0])) : 0);
                            mandAmt = getFormatValue(NumericConstants.TWO, String.valueOf(annualMandamt), CURRENCY);
                            annualMandrate = annualMandrate + (obj[1] != null ? Double.parseDouble(String.valueOf(obj[1])) : 0);
                            mandRate = getFormatValue(NumericConstants.TWO, String.valueOf(annualMandrate), PERCENTAGE);
                            annualMandrpu = annualMandrpu + (obj[NumericConstants.TWO] != null ? Double.parseDouble(String.valueOf(obj[NumericConstants.TWO])) : 0);
                            mandRPU = getFormatValue(NumericConstants.TWO, String.valueOf(annualMandrpu), CURRENCY);
                        } else {
                            annualMandProjamt = annualMandProjamt + (obj[0] != null ? Double.parseDouble(String.valueOf(obj[0])) : 0);
                            mandAmt = getFormatValue(NumericConstants.TWO, String.valueOf(annualMandProjamt), CURRENCY);
                            annualMandProjrate = annualMandProjrate + (obj[1] != null ? Double.parseDouble(String.valueOf(obj[1])) : 0);
                            mandRate = getFormatValue(NumericConstants.TWO, String.valueOf(annualMandProjrate), PERCENTAGE);
                            annualMandProjrpu = annualMandProjrpu + (obj[NumericConstants.TWO] != null ? Double.parseDouble(String.valueOf(obj[NumericConstants.TWO])) : 0);
                            mandRPU = getFormatValue(NumericConstants.TWO, String.valueOf(annualMandProjrpu), CURRENCY);
                        }

                        if (!actualFlag) {
                            mandAmt = CommonUtils.forecastConfigDataHide(freq, projSelDTO.getForecastConfigPeriods(), commonColumn, mandAmt);
                            mandRate = CommonUtils.forecastConfigDataHide(freq, projSelDTO.getForecastConfigPeriods(), commonColumn, mandRate);
                            mandRPU = CommonUtils.forecastConfigDataHide(freq, projSelDTO.getForecastConfigPeriods(), commonColumn, mandRPU);
                        }
                        columnName = actualFlag ? Constant.ACTUALS_AMOUNT : Constant.PROJECTIONS_AMOUNT;
                        mandatedDTO.addStringProperties(annualColumn + columnName, mandAmt);

                        columnName = actualFlag ? Constant.ACTUALS_RATE : Constant.PROJECTIONS_RATE;
                        mandatedDTO.addStringProperties(annualColumn + columnName, mandRate);

                        columnName = actualFlag ? Constant.ACTUALS_RPU : Constant.PROJECTIONS_RPU;
                        mandatedDTO.addStringProperties(annualColumn + columnName, mandRPU);
                    }
                }
                if (SUPPLEMENTAL.equalsIgnoreCase(mandSupp) || Constant.BOTH_SMALL.equalsIgnoreCase(mandSupp)) {
                    String suppAmt = StringUtils.EMPTY + obj[NumericConstants.THREE];
                    String suppRate = StringUtils.EMPTY + obj[NumericConstants.FOUR];
                    String suppRPU = StringUtils.EMPTY + obj[NumericConstants.FIVE];

                    if (!actualFlag) {
                        suppAmt = CommonUtils.forecastConfigDataHide(freq, projSelDTO.getForecastConfigPeriods(), commonColumn, suppAmt);
                        suppRate = CommonUtils.forecastConfigDataHide(freq, projSelDTO.getForecastConfigPeriods(), commonColumn, suppRate);
                        suppRPU = CommonUtils.forecastConfigDataHide(freq, projSelDTO.getForecastConfigPeriods(), commonColumn, suppRPU);
                    }

                    suppAmt = getFormatValue(NumericConstants.TWO, suppAmt, CURRENCY);
                    columnName = actualFlag ? Constant.ACTUALS_AMOUNT : Constant.PROJECTIONS_AMOUNT;
                    supplDTO.addStringProperties(commonColumn + columnName, suppAmt);

                    suppRate = getFormatValue(NumericConstants.TWO, suppRate, PERCENTAGE);
                    columnName = actualFlag ? Constant.ACTUALS_RATE : Constant.PROJECTIONS_RATE;
                    supplDTO.addStringProperties(commonColumn + columnName, suppRate);

                    suppRPU = getFormatValue(NumericConstants.TWO, suppRPU, CURRENCY);
                    columnName = actualFlag ? Constant.ACTUALS_RPU : Constant.PROJECTIONS_RPU;
                    supplDTO.addStringProperties(commonColumn + columnName, suppRPU);
                    if (!annualColumn.equals(StringUtils.EMPTY) && commonColumn.contains(annualColumn) && rightHeaderColumns.toString().contains(commonColumn)) {
                        if (actualFlag) {
                            annualSuppamt = annualSuppamt + (obj[NumericConstants.THREE] != null ? Double.parseDouble(String.valueOf(obj[NumericConstants.THREE])) : 0);
                            suppAmt = getFormatValue(NumericConstants.TWO, String.valueOf(annualSuppamt), CURRENCY);
                            annualSupprate = annualSupprate + (obj[NumericConstants.FOUR] != null ? Double.parseDouble(String.valueOf(obj[NumericConstants.FOUR])) : 0);
                            suppRate = getFormatValue(NumericConstants.TWO, String.valueOf(annualSupprate), PERCENTAGE);
                            annualSupprpu = annualSupprpu + (obj[NumericConstants.FIVE] != null ? Double.parseDouble(String.valueOf(obj[NumericConstants.FIVE])) : 0);
                            suppRPU = getFormatValue(NumericConstants.TWO, String.valueOf(annualSupprpu), CURRENCY);
                        } else {
                            annualSuppProjamt = annualSuppProjamt + (obj[NumericConstants.THREE] != null ? Double.parseDouble(String.valueOf(obj[NumericConstants.THREE])) : 0);
                            suppAmt = getFormatValue(NumericConstants.TWO, String.valueOf(annualSuppProjamt), CURRENCY);
                            annualSuppProjrate = annualSuppProjrate + (obj[NumericConstants.FOUR] != null ? Double.parseDouble(String.valueOf(obj[NumericConstants.FOUR])) : 0);
                            suppRate = getFormatValue(NumericConstants.TWO, String.valueOf(annualSuppProjrate), PERCENTAGE);
                            annualSuppProjrpu = annualSuppProjrpu + (obj[NumericConstants.FIVE] != null ? Double.parseDouble(String.valueOf(obj[NumericConstants.FIVE])) : 0);
                            suppRPU = getFormatValue(NumericConstants.TWO, String.valueOf(annualSuppProjrpu), CURRENCY);
                        }
                        if (!actualFlag) {
                            suppAmt = CommonUtils.forecastConfigDataHide(freq, projSelDTO.getForecastConfigPeriods(), commonColumn, suppAmt);
                            suppRate = CommonUtils.forecastConfigDataHide(freq, projSelDTO.getForecastConfigPeriods(), commonColumn, suppRate);
                            suppRPU = CommonUtils.forecastConfigDataHide(freq, projSelDTO.getForecastConfigPeriods(), commonColumn, suppRPU);
                        }
                        columnName = actualFlag ? Constant.ACTUALS_AMOUNT : Constant.PROJECTIONS_AMOUNT;
                        supplDTO.addStringProperties(annualColumn + columnName, suppAmt);

                        columnName = actualFlag ? Constant.ACTUALS_RATE : Constant.PROJECTIONS_RATE;
                        supplDTO.addStringProperties(annualColumn + columnName, suppRate);

                        columnName = actualFlag ? Constant.ACTUALS_RPU : Constant.PROJECTIONS_RPU;
                        supplDTO.addStringProperties(annualColumn + columnName, suppRPU);
                    }
                }
            }

        }
        projDTOList.add(projTotal);
        projDTOList.add(mandatedDTO);
        projDTOList.add(supplDTO);
        return projDTOList;
    }

    public List<DiscountProjectionResultsDTO> getCustomizedProjectionPivotTotal(List<Object[]> list, ProjectionSelectionDTO projSelDTO) {
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        List<DiscountProjectionResultsDTO> projDTOList = new ArrayList<>();
        List<String> periodList = new ArrayList<>(projSelDTO.getPeriodList());
        String mandSupp = projSelDTO.getMandatedOrSupp();
        String freq = projSelDTO.getFrequency();

        String type;
        String lastPeriod = StringUtils.EMPTY;
        DiscountProjectionResultsDTO projDTO = null;
        for (int i = 0; i < list.size(); i++) {
            final Object[] row = (Object[]) list.get(i);
            boolean annualFlag = false;
            boolean actualFlag = false;
            String columnName;
            if (ANNUALLY.equalsIgnoreCase(freq)) {
                annualFlag = true;
            }
            if (annualFlag) {
                type = String.valueOf(row[NumericConstants.TEN]);
            } else {
                type = String.valueOf(row[NumericConstants.ELEVEN]);
            }
            String column;
            int year = annualFlag ? Integer.parseInt(String.valueOf(row[NumericConstants.NINE])) : Integer.parseInt(String.valueOf(row[NumericConstants.TEN]));
            int period = annualFlag ? 0 : Integer.parseInt(String.valueOf(row[NumericConstants.NINE]));
            List<String> common = HeaderUtils.getCommonColumnHeader(frequencyDivision, year, period, false);
            String pcommonColumn = common.get(0);
            String commonHeader = common.get(1);
            String commonColumn;
            if ("Actual".equalsIgnoreCase(type)) {
                actualFlag = true;
            } else {
                actualFlag = false;
            }
            if (periodList.contains(pcommonColumn)) {
                if (lastPeriod.isEmpty() || !lastPeriod.equalsIgnoreCase(common.get(0))) {
                    if (projDTO != null) {
                        projDTO.setParent(0);
                        projDTO.setProjectionTotal(1);
                        projDTOList.add(projDTO);
                    }
                    projDTO = new DiscountProjectionResultsDTO();
                    lastPeriod = common.get(0);
                }
                projDTO.setLevelValue(commonHeader);
                projDTO.setRelationshipLevelName(commonHeader);
                String value;
                commonColumn = Constant.TOTALDISCOUNT;
                column = actualFlag ? Constant.ACTUALS_AMOUNT : Constant.PROJECTIONS_AMOUNT;
                columnName = commonColumn + column;
                if (projSelDTO.hasColumn(columnName)) {
                    value = StringUtils.EMPTY + row[NumericConstants.SIX];
                    if (!actualFlag) {
                        value = CommonUtils.forecastConfigDataHide(freq, projSelDTO.getForecastConfigPeriods(), pcommonColumn, value);
                    }
                    value = getFormatValue(NumericConstants.TWO, value, CURRENCY);
                    projDTO.addStringProperties(columnName, value);
                }
                column = actualFlag ? Constant.ACTUALS_RATE : Constant.PROJECTIONS_RATE;
                columnName = commonColumn + column;
                if (projSelDTO.hasColumn(columnName)) {
                    value = StringUtils.EMPTY + row[NumericConstants.SEVEN];
                    if (!actualFlag) {
                        value = CommonUtils.forecastConfigDataHide(freq, projSelDTO.getForecastConfigPeriods(), pcommonColumn, value);
                    }
                    value = getFormatValue(NumericConstants.TWO, value, PERCENTAGE);
                    projDTO.addStringProperties(columnName, value);
                }
                column = actualFlag ? Constant.ACTUALS_RPU : Constant.PROJECTIONS_RPU;
                columnName = commonColumn + column;
                if (projSelDTO.hasColumn(columnName)) {
                    value = StringUtils.EMPTY + row[NumericConstants.EIGHT];
                    value = getFormatValue(NumericConstants.TWO, value, CURRENCY);
                    if (!actualFlag) {
                        value = CommonUtils.forecastConfigDataHide(freq, projSelDTO.getForecastConfigPeriods(), pcommonColumn, value);
                    }
                    projDTO.addStringProperties(columnName, value);
                }
                if (Constant.MANDATED.equalsIgnoreCase(mandSupp) || Constant.BOTH_SMALL.equalsIgnoreCase(mandSupp)) {
                    commonColumn = Constant.MANDATED_DISCOUNT1;
                    column = actualFlag ? Constant.ACTUALS_AMOUNT : Constant.PROJECTIONS_AMOUNT;
                    columnName = commonColumn + column;
                    if (projSelDTO.hasColumn(columnName)) {
                        value = StringUtils.EMPTY + row[0];
                        if (!actualFlag) {
                            value = CommonUtils.forecastConfigDataHide(freq, projSelDTO.getForecastConfigPeriods(), pcommonColumn, value);
                        }
                        value = getFormatValue(NumericConstants.TWO, value, CURRENCY);
                        projDTO.addStringProperties(columnName, value);
                    }
                    column = actualFlag ? Constant.ACTUALS_RATE : Constant.PROJECTIONS_RATE;
                    columnName = commonColumn + column;
                    if (projSelDTO.hasColumn(columnName)) {
                        value = StringUtils.EMPTY + row[1];
                        if (!actualFlag) {
                            value = CommonUtils.forecastConfigDataHide(freq, projSelDTO.getForecastConfigPeriods(), pcommonColumn, value);
                        }
                        value = getFormatValue(NumericConstants.TWO, value, PERCENTAGE);
                        projDTO.addStringProperties(columnName, value);
                    }
                    column = actualFlag ? Constant.ACTUALS_RPU : Constant.PROJECTIONS_RPU;
                    columnName = commonColumn + column;
                    if (projSelDTO.hasColumn(columnName)) {
                        value = StringUtils.EMPTY + row[NumericConstants.TWO];
                        if (!actualFlag) {
                            value = CommonUtils.forecastConfigDataHide(freq, projSelDTO.getForecastConfigPeriods(), pcommonColumn, value);
                        }
                        value = getFormatValue(NumericConstants.TWO, value, CURRENCY);
                        projDTO.addStringProperties(columnName, value);
                    }
                }
                if (SUPPLEMENTAL.equalsIgnoreCase(mandSupp) || Constant.BOTH_SMALL.equalsIgnoreCase(mandSupp)) {
                    commonColumn = SUPPLEMENTAL_DISCOUNT1;
                    column = actualFlag ? Constant.ACTUALS_AMOUNT : Constant.PROJECTIONS_AMOUNT;
                    columnName = commonColumn + column;
                    if (projSelDTO.hasColumn(columnName)) {
                        value = StringUtils.EMPTY + row[NumericConstants.THREE];
                        if (!actualFlag) {
                            value = CommonUtils.forecastConfigDataHide(freq, projSelDTO.getForecastConfigPeriods(), pcommonColumn, value);
                        }
                        value = getFormatValue(NumericConstants.TWO, value, CURRENCY);
                        projDTO.addStringProperties(columnName, value);
                    }
                    column = actualFlag ? Constant.ACTUALS_RATE : Constant.PROJECTIONS_RATE;
                    columnName = commonColumn + column;
                    if (projSelDTO.hasColumn(columnName)) {
                        value = StringUtils.EMPTY + row[NumericConstants.FOUR];
                        if (!actualFlag) {
                            value = CommonUtils.forecastConfigDataHide(freq, projSelDTO.getForecastConfigPeriods(), pcommonColumn, value);
                        }
                        value = getFormatValue(NumericConstants.TWO, value, PERCENTAGE);
                        projDTO.addStringProperties(columnName, value);
                    }
                    column = actualFlag ? Constant.ACTUALS_RPU : Constant.PROJECTIONS_RPU;
                    columnName = commonColumn + column;
                    if (projSelDTO.hasColumn(columnName)) {
                        value = StringUtils.EMPTY + row[NumericConstants.FIVE];
                        if (!actualFlag) {
                            value = CommonUtils.forecastConfigDataHide(freq, projSelDTO.getForecastConfigPeriods(), pcommonColumn, value);
                        }
                        value = getFormatValue(NumericConstants.TWO, value, CURRENCY);
                        projDTO.addStringProperties(columnName, value);
                    }
                }
                if (i == list.size() - 1) {
                    projDTO.setParent(0);
                    projDTO.setProjectionTotal(1);
                    projDTOList.add(projDTO);
                }

            }
        }
        return projDTOList;
    }

    public String getFormattedValue(DecimalFormat formatt, String value) {
        String valueFormat = value;
        if (value.contains(Constant.NULL)) {
            valueFormat = "...";
        } else {
            valueFormat = formatt.format(Double.valueOf(valueFormat));
        }
        return valueFormat;
    }

    public String getFormatValue(int numberOfDecimal, String value, String appendChar) {
        String formattedValue = value;
        if (formattedValue.contains(Constant.NULL)) {
            formattedValue = "...";
        } else if (CURRENCY.equals(appendChar)) {
            formattedValue = String.valueOf(new BigDecimal(String.valueOf(formattedValue)).setScale(numberOfDecimal, BigDecimal.ROUND_DOWN));
            formattedValue = getFormattedValue(CUR_TWO, formattedValue);

        } else if (Constant.PERCENT.equals(appendChar)) {
            formattedValue = String.valueOf(new BigDecimal(String.valueOf(formattedValue)).setScale(numberOfDecimal, BigDecimal.ROUND_DOWN));
            formattedValue = getFormattedValue(PER_THREE, formattedValue).concat(appendChar);
        }
        return formattedValue;
    }

    private String getProgramCodeQuery(String hierarchyNo, String frequency, ProjectionSelectionDTO projSelDTO, String freqChar) {
        String columnName = StringUtils.EMPTY;
        List list;
        String hierSQL = "SELECT FIELD_NAME FROM dbo.HIERARCHY_LEVEL_DEFINITION WHERE HIERARCHY_DEFINITION_SID=" + projSelDTO.getCustomerHierarchySID() + " and LEVEL_NAME='Contract'";
        list = (List<Object>) CommonLogic.executeSelectQuery(hierSQL);

        if (list != null && !list.isEmpty()) {
            final Object obj = list.get(0);
            columnName = String.valueOf(obj);
        }

        if (StringUtils.isBlank(columnName)) {
            columnName = "contract_name";
        }
        String selectedHierQury = "IF Object_id('TEMPDB..#SELECTED_HIERARCHY_NO') IS NOT NULL\n"
                + "  DROP TABLE #SELECTED_HIERARCHY_NO\n"
                + "\n"
                + "CREATE TABLE #SELECTED_HIERARCHY_NO\n"
                + "  (\n"
                + "     HIERARCHY_NO    VARCHAR(50),\n"
                + "     CCP_DETAILS_SID INT\n"
                + "  )\n"
                + "\n"
                + "INSERT INTO #SELECTED_HIERARCHY_NO\n"
                + "            (HIERARCHY_NO,\n"
                + "             CCP_DETAILS_SID)\n"
                + "SELECT DISTINCT HIERARCHY_NO,\n"
                + "                CH.CCP_DETAILS_SID\n"
                + "FROM   ( VALUES('" + hierarchyNo + "') ) A(HIERARCHY_NO)\n"
                + "       JOIN ST_CCP_HIERARCHY CH\n"
                + "         ON CH.CUST_HIERARCHY_NO LIKE A.HIERARCHY_NO + '%' ";
        String customSQL = selectedHierQury + " SELECT \n"
                + "    '' as   LEVEL_NO,\n"
                + "     '' as   LEVEL_NAME,\n"
                + "       cm." + columnName + ",\n"
                + Constant.PD_HIERARCHY_NO
                + "p.\"YEAR\", \n"
                + "       Sum(MAD.ACTUAL_SALES)        AS  Disc_Amt,\n"
                + "Coalesce(Sum(MAD.ACTUAL_SALES)/Nullif(Sum(m_ac.ACTUAL_SALES),0),0)*100       AS  Disc_Rate,\n"
                + "       Sum(SPMA.ACTUAL_SALES)        AS  Supp_Amt,\n"
                + "Coalesce(Sum(SPMA.ACTUAL_SALES)/Nullif(Sum(m_ac.ACTUAL_SALES),0),0)*100      AS  Supp_Rate,\n"
                + " Coalesce(Sum(MAD.ACTUAL_SALES)/Nullif(Sum(m_ac.ACTUAL_UNITS),0),0) AS Man_RPU, Coalesce(Sum(SPMA.ACTUAL_SALES)/Nullif(Sum(m_ac.ACTUAL_UNITS),0),0) As Sup_RPU,\n"
                + "       0                            As ActualProj\n";
        if (!Constant.YEAR_SPACE.equalsIgnoreCase(frequency)) {
            customSQL += "       , p." + frequency + "\n";
            customSQL += ",'" + freqChar + "'+CONVERT(VARCHAR(2),P." + frequency + ")+' '+CONVERT(VARCHAR(4),P.YEAR) AS PERIOD\n";
        }
        customSQL += "FROM #SELECTED_HIERARCHY_NO pd\n";

        customSQL += "join ccp_details ccd on ccd.CCP_DETAILS_SID=pd.CCP_DETAILS_SID\n"
                + "join contract_master cm on cm.contract_master_sid=ccd.contract_master_sid\n";

        customSQL += Constant.JOIN_SPACE
                + "   ST_M_SALES_PROJECTION_MASTER "
                + "m_mas ON pd.CCP_DETAILS_SID = m_mas.CCP_DETAILS_SID\n"
                + "JOIN  "
                + " ST_M_ACTUAL_SALES m_ac"
                + " ON m_mas.CCP_DETAILS_SID = m_ac.CCP_DETAILS_SID\n"
                + "JOIN   ST_M_ACTUAL_DISCOUNT "
                + " MAD ON MAD.CCP_DETAILS_SID = pd.CCP_DETAILS_SID \n"
                + "AND MAD.PERIOD_SID=m_ac.PERIOD_SID \n"
                + Constant.LEFT_JOIN
                + " ST_M_SUPPLEMENTAL_DISC_MASTER "
                + " SPM ON SPM.CCP_DETAILS_SID = pd.CCP_DETAILS_SID \n"
                + "LEFT JOIN  "
                + "  ST_M_SUPPLEMENTAL_DISC_ACTUALS "
                + " SPMA ON SPMA.CCP_DETAILS_SID = pd.CCP_DETAILS_SID \n"
                + "										   AND SPMA.PERIOD_SID=m_ac.PERIOD_SID\n"
                + "JOIN   PERIOD p ON p.period_sid = m_ac.PERIOD_SID \n"
                + "GROUP  BY cm." + columnName + ",\n";
        if (!Constant.YEAR_SPACE.equalsIgnoreCase(frequency)) {
            customSQL += "         p." + frequency + ",\n";
        }
        customSQL += Constant.PD_HIERARCHY_NO
                + "          p.\"YEAR\""
                + "Union ALL \n"
                + "\n"
                + "SELECT\n"
                + "    '' as   LEVEL_NO,\n"
                + "     '' as   LEVEL_NAME,\n"
                + "       cm." + columnName + ",\n"
                + Constant.PD_HIERARCHY_NO
                + "p.\"YEAR\", \n"
                + "       Sum(MAD.PROJECTION_SALES)        AS  Disc_Amt,\n"
                + "Coalesce(Sum(MAD.PROJECTION_SALES)/Nullif(Sum(m_ac.PROJECTION_SALES),0),0)*100 AS  Disc_Rate,\n"
                + "       Sum(SPMA.PROJECTION_SALES)        AS  Supp_Amt,\n"
                + "Coalesce(Sum(SPMA.PROJECTION_SALES)/Nullif(Sum(m_ac.PROJECTION_SALES),0),0)*100 AS  Supp_Rate,\n"
                + " Coalesce(Sum(MAD.PROJECTION_SALES)/Nullif(Sum(m_ac.PROJECTION_UNITS),0),0) AS Man_RPU, Coalesce(Sum(SPMA.PROJECTION_SALES)/Nullif(Sum(m_ac.PROJECTION_UNITS),0),0) As Sup_RPU, \n"
                + "       1                            As ActualProj\n";
        if (!Constant.YEAR_SPACE.equalsIgnoreCase(frequency)) {
            customSQL += "        , p." + frequency + "\n";
            customSQL += ",'" + freqChar + "'+CONVERT(VARCHAR(2),P." + frequency + ")+' '+CONVERT(VARCHAR(4),P.YEAR) AS PERIOD\n";
        }
        customSQL += "FROM   #SELECTED_HIERARCHY_NO pd\n";

        customSQL += "join ccp_details ccd on ccd.CCP_DETAILS_SID=pd.CCP_DETAILS_SID\n"
                + "join contract_master cm on cm.contract_master_sid=ccd.contract_master_sid\n";

        customSQL += Constant.JOIN_SPACE
                + "  ST_M_SALES_PROJECTION_MASTER"
                + " m_mas ON pd.CCP_DETAILS_SID = m_mas.CCP_DETAILS_SID\n"
                + Constant.JOIN_SPACE
                + "  ST_M_SALES_PROJECTION "
                + " m_ac ON m_mas.CCP_DETAILS_SID = m_ac.CCP_DETAILS_SID \n"
                + "JOIN    "
                + "  ST_M_DISCOUNT_PROJECTION "
                + " MAD ON MAD.CCP_DETAILS_SID = pd.CCP_DETAILS_SID \n"
                + "AND MAD.PERIOD_SID=m_ac.PERIOD_SID \n"
                + Constant.LEFT_JOIN
                + "  ST_M_SUPPLEMENTAL_DISC_MASTER "
                + " SPM ON SPM.CCP_DETAILS_SID = pd.CCP_DETAILS_SID\n"
                + "\n"
                + Constant.LEFT_JOIN
                + "  ST_M_SUPPLEMENTAL_DISC_PROJ "
                + " SPMA ON SPMA.CCP_DETAILS_SID = pd.CCP_DETAILS_SID \n"
                + "                                                                        AND SPMA.PERIOD_SID=m_ac.PERIOD_SID\n"
                + "JOIN   PERIOD p ON p.period_sid = m_ac.PERIOD_SID \n"
                + "GROUP  BY cm." + columnName + ",\n";
        if (!Constant.YEAR_SPACE.equalsIgnoreCase(frequency)) {
            customSQL += "          p." + frequency + ",\n";
        }
        customSQL += Constant.PD_HIERARCHY_NO
                + "          p.\"YEAR\"\n";
        if (Constant.PERIOD.equalsIgnoreCase(projSelDTO.getPivotView())) {
            customSQL += "Order By cm." + columnName + ",p.\"YEAR\"\n";
        } else {
            customSQL += "Order By p.\"YEAR\"\n";
        }

        if (!Constant.YEAR_SPACE.equalsIgnoreCase(frequency)) {
            customSQL += "          ,p." + frequency + "\n";
        }
        customSQL = QueryUtil.replaceTableNames(customSQL, projSelDTO.getSessionDTO().getCurrentTableNames());

        return customSQL;
    }

    private String getProgramCodeNameQuery(int projectionId) {
        String customSQL = "SELECT DISTINCT CM.CONTRACT_NAME FROM dbo.CONTRACT_MASTER CM \n"
                + "JOIN dbo.CCP_DETAILS CCP ON CCP.CONTRACT_MASTER_SID = CM.CONTRACT_MASTER_SID\n"
                + "JOIN dbo.PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID  = CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID = " + projectionId;
        return customSQL;
    }

    public DiscountProjectionResultsDTO getChildNodeValues(DiscountProjectionResultsDTO dto, ProjectionSelectionDTO projSelDTO) {
        Map<Integer, String> freq = new HashMap<>();
        freq.put(1, Constant.YEAR_SPACE);
        freq.put(NumericConstants.TWO, Constant.SEMI_ANNUAL);
        freq.put(NumericConstants.FOUR, Constant.QUARTER);
        freq.put(NumericConstants.TWELVE, Constant.MONTH_SPACE);
        String frequency = freq.get(projSelDTO.getFrequencyDivision());
        String freqChar;
        freqChar = !Constant.YEAR_SPACE.equalsIgnoreCase(frequency) ? Constant.SEMI_ANNUAL.equalsIgnoreCase(frequency) ? Constant.S : Constant.QUARTER.equalsIgnoreCase(frequency) ? Constant.Q : CommonUtils.BUSINESS_PROCESS_INDICATOR_MANDATED : "";
        String query = getHierarchyLevelQuery(projSelDTO.getHierarchyNo(), frequency, projSelDTO, freqChar);
        List<Object> list = (List<Object>) CommonLogic.executeSelectQuery(query);
        double annualMandamt = 0.0;
        double annualMandrate = 0.0;
        double annualMandrpu = 0.0;
        double annualMandProjamt = 0.0;
        double annualMandProjrate = 0.0;
        double annualMandProjrpu = 0.0;
        double annualSuppamt = 0.0;
        double annualSupprate = 0.0;
        double annualSupprpu = 0.0;
        double annualSuppProjamt = 0.0;
        double annualSuppProjrate = 0.0;
        double annualSuppProjrpu = 0.0;
        String tempAnnual = StringUtils.EMPTY;
        List<Object> rightHeaderColumns = projSelDTO.getRightHeaderDoubleColumns();
        List<String> annualTotal = new ArrayList<>();
        if (list != null && !list.isEmpty()) {
            int frequencyDivision = projSelDTO.getFrequencyDivision();
            DiscountProjectionResultsDTO mandatedDTO = new DiscountProjectionResultsDTO();
            DiscountProjectionResultsDTO suppDTO = new DiscountProjectionResultsDTO();
            mandatedDTO.setCustomLevelNo(dto.getCustomLevelNo());
            suppDTO.setCustomLevelNo(dto.getCustomLevelNo());
            mandatedDTO.setCustomerHierarchyNo(dto.getCustomerHierarchyNo());
            suppDTO.setCustomerHierarchyNo(dto.getCustomerHierarchyNo());
            mandatedDTO.setProductHierarchyNo(dto.getProductHierarchyNo());
            suppDTO.setProductHierarchyNo(dto.getProductHierarchyNo());
            boolean actualFlag = false;
            for (Object list1 : list) {

                String columnName;
                final Object[] obj = (Object[]) list1;
                actualFlag = Integer.parseInt(String.valueOf(obj[NumericConstants.ELEVEN])) == 0;
                String mandSupp = projSelDTO.getMandatedOrSupp();

                int year = Integer.parseInt(String.valueOf(obj[NumericConstants.FOUR]));
                int period = projSelDTO.getFrequencyDivision() == 1 ? 0 : Integer.parseInt(String.valueOf(obj[NumericConstants.TWELVE]));
                String annualColumn = StringUtils.EMPTY;
                if (!ANNUALLY.equalsIgnoreCase(projSelDTO.getFrequency())) {
                    annualTotal = HeaderUtils.getCommonColumnHeader(1, year, 0, false);
                }
                if (annualTotal != null && !annualTotal.isEmpty() && !tempAnnual.equals(annualTotal.get(0))) {
                    annualMandamt = 0.0;
                    annualMandrate = 0.0;
                    annualMandrpu = 0.0;
                    annualMandProjamt = 0.0;
                    annualMandProjrate = 0.0;
                    annualMandProjrpu = 0.0;
                    annualSuppamt = 0.0;
                    annualSupprate = 0.0;
                    annualSupprpu = 0.0;
                    annualSuppProjamt = 0.0;
                    annualSuppProjrate = 0.0;
                    annualSuppProjrpu = 0.0;
                }
                if (annualTotal != null && !annualTotal.isEmpty()) {
                    annualColumn = annualTotal.get(0);
                    tempAnnual = annualColumn;
                }
                List<String> common = HeaderUtils.getCommonColumnHeader(frequencyDivision, year, period, false);
                String commonColumn = common.get(0);
                if (Constant.MANDATED.equalsIgnoreCase(mandSupp)) {

                    String mandAmt = StringUtils.EMPTY + obj[NumericConstants.FIVE];
                    String mandRate = StringUtils.EMPTY + obj[NumericConstants.SIX];
                    String mandRPU = StringUtils.EMPTY + obj[NumericConstants.NINE];

                    if (!actualFlag) {
                        mandAmt = CommonUtils.forecastConfigDataHide(projSelDTO.getFrequency(), projSelDTO.getForecastConfigPeriods(), commonColumn, mandAmt);
                        mandRate = CommonUtils.forecastConfigDataHide(projSelDTO.getFrequency(), projSelDTO.getForecastConfigPeriods(), commonColumn, mandRate);
                        mandRPU = CommonUtils.forecastConfigDataHide(projSelDTO.getFrequency(), projSelDTO.getForecastConfigPeriods(), commonColumn, mandRPU);
                    }

                    mandAmt = getFormatValue(NumericConstants.TWO, mandAmt, CURRENCY);
                    columnName = actualFlag ? Constant.ACTUALS_AMOUNT : Constant.PROJECTIONS_AMOUNT;
                    mandatedDTO.addStringProperties(commonColumn + columnName, mandAmt);
                    dto.addStringProperties(commonColumn + columnName, mandAmt);

                    mandRate = getFormatValue(NumericConstants.TWO, mandRate, PERCENTAGE);
                    columnName = actualFlag ? Constant.ACTUALS_RATE : Constant.PROJECTIONS_RATE;
                    mandatedDTO.addStringProperties(commonColumn + columnName, mandRate);

                    dto.addStringProperties(commonColumn + columnName, mandRate);

                    mandRPU = getFormatValue(NumericConstants.TWO, mandRPU, CURRENCY);
                    columnName = actualFlag ? Constant.ACTUALS_RPU : Constant.PROJECTIONS_RPU;
                    mandatedDTO.addStringProperties(commonColumn + columnName, mandRPU);
                    dto.addStringProperties(commonColumn + columnName, mandRPU);

                    if (!annualColumn.equals(StringUtils.EMPTY) && commonColumn.contains(annualColumn) && rightHeaderColumns.toString().contains(commonColumn)) {
                        if (actualFlag) {
                            annualMandamt = annualMandamt + (obj[NumericConstants.FIVE] != null ? Double.parseDouble(String.valueOf(obj[NumericConstants.FIVE])) : 0);
                            mandAmt = getFormatValue(NumericConstants.TWO, String.valueOf(annualMandamt), CURRENCY);
                            annualMandrate = annualMandrate + (obj[NumericConstants.SIX] != null ? Double.parseDouble(String.valueOf(obj[NumericConstants.SIX])) : 0);
                            mandRate = getFormatValue(NumericConstants.TWO, String.valueOf(annualMandrate), PERCENTAGE);
                            annualMandrpu = annualMandrpu + (obj[NumericConstants.NINE] != null ? Double.parseDouble(String.valueOf(obj[NumericConstants.NINE])) : 0);
                            mandRPU = getFormatValue(NumericConstants.TWO, String.valueOf(annualMandrpu), CURRENCY);
                        } else {
                            annualMandProjamt = annualMandProjamt + (obj[NumericConstants.FIVE] != null ? Double.parseDouble(String.valueOf(obj[NumericConstants.FIVE])) : 0);
                            mandAmt = getFormatValue(NumericConstants.TWO, String.valueOf(annualMandProjamt), CURRENCY);
                            annualMandProjrate = annualMandProjrate + (obj[NumericConstants.SIX] != null ? Double.parseDouble(String.valueOf(obj[NumericConstants.SIX])) : 0);
                            mandRate = getFormatValue(NumericConstants.TWO, String.valueOf(annualMandProjrate), PERCENTAGE);
                            annualMandProjrpu = annualMandProjrpu + (obj[NumericConstants.NINE] != null ? Double.parseDouble(String.valueOf(obj[NumericConstants.NINE])) : 0);
                            mandRPU = getFormatValue(NumericConstants.TWO, String.valueOf(annualMandProjrpu), CURRENCY);
                        }

                        if (!actualFlag) {
                            mandAmt = CommonUtils.forecastConfigDataHide(projSelDTO.getFrequency(), projSelDTO.getForecastConfigPeriods(), commonColumn, mandAmt);
                            mandRate = CommonUtils.forecastConfigDataHide(projSelDTO.getFrequency(), projSelDTO.getForecastConfigPeriods(), commonColumn, mandRate);
                            mandRPU = CommonUtils.forecastConfigDataHide(projSelDTO.getFrequency(), projSelDTO.getForecastConfigPeriods(), commonColumn, mandRPU);
                        }

                        columnName = actualFlag ? Constant.ACTUALS_AMOUNT : Constant.PROJECTIONS_AMOUNT;
                        mandatedDTO.addStringProperties(annualColumn + columnName, mandAmt);
                        dto.addStringProperties(annualColumn + columnName, mandAmt);

                        columnName = actualFlag ? Constant.ACTUALS_RATE : Constant.PROJECTIONS_RATE;
                        mandatedDTO.addStringProperties(annualColumn + columnName, mandRate);
                        dto.addStringProperties(annualColumn + columnName, mandRate);

                        columnName = actualFlag ? Constant.ACTUALS_RPU : Constant.PROJECTIONS_RPU;
                        mandatedDTO.addStringProperties(annualColumn + columnName, mandRPU);
                        dto.addStringProperties(annualColumn + columnName, mandRPU);
                    }
                }
                if (SUPPLEMENTAL.equalsIgnoreCase(mandSupp)) {
                    String suppAmt = StringUtils.EMPTY + obj[NumericConstants.SEVEN];
                    String suppRate = StringUtils.EMPTY + obj[NumericConstants.EIGHT];
                    String suppRPU = StringUtils.EMPTY + obj[NumericConstants.TEN];
                    if (!actualFlag) {
                        suppAmt = CommonUtils.forecastConfigDataHide(projSelDTO.getFrequency(), projSelDTO.getForecastConfigPeriods(), commonColumn, suppAmt);
                        suppRate = CommonUtils.forecastConfigDataHide(projSelDTO.getFrequency(), projSelDTO.getForecastConfigPeriods(), commonColumn, suppRate);
                        suppRPU = CommonUtils.forecastConfigDataHide(projSelDTO.getFrequency(), projSelDTO.getForecastConfigPeriods(), commonColumn, suppRPU);
                    }
                    suppAmt = getFormatValue(NumericConstants.TWO, suppAmt, CURRENCY);
                    columnName = actualFlag ? Constant.ACTUALS_AMOUNT : Constant.PROJECTIONS_AMOUNT;
                    suppDTO.addStringProperties(commonColumn + columnName, suppAmt);
                    dto.addStringProperties(commonColumn + columnName, suppAmt);

                    suppRate = getFormatValue(NumericConstants.TWO, suppRate, PERCENTAGE);
                    columnName = actualFlag ? Constant.ACTUALS_RATE : Constant.PROJECTIONS_RATE;
                    suppDTO.addStringProperties(commonColumn + columnName, suppRate);
                    dto.addStringProperties(commonColumn + columnName, suppRate);

                    suppRPU = getFormatValue(NumericConstants.TWO, suppRPU, CURRENCY);
                    columnName = actualFlag ? Constant.ACTUALS_RATE : Constant.PROJECTIONS_RATE;
                    suppDTO.addStringProperties(commonColumn + columnName, suppRPU);
                    dto.addStringProperties(commonColumn + columnName, suppRPU);

                    if (!annualColumn.equals(StringUtils.EMPTY) && commonColumn.contains(annualColumn) && rightHeaderColumns.toString().contains(commonColumn)) {
                        if (actualFlag) {
                            annualSuppamt = annualSuppamt + (obj[NumericConstants.SEVEN] != null ? Double.parseDouble(String.valueOf(obj[NumericConstants.SEVEN])) : 0);
                            suppAmt = getFormatValue(NumericConstants.TWO, String.valueOf(annualSuppamt), CURRENCY);
                            annualSupprate = annualSupprate + (obj[NumericConstants.EIGHT] != null ? Double.parseDouble(String.valueOf(obj[NumericConstants.EIGHT])) : 0);
                            suppRate = getFormatValue(NumericConstants.TWO, String.valueOf(annualSupprate), PERCENTAGE);
                            annualSupprpu = annualSupprpu + (obj[NumericConstants.TEN] != null ? Double.parseDouble(String.valueOf(obj[NumericConstants.TEN])) : 0);
                            suppRPU = getFormatValue(NumericConstants.TWO, String.valueOf(annualSupprpu), CURRENCY);
                        } else {
                            annualSuppProjamt = annualSuppProjamt + (obj[NumericConstants.SEVEN] != null ? Double.parseDouble(String.valueOf(obj[NumericConstants.SEVEN])) : 0);
                            suppAmt = getFormatValue(NumericConstants.TWO, String.valueOf(annualSuppProjamt), CURRENCY);
                            annualSuppProjrate = annualSuppProjrate + (obj[NumericConstants.EIGHT] != null ? Double.parseDouble(String.valueOf(obj[NumericConstants.EIGHT])) : 0);
                            suppRate = getFormatValue(NumericConstants.TWO, String.valueOf(annualSuppProjrate), PERCENTAGE);
                            annualSuppProjrpu = annualSuppProjrpu + (obj[NumericConstants.TEN] != null ? Double.parseDouble(String.valueOf(obj[NumericConstants.TEN])) : 0);
                            suppRPU = getFormatValue(NumericConstants.TWO, String.valueOf(annualSuppProjrpu), CURRENCY);
                        }

                        if (!actualFlag) {
                            suppAmt = CommonUtils.forecastConfigDataHide(projSelDTO.getFrequency(), projSelDTO.getForecastConfigPeriods(), commonColumn, suppAmt);
                            suppRate = CommonUtils.forecastConfigDataHide(projSelDTO.getFrequency(), projSelDTO.getForecastConfigPeriods(), commonColumn, suppRate);
                            suppRPU = CommonUtils.forecastConfigDataHide(projSelDTO.getFrequency(), projSelDTO.getForecastConfigPeriods(), commonColumn, suppRPU);
                        }

                        columnName = actualFlag ? Constant.ACTUALS_AMOUNT : Constant.PROJECTIONS_AMOUNT;
                        suppDTO.addStringProperties(annualColumn + columnName, suppAmt);
                        dto.addStringProperties(annualColumn + columnName, suppAmt);

                        columnName = actualFlag ? Constant.ACTUALS_RATE : Constant.PROJECTIONS_RATE;
                        suppDTO.addStringProperties(annualColumn + columnName, suppRate);
                        dto.addStringProperties(annualColumn + columnName, suppRate);

                        columnName = actualFlag ? Constant.ACTUALS_RPU : Constant.PROJECTIONS_RPU;
                        suppDTO.addStringProperties(annualColumn + columnName, suppRPU);
                        dto.addStringProperties(annualColumn + columnName, suppRPU);
                    }
                }
                if (Constant.BOTH_SMALL.equalsIgnoreCase(mandSupp)) {
                    String mandAmt = StringUtils.EMPTY + obj[NumericConstants.FIVE];
                    String mandRate = StringUtils.EMPTY + obj[NumericConstants.SIX];
                    String suppAmt = StringUtils.EMPTY + obj[NumericConstants.SEVEN];
                    String suppRate = StringUtils.EMPTY + obj[NumericConstants.EIGHT];
                    String mandRPU = StringUtils.EMPTY + obj[NumericConstants.NINE];
                    String suppRPU = StringUtils.EMPTY + obj[NumericConstants.TEN];

                    if (!actualFlag) {
                        mandAmt = CommonUtils.forecastConfigDataHide(projSelDTO.getFrequency(), projSelDTO.getForecastConfigPeriods(), commonColumn, mandAmt);
                        mandRate = CommonUtils.forecastConfigDataHide(projSelDTO.getFrequency(), projSelDTO.getForecastConfigPeriods(), commonColumn, mandRate);
                        mandRPU = CommonUtils.forecastConfigDataHide(projSelDTO.getFrequency(), projSelDTO.getForecastConfigPeriods(), commonColumn, mandRPU);
                        suppAmt = CommonUtils.forecastConfigDataHide(projSelDTO.getFrequency(), projSelDTO.getForecastConfigPeriods(), commonColumn, suppAmt);
                        suppRate = CommonUtils.forecastConfigDataHide(projSelDTO.getFrequency(), projSelDTO.getForecastConfigPeriods(), commonColumn, suppRate);
                        suppRPU = CommonUtils.forecastConfigDataHide(projSelDTO.getFrequency(), projSelDTO.getForecastConfigPeriods(), commonColumn, suppRPU);
                    }

                    mandAmt = getFormatValue(NumericConstants.TWO, mandAmt, CURRENCY);
                    columnName = actualFlag ? Constant.ACTUALS_AMOUNT : Constant.PROJECTIONS_AMOUNT;
                    mandatedDTO.addStringProperties(commonColumn + columnName, mandAmt);

                    mandRate = getFormatValue(NumericConstants.TWO, mandRate, PERCENTAGE);
                    columnName = actualFlag ? Constant.ACTUALS_RATE : Constant.PROJECTIONS_RATE;
                    mandatedDTO.addStringProperties(commonColumn + columnName, mandRate);

                    suppAmt = getFormatValue(NumericConstants.TWO, suppAmt, CURRENCY);
                    columnName = actualFlag ? Constant.ACTUALS_AMOUNT : Constant.PROJECTIONS_AMOUNT;
                    suppDTO.addStringProperties(commonColumn + columnName, suppAmt);
                    Double amt = (StringUtils.isNotBlank(String.valueOf(obj[NumericConstants.FIVE])) ? Double.parseDouble(String.valueOf(obj[NumericConstants.FIVE])) : 0.00) + (!Constant.NULL.equalsIgnoreCase(String.valueOf(obj[NumericConstants.SEVEN])) && StringUtils.isNotBlank(String.valueOf(obj[NumericConstants.SEVEN])) ? Double.parseDouble(String.valueOf(obj[NumericConstants.SEVEN])) : 0.00);
                    String stringAmount = String.valueOf(amt);
                    if (!actualFlag) {
                        stringAmount = CommonUtils.forecastConfigDataHide(projSelDTO.getFrequency(), projSelDTO.getForecastConfigPeriods(), commonColumn, stringAmount);
                    }
                    dto.addStringProperties(commonColumn + columnName, getFormatValue(NumericConstants.TWO, stringAmount, CURRENCY));

                    suppRate = getFormatValue(NumericConstants.TWO, suppRate, PERCENTAGE);
                    columnName = actualFlag ? Constant.ACTUALS_RATE : Constant.PROJECTIONS_RATE;
                    suppDTO.addStringProperties(commonColumn + columnName, suppRate);

                    Double rate = (StringUtils.isNotBlank(String.valueOf(obj[NumericConstants.SIX])) ? Double.parseDouble(String.valueOf(obj[NumericConstants.SIX])) : 0.00) + (!Constant.NULL.equalsIgnoreCase(String.valueOf(obj[NumericConstants.EIGHT])) && StringUtils.isNotBlank(String.valueOf(obj[NumericConstants.EIGHT])) ? Double.parseDouble(String.valueOf(obj[NumericConstants.EIGHT])) : 0.00);
                    String stringRate = String.valueOf(rate);
                    if (!actualFlag) {
                        stringRate = CommonUtils.forecastConfigDataHide(projSelDTO.getFrequency(), projSelDTO.getForecastConfigPeriods(), commonColumn, stringRate);
                    }
                    dto.addStringProperties(commonColumn + columnName, getFormatValue(NumericConstants.TWO, stringRate, PERCENTAGE));

                    mandRPU = getFormatValue(NumericConstants.TWO, mandRPU, CURRENCY);
                    columnName = actualFlag ? Constant.ACTUALS_RPU : Constant.PROJECTIONS_RPU;
                    mandatedDTO.addStringProperties(commonColumn + columnName, mandRPU);

                    suppRPU = getFormatValue(NumericConstants.TWO, suppRPU, CURRENCY);
                    columnName = actualFlag ? Constant.ACTUALS_RPU : Constant.PROJECTIONS_RPU;
                    suppDTO.addStringProperties(commonColumn + columnName, suppRPU);
                    Double rpu = (StringUtils.isNotBlank(String.valueOf(obj[NumericConstants.NINE])) ? Double.parseDouble(String.valueOf(obj[NumericConstants.NINE])) : 0.00) + (!Constant.NULL.equalsIgnoreCase(String.valueOf(obj[NumericConstants.TEN])) && StringUtils.isNotBlank(String.valueOf(obj[NumericConstants.TEN])) ? Double.parseDouble(String.valueOf(obj[NumericConstants.TEN])) : 0.00);
                    String stringRpu = String.valueOf(rpu);
                    if (!actualFlag) {
                        stringRpu = CommonUtils.forecastConfigDataHide(projSelDTO.getFrequency(), projSelDTO.getForecastConfigPeriods(), commonColumn, stringRpu);
                    }
                    dto.addStringProperties(commonColumn + columnName, getFormatValue(NumericConstants.TWO, stringRpu, CURRENCY));

                    if (!annualColumn.equals(StringUtils.EMPTY) && commonColumn.contains(annualColumn) && rightHeaderColumns.toString().contains(commonColumn)) {
                        if (actualFlag) {
                            annualMandamt = annualMandamt + (obj[NumericConstants.FIVE] != null ? Double.parseDouble(String.valueOf(obj[NumericConstants.FIVE])) : 0);
                            mandAmt = getFormatValue(NumericConstants.TWO, String.valueOf(annualMandamt), CURRENCY);
                            annualMandrate = annualMandrate + (obj[NumericConstants.SIX] != null ? Double.parseDouble(String.valueOf(obj[NumericConstants.SIX])) : 0);
                            mandRate = getFormatValue(NumericConstants.TWO, String.valueOf(annualMandrate), PERCENTAGE);
                            annualMandrpu = annualMandrpu + (obj[NumericConstants.NINE] != null ? Double.parseDouble(String.valueOf(obj[NumericConstants.NINE])) : 0);
                            mandRPU = getFormatValue(NumericConstants.TWO, String.valueOf(annualMandrpu), CURRENCY);
                            annualSuppamt = annualSuppamt + (obj[NumericConstants.SEVEN] != null ? Double.parseDouble(String.valueOf(obj[NumericConstants.SEVEN])) : 0);
                            suppAmt = getFormatValue(NumericConstants.TWO, String.valueOf(annualSuppamt), CURRENCY);
                            annualSupprate = annualSupprate + (obj[NumericConstants.EIGHT] != null ? Double.parseDouble(String.valueOf(obj[NumericConstants.EIGHT])) : 0);
                            suppRate = getFormatValue(NumericConstants.TWO, String.valueOf(annualSupprate), PERCENTAGE);
                            annualSupprpu = annualSupprpu + (obj[NumericConstants.TEN] != null ? Double.parseDouble(String.valueOf(obj[NumericConstants.TEN])) : 0);
                            suppRPU = getFormatValue(NumericConstants.TWO, String.valueOf(annualSupprpu), CURRENCY);
                            dto.addStringProperties(annualColumn + Constant.ACTUALS_AMOUNT, getFormatValue(NumericConstants.TWO, String.valueOf(annualMandamt + annualSuppamt), CURRENCY));
                            dto.addStringProperties(annualColumn + Constant.ACTUALS_RATE, getFormatValue(NumericConstants.TWO, String.valueOf(annualMandrate + annualSupprate), PERCENTAGE));
                            dto.addStringProperties(annualColumn + Constant.ACTUALS_RPU, getFormatValue(NumericConstants.TWO, String.valueOf(annualMandrpu + annualSupprpu), CURRENCY));
                        } else {
                            annualMandProjamt = annualMandProjamt + (obj[NumericConstants.FIVE] != null ? Double.parseDouble(String.valueOf(obj[NumericConstants.FIVE])) : 0);
                            annualMandProjrate = annualMandProjrate + (obj[NumericConstants.SIX] != null ? Double.parseDouble(String.valueOf(obj[NumericConstants.SIX])) : 0);
                            annualMandProjrpu = annualMandProjrpu + (obj[NumericConstants.NINE] != null ? Double.parseDouble(String.valueOf(obj[NumericConstants.NINE])) : 0);
                            annualSuppProjamt = annualSuppProjamt + (obj[NumericConstants.SEVEN] != null ? Double.parseDouble(String.valueOf(obj[NumericConstants.SEVEN])) : 0);
                            annualSuppProjrate = annualSuppProjrate + (obj[NumericConstants.EIGHT] != null ? Double.parseDouble(String.valueOf(obj[NumericConstants.EIGHT])) : 0);
                            annualSuppProjrpu = annualSuppProjrpu + (obj[NumericConstants.TEN] != null ? Double.parseDouble(String.valueOf(obj[NumericConstants.TEN])) : 0);

                            String stringAnnualMandProjamt = String.valueOf(annualMandProjamt);
                            String stringAnnualMandProjrate = String.valueOf(annualMandProjrate);
                            String stringAnnualMandProjrpu = String.valueOf(annualMandProjrpu);
                            String stringAnnualSuppProjamt = String.valueOf(annualSuppProjamt);
                            String stringAnnualSuppProjrate = String.valueOf(annualSuppProjrate);
                            String stringAnnualSuppProjrpu = String.valueOf(annualSuppProjrpu);

                            if (!actualFlag) {
                                stringAnnualMandProjamt = CommonUtils.forecastConfigDataHide(projSelDTO.getFrequency(), projSelDTO.getForecastConfigPeriods(), commonColumn, stringAnnualMandProjamt);
                                stringAnnualMandProjrate = CommonUtils.forecastConfigDataHide(projSelDTO.getFrequency(), projSelDTO.getForecastConfigPeriods(), commonColumn, stringAnnualMandProjrate);
                                stringAnnualMandProjrpu = CommonUtils.forecastConfigDataHide(projSelDTO.getFrequency(), projSelDTO.getForecastConfigPeriods(), commonColumn, stringAnnualMandProjrpu);
                                stringAnnualSuppProjamt = CommonUtils.forecastConfigDataHide(projSelDTO.getFrequency(), projSelDTO.getForecastConfigPeriods(), commonColumn, stringAnnualSuppProjamt);
                                stringAnnualSuppProjrate = CommonUtils.forecastConfigDataHide(projSelDTO.getFrequency(), projSelDTO.getForecastConfigPeriods(), commonColumn, stringAnnualSuppProjrate);
                                stringAnnualSuppProjrpu = CommonUtils.forecastConfigDataHide(projSelDTO.getFrequency(), projSelDTO.getForecastConfigPeriods(), commonColumn, stringAnnualSuppProjrpu);
                            }

                            mandAmt = getFormatValue(NumericConstants.TWO, stringAnnualMandProjamt, CURRENCY);
                            mandRate = getFormatValue(NumericConstants.TWO, stringAnnualMandProjrate, PERCENTAGE);
                            mandRPU = getFormatValue(NumericConstants.TWO, stringAnnualMandProjrpu, CURRENCY);
                            suppAmt = getFormatValue(NumericConstants.TWO, stringAnnualSuppProjamt, CURRENCY);
                            suppRate = getFormatValue(NumericConstants.TWO, stringAnnualSuppProjrate, PERCENTAGE);
                            suppRPU = getFormatValue(NumericConstants.TWO, stringAnnualSuppProjrpu, CURRENCY);

                            dto.addStringProperties(annualColumn + Constant.PROJECTIONS_AMOUNT, getFormatValue(NumericConstants.TWO, getSummedData(stringAnnualMandProjamt, stringAnnualSuppProjamt), CURRENCY));
                            dto.addStringProperties(annualColumn + Constant.PROJECTIONS_RATE, getFormatValue(NumericConstants.TWO, getSummedData(stringAnnualMandProjrate, stringAnnualSuppProjrate), PERCENTAGE));
                            dto.addStringProperties(annualColumn + Constant.PROJECTIONS_RPU, getFormatValue(NumericConstants.TWO, getSummedData(stringAnnualMandProjrpu, stringAnnualSuppProjrpu), CURRENCY));
                        }
                        columnName = actualFlag ? Constant.ACTUALS_AMOUNT : Constant.PROJECTIONS_AMOUNT;
                        mandatedDTO.addStringProperties(annualColumn + columnName, mandAmt);

                        columnName = actualFlag ? Constant.ACTUALS_RATE : Constant.PROJECTIONS_RATE;
                        mandatedDTO.addStringProperties(annualColumn + columnName, mandRate);

                        columnName = actualFlag ? Constant.ACTUALS_RPU : Constant.PROJECTIONS_RPU;
                        mandatedDTO.addStringProperties(annualColumn + columnName, mandRPU);

                        columnName = actualFlag ? Constant.ACTUALS_AMOUNT : Constant.PROJECTIONS_AMOUNT;
                        suppDTO.addStringProperties(annualColumn + columnName, suppAmt);

                        columnName = actualFlag ? Constant.ACTUALS_RATE : Constant.PROJECTIONS_RATE;
                        suppDTO.addStringProperties(annualColumn + columnName, suppRate);

                        columnName = actualFlag ? Constant.ACTUALS_RPU : Constant.PROJECTIONS_RPU;
                        suppDTO.addStringProperties(annualColumn + columnName, suppRPU);
                    }
                }

            }
            dto.setManDTO(mandatedDTO);
            dto.setSuppDTO(suppDTO);
        }
        return dto;
    }

    public static String getHierarchyLevelQuery(String hierarchyNo, String frequency, ProjectionSelectionDTO projSelDTO, String freqChar) {
        String selectedHierQury = "IF Object_id('TEMPDB..#SELECTED_HIERARCHY_NO') IS NOT NULL\n"
                + "  DROP TABLE #SELECTED_HIERARCHY_NO\n"
                + "\n"
                + "CREATE TABLE #SELECTED_HIERARCHY_NO\n"
                + "  (\n"
                + "     HIERARCHY_NO    VARCHAR(50),\n"
                + "     CCP_DETAILS_SID INT\n"
                + "  )\n"
                + "\n"
                + "INSERT INTO #SELECTED_HIERARCHY_NO\n"
                + "            (HIERARCHY_NO,\n"
                + "             CCP_DETAILS_SID)\n"
                + "SELECT DISTINCT HIERARCHY_NO,\n"
                + "                CH.CCP_DETAILS_SID\n"
                + "FROM   ( VALUES('" + hierarchyNo + "%') ) A(HIERARCHY_NO)\n"
                + "       JOIN ST_CCP_HIERARCHY CH\n"
                + "         ON CH.CUST_HIERARCHY_NO LIKE A.HIERARCHY_NO + '%' ";
        String customSQL = selectedHierQury + "SELECT \n"
                + "  '' as   LEVEL_NO,\n"
                + "   '' as     LEVEL_NAME,\n"
                + "  '' as      RELATIONSHIP_LEVEL_VALUES,\n"
                + Constant.PD_HIERARCHY_NO
                + "p.\"YEAR\",\n"
                + "       Sum(MAD.ACTUAL_SALES)        AS  Disc_Amt,\n"
                + "Coalesce(Sum(MAD.ACTUAL_SALES)/Nullif(Sum(m_ac.ACTUAL_SALES),0),0)*100       AS  Disc_Rate,\n"
                + "       Sum(SPMA.ACTUAL_SALES)        AS  Supp_Amt,\n"
                + "Coalesce(Sum(SPMA.ACTUAL_SALES)/Nullif(Sum(m_ac.ACTUAL_SALES),0),0)*100      AS  Supp_Rate,\n"
                + " Coalesce(Sum(MAD.ACTUAL_SALES)/Nullif(Sum(m_ac.ACTUAL_UNITS),0),0) AS Man_RPU, Coalesce(Sum(SPMA.ACTUAL_SALES)/Nullif(Sum(m_ac.ACTUAL_UNITS),0),0) As Sup_RPU,\n"
                + "       0                            As ActualProj\n";
        if (!Constant.YEAR_SPACE.equalsIgnoreCase(frequency)) {
            customSQL += "         , p." + frequency + "\n";
        }
        customSQL += "FROM #SELECTED_HIERARCHY_NO pd \n";

        customSQL += Constant.JOIN_SPACE
                + "  ST_M_SALES_PROJECTION_MASTER "
                + " m_mas ON pd.CCP_DETAILS_SID = m_mas.CCP_DETAILS_SID\n"
                + Constant.JOIN_SPACE
                + "  ST_M_ACTUAL_SALES "
                + " m_ac ON m_mas.CCP_DETAILS_SID = m_ac.CCP_DETAILS_SID\n"
                + "JOIN  "
                + "  ST_M_ACTUAL_DISCOUNT "
                + " MAD ON MAD.CCP_DETAILS_SID = pd.CCP_DETAILS_SID\n"
                + "AND MAD.PERIOD_SID=m_ac.PERIOD_SID\n"
                + Constant.LEFT_JOIN
                + "  ST_M_SUPPLEMENTAL_DISC_MASTER "
                + " SPM ON SPM.CCP_DETAILS_SID = pd.CCP_DETAILS_SID\n"
                + Constant.LEFT_JOIN
                + "  ST_M_SUPPLEMENTAL_DISC_ACTUALS "
                + " SPMA ON SPMA.CCP_DETAILS_SID = pd.CCP_DETAILS_SID\n"
                + "										   AND SPMA.PERIOD_SID=m_ac.PERIOD_SID\n"
                + "JOIN   PERIOD p ON p.period_sid = m_ac.PERIOD_SID\n";
        if (!Constant.PERIOD.equalsIgnoreCase(projSelDTO.getPivotView())) {
            if (!Constant.YEAR_SPACE.equalsIgnoreCase(frequency)) {

                customSQL += "AND p.PERIOD_SID between (SELECT MIN(PERIOD_SID) FROM PERIOD WHERE '" + freqChar + "'+CONVERT(VARCHAR(2)," + frequency + ")+' '+CONVERT(VARCHAR(4),YEAR)='" + freqChar + projSelDTO.getHistoryStartPeriod() + " " + projSelDTO.getHistoryStartYear() + "')"
                        + " and (SELECT MAX(PERIOD_SID) FROM PERIOD WHERE 'M'+CONVERT(VARCHAR(2),\"MONTH\")+' '+CONVERT(VARCHAR(4),YEAR)='M" + projSelDTO.getForecastDTO().getForecastEndMonth() + " " + projSelDTO.getForecastDTO().getForecastEndYear() + "')";
            } else {
                customSQL += "AND p.PERIOD_SID>=(SELECT MIN(PERIOD_SID) FROM PERIOD WHERE YEAR='" + projSelDTO.getHistoryStartYear() + "')";
            }

        }

        customSQL += "GROUP  BY \n";
        if (!Constant.YEAR_SPACE.equalsIgnoreCase(frequency)) {
            customSQL += "          p." + frequency + ",\n";
        }
        customSQL += Constant.PD_HIERARCHY_NO
                + "          p.\"YEAR\"\n"
                + "Union ALL \n"
                + "\n"
                + "SELECT\n"
                + "   '' as   LEVEL_NO,\n"
                + "  '' as   LEVEL_NAME,\n"
                + "     '' as   RELATIONSHIP_LEVEL_VALUES,\n"
                + Constant.PD_HIERARCHY_NO
                + "p.\"YEAR\",\n"
                + "       Sum(MAD.PROJECTION_SALES)        AS  Disc_Amt,\n"
                + "Coalesce(Sum(MAD.PROJECTION_SALES)/Nullif(Sum(m_ac.PROJECTION_SALES),0),0)*100 AS  Disc_Rate,\n"
                + "       Sum(SPMA.PROJECTION_SALES)        AS  Supp_Amt,\n"
                + "Coalesce(Sum(SPMA.PROJECTION_SALES)/Nullif(Sum(m_ac.PROJECTION_SALES),0),0)*100 AS  Supp_Rate,\n"
                + " Coalesce(Sum(MAD.PROJECTION_SALES)/Nullif(Sum(m_ac.PROJECTION_UNITS),0),0) AS Man_RPU, Coalesce(Sum(SPMA.PROJECTION_SALES)/Nullif(Sum(m_ac.PROJECTION_UNITS),0),0) As Sup_RPU, \n"
                + "       1                            As ActualProj\n";
        if (!Constant.YEAR_SPACE.equalsIgnoreCase(frequency)) {
            customSQL += "         , p." + frequency + "\n";
        }
        customSQL += "	FROM #SELECTED_HIERARCHY_NO pd \n ";

        customSQL += Constant.JOIN_SPACE
                + "  ST_M_SALES_PROJECTION_MASTER "
                + "m_mas ON pd.CCP_DETAILS_SID = m_mas.CCP_DETAILS_SID\n"
                + "JOIN   "
                + " ST_M_SALES_PROJECTION"
                + " m_ac ON m_mas.CCP_DETAILS_SID = m_ac.CCP_DETAILS_SID\n"
                + "JOIN   "
                + " ST_M_DISCOUNT_PROJECTION "
                + " MAD ON MAD.CCP_DETAILS_SID = pd.CCP_DETAILS_SID\n"
                + "AND MAD.PERIOD_SID=m_ac.PERIOD_SID\n"
                + "LEFT JOIN  "
                + " ST_M_SUPPLEMENTAL_DISC_MASTER "
                + " SPM ON SPM.CCP_DETAILS_SID = pd.CCP_DETAILS_SID \n"
                + Constant.LEFT_JOIN
                + "  ST_M_SUPPLEMENTAL_DISC_PROJ "
                + " SPMA ON SPMA.CCP_DETAILS_SID = pd.CCP_DETAILS_SID\n"
                + "                                                                        AND SPMA.PERIOD_SID=m_ac.PERIOD_SID\n"
                + "JOIN   PERIOD p ON p.period_sid = m_ac.PERIOD_SID\n";
        if (!Constant.PERIOD.equalsIgnoreCase(projSelDTO.getPivotView())) {
            if (!Constant.YEAR_SPACE.equalsIgnoreCase(frequency)) {
                customSQL += "AND p.PERIOD_SID between (SELECT MIN(PERIOD_SID) FROM PERIOD WHERE '" + freqChar + "'+CONVERT(VARCHAR(2)," + frequency + ")+' '+CONVERT(VARCHAR(4),YEAR)='" + freqChar + projSelDTO.getHistoryStartPeriod() + " " + projSelDTO.getHistoryStartYear() + "')"
                        + " and (SELECT MAX(PERIOD_SID) FROM PERIOD WHERE 'M'+CONVERT(VARCHAR(2),\"MONTH\")+' '+CONVERT(VARCHAR(4),YEAR)='M" + projSelDTO.getForecastDTO().getForecastEndMonth() + " " + projSelDTO.getForecastDTO().getForecastEndYear() + "')";
            } else {
                customSQL += "AND p.PERIOD_SID>=(SELECT MIN(PERIOD_SID) FROM PERIOD WHERE YEAR='" + projSelDTO.getHistoryStartYear() + "')";
            }
        }

        customSQL += "GROUP  BY ";
        if (!Constant.YEAR_SPACE.equalsIgnoreCase(frequency)) {
            customSQL += "        p." + frequency + ",\n";
        }
        customSQL += Constant.PD_HIERARCHY_NO
                + " p.\"YEAR\"\n"
                + "Order By p.\"YEAR\"\n";
        if (!Constant.YEAR_SPACE.equalsIgnoreCase(frequency)) {
            customSQL += "          ,p." + frequency + "\n";
        }
        customSQL = QueryUtil.replaceTableNames(customSQL, projSelDTO.getSessionDTO().getCurrentTableNames());
        return customSQL;
    }

    /**
     * This method is used to load the aggregate value of child node in pivot
     * mode
     *
     * @param dto
     * @param projSelDTO
     * @return
     *
     */
    public DiscountProjectionResultsDTO getPivotChildNodeValues(DiscountProjectionResultsDTO dto, ProjectionSelectionDTO projSelDTO) {
        Map<Integer, String> freq = new HashMap<>();
        freq.put(1, Constant.YEAR_SPACE);
        freq.put(NumericConstants.TWO, Constant.SEMI_ANNUAL);
        freq.put(NumericConstants.FOUR, Constant.QUARTER);
        freq.put(NumericConstants.TWELVE, Constant.MONTH_SPACE);
        String frequencyPivot = freq.get(projSelDTO.getFrequencyDivision());
        String freqCharPivot = frequencyDPR(frequencyPivot);
        String query = getHierarchyLevelQuery(projSelDTO.getHierarchyNo(), frequencyPivot, projSelDTO, freqCharPivot);
        List<Object> list = (List<Object>) CommonLogic.executeSelectQuery(query);
        if (projSelDTO.getProjectionOrder().equalsIgnoreCase(Constant.DESCENDING)) {
            Collections.reverse(list);
        }
        List<DiscountProjectionResultsDTO> childList = new ArrayList<>();
        try {
            if (list != null && !list.isEmpty()) {
                int frequencyDivision = projSelDTO.getFrequencyDivision();
                String lastPeriod = StringUtils.EMPTY;
                boolean actualFlag = false;
                DiscountProjectionResultsDTO periodDTO = null;
                List<Object> pcList = addProgramCodeDiscounts(projSelDTO);
                for (int i = 0; i < list.size(); i++) {

                    String columnName;
                    final Object[] obj = (Object[]) list.get(i);
                    actualFlag = Integer.parseInt(String.valueOf(obj[NumericConstants.ELEVEN])) == 0;
                    String mandSupp = projSelDTO.getMandatedOrSupp();

                    int year = Integer.parseInt(String.valueOf(obj[NumericConstants.FOUR]));
                    int period = projSelDTO.getFrequencyDivision() == 1 ? 0 : Integer.parseInt(String.valueOf(obj[NumericConstants.TWELVE]));
                    List<String> common = HeaderUtils.getCommonColumnHeader(frequencyDivision, year, period, false);
                    String column = common.get(0);
                    String commonHeader = common.get(1);
                    String commonColumn;

                    if (lastPeriod.isEmpty() || !lastPeriod.equalsIgnoreCase(common.get(1))) {
                        if (periodDTO != null) {
                            customizePCDisc(pcList, periodDTO, projSelDTO, lastPeriod, freq.get(projSelDTO.getFrequencyDivision()));
                            childList.add(periodDTO);
                        }
                        periodDTO = new DiscountProjectionResultsDTO();
                        periodDTO.setParent(0);
                        lastPeriod = common.get(1);
                    }
                    periodDTO.setLevelValue(commonHeader);
                    periodDTO.setRelationshipLevelName(commonHeader);
                    if (Constant.MANDATED.equalsIgnoreCase(mandSupp)) {
                        String mandAmt = StringUtils.EMPTY + obj[NumericConstants.FIVE];
                        String mandRate = StringUtils.EMPTY + obj[NumericConstants.SIX];
                        String mandRPU = StringUtils.EMPTY + obj[NumericConstants.NINE];

                        if (!actualFlag) {
                            mandAmt = CommonUtils.forecastConfigDataHide(projSelDTO.getFrequency(), projSelDTO.getForecastConfigPeriods(), column, mandAmt);
                            mandRate = CommonUtils.forecastConfigDataHide(projSelDTO.getFrequency(), projSelDTO.getForecastConfigPeriods(), column, mandRate);
                            mandRPU = CommonUtils.forecastConfigDataHide(projSelDTO.getFrequency(), projSelDTO.getForecastConfigPeriods(), column, mandRPU);
                        }

                        commonColumn = Constant.MANDATED_DISCOUNT1;
                        mandAmt = getFormatValue(NumericConstants.TWO, mandAmt, CURRENCY);
                        columnName = actualFlag ? Constant.ACTUALS_AMOUNT : Constant.PROJECTIONS_AMOUNT;
                        periodDTO.addStringProperties(commonColumn + columnName, mandAmt);

                        mandRate = getFormatValue(NumericConstants.TWO, mandRate, PERCENTAGE);
                        columnName = actualFlag ? Constant.ACTUALS_RATE : Constant.PROJECTIONS_RATE;
                        periodDTO.addStringProperties(commonColumn + columnName, mandRate);

                        mandRPU = getFormatValue(NumericConstants.TWO, mandRPU, CURRENCY);
                        columnName = actualFlag ? Constant.ACTUALS_RPU : Constant.PROJECTIONS_RPU;
                        periodDTO.addStringProperties(commonColumn + columnName, mandRPU);

                        commonColumn = Constant.TOTALDISCOUNT;
                        columnName = actualFlag ? Constant.ACTUALS_AMOUNT : Constant.PROJECTIONS_AMOUNT;
                        periodDTO.addStringProperties(commonColumn + columnName, mandAmt);
                        columnName = actualFlag ? Constant.ACTUALS_RATE : Constant.PROJECTIONS_RATE;
                        periodDTO.addStringProperties(commonColumn + columnName, mandRate);
                        columnName = actualFlag ? Constant.ACTUALS_RPU : Constant.PROJECTIONS_RPU;
                        periodDTO.addStringProperties(commonColumn + columnName, mandRPU);
                    }
                    if (SUPPLEMENTAL.equalsIgnoreCase(mandSupp)) {

                        String suppAmt = StringUtils.EMPTY + obj[NumericConstants.SEVEN];
                        String suppRate = StringUtils.EMPTY + obj[NumericConstants.EIGHT];
                        String suppRPU = StringUtils.EMPTY + obj[NumericConstants.TEN];

                        if (!actualFlag) {
                            suppAmt = CommonUtils.forecastConfigDataHide(projSelDTO.getFrequency(), projSelDTO.getForecastConfigPeriods(), column, suppAmt);
                            suppRate = CommonUtils.forecastConfigDataHide(projSelDTO.getFrequency(), projSelDTO.getForecastConfigPeriods(), column, suppRate);
                            suppRPU = CommonUtils.forecastConfigDataHide(projSelDTO.getFrequency(), projSelDTO.getForecastConfigPeriods(), column, suppRPU);
                        }

                        commonColumn = SUPPLEMENTAL_DISCOUNT1;
                        suppAmt = getFormatValue(NumericConstants.TWO, suppAmt, CURRENCY);
                        columnName = actualFlag ? Constant.ACTUALS_AMOUNT : Constant.PROJECTIONS_AMOUNT;
                        periodDTO.addStringProperties(commonColumn + columnName, suppAmt);

                        suppRate = getFormatValue(NumericConstants.TWO, suppRate, PERCENTAGE);
                        columnName = actualFlag ? Constant.ACTUALS_RATE : Constant.PROJECTIONS_RATE;
                        periodDTO.addStringProperties(commonColumn + columnName, suppRate);

                        suppRPU = getFormatValue(NumericConstants.TWO, suppRPU, CURRENCY);
                        columnName = actualFlag ? Constant.ACTUALS_RPU : Constant.PROJECTIONS_RPU;
                        periodDTO.addStringProperties(commonColumn + columnName, suppRPU);

                        commonColumn = Constant.TOTALDISCOUNT;
                        columnName = actualFlag ? Constant.ACTUALS_AMOUNT : Constant.PROJECTIONS_AMOUNT;
                        periodDTO.addStringProperties(commonColumn + columnName, suppAmt);
                        columnName = actualFlag ? Constant.ACTUALS_RATE : Constant.PROJECTIONS_RATE;
                        periodDTO.addStringProperties(commonColumn + columnName, suppRate);
                        columnName = actualFlag ? Constant.ACTUALS_RPU : Constant.PROJECTIONS_RPU;
                        periodDTO.addStringProperties(commonColumn + columnName, suppRPU);
                    }
                    if (Constant.BOTH_SMALL.equalsIgnoreCase(mandSupp)) {
                        commonColumn = Constant.MANDATED_DISCOUNT1;
                        String mandAmt = StringUtils.EMPTY + obj[NumericConstants.FIVE];
                        String mandRate = StringUtils.EMPTY + obj[NumericConstants.SIX];
                        String mandRPU = StringUtils.EMPTY + obj[NumericConstants.NINE];

                        if (!actualFlag) {
                            mandAmt = CommonUtils.forecastConfigDataHide(projSelDTO.getFrequency(), projSelDTO.getForecastConfigPeriods(), column, mandAmt);
                            mandRate = CommonUtils.forecastConfigDataHide(projSelDTO.getFrequency(), projSelDTO.getForecastConfigPeriods(), column, mandRate);
                            mandRPU = CommonUtils.forecastConfigDataHide(projSelDTO.getFrequency(), projSelDTO.getForecastConfigPeriods(), column, mandRPU);
                        }

                        mandAmt = getFormatValue(NumericConstants.TWO, mandAmt, CURRENCY);
                        columnName = actualFlag ? Constant.ACTUALS_AMOUNT : Constant.PROJECTIONS_AMOUNT;
                        periodDTO.addStringProperties(commonColumn + columnName, mandAmt);

                        mandRate = getFormatValue(NumericConstants.TWO, mandRate, PERCENTAGE);
                        columnName = actualFlag ? Constant.ACTUALS_RATE : Constant.PROJECTIONS_RATE;
                        periodDTO.addStringProperties(commonColumn + columnName, mandRate);

                        mandRPU = getFormatValue(NumericConstants.TWO, mandRPU, CURRENCY);
                        columnName = actualFlag ? Constant.ACTUALS_RPU : Constant.PROJECTIONS_RPU;
                        periodDTO.addStringProperties(commonColumn + columnName, mandRPU);

                        commonColumn = SUPPLEMENTAL_DISCOUNT1;

                        String suppAmt = StringUtils.EMPTY + obj[NumericConstants.SEVEN];
                        String suppRate = StringUtils.EMPTY + obj[NumericConstants.EIGHT];
                        String suppRPU = StringUtils.EMPTY + obj[NumericConstants.TEN];

                        if (!actualFlag) {
                            suppAmt = CommonUtils.forecastConfigDataHide(projSelDTO.getFrequency(), projSelDTO.getForecastConfigPeriods(), column, suppAmt);
                            suppRate = CommonUtils.forecastConfigDataHide(projSelDTO.getFrequency(), projSelDTO.getForecastConfigPeriods(), column, suppRate);
                            suppRPU = CommonUtils.forecastConfigDataHide(projSelDTO.getFrequency(), projSelDTO.getForecastConfigPeriods(), column, suppRPU);
                        }

                        suppAmt = getFormatValue(NumericConstants.TWO, suppAmt, CURRENCY);
                        columnName = actualFlag ? Constant.ACTUALS_AMOUNT : Constant.PROJECTIONS_AMOUNT;
                        periodDTO.addStringProperties(commonColumn + columnName, suppAmt);

                        suppRate = getFormatValue(NumericConstants.TWO, suppRate, PERCENTAGE);
                        columnName = actualFlag ? Constant.ACTUALS_RATE : Constant.PROJECTIONS_RATE;
                        periodDTO.addStringProperties(commonColumn + columnName, suppRate);

                        suppRPU = getFormatValue(NumericConstants.TWO, suppRPU, CURRENCY);
                        columnName = actualFlag ? Constant.ACTUALS_RPU : Constant.PROJECTIONS_RPU;
                        periodDTO.addStringProperties(commonColumn + columnName, suppRPU);

                        commonColumn = Constant.TOTALDISCOUNT;
                        Double amt = (StringUtils.isNotBlank(String.valueOf(obj[NumericConstants.FIVE])) ? Double.parseDouble(String.valueOf(obj[NumericConstants.FIVE])) : 0.00) + (!Constant.NULL.equalsIgnoreCase(String.valueOf(obj[NumericConstants.SEVEN])) && StringUtils.isNotBlank(String.valueOf(obj[NumericConstants.SEVEN])) ? Double.parseDouble(String.valueOf(obj[NumericConstants.SEVEN])) : 0.00);
                        Double rate = (StringUtils.isNotBlank(String.valueOf(obj[NumericConstants.SIX])) ? Double.parseDouble(String.valueOf(obj[NumericConstants.SIX])) : 0.00) + (!Constant.NULL.equalsIgnoreCase(String.valueOf(obj[NumericConstants.EIGHT])) && StringUtils.isNotBlank(String.valueOf(obj[NumericConstants.EIGHT])) ? Double.parseDouble(String.valueOf(obj[NumericConstants.EIGHT])) : 0.00);
                        Double rpu = (StringUtils.isNotBlank(String.valueOf(obj[NumericConstants.NINE])) ? Double.parseDouble(String.valueOf(obj[NumericConstants.NINE])) : 0.00) + (!Constant.NULL.equalsIgnoreCase(String.valueOf(obj[NumericConstants.TEN])) && StringUtils.isNotBlank(String.valueOf(obj[NumericConstants.TEN])) ? Double.parseDouble(String.valueOf(obj[NumericConstants.TEN])) : 0.00);

                        String stringAmt = String.valueOf(amt);
                        String stringRate = String.valueOf(rate);
                        String stringRpu = String.valueOf(rpu);
                        if (!actualFlag) {
                            stringAmt = CommonUtils.forecastConfigDataHide(projSelDTO.getFrequency(), projSelDTO.getForecastConfigPeriods(), column, stringAmt);
                            stringRate = CommonUtils.forecastConfigDataHide(projSelDTO.getFrequency(), projSelDTO.getForecastConfigPeriods(), column, stringRate);
                            stringRpu = CommonUtils.forecastConfigDataHide(projSelDTO.getFrequency(), projSelDTO.getForecastConfigPeriods(), column, stringRpu);
                        }
                        columnName = actualFlag ? Constant.ACTUALS_AMOUNT : Constant.PROJECTIONS_AMOUNT;
                        periodDTO.addStringProperties(commonColumn + columnName, getFormatValue(NumericConstants.TWO, stringAmt, CURRENCY));
                        columnName = actualFlag ? Constant.ACTUALS_RATE : Constant.PROJECTIONS_RATE;
                        periodDTO.addStringProperties(commonColumn + columnName, getFormatValue(NumericConstants.TWO, stringRate, PERCENTAGE));
                        columnName = actualFlag ? Constant.ACTUALS_RPU : Constant.PROJECTIONS_RPU;
                        periodDTO.addStringProperties(commonColumn + columnName, getFormatValue(NumericConstants.TWO, stringRpu, CURRENCY));
                    }
                    if (i == list.size() - 1) {
                        customizePCDisc(pcList, periodDTO, projSelDTO, lastPeriod, freq.get(projSelDTO.getFrequencyDivision()));
                        childList.add(periodDTO);
                    }
                }

            }
        } catch (NumberFormatException e) {
            LOGGER.error(e.getMessage());
        }
        dto.setDprDTOList(childList);
        return dto;

    }

    private List<DiscountProjectionResultsDTO> getCustomizedPivotChildNodes(List<DiscountProjectionResultsDTO> projectionDtoList, ProjectionSelectionDTO projSelDTO) {
        if (projSelDTO.getDprDTOList() != null && !projSelDTO.getDprDTOList().isEmpty()) {
            for (DiscountProjectionResultsDTO discountProjectionResultsDTO : projSelDTO.getDprDTOList()) {
                projectionDtoList.add(discountProjectionResultsDTO);
            }
        }
        return projectionDtoList;
    }

    private List<Object> addProgramCodeDiscounts(ProjectionSelectionDTO projSelDTO) {
        List<Object> list;
        Map<Integer, String> freq = new HashMap<>();
        freq.put(1, Constant.YEAR_SPACE);
        freq.put(NumericConstants.TWO, Constant.SEMI_ANNUAL);
        freq.put(NumericConstants.FOUR, Constant.QUARTER);
        freq.put(NumericConstants.TWELVE, Constant.MONTH_SPACE);
        String frequencyDiscounts = freq.get(projSelDTO.getFrequencyDivision());
        String freqCharDiscounts = frequencyDPR(frequencyDiscounts);
        list = (List<Object>) CommonLogic.executeSelectQuery(getProgramCodeQuery(projSelDTO.getHierarchyNo(), frequencyDiscounts, projSelDTO, freqCharDiscounts));
        return list;
    }

    private void customizePCDisc(List<Object> list, DiscountProjectionResultsDTO periodDTO, ProjectionSelectionDTO projSelDTO, String commonHeader, String freq) {
        if (list != null && !list.isEmpty()) {
            int frequencyDivision = projSelDTO.getFrequencyDivision();
            String lastPeriod;
            boolean actualFlag = false;
            for (int i = 0; i < list.size(); i++) {

                String columnName;
                final Object[] obj = (Object[]) list.get(i);
                actualFlag = Integer.parseInt(String.valueOf(obj[NumericConstants.ELEVEN])) == 0;

                int year = Integer.parseInt(String.valueOf(obj[NumericConstants.FOUR]));
                int period = projSelDTO.getFrequencyDivision() == 1 ? 0 : Integer.parseInt(String.valueOf(obj[NumericConstants.TWELVE]));
                List<String> common = HeaderUtils.getCommonColumnHeader(frequencyDivision, year, period, false);
                String column = common.get(0);
                lastPeriod = Constant.YEAR_SPACE.equalsIgnoreCase(freq) ? String.valueOf(obj[NumericConstants.FOUR]) : String.valueOf(obj[NumericConstants.THIRTEEN]);
                if (!lastPeriod.isEmpty() && commonHeader.equalsIgnoreCase(lastPeriod)) {
                    String commonColumn = obj[NumericConstants.TWO] + "m";
                    String mandAmt = StringUtils.EMPTY + obj[NumericConstants.FIVE];
                    String mandRate = StringUtils.EMPTY + obj[NumericConstants.SIX];
                    String mandRPU = StringUtils.EMPTY + obj[NumericConstants.NINE];
                    String suppAmt = StringUtils.EMPTY + obj[NumericConstants.SEVEN];
                    String suppRate = StringUtils.EMPTY + obj[NumericConstants.EIGHT];
                    String suppRPU = StringUtils.EMPTY + obj[NumericConstants.TEN];

                    if (!actualFlag) {
                        mandAmt = CommonUtils.forecastConfigDataHide(projSelDTO.getFrequency(), projSelDTO.getForecastConfigPeriods(), column, mandAmt);
                        mandRate = CommonUtils.forecastConfigDataHide(projSelDTO.getFrequency(), projSelDTO.getForecastConfigPeriods(), column, mandRate);
                        mandRPU = CommonUtils.forecastConfigDataHide(projSelDTO.getFrequency(), projSelDTO.getForecastConfigPeriods(), column, mandRPU);
                        suppAmt = CommonUtils.forecastConfigDataHide(projSelDTO.getFrequency(), projSelDTO.getForecastConfigPeriods(), column, suppAmt);
                        suppRate = CommonUtils.forecastConfigDataHide(projSelDTO.getFrequency(), projSelDTO.getForecastConfigPeriods(), column, suppRate);
                        suppRPU = CommonUtils.forecastConfigDataHide(projSelDTO.getFrequency(), projSelDTO.getForecastConfigPeriods(), column, suppRPU);
                    }
                    mandAmt = getFormatValue(NumericConstants.TWO, mandAmt, CURRENCY);
                    columnName = actualFlag ? Constant.ACTUALS_AMOUNT : Constant.PROJECTIONS_AMOUNT;
                    periodDTO.addStringProperties(commonColumn + columnName, mandAmt);

                    mandRate = getFormatValue(NumericConstants.TWO, mandRate, PERCENTAGE);
                    columnName = actualFlag ? Constant.ACTUALS_RATE : Constant.PROJECTIONS_RATE;
                    periodDTO.addStringProperties(commonColumn + columnName, mandRate);

                    mandRPU = getFormatValue(NumericConstants.TWO, mandRPU, CURRENCY);
                    columnName = actualFlag ? Constant.ACTUALS_RPU : Constant.PROJECTIONS_RPU;
                    periodDTO.addStringProperties(commonColumn + columnName, mandRPU);

                    commonColumn = obj[NumericConstants.TWO] + Constant.S_SMALL;

                    suppAmt = getFormatValue(NumericConstants.TWO, suppAmt, CURRENCY);
                    columnName = actualFlag ? Constant.ACTUALS_AMOUNT : Constant.PROJECTIONS_AMOUNT;
                    periodDTO.addStringProperties(commonColumn + columnName, suppAmt);

                    suppRate = getFormatValue(NumericConstants.TWO, suppRate, PERCENTAGE);
                    columnName = actualFlag ? Constant.ACTUALS_RATE : Constant.PROJECTIONS_RATE;
                    periodDTO.addStringProperties(commonColumn + columnName, suppRate);

                    suppRPU = getFormatValue(NumericConstants.TWO, suppRPU, CURRENCY);
                    columnName = actualFlag ? Constant.ACTUALS_RPU : Constant.PROJECTIONS_RPU;
                    periodDTO.addStringProperties(commonColumn + columnName, suppRPU);
                }
            }
        }
    }

    public List<DiscountProjectionResultsDTO> getConfiguredResultsTotal(ProjectionSelectionDTO projSelDTO) {
        List<DiscountProjectionResultsDTO> totalDTO = new ArrayList<>();
        List<Integer> yearList = new ArrayList<>();
        yearList.add(projSelDTO.getForecastDTO().getHistoryStartYear());
        yearList.add(projSelDTO.getForecastDTO().getHistoryStartMonth());
        yearList.add(projSelDTO.getForecastDTO().getHistoryEndYear());
        yearList.add(projSelDTO.getForecastDTO().getHistoryEndMonth());

        yearList.add(projSelDTO.getForecastDTO().getForecastStartYear());
        yearList.add(projSelDTO.getForecastDTO().getForecastStartMonth());
        yearList.add(projSelDTO.getForecastDTO().getForecastEndYear());
        yearList.add(projSelDTO.getForecastDTO().getForecastEndMonth());
        Object[] orderedArgs = {projSelDTO.getProjectionId(), projSelDTO.getUserId(), projSelDTO.getSessionDTO().getSessionId(), "ASD", projSelDTO.getFrequency(), null, null, yearList.get(1) != null ? yearList.get(1) + 1 : 1, yearList.get(0)};
        if (projSelDTO.getPivotView().contains(PERIOD.getConstant())) {
            List<DiscountProjectionResultsDTO> resultList = getProjectionTotal(orderedArgs, projSelDTO);

            if (!resultList.isEmpty()) {
                totalDTO.add(resultList.get(0));
                    if (Constant.SUPPLEMENTAL.equalsIgnoreCase(projSelDTO.getMandatedOrSupp())) {
                    totalDTO.add(resultList.get(NumericConstants.TWO));
                } else {
                    totalDTO.add(resultList.get(1));
                    totalDTO.add(resultList.get(NumericConstants.TWO));
                }
            }
            return totalDTO;
        } else {

            List<DiscountProjectionResultsDTO> projectionDtoList;

            projectionDtoList = getProjectionPivotTotal(orderedArgs, projSelDTO);

            return projectionDtoList;
        }

    }

    private List<Object> getProgramCodeCount(int projectionId, String hierarchyNo) {
        List<Object> list;
        String customSQL = "SELECT DISTINCT CM.CONTRACT_NO FROM dbo.CONTRACT_MASTER CM \n"
                + "JOIN dbo.CCP_DETAILS CCP ON CCP.CONTRACT_MASTER_SID = CM.CONTRACT_MASTER_SID\n"
                + "JOIN dbo.PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID  = CCP.CCP_DETAILS_SID \n"
                + "JOIN dbo.CCP_MAP ccpm on ccpm.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID\n"
                + "JOIN dbo.RELATIONSHIP_LEVEL_DEFINITION RLD ON RLD.RELATIONSHIP_LEVEL_SID = ccpm.RELATIONSHIP_LEVEL_SID AND RLD.HIERARCHY_NO like '" + hierarchyNo + "%'\n"
                + "AND PD.PROJECTION_MASTER_SID = " + projectionId;
        list = (List<Object>) CommonLogic.executeSelectQuery(customSQL);
        return list;
    }

    /**
     * Get projection selection
     *
     * @param projectionId
     * @param screenName
     * @return
     */
    public static Map<Object, Object> getMProjectionSelection(final int projectionId, final String screenName) {
        List<Object[]> list = new ArrayList<>();
        Map<Object, Object> map = new HashMap<>();
        DynamicQuery query = MProjectionSelectionLocalServiceUtil.dynamicQuery();
        query.add(RestrictionsFactoryUtil.eq(Constant.PROJECTION_MASTER_SID, projectionId));
        query.add(RestrictionsFactoryUtil.eq(Constant.SCREEN_NAME, screenName));
        ProjectionList projectionListFrom = ProjectionFactoryUtil.projectionList();
        projectionListFrom.add(ProjectionFactoryUtil.property(Constant.FIELD_NAME));
        projectionListFrom.add(ProjectionFactoryUtil.property(Constant.FIELD_VALUES));
        query.setProjection(ProjectionFactoryUtil.distinct(projectionListFrom));
        try {
            list = MProjectionSelectionLocalServiceUtil.dynamicQuery(query);
            if (list != null && !list.isEmpty()) {
                for (int i = 0; i < list.size(); i++) {
                    Object[] obj = list.get(i);
                    map.put(obj[0], obj[1]);
                }
            }
            return map;
        } catch (SystemException ex) {
            LOGGER.error(ex.getMessage());
        }
        return null;
    }

    public void saveDiscountProjection(SessionDTO sessionDTO) throws PortalException {

        LOGGER.debug("Session--->= {}, {}, {}" , sessionDTO.getUserId(), sessionDTO.getSessionId(), sessionDTO.getProjectionId());
        SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();

        String queryString = "DECLARE @USER_ID               INT = " + sessionDTO.getUserId() + ",\n"
                + "        @PROJECTION_MASTER_SID INT = " + sessionDTO.getProjectionId() + ",\n"
                + "        @SESSION_ID            INT = " + sessionDTO.getSessionId() + "\n"
                + "UPDATE D\n"
                + "SET    SAVE_FLAG = CASE\n"
                + "                     WHEN USER_ID = " + sessionDTO.getUserId() + "\n"
                + "                          AND SESSION_ID = " + sessionDTO.getSessionId() + " THEN 1\n"
                + "                     ELSE 0\n"
                + "                   END\n"
                + "FROM   dbo.M_DISCOUNT_PROJECTION D\n"
                + "       JOIN PROJECTION_DETAILS B ON D.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID\n"
                + "WHERE  PROJECTION_MASTER_SID = " + sessionDTO.getProjectionId() + "\n"
                + "   AND EXISTS (SELECT 1\n"
                + "               FROM   dbo.M_DISCOUNT_PROJECTION D\n"
                + "               JOIN   PROJECTION_DETAILS B ON D.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID\n"
                + "               WHERE  D.USER_ID = @USER_ID\n"
                + "                  AND D.SESSION_ID = @SESSION_ID\n"
                + "                  AND PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID);";
        salesProjectionDAO.executeUpdateQuery(queryString);

        queryString = "DECLARE @USER_ID               INT = " + sessionDTO.getUserId() + ",\n"
                + "        @PROJECTION_MASTER_SID INT = " + sessionDTO.getProjectionId() + ",\n"
                + "        @SESSION_ID            INT = " + sessionDTO.getSessionId() + "\n"
                + "UPDATE D\n"
                + "SET    SAVE_FLAG = CASE\n"
                + "                     WHEN USER_ID = " + sessionDTO.getUserId() + "\n"
                + "                          AND SESSION_ID = " + sessionDTO.getSessionId() + " THEN 1\n"
                + "                     ELSE 0\n"
                + "                   END\n"
                + "FROM   dbo.M_ACTUAL_DISCOUNT D\n"
                + "       JOIN PROJECTION_DETAILS B ON D.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID\n"
                + "WHERE  PROJECTION_MASTER_SID = " + sessionDTO.getProjectionId() + "\n"
                + "   AND EXISTS (SELECT 1\n"
                + "               FROM   dbo.M_ACTUAL_DISCOUNT D\n"
                + "               JOIN   PROJECTION_DETAILS B ON D.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID\n"
                + "               WHERE  D.USER_ID = @USER_ID\n"
                + "                  AND D.SESSION_ID = @SESSION_ID\n"
                + "                  AND PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID);";
        salesProjectionDAO.executeUpdateQuery(queryString);

    }

    private String getSummedData(String stringAnnualMandProjamt, String stringAnnualSuppProjamt) {
        Double mandatedAmount = 0.0;
        Double supplementalAmount;
        try {
            mandatedAmount = Double.valueOf(stringAnnualMandProjamt);
        } catch (NumberFormatException nfe) {
            mandatedAmount = 0.0;
        }

        try {
            supplementalAmount = Double.valueOf(stringAnnualSuppProjamt);
        } catch (NumberFormatException nfe) {
            supplementalAmount = 0.0;
        }
        return String.valueOf(mandatedAmount + supplementalAmount);
    }

	public List<DiscountProjectionResultsDTO> getProjectionTotalList() {
		return projectionTotalList;
	}

	public void setProjectionTotalList(List<DiscountProjectionResultsDTO> projectionTotalList) {
		this.projectionTotalList = projectionTotalList;
	}
        
    private String frequencyDPR(String frequency) {
        String freqCharDPR = frequency;
        if (Constant.SEMI_ANNUAL.equalsIgnoreCase(freqCharDPR)) {
            freqCharDPR = Constant.S;
        } else if (Constant.QUARTER.equalsIgnoreCase(freqCharDPR)) {
            freqCharDPR = Constant.Q;
        } else if (Constant.YEAR_SPACE.equalsIgnoreCase(freqCharDPR)) {
            freqCharDPR = StringUtils.EMPTY;
        } else {
            freqCharDPR = CommonUtils.BUSINESS_PROCESS_INDICATOR_MANDATED;
        }
        return freqCharDPR;
    }
}
