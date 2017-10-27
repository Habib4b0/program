package com.stpl.app.global.ifp.ui.form;

import com.stpl.app.global.common.util.CommonUtil;
import com.stpl.app.global.ifp.dto.ItemFamilyplanMasterDTO;
import com.stpl.app.global.ifp.dto.ItemFamilyplanSearchDTO;
import com.stpl.app.global.ifp.ui.lazyload.IFPParentLookupCriteria;
import com.stpl.app.global.item.logic.ItemSearchLogic;
import com.stpl.app.global.item.util.UIUtils;
import com.stpl.app.ui.IFPFilterGenerator;
import com.stpl.app.ui.errorhandling.ErrorLabel;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.CommonUIUtils;
import com.stpl.app.util.Constants;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.ErrorCodeUtil;
import com.stpl.app.util.ErrorCodes;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.event.ShortcutAction;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;
import com.stpl.app.global.ifp.logic.IFPParentLookupTableLogic;
import com.stpl.app.util.ResponsiveUtils;
import com.vaadin.ui.VerticalLayout;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;

public final class ParentIFPIdLookup extends Window {

    private static final Logger LOGGER = Logger.getLogger(PricingResults.class);

    @UiField("errorLB")
    private ErrorLabel errorMsg;

    @UiField("itemFamilyplanIdLB")
    private Label itemFamilyplanIdLB;
    @UiField("itemFamilyplanId")
    private TextField itemFamilyplanId;

    @UiField("itemFamilyplanNoLB")
    private Label itemFamilyplanNoLB;
    @UiField("itemFamilyplanNo")
    private TextField itemFamilyplanNo;

    @UiField("itemFamilyplanStatusLB")
    private Label itemFamilyplanStatusLB;
    @UiField("itemFamilyplanStatus")
    private ComboBox itemFamilyplanStatus;

    @UiField("itemFamilyplanNameLB")
    private Label itemFamilyplanNameLB;
    @UiField("itemFamilyplanName")
    private TextField itemFamilyplanName;

    @UiField("itemFamilyplanTypeLB")
    private Label itemFamilyplanTypeLB;
    @UiField("itemFamilyplanType")
    private ComboBox itemFamilyplanType;

    private final Label space = new Label(ConstantsUtils.SPACE, ContentMode.HTML);

    private TextField parentItemFamilyplanId = new TextField();

    private TextField parentItemFamilyplanName = new TextField();

    private final ItemSearchLogic ifpLogic = new ItemSearchLogic();

    public IFPParentLookupCriteria ifpCriteria = new IFPParentLookupCriteria();

    private ErrorfulFieldGroup binder;

    @UiField("search")
    private Button search;

    @UiField("reset")
    private Button reset;

    @UiField("selectBtn")
    private Button selectBtn;

    @UiField("closeBtn")
    private Button closeBtn;

    @UiField("tableLayout")
    private VerticalLayout tableLayout;

    @UiField("controlLayout")
    private HorizontalLayout controlLayout;

    private Boolean itemClicked = false;

    private IFPParentLookupTableLogic tablelogic = new IFPParentLookupTableLogic();
    private ExtPagedTable resultTable = new ExtPagedTable(tablelogic);
    private BeanItemContainer<ItemFamilyplanSearchDTO> resultbean = new BeanItemContainer<>(ItemFamilyplanSearchDTO.class);

    private CommonUtil commonUtil = CommonUtil.getInstance();
    private final UIUtils uiUtils = UIUtils.getInstance();

    public TextField getParentItemFamilyplanId() {
        return parentItemFamilyplanId;
    }

    public void setParentItemFamilyplanId(final TextField parentItemFamilyplanId) {
        this.parentItemFamilyplanId = parentItemFamilyplanId;
    }

    public TextField getParentItemFamilyplanName() {
        return parentItemFamilyplanName;
    }

    public void setParentItemFamilyplanName(final TextField parentItemFamilyplanName) {
        this.parentItemFamilyplanName = parentItemFamilyplanName;
    }

    public ErrorLabel getErrorMsg() {
        return errorMsg;
    }

    public Label getSpace() {
        return space;
    }

    public TextField getItemFamilyplanId() {
        return itemFamilyplanId;
    }

    public TextField getItemFamilyplanName() {
        return itemFamilyplanName;
    }

    public TextField getItemFamilyplanNo() {
        return itemFamilyplanNo;
    }

    public ComboBox getItemFamilyplanStatus() {
        return itemFamilyplanStatus;
    }

    public ComboBox getItemFamilyplanType() {
        return itemFamilyplanType;
    }

    public ItemSearchLogic getIfpLogic() {
        return ifpLogic;
    }

    public ExtFilterTable getResultTable() {
        return resultTable;
    }

    public Button getSearch() {
        return search;
    }

    public Button getReset() {
        return reset;
    }

    public void setBinder(final ErrorfulFieldGroup binder) {
        this.binder = binder;
    }

    public ParentIFPIdLookup(final TextField parentItemFamilyplanId, final TextField parentItemFamilyplanName) {
        super("Item Family Plan Search");
        this.parentItemFamilyplanId = parentItemFamilyplanId;
        this.parentItemFamilyplanName = parentItemFamilyplanName;
        init();
    }

    private void init() {
        center();
        setClosable(true);
        setModal(true);
        setHeight("600px");
        addToContent();
        configureFields();
        getBinder();
        validateFields();

    }

    public void validateFields() {
        Collection<Field<?>> collection = binder.getFields();
        CommonUtil commmonUtil = CommonUtil.getInstance();
        for (Field field : collection) {
            if (field instanceof TextField) {
                TextField textField = (TextField) field;
                commmonUtil.textValidation(textField, textField.getData());
            }
        }
    }

    private void addToContent() {

        setContent(Clara.create(getClass().getResourceAsStream("/declarativeui/item_family_plan/parentifpidlookup.xml"), this));

        addTabel();

    }

    private ErrorfulFieldGroup getBinder() {

        binder = new ErrorfulFieldGroup(
                new BeanItem<>(new ItemFamilyplanMasterDTO()));
        binder.setBuffered(true);
        binder.bindMemberFields(this);
        binder.setErrorDisplay(errorMsg);

        return binder;
    }

    public HorizontalLayout addToGrid() {

        final HorizontalLayout hLayout = new HorizontalLayout();
        hLayout.setStyleName("responsiveTabGrid");
        final CssLayout cssLayout = new CssLayout();
        cssLayout.setSizeFull();
        cssLayout.addComponent(new Label("IFP ID"));
        cssLayout.addComponent(itemFamilyplanId);
        cssLayout.addComponent(new Label("IFP No"));
        cssLayout.addComponent(itemFamilyplanNo);
        cssLayout.addComponent(new Label("IFP Name"));
        cssLayout.addComponent(itemFamilyplanName);
        cssLayout.addComponent(new Label("IFP Status"));
        cssLayout.addComponent(itemFamilyplanStatus);
        cssLayout.addComponent(new Label("IFP Type"));
        cssLayout.addComponent(itemFamilyplanType);
        hLayout.addComponent(cssLayout);
        return hLayout;

    }

    private void addTabel() {

        resultTable.setPageLength(NumericConstants.TEN);
        resultTable.setImmediate(true);
        resultTable.setSelectable(true);
        resultTable.setWidth(NumericConstants.NINETY_EIGHT, UNITS_PERCENTAGE);
        tablelogic.setContainerDataSource(resultbean);
        tablelogic.sinkItemPerPageWithPageLength(false);

        resultTable.setVisibleColumns(uiUtils.ifpSearchTable);
        resultTable.setColumnHeaders(uiUtils.ifpColHeaders);
        tableLayout.addComponent(resultTable);
        ResponsiveUtils.getResponsiveControls(tablelogic.createControls(), controlLayout);
        tableLayout.addComponent(controlLayout);

        resultTable.setFilterBarVisible(true);
        resultTable.setFilterDecorator(new ExtDemoFilterDecorator());
        resultTable.setValidationVisible(false);
        resultTable.addStyleName(ConstantsUtils.FILTER_BAR);
        resultTable.setFilterFieldVisible("totalDollarCommitment", false);
        resultTable.setFilterFieldVisible("commitmentPeriod", false);
        resultTable.setFilterFieldVisible("totalVolumeCommitment", false);
        resultTable.setFilterFieldVisible("totalMarketshareCommitment", false);
        resultTable.setFilterGenerator(new IFPFilterGenerator());

        resultTable.addValueChangeListener(new Property.ValueChangeListener() {

            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if (event.getProperty().getValue() != null) {
                    itemClicked = true;
                    selectBtn.setEnabled(true);
                    closeBtn.setEnabled(true);
                } else {
                    itemClicked = false;
                    selectBtn.setEnabled(false);
                    closeBtn.setEnabled(true);
                }

            }
        });
    }

    protected void configureFields() {
        addStyleName("bootstrap-company");
        addStyleName(ConstantsUtils.BOOTSTRAP);
        addStyleName(ConstantsUtils.BOOTSTRAP_BB);
        selectBtn.setEnabled(false);
        closeBtn.setEnabled(false);
        setResizable(false);
        itemFamilyplanId.focus();
        setCloseShortcut(ShortcutAction.KeyCode.ESCAPE);

        itemFamilyplanName.setData("maxlengthvalidationhundred,maxlengthvalidationitemfamilyplanname,null,null");
        itemFamilyplanName.setImmediate(true);
        itemFamilyplanName.setValidationVisible(true);
        itemFamilyplanName.setDescription((String) itemFamilyplanName.getValue());
        itemFamilyplanName.addValueChangeListener(new Property.ValueChangeListener() {
            @SuppressWarnings("PMD")
            public void valueChange(final Property.ValueChangeEvent event) {
                itemFamilyplanName.setDescription((String) itemFamilyplanName.getValue());
            }
        });

        itemFamilyplanNo.setImmediate(true);
        itemFamilyplanNo.setValidationVisible(true);
        itemFamilyplanNo.setData("maxlengthvalidationfifty,maxlengthvalidationitemfamilyplanno,null,null");
        itemFamilyplanNo.setDescription((String) itemFamilyplanNo.getValue());
        itemFamilyplanNo.addValueChangeListener(new Property.ValueChangeListener() {

            public void valueChange(final Property.ValueChangeEvent event) {
                itemFamilyplanNo.setDescription((String) itemFamilyplanNo.getValue());
            }
        });

        itemFamilyplanId.setImmediate(true);
        itemFamilyplanId.setValidationVisible(true);
        itemFamilyplanId.setData("maxlengthvalidationfifty,maxlengthvalidationitemfamilyplanid,null,null");

        itemFamilyplanId.addValueChangeListener(new Property.ValueChangeListener() {

            public void valueChange(final Property.ValueChangeEvent event) {
                itemFamilyplanId.setDescription((String) itemFamilyplanId.getValue());
            }
        });

        itemFamilyplanStatus.setImmediate(true);
        commonUtil.loadComboBox(itemFamilyplanStatus, UIUtils.STATUS, false);

        itemFamilyplanType.setImmediate(true);
        commonUtil.loadComboBox(itemFamilyplanType, "IFP_TYPE", false);

        space.setHeight("10px");

        search.addClickListener(new ClickListener() {

            public void buttonClick(final ClickEvent event) {
                LOGGER.debug("Entering ParentIFPIdLookup search operaion");
                List<Object> collapsedColumns = new ArrayList<>();
                try {

                    for (Object item : resultTable.getVisibleColumns()) {
                        if (resultTable.isColumnCollapsed(item)) {
                            collapsedColumns.add(item);
                        }
                    }
                    if (binder.getField("itemFamilyplanId").getValue().toString().isEmpty()
                            && binder.getField("itemFamilyplanNo").getValue().toString().isEmpty()
                            && binder.getField("itemFamilyplanName").getValue().toString().isEmpty()
                            && (((com.stpl.ifs.util.HelperDTO) binder.getField("itemFamilyplanStatus").getValue()).getId() == 0)
                            && (((com.stpl.ifs.util.HelperDTO) binder.getField("itemFamilyplanType").getValue()).getId() == 0)) {
                        MessageBox.showPlain(Icon.ERROR, "Search Error", "Please enter Search Criteria", ButtonId.OK);
                        return;
                    }
                    ifpCriteria.setCustomDirty(true);
                    binder.commit();

                    tablelogic.configureSearchData(binder,Boolean.TRUE);
                    tablelogic.setContainerDataSource(resultbean);
                    if (resultbean.size() > Constants.ZERO) {
                        CommonUIUtils.successNotification(ConstantsUtils.SEARCH_COMPLETED);
                    } else {
                        CommonUIUtils.successNotification(ConstantsUtils.NO_RESULTS_COMPLETED);
                    }

                    resultTable.setVisibleColumns(uiUtils.ifpSearchTable);
                    resultTable.setColumnHeaders(uiUtils.ifpColHeaders);
                } catch (FieldGroup.CommitException ex) {
                    LOGGER.error(ex);
                } catch (Exception exception) {
                    final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1004), new MessageBoxListener() {

                        @SuppressWarnings("PMD")
                        public void buttonClicked(final ButtonId buttonId) {
                            return;
                        }
                    }, ButtonId.OK);
                    msg.getButton(ButtonId.OK).focus();
                    LOGGER.error(exception);
                }
                ifpCriteria.setCustomDirty(false);
                for (Object propertyId : collapsedColumns) {
                    resultTable.setColumnCollapsed(propertyId, true);
                }
                ifpCriteria.setCustomDirty(true);
                LOGGER.debug("Ending ParentIFPIdLookup search operaion");
            }
        });

        reset.addClickListener(new ClickListener() {

            public void buttonClick(final ClickEvent event) {
                MessageBox.showPlain(Icon.QUESTION, "Confirmation", "Are you sure you want to reset the page to default/previous values " + " ?", new MessageBoxListener() {

                    @SuppressWarnings("PMD")
                    public void buttonClicked(final ButtonId buttonId) {
                        if (buttonId.name().equals(ConstantsUtils.YES)) {
                            try {
                                List<Object> collapsedColumns = new ArrayList<>();
                                for (Object item : resultTable.getVisibleColumns()) {
                                    if (resultTable.isColumnCollapsed(item)) {
                                        collapsedColumns.add(item);
                                    }
                                }
                                LOGGER.debug("Entering ParentIFPIdLookup reset operaion");
                                binder.setItemDataSource(new BeanItem<>(new ItemFamilyplanMasterDTO()));
                                binder.getErrorDisplay().clearError();
                                tablelogic.configureSearchData(binder, Boolean.FALSE);
                                tablelogic.setItemsPerPage(NumericConstants.FIFTEEN);
                               ifpCriteria.setCustomDirty(false);
                                for (Object propertyId : collapsedColumns) {
                                    resultTable.setColumnCollapsed(propertyId, true);
                                }
                                ifpCriteria.setCustomDirty(true);
                                LOGGER.debug("Ending ParentIFPIdLookup reset operaion");
                            } catch (Exception exception) {
                                final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1006), new MessageBoxListener() {

                                    @SuppressWarnings("PMD")
                                    public void buttonClicked(final ButtonId buttonId) {
                                        // Do Nothing              
                                    }
                                }, ButtonId.OK);
                                msg.getButton(ButtonId.OK).focus();
                                LOGGER.error(exception);

                            }
                        }
                    }
                }, ButtonId.YES, ButtonId.NO);

            }
        });

    }

    @UiHandler("selectBtn")
    public void selectBtn(Button.ClickEvent event) {
        if (itemClicked) {
            final ItemFamilyplanSearchDTO ifpSearchDto = (ItemFamilyplanSearchDTO) resultTable.getValue();
            parentItemFamilyplanId.setValue(ifpSearchDto.getIfpId());
            parentItemFamilyplanName.setValue(ifpSearchDto.getIfpName());
            close();
        } else {
            final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ConstantsUtils.ERROR, "Please select a row", new MessageBoxListener() {

                @SuppressWarnings("PMD")
                public void buttonClicked(final ButtonId buttonId) {
                    // Do Nothing              
                }
            }, ButtonId.OK);
            msg.getButton(ButtonId.OK).focus();
        }
    }

    @UiHandler("closeBtn")
    public void closeBtn(Button.ClickEvent event) {
        close();
    }

}