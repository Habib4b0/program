/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.netsalesformula.ui.form;

import com.stpl.app.global.abstractsearch.view.AbstractSearchView;
import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.global.common.util.CommonUtil;
import com.stpl.app.global.ifp.logic.IFPLogic;
import com.stpl.app.global.netsalesformula.dto.NsfDto;
import com.stpl.app.global.netsalesformula.dto.SalesBasisDto;
import com.stpl.app.global.netsalesformula.logic.NsfLogic;
import com.stpl.app.global.netsalesformula.ui.view.NSFView;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.ui.StplCustomComponent;
import com.stpl.app.ui.errorhandling.ErrorLabel;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.TabNameUtil;
import com.stpl.app.util.UISecurityUtil;
import com.stpl.domain.global.base.AddBaseForm;
import com.stpl.ifs.ui.CommonSecurityLogic;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.Position;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author karthikraja.k
 */
public class NSFMainTab extends StplCustomComponent implements AddBaseForm {
    
    /** The Formula Id */
    @UiField("formulaId")
    TextField formulaId;
    /** The Formula No*/
    @UiField("formulaNo")
    TextField formulaNo;
    /** The Formula Name*/
    @UiField("formulaName")
    TextField formulaName;
    /** The Formula  Category*/
    @UiField("formulaCategory")
    ComboBox formulaCategory;
    /** The Save Button*/
    @UiField("saveBtn")
    private Button saveBtn;
    /** The back Button*/
    @UiField("backBtn")
    private Button backBtn;
     /**
     * The error msg.
     */
    @UiField("errorMsg")
    private ErrorLabel errorMsg;  
    
    @UiField("cssLayout")
    private HorizontalLayout cssLayout;
    
    SalesBasis salesBasis;
    
    Deductions deductions;
    SessionDTO sessionDto;
    public static final Logger LOGGER = Logger.getLogger(NSFMainTab.class);
     /**
     * The binder.
     */
    private final ErrorfulFieldGroup binder;
    NsfDto nsfDto;
    NsfLogic logic = new NsfLogic();
    CommonUtil commonMsg=CommonUtil.getInstance();
    IFPLogic ifpLogic = new IFPLogic();
    CommonSecurityLogic securityLogic=new CommonSecurityLogic();
    SalesBasisDto salesBasisDto=new SalesBasisDto();
    /**
     * The tab sheet.
     */
    @UiField("tabSheet")
    private TabSheet tabSheet;
    public NSFMainTab(SessionDTO sessionDto,ErrorfulFieldGroup binder,NsfDto nsfDto,SalesBasisDto salesBasisDto) throws SystemException, PortalException {     
         super();
         this.binder=binder;
         this.nsfDto=nsfDto;
         this.sessionDto=sessionDto;
         this.salesBasisDto=salesBasisDto;
         setCompositionRoot(Clara.create(getClass().getResourceAsStream("/declarativeui/nsf/NSFMainTab.xml"), this));
         init();
    }

    /**
     * Method to provide an easy way of binding fields to data and handling
     * commits of fields.
     *
     * @return the binder
     */
    private ErrorfulFieldGroup getBinder() {

        binder.bindMemberFields(this);
        if (ConstantsUtils.COPY.equalsIgnoreCase(sessionDto.getMode())) {
            NsfDto nsfNewDto = new NsfDto();
            nsfNewDto.setFormulaCategory(NSFView.getFormCategory());
            binder.setItemDataSource(new BeanItem<>(nsfNewDto));
        } else {
            binder.setItemDataSource(new BeanItem<>(nsfDto));
        }
        binder.setBuffered(true);
        binder.setErrorDisplay(errorMsg);
        errorMsg.setId("ErrorMessage");
        return binder;
    }
    
    @Override
    public void addLogic() {
        return;
    }

    @Override
    public void init() throws SystemException, PortalException {
        addToContent();
        configureFields();
        getBinder();
        getResponsiveFirstTab();
        configureButtonPermission();
    }

    @Override
    public void configureFields() throws PortalException, SystemException {
             
           backBtn.addClickListener(new Button.ClickListener() {
            /**
             * Constant SerialID
             */
            private static final long serialVersionUID = 1L;

            /**
             * Logic for back button.
             *
             * @param event
             */
            @Override
            public void buttonClick(final Button.ClickEvent event) {
                 
                if (!ConstantsUtils.VIEW.equalsIgnoreCase(sessionDto.getMode())) {
                    MessageBox.showPlain(Icon.QUESTION, "Save?", "Do you want to save this Net Sales Formula record before returning to the Landing Screen?", new MessageBoxListener() {
                        /**
                         * Called when a Button has been clicked .
                         *
                         */
                        @Override
                        public void buttonClicked(final ButtonId buttonId) {
                            try {
                                if (buttonId.name().equals("YES") && !validateSave()) {
                                        logic.saveNsf(sessionDto, binder, deductions.getFormulaType(), salesBasis.getContractSelected(),Integer.valueOf(salesBasis.getRuleSystemId()));
                                        navigateToNsf();
                                }
                                
                                if (buttonId.name().equalsIgnoreCase("NO")) {
                                 getUI().getNavigator().navigateTo(AbstractSearchView.NAME);
                                }
                            } catch (Exception ex) {
                                LOGGER.error(ex);
                            }
                        }
                    }, ButtonId.YES, ButtonId.NO);
                } else {
                    navigateToNsf();
                }
            }
        });

        CommonUtil commonUtil = CommonUtil.getInstance();
        commonUtil.loadComboBox(formulaCategory, com.stpl.app.global.abstractsearch.util.UIUtils.NS_FORMULA_CATEGORY, false);  
        if (ConstantsUtils.VIEW.equalsIgnoreCase(sessionDto.getMode())) {
            disableFields();
            saveBtn.setEnabled(false);
        }
         if (ConstantsUtils.EDIT.equalsIgnoreCase(sessionDto.getMode())) {
         saveBtn.setCaption("Update");
         }
    }

    @Override
    public void addToContent() throws PortalException, SystemException {
        addTabSheet();
    }

    private void addTabSheet() {
        tabSheet.addStyleName(ValoTheme.TABSHEET_FRAMED);
        tabSheet.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
        tabSheet.setId("responsive-tab");
        tabSheet.setReadOnly(true);
        salesBasis = new SalesBasis(sessionDto, nsfDto,salesBasisDto);
        salesBasis.setCaption(TabNameUtil.SALES_BASIS);
        tabSheet.addTab(salesBasis, TabNameUtil.SALES_BASIS);
        deductions = new Deductions(sessionDto,nsfDto);
        deductions.setCaption(TabNameUtil.DEDUCTIONS);
        tabSheet.addTab(deductions, TabNameUtil.DEDUCTIONS);
    }
     /**
     * Save btn.
     *
     * @param event the event
     */
    @UiHandler("saveBtn")
    public void saveBtn(Button.ClickEvent event) {
       if(!validateSave()){
        
           String msg = logic.duplicateCheck(binder);
           if (sessionDto.getMode().equalsIgnoreCase("Edit")) {
               msg = "S";
           }
           if ("S".equals(msg)) {
               saveNsf();
           } else if (("IDEXIST").equals(msg)) {
               binder.getErrorDisplay().setError("Formula ID already exists, Please enter different Formula ID");
           } else if ("NOEXIST".equals(msg)) {
               binder.getErrorDisplay().setError("Formula No already exists, Please enter different Formula No");
           } else if ("NAMEEXIST".equals(msg)) {
               binder.getErrorDisplay().setError("Formula Name already exists, Please enter different Formula Name");
           }
           
       }
        UI.getCurrent().setFocusedComponent(UI.getCurrent());
    }

    boolean validateSave() {
        boolean flag = false;
        try {
            binder.getErrorDisplay().clearError();
            binder.getFields();
            binder.commit();

           
            StringBuilder errorMessage = new StringBuilder("Information for the following Mandatory fields need to be provided:" + ConstantsUtils.BREAK);
            if (binder.getField("formulaId").getValue().toString().isEmpty()) {
                if (flag) {
                    errorMessage.append(ConstantsUtils.COMMA);
                }
                errorMessage.append("Formula ID");
                flag = true;
            }
            if (binder.getField("formulaNo").getValue().toString().isEmpty()) {
                if (flag) {
                    errorMessage.append(", ");
                }
                errorMessage.append("Formula No");
                flag = true;
            }
            if (binder.getField(ConstantsUtils.FORMULA_NAME).getValue().toString().isEmpty()) {
                if (flag) {
                    errorMessage.append(", ");
                }
                errorMessage.append("Formula Name");
                flag = true;
            }
            if (deductions.getFormulaType().getId() == 0) {
                if (flag) {
                    errorMessage.append(", ");
                }
                errorMessage.append("Formula Type");
                flag = true;
            }

            if (flag) {
                errorMessage.append(ConstantsUtils.BREAK);
            }
          
            String sbMsg=salesBasis.isTableSelected();
            boolean sbFlag = !StringUtils.EMPTY.equals(sbMsg);
            boolean deductFlag = true;

            if (sbFlag) {
                errorMessage.append(sbMsg);
                flag = true;
            }

            if (!deductions.isTableSelected()) {
                if (sbFlag) {
                    errorMessage.append(ConstantsUtils.BREAK);
                }
                errorMessage.append("Select at least one Deduction in Deductions tab");
                flag = true;
                deductFlag = false;
            }

            if (deductFlag && logic.checkNsf(sessionDto, "INDICATOR_CHECK", "IMTD_DEDUCTION_DETAILS")) {
                    if (sbFlag) {
                        errorMessage.append(ConstantsUtils.BREAK);
                    }
                    errorMessage.append("Select +/- Indicator for selected deductions in Deductions tab");
                    flag = true;
                }
            if (flag) {
                binder.getErrorDisplay().setError(errorMessage.toString());
            }
       
        } catch (FieldGroup.CommitException ex) {
            LOGGER.error(ex);

        } catch (Exception exception) {
            LOGGER.error(exception);

        }
        return flag;
    }

    private void disableFields() {
        salesBasis.disableSalesFields();
        deductions.disableDeductionFields();
    }
    void saveNsf(){
         new AbstractNotificationUtils() {
                public void noMethod() {
                    // do nothing
                }

                @Override
                /**
                 * The method is triggered when Yes button of the message box is
                 * pressed .
                 *
                 * @param buttonId The buttonId of the pressed button.
                 */
                public void yesMethod() {
                    try {
                        String contractSelected = salesBasis.getContractSelected();
                        logic.saveNsf(sessionDto, binder, deductions.getFormulaType(), contractSelected, Integer.valueOf(salesBasis.getRuleSystemId()));
                        // To save Sales Basis Selections
                        if (!contractSelected.startsWith("Existing")) {
                            salesBasis.saveSalesBasisSelections(sessionDto.getSystemId());
                        }
                        // logic to navigate to edit mode once saved
                        logic.removeAllFromTempTable(sessionDto);
                        final Notification notif = new Notification(commonMsg.getSavedSuccessfulMessage(binder.getField("formulaId").getValue().toString(), binder.getField(ConstantsUtils.FORMULA_NAME).toString()), Notification.Type.HUMANIZED_MESSAGE);
                        notif.setPosition(Position.MIDDLE_CENTER);
                        notif.setStyleName("mystyle");
                        notif.show(Page.getCurrent());
                        sessionDto.setMode(ConstantsUtils.EDIT);
                        getUI().getNavigator().navigateTo(NSFView.NAME);
                    } catch (Exception ex) {
                        LOGGER.error(ex);
                    }
                }

            }.getConfirmationMessage("Save Confirmation", "Save record " + String.valueOf(binder.getField(ConstantsUtils.FORMULA_NAME).getValue()) + " ?");
  
    }
    void navigateToNsf() {
        try {
            AbstractSearchView.flag = false;
            logic.removeAllFromTempTable(sessionDto);
            getUI().getNavigator().navigateTo(AbstractSearchView.NAME);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }
    private void getResponsiveFirstTab() throws PortalException, SystemException {
             LOGGER.debug("Entering getFirstTab1");
        try {
            final StplSecurity stplSecurity = new StplSecurity();
            final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
            final Map<String, AppPermission> fieldItemHM = stplSecurity
                    .getFieldOrColumnPermission(userId, UISecurityUtil.NET_SALES_FORMULA + ConstantsUtils.COMMA + ConstantsUtils.NET_SALES_FORMULA_MAIN_TAB, false);
            List<Object> resultList = ifpLogic.getFieldsForSecurity(UISecurityUtil.NET_SALES_FORMULA, ConstantsUtils.NET_SALES_FORMULA_MAIN_TAB);
            securityLogic.removeComponentOnPermission(resultList, cssLayout, fieldItemHM, sessionDto.getMode());
       

        }catch(Exception ex){
            LOGGER.error(ex);
        }
       }
     void configureButtonPermission() throws PortalException, SystemException
          {
      
            final StplSecurity stplSecurity = new StplSecurity();
            final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
            final Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(userId, UISecurityUtil.NET_SALES_FORMULA+ConstantsUtils.COMMA+ConstantsUtils.NET_SALES_FORMULA_MAIN_TAB);
              if (functionHM.get("saveBtn") == null ||  !((AppPermission) functionHM.get("saveBtn")).isFunctionFlag()) {
  
                 saveBtn.setVisible(false);
              }
              if (functionHM.get("backBtn") == null ||   !((AppPermission) functionHM.get("backBtn")).isFunctionFlag()) {
                  backBtn.setVisible(false);
              }
          }
}
