
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.itemmanagement.index.form;

import com.stpl.app.gcm.common.CommonLogic;
import com.stpl.app.gcm.common.CommonUtil;
import com.stpl.app.gcm.globalchange.dto.SelectionDTO;
import com.stpl.app.gcm.itemmanagement.index.dto.ItemIndexDto;
import com.stpl.app.gcm.itemmanagement.index.logic.ItemLogic;
import com.stpl.app.gcm.itemmanagement.itemabstract.queryutils.ItemQueries;
import com.stpl.app.gcm.itemmanagement.index.logic.ItemIndexTableLogic;
import com.stpl.app.gcm.itemmanagement.index.util.ConstantsUtil;
import com.stpl.app.gcm.itemmanagement.itemabstract.logic.AbstractLogic;
import com.stpl.app.gcm.security.StplSecurity;
import com.stpl.app.gcm.tp.dto.TabSelectionDTO;
import com.stpl.app.gcm.util.AbstractNotificationUtils;
import com.stpl.app.gcm.util.CommonUtils;
import com.stpl.app.gcm.util.Constants;
import com.stpl.app.gcm.util.Constants.IndicatorConstants;
import com.stpl.app.gcm.util.UiUtils;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.ExcelExportforBB;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TableFieldFactory;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.customwindow.CustomWindow;
import org.asi.ui.customwindow.MinimizeTray;
import org.asi.ui.extcustomcheckbox.ExtCustomCheckBox;
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
 * @author Srithar
 */
public class ItemManagementIndex extends CustomComponent {

    @UiField("itemId")
    public TextField itemId;
    @UiField("itemName")
    public TextField itemName;
    @UiField("therapeuticClass")
    public ComboBox therapeuticClass;
    @UiField("form")
    public ComboBox form_DTO;
    @UiField("identifierType")
    public ComboBox identifierType_DTO;
    @UiField("itemNo")
    public TextField itemNo;
    @UiField("itemDesc")
    public TextField itemDesc;
    @UiField("brand")
    public ComboBox brand_DTO;
    @UiField("strength")
    public ComboBox strength_DTO;
    @UiField("identifier")
    public TextField identifier;
    @UiField("company")
    public ComboBox company_DTO;
    @UiField("placeHolder")
    public ComboBox placeHolder_DTO;
    @UiField("ndc9")
    public TextField ndc9;
    @UiField("itemCategory")
    public ComboBox itemCategory;
    @UiField("itemType")
    public ComboBox itemType;
    @UiField("tableLayout")
    public VerticalLayout vLayout;
    @UiField("addBtn")
    public Button addBtn;
    @UiField("deleteBtn")
    public Button deleteBtn;
    @UiField("editBtn")
    public Button editBtn;
    @UiField("transferBtn")
    public Button transferBtn;
    @UiField("excelBtn")
    public Button excel;
    @UiField("searchBtn")
    public Button searchBtn;
    @UiField("resetBtn")
    public Button resetBtn;
    @UiField("tableReset")
    public Button tableReset;
    TabSheet mainTab = new TabSheet();
    public static final Logger LOGGER = Logger.getLogger(ItemManagementIndex.class);
    BeanItemContainer<ItemIndexDto> searchContainer = new BeanItemContainer<ItemIndexDto>(ItemIndexDto.class);
    ItemLogic logic = new ItemLogic();
    AbstractLogic abstractLogic = AbstractLogic.getInstance();
    ItemIndexDto binderDto = new ItemIndexDto();
    private CustomFieldGroup binder = new CustomFieldGroup(new BeanItem<ItemIndexDto>(binderDto));
    SelectionDTO selection;
    boolean resetFlag = false;
    HelperDTO ddlbDefaultValue = new HelperDTO(0, Constants.IndicatorConstants.SELECT_ONE.getConstant());
    ItemIndexTableLogic tableLogic = new ItemIndexTableLogic();
    public ExtPagedTable itemResults = new ExtPagedTable(tableLogic);
    List<ItemIndexDto> selecteditemList = new ArrayList<ItemIndexDto>();
    final SimpleDateFormat fmtID = new SimpleDateFormat("hhmmssms");
    Integer internalSessionId = 0;
    Long sessionId;
    private final Resource excelExportImage = new ThemeResource("../../icons/excel.png");
    VerticalLayout contractDashboardLay = new VerticalLayout();
    final StplSecurity stplSecurity = new StplSecurity();
    Map<String, AppPermission> functionHM = new HashMap<String, AppPermission>();
    CommonUtil commonUtil = CommonUtil.getInstance();
    UiUtils UIUtils = new UiUtils();

    public ItemManagementIndex(SelectionDTO selection) {
        this.selection = selection;
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/item/itemManagementIndex.xml"), this));
        configureSecurityPermissions();
        configureFields();
    }

    protected void configureFields() {
        try {

            configureSearch();
            configureTable();
            excel.setPrimaryStyleName("link");
            excel.setIcon(excelExportImage, "Excel Export");

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    private void configureSearch() {
        therapeuticClass.setNullSelectionAllowed(Boolean.TRUE);
        therapeuticClass.setNullSelectionItemId(IndicatorConstants.SELECT_ONE.getConstant());
        therapeuticClass.addItem(IndicatorConstants.SELECT_ONE.getConstant());
        therapeuticClass.select(IndicatorConstants.SELECT_ONE.getConstant());
        form_DTO.setNullSelectionAllowed(Boolean.TRUE);
        form_DTO.setNullSelectionItemId(IndicatorConstants.SELECT_ONE.getConstant());
        form_DTO.addItem(IndicatorConstants.SELECT_ONE.getConstant());
        form_DTO.select(IndicatorConstants.SELECT_ONE.getConstant());
        identifierType_DTO.setNullSelectionAllowed(Boolean.TRUE);
        identifierType_DTO.setNullSelectionItemId(IndicatorConstants.SELECT_ONE.getConstant());
        identifierType_DTO.addItem(IndicatorConstants.SELECT_ONE.getConstant());
        identifierType_DTO.select(IndicatorConstants.SELECT_ONE.getConstant());
        brand_DTO.setNullSelectionAllowed(Boolean.TRUE);
        brand_DTO.setNullSelectionItemId(IndicatorConstants.SELECT_ONE.getConstant());
        brand_DTO.addItem(IndicatorConstants.SELECT_ONE.getConstant());
        brand_DTO.select(IndicatorConstants.SELECT_ONE.getConstant());
        strength_DTO.setNullSelectionAllowed(Boolean.TRUE);
        strength_DTO.setNullSelectionItemId(IndicatorConstants.SELECT_ONE.getConstant());
        strength_DTO.addItem(IndicatorConstants.SELECT_ONE.getConstant());
        strength_DTO.select(IndicatorConstants.SELECT_ONE.getConstant());
        company_DTO.setNullSelectionAllowed(Boolean.TRUE);
        company_DTO.setNullSelectionItemId(IndicatorConstants.SELECT_ONE.getConstant());
        company_DTO.addItem(IndicatorConstants.SELECT_ONE.getConstant());
        company_DTO.select(IndicatorConstants.SELECT_ONE.getConstant());
        placeHolder_DTO.setNullSelectionAllowed(Boolean.TRUE);
        placeHolder_DTO.setNullSelectionItemId(IndicatorConstants.SELECT_ONE.getConstant());
        placeHolder_DTO.addItem(IndicatorConstants.SELECT_ONE.getConstant());
        placeHolder_DTO.select(IndicatorConstants.SELECT_ONE.getConstant());
        itemCategory.setNullSelectionAllowed(Boolean.TRUE);
        itemCategory.setNullSelectionItemId(IndicatorConstants.SELECT_ONE.getConstant());
        itemCategory.addItem(IndicatorConstants.SELECT_ONE.getConstant());
        itemCategory.select(IndicatorConstants.SELECT_ONE.getConstant());
        itemType.setNullSelectionAllowed(Boolean.TRUE);
        itemType.setNullSelectionItemId(IndicatorConstants.SELECT_ONE.getConstant());
        itemType.addItem(IndicatorConstants.SELECT_ONE.getConstant());
        itemType.select(IndicatorConstants.SELECT_ONE.getConstant());
        loadAllDdlb();
        getBinder();
        identifier.setEnabled(false);
        identifier.setImmediate(true);
    }

    private void configureTable() {
        addResultTable();
        tableLogic.setContainerDataSource(searchContainer);
        tableLogic.setPageLength(NumericConstants.TEN);
        tableLogic.sinkItemPerPageWithPageLength(false);
        itemResults.setVisibleColumns(CommonUtils.visibleColumnItemSearch);
        itemResults.setColumnHeaders(CommonUtils.columnHeaderItemSearch);
        itemResults.setSizeUndefined();
        itemResults.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        itemResults.setEditable(Boolean.TRUE);
        itemResults.setFilterBarVisible(true);
        itemResults.addStyleName(ConstantsUtil.FILTERCOMBOBOX);
        itemResults.setFilterDecorator(new ExtDemoFilterDecorator());
        for (Object propertyId : itemResults.getVisibleColumns()) {
            itemResults.setColumnWidth(propertyId, -1);
        }
        itemResults.setWidth("1600");
        itemResults.markAsDirty();
        itemResults.setSelectable(false);
        itemResults.setTableFieldFactory(new TableFieldFactory() {
            public Field<?> createField(Container container, final Object itemId, Object propertyId, Component uiContext) {
                if (propertyId.equals(Constants.CHECK_RECORD)) {
                    final ExtCustomCheckBox check = new ExtCustomCheckBox();

                    check.addClickListener(new ExtCustomCheckBox.ClickListener() {
                        public void click(ExtCustomCheckBox.ClickEvent event) {
                            boolean isCheck = check.getValue();

                            if (isCheck) {
                                selecteditemList.add((ItemIndexDto) itemId);
                            } else {
                                if (selecteditemList.contains(itemId)) {
                                    selecteditemList.remove(itemId);
                                }
                            }
                        }
                    });
                    return check;
                }
                return null;
            }
        });

        itemResults.setFilterGenerator(new ExtFilterGenerator() {
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
            }

            public void filterAdded(Object propertyId, Class<? extends Container.Filter> filterType, Object value) {
            }

            public Container.Filter filterGeneratorFailed(Exception reason, Object propertyId, Object value) {
                return null;
            }

            public AbstractField<?> getCustomFilterComponent(Object propertyId) {
                if (Constants.CHECK_RECORD.equals(propertyId)) {
                    CheckBox text = new CheckBox();
                    text.setVisible(false);
                    return text;
                }

                if ("company".equals(propertyId)) {
                    ComboBox companyDdlb = new ComboBox();
                    abstractLogic.LazyLoadDdlb(companyDdlb, "LoadCompanyCount", "LoadCompany", true);
                    return companyDdlb;
                }
                if ("therapeuticClass".equals(propertyId)) {
                    ComboBox therapeuticClassDdlb = new ComboBox();
                    try {
                        abstractLogic.loadComboBox(therapeuticClassDdlb, "THERAPEUTIC_CLASS", true);
                    } catch (Exception ex) {
                        LOGGER.error(ex);
                    }
                    return therapeuticClassDdlb;
                }
                if ("brand".equals(propertyId)) {
                    ComboBox brandDdlb = new ComboBox();
                    abstractLogic.LazyLoadDdlb(brandDdlb, "LoadBrandCount", "LoadBrand", true);
                    return brandDdlb;
                }
                if ("form".equals(propertyId)) {
                    ComboBox type = new ComboBox();
                    abstractLogic.LazyLoadDdlb(type, "LoadFormCount", "LoadForm", true);
                    return type;
                }
                if ("strength".equals(propertyId)) {
                    ComboBox strengthDdlb = new ComboBox();
                    abstractLogic.LazyLoadDdlb(strengthDdlb, "LoadStrengthCount", "LoadStrength", true);
                    return strengthDdlb;
                }
                if ("placeHolder".equals(propertyId)) {
                    ComboBox type = new ComboBox();
                    loadPlaceHolder(type, true);
                    return type;
                }
                if ("itemCategory".equals(propertyId)) {
                    try {
                        ComboBox itemCategoryDdlb = new ComboBox();
                        abstractLogic.loadComboBox(itemCategoryDdlb, "ITEM_CATEGORY", true);
                        return itemCategoryDdlb;
                    } catch (Exception ex) {
                        LOGGER.error(ex);
                    }
                }
                if ("itemType".equals(propertyId)) {
                    try {
                        ComboBox itemTypeDdlb = new ComboBox();
                        abstractLogic.loadComboBox(itemTypeDdlb, "ITEM_TYPE", true);
                        return itemTypeDdlb;
                    } catch (Exception ex) {
                        LOGGER.error(ex);
                    }
                }
                return null;
            }
        });
        itemResults.setFilterBarVisible(true);
        itemResults.setFilterDecorator(new ExtDemoFilterDecorator());
    }

    private void loadAllDdlb() {
        loadForm();
        loadBrand();
        loadStrength();
        loadItemType();
        loadCategory();
        loadItemTherapeuticClass();
        loadCompany();
        loadPlaceHolder(placeHolder_DTO, false);
        loadIdentifierType();
    }

    @UiHandler("searchBtn")
    public void searchButtonLogic(Button.ClickEvent event) {
        LOGGER.debug("searchButtonLogic starts");
        try {
            selecteditemList.clear();
            binder.commit();
            if ((binderDto.getItemId() == null || binderDto.getItemId().isEmpty())
                    && (binderDto.getItemName() == null || binderDto.getItemName().isEmpty())
                    && (binderDto.getTherapeuticClass() == null || Constants.SELECT_ONE.equals(binderDto.getTherapeuticClass()))
                    && (binderDto.getForm_DTO() == null)
                    && binderDto.getIdentifierType_DTO() == null
                    && (binderDto.getItemNo() == null || binderDto.getItemNo().isEmpty())
                    && (binderDto.getItemDesc() == null || binderDto.getItemDesc().isEmpty())
                    && (binderDto.getBrand_DTO() == null)
                    && (binderDto.getStrength_DTO() == null)
                    && (binderDto.getIdentifier() == null || binderDto.getIdentifier().isEmpty())
                    && (binderDto.getCompany_DTO() == null)
                    && (binderDto.getPlaceHolder_DTO() == null || binderDto.getPlaceHolder_DTO().getId() == NumericConstants.ELEVEN)
                    && (binderDto.getNdc9() == null || binderDto.getNdc9().isEmpty())
                    && (binderDto.getItemCategory() == null || Constants.SELECT_ONE.equals(binderDto.getItemCategory()) || StringUtils.EMPTY.equals(binderDto.getItemCategory().trim()))
                    && (binderDto.getItemType() == null || Constants.SELECT_ONE.equals(binderDto.getItemType()) || StringUtils.EMPTY.equals(binderDto.getItemType().trim()))) {

                MessageBox.showPlain(Icon.INFO, "Error", "Please Enter Search Criteria.", ButtonId.OK);
            } else {
                Date sessionDate = new Date();
                selection.setInternalSessionid(Integer.valueOf(fmtID.format(sessionDate)).toString());
                selection.setReset(false);
                resetFlag = true;
                if (!tableLogic.loadSetData(binderDto, selection, selecteditemList)) {

                    AbstractNotificationUtils.getErrorNotification("No Matching Records",
                            "There were no records matching the search criteria.  Please try again.");
                }
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        LOGGER.debug("searchButtonLogic ends");
    }

    @UiHandler("excelBtn")
    public void excelExport(Button.ClickEvent event) {
        try {
            if (searchContainer.size() > 0) {
                createWorkSheet("Item_Details", itemResults);
            }

        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    public void createWorkSheet(String moduleName, ExtPagedTable resultTable) throws SystemException, PortalException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        long recordCount = 0;
        List<String> visibleList = Arrays.asList(itemResults.getColumnHeaders()).subList(1, itemResults.getVisibleColumns().length);
        if (resultTable.size() != 0) {
            binderDto.setIsCount(false);
            selection.setFilters(tableLogic.getFilters());
            recordCount = logic.getSearchCount(binderDto, selection);
        }
        ExcelExportforBB.createWorkSheet(visibleList.toArray(new String[visibleList.size()]), recordCount, this, UI.getCurrent(), moduleName.replace(" ", "_").toUpperCase());
    }

    public void createWorkSheetContent(final Integer start, final Integer end, final PrintWriter printWriter) {
        binderDto.setStartIndex(start);
        binderDto.setEndIndex(end);
        binderDto.setIsCount(true);
        List visibleList = Arrays.asList(itemResults.getVisibleColumns()).subList(1, itemResults.getVisibleColumns().length);
        try {
            if (end != 0) {
                selection.setFilters(tableLogic.getFilters());
                final List<ItemIndexDto> searchList = logic.getSearchResults(binderDto, selection, selecteditemList);
                ExcelExportforBB.createFileContent(visibleList.toArray(), searchList, printWriter);
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    @UiHandler("resetBtn")
    public void resetButtonLogic(Button.ClickEvent event) {
        new AbstractNotificationUtils() {
            @Override
            public void yesMethod() {
                try {
                    binderDto = new ItemIndexDto();
                    binder.setItemDataSource(new BeanItem<>(binderDto));
                    binder.commit();
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            }

            @Override
            public void noMethod() {
            }
        }.getConfirmationMessage("Confirmation", "Are you sure you want to reset the Item Search?");

    }

    @UiHandler("tableReset")
    public void tableResetButtonLogic(Button.ClickEvent event) {
        new AbstractNotificationUtils() {
            @Override
            public void yesMethod() {
                itemResults.resetFilters();
                selection.setReset(true);
                resetFlag = true;
                tableLogic.loadSetData(binderDto, selection, null);
            }

            @Override
            public void noMethod() {
            }
        }.getConfirmationMessage("Confirmation", "Are you sure you want to reset the page to default/previous values?");
    }

    @UiHandler("addBtn")
    public void addButtonLogic(Button.ClickEvent event) throws CloneNotSupportedException {
        if (!selecteditemList.isEmpty()) {
            SelectionDTO selection = createSessionId();

            if (selection.getButtonMode().equals(ConstantsUtil.ADD)) {
                selection.setWindowName("Add Item");
                if (selecteditemList.size() < NumericConstants.TWO) {
                    final ItemManagementLookup addWindow = new ItemManagementLookup(selecteditemList, selection);
                    createWindow(addWindow);
                } else {
                    MessageBox.showPlain(Icon.INFO, "Error", "Please select one item to proceed.", ButtonId.OK);
                }

            } else {
                MessageBox.showPlain(Icon.INFO, "Error", "For the selected Update Type, this operation is not valid ", ButtonId.OK);

            }

        } else {
            AbstractNotificationUtils.getErrorNotification("Error", "Please select a value in the Results list view then try again.");
        }
    }

    @UiHandler("transferBtn")
    public void transferButtonLogic(Button.ClickEvent event) throws CloneNotSupportedException {
        LOGGER.debug("Entering Transfer Button");
        if (!selecteditemList.isEmpty()) {
            SelectionDTO selection = createSessionId();
            if (selection.getButtonMode().equals(ConstantsUtil.TRANSFER) || selection.getButtonMode().equals(ConstantsUtil.PROJECTIONTRANSFER)) {
                if (selection.getButtonMode().equals(ConstantsUtil.PROJECTIONTRANSFER)) {
                    selection.setWindowName("Projection Details Transfer");
                } else {
                    selection.setWindowName("Transfer Item");
                }
                if (selecteditemList.size() != 1) {
                    if (checkOneContract()) {

                        final ItemManagementLookup projectionTransferWindow = new ItemManagementLookup(selecteditemList, selection);
                        createWindow(projectionTransferWindow);
                    } else {
                        MessageBox.showPlain(Icon.INFO, "Error", "The selected Items do not belong to the same Contract. Please refine your selection to only include Items that are on the same Contract.", ButtonId.OK);
                    }
                } else {
                    if (checkOneContract()) {

                        final ItemManagementLookup itemTransferWindow = new ItemManagementLookup(selecteditemList, selection);
                        createWindow(itemTransferWindow);
                    } else {
                        MessageBox.showPlain(Icon.INFO, "Error", "The selected Item do not belong to any Contract. \n"
                                + "Please refine your selection to only include a Item that is attached to a Contract.", ButtonId.OK);
                    }
                }
            } else {
                MessageBox.showPlain(Icon.INFO, "Error", "For the selected Update Type, this operation is not valid ", ButtonId.OK);

            }

        } else {
            AbstractNotificationUtils.getErrorNotification("Error", "Please select a value in the Results list view then try again.");
        }
        LOGGER.debug("Ending Transfer Button");
    }

    @UiHandler("editBtn")
    public void editButtonLogic(Button.ClickEvent event) throws CloneNotSupportedException {
        if (!selecteditemList.isEmpty()) {
            SelectionDTO selection = createSessionId();
            if (selection.getButtonMode().equals(ConstantsUtil.EDIT)) {
                selection.setWindowName("Update Item");
                if (true) {
                    final ItemManagementLookup editWindow = new ItemManagementLookup(selecteditemList, selection);
                    createWindow(editWindow);
                } else {
                    MessageBox.showPlain(Icon.INFO, "Error", "The selected Items do not belong to the same Contract. Please refine your selection to only include Items that are on the same Contract.", ButtonId.OK);
                }

            } else {
                MessageBox.showPlain(Icon.INFO, "Error", "For the selected Update Type, this operation is not valid ", ButtonId.OK);

            }

        } else {
            AbstractNotificationUtils.getErrorNotification("Error", "Please select a value in the Results list view then try again.");
        }
    }

    @UiHandler("deleteBtn")
    public void deleteButtonLogic(Button.ClickEvent event) throws CloneNotSupportedException {
        if (!selecteditemList.isEmpty()) {
            SelectionDTO selection = createSessionId();
            if (selection.getButtonMode().equals(ConstantsUtil.DELETE)) {
                selection.setWindowName("Remove Item");
                if (true) {
                    final ItemManagementLookup removeWidow = new ItemManagementLookup(selecteditemList, selection);
                    createWindow(removeWidow);
                } else {
                    MessageBox.showPlain(Icon.INFO, "Error", "The selected Items do not belong to the same Contract. Please refine your selection to only include Items that are on the same Contract.", ButtonId.OK);
                }
            } else {
                MessageBox.showPlain(Icon.INFO, "Error", "For the selected Update Type, this operation is not valid ", ButtonId.OK);

            }

        } else {
            AbstractNotificationUtils.getErrorNotification("Error", "Please select a value in the Results list view then try again.");
        }
    }

    @UiHandler("identifierType")
    public void identifierTypeDdlbChange(Property.ValueChangeEvent event) {
        if (identifierType_DTO.getValue() != null) {
            identifier.setValue(StringUtils.EMPTY);
            identifier.setEnabled(true);
        } else {
            identifier.setValue(StringUtils.EMPTY);
            identifier.setEnabled(false);
        }
    }

    private void loadForm() {
        abstractLogic.LazyLoadDdlb(form_DTO, "LoadFormCount", "LoadForm", false);
    }

    private void loadBrand() {
        abstractLogic.LazyLoadDdlb(brand_DTO, "LoadBrandCount", "LoadBrand", false);
    }

    private void loadStrength() {
        abstractLogic.LazyLoadDdlb(strength_DTO, "LoadStrengthCount", "LoadStrength", false);
    }

    private void loadItemType() {
        commonUtil.loadComboBox(itemType, UIUtils.ITEM_TYPE, false);

    }

    private void loadItemTherapeuticClass() {
        commonUtil.loadComboBox(therapeuticClass, UIUtils.THERAPEUTIC_CLASS, false);
    }

    private void loadCategory() {
        commonUtil.loadComboBox(itemCategory, UIUtils.ITEM_CATEGORY, false);
    }

    private void loadCompany() {
        abstractLogic.LazyLoadDdlb(company_DTO, "LoadCompanyCount", "LoadCompany", false);
    }

    private void loadPlaceHolder(ComboBox placeHolder_DTO, boolean isFilter) {
        BeanItemContainer<HelperDTO> container = new BeanItemContainer<HelperDTO>(HelperDTO.class);
        List<HelperDTO> placeHolderList = new ArrayList<HelperDTO>();
        HelperDTO dto = new HelperDTO(NumericConstants.ELEVEN, Constants.SELECT_ONE);
        HelperDTO showAll = new HelperDTO(NumericConstants.ELEVEN, Constants.SHOW_ALL);
        HelperDTO yesDto = new HelperDTO(1, "Yes");
        HelperDTO noDto = new HelperDTO(0, "No");
        if (!isFilter) {
            placeHolderList.add(dto);
        } else {
            placeHolderList.add(showAll);
        }
        placeHolderList.add(yesDto);
        placeHolderList.add(noDto);
        placeHolder_DTO.setContainerDataSource(container);
        if (!isFilter) {
            placeHolder_DTO.setNullSelectionItemId(dto);
        } else {
            placeHolder_DTO.setNullSelectionItemId(showAll);
        }
        placeHolder_DTO.setNullSelectionAllowed(true);
        placeHolder_DTO.setImmediate(true);
        placeHolder_DTO.setItemCaptionPropertyId("description");
        container.addAll(placeHolderList);
        placeHolder_DTO.select(dto);
    }

    private CustomFieldGroup getBinder() {
        binder.bindMemberFields(this);
        binder.setItemDataSource(new BeanItem<ItemIndexDto>(binderDto));
        binder.setBuffered(true);
        return binder;
    }

    /**
     * Add Result Table.
     */
    @SuppressWarnings("serial")
    private void addResultTable() {
        vLayout.addComponent(itemResults);
        HorizontalLayout controls = tableLogic.createControls();
        HorizontalLayout controlLayout = CommonLogic.getResponsiveControls(controls);
        vLayout.addComponent(controlLayout);
    }

    private SelectionDTO createSessionId() throws CloneNotSupportedException {
        SelectionDTO selection = this.selection.clone();
        selection.setContractMasterSid(StringUtils.EMPTY);
        selection.setIfpConteractSid(0);
        selection.setCfpContractSid(0);
        selection.setPsContractSid(0);
        selection.setRsContractSid(0);
        selection.setTransterTabItemIds(new ArrayList());
        selection.setContractMasterSid(StringUtils.EMPTY);
        TabSelectionDTO selectionDTO = new TabSelectionDTO();
        selectionDTO.setItemList(selecteditemList);
        selection.setItemList(selecteditemList);
        selection.setTabSelection(selectionDTO);
        Date sessionDate = new Date();
        selection.setSessionId(Integer.valueOf(fmtID.format(sessionDate)));
        selection.setSessionDate(sessionDate);
        selection.setUserId(Integer.valueOf(String.valueOf(VaadinSession.getCurrent().getAttribute("userId"))));
        return selection;
    }

    private void loadIdentifierType() {
        abstractLogic.LazyLoadDdlb(identifierType_DTO, "LoadIdentifierType Count", "LoadIdentifierType", false);
    }

    private boolean checkOneContract() {
        List input = new ArrayList();
        input.add(AbstractLogic.getItemIds(selecteditemList));
        input.add(selecteditemList.size());
        List list = ItemQueries.getItemData(input, "Checking the items in one contract", null);

        if (list == null || list.isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     * Used to close the Edit tray
     *
     * @param editWindow
     */
    private void closeEditTray(CustomWindow editWindow) {
        MinimizeTray tray = editWindow.getMinimizeTray();
        if (tray.getWindowItems() != null && tray.getWindowItems().size() == 1) {
            tray.close();
        }
    }

    private void createWindow(final ItemManagementLookup editWindow) {
        editWindow.setClosable(false);
        UI.getCurrent().addWindow(editWindow);
        editWindow.addCloseListener(new CustomWindow.CloseListener() {

            @Override
            public void windowClose(Window.CloseEvent e) {
                closeEditTray(editWindow);
            }
        });
        editWindow.closeBtn.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                editWindow.closeBtnLogic();
                closeEditTray(editWindow);
            }
        });
    }

    /**
     * Configure security
     *
     */
    private void configureSecurityPermissions() {
        try {
            String userId = String.valueOf(VaadinSession.getCurrent().getAttribute("userId"));
            Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(userId, "GCM-Item Management", "item Management", "Landing screen");
            searchBtn.setVisible(CommonLogic.isButtonVisibleAccess("searchBtn", functionHM));
            resetBtn.setVisible(CommonLogic.isButtonVisibleAccess("resetBtn", functionHM));
            addBtn.setVisible(CommonLogic.isButtonVisibleAccess("addBtn", functionHM));
            editBtn.setVisible(CommonLogic.isButtonVisibleAccess("editBtn", functionHM));
            deleteBtn.setVisible(CommonLogic.isButtonVisibleAccess("deleteBtn", functionHM));
            transferBtn.setVisible(CommonLogic.isButtonVisibleAccess("transferBtn", functionHM));
            tableReset.setVisible(CommonLogic.isButtonVisibleAccess("tableReset", functionHM));
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }
}
