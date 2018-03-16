/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.transfercontract.form;

import org.asi.container.ExtTreeContainer;
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
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.constants.BooleanConstant;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.util.BeanItem;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.v7.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.v7.ui.TextField;
import com.vaadin.v7.ui.Tree;
import com.vaadin.v7.ui.TreeTable;
import com.vaadin.v7.ui.VerticalLayout;
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
    private final ExtTreeContainer<TransferFromDTO> fromResultContainer = new ExtTreeContainer<>(TransferFromDTO.class);
    private final ExtTreeContainer<TransferToDTO> toResultContainer = new ExtTreeContainer<>(TransferToDTO.class);
    private final BeanItemContainer<ComponentDetailsDTO> fromCDResultContainer = new BeanItemContainer<>(ComponentDetailsDTO.class);
    private final BeanItemContainer<ComponentDetailsDTO> toCDResultContainer = new BeanItemContainer<>(ComponentDetailsDTO.class);
    private final Map<String, Set<String>> resultList;
    private final TransferSelectionLogic logic = new TransferSelectionLogic();
    private final TransferContractWindow window;
    private String sComponent;
    private String sField;
    private String sValue;
    private final DetailsDTO detailsDTO=new DetailsDTO();
    private ErrorfulFieldGroup binder = new ErrorfulFieldGroup(new BeanItem<>(detailsDTO));
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
        tabSheet.markAsDirty();
        tabSheet.markAsDirtyRecursive();
        tabSheet.addTab(Clara.create(getClass().getResourceAsStream("/TransferContract/transferSelection.xml"), this), "Transfer Selection", null, 0);
        tabSheet.addTab(new CrossReferenceForm(), "Cross Reference", null, 1);
        tabSheet.addTab(new TransferDetailsForm(), "Transfer Details", null, NumericConstants.TWO);
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
        searchField.setNullSelectionItemId(Constants.SELECT_ONE);
        searchField.addItem(Constants.SELECT_ONE);
        searchField.select(Constants.SELECT_ONE);
        
        fromResultTable.setImmediate(BooleanConstant.getTrueFlag());
        fromResultTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        fromResultTable.setWidth(NumericConstants.SEVEN_SEVEN_FIVE, Unit.PIXELS);
        fromResultTable.setHeight(NumericConstants.FOUR_HUNDRED, Unit.PIXELS);
        fromResultTable.setSelectable(BooleanConstant.getTrueFlag());
        fromResultTable.setPageLength(NumericConstants.FIVE);

        toResultTable.setImmediate(BooleanConstant.getTrueFlag());
        toResultTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        toResultTable.setWidth(NumericConstants.NINE_SEVEN_FIVE, Unit.PIXELS);
        toResultTable.setHeight(NumericConstants.FOUR_HUNDRED, Unit.PIXELS);
        toResultTable.setSelectable(BooleanConstant.getTrueFlag());
        toResultTable.setPageLength(NumericConstants.FIVE);
        toResultTable.setContainerDataSource(toResultContainer);
        toResultTable.setVisibleColumns(HeaderUtil.getInstance().transforToColumn);
        toResultTable.setColumnHeaders(HeaderUtil.getInstance().transforToHeader);

        fromCDResultTable.setImmediate(BooleanConstant.getTrueFlag());
        fromCDResultTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        fromCDResultTable.setWidth(NumericConstants.SEVEN_SEVEN_FIVE, Unit.PIXELS);
        fromCDResultTable.setHeight(NumericConstants.FOUR_HUNDRED, Unit.PIXELS);
        fromCDResultTable.setPageLength(NumericConstants.FIVE);
        fromCDResultTable.setContainerDataSource(fromCDResultContainer);
        fromCDResultTable.setVisibleColumns(HeaderUtil.getInstance().componentDetailsItemColumn);
        fromCDResultTable.setColumnHeaders(HeaderUtil.getInstance().componentDetailsItemHeader);

        toCDResultTable.setImmediate(BooleanConstant.getTrueFlag());
        toCDResultTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        toCDResultTable.setWidth(NumericConstants.NINE_SEVEN_FIVE, Unit.PIXELS);
        toCDResultTable.setHeight(NumericConstants.FOUR_HUNDRED, Unit.PIXELS);
        toCDResultTable.setPageLength(NumericConstants.FIVE);
        toCDResultTable.setContainerDataSource(toCDResultContainer);
        toCDResultTable.setVisibleColumns(HeaderUtil.getInstance().componentDetailsItemColumn);
        toCDResultTable.setColumnHeaders(HeaderUtil.getInstance().componentDetailsItemHeader);
        loadFormResultTable();
    }

    private void loadFormResultTable() {
        fromResultTable.setContainerDataSource(fromResultContainer);
        fromResultTable.setVisibleColumns(HeaderUtil.getInstance().transforFromColumn);
        fromResultTable.setColumnHeaders(HeaderUtil.getInstance().transforFromHeader);
        for (Object temp : logic.getTransferFromDetails(null, resultList)) {
            fromResultContainer.addItem(temp);
            fromResultContainer.setChildrenAllowed(temp, BooleanConstant.getTrueFlag());
            fromResultTable.setCollapsed(temp, BooleanConstant.getFalseFlag());
        }
    }

    private void toFormResultTable() {
        toResultContainer.removeAllItems();
        toResultTable.setContainerDataSource(toResultContainer);
        toResultTable.setVisibleColumns(HeaderUtil.getInstance().transforToColumn);
        toResultTable.setColumnHeaders(HeaderUtil.getInstance().transforToHeader);
        for (Object temp : logic.getTransferToDetails(null, sComponent, sField, sValue)) {
            toResultContainer.addItem(temp);
            toResultContainer.setChildrenAllowed(temp, BooleanConstant.getTrueFlag());
            toResultTable.setCollapsed(temp, BooleanConstant.getFalseFlag());
        }
    }

    private void loadCfpFromCD(final TransferFromDTO parent) {
        fromCDResultTable.setContainerDataSource(new BeanItemContainer<>(CFPComponentDetailsDTO.class));
        fromCDResultTable.addItems(logic.getFromCfpCD(parent));
        fromCDResultTable.setVisibleColumns(HeaderUtil.getInstance().componentDetailsCompanyColumn);
        fromCDResultTable.setColumnHeaders(HeaderUtil.getInstance().componentDetailsCompanyHeader);
    }

    private void loadIfpFromCD(final TransferFromDTO parent) {
        fromCDResultTable.setContainerDataSource(new BeanItemContainer<>(ComponentDetailsDTO.class));
        fromCDResultTable.addItems(logic.getFromIfpCD(parent));
        fromCDResultTable.setVisibleColumns(HeaderUtil.getInstance().componentDetailsItemColumn);
        fromCDResultTable.setColumnHeaders(HeaderUtil.getInstance().componentDetailsItemHeader);
    }

    private void loadPsFromCD(final TransferFromDTO parent) {
        fromCDResultTable.setContainerDataSource(new BeanItemContainer<>(PSComponentDetailsDTO.class));
        fromCDResultTable.addItems(logic.getFromPsCD(parent));
        fromCDResultTable.setVisibleColumns(HeaderUtil.getInstance().componentDetailsPsColumn);
        fromCDResultTable.setColumnHeaders(HeaderUtil.getInstance().componentDetailsPsHeader);
    }

    private void loadRsFromCD(final TransferFromDTO parent) {
        fromCDResultTable.setContainerDataSource(new BeanItemContainer<>(RSComponentDetailsDTO.class));
        fromCDResultTable.addItems(logic.getFromRsCD(parent));
        fromCDResultTable.setVisibleColumns(HeaderUtil.getInstance().componentDetailsRsColumn);
        fromCDResultTable.setColumnHeaders(HeaderUtil.getInstance().componentDetailsRsHeader);
    }

    private void loadCfpToCD(final TransferToDTO parent) {
        toCDResultTable.setContainerDataSource(new BeanItemContainer<>(CFPComponentDetailsDTO.class));
        toCDResultTable.addItems(logic.getFromCfpCD(parent));
        toCDResultTable.setVisibleColumns(HeaderUtil.getInstance().componentDetailsCompanyColumn);
        toCDResultTable.setColumnHeaders(HeaderUtil.getInstance().componentDetailsCompanyHeader);
    }

    private void loadIfpToCD(final TransferToDTO parent) {
        toCDResultTable.setContainerDataSource(new BeanItemContainer<>(ComponentDetailsDTO.class));
        toCDResultTable.addItems(logic.getFromIfpCD(parent));
        toCDResultTable.setVisibleColumns(HeaderUtil.getInstance().componentDetailsItemColumn);
        toCDResultTable.setColumnHeaders(HeaderUtil.getInstance().componentDetailsItemHeader);
    }

    private void loadPsToCD(final TransferToDTO parent) {
        toCDResultTable.setContainerDataSource(new BeanItemContainer<>(PSComponentDetailsDTO.class));
        toCDResultTable.addItems(logic.getFromPsCD(parent));
        toCDResultTable.setVisibleColumns(HeaderUtil.getInstance().componentDetailsPsColumn);
        toCDResultTable.setColumnHeaders(HeaderUtil.getInstance().componentDetailsPsHeader);
    }

    private void loadRsToCD(final TransferToDTO parent) {
        toCDResultTable.setContainerDataSource(new BeanItemContainer<>(RSComponentDetailsDTO.class));
        toCDResultTable.addItems(logic.getFromRsCD(parent));
        toCDResultTable.setVisibleColumns(HeaderUtil.getInstance().componentDetailsRsColumn);
        toCDResultTable.setColumnHeaders(HeaderUtil.getInstance().componentDetailsRsHeader);
    }

    @UiHandler("fromResultTable")
    public void fromTableExpandLogic(Tree.ExpandEvent event) {
        for (TransferFromDTO temp : logic.getTransferFromDetails((TransferFromDTO) (event.getItemId()), resultList)) {
            fromResultContainer.addItem(temp);
            fromResultContainer.setParent(temp, event.getItemId());
            if (temp.getLevel() < NumericConstants.FOUR) {
                fromResultContainer.setChildrenAllowed(temp, BooleanConstant.getTrueFlag());
                fromResultTable.setCollapsed(temp, BooleanConstant.getFalseFlag());
            } else {
                fromResultContainer.setChildrenAllowed(temp, BooleanConstant.getFalseFlag());
            }
        }
    }

    @UiHandler("fromResultTable")
    public void fromTableCollapseLogic(Tree.CollapseEvent event) {
        Object temp = event.getItemId();
        Object parent = fromResultContainer.getParent(temp);
        fromResultContainer.removeItemRecursively(temp);
        fromResultContainer.addItem(temp);
        fromResultContainer.setChildrenAllowed(temp, BooleanConstant.getTrueFlag());
        fromResultContainer.setParent(temp, parent);
    }

    @UiHandler("toResultTable")
    public void toTableExpandLogic(Tree.ExpandEvent event) {
        for (TransferToDTO temp : logic.getTransferToDetails((TransferToDTO) (event.getItemId()), sComponent, sField, sValue)) {
            toResultContainer.addItem(temp);
            toResultContainer.setParent(temp, event.getItemId());
            if (temp.getLevel() < NumericConstants.FOUR) {
                toResultContainer.setChildrenAllowed(temp, BooleanConstant.getTrueFlag());
                toResultTable.setCollapsed(temp, BooleanConstant.getFalseFlag());
            } else {
                toResultContainer.setChildrenAllowed(temp, BooleanConstant.getFalseFlag());
            }
        }
    }

    @UiHandler("toResultTable")
    public void toTableCollapseLogic(Tree.CollapseEvent event) {
        Object temp = event.getItemId();
        Object parent = toResultContainer.getParent(temp);
        toResultContainer.removeItemRecursively(temp);
        toResultContainer.addItem(temp);
        toResultContainer.setChildrenAllowed(temp, BooleanConstant.getTrueFlag());
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
            searchField.addItem(Constants.SELECT_ONE);
            searchField.addItems(Arrays.asList(Constant.getInstance().contractSearch));
        } else if (Constant.CFP_CATEGORY.equals(componentType.getValue())) {
            searchField.removeAllItems();
            searchField.addItem(Constants.SELECT_ONE);
            searchField.addItems(Arrays.asList(Constant.getInstance().cfpSearch));
        } else if (Constant.IFP_CATEGORY.equals(componentType.getValue())) {
            searchField.removeAllItems();
            searchField.addItem(Constants.SELECT_ONE);
            searchField.addItems(Arrays.asList(Constant.getIFPSEARCH()));
        } else if (Constant.PS_CATEGORY.equals(componentType.getValue())) {
            searchField.removeAllItems();
            searchField.addItem(Constants.SELECT_ONE);
            searchField.addItems(Arrays.asList(Constant.getPSSEARCH()));
        } else if (Constant.RS_CATEGORY.equals(componentType.getValue())) {
            searchField.removeAllItems(); 
            searchField.addItem(Constants.SELECT_ONE);
            searchField.addItems(Arrays.asList(Constant.getRSSEARCH()));
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
        binder.setItemDataSource(new BeanItem<>(new DetailsDTO()));
    }
       private ErrorfulFieldGroup getBinder() {
        binder.bindMemberFields(this);
        binder.setItemDataSource(new BeanItem<>(detailsDTO));
        binder.setBuffered(true);
        return binder;
    }
}
