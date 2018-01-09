/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.lookups.logic;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionList;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.addons.tableexport.TemporaryFileDownloadResource;
import com.stpl.app.gtnforecasting.dao.PPAProjectionDao;
import com.stpl.app.gtnforecasting.dao.SalesProjectionDAO;
import com.stpl.app.gtnforecasting.dao.impl.PPAProjectionDaoImpl;
import com.stpl.app.gtnforecasting.dao.impl.SalesProjectionDAOImpl;
import com.stpl.app.gtnforecasting.dto.PMPYRowDto;
import com.stpl.app.gtnforecasting.dto.PeriodDTO;
import com.stpl.app.gtnforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.logic.DataSourceConnection;
import com.stpl.app.gtnforecasting.lookups.dto.MPmpyDTO;
import com.stpl.app.gtnforecasting.lookups.dto.PmpyTradingPartnerDTO;
import com.stpl.app.gtnforecasting.salesprojection.logic.SalesLogic;
import com.stpl.app.gtnforecasting.salesprojection.utils.HeaderUtils;
import com.stpl.app.gtnforecasting.salesprojection.utils.SalesUtils;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import static com.stpl.app.gtnforecasting.utils.Constant.DASH;
import com.stpl.app.gtnforecasting.utils.xmlparser.SQlUtil;
import com.stpl.app.service.CcpDetailsLocalServiceUtil;
import com.stpl.app.service.CompanyMasterLocalServiceUtil;
import com.stpl.app.service.ContractMasterLocalServiceUtil;
import com.stpl.app.service.ProjectionDetailsLocalServiceUtil;
import com.stpl.app.utils.Constants;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.server.Page;
import com.vaadin.ui.UI;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.filter.SimpleStringFilter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.naming.NamingException;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class PmpyLogic.
 *
 * @author vinodhini
 */
public class PmpyLogic {

    /**
     * The Constant LOGGER.
     */
    public static final Logger LOGGER = LoggerFactory.getLogger(PmpyLogic.class);
    protected DecimalFormat doubleDecimalFormat = new DecimalFormat("#0.00");
    protected DecimalFormat noDecimalFormat = new DecimalFormat("#");
    /**
     * The Constant XLS_FORMAT.
     */
    public static final String XLS_FORMAT = ".xls";

    /**
     * The Constant FILE_NAME.
     */
    public static final String FILE_NAME = "PMPY Calculator";

    /**
     * The Constant EXCEL_MIME_TYPE.
     */
    public static final String EXCEL_MIME_TYPE = "application/vnd.ms-excel";
    public static final String TRADING_PARTNER_NAME = "tradingPartnerName";
    public static final String TRADING_PARTNER_NO = "tradingPartnerNo";

    /**
     * The Constant WINDOW_NAME.
     */
    public static final String WINDOW_NAME = "_blank";

    /**
     * The Constant EXCEL_ICON.
     */
    public static final String EXCEL_ICON = "../../icons/excel.png";

    /**
     * The Constant CHART_ICON.
     */
    public static final String CHART_ICON = "../../icons/chart.png";

    /**
     * Trading partner look up.
     *
     * @param tpNo
     * @return the list
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    public Object tradingPartnerLookUp(String tpNo, String tpName, Object contractHolder, int start, int offset, final List<SortByColumn> sortByColumns, final Set<Container.Filter> filterSet, boolean isCount) {
        LOGGER.debug("Entering tradingPartnerLookUp  ::::");
        List input = new ArrayList<>();
        String queryName;

        if (contractHolder != null) {
            input.add(contractHolder);
            if (isCount) {
                queryName = "PMPY-Load TP with CH Count";
            } else {
                queryName = "PMPY-Load TP with CH";
            }

        } else {
            if (isCount) {
                queryName = "PMPY-Load TP without CH Count";
            } else {
                queryName = "PMPY-Load TP without CH";
            }
        }
        Map<String, String> parameters = new HashMap<>();

        if (filterSet != null) {

            for (Container.Filter filter : filterSet) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = Constant.PERCENT + stringFilter.getFilterString()
                            + Constant.PERCENT;

                    parameters.put(
                            String.valueOf(stringFilter.getPropertyId()),
                            filterString);

                }
            }
        }

        if (!tpName.isEmpty()) {
            String tradingPartnerName = tpName;
            tradingPartnerName = tradingPartnerName.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            input.add(tradingPartnerName);
        } else {
            input.add(Constant.PERCENT);
        }
        if (!tpNo.isEmpty()) {
            String tradingPartnerNo = tpNo;
            tradingPartnerNo = tradingPartnerNo.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            input.add(tradingPartnerNo);
        } else {
            input.add(Constant.PERCENT);
        }
        Object tradingpartner;
        List resultTPList = getPmpyData(input, queryName, start, start + offset, sortByColumns, isCount, parameters);
        if (isCount) {
            tradingpartner = resultTPList.get(0);
        } else {
            tradingpartner = getTPLookedUp(resultTPList);
        }
        LOGGER.debug("Ending tradingPartnerLookUp    ::::");
        return tradingpartner;
    }

    /**
     * Gets the TP looked up.
     *
     * @param list the list
     * @return the TP looked up
     */
    public List<PmpyTradingPartnerDTO> getTPLookedUp(final List<Object[]> list) {
        final List<PmpyTradingPartnerDTO> resultList = new ArrayList<>();

        LOGGER.debug("Entering getTPLookedUp  ::::");
        final int index = list.size();
        for (int i = 0; i < index; i++) {
            Object[] obj = list.get(i);
            final PmpyTradingPartnerDTO lookedUpTP = new PmpyTradingPartnerDTO();
            lookedUpTP.setTradingPartnerNo(String.valueOf(obj[0]));
            lookedUpTP.setTradingPartnerName(String.valueOf(obj[1]));
            lookedUpTP.setCompanySysId(Integer.valueOf(String.valueOf(obj[NumericConstants.TWO])));
            resultList.add(lookedUpTP);
        }

        LOGGER.debug("Ending getTPLookedUp return  size ::::" + resultList.size());

        return resultList;
    }

    final static PPAProjectionDao PPADAO = new PPAProjectionDaoImpl();

    public static List getGroupList() {
        return new ArrayList();
    }

    List getPmpyData(List input, String queryName, final int index, final int next, final List<SortByColumn> sortByColumns, boolean isCount, Map<String, String> parameters) {
        LOGGER.debug("Inside getPmpyData queryName=" + queryName);
        HashMap<String, String> tpColumnName = loadDbColumnName();
        List list = new ArrayList();
        StringBuilder sql = new StringBuilder();
        try {

            sql = new StringBuilder(SQlUtil.getQuery(getClass(),queryName));

            for (Object temp : input) {
                sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(temp));
            }
            boolean sortOrder = false;
            String columnName = null;
            String orderByColumn = null;
            if (sortByColumns != null) {
                for (final Iterator<SortByColumn> iterator = sortByColumns.iterator(); iterator.hasNext();) {
                    final SortByColumn sortByColumn = (SortByColumn) iterator.next();

                    columnName = sortByColumn.getName();
                    orderByColumn = tpColumnName.get(columnName);
                    if (sortByColumn.getType() == SortByColumn.Type.ASC) {
                        sortOrder = false;
                    } else {
                        sortOrder = true;
                    }
                }
            }
            boolean flag = false;
            if (parameters.get(TRADING_PARTNER_NO) != null) {
                if (flag) {
                    sql.append(" and");
                } else {
                    sql.append(" where ");
                    flag = true;
                }

                sql.append(" COMM.COMPANY_NO like '")
                        .append(String.valueOf(parameters.get(TRADING_PARTNER_NO))).append("'   ");

            }
            if (parameters.get(TRADING_PARTNER_NAME) != null) {
                if (flag) {
                    sql.append(" and");
                } else {
                    sql.append(" where ");
                }

                sql.append(" COMM.COMPANY_NAME like '")
                        .append(String.valueOf(parameters.get(TRADING_PARTNER_NAME))).append("'   ");

            }

            if (!isCount) {

                if (orderByColumn == null || StringUtils.EMPTY.equals(orderByColumn)) {
                    sql.append(" ORDER BY COMM.COMPANY_MASTER_SID").append((!sortOrder) ? " ASC " : " DESC ");
                } else {
                    sql.append("ORDER BY ").append(orderByColumn).append((!sortOrder) ? " ASC " : " DESC ");
                }

                sql.append(" OFFSET ");
                sql.append(index);
                sql.append(Constant.ROWS_FETCH_NEXT_SPACE);
                sql.append(next - index);
                sql.append(" ROWS ONLY;");
            }

            list = (List) PPADAO.executeSelect(sql.toString());

        } catch (Exception ex) {

            LOGGER.error(ex.getMessage());
        }
        LOGGER.debug("End of getPmpyData");
        return list;
    }

    public HashMap<String, String> loadDbColumnName() {
        HashMap<String, String> tpColumnName = new HashMap<>();
        tpColumnName.put(TRADING_PARTNER_NO, "COMM.COMPANY_NO");
        tpColumnName.put(TRADING_PARTNER_NAME, "COMM.COMPANY_NAME");
        return tpColumnName;
    }

    public List loadCustomer() throws PortalException, SystemException {

        StringBuilder query = new StringBuilder();
        query.append("SELECT DISTINCT CM.COMPANY_MASTER_SID,CM.COMPANY_NO FROM dbo.CCP_DETAILS CCP \n"
                + " JOIN dbo.COMPANY_MASTER CM ON CM.COMPANY_MASTER_SID = CCP.COMPANY_MASTER_SID \n"
                + " JOIN dbo.CONTRACT_MASTER CO ON CO.CONTRACT_MASTER_SID = CCP.CONTRACT_MASTER_SID \n"
                + " JOIN dbo.HELPER_TABLE HT ON HT.HELPER_TABLE_SID=CO.CONTRACT_TYPE AND HT.LIST_NAME = 'CONTRACT_TYPE' AND \n"
                + " HT.DESCRIPTION IN ('FFS','Medicaid FFS','SPAP','ADAP','PHS','Managed Medicaid','MM','Federal') order by CM.COMPANY_NO ;");

        SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
        List list = (List) salesProjectionDAO.executeSelectQuery(query.toString());
        return list;
    }

    public List loadContract(int customerSid) throws PortalException, SystemException {

        StringBuilder query = new StringBuilder();
        query.append("SELECT DISTINCT CO.CONTRACT_MASTER_SID,CO.CONTRACT_NO FROM dbo.CCP_DETAILS CCP \n"
                + " JOIN dbo.COMPANY_MASTER CM ON CM.COMPANY_MASTER_SID = CCP.COMPANY_MASTER_SID AND CM.COMPANY_MASTER_SID = " + customerSid + " \n"
                + " JOIN dbo.CONTRACT_MASTER CO ON CO.CONTRACT_MASTER_SID = CCP.CONTRACT_MASTER_SID \n"
                + " JOIN dbo.HELPER_TABLE HT ON HT.HELPER_TABLE_SID=CO.CONTRACT_TYPE AND HT.LIST_NAME = 'CONTRACT_TYPE' AND  \n"
                + " HT.DESCRIPTION IN ('FFS','Medicaid FFS','SPAP','ADAP','PHS','Managed Medicaid','MM','Federal') order by CO.CONTRACT_NO;");

        SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
        List list = (List) salesProjectionDAO.executeSelectQuery(query.toString());
        return list;
    }

    public List loadProduct( int customerSid, int contractSid) throws PortalException, SystemException {

        StringBuilder query = new StringBuilder();
        query.append("SELECT DISTINCT IM.ITEM_MASTER_SID,IM.ITEM_NO,IM.ITEM_NAME FROM dbo.CCP_DETAILS CCP \n"
                + " JOIN dbo.COMPANY_MASTER CM ON CM.COMPANY_MASTER_SID = CCP.COMPANY_MASTER_SID AND CM.COMPANY_MASTER_SID = " + customerSid + " \n"
                + " JOIN dbo.CONTRACT_MASTER CO ON CO.CONTRACT_MASTER_SID = CCP.CONTRACT_MASTER_SID AND CO.CONTRACT_MASTER_SID = " + contractSid + " \n"
                + " JOIN dbo.ITEM_MASTER IM ON IM.ITEM_MASTER_SID = CCP.ITEM_MASTER_SID \n"
                + " JOIN dbo.HELPER_TABLE HT ON HT.HELPER_TABLE_SID=CO.CONTRACT_TYPE AND HT.LIST_NAME = 'CONTRACT_TYPE' AND \n"
                + " HT.DESCRIPTION IN ('FFS','Medicaid FFS','SPAP','ADAP','PHS','Managed Medicaid','MM','Federal');");

        SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
        List<Object[]> list = (List) salesProjectionDAO.executeSelectQuery(query.toString());
        return convertProductResults(list);
    }

    public List convertProductResults(List<Object[]> list) {
        List<MPmpyDTO> resultList = new ArrayList<>();
        for (Object[] obj : list) {
            MPmpyDTO pmpyDTO = new MPmpyDTO();
            pmpyDTO.setProductSid(String.valueOf(obj[0]));
            pmpyDTO.setProductNo(String.valueOf(obj[1]));
            pmpyDTO.setProductName(String.valueOf(obj[NumericConstants.TWO]));
            resultList.add(pmpyDTO);
        }
        return resultList;
    }

    public List<MPmpyDTO> convertPMPYResults(List<Object[]> list, String frequency) {
        List<MPmpyDTO> resultList = new ArrayList();

        MPmpyDTO pmpyActualSalesDTO = new MPmpyDTO();
        MPmpyDTO pmpyProjectionSalesDTO = new MPmpyDTO();
        MPmpyDTO pmpyActualUnitsDTO = new MPmpyDTO();
        MPmpyDTO pmpyProjectionUnitsDTO = new MPmpyDTO();
        MPmpyDTO pmpyLivesDTO = new MPmpyDTO();

        pmpyActualSalesDTO.setFirstColumn(Constant.ACTUAL_SALES);
        pmpyProjectionSalesDTO.setFirstColumn("Projected Sales");
        pmpyActualUnitsDTO.setFirstColumn("Actual Units");
        pmpyProjectionUnitsDTO.setFirstColumn("Projected Units");
        pmpyLivesDTO.setFirstColumn(Constant.LIVES);

        for (Object[] obj : list) {

            String key = StringUtils.EMPTY;

            if (frequency.contains(Constants.FrequencyConstants.QUARTERLY.getConstant())) {
                key = Constant.Q + obj[NumericConstants.FIVE] + StringUtils.EMPTY + obj[NumericConstants.SIX];
            } else if (frequency.contains(Constants.FrequencyConstants.SEMI_ANNUAL.getConstant())) {
                key = Constant.S + obj[NumericConstants.FIVE] + StringUtils.EMPTY + obj[NumericConstants.SIX];
            } else if (frequency.contains(Constants.FrequencyConstants.ANNUAL.getConstant())) {
                key = StringUtils.EMPTY + obj[NumericConstants.FIVE];
            } else if (frequency.contains(Constants.FrequencyConstants.MONTHLY.getConstant())) {
                String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[NumericConstants.FIVE])));
                key = String.valueOf(monthName + String.valueOf(obj[NumericConstants.SIX])).toUpperCase();
            }

            if (StringUtils.isNotEmpty(key) && StringUtils.isNotBlank(key)) {
                if (StringUtils.EMPTY.equals(String.valueOf(obj[0])) || Constant.NULL.equals(String.valueOf(obj[0]))) {
                    pmpyActualSalesDTO.addStringProperties(key, "$0");
                } else {
                    String tempValue = SalesUtils.valueToCurrency(String.valueOf(obj[0]), doubleDecimalFormat);
                    pmpyActualSalesDTO.addStringProperties(key, tempValue);
                }

                if (StringUtils.EMPTY.equals(String.valueOf(obj[1])) || Constant.NULL.equals(String.valueOf(obj[1]))) {
                    pmpyActualUnitsDTO.addStringProperties(key, DASH);
                } else {
                    String tempValue = noDecimalFormat.format(Double.valueOf(String.valueOf(obj[1])));
                    pmpyActualUnitsDTO.addStringProperties(key, tempValue);
                }

                if (StringUtils.EMPTY.equals(String.valueOf(obj[NumericConstants.TWO])) || Constant.NULL.equals(String.valueOf(obj[NumericConstants.TWO]))) {
                    pmpyProjectionSalesDTO.addStringProperties(key, "$0");
                } else {
                    String tempValue = SalesUtils.valueToCurrency(String.valueOf(obj[NumericConstants.TWO]), doubleDecimalFormat);
                    pmpyProjectionSalesDTO.addStringProperties(key, tempValue);
                }

                if (StringUtils.EMPTY.equals(String.valueOf(obj[NumericConstants.THREE])) || Constant.NULL.equals(String.valueOf(obj[NumericConstants.THREE]))) {
                    pmpyProjectionUnitsDTO.addStringProperties(key, DASH);
                } else {
                    String tempValue = noDecimalFormat.format(Double.valueOf(String.valueOf(obj[NumericConstants.THREE])));
                    pmpyProjectionUnitsDTO.addStringProperties(key, tempValue);
                }

                if (StringUtils.EMPTY.equals(String.valueOf(obj[NumericConstants.FOUR])) || Constant.NULL.equals(String.valueOf(obj[NumericConstants.FOUR]))) {
                    pmpyLivesDTO.addStringProperties(key, DASH);
                } else {
                    String tempValue = noDecimalFormat.format(Double.valueOf(String.valueOf(obj[NumericConstants.FOUR])));
                    pmpyLivesDTO.addStringProperties(key, tempValue);
                }
            }
        }

        resultList.add(0, pmpyActualSalesDTO);
        resultList.add(1, pmpyProjectionSalesDTO);
        resultList.add(NumericConstants.TWO, pmpyActualUnitsDTO);
        resultList.add(NumericConstants.THREE, pmpyProjectionUnitsDTO);
        resultList.add(NumericConstants.FOUR, pmpyLivesDTO);

        return resultList;
    }

    /**
     * Export the calculation to workSheet.
     *
     * @param exporterDto the exporter dto
     * @throws SystemException the system exception
     */
    public void export(final MPmpyDTO exporterDto)  {
        File tempFile = getFile();
        try (FileOutputStream fileOut = new FileOutputStream(tempFile);){
            LOGGER.debug("Entering export method ");
            final HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFCell cellA1;
            HSSFRow dataRow;
            HSSFRow headerRow;
            final HSSFCellStyle cellStyle = workbook.createCellStyle();

            cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            final HSSFSheet worksheet = workbook.createSheet(FILE_NAME + " Worksheet");
            headerRow = worksheet.createRow(0);
            cellA1 = headerRow.createCell(0);
            cellA1.setCellValue(FILE_NAME);
            cellA1.setCellStyle(cellStyle);
            worksheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 1));

            final HSSFCellStyle cellStyleCurrency = workbook.createCellStyle();
            cellStyleCurrency.setDataFormat(Short.valueOf(String.valueOf(NumericConstants.SIX)));

            final HSSFCellStyle cellStylePercentage = workbook.createCellStyle();
            final HSSFDataFormat dateformat = workbook.createDataFormat();
            cellStylePercentage.setDataFormat(dateformat.getFormat("#0.0%"));

            final HSSFCellStyle cellStyleUnit = workbook.createCellStyle();
            final HSSFDataFormat dataFormat = workbook.createDataFormat();
            cellStyleUnit.setDataFormat(dataFormat.getFormat("#0"));

            for (int i = 1; i < NumericConstants.TEN; i++) {
                dataRow = worksheet.createRow(i);
                if (i == 1) {

                    cellA1 = dataRow.createCell(0);
                    cellA1.setCellValue("Sales/Units");
                    cellA1 = dataRow.createCell(1);
                    String changeValue = exporterDto.getSales();
                    if (changeValue.isEmpty()) {

                        cellA1.setCellValue(exporterDto.getSales());
                    } else {
                        changeValue = changeValue.replaceAll(MPmpyDTO.COMMA, StringUtils.EMPTY);
                        final double value = Double.parseDouble(changeValue);
                        cellStyleCurrency.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
                        cellA1.setCellStyle(cellStyleCurrency);
                        cellA1.setCellValue(value);
                    }
                }
                if (i == NumericConstants.TWO) {
                    cellA1 = dataRow.createCell(0);
                    cellA1.setCellValue("Analog Lives");
                    cellA1 = dataRow.createCell(1);
                    String changeValue = exporterDto.getLives();

                    if (changeValue.isEmpty()) {
                        cellA1.setCellValue(exporterDto.getLives());
                    } else {
                        changeValue = changeValue.replaceAll(MPmpyDTO.COMMA, StringUtils.EMPTY);
                        cellStyleUnit.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
                        cellA1.setCellStyle(cellStyleUnit);
                        cellA1.setCellValue(changeValue);
                    }
                }
                if (i == NumericConstants.THREE) {
                    cellA1 = dataRow.createCell(0);
                    cellA1.setCellValue("Value per Life");
                    cellA1 = dataRow.createCell(1);
                    String changeValue = exporterDto.getValuePerLife();
                    if (changeValue.isEmpty()) {
                        cellA1.setCellValue(exporterDto.getValuePerLife());
                    } else {
                        changeValue = changeValue.replaceAll(MPmpyDTO.COMMA, StringUtils.EMPTY);
                        final double value = Double.parseDouble(changeValue);
                        cellStyleCurrency.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
                        cellA1.setCellStyle(cellStyleCurrency);
                        cellA1.setCellValue(value);
                    }
                }

                if (i == NumericConstants.FOUR) {
                    cellA1 = dataRow.createCell(0);
                    cellA1.setCellValue("Projected Lives");
                    cellA1 = dataRow.createCell(1);
                    String changeValue = exporterDto.getTotalLives();
                    if (changeValue.isEmpty()) {
                        cellA1.setCellValue(exporterDto.getTotalLives());
                    } else {
                        changeValue = changeValue.replaceAll(MPmpyDTO.COMMA, StringUtils.EMPTY);
                        cellStyleUnit.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
                        cellA1.setCellStyle(cellStyleUnit);
                        cellA1.setCellValue(changeValue);
                    }
                }
                if (i == NumericConstants.FIVE) {
                    cellA1 = dataRow.createCell(0);
                    cellA1.setCellValue("Total Sales/Units");
                    cellA1 = dataRow.createCell(1);
                    String changeValue = exporterDto.getTotalSales();

                    if (changeValue.isEmpty()) {
                        cellA1.setCellValue(exporterDto.getTotalSales());
                    } else {
                        changeValue = changeValue.replaceAll(MPmpyDTO.COMMA, StringUtils.EMPTY);

                        final double value = Double.parseDouble(changeValue);
                        cellStyleCurrency.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
                        cellA1.setCellStyle(cellStyleCurrency);
                        cellA1.setCellValue(value);
                    }
                }
                if (i == NumericConstants.SIX) {
                    cellA1 = dataRow.createCell(0);
                    cellA1.setCellValue("Projected Period Total");
                    cellA1 = dataRow.createCell(1);
                    String changeValue = exporterDto.getProjectionPeriodTotal();
                    if (changeValue.isEmpty()) {
                        cellA1.setCellValue(exporterDto.getProjectionPeriodTotal());
                    } else {
                        changeValue = changeValue.replaceAll(MPmpyDTO.COMMA, StringUtils.EMPTY);
                        final double value = Double.parseDouble(changeValue);
                        cellStyleCurrency.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
                        cellA1.setCellStyle(cellStyleCurrency);
                        cellA1.setCellValue(value);
                    }
                }

            }
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();

            Page.getCurrent().open(new TemporaryFileDownloadResource(UI.getCurrent(), FILE_NAME + ".xls", EXCEL_MIME_TYPE, tempFile), WINDOW_NAME, true);

            tempFile.deleteOnExit();
            LOGGER.debug("End of export method");
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    public List getNmProjectionDetId(Object[] inputs) {
        List list = null;
        try {
            StringBuilder queryBuilder = new StringBuilder(StringUtils.EMPTY);
            String projectionId = String.valueOf(inputs[0]);
            String hierarchyNos = (String) inputs[1];

            if (hierarchyNos.equals("WHERE")) {
                hierarchyNos = " ";
            }

            queryBuilder.append("  SELECT PROJECTION_DETAILS_SID FROM  PROJECTION_DETAILS WHERE CCP_DETAILS_SID IN  \n");

            queryBuilder.append("   (SELECT LCCP.CCP_DETAILS_SID from       \n");
            queryBuilder.append("   (SELECT CCPMAP.CCP_DETAILS_SID, HLD.HIERARCHY_NO, HLD.RELATIONSHIP_LEVEL_SID from       \n");
            queryBuilder.append("   (SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID       \n");
            queryBuilder.append("   FROM RELATIONSHIP_LEVEL_DEFINITION RLD       \n");
            queryBuilder.append("   JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID      \n");
            queryBuilder.append("   JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID='" + (projectionId) + "'      \n");

            queryBuilder.append("   JOIN PROJECTION_MASTER PM  ON PD.PROJECTION_MASTER_SID=PM.PROJECTION_MASTER_SID       \n");
            queryBuilder.append("   WHERE PM.PROJECTION_MASTER_SID='" + (projectionId) + "') CCPMAP,       \n");
            queryBuilder.append("   (SELECT RLD1.HIERARCHY_NO, RLD1.RELATIONSHIP_LEVEL_SID      \n");
            queryBuilder.append("   FROM RELATIONSHIP_LEVEL_DEFINITION RLD1       \n");

            queryBuilder.append("    JOIN PROJECTION_CUST_HIERARCHY PCH     \n");

            queryBuilder.append("   ON PCH.RELATIONSHIP_LEVEL_SID=RLD1.RELATIONSHIP_LEVEL_SID       \n");
            queryBuilder.append("   AND PCH.PROJECTION_MASTER_SID='" + (projectionId) + "'       \n");

            queryBuilder.append("    " + hierarchyNos + "       \n");

            queryBuilder.append(" ) HLD  WHERE CCPMAP.HIERARCHY_NO like HLD.HIERARCHY_NO + '%' ) LCCP      \n");
            queryBuilder.append("   WHERE LCCP.HIERARCHY_NO in       \n");
            queryBuilder.append("   (SELECT RLD2.HIERARCHY_NO       \n");
            queryBuilder.append("   FROM RELATIONSHIP_LEVEL_DEFINITION RLD2      \n");

            queryBuilder.append("    JOIN PROJECTION_CUST_HIERARCHY PCH2     \n");

            queryBuilder.append("   ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID       \n");
            queryBuilder.append("   AND PCH2.PROJECTION_MASTER_SID='" + (projectionId) + "'       \n");

            queryBuilder.append(" ) )   \n");
            SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
            list = (List) salesProjectionDAO.executeSelectQuery(queryBuilder.toString());
        } catch (PortalException | SystemException e) {
            LOGGER.error(e.getMessage());
        }
        return list;
    }

    public List getTradingPartnerInfo(int prDetId) {
        List list = new ArrayList();
        List<String> resultList = new ArrayList<>();
        String tradingName = StringUtils.EMPTY;
        String tradingNo = StringUtils.EMPTY;
        String contractHolder = StringUtils.EMPTY;

        DynamicQuery dynamicQuery = CcpDetailsLocalServiceUtil.dynamicQuery();

        final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();

        productProjectionList.add(ProjectionFactoryUtil.property(Constant.COMPANYMASTERSID));
        productProjectionList.add(ProjectionFactoryUtil.property(Constant.CONTRACT_MASTER_SID));

        dynamicQuery.add(PropertyFactoryUtil.forName(Constant.CCP_DETAILS_SID).in(
                ProjectionDetailsLocalServiceUtil.dynamicQuery()
                .add(RestrictionsFactoryUtil.eq(Constant.PROJECTION_DETAILS_SID, prDetId))
                .setProjection(ProjectionFactoryUtil.property(Constant.CCP_DETAILS_SID))));

        dynamicQuery.setProjection(ProjectionFactoryUtil.distinct(productProjectionList));
        try {
            list = CcpDetailsLocalServiceUtil.dynamicQuery(dynamicQuery);
        } catch (SystemException ex) {
            LOGGER.error(ex.getMessage());
        }
        if (!list.isEmpty()) {
            Object[] obj = (Object[]) list.get(0);
            String.valueOf(obj[0]);
            int companyMasterSid = Integer.parseInt(String.valueOf(obj[0]));

            int contractMasterSid = Integer.parseInt(String.valueOf(obj[1]));
            list = new ArrayList();
            DynamicQuery dynamicQuery1 = CompanyMasterLocalServiceUtil.dynamicQuery();

            final ProjectionList productProjectionList1 = ProjectionFactoryUtil.projectionList();

            productProjectionList1.add(ProjectionFactoryUtil.property(Constant.COMPANY_NAME));
            productProjectionList1.add(ProjectionFactoryUtil.property("companyNo"));
            dynamicQuery1.add(RestrictionsFactoryUtil.eq(Constant.COMPANYMASTERSID, companyMasterSid));
            dynamicQuery1.setProjection(ProjectionFactoryUtil.distinct(productProjectionList1));
            try {
                list = CompanyMasterLocalServiceUtil.dynamicQuery(dynamicQuery1);
            } catch (SystemException ex) {
                LOGGER.error(ex.getMessage());
            }
            if (!list.isEmpty()) {

                Object[] obj1 = (Object[]) list.get(0);
                tradingName = String.valueOf(obj1[0]);
                tradingNo = String.valueOf(obj1[1]);
                list = new ArrayList();
                DynamicQuery dynamicQuery2 = CompanyMasterLocalServiceUtil.dynamicQuery();

                final ProjectionList productProjectionList2 = ProjectionFactoryUtil.projectionList();

                productProjectionList2.add(ProjectionFactoryUtil.property(Constant.COMPANY_NAME));

                dynamicQuery2.add(PropertyFactoryUtil.forName(Constant.COMPANYMASTERSID).in(
                        ContractMasterLocalServiceUtil.dynamicQuery()
                        .add(RestrictionsFactoryUtil.eq(Constant.CONTRACT_MASTER_SID, contractMasterSid))
                        .setProjection(ProjectionFactoryUtil.property("contHoldCompanyMasterSid"))));

                dynamicQuery2.setProjection(ProjectionFactoryUtil.distinct(productProjectionList2));
                try {
                    list = CompanyMasterLocalServiceUtil.dynamicQuery(dynamicQuery2);
                } catch (SystemException ex) {
                    LOGGER.error(ex.getMessage());
                }
                if (!list.isEmpty()) {
                    contractHolder = (String) list.get(0);
               
                }

            }
        }

        resultList.add(tradingName);
        resultList.add(tradingNo);
        resultList.add(contractHolder);

        return resultList;
    }

    public List<PMPYRowDto> getNmPmpyResultList(Object[] inputs) {

        List<PMPYRowDto> resultList = new ArrayList<>();

        List list = null;
        try {
            list = callNmPmpyProcedure(inputs);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }

        List<Object> propertyList = (List<Object>) inputs[NumericConstants.FOUR];

        if (list != null) {
            List<PeriodDTO> tempList = new ArrayList<>();
            PeriodDTO periodDTO = null;
            for (int i = 0; i < list.size(); i++) {
                Object[] obj = (Object[]) list.get(i);
                periodDTO = new PeriodDTO();
                if (obj[0] != null) {
                    periodDTO.setQuator(String.valueOf(obj[0]));

                }
                if (obj[1] != null) {
                    periodDTO.setYear(String.valueOf(obj[1]));

                }
                if (obj[NumericConstants.TWO] != null) {
                    periodDTO.setActualSales(String.valueOf(CommonUtils.MONEY.format(Double.valueOf(obj[NumericConstants.TWO].toString()))));

                }

                if (obj[NumericConstants.THREE] != null) {
                
                    periodDTO.setActualUnits(String.valueOf(CommonUtils.UNITVOLUME.format(Double.valueOf(obj[NumericConstants.THREE].toString()))));
                }
                if (obj[NumericConstants.FOUR] != null) {
                 
                    periodDTO.setLives(Double.valueOf(CommonUtils.UNITVOLUME.format(Double.valueOf(obj[NumericConstants.FOUR].toString()))));
                }

                tempList.add(periodDTO);

            }

            PMPYRowDto pmpyRowDto1 = new PMPYRowDto();
            PMPYRowDto pmpyRowDto2 = new PMPYRowDto();
            PMPYRowDto pmpyRowDto3 = new PMPYRowDto();

            Map<String, String> properties1 = new HashMap<>();
            Map<String, String> properties2 = new HashMap<>();
            Map<String, String> properties3 = new HashMap<>();
            for (Object obj : propertyList) {

                properties1.put(String.valueOf(obj), "0.0");
                properties2.put(String.valueOf(obj), "0.0");
                properties3.put(String.valueOf(obj), "0.0");

            }

            for (PeriodDTO pDto : tempList) {
                properties1.put(Constant.Q_SMALL + pDto.getQuator() + "-" + pDto.getYear(), StringUtils.EMPTY + pDto.getActualSales() + StringUtils.EMPTY);
                properties2.put(Constant.Q_SMALL + pDto.getQuator() + "-" + pDto.getYear(), StringUtils.EMPTY + pDto.getActualUnits() + StringUtils.EMPTY);
                properties3.put(Constant.Q_SMALL + pDto.getQuator() + "-" + pDto.getYear(), StringUtils.EMPTY + pDto.getLives() + StringUtils.EMPTY);
            }

            pmpyRowDto1.setProperties(properties1);
            pmpyRowDto1.setType(Constant.SALES_SMALL);
            pmpyRowDto2.setProperties(properties2);
            pmpyRowDto2.setType(Constant.UNITS_SMALL);
            pmpyRowDto3.setProperties(properties3);
            pmpyRowDto3.setType(Constant.LIVES);

            resultList.add(pmpyRowDto1);
            resultList.add(pmpyRowDto2);
            resultList.add(pmpyRowDto3);

        }

        return resultList;
    }

    private List callNmPmpyProcedure(Object[] inputs)  {
        List list = null;
        final DataSourceConnection dataSourceConnection = DataSourceConnection.getInstance();
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet resList = null;
        try {
            connection = dataSourceConnection.getConnection();

            LOGGER.debug("Entering callNmPmpyProcedure  ::::");

            if (connection != null) {

                statement = connection.prepareCall("{call Nm_sales_pmpy (?,?,?,?)}");
                statement.setInt(1, Integer.parseInt((String) inputs[0]));  //PROJECTION_MASTER_SID
                statement.setString(NumericConstants.TWO, String.valueOf(inputs[1]));   //@PROJECTION_DETAILS_SID
                statement.setInt(NumericConstants.THREE, Integer.parseInt((String) inputs[NumericConstants.TWO])); //CONTRACT_HOLDER_SID
                statement.setInt(NumericConstants.FOUR, Integer.parseInt((String) inputs[NumericConstants.THREE]));   //TRADING_PARTNER_SID

                resList = statement.executeQuery();
            }

            list = new ArrayList();

            Object[] temp = null;
            while (resList.next()) {

                temp = new Object[NumericConstants.FIVE];

                temp[0] = resList.getString(1);
                temp[1] = resList.getString(NumericConstants.TWO);
                temp[NumericConstants.TWO] = resList.getString(NumericConstants.THREE);
                temp[NumericConstants.THREE] = resList.getString(NumericConstants.FOUR);
                temp[NumericConstants.FOUR] = resList.getString(NumericConstants.FIVE);
                list.add(temp);
            }
            LOGGER.debug("Ending callSalesInsertProcedure return  staus ::::");
        } catch (NumberFormatException | SQLException | NamingException ex) {
            LOGGER.error(ex.getMessage());
        } finally {
           try {
                if (statement != null) 
                {
                statement.close();
                }
                if (resList != null)
                {
                resList.close();
                }
                if (connection != null)
                {
                connection.close();
                } 
           }catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
        }
        return list;
    }

    public void importPMPY(Object[] inputs, ProjectionSelectionDTO projectionSelectionDTO) {

        try {
            SalesLogic spLogic = new SalesLogic();
            SessionDTO session = projectionSelectionDTO.getSessionDTO();
            List input = new ArrayList();
            input.add(inputs[NumericConstants.THREE]);
            input.add(inputs[NumericConstants.TWO]);
            input.add(session.getSessionId());
            input.add(session.getUserId());
            input.add(inputs[1]);
            input.add(CommonUtils.getListToString((List) inputs[0]));

            String changeProperty;
            if (inputs[NumericConstants.FOUR].equals(Constant.SALES_CAPS)) {
                pmpyUpdate(input, "PMPY-UPDATE-SALES");
                changeProperty = Constant.SALES_CAPS;
            } else {
                pmpyUpdate(input, "PMPY-UPDATE-UNITS");
                changeProperty = Constant.UNITS_SMALL;
            }
            spLogic.callManualEntry(projectionSelectionDTO, changeProperty);

        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    public static Boolean pmpyUpdate(List input, String queryName) {
        LOGGER.debug("Inside pmpy Update ");
        StringBuilder sql = new StringBuilder();
        try {

            sql = new StringBuilder(SQlUtil.getQuery(PmpyLogic.class,queryName));
            for (Object temp : input) {
                sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(temp));
            }
            return (Boolean) PPADAO.executeUpdate(sql.toString());

        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        LOGGER.debug("End of pmpy Update ");
        return Boolean.FALSE;
    }

    private File getFile() {
        try {
            File tempFile = File.createTempFile(FILE_NAME, XLS_FORMAT);
            return tempFile;
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage());
        }
        return null;
    }
}
