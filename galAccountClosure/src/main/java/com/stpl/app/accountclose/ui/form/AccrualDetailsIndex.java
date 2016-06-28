/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.ui.form;

import com.stpl.app.accountclose.dto.AccrualDetailsDTO;
import com.stpl.app.accountclose.sessionutils.SessionDTO;
import com.stpl.app.accountclose.utils.Constants;
import com.stpl.app.customtreecontainer.CustomTreeContainer;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.VerticalLayout;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;

/**
 *
 * @author alok.v
 */
public class AccrualDetailsIndex extends CustomComponent implements View {

    SessionDTO session;
    private AccrualDetailsDTO accrualDetailsDTO;
    public CustomFieldGroup AccrualDetailsBinder;
    @UiField("accrualDetailsTableLayout")
    public VerticalLayout accrualDetailsTableLayout;
    public ExtFilterTable accrualResultsTable = new ExtFilterTable();
    private CustomTreeContainer<AccrualDetailsDTO> accrualDetailResultsContainer = new CustomTreeContainer<AccrualDetailsDTO>(AccrualDetailsDTO.class);
    /** The Constant LOGGER. */
    private static final Logger LOGGER = Logger.getLogger(AccrualDetailsIndex.class);
    /**
     *
     * @param screenIndicator
     * @param accrualDetailsDTO
     * @param custom
     * @throws Exception
     */
    public AccrualDetailsIndex(String screenIndicator, AccrualDetailsDTO accrualDetailsDTO, CustomFieldGroup custom) throws Exception {
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/accrualDetails.xml"), this));
        this.accrualDetailsDTO = accrualDetailsDTO;
        this.AccrualDetailsBinder = custom;
        configureFields();
    }

    /**
     * Configure Scheduled Processes Table
     *
     */
    protected void configureFields() {
        try {
            accrualDetailsTableLayout.addComponent(accrualResultsTable);
            configureAccrualResultsTable();

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }
    /**
     * Configuring the Accrual Results Table
     * 
     */
    public void configureAccrualResultsTable() {
        accrualResultsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        accrualResultsTable.setWidth("1630px");
        accrualResultsTable.setHeight("570px");
        accrualResultsTable.setPageLength(5);
        accrualResultsTable.setContainerDataSource(accrualDetailResultsContainer);
        accrualResultsTable.setVisibleColumns(Constants.ACCRUAL_DETAILS_COLUMNS);
        accrualResultsTable.setColumnHeaders(Constants.ACCRUAL_DETAILS_HEADERS);
        for (Object propertyId : accrualResultsTable.getVisibleColumns()) {
            accrualResultsTable.setColumnWidth(propertyId, 185);
        }
    }

    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }
}
