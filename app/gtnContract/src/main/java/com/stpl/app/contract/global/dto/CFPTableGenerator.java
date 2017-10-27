package com.stpl.app.contract.global.dto;

import com.stpl.app.contract.common.dto.SessionDTO;
import com.stpl.app.contract.global.logic.CFPSearchLogic;
import com.stpl.app.contract.global.logic.RebateScheduleLogic;
import com.stpl.app.contract.global.util.CommonUtils;
import com.stpl.app.contract.util.AbstractNotificationUtils;
import com.stpl.app.contract.util.CommonUIUtils;
import com.stpl.app.contract.util.Constants;
import com.stpl.app.contract.util.QueryUtil;
import com.stpl.app.serviceUtils.ConstantsUtils;
import com.stpl.ifs.ui.CustomePagedFilterTable;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.PopupDateField;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 * The Class CFPTableGenerator.
 */
public class CFPTableGenerator extends DefaultFieldFactory {
    /**
     * The rebate schedule logic.
     */
    private final RebateScheduleLogic rebateScheduleLogic = new RebateScheduleLogic();
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(CFPTableGenerator.class);
    
    private BeanItemContainer<CFPCompanyDTO> saveContainer;
    
    CustomePagedFilterTable table;
    
    Object[] dates;

    SimpleDateFormat format = new SimpleDateFormat("MM/dd/YYYY");
    
    Map<String,List> tempDate;

    List<Date> tempDateList = new ArrayList<>();
    
    
    private CFPSearchLogic cfpLogic;
     
    private String userId;
    
    private String sessionId;
    
    List checkUpdate=new ArrayList();
    
    List checkSelect=new ArrayList();
    
    SessionDTO sessionDTO;
    
    private int check;
    
    public CFPTableGenerator(BeanItemContainer<CFPCompanyDTO> saveContainer,final Object[] dates, Map<String,List> tempDate, CustomePagedFilterTable table, SessionDTO sessionDTO){
        this.saveContainer=saveContainer;
        this.dates = dates;
        this.tempDate = tempDate;
        this.table=table;
        this.sessionDTO=sessionDTO;
    }
    /**
     * Creates the field based on propertyId.
     *
     * @param container the container
     * @param itemId the item id
     * @param propertyId the property id
     * @param uiContext the ui context
     * @return the field
     */
    public Field<?> createField(final Container container, final Object itemId, final Object propertyId, final Component uiContext) {
        final CFPCompanyDTO temp=(CFPCompanyDTO)itemId;
        
        tempDateList.add(temp.getCompanyFamilyPlanStartDate());
        tempDateList.add(temp.getCompanyFamilyPlanEndDate());
        tempDate.put(temp.getCompanyId(), tempDateList);
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        sessionId =sessionDTO.getUiSessionId();
        
        if (Constants.CHECK_BOX.equals(propertyId)) {
            final CheckBox checkbox = new CheckBox();
            checkbox.setValue(temp.getCheckbox());
            checkbox.setReadOnly(false);
            checkbox.setId("contractdashboardcheckbox");
            
            
            checkbox.addValueChangeListener(new Property.ValueChangeListener() {

                @Override
                public void valueChange(ValueChangeEvent event) {
                    if (temp.getCheckbox() != null) {
                       saveContainer.addItem(itemId);
                       check=temp.getCheckbox()?1:0;
                        checkUpdate=new ArrayList();
                        

                        checkUpdate.add(check);
                        checkUpdate.add(sessionId);
                        checkUpdate.add(userId);
                        checkUpdate.add(temp.getCompanySystemId());

                        QueryUtil.updateAppData(checkUpdate, "CFPCheckUpdate");

                        checkUpdate = new ArrayList();

                        checkUpdate.add(sessionId);
                        checkUpdate.add(userId);
                        
                        checkSelect=new ArrayList();

                        checkSelect = QueryUtil.getAppData(checkUpdate,"CFPCheckSelect", null);

                        if (checkSelect.size() == 0) {
                            table.setCurrentPage(table.getCurrentPage());
                            table.setColumnCheckBox(ConstantsUtils.CHECK_BOX, true, true);
                        } else {
                            table.setCurrentPage(table.getCurrentPage());
                            table.setColumnCheckBox(ConstantsUtils.CHECK_BOX, true, false);
                        }

                    }
                }
            });
            return checkbox;
        }

        if (Constants.COMPANY_FAMILY_PLAN_START_DATE.equals(propertyId)) {

            final PopupDateField companyFamilyPlanStartDate = new PopupDateField();
            companyFamilyPlanStartDate.setRequired(true);
            companyFamilyPlanStartDate.setImmediate(true);
            companyFamilyPlanStartDate.setDateFormat(Constants.MM_DD_YYYY);
            companyFamilyPlanStartDate.setRequiredError("Start Date should  be present");
            companyFamilyPlanStartDate.setDescription(Constants.DATE);
            companyFamilyPlanStartDate.setValue(temp.getCompanyFamilyPlanStartDate());
            attachListeners(companyFamilyPlanStartDate, Constants.COMPANY_FAMILY_PLAN_START_DATE, itemId, temp);
            return companyFamilyPlanStartDate;
        }
        if (Constants.CFP_END_DATE.equals(propertyId)) {
            final PopupDateField companyFamilyPlanEndDate = new PopupDateField();
            companyFamilyPlanEndDate.setDateFormat(Constants.MM_DD_YYYY);
            companyFamilyPlanEndDate.setImmediate(true);
            companyFamilyPlanEndDate.setDescription(Constants.DATE);
            companyFamilyPlanEndDate.setValue(temp.getCompanyFamilyPlanEndDate());
            attachListeners(companyFamilyPlanEndDate,Constants.CFP_END_DATE,itemId,temp);
            return companyFamilyPlanEndDate;
        }
        if (Constants.CFP_STATUS.equals(propertyId)) {

            final ComboBox companyFamilyPlanStatus = new ComboBox();
             companyFamilyPlanStatus.setContainerDataSource(new IndexedContainer(rebateScheduleLogic.getHelperDetailsDesc(CommonUtils.STATUS)));
            companyFamilyPlanStatus.setNullSelectionAllowed(true);
            companyFamilyPlanStatus.setNullSelectionItemId(Constants.SELECT_ONE);
            if(StringUtils.isEmpty(temp.getCompanyFamilyPlanStatus())|| Constants.SELECT_ONE.equals(temp.getCompanyFamilyPlanStatus())){
                companyFamilyPlanStatus.setValue(Constants.SELECT_ONE);
            }else{
                companyFamilyPlanStatus.setValue(temp.getCompanyFamilyPlanStatus());
            }
            companyFamilyPlanStatus.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * Will execute ,when the value of companyFamilyPlanEndDate's
                 * value changed.
                 */
                public void valueChange(final ValueChangeEvent event) {
                    if(StringUtils.EMPTY.equals(temp.getCompanyFamilyPlanStatus())){
                        companyFamilyPlanStatus.setValue(Constants.SELECT_ONE);
                    }else{
                        companyFamilyPlanStatus.setValue(temp.getCompanyFamilyPlanStatus());
                        saveContainer.addItem(itemId);
                    }
                   
                }
            });
            return companyFamilyPlanStatus;
        }
        return null;
    }

    public void attachListeners(final AbstractField field, final String component, final Object itemId, final CFPCompanyDTO temp) {
        field.setImmediate(true);
       
        field.addValueChangeListener(new ValueChangeListener() {
            @Override
            public void valueChange(ValueChangeEvent event) {
                try {
                  
                    if (Constants.COMPANY_FAMILY_PLAN_START_DATE.equals(component)) {
                        tempDateList = tempDate.get(temp.getCompanyId());
                        if (((PopupDateField) field).getValue() != null && ((PopupDateField) field).getValue().before((Date) dates[0])) {
                            AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, "CFP Start date cannot be before " + format.format(dates[0]));
                            detachListeners(field);
                            ((PopupDateField) field).setValue(tempDateList.get(0));
                            attachListeners(field, component, itemId, temp);
                            return;
                        } else if (((PopupDateField) field).getValue() != null && (Date) dates[1] != null && ((PopupDateField) field).getValue().after((Date) dates[1])) {
                            AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, "CFP Start date cannot be after " + format.format(dates[1]));
                            detachListeners(field);
                            ((PopupDateField) field).setValue(tempDateList.get(0));
                            attachListeners(field, component, itemId, temp);
                            return;
                        } else {
                            temp.setCompanyFamilyPlanStartDate(((PopupDateField) field).getValue());
                            saveContainer.addItem(itemId);
                            field.setDescription(CommonUIUtils.convert2DigitTo4DigitYear(((PopupDateField) field).getValue()));
                        }
                    } else if (Constants.CFP_END_DATE.equals(component)) {
                        tempDateList = tempDate.get(temp.getCompanyId());
                        if (((PopupDateField) field).getValue() != null && (Date) dates[1] != null && ((PopupDateField) field).getValue().after((Date) dates[1])) {
                            detachListeners(field);
                            ((PopupDateField) field).setValue(tempDateList.get(1) != null ? tempDateList.get(1) : null);
                            AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, "CFP end date cannot be after " + format.format(dates[1]));
                            attachListeners(field, component, itemId, temp);
                            return;
                        } else if (((PopupDateField) field).getValue() != null && ((PopupDateField) field).getValue().before((Date) dates[0])) {
                            detachListeners(field);
                            ((PopupDateField) field).setValue(tempDateList.get(1) != null ? tempDateList.get(1) : null);
                            AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, "CFP end date cannot be before " + format.format(dates[0]));
                            attachListeners(field, component, itemId, temp);
                            return;
                        } else {
                            saveContainer.addItem(itemId);
                            ((PopupDateField) field).setDescription(CommonUIUtils.convert2DigitTo4DigitYear(((PopupDateField) field).getValue()));
                        }
                    }

                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            }
        });
    }

    public void detachListeners(final AbstractField field) {
        List<Property.ValueChangeListener> listeners;
        listeners = (List<Property.ValueChangeListener>) field.getListeners(Property.ValueChangeEvent.class);
        for (final Property.ValueChangeListener object : listeners) {

            field.removeValueChangeListener(object);
        }

    }
    

}
