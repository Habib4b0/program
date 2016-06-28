/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.nationalassumptions.logic;


import com.stpl.app.NoSuchItemGroupException;
import com.stpl.app.galforecasting.dao.NACommonResultsDAO;
import com.stpl.app.galforecasting.dao.NADataSelectionDAO;
import com.stpl.app.galforecasting.dao.impl.NACommonResultsDAOImpl;
import com.stpl.app.galforecasting.dao.impl.NADataSelectionDAOImpl;
import com.stpl.app.galforecasting.nationalassumptions.dto.DataSelectionDTO;
import com.stpl.app.galforecasting.nationalassumptions.dto.ResultList;
import com.stpl.app.galforecasting.nationalassumptions.queryutils.DataSelectionQueryUtils;
import com.stpl.app.galforecasting.nationalassumptions.util.CommonUtils;
import com.stpl.app.galforecasting.utils.Constant;
import com.stpl.app.model.CompanyMaster;
import com.stpl.app.model.ForecastConfig;
import com.stpl.app.model.ItemGroup;
import com.stpl.app.model.ItemMaster;
import com.stpl.app.model.NaProjDetails;
import com.stpl.app.model.NaProjMaster;
import com.stpl.app.service.CompanyMasterLocalServiceUtil;
import com.stpl.app.service.ItemGroupLocalServiceUtil;
import com.stpl.app.service.ItemMasterLocalServiceUtil;
import com.stpl.app.service.NaProjDetailsLocalServiceUtil;
import com.stpl.app.service.NaProjMasterLocalServiceUtil;
import com.stpl.ifs.util.HelperDTO;
import static com.stpl.ifs.util.constants.GlobalConstants.*;
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
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.server.VaadinSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
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
	public ResultList searchCCP(Object companyValue,Object therapeuticClassValue, Object productGroupValue) {
        Set<DataSelectionDTO> productsList = new HashSet<>();
        ResultList result = new ResultList();
        List resultList = new ArrayList();
        try{  
            
            if (!StringUtils.EMPTY.equals((String) productGroupValue)) {
                resultList = dsQueryUtils.getProductGroupresults(companyValue, therapeuticClassValue, productGroupValue);
            } else {
                resultList = NaProjMasterLocalServiceUtil.getAvailableProducts(companyValue, therapeuticClassValue, productGroupValue, null);
            }
            productsList = getCustomizedCustomersAndContractsFromModel(resultList);
            result.setAvailableProducts(productsList);
            if (productsList.size() > 0) {
                result.setFlag(Constant.SUCCESS);
            }
            resultList = null;
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
        Set<DataSelectionDTO> customersAndContracts = new HashSet<DataSelectionDTO>();
        for (Object element : resultList) {
            Object[] object = (Object[]) element;
            DataSelectionDTO cac = new DataSelectionDTO();
            cac.setProductNo(object[0] == null ? StringUtils.EMPTY : String.valueOf(object[0].toString()));
            cac.setProductName(object[1] == null ? StringUtils.EMPTY : String.valueOf(object[1].toString()));
            cac.setItemMasterSid(object[2] == null ? 0 : Integer.valueOf(object[2].toString()));            
            customersAndContracts.add(cac);
        }

        return customersAndContracts;
    }
    
    public List<HelperDTO> loadCompanies() {
            return CommonUtils.getManufacturesWithIds();
    }
    
    public String saveProjection(Object[] values,
            List<DataSelectionDTO> selectedProducts, boolean updateFlag) throws FieldGroup.CommitException {
        String result= StringUtils.EMPTY;
            try {
                String userId = (String) VaadinSession.getCurrent().getAttribute(
                        Constant.USER_ID);
                int companyId=Integer.valueOf(values[1].toString());
                NaProjMaster naProjMaster = updateFlag ? NaProjMasterLocalServiceUtil.getNaProjMaster(Integer.valueOf(VaadinSession.getCurrent().getAttribute(Constant.PROJECTION_ID).toString())) :NaProjMasterLocalServiceUtil.createNaProjMaster(0);
                
                naProjMaster.setNaProjName(values[0].toString());
                naProjMaster.setCompanyMasterSid(companyId);
                naProjMaster.setTherapeuticClass(Integer.valueOf(values[2].toString()));
                naProjMaster.setItemGroupSid(Integer.valueOf(values[3].toString()));
                if (!updateFlag) {
                    naProjMaster.setCreatedDate(new Date());
                    naProjMaster.setCreatedBy(Integer.valueOf(userId));
                }
                naProjMaster.setModifiedBy(Integer.valueOf(userId));
                naProjMaster.setModifiedDate(new Date());
                try {
                    naProjMaster = updateFlag ? NaProjMasterLocalServiceUtil.updateNaProjMaster(naProjMaster) : NaProjMasterLocalServiceUtil
                            .addNaProjMaster(naProjMaster);
                    if (!updateFlag) {
                      result= saveProducts(naProjMaster.getNaProjMasterSid(),selectedProducts);  
                    }
                    if(!selectedProducts.isEmpty()){
                        selectedProducts = null; 
                    }
                } catch (Exception e) {
                    LOGGER.error(e.getMessage());
                    return Constant.FAIL;
                }
                
            } catch (PortalException ex) {
                LOGGER.error(ex.getMessage());
                return Constant.FAIL;
            } catch (SystemException ex) {
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
            } catch (Exception e) {
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
                        naProjDetailsID.append(",").append(naProjID);
                    }
                    
                }
                Map<String, Object> input = new HashMap<String, Object>();
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
                        e.printStackTrace();
                        LOGGER.error(e.getMessage());
                    }
                }
            } catch (PortalException ex) {            
                LOGGER.error(ex.getMessage());
            } catch (SystemException ex) {
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
                } catch (Exception e) {
                    LOGGER.error(e.getMessage());
                }
            } 
        }
    }
    @SuppressWarnings("unchecked")
    public List<DataSelectionDTO> getProjectionResults(String projectionName,String getSelectedProducts, Object companyValueId, Object thearupeticValueId, int productGroupId,int startIndex, int offset, Set<Container.Filter> filters,List<SortByColumn> sortByColumns) {
        List<DataSelectionDTO> projectionResults = new ArrayList<DataSelectionDTO>();
        try {  
          List<Object[]> returnList= dsQueryUtils.loadResultsTable(projectionName,getSelectedProducts, companyValueId,thearupeticValueId, productGroupId,startIndex, offset,filters,sortByColumns);
           projectionResults= getCustomizedProjectionResults(returnList);
           if(!returnList.isEmpty()){
               returnList = null;
           }
        } catch (Exception ex) {
                LOGGER.error(ex.getMessage());
        }
         return projectionResults;
    }
    public int getProjectionResultsCount(String projectionName, String getSelectedProducts, Object companyValueId, Object thearupeticValueId, int productGroupId, Set<Container.Filter> filters) {
        int count = 0;
        try {
            count = dsQueryUtils.loadResultsTableCount(projectionName, getSelectedProducts, companyValueId, thearupeticValueId, productGroupId, filters);

        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }

        return count;

    }

    private List<DataSelectionDTO> getCustomizedProjectionResults(List<Object[]> resultList) throws ParseException {
        List<DataSelectionDTO> projectionResults = new ArrayList<DataSelectionDTO>();       
        for (Object[] obj : resultList) {
            DataSelectionDTO projResult = new DataSelectionDTO();
            projResult.setProjectionName(obj[0]!=null?obj[0].toString():StringUtils.EMPTY);
            projResult.setCompany(obj[1]!=null?obj[1].toString():StringUtils.EMPTY);
            projResult.setProductGroup(obj[2]!=null?obj[2].toString():StringUtils.EMPTY);
            projResult.setTherapeuticClass((obj[3] == null || Constant.SELECT_ONE.equals(String.valueOf(obj[3]))) ? StringUtils.EMPTY : String.valueOf(obj[3]));
            projResult.setCreatedDate(formatDate(convertNullToEmpty(String.valueOf(obj[4]))));
            projResult.setModifiedDate(formatDate(convertNullToEmpty(String.valueOf(obj[5]))));
            projResult.setCreatedDateSearch(parsetDate(convertNullToEmpty(String.valueOf(obj[4]))));
            projResult.setModifiedDateSearch(parsetDate(convertNullToEmpty(String.valueOf(obj[5]))));
            projResult.setCreatedBy(CommonUtils.getUserNameById(String.valueOf(obj[6])));
            projResult.setProjectionId(obj[7]!=null?(Integer)obj[7]:0);
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
                LOGGER.error(ex.getMessage());
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
                if(ex instanceof NoSuchItemGroupException){
                   if( ex.getMessage().contains("No ItemGroup exists with the primary key")){
                       return StringUtils.EMPTY;
                   }
                }
            }
         return productName;
        }
    public String deleteProjection(int projectionId, String currentUserId) {
        LOGGER.info("Inside deleteProjection");
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
                    && naProjDetails.size() > 0) {
                for (NaProjDetails prodDetails : naProjDetails) {
                    NaProjDetailsLocalServiceUtil
                            .deleteNaProjDetails(prodDetails);
                }
                naProjDetails = null;
            }
                    
           projMaster = NaProjMasterLocalServiceUtil.deleteNaProjMaster(projMaster.getNaProjMasterSid());
           deletedProjectionName = projMaster.getNaProjName();

        } catch (Exception ex) {          
            LOGGER.error(ex.getMessage());
        }
        return deletedProjectionName;
    }     
    public User getUserByID(String userId) {
        User user = null;
        try {
            user = UserLocalServiceUtil.getUser(Long.valueOf(userId));
        } catch (Exception ex) {
              LOGGER.error(ex.getMessage());
        }
        return user;
    }
    public List getProjection(int projectionId){
       List finalList=new ArrayList();
       DynamicQuery query =DynamicQueryFactoryUtil.forClass(NaProjDetails.class);
       query.add(RestrictionsFactoryUtil.eq("naProjMasterSid", projectionId));
        NaProjMaster naProjMaster=NaProjMasterLocalServiceUtil.createNaProjMaster(0);
         List<NaProjDetails> detailsList=new ArrayList<NaProjDetails>();
        try {
           naProjMaster=  NaProjMasterLocalServiceUtil.getNaProjMaster(projectionId);
           detailsList= NaProjDetailsLocalServiceUtil.dynamicQuery(query);
        } catch (Exception ex) {
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
        } catch (Exception ex) {
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

            DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ForecastConfig.class);
            dynamicQuery.add(RestrictionsFactoryUtil.eq("businessProcessType", businessProcessType));
            dynamicQuery.addOrder(OrderFactoryUtil.desc(Constant.VERSION_NO));
            resultList = dataSelectionDao.getForecastConfig(dynamicQuery);

            if (resultList != null && !resultList.isEmpty()) {
                forecastConfig = (ForecastConfig) resultList.get(0);
            }
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        return forecastConfig;
    }
  private static Date parsetDate(String value) throws ParseException {
        Date date = null;
        String tempDate = StringUtils.EMPTY;
        SimpleDateFormat parse = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
        if (value != null && !StringUtils.EMPTY.equals(value) && !Constant.NULL.equals(value)) {
            tempDate = format.format(parse.parse(value));
            date = (format.parse(tempDate));
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

}
