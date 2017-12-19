/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.nationalassumptions.logic;


import com.stpl.app.NoSuchItemGroupException;
import com.stpl.app.gtnforecasting.dao.NACommonResultsDAO;
import com.stpl.app.gtnforecasting.dao.NADataSelectionDAO;
import com.stpl.app.gtnforecasting.dao.impl.NACommonResultsDAOImpl;
import com.stpl.app.gtnforecasting.dao.impl.NADataSelectionDAOImpl;
import com.stpl.app.gtnforecasting.nationalassumptions.dto.DataSelectionDTO;
import com.stpl.app.gtnforecasting.nationalassumptions.dto.ResultList;
import com.stpl.app.gtnforecasting.nationalassumptions.queryutils.DataSelectionQueryUtils;
import com.stpl.app.gtnforecasting.nationalassumptions.util.CommonUtils;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.gtnforecasting.utils.xmlparser.SQlUtil;
import com.stpl.app.model.CompanyMaster;
import com.stpl.app.model.ForecastConfig;
import com.stpl.app.model.ItemGroup;
import com.stpl.app.model.ItemMaster;
import com.stpl.app.model.NaProjDetails;
import com.stpl.app.model.NaProjMaster;
import com.stpl.app.service.CompanyMasterLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.ItemGroupLocalServiceUtil;
import com.stpl.app.service.ItemMasterLocalServiceUtil;
import com.stpl.app.service.NaProjDetailsLocalServiceUtil;
import com.stpl.app.service.NaProjMasterLocalServiceUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.OrderFactoryUtil;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.model.User;
import com.stpl.portal.service.UserLocalServiceUtil;
import com.stpl.util.dao.orm.CustomSQLUtil;
import com.vaadin.data.Container;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;
import org.jboss.logging.Logger;


/**
 *
 * @author gopinath
 */
public class DataSelectionLogic {
    
    private static final NACommonResultsDAO DAO = new NACommonResultsDAOImpl();
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(DataSelectionLogic.class);  
    DataSelectionQueryUtils dsQueryUtils = new DataSelectionQueryUtils();
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public ResultList searchCCP(Object companyValue,Object therapeuticClassValue, Object productGroupValue,Object businessUnit) {
        Set<DataSelectionDTO> productsList = new HashSet<>();
        ResultList result = new ResultList();
        List resultList = new ArrayList();
        try{  
            
            if (!StringUtils.EMPTY.equals((String) productGroupValue)) {
                resultList = dsQueryUtils.getProductGroupresults(companyValue, therapeuticClassValue, productGroupValue,businessUnit);
            } else {
                resultList = getAvailableProducts(companyValue, therapeuticClassValue, businessUnit);
            }
            productsList = getCustomizedCustomersAndContractsFromModel(resultList);
            result.setAvailableProducts(productsList);
            if (!productsList.isEmpty()) {
                result.setFlag(Constant.SUCCESS);
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return result;
    }
 
 /**
     * This method is used to convert the View Mandated model to DTO
     *
     * @param resultList
     * @return list
     */
    private Set<DataSelectionDTO> getCustomizedCustomersAndContractsFromModel(
            List resultList) {
        Set<DataSelectionDTO> customersAndContracts = new HashSet<>();
        for (Object element : resultList) {
            Object[] object = (Object[]) element;
            DataSelectionDTO cac = new DataSelectionDTO();
            cac.setProductNo(object[0] == null ? StringUtils.EMPTY : String.valueOf(object[0].toString()));
            cac.setProductName(object[1] == null ? StringUtils.EMPTY : String.valueOf(object[1].toString()));
            cac.setItemMasterSid(object[NumericConstants.TWO] == null ? 0 : Integer.valueOf(object[NumericConstants.TWO].toString()));            
            customersAndContracts.add(cac);
        }

        return customersAndContracts;
    }
    
    public List<HelperDTO> loadCompanies() {
            return CommonUtils.getManufacturesWithIds();
    }
    
    public String saveProjection(Object[] values,
            List<DataSelectionDTO> selectedProducts, boolean updateFlag,SessionDTO session)  {
        String result= StringUtils.EMPTY;
            try {
                int companyId=Integer.valueOf(values[1].toString());
                NaProjMaster naProjMaster = updateFlag ? NaProjMasterLocalServiceUtil.getNaProjMaster(session.getProjectionId()) :NaProjMasterLocalServiceUtil.createNaProjMaster(0);
                
                naProjMaster.setNaProjName(values[0].toString());
                naProjMaster.setCompanyMasterSid(companyId);
                naProjMaster.setTherapeuticClass(Integer.valueOf(values[NumericConstants.TWO].toString()));
                naProjMaster.setItemGroupSid(Integer.valueOf(values[NumericConstants.THREE].toString()));
                naProjMaster.setBusinessUnit(Integer.valueOf(values[NumericConstants.FOUR].toString()));
                if (!updateFlag) {
                    naProjMaster.setCreatedDate(new Date());
                    naProjMaster.setCreatedBy(Integer.valueOf(session.getUserId()));
                }
                naProjMaster.setModifiedBy(Integer.valueOf(session.getUserId()));
                naProjMaster.setModifiedDate(new Date());
                try {
                    naProjMaster = updateFlag ? NaProjMasterLocalServiceUtil.updateNaProjMaster(naProjMaster) : NaProjMasterLocalServiceUtil
                            .addNaProjMaster(naProjMaster);
                    if (!updateFlag) {
                      result= saveProducts(naProjMaster.getNaProjMasterSid(),selectedProducts);  
                    }
                } catch (Exception e) {
                    LOGGER.error(e);
                    return Constant.FAIL;
                }
                
            } catch (PortalException | SystemException ex) {
                LOGGER.error(ex);
                return Constant.FAIL;
            }
        return result;
    }
    private String saveProducts(int projectionId, List<DataSelectionDTO> selectedProducts) {
        for (DataSelectionDTO projCC : selectedProducts) {
            NaProjDetails projectionprod = NaProjDetailsLocalServiceUtil.createNaProjDetails(0);
            projectionprod.setNaProjMasterSid(projectionId);
            projectionprod.setItemMasterSid(projCC.getItemMasterSid());
            try {
                NaProjDetailsLocalServiceUtil
                        .addNaProjDetails(projectionprod);
            } catch (Exception e) {
                LOGGER.error(e);
                return Constant.FAIL;
            }
        }
        return String.valueOf(projectionId);
    }
    
    public void updateProducts(int projectionId, List<Integer> insertList, List<Integer> removeList) {
        StringBuilder naProjDetailsID=new StringBuilder();
        boolean flag =false;
        if (!removeList.isEmpty()) {
            try {
                for (Integer naProjID : removeList) {
                    if (!flag) {
                        naProjDetailsID.append(naProjID);
                        flag=true;
                    }else{
                        naProjDetailsID.append(",").append(naProjID);
                    }
                    
                }
                Map<String, Object> input = new HashMap<>();
                input.put("?PID", naProjDetailsID);
                String customSql = CustomSQLUtil.get("na.update");
                
                for (String key : input.keySet()) {
                    customSql = customSql.replace(key, String.valueOf(input.get(key)));
                }
                DAO.executeBulkUpdateQuery(customSql);
                for (Integer naProjID : removeList) {
                    try {
                        NaProjDetailsLocalServiceUtil
                                .deleteNaProjDetails(naProjID);
                    } catch (Exception e) {
                        LOGGER.error(e);
                    }
                }
            } catch (PortalException | SystemException ex) {
                LOGGER.error(ex);
            }
        }
        if (!insertList.isEmpty()) {
            for (Integer itemSid : insertList) {
                NaProjDetails projectionprod = NaProjDetailsLocalServiceUtil.createNaProjDetails(0);
                projectionprod.setNaProjMasterSid(projectionId);
                projectionprod.setItemMasterSid(itemSid);
                try {
                    NaProjDetailsLocalServiceUtil
                            .addNaProjDetails(projectionprod);
                } catch (Exception e) {
                    LOGGER.error(e);
                }
            } 
        }
    }
    @SuppressWarnings("unchecked")
    public List<DataSelectionDTO> getProjectionResults(String projectionName,String getSelectedProducts, Object companyValueId, Object thearupeticValueId, int productGroupId,int startIndex, int offset, Set<Container.Filter> filters,List<SortByColumn> sortByColumns,Object businessUnit) {
        List<DataSelectionDTO> projectionResults = new ArrayList<>();
        try {  
          List<Object[]> returnList= dsQueryUtils.loadResultsTable(projectionName,getSelectedProducts, companyValueId,thearupeticValueId, productGroupId,startIndex, offset,filters,sortByColumns,businessUnit);
           projectionResults= getCustomizedProjectionResults(returnList);
        } catch (Exception ex) {
                LOGGER.error(ex);
        }
         return projectionResults;
    }
    public int getProjectionResultsCount(String projectionName, String getSelectedProducts, Object companyValueId, Object thearupeticValueId, int productGroupId, Set<Container.Filter> filters,Object businessUnit) {
        int count = 0;
        try {
            count = dsQueryUtils.loadResultsTableCount(projectionName, getSelectedProducts, companyValueId, thearupeticValueId, productGroupId, filters,businessUnit);

        } catch (Exception ex) {
            LOGGER.error(ex);
        }

        return count;

    }

    private List<DataSelectionDTO> getCustomizedProjectionResults(List<Object[]> resultList) throws ParseException {
        List<DataSelectionDTO> projectionResults = new ArrayList<>();       
        for (Object[] obj : resultList) {
            DataSelectionDTO projResult = new DataSelectionDTO();
            projResult.setProjectionName(obj[0]!=null?obj[0].toString():StringUtils.EMPTY);
            projResult.setCompany(obj[1]!=null?obj[1].toString():StringUtils.EMPTY);
            projResult.setProductGroup(obj[NumericConstants.TWO]!=null?obj[NumericConstants.TWO].toString():StringUtils.EMPTY);
            projResult.setTherapeuticClass((obj[NumericConstants.THREE] == null || Constant.SELECT_ONE.equals(String.valueOf(obj[NumericConstants.THREE]))) ? StringUtils.EMPTY : String.valueOf(obj[NumericConstants.THREE]));
            projResult.setCreatedDate(formatDate(convertNullToEmpty(String.valueOf(obj[NumericConstants.FOUR]))));
            projResult.setModifiedDate(formatDate(convertNullToEmpty(String.valueOf(obj[NumericConstants.FIVE]))));
            projResult.setCreatedDateSearch(parsetDate(convertNullToEmpty(String.valueOf(obj[NumericConstants.FOUR]))));
            projResult.setModifiedDateSearch(parsetDate(convertNullToEmpty(String.valueOf(obj[NumericConstants.FIVE]))));
            projResult.setCreatedBy(CommonUtils.getUserNameById(String.valueOf(obj[NumericConstants.SIX])));
            projResult.setProjectionId(obj[NumericConstants.SEVEN]!=null?(Integer)obj[NumericConstants.SEVEN]:0);
            
            projResult.setBusinessUnitName(obj[NumericConstants.EIGHT]!=null?obj[NumericConstants.EIGHT].toString():StringUtils.EMPTY);
            projectionResults.add(projResult);
        }
         return projectionResults;
    }
      
    public static String getDescription(int id) {
            String companyName = StringUtils.EMPTY;
         try {
            CompanyMaster companyMaster = CompanyMasterLocalServiceUtil.getCompanyMaster(id);
            companyName = companyMaster.getCompanyName();
            } catch (Exception ex) {
               LOGGER.error(ex);
            }
         return companyName;
        }
    public static String getProductDescription(int id) {
            String productName = StringUtils.EMPTY;
         try {
            ItemGroup itemGroup = ItemGroupLocalServiceUtil.getItemGroup(id);
            productName = itemGroup.getItemGroupName();
            }catch (Exception ex) {
                LOGGER.error(ex.getMessage());
                if((ex instanceof NoSuchItemGroupException) && ( ex.getMessage().contains("No ItemGroup exists with the primary key"))){
                       return StringUtils.EMPTY;
                }
            }
         return productName;
        }
    public String deleteProjection(int projectionId, String currentUserId) {
        LOGGER.debug("Inside deleteProjection");
        String deletedProjectionName = StringUtils.EMPTY; 
        List<NaProjDetails> naProjDetails;
        try { 
            NaProjMaster projMaster ;
            projMaster = NaProjMasterLocalServiceUtil.getNaProjMaster(projectionId);
             if (!String.valueOf(projMaster.getCreatedBy()).equals(currentUserId)) {
                return "accessDenined";
            }
             dsQueryUtils.deleteResultsTable(projectionId);
             
            DynamicQuery projectioncProdDetailsDynamicQuery = DynamicQueryFactoryUtil
                    .forClass(NaProjDetails.class);
            projectioncProdDetailsDynamicQuery.add(RestrictionsFactoryUtil.eq(
                    "naProjMasterSid", projectionId));
            naProjDetails = NaProjDetailsLocalServiceUtil
                    .dynamicQuery(projectioncProdDetailsDynamicQuery);
            if (naProjDetails != null
                    && !naProjDetails.isEmpty()) {
                for (NaProjDetails prodDetails : naProjDetails) {
                    NaProjDetailsLocalServiceUtil
                            .deleteNaProjDetails(prodDetails);
                }
            }
                    
           projMaster = NaProjMasterLocalServiceUtil.deleteNaProjMaster(projMaster.getNaProjMasterSid());
           deletedProjectionName = projMaster.getNaProjName();

        } catch (Exception ex) {          
            LOGGER.error(ex);
        }
        return deletedProjectionName;
    }     
    public User getUserByID(String userId) {
        User user = null;
        try {
            user = UserLocalServiceUtil.getUser(Long.valueOf(userId));
        } catch (Exception ex) {
              LOGGER.error(ex);
        }
        return user;
    }
    public List getProjection(int projectionId){
       List finalList=new ArrayList();
       DynamicQuery query =DynamicQueryFactoryUtil.forClass(NaProjDetails.class);
       query.add(RestrictionsFactoryUtil.eq("naProjMasterSid", projectionId));
        NaProjMaster naProjMaster=NaProjMasterLocalServiceUtil.createNaProjMaster(0);
         List<NaProjDetails> detailsList=new ArrayList<>();
        try {
           naProjMaster=  NaProjMasterLocalServiceUtil.getNaProjMaster(projectionId);
           detailsList= NaProjDetailsLocalServiceUtil.dynamicQuery(query);
        } catch (Exception ex) {
           LOGGER.error(ex); 
        }
          finalList.add(naProjMaster);
          finalList.add(detailsList);
        return finalList;
    }
    public ItemMaster getItemMaster(int itemMasterSid){
         ItemMaster  itemMaster= ItemMasterLocalServiceUtil.createItemMaster(0);
        try {
            itemMaster=   ItemMasterLocalServiceUtil.getItemMaster(itemMasterSid);
        } catch (Exception ex) {
           LOGGER.error(ex);
        }
        return itemMaster;
    }
    public static ForecastConfig getTimePeriod() {
        NADataSelectionDAO dataSelectionDao = new NADataSelectionDAOImpl();
        List resultList = null;
        int businessProcessType = 0;
        ForecastConfig forecastConfig = null;
        try {
            businessProcessType = CommonUtils.getHelperCode(CommonUtils.BUSINESS_PROCESS_TYPE, "National Assumptions");

            DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ForecastConfig.class);
            dynamicQuery.add(RestrictionsFactoryUtil.eq("businessProcessType", businessProcessType));
            dynamicQuery.addOrder(OrderFactoryUtil.desc(Constant.VERSION_NO));
            resultList = dataSelectionDao.getForecastConfig(dynamicQuery);

            if (resultList != null && !resultList.isEmpty()) {
                forecastConfig = (ForecastConfig) resultList.get(0);
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return forecastConfig;
    }
  private static Date parsetDate(String value) throws ParseException {
        Date date = null;
        String tempDate;
        SimpleDateFormat parse = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
        if (value != null && !StringUtils.EMPTY.equals(value) && !Constant.NULL.equals(value)) {
            tempDate = format.format(parse.parse(value));
            date = format.parse(tempDate);
        }

        return date;
    }

    public static String convertNullToEmpty(Object value) {
        String returnValue;
        if (value == null || Constant.NULL.equals(String.valueOf(value))|| Constant.SELECT_ONE.equals(String.valueOf(value))) {
            returnValue = StringUtils.EMPTY;
        } else {
            returnValue = String.valueOf(value);
        }
        return returnValue;
    }

    private static String formatDate(String value) throws ParseException {
        String date = StringUtils.EMPTY;
        SimpleDateFormat parse = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
        if (value != null && !StringUtils.EMPTY.equals(value) && !Constant.NULL.equals(value)) {
            date = format.format(parse.parse(value));
        }
        return date;
    }
     public List getAvailableProducts(Object companyValue, Object therapeuticClassValue, Object businessUnit) {
        StringBuilder sql = null;
          try{
            if (!String.valueOf(companyValue).equals("0") || (!String.valueOf(therapeuticClassValue).equals("") && 
                    !therapeuticClassValue.toString().equals("0") )|| !String.valueOf(businessUnit).equals("0")) {
                sql = new StringBuilder(CustomSQLUtil.get("loadAvailableProducts"));
                if (String.valueOf(businessUnit).equals("0") || String.valueOf(businessUnit).equals("null") || String.valueOf(businessUnit).isEmpty()) {
                    sql = sql.append(" WHERE  IM.ORGANIZATION_KEY like '%' ");
                } else {
                    sql = sql.append(" WHERE  IM.ORGANIZATION_KEY like '").append(String.valueOf(businessUnit)).append("' ");
                }
                if (!String.valueOf(companyValue).equals("0") && !String.valueOf(companyValue).equals("null")&& !String.valueOf(companyValue).isEmpty()) {
                    sql = sql.append(" AND  CM.COMPANY_MASTER_SID='").append(String.valueOf(companyValue)).append("' ");
                }
                if (!String.valueOf(therapeuticClassValue).equals("null") && !String.valueOf(therapeuticClassValue).equals("0")&& !String.valueOf(therapeuticClassValue).isEmpty()) {
                    sql = sql.append("AND  IM.THERAPEUTIC_CLASS = '").append(String.valueOf(therapeuticClassValue)).append("'");
                }
            }

            
              List list=HelperTableLocalServiceUtil.executeSelectQuery(sql.toString());
            return list;
        } catch (Exception e) {            
            LOGGER.error(e);
            LOGGER.error(sql.toString());
            return Collections.emptyList();
        } finally {
}
    }   
    /**
     * Method return a selected company name
     *
     * @param companyId
     * @return
     */
    public List getCompanyName(int companyId) {
        
        String query = SQlUtil.getQuery("get-companyName");
        query = query.replace("@GLCOMP", StringUtils.EMPTY + companyId);

        List list = HelperTableLocalServiceUtil.executeSelectQuery(query);
        
        return list;
    }
}
