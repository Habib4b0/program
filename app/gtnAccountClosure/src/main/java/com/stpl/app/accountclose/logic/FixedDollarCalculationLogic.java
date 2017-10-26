/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.logic;

import com.stpl.app.accountclose.common.CommonLogic;
import com.stpl.app.accountclose.dao.BaseRateDAO;
import com.stpl.app.accountclose.dao.impl.BaseRateDAOImpl;
import com.stpl.app.accountclose.dto.FixedDollarDTO;
import com.stpl.app.accountclose.dto.FixedDollarGroupDTO;
import com.stpl.app.accountclose.dto.FixedDollarSaveDTO;
import com.stpl.app.accountclose.utils.CommonUtils;
import com.stpl.app.accountclose.utils.Constants;
import com.stpl.app.accountclose.utils.Converters;
import com.stpl.app.accountclose.utils.QueryUtils;
import com.stpl.app.model.CompanyGroupDetails;
import com.stpl.app.model.CompanyMaster;
import com.stpl.app.model.HelperTable;
import com.stpl.app.model.ItemGroupDetails;
import com.stpl.app.model.ItemMaster;
import com.stpl.app.parttwo.model.AccClosureDetails;
import com.stpl.app.parttwo.model.AccClosureMaster;
import com.stpl.app.parttwo.service.AccClosureDetailsLocalServiceUtil;
import com.stpl.app.parttwo.service.AccClosureMasterLocalServiceUtil;
import com.stpl.app.service.CompanyGroupDetailsLocalServiceUtil;
import com.stpl.app.service.CompanyMasterLocalServiceUtil;
import com.stpl.app.service.ItemGroupDetailsLocalServiceUtil;
import com.stpl.app.service.ItemMasterLocalServiceUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionList;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.vaadin.server.VaadinSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author alok.v
 */
public class FixedDollarCalculationLogic {

    public static final Logger LOGGER = Logger.getLogger(FixedDollarCalculationLogic.class);
    DataQueryLogic dqLogic = new DataQueryLogic();
    final String moduleName = "FixedDollar";
    private BaseRateDAO dao = new BaseRateDAOImpl();
    CommonLogic logic = new CommonLogic();
    String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
    private final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    /**
     *
     * @param query
     * @return
     * @throws Exception
     */
    public List<String> executeQuery(final String query) throws Exception {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("query", query);

        List resultList = dqLogic.executeQuery(parameters);
        List<String> returnList = new ArrayList<String>();
        if (returnList.isEmpty()) {
            for (Object value : resultList) {
                returnList.add(String.valueOf(value));
            }
        }
        return returnList;
    }

    /**
     * Get Customer Group Logic
     *
     * @param customerName
     * @param customerNo
     * @param companySids
     * @return
     * @throws Exception
     */
    public List<FixedDollarGroupDTO> getCustomerGroup(String customerName, String customerNo, List<String> companySids) throws Exception {
        Map<String, Object> parameters = new HashMap<String, Object>();
        customerName = customerName.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
        customerNo = customerNo.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
        parameters.put("customerNo", customerNo);
        parameters.put("customerName", customerName);
        parameters.put("companySids", companySids);
        parameters.put("indicator", "CustomerGroup");
        List resultList = dqLogic.getCustomerProductGroup(parameters);
        return Converters.convertFDCustomerGroupList(resultList);
    }

    /**
     * Gets the Product group.
     *
     * @param productName the Product group name search criteria
     * @param productNo the Product group no search criteria
     * @return the Product group result list
     * @throws java.lang.Exception
     */
    public List<FixedDollarGroupDTO> getProductGroup(String productName, String productNo, List<String> itemSids) throws Exception {
        Map<String, Object> parameters = new HashMap<String, Object>();
        productName = productName.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
        productNo = productNo.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);

        parameters.put("productName", productName);
        parameters.put("productNo", productNo);
        parameters.put("itemSids", itemSids);
        parameters.put("indicator", "ProductGroup");
        List<FixedDollarGroupDTO> resultList = dqLogic.getCustomerProductGroup(parameters);
        return Converters.convertFDItemGroupList(resultList);
    }

    /**
     * Get Company Group Details Logic
     *
     * @param companyGroupSid
     * @return
     * @throws Exception
     */
    public List<String> getCustomerGroupDetails(int companyGroupSid) throws Exception {
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyGroupDetails.class);

        dynamicQuery.add(RestrictionsFactoryUtil.eq("companyGroupSid", companyGroupSid));

        final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
        productProjectionList.add(ProjectionFactoryUtil.property("companyMasterSid"));
        dynamicQuery.setProjection(productProjectionList);
        List resultList = CompanyGroupDetailsLocalServiceUtil.dynamicQuery(dynamicQuery);

        List<String> returnList = new ArrayList<String>();
        for (Object companySid : resultList) {
            returnList.add(String.valueOf(companySid));
        }
        return returnList;
    }

    /**
     * Gets the Item Group Details
     *
     * @param itemGroupSid
     * @return
     * @throws Exception
     */
    public List<String> getItemGroupDetails(int itemGroupSid) throws Exception {
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ItemGroupDetails.class);
        dynamicQuery.add(RestrictionsFactoryUtil.eq("itemGroupSid", itemGroupSid));
        final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
        productProjectionList.add(ProjectionFactoryUtil.property("itemMasterSid"));
        dynamicQuery.setProjection(productProjectionList);
        List resultList = ItemGroupDetailsLocalServiceUtil.dynamicQuery(dynamicQuery);

        List<String> returnList = new ArrayList<String>();
        for (Object companySid : resultList) {
            returnList.add(String.valueOf(companySid));
        }
        return returnList;
    }

    /**
     * Get Company system id
     *
     * @param companySids
     * @return
     * @throws Exception
     */
    public List<CompanyMaster> getCompanyFromSids(final List<String> companySids) throws Exception {

        List<Integer> sids = new ArrayList<Integer>();

        for (String sid : companySids) {
            sids.add(Integer.parseInt(sid));
        }

        final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyMaster.class);
        dynamicQuery.add(RestrictionsFactoryUtil.in("companyMasterSid", sids));
        List<CompanyMaster> companies = CompanyMasterLocalServiceUtil.dynamicQuery(dynamicQuery);
        return companies;
    }

    /**
     * Get The Item System ID
     *
     * @param itemSids
     * @return
     * @throws Exception
     */
    public List<ItemMaster> getItemsFromSids(final List<String> itemSids) throws Exception {

        List<Integer> sids = new ArrayList<Integer>();
        List<ItemMaster> items = null;
        for (String sid : itemSids) {
            sids.add(Integer.parseInt(sid));
        }
        if (itemSids != null && !itemSids.isEmpty()) {
            final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ItemMaster.class);
            dynamicQuery.add(RestrictionsFactoryUtil.in("itemMasterSid", sids));
            items = ItemMasterLocalServiceUtil.dynamicQuery(dynamicQuery);
        }
        return items;
    }

    public String setWorkflow(int masterId, String status, boolean flag, String wfSid,String notes) {
        LOGGER.info("Entering setWorkflow method" + masterId);
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil
                .forClass(AccClosureMaster.class);
        dynamicQuery.add(RestrictionsFactoryUtil.eq(
                "moduleType", CommonUtils.BUSINESS_PROCESS_TYPE_FDA));
        dynamicQuery.add(RestrictionsFactoryUtil.eq("accClosureMasterSid",
                masterId));
        String workflow = StringUtils.EMPTY;

        List<AccClosureMaster> resultList;
        try {
            int statusSid = getStatusId(status);
            resultList = dao.getBRMaster(dynamicQuery);

            for (AccClosureMaster pm : resultList) {
                AccClosureMaster projMaster = pm;

                projMaster.setWorkflowStatus(statusSid);
                projMaster.setModifiedDate(new Date());
                dao.updateBRMaster(projMaster);

            }
            LOGGER.info("Ending submitNMProjection ");
            workflow = logic.saveWorkflowFDA(masterId, userId, statusSid, flag, wfSid,notes);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        LOGGER.info("Ending setWorkflow method");
        return workflow;
    }

    public int getStatusId(String wStatus) throws Exception {
        LOGGER.info("Entering getStatusId method" + wStatus);
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil
                .forClass(HelperTable.class);
        dynamicQuery.add(RestrictionsFactoryUtil.eq(
                "listName", CommonUtils.WORKFLOWSTATUS));
        dynamicQuery.add(RestrictionsFactoryUtil.eq("description",
                wStatus));
        List<HelperTable> resultList = dao.getHelperSid(dynamicQuery);
        int sid = 0;
        if (resultList != null && !resultList.isEmpty()) {
            sid = resultList.get(0).getHelperTableSid();
        }
        LOGGER.info("Ending getStatusId method" + sid);
        return sid;
    }

    public boolean stopDate(int sid) throws Exception {
        boolean returnDate = false;
        Map<String, String> input =new HashMap();
        input.put("?AC_MASTER_SID",String.valueOf(sid));
        List list = (List)QueryUtils.executeSelectQuery(input, "getDateFromAcFdAdjustmentSelection");
        if (!list.isEmpty()) {
            returnDate = Boolean.valueOf(list.get(0).toString());
        }
        return returnDate;

    }
   
    public void workflowAdjustmentselection(Integer sid) throws Exception {
        Map<String, String> input =new HashMap();
        input.put("?AC_MASTER_SID",String.valueOf(sid));
        QueryUtils.executeUpdateQuery(input, "updateCalcFlag");
    }
  
    public void updateAccMaster(int sid)  throws Exception {
        AccClosureMaster updatemaster = AccClosureMasterLocalServiceUtil.getAccClosureMaster(sid);
        updatemaster.setSaveFlag(true);
        AccClosureMasterLocalServiceUtil.updateAccClosureMaster(updatemaster);
    }
  
    public int saveFixedDollarDetails(FixedDollarSaveDTO saveDto, List<FixedDollarDTO> fdList) {
        int accClosureMasterSid = 0;
        Map<String, String> input = new HashMap();
        AccClosureMaster accMaster;
        accMaster = AccClosureMasterLocalServiceUtil.createAccClosureMaster(0);
        try {

            accMaster.setGlCompanyMasterSid(saveDto.getGlCompanyMasterSid());
            accMaster.setContractType(saveDto.getContractType());
            accMaster.setRsType(saveDto.getRsType());
            accMaster.setRebateProgramType(saveDto.getRebateProgramType());
            accMaster.setAdjustmentType(saveDto.getAdjustmentType());
            accMaster.setAccrualPeriod(saveDto.getAccrualPeriod());

            accMaster.setCompanyGroupSid(saveDto.getCompanyGroupSid());
            accMaster.setContractMasterSid(saveDto.getContractMasterSid());
            accMaster.setItemGroupSid(saveDto.getItemGroupSid());
            accMaster.setItemMasterSid(saveDto.getItemMasterSid());
            accMaster.setProductIdentifier(saveDto.getProductIdentifier());
            accMaster.setWorkflowStatus(saveDto.getWorkflowStatus());
            accMaster.setModuleType(saveDto.getModuleType());
            Date forecastfromdate = null;
            Date forecastTodate = null;

            List forecastConfigList = (List) QueryUtils.executeSelectQuery(input, "forecastConfigDate");

            for (int i = 0; i < forecastConfigList.size(); i++) {
                Object[] obje = (Object[]) forecastConfigList.get(i);
                forecastfromdate = (Date) FORMAT.parse(String.valueOf(obje[0]));
                forecastTodate = (Date) FORMAT.parse(String.valueOf(obje[1]));
            }
            accMaster.setFromDate(forecastfromdate);
            accMaster.setToDate(forecastTodate);
            accMaster.setSaveFlag(false);
            accMaster.setCreatedBy(Integer.valueOf(userId));
            accMaster.setCreatedDate(new Date());
            accMaster.setModifiedBy(Integer.valueOf(userId));
            accMaster.setModifiedDate(new Date());
            AccClosureMaster updatemaster = AccClosureMasterLocalServiceUtil.addAccClosureMaster(accMaster);
            accClosureMasterSid = updatemaster.getAccClosureMasterSid();


            //Getting RsModel based on acc-type ddl
            String rs_model_id = StringUtils.EMPTY;

            input.clear();
            input.put("?MTID", String.valueOf(saveDto.getContractType()));
            input.put("?ACC_TYPE_ID", String.valueOf(saveDto.getRsType()));
            input.put("?CMID", String.valueOf(saveDto.getContractMasterSid()));
            input.put("?IMID", String.valueOf(saveDto.getItemMasterSid()));
            List list = (List) QueryUtils.executeSelectQuery(input, "getAcRsModelSid");
            for (int j = 0; j < fdList.size(); j++) {

                if (list != null) {

                    for (int i = 0; i < list.size(); i++) {

                        Object[] obje = (Object[]) list.get(i);
                        rs_model_id = obje[2].toString();
                        AccClosureDetails accdetails = AccClosureDetailsLocalServiceUtil.createAccClosureDetails(0);
                        accdetails.setAccClosureMasterSid(updatemaster.getAccClosureMasterSid());
                        FixedDollarDTO test = fdList.get(j);
                        accdetails.setCcpDetailsSid(Integer.valueOf(test.getCcpSid()));
                        accdetails.setRsModelSid(Integer.valueOf(rs_model_id));
                        AccClosureDetailsLocalServiceUtil.addAccClosureDetails(accdetails);
                    }
                }
            }

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return accClosureMasterSid;
    }
}
