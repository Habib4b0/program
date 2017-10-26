package com.stpl.app.global.cfp.dto;

import com.stpl.app.global.cfp.util.UIUtils;
import com.stpl.app.global.common.util.CommonUtil;
import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.util.CommonUIUtils;
import com.stpl.app.util.ConstantsUtils;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 * The Class CFPTableGenerator.
 */
public class CFPTableGenerator extends DefaultFieldFactory {

    
      /**
     * The company details save bean is used to save the company details before performing any activity
     * it will get the object form lazy container while user trying to edit or changing any value in the table and it is useful for saving in temp table
     */
    private final BeanItemContainer<CFPCompanyDTO> companyDetailsResultBean;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(CFPTableGenerator.class);
    private CommonUtil commonUtil = CommonUtil.getInstance();
    SessionDTO sessionDTO;

    public CFPTableGenerator(final BeanItemContainer<CFPCompanyDTO> companyDetailsResultBean, final SessionDTO sessionDTO) {
        this.companyDetailsResultBean = companyDetailsResultBean;
        this.sessionDTO=sessionDTO;
    }

    /**
     * Creates the field based on PropertyId.
     *
     * @param container the container
     * @param itemId the item id
     * @param propertyId the property id
     * @param uiContext the ui context
     * @return the field<?>
     */
    @Override
    public Field<?> createField(final Container container, final Object itemId, final Object propertyId, final Component uiContext) {
     final  CFPCompanyDTO cfpCompanyDTO=(CFPCompanyDTO) itemId;
        if (ConstantsUtils.CHECK_BOX.equals(propertyId)) {
            final CheckBox checkbox = new CheckBox();
            checkbox.setReadOnly(false);
            checkbox.setValue(cfpCompanyDTO.getCheckbox());
            checkbox.addValueChangeListener(new Property.ValueChangeListener() {
                public void valueChange(Property.ValueChangeEvent event) {
                    if (cfpCompanyDTO.getCheckbox() != null ){
                        companyDetailsResultBean.addItem(itemId);
                    }
                }
            });
            return checkbox;
        }

        if (ConstantsUtils.CFP_START_DATE.equals(propertyId)) {
            final PopupDateField cfpStartDate = new PopupDateField();
            cfpStartDate.setDescription(ConstantsUtils.DATE_DES);
            cfpStartDate.setValidationVisible(true);
            cfpStartDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
            cfpStartDate.setImmediate(true);
            cfpStartDate.setRequired(true);
            cfpStartDate.addStyleName(ConstantsUtils.DATE_FIEILD_CENTER);
            cfpStartDate.setRequiredError("Start Date should  be present");
            cfpStartDate.setValue(cfpCompanyDTO.getCompanyFamilyPlanStartDate());
            cfpStartDate.addValueChangeListener(new Property.ValueChangeListener() {
                public void valueChange(Property.ValueChangeEvent event) {
                    if(event.getProperty().getValue() !=null ){
                        cfpCompanyDTO.setCheckFlag(true);
                    companyDetailsResultBean.addItem(itemId);
                    cfpStartDate.setDescription(CommonUIUtils.convert2DigitTo4DigitYear(cfpStartDate.getValue()));
                    }
                }
            });
            return cfpStartDate;
        }
        if (ConstantsUtils.CFP_END_DATE.equals(propertyId)) {

            final PopupDateField cfpEndDate = new PopupDateField();
            cfpEndDate.setDescription(ConstantsUtils.DATE_DES);
            cfpEndDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
            cfpEndDate.addStyleName(ConstantsUtils.DATE_FIEILD_CENTER);
            cfpEndDate.setImmediate(true);
            cfpEndDate.setValue(cfpCompanyDTO.getCompanyFamilyPlanEndDate());
            cfpEndDate.addValueChangeListener(new Property.ValueChangeListener() {
                public void valueChange(Property.ValueChangeEvent event) {
                     if(event.getProperty().getValue() !=null ){
                         cfpCompanyDTO.setCheckFlag(true);
                    companyDetailsResultBean.addItem(itemId);
                    cfpEndDate.setDescription(CommonUIUtils.convert2DigitTo4DigitYear(cfpEndDate.getValue()));
                     }
                }
            });
            return cfpEndDate;
        }
        /*GAL-528*/
        if ("cfpAttachedDate".equals(propertyId)) {
            final DateField cfpAttachedDate = new DateField();
            cfpAttachedDate.setDescription(ConstantsUtils.DATE_DES);
            cfpAttachedDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
            cfpAttachedDate.setImmediate(true);
            cfpAttachedDate.setReadOnly(true);
            
            return cfpAttachedDate;
        }
        if ("modifiedDate".equals(propertyId)) {
            final DateField modifiedDate = new DateField();
            modifiedDate.setDescription(ConstantsUtils.DATE_DES);
            modifiedDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
            modifiedDate.setImmediate(true);
            modifiedDate.setReadOnly(true);
            
            return modifiedDate;
        }
        if ("createdDate".equals(propertyId)) {
            final DateField createdDate = new DateField();
            createdDate.setDescription(ConstantsUtils.DATE_DES);
            createdDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
            createdDate.setImmediate(true);
            createdDate.setReadOnly(true);
            
            return createdDate;
        }
        /*GAL-528*/
        if("tradingPartnerContractNo".equals(propertyId)){
            final TextField tradingPartnerContractNo = new TextField();
                tradingPartnerContractNo.setImmediate(true);
                tradingPartnerContractNo.setValue(cfpCompanyDTO.getTradingPartnerContractNo());
                tradingPartnerContractNo.addValueChangeListener(new Property.ValueChangeListener() {
                    public void valueChange(Property.ValueChangeEvent event) {
                        if (event.getProperty().getValue() != null) {
                            cfpCompanyDTO.setCheckFlag(true);
                            companyDetailsResultBean.addItem(itemId);
                        }
                    }
                });
                

                return tradingPartnerContractNo;
        }
        if (ConstantsUtils.CFP_STATUS.equals(propertyId)) {

            final ComboBox cfpStatus = new ComboBox();
         
         try {
             commonUtil.loadComboBox(cfpStatus, UIUtils.CFP_STATUS, false);
              } catch (Exception ex) {
             LOGGER.error(ex);
         }
       
            cfpStatus.addValueChangeListener(new Property.ValueChangeListener() {
                public void valueChange(Property.ValueChangeEvent event) {
                    if (event.getProperty().getValue() != null && StringUtils.EMPTY.equals(event.getProperty().getValue().toString())) {
                        cfpStatus.setValue(ConstantsUtils.SELECT_ONE);
                    } else if (event.getProperty().getValue() != null ){
                        cfpCompanyDTO.setCheckFlag(true);
                        companyDetailsResultBean.addItem(itemId);
                    }
                }
            });
            return cfpStatus;
        }
        
        if ("modifiedBy".equals(propertyId)) {
            final String isSaved = String.valueOf(sessionDTO.getIsSave());
            final TextField modifiedBy = new TextField();
            if ("N".equals(isSaved)) {
                cfpCompanyDTO.setModifiedBy(StringUtils.EMPTY);
                modifiedBy.setValue(StringUtils.EMPTY);
            } else {
                modifiedBy.setValue(cfpCompanyDTO.getModifiedBy());
            }
            modifiedBy.setReadOnly(true);
            return modifiedBy;
        }
        final Field field = super.createField(container, itemId, propertyId, uiContext);
        field.setReadOnly(true);
        // Otherwise use the default field factory
        return field;
    }
}
