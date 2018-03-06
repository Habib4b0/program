/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.itemmanagement.itemabstract.form.lookup;

import com.stpl.app.gcm.common.CommonLogic;
import com.stpl.app.gcm.globalchange.dto.SelectionDTO;
import com.stpl.app.gcm.itemmanagement.index.util.ConstantsUtil;
import com.stpl.app.gcm.itemmanagement.itemabstract.dto.ComponentLookUpDTO;
import com.stpl.app.gcm.itemmanagement.itemabstract.logic.AbstractLogic;
import com.stpl.app.gcm.itemmanagement.itemabstract.logic.abstracttablelogic.AbstractLookUpTableLogic;
import com.stpl.app.gcm.util.AbstractNotificationUtils;
import com.stpl.app.gcm.util.Constants;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.constants.BooleanConstant;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.fieldgroup.FieldGroup;
import com.vaadin.v7.data.util.BeanItem;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.data.util.filter.SimpleStringFilter;
import com.vaadin.v7.ui.AbstractField;
import com.vaadin.ui.Button;
import com.vaadin.v7.ui.ComboBox;
import org.asi.ui.extfilteringtable.ExtCustomTable;
import com.vaadin.v7.ui.Field;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.v7.ui.PopupDateField;
import com.vaadin.v7.ui.TextField;
import com.vaadin.v7.ui.VerticalLayout;
import com.vaadin.ui.Window;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author mohamed.hameed
 */
public class RSLookUp extends Window {
private static final Logger LOGGER = LoggerFactory.getLogger(RSLookUp.class);
private static final BooleanConstant BOOLEAN_CONSTANT = new BooleanConstant();
    @UiField("cfpTableLayout")
    public VerticalLayout cfpTableLayout;
    @UiField("componentId")
    public TextField componentId;
    @UiField("componentName")
    public TextField componentName;
    @UiField("startDate")
    private PopupDateField startDate;
    @UiField("componentNo")
    public TextField componentNo;
    @UiField("componentStatus_DTO")
    public ComboBox componentStatus_DTO;
    @UiField("componentType_DTO")
    public ComboBox componentType_DTO;
    @UiField("componentCategory_DTO")
    public ComboBox componentCategory_DTO;
    @UiField("rsProgramType_DTO")
    public ComboBox rsProgramType_DTO;
    @UiField("searchBtn")
    public Button searchBtn;
    @UiField("resetBtn")
    public Button resetBtn;
    @UiField("selectBtn")
    public Button selectButton;
    @UiField("closeBtn")
    public Button closeButton;
    @UiField("endDate")
    private PopupDateField endDate;
    private final AbstractLookUpTableLogic tableLogic = new AbstractLookUpTableLogic();
    private final ExtPagedTable resultsTable = new ExtPagedTable(tableLogic);
    private final BeanItemContainer<ComponentLookUpDTO> resultsContainer = new BeanItemContainer<>(ComponentLookUpDTO.class);
    private final BeanItemContainer<String> componentStatusBean = new BeanItemContainer<>(String.class);
    private final BeanItemContainer<String> componentTypeBean = new BeanItemContainer<>(String.class);
    private final ComponentLookUpDTO binderDto = new ComponentLookUpDTO();
    private final ErrorfulFieldGroup binder = new ErrorfulFieldGroup(new BeanItem<>(binderDto));
    private final SelectionDTO selection = new SelectionDTO();
    private final AbstractLogic logic = AbstractLogic.getInstance();
    private final CustomTextField componentTextField;
    public static final String REBATE_PROGRAM_TYPE_HEADER = "REBATE_PROGRAM_TYPE";
    private final Object[] RS_SEARCH_COLUMNS = new Object[]{
        "componentId", "componentNo", "componentName", "componentType", "rsProgramType", "category", "tradeClass", "designation", "planId", "planName", "componentStatus", Constants.START_DATE, Constants.END_DATE};
    private final String[] RS_SEARCH_HEADERS = new String[]{"REBATE_SCHEDULE ID", "REBATE_SCHEDULE No", "REBATE_SCHEDULE_NAME", "REBATE_SCHEDULE_TYPE", REBATE_PROGRAM_TYPE_HEADER, "REBATE_SCHEDULE_CATEGORY", "TRADE_CLASS", "REBATE_SCHEDULE_DESIGNATION", "PARENT_REBATE_SCHEDULE_ID", "PARENT_REBATE_SCHEDULE_NAME", "REBATE_SCHEDULE_STATUS", "REBATE_SCHEDULE_START_DATE", "REBATE_SCHEDULE_END_DATE"};
    

    public RSLookUp(final CustomTextField componentTextField) {
        this.componentTextField = componentTextField;
        setContent(Clara.create(getClass().getResourceAsStream("/item/rsLookUp.xml"), this));
        addStyleName("valo-theme-customwindow");
        addStyleName("bootstrap-ui");
        addStyleName(Constants.BOOTSTRAP);
        addStyleName(Constants.BOOTSTRAP_FORECAST_BOOTSTRAP_NM);
        setClosable(true);
        setModal(true);
        getBinder();
        configureFields();
        loadComponentDdlb();
    }

    public void configureFields() {
        addTableLayout();
        startDate.addStyleName("align-center");
        endDate.addStyleName("align-center");
        tableLogic.setContainerDataSource(resultsContainer);
        tableLogic.setPageLength(NumericConstants.TEN);
        tableLogic.sinkItemPerPageWithPageLength(false);
        resultsTable.setVisibleColumns(RS_SEARCH_COLUMNS);
        resultsTable.setColumnHeaders(RS_SEARCH_HEADERS);
        resultsTable.addStyleName("filterbar");
        resultsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        resultsTable.setSelectable(true);

        for (Object propertyId : resultsTable.getVisibleColumns()) {
            resultsTable.setColumnWidth(propertyId, NumericConstants.ONE_EIGHT_ZERO);
        }
        componentStatusBean.addItem(Constants.IndicatorConstants.SELECT_ONE.getConstant());
        componentTypeBean.addItem(Constants.IndicatorConstants.SELECT_ONE.getConstant());
        selectButton.setEnabled(true);

        
        for (Object object : resultsTable.getVisibleColumns()) {
            if (String.valueOf(object).contains("Date")) {
                resultsTable.setColumnAlignment(object, ExtCustomTable.Align.CENTER);
                resultsTable.setConverter(object, ConstantsUtil.DATETOSTRINGCONVERTER);
            }
        }
        resultsTable.setFilterGenerator(new ExtFilterGenerator() {
            @Override
            public Container.Filter generateFilter(Object propertyId, Object value) {
                return null;
            }

            @Override
            public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {
                if (originatingField instanceof ComboBox) {
                    if (originatingField.getValue() != null) {
                        HelperDTO dto = (HelperDTO) originatingField.getValue();

                        return new SimpleStringFilter(propertyId, String.valueOf(dto.getDescription()), false, false);
                    } else {
                        return null;
                    }
                }
                return null;
            }

            @Override
            public void filterRemoved(Object propertyId) {
                return;
            }

            @Override
            public void filterAdded(Object propertyId, Class<? extends Container.Filter> filterType, Object value) {
                return;
            }

            @Override
            public Container.Filter filterGeneratorFailed(Exception reason, Object propertyId, Object value) {
                return null;
            }

            @Override
            public AbstractField<?> getCustomFilterComponent(Object propertyId) {
                if ("componentType".equals(propertyId)) {
                    try {
                        ComboBox componentType = new ComboBox();
                        logic.loadComboBox(componentType, "RS_TYPE", true);
                        return componentType;
                    } catch (Exception ex) {
                        LOGGER.error("",ex);
                    }
                }
                if ("rsProgramType".equals(propertyId)) {
                    try {
                        ComboBox rsProgramType = new ComboBox();
                        logic.loadComboBox(rsProgramType, REBATE_PROGRAM_TYPE_HEADER, true);
                        return rsProgramType;
                    } catch (Exception ex) {
                         LOGGER.error("",ex);
                    }
                }
                if ("category".equals(propertyId)) {
                    try {
                        ComboBox category = new ComboBox();
                        logic.loadComboBox(category, "RS_CATEGORY", true);
                        return category;
                    } catch (Exception ex) {
                        LOGGER.error("",ex);
                    }
                }
                if ("componentStatus".equals(propertyId)) {
                        ComboBox componentStatus = new ComboBox();
                    logic.LazyLoadDdlb(componentStatus, "rsStatus count", "rsStatus", BOOLEAN_CONSTANT.getTrueFlag());
                        return componentStatus;
                    }
                return null;
            }
        });
        resultsTable.setFilterBarVisible(true);
        resultsTable.setFilterDecorator(new ExtDemoFilterDecorator());
    }

    public void addTableLayout() {
        cfpTableLayout.addComponent(resultsTable);
        HorizontalLayout controls = tableLogic.createControls();
        HorizontalLayout controlLayout = CommonLogic.getResponsiveControls(controls);
        cfpTableLayout.addComponent(controlLayout);
    }

    private ErrorfulFieldGroup getBinder() {
        binder.bindMemberFields(this);
        binder.setItemDataSource(new BeanItem<>(binderDto));
        binder.setBuffered(true);
        return binder;
    }

    @UiHandler("searchBtn")
    public void searchButtonLogic(Button.ClickEvent event) throws FieldGroup.CommitException {
        binder.commit();
        selection.setOperation(Constants.RS);
        selection.setCountQueryName("RS-C Abstract");
        selection.setDataQueryName("RS Abstract");
        if (!tableLogic.loadSetData(binderDto, selection)) {
            AbstractNotificationUtils.getErrorNotification("No Matching Records",
                    "There were no records matching the search criteria.  Please try again.");
        } else {
            Notification.show("Search Completed");
        }
    }

    @UiHandler("resetBtn")
    public void resetButtonLogic(Button.ClickEvent event) {
        new AbstractNotificationUtils() {
            @Override
            public void yesMethod() {
                try {
                                   resultsTable.resetFilters();

                    componentId.setValue(StringUtils.EMPTY);
                    componentName.setValue(StringUtils.EMPTY);
                    rsProgramType_DTO.setValue(null);
                    componentStatus_DTO.setValue(null);
                    startDate.setValue(null);
                    componentNo.setValue(StringUtils.EMPTY);
                    componentType_DTO.setValue(null);
                    componentCategory_DTO.setValue(null);
                    endDate.setValue(null);
                    binder.commit();
                } catch (FieldGroup.CommitException ex) {
                    LOGGER.error("",ex);
                }
            }

            @Override
            public void noMethod() {
                return;
            }
        }.getConfirmationMessage("Confirmation", "Are you sure you want to reset?");

    }

    @UiHandler("selectBtn")
    public void selectBtn(Button.ClickEvent event) {
        if (resultsTable.getValue() != null) {
            ComponentLookUpDTO componentLookUp = (ComponentLookUpDTO) resultsTable.getValue();
            componentTextField.setData(componentLookUp);
            close();
        } else {
            AbstractNotificationUtils.getErrorNotification("Error", "Please select a record");
        }
    }

    @UiHandler("closeBtn")
    public void closeBtn(Button.ClickEvent event) {
        close();
    }

    private void loadComponentDdlb() {
        loadComponentStatus();
        loadComponentType();
        loadRsCategory();
        loadRptype();
    }

    private void loadComponentStatus() {
        logic.LazyLoadDdlb(componentStatus_DTO, "rsStatus count", "rsStatus", BOOLEAN_CONSTANT.getFalseFlag());
    }

    private void loadComponentType() {
       logic.loadComboBox(componentType_DTO, "RS_TYPE", false);

    }

    private void loadRsCategory() {
        logic.loadComboBox(componentCategory_DTO, "RS_CATEGORY", false);
     
    }

    private void loadRptype() {
      logic.loadComboBox(rsProgramType_DTO, REBATE_PROGRAM_TYPE_HEADER, false);
     
    }

   
}
