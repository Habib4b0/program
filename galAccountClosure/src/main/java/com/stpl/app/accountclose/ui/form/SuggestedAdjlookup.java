/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.ui.form;

import com.stpl.app.accountclose.common.CommonLogic;
import com.stpl.app.accountclose.dto.BaseRateDTO;
import com.stpl.app.accountclose.dto.TreeDTO;
import com.stpl.app.accountclose.gtnbalancereport.utils.DataSelectionUtil;
import com.stpl.app.accountclose.logic.SuggestedAdjustmentTableLogic;
import com.stpl.app.accountclose.sessionutils.SessionDTO;
import static com.stpl.app.accountclose.ui.form.BaseRateCalculation.LOGGER;
import com.stpl.app.accountclose.utils.CommonUtils;
import com.stpl.app.accountclose.utils.Constants;
import com.stpl.app.accountclose.utils.HeaderUtils;
import com.stpl.app.customtreecontainer.CustomTreeContainer;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.vaadin.data.Container;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.Field;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TableFieldFactory;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extcustomcheckbox.ExtCustomCheckBox;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.asi.ui.extfilteringtable.ExtFilterTreeTable;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.freezetable.FreezePagedTreeTable;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;

/**
 *
 * @author kasiammal.m
 */
public class SuggestedAdjlookup extends Window {

    private String indicator;
    private TextField groupLookup;
    private String sidQuery;
    @UiField("availableTableLayout")
    public VerticalLayout availableTableLayout;
    SuggestedAdjustmentTableLogic logic = new SuggestedAdjustmentTableLogic();
    public FreezePagedTreeTable availableCustomer = new FreezePagedTreeTable(logic);
    ExtFilterTreeTable leftAvailTable;
    ExtFilterTreeTable rightAvailTable;
    @UiField("tableLayout")
    public VerticalLayout tableLayout;
    public ExtFilterTable baseRateCalcTable = new ExtFilterTable();
    @UiField("frequency")
    public ComboBox frequency;
    CustomTableHeaderDTO headerDTO = new CustomTableHeaderDTO();
    @UiField("fromDateDdlb")
    public ComboBox fromDateDdlb;
    @UiField("toDateDdlb")
    public ComboBox toDateDdlb;
    CommonUtils comutils = new CommonUtils();
    SessionDTO session;
    List<String> doubleHeaderList = new ArrayList<String>();
    List<String> checkedList = new ArrayList<String>();
    private CustomTreeContainer<BaseRateDTO> baseRateCalcContainer = new CustomTreeContainer<BaseRateDTO>(BaseRateDTO.class);
    List<String> headersList = new ArrayList<String>();
    List<String> doubleheadList = new ArrayList<String>();
    CustomTableHeaderDTO fullHeader = new CustomTableHeaderDTO();
    CustomTableHeaderDTO rightDTO;
    CustomTableHeaderDTO leftDTO;
    private CustomTreeContainer<TreeDTO> resultBean = new CustomTreeContainer<TreeDTO>(TreeDTO.class);
    HeaderUtils utils = new HeaderUtils();
    private TreeDTO tableBean;
    CommonLogic commonLogic = new CommonLogic();
    final List<TreeDTO> selecteditemList = new ArrayList<TreeDTO>();
    List<String> ccpIds=new ArrayList<String>();

    public SuggestedAdjlookup(final TextField groupLookup,List<String> ccpIds) {
        super("Adjustment Calculator");
        addStyleName("bootstrap-ui");
        addStyleName("bootstrap");
        addStyleName("bootstrap-forecast bootstrap-nm");
        this.groupLookup = groupLookup;
        this.ccpIds=ccpIds;
        logic.setCcpIds(ccpIds);
        init();
    }

    public void init() {
        setClosable(true);
        setModal(true);
        setWidth(60f, Sizeable.Unit.PERCENTAGE);
        setHeight(40f, Sizeable.Unit.PERCENTAGE);
        setContent(Clara.create(getClass().getResourceAsStream("/SuggestedAdjustmentcal.xml"), this));
        Panel avail = new Panel();
        avail.setContent(availableCustomer);
        availableTableLayout.addComponent(avail);
        frequency.addItem("-Select One-");
        frequency.setNullSelectionAllowed(true);
        frequency.setNullSelectionItemId("-Select One-");
        frequency.addItem(Constants.FrequencyConstants.ANNUALLY.getConstant());
        frequency.addItem(Constants.FrequencyConstants.SEMI_ANNUALLY.getConstant());
        frequency.addItem(Constants.FrequencyConstants.QUARTERLY.getConstant());
        frequency.addItem(Constants.FrequencyConstants.MONTHLY.getConstant());
        frequency.setValue(Constants.FrequencyConstants.QUARTERLY.getConstant());
        configureBaseRateCalcTable();
        configureAvailTable();
         logic.setData();
    }

    public void configureBaseRateCalcTable() {
        try {
            tableLayout.removeAllComponents();
            baseRateCalcTable = new ExtFilterTable();
            String frequencyVal = String.valueOf(frequency.getValue());
            headerDTO = new CustomTableHeaderDTO();
            checkedList.clear();
            doubleHeaderList = DataSelectionUtil.configureTimeDdlb(fromDateDdlb, toDateDdlb, session.getDsFromDate(), session.getDsToDate(), StringUtils.EMPTY, "DS", String.valueOf(frequency.getValue()));
            final String timePeriodFrom = String.valueOf(fromDateDdlb.getValue());
            final String timePeriodTo = String.valueOf(toDateDdlb.getValue());
            headerDTO = HeaderUtils.getBRTableColumns(timePeriodFrom, timePeriodTo, doubleHeaderList, false, true, null);
            baseRateCalcTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
            baseRateCalcTable.setWidth("830px");
            baseRateCalcTable.setHeight("590px");
            baseRateCalcTable.setPageLength(5);

            baseRateCalcContainer = new CustomTreeContainer<BaseRateDTO>(BaseRateDTO.class);
            baseRateCalcContainer.setColumnProperties(headerDTO.getProperties());
            baseRateCalcTable.setContainerDataSource(baseRateCalcContainer);

            baseRateCalcTable.setVisibleColumns(headerDTO.getSingleColumns().toArray());
            baseRateCalcTable.setColumnHeaders(headerDTO.getSingleHeaders().toArray(new String[headerDTO.getSingleHeaders().size()]));
            for (Object visibleColumns : headerDTO.getSingleColumns()) {
                baseRateCalcTable.setColumnWidth(visibleColumns, 150);
            }
            for (Object obj : headerDTO.getSingleColumns()) {
                baseRateCalcTable.setColumnCheckBox(obj, true, false);
            }
            baseRateCalcTable.setDoubleHeaderVisible(true);
            baseRateCalcTable.setDoubleHeaderVisibleColumns(headerDTO.getDoubleColumns().toArray());
            baseRateCalcTable.setDoubleHeaderColumnHeaders(headerDTO.getDoubleHeaders().toArray(new String[headerDTO.getDoubleHeaders().size()]));
            baseRateCalcTable.setDoubleHeaderMap(headerDTO.getDoubleHeaderMaps());

            baseRateCalcTable.setTripleHeaderVisible(true);
            baseRateCalcTable.setTripleHeaderVisibleColumns(headerDTO.getTripleColumns().toArray());
            baseRateCalcTable.setTripleHeaderColumnHeaders(headerDTO.getTripleHeader().toArray(new String[headerDTO.getTripleHeader().size()]));
            baseRateCalcTable.setTripleHeaderMap(headerDTO.getTripleHeaderMaps());

            for (Object obj : headerDTO.getTripleColumns()) {
                baseRateCalcTable.setTripleHeaderColumnCheckBox(obj, true);
                baseRateCalcTable.setColumnWidth(obj, 150);
            }
            for (Object obj : headerDTO.getDoubleColumns()) {
                baseRateCalcTable.setDoubleHeaderColumnCheckBox(obj, true, false);
            }
            baseRateCalcTable.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {
                public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                    if (event.isChecked()) {
                        headersList.add(event.getPropertyId().toString());
                        headerDTO = HeaderUtils.getBRTableColumns(timePeriodFrom, timePeriodTo, doubleHeaderList, false, false, headersList);
                        baseRateCalcTable.setColumnHeaders(headerDTO.getSingleHeaders().toArray(new String[headerDTO.getSingleHeaders().size()]));
                    } else {
                        headersList.add(event.getPropertyId().toString());
                        headerDTO = HeaderUtils.getBRTableColumns(timePeriodFrom, timePeriodTo, doubleHeaderList, false, true, headersList);
                        baseRateCalcTable.setColumnHeaders(headerDTO.getSingleHeaders().toArray(new String[headerDTO.getSingleHeaders().size()]));
                    }

                }
            });

            baseRateCalcTable.addDoubleHeaderColumnCheckListener(new ExtCustomTable.DoubleHeaderColumnCheckListener() {

                public void doubleHeaderColumnCheck(ExtCustomTable.DoubleHeaderColumnCheckEvent event) {
                    if (event.isChecked()) {
                        doubleheadList.add(event.getPropertyId().toString());
                        headerDTO = HeaderUtils.getBRTableColumns(timePeriodFrom, timePeriodTo, doubleHeaderList, false, false, doubleheadList);
                        baseRateCalcTable.setDoubleHeaderColumnHeaders(headerDTO.getDoubleHeaders().toArray(new String[headerDTO.getDoubleHeaders().size()]));
                    } else {
                        doubleheadList.remove(event.getPropertyId().toString());
                        headerDTO = HeaderUtils.getBRTableColumns(timePeriodFrom, timePeriodTo, doubleHeaderList, false, true, doubleheadList);
                        baseRateCalcTable.setDoubleHeaderColumnHeaders(headerDTO.getDoubleHeaders().toArray(new String[headerDTO.getDoubleHeaders().size()]));
                    }
                }
            });
            baseRateCalcTable.addTripleHeaderColumnCheckListener(new ExtCustomTable.TripleHeaderColumnCheckListener() {

                public void tripleHeaderColumnCheck(ExtCustomTable.TripleHeaderColumnCheckEvent event) {
                    if (event.isChecked()) {
                        checkedList.add(event.getPropertyId().toString());
                    } else {
                        checkedList.remove(event.getPropertyId().toString());
                    }
                }
            });

            tableLayout.addComponent(baseRateCalcTable);
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    public void configureAvailTable() {
        fullHeader = new CustomTableHeaderDTO();
        rightDTO = utils.getTableColumns(fullHeader);
        resultBean = new CustomTreeContainer<TreeDTO>(TreeDTO.class);
        resultBean.setColumnProperties(rightDTO.getProperties());
        logic.setPageLength(8);
        logic.setContainerDataSource(resultBean);
        logic.sinkItemPerPageWithPageLength(false);
        availableCustomer.setWidth(100, Unit.PERCENTAGE);
        availableCustomer.setSplitPosition(100, Unit.PERCENTAGE);
        availableCustomer.markAsDirty();
        leftAvailTable = availableCustomer.getLeftFreezeAsTable();
        rightAvailTable = availableCustomer.getRightFreezeAsTable();
        rightAvailTable.setVisible(false);
        leftAvailTable.setEditable(true);
        leftAvailTable.setVisibleColumns(rightDTO.getSingleColumns().toArray());
        leftAvailTable.setColumnHeaders(rightDTO.getSingleHeaders().toArray(new String[rightDTO.getSingleHeaders().size()]));
        leftAvailTable.setColumnCheckBox("checkRecord", Boolean.TRUE);
        leftAvailTable.setTableFieldFactory(new TableFieldFactory() {
            public Field<?> createField(Container container, final Object itemId, final Object propertyId, Component uiContext) {

                if (propertyId.equals("checkRecord")) {
                    final ExtCustomCheckBox check = new ExtCustomCheckBox();

                    check.addClickListener(new ExtCustomCheckBox.ClickListener() {
                        public void click(ExtCustomCheckBox.ClickEvent event) {
                            TreeDTO trDto = (TreeDTO) itemId;
                            Boolean checkValue = check.getValue();
                            trDto.addBooleanProperties(propertyId, checkValue);
                            int updatedRecordsNo = updateCheckedRecord(trDto, 1);
                            availableCustomer.getLeftFreezeAsTable().setRefresh(false);
                            updateCheckForParentLevels(itemId, 5, checkValue);
                            availableCustomer.getLeftFreezeAsTable().setRefresh(true);
                            if (checkValue) {
                                if (!selecteditemList.contains(itemId)) {
                                    selecteditemList.add((TreeDTO) itemId);
                                }
                            } else {
                                selecteditemList.remove(itemId);
                            }
                        }
                    });
                    return check;
                }
                return null;
            }
        });
        leftAvailTable.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {
            public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                Collection list = leftAvailTable.getItemIds();
                for (Object obj : list) {
                    resultBean.getContainerProperty(obj, event.getPropertyId()).setValue(event.isChecked());
                }

            }

        });

        for (Object property : leftAvailTable.getVisibleColumns()) {
            if (String.valueOf(property).contains("checkRecord")) {
                leftAvailTable.setColumnWidth(property, 60);
            }
        }
    }

    private int updateCheckedRecord(TreeDTO dto, int val) {
        LOGGER.info("Entering updateCheckedRecord ->" + dto.getComapnySid() + dto.getCcpSid());
        String hierarchyNo = StringUtils.EMPTY;
        int updatedRecordCount = 0;
        int maxTreeLevelno = 0;
        int treeLevelNo = 0;
        int count = 0;
        boolean checkValue = true;
        setValues(dto);
        commonLogic.updateCheckRecord(dto, val,StringUtils.EMPTY);
        LOGGER.info(" Ending updateCheckedRecord ");
        return updatedRecordCount;
    }

    public void setValues(TreeDTO dto) {
       

    }

    private void updateCheckForParentLevels(Object itemId, int updatedRecordsNo, Boolean checkValue) {
        TreeDTO tempDto = (TreeDTO) itemId;
        boolean check = tempDto.getUncheckCount() == 0;
        tempDto.addBooleanProperties("checkRecord", checkValue);
        if (checkValue) {
            logic.getContainerDataSource().getContainerProperty(itemId, "checkRecord").setValue(checkValue);
        }
    }
}
