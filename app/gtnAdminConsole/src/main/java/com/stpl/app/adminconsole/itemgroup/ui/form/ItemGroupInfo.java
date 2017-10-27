package com.stpl.app.adminconsole.itemgroup.ui.form;

import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.app.adminconsole.util.StringConstantUtils;
import com.stpl.app.adminconsole.abstractsearch.ui.AbstractSearchForm;
import com.stpl.app.adminconsole.common.dto.SessionDTO;
import com.stpl.app.adminconsole.common.util.CommonUIUtil;
import com.stpl.app.adminconsole.common.util.CommonUtil;
import com.stpl.app.adminconsole.itemgroup.dto.ItemDetailsDTO;
import com.stpl.app.adminconsole.itemgroup.dto.ItemGroupDTO;
import com.stpl.app.adminconsole.itemgroup.logic.ItemGroupLogic;
import static com.stpl.app.adminconsole.itemgroup.ui.form.ItemGroupInfo.searchCriteria;
import com.stpl.app.adminconsole.itemgroup.ui.view.ItemGroupView;
import com.stpl.app.adminconsole.itemgroup.util.UISecurityUtil;
import com.stpl.app.adminconsole.util.AbstractNotificationUtils;
import com.stpl.app.adminconsole.util.CommonUtils;
import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.stpl.app.adminconsole.util.ErrorCodeUtil;
import com.stpl.app.adminconsole.util.ErrorCodes;
import com.stpl.app.adminconsole.util.ValidationUtils;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.service.ItemGroupLocalServiceUtil;
import com.stpl.app.ui.errorhandling.ErrorLabel;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.ifs.ui.CommonSecurityLogic;
import com.stpl.ifs.ui.util.CommonUIUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.ExtCustomTableHolder;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.TableResultCustom;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.Position;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;

/**
 * The Class ItemGroupInfo.
 *
 * @author vishalakshi
 */
public class ItemGroupInfo extends CustomComponent implements View {

    public int count;

    private static final Logger LOGGER = Logger.getLogger(ItemGroupInfo.class);

    private final Label space = new Label("&nbsp;", ContentMode.HTML);

    @UiField("itemGroupName")
    private TextField itemGroupName;

    @UiField("itemGroupDesc")
    private TextField itemGroupDesc;

    @UiField("itemGroupNo")
    private TextField itemGroupNo;

    @UiField("companyDdlb")
    private ComboBox companyDdlb;

    @UiField("itemDesc")
    private TextField itemDesc;

    private final TextField brand = new TextField();

    @UiField("brandDdlb")
    private ComboBox brandDdlb;

    @UiField("strength")
    private TextField strength;

    @UiField("itemNo")
    private TextField itemNo;

    private final TextField form = new TextField();
    @UiField("formDdlb")
    private ComboBox formDdlb;

    @UiField("itemTypeDdlb")
    private ComboBox itemTypeDdlb;

    @UiField("therapeuticClassDdlb")
    private ComboBox therapeuticClassDdlb;

    private final TextField itemType = new TextField();

    private final TextField therapeuticClass = new TextField();

    @UiField("search")
    private Button search;

    @UiField("reset")
    private Button reset;

    @UiField("add")
    private Button add;

    @UiField("addAll")
    private Button addAll;

    @UiField("remove")
    private Button remove;

    @UiField("removeAll")
    private Button removeAll;

    @UiField("save")
    private Button save;
    /**
     * The reset all.
     */
    @UiField("resetAll")
    private Button resetAll;

    @UiField("backButton")
    private Button backButton;

    @UiField("resultsTable")
    private ExtFilterTable availableResults;

    @UiField("selectedResultsTable")
    private ExtFilterTable selectedResults;

    private final TextField company = new TextField();

    @UiField("searchPanel")
    private Panel searchPanel;

    @UiField("isCssLayout")
    private CssLayout isCssLayout;

    @UiField("cssLayout")
    private CssLayout cssLayout;

    @UiField("selectedItemsTable")
    private Panel selectedItems;

    @UiField("resultTablePanel")
    private Panel resultTablePanel;

    @UiField("errorMsg")
    private ErrorLabel errorMsg;

    @UiField("searchButtonLayout")
    private HorizontalLayout searchButtonLayout;

    @UiField("includeButtonLayout")
    private HorizontalLayout includeButtonLayout;

    @UiField("excludeButtonLayout")
    private HorizontalLayout excludeButtonLayout;

    @UiField("excludeButtonLayout1")
    private HorizontalLayout excludeButtonLayout1;
    
      @UiField("itemGroupNameLabel")
    private Label itemGroupNameLabel;

    @UiField("itemGroupNoLabel")
    private Label itemGroupNoLabel;

    @UiField("itemGroupDescLabel")
    private Label itemGroupDescLabel;

    @UiField("companyDdlbLabel")
    private Label companyDdlbLabel;

    private String userId;

    private BeanItemContainer<ItemDetailsDTO> availableResultsBean = new BeanItemContainer<>(ItemDetailsDTO.class);

    private BeanItemContainer<ItemDetailsDTO> selectedResultsBean = new BeanItemContainer<>(ItemDetailsDTO.class);

    CommonUtil commonUtil = new CommonUtil();

    private ItemGroupDTO itemGroupDTO;

    public ItemDetailsDTO itemDetailsDTO;

    public ErrorfulFieldGroup itemGroupBinder;

    public ErrorfulFieldGroup itemBinder;

    private ItemGroupLogic logic;

    private Boolean removeFlag = false;
    private Boolean removeAllFlag = false;

    @UiField("availableResultsExcelExport")
    private Button availableResultsExcelExport;

    @UiField("selectedResultsExcelExport")
    private Button selectedResultsExcelExport;

    private final ThemeResource excelImage = new ThemeResource("../../icons/excel.png");

    private Boolean recordSelectedFlag = false;

    private final HelperDTO dto = new HelperDTO(ConstantsUtils.SELECT_ONE);

    private int copyResetSystemId;

    private int version;
    int criteriaCount;
    static String searchCriteria = StringUtils.EMPTY;
    static String searchCriteriaFinal = StringUtils.EMPTY;

    private Boolean recordAddFlag = false;

    private Boolean recordRemoveFlag = false;

    private final HorizontalLayout searchLayout = new HorizontalLayout();
    private boolean saveFlag = false;
    CommonSecurityLogic commonSecurity = new CommonSecurityLogic();

    public int getVersion() {
        return version;
    }

    public void setVersion(final int version) {
        this.version = version;
    }

    public int getCount() {
        return count;
    }

    public void setCount(final int count) {
        this.count = count;
    }

    public ComboBox getItemTypeDdlb() {
        return itemTypeDdlb;
    }

    public void setItemTypeDdlb(final ComboBox itemTypeDdlb) {
        this.itemTypeDdlb = itemTypeDdlb;
    }

    public ComboBox getTherapeuticClassDdlb() {
        return therapeuticClassDdlb;
    }

    public void setTherapeuticClassDdlb(final ComboBox therapeuticClassDdlb) {
        this.therapeuticClassDdlb = therapeuticClassDdlb;
    }

    public ExtFilterTable getAvailableResults() {
        return availableResults;
    }

    public void setAvailableResults(final ExtPagedTable availableResults) {
        this.availableResults = availableResults;
    }

    public ExtFilterTable getSelectedResults() {
        return selectedResults;
    }

    public void setSelectedResults(final ExtFilterTable selectedResults) {
        this.selectedResults = selectedResults;
    }

    public BeanItemContainer<ItemDetailsDTO> getSelectedResultsBean() {
        return selectedResultsBean;
    }

    public void setSelectedResultsBean(final BeanItemContainer<ItemDetailsDTO> selectedResultsBean) {
        this.selectedResultsBean = selectedResultsBean;
    }

    public ItemGroupDTO getItemGroupDTO() {
        return itemGroupDTO;
    }

    public void setItemGroupDTO(final ItemGroupDTO itemGroupDTO) {
        this.itemGroupDTO = itemGroupDTO;
    }

    public ItemDetailsDTO getItemDetailsDTO() {
        return itemDetailsDTO;
    }

    public void setItemDetailsDTO(final ItemDetailsDTO itemDetailsDTO) {
        this.itemDetailsDTO = itemDetailsDTO;
    }

    public Boolean getRecordSelectedFlag() {
        return recordSelectedFlag;
    }

    public void setRecordSelectedFlag(final Boolean recordSelectedFlag) {
        this.recordSelectedFlag = recordSelectedFlag;
    }

    public Label getSpace() {
        return space;
    }

    public ErrorLabel getErrorMsg() {
        return errorMsg;
    }

    public TextField getItemGroupName() {
        return itemGroupName;
    }

    public TextField getItemGroupDesc() {
        return itemGroupDesc;
    }

    public TextField getItemGroupNo() {
        return itemGroupNo;
    }

    public ComboBox getCompanyDdlb() {
        return companyDdlb;
    }

    public TextField getItemDesc() {
        return itemDesc;
    }

    public ComboBox getBrand() {
        return brandDdlb;
    }

    public TextField getStrength() {
        return strength;
    }

    public TextField getItemNo() {
        return itemNo;
    }

    public ComboBox getForm() {
        return formDdlb;
    }

    public TextField getItemType() {
        return itemType;
    }

    public TextField getTherapeuticClass() {
        return therapeuticClass;
    }

    public TextField getform() {
        return form;
    }

    public Button getSearch() {
        return search;
    }

    public Button getReset() {
        return reset;
    }

    public Button getAdd() {
        return add;
    }

    public Button getAddAll() {
        return addAll;
    }

    public Button getRemove() {
        return remove;
    }

    public Button getRemoveAll() {
        return removeAll;
    }

    public Button getSave() {
        return save;
    }

    public Button getResetAll() {
        return resetAll;
    }

    public Button getBackButton() {
        return backButton;
    }

    public TextField getCompany() {
        return company;
    }

    public BeanItemContainer<ItemDetailsDTO> getAvailableResultsBean() {
        return availableResultsBean;
    }

    public ItemGroupLogic getLogic() {
        return logic;
    }

    public HorizontalLayout getSearchButtonLayout() {
        return searchButtonLayout;
    }

    public HorizontalLayout getIncludeButtonLayout() {
        return includeButtonLayout;
    }

    public HorizontalLayout getExcludeButtonLayout() {
        return excludeButtonLayout;
    }

    public Button getAvailableResultsExcelExport() {
        return availableResultsExcelExport;
    }

    public Button getSelectedResultsExcelExport() {
        return selectedResultsExcelExport;
    }

    public ThemeResource getExcelImage() {
        return excelImage;
    }

    public HelperDTO getDto() {
        return dto;
    }

    public void setItemGroupBinder(final ErrorfulFieldGroup itemGroupBinder) {
        this.itemGroupBinder = itemGroupBinder;
    }

    public void setItemBinder(final ErrorfulFieldGroup itemBinder) {
        this.itemBinder = itemBinder;
    }

    public ComboBox getBrandDdlb() {
        return brandDdlb;
    }

    public void setBrandDdlb(ComboBox brandDdlb) {
        this.brandDdlb = brandDdlb;
    }

    public ComboBox getFormDdlb() {
        return formDdlb;
    }

    public void setFormDdlb(ComboBox formDdlb) {
        this.formDdlb = formDdlb;
    }

    public int getCriteriaCount() {
        return criteriaCount;
    }

    public void setCriteriaCount(int criteriaCount) {
        this.criteriaCount = criteriaCount;
    }

    public static String getSearchCriteria() {
        return searchCriteria;
    }

    public static void setSearchCriteria(String searchCriteria) {
        ItemGroupInfo.searchCriteria = searchCriteria;
    }

    public static String getSearchCriteriaFinal() {
        return searchCriteriaFinal;
    }

    public static void setSearchCriteriaFinal(String searchCriteriaFinal) {
        ItemGroupInfo.searchCriteriaFinal = searchCriteriaFinal;
    }
    private static final String REGEX_STRING = ValidationUtils.ALPHA_NUMERIC;

    SessionDTO sessionDTO;

    /**
     * Instantiates a new item group info.
     *
     * @param itemGroupDTO the item group dto
     * @param itemGroupBinder the item group binder
     * @param itemBinder the item binder
     * @param itemDetailsDTO the item details dto
     * @param selectedResultsBean the selected results bean
     * @param availableResultsBean
     * @param sessionDTO
     */
    public ItemGroupInfo(final ItemGroupDTO itemGroupDTO, final ErrorfulFieldGroup itemGroupBinder, final ErrorfulFieldGroup itemBinder, final ItemDetailsDTO itemDetailsDTO,
            final BeanItemContainer selectedResultsBean, final BeanItemContainer availableResultsBean, final SessionDTO sessionDTO) {
        super();
        LOGGER.debug("ItemGroupInfo Constructor started");
        try {
            this.itemGroupDTO = itemGroupDTO;
            this.itemGroupBinder = itemGroupBinder;
            this.itemBinder = itemBinder;
            this.itemDetailsDTO = itemDetailsDTO;
            this.selectedResultsBean = selectedResultsBean;
            this.availableResultsBean = availableResultsBean;
            this.sessionDTO = sessionDTO;
            logic = new ItemGroupLogic(sessionDTO);
            init();

        } catch (Exception e) {
            LOGGER.error(e);
        }
        searchCriteria = StringUtils.EMPTY;
        LOGGER.debug("ItemGroupInfo Constructor Ended");
    }

    /**
     * Inits the.
     *
     * @throws Exception
     * @throws PortalException
     * @throws SystemException
     */
    public final void init() throws Exception {
        LOGGER.debug("Init method started");
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/itemGroupMaster.xml"), this));
        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = sessionDTO.getUserId();
        final Map<String, AppPermission> fieldItemHM = stplSecurity
                .getFieldOrColumnPermission(userId, UISecurityUtil.ITEM_GROUP_MASTER + "," + FUNCTIONAL_SCREEN, false);
        final Map<String, AppPermission> functionCompanyHM = stplSecurity.getBusinessFunctionPermission(userId, UISecurityUtil.ITEM_GROUP_MASTER + "," + FUNCTIONAL_SCREEN);
        getResponsiveFirstTab(fieldItemHM);
        configureFields();
        getItemGroupBinder();
        getItemBinder();
        getButtonPermission(functionCompanyHM);
        addItemAvailableResults();
        addItemSelectedResults();
        if (sessionDTO.getMode().equals(ConstantsUtils.EDIT)) {
            save.setCaption("UPDATE");
        }

        LOGGER.debug("Init method started");
    }
    public static final String FUNCTIONAL_SCREEN = "Functional Screen";

    /**
     * Gets the item group binder.
     *
     * @return the item group binder
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    private ErrorfulFieldGroup getItemGroupBinder() {
        LOGGER.debug("getItemGroupBinder method started");
        itemGroupBinder.bindMemberFields(this);
        itemGroupBinder.setItemDataSource(new BeanItem<>(itemGroupDTO));
        itemGroupBinder.setBuffered(true);
        itemGroupBinder.setErrorDisplay(errorMsg);
        LOGGER.debug("getItemGroupBinder method started");
        return itemGroupBinder;
    }

    /**
     * Gets the item binder.
     *
     * @return the item binder
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    private ErrorfulFieldGroup getItemBinder() {
        LOGGER.debug("getItemBinder method started");
        itemBinder.bindMemberFields(this);

        itemBinder.setItemDataSource(new BeanItem<>(itemDetailsDTO));
        itemBinder.setBuffered(true);
        itemBinder.setErrorDisplay(errorMsg);
        LOGGER.debug("getItemBinder method Ended");
        return itemBinder;
    }

    /**
     * Adds the item available results.
     *
     * @return the filter table
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    private ExtFilterTable addItemAvailableResults() throws SystemException, PortalException {
        try {
            LOGGER.debug("addItemAvailableResults method started");

            availableResults.markAsDirty();
            availableResults.setFilterBarVisible(true);
            availableResults.setFilterDecorator(new ExtDemoFilterDecorator());
            availableResults.setContainerDataSource(availableResultsBean);
            final StplSecurity stplSecurity = new StplSecurity();
            userId = sessionDTO.getUserId();
            String mode = sessionDTO.getMode();
            final Map<String, AppPermission> fieldIfpHM = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.ITEM_GROUP_MASTER, false);
            List<Object> resultList = commonUtil.getFieldsForSecurity(UISecurityUtil.ITEM_GROUP_MASTER, "Functional List view");
            Object[] objColumn = CommonUIUtil.getInstance().itemResultColumns;
            TableResultCustom tableResultCustom = commonSecurity.getTableColumnsPermission(resultList, objColumn, fieldIfpHM, mode);

            availableResults.setMultiSelect(true);

            for (Object objColumn1 : objColumn) {
                String value = objColumn1.toString();
                if (value.endsWith("Date")) {
                    availableResults.setColumnAlignment(objColumn1, ExtCustomTable.Align.CENTER);
                }
            }
            if (tableResultCustom.getObjResult().length > 0) {
                availableResults.setVisibleColumns(tableResultCustom.getObjResult());
                availableResults.setColumnHeaders(tableResultCustom.getObjResultHeader());
            } else {
                resultTablePanel.setVisible(false);
            }
            availableResults.setPageLength(NumericConstants.SEVEN);

            availableResults.setWidth(ConstantsUtils.WIDTH);
            availableResults.setImmediate(true);
            availableResults.setSelectable(true);
            availableResults.setStyleName("table-header-normal-left");
            availableResults.setSizeFull();

            availableResults.addValueChangeListener(new Property.ValueChangeListener() {

                @SuppressWarnings("PMD")
                public void valueChange(final Property.ValueChangeEvent event) {
                    LOGGER.debug("In addItemAvailableResults availableResults.addValueChangeListener started");
                    recordAddFlag = true;
                    recordRemoveFlag = false;
                    LOGGER.debug("In addItemAvailableResults availableResults.addValueChangeListener Ended");
                }
            });

            availableResults.addItemClickListener(new ItemClickEvent.ItemClickListener() {

                public void itemClick(final ItemClickEvent event) {
                    if (event.isDoubleClick()) {
                        LOGGER.debug("In addItemAvailableResults availableResults.addItemClickListener started");
                        addItemsDoubleClick(event);
                        LOGGER.debug("In addItemAvailableResults availableResults.addItemClickListener Ended");
                    }
                }
            });

            LOGGER.debug("addItemAvailableResults method Ended");

        } catch (PortalException ex) {
            LOGGER.error(ex);
        } catch (SystemException ex) {
            LOGGER.error(ex);
        }
        return availableResults;
    }

    /**
     * Adds the item selected results.
     *
     * @return the filter table
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    private ExtFilterTable addItemSelectedResults() {
        try {
            LOGGER.debug("addItemSelectedResults method started");
            selectedResults.markAsDirty();
            selectedResults.setFilterBarVisible(true);
            selectedResults.setFilterDecorator(new ExtDemoFilterDecorator());
            selectedResults.setContainerDataSource(selectedResultsBean);
            final StplSecurity stplSecurity = new StplSecurity();
            userId = sessionDTO.getUserId();
            String mode = sessionDTO.getMode();
            final Map<String, AppPermission> fieldIfpHM = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.ITEM_GROUP_MASTER, false);
            List<Object> resultList = commonUtil.getFieldsForSecurity(UISecurityUtil.ITEM_GROUP_MASTER, "Functional List view");
            Object[] objColumn = CommonUIUtil.getInstance().itemResultColumns;
            TableResultCustom tableResultCustom = commonSecurity.getTableColumnsPermission(resultList, objColumn, fieldIfpHM, mode);

            selectedResults.setMultiSelect(true);
            selectedResults.setCaption(ConstantsUtils.SELECTED_ITEMS);

            for (Object objColumn1 : objColumn) {
                String value = objColumn1.toString();
                if (value.endsWith("Date")) {
                    selectedResults.setColumnAlignment(objColumn1, ExtCustomTable.Align.CENTER);
                }
            }
            if (tableResultCustom.getObjResult().length > 0) {
                selectedResults.setVisibleColumns(tableResultCustom.getObjResult());
                selectedResults.setColumnHeaders(tableResultCustom.getObjResultHeader());
            } else {
                selectedItems.setVisible(false);
            }
            selectedResults.setPageLength(NumericConstants.FIVE);
            selectedResults.setWidth(ConstantsUtils.WIDTH);
            selectedResults.setImmediate(true);
            selectedResults.setSelectable(true);
            selectedResults.setStyleName("table-header-normal-left");
            selectedResults.setSizeFull();
            selectedResults.addValueChangeListener(new Property.ValueChangeListener() {

                @SuppressWarnings("PMD")
                public void valueChange(final Property.ValueChangeEvent event) {
                    LOGGER.debug("In addItemSelectedResults selectedResults.addValueChangeListener started");
                    recordRemoveFlag = true;
                    recordAddFlag = false;
                    LOGGER.debug("In addItemSelectedResults selectedResults.addValueChangeListener Ended");
                }
            });

            selectedResults.addItemClickListener(new ItemClickEvent.ItemClickListener() {

                public void itemClick(final ItemClickEvent event) {
                    if (event.isDoubleClick()) {
                        LOGGER.debug("In addItemSelectedResults selectedResults.addItemClickListener started");
                        removeItemsDoubleClick(event);
                        LOGGER.debug("In addItemSelectedResults selectedResults.addItemClickListener  Ended");
                    }
                }
            });

            LOGGER.debug("addItemSelectedResults method ended");

        } catch (PortalException ex) {
            LOGGER.error(ex);
        } catch (SystemException ex) {
            LOGGER.error(ex);
        }
        return selectedResults;
    }

    /**
     * Results item click.
     *
     * @param obj the id
     * @throws PortalException the portal exception
     */
    protected void resultsItemClick(final Object obj) {
        LOGGER.debug("resultsItemClick method started");
        if (obj == null) {
            recordSelectedFlag = false;
        } else {
            recordSelectedFlag = true;
        }
        LOGGER.debug("resultsItemClick method Ended");
    }

    /**
     * Configure fields.
     *
     * @throws SystemException
     */
    private void configureFields() throws SystemException {
        LOGGER.debug("configureFields method started");
        availableResultsExcelExport.setDescription(ConstantsUtils.EXCEL_EXPORT);
        selectedResultsExcelExport.setDescription(ConstantsUtils.EXCEL_EXPORT);

        selectedResultsExcelExport.setIconAlternateText(ConstantsUtils.EXCEL_EXPORT);
        itemGroupName.focus();
        itemGroupName.setImmediate(true);
        itemGroupNo.setImmediate(true);
        itemGroupDesc.setImmediate(true);
        itemGroupName.setMaxLength(NumericConstants.HUNDRED);
        itemGroupNo.setMaxLength(NumericConstants.HUNDRED);
        itemGroupDesc.setMaxLength(NumericConstants.HUNDRED);

        itemGroupName.setRequired(true);

        itemGroupNo.setRequired(true);

        itemGroupDesc.setRequired(true);

        companyDdlb.setRequired(true);

        companyDdlb.setImmediate(true);
        companyDdlb.addItem(ConstantsUtils.SELECT_ONE);
        companyDdlb = CommonUtils.getCompany(companyDdlb);
        companyDdlb.setNullSelectionAllowed(true);
        companyDdlb.setNullSelectionItemId(ConstantsUtils.SELECT_ONE);
        companyDdlb.select(ConstantsUtils.SELECT_ONE);
        therapeuticClassDdlb = CommonUtil.getComboBox(therapeuticClassDdlb, "THERAPEUTIC_CLASS");
        therapeuticClassDdlb.setNullSelectionAllowed(true);
        therapeuticClassDdlb.setInputPrompt(ConstantsUtils.SELECT_ONE);
        therapeuticClassDdlb.setImmediate(true);

        itemTypeDdlb = CommonUtil.getComboBox(itemTypeDdlb, "ITEM_TYPE");
        itemTypeDdlb.setNullSelectionAllowed(true);
        itemTypeDdlb.setInputPrompt(ConstantsUtils.SELECT_ONE);

        itemTypeDdlb.setImmediate(true);
        formDdlb = CommonUtil.getComboBox(formDdlb, "FORM");
        formDdlb.setNullSelectionAllowed(true);
        formDdlb.setInputPrompt(ConstantsUtils.SELECT_ONE);

        formDdlb.setImmediate(true);

        brandDdlb = CommonUtil.getBrandComboBox(brandDdlb);
        brandDdlb.setNullSelectionAllowed(true);
        brandDdlb.setInputPrompt(ConstantsUtils.SELECT_ONE);

        brandDdlb.setImmediate(true);

        itemNo.setImmediate(true);
        itemDesc.setImmediate(true);
        brandDdlb.setImmediate(true);
        formDdlb.setImmediate(true);
        strength.setImmediate(true);
        if ("add".equalsIgnoreCase(sessionDTO.getMode()) || "copy".equalsIgnoreCase(sessionDTO.getMode())) {
            itemGroupNameLabel.addStyleName(StringConstantUtils.MANDATORY);
            itemGroupNoLabel.addStyleName(StringConstantUtils.MANDATORY);
            itemGroupDescLabel.addStyleName(StringConstantUtils.MANDATORY);
            companyDdlbLabel.addStyleName(StringConstantUtils.MANDATORY);
        }

        availableResultsExcelExport.setIcon(excelImage);
        availableResultsExcelExport.setStyleName("link");

        selectedResultsExcelExport.setIcon(excelImage);
        selectedResultsExcelExport.setStyleName("link");

        availableResultsExcelExport.addClickListener(new ClickListener() {

            public void buttonClick(final ClickEvent event) {
                LOGGER.debug("In configureFields availableResultsExcelExport.addClickListener started");

                if (availableResults.size() > ConstantsUtils.ZERO_NUM) {
                    VaadinSession.getCurrent().setAttribute(ConstantsUtils.EXCEL_CLOSE, "true");
                    ExcelExport excel = new ExcelExport(new ExtCustomTableHolder(availableResults), "Available Item Results", "Available Item Results", "Available Item Results.xls", false);
                    excel.export();

                } else {
                    MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "No results to Export", ButtonId.OK);
                }
                LOGGER.debug("In configureFields availableResultsExcelExport.addClickListener Ended");
            }
        });

        selectedResultsExcelExport.addClickListener(new ClickListener() {

            public void buttonClick(final ClickEvent event) {
                LOGGER.debug("In configureFields selectedResultsExcelExport.addClickListener started");
                if (selectedResults.size() > ConstantsUtils.ZERO_NUM) {
                    VaadinSession.getCurrent().setAttribute(ConstantsUtils.EXCEL_CLOSE, "true");
                    ExcelExport excel = new ExcelExport(new ExtCustomTableHolder(selectedResults), "Selected Item Results", "Selected Item Results", "Selected Item Results.xls", false);
                    excel.export();

                } else {
                    MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "No results to Export", ButtonId.OK);
                }
                LOGGER.debug("In configureFields selectedResultsExcelExport.addClickListener Ended");
            }
        });

        LOGGER.debug("configureFields method Ended");
    }

    private boolean saveGroupInfo(String indicator) {
        try {

            saveFlag = false;
            itemGroupBinder.getErrorDisplay().clearError();
            itemGroupBinder.commit();
            final int itemGroupSystemId = sessionDTO.getSystemId();
            if (itemGroupSystemId == ConstantsUtils.ZERO_NUM) {
                version = 0;
                final Map duplicateItemGroupName = logic.getExistingItemgroupNames();
                if (duplicateItemGroupName.containsValue(String.valueOf(itemGroupName))) {
                    if (itemGroupSystemId == ConstantsUtils.ZERO_NUM) {
                        MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "Entered Item group Name already exists ", ButtonId.OK);
                        saveFlag = false;
                        return false;
                    } else {
                        if (!duplicateItemGroupName.containsKey(String.valueOf(itemGroupSystemId))) {
                            MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "Entered Item Group Name already exists ", ButtonId.OK);
                            saveFlag = false;
                            return false;
                        }
                    }
                }
            }

            if (selectedResultsBean != null && selectedResultsBean.size() == 0) {
                if (availableResultsBean != null && availableResultsBean.size() == 0) {
                    MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "Please search the Item and select atleast one Item to save the Item Group ", ButtonId.OK);
                } else {
                    MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "Please select atleast one Item to save the Item Group ", ButtonId.OK);
                }
                saveFlag = false;
                return false;
            }
            if ("back".equals(indicator)) {
                save();
            } else {

                MessageBox.showPlain(Icon.QUESTION, ConstantsUtils.CONFORMATION, "Save record " + itemGroupName.getValue() + ConstantsUtils.QUESTION_MARK, new MessageBoxListener() {

                    @SuppressWarnings("PMD")
                    public void buttonClicked(final ButtonId buttonId) {
                        if (buttonId.name().equals(ConstantsUtils.YES)) {
                            save();
                        }
                    }
                }, ButtonId.YES, ButtonId.NO);
            }

        } catch (CommitException e) {
            StringBuilder errorMessage = new StringBuilder("Information for the following Mandatory fields need to be provided: ");
            if (itemGroupBinder.getField("itemGroupName").getValue() == null || ConstantsUtils.EMPTY.equals(itemGroupBinder.getField("itemGroupName").getValue())) {
                if (saveFlag) {
                    errorMessage.append(" ,");
                }
                errorMessage.append("Item Group Name");
                itemGroupName.setRequiredError("Please enter Item Group Name");
                saveFlag = true;
            }
            if (itemGroupBinder.getField("itemGroupNo").getValue() == null || ConstantsUtils.EMPTY.equals(itemGroupBinder.getField("itemGroupNo").getValue())) {
                if (saveFlag) {
                    errorMessage.append(" ,");
                }
                errorMessage.append("Item Group No");
                itemGroupNo.setRequiredError("Please enter Item Group No");
                saveFlag = true;
            }
            if (itemGroupBinder.getField("itemGroupDesc").getValue() == null || ConstantsUtils.EMPTY.equals(itemGroupBinder.getField("itemGroupDesc").getValue())) {
                if (saveFlag) {
                    errorMessage.append(" ,");
                }
                errorMessage.append("Item Group Description");
                itemGroupDesc.setRequiredError("Please enter Item Group Description");
                saveFlag = true;
            }
            if (itemGroupBinder.getField("companyDdlb").getValue() == null || ConstantsUtils.SELECT_ONE.equals(companyDdlb.getDescription())) {
                if (saveFlag) {
                    errorMessage.append(" ,");
                }
                errorMessage.append("Company");
                companyDdlb.setRequiredError("Please select Company in the Item Group Information section");
                saveFlag = true;
            }
            if (saveFlag) {
                itemGroupBinder.getErrorDisplay().setError(errorMessage.toString());
                LOGGER.error(errorMessage.toString());
            }
            saveFlag = false;
            LOGGER.error(e);
        } catch (SystemException e) {
            saveFlag = false;
            final String errorMsg = ErrorCodeUtil.getErrorMessage(e);
            LOGGER.error(e);
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
        } catch (Exception e) {
            saveFlag = false;
            LOGGER.error(e);
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4002));
        }
        return saveFlag;
    }

    /**
     * Detach listeners.
     *
     * @param field the field
     * @throws SystemException the system exception
     */
    public void detachListeners(final AbstractField field) {
        LOGGER.debug("detachListeners method started");
        List<Property.ValueChangeListener> listeners;
        listeners = (List<Property.ValueChangeListener>) field.getListeners(Property.ValueChangeEvent.class);

        for (final Iterator<ValueChangeListener> iterator = listeners.iterator(); iterator.hasNext();) {
            final Property.ValueChangeListener object = iterator.next();
            count++;
            field.removeValueChangeListener(object);
        }
        LOGGER.debug("detachListeners method ended");
    }

    private void save() {
        try {
            List<Integer> idList = saveButtonClick();
            String page = sessionDTO.getFromViewPage();
            if (!idList.isEmpty() && (page.equalsIgnoreCase("Add") || page.equalsIgnoreCase("Copy"))) {
                LOGGER.debug("Inside if 0" + idList.get(0));
                LOGGER.debug("Inside if 1" + idList.get(1));
                sessionDTO.setSystemId(idList.get(0));
                sessionDTO.setFromViewPage("Edit");
                sessionDTO.setVersionNo(idList.get(1));
                sessionDTO.setLogic("edit");
                sessionDTO.setMode(ConstantsUtils.EDIT);
                getUI().getNavigator().navigateTo(ItemGroupView.NAME);
            }
            final Notification notif = new Notification(
                    itemGroupName.getValue() + " has been successfully saved",
                    Notification.Type.HUMANIZED_MESSAGE);
            notif.setPosition(Position.BOTTOM_CENTER);
            notif.setStyleName("mystyle");
            notif.setDelayMsec(NumericConstants.THREE_THOUSAND);
            notif.show(Page.getCurrent());
            saveFlag = true;
        } catch (SystemException e) {
            final String errorMsg = ErrorCodeUtil.getErrorMessage(e);
            LOGGER.error(e);
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
        } catch (PortalException e) {
            LOGGER.error(e);
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4002));
        } catch (Exception e) {
            LOGGER.error(e);
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4002));
        }
    }

    protected void companyOnChangeEvent(final Object companyValue) {
        LOGGER.debug("companyOnChangeEvent method started with companyValue: " + companyValue);
        if (companyDdlb.getValue() == null) {
            company.setValue(null);
        } else {
            final Object companyValue1 = ((HelperDTO) companyDdlb.getValue()).getId();
            company.setValue(companyValue1.toString());

        }
    }

    /**
     * Therapeutic class on change event.
     *
     * @param companyValue the company value
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    protected void therapeuticClassOnChangeEvent(final Object companyValue) {
        LOGGER.debug("therapeuticClassOnChangeEvent method started with companyValue: " + companyValue);

        if (therapeuticClassDdlb.getValue() == null) {
            therapeuticClass.setValue(null);
        } else {
            therapeuticClass.setValue(companyValue.toString());

        }

    }

    /**
     * Item type on change event.
     *
     * @param companyValue the company value
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    protected void itemTypeOnChangeEvent(final Object companyValue) {
        LOGGER.debug("itemTypeOnChangeEvent method started with companyValue: " + companyValue);
        try {
            if (itemTypeDdlb.getValue() == null) {
                itemType.setValue(null);
            } else {
                itemType.setValue(companyValue.toString());
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    /**
     * Form on change event.
     *
     * @param formValue the form Value
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    protected void formOnChangeEvent(final Object formValue) {
        LOGGER.debug("formTypeOnChangeEvent method started with formValue: " + formValue);
        try {
            if (formDdlb.getValue() == null) {
                form.setValue(null);
            } else {
                form.setValue(formValue.toString());
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    /**
     * Search button click logic.
     *
     * @param event the event
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    protected void searchButtonClickLogic(final Button.ClickEvent event) throws Exception {

        itemBinder.commit();
        availableResultsBean.removeAllItems();
        criteriaCount++;
        final List<ItemDetailsDTO> searchResults = logic.getItemSearch(itemBinder, criteriaCount,sessionDTO);
        searchCriteria += logic.getSearchCriteria(itemBinder, criteriaCount).get(NumericConstants.SEVEN);
        if (searchCriteria.contains("Wildcard")) {
            searchCriteria = "*";
        }
        if (searchResults.isEmpty()) {
            MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "No results could be found that match the entered search criteria.", ButtonId.OK);
        } else {

            availableResultsBean.addAll(searchResults);
            CommonUIUtils.getMessageNotification(ConstantsUtils.SEARCH_COMPLETED);

        }
        LOGGER.debug("searchButtonClickLogic method Ended");
    }

    /**
     * Reset button click logic.
     *
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     */
    protected void resetButtonClickLogic() {
        LOGGER.debug("resetButtonClickLogic method started");
        itemGroupBinder.getErrorDisplay().clearError();
        itemTypeDdlb.setValue(null);
        itemDesc.setValue(ConstantsUtils.EMPTY);
        brand.setValue(ConstantsUtils.EMPTY);
        strength.setValue(ConstantsUtils.EMPTY);
        itemNo.setValue(ConstantsUtils.EMPTY);
        therapeuticClassDdlb.setValue(null);
        form.setValue(ConstantsUtils.EMPTY);
        brandDdlb.setValue(null);
        formDdlb.setValue(null);

        LOGGER.debug("resetButtonClickLogic method Ended");
    }

    protected void resetIGInformation() {
        itemGroupName.setValue(ConstantsUtils.EMPTY);
        itemGroupNo.setValue(ConstantsUtils.EMPTY);
        itemGroupDesc.setValue(ConstantsUtils.EMPTY);
        companyDdlb.setValue(null);
    }

    protected void resetTables() {
        availableResultsBean.removeAllItems();
        selectedResultsBean.removeAllItems();
    }

    /**
     * Reset all button click logic.
     *
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    protected void resetAllButtonClickLogic() throws Exception {
        LOGGER.debug("resetAllButtonClickLogic method started");
        final String fromViewPage = sessionDTO.getFromViewPage();
        List<ItemDetailsDTO> availableContainer = new ArrayList<>();
        final int version = sessionDTO.getVersionNo();
        itemGroupDTO = new ItemGroupLogic().getHistoryItemGroupInfo(version, sessionDTO);
        if (!StringUtils.isEmpty(itemGroupDTO.getItemFilter())) {
            List availableList = ItemGroupLocalServiceUtil.getAvailableSearchResults(itemGroupDTO.getItemFilter());
            if (availableList.size() > 0) {
                availableContainer = logic.getCustomizedItemResults(availableList);
            }
        }
        if (fromViewPage.equals(ConstantsUtils.EDIT)) {
            final int itemGroupSystemId = sessionDTO.getSystemId();
            itemGroupDTO = new ItemGroupLogic().getItemGroupInfo(sessionDTO);
            final HelperDTO companyDTO = new HelperDTO();
            companyDTO.setId(itemGroupDTO.getCompanyDdlb().getId());
            companyDTO.setDescription(itemGroupDTO.getCompanyDdlb().getDescription());
            companyDdlb.setValue(companyDTO);
            availableResults.removeAllItems();
            availableResultsBean.addAll(availableContainer);
            final List<ItemDetailsDTO> itemDetails = new ItemGroupLogic().getSavedItemDetails(itemGroupSystemId);
            selectedResultsBean.removeAllItems();
            selectedResultsBean.addAll(itemDetails);
        } else {
            itemGroupBinder.getErrorDisplay().clearError();
            availableResultsBean.removeAllItems();
            selectedResultsBean.removeAllItems();

            if (fromViewPage.equals(ConstantsUtils.COPY)) {
                final List<ItemDetailsDTO> itemDetails = new ItemGroupLogic().getSavedItemDetails(copyResetSystemId);
                selectedResultsBean.addAll(itemDetails);
            }
            itemGroupNo.setValue(ConstantsUtils.EMPTY);
            itemGroupName.setValue(ConstantsUtils.EMPTY);
            itemGroupDesc.setValue(ConstantsUtils.EMPTY);
            companyDdlb.setValue(new HelperDTO(ConstantsUtils.SELECT_ONE));
            company.setValue(ConstantsUtils.EMPTY);
            itemNo.setValue(ConstantsUtils.EMPTY);
            itemDesc.setValue(ConstantsUtils.EMPTY);
            formDdlb.setValue(new HelperDTO(ConstantsUtils.SELECT_ONE));
            strength.setValue(ConstantsUtils.EMPTY);
            brandDdlb.setValue(new HelperDTO(ConstantsUtils.SELECT_ONE));
            therapeuticClassDdlb.setValue(new HelperDTO(ConstantsUtils.SELECT_ONE));
            itemTypeDdlb.setValue(new HelperDTO(ConstantsUtils.SELECT_ONE));
        }

        LOGGER.debug("resetAllButtonClickLogic method Ended");
    }

    /**
     * Adds the items button click.
     *
     * @param event the event
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    protected void addItemsButtonClick(final ClickEvent event) {
        LOGGER.debug("addItemsButtonClick method started");
        if (availableResults.getValue() instanceof Collection) {
            final java.util.Set<ItemDetailsDTO> itemMasterDetailsList = (java.util.Set<ItemDetailsDTO>) availableResults.getValue();
            selectedResultsBean.addAll(itemMasterDetailsList);
            for (final Iterator<ItemDetailsDTO> iterator = itemMasterDetailsList.iterator(); iterator.hasNext();) {
                final ItemDetailsDTO item = iterator.next();
                sessionDTO.getSelectedItems().add(String.valueOf(item.getItemSystemId()));
                availableResultsBean.removeItem(item);
            }
        } else {
            final Object itemId = availableResults.getValue();
            availableResultsBean.removeItem(itemId);
            selectedResultsBean.addItem(itemId);
        }
        selectedResults.setValue(null);
        availableResults.setValue(null);
        recordAddFlag = false;
        LOGGER.debug("addItemsButtonClick method ended");
    }

    /**
     * Adds the items double click.
     *
     * @param event the event
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    protected void addItemsDoubleClick(final ItemClickEvent event) {

        LOGGER.debug("addItemsDoubleClick method started");

        final Object itemId = availableResults.getValue();
        selectedResults.addItem(itemId);
        availableResults.removeItem(itemId);
        selectedResults.setValue(null);
        availableResults.setValue(null);
        LOGGER.debug("addItemsDoubleClick method ended");
    }

    /**
     * Adds the all items button click.
     *
     * @param event the event
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    protected void addAllItemsButtonClick(final ClickEvent event) {
        LOGGER.debug("addAllItemsButtonClick method started");
        for (int i = 0; i < availableResultsBean.size(); i++) {
            sessionDTO.getSelectedItems().add(String.valueOf(availableResultsBean.getIdByIndex(i).getItemSystemId()));
            selectedResultsBean.addItem(availableResultsBean.getIdByIndex(i));
        }
        availableResultsBean.removeAllItems();
        selectedResults.setValue(null);
        availableResults.setValue(null);
        LOGGER.debug("addAllItemsButtonClick method ended");
    }

    /**
     * Removes the items button click.
     *
     * @param event the event
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    protected void removeItemsButtonClick(final ClickEvent event) {
        LOGGER.debug("addAllItemsButtonClick method started");
        if (selectedResults.getValue() instanceof Collection) {
            final java.util.Set<ItemDetailsDTO> itemMasterDetailsList = (java.util.Set<ItemDetailsDTO>) selectedResults.getValue();
            availableResultsBean.addAll(itemMasterDetailsList);
            for (final Iterator<ItemDetailsDTO> iterator = itemMasterDetailsList.iterator(); iterator.hasNext();) {
                final ItemDetailsDTO item = iterator.next();
                  sessionDTO.getSelectedItems().remove(String.valueOf(item.getItemSystemId()));
                selectedResultsBean.removeItem(item);
            }
        } else {
            final Object itemId = selectedResults.getValue();
            selectedResultsBean.removeItem(itemId);
            availableResultsBean.addItem(itemId);
        }
        selectedResults.setValue(null);
        availableResults.setValue(null);
        LOGGER.debug("addAllItemsButtonClick method ended");
    }

    /**
     * Removes the items double click.
     *
     * @param event the event
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     */
    protected void removeItemsDoubleClick(final ItemClickEvent event) {
        LOGGER.debug("removeItemsDoubleClick method started");
        final Object itemId = selectedResults.getValue();
        availableResultsBean.addItem(itemId);
        selectedResultsBean.removeItem(itemId);
        selectedResults.setValue(null);
        availableResults.setValue(null);
        LOGGER.debug("removeItemsDoubleClick method ended");
    }

    /**
     * Removes the all items button click.
     *
     * @param event the event
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    protected void removeAllItemsButtonClick(final ClickEvent event) {
        LOGGER.debug("removeAllItemsButtonClick method started");
        for (int i = 0; i < selectedResultsBean.size(); i++) {
            sessionDTO.getSelectedItems().remove(String.valueOf(selectedResultsBean.getIdByIndex(i).getItemSystemId()));
            availableResultsBean.addItem(selectedResultsBean.getIdByIndex(i));
        }
        selectedResultsBean.removeAllItems();
        selectedResults.setValue(null);
        availableResults.setValue(null);
        LOGGER.debug("removeAllItemsButtonClick method ended");
    }

    /**
     * Save button click.
     *
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     */
    protected List<Integer> saveButtonClick() throws Exception {
        LOGGER.debug("saveButtonClick method started");
        final List selectedItems = selectedResultsBean.getItemIds();
        List<Integer> idList = logic.saveItemGroup(itemGroupBinder, selectedItems, searchCriteria, sessionDTO);
        LOGGER.debug("saveButtonClick method ended");
        return idList;

    }

    /**
     * This method is always called before the view is shown on screen.
     *
     * @param event the event
     */
    public void enter(final ViewChangeListener.ViewChangeEvent event) {
        return;
    }

    public void entry(final Boolean flag) {
        try {
            LOGGER.debug("entry started");
            final int itemGroupSystemId = sessionDTO.getSystemId();
            final String pageName = sessionDTO.getLogic();
            if (itemGroupSystemId != ConstantsUtils.ZERO_NUM && !ConstantsUtils.LOWERCASE_COPY.equalsIgnoreCase(pageName)) {
                if (flag) {
                    final int versionNo = sessionDTO.getVersionNo();
                    itemGroupDTO = new ItemGroupLogic().getHistoryItemGroupInfo(versionNo, sessionDTO);
                } else {
                    itemGroupDTO = new ItemGroupLogic().getItemGroupInfo(sessionDTO);
                }
                itemGroupName.setReadOnly(false);
                itemGroupNo.setReadOnly(false);
                itemGroupDesc.setReadOnly(false);
                companyDdlb.setReadOnly(false);
                selectedResults.setReadOnly(false);
                resetButtonClickLogic();
                final HelperDTO companyDTO = new HelperDTO();
                companyDTO.setId(itemGroupDTO.getCompanyDdlb().getId());
                companyDTO.setDescription(itemGroupDTO.getCompanyDdlb().getDescription());
                companyDdlb.setValue(companyDTO);
                itemGroupBinder.setItemDataSource(new BeanItem<>(itemGroupDTO));
                availableResults.removeAllItems();
            }
            final String fromViewPage = sessionDTO.getFromViewPage();
            if (fromViewPage.equalsIgnoreCase(ConstantsUtils.SMALL_YES)) {

                itemGroupName.setReadOnly(true);
                itemGroupNo.setReadOnly(true);
                itemGroupDesc.setReadOnly(true);
                companyDdlb.setReadOnly(true);
                searchPanel.setVisible(false);
                searchButtonLayout.setVisible(false);
                availableResults.setVisible(false);
                includeButtonLayout.setVisible(false);
                excludeButtonLayout.setVisible(true);
                remove.setVisible(false);
                removeAll.setVisible(false);
                selectedResultsExcelExport.setVisible(true);
                save.setEnabled(false);
                resetAll.setEnabled(false);
                selectedResults.setReadOnly(true);
                searchLayout.setVisible(false);
                resultTablePanel.setVisible(false);
            } else {
                itemGroupName.setReadOnly(true);
                itemGroupNo.setReadOnly(true);
                itemGroupDesc.setReadOnly(true);
                companyDdlb.setReadOnly(true);
                searchPanel.setVisible(true);
                searchButtonLayout.setVisible(true);
                availableResults.setVisible(true);
                includeButtonLayout.setVisible(true);
                excludeButtonLayout.setVisible(true);
                if (removeFlag == false) {
                    remove.setVisible(true);
                }
                if (removeAllFlag = false) {
                    removeAll.setVisible(true);
                }
                save.setEnabled(true);
                resetAll.setEnabled(true);
                selectedResults.setReadOnly(false);
                final String fromAddPage = sessionDTO.getFromViewPage();

                if (fromAddPage.equals(ConstantsUtils.ADD)) {
                    itemGroupName.setReadOnly(false);
                    itemGroupNo.setReadOnly(false);
                    itemGroupDesc.setReadOnly(false);
                    companyDdlb.setReadOnly(false);
                    selectedResultsBean.removeAllItems();
                    availableResultsBean.removeAllItems();
                    itemGroupNo.setValue(ConstantsUtils.EMPTY);
                    itemGroupName.setValue(ConstantsUtils.EMPTY);
                    itemGroupDesc.setValue(ConstantsUtils.EMPTY);
                    companyDdlb.setValue(ConstantsUtils.SELECT_ONE);
                } else if (fromAddPage.equals("copy")) {
                    itemGroupName.setReadOnly(false);
                    itemGroupNo.setReadOnly(false);
                    itemGroupDesc.setReadOnly(false);
                    companyDdlb.setReadOnly(false);
                    itemGroupNo.setValue(ConstantsUtils.EMPTY);
                    itemGroupName.setValue(ConstantsUtils.EMPTY);
                    itemGroupDesc.setValue(ConstantsUtils.EMPTY);
                    companyDdlb.setValue(ConstantsUtils.SELECT_ONE);
                    sessionDTO.setSystemId(0);
                    copyResetSystemId = itemGroupSystemId;
                }
            }
            LOGGER.debug("entry Ended");
        } catch (SystemException e) {
            final String errorsMsg = ErrorCodeUtil.getErrorMessage(e);
            LOGGER.error(e);
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorsMsg);
        } catch (PortalException e) {
            LOGGER.error(e);
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4014));
        } catch (Exception e) {
            LOGGER.error(e);
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4014));
        }
    }

    public int getCopyResetSystemId() {
        return copyResetSystemId;
    }

    public void setCopyResetSystemId(final int copyResetSystemId) {
        this.copyResetSystemId = copyResetSystemId;
    }

    /**
     * Responsiveness method
     *
     * @param table
     * @param resultsBean
     */
    public void addResponsiveSearchTableCollapse(final ExtFilterTable table, final BeanItemContainer<ItemDetailsDTO> resultsBean) {
        final Map<Integer, Boolean> reloadMap = new HashMap<>();
        reloadMap.put(ConstantsUtils.PX_1516, true);
        reloadMap.put(NumericConstants.NINE_SEVEN_EIGHT, true);
        reloadMap.put(NumericConstants.SIX_HUNDRED, true);
        reloadMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
        reloadMap.put(NumericConstants.THREE_TWO_ZERO, true);
        reloadMap.put(0, true);

        try {

            table.setColumnCollapsingAllowed(true);

            Page.getCurrent().addBrowserWindowResizeListener(new Page.BrowserWindowResizeListener() {
                @Override
                public void browserWindowResized(final Page.BrowserWindowResizeEvent event) {

                    final int browserWidth = Page.getCurrent().getBrowserWindowWidth();
                    if (browserWidth > ConstantsUtils.PX_1516 && reloadMap.get(ConstantsUtils.PX_1516)) {

                        for (final Object propertyId : table.getVisibleColumns()) {
                            table.setColumnCollapsed(propertyId, false);
                        }
                        for (final Object propertyId : getCollapsibleColumnsDefault(table)) {
                            table.setColumnCollapsed(propertyId, browserWidth > ConstantsUtils.PX_1516);
                        }
                        reloadMap.put(ConstantsUtils.PX_1516, false);
                        reloadMap.put(NumericConstants.NINE_SEVEN_EIGHT, true);
                        reloadMap.put(NumericConstants.SIX_HUNDRED, true);
                        reloadMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
                        reloadMap.put(NumericConstants.THREE_TWO_ZERO, true);
                        reloadMap.put(0, true);

                    } else if (browserWidth < ConstantsUtils.PX_1516 && browserWidth > NumericConstants.NINE_SEVEN_EIGHT && reloadMap.get(NumericConstants.NINE_SEVEN_EIGHT)) {

                        for (final Object propertyId : table.getVisibleColumns()) {
                            table.setColumnCollapsed(propertyId, false);
                        }
                        for (final Object propertyId : getCollapsibleColumnsDefault1515Px(table)) {
                            table.setColumnCollapsed(propertyId, browserWidth < ConstantsUtils.PX_1516);
                        }

                        reloadMap.put(ConstantsUtils.PX_1516, true);
                        reloadMap.put(NumericConstants.NINE_SEVEN_EIGHT, false);
                        reloadMap.put(NumericConstants.SIX_HUNDRED, true);
                        reloadMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
                        reloadMap.put(NumericConstants.THREE_TWO_ZERO, true);
                        reloadMap.put(0, true);

                    } else if (browserWidth < NumericConstants.NINE_SEVEN_EIGHT && browserWidth > NumericConstants.SIX_HUNDRED && reloadMap.get(NumericConstants.SIX_HUNDRED)) {

                        for (final Object propertyId : table.getVisibleColumns()) {
                            table.setColumnCollapsed(propertyId, false);
                        }

                        for (final Object propertyId : getCollapsibleColumns600Px(table)) {
                            table.setColumnCollapsed(propertyId, true);
                        }

                        reloadMap.put(ConstantsUtils.PX_1516, true);
                        reloadMap.put(NumericConstants.NINE_SEVEN_EIGHT, true);
                        reloadMap.put(NumericConstants.SIX_HUNDRED, false);
                        reloadMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
                        reloadMap.put(NumericConstants.THREE_TWO_ZERO, true);
                        reloadMap.put(0, true);
                    } else if (browserWidth < NumericConstants.SIX_HUNDRED && browserWidth > NumericConstants.FOUR_EIGHT_ZERO && reloadMap.get(NumericConstants.FOUR_EIGHT_ZERO)) {
                        for (final Object propertyId : table.getVisibleColumns()) {
                            // table is refreshed when container reloading is disabled
                            table.setColumnCollapsed(propertyId, false);
                        }
                        for (final Object propertyId : getCollapsibleColumns600Px(table)) {
                            // table is refreshed when container reloading is disabled
                            table.setColumnCollapsed(propertyId, true);
                        }
                        reloadMap.put(ConstantsUtils.PX_1516, true);
                        reloadMap.put(NumericConstants.NINE_SEVEN_EIGHT, true);
                        reloadMap.put(NumericConstants.SIX_HUNDRED, true);
                        reloadMap.put(NumericConstants.FOUR_EIGHT_ZERO, false);
                        reloadMap.put(NumericConstants.THREE_TWO_ZERO, true);
                        reloadMap.put(0, true);
                    } else if (browserWidth < NumericConstants.FOUR_EIGHT_ZERO && browserWidth > NumericConstants.THREE_TWO_ZERO && reloadMap.get(NumericConstants.THREE_TWO_ZERO)) {

                        for (final Object propertyId : table.getVisibleColumns()) {
                            table.setColumnCollapsed(propertyId, false);
                        }
                        for (final Object propertyId : getCollapsibleColumns480Px(table)) {
                            table.setColumnCollapsed(propertyId, true);
                        }
                        reloadMap.put(ConstantsUtils.PX_1516, true);
                        reloadMap.put(NumericConstants.NINE_SEVEN_EIGHT, true);
                        reloadMap.put(NumericConstants.SIX_HUNDRED, true);
                        reloadMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
                        reloadMap.put(NumericConstants.THREE_TWO_ZERO, false);
                        reloadMap.put(0, true);
                    } else if (browserWidth < NumericConstants.THREE_TWO_ZERO && reloadMap.get(0)) {

                        for (final Object propertyId : table.getVisibleColumns()) {
                            table.setColumnCollapsed(propertyId, false);
                        }
                        for (final Object propertyId : getCollapsibleColumns480Px(table)) {
                            table.setColumnCollapsed(propertyId, true);
                        }
                        reloadMap.put(ConstantsUtils.PX_1516, true);
                        reloadMap.put(NumericConstants.NINE_SEVEN_EIGHT, true);
                        reloadMap.put(NumericConstants.SIX_HUNDRED, true);
                        reloadMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
                        reloadMap.put(NumericConstants.THREE_TWO_ZERO, true);
                        reloadMap.put(0, false);
                    }

                }
            });
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    /**
     * getCollapsibleColumnsDefault
     *
     * @param table
     * @return string
     */
    private static String[] getCollapsibleColumnsDefault(final ExtFilterTable table) {
        final Object[] visibleColumns = table.getVisibleColumns();
        String[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, String[].class);
        final List<String> list = new ArrayList<>(Arrays.asList(propertyIds));

        for (int i = 0; i < NumericConstants.TEN && !list.isEmpty(); i++) {
            list.remove(propertyIds[i]);
        }
        propertyIds = list.toArray(new String[list.size()]);
        return propertyIds;
    }

    /**
     *
     * @param table
     * @return
     */
    private static String[] getCollapsibleColumnsDefault1515Px(final ExtFilterTable table) {
        final Object[] visibleColumns = table.getVisibleColumns();
        String[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, String[].class);
        final List<String> list = new ArrayList<>(Arrays.asList(propertyIds));
        list.remove(propertyIds[0]);
        list.remove(propertyIds[1]);
        list.remove(propertyIds[NumericConstants.TWO]);
        list.remove(propertyIds[NumericConstants.THREE]);
        propertyIds = list.toArray(new String[list.size()]);
        return propertyIds;
    }

    /**
     * getCollapsibleColumns480Px
     *
     * @param table
     * @return string
     */
    private static String[] getCollapsibleColumns600Px(final ExtFilterTable table) {
        final Object[] visibleColumns = table.getVisibleColumns();
        String[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, String[].class);
        final List<String> list = new ArrayList<>(Arrays.asList(propertyIds));
        list.remove(propertyIds[0]);
        list.remove(propertyIds[1]);
        propertyIds = list.toArray(new String[list.size()]);
        return propertyIds;
    }

    /**
     * getCollapsibleColumns480Px
     *
     * @param table
     * @return
     */
    private static String[] getCollapsibleColumns480Px(final ExtFilterTable table) {
        final Object[] visibleColumns = table.getVisibleColumns();
        String[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, String[].class);
        final List<String> list = new ArrayList<>(Arrays.asList(propertyIds));
        list.remove(propertyIds[0]);
        list.remove(propertyIds[1]);
        propertyIds = list.toArray(new String[list.size()]);
        return propertyIds;
    }

    /**
     * getCollapsibleColumns978Px
     *
     * @param table
     * @return string
     */
    private static String[] getCollapsibleColumns978Px(final ExtFilterTable table) {
        final Object[] visibleColumns = table.getVisibleColumns();
        String[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, String[].class);
        final List<String> list = new ArrayList<>(Arrays.asList(propertyIds));
        list.remove(propertyIds[0]);
        list.remove(propertyIds[1]);
        list.remove(propertyIds[NumericConstants.TWO]);
        list.remove(propertyIds[NumericConstants.THREE]);
        propertyIds = list.toArray(new String[list.size()]);
        return propertyIds;
    }

    /**
     * setDefaultTableWidth
     *
     * @param table
     * @param container
     */
    public void setDefaultTableWidth(final ExtFilterTable table, final BeanItemContainer<ItemDetailsDTO> container) {

        try {
            table.setColumnCollapsingAllowed(true);
            int browserWidth = Page.getCurrent().getBrowserWindowWidth();
            if (browserWidth > ConstantsUtils.PX_1516) {

                for (Object propertyId : table.getVisibleColumns()) {
                    table.setColumnCollapsed(propertyId, false);
                }
                for (Object propertyId : getSixColumns(table)) {
                    table.setColumnCollapsed(propertyId, true);
                }

            } else if (browserWidth < ConstantsUtils.PX_1516 && browserWidth > NumericConstants.NINE_SEVEN_EIGHT) {
                for (Object propertyId : table.getVisibleColumns()) {
                    table.setColumnCollapsed(propertyId, false);
                }
                for (Object propertyId : getCollapsibleColumnsDefault1515Px(table)) {
                    table.setColumnCollapsed(propertyId, true);
                }
            } else if (browserWidth < NumericConstants.NINE_SEVEN_EIGHT && browserWidth > NumericConstants.SIX_HUNDRED) {
                for (Object propertyId : table.getVisibleColumns()) {
                    table.setColumnCollapsed(propertyId, false);
                }

                if (container != null && container.getItemIds().isEmpty()) {
                    for (Object propertyId : getCollapsibleColumns978Px(table)) {
                        table.setColumnCollapsed(propertyId, true);
                    }
                } else {
                    for (Object propertyId : getCollapsibleColumns600Px(table)) {
                        table.setColumnCollapsed(propertyId, true);
                    }
                }
            } else if (browserWidth < NumericConstants.SIX_HUNDRED && browserWidth > NumericConstants.FOUR_EIGHT_ZERO) {
                for (Object propertyId : table.getVisibleColumns()) {
                    table.setColumnCollapsed(propertyId, false);
                }
                for (Object propertyId : getCollapsibleColumns600Px(table)) {
                    table.setColumnCollapsed(propertyId, true);
                }
            } else if (browserWidth < NumericConstants.FOUR_EIGHT_ZERO && browserWidth > NumericConstants.THREE_TWO_ZERO) {
                for (Object propertyId : table.getVisibleColumns()) {
                    table.setColumnCollapsed(propertyId, false);
                }
                for (Object propertyId : getCollapsibleColumns480Px(table)) {
                    table.setColumnCollapsed(propertyId, true);
                }
            } else if (browserWidth < NumericConstants.THREE_TWO_ZERO) {
                for (Object propertyId : table.getVisibleColumns()) {
                    table.setColumnCollapsed(propertyId, false);
                }
                for (Object propertyId : getCollapsibleColumns480Px(table)) {
                    table.setColumnCollapsed(propertyId, true);
                }
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    /**
     * getSixColumns
     *
     * @param table
     * @return object
     */
    private static Object[] getSixColumns(ExtFilterTable table) {
        table.setColumnCollapsingAllowed(true);
        table.setImmediate(true);
        Object[] visibleColumns = table.getVisibleColumns();
        Object[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, Object[].class);
        List<Object> list = new ArrayList<>(Arrays.asList(visibleColumns));
        for (int i = 0; i < NumericConstants.SIX; i++) {
            list.remove(propertyIds[i]);
        }
        propertyIds = list.toArray(new Object[list.size()]);

        for (Object propertyId : table.getVisibleColumns()) {
            table.setColumnCollapsed(propertyId, false);
        }

        for (Object propertyId : propertyIds) {
            table.setColumnCollapsed(propertyId, true);
        }

        return propertyIds;
    }

    private void getResponsiveFirstTab(final Map<String, AppPermission> fieldItemHM) {
        LOGGER.debug("Entering getFirstTab1");
        try {
            String mode = sessionDTO.getMode();
            List<Object> resultList = commonUtil.getFieldsForSecurity(UISecurityUtil.ITEM_GROUP_MASTER, FUNCTIONAL_SCREEN);

            commonSecurity.removeComponentOnPermission(resultList, isCssLayout, fieldItemHM, mode);
            commonSecurity.removeComponentOnPermission(resultList, cssLayout, fieldItemHM, mode);
        } catch (Exception ex) {

            LOGGER.error(ex);
        }
        LOGGER.debug("Ending getFirstTab1");
    }

    public void backButton() {

        backButton.addClickListener(new Button.ClickListener() {
          
            public void buttonClick(final Button.ClickEvent event) {
                LOGGER.debug("In configureFields backButton.addClickListener navigateTo AbstractSearchForm");
                final String viewPage = sessionDTO.getFromViewPage();
                if (viewPage.equalsIgnoreCase("YES")) {
                    try {
                        getUI().getNavigator().navigateTo(AbstractSearchForm.NAME);

                    } catch (Exception e) {
                        LOGGER.error(e);
                    }
                } else {
                    MessageBox.showPlain(Icon.QUESTION, ConstantsUtils.CONFORMATION, " Any unsaved information will not be saved. Do you want to proceed"
                            + ConstantsUtils.QUESTION_MARK, new MessageBoxListener() {
                                @SuppressWarnings("PMD")
                                public void buttonClicked(final ButtonId buttonId) {
                                    if (buttonId.name().equals(ConstantsUtils.YES)) {
                                        try {
                                            getUI().getNavigator().navigateTo(AbstractSearchForm.NAME);
                                            
                                        } catch (Exception e) {
                                            LOGGER.error(e);
                                        }
                                    }
                                }
                            }, ButtonId.YES, ButtonId.NO);
                }
            }
        });
    }

    public void searchButton() {
        search.addClickListener(new Button.ClickListener() {
            public void buttonClick(final Button.ClickEvent event) {
                LOGGER.debug("In configureFields search.addClickListener started");
                try {
                    if (!String.valueOf(itemDesc.getValue()).matches(REGEX_STRING)) {
                        MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "Item Search Criteria can contains only Alphabets and Numbers", ButtonId.OK);
                    } else {
                        if ((itemTypeDdlb.getValue() != null && !String.valueOf(itemTypeDdlb.getValue()).equals(ConstantsUtils.SELECT_ONE))
                                || !StringUtils.isEmpty(String.valueOf(itemDesc.getValue()))
                                || (brandDdlb.getValue() != null && !String.valueOf(brandDdlb.getValue()).equals(ConstantsUtils.SELECT_ONE))
                                || !StringUtils.isEmpty(String.valueOf(strength.getValue()))
                                || !StringUtils.isEmpty(String.valueOf(itemNo.getValue()))
                                || (therapeuticClassDdlb.getValue() != null && !String.valueOf(therapeuticClassDdlb.getValue()).equals(ConstantsUtils.SELECT_ONE))
                                || (formDdlb.getValue() != null && !String.valueOf(formDdlb.getValue()).equals(ConstantsUtils.SELECT_ONE))) {
                            searchButtonClickLogic(event);
                        } else {
                            AbstractNotificationUtils.getErrorNotification("Search Error", "No search criteria have been entered.  Please enter search criteria and try again.");
                        }
                    }

                } catch (SystemException e) {
                    final String errorMsg = ErrorCodeUtil.getErrorMessage(e);
                    LOGGER.error(e);
                    AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
                } catch (PortalException e) {
                    LOGGER.error(e);
                    AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4005));
                } catch (Exception e) {
                    LOGGER.error(e);
                    AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4005));
                }
                LOGGER.debug("In configureFields search.addClickListener Ended");
            }
        });
    }

    public void resetButton() {
        reset.addClickListener(new Button.ClickListener() {
            public void buttonClick(final Button.ClickEvent event) {
                LOGGER.debug("In configureFields reset.addClickListener started");
                MessageBox.showPlain(Icon.QUESTION, ConstantsUtils.CONFORMATION, "Are you sure you want to reset the page to default/previous values "
                        + ConstantsUtils.QUESTION_MARK, new MessageBoxListener() {
                            /**
                             * Called when reset button is clicked
                             */
                            @SuppressWarnings("PMD")
                            public void buttonClicked(final ButtonId buttonId) {
                                if (buttonId.name().equals(ConstantsUtils.YES)) {
                                    resetButtonClickLogic();
                                }
                            }
                        }, ButtonId.YES, ButtonId.NO);
                LOGGER.debug("In configureFields reset.addClickListener Ended");
            }
        });

    }

    public void resetAllButton() {
        resetAll.addClickListener(new Button.ClickListener() {

            public void buttonClick(final Button.ClickEvent event) {
                LOGGER.debug("In configureFields resetAll.addClickListener started");
                MessageBox.showPlain(Icon.QUESTION, ConstantsUtils.CONFORMATION, "Are you sure you want to reset the page to default/previous values "
                        + ConstantsUtils.QUESTION_MARK, new MessageBoxListener() {

                            @SuppressWarnings("PMD")
                            public void buttonClicked(final ButtonId buttonId) {
                                if (buttonId.name().equals(ConstantsUtils.YES)) {
                                    try {
                                        String page = sessionDTO.getFromViewPage();
                                        if (page.equals("Add")) {
                                            resetIGInformation();
                                            resetButtonClickLogic();
                                            resetTables();
                                        } else {
                                            resetAllButtonClickLogic();
                                            resetButtonClickLogic();
                                        }
                                    } catch (SystemException e) {
                                        final String errorMsg = ErrorCodeUtil.getErrorMessage(e);
                                        LOGGER.error(e);
                                        AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
                                    } catch (PortalException e) {
                                        LOGGER.error(e);
                                        AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4006));
                                    } catch (Exception e) {
                                        LOGGER.error(e);
                                        AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4006));
                                    }
                                }
                            }
                        }, ButtonId.YES, ButtonId.NO);
                LOGGER.debug("In configureFields resetAll.addClickListener Ended");
            }
        });

    }

    public void addButton() {
        add.addClickListener(new Button.ClickListener() {

            public void buttonClick(final Button.ClickEvent event) {
                LOGGER.debug("In configureFields add.addClickListener started");
                if (recordAddFlag) {
                    addItemsButtonClick(event);
                    recordAddFlag = false;
                } else {
                    MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "No items were selected in the Results list view.  Please select at least one item and try again.", ButtonId.OK);
                }
                LOGGER.debug("In configureFields add.addClickListener Ended");
            }
        });
    }

    public void addAllButton() {
        addAll.addClickListener(new Button.ClickListener() {

            public void buttonClick(final ClickEvent event) {
                LOGGER.debug("In configureFields addAll.addClickListener started");
                if (availableResultsBean.size() > ConstantsUtils.ZERO_NUM) {
                    addAllItemsButtonClick(event);
                } else {
                    MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "No items were selected in the Results list view.  Please select at least one item and try again.", ButtonId.OK);
                }
                LOGGER.debug("In configureFields addAll.addClickListener Ended");
            }
        });
    }

    public void removeButton() {

        remove.addClickListener(new Button.ClickListener() {

            public void buttonClick(final ClickEvent event) {
                LOGGER.debug("In configureFields remove.addClickListener started");
                if (recordRemoveFlag) {
                    removeItemsButtonClick(event);
                    recordRemoveFlag = false;
                } else {
                    MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, ConstantsUtils.NO_ITEMS_SELECTED, ButtonId.OK);
                }
                LOGGER.debug("In configureFields remove.addClickListener Ended");
            }
        });
    }

    public void removeAllButton() {
        removeAll.addClickListener(new Button.ClickListener() {

            public void buttonClick(final ClickEvent event) {
                LOGGER.debug("In configureFields removeAll.addClickListener started");
                if (selectedResultsBean.size() > ConstantsUtils.ZERO_NUM) {
                    removeAllItemsButtonClick(event);
                } else {
                    MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, ConstantsUtils.NO_ITEMS_SELECTED, ButtonId.OK);
                }
                LOGGER.debug("In configureFields removeAll.addClickListener Ended");
            }
        });
    }

    public void saveButton() {
        save.addClickListener(new Button.ClickListener() {

            public void buttonClick(final ClickEvent event) {
                LOGGER.debug("In configureFields save.addClickListener started");
                boolean saved = saveGroupInfo("save");
                if (!saved) {
                    return;
                }
                LOGGER.debug("In configureFields save.addClickListener Ended");
            }
        });

    }

    private void getButtonPermission(Map<String, AppPermission> functionCompanyHM) {
        LOGGER.debug("Inside Button security");

        backButton();

        searchButton();
        if (functionCompanyHM.get(ConstantsUtils.SAVE_BUTTON) != null && !((AppPermission) functionCompanyHM.get(ConstantsUtils.SAVE_BUTTON)).isFunctionFlag()) {
            save.setVisible(false);
        } else {
            saveButton();
        }
        if (functionCompanyHM.get(ConstantsUtils.REMOVE_BUTTON) != null && !((AppPermission) functionCompanyHM.get(ConstantsUtils.REMOVE_BUTTON)).isFunctionFlag()) {
            removeFlag = true;
            remove.setVisible(false);
        } else {
            removeButton();
        }
        if (functionCompanyHM.get(ConstantsUtils.ADD_BUTTON) != null && !((AppPermission) functionCompanyHM.get(ConstantsUtils.ADD_BUTTON)).isFunctionFlag()) {
            add.setVisible(false);
        } else {
            addButton();
        }
        if (functionCompanyHM.get(ConstantsUtils.ADD_ALL_BUTTON) != null && !((AppPermission) functionCompanyHM.get(ConstantsUtils.ADD_ALL_BUTTON)).isFunctionFlag()) {
            addAll.setVisible(false);
        } else {
            addAllButton();
        }
        if (functionCompanyHM.get(ConstantsUtils.REMOVE_ALL_BUTTON) != null && !((AppPermission) functionCompanyHM.get(ConstantsUtils.REMOVE_ALL_BUTTON)).isFunctionFlag()) {
            removeAllFlag = true;
            removeAll.setVisible(false);
        } else {
            removeAllButton();
        }
        if (functionCompanyHM.get(ConstantsUtils.RESET_BUTTON) != null && !((AppPermission) functionCompanyHM.get(ConstantsUtils.RESET_BUTTON)).isFunctionFlag()) {
            reset.setVisible(false);
        } else {
            resetButton();
        }
        if (functionCompanyHM.get(ConstantsUtils.RESET_ALL_BUTTON1) != null && !((AppPermission) functionCompanyHM.get(ConstantsUtils.RESET_ALL_BUTTON1)).isFunctionFlag()) {
            resetAll.setVisible(false);
        } else {
            resetAllButton();
        }

    }
    /**
     * to generate worksheet content.
     *
     * @param start the start
     * @param end the end
     * @param printWriter the print writer
     * @throws PortalException the portal exception
     * @throws SystemException the system exception
     */

}
