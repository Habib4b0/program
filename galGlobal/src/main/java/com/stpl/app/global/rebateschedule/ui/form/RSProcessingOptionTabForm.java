package com.stpl.app.global.rebateschedule.ui.form;

import com.stpl.app.global.common.util.CommonUtil;
import com.stpl.app.global.ifp.logic.IFPLogic;
import com.stpl.app.global.rebateschedule.dto.RulesDTO;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.CommonUIUtils;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.GeneralCommonUtils;
import com.stpl.app.util.UISecurityUtil;
import com.stpl.ifs.ui.CommonSecurityLogic;
import com.stpl.ifs.util.TableResultCustom;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import java.util.List;
import java.util.Map;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;

public class RSProcessingOptionTabForm extends CustomComponent implements View {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private static final Logger LOGGER = Logger.getLogger(RSProcessingOptionTabForm.class);
    @UiField("validationProfile")
    private ComboBox validationProfile;
       
    @UiField("rebateProcessingType")
    private ComboBox rebateProcessingTypeDDLB;
     
    @UiField("interestBearingIndicator")
    private ComboBox interestBearingIndicatorDDLB;
    
    @UiField("interestBearingBasis")
    private ComboBox interestBearingBasisDDLB;
    
    private final ErrorfulFieldGroup binder;
    @UiField("availableRulesTable")
    private ExtFilterTable availableRulesTable;
    @UiField("selectedRulesTable")
    private ExtFilterTable selectedRulesTable;
    @UiField("moveRight")
    private Button moveRight;
    @UiField("moveLeft")
    private Button moveLeft;
    @UiField("moveAll")
    private Button moveAll;
    @UiField("removeAll")
    private Button removeAll;
    @UiField("verticalButtonLayout")
    private VerticalLayout verticalButtonLayout;
    @UiField("hlayout2")
    private HorizontalLayout hlayout2;
    @UiField("cssLayout")
    CssLayout cssLayout;
    
    @UiField("availableRecordCountLB")
    private Label availableRecordCountLB;
    
    @UiField("selectedRecordCountLB")
    private Label selectedRecordCountLB;
    
    CommonUIUtils commonUiUtil = new CommonUIUtils();
    CommonSecurityLogic commonSecurityLogic = new CommonSecurityLogic();
    private String userId;
    private final IFPLogic ifpLogic = new IFPLogic();

    private final String mode;
    private final boolean isViewMode;
    private BeanItemContainer<RulesDTO> availableBean = new BeanItemContainer<RulesDTO>(RulesDTO.class);

    public RSProcessingOptionTabForm(final ErrorfulFieldGroup binder, String mode) {
        this.binder = binder;
        this.mode = mode;
        this.isViewMode = ConstantsUtils.VIEW.equals(mode);
        try {
            setCompositionRoot(Clara.create(getClass().getResourceAsStream("/clara/rebateschedule/processingoptiontabform.xml"), this));
            getBinder();
            addToContent();
            configureFields();
            final StplSecurity stplSecurity = new StplSecurity();
            userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
            final Map<String, AppPermission> fieldRsHM = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.REBATE_SCHEDULE+","+"Processing Option",false);
            addResponsiveness(fieldRsHM);
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    private void addToContent() throws Exception {
        inclusionExclusionSection();

    }

    private ErrorfulFieldGroup getBinder() {
        binder.bindMemberFields(this);
        binder.setBuffered(true);
        return binder;
    }

    private void addResponsiveness(final Map<String, AppPermission> fieldRsHM) throws Exception {
        LOGGER.info("Entering configurePermission");
        try {
            List<Object> resultList = ifpLogic.getFieldsForSecurity(UISecurityUtil.REBATE_SCHEDULE, "Processing Option");
            commonSecurityLogic.removeComponentOnPermission(resultList, cssLayout, fieldRsHM, mode);
        } catch (Exception ex) {
            LOGGER.error(ex);
            
        }
        LOGGER.info("Ending configurePermission");

    }

    public void inclusionExclusionSection() {
        try {
            final StplSecurity stplSecurity = new StplSecurity();
            userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
            final Map<String, AppPermission> fieldRsHM = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.REBATE_SCHEDULE+","+"Processing Option",false);
            List<Object> resultList = ifpLogic.getFieldsForSecurity(UISecurityUtil.REBATE_SCHEDULE, "Processing Option");
            Object[] obj = {"ruleNo", "ruleName", "ruleVersion"};
            TableResultCustom tableResultCustom = commonSecurityLogic.getTableColumnsPermission(resultList, obj, fieldRsHM, mode);
            if (tableResultCustom.getObjResult().length == 0) {
                availableRulesTable.setVisible(false);
                selectedRulesTable.setVisible(false);
            }
            availableRulesTable.setHeight("150px");
            availableRulesTable.setCaption("Available Rules");
            availableRulesTable.setContainerDataSource(availableBean);
            availableRulesTable.setVisibleColumns(tableResultCustom.getObjResult());
            availableRulesTable.setColumnHeaders(tableResultCustom.getObjResultHeader());
            availableRulesTable.setWidth("350px");
            moveRight.setWidth(ConstantsUtils.WIDTH_50);
            moveLeft.setWidth(ConstantsUtils.WIDTH_50);
            moveAll.setWidth(ConstantsUtils.WIDTH_50);
            removeAll.setWidth(ConstantsUtils.WIDTH_50);
            verticalButtonLayout.setComponentAlignment(moveRight, Alignment.MIDDLE_CENTER);
            verticalButtonLayout.setComponentAlignment(moveLeft, Alignment.MIDDLE_CENTER);
            verticalButtonLayout.setComponentAlignment(moveAll, Alignment.MIDDLE_CENTER);
            verticalButtonLayout.setComponentAlignment(removeAll, Alignment.MIDDLE_CENTER);
            verticalButtonLayout.addStyleName("NavButtonsForIFP");
            verticalButtonLayout.addStyleName("dataTransferGridForIFP");

            selectedRulesTable.setContainerDataSource(availableBean);
            selectedRulesTable.setVisibleColumns(tableResultCustom.getObjResult());
            selectedRulesTable.setColumnHeaders(tableResultCustom.getObjResultHeader());

            selectedRulesTable.setHeight("150px");
            selectedRulesTable.setWidth("350px");
            selectedRulesTable.setCaption("Selected Rules");
            hlayout2.setComponentAlignment(verticalButtonLayout, Alignment.MIDDLE_CENTER);
            
            availableRulesTable.setColumnHeader("ruleVersion", "Version");
            selectedRulesTable.setColumnHeader("ruleVersion", "Version");
            
        } catch (Exception e) {
            LOGGER.error(e);

        }
    }

    private void configureFields() throws SystemException, Exception {
        
        // TODO Auto-generated method stub
        moveRight.setCaption(">");
        moveLeft.setCaption("<");
        moveAll.setCaption(">>");
        removeAll.setCaption("<<");

        LOGGER.info("In configureFields lodding-ValidationProfile Dblb ListType=" + GeneralCommonUtils.REBATE_VALIDATION_PROFILE);
        CommonUtil commonUtil = CommonUtil.getInstance();
        commonUtil.loadComboBox(validationProfile, GeneralCommonUtils.REBATE_VALIDATION_PROFILE, false);
        commonUtil.loadComboBox(interestBearingBasisDDLB, "INTEREST_BEARING_BASIS", false);
        commonUtil.loadComboBox(interestBearingIndicatorDDLB, "INTEREST_BEARING_INDICATOR", false);
        commonUtil.loadComboBox(rebateProcessingTypeDDLB, "RS_PROCESSING_TYPE", false);
               
        availableRecordCountLB.setCaption("Total Record Count: 0");
        selectedRecordCountLB.setCaption("Total Record Count: 0");    
        availableRecordCountLB.setStyleName("noStyle");
        selectedRecordCountLB.setStyleName("noStyle");
        
        if(isViewMode){
        validationProfile.setEnabled(!isViewMode);
        interestBearingBasisDDLB.setEnabled(!isViewMode);
        interestBearingIndicatorDDLB.setEnabled(!isViewMode);
        rebateProcessingTypeDDLB.setEnabled(!isViewMode);
        moveRight.setEnabled(!isViewMode);
        moveLeft.setEnabled(!isViewMode);
        moveAll.setEnabled(!isViewMode);
        removeAll.setEnabled(!isViewMode);
        availableRulesTable.setEnabled(!isViewMode);
        selectedRulesTable.setEnabled(!isViewMode);
        }
    }

    @Override
    public void enter(ViewChangeEvent event) {
        // TODO Auto-generated method stub

    }

    /**
     * To reset the processing options tab.
     */
    public void resetProcessingOptions() {        
        if (!String.valueOf(mode).equals(ConstantsUtils.EDIT)) {
            validationProfile.select(null);
            interestBearingBasisDDLB.select(null);
            interestBearingIndicatorDDLB.select(null);
            rebateProcessingTypeDDLB.select(null);
        }            
        
    }
    
}
