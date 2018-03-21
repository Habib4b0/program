package com.stpl.app.security.businessRoleModuleMaster.logic;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import com.stpl.app.model.BusinessroleMaster;
import com.stpl.app.model.BusinessroleModule;
import com.stpl.app.model.ModuleSubmoduleMaster;
import com.stpl.app.security.businessRoleModuleMaster.dto.HelperDTO;
import com.stpl.app.security.businessRoleModuleMaster.dto.SearchBusinessRoleModuleForm;
import com.stpl.app.security.businessRoleModuleMaster.util.CommonUtils;
import com.stpl.app.security.businessRoleModuleMaster.util.ModuleNameCheckingUtil;
import com.stpl.app.security.dao.BusinessRoleModuleMasterLogicDAO;
import com.stpl.app.security.dao.impl.BusinessRoleModuleMasterLogicDAOImpl;
import com.stpl.app.service.BusinessroleModuleLocalServiceUtil;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.ifs.ui.util.NumericConstants;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.transaction.Transactional;
import com.stpl.app.service.BusinessroleMasterLocalServiceUtil;
import com.stpl.app.service.ModuleSubmoduleMasterLocalServiceUtil;
import com.stpl.ifs.util.constants.BooleanConstant;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.server.VaadinSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Transactional
public class BusinessRoleModuleSearchLogic extends BeanItemContainer<SearchBusinessRoleModuleForm> implements Serializable {

    public BusinessRoleModuleSearchLogic() {
        super(SearchBusinessRoleModuleForm.class);

    }
    private final transient BusinessRoleModuleMasterLogicDAO dao = new BusinessRoleModuleMasterLogicDAOImpl();
    /**
     *
     */
    private static final long serialVersionUID = 4573149356126437540L;
    private static final Logger LOGGER = LoggerFactory
            .getLogger(BusinessRoleModuleSearchLogic.class.getName());
    
    private final int VersionNo = NumericConstants.ONE;
    public List<SearchBusinessRoleModuleForm> searchmoduleAccessDetails(
            ErrorfulFieldGroup searchBusinessRoleModuleForm)
            throws SystemException, PortalException {
 
        String businessRoleName = "";
        String subModuleName = "";
        String moduleName = "";
        List<SearchBusinessRoleModuleForm> searchList = new ArrayList<>();
        try {
        if (searchBusinessRoleModuleForm.getField(CommonUtils.BUSINESS_ROLE_NAME)
                .getValue() != null) {
            businessRoleName = searchBusinessRoleModuleForm
                    .getField(CommonUtils.BUSINESS_ROLE_NAME).getValue().toString();
           LOGGER.debug("Business Role Name is {}.{}.", CommonUtils.BUSINESS_ROLE_NAME,businessRoleName);
        }
        if (searchBusinessRoleModuleForm.getField(CommonUtils.SUB_MODULE_NAME).getValue() != null) {
            subModuleName = searchBusinessRoleModuleForm
                    .getField(CommonUtils.SUB_MODULE_NAME).getValue().toString();
            LOGGER.debug("Sub Module Name Name is {}.{}.", CommonUtils.SUB_MODULE_NAME, subModuleName);
        }
        if (searchBusinessRoleModuleForm.getField(CommonUtils.MODULE_NAME).getValue() != null) {
            moduleName = searchBusinessRoleModuleForm.getField(CommonUtils.MODULE_NAME)
                    .getValue().toString();
            LOGGER.debug("{}.{}.",CommonUtils.MODULE_NAME,moduleName);
        }
       

        @SuppressWarnings("rawtypes")
        List businessRoleModuleList = dao.getBusinessRoleModuleList(businessRoleName, subModuleName, moduleName);
        
        @SuppressWarnings("rawtypes")
        List subModuleProperyList = dao.getSubModuleProperyList(businessRoleName, subModuleName, moduleName);
            LOGGER.debug("SubModuleProperyList size is {}.", subModuleProperyList.size());
       
            DynamicQuery businessRoleDynamicQuery = BusinessroleMasterLocalServiceUtil.dynamicQuery();
            if (StringUtils.isNotBlank(businessRoleName)) {
                businessRoleName = businessRoleName.replace(CommonUtils.CHAR_ASTERISK,
                        CommonUtils.CHAR_PERCENT);
                businessRoleDynamicQuery.add(RestrictionsFactoryUtil
                        .eq(CommonUtils.BUSINESS_ROLE_NAME, businessRoleName));
            }
            @SuppressWarnings("unchecked")
            List<BusinessroleMaster> businessroleMastersList = dao.getBusinessroleMasterList(businessRoleDynamicQuery);
           
            int businessroleMasterId = businessroleMastersList.get(0).getBusinessroleMasterSid();
            
        if (businessRoleModuleList != null && !businessRoleModuleList.isEmpty()) {
            searchList = getCustomizedSearchFormFromObject(businessRoleModuleList, subModuleProperyList, moduleName, businessroleMasterId);
           
        } else {
            searchList = getCustomizedSearchFormFromSubModuleProperty(subModuleProperyList, businessroleMasterId, moduleName);
        }
        
        } catch (SystemException e) {
                LOGGER.error(e.getMessage());
                }
        return searchList;
        
    }

    public List<SearchBusinessRoleModuleForm> searchFieldAccessDetails(
            ErrorfulFieldGroup searchBusinessRoleModuleForm)
            throws SystemException, PortalException {

        String businessRoleName = "";
        String subModuleName = "";
        String moduleName = "";
        LOGGER.debug("In Search Field Access Details-----------------");
        List<SearchBusinessRoleModuleForm> searchList = new ArrayList<>();
        try {
        if (searchBusinessRoleModuleForm.getField(CommonUtils.BUSINESS_ROLE_NAME)
                .getValue() != null) {
            businessRoleName = searchBusinessRoleModuleForm
                    .getField(CommonUtils.BUSINESS_ROLE_NAME).getValue().toString();
            
        }
        if (searchBusinessRoleModuleForm.getField("submoduleName").getValue() != null) {
            subModuleName = searchBusinessRoleModuleForm
                    .getField("submoduleName").getValue().toString();
           
        }
        if (searchBusinessRoleModuleForm.getField(CommonUtils.MODULE_NAME).getValue() != null) {
            moduleName = searchBusinessRoleModuleForm.getField(CommonUtils.MODULE_NAME)
                    .getValue().toString();
            
        }

      
        @SuppressWarnings("rawtypes")
        List businessRoleModuleList = dao.findFieldAccessDetails(businessRoleName, subModuleName, moduleName);
       
        @SuppressWarnings("rawtypes")
        List subModuleProperyList = dao.findSubModuleFieldDetails(businessRoleName, subModuleName, moduleName);
       
        if (businessRoleModuleList != null && !businessRoleModuleList.isEmpty()) {
            searchList = getCustomizedSearchFieldObject(businessRoleModuleList, subModuleProperyList, moduleName);
            LOGGER.debug("Custom sql: {}. ",searchList.size());
        } else {
            DynamicQuery businessRoleDynamicQuery = BusinessroleMasterLocalServiceUtil.dynamicQuery();
            if (StringUtils.isNotBlank(businessRoleName)) {
                businessRoleName = businessRoleName.replace(CommonUtils.CHAR_ASTERISK,
                        CommonUtils.CHAR_PERCENT);
                businessRoleDynamicQuery.add(RestrictionsFactoryUtil
                        .eq(CommonUtils.BUSINESS_ROLE_NAME, businessRoleName));
            }
            @SuppressWarnings("unchecked")
            List<BusinessroleMaster> businessroleMastersList = dao.getBusinessroleMasterList(businessRoleDynamicQuery);
            int businessroleMasterId = businessroleMastersList.get(0).getBusinessroleMasterSid();

            searchList = getCustomizedSearchFormFieldProperty(subModuleProperyList, businessroleMasterId, moduleName);
            LOGGER.debug("Custom sql getCustomizedSearchFormFieldProperty is {}.", searchList.size());
        }
        } catch (SystemException e) {
               LOGGER.error(e.getMessage());
                }
        return searchList;
    }

    private List<SearchBusinessRoleModuleForm> getCustomizedSearchFormFromSubModuleProperty(
            @SuppressWarnings("rawtypes") List submodulePropertyMastersList,
            int businessroleMasterId, String moduleName) {
        List<SearchBusinessRoleModuleForm> searchBusinessRoleModuleFormList = new ArrayList<>();
        LOGGER.debug("In getCustomizedSearchFormFromSubModuleProperty-------------");
        try {
        if (submodulePropertyMastersList != null) {
            for (int i = 0; i < submodulePropertyMastersList.size(); i++) {
                Object[] obj = (Object[]) submodulePropertyMastersList.get(i);
                SearchBusinessRoleModuleForm searchBusinessRoleModuleForm = new SearchBusinessRoleModuleForm();
                searchBusinessRoleModuleForm.setModuleName(moduleName);
                searchBusinessRoleModuleForm.setSubmoduleName(obj[NumericConstants.TWO].toString());
                searchBusinessRoleModuleForm.setFunction("null".equals(String.valueOf(obj[0]))?"":String.valueOf(obj[0]));
                searchBusinessRoleModuleForm.setTabName("null".equals(String.valueOf(obj[NumericConstants.FOUR]))?"":String.valueOf(obj[NumericConstants.FOUR]));
                searchBusinessRoleModuleForm.setAccess(BooleanConstant.getFalseFlag());
                searchBusinessRoleModuleForm.setCategoryName(String.valueOf(obj[NumericConstants.THREE]));
                searchBusinessRoleModuleForm.setSubmodulePropertyId(String.valueOf(obj[NumericConstants.ONE]));
                searchBusinessRoleModuleForm.setBusinessroleMasterSid(businessroleMasterId);
                searchBusinessRoleModuleFormList
                        .add(searchBusinessRoleModuleForm);
            }
        }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return searchBusinessRoleModuleFormList;
    }

    private List<SearchBusinessRoleModuleForm> getCustomizedSearchFormFieldProperty(
            @SuppressWarnings("rawtypes") List submodulePropertyMastersList,
            int businessroleMasterId, String moduleName) {
        List<SearchBusinessRoleModuleForm> searchBusinessRoleModuleFormList = new ArrayList<>();
       LOGGER.debug("In getCustomizedSearchFormFieldProperty---------------");
        try {
        if (submodulePropertyMastersList != null) {
            for (int i = 0; i < submodulePropertyMastersList.size(); i++) {
                Object[] obj = (Object[]) submodulePropertyMastersList.get(i);
                SearchBusinessRoleModuleForm searchBusinessRoleModuleForm = new SearchBusinessRoleModuleForm();
                searchBusinessRoleModuleForm.setModuleName(moduleName);
                searchBusinessRoleModuleForm.setSubmoduleName(obj[NumericConstants.TWO].toString());
                searchBusinessRoleModuleForm.setFieldName(obj[0].toString());
                searchBusinessRoleModuleForm.setAdd(BooleanConstant.getFalseFlag());
                searchBusinessRoleModuleForm.setEdit(BooleanConstant.getFalseFlag());
                searchBusinessRoleModuleForm.setView(BooleanConstant.getFalseFlag());
                searchBusinessRoleModuleForm.setSubmodulePropertyId(String.valueOf(obj[NumericConstants.ONE]));
                searchBusinessRoleModuleForm.setNullFlag((obj[NumericConstants.THREE] != null && obj[NumericConstants.THREE].toString().equals("N")) ? true : false);
                   searchBusinessRoleModuleForm.setTabName("null".equals(String.valueOf(obj[NumericConstants.FOUR]))?"":String.valueOf(obj[NumericConstants.FOUR]));
                searchBusinessRoleModuleForm.setCategoryName(String.valueOf(obj[NumericConstants.FIVE]));
                   searchBusinessRoleModuleForm.setBusinessroleMasterSid(businessroleMasterId);
                searchBusinessRoleModuleFormList
                        .add(searchBusinessRoleModuleForm);
            }
        }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return searchBusinessRoleModuleFormList;
    }

    public List<SearchBusinessRoleModuleForm> getCustomizedSearchFieldObject(
            @SuppressWarnings("rawtypes") List list, @SuppressWarnings("rawtypes") List submoduleProperties, String moduleName) {
        List<SearchBusinessRoleModuleForm> searchBusinessRoleModuleFormList = new ArrayList<>();
        
        if (submoduleProperties != null) {
            for (int i = 0; i < submoduleProperties.size(); i++) {
                SearchBusinessRoleModuleForm searchBusinessRoleModuleForm = new SearchBusinessRoleModuleForm();
                Object[] obj = (Object[]) submoduleProperties.get(i);
                searchBusinessRoleModuleForm.setModuleName(moduleName);
                searchBusinessRoleModuleForm.setSubmoduleName(String.valueOf(obj[NumericConstants.TWO]));
                searchBusinessRoleModuleForm
                        .setFieldName("null".equals(String.valueOf(obj[0]))? "":String.valueOf(obj[0]));
                searchBusinessRoleModuleForm
                        .setSubmodulePropertyId(String.valueOf(obj[NumericConstants.ONE]));
                boolean addFlag = false;
                boolean editFlag = false;
                boolean viewFlag = false;
                String businessroleMasterId = StringUtils.EMPTY;
                int id = 0;
                
                //BMM.ROLE_MODULE_MNG_ID,MSM.MODULE_NAME,MSM.SUBMODULE_NAME,SPM.DISPLAY_NAME,BMM.ADD_FLAG,BMM.EDIT_FLAG,BMM.VIEW_FLAG,SPM.MODULE_PROPERTY_ID,BM.BUSINESSROLE_MASTER_ID
                for (int j = 0; j < list.size(); j++) {
                    Object[] roles = (Object[]) list.get(j);
                   
                    if (roles[NumericConstants.SEVEN].equals(obj[NumericConstants.ONE])) {
                        id = ((Integer) roles[0]).intValue();
                        businessroleMasterId = String.valueOf(roles[NumericConstants.EIGHT]);
                     
                        if (roles[NumericConstants.FOUR] != null
                                && "1".equals(String.valueOf(roles[NumericConstants.FOUR]))) {
                            addFlag = true;
                        } else {
                            addFlag = false;
                        }
                        if (roles[NumericConstants.FIVE] != null
                                && "1".equals(String.valueOf(roles[NumericConstants.FIVE]))) {
                            editFlag = true;
                        } else {
                            editFlag = false;
                        }
                        if (roles[NumericConstants.SIX] != null
                                && "1".equals(String.valueOf(roles[NumericConstants.SIX]))) {
                            viewFlag = true;
                        } else {
                            viewFlag = false;
                        }
                        break;
                    }
                }
                searchBusinessRoleModuleForm.setNullFlag((obj[NumericConstants.THREE] != null && obj[NumericConstants.THREE].toString().equals("N")) ? true : false);
                searchBusinessRoleModuleForm.setTabName("null".equals(String.valueOf(obj[NumericConstants.FOUR]))?" ":String.valueOf(obj[NumericConstants.FOUR]));
                searchBusinessRoleModuleForm.setSystemId(id);
                searchBusinessRoleModuleForm
                        .setBusinessroleMasterSid(Integer.parseInt(businessroleMasterId.trim()));
                searchBusinessRoleModuleForm.setCategoryName(String.valueOf(obj[NumericConstants.FIVE]));
                searchBusinessRoleModuleForm.setAdd(addFlag);
                searchBusinessRoleModuleForm.setEdit(editFlag);
                searchBusinessRoleModuleForm.setView(viewFlag);
                searchBusinessRoleModuleFormList
                        .add(searchBusinessRoleModuleForm);
            }
        }
        return searchBusinessRoleModuleFormList;
    }

    public List<SearchBusinessRoleModuleForm> getCustomizedSearchFormFromObject(
            @SuppressWarnings("rawtypes") List list, @SuppressWarnings("rawtypes") List submoduleProperties, String moduleName,int businessroleMasterSid) {
        List<SearchBusinessRoleModuleForm> searchBusinessRoleModuleFormList = new ArrayList<>();
        LOGGER.debug("List-------------------: {}.", list.size());
        if (submoduleProperties != null) {
            for (int i = 0; i < submoduleProperties.size(); i++) {
                SearchBusinessRoleModuleForm searchBusinessRoleModuleForm = new SearchBusinessRoleModuleForm();
                Object[] obj = (Object[]) submoduleProperties.get(i);
                searchBusinessRoleModuleForm.setModuleName(moduleName);
                searchBusinessRoleModuleForm.setSubmoduleName(obj[NumericConstants.TWO].toString());
                searchBusinessRoleModuleForm
                        .setFunction("null".equals(String.valueOf(obj[0]))?" ":String.valueOf(obj[0]));
                searchBusinessRoleModuleForm
                        .setSubmodulePropertyId(String.valueOf(obj[NumericConstants.ONE]));
                boolean access = false;
                String businessroleMasterId = String.valueOf(businessroleMasterSid);
                int id = 0;
                for (int j = 0; j < list.size(); j++) {
                    Object[] roles = (Object[]) list.get(j);
                    if (roles[NumericConstants.FIVE].equals(obj[NumericConstants.ONE])) {
                        id = ((Integer) roles[0]).intValue();
                        businessroleMasterId = String.valueOf(roles[NumericConstants.SIX]);
                        if (roles[NumericConstants.FOUR] != null
                                && "1".equals(String.valueOf(roles[NumericConstants.FOUR]))) {
                            access = true;
                        } else {
                            access = false;
                        }
                        break;
                    }
                }
                searchBusinessRoleModuleForm.setCategoryName(String.valueOf(obj[NumericConstants.THREE]));
                searchBusinessRoleModuleForm.setTabName("null".equals(String.valueOf(obj[NumericConstants.FOUR]))?" ":String.valueOf(obj[NumericConstants.FOUR]));
                searchBusinessRoleModuleForm.setSystemId(id);
                searchBusinessRoleModuleForm
                        .setBusinessroleMasterSid(Integer.parseInt(businessroleMasterId));
                searchBusinessRoleModuleForm.setAccess(access);

                searchBusinessRoleModuleFormList
                        .add(searchBusinessRoleModuleForm);
            }
        }
        return searchBusinessRoleModuleFormList;
    }

    public List<SearchBusinessRoleModuleForm> getCustomizedSearchFormObject(
            @SuppressWarnings("rawtypes") List list, @SuppressWarnings("rawtypes") List submoduleProperties, String moduleName) {
        List<SearchBusinessRoleModuleForm> searchBusinessRoleModuleFormList = new ArrayList<>();
        if (submoduleProperties != null) {
            for (int i = 0; i < submoduleProperties.size(); i++) {
                SearchBusinessRoleModuleForm searchBusinessRoleModuleForm = new SearchBusinessRoleModuleForm();
                Object[] obj = (Object[]) submoduleProperties.get(i);
                searchBusinessRoleModuleForm.setModuleName(moduleName);
                searchBusinessRoleModuleForm.setSubmoduleName(obj[NumericConstants.TWO].toString());
                searchBusinessRoleModuleForm
                        .setFunction(obj[0].toString());
                searchBusinessRoleModuleForm
                        .setSubmodulePropertyId(String.valueOf(obj[NumericConstants.ONE]));
                boolean access = false;
                String businessroleMasterId = StringUtils.EMPTY;
                int id = 0;
                for (int j = 0; j < list.size(); j++) {
                    Object[] roles = (Object[]) list.get(j);
                    if (roles[NumericConstants.FIVE].equals(obj[NumericConstants.ONE])) {
                        id = ((BigDecimal) roles[0]).intValue();
                        businessroleMasterId = String.valueOf(roles[NumericConstants.SIX]);
                        if (roles[NumericConstants.FOUR] != null
                                && "1".equals(String.valueOf(roles[NumericConstants.FOUR]))) {
                            access = true;
                        } else {
                            access = false;
                        }
                        break;
                    }
                }
                searchBusinessRoleModuleForm.setSystemId(id);
                searchBusinessRoleModuleForm
                        .setBusinessroleMasterSid(Integer.parseInt(businessroleMasterId));
                searchBusinessRoleModuleForm.setAccess(access);
                searchBusinessRoleModuleFormList
                        .add(searchBusinessRoleModuleForm);
            }
        }
        return searchBusinessRoleModuleFormList;
    }

    public List<String> getModuleNames() {
        List<String> moduleNames = new ArrayList<>();
        try {
            DynamicQuery moduleSubmoduleMasterDynamicQuery = ModuleSubmoduleMasterLocalServiceUtil.dynamicQuery();
            moduleSubmoduleMasterDynamicQuery
                    .setProjection(ProjectionFactoryUtil
                    .distinct(ProjectionFactoryUtil
                    .property(CommonUtils.MODULE_NAME)));
            @SuppressWarnings("unchecked")
            List<ModuleSubmoduleMaster> moduleSubmoduleMastersList = dao.getModuleSubmoduleMaster(moduleSubmoduleMasterDynamicQuery);
            LOGGER.debug("Module Submodule Masters List Size is -----------------> {}.",moduleSubmoduleMastersList.size());
            for (int i = 0; i < moduleSubmoduleMastersList.size(); i++) {
                moduleNames.add(String.valueOf(moduleSubmoduleMastersList
                        .get(i)));
            }
        } catch (SystemException e) {
            LOGGER.error(e.getMessage());

        }
        return moduleNames;
    }

    public List<HelperDTO> getBusinessRoleNames() {
        List<HelperDTO> list = new ArrayList<>();
         HelperDTO busiessRoleName1 = new HelperDTO(0,"-Select One-");
      list.add(busiessRoleName1);
        try {
            int count = dao.count();
            List<BusinessroleMaster> businessroleMastersList = dao.getBusinessroleMasters(0, count);
            for (int i = 0; i < businessroleMastersList.size(); i++) {
                HelperDTO busiessRoleName = new HelperDTO();
                BusinessroleMaster businessroleMaster = businessroleMastersList
                        .get(i);
                busiessRoleName.setId(businessroleMaster
                        .getBusinessroleMasterSid());
                busiessRoleName.setDescription(businessroleMaster
                        .getBusinessroleName());
             
                list.add(busiessRoleName);

            }

            return list;
        } catch (SystemException e) {
            LOGGER.error(e.getMessage());

        }

        return list;
    }

    public void saveBusinessRoleModuleMaster(
            List<SearchBusinessRoleModuleForm> itemIds, List<SearchBusinessRoleModuleForm> fieldIds, String userId) {
        try {

            Date date = new Date();
            for (int i = 0; i < itemIds.size(); i++) {
                if (itemIds.get(i).getSystemId() != 0) {

                    BusinessroleModule businessroleModuleMaster = dao.getBusinessroleModuleMaster(itemIds.get(i).getSystemId());

                    if (itemIds.get(i).getAccess()) {
                        businessroleModuleMaster.setAccessModule("1");
                    } else {
                        businessroleModuleMaster.setAccessModule("0");
                    }
                    businessroleModuleMaster.setModifiedDate(date);
                    businessroleModuleMaster.setModifiedBy(Integer.parseInt(userId));
                    businessroleModuleMaster.setVersionNo(VersionNo);
                    dao.updateBusinessroleModuleMaster(businessroleModuleMaster);

                } else {
                    BusinessroleModule businessroleModuleMaster = BusinessroleModuleLocalServiceUtil.createBusinessroleModule(0);
                    SearchBusinessRoleModuleForm currentRecord = itemIds.get(i);
                    businessroleModuleMaster.setSubmodulePropertyId(Integer.parseInt(currentRecord.getSubmodulePropertyId()));
                    if (itemIds.get(i).getAccess()) {
                        businessroleModuleMaster.setAccessModule("1");
                    } else {
                        businessroleModuleMaster.setAccessModule("0");
                    }
                    businessroleModuleMaster.setBusinessroleMasterSid(currentRecord.getBusinessroleMasterSid());
                    businessroleModuleMaster.setCreatedDate(date);
                    businessroleModuleMaster.setModifiedDate(date);
                    businessroleModuleMaster.setCreatedBy(Integer.parseInt(userId));
                    businessroleModuleMaster.setModifiedBy(NumericConstants.ONE);
                    businessroleModuleMaster.setVersionNo(NumericConstants.ONE);
                    dao.saveBusinessroleModuleMaster(businessroleModuleMaster);
                }
            }
            saveBusinessRoleModuleMaster(fieldIds);
        } catch (SystemException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public void saveBusinessRoleModuleMaster(
            List<SearchBusinessRoleModuleForm> fieldIds) {
        Date date = new Date();
        try {
           
            for (int i = 0; i < fieldIds.size(); i++) {
                BusinessroleModule businessroleModuleMaster = BusinessroleModuleLocalServiceUtil.createBusinessroleModule(0);
                if (!ModuleNameCheckingUtil.moduleNameCheckingFun(fieldIds.get(i).getSubmoduleName())) {
                    if (fieldIds.get(i).getAdd()) {
                        businessroleModuleMaster.setAddFlag("1");
                    } else {
                        businessroleModuleMaster.setAddFlag("0");
                    }
                    if (fieldIds.get(i).getEdit()) {
                        businessroleModuleMaster.setEditFlag("1");
                    } else {
                        businessroleModuleMaster.setEditFlag("0");
                    }
                }
                if (fieldIds.get(i).getView()) {
                    businessroleModuleMaster.setViewFlag("1");
                } else {
                    businessroleModuleMaster.setViewFlag("0");
                }
                if (fieldIds.get(i).getSystemId() != 0) {
           
                    BusinessroleModule businessroleModuleMasterFetch = dao.getBusinessroleModuleMaster(fieldIds.get(i).getSystemId());
                    businessroleModuleMasterFetch = setBusinessroleModuleMasterFetch(businessroleModuleMasterFetch, businessroleModuleMaster);
                    String userId = String.valueOf(VaadinSession.getCurrent().getAttribute("userId"));
                    businessroleModuleMasterFetch.setModifiedBy(Integer.parseInt(userId));
                    businessroleModuleMasterFetch.setModifiedDate(date);
                    businessroleModuleMaster.setVersionNo(VersionNo);
                    dao.updateBusinessroleModuleMaster(businessroleModuleMasterFetch);  
                     
                } else {
                    SearchBusinessRoleModuleForm currentRecord = fieldIds.get(i);
                    businessroleModuleMaster.setSubmodulePropertyId(Integer.parseInt(currentRecord.getSubmodulePropertyId()));
                    String userId = String.valueOf(VaadinSession.getCurrent().getAttribute("userId"));
                    businessroleModuleMaster.setCreatedDate(date);
                    businessroleModuleMaster.setBusinessroleMasterSid(currentRecord.getBusinessroleMasterSid());
                    businessroleModuleMaster.setCreatedBy(Integer.parseInt(userId));
                    businessroleModuleMaster.setModifiedBy(Integer.parseInt(userId));
                      businessroleModuleMaster.setVersionNo(NumericConstants.ONE);
                    businessroleModuleMaster.setModifiedDate(date);
                     dao.saveBusinessroleModuleMaster(businessroleModuleMaster);
                }
             
            }
            LOGGER.debug("Updated Successfully");
        } catch (SystemException | NumberFormatException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public BusinessroleModule setBusinessroleModuleMasterFetch(BusinessroleModule businessroleModuleMasterFetch, BusinessroleModule businessroleModuleMaster) {
        businessroleModuleMasterFetch.setAddFlag(businessroleModuleMaster.getAddFlag());
        businessroleModuleMasterFetch.setEditFlag(businessroleModuleMaster.getEditFlag());
        businessroleModuleMasterFetch.setViewFlag(businessroleModuleMaster.getViewFlag());
        return businessroleModuleMasterFetch;

    }

    public List<String> getSubModules(String moduleName) {
        List<String> subModuleNames = new ArrayList<>();
        try {
            DynamicQuery moduleSubmoduleMasterDynamicQuery = ModuleSubmoduleMasterLocalServiceUtil.dynamicQuery();
            String tempModuleName;
            if (StringUtils.isNotBlank(moduleName)) {
                tempModuleName = moduleName.replace(CommonUtils.CHAR_ASTERISK,
                        CommonUtils.CHAR_PERCENT);
                moduleSubmoduleMasterDynamicQuery.add(RestrictionsFactoryUtil
                        .like(CommonUtils.MODULE_NAME, tempModuleName));
            }
            @SuppressWarnings("unchecked")
            List<ModuleSubmoduleMaster> moduleSubmoduleMastersList = dao.getSubModules(moduleSubmoduleMasterDynamicQuery);

            for (int i = 0; i < moduleSubmoduleMastersList.size(); i++) {
                subModuleNames.add(moduleSubmoduleMastersList.get(i)
                        .getSubmoduleName());
            }
        } catch (SystemException e) {
            LOGGER.error(e.getMessage());

        }
        return subModuleNames;
    }
}
