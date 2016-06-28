/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.transfercontract.form;

import com.stpl.app.customtreecontainer.CustomTreeContainer;
import com.stpl.app.gcm.transfercontract.dto.CFPComponentDetailsDTO;
import com.stpl.app.gcm.transfercontract.dto.ComponentDetailsDTO;
import com.stpl.app.gcm.transfercontract.dto.DetailsDTO;
import com.stpl.app.gcm.transfercontract.dto.PSComponentDetailsDTO;
import com.stpl.app.gcm.transfercontract.dto.RSComponentDetailsDTO;
import com.stpl.app.gcm.transfercontract.dto.TransferFromDTO;
import com.stpl.app.gcm.transfercontract.dto.TransferToDTO;
import com.stpl.app.gcm.transfercontract.logic.TransferSelectionLogic;
import com.stpl.app.gcm.transfercontract.util.Constant;
import com.stpl.app.gcm.transfercontract.util.HeaderUtil;
import com.stpl.app.gcm.util.AbstractNotificationUtils;
import com.stpl.app.gcm.util.Constants;
import com.stpl.app.util.ConstantUtil;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Tree;
import com.vaadin.ui.TreeTable;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author Harlin
 */
public class TransferSelectionForm extends VerticalLayout {

    @UiField("fromResultTable")
    public TreeTable fromResultTable;

    @UiField("toResultTable")
    public TreeTable toResultTable;

    @UiField("fromCDResultTable")
    public ExtFilterTable fromCDResultTable;

    @UiField("toCDResultTable")
    public ExtFilterTable toCDResultTable;

    @UiField("componentType")
    public ComboBox componentType;

    @UiField("searchField")
    public ComboBox searchField;

    @UiField("value")
    public TextField value;

    @UiField("fromCDLabelNo")
    public Label fromCDLabelNo;

    @UiField("fromCDNo")
    public TextField fromCDNo;

    @UiField("fromCDLabelName")
    public Label fromCDLabelName;

    @UiField("fromCDName")
    public TextField fromCDName;

    @UiField("toCDLabelNo")
    public Label toCDLabelNo;

    @UiField("toCDNo")
    public TextField toCDNo;

    @UiField("toCDLabelName")
    public Label toCDLabelName;

    @UiField("toCDName")
    public TextField toCDName;

    @UiField("searchBtn")
    public Button searchBtn;

    @UiField("resetBtn")
    public Button resetBtn;

    @UiField("fromPopulateBtn")
    public Button fromPopulateBtn;

    @UiField("fromtransferBtn")
    public Button fromtransferBtn;

    @UiField("toPopulateBtn")
    public Button toPopulateBtn;

    @UiField("toRemoveBtn")
    public Button toRemoveBtn;

    @UiField("toResetBtn")
    public Button toResetBtn;

    @UiField("close")
    public Button close;

    @UiField("next")
    public Button next;

    private final TabSheet tabSheet = new TabSheet();
    private final CustomTreeContainer<TransferFromDTO> fromResultContainer = new CustomTreeContainer<TransferFromDTO>(TransferFromDTO.class);
    private final CustomTreeContainer<TransferToDTO> toResultContainer = new CustomTreeContainer<TransferToDTO>(TransferToDTO.class);
    private final BeanItemContainer<ComponentDetailsDTO> fromCDResultContainer = new BeanItemContainer<ComponentDetailsDTO>(ComponentDetailsDTO.class);
    private final BeanItemContainer<ComponentDetailsDTO> toCDResultContainer = new BeanItemContainer<ComponentDetailsDTO>(ComponentDetailsDTO.class);
    final Map<String, Set<String>> resultList;
    private final TransferSelectionLogic logic = new TransferSelectionLogic();
    final TransferContractWindow window;
    private String sComponent;
    private String sField;
    private String sValue;
    DetailsDTO detailsDTO=new DetailsDTO();
    private CustomFieldGroup binder = new CustomFieldGroup(new BeanItem<DetailsDTO>(detailsDTO));
    public TransferSelectionForm(final Map<String, Set<String>> resultList, final TransferContractWindow window) {
        this.resultList = resultList;
        addTab();
        addComponent(tabSheet);
        configureTables();
        binder=getBinder();
        this.window = window;
    }

    /**
     * Adds the tab.
     *
     * @return the component
     */
    private void addTab() {
        tabSheet.addStyleName(ValoTheme.TABSHEET_FRAMED);
        tabSheet.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
        tabSheet.setImmediate(Boolean.TRUE);
        tabSheet.markAsDirty();
        tabSheet.markAsDirtyRecursive();
        tabSheet.addTab(Clara.create(getClass().getResourceAsStream("/TransferContract/transferSelection.xml"), this), "Transfer Selection", null, 0);
        tabSheet.addTab(new CrossReferenceForm(), "Cross Reference", null, 1);
        tabSheet.addTab(new TransferDetailsForm(), "Transfer Details", null, 2);
    }

    private void configureTables() {
        componentType.setNullSelectionAllowed(true);
        componentType.setNullSelectionItemId(Constants.SELECT_ONE);
        componentType.select(Constants.SELECT_ONE);
        componentType.addItem(Constants.SELECT_ONE);
        componentType.addItem(Constant.CONTRACT_CATEGORY);
        componentType.addItem(Constant.CFP_CATEGORY);
        componentType.addItem(Constant.IFP_CATEGORY);
        componentType.addItem(Constant.PS_CATEGORY);
        componentType.addItem(Constant.RS_CATEGORY);
        
        searchField.setNullSelectionAllowed(true);
        searchField.setNullSelectionItemId(ConstantUtil.SELECT_ONE);
        searchField.addItem(ConstantUtil.SELECT_ONE);
        searchField.select(ConstantUtil.SELECT_ONE);
        
        fromResultTable.setImmediate(Boolean.TRUE);
        fromResultTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        fromResultTable.setWidth(775, Unit.PIXELS);
        fromResultTable.setHeight(400, Unit.PIXELS);
        fromResultTable.setSelectable(Boolean.TRUE);
        fromResultTable.setPageLength(5);

        toResultTable.setImmediate(Boolean.TRUE);
        toResultTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        toResultTable.setWidth(975, Unit.PIXELS);
        toResultTable.setHeight(400, Unit.PIXELS);
        toResultTable.setSelectable(Boolean.TRUE);
        toResultTable.setPageLength(5);
        toResultTable.setContainerDataSource(toResultContainer);
        toResultTable.setVisibleColumns(HeaderUtil.TRANSFOR_TO_COLUMN);
        toResultTable.setColumnHeaders(HeaderUtil.TRANSFOR_TO_HEADER);

        fromCDResultTable.setImmediate(Boolean.TRUE);
        fromCDResultTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        fromCDResultTable.setWidth(775, Unit.PIXELS);
        fromCDResultTable.setHeight(400, Unit.PIXELS);
        fromCDResultTable.setPageLength(5);
        fromCDResultTable.setContainerDataSource(fromCDResultContainer);
        fromCDResultTable.setVisibleColumns(HeaderUtil.COMPONENT_DETAILS_ITEM_COLUMN);
        fromCDResultTable.setColumnHeaders(HeaderUtil.COMPONENT_DETAILS_ITEM_HEADER);

        toCDResultTable.setImmediate(Boolean.TRUE);
        toCDResultTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        toCDResultTable.setWidth(975, Unit.PIXELS);
        toCDResultTable.setHeight(400, Unit.PIXELS);
        toCDResultTable.setPageLength(5);
        toCDResultTable.setContainerDataSource(toCDResultContainer);
        toCDResultTable.setVisibleColumns(HeaderUtil.COMPONENT_DETAILS_ITEM_COLUMN);
        toCDResultTable.setColumnHeaders(HeaderUtil.COMPONENT_DETAILS_ITEM_HEADER);
        loadFormResultTable();
    }

    private void loadFormResultTable() {
        fromResultTable.setContainerDataSource(fromResultContainer);
        fromResultTable.setVisibleColumns(HeaderUtil.TRANSFOR_FROM_COLUMN);
        fromResultTable.setColumnHeaders(HeaderUtil.TRANSFOR_FROM_HEADER);
        for (Object temp : logic.getTransferFromDetails(null, resultList)) {
            fromResultContainer.addItem(temp);
            fromResultContainer.setChildrenAllowed(temp, Boolean.TRUE);
            fromResultTable.setCollapsed(temp, Boolean.FALSE);
        }
    }

    private void toFormResultTable() {
        toResultContainer.removeAllItems();
        toResultTable.setContainerDataSource(toResultContainer);
        toResultTable.setVisibleColumns(HeaderUtil.TRANSFOR_TO_COLUMN);
        toResultTable.setColumnHeaders(HeaderUtil.TRANSFOR_TO_HEADER);
        for (Object temp : logic.getTransferToDetails(null, sComponent, sField, sValue)) {
            toResultContainer.addItem(temp);
            toResultContainer.setChildrenAllowed(temp, Boolean.TRUE);
            toResultTable.setCollapsed(temp, Boolean.FALSE);
        }
    }

    private void loadCfpFromCD(final TransferFromDTO parent) {
        fromCDResultTable.setContainerDataSource(new BeanItemContainer<CFPComponentDetailsDTO>(CFPComponentDetailsDTO.class));
        fromCDResultTable.addItems(logic.getFromCfpCD(parent));
        fromCDResultTable.setVisibleColumns(HeaderUtil.COMPONENT_DETAILS_COMPANY_COLUMN);
        fromCDResultTable.setColumnHeaders(HeaderUtil.COMPONENT_DETAILS_COMPANY_HEADER);
    }

    private void loadIfpFromCD(final TransferFromDTO parent) {
        fromCDResultTable.setContainerDataSource(new BeanItemContainer<ComponentDetailsDTO>(ComponentDetailsDTO.class));
        fromCDResultTable.addItems(logic.getFromIfpCD(parent));
        fromCDResultTable.setVisibleColumns(HeaderUtil.COMPONENT_DETAILS_ITEM_COLUMN);
        fromCDResultTable.setColumnHeaders(HeaderUtil.COMPONENT_DETAILS_ITEM_HEADER);
    }

    private void loadPsFromCD(final TransferFromDTO parent) {
        fromCDResultTable.setContainerDataSource(new BeanItemContainer<PSComponentDetailsDTO>(PSComponentDetailsDTO.class));
        fromCDResultTable.addItems(logic.getFromPsCD(parent));
        fromCDResultTable.setVisibleColumns(HeaderUtil.COMPONENT_DETAILS_PS_COLUMN);
        fromCDResultTable.setColumnHeaders(HeaderUtil.COMPONENT_DETAILS_PS_HEADER);
    }

    private void loadRsFromCD(final TransferFromDTO parent) {
        fromCDResultTable.setContainerDataSource(new BeanItemContainer<RSComponentDetailsDTO>(RSComponentDetailsDTO.class));
        fromCDResultTable.addItems(logic.getFromRsCD(parent));
        fromCDResultTable.setVisibleColumns(HeaderUtil.COMPONENT_DETAILS_RS_COLUMN);
        fromCDResultTable.setColumnHeaders(HeaderUtil.COMPONENT_DETAILS_RS_HEADER);
    }

    private void loadCfpToCD(final TransferToDTO parent) {
        toCDResultTable.setContainerDataSource(new BeanItemContainer<CFPComponentDetailsDTO>(CFPComponentDetailsDTO.class));
        toCDResultTable.addItems(logic.getFromCfpCD(parent));
        toCDResultTable.setVisibleColumns(HeaderUtil.COMPONENT_DETAILS_COMPANY_COLUMN);
        toCDResultTable.setColumnHeaders(HeaderUtil.COMPONENT_DETAILS_COMPANY_HEADER);
    }

    private void loadIfpToCD(final TransferToDTO parent) {
        toCDResultTable.setContainerDataSource(new BeanItemContainer<ComponentDetailsDTO>(ComponentDetailsDTO.class));
        toCDResultTable.addItems(logic.getFromIfpCD(parent));
        toCDResultTable.setVisibleColumns(HeaderUtil.COMPONENT_DETAILS_ITEM_COLUMN);
        toCDResultTable.setColumnHeaders(HeaderUtil.COMPONENT_DETAILS_ITEM_HEADER);
    }

    private void loadPsToCD(final TransferToDTO parent) {
        toCDResultTable.setContainerDataSource(new BeanItemContainer<PSComponentDetailsDTO>(PSComponentDetailsDTO.class));
        toCDResultTable.addItems(logic.getFromPsCD(parent));
        toCDResultTable.setVisibleColumns(HeaderUtil.COMPONENT_DETAILS_PS_COLUMN);
        toCDResultTable.setColumnHeaders(HeaderUtil.COMPONENT_DETAILS_PS_HEADER);
    }

    private void loadRsToCD(final TransferToDTO parent) {
        toCDResultTable.setContainerDataSource(new BeanItemContainer<RSComponentDetailsDTO>(RSComponentDetailsDTO.class));
        toCDResultTable.addItems(logic.getFromRsCD(parent));
        toCDResultTable.setVisibleColumns(HeaderUtil.COMPONENT_DETAILS_RS_COLUMN);
        toCDResultTable.setColumnHeaders(HeaderUtil.COMPONENT_DETAILS_RS_HEADER);
    }

    @UiHandler("fromResultTable")
    public void fromTableExpandLogic(Tree.ExpandEvent event) {
        for (TransferFromDTO temp : logic.getTransferFromDetails((TransferFromDTO) (event.getItemId()), resultList)) {
            fromResultContainer.addItem(temp);
            fromResultContainer.setParent(temp, event.getItemId());
            if (temp.getLevel() < 4) {
                fromResultContainer.setChildrenAllowed(temp, Boolean.TRUE);
                fromResultTable.setCollapsed(temp, Boolean.FALSE);
            } else {
                fromResultContainer.setChildrenAllowed(temp, Boolean.FALSE);
            }
        }
    }

    @UiHandler("fromResultTable")
    public void fromTableCollapseLogic(Tree.CollapseEvent event) {
        Object temp = event.getItemId();
        Object parent = fromResultContainer.getParent(temp);
        fromResultContainer.removeItemRecursively(temp);
        fromResultContainer.addItem(temp);
        fromResultContainer.setChildrenAllowed(temp, Boolean.TRUE);
        fromResultContainer.setParent(temp, parent);
    }

    @UiHandler("toResultTable")
    public void toTableExpandLogic(Tree.ExpandEvent event) {
        for (TransferToDTO temp : logic.getTransferToDetails((TransferToDTO) (event.getItemId()), sComponent, sField, sValue)) {
            toResultContainer.addItem(temp);
            toResultContainer.setParent(temp, event.getItemId());
            if (temp.getLevel() < 4) {
                toResultContainer.setChildrenAllowed(temp, Boolean.TRUE);
                toResultTable.setCollapsed(temp, Boolean.FALSE);
            } else {
                toResultContainer.setChildrenAllowed(temp, Boolean.FALSE);
            }
        }
    }

    @UiHandler("toResultTable")
    public void toTableCollapseLogic(Tree.CollapseEvent event) {
        Object temp = event.getItemId();
        Object parent = toResultContainer.getParent(temp);
        toResultContainer.removeItemRecursively(temp);
        toResultContainer.addItem(temp);
        toResultContainer.setChildrenAllowed(temp, Boolean.TRUE);
        toResultContainer.setParent(temp, parent);
    }

    @UiHandler("fromPopulateBtn")
    public void populateButtonClick(Button.ClickEvent event) {
        if (fromResultTable.getValue() != null) {
            TransferFromDTO temp = (TransferFromDTO) fromResultTable.getValue();
            fromCDLabelName.setValue(temp.getCategory() + " No: ");
            fromCDLabelNo.setValue(temp.getCategory() + " Name: ");
            fromCDNo.setValue(temp.getNumber());
            fromCDName.setValue(temp.getName());
            if (Constant.CFP_CATEGORY.equals(temp.getCategory())) {
                loadCfpFromCD(temp);
            } else if (Constant.IFP_CATEGORY.equals(temp.getCategory())) {
                loadIfpFromCD(temp);
            } else if (Constant.PS_CATEGORY.equals(temp.getCategory())) {
                loadPsFromCD(temp);
            } else if (Constant.RS_CATEGORY.equals(temp.getCategory())) {
                loadRsFromCD(temp);
            }
        }
    }

    @UiHandler("componentType")
    public void componentValueChange(Property.ValueChangeEvent event) {
        if (Constant.CONTRACT_CATEGORY.equals(componentType.getValue())) {
            searchField.removeAllItems();
            searchField.addItem(ConstantUtil.SELECT_ONE);
            searchField.addItems(Arrays.asList(Constant.CONTRACT_SEARCH));
        } else if (Constant.CFP_CATEGORY.equals(componentType.getValue())) {
            searchField.removeAllItems();
            searchField.addItem(ConstantUtil.SELECT_ONE);
            searchField.addItems(Arrays.asList(Constant.CFP_SEARCH));
        } else if (Constant.IFP_CATEGORY.equals(componentType.getValue())) {
            searchField.removeAllItems();
            searchField.addItem(ConstantUtil.SELECT_ONE);
            searchField.addItems(Arrays.asList(Constant.IFP_SEARCH));
        } else if (Constant.PS_CATEGORY.equals(componentType.getValue())) {
            searchField.removeAllItems();
            searchField.addItem(ConstantUtil.SELECT_ONE);
            searchField.addItems(Arrays.asList(Constant.PS_SEARCH));
        } else if (Constant.RS_CATEGORY.equals(componentType.getValue())) {
            searchField.removeAllItems(); 
            searchField.addItem(ConstantUtil.SELECT_ONE);
            searchField.addItems(Arrays.asList(Constant.RS_SEARCH));
        }
    }

    @UiHandler("searchBtn")
    public void searchButtonLogic(Button.ClickEvent event) {
        if (componentType.getValue() == null) {
            AbstractNotificationUtils.getErrorNotification(Constants.MessageConstants.SEARCHC_COMPONENT_TITLE.getConstant(), Constants.MessageConstants.SELECT_COMPONENT_BODY.getConstant());
        } else if (searchField.getValue() == null) {
            AbstractNotificationUtils.getErrorNotification(Constants.MessageConstants.SEARCHC_COMPONENT_TITLE.getConstant(), Constants.MessageConstants.SELECT_FIELD_BODY.getConstant());
        } else if (StringUtils.isBlank(value.getValue())) {
            AbstractNotificationUtils.getErrorNotification(Constants.MessageConstants.SEARCHC_COMPONENT_TITLE.getConstant(), Constants.MessageConstants.SELECT_VALUE_BODY.getConstant());
        } else {
            sComponent = componentType.getValue().toString();
            sField = searchField.getValue().toString();
            sValue = value.getValue();
            toFormResultTable();
        }

    }

    @UiHandler("close")
    public void closeButtonLogic(Button.ClickEvent event) {
        window.close();
    }

    @UiHandler("next")
    public void nextButtonLogic(Button.ClickEvent event) {
        tabSheet.setSelectedTab(1);
    }

    @UiHandler("toPopulateBtn")
    public void toPopulateButtonClick(Button.ClickEvent event) {
        if (toResultTable.getValue() != null) {
            TransferToDTO temp = (TransferToDTO) toResultTable.getValue();
            toCDLabelName.setValue(temp.getCategory() + " No: ");
            toCDLabelNo.setValue(temp.getCategory() + " Name: ");
            toCDNo.setValue(temp.getNumber());
            toCDName.setValue(temp.getName());
            if (Constant.CFP_CATEGORY.equals(temp.getCategory())) {
                loadCfpToCD(temp);
            } else if (Constant.IFP_CATEGORY.equals(temp.getCategory())) {
                loadIfpToCD(temp);
            } else if (Constant.PS_CATEGORY.equals(temp.getCategory())) {
                loadPsToCD(temp);
            } else if (Constant.RS_CATEGORY.equals(temp.getCategory())) {
                loadRsToCD(temp);
            }
        }
    }
     @UiHandler("resetBtn")
    public void resetLogic(Button.ClickEvent event) {
        binder.setItemDataSource(new BeanItem<DetailsDTO>(new DetailsDTO()));
    }
       private CustomFieldGroup getBinder() {
        binder.bindMemberFields(this);
        binder.setItemDataSource(new BeanItem<DetailsDTO>(detailsDTO));
        binder.setBuffered(true);
        return binder;
    }
}
