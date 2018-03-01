/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.processscheduler.ui.form;

import com.stpl.app.adminconsole.util.StringConstantUtils;
import com.stpl.app.adminconsole.common.util.CommonUIUtil;
import com.stpl.app.adminconsole.processscheduler.dto.OutboundFilterGenerator;
import com.stpl.app.adminconsole.processscheduler.dto.OutboundTableDTO;
import com.stpl.app.adminconsole.processscheduler.dto.RelationshipOutboundDTO;
import com.stpl.app.adminconsole.processscheduler.logic.OutboundLogic;
import com.stpl.app.adminconsole.processscheduler.logic.tableLogic.RelationshipOutboundTableLogic;
import com.stpl.app.adminconsole.processscheduler.ui.lazyload.HierarchyNameContainer;
import com.stpl.app.adminconsole.processscheduler.ui.lazyload.HierarchyNameCriteria;
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
import com.stpl.app.ui.errorhandling.ErrorLabel;
import com.stpl.ifs.ui.DateToStringConverter;
import com.stpl.ifs.ui.util.CommonUIUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.ExcelExportforBB;
import com.stpl.ifs.util.HelperDTO;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.fieldgroup.FieldGroup;
import com.vaadin.v7.data.util.BeanItem;
import com.vaadin.v7.data.util.BeanItemContainer;
import static com.vaadin.server.Sizeable.UNITS_PERCENTAGE;
import com.vaadin.ui.Button;
import com.vaadin.v7.ui.CheckBox;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.v7.ui.DefaultFieldFactory;
import com.vaadin.v7.ui.Field;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.v7.ui.OptionGroup;
import com.vaadin.ui.UI;
import com.vaadin.v7.ui.VerticalLayout;
import com.vaadin.ui.Window;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.addons.lazycontainer.LazyContainer;
import org.asi.ui.extfilteringtable.ExtCustomTable;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author satheesh.n
 */
public class RelationshipOutboundProcess extends Window {

    private static final Logger LOGGER = LoggerFactory.getLogger(RelationshipOutboundProcess.class);
    
    @UiField("hierarchyNameDDLB")
    private ComboBox hierarchyNameDDLB;
    
    @UiField("relationshipType")
    private OptionGroup relationshipType;
     
    @UiField("tableLayout")
    private VerticalLayout tableLayout;
    
    @UiField("controlLayout")
    private HorizontalLayout controlLayout;
    
    @UiField("errorMsg")
    public ErrorLabel errorMsg;
    
    private RelationshipOutboundTableLogic tableLogic = new RelationshipOutboundTableLogic();
    private final OutboundLogic searchLogic = new OutboundLogic();
    private ExtPagedTable resultTable = new ExtPagedTable(tableLogic);
    private BeanItemContainer<RelationshipOutboundDTO> resultBean = new BeanItemContainer<>(RelationshipOutboundDTO.class);
    private final CommonUtils commonsUtil = new CommonUtils();
    private ErrorfulFieldGroup binder;
    private Map<String, RelationshipOutboundDTO> checkedHierarchy = new HashMap<>();
    private OutboundLogic outboundLogic = new OutboundLogic();
    private List<OutboundTableDTO> rbCsvList = null;

    public RelationshipOutboundProcess() {
        super("Relationship Builder Outbound Process");
        try {
            init();
        } catch (Exception ex) {
           LOGGER.error(ex.getMessage());
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

    public void init() {

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

    public void configureFields() {
        try {
            relationshipType.addItem(StringConstantUtils.PRIMARY);
            relationshipType.addItem("Secondary");
            relationshipType.select(StringConstantUtils.PRIMARY);
            relationshipType.addItem(StringConstantUtils.PRIMARY);
            relationshipType.setImmediate(true);
            final LazyContainer hierarchyNameContainer = new LazyContainer(HelperDTO.class, new HierarchyNameContainer(null), new HierarchyNameCriteria());
            commonsUtil.loadLazyComboBox(hierarchyNameDDLB, hierarchyNameContainer);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    private void configureTable()  {

        tableLayout.addComponent(resultTable);
        ResponsiveUtils.getResponsiveControls(tableLogic.createControls(), controlLayout);
        tableLayout.addComponent(controlLayout);

        tableLogic.setContainerDataSource(resultBean);
        tableLogic.setPageLength(NumericConstants.TEN);
        tableLogic.sinkItemPerPageWithPageLength(false);

        resultTable.setImmediate(true);
        resultTable.setWidth(NumericConstants.NINTY_NINE, UNITS_PERCENTAGE);

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
        resultTable.setVisibleColumns(CommonUIUtil.getInstance().relationshipOutboundSearchColumns);
        resultTable.setColumnHeaders(CommonUIUtil.getInstance().relationshipOutboundSearchHeader);
        resultTable.setEditable(true);
        resultTable.setColumnCheckBox(CHECK, true);
        resultTable.getFilterField(CHECK).setVisible(false);
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
                    if (CHECK.equals(propertyId)) {
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
                    LOGGER.error(exception.getMessage());

                }
                return null;

            }
        });

        resultTable.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {
            @Override
            public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                if (CHECK.equals(event.getPropertyId().toString())) {
                    if (event.isChecked()) {
                        checkedHierarchy.clear();
                        loadGrid(true);
                        // this setCurrentPage is used to refresh the lazy conatiner
                        resultTable.setCurrentPage(resultTable.getCurrentPage());
                        resultTable.setColumnCheckBox(CHECK, true, true);
                    } else {
                        checkedHierarchy.clear();
                        loadGrid(false);
                        // this setCurrentPage is used to refresh the lazy conatiner
                        resultTable.setCurrentPage(resultTable.getCurrentPage());
                        resultTable.setColumnCheckBox(CHECK, true, false);

                    }
                }
            }
        });

    }
    public static final String CHECK = "check";

    @UiHandler("searchBtn")
    public void btnSearchLogic(Button.ClickEvent event) {
        binder.getErrorDisplay().clearError();
        if (!searchLogic.checkSearchCriteria(binder)) {
            AbstractNotificationUtils.getWarningNotification(MessageUtil.getMessage(Message.SEARCH_CRITERIA_HEADER), MessageUtil.getMessage(Message.SEARCH_CRITERIA_MESSAGE));
        } else {

            try {
                checkedHierarchy.clear();
                binder.commit();
                resultTable.setColumnCheckBox(CHECK, true, false);
                loadGrid(false);

                if (tableLogic.isResultsEmpty()) {
                    CommonUIUtils.successNotification(ConstantsUtils.NO_RESULSTS);
                } else {
                    CommonUIUtils.successNotification(ConstantsUtils.SEARCH_COMPLETED);
                }

            } catch (FieldGroup.CommitException commit) {
                tableLogic.clearAll();
                tableLogic.getFilters().clear();
                resultTable.setFilterDecorator(new ExtDemoFilterDecorator());
                resultTable.setFilterGenerator(new OutboundFilterGenerator());
                LOGGER.error(commit.getMessage());
            } catch (Exception exception) {
                AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1004));
                LOGGER.error(exception.getMessage());
            }
        }

    }

    private void loadGrid(boolean isCheckAll) {
        try {

            tableLogic.configureSearchData(binder, isCheckAll);
            resultTable.setFilterDecorator(new ExtDemoFilterDecorator());
            resultTable.setFilterGenerator(new OutboundFilterGenerator());
            resultTable.setImmediate(true);
            resultTable.setWidth(NumericConstants.NINTY_NINE, UNITS_PERCENTAGE);
            resultTable.addStyleName("TableCheckBox");
            resultTable.setColumnCheckBox(CHECK, true);
            resultTable.getFilterField(CHECK).setVisible(false);
            resultTable.setSelectable(true);
            resultTable.markAsDirtyRecursive();
        } catch (Exception ex) {
           LOGGER.error(ex.getMessage());
        }
    }

    @UiHandler("closeBtn")
    public void closeBtnLogic(Button.ClickEvent event) {
        close();
    }

    @UiHandler("outboundBtn")
    public void outboundBtnLogic(Button.ClickEvent event) {
        LOGGER.debug("outboundBtn click listener started");
        try {
            if (resultTable.size() != 0) {
                Long startTime = System.currentTimeMillis();
                StringBuilder ids = new StringBuilder(StringUtils.EMPTY);
                String checkedIds = StringUtils.EMPTY;
                boolean checkedAll = false;
                if (resultTable.getColumnCheckBox(CHECK)) {
                    checkedAll = true;
                } else if (!checkedHierarchy.isEmpty()) {
                    for (String keyId : checkedHierarchy.keySet()) {
                        ids.append(',').append(keyId);
                    }
                    checkedIds = ids.toString().replaceFirst(",", ConstantsUtils.EMPTY);
                }
                String csvName = "Relationship_Builder_Outbound_Process";
                LOGGER.debug(" checked Ids= {}" , checkedIds);
                if (checkedAll) {
                    createWorkSheet(csvName, checkedIds, true);
                }
                if (StringUtils.isNotBlank(checkedIds)) {
                    createWorkSheet(csvName, checkedIds, false);
                }
                Long endTime = System.currentTimeMillis();
                LOGGER.debug("_____Relationship builder_____Total Time taken===in milli sesconds= {}" , (endTime - startTime));
                LOGGER.debug("____Relationship builder______Total Time taken==in seconds= {}" , TimeUnit.MILLISECONDS.toSeconds(endTime - startTime));

            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        LOGGER.debug("processTrackingBtn click listener ends");

    }

    public void createWorkSheet(String csvName, String checkedIds, boolean isCheckAll) throws SystemException, PortalException, NoSuchMethodException, IllegalAccessException,  InvocationTargetException {
        long recordCount;
        try {
            if (isCheckAll) {
                rbCsvList = outboundLogic.getRelationShipOutboundCheckAllResults(binder);
            } else {
                rbCsvList = outboundLogic.getRelationShipOutboundResults(checkedIds);
            }
        } catch (Exception ex) {
           LOGGER.error(ex.getMessage());
        }
        recordCount = rbCsvList.size();
        ExcelExportforBB.createWorkSheet(CommonUIUtil.getInstance().relationshipOutboundExcelHeader, recordCount, this, UI.getCurrent(), csvName);
    }

    public void createWorkSheetContent(final Integer start,final Integer end, final PrintWriter printWriter) {
        try {
             LOGGER.info("Start index of record is = {} ", start);
            if (end != 0) {
                ExcelExportforBB.createFileContent(CommonUIUtil.getInstance().relationshipOutboundExcelColumns, rbCsvList, printWriter);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

}
