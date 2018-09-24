/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.ppaprojection.logic;

import com.stpl.app.gtnforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.dto.SaveDTO;
import com.stpl.app.gtnforecasting.logic.CommonLogic;
import com.stpl.app.gtnforecasting.logic.RunnableJob;
import com.stpl.app.gtnforecasting.ppaprojection.dto.PPAProjectionDTO;
import com.stpl.app.gtnforecasting.queryUtils.PPAQuerys;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import static com.stpl.app.gtnforecasting.utils.Constant.DASH;
import com.stpl.app.gtnforecasting.utils.HeaderUtils;
import com.stpl.app.gtnforecasting.utils.HelperListUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.constants.BooleanConstant;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Jayaram
 */
public class PPAProjectionLogic {

    private static final Logger LOGGER = LoggerFactory.getLogger(PPAProjectionLogic.class);
    
    private static Thread procedureThread;
    private static RunnableJob runnableJob;
    private final CommonLogic commonLogic=new CommonLogic();
    public  final SimpleDateFormat formatter = new SimpleDateFormat(Constant.DATE_FORMAT);

    public List getInputForMassUpdate(int startQuater, int endQuater, int startYear, int endYear, Object columnValue, String columnName, ProjectionSelectionDTO selection) {
        List input = new ArrayList();
          String ccpQuery = new CommonLogic().insertAvailableHierarchyNo(selection);
        input.add(ccpQuery);
        List startAndEndDate = getStartAndEndDate(startQuater, endQuater, startYear, endYear);
        input.add(columnName);
        if (columnValue instanceof HelperDTO) {
            input.add(((HelperDTO) columnValue).getId());
        } else if (columnValue instanceof Date) {
            input.add("CONVERT(DATE, '" + CommonUtils.getDateTime(Constant.DATE_FORMAT_1, (Date) columnValue) + "')");
        } else {
            if (columnName.equals("NEP") || columnName.equals("PRICE_TOLERANCE") || columnName.equals("MAX_INCREMENTAL_CHANGE")) {
                input.add(Double.valueOf(String.valueOf(columnValue)));
                input.add(selection.getProjectionId());
            } else {
                input.add("'" + columnValue + "'");
            }

        }

        input.add(selection.getRelationshipBuilderSid());
        if (!selection.getGroupFilter().equals(Constant.ALL_GROUP)) {
            input.add(selection.getGroupFilter().replace(Constant.PPA, StringUtils.EMPTY));
        } else {
            input.add(Constant.PERCENT);
        }
        input.add(formatter.format(startAndEndDate.get(0)));
        input.add(formatter.format(startAndEndDate.get(1)));
        return input;

    }

    public List getInputForMassUpdateGroup(Object columnValue, String columnName, ProjectionSelectionDTO selection) {
        List input = new ArrayList();
          String ccpQuery = new CommonLogic().insertAvailableHierarchyNo(selection);
        input.add(ccpQuery);
        input.add(columnName);
        if (columnValue instanceof HelperDTO) {
            input.add(((HelperDTO) columnValue).getId());
        } else if (columnValue instanceof Date) {
            input.add("CONVERT(DATE, '" + CommonUtils.getDateTime(Constant.DATE_FORMAT_1, (Date) columnValue) + "')");
        } else {
            input.add("'" + columnValue + "'");
        }
        input.add(selection.getRelationshipBuilderSid());
        if (!selection.getGroupFilter().equals(Constant.ALL_GROUP)) {
            input.add(selection.getGroupFilter().replace(Constant.PPA, StringUtils.EMPTY));
        } else {
            input.add(Constant.PERCENT);
        }
        return input;
    }

    private static List getInputForSaveGroup(int projectionId, Object priceCap, String hirarechyNo, String group, ProjectionSelectionDTO selection) {
        List input = new ArrayList();
        String ccpQuery = new CommonLogic().insertAvailableHierarchyNo(selection);
        input.add(ccpQuery);
        input.add(priceCap);
        input.add(selection.getRelationshipBuilderSid());
        input.add(projectionId);
        input.add(hirarechyNo);
        input.add(group);

        return input;
    }

    /**
     * Load result grid while Tab change.
     *
     * @param selection
     * @param ridhtdto
     * @param start
     * @param offset
     * @return
     * @throws com.stpl.portal.kernel.exception.PortalException
     */
    public Object getPPAProjectionResults(ProjectionSelectionDTO selection, CustomTableHeaderDTO ridhtdto, int start, int offset) {
        List list = null;
        List<Object> finalList = (List) configureLevels( start, offset, selection, ridhtdto);
        List<PPAProjectionDTO> ppaList = (List<PPAProjectionDTO>) finalList.get(0);
        list = getPPAProjectionList(selection, selection.getHierarchyNo());
        list = getCustomizedPPA(list, selection, ppaList);
        return list;
    }

    private List getPPAProjectionList(ProjectionSelectionDTO selection, String hirarechyNo) {
        PPAQuerys.setTableName(selection.getSessionDTO().getCurrentTableNames());
        String ccpQuery = new CommonLogic().insertAvailableHierarchyNo(selection);
        List<Object> input = new ArrayList<>();
        String queryName = "PPAGenerate";
        input.add(ccpQuery);
        input.add(selection.getRelationshipBuilderSid());
        input.add(selection.getProjectionId());
        input.add(hirarechyNo == null ? StringUtils.EMPTY : hirarechyNo);
        input.add(selection.getSessionId());
        input.add(selection.getUserId());
        input.add(selection.getLevelNo());
        if (selection.getGroupFilter() != Constant.ALL_GROUP) {
            queryName = "PPAGenerate-For-Group";
            input.add(selection.getGroupFilter().replace(Constant.PPA, StringUtils.EMPTY));
        }
        input.add(selection.getFromDateDdlb());
        input.add(selection.getToDateDdlb());
        return PPAQuerys.getPPAData(input, queryName, null);

    }

    private List<PPAProjectionDTO> getCustomizedPPA(List<Object[]> list, ProjectionSelectionDTO selection, List<PPAProjectionDTO> dtoList)   {
        LOGGER.debug("Inside getcustomized PPA");
        int levelNo = selection.getTreeLevelNo();
        for (int i = 0; i < list.size(); i++) {
            Object[] str = list.get(i);
            if (str[0].toString().equalsIgnoreCase(String.valueOf(levelNo))) {
              setdata(str, selection, dtoList);
            }
        }

        LOGGER.debug("End of CustomizedPPA");
        return dtoList;
    }

    private static String createColumn(String quater, String year, String columnName) {
        StringBuilder column = new StringBuilder();
        column.append('q').append(quater).append(year).append(columnName);
        return column.toString();
    }

    private PPAProjectionDTO setdata(Object[] str, ProjectionSelectionDTO selection, List<PPAProjectionDTO> dtoList)  {
        LOGGER.debug("Inside setdata");
        PPAProjectionDTO dto = getDtoFromList(dtoList, str[NumericConstants.NINE]);
        if (dto != null) {
            if (dto.getHirarechyName().equals(Constant.TRADING_PARTNER)) {
                dto.setGroup((str[NumericConstants.FIVE] == null) ? DASH : str[NumericConstants.FIVE].toString());
            }
            int id = cutomizeIntegerObject(str[NumericConstants.SIX]);
            dto.setPriceProtectionStatus(id);
            if (selection.isExcelExport()) {
                dto.setPriceProtectionStatus1(getPriceTypeNameByID(id));
            }
            dto.setPriceProtectionStartDate((Date) str[NumericConstants.SEVEN]);
            dto.setPriceProtectionEndDate((Date) str[NumericConstants.EIGHT]);

            if (str[NumericConstants.TEN] != null && str[NumericConstants.ELEVEN] != null) {
                if (str[NumericConstants.TEN].equals(str[NumericConstants.ELEVEN])) {
                    dto.addBooleanProperties(Constant.CHECK_RECORD + ".0", Integer.parseInt(str[NumericConstants.TEN].toString()) > 0);
                }
                dto.setCheckRecordCount(Integer.valueOf(str[NumericConstants.TEN].toString()));
            }
            if (str[NumericConstants.ELEVEN] != null) {
                dto.setCCPCount(Integer.valueOf(str[NumericConstants.ELEVEN].toString()));

            }

            customizeResults(str, dto, selection);
        }
        LOGGER.debug("End of setdata");
        return dto;
    }

    private static Integer cutomizeIntegerObject(Object val) {
        return Integer.valueOf(val == null || Constant.NULL.equals(val.toString()) ? DASH : val.toString());
    }


    public List getGroupList(int projectionId) {
        List input = new ArrayList();
        input.add(projectionId);
        return PPAQuerys.getPPAData(input, "getUser-Group", null);

    }

    public void setNullValue(PPAProjectionDTO dto, List<Object> visibleColumn) {
        for (Object obj : visibleColumn) {
            if (obj.toString().contains(Constant.PRICECAP) && !obj.toString().contains(Constant.ACTUAL) && !obj.toString().equals("actualPriceCap") && !obj.toString().equals(Constant.GROUP)) {

                dto.addStringProperties(obj.toString(), CommonUtils.PERCENTFORMAT.format(Double.valueOf(Constant.DASH)));
            } else if (obj.toString().equals(Constant.GROUP)) {
                dto.addStringProperties(obj.toString(), DASH);
            }

        }
        dto.addBooleanProperties(Constant.CHECK_RECORD, Boolean.FALSE);
    }


    public static Boolean savePPAProjection(int projectionId, SessionDTO session, SaveDTO dto, ProjectionSelectionDTO selection) {
        List input;
        String propertyId = dto.getPropertyId();
          PPAQuerys.setTableName(session.getCurrentTableNames());
        if (propertyId.equals(Constant.GROUP)) {
            input = getInputForSaveGroup(projectionId, dto.getValue().toString(), dto.getHirarechyNo(), dto.getGroup(), selection);
            PPAQuerys.ppaUpdate(input, "PPA.massupdate-group");

        } else if (propertyId.contains(Constant.PRICECAP)) {
            String freq = String.valueOf(propertyId.charAt(1));
            String year = String.valueOf(propertyId.substring(NumericConstants.TWO, NumericConstants.SIX));
            input = getInputForSavePriceCap(projectionId, dto, dto.getValue().toString(),  freq, year, selection);
            PPAQuerys.ppaUpdate(input, "PPA.savePriceCap");
        } else if (propertyId.contains(Constant.RESET_SMALL)) {
            String freq = String.valueOf(propertyId.charAt(1));
            String year = String.valueOf(propertyId.substring(NumericConstants.TWO, NumericConstants.SIX));
            if ((dto.getValue() != null) && (Boolean.parseBoolean(dto.getValue().toString()))) {
                input = getInputForSavePriceCap(projectionId, dto, Constant.STRING_ONE,  freq, year, selection);
            } else {
                input = getInputForSavePriceCap(projectionId, dto, DASH,  freq, year, selection);
            }
            PPAQuerys.ppaUpdate(input, "PPA.saveReset");
        } else if (propertyId.contains(Constant.CHECK_RECORD)) {
                  
            if ((dto.getValue() != null) && (Boolean.parseBoolean(dto.getValue().toString()))) {
                input = getInputForSaveCheckRecord(projectionId, dto, Constant.STRING_ONE, selection);
            } else {
                input = getInputForSaveCheckRecord(projectionId, dto, DASH, selection);
            }
            PPAQuerys.ppaUpdate(input, "PPA.saveCheckRecord");
        }
        return BooleanConstant.getTrueFlag();

    }

    public static List getInputForSavePriceCap(int projectionId, SaveDTO dto, String value, String freq, String year, ProjectionSelectionDTO selection) {
        List input = new ArrayList();
         String ccpQuery = new CommonLogic().insertAvailableHierarchyNo(selection);
        input.add(ccpQuery);
        input.add(value);
        input.add(selection.getRelationshipBuilderSid());
        input.add(projectionId);
        input.add(dto.getHirarechyNo());
        input.add(dto.getGroup());
        input.add(freq);
        input.add(year);

        return input;
    }


    private static List getInputForSaveCheckRecord(int projectionId, SaveDTO dto, String value, ProjectionSelectionDTO selection) {
        List input = new ArrayList();
        String ccpQuery = new CommonLogic().insertAvailableHierarchyNo(selection);
        input.add(ccpQuery);
        input.add(value);
        input.add(selection.getRelationshipBuilderSid());
        input.add(projectionId);
        input.add(dto.getHirarechyNo());
        input.add(dto.getGroup());
        return input;
    }

        /**
     *
     * @param start
     * @param offset
     * @param projSelDTO
     * @return
     */
     /**
     *
     * @param start
     * @param offset
     * @param projSelDTO
     * @return
     */
    public List<PPAProjectionDTO> configureLevels(int start, int offset, ProjectionSelectionDTO projSelDTO, CustomTableHeaderDTO ridhtdto) {
        List finalList = new ArrayList();
        Set<String> hirarechyNos = new HashSet<>();
        CommonLogic commLogic = new CommonLogic();
        List<PPAProjectionDTO> resultList = new ArrayList<>();
        Map<String, List> relationshipLevelDetailsMap = projSelDTO.getSessionDTO().getHierarchyLevelDetails();
        if (projSelDTO.isIsCustomHierarchy()) {

            String hierarchyIndicator = commLogic.getHiearchyIndicatorFromCustomView(projSelDTO);
            List<String> hierarchyNoList = commLogic.getHiearchyNoForCustomView(projSelDTO, start, offset);
            for (String hierarchyNo : hierarchyNoList) {
                resultList.add(configureDetailsInDTO(projSelDTO, hierarchyNo, hierarchyIndicator, relationshipLevelDetailsMap.get(hierarchyNo),ridhtdto));
            }

        } else {
            List<String> hierarchyNoList = commLogic.getHiearchyNoAsList(projSelDTO, start, offset);
            for (String hierarchyNo : hierarchyNoList) {
               hirarechyNos.add(hierarchyNo);
                resultList.add(configureDetailsInDTO(projSelDTO, hierarchyNo, projSelDTO.getHierarchyIndicator(), relationshipLevelDetailsMap.get(hierarchyNo),ridhtdto));
            }
        }
        finalList.add(resultList);
        finalList.add(hirarechyNos);

        return finalList;
    }

    /**
     *
     * @param projSelDTO
     * @param hierarchyNo
     * @param hierarchyIndicator
     * @param detailsList
     * @return
     */
    public PPAProjectionDTO configureDetailsInDTO(ProjectionSelectionDTO projSelDTO, String hierarchyNo, String hierarchyIndicator, List detailsList, CustomTableHeaderDTO ridhtdto) {
        PPAProjectionDTO dto = new PPAProjectionDTO();
        setNullValue(dto, ridhtdto.getSingleColumns());
        dto.setLevelNo(Integer.valueOf(detailsList.get(NumericConstants.TWO).toString()));
        dto.setHirarechyNo(hierarchyNo);

        dto.setLevelName(projSelDTO.getSessionDTO().getLevelValueDiscription(hierarchyNo, hierarchyIndicator));
        dto.setHirarechyName(detailsList.get(0).toString());
        dto.setGroup(detailsList.get(0).toString());

        return dto;
    }

    private PPAProjectionDTO getDtoFromList(List<PPAProjectionDTO> dtoList, Object hirarechyNo) {

        for (int i = 0; i < dtoList.size(); i++) {
            if (dtoList.get(i).getHirarechyNo().equals(hirarechyNo)) {
                return dtoList.get(i);
            }
        }
        return null;

    }
    

    private static List<Date> getStartAndEndDate(int startQuater, int endQuater, int startYear, int endYear) {

        List result = new ArrayList();
        Calendar startCal = Calendar.getInstance();
        Calendar endCal = Calendar.getInstance();
        startCal.set(Calendar.DATE, 1);
        startCal.set(Calendar.YEAR, startYear);
        endCal.set(Calendar.YEAR, endYear);
        if (startQuater == 1) {
            startCal.set(Calendar.MONTH, 0);

        } else if (startQuater == NumericConstants.TWO) {
            startCal.set(Calendar.MONTH, NumericConstants.THREE);
        } else if (startQuater == NumericConstants.THREE) {
            startCal.set(Calendar.MONTH, NumericConstants.SIX);
        } else if (startQuater == NumericConstants.FOUR) {
            startCal.set(Calendar.MONTH, NumericConstants.NINE);
        }
        if (endQuater == 1) {
            endCal.set(Calendar.DATE, NumericConstants.THIRTY_ONE);
            endCal.set(Calendar.MONTH, NumericConstants.TWO);
        } else if (endQuater == NumericConstants.TWO) {
            endCal.set(Calendar.DATE, NumericConstants.THIRTY);
            endCal.set(Calendar.MONTH, NumericConstants.FIVE);
        } else if (endQuater == NumericConstants.THREE) {
            endCal.set(Calendar.DATE, NumericConstants.THIRTY);
            endCal.set(Calendar.MONTH, NumericConstants.EIGHT);
        } else if (endQuater == NumericConstants.FOUR) {
            endCal.set(Calendar.DATE, NumericConstants.THIRTY_ONE);
            endCal.set(Calendar.MONTH, NumericConstants.ELEVEN);
        }
        result.add(startCal.getTime());
        result.add(endCal.getTime());
        return result;
    }

    /**
     * In selection needed things 1.projectionid 2.isFilter 3.levelNo
     * 4.HierarchyNo 5.isCount
     *
     * @param selection
     * @param lastParent
     * @param session
     * @param viewChangeHierarchy
     * @param start
     * @param offset
     * @param rightDto
     * @return
     */
    public List getPPALogicData(ProjectionSelectionDTO selection, Object lastParent, int start, int offset, CustomTableHeaderDTO rightDto) {
        List list = null;

        try {
            int levelNo;
            selection.setIsCount(BooleanConstant.getFalseFlag());
            if (lastParent instanceof PPAProjectionDTO) {
                PPAProjectionDTO dto = (PPAProjectionDTO) lastParent;
                levelNo = dto.getLevelNo();
                selection.setLevelNo(levelNo + 1);
                selection.setHierarchyNo(dto.getHirarechyNo());
                list = (List) getPPAProjectionResults(selection, rightDto, start, offset);
            } else if (!selection.isIsFilter()) {
                selection.setHierarchyNo(StringUtils.EMPTY);
                selection.setLevelNo(selection.getCustomerLevelNo());
                list = (List) getPPAProjectionResults(selection, rightDto, start, offset);
            }
            if (selection.isIsFilter()) {
                list = (List) getPPAProjectionResults(selection, rightDto, start, offset);
            }

        } catch (Exception ex) {

            LOGGER.error(ex.getMessage());
        }
        return list;
    }

    public int getPPAProjectionResultsCount(ProjectionSelectionDTO selection, Object lastParent, SessionDTO session) {
        int count = 0;
        try {
            if (lastParent != null && (lastParent instanceof PPAProjectionDTO) || (selection.isIsFilter())) {
                if (selection.isIsFilter()) {
                    count = configureLevelsCount(selection.getLevelNo(), selection);
                } else {
                    PPAProjectionDTO dto = (PPAProjectionDTO) lastParent;
                    selection.setLevelNo(dto == null ? 0 : dto.getLevelNo() + 1);
                    count = configureLevelsCount(selection.getLevelNo(), selection);

                }

            } else {
                selection.setLevelNo(Integer.parseInt(session.getCustomerLevelNumber()));
                count = configureLevelsCount(selection.getLevelNo(), selection);
            }

            selection.setLevelCount(count);
        } catch (NumberFormatException ex) {
            LOGGER.error(ex.getMessage());
        }
        return count;
    }

    public int configureLevelsCount(int levelNo, ProjectionSelectionDTO projSelDTO) {
        int levelCount = 0;
        if (projSelDTO.isIsCustomHierarchy()) {
            levelCount = commonLogic.getCountForCustomView(projSelDTO);
        } else {
            projSelDTO.setTreeLevelNo(levelNo);
            levelCount = commonLogic.getCount(projSelDTO);
        }
        return levelCount;
    }

    public void saveAllcheckRecord(boolean checked, SessionDTO session, String group) {
          PPAQuerys.setTableName(session.getCurrentTableNames());
        List input = new ArrayList();
        if (checked) {
            input.add(1);
        } else {
            input.add(0);
        }
        input.add(group);
        PPAQuerys.ppaUpdate(input, "PPA.saveAllCheckRecord");

    }


    public static void waitForPPAProcedure() {
        LOGGER.debug("Inside  waitForProcedure Method");
        if (procedureThread != null) {
            synchronized (procedureThread) {
                if (procedureThread.isAlive()) {

                    try {
                        runnableJob.wait();
                    } catch (InterruptedException e) {
                        LOGGER.error(e.getMessage());
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
        LOGGER.debug("End of  waitForProcedure  Method");
    }



    private void addPropertyValue(Object value, PPAProjectionDTO dto, String column, int startIndex, int varIndex, String commonCol, boolean isDate) {
        if (startIndex != -1 && varIndex != -1) {
            int start = startIndex + varIndex;
            String singleColumn = commonCol + column + "." + start;
            Object val = value;
            if (!isDate) {
                if (value == null) {
                    val = StringUtils.EMPTY;
                } else {
                    val = String.valueOf(value);
                }
            }
            dto.addProperties(singleColumn, val);
        }
    }

    private void addHelperListValue(Object value, PPAProjectionDTO dto, String column, int startIndex, int varIndex, String commonCol) {
        if (startIndex != -1 && varIndex != -1) {
            int start = startIndex + varIndex;
            String singleColumn = commonCol + column + "." + start;
            if (Constant.STRING_ONE.equals(String.valueOf(value).trim())) {
                dto.getHelperList().add(singleColumn);
            }
        }
    }

    private void addCustomPropertyValue(Object value, PPAProjectionDTO dto, String column, int startIndex, int varIndex, String commonCol, boolean isExcel) {
        if (startIndex != -1 && varIndex != -1) {
            int start = startIndex + varIndex;
            String singleColumn = commonCol + column + "." + start;
            int id = 0;
            if (dto.getHelperList().contains(singleColumn)) {
                dto.addIntegerProperties(singleColumn, -1);
            } else if (!isExcel) {
                id = cutomizeIntegerObject(value);
                dto.addIntegerProperties(singleColumn, id);
            } else {
                if ("priceProtectionPriceType".equals(column)
                        || "basePricePriceType".equals(column)
                        || "resetPriceType".equals(column)
                        || "subsequentPeriodPriceType".equals(column)) {
                    id = cutomizeIntegerObject(value);
                    dto.addStringProperties(singleColumn, PPAServiceSupport.getInstance().getDescriptionByID(id));

                } else {
                    id = cutomizeIntegerObject(value);
                    dto.addStringProperties(singleColumn, HelperListUtil.getInstance().getDescriptionByID(id));
                }

            }
        }
    }

    /**
     *
     * @param obj
     * @param dto
     */
    private void customizeResults(Object[] obj, PPAProjectionDTO dto, ProjectionSelectionDTO selection) {
        int quater = Integer.parseInt(obj[NumericConstants.THREE].toString());
        int year = Integer.parseInt(obj[NumericConstants.FOUR].toString());
        List<String> commonCol = HeaderUtils.getCommonColumnHeader(selection.getFrequencyDivision(), year, quater);
        int pIndex = selection.getPeriodList().indexOf(commonCol.get(0));
        if (pIndex > -1) {
            int startIndex = pIndex * selection.getPpaSelectedVariables().size();
            startIndex = startIndex + (selection.isExcelExport() ? 0 : 1);

            addPropertyValue(obj[NumericConstants.THIRTEEN], dto, Constant.PPAColumns.NEP.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.NEP.getConstant()), commonCol.get(0), false);

            addPropertyValue(obj[NumericConstants.SIXTEEN], dto, Constant.PPAColumns.BASE_PRICE_MANUAL.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.BASE_PRICE_MANUAL.getConstant()), commonCol.get(0), false);
            addPropertyValue(obj[NumericConstants.SEVENTEEN], dto, Constant.PPAColumns.BASE_PRICE_DATE.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.BASE_PRICE_DATE.getConstant()), commonCol.get(0), true);

            addPropertyValue(obj[NumericConstants.TWENTY_SEVEN], dto, Constant.PPAColumns.PRICE_TOLERANCE.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.PRICE_TOLERANCE.getConstant()), commonCol.get(0), false);
            addPropertyValue(obj[NumericConstants.TWENTY_EIGHT], dto, Constant.PPAColumns.MAX_INCREMENTAL_CHANGE.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.MAX_INCREMENTAL_CHANGE.getConstant()), commonCol.get(0), false);
            addPropertyValue(obj[NumericConstants.THIRTY_ONE], dto, Constant.PPAColumns.RESET_DATE.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.RESET_DATE.getConstant()), commonCol.get(0), true);

            addPropertyValue(obj[NumericConstants.THIRTY_NINE], dto, Constant.PPAColumns.ATTACHED_DATE.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.ATTACHED_DATE.getConstant()), commonCol.get(0), true);

            addHelperListValue(String.valueOf(obj[NumericConstants.FORTY]), dto, Constant.PPAColumns.PRICE_PROTECTION_PRICE_TYPE.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.PRICE_PROTECTION_PRICE_TYPE.getConstant()), commonCol.get(0));

            addHelperListValue(String.valueOf(obj[NumericConstants.FORTY_TWO]), dto, Constant.PPAColumns.BASE_PRICE_TYPE.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.BASE_PRICE_TYPE.getConstant()), commonCol.get(0));
            addHelperListValue(String.valueOf(obj[NumericConstants.FORTY_THREE]), dto, Constant.PPAColumns.BASE_PRICE_DATE.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.BASE_PRICE_DATE.getConstant()), commonCol.get(0));
            addHelperListValue(String.valueOf(obj[NumericConstants.FORTY_FOUR]), dto, Constant.PPAColumns.BASE_PRICE_PRICE_TYPE.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.BASE_PRICE_PRICE_TYPE.getConstant()), commonCol.get(0));
            addHelperListValue(String.valueOf(obj[NumericConstants.FORTY_FIVE]), dto, Constant.PPAColumns.NET_BASE_PRICE.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.NET_BASE_PRICE.getConstant()), commonCol.get(0));

            addHelperListValue(String.valueOf(obj[NumericConstants.FORTY_SEVEN]), dto, Constant.PPAColumns.SUBSEQUENT_PERIOD_PRICE_TYPE.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.SUBSEQUENT_PERIOD_PRICE_TYPE.getConstant()), commonCol.get(0));
            addHelperListValue(String.valueOf(obj[NumericConstants.FORTY_EIGHT]), dto, Constant.PPAColumns.NET_SUBSEQUENT_PERIOD_PRICE.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.NET_SUBSEQUENT_PERIOD_PRICE.getConstant()), commonCol.get(0));

            addHelperListValue(String.valueOf(obj[NumericConstants.FIFTY]), dto, Constant.PPAColumns.PRICE_TOLERANCE_INTERVAL.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.PRICE_TOLERANCE_INTERVAL.getConstant()), commonCol.get(0));
            addHelperListValue(String.valueOf(obj[NumericConstants.FIFTY_ONE]), dto, Constant.PPAColumns.PRICE_TOLERANCE_FREQUENCY.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.PRICE_TOLERANCE_FREQUENCY.getConstant()), commonCol.get(0));
            addHelperListValue(String.valueOf(obj[NumericConstants.FIFTY_TWO]), dto, Constant.PPAColumns.PRICE_TOLERANCE_TYPE.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.PRICE_TOLERANCE_TYPE.getConstant()), commonCol.get(0));
            addHelperListValue(String.valueOf(obj[NumericConstants.FIFTY_THREE]), dto, Constant.PPAColumns.PRICE_TOLERANCE.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.PRICE_TOLERANCE.getConstant()), commonCol.get(0));
            addHelperListValue(String.valueOf(obj[NumericConstants.FIFTY_FOUR]), dto, Constant.PPAColumns.RESET_ELIGIBLE.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.RESET_ELIGIBLE.getConstant()), commonCol.get(0));
            addHelperListValue(String.valueOf(obj[NumericConstants.FIFTY_FIVE]), dto, Constant.PPAColumns.RESET_TYPE.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.RESET_TYPE.getConstant()), commonCol.get(0));
            addHelperListValue(String.valueOf(obj[NumericConstants.FIFTY_SIX]), dto, Constant.PPAColumns.RESET_DATE.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.RESET_DATE.getConstant()), commonCol.get(0));
            addHelperListValue(String.valueOf(obj[NumericConstants.FIFTY_SEVEN]), dto, Constant.PPAColumns.RESET_INTERVAL.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.RESET_INTERVAL.getConstant()), commonCol.get(0));
            addHelperListValue(String.valueOf(obj[NumericConstants.FIFTY_EIGHT]), dto, Constant.PPAColumns.RESET_FREQUENCY.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.RESET_FREQUENCY.getConstant()), commonCol.get(0));
            addHelperListValue(String.valueOf(obj[NumericConstants.FIFTY_NINE]), dto, Constant.PPAColumns.RESET_PRICE_TYPE.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.RESET_PRICE_TYPE.getConstant()), commonCol.get(0));
            addHelperListValue(String.valueOf(obj[NumericConstants.SIXTY]), dto, Constant.PPAColumns.NET_RESET_PRICE_TYPE.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.NET_RESET_PRICE_TYPE.getConstant()), commonCol.get(0));

            addHelperListValue(String.valueOf(obj[NumericConstants.SIXTY_TWO]), dto, Constant.PPAColumns.NET_PRICE_TYPE.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.NET_PRICE_TYPE.getConstant()), commonCol.get(0));
            addPropertyValue(String.valueOf(obj[NumericConstants.FORTY_SIX]).equals(Constant.STRING_ONE) ? StringUtils.EMPTY : obj[NumericConstants.SIXTY_FOUR], dto, Constant.PPAColumns.NET_BASE_PRICE_FORMULA.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.NET_BASE_PRICE_FORMULA.getConstant()), commonCol.get(0), false);
            addPropertyValue(String.valueOf(obj[NumericConstants.FORTY_ONE]).equals(Constant.STRING_ONE) ? StringUtils.EMPTY : obj[NumericConstants.SIXTY_FIVE], dto, Constant.PPAColumns.NEP_FORMULA.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.NEP_FORMULA.getConstant()), commonCol.get(0), false);
            addPropertyValue(String.valueOf(obj[NumericConstants.FORTY_NINE]).equals(Constant.STRING_ONE) ? StringUtils.EMPTY : obj[NumericConstants.SIXTY_SIX], dto, Constant.PPAColumns.NET_SUBSEQUENT_PERIOD_PRICE_FORMULA.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.NET_SUBSEQUENT_PERIOD_PRICE_FORMULA.getConstant()), commonCol.get(0), false);
            addPropertyValue(String.valueOf(obj[NumericConstants.SIXTY_THREE]).equals(Constant.STRING_ONE) ? StringUtils.EMPTY : obj[NumericConstants.SIXTY_SEVEN], dto, Constant.PPAColumns.NET_PRICE_TYPE_FORMULA.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.NET_PRICE_TYPE_FORMULA.getConstant()), commonCol.get(0), false);
            addPropertyValue(String.valueOf(obj[NumericConstants.SIXTY_ONE]).equals(Constant.STRING_ONE) ? StringUtils.EMPTY : obj[NumericConstants.SIXTY_EIGHT], dto, Constant.PPAColumns.NET_RESET_PRICE_FORMULA.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.NET_RESET_PRICE_FORMULA.getConstant()), commonCol.get(0), false);
            addHelperListValue(String.valueOf(obj[NumericConstants.FORTY_ONE]), dto, Constant.PPAColumns.NEP_FORMULA.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.NEP_FORMULA.getConstant()), commonCol.get(0));
            addHelperListValue(String.valueOf(obj[NumericConstants.FORTY_SIX]), dto, Constant.PPAColumns.NET_BASE_PRICE_FORMULA.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.NET_BASE_PRICE_FORMULA.getConstant()), commonCol.get(0));
            addHelperListValue(String.valueOf(obj[NumericConstants.FORTY_NINE]), dto, Constant.PPAColumns.NET_SUBSEQUENT_PERIOD_PRICE_FORMULA.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.NET_SUBSEQUENT_PERIOD_PRICE_FORMULA.getConstant()), commonCol.get(0));
            addHelperListValue(String.valueOf(obj[NumericConstants.SIXTY_ONE]), dto, Constant.PPAColumns.NET_RESET_PRICE_FORMULA.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.NET_RESET_PRICE_FORMULA.getConstant()), commonCol.get(0));
            addHelperListValue(String.valueOf(obj[NumericConstants.SIXTY_THREE]), dto, Constant.PPAColumns.NET_PRICE_TYPE_FORMULA.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.NET_PRICE_TYPE_FORMULA.getConstant()), commonCol.get(0));
            addCustomPropertyValue(obj[NumericConstants.TWELVE], dto, Constant.PPAColumns.PRICE_PROTECTION_PRICE_TYPE.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.PRICE_PROTECTION_PRICE_TYPE.getConstant()), commonCol.get(0), selection.isExcelExport());
            addCustomPropertyValue(obj[NumericConstants.FIFTEEN], dto, Constant.PPAColumns.BASE_PRICE_TYPE.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.BASE_PRICE_TYPE.getConstant()), commonCol.get(0), selection.isExcelExport());
            addCustomPropertyValue(obj[NumericConstants.EIGHTEEN], dto, Constant.PPAColumns.BASE_PRICE_PRICE_TYPE.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.BASE_PRICE_PRICE_TYPE.getConstant()), commonCol.get(0), selection.isExcelExport());
            addCustomPropertyValue(obj[NumericConstants.NINETEEN], dto, Constant.PPAColumns.NET_BASE_PRICE.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.NET_BASE_PRICE.getConstant()), commonCol.get(0), selection.isExcelExport());
            addCustomPropertyValue(obj[NumericConstants.TWENTY_ONE], dto, Constant.PPAColumns.SUBSEQUENT_PERIOD_PRICE_TYPE.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.SUBSEQUENT_PERIOD_PRICE_TYPE.getConstant()), commonCol.get(0), selection.isExcelExport());
            addCustomPropertyValue(obj[NumericConstants.TWENTY_TWO], dto, Constant.PPAColumns.NET_SUBSEQUENT_PERIOD_PRICE.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.NET_SUBSEQUENT_PERIOD_PRICE.getConstant()), commonCol.get(0), selection.isExcelExport());
            addCustomPropertyValue(obj[NumericConstants.TWENTY_FOUR], dto, Constant.PPAColumns.PRICE_TOLERANCE_INTERVAL.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.PRICE_TOLERANCE_INTERVAL.getConstant()), commonCol.get(0), selection.isExcelExport());
            addCustomPropertyValue(obj[NumericConstants.TWENTY_FIVE], dto, Constant.PPAColumns.PRICE_TOLERANCE_FREQUENCY.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.PRICE_TOLERANCE_FREQUENCY.getConstant()), commonCol.get(0), selection.isExcelExport());
            addCustomPropertyValue(obj[NumericConstants.TWENTY_SIX], dto, Constant.PPAColumns.PRICE_TOLERANCE_TYPE.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.PRICE_TOLERANCE_TYPE.getConstant()), commonCol.get(0), selection.isExcelExport());
            addCustomPropertyValue(obj[NumericConstants.TWENTY_NINE], dto, Constant.PPAColumns.RESET_ELIGIBLE.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.RESET_ELIGIBLE.getConstant()), commonCol.get(0), selection.isExcelExport());
            addCustomPropertyValue(obj[NumericConstants.THIRTY], dto, Constant.PPAColumns.RESET_TYPE.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.RESET_TYPE.getConstant()), commonCol.get(0), selection.isExcelExport());
            addCustomPropertyValue(obj[NumericConstants.THIRTY_TWO], dto, Constant.PPAColumns.RESET_INTERVAL.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.RESET_INTERVAL.getConstant()), commonCol.get(0), selection.isExcelExport());
            addCustomPropertyValue(obj[NumericConstants.THIRTY_THREE], dto, Constant.PPAColumns.RESET_FREQUENCY.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.RESET_FREQUENCY.getConstant()), commonCol.get(0), selection.isExcelExport());
            addCustomPropertyValue(obj[NumericConstants.THIRTY_FOUR], dto, Constant.PPAColumns.RESET_PRICE_TYPE.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.RESET_PRICE_TYPE.getConstant()), commonCol.get(0), selection.isExcelExport());
            addCustomPropertyValue(obj[NumericConstants.THIRTY_FIVE], dto, Constant.PPAColumns.NET_RESET_PRICE_TYPE.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.NET_RESET_PRICE_TYPE.getConstant()), commonCol.get(0), selection.isExcelExport());
            addCustomPropertyValue(obj[NumericConstants.THIRTY_SEVEN], dto, Constant.PPAColumns.NET_PRICE_TYPE.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.NET_PRICE_TYPE.getConstant()), commonCol.get(0), selection.isExcelExport());

        }


    }

    /**
     *
     * @param columnName
     * @param quater
     * @param year
     * @param obj
     * @param dto
     */
    public void setColumnValue(String columnName, String quater, String year, Object obj, PPAProjectionDTO dto, boolean isExcel) {
        int id = 0;
        if (Constant.STRING_ONE.equals(dto.getPropertyValue(Constant.Q_SMALL + quater + year + columnName + Constant.FLAG))) {
            dto.addIntegerProperties(createColumn(quater, year, columnName), -1);
        } else if (!isExcel) {
            id = cutomizeIntegerObject(obj);
            dto.addIntegerProperties(createColumn(quater, year, columnName), id);
        } else {

            if ("priceProtectionPriceType".equals(columnName)
                    || "basePricePriceType".equals(columnName)
                    || "resetPriceType".equals(columnName)
                    || "subsequentPeriodPriceType".equals(columnName)) {

                id = cutomizeIntegerObject(obj);
                dto.addStringProperties(createColumn(quater, year, columnName), PPAServiceSupport.getInstance().getDescriptionByID(id));

            } else {
                id = cutomizeIntegerObject(obj);
                dto.addStringProperties(createColumn(quater, year, columnName), HelperListUtil.getInstance().getDescriptionByID(id));
            }
        }
    }

    public String getPriceTypeNameByID(final Integer priceType)  {

        String retval;
        List<Object> list;
        if (priceType != null && !priceType.equals(0)) {

            String sqlQuery = "SELECT * FROM HELPER_TABLE WHERE HELPER_TABLE_SID =" + priceType;
            list = HelperTableLocalServiceUtil.executeSelectQuery(sqlQuery);
            Object[] value = (Object[]) list.get(0);
            retval = value[1].toString();
        } else {
            retval = StringUtils.EMPTY;
        }
        return retval;
    }
}
