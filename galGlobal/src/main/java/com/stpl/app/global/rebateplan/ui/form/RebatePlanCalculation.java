/**
 *
 */
package com.stpl.app.global.rebateplan.ui.form;

import com.stpl.app.global.abstractsearch.util.CommonUtils;
import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.global.common.ui.lookup.FormulaLookup;
import com.stpl.app.global.common.util.CommonUtil;
import com.stpl.app.global.common.util.HelperListUtil;
import com.stpl.app.global.ifp.logic.IFPLogic;
import com.stpl.app.global.netsalesformula.dto.NetSalesRuleLookupDto;
import com.stpl.app.global.netsalesformula.ui.form.NetSalesRuleLookUp;
import com.stpl.app.global.rebateplan.dto.RebatePlanMasterDTO;
import com.stpl.app.global.rebateplan.dto.RebatePlanTierResults;
import com.stpl.app.global.rebateplan.logic.RebatePlanSearchLogic;
import com.stpl.app.global.rebateschedule.dto.NetSalesFormulaDTO;
import com.stpl.app.global.rebateschedule.dto.RSFormulaDTO;
import com.stpl.app.global.rebateschedule.dto.RebatePlanDTO;
import com.stpl.app.global.rebateschedule.ui.lookup.NetSalesFormulaLookup;
import com.stpl.app.global.rebateschedule.ui.lookup.RebatePlanLookup;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.CommonUIUtils;
import com.stpl.app.util.Constants;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.FunctionNameUtil;
import com.stpl.app.util.GeneralCommonUtils;
import com.stpl.app.util.UISecurityUtil;
import com.stpl.ifs.ui.CommonSecurityLogic;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.TableResultCustom;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Validator;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.AbstractValidator;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 * @author sibi
 *
 */
public class RebatePlanCalculation extends CustomComponent {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * The Constant Logger
     */
    private static final Logger LOGGER = Logger.getLogger(RebatePlanCalculation.class);   
       
    /**
     * The Label
     */
    @UiField("tierToleranceLabel")
    private Label tierToleranceLabel;
    /**
     * The Label
     */
    @UiField("fromLabel")
    private Label fromLabel;
    /**
     * The Label
     */
    @UiField("toLabel")
    private Label toLabel;
    /**
     * The Label
     */
    @UiField("valueLabel")
    private Label valueLabel;
    /**
     * The Label
     */
    @UiField("operatorLabel")
    private Label operatorLabel;
    /**
     * The Combo box
     */
    @UiField("rebateBasedOn")
    private ComboBox rebateBasedOn;
    /**
     * The Combo box
     */

    @UiField("rebateStructure")
    private ComboBox rebateStructure;
    /**
     * The Combo box
     */

    @UiField("rebateRangeBasedOn")
    private ComboBox rebateRangeBasedOn;
    
    /**
     * The Combo box
     */

    @UiField("operator")
    private ComboBox tierOperator;  
    
    /**
     * The Text Field
     */

    @UiField("tierTolerance")
    private TextField tierTolerance;
    /**
     * The Text Field
     */

    @UiField("from")
    private TextField tierFrom;
    /**
     * The Text Field
     */

    @UiField("tierTo")
    private TextField tierTo;
//    /**
//     * The Text Field
//     */
//
//    @UiField("value")
//    private TextField tierValue;
    /**
     * The Table
     */
    @UiField("calculationResultsTable")
    private ExtFilterTable resultsTable;
    /**
     * The Button
     */

    @UiField("calculationAdd")
    private Button btnAdd;
       
    @UiField("calculationRemove")
    private Button btnRemove;
        
    @UiField("cssLayout")
    private CssLayout cssLayout;
        
    @UiField("selfGrowthIndicator")
    private TextField selfGrowthIndicator;
    
    @UiField("selfGrowthReference")
    private TextField selfGrowthReference;

    @UiField("marketShareIndicator")
    private TextField marketShareIndicator;

    @UiField("marketShareReference")
    private TextField marketShareReference;

    @UiField("secondaryRebatePlanNo")
    private CustomTextField secondaryRebatePlanNo;    
    
    @UiField("tierFormula")
    private CustomTextField tierFormula;    
        	
    @UiField("selfGrowthFrom")
    private PopupDateField selfGrowthFrom;

    @UiField("selfGrowthTo")
    private PopupDateField selfGrowthTo;

    @UiField("marketShareFrom")
    private PopupDateField marketShareFrom;

    @UiField("marketShareTo")
    private PopupDateField  marketShareTo;
    
    @UiField("netSalesFormulaLabel")
    private Label netSalesFormulaLabel;
    
    @UiField("netSalesFormulaName")
    private CustomTextField netSalesFormulaName;
      
    @UiField("netSalesRule")
    private CustomTextField  netSalesRule;   

    @UiField("cssLayoutMain")
    private VerticalLayout  cssLayoutMain; 
    
    private TextField netSalesFormulaSid = new TextField();    
    
    @UiField("tierLevel")
    private TextField tierLevel;
    
    @UiField("valueDDLB")
    private ComboBox valueDDLB;    

    @UiField("percentvalueDDLB")
    private ComboBox percentvalueDDLB;    
    
    @UiField("selectOne")
    private ComboBox selectOne;    

    @UiField(ConstantsUtils.CALCULATION_RESET)
    Button calculationReset;
    
    @UiField("tierPanel")
    Panel tierPanel;
    
    @UiField("mainLayout")
    HorizontalLayout mainLayout;
   @UiField("tierLayout")
   VerticalLayout tierLayout;
    
   @UiField("rpCalculationLayout")
   VerticalLayout rpCalculationLayout;
    boolean minimized = false;
     boolean extended=false;

        /**
     * The rebate plan logic.
     */
    private RebatePlanSearchLogic rebatePlanLogic = new RebatePlanSearchLogic();  
      
    CommonUtil commonMsg=CommonUtil.getInstance();

    
    private String userId;
    /**
     * The Binder
     */
    ErrorfulFieldGroup binder;
    
    /**
     * The Bean Item Container
     */
    private BeanItemContainer<RebatePlanTierResults> searchResultbeans;
    
    /**
     * The Value of Search Result bean
     */
    private int levelValue = 1;

    CommonUIUtils commonUiUtil = new CommonUIUtils();

    CommonUtils commonUtils = new CommonUtils();
    private final IFPLogic ifpLogic = new IFPLogic();

    private CommonUtil commonUtil = CommonUtil.getInstance();
    SessionDTO sessionDTO;
    String mode;
    CommonSecurityLogic commonSecurityLogic = new CommonSecurityLogic();

    HelperListUtil helperListUtil = HelperListUtil.getInstance();
    /**
     * To check Addition of Duplicate Items
     */
    public BeanItemContainer<RebatePlanTierResults> tempBeansList = new BeanItemContainer<>(RebatePlanTierResults.class);

    NetSalesFormulaLookup netSalesLookup = null;

    HelperDTO helperDTO = new HelperDTO();
    
    private final RebatePlanMasterDTO rebatePlanMasterDTO;       
       
    final DecimalFormat formatter = new DecimalFormat("#.00");
     final DecimalFormat SIX_DIGIT_FORMAT = new DecimalFormat("##0.000000");
    boolean valueChange=true;
    /**
     * The Constructor
     *
     * @param binder
     * @param searchResultbeans
     * @throws Exception
     */
    public RebatePlanCalculation(ErrorfulFieldGroup binder, BeanItemContainer<RebatePlanTierResults> searchResultbeans, final SessionDTO sessionDTO,final RebatePlanMasterDTO rebatePlanMasterDTO) throws Exception {
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/clara/rebateplan/rebate-plan-calculation.xml"), this));
        addScrollBar();
        this.binder = binder;
        this.searchResultbeans = searchResultbeans;
        this.rebatePlanMasterDTO = rebatePlanMasterDTO;
        tempBeansList.addAll(this.searchResultbeans.getItemIds());
        this.sessionDTO = sessionDTO;
        this.mode = this.sessionDTO.getMode();
        final StplSecurity stplSecurity = new StplSecurity();
        userId = String.valueOf(VaadinSession.getCurrent().getAttribute("userId"));

        Map<String, AppPermission> fieldCfpHM = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.REBATE_PLAN + ConstantsUtils.COMMA + ConstantsUtils.CALCULATIONS, false);
        if (ConstantsUtils.EDIT.equals(this.sessionDTO.getMode())
                || ConstantsUtils.VIEW.equals(this.sessionDTO.getMode())||sessionDTO.getMode().equalsIgnoreCase(ConstantsUtils.COPY)) {
            configureFields();
            getBinder();
            validateFields();
            configurePermission(fieldCfpHM);
        } else {
            getBinder();
            configureFields();
            validateFields();
            configurePermission(fieldCfpHM);
        }
        Map<String, AppPermission> functionRpHM = stplSecurity.getBusinessFunctionPermission(userId, UISecurityUtil.REBATE_PLAN + ConstantsUtils.COMMA + ConstantsUtils.CALCULATIONS);
        if (functionRpHM.get(FunctionNameUtil.ADD_TIER) != null && ((AppPermission) functionRpHM.get(FunctionNameUtil.ADD_TIER)).isFunctionFlag()) {
            configureCalculationAddButton();
        } else {
            btnAdd.setVisible(false);
        }
        if (functionRpHM.get(FunctionNameUtil.REMOVE_TIER) != null && ((AppPermission) functionRpHM.get(FunctionNameUtil.REMOVE_TIER)).isFunctionFlag()) {
            configureRemoveButton();
        } else {
            btnRemove.setVisible(false);
        }
        if (functionRpHM.get(ConstantsUtils.CALCULATION_RESET) == null || !((AppPermission) functionRpHM.get(ConstantsUtils.CALCULATION_RESET)).isFunctionFlag()) {
            calculationReset.setVisible(false);
        } else {
            resetLogic();
        }
   configureOnViewMode();
    }

    /**
     * Configuring permission
     * 
     * @param fieldIfpHM 
     */
    private void configurePermission(final Map<String, AppPermission> fieldIfpHM) {
        LOGGER.info("Entering configurePermission");
        try {
            List<Object> resultList = ifpLogic.getFieldsForSecurity(UISecurityUtil.REBATE_PLAN, ConstantsUtils.CALCULATIONS);
            commonSecurityLogic.removeComponentOnPermission(resultList, cssLayout, fieldIfpHM, mode.equals(ConstantsUtils.COPY) ? ConstantsUtils.EDIT:mode);
            commonSecurityLogic.removeComponentOnPermission(resultList, cssLayoutMain, fieldIfpHM, mode.equals(ConstantsUtils.COPY) ? ConstantsUtils.EDIT:mode);

            for (int j = 0; j < cssLayoutMain.getComponentCount(); j++) {

                if ((cssLayoutMain.getComponentCount() >= 1) && (cssLayoutMain.getComponent(j).getClass().isInstance(new HorizontalLayout()))) {
                    HorizontalLayout layout = (HorizontalLayout) cssLayoutMain.getComponent(j);
                    if (layout.getComponentCount() <= 0) {
                      cssLayoutMain.removeComponent(cssLayoutMain.getComponent(j));
                    }

                }
            }

        } catch (Exception ex) {
            LOGGER.error(ex);

        }
        LOGGER.info("Ending configurePermission");
    }

    /**
     * Gets the binder.
     */
    private void getBinder() {
        binder.bindMemberFields(this);
       secondaryRebatePlanNo.setValue(StringUtils.EMPTY);
    }

    /**
     * 
     * @throws Exception 
     */
    public void configureResultTable() throws Exception {
       
            final StplSecurity stplSecurity = new StplSecurity();
            userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
            final Map<String, AppPermission> fieldCfpHM = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.REBATE_PLAN+ConstantsUtils.COMMA+UISecurityUtil.CALCULATION_HEADER,false);

            String mode = (String) sessionDTO.getMode();
            List<Object> resultList = ifpLogic.getFieldsForSecurity(UISecurityUtil.REBATE_PLAN, UISecurityUtil.CALCULATION_HEADER);
            Object[] obj = CommonUIUtils.REBATE_TIER_COLUMNS;
            TableResultCustom tableResultCustom = commonSecurityLogic.getTableColumnsPermission(resultList, obj, fieldCfpHM, mode.equals(ConstantsUtils.COPY) ? ConstantsUtils.EDIT:mode);
            if(tableResultCustom.getObjResult().length == 0){
              resultsTable.setVisible(false);
            }
            resultsTable.setContainerDataSource(searchResultbeans);
            resultsTable.setVisibleColumns(tableResultCustom.getObjResult());            
            resultsTable.setColumnHeaders(tableResultCustom.getObjResultHeader());
            resultsTable.markAsDirty();
            resultsTable.addStyleName(ConstantsUtils.FILTER_BAR);

            resultsTable.setFilterBarVisible(true);
            resultsTable.setFilterDecorator(new ExtDemoFilterDecorator());
            resultsTable.setPageLength(9);
            resultsTable.setColumnCollapsingAllowed(true);
            resultsTable.setImmediate(true);
            resultsTable.setSelectable(true);
            resultsTable.setComponentError(null);
            resultsTable.setValidationVisible(false);
            resultsTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {

                @Override
                public void itemClick(ItemClickEvent event) {
                   btnRemove.setEnabled(Integer.valueOf(searchResultbeans.getItem(event.getItemId()).getBean().getTierLevel()) == searchResultbeans.size());
                }
            });

            resultsTable.setWidth("1100px");
        
    }

    /**
     * Configure fields.
     *
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    private void configureFields() throws SystemException, Exception {                
         
        selfGrowthFrom.setDescription(ConstantsUtils.DATE_DES);
        selfGrowthTo.setDescription(ConstantsUtils.DATE_DES);
        marketShareFrom.setDescription(ConstantsUtils.DATE_DES);
        marketShareTo.setDescription(ConstantsUtils.DATE_DES);
        selfGrowthFrom.setDateFormat(ConstantsUtils.DATE_FORMAT);
        selfGrowthTo.setDateFormat(ConstantsUtils.DATE_FORMAT);
        marketShareFrom.setDateFormat(ConstantsUtils.DATE_FORMAT);
        marketShareTo.setDateFormat(ConstantsUtils.DATE_FORMAT);
        configureResultTable();

        commonUtil.loadComboBox(rebateBasedOn, GeneralCommonUtils.REBATE_BASED_ON, false);
        commonUtil.loadComboBox(rebateStructure, GeneralCommonUtils.REBATE_STRUCTURE, false);
        commonUtil.loadComboBox(rebateRangeBasedOn, GeneralCommonUtils.REBATE_RANGE_BASED_ON, false);

        tierFrom.setData("null,null,numericchars,numericvalidationrebateplantierfrom");
        tierFrom.setStyleName("align-right");
        tierFrom.setImmediate(true);
        tierFrom.setValidationVisible(true);
        tierFrom.setDescription((String) tierFrom.getValue());
        tierFrom.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Notifies this listener that the Property's value has changed.
             */
            public void valueChange(final ValueChangeEvent event) {

                tierFrom.setDescription((String) tierFrom.getValue());

            }
        });

        tierLevel.setImmediate(true);
        tierLevel.setValue(String.valueOf(levelValue));
        tierLevel.setReadOnly(true);
        tierLevel.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Notifies this listener that the Property's value has changed.
             */
            public void valueChange(final ValueChangeEvent event) {
                LOGGER.info(String.valueOf(event));
            }
        });
        
        tierTo.setData("null,null,numericchars,numericvalidationrebateplantierto");
        tierTo.setImmediate(true);
        tierTo.setStyleName("align-right");
        tierTo.setValidationVisible(true);
        tierTo.setDescription((String) tierTo.getValue());
        tierTo.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Notifies this listener that the Property's value has changed.
             */
            public void valueChange(final ValueChangeEvent event) {

                tierTo.setDescription((String) tierTo.getValue());

            }
        });

//        tierValue.setData("null,null,numericwithsixDecimal,rpsplcharmsgvalue");
//        tierValue.setStyleName("align-left");
//        tierValue.setImmediate(true);
//        tierValue.setValidationVisible(true);
//        tierValue.setDescription((String) tierValue.getValue());
//        tierValue.addValueChangeListener(new Property.ValueChangeListener() {
//            /**
//             * Notifies this listener that the Property's value has changed.
//             */
//            public void valueChange(final ValueChangeEvent event) {
//                if(valueChange)
//                tierValue.setDescription(String.valueOf( tierValue.getValue()));
//                if(tierValue.isValid() && !tierValue.getValue().isEmpty())
//                {
//                    valueChange=false;
//                    tierValue.setValue(SIX_DIGIT_FORMAT.format(Double.valueOf(tierValue.getValue())));
//                    valueChange=true;
//                }
//
//            }
//        });


        tierTolerance.setData("null,null,numericchars,maxlengthvalidationrebateplantierTolerance");
        tierTolerance.setImmediate(true);
        tierTolerance.setValidationVisible(true);
        tierTolerance.setDescription((String) tierTolerance.getValue());
        tierTolerance.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Notifies this listener that the Property's value has changed.
             */
            public void valueChange(final ValueChangeEvent event) {

                tierTolerance.setDescription((String) tierTolerance.getValue());

            }
        });
                
        valueDDLB.setImmediate(true);
        configureValueDDLB();
        valueDDLB.setValidationVisible(true);                
        valueDDLB.markAsDirty();     
        valueDDLB.setInputPrompt(ConstantsUtils.SELECT_ONE);
        
        percentvalueDDLB.setImmediate(true);
        configurePercentValueDDLB();
        percentvalueDDLB.setValidationVisible(true);                
        percentvalueDDLB.markAsDirty();     
        percentvalueDDLB.setInputPrompt(ConstantsUtils.SELECT_ONE);
        
        selectOne.setImmediate(true);
        selectOne.setValidationVisible(true);                
        selectOne.markAsDirty();     
        selectOne.addItem(0);
        selectOne.setItemCaption(0, ConstantsUtils.SELECT_ONE);
        selectOne.setNullSelectionAllowed(true);
        selectOne.setNullSelectionItemId(0);
        
        selfGrowthIndicator.setData("maximumlengthvalidation,maximumlengthvalidationrebateplanselfGrowthIndicator,null,null");
        selfGrowthReference.setData("maxlengthvalidation,maximumlengthvalidationrebateplanselfGrowthReference,null,null");
        marketShareIndicator.setData("maximumlengthvalidation,maximumlengthvalidationrebateplanmarketShareIndicator,null,null");
        marketShareReference.setData("maxlengthvalidationfifty,maximumlengthvalidationrebateplanmarketShareReference,null,null");
        secondaryRebatePlanNo.setData("maxlengthvalidationhundred,maxlengthvalidationSecondaryrp,null,null");
        secondaryRebatePlanNo.setValue(StringUtils.EMPTY);
        commonUtil.loadComboBox(tierOperator, GeneralCommonUtils.TIER_OPERATOR, false);
        final Map operatorMap = new HashMap();
        Collection collection = tierOperator.getItemIds();
        for (Object object : collection) {
            if (object != null) {
                HelperDTO helperDTO = (HelperDTO) object;
                operatorMap.put(helperDTO.getDescription(), helperDTO);
            }
        }
        
        netSalesFormulaName.setImmediate(true);
        netSalesFormulaName.addStyleName("searchicon");
        netSalesFormulaName.addClickListener(new CustomTextField.ClickListener() {
                /**
                 * Method used for parent Rebate Schedule Id
                 */
                public void click(final CustomTextField.ClickEvent event) {
                    try {
                        netSalesLookup = new NetSalesFormulaLookup(netSalesFormulaName); 
                       
                        UI.getCurrent().addWindow(netSalesLookup);
                        netSalesLookup.addCloseListener(new Window.CloseListener() {
                            @Override
                            public void windowClose(Window.CloseEvent e) {
                                if(netSalesLookup.isSelected){
                                NetSalesFormulaDTO netSaleFormula= netSalesLookup.getSelectedItem();
                               
                                LOGGER.info("netSaleFormula.getSystemID()"+netSaleFormula.getSystemID());
                                if(!StringUtils.isNotBlank(netSaleFormula.getSystemID()) || ConstantsUtils.NULL.equals(netSaleFormula.getSystemID())){
                                    
                                } else {
                                        netSalesFormulaName.setValue(netSaleFormula.getNetSalesFormulaName());
                                        netSalesFormulaSid.setValue(netSaleFormula.getSystemID());
                                        
                                }
                            }
                            }
                        });
                    } catch (Exception ex) {
                        LOGGER.error(ex);
                    }
                    
                }
        });
        
        tierFormula.setImmediate(true);
        tierFormula.addStyleName("searchicon");
        tierFormula.addClickListener(new CustomTextField.ClickListener() {
                /**
                 * Method used for parent Rebate Schedule Id
                 */
                public void click(final CustomTextField.ClickEvent event) {
                    try {
                        final FormulaLookup formulaLookup = new FormulaLookup(); 
                       
                        UI.getCurrent().addWindow(formulaLookup);
                        formulaLookup.addCloseListener(new Window.CloseListener() {
                            @Override
                            public void windowClose(Window.CloseEvent e) {
                                if (formulaLookup.isSelected()) {
                                RSFormulaDTO formulaDTO = formulaLookup.getSelectedItem();
                                tierFormula.setValue(formulaDTO.getFormulaNo());
                                tierFormula.setData(formulaDTO.getFormulaID()+ ConstantsUtils.TILDE + formulaDTO.getFormulaName());
                            }
                            }
                        });
                    } catch (Exception ex) {
                        LOGGER.error(ex);
                    }
                    
                }
                });
        secondaryRebatePlanNo.setImmediate(true);
        secondaryRebatePlanNo.addStyleName("searchicon");
        secondaryRebatePlanNo.addClickListener(new CustomTextField.ClickListener() {
            @Override
            public void click(CustomTextField.ClickEvent event) {
                try {
                    final RebatePlanLookup rebatePlanLookup = new RebatePlanLookup();
                    UI.getCurrent().addWindow(rebatePlanLookup);
                    rebatePlanLookup.addCloseListener(new Window.CloseListener() {
                        @Override
                        public void windowClose(Window.CloseEvent e) {
                            if (rebatePlanLookup.isSelected()) {
                                RebatePlanDTO rebatePlanDTO = rebatePlanLookup.getSelectedItem();
                                secondaryRebatePlanNo.setValue(rebatePlanDTO.getRebatePlanNo());
                                secondaryRebatePlanNo.setData(rebatePlanDTO.getRebatePlanSystemId() + ConstantsUtils.TILDE + rebatePlanDTO.getRebatePlanName());
                            }
                        }
                    });
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            }
        });

        
        Map<String,List<HelperDTO>> map = helperListUtil.getListNameMap();
        List<HelperDTO> helperList = map.get("RULE_TYPE");    
        if(helperList !=null && !helperList.isEmpty()){
        for(HelperDTO obj : helperList){
            if("Net Sales".equalsIgnoreCase(obj.getDescription())){
               helperDTO = obj; 
            }
        }
        }
        netSalesRule.setImmediate(true);
        netSalesRule.addStyleName("searchicon");
        netSalesRule.addClickListener(new CustomTextField.ClickListener() {
            @Override
            public void click(CustomTextField.ClickEvent event) {
                try {
                    final NetSalesRuleLookUp netSalesLookup = new NetSalesRuleLookUp(netSalesRule, helperDTO,"Net Sales Rule Lookup","Rebate Schedule");
                    UI.getCurrent().addWindow(netSalesLookup);
                    netSalesLookup.addCloseListener(new Window.CloseListener() {
                        @Override
                        public void windowClose(Window.CloseEvent e) {
                            if (netSalesLookup.isSelected()) {
                                NetSalesRuleLookupDto searchDTO = netSalesLookup.getSelectedItem();
                                netSalesRule.setValue(searchDTO.getRuleName());
                                netSalesRule.setData(searchDTO.getRuleSystemId());
                            }
                        }
                    });
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            }
        });
        
        rebateBasedOn.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(ValueChangeEvent event) {
                
                if (event.getProperty().getValue() != null) {
                    if (Constants.DOLLARS.equalsIgnoreCase(event.getProperty().getValue().toString())) {
                        tierOperator.select(operatorMap.get("$"));
                    } else if (Constants.UNITS.equalsIgnoreCase(event.getProperty().getValue().toString())) {
                        tierOperator.select(operatorMap.get("%"));
                    }else{
                        tierOperator.select(operatorMap.get("0"));
                    }
                }
            }
        });
        
        tierOperator.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(ValueChangeEvent event) {
                if (event.getProperty() != null && "$".equals(String.valueOf(event.getProperty().getValue()))) {
                    percentvalueDDLB.setVisible(false);
                    valueDDLB.setVisible(true);
                    selectOne.setVisible(false);
                } else if (event.getProperty() != null && "%".equals(String.valueOf(event.getProperty().getValue()))){
                    selectOne.setVisible(false);
                    percentvalueDDLB.setVisible(true);
                    valueDDLB.setVisible(false);
                }else{
                     percentvalueDDLB.setVisible(false);
                     valueDDLB.setVisible(false);
                    selectOne.setVisible(true);
                }
            }
        });
    }

    /**
     * Detach listeners.
     *
     * @param field the field
     */
    public void detachListeners(final AbstractField field) {

        LOGGER.info("Entering detachListeners method");
        List<Property.ValueChangeListener> listeners;

        listeners = (List<Property.ValueChangeListener>) field.getListeners(ValueChangeEvent.class);
        for (final Property.ValueChangeListener object : listeners) {
            field.removeValueChangeListener(object);
        }
        LOGGER.info("End of detachListeners method");

    }

    /**
     * Removes the button.
     *
     * @throws Exception the exception
     */
    @UiHandler("calculationRemove")
    private void configureRemoveButton() throws Exception {

        // Commit button
        btnRemove.setWidth(ConstantsUtils.BTN_WIDTH);
        btnRemove.addClickListener(new ClickListener() {
            /**
             * Notifies this listener that the Property's value has changed.
             */
            @SuppressWarnings("PMD")
            public void buttonClick(final ClickEvent event) {
                if (event != null) {
                    LOGGER.info("Entering Rebate Plan remove operation");
                    final Object itemId = resultsTable.getValue();
                    boolean flag = resultsTable.isSelected(itemId);
                    if (searchResultbeans.size() == 0) {
                        final MessageBox msg = MessageBox.showPlain(Icon.ERROR, "No items available", "There is no Rebate Plan Level available to remove", new MessageBoxListener() {    
                            /**            
                             * The method is triggered when a button of the message box is   
                             * pressed .        
                             *          
                             * @param buttonId The buttonId of the pressed button.     
                             */           
                            @SuppressWarnings("PMD")           
                            public void buttonClicked(final ButtonId buttonId) { 
                                // Do Nothing     
                            }           
                        }, ButtonId.OK);  
                        msg.getButton(ButtonId.OK).focus();
                    } else if (!flag) {
                        final MessageBox msg = MessageBox.showPlain(Icon.ERROR, "Remove", "Please select the last tier from the list view to remove.", new MessageBoxListener() {    
                            /**            
                             * The method is triggered when a button of the message box is   
                             * pressed .        
                             *          
                             * @param buttonId The buttonId of the pressed button.     
                             */           
                            @SuppressWarnings("PMD")           
                            public void buttonClicked(final ButtonId buttonId) { 
                                // Do Nothing     
                            }           
                        }, ButtonId.OK);  
                        msg.getButton(ButtonId.OK).focus();
                    } else {
                        MessageBox.showPlain(Icon.QUESTION, "Confirmation", "Are you sure you want to remove the last tier ?", new MessageBoxListener() {
                            /**
                             * Called when a Button has been clicked . a
                             */
                            @SuppressWarnings("PMD")
                            public void buttonClicked(final ButtonId buttonId) {
                                try {
                                    if (buttonId.name().equals("YES")) {
                                        BigDecimal tierFromValue = searchResultbeans.lastItemId().getTierFrom();
                                        searchResultbeans.removeItem(searchResultbeans.lastItemId());
                                        tempBeansList.removeItem(tempBeansList.lastItemId());
                                        levelValue = searchResultbeans.size() + 1;
                                        tierLevel.setReadOnly(false);
                                        tierLevel.setValue(String.valueOf(levelValue));
                                        tierLevel.setReadOnly(true);
                                        tierFrom.setReadOnly(false);
                                        tierTo.setReadOnly(false);
                                        if (searchResultbeans.size() != 0) {
                                            tierFrom.setValue(tierFromValue.setScale(2, RoundingMode.HALF_UP).toPlainString());
                                            tierFrom.setReadOnly(true);
                                        }else{
                                            tierFrom.setValue(String.valueOf(Constants.ZERO));
                                        }
                                        tierTo.setValue(StringUtils.EMPTY);
                                    }
                                } catch (Exception e) {
                                    LOGGER.error(e);
                                }
                            }
                        }, ButtonId.YES, ButtonId.NO);
                    }
                }
                LOGGER.info("Ending Rebate Plan remove operation");
            }
        });
    }

    /**
     * Adds the calculation add button
     *
     * @throws Exception the exception
     */
    private void configureCalculationAddButton() throws Exception {

        btnAdd.setWidth(ConstantsUtils.BTN_WIDTH);
        btnAdd.setImmediate(true);
        btnAdd.addClickListener(new ClickListener() {
            /**
             *
             */
            private static final long serialVersionUID = 1L;

            /**
             * Notifies this listener that the Property's value has changed.
             */
            public void buttonClick(final ClickEvent event) {

                LOGGER.debug("Entering Rebate Plan Calculate operation");
                try {  
                    binder.commit();
                    binder.getErrorDisplay().clearError();                  
                    LOGGER.info("inside calc");
                    BigDecimal tFromValue, tToValue;
                    final RebatePlanTierResults rebatePlanTier = new RebatePlanTierResults();
                    
                    if (tempBeansList != null && tempBeansList.size() != 0) {  
                        if (tempBeansList.lastItemId().getTierTo() == null || StringUtils.isBlank(tempBeansList.lastItemId().getTierTo().toPlainString())) {
                            binder.getErrorDisplay().setError("No more tiers can be entered");
                            return;
                        }
                    }

                    if ((StringUtils.isBlank(tierFrom.getValue()))) {
                        binder.getErrorDisplay().setError("Please enter From");
                        return;
                    } else {
                        if(tierFrom.getValue() != null && tierFrom.getValue().contains("-")){
                            binder.getErrorDisplay().setError("Only positive numbers are allowed to be entered.");
                            return;
                        }
                        
                        try {
                            final String tfrom = tierFrom.getValue().trim();
                            tFromValue = new BigDecimal(tfrom);
                            rebatePlanTier.setTierFrom(tFromValue);
                            rebatePlanTier.setTierFrom(new BigDecimal(formatter.format(tFromValue)));
                        } catch (Exception e) {
                            LOGGER.error(e);
                            binder.getErrorDisplay().setError("Please enter only numbers with two decimals places in From");
                            return;
                        }

                    }
                    
                    if (StringUtils.isNotBlank(secondaryRebatePlanNo.getValue()) && !ConstantsUtils.NULL.equals(secondaryRebatePlanNo.getValue())) {
                        rebatePlanTier.setSecondaryRebatePlanNo(secondaryRebatePlanNo.getValue());
                        String[] secondaryRebateDetails = String.valueOf(secondaryRebatePlanNo.getData()).split(ConstantsUtils.TILDE);
                        if (secondaryRebateDetails != null && secondaryRebateDetails.length > 1) {
                            rebatePlanTier.setSecondaryRebatePlanSid(Integer.valueOf(secondaryRebateDetails[0]));
                            rebatePlanTier.setSecondaryRebatePlanName(secondaryRebateDetails[1]);
                        }
                    }
                    
                    if (StringUtils.isNotBlank(tierFormula.getValue()) && !ConstantsUtils.NULL.equals(tierFormula.getValue())) {
                        rebatePlanTier.setFormulaNo(tierFormula.getValue());
                        String[] formulaDetails = String.valueOf(tierFormula.getData()).split(ConstantsUtils.TILDE);
                        if (formulaDetails != null && formulaDetails.length > 1) {
                            rebatePlanTier.setFormulaID(formulaDetails[0]);
                            rebatePlanTier.setFormulaName(formulaDetails[1]);
                        }
                    }
                    
                    if (StringUtils.isNotBlank(tierTo.getValue())) {
                        
                        if(tierTo.getValue() != null && tierTo.getValue().contains("-")){
                            binder.getErrorDisplay().setError("Only positive numbers are allowed to be entered.");
                            return;
                        }
                        
                        try {
                            final String terTo = tierTo.getValue().trim();
                            tToValue = new BigDecimal(terTo);
                            rebatePlanTier.setTierTo(tToValue);
                            rebatePlanTier.setTierTo(new BigDecimal(formatter.format(tToValue)));
                        } catch (Exception e) {
                            LOGGER.error(e);
                            binder.getErrorDisplay().setError("Please enter only numbers with two decimals places in To");
                            return;
                        }                        
                        if (tToValue.compareTo(tFromValue) == -1) {
                            binder.getErrorDisplay().setError("To value should not be lesser than From value");
                            return;
                        }

                    }

                    if (tierOperator.getValue() == null || 
                            ((HelperDTO)tierOperator.getValue()) == null ||
                            ConstantsUtils.SELECT_ONE.equals(tierOperator.getValue()) || 
                            ConstantsUtils.SELECT_ONE.equals(((HelperDTO)tierOperator.getValue()).getDescription())) {
                        binder.getErrorDisplay().setError("Please enter Operator");
                        return;
                    } else {
                        rebatePlanTier.setTierOperator(String.valueOf(((HelperDTO)tierOperator.getValue()).getId()));
                        rebatePlanTier.setTierOperatorValue(tierOperator.getItemCaption(tierOperator.getValue()));
                    }
                    
                        if ("$".equalsIgnoreCase(tierOperator.getValue().toString())) {                        
                        if (valueDDLB.getValue() == null || ConstantsUtils.SELECT_ONE.equals(valueDDLB.getValue().toString())
                                || "0".equals(valueDDLB.getValue())) {
                            binder.getErrorDisplay().setError("Please Select Value");
                            return;
                        } else {
                            rebatePlanTier.setTierValue(valueDDLB.getItemCaption(valueDDLB.getValue()).trim());
                            rebatePlanTier.setTierValueId(String.valueOf(valueDDLB.getValue()));                            
                            }
                        }
                        if ("%".equalsIgnoreCase(tierOperator.getValue().toString())) {                        
                        if (percentvalueDDLB.getValue() == null || ConstantsUtils.SELECT_ONE.equals(percentvalueDDLB.getValue().toString())
                                || "0".equals(percentvalueDDLB.getValue())) {
                            binder.getErrorDisplay().setError("Please Select Value");
                            return;
                        } else {
                            rebatePlanTier.setTierValue(percentvalueDDLB.getItemCaption(percentvalueDDLB.getValue()).trim());
                            rebatePlanTier.setTierValueId(String.valueOf(percentvalueDDLB.getValue()));      
                        }
                    }
                    
                    if (StringUtils.isNotBlank(tierLevel.getValue())) {
                        rebatePlanTier.setTierLevel(tierLevel.getValue().trim());
                    }
                    if((tierTolerance.getValue().length()!=0) && !ConstantsUtils.NULL.equals(tierTolerance.getValue())){
                    rebatePlanTier.setTierTolerance(Double.valueOf(String.valueOf(tierTolerance.getValue())));
                    }                    
                    binder.getFields();
                    binder.commit();
          
                    if (searchResultbeans.size() < GeneralCommonUtils.ONE && tempBeansList.size() < GeneralCommonUtils.ONE) {                        
                        tempBeansList.addBean(rebatePlanTier);
                        searchResultbeans.addBean(rebatePlanTier);
                    } else {                        
                        tempBeansList.addItemAt(searchResultbeans.size(), rebatePlanTier);
                        searchResultbeans.addItemAt(searchResultbeans.size(), rebatePlanTier);
                    }
                   
                    String tierToString = StringUtils.EMPTY;
//                    tierValue.setValue(StringUtils.EMPTY);
                    tierTolerance.setValue(StringUtils.EMPTY);
                    levelValue = searchResultbeans.size() + 1;
                    tierLevel.setReadOnly(false);
                    tierLevel.setValue(String.valueOf(levelValue));
                    tierLevel.setReadOnly(true);
                    tierOperator.setValue(0);
                    valueDDLB.setValue(0);
                    percentvalueDDLB.setValue(0);
                    selectOne.setValue(0);
                    if (StringUtils.isNotBlank(tierTo.getValue()) && !ConstantsUtils.NULL.equals(tierTo.getValue()) && !ConstantsUtils.ZERO.equals(tierTo.getValue())) {
                        tierToString = tierTo.getValue();
                        tierTo.setValue(StringUtils.EMPTY);
                    } else {
                        tierFrom.setReadOnly(true);
                        tierTo.setReadOnly(true);
                    }
                    if (!StringUtils.EMPTY.equals(tierToString)) {
                        tierFrom.setReadOnly(false);
                        tierFrom.setValue((new BigDecimal(tierToString).add(BigDecimal.valueOf(0.01))).setScale(2, RoundingMode.HALF_UP).toPlainString());
                        tierFrom.setReadOnly(true);
                    }
                    LOGGER.info("Ending Rebate Plan Calculate operation");
                } catch (CommitException ex) {
                    LOGGER.error(ex);
                }
                }
        });

    }

    /**
     * Focus Information based on field rebateBasedOn
     */
    public void focusRebateBasedOn() {
        rebateBasedOn.focus();
    }

    /**
     * Getter method for Result Table
     *
     * @return resultsTable
     */
    public ExtFilterTable getResultsTable() {
        return resultsTable;
    }

    /**
     * Getter Method for add button
     *
     * @return btnAdd
     */
    public Button getBtnAdd() {
        return btnAdd;
    }

    /**
     * Getter Method for Remove button
     *
     * @return btnRemove
     */
    public Button getBtnRemove() {
        return btnRemove;
    }

    /**
     * Configurations of layout for view mode
     */
    public void configureOnView() {
        btnAdd.setVisible(false);
        btnRemove.setVisible(false);

        tierTolerance.setVisible(false);
        tierToleranceLabel.setVisible(false);
        fromLabel.setVisible(false);
        tierFrom.setVisible(false);
        tierTo.setVisible(false);
        toLabel.setVisible(false);
        valueLabel.setVisible(false);
//        tierValue.setVisible(false);
        operatorLabel.setVisible(false);
        tierOperator.setVisible(false);
    }

    private void validateFields() {
        Collection<Field<?>> collection = binder.getFields();
        CommonUtil commmonUtil = CommonUtil.getInstance();
        for (Field field : collection) {
            if (field instanceof TextField) {
                TextField textField = (TextField) field;
                commmonUtil.textValidation(textField, textField.getData());
            }
        }
    }
    public void configureOnEdit(){
        tierFrom.setReadOnly(true);
    }

    
    /**
     * The Class SelfGrowthDateValidator.
     */
    @SuppressWarnings("rawtypes")
    public class SelfGrowthDateValidator extends AbstractValidator {

        /**
         * The Constant serialVersionUID.
         */
        private static final long serialVersionUID = 1L;

        /**
         * The Constructor.
         */
        public SelfGrowthDateValidator() {
            super(StringUtils.EMPTY);
        }

        /**
         * The Constructor.
         *
         * @param errorMessage the error message
         */
        public SelfGrowthDateValidator(final String errorMessage) {
            super(errorMessage);
        }

        /**
         * Validates the value.
         *
         * @param value the value
         */
        @Override
        public void validate(final Object value) {

            if (selfGrowthFrom.getValue() != null && selfGrowthTo.getValue() != null) {
                if (selfGrowthFrom.getValue().after(selfGrowthTo.getValue())) {
                    throw new Validator.InvalidValueException("Self Growth To Date should be greater than Self Growth From Date");
                } else if (selfGrowthFrom.getValue().equals(selfGrowthTo.getValue())) {
                    throw new Validator.InvalidValueException("Self Growth From Date and Self Growth To Date should not be  equal");
                }
            }

        }

        /**
         * Validates the value and returns result as boolean.
         *
         * @param value the value
         * @return true, if is valid value
         */
        @Override
        protected boolean isValidValue(final Object value) {

            if (selfGrowthFrom.getValue() != null && selfGrowthTo.getValue() != null) {
                return selfGrowthFrom.getValue().compareTo(selfGrowthTo.getValue()) <= 0;
            }

            return true;
        }

        /**
         * Default method.
         *
         * @return the type
         */
        @Override
        public Class getType() {
            return null;
        }
    }
   
    /**
     * The Class MarketShareDateValidator.
     */
    @SuppressWarnings("rawtypes")
    public class MarketShareDateValidator extends AbstractValidator {

        /**
         * The Constant serialVersionUID.
         */
        private static final long serialVersionUID = 1L;

        /**
         * The Constructor.
         */
        public MarketShareDateValidator() {
            super(StringUtils.EMPTY);
        }

        /**
         * The Constructor.
         *
         * @param errorMessage the error message
         */
        public MarketShareDateValidator(final String errorMessage) {
            super(errorMessage);
        }

        /**
         * Validates the value.
         *
         * @param value the value
         */
        @Override
        public void validate(final Object value) {

            if (marketShareFrom.getValue() != null && marketShareTo.getValue() != null) {
                if (marketShareFrom.getValue().after(marketShareTo.getValue())) {
                    throw new Validator.InvalidValueException("Market Share To Date should be greater than Market Share From Date");
                } else if (marketShareFrom.getValue().equals(marketShareTo.getValue())) {
                    throw new Validator.InvalidValueException("Market Share From Date and Market Share To Date should not be  equal");
                }
            }

        }

        /**
         * Validates the value.
         *
         * @param value the value
         * @return true, if is valid value
         */
        @Override
        protected boolean isValidValue(final Object value) {

            if (marketShareFrom.getValue() != null && marketShareTo.getValue() != null) {
                return marketShareFrom.getValue().compareTo(marketShareTo.getValue()) <= 0;
            }

            return true;
        }

        /**
         * Default Method.
         *
         * @return the type
         */
        @Override
        public Class getType() {
            return null;
        }
    }
  
    void resetLogic() {

        calculationReset.addClickListener(new ClickListener() {
            @SuppressWarnings("PMD")
            /**
             * Constant SerialID
             */
            private static final long serialVersionUID = 1L;

            /**
             * Logic for back button.
             *
             * @param event
             */
            @SuppressWarnings("PMD")
            public void buttonClick(final ClickEvent event) {
                MessageBox.showPlain(Icon.QUESTION, "Confirmation", commonMsg.getResetMessage(), new MessageBoxListener() {
                    /**
                     * Called when a Button has been clicked .
                     *
                     */
                    @SuppressWarnings("PMD")
                    public void buttonClicked(final ButtonId buttonId) {
                        if (buttonId.name().equals("YES")) {
                            try {
                                LOGGER.info("Entering inside  reset  method for calculation ");
                                binder.getErrorDisplay().clearError();
                                if (sessionDTO.getMode().equalsIgnoreCase(ConstantsUtils.EDIT)||sessionDTO.getMode().equalsIgnoreCase(ConstantsUtils.COPY)) {
                                    final String idValue = String.valueOf(sessionDTO.getSystemId());
                                    final int rebatePlanMasterId = Integer.valueOf(idValue);
                                    searchResultbeans.removeAllItems();
                                    tempBeansList.removeAllItems();
                                    searchResultbeans.addAll(rebatePlanLogic.getRebatePlanTiersfromId(rebatePlanMasterId,rebatePlanMasterDTO));
                                    tempBeansList.addAll(searchResultbeans.getItemIds());

                                    BigDecimal toToValue = searchResultbeans.lastItemId().getTierTo();

                                    if (toToValue != null && !toToValue.equals(BigDecimal.ZERO)) {
                                        tierFrom.setReadOnly(false);
                                        tierTo.setReadOnly(false);
                                        toToValue.add(BigDecimal.ONE);
                                        tierFrom.setValue((toToValue.setScale(2, RoundingMode.HALF_UP).toPlainString()));
                                        tierFrom.setReadOnly(true);
                                    } else {
                                        tierFrom.setReadOnly(false);
                                        tierFrom.setValue("");
                                        tierFrom.setReadOnly(true);
                                        tierTo.setReadOnly(false);
                                    }
                                } else {
                                    searchResultbeans.removeAllItems();
                                    tempBeansList.removeAllItems();
                                }
                            } catch (Exception ex) {
                                LOGGER.error("Error in reset Caluculation Table" + ex);
                            }
                        }
                    }
                }, ButtonId.YES, ButtonId.NO);
            }
        });
    }

    private void configureValueDDLB() {
        valueDDLB.addItem(0);
        valueDDLB.setItemCaption(0, ConstantsUtils.SELECT_ONE);
        Map<Integer, String> map = rebatePlanMasterDTO.getItemPricingQualifierMap();
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            valueDDLB.addItem(entry.getKey());
            valueDDLB.setItemCaption(entry.getKey(), entry.getValue());
        }
        valueDDLB.setNullSelectionAllowed(true);
        valueDDLB.setNullSelectionItemId(0);
        valueDDLB.setNewItemsAllowed(true);
        valueDDLB.setNewItemHandler(new AbstractSelect.NewItemHandler() {

            @Override
            public void addNewItem(String newItemCaption) {
                try {
                    Double.parseDouble(newItemCaption);
                    String value = "$" + newItemCaption;
                    valueDDLB.addItem(value);
                    valueDDLB.setItemCaption(value, newItemCaption);
                    valueDDLB.select(value);
                } catch (NumberFormatException e) {
                    valueDDLB.select(valueDDLB.getValue());    
                    
                }
            }
        });
        
    }
    private void configurePercentValueDDLB() {
     
        percentvalueDDLB.addItem(0);
        percentvalueDDLB.setItemCaption(0, ConstantsUtils.SELECT_ONE);
        if (helperListUtil.getListNameMap().get("TIER_PERCENT_VALUE")!=null) {
            percentvalueDDLB.addItems(helperListUtil.getListNameMap().get("TIER_PERCENT_VALUE"));
        }
        percentvalueDDLB.setNullSelectionAllowed(true);
        percentvalueDDLB.setNullSelectionItemId(0);
        percentvalueDDLB.setNewItemsAllowed(true);
        percentvalueDDLB.setNewItemHandler(new AbstractSelect.NewItemHandler() {

            @Override
            public void addNewItem(String newItemCaption) {
                try {
                    String  value = "%" + newItemCaption;  
                    percentvalueDDLB.addItem(value);
                    percentvalueDDLB.setItemCaption(value, newItemCaption);
                    percentvalueDDLB.select(value);
                } catch (NumberFormatException e) {
                    percentvalueDDLB.select(valueDDLB.getValue());    
                    
                }
            }
        });
        
    }
    void configureOnViewMode()
    {
        if(sessionDTO.getMode().equalsIgnoreCase("View"))
        {
            netSalesFormulaName.setEnabled(false);
            calculationReset.setEnabled(false);
            netSalesRule.setEnabled(false);
            tierFormula.setEnabled(false);
            secondaryRebatePlanNo.setEnabled(false);
        }
    }
    private void addScrollBar() {
       

        if (Page.getCurrent().getBrowserWindowWidth() >= 1900) {
            HorizontalLayout layout = (HorizontalLayout) tierPanel.getContent();
            rpCalculationLayout.removeComponent(tierPanel);
            rpCalculationLayout.addComponent(layout);
            extended = true;
            minimized = false;
        }
        Page.getCurrent().addBrowserWindowResizeListener(new Page.BrowserWindowResizeListener() {
            @Override
            public void browserWindowResized(Page.BrowserWindowResizeEvent event) {
                if (event.getWidth() >= 1900 && !extended) {
                    HorizontalLayout layout = (HorizontalLayout) tierPanel.getContent();
                    rpCalculationLayout.removeComponent(tierPanel);
                    rpCalculationLayout.addComponent(layout);
                    extended = true;
                    minimized = false;
                } else if (!minimized) {
                    if (!rpCalculationLayout.getComponent(0).getId().equals("tierPanel")) {
                        tierPanel.setContent(rpCalculationLayout.getComponent(0));
                    }
                    rpCalculationLayout.removeAllComponents();
                    rpCalculationLayout.addComponent(tierPanel);
                    minimized = true;
                    extended = false;
                }
            }
        });
    }
}
