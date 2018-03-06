/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.itemmanagement.transfer;

import com.stpl.app.gcm.util.StringConstantsUtil;
import com.stpl.app.gcm.common.CommonLogic;
import com.stpl.app.gcm.globalchange.dto.SelectionDTO;
import com.stpl.app.gcm.itemmanagement.index.dto.ItemIndexDto;
import com.stpl.app.gcm.itemmanagement.index.logic.ItemIndexTableLogic;
import com.stpl.app.gcm.itemmanagement.index.logic.ItemLogic;
import com.stpl.app.gcm.itemmanagement.itemabstract.logic.AbstractLogic;
import com.stpl.app.gcm.util.AbstractNotificationUtils;
import com.stpl.app.gcm.util.Constants;
import com.stpl.app.gcm.util.UiUtils;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.ExcelExportforBB;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.constants.BooleanConstant;
import com.vaadin.v7.ui.ComboBox;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.fieldgroup.FieldGroup;
import com.vaadin.v7.data.util.BeanItem;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import org.asi.ui.extfilteringtable.ExtCustomTable;
import com.vaadin.v7.ui.Field;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.v7.ui.TableFieldFactory;
import com.vaadin.v7.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.v7.ui.VerticalLayout;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extcustomcheckbox.ExtCustomCheckBox;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author hazi.s
 */
public class ItemSelection extends CustomComponent {

    private static final BooleanConstant BOOLEAN_CONSTANT = new BooleanConstant();
    
    @UiField("startingPanel")
    private Panel startingPanel;
    @UiField("selectedItems")
    private Panel selectedItems;
    @UiField("itemID")
    private TextField itemID;
    @UiField("itemName")
    private TextField itemName;
    @UiField("therapeuticClass")
    private ComboBox therapeuticClass_DTO;
    @UiField("form")
    private ComboBox form_DTO;
    @UiField("identifierType")
    private ComboBox identifierType_DTO;
    @UiField("itemNo")
    private TextField itemNo;
    @UiField("itemDesc")
    private TextField itemDesc;
    @UiField("brand")
    private ComboBox brand_DTO;
    @UiField("strength")
    private ComboBox strength_DTO;
    @UiField("identifier")
    private TextField identifier;
    @UiField("company")
    private ComboBox company_DTO;
    @UiField("placeHolder")
    private ComboBox placeHolder_DTO;
    @UiField("ndc9")
    private TextField ndc9;
    @UiField("itemCategory")
    private ComboBox itemCategory_DTO;
    @UiField("itemType")
    private ComboBox itemType_DTO;
    @UiField("resetBtn")
    private Button resetBtn;
    @UiField("searchBtn")
    private Button searchBtn;
    @UiField("results")
    private Panel results;
    @UiField("resetBtncur")
    private Button resetBtncur;
    @UiField("transferBtncur")
    private Button transferBtncur;
    @UiField("exportBtncurr")
    private Button exportBtncurr;
    @UiField("transferItems")
    private Panel transferItems;
    @UiField("removeBtncur")
    private Button removeBtncur;
    @UiField("exportBtncur")
    private Button exportBtncur;
    @UiField("SelectItemTableLayout")
    private VerticalLayout selectItemTableLayout;
    private final Resource excelExportImage = new ThemeResource("img/excel.png");
    @UiField("resultTableLayout")
    private VerticalLayout resultTableLayout;
    @UiField("transferItemsTable")
    private VerticalLayout transferItemsTable;
    @UiField("componentVertical")
    private VerticalLayout componentVertical;
    @UiField("export")
    private Button export;
    private final SelectionDTO selection;
    private final ItemIndexTableLogic tableLogic = new ItemIndexTableLogic();
    private final ExtPagedTable itemFromLS = new ExtPagedTable(tableLogic);
    private List<ItemIndexDto> selecteditemList = new ArrayList<>();
    private final List<ItemIndexDto> selectedToBeTransferredList = new ArrayList<>();
    private final BeanItemContainer<ItemIndexDto> itemViewContainer = new BeanItemContainer<>(ItemIndexDto.class);
    private final BeanItemContainer<ItemIndexDto> searchContainer = new BeanItemContainer<>(ItemIndexDto.class);
    private final BeanItemContainer<ItemIndexDto> transferContainer = new BeanItemContainer<>(ItemIndexDto.class);
    private final ItemIndexDto binderDto = new ItemIndexDto();
    private final ErrorfulFieldGroup binder = new ErrorfulFieldGroup(new BeanItem<>(binderDto));
    private final AbstractLogic logic = AbstractLogic.getInstance();
    private final ItemIndexTableLogic resultTableLogic = new ItemIndexTableLogic();
    private final ExtPagedTable itemResult = new ExtPagedTable(resultTableLogic);
    private final SimpleDateFormat fmtID = new SimpleDateFormat("hhmmssms");
    public static final Logger LOGGER = LoggerFactory.getLogger(ItemSelection.class);
    private final ExtFilterTable transferTable = new ExtFilterTable();
    
    private final List<ItemIndexDto> excelList = new ArrayList<>();
    private final List<String> sidList = new ArrayList<>();
    private final List<String> tranferredCount = new ArrayList<>();
    private final List<String> fromLsSidList = new ArrayList<>();
    private String excelName = StringUtils.EMPTY;
    public static final String EXCEL_EXPORT = "Excel Export";
    
    public ItemSelection(SelectionDTO selection, List<ItemIndexDto> itemList) {
        this.selecteditemList = itemList == null ? itemList : new ArrayList<>(itemList);
        this.selection = selection;
    }

    public Component getContent() {

        VerticalLayout layout = new VerticalLayout();
        layout.addComponent(Clara.create(getClass().getResourceAsStream("/item/itemSelection.xml"), this));
        configuretable();
        configureResultTable();
        configureTansferTable();
        configureSearch();
        Panel panel = new Panel();
        panel.setContent(layout);
        configurefields();
        return panel;
    }

    private void configurefields() {
        exportBtncur.addStyleName("link");
        exportBtncur.setIcon(excelExportImage, EXCEL_EXPORT);
        exportBtncurr.addStyleName("link");
        exportBtncurr.setIcon(excelExportImage, EXCEL_EXPORT);
        export.addStyleName("link");
        export.setIcon(excelExportImage, EXCEL_EXPORT);
    }

    @UiHandler("export")
    public void exportButtonLogic(Button.ClickEvent event) {        
        try {
            if (itemViewContainer.size() > 0) {
                createWorkSheet(StringConstantsUtil.SELECTED_ITEMS);
            }
        } catch (Exception e) {
            LOGGER.error("",e);
        }
    }
    
    public void createWorkSheet(String moduleName) throws   NoSuchMethodException, IllegalAccessException,  InvocationTargetException {
        long recordCount = selecteditemList.size();
        List<String> visibleList = Arrays.asList(itemFromLS.getColumnHeaders()).subList(1, itemFromLS.getVisibleColumns().length);
        excelName = StringConstantsUtil.SELECTED_ITEMS;
        ExcelExportforBB.createWorkSheet(visibleList.toArray(new String[visibleList.size()]), recordCount, this, UI.getCurrent(), moduleName.replace(' ', '_'));        
    }

    public void createWorkSheetContent(final Integer start, final Integer end, final PrintWriter printWriter) throws  NoSuchMethodException, IllegalAccessException,  InvocationTargetException, NoSuchFieldException {
        binderDto.setStartIndex(start);
        binderDto.setEndIndex(end);
        binderDto.setIsCount(true);
        if(excelName.equals(StringConstantsUtil.SELECTED_ITEMS)){
            List visibleList = Arrays.asList(itemFromLS.getVisibleColumns()).subList(1, itemFromLS.getVisibleColumns().length);        
            ExcelExportforBB.createFileContent(visibleList.toArray(), selecteditemList, printWriter);
        } 
        if(excelName.equals(StringConstantsUtil.ITEM_RESULTS)){
            ItemLogic logic = new ItemLogic();
            List<ItemIndexDto> list = null;
            if (!selection.isReset()) {
                list = logic.getSearchResults(binderDto, selection, selecteditemList);                
            }
            List visibleList = Arrays.asList(itemResult.getVisibleColumns()).subList(1, itemResult.getVisibleColumns().length);        
            ExcelExportforBB.createFileContent(visibleList.toArray(), list, printWriter);
        }
        if(excelName.equals(StringConstantsUtil.TRANSFER_ITEMS)){
            List<ItemIndexDto> list = transferContainer.getItemIds();
            List visibleList = Arrays.asList(transferTable.getVisibleColumns()).subList(1, transferTable.getVisibleColumns().length);        
            ExcelExportforBB.createFileContent(visibleList.toArray(), list, printWriter);
        }
    }

    @UiHandler("resetBtn")
    public void resetBtnButtonLogic(Button.ClickEvent event) {
        new AbstractNotificationUtils() {
            @Override
            public void yesMethod() {
                itemResult.resetFilters();
                itemFromLS.resetFilters();
                resetSearchField();
            }

            @Override
            public void noMethod() {
                return;
            }
        }.getConfirmationMessage("Confirmation", "Are you sure you want to reset the Search Criteria?");


    }

    @UiHandler("resetBtncur")
    public void resetBtncurBtnButtonLogic(Button.ClickEvent event) {
        new AbstractNotificationUtils() {
            @Override
            public void yesMethod() {
                itemResult.resetFilters();
                itemFromLS.resetFilters();
                resetSearchField();
                selection.setReset(true);
                resultTableLogic.loadSetData(binderDto, selection, null);
                transferTable.resetFilters();
            }

            @Override
            public void noMethod() {
                return;
            }
        }.getConfirmationMessage("Confirmation", "Are you sure you want to reset the Results list view?");
    }

    @UiHandler("transferBtncur")
    public void transferBtncurButtonLogic(Button.ClickEvent event) {
        setFromItemSid();
        if (itemFromLS.getValue() != null && itemResult.getValue() != null) {
            final List<ItemIndexDto> list = new ArrayList<>();
            ItemIndexDto fromDTO = (ItemIndexDto) itemFromLS.getValue();
            ItemIndexDto toDTO = (ItemIndexDto) itemResult.getValue();
            ItemIndexDto transferDTO = new ItemIndexDto();
            transferDTO.setItemId(fromDTO.getItemId());
            transferDTO.setItemNo(fromDTO.getItemNo());
            transferDTO.setItemName(fromDTO.getItemName());
            transferDTO.setItemIdTo(toDTO.getItemId());
            transferDTO.setItemNoTo(toDTO.getItemNo());
            transferDTO.setItemNameTo(toDTO.getItemName());
            transferDTO.setSystemId(toDTO.getSystemId());
            transferDTO.setFromSid(fromDTO.getSystemId());
            sidList.add(toDTO.getSystemId());
            list.add(transferDTO);
            excelList.add(transferDTO);
            selection.setTransferItemList(excelList);
            transferContainer.addAll(list);
            itemResult.removeItem(toDTO);
        } else {
            AbstractNotificationUtils.getErrorNotification("Transfer Error",
                    "Please select an Item value in both the Selected Items, and the Results list view then try again. ");
        }
         selection.setTransterItemIds(transferContainer.getItemIds());
        setTransferredIdCount();
    }

    @UiHandler("exportBtncurr")
    public void exportBtncurrButtonLogic(Button.ClickEvent event) {        
        try {
            if (searchContainer.size() > 0) {
                createWorkSheetItemResults(StringConstantsUtil.ITEM_RESULTS);
            }
        } catch (Exception e) {
            LOGGER.error("",e);
        }
    }
    public void createWorkSheetItemResults(String moduleName) throws   NoSuchMethodException, IllegalAccessException,  InvocationTargetException {
        long recordCount = 0;
        binderDto.setIsCount(false);
        ItemLogic logic = new ItemLogic();
        if (!selection.isReset()) {
           recordCount = logic.getSearchCount(binderDto, selection);           
        }
        List<String> visibleList = Arrays.asList(itemResult.getColumnHeaders()).subList(1, itemResult.getVisibleColumns().length);
        excelName = StringConstantsUtil.ITEM_RESULTS;
        ExcelExportforBB.createWorkSheet(visibleList.toArray(new String[visibleList.size()]), recordCount, this, UI.getCurrent(), moduleName.replace(' ', '_').toUpperCase());        
    }


    @UiHandler("removeBtncur")
    public void removeBtncurButtonLogic(Button.ClickEvent event) {
        if (!selectedToBeTransferredList.isEmpty()) {
            new AbstractNotificationUtils() {
                @Override
                public void yesMethod() {
                    Collection object = transferTable.getItemIds();
                    List<ItemIndexDto> toBeRemoved = new ArrayList<>();
                    for (Object itemId : object) {
                        ItemIndexDto dto = (ItemIndexDto) itemId;
                        if (dto.getCheckRecord()) {
                            toBeRemoved.add(dto);
                        }
                    }
                    for (Object itemId : toBeRemoved) {
                        transferTable.removeItem(itemId);
                        if (excelList.contains(itemId)) {
                            excelList.remove(itemId);
                        }
                    }
                }

                @Override
                public void noMethod() {
                    return;
                }
            }.getConfirmationMessage("Remove Confirmation", "Are you sure you want to undo these selected Item links? Items will be returned to their respective list views. ");
        } else {
            AbstractNotificationUtils.getErrorNotification("No values selected", "Please select a record to remove. Then try again.");
        }
    }

    @UiHandler("exportBtncur")
    public void exportBtncurLogic(Button.ClickEvent event) {
        
        try {
            if (transferContainer.size() > 0) {
                createWorkSheetTransferItem(StringConstantsUtil.TRANSFER_ITEMS);
            }
        } catch (Exception e) {
            LOGGER.error("",e);
        }
    }
    public void createWorkSheetTransferItem(String moduleName) throws   NoSuchMethodException, IllegalAccessException,  InvocationTargetException {
        long recordCount = 0;
        
        List<ItemIndexDto> list = transferContainer.getItemIds();
        recordCount = list.size();
        List<String> visibleList = Arrays.asList(transferTable.getColumnHeaders()).subList(1, transferTable.getVisibleColumns().length);
        excelName = StringConstantsUtil.TRANSFER_ITEMS;
        ExcelExportforBB.createWorkSheet(visibleList.toArray(new String[visibleList.size()]), recordCount, this, UI.getCurrent(), moduleName.toUpperCase());        
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

    @UiHandler("searchBtn")
    public void searchButtonLogic(Button.ClickEvent event) throws FieldGroup.CommitException {
        binder.commit();
        if ((binderDto.getItemId() == null || binderDto.getItemId().isEmpty()) && (binderDto.getItemName() == null || binderDto.getItemName().isEmpty())
                && (binderDto.getTherapeuticClass_DTO() == null) && (binderDto.getForm_DTO() == null)
                && binderDto.getIdentifierType_DTO() == null && (binderDto.getItemNo() == null || binderDto.getItemNo().isEmpty())
                && (binderDto.getItemDesc() == null || binderDto.getItemDesc().isEmpty())
                && (binderDto.getBrand_DTO() == null) && (binderDto.getStrength_DTO() == null)
                && (binderDto.getIdentifier() == null || binderDto.getIdentifier().isEmpty()) && (binderDto.getCompany_DTO() == null)
                && (binderDto.getPlaceHolder_DTO() == null)
                && (binderDto.getNdc9() == null || binderDto.getNdc9().isEmpty()) && (binderDto.getItemCategory_DTO() == null)
                && (binderDto.getItemType_DTO() == null)) {

            MessageBox.showPlain(Icon.INFO, "Error", "Please enter/select search criteria", ButtonId.OK);
        } else {
            Date sessionDate = new Date();
            selection.setInternalSessionid(Integer.valueOf(fmtID.format(sessionDate)).toString());
            selection.setReset(false);
            binderDto.setPlaceHolderValue("No");
            if (!resultTableLogic.loadSetData(binderDto, selection, selecteditemList)) {

                AbstractNotificationUtils.getErrorNotification("No Matching Records",
                        "There were no records matching the search criteria.  Please try again.");
            }
        }
    }

    private void configuretable() {
        addItemTable();
        tableLogic.setContainerDataSource(itemViewContainer);
        tableLogic.sinkItemPerPageWithPageLength(false);
        tableLogic.setPageLength(NumericConstants.FIVE);
        itemFromLS.setSizeFull();
        itemFromLS.setVisibleColumns(UiUtils.getInstance().visibleColumnItem);
        itemFromLS.setColumnHeaders(UiUtils.getInstance().columnHeaderItem);
        itemFromLS.setEditable(BOOLEAN_CONSTANT.getFalseFlag());
        itemFromLS.setSelectable(true);
        itemFromLS.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        itemFromLS.addItems(selecteditemList);

    }

    private void configureTansferTable() {
        addTransferTable();
        transferTable.setContainerDataSource(transferContainer);
        transferTable.setSizeFull();
        transferTable.setPageLength(NumericConstants.FIVE);
        transferTable.setVisibleColumns(UiUtils.getInstance().transferVisible);
        transferTable.setColumnHeaders(UiUtils.getInstance().transferHeader);
        transferTable.setEditable(BOOLEAN_CONSTANT.getTrueFlag());
        transferTable.setSelectable(BOOLEAN_CONSTANT.getFalseFlag());
        transferTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        transferTable.setColumnCheckBox(Constants.CHECK_RECORD, BOOLEAN_CONSTANT.getTrueFlag());
        transferTable.setTableFieldFactory(new TableFieldFactory() {
            @Override
            public Field<?> createField(Container container, final Object itemId, Object propertyId, Component uiContext) {
                if (propertyId.equals(Constants.CHECK_RECORD)) {
                    final ExtCustomCheckBox check = new ExtCustomCheckBox();

                    check.addClickListener(new ExtCustomCheckBox.ClickListener() {
                        @Override
                        public void click(ExtCustomCheckBox.ClickEvent event) {
                            boolean isCheck = check.getValue();
                            if (isCheck) {
                                selectedToBeTransferredList.add((ItemIndexDto) itemId);
                            } else {
                                if (selectedToBeTransferredList.contains(itemId)) {
                                    selectedToBeTransferredList.remove(itemId);
                                }
                            }
                        }
                    });
                    return check;
                }
                return null;
            }
        });

        transferTable.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {
            @Override
            public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                Collection itemList = transferTable.getItemIds();
                for (Object obj : itemList) {
                    ItemIndexDto dto = (ItemIndexDto) obj;
                    dto.setCheckRecord(event.isChecked());
                    if (event.isChecked()) {
                        selectedToBeTransferredList.add(dto);
                    } else {
                        selectedToBeTransferredList.clear();
                    }
                    transferTable.getContainerProperty(obj, event.getPropertyId()).setValue(event.isChecked());
                }
            }
        });
    }

    public void configureSearch() {

        therapeuticClass_DTO.setNullSelectionAllowed(BOOLEAN_CONSTANT.getTrueFlag());
        therapeuticClass_DTO.setNullSelectionItemId(Constants.IndicatorConstants.SELECT_ONE.getConstant());
        therapeuticClass_DTO.addItem(Constants.IndicatorConstants.SELECT_ONE.getConstant());
        therapeuticClass_DTO.select(Constants.IndicatorConstants.SELECT_ONE.getConstant());
        form_DTO.setNullSelectionAllowed(BOOLEAN_CONSTANT.getTrueFlag());
        form_DTO.setNullSelectionItemId(Constants.IndicatorConstants.SELECT_ONE.getConstant());
        form_DTO.addItem(Constants.IndicatorConstants.SELECT_ONE.getConstant());
        form_DTO.select(Constants.IndicatorConstants.SELECT_ONE.getConstant());
        identifierType_DTO.setNullSelectionAllowed(BOOLEAN_CONSTANT.getTrueFlag());
        identifierType_DTO.setNullSelectionItemId(Constants.IndicatorConstants.SELECT_ONE.getConstant());
        identifierType_DTO.addItem(Constants.IndicatorConstants.SELECT_ONE.getConstant());
        identifierType_DTO.select(Constants.IndicatorConstants.SELECT_ONE.getConstant());
        brand_DTO.setNullSelectionAllowed(BOOLEAN_CONSTANT.getTrueFlag());
        brand_DTO.setNullSelectionItemId(Constants.IndicatorConstants.SELECT_ONE.getConstant());
        brand_DTO.addItem(Constants.IndicatorConstants.SELECT_ONE.getConstant());
        brand_DTO.select(Constants.IndicatorConstants.SELECT_ONE.getConstant());
        strength_DTO.setNullSelectionAllowed(BOOLEAN_CONSTANT.getTrueFlag());
        strength_DTO.setNullSelectionItemId(Constants.IndicatorConstants.SELECT_ONE.getConstant());
        strength_DTO.addItem(Constants.IndicatorConstants.SELECT_ONE.getConstant());
        strength_DTO.select(Constants.IndicatorConstants.SELECT_ONE.getConstant());
        company_DTO.setNullSelectionAllowed(BOOLEAN_CONSTANT.getTrueFlag());
        company_DTO.setNullSelectionItemId(Constants.IndicatorConstants.SELECT_ONE.getConstant());
        company_DTO.addItem(Constants.IndicatorConstants.SELECT_ONE.getConstant());
        company_DTO.select(Constants.IndicatorConstants.SELECT_ONE.getConstant());
        itemCategory_DTO.setNullSelectionAllowed(BOOLEAN_CONSTANT.getTrueFlag());
        itemCategory_DTO.setNullSelectionItemId(Constants.IndicatorConstants.SELECT_ONE.getConstant());
        itemCategory_DTO.addItem(Constants.IndicatorConstants.SELECT_ONE.getConstant());
        itemCategory_DTO.select(Constants.IndicatorConstants.SELECT_ONE.getConstant());
        itemType_DTO.setNullSelectionAllowed(BOOLEAN_CONSTANT.getTrueFlag());
        itemType_DTO.setNullSelectionItemId(Constants.IndicatorConstants.SELECT_ONE.getConstant());
        itemType_DTO.addItem(Constants.IndicatorConstants.SELECT_ONE.getConstant());
        itemType_DTO.select(Constants.IndicatorConstants.SELECT_ONE.getConstant());
        loadAllDdlb();
        getBinder();
        identifier.setEnabled(false);
        identifier.setImmediate(true);
    }

    public void loadAllDdlb() {
        try {
            loadCompany();
            loadForm();
            loadBrand();
            loadStrength();
            loadItemType();
            loadCategory();
            loadItemTherapeuticClass();
            loadPlaceHolder(placeHolder_DTO, false);
            loadIdentifierType();
        } catch (Exception ex) {
           LOGGER.error("",ex);
        }
    }

    private void loadCompany() {
        logic.LazyLoadDdlb(company_DTO, "LoadCompanyCount", "LoadCompany", BOOLEAN_CONSTANT.getFalseFlag());
    }

    private void loadForm() {
        logic.LazyLoadDdlb(form_DTO, "LoadFormCount", "LoadForm", BOOLEAN_CONSTANT.getFalseFlag());
    }

    private void loadBrand() {
        logic.LazyLoadDdlb(brand_DTO, "LoadBrandCount", "LoadBrand", BOOLEAN_CONSTANT.getFalseFlag());
    }

    private void loadStrength() {
        logic.LazyLoadDdlb(strength_DTO, "LoadStrengthCount", "LoadStrength", BOOLEAN_CONSTANT.getFalseFlag());
    }

    private void loadItemType() {
        logic.loadComboBox(itemType_DTO, "ITEM_TYPE", false);
    }

    private void loadItemTherapeuticClass() {
        logic.loadComboBox(therapeuticClass_DTO, "THERAPEUTIC_CLASS", false);
    }

    private void loadCategory() {
        logic.loadComboBox(itemCategory_DTO, "ITEM_CATEGORY", false);
    }

    private void loadPlaceHolder(ComboBox placeHolder_DTO, boolean isFilter) {
        BeanItemContainer<HelperDTO> container = new BeanItemContainer<>(HelperDTO.class);
        List<HelperDTO> placeHolderList = new ArrayList<>();
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
        placeHolder_DTO.setItemCaptionPropertyId("description");
        container.addAll(placeHolderList);
        placeHolder_DTO.select(dto);
    }

    private void loadIdentifierType() {
        logic.LazyLoadDdlb(identifierType_DTO, "LoadIdentifierType Count", "LoadIdentifierType", BOOLEAN_CONSTANT.getFalseFlag());
    }

    @SuppressWarnings("serial")
    private void addItemTable() {
        selectItemTableLayout.setSizeFull();
        selectItemTableLayout.addComponent(itemFromLS);
    }

    private void addTransferTable() {
        transferItemsTable.setSizeFull();
        componentVertical.setSizeFull();
        transferItemsTable.addComponent(transferTable);
    }

    private ErrorfulFieldGroup getBinder() {
        binder.bindMemberFields(this);
        binder.setItemDataSource(new BeanItem<>(binderDto));
        binder.setBuffered(true);
        return binder;
    }

    private void configureResultTable() {
        addResultTable();
        resultTableLogic.setContainerDataSource(searchContainer);
        resultTableLogic.setPageLength(NumericConstants.FIVE);
        resultTableLogic.sinkItemPerPageWithPageLength(false);
        itemResult.setSizeFull();
        itemResult.setVisibleColumns(UiUtils.getInstance().visibleColumnItem);
        itemResult.setColumnHeaders(UiUtils.getInstance().columnHeaderItem);
        itemResult.setEditable(BOOLEAN_CONSTANT.getFalseFlag());
        itemResult.setSelectable(true);
        itemResult.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        itemResult.setColumnCheckBox(Constants.CHECK_RECORD, BOOLEAN_CONSTANT.getTrueFlag());
    }

    private void addResultTable() {
        resultTableLayout.setSizeFull();
        resultTableLayout.addComponent(itemResult);
        HorizontalLayout controls = resultTableLogic.createControls();
        HorizontalLayout controlLayout = CommonLogic.getResponsiveControls(controls);
        resultTableLayout.addComponent(controlLayout);
    }

    private void resetSearchField() {
        try {
            itemID.setValue(StringUtils.EMPTY);
            itemName.setValue(StringUtils.EMPTY);
            itemNo.setValue(StringUtils.EMPTY);
            itemDesc.setValue(StringUtils.EMPTY);
            identifier.setValue(StringUtils.EMPTY);
            ndc9.setValue(StringUtils.EMPTY);
            therapeuticClass_DTO.setValue(null);
            form_DTO.setValue(null);
            identifierType_DTO.setValue(null);
            brand_DTO.setValue(null);
            strength_DTO.setValue(null);
            itemCategory_DTO.setValue(null);
            itemType_DTO.setValue(null);
            company_DTO.setValue(null);
            placeHolder_DTO.setValue(null);
            binder.commit();
        } catch (FieldGroup.CommitException ex) {
            LOGGER.error("",ex);
        }
    }

    public void getItemIdsFromTrnasferContainer() {
        for (Object obj : transferContainer.getItemIds()) {
            selection.getTransterTabItemIds().add(((ItemIndexDto) obj).getSystemId());
        }

    }

    public boolean checkTransferedItems() {
        if (transferContainer != null && transferContainer.size() > 0) {
            return true;
        }
        return false;
    }

    public List<String> mappingItems() {
            return Collections.unmodifiableList(sidList);
    }

    private void setFromItemSid() {
        if (!fromLsSidList.isEmpty()) {
            fromLsSidList.clear();
        }
        for (Object object : itemViewContainer.getItemIds()) {
            ItemIndexDto itemDto = (ItemIndexDto) object;
            fromLsSidList.add(itemDto.getSystemId());
        }
    }

    private void setTransferredIdCount() {
        if (!tranferredCount.isEmpty()) {
            tranferredCount.clear();
        }
        for (Object object : transferContainer.getItemIds()) {
            ItemIndexDto itemDto = (ItemIndexDto) object;
            tranferredCount.add(itemDto.getFromSid());
        }
    }

    public List<String> getTransferredCount() {
        return Collections.unmodifiableList(tranferredCount);
    }

    public List<String> getFromIdCount() {
        return Collections.unmodifiableList(fromLsSidList);
    }
}
