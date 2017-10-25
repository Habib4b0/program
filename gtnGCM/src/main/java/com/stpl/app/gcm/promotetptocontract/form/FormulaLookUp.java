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
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
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
    public Button selectBtn;
    @UiField("closeBtn")
    public Button closeBtn;
    FormulaIdTableLogic tableLogic = new FormulaIdTableLogic();
    private ExtPagedTable resultsTable = new ExtPagedTable(tableLogic);
    public CustomTextField componentTextField;
    private BeanItemContainer<FormulaDTO> resultsContainer = new BeanItemContainer<FormulaDTO>(FormulaDTO.class);
    public static final Object FORMULA_SEARCH_COLUMNS[] = new Object[]{
        "formulaId", "formulaNo", "formulaName"};
    public static final String FORMULA_SEARCH_HEADERS[] = new String[]{
        "Formula Id", "Formula No", "Formula Name"};
    SelectionDTO selection = new SelectionDTO();

    public FormulaLookUp(CustomTextField componentTextField) {
        super("Formula ID lookup");
        setContent(Clara.create(getClass().getResourceAsStream("/discount/formulaIdpopup.xml"), this));
        addStyleName("valo-theme-customwindow");
        addStyleName("bootstrap-ui");
        addStyleName(Constants.bootstrap);
        addStyleName(Constants.bootstrap_forecast_bootstrap_nm);
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
        resultsTable.setVisibleColumns(FORMULA_SEARCH_COLUMNS);
        resultsTable.setColumnHeaders(FORMULA_SEARCH_HEADERS);
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
    public void closeBtn(Button.ClickEvent event) throws SystemException, PortalException {
        close();
    }

    @UiHandler("selectBtn")
    public void selectBtn(Button.ClickEvent event) throws SystemException, PortalException {
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
    public void searchButtonLogic(Button.ClickEvent event) throws SystemException, PortalException {
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
    public void resetButtonLogic(Button.ClickEvent event) throws SystemException, PortalException {
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
            }
        }.getConfirmationMessage("Confirmation", "Are you sure you want to reset the values in the Formula Search?");
    }
}
