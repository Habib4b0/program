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
import com.stpl.app.transactional.common.logic.SearchLogic;
import com.stpl.app.util.ConstantUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
    List<List> searchList = new ArrayList<>();
    List<String> headerList = new ArrayList<>();
    List<Object> columnList = new ArrayList<>();
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
    String invalidTable = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.INVALID_TABLE_NAME));
    public List<String> HEAD_LIST = Arrays.asList("Inventory Unit Change", "Uncaptured Units", "Uncaptured Units Ratio", "Forecast Name", "Forecast Version");
    public List<String> HEAD_LIST_ACCURAL = Arrays.asList("Company Qualifier");
    DecimalFormat decimalformatdollar = new DecimalFormat("$#,###0.00");
    DecimalFormat decimalformatper = new DecimalFormat("#0.00");
    DecimalFormat alphanumeric = new DecimalFormat("#,###");
    private DecimalFormat format2 = new DecimalFormat();
    private SearchLogic searchLogic=new SearchLogic();
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(AbstractComponentCreater.class);
    Object[] ob = null;

    public AbstractComponentCreater(final Object[] ob, Object[] selectedValues) throws PortalException, SystemException, ParseException {
        this.ob = ob;
        this.selectedValues = selectedValues;
        moduleDetailsValue = (List<DetailsDTO>) ob[0];
        primaryValueList = (List<DetailsDTO>) ob[NumericConstants.ONE];
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/ui/common/viewLayout.xml"), this));
        init();
    }

    private void init() throws PortalException, SystemException, ParseException {
        count = 0;
        getDynamicComponent();
    }

     private void getDynamicComponent() throws PortalException, SystemException, ParseException {
        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.USERID));
        Map<String, AppPermission> fieldForecastSalesHM;
        Map<String, AppPermission> functionForecastSalesHM;
        if (!String.valueOf(VaadinSession.getCurrent().getAttribute("modName")).equalsIgnoreCase("InvalidRecordCount")) {
            if (primaryValueList.get(0).getValidation().equalsIgnoreCase("Actual Master")) {
                fieldForecastSalesHM = stplSecurity.getBusinessFieldPermission(userId, primaryValueList.get(0).getValidation() + "," + ob[NumericConstants.TWO].toString());
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
            if(invalidTable.equalsIgnoreCase(ConstantUtil.INVALID_ACCRUAL)){
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
            } else if (ConstantUtil.BUTTON.equals(moduleProperty.getCategoryName()) && ((((AppPermission) functionForecastSalesHM.get(moduleProperty.getPropertyName())) == null) ? false
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

    public static void addComponentInCsssLayout(CssLayout layout, final Object labelComponent, final Component fieldComponent, final boolean appPermission) {
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

    private Component componentCreater(final DetailsDTO moduleProperty, final Object value) throws ParseException  {
        if (ConstantUtil.TEXT_FIELD.equals(moduleProperty.getCategoryName())) {
            return new TextField() {
                {
                    setData(moduleProperty.getPropertyName());
                    if (moduleProperty.getTableName().equals("CPI_INDEX_MASTER") && moduleProperty.getPropertyName().equals("STATUS")) {
                        setValue(CommonLogic.getDescription(Integer.valueOf(value.toString())));
                    } else if (value != null && moduleProperty.getTableName().equals("VW_CUSTOMER_GTS_FORECAST") && (moduleProperty.getPropertyName().equals("DEDUCTION_CATEGORY") || moduleProperty.getPropertyName().equals("DEDUCTION_PROGRAM_TYPE")
                            || moduleProperty.getPropertyName().equals("DEDUCTION_TYPE")
                            || (moduleProperty.getPropertyName().equals("UDC1") && !invalidTable.equals(ConstantUtil.IVLD_CUSTOMER_GTS_FORECAST))
                            || (moduleProperty.getPropertyName().equals("UDC2") && !invalidTable.equals(ConstantUtil.IVLD_CUSTOMER_GTS_FORECAST))
                            || (moduleProperty.getPropertyName().equals("UDC3") && !invalidTable.equals(ConstantUtil.IVLD_CUSTOMER_GTS_FORECAST))
                            || (moduleProperty.getPropertyName().equals("UDC4") && !invalidTable.equals(ConstantUtil.IVLD_CUSTOMER_GTS_FORECAST))
                            || (moduleProperty.getPropertyName().equals("UDC5") && !invalidTable.equals(ConstantUtil.IVLD_CUSTOMER_GTS_FORECAST))
                            || (moduleProperty.getPropertyName().equals("UDC6") && !invalidTable.equals(ConstantUtil.IVLD_CUSTOMER_GTS_FORECAST)))) {
                        if (!"0".equals(String.valueOf(value))) {
                            setValue(CommonLogic.getDescription(Integer.valueOf(value.toString())));
                        } else {
                            setValue(StringUtils.EMPTY);
                        }
                    } else if (moduleProperty.getTableName().trim().equals("GL_BALANCE_MASTER") && moduleProperty.getPropertyName().trim().equals("IS_ACTIVE")) {
                        if (Boolean.valueOf(String.valueOf(value))) {
                            setValue("Yes");
                        } else {
                            setValue("No");
                        }
                    } else if ((moduleProperty.getTableName().trim().equals(ConstantUtil.VW_ITEM_MASTER_CAPS) || (moduleProperty.getTableName().trim().equals("IVLD_ITEM_MASTER"))) && (moduleProperty.getPropertyName().trim().equals("ACQUIRED_AMP")
                            || moduleProperty.getPropertyName().trim().equals("ACQUIRED_BAMP") || moduleProperty.getPropertyName().trim().equals("OBRA_BAMP")
                            || moduleProperty.getPropertyName().trim().equals("BASELINE_AMP"))) {

                        if (moduleProperty.getPropertyName().trim().equals("BASELINE_AMP")) {
                            String baselineAmp = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.BASELINE_AMP_PRECISION));
                            format2.applyPattern(searchLogic.pattern(Integer.valueOf(baselineAmp)));
                            setValue(value == null ? StringUtils.EMPTY : "$" + format2.format(Double.valueOf(value.toString())));
                        } else {
                            setValue(value == null ? StringUtils.EMPTY : String.valueOf(decimalformatdollar.format(Double.valueOf(value.toString()))));
                        }
                        addStyleName(ValoTheme.DATEFIELD_ALIGN_RIGHT);

                    } else if ((moduleProperty.getTableName().trim().equals(ConstantUtil.VW_ITEM_MASTER_CAPS) || (moduleProperty.getTableName().trim().equals("IVLD_ITEM_MASTER"))) && moduleProperty.getPropertyName().trim().equals("BASE_CPI")) {

                        if (moduleProperty.getPropertyName().trim().equals("BASE_CPI")) {
                            String baselineCpi = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.BASE_CPI_PRECISION));
                            format2.applyPattern(searchLogic.pattern(Integer.valueOf(baselineCpi)));
                            setValue(value == null ? StringUtils.EMPTY : String.valueOf(format2.format(Double.valueOf(value.toString()))));
                        } else {
                            setValue(value == null ? StringUtils.EMPTY : String.valueOf(decimalformatper.format(Double.valueOf(value.toString()))));
                        }
                        addStyleName(ValoTheme.DATEFIELD_ALIGN_RIGHT);
                    } else if ((moduleProperty.getTableName().trim().equals("VW_ITEM_PRICING") || (moduleProperty.getTableName().trim().equals("IVLD_ITEM_PRICING"))) && moduleProperty.getPropertyName().trim().equals("ITEM_PRICE")) {
                        String itemPrice =String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.ITEM_PRICE_PRECISION));
                        format2.applyPattern(searchLogic.pattern(Integer.valueOf(itemPrice)));
                        setValue(String.valueOf("$"+format2.format(Double.valueOf(value.toString()))));
                        addStyleName(ValoTheme.DATEFIELD_ALIGN_RIGHT);
                    } else if ((moduleProperty.getTableName().trim().equals(ConstantUtil.VW_ITEM_MASTER_CAPS)) && moduleProperty.getPropertyName().trim().equals("UPPS") || moduleProperty.getPropertyName().trim().equals("DRA")) {
                        setValue(value == null ? StringUtils.EMPTY : String.valueOf(alphanumeric.format(Double.valueOf(value.toString()))));
                        addStyleName("align-left");
                    } else if (!String.valueOf(value).isEmpty() && String.valueOf(value).matches("^[0-9]*$") && (moduleProperty.getPropertyName().trim().equals("CREATED_BY") || moduleProperty.getPropertyName().trim().equals("MODIFIED_BY"))) {
                        String createdByVal = StplSecurity.userMap.get(Long.valueOf(String.valueOf(value)).intValue());
                        setValue(createdByVal != null ? createdByVal : StringUtils.EMPTY);
                    } else {
                        setValue(value == null ? StringUtils.EMPTY : value.toString());

                    }
                    setReadOnly(true);
                }
            };
        } else if (ConstantUtil.POP_UP_DATE_FIELD.equals(moduleProperty.getCategoryName())) {
            return new PopupDateField() {
                {

                    SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
                    setData(moduleProperty.getPropertyName());
                    setDateFormat(ConstantUtil.MMDDYYYY);
                    addStyleName(ValoTheme.DATEFIELD_ALIGN_CENTER);
                    if (value != null && !String.valueOf(value).equals("null") && !String.valueOf(value).isEmpty()) {
                        setValue(sdf1.parse(sdf1.format(parseDate(String.valueOf(value)))));
                        setReadOnly(true);
                    }
                    setReadOnly(true);
                }
            };
        }
        setReadOnly(true);
        return null;
    }

    protected abstract void clickListeners(Button.ClickEvent event);

    Date parseDate(String d) {
        String[] formats = {
            "dd-MMM-yy", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd", "MM-dd-yyyy",
            "yyyy-MM-dd HH:mm:ss.S", "MM/dd/yyyy HH:mm:ss", "dd/MM/yyyy", "MMM dd yyyy hh:mm", "dd/MM/yy",
            "dd/MM/yyyy", "d/M/yy", "d/M/yyyy", "ddMMyy", "ddMMyyyy", "ddMMMyy", "ddMMMyyyy", "dd-MMM-yyyy", "dMMMyy",
            "dMMMyyyy", "d-MMM-yy", "d-MMM-yyyy", "d-MMMM-yy", "d-MMMM-yyyy", "yyMMdd", "yyyyMMdd", "yy/MM/dd",
            "yyyy/MM/dd", "MMddyy", "MMddyyyy", "MM/dd/yy", "MM/dd/yyyy", "MMM-dd-yy", "MMM-dd-yyyy", "yyyy-MM-dd", "MMM dd yyyy hh:mm aaa", "dd", "yyyy", "MM"};

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
