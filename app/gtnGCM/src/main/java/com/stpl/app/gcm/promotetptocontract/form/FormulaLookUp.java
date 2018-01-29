/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.promotetptocontract.form;

import com.stpl.app.gcm.globalchange.dto.SelectionDTO;
import com.stpl.app.gcm.itemmanagement.itemabstract.dto.FormulaDTO;
import com.stpl.app.gcm.itemmanagement.itemabstract.logic.abstracttablelogic.FormulaIdTableLogic;
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
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author alok.v
 */
public class FormulaLookUp extends CustomWindow {

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
    public Button selectButton;
    @UiField("closeBtn")
    public Button closeButton;
    private final FormulaIdTableLogic tableLogic = new FormulaIdTableLogic();
    private final ExtPagedTable resultsTable = new ExtPagedTable(tableLogic);
    private CustomTextField componentTextField;
    private final BeanItemContainer<FormulaDTO> resultsContainer = new BeanItemContainer<>(FormulaDTO.class);
    public final Object[] formulaSearchColumns = new Object[]{
        "formulaId", "formulaNo", "formulaName"};
    public final String[] formulaSearchHeaders = new String[]{
        "Formula Id", "Formula No", "Formula Name"};
    private final SelectionDTO selection = new SelectionDTO();

    public FormulaLookUp(CustomTextField componentTextField) {
        super("Formula ID lookup");
        setContent(Clara.create(getClass().getResourceAsStream("/discount/formulaIdpopup.xml"), this));
        addStyleName("valo-theme-customwindow");
        addStyleName("bootstrap-ui");
        addStyleName(Constants.BOOTSTRAP);
        addStyleName(Constants.BOOTSTRAP_FORECAST_BOOTSTRAP_NM);
        this.componentTextField = componentTextField;
        setClosable(true);
        setModal(true);
        configureFields();
    }

    public void configureFields() {
        formulaTableLayout.addComponent(resultsTable);
        resultsTable.setPageLength(NumericConstants.TEN);
        tableLogic.setContainerDataSource(resultsContainer);
        tableLogic.sinkItemPerPageWithPageLength(false);
        resultsTable.setVisibleColumns(formulaSearchColumns);
        resultsTable.setColumnHeaders(formulaSearchHeaders);
        resultsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        resultsTable.setSelectable(true);
        for (Object propertyId : resultsTable.getVisibleColumns()) {
            resultsTable.setColumnWidth(propertyId, NumericConstants.ONE_EIGHT_ZERO);
        }
        HorizontalLayout controls = ResponsiveUtils.getResponsiveControls(tableLogic.createControls());
        formulaTableLayout.addComponent(controls);
        for (Object propertyId : resultsTable.getVisibleColumns()) {
            resultsTable.setColumnWidth(propertyId, NumericConstants.ONE_EIGHT_ZERO);
        }
    }

    @UiHandler("closeBtn")
    public void closeBtn(Button.ClickEvent event) {
        close();
    }

    @UiHandler("selectBtn")
    public void selectBtn(Button.ClickEvent event) {
        if (resultsTable.getValue() != null) {
            FormulaDTO componentLookUp = (FormulaDTO) resultsTable.getValue();
            componentTextField.setValue(componentLookUp.getFormulaName());
            componentTextField.setData(componentLookUp.getFormulaSid());
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
        selection.setCountQueryName("formulaIdCount");
        selection.setDataQueryName("formulaIdRecord");
        selection.setReset(false);
        if (!tableLogic.loadSetData(searchDto, selection)) {
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
                                resultsTable.resetFilters();

                formulaId.setValue(StringUtils.EMPTY);
                formulaNo.setValue(StringUtils.EMPTY);
                formulaName.setValue(StringUtils.EMPTY);
            }

            @Override
            public void noMethod() {
                return;
            }
        }.getConfirmationMessage("Confirmation", "Are you sure you want to reset the values in the Formula Search?");
    }
}
