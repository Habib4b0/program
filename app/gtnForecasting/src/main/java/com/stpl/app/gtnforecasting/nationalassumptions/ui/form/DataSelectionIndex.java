package com.stpl.app.gtnforecasting.nationalassumptions.ui.form;

import com.stpl.app.gtnforecasting.nationalassumptions.dto.DataSelectionDTO;
import com.stpl.app.gtnforecasting.nationalassumptions.dto.ProductGroupLookUpDTO;
import com.stpl.app.gtnforecasting.nationalassumptions.dto.ResultList;
import com.stpl.app.gtnforecasting.nationalassumptions.logic.DataSelectionLogic;
import com.stpl.app.gtnforecasting.nationalassumptions.logic.DataSelectionSearchLogic;
import com.stpl.app.gtnforecasting.nationalassumptions.ui.view.NationalAssumptionsView;
import com.stpl.app.gtnforecasting.nationalassumptions.util.CommonUiUtils;
import com.stpl.app.gtnforecasting.nationalassumptions.util.CommonUtils;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.LabelConstants.*;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.security.StplSecurity;

import com.stpl.app.gtnforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.utils.Constants;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.DateToStringConverter;
import com.stpl.ifs.ui.errorhandling.ErrorLabel;
import com.stpl.ifs.ui.util.CommonUIUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.ValidationUtils;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.Position;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.Field;
import com.vaadin.ui.Notification;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

// TODO: Auto-generated Javadoc
/**
 * The Class DataSelectionIndex.
 */
public class DataSelectionIndex extends CustomComponent implements View {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(DataSelectionIndex.class);
    /**
     * The mode option.
     */
    @UiField("modeOption")
    private OptionGroup modeOption;

    /**
     * The name.
     */
    @UiField("name")
    private TextField projectionName;

    /**
     * The product group.
     */
    @UiField("productGroup")
    private CustomTextField productGroup;

    /**
     * The therapeutic class.
     */
    @UiField("therapeuticClass")
    private ComboBox therapeuticClass;

    /**
     * The company.
     */
    @UiField("company")
    private ComboBox company;

    /**
     * The available product.
     */
    @UiField("availableProducts")
    private ExtFilterTable availableProduct;

    /**
     * The selected product.
     */
    @UiField("selectedProducts")
    private ExtFilterTable selectedProducts;

    @UiField("resultsTableLayout")
    private VerticalLayout resultsTableLayout;

    /**
     * The search table.
     */
    private final DataSelectionSearchLogic tableLogic = new DataSelectionSearchLogic();
    private final ExtPagedTable resultTable = new ExtPagedTable(tableLogic);

    /**
     * The available product bean.
     */
    private final BeanItemContainer<DataSelectionDTO> availableProductBean = new BeanItemContainer<>(DataSelectionDTO.class);

    /**
     * The selected product bean.
     */
    private final BeanItemContainer<DataSelectionDTO> selectedProductBean = new BeanItemContainer<>(DataSelectionDTO.class);

    /**
     * The table bean.
     */
    private final BeanItemContainer<DataSelectionDTO> resultsContainer = new BeanItemContainer<>(DataSelectionDTO.class);
    /**
     * The generate.
     */
    @UiField("generateBtn")
    private Button generateBtn;

    /**
     * The search.
     */
    @UiField("searchBtn")
    private Button searchBtn;

    /**
     * The edit.
     */
    @UiField("editBtn")
    private Button editBtn;

    /**
     * The view.
     */
    @UiField("viewBtn")
    private Button viewBtn;

    /**
     * The delete.
     */
    @UiField("deleteBtn")
    private Button deleteBtn;

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = -5631894964184716246L;

    /**
     * The logic.
     */
    private final DataSelectionLogic logic = new DataSelectionLogic();

    private final NationalAssumptions nationalAssumptions;

    /**
     * The move left product.
     */
    @UiField("moveLeftProduct")
    private Button moveLeftProduct;

    /**
     * The move right product.
     */
    @UiField("moveRightProduct")
    private Button moveRightProduct;

    /**
     * The all product left btn.
     */
    @UiField("allProductLeftBtn")
    private Button allProductLeftBtn;

    /**
     * The all product right btn.
     */
    @UiField("allProductRightBtn")
    private Button allProductRightBtn;

    private Object companyValueId;

    private Object thearupeticValue;
    private Object thearupeticValueId;

    private Integer productGroupId = 0;
    private Object productGroupValue;

    private final CustomFieldGroup dataSelectionBinder;

    private final ErrorLabel errorMsg = new ErrorLabel();

    private final TextField projectionId = new TextField();
    private String modifiedDate;
    /**
     * The table bean id.
     */
    private Object tableBeanId;
    private DataSelectionDTO dsDto = new DataSelectionDTO();
            /**
     * The business Unit.
     */
    @UiField("businessUnit")
    private ComboBox businessUnit;
    
    private String productGroupCompany = StringUtils.EMPTY;
    
   private final com.stpl.app.gtnforecasting.logic.DataSelectionLogic dsLogic = new com.stpl.app.gtnforecasting.logic.DataSelectionLogic();
   private final SessionDTO sessionDTO;
 
    public DataSelectionIndex(DataSelectionDTO dtoValue, CustomFieldGroup dataSelectionBinder,SessionDTO sessionDTO) {
        super();
        this.sessionDTO=sessionDTO;
        nationalAssumptions= new NationalAssumptions(sessionDTO);
        this.dataSelectionBinder = dataSelectionBinder;
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/nationalassumption/DataSelectionIndex.xml"), this));
        init();
    }

    /**
     * Inits the.
     */
    public void init() {
        configureFields();
        getBinder();
    }

    /**
     * To map the components in DataSelectionDTO
     *
     * @return
     */
    private CustomFieldGroup getBinder() {
        dataSelectionBinder.bindMemberFields(this);
        dataSelectionBinder.setBuffered(true);
        dataSelectionBinder.setErrorDisplay(errorMsg);
        return dataSelectionBinder;
    }
    /*
     * (non-Javadoc)
     * 
     * @see
     * com.vaadin.navigator.View#enter(com.vaadin.navigator.ViewChangeListener
     * .ViewChangeEvent)
     */

    @Override
    public void enter(ViewChangeEvent event) {
        projectionName.setValue(StringUtils.EMPTY);
    }

    /**
     * Configure fields.
     */
    private void configureFields() {
        tableLogic.setContainerDataSource(resultsContainer);
        tableLogic.setPageLength(10);
        resultsTableLayout.addComponent(resultTable);
        resultsTableLayout.addComponent(tableLogic.createControls());
        resultTable.addStyleName(Constant.FILTER_TABLE);
        resultTable.addStyleName("table-header-center");
        resultTable.setItemsPerPage(10);
        resultTable.setSortEnabled(true);
        resultTable.setFilterGenerator(new ExtFilterGenerator() {

            @Override
            public Container.Filter generateFilter(Object propertyId, Object value) {
                return null;
            }

            @Override
            public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {
                if (originatingField instanceof ComboBox) {
                    if (originatingField.getValue() != null) {
                        return new SimpleStringFilter(propertyId, String.valueOf(originatingField.getValue()), false, false);
                    } else {
                        return null;
                    }
                }
                return null;
            }

            @Override
            public AbstractField<?> getCustomFilterComponent(Object propertyId) {

                if ("therapeuticClass".equals(propertyId)) {
                    ComboBox therapClass = new ComboBox();

                    List<HelperDTO> result = CommonUtils.getTherapeuticClass();
                    result.remove(0);
                    if (!result.isEmpty()) {
                        therapClass.setContainerDataSource(new IndexedContainer(result));
                    }

                    therapClass.addItem(Constant.SHOW_ALL);
                    therapClass.setNullSelectionItemId(Constant.SHOW_ALL);
                    result.clear();
                    return therapClass;
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
        });
        projectionName.setImmediate(true);
        projectionName.setValidationVisible(true);
        projectionName.addValidator(new RegexpValidator(ValidationUtils.Search_specialCharacter, ValidationUtils.Search_specialCharacter_Message));
        projectionName.addValidator(new StringLengthValidator("Projection Name Should be less than 100 characters", 0, NumericConstants.HUNDRED, true));

        company.setImmediate(true);
        company.setNullSelectionAllowed(true);

        therapeuticClass.setEnabled(true);
        therapeuticClass.setNullSelectionAllowed(true);
        therapeuticClass.setNullSelectionItemId(new HelperDTO(Constant.SELECTONE));
        therapeuticClass.setImmediate(true);

        modeOption.setImmediate(true);
        modeOption.addItem(Constant.ADD_SMALL);
        modeOption.addItem(Constants.LabelConstants.MODE_SEARCH);
        modeOption.select(Constant.ADD_SMALL);

        resultTable.setColumnAlignment(CommonUiUtils.visibleSearchColumn[NumericConstants.FOUR], ExtFilterTable.Align.CENTER);
        resultTable.setColumnAlignment(CommonUiUtils.visibleSearchColumn[NumericConstants.FIVE], ExtFilterTable.Align.CENTER);
        
            businessUnit.setPageLength(NumericConstants.SEVEN);
            businessUnit.setImmediate(true);
            businessUnit.addItem(0);
            businessUnit.setItemCaption(0, Constants.CommonConstants.SELECT_ONE.getConstant());
            businessUnit.setNullSelectionAllowed(true);
            businessUnit.setNullSelectionItemId(0);
            businessUnit.setInputPrompt(Constants.CommonConstants.SELECT_ONE.getConstant());
            businessUnit.markAsDirty();

            List<Object[]> list = dsLogic.getBusinessUnits(0);
            if (list != null && !list.isEmpty()) {
                for (Object[] object : list) {
                    businessUnit.addItem(object[0]);
                    businessUnit.setItemCaption(object[0], object[NumericConstants.TWO] + Constant.SPACE + Constant.DASH_NO_DATA + Constant.SPACE + object[NumericConstants.THREE]);
                }
            }

            businessUnit.addValueChangeListener(new Property.ValueChangeListener() {

                @Override
                public void valueChange(Property.ValueChangeEvent event) {
                    selectedProductBean.removeAllItems();
                    companyOnChangeEvent();
                }
            });

            company.setPageLength(NumericConstants.SEVEN);
            company.setImmediate(true);
            company.addItem(0);
            company.setItemCaption(0, Constants.CommonConstants.SELECT_ONE.getConstant());
            company.setNullSelectionAllowed(true);
            company.setNullSelectionItemId(0);
            company.setInputPrompt(Constants.CommonConstants.SELECT_ONE.getConstant());
            company.markAsDirty();

            List<Object[]> companyList = dsLogic.getCompanies();
            if (companyList != null && !companyList.isEmpty()) {
                for (Object[] object : companyList) {
                    company.addItem(object[0]);
                    company.setItemCaption(object[0], object[NumericConstants.TWO] + Constant.SPACE + Constant.DASH_NO_DATA + Constant.SPACE + object[NumericConstants.THREE]);
                }
            }
            company.select(0);
            company.addValueChangeListener(new Property.ValueChangeListener() {

                @Override
                public void valueChange(Property.ValueChangeEvent event) {

                    selectedProductBean.removeAllItems();
                    companyOnChangeEvent();
                }
            });

        
        
        loadTherapeuticClass();
        therapeuticClass.addValueChangeListener(new Property.ValueChangeListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void valueChange(ValueChangeEvent event) {
                companyOnChangeEvent();
            }
        });

        editBtn.addClickListener(new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (projectionId.getValue() != null && !StringUtils.EMPTY.equals(projectionId.getValue())) {

                    VaadinSession.getCurrent().setAttribute(Constant.PROJECTION_ID, Integer.parseInt(projectionId.getValue().replaceAll(",", StringUtils.EMPTY)));
                     sessionDTO.setProjectionId(Integer.parseInt(projectionId.getValue().replaceAll(",", StringUtils.EMPTY)));
                    VaadinSession.getCurrent().setAttribute(Constant.MODE, Constant.EDIT_SMALL);
                    VaadinSession.getCurrent().setAttribute("SessionDate", modifiedDate);
                    getUI().getNavigator().navigateTo(NationalAssumptionsView.NAME);
                    LOGGER.debug("projectionId in edit Button ------------------------>" + projectionId);
                } else if (resultTable.getValue() == null) {
                    AbstractNotificationUtils.getErrorNotification(Constant.SELECT_RECORD, Constant.NO_RECORD_WAS_SELECTED_PLEASE_TRY_AGAIN);
                }
            }
        });
        viewBtn.addClickListener(new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (resultTable.getValue() != null) {
                    int projectonIdValue = ((DataSelectionDTO) resultTable
                            .getValue()).getProjectionId();
                    VaadinSession.getCurrent().setAttribute(Constant.PROJECTION_ID,
                            projectonIdValue);
                   sessionDTO.setProjectionId(projectonIdValue);
                    VaadinSession.getCurrent().setAttribute(Constant.MODE, Constant.VIEW);
                    VaadinSession.getCurrent().setAttribute("SessionDate", modifiedDate);
                    getUI().getNavigator().navigateTo(NationalAssumptionsView.NAME);

                } else {
                    AbstractNotificationUtils.getErrorNotification(Constant.SELECT_RECORD, Constant.NO_RECORD_WAS_SELECTED_PLEASE_TRY_AGAIN);
                }
            }
        });
        deleteBtn.addClickListener(new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(Button.ClickEvent event) {

                deleteButtonClickLogic();

                UI.getCurrent().setFocusedComponent(UI.getCurrent());
            }

        });
        moveLeftProduct.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (availableProduct.getValue() != null) {
                    Object item = availableProduct.getValue();
                    moveToSelected(item);

                }
            }
        });
        moveRightProduct.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (selectedProducts.getValue() != null) {
                    Object item = selectedProducts.getValue();
                    moveToAvailable(item);
                }
            }
        });
        allProductLeftBtn.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {

                List<DataSelectionDTO> tempAvaliable = availableProductBean.getItemIds();
                if (selectedProductBean.size() > 0) {
                    List<DataSelectionDTO> availableProducts = new ArrayList<>();
                    List<DataSelectionDTO> selectedIdList = selectedProductBean.getItemIds();
                    Map<Integer, String> selectedId = new HashMap<>();

                    availableProducts.addAll(tempAvaliable);
                    for (DataSelectionDTO selected : selectedIdList) {
                        selectedId.put(selected.getItemMasterSid(), selected.getProductNo());
                    }

                    for (Iterator<DataSelectionDTO> it = availableProducts.iterator(); it.hasNext();) {
                        DataSelectionDTO dataDto = it.next();
                        int itemSid = dataDto.getItemMasterSid();
                        if (selectedId.get(itemSid) != null) {
                            {
                                it.remove();
                            }
                        }
                    }
                    selectedProductBean.addAll(availableProducts);
                } else {
                    selectedProductBean.addAll(tempAvaliable);
                }

            }
        });
        allProductRightBtn.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                selectedProductBean.removeAllItems();

            }
        });

        resultTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {

            @Override
            public void itemClick(ItemClickEvent event) {
                LOGGER.debug("Inside resultsTable itemClick method");
                tableBeanId = event.getItemId();
                dsDto = getBeanFromId(tableBeanId);
                LOGGER.debug("resultsTable itemClick method ends");
            }
        });

        addAvailableProducts();
        addSelectedProducts();
        addSearchTable();

        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = sessionDTO.getUserId();
        try {
            final Map<String, AppPermission> tabItemHM = stplSecurity.getBusinessFunctionPermission(userId, NATIONAL_ASSUMPTIONS.getConstant() + "," + DATA_SELECTION_INDEX.getConstant());
            if (tabItemHM.get("generateBtn") != null && tabItemHM.get("generateBtn").isFunctionFlag()) {
                generateBtn.setVisible(true);
            } else {
                generateBtn.setVisible(false);
            }

            if (tabItemHM.get("editBtn") != null && tabItemHM.get("editBtn").isFunctionFlag()) {
                editBtn.setVisible(true);
            } else {
                editBtn.setVisible(false);
            }
            if (tabItemHM.get("viewBtn") != null && tabItemHM.get("viewBtn").isFunctionFlag()) {
                viewBtn.setVisible(true);
            } else {
                viewBtn.setVisible(false);
            }
            if (tabItemHM.get("deleteBtn") != null && tabItemHM.get("deleteBtn").isFunctionFlag()) {
                deleteBtn.setVisible(true);
            } else {
                deleteBtn.setVisible(false);
            }
        } catch (Exception system) {
            LOGGER.error(system);
        }

    }
   
    /**
     * Company on change event.
     *
     * @param companyValue the company value
     */
    protected void companyOnChangeEvent() {
        loadOnChangeEvent();

    }

    /**
     * To load the Data when event changes.
     */
    private void loadOnChangeEvent() {
        if (modeOption.getValue() != null) {
            availableProductBean.removeAllItems();
            loadData();
            ResultList result = null;
            if (!"0".equals(company.getValue()) || ((Integer) thearupeticValueId) != 0 || !StringUtils.EMPTY.equals((String) productGroupValue)|| !String.valueOf(businessUnit.getValue()).isEmpty()) {

                result = new DataSelectionLogic().searchCCP(company.getValue(), thearupeticValueId, productGroupValue.toString(),businessUnit.getValue());
            } else {
                availableProductBean.removeAllItems();
            }
            
            if (result != null && Constant.SUCCESS.equals(result.getFlag())) {
                availableProductBean.addAll(result.getAvailableProducts());
            } else {
                Notification notif = new Notification("No results found",
                        Notification.Type.HUMANIZED_MESSAGE);
                notif.setPosition(Position.MIDDLE_CENTER);
                notif.setDelayMsec(NumericConstants.THOUSAND);
                notif.show(Page.getCurrent());
            }
            if (company.getValue() != null  && !"null".equalsIgnoreCase(String.valueOf(productGroupValue)) && !StringUtils.EMPTY.equals((String) productGroupValue) ) {
                String compName = StringUtils.EMPTY;
                List list = logic.getCompanyName(Integer.valueOf(company.getValue().toString()));
                    for (int i = 0; i < list.size(); i++) {
                        Object[] ob = (Object[]) list.get(i);
                        compName = ob[NumericConstants.TWO].toString();                                               
                    }
                if (!productGroupCompany.equals(compName)){
                    availableProductBean.removeAllItems();
                    Notification notif = new Notification("No results found",
                        Notification.Type.HUMANIZED_MESSAGE);
                    notif.setPosition(Position.MIDDLE_CENTER);
                    notif.setDelayMsec(NumericConstants.THOUSAND);
                    notif.show(Page.getCurrent());
                }
            }
        }
    }

    /**
     * To load data depends on the selection
     */
    private void loadData() {
        if (company.getValue() != null) {
            companyValueId =  company.getValue();
        } else {
            companyValueId = 0;
        }
        if (therapeuticClass.getValue() != null) {
            thearupeticValueId = ((HelperDTO) therapeuticClass.getValue()).getId();
            thearupeticValue = ((HelperDTO) therapeuticClass.getValue()).getDescription();
            thearupeticValue = thearupeticValue.equals(Constant.SELECTONE) ? StringUtils.EMPTY : thearupeticValue;
        } else {
            thearupeticValueId = 0;
            thearupeticValue = StringUtils.EMPTY;
        }
        productGroupValue = productGroup.getValue();
    }

    /**
     * After clicking search button, this search button logic executes.
     *
     * @param event the event
     */
    private void searchButtonClickLogic(boolean searchFlag) {

        loadData();

        String projectionNametemp = projectionName.getValue() == null ? StringUtils.EMPTY : projectionName.getValue();

        if (!tableLogic.fireSetData(projectionNametemp, getSelectedProducts(), companyValueId, thearupeticValueId, productGroupId, false,businessUnit.getValue())) {
            enableButtons(false);
            if (searchFlag) {
                AbstractNotificationUtils
                        .getErrorNotification("No Results Found", "There are no projections that match your search criteria. Please try again.");
            }
        } else {
            enableButtons(true);

            if (searchFlag) {
                CommonUIUtils.getMessageNotification("Search Completed");
            }
        }
    }

    /**
     * Attach prodcuct group listener.
     */
    private String getSelectedProducts() {
        List<DataSelectionDTO> selectedIds = selectedProductBean.getItemIds();
        StringBuilder ids = new StringBuilder(StringUtils.EMPTY);
        int i = 0;
        for (DataSelectionDTO dto : selectedIds) {
            if (i++ == 0) {
                ids.append(dto.getItemMasterSid());
            } else {
                ids.append(",").append(dto.getItemMasterSid());
            }
        }
        return ids.toString();
    }

    /**
     * Product group.
     *
     * @param event the event
     */
    @UiHandler("productGroup")
    public void productGroup(CustomTextField.ClickEvent event) {

        final ProductGroupLookup lookUp = new ProductGroupLookup(sessionDTO);
        UI.getCurrent().addWindow(lookUp);
        // TODO Auto-generated method stub
        lookUp.addCloseListener(new Window.CloseListener() {

            private static final long serialVersionUID = 1L;

            @Override
            public void windowClose(Window.CloseEvent e) {
                ProductGroupLookUpDTO value = (ProductGroupLookUpDTO) lookUp.getResultTable();
                if (value != null) {
                    productGroup.setReadOnly(false);
                    productGroup.setValue(value.getProductGroupName());
                    productGroupId = value.getItemGroupSid();
                    productGroup.setReadOnly(true);
                    productGroupCompany = value.getCompany();
                    company.select(value.getCompanySid());
                    company.setItemCaption(value.getCompanySid(), value.getCompany());
                    company.setImmediate(true);
                    loadOnChangeEvent();
                } 

            }
        });

    }

    /**
     * Adds the available products.
     *
     * @return the ext filter table
     */
    private ExtFilterTable addAvailableProducts() {

        availableProduct.markAsDirty();
        availableProduct.setFilterBarVisible(true);
        availableProduct.setFilterDecorator(new ExtDemoFilterDecorator());
        availableProduct.setStyleName(Constant.FILTER_TABLE);
        availableProduct.setContainerDataSource(availableProductBean);
        availableProduct.setVisibleColumns(CommonUiUtils.visibleColumn);
        availableProduct.setColumnHeaders(CommonUiUtils.visibleHeader);
        availableProduct.setColumnAlignment(CommonUiUtils.visibleColumn[0], ExtCustomTable.Align.RIGHT);
        availableProduct.setPageLength(NumericConstants.TEN);
        availableProduct.setImmediate(true);
        availableProduct.setSelectable(true);
        availableProduct.setSizeFull();
        availableProduct.setWidth(Constant.PX_390);
        availableProduct.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            /**
             *
             */
            private static final long serialVersionUID = 1L;

            @Override
            public void itemClick(ItemClickEvent event) {
                if (event.isDoubleClick()) {
                    Object item = event.getItemId();
                    moveToSelected(item);
                }
            }
        });

        return availableProduct;

    }

    /**
     * Adds the selected products.
     *
     * @return the ext filter table
     */
    private ExtFilterTable addSelectedProducts() {

        selectedProducts.markAsDirty();
        selectedProducts.setFilterBarVisible(true);
        selectedProducts.setFilterDecorator(new ExtDemoFilterDecorator());
        selectedProducts.setStyleName(Constant.FILTER_TABLE);
        selectedProducts.setContainerDataSource(selectedProductBean);
        selectedProducts.setVisibleColumns(CommonUiUtils.visibleColumn);
        selectedProducts.setColumnHeaders(CommonUiUtils.visibleHeader);
        selectedProducts.setColumnAlignment(CommonUiUtils.visibleColumn[0], ExtCustomTable.Align.RIGHT);
        selectedProducts.setPageLength(NumericConstants.TEN);
        selectedProducts.setImmediate(true);
        selectedProducts.setSelectable(true);
        selectedProducts.setSizeFull();
        selectedProducts.setWidth(Constant.PX_390);

        return selectedProducts;
    }

    /**
     * Adds the search table.
     *
     * @return the table
     */
    private ExtFilterTable addSearchTable() {
        resultTable.markAsDirty();
        resultTable.setConverter("createdDateSearch", new DateToStringConverter());
        resultTable.setConverter("modifiedDateSearch", new DateToStringConverter());
        resultTable.setFilterBarVisible(true);
        resultTable.setFilterDecorator(new ExtDemoFilterDecorator());
        resultTable.setStyleName(Constant.FILTER_TABLE);
        resultTable.setVisibleColumns(CommonUiUtils.visibleSearchColumn);
        resultTable.setColumnHeaders(CommonUiUtils.visibleSearchHeader);
        resultTable.setPageLength(NumericConstants.TEN);
        resultTable.setItemsPerPage(10);
        resultTable.setImmediate(true);
        resultTable.setSelectable(true);
        resultTable.setWidth("100%");
        resultTable.setHeight("420px");
        resultTable.addValueChangeListener(new Property.ValueChangeListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void valueChange(ValueChangeEvent event) {
                resultsItemClick(event.getProperty().getValue());
            }
        });
        return resultTable;
    }

    /**
     * Selected product.
     *
     * @param event the event
     */
    @UiHandler("selectedProducts")
    public void selectedProducts(ItemClickEvent event) {
        if (event.isDoubleClick()) {
            Object item = event.getItemId();
            moveToAvailable(item);
        }
    }

    /**
     * Search btn.
     *
     * @param event the event
     */
    @UiHandler("searchBtn")
    public void searchBtn(Button.ClickEvent event) {
        searchButtonClickLogic(true);

    }

    /**
     * Generate btn.
     *
     * @param event the event
     */
    @UiHandler("generateBtn")
    public void generateBtn(Button.ClickEvent event)  {
        try {
            LOGGER.debug("generateBtn ClickEvent starts with company value   " + company.getValue());
            if (StringUtils.isBlank(projectionName.getValue()) || "0".equals(company.getValue()) || company.getValue() == null|| "0".equals(businessUnit.getValue()) || businessUnit.getValue() == null) {
                AbstractNotificationUtils.getErrorNotification("Missing Data", "Please select all required fields before clicking the Generate button.");
            } else if (selectedProducts.size() == 0) {
                AbstractNotificationUtils.getErrorNotification("Missing Data", "Please select all required fields before clicking the Generate button.");
            } else {
                loadData();
                String msg = StringUtils.EMPTY;
                if (modeOption.getValue() != null && Constant.ADD_SMALL.equals(modeOption.getValue())) {
                    // Save the Projection
                    List<DataSelectionDTO> selProducts = new ArrayList<>();
                    for (int i = 0; i < selectedProductBean.size(); i++) {
                        DataSelectionDTO dataSelectionDto = (DataSelectionDTO) selectedProductBean.getIdByIndex(i);
                        selProducts.add(dataSelectionDto);
                    }
                    try {
                        dataSelectionBinder.commit();
                    } catch (Exception e) {
                        LOGGER.error(e);
                    }
                    Object[] values = {projectionName.getValue() == null ? StringUtils.EMPTY : projectionName.getValue(), companyValueId == null ? 0 : companyValueId,
                        thearupeticValueId, productGroupId,String.valueOf(businessUnit.getValue())};
                    msg = logic.saveProjection(values, selProducts, false,sessionDTO);
                    if (!Constant.FAIL.equalsIgnoreCase(msg)) {
                        VaadinSession.getCurrent().setAttribute(Constant.PROJECTION_ID, Integer.parseInt(msg));
                        VaadinSession.getCurrent().setAttribute(Constant.MODE, Constant.ADD_FULL_SMALL);
                        sessionDTO.setProjectionId( Integer.parseInt(msg));
                        sessionDTO.setProjectionName(projectionName.getValue());
                        sessionDTO.setMode(Constant.ADD_FULL_SMALL);
                        getUI().getNavigator().navigateTo(NationalAssumptionsView.NAME);

                        UI.getCurrent().setFocusedComponent(UI.getCurrent());
                        nationalAssumptions.getNDCSetup(String.valueOf(sessionDTO.getProjectionId()));
                    }
                } else if (modeOption.getValue() != null && Constants.LabelConstants.MODE_SEARCH.equals(modeOption.getValue())) {
                    if (projectionId.getValue() == null || StringUtils.EMPTY.equals(projectionId.getValue())) {
                    } else {
                        msg = projectionId.getValue();
                    }
                }

                if (!msg.equals(Constant.FAIL)) {
                    selectedProductBean.removeAllItems();
                    availableProductBean.removeAllItems();
                    VaadinSession.getCurrent().setAttribute(Constant.PROJECTION_ID,
                            Integer.parseInt(msg));
                    sessionDTO.setProjectionId(Integer.parseInt(msg));

                }

            }
            LOGGER.debug("GenerateBtn ClickEvent ends");
        } catch (Exception e) {
            LOGGER.error(e);
        }

    }

    /**
     * Mode option.
     *
     * @param event the event
     */
    @UiHandler("modeOption")
    public void modeOption(ValueChangeEvent event) {
        try {
            LOGGER.debug("Inside Add or Search option value change listener");
            if (Constant.ADD_SMALL.equals(modeOption.getValue())) {
                searchBtn.setImmediate(true);
                searchBtn.setEnabled(false);
                generateBtn.setImmediate(true);
                generateBtn.setEnabled(true);
                company.setImmediate(true);
                therapeuticClass.setImmediate(true);
                resultTable.removeAllItems();

            } else if (Constants.LabelConstants.MODE_SEARCH.equals(modeOption.getValue())) {
                generateBtn.setImmediate(true);
                generateBtn.setEnabled(false);
                searchBtn.setImmediate(true);
                searchBtn.setEnabled(true);
                company.setImmediate(true);
                therapeuticClass.setImmediate(true);
            }

            if (resultTable.size() > 0) {
                enableButtons(true);
            } else {
                enableButtons(false);
            }
            LOGGER.debug("Add or Search option value change listener ends");

        } catch (Exception e) {
            LOGGER.error(e);
        }
    }


    /**
     * Loading therapeutic class and we can select from drop down list box
     */
    private void loadTherapeuticClass() {
        List<HelperDTO> result = CommonUtils.getTherapeuticClass();
        if (result != null && !result.isEmpty()) {
            therapeuticClass.setContainerDataSource(new IndexedContainer(result));
        }
    }

    /**
     * After clicking delete button, this search button logic executes
     *
     * @param event
     */
    protected void deleteButtonClickLogic() {
        LOGGER.debug("deleteButtonClickLogic starts");
        try {
            if (tableBeanId == null || resultTable.size() <= 0 || !resultTable.isSelected(tableBeanId)) {
                AbstractNotificationUtils.getErrorNotification(Constant.SELECT_RECORD, Constant.NO_RECORD_WAS_SELECTED_PLEASE_TRY_AGAIN);
            } else {
                MessageBox.showPlain(Icon.QUESTION, "Delete Confirmation", "Are you sure you want to delete this " + dsDto.getProjectionName() + "?", new MessageBoxListener() {
                    @Override
                    public void buttonClicked(ButtonId buttonId) {
                        if (buttonId.name().equals(Constant.YES)) {
                            DataSelectionDTO projection = (DataSelectionDTO) resultTable.getValue();

                            String deletedProjectionName = logic.deleteProjection(projection.getProjectionId(), sessionDTO.getUserId());

                            if (deletedProjectionName != null && !StringUtils.EMPTY.equals(deletedProjectionName)) {
                                if ("accessDenined".equals(deletedProjectionName)) {
                                    AbstractNotificationUtils.getErrorNotification("Cannot Delete Record", "You do not have permission to delete this projection.  It can only be deleted by the creator.");
                                    return;
                                }
                                
                                Notification notif = new Notification("The selected Price Type Projection has been deleted.",
                                        Notification.Type.HUMANIZED_MESSAGE);
                                notif.setPosition(Position.BOTTOM_CENTER);
                                notif.setStyleName(Constant.MY_STYLE);
                                notif.show(Page.getCurrent());

                                resultsContainer.removeAllItems();
                                selectedProductBean.removeAllItems();
                                availableProductBean.removeAllItems();

                                //Code for retrieving remaining values
                                resultsContainer.removeAllItems();
                                loadData();

                                reloadResultsTable();
                            }
                        }
                    }
                }, ButtonId.YES, ButtonId.NO);
                tableBeanId = null;
                LOGGER.debug("deleteButtonClickLogic ends");
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }

    }

    /**
     * Saving the projection
     *
     * @return boolean which contains true or false
     */
    public boolean onAdvance() {
        if (StringUtils.isNotBlank(projectionName.getValue())) {
            String msg = StringUtils.EMPTY;
            if (modeOption.getValue() != null && Constant.ADD_SMALL.equals(modeOption.getValue())) {
                // Save the Projection
                List<DataSelectionDTO> productSelected = new ArrayList<>();
                for (int i = 0; i < selectedProductBean.size(); i++) {
                    DataSelectionDTO ccpDTO = (DataSelectionDTO) selectedProductBean
                            .getIdByIndex(i);
                    productSelected.add(ccpDTO);
                }
                try {
                    dataSelectionBinder.commit();
                } catch (FieldGroup.CommitException e) {
                    LOGGER.error(e);
                }
                Object[] values = {projectionName.getValue() == null ? StringUtils.EMPTY : projectionName.getValue(), companyValueId == null ? 0 : companyValueId,
                    thearupeticValueId, productGroupId,String.valueOf(businessUnit.getValue())};
                try {
                    msg = logic.saveProjection(values, productSelected, false,sessionDTO);
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            } else if (modeOption.getValue() != null
                    && Constants.LabelConstants.MODE_SEARCH.equals(modeOption.getValue())) {
                if (projectionId.getValue() == null
                        || StringUtils.EMPTY.equals(projectionId.getValue())) {
                    return true;
                } else {
                    msg = projectionId.getValue();
                }
            }
            if (!msg.equals(Constant.FAIL)) {
                selectedProductBean.removeAllItems();
                availableProductBean.removeAllItems();
                VaadinSession.getCurrent().setAttribute(Constant.PROJECTION_ID,
                        Integer.parseInt(msg));
                sessionDTO.setProjectionId(Integer.parseInt(msg));
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * After click on item, results will display
     *
     * @param id
     */
    protected void resultsItemClick(Object id) {
        if (id != null) {
            deleteBtn.setEnabled(true);
            BeanItem<?> targetItem = null;
            if (id instanceof BeanItem<?>) {
                targetItem = (BeanItem<?>) id;
            } else if (id instanceof DataSelectionDTO) {
                targetItem = new BeanItem<>(
                        (DataSelectionDTO) id);
            }
            int projectionSysId = ((DataSelectionDTO) targetItem.getBean()).getProjectionId();
             String projectionName = ((DataSelectionDTO) targetItem.getBean()).getProjectionName();
            modifiedDate = ((DataSelectionDTO) targetItem.getBean()).getModifiedDate();
            projectionId.setValue(String.valueOf(projectionSysId));
            sessionDTO.setProjectionName(projectionName);
        } else {
            projectionId.setValue(null);
            sessionDTO.setProjectionName(StringUtils.EMPTY);
        }

    }

    public void enableButtons(boolean flag) {
        editBtn.setEnabled(flag);
        viewBtn.setEnabled(flag);
        deleteBtn.setEnabled(flag);
    }

    /**
     * Method to getting Bean From Id
     *
     * @param id
     * @return
     */
    public DataSelectionDTO getBeanFromId(Object id) {
        BeanItem<?> targetItem = null;
        if (id instanceof BeanItem<?>) {
            targetItem = (BeanItem<?>) id;
        } else if (id instanceof DataSelectionDTO) {
            targetItem = new BeanItem<>(
                    (DataSelectionDTO) id);
        }
        return (DataSelectionDTO) targetItem.getBean();
    }

    public void moveToSelected(Object item) {
        if (selectedProductBean.size() > 0) {
            boolean addFalg = true;
            Object[] selectedIds = selectedProductBean.getItemIds().toArray();
            for (Object id : selectedIds) {
                if (getBeanFromId(id).getItemMasterSid() == getBeanFromId(item).getItemMasterSid()) {
                    addFalg = false;
                    break;
                }
            }
            if (addFalg) {
                selectedProductBean.addItem(item);
            }
        } else {
            selectedProductBean.addItem(item);
        }

    }

    public void moveToAvailable(Object item) {
        selectedProductBean.removeItem(item);
    }


    private void reloadResultsTable() {
        searchButtonClickLogic(false);
    }
}
