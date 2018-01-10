/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.projnameconfig.logic;

import com.stpl.app.adminconsole.common.util.CommonUtil;
import com.stpl.app.model.ProjectionNameConfig;
import com.stpl.app.adminconsole.projnameconfig.dto.ProjectionNameDTO;
import com.stpl.app.service.ProjectionNameConfigLocalServiceUtil;
import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.vaadin.server.VaadinSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class ProjNameConfigLogic.
 *
 * @author santanukumar
 */
public class ProjNameConfigLogic {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ProjNameConfigLogic.class);

    
    public List<ProjectionNameDTO> getBusinessProcessAttributes() {
         final List<ProjectionNameDTO> nameDTOs = new ArrayList<>();
    
            LOGGER.debug("getBusinessProcessAttributes method started ");
           
            final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
           final Date date=new Date();
            final String[] attributeArr = new String[]{"Market Type", "Customer #", "Customer Name", "Brand", "Product #", "Product Name", sdf.format(date), String.valueOf(new Date())};
            for (String attributeArr1 : attributeArr) {
                final ProjectionNameDTO dTO = new ProjectionNameDTO();
                dTO.setAvailableAttributes(attributeArr1);
                nameDTOs.add(dTO);
            }
            LOGGER.debug("getBusinessProcessAttributes method ended ");
            return nameDTOs;
    }

    /**
     * Save proj name config details.
     *
     * @param finalTemplateName the final template name
     * @return the string
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public String saveProjNameConfigDetails(final String finalTemplateName)throws SystemException  {       
         LOGGER.debug("saveProjNameConfigDetails method started ");
         final int userId = Integer.valueOf((String) VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
         
            final String[] names=finalTemplateName.split("~",NumericConstants.TWO);          
            ProjectionNameConfig config = null;
            config.setBusinessProcessType(names[0]);
            config.setSelectedAttributes(names[1]);
            config.setVersionNo(1);
            config.setCreatedBy(userId);
            config.setCreatedDate(new Date());
            config.setModifiedBy(userId);
            config.setModifiedDate(new Date());
            ProjectionNameConfigLocalServiceUtil.addProjectionNameConfig(config);
          LOGGER.debug("saveProjNameConfigDetails method ended ");
        return "success";
    }

    /**
     * Gets the available name template.
     *
     * @param businessProcess the business process
     * @return the available name template
     * @throws SystemException the system exception
     */
    public List<ProjectionNameDTO> getAvailableNameTemplate(final String businessProcess)throws SystemException  {
        LOGGER.debug("getAvailableNameTemplate method started ");
        final DynamicQuery nameDynamicQuery = ProjectionNameConfigLocalServiceUtil.dynamicQuery();
         final Map userInfoMap = (HashMap) CommonUtil.getCreatedByUser();
         final List<ProjectionNameDTO> nameDTOs = new ArrayList<>();
         nameDynamicQuery.add(RestrictionsFactoryUtil.ilike("businessProcessType", businessProcess));
         List<ProjectionNameConfig> nameConfigs=ProjectionNameConfigLocalServiceUtil.dynamicQuery(nameDynamicQuery);
         for (ProjectionNameConfig nameConfig : nameConfigs) {
            ProjectionNameDTO dTO=new ProjectionNameDTO();
            dTO.setProjectionNameCongigSid(nameConfig.getProjectionNameConfigSid());
            dTO.setBusinessProcessType(nameConfig.getBusinessProcessType());
            dTO.setSelectedAttributes(nameConfig.getSelectedAttributes());
            dTO.setVersionNo(String.valueOf(nameConfig.getVersionNo()));
            dTO.setCreatedBy(String.valueOf(userInfoMap.get(nameConfig.getCreatedBy())));
            dTO.setCreatedDate(nameConfig.getCreatedDate().toString());
            dTO.setModifiedBy(String.valueOf(userInfoMap.get(nameConfig.getModifiedBy())));
            dTO.setModifiedDate(nameConfig.getModifiedDate().toString());
            nameDTOs.add(dTO);
        }
         LOGGER.debug("getAvailableNameTemplate method ended ");
         return nameDTOs;
    }
    
    /**
     * Duplicate check.
     *
     * @param businessProcess the business process
     * @param finalName the final name
     * @return true, if successful
     * @throws SystemException the system exception
     */
    public boolean duplicateCheck(final String businessProcess,final String finalName)throws SystemException  {
         LOGGER.debug("duplicateCheck method started ");
        int count=0;
        final DynamicQuery nameDynamicQuery = ProjectionNameConfigLocalServiceUtil.dynamicQuery();
        nameDynamicQuery.add(RestrictionsFactoryUtil.ilike("businessProcessType", businessProcess));
         List<ProjectionNameConfig> nameConfigs=ProjectionNameConfigLocalServiceUtil.dynamicQuery(nameDynamicQuery);
         for (ProjectionNameConfig nameConfig : nameConfigs) {         
            final String projName=nameConfig.getBusinessProcessType()+nameConfig.getSelectedAttributes();
            
            if(finalName.replaceAll("~", ConstantsUtils.EMPTY).equalsIgnoreCase(projName.replaceAll("~", ConstantsUtils.EMPTY))){
                count++;
            }           
        }
          LOGGER.debug("duplicateCheck method ended ");
         if(count > 0){
             return false;
         }else{
             return true;
         }        
        
    }
}
