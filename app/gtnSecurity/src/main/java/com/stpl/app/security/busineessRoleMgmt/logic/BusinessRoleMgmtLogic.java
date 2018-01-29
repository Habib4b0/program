package com.stpl.app.security.busineessRoleMgmt.logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.stpl.app.model.BusinessroleMaster;
import com.stpl.app.security.busineessRoleMgmt.dto.BusinessroleMasterDTO;
import com.stpl.app.security.businessRoleModuleMaster.util.CommonUtils;
import com.stpl.app.security.dao.BusinessRoleMgmtLogicDAO;
import com.stpl.app.security.dao.impl.BusinessRoleMgmtLogicDAOImpl;
import com.stpl.app.service.BusinessroleMasterLocalServiceUtil;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.vaadin.v7.data.util.BeanItemContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BusinessRoleMgmtLogic extends BeanItemContainer<BusinessroleMasterDTO>{
	private static final Logger LOGGER = LoggerFactory
			.getLogger(BusinessRoleMgmtLogic.class.getName());
    public BusinessRoleMgmtLogic() {
        super(BusinessroleMasterDTO.class);

    }
 private BusinessRoleMgmtLogicDAO dao = new BusinessRoleMgmtLogicDAOImpl();

    public List<BusinessroleMasterDTO> getAllBusinessroles() throws SystemException {
    	DynamicQuery businessroleMasterDynamicQuery = BusinessroleMasterLocalServiceUtil.dynamicQuery();
    	Criterion criterion = RestrictionsFactoryUtil.eq("isActive", "Y");
    	criterion = RestrictionsFactoryUtil.or(criterion , RestrictionsFactoryUtil.isNull("isActive"));
    	businessroleMasterDynamicQuery.add(criterion);
		@SuppressWarnings("unchecked")
		List<BusinessroleMaster> list = dao.getBusinessroleMasterList(businessroleMasterDynamicQuery);                
        List<BusinessroleMasterDTO> searchList;
        searchList = getCustomizedListFromModel(list);

        return searchList;
    }

    public List<BusinessroleMasterDTO> getCustomizedListFromModel(
            List<BusinessroleMaster> list) {
        List<BusinessroleMasterDTO> searchItemList = new ArrayList<BusinessroleMasterDTO>();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                BusinessroleMasterDTO businessroleMasterDTO = new BusinessroleMasterDTO();
                BusinessroleMaster businessroleMaster = (BusinessroleMaster) list.get(i);
                businessroleMasterDTO.setCreatedBy(businessroleMaster.getCreatedBy());
                businessroleMasterDTO.setCreatedDate(CommonUtils.convertDateToString(businessroleMaster.getCreatedDate()));
                businessroleMasterDTO.setHierarchyLevel(String.valueOf(businessroleMaster.getHierarchyLevel()));
                businessroleMasterDTO.setUserId(businessroleMaster.getUsersSid());
                businessroleMasterDTO.setProcessed(businessroleMaster.getProcessed());
                businessroleMasterDTO.setBusinessroleName(businessroleMaster.getBusinessroleName());
                businessroleMasterDTO.setModifiedBy(businessroleMaster.getModifiedBy());
                businessroleMasterDTO.setModifiedDate(CommonUtils.convertDateToString(businessroleMaster.getModifiedDate()));
                businessroleMasterDTO.setBusinessroleDesc(businessroleMaster.getBusinessroleDesc());
                businessroleMasterDTO.setBusinessroleMasterSid(businessroleMaster.getBusinessroleMasterSid());
                businessroleMasterDTO.setIsActive(businessroleMaster.getIsActive());
                businessroleMasterDTO.setSystemId(String.valueOf(businessroleMaster.getBusinessroleMasterSid()));
                searchItemList.add(businessroleMasterDTO);

            }
        }
        return searchItemList;
    }

	public String saveBusinessRoleMgmt(ErrorfulFieldGroup searchBusinessRoleForm) {
		
		if(searchBusinessRoleForm.getField(CommonUtils.BUSINESS_ROLE_MASTERSID).getValue()!=null && !"0".equals(searchBusinessRoleForm.getField(CommonUtils.BUSINESS_ROLE_MASTERSID).getValue().toString())){
			updateBusinessRoleMgmt(searchBusinessRoleForm);
			return "success";
		}else{
			return addBusinessRoleMgmt(searchBusinessRoleForm);
		}
		
		
	}

	private void updateBusinessRoleMgmt(
			ErrorfulFieldGroup searchBusinessRoleForm) {
		String businessroleDesc=searchBusinessRoleForm.getField("businessroleDesc").getValue().toString();
		String businessroleName=searchBusinessRoleForm.getField(CommonUtils.BUSINESS_ROLE_NAME).getValue().toString();
		String hierarchyLevel=searchBusinessRoleForm.getField("hierarchyLevel").getValue().toString();
		Integer businessroleMasterSid =  Integer.parseInt(searchBusinessRoleForm.getField(CommonUtils.BUSINESS_ROLE_MASTERSID).getValue().toString());
		try {
			BusinessroleMaster businessroleMaster = BusinessroleMasterLocalServiceUtil.fetchBusinessroleMaster(businessroleMasterSid);
			businessroleMaster.setBusinessroleDesc(businessroleDesc);
			businessroleMaster.setBusinessroleName(businessroleName);
			businessroleMaster.setHierarchyLevel(Integer.parseInt(hierarchyLevel));
			businessroleMaster.setModifiedDate(new Date());
			dao.updateBusinessRoleMgmt(businessroleMaster);
		} catch (SystemException e) {
			// TODO Auto-generated catch block
                       
			LOGGER.error(e.getMessage());
		}
	}

	private String addBusinessRoleMgmt(ErrorfulFieldGroup searchBusinessRoleForm) {
		BusinessroleMaster businessroleMaster = BusinessroleMasterLocalServiceUtil.createBusinessroleMaster(0);
                LOGGER.debug("addBusinessRoleMgmt");
		String businessroleDesc=searchBusinessRoleForm.getField("businessroleDesc").getValue().toString();
		String businessroleName=searchBusinessRoleForm.getField(CommonUtils.BUSINESS_ROLE_NAME).getValue().toString();
		String hierarchyLevel=searchBusinessRoleForm.getField("hierarchyLevel").getValue().toString();
		businessroleMaster.setBusinessroleName(businessroleName);
		businessroleMaster.setBusinessroleDesc(businessroleDesc);
		businessroleMaster.setHierarchyLevel(Integer.parseInt(hierarchyLevel));
		businessroleMaster.setCreatedDate(new Date());
                businessroleMaster.setModifiedDate(new Date());
                businessroleMaster.setCreatedBy(0);
                businessroleMaster.setModifiedBy(0);
		businessroleMaster.setIsActive("Y");
		try {
			DynamicQuery businessroleMasterDynamicQuery = BusinessroleMasterLocalServiceUtil.dynamicQuery();
	    	businessroleMasterDynamicQuery.add(RestrictionsFactoryUtil.eq(CommonUtils.BUSINESS_ROLE_NAME, businessroleName));
			@SuppressWarnings("unchecked")
			List<BusinessroleMaster> list = dao.getBusinessroleMasterList(businessroleMasterDynamicQuery);
			if(list!=null && list.size()>0){
				return "already exists";
			}
	        dao.saveBusinessRoleMgmt(businessroleMaster);
			return "success";
		} catch (SystemException e) {
			// TODO Auto-generated catch block
                    
			LOGGER.error(e.getMessage());
			return null;
		}
                   
		}
		
	public void deleteBusinessRole(int businessroleMasterSid) {
		try {
			BusinessroleMaster businessroleMaster = dao.getBusinessRoleUsingId(businessroleMasterSid);
			businessroleMaster.setIsActive("N");
			dao.updateBusinessRoleMgmt(businessroleMaster);
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage());
		}
		
	}

}
