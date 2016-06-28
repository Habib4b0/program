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
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.customwindow.CustomWindow;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author mohamed.hameed
 */
public class RSLookUp extends CustomWindow {
private static final Logger LOGGER = Logger.getLogger(RSLookUp.class);
    @UiField("cfpTableLayout")
    public VerticalLayout cfpTableLayout;
    @UiField("componentId")
    public TextField componentId;
    @UiField("componentName")
    public TextField componentName;
    @UiField("startDate")
    PopupDateField startDate;
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
    public Button selectBtn;
    @UiField("closeBtn")
    public Button closeBtn;
    @UiField("endDate")
    PopupDateField endDate;
    AbstractLookUpTableLogic tableLogic = new AbstractLookUpTableLogic();
    private ExtPagedTable resultsTable = new ExtPagedTable(tableLogic);
    private BeanItemContainer<ComponentLookUpDTO> resultsContainer = new BeanItemContainer<ComponentLookUpDTO>(ComponentLookUpDTO.class);
    private BeanItemContainer<String> componentStatusBean = new BeanItemContainer<String>(String.class);
    private BeanItemContainer<String> componentTypeBean = new BeanItemContainer<String>(String.class);
    private String component = StringUtils.EMPTY;
    ComponentLookUpDTO componentDto;
    ComponentLookUpDTO binderDto = new ComponentLookUpDTO();
    private CustomFieldGroup binder = new CustomFieldGroup(new BeanItem<ComponentLookUpDTO>(binderDto));
    SelectionDTO selection = new SelectionDTO();
    AbstractLogic logic = AbstractLogic.getInstance();
    List<String> countFlag = new ArrayList<String>();
    List<String> loadDataFlag = new ArrayList<String>();
    public CustomTextField componentTextField;
    private Boolean recordSelectedFlag = false;
    public Object RS_SEARCH_COLUMNS[] = new Object[]{
        "componentId", "componentNo", "componentName", "componentType", "rsProgramType", "category", "tradeClass", "designation", "planId", "planName", "componentStatus", Constants.START_DATE, Constants.END_DATE};
    public String RS_SEARCH_HEADERS[] = new String[]{"REBATE_SCHEDULE ID", "REBATE_SCHEDULE No", "REBATE_SCHEDULE_NAME", "REBATE_SCHEDULE_TYPE", "REBATE_PROGRAM_TYPE", "REBATE_SCHEDULE_CATEGORY", "TRADE_CLASS", "REBATE_SCHEDULE_DESIGNATION", "PARENT_REBATE_SCHEDULE_ID", "PARENT_REBATE_SCHEDULE_NAME", "REBATE_SCHEDULE_STATUS", "REBATE_SCHEDULE_START_DATE", "REBATE_SCHEDULE_END_DATE"};

    public RSLookUp(final String component, final CustomTextField componentTextField) throws Exception {
        this.component = component;
        this.componentTextField = componentTextField;
        setContent(Clara.create(getClass().getResourceAsStream("/item/rsLookUp.xml"), this));
        addStyleName("valo-theme-customwindow");
        addStyleName("bootstrap-ui");
        addStyleName(Constants.bootstrap);
        addStyleName(Constants.bootstrap_forecast_bootstrap_nm);
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
        tableLogic.setPageLength(10);
        tableLogic.sinkItemPerPageWithPageLength(false);
        resultsTable.setVisibleColumns(RS_SEARCH_COLUMNS);
        resultsTable.setColumnHeaders(RS_SEARCH_HEADERS);
        resultsTable.addStyleName("filterbar");
        resultsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        resultsTable.setSelectable(true);

        for (Object propertyId : resultsTable.getVisibleColumns()) {
            resultsTable.setColumnWidth(propertyId, 180);
        }
        componentStatusBean.addItem(Constants.IndicatorConstants.SELECT_ONE.getConstant());
        componentStatus_DTO.setImmediate(true);
        componentTypeBean.addItem(Constants.IndicatorConstants.SELECT_ONE.getConstant());
        selectBtn.setImmediate(false);
        selectBtn.setEnabled(true);

        resultsTable.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Method called when available results value is changed.
             */
            @SuppressWarnings("PMD")
            public void valueChange(final Property.ValueChangeEvent event) {
                resultsItemClick(event.getProperty().getValue());
            }
        });
        for (Object object : resultsTable.getVisibleColumns()) {
            if (String.valueOf(object).contains("Date")) {
                resultsTable.setColumnAlignment(object, ExtCustomTable.Align.CENTER);
                resultsTable.setConverter(object, ConstantsUtil.DATETOSTRINGCONVERTER);
            }
        }
        resultsTable.setFilterGenerator(new ExtFilterGenerator() {
            public Container.Filter generateFilter(Object propertyId, Object value) {
                return null;
            }

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

            public void filterRemoved(Object propertyId) {
            }

            public void filterAdded(Object propertyId, Class<? extends Container.Filter> filterType, Object value) {
            }

            public Container.Filter filterGeneratorFailed(Exception reason, Object propertyId, Object value) {
                return null;
            }

            public AbstractField<?> getCustomFilterComponent(Object propertyId) {
                if ("componentType".equals(propertyId)) {
                    try {
                        ComboBox componentType = new ComboBox();
                        logic.loadComboBox(componentType, "RS_TYPE", true);
                        return componentType;
                    } catch (Exception ex) {
                        LOGGER.info("Error---->" + ex.getMessage());
                    }
                }
                if ("rsProgramType".equals(propertyId)) {
                    try {
                        ComboBox rsProgramType = new ComboBox();
                        logic.loadComboBox(rsProgramType, "REBATE_PROGRAM_TYPE", true);
                        return rsProgramType;
                    } catch (Exception ex) {
                        LOGGER.info("Error---->" + ex.getMessage());
                    }
                }
                if ("category".equals(propertyId)) {
                    try {
                        ComboBox category = new ComboBox();
                        logic.loadComboBox(category, "RS_CATEGORY", true);
                        return category;
                    } catch (Exception ex) {
                        LOGGER.info("Error---->" + ex.getMessage());
                    }
                }
                if ("componentStatus".equals(propertyId)) {
                        ComboBox componentStatus = new ComboBox();
                    logic.LazyLoadDdlb(componentStatus, "rsStatus count", "rsStatus", true);
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

    private CustomFieldGroup getBinder() {
        binder.bindMemberFields(this);
        binder.setItemDataSource(new BeanItem<ComponentLookUpDTO>(binderDto));
        binder.setBuffered(true);
        return binder;
    }

    @UiHandler("searchBtn")
    public void searchButtonLogic(Button.ClickEvent event) throws SystemException, PortalException, Exception {
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
    public void resetButtonLogic(Button.ClickEvent event) throws SystemException, PortalException, Exception {
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
                    LOGGER.error(ex.getMessage());
                }
            }

            @Override
            public void noMethod() {
            }
        }.getConfirmationMessage("Confirmation", "Are you sure you want to reset?");

    }

    @UiHandler("selectBtn")
    public void selectBtn(Button.ClickEvent event) throws SystemException, PortalException, Exception {
        if (resultsTable.getValue() != null) {
            ComponentLookUpDTO componentLookUp = (ComponentLookUpDTO) resultsTable.getValue();
            componentTextField.setData(componentLookUp);
            close();
        } else {
            AbstractNotificationUtils.getErrorNotification("Error", "Please select a record");
        }
    }

    @UiHandler("closeBtn")
    public void closeBtn(Button.ClickEvent event) throws SystemException, PortalException, Exception {
        close();
    }

    private void loadComponentDdlb() throws Exception {
        loadComponentStatus();
        loadComponentType();
        loadRsCategory();
        loadRptype();
    }

    private void loadComponentStatus() {
        logic.LazyLoadDdlb(componentStatus_DTO, "rsStatus count", "rsStatus", false);
    }

    private void loadComponentType() throws Exception {
       logic.loadComboBox(componentType_DTO, "RS_TYPE", false);

    }

    private void loadRsCategory() throws Exception {
        logic.loadComboBox(componentCategory_DTO, "RS_CATEGORY", false);
     
    }

    private void loadRptype() throws Exception {
      logic.loadComboBox(rsProgramType_DTO, "REBATE_PROGRAM_TYPE", false);
     
    }

    /**
     * Results item click.
     *
     * @param obj the id
     */
    protected void resultsItemClick(final Object obj) {
        if (obj == null) {
            recordSelectedFlag = false;
        } else {
            recordSelectedFlag = true;
        }
    }
}
