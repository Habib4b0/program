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
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TableFieldFactory;
import com.vaadin.ui.TextField;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extcustomcheckbox.ExtCustomCheckBox;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.paged.ExtPagedFilterTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author kasiammal.m
 */
public class ReserveAccount extends CustomComponent {

    public static final Logger LOGGER = Logger.getLogger(ReserveAccount.class);
    @UiField("componentmode")
    OptionGroup componentmode;
    @UiField("searchtable")
    public ExtPagedFilterTable searchtable = new ExtPagedFilterTable("Results");
    CustomTableHeaderDTO rightDTO;
    CustomTableHeaderDTO fullHeader = new CustomTableHeaderDTO();
    @UiField("addbtn")
    Button addbtn;
    @UiField("copybtn")
    Button copybtn;
    @UiField("Reasetbtnbottom")
    Button Reasetbtnbottom;
    @UiField("removebtn")
    Button removebtn;
    @UiField("exportbtn")
    Button exportbtn;
    @UiField("exportlayout")
    HorizontalLayout exportlayout;
    @UiField("savebtn")
    Button savebtn;
    private BeanItemContainer<GLReserveMappingDTO> searchContainer = new BeanItemContainer<GLReserveMappingDTO>(GLReserveMappingDTO.class);
    private final ExtCustomTable excelPVSTable = new ExtCustomTable();
    private BeanItemContainer<GLReserveMappingDTO> excelContainer = new BeanItemContainer<GLReserveMappingDTO>(GLReserveMappingDTO.class);
    String mode = StringUtils.EMPTY;
    List<HelperDTO> typeList;
    GLReserveMappingLogic logic = new GLReserveMappingLogic();
    private SessionDTO session = new SessionDTO();
    List<GLReserveMappingDTO> removeList = new ArrayList<GLReserveMappingDTO>();
    GLReserveMappingMain parent;
    List<GLReserveMappingDTO> selectedList = new ArrayList<GLReserveMappingDTO>();
    public boolean saveFlag;
    public static final HelperDTO ddlbDefaultValue = new HelperDTO(0, Constants.IndicatorConstants.SELECT_ONE.getConstant());
    private final Resource excelExportImage = new ThemeResource("../../icons/excel.png");

    public ReserveAccount(SessionDTO session, String mode, GLReserveMappingMain parent) {

        this.mode = mode;
        this.session = session;
        this.parent = parent;
        try {
            setCompositionRoot(Clara.create(getClass().getResourceAsStream("/ReserveAccount.xml"), this));
            configurefields();
        } catch (SystemException ex) {
            LOGGER.error(ex);
        }
    }

    public ReserveAccount() {
    }

    private void configurefields() throws SystemException {
        typeList = logic.getDropDownList("ADJUSTMENT_TYPE", ddlbDefaultValue);
        exportlayout.addComponent(excelPVSTable);
        excelPVSTable.setVisible(false);
        componentmode.addItem("Current");
        componentmode.addItem("History");
        componentmode.select("Current");
        if (Constants.VIEW_MODE.equals(mode) || Constants.EDIT_MODE.equals(mode) || Constants.COPY_MODE.equals(mode)) {
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
        fullHeader = new CustomTableHeaderDTO();
        rightDTO = HeaderUtils.getGlrMappingSearchTableColumns(fullHeader);

        searchtable.setFilterDecorator(new ExtDemoFilterDecorator());
        exportbtn.setIcon(excelExportImage);
        exportbtn.setPrimaryStyleName("link");
        searchtable.setContainerDataSource(searchContainer);
        if (Constants.VIEW_MODE.equals(mode)) {
            searchtable.setEditable(false);
            searchtable.setVisibleColumns(rightDTO.getSingleColumns().subList(1, rightDTO.getSingleColumns().size()).toArray());
            searchtable.setColumnHeaders(rightDTO.getSingleHeaders().subList(1, rightDTO.getSingleHeaders().size()).toArray(new String[rightDTO.getSingleHeaders().size() - 1]));
            addbtn.setEnabled(false);
            removebtn.setEnabled(false);
            copybtn.setEnabled(false);
            savebtn.setEnabled(false);
            Reasetbtnbottom.setEnabled(false);

        } else {
            searchtable.setEditable(true);
            searchtable.setVisibleColumns(rightDTO.getSingleColumns().toArray());
            searchtable.setColumnHeaders(rightDTO.getSingleHeaders().toArray(new String[rightDTO.getSingleHeaders().size()]));
            searchtable.setColumnCheckBox(Constants.CHECK, Boolean.TRUE);
            searchtable.setTableFieldFactory(new TableFieldFactory() {
                public Field<?> createField(Container container, final Object itemId, Object propertyId, Component uiContext) {
                    Field field = null;
                    if (Constants.CHECK.equals(propertyId)) {
                        final ExtCustomCheckBox check = new ExtCustomCheckBox();
                        check.addClickListener(new ExtCustomCheckBox.ClickListener() {
                            public void click(ExtCustomCheckBox.ClickEvent event) {
                                GLReserveMappingDTO trDto = (GLReserveMappingDTO) itemId;
                                Boolean checkValue = check.getValue();
                                if (checkValue) {
                                    selectedList.add(trDto);
                                } else {
                                    selectedList.remove(trDto);
                                }
                                if (!checkValue) {
                                    searchtable.setColumnCheckBox(Constants.CHECK, true, false);
                                }
                            }
                        });
                        session.setSelectedList(selectedList);
                        field = check;
                    }
                    if ("acctType".equals(propertyId)) {
                        ComboBox field1 = new ComboBox();
                        field1.addItem(Constants.IndicatorConstants.SELECT_ONE.getConstant());
                        field1.setNullSelectionAllowed(true);
                        field1.setNullSelectionItemId(Constants.IndicatorConstants.SELECT_ONE.getConstant());
                        field1.select(Constants.IndicatorConstants.SELECT_ONE.getConstant());
                        field1.setImmediate(true);
                        field1.addItems(typeList);
                        field1.setRequired(true);
                        field1.setWidth("140px");
                        field1.setStyleName("acctmandatoryfields");
                        field = field1;
                    }
                    if ("accountID".equals(propertyId)) {
                        TextField field1 = new TextField();
                        field1.setRequired(true);
                        field1.setWidth("140px");
                        field = field1;
                    }
                    if ("accountnumber".equals(propertyId)) {
                        field = new TextField();
                        field.setRequired(true);
                        field.setStyleName("acctmandatoryfields");
                        field.setWidth("140px");
                    }
                    if ("accountdesc".equals(propertyId)) {
                        field = new TextField();
                        field.setWidth("140px");
                    }
                    if ("remark".equals(propertyId)) {
                        field = new TextField();
                        field.setWidth("140px");
                    }
                    if ("subledgertype".equals(propertyId)) {
                        field = new TextField();
                        field.setRequired(true);
                        field.setStyleName("acctmandatoryfields");
                        field.setWidth("140px");
                    }
                    if ("indicator".equals(propertyId)) {
                        ComboBox field1 = new ComboBox();
                        field1.addItem(Constants.IndicatorConstants.SELECT_ONE.getConstant());
                        field1.addItem("Positive");
                        field1.addItem("Negative");
                        field1.setNullSelectionAllowed(true);
                        field1.setNullSelectionItemId(Constants.IndicatorConstants.SELECT_ONE.getConstant());
                        field1.select(Constants.IndicatorConstants.SELECT_ONE.getConstant());
                        field1.setRequired(true);
                        field1.setImmediate(true);
                        field1.setWidth("140px");
                        field1.setStyleName("acctmandatoryfields");
                        field = field1;
                    }

                    if ("periodIndicator".equals(propertyId)) {
                        ComboBox field1 = new ComboBox();
                        field1.addItem(Constants.IndicatorConstants.SELECT_ONE.getConstant());
                        field1.addItem("Current");
                        field1.addItem("Prior");
                        field1.setNullSelectionAllowed(true);
                        field1.setNullSelectionItemId(Constants.IndicatorConstants.SELECT_ONE.getConstant());
                        field1.select(Constants.IndicatorConstants.SELECT_ONE.getConstant());
                        field1.setRequired(true);
                        field1.setImmediate(true);
                        field1.setWidth("140px");
                        field1.setStyleName("acctmandatoryfields");
                        field = field1;
                    }
                    if ("activeFrom".equals(propertyId)) {
                        PopupDateField field1 = new PopupDateField();
                        field1.addStyleName("dateField-align-center");
                        field = field1;
                    }
                    if ("activeTo".equals(propertyId)) {
                        PopupDateField field1 = new PopupDateField();
                        field1.addStyleName("dateField-align-center");
                        field = field1;
                    }
                    return field;
                }
            });
            searchtable.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {
                public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                    for (GLReserveMappingDTO temp : searchContainer.getItemIds()) {
                        searchContainer.getItem(temp).getItemProperty(event.getPropertyId()).setValue(event.isChecked());
                        selectedList.add(temp);
                    }
                    session.setSelectedList(selectedList);
                }
            });
        }

        Object[] visibleColumns = searchtable.getVisibleColumns();
        for (Object column : visibleColumns) {
            searchtable.setColumnWidth(column, 175);
            searchtable.setColumnAlignment(column, ExtCustomTable.Align.LEFT);
        }

        if ("View".equals(mode) || "Edit".equals(mode) || "Copy".equals(mode)) {
            searchContainer.removeAllItems();
            searchContainer.addAll(logic.loadAccDtls(String.valueOf(session.getGlReserveMapping().getGlResMasterSid()), false));
        }
    }

    @UiHandler("addbtn")
    public void AddbtnClick(Button.ClickEvent event) throws PortalException, Exception {
        List<GLReserveMappingDTO> list = new ArrayList<>();
        GLReserveMappingDTO glrMapDTO = new GLReserveMappingDTO();
        if (Constants.EDIT_MODE.equals(mode) || Constants.COPY_MODE.equals(mode)) {
            glrMapDTO.setGlResMasterSid(session.getGlReserveMapping().getGlResMasterSid());
        } else {
            GLReserveMappingDTO binderDTo = new GLReserveMappingDTO();
            String companyId = "%";
            String dtSid = "%";
            String dstSid = "%";
            String fre = "%";
            if (parent.businessUnit.getValue() != null && !Constants.IndicatorConstants.SELECT_ONE.getConstant().equals(parent.businessUnit.getValue())) {
                companyId = String.valueOf(((HelperDTO) parent.businessUnit.getValue()).getId());
                binderDTo.setBusinessUnit((HelperDTO) parent.businessUnit.getValue());
            }
            if (parent.programType.getValue() != null && !Constants.IndicatorConstants.SELECT_ONE.getConstant().equals(parent.businessUnit.getValue())) {
                dtSid = String.valueOf(((HelperDTO) parent.programType.getValue()).getId());
                binderDTo.setProgramType((HelperDTO) parent.programType.getValue());
            }
            if (parent.programsubType.getValue() != null && !Constants.IndicatorConstants.SELECT_ONE.getConstant().equals(parent.businessUnit.getValue())) {
                dstSid = String.valueOf(((HelperDTO) parent.programsubType.getValue()).getId());
                binderDTo.setProgramsubType((HelperDTO) parent.programsubType.getValue());
            }
            binderDTo.setStartIndex(0);
            binderDTo.setEndIndex(2000);
            List<GLReserveMappingDTO> dTOs = logic.loadIndexTableData(binderDTo, Boolean.FALSE);
            if (!dTOs.isEmpty()) {
                session.setGlReserveMapping(dTOs.get(0));
                glrMapDTO.setGlResMasterSid(session.getGlReserveMapping().getGlResMasterSid());
            }
        }
        glrMapDTO.setAcctType(null);
        glrMapDTO.setAccountID(StringUtils.EMPTY);
        glrMapDTO.setAccountnumber(StringUtils.EMPTY);
        glrMapDTO.setAccountdesc(StringUtils.EMPTY);
        glrMapDTO.setRemark(StringUtils.EMPTY);
        glrMapDTO.setSubledgertype(StringUtils.EMPTY);
        glrMapDTO.setIndicator(null);
        list.add(glrMapDTO);
        searchContainer.addAll(list);
        Object[] visibleColumns = searchtable.getVisibleColumns();
        for (Object column : visibleColumns) {
            searchtable.setColumnWidth(column, 175);
            searchtable.setColumnAlignment(column, ExtCustomTable.Align.LEFT);
        }
    }

    @UiHandler("savebtn")
    public void savebtnClick(Button.ClickEvent event) {
        try {
            if (parent.businessUnit.getValue() == null && parent.programType.getValue() == null && parent.programsubType.getValue() == null && parent.frequency.getValue() == null) {
                AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_018), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_019).concat(ErrorCodes.PERIOD));
            } else {
                saveButtonLogic(StringUtils.EMPTY);
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    @UiHandler("removebtn")
    public void removebtnClick(Button.ClickEvent event) {

        Collection<?> list = searchtable.getItemIds();
        final List<GLReserveMappingDTO> idList = new ArrayList<GLReserveMappingDTO>();
        if (list != null) {

            for (final Iterator<?> it = searchtable.getItemIds().iterator(); it.hasNext();) {
                GLReserveMappingDTO item = (GLReserveMappingDTO) it.next();
                Boolean checked = (Boolean) searchContainer.getContainerProperty(item, Constants.CHECK).getValue();
                if (checked) {
                    idList.add(item);
                }
            }
            if (idList.size() > 0) {
                new AbstractNotificationUtils() {
                    @Override
                    public void noMethod() {
                       
                    }

                    @Override
                    public void yesMethod() {
                        try {
                            for (GLReserveMappingDTO obj : idList) {
                                searchContainer.removeItem(obj);
                                searchtable.removeItem(obj);
                                removeList.add(obj);
                                selectedList.remove(obj);
                            }
                            session.setSelectedList(selectedList);
                        } catch (Exception ex) {
                            LOGGER.error(ex);
                        }
                    }
                }.getConfirmationMessage(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_020), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_021).concat(ErrorCodes.QUESTION));

            } else {
                AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_020), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_022).concat(ErrorCodes.PERIOD));
            }

        }
    }

    @UiHandler("copybtn")
    public void copybtnClick(Button.ClickEvent event) {
        Collection<?> list = searchtable.getItemIds();
        final List<GLReserveMappingDTO> DTOlist = new ArrayList<GLReserveMappingDTO>();
        final List<Object> idList = new ArrayList<Object>();
        if (list != null) {

            for (final Iterator<?> it = searchtable.getItemIds().iterator(); it.hasNext();) {
                Object item = it.next();
                Boolean checked = (Boolean) searchContainer.getContainerProperty(item, Constants.CHECK).getValue();
                if (checked) {
                    idList.add(item);
                    GLReserveMappingDTO GLReserveMappingDTO = new GLReserveMappingDTO();
                    GLReserveMappingDTO.setGlResMasterSid(session.getGlReserveMapping().getGlResMasterSid());
                    GLReserveMappingDTO.setAcctType((HelperDTO) searchContainer.getContainerProperty(item, "acctType").getValue());
                    GLReserveMappingDTO.setAccountID(searchContainer.getContainerProperty(item, "accountID").getValue().toString());
                    GLReserveMappingDTO.setAccountnumber(searchContainer.getContainerProperty(item, "accountnumber").getValue().toString());
                    GLReserveMappingDTO.setAccountdesc(searchContainer.getContainerProperty(item, "accountdesc").getValue().toString());
                    GLReserveMappingDTO.setRemark(searchContainer.getContainerProperty(item, "remark").getValue().toString());
                    GLReserveMappingDTO.setSubledgertype(searchContainer.getContainerProperty(item, "subledgertype").getValue().toString());
                    if (searchContainer.getContainerProperty(item, "indicator").getValue() == null) {
                        GLReserveMappingDTO.setIndicator(null);
                    } else {
                        GLReserveMappingDTO.setIndicator(searchContainer.getContainerProperty(item, "indicator").getValue().toString());
                    }
                    if (searchContainer.getContainerProperty(item, "periodIndicator").getValue() == null) {
                        GLReserveMappingDTO.setIndicator(null);
                    } else {
                        GLReserveMappingDTO.setPeriodIndicator(searchContainer.getContainerProperty(item, "periodIndicator").getValue().toString());
                    }
                    DTOlist.add(GLReserveMappingDTO);

                }
            }
            if (idList.size() > 0) {
                new AbstractNotificationUtils() {
                    @Override
                    public void noMethod() {
                       
                    }

                    @Override
                    public void yesMethod() {
                        try {
                            searchContainer.addAll(DTOlist);
                        } catch (Exception ex) {
                            LOGGER.error(ex);
                        }
                    }
                }.getConfirmationMessage(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_023), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_024).concat(ErrorCodes.QUESTION));
            } else {
                AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_023), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_025).concat(ErrorCodes.PERIOD));
            }
        }

    }

    @UiHandler("componentmode")
    public void componentmodeChange(Property.ValueChangeEvent event) throws Exception {
        fullHeader = new CustomTableHeaderDTO();
        rightDTO = HeaderUtils.getGlrMappingSearchTableColumns(fullHeader);
        if (componentmode.getValue().toString().equalsIgnoreCase("History")) {
            searchtable.setEditable(false);
            searchtable.setVisibleColumns(rightDTO.getSingleColumns().subList(1, rightDTO.getSingleColumns().size()).toArray());
            searchtable.setColumnHeaders(rightDTO.getSingleHeaders().subList(1, rightDTO.getSingleHeaders().size()).toArray(new String[rightDTO.getSingleHeaders().size() - 1]));
            addbtn.setEnabled(false);
            copybtn.setEnabled(false);
            removebtn.setEnabled(false);
            if (!searchtable.getItemIds().isEmpty()) {
                searchContainer.removeAllItems();
                searchContainer.addAll(logic.loadAccDtls(String.valueOf(session.getGlReserveMapping().getGlResMasterSid()), true));
            }
        } else {
            if (Constants.EDIT_MODE.equals(mode)) {
                searchContainer = new BeanItemContainer<>(GLReserveMappingDTO.class);
                searchtable.setEditable(true);
                searchtable.setContainerDataSource(searchContainer);
                searchtable.setVisibleColumns(rightDTO.getSingleColumns().toArray());
                searchtable.setColumnHeaders(rightDTO.getSingleHeaders().toArray(new String[rightDTO.getSingleHeaders().size()]));
                searchContainer.addAll(logic.loadAccDtls(String.valueOf(session.getGlReserveMapping().getGlResMasterSid()), false));
                addbtn.setEnabled(true);
                copybtn.setEnabled(true);
                removebtn.setEnabled(true);
                Reasetbtnbottom.setEnabled(true);
            }
            if (Constants.VIEW_MODE.equals(mode)) {
                searchContainer.removeAllItems();
                searchContainer = new BeanItemContainer<>(GLReserveMappingDTO.class);
                searchtable.setEditable(false);
                searchtable.setContainerDataSource(searchContainer);
                searchtable.setVisibleColumns(rightDTO.getSingleColumns().subList(1, rightDTO.getSingleColumns().size()).toArray());
                searchtable.setColumnHeaders(rightDTO.getSingleHeaders().subList(1, rightDTO.getSingleHeaders().size()).toArray(new String[rightDTO.getSingleHeaders().size() - 1]));
                searchContainer.addAll(logic.loadAccDtls(String.valueOf(session.getGlReserveMapping().getGlResMasterSid()), false));
                addbtn.setEnabled(false);
                copybtn.setEnabled(false);
                removebtn.setEnabled(false);
                Reasetbtnbottom.setEnabled(false);
            }

        }
    }

    @UiHandler("Reasetbtnbottom")
    public void bottomReasetClick(Button.ClickEvent event) {
        new AbstractNotificationUtils() {
            @Override
            public void noMethod() {

            }

            @Override
            public void yesMethod() {
                try {
                    if (searchContainer != null) {
                        searchContainer.removeAllItems();
                    }
                    searchContainer = new BeanItemContainer<>(GLReserveMappingDTO.class);
                    searchtable.setEditable(true);
                    searchtable.setContainerDataSource(searchContainer);
                    searchtable.setVisibleColumns(rightDTO.getSingleColumns().toArray());
                    searchtable.setColumnHeaders(rightDTO.getSingleHeaders().toArray(new String[rightDTO.getSingleHeaders().size()]));
                    searchContainer.addAll(logic.loadAccDtls(String.valueOf(session.getGlReserveMapping().getGlResMasterSid()), false));
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            }
        }.getConfirmationMessage(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_001), "Are you sure you want to reset the page to default/previous values? ");

    }

    @UiHandler("exportbtn")
    public void ExportbtnClick(Button.ClickEvent event) {
        Collection<?> list = searchtable.getItemIds();
        Map<String, String> formatter = new HashMap<String, String>();
        final List<GLReserveMappingDTO> DTOlist = new ArrayList<GLReserveMappingDTO>();
        final List<Object> idList = new ArrayList<Object>();
        excelContainer.removeAllItems();
        if (list != null) {

            for (final Iterator<?> it = searchtable.getItemIds().iterator(); it.hasNext();) {
                Object item = it.next();
                Boolean checked = (Boolean) searchContainer.getContainerProperty(item, Constants.CHECK).getValue();
                idList.add(item);
                GLReserveMappingDTO GLReserveMappingDTO = new GLReserveMappingDTO();
                GLReserveMappingDTO.setAcctType((HelperDTO) searchContainer.getContainerProperty(item, "acctType").getValue());
                GLReserveMappingDTO.setAccountID(searchContainer.getContainerProperty(item, "accountID").getValue().toString());
                GLReserveMappingDTO.setAccountnumber(searchContainer.getContainerProperty(item, "accountnumber").getValue().toString());
                GLReserveMappingDTO.setAccountdesc(searchContainer.getContainerProperty(item, "accountdesc").getValue().toString());
                GLReserveMappingDTO.setRemark(searchContainer.getContainerProperty(item, "remark").getValue().toString());
                GLReserveMappingDTO.setSubledgertype(searchContainer.getContainerProperty(item, "subledgertype").getValue().toString());
                GLReserveMappingDTO.setIndicator(searchContainer.getContainerProperty(item, "indicator").getValue().toString());
                GLReserveMappingDTO.setPeriodIndicator(searchContainer.getContainerProperty(item, "periodIndicator").getValue().toString());
                DTOlist.add(GLReserveMappingDTO);

            }

        }
        excelContainer.addAll(DTOlist);
        excelPVSTable.setContainerDataSource(excelContainer);

        excelPVSTable.setVisibleColumns(rightDTO.getSingleColumns().subList(1, rightDTO.getSingleColumns().size()).toArray());
        excelPVSTable.setColumnHeaders(rightDTO.getSingleHeaders().subList(1, rightDTO.getSingleHeaders().size()).toArray(new String[rightDTO.getSingleHeaders().size() - 1]));
        VaadinSession.getCurrent().setAttribute("excelClose", "true");
        ExportToExcel excel = new ExportToExcel(new ExtCustomTableHolder(excelPVSTable), "Reserve Accounts", "Reserve Accounts", "ReserveAccounts.xls", false, formatter, "Reserve Accounts");
        excel.export();
    }

    public boolean isSaveFlag() {
        return saveFlag;
    }

    public void setSaveFlag(boolean saveFlag) {
        this.saveFlag = saveFlag;
    }

    public void saveButtonLogic(String businessProcess) {
        Boolean saveFlag = true;
        for (GLReserveMappingDTO temp : searchContainer.getItemIds()) {
            if (temp.getAcctType() == null || StringUtils.isBlank(temp.getAcctType().getDescription())) {
                saveFlag = false;
                break;
            }
            if (StringUtils.isBlank(temp.getAccountID())) {
                saveFlag = false;
                break;
            }
            if (StringUtils.isBlank(temp.getIndicator())) {
                saveFlag = false;
                break;
            }
            if (StringUtils.isBlank(temp.getPeriodIndicator())) {
                saveFlag = false;
                break;
            }
            if (StringUtils.isBlank(temp.getAccountnumber())) {
                saveFlag = false;
                break;
            }
        }
        if (saveFlag) {
            Boolean dupFlag = true;
            List<String> availableList = new ArrayList<String>();
            for (GLReserveMappingDTO temp : searchContainer.getItemIds()) {
                String tempValue = temp.getAcctType() + temp.getAccountID() + temp.getIndicator() + temp.getPeriodIndicator() + temp.getAccountnumber();
                if (!availableList.contains(tempValue)) {
                    availableList.add(tempValue);
                } else {
                    dupFlag = false;
                    break;
                }
            }
            if (dupFlag) {
                logic.saveFrequencyLogic(String.valueOf(parent.frequency.getValue()), String.valueOf(session.getGlReserveMapping().getGlResMasterSid()));
                logic.removeLogic(removeList);
                removeList.removeAll(removeList);
                logic.saveDetails(searchContainer.getItemIds(), businessProcess);
                this.saveFlag = true;
                setSaveFlag(true);
                AbstractNotificationUtils.getInfoNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_027), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_029));
            } else {
                this.saveFlag = false;
                setSaveFlag(false);
                AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_026), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_028).concat(ErrorCodes.PERIOD));
            }
        } else {
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_026), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_019).concat(ErrorCodes.PERIOD));
        }

    }
}
