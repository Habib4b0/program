package com.stpl.app.gtnforecasting.nationalassumptions.ui.form;

import com.stpl.app.gtnforecasting.nationalassumptions.dto.DataSelectionDTO;
import com.stpl.app.gtnforecasting.nationalassumptions.dto.ProductGroupLookUpDTO;
import com.stpl.app.gtnforecasting.nationalassumptions.dto.ResultList;
import com.stpl.app.gtnforecasting.nationalassumptions.logic.DataSelectionLogic;
import com.stpl.app.gtnforecasting.nationalassumptions.ui.view.NationalAssumptionsView;
import com.stpl.app.gtnforecasting.nationalassumptions.util.CommonUiUtils;
import com.stpl.app.gtnforecasting.nationalassumptions.util.CommonUtils;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.LabelConstants.*;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.model.ItemMaster;
import com.stpl.app.model.NaProjDetails;
import com.stpl.app.model.NaProjMaster;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.service.NaProjMasterLocalServiceUtil;
import com.stpl.app.utils.Constants;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.errorhandling.ErrorLabel;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
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
    private final BeanItemContainer<DataSelectionDTO> tableBean = new BeanItemContainer<>(DataSelectionDTO.class);

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
        /**
     * The business Unit.
     */
    @UiField("businessUnit")
    private ComboBox businessUnit;

    private final CustomFieldGroup dataSelectionBinder;

    private final ErrorLabel errorMsg = new ErrorLabel();

    private Object companyValueId;
    private Object thearupeticValueId;
    private Integer productGroupId = 0;

    private final TextField projectionId = new TextField();

    private final NationalAssumptions nationalAssumptions ;

    private boolean updateOnTabChange = false;
    private boolean reloadAfterUpdate = false;

    private final List<Integer> existItems = new ArrayList<>();

    private final List<Integer> currentItems = new ArrayList<>();

    private List<NaProjDetails> detailsList;
    private final com.stpl.app.gtnforecasting.logic.DataSelectionLogic dsLogic = new com.stpl.app.gtnforecasting.logic.DataSelectionLogic();
    private final SessionDTO sessionDTO;

    public DataSelection(CustomFieldGroup dataSelectionBinder,SessionDTO sessionDTO) {
        super();
        this.sessionDTO=sessionDTO;
        nationalAssumptions= new NationalAssumptions(sessionDTO);
        LOGGER.debug("Inside DataSelection Constructor");
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/nationalassumption/DataSelection.xml"), this));
        this.dataSelectionBinder = dataSelectionBinder;
        init();
        getBinder();
        setSavedValues();
        LOGGER.debug("DataSelection Constructor Ends");
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
    @Override
    public void enter(ViewChangeEvent event) {
      //Default method
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

            List<Object[]> companyList =  dsLogic.getCompanies();
            if (companyList != null && !companyList.isEmpty()) {
                for (Object[] object : companyList) {
                    company.addItem(object[0]);
                    company.setItemCaption(object[0], object[NumericConstants.TWO] + Constant.SPACE + Constant.DASH_NO_DATA + Constant.SPACE + object[NumericConstants.THREE]);
                }
            }

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
            public void valueChange(Property.ValueChangeEvent event) {
                companyOnChangeEvent();
            }
        });

        editBtn.addClickListener(new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
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

            @Override
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

            @Override
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
        addAvailableProducts();
        addSelectedProducts();
        addSearchTable();

        final StplSecurity stplSecurity = new StplSecurity();
        final String userId =sessionDTO.getUserId();
        try {
            final Map<String, AppPermission> tabItemHM = stplSecurity.getBusinessFunctionPermission(userId, NATIONAL_ASSUMPTIONS.getConstant() + "," + DATA_SELECTION_TAB);

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

    protected void companyOnChangeEvent() {
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
            if (!"0".equals(company.getValue()) || ((Integer) thearupeticValueId) != 0 || !StringUtils.EMPTY.equals((String) productGroupValue)||!"0".equals(businessUnit.getValue())) {

                result = new DataSelectionLogic().searchCCP(company.getValue(), thearupeticValueId, productGroupValue.toString(),businessUnit.getValue());
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
            companyValueId = company.getValue();
        } else {
            companyValueId = 0;
        }
        if (therapeuticClass.getValue() != null) {
            thearupeticValueId = ((HelperDTO) therapeuticClass.getValue()).getId();
        } else {
            thearupeticValueId = 0;
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
        availableProduct.setVisibleColumns(CommonUiUtils.visibleColumn);
        availableProduct.setColumnHeaders(CommonUiUtils.visibleHeader);
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
        selectedProducts.setVisibleColumns(CommonUiUtils.visibleColumn);
        selectedProducts.setColumnHeaders(CommonUiUtils.visibleHeader);
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
        resultTable.setFilterBarVisible(true);
        resultTable.setFilterDecorator(new ExtDemoFilterDecorator());
        resultTable.setStyleName(Constant.FILTER_TABLE);
        resultTable.markAsDirty();
        resultTable.setContainerDataSource(tableBean);
        resultTable.setVisibleColumns(CommonUiUtils.visibleSearchColumn);
        resultTable.setColumnHeaders(CommonUiUtils.visibleSearchHeader);
        resultTable.setPageLength(NumericConstants.TEN);
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
                    List<DataSelectionDTO> ProductSelected = new ArrayList<>();
                    for (int i = 0; i < selectedProductBean.size(); i++) {
                        DataSelectionDTO dataSelectionDto = (DataSelectionDTO) selectedProductBean.getIdByIndex(i);
                        ProductSelected.add(dataSelectionDto);
                    }
                    try {
                        dataSelectionBinder.commit();
                    } catch (FieldGroup.CommitException e) {
                        LOGGER.error(e);
                    }
                    VaadinSession.getCurrent().setAttribute(Constant.PROJECTION_ID, Integer.parseInt(msg));
                    sessionDTO.setProjectionId(Integer.parseInt(msg));
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
                    sessionDTO.setProjectionId(Integer.parseInt(msg));

                } else {
                }
            } else {
                AbstractNotificationUtils.getErrorNotification(Constant.MISSING_DATA, Constant.PLEASE_SELECT_ALL_REQUIRED_FIELDS_BEFORE);
            }
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
    public void modeOption(Property.ValueChangeEvent event) {
        try {
            LOGGER.debug("Inside Add or Search option value change listener");
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
        if (!"0".equals(company.getValue()) || ((Integer) thearupeticValueId) != 0 || !StringUtils.EMPTY.equals(productGroup.getValue())||!"0".equals(businessUnit.getValue())) {
            result = new DataSelectionLogic().searchCCP(company.getValue(), thearupeticValueId, productGroup.getValue(),businessUnit.getValue());
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
        List<Integer> existItemsList = new ArrayList<>();

        List<Integer> currentItemsList = new ArrayList<>();
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
            businessUnit.setReadOnly(true);
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
            targetItem = new BeanItem<>(
                    (DataSelectionDTO) id);
        }
        return (DataSelectionDTO) targetItem.getBean();
    }

    public void moveToAvailable(Object item) {
        selectedProductBean.removeItem(item);
    }

    public void updateDataSelection() {
        if (company.getValue() == null || Constant.SELECTONE.equals(company.getValue())) {
            AbstractNotificationUtils.getErrorNotification(Constant.MISSING_DATA, Constant.PLEASE_SELECT_ALL_REQUIRED_FIELDS_BEFORE);
        } else if (selectedProducts.size() == 0) {
            AbstractNotificationUtils.getErrorNotification(Constant.MISSING_DATA, Constant.PLEASE_SELECT_ALL_REQUIRED_FIELDS_BEFORE);
        } else {
            loadData();
            try {
                // Save the Projection
                List<DataSelectionDTO> selectedProduct = new ArrayList<>();
                for (int i = 0; i < selectedProductBean.size(); i++) {
                    DataSelectionDTO dataSelectionDto = (DataSelectionDTO) selectedProductBean.getIdByIndex(i);
                    selectedProduct.add(dataSelectionDto);
                }
                try {
                    dataSelectionBinder.commit();
                } catch (Exception e) {
                    LOGGER.error(e);
                }
                Object[] values = {projectionName.getValue() == null ? StringUtils.EMPTY : projectionName.getValue(), companyValueId == null ? 0 : companyValueId,
                    thearupeticValueId, productGroupId,String.valueOf(businessUnit.getValue())};
                logic.saveProjection(values, selectedProduct, true,sessionDTO);
                List<Integer> existList = new ArrayList<>(existItems);
                List<Integer> currentList = new ArrayList<>(currentItems);
                existItems.removeAll(currentItems);
                currentList.removeAll(existList);
                List<Integer> removeList = new ArrayList<>();
                List<Integer> insertList = new ArrayList<>(currentList);
                for (NaProjDetails naProjDetails : detailsList) {
                    for (Integer integer : existItems) {
                        if (naProjDetails.getItemMasterSid() == integer) {
                            removeList.add(naProjDetails.getNaProjDetailsSid());
                        }
                    }
                    existItems.add(naProjDetails.getItemMasterSid());
                }
                logic.updateProducts((Integer) VaadinSession.getCurrent().getAttribute(Constant.PROJECTION_ID), insertList, removeList);
                nationalAssumptions.getNDCSetup(String.valueOf(sessionDTO.getProjectionId()));
            } catch (Exception ex) {
                LOGGER.error(ex);
            } finally {
                LOGGER.debug("Inside finally");
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
                    company.select(value.getCompanySid());
                    company.setItemCaption(value.getCompanySid(), value.getCompany());
                    company.setImmediate(true);
                    loadOnChangeEvent();
                }
            }
        });


    }

    private void setSavedValues() {
        try {
            NaProjMaster model = NaProjMasterLocalServiceUtil.getNaProjMaster(Integer.valueOf(VaadinSession.getCurrent().getAttribute(Constant.PROJECTION_ID).toString()));
            company.setValue(model.getCompanyMasterSid());
            businessUnit.setValue(model.getBusinessUnit());
        } catch (PortalException | SystemException ex) {
            LOGGER.error(ex);
        } 
    }
}
