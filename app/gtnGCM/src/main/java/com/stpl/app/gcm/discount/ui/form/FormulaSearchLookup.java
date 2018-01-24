/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.discount.ui.form;

import com.stpl.app.gcm.discount.dto.LookupDTO;
import com.stpl.app.gcm.discount.logic.FormulaLookupTableLogic;
import com.stpl.app.gcm.util.AbstractNotificationUtils;
import com.stpl.app.gcm.util.Constants;
import com.stpl.app.gcm.util.ResponsiveUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.v7.ui.TextField;
import com.vaadin.v7.ui.VerticalLayout;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.customwindow.CustomWindow;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author santanukumar
 */
public class FormulaSearchLookup extends CustomWindow {

    @UiField("formulaTableLayout")
    public VerticalLayout formulaTableLayout;
    @UiField("formulaId")
    public TextField formulaId;
    @UiField("formulaNo")
    public TextField formulaNo;
    @UiField("formulaName")
    public TextField formulaName;
    @UiField("searchBtn")
    public Button searchBtn;
    @UiField("resetBtn")
    public Button resetBtn;
    @UiField("selectBtn")
    public Button selectBtn;
    @UiField("closeBtn")
    public Button closeBtn;
    private final FormulaLookupTableLogic tableLogic = new FormulaLookupTableLogic();
    private final ExtPagedTable resultsTable = new ExtPagedTable(tableLogic);
    private CustomTextField componentTextField;
    private final BeanItemContainer<LookupDTO> resultsContainer = new BeanItemContainer<>(LookupDTO.class);
     private static final Logger LOGGER = LoggerFactory.getLogger(FormulaSearchLookup.class);
    public FormulaSearchLookup(CustomTextField componentTextField) {
        super("Formula Id Lookup");
        setContent(Clara.create(getClass().getResourceAsStream("/discount/formulaIdpopup.xml"), this));
        addStyleName("valo-theme-customwindow");
        addStyleName("bootstrap-ui");
        addStyleName(Constants.BOOTSTRAP);
        addStyleName(Constants.BOOTSTRAP_FORECAST_BOOTSTRAP_NM);
        this.componentTextField=componentTextField;
        setClosable(true);
        setModal(true);
        configureFields();
    }

    public void configureFields() {
        formulaTableLayout.addComponent(resultsTable);
        tableLogic.setPageLength(NumericConstants.FIVE);
        tableLogic.setContainerDataSource(resultsContainer);
        tableLogic.sinkItemPerPageWithPageLength(false);
        resultsTable.setFilterBarVisible(true);
        resultsTable.setSelectable(true);
        resultsTable.setVisibleColumns(Constants.getInstance().formulaSearchColumns);
        resultsTable.setColumnHeaders(Constants.getInstance().formulaSearchHeaders);
        resultsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        HorizontalLayout controls = ResponsiveUtils.getResponsiveControls(tableLogic.createControls());
        formulaTableLayout.addComponent(controls);
    }
    @UiHandler("closeBtn")
    public void closeBtnClickLogic(Button.ClickEvent event) {        
        close();
    }
     @UiHandler("selectBtn")
    public void selectBtnClickLogic(Button.ClickEvent event) {
        if (resultsTable.getValue() != null) {
            LookupDTO componentLookUp = (LookupDTO) resultsTable.getValue();
            componentTextField.setValue(componentLookUp.getFormulaName());
            componentTextField.setData(componentLookUp.getFormulaSysId());
            close();
         }else{
            MessageBox.showPlain(Icon.INFO, "Select error", "Please select a record. ", ButtonId.OK);
        }
    }
    @UiHandler("searchBtn")
    public void searchButtonLogic(Button.ClickEvent event) {
       LookupDTO searchDto=new LookupDTO();
       searchDto.setFormulaId(formulaId.getValue());
       searchDto.setFormulaName(formulaName.getValue());
       searchDto.setFormulaNo(formulaNo.getValue());
        if (!tableLogic.loadSetData(searchDto)) {
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
                     formulaId.setValue(StringUtils.EMPTY);
                     formulaNo.setValue(StringUtils.EMPTY);
                    formulaName.setValue(StringUtils.EMPTY);
                } catch (Exception ex) {
                     LOGGER.error("",ex);
                }
            }

            @Override
            public void noMethod() {
                return;
            }
        }.getConfirmationMessage("Confirmation", "Are you sure you want to reset the values in the Formula Search?");
    }

}
