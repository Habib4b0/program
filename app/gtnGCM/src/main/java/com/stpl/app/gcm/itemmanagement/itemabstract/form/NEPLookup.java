/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.itemmanagement.itemabstract.form;

import com.stpl.app.gcm.common.CommonUtil;
import com.stpl.app.gcm.globalchange.dto.SelectionDTO;
import com.stpl.app.gcm.itemmanagement.itemabstract.dto.FormulaDTO;
import com.stpl.app.gcm.itemmanagement.itemabstract.logic.NEPLookuptablelogic;
import com.stpl.app.gcm.util.AbstractNotificationUtils;
import com.stpl.app.gcm.util.Constants;
import com.stpl.app.gtnworkflow.util.CommonUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author rohitvignesh.s
 */
public class NEPLookup extends Window {

    NEPLookuptablelogic tablelogic = new NEPLookuptablelogic();
    FormulaDTO nepDto;
    private final ExtPagedTable resultTable = new ExtPagedTable(tablelogic);
    public BeanItemContainer<FormulaDTO> forumulaLookupBean = new BeanItemContainer<>(FormulaDTO.class);
    @UiField("formulaId")
    public TextField formulaId;
    @UiField("formulaNo")
    public TextField formulaNo;
    @UiField("formulaName")
    public TextField formulaName;
    @UiField("netSalesFormulaType")
    public ComboBox netSalesFormulaType;
    @UiField("searchBtn")
    public Button searchBtn;
    @UiField("resetBtn")
    public Button resetBtn;
    @UiField("selectBtn")
    public Button selectBtn;
    @UiField("closeBtn")
    public Button closeBtn;
    @UiField("tableLayout")
    private VerticalLayout tableLayout;
    @UiField("mainPanel")
    private Panel mainPanel;
    @UiField("mainLayout")
    private VerticalLayout mainLayout;
    @UiField("panelid")
    private Panel panelid;
    @UiField("controlLayout")
    private HorizontalLayout controlLayout;
    private String lookupName = StringUtils.EMPTY;
    @UiField("vertiLayout")
    private VerticalLayout vertiLayout;
    public CustomTextField componentTextField;
    SelectionDTO selection = new SelectionDTO();

    public final Object[] formulaSearchColumns = new Object[]{
        "netSalesformulaType", "formulaId", "formulaNo", "formulaName"};
    public final String[] formulaSearchHeaders = new String[]{
        "Net Sales Formula Type", "Net Sales Formula ID", "Net Sales Formula NO", "Net Sales Formula Name"};

    public NEPLookup(CustomTextField componentTextField, String lookupName) {
        this.lookupName = lookupName;
        this.componentTextField = componentTextField;
        init();
    }

    private void init() {
        addToContent(lookupName);
        configureTable();
    }

    private void addToContent(String lookupName) {
        setContent(Clara.create(getClass().getResourceAsStream("/discount/nepFormula.xml"), this));
        addStyleName("bootstrap");
        addStyleName("bootstrap-bb");
        center();
        setClosable(true);
        setModal(true);
        setHeight(NumericConstants.FLOAT_SIXTY_FIVE, Unit.PERCENTAGE);
        setWidth(NumericConstants.FLOAT_EIGHTY, Unit.PERCENTAGE);
        setCaption(lookupName);
        mainPanel.setSizeFull();
        mainLayout.setWidth(Constants.HUNDRED_PERCENT);
        panelid.setWidth(Constants.HUNDRED_PERCENT);
        vertiLayout.setWidth(Constants.HUNDRED_PERCENT);
        tableLayout.setWidth(Constants.HUNDRED_PERCENT);
        selectBtn.setEnabled(false);

    }

    private void configureTable() {
        resultTable.setSizeFull();
        List<Integer> pageLength = new ArrayList<>();
        pageLength.add(NumericConstants.TEN);
        pageLength.add(NumericConstants.FIFTEEN);
        pageLength.add(NumericConstants.TWENTY);
        pageLength.add(NumericConstants.TWENTY_FIVE);
        pageLength.add(NumericConstants.FIFTY);
        pageLength.add(NumericConstants.HUNDRED);
        tablelogic.getControlConfig().setPageLengthsAndCaptions(pageLength);
        tableLayout.addComponent(resultTable);
        CommonUtils.getResponsiveControls(tablelogic.createControls(), controlLayout);
        resultTable.setFilterDecorator(new ExtDemoFilterDecorator());
        resultTable.setFilterGenerator(new FormulaLookupTableGenerator());
        tablelogic.setContainerDataSource(forumulaLookupBean);
        resultTable.setVisibleColumns(formulaSearchColumns);
        resultTable.setColumnHeaders(formulaSearchHeaders);
        resultTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        resultTable.setPageLength(NumericConstants.TEN);
        tablelogic.sinkItemPerPageWithPageLength(false);
        resultTable.setComponentError(null);
        resultTable.setSelectable(true);
        resultTable.markAsDirty();
        resultTable.setImmediate(true);
        resultTable.setFilterBarVisible(true);
        resultTable.setValidationVisible(false);
        resultTable.addStyleName("filterbar");
        resultTable.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if (resultTable.getValue() != null) {
                    selectBtn.setEnabled(true);
                } else {
                    selectBtn.setEnabled(false);
                }
            }
        });
        CommonUtil.loadComboBoxForGCM(netSalesFormulaType, Constants.NS_FORMULA_TYPE_LISTNAME, false);
    }

    public void enter(ViewChangeListener.ViewChangeEvent event) {
        return;
    }

    @UiHandler("closeBtn")
    public void closeButton(Button.ClickEvent event) {
        getUI().setData(null);
        close();
    }

    @UiHandler("selectBtn")
    public void selectButton(Button.ClickEvent event) {
        if (resultTable.getValue() != null) {
            FormulaDTO componentLookUp = (FormulaDTO) resultTable.getValue();
            componentTextField.setReadOnly(false);
            componentTextField.setValue(componentLookUp.getFormulaName());
            componentTextField.setData(componentLookUp);
            componentTextField.setReadOnly(true);
            close();
        } else {
            MessageBox.showPlain(Icon.INFO, "Error", "Please select a record. ", ButtonId.OK);
        }
    }

    @UiHandler("searchBtn")
    public void searchButtonLogic(Button.ClickEvent event) {
        FormulaDTO searchDto = new FormulaDTO();
        searchDto.setFormulaId(formulaId.getValue());
        searchDto.setFormulaName(formulaName.getValue());
        searchDto.setFormulaNo(formulaNo.getValue());
        searchDto.setNetSalesformulaType((HelperDTO) netSalesFormulaType.getValue());
        tablelogic.configureSearchData(searchDto);
        tablelogic.setCurrentPage(1);
        if (tablelogic.isResultsEmpty()) {
            AbstractNotificationUtils.getErrorNotification("No Matching Records",
                    "There were no records matching the search criteria.  Please try again.");
        } else {
            CommonUtils.successNotification("Search Completed");
        }

        
        
    }

    @UiHandler("resetBtn")
    public void resetButtonLogic(Button.ClickEvent event) {
        new AbstractNotificationUtils() {
            @Override
            public void yesMethod() {
                resultTable.resetFilters();
                formulaId.setValue(StringUtils.EMPTY);
                formulaNo.setValue(StringUtils.EMPTY);
                formulaName.setValue(StringUtils.EMPTY);
                netSalesFormulaType.setValue(null);
            }

            @Override
            public void noMethod() {
                return;
            }
        }.getConfirmationMessage("Confirmation", "Are you sure you want to reset the values in the Formula Search?");
    }
    

}
