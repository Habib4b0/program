
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.tp.logic;

import com.stpl.app.gcm.util.StringConstantsUtil;
import com.stpl.app.gcm.common.CommonLogic;
import com.stpl.app.gcm.common.HelperListUtil;
import com.stpl.app.gcm.common.dao.CommonDao;
import com.stpl.app.gcm.common.dao.impl.CommonImpl;
import com.stpl.app.gcm.copycontract.dto.ContractSelectionDTO;
import com.stpl.app.gcm.discount.dto.ContractsDetailsDto;
import com.stpl.app.gcm.itemmanagement.itemabstract.logic.AbstractLogic;
import com.stpl.app.gcm.itemmanagement.itemabstract.queryutils.ItemQueries;
import com.stpl.app.gcm.sessionutils.SessionDTO;
import com.stpl.app.gcm.tp.dto.CompanyCrtIdentifierDTO;
import com.stpl.app.gcm.tp.dto.ContractResultDTO;
import com.stpl.app.gcm.tp.dto.IdDescriptionDTO;
import com.stpl.app.gcm.tp.dto.TradingPartnerDTO;
import com.stpl.app.gcm.util.AbstractNotificationUtils;
import com.stpl.app.gcm.util.CommonUtils;
import com.stpl.app.gcm.util.Constants;

import static com.stpl.app.gcm.itemmanagement.itemabstract.logic.AbstractLogic.getDdlbmap;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.*;
import com.stpl.app.gcm.util.Converters;
import com.stpl.app.gcm.util.ErrorCodeUtil;
import com.stpl.app.gcm.util.ErrorCodes;
import com.stpl.app.model.CompanyIdentifier;
import com.stpl.app.model.CompanyMaster;
import com.stpl.app.model.CompanyQualifier;
import com.stpl.app.model.CompanyTradeClass;
import com.stpl.app.model.HelperTable;
import com.stpl.app.service.CompanyIdentifierLocalServiceUtil;
import com.stpl.app.service.CompanyMasterLocalServiceUtil;
import com.stpl.app.service.CompanyQualifierLocalServiceUtil;
import com.stpl.app.service.CompanyTradeClassLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionList;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.util.dao.orm.CustomSQLUtil;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.filter.Between;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.ComboBox;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtTreeContainer;
import org.jboss.logging.Logger;

/**
 *
 * @author maheshj
 */
public class CommmonLogic {

    private final CommonDao DAO = CommonImpl.getInstance();
    private static final HelperDTO ddlbDefaultValue = new HelperDTO(0, Constants.IndicatorConstants.SELECT_ONE.getConstant());
    private static final HelperDTO ddlbShowAllValue = new HelperDTO(0, Constants.SHOW_ALL);
    private final HelperListUtil helperListUtil = HelperListUtil.getInstance();
    public static final Logger LOGGER = Logger.getLogger(CommmonLogic.class);
    public static final String FILTERCFP_NAME = "filter~cfpName";
    public static final String FILTERIFP_NAME = "filter~ifpName";
    public static final String FILTERCFP_NO = "filter~cfpNo";
    public static final String FILTERIFP_NO = "filter~ifpNo";
    public static final String SLASH_N = "'  \n ";
    public static final String FILTERCONT_END_DATETO = "filter~contEndDate~to";
    public static final String FILTERCONT_END_DATEFROM = "filter~contEndDate~from";
    public static final String SLASH_N_SPACE = "' \n ";
    public static final String AND_QUOTE = "' AND '";
    public static final String YYYY_M_MDD_H_HMMSS_SSS = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String FILTER = "filter~";

    public List<IdDescriptionDTO> loadDdlbs(String fieldName) {
        List list = new ArrayList();
        List<IdDescriptionDTO> resultList = new ArrayList<>();
        IdDescriptionDTO idDescription = null;
        try {
            DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(HelperTable.class);
            final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
            productProjectionList.add(ProjectionFactoryUtil.property("helperTableSid"));
            productProjectionList.add(ProjectionFactoryUtil.property("description"));
            dynamicQuery.add(RestrictionsFactoryUtil.eq("listName", fieldName));
            dynamicQuery.setProjection(productProjectionList);
            list = HelperTableLocalServiceUtil.dynamicQuery(dynamicQuery);

            if (!list.isEmpty()) {
                for (int i = 0; i < list.size(); i++) {
                    Object obj[] = (Object[]) list.get(i);
                    idDescription = new IdDescriptionDTO(Integer.parseInt(String.valueOf(obj[0])), String.valueOf(obj[1]));
                    resultList.add(idDescription);
                }
            }

        } catch (SystemException ex) {
            LOGGER.error(ex);
        }
        return resultList;
    }

    public void setIdDescription(List<IdDescriptionDTO> idDesList, ComboBox ddlb) {
        ddlb.removeAllItems();
        ddlb.addItem(SELECT_ONE);
        ddlb.setNullSelectionItemId(SELECT_ONE);
        for (IdDescriptionDTO dto : idDesList) {
            ddlb.addItem(dto.getId());
            ddlb.setItemCaption(dto.getId(), dto.getDescription());
        }
    }

    public String buildContractSearchQuery(ContractSelectionDTO conSelDTO, String userId, String sessionId, int start, int end, Set<Container.Filter> filters) throws ParseException {

        List<String> companyMasterSidsList;
        if (conSelDTO.getModuleName().equals(PROJECTION_DETAILS_TRANSFER.getConstant()) && conSelDTO.getScreenName().equals(TAB_TRANSFER_CONTRACT.getConstant())) {
            companyMasterSidsList = conSelDTO.getPhCompanyMasterSids();
        } else {
            companyMasterSidsList = conSelDTO.getCompanyMasterSids();
        }

        String companyMasterSids = CommonUtils.CollectionToString(companyMasterSidsList, true);

        Map<String, Object> parameters = new HashMap<>();
        if (filters != null) {
            for (Container.Filter filter : filters) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = "%" + stringFilter.getFilterString() + "%";
                    parameters.put(FILTER + stringFilter.getPropertyId(), filterString);
                } else if (filter instanceof Between) {
                    Between betweenFilter = (Between) filter;
                    Date startValue = (Date) betweenFilter.getStartValue();
                    Date endValue = (Date) betweenFilter.getEndValue();
                    parameters.put(FILTER + betweenFilter.getPropertyId() + StringConstantsUtil.FROM_FILTER, String.valueOf(startValue));
                    parameters.put(FILTER + betweenFilter.getPropertyId() + StringConstantsUtil.TO_FILTER, String.valueOf(endValue));
                } else if (filter instanceof Compare) {
                    Compare compare = (Compare) filter;
                    Compare.Operation operation = compare.getOperation();
                    Date value = (Date) compare.getValue();
                    if (Compare.Operation.GREATER_OR_EQUAL.toString().equals(operation.name())) {
                        parameters.put(FILTER + compare.getPropertyId() + StringConstantsUtil.FROM_FILTER, String.valueOf(value));
                    } else {
                        parameters.put(FILTER + compare.getPropertyId() + StringConstantsUtil.TO_FILTER, String.valueOf(value));
                    }
                }
            }
        }

        StringBuilder query = new StringBuilder(StringUtils.EMPTY);

        String symbol = " in ";
        if (conSelDTO.isSearchInverse()) {
            symbol = " not in ";
        }

        query.append("DECLARE @LAST_APPROVED_PROJECTION_SID INT = (SELECT TOP 1 WM.PROJECTION_MASTER_SID \n");
        query.append("                            FROM WORKFLOW_MASTER WM \n");
        query.append("                            JOIN HELPER_TABLE WF_HT ON WF_HT.HELPER_TABLE_SID = WM.WORKFLOW_STATUS_ID \n");
        query.append("                            WHERE WF_HT.DESCRIPTION IN ( 'Approved' ) \n");
        query.append("                                AND WM.PROJECTION_MASTER_SID " + symbol + " (SELECT A.PROJECTION_MASTER_SID \n");
        query.append("                                FROM   (SELECT A.PROJECTION_MASTER_SID, B.COMPANY_MASTER_SID,Row_number() \n");
        query.append("                                                                                  OVER( \n");
        query.append("                                                                                    PARTITION BY A.PROJECTION_MASTER_SID, B.COMPANY_MASTER_SID \n");
        query.append("                                                                                    ORDER BY A.PROJECTION_MASTER_SID) AS RN \n");
        query.append("                                                                         FROM   PROJECTION_DETAILS A \n");
        query.append("                                                                         INNER JOIN PROJECTION_MASTER PM ON PM.PROJECTION_MASTER_SID = A.PROJECTION_MASTER_SID");
        query.append("                                                                         JOIN   CCP_DETAILS B ON A.CCP_DETAILS_SID = B.CCP_DETAILS_SID \n");
        query.append("                                                                         WHERE  B.COMPANY_MASTER_SID IN (" + companyMasterSids + " ) AND PM.IS_APPROVED = 'A')A \n");
        query.append("                                                                 WHERE  RN = 1 \n");
        query.append("                                                                 GROUP  BY A.PROJECTION_MASTER_SID \n");
        query.append("                                                                 HAVING Sum(( CASE \n");
        query.append("                                                                                WHEN A.COMPANY_MASTER_SID IN (" + companyMasterSids + " ) THEN -1 \n");
        query.append("                                                                                ELSE 0 \n");
        query.append("                                                                              END )) = -" + companyMasterSidsList.size() + ") \n");
        query.append("                                                                  AND WM.WORKFLOW_MASTER_SID IN (SELECT WORKFLOW_MASTER_SID \n");
        query.append("                                                                                         FROM   GCM_GLOBAL_DETAILS \n");
        query.append("                                                                                         WHERE USER_ID='").append(userId).append("' \n AND SESSION_ID='").append(sessionId).append("' \n");

        if (!conSelDTO.getScreenName().equals(StringUtils.EMPTY) && !conSelDTO.getScreenName().equals(Constants.NULL)) {
            query.append(" AND SCREEN_NAME = '").append(conSelDTO.getScreenName()).append("'");
        }
        query.append(") \n");

        query.append("                         ORDER  BY Isnull(WM.MODIFIED_DATE, WM.CREATED_DATE) DESC)");

        query.append(" SELECT Distinct TEMP_TABLE.PROJECTION_MASTER_SID AS 'PROJECTION_ID',\n");
        query.append(" WF.WORKFLOW_STATUS, \n");
        query.append(" CM.COMPANY_NAME AS 'Contract Holder',CON.CONTRACT_NO,CON.CONTRACT_NAME,HEL.DESCRIPTION AS 'CONTRACT_TYPE', \n");
        query.append(" CON.START_DATE as 'CONTRACT_START_DATE',CON.END_DATE as 'CONTRACT_END_DATE', \n ");
        query.append(" CFP_CON.CFP_NAME,CFP_CON.CFP_NO as 'CFP NO',CFP_MOD.CFP_ID,CFP_CON.CFP_STATUS,CFP_CON.CFP_START_DATE,CFP_CON.CFP_END_DATE, \n ");
        query.append(" IFP_CON.IFP_NAME,IFP_CON.IFP_NO as 'IFP NO',IFP_MOD.IFP_ID,IFP_CON.IFP_STATUS,IFP_CON.IFP_START_DATE,IFP_CON.IFP_END_DATE, \n ");
        query.append(" PS_CON.PS_NAME,PS_CON.PS_NO as 'PS NO',PS_MOD.PS_ID,PS_CON.PS_STATUS,PS_CON.PS_START_DATE,PS_CON.PS_END_DATE,   \n ");
        query.append(" RS_CON.RS_NAME,RS_CON.RS_NO as 'RAR NO',HEL1.DESCRIPTION AS 'RAR CATEGORY',RS_MOD.RS_ID,RS_MOD.RS_STATUS,RS_CON.RS_START_DATE,RS_CON.RS_END_DATE, \n ");
        query.append(" CFP_CON.CFP_CONTRACT_SID,RS_CON.RS_CONTRACT_SID,IFP_CON.IFP_CONTRACT_SID,PS_CON.PS_CONTRACT_SID,CON.CONTRACT_MASTER_SID,  \n ");
        query.append(" TEMP_TABLE.START_DATE as 'COMPANY_START_DATE',TEMP_TABLE.END_DATE as 'COMPANY_END_DATE',TEMP_TABLE.STATUS as 'TEMP_STATUS',TEMP_TABLE.CHECK_RECORD, \n");
        query.append(" (case when  HEL_TAB1.HELPER_TABLE_SID=0 then ' ' else HEL_TAB1.DESCRIPTION end) AS 'STATUS_DESCRIPTION' ,\n"
                + "				  HEL_TAB1.description  \n ");

        query.append(" FROM CONTRACT_MASTER CON   \n ");
        query.append(" LEFT JOIN COMPANY_MASTER  CM ON  CON.CONT_HOLD_COMPANY_MASTER_SID=CM.COMPANY_MASTER_SID \n ");
        query.append(" JOIN CFP_CONTRACT CFP_CON ON CFP_CON.CONTRACT_MASTER_SID=CON.CONTRACT_MASTER_SID \n ");
        query.append(" JOIN CFP_CONTRACT_DETAILS CFP_CON_DET ON CFP_CON_DET.CFP_CONTRACT_SID = CFP_CON.CFP_CONTRACT_SID AND CFP_CON_DET.INBOUND_STATUS <> 'D' \n");
        query.append(" JOIN IFP_CONTRACT IFP_CON ON IFP_CON.CONTRACT_MASTER_SID=CON.CONTRACT_MASTER_SID AND IFP_CON.CFP_CONTRACT_SID = CFP_CON.CFP_CONTRACT_SID \n ");
        query.append(" JOIN IFP_CONTRACT_DETAILS IFP_CON_DET ON IFP_CON_DET.IFP_CONTRACT_SID = IFP_CON.IFP_CONTRACT_SID \n");
        query.append(" JOIN PS_CONTRACT PS_CON ON PS_CON.CONTRACT_MASTER_SID=CON.CONTRACT_MASTER_SID AND IFP_CON.CFP_CONTRACT_SID = PS_CON.CFP_CONTRACT_SID \n");
        query.append("                         AND IFP_CON.IFP_CONTRACT_SID = PS_CON.IFP_CONTRACT_SID \n ");
        query.append(" JOIN RS_CONTRACT RS_CON ON RS_CON.CONTRACT_MASTER_SID=CON.CONTRACT_MASTER_SID AND IFP_CON.CFP_CONTRACT_SID = RS_CON.CFP_CONTRACT_SID \n");
        query.append("                         AND RS_CON.IFP_CONTRACT_SID = IFP_CON.IFP_CONTRACT_SID \n");
        query.append("                         AND RS_CON.PS_CONTRACT_SID = PS_CON.PS_CONTRACT_SID \n ");
        query.append(" JOIN HELPER_TABLE HEL ON HEL.HELPER_TABLE_SID=CON.CONTRACT_TYPE  \n ");

        /*below line added for RAR category */
        if (!conSelDTO.getRarCategory().equals(StringUtils.EMPTY) && !conSelDTO.getRarCategory().equals(Constants.NULL)) {
            query.append(" JOIN HELPER_TABLE HEL1 ON HEL1.HELPER_TABLE_SID=(select UDC2 from dbo.UDCS where MASTER_SID=RS_CON.RS_MODEL_SID and MASTER_TYPE='RS_MODEL')  \n ");
        } else {
            query.append("LEFT JOIN HELPER_TABLE HEL1 ON HEL1.HELPER_TABLE_SID = 0  \n ");
        }

        query.append(" LEFT JOIN COMPANY_MASTER CM1 ON CM1.COMPANY_MASTER_SID = CFP_CON_DET.COMPANY_MASTER_SID \n");
        query.append(" INNER JOIN GCM_GLOBAL_DETAILS TEMP_TABLE ON TEMP_TABLE.CFP_CONTRACT_SID=CFP_CON.CFP_CONTRACT_SID AND \n");
        query.append(" TEMP_TABLE.CONTRACT_MASTER_SID=CON.CONTRACT_MASTER_SID AND TEMP_TABLE.IFP_CONTRACT_SID=IFP_CON.IFP_CONTRACT_SID  \n");
        query.append(" AND TEMP_TABLE.RS_CONTRACT_SID=RS_CON.RS_CONTRACT_SID AND TEMP_TABLE.PS_CONTRACT_SID=PS_CON.PS_CONTRACT_SID \n");
        query.append(" AND TEMP_TABLE.USER_ID ='").append(userId).append("' AND  TEMP_TABLE.SESSION_ID='").append(sessionId).append("' \n");

        if (!conSelDTO.getScreenName().equals(StringUtils.EMPTY) && !conSelDTO.getScreenName().equals(Constants.NULL)) {
            query.append(" AND TEMP_TABLE.SCREEN_NAME = '").append(conSelDTO.getScreenName()).append("'");
        }

        String udcValue = "1";
        if (conSelDTO.getModuleName().equals(ADD_TRADING_PARTNER.getConstant()) || conSelDTO.getScreenName().equals(TAB_TRANSFER_CONTRACT.getConstant())) {
            udcValue = "2";
        }

        query.append(" AND TEMP_TABLE.OPERATION <> '").append(udcValue).append("' \n");

        query.append(" JOIN (SELECT WM.WORKFLOW_MASTER_SID, WF_HT.DESCRIPTION AS WORKFLOW_STATUS \n");
        query.append("            FROM   WORKFLOW_MASTER WM \n");
        query.append("            JOIN   HELPER_TABLE WF_HT ON WF_HT.HELPER_TABLE_SID = WM.WORKFLOW_STATUS_ID \n");
        query.append("            WHERE  WF_HT.DESCRIPTION IN ('Pending', 'Rejected', 'Cancelled') \n");
        query.append("                    OR WM.PROJECTION_MASTER_SID = @LAST_APPROVED_PROJECTION_SID) WF ON WF.WORKFLOW_MASTER_SID = TEMP_TABLE.WORKFLOW_MASTER_SID \n");

        query.append(" LEFT JOIN HELPER_TABLE HEL_TAB1 ON HEL_TAB1.HELPER_TABLE_SID=TEMP_TABLE.STATUS \n");
        query.append(" JOIN PROJECTION_MASTER PM ON PM.PROJECTION_MASTER_SID = TEMP_TABLE.PROJECTION_MASTER_SID AND PM.FORECASTING_TYPE <> 'Channel' ");
        query.append(" AND CON.INBOUND_STATUS <> 'D' AND CFP_CON.INBOUND_STATUS<> 'D' AND IFP_CON.INBOUND_STATUS<> 'D' \n");
        query.append(" AND PS_CON.INBOUND_STATUS <> 'D' AND RS_CON.INBOUND_STATUS <> 'D' \n");

        query.append("AND CFP_CON.CFP_CONTRACT_SID " + symbol + " (SELECT CFP.CFP_CONTRACT_SID\n");
        query.append("                                FROM   CONTRACT_MASTER A\n");
        query.append("                                JOIN   CFP_CONTRACT CFP ON A.CONTRACT_MASTER_SID = CFP.CONTRACT_MASTER_SID AND CFP.CONTRACT_MASTER_SID=CON.CONTRACT_MASTER_SID\n");
        query.append("                                JOIN   CFP_CONTRACT_DETAILS CFP_D ON CFP_D.CFP_CONTRACT_SID = CFP.CFP_CONTRACT_SID\n");
        query.append("                                WHERE  CFP_D.COMPANY_MASTER_SID IN ( " + companyMasterSids + " )\n");
        query.append("                                GROUP  BY CFP.CFP_CONTRACT_SID\n");
        query.append("                                HAVING Sum(( CASE WHEN CFP_D.COMPANY_MASTER_SID IN ( " + companyMasterSids + " ) THEN -1 ELSE 0 END )) = -" + companyMasterSidsList.size() + ") \n");

        query.append("LEFT JOIN (SELECT CFP_MOD.CFP_NO, CFP_MODEL_SID, CFP_MOD.CFP_ID, CFP_ST.DESCRIPTION AS CFP_STATUS FROM CFP_MODEL CFP_MOD \n"
                + "        INNER JOIN HELPER_TABLE CFP_ST ON CFP_ST.HELPER_TABLE_SID = CFP_MOD.CFP_STATUS)CFP_MOD \n"
                + "         ON CFP_MOD.CFP_MODEL_SID = CFP_CON.CFP_MODEL_SID\n"
                + "   LEFT JOIN (SELECT IFP_MOD.IFP_NO, IFP_MODEL_SID, IFP_MOD.IFP_ID, IFP_ST.DESCRIPTION AS IFP_STATUS FROM IFP_MODEL IFP_MOD \n"
                + "        JOIN HELPER_TABLE IFP_ST ON IFP_ST.HELPER_TABLE_SID = IFP_MOD.IFP_STATUS) IFP_MOD \n"
                + "        ON IFP_MOD.IFP_MODEL_SID = IFP_CON.IFP_MODEL_SID\n"
                + "   LEFT JOIN (SELECT RS_MOD.RS_NO, RS_MODEL_SID, RS_MOD.RS_ID, RS_ST.DESCRIPTION AS RS_STATUS FROM RS_MODEL RS_MOD \n"
                + "        JOIN HELPER_TABLE RS_ST ON RS_ST.HELPER_TABLE_SID = RS_MOD.RS_STATUS) RS_MOD \n"
                + "        ON RS_MOD.RS_MODEL_SID = RS_CON.RS_MODEL_SID\n"
                + "   LEFT JOIN (SELECT PS_MOD.PS_NO, PS_MODEL_SID, PS_MOD.PS_ID, PS_ST.DESCRIPTION AS PS_STATUS FROM   PS_MODEL PS_MOD\n"
                + "        JOIN HELPER_TABLE PS_ST ON PS_ST.HELPER_TABLE_SID = PS_MOD.PS_STATUS) PS_MOD \n"
                + "        ON PS_MOD.PS_MODEL_SID = PS_CON.PS_MODEL_SID ");

        boolean where = false;

        if (!conSelDTO.getContractNo().equals(StringUtils.EMPTY) && !conSelDTO.getContractNo().equals(Constants.NULL)) {
            String contractNo = conSelDTO.getContractNo().replace('*', '%');
            if (where) {
                query.append(" AND CON.CONTRACT_NO  like '").append(contractNo).append("'");
            } else {
                query.append(" WHERE CON.CONTRACT_NO  like '").append(contractNo).append(SLASH_N_SPACE);
                where = true;
            }
        }

        if (where) {
            query.append("AND CM1.COMPANY_MASTER_SID " + symbol + " (" + companyMasterSids + ")  \n ");
        } else {
            query.append(" WHERE CM1.COMPANY_MASTER_SID " + symbol + " (" + companyMasterSids + ")  \n ");
            where = true;
        }

        if (!conSelDTO.getContractName().equals(StringUtils.EMPTY) && !conSelDTO.getContractName().equals(Constants.NULL)) {
            String contractName = conSelDTO.getContractName().replace('*', '%');
            if (where) {
                query.append(" AND CON.CONTRACT_NAME like  '").append(contractName).append(SLASH_N);
            } else {
                query.append(" WHERE CON.CONTRACT_NAME like  '").append(contractName).append(SLASH_N);
                where = true;
            }
        }
        if (!conSelDTO.getContractHolder().equals(StringUtils.EMPTY) && !conSelDTO.getContractHolder().equals(Constants.NULL)) {
            String contractHolder = conSelDTO.getContractHolder();
            if (where) {
                query.append(" AND CON.CONT_HOLD_COMPANY_MASTER_SID='").append(contractHolder).append(SLASH_N);
            } else {
                query.append(" WHERE CON.CONT_HOLD_COMPANY_MASTER_SID ='").append(contractHolder).append(SLASH_N);
                where = true;
            }
        }
        if (!conSelDTO.getMarketType().equals(StringUtils.EMPTY) && !conSelDTO.getMarketType().equals(Constants.NULL)) {
            String contractType = conSelDTO.getMarketType();
            if (where) {
                query.append(" AND HEL.HELPER_TABLE_SID= '").append(contractType).append(SLASH_N);
            } else {
                query.append(" WHERE HEL.HELPER_TABLE_SID ='").append(contractType).append(SLASH_N);
                where = true;
            }
        }
        if (!conSelDTO.getRebateScheduleId().equals(StringUtils.EMPTY) && !conSelDTO.getRebateScheduleId().equals(Constants.NULL)) {
            String rsId = conSelDTO.getRebateScheduleId().replace('*', '%');
            if (where) {
                query.append(" AND RS_CON.RS_ID like '").append(rsId).append(SLASH_N);
            } else {
                query.append(" WHERE RS_CON.RS_ID like '").append(rsId).append(SLASH_N);
                where = true;
            }
        }
        if (!conSelDTO.getRebateScheduleName().equals(StringUtils.EMPTY) && !conSelDTO.getRebateScheduleName().equals(Constants.NULL)) {
            String rsName = conSelDTO.getRebateScheduleName().replace('*', '%');
            if (where) {
                query.append(" AND RS_CON.RS_NAME like  '").append(rsName).append(SLASH_N);
            } else {
                query.append(" WHERE RS_CON.RS_NAME  like '").append(rsName).append(SLASH_N);
                where = true;
            }
        }
        if (!conSelDTO.getRebateScheduleType().equals(StringUtils.EMPTY) && !conSelDTO.getRebateScheduleType().equals(Constants.NULL)) {
            String rsType = conSelDTO.getRebateScheduleType();
            if (where) {
                query.append(" AND RS_CON.RS_TYPE ='").append(rsType).append(SLASH_N_SPACE);
            } else {
                query.append(" WHERE RS_CON.RS_TYPE='").append(rsType).append(SLASH_N_SPACE);
                where = true;
            }
        }
        if (!conSelDTO.getRarCategory().equals(StringUtils.EMPTY) && !conSelDTO.getRarCategory().equals(Constants.NULL)) {
            String rarCategory = conSelDTO.getRarCategory();
            Integer integer = 0;
            Map<Integer, HelperDTO> idHelperDTOMap = helperListUtil.getIdHelperDTOMap();
            for (Map.Entry<Integer, HelperDTO> entry : idHelperDTOMap.entrySet()) {
                integer = entry.getKey();
                HelperDTO helperDTO = entry.getValue();
                if (helperDTO.getDescription().equals(rarCategory)) {
                    break;
                } else {
                    integer = 0;
                }
            }
            if (where) {
                query.append(" AND RS_CON.RS_MODEL_SID IN(select MASTER_SID from dbo.UDCS where MASTER_TYPE='RS_MODEL' AND UDC2= '").append(integer).append("')");
            } else {
                query.append(" WHERE RS_CON.RS_MODEL_SID IN(select MASTER_SID from dbo.UDCS where MASTER_TYPE='RS_MODEL' AND UDC2= '").append(integer).append("')");
            }
        }
        if (!conSelDTO.getRebateScheduleNo().equals(StringUtils.EMPTY) && !conSelDTO.getRebateScheduleNo().equals(Constants.NULL)) {
            String rsNo = conSelDTO.getRebateScheduleNo().replace('*', '%');
            if (where) {
                query.append(" AND RS_CON.RS_NO like  '").append(rsNo).append(SLASH_N_SPACE);
            } else {
                query.append(" WHERE RS_CON.RS_NO  like '").append(rsNo).append(SLASH_N_SPACE);
                where = true;
            }
        }
        if (!conSelDTO.getRebateScheduleCategory().equals(StringUtils.EMPTY) && !conSelDTO.getRebateScheduleCategory().equals(StringUtils.EMPTY)) {
            String rsCategory = conSelDTO.getRebateScheduleCategory();
            if (where) {
                query.append(" AND RS_CON.RS_CATEGORY ='").append(rsCategory).append(SLASH_N_SPACE);
            } else {
                query.append(" WHERE RS_CON.RS_CATEGORY='").append(rsCategory).append(SLASH_N_SPACE);
                where = true;
            }
        }

        if (!conSelDTO.getRebateProgramType().equals(StringUtils.EMPTY) && !conSelDTO.getRebateProgramType().equals(Constants.NULL)) {
            String rsProgType = conSelDTO.getRebateProgramType();
            if (where) {
                query.append(" AND RS_CON.REBATE_PROGRAM_TYPE ='").append(rsProgType).append(SLASH_N);
            } else {
                query.append(" WHERE RS_CON.REBATE_PROGRAM_TYPE='").append(rsProgType).append(SLASH_N_SPACE);
                where = true;
            }
        }
        if (!conSelDTO.getRebateScheduleAlias().equals(StringUtils.EMPTY) && !conSelDTO.getRebateScheduleAlias().equals(Constants.NULL)) {
            String rsAlias = conSelDTO.getRebateScheduleNo().replace('*', '%');
            if (where) {
                query.append(" AND RS_CON.RS_NO  like '").append(rsAlias).append("' ");
            } else {
                query.append(" WHERE RS_CON.RS_NO  like '").append(rsAlias).append("' ");
            }
        }
        if (!conSelDTO.getCfpNo().equals(StringUtils.EMPTY) && !conSelDTO.getCfpNo().equals(Constants.NULL)) {
            String cfpNo = conSelDTO.getCfpNo();
            if (where) {
                query.append(" AND CFP_CON.CFP_NO='").append(cfpNo).append(SLASH_N);
            } else {
                query.append(" WHERE CFP_CON.CFP_NO ='").append(cfpNo).append(SLASH_N);
                where = true;
            }
        }
        if (!conSelDTO.getIfpNo().equals(StringUtils.EMPTY) && !conSelDTO.getIfpNo().equals(Constants.NULL)) {
            String ifpNo = conSelDTO.getIfpNo();
            if (where) {
                query.append(" AND IFP_CON.IFP_NO='").append(ifpNo).append(SLASH_N);
            } else {
                query.append(" WHERE IFP_CON.IFP_NO ='").append(ifpNo).append(SLASH_N);
                where = true;
            }
        }
        if (!conSelDTO.getPsNo().equals(StringUtils.EMPTY) && !conSelDTO.getPsNo().equals(Constants.NULL)) {
            String psNo = conSelDTO.getPsNo();
            if (where) {
                query.append(" AND PS_CON.PS_NO='").append(psNo).append(SLASH_N);
            } else {
                query.append(" WHERE PS_CON.PS_NO ='").append(psNo).append(SLASH_N);
                where = true;
            }
        }

        if (parameters.containsKey(StringConstantsUtil.FILTERCONTRACT_HOLDER)) {

            if (where) {
                query.append(" AND CM.COMPANY_NAME like '").append(String.valueOf(parameters.get(StringConstantsUtil.FILTERCONTRACT_HOLDER))).append(SLASH_N);
            } else {
                query.append(" WHERE CM.COMPANY_NAME like '").append(String.valueOf(parameters.get("contractHolder"))).append(SLASH_N_SPACE);
            }
        }
        if (parameters.containsKey(FILTERCFP_NAME)) {

            if (where) {
                query.append(" AND CFP_CON.CFP_NAME like '").append(String.valueOf(parameters.get(FILTERCFP_NAME))).append(SLASH_N);
            } else {
                query.append(" WHERE CFP_CON.CFP_NAME like '").append(String.valueOf(parameters.get(FILTERCFP_NAME))).append(SLASH_N_SPACE);
            }
        }
        if (parameters.containsKey(FILTERIFP_NAME)) {

            if (where) {
                query.append(" AND IFP_CON.IFP_NAME like '").append(String.valueOf(parameters.get(FILTERIFP_NAME))).append(SLASH_N);
            } else {
                query.append(" WHERE IFP_CON.IFP_NAME like '").append(String.valueOf(parameters.get(FILTERIFP_NAME))).append(SLASH_N_SPACE);
            }
        }
        if (parameters.containsKey(StringConstantsUtil.FILTERR_S_NAME)) {

            if (where) {
                query.append(" AND RS_CON.RS_NAME like  '").append(String.valueOf(parameters.get(StringConstantsUtil.FILTERR_S_NAME))).append(SLASH_N);
            } else {
                query.append(" WHERE RS_CON.RS_NAME  like '").append(String.valueOf(parameters.get(StringConstantsUtil.FILTERR_S_NAME))).append(SLASH_N_SPACE);
            }
        }
        if (parameters.containsKey(StringConstantsUtil.FILTERP_S_NAME)) {

            if (where) {
                query.append(" AND PS_CON.PS_NAME like '").append(String.valueOf(parameters.get(StringConstantsUtil.FILTERP_S_NAME))).append(SLASH_N);
            } else {
                query.append(" WHERE PS_CON.PS_NAME like '").append(String.valueOf(parameters.get(StringConstantsUtil.FILTERP_S_NAME))).append(SLASH_N_SPACE);
            }
        }
        if (parameters.containsKey(StringConstantsUtil.FILTERCONTRACT_NO)) {

            if (where) {
                query.append(" AND CON.CONTRACT_NO  like '").append(String.valueOf(parameters.get(StringConstantsUtil.FILTERCONTRACT_NO))).append(SLASH_N);
            } else {
                query.append(" WHERE  CON.CONTRACT_NO like '").append(String.valueOf(parameters.get(StringConstantsUtil.FILTERCONTRACT_NO))).append(SLASH_N_SPACE);
            }
        }
        if (parameters.containsKey(StringConstantsUtil.FILTERCONTRACT_NAME)) {

            if (where) {
                query.append(" AND CON.CONTRACT_NAME like '").append(String.valueOf(parameters.get(StringConstantsUtil.FILTERCONTRACT_NAME))).append(SLASH_N);
            } else {
                query.append(" WHERE CON.CONTRACT_NAME like '").append(String.valueOf(parameters.get(StringConstantsUtil.FILTERCONTRACT_NAME))).append(SLASH_N_SPACE);
            }
        }
        if (parameters.containsKey(FILTERCFP_NO)) {

            if (where) {
                query.append(" AND CFP_CON.CFP_NO like '").append(String.valueOf(parameters.get(FILTERCFP_NO))).append(SLASH_N);
            } else {
                query.append(" WHERE CFP_CON.CFP_NO like '").append(String.valueOf(parameters.get(FILTERCFP_NO))).append(SLASH_N_SPACE);
            }
        }
        if (parameters.containsKey(FILTERIFP_NO)) {

            if (where) {
                query.append(" AND IFP_CON.IFP_NO like '").append(String.valueOf(parameters.get(FILTERIFP_NO))).append(SLASH_N);
            } else {
                query.append(" WHERE IFP_CON.IFP_NO like '").append(String.valueOf(parameters.get(FILTERIFP_NO))).append(SLASH_N_SPACE);
            }
        }
        if (parameters.containsKey(StringConstantsUtil.FILTERR_S_NO)) {

            if (where) {
                query.append(" AND RS_CON.RS_NO  like  '").append(String.valueOf(parameters.get(StringConstantsUtil.FILTERR_S_NO))).append(SLASH_N);
            } else {
                query.append(" WHERE  RS_CON.RS_NO like '").append(String.valueOf(parameters.get(StringConstantsUtil.FILTERR_S_NO))).append(SLASH_N_SPACE);
            }
        }
        if (parameters.containsKey(StringConstantsUtil.FILTERP_S_NO)) {

            if (where) {
                query.append(" AND PS_CON.PS_NO like '").append(String.valueOf(parameters.get(StringConstantsUtil.FILTERP_S_NO))).append(SLASH_N);
            } else {
                query.append(" WHERE PS_CON.PS_NO like '").append(String.valueOf(parameters.get(StringConstantsUtil.FILTERP_S_NO))).append(SLASH_N_SPACE);
            }
        }
        if (parameters.containsKey(StringConstantsUtil.FILTERCONTRACT_TYPE)) {

            if (where) {
                query.append(" AND CON.CONTRACT_TYPE like '").append(String.valueOf(parameters.get(StringConstantsUtil.FILTERCONTRACT_TYPE))).append(SLASH_N);
            } else {
                query.append(" WHERE CON.CONTRACT_TYPE like '").append(String.valueOf(parameters.get(StringConstantsUtil.FILTERCONTRACT_TYPE))).append(SLASH_N_SPACE);
            }
        }
        if (parameters.containsKey(StringConstantsUtil.FILTERR_AR_CATEGORY)) {
            HelperDTO type = new HelperDTO(0, Constants.SELECT_ONE);
            if (parameters.get(StringConstantsUtil.FILTERR_AR_CATEGORY) != null) {
                type = (HelperDTO) parameters.get(StringConstantsUtil.FILTERR_AR_CATEGORY);
            }
            if (where) {
                query.append(" AND RS_CON.RS_MODEL_SID IN(select MASTER_SID from dbo.UDCS where MASTER_TYPE='RS_MODEL' AND UDC2 ='").append(type.getId()).append("')");
            } else {
                query.append(" WHERE RS_CON.RS_MODEL_SID IN(select MASTER_SID from dbo.UDCS where MASTER_TYPE='RS_MODEL' AND UDC2= '").append(type.getId()).append("')");
            }
        }
        if ((parameters.get(StringConstantsUtil.FILTERCONT_START_DATEFROM) != null && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.FILTERCONT_START_DATEFROM)))
                && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FILTERCONT_START_DATEFROM))))
                && (parameters.get(StringConstantsUtil.FILTERCONT_START_DATETO) != null && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.FILTERCONT_START_DATETO)))
                && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FILTERCONT_START_DATETO))))) {
            query.append(" AND CON.START_DATE BETWEEN '");
            SimpleDateFormat parse = new SimpleDateFormat(StringConstantsUtil.DATE_HOUR_FORMAT);
            SimpleDateFormat format = new SimpleDateFormat(Constants.DATE_FORMAT);
            String from = format.format(parse.parse(String.valueOf(parameters.get(StringConstantsUtil.FILTERCONT_START_DATEFROM))));
            query.append(from);
            query.append(AND_QUOTE);
            String to = format.format(parse.parse(String.valueOf(parameters.get(StringConstantsUtil.FILTERCONT_START_DATETO))));
            query.append(to);
            query.append("' ");
        } else if ((parameters.get(StringConstantsUtil.FILTERCONT_START_DATEFROM) == null || Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.FILTERCONT_START_DATEFROM)))
                || StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FILTERCONT_START_DATEFROM))))
                && (parameters.get(StringConstantsUtil.FILTERCONT_START_DATETO) != null && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.FILTERCONT_START_DATETO)))
                && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FILTERCONT_START_DATETO))))) {
            query.append(" AND CON.START_DATE < '");
            SimpleDateFormat parse = new SimpleDateFormat(StringConstantsUtil.DATE_HOUR_FORMAT);
            SimpleDateFormat format = new SimpleDateFormat(Constants.DATE_FORMAT);
            String to = format.format(parse.parse(String.valueOf(parameters.get(StringConstantsUtil.FILTERCONT_START_DATETO))));
            query.append(to);
            query.append("' ");
        } else if ((parameters.get(StringConstantsUtil.FILTERCONT_START_DATEFROM) != null && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.FILTERCONT_START_DATEFROM)))
                && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FILTERCONT_START_DATEFROM))))
                && (parameters.get(StringConstantsUtil.FILTERCONT_START_DATETO) == null || Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.FILTERCONT_START_DATETO)))
                || StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FILTERCONT_START_DATETO))))) {
            query.append(" AND CON.START_DATE > '");
            SimpleDateFormat parse = new SimpleDateFormat(StringConstantsUtil.DATE_HOUR_FORMAT);
            SimpleDateFormat format = new SimpleDateFormat(Constants.DATE_FORMAT);
            String from = format.format(parse.parse(String.valueOf(parameters.get(StringConstantsUtil.FILTERCONT_START_DATEFROM))));
            query.append(from);
            query.append("' ");
        }
        if ((parameters.get(FILTERCONT_END_DATEFROM) != null && !Constants.NULL.equals(String.valueOf(parameters.get(FILTERCONT_END_DATEFROM)))
                && !StringUtils.isBlank(String.valueOf(parameters.get(FILTERCONT_END_DATEFROM))))
                && (parameters.get(FILTERCONT_END_DATETO) != null && !Constants.NULL.equals(String.valueOf(parameters.get(FILTERCONT_END_DATETO)))
                && !StringUtils.isBlank(String.valueOf(parameters.get(FILTERCONT_END_DATETO))))) {
            query.append(" AND CON.END_DATE BETWEEN '");
            SimpleDateFormat parse = new SimpleDateFormat(StringConstantsUtil.DATE_HOUR_FORMAT);
            SimpleDateFormat format = new SimpleDateFormat(Constants.DATE_FORMAT);
            String from = format.format(parse.parse(String.valueOf(parameters.get(FILTERCONT_END_DATEFROM))));
            query.append(from);
            query.append(AND_QUOTE);
            String to = format.format(parse.parse(String.valueOf(parameters.get(FILTERCONT_END_DATETO))));
            query.append(to);
            query.append("' ");
        } else if ((parameters.get(FILTERCONT_END_DATEFROM) == null || Constants.NULL.equals(String.valueOf(parameters.get(FILTERCONT_END_DATEFROM)))
                || StringUtils.isBlank(String.valueOf(parameters.get(FILTERCONT_END_DATEFROM))))
                && (parameters.get(FILTERCONT_END_DATETO) != null && !Constants.NULL.equals(String.valueOf(parameters.get(FILTERCONT_END_DATETO)))
                && !StringUtils.isBlank(String.valueOf(parameters.get(FILTERCONT_END_DATETO))))) {
            query.append(" AND CON.END_DATE < '");
            SimpleDateFormat parse = new SimpleDateFormat(StringConstantsUtil.DATE_HOUR_FORMAT);
            SimpleDateFormat format = new SimpleDateFormat(Constants.DATE_FORMAT);
            String to = format.format(parse.parse(String.valueOf(parameters.get(FILTERCONT_END_DATETO))));
            query.append(to);
            query.append("' ");
        } else if ((parameters.get(FILTERCONT_END_DATEFROM) != null && !Constants.NULL.equals(String.valueOf(parameters.get(FILTERCONT_END_DATEFROM)))
                && !StringUtils.isBlank(String.valueOf(parameters.get(FILTERCONT_END_DATEFROM))))
                && (parameters.get(FILTERCONT_END_DATETO) == null || Constants.NULL.equals(String.valueOf(parameters.get(FILTERCONT_END_DATETO)))
                || StringUtils.isBlank(String.valueOf(parameters.get(FILTERCONT_END_DATETO))))) {
            query.append(" AND CON.END_DATE > '");
            SimpleDateFormat parse = new SimpleDateFormat(StringConstantsUtil.DATE_HOUR_FORMAT);
            SimpleDateFormat format = new SimpleDateFormat(Constants.DATE_FORMAT);
            String from = format.format(parse.parse(String.valueOf(parameters.get(FILTERCONT_END_DATEFROM))));
            query.append(from);
            query.append("' ");
        }
        if ((parameters.get(StringConstantsUtil.FILTERCOMP_START_DATEFROM) != null && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.FILTERCOMP_START_DATEFROM)))
                && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FILTERCOMP_START_DATEFROM))))
                && (parameters.get(StringConstantsUtil.FILTERCOMP_START_DATETO) != null && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.FILTERCOMP_START_DATETO)))
                && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FILTERCOMP_START_DATETO))))) {
            query.append(" AND TEMP_TABLE.START_DATE BETWEEN '");
            SimpleDateFormat parse = new SimpleDateFormat(StringConstantsUtil.DATE_HOUR_FORMAT);
            SimpleDateFormat format = new SimpleDateFormat(Constants.DATE_FORMAT);
            String from = format.format(parse.parse(String.valueOf(parameters.get(StringConstantsUtil.FILTERCOMP_START_DATEFROM))));
            query.append(from);
            query.append(AND_QUOTE);
            String to = format.format(parse.parse(String.valueOf(parameters.get(StringConstantsUtil.FILTERCOMP_START_DATETO))));
            query.append(to);
            query.append("' ");
        } else if ((parameters.get(StringConstantsUtil.FILTERCOMP_START_DATEFROM) == null || Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.FILTERCOMP_START_DATEFROM)))
                || StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FILTERCOMP_START_DATEFROM))))
                && (parameters.get(StringConstantsUtil.FILTERCOMP_START_DATETO) != null && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.FILTERCOMP_START_DATETO)))
                && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FILTERCOMP_START_DATETO))))) {
            query.append(" AND TEMP_TABLE.START_DATE < '");
            SimpleDateFormat parse = new SimpleDateFormat(StringConstantsUtil.DATE_HOUR_FORMAT);
            SimpleDateFormat format = new SimpleDateFormat(Constants.DATE_FORMAT);
            String to = format.format(parse.parse(String.valueOf(parameters.get(StringConstantsUtil.FILTERCOMP_START_DATETO))));
            query.append(to);
            query.append("' ");
        } else if ((parameters.get(StringConstantsUtil.FILTERCOMP_START_DATEFROM) != null && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.FILTERCOMP_START_DATEFROM)))
                && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FILTERCOMP_START_DATEFROM))))
                && (parameters.get(StringConstantsUtil.FILTERCOMP_START_DATETO) == null || Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.FILTERCOMP_START_DATETO)))
                || StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FILTERCONT_START_DATETO))))) {
            query.append(" AND TEMP_TABLE.START_DATE > '");
            SimpleDateFormat parse = new SimpleDateFormat(StringConstantsUtil.DATE_HOUR_FORMAT);
            SimpleDateFormat format = new SimpleDateFormat(Constants.DATE_FORMAT);
            String from = format.format(parse.parse(String.valueOf(parameters.get(StringConstantsUtil.FILTERCOMP_START_DATEFROM))));
            query.append(from);
            query.append("' ");
        }
        if ((parameters.get(StringConstantsUtil.FILTERCOMP_END_DATEFROM) != null && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.FILTERCOMP_END_DATEFROM)))
                && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FILTERCOMP_END_DATEFROM))))
                && (parameters.get(StringConstantsUtil.FILTERCOMP_END_DATETO) != null && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.FILTERCOMP_END_DATETO)))
                && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FILTERCOMP_END_DATETO))))) {
            query.append(" AND TEMP_TABLE.END_DATE BETWEEN '");
            SimpleDateFormat parse = new SimpleDateFormat(StringConstantsUtil.DATE_HOUR_FORMAT);
            SimpleDateFormat format = new SimpleDateFormat(Constants.DATE_FORMAT);
            String from = format.format(parse.parse(String.valueOf(parameters.get(StringConstantsUtil.FILTERCOMP_END_DATEFROM))));
            query.append(from);
            query.append(AND_QUOTE);
            String to = format.format(parse.parse(String.valueOf(parameters.get(StringConstantsUtil.FILTERCOMP_END_DATETO))));
            query.append(to);
            query.append("' ");
        } else if ((parameters.get(StringConstantsUtil.FILTERCOMP_END_DATEFROM) == null || Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.FILTERCOMP_END_DATEFROM)))
                || StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FILTERCOMP_END_DATEFROM))))
                && (parameters.get(StringConstantsUtil.FILTERCOMP_END_DATETO) != null && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.FILTERCOMP_END_DATETO)))
                && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FILTERCOMP_END_DATETO))))) {
            query.append(" AND TEMP_TABLE.END_DATE < '");
            SimpleDateFormat parse = new SimpleDateFormat(StringConstantsUtil.DATE_HOUR_FORMAT);
            SimpleDateFormat format = new SimpleDateFormat(Constants.DATE_FORMAT);
            String to = format.format(parse.parse(String.valueOf(parameters.get(StringConstantsUtil.FILTERCOMP_END_DATETO))));
            query.append(to);
            query.append("' ");
        } else if ((parameters.get(StringConstantsUtil.FILTERCOMP_END_DATEFROM) != null && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.FILTERCOMP_END_DATEFROM)))
                && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FILTERCOMP_END_DATEFROM))))
                && (parameters.get(StringConstantsUtil.FILTERCOMP_END_DATETO) == null || Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.FILTERCOMP_END_DATETO)))
                || StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FILTERCOMP_END_DATETO))))) {
            query.append(" AND TEMP_TABLE.END_DATE > '");
            SimpleDateFormat parse = new SimpleDateFormat(StringConstantsUtil.DATE_HOUR_FORMAT);
            SimpleDateFormat format = new SimpleDateFormat(Constants.DATE_FORMAT);
            String from = format.format(parse.parse(String.valueOf(parameters.get(StringConstantsUtil.FILTERCOMP_END_DATEFROM))));
            query.append(from);
            query.append("' ");
        }
        if (parameters.containsKey(StringConstantsUtil.FILTERSTATUS)) {

            if (where) {
                query.append(" AND TEMP_TABLE.STATUS like '").append(String.valueOf(parameters.get(StringConstantsUtil.FILTERSTATUS))).append(SLASH_N);
            } else {
                query.append(" WHERE TEMP_TABLE.STATUS like '").append(String.valueOf(parameters.get(StringConstantsUtil.FILTERSTATUS))).append(SLASH_N_SPACE);
            }
        }
        if (parameters.containsKey(StringConstantsUtil.FILTERPROJECTION_ID_LINK)) {

            if (where) {
                query.append(" AND TEMP_TABLE.PROJECTION_MASTER_SID like '").append(String.valueOf(parameters.get(StringConstantsUtil.FILTERPROJECTION_ID_LINK))).append(SLASH_N);
            } else {
                query.append(" WHERE TEMP_TABLE.PROJECTION_MASTER_SID like '").append(String.valueOf(parameters.get(StringConstantsUtil.FILTERPROJECTION_ID_LINK))).append(SLASH_N_SPACE);
            }
        }
        if (parameters.containsKey(StringConstantsUtil.FILTERWORKFLOW_STATUS)) {

            if (where) {
                query.append(" AND WF.WORKFLOW_STATUS like '").append(String.valueOf(parameters.get(StringConstantsUtil.FILTERWORKFLOW_STATUS))).append(SLASH_N);
            } else {
                query.append(" WHERE WF.WORKFLOW_STATUS like '").append(String.valueOf(parameters.get(StringConstantsUtil.FILTERWORKFLOW_STATUS))).append(SLASH_N_SPACE);
            }
        }
        query.append(" ORDER BY  CFP_CON.CFP_CONTRACT_SID,RS_CON.RS_CONTRACT_SID,IFP_CON.IFP_CONTRACT_SID,PS_CON.PS_CONTRACT_SID OFFSET ").append(start).append(" ROWS FETCH NEXT ").append(end).append(" ROWS ONLY ");
        return query.toString();
    }

    public List<ContractResultDTO> getContractResults(List list) {
        List<ContractResultDTO> resultList = new ArrayList<>();
        ContractResultDTO dto = null;
        Map<Integer, HelperDTO> idHelperDTOMap = helperListUtil.getIdHelperDTOMap();

        if (!list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                Object obj[] = (Object[]) list.get(i);
                dto = new ContractResultDTO();
                dto.setContractHolder(Converters.convertNullToEmpty(obj[0]));
                dto.setContractNo(String.valueOf(obj[1]));
                dto.setContractName(String.valueOf(obj[NumericConstants.TWO]));
                dto.setContractType(idHelperDTOMap.get(obj[NumericConstants.THREE]));
                dto.setContStartDate(!String.valueOf(obj[NumericConstants.FOUR]).equals(Constants.NULL) ? new Date(convertToSrting((Date) obj[NumericConstants.FOUR], Constants.DATE_FORMAT)) : null);
                dto.setContEndDate(!String.valueOf(obj[NumericConstants.FIVE]).equals(Constants.NULL) ? new Date(convertToSrting((Date) obj[NumericConstants.FIVE], Constants.DATE_FORMAT)) : null);
                dto.setCfpName(String.valueOf(obj[NumericConstants.SIX]));
                dto.setCfpNo(String.valueOf(obj[NumericConstants.SEVEN]));
                dto.setIfpName(String.valueOf(obj[NumericConstants.EIGHT]));
                dto.setIfpNo(String.valueOf(obj[NumericConstants.NINE]));
                dto.setpSName(String.valueOf(obj[NumericConstants.TEN]));
                dto.setpSNo(String.valueOf(obj[NumericConstants.ELEVEN]));
                dto.setrSName(String.valueOf(obj[NumericConstants.TWELVE]));
                dto.setrSNo(String.valueOf(obj[NumericConstants.THIRTEEN]));
                dto.setrARCategory(String.valueOf(obj[NumericConstants.FOURTEEN]));
                dto.setCfpContSid(String.valueOf(obj[NumericConstants.FIFTEEN]));
                dto.setRsContSid(String.valueOf(obj[NumericConstants.SIXTEEN]));
                dto.setIfpContSid(String.valueOf(obj[NumericConstants.SEVENTEEN]));
                dto.setPsContSid(String.valueOf(obj[NumericConstants.EIGHTEEN]));
                dto.setContractMasterSid(String.valueOf(obj[NumericConstants.NINETEEN]));
                dto.setCompStartDate(!String.valueOf(obj[NumericConstants.TWENTY]).equals("null") ? new Date(convertToSrting((Date) obj[NumericConstants.TWENTY], Constants.DATE_FORMAT)) : null);
                dto.setCompEndDate(!String.valueOf(obj[NumericConstants.TWENTY_ONE]).equals("null") ? new Date(convertToSrting((Date) obj[NumericConstants.TWENTY_ONE], Constants.DATE_FORMAT)) : null);
                dto.setStatusString(String.valueOf(obj[NumericConstants.TWENTY_TWO]));
                dto.setStatusDescription(idHelperDTOMap.get(obj[NumericConstants.TWENTY_TWO]));
                dto.setCheckRecord(!String.valueOf(obj[NumericConstants.TWENTY_THREE]).equals(Constants.NULL) ? String.valueOf(obj[NumericConstants.TWENTY_THREE]).equals(Constants.TRUE) ? true : false : false);
                dto.setTpstatus(String.valueOf(obj[NumericConstants.TWENTY_TWO]));
                resultList.add(dto);
            }
        }

        return resultList;

    }

    public int buildContractCountQuery(ContractSelectionDTO conSelDTO, String userId, String sessionId, Set<Container.Filter> filters) throws ParseException {
        int count = 0;
        List<String> companyMasterSidsList;
        if (conSelDTO.getModuleName().equals(PROJECTION_DETAILS_TRANSFER.getConstant()) && conSelDTO.getScreenName().equals(TAB_TRANSFER_CONTRACT.getConstant())) {
            companyMasterSidsList = conSelDTO.getPhCompanyMasterSids();
        } else {
            companyMasterSidsList = conSelDTO.getCompanyMasterSids();
        }

        String companyMasterSids = CommonUtils.CollectionToString(companyMasterSidsList, true);
        Map<String, Object> parameters = new HashMap<>();
        if (filters != null) {
            for (Container.Filter filter : filters) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = "%" + stringFilter.getFilterString() + "%";
                    parameters.put(FILTER + stringFilter.getPropertyId(), filterString);
                } else if (filter instanceof Between) {
                    Between betweenFilter = (Between) filter;
                    Date startValue = (Date) betweenFilter.getStartValue();
                    Date endValue = (Date) betweenFilter.getEndValue();
                    parameters.put(FILTER + betweenFilter.getPropertyId() + StringConstantsUtil.FROM_FILTER, String.valueOf(startValue));
                    parameters.put(FILTER + betweenFilter.getPropertyId() + StringConstantsUtil.TO_FILTER, String.valueOf(endValue));
                } else if (filter instanceof Compare) {
                    Compare compare = (Compare) filter;
                    Compare.Operation operation = compare.getOperation();
                    Date value = (Date) compare.getValue();
                    if (Compare.Operation.GREATER_OR_EQUAL.toString().equals(operation.name())) {
                        parameters.put(FILTER + compare.getPropertyId() + StringConstantsUtil.FROM_FILTER, String.valueOf(value));
                    } else {
                        parameters.put(FILTER + compare.getPropertyId() + StringConstantsUtil.TO_FILTER, String.valueOf(value));
                    }
                }
            }
        }

        String symbol = " in ";
        if (conSelDTO.isSearchInverse()) {
            symbol = " not in ";
        }

        StringBuilder query = new StringBuilder(" DECLARE @LAST_APPROVED_PROJECTION_SID INT = (SELECT TOP 1 WM.PROJECTION_MASTER_SID \n");
        query.append("                            FROM WORKFLOW_MASTER WM \n");
        query.append("                            JOIN HELPER_TABLE WF_HT ON WF_HT.HELPER_TABLE_SID = WM.WORKFLOW_STATUS_ID \n");
        query.append("                            WHERE WF_HT.DESCRIPTION IN ( 'Approved' ) \n");
        query.append("                                AND WM.PROJECTION_MASTER_SID " + symbol + " (SELECT A.PROJECTION_MASTER_SID \n");
        query.append("                                FROM   (SELECT A.PROJECTION_MASTER_SID, B.COMPANY_MASTER_SID,Row_number() \n");
        query.append("                                                                                  OVER( \n");
        query.append("                                                                                    PARTITION BY A.PROJECTION_MASTER_SID, B.COMPANY_MASTER_SID \n");
        query.append("                                                                                    ORDER BY A.PROJECTION_MASTER_SID) AS RN \n");
        query.append("                                                                         FROM   PROJECTION_DETAILS A \n");
        query.append("                                                                             INNER JOIN PROJECTION_MASTER PM ON PM.PROJECTION_MASTER_SID = A.PROJECTION_MASTER_SID");
        query.append("                                                                         JOIN   CCP_DETAILS B ON A.CCP_DETAILS_SID = B.CCP_DETAILS_SID \n");
        query.append("                                                                         WHERE  B.COMPANY_MASTER_SID IN (" + companyMasterSids + " ) AND PM.IS_APPROVED = 'A')A \n");
        query.append("                                                                 WHERE  RN = 1 \n");
        query.append("                                                                 GROUP  BY A.PROJECTION_MASTER_SID \n");
        query.append("                                                                 HAVING Sum(( CASE \n");
        query.append("                                                                                WHEN A.COMPANY_MASTER_SID IN (" + companyMasterSids + " ) THEN -1 \n");
        query.append("                                                                                ELSE 0 \n");
        query.append("                                                                              END )) = -" + companyMasterSidsList.size() + ") \n");
        query.append("                                                                  AND WM.WORKFLOW_MASTER_SID IN (SELECT WORKFLOW_MASTER_SID \n");
        query.append("                                                                                         FROM   GCM_GLOBAL_DETAILS \n");
        query.append("                                                                                         WHERE USER_ID='").append(userId).append("' \n AND SESSION_ID='").append(sessionId).append("' \n");

        if (!conSelDTO.getScreenName().equals(StringUtils.EMPTY) && !conSelDTO.getScreenName().equals(Constants.NULL)) {
            query.append(" AND SCREEN_NAME = '").append(conSelDTO.getScreenName()).append("'");
        }
        query.append(") \n");

        query.append("                         ORDER  BY Isnull(WM.MODIFIED_DATE, WM.CREATED_DATE) DESC)");

        query.append("   SELECT ISNULL(SUM(TEM.row_count),0) from  (\n"
                + " SELECT Distinct TEMP_TABLE.PROJECTION_MASTER_SID AS 'PROJECTION_ID', \n"
                + " WF.WORKFLOW_STATUS, \n"
                + " CM.COMPANY_NAME AS 'Contract Holder',CON.CONTRACT_NO,CON.CONTRACT_NAME,HEL.DESCRIPTION AS 'CONTRACT_TYPE', \n"
                + " CFP_CON.CFP_CONTRACT_SID,RS_CON.RS_CONTRACT_SID, \n"
                + " IFP_CON.IFP_CONTRACT_SID,PS_CON.PS_CONTRACT_SID,1 as 'row_count',CON.CONTRACT_MASTER_SID,  \n "
                + " TEMP_TABLE.START_DATE AS 'COMPANY_START_DATE', TEMP_TABLE.END_DATE AS 'COMPANY_END_DATE' \n"
                + "  FROM CONTRACT_MASTER CON  \n ");
        query.append(" LEFT JOIN COMPANY_MASTER CM ON  CON.CONT_HOLD_COMPANY_MASTER_SID=CM.COMPANY_MASTER_SID \n ");
        query.append(" JOIN CFP_CONTRACT CFP_CON ON CFP_CON.CONTRACT_MASTER_SID=CON.CONTRACT_MASTER_SID \n ");
        query.append(" JOIN CFP_CONTRACT_DETAILS CFP_CON_DET ON CFP_CON_DET.CFP_CONTRACT_SID = CFP_CON.CFP_CONTRACT_SID AND CFP_CON_DET.INBOUND_STATUS <> 'D' \n");
        query.append(" JOIN IFP_CONTRACT IFP_CON ON IFP_CON.CONTRACT_MASTER_SID=CON.CONTRACT_MASTER_SID AND IFP_CON.CFP_CONTRACT_SID = CFP_CON.CFP_CONTRACT_SID \n ");
        query.append(" JOIN IFP_CONTRACT_DETAILS IFP_CON_DET ON IFP_CON_DET.IFP_CONTRACT_SID = IFP_CON.IFP_CONTRACT_SID \n");
        query.append(" JOIN PS_CONTRACT PS_CON ON PS_CON.CONTRACT_MASTER_SID=CON.CONTRACT_MASTER_SID AND IFP_CON.CFP_CONTRACT_SID = PS_CON.CFP_CONTRACT_SID \n");
        query.append("                         AND IFP_CON.IFP_CONTRACT_SID = PS_CON.IFP_CONTRACT_SID \n ");
        query.append(" JOIN RS_CONTRACT RS_CON ON RS_CON.CONTRACT_MASTER_SID=CON.CONTRACT_MASTER_SID AND IFP_CON.CFP_CONTRACT_SID = RS_CON.CFP_CONTRACT_SID \n");
        query.append("                         AND RS_CON.IFP_CONTRACT_SID = IFP_CON.IFP_CONTRACT_SID \n");
        query.append("                         AND RS_CON.PS_CONTRACT_SID = PS_CON.PS_CONTRACT_SID \n ");
        query.append(" JOIN HELPER_TABLE HEL ON HEL.HELPER_TABLE_SID=CON.CONTRACT_TYPE  \n ");

        query.append(" JOIN COMPANY_MASTER CM1 ON CM1.COMPANY_MASTER_SID = CFP_CON_DET.COMPANY_MASTER_SID \n");
        query.append(" INNER JOIN GCM_GLOBAL_DETAILS TEMP_TABLE ON TEMP_TABLE.CFP_CONTRACT_SID=CFP_CON.CFP_CONTRACT_SID AND \n");
        query.append(" TEMP_TABLE.CONTRACT_MASTER_SID=CON.CONTRACT_MASTER_SID AND TEMP_TABLE.IFP_CONTRACT_SID=IFP_CON.IFP_CONTRACT_SID  \n");
        query.append(" AND TEMP_TABLE.RS_CONTRACT_SID=RS_CON.RS_CONTRACT_SID AND TEMP_TABLE.PS_CONTRACT_SID=PS_CON.PS_CONTRACT_SID \n "
        ).append(" AND TEMP_TABLE.USER_ID= '").append(userId).append("' AND  TEMP_TABLE.SESSION_ID='").append(sessionId).append("' \n");

        if (!conSelDTO.getScreenName().equals(StringUtils.EMPTY) && !conSelDTO.getScreenName().equals(Constants.NULL)) {
            query.append(" AND TEMP_TABLE.SCREEN_NAME = '").append(conSelDTO.getScreenName()).append("'  \n");
        }

        String udcValue = "1";
        if (conSelDTO.getModuleName().equals(ADD_TRADING_PARTNER) || conSelDTO.getScreenName().equals(TAB_TRANSFER_CONTRACT.getConstant())) {
            udcValue = "2";
        }
        query.append(" AND TEMP_TABLE.OPERATION <> '").append(udcValue).append("' ");

        query.append(" JOIN (SELECT WM.WORKFLOW_MASTER_SID, WF_HT.DESCRIPTION AS WORKFLOW_STATUS \n");
        query.append("            FROM   WORKFLOW_MASTER WM \n");
        query.append("            JOIN   HELPER_TABLE WF_HT ON WF_HT.HELPER_TABLE_SID = WM.WORKFLOW_STATUS_ID \n");
        query.append("            WHERE  WF_HT.DESCRIPTION IN ('Pending', 'Rejected', 'Cancelled') \n");
        query.append("                    OR WM.PROJECTION_MASTER_SID = @LAST_APPROVED_PROJECTION_SID) WF ON WF.WORKFLOW_MASTER_SID = TEMP_TABLE.WORKFLOW_MASTER_SID \n");

        query.append(" AND CON.INBOUND_STATUS <> 'D' AND CFP_CON.INBOUND_STATUS<> 'D' AND IFP_CON.INBOUND_STATUS<> 'D' \n");
        query.append(" AND PS_CON.INBOUND_STATUS <> 'D' AND RS_CON.INBOUND_STATUS <> 'D' \n");

        query.append("AND CFP_CON.CFP_CONTRACT_SID " + symbol + " (SELECT CFP.CFP_CONTRACT_SID\n");
        query.append("                                FROM   CONTRACT_MASTER A\n");
        query.append("                                JOIN   CFP_CONTRACT CFP ON A.CONTRACT_MASTER_SID = CFP.CONTRACT_MASTER_SID AND CFP.CONTRACT_MASTER_SID=CON.CONTRACT_MASTER_SID\n");
        query.append("                                JOIN   CFP_CONTRACT_DETAILS CFP_D ON CFP_D.CFP_CONTRACT_SID = CFP.CFP_CONTRACT_SID\n");
        query.append("                                WHERE  CFP_D.COMPANY_MASTER_SID IN ( " + companyMasterSids + " )\n");
        query.append("                                GROUP  BY CFP.CFP_CONTRACT_SID\n");
        query.append("                                HAVING Sum(( CASE WHEN CFP_D.COMPANY_MASTER_SID IN ( " + companyMasterSids + " ) THEN -1 ELSE 0 END )) = -" + companyMasterSidsList.size() + ") \n");

        query.append("LEFT JOIN (SELECT CFP_MOD.CFP_NO, CFP_MODEL_SID, CFP_MOD.CFP_ID, CFP_ST.DESCRIPTION AS CFP_STATUS FROM CFP_MODEL CFP_MOD \n"
                + "        INNER JOIN HELPER_TABLE CFP_ST ON CFP_ST.HELPER_TABLE_SID = CFP_MOD.CFP_STATUS)CFP_MOD \n"
                + "         ON CFP_MOD.CFP_MODEL_SID = CFP_CON.CFP_MODEL_SID\n"
                + "   LEFT JOIN (SELECT IFP_MOD.IFP_NO, IFP_MODEL_SID, IFP_MOD.IFP_ID, IFP_ST.DESCRIPTION AS IFP_STATUS FROM IFP_MODEL IFP_MOD \n"
                + "        JOIN HELPER_TABLE IFP_ST ON IFP_ST.HELPER_TABLE_SID = IFP_MOD.IFP_STATUS) IFP_MOD \n"
                + "        ON IFP_MOD.IFP_MODEL_SID = IFP_CON.IFP_MODEL_SID\n"
                + "   LEFT JOIN (SELECT RS_MOD.RS_NO, RS_MODEL_SID, RS_MOD.RS_ID, RS_ST.DESCRIPTION AS RS_STATUS FROM RS_MODEL RS_MOD \n"
                + "        JOIN HELPER_TABLE RS_ST ON RS_ST.HELPER_TABLE_SID = RS_MOD.RS_STATUS) RS_MOD \n"
                + "        ON RS_MOD.RS_MODEL_SID = RS_CON.RS_MODEL_SID\n"
                + "   LEFT JOIN (SELECT PS_MOD.PS_NO, PS_MODEL_SID, PS_MOD.PS_ID, PS_ST.DESCRIPTION AS PS_STATUS FROM   PS_MODEL PS_MOD\n"
                + "        JOIN HELPER_TABLE PS_ST ON PS_ST.HELPER_TABLE_SID = PS_MOD.PS_STATUS) PS_MOD \n"
                + "        ON PS_MOD.PS_MODEL_SID = PS_CON.PS_MODEL_SID");

        boolean where = false;

        if (!conSelDTO.getContractNo().equals(StringUtils.EMPTY) && !conSelDTO.getContractNo().equals(Constants.NULL)) {
            String contractNo = conSelDTO.getContractNo().replace('*', '%');
            if (where) {
                query.append(" AND CON.CONTRACT_NO like '").append(contractNo).append("'");
            } else {
                query.append(" WHERE CON.CONTRACT_NO like '").append(contractNo).append(SLASH_N_SPACE);
                where = true;
            }
        }

        if (where) {
            query.append("AND CM1.COMPANY_MASTER_SID" + symbol + " (" + companyMasterSids + ") \n ");
        } else {
            query.append(" WHERE CM1.COMPANY_MASTER_SID " + symbol + " (" + companyMasterSids + ") \n ");
            where = true;
        }

        if (!conSelDTO.getContractName().equals(StringUtils.EMPTY) && !conSelDTO.getContractName().equals(Constants.NULL)) {
            String contractName = conSelDTO.getContractName().replace('*', '%');
            if (where) {
                query.append(" AND CON.CONTRACT_NAME  like '").append(contractName).append(SLASH_N);
            } else {
                query.append(" WHERE CON.CONTRACT_NAME like '").append(contractName).append(SLASH_N);
                where = true;
            }
        }
        if (!conSelDTO.getContractHolder().equals(StringUtils.EMPTY) && !conSelDTO.getContractHolder().equals(Constants.NULL)) {
            String contractHolder = conSelDTO.getContractHolder();
            if (where) {
                query.append(" AND CON.CONT_HOLD_COMPANY_MASTER_SID='").append(contractHolder).append(SLASH_N);
            } else {
                query.append(" WHERE CON.CONT_HOLD_COMPANY_MASTER_SID ='").append(contractHolder).append(SLASH_N);
                where = true;
            }
        }
        if (!conSelDTO.getMarketType().equals(StringUtils.EMPTY) && !conSelDTO.getMarketType().equals(Constants.NULL)) {
            String contractType = conSelDTO.getMarketType();
            if (where) {
                query.append(" AND  HEL.HELPER_TABLE_SID='").append(contractType).append(SLASH_N);
            } else {
                query.append(" WHERE  HEL.HELPER_TABLE_SID='").append(contractType).append(SLASH_N);
                where = true;
            }
        }
        if (!conSelDTO.getRebateScheduleId().equals(StringUtils.EMPTY) && !conSelDTO.getRebateScheduleId().equals(Constants.NULL)) {
            String rsId = conSelDTO.getRebateScheduleId().replace('*', '%');
            if (where) {
                query.append(" AND RS_CON.RS_ID like '").append(rsId).append(SLASH_N);
            } else {
                query.append(" WHERE RS_CON.RS_ID like '").append(rsId).append(SLASH_N);
                where = true;
            }
        }
        if (!conSelDTO.getRebateScheduleName().equals(StringUtils.EMPTY) && !conSelDTO.getRebateScheduleName().equals(Constants.NULL)) {
            String rsName = conSelDTO.getRebateScheduleName().replace('*', '%');
            if (where) {
                query.append(" AND RS_CON.RS_NAME like '").append(rsName).append(SLASH_N);
            } else {
                query.append(" WHERE RS_CON.RS_NAME like '").append(rsName).append(SLASH_N);
                where = true;
            }
        }
        if (!conSelDTO.getRebateScheduleType().equals(StringUtils.EMPTY) && !conSelDTO.getRebateScheduleType().equals(Constants.NULL)) {
            String rsType = conSelDTO.getRebateScheduleType();
            if (where) {
                query.append(" AND RS_CON.RS_TYPE ='").append(rsType).append(SLASH_N_SPACE);
            } else {
                query.append(" WHERE RS_CON.RS_TYPE='").append(rsType).append(SLASH_N_SPACE);
                where = true;
            }
        }
        if (!conSelDTO.getRarCategory().equals(StringUtils.EMPTY) && !conSelDTO.getRarCategory().equals(Constants.NULL)) {
            String rarCategory = conSelDTO.getRarCategory();
            Integer integer = 0;
            Map<Integer, HelperDTO> idHelperDTOMap = helperListUtil.getIdHelperDTOMap();
            for (Map.Entry<Integer, HelperDTO> entry : idHelperDTOMap.entrySet()) {
                integer = entry.getKey();
                HelperDTO helperDTO = entry.getValue();
                if (helperDTO.getDescription().equals(rarCategory)) {
                    break;
                } else {
                    integer = 0;
                }
            }
            if (where) {
                query.append(" AND RS_CON.RS_MODEL_SID IN(select MASTER_SID from dbo.UDCS where MASTER_TYPE='RS_MODEL' AND UDC2='").append(integer).append("')");
            } else {
                query.append(" WHERE RS_CON.RS_MODEL_SID IN(select MASTER_SID from dbo.UDCS where MASTER_TYPE='RS_MODEL' AND UDC2='").append(integer).append("')");
            }
        }
        if (!conSelDTO.getRebateScheduleNo().equals(StringUtils.EMPTY) && !conSelDTO.getRebateScheduleNo().equals(Constants.NULL)) {
            String rsNo = conSelDTO.getRebateScheduleNo().replace('*', '%');
            if (where) {
                query.append(" AND  RS_CON.RS_NO like '").append(rsNo).append(SLASH_N_SPACE);
            } else {
                query.append("  WHERE  RS_CON.RS_NO like '").append(rsNo).append(SLASH_N_SPACE);
                where = true;
            }
        }
        if (!conSelDTO.getRebateScheduleCategory().equals(StringUtils.EMPTY) && !conSelDTO.getRebateScheduleCategory().equals(StringUtils.EMPTY)) {
            String rsCategory = conSelDTO.getRebateScheduleCategory();
            if (where) {
                query.append(" AND RS_CON.RS_CATEGORY ='").append(rsCategory).append(SLASH_N_SPACE);
            } else {
                query.append(" WHERE RS_CON.RS_CATEGORY='").append(rsCategory).append(SLASH_N_SPACE);
                where = true;
            }
        }

        if (!conSelDTO.getRebateProgramType().equals(StringUtils.EMPTY) && !conSelDTO.getRebateProgramType().equals(Constants.NULL)) {
            String rsProgType = conSelDTO.getRebateProgramType();
            if (where) {
                query.append(" AND RS_CON.REBATE_PROGRAM_TYPE ='").append(rsProgType).append(SLASH_N);
            } else {
                query.append(" WHERE RS_CON.REBATE_PROGRAM_TYPE='").append(rsProgType).append(SLASH_N_SPACE);
                where = true;
            }
        }
        if (!conSelDTO.getRebateScheduleAlias().equals(StringUtils.EMPTY) && !conSelDTO.getRebateScheduleAlias().equals(Constants.NULL)) {
            String rsAlias = conSelDTO.getRebateScheduleNo().replace('*', '%');
            if (where) {
                query.append(" AND RS_CON.RS_NO like '" + rsAlias + "' ");
            } else {
                query.append(" WHERE RS_CON.RS_NO like '" + rsAlias + "' ");
                where = true;
            }
        }
        if (!conSelDTO.getCfpNo().equals(StringUtils.EMPTY) && !conSelDTO.getCfpNo().equals(Constants.NULL)) {
            String cfpNo = conSelDTO.getCfpNo();
            if (where) {
                query.append(" AND CFP_CON.CFP_NO='").append(cfpNo).append(SLASH_N);
            } else {
                query.append(" WHERE CFP_CON.CFP_NO ='").append(cfpNo).append(SLASH_N);
                where = true;
            }
        }
        if (!conSelDTO.getIfpNo().equals(StringUtils.EMPTY) && !conSelDTO.getIfpNo().equals(Constants.NULL)) {
            String ifpNo = conSelDTO.getIfpNo();
            if (where) {
                query.append(" AND IFP_CON.IFP_NO='").append(ifpNo).append(SLASH_N);
            } else {
                query.append(" WHERE IFP_CON.IFP_NO ='").append(ifpNo).append(SLASH_N);
                where = true;
            }
        }
        if (!conSelDTO.getPsNo().equals(StringUtils.EMPTY) && !conSelDTO.getPsNo().equals(Constants.NULL)) {
            String psNo = conSelDTO.getPsNo();
            if (where) {
                query.append(" AND PS_CON.PS_NO='").append(psNo).append(SLASH_N);
            } else {
                query.append(" WHERE PS_CON.PS_NO ='").append(psNo).append(SLASH_N);
                where = true;
            }
        }
        if (parameters.containsKey(StringConstantsUtil.FILTERCONTRACT_HOLDER)) {
            if (where) {
                query.append(" AND CM.COMPANY_NAME like '").append(String.valueOf(parameters.get(StringConstantsUtil.FILTERCONTRACT_HOLDER))).append(SLASH_N);
            } else {
                query.append(" WHERE CM.COMPANY_NAME like '").append(String.valueOf(parameters.get(StringConstantsUtil.FILTERCONTRACT_HOLDER))).append(SLASH_N_SPACE);
            }
        }
        if (parameters.containsKey(FILTERCFP_NAME)) {

            if (where) {
                query.append(" AND CFP_CON.CFP_NAME like '").append(String.valueOf(parameters.get(FILTERCFP_NAME))).append(SLASH_N);
            } else {
                query.append(" WHERE CFP_CON.CFP_NAME like '").append(String.valueOf(parameters.get(FILTERCFP_NAME))).append(SLASH_N_SPACE);
            }
        }
        if (parameters.containsKey(FILTERIFP_NAME)) {

            if (where) {
                query.append(" AND IFP_CON.IFP_NAME like '").append(String.valueOf(parameters.get(FILTERIFP_NAME))).append(SLASH_N);
            } else {
                query.append(" WHERE IFP_CON.IFP_NAME like '").append(String.valueOf(parameters.get(FILTERIFP_NAME))).append(SLASH_N_SPACE);
            }
        }
        if (parameters.containsKey(StringConstantsUtil.FILTERR_S_NAME)) {

            if (where) {
                query.append(" AND RS_CON.RS_NAME like '").append(String.valueOf(parameters.get(StringConstantsUtil.FILTERR_S_NAME))).append(SLASH_N);
            } else {
                query.append(" WHERE RS_CON.RS_NAME like '").append(String.valueOf(parameters.get(StringConstantsUtil.FILTERR_S_NAME))).append(SLASH_N_SPACE);
            }
        }
        if (parameters.containsKey(StringConstantsUtil.FILTERP_S_NAME)) {

            if (where) {
                query.append(" AND PS_CON.PS_NAME like '").append(String.valueOf(parameters.get(StringConstantsUtil.FILTERP_S_NAME))).append(SLASH_N);
            } else {
                query.append(" WHERE PS_CON.PS_NAME like '").append(String.valueOf(parameters.get(StringConstantsUtil.FILTERP_S_NAME))).append(SLASH_N_SPACE);
            }
        }
        if (parameters.containsKey(StringConstantsUtil.FILTERCONTRACT_NO)) {

            if (where) {
                query.append(" AND CON.CONTRACT_NO like '").append(String.valueOf(parameters.get(StringConstantsUtil.FILTERCONTRACT_NO))).append(SLASH_N);
            } else {
                query.append(" WHERE CON.CONTRACT_NO like '").append(String.valueOf(parameters.get(StringConstantsUtil.FILTERCONTRACT_NO))).append(SLASH_N_SPACE);
            }
        }
        if (parameters.containsKey(StringConstantsUtil.FILTERCONTRACT_NAME)) {

            if (where) {
                query.append(" AND CON.CONTRACT_NAME like '").append(String.valueOf(parameters.get(StringConstantsUtil.FILTERCONTRACT_NAME))).append(SLASH_N);
            } else {
                query.append(" WHERE  CON.CONTRACT_NAME like '").append(String.valueOf(parameters.get(StringConstantsUtil.FILTERCONTRACT_NAME))).append(SLASH_N_SPACE);
            }
        }
        if (parameters.containsKey(FILTERCFP_NO)) {

            if (where) {
                query.append(" AND CFP_CON.CFP_NO like '").append(String.valueOf(parameters.get(FILTERCFP_NO))).append(SLASH_N);
            } else {
                query.append(" WHERE CFP_CON.CFP_NO like '").append(String.valueOf(parameters.get(FILTERCFP_NO))).append(SLASH_N_SPACE);
            }
        }
        if (parameters.containsKey(FILTERIFP_NO)) {

            if (where) {
                query.append(" AND IFP_CON.IFP_NO like '").append(String.valueOf(parameters.get(FILTERIFP_NO))).append(SLASH_N);
            } else {
                query.append(" WHERE IFP_CON.IFP_NO like '").append(String.valueOf(parameters.get(FILTERIFP_NO))).append(SLASH_N_SPACE);
            }
        }
        if (parameters.containsKey(StringConstantsUtil.FILTERR_S_NO)) {

            if (where) {
                query.append(" AND RS_CON.RS_NO like '").append(String.valueOf(parameters.get(StringConstantsUtil.FILTERR_S_NO))).append(SLASH_N);
            } else {
                query.append(" WHERE RS_CON.RS_NO like '").append(String.valueOf(parameters.get(StringConstantsUtil.FILTERR_S_NO))).append(SLASH_N_SPACE);
            }
        }
        if (parameters.containsKey(StringConstantsUtil.FILTERP_S_NO)) {

            if (where) {
                query.append(" AND PS_CON.PS_NO like '").append(String.valueOf(parameters.get(StringConstantsUtil.FILTERP_S_NO))).append(SLASH_N);
            } else {
                query.append(" WHERE PS_CON.PS_NO like '").append(String.valueOf(parameters.get(StringConstantsUtil.FILTERP_S_NO))).append(SLASH_N_SPACE);
            }
        }
        if (parameters.containsKey(StringConstantsUtil.FILTERCONTRACT_TYPE)) {

            if (where) {
                query.append(" AND CON.CONTRACT_TYPE like '").append(String.valueOf(parameters.get(StringConstantsUtil.FILTERCONTRACT_TYPE))).append(SLASH_N);
            } else {
                query.append(" WHERE CON.CONTRACT_TYPE like '").append(String.valueOf(parameters.get(StringConstantsUtil.FILTERCONTRACT_TYPE))).append(SLASH_N_SPACE);
            }
        }
        if (parameters.containsKey(StringConstantsUtil.FILTERR_AR_CATEGORY)) {
            HelperDTO type = new HelperDTO(0, Constants.SELECT_ONE);
            if (parameters.get(StringConstantsUtil.FILTERR_AR_CATEGORY) != null) {
                type = (HelperDTO) parameters.get(StringConstantsUtil.FILTERR_AR_CATEGORY);
            }
            if (where) {
                query.append(" AND RS_CON.RS_MODEL_SID IN(select MASTER_SID from dbo.UDCS where MASTER_TYPE='RS_MODEL' AND UDC2='").append(type.getId()).append("')");
            } else {
                query.append(" WHERE RS_CON.RS_MODEL_SID IN(select MASTER_SID from dbo.UDCS where MASTER_TYPE='RS_MODEL' AND UDC2='").append(type.getId()).append("')");
            }
        }
        if ((parameters.get(StringConstantsUtil.FILTERCONT_START_DATEFROM) != null && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.FILTERCONT_START_DATEFROM)))
                && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FILTERCONT_START_DATEFROM))))
                && (parameters.get(StringConstantsUtil.FILTERCONT_START_DATETO) != null && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.FILTERCONT_START_DATETO)))
                && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FILTERCONT_START_DATETO))))) {
            query.append(" AND CON.START_DATE BETWEEN '");
            SimpleDateFormat parse = new SimpleDateFormat(StringConstantsUtil.DATE_HOUR_FORMAT);
            SimpleDateFormat format = new SimpleDateFormat(Constants.DATE_FORMAT);
            String from = format.format(parse.parse(String.valueOf(parameters.get(StringConstantsUtil.FILTERCONT_START_DATEFROM))));
            query.append(from);
            query.append(AND_QUOTE);
            String to = format.format(parse.parse(String.valueOf(parameters.get(StringConstantsUtil.FILTERCONT_START_DATETO))));
            query.append(to);
            query.append("' ");
        } else if ((parameters.get(StringConstantsUtil.FILTERCONT_START_DATEFROM) == null || Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.FILTERCONT_START_DATEFROM)))
                || StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FILTERCONT_START_DATEFROM))))
                && (parameters.get(StringConstantsUtil.FILTERCONT_START_DATETO) != null && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.FILTERCONT_START_DATETO)))
                && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FILTERCONT_START_DATETO))))) {
            query.append(" AND CON.START_DATE < '");
            SimpleDateFormat parse = new SimpleDateFormat(StringConstantsUtil.DATE_HOUR_FORMAT);
            SimpleDateFormat format = new SimpleDateFormat(Constants.DATE_FORMAT);
            String to = format.format(parse.parse(String.valueOf(parameters.get(StringConstantsUtil.FILTERCONT_START_DATETO))));
            query.append(to);
            query.append("' ");
        } else if ((parameters.get(StringConstantsUtil.FILTERCONT_START_DATEFROM) != null && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.FILTERCONT_START_DATEFROM)))
                && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FILTERCONT_START_DATEFROM))))
                && (parameters.get(StringConstantsUtil.FILTERCONT_START_DATETO) == null || Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.FILTERCONT_START_DATETO)))
                || StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FILTERCONT_START_DATETO))))) {
            query.append(" AND CON.START_DATE > '");
            SimpleDateFormat parse = new SimpleDateFormat(StringConstantsUtil.DATE_HOUR_FORMAT);
            SimpleDateFormat format = new SimpleDateFormat(Constants.DATE_FORMAT);
            String from = format.format(parse.parse(String.valueOf(parameters.get(StringConstantsUtil.FILTERCONT_START_DATEFROM))));
            query.append(from);
            query.append("' ");
        }
        if ((parameters.get(FILTERCONT_END_DATEFROM) != null && !Constants.NULL.equals(String.valueOf(parameters.get(FILTERCONT_END_DATEFROM)))
                && !StringUtils.isBlank(String.valueOf(parameters.get(FILTERCONT_END_DATEFROM))))
                && (parameters.get(FILTERCONT_END_DATETO) != null && !Constants.NULL.equals(String.valueOf(parameters.get(FILTERCONT_END_DATETO)))
                && !StringUtils.isBlank(String.valueOf(parameters.get(FILTERCONT_END_DATETO))))) {
            query.append(" AND CON.END_DATE BETWEEN '");
            SimpleDateFormat parse = new SimpleDateFormat(StringConstantsUtil.DATE_HOUR_FORMAT);
            SimpleDateFormat format = new SimpleDateFormat(Constants.DATE_FORMAT);
            String from = format.format(parse.parse(String.valueOf(parameters.get(FILTERCONT_END_DATEFROM))));
            query.append(from);
            query.append(AND_QUOTE);
            String to = format.format(parse.parse(String.valueOf(parameters.get(FILTERCONT_END_DATETO))));
            query.append(to);
            query.append("' ");
        } else if ((parameters.get(FILTERCONT_END_DATEFROM) == null || Constants.NULL.equals(String.valueOf(parameters.get(FILTERCONT_END_DATEFROM)))
                || StringUtils.isBlank(String.valueOf(parameters.get(FILTERCONT_END_DATEFROM))))
                && (parameters.get(FILTERCONT_END_DATETO) != null && !Constants.NULL.equals(String.valueOf(parameters.get(FILTERCONT_END_DATETO)))
                && !StringUtils.isBlank(String.valueOf(parameters.get(FILTERCONT_END_DATETO))))) {
            query.append(" AND CON.END_DATE < '");
            SimpleDateFormat parse = new SimpleDateFormat(StringConstantsUtil.DATE_HOUR_FORMAT);
            SimpleDateFormat format = new SimpleDateFormat(Constants.DATE_FORMAT);
            String to = format.format(parse.parse(String.valueOf(parameters.get(FILTERCONT_END_DATETO))));
            query.append(to);
            query.append("' ");
        } else if ((parameters.get(FILTERCONT_END_DATEFROM) != null && !Constants.NULL.equals(String.valueOf(parameters.get(FILTERCONT_END_DATEFROM)))
                && !StringUtils.isBlank(String.valueOf(parameters.get(FILTERCONT_END_DATEFROM))))
                && (parameters.get(FILTERCONT_END_DATETO) == null || Constants.NULL.equals(String.valueOf(parameters.get(FILTERCONT_END_DATETO)))
                || StringUtils.isBlank(String.valueOf(parameters.get(FILTERCONT_END_DATETO))))) {
            query.append(" AND CON.END_DATE > '");
            SimpleDateFormat parse = new SimpleDateFormat(StringConstantsUtil.DATE_HOUR_FORMAT);
            SimpleDateFormat format = new SimpleDateFormat(Constants.DATE_FORMAT);
            String from = format.format(parse.parse(String.valueOf(parameters.get(FILTERCONT_END_DATEFROM))));
            query.append(from);
            query.append("' ");
        }
        if ((parameters.get(StringConstantsUtil.FILTERCOMP_START_DATEFROM) != null && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.FILTERCOMP_START_DATEFROM)))
                && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FILTERCOMP_START_DATEFROM))))
                && (parameters.get(StringConstantsUtil.FILTERCOMP_START_DATETO) != null && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.FILTERCOMP_START_DATETO)))
                && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FILTERCOMP_START_DATETO))))) {
            query.append(" AND TEMP_TABLE.START_DATE BETWEEN '");
            SimpleDateFormat parse = new SimpleDateFormat(StringConstantsUtil.DATE_HOUR_FORMAT);
            SimpleDateFormat format = new SimpleDateFormat(Constants.DATE_FORMAT);
            String from = format.format(parse.parse(String.valueOf(parameters.get(StringConstantsUtil.FILTERCOMP_START_DATEFROM))));
            query.append(from);
            query.append(AND_QUOTE);
            String to = format.format(parse.parse(String.valueOf(parameters.get(StringConstantsUtil.FILTERCOMP_START_DATETO))));
            query.append(to);
            query.append("' ");
        } else if ((parameters.get(StringConstantsUtil.FILTERCOMP_START_DATEFROM) == null || Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.FILTERCOMP_START_DATEFROM)))
                || StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FILTERCOMP_START_DATEFROM))))
                && (parameters.get(StringConstantsUtil.FILTERCOMP_START_DATETO) != null && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.FILTERCOMP_START_DATETO)))
                && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FILTERCOMP_START_DATETO))))) {
            query.append(" AND TEMP_TABLE.START_DATE < '");
            SimpleDateFormat parse = new SimpleDateFormat(StringConstantsUtil.DATE_HOUR_FORMAT);
            SimpleDateFormat format = new SimpleDateFormat(Constants.DATE_FORMAT);
            String to = format.format(parse.parse(String.valueOf(parameters.get(StringConstantsUtil.FILTERCOMP_START_DATETO))));
            query.append(to);
            query.append("' ");
        } else if ((parameters.get(StringConstantsUtil.FILTERCOMP_START_DATEFROM) != null && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.FILTERCOMP_START_DATEFROM)))
                && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FILTERCOMP_START_DATEFROM))))
                && (parameters.get(StringConstantsUtil.FILTERCOMP_START_DATETO) == null || Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.FILTERCOMP_START_DATETO)))
                || StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FILTERCONT_START_DATETO))))) {
            query.append(" AND TEMP_TABLE.START_DATE > '");
            SimpleDateFormat parse = new SimpleDateFormat(StringConstantsUtil.DATE_HOUR_FORMAT);
            SimpleDateFormat format = new SimpleDateFormat(Constants.DATE_FORMAT);
            String from = format.format(parse.parse(String.valueOf(parameters.get(StringConstantsUtil.FILTERCOMP_START_DATEFROM))));
            query.append(from);
            query.append("' ");
        }
        if ((parameters.get(StringConstantsUtil.FILTERCOMP_END_DATEFROM) != null && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.FILTERCOMP_END_DATEFROM)))
                && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FILTERCOMP_END_DATEFROM))))
                && (parameters.get(StringConstantsUtil.FILTERCOMP_END_DATETO) != null && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.FILTERCOMP_END_DATETO)))
                && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FILTERCOMP_END_DATETO))))) {
            query.append(" AND TEMP_TABLE.END_DATE BETWEEN '");
            SimpleDateFormat parse = new SimpleDateFormat(StringConstantsUtil.DATE_HOUR_FORMAT);
            SimpleDateFormat format = new SimpleDateFormat(Constants.DATE_FORMAT);
            String from = format.format(parse.parse(String.valueOf(parameters.get(StringConstantsUtil.FILTERCOMP_END_DATEFROM))));
            query.append(from);
            query.append(AND_QUOTE);
            String to = format.format(parse.parse(String.valueOf(parameters.get(StringConstantsUtil.FILTERCOMP_END_DATETO))));
            query.append(to);
            query.append("' ");
        } else if ((parameters.get(StringConstantsUtil.FILTERCOMP_END_DATEFROM) == null || Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.FILTERCOMP_END_DATEFROM)))
                || StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FILTERCOMP_END_DATEFROM))))
                && (parameters.get(StringConstantsUtil.FILTERCOMP_END_DATETO) != null && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.FILTERCOMP_END_DATETO)))
                && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FILTERCOMP_END_DATETO))))) {
            query.append(" AND TEMP_TABLE.END_DATE < '");
            SimpleDateFormat parse = new SimpleDateFormat(StringConstantsUtil.DATE_HOUR_FORMAT);
            SimpleDateFormat format = new SimpleDateFormat(Constants.DATE_FORMAT);
            String to = format.format(parse.parse(String.valueOf(parameters.get(StringConstantsUtil.FILTERCOMP_END_DATETO))));
            query.append(to);
            query.append("' ");
        } else if ((parameters.get(StringConstantsUtil.FILTERCOMP_END_DATEFROM) != null && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.FILTERCOMP_END_DATEFROM)))
                && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FILTERCOMP_END_DATEFROM))))
                && (parameters.get(StringConstantsUtil.FILTERCOMP_END_DATETO) == null || Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.FILTERCOMP_END_DATETO)))
                || StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FILTERCOMP_END_DATETO))))) {
            query.append(" AND TEMP_TABLE.END_DATE > '");
            SimpleDateFormat parse = new SimpleDateFormat(StringConstantsUtil.DATE_HOUR_FORMAT);
            SimpleDateFormat format = new SimpleDateFormat(Constants.DATE_FORMAT);
            String from = format.format(parse.parse(String.valueOf(parameters.get(StringConstantsUtil.FILTERCOMP_END_DATEFROM))));
            query.append(from);
            query.append("' ");
        }
        if (parameters.containsKey(StringConstantsUtil.FILTERSTATUS)) {

            if (where) {
                query.append(" AND TEMP_TABLE.STATUS like '").append(String.valueOf(parameters.get(StringConstantsUtil.FILTERSTATUS))).append(SLASH_N);
            } else {
                query.append(" WHERE TEMP_TABLE.STATUS like '").append(String.valueOf(parameters.get(StringConstantsUtil.FILTERSTATUS))).append(SLASH_N_SPACE);
            }
        }
        if (parameters.containsKey(StringConstantsUtil.FILTERPROJECTION_ID_LINK)) {

            if (where) {
                query.append(" AND TEMP_TABLE.PROJECTION_MASTER_SID like '").append(String.valueOf(parameters.get(StringConstantsUtil.FILTERPROJECTION_ID_LINK))).append(SLASH_N);
            } else {
                query.append(" WHERE TEMP_TABLE.PROJECTION_MASTER_SID like '").append(String.valueOf(parameters.get(StringConstantsUtil.FILTERPROJECTION_ID_LINK))).append(SLASH_N_SPACE);
            }
        }
        if (parameters.containsKey(StringConstantsUtil.FILTERWORKFLOW_STATUS)) {

            if (where) {
                query.append(" AND WF.WORKFLOW_STATUS like '").append(String.valueOf(parameters.get(StringConstantsUtil.FILTERWORKFLOW_STATUS))).append(SLASH_N);
            } else {
                query.append(" WHERE WF.WORKFLOW_STATUS like '").append(String.valueOf(parameters.get(StringConstantsUtil.FILTERWORKFLOW_STATUS))).append(SLASH_N_SPACE);
            }
        }

        query.append("  ) TEM ");
        List list = (List) DAO.executeSelect(query.toString());

        if (list != null && list.get(0) != null) {

            count = Integer.parseInt(String.valueOf(list.get(0)));
        }
        return count;
    }

    /**
     * Converts the date in string from the given input format to the given
     * output format.
     *
     * @param stringDate
     * @param inputFormat
     * @param outputFormat
     * @return - formatted Date in String object
     * @throws ParseException
     */
    public String convertStringToDate(String stringDate, String inputFormat, String outputFormat) throws ParseException {
        SimpleDateFormat inputDateFormatter = new SimpleDateFormat(inputFormat);
        SimpleDateFormat outputDateFormatter = new SimpleDateFormat(outputFormat);
        Date date;
        date = inputDateFormatter.parse(stringDate);
        return outputDateFormatter.format(date);
    }

    public int callCheckRecUpdate(boolean checkValue, ContractResultDTO dto, String contractType, SessionDTO session) {
        int count = 0;
        StringBuilder query = new StringBuilder("   ");
        query.append("UPDATE IMTD SET  CHECK_RECORD='").append(checkValue ? 1 : 0).append("'");
        query.append("FROM   GCM_GLOBAL_DETAILS IMTD\n"
                + " WHERE CONTRACT_MASTER_SID='").append(!dto.getContractMasterSid().equals(StringUtils.EMPTY) ? dto.getContractMasterSid() : 0).append("'  ");
        query.append("AND CFP_CONTRACT_SID= '").append(!dto.getCfpContSid().equals(StringUtils.EMPTY) ? dto.getCfpContSid() : 0).append("'  ");
        query.append("AND IFP_CONTRACT_SID= '").append(!dto.getIfpContSid().equals(StringUtils.EMPTY) ? dto.getIfpContSid() : 0).append("'  ");
        query.append("AND RS_CONTRACT_SID= '").append(!dto.getRsContSid().equals(StringUtils.EMPTY) ? dto.getRsContSid() : 0).append("'  ");
        query.append("AND PS_CONTRACT_SID= '").append(!dto.getPsContSid().equals(StringUtils.EMPTY) ? dto.getPsContSid() : 0).append("'  ");
        if (!dto.getProjectionId().isEmpty()) {
            query.append("AND PROJECTION_MASTER_SID= '").append(dto.getProjectionId()).append("'  ");
        }
        query.append("AND USER_ID= '").append(session.getUserId()).append("'  ");
        query.append("AND SESSION_ID= '").append(session.getSessionId()).append("'  ");
        if (!contractType.equals(StringUtils.EMPTY) && !contractType.equals(Constants.NULL)) {
            query.append("AND SCREEN_NAME ='").append(contractType).append("'");
        }

        count = CompanyMasterLocalServiceUtil.executeUpdateQuery(query.toString());
        return count;
    }

    public int callCheckRecInsert(boolean checkValue, ContractResultDTO dto, String contractType, SessionDTO session) {
        int count = 0;
        StringBuilder query = new StringBuilder("   ");
        query.append("INSERT INTO IMTD_ITEM_PRICE_REBATE_DETAILS ");
        if (!contractType.equals(StringUtils.EMPTY) && !contractType.equals(Constants.NULL)) {
            query.append(" (CHECK_RECORD,CONTRACT_MASTER_SID,CFP_MODEL_SID,IFP_MODEL_SID,RS_MODEL_SID,PS_MODEL_SID,USERS_SID,SESSION_ID,OPERATION)  ");
        } else {
            query.append(" (CHECK_RECORD,CONTRACT_MASTER_SID,CFP_MODEL_SID,IFP_MODEL_SID,RS_MODEL_SID,PS_MODEL_SID,USERS_SID,SESSION_ID)  ");
        }

        query.append("VALUES (  ");
        query.append("'").append(checkValue ? 1 : 0).append("'");
        query.append(",").append(!dto.getContractMasterSid().equals(StringUtils.EMPTY) ? dto.getContractMasterSid() : 0);
        query.append(",").append(!dto.getCfpContSid().equals(StringUtils.EMPTY) ? dto.getCfpContSid() : 0);
        query.append(",").append(!dto.getIfpContSid().equals(StringUtils.EMPTY) ? dto.getIfpContSid() : 0);
        query.append(",").append(!dto.getRsContSid().equals(StringUtils.EMPTY) ? dto.getRsContSid() : 0);
        query.append(",").append(!dto.getPsContSid().equals(StringUtils.EMPTY) ? dto.getPsContSid() : 0);
        query.append(",'").append(session.getUserId()).append("'");
        query.append(",'").append(session.getSessionId()).append("'");
        if (!contractType.equals(StringUtils.EMPTY) && !contractType.equals(Constants.NULL)) {
            query.append(",'").append(contractType).append("'");
        }
        query.append("  )");
        count = CompanyMasterLocalServiceUtil.executeUpdateQuery(query.toString());

        return count;
    }

    public int callDateUpdate(Date date, ContractResultDTO dto, SessionDTO session, String contractType, String startOrEnd) {
        int count = 0;
        SimpleDateFormat dateFormater = new SimpleDateFormat(YYYY_M_MDD_H_HMMSS_SSS);
        StringBuilder query = new StringBuilder("   ");

        query.append("UPDATE GCM_GLOBAL_DETAILS SET  ").append(startOrEnd).append("='").append(dateFormater.format(date)).append("'");
        query.append("WHERE CONTRACT_MASTER_SID='").append(!dto.getContractMasterSid().equals(StringUtils.EMPTY) ? dto.getContractMasterSid() : 0).append("'  ");
        query.append("AND CFP_CONTRACT_SID='").append(!dto.getCfpContSid().equals(StringUtils.EMPTY) ? dto.getCfpContSid() : 0).append("'  ");
        query.append("AND IFP_CONTRACT_SID='").append(!dto.getIfpContSid().equals(StringUtils.EMPTY) ? dto.getIfpContSid() : 0).append("'  ");
        query.append("AND RS_CONTRACT_SID='").append(!dto.getRsContSid().equals(StringUtils.EMPTY) ? dto.getRsContSid() : 0).append("'  ");
        query.append("AND PS_CONTRACT_SID='").append(!dto.getPsContSid().equals(StringUtils.EMPTY) ? dto.getPsContSid() : 0).append("'  ");
        query.append("AND PROJECTION_MASTER_SID='").append(dto.getProjectionId()).append("'  ");
        query.append("AND USER_ID= '").append(session.getUserId()).append("'  ");
        query.append("AND SESSION_ID= '").append(session.getSessionId()).append("'  ");
        if (!contractType.equals(StringUtils.EMPTY) && !contractType.equals(Constants.NULL)) {
            query.append("AND SCREEN_NAME= '").append(contractType).append("'");
        }

        count = CompanyMasterLocalServiceUtil.executeUpdateQuery(query.toString());
        return count;
    }

    public int callDateInsert(Date date, ContractResultDTO dto, SessionDTO session, String contractType, String startOrEnd) {
        int count = 0;
        SimpleDateFormat dateFormater = new SimpleDateFormat(YYYY_M_MDD_H_HMMSS_SSS);

        StringBuilder query = new StringBuilder("   ");
        query.append("INSERT INTO GCM_GLOBAL_DETAILS ");
        if (!contractType.equals(StringUtils.EMPTY) && !contractType.equals(Constants.NULL)) {
            query.append(" (").append(startOrEnd).append(",CONTRACT_MASTER_SID,CFP_CONTRACT_SID,IFP_CONTRACT_SID,RS_CONTRACT_SID,PS_CONTRACT_SID,USER_ID,SESSION_ID,SCREEN_NAME)  ");
        } else {
            query.append(" (").append(startOrEnd).append(",CONTRACT_MASTER_SID,CFP_CONTRACT_SID,IFP_CONTRACT_SID,RS_CONTRACT_SID,PS_CONTRACT_SID,USER_ID,SESSION_ID)  ");
        }

        query.append("VALUES (  ");
        query.append("'").append(dateFormater.format(date)).append("'");
        query.append(",").append(!dto.getContractMasterSid().equals(StringUtils.EMPTY) ? dto.getContractMasterSid() : 0);
        query.append(",").append(!dto.getCfpContSid().equals(StringUtils.EMPTY) ? dto.getCfpContSid() : 0);
        query.append(",").append(!dto.getIfpContSid().equals(StringUtils.EMPTY) ? dto.getIfpContSid() : 0);
        query.append(",").append(!dto.getRsContSid().equals(StringUtils.EMPTY) ? dto.getRsContSid() : 0);
        query.append(",").append(!dto.getPsContSid().equals(StringUtils.EMPTY) ? dto.getPsContSid() : 0);
        query.append(",'").append(session.getUserId()).append("'");
        query.append(",'").append(session.getSessionId()).append("'");
        if (!contractType.equals(StringUtils.EMPTY) && !contractType.equals(Constants.NULL)) {
            query.append(",'").append(contractType).append("'");
        }
        query.append("  )");
        count = CompanyMasterLocalServiceUtil.executeUpdateQuery(query.toString());

        return count;
    }

    public int callStatusUpdate(int status, ContractResultDTO dto, SessionDTO session, String contractType) {
        int count = 0;
        StringBuilder query = new StringBuilder("   ");
        query.append("UPDATE GCM_GLOBAL_DETAILS SET STATUS='").append(status).append("' ");
        query.append("WHERE CONTRACT_MASTER_SID='").append(!dto.getContractMasterSid().equals(StringUtils.EMPTY) ? dto.getContractMasterSid() : 0).append("'  ");
        query.append("AND CFP_CONTRACT_SID='").append(!dto.getCfpContSid().equals(StringUtils.EMPTY) ? dto.getCfpContSid() : 0).append("'  ");
        query.append("AND IFP_CONTRACT_SID='").append(!dto.getIfpContSid().equals(StringUtils.EMPTY) ? dto.getIfpContSid() : 0).append("'  ");
        query.append("AND RS_CONTRACT_SID='").append(!dto.getRsContSid().equals(StringUtils.EMPTY) ? dto.getRsContSid() : 0).append("'  ");
        query.append("AND PS_CONTRACT_SID='").append(!dto.getPsContSid().equals(StringUtils.EMPTY) ? dto.getPsContSid() : 0).append("'  ");
        query.append("AND PROJECTION_MASTER_SID='").append(dto.getProjectionId()).append("'  ");
        query.append("AND USER_ID='").append(session.getUserId()).append("'  ");
        query.append("AND SESSION_ID='").append(session.getSessionId()).append("'  ");
        if (!contractType.equals(StringUtils.EMPTY) && !contractType.equals(Constants.NULL)) {
            query.append("AND SCREEN_NAME='").append(contractType).append("'");
        }

        count = CompanyMasterLocalServiceUtil.executeUpdateQuery(query.toString());
        return count;
    }

    public int callStatusInsert(int status, ContractResultDTO dto, SessionDTO session, String contractType) {
        int count = 0;
        StringBuilder query = new StringBuilder("   ");
        query.append("INSERT INTO GCM_GLOBAL_DETAILS ");
        if (!contractType.equals(StringUtils.EMPTY) && !contractType.equals(Constants.NULL)) {
            query.append(" (STATUS,CONTRACT_MASTER_SID,CFP_CONTRACT_SID,IFP_CONTRACT_SID,RS_CONTRACT_SID,PS_CONTRACT_SID,USER_ID,SESSION_ID,SCREEN_NAME)  ");
        } else {
            query.append(" (STATUS,CONTRACT_MASTER_SID,CFP_CONTRACT_SID,IFP_CONTRACT_SID,RS_CONTRACT_SID,PS_CONTRACT_SID,USER_ID,SESSION_ID)  ");
        }

        query.append("VALUES(  ");
        query.append("'").append(status).append("'");
        query.append(",").append(!dto.getContractMasterSid().equals(StringUtils.EMPTY) ? dto.getContractMasterSid() : 0);
        query.append(",").append(!dto.getCfpContSid().equals(StringUtils.EMPTY) ? dto.getCfpContSid() : 0);
        query.append(",").append(!dto.getIfpContSid().equals(StringUtils.EMPTY) ? dto.getIfpContSid() : 0);
        query.append(",").append(!dto.getRsContSid().equals(StringUtils.EMPTY) ? dto.getRsContSid() : 0);
        query.append(",").append(!dto.getPsContSid().equals(StringUtils.EMPTY) ? dto.getPsContSid() : 0);
        query.append(",'").append(session.getUserId()).append("'");
        query.append(",'").append(session.getSessionId()).append("'");
        if (!contractType.equals(StringUtils.EMPTY) && !contractType.equals(Constants.NULL)) {
            query.append(",'").append(contractType).append("'");
        }
        query.append("  )");
        count = CompanyMasterLocalServiceUtil.executeUpdateQuery(query.toString());

        return count;
    }

    public boolean massUpdate(String fieldName, String userId, String sessionId, String contractType, Object value) {
        boolean updateStatus = false;

        SimpleDateFormat dateFormater = new SimpleDateFormat(YYYY_M_MDD_H_HMMSS_SSS);
        StringBuilder query = new StringBuilder("   ");
        query.append("UPDATE GCM_GLOBAL_DETAILS SET   ");
        if (fieldName.equals("Status")) {

            query.append(" STATUS='").append((Integer) value).append("' ");
        } else if (fieldName.equals("Company Start Date")) {
            if (value == null) {
                query.append(" START_DATE = null ");
            } else {
                query.append(" START_DATE='").append(dateFormater.format((Date) value)).append("' ");
            }
        } else if (fieldName.equals("Company End Date")) {
            if (value == null) {
                query.append(" END_DATE = null ");
            } else {
                query.append(" END_DATE = '").append(dateFormater.format((Date) value)).append("' ");
            }
        }
        query.append("WHERE CHECK_RECORD='1' ");

        query.append("AND USER_ID='").append(userId).append("'  ");
        query.append("AND SESSION_ID='").append(sessionId).append("'  ");
        if (!contractType.equals(StringUtils.EMPTY) && !contractType.equals(Constants.NULL)) {
            query.append("AND SCREEN_NAME='").append(contractType).append("'");
        }

        CompanyMasterLocalServiceUtil.executeUpdateQuery(query.toString());
        return updateStatus;
    }

    public boolean isValuesPresentAlready(String fieldName, String userId, String sessionId, String contractType) {
        StringBuilder query = new StringBuilder(" ");
        query.append("SELECT count(*) from GCM_GLOBAL_DETAILS where ");
        if (fieldName.equals("Status")) {

            query.append("STATUS IS NOT NULL AND STATUS <> '0'");
        } else if (fieldName.equals("Company Start Date")) {

            query.append("START_DATE IS NOT NULL");
        } else if (fieldName.equals("Company End Date")) {

            query.append("END_DATE IS NOT NULL");
        }
        query.append(" AND CHECK_RECORD='1'");
        query.append(" AND USER_ID='").append(userId).append("'  ");
        query.append(" AND SESSION_ID='").append(sessionId).append("'  ");

        if (!contractType.equals(StringUtils.EMPTY) && !contractType.equals(Constants.NULL)) {
            query.append(" AND SCREEN_NAME='").append(contractType).append("'");
        }

        int count = CommonUtils.convertToInteger(String.valueOf(((List) DAO.executeSelect(query.toString())).get(0)));
        return count != 0;
    }

    public static List getSubmittedRecords(SessionDTO session, String screenName, boolean isCount) {
        String udcValue = "1";

        if (session.getModuleName().equals(ADD_TRADING_PARTNER.getConstant()) || screenName.equals(TAB_TRANSFER_CONTRACT.getConstant())) {
            udcValue = "2";
        }
        StringBuilder customSql = new StringBuilder();
        if (isCount) {
            customSql.append("SELECT count(*) from (");
        }
        customSql.append(CustomSQLUtil.get("get Submitted Records"));
        customSql.replace(customSql.indexOf("?"), customSql.indexOf("?") + 1, session.getUserId());
        customSql.replace(customSql.indexOf("?"), customSql.indexOf("?") + 1, session.getSessionId());
        customSql.replace(customSql.indexOf("?"), customSql.indexOf("?") + 1, udcValue);

        if (!screenName.equals(StringUtils.EMPTY) && !screenName.equals(Constants.NULL)) {
            customSql.replace(customSql.indexOf("?"), customSql.indexOf("?") + 1, screenName);
        } else {
            customSql.replace(customSql.indexOf("?"), customSql.indexOf("?") + 1, "%");
        }

        if (isCount) {
            customSql.append(")TEM");
        }
        return CompanyMasterLocalServiceUtil.executeQuery(String.valueOf(customSql));
    }

    public String getLevelOneHierarchy(String userId, String sessionId) {
        StringBuilder query = new StringBuilder();

        query.append(" select distinct CON_M.CONTRACT_ID  as conId,CON_M.CONTRACT_NO as conNo,CON_M.CONTRACT_NAME as conName,CON_M.CONTRACT_MASTER_SID  as sysId from CONTRACT_MASTER CON_M     ");
        query.append(" join GCM_GLOBAL_DETAILS TEMP_TABLE ON TEMP_TABLE.CONTRACT_MASTER_SID=CON_M.CONTRACT_MASTER_SID     ");
        query.append(" AND TEMP_TABLE.USER_ID = '").append(userId).append("' AND TEMP_TABLE.SESSION_ID ='").append(sessionId).append("'  AND TEMP_TABLE.OPERATION='2'      ");

        return query.toString();
    }

    public String getLevelTwoHierarchy(String userId, String sessionId, int contractSid) {
        StringBuilder query = new StringBuilder();

        query.append(" select distinct CFP_MOD.CFP_ID as cfpId,CFP_M.CFP_NO as cfpNo,CFP_M.CFP_NAME as cfpName,CFP_M.CFP_CONTRACT_SID as sysId from CFP_CONTRACT CFP_M        ");
        query.append(" join GCM_GLOBAL_DETAILS TEMP_TABLE ON TEMP_TABLE.CFP_CONTRACT_SID=CFP_M.CFP_CONTRACT_SID    ");
        query.append(" LEFT JOIN CFP_MODEL CFP_MOD ON CFP_MOD.CFP_MODEL_SID=CFP_M.CFP_MODEL_SID    ");
        query.append(" AND  TEMP_TABLE.USER_ID='").append(userId).append("' AND TEMP_TABLE.SESSION_ID= '").append(sessionId).append("'   AND TEMP_TABLE.OPERATION='2'    ");
        query.append(" where CFP_MOD.CFP_ID IS NOT NULL and CFP_M.CFP_NO IS NOT NULL AND CFP_M.CFP_NAME IS NOT NULL AND CFP_M.CONTRACT_MASTER_SID='").append(contractSid).append("'   ");

        return query.toString();
    }

    public String getLevelThreeHierarchy(String userId, String sessionId, int contractSid, int cfpSid) {
        StringBuilder query = new StringBuilder();

        query.append(" select distinct IFP_MOD.IFP_ID as ifpId,IFP_M.IFP_NO as ifpNo,IFP_M.IFP_NAME as ifpName,IFP_M.IFP_CONTRACT_SID as sysId from IFP_CONTRACT IFP_M        ");
        query.append(" join GCM_GLOBAL_DETAILS TEMP_TABLE ON TEMP_TABLE.IFP_CONTRACT_SID=IFP_M.IFP_CONTRACT_SID    ");
        query.append(" LEFT JOIN IFP_MODEL IFP_MOD ON IFP_MOD.iFP_MODEL_SID=iFP_M.IFP_MODEL_SID    ");
        query.append(" AND  TEMP_TABLE.USER_ID='").append(userId).append("'  AND TEMP_TABLE.SESSION_ID='").append(sessionId).append("' AND TEMP_TABLE.OPERATION='2'    ");
        query.append(" where IFP_MOD.IFP_ID IS NOT NULL and IFP_M.IFP_NO IS NOT NULL AND IFP_M.IFP_NAME IS NOT NULL AND IFP_M.CONTRACT_MASTER_SID='").append(contractSid).append("' AND IFP_M.CFP_CONTRACT_SID='").append(cfpSid).append("'   ");

        return query.toString();
    }

    public String getLevelFourHierarchy(String userId, String sessionId, int contractSid, int cfpSid, int ifpSid) {
        StringBuilder query = new StringBuilder();

        query.append(" select distinct PS_MOD.PS_ID as psId,PS_CON.PS_NO as psNo,PS_CON.PS_NAME as psName,PS_CON.PS_CONTRACT_SID as sysId from PS_CONTRACT PS_CON        ");
        query.append(" join GCM_GLOBAL_DETAILS TEMP_TABLE ON TEMP_TABLE.PS_CONTRACT_SID=PS_CON.PS_CONTRACT_SID    ");
        query.append(" LEFT JOIN PS_MODEL PS_MOD ON PS_MOD.PS_MODEL_SID=PS_CON.PS_MODEL_SID    ");
        query.append(" AND TEMP_TABLE.USER_ID='").append(userId).append("'  AND TEMP_TABLE.SESSION_ID='").append(sessionId).append("'  AND TEMP_TABLE.OPERATION='2'     ");
        query.append(" where PS_MOD.PS_ID IS NOT NULL and PS_CON.PS_NO IS NOT NULL AND PS_CON.PS_NAME IS NOT NULL   ");
        query.append(" AND PS_CON.CONTRACT_MASTER_SID='").append(contractSid).append("' AND PS_CON.CFP_CONTRACT_SID='").append(cfpSid).append("' AND PS_CON.IFP_CONTRACT_SID='").append(ifpSid).append("'    ");

        return query.toString();
    }

    public String getLevelFiveHierarchy(String userId, String sessionId, int contractSid, int cfpSid, int ifpSid, int psSid) {
        StringBuilder query = new StringBuilder();

        query.append(" select distinct RS_MOD.RS_ID as rsId,RS_CON.RS_NO as rsNo,RS_CON.RS_NAME as rsName,RS_CON.RS_CONTRACT_SID as sysId from RS_CONTRACT RS_CON        ");
        query.append(" join GCM_GLOBAL_DETAILS TEMP_TABLE ON TEMP_TABLE.RS_CONTRACT_SID=RS_CON.RS_CONTRACT_SID    ");
        query.append(" LEFT JOIN RS_MODEL RS_MOD ON RS_MOD.RS_MODEL_SID=RS_CON.RS_MODEL_SID    ");
        query.append(" AND TEMP_TABLE.USER_ID='").append(userId).append("' AND TEMP_TABLE.SESSION_ID='").append(sessionId).append("'  AND TEMP_TABLE.OPERATION='2'    ");
        query.append(" where RS_MOD.RS_ID IS NOT NULL and RS_CON.RS_NO IS NOT NULL AND RS_CON.RS_NAME IS NOT NULL   ");
        query.append(" AND RS_CON.CONTRACT_MASTER_SID='").append(contractSid).append("' AND RS_CON.CFP_CONTRACT_SID='").append(cfpSid).append("' AND RS_CON.IFP_CONTRACT_SID='").append(ifpSid).append("'   ");
        query.append(" AND RS_CON.PS_CONTRACT_SID='").append(psSid).append("'   ");

        return query.toString();
    }

    public List<ContractsDetailsDto> getDasboardResults(String query, int levelIndicator, int contractSid, int cfpSid, int ifpSid, int psSid, ContractsDetailsDto parent1, ContractsDetailsDto parent2, ContractsDetailsDto parent3, ContractsDetailsDto parent4) {
        List list;
        List<ContractsDetailsDto> resultList = new ArrayList<>();
        ContractsDetailsDto dto = null;

        list = (List) DAO.executeSelect(query);

        if (!list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {

                Object obj[] = (Object[]) list.get(i);
                dto = new ContractsDetailsDto();
                if (levelIndicator == 1) {

                    dto.setContractId(String.valueOf(obj[0]));
                    dto.setContractNo(String.valueOf(obj[1]));
                    dto.setContractName(String.valueOf(obj[NumericConstants.TWO]));
                    dto.setContractSid(Integer.parseInt(String.valueOf(obj[NumericConstants.THREE])));
                    dto.setCategory(CONTRACT.getConstant());
                    dto.setLevel(levelIndicator);

                } else if (levelIndicator == NumericConstants.TWO) {
                    dto.setContractId(String.valueOf(obj[0]));
                    dto.setContractNo(String.valueOf(obj[1]));
                    dto.setContractName(String.valueOf(obj[NumericConstants.TWO]));
                    dto.setCfpContractId(Integer.parseInt(String.valueOf(obj[NumericConstants.THREE])));
                    dto.setContractSid(contractSid);
                    dto.setCategory(CFP.getConstant());
                    dto.setLevel(levelIndicator);
                    dto.setParent1(parent1);

                } else if (levelIndicator == NumericConstants.THREE) {
                    dto.setContractId(String.valueOf(obj[0]));
                    dto.setContractNo(String.valueOf(obj[1]));
                    dto.setContractName(String.valueOf(obj[NumericConstants.TWO]));
                    dto.setIfpContractId(Integer.parseInt(String.valueOf(obj[NumericConstants.THREE])));
                    dto.setContractSid(contractSid);
                    dto.setCfpContractId(cfpSid);
                    dto.setCategory(IFP.getConstant());
                    dto.setLevel(levelIndicator);
                    dto.setParent1(parent1);
                    dto.setParent2(parent2);

                } else if (levelIndicator == NumericConstants.FOUR) {
                    dto.setContractId(String.valueOf(obj[0]));
                    dto.setContractNo(String.valueOf(obj[1]));
                    dto.setContractName(String.valueOf(obj[NumericConstants.TWO]));
                    dto.setPsContractId(Integer.parseInt(String.valueOf(obj[NumericConstants.THREE])));
                    dto.setContractSid(contractSid);
                    dto.setCfpContractId(cfpSid);
                    dto.setIfpContractId(ifpSid);
                    dto.setCategory(PS_VALUE.getConstant());
                    dto.setLevel(levelIndicator);

                    dto.setParent1(parent1);
                    dto.setParent2(parent2);
                    dto.setParent3(parent3);

                } else if (levelIndicator == NumericConstants.FIVE) {
                    dto.setContractId(String.valueOf(obj[0]));
                    dto.setContractNo(String.valueOf(obj[1]));
                    dto.setContractName(String.valueOf(obj[NumericConstants.TWO]));
                    dto.setRsContractSId(Integer.parseInt(String.valueOf(obj[NumericConstants.THREE])));
                    dto.setContractSid(contractSid);
                    dto.setCfpContractId(cfpSid);
                    dto.setIfpContractId(ifpSid);
                    dto.setPsContractId(psSid);
                    dto.setCategory(RS_VALUE.getConstant());
                    dto.setLevel(levelIndicator);
                    dto.setParent1(parent1);
                    dto.setParent2(parent2);
                    dto.setParent3(parent3);
                    dto.setParent4(parent4);
                }
                resultList.add(dto);

            }
        }
        return resultList;

    }

    public ExtTreeContainer<ContractsDetailsDto> loadContainerData(List<ContractsDetailsDto> resultList, final ExtTreeContainer<ContractsDetailsDto> container, ContractsDetailsDto parent) {
        if (parent != null) {
            if (parent.getLevel() == 1) {
                container.removeAllItems();
                container.addBean(parent);
                container.setChildrenAllowed(parent, true);

            } else if (parent.getLevel() == NumericConstants.TWO) {
                container.removeAllItems();
                container.addBean(parent.getParent1());

                container.setChildrenAllowed(parent.getParent1(), true);
                container.addBean(parent);
                container.setChildrenAllowed(parent, true);
                container.setParent(parent, parent.getParent1());

            } else if (parent.getLevel() == NumericConstants.THREE) {
                container.removeAllItems();
                container.addBean(parent.getParent1());
                container.setChildrenAllowed(parent.getParent1(), true);
                container.addBean(parent.getParent2());
                container.setChildrenAllowed(parent.getParent2(), true);
                container.setParent(parent.getParent2(), parent.getParent1());
                container.addBean(parent);
                container.setChildrenAllowed(parent, true);
                container.setParent(parent, parent.getParent2());

            } else if (parent.getLevel() == NumericConstants.FOUR) {
                container.removeAllItems();
                container.addBean(parent.getParent1());
                container.setChildrenAllowed(parent.getParent1(), true);
                container.addBean(parent.getParent2());
                container.setChildrenAllowed(parent.getParent2(), true);
                container.setParent(parent.getParent2(), parent.getParent1());
                container.addBean(parent.getParent3());
                container.setChildrenAllowed(parent.getParent3(), true);
                container.setParent(parent.getParent3(), parent.getParent2());
                container.addBean(parent);
                container.setChildrenAllowed(parent, true);
                container.setParent(parent, parent.getParent3());

            }

            for (ContractsDetailsDto contractMember : resultList) {

                container.addBean(contractMember);
                if (!RS_VALUE.getConstant().equals(contractMember.getCategory())) {
                    container.setChildrenAllowed(contractMember, true);
                } else {
                    container.setChildrenAllowed(contractMember, false);
                }

                container.setParent(contractMember, parent);
            }

        } else {
            container.removeAllItems();
            for (final Iterator<ContractsDetailsDto> iterator = resultList.iterator(); iterator.hasNext();) {
                final ContractsDetailsDto contractMember = (ContractsDetailsDto) iterator.next();
                container.addBean(contractMember);

                if (!RS_VALUE.getConstant().equals(contractMember.getCategory()) && contractMember.getLevel() < NumericConstants.FIVE) {
                    container.setChildrenAllowed(contractMember, true);
                } else {
                    container.setChildrenAllowed(contractMember, false);
                }
            }

        }

        return container;
    }

    public List<ContractResultDTO> getContractSelectionResults(String query, boolean isExcelExport) {

        List list;
        List<ContractResultDTO> resultList = new ArrayList<>();
        ContractResultDTO dto = null;
        Map<Integer, HelperDTO> idHelperDTOMap = helperListUtil.getIdHelperDTOMap();
        list = (List) DAO.executeSelect(query);
        if (!list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                try {
                    Object obj[] = (Object[]) list.get(i);
                    dto = new ContractResultDTO();

                    dto.setProjectionId(String.valueOf(obj[0]));
                    if (!String.valueOf(obj[1]).equals("Approved")) {
                        dto.setWorkflowStatus(String.valueOf(obj[1]));
                    } else if (isExcelExport) {
                        dto.setProjectionId(StringUtils.EMPTY);
                    }
                    dto.setContractHolder(CommonUtils.getPureValue(String.valueOf(obj[NumericConstants.TWO])));
                    dto.setContractNo(String.valueOf(obj[NumericConstants.THREE]));
                    dto.setContractName(String.valueOf(obj[NumericConstants.FOUR]));
                    dto.setContractType(idHelperDTOMap.get(obj[NumericConstants.FIVE]));
                    dto.setContStartDate(!String.valueOf(obj[NumericConstants.SIX]).equals(Constants.NULL) ? new Date(convertStringToDate(obj[NumericConstants.SIX].toString(), YYYY_M_MDD_H_HMMSS_SSS, Constants.DATE_FORMAT)) : null);
                    dto.setContEndDate(!String.valueOf(obj[NumericConstants.SEVEN]).equals(Constants.NULL) ? new Date(convertStringToDate(obj[NumericConstants.SEVEN].toString(), YYYY_M_MDD_H_HMMSS_SSS, Constants.DATE_FORMAT)) : null);
                    dto.setCfpName(String.valueOf(obj[NumericConstants.EIGHT]));
                    dto.setCfpNo(String.valueOf(obj[NumericConstants.NINE]));
                    dto.setCfpId(String.valueOf(obj[NumericConstants.TEN]));
                    dto.setCfpStatus(idHelperDTOMap.get(obj[NumericConstants.ELEVEN]));
                    dto.setCfpStartDate(!String.valueOf(obj[NumericConstants.TWELVE]).equals(Constants.NULL) ? new Date(convertStringToDate(obj[NumericConstants.TWELVE].toString(), YYYY_M_MDD_H_HMMSS_SSS, Constants.DATE_FORMAT)) : null);
                    dto.setCfpEndDate(!String.valueOf(obj[NumericConstants.THIRTEEN]).equals(Constants.NULL) ? new Date(convertStringToDate(obj[NumericConstants.THIRTEEN].toString(), YYYY_M_MDD_H_HMMSS_SSS, Constants.DATE_FORMAT)) : null);

                    dto.setIfpName(String.valueOf(obj[NumericConstants.FOURTEEN]));
                    dto.setIfpNo(String.valueOf(obj[NumericConstants.FIFTEEN]));
                    dto.setIfpId(String.valueOf(obj[NumericConstants.SIXTEEN]));
                    dto.setIfpStatus(idHelperDTOMap.get(obj[NumericConstants.SEVENTEEN]));
                    dto.setIfpStartDate(!String.valueOf(obj[NumericConstants.EIGHTEEN]).equals(Constants.NULL) ? new Date(convertStringToDate(obj[NumericConstants.EIGHTEEN].toString(), YYYY_M_MDD_H_HMMSS_SSS, Constants.DATE_FORMAT)) : null);
                    dto.setIfpEndDate(!String.valueOf(obj[NumericConstants.NINETEEN]).equals(Constants.NULL) ? new Date(convertStringToDate(obj[NumericConstants.NINETEEN].toString(), YYYY_M_MDD_H_HMMSS_SSS, Constants.DATE_FORMAT)) : null);

                    dto.setpSName(String.valueOf(obj[NumericConstants.TWENTY]));
                    dto.setpSNo(String.valueOf(obj[NumericConstants.TWENTY_ONE]));
                    dto.setpSId(String.valueOf(obj[NumericConstants.TWENTY_TWO]));
                    dto.setpSStatus(idHelperDTOMap.get(obj[NumericConstants.TWENTY_THREE]));
                    dto.setpStartDate(!String.valueOf(obj[NumericConstants.TWENTY_FOUR]).equals(Constants.NULL) ? new Date(convertStringToDate(obj[NumericConstants.TWENTY_FOUR].toString(), YYYY_M_MDD_H_HMMSS_SSS, Constants.DATE_FORMAT)) : null);
                    dto.setpSEndDate(!String.valueOf(obj[NumericConstants.TWENTY_FIVE]).equals(Constants.NULL) ? new Date(convertStringToDate(obj[NumericConstants.TWENTY_FIVE].toString(), YYYY_M_MDD_H_HMMSS_SSS, Constants.DATE_FORMAT)) : null);

                    dto.setrSName(String.valueOf(obj[NumericConstants.TWENTY_SIX]));
                    dto.setrSNo(obj[NumericConstants.TWENTY_SEVEN] != null ? String.valueOf(obj[NumericConstants.TWENTY_SEVEN]) : StringUtils.EMPTY);
                    dto.setrARCategory(String.valueOf(obj[NumericConstants.TWENTY_EIGHT]).trim().equalsIgnoreCase(Constants.SELECT_ONE) || obj[NumericConstants.TWENTY_EIGHT] == null ? StringUtils.EMPTY : String.valueOf(obj[NumericConstants.TWENTY_EIGHT]));
                    dto.setrSId(String.valueOf(obj[NumericConstants.TWENTY_NINE]));
                    dto.setrSStatus(idHelperDTOMap.get(obj[NumericConstants.THIRTY]));
                    dto.setrStartDate(!String.valueOf(obj[NumericConstants.THIRTY_ONE]).equals(Constants.NULL) ? new Date(convertStringToDate(obj[NumericConstants.THIRTY_ONE].toString(), YYYY_M_MDD_H_HMMSS_SSS, Constants.DATE_FORMAT)) : null);
                    dto.setrSEndDate(!String.valueOf(obj[NumericConstants.THIRTY_TWO]).equals(Constants.NULL) ? new Date(convertStringToDate(obj[NumericConstants.THIRTY_TWO].toString(), YYYY_M_MDD_H_HMMSS_SSS, Constants.DATE_FORMAT)) : null);

                    dto.setCfpContSid(String.valueOf(obj[NumericConstants.THIRTY_THREE]));
                    dto.setRsContSid(String.valueOf(obj[NumericConstants.THIRTY_FOUR]));
                    dto.setIfpContSid(String.valueOf(obj[NumericConstants.THIRTY_FIVE]));
                    dto.setPsContSid(String.valueOf(obj[NumericConstants.THIRTY_SIX]));
                    dto.setContractMasterSid(String.valueOf(obj[NumericConstants.THIRTY_SEVEN]));
                    dto.setCompStartDate(!String.valueOf(obj[NumericConstants.THIRTY_EIGHT]).equals(Constants.NULL) ? new Date(convertStringToDate(obj[NumericConstants.THIRTY_EIGHT].toString(), YYYY_M_MDD_H_HMMSS_SSS, Constants.DATE_FORMAT)) : null);
                    dto.setCompEndDate(!String.valueOf(obj[NumericConstants.THIRTY_NINE]).equals(Constants.NULL) ? new Date(convertStringToDate(obj[NumericConstants.THIRTY_NINE].toString(), YYYY_M_MDD_H_HMMSS_SSS, Constants.DATE_FORMAT)) : null);
                    HelperDTO status = new HelperDTO();
                    status.setId(!String.valueOf(obj[NumericConstants.FORTY]).equals(Constants.NULL) ? Integer.parseInt(String.valueOf(obj[NumericConstants.FORTY])) : 0);
                    status.setDescription(!String.valueOf(obj[NumericConstants.FORTY_THREE]).equals(Constants.NULL) ? String.valueOf(obj[NumericConstants.FORTY_THREE]) : Constants.SELECT_ONE);
                    dto.setStatus(status);
                    dto.setCheckRecord(!String.valueOf(obj[NumericConstants.FORTY_ONE]).equals(Constants.NULL) ? String.valueOf(obj[NumericConstants.FORTY_ONE]).equals(Constants.TRUE) : false);
                    dto.setStatusString(String.valueOf(obj[NumericConstants.TWENTY_TWO]));
                    dto.setStatusDescription(new HelperDTO(String.valueOf(obj[NumericConstants.FORTY_TWO])));
                    dto.setTpstatus(String.valueOf(obj[NumericConstants.FORTY_TWO]));
                    resultList.add(dto);
                } catch (ParseException ex) {
                    LOGGER.error(ex);
                }
            }
        }

        return resultList;

    }

    public void updateCfpDetails(SessionDTO session) {
        StringBuilder customSql = new StringBuilder("  ");
        customSql.append(CustomSQLUtil.get("updateCfpDetails"));
        customSql.replace(customSql.indexOf("?"), customSql.indexOf("?") + 1, session.getUserId());
        customSql.replace(customSql.indexOf("?"), customSql.indexOf("?") + 1, session.getSessionId());
        customSql.replace(customSql.indexOf("?"), customSql.indexOf("?") + 1, CommonUtils.CollectionToString(session.getCompanyMasterSids(), false));
        if (TRADING_PARTNER_REMOVE.getConstant().equalsIgnoreCase(session.getModuleName())) {
            customSql.replace(customSql.indexOf("?"), customSql.indexOf("?") + 1, "GetDate()");
        } else {
            customSql.replace(customSql.indexOf("?"), customSql.indexOf("?") + 1, "Source.END_DATE");
        }
        CompanyMasterLocalServiceUtil.executeUpdateQuery(customSql.toString());
    }

    public List<IdDescriptionDTO> loadCompanyQualifier() {

        List list = new ArrayList();
        List<IdDescriptionDTO> resultList = new ArrayList<>();
        IdDescriptionDTO idDescription = null;
        try {
            DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyQualifier.class);
            final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
            productProjectionList.add(ProjectionFactoryUtil.property("companyQualifierSid"));
            productProjectionList.add(ProjectionFactoryUtil.property("companyQualifierName"));

            dynamicQuery.setProjection(productProjectionList);
            list = CompanyQualifierLocalServiceUtil.dynamicQuery(dynamicQuery);

            if (!list.isEmpty()) {
                for (int i = 0; i < list.size(); i++) {
                    Object obj[] = (Object[]) list.get(i);
                    idDescription = new IdDescriptionDTO(Integer.parseInt(String.valueOf(obj[0])), String.valueOf(obj[1]));
                    resultList.add(idDescription);
                }
            }

        } catch (SystemException ex) {
            LOGGER.error(ex);
        }

        return resultList;
    }

    public int getCompanyCrtQualifierByQualifierName(final String identifierValue, int compQualifierSid) {
        List input = new ArrayList();
        int count = 0;
        input.add(identifierValue);
        input.add(compQualifierSid);
        List list = ItemQueries.getItemData(input, "get Company Crt Qualifier By QualifierName", null);
        if (list != null && list.get(0) != null) {

            count = CommonUtils.convertToInteger(String.valueOf(list.get(0)));
        }
        return count;

    }

    public int getCompanyCountByID(final String columnName, String value) {
        int count = 0;
        StringBuilder query = new StringBuilder("  ");

        query.append("  SELECT count(Distinct COMPANY_MASTER_SID) FROM COMPANY_MASTER WHERE  ");
        query.append(columnName).append("='").append(value).append("'" + " AND INBOUND_STATUS <> 'D'  ");

        List list = (List) DAO.executeSelect(query.toString());

        if (list != null && list.get(0) != null) {

            count = Integer.parseInt(String.valueOf(list.get(0)));
        }
        return count;
    }

    public CompanyMaster saveCompanyMaster(final CompanyCrtIdentifierDTO companyform, final List<CompanyCrtIdentifierDTO> identifierList, final List<CompanyCrtIdentifierDTO> companyTradeList) {

        CompanyMaster company = CompanyMasterLocalServiceUtil.createCompanyMaster(0);

        try {

            company.setInboundStatus("A");

            CompanyMaster result = null;
            company.setCompanyId(companyform.getCompanyId());
            company.setCompanyNo(companyform.getCompanyNo());
            company.setCompanyName(companyform.getCompanyName());
            company.setCompanyStatus(Integer.valueOf(companyform.getComapnyStatus()));
            company.setCompanyStartDate(companyform.getCompanyStartDate());
            company.setCompanyEndDate(null);
            company.setRecordLockStatus(false);//unlocked

            final Date createdDate = new Date();
            company.setCreatedBy(Integer.parseInt(VaadinSession.getCurrent().getAttribute(Constants.USER_ID).toString()));
            company.setCreatedDate(createdDate);
            company.setModifiedDate(createdDate);

            result = CompanyMasterLocalServiceUtil.addCompanyMaster(company);
            saveIdentifiersList(identifierList, result);
            saveCompanyTradeClass(companyTradeList, result);

        } catch (Exception e) {
            LOGGER.error(e);
        }

        return company;

    }

    public void saveIdentifiersList(final List<CompanyCrtIdentifierDTO> identifierList, final CompanyMaster result) throws SystemException {

        if (identifierList != null) {
            for (int i = 0; i < identifierList.size(); i++) {
                final CompanyCrtIdentifierDTO identifierForm = (CompanyCrtIdentifierDTO) identifierList.get(i);

                final CompanyIdentifier identifier = CompanyIdentifierLocalServiceUtil.createCompanyIdentifier(0);

                identifier.setCompanyQualifierSid(Integer.valueOf(identifierForm.getQualifierName()));

                identifier.setIdentifierStatus(Integer.valueOf(identifierForm.getIdentifierStatus()));
                identifier.setCompanyIdentifierValue(identifierForm.getCompanyIdentifier().trim());
                identifier.setIdentifierStatus(Integer.valueOf(identifierForm.getIdentifierStatus()));
                identifier.setCreatedDate(new Date());
                identifier.setModifiedDate(new Date());
                identifier.setCreatedBy(Integer.parseInt(VaadinSession.getCurrent().getAttribute(Constants.USER_ID).toString()));

                identifier.setCompanyMasterSid(result.getCompanyMasterSid());

                identifier.setStartDate(identifierForm.getStartDate());

                identifier.setEndDate(identifierForm.getEndDate());

                identifier.setModifiedDate(new Date());

                identifier.setInboundStatus("A");
                CompanyIdentifierLocalServiceUtil.addCompanyIdentifier(identifier);

            }

        }

    }

    /**
     * Save company trade class.
     *
     * @param companyTradeList the company trade list
     * @param result the result
     */
    public void saveCompanyTradeClass(final List<CompanyCrtIdentifierDTO> companyTradeList, final CompanyMaster result) {
        try {
            for (int i = 0; i < companyTradeList.size(); i++) {
                CompanyTradeClass tradeClass = CompanyTradeClassLocalServiceUtil.createCompanyTradeClass(0);
                tradeClass.setRecordLockStatus(false);//unlocked
                tradeClass.setCompanyMasterSid(result.getCompanyMasterSid());

                tradeClass.setCompanyTradeClass(Integer.valueOf(companyTradeList.get(i).getTradeClass()));

                if (companyTradeList.get(i).getTradeStartDate() != null && !StringUtils.EMPTY.equals(companyTradeList.get(i).getTradeStartDate())) {
                    tradeClass.setTradeClassStartDate(companyTradeList.get(i).getTradeStartDate());
                }
                if (companyTradeList.get(i).getTradeEndDate() != null && !StringUtils.EMPTY.equals(companyTradeList.get(i).getTradeEndDate())) {
                    tradeClass.setTradeClassEndDate(companyTradeList.get(i).getTradeEndDate());
                } else {
                    tradeClass.setTradeClassEndDate(null);
                }

                final DynamicQuery tradeClassDynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyTradeClass.class);
                tradeClassDynamicQuery.add(RestrictionsFactoryUtil.eq("companyTradeClass", tradeClass.getCompanyTradeClass()));
                tradeClassDynamicQuery.add(RestrictionsFactoryUtil.eq("tradeClassStartDate", tradeClass.getTradeClassStartDate()));
                tradeClassDynamicQuery.add(RestrictionsFactoryUtil.eq("inboundStatus", "D"));
                final List<CompanyTradeClass> list = (List<CompanyTradeClass>) CompanyTradeClassLocalServiceUtil.dynamicQuery(tradeClassDynamicQuery);
                if (!list.isEmpty()) {
                    CompanyTradeClass companyTradeClass = list.get(0);
                    tradeClass.setCompanyTradeClassSid(companyTradeClass.getCompanyTradeClassSid());
                    tradeClass.setCreatedBy(Integer.parseInt((String) VaadinSession.getCurrent().getAttribute(Constants.USER_ID)));
                    tradeClass.setModifiedBy(Integer.parseInt((String) VaadinSession.getCurrent().getAttribute(Constants.USER_ID)));
                    tradeClass.setCreatedDate(new Date());
                    tradeClass.setModifiedDate(new Date());
                    tradeClass.setInboundStatus("A");
                    CompanyTradeClassLocalServiceUtil.updateCompanyTradeClass(tradeClass);
                } else {

                    tradeClass.setCreatedBy(Integer.parseInt((String) VaadinSession.getCurrent().getAttribute(Constants.USER_ID)));
                    tradeClass.setCreatedDate(new Date());
                    tradeClass.setModifiedDate(new Date());
                    tradeClass.setInboundStatus("A");
                    CompanyTradeClassLocalServiceUtil.addCompanyTradeClass(tradeClass);
                }

            }

        } catch (SystemException ex) {
            LOGGER.error(ex);
            final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);

            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
        }

    }

    public Date stringToDateForIden(final String dateString) throws ParseException {
        final DateFormat inputFormat = new SimpleDateFormat(YYYY_M_MDD_H_HMMSS_SSS);
        Date date;
        date = inputFormat.parse(dateString);
        return date;
    }

    public int validateStartAndEndDate(String fieldName, String date, SessionDTO session) {

        int count = 0;
        StringBuilder query = new StringBuilder("  ");

        query.append(" SELECT COUNT(" + fieldName + ") FROM IMTD_ITEM_PRICE_REBATE_DETAILS WHERE CHECK_RECORD='1' AND UDC1='0' AND SESSION_ID  ='" + session.getSessionId() + "' AND " + fieldName + (fieldName.equals("START_DATE") ? ">" : "<") + " CONVERT(DATE, '" + date + "'); ");

        List list = (List) DAO.executeSelect(query.toString());

        if (list != null && list.get(0) != null) {

            count = Integer.parseInt(String.valueOf(list.get(0)));
        }
        return count;

    }

    public int validateIsValueExists(String fieldName, SessionDTO session) {

        int count = 0;
        StringBuilder query = new StringBuilder("  ");

        query.append(" SELECT COUNT(*) FROM IMTD_ITEM_PRICE_REBATE_DETAILS WHERE CHECK_RECORD='1' AND UDC1='0' AND  SESSION_ID  ='" + session.getSessionId() + "' AND  " + (fieldName.equals("ATTACHED_STATUS") ? "ATTACHED_STATUS!=0" : (fieldName + " IS NOT NULL")));

        List list = (List) DAO.executeSelect(query.toString());

        if (list != null && list.get(0) != null) {

            count = Integer.parseInt(String.valueOf(list.get(0)));
        }
        return count;

    }

    public String updateOnRemove(String userId, String sessionId, int contractSid, int cfpSid, int ifpSid, int psSid, int rsSid) {
        StringBuilder query = new StringBuilder();

        query.append(" UPDATE IMTD_ITEM_PRICE_REBATE_DETAILS set CHECK_RECORD = '0' , UDC1 = '0'        ");

        query.append(" WHERE USERS_SID='").append(userId).append("' AND SESSION_ID='").append(sessionId).append("'  AND UDC1='1'    ");
        if (contractSid != 0) {
            query.append(" AND  CONTRACT_MASTER_SID='").append(contractSid).append("' ");
        }
        if (cfpSid != 0) {
            query.append(" AND CFP_MODEL_SID='").append(cfpSid).append("' ");
        }
        if (ifpSid != 0) {
            query.append(" AND IFP_MODEL_SID='").append(ifpSid).append("' ");
        }
        if (psSid != 0) {
            query.append(" AND PS_MODEL_SID='").append(psSid).append("' ");
        }
        if (rsSid != 0) {
            query.append(" AND RS_MODEL_SID='").append(rsSid).append("' ");

        }
        CompanyMasterLocalServiceUtil.executeUpdateQuery(query.toString());

        return query.toString();
    }

    public int callCompanyUpdate(boolean checkValue, TradingPartnerDTO dto, String updateType, String searchSessionId) {
        int count = 0;
        StringBuilder query = new StringBuilder("   ");
        query.append("UPDATE GCM_COMPANY_DETAILS SET CHECK_RECORD='").append(checkValue ? 1 : 0).append("'");
        query.append("WHERE COMPANY_MASTER_SID='").append(!dto.getCompanySystemId().equals(StringUtils.EMPTY) ? dto.getCompanySystemId() : 0).append("'  ");
        query.append(" AND OPERATION = '").append(updateType).append("'");
        query.append(" AND SESSION_ID = '").append(searchSessionId).append("'");

        count = CompanyMasterLocalServiceUtil.executeUpdateQuery(query.toString());
        return count;
    }

    public int callCompanyInsert(boolean checkValue, TradingPartnerDTO dto, String updateType, String searchSessionId) {
        int count = 0;
        StringBuilder query = new StringBuilder("   ");
        query.append("INSERT INTO GCM_COMPANY_DETAILS ");
        query.append(" (CHECK_RECORD,COMPANY_MASTER_SID,COMPANY_NAME, OPERATION, SESSION_ID)  ");
        query.append("VALUES(  ");
        query.append("'").append(checkValue ? 1 : 0).append("'");
        query.append(",").append(!dto.getCompanySystemId().equals(StringUtils.EMPTY) ? dto.getCompanySystemId() : 0);
        query.append(",").append("'").append(dto.getCompanyName()).append("'");
        query.append(",").append("'").append(updateType).append("'");
        query.append(",").append("'").append(searchSessionId).append("'");
        query.append("  )");
        count = CompanyMasterLocalServiceUtil.executeUpdateQuery(query.toString());

        return count;
    }

    public String convertToSrting(Date date, String outputFormat) {
        SimpleDateFormat outputDateFormatter = new SimpleDateFormat(outputFormat);

        return outputDateFormatter.format(date);
    }

    public void updateIfpDetailsForCfp(SessionDTO session) {
        StringBuilder queryString = new StringBuilder(StringUtils.EMPTY);

        String oldContractMasterSid = String.valueOf(CommonLogic.getSelectedContractSid(session.getSessionId(), false));
        String newContractMasterSid = String.valueOf(CommonLogic.getSelectedContractSid(session.getSessionId(), true));
        List<String> newCfpContractMasterSidList = CommonLogic.getSelectedCfpSid(session.getSessionId(), true);
        List<String> oldCfpContractMasterSidList = CommonLogic.getSelectedCfpSid(session.getSessionId(), false);

        String newCfpContractMasterSid = CommonUtils.CollectionToString(newCfpContractMasterSidList, true);
        String oldCfpContractMasterSid = CommonUtils.CollectionToString(oldCfpContractMasterSidList, true);

        queryString.append(CustomSQLUtil.get("tp.transferIFPQuery"));
        queryString.replace(queryString.indexOf("?"), queryString.indexOf("?") + 1, oldContractMasterSid);
        queryString.replace(queryString.indexOf("?"), queryString.indexOf("?") + 1, newContractMasterSid);
        queryString.replace(queryString.indexOf("?"), queryString.indexOf("?") + 1, newCfpContractMasterSid);
        queryString.replace(queryString.indexOf("?"), queryString.indexOf("?") + 1, oldCfpContractMasterSid);
        queryString.replace(queryString.indexOf("?"), queryString.indexOf("?") + 1, newCfpContractMasterSid);
        queryString.replace(queryString.indexOf("?"), queryString.indexOf("?") + 1, oldCfpContractMasterSid);

        CompanyMasterLocalServiceUtil.executeUpdateQuery(queryString.toString());
    }

    public List<ContractResultDTO> getActualPaidList(SessionDTO session, String field) {

        List<String> compSids = session.getCompanyMasterSids();
        String compSid = StringUtils.EMPTY;
        for (String sid : compSids) {
            if (compSid.equals(StringUtils.EMPTY)) {
                compSid = sid;
            } else {
                compSid = compSid + "," + sid;
            }
        }
        String query = " SELECT ";
        if (field.equals("count")) {
            query = query + " 1 \n";
        } else if (field.equals("contractCustomer")) {
            query = query + " CON_M.CONTRACT_NAME,CM.COMPANY_NAME \n";
        } else if (field.equals("tempDate")) {
            query = query + " TEMP.START_DATE,TEMP.END_DATE \n";
        } else if (field.equals("tempActualDate")) {
            query = query + "  MIN(AM.ACCRUAL_ACTUAL_START_DATE) as 'START_DATE',MAX(AM.ACCRUAL_ACTUAL_END_DATE) as 'END_DATE' \n";
        }
        List input = new ArrayList();
        input.add(session.getSessionId());
        input.add(compSid);
        query = query + ItemQueries.getQuery(input, "get Actual Paid List Count");
        List<ContractResultDTO> resultList = new ArrayList<>();
        ContractResultDTO dto = null;

        List list = (List) DAO.executeSelect(query);

        if (field.equals("count")) {
            dto = new ContractResultDTO();
            dto.setTempVarOne(String.valueOf(AbstractLogic.getCount(list)));
            resultList.add(dto);

        } else {
            SimpleDateFormat outputDateFormatter = new SimpleDateFormat(Constants.DATE_FORMAT);
            if (!list.isEmpty()) {
                for (int i = 0; i < list.size(); i++) {
                    Object obj[] = (Object[]) list.get(i);
                    dto = new ContractResultDTO();
                    if (field.equals("contractCustomer")) {
                        dto.setTempVarOne(String.valueOf(obj[0]));
                        dto.setTempVarTwo(String.valueOf(obj[1]));
                    } else {
                        dto.setTempVarOne(!String.valueOf(obj[0]).equals(Constants.NULL) ? outputDateFormatter.format((Date) obj[0]) : "--");
                        dto.setTempVarTwo(!String.valueOf(obj[1]).equals(Constants.NULL) ? outputDateFormatter.format((Date) obj[1]) : "--");
                    }

                    resultList.add(dto);
                }
            }
        }

        return resultList;
    }

    public static void resetContractListView(String sessionId, String operation) {
        List input = new ArrayList();
        input.add(sessionId);
        if (!operation.isEmpty()) {
            input.add(operation);
        } else {
            input.add("%");
        }
        ItemQueries.itemUpdate(input, "Reset Contract List View");
    }

    public static void loaDDLBForListLoading(final ComboBox comboBox, String columnName, Boolean isFilter) {
        comboBox.setPageLength(NumericConstants.SEVEN);
        BeanItemContainer<HelperDTO> container = new BeanItemContainer<>(HelperDTO.class);
        comboBox.setContainerDataSource(container);
        if (isFilter) {
            comboBox.setNullSelectionItemId(ddlbShowAllValue);
        } else {
            comboBox.setNullSelectionItemId(ddlbDefaultValue);
        }
        comboBox.setNullSelectionAllowed(true);
        comboBox.setImmediate(true);
        comboBox.setItemCaptionPropertyId("description");
        container.addAll(getDDLBListForListLoading(columnName, isFilter));
    }

    public static List getDDLBListForListLoading(String columnName, Boolean isFilter) {
    	Map<String, List> ddlbMap = AbstractLogic.getDdlbmap();
        if (ddlbMap.get(columnName) == null) {
            List input = new ArrayList();
            input.add(columnName);
            List<Object[]> list = ItemQueries.getItemData(input, "Combobox List Loading", null);
            List<HelperDTO> resultList = new ArrayList<>();
            if (isFilter) {
                HelperDTO defaultValue = new HelperDTO(0, Constants.SHOW_ALL);
                resultList.add(defaultValue);
            } else {
                HelperDTO defaultValue = new HelperDTO(0, Constants.IndicatorConstants.SELECT_ONE.getConstant());
                resultList.add(defaultValue);
            }

            for (Object[] str : list) {
                if (!str[1].equals(String.valueOf(Constants.IndicatorConstants.SELECT_ONE.getConstant()))) {
                    HelperDTO dto = new HelperDTO();
                    dto.setId(str[0] == null ? 0 : Integer.valueOf(str[0].toString()));
                    dto.setDescription(str[1] == null ? Constants.ZEROSTRING : String.valueOf(str[1]));
                    resultList.add(dto);
                }
            }

            ddlbMap.put(columnName, resultList);
            return resultList;
        }

        return ddlbMap.get(columnName);
    }

    public static String generateCustomerMappings(List<String> sourceCompanies, List<String> destinationCompanies) {
        String customerMapping = StringUtils.EMPTY;
        for (int i = 0; i < sourceCompanies.size(); i++) {
            customerMapping += String.valueOf(sourceCompanies.get(i)) + " - " + String.valueOf(destinationCompanies.get(i));
            if (i < sourceCompanies.size() - 1) {
                customerMapping += ",";
                return customerMapping;
            }
        }
        return customerMapping;
    }

    public static List getPromotedContractDetails(String contractId, String companyId) {
        String query = "SELECT distinct CM.CONT_HOLD_COMPANY_MASTER_SID,CONTRACT_NO,CONTRACT_NAME,HT.DESCRIPTION AS MARKETTYPE,CM.START_DATE,CM.END_DATE ,CFP_C.CFP_NAME,IFP_C.IFP_NAME,\n"
                + "PS_C.PS_NAME,RS_C.RS_NAME,COM.COMPANY_START_DATE,COM.COMPANY_END_DATE,HT1.DESCRIPTION AS STATUS  FROM dbo.CONTRACT_MASTER CM \n"
                + "LEFT JOIN dbo.HELPER_TABLE HT ON CM.CONTRACT_TYPE=HT.HELPER_TABLE_SID\n"
                + "LEFT JOIN dbo.CFP_CONTRACT CFP_C ON CFP_C.CONTRACT_MASTER_SID=CM.CONTRACT_MASTER_SID\n"
                + "LEFT JOIN dbo.IFP_CONTRACT IFP_C ON IFP_C.CONTRACT_MASTER_SID=CM.CONTRACT_MASTER_SID\n"
                + "LEFT JOIN dbo.PS_CONTRACT PS_C ON PS_C.CONTRACT_MASTER_SID=CM.CONTRACT_MASTER_SID\n"
                + "LEFT JOIN dbo.RS_CONTRACT RS_C ON RS_C.CONTRACT_MASTER_SID=CM.CONTRACT_MASTER_SID \n"
                + "LEFT JOIN dbo.CFP_CONTRACT_DETAILS CFP_D ON CFP_C.CFP_CONTRACT_SID=CFP_D.CFP_CONTRACT_SID\n"
                + "LEFT JOIN dbo.COMPANY_MASTER COM ON COM.COMPANY_MASTER_SID=CFP_D.COMPANY_MASTER_SID \n"
                + "LEFT JOIN dbo.RS_CONTRACT_DETAILS RS_CD ON RS_C.RS_CONTRACT_SID=RS_CD.RS_CONTRACT_SID LEFT JOIN dbo.HELPER_TABLE HT1 ON CM.CONTRACT_STATUS=HT1.HELPER_TABLE_SID \n"
                + " WHERE COM.COMPANY_MASTER_SID='" + companyId + "' AND CM.CONTRACT_MASTER_SID=" + contractId;
        List list = HelperTableLocalServiceUtil.executeSelectQuery(query);
        return list;

    }

    public static List<String> getCustomerName(List<String> companyMasterSids) {
        String query = "Select COMPANY_NAME from COMPANY_MASTER where COMPANY_MASTER_SID in(" + CommonUtils.CollectionToString(companyMasterSids, true) + ")";
        List<String> companyNamesList = CompanyMasterLocalServiceUtil.executeQuery(query);
        return companyNamesList;
    }
}
