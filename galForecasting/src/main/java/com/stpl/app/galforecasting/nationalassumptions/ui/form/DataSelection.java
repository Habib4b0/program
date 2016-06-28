package com.stpl.app.galforecasting.nationalassumptions.ui.form;

import com.stpl.app.galforecasting.nationalassumptions.dto.DataSelectionDTO;
import com.stpl.app.galforecasting.nationalassumptions.dto.ProductGroupLookUpDTO;
import com.stpl.app.galforecasting.nationalassumptions.dto.ResultList;
import com.stpl.app.galforecasting.nationalassumptions.logic.DataSelectionLogic;
import com.stpl.app.galforecasting.nationalassumptions.ui.view.NationalAssumptionsView;
import com.stpl.app.galforecasting.nationalassumptions.util.CommonUiUtils;
import com.stpl.app.galforecasting.nationalassumptions.util.CommonUtils;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.CommonConstants.USER_ID;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.LabelConstants.*;
import com.stpl.app.galforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.galforecasting.utils.Constant;
import com.stpl.app.model.ItemMaster;
import com.stpl.app.model.NaProjDetails;
import com.stpl.app.model.NaProjMaster;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.utils.Constants;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.errorhandling.ErrorLabel;
import com.stpl.ifs.util.HelperDTO;
import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Notification;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

// TODO: Auto-generated Javadoc
/**
 * The Class DataSelection.
 */
public class DataSelection extends CustomComponent implements View {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(DataSelection.class);
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

    /**
     * The search table.
     */
    @UiField("resultTable")
    private ExtFilterTable resultTable;

    /**
     * The dto value.
     */
    DataSelectionDTO dtoValue;

    /**
     * The available product bean.
     */
    private BeanItemContainer<DataSelectionDTO> availableProductBean = new BeanItemContainer<DataSelectionDTO>(DataSelectionDTO.class);

    /**
     * The selected product bean.
     */
    private BeanItemContainer<DataSelectionDTO> selectedProductBean = new BeanItemContainer<DataSelectionDTO>(DataSelectionDTO.class);

    /**
     * The table bean.
     */
    private BeanItemContainer<DataSelectionDTO> tableBean = new BeanItemContainer<DataSelectionDTO>(DataSelectionDTO.class);

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
    DataSelectionLogic logic = new DataSelectionLogic();

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

    CustomFieldGroup dataSelectionBinder;

    final ErrorLabel errorMsg = new ErrorLabel();

    Object companyValue;
    Object companyValueId;

    Object thearupeticValue;
    Object thearupeticValueId;

    Object productTypeValue;
    private Integer productGroupId = 0;

    private TextField projectionId = new TextField();

    private NationalAssumptions nationalAssumptions = new NationalAssumptions();

    private boolean updateOnTabChange = false;
    private boolean reloadAfterUpdate = false;

    List<Integer> existItems = new ArrayList<Integer>();

    List<Integer> currentItems = new ArrayList<Integer>();

    List<NaProjDetails> detailsList;

    /**
     * Instantiates a new data selection index.
     *
     * @param dtoValue the dto value
     * @param mode the mode
     */
    public DataSelection(DataSelectionDTO dtoValue, OptionGroup mode, CustomFieldGroup dataSelectionBinder) {
        super();
        LOGGER.info("Inside DataSelection Constructor");
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/nationalassumption/DataSelection.xml"), this));
        this.dataSelectionBinder = dataSelectionBinder;
        init();
        getBinder();
        LOGGER.info("DataSelection Constructor Ends");
    }

    /**
     * Inits the.
     */
    public void init() {
        configureFields();
    }

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
    public void enter(ViewChangeEvent event) {
    }

    /**
     * Configure fields.
     */
    private void configureFields() {
        resultTable.addStyleName(Constant.FILTER_TABLE);
        resultTable.addStyleName("table-header-center");

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

        company.setNullSelectionAllowed(true);
        company.setNullSelectionItemId(new HelperDTO(Constant.SELECTONE));
        loadCompanies();
        loadTherapeuticClass();
        company.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             *
             */
            private static final long serialVersionUID = 1L;

            public void valueChange(Property.ValueChangeEvent event) {

                companyOnChangeEvent(event.getProperty().getValue());
            }
        });
        therapeuticClass.addValueChangeListener(new Property.ValueChangeListener() {
            private static final long serialVersionUID = 1L;

            public void valueChange(Property.ValueChangeEvent event) {
                companyOnChangeEvent(event.getProperty().getValue());
            }
        });

        editBtn.addClickListener(new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            public void buttonClick(Button.ClickEvent event) {
                if (resultTable.getValue() != null) {
                    getUI().getNavigator().navigateTo(NationalAssumptionsView.NAME);

                } else {
                    Notification.show("Please Select Item in Table ", Notification.Type.ERROR_MESSAGE);
                }
            }
        });
        viewBtn.addClickListener(new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            public void buttonClick(Button.ClickEvent event) {
                if (resultTable.getValue() != null) {
                    getUI().getNavigator().navigateTo(NationalAssumptionsView.NAME);

                } else {
                    Notification.show("Please Select Item in Table ", Notification.Type.ERROR_MESSAGE);
                }
            }
        });
        deleteBtn.addClickListener(new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            public void buttonClick(Button.ClickEvent event) {
                if (resultTable.getValue() != null) {

                }
                UI.getCurrent().setFocusedComponent(UI.getCurrent());
            }

        });
        moveLeftProduct.addClickListener(new Button.ClickListener() {

            public void buttonClick(Button.ClickEvent event) {
                if (availableProduct.getValue() != null) {
                    Object item = availableProduct.getValue();
                    moveToSelected(item);

                }
            }
        });
        moveRightProduct.addClickListener(new Button.ClickListener() {

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
                    List<DataSelectionDTO> availableProducts = new ArrayList<DataSelectionDTO>();
                    List<DataSelectionDTO> selectedIdList = selectedProductBean.getItemIds();
                    Map<Integer, String> selectedId = new HashMap<Integer, String>();

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
        addAvailableProducts();
        addSelectedProducts();
        addSearchTable();

        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(USER_ID.getConstant()));
        try {
            final Map<String, AppPermission> tabItemHM = stplSecurity.getBusinessFunctionPermission(userId, NATIONAL_ASSUMPTIONS + "," + DATA_SELECTION_TAB);

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
            LOGGER.error(system.getMessage());
        }

    }

    /**
     * Company on change event.
     *
     * @param companyValue the company value
     */
    protected void companyOnChangeEvent(Object companyValue) {
        loadOnChangeEvent();

    }

    /**
     * To load the Data when event changes.
     */
    private void loadOnChangeEvent() {
        if (modeOption.getValue() != null && Constant.ADD_SMALL.equals(modeOption.getValue().toString())) {
            availableProductBean.removeAllItems();
            loadData();
            Object productGroupValue = productGroup.getValue();
            ResultList result = null;
            if (!StringUtils.EMPTY.equals(companyValue) || ((Integer) thearupeticValueId) != 0 || !StringUtils.EMPTY.equals((String) productGroupValue)) {

                result = new DataSelectionLogic().searchCCP(companyValue, thearupeticValueId, productGroupValue.toString());
            } else {
                availableProductBean.removeAllItems();
            }

            if (result != null && Constant.SUCCESS.equals(result.getFlag())) {
                availableProductBean.addAll(result.getAvailableProducts());
            }
        }
    }

    /**
     * To load data depends on the selection
     */
    private void loadData() {
        if (company.getValue() != null) {
            companyValue = ((HelperDTO) company.getValue()).getDescription();
            companyValueId = ((HelperDTO) company.getValue()).getId();
            companyValue = companyValue.equals(Constant.SELECTONE) ? StringUtils.EMPTY : companyValue;
        } else {
            companyValue = StringUtils.EMPTY;
            companyValueId = 0;
        }
        if (therapeuticClass.getValue() != null) {
            thearupeticValueId = ((HelperDTO) therapeuticClass.getValue()).getId();
            thearupeticValue = (((HelperDTO) therapeuticClass.getValue()).getDescription());
        } else {
            thearupeticValueId = 0;
            thearupeticValue = StringUtils.EMPTY;
        }
    }

    /**
     * Adds the available products.
     *
     * @return the ext filter table
     */
    private ExtFilterTable addAvailableProducts() {
        availableProduct.setFilterBarVisible(true);
        availableProduct.setFilterDecorator(new ExtDemoFilterDecorator());
        availableProduct.setStyleName(Constant.FILTER_TABLE);
        availableProduct.markAsDirty();
        availableProduct.setFilterBarVisible(true);
        availableProduct.setContainerDataSource(availableProductBean);
        availableProduct.setVisibleColumns(CommonUiUtils.VISIBLECOLUMN);
        availableProduct.setColumnHeaders(CommonUiUtils.VISIBLEHEADER);
        availableProduct.setPageLength(10);
        availableProduct.setImmediate(true);
        availableProduct.setSelectable(true);
        availableProduct.setSizeFull();
        availableProduct.setWidth("390px");
        availableProduct.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            /**
             *
             */
            private static final long serialVersionUID = 1L;

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
        selectedProducts.setFilterBarVisible(true);
        selectedProducts.setFilterDecorator(new ExtDemoFilterDecorator());
        selectedProducts.setStyleName(Constant.FILTER_TABLE);
        selectedProducts.markAsDirty();
        selectedProducts.setFilterBarVisible(true);
        selectedProducts.setContainerDataSource(selectedProductBean);
        selectedProducts.setVisibleColumns(CommonUiUtils.VISIBLECOLUMN);
        selectedProducts.setColumnHeaders(CommonUiUtils.VISIBLEHEADER);
        selectedProducts.setPageLength(10);
        selectedProducts.setImmediate(true);
        selectedProducts.setSelectable(true);
        selectedProducts.setSizeFull();
        selectedProducts.setWidth("390px");

        return selectedProducts;
    }

    /**
     * Adds the search table.
     *
     * @return the table
     */
    private ExtFilterTable addSearchTable() {
        resultTable.setFilterBarVisible(true);
        resultTable.setFilterDecorator(new ExtDemoFilterDecorator());
        resultTable.setStyleName(Constant.FILTER_TABLE);
        resultTable.markAsDirty();
        resultTable.setContainerDataSource(tableBean);
        resultTable.setVisibleColumns(CommonUiUtils.VISIBLESEARCHCOLUMN);
        resultTable.setColumnHeaders(CommonUiUtils.VISIBLESEARCHHEADER);
        resultTable.setPageLength(10);
        resultTable.setImmediate(true);
        resultTable.setSelectable(true);
        resultTable.setSizeFull();
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
        if (resultTable.size() > 0) {
            editBtn.setEnabled(true);
            viewBtn.setEnabled(true);
            deleteBtn.setEnabled(true);
        } else {
            editBtn.setEnabled(false);
            viewBtn.setEnabled(false);
            deleteBtn.setEnabled(false);
        }
    }

    /**
     * Generate btn.
     *
     * @param event the event
     */
    @UiHandler("generateBtn")
    public void generateBtn(Button.ClickEvent event) throws FieldGroup.CommitException {
        try {
            if (StringUtils.isNotBlank(projectionName.getValue())) {
                loadData();
                String msg = StringUtils.EMPTY;
                if (modeOption.getValue() != null && Constant.ADD_SMALL.equals(modeOption.getValue())) {
                    // Save the Projection
                    List<DataSelectionDTO> selectedProducts = new ArrayList<DataSelectionDTO>();
                    for (int i = 0; i < selectedProductBean.size(); i++) {
                        DataSelectionDTO dataSelectionDto = (DataSelectionDTO) selectedProductBean.getIdByIndex(i);
                        selectedProducts.add(dataSelectionDto);
                    }
                    try {
                        dataSelectionBinder.commit();
                    } catch (FieldGroup.CommitException e) {
                        LOGGER.error(e.getMessage());
                    }
                    Object[] values = {projectionName.getValue() == null ? StringUtils.EMPTY : projectionName.getValue(), companyValueId == null ? 0 : companyValueId,
                        thearupeticValueId, productGroupId};
                    VaadinSession.getCurrent().setAttribute(Constant.PROJECTION_ID, Integer.parseInt(msg));
                    getUI().getNavigator().navigateTo(NationalAssumptionsView.NAME);
                    UI.getCurrent().setFocusedComponent(UI.getCurrent());
                } else if (modeOption.getValue() != null && Constants.LabelConstants.MODE_SEARCH.equals(modeOption.getValue())) {
                    if (projectionId.getValue() == null || StringUtils.EMPTY.equals(projectionId.getValue())) {
                    } else {
                        msg = projectionId.getValue();
                    }
                }

                if (!msg.equals(Constant.FAIL)) {
                    selectedProductBean.removeAllItems();
                    selectedProductBean.removeAllItems();
                    availableProductBean.removeAllItems();
                    availableProductBean.removeAllItems();
                    VaadinSession.getCurrent().setAttribute(Constant.PROJECTION_ID,
                            Integer.parseInt(msg));

                } else {
                }
            } else {
                AbstractNotificationUtils.getErrorNotification("Missing Data", "Please select all required fields before clicking the Generate button.");
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

    }

    /**
     * Mode option.
     *
     * @param event the event
     */
    @UiHandler("modeOption")
    public void modeOption(Property.ValueChangeEvent event) {
        try {
            LOGGER.info("Inside Add or Search option value change listener");
            if (modeOption.getValue().toString().equals(Constant.ADD_SMALL)) {
                searchBtn.setImmediate(true);
                searchBtn.setEnabled(false);
                generateBtn.setImmediate(true);
                generateBtn.setEnabled(true);
                projectionName.setValue(StringUtils.EMPTY);
                company.setImmediate(true);
                company.setValue(null);
                productGroup.setValue(StringUtils.EMPTY);
                therapeuticClass.setImmediate(true);
                therapeuticClass.setValue(null);
                availableProduct.removeAllItems();
                selectedProducts.removeAllItems();
                resultTable.removeAllItems();

            } else if (modeOption.getValue().toString().equals(Constants.LabelConstants.MODE_SEARCH)) {
                generateBtn.setImmediate(true);
                generateBtn.setEnabled(false);
                searchBtn.setImmediate(true);
                searchBtn.setEnabled(true);
                projectionName.setValue(StringUtils.EMPTY);
                company.setImmediate(true);
                company.setValue(null);
                productGroup.setValue(StringUtils.EMPTY);
                therapeuticClass.setImmediate(true);
                therapeuticClass.setValue(null);
                availableProduct.removeAllItems();
                selectedProducts.removeAllItems();
                resultTable.removeAllItems();

            }

            if (resultTable.size() > 0) {
                editBtn.setEnabled(true);
                viewBtn.setEnabled(true);
                deleteBtn.setEnabled(true);
            } else {
                editBtn.setEnabled(false);
                viewBtn.setEnabled(false);
                deleteBtn.setEnabled(false);
            }
            LOGGER.info("Add or Search option value change listener ends");

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Loading companies to drop down list box
     */
    private void loadCompanies() {
        List<HelperDTO> result = logic.loadCompanies();
        company.setContainerDataSource(new IndexedContainer(result));

    }

    /**
     * Loading therapeutic class and we can select from drop down list box
     */
    private void loadTherapeuticClass() {
        List<HelperDTO> result = CommonUtils.getTherapeuticClass();
        if (result != null && result.size() > 0) {
            therapeuticClass.setContainerDataSource(new IndexedContainer(result));
        }
    }

    public void setValues() {
        selectedProductBean.removeAllItems();
        Integer projId = (Integer) VaadinSession.getCurrent().getAttribute(Constant.PROJECTION_ID);
        List list = logic.getProjection(projId);
        NaProjMaster naProjMaster = (NaProjMaster) list.get(0);
        detailsList = (List<NaProjDetails>) list.get(1);
        projectionName.setValue(naProjMaster.getNaProjName());
        company.setValue(new HelperDTO(naProjMaster.getCompanyMasterSid(), DataSelectionLogic.getDescription(naProjMaster.getCompanyMasterSid())));
        therapeuticClass.setValue(new HelperDTO(naProjMaster.getTherapeuticClass(), CommonUtils.getDescription(naProjMaster.getTherapeuticClass())));
         productGroup.setReadOnly(false);
        if (naProjMaster.getItemGroupSid() != 0) {
            productGroup.setValue(DataSelectionLogic.getProductDescription(naProjMaster.getItemGroupSid()));
        }
         productGroup.setReadOnly(true);
        for (NaProjDetails detailsList1 : detailsList) {
            DataSelectionDTO dto = new DataSelectionDTO();
            ItemMaster itemMaster = logic.getItemMaster(detailsList1.getItemMasterSid());
            dto.setProductNo(itemMaster.getItemNo());
            dto.setProductName(itemMaster.getItemName());
            dto.setItemMasterSid(itemMaster.getItemMasterSid());
            selectedProductBean.addBean(dto);
        }
        availableProductBean.removeAllItems();
        loadData();

        ResultList result = null;
        if (!StringUtils.EMPTY.equals(companyValue) || ((Integer) thearupeticValueId) != 0 || !StringUtils.EMPTY.equals(productGroup.getValue())) {
            result = new DataSelectionLogic().searchCCP(companyValue, thearupeticValueId, productGroup.getValue());
        } else {
            availableProductBean.removeAllItems();
        }

        if (result != null && Constant.SUCCESS.equals(result.getFlag())) {
            availableProductBean.addAll(result.getAvailableProducts());
        }
    }

    public boolean isChanged() {
        existItems.clear();
        currentItems.clear();
        Integer projId = (Integer) VaadinSession.getCurrent().getAttribute(Constant.PROJECTION_ID);
        List list = logic.getProjection(projId);
        detailsList = (List<NaProjDetails>) list.get(1);
        List<Integer> existItemsList = new ArrayList<Integer>();

        List<Integer> currentItemsList = new ArrayList<Integer>();
        for (NaProjDetails naProjDetails : detailsList) {
            existItemsList.add(naProjDetails.getItemMasterSid());
        }

        Object[] selectedIds = selectedProductBean.getItemIds().toArray();
        for (Object object : selectedIds) {
            currentItemsList.add(getBeanFromId(object).getItemMasterSid());
        }
        if (!existItemsList.isEmpty() && !currentItemsList.isEmpty()) {
            Collections.sort(existItemsList);
            Collections.sort(currentItemsList);
            setUpdateOnTabChange(!existItemsList.equals(currentItemsList));
            existItems.addAll(existItemsList);
            currentItems.addAll(currentItemsList);
        }
        return updateOnTabChange;
    }

    public void disableFields(String mode) {
        modeOption.setReadOnly(true);
        projectionName.setReadOnly(true);
        generateBtn.setEnabled(false);
        searchBtn.setEnabled(false);
        if ("View".equalsIgnoreCase(mode)) {
            company.setReadOnly(true);
            productGroup.setReadOnly(true);
            therapeuticClass.setReadOnly(true);
            availableProduct.setReadOnly(true);
            availableProduct.setSelectable(false);
            selectedProducts.setReadOnly(true);
            selectedProducts.setSelectable(false);
            moveLeftProduct.setEnabled(false);
            moveRightProduct.setEnabled(false);
            allProductLeftBtn.setEnabled(false);
            allProductRightBtn.setEnabled(false);
        }
    }

    public String getProjName() {
        return projectionName.getValue();
    }

    public void moveToSelected(Object item) {
        if (selectedProductBean.size() > 0) {
            boolean addFlag = true;
            Object[] selectedIds = selectedProductBean.getItemIds().toArray();
            for (Object id : selectedIds) {
                if (getBeanFromId(id).getItemMasterSid() == getBeanFromId(item).getItemMasterSid()) {
                    addFlag = false;
                    break;
                }
            }
            if (addFlag) {
                selectedProductBean.addItem(item);
            }
        } else {
            selectedProductBean.addItem(item);
        }
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
            targetItem = new BeanItem<DataSelectionDTO>(
                    (DataSelectionDTO) id);
        }
        return (DataSelectionDTO) targetItem.getBean();
    }

    public void moveToAvailable(Object item) {
        selectedProductBean.removeItem(item);
    }

    public void updateDataSelection() {
        if (company.getValue() == null || Constant.SELECTONE.equals(company.getValue())) {
            AbstractNotificationUtils.getErrorNotification("Missing Data", "Please select all required fields before clicking the Generate button.");
        } else if (selectedProducts.size() == 0) {
            AbstractNotificationUtils.getErrorNotification("Missing Data", "Please select all required fields before clicking the Generate button.");
        } else {
            loadData();
            String msg = StringUtils.EMPTY;
            try {
                // Save the Projection
                List<DataSelectionDTO> selectedProducts = new ArrayList<DataSelectionDTO>();
                for (int i = 0; i < selectedProductBean.size(); i++) {
                    DataSelectionDTO dataSelectionDto = (DataSelectionDTO) selectedProductBean.getIdByIndex(i);
                    selectedProducts.add(dataSelectionDto);
                }
                try {
                    dataSelectionBinder.commit();
                } catch (Exception e) {
                    LOGGER.error(e.getMessage());
                }
                Object[] values = {projectionName.getValue() == null ? StringUtils.EMPTY : projectionName.getValue(), companyValueId == null ? 0 : companyValueId,
                    thearupeticValueId, productGroupId};
                msg = logic.saveProjection(values, selectedProducts, true);
                List<Integer> existList = new ArrayList<Integer>(existItems);
                List<Integer> currentList = new ArrayList<Integer>(currentItems);
                existItems.removeAll(currentItems);
                currentList.removeAll(existList);
                List<Integer> removeList = new ArrayList<Integer>();
                List<Integer> insertList = new ArrayList<Integer>(currentList);
                for (NaProjDetails naProjDetails : detailsList) {
                    for (Integer integer : existItems) {
                        if (naProjDetails.getItemMasterSid() == integer) {
                            removeList.add(naProjDetails.getNaProjDetailsSid());
                        }
                    }
                    existItems.add(naProjDetails.getItemMasterSid());
                }
                logic.updateProducts((Integer) VaadinSession.getCurrent().getAttribute(Constant.PROJECTION_ID), insertList, removeList);
                nationalAssumptions.getNDCSetup();
                existList = null;
                currentList = null;
                removeList = null;
                insertList = null;
            } catch (Exception ex) {
                LOGGER.error(ex.getMessage());
            } finally {
                System.gc();
            }
        }
    }

    public boolean isUpdateOnTabChange() {
        return updateOnTabChange;
    }

    public void setUpdateOnTabChange(boolean updateOnTabChange) {
        this.updateOnTabChange = updateOnTabChange;
    }

    public boolean isReloadAfterUpdate() {
        return reloadAfterUpdate;
    }

    public void setReloadAfterUpdate(boolean reloadAfterUpdate) {
        this.reloadAfterUpdate = reloadAfterUpdate;
    }

    /**
     * Product group.
     *
     * @param event the event
     */
    @UiHandler("productGroup")
    public void productGroup(CustomTextField.ClickEvent event) {


        final ProductGroupLookup lookUp = new ProductGroupLookup();
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
                    HelperDTO dto = new HelperDTO();
                    dto.setDescription(value.getCompany());
                    dto.setId(value.getCompanySid());
                    company.setImmediate(true);
                    company.removeAllItems();
                    company.setNullSelectionAllowed(true);
                    company.setNullSelectionItemId(new HelperDTO(Constant.SELECTONE));
                    List<HelperDTO> listValue = new ArrayList<HelperDTO>();
                    listValue.add(new HelperDTO(Constant.SELECTONE));
                    listValue.add(dto);
                    company.setContainerDataSource(new IndexedContainer(listValue));
                    company.select(dto);
                    companyValue = dto.getDescription();
                    company.focus();
                    loadOnChangeEvent();
                } else {
                    productGroup.setReadOnly(false);
                    productGroup.setValue(StringUtils.EMPTY);
                    productGroup.setReadOnly(true);
                    loadCompanies();

                }
            }
        });


    }
}
