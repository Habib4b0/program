/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.ui.form;

import com.stpl.app.accountclose.dto.BaseRateDTO;
import com.stpl.app.accountclose.logic.BaseRateCalculationLogic;
import com.stpl.app.accountclose.utils.AbstractNotificationUtils;
import com.stpl.app.accountclose.utils.CommonUtils;
import com.stpl.app.accountclose.utils.Constants;
import static com.stpl.app.accountclose.utils.Constants.IndicatorConstants.TIME_WEIGHTED_AVERAGE;
import com.stpl.app.accountclose.utils.HeaderUtils;
import com.stpl.app.customtreecontainer.CustomContainer;
import com.stpl.app.customtreecontainer.CustomTreeContainer;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.event.FieldEvents;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.asi.ui.extfilteringtable.ExtFilterTreeTable;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author santanukumar
 */
public class HeaderLookUP extends Window {

    private static final long serialVersionUID = 1L;
    public static final Logger LOGGER = Logger.getLogger(HeaderLookUP.class);
    VerticalLayout layout = new VerticalLayout();
    @UiField("tableLayout")
    public VerticalLayout tableLayout;
    CustomTableHeaderDTO headerDTO = new CustomTableHeaderDTO();
    private CustomContainer<BaseRateDTO> baseRateCalcContainer = new CustomContainer<BaseRateDTO>(BaseRateDTO.class);
    private OptionGroup variables = new OptionGroup();
    public ExtFilterTable baseRateCalcTable = new ExtFilterTable();
    List<String> headerList;
    Map<String, Integer> salesMap = new HashMap<String, Integer>();
    Map<String, Integer> paymentsMap = new HashMap<String, Integer>();
    Map<String, Integer> textMap = new HashMap<String, Integer>();
    BaseRateCalculationLogic brLogic = new BaseRateCalculationLogic();
    BaseRateDTO baseRateDTO;
    String manual;
    public String calc;
    String focusValue = StringUtils.EMPTY;
    CommonUtils util = new CommonUtils();

    /**
     *
     * @param windowName
     * @param groupLookup
     * @param sidQuery
     */
    public HeaderLookUP(List<String> headerList, BaseRateDTO baseRateDTO, String manual) {
        super("Methodology Selection");
        this.headerList = headerList;
        this.baseRateDTO = baseRateDTO;
        this.manual = manual;
        addStyleName("bootstrap-ui");
        addStyleName("bootstrap");
        addStyleName("bootstrap-forecast bootstrap-nm");
        init();
    }

    public void init() {
        LOGGER.info("Entered HeaderLookUP" + manual);
        setClosable(true);
        setModal(true);
        setContent(Clara.create(getClass().getResourceAsStream("/headerLookUp.xml"), this));
        configureFields();
        LOGGER.info("Ended HeaderLookUP");
    }

    /**
     * Configure fields.
     */
    private void configureFields() {

        configureBaseRateCalcTable();
        tableLayout.addComponent(baseRateCalcTable);
    }

    public void configureBaseRateCalcTable() {
        try {
            tableLayout.removeAllComponents();
            baseRateCalcTable = new ExtFilterTable();
            headerDTO = new CustomTableHeaderDTO();
            headerDTO = HeaderUtils.getBRCalcColumns(headerList);
            baseRateCalcTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
            baseRateCalcTable.setHeight("200px");
            baseRateCalcTable.setPageLength(2);
            baseRateCalcTable.setEditable(true);
            baseRateCalcTable.setImmediate(true);

            baseRateCalcContainer = new CustomTreeContainer<BaseRateDTO>(BaseRateDTO.class);
            baseRateCalcContainer.setColumnProperties(headerDTO.getProperties());
            baseRateCalcTable.setContainerDataSource(baseRateCalcContainer);
            BaseRateDTO dto1 = new BaseRateDTO();
            BaseRateDTO dto2 = new BaseRateDTO();
            dto1.setRowType("ddlbSales");
            dto2.setRowType("ddlbPayments");
            baseRateCalcContainer.addBean(dto1);
            baseRateCalcContainer.addBean(dto2);
            if (TIME_WEIGHTED_AVERAGE.getConstant().equals(manual)) {
                BaseRateDTO dto3 = new BaseRateDTO();
                dto3.setRowType(Constants.TEXT_FIELD);
                baseRateCalcContainer.addBean(dto3);
                for (Object obj : headerDTO.getSingleColumns()) {
                    dto3.addStringProperties(obj, StringUtils.EMPTY);
                }
            }

            baseRateCalcTable.setVisibleColumns(headerDTO.getSingleColumns().toArray());
            baseRateCalcTable.setColumnHeaders(headerDTO.getSingleHeaders().toArray(new String[headerDTO.getSingleHeaders().size()]));
            for (Object visibleColumns : headerDTO.getSingleColumns()) {
                baseRateCalcTable.setColumnWidth(visibleColumns, 150);
            }

            baseRateCalcTable.setTableFieldFactory(new DefaultFieldFactory() {
                public Field<?> createField(final Container container, final Object itemId,
                        final Object propertyId, Component uiContext) {
                    try {
                        final BaseRateDTO dto = (BaseRateDTO) itemId;
                        if (Constants.TEXT_FIELD.equals(dto.getRowType())) {
                            TextField field = new TextField(StringUtils.EMPTY);
                            field.setWidth(100, Unit.PERCENTAGE);
                            field.addStyleName("txtRightAlign");
                            field.setValue(StringUtils.EMPTY);
                            field.setNullRepresentation(StringUtils.EMPTY);
                            field.setNullSettingAllowed(false);
                            field.addBlurListener(new FieldEvents.BlurListener() {
                                @Override
                                public void blur(FieldEvents.BlurEvent event) {
                                    focusValue = String.valueOf(container.getContainerProperty(itemId, propertyId).getValue()).replace(" ", StringUtils.EMPTY);
                                    if (!util.getNull(focusValue)) {
                                        LOGGER.info(" focus Value" + focusValue);
                                        dto.addStringProperties(propertyId, focusValue);
                                        textMap.put(String.valueOf(propertyId), Integer.parseInt(focusValue));
                                    }
                                }
                            });

                            return field;
                        } else {
                            ComboBox combo = new ComboBox();
                            combo.setWidth(100, Unit.PERCENTAGE);
                            combo.addStyleName("txtRightAlign");
                            combo.setImmediate(true);
                            combo.addItem(Constants.SELECT_ONE);
                            combo.addItem("Actuals");
                            combo.addItem("Projections");
                            combo.setNullSelectionAllowed(true);
                            combo.setNullSelectionItemId(Constants.SELECT_ONE);
                            combo.setValue("Actuals");
                            combo.addValueChangeListener(new Property.ValueChangeListener() {
                                public void valueChange(Property.ValueChangeEvent event) {

                                    int val = 0;
                                    String selected = String.valueOf(container.getContainerProperty(itemId, propertyId).getValue());
                                    if ("Actuals".equals(selected)) {
                                        val = 0;
                                    } else {
                                        val = 1;
                                    }
                                    if ("ddlbSales".equals(dto.getRowType())) {

                                        dto.addStringProperties(propertyId, selected);
                                        salesMap.put(String.valueOf(propertyId), val);
                                    } else if ("ddlbPayments".equals(dto.getRowType())) {
                                        dto.addStringProperties(propertyId, selected);
                                        paymentsMap.put(String.valueOf(propertyId), val);
                                    }

                                }
                            });
                            return combo;
                        }

                    } catch (Exception e) {
                        LOGGER.error(e);
                    }
                    return null;
                }
            });

        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    /**
     * next Btn logic
     *
     * @param event
     */
    @UiHandler("submitMethodBtn")
    public void submitMethodBtnClick(Button.ClickEvent event) {
        LOGGER.info("Entering calculate");
        List periodSid = new ArrayList();
        try {
            if (isSelected()) {
                List periods = brLogic.getPeriodSid(headerList, baseRateDTO.getFrequecny(), salesMap, paymentsMap, textMap);
                calc = brLogic.saveMethodology(baseRateDTO, periods, manual);
                close();
            } else {
                AbstractNotificationUtils.getErrorNotification("No Values Selected", "Please select the values for calculation");
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        LOGGER.info("Ending calculate");
    }

    /**
     * Selected in container
     */
    private boolean isSelected() {
        Collection itemId = baseRateCalcTable.getItemIds();
        for (Object object : itemId) {
            BaseRateDTO dto = (BaseRateDTO) object;
            for (Object object1 : dto.getProperties().keySet()) {
                if (dto.getProperties().get(object1) == null) {
                    return false;
                }
            }

        }
        return true;
    }
}
