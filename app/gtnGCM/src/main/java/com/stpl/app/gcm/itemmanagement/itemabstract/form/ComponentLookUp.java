/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.itemmanagement.itemabstract.form;

import com.stpl.app.gcm.common.CommonLogic;
import com.stpl.app.gcm.globalchange.dto.SelectionDTO;
import com.stpl.app.gcm.itemmanagement.itemabstract.dto.ComponentLookUpDTO;
import com.stpl.app.gcm.itemmanagement.itemabstract.logic.ComponentLookUpLogic;
import com.stpl.app.gcm.itemmanagement.itemabstract.logic.AbstractLogic;
import com.stpl.app.gcm.util.AbstractNotificationUtils;
import com.stpl.app.gcm.util.Constants;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.fieldgroup.FieldGroup;
import com.vaadin.v7.data.util.BeanItem;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.data.util.filter.SimpleStringFilter;
import com.vaadin.v7.ui.AbstractField;
import com.vaadin.ui.Button;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.v7.ui.Field;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.v7.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.v7.ui.TextField;
import com.vaadin.v7.ui.VerticalLayout;
import com.vaadin.ui.Window;
import java.util.ArrayList;
import java.util.List;
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
public class ComponentLookUp extends Window {
public static final Logger LOGGER = LoggerFactory.getLogger(ComponentLookUp.class);
    @UiField("cfpTableLayout")
    public VerticalLayout cfpTableLayout;
    @UiField("componentId")
    public TextField componentId;
    @UiField("componentNo")
    public TextField componentNo;
    @UiField("componentName")
    public TextField componentName;
    @UiField("componentStatus")
    public ComboBox componentStatus_DTO;
    @UiField("componentType")
    public ComboBox componentType_DTO;
    @UiField("labelId")
    public Label lableId;
    @UiField("labelNo")
    public Label labelNo;
    @UiField("labelName")
    public Label labelName;
    @UiField("labelType")
    public Label labelType;
    @UiField("labelStatus")
    public Label labelStatus;
    @UiField("searchBtn")
    public Button searchBtn;
    @UiField("resetBtn")
    public Button resetBtn;
    @UiField("selectBtn")
    public Button selectBtn;
    @UiField("closeBtn")
    public Button closeBtn;
    ComponentLookUpLogic tableLogic = new ComponentLookUpLogic();
    private ExtPagedTable resultsTable = new ExtPagedTable(tableLogic);
    private BeanItemContainer<ComponentLookUpDTO> resultsContainer = new BeanItemContainer<>(ComponentLookUpDTO.class);
    private BeanItemContainer<String> componentStatusBean = new BeanItemContainer<>(String.class);
    private BeanItemContainer<String> componentTypeBean = new BeanItemContainer<>(String.class);
    private String component = StringUtils.EMPTY;
    ComponentLookUpDTO componentDto;
    ComponentLookUpDTO binderDto = new ComponentLookUpDTO();
    private ErrorfulFieldGroup binder = new ErrorfulFieldGroup(new BeanItem<>(binderDto));
    SelectionDTO selection = new SelectionDTO();
    AbstractLogic logic = AbstractLogic.getInstance();
    List<String> countFlag = new ArrayList<>();
    List<String> loadDataFlag = new ArrayList<>();
    public CustomTextField componentTextField;

    public ComponentLookUp(final String component, final String caption, final CustomTextField componentTextField) {
        this.component = component;
        this.componentTextField = componentTextField;
        setContent(Clara.create(getClass().getResourceAsStream("/componentLookup.xml"), this));
        addStyleName("valo-theme-customwindow");
        addStyleName("bootstrap-ui");
        addStyleName(Constants.BOOTSTRAP);
        addStyleName(Constants.BOOTSTRAP_FORECAST_BOOTSTRAP_NM);
        setClosable(true);
        setModal(true);
        getBinder();
        configureFields();
        loadComponentDdlb();
        setCaption(caption);
    }

    public void configureFields() {
        componentId.focus();
        Object[] CFP_SEARCH_COLUMNS = new Object[]{
            "componentId", "componentNo", "componentName", "componentStatus", "componentType"};
        String[] CFP_SEARCH_HEADERS = new String[]{
            component + " ID", component + " No", component + " Name", component + " Status", component + " Type"};
        addTableLayout();
        setFlag();
        tableLogic.setContainerDataSource(resultsContainer);
        tableLogic.setPageLength(NumericConstants.TEN);
        tableLogic.sinkItemPerPageWithPageLength(false);
        resultsTable.setVisibleColumns(CFP_SEARCH_COLUMNS);
        resultsTable.setColumnHeaders(CFP_SEARCH_HEADERS);
        resultsTable.addStyleName("filterbar");
        resultsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        resultsTable.setSelectable(true);

        for (Object propertyId : resultsTable.getVisibleColumns()) {
            resultsTable.setColumnWidth(propertyId, NumericConstants.ONE_EIGHT_ZERO);
        }
        lableId.setCaption(component + lableId.getCaption());
        labelName.setCaption(component + labelName.getCaption());
        labelNo.setCaption(component + labelNo.getCaption());
        labelStatus.setCaption(component + labelStatus.getCaption());
        labelType.setCaption(component + labelType.getCaption());
        componentStatusBean.addItem(Constants.IndicatorConstants.SELECT_ONE.getConstant());
        componentTypeBean.addItem(Constants.IndicatorConstants.SELECT_ONE.getConstant());
        selectBtn.setEnabled(true);

      
        resultsTable.setFilterGenerator(new ExtFilterGenerator() {
            public Container.Filter generateFilter(Object propertyId, Object value) {
                return null;
            }

            public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {
                if (originatingField instanceof ComboBox) {
                    if (originatingField.getValue() != null) {
                        HelperDTO dto = (HelperDTO) originatingField.getValue();

                        return new SimpleStringFilter(propertyId, String.valueOf(dto.getId()), false, false);
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
                    logic.LazyLoadDdlb(status, countFlag.get(0), loadDataFlag.get(0), true);
                    return status;
                }
                if ("componentType".equals(propertyId)) {
                    ComboBox type = new ComboBox();
                    logic.LazyLoadDdlb(type, countFlag.get(1), loadDataFlag.get(1), true);
                    return type;
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
        controlLayout.addStyleName("responsivePagedTable");
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
                    componentType_DTO.setValue(null);
                    binder.commit();
                } catch (FieldGroup.CommitException ex) {
                    LOGGER.error("",ex);
                }
            }

            @Override
            public void noMethod() {
                return;
            }
        }.getConfirmationMessage("Confirmation", "Are you sure you want to reset the page to default/previous values?");

    }

    @UiHandler("selectBtn")
    public void selectBtn(Button.ClickEvent event) {
        if (resultsTable.getValue() != null) {
            ComponentLookUpDTO componentLookUp = (ComponentLookUpDTO) resultsTable.getValue();
            componentTextField.setData(componentLookUp);
            close();
        } else {
            AbstractNotificationUtils.getErrorNotification("Select error", "Please select a record");
        }
    }

    @UiHandler("closeBtn")
    public void closeBtn(Button.ClickEvent event) {
        close();
    }

    private void loadComponentDdlb() {
        loadComponentStatus();
        loadComponentType();
    }

    private void loadComponentStatus() {
        logic.LazyLoadDdlb(componentStatus_DTO, countFlag.get(0), loadDataFlag.get(0), false);
    }

    private void loadComponentType() {
        logic.LazyLoadDdlb(componentType_DTO, countFlag.get(1), loadDataFlag.get(1), false);
    }

  

    private void setFlag() {
        selection.setComponent(component);
        selection.setComponentScreen("Component Lookup");
        if (component.equals("Contract Holder")) {
            countFlag.add("contractholderStatus count");
            countFlag.add("contractholderType count");
            loadDataFlag.add("contractholderStatus");
            loadDataFlag.add("contractholderType");
            selection.setComponentCount("contractholder count");
            selection.setComponentLoad("contractholder");
        } else if (component.equals(Constants.CFP)) {
            countFlag.add("cfpStatus count");
            countFlag.add("cfpType count");
            loadDataFlag.add("cfpStatus");
            loadDataFlag.add("cfpType");
            selection.setComponentCount("cfp count");
            selection.setComponentLoad("cfp");
        } else if (component.equals("Contract")) {
            countFlag.add("contractStatus count");
            countFlag.add("contractType count");
            loadDataFlag.add("contractStatus");
            loadDataFlag.add("contractType");
            selection.setComponentCount("contract count");
            selection.setComponentLoad("contract");
        } else if (component.equals(Constants.IFP)) {
            countFlag.add("ifpStatus count");
            countFlag.add("ifpType count");
            loadDataFlag.add("ifpStatus");
            loadDataFlag.add("ifpType");
            selection.setComponentCount("ifp count");
            selection.setComponentLoad("ifp");
        }  else if (component.equals(Constants.PS)) {
            countFlag.add("psStatus count");
            countFlag.add("psType count");
            loadDataFlag.add("psStatus");
            loadDataFlag.add("psType");
            selection.setComponentCount("ps count");
            selection.setComponentLoad("ps");
        } else if (component.equals("Customer")) {
            countFlag.add("companyStatus count");
            countFlag.add("companyType count");
            loadDataFlag.add("companyStatus");
            loadDataFlag.add("companyType");
            selection.setComponentCount("company count");
            selection.setComponentLoad("company");
        } else if (component.equals(Constants.RS)) {
            countFlag.add("rsStatus count");
            countFlag.add("rsType count");
            loadDataFlag.add("rsStatus");
            loadDataFlag.add("rsType");
            selection.setComponentCount("rs count For Look UP");
            selection.setComponentLoad("rs");
        } else if (component.equals("Item")) {
            countFlag.add("itemStatus count");
            countFlag.add("itemType count");
            loadDataFlag.add("itemStatus");
            loadDataFlag.add("itemType");
            selection.setComponentCount("item count");
            selection.setComponentLoad("item");
        } else if (component.equals("Rebate Plan")) {
            countFlag.add("rebatePlanStatus count");
            countFlag.add("rebatePlanType count");
            loadDataFlag.add("rebatePlanStatus");
            loadDataFlag.add("rebatePlanType");
            selection.setComponentCount("rebatePlan count");
            selection.setComponentLoad("rebatePlan");
        }
    }
}
