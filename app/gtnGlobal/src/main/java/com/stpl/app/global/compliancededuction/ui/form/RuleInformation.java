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
import com.stpl.app.global.compliancededuction.dto.CDRDto;
import com.stpl.app.global.compliancededuction.logic.CDRLogic;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.ui.errorhandling.ErrorLabel;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.CommonUIUtils;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.UISecurityUtil;
import com.stpl.ifs.ui.CommonSecurityLogic;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.ui.util.converters.DataFormatConverter;
import com.stpl.ifs.util.HelperDTO;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.vaadin.v7.data.fieldgroup.FieldGroup;
import com.vaadin.v7.data.util.BeanItem;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.data.validator.RegexpValidator;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.v7.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.ui.TextField;
import com.vaadin.v7.ui.VerticalLayout;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;

/**
 *
 * @author mohamed.hameed
 */
public class RuleInformation extends CustomComponent {

    public RuleInformation() {
        super();
    }

    /**
     * The CsLayout Layout.
     */
    @UiField("buttonLayout")
    private HorizontalLayout buttonLayout;

    @UiField("detailsTableLayout")
    private VerticalLayout detailsTableLayout;
    @UiField("ruleTypeLb")
    private Label ruleTypeLb;
    @UiField("ruleNoLb")
    private Label ruleNoLb;
    @UiField("ruleNameLb")
    private Label ruleNameLb;
    @UiField("ruleCategoryLb")
    private Label ruleCategoryLb;
    @UiField("ruleType")
    private ComboBox ruleTypeDto;
    @UiField("ruleNo")
    private TextField ruleNo;
    @UiField("ruleName")
    private TextField ruleName;
    @UiField("ruleCategory")
    private ComboBox ruleCategoryDto;
    @UiField("lineTypeDdlb")
    private ComboBox lineTypeDdlbDto;
    @UiField("itemGroupDdlb")
    private ComboBox itemGroupDdlb;
    @UiField("keywordDdlb")
    private ComboBox keywordDdlbDto;
    @UiField("operatorDdlb")
    private ComboBox operatorDdlbDto;
    @UiField("comparisonDdlb")
    private ComboBox comparisonDdlbDto;
    @UiField("logicalOperatorDdlb")
    private ComboBox logicalOperatorDdlbDto;
    @UiField("valueText")
    private TextField valueText;
    @UiField("addBtn")
    private Button addBtn;
    @UiField("removeBtn")
    private Button removeBtn;
    @UiField("resetBtn")
    private Button resetBtn;
    private final ExtFilterTable resultsTable = new ExtFilterTable();
    private final BeanItemContainer<CDRDto> resultsContainer = new BeanItemContainer(CDRDto.class);
    private static final Object RULE_DETAILS_COLUMNS[] = new Object[]{
        "lineTypeDdlb", "itemGroupDdlb", "keywordDdlb", "operatorDdlb", "valueText", "comparisonDdlb", "logicalOperatorDdlb"};
    
    public static final String RULE_DETAILS_HEADERS[] = new String[]{ConstantsUtils.LINE_TYPE_LABEL, "Item/Group/Association", ConstantsUtils.KEYWORD, ConstantsUtils.OPERATOR, ConstantsUtils.VALUE, "Comparison", ConstantsUtils.OPERATOR};
    
    private final CDRDto cdrBinerDto = new CDRDto();
    private String noteshistory = new String();
    private final DecimalFormat percentFormat = new DecimalFormat("###,###,##0.00");
    private final DecimalFormat dollarFormat = new DecimalFormat("$###,###,##0.00");
    private final DataFormatConverter numericFormat = new DataFormatConverter("#,##0.00");

    public String getNoteshistory() {
        return noteshistory;
    }

    public void setNoteshistory(String noteshistory) {
        this.noteshistory = noteshistory;
    }

    /**
     * The common util.
     */
    private final CommonUtil commonUtil = CommonUtil.getInstance();
    /**
     * The logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(RuleInformation.class);
    /**
     * The binder.
     */
    private ErrorfulFieldGroup binder = new ErrorfulFieldGroup(new BeanItem(cdrBinerDto));
    private final ErrorLabel errorMsg = new ErrorLabel();
    private SessionDTO sessionDTO;
    private final CommonSecurityLogic commonSecurityLogic = new CommonSecurityLogic();
    private final CommonUIUtils commonUIUtils = new CommonUIUtils();
    private List deletedRuleInfoIds = new ArrayList();
    private final HelperListUtil helperListUtil = HelperListUtil.getInstance();

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
        ruleNoLb.addStyleName(ConstantsUtils.NAME_ALIGN);
        ruleNameLb.addStyleName(ConstantsUtils.NAME_ALIGN);
        ruleTypeLb.addStyleName(ConstantsUtils.NAME_ALIGN);
        ruleCategoryLb.addStyleName(ConstantsUtils.NAME_ALIGN);
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
                            tobeAdded.setLineTypeDdlb(cdrBinerDto.getLineTypeDdlbDto());
                            tobeAdded.setItemGroupDdlb(cdrBinerDto.getItemGroupDdlb());
                            tobeAdded.setKeywordDdlb(cdrBinerDto.getKeywordDdlbDto());
                            tobeAdded.setOperatorDdlb(cdrBinerDto.getOperatorDdlbDto());
                            tobeAdded.setValueText(cdrBinerDto.getValueText() != null ? cdrBinerDto.getValueText() : StringUtils.EMPTY);
                            tobeAdded.setComparisonDdlb(cdrBinerDto.getComparisonDdlbDto());
                            if (tobeAdded.getComparisonDdlb() != null) {
                                tobeAdded.setValueText(cdrBinerDto.getValueText() != null ? percentFormat.format(Double.valueOf(cdrBinerDto.getValueText())) + "%" : StringUtils.EMPTY);
                            } else {
                                tobeAdded.setValueText(cdrBinerDto.getValueText() != null ? dollarFormat.format(Double.valueOf(cdrBinerDto.getValueText())) : StringUtils.EMPTY);
                            }
                            tobeAdded.setLogicalOperatorDdlb(cdrBinerDto.getLogicalOperatorDdlbDto());
                            resultsContainer.addBean(tobeAdded);
                        }
                    }
                } catch (FieldGroup.CommitException | NumberFormatException ex) {
                    LOGGER.error(ex.getMessage());
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
                        @Override
                        public void buttonClicked(final ButtonId buttonId) {
                            if (buttonId.name().equals("YES")) {
                                try {
                                    CDRDto eventDto = (CDRDto) resultsTable.getValue();
                                    resultsContainer.removeItem(eventDto);
                                    resultsTable.setValue(null);
                                    if (ConstantsUtils.EDIT.equals(sessionDTO.getMode()) && eventDto.getCdrDetailsSid() != 0) {
                                        deletedRuleInfoIds.add(eventDto.getCdrDetailsSid());
                                    }
                                } catch (Property.ReadOnlyException ex) {
                                    LOGGER.error(ex.getMessage());
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
                    @Override
                    public void buttonClicked(final ButtonId buttonId) {
                        if (buttonId.name().equals("YES")) {
                            try {
                                lineTypeDdlbDto.select(null);
                                keywordDdlbDto.select(null);
                                operatorDdlbDto.select(null);
                                comparisonDdlbDto.select(null);
                                logicalOperatorDdlbDto.select(null);
                                valueText.setValue(StringUtils.EMPTY);
                                if (resultsContainer.size() != 0) {
                                    resultsContainer.removeAllItems();
                                    resultsTable.removeAllItems();
                                }
                                if (sessionDTO.getSystemId() != 0) {
                                    loadSavedRulesDetails(CDRLogic.getSavedRuleDetails(sessionDTO.getSystemId()));
                                }
                            } catch (Property.ReadOnlyException ex) {
                                LOGGER.error(ex.getMessage());
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

            if (object.getLineTypeDdlb().getId() == cdrBinerDto.getLineTypeDdlbDto().getId()
                    && object.getItemGroupDdlb().getId() == cdrBinerDto.getItemGroupDdlb().getId()
                    && object.getKeywordDdlb().getId() == cdrBinerDto.getKeywordDdlbDto().getId()
                    && object.getOperatorDdlb().getId() == cdrBinerDto.getOperatorDdlbDto().getId()
                    && cdrValue.equals(StringUtils.isBlank(cdrBinerDto.getValueText()) ? StringUtils.EMPTY : cdrBinerDto.getValueText())
                    && (object.getComparisonDdlb() == null ? 0 : object.getComparisonDdlb().getId()) == (cdrBinerDto.getComparisonDdlbDto() == null ? 0 : cdrBinerDto.getComparisonDdlbDto().getId())
                    && (object.getLogicalOperatorDdlb() == null ? 0 : object.getLogicalOperatorDdlb().getId()) == (cdrBinerDto.getLogicalOperatorDdlbDto() == null ? 0 : cdrBinerDto.getLogicalOperatorDdlbDto().getId())) {
                AbstractNotificationUtils.getInfoNotification("Duplicate Rules", "You cannot add two of the same rules. The rule you are trying to add already exists. \n");
                return true;
            }
        }
        return false;
    }

    public boolean requiredFieldCheck() {
        String fieldName = StringUtils.isBlank(cdrBinerDto.getLineTypeDdlbDto() != null ? cdrBinerDto.getLineTypeDdlbDto().toString() : StringUtils.EMPTY) ? ConstantsUtils.LINE_TYPE_LABEL
                : (cdrBinerDto.getItemGroupDdlb().getId() == 0) ? "Item/Group/MS Association"
                        : StringUtils.isBlank(cdrBinerDto.getLineTypeDdlbDto() != null ? cdrBinerDto.getLineTypeDdlbDto().toString() : StringUtils.EMPTY) ? ConstantsUtils.LINE_TYPE_LABEL
                        : StringUtils.isBlank(cdrBinerDto.getKeywordDdlbDto() != null ? cdrBinerDto.getKeywordDdlbDto().toString() : StringUtils.EMPTY) ? ConstantsUtils.KEYWORD
                        : StringUtils.isBlank(cdrBinerDto.getOperatorDdlbDto() != null ? cdrBinerDto.getOperatorDdlbDto().toString() : StringUtils.EMPTY) ?ConstantsUtils.OPERATOR
                        : StringUtils.isBlank(cdrBinerDto.getValueText() != null ? cdrBinerDto.getValueText() : StringUtils.EMPTY) ? ConstantsUtils.VALUE
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
        lineTypeDdlbDto.addValidator(new RegexpValidator(ValidationUtil.getMessage(ConstantsUtils.EMPTY_FIELD_CHECK), ValidationUtil.getMessage(ConstantsUtils.EMPTY_FIELD_VALIDATION).replace("<>",ConstantsUtils.LINE_TYPE_LABEL)));
        itemGroupDdlb.addValidator(new RegexpValidator(ValidationUtil.getMessage(ConstantsUtils.EMPTY_FIELD_CHECK), ValidationUtil.getMessage(ConstantsUtils.EMPTY_FIELD_VALIDATION).replace("<>", "Item/Group/MS Association")));
        keywordDdlbDto.addValidator(new RegexpValidator(ValidationUtil.getMessage(ConstantsUtils.EMPTY_FIELD_CHECK), ValidationUtil.getMessage(ConstantsUtils.EMPTY_FIELD_VALIDATION).replace("<>", ConstantsUtils.KEYWORD)));
        operatorDdlbDto.addValidator(new RegexpValidator(ValidationUtil.getMessage(ConstantsUtils.EMPTY_FIELD_CHECK), ValidationUtil.getMessage(ConstantsUtils.EMPTY_FIELD_VALIDATION).replace("<>", ConstantsUtils.OPERATOR)));
        valueText.addValidator(new RegexpValidator(ValidationUtil.getMessage(ConstantsUtils.EMPTY_FIELD_CHECK), ValidationUtil.getMessage(ConstantsUtils.EMPTY_FIELD_VALIDATION).replace("<>", ConstantsUtils.VALUE)));
    }

    private void loadDdlb() {
        commonUtil.loadComboBox(ruleTypeDto, UIUtils.RULE_TYPE, false);
        commonUtil.loadComboBox(ruleCategoryDto, UIUtils.RULE_CATEGORY, false);
        commonUtil.loadComboBox(lineTypeDdlbDto, UIUtils.LINE_TYPE, false);
        commonUtil.loadComboBox(itemGroupDdlb, UIUtils.ITEM_GROUP_MS_ASSOCIATION, false);
        commonUtil.loadComboBox(keywordDdlbDto, UIUtils.KEYWORD, false);
        commonUtil.loadComboBox(operatorDdlbDto, UIUtils.OPERATOR, false);
        commonUtil.loadComboBox(comparisonDdlbDto, UIUtils.COMPARISON, false);
        commonUtil.loadComboBox(logicalOperatorDdlbDto, UIUtils.LOGICAL_OPERATOR, false);
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
        } catch (FieldGroup.CommitException ex) {
            LOGGER.error(ex.getMessage());
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
            ruleTypeDto.setValue(ruleType);
        }
        if (sessionDTO.getMode().equals(ConstantsUtils.COPY)) {
            ruleTypeDto.select(null);
            ruleNo.setValue(StringUtils.EMPTY);
            ruleName.setValue(StringUtils.EMPTY);
            ruleCategory.setId(objects[NumericConstants.FOUR] != null ? (Integer) objects[NumericConstants.FOUR] : 0);
            ruleCategory.setDescription(objects[NumericConstants.FIVE] != null || !String.valueOf(objects[NumericConstants.FIVE]).equals(ConstantsUtils.SELECT_ONE) ? (String) objects[NumericConstants.FIVE] : ConstantsUtils.SELECT_ONE);
            ruleCategoryDto.setValue(ruleCategory);
        } else {
            ruleNo.setValue(objects[NumericConstants.TWO] != null ? (String) objects[NumericConstants.TWO] : StringUtils.EMPTY);
            ruleName.setValue(objects[NumericConstants.THREE] != null ? (String) objects[NumericConstants.THREE] : StringUtils.EMPTY);
            ruleCategory.setId(objects[NumericConstants.FOUR] != null ? (Integer) objects[NumericConstants.FOUR] : 0);
            ruleCategory.setDescription(objects[NumericConstants.FIVE] != null || !String.valueOf(objects[NumericConstants.FIVE]).equals(ConstantsUtils.SELECT_ONE) ? (String) objects[NumericConstants.FIVE] : ConstantsUtils.SELECT_ONE);
            ruleCategoryDto.setValue(ruleCategory);
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

            if(objects[NumericConstants.ELEVEN] != null && Integer.valueOf(String.valueOf(objects[NumericConstants.ELEVEN])) != 0) {
                ruleDetailsDto.setComparisonDdlb(helperListUtil.getIdHelperDTOMap().get(Integer.valueOf(String.valueOf(objects[NumericConstants.ELEVEN]))));
            }
            if (objects[NumericConstants.ELEVEN] != null && Integer.valueOf(String.valueOf(objects[NumericConstants.ELEVEN])) == 0) {
                ruleDetailsDto.setValueText(objects[NumericConstants.TEN] != null ? dollarFormat.format(Double.valueOf(String.valueOf(objects[NumericConstants.TEN]))) : StringUtils.EMPTY);
            } else {
                ruleDetailsDto.setValueText(objects[NumericConstants.TEN] != null ? percentFormat.format(Double.valueOf(String.valueOf(objects[NumericConstants.TEN]))) + "%" : StringUtils.EMPTY);
            }
            if(objects[NumericConstants.TWELVE] != null && Integer.valueOf(String.valueOf(objects[NumericConstants.TWELVE])) != 0) {
                ruleDetailsDto.setLogicalOperatorDdlb(helperListUtil.getIdHelperDTOMap().get(Integer.valueOf(String.valueOf(objects[NumericConstants.TWELVE]))));
            }

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
        ruleTypeDto.setEnabled(isEnable);
        ruleNo.setEnabled(isEnable);
        ruleName.setEnabled(isEnable);
        ruleCategoryDto.setEnabled(isEnable);
        lineTypeDdlbDto.setEnabled(isEnable);
        keywordDdlbDto.setEnabled(isEnable);
        operatorDdlbDto.setEnabled(isEnable);
        valueText.setEnabled(isEnable);
        comparisonDdlbDto.setEnabled(isEnable);
        logicalOperatorDdlbDto.setEnabled(isEnable);
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
        ruleTypeDto.select(null);
        ruleCategoryDto.select(null);
        lineTypeDdlbDto.select(null);
        keywordDdlbDto.select(null);
        operatorDdlbDto.select(null);
        comparisonDdlbDto.select(null);
        logicalOperatorDdlbDto.select(null);
        valueText.setValue(StringUtils.EMPTY);
        LOGGER.debug("Add reset Method ends");
    }

    private void configurePermission(final Map<String, AppPermission> fieldCompanyHM, final Map<String, AppPermission> functionHM) {

        LOGGER.debug("Entering configurePermission");
        try {
            String mode = sessionDTO.getMode();

            List<Object> resultList = commonUIUtils.getFieldsForSecurity(UISecurityUtil.COMPLIANCE_DEDUCTION_RULES, ConstantsUtils.RULE_INFORMATION);
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
            LOGGER.error(ex.getMessage());
        }
        LOGGER.debug("Ending configurePermission");

    }
}
