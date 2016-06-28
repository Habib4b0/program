/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.glReserveMapping.ui.form;

import com.stpl.app.accountclose.common.CommonUtil;
import com.stpl.app.accountclose.dto.GLReserveMappingDTO;
import com.stpl.app.accountclose.logic.GLReserveMappingLogic;
import com.stpl.app.accountclose.sessionutils.SessionDTO;
import com.stpl.app.accountclose.utils.AbstractNotificationUtils;
import com.stpl.app.accountclose.utils.Constants;
import com.stpl.app.accountclose.utils.ErrorCodeUtil;
import com.stpl.app.accountclose.utils.ErrorCodes;
import com.stpl.app.accountclose.utils.ExportToExcel;
import com.stpl.app.accountclose.utils.HeaderUtils;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.stpl.ifs.util.ExtCustomTableHolder;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.TableFieldFactory;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
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
public class RulesTab extends CustomComponent {

    @UiField("componentmode")
    OptionGroup componentmode;
    @UiField("CategoryProcess")
    ComboBox categoryProcess;
    GLReserveMappingLogic gLReserveMappingLogic = new GLReserveMappingLogic();
    @UiField("searchtableAvailable")
    public ExtFilterTable searchtableAvailable;
    @UiField("searchtableselected")
    public ExtFilterTable searchtableselected;
    CustomTableHeaderDTO rightDTO;
    CustomTableHeaderDTO fullHeader = new CustomTableHeaderDTO();
    private final Resource excelExportImage = new ThemeResource("../../icons/excel.png");
    @UiField("AddRu")
    public Button Addbtn;
    @UiField("searchbtnRU")
    public Button searchbtnRU;
    @UiField("ExportRu")
    public Button Exportbtn;
    @UiField("RemoveRu")
    public Button Removebtn;
    @UiField("ExportRubottom")
    public Button ExportRubottombtn;
    @UiField("ReasetbtnRU")
    public Button ReasetbtnRU;
    @UiField("savebtnRU")
    public Button savebtnRU;
    private final BeanItemContainer<GLReserveMappingDTO> searchContainer = new BeanItemContainer<GLReserveMappingDTO>(GLReserveMappingDTO.class);
    private final BeanItemContainer<GLReserveMappingDTO> availbleContainer = new BeanItemContainer<GLReserveMappingDTO>(GLReserveMappingDTO.class);
    @UiField("exportlayoutavailable")
    HorizontalLayout exportlayoutavailable;
    @UiField("exportlayoutselected")
    HorizontalLayout exportlayoutselected;
    private final ExtCustomTable excelPVSTableavailable = new ExtCustomTable();
    private final ExtCustomTable excelPVSTableselected = new ExtCustomTable();
    public static final HelperDTO ddlbDefaultValue = new HelperDTO(0, Constants.IndicatorConstants.SELECT_ONE.getConstant());
    private final SessionDTO session;
    ReserveAccount parent;
    String mode = StringUtils.EMPTY;
    public static final Logger LOGGER = Logger.getLogger(RulesTab.class);

    public RulesTab(SessionDTO session, String mode, ReserveAccount parent) throws Exception {
        this.session = session;
        this.mode = mode;
        this.parent = parent;
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/Rules.xml"), this));
        configurefields();
    }

    private void configurefields() throws Exception {
        if (Constants.VIEW_MODE.equals(mode)) {
            searchbtnRU.setEnabled(false);
            Addbtn.setEnabled(false);
            Removebtn.setEnabled(false);
            ReasetbtnRU.setEnabled(false);
            savebtnRU.setEnabled(false);
        } else {
            searchbtnRU.setEnabled(true);
            Addbtn.setEnabled(true);
            Removebtn.setEnabled(true);
            ReasetbtnRU.setEnabled(true);
            savebtnRU.setEnabled(true);

        }
        exportlayoutavailable.addComponent(excelPVSTableavailable);
        excelPVSTableavailable.setVisible(false);
        exportlayoutselected.addComponent(excelPVSTableselected);
        excelPVSTableselected.setVisible(false);
        Exportbtn.setIcon(excelExportImage);
        Exportbtn.setPrimaryStyleName("link");
        ExportRubottombtn.setIcon(excelExportImage);
        ExportRubottombtn.setPrimaryStyleName("link");
        componentmode.addItem("Current");
        componentmode.addItem("History");
        componentmode.select("Current");
        categoryProcess.addItem(Constants.SELECT_ONE);
        categoryProcess.setNullSelectionAllowed(true);
        categoryProcess.setNullSelectionItemId(Constants.SELECT_ONE);
        categoryProcess.addItems(gLReserveMappingLogic.loadCategoryDDLB());
        categoryProcess.select(Constants.SELECT_ONE);
        categoryProcess.setValidationVisible(true);
        categoryProcess.setImmediate(true);
        categoryProcess.addStyleName("popupContentComboSize");
        fullHeader = new CustomTableHeaderDTO();
        rightDTO = HeaderUtils.getGlrMapAvailableTableTableColumns(fullHeader);
        searchtableAvailable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        searchtableAvailable.setEditable(true);
        searchtableselected.setEditable(true);
        searchtableAvailable.setWidth("100%");
        searchtableAvailable.setHeight("100%");
        searchtableAvailable.setFilterBarVisible(true);
        searchtableAvailable.setImmediate(true);
        searchtableAvailable.setPageLength(5);
        searchtableAvailable.setFilterDecorator(new ExtDemoFilterDecorator());
        searchtableAvailable.setContainerDataSource(searchContainer);
        searchtableAvailable.setVisibleColumns(rightDTO.getSingleColumns().toArray());
        searchtableAvailable.setColumnHeaders(rightDTO.getSingleHeaders().toArray(new String[rightDTO.getSingleHeaders().size()]));
        searchtableselected.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        searchtableselected.setWidth("100%");
        searchtableselected.setHeight("100%");
        searchtableselected.setFilterBarVisible(true);
        searchtableselected.setImmediate(true);
        searchtableselected.setPageLength(5);
        searchtableselected.setFilterDecorator(new ExtDemoFilterDecorator());
        searchtableselected.setContainerDataSource(availbleContainer);
        searchtableselected.setVisibleColumns(rightDTO.getSingleColumns().toArray());
        searchtableselected.setColumnHeaders(rightDTO.getSingleHeaders().toArray(new String[rightDTO.getSingleHeaders().size()]));
        searchtableAvailable.setColumnCheckBox(Constants.CHECK, Boolean.TRUE);
        searchtableselected.setColumnCheckBox(Constants.CHECK, Boolean.TRUE);
        Object[] visibleColumns = searchtableAvailable.getVisibleColumns();
        for (Object column : visibleColumns) {
            searchtableAvailable.setColumnWidth(column, 165);
            searchtableAvailable.setColumnAlignment(column, ExtCustomTable.Align.LEFT);
        }
        Object[] visibleColumns1 = searchtableselected.getVisibleColumns();
        for (Object column : visibleColumns1) {
            searchtableselected.setColumnWidth(column, 165);
            searchtableselected.setColumnAlignment(column, ExtCustomTable.Align.LEFT);
        }

        searchtableselected.setTableFieldFactory(new TableFieldFactory() {
            public Field<?> createField(Container container, Object itemId, Object propertyId, Component uiContext) {
                Field field;
                if (String.valueOf(Constants.CHECK).equals(propertyId)) {
                    field = new CheckBox();
                } else {
                    field = null;
                }
                return field;
            }
        });

        searchtableselected.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {
            public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                for (GLReserveMappingDTO temp : searchContainer.getItemIds()) {
                    searchContainer.getItem(temp).getItemProperty(event.getPropertyId()).setValue(event.isChecked());
                }
            }
        });
        searchtableAvailable.setTableFieldFactory(new TableFieldFactory() {
            public Field<?> createField(Container container, Object itemId, Object propertyId, Component uiContext) {
                Field field;
                if (String.valueOf(Constants.CHECK).equals(propertyId)) {
                    field = new CheckBox();
                } else {
                    field = null;
                }
                return field;
            }
        });

        searchtableAvailable.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {
            public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                for (GLReserveMappingDTO temp : availbleContainer.getItemIds()) {
                    searchContainer.getItem(temp).getItemProperty(event.getPropertyId()).setValue(event.isChecked());
                }
            }
        });

        excelPVSTableavailable.setContainerDataSource(searchContainer);
        excelPVSTableavailable.setVisibleColumns(rightDTO.getSingleColumns().toArray());
        excelPVSTableavailable.setColumnHeaders(rightDTO.getSingleHeaders().toArray(new String[rightDTO.getSingleHeaders().size()]));

        excelPVSTableselected.setContainerDataSource(availbleContainer);
        excelPVSTableselected.setVisibleColumns(rightDTO.getSingleColumns().toArray());
        excelPVSTableselected.setColumnHeaders(rightDTO.getSingleHeaders().toArray(new String[rightDTO.getSingleHeaders().size()]));
    }

    @UiHandler("componentmode")
    public void componentmodeChange(Property.ValueChangeEvent event) throws Exception {
        if (componentmode.getValue().toString().equalsIgnoreCase("History")) {
            searchtableselected.setVisible(false);
            Addbtn.setVisible(false);
            Exportbtn.setVisible(false);
            Removebtn.setVisible(false);
            ExportRubottombtn.setVisible(false);
            searchtableAvailable.setPageLength(12);
            searchContainer.removeAllItems();
            searchContainer.addAll(gLReserveMappingLogic.loadSavedRules(String.valueOf(session.getGlReserveMapping().getGlResMasterSid()), true));
        } else {
            searchContainer.removeAllItems();
            searchtableselected.setVisible(true);
            Addbtn.setVisible(true);
            Exportbtn.setVisible(true);
            Removebtn.setVisible(true);
            ExportRubottombtn.setVisible(true);
            searchtableAvailable.setPageLength(5);
        }
    }

    @UiHandler("CategoryProcess")
    public void CategoryProcessChange(Property.ValueChangeEvent event) throws Exception {
    }

    @UiHandler("ExportRu")
    public void ExportbtnClick(Button.ClickEvent event) {
        Map<String, String> formatter = new HashMap<String, String>();
        ExportToExcel excel = new ExportToExcel(new ExtCustomTableHolder(excelPVSTableavailable), "Rules", "Rules", "Rules.xls", false, formatter, "Rules");
        excel.export();
    }

    @UiHandler("AddRu")
    public void AddbtnClick(Button.ClickEvent event) {
        List<GLReserveMappingDTO> containerList = searchContainer.getItemIds();
        List<GLReserveMappingDTO> addedList = new ArrayList<GLReserveMappingDTO>();
        int count = 0;
        for (GLReserveMappingDTO dto : containerList) {
            if (dto.getCheck()) {
                addedList.add(dto);
                count++;
            }
        }
        if (count > 0) {
            availbleContainer.addAll(addedList);

        } else {
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_002), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_030));
        }

        for (GLReserveMappingDTO addedDto : addedList) {
            searchContainer.removeItem(addedDto);
        }
    }

    @UiHandler("ExportRubottom")
    public void ExportRubottombtnClick(Button.ClickEvent event) {
        Map<String, String> formatter = new HashMap<String, String>();
        ExportToExcel excel = new ExportToExcel(new ExtCustomTableHolder(excelPVSTableselected), "Rules", "Rules", "Rules.xls", false, formatter, "Rules");
        excel.export();
    }

    @UiHandler("ReasetbtnRU")
    public void bottomReasetClick(Button.ClickEvent event) {
        new AbstractNotificationUtils() {
            @Override
            public void noMethod() {
                
            }

            @Override
            public void yesMethod() {
                try {
                    searchContainer.removeAllItems();
                    availbleContainer.removeAllItems();
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            }
        }.getConfirmationMessage(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_032).concat(ErrorCodes.QUESTION));
    }

    @UiHandler("savebtnRU")
    public void savebtnRUClick(Button.ClickEvent event) {
        new AbstractNotificationUtils() {
            @Override
            public void noMethod() {
                
            }

            @Override
            public void yesMethod() {
                try {
                    List<GLReserveMappingDTO> containerList = availbleContainer.getItemIds();
                    gLReserveMappingLogic.saveSelectedRules(containerList, session.getGlReserveMapping().getGlResMasterSid());
                    parent.saveButtonLogic("Accrual Rate");
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            }
        }.getConfirmationMessage(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_033).concat(ErrorCodes.QUESTION));
    }

    @UiHandler("searchbtnRU")
    public void searchButton(Button.ClickEvent event) throws SystemException, PortalException, Exception {
        if (!CommonUtil.emptyCheck(String.valueOf(categoryProcess.getValue()))) {
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_002), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_036));
        } else {
            String category = String.valueOf(categoryProcess.getValue());
            List<GLReserveMappingDTO> searchResults = new ArrayList<GLReserveMappingDTO>();
            searchResults = gLReserveMappingLogic.loadAvailableRules(category);

            if (searchResults.isEmpty()) {
                AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_005),
                        ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_012).concat(ErrorCodes.PERIOD).concat(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_013)).concat(ErrorCodes.PERIOD));
            } else {
                searchContainer.removeAllItems();
                searchContainer.addAll(searchResults);
                Notification.show("Search Completed");
            }
        }
    }

    @UiHandler("RemoveRu")
    public void removebtnRUClick(Button.ClickEvent event) {
        List<GLReserveMappingDTO> containerList = availbleContainer.getItemIds();
        List<GLReserveMappingDTO> removeList = new ArrayList<GLReserveMappingDTO>();
        int count = 0;
        for (GLReserveMappingDTO dto : containerList) {
            if (dto.getCheck()) {
                removeList.add(dto);
                count++;
            }
        }
        if (count > 0) {
            searchContainer.addAll(removeList);
        } else {
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_002), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_030));
        }
        for (GLReserveMappingDTO addedDto : removeList) {
            availbleContainer.removeItem(addedDto);
        }
    }

    void loadSelectedRules(SessionDTO session) {
        try {
            if ("View".equals(mode) || "Edit".equals(mode) || "Copy".equals(mode)) {
                availbleContainer.removeAllItems();
                List<Object[]> versionFromGlTables = gLReserveMappingLogic.getVersionNo(true, String.valueOf(session.getGlReserveMapping().getGlResMasterSid()));
                String glVersionNo = StringUtils.EMPTY;
                String transctionSid = StringUtils.EMPTY;
                Object[] obj = versionFromGlTables.get(0);
                glVersionNo = String.valueOf(obj[1]);
                transctionSid = String.valueOf(obj[0]);
                List versionFromBpmtables = gLReserveMappingLogic.getVersionNo(false, transctionSid);
                Integer obj1 = (Integer) versionFromBpmtables.get(0);
                String bpmVersionNo = String.valueOf(obj1);
                if (glVersionNo.equalsIgnoreCase(bpmVersionNo)) {
                    availbleContainer.addAll(gLReserveMappingLogic.loadSavedRules(String.valueOf(session.getGlReserveMapping().getGlResMasterSid()), false));
                } else {
                    AbstractNotificationUtils.getInfoNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_034), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_035).concat(ErrorCodeUtil.getEC(ErrorCodes.PERIOD)));
                    gLReserveMappingLogic.updateGlVersionNo(String.valueOf(session.getGlReserveMapping().getGlResMasterSid()), bpmVersionNo);
                    availbleContainer.addAll(gLReserveMappingLogic.loadSavedRules(String.valueOf(session.getGlReserveMapping().getGlResMasterSid()), false));
                }
                if ("View".equals(mode)) {
                    Addbtn.setVisible(false);
                    Removebtn.setVisible(false);
                    savebtnRU.setVisible(false);
                    ReasetbtnRU.setVisible(false);
                    searchtableAvailable.setEditable(false);
                    searchtableselected.setEditable(false);
                    searchtableselected.setVisibleColumns(rightDTO.getSingleColumns().toArray());
                    searchtableselected.setColumnHeaders(rightDTO.getSingleHeaders().toArray(new String[rightDTO.getSingleHeaders().size()]));

                    searchtableAvailable.setVisibleColumns(rightDTO.getSingleColumns().toArray());
                    searchtableAvailable.setColumnHeaders(rightDTO.getSingleHeaders().toArray(new String[rightDTO.getSingleHeaders().size()]));
                }
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }
}
