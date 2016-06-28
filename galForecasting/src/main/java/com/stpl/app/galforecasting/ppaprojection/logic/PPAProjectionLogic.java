/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.ppaprojection.logic;

import com.stpl.app.galforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.galforecasting.dto.SaveDTO;
import com.stpl.app.galforecasting.logic.CommonLogic;
import com.stpl.app.galforecasting.logic.RunnableJob;
import com.stpl.app.galforecasting.ppaprojection.dto.PPAProjectionDTO;
import com.stpl.app.galforecasting.ppaprojection.form.PPAProjection;
import com.stpl.app.galforecasting.queryUtils.PPAQuerys;
import com.stpl.app.galforecasting.sessionutils.SessionDTO;
import com.stpl.app.galforecasting.utils.CommonUtils;
import com.stpl.app.galforecasting.utils.Constant;
import static com.stpl.app.galforecasting.utils.Constant.DASH;
import com.stpl.app.galforecasting.utils.HeaderUtils;
import com.stpl.app.galforecasting.utils.HelperListUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author Jayaram
 */
public class PPAProjectionLogic {

    private static final Logger LOGGER = Logger.getLogger(PPAProjectionLogic.class);
    private static Thread procedureThread;
    private static RunnableJob runnableJob;
    private static final Map<Integer, String> PRICE_QUALIFIER_MAP = PPAServiceSupport.getInstance().getPriceQualifierMap();

    public List getInputForMassUpdate(int startQuater, int endQuater, int startYear, int endYear, Object columnValue, String columnName, String group, ProjectionSelectionDTO selection) {
        List input = new ArrayList();
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
                input.add("'" + String.valueOf(columnValue) + "'");
            }

        }

        input.add(selection.getRelationshipBuilderSid());
        input.add(selection.getSessionId());
        input.add(selection.getUserId());
        input.add(selection.getProjectionId());
        if (selection.getGroupFilter() != Constant.ALL_GROUP) {
            input.add(selection.getGroupFilter().replace(Constant.PPA, StringUtils.EMPTY));
        } else {
            input.add(Constant.PERCENT);
        }
        input.add(CommonUtils.DBDate.format(startAndEndDate.get(0)));
        input.add(CommonUtils.DBDate.format(startAndEndDate.get(1)));
        return input;

    }

    public List getInputForMassUpdateGroup(Object columnValue, String columnName, String group, ProjectionSelectionDTO selection) {
        List input = new ArrayList();
        input.add(columnName);
        if (columnValue instanceof HelperDTO) {
            input.add(((HelperDTO) columnValue).getId());
        } else if (columnValue instanceof Date) {
            input.add("CONVERT(DATE, '" + CommonUtils.getDateTime(Constant.DATE_FORMAT_1, (Date) columnValue) + "')");
        } else {
            input.add("'" + String.valueOf(columnValue) + "'");
        }
        input.add(selection.getRelationshipBuilderSid());
        input.add(selection.getSessionId());
        input.add(selection.getUserId());
        input.add(selection.getProjectionId());
        if (selection.getGroupFilter() != Constant.ALL_GROUP) {
            input.add(selection.getGroupFilter().replace(Constant.PPA, StringUtils.EMPTY));
        } else {
            input.add(Constant.PERCENT);
        }
        return input;
    }

    private static List getInputForSaveGroup(int projectionId, Object priceCap, String hirarechyNo, SessionDTO session, String group, ProjectionSelectionDTO selection) {
        List input = new ArrayList();
        input.add(priceCap);
        input.add(selection.getRelationshipBuilderSid());
        input.add(projectionId);
        input.add(hirarechyNo);
        input.add(session.getSessionId());
        input.add(session.getUserId());
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
    public Object getPPAProjectionResults(ProjectionSelectionDTO selection, CustomTableHeaderDTO ridhtdto, int start, int offset) throws PortalException, Exception {
        List list = null;
        printselection(selection);
        List<Object> finalList = (List) configureLevel(selection, start, offset, selection.getHierarchyNo(), ridhtdto);
        List<PPAProjectionDTO> PPAList = (List<PPAProjectionDTO>) finalList.get(0);
        list = getPPAProjectionList(selection, selection.getHierarchyNo(), finalList);
        list = getCustomizedPPA(list, selection, PPAList);
        return list;
    }

    private List getPPAProjectionList(ProjectionSelectionDTO selection, String hirarechyNo, List<Object> finalList) {
        List<Object> input = new ArrayList<Object>();
        String queryName = "PPAGenerate";
        input.add(selection.getRelationshipBuilderSid());
        input.add(selection.getProjectionId());
        input.add((hirarechyNo == null ? StringUtils.EMPTY : hirarechyNo));
        input.add(selection.getSessionId());
        input.add(selection.getUserId());
        input.add(selection.getLevelNo());
        if (selection.getGroupFilter() != Constant.ALL_GROUP) {
            queryName = "PPAGenerate-For-Group";
            input.add(selection.getGroupFilter().replace(Constant.PPA, StringUtils.EMPTY));
        }
        input.add(getListToString((Collection) finalList.get(1)));
        input.add(selection.getFromDateDdlb());
        input.add(selection.getToDateDdlb());
        return PPAQuerys.getPPAData(input, queryName, null);

    }

    private List<PPAProjectionDTO> getCustomizedPPA(List<Object[]> list, ProjectionSelectionDTO selection, List<PPAProjectionDTO> dtoList) throws Exception {
        LOGGER.info("Inside getcustomized PPA");
        PPAProjectionDTO dto = null;
        int levelNo = selection.getLevelNo();
        for (int i = 0; i < list.size(); i++) {
            Object[] str = list.get(i);
            if (str[0].toString().equalsIgnoreCase(String.valueOf(levelNo))) {
                dto = setdata(str, selection, dtoList);
            }
        }

        LOGGER.info("End of CustomizedPPA");
        return dtoList;
    }

    private static String createColumn(String quater, String year, String columnName) {
        StringBuilder column = new StringBuilder();
        column.append(Constant.Q_SMALL).append(quater).append(year).append(columnName);
        return column.toString();
    }

    private PPAProjectionDTO setdata(Object[] str, ProjectionSelectionDTO selection, List<PPAProjectionDTO> dtoList) throws SystemException {
        LOGGER.info("Inside setdata");
        PPAProjectionDTO dto = getDtoFromList(dtoList, str[9]);
        if (dto != null) {
            if (dto.getHirarechyName().equals(Constant.TRADING_PARTNER)) {
                dto.setGroup((str[5] == null) ? DASH : str[5].toString());
            }
            int id = cutomizeIntegerObject(str[6]);
            dto.setPriceProtectionStatus(id);
            if (selection.isExcelExport()) {
                dto.setPriceProtectionStatus1(getPriceTypeNameByID(id));
            }
            dto.setPriceProtectionStartDate((Date) str[7]);
            dto.setPriceProtectionEndDate((Date) str[8]);

            if (str[10] != null && str[11] != null) {
                if (str[10].equals(str[11])) {
                    dto.addBooleanProperties(Constant.CHECK_RECORD + ".0", Integer.valueOf(str[10].toString()) > 0);
                }
                dto.setCheckRecordCount(Integer.valueOf(str[10].toString()));
            }
            if (str[11] != null) {
                dto.setCCPCount(Integer.valueOf(str[11].toString()));

            }

            customizeResults(str, dto, selection);
        }
        LOGGER.info("End of setdata");
        return dto;
    }

    private static Integer cutomizeIntegerObject(Object val) {
        return Integer.valueOf(val == null || Constant.NULL.equals(val.toString()) ? DASH : val.toString());
    }

    private boolean isColumn(List<Object> visibleColumn, String quater, String year, String caption) {
        if (visibleColumn.contains(Constant.Q_SMALL + quater + year + caption)) {
            return true;
        }
        return false;
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
        if (propertyId.equals(Constant.GROUP)) {
            input = getInputForSaveGroup(projectionId, dto.getValue().toString(), dto.getHirarechyNo(), session, dto.getGroup(), selection);
            PPAQuerys.PPAUpdate(input, "PPA.massupdate-group");

        } else if (propertyId.contains(Constant.PRICECAP)) {
            String freq = String.valueOf(propertyId.charAt(1));
            String year = String.valueOf(propertyId.substring(2, 6));
            input = getInputForSavePriceCap(projectionId, dto, dto.getValue().toString(), session, freq, year, selection);
            PPAQuerys.PPAUpdate(input, "PPA.savePriceCap");
        } else if (propertyId.contains(Constant.RESET_SMALL)) {
            String freq = String.valueOf(propertyId.charAt(1));
            String year = String.valueOf(propertyId.substring(2, 6));
            if ((dto.getValue() != null) && (Boolean.valueOf(dto.getValue().toString()))) {
                input = getInputForSavePriceCap(projectionId, dto, Constant.STRING_ONE, session, freq, year, selection);
            } else {
                input = getInputForSavePriceCap(projectionId, dto, DASH, session, freq, year, selection);
            }
            PPAQuerys.PPAUpdate(input, "PPA.saveReset");
        } else if (propertyId.contains(Constant.CHECK_RECORD)) {
                  
            if ((dto.getValue() != null) && (Boolean.valueOf(dto.getValue().toString()))) {
                input = getInputForSaveCheckRecord(projectionId, dto, Constant.STRING_ONE, session, selection);
            } else {
                input = getInputForSaveCheckRecord(projectionId, dto, DASH, session, selection);
            }
            PPAQuerys.PPAUpdate(input, "PPA.saveCheckRecord");
        }
        return true;

    }

    public static List getInputForSavePriceCap(int projectionId, SaveDTO dto, String value, SessionDTO session, String freq, String year, ProjectionSelectionDTO selection) {
        List input = new ArrayList();
        input.add(value);
        input.add(selection.getRelationshipBuilderSid());
        input.add(projectionId);
        input.add(dto.getHirarechyNo());
        input.add(session.getSessionId());
        input.add(session.getUserId());
        input.add(dto.getGroup());
        input.add(freq);
        input.add(year);

        return input;
    }

    private void printList(List<Object[]> list) {

        for (int i = 0; i < list.size(); i++) {

            Object[] str = list.get(i);

        }
    }

    private static List getInputForSaveCheckRecord(int projectionId, SaveDTO dto, String value, SessionDTO session, ProjectionSelectionDTO selection) {
        List input = new ArrayList();
        input.add(value);
        input.add(selection.getRelationshipBuilderSid());
        input.add(projectionId);
        input.add(dto.getHirarechyNo());
        input.add(session.getSessionId());
        input.add(session.getUserId());
        input.add(dto.getGroup());
        return input;
    }

    private Object configureLevel(ProjectionSelectionDTO selection, int start, int offset, String hirarechyNo, CustomTableHeaderDTO ridhtdto) {
        List result = new ArrayList();
        List finalList = new ArrayList();
        Set<String> hirarechyNos = new HashSet<String>();
        List<Leveldto> levelList = CommonLogic.getConditionalLevelList(selection.getProjectionId(), start, offset, selection.getHierarchyIndicator(), selection.getLevelNo(), hirarechyNo, selection.getProductHierarchyNo(), selection.getCustomerHierarchyNo(), selection.isIsFilter(), false, selection.isIsCustomHierarchy(), selection.getCustomId(), selection.getGroupFilter(), selection.getUserId(), selection.getSessionId(), selection.getCustRelationshipBuilderSid(), selection.getProdRelationshipBuilderSid(), false, true);
        for (int i = 0; i < levelList.size(); i++) {
            Leveldto levelDto = levelList.get(i);
            PPAProjectionDTO dto = new PPAProjectionDTO();
            setNullValue(dto, ridhtdto.getSingleColumns());
            dto.setLevelNo(levelDto.getLevelNo());
            dto.setHirarechyNo(levelDto.getHierarchyNo());
            hirarechyNos.add(levelDto.getHierarchyNo());
            dto.setLevelName(selection.getSessionDTO().getLevelValueDiscription(levelDto.getHierarchyNo(), levelDto.getHierarchyIndicator()));
            dto.setHirarechyName(levelDto.getLevel());
            result.add(dto);
        }
        finalList.add(result);
        finalList.add(hirarechyNos);
        return finalList;
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

        } else if (startQuater == 2) {
            startCal.set(Calendar.MONTH, 3);
        } else if (startQuater == 3) {
            startCal.set(Calendar.MONTH, 6);
        } else if (startQuater == 4) {
            startCal.set(Calendar.MONTH, 9);
        }
        if (endQuater == 1) {
            endCal.set(Calendar.DATE, 31);
            endCal.set(Calendar.MONTH, 2);
        } else if (endQuater == 2) {
            endCal.set(Calendar.DATE, 30);
            endCal.set(Calendar.MONTH, 5);
        } else if (endQuater == 3) {
            endCal.set(Calendar.DATE, 30);
            endCal.set(Calendar.MONTH, 8);
        } else if (endQuater == 4) {
            endCal.set(Calendar.DATE, 31);
            endCal.set(Calendar.MONTH, 11);
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
            int levelNo = selection.getLevelNo();
            selection.setIsCount(Boolean.FALSE);
            if (lastParent != null && (lastParent instanceof PPAProjectionDTO)) {
                PPAProjectionDTO dto = (PPAProjectionDTO) lastParent;
                levelNo = dto.getLevelNo();
                selection.setLevelNo(levelNo + 1);
                selection.setHierarchyNo(dto.getHirarechyNo());
                list = (List) getPPAProjectionResults(selection, rightDto, start, offset);
            } else if (!selection.isIsFilter()) {
                selection.setHierarchyNo(StringUtils.EMPTY);
                selection.setLevelNo(Integer.valueOf(selection.getCustomerLevelNo()));
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

    public int getPPAProjectionResultsCount(ProjectionSelectionDTO selection, Object lastParent, SessionDTO session, int i, int i0, CustomTableHeaderDTO rightDto) {
        int count = 0;
        try {
            if (lastParent != null && (lastParent instanceof PPAProjectionDTO) || (selection.isIsFilter())) {
                if (selection.isIsFilter()) {
                    count = configureLevelsCount(selection.getLevelNo(), selection, StringUtils.EMPTY, Boolean.TRUE, Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY, Boolean.FALSE);
                } else {
                    PPAProjectionDTO dto = (PPAProjectionDTO) lastParent;
                    selection.setLevelNo(dto.getLevelNo() + 1);
                    count = configureLevelsCount(selection.getLevelNo(), selection, dto.getHirarechyNo(), Boolean.FALSE, Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY, Boolean.FALSE);

                }

            } else {
                selection.setLevelNo(Integer.valueOf(session.getCustomerLevelNumber()));
                count = configureLevelsCount(selection.getLevelNo(), selection, StringUtils.EMPTY, Boolean.FALSE, Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY, Boolean.FALSE);
            }

            selection.setLevelCount(count);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        return count;
    }

    private void printselection(ProjectionSelectionDTO selection) {

    }

    public int configureLevelsCount(int levelNo, ProjectionSelectionDTO selection, String hierarchyNo, boolean isFilter, String parentHierarchyInd, boolean isCustom) {

        return CommonLogic.getLevelListCount(selection.getProjectionId(), parentHierarchyInd, levelNo, hierarchyNo, StringUtils.EMPTY, StringUtils.EMPTY, isFilter, Boolean.FALSE, 0, selection.getGroupFilter(), selection.getUserId(), selection.getSessionId(), selection.getCustRelationshipBuilderSid(), selection.getProdRelationshipBuilderSid());

    }

    public void saveAllcheckRecord(boolean checked, SessionDTO session, String group) {
        List input = new ArrayList();
        if (checked) {
            input.add(1);
        } else {
            input.add(0);
        }
        input.add(session.getProjectionId());
        input.add(session.getSessionId());
        input.add(session.getUserId());
        input.add(group);
        PPAQuerys.PPAUpdate(input, "PPA.saveAllCheckRecord");

    }

    public static Boolean nonMandatedPPAProjectionInsert(final int projectionId, final int userId, final int sessionId) {

        Object[] input = {String.valueOf(projectionId), String.valueOf(userId), String.valueOf(sessionId)};
        CommonLogic.callProcedure("PRC_NM_PPA_INSERT", input);

        return Boolean.TRUE;
    }

    public static Boolean nonMandatedPPAProjectionCalculate(final int projectionId, final int userId, final int sessionId) {
        PPAProjection.waitForSave();
        runnableJob = new RunnableJob(projectionId, userId, sessionId, Constant.PROCEDURE_CALL);
        procedureThread = new Thread(runnableJob);

        procedureThread.start();
        return Boolean.TRUE;

    }

    public static void waitForPPAProcedure() {
        LOGGER.info("Inside  waitForProcedure Method");
        if (procedureThread != null) {
            synchronized (procedureThread) {
                if (procedureThread.isAlive()) {

                    try {
                        runnableJob.wait();
                    } catch (InterruptedException e) {
                        LOGGER.error(e.getMessage());
                    }
                }
            }
        }
        LOGGER.info("End of  waitForProcedure  Method");
    }

    private Object getListToString(Collection hirarechyNos) {
        StringBuilder result = new StringBuilder();
        if (hirarechyNos != null && !hirarechyNos.isEmpty()) {
            for (Object hirarechyNo : hirarechyNos) {
                result.append("'" + hirarechyNo + "' ,");
            }
            result.deleteCharAt(result.length() - 1);
            return result.toString();
        }
        return "''";
    }

    private List getSingleParent(Object lastParent, List<Leveldto> levelList) throws CloneNotSupportedException {
        List result = new ArrayList();
        if (lastParent instanceof PPAProjectionDTO) {
            PPAProjectionDTO lastPrent1 = (PPAProjectionDTO) lastParent;
            PPAProjectionDTO dto = (PPAProjectionDTO) lastPrent1.clone();
            for (int i = 0; i < levelList.size(); i++) {
                Leveldto levelDto = levelList.get(i);
                dto.setLevelNo(levelDto.getLevelNo());
                dto.setHirarechyNo(levelDto.getHierarchyNo());
                dto.setLevelName(levelDto.getRelationshipLevelValue());
                dto.setHirarechyName(levelDto.getLevel());
            }
            result.add(dto);
        }
        return result;
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
        int quater = Integer.valueOf(obj[3].toString());
        int year = Integer.valueOf(obj[4].toString());
        List<String> commonCol = HeaderUtils.getCommonColumnHeader(selection.getFrequencyDivision(), year, quater);
        int pIndex = selection.getPeriodList().indexOf(commonCol.get(0));
        if (pIndex > -1) {
            int startIndex = (pIndex * selection.getPpaSelectedVariables().size());
            startIndex = startIndex + (selection.isExcelExport() ? 0 : 1);

            addPropertyValue(obj[13], dto, Constant.PPAColumns.NEP.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.NEP.getConstant()), commonCol.get(0), false);

            addPropertyValue(obj[16], dto, Constant.PPAColumns.BASE_PRICE_MANUAL.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.BASE_PRICE_MANUAL.getConstant()), commonCol.get(0), false);
            addPropertyValue(obj[17], dto, Constant.PPAColumns.BASE_PRICE_DATE.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.BASE_PRICE_DATE.getConstant()), commonCol.get(0), true);

            addPropertyValue(obj[27], dto, Constant.PPAColumns.PRICE_TOLERANCE.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.PRICE_TOLERANCE.getConstant()), commonCol.get(0), false);
            addPropertyValue(obj[28], dto, Constant.PPAColumns.MAX_INCREMENTAL_CHANGE.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.MAX_INCREMENTAL_CHANGE.getConstant()), commonCol.get(0), false);
            addPropertyValue(obj[31], dto, Constant.PPAColumns.RESET_DATE.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.RESET_DATE.getConstant()), commonCol.get(0), true);

            addPropertyValue(obj[39], dto, Constant.PPAColumns.ATTACHED_DATE.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.ATTACHED_DATE.getConstant()), commonCol.get(0), true);

            addHelperListValue(String.valueOf(obj[40]), dto, Constant.PPAColumns.PRICE_PROTECTION_PRICE_TYPE.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.PRICE_PROTECTION_PRICE_TYPE.getConstant()), commonCol.get(0));

            addHelperListValue(String.valueOf(obj[42]), dto, Constant.PPAColumns.BASE_PRICE_TYPE.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.BASE_PRICE_TYPE.getConstant()), commonCol.get(0));
            addHelperListValue(String.valueOf(obj[43]), dto, Constant.PPAColumns.BASE_PRICE_DATE.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.BASE_PRICE_DATE.getConstant()), commonCol.get(0));
            addHelperListValue(String.valueOf(obj[44]), dto, Constant.PPAColumns.BASE_PRICE_PRICE_TYPE.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.BASE_PRICE_PRICE_TYPE.getConstant()), commonCol.get(0));
            addHelperListValue(String.valueOf(obj[45]), dto, Constant.PPAColumns.NET_BASE_PRICE.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.NET_BASE_PRICE.getConstant()), commonCol.get(0));

            addHelperListValue(String.valueOf(obj[47]), dto, Constant.PPAColumns.SUBSEQUENT_PERIOD_PRICE_TYPE.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.SUBSEQUENT_PERIOD_PRICE_TYPE.getConstant()), commonCol.get(0));
            addHelperListValue(String.valueOf(obj[48]), dto, Constant.PPAColumns.NET_SUBSEQUENT_PERIOD_PRICE.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.NET_SUBSEQUENT_PERIOD_PRICE.getConstant()), commonCol.get(0));

            addHelperListValue(String.valueOf(obj[50]), dto, Constant.PPAColumns.PRICE_TOLERANCE_INTERVAL.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.PRICE_TOLERANCE_INTERVAL.getConstant()), commonCol.get(0));
            addHelperListValue(String.valueOf(obj[51]), dto, Constant.PPAColumns.PRICE_TOLERANCE_FREQUENCY.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.PRICE_TOLERANCE_FREQUENCY.getConstant()), commonCol.get(0));
            addHelperListValue(String.valueOf(obj[52]), dto, Constant.PPAColumns.PRICE_TOLERANCE_TYPE.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.PRICE_TOLERANCE_TYPE.getConstant()), commonCol.get(0));
            addHelperListValue(String.valueOf(obj[53]), dto, Constant.PPAColumns.PRICE_TOLERANCE.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.PRICE_TOLERANCE.getConstant()), commonCol.get(0));
            addHelperListValue(String.valueOf(obj[54]), dto, Constant.PPAColumns.RESET_ELIGIBLE.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.RESET_ELIGIBLE.getConstant()), commonCol.get(0));
            addHelperListValue(String.valueOf(obj[55]), dto, Constant.PPAColumns.RESET_TYPE.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.RESET_TYPE.getConstant()), commonCol.get(0));
            addHelperListValue(String.valueOf(obj[56]), dto, Constant.PPAColumns.RESET_DATE.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.RESET_DATE.getConstant()), commonCol.get(0));
            addHelperListValue(String.valueOf(obj[57]), dto, Constant.PPAColumns.RESET_INTERVAL.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.RESET_INTERVAL.getConstant()), commonCol.get(0));
            addHelperListValue(String.valueOf(obj[58]), dto, Constant.PPAColumns.RESET_FREQUENCY.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.RESET_FREQUENCY.getConstant()), commonCol.get(0));
            addHelperListValue(String.valueOf(obj[59]), dto, Constant.PPAColumns.RESET_PRICE_TYPE.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.RESET_PRICE_TYPE.getConstant()), commonCol.get(0));
            addHelperListValue(String.valueOf(obj[60]), dto, Constant.PPAColumns.NET_RESET_PRICE_TYPE.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.NET_RESET_PRICE_TYPE.getConstant()), commonCol.get(0));

            addHelperListValue(String.valueOf(obj[62]), dto, Constant.PPAColumns.NET_PRICE_TYPE.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.NET_PRICE_TYPE.getConstant()), commonCol.get(0));
            addPropertyValue(String.valueOf(obj[46]).equals(Constant.STRING_ONE) ? StringUtils.EMPTY : obj[64], dto, Constant.PPAColumns.NET_BASE_PRICE_FORMULA.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.NET_BASE_PRICE_FORMULA.getConstant()), commonCol.get(0), false);
            addPropertyValue(String.valueOf(obj[41]).equals(Constant.STRING_ONE) ? StringUtils.EMPTY : obj[65], dto, Constant.PPAColumns.NEP_FORMULA.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.NEP_FORMULA.getConstant()), commonCol.get(0), false);
            addPropertyValue(String.valueOf(obj[49]).equals(Constant.STRING_ONE) ? StringUtils.EMPTY : obj[66], dto, Constant.PPAColumns.NET_SUBSEQUENT_PERIOD_PRICE_FORMULA.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.NET_SUBSEQUENT_PERIOD_PRICE_FORMULA.getConstant()), commonCol.get(0), false);
            addPropertyValue(String.valueOf(obj[63]).equals(Constant.STRING_ONE) ? StringUtils.EMPTY : obj[67], dto, Constant.PPAColumns.NET_PRICE_TYPE_FORMULA.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.NET_PRICE_TYPE_FORMULA.getConstant()), commonCol.get(0), false);
            addPropertyValue(String.valueOf(obj[61]).equals(Constant.STRING_ONE) ? StringUtils.EMPTY : obj[68], dto, Constant.PPAColumns.NET_RESET_PRICE_FORMULA.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.NET_RESET_PRICE_FORMULA.getConstant()), commonCol.get(0), false);
            addHelperListValue(String.valueOf(obj[41]), dto, Constant.PPAColumns.NEP_FORMULA.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.NEP_FORMULA.getConstant()), commonCol.get(0));
            addHelperListValue(String.valueOf(obj[46]), dto, Constant.PPAColumns.NET_BASE_PRICE_FORMULA.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.NET_BASE_PRICE_FORMULA.getConstant()), commonCol.get(0));
            addHelperListValue(String.valueOf(obj[49]), dto, Constant.PPAColumns.NET_SUBSEQUENT_PERIOD_PRICE_FORMULA.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.NET_SUBSEQUENT_PERIOD_PRICE_FORMULA.getConstant()), commonCol.get(0));
            addHelperListValue(String.valueOf(obj[61]), dto, Constant.PPAColumns.NET_RESET_PRICE_FORMULA.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.NET_RESET_PRICE_FORMULA.getConstant()), commonCol.get(0));
            addHelperListValue(String.valueOf(obj[63]), dto, Constant.PPAColumns.NET_PRICE_TYPE_FORMULA.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.NET_PRICE_TYPE_FORMULA.getConstant()), commonCol.get(0));
            addCustomPropertyValue(obj[12], dto, Constant.PPAColumns.PRICE_PROTECTION_PRICE_TYPE.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.PRICE_PROTECTION_PRICE_TYPE.getConstant()), commonCol.get(0), selection.isExcelExport());
            addCustomPropertyValue(obj[15], dto, Constant.PPAColumns.BASE_PRICE_TYPE.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.BASE_PRICE_TYPE.getConstant()), commonCol.get(0), selection.isExcelExport());
            addCustomPropertyValue(obj[18], dto, Constant.PPAColumns.BASE_PRICE_PRICE_TYPE.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.BASE_PRICE_PRICE_TYPE.getConstant()), commonCol.get(0), selection.isExcelExport());
            addCustomPropertyValue(obj[19], dto, Constant.PPAColumns.NET_BASE_PRICE.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.NET_BASE_PRICE.getConstant()), commonCol.get(0), selection.isExcelExport());
            addCustomPropertyValue(obj[21], dto, Constant.PPAColumns.SUBSEQUENT_PERIOD_PRICE_TYPE.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.SUBSEQUENT_PERIOD_PRICE_TYPE.getConstant()), commonCol.get(0), selection.isExcelExport());
            addCustomPropertyValue(obj[22], dto, Constant.PPAColumns.NET_SUBSEQUENT_PERIOD_PRICE.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.NET_SUBSEQUENT_PERIOD_PRICE.getConstant()), commonCol.get(0), selection.isExcelExport());
            addCustomPropertyValue(obj[24], dto, Constant.PPAColumns.PRICE_TOLERANCE_INTERVAL.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.PRICE_TOLERANCE_INTERVAL.getConstant()), commonCol.get(0), selection.isExcelExport());
            addCustomPropertyValue(obj[25], dto, Constant.PPAColumns.PRICE_TOLERANCE_FREQUENCY.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.PRICE_TOLERANCE_FREQUENCY.getConstant()), commonCol.get(0), selection.isExcelExport());
            addCustomPropertyValue(obj[26], dto, Constant.PPAColumns.PRICE_TOLERANCE_TYPE.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.PRICE_TOLERANCE_TYPE.getConstant()), commonCol.get(0), selection.isExcelExport());
            addCustomPropertyValue(obj[29], dto, Constant.PPAColumns.RESET_ELIGIBLE.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.RESET_ELIGIBLE.getConstant()), commonCol.get(0), selection.isExcelExport());
            addCustomPropertyValue(obj[30], dto, Constant.PPAColumns.RESET_TYPE.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.RESET_TYPE.getConstant()), commonCol.get(0), selection.isExcelExport());
            addCustomPropertyValue(obj[32], dto, Constant.PPAColumns.RESET_INTERVAL.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.RESET_INTERVAL.getConstant()), commonCol.get(0), selection.isExcelExport());
            addCustomPropertyValue(obj[33], dto, Constant.PPAColumns.RESET_FREQUENCY.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.RESET_FREQUENCY.getConstant()), commonCol.get(0), selection.isExcelExport());
            addCustomPropertyValue(obj[34], dto, Constant.PPAColumns.RESET_PRICE_TYPE.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.RESET_PRICE_TYPE.getConstant()), commonCol.get(0), selection.isExcelExport());
            addCustomPropertyValue(obj[35], dto, Constant.PPAColumns.NET_RESET_PRICE_TYPE.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.NET_RESET_PRICE_TYPE.getConstant()), commonCol.get(0), selection.isExcelExport());
            addCustomPropertyValue(obj[37], dto, Constant.PPAColumns.NET_PRICE_TYPE.getConstant(), startIndex, selection.getPpaSelectedVariables().indexOf(Constant.PPAColumns.NET_PRICE_TYPE.getConstant()), commonCol.get(0), selection.isExcelExport());

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
        if (Constant.STRING_ONE.equals(dto.getPropertyValue(Constant.Q_SMALL + quater + year + columnName + Constant._FLAG))) {
            dto.addIntegerProperties(createColumn(quater, year, columnName), -1);
        } else if (!isExcel) {
            id = cutomizeIntegerObject(obj);
            dto.addIntegerProperties(createColumn(quater, year, columnName), id);
        } else {
            SaveDTO dto1 = new SaveDTO();
            String propertyId = dto1.getPropertyId();
            String customizedPropertyId = StringUtils.EMPTY;

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

    public String getPriceTypeNameByID(final Integer priceType) throws SystemException {

        String retval = StringUtils.EMPTY;
        List<Object> list = new ArrayList<Object>();
        if (Integer.valueOf(priceType) != null && !Integer.valueOf(priceType).equals(0)) {

            String sqlQuery = "SELECT * FROM HELPER_TABLE WHERE HELPER_TABLE_SID =" + priceType;
            list = HelperTableLocalServiceUtil.executeSelectQuery(sqlQuery.toString());
            Object[] value = (Object[]) list.get(0);
            retval = value[1].toString();
        } else {
            retval = StringUtils.EMPTY;
        }
        return retval;
    }
}
