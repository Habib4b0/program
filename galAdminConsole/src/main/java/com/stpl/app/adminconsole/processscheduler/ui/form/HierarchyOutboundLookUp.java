package com.stpl.app.adminconsole.processscheduler.ui.form;

import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.app.adminconsole.common.util.CommonUIUtil;
import org.jboss.logging.Logger;

import com.stpl.app.adminconsole.processscheduler.dto.HierarchyDefinitionDTO;
import com.stpl.app.adminconsole.processscheduler.dto.OutboundFilterGenerator;
import com.stpl.app.adminconsole.processscheduler.dto.OutboundTableDTO;
import com.stpl.app.adminconsole.processscheduler.logic.OutboundLogic;
import com.stpl.app.adminconsole.processscheduler.logic.tableLogic.HierarchyTableLogic;
import com.stpl.app.adminconsole.processscheduler.util.CommonUtil;
import com.stpl.app.adminconsole.util.CommonUtils;
import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.stpl.app.adminconsole.util.HelperListUtil;
import com.stpl.app.adminconsole.util.ResponsiveUtils;
import com.stpl.app.customtreecontainer.CustomTreeContainer;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.ui.errorhandling.ErrorLabel;
import com.stpl.app.util.ConstantUtil;
import com.stpl.ifs.ui.util.CommonUIUtils;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.stpl.ifs.util.ExtCustomTableHolder;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.converter.Converter;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.ExtCustomTreeTable;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 * The Class HierarchyOutboundLookUp.
 *
 * @author vinodhini
 */
public final class HierarchyOutboundLookUp extends Window {

    private static final Logger LOGGER = Logger.getLogger(HierarchyOutboundLookUp.class);

    @UiField("errorMsg")
    private ErrorLabel errorMsg;
    @UiField("hierarchyType")
    private OptionGroup hierarchyType;
    @UiField("hierarchyName")
    private TextField hierarchyName;
    @UiField("hierarchyCategory")
    private ComboBox hierarchyCategory;   
    @UiField("createdDateFrom")
    private PopupDateField createdDateFrom;
    @UiField("createdDateTo")
    private PopupDateField createdDateTo;

    @UiField("searchBtn")
    private Button searchBtn;

    @UiField("closeBtn")
    private Button closeBtn;
    @UiField("outboundBtn")
    private Button outboundBtn;
    @UiField("tableLayout")
    private VerticalLayout tableLayout;
    @UiField("controlLayout")
    private HorizontalLayout controlLayout;

    private ErrorfulFieldGroup binder;
    HierarchyTableLogic tableLogic = new HierarchyTableLogic();
    protected ExtPagedTable resultTable = new ExtPagedTable(tableLogic);

    protected final BeanItemContainer<HierarchyDefinitionDTO> resultsContainer = new BeanItemContainer<>(HierarchyDefinitionDTO.class);

    Map<String, HierarchyDefinitionDTO> checkedHierarchy = new HashMap<>();
    ExtCustomTreeTable exceltable = new ExtCustomTreeTable();
    CustomTreeContainer<OutboundTableDTO> excelResultBeanContainer = new CustomTreeContainer<>(OutboundTableDTO.class);
    CustomTableHeaderDTO fullHeader = new CustomTableHeaderDTO();
    String hierType;
    OutboundLogic outboundLogic = new OutboundLogic();

    public void setBinder(final ErrorfulFieldGroup binder) {
        this.binder = binder;
    }

    public HierarchyOutboundLookUp() {
        super("Hierarchy Definition Outbound Process");
        LOGGER.info(" HierarchyOutboundLookUp constructor started");
        init();
        LOGGER.info("HierarchyOutboundLookUp constructor Ended");
    }

    public void init() {
        LOGGER.info("init method started");
        center();
        setClosable(true);
        setModal(true);
        setWidth("850px");
        setHeight("759px");
        setContent(Clara.create(getClass().getResourceAsStream("/clara/hierarchyOutbound.xml"), this));
        addStyleName("bootstrap-ui");
        addStyleName("bootstrap");
        addStyleName("bootstrap-forecast bootstrap-nm");
        getBinder();
        HelperListUtil helperListUtil = HelperListUtil.getInstance();
        helperListUtil.loadValuesWithListName("outBoundProcess");

        configureFields();
        addTable();
        LOGGER.info("init method Ended");
    }

    private ErrorfulFieldGroup getBinder() {
        LOGGER.info("getBinder method started");
        binder = new ErrorfulFieldGroup(new BeanItem<>(new HierarchyDefinitionDTO()));
        binder.setBuffered(true);
        binder.bindMemberFields(this);
        binder.setErrorDisplay(errorMsg);
        LOGGER.info("getBinder method returns binder");
        return binder;
    }


    @UiHandler("closeBtn")
    public void closeBtnLogic(Button.ClickEvent event) {
        close();
    }

    public void addTable() {
        LOGGER.info("addTable method started");

        tableLayout.addComponent(resultTable);
        tableLogic.setContainerDataSource(resultsContainer);
        resultTable.setEditable(true);
        tableLogic.setPageLength(5);
        tableLogic.sinkItemPerPageWithPageLength(false);
        resultTable.setVisibleColumns(CommonUIUtil.HIERARCHY_OUTBOUND_SEARCH_COLUMNS);
        resultTable.setColumnHeaders(CommonUIUtil.HIERARCHY_OUTBOUND_SEARCH_HEADER);
        resultTable.setFilterBarVisible(true);
        resultTable.setSizeFull();
        resultTable.setImmediate(true);
        resultTable.setPageLength(5);
        resultTable.setFilterDecorator(new ExtDemoFilterDecorator());
        resultTable.setFilterGenerator(new OutboundFilterGenerator());
        resultTable.addStyleName("filtertable");
        resultTable.addStyleName("table-header-normal");
        resultTable.setColumnCheckBox("check", true);
        resultTable.getFilterField("check").setVisible(false);
        resultTable.setTableFieldFactory(new DefaultFieldFactory() {

            private static final long serialVersionUID = 1L;

            @Override
            public Field<?> createField(final Container container,
                    final Object itemId, final Object propertyId,
                    final Component uiContext) {
                try {
                    final HierarchyDefinitionDTO tableDto = (HierarchyDefinitionDTO) itemId;
                    if ("check".equals(propertyId)) {
                        final CheckBox check = new CheckBox();
                        if (checkedHierarchy.get(tableDto.getHierarchyDefinitionSystemId()) != null) {
                            check.setValue(true);
                            tableDto.setCheck(true);
                        }
                        check.setEnabled(true);
                        check.setCaption(ConstantsUtils.EMPTY);
                        check.addValueChangeListener(new Property.ValueChangeListener() {
                            @Override
                            public void valueChange(Property.ValueChangeEvent event) {

                                if ((boolean) check.getValue()) {
                                    checkedHierarchy.put(tableDto.getHierarchyDefinitionSystemId(), tableDto);
                                    tableDto.setCheck(true);
                                } else if (!tableDto.isCheck()) {
                                    checkedHierarchy.remove(tableDto.getHierarchyDefinitionSystemId());
                                    tableDto.setCheck(false);
                                }
                            }
                        });
                        return check;
                    }

                } catch (Property.ReadOnlyException | Converter.ConversionException exception) {
                    LOGGER.error(exception);

                }
                return null;

            }
        });
        resultTable.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {
            @Override
            public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                if ("check".equals(event.getPropertyId().toString())) {
                    if (event.isChecked()) {
                        checkedHierarchy.clear();
                        loadGrid(true);
                        // this setCurrentPage is used to refresh the lazy conatiner
                        resultTable.setCurrentPage(resultTable.getCurrentPage());
                        resultTable.setColumnCheckBox("check", true, true);
                    } else {
                        checkedHierarchy.clear();
                        loadGrid(false);
                        // this setCurrentPage is used to refresh the lazy conatiner
                        resultTable.setCurrentPage(resultTable.getCurrentPage());
                        resultTable.setColumnCheckBox("check", true, false);

                    }
                }
            }
        });
        resultTable.setWidth("798px");
        ResponsiveUtils.getResponsiveControls(tableLogic.createControls(), controlLayout);
        tableLayout.addComponent(controlLayout);
        LOGGER.info("addTable method returns table");
    }

    
    public void configureFields() {
        LOGGER.info("configureFields method started");
        try {
            hierarchyType.setImmediate(true);
            hierarchyType.addItem(ConstantsUtils.PRIMARY);
            hierarchyType.addItem(ConstantsUtils.SECONDARY);
            hierarchyType.select(ConstantsUtils.PRIMARY);
            hierarchyName.focus();

            CommonUtils commonUtil = CommonUtils.getInstance();
            commonUtil.loadComboBox(hierarchyCategory, CommonUIUtil.HIERARCHY_CATEGORY, false);
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    @UiHandler("searchBtn")
    public void btnSearchLogic(Button.ClickEvent event) {
        binder.getErrorDisplay().clearError();
        try {

            checkedHierarchy.clear();
            binder.commit();
            resultTable.setColumnCheckBox("check", true, false);
            loadGrid(false);

            if (tableLogic.isResultsEmpty()) {
                CommonUIUtils.successNotification(ConstantUtil.NO_RESULTS_COMPLETED);
            } else {
                CommonUIUtils.successNotification(ConstantUtil.SEARCH_COMPLETED);
            }

        } catch (Exception exception) {
            LOGGER.error(exception);
        }
    }

   
    private void loadGrid(boolean isCheckAll) {
        try {
            hierType = String.valueOf(hierarchyType.getValue());
            tableLogic.configureSearchData(binder, hierType, isCheckAll);
            resultTable.setFilterDecorator(new ExtDemoFilterDecorator());
            resultTable.setFilterGenerator(new OutboundFilterGenerator());
            resultTable.setImmediate(true);
            resultTable.setWidth("798px");
            resultTable.addStyleName("TableCheckBox");
            resultTable.setColumnCheckBox("check", true);
            resultTable.getFilterField("check").setVisible(false);
            resultTable.setCurrentPage(resultTable.getCurrentPage());
            resultTable.markAsDirtyRecursive();
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

   
    @UiHandler("outboundBtn")
    public void outboundBtn(final Button.ClickEvent event) {
        LOGGER.info("outboundBtn click listener started");
        try {
            if (resultTable.size() != 0) {
                 Long startTime = System.currentTimeMillis();
            StringBuilder ids = new StringBuilder(StringUtils.EMPTY);

            String checkedIds=StringUtils.EMPTY;
            boolean checkedAll=false;
            if (resultTable.getColumnCheckBox("check")) {    
                checkedAll=true;

            } else if (!checkedHierarchy.isEmpty()) {
                for (String keyId : checkedHierarchy.keySet()) {
                    ids.append(",").append(keyId);
                }
                checkedIds = ids.toString().replaceFirst(",", ConstantsUtils.EMPTY);
            }

            
           LOGGER.info(" checked Ids="+checkedIds);
            if (StringUtils.isNotBlank(checkedIds)) {
                excelLogic(checkedIds, false);
            }
            if (checkedAll) {
                excelLogic(checkedIds, true);
            }
            Long endTime = System.currentTimeMillis();
            LOGGER.info("____Hierarchy Definition______Total Time taken===in milli sesconds=" + (endTime - startTime));
            LOGGER.info("_____Hierarchy Definition_____Total Time taken==in seconds==" + TimeUnit.MILLISECONDS.toSeconds(endTime - startTime));

            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        LOGGER.info("outboundBtn click listener ends");
    }

    private void configureExcelResultTable(String checkedIds,boolean isCheckAll) {
        exceltable = new ExtCustomTreeTable();
        excelResultBeanContainer = new CustomTreeContainer<>(OutboundTableDTO.class);
        fullHeader = new CustomTableHeaderDTO();
        int maxLevel = 0;
        
        List levelCount;
        if(isCheckAll){
            levelCount = outboundLogic.hierarchyQueryResults(checkedIds, "getHierarchyDefinitionOutboundAllMaxLevel",isCheckAll);
        }else{
            levelCount = outboundLogic.hierarchyQueryResults(checkedIds, "getHierarchyDefinitionOutboundMaxLevel",isCheckAll);
        }
        if (levelCount != null && !levelCount.isEmpty()) {
            maxLevel = Integer.parseInt((StringUtils.isNotBlank(String.valueOf(levelCount.get(0))) ? String.valueOf(levelCount.get(0)) : "0"));
        }
        LOGGER.info(" Maximum Level=" + maxLevel);
        CommonUtil.getHdOutboundCalculatedColumns(fullHeader, maxLevel);
        excelResultBeanContainer.setColumnProperties(fullHeader.getProperties());
        tableLayout.addComponent(exceltable);
        exceltable.setRefresh(false);
        exceltable.setVisible(false);
        exceltable.setContainerDataSource(excelResultBeanContainer);
        exceltable.setVisibleColumns(fullHeader.getSingleColumns().toArray());
        exceltable.setColumnHeaders(fullHeader.getSingleHeaders().toArray(new String[fullHeader.getSingleHeaders().size()]));
        exceltable.setDoubleHeaderVisible(true);
        exceltable
                .setDoubleHeaderVisibleColumns(fullHeader.getDoubleColumns().toArray());
        exceltable
                .setDoubleHeaderColumnHeaders(fullHeader.getDoubleHeaders().toArray(new String[fullHeader.getDoubleHeaders().size()]));

        exceltable.setDoubleHeaderMap(fullHeader.getDoubleHeaderMaps());
        exceltable.setRefresh(true);
    }

    private void loadExcelResultTable(String checkedIds,boolean isCheckAll) {
        excelResultBeanContainer.removeAllItems();

        List<OutboundTableDTO> resultList;
        if (isCheckAll) {
            resultList = outboundLogic.getHierarchyCheckedAllResults(binder, hierType);
        } else {
            resultList = outboundLogic.hierarchyDefinitionOutbound(checkedIds, "getHierarchyDefinitionOutbound", isCheckAll);
        }

        excelResultBeanContainer.addAll(resultList);
    }

    private void excelLogic(String checkedIds, boolean isCheckAll) {
        configureExcelResultTable(checkedIds, isCheckAll);
        loadExcelResultTable(checkedIds, isCheckAll);
        VaadinSession.getCurrent().setAttribute(ConstantsUtils.EXCEL_CLOSE, "true");
        ExcelExport exp = new ExcelExport(new ExtCustomTableHolder(exceltable), "Hierarchy Definition Outbound Process", "Hierarchy Definition Outbound Process", "Hierarchy_Definition_Outbound_Process.xls", false);
        exp.export();
        tableLayout.removeComponent(exceltable);
    }
    
}
