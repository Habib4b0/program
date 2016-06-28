/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.contract.dashboard.ui.lookup;

import com.stpl.app.contract.common.util.CommonUtil;
import com.stpl.app.contract.contractheader.util.UIUtils;
import com.stpl.app.contract.dashboard.logic.RsDeductionTableLogic;
import com.stpl.app.contract.dashboard.util.ContractUtils;
import com.stpl.app.contract.global.dto.RsDeductionLookupDto;
import com.stpl.app.contract.ui.ErrorfulFieldGroup;
import com.stpl.app.contract.util.CommonUIUtils;
import static com.stpl.app.contract.util.ResponsiveUtils.getResponsiveControls;
import com.stpl.app.util.ConstantUtil;
import com.stpl.ifs.ui.DateToStringConverter;
import com.stpl.ifs.util.HelperDTO;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 * The class RsDeductionLookup
 *
 * @author vinodhini
 */
public class RsDeductionLookup extends Window {

    /**
     * LOGGER
     */
    private static final Logger LOGGER = Logger.getLogger(RsDeductionLookup.class);
    @UiField("deductionNo")
    public TextField deductionNo;

    @UiField("deductionName")
    public TextField deductionName;

    @UiField("deductionDesc")
    public TextField deductionDesc;

    @UiField("category")
    public ComboBox category;
    /**
     * The search btn.
     */
    @UiField("searchBtn")
    Button searchBtn;
    /**
     * The reset btn.
     */
    @UiField("resetBtn")
    Button resetBtn;

    /**
     * The submit btn.
     */
    @UiField("selectBtn")
    Button selectBtn;
    /**
     * The close btn.
     */
    @UiField("closeBtn")
    public Button closeBtn;

    @UiField("resultsPanel")
    Panel resultsPanel;
    @UiField("tableLayout")
    VerticalLayout tableLayout;
    HorizontalLayout controlLayout = new HorizontalLayout();

    @UiField("searchLayout")
    GridLayout searchLayout;
    public boolean isSelected = false;
    public boolean isClosed = false;
    /**
     * The binder.
     */
    private ErrorfulFieldGroup binder;
    RsDeductionTableLogic tableLogic = new RsDeductionTableLogic();
    private final ExtPagedTable resultTable = new ExtPagedTable(tableLogic);
    public BeanItemContainer<RsDeductionLookupDto> resultBean = new BeanItemContainer<>(RsDeductionLookupDto.class);
    RsDeductionLookupDto deductionDto = new RsDeductionLookupDto();
    CustomTextField deductionNoField;

    public RsDeductionLookup(CustomTextField deductionNoField) {
        super("Deduction Calendar Lookup");
        center();
        this.deductionNoField = deductionNoField;
        setModal(true);
        setHeight("760px");
        setWidth("960px");
        addStyleName("bootstrap");
        addStyleName("bootstrap-bb");
        init();

    }

    public void init() {
        try {
            setContent(Clara.create(getClass().getResourceAsStream("/declarative-ui/contract-dashboard/rs-deduction-lookup.xml"), this));
            binder = getBinder();
            configureFields();
            configureTable();
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    /**
     * Gets the binder.
     *
     * @return the binder
     */
    public ErrorfulFieldGroup getBinder() {

        final ErrorfulFieldGroup deductionBinder;
        deductionBinder = new ErrorfulFieldGroup(new BeanItem<>(new RsDeductionLookupDto()));
        deductionBinder.setBuffered(true);
        deductionBinder.bindMemberFields(this);
        return deductionBinder;
    }

    /**
     * Configure fields.
     */
    private void configureFields() throws Exception {
        deductionNo.setValidationVisible(true);
        deductionName.setValidationVisible(true);
        deductionNo.setImmediate(true);
        deductionName.setImmediate(true);
        deductionDesc.setImmediate(true);

        resultTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {

            @Override
            public void itemClick(ItemClickEvent event) {
                itemselectLogic(event);
            }
        });

        CommonUtil commonUtil = CommonUtil.getInstance();
        commonUtil.loadComboBox(category, UIUtils.DEDUCTION_CALENDAR_CATEGORY, false);

    }

    @UiHandler("searchBtn")
    public void btnSearchLogic(Button.ClickEvent event) {
        try {
            binder.commit();
            if ((binder.getField("category").getValue() == null || (((HelperDTO) binder.getField("category").getValue()).getId()) == 0)
                    && StringUtils.isBlank(String.valueOf(binder.getField("deductionNo").getValue()).trim())
                    && StringUtils.isBlank(String.valueOf(binder.getField("deductionName").getValue()).trim())
                    && StringUtils.isBlank(String.valueOf(binder.getField("deductionDesc").getValue()).trim())) {

                MessageBox.showPlain(Icon.WARN, "Search", "Please enter/select search criteria.", ButtonId.OK);

            } else {
                loadGrid();

                if (tableLogic.isResultsEmpty()) {
                    CommonUIUtils.successNotification(ConstantUtil.NO_RESULTS_COMPLETED);

                } else {
                    CommonUIUtils.successNotification(ConstantUtil.SEARCH_COMPLETED);
                }

                resultTable.markAsDirtyRecursive();
            }

        } catch (FieldGroup.CommitException commit) {
            tableLogic.clearAll();
            tableLogic.getFilters().clear();
            resultTable.setFilterDecorator(new ExtDemoFilterDecorator());
            LOGGER.error(commit);
        } catch (Exception exception) {
            LOGGER.error(exception);
        }
    }

    /**
     * Reset Button Listener
     *
     * @param event
     */
    @UiHandler("resetBtn")
    public void btnResetLogic(Button.ClickEvent event) {

        MessageBox.showPlain(Icon.QUESTION, "Reset", "Are you sure you want to reset the search criteria?", new MessageBoxListener() {
            /**
             * Called when a Button has been clicked .
             *
             */
            @SuppressWarnings("PMD")
            public void buttonClicked(final ButtonId buttonId) {
                if (buttonId.name().equalsIgnoreCase("YES")) {
                    LOGGER.info("Entering Reset operation");
                    binder.getErrorDisplay().clearError();
                    binder.setItemDataSource(new BeanItem<>(new RsDeductionLookupDto()));

                    tableLogic.clearAll();
                    tableLogic.setReset(true);
                    tableLogic.setRequiredCount(true);
                    tableLogic.setCurrentPage(1);
                    tableLogic.setContainerDataSource(resultBean);
                    tableLogic.setPageLength(10);
                    tableLogic.sinkItemPerPageWithPageLength(false);

                    setTableDefaultConfig();
                    resultTable.setSelectable(true);
                    resultTable.markAsDirty();
                    resultTable.setComponentError(null);
                    resultTable.setFilterBarVisible(true);
                    resultTable.setFilterDecorator(new ExtDemoFilterDecorator());
                    resultTable.setValidationVisible(false);
                    resultTable.addStyleName("filterbar");

                    LOGGER.info("Ending Reset operation");
                }
            }
        }, ButtonId.YES, ButtonId.NO);
    }

    private void configureTable() {
        tableLayout.addComponent(resultTable);
        getResponsiveControls(tableLogic.createControls(), controlLayout);
        tableLayout.addComponent(controlLayout);

        tableLogic.setContainerDataSource(resultBean);
        tableLogic.setPageLength(10);
        tableLogic.sinkItemPerPageWithPageLength(false);
        setTableDefaultConfig();

        resultTable.setSelectable(true);
        resultTable.markAsDirty();

        resultTable.setComponentError(null);
        resultTable.setFilterBarVisible(true);
        resultTable.setFilterDecorator(new ExtDemoFilterDecorator());
        resultTable.setValidationVisible(false);
        resultTable.addStyleName("filterbar");

    }

    public void setTableDefaultConfig() {
        resultTable.setConverter("creationDate", new DateToStringConverter());
        resultTable.setConverter("modifiedDate", new DateToStringConverter());
        resultTable.setVisibleColumns(ContractUtils.DEDUCTION_LOOKUP_COLUMN);
        resultTable.setColumnHeaders(ContractUtils.DEDUCTION_LOOKUP_HEADER);
        resultTable.markAsDirtyRecursive();
        resultTable.setImmediate(true);
        resultTable.setWidth(99, UNITS_PERCENTAGE);
        resultTable.setHeight("250px");

    }

    /**
     * TO load the grid
     */
    private void loadGrid() {
        try {
            tableLogic.configureSearchData(binder);
            resultTable.setFilterDecorator(new ExtDemoFilterDecorator());
            resultTable.setImmediate(true);
            resultTable.setWidth(99, UNITS_PERCENTAGE);
            resultTable.addStyleName("TableCheckBox");
            resultTable.setSelectable(true);
            resultTable.markAsDirtyRecursive();

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    public void itemselectLogic(final ItemClickEvent event) {
        try {
            if (event.getItemId() != null) {
                deductionDto = (RsDeductionLookupDto) event.getItemId();
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    /**
     * Close Button Listener
     *
     * @param event
     */
    @UiHandler("closeBtn")
    public void closeBtnLogic(Button.ClickEvent event) {
        isClosed = true;
        close();
    }

    /**
     * Select Button Listener
     *
     * @param event
     */
    @UiHandler("selectBtn")
    public void selectBtnLogic(Button.ClickEvent event) {
        if (null != resultTable.getValue()) {
            deductionNoField.setReadOnly(false);
            deductionNoField.setValue(deductionDto.getDeductionNo());
            deductionNoField.setReadOnly(true);
            isSelected = true;
            close();
        } else {
            final MessageBox msg = MessageBox.showPlain(Icon.WARN, "Search", "Please select a row from the Results list view to proceed.", new MessageBoxListener() {

                /**
                 * The method is triggered when a button of the message box is
                 * pressed .
                 *
                 * @param buttonId The buttonId of the pressed button.
                 */
                @SuppressWarnings("PMD")
                public void buttonClicked(final ButtonId buttonId) {
                 
                }
            }, ButtonId.OK);
            msg.getButton(ButtonId.OK).focus();
        }
    }

    public RsDeductionLookupDto getDeductionDto() {
        return deductionDto;
    }

    public void setDeductionDto(RsDeductionLookupDto deductionDto) {
        this.deductionDto = deductionDto;
    }

    public ExtPagedTable getResultTable() {

        return resultTable;
    }

    public void disableComponents(boolean isEnable) {
        resultsPanel.setVisible(isEnable);
        searchLayout.setEnabled(isEnable);
        searchBtn.setVisible(isEnable);
        resetBtn.setVisible(isEnable);
        selectBtn.setVisible(isEnable);
    }
}
