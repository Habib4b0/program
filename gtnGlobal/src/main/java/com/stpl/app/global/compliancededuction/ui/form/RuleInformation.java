/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.compliancededuction.ui.form;

import com.stpl.app.global.abstractsearch.util.UIUtils;
import com.stpl.app.global.abstractsearch.util.ValidationUtil;
import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.global.common.util.CommonUtil;
import com.stpl.app.global.common.util.HelperListUtil;
import com.stpl.app.global.company.util.FiledNameUtils;
import com.stpl.app.global.compliancededuction.dto.CDRDto;
import com.stpl.app.global.compliancededuction.logic.CDRLogic;
import com.stpl.app.global.ifp.logic.IFPLogic;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.ui.errorhandling.ErrorLabel;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.UISecurityUtil;
import com.stpl.ifs.ui.CommonSecurityLogic;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.ui.util.converters.DataFormatConverter;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author mohamed.hameed
 */
public class RuleInformation extends CustomComponent {

    /**
     * The CsLayout Layout.
     */
    @UiField("buttonLayout")
    HorizontalLayout buttonLayout;

    @UiField("detailsTableLayout")
    public VerticalLayout detailsTableLayout;
    @UiField("ruleTypeLb")
    private Label ruleTypeLb;
    @UiField("ruleNoLb")
    private Label ruleNoLb;
    @UiField("ruleNameLb")
    private Label ruleNameLb;
    @UiField("ruleCategoryLb")
    private Label ruleCategoryLb;
    @UiField("ruleType")
    private ComboBox ruleType_DTO;
    @UiField("ruleNo")
    private TextField ruleNo;
    @UiField("ruleName")
    private TextField ruleName;
    @UiField("ruleCategory")
    private ComboBox ruleCategory_DTO;
    @UiField("lineTypeDdlb")
    private ComboBox lineTypeDdlb_DTO;
    @UiField("itemGroupDdlb")
    private ComboBox itemGroupDdlb;
    @UiField("keywordDdlb")
    private ComboBox keywordDdlb_DTO;
    @UiField("operatorDdlb")
    private ComboBox operatorDdlb_DTO;
    @UiField("comparisonDdlb")
    private ComboBox comparisonDdlb_DTO;
    @UiField("logicalOperatorDdlb")
    private ComboBox logicalOperatorDdlb__DTO;
    @UiField("valueText")
    private TextField valueText;
    @UiField("addBtn")
    private Button addBtn;
    @UiField("removeBtn")
    private Button removeBtn;
    @UiField("resetBtn")
    private Button resetBtn;
    private ExtFilterTable resultsTable = new ExtFilterTable();
    private BeanItemContainer<CDRDto> resultsContainer = new BeanItemContainer(CDRDto.class);
    public Object RULE_DETAILS_COLUMNS[] = new Object[]{
        "lineTypeDdlb", "itemGroupDdlb", "keywordDdlb", "operatorDdlb", "valueText", "comparisonDdlb", "logicalOperatorDdlb"};
    public String RULE_DETAILS_HEADERS[] = new String[]{"Line Type", "Item/Group/Association", "Keyword", "Operator", ConstantsUtils.VALUE, "Comparison", "Operator"};
    final CDRDto cdrBinerDto = new CDRDto();
    String noteshistory = new String();
    DecimalFormat percentFormat = new DecimalFormat("###,###,##0.00");
    DecimalFormat dollarFormat = new DecimalFormat("$###,###,##0.00");
    DataFormatConverter numericFormat = new DataFormatConverter("#,##0.00");

    public String getNoteshistory() {
        return noteshistory;
    }

    public void setNoteshistory(String noteshistory) {
        this.noteshistory = noteshistory;
    }

    /**
     * The common util.
     */
    private CommonUtil commonUtil = CommonUtil.getInstance();
    /**
     * The logger.
     */
    private static final Logger LOGGER = Logger.getLogger(RuleInformation.class);
    /**
     * The binder.
     */
    private ErrorfulFieldGroup binder = new ErrorfulFieldGroup(new BeanItem(cdrBinerDto));
    public ErrorLabel errorMsg = new ErrorLabel();
    SessionDTO sessionDTO;
    CommonSecurityLogic commonSecurityLogic = new CommonSecurityLogic();
    IFPLogic ifpLogic = new IFPLogic();
    List deletedRuleInfoIds = new ArrayList();
    HelperListUtil helperListUtil = HelperListUtil.getInstance();

    /**
     * Loading the content
     *
     * @return Component
     */
    public Component getContent(final ErrorfulFieldGroup binder, final SessionDTO sessionDTO) throws PortalException, SystemException {
        VerticalLayout layout = new VerticalLayout();
        layout.addComponent(Clara.create(getClass().getResourceAsStream("/clara/cdr/RuleInfo.xml"), this));
        this.binder = binder;
        this.sessionDTO = sessionDTO;
        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute("userId"));
        final Map<String, AppPermission> fieldCompanyHM = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.COMPLIANCE_DEDUCTION_RULES + ConstantsUtils.COMMA + ConstantsUtils.RULE_INFORMATION, false);
        final Map<String, AppPermission> functionCompanyHM = stplSecurity.getBusinessFunctionPermission(userId, UISecurityUtil.COMPLIANCE_DEDUCTION_RULES + ConstantsUtils.COMMA + ConstantsUtils.RULE_INFORMATION);
        layout.setMargin(true);
        layout.setSpacing(true);
        Panel panel = new Panel();
        panel.setContent(layout);
        getBinder();
        configurePermission(fieldCompanyHM, functionCompanyHM);
        configureFields();
        preLoadData();
        return panel;
    }

    /**
     * Configure Fields
     */
    public void configureFields() {
        ruleNoLb.addStyleName("nameAlign");
        ruleNameLb.addStyleName("nameAlign");
        ruleTypeLb.addStyleName("nameAlign");
        ruleCategoryLb.addStyleName("nameAlign");
        resultsTable.setWidth("100%");
        resultsTable.setSizeFull();
        resultsTable.setContainerDataSource(resultsContainer);
        resultsTable.setVisibleColumns(RULE_DETAILS_COLUMNS);
        resultsTable.setColumnHeaders(RULE_DETAILS_HEADERS);
        resultsTable.addStyleName(ConstantsUtils.FILTER_BAR);
        resultsTable.setFilterBarVisible(true);
        resultsTable.setFilterDecorator(new ExtDemoFilterDecorator());
        resultsTable.setSelectable(true);
        resultsTable.setPageLength(NumericConstants.FIVE);
        resultsTable.setSortDisabled(true);
        detailsTableLayout.addComponent(resultsTable);
        loadDdlb();
        valueText.setConverter(numericFormat);

    }

    private ErrorfulFieldGroup getBinder() {
        binder.setErrorDisplay(errorMsg);
        errorMsg.setId("ErrorMessage");
        binder.bindMemberFields(this);
        binder.setItemDataSource(new BeanItem(cdrBinerDto));
        binder.setBuffered(true);
        return binder;
    }

    public void addBtn() {
        addBtn.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                try {
                    binder.commit();
                    LOGGER.debug("errorMsg " + errorMsg);
                    if (requiredFieldCheck() && !isSameRuleAvailable()) {
                        int operatorint = 1;
                        List<CDRDto> cdrList = resultsContainer.getItemIds();
                        if (cdrList != null && !cdrList.isEmpty()) {
                            CDRDto cdrDto = cdrList.get(cdrList.size() - 1);
                            operatorint = cdrDto.getLogicalOperatorDdlb() == null ? 0 : cdrDto.getLogicalOperatorDdlb().getId();
                        }
                        if (operatorint == 0) {
                            AbstractNotificationUtils.getInfoNotification("Cannot Add Rule", "Last rule must have an Operator in order to add Rule. \n");

                        } else {
                            CDRDto tobeAdded = new CDRDto();
                            tobeAdded.setLineTypeDdlb(cdrBinerDto.getLineTypeDdlb_DTO());
                            tobeAdded.setItemGroupDdlb(cdrBinerDto.getItemGroupDdlb());
                            tobeAdded.setKeywordDdlb(cdrBinerDto.getKeywordDdlb_DTO());
                            tobeAdded.setOperatorDdlb(cdrBinerDto.getOperatorDdlb_DTO());
                            tobeAdded.setValueText(cdrBinerDto.getValueText() != null ? cdrBinerDto.getValueText() : StringUtils.EMPTY);
                            tobeAdded.setComparisonDdlb(cdrBinerDto.getComparisonDdlb_DTO());
                            if (tobeAdded.getComparisonDdlb() != null) {
                                tobeAdded.setValueText(cdrBinerDto.getValueText() != null ? percentFormat.format(Double.valueOf(cdrBinerDto.getValueText())) + "%" : StringUtils.EMPTY);
                            } else {
                                tobeAdded.setValueText(cdrBinerDto.getValueText() != null ? dollarFormat.format(Double.valueOf(cdrBinerDto.getValueText())) : StringUtils.EMPTY);
                            }
                            tobeAdded.setLogicalOperatorDdlb(cdrBinerDto.getLogicalOperatorDdlb_DTO());
                            resultsContainer.addBean(tobeAdded);
                        }
                    }
                } catch (FieldGroup.CommitException ex) {
                    LOGGER.error(ex);
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            }
        });
    }

    public void removeBtn() {
        removeBtn.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (resultsTable.getValue() != null) {
                    MessageBox.showPlain(Icon.QUESTION, "Delete Confirmation", "Are you sure you want to delete the selected Rule Record?\n", new MessageBoxListener() {
                        /**
                         * Called when a Button has been clicked .
                         *
                         */
                        @SuppressWarnings("PMD")
                        public void buttonClicked(final ButtonId buttonId) {
                            if (buttonId.name().equals("YES")) {
                                try {
                                    CDRDto eventDto = (CDRDto) resultsTable.getValue();
                                    resultsContainer.removeItem(eventDto);
                                    resultsTable.setValue(null);
                                    if (ConstantsUtils.EDIT.equals(sessionDTO.getMode()) && eventDto.getCdrDetailsSid() != 0) {
                                        deletedRuleInfoIds.add(eventDto.getCdrDetailsSid());
                                    }
                                } catch (Exception ex) {
                                    java.util.logging.Logger.getLogger(RuleInformation.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }
                    }, ButtonId.YES, ButtonId.NO);

                } else {
                    AbstractNotificationUtils.getErrorNotification("No Record Selected", "Please select a rule to delete");
                }
            }
        });
    }

    public void resetBtn() {
        resetBtn.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                LOGGER.debug(" Reset Method starts");

                MessageBox.showPlain(Icon.QUESTION, "Reset Confirmation", "Are you sure you "
                        + "want to reset the values in the ‘Rule Details’ "
                        + "group box?", new MessageBoxListener() {
                    /**
                     * Called when a Button has been clicked .
                     *
                     */
                    @SuppressWarnings("PMD")
                    public void buttonClicked(final ButtonId buttonId) {
                        if (buttonId.name().equals("YES")) {
                            try {
                                lineTypeDdlb_DTO.select(null);
                                keywordDdlb_DTO.select(null);
                                operatorDdlb_DTO.select(null);
                                comparisonDdlb_DTO.select(null);
                                logicalOperatorDdlb__DTO.select(null);
                                valueText.setValue(StringUtils.EMPTY);
                                if (resultsContainer.size() != 0) {
                                    resultsContainer.removeAllItems();
                                    resultsTable.removeAllItems();
                                }
                                if (sessionDTO.getSystemId() != 0) {
                                    loadSavedRulesDetails(CDRLogic.getSavedRuleDetails(sessionDTO.getSystemId()));
                                }
                            } catch (Exception ex) {
                                java.util.logging.Logger.getLogger(RuleInformation.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }, ButtonId.YES, ButtonId.NO);

                LOGGER.debug(" Reset Method ends");
            }
        });
    }

    /**
     * Is Same Rules Available
     *
     * @return
     */
    private boolean isSameRuleAvailable() {
        for (CDRDto object : resultsContainer.getItemIds()) {
            String cdrValue = object.getValueText().replace("$", StringUtils.EMPTY).replace("%", StringUtils.EMPTY).replace(",", StringUtils.EMPTY);

            if (object.getLineTypeDdlb().getId() == cdrBinerDto.getLineTypeDdlb_DTO().getId()
                    && object.getItemGroupDdlb().getId() == cdrBinerDto.getItemGroupDdlb().getId()
                    && object.getKeywordDdlb().getId() == cdrBinerDto.getKeywordDdlb_DTO().getId()
                    && object.getOperatorDdlb().getId() == cdrBinerDto.getOperatorDdlb_DTO().getId()
                    && cdrValue.equals(StringUtils.isBlank(cdrBinerDto.getValueText()) ? StringUtils.EMPTY : cdrBinerDto.getValueText())
                    && (object.getComparisonDdlb() == null ? 0 : object.getComparisonDdlb().getId()) == (cdrBinerDto.getComparisonDdlb_DTO() == null ? 0 : cdrBinerDto.getComparisonDdlb_DTO().getId())
                    && (object.getLogicalOperatorDdlb() == null ? 0 : object.getLogicalOperatorDdlb().getId()) == (cdrBinerDto.getLogicalOperatorDdlb_DTO() == null ? 0 : cdrBinerDto.getLogicalOperatorDdlb_DTO().getId())) {
                AbstractNotificationUtils.getInfoNotification("Duplicate Rules", "You cannot add two of the same rules. The rule you are trying to add already exists. \n");
                return true;
            }
        }
        return false;
    }

    public boolean requiredFieldCheck() {
        String fieldName = StringUtils.isBlank(cdrBinerDto.getLineTypeDdlb_DTO() != null ? cdrBinerDto.getLineTypeDdlb_DTO().toString() : StringUtils.EMPTY) ? "Line Type"
                : (cdrBinerDto.getItemGroupDdlb().getId() == 0) ? "Item/Group/MS Association"
                        : StringUtils.isBlank(cdrBinerDto.lineTypeDdlb_DTO != null ? cdrBinerDto.lineTypeDdlb_DTO.toString() : StringUtils.EMPTY) ? "Line Type"
                        : StringUtils.isBlank(cdrBinerDto.getKeywordDdlb_DTO() != null ? cdrBinerDto.getKeywordDdlb_DTO().toString() : StringUtils.EMPTY) ? "Keyword"
                        : StringUtils.isBlank(cdrBinerDto.getOperatorDdlb_DTO() != null ? cdrBinerDto.getOperatorDdlb_DTO().toString() : StringUtils.EMPTY) ? "Operator"
                        : StringUtils.isBlank(cdrBinerDto.valueText != null ? cdrBinerDto.valueText : StringUtils.EMPTY) ? ConstantsUtils.VALUE
                        : StringUtils.EMPTY;
        if (!fieldName.isEmpty()) {
            AbstractNotificationUtils.getErrorNotification("Missing Required Fields", "Please check that all required fields are populated. \n"
                    + fieldName + " has no value. \n");
            return false;
        }
        return true;
    }

    /**
     * Fields Validations
     */
    public void fieldValidation() {
        lineTypeDdlb_DTO.addValidator(new RegexpValidator(ValidationUtil.getMessage("emptyFieldCheck"), ValidationUtil.getMessage("emptyFieldValidationMsg").replace("<>", "Line Type")));
        itemGroupDdlb.addValidator(new RegexpValidator(ValidationUtil.getMessage("emptyFieldCheck"), ValidationUtil.getMessage("emptyFieldValidationMsg").replace("<>", "Item/Group/MS Association")));
        keywordDdlb_DTO.addValidator(new RegexpValidator(ValidationUtil.getMessage("emptyFieldCheck"), ValidationUtil.getMessage("emptyFieldValidationMsg").replace("<>", "Keyword")));
        operatorDdlb_DTO.addValidator(new RegexpValidator(ValidationUtil.getMessage("emptyFieldCheck"), ValidationUtil.getMessage("emptyFieldValidationMsg").replace("<>", "Operator")));
        valueText.addValidator(new RegexpValidator(ValidationUtil.getMessage("emptyFieldCheck"), ValidationUtil.getMessage("emptyFieldValidationMsg").replace("<>", ConstantsUtils.VALUE)));
    }

    private void loadDdlb() {
        commonUtil.loadComboBox(ruleType_DTO, UIUtils.RULE_TYPE, false);
        commonUtil.loadComboBox(ruleCategory_DTO, UIUtils.RULE_CATEGORY, false);
        commonUtil.loadComboBox(lineTypeDdlb_DTO, UIUtils.LINE_TYPE, false);
        commonUtil.loadComboBox(itemGroupDdlb, UIUtils.ITEM_GROUP_MS_ASSOCIATION, false);
        commonUtil.loadComboBox(keywordDdlb_DTO, UIUtils.KEYWORD, false);
        commonUtil.loadComboBox(operatorDdlb_DTO, UIUtils.OPERATOR, false);
        commonUtil.loadComboBox(comparisonDdlb_DTO, UIUtils.COMPARISON, false);
        commonUtil.loadComboBox(logicalOperatorDdlb__DTO, UIUtils.LOGICAL_OPERATOR, false);
        List<HelperDTO> dtoList = helperListUtil.getListNameMap().get(UIUtils.ITEM_GROUP_MS_ASSOCIATION);
        if (dtoList != null && !dtoList.isEmpty()) {
            final HelperDTO defaultValue = dtoList.get(0);
            itemGroupDdlb.select(defaultValue);
        }
        itemGroupDdlb.setEnabled(false);
    }

    /**
     * Get Rule Informations
     *
     * @return
     * @throws Exception
     */
    public List getRuleInformations() {
        List list = new ArrayList();
        try {
            binder.getErrorDisplay().clearError();
            binder.commit();
            list.add(cdrBinerDto);
            list.add(resultsContainer.getItemIds());
        } catch (Exception ex) {
            LOGGER.error(ex);
            LOGGER.error(ex);
        }
        return list;
    }

    /**
     * Get Rule Informations
     *
     * @return
     * @throws Exception
     */
    public List getDeletedRuleInformations() {

        return deletedRuleInfoIds;
    }

    public void setDeletedRuleInformations(List deletedRuleInfoIds) {

        this.deletedRuleInfoIds = deletedRuleInfoIds;
    }

    /**
     * load omn edit view and copy
     */
    public void preLoadData() {
        if (!sessionDTO.getMode().equals(ConstantsUtils.ADD)) {
            List list = CDRLogic.getSavedRuleDetails(sessionDTO.getSystemId());
            loadSavedRuleInfo((Object[]) list.get(0));
            loadSavedRulesDetails(list);
            if (sessionDTO.getMode().equals(ConstantsUtils.VIEW)) {
                enableDisableFields(false);
            } else {
                enableDisableFields(true);
            }
        }
    }

    /**
     * Prepopulate values While Edit
     */
    private void loadSavedRuleInfo(Object[] objects) {
        HelperDTO ruleType = new HelperDTO();
        HelperDTO ruleCategory = new HelperDTO();
        ruleType.setId(objects[0] != null ? (Integer) objects[0] : 0);
        ruleType.setDescription(objects[1] != null || !String.valueOf(objects[1]).equals(ConstantsUtils.SELECT_ONE) ? (String) objects[1] : ConstantsUtils.SELECT_ONE);
        if (!sessionDTO.getMode().equals(ConstantsUtils.COPY)) {
            ruleType_DTO.setValue(ruleType);
        }
        if (sessionDTO.getMode().equals(ConstantsUtils.COPY)) {
            ruleType_DTO.select(null);
            ruleNo.setValue(StringUtils.EMPTY);
            ruleName.setValue(StringUtils.EMPTY);
            ruleCategory.setId(objects[NumericConstants.FOUR] != null ? (Integer) objects[NumericConstants.FOUR] : 0);
            ruleCategory.setDescription(objects[NumericConstants.FIVE] != null || !String.valueOf(objects[NumericConstants.FIVE]).equals(ConstantsUtils.SELECT_ONE) ? (String) objects[NumericConstants.FIVE] : ConstantsUtils.SELECT_ONE);
            ruleCategory_DTO.setValue(ruleCategory);
        } else {
            ruleNo.setValue(objects[NumericConstants.TWO] != null ? (String) objects[NumericConstants.TWO] : StringUtils.EMPTY);
            ruleName.setValue(objects[NumericConstants.THREE] != null ? (String) objects[NumericConstants.THREE] : StringUtils.EMPTY);
            ruleCategory.setId(objects[NumericConstants.FOUR] != null ? (Integer) objects[NumericConstants.FOUR] : 0);
            ruleCategory.setDescription(objects[NumericConstants.FIVE] != null || !String.valueOf(objects[NumericConstants.FIVE]).equals(ConstantsUtils.SELECT_ONE) ? (String) objects[NumericConstants.FIVE] : ConstantsUtils.SELECT_ONE);
            ruleCategory_DTO.setValue(ruleCategory);
        }
        setNoteshistory((objects[NumericConstants.FOURTEEN] != null && !"null".equals(objects[NumericConstants.FOURTEEN])) ? (String) objects[NumericConstants.FOURTEEN] : StringUtils.EMPTY);
    }

    public void loadSavedRulesDetails(List<Object[]> list) {
        if (resultsContainer != null) {
            resultsContainer.removeAllItems();
            resultsTable.removeAllItems();
        }
        List<CDRDto> ruleDetails = new ArrayList<>();
        for (Object[] objects : list) {
            CDRDto ruleDetailsDto = new CDRDto();
            ruleDetailsDto.setLineTypeDdlb(helperListUtil.getIdHelperDTOMap().get(objects[NumericConstants.SIX] != null ? Integer.valueOf(String.valueOf(objects[NumericConstants.SIX])) : 0));
            ruleDetailsDto.setItemGroupDdlb(helperListUtil.getIdHelperDTOMap().get(objects[NumericConstants.SEVEN] != null ? Integer.valueOf(String.valueOf(objects[NumericConstants.SEVEN])) : 0));
            ruleDetailsDto.setKeywordDdlb(helperListUtil.getIdHelperDTOMap().get(objects[NumericConstants.EIGHT] != null ? Integer.valueOf(String.valueOf(objects[NumericConstants.EIGHT])) : 0));
            ruleDetailsDto.setOperatorDdlb(helperListUtil.getIdHelperDTOMap().get(objects[NumericConstants.NINE] != null ? Integer.valueOf(String.valueOf(objects[NumericConstants.NINE])) : 0));

            ruleDetailsDto.setComparisonDdlb(helperListUtil.getIdHelperDTOMap().get(objects[NumericConstants.ELEVEN] != null ? Integer.valueOf(String.valueOf(objects[NumericConstants.ELEVEN])) : 0));
            if (ruleDetailsDto.getComparisonDdlb().getId() == 0) {
                ruleDetailsDto.setValueText(objects[NumericConstants.TEN] != null ? dollarFormat.format(Double.valueOf(String.valueOf(objects[NumericConstants.TEN]))) : StringUtils.EMPTY);
            } else {
                ruleDetailsDto.setValueText(objects[NumericConstants.TEN] != null ? percentFormat.format(Double.valueOf(String.valueOf(objects[NumericConstants.TEN]))) + "%" : StringUtils.EMPTY);
            }
            ruleDetailsDto.setLogicalOperatorDdlb(helperListUtil.getIdHelperDTOMap().get(objects[NumericConstants.TWELVE] != null ? Integer.valueOf(String.valueOf(objects[NumericConstants.TWELVE])) : 0));

            if (ConstantsUtils.COPY.equals(sessionDTO.getMode())) {
                ruleDetailsDto.setCdrDetailsSid(0);
            } else {
                ruleDetailsDto.setCdrDetailsSid(Integer.valueOf(String.valueOf(objects[NumericConstants.THIRTEEN])));
            }
            ruleDetails.add(ruleDetailsDto);
        }
        resultsContainer.addAll(ruleDetails);
    }

    /**
     * Set Enable and Disable based on the mode
     *
     * @param isEnable
     */
    private void enableDisableFields(final boolean isEnable) {
        ruleType_DTO.setEnabled(isEnable);
        ruleNo.setEnabled(isEnable);
        ruleName.setEnabled(isEnable);
        ruleCategory_DTO.setEnabled(isEnable);
        lineTypeDdlb_DTO.setEnabled(isEnable);
        keywordDdlb_DTO.setEnabled(isEnable);
        operatorDdlb_DTO.setEnabled(isEnable);
        valueText.setEnabled(isEnable);
        comparisonDdlb_DTO.setEnabled(isEnable);
        logicalOperatorDdlb__DTO.setEnabled(isEnable);
        addBtn.setEnabled(isEnable);
        removeBtn.setEnabled(isEnable);
        resetBtn.setEnabled(isEnable);
        resultsTable.setSelectable(isEnable);
    }

    void resetMethod() {
        LOGGER.debug("Add reset Method");
        if (resultsContainer != null) {
            resultsContainer.removeAllItems();
            resultsTable.removeAllItems();

        }
        ruleNo.setValue(StringUtils.EMPTY);
        ruleName.setValue(StringUtils.EMPTY);
        ruleType_DTO.select(null);
        ruleCategory_DTO.select(null);
        lineTypeDdlb_DTO.select(null);
        keywordDdlb_DTO.select(null);
        operatorDdlb_DTO.select(null);
        comparisonDdlb_DTO.select(null);
        logicalOperatorDdlb__DTO.select(null);
        valueText.setValue(StringUtils.EMPTY);
        LOGGER.debug("Add reset Method ends");
    }

    private void configurePermission(final Map<String, AppPermission> fieldCompanyHM, final Map<String, AppPermission> functionHM) {

        LOGGER.debug("Entering configurePermission");
        try {
            String mode = sessionDTO.getMode();

            List<Object> resultList = ifpLogic.getFieldsForSecurity(UISecurityUtil.COMPLIANCE_DEDUCTION_RULES, ConstantsUtils.RULE_INFORMATION);
            commonSecurityLogic.removeComponentOnPermission(resultList, buttonLayout, fieldCompanyHM, mode);

            if (functionHM.get("addBtn") != null && ((AppPermission) functionHM.get("addBtn")).isFunctionFlag()) {
                addBtn();
            } else {
                addBtn.setVisible(false);
            }
            if (functionHM.get("removeBtn") != null && ((AppPermission) functionHM.get("removeBtn")).isFunctionFlag()) {

                removeBtn();
            } else {
                removeBtn.setVisible(false);
            }
            if (functionHM.get("tableResetBtn") != null && ((AppPermission) functionHM.get("tableResetBtn")).isFunctionFlag()) {
                resetBtn();
            } else {
                removeBtn.setVisible(false);
            }

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.debug("Ending configurePermission");

    }
}
