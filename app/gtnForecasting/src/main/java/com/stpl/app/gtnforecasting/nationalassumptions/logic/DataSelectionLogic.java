/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.nationalassumptions.logic;



import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.stpl.app.exception.NoSuchItemGroupException;
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
import com.stpl.app.service.ForecastConfigLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.ItemGroupLocalServiceUtil;
import com.stpl.app.service.ItemMasterLocalServiceUtil;
import com.stpl.app.service.NaProjDetailsLocalServiceUtil;
import com.stpl.app.service.NaProjMasterLocalServiceUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.vaadin.v7.data.Container;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 * @author gopinath
 */
public class DataSelectionLogic {
    
    private static final NACommonResultsDAO DAO = new NACommonResultsDAOImpl();
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DataSelectionLogic.class);  
    protected DataSelectionQueryUtils dsQueryUtils = new DataSelectionQueryUtils();
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
            LOGGER.error(e.getMessage());
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
            cac.setItemMasterSid(object[NumericConstants.TWO] == null ? 0 : Integer.parseInt(object[NumericConstants.TWO].toString()));            
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
                int companyId=Integer.parseInt(values[1].toString());
                NaProjMaster naProjMaster = updateFlag ? NaProjMasterLocalServiceUtil.getNaProjMaster(session.getProjectionId()) :NaProjMasterLocalServiceUtil.createNaProjMaster(0);
                
                naProjMaster.setNaProjName(values[0].toString());
                naProjMaster.setCompanyMasterSid(companyId);
                naProjMaster.setTherapeuticClass(Integer.parseInt(values[NumericConstants.TWO].toString()));
                naProjMaster.setItemGroupSid(Integer.parseInt(values[NumericConstants.THREE].toString()));
                naProjMaster.setBusinessUnit(Integer.parseInt(values[NumericConstants.FOUR].toString()));
                if (!updateFlag) {
                    naProjMaster.setCreatedDate(new Date());
                    naProjMaster.setCreatedBy(Integer.parseInt(session.getUserId()));
                }
                naProjMaster.setModifiedBy(Integer.parseInt(session.getUserId()));
                naProjMaster.setModifiedDate(new Date());
                    naProjMaster = updateFlag ? NaProjMasterLocalServiceUtil.updateNaProjMaster(naProjMaster) : NaProjMasterLocalServiceUtil
                            .addNaProjMaster(naProjMaster);
                    if (!updateFlag) {
                      result= saveProducts(naProjMaster.getNaProjMasterSid(),selectedProducts);  
                    }
                
            } catch (PortalException | SystemException ex) {
                LOGGER.error(ex.getMessage());
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
            } catch (SystemException e) {
                LOGGER.error(e.getMessage());
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
                        naProjDetailsID.append(',').append(naProjID);
                    }
                    
                }
                Map<String, Object> input = new HashMap<>();
                input.put("?PID", naProjDetailsID);
                String customSql = SQlUtil.getQuery(getClass(),"na.update");
                
                for (Map.Entry<String, Object> key : input.entrySet()) {
                    customSql = customSql.replace(key.getKey(), String.valueOf(key.getValue()));
                }
                DAO.executeBulkUpdateQuery(customSql);
                for (Integer naProjID : removeList) {
                        NaProjDetailsLocalServiceUtil
                                .deleteNaProjDetails(naProjID);
                }
            } catch (PortalException | SystemException ex) {
                LOGGER.error(ex.getMessage());
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
                } catch (SystemException e) {
                    LOGGER.error(e.getMessage());
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
        } catch (SystemException | ParseException ex) {
                LOGGER.error(ex.getMessage());
        }
         return projectionResults;
    }
    public int getProjectionResultsCount(String projectionName, String getSelectedProducts, Object companyValueId, Object thearupeticValueId, int productGroupId, Set<Container.Filter> filters,Object businessUnit) {
        int count = 0;
        try {
            count = dsQueryUtils.loadResultsTableCount(projectionName, getSelectedProducts, companyValueId, thearupeticValueId, productGroupId, filters,businessUnit);

        } catch (SystemException | ParseException ex) {
            LOGGER.error(ex.getMessage());
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
            } catch (PortalException | SystemException ex) {
               LOGGER.error(ex.getMessage());
            }
         return companyName;
        }
    public static String getProductDescription(int id) {
            String productName = StringUtils.EMPTY;
         try {
            ItemGroup itemGroup = ItemGroupLocalServiceUtil.getItemGroup(id);
            productName = itemGroup.getItemGroupName();
            }catch (PortalException | SystemException ex) {
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
             
            DynamicQuery projectioncProdDetailsDynamicQuery = NaProjDetailsLocalServiceUtil.dynamicQuery();
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

        } catch (PortalException | SystemException ex) {          
            LOGGER.error(ex.getMessage());
        }
        return deletedProjectionName;
    }     
    public User getUserByID(String userId) {
        User user = null;
        try {
            user = UserLocalServiceUtil.getUser(Long.parseLong(userId));
        } catch (PortalException | SystemException | NumberFormatException ex) {
              LOGGER.error(ex.getMessage());
        }
        return user;
    }
    public List getProjection(int projectionId){
       List finalList=new ArrayList();
       DynamicQuery query =NaProjDetailsLocalServiceUtil.dynamicQuery();
       query.add(RestrictionsFactoryUtil.eq("naProjMasterSid", projectionId));
        NaProjMaster naProjMaster=NaProjMasterLocalServiceUtil.createNaProjMaster(0);
         List<NaProjDetails> detailsList=new ArrayList<>();
        try {
           naProjMaster=  NaProjMasterLocalServiceUtil.getNaProjMaster(projectionId);
           detailsList= NaProjDetailsLocalServiceUtil.dynamicQuery(query);
        } catch (PortalException | SystemException ex) {
           LOGGER.error(ex.getMessage()); 
        }
          finalList.add(naProjMaster);
          finalList.add(detailsList);
        return finalList;
    }
    public ItemMaster getItemMaster(int itemMasterSid){
         ItemMaster  itemMaster= ItemMasterLocalServiceUtil.createItemMaster(0);
        try {
            itemMaster=   ItemMasterLocalServiceUtil.getItemMaster(itemMasterSid);
        } catch (PortalException | SystemException ex) {
           LOGGER.error(ex.getMessage());
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

            DynamicQuery dynamicQuery = ForecastConfigLocalServiceUtil.dynamicQuery();
            dynamicQuery.add(RestrictionsFactoryUtil.eq("businessProcessType", businessProcessType));
            dynamicQuery.addOrder(OrderFactoryUtil.desc(Constant.VERSION_NO));
            resultList = dataSelectionDao.getForecastConfig(dynamicQuery);

            if (resultList != null && !resultList.isEmpty()) {
                forecastConfig = (ForecastConfig) resultList.get(0);
            }
        } catch (SystemException ex) {
            LOGGER.error(ex.getMessage());
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
          try{
            if (availableProductCheck(companyValue, therapeuticClassValue, businessUnit)) {
                StringBuilder sql = new StringBuilder(SQlUtil.getQuery(getClass(),"loadAvailableProducts"));
                if (isBusinessUnit(businessUnit)) {
                    sql = sql.append(" WHERE  IM.ORGANIZATION_KEY like '%' ");
                } else {
                    sql = sql.append(" WHERE  IM.ORGANIZATION_KEY like '").append(String.valueOf(businessUnit)).append("' ");
                }
                if (isCompanyValue(companyValue)) {
                    sql = sql.append(" AND  CM.COMPANY_MASTER_SID='").append(String.valueOf(companyValue)).append("' ");
                }
                if (isTherapeuticClass(therapeuticClassValue)) {
                    sql = sql.append("AND  IM.THERAPEUTIC_CLASS = '").append(String.valueOf(therapeuticClassValue)).append('\'');
                }
                sql = sql.append(" AND HT.DESCRIPTION ='NDC-11'");
                List list = HelperTableLocalServiceUtil.executeSelectQuery(sql.toString());
                return list;
            }
         } catch (Exception e) {
             LOGGER.error(e.getMessage());
             return Collections.emptyList();
         }
         return Collections.emptyList();
    }   

    private static boolean isTherapeuticClass(Object therapeuticClassValue) {
        return !String.valueOf(therapeuticClassValue).equals("null") && !String.valueOf(therapeuticClassValue).equals("0")&& !String.valueOf(therapeuticClassValue).isEmpty();
    }

    private static boolean isCompanyValue(Object companyValue) {
        return !String.valueOf(companyValue).equals("0") && !String.valueOf(companyValue).equals("null")&& !String.valueOf(companyValue).isEmpty();
    }

    private static boolean isBusinessUnit(Object businessUnit) {
        return String.valueOf(businessUnit).equals("0") || String.valueOf(businessUnit).equals("null") || String.valueOf(businessUnit).isEmpty();
    }

    private static boolean availableProductCheck(Object companyValue, Object therapeuticClassValue, Object businessUnit) {
        return !String.valueOf(companyValue).equals("0") || (!String.valueOf(therapeuticClassValue).equals("") && 
                !therapeuticClassValue.toString().equals("0") )|| !String.valueOf(businessUnit).equals("0");
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
