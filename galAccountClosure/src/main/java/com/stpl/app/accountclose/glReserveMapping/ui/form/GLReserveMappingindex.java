/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.glReserveMapping.ui.form;

import com.stpl.app.accountclose.dto.GLReserveMappingDTO;
import com.stpl.app.accountclose.dto.GLReserveTreeDTO;
import com.stpl.app.accountclose.glReserveMapping.tableLogic.GLRIndexTableLogic;
import com.stpl.app.accountclose.logic.GLReserveMappingLogic;
import com.stpl.app.accountclose.sessionutils.SessionDTO;
import com.stpl.app.accountclose.ui.layout.GLReserveMappingMainWindow;
import com.stpl.app.accountclose.utils.AbstractNotificationUtils;
import com.stpl.app.accountclose.utils.Constants;
import com.stpl.app.accountclose.utils.DateToStringConverter;
import com.stpl.app.accountclose.utils.ErrorCodeUtil;
import com.stpl.app.accountclose.utils.ErrorCodes;
import com.stpl.app.accountclose.utils.HeaderUtils;
import com.stpl.app.customtreecontainer.CustomTreeContainer;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.util.CsvExportforPagedTable;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Tree;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;
import org.asi.container.ExtContainer;

/**
 *
 * @author kasiammal.m
 */
public class GLReserveMappingindex extends CustomComponent implements View {

    private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(GLReserveMappingindex.class);
    @UiField("componentitems")
    OptionGroup componentitems;
    @UiField("searchbtn")
    Button searchbtn;
    @UiField("generatebtn")
    Button generatebtn;
    @UiField("reasetbtn")
    Button reasetbtn;
    GLRIndexTableLogic tableLogic = new GLRIndexTableLogic();
    ExtPagedTable searchtable = new ExtPagedTable(tableLogic);
    @UiField("businessUnit")
    ComboBox businessUnit;
    @UiField("programType")
    ComboBox programType;
    @UiField("programsubType")
    ComboBox programsubType;
    @UiField("frequency")
    ComboBox frequency;
    Tree selectiontable = new Tree();
    @UiField("selectiontbl")
    VerticalLayout selectiontbl;
    @UiField("resultstbl")
    VerticalLayout resultstbl;
    private GLReserveMappingDTO binderDto = new GLReserveMappingDTO();
    private CustomFieldGroup binder = new CustomFieldGroup(new BeanItem<>(binderDto));
    ExtContainer<GLReserveMappingDTO> extSearchContainer = new ExtContainer<GLReserveMappingDTO>(GLReserveMappingDTO.class);
    CustomTreeContainer<GLReserveMappingDTO> excelContainer = new CustomTreeContainer<GLReserveMappingDTO>(GLReserveMappingDTO.class);
    GLReserveMappingDTO glReserveMappingDTOobj = new GLReserveMappingDTO();
    private final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd ");
    DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
    private final Resource excelExportImage = new ThemeResource("../../icons/excel.png");
    @UiField("addbtn")
    Button addbtn;
    @UiField("deletebtn")
    Button deletebtn;
    @UiField("editbtn")
    Button editbtn;
    @UiField("copybtn")
    Button copybtn;
    @UiField("viewbtn")
    Button viewbtn;
    @UiField("exportbtn")
    Button exportbtn;
    @UiField("exportlayout")
    HorizontalLayout exportlayout;
    @UiField("reasetbtnbottom")
    Button reasetbtnbottom;
    private ExtCustomTable excelPVSTable = new ExtCustomTable();
    private CustomTreeContainer<GLReserveTreeDTO> treeContainer = new CustomTreeContainer<GLReserveTreeDTO>(GLReserveTreeDTO.class);
    private GLReserveMappingLogic logic = new GLReserveMappingLogic();
    private Boolean expandFlag = Boolean.TRUE;
    TextField pageTextField;
    ComboBox pageLength;
    CustomTableHeaderDTO rightDTO;
    CustomTableHeaderDTO fullHeader = new CustomTableHeaderDTO();
    HeaderUtils utils = new HeaderUtils();

    public GLReserveMappingindex() throws SystemException, Exception {

        addStyleName("bootstrap-ui");
        addStyleName("bootstrap");
        addStyleName("bootstrap-forecast bootstrap-nm");
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/glReserveMapping.xml"), this));
        try {
            init();
        } catch (Exception ex) {
            LOGGER.error(ex);
        }

    }

    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

    private void init() throws SystemException, Exception {
        getBinder();
        businessUnit.focus();
        componentitems.addItem("Collapsed");
        componentitems.addItem("Expand");
        componentitems.select("Collapsed");
        selectiontable.setImmediate(true);
        logic.loadGLRSVMasterTable();
        frequency = logic.loadFrequency(frequency, false);
        logic.comboBoxAddItems(businessUnit, "gl.getCompany", Boolean.FALSE, StringUtils.EMPTY, StringUtils.EMPTY);
        logic.comboBoxAddItems(programType, "gl.loadDType", Boolean.FALSE, StringUtils.EMPTY, StringUtils.EMPTY);
        logic.comboBoxAddItems(programsubType, "gl.loadDSType", Boolean.FALSE, StringUtils.EMPTY, StringUtils.EMPTY);
        resultstbl.addComponent(searchtable);
        HorizontalLayout layout = tableLogic.createControls();
        pageTextField = (TextField) ((HorizontalLayout) layout.getComponent(1)).getComponent(3);
        pageLength = (ComboBox) ((HorizontalLayout) layout.getComponent(0)).getComponent(1);
        layout.setStyleName("responsivePagedTable");
        resultstbl.addComponent(layout);
        tableLogic.setContainerDataSource(extSearchContainer);
        tableLogic.setPageLength(10);
        tableLogic.sinkItemPerPageWithPageLength(false);
        searchtable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        searchtable.setWidth("100%");
        searchtable.setHeight("100%");
        searchtable.setFilterBarVisible(true);
        searchtable.setImmediate(true);
        searchtable.setPageLength(10);
        searchtable.setFilterDecorator(new ExtDemoFilterDecorator());
        setFiltersComponents();
        searchtable.setSelectable(true);
        searchtable.setMultiSelect(false);
        fullHeader = new CustomTableHeaderDTO();
        rightDTO = utils.getGlrMappingIndexSearchTableColumns(fullHeader);

        searchtable.setVisibleColumns(rightDTO.getSingleColumns().toArray());
        searchtable.setColumnHeaders(rightDTO.getSingleHeaders().toArray(new String[rightDTO.getSingleHeaders().size()]));
        searchtable.setConverter("createdDate", new DateToStringConverter());
        searchtable.setConverter("modifiedDate", new DateToStringConverter());
        Object[] visibleColumns = searchtable.getVisibleColumns();
        selectiontbl.addComponent(selectiontable);
        selectiontable.setSizeFull();
        selectiontable.setWidth("100%");
        selectiontable.setHeight("100%");
        selectiontable.setImmediate(true);
        exportlayout.addComponent(excelPVSTable);
        excelPVSTable.setVisible(false);
        excelPVSTable.setContainerDataSource(excelContainer);

        excelPVSTable.setVisibleColumns(rightDTO.getSingleColumns().toArray());
        excelPVSTable.setColumnHeaders(rightDTO.getSingleHeaders().toArray(new String[rightDTO.getSingleHeaders().size()]));
        selectiontable.setContainerDataSource(treeContainer);
        selectiontable.setItemCaptionPropertyId("levelValue");
        List<GLReserveTreeDTO> tempList = logic.loadCompany();
        for (GLReserveTreeDTO tempList1 : tempList) {
            treeContainer.addItem(tempList1);
            treeContainer.setChildrenAllowed(tempList1, true);
        }
        exportbtn.setIcon(excelExportImage);
        exportbtn.setPrimaryStyleName("link");
        selectiontable.addExpandListener(new Tree.ExpandListener() {
            public void nodeExpand(Tree.ExpandEvent event) {
                if (event != null && expandFlag) {
                    GLReserveTreeDTO temp = (GLReserveTreeDTO) event.getItemId();
                    if (temp.getLevelNo() == 1) {
                        List<GLReserveTreeDTO> tempList = logic.loadDT(String.valueOf(temp.getLevelId()));
                        for (GLReserveTreeDTO tempList1 : tempList) {
                            treeContainer.addItem(tempList1);
                            treeContainer.setChildrenAllowed(tempList1, true);
                            treeContainer.setParent(tempList1, event.getItemId());
                        }
                    } else if (temp.getLevelNo() == 2) {
                        GLReserveTreeDTO parent = (GLReserveTreeDTO) treeContainer.getParent(temp);
                        List<GLReserveTreeDTO> tempList = logic.loadDST(String.valueOf(parent.getLevelId()), String.valueOf(temp.getLevelId()));
                        for (GLReserveTreeDTO tempList1 : tempList) {
                            treeContainer.addItem(tempList1);
                            treeContainer.setChildrenAllowed(tempList1, false);
                            treeContainer.setParent(tempList1, event.getItemId());
                        }
                    }
                }

            }
        });

        selectiontable.addCollapseListener(new Tree.CollapseListener() {
            public void nodeCollapse(Tree.CollapseEvent event) {
                
                Collection<?> parent = (Collection<?>) treeContainer.getChildren(event.getItemId());
                List<Object> temp = new ArrayList<Object>();
                for (Object next : parent) {
                    temp.add(next);
                }
                for (int i = temp.size() - 1; i >= 0; i--) {
                    treeContainer.removeItemRecursively(temp.get(i));
                }

            }
        });

        selectiontable.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            public void itemClick(ItemClickEvent event) {
                GLReserveTreeDTO temp = (GLReserveTreeDTO) event.getItemId();
                if (temp.getLevelNo() == 3) {
                    GLReserveTreeDTO parent1 = (GLReserveTreeDTO) treeContainer.getParent(temp);
                    GLReserveTreeDTO parent2 = (GLReserveTreeDTO) treeContainer.getParent(parent1);
                    for (Object itemId : businessUnit.getItemIds()) {
                        if (parent2.getLevelValue().equals(itemId.toString())) {
                            businessUnit.select(itemId);
                            break;
                        }
                    }
                    for (Object itemId : programType.getItemIds()) {
                        if (parent1.getLevelValue().equals(itemId.toString())) {
                            programType.select(itemId);
                            break;
                        }
                    }
                    for (Object itemId : programsubType.getItemIds()) {
                        if (temp.getLevelValue().equals(itemId.toString())) {
                            programsubType.select(itemId);
                            break;
                        }
                    }
                }
            }
        });

        businessUnit.focus();
        businessUnit.setTabIndex(1);
        programType.setTabIndex(2);
        programsubType.setTabIndex(3);
        frequency.setTabIndex(4);
        searchbtn.setTabIndex(5);
        reasetbtn.setTabIndex(6);
        searchtable.setTabIndex(7);
        componentitems.setTabIndex(8);
        generatebtn.setTabIndex(9);
        selectiontable.setTabIndex(10);
        reasetbtnbottom.setTabIndex(11);
        addbtn.setTabIndex(12);
        editbtn.setTabIndex(13);
        viewbtn.setTabIndex(14);
        deletebtn.setTabIndex(15);
        copybtn.setTabIndex(16);
        exportbtn.setTabIndex(17);

        LOGGER.info("search table tab index" + searchtable.getTabIndex());

    }

    private CustomFieldGroup getBinder() {
        binder.bindMemberFields(this);
        binder.setItemDataSource(new BeanItem<>(binderDto));
        binder.setBuffered(true);
        return binder;
    }

    public void generateBtnClick() {
        logic.loadGLRSVMasterTable();
        treeContainer.removeAllItems();
        List<GLReserveTreeDTO> tempList = logic.loadCompany();
        for (GLReserveTreeDTO tempList1 : tempList) {
            treeContainer.addItem(tempList1);
            treeContainer.setChildrenAllowed(tempList1, true);
        }
    }

    @UiHandler("searchbtn")
    public void searchbtnClick(Button.ClickEvent event) throws Exception {

        if (businessUnit.getValue() == null && programType.getValue() == null && programsubType.getValue() == null && frequency.getValue() == null) {
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_004), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_011) + (ErrorCodes.PERIOD));
            tableLogic.loadSetData(binderDto, Boolean.TRUE, Boolean.TRUE);
        } else {
            binder.commit();
            if (!tableLogic.loadSetData(binderDto, Boolean.FALSE, Boolean.TRUE)) {
                AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_005), (ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_012) + (ErrorCodes.PERIOD)) + (ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_013) + (ErrorCodes.PERIOD)));
            } else {
                Notification.show("Search Completed");
            }
        }
    }

    @UiHandler("reasetbtn")
    public void ReasetbtnClick(Button.ClickEvent event) {
        new AbstractNotificationUtils() {
            @Override
            public void noMethod() {
               
            }

            @Override
            public void yesMethod() {
                try {
                    businessUnit.select(Constants.IndicatorConstants.SELECT_ONE.getConstant());
                    programType.select(Constants.IndicatorConstants.SELECT_ONE.getConstant());
                    programsubType.select(Constants.IndicatorConstants.SELECT_ONE.getConstant());
                    frequency.select(Constants.IndicatorConstants.SELECT_ONE.getConstant());
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            }
        }.getConfirmationMessage(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_014) + (ErrorCodes.QUESTION));

    }

    @UiHandler("addbtn")
    public void AddbtnClick(Button.ClickEvent event) throws Exception {
        SessionDTO temp = new SessionDTO();
        temp.setGlReserveMapping((GLReserveMappingDTO) searchtable.getValue());
        GLReserveMappingMainWindow CCWindow = new GLReserveMappingMainWindow(temp, "Add");
        UI.getCurrent().addWindow(CCWindow);
    }

    @UiHandler("generatebtn")
    public void generatebtnClick(Button.ClickEvent event) throws Exception {
        logic.loadGLRSVMasterTable();
        expandFlag = Boolean.FALSE;
        if ("Collapsed".equals(componentitems.getValue())) {
            generateBtnClick();
        } else {
            treeContainer.removeAllItems();
            List<GLReserveTreeDTO> tempList = logic.loadCompany();
            for (GLReserveTreeDTO tempList1 : tempList) {
                treeContainer.addItem(tempList1);
                treeContainer.setChildrenAllowed(tempList1, true);
                expandLogic(tempList1);
                selectiontable.expandItemsRecursively(tempList1);
            }
        }
        expandFlag = Boolean.TRUE;
    }

    @UiHandler("editbtn")
    public void EditbtnClick(Button.ClickEvent event) throws Exception {
        if (searchtable.getValue() == null) {
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_003), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_015) + (ErrorCodes.PERIOD));

        } else {
            if (navigationValidation()) {
                SessionDTO temp = new SessionDTO();
                temp.setGlReserveMapping((GLReserveMappingDTO) searchtable.getValue());
                GLReserveMappingMainWindow CCWindow = new GLReserveMappingMainWindow(temp, "Edit");
                UI.getCurrent().addWindow(CCWindow);
            } else {
                AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_031), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_016) + (ErrorCodes.PERIOD));

            }

        }
    }

    @UiHandler("copybtn")
    public void CopybtnClick(Button.ClickEvent event) throws Exception {
        if (searchtable.getValue() == null) {
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_004), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_015) + (ErrorCodes.PERIOD));

        } else {
            if (navigationValidation()) {
                SessionDTO temp = new SessionDTO();
                temp.setGlReserveMapping((GLReserveMappingDTO) searchtable.getValue());
                GLReserveMappingMainWindow CCWindow = new GLReserveMappingMainWindow(temp, "Copy");
                UI.getCurrent().addWindow(CCWindow);
            } else {
                AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_031), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_016) + (ErrorCodes.PERIOD));

            }
        }
    }

    @UiHandler("viewbtn")
    public void viewBtnClick(Button.ClickEvent event) throws Exception {
        if (searchtable.getValue() == null) {
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_004), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_015) + (ErrorCodes.PERIOD));
        } else {
            if (navigationValidation()) {
                SessionDTO temp = new SessionDTO();
                temp.setGlReserveMapping((GLReserveMappingDTO) searchtable.getValue());
                GLReserveMappingMainWindow CCWindow = new GLReserveMappingMainWindow(temp, "View");
                UI.getCurrent().addWindow(CCWindow);
            } else {
                AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_031), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_016) + (ErrorCodes.PERIOD));
            }
        }
    }

    @UiHandler("exportbtn")
    public void ExportbtnClick(Button.ClickEvent event) {
        Object[] visibleCol = new Object[]{"businessUnit", "programType", "programsubType", "frequency", "createdBy", "createdDate", "modifiedBy", "modifiedDate", "source"};
        String[] header = new String[]{"Business Unit", "Deduction Type", "Deduction Sub-Type", "Frequency", "Created By", "Created Date", "Modified By", "Modified Date", "Source"};
        try {
            CsvExportforPagedTable.createWorkSheet(header, visibleCol, tableLogic, "Reserve Configuration");
        } catch (Exception e) {
            LOGGER.error(e);
        }

    }

    @UiHandler("businessUnit")
    public void businessUnitDdlbChange(Property.ValueChangeEvent event) throws SystemException, PortalException, Exception {
        if (businessUnit.getValue() != null) {
            HelperDTO businessUnitVal = (HelperDTO) businessUnit.getValue();
            programType.removeAllItems();
            programType = logic.comboBoxAddItems(programType, "gl.loadDType", Boolean.FALSE, String.valueOf(businessUnitVal.getId()), StringUtils.EMPTY);
        } else {
            programType.setValue(null);
        }
    }

    @UiHandler("programType")
    public void deductionTypeDdlbChange(Property.ValueChangeEvent event) throws SystemException, PortalException, Exception {
        if (programType.getValue() != null) {
            HelperDTO programTypeVal = (HelperDTO) programType.getValue();
            programsubType.removeAllItems();
            String compId = StringUtils.EMPTY;
            if (businessUnit.getValue() != null) {
                compId = String.valueOf(((HelperDTO) businessUnit.getValue()).getId());
            }
            programsubType = logic.comboBoxAddItems(programsubType, "gl.loadDSType", Boolean.FALSE, compId, String.valueOf(programTypeVal.getId()));
        } else {
            programsubType.setValue(null);
        }
    }

    private void expandLogic(GLReserveTreeDTO parent) {
        if (parent.getLevelNo() == 1) {
            List<GLReserveTreeDTO> tempList = logic.loadDT(String.valueOf(parent.getLevelId()));
            for (GLReserveTreeDTO tempList1 : tempList) {
                treeContainer.addItem(tempList1);
                treeContainer.setChildrenAllowed(tempList1, true);
                treeContainer.setParent(tempList1, parent);
                expandLogic(tempList1);
            }
        } else if (parent.getLevelNo() == 2) {
            GLReserveTreeDTO preParent = (GLReserveTreeDTO) treeContainer.getParent(parent);
            List<GLReserveTreeDTO> tempList = logic.loadDST(String.valueOf(preParent.getLevelId()), String.valueOf(parent.getLevelId()));
            for (GLReserveTreeDTO tempList1 : tempList) {
                treeContainer.addItem(tempList1);
                treeContainer.setChildrenAllowed(tempList1, false);
                treeContainer.setParent(tempList1, parent);
            }
        }
    }

    private Boolean navigationValidation() {
        Boolean flag = Boolean.FALSE;
        if (selectiontable.getValue() == null) {
            flag = Boolean.TRUE;
        } else if (((GLReserveTreeDTO) selectiontable.getValue()).getLevelNo() < 3) {
            flag = Boolean.FALSE;
        } else {
            GLReserveMappingDTO tempRslt = (GLReserveMappingDTO) searchtable.getValue();
            GLReserveTreeDTO tempTree = (GLReserveTreeDTO) selectiontable.getValue();
            if (tempRslt.getProgramsubType().equals(tempTree.getLevelValue())) {
                GLReserveTreeDTO level2 = (GLReserveTreeDTO) selectiontable.getParent(tempTree);
                if (tempRslt.getProgramType().equals(level2.getLevelValue())) {
                    GLReserveTreeDTO level3 = (GLReserveTreeDTO) selectiontable.getParent(level2);
                    if (tempRslt.getBusinessUnit().equals(level3.getLevelValue())) {
                        flag = Boolean.TRUE;
                    } else {
                        flag = Boolean.FALSE;
                    }
                } else {
                    flag = Boolean.FALSE;
                }
            } else {
                flag = Boolean.FALSE;
            }
        }
        return flag;
    }

    @UiHandler("reasetbtnbottom")
    public void bottomReasetbtnClick(Button.ClickEvent event) {
        new AbstractNotificationUtils() {
            @Override
            public void noMethod() {
               
            }

            @Override
            public void yesMethod() {
                try {
                    tableLogic.loadSetData(binderDto, Boolean.TRUE, Boolean.TRUE);
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            }
        }.getConfirmationMessage(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_010) + (ErrorCodes.QUESTION));

    }

    @UiHandler("deletebtn")
    public void DeletebtnClick(Button.ClickEvent event) throws Exception {
        if (searchtable.getValue() == null) {
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_004), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_015) + (ErrorCodes.PERIOD));

        } else {
            if (navigationValidation()) {
                new AbstractNotificationUtils() {
                    @Override
                    public void noMethod() {
                      
                    }

                    @Override
                    public void yesMethod() {
                        try {
                            boolean deleteFlag = logic.deleteMethodLogic((GLReserveMappingDTO) searchtable.getValue());
                            if (deleteFlag) {
                                Notification.show("Deleted Successfully.");
                            }
                            tableLogic.loadSetData(binderDto, Boolean.FALSE, Boolean.TRUE);
                        } catch (Exception e) {
                            LOGGER.error(e);
                        }
                    }
                }.getConfirmationMessage(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_017) + (ErrorCodes.QUESTION));

            } else {
                AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_031), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_016) + (ErrorCodes.PERIOD));

            }
        }
    }

    private void setFiltersComponents() {
        searchtable.setFilterGenerator(new ExtFilterGenerator() {
            public Container.Filter generateFilter(Object propertyId, Object value) {
                return null;
            }

            public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {
                if (originatingField instanceof ComboBox) {
                    if (originatingField.getValue() != null) {
                        return new SimpleStringFilter(propertyId, String.valueOf(originatingField.getValue()), false, false);
                    } else {
                        return null;
                    }
                }
                return null;
            }

            public void filterRemoved(Object propertyId) {
            }

            public void filterAdded(Object propertyId, Class<? extends Container.Filter> filterType, Object value) {
            }

            public Container.Filter filterGeneratorFailed(Exception reason, Object propertyId, Object value) {
                return null;
            }

            public AbstractField<?> getCustomFilterComponent(Object propertyId) {
                if ("businessUnit".equals(propertyId)) {
                    ComboBox businessUnit = new ComboBox();
                    businessUnit.setHeight("37px");
                    logic.comboBoxAddItems(businessUnit, "gl.getCompany", Boolean.TRUE, StringUtils.EMPTY, StringUtils.EMPTY);
                    return businessUnit;
                }
                if ("programType".equals(propertyId)) {
                    ComboBox businessUnit = new ComboBox();
                    businessUnit.setHeight("37px");
                    logic.comboBoxAddItems(businessUnit, "gl.loadDType", Boolean.TRUE, StringUtils.EMPTY, StringUtils.EMPTY);
                    return businessUnit;
                }
                if ("programsubType".equals(propertyId)) {
                    ComboBox businessUnit = new ComboBox();
                    businessUnit.setHeight("37px");
                    logic.comboBoxAddItems(businessUnit, "gl.loadDSType", Boolean.TRUE, StringUtils.EMPTY, StringUtils.EMPTY);
                    return businessUnit;
                }

                if ("frequency".equals(propertyId)) {
                    ComboBox fre = new ComboBox();
                    fre.setHeight("37px");
                    logic.loadFrequency(fre, true);
                    return fre;
                }
                return null;
            }
        });
        searchtable.setFilterBarVisible(true);
        searchtable.setFilterDecorator(new ExtDemoFilterDecorator());
    }
}
