/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.ui.form;

import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.app.accountclose.common.CommonLogic;
import com.stpl.app.accountclose.dto.BaseRateDTO;
import com.stpl.app.accountclose.dto.BaseRateSummaryDTO;
import com.stpl.app.accountclose.logic.BaseRateCalculationLogic;
import com.stpl.app.accountclose.logic.CommonQueryLogic;
import com.stpl.app.accountclose.logic.SummaryTableLogic;
import com.stpl.app.accountclose.sessionutils.SessionDTO;
import static com.stpl.app.accountclose.ui.form.BaseRateSummary.LOGGER;
import com.stpl.app.accountclose.utils.AbstractNotificationUtils;
import com.stpl.app.accountclose.utils.Constants;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import static com.stpl.app.accountclose.utils.Constants.IndicatorConstants.*;
import static com.stpl.app.accountclose.utils.Constants.ResourceConstants.EXCEL_IMAGE_PATH;
import com.stpl.app.accountclose.utils.HeaderUtils;
import com.stpl.app.customtreecontainer.CustomTreeContainer;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.stpl.ifs.util.ExtCustomTableHolder;
import com.stpl.ifs.util.HelperDTO;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TableFieldFactory;
import com.vaadin.ui.TextField;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extcustomcheckbox.ExtCustomCheckBox;
import org.asi.ui.extfilteringtable.ExtFilterTreeTable;
import org.asi.ui.extfilteringtable.paged.ExtPagedTreeTable;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author alok.v
 */
public class BaseRateSummary extends CustomComponent implements View {

    public static final Logger LOGGER = Logger.getLogger(BaseRateSummary.class);
    SessionDTO session;
    private BaseRateDTO baseRateDTO;
    private CommonLogic logic = new CommonLogic();
    /**
     * Field Drop Down List
     */
    @UiField("fieldDdlb")
    public ComboBox fieldDdlb;
    @UiField("valueDdlb")
    public ComboBox valueDdlb;
    @UiField("textDdlb")
    public TextField textDdlb;
    @UiField("massUpdateRadio")
    public OptionGroup massUpdateRadio;
    @UiField("brSummaryTableLayout")
    public HorizontalLayout brSummaryTableLayout;
    @UiField("brVerSummaryTableLayout")
    public VerticalLayout brVerSummaryTableLayout;
    @UiField("excelBtn")
    public Button excelBtn;
    @UiField("populateBtn")
    public Button populateBtn;
    @UiField("companyId")
    private TextField companyId;
    @UiField("acctType")
    private TextField acctType;
    @UiField("customerGroup")
    private TextField customerGroup;
    @UiField("productGroup")
    private TextField productGroup;
    @UiField("marketType")
    private TextField marketType;
    @UiField("acctSubType")
    private TextField acctSubType;
    @UiField("contractId")
    private TextField contractId;
    @UiField("productName")
    private TextField productName;
    SummaryTableLogic summaryLogic = new SummaryTableLogic();
    public ExtPagedTreeTable baseRateSummaryTable = new ExtPagedTreeTable(summaryLogic);
    public CustomTreeContainer<BaseRateSummaryDTO> baseRateSummaryContainer;
    CustomTableHeaderDTO headerDTO;
    CustomTableHeaderDTO fullHeader = new CustomTableHeaderDTO();
    CustomTableHeaderDTO rightDTO;
    CustomTableHeaderDTO leftDTO;
    HeaderUtils utils = new HeaderUtils();
    BaseRateSummaryDTO summaryDto = new BaseRateSummaryDTO();
    BaseRateCalculation baseRateCalc;
    BaseRateCalculationLogic brLogic = new BaseRateCalculationLogic();
    /**
     * The excel export image.
     */
    private final Resource excelExportImage = new ThemeResource(EXCEL_IMAGE_PATH.getConstant());
    /* The Excel table */
    ExtFilterTreeTable excelTable = new ExtFilterTreeTable();
    /* The Excel container */
    CustomTreeContainer<BaseRateSummaryDTO> excelContainer = new CustomTreeContainer<BaseRateSummaryDTO>(BaseRateSummaryDTO.class);
    List<String> deleteList = new ArrayList<String>();

    public Component getContent() {
        LOGGER.info("getContent method starts");
        VerticalLayout layout = new VerticalLayout();
        layout.addComponent(Clara.create(getClass().getResourceAsStream("/baseRateSummary.xml"), this));
        Panel panel = new Panel();
        panel.setContent(layout);
        LOGGER.info("getContent method ends");
        return panel;
    }

    public BaseRateSummary(final SessionDTO session, final BaseRateDTO baseRateDTO) {
        this.session = session;
        this.baseRateDTO = baseRateDTO;
        summaryDto.setSessionId(session.getSessionId());
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/baseRateSummary.xml"), this));
        configureFields();
    }

    /**
     *
     */
    public void configureFields() {
        try {
            LOGGER.info("Entered configureFields BaseRateSummary");
            massUpdateRadio.setImmediate(true);
            massUpdateRadio.addItem(ENABLE.getConstant());
            massUpdateRadio.addItem(DISABLE.getConstant());
            massUpdateRadio.select(DISABLE.getConstant());
            massUpdateRadio.setEnabled(true);
            excelBtn.setIcon(excelExportImage);

            fieldDdlb.addItem(Constants.IndicatorConstants.SELECT_ONE.getConstant());
            fieldDdlb.setNullSelectionAllowed(true);
            fieldDdlb.setNullSelectionItemId(Constants.IndicatorConstants.SELECT_ONE.getConstant());
            fieldDdlb.addItem(Constants.IndicatorConstants.REASON_CODE.getConstant());
            fieldDdlb.addItem(Constants.IndicatorConstants.NOTES.getConstant());

            valueDdlb.addItem(Constants.IndicatorConstants.SELECT_ONE.getConstant());
            valueDdlb.setImmediate(true);
            valueDdlb.setNullSelectionAllowed(true);
            valueDdlb.setNullSelectionItemId(Constants.IndicatorConstants.SELECT_ONE.getConstant());
            List<HelperDTO> helper = new ArrayList<HelperDTO>();
            helper.addAll(logic.getDropDownList(Constants.REASON_CODES));
            for (int i = 0; i < helper.size(); i++) {
                if (helper.get(i).getId() != 0) {
                    valueDdlb.addItem(String.valueOf(helper.get(i).getId()));
                    valueDdlb.setItemCaption(String.valueOf(helper.get(i).getId()), helper.get(i).getDescription());
                }
            }

            brSummaryTableLayout.addComponent(baseRateSummaryTable);
            configureBaseRateSummaryTable();
            HorizontalLayout controls = summaryLogic.createControls();
            HorizontalLayout controlLayout = CommonLogic.getResponsiveControls(controls);
            brVerSummaryTableLayout.addComponent(brSummaryTableLayout);
            brVerSummaryTableLayout.addComponent(controlLayout);
            LOGGER.info("Ended configureFields BaseRateSummary");
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    /**
     *
     */
    public void configureBaseRateSummaryTable() {

        fullHeader = new CustomTableHeaderDTO();
        rightDTO = utils.getSummTableColumns(fullHeader);
        baseRateSummaryContainer = new CustomTreeContainer<BaseRateSummaryDTO>(BaseRateSummaryDTO.class);
        summaryLogic.setControlTable(baseRateSummaryTable);
        baseRateSummaryContainer.setColumnProperties(rightDTO.getProperties());

        summaryLogic.sinkItemPerPageWithPageLength(false);
        summaryLogic.setContainerDataSource(baseRateSummaryContainer);
        summaryLogic.setPageLength(10);
        baseRateSummaryTable.setWidth(100, Unit.PERCENTAGE);
        baseRateSummaryTable.markAsDirty();
        baseRateSummaryTable.setVisibleColumns(rightDTO.getSingleColumns().toArray());
        baseRateSummaryTable.setColumnHeaders(rightDTO.getSingleHeaders().toArray(new String[rightDTO.getSingleHeaders().size()]));
        baseRateSummaryTable.setColumnCheckBox("checkRecord", Boolean.TRUE);
        baseRateSummaryTable.setTableFieldFactory(new TableFieldFactory() {
            public Field<?> createField(Container container, final Object itemId, final Object propertyId, Component uiContext) {

                if (propertyId.equals("checkRecord")) {
                    final BaseRateSummaryDTO trDto = (BaseRateSummaryDTO) itemId;
                    if (trDto.getLevelNo() == 5) {
                        final ExtCustomCheckBox check = new ExtCustomCheckBox();

                        check.addClickListener(new ExtCustomCheckBox.ClickListener() {
                            public void click(ExtCustomCheckBox.ClickEvent event) {
                                int val = 0;
                                Boolean checkValue = check.getValue();
                                if (checkValue) {
                                    val = 1;
                                    deleteList.add(String.valueOf(trDto.getMethodology()));
                                } else {
                                    deleteList.remove(String.valueOf(trDto.getMethodology()));
                                }

                                baseRateSummaryTable.getItem(itemId).getItemProperty("checkRecord").setValue(check.getValue());
                                ((BaseRateSummaryDTO) itemId).addBooleanProperties("checkRecord", check.getValue());
                                brLogic.updateCheckQuery(trDto, val, session.getSessionId());
                            }
                        });
                        return check;
                    }
                }

                if (propertyId.equals("reasonCode")) {
                    final BaseRateSummaryDTO trDto = (BaseRateSummaryDTO) itemId;
                    if (trDto.getLevelNo() == 5) {
                        ComboBox combo = new ComboBox();
                        combo.setWidth(100, Unit.PERCENTAGE);
                        try {
                            combo = CommonLogic.getComboBox(combo, CommonLogic.getDropDownList("REASON_CODES"));
                        } catch (Exception ex) {
                            LOGGER.error(ex);
                        }
                        combo.addValueChangeListener(new Property.ValueChangeListener() {
                            public void valueChange(Property.ValueChangeEvent event) {
                                String value = String.valueOf(event.getProperty().getValue());
                            }
                        });

                        return combo;
                    }
                }
                if (propertyId.equals("notes")) {
                    final BaseRateSummaryDTO trDto = (BaseRateSummaryDTO) itemId;
                    if (trDto.getLevelNo() == 5) {
                        TextField combo = new TextField();
                        combo.setWidth(100, Unit.PERCENTAGE);
                        combo.addValueChangeListener(new Property.ValueChangeListener() {
                            public void valueChange(Property.ValueChangeEvent event) {
                                String value = String.valueOf(event.getProperty().getValue());
                            }
                        });

                        return combo;
                    }
                }

                return null;
            }
        });

        baseRateSummaryTable.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {
            public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                Collection list = baseRateSummaryTable.getItemIds();
                for (Object obj : list) {
                    baseRateSummaryContainer.getContainerProperty(obj, event.getPropertyId()).setValue(event.isChecked());
                }

            }
        });

        for (Object property : baseRateSummaryTable.getVisibleColumns()) {
            if (String.valueOf(property).contains("checkRecord")) {
                baseRateSummaryTable.setColumnWidth(property, 60);
            } else {
                baseRateSummaryTable.setColumnWidth(property, 120);
            }
        }
    }

    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

    /**
     * Mass Update Functionality
     *
     * @param event
     */
    @UiHandler("massUpdateRadio")
    public void massUpdateEnDisLogic(Property.ValueChangeEvent event) {
        LOGGER.info(" massUpdate ValueChangeEvent initiated ");
        if ("Disable".equals(massUpdateRadio.getValue())) {
            enableOrDisable(false);
        } else {
            enableOrDisable(true);
        }
        LOGGER.info("massUpdate ValueChangeEvent ends ");
    }

    /**
     * Enable and Disable Logic
     *
     * @param value
     */
    public void enableOrDisable(boolean value) {
        fieldDdlb.setEnabled(value);
        valueDdlb.setEnabled(value);
        populateBtn.setEnabled(value);
    }

    public void setSummaryValues(BaseRateCalculation baseRateCalc) {

        companyId.setValue(String.valueOf(baseRateCalc.company.getItemCaption(baseRateCalc.company.getValue())));
        acctType.setValue(String.valueOf(baseRateCalc.acctType.getItemCaption(baseRateCalc.acctType.getValue())));
        acctSubType.setValue(String.valueOf(baseRateCalc.acctSubType.getItemCaption(baseRateCalc.acctSubType.getValue())));
        customerGroup.setValue(String.valueOf(baseRateCalc.customerGroup.getValue()));
        productGroup.setValue(String.valueOf(baseRateCalc.productGroup.getValue()));
        marketType.setValue(String.valueOf(baseRateCalc.marketType.getItemCaption(baseRateCalc.marketType.getValue())));
        contractId.setValue(String.valueOf(baseRateCalc.contract.getItemCaption(baseRateCalc.contract.getValue())));
        productName.setValue(String.valueOf(baseRateCalc.productName.getItemCaption(baseRateCalc.productName.getValue())));
        summaryDto.setAcMasterSid(baseRateCalc.baseRateDTO.getAcMasterSid());
        summaryLogic.setData(summaryDto, true);
    }

    /**
     * populate Btn logic
     *
     * @param event
     */
    @UiHandler("populateBtn")
    public void populateBtnClick(Button.ClickEvent event) {

        LOGGER.info("Entering populateBtn");
        populateButtonLogic();

        LOGGER.info("Ending populateBtn");
    }

    /**
     * reset Btn logic for methodology section
     *
     * @param event
     */
    @UiHandler("resetBtn")
    public void resetBtnClick(Button.ClickEvent event) {
        LOGGER.info("Entering resetBtnClick in summary");
        new AbstractNotificationUtils() {
            @Override
            public void yesMethod() {

                for (Object item : baseRateSummaryTable.getItemIds()) {
                    baseRateSummaryTable.getItem(item).getItemProperty("notes").setValue(String.valueOf(StringUtils.EMPTY));
                    ((BaseRateSummaryDTO) item).addStringProperties("notes", String.valueOf(StringUtils.EMPTY));
                    baseRateSummaryTable.getItem(item).getItemProperty("reasonCode").setValue(String.valueOf(StringUtils.EMPTY));
                    ((BaseRateSummaryDTO) item).addStringProperties("reasonCode", String.valueOf(StringUtils.EMPTY));
                }

            }

            @Override
            public void noMethod() {
            }
        }.getConfirmationMessage("Confirmation", "Are you sure you want to reset all the fields to default ");

        LOGGER.info("Ending resetBtnClick in summary");
    }

    /**
     * excel Btn logic
     *
     * @param event
     */
    @UiHandler("excelBtn")
    public void excelBtnClick(Button.ClickEvent event) {
        try {
            brSummaryTableLayout.addComponent(excelTable);
            excelTable.setVisible(false);
            excelContainer = new CustomTreeContainer<BaseRateSummaryDTO>(BaseRateSummaryDTO.class);
            CustomTableHeaderDTO excelHeader = new CustomTableHeaderDTO();
            excelHeader = utils.getSummTableColumns(fullHeader);
            excelContainer.setColumnProperties(excelHeader.getProperties());
            excelTable.setContainerDataSource(excelContainer);

            excelTable.setVisibleColumns(excelHeader.getSingleColumns().toArray());
            excelTable.setColumnHeaders(excelHeader.getSingleHeaders().toArray(new String[excelHeader.getSingleHeaders().size()]));
            summaryDto.setLevelNo(1);
            List<BaseRateSummaryDTO> searchResults = brLogic.configureLevels(summaryDto);
            loadDataToContainer(searchResults, null, true);
            Map<String, String> formatter = new HashMap<String, String>();
            formatter.put("currencyTwoDecimal", "Amount");
            formatter.put("percentTwoDecimal", "Rate");
            LOGGER.info("excelContainer" + excelContainer.size() + excelTable.size());
            ExcelExport export = new ExcelExport(new ExtCustomTableHolder(excelTable), "Base Rate Summary", "Base Rate Summary", "BRSummary.xls", false);

            export.export();
            brSummaryTableLayout.removeComponent(excelTable);
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    public void loadDataToContainer(List<BaseRateSummaryDTO> resultList, Object parentId, boolean isRecursive) {
        try {
            LOGGER.info("Inside loadDataToContainer" + resultList.size());
            for (BaseRateSummaryDTO dto : resultList) {

                excelContainer.addBean(dto);
                if (parentId != null) {
                    excelContainer.setParent(dto, parentId);
                }
                if (dto.getLevelNo() != 5) {
                    excelContainer.setChildrenAllowed(dto, true);
                    if (isRecursive) {
                        addLowerLevelsForExport(dto);
                    }
                } else {
                    excelContainer.setChildrenAllowed(dto, false);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        LOGGER.info("Ended loadDataToContainer");

    }

    public void addLowerLevelsForExport(BaseRateSummaryDTO dto) {
        LOGGER.info("Inside addLowerLevelsForExport");
        try {
            if (dto.getLevelNo() == 5) {
                summaryDto.setLevelNo(dto.getLevelNo());
            } else {
                summaryDto.setLevelNo(dto.getLevelNo() + 1);
            }
            List<BaseRateSummaryDTO> searchResults = brLogic.configureLevels(summaryDto);
            loadDataToContainer(searchResults, dto, true);

            excelTable.setCollapsed(dto, false);

        } catch (Exception e) {
            LOGGER.error(e);
        }
        LOGGER.info("Ended addLowerLevelsForExport");
    }

    /**
     * The Mass Update field Ddlb value change.
     *
     * @param event the event
     */
    @UiHandler("fieldDdlb")
    public void addMassFieldValueChange(Property.ValueChangeEvent event) {
        String fieldValue = String.valueOf(event.getProperty().getValue());
        if (Constants.IndicatorConstants.NOTES.getConstant().equals(fieldValue)) {
            valueDdlb.setVisible(false);
            textDdlb.setVisible(true);
        } else {
            valueDdlb.setVisible(true);
            textDdlb.setVisible(false);
        }

    }

    /**
     * The Mass Update field Ddlb value change.
     *
     * @param event the event
     */
    @UiHandler("deleteBtn")
    public void deleteBtnClick(Button.ClickEvent event) {
        LOGGER.info("Entering deleteBtnClick" + deleteList.get(0));
        if (deleteList.size() > 0) {
            brLogic.deleteSummary(summaryDto, deleteList);
            summaryLogic.setData(summaryDto, true);
        } else {
            AbstractNotificationUtils.getErrorNotification("No Records Selected", "Please select atleast a record to delete");
        }
        LOGGER.info("Ending deleteBtnClick");
    }

    public int saveBRSummary() {
        return brLogic.saveBRSummary(summaryDto);
    }

    public void populateLogic() {
        Collection itemId = baseRateSummaryTable.getItemIds();
        for (Object object : itemId) {
            BaseRateSummaryDTO dto = (BaseRateSummaryDTO) object;
            if ((Boolean) dto.getPropertyValue("checkRecord")) {
                if (fieldDdlb.getValue().equals("Notes")) {
                    String notes = textDdlb.getValue();
                    baseRateSummaryTable.getItem(object).getItemProperty("notes").setValue(notes);
                } else if (fieldDdlb.getValue().equals("Reason Code")) {
                    String valueReason = valueDdlb.getValue().toString();
                    baseRateSummaryTable.getItem(object).getItemProperty("reasonCode").setValue(valueReason);
                }
            }
        }
    }

    private void populateButtonLogic() {
        if (valueDdlb.getValue() != null || textDdlb.getValue() != null) {
            boolean isChecked = Boolean.TRUE;
            isChecked = getAndSetData();
            if (isChecked) {
                Collection itemids = baseRateSummaryTable.getItemIds();
                if (fieldDdlb.getValue().equals("Notes")) {
                    String value = textDdlb.getValue();

                    logic.massUpdateBaseRateSummary(value, session, false);
                    for (Object item : itemids) {
                        BaseRateSummaryDTO dto = (BaseRateSummaryDTO) item;
                        if (Boolean.TRUE.equals(dto.getPropertyValue("checkRecord"))) {
                            baseRateSummaryTable.getItem(item).getItemProperty("notes").setValue(String.valueOf(value));
                            ((BaseRateSummaryDTO) item).addStringProperties("notes", String.valueOf(value));
                        }
                    }
                } else if (fieldDdlb.getValue().equals("Reason Code")) {
                    String value = String.valueOf(valueDdlb.getValue());
                    logic.massUpdateBaseRateSummary(value, session, true);
                    for (Object item : itemids) {
                        BaseRateSummaryDTO dto = (BaseRateSummaryDTO) item;
                        if (Boolean.TRUE.equals(dto.getPropertyValue("checkRecord"))) {
                            baseRateSummaryTable.getItem(item).getItemProperty("reasonCode").setValue(String.valueOf(value));
                            ((BaseRateSummaryDTO) item).addStringProperties("reasonCode", String.valueOf(value));
                        }
                    }
                }

            } else {
                MessageBox.showPlain(Icon.INFO, "Error", "Please select which levels in the list view the Mass Update applies to", ButtonId.OK);

            }
        } else {
            MessageBox.showPlain(Icon.INFO, "Error", "Please Enter the correct price cap value to mass update.", ButtonId.OK);
        }

    }

    boolean getAndSetData() {
        List list = CommonQueryLogic.getItemData(logic.getResultsIntput(session), "Checking populate", null);
        if (!list.isEmpty()) {
            Object obj = list.get(0);
            return obj == null ? Boolean.FALSE : (Integer) obj == 0 ? Boolean.FALSE : Boolean.TRUE;
        }
        return false;

    }
}
