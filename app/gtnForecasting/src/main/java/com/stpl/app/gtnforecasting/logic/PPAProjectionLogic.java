
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.logic;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionList;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.gtnforecasting.dao.PPAProjectionDao;
import com.stpl.app.gtnforecasting.dao.impl.PPAProjectionDaoImpl;
import com.stpl.app.gtnforecasting.dto.PPAProjectionDTO;
import com.stpl.app.gtnforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.dto.RSFormulaDTO;
import com.stpl.app.gtnforecasting.dto.SaveDTO;
import com.stpl.app.gtnforecasting.queryUtils.PPAQuerys;
import com.stpl.app.gtnforecasting.queryUtils.QueryReader;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import static com.stpl.app.gtnforecasting.utils.Constant.DASH;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.ItemPricingQualifierLocalServiceUtil;
import com.stpl.app.serviceUtils.ConstantsUtils;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.stpl.ifs.util.HelperDTO;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.filter.SimpleStringFilter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtListDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author srithar
 */
public class PPAProjectionLogic {

    private static final Logger LOGGER = LoggerFactory.getLogger(PPAProjectionLogic.class);
    private static Thread procedureThread;

    private static RunnableJob runnableJob;
    private static final PPAProjectionDao DAO = new PPAProjectionDaoImpl();
    private static final Map<String, String> columnHeaderMap = Constant.getColumnHeaderMap();
    private static final Map<String, List<String>> populateIdentifier = Constant.getPopulateIdentifier();
    private static final Map<String, String> dbColumnIdentifier = Constant.getDatabaseColumnIdentifier();

    private static List getInputForMassUpdate(int startQuater, int endQuater, int startYear, int endYear, Object priceCap, ProjectionSelectionDTO selection) {
        List input = new ArrayList();
        List startAndEndDate = getStartAndEndDate(startQuater, endQuater, startYear, endYear);
        input.add(Double.valueOf(priceCap.toString()));
        input.add(selection.getRelationshipBuilderSid());
        if (selection.getGroupFilter() != Constant.ALL_GROUP) {
            input.add(selection.getGroupFilter().replace(Constant.PPA, StringUtils.EMPTY));
        } else {
            input.add(Constant.PERCENT);
        }
        input.add(CommonUtils.DBDate.format(startAndEndDate.get(0)));
        input.add(CommonUtils.DBDate.format(startAndEndDate.get(1)));
        return input;

    }

    private static List getInputForMassUpdateGroup(Object usergroup, ProjectionSelectionDTO selection) {
        String ccpQuery = new CommonLogic().insertAvailableHierarchyNo(selection);
        List input = new ArrayList();
        input.add(ccpQuery);
        input.add(usergroup);
        input.add(selection.getRelationshipBuilderSid());
        if (selection.getGroupFilter() != Constant.ALL_GROUP) {
            input.add(selection.getGroupFilter().replace(Constant.PPA, StringUtils.EMPTY));
        } else {
            input.add(Constant.PERCENT);
        }
        return input;
    }

    private static List getInputForSaveLeftTable(int projectionId, Object columnValue, String hirarechyNo, String group, ProjectionSelectionDTO selection, String columnName) {
        List input = new ArrayList();
        String ccpQuery = new CommonLogic().insertAvailableHierarchyNo(selection);
        input.add(ccpQuery);
        if (columnValue instanceof HelperDTO) {
            input.add(((HelperDTO) columnValue).getId());
        } else if (columnValue instanceof Date) {
            input.add("CONVERT(DATE, '" + CommonUtils.getDateTime(Constant.DATE_FORMAT_1, (Date) columnValue) + "')");
        } else {
            input.add("'" + String.valueOf(columnValue) + "'");
        }
        input.add(selection.getRelationshipBuilderSid());
        input.add(projectionId);
        input.add(hirarechyNo);
        input.add(group);
        input.add(columnName);

        return input;
    }

    public void massUpdatePPAProjection(Object priceCap, int startQuater, int endQuater, int startYear, int endYear, ProjectionSelectionDTO selection) {
        List input = null;
        if (priceCap instanceof Double) {
            input = getInputForMassUpdate(startQuater, endQuater, startYear, endYear, priceCap, selection);
            PPAQuerys.PPAUpdate(input, "PPA.MAssUpdate-PriceCap");
        } else if (priceCap instanceof String) {
            input = getInputForMassUpdateGroup(priceCap, selection);
            PPAQuerys.PPAUpdate(input, "PPA.MAssUpdate-Group");
        }
    }

    /**
     * Load result grid while Tab change.
     *
     * @param selection
     * @param ridhtdto
     * @param start
     * @param offset
     * @return
     * @throws com.liferay.portal.kernel.exception.PortalException
     */
    public Object getPPAProjectionResults(ProjectionSelectionDTO selection, CustomTableHeaderDTO ridhtdto, int start, int offset) {
        List list = null;
        printselection();
        List<Object> finalList = (List) configureLevel(selection, start, offset, selection.getHierarchyNo(), ridhtdto);
        List<PPAProjectionDTO> PPAList = (List<PPAProjectionDTO>) finalList.get(0);
        list = getPPAProjectionList(selection, selection.getHierarchyNo(), finalList);
        list = getCustomizedPPA(list, selection, ridhtdto, PPAList);
        return list;
    }

    private List getPPAProjectionList(ProjectionSelectionDTO selection, String hirarechyNo, List<Object> finalList) {
        List<Object> input = new ArrayList<>();
        String queryName = "PPAGenerate";
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
        input.add(getListToString((Collection) finalList.get(1)));
        return PPAQuerys.getPPAData(input, queryName, null);

    }

    private List<PPAProjectionDTO> getCustomizedPPA(List<Object[]> list, ProjectionSelectionDTO selection, CustomTableHeaderDTO ridhtdto, List<PPAProjectionDTO> dtoList) {
        LOGGER.debug("Inside getcustomized PPA");
        try {
            List<PPAProjectionDTO> result = new ArrayList<>();
            List<Object> visibleColumn = ridhtdto.getSingleColumns();
            PPAProjectionDTO dto = null;
            int levelNo = selection.getLevelNo();
            for (int i = 0; i < list.size(); i++) {
                Object[] str = list.get(i);
                if (str[0].toString().equalsIgnoreCase(String.valueOf(levelNo))) {
                    if (i == 0) {
                        dto = setdata(str, visibleColumn, dtoList);
                    } else if (i > 0 && !dto.getHirarechyNo().equals(str[NumericConstants.ELEVEN])) {
                        result.add(dto);
                        dto = setdata(str, visibleColumn, dtoList);
                    } else {
                        String quater = str[NumericConstants.THREE].toString();
                        String year = str[NumericConstants.FOUR].toString();
                        int intQuarter = Integer.parseInt(quater);
                        int intYear = Integer.parseInt(year);
                        int endQuater = (selection.getForecastDTO().getProjectionEndMonth() / NumericConstants.THREE) + 1;
                        int endYear = selection.getForecastDTO().getProjectionEndYear();
                        boolean flag = Boolean.FALSE;

                        if (intYear < endYear) {
                            flag = Boolean.TRUE;
                        } else if (intYear == endYear && intQuarter <= endQuater) {
                            flag = Boolean.TRUE;
                        }
                        if ((isColumn(visibleColumn, quater, year, Constant.PRICECAP)) && (str[NumericConstants.EIGHT].toString() != null || !str[NumericConstants.EIGHT].equals(Constant.NULL))) {
                            if (flag) {
                                dto.addStringProperties(Constant.Q_SMALL + quater + year + Constant.PRICECAP, CommonUtils.PERCENTFORMAT.format(Double.parseDouble(str[NumericConstants.EIGHT].toString()) / NumericConstants.HUNDRED));
                            } else {
                                dto.addStringProperties(Constant.Q_SMALL + quater + year + Constant.PRICECAP, Constant.DASH_NO_DATA);
                            }
                        }
                        if ((isColumn(visibleColumn, quater, year, Constant.RESET_SMALL)) && (flag)) {
                            int value = Integer.valueOf((str[NumericConstants.NINE] == null) ? DASH : str[NumericConstants.NINE].toString());
                            dto.addBooleanProperties(Constant.Q_SMALL + quater + year + Constant.RESET_SMALL, value >= dto.getCCPCount());

                        }
                    }

                }
            }
        } catch (NumberFormatException e) {
            LOGGER.error(e.getMessage());
        }

        LOGGER.debug("End of CustomizedPPA");
        return dtoList;
    }

    private PPAProjectionDTO setdata(Object[] str, List<Object> visibleColumn, List<PPAProjectionDTO> dtoList) {
        LOGGER.debug("Inside setdata");
        PPAProjectionDTO dto = getDtoFromList(dtoList, str[NumericConstants.ELEVEN]);
        if (dto != null) {
            String quater = str[NumericConstants.THREE].toString();
            String year = str[NumericConstants.FOUR].toString();
            dto.setPriceBasis(str[NumericConstants.SEVEN] != null ? str[NumericConstants.SEVEN].toString() : "-");

            dto.setActualPriceCap(str[NumericConstants.SIX] != null ? CommonUtils.PERCENTFORMAT.format(Double.parseDouble(str[NumericConstants.SIX].toString()) / NumericConstants.HUNDRED) : CommonUtils.PERCENTFORMAT.format(Double.valueOf(Constant.DASH)));
            if (str[NumericConstants.TWELVE] != null && str[NumericConstants.THIRTEEN] != null) {
                if (str[NumericConstants.TWELVE].equals(str[NumericConstants.THIRTEEN])) {
                    dto.addBooleanProperties(Constant.CHECK_RECORD, Integer.parseInt(str[NumericConstants.TWELVE].toString()) > 0);
                }
                dto.setCheckRecordCount(Integer.valueOf(str[NumericConstants.TWELVE].toString()));
            }
            if (str[NumericConstants.THIRTEEN] != null) {
                dto.setCCPCount(Integer.valueOf(str[NumericConstants.THIRTEEN].toString()));

            }
            if (dto.getHirarechyName().equals(Constant.TRADING_PARTNER)) {
                dto.setGroup((str[NumericConstants.FIVE] == null) ? DASH : str[NumericConstants.FIVE].toString());
            }
            if (isColumn(visibleColumn, quater, year, Constant.PRICECAP)) {
                if (str[NumericConstants.EIGHT].toString() == null && str[NumericConstants.EIGHT].equals(Constant.NULL)) {
                    dto.addStringProperties(Constant.Q_SMALL + quater + year + Constant.PRICECAP, CommonUtils.PERCENTFORMAT.format(Double.valueOf(Constant.DASH)));
                } else {
                    dto.addStringProperties(Constant.Q_SMALL + quater + year + Constant.PRICECAP, CommonUtils.PERCENTFORMAT.format(Double.parseDouble(str[NumericConstants.EIGHT].toString()) / NumericConstants.HUNDRED));
                }
            }
            if (isColumn(visibleColumn, quater, year, Constant.RESET_SMALL)) {
                int value = Integer.valueOf((str[NumericConstants.NINE] == null) ? DASH : str[NumericConstants.NINE].toString());
                dto.addBooleanProperties(Constant.Q_SMALL + quater + year + Constant.RESET_SMALL, value >= dto.getCCPCount());
            }
        }
        LOGGER.debug("End of setdata");
        return dto;
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

                dto.addStringProperties(obj, CommonUtils.PERCENTFORMAT.format(Double.valueOf(Constant.DASH)));
            } else if (obj.toString().equals(Constant.GROUP)) {
                dto.addStringProperties(obj, DASH);
            }

        }
        dto.addBooleanProperties(Constant.CHECK_RECORD, Boolean.FALSE);
    }

    public static Boolean savePPAProjection(int projectionId, SaveDTO dto, ProjectionSelectionDTO selection) {
        try {
            List input;
            String tempProId = ExtListDTO.getPropertyId(dto.getPropertyId());
            String propertyId = tempProId;
            String columnName;
            String customizedPropertyId;
            if (Constant.RIGHT.equals(dto.getTable())) {
                customizedPropertyId = propertyId.substring(propertyId.indexOf('q') + NumericConstants.SIX);
                columnName = dbColumnIdentifier.get(columnHeaderMap.get(customizedPropertyId));
                if (populateIdentifier.get(Constant.DDLB_FIELD).contains(columnHeaderMap.get(customizedPropertyId))
                        || populateIdentifier.get(Constant.TEXT_FIELD).contains(columnHeaderMap.get(customizedPropertyId))
                        || populateIdentifier.get(Constant.DATE_FEILD).contains(columnHeaderMap.get(customizedPropertyId))
                        || populateIdentifier.get(Constant.LOOKUP_FIELD).contains(columnHeaderMap.get(customizedPropertyId))) {
                    String freq = String.valueOf(propertyId.charAt(1));
                    String year = String.valueOf(propertyId.substring(NumericConstants.TWO, NumericConstants.SIX));
                    input = getInputForSaveRightTable(projectionId, dto, dto.getValue(), freq, year, selection, columnName);
                    PPAQuerys.ppaUpdateQuery(input, "PPA.saveRightTableFields");
                }
            } else {
                columnName = dbColumnIdentifier.get(columnHeaderMap.get(propertyId));
                if (populateIdentifier.get(Constant.FROZEN_FIELDS).contains(columnHeaderMap.get(propertyId))) {
                    input = getInputForSaveLeftTable(projectionId, dto.getValue(), dto.getHirarechyNo(), dto.getGroup(), selection, columnName);
                    PPAQuerys.ppaUpdateQuery(input, "PPA.saveLeftTableFields");
                } else if (propertyId.contains(Constant.CHECK_RECORD)) {
                    if ((dto.getValue() != null) && (Boolean.parseBoolean(dto.getValue().toString()))) {
                        input = getInputForSaveCheckRecord(projectionId, dto, Constant.STRING_ONE, selection);
                    } else {
                        input = getInputForSaveCheckRecord(projectionId, dto, DASH, selection);
                    }
                    PPAQuerys.PPAUpdate(input, "PPA.saveCheckRecord");
                }
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return true;

    }

    public static List getInputForSaveRightTable(int projectionId, SaveDTO dto, Object columnValue, String freq, String year, ProjectionSelectionDTO selection, String columnName) {
        List input = new ArrayList();
        String ccpQuery = new CommonLogic().insertAvailableHierarchyNo(selection);
        input.add(ccpQuery);
        if (columnValue instanceof HelperDTO) {
            input.add(((HelperDTO) columnValue).getId());
        } else if (columnValue instanceof Date) {
            input.add("CONVERT(DATE, '" + CommonUtils.getDateTime(Constant.DATE_FORMAT_1, (Date) columnValue) + "')");
        } else if (Constant.NULL.equalsIgnoreCase(String.valueOf(columnValue))) {
            input.add(Constant.NULL_CAPS);
        } else {
            input.add("'" + String.valueOf(columnValue) + "'");
        }
        input.add(selection.getRelationshipBuilderSid());
        input.add(projectionId);
        input.add(dto.getHirarechyNo());
        input.add(dto.getGroup());
        input.add(freq);
        input.add(year);
        input.add(columnName);

        return input;
    }

    private static List getInputForSaveCheckRecord(int projectionId, SaveDTO dto, String value, ProjectionSelectionDTO selection) {
        String ccpQuery = new CommonLogic().insertAvailableHierarchyNo(selection);
        List input = new ArrayList();
        input.add(ccpQuery);
        input.add(value);
        input.add(selection.getRelationshipBuilderSid());
        input.add(projectionId);
        input.add(dto.getHirarechyNo());
        input.add(dto.getGroup());
        return input;
    }

    private Object configureLevel(ProjectionSelectionDTO selection, int start, int offset, String hirarechyNo, CustomTableHeaderDTO ridhtdto) {
        List result = new ArrayList();
        List finalList = new ArrayList();
        Set<String> hirarechyNos = new HashSet<>();
        List<Leveldto> levelList = CommonLogic.getConditionalLevelList(selection.getProjectionId(), Constant.PPA, start, offset, selection.getHierarchyIndicator(), selection.getLevelNo(), hirarechyNo, selection.getProductHierarchyNo(), selection.getCustomerHierarchyNo(), selection.isIsFilter(), false, selection.isIsCustomHierarchy(), selection.getCustomId(), selection.getGroupFilter(), selection.getUserId(), selection.getSessionId(), selection.getCustRelationshipBuilderSid(), selection.getProdRelationshipBuilderSid(), false, true, selection.getDiscountNoList(), selection);
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

    public int getPPAProjectionResultsCount(ProjectionSelectionDTO selection, Object lastParent, SessionDTO session) {
        int count = 0;
        try {
            if (lastParent != null && (lastParent instanceof PPAProjectionDTO) || (selection.isIsFilter())) {
                if (selection.isIsFilter()) {
                    count = configureLevelsCount(selection.getLevelNo(), selection, StringUtils.EMPTY, Boolean.TRUE, Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY);
                } else {
                    PPAProjectionDTO dto = (PPAProjectionDTO) lastParent;
                    selection.setLevelNo(dto.getLevelNo() + 1);
                    count = configureLevelsCount(selection.getLevelNo(), selection, dto.getHirarechyNo(), Boolean.FALSE, Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY);

                }

            } else {
                selection.setLevelNo(Integer.parseInt(session.getCustomerLevelNumber()));
                count = configureLevelsCount(selection.getLevelNo(), selection, StringUtils.EMPTY, Boolean.FALSE, Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY);
            }
            selection.setLevelCount(count);
        } catch (NumberFormatException ex) {
            LOGGER.error(ex.getMessage());
        }
        return count;
    }

    private void printselection() {
        return;
    }

    public int configureLevelsCount(int levelNo, ProjectionSelectionDTO selection, String hierarchyNo, boolean isFilter, String parentHierarchyInd) {

        return CommonLogic.getLevelListCount(selection.getProjectionId(), Constant.PPA, parentHierarchyInd, levelNo, hierarchyNo, StringUtils.EMPTY, StringUtils.EMPTY, isFilter, Boolean.FALSE, 0, selection.getGroupFilter(), selection.getUserId(), selection.getSessionId(), selection.getCustRelationshipBuilderSid(), selection.getProdRelationshipBuilderSid(), selection.getDiscountNoList(), selection);

    }

    public void saveAllcheckRecord(boolean checked, SessionDTO session, String group) {
        List input = new ArrayList();
        if (checked) {
            input.add(1);
        } else {
            input.add(0);
        }
        input.add(session.getProjectionId());
        input.add(group);
        PPAQuerys.PPAUpdate(input, "PPA.saveAllCheckRecord");

    }

    public static Boolean nonMandatedPPAProjectionInsert(final int projectionId, final int userId, final int sessionId) {

        DataSource datasource = null;
        try {
            Context initialContext = new InitialContext();
            datasource = (DataSource) initialContext.lookup("java:jboss/datasources/jdbc/appDataPool");
        } catch (NamingException ex) {
            LOGGER.debug("nonMandatedPPAProjectionInsert= {} " , ex);
        }
        if (datasource != null) {
            try (Connection connection = datasource.getConnection();
                    CallableStatement statement = connection.prepareCall("{call PRC_NM_PPA_INSERT(?,?,?)}")) {
                LOGGER.debug(" Executing Discount Insert procedure ");
                LOGGER.debug("Projection ID-->= {} " , projectionId);
                LOGGER.debug("User Id      -->= {} " , userId);
                LOGGER.debug("Session ID   -->= {} " , sessionId);
                statement.setInt(1, projectionId);
                statement.setInt(NumericConstants.TWO, userId);
                statement.setInt(NumericConstants.THREE, sessionId);
                statement.execute();

            } catch (SQLException ex) {
                LOGGER.error(ex.getMessage());
            }
        }
        return Boolean.TRUE;
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
                    }
                }
            }
        }
        LOGGER.debug("End of  waitForProcedure  Method");
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

    public static int getPriceTypeCount(final String filterText, final HelperDTO priceType) throws SystemException {
        final String filter = StringUtils.trimToEmpty(filterText) + Constant.PERCENT;
        LOGGER.debug("Entering getLazyPriceTypeCount method with filterText= {}" , filterText);
        List<Object[]> qualifierList;
        final DynamicQuery cfpDynamicQuery = ItemPricingQualifierLocalServiceUtil.dynamicQuery();
        if (priceType != null && priceType.getId() != 0) {
            cfpDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.ITEM_PRICING_QUALIFIER_SID, priceType.getId()));
        }
        cfpDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.ITEM_PRICING_QUAL_NAME, filter));
        cfpDynamicQuery.setProjection(ProjectionFactoryUtil.countDistinct(ConstantsUtils.ITEM_PRICING_QUALIFIER_SID));
        cfpDynamicQuery.add(RestrictionsFactoryUtil.isNotNull(ConstantsUtils.ITEM_PRICING_QUAL_NAME));
        cfpDynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.like(ConstantsUtils.ITEM_PRICING_QUAL_NAME, StringUtils.EMPTY)));
        qualifierList = DAO.getItemPricingTypeList(cfpDynamicQuery);
        LOGGER.debug("Ending getLazyPriceTypeCount method with filterText with count= {}" , qualifierList.get(0));
        return Integer.parseInt(String.valueOf(qualifierList.get(0)));
    }

    public static List<HelperDTO> getPriceTypeResults(final int startIndex, final int end, final String filter, final HelperDTO priceType) throws SystemException {
        final List<HelperDTO> list = new ArrayList<>();
        LOGGER.debug("Entering getLazyPriceTypeResults method with filterText= {}" , filter);
        final String filterString = StringUtils.trimToEmpty(filter) + Constant.PERCENT;
        final DynamicQuery cfpDynamicQuery = ItemPricingQualifierLocalServiceUtil.dynamicQuery();
        cfpDynamicQuery.setLimit(startIndex, end);
        if (priceType != null && priceType.getId() != 0) {
            cfpDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.ITEM_PRICING_QUALIFIER_SID, priceType.getId()));
        }
        final ProjectionList projectionList = ProjectionFactoryUtil.projectionList();
        projectionList.add(ProjectionFactoryUtil.property(ConstantsUtils.ITEM_PRICING_QUALIFIER_SID));
        projectionList.add(ProjectionFactoryUtil.property(ConstantsUtils.ITEM_PRICING_QUAL_NAME));
        cfpDynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.like(ConstantsUtils.ITEM_PRICING_QUAL_NAME, StringUtils.EMPTY)));
        cfpDynamicQuery.add(RestrictionsFactoryUtil.isNotNull(ConstantsUtils.ITEM_PRICING_QUAL_NAME));
        if (filter != null) {
            cfpDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.ITEM_PRICING_QUAL_NAME, filterString));
        }
        cfpDynamicQuery.addOrder(OrderFactoryUtil.asc(ConstantsUtils.ITEM_PRICING_QUAL_NAME));
        cfpDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(projectionList));
        final List<Object[]> returnList = DAO.getItemPricingTypeList(cfpDynamicQuery);

        HelperDTO helperTable;
        if (startIndex == ConstantsUtils.ZERO_INT) {
            helperTable = new HelperDTO();
            helperTable.setId(0);
            helperTable.setDescription(ConstantsUtils.SELECT_ONE);
            list.add(helperTable);
            if (priceType != null && priceType.getId() != 0 && filter == null) {
                list.add(priceType);
            }
        }
        for (final Iterator<Object[]> iterator = returnList.iterator(); iterator.hasNext();) {
            final Object[] value = iterator.next();
            helperTable = new HelperDTO();
            helperTable.setId(value[0] != null ? Integer.parseInt(value[0].toString()) : 0);
            helperTable.setDescription(value[1] != null ? value[1].toString() : StringUtils.EMPTY);
            if (!StringUtils.EMPTY.equals(helperTable.getDescription())) {
                list.add(helperTable);
            }
        }
        LOGGER.debug("Ending getLazyPriceTypeResults  return list size= {}" , list.size());
        return list;
    }

    /**
     *
     * @param rsFormulaDTO
     * @param start
     * @param offset
     * @param isCount
     * @return
     */
    public Object loadRSFormula(final RSFormulaDTO rsFormulaDTO, final int start, final int offset, final boolean isCount, final Set<Container.Filter> filterSet) {
        LOGGER.debug("Start-->= {}, and offset= {} " , start, offset);
        String query;

        if (isCount) {
            query = QueryReader.getQuery("FORMULA_LOOKUP_COUNT");
        } else {
            query = QueryReader.getQuery("FORMULA_LOOKUP_LOADDATA");
            query = query.replace("@OFFSET", String.valueOf(start)).replace("@START", String.valueOf(offset));
        }
        query = query.replace("@FORMULA_ID", StringUtils.isBlank(rsFormulaDTO.getFormulaID()) || Constant.NULL.equals(rsFormulaDTO.getFormulaID())
                ? Constant.PERCENT : replaceForWildCardSearch(rsFormulaDTO.getFormulaID()));
        query = query.replace("@FORMULA_NO", StringUtils.isBlank(rsFormulaDTO.getFormulaNo()) || Constant.NULL.equals(rsFormulaDTO.getFormulaNo())
                ? Constant.PERCENT : replaceForWildCardSearch(rsFormulaDTO.getFormulaNo()));
        query = query.replace("@FORMULA_NAME", StringUtils.isBlank(rsFormulaDTO.getFormulaName()) || Constant.NULL.equals(rsFormulaDTO.getFormulaName())
                ? Constant.PERCENT : replaceForWildCardSearch(rsFormulaDTO.getFormulaName()));
        query = query.replace("@FORMULA_TYPE", rsFormulaDTO.getFormulaType() == null || rsFormulaDTO.getFormulaType().getId() == 0 ? Constant.PERCENT : String.valueOf(rsFormulaDTO.getFormulaType().getId()));

        query = query.replace("@NEP_FILTER", Constant.PPAVariables.NEP_FORMULA.getConstant().equals(rsFormulaDTO.getPropertyId()) ? " AND formulaType.DESCRIPTION = 'Contract Deduction' " : StringUtils.EMPTY);
        String filterQuery = getFilterQuery(filterSet);
        query = StringUtils.isEmpty(filterQuery) ? query.replace(Constant.FILTERR, StringUtils.EMPTY) : query.replace(Constant.FILTERR, filterQuery);
        List resultList = (List) HelperTableLocalServiceUtil.executeSelectQuery(query);
        if (isCount) {
            int count = (Integer) resultList.get(0);
            return count;
        } else {
            return convertFormulaList(resultList);
        }

    }

    /**
     * Converts the list of objects to list of RSFormulaDTO.
     *
     * @param list List<Object[]>
     * @return List<RebatePlanDTO>
     */
    private List<RSFormulaDTO> convertFormulaList(final List<Object[]> list) {
        List<RSFormulaDTO> resultList = new ArrayList<>();
        for (Object[] object : list) {
            RSFormulaDTO rSFormulaDTO = new RSFormulaDTO();
            rSFormulaDTO.setForectastingFormulaSid(Integer.parseInt(String.valueOf(object[0])));
            rSFormulaDTO.setFormulaID(String.valueOf(object[1]));
            rSFormulaDTO.setFormulaNo(String.valueOf(object[NumericConstants.TWO]));
            rSFormulaDTO.setFormulaName(String.valueOf(object[NumericConstants.THREE]));
            rSFormulaDTO.setFormulaTypeId(Integer.parseInt(String.valueOf(object[NumericConstants.FOUR])));
            HelperDTO dto = new HelperDTO();
            dto.setId(Integer.parseInt(String.valueOf(object[NumericConstants.FOUR])));
            dto.setDescription(String.valueOf(object[NumericConstants.FIVE]));
            rSFormulaDTO.setFormulaType(dto);
            resultList.add(rSFormulaDTO);
        }
        return resultList;
    }

    private String replaceForWildCardSearch(String input) {
        if (StringUtils.isNotBlank(input)) {
            input = input.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
        }
        return input;
    }

    private String getFilterQuery(final Set<Container.Filter> filterSet) {
        HashMap<String, String> filterCriteria = new HashMap<>();
        filterCriteria.put(Constant.FORMULA_TYPE, "NET_SALES_FORMULA_TYPE");
        filterCriteria.put("formulaID", "NET_SALES_FORMULA_ID");
        filterCriteria.put("formulaNo", "NET_SALES_FORMULA_NO");
        filterCriteria.put("formulaName", "NET_SALES_FORMULA_NAME");
        filterCriteria.put("version", "NET_SALES_FORMULA_TYPE");
        filterCriteria.put(Constant.DEDUCTION_TYPE, "DEDUCTION_TYPE");
        filterCriteria.put(Constant.DEDUCTION_SUB_TYPE, "DEDUCTION_SUB_TYPE");
        filterCriteria.put(Constant.DEDUCTION_CATEGORY, "DEDUCTION_CATEGORY");
        filterCriteria.put(Constant.INDICATOR, "INDICATOR");
        String stringBuilder = StringUtils.EMPTY;
        if (filterSet != null) {
            for (Container.Filter filter : filterSet) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    stringBuilder += " AND " + filterCriteria.get(stringFilter.getPropertyId().toString()) + " LIKE '%" + stringFilter.getFilterString() + "%'";

                }
            }
        }
        return stringBuilder;
    }

    public Object loadDetailsTable(int forectastingFormulaSid, final int start, final int offset, final boolean isCount, final Set<Container.Filter> filterSet) {

        LOGGER.debug("Start-->= {}, and offset= {} " , start, offset);
        String query;

        if (isCount) {
            query = QueryReader.getQuery("FORMULA_DETAILS_COUNT");
        } else {
            query = QueryReader.getQuery("FORMULA_DETAILS_LOAD");
            query = query.replace("@OFFSET", String.valueOf(start)).replace("@START", String.valueOf(offset));
        }
        query = query.replace("@FORMULA_SID", String.valueOf(forectastingFormulaSid));
        String filterQuery = getFilterQuery(filterSet);
        query = StringUtils.isEmpty(filterQuery) ? query.replace(Constant.FILTERR, StringUtils.EMPTY) : query.replace(Constant.FILTERR, filterQuery);
        List resultList = (List) HelperTableLocalServiceUtil.executeSelectQuery(query);
        if (isCount) {
            int count = (Integer) resultList.get(0);
            return count;
        } else {
            return convertFormulaDetailsList(resultList);
        }
    }

    /**
     * Converts the list of objects to list of RSFormulaDTO.
     *
     * @param list List<Object[]>
     * @return List<RebatePlanDTO>
     */
    private List<RSFormulaDTO> convertFormulaDetailsList(final List<Object[]> list) {
        List<RSFormulaDTO> resultList = new ArrayList<>();
        for (Object[] object : list) {
            RSFormulaDTO rSFormulaDTO = new RSFormulaDTO();
            rSFormulaDTO.setDeductionType(String.valueOf(object[0]));
            rSFormulaDTO.setDeductionSubType(String.valueOf(object[1]));
            rSFormulaDTO.setDeductionCategory(String.valueOf(object[NumericConstants.TWO]));
            rSFormulaDTO.setIndicator(String.valueOf(object[NumericConstants.THREE]));
            resultList.add(rSFormulaDTO);
        }
        return resultList;
    }
}
