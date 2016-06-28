/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.glReserveMapping.ui.form;

import com.stpl.app.accountclose.dto.GLReserveMappingDTO;
import com.stpl.app.accountclose.logic.GLReserveMappingLogic;
import com.stpl.app.accountclose.sessionutils.SessionDTO;
import com.stpl.app.accountclose.utils.AbstractNotificationUtils;
import com.stpl.app.accountclose.utils.Constants;
import com.stpl.app.accountclose.utils.ErrorCodeUtil;
import com.stpl.app.accountclose.utils.ErrorCodes;
import com.stpl.app.accountclose.utils.HeaderUtils;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.Field;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TableFieldFactory;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extcustomcheckbox.ExtCustomCheckBox;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author kasiammal.m
 */
public class Businessprocess extends CustomComponent {

    @UiField("componentmode")
    OptionGroup componentmode;
    @UiField("searchtable")
    public ExtFilterTable searchtable;
    CustomTableHeaderDTO rightDTO;
    CustomTableHeaderDTO fullHeader = new CustomTableHeaderDTO();
    private BeanItemContainer<GLReserveMappingDTO> searchContainer = new BeanItemContainer<GLReserveMappingDTO>(GLReserveMappingDTO.class);
    @UiField("businessProcess")
    public ComboBox businessProcess;
    @UiField("ReasetbtnBP")
    public Button ReasetbtnBP;
    String mode = StringUtils.EMPTY;
    private final SessionDTO session;
    GLReserveMappingLogic logic = new GLReserveMappingLogic();
    @UiField("savebtnbp")
    public Button saveBtn;
    ReserveAccount parent;
    TabSheet tabsheet;

    private static final Logger LOGGER = Logger.getLogger(Businessprocess.class);

    public Businessprocess(SessionDTO session, String mode, ReserveAccount parent, TabSheet tabsheet) {

        this.mode = mode;
        this.session = session;
        this.parent = parent;
        this.tabsheet = tabsheet;
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/BusinessProcess.xml"), this));
        configurefields();
    }

    private void configurefields() {
        componentmode.addItem("Current");
        componentmode.addItem("History");
        componentmode.select("Current");
        if (Constants.VIEW_MODE.equals(mode) || Constants.EDIT_MODE.equals(mode)) {
            componentmode.setVisible(true);
        } else {
            componentmode.setVisible(false);
        }
        searchtable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        searchtable.setWidth("100%");
        searchtable.setHeight("100%");
        searchtable.setFilterBarVisible(true);

        searchtable.setImmediate(true);
        searchtable.setPageLength(15);
        searchtable.setFilterDecorator(new ExtDemoFilterDecorator());
        fullHeader = new CustomTableHeaderDTO();
        rightDTO = HeaderUtils.getGlrMappingSearchTableColumns(fullHeader);
        searchtable.setContainerDataSource(searchContainer);
        if (Constants.VIEW_MODE.equals(mode)) {
            searchtable.setEditable(false);
            searchtable.setVisibleColumns(rightDTO.getSingleColumns().subList(1, rightDTO.getSingleColumns().size()).toArray());
            searchtable.setColumnHeaders(rightDTO.getSingleHeaders().subList(1, rightDTO.getSingleHeaders().size()).toArray(new String[rightDTO.getSingleHeaders().size() - 1]));
            saveBtn.setEnabled(false);
            ReasetbtnBP.setEnabled(false);
        } else {
            searchtable.setEditable(true);
            searchtable.setVisibleColumns(rightDTO.getSingleColumns().subList(1, rightDTO.getSingleColumns().size()).toArray());
            searchtable.setColumnHeaders(rightDTO.getSingleHeaders().subList(1, rightDTO.getSingleHeaders().size()).toArray(new String[rightDTO.getSingleHeaders().size() - 1]));
            searchtable.setColumnCheckBox("check", Boolean.TRUE);
            searchtable.setTableFieldFactory(new TableFieldFactory() {
                public Field<?> createField(Container container, final Object itemId, Object propertyId, Component uiContext) {
                    Field field = null;

                    if ("check".equals(propertyId)) {
                        final ExtCustomCheckBox check = new ExtCustomCheckBox();
                        check.addClickListener(new ExtCustomCheckBox.ClickListener() {
                            public void click(ExtCustomCheckBox.ClickEvent event) {
                                Boolean checkValue = check.getValue();
                                if (!checkValue) {
                                    searchtable.setColumnCheckBox("check", true, false);
                                }
                            }
                        });
                        field = check;
                    }
                    return field;
                }
            });
            searchtable.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {
                public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                    for (GLReserveMappingDTO temp : searchContainer.getItemIds()) {
                        searchContainer.getItem(temp).getItemProperty(event.getPropertyId()).setValue(event.isChecked());
                    }
                }
            });
        }

        businessProcess.addItem(Constants.IndicatorConstants.SELECT_ONE.getConstant());
        businessProcess.setNullSelectionAllowed(true);
        businessProcess.setNullSelectionItemId(Constants.IndicatorConstants.SELECT_ONE.getConstant());
        businessProcess.setImmediate(true);
        businessProcess.setValue(Constants.IndicatorConstants.SELECT_ONE.getConstant());
        businessProcess.addItem("Accrual Rate");
        businessProcess.addItem("Adjustment");
        businessProcess.setStyleName("acctmandatoryfields");
        Object[] visibleColumns = searchtable.getVisibleColumns();
        for (Object column : visibleColumns) {
            searchtable.setColumnWidth(column, 175);
            searchtable.setColumnAlignment(column, ExtCustomTable.Align.LEFT);
        }

        setSelectionCriteria(session);
    }

    void setSelectionCriteria(SessionDTO session) {
        if (Constants.VIEW_MODE.equals(mode) || Constants.EDIT_MODE.equals(mode) || Constants.COPY_MODE.equals(mode)) {
            searchContainer.removeAllItems();
            List<GLReserveMappingDTO> results = logic.loadAccDtls(String.valueOf(this.session.getGlReserveMapping().getGlResMasterSid()), false);
            searchContainer.addAll(results);
            String bp = results.get(0).getBusinessProcess();
            for (Object itemId : businessProcess.getItemIds()) {
                if (bp.equals(itemId.toString())) {
                    businessProcess.select(itemId);
                    break;
                }
            }
            if (Constants.VIEW_MODE.equals(mode)) {
                businessProcess.setEnabled(false);
            }
        } else {
            List<GLReserveMappingDTO> selectedList = session.getSelectedList();
            if (!selectedList.isEmpty()) {
                searchContainer.removeAllItems();
                searchContainer.addAll(selectedList);
            }
        }
    }

    @UiHandler("savebtnbp")
    public void saveButtonLogic(Button.ClickEvent event) {
        if (businessProcess.getValue() == null) {
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_003), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_009) + (ErrorCodes.PERIOD));
        } else {
            String businesProcess = String.valueOf(businessProcess.getValue());
            parent.saveButtonLogic(businesProcess);
        }
    }

    @UiHandler("ReasetbtnBP")
    public void bottomReasetClick(Button.ClickEvent event) {
        new AbstractNotificationUtils() {
            @Override
            public void noMethod() {

            }

            @Override
            public void yesMethod() {
                try {
                    searchContainer.removeAllItems();
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            }
        }.getConfirmationMessage(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_010) + (ErrorCodes.QUESTION));

    }

    @UiHandler("componentmode")
    public void componentmodeChange(Property.ValueChangeEvent event) throws Exception {
        fullHeader = new CustomTableHeaderDTO();
        rightDTO = HeaderUtils.getGlrMappingSearchTableColumns(fullHeader);
        if (componentmode.getValue().toString().equalsIgnoreCase("History")) {
            searchtable.setVisibleColumns(rightDTO.getSingleColumns().subList(1, rightDTO.getSingleColumns().size()).toArray());
            searchtable.setColumnHeaders(rightDTO.getSingleHeaders().subList(1, rightDTO.getSingleHeaders().size()).toArray(new String[rightDTO.getSingleHeaders().size() - 1]));
            if (!searchtable.getItemIds().isEmpty()) {
                searchContainer.removeAllItems();
                searchContainer.addAll(logic.loadAccDtls(String.valueOf(session.getGlReserveMapping().getGlResMasterSid()), true));
            }
        } else {
            if (Constants.EDIT_MODE.equals(mode)) {
                searchContainer = new BeanItemContainer<>(GLReserveMappingDTO.class);
                searchtable.setContainerDataSource(searchContainer);
                searchtable.setVisibleColumns(rightDTO.getSingleColumns().subList(1, rightDTO.getSingleColumns().size()).toArray());
                searchtable.setColumnHeaders(rightDTO.getSingleHeaders().subList(1, rightDTO.getSingleHeaders().size()).toArray(new String[rightDTO.getSingleHeaders().size() - 1]));
                searchContainer.addAll(logic.loadAccDtls(String.valueOf(session.getGlReserveMapping().getGlResMasterSid()), false));
            }
            if (Constants.VIEW_MODE.equals(mode)) {
                searchContainer.removeAllItems();
                searchContainer = new BeanItemContainer<>(GLReserveMappingDTO.class);
                searchtable.setContainerDataSource(searchContainer);
                searchtable.setVisibleColumns(rightDTO.getSingleColumns().subList(1, rightDTO.getSingleColumns().size()).toArray());
                searchtable.setColumnHeaders(rightDTO.getSingleHeaders().subList(1, rightDTO.getSingleHeaders().size()).toArray(new String[rightDTO.getSingleHeaders().size() - 1]));
                searchContainer.addAll(logic.loadAccDtls(String.valueOf(session.getGlReserveMapping().getGlResMasterSid()), false));
            }
        }
    }

    @UiHandler("businessProcess")
    public void businessProcessLogic(Property.ValueChangeEvent event) throws Exception {
        if (businessProcess.getValue() != null) {
            String businesProcess = String.valueOf(businessProcess.getValue());
            if ("Adjustment".equals(businesProcess) && tabsheet.getTab(2) != null) {
                tabsheet.getTab(2).setVisible(false);
            } else if (tabsheet.getTab(2) != null) {
                tabsheet.getTab(2).setVisible(true);
            }
        }
    }
}
