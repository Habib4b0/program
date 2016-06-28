/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.processscheduler.ui.form;

import com.stpl.app.adminconsole.common.util.CommonUIUtil;
import com.stpl.app.adminconsole.processscheduler.dto.OutboundFilterGenerator;
import com.stpl.app.adminconsole.processscheduler.dto.OutboundTableDTO;
import com.stpl.app.adminconsole.processscheduler.dto.RelationshipOutboundDTO;
import com.stpl.app.adminconsole.processscheduler.logic.OutboundLogic;
import com.stpl.app.adminconsole.processscheduler.logic.tableLogic.RelationshipOutboundTableLogic;
import com.stpl.app.adminconsole.relationshipbuilder.ui.lazyload.HierarchyNameContainer;
import com.stpl.app.adminconsole.relationshipbuilder.ui.lazyload.HierarchyNameCriteria;
import com.stpl.app.adminconsole.util.AbstractNotificationUtils;
import com.stpl.app.adminconsole.util.CommonUtils;

import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.stpl.app.adminconsole.util.ErrorCodeUtil;
import com.stpl.app.adminconsole.util.ErrorCodes;
import com.stpl.app.adminconsole.util.HelperListUtil;
import com.stpl.app.adminconsole.util.Message;
import com.stpl.app.adminconsole.util.MessageUtil;
import com.stpl.app.adminconsole.util.ResponsiveUtils;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.ConstantUtil;
import com.stpl.app.ui.errorhandling.ErrorLabel;
import com.stpl.ifs.ui.DateToStringConverter;
import com.stpl.ifs.ui.util.CommonUIUtils;
import com.stpl.ifs.util.ExcelExportforBB;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import static com.vaadin.server.Sizeable.UNITS_PERCENTAGE;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.LazyContainer;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author satheesh.n
 */
public class RelationshipOutboundProcess extends Window {

    private static final Logger LOGGER = Logger.getLogger(RelationshipOutboundProcess.class);
    @UiField("relationshipName")
    private TextField relationshipName;
    @UiField("hierarchyNameDDLB")
    private ComboBox hierarchyNameDDLB;
    @UiField("relationshipDescription")
    private TextField relationshipDescription;
    @UiField("startDateFrom")
    private PopupDateField startDateFrom;
    @UiField("startDateTo")
    private PopupDateField startDateTo;
    @UiField("relationshipType")
    private OptionGroup relationshipType;
    @UiField("creationDateFrom")
    private PopupDateField creationDateFrom;
    @UiField("creationDateTo")
    private PopupDateField creationDateTo;
    @UiField("searchBtn")
    private Button searchBtn;
    @UiField("outboundBtn")
    private Button outboundBtn;
    @UiField("closeBtn")
    private Button closeBtn;
    @UiField("tableLayout")
    private VerticalLayout tableLayout;
    @UiField("controlLayout")
    private HorizontalLayout controlLayout;
    @UiField("errorMsg")
    public ErrorLabel errorMsg;
    RelationshipOutboundTableLogic tableLogic = new RelationshipOutboundTableLogic();
    private final OutboundLogic searchLogic = new OutboundLogic();
    private ExtPagedTable resultTable = new ExtPagedTable(tableLogic);
    private BeanItemContainer<RelationshipOutboundDTO> resultBean = new BeanItemContainer<>(RelationshipOutboundDTO.class);
    final CommonUtils commonsUtil = new CommonUtils();
    private ErrorfulFieldGroup binder;
    Map<String, RelationshipOutboundDTO> checkedHierarchy = new HashMap<>();
    OutboundLogic outboundLogic = new OutboundLogic();
    List<OutboundTableDTO> rbCsvList = null;

    public RelationshipOutboundProcess() {
        super("Relationship Builder Outbound Process");
        try {
            init();
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    public ErrorfulFieldGroup getBinder() {
        final RelationshipOutboundDTO bean = new RelationshipOutboundDTO();
        binder = new ErrorfulFieldGroup(new BeanItem<>(bean));
        binder.setBuffered(true);
        binder.bindMemberFields(this);
        binder.setErrorDisplay(errorMsg);
        return binder;
    }

    public void init() throws Exception {

        setContent(Clara.create(getClass().getResourceAsStream("/clara/relationshipBuilderoutbound.xml"), this));
        addStyleName("bootstrap-ui");
        addStyleName("bootstrap");
        addStyleName("bootstrap-forecast bootstrap-nm");
        center();
        setClosable(true);
        setModal(true);
        setWidth("1020px");
        setHeight("965px");
        getBinder();
        HelperListUtil helperListUtil = HelperListUtil.getInstance();
        helperListUtil.loadValuesWithListName("outBoundProcess");
        configureFields();
        configureTable();
    }

    public void configureFields() throws SystemException, Exception {
        try {
            relationshipType.addItem("Primary");
            relationshipType.addItem("Secondary");
            relationshipType.select("Primary");
            relationshipType.addItem("Primary");
            relationshipType.setImmediate(true);
            final LazyContainer hierarchyNameContainer = new LazyContainer(HelperDTO.class, new HierarchyNameContainer(null), new HierarchyNameCriteria());
            commonsUtil.loadLazyComboBox(hierarchyNameDDLB, hierarchyNameContainer);
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    private void configureTable() throws Exception {

        tableLayout.addComponent(resultTable);
        ResponsiveUtils.getResponsiveControls(tableLogic.createControls(), controlLayout);
        tableLayout.addComponent(controlLayout);

        tableLogic.setContainerDataSource(resultBean);
        tableLogic.setPageLength(10);
        tableLogic.sinkItemPerPageWithPageLength(false);

        resultTable.setImmediate(true);
        resultTable.setWidth(99, UNITS_PERCENTAGE);

        binder.getErrorDisplay().clearError();

        resultTable.setSelectable(true);
        resultTable.markAsDirty();
        resultTable.setComponentError(null);
        resultTable.setFilterBarVisible(true);
        resultTable.setFilterDecorator(new ExtDemoFilterDecorator());
        resultTable.setFilterGenerator(new OutboundFilterGenerator());
        resultTable.setValidationVisible(false);
        resultTable.addStyleName("TableCheckBox");
        resultTable.addStyleName("filterbar");
        resultTable.setVisibleColumns(CommonUIUtil.RELATIONSHIP_OUTBOUND_SEARCH_COLUMNS);
        resultTable.setColumnHeaders(CommonUIUtil.RELATIONSHIP_OUTBOUND_SEARCH_HEADER);
        resultTable.setEditable(true);
        resultTable.setColumnCheckBox("check", true);
        resultTable.getFilterField("check").setVisible(false);
        resultTable.setConverter(ConstantsUtils.CREATED_DATE, new DateToStringConverter());
        resultTable.setConverter(ConstantsUtils.MODIFIED_DATE, new DateToStringConverter());
        resultTable.setConverter(ConstantsUtils.START_DATE, new DateToStringConverter());
        resultTable.setFilterBarVisible(true);
        resultTable.setTableFieldFactory(new DefaultFieldFactory() {

            private static final long serialVersionUID = 1L;

            public Field<?> createField(final Container container,
                    final Object itemId, final Object propertyId,
                    final Component uiContext) {
                try {
                    final RelationshipOutboundDTO tableDto = (RelationshipOutboundDTO) itemId;
                    if ("check".equals(propertyId)) {
                        final CheckBox check = new CheckBox();
                        if (checkedHierarchy.get(tableDto.getRbSystemId()) != null) {
                            check.setValue(true);
                            tableDto.setCheck(true);
                        }
                        check.setEnabled(true);
                        check.setCaption(ConstantsUtils.EMPTY);
                        check.addValueChangeListener(new Property.ValueChangeListener() {
                            @Override
                            public void valueChange(Property.ValueChangeEvent event) {

                                if ((boolean) check.getValue()) {
                                    checkedHierarchy.put(tableDto.getRbSystemId(), tableDto);
                                    tableDto.setCheck(true);
                                } else if (!tableDto.isCheck()) {
                                    checkedHierarchy.remove(tableDto.getRbSystemId());
                                    tableDto.setCheck(false);
                                }
                            }
                        });
                        return check;
                    }

                } catch (Exception exception) {
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

    }

    @UiHandler("searchBtn")
    public void btnSearchLogic(Button.ClickEvent event) {
        binder.getErrorDisplay().clearError();
        if (!searchLogic.checkSearchCriteria(binder)) {
            AbstractNotificationUtils.getWarningNotification(MessageUtil.getMessage(Message.SEARCH_CRITERIA_HEADER), MessageUtil.getMessage(Message.SEARCH_CRITERIA_MESSAGE));
        } else {

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

            } catch (FieldGroup.CommitException commit) {
                tableLogic.clearAll();
                tableLogic.getFilters().clear();
                resultTable.setFilterDecorator(new ExtDemoFilterDecorator());
                resultTable.setFilterGenerator(new OutboundFilterGenerator());
                LOGGER.error(commit);
            } catch (Exception exception) {
                AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1004));
                LOGGER.error(exception);
            }
        }

    }

    private void loadGrid(boolean isCheckAll) {
        try {

            tableLogic.configureSearchData(binder, isCheckAll);
            resultTable.setFilterDecorator(new ExtDemoFilterDecorator());
            resultTable.setFilterGenerator(new OutboundFilterGenerator());
            resultTable.setImmediate(true);
            resultTable.setWidth(99, UNITS_PERCENTAGE);
            resultTable.addStyleName("TableCheckBox");
            resultTable.setColumnCheckBox("check", true);
            resultTable.getFilterField("check").setVisible(false);
            resultTable.setSelectable(true);
            resultTable.markAsDirtyRecursive();
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    @UiHandler("closeBtn")
    public void closeBtnLogic(Button.ClickEvent event) {
        close();
    }

    @UiHandler("outboundBtn")
    public void outboundBtnLogic(Button.ClickEvent event) {
        LOGGER.info("outboundBtn click listener started");
        try {
            if (resultTable.size() != 0) {
                Long startTime = System.currentTimeMillis();
                StringBuilder ids = new StringBuilder(StringUtils.EMPTY);
                String checkedIds = StringUtils.EMPTY;
                boolean checkedAll = false;
                if (resultTable.getColumnCheckBox("check")) {
                    checkedAll = true;
                } else if (!checkedHierarchy.isEmpty()) {
                    for (String keyId : checkedHierarchy.keySet()) {
                        ids.append(",").append(keyId);
                    }
                    checkedIds = ids.toString().replaceFirst(",", ConstantsUtils.EMPTY);
                }
                String csvName = "Relationship_Builder_Outbound_Process";
                LOGGER.info(" checked Ids=" + checkedIds);
                if (checkedAll) {
                    createWorkSheet(csvName, checkedIds, true);
                }
                if (StringUtils.isNotBlank(checkedIds)) {
                    createWorkSheet(csvName, checkedIds, false);
                }
                Long endTime = System.currentTimeMillis();
                LOGGER.info("_____Relationship builder_____Total Time taken===in milli sesconds=" + (endTime - startTime));
                LOGGER.info("____Relationship builder______Total Time taken==in seconds==" + TimeUnit.MILLISECONDS.toSeconds(endTime - startTime));

            }

        } catch (Exception e) {
            LOGGER.error(e);
        }
        LOGGER.info("processTrackingBtn click listener ends");

    }

    public void createWorkSheet(String csvName, String checkedIds, boolean isCheckAll) throws SystemException, PortalException, Exception {
        long recordCount;
        try {
            if (isCheckAll) {
                rbCsvList = outboundLogic.getRelationShipOutboundCheckAllResults(binder);
            } else {
                rbCsvList = outboundLogic.getRelationShipOutboundResults(checkedIds);
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        recordCount = rbCsvList.size();
        ExcelExportforBB.createWorkSheet(CommonUIUtil.RELATIONSHIP_OUTBOUND_EXCEL_HEADER, recordCount, this, UI.getCurrent(), csvName);
    }

    public void createWorkSheetContent(final Integer start, final Integer end, final PrintWriter printWriter) throws SystemException, PortalException, Exception {
        try {
            if (end != 0) {
                ExcelExportforBB.createFileContent(CommonUIUtil.RELATIONSHIP_OUTBOUND_EXCEL_COLUMNS, rbCsvList, printWriter);
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

}
