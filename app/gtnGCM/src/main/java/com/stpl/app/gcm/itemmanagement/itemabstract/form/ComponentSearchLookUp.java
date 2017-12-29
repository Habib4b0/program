
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.itemmanagement.itemabstract.form;

import com.stpl.app.gcm.util.StringConstantsUtil;
import com.stpl.app.gcm.common.CommonLogic;
import com.stpl.app.gcm.globalchange.dto.SelectionDTO;
import com.stpl.app.gcm.itemmanagement.itemabstract.dto.ComponentLookUpDTO;
import com.stpl.app.gcm.itemmanagement.itemabstract.logic.AbstractLogic;
import com.stpl.app.gcm.itemmanagement.itemabstract.logic.ComponentLookUpLogic;
import com.stpl.app.gcm.util.AbstractNotificationUtils;
import com.stpl.app.gcm.util.Constants;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.DateToStringConverter;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.exception.PortalException;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.customwindow.CustomWindow;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.jboss.logging.Logger;

/**
 *
 * @author mohamed.hameed
 */
public class ComponentSearchLookUp extends CustomWindow {
private static final Logger LOGGER = Logger.getLogger(ComponentSearchLookUp.class);
    @UiField("cfpTableLayout")
    public VerticalLayout cfpTableLayout;
    @UiField("componentId")
    public TextField componentId;
    @UiField("componentNo")
    public TextField componentNo;
    @UiField("componentCategory")
    public TextField componentCategory;
    @UiField("componentName")
    public TextField componentName;
    @UiField("componentStatus")
    public ComboBox componentStatus_DTO;
    @UiField("componentType")
    public TextField componentType;
    @UiField("labelId")
    public Label lableId;
    @UiField("labelNo")
    public Label labelNo;
    @UiField("labelName")
    public Label labelName;
    @UiField("labelCategory")
    public Label labelCategory;
    @UiField("labelStartDate")
    public Label labelStartDate;
    @UiField("labelEndDate")
    public Label labelEndDate;
    @UiField("labelType")
    public Label labelType;
    @UiField("labelStatus")
    public Label labelStatus;
    @UiField("searchBtn")
    public Button searchBtn;
    @UiField("resetBtn")
    public Button resetBtn;
    @UiField("selectBtn")
    public Button selectButton;
    @UiField("closeBtn")
    public Button closeButton;
    @UiField("startDate")
    public PopupDateField startDate;
    @UiField("endDate")
    public PopupDateField endDate;
    ComponentLookUpLogic tableLogic = new ComponentLookUpLogic();
    private final ExtPagedTable resultsTable = new ExtPagedTable(tableLogic);
    private final BeanItemContainer<ComponentLookUpDTO> resultsContainer = new BeanItemContainer<>(ComponentLookUpDTO.class);
    private final BeanItemContainer<String> componentStatusBean = new BeanItemContainer<>(String.class);
    private final BeanItemContainer<String> componentTypeBean = new BeanItemContainer<>(String.class);
    private String component = StringUtils.EMPTY;
    ComponentLookUpDTO componentDto;
    ComponentLookUpDTO binderDto = new ComponentLookUpDTO();
    private final CustomFieldGroup binder = new CustomFieldGroup(new BeanItem<>(binderDto));
    SelectionDTO selection = new SelectionDTO();
    AbstractLogic logic = AbstractLogic.getInstance();
    List<String> countFlag = new ArrayList<>();
    List<String> loadDataFlag = new ArrayList<>();
    public CustomTextField componentTextField;
    Object[] CFP_SEARCH_COLUMNS = new Object[]{
        "componentId", StringConstantsUtil.COMPONENT_NO, StringConstantsUtil.COMPONENT_NAME, StringConstantsUtil.COMPONENT_TYPE, Constants.CATEGORY, StringConstantsUtil.DESIGNATION_PROPERTY, "planId", "planName", StringConstantsUtil.COMPONENT_STATUS_PROPERTY, "tradeClass", Constants.START_DATE, Constants.END_DATE};
    String[] CFP_SEARCH_HEADERS = new String[]{
        "CFP ID", "CFP No", "CFP Name", "CFP Type", "CFP Category", "CFP Designation", "CFP Plan Id", "CFP Plan Name", "CFP Status", "CFP Trade Class", "CFP Start Date", "CFP End Date"};
    Object[] IFP_SEARCH_COLUMNS = new Object[]{
        "componentId", StringConstantsUtil.COMPONENT_NO, StringConstantsUtil.COMPONENT_NAME, StringConstantsUtil.COMPONENT_TYPE, Constants.CATEGORY, StringConstantsUtil.DESIGNATION_PROPERTY, "planId", "planName", StringConstantsUtil.COMPONENT_STATUS_PROPERTY, Constants.START_DATE, Constants.END_DATE};
    String[] IFP_SEARCH_HEADERS = new String[]{
        Constants.IFP_ID, Constants.IFP_NO, Constants.IFP_NAME_LABEL, "IFP Type", "IFP Category", "IFP Designation", "IFP Plan Id", "IFP Plan Name", "IFP Status", "IFP Start Date", "IFP End Date"};
    Object[] PS_SEARCH_COLUMNS = new Object[]{
        StringConstantsUtil.COMPONENT_NO, StringConstantsUtil.COMPONENT_NAME, StringConstantsUtil.COMPONENT_TYPE, Constants.CATEGORY, "tradeClass", StringConstantsUtil.DESIGNATION_PROPERTY, "parentPsId", "parentPsName", StringConstantsUtil.COMPONENT_STATUS_PROPERTY, Constants.START_DATE, Constants.END_DATE};
    String[] PS_SEARCH_HEADERS = new String[]{
        "PS No", "PS Name", "PS Type", "PS Category", "PS Trade Class", "PS Designation", "Parent PS Id", "Parent PS Name", "PS Status", "PS Start Date", "PS End Date"};

    public ComponentSearchLookUp(final String component, final CustomTextField componentTextField) {
        this.component = component;
        this.componentTextField = componentTextField;
        setContent(Clara.create(getClass().getResourceAsStream("/item/componentSearchLookup.xml"), this));
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
        tableLogic.setContainerDataSource(resultsContainer);
        tableLogic.setPageLength(NumericConstants.TEN);
        tableLogic.sinkItemPerPageWithPageLength(false);
        if (component.equals(Constants.CFP)) {
            resultsTable.setVisibleColumns(CFP_SEARCH_COLUMNS);
            resultsTable.setColumnHeaders(CFP_SEARCH_HEADERS);
        } else if (component.equals(Constants.IFP)) {
            resultsTable.setVisibleColumns(IFP_SEARCH_COLUMNS);
            resultsTable.setColumnHeaders(IFP_SEARCH_HEADERS);
        } else if (component.equals(Constants.PS)) {
            resultsTable.setVisibleColumns(PS_SEARCH_COLUMNS);
            resultsTable.setColumnHeaders(PS_SEARCH_HEADERS);
        }
        resultsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        resultsTable.setSelectable(true);
        lableId.setCaption(component + lableId.getCaption());
        labelName.setCaption(component + labelName.getCaption());
        labelNo.setCaption(component + labelNo.getCaption());
        labelStatus.setCaption(component + labelStatus.getCaption());
        labelType.setCaption(component + labelType.getCaption());
        labelCategory.setCaption(component + labelCategory.getCaption());
        labelStartDate.setCaption(component + labelStartDate.getCaption());
        labelEndDate.setCaption(component + labelEndDate.getCaption());
        componentStatusBean.addItem(Constants.IndicatorConstants.SELECT_ONE.getConstant());
        componentStatus_DTO.setImmediate(true);
        componentTypeBean.addItem(Constants.IndicatorConstants.SELECT_ONE.getConstant());
        componentType.setImmediate(true);
        selectButton.setImmediate(false);
        selectButton.setEnabled(false);
        componentStatus_DTO.setNullSelectionAllowed(true);
        componentStatus_DTO.setNullSelectionItemId(Constants.IndicatorConstants.SELECT_ONE.getConstant());

        resultsTable.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Method called when available results value is changed.
             */
            @SuppressWarnings("PMD")
            @Override
            public void valueChange(final Property.ValueChangeEvent event) {
                resultsItemClick(event.getProperty().getValue());
            }
        });
        setFlag();
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
                if (StringConstantsUtil.COMPONENT_STATUS_PROPERTY.equals(propertyId)) {
                    final ComboBox componentStatusDdlb = new ComboBox();
                    logic.LazyLoadDdlb(componentStatusDdlb, countFlag.get(0), loadDataFlag.get(0), true);
                    return componentStatusDdlb;
                }

                return null;
            }
        });
        resultsTable.addStyleName("filterbar");
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
        if ((componentId.getValue() == null || componentId.getValue().isEmpty()) && (componentName.getValue() == null || componentName.getValue().isEmpty())
                && (componentNo.getValue() == null || componentNo.getValue().isEmpty())
                && (componentType.getValue() == null || componentType.getValue().isEmpty())
                && (labelCategory.getValue() == null || binderDto.getCategory().isEmpty())) {

            MessageBox.showPlain(Icon.INFO, "Error", "Please enter/select search criteria", ButtonId.OK);
        } else {

            searchButtonLogic();
            resultsTable.setConverter(Constants.START_DATE, new DateToStringConverter());
            resultsTable.setConverter(Constants.END_DATE, new DateToStringConverter());
        }
    }

    public void searchButtonLogic() throws FieldGroup.CommitException {
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
                    componentCategory.setValue(StringUtils.EMPTY);
                    componentType.setValue(StringUtils.EMPTY);
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
        logic.LazyLoadDdlb(componentStatus_DTO, countFlag.get(0), loadDataFlag.get(0), false);
    }

    /**
     * Results item click.
     *
     * @param obj the id
     * @throws PortalException the portal exception
     */
    protected void resultsItemClick(final Object obj) {
        if (obj == null) {
            selectButton.setEnabled(false);
        } else {
            selectButton.setEnabled(true);
        }
    }

    private void setFlag() {
        selection.setComponent(component);
        selection.setComponentScreen("Component Search");
        if (component.equals(Constants.CFP)) {
            countFlag.add("cfpStatus count");
            loadDataFlag.add("cfpStatus");
            selection.setComponentCount("cfp search count");
            selection.setComponentLoad("cfp search");
        } else if (component.equals(Constants.IFP)) {
            countFlag.add("ifpStatus count");
            loadDataFlag.add("ifpStatus");
            selection.setComponentCount("ifp search count");
            selection.setComponentLoad("ifp search");
        } else if (component.equals(Constants.PS)) {
            countFlag.add("psStatus count");
            loadDataFlag.add("psStatus");
            selection.setComponentCount("ps search count");
            selection.setComponentLoad("ps search");
        }
    }
}
