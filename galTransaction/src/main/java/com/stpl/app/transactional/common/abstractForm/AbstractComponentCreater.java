/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.transactional.common.abstractForm;

import com.stpl.app.transactional.common.dto.DetailsDTO;
import com.stpl.app.security.impl.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.transactional.common.logic.CommonLogic;
import com.stpl.app.util.ConstantUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;

/**
 *
 * @author sooriya.lakshmanan
 */
public abstract class AbstractComponentCreater extends CustomComponent {
   
    /**
     * the Search List
     */
    List<List> searchList = new ArrayList<List>();
    List<String> headerList = new ArrayList<String>();
    List<Object> columnList = new ArrayList<Object>();
    List<DetailsDTO> moduleDetailsValue = null;
    List<DetailsDTO> primaryValueList = null;
    @UiField("cssLayout")
    protected CssLayout cssLayout;
    @UiField("buttonLayout")
    protected VerticalLayout buttonLayout;
    Object[] selectedValues = null;
    int count = 0;
    static String tableValue1 = null;
    static boolean adjustDemand = false;
    static Set headerListInventory = null;
    String invalidTable= String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.INVALID_TABLE_NAME));
    public List<String> HEAD_LIST =Arrays.asList("Inventory Unit Change","Uncaptured Units","Uncaptured Units Ratio","Forecast Name","Forecast Version");
    public List<String> HEAD_LIST_ACCURAL =Arrays.asList("Company Qualifier");
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(AbstractComponentCreater.class);
    Object[] ob = null;

    public AbstractComponentCreater(final Object[] ob, Object[] selectedValues) throws Exception {
        this.ob = ob;
        this.selectedValues = selectedValues;
        moduleDetailsValue = (List<DetailsDTO>) ob[0];
        primaryValueList = (List<DetailsDTO>) ob[1];
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/ui/common/viewLayout.xml"), this));
        init();
    }

    private void init() throws Exception {
        count = 0;
        getDynamicComponent();
    }

    private void getDynamicComponent() throws PortalException, Exception {
        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.USERID));
        Map<String, AppPermission> fieldForecastSalesHM = new HashMap<String, AppPermission>();
        Map<String, AppPermission> functionForecastSalesHM = new HashMap<String, AppPermission>();
        if (!String.valueOf(VaadinSession.getCurrent().getAttribute("modName")).equalsIgnoreCase("InvalidRecordCount")) {
            if (primaryValueList.get(0).getValidation().equalsIgnoreCase("Actual Master")) {
                fieldForecastSalesHM = stplSecurity.getBusinessFieldPermission(userId, primaryValueList.get(0).getValidation() + "," + ob[2].toString());
            } else {
                fieldForecastSalesHM = stplSecurity.getBusinessFieldPermission(userId, primaryValueList.get(0).getValidation() + "," + primaryValueList.get(0).getValidation());
                if (primaryValueList.get(0).getValidation().equals("AccrualMaster")) {
                    fieldForecastSalesHM = stplSecurity.getBusinessFieldPermission(userId, "AccrualMaster");
                }
            }
            functionForecastSalesHM = stplSecurity.getBusinessFunctionPermission(userId, primaryValueList.get(0).getValidation());
        } else {
            fieldForecastSalesHM = stplSecurity.getBusinessFieldPermission(userId, (String) VaadinSession.getCurrent().getAttribute(ConstantUtil.INVALID_TABLE_NAME1));
            functionForecastSalesHM = stplSecurity.getBusinessFunctionPermission(userId, (String) VaadinSession.getCurrent().getAttribute(ConstantUtil.INVALID_TABLE_NAME1));
        }
        for (final DetailsDTO moduleProperty : moduleDetailsValue) {
             boolean createFlag=false;
             if((moduleProperty.getTableName().equalsIgnoreCase(ConstantUtil.DEMAND_VIEW) && adjustDemand == false) || (adjustDemand == true)){
            for(String s:HEAD_LIST){
                    if(s.equals(moduleProperty.getDisplayName())){
                        createFlag=true;
                    }
                }
            }
            if((moduleProperty.getTableName().equalsIgnoreCase(ConstantUtil.INVENTORYVIEW_TABLE_IN_VIEW) && headerListInventory !=null) || (invalidTable.equalsIgnoreCase(ConstantUtil.INVALID_INVENTORYVIEW_TABLE_IN_VIEW) && headerListInventory !=null)){
                for(Object s:headerListInventory){
                    if(String.valueOf(s).equals(moduleProperty.getDisplayName())){
                       
                        createFlag=true;
                    }
                }
            }
            if((invalidTable.equalsIgnoreCase(ConstantUtil.INVALID_ACCRUAL))){
                for(Object s:HEAD_LIST_ACCURAL){
                    if(String.valueOf(s).equals(moduleProperty.getDisplayName())){
                       
                        createFlag=true;
                    }
                }
            }
            if (ConstantUtil.COLUMN.equals(moduleProperty.getCategoryName()) && (((AppPermission) fieldForecastSalesHM.get(moduleProperty.getPropertyName()) == null) ? false
                    : ((AppPermission) fieldForecastSalesHM.get(moduleProperty.getPropertyName())).isViewFlag())) {
                headerList.add(moduleProperty.getDisplayName());
                columnList.add(moduleProperty.getPropertyName());
            } else if (ConstantUtil.Button.equals(moduleProperty.getCategoryName()) && ((((AppPermission) functionForecastSalesHM.get(moduleProperty.getPropertyName())) == null) ? false
                    : ((AppPermission) functionForecastSalesHM.get(moduleProperty.getPropertyName())).isFunctionFlag())) {
                buttonLayout.addComponent(new Button() {
                    {
                        addClickListener(new Button.ClickListener() {

                            public void buttonClick(Button.ClickEvent event) {
                                try {
                                    clickListeners(event);
                                } catch (Exception ex) {
                                    LOGGER.error(ex);
                                }
                            }
                        });
                        setCaption(moduleProperty.getDisplayName());
                    }
                });
            } else { 
                if ((ConstantUtil.ACTUALS.equals(tableValue1) && createFlag==true && adjustDemand==false) || ((moduleProperty.getTableName().equalsIgnoreCase(ConstantUtil.INVENTORYVIEW_TABLE_IN_VIEW)&& createFlag==true )
                        || ( invalidTable.equalsIgnoreCase(ConstantUtil.INVALID_INVENTORYVIEW_TABLE_IN_VIEW)&& createFlag==true )) 
                        || (ConstantUtil.ACTUALS.equals(tableValue1) && createFlag==true && adjustDemand==true ) || (invalidTable.equalsIgnoreCase(ConstantUtil.INVALID_ACCRUAL) && createFlag==true)) {
                    createFlag=false;
                    if((ConstantUtil.ACTUALS.equals(tableValue1) && adjustDemand==true ) && moduleProperty.getDisplayName().equals("Forecast Version") ){
                     count++;
                    }
                    if((moduleProperty.getTableName().equalsIgnoreCase(ConstantUtil.INVENTORYVIEW_TABLE_IN_VIEW)) || (invalidTable.equalsIgnoreCase(ConstantUtil.INVALID_INVENTORYVIEW_TABLE_IN_VIEW))){
                     count++;
                    }
                } else{
                     if (((AppPermission) fieldForecastSalesHM.get(moduleProperty.getPropertyName()) == null) ? false
                            : ((AppPermission) fieldForecastSalesHM.get(moduleProperty.getPropertyName())).isViewFlag()) {
                        if (selectedValues.length == 0) {
                            addComponentInCsssLayout(cssLayout, new Label() {
                                {
                                    setValue(moduleProperty.getDisplayName());
                                }
                            }, componentCreater(moduleProperty, null), true);
                        } else {
                            addComponentInCsssLayout(cssLayout, new Label() {
                                {
                                    setValue(moduleProperty.getDisplayName());
                                }
                            }, componentCreater(moduleProperty, selectedValues[count++]), true);
                        }
                    } else {
                        count++;
                    }
                }
            }
        }
        searchList.add(headerList);
        searchList.add(columnList);
    }

    public static void addComponentInCsssLayout(CssLayout layout, final Object labelComponent, final Component fieldComponent, final boolean appPermission) throws Exception {
        if (appPermission && fieldComponent != null) {
            if (labelComponent != null && labelComponent instanceof Label) {
                layout.addComponent((Label) labelComponent);
                layout.addComponent(fieldComponent);
            }
            if (labelComponent instanceof String) {
                fieldComponent.setStyleName("ActualLabel");
                fieldComponent.setCaption(labelComponent.toString());
                layout.addComponent(fieldComponent);
            }
        }
    }

    public void singleFieldSet(final Component labelComponent, final Component fieldComponent) {
        if (fieldComponent != null) {
            cssLayout.addComponent(labelComponent);
            cssLayout.addComponent(fieldComponent);
        }
    }

    private Component componentCreater(final DetailsDTO moduleProperty, final Object value) throws Exception {

        if (ConstantUtil.TextField.equals(moduleProperty.getCategoryName())) {
            return new TextField() {
                {
                    setData(moduleProperty.getPropertyName());
                    if(moduleProperty.getTableName().equals("CPI_INDEX_MASTER") && moduleProperty.getPropertyName().equals("STATUS")){
                        setValue(CommonLogic.getDescription(Integer.valueOf(value.toString())));
                    }else if( value!=null && moduleProperty.getTableName().equals("VW_CUSTOMER_GTS_FORECAST")&&(moduleProperty.getPropertyName().equals("DEDUCTION_CATEGORY")|| moduleProperty.getPropertyName().equals("DEDUCTION_PROGRAM_TYPE")
                          ||moduleProperty.getPropertyName().equals("DEDUCTION_TYPE") ||
                            (moduleProperty.getPropertyName().equals("UDC1") && !invalidTable.equals("IVLD_CUSTOMER_GTS_FORECAST"))
                            ||(moduleProperty.getPropertyName().equals("UDC2")&& !invalidTable.equals("IVLD_CUSTOMER_GTS_FORECAST")) 
                            ||(moduleProperty.getPropertyName().equals("UDC3") && !invalidTable.equals("IVLD_CUSTOMER_GTS_FORECAST"))
                            ||(moduleProperty.getPropertyName().equals("UDC4") && !invalidTable.equals("IVLD_CUSTOMER_GTS_FORECAST"))
                            ||(moduleProperty.getPropertyName().equals("UDC5")&& !invalidTable.equals("IVLD_CUSTOMER_GTS_FORECAST")) 
                            ||(moduleProperty.getPropertyName().equals("UDC6")&& !invalidTable.equals("IVLD_CUSTOMER_GTS_FORECAST") ))){
                        if(!"0".equals(String.valueOf(value))){
                       setValue(CommonLogic.getDescription(Integer.valueOf(value.toString())));
                        }else{
                           setValue(StringUtils.EMPTY) ;
                        }
                    }else if(moduleProperty.getTableName().trim().equals("GL_BALANCE_MASTER") && moduleProperty.getPropertyName().trim().equals("IS_ACTIVE")){
                        if(Boolean.valueOf(String.valueOf(value))){
                            setValue("Yes");
                        }else{
                            setValue("No");
                        }
                    }else {
                        setValue(value == null ? StringUtils.EMPTY : value.toString());
                    }
                    setReadOnly(true);
                }
            };
        } else if (ConstantUtil.PopupDateField.equals(moduleProperty.getCategoryName())) {
            return new PopupDateField() {
                {

                    SimpleDateFormat sdf1 = new SimpleDateFormat(ConstantUtil.MMDDYYYY);
                    setData(moduleProperty.getPropertyName());
                    setDateFormat(ConstantUtil.MMDDYYYY);
                    if (value != null && !String.valueOf(value).equals("null") && !String.valueOf(value).isEmpty()) {
                        setValue(sdf1.parse(sdf1.format(parseDate(value.toString()))));
                        setReadOnly(true);
                    }
                   setReadOnly(true); 
                }
            };
        }
        setReadOnly(true);
        return null;
    }

    protected abstract void clickListeners(Button.ClickEvent event) throws Exception;

    Date parseDate(String d) {
        String[] formats = {
          "dd-MMM-yy","yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd",
            "yyyy-MM-dd HH:mm:ss.S", "MM/dd/yyyy HH:mm:ss", "dd/MM/yyyy", "MMM dd yyyy hh:mm" ,"dd/MM/yy",
            "dd/MM/yyyy","d/M/yy","d/M/yyyy","ddMMyy","ddMMyyyy","ddMMMyy","ddMMMyyyy","dd-MMM-yyyy","dMMMyy",
            "dMMMyyyy","d-MMM-yy","d-MMM-yyyy","d-MMMM-yy","d-MMMM-yyyy","yyMMdd","yyyyMMdd","yy/MM/dd",
            "yyyy/MM/dd","MMddyy","MMddyyyy","MM/dd/yy","MM/dd/yyyy","MMM-dd-yy","MMM-dd-yyyy","yyyy-MM-dd","MMM dd yyyy hh:mm aaa"};
        
        if (d != null) {
            for (String parse : formats) {
                SimpleDateFormat sdf = new SimpleDateFormat(parse);
                try {
                    sdf.setLenient(false);
                    return sdf.parse(d);
                } catch (ParseException e) {
                     continue;
                }
            }
        }
        return null;
    }
    
    static void setTableValue(final String tableValue) {
        tableValue1 = tableValue;
    }
    public static void setAdjustDemand(final boolean adjustDemand1) {
        adjustDemand = adjustDemand1;
    }
    public static void setHeaderList(final Set tableValue) {
        headerListInventory = tableValue;
    }
}
          
