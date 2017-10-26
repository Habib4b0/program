/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.gtnbalancereport.logic;

import com.stpl.app.accountclose.common.CommonUtil;
import com.stpl.app.accountclose.dao.BaseRateDAO;
import com.stpl.app.accountclose.dao.impl.BaseRateDAOImpl;
import com.stpl.app.accountclose.gtnbalancereport.dao.DataSelectionDAO;
import com.stpl.app.accountclose.gtnbalancereport.dao.daoImpl.DataSelectionDaoImpl;
import com.stpl.app.accountclose.gtnbalancereport.dto.DataSelectionDTO;
import com.stpl.app.accountclose.gtnbalancereport.utils.Constants;
import com.stpl.app.accountclose.gtnbalancereport.utils.DataSelectionUtil;
import com.stpl.app.accountclose.sessionutils.SessionDTO;
import com.stpl.app.accountclose.utils.QueryUtils;
import com.stpl.app.parttwo.model.AccClosureMaster;
import com.stpl.app.parttwo.service.AccClosureMasterLocalServiceUtil;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.server.VaadinSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author santanukumar
 */
public class GTNbalanceLogic {

    public static final DataSelectionDAO dataSelection = new DataSelectionDaoImpl();
    BaseRateDAO brdao = new BaseRateDAOImpl();
    public static final Logger LOGGER = Logger.getLogger(GTNbalanceLogic.class);

    public void saveTempToMain(SessionDTO sessionDTO) throws Exception {
        List input = new ArrayList();
        input.add(sessionDTO.getUserId());
         input.add(sessionDTO.getSessionId());
         QueryUtils.executeUpdateQuery(input, "saveToMainTable");
   
    }

    public static void updateSaveFlag(int acctCloserMasterId) throws Exception {
        LOGGER.info("Entering updateSaveFlag with id " + acctCloserMasterId);
        AccClosureMaster accCloserMaster = AccClosureMasterLocalServiceUtil.createAccClosureMaster(0);
        accCloserMaster = dataSelection.getAcctCloserMaster(acctCloserMasterId);
        accCloserMaster.setSaveFlag(true);
        dataSelection.updateAccountCloserMaster(accCloserMaster);
    }

    public int saveOrUpdateGtnReport(final DataSelectionDTO dataSelectionDTO, String actionFlag) throws SystemException, Exception {
        String userId = (String) VaadinSession.getCurrent().getAttribute("userId");
        AccClosureMaster accCloserMaster = null;
        if ("Update".equals(actionFlag)) {
            accCloserMaster = dataSelection.getAcctCloserMaster(Integer.valueOf(dataSelectionDTO.getAccountClosureSid()));
        } else {
            accCloserMaster = AccClosureMasterLocalServiceUtil.createAccClosureMaster(0);
        }
        accCloserMaster.setReportName(dataSelectionDTO.getReportName());
        accCloserMaster.setDescription(dataSelectionDTO.getDescription());
        accCloserMaster.setAccountNo(dataSelectionDTO.getAccountNo());
        accCloserMaster.setFromDate(dataSelectionDTO.getFromDate());
        accCloserMaster.setToDate(dataSelectionDTO.getToDate());
        if (!dataSelectionDTO.getMarketType().isEmpty()) {
            accCloserMaster.setContractType(Integer.valueOf(dataSelectionDTO.getMarketType()));
        }
        if (!dataSelectionDTO.getDeductionType().isEmpty()) {
            accCloserMaster.setRsType(Integer.valueOf(dataSelectionDTO.getDeductionType()));
        }
        if (!dataSelectionDTO.getDeductionSubType().isEmpty()) {
            accCloserMaster.setRebateProgramType(dataSelectionDTO.getDeductionSubType() != null && !"null".equals(dataSelectionDTO.getDeductionSubType()) ? Integer.valueOf(dataSelectionDTO.getDeductionSubType()) : 0);
        }
        accCloserMaster.setGlCompanyMasterSid(Integer.valueOf(dataSelectionDTO.getCompanySid()));
        if (!dataSelectionDTO.getContractSid().isEmpty()) {
            accCloserMaster.setContractMasterSid(Integer.valueOf(dataSelectionDTO.getContractSid()));
        }
        if (!dataSelectionDTO.getItemSid().isEmpty()) {
            accCloserMaster.setItemMasterSid(Integer.valueOf(dataSelectionDTO.getItemSid()));
        }

        accCloserMaster.setModuleType(Constants.GTN_BALANCE);
        if (dataSelectionDTO.getCustomerGRpSid() != null && dataSelectionDTO.getCustomerGRpSid() != 0) {
            accCloserMaster.setCompanyGroupSid(String.valueOf(dataSelectionDTO.getCustomerGRpSid()));
        }
        if (dataSelectionDTO.getProductGrpSid() != null && dataSelectionDTO.getProductGrpSid() != 0) {
            accCloserMaster.setItemGroupSid(String.valueOf(dataSelectionDTO.getProductGrpSid()));
        }

        accCloserMaster.setModifiedBy(DataSelectionUtil.parseStringToInteger(userId));
        accCloserMaster.setModifiedDate(new Date());
        if (!dataSelectionDTO.getDeductionCategory().isEmpty()) {
            accCloserMaster.setRsCategory(Integer.valueOf(dataSelectionDTO.getDeductionCategory()));
        }

        if ("Update".equals(actionFlag)) {
            accCloserMaster = dataSelection.updateAccountCloserMaster(accCloserMaster);
        } else {
            accCloserMaster.setCreatedBy(DataSelectionUtil.parseStringToInteger(userId));
            accCloserMaster.setCreatedDate(new Date());
            accCloserMaster = dataSelection.addAcctCloserMaster(accCloserMaster);
        }

        return accCloserMaster.getAccClosureMasterSid();

    }

    public void saveSelection(Map map, int acctCloserMasterId, String gtn_Balance_Report) throws Exception {
        StringBuilder queryBuilder = new StringBuilder();
        Map<String,String> inputMap=new HashMap<String,String>();
        List input=new ArrayList();
        if (!map.isEmpty()) {
            String view = (String) map.get("view");
            String slectedVariables = (String) map.get("selectedVariables");
            inputMap.put("@accMaster_Sid",StringUtils.EMPTY+acctCloserMasterId);
            inputMap.put("@Frequency",StringUtils.EMPTY+map.get("Frequency"));
            inputMap.put("@From_Period",String.valueOf(map.get("fromDateDdlb")));
            inputMap.put("@To_Period",String.valueOf(map.get("toDateDdlb")));
            inputMap.put("@Choosen_Period",String.valueOf(map.get("chosenPeriod")));
            inputMap.put("@View",view);
            inputMap.put("@Variable",slectedVariables);
             QueryUtils.executeUpdateQuery(inputMap,"saveProjectionSelectionBR");
        }
    }

    public void deleteTempBySession(SessionDTO sessionDTO) throws Exception {
    List input = new ArrayList();
        input.add(sessionDTO.getUserId());
        input.add(sessionDTO.getSessionId());
        QueryUtils.executeUpdateQuery(input, "deleteFromTemp");
    }

    public List<String> getCcpIds(SessionDTO session) throws Exception {
        List<String> ids = new ArrayList<String>();
        Map<String, String> inputMap = new HashMap<String, String>();
        String selectedCustomerIds = getSelectedIds(session);
        inputMap.put("?COMPANYMASTERSIDS?", selectedCustomerIds);
        inputMap.put("?ACCCLOSUREMASTERSID?", String.valueOf(session.getAcctCloserMasterId()));
        String query = CommonUtil.getQuery(inputMap, "ajdust_reconsile_ccp_query");
        ids = (List<String>) brdao.executeSelectQuery(query);
        return ids;
    }

    private String getSelectedIds(SessionDTO session) throws Exception {
        List results = new ArrayList();
        String selectedCustomerIds = StringUtils.EMPTY;
        String query = StringUtils.EMPTY;
        String viewType = session.getBalanceReportDTO().getViewType();
        if (viewType.equalsIgnoreCase(com.stpl.app.accountclose.utils.Constants.IndicatorConstants.CUSTOMER.getConstant())) {
            query = "SELECT  DISTINCT CONTRACT_MASTER_SID,COMPANY_MASTER_SID \n"
                    + "FROM ST_ACC_CLOSURE_DETAILS TEMP \n"
                    + "WHERE MODULE_NAME like '" + com.stpl.app.accountclose.gtnbalancereport.utils.Constants.DS_SELECTED_CUSTOMERS + "' AND USER_ID = '" + session.getUserId() + "' AND  SESSION_ID = '" + session.getSessionId() + "' AND CHECK_RECORD = 0;";
            results = (List) brdao.executeSelectQuery(query);
        } else {
            Map<String, String> inputMap = new HashMap<String, String>();
            inputMap.put("?ACC_CLOSURE_MASTER_SID?", String.valueOf(session.getAcctCloserMasterId()));
            inputMap.put("?USER_ID?", session.getUserId());
            inputMap.put("?SESSION_ID?", session.getSessionId());
            inputMap.put("?CMSID?", "%");
            inputMap.put("?CSID?", "%");
            inputMap.put("?THID?", "%");
            inputMap.put("?BMSID?", "%");
            inputMap.put("?ISID?", "%");

            if (StringUtils.isNotBlank(session.getBalanceReportDTO().getComapnySid())) {
                inputMap.put("?CMSID?", session.getBalanceReportDTO().getComapnySid());
            }
            if (StringUtils.isNotBlank(session.getBalanceReportDTO().getContractSid())) {
                inputMap.put("?CSID?", session.getBalanceReportDTO().getContractSid());
            }
            if (StringUtils.isNotBlank(session.getBalanceReportDTO().getTherapeuticSid())) {
                inputMap.put("?THID?", session.getBalanceReportDTO().getTherapeuticSid());
            }
            if (StringUtils.isNotBlank(session.getBalanceReportDTO().getBrandSid())) {
                inputMap.put("?BMSID?", session.getBalanceReportDTO().getBrandSid());
            }
            if (StringUtils.isNotBlank(session.getBalanceReportDTO().getItemSid())) {
                inputMap.put("?ISID?", session.getBalanceReportDTO().getItemSid());
            }
            query = CommonUtil.getQuery(inputMap, "brandResultQuery");
            results = (List) brdao.executeSelectQuery(query);
        }


        int size = results.size();
        for (int i = 0; i < size; i++) {
            final Object[] obj = (Object[]) results.get(i);
            if (i == 0) {
                selectedCustomerIds = String.valueOf(obj[1]);
            } else {
                selectedCustomerIds += "," + String.valueOf(obj[1]);
            }
        }
        return selectedCustomerIds;
    }
}
