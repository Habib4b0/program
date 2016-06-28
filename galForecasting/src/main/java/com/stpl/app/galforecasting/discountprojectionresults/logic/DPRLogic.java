/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.discountprojectionresults.logic;

import com.stpl.app.galforecasting.dao.SalesProjectionDAO;
import com.stpl.app.galforecasting.dao.impl.SalesProjectionDAOImpl;
import com.stpl.app.galforecasting.discountprojectionresults.dto.DiscountProjectionResultsDTO;
import com.stpl.app.galforecasting.discountprojectionresults.utils.HeaderUtils;
import com.stpl.app.galforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.galforecasting.logic.CommonLogic;
import com.stpl.app.galforecasting.sessionutils.SessionDTO;
import com.stpl.app.galforecasting.utils.CommonUtils;
import com.stpl.app.galforecasting.utils.Constant;
import static com.stpl.app.galforecasting.utils.Constant.ANNUALLY;
import static com.stpl.app.galforecasting.utils.Constant.LabelConstants.Mandated_Discount;
import static com.stpl.app.galforecasting.utils.Constant.LabelConstants.PERIOD;
import static com.stpl.app.galforecasting.utils.Constant.LabelConstants.Supplemental_Discount;
import com.stpl.app.model.MProjectionSelection;
import com.stpl.app.service.MProjectionSelectionLocalServiceUtil;
import static com.stpl.app.utils.Constants.FrequencyConstants.MONTHLY;
import static com.stpl.app.utils.Constants.FrequencyConstants.SEMI_ANNUALLY;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionList;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.PortalException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author pvinoth
 */
public class DPRLogic {

    private static final Logger LOGGER = Logger.getLogger(DPRLogic.class);
    private static final DecimalFormat CUR_ZERO = new DecimalFormat("$#,##0");
    private static final DecimalFormat CUR_TWO = new DecimalFormat("$#,##0.00");
    private static final DecimalFormat PER_TWO = new DecimalFormat("#,##0.00");
    private static final DecimalFormat PER_THREE = new DecimalFormat("#,##0.000");
    private static final String CURRENCY = "$";
    private static final String PERCENTAGE = Constant.PERCENT;
    private static final DecimalFormat UNITVOLUME = new DecimalFormat("#,##0.0");
    List<DiscountProjectionResultsDTO> projectionTotalList = new ArrayList<DiscountProjectionResultsDTO>();

    public List<DiscountProjectionResultsDTO> getConfiguredDPResults(Object parentId, int start, int offset, ProjectionSelectionDTO projSelDTO) throws Exception {
        List<DiscountProjectionResultsDTO> resultList = new ArrayList<DiscountProjectionResultsDTO>();
        if (!projSelDTO.isIsFilter() || (parentId instanceof DiscountProjectionResultsDTO)) {
            projSelDTO.setYear(Constant.All);

            if (projSelDTO.getActualsOrProjections().equals(Constant.BOTH)) {
                projSelDTO.setActualsOrProjections("Actuals and Projections");
            }
            if (ANNUALLY.equalsIgnoreCase(projSelDTO.getFrequency())) {
                projSelDTO.setFrequencyDivision(1);
            } else if (Constant.SEMIANNUALLY_SMALL.equalsIgnoreCase(projSelDTO.getFrequency())) {
                projSelDTO.setFrequencyDivision(2);
            } else if (Constant.QUARTERLY_SMALL.equalsIgnoreCase(projSelDTO.getFrequency())) {
                projSelDTO.setFrequencyDivision(4);
            } else if (Constant.MONTHLY_SMALL.equalsIgnoreCase(projSelDTO.getFrequency())) {
                projSelDTO.setFrequencyDivision(12);
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
                } else {
                    if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(projSelDTO.getHierarchyIndicator())) {
                        projSelDTO.setLevelNo(projSelDTO.getCustomerLevelNo() - 1);
                        projSelDTO.setTreeLevelNo(projSelDTO.getCustomerLevelNo() - 1);
                    } else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(projSelDTO.getHierarchyIndicator())) {
                        projSelDTO.setLevelNo(projSelDTO.getProductLevelNo() - 1);
                        projSelDTO.setTreeLevelNo(projSelDTO.getCustomerLevelNo() - 1);
                    } else {
                        projSelDTO.setLevelNo(0);
                        projSelDTO.setTreeLevelNo(0);
                    }
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

    public List<DiscountProjectionResultsDTO> getDPResults(int start, int offset, ProjectionSelectionDTO projSelDTO) throws Exception {
        int neededRecord = offset;
        int started = start;
        int mayBeAdded = 0;
        List<DiscountProjectionResultsDTO> projDTOList = new ArrayList<DiscountProjectionResultsDTO>();
        String discList = CommonUtils.CollectionToString(projSelDTO.getDiscountNoList(), false);
        String freq = StringUtils.EMPTY;
        boolean tempCustomFlag = false;

        List<Integer> yearList = new ArrayList<Integer>();
        yearList.add(projSelDTO.getForecastDTO().getHistoryStartYear());
        yearList.add(projSelDTO.getForecastDTO().getHistoryStartMonth());
        yearList.add(projSelDTO.getForecastDTO().getHistoryEndYear());
        yearList.add(projSelDTO.getForecastDTO().getHistoryEndMonth());

        yearList.add(projSelDTO.getForecastDTO().getForecastStartYear());
        yearList.add(projSelDTO.getForecastDTO().getForecastStartMonth());
        yearList.add(projSelDTO.getForecastDTO().getForecastEndYear());
        yearList.add(projSelDTO.getForecastDTO().getForecastEndMonth());

        if (projSelDTO.getFrequencyDivision() == 1 || ANNUALLY.equalsIgnoreCase(projSelDTO.getFrequency())) {
            freq = "ANNUAL";
        } else if (projSelDTO.getFrequencyDivision() == 2 || SEMI_ANNUALLY.getConstant().equalsIgnoreCase(projSelDTO.getFrequency())) {
            freq = "SEMI-ANNUAL";
        } else if (projSelDTO.getFrequencyDivision() == 4 || Constant.QUARTERLY.equalsIgnoreCase(projSelDTO.getFrequency())) {
            freq = "QUARTERLY";
        } else if (projSelDTO.getFrequencyDivision() == 12 || MONTHLY.getConstant().equalsIgnoreCase(projSelDTO.getFrequency())) {
            freq = "MONTHLY";
        }
        Object[] orderedArgs = {projSelDTO.getProjectionId(), projSelDTO.getUserId(), projSelDTO.getSessionId(), "ASD", projSelDTO.getFrequency(), null, null, yearList.get(1), yearList.get(0)};
        if (neededRecord > 0 && projSelDTO.getPivotView().contains(PERIOD.getConstant())) {
            DiscountProjectionResultsDTO totalDTO = null;
            DiscountProjectionResultsDTO mandatedDisc = null;
            DiscountProjectionResultsDTO SupplDisc = null;
            if (projSelDTO.isIsProjectionTotal() && projSelDTO.isIsTotal()) {
                projectionTotalList = getConfiguredResultsTotal(start, offset, projSelDTO);
                if (!projectionTotalList.isEmpty()) {
                    projDTOList.addAll(projectionTotalList);
                }
            }
            String discount = projSelDTO.getMandatedOrSupp();
            if (neededRecord > 0 && projSelDTO.isIsTotal() && !projSelDTO.isIsProjectionTotal()) {
                if (mandatedDisc == null || SupplDisc == null) {
                    List<DiscountProjectionResultsDTO> mandOrSupplemental = getMandSuppDiscount(projSelDTO);
                    if (mandOrSupplemental != null && !mandOrSupplemental.isEmpty()) {
                        mandatedDisc = mandOrSupplemental.get(0);
                        SupplDisc = mandOrSupplemental.get(1);
                    }

                }
                if (discount.equals(Constant.BOTH) || discount.equals(CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED)) {
                    boolean toadd = false;
                    if (projSelDTO.isIsProjectionTotal() && start < 4) {
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
                    if (projSelDTO.isIsProjectionTotal() && start < 5) {
                        toadd = true;
                    } else if (!projSelDTO.isIsProjectionTotal() && start < 2) {
                        toadd = true;
                    } else {
                        toadd = false;
                    }
                    if (toadd && !projSelDTO.isIsProjectionTotal()) {
                        projDTOList.add(SupplDisc);
                        neededRecord--;
                        started++;
                    }
                    mayBeAdded++;
                }
            }

            if (Mandated_Discount.getConstant().equals(projSelDTO.getLevelValue()) || Supplemental_Discount.getConstant().equals(projSelDTO.getLevelValue())) {
                List<DiscountProjectionResultsDTO> programCodeList = new ArrayList<DiscountProjectionResultsDTO>();
                programCodeList = getProgramCodeDiscount(projSelDTO);
                if (programCodeList != null && !programCodeList.isEmpty()) {
                    for (DiscountProjectionResultsDTO dto : programCodeList) {
                        projDTOList.add(dto);
                    }
                }
            }
        } else {
            if (projSelDTO.isIsProjectionTotal() && projSelDTO.isIsTotal()) {
                List<DiscountProjectionResultsDTO> projectionDtoList = new ArrayList<DiscountProjectionResultsDTO>();
                List<DiscountProjectionResultsDTO> totalDTO = new ArrayList<DiscountProjectionResultsDTO>();
                if (start < 1) {
                    DiscountProjectionResultsDTO dto = new DiscountProjectionResultsDTO();
                    dto.setLevelValue(Constant.PROJECTION_TOTAL);
                    dto.setRelationshipLevelName(Constant.PROJECTION_TOTAL);
                    dto.setParent(0);
                    totalDTO.add(dto);
                    neededRecord--;
                }
                mayBeAdded++;
                projectionDtoList = getConfiguredResultsTotal(start, offset, projSelDTO);
                int maybeAddedRecord = start - mayBeAdded;
                if (maybeAddedRecord < 0) {
                    maybeAddedRecord = 0;
                }
                for (int k = maybeAddedRecord; k < projectionDtoList.size() && neededRecord > 0; k++) {
                    totalDTO.add(projectionDtoList.get(k));
                    neededRecord--;
                    started++;
                }
                mayBeAdded += projectionDtoList.size();
                projDTOList.addAll(totalDTO);
            }
            /*Dumpy Value*/
            /*
             if (projSelDTO.isIsProjectionTotal() && projSelDTO.isIsTotal()) {
             projectionTotalList = getConfiguredResultsTotal(start, offset, projSelDTO);
             if (!projectionTotalList.isEmpty()) {
             projDTOList.addAll(projectionTotalList);
             }
             }
             */

            List<DiscountProjectionResultsDTO> projectionDtoList = new ArrayList<DiscountProjectionResultsDTO>();
            if (projSelDTO.isIsProjectionTotal()) {
//                if (start < 1) {
//                    DiscountProjectionResultsDTO dto = new DiscountProjectionResultsDTO();
//                    dto.setLevelValue("Projection Total");
//                    dto.setParent(0);
//                    projDTOList.add(dto);
//                    neededRecord--;
////                    started++;
//                }
//                mayBeAdded++;
//                projectionDtoList = getProjectionPivotTotal(orderedArgs, projSelDTO);
            } else {
                projectionDtoList = getCustomizedPivotChildNodes(projectionDtoList, projSelDTO);
                for (int k = started; k < projectionDtoList.size() && neededRecord > 0; k++) {
                    projDTOList.add(projectionDtoList.get(k));
                    neededRecord--;
                }
                mayBeAdded += projectionDtoList.size();
            }
        }
        //        neededRecord=1;
        if (neededRecord > 0 && projSelDTO.isIsTotal() && !projSelDTO.isIsFilter()) {
            int mayBeAddedRecord = start - mayBeAdded;
            if (mayBeAddedRecord < 0) {
                mayBeAddedRecord = 0;
            }
            if (projSelDTO.isCustomFlag()) {
                projSelDTO.setCustomLevelNo(projSelDTO.getCustomLevelNo() + 1);
                int i = CommonLogic.getIndicatorCount(projSelDTO.getProjectionId(), projSelDTO.getCustomId());
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
                    nextLevelValueList = null;

                }
            } else {
                projSelDTO.setLevelNo(projSelDTO.getLevelNo() + 1);
                projSelDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo() + 1);
                List<DiscountProjectionResultsDTO> nextLevelValueList = configureLevels(mayBeAddedRecord, neededRecord, projSelDTO);
                projDTOList.addAll(nextLevelValueList);
                nextLevelValueList = null;

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
        List<Object> list = new ArrayList<Object>();
        Map<Integer, String> freq = new HashMap<Integer, String>();
        freq.put(1, "\"YEAR\"");
        freq.put(2, "SEMI_ANNUAL");
        freq.put(4, "QUARTER");
        freq.put(12, "\"MONTH\"");
        String frequency = freq.get(projSelDTO.getFrequencyDivision());
        String freqChar = !"\"YEAR\"".equalsIgnoreCase(frequency) ? "SEMI_ANNUAL".equalsIgnoreCase(frequency) ? Constant.S : "QUARTER".equalsIgnoreCase(frequency) ? Constant.Q : CommonUtils.BUSINESS_PROCESS_INDICATOR_MANDATED : "";
        list = (List<Object>) CommonLogic.executeSelectQuery(getProgramCodeQuery(projSelDTO.getProjectionId(), projSelDTO.getUserId(), projSelDTO.getSessionId(), projSelDTO.getLevelNo(), projSelDTO.getHierarchyNo(), frequency, projSelDTO.getView(), projSelDTO, freqChar), null, null);
        List<DiscountProjectionResultsDTO> projDTOList = getCustomizedPC(list, projSelDTO);
        list = null;

        return projDTOList;
    }

    public List<Object> getProgramCodeName(ProjectionSelectionDTO projSelDTO) {
        List<Object> list = new ArrayList<Object>();
        list = (List<Object>) CommonLogic.executeSelectQuery(getProgramCodeNameQuery(projSelDTO.getProjectionId()), null, null);
        return list;
    }

    public List<DiscountProjectionResultsDTO> getCustomizedMandSuppDisc(List<Object> list, ProjectionSelectionDTO projSelDTO) {
        List<DiscountProjectionResultsDTO> projDtoList = new ArrayList<DiscountProjectionResultsDTO>();
        List<String> columnList = new ArrayList<String>(projSelDTO.getColumns());
        columnList.remove("levelValue");
        DiscountProjectionResultsDTO mandatedDTO = projSelDTO.getMandatedDTO();
        DiscountProjectionResultsDTO suppDTO = projSelDTO.getSupplementalDTO();
        mandatedDTO.setLevelNo(projSelDTO.getLevelNo());
        mandatedDTO.setHierarchyNo(projSelDTO.getHierarchyNo());
        mandatedDTO.setHierarchyIndicator(projSelDTO.getHierarchyIndicator());
        mandatedDTO.setLevelValue(Mandated_Discount.getConstant());
        mandatedDTO.setRelationshipLevelName(Mandated_Discount.getConstant());
        mandatedDTO.setParentLevelName(projSelDTO.getLevelValue());

        suppDTO.setLevelNo(projSelDTO.getLevelNo());
        suppDTO.setHierarchyNo(projSelDTO.getHierarchyNo());
        suppDTO.setHierarchyIndicator(projSelDTO.getHierarchyIndicator());
        suppDTO.setLevelValue(Supplemental_Discount.getConstant());
        suppDTO.setRelationshipLevelName(Supplemental_Discount.getConstant());
        suppDTO.setParentLevelName(projSelDTO.getLevelValue());

        mandatedDTO.setParent(1);
        suppDTO.setParent(1);
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        if (Constant.QUARTERLY_SMALL.equalsIgnoreCase(projSelDTO.getFrequency())) {
            frequencyDivision = 4;
        } else if (Constant.MONTHLY_SMALL.equalsIgnoreCase(projSelDTO.getFrequency())) {
            frequencyDivision = 12;
        } else if (Constant.SEMIANNUALLY_SMALL.equalsIgnoreCase(projSelDTO.getFrequency())) {
            frequencyDivision = 2;
        } else {
            frequencyDivision = 1;
        }
        String projections = projSelDTO.getActualsOrProjections();
        String salesOrUnits = projSelDTO.getMandatedOrSupp();
        if (list != null && !list.isEmpty()) {
            boolean actualFlag = false;
            for (Object list1 : list) {
                String columnName = StringUtils.EMPTY;
                final Object[] obj = (Object[]) list1;
                int year = Integer.valueOf(String.valueOf(obj[2]));
                int period = Integer.valueOf(String.valueOf(obj[3]));
                List<String> common = HeaderUtils.getCommonColumnHeader(frequencyDivision, year, period, false);
                String commonColumn = common.get(0);
                int col = 2;
                if ("mandated".equalsIgnoreCase(salesOrUnits) || Constant.BOTH_SMALL.equalsIgnoreCase(salesOrUnits)) {

                    String mandAmt = StringUtils.EMPTY + obj[0];
                    mandAmt = getFormattedValue(CUR_ZERO, mandAmt);
                    columnName = actualFlag ? "ActualsAmount" : "ProjectionsAmount";
                    mandatedDTO.addStringProperties(commonColumn + columnName, mandAmt);

                    String mandRate = StringUtils.EMPTY + obj[1];
                    mandRate = getFormattedValue(CUR_ZERO, mandRate);
                    columnName = actualFlag ? "ActualsRate" : "ProjectionsRate";
                    mandatedDTO.addStringProperties(commonColumn + columnName, mandRate);

                }
                if ("supplemental".equalsIgnoreCase(salesOrUnits) || Constant.BOTH_SMALL.equalsIgnoreCase(salesOrUnits)) {
                    String actual = StringUtils.EMPTY + obj[0];
                    actual = getFormattedValue(UNITVOLUME, actual);
                    suppDTO.addStringProperties(commonColumn + Constant.ACTUALS, actual);
                    String projection = StringUtils.EMPTY + obj[1];
                    projection = getFormattedValue(UNITVOLUME, projection);
                    suppDTO.addStringProperties(commonColumn + Constant.PROJECTIONS, projection);

                }
            }

        }
//        for (String columns : columnList) {
//            contractDTO.addStringProperties(columns, getFormattedValue(CUR_ZERO, "null"));
//            supplDTO.addStringProperties(columns, getFormattedValue(CUR_ZERO, "null"));
//        }
        projDtoList.add(mandatedDTO);
        projDtoList.add(suppDTO);
        return projDtoList;
    }

    public List<DiscountProjectionResultsDTO> getCustomizedPC(List<Object> list, ProjectionSelectionDTO projSelDTO) {
        List<DiscountProjectionResultsDTO> pcDTO = new ArrayList<DiscountProjectionResultsDTO>();
        if (list != null && !list.isEmpty()) {
            int frequencyDivision = projSelDTO.getFrequencyDivision();
            DiscountProjectionResultsDTO contractDTO = null;
            DiscountProjectionResultsDTO suppDTO = new DiscountProjectionResultsDTO();
            boolean actualFlag = false;
            String lastValue = StringUtils.EMPTY;
            String tempAnnual = StringUtils.EMPTY;
            double annualMandamt = 0.0,annualMandrate = 0.0,annualMandrpu = 0.0,annualMandProjamt = 0.0,annualMandProjrate = 0.0,annualMandProjrpu = 0.0,
                    annualSuppamt = 0.0, annualSupprate = 0.0, annualSupprpu = 0.0,annualSuppProjamt = 0.0, annualSuppProjrate = 0.0, annualSuppProjrpu = 0.0;
            List<Object> rightHeaderColumns = projSelDTO.getRightHeaderDoubleColumns();
            for (int i = 0; i < list.size(); i++) {
                String columnName = StringUtils.EMPTY;
                final Object[] obj = (Object[]) list.get(i);
                if (lastValue.isEmpty() || !lastValue.equalsIgnoreCase(String.valueOf(obj[2]))) {
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
                actualFlag = Integer.valueOf(String.valueOf(obj[11])) == 0;
                lastValue = String.valueOf(obj[2]);
                int year = Integer.valueOf(String.valueOf(obj[4]));
                int period = projSelDTO.getFrequencyDivision() == 1 ? 0 : Integer.valueOf(String.valueOf(obj[12]));
                List<String> annualTotal = new ArrayList<String>();
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

                if (Mandated_Discount.getConstant().equals(projSelDTO.getLevelValue())) {
                    String mandAmt = StringUtils.EMPTY + obj[5];
                    mandAmt = getFormatValue(2, mandAmt, CURRENCY);
                    columnName = actualFlag ? "ActualsAmount" : "ProjectionsAmount";
                    contractDTO.addStringProperties(commonColumn + columnName, mandAmt);

                    String mandRate = StringUtils.EMPTY + obj[6];
                    mandRate = getFormatValue(2, mandRate, PERCENTAGE);
                    columnName = actualFlag ? "ActualsRate" : "ProjectionsRate";
                    contractDTO.addStringProperties(commonColumn + columnName, mandRate);

                    String mandRPU = StringUtils.EMPTY + obj[9];
                    mandRPU = getFormatValue(2, mandRPU, CURRENCY);
                    columnName = actualFlag ? "ActualsRPU" : "ProjectionsRPU";
                    contractDTO.addStringProperties(commonColumn + columnName, mandRPU);

                    if (!annualColumn.equals(StringUtils.EMPTY) && commonColumn.contains(annualColumn) && rightHeaderColumns.toString().contains(commonColumn)) {
                        if (actualFlag) {
                            annualMandamt = annualMandamt + (obj[5] != null ? Double.valueOf(String.valueOf(obj[5])) : 0);
                            mandAmt = getFormatValue(2, String.valueOf(annualMandamt), CURRENCY);
                            annualMandrate = annualMandrate + (obj[6] != null ? Double.valueOf(String.valueOf(obj[6])) : 0);
                            mandRate = getFormatValue(2, String.valueOf(annualMandrate), PERCENTAGE);
                            annualMandrpu = annualMandrpu + (obj[9] != null ? Double.valueOf(String.valueOf(obj[9])) : 0);
                            mandRPU = getFormatValue(2, String.valueOf(annualMandrpu), CURRENCY);
                        } else {
                            annualMandProjamt = annualMandProjamt + (obj[5] != null ? Double.valueOf(String.valueOf(obj[5])) : 0);
                            mandAmt = getFormatValue(2, String.valueOf(annualMandProjamt), CURRENCY);
                            annualMandProjrate = annualMandProjrate + (obj[6] != null ? Double.valueOf(String.valueOf(obj[6])) : 0);
                            mandRate = getFormatValue(2, String.valueOf(annualMandProjrate), PERCENTAGE);
                            annualMandProjrpu = annualMandProjrpu + (obj[9] != null ? Double.valueOf(String.valueOf(obj[9])) : 0);
                            mandRPU = getFormatValue(2, String.valueOf(annualMandProjrpu), CURRENCY);
                        }
                        columnName = actualFlag ? "ActualsAmount" : "ProjectionsAmount";
                        contractDTO.addStringProperties(annualColumn + columnName, mandAmt);

                        columnName = actualFlag ? "ActualsRate" : "ProjectionsRate";
                        contractDTO.addStringProperties(annualColumn + columnName, mandRate);

                        columnName = actualFlag ? "ActualsRPU" : "ProjectionsRPU";
                        contractDTO.addStringProperties(annualColumn + columnName, mandRPU);
                    }
                } else if (Supplemental_Discount.getConstant().equals(projSelDTO.getLevelValue())) {
                    String suppAmt = StringUtils.EMPTY + obj[7];
                    suppAmt = getFormatValue(2, suppAmt, CURRENCY);
                    columnName = actualFlag ? "ActualsAmount" : "ProjectionsAmount";
                    contractDTO.addStringProperties(commonColumn + columnName, suppAmt);

                    String suppRate = StringUtils.EMPTY + obj[8];
                    suppRate = getFormatValue(2, suppRate, PERCENTAGE);
                    columnName = actualFlag ? "ActualsRate" : "ProjectionsRate";
                    contractDTO.addStringProperties(commonColumn + columnName, suppRate);

                    String suppRPU = StringUtils.EMPTY + obj[10];
                    suppRPU = getFormatValue(2, suppRPU, CURRENCY);
                    columnName = actualFlag ? "ActualsRPU" : "ProjectionsRPU";
                    contractDTO.addStringProperties(commonColumn + columnName, suppRPU);

                    if (!annualColumn.equals(StringUtils.EMPTY) && commonColumn.contains(annualColumn) && rightHeaderColumns.toString().contains(commonColumn)) {
                        if (actualFlag) {
                            annualSuppamt = annualSuppamt + (obj[7] != null ? Double.valueOf(String.valueOf(obj[7])) : 0);
                            suppAmt = getFormatValue(2, String.valueOf(annualSuppamt), CURRENCY);
                            annualSupprate = annualSupprate + (obj[8] != null ? Double.valueOf(String.valueOf(obj[8])) : 0);
                            suppRate = getFormatValue(2, String.valueOf(annualSupprate), PERCENTAGE);
                            annualSupprpu = annualSupprpu + (obj[10] != null ? Double.valueOf(String.valueOf(obj[10])) : 0);
                            suppRPU = getFormatValue(2, String.valueOf(annualSupprpu), CURRENCY);
                        } else {
                            annualSuppProjamt = annualSuppProjamt + (obj[7] != null ? Double.valueOf(String.valueOf(obj[7])) : 0);
                            suppAmt = getFormatValue(2, String.valueOf(annualSuppProjamt), CURRENCY);
                            annualSuppProjrate = annualSuppProjrate + (obj[8] != null ? Double.valueOf(String.valueOf(obj[8])) : 0);
                            suppRate = getFormatValue(2, String.valueOf(annualSuppProjrate), PERCENTAGE);
                            annualSuppProjrpu = annualSuppProjrpu + (obj[10] != null ? Double.valueOf(String.valueOf(obj[10])) : 0);
                            suppRPU = getFormatValue(2, String.valueOf(annualSuppProjrpu), CURRENCY);
                        }
                        columnName = actualFlag ? "ActualsAmount" : "ProjectionsAmount";
                        contractDTO.addStringProperties(annualColumn + columnName, suppAmt);

                        columnName = actualFlag ? "ActualsRate" : "ProjectionsRate";
                        contractDTO.addStringProperties(annualColumn + columnName, suppRate);
                        columnName = actualFlag ? "ActualsRPU" : "ProjectionsRPU";
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
        }

        return pcDTO;
    }

    public int getConfiguredDPResultsCount(Object parentId, ProjectionSelectionDTO projSelDTO, boolean isLevelCount, ProjectionSelectionDTO initialProjSelDTO) {
        int count = 0;
        if (!projSelDTO.isIsFilter() || (parentId instanceof DiscountProjectionResultsDTO)) {
            projSelDTO.setYear(Constant.All);

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
                projSelDTO.setIsProjectionTotal(true);
                projSelDTO.setIsTotal(true);
                projSelDTO.setHierarchyIndicator(initialProjSelDTO.getHierarchyIndicator());
                if (projSelDTO.isCustomFlag()) {
                    String indicator = CommonLogic.getIndicator(1, projSelDTO.getCustomId());
                    projSelDTO.setHierarchyIndicator(indicator);
                    projSelDTO.setLevelNo(0);
                    projSelDTO.setCustomLevelNo(0);
                    projSelDTO.setTreeLevelNo(0);
                } else {
                    if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(projSelDTO.getHierarchyIndicator())) {
                        projSelDTO.setLevelNo(initialProjSelDTO.getCustomerLevelNo() - 1);
                        projSelDTO.setTreeLevelNo(initialProjSelDTO.getCustomerLevelNo() - 1);
                    } else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(projSelDTO.getHierarchyIndicator())) {
                        projSelDTO.setLevelNo(initialProjSelDTO.getProductLevelNo() - 1);
                        projSelDTO.setTreeLevelNo(initialProjSelDTO.getProductLevelNo() - 1);
                    } else {
                        projSelDTO.setLevelNo(0);
                    }
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
        int count = 0;
        boolean tempCustomFlag = false;
        boolean mdFlag = false;
        if (projSelDTO.getPivotView().contains(PERIOD.getConstant())) {
            String salesOrUnit = projSelDTO.getMandatedOrSupp();
            count += projSelDTO.isIsProjectionTotal() == true ? 1 : 0;

            if (Mandated_Discount.getConstant().equals(projSelDTO.getLevelValue()) || Supplemental_Discount.getConstant().equals(projSelDTO.getLevelValue())) {
                List<Object> list = new ArrayList<Object>();
                list = getProgramCodeCount(projSelDTO.getProjectionId(), projSelDTO.getHierarchyNo());
                if (list != null && !list.isEmpty()) {
                    count = count + list.size();
                }
                mdFlag = true;
            } else {
                if ((CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED).equalsIgnoreCase(salesOrUnit) || (Constant.SUPPLEMENTAL).equalsIgnoreCase(salesOrUnit)) {
                    count += 1;
                } else {
                    count += 2;
                }
            }

//            if (projSelDTO.isIsFilter()) {
//                return 0;
//            }
//            if (!projSelDTO.isIsProjectionTotal() && projSelDTO.isIsTotal()) {
//                count = count + 3;
//            }
//            String discount = projSelDTO.getMandatedOrSupp();
//            if (projSelDTO.isIsTotal()) {

//                if (discount.equalsIgnoreCase("Both") || discount.equalsIgnoreCase("Mandated")) {
//                    count++;
//                }
//                if (discount.equalsIgnoreCase("Both") || discount.equalsIgnoreCase("Supplemental")) {
//                    count++;
//                }
//            }
//            if (projSelDTO.isIsTotal()) {
//                count++;
//            }
//            if (Mandated_Discount.getConstant().equals(projSelDTO.getLevelValue()) || Supplemental_Discount.getConstant().equals(projSelDTO.getLevelValue())) {
//                List<Object> list = new ArrayList<Object>();
//                list = getProgramCodeCount(projSelDTO.getProjectionId(), projSelDTO.getHierarchyNo());

//                if (list != null && !list.isEmpty()) {
//                    count = count + list.size();
//                }
//            }
        } else {

            if (projSelDTO.isIsProjectionTotal()) {
                count = count + 1 + projSelDTO.getPeriodList().size();
            } else {
                count = count + projSelDTO.getPeriodList().size();
            }
        }
        if (projSelDTO.isIsTotal() && isLevelCount && !projSelDTO.isIsFilter()) {
            if (projSelDTO.isCustomFlag()) {
                projSelDTO.setCustomLevelNo(projSelDTO.getCustomLevelNo() + 1);
                int i = CommonLogic.getIndicatorCount(projSelDTO.getProjectionId(), projSelDTO.getCustomId());
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
                    count = count + configureLevelsCount(projSelDTO);
                }
//                projSelDTO.setLevelNo(projSelDTO.getLevelNo() + 1);
//                projSelDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo() + 1);
            } else {
                projSelDTO.setLevelNo(projSelDTO.getLevelNo() + 1);
                projSelDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo() + 1);
                count = count + configureLevelsCount(projSelDTO);
            }
        } else if (!mdFlag && !projSelDTO.isIsFilter()) {
            projSelDTO.setLevelNo(projSelDTO.getLevelNo());
            count = count + configureLevelsCount(projSelDTO);

        }
        projSelDTO.setLevelCount(configureLevelsCount(projSelDTO));
        return count;
    }

    public List<DiscountProjectionResultsDTO> configureLevels(int start, int offset, ProjectionSelectionDTO projSelDTO) {
        int neededRecord = offset;

        List<DiscountProjectionResultsDTO> resultList = new ArrayList<DiscountProjectionResultsDTO>();
        if (neededRecord > 0) {
            List<Leveldto> levelList = CommonLogic.getConditionalLevelList(projSelDTO.getProjectionId(), start, offset, projSelDTO.getHierarchyIndicator(), projSelDTO.getLevelNo(), projSelDTO.getHierarchyNo(), projSelDTO.getProductHierarchyNo(), projSelDTO.getCustomerHierarchyNo(), projSelDTO.isIsFilter(), false, projSelDTO.isIsCustomHierarchy(), projSelDTO.getCustomId(), false, StringUtils.EMPTY);

            for (int i = 0; i < levelList.size() && neededRecord > 0; i++) {
                Leveldto levelDto = levelList.get(i);

                DiscountProjectionResultsDTO dto = new DiscountProjectionResultsDTO();
                dto.setLevelNo(levelDto.getLevelNo());
                dto.setTreeLevelNo(levelDto.getTreeLevelNo());
                dto.setParentNode(levelDto.getParentNode());
                dto.setGroup(levelDto.getRelationshipLevelValue());
                dto.setLevelValue(levelDto.getRelationshipLevelValue());
                dto.setHierarchyNo(levelDto.getHierarchyNo());
                dto.setHierarchyIndicator(levelDto.getHierarchyIndicator());
                dto.setRelationshipLevelName(projSelDTO.getSessionDTO().getLevelValueDiscription(dto.getHierarchyNo(), dto.getHierarchyIndicator()));
                if (dto.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
                    dto.setCustomerHierarchyNo(dto.getHierarchyNo());
                    dto.setProductHierarchyNo(projSelDTO.getProductHierarchyNo());
                } else if (dto.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
                    dto.setProductHierarchyNo(dto.getHierarchyNo());
                    dto.setCustomerHierarchyNo(projSelDTO.getCustomerHierarchyNo());
                }
                dto.setCustomLevelNo(projSelDTO.getCustomLevelNo());
                dto.setOnExpandTotalRow(1);
                dto.setParent(1);
                DiscountProjectionResultsDTO discountDTO = new DiscountProjectionResultsDTO();

                if (projSelDTO.getPivotView().equalsIgnoreCase(Constant.PERIOD)) {
                    discountDTO = getChildNodeValues(dto, projSelDTO);
                } else {
                    discountDTO = getPivotChildNodeValues(dto, projSelDTO);
                }
                resultList.add(discountDTO);
                neededRecord--;
            }
        }
        return resultList;
    }

    public int configureLevelsCount(ProjectionSelectionDTO projSelDTO) {
        int levelCount = 0;
        levelCount = CommonLogic.getLevelListCountDPR(projSelDTO.getProjectionId(), projSelDTO.getHierarchyIndicator(), projSelDTO.getLevelNo(), projSelDTO.getHierarchyNo(), projSelDTO.isIsFilter(), false, StringUtils.EMPTY,projSelDTO.getCustomId(),projSelDTO.isCustomFlag());
        projSelDTO.setLevelCount(levelCount);
        return levelCount;
    }

    public List<DiscountProjectionResultsDTO> getProjectionTotal(Object[] orderedArgs, ProjectionSelectionDTO projSelDTO) throws Exception {
        List<DiscountProjectionResultsDTO> projDTOList = new ArrayList<DiscountProjectionResultsDTO>();
//        List<Object[]> gtsList=null;
        List<Object[]> gtsList = CommonLogic.callProcedure("PRC_M_DISCOUNT_PROJ_TOTAL", orderedArgs);
        if (gtsList != null) {
            projDTOList = getCustomizedProjectionTotal(gtsList, projSelDTO);
        }
     
        return projDTOList;
    }

    public List<DiscountProjectionResultsDTO> getProjectionPivotTotal(Object[] orderedArgs, ProjectionSelectionDTO projSelDTO) {
        List<DiscountProjectionResultsDTO> projDTOList = new ArrayList<DiscountProjectionResultsDTO>();
        List<Object[]> gtsList = CommonLogic.callProcedure("PRC_M_DISCOUNT_PROJ_TOTAL", orderedArgs);
        projDTOList = getCustomizedProjectionPivotTotal(gtsList, projSelDTO);
        if (projSelDTO.getProjectionOrder().equalsIgnoreCase(Constant.DESCENDING)) {
            Collections.reverse(projDTOList);
        }
        return projDTOList;
    }

    public List<DiscountProjectionResultsDTO> getCustomizedProjectionTotal(List<Object[]> list, ProjectionSelectionDTO projSelDTO) throws Exception {
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        String mandSupp = projSelDTO.getMandatedOrSupp();
        String freq = projSelDTO.getFrequency();
        List<DiscountProjectionResultsDTO> projDTOList = new ArrayList<DiscountProjectionResultsDTO>();
        DiscountProjectionResultsDTO projTotal = new DiscountProjectionResultsDTO();
        DiscountProjectionResultsDTO mandatedDTO = new DiscountProjectionResultsDTO();
        DiscountProjectionResultsDTO supplDTO = new DiscountProjectionResultsDTO();

        projTotal.setParent(0);
        projTotal.setLevelValue(Constant.PROJECTION_TOTAL);
        projTotal.setRelationshipLevelName(Constant.PROJECTION_TOTAL);

        mandatedDTO.setParent(0);
        mandatedDTO.setLevelValue(Mandated_Discount.getConstant());
        mandatedDTO.setRelationshipLevelName(Mandated_Discount.getConstant());

        supplDTO.setParent(0);
        supplDTO.setLevelValue(Supplemental_Discount.getConstant());
        supplDTO.setRelationshipLevelName(Supplemental_Discount.getConstant());
        double amt = 0.0, rate = 0.0, rpu = 0.0, projamt = 0.0, projrate = 0.0, projrpu = 0.0, annualMandamt = 0.0, annualMandrate = 0.0, annualMandrpu = 0.0,
                annualMandProjamt = 0.0, annualMandProjrate = 0.0, annualMandProjrpu = 0.0, annualSuppamt = 0.0, annualSupprate = 0.0, annualSupprpu = 0.0, annualSuppProjamt = 0.0, annualSuppProjrate = 0.0, annualSuppProjrpu = 0.0;
        String tempAnnualColumn = StringUtils.EMPTY;
        List<Object> rightHeaderColumns = projSelDTO.getRightHeaderDoubleColumns();

        if (list != null && !list.isEmpty()) {
            boolean annualFlag = false;
            boolean actualFlag = false;
            if (ANNUALLY.equalsIgnoreCase(freq)) {
                annualFlag = true;
            }
            for (Object list1 : list) {
                final Object[] obj = (Object[]) list1;
                String type = StringUtils.EMPTY;
                String columnName = StringUtils.EMPTY;
                if (annualFlag) {
                    type = String.valueOf(obj[10]);
                } else {
                    type = String.valueOf(obj[11]);
                }
                String column = StringUtils.EMPTY;
                int year = annualFlag ? Integer.valueOf(String.valueOf(obj[9])) : Integer.valueOf(String.valueOf(obj[10]));
                int period = annualFlag ? 0 : Integer.valueOf(String.valueOf(obj[9]));
                List<String> annualTotal = new ArrayList<String>();
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
                String totalAmt = StringUtils.EMPTY + obj[6];
                totalAmt = getFormatValue(2, totalAmt, CURRENCY);
                columnName = actualFlag ? "ActualsAmount" : "ProjectionsAmount";
                projTotal.addStringProperties(commonColumn + columnName, totalAmt);

                String totalRate = StringUtils.EMPTY + obj[7];
                totalRate = getFormatValue(2, totalRate, PERCENTAGE);
                columnName = actualFlag ? "ActualsRate" : "ProjectionsRate";
                projTotal.addStringProperties(commonColumn + columnName, totalRate);

                String totalRPU = StringUtils.EMPTY + obj[8];
                totalRPU = getFormatValue(2, totalRPU, CURRENCY);
                columnName = actualFlag ? "ActualsRPU" : "ProjectionsRPU";
                projTotal.addStringProperties(commonColumn + columnName, totalRPU);

                if (!annualColumn.equals(StringUtils.EMPTY) && commonColumn.contains(annualColumn) && rightHeaderColumns.toString().contains(commonColumn)) {
                    if (actualFlag) {
                        amt = amt + (obj[6] != null ? Double.valueOf(String.valueOf(obj[6])) : 0);
                        totalAmt = getFormatValue(2, String.valueOf(amt), CURRENCY);
                        rate = rate + (obj[7] != null ? Double.valueOf(String.valueOf(obj[7])) : 0);
                        totalRate = getFormatValue(2, String.valueOf(rate), PERCENTAGE);
                        rpu = rpu + (obj[8] != null ? Double.valueOf(String.valueOf(obj[8])) : 0);
                        totalRPU = getFormatValue(2, String.valueOf(rpu), CURRENCY);
                    } else {
                        projamt = projamt + (obj[6] != null ? Double.valueOf(String.valueOf(obj[6])) : 0);
                        totalAmt = getFormatValue(2, String.valueOf(projamt), CURRENCY);
                        projrate = projrate + (obj[7] != null ? Double.valueOf(String.valueOf(obj[7])) : 0);
                        totalRate = getFormatValue(2, String.valueOf(projrate), PERCENTAGE);
                        projrpu = projrpu + (obj[8] != null ? Double.valueOf(String.valueOf(obj[8])) : 0);
                        totalRPU = getFormatValue(2, String.valueOf(projrpu), CURRENCY);
                    }
                    columnName = actualFlag ? "ActualsAmount" : "ProjectionsAmount";
                    projTotal.addStringProperties(annualColumn + columnName, totalAmt);

                    columnName = actualFlag ? "ActualsRate" : "ProjectionsRate";
                    projTotal.addStringProperties(annualColumn + columnName, totalRate);

                    columnName = actualFlag ? "ActualsRPU" : "ProjectionsRPU";
                    projTotal.addStringProperties(annualColumn + columnName, totalRPU);
                }

                if ("mandated".equalsIgnoreCase(mandSupp) || Constant.BOTH_SMALL.equalsIgnoreCase(mandSupp)) {

                    String mandAmt = StringUtils.EMPTY + obj[0];
                    mandAmt = getFormatValue(2, mandAmt, CURRENCY);
                    columnName = actualFlag ? "ActualsAmount" : "ProjectionsAmount";
                    mandatedDTO.addStringProperties(commonColumn + columnName, mandAmt);
                    String mandRate = StringUtils.EMPTY + obj[1];
                    mandRate = getFormatValue(2, mandRate, PERCENTAGE);
                    columnName = actualFlag ? "ActualsRate" : "ProjectionsRate";
                    mandatedDTO.addStringProperties(commonColumn + columnName, mandRate);
                    String mandRPU = StringUtils.EMPTY + obj[2];
                    mandRPU = getFormatValue(2, mandRPU, CURRENCY);
                    columnName = actualFlag ? "ActualsRPU" : "ProjectionsRPU";
                    mandatedDTO.addStringProperties(commonColumn + columnName, mandRPU);
                    if (!annualColumn.equals(StringUtils.EMPTY) && commonColumn.contains(annualColumn) && rightHeaderColumns.toString().contains(commonColumn)) {
                        if (actualFlag) {
                            annualMandamt = annualMandamt + (obj[0] != null ? Double.valueOf(String.valueOf(obj[0])) : 0);
                            mandAmt = getFormatValue(2, String.valueOf(annualMandamt), CURRENCY);
                            annualMandrate = annualMandrate + (obj[1] != null ? Double.valueOf(String.valueOf(obj[1])) : 0);
                            mandRate = getFormatValue(2, String.valueOf(annualMandrate), PERCENTAGE);
                            annualMandrpu = annualMandrpu + (obj[2] != null ? Double.valueOf(String.valueOf(obj[2])) : 0);
                            mandRPU = getFormatValue(2, String.valueOf(annualMandrpu), CURRENCY);
                        } else {
                            annualMandProjamt = annualMandProjamt + (obj[0] != null ? Double.valueOf(String.valueOf(obj[0])) : 0);
                            mandAmt = getFormatValue(2, String.valueOf(annualMandProjamt), CURRENCY);
                            annualMandProjrate = annualMandProjrate + (obj[1] != null ? Double.valueOf(String.valueOf(obj[1])) : 0);
                            mandRate = getFormatValue(2, String.valueOf(annualMandProjrate), PERCENTAGE);
                            annualMandProjrpu = annualMandProjrpu + (obj[2] != null ? Double.valueOf(String.valueOf(obj[2])) : 0);
                            mandRPU = getFormatValue(2, String.valueOf(annualMandProjrpu), CURRENCY);
                        }

                        columnName = actualFlag ? "ActualsAmount" : "ProjectionsAmount";
                        mandatedDTO.addStringProperties(annualColumn + columnName, mandAmt);

                        columnName = actualFlag ? "ActualsRate" : "ProjectionsRate";
                        mandatedDTO.addStringProperties(annualColumn + columnName, mandRate);

                        columnName = actualFlag ? "ActualsRPU" : "ProjectionsRPU";
                        mandatedDTO.addStringProperties(annualColumn + columnName, mandRPU);
                    }
                }
                if ("supplemental".equalsIgnoreCase(mandSupp) || Constant.BOTH_SMALL.equalsIgnoreCase(mandSupp)) {
                    String suppAmt = StringUtils.EMPTY + obj[3];
                    suppAmt = getFormatValue(2, suppAmt, CURRENCY);
                    columnName = actualFlag ? "ActualsAmount" : "ProjectionsAmount";
                    supplDTO.addStringProperties(commonColumn + columnName, suppAmt);
                    String suppRate = StringUtils.EMPTY + obj[4];
                    suppRate = getFormatValue(2, suppRate, PERCENTAGE);
                    columnName = actualFlag ? "ActualsRate" : "ProjectionsRate";
                    supplDTO.addStringProperties(commonColumn + columnName, suppRate);
                    String suppRPU = StringUtils.EMPTY + obj[5];
                    suppRPU = getFormatValue(2, suppRPU, CURRENCY);
                    columnName = actualFlag ? "ActualsRPU" : "ProjectionsRPU";
                    supplDTO.addStringProperties(commonColumn + columnName, suppRPU);
                    if (!annualColumn.equals(StringUtils.EMPTY) && commonColumn.contains(annualColumn) && rightHeaderColumns.toString().contains(commonColumn)) {
                        if (actualFlag) {
                            annualSuppamt = annualSuppamt + (obj[3] != null ? Double.valueOf(String.valueOf(obj[3])) : 0);
                            suppAmt = getFormatValue(2, String.valueOf(annualSuppamt), CURRENCY);
                            annualSupprate = annualSupprate + (obj[4] != null ? Double.valueOf(String.valueOf(obj[4])) : 0);
                            suppRate = getFormatValue(2, String.valueOf(annualSupprate), PERCENTAGE);
                            annualSupprpu = annualSupprpu + (obj[5] != null ? Double.valueOf(String.valueOf(obj[5])) : 0);
                            suppRPU = getFormatValue(2, String.valueOf(annualSupprpu), CURRENCY);
                        } else {
                            annualSuppProjamt = annualSuppProjamt + (obj[3] != null ? Double.valueOf(String.valueOf(obj[3])) : 0);
                            suppAmt = getFormatValue(2, String.valueOf(annualSuppProjamt), CURRENCY);
                            annualSuppProjrate = annualSuppProjrate + (obj[4] != null ? Double.valueOf(String.valueOf(obj[4])) : 0);
                            suppRate = getFormatValue(2, String.valueOf(annualSuppProjrate), PERCENTAGE);
                            annualSuppProjrpu = annualSuppProjrpu + (obj[5] != null ? Double.valueOf(String.valueOf(obj[5])) : 0);
                            suppRPU = getFormatValue(2, String.valueOf(annualSuppProjrpu), CURRENCY);
                        }
                        columnName = actualFlag ? "ActualsAmount" : "ProjectionsAmount";
                        supplDTO.addStringProperties(annualColumn + columnName, suppAmt);

                        columnName = actualFlag ? "ActualsRate" : "ProjectionsRate";
                        supplDTO.addStringProperties(annualColumn + columnName, suppRate);

                        columnName = actualFlag ? "ActualsRPU" : "ProjectionsRPU";
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
        List<DiscountProjectionResultsDTO> projDTOList = new ArrayList<DiscountProjectionResultsDTO>();
        List<String> periodList = new ArrayList<String>(projSelDTO.getPeriodList());
        String mandSupp = projSelDTO.getMandatedOrSupp();
        String freq = projSelDTO.getFrequency();

        String type = StringUtils.EMPTY;
        String lastPeriod = StringUtils.EMPTY;
        DiscountProjectionResultsDTO projDTO = null;
        for (int i = 0; i < list.size(); i++) {
            final Object[] row = (Object[]) list.get(i);
            boolean annualFlag = false;
            boolean actualFlag = false;
            String columnName = StringUtils.EMPTY;
            if (ANNUALLY.equalsIgnoreCase(freq)) {
                annualFlag = true;
            }
            if (annualFlag) {
                type = String.valueOf(row[10]);
            } else {
                type = String.valueOf(row[11]);
            }
            String column = StringUtils.EMPTY;
            int year = annualFlag ? Integer.valueOf(String.valueOf(row[9])) : Integer.valueOf(String.valueOf(row[10]));
            int period = annualFlag ? 0 : Integer.valueOf(String.valueOf(row[9]));
            List<String> common = HeaderUtils.getCommonColumnHeader(frequencyDivision, year, period, false);
            String pcommonColumn = common.get(0);
            String commonHeader = common.get(1);
            String commonColumn = StringUtils.EMPTY;
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
                String value = Constant.NULL;
                commonColumn = Constant.TOTALDISCOUNT;
                column = actualFlag ? "ActualsAmount" : "ProjectionsAmount";
                columnName = commonColumn + column;
                if (projSelDTO.hasColumn(columnName)) {
                    value = StringUtils.EMPTY + row[6];
                    value = getFormatValue(2, value, CURRENCY);
                    projDTO.addStringProperties(columnName, value);
                }
                column = actualFlag ? "ActualsRate" : "ProjectionsRate";
                columnName = commonColumn + column;
                if (projSelDTO.hasColumn(columnName)) {
                    value = StringUtils.EMPTY + row[7];
                    value = getFormatValue(2, value, PERCENTAGE);
                    projDTO.addStringProperties(columnName, value);
                }
                column = actualFlag ? "ActualsRPU" : "ProjectionsRPU";
                columnName = commonColumn + column;
                if (projSelDTO.hasColumn(columnName)) {
                    value = StringUtils.EMPTY + row[8];
                    value = getFormatValue(2, value, CURRENCY);
                    projDTO.addStringProperties(columnName, value);
                }
                if ("mandated".equalsIgnoreCase(mandSupp) || Constant.BOTH_SMALL.equalsIgnoreCase(mandSupp)) {
                    commonColumn = "MandatedDiscount";
                    column = actualFlag ? "ActualsAmount" : "ProjectionsAmount";
                    columnName = commonColumn + column;
                    if (projSelDTO.hasColumn(columnName)) {
                        value = StringUtils.EMPTY + row[0];
                        value = getFormatValue(2, value, CURRENCY);
                        projDTO.addStringProperties(columnName, value);
                    }
                    column = actualFlag ? "ActualsRate" : "ProjectionsRate";
                    columnName = commonColumn + column;
                    if (projSelDTO.hasColumn(columnName)) {
                        value = StringUtils.EMPTY + row[1];
                        value = getFormatValue(2, value, PERCENTAGE);
                        projDTO.addStringProperties(columnName, value);
                    }
                    column = actualFlag ? "ActualsRPU" : "ProjectionsRPU";
                    columnName = commonColumn + column;
                    if (projSelDTO.hasColumn(columnName)) {
                        value = StringUtils.EMPTY + row[2];
                        value = getFormatValue(2, value, CURRENCY);
                        projDTO.addStringProperties(columnName, value);
                    }
                }
                if ("supplemental".equalsIgnoreCase(mandSupp) || Constant.BOTH_SMALL.equalsIgnoreCase(mandSupp)) {
                    commonColumn = "SupplementalDiscount";
                    column = actualFlag ? "ActualsAmount" : "ProjectionsAmount";
                    columnName = commonColumn + column;
                    if (projSelDTO.hasColumn(columnName)) {
                        value = StringUtils.EMPTY + row[3];
                        value = getFormatValue(2, value, CURRENCY);
                        projDTO.addStringProperties(columnName, value);
                    }
                    column = actualFlag ? "ActualsRate" : "ProjectionsRate";
                    columnName = commonColumn + column;
                    if (projSelDTO.hasColumn(columnName)) {
                        value = StringUtils.EMPTY + row[4];
                        value = getFormatValue(2, value, PERCENTAGE);
                        projDTO.addStringProperties(columnName, value);
                    }
                    column = actualFlag ? "ActualsRPU" : "ProjectionsRPU";
                    columnName = commonColumn + column;
                    if (projSelDTO.hasColumn(columnName)) {
                        value = StringUtils.EMPTY + row[5];
                        value = getFormatValue(2, value, CURRENCY);
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
//        for (String ob : periodList) {
//            DiscountProjectionResultsDTO projDTO = new DiscountProjectionResultsDTO();
//            projDTO.setParent(0);
//            projDTO.setProjectionTotal(1);
////            projDTO.setGroup(projSelDTO.getPeriodListMap().get(ob));
//            projDTO.setLevelValue(projSelDTO.getPeriodListMap().get(ob));
//            projDTOList.add(projDTO);
//        }
        return projDTOList;
    }

    public String getFormattedValue(DecimalFormat FORMAT, String value) {
        if (value.contains(Constant.NULL)) {
            value = "...";
        } else {
            value = FORMAT.format(Double.valueOf(value));
        }
        return value;
    }

    public String getFormatValue(int numberOfDecimal, String value, String appendChar) {
        if (value.contains(Constant.NULL)) {
            value = "...";
        } else {
            if (CURRENCY.equals(appendChar)) {
                value = String.valueOf(new BigDecimal(String.valueOf(value)).setScale(numberOfDecimal, BigDecimal.ROUND_DOWN));
                value = getFormattedValue(CUR_TWO, value);

            } else if (Constant.PERCENT.equals(appendChar)) {
                value = String.valueOf(new BigDecimal(String.valueOf(value)).setScale(numberOfDecimal, BigDecimal.ROUND_DOWN));
                value = getFormattedValue(PER_THREE, value).concat(appendChar);
            }
        }
        return value;
    }

    private String getProgramCodeQuery(int projectionId, int userID, int sessionID, int levelNo, String hierarchyNo, String frequency, String View, ProjectionSelectionDTO projSelDTO, String freqChar) {
        String hierarchy = StringUtils.EMPTY;
        String columnName = StringUtils.EMPTY;
        String customerLevelNo = StringUtils.EMPTY;
        String prodLevelNo = StringUtils.EMPTY;
        String customerHierNo = StringUtils.isBlank(String.valueOf(projSelDTO.getCustomerHierarchyNo())) ? StringUtils.EMPTY : String.valueOf(projSelDTO.getCustomerHierarchyNo());
        String prodHierNo = StringUtils.isBlank(String.valueOf(projSelDTO.getProductHierarchyNo())) ? StringUtils.EMPTY : String.valueOf(projSelDTO.getProductHierarchyNo());
        List list;
        int customID = projSelDTO.getCustomId();
        int customLevelNo = projSelDTO.getCustomLevelNo();
        String hierarchyIndicator = projSelDTO.getHierarchyIndicator().trim();
        if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equalsIgnoreCase(hierarchyIndicator)) {
            customerLevelNo = StringUtils.isBlank(String.valueOf(customLevelNo)) ? Constant.PERCENT : String.valueOf(customLevelNo);
            prodLevelNo = Constant.PERCENT;
        } else {
            customerLevelNo = Constant.PERCENT;
            prodLevelNo = StringUtils.isBlank(String.valueOf(customLevelNo)) ? Constant.PERCENT : String.valueOf(customLevelNo);
        }

        if (Constant.CUSTOMER_SMALL.equalsIgnoreCase(View)) {
            hierarchy = "PROJECTION_CUST_HIERARCHY";
        } else {
            hierarchy = "PROJECTION_PROD_HIERARCHY";
        }
        String hierSQL = "SELECT FIELD_NAME FROM dbo.HIERARCHY_LEVEL_DEFINITION WHERE HIERARCHY_DEFINITION_SID=" + projSelDTO.getCustHierarchySID() + " and LEVEL_NAME='Contract'";
        list = (List<Object>) CommonLogic.executeSelectQuery(hierSQL, null, null);

        if (list != null && !list.isEmpty()) {
            final Object obj = list.get(0);
            columnName = String.valueOf(obj);
        }

        if (StringUtils.isBlank(columnName)) {
            columnName = "contract_name";
        }
        String customSQL = "SELECT \n"
                + "       rld.LEVEL_NO,\n"
                + "       HLD.LEVEL_NAME,\n"
                + "       cm." + columnName + ",\n"
                + "CCP.HIERARCHY_NO,\n"
                + "p.\"YEAR\",\n"
                + "       Sum(MAD.ACTUAL_SALES)        AS  Disc_Amt,\n"
                + "Coalesce(Sum(MAD.ACTUAL_SALES)/Nullif(Sum(m_ac.ACTUAL_SALES),0),0)*100       AS  Disc_Rate,\n"
                + "       Sum(SPMA.ACTUAL_SALES)        AS  Supp_Amt,\n"
                + "Coalesce(Sum(SPMA.ACTUAL_SALES)/Nullif(Sum(m_ac.ACTUAL_SALES),0),0)*100      AS  Supp_Rate,\n"
                + " Coalesce(Sum(MAD.ACTUAL_SALES)/Nullif(Sum(m_ac.ACTUAL_UNITS),0),0) AS Man_RPU, Coalesce(Sum(SPMA.ACTUAL_SALES)/Nullif(Sum(m_ac.ACTUAL_UNITS),0),0) As Sup_RPU,\n"
                + "       0                            As ActualProj\n";
        if (!"\"YEAR\"".equalsIgnoreCase(frequency)) {
            customSQL += "         , p." + frequency + "\n";
            customSQL += ",'" + freqChar + "'+CONVERT(VARCHAR(2),P." + frequency + ")+' '+CONVERT(VARCHAR(4),P.YEAR) AS PERIOD\n";
        }
        customSQL += "FROM   projection_details pd\n";

        if (!Constant.CUSTOM.equalsIgnoreCase(View)) {

            customSQL += "JOIN   " + hierarchy + " ph ON pd.PROJECTION_MASTER_SID = ph.PROJECTION_MASTER_SID\n"
                    + "JOIN   (SELECT DISTINCT\n"
                    + "               LCCP.RELATIONSHIP_LEVEL_SID,\n"
                    + "               LCCP.CCP_DETAILS_SID,\n"
                    + "               LCCP.HIERARCHY_NO\n"
                    + "        FROM   (SELECT CCPMAP.CCP_DETAILS_SID,\n"
                    + "                       HLD.HIERARCHY_NO,\n"
                    + "                       HLD.RELATIONSHIP_LEVEL_SID\n"
                    + "                FROM   (SELECT RLD.RELATIONSHIP_LEVEL_VALUES,\n"
                    + "                               RLD.HIERARCHY_NO,\n"
                    + "                               CCP.CCP_DETAILS_SID\n"
                    + "                        FROM   RELATIONSHIP_LEVEL_DEFINITION RLD\n"
                    + "                        JOIN   CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID = CCP.RELATIONSHIP_LEVEL_SID\n"
                    + "                        JOIN   PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID\n"
                    + "                                                    AND PD.PROJECTION_MASTER_SID = " + projectionId + "\n"
                    + "                        JOIN   PROJECTION_MASTER PM ON PD.PROJECTION_MASTER_SID = PM.PROJECTION_MASTER_SID\n"
                    + "                        WHERE  PM.PROJECTION_MASTER_SID = " + projectionId + ") CCPMAP,\n"
                    + "                       (SELECT RLD1.HIERARCHY_NO,\n"
                    + "                               RLD1.RELATIONSHIP_LEVEL_SID\n"
                    + "                        FROM   RELATIONSHIP_LEVEL_DEFINITION RLD1\n"
                    + "                        JOIN   " + hierarchy + " PCH ON PCH.RELATIONSHIP_LEVEL_SID = RLD1.RELATIONSHIP_LEVEL_SID\n"
                    + "                                                            AND PCH.PROJECTION_MASTER_SID = " + projectionId + "\n"
                    + "                        WHERE  RLD1.HIERARCHY_NO LIKE '" + hierarchyNo + "%') HLD\n"
                    + "                WHERE  CCPMAP.HIERARCHY_NO LIKE HLD.HIERARCHY_NO + '%') LCCP\n"
                    + "        WHERE  LCCP.HIERARCHY_NO IN (SELECT RLD2.HIERARCHY_NO\n"
                    + "                                     FROM   RELATIONSHIP_LEVEL_DEFINITION RLD2\n"
                    + "                                     JOIN   " + hierarchy + " PCH2 ON PCH2.RELATIONSHIP_LEVEL_SID = RLD2.RELATIONSHIP_LEVEL_SID\n"
                    + "                                                                          AND PCH2.PROJECTION_MASTER_SID = " + projectionId + "\n"
                    + "                                     WHERE  RLD2.LEVEL_NO = " + levelNo + ")) CCP ON CCP.CCP_DETAILS_SID = pd.CCP_DETAILS_SID\n";

        } else {

            customSQL += "JOIN    (SELECT distinct HLD" + hierarchyIndicator + ".RELATIONSHIP_LEVEL_SID,HLD" + hierarchyIndicator + ".HIERARCHY_NO, CCPMAPC.CCP_DETAILS_SID FROM\n"
                    + "  (SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID FROM RELATIONSHIP_LEVEL_DEFINITION RLD\n"
                    + "  JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID\n"
                    + "  JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID=" + projectionId + "\n"
                    + "  ) CCPMAPC\n"
                    + "  JOIN\n"
                    + "  (SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID FROM RELATIONSHIP_LEVEL_DEFINITION RLD\n"
                    + "  JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID\n"
                    + "  JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID=" + projectionId + "\n"
                    + "  ) CCPMAPP ON CCPMAPC.CCP_DETAILS_SID=CCPMAPP.CCP_DETAILS_SID\n"
                    + " JOIN  (SELECT RLD2.HIERARCHY_NO,RLD2.RELATIONSHIP_LEVEL_SID, CVD.LEVEL_NO FROM dbo.CUSTOM_VIEW_DETAILS CVD\n"
                    + "  JOIN dbo.CUSTOM_VIEW_MASTER CVM ON CVD.CUSTOM_VIEW_MASTER_SID='" + customID + "'  AND CVD.LEVEL_NO  like '" + customerLevelNo + "'\n"
                    + "  JOIN dbo.HIERARCHY_LEVEL_DEFINITION HLD ON CVD.HIERARCHY_ID=HLD.HIERARCHY_LEVEL_DEFINITION_SID\n"
                    + "  JOIN RELATIONSHIP_LEVEL_DEFINITION RLD2 ON HLD.HIERARCHY_LEVEL_DEFINITION_SID=RLD2.HIERARCHY_LEVEL_DEFINITION_SID\n"
                    + "  JOIN PROJECTION_CUST_HIERARCHY PCH2 ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID AND PCH2.PROJECTION_MASTER_SID=" + projectionId + "\n"
                    + "  WHERE RLD2.HIERARCHY_NO like '" + customerHierNo + "%') HLDC ON CCPMAPC.HIERARCHY_NO like HLDC.HIERARCHY_NO+'%'\n"
                    + " JOIN    (SELECT RLD2.HIERARCHY_NO,RLD2.RELATIONSHIP_LEVEL_SID, CVD.LEVEL_NO FROM dbo.CUSTOM_VIEW_DETAILS CVD\n"
                    + "  JOIN dbo.CUSTOM_VIEW_MASTER CVM ON\n"
                    + "  CVD.CUSTOM_VIEW_MASTER_SID='" + customID + "' AND CVD.LEVEL_NO like '" + prodLevelNo + "'\n"
                    + "  JOIN dbo.HIERARCHY_LEVEL_DEFINITION HLD ON CVD.HIERARCHY_ID=HLD.HIERARCHY_LEVEL_DEFINITION_SID\n"
                    + "  JOIN RELATIONSHIP_LEVEL_DEFINITION RLD2 ON HLD.HIERARCHY_LEVEL_DEFINITION_SID=RLD2.HIERARCHY_LEVEL_DEFINITION_SID\n"
                    + "  JOIN PROJECTION_PROD_HIERARCHY PCH2 ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID AND PCH2.PROJECTION_MASTER_SID=" + projectionId + "\n"
                    + "  WHERE RLD2.HIERARCHY_NO like '" + prodHierNo + "%' ) HLDP ON CCPMAPP.HIERARCHY_NO like HLDP.HIERARCHY_NO+'%'\n"
                    + "  )CCP ON CCP.CCP_DETAILS_SID = pd.CCP_DETAILS_SID\n";
        }

        customSQL += "join ccp_details ccd on ccd.CCP_DETAILS_SID=pd.CCP_DETAILS_SID\n"
                + "join contract_master cm on cm.contract_master_sid=ccd.contract_master_sid\n"
                + "JOIN   RELATIONSHIP_LEVEL_DEFINITION rld ON CCP.RELATIONSHIP_LEVEL_SID = rld.RELATIONSHIP_LEVEL_SID\n";
        if (!Constant.CUSTOM.equalsIgnoreCase(View)) {
            customSQL += "                                        AND ph.RELATIONSHIP_LEVEL_SID = rld.RELATIONSHIP_LEVEL_SID\n";

        }
        customSQL += "JOIN   ST_M_SALES_PROJECTION_MASTER m_mas ON pd.PROJECTION_DETAILS_SID = m_mas.PROJECTION_DETAILS_SID\n"
                + "                                         AND m_mas.user_id = " + userID + "\n"
                + "                                         AND m_mas.session_id = " + sessionID + "\n"
                + "JOIN   HIERARCHY_LEVEL_DEFINITION HLD ON HLD.HIERARCHY_LEVEL_DEFINITION_SID = rld.HIERARCHY_LEVEL_DEFINITION_SID\n"
                + "JOIN   ST_M_ACTUAL_SALES m_ac ON m_mas.PROJECTION_DETAILS_SID = m_ac.PROJECTION_DETAILS_SID\n"
                + "                             AND m_ac.user_id = m_mas.user_id\n"
                + "                             AND m_ac.session_id = m_mas.session_id\n"
                + "JOIN   M_ACTUAL_DISCOUNT MAD ON MAD.PROJECTION_DETAILS_SID = pd.PROJECTION_DETAILS_SID\n"
                + "AND MAD.PERIOD_SID=m_ac.PERIOD_SID\n"
                + "                            AND MAD.user_id = m_mas.user_id\n"
                + "                            AND MAD.session_id = m_mas.session_id\n"
                + "LEFT JOIN   ST_M_SUPPLEMENTAL_DISC_MASTER SPM ON SPM.PROJECTION_DETAILS_SID = pd.PROJECTION_DETAILS_SID\n"
                + "\n"
                + "                                        AND SPM.user_id = m_mas.user_id\n"
                + "                                        AND SPM.session_id = m_mas.session_id\n"
                + "LEFT JOIN   ST_M_SUPPLEMENTAL_DISC_ACTUALS SPMA ON SPMA.PROJECTION_DETAILS_SID = pd.PROJECTION_DETAILS_SID\n"
                + "										   AND SPMA.PERIOD_SID=m_ac.PERIOD_SID\n"
                + "                                          AND SPMA.user_id = m_mas.user_id\n"
                + "                                          AND SPMA.session_id = m_mas.session_id\n"
                + "JOIN   PERIOD p ON p.period_sid = m_ac.PERIOD_SID\n"
                + "WHERE  pd.PROJECTION_MASTER_SID = " + projectionId + "\n"
                + "   AND rld.LEVEL_NO = " + levelNo + "\n"
                + "   AND m_mas.USER_ID = " + userID + "\n"
                + "   AND m_mas.SESSION_ID = " + sessionID + "\n"
                + "   AND m_ac.USER_ID = " + userID + "\n"
                + "   AND m_ac.SESSION_ID = " + sessionID + "\n"
                + "GROUP  BY cm." + columnName + ",\n";
        if (!"\"YEAR\"".equalsIgnoreCase(frequency)) {
            customSQL += "          p." + frequency + ",\n";
        }
        customSQL += "          rld.RELATIONSHIP_LEVEL_SID,\n"
                + "          rld.LEVEL_NO,rld.LEVEL_NAME,\n"
                + "CCP.HIERARCHY_NO,\n"
                + "          p.\"YEAR\",\n"
                + "          HLD.LEVEL_NAME\n"
                + "Union \n"
                + "\n"
                + "SELECT\n"
                + "       rld.LEVEL_NO,\n"
                + "       HLD.LEVEL_NAME,\n"
                + "       cm." + columnName + ",\n"
                + "CCP.HIERARCHY_NO,\n"
                + "p.\"YEAR\",\n"
                + "       Sum(MAD.PROJECTION_SALES)        AS  Disc_Amt,\n"
                + "Coalesce(Sum(MAD.PROJECTION_SALES)/Nullif(Sum(m_ac.PROJECTION_SALES),0),0)*100 AS  Disc_Rate,\n"
                + "       Sum(SPMA.PROJECTION_SALES)        AS  Supp_Amt,\n"
                + "Coalesce(Sum(SPMA.PROJECTION_SALES)/Nullif(Sum(m_ac.PROJECTION_SALES),0),0)*100 AS  Supp_Rate,\n"
                + " Coalesce(Sum(MAD.PROJECTION_SALES)/Nullif(Sum(m_ac.PROJECTION_UNITS),0),0) AS Man_RPU, Coalesce(Sum(SPMA.PROJECTION_SALES)/Nullif(Sum(m_ac.PROJECTION_UNITS),0),0) As Sup_RPU, \n"
                + "       1                            As ActualProj\n";
        if (!"\"YEAR\"".equalsIgnoreCase(frequency)) {
            customSQL += "         , p." + frequency + "\n";
            customSQL += ",'" + freqChar + "'+CONVERT(VARCHAR(2),P." + frequency + ")+' '+CONVERT(VARCHAR(4),P.YEAR) AS PERIOD\n";
        }
        customSQL += "FROM   projection_details pd\n";

        if (!Constant.CUSTOM.equalsIgnoreCase(View)) {
            customSQL += "JOIN   " + hierarchy + " ph ON pd.PROJECTION_MASTER_SID = ph.PROJECTION_MASTER_SID\n"
                    + "JOIN   (SELECT DISTINCT\n"
                    + "               LCCP.RELATIONSHIP_LEVEL_SID,\n"
                    + "               LCCP.CCP_DETAILS_SID,\n"
                    + "               LCCP.HIERARCHY_NO\n"
                    + "        FROM   (SELECT CCPMAP.CCP_DETAILS_SID,\n"
                    + "                       HLD.HIERARCHY_NO,\n"
                    + "                       HLD.RELATIONSHIP_LEVEL_SID\n"
                    + "                FROM   (SELECT RLD.RELATIONSHIP_LEVEL_VALUES,\n"
                    + "                               RLD.HIERARCHY_NO,\n"
                    + "                               CCP.CCP_DETAILS_SID\n"
                    + "                        FROM   RELATIONSHIP_LEVEL_DEFINITION RLD\n"
                    + "                        JOIN   CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID = CCP.RELATIONSHIP_LEVEL_SID\n"
                    + "                        JOIN   PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID\n"
                    + "                                                    AND PD.PROJECTION_MASTER_SID = " + projectionId + "\n"
                    + "                        JOIN   PROJECTION_MASTER PM ON PD.PROJECTION_MASTER_SID = PM.PROJECTION_MASTER_SID\n"
                    + "                        WHERE  PM.PROJECTION_MASTER_SID = " + projectionId + ") CCPMAP,\n"
                    + "                       (SELECT RLD1.HIERARCHY_NO,\n"
                    + "                               RLD1.RELATIONSHIP_LEVEL_SID\n"
                    + "                        FROM   RELATIONSHIP_LEVEL_DEFINITION RLD1\n"
                    + "                        JOIN   " + hierarchy + " PCH ON PCH.RELATIONSHIP_LEVEL_SID = RLD1.RELATIONSHIP_LEVEL_SID\n"
                    + "                                                            AND PCH.PROJECTION_MASTER_SID = " + projectionId + "\n"
                    + "                        WHERE  RLD1.HIERARCHY_NO LIKE '" + hierarchyNo + "%') HLD\n"
                    + "                WHERE  CCPMAP.HIERARCHY_NO LIKE HLD.HIERARCHY_NO + '%') LCCP\n"
                    + "        WHERE  LCCP.HIERARCHY_NO IN (SELECT RLD2.HIERARCHY_NO\n"
                    + "                                     FROM   RELATIONSHIP_LEVEL_DEFINITION RLD2\n"
                    + "                                     JOIN   " + hierarchy + " PCH2 ON PCH2.RELATIONSHIP_LEVEL_SID = RLD2.RELATIONSHIP_LEVEL_SID\n"
                    + "                                                                          AND PCH2.PROJECTION_MASTER_SID = " + projectionId + "\n"
                    + "                                     WHERE  RLD2.LEVEL_NO = " + levelNo + ")) CCP ON CCP.CCP_DETAILS_SID = pd.CCP_DETAILS_SID\n";
        } else {
            customSQL += "JOIN    (SELECT distinct HLD" + hierarchyIndicator + ".RELATIONSHIP_LEVEL_SID,HLD" + hierarchyIndicator + ".HIERARCHY_NO, CCPMAPC.CCP_DETAILS_SID FROM\n"
                    + "  (SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID FROM RELATIONSHIP_LEVEL_DEFINITION RLD\n"
                    + "  JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID\n"
                    + "  JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID=" + projectionId + "\n"
                    + "  ) CCPMAPC\n"
                    + "  JOIN\n"
                    + "  (SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID FROM RELATIONSHIP_LEVEL_DEFINITION RLD\n"
                    + "  JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID\n"
                    + "  JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID=" + projectionId + "\n"
                    + "  ) CCPMAPP ON CCPMAPC.CCP_DETAILS_SID=CCPMAPP.CCP_DETAILS_SID\n"
                    + " JOIN  (SELECT RLD2.HIERARCHY_NO,RLD2.RELATIONSHIP_LEVEL_SID, CVD.LEVEL_NO FROM dbo.CUSTOM_VIEW_DETAILS CVD\n"
                    + "  JOIN dbo.CUSTOM_VIEW_MASTER CVM ON CVD.CUSTOM_VIEW_MASTER_SID='" + customID + "'  AND CVD.LEVEL_NO  like '" + customerLevelNo + "'\n"
                    + "  JOIN dbo.HIERARCHY_LEVEL_DEFINITION HLD ON CVD.HIERARCHY_ID=HLD.HIERARCHY_LEVEL_DEFINITION_SID\n"
                    + "  JOIN RELATIONSHIP_LEVEL_DEFINITION RLD2 ON HLD.HIERARCHY_LEVEL_DEFINITION_SID=RLD2.HIERARCHY_LEVEL_DEFINITION_SID\n"
                    + "  JOIN PROJECTION_CUST_HIERARCHY PCH2 ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID AND PCH2.PROJECTION_MASTER_SID=" + projectionId + "\n"
                    + "  WHERE RLD2.HIERARCHY_NO like '" + customerHierNo + "%') HLDC ON CCPMAPC.HIERARCHY_NO like HLDC.HIERARCHY_NO+'%'\n"
                    + " JOIN    (SELECT RLD2.HIERARCHY_NO,RLD2.RELATIONSHIP_LEVEL_SID, CVD.LEVEL_NO FROM dbo.CUSTOM_VIEW_DETAILS CVD\n"
                    + "  JOIN dbo.CUSTOM_VIEW_MASTER CVM ON\n"
                    + "  CVD.CUSTOM_VIEW_MASTER_SID='" + customID + "' AND CVD.LEVEL_NO like '" + prodLevelNo + "'\n"
                    + "  JOIN dbo.HIERARCHY_LEVEL_DEFINITION HLD ON CVD.HIERARCHY_ID=HLD.HIERARCHY_LEVEL_DEFINITION_SID\n"
                    + "  JOIN RELATIONSHIP_LEVEL_DEFINITION RLD2 ON HLD.HIERARCHY_LEVEL_DEFINITION_SID=RLD2.HIERARCHY_LEVEL_DEFINITION_SID\n"
                    + "  JOIN PROJECTION_PROD_HIERARCHY PCH2 ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID AND PCH2.PROJECTION_MASTER_SID=" + projectionId + "\n"
                    + "  WHERE RLD2.HIERARCHY_NO like '" + prodHierNo + "%' ) HLDP ON CCPMAPP.HIERARCHY_NO like HLDP.HIERARCHY_NO+'%'\n"
                    + "  )CCP ON CCP.CCP_DETAILS_SID = pd.CCP_DETAILS_SID\n";
        }

        customSQL += "join ccp_details ccd on ccd.CCP_DETAILS_SID=pd.CCP_DETAILS_SID\n"
                + "join contract_master cm on cm.contract_master_sid=ccd.contract_master_sid\n"
                + "JOIN   RELATIONSHIP_LEVEL_DEFINITION rld ON CCP.RELATIONSHIP_LEVEL_SID = rld.RELATIONSHIP_LEVEL_SID\n";
        if (!Constant.CUSTOM.equalsIgnoreCase(View)) {
            customSQL += "                                        AND ph.RELATIONSHIP_LEVEL_SID = rld.RELATIONSHIP_LEVEL_SID\n";
        }

        customSQL += "JOIN   ST_M_SALES_PROJECTION_MASTER m_mas ON pd.PROJECTION_DETAILS_SID = m_mas.PROJECTION_DETAILS_SID\n"
                + "                                         AND m_mas.user_id = " + userID + "\n"
                + "                                         AND m_mas.session_id = " + sessionID + "\n"
                + "JOIN   HIERARCHY_LEVEL_DEFINITION HLD ON HLD.HIERARCHY_LEVEL_DEFINITION_SID = rld.HIERARCHY_LEVEL_DEFINITION_SID\n"
                + "JOIN   ST_M_SALES_PROJECTION m_ac ON m_mas.PROJECTION_DETAILS_SID = m_ac.PROJECTION_DETAILS_SID\n"
                + "                             AND m_ac.user_id = m_mas.user_id\n"
                + "                             AND m_ac.session_id = m_mas.session_id\n"
                + "JOIN   M_DISCOUNT_PROJECTION MAD ON MAD.PROJECTION_DETAILS_SID = pd.PROJECTION_DETAILS_SID\n"
                + "AND MAD.PERIOD_SID=m_ac.PERIOD_SID\n"
                + "                            AND MAD.user_id = m_mas.user_id\n"
                + "                            AND MAD.session_id = m_mas.session_id\n"
                + "LEFT JOIN   ST_M_SUPPLEMENTAL_DISC_MASTER SPM ON SPM.PROJECTION_DETAILS_SID = pd.PROJECTION_DETAILS_SID\n"
                + "\n"
                + "                                        AND SPM.user_id = m_mas.user_id\n"
                + "                                        AND SPM.session_id = m_mas.session_id\n"
                + "LEFT JOIN   ST_M_SUPPLEMENTAL_DISC_PROJ SPMA ON SPMA.PROJECTION_DETAILS_SID = pd.PROJECTION_DETAILS_SID\n"
                + "                                                                        AND SPMA.PERIOD_SID=m_ac.PERIOD_SID\n"
                + "                                          AND SPMA.user_id = m_mas.user_id\n"
                + "                                          AND SPMA.session_id = m_mas.session_id\n"
                + "JOIN   PERIOD p ON p.period_sid = m_ac.PERIOD_SID\n"
                + "WHERE  pd.PROJECTION_MASTER_SID = " + projectionId + "\n"
                + "   AND rld.LEVEL_NO = " + levelNo + "\n"
                + "   AND m_mas.USER_ID = " + userID + "\n"
                + "   AND m_mas.SESSION_ID = " + sessionID + "\n"
                + "   AND m_ac.USER_ID = " + userID + "\n"
                + "   AND m_ac.SESSION_ID = " + sessionID + "\n"
                + "GROUP  BY cm." + columnName + ",\n";
        if (!"\"YEAR\"".equalsIgnoreCase(frequency)) {
            customSQL += "          p." + frequency + ",\n";
        }
        customSQL += "          rld.RELATIONSHIP_LEVEL_SID,\n"
                + "          rld.LEVEL_NO, rld.LEVEL_NAME,\n"
                + "CCP.HIERARCHY_NO,\n"
                + "          p.\"YEAR\",\n"
                + "          HLD.LEVEL_NAME\n";
        if (Constant.PERIOD.equalsIgnoreCase(projSelDTO.getPivotView())) {
            customSQL += "Order By cm." + columnName + ",p.\"YEAR\"\n";
        } else {
            customSQL += "Order By p.\"YEAR\"\n";
        }

        if (!"\"YEAR\"".equalsIgnoreCase(frequency)) {
            customSQL += "          ,p." + frequency + "\n";
        }

        return customSQL;
    }

    private String getProgramCodeNameQuery(int projectionId) {
        String customSQL = "SELECT DISTINCT CM.CONTRACT_NO FROM dbo.CONTRACT_MASTER CM \n"
                + "JOIN dbo.CCP_DETAILS CCP ON CCP.CONTRACT_MASTER_SID = CM.CONTRACT_MASTER_SID\n"
                + "JOIN dbo.PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID  = CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID = " + projectionId;
        return customSQL;
    }

    public DiscountProjectionResultsDTO getChildNodeValues(DiscountProjectionResultsDTO dto, ProjectionSelectionDTO projSelDTO) {
        Map<Integer, String> freq = new HashMap<Integer, String>();
        freq.put(1, "\"YEAR\"");
        freq.put(2, "SEMI_ANNUAL");
        freq.put(4, "QUARTER");
        freq.put(12, "\"MONTH\"");
        String frequency = freq.get(projSelDTO.getFrequencyDivision());
        String freqChar = !"\"YEAR\"".equalsIgnoreCase(frequency) ? "SEMI_ANNUAL".equalsIgnoreCase(frequency) ? Constant.S : "QUARTER".equalsIgnoreCase(frequency) ? Constant.Q : CommonUtils.BUSINESS_PROCESS_INDICATOR_MANDATED : "";
        String query = getHierarchyLevelQuery(projSelDTO.getProjectionId(), projSelDTO.getUserId(), projSelDTO.getSessionId(), dto.getLevelNo(), projSelDTO.getHierarchyNo(), frequency, projSelDTO.getView(), projSelDTO, dto.getCustomerHierarchyNo(), dto.getProductHierarchyNo(), dto.getLevelValue(), freqChar);
        List<Object> list = (List<Object>) CommonLogic.executeSelectQuery(query, null, null);
        double annualMandamt = 0.0, annualMandrate = 0.0, annualMandrpu = 0.0, annualMandProjamt = 0.0, annualMandProjrate = 0.0, annualMandProjrpu = 0.0,
                annualSuppamt = 0.0, annualSupprate = 0.0, annualSupprpu = 0.0, annualSuppProjamt = 0.0, annualSuppProjrate = 0.0, annualSuppProjrpu = 0.0;
        String tempAnnual = StringUtils.EMPTY;
        List<Object> rightHeaderColumns = projSelDTO.getRightHeaderDoubleColumns();
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

                String columnName = StringUtils.EMPTY;
                final Object[] obj = (Object[]) list1;
                actualFlag = Integer.valueOf(String.valueOf(obj[11])) == 0;
                String mandSupp = projSelDTO.getMandatedOrSupp();

                int year = Integer.valueOf(String.valueOf(obj[4]));
                int period = projSelDTO.getFrequencyDivision() == 1 ? 0 : Integer.valueOf(String.valueOf(obj[12]));
                List<String> annualTotal = new ArrayList<String>();
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
                if ("mandated".equalsIgnoreCase(mandSupp)) {

                    String mandAmt = StringUtils.EMPTY + obj[5];
                    mandAmt = getFormatValue(2, mandAmt, CURRENCY);
                    columnName = actualFlag ? "ActualsAmount" : "ProjectionsAmount";
                    mandatedDTO.addStringProperties(commonColumn + columnName, mandAmt);
                    dto.addStringProperties(commonColumn + columnName, mandAmt);

                    String mandRate = StringUtils.EMPTY + obj[6];
                    mandRate = getFormatValue(2, mandRate, PERCENTAGE);
                    columnName = actualFlag ? "ActualsRate" : "ProjectionsRate";
                    mandatedDTO.addStringProperties(commonColumn + columnName, mandRate);

                    dto.addStringProperties(commonColumn + columnName, mandRate);

                    String mandRPU = StringUtils.EMPTY + obj[9];
                    mandRPU = getFormatValue(2, mandRPU, CURRENCY);
                    columnName = actualFlag ? "ActualsRPU" : "ProjectionsRPU";
                    mandatedDTO.addStringProperties(commonColumn + columnName, mandRPU);
                    dto.addStringProperties(commonColumn + columnName, mandRPU);

                    if (!annualColumn.equals(StringUtils.EMPTY) && commonColumn.contains(annualColumn) && rightHeaderColumns.toString().contains(commonColumn)) {
                        if (actualFlag) {
                            annualMandamt = annualMandamt + (obj[5] != null ? Double.valueOf(String.valueOf(obj[5])) : 0);
                            mandAmt = getFormatValue(2, String.valueOf(annualMandamt), CURRENCY);
                            annualMandrate = annualMandrate + (obj[6] != null ? Double.valueOf(String.valueOf(obj[6])) : 0);
                            mandRate = getFormatValue(2, String.valueOf(annualMandrate), PERCENTAGE);
                            annualMandrpu = annualMandrpu + (obj[9] != null ? Double.valueOf(String.valueOf(obj[9])) : 0);
                            mandRPU = getFormatValue(2, String.valueOf(annualMandrpu), CURRENCY);
                        } else {
                            annualMandProjamt = annualMandProjamt + (obj[5] != null ? Double.valueOf(String.valueOf(obj[5])) : 0);
                            mandAmt = getFormatValue(2, String.valueOf(annualMandProjamt), CURRENCY);
                            annualMandProjrate = annualMandProjrate + (obj[6] != null ? Double.valueOf(String.valueOf(obj[6])) : 0);
                            mandRate = getFormatValue(2, String.valueOf(annualMandProjrate), PERCENTAGE);
                            annualMandProjrpu = annualMandProjrpu + (obj[9] != null ? Double.valueOf(String.valueOf(obj[9])) : 0);
                            mandRPU = getFormatValue(2, String.valueOf(annualMandProjrpu), CURRENCY);
                        }
                        columnName = actualFlag ? "ActualsAmount" : "ProjectionsAmount";
                        mandatedDTO.addStringProperties(annualColumn + columnName, mandAmt);
                        dto.addStringProperties(annualColumn + columnName, mandAmt);

                        columnName = actualFlag ? "ActualsRate" : "ProjectionsRate";
                        mandatedDTO.addStringProperties(annualColumn + columnName, mandRate);
                        dto.addStringProperties(annualColumn + columnName, mandRate);

                        columnName = actualFlag ? "ActualsRPU" : "ProjectionsRPU";
                        mandatedDTO.addStringProperties(annualColumn + columnName, mandRPU);
                        dto.addStringProperties(annualColumn + columnName, mandRPU);
                    }
                }
                if ("supplemental".equalsIgnoreCase(mandSupp)) {
                    String suppAmt = StringUtils.EMPTY + obj[7];
                    suppAmt = getFormatValue(2, suppAmt, CURRENCY);
                    columnName = actualFlag ? "ActualsAmount" : "ProjectionsAmount";
                    suppDTO.addStringProperties(commonColumn + columnName, suppAmt);
                    dto.addStringProperties(commonColumn + columnName, suppAmt);
                    String suppRate = StringUtils.EMPTY + obj[8];
                    suppRate = getFormatValue(2, suppRate, PERCENTAGE);
                    columnName = actualFlag ? "ActualsRate" : "ProjectionsRate";
                    suppDTO.addStringProperties(commonColumn + columnName, suppRate);
                    dto.addStringProperties(commonColumn + columnName, suppRate);
                    String suppRPU = StringUtils.EMPTY + obj[10];
                    suppRPU = getFormatValue(2, suppRPU, CURRENCY);
                    columnName = actualFlag ? "ActualsRate" : "ProjectionsRate";
                    suppDTO.addStringProperties(commonColumn + columnName, suppRPU);
                    dto.addStringProperties(commonColumn + columnName, suppRPU);

                    if (!annualColumn.equals(StringUtils.EMPTY) && commonColumn.contains(annualColumn) && rightHeaderColumns.toString().contains(commonColumn)) {
                        if (actualFlag) {
                            annualSuppamt = annualSuppamt + (obj[7] != null ? Double.valueOf(String.valueOf(obj[7])) : 0);
                            suppAmt = getFormatValue(2, String.valueOf(annualSuppamt), CURRENCY);
                            annualSupprate = annualSupprate + (obj[8] != null ? Double.valueOf(String.valueOf(obj[8])) : 0);
                            suppRate = getFormatValue(2, String.valueOf(annualSupprate), PERCENTAGE);
                            annualSupprpu = annualSupprpu + (obj[10] != null ? Double.valueOf(String.valueOf(obj[10])) : 0);
                            suppRPU = getFormatValue(2, String.valueOf(annualSupprpu), CURRENCY);
                        } else {
                            annualSuppProjamt = annualSuppProjamt + (obj[7] != null ? Double.valueOf(String.valueOf(obj[7])) : 0);
                            suppAmt = getFormatValue(2, String.valueOf(annualSuppProjamt), CURRENCY);
                            annualSuppProjrate = annualSuppProjrate + (obj[8] != null ? Double.valueOf(String.valueOf(obj[8])) : 0);
                            suppRate = getFormatValue(2, String.valueOf(annualSuppProjrate), PERCENTAGE);
                            annualSuppProjrpu = annualSuppProjrpu + (obj[10] != null ? Double.valueOf(String.valueOf(obj[10])) : 0);
                            suppRPU = getFormatValue(2, String.valueOf(annualSuppProjrpu), CURRENCY);
                        }

                        columnName = actualFlag ? "ActualsAmount" : "ProjectionsAmount";
                        suppDTO.addStringProperties(annualColumn + columnName, suppAmt);
                        dto.addStringProperties(annualColumn + columnName, suppAmt);

                        columnName = actualFlag ? "ActualsRate" : "ProjectionsRate";
                        suppDTO.addStringProperties(annualColumn + columnName, suppRate);
                        dto.addStringProperties(annualColumn + columnName, suppRate);

                        columnName = actualFlag ? "ActualsRPU" : "ProjectionsRPU";
                        suppDTO.addStringProperties(annualColumn + columnName, suppRPU);
                        dto.addStringProperties(annualColumn + columnName, suppRPU);
                    }
                }
                if (Constant.BOTH_SMALL.equalsIgnoreCase(mandSupp)) {
                    String mandAmt = StringUtils.EMPTY + obj[5];
                    mandAmt = getFormatValue(2, mandAmt, CURRENCY);
                    columnName = actualFlag ? "ActualsAmount" : "ProjectionsAmount";
                    mandatedDTO.addStringProperties(commonColumn + columnName, mandAmt);
                    String mandRate = StringUtils.EMPTY + obj[6];
                    mandRate = getFormatValue(2, mandRate, PERCENTAGE);
                    columnName = actualFlag ? "ActualsRate" : "ProjectionsRate";
                    mandatedDTO.addStringProperties(commonColumn + columnName, mandRate);

                    String suppAmt = StringUtils.EMPTY + obj[7];
                    suppAmt = getFormatValue(2, suppAmt, CURRENCY);
                    columnName = actualFlag ? "ActualsAmount" : "ProjectionsAmount";
                    suppDTO.addStringProperties(commonColumn + columnName, suppAmt);
                    String nullValue = "...";
                    if (nullValue.equals(suppAmt)) {
                        suppAmt = StringUtils.EMPTY;
                    }
                    Double amt = (StringUtils.isNotBlank(String.valueOf(obj[5])) ? Double.valueOf(String.valueOf(obj[5])) : 0.00) + (!Constant.NULL.equalsIgnoreCase(String.valueOf(obj[7])) && StringUtils.isNotBlank(String.valueOf(obj[7])) ? Double.valueOf(String.valueOf(obj[7])) : 0.00);
                    dto.addStringProperties(commonColumn + columnName, getFormatValue(2, String.valueOf(amt), CURRENCY));
                    String suppRate = StringUtils.EMPTY + obj[8];
                    suppRate = getFormatValue(2, suppRate, PERCENTAGE);
                    columnName = actualFlag ? "ActualsRate" : "ProjectionsRate";
                    suppDTO.addStringProperties(commonColumn + columnName, suppRate);
                    if (nullValue.equals(suppRate)) {
                        suppRate = StringUtils.EMPTY;
                    }

                    Double rate = (StringUtils.isNotBlank(String.valueOf(obj[6])) ? Double.valueOf(String.valueOf(obj[6])) : 0.00) + (!Constant.NULL.equalsIgnoreCase(String.valueOf(obj[8])) && StringUtils.isNotBlank(String.valueOf(obj[8])) ? Double.valueOf(String.valueOf(obj[8])) : 0.00);
                    dto.addStringProperties(commonColumn + columnName, getFormatValue(2, String.valueOf(rate), PERCENTAGE));
                    String mandRPU = StringUtils.EMPTY + obj[9];
                    mandRPU = getFormatValue(2, mandRPU, CURRENCY);
                    columnName = actualFlag ? "ActualsRPU" : "ProjectionsRPU";
                    mandatedDTO.addStringProperties(commonColumn + columnName, mandRPU);

                    String suppRPU = StringUtils.EMPTY + obj[10];
                    suppRPU = getFormatValue(2, suppRPU, CURRENCY);
                    columnName = actualFlag ? "ActualsRPU" : "ProjectionsRPU";
                    suppDTO.addStringProperties(commonColumn + columnName, suppRPU);
                    if (nullValue.equals(suppRPU)) {
                        suppRPU = StringUtils.EMPTY;
                    }
                    Double rpu = (StringUtils.isNotBlank(String.valueOf(obj[9])) ? Double.valueOf(String.valueOf(obj[9])) : 0.00) + (!Constant.NULL.equalsIgnoreCase(String.valueOf(obj[10])) && StringUtils.isNotBlank(String.valueOf(obj[10])) ? Double.valueOf(String.valueOf(obj[10])) : 0.00);
                    dto.addStringProperties(commonColumn + columnName, getFormatValue(2, String.valueOf(rpu), CURRENCY));

                    if (!annualColumn.equals(StringUtils.EMPTY) && commonColumn.contains(annualColumn) && rightHeaderColumns.toString().contains(commonColumn)) {
                        if (actualFlag) {
                            annualMandamt = annualMandamt + (obj[5] != null ? Double.valueOf(String.valueOf(obj[5])) : 0);
                            mandAmt = getFormatValue(2, String.valueOf(annualMandamt), CURRENCY);
                            annualMandrate = annualMandrate + (obj[6] != null ? Double.valueOf(String.valueOf(obj[6])) : 0);
                            mandRate = getFormatValue(2, String.valueOf(annualMandrate), PERCENTAGE);
                            annualMandrpu = annualMandrpu + (obj[9] != null ? Double.valueOf(String.valueOf(obj[9])) : 0);
                            mandRPU = getFormatValue(2, String.valueOf(annualMandrpu), CURRENCY);
                            annualSuppamt = annualSuppamt + (obj[7] != null ? Double.valueOf(String.valueOf(obj[7])) : 0);
                            suppAmt = getFormatValue(2, String.valueOf(annualSuppamt), CURRENCY);
                            annualSupprate = annualSupprate + (obj[8] != null ? Double.valueOf(String.valueOf(obj[8])) : 0);
                            suppRate = getFormatValue(2, String.valueOf(annualSupprate), PERCENTAGE);
                            annualSupprpu = annualSupprpu + (obj[10] != null ? Double.valueOf(String.valueOf(obj[10])) : 0);
                            suppRPU = getFormatValue(2, String.valueOf(annualSupprpu), CURRENCY);
                            dto.addStringProperties(annualColumn + "ActualsAmount", getFormatValue(2, String.valueOf(annualMandamt + annualSuppamt), CURRENCY));
                            dto.addStringProperties(annualColumn + "ActualsRate", getFormatValue(2, String.valueOf(annualMandrate + annualSupprate), PERCENTAGE));
                            dto.addStringProperties(annualColumn + "ActualsRPU", getFormatValue(2, String.valueOf(annualMandrpu + annualSupprpu), CURRENCY));
                        } else {
                            annualMandProjamt = annualMandProjamt + (obj[5] != null ? Double.valueOf(String.valueOf(obj[5])) : 0);
                            mandAmt = getFormatValue(2, String.valueOf(annualMandProjamt), CURRENCY);
                            annualMandProjrate = annualMandProjrate + (obj[6] != null ? Double.valueOf(String.valueOf(obj[6])) : 0);
                            mandRate = getFormatValue(2, String.valueOf(annualMandProjrate), PERCENTAGE);
                            annualMandProjrpu = annualMandProjrpu + (obj[9] != null ? Double.valueOf(String.valueOf(obj[9])) : 0);
                            mandRPU = getFormatValue(2, String.valueOf(annualMandProjrpu), CURRENCY);
                            annualSuppProjamt = annualSuppProjamt + (obj[7] != null ? Double.valueOf(String.valueOf(obj[7])) : 0);
                            suppAmt = getFormatValue(2, String.valueOf(annualSuppProjamt), CURRENCY);
                            annualSuppProjrate = annualSuppProjrate + (obj[8] != null ? Double.valueOf(String.valueOf(obj[8])) : 0);
                            suppRate = getFormatValue(2, String.valueOf(annualSuppProjrate), PERCENTAGE);
                            annualSuppProjrpu = annualSuppProjrpu + (obj[10] != null ? Double.valueOf(String.valueOf(obj[10])) : 0);
                            suppRPU = getFormatValue(2, String.valueOf(annualSuppProjrpu), CURRENCY);
                            dto.addStringProperties(annualColumn + "ProjectionsAmount", getFormatValue(2, String.valueOf(annualMandProjamt + annualSuppProjrate), CURRENCY));
                            dto.addStringProperties(annualColumn + "ProjectionsRate", getFormatValue(2, String.valueOf(annualMandProjrate + annualSuppProjrate), PERCENTAGE));
                            dto.addStringProperties(annualColumn + "ProjectionsRPU", getFormatValue(2, String.valueOf(annualMandProjrpu + annualSuppProjrpu), CURRENCY));
                        }
                        columnName = actualFlag ? "ActualsAmount" : "ProjectionsAmount";
                        mandatedDTO.addStringProperties(annualColumn + columnName, mandAmt);

                        columnName = actualFlag ? "ActualsRate" : "ProjectionsRate";
                        mandatedDTO.addStringProperties(annualColumn + columnName, mandRate);

                        columnName = actualFlag ? "ActualsRPU" : "ProjectionsRPU";
                        mandatedDTO.addStringProperties(annualColumn + columnName, mandRPU);

                        columnName = actualFlag ? "ActualsAmount" : "ProjectionsAmount";
                        suppDTO.addStringProperties(annualColumn + columnName, suppAmt);

                        columnName = actualFlag ? "ActualsRate" : "ProjectionsRate";
                        suppDTO.addStringProperties(annualColumn + columnName, suppRate);

                        columnName = actualFlag ? "ActualsRPU" : "ProjectionsRPU";
                        suppDTO.addStringProperties(annualColumn + columnName, suppRPU);
                    }
                }

            }
            dto.setManDTO(mandatedDTO);
            dto.setSuppDTO(suppDTO);
        }
        return dto;
    }

    public static String getHierarchyLevelQuery(int projectionId, int userID, int sessionID, int levelNo, String hierarchyNo, String frequency, String View, ProjectionSelectionDTO projSelDTO, String customerHierNo, String prodHierNo, String levelValue, String freqChar) {
        String hierarchy = StringUtils.EMPTY;
        String customerLevelNo = StringUtils.EMPTY;
        String prodLevelNo = StringUtils.EMPTY;
        String customerHierarNo = StringUtils.isBlank(String.valueOf(customerHierNo)) ? StringUtils.EMPTY : String.valueOf(customerHierNo);
        String prodHierarNo = StringUtils.isBlank(String.valueOf(prodHierNo)) ? StringUtils.EMPTY : String.valueOf(prodHierNo);

        int customID = projSelDTO.getCustomId();
        int customLevelNo = projSelDTO.getCustomLevelNo();
        String hierarchyIndicator = projSelDTO.getHierarchyIndicator().trim();
        if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equalsIgnoreCase(hierarchyIndicator)) {
            customerLevelNo = StringUtils.isBlank(String.valueOf(customLevelNo)) ? Constant.PERCENT : String.valueOf(customLevelNo);
            prodLevelNo = Constant.PERCENT;
        } else {
            customerLevelNo = Constant.PERCENT;
            prodLevelNo = StringUtils.isBlank(String.valueOf(customLevelNo)) ? Constant.PERCENT : String.valueOf(customLevelNo);
        }

        if (Constant.CUSTOMER_SMALL.equalsIgnoreCase(View)) {
            hierarchy = "PROJECTION_CUST_HIERARCHY";
        } else {
            hierarchy = "PROJECTION_PROD_HIERARCHY";
        }
        String customSQL = "SELECT \n"
                + "       rld.LEVEL_NO,\n"
                + "       HLD.LEVEL_NAME,\n"
                + "       rld.RELATIONSHIP_LEVEL_VALUES,\n"
                + "CCP.HIERARCHY_NO,\n"
                + "p.\"YEAR\",\n"
                + "       Sum(MAD.ACTUAL_SALES)        AS  Disc_Amt,\n"
                + "Coalesce(Sum(MAD.ACTUAL_SALES)/Nullif(Sum(m_ac.ACTUAL_SALES),0),0)*100       AS  Disc_Rate,\n"
                + "       Sum(SPMA.ACTUAL_SALES)        AS  Supp_Amt,\n"
                + "Coalesce(Sum(SPMA.ACTUAL_SALES)/Nullif(Sum(m_ac.ACTUAL_SALES),0),0)*100      AS  Supp_Rate,\n"
                + " Coalesce(Sum(MAD.ACTUAL_SALES)/Nullif(Sum(m_ac.ACTUAL_UNITS),0),0) AS Man_RPU, Coalesce(Sum(SPMA.ACTUAL_SALES)/Nullif(Sum(m_ac.ACTUAL_UNITS),0),0) As Sup_RPU,\n"
                + "       0                            As ActualProj\n";
        if (!"\"YEAR\"".equalsIgnoreCase(frequency)) {
            customSQL += "         , p." + frequency + "\n";
        }
        customSQL += "FROM   projection_details pd\n";

        if (!Constant.CUSTOM.equalsIgnoreCase(View)) {
            customSQL += "JOIN   " + hierarchy + " ph ON pd.PROJECTION_MASTER_SID = ph.PROJECTION_MASTER_SID\n"
                    + "JOIN   (SELECT DISTINCT\n"
                    + "               LCCP.RELATIONSHIP_LEVEL_SID,\n"
                    + "               LCCP.CCP_DETAILS_SID,\n"
                    + "               LCCP.HIERARCHY_NO\n"
                    + "        FROM   (SELECT CCPMAP.CCP_DETAILS_SID,\n"
                    + "                       HLD.HIERARCHY_NO,\n"
                    + "                       HLD.RELATIONSHIP_LEVEL_SID\n"
                    + "                FROM   (SELECT RLD.RELATIONSHIP_LEVEL_VALUES,\n"
                    + "                               RLD.HIERARCHY_NO,\n"
                    + "                               CCP.CCP_DETAILS_SID\n"
                    + "                        FROM   RELATIONSHIP_LEVEL_DEFINITION RLD\n"
                    + "                        JOIN   CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID = CCP.RELATIONSHIP_LEVEL_SID\n"
                    + "                        JOIN   PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID\n"
                    + "                                                    AND PD.PROJECTION_MASTER_SID = " + projectionId + "\n"
                    + "                        JOIN   PROJECTION_MASTER PM ON PD.PROJECTION_MASTER_SID = PM.PROJECTION_MASTER_SID\n"
                    + "                        WHERE  PM.PROJECTION_MASTER_SID = " + projectionId + ") CCPMAP,\n"
                    + "                       (SELECT RLD1.HIERARCHY_NO,\n"
                    + "                               RLD1.RELATIONSHIP_LEVEL_SID\n"
                    + "                        FROM   RELATIONSHIP_LEVEL_DEFINITION RLD1\n"
                    + "                        JOIN   " + hierarchy + " PCH ON PCH.RELATIONSHIP_LEVEL_SID = RLD1.RELATIONSHIP_LEVEL_SID\n"
                    + "                                                            AND PCH.PROJECTION_MASTER_SID = " + projectionId + "\n"
                    + "                        WHERE  RLD1.HIERARCHY_NO LIKE '" + hierarchyNo + "%') HLD\n"
                    + "                WHERE  CCPMAP.HIERARCHY_NO LIKE HLD.HIERARCHY_NO + '%') LCCP\n"
                    + "        WHERE  LCCP.HIERARCHY_NO IN (SELECT RLD2.HIERARCHY_NO\n"
                    + "                                     FROM   RELATIONSHIP_LEVEL_DEFINITION RLD2\n"
                    + "                                     JOIN   " + hierarchy + " PCH2 ON PCH2.RELATIONSHIP_LEVEL_SID = RLD2.RELATIONSHIP_LEVEL_SID\n"
                    + "                                                                          AND PCH2.PROJECTION_MASTER_SID = " + projectionId + "\n"
                    + "                                     WHERE  RLD2.LEVEL_NO = " + levelNo + ")) CCP ON CCP.CCP_DETAILS_SID = pd.CCP_DETAILS_SID\n";
        } else {
            customSQL += "JOIN    (SELECT distinct HLD" + hierarchyIndicator + ".RELATIONSHIP_LEVEL_SID,HLD" + hierarchyIndicator + ".HIERARCHY_NO, CCPMAPC.CCP_DETAILS_SID FROM\n"
                    + "  (SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID FROM RELATIONSHIP_LEVEL_DEFINITION RLD\n"
                    + "  JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID\n"
                    + "  JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID=" + projectionId + "\n"
                    + "  ) CCPMAPC\n"
                    + "  JOIN\n"
                    + "  (SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID FROM RELATIONSHIP_LEVEL_DEFINITION RLD\n"
                    + "  JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID\n"
                    + "  JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID=" + projectionId + "\n"
                    + "  ) CCPMAPP ON CCPMAPC.CCP_DETAILS_SID=CCPMAPP.CCP_DETAILS_SID\n"
                    + " JOIN  (SELECT RLD2.HIERARCHY_NO,RLD2.RELATIONSHIP_LEVEL_SID, CVD.LEVEL_NO FROM dbo.CUSTOM_VIEW_DETAILS CVD\n"
                    + "  JOIN dbo.CUSTOM_VIEW_MASTER CVM ON CVD.CUSTOM_VIEW_MASTER_SID='" + customID + "'  AND CVD.LEVEL_NO  like '" + customerLevelNo + "'\n"
                    + "  JOIN dbo.HIERARCHY_LEVEL_DEFINITION HLD ON CVD.HIERARCHY_ID=HLD.HIERARCHY_LEVEL_DEFINITION_SID\n"
                    + "  JOIN RELATIONSHIP_LEVEL_DEFINITION RLD2 ON HLD.HIERARCHY_LEVEL_DEFINITION_SID=RLD2.HIERARCHY_LEVEL_DEFINITION_SID\n"
                    + "  JOIN PROJECTION_CUST_HIERARCHY PCH2 ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID AND PCH2.PROJECTION_MASTER_SID=" + projectionId + "\n"
                    + "  WHERE RLD2.HIERARCHY_NO like '" + customerHierarNo + "%') HLDC ON CCPMAPC.HIERARCHY_NO like HLDC.HIERARCHY_NO+'%'\n"
                    + " JOIN    (SELECT RLD2.HIERARCHY_NO,RLD2.RELATIONSHIP_LEVEL_SID, CVD.LEVEL_NO FROM dbo.CUSTOM_VIEW_DETAILS CVD\n"
                    + "  JOIN dbo.CUSTOM_VIEW_MASTER CVM ON\n"
                    + "  CVD.CUSTOM_VIEW_MASTER_SID='" + customID + "' AND CVD.LEVEL_NO like '" + prodLevelNo + "'\n"
                    + "  JOIN dbo.HIERARCHY_LEVEL_DEFINITION HLD ON CVD.HIERARCHY_ID=HLD.HIERARCHY_LEVEL_DEFINITION_SID\n"
                    + "  JOIN RELATIONSHIP_LEVEL_DEFINITION RLD2 ON HLD.HIERARCHY_LEVEL_DEFINITION_SID=RLD2.HIERARCHY_LEVEL_DEFINITION_SID\n"
                    + "  JOIN PROJECTION_PROD_HIERARCHY PCH2 ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID AND PCH2.PROJECTION_MASTER_SID=" + projectionId + "\n"
                    + "  WHERE RLD2.HIERARCHY_NO like '" + prodHierarNo + "%' ) HLDP ON CCPMAPP.HIERARCHY_NO like HLDP.HIERARCHY_NO+'%'\n"
                    + "  )CCP ON CCP.CCP_DETAILS_SID = pd.CCP_DETAILS_SID\n";
        }

        customSQL += "JOIN   RELATIONSHIP_LEVEL_DEFINITION rld ON CCP.RELATIONSHIP_LEVEL_SID = rld.RELATIONSHIP_LEVEL_SID\n";
        if (!Constant.CUSTOM.equalsIgnoreCase(View)) {
            customSQL += "                                        AND ph.RELATIONSHIP_LEVEL_SID = rld.RELATIONSHIP_LEVEL_SID\n";
        }
        customSQL += "JOIN   ST_M_SALES_PROJECTION_MASTER m_mas ON pd.PROJECTION_DETAILS_SID = m_mas.PROJECTION_DETAILS_SID\n"
                + "                                         AND m_mas.user_id = " + userID + "\n"
                + "                                         AND m_mas.session_id = " + sessionID + "\n"
                + "JOIN   HIERARCHY_LEVEL_DEFINITION HLD ON HLD.HIERARCHY_LEVEL_DEFINITION_SID = rld.HIERARCHY_LEVEL_DEFINITION_SID\n"
                + "JOIN   ST_M_ACTUAL_SALES m_ac ON m_mas.PROJECTION_DETAILS_SID = m_ac.PROJECTION_DETAILS_SID\n"
                + "                             AND m_ac.user_id = m_mas.user_id\n"
                + "                             AND m_ac.session_id = m_mas.session_id\n"
                + "JOIN   M_ACTUAL_DISCOUNT MAD ON MAD.PROJECTION_DETAILS_SID = pd.PROJECTION_DETAILS_SID\n"
                + "AND MAD.PERIOD_SID=m_ac.PERIOD_SID\n"
                + "                            AND MAD.user_id = m_mas.user_id\n"
                + "                            AND MAD.session_id = m_mas.session_id\n"
                + "LEFT JOIN   ST_M_SUPPLEMENTAL_DISC_MASTER SPM ON SPM.PROJECTION_DETAILS_SID = pd.PROJECTION_DETAILS_SID\n"
                + "\n"
                + "                                        AND SPM.user_id = m_mas.user_id\n"
                + "                                        AND SPM.session_id = m_mas.session_id\n"
                + "LEFT JOIN   ST_M_SUPPLEMENTAL_DISC_ACTUALS SPMA ON SPMA.PROJECTION_DETAILS_SID = pd.PROJECTION_DETAILS_SID\n"
                + "										   AND SPMA.PERIOD_SID=m_ac.PERIOD_SID\n"
                + "                                          AND SPMA.user_id = m_mas.user_id\n"
                + "                                          AND SPMA.session_id = m_mas.session_id\n"
                + "JOIN   PERIOD p ON p.period_sid = m_ac.PERIOD_SID\n"
                + "WHERE  pd.PROJECTION_MASTER_SID = " + projectionId + "\n"
                + "   AND rld.LEVEL_NO = " + levelNo + "\n"
                + "   AND m_mas.USER_ID = " + userID + "\n"
                + "   AND m_mas.SESSION_ID = " + sessionID + "\n"
                + "   AND m_ac.USER_ID = " + userID + "\n"
                + "   AND m_ac.SESSION_ID = " + sessionID + "\n";
        if (!Constant.PERIOD.equalsIgnoreCase(projSelDTO.getPivotView())) {
            if (!"\"YEAR\"".equalsIgnoreCase(frequency)) {

                customSQL += "AND p.PERIOD_SID between (SELECT MIN(PERIOD_SID) FROM PERIOD WHERE '" + freqChar + "'+CONVERT(VARCHAR(2)," + frequency + ")+' '+CONVERT(VARCHAR(4),YEAR)='" + freqChar + projSelDTO.getHistoryStartPeriod() + " " + projSelDTO.getHistoryStartYear() + "')"
                        + " and (SELECT MAX(PERIOD_SID) FROM PERIOD WHERE 'M'+CONVERT(VARCHAR(2),\"MONTH\")+' '+CONVERT(VARCHAR(4),YEAR)='M" + projSelDTO.getForecastDTO().getForecastEndMonth() + " " + projSelDTO.getForecastDTO().getForecastEndYear() + "')";
            } else {
                customSQL += "AND p.PERIOD_SID>=(SELECT MIN(PERIOD_SID) FROM PERIOD WHERE YEAR='" + projSelDTO.getHistoryStartYear() + "')";
            }

        }
        if (StringUtils.isNotBlank(levelValue)) {
            customSQL += "   AND rld.RELATIONSHIP_LEVEL_VALUES='" + levelValue + "'" + "\n";
        }

        customSQL += "GROUP  BY rld.RELATIONSHIP_LEVEL_VALUES,\n";
        if (!"\"YEAR\"".equalsIgnoreCase(frequency)) {
            customSQL += "          p." + frequency + ",\n";
        }
        customSQL += "          rld.RELATIONSHIP_LEVEL_SID,\n"
                + "          rld.LEVEL_NO,\n"
                + "CCP.HIERARCHY_NO,\n"
                + "          p.\"YEAR\",\n"
                + "          HLD.LEVEL_NAME\n"
                + "Union \n"
                + "\n"
                + "SELECT\n"
                + "       rld.LEVEL_NO,\n"
                + "       HLD.LEVEL_NAME,\n"
                + "       rld.RELATIONSHIP_LEVEL_VALUES,\n"
                + "CCP.HIERARCHY_NO,\n"
                + "p.\"YEAR\",\n"
                + "       Sum(MAD.PROJECTION_SALES)        AS  Disc_Amt,\n"
                + "Coalesce(Sum(MAD.PROJECTION_SALES)/Nullif(Sum(m_ac.PROJECTION_SALES),0),0)*100 AS  Disc_Rate,\n"
                + "       Sum(SPMA.PROJECTION_SALES)        AS  Supp_Amt,\n"
                + "Coalesce(Sum(SPMA.PROJECTION_SALES)/Nullif(Sum(m_ac.PROJECTION_SALES),0),0)*100 AS  Supp_Rate,\n"
                + " Coalesce(Sum(MAD.PROJECTION_SALES)/Nullif(Sum(m_ac.PROJECTION_UNITS),0),0) AS Man_RPU, Coalesce(Sum(SPMA.PROJECTION_SALES)/Nullif(Sum(m_ac.PROJECTION_UNITS),0),0) As Sup_RPU, \n"
                + "       1                            As ActualProj\n";
        if (!"\"YEAR\"".equalsIgnoreCase(frequency)) {
            customSQL += "         , p." + frequency + "\n";
        }
        customSQL += "FROM   projection_details pd\n";

        if (!Constant.CUSTOM.equalsIgnoreCase(View)) {
            customSQL += "JOIN   " + hierarchy + " ph ON pd.PROJECTION_MASTER_SID = ph.PROJECTION_MASTER_SID\n"
                    + "JOIN   (SELECT DISTINCT\n"
                    + "               LCCP.RELATIONSHIP_LEVEL_SID,\n"
                    + "               LCCP.CCP_DETAILS_SID,\n"
                    + "               LCCP.HIERARCHY_NO\n"
                    + "        FROM   (SELECT CCPMAP.CCP_DETAILS_SID,\n"
                    + "                       HLD.HIERARCHY_NO,\n"
                    + "                       HLD.RELATIONSHIP_LEVEL_SID\n"
                    + "                FROM   (SELECT RLD.RELATIONSHIP_LEVEL_VALUES,\n"
                    + "                               RLD.HIERARCHY_NO,\n"
                    + "                               CCP.CCP_DETAILS_SID\n"
                    + "                        FROM   RELATIONSHIP_LEVEL_DEFINITION RLD\n"
                    + "                        JOIN   CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID = CCP.RELATIONSHIP_LEVEL_SID\n"
                    + "                        JOIN   PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID\n"
                    + "                                                    AND PD.PROJECTION_MASTER_SID = " + projectionId + "\n"
                    + "                        JOIN   PROJECTION_MASTER PM ON PD.PROJECTION_MASTER_SID = PM.PROJECTION_MASTER_SID\n"
                    + "                        WHERE  PM.PROJECTION_MASTER_SID = " + projectionId + ") CCPMAP,\n"
                    + "                       (SELECT RLD1.HIERARCHY_NO,\n"
                    + "                               RLD1.RELATIONSHIP_LEVEL_SID\n"
                    + "                        FROM   RELATIONSHIP_LEVEL_DEFINITION RLD1\n"
                    + "                        JOIN   " + hierarchy + " PCH ON PCH.RELATIONSHIP_LEVEL_SID = RLD1.RELATIONSHIP_LEVEL_SID\n"
                    + "                                                            AND PCH.PROJECTION_MASTER_SID = " + projectionId + "\n"
                    + "                        WHERE  RLD1.HIERARCHY_NO LIKE '" + hierarchyNo + "%') HLD\n"
                    + "                WHERE  CCPMAP.HIERARCHY_NO LIKE HLD.HIERARCHY_NO + '%') LCCP\n"
                    + "        WHERE  LCCP.HIERARCHY_NO IN (SELECT RLD2.HIERARCHY_NO\n"
                    + "                                     FROM   RELATIONSHIP_LEVEL_DEFINITION RLD2\n"
                    + "                                     JOIN   " + hierarchy + " PCH2 ON PCH2.RELATIONSHIP_LEVEL_SID = RLD2.RELATIONSHIP_LEVEL_SID\n"
                    + "                                                                          AND PCH2.PROJECTION_MASTER_SID = " + projectionId + "\n"
                    + "                                     WHERE  RLD2.LEVEL_NO = " + levelNo + ")) CCP ON CCP.CCP_DETAILS_SID = pd.CCP_DETAILS_SID\n";
        } else {
            customSQL += "JOIN    (SELECT distinct HLD" + hierarchyIndicator + ".RELATIONSHIP_LEVEL_SID,HLD" + hierarchyIndicator + ".HIERARCHY_NO, CCPMAPC.CCP_DETAILS_SID FROM\n"
                    + "  (SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID FROM RELATIONSHIP_LEVEL_DEFINITION RLD\n"
                    + "  JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID\n"
                    + "  JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID=" + projectionId + "\n"
                    + "  ) CCPMAPC\n"
                    + "  JOIN\n"
                    + "  (SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID FROM RELATIONSHIP_LEVEL_DEFINITION RLD\n"
                    + "  JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID\n"
                    + "  JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID=" + projectionId + "\n"
                    + "  ) CCPMAPP ON CCPMAPC.CCP_DETAILS_SID=CCPMAPP.CCP_DETAILS_SID\n"
                    + " JOIN  (SELECT RLD2.HIERARCHY_NO,RLD2.RELATIONSHIP_LEVEL_SID, CVD.LEVEL_NO FROM dbo.CUSTOM_VIEW_DETAILS CVD\n"
                    + "  JOIN dbo.CUSTOM_VIEW_MASTER CVM ON CVD.CUSTOM_VIEW_MASTER_SID='" + customID + "'  AND CVD.LEVEL_NO  like '" + customerLevelNo + "'\n"
                    + "  JOIN dbo.HIERARCHY_LEVEL_DEFINITION HLD ON CVD.HIERARCHY_ID=HLD.HIERARCHY_LEVEL_DEFINITION_SID\n"
                    + "  JOIN RELATIONSHIP_LEVEL_DEFINITION RLD2 ON HLD.HIERARCHY_LEVEL_DEFINITION_SID=RLD2.HIERARCHY_LEVEL_DEFINITION_SID\n"
                    + "  JOIN PROJECTION_CUST_HIERARCHY PCH2 ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID AND PCH2.PROJECTION_MASTER_SID=" + projectionId + "\n"
                    + "  WHERE RLD2.HIERARCHY_NO like '" + customerHierarNo + "%') HLDC ON CCPMAPC.HIERARCHY_NO like HLDC.HIERARCHY_NO+'%'\n"
                    + " JOIN    (SELECT RLD2.HIERARCHY_NO,RLD2.RELATIONSHIP_LEVEL_SID, CVD.LEVEL_NO FROM dbo.CUSTOM_VIEW_DETAILS CVD\n"
                    + "  JOIN dbo.CUSTOM_VIEW_MASTER CVM ON\n"
                    + "  CVD.CUSTOM_VIEW_MASTER_SID='" + customID + "' AND CVD.LEVEL_NO like '" + prodLevelNo + "'\n"
                    + "  JOIN dbo.HIERARCHY_LEVEL_DEFINITION HLD ON CVD.HIERARCHY_ID=HLD.HIERARCHY_LEVEL_DEFINITION_SID\n"
                    + "  JOIN RELATIONSHIP_LEVEL_DEFINITION RLD2 ON HLD.HIERARCHY_LEVEL_DEFINITION_SID=RLD2.HIERARCHY_LEVEL_DEFINITION_SID\n"
                    + "  JOIN PROJECTION_PROD_HIERARCHY PCH2 ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID AND PCH2.PROJECTION_MASTER_SID=" + projectionId + "\n"
                    + "  WHERE RLD2.HIERARCHY_NO like '" + prodHierarNo + "%' ) HLDP ON CCPMAPP.HIERARCHY_NO like HLDP.HIERARCHY_NO+'%'\n"
                    + "  )CCP ON CCP.CCP_DETAILS_SID = pd.CCP_DETAILS_SID\n";
        }

        customSQL += "JOIN   RELATIONSHIP_LEVEL_DEFINITION rld ON CCP.RELATIONSHIP_LEVEL_SID = rld.RELATIONSHIP_LEVEL_SID\n";
        if (!Constant.CUSTOM.equalsIgnoreCase(View)) {
            customSQL += "                                        AND ph.RELATIONSHIP_LEVEL_SID = rld.RELATIONSHIP_LEVEL_SID\n";
        }
        customSQL += "JOIN   ST_M_SALES_PROJECTION_MASTER m_mas ON pd.PROJECTION_DETAILS_SID = m_mas.PROJECTION_DETAILS_SID\n"
                + "                                         AND m_mas.user_id = " + userID + "\n"
                + "                                         AND m_mas.session_id = " + sessionID + "\n"
                + "JOIN   HIERARCHY_LEVEL_DEFINITION HLD ON HLD.HIERARCHY_LEVEL_DEFINITION_SID = rld.HIERARCHY_LEVEL_DEFINITION_SID\n"
                + "JOIN   ST_M_SALES_PROJECTION m_ac ON m_mas.PROJECTION_DETAILS_SID = m_ac.PROJECTION_DETAILS_SID\n"
                + "                             AND m_ac.user_id = m_mas.user_id\n"
                + "                             AND m_ac.session_id = m_mas.session_id\n"
                + "JOIN   M_DISCOUNT_PROJECTION MAD ON MAD.PROJECTION_DETAILS_SID = pd.PROJECTION_DETAILS_SID\n"
                + "AND MAD.PERIOD_SID=m_ac.PERIOD_SID\n"
                + "                            AND MAD.user_id = m_mas.user_id\n"
                + "                            AND MAD.session_id = m_mas.session_id\n"
                + "LEFT JOIN   ST_M_SUPPLEMENTAL_DISC_MASTER SPM ON SPM.PROJECTION_DETAILS_SID = pd.PROJECTION_DETAILS_SID\n"
                + "\n"
                + "                                        AND SPM.user_id = m_mas.user_id\n"
                + "                                        AND SPM.session_id = m_mas.session_id\n"
                + "LEFT JOIN   ST_M_SUPPLEMENTAL_DISC_PROJ SPMA ON SPMA.PROJECTION_DETAILS_SID = pd.PROJECTION_DETAILS_SID\n"
                + "                                                                        AND SPMA.PERIOD_SID=m_ac.PERIOD_SID\n"
                + "                                          AND SPMA.user_id = m_mas.user_id\n"
                + "                                          AND SPMA.session_id = m_mas.session_id\n"
                + "JOIN   PERIOD p ON p.period_sid = m_ac.PERIOD_SID\n"
                + "WHERE  pd.PROJECTION_MASTER_SID = " + projectionId + "\n"
                + "   AND rld.LEVEL_NO = " + levelNo + "\n"
                + "   AND m_mas.USER_ID = " + userID + "\n"
                + "   AND m_mas.SESSION_ID = " + sessionID + "\n"
                + "   AND m_ac.USER_ID = " + userID + "\n"
                + "   AND m_ac.SESSION_ID = " + sessionID + "\n";
        if (!Constant.PERIOD.equalsIgnoreCase(projSelDTO.getPivotView())) {
            if (!"\"YEAR\"".equalsIgnoreCase(frequency)) {
                customSQL += "AND p.PERIOD_SID between (SELECT MIN(PERIOD_SID) FROM PERIOD WHERE '" + freqChar + "'+CONVERT(VARCHAR(2)," + frequency + ")+' '+CONVERT(VARCHAR(4),YEAR)='" + freqChar + projSelDTO.getHistoryStartPeriod() + " " + projSelDTO.getHistoryStartYear() + "')"
                        + " and (SELECT MAX(PERIOD_SID) FROM PERIOD WHERE 'M'+CONVERT(VARCHAR(2),\"MONTH\")+' '+CONVERT(VARCHAR(4),YEAR)='M" + projSelDTO.getForecastDTO().getForecastEndMonth() + " " + projSelDTO.getForecastDTO().getForecastEndYear() + "')";
            } else {
                customSQL += "AND p.PERIOD_SID>=(SELECT MIN(PERIOD_SID) FROM PERIOD WHERE YEAR='" + projSelDTO.getHistoryStartYear() + "')";
            }
        }

        if (StringUtils.isNotBlank(levelValue)) {
            customSQL += "   AND rld.RELATIONSHIP_LEVEL_VALUES='" + levelValue + "'" + "\n";
        }
        customSQL += "GROUP  BY rld.RELATIONSHIP_LEVEL_VALUES,\n";
        if (!"\"YEAR\"".equalsIgnoreCase(frequency)) {
            customSQL += "          p." + frequency + ",\n";
        }
        customSQL += "          rld.RELATIONSHIP_LEVEL_SID,\n"
                + "          rld.LEVEL_NO,\n"
                + "CCP.HIERARCHY_NO,\n"
                + "          p.\"YEAR\",\n"
                + "          HLD.LEVEL_NAME\n"
                + "Order By p.\"YEAR\"\n";
        if (!"\"YEAR\"".equalsIgnoreCase(frequency)) {
            customSQL += "          ,p." + frequency + "\n";
        }

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
        Map<Integer, String> freq = new HashMap<Integer, String>();
        freq.put(1, "\"YEAR\"");
        freq.put(2, "SEMI_ANNUAL");
        freq.put(4, "QUARTER");
        freq.put(12, "\"MONTH\"");
        String frequency = freq.get(projSelDTO.getFrequencyDivision());
        String freqChar = !"\"YEAR\"".equalsIgnoreCase(frequency) ? "SEMI_ANNUAL".equalsIgnoreCase(frequency) ? Constant.S : "QUARTER".equalsIgnoreCase(frequency) ? Constant.Q : CommonUtils.BUSINESS_PROCESS_INDICATOR_MANDATED : "";
        String query = getHierarchyLevelQuery(projSelDTO.getProjectionId(), projSelDTO.getUserId(), projSelDTO.getSessionId(), dto.getLevelNo(), projSelDTO.getHierarchyNo(), frequency, projSelDTO.getView(), projSelDTO, dto.getCustomerHierarchyNo(), dto.getProductHierarchyNo(), dto.getLevelValue(), freqChar);

        List<Object> list = (List<Object>) CommonLogic.executeSelectQuery(query, null, null);
        if (projSelDTO.getProjectionOrder().equalsIgnoreCase(Constant.DESCENDING)) {
            Collections.reverse(list);
        }
        List<DiscountProjectionResultsDTO> childList = new ArrayList<DiscountProjectionResultsDTO>();
        try {
            if (list != null && !list.isEmpty()) {
                int frequencyDivision = projSelDTO.getFrequencyDivision();
                String lastPeriod = StringUtils.EMPTY;
                boolean actualFlag = false;
                DiscountProjectionResultsDTO periodDTO = null;
                List<Object> pcList = addProgramCodeDiscounts(projSelDTO);
                for (int i = 0; i < list.size(); i++) {

                    String columnName = StringUtils.EMPTY;
                    final Object[] obj = (Object[]) list.get(i);
                    actualFlag = Integer.valueOf(String.valueOf(obj[11])) == 0;
                    String mandSupp = projSelDTO.getMandatedOrSupp();

                    int year = Integer.valueOf(String.valueOf(obj[4]));
                    int period = projSelDTO.getFrequencyDivision() == 1 ? 0 : Integer.valueOf(String.valueOf(obj[12]));
                    List<String> common = HeaderUtils.getCommonColumnHeader(frequencyDivision, year, period, false);
                    String commonColumn = common.get(0);
                    String commonHeader = common.get(1);
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
                    if ("mandated".equalsIgnoreCase(mandSupp)) {
                        commonColumn = "MandatedDiscount";
                        String mandAmt = StringUtils.EMPTY + obj[5];
                        mandAmt = getFormatValue(2, mandAmt, CURRENCY);
                        columnName = actualFlag ? "ActualsAmount" : "ProjectionsAmount";
                        periodDTO.addStringProperties(commonColumn + columnName, mandAmt);

                        String mandRate = StringUtils.EMPTY + obj[6];
                        mandRate = getFormatValue(2, mandRate, PERCENTAGE);
                        columnName = actualFlag ? "ActualsRate" : "ProjectionsRate";
                        periodDTO.addStringProperties(commonColumn + columnName, mandRate);

                        String mandRPU = StringUtils.EMPTY + obj[9];
                        mandRPU = getFormatValue(2, mandRPU, CURRENCY);
                        columnName = actualFlag ? "ActualsRPU" : "ProjectionsRPU";
                        periodDTO.addStringProperties(commonColumn + columnName, mandRPU);

                        commonColumn = Constant.TOTALDISCOUNT;
                        columnName = actualFlag ? "ActualsAmount" : "ProjectionsAmount";
                        periodDTO.addStringProperties(commonColumn + columnName, mandAmt);
                        columnName = actualFlag ? "ActualsRate" : "ProjectionsRate";
                        periodDTO.addStringProperties(commonColumn + columnName, mandRate);
                        columnName = actualFlag ? "ActualsRPU" : "ProjectionsRPU";
                        periodDTO.addStringProperties(commonColumn + columnName, mandRPU);
                    }
                    if ("supplemental".equalsIgnoreCase(mandSupp)) {
                        commonColumn = "SupplementalDiscount";
                        String suppAmt = StringUtils.EMPTY + obj[7];
                        suppAmt = getFormatValue(2, suppAmt, CURRENCY);
                        columnName = actualFlag ? "ActualsAmount" : "ProjectionsAmount";
                        periodDTO.addStringProperties(commonColumn + columnName, suppAmt);

                        String suppRate = StringUtils.EMPTY + obj[8];
                        suppRate = getFormatValue(2, suppRate, PERCENTAGE);
                        columnName = actualFlag ? "ActualsRate" : "ProjectionsRate";
                        periodDTO.addStringProperties(commonColumn + columnName, suppRate);

                        String suppRPU = StringUtils.EMPTY + obj[10];
                        suppRPU = getFormatValue(2, suppRPU, CURRENCY);
                        columnName = actualFlag ? "ActualsRPU" : "ProjectionsRPU";
                        periodDTO.addStringProperties(commonColumn + columnName, suppRPU);

                        commonColumn = Constant.TOTALDISCOUNT;
                        columnName = actualFlag ? "ActualsAmount" : "ProjectionsAmount";
                        periodDTO.addStringProperties(commonColumn + columnName, suppAmt);
                        columnName = actualFlag ? "ActualsRate" : "ProjectionsRate";
                        periodDTO.addStringProperties(commonColumn + columnName, suppRate);
                        columnName = actualFlag ? "ActualsRPU" : "ProjectionsRPU";
                        periodDTO.addStringProperties(commonColumn + columnName, suppRPU);
                    }
                    if (Constant.BOTH_SMALL.equalsIgnoreCase(mandSupp)) {
                        commonColumn = "MandatedDiscount";
                        String mandAmt = StringUtils.EMPTY + obj[5];
                        mandAmt = getFormatValue(2, mandAmt, CURRENCY);
                        columnName = actualFlag ? "ActualsAmount" : "ProjectionsAmount";
                        periodDTO.addStringProperties(commonColumn + columnName, mandAmt);
                        String mandRate = StringUtils.EMPTY + obj[6];
                        mandRate = getFormatValue(2, mandRate, PERCENTAGE);
                        columnName = actualFlag ? "ActualsRate" : "ProjectionsRate";
                        periodDTO.addStringProperties(commonColumn + columnName, mandRate);
                        String mandRPU = StringUtils.EMPTY + obj[9];
                        mandRPU = getFormatValue(2, mandRPU, CURRENCY);
                        columnName = actualFlag ? "ActualsRPU" : "ProjectionsRPU";
                        periodDTO.addStringProperties(commonColumn + columnName, mandRPU);

                        commonColumn = "SupplementalDiscount";
                        String suppAmt = StringUtils.EMPTY + obj[7];
                        suppAmt = getFormatValue(2, suppAmt, CURRENCY);
                        columnName = actualFlag ? "ActualsAmount" : "ProjectionsAmount";
                        periodDTO.addStringProperties(commonColumn + columnName, suppAmt);
                        String nullValue = "...";
                        if (nullValue.equals(suppAmt)) {
                            suppAmt = StringUtils.EMPTY;
                        }

                        String suppRate = StringUtils.EMPTY + obj[8];
                        suppRate = getFormatValue(2, suppRate, PERCENTAGE);
                        columnName = actualFlag ? "ActualsRate" : "ProjectionsRate";
                        periodDTO.addStringProperties(commonColumn + columnName, suppRate);
                        if (nullValue.equals(suppRate)) {
                            suppRate = StringUtils.EMPTY;
                        }

                        String suppRPU = StringUtils.EMPTY + obj[10];
                        suppRPU = getFormatValue(2, suppRPU, CURRENCY);
                        columnName = actualFlag ? "ActualsRPU" : "ProjectionsRPU";
                        periodDTO.addStringProperties(commonColumn + columnName, suppRPU);

                        commonColumn = Constant.TOTALDISCOUNT;
                        columnName = actualFlag ? "ActualsAmount" : "ProjectionsAmount";
                        Double amt = (StringUtils.isNotBlank(String.valueOf(obj[5])) ? Double.valueOf(String.valueOf(obj[5])) : 0.00) + (!Constant.NULL.equalsIgnoreCase(String.valueOf(obj[7])) && StringUtils.isNotBlank(String.valueOf(obj[7])) ? Double.valueOf(String.valueOf(obj[7])) : 0.00);
                        periodDTO.addStringProperties(commonColumn + columnName, getFormatValue(2, String.valueOf(amt), CURRENCY));
                        columnName = actualFlag ? "ActualsRate" : "ProjectionsRate";
                        Double rate = (StringUtils.isNotBlank(String.valueOf(obj[6])) ? Double.valueOf(String.valueOf(obj[6])) : 0.00) + (!Constant.NULL.equalsIgnoreCase(String.valueOf(obj[8])) && StringUtils.isNotBlank(String.valueOf(obj[8])) ? Double.valueOf(String.valueOf(obj[8])) : 0.00);
                        periodDTO.addStringProperties(commonColumn + columnName, getFormatValue(2, String.valueOf(rate), PERCENTAGE));
                        columnName = actualFlag ? "ActualsRPU" : "ProjectionsRPU";
                        Double rpu = (StringUtils.isNotBlank(String.valueOf(obj[9])) ? Double.valueOf(String.valueOf(obj[9])) : 0.00) + (!Constant.NULL.equalsIgnoreCase(String.valueOf(obj[10])) && StringUtils.isNotBlank(String.valueOf(obj[10])) ? Double.valueOf(String.valueOf(obj[10])) : 0.00);
                        periodDTO.addStringProperties(commonColumn + columnName, getFormatValue(2, String.valueOf(rpu), CURRENCY));
                    }
                    if (i == list.size() - 1) {
                        customizePCDisc(pcList, periodDTO, projSelDTO, lastPeriod, freq.get(projSelDTO.getFrequencyDivision()));
                        childList.add(periodDTO);
                    }
                }

            }
        } catch (Exception e) {
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
        List<Object> list = new ArrayList<Object>();
        Map<Integer, String> freq = new HashMap<Integer, String>();
        freq.put(1, "\"YEAR\"");
        freq.put(2, "SEMI_ANNUAL");
        freq.put(4, "QUARTER");
        freq.put(12, "\"MONTH\"");
        String frequency = freq.get(projSelDTO.getFrequencyDivision());
        String freqChar = !"\"YEAR\"".equalsIgnoreCase(frequency) ? "SEMI_ANNUAL".equalsIgnoreCase(frequency) ? Constant.S : "QUARTER".equalsIgnoreCase(frequency) ? Constant.Q : CommonUtils.BUSINESS_PROCESS_INDICATOR_MANDATED : "";
        list = (List<Object>) CommonLogic.executeSelectQuery(getProgramCodeQuery(projSelDTO.getProjectionId(), projSelDTO.getUserId(), projSelDTO.getSessionId(), projSelDTO.getLevelNo(), projSelDTO.getHierarchyNo(), frequency, projSelDTO.getView(), projSelDTO, freqChar), null, null);
        return list;
    }

    private void customizePCDisc(List<Object> list, DiscountProjectionResultsDTO periodDTO, ProjectionSelectionDTO projSelDTO, String commonHeader, String freq) {
        if (list != null && !list.isEmpty()) {
            int frequencyDivision = projSelDTO.getFrequencyDivision();
//            DiscountProjectionResultsDTO periodDTO = new DiscountProjectionResultsDTO();
//            periodDTO.setCustomLevelNo(dto.getCustomLevelNo());
//            periodDTO.setCustomerHierarchyNo(dto.getCustomerHierarchyNo());
//            periodDTO.setProductHierarchyNo(dto.getProductHierarchyNo());
            String lastPeriod = StringUtils.EMPTY;
            boolean actualFlag = false;
            for (int i = 0; i < list.size(); i++) {

                String columnName = StringUtils.EMPTY;
                final Object[] obj = (Object[]) list.get(i);
                actualFlag = Integer.valueOf(String.valueOf(obj[11])) == 0;

                int year = Integer.valueOf(String.valueOf(obj[4]));
                int period = projSelDTO.getFrequencyDivision() == 1 ? 0 : Integer.valueOf(String.valueOf(obj[12]));
                List<String> common = HeaderUtils.getCommonColumnHeader(frequencyDivision, year, period, false);
                String commonColumn = common.get(0);
                lastPeriod = "\"YEAR\"".equalsIgnoreCase(freq) ? String.valueOf(obj[4]) : String.valueOf(obj[13]);
                if (!lastPeriod.isEmpty() && commonHeader.equalsIgnoreCase(lastPeriod)) {
                    commonColumn = obj[2] + "m";
                    String mandAmt = StringUtils.EMPTY + obj[5];
                    mandAmt = getFormatValue(2, mandAmt, CURRENCY);
                    columnName = actualFlag ? "ActualsAmount" : "ProjectionsAmount";
                    periodDTO.addStringProperties(commonColumn + columnName, mandAmt);
                    String mandRate = StringUtils.EMPTY + obj[6];
                    mandRate = getFormatValue(2, mandRate, PERCENTAGE);
                    columnName = actualFlag ? "ActualsRate" : "ProjectionsRate";
                    periodDTO.addStringProperties(commonColumn + columnName, mandRate);
                    String mandRPU = StringUtils.EMPTY + obj[9];
                    mandRPU = getFormatValue(2, mandRPU, CURRENCY);
                    columnName = actualFlag ? "ActualsRPU" : "ProjectionsRPU";
                    periodDTO.addStringProperties(commonColumn + columnName, mandRPU);

                    commonColumn = obj[2] + Constant.S_SMALL;
                    String suppAmt = StringUtils.EMPTY + obj[7];
                    suppAmt = getFormatValue(2, suppAmt, CURRENCY);
                    columnName = actualFlag ? "ActualsAmount" : "ProjectionsAmount";
                    periodDTO.addStringProperties(commonColumn + columnName, suppAmt);

                    String suppRate = StringUtils.EMPTY + obj[8];
                    suppRate = getFormatValue(2, suppRate, PERCENTAGE);
                    columnName = actualFlag ? "ActualsRate" : "ProjectionsRate";
                    periodDTO.addStringProperties(commonColumn + columnName, suppRate);
                    String suppRPU = StringUtils.EMPTY + obj[10];
                    suppRPU = getFormatValue(2, suppRPU, CURRENCY);
                    columnName = actualFlag ? "ActualsRPU" : "ProjectionsRPU";
                    periodDTO.addStringProperties(commonColumn + columnName, suppRPU);
                }
            }
        }
    }

    public List<DiscountProjectionResultsDTO> getConfiguredResultsTotal(int start, int offset, ProjectionSelectionDTO projSelDTO) throws Exception {
        List<DiscountProjectionResultsDTO> totalDTO = new ArrayList<DiscountProjectionResultsDTO>();
        List<Integer> yearList = new ArrayList<Integer>();
        yearList.add(projSelDTO.getForecastDTO().getHistoryStartYear());
        yearList.add(projSelDTO.getForecastDTO().getHistoryStartMonth());
        yearList.add(projSelDTO.getForecastDTO().getHistoryEndYear());
        yearList.add(projSelDTO.getForecastDTO().getHistoryEndMonth());

        yearList.add(projSelDTO.getForecastDTO().getForecastStartYear());
        yearList.add(projSelDTO.getForecastDTO().getForecastStartMonth());
        yearList.add(projSelDTO.getForecastDTO().getForecastEndYear());
        yearList.add(projSelDTO.getForecastDTO().getForecastEndMonth());
        int neededRecord = offset;
        int started = start;
        int mayBeAdded = 0;
        Object[] orderedArgs = {projSelDTO.getProjectionId(), projSelDTO.getUserId(), projSelDTO.getSessionId(), "ASD", projSelDTO.getFrequency(), null, null,yearList.get(1)!=null ? yearList.get(1)+1 : 1, yearList.get(0)};
        if (projSelDTO.getPivotView().contains(PERIOD.getConstant())) {
            List<DiscountProjectionResultsDTO> resultList = getProjectionTotal(orderedArgs, projSelDTO);

            if (!resultList.isEmpty()) {
                totalDTO.add(resultList.get(0));
                if (CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED.equalsIgnoreCase(projSelDTO.getMandatedOrSupp())) {
                    totalDTO.add(resultList.get(1));
                } else if (Constant.SUPPLEMENTAL.equalsIgnoreCase(projSelDTO.getMandatedOrSupp())) {
                    totalDTO.add(resultList.get(2));
                    DiscountProjectionResultsDTO dto = resultList.get(2);
                } else {
                    totalDTO.add(resultList.get(1));
                    totalDTO.add(resultList.get(2));
                }
            }
            return totalDTO;
        } else {

            List<DiscountProjectionResultsDTO> projectionDtoList = new ArrayList<DiscountProjectionResultsDTO>();

            projectionDtoList = getProjectionPivotTotal(orderedArgs, projSelDTO);

            return projectionDtoList;
        }

    }

    private List<Object> getProgramCodeCount(int projectionId, String hierarchyNo) {
        List<Object> list = new ArrayList<Object>();
        String customSQL = "SELECT DISTINCT CM.CONTRACT_NO FROM dbo.CONTRACT_MASTER CM \n"
                + "JOIN dbo.CCP_DETAILS CCP ON CCP.CONTRACT_MASTER_SID = CM.CONTRACT_MASTER_SID\n"
                + "JOIN dbo.PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID  = CCP.CCP_DETAILS_SID \n"
                + "JOIN dbo.CCP_MAP ccpm on ccpm.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID\n"
                + "JOIN dbo.RELATIONSHIP_LEVEL_DEFINITION RLD ON RLD.RELATIONSHIP_LEVEL_SID = ccpm.RELATIONSHIP_LEVEL_SID AND RLD.HIERARCHY_NO like '" + hierarchyNo + "%'\n"
                + "AND PD.PROJECTION_MASTER_SID = " + projectionId;
        list = (List<Object>) CommonLogic.executeSelectQuery(customSQL, null, null);
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
        List<Object[]> list = new ArrayList<Object[]>();
        Map<Object, Object> map = new HashMap<Object, Object>();
        DynamicQuery query = DynamicQueryFactoryUtil.forClass(MProjectionSelection.class);
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
                    Object[] obj = (Object[]) list.get(i);
                    map.put(obj[0], obj[1]);
                }
            }
            return map;
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        return null;
    }

    public void saveDiscountProjection(SessionDTO sessionDTO) throws PortalException, Exception {

        LOGGER.info("Session--->" + sessionDTO.getUserId() + " || " + sessionDTO.getSessionId() + " || " + sessionDTO.getProjectionId());
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
}
