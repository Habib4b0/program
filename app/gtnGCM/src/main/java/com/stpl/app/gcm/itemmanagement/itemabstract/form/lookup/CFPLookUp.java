
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
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.vaadin.data.Container;
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
import com.vaadin.ui.Window;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customtextfield.CustomTextField;
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
public class CFPLookUp extends Window {
private static final Logger LOGGER = Logger.getLogger(CFPLookUp.class);
    @UiField("cfpTableLayout")
    public VerticalLayout cfpTableLayout;
    @UiField("componentId")
    public TextField componentId;
    @UiField("componentName")
    public TextField componentName;
    @UiField("category")
    public TextField category;
    @UiField("startDate")
    PopupDateField startDate;
    @UiField("componentNo")
    public TextField componentNo;
    @UiField("componentStatus_DTO")
    public ComboBox componentStatus_DTO;
    @UiField("componentType")
    public TextField componentType;
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
    private BeanItemContainer<ComponentLookUpDTO> resultsContainer = new BeanItemContainer<>(ComponentLookUpDTO.class);
    private BeanItemContainer<String> componentStatusBean = new BeanItemContainer<>(String.class);
    private BeanItemContainer<String> componentTypeBean = new BeanItemContainer<>(String.class);
    ComponentLookUpDTO componentDto;
    ComponentLookUpDTO binderDto = new ComponentLookUpDTO();
    private CustomFieldGroup binder = new CustomFieldGroup(new BeanItem<>(binderDto));
    SelectionDTO selection = new SelectionDTO();
    AbstractLogic logic = AbstractLogic.getInstance();
    List<String> countFlag = new ArrayList<>();
    List<String> loadDataFlag = new ArrayList<>();
    public CustomTextField componentTextField;
    public Object[] CFP_SEARCH_COLUMNS = new Object[]{
        "componentId", "componentNo", "componentName", "componentType", "category", "designation", "planId", "planName", "componentStatus", "tradeClass", Constants.START_DATE, Constants.END_DATE};
    public String[] CFP_SEARCH_HEADERS = new String[]{"CFP ID", "CFP No", "CFP Name", "CFP Type", "CFP Category", "CFP Designation", "CFP Plan ID", "CFP Plan Name", "CFP Status", "CFP Trade Class", "CFP Start Date", "CFP End Date"};

    public CFPLookUp(final CustomTextField componentTextField) {
        this.componentTextField = componentTextField;
        setContent(Clara.create(getClass().getResourceAsStream("/item/cfpLookUp.xml"), this));
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
        resultsTable.setVisibleColumns(CFP_SEARCH_COLUMNS);
        resultsTable.setColumnHeaders(CFP_SEARCH_HEADERS);
        resultsTable.addStyleName("filterbar");
        resultsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        resultsTable.setSelectable(true);
        for (Object propertyId : resultsTable.getVisibleColumns()) {
            resultsTable.setColumnWidth(propertyId, -1);
        }
        componentStatusBean.addItem(Constants.IndicatorConstants.SELECT_ONE.getConstant());
        componentStatus_DTO.setImmediate(true);
        componentTypeBean.addItem(Constants.IndicatorConstants.SELECT_ONE.getConstant());
        selectBtn.setImmediate(false);
        selectBtn.setEnabled(true);

        
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
                return;
            }

            public void filterAdded(Object propertyId, Class<? extends Container.Filter> filterType, Object value) {
                return;
            }

            public Container.Filter filterGeneratorFailed(Exception reason, Object propertyId, Object value) {
                return null;
            }

            public AbstractField<?> getCustomFilterComponent(Object propertyId) {
                if ("componentStatus".equals(propertyId)) {
                    ComboBox status = new ComboBox();
                    logic.LazyLoadDdlb(status, "cfpStatus count", "cfpStatus", true);
                    return status;
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
        binder.setItemDataSource(new BeanItem<>(binderDto));
        binder.setBuffered(true);
        return binder;
    }

    @UiHandler("searchBtn")
    public void searchButtonLogic(Button.ClickEvent event) throws FieldGroup.CommitException {
        binder.commit();
        selection.setOperation(Constants.CFP);
        selection.setCountQueryName("CFP-C Abstract");
        selection.setDataQueryName("CFP Abstract");
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
                    componentNo.setValue(StringUtils.EMPTY);
                    componentStatus_DTO.setValue(null);
                    componentType.setValue(StringUtils.EMPTY);
                    category.setValue(StringUtils.EMPTY);
                    startDate.setValue(null);
                    endDate.setValue(null);
                    binder.commit();
                } catch (FieldGroup.CommitException ex) {
                    LOGGER.error(ex);
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
    }

    private void loadComponentStatus() {
        logic.LazyLoadDdlb(componentStatus_DTO, "cfpStatus count", "cfpStatus", false);
    }

   
}
