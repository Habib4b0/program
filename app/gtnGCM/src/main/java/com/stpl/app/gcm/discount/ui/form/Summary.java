/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.discount.ui.form;

import org.asi.container.ExtTreeContainer;
import com.stpl.app.gcm.common.CommonLogic;
import com.stpl.app.gcm.discount.dto.ContractsDetailsDto;
import com.stpl.app.gcm.discount.dto.DiscountDTO;
import com.stpl.app.gcm.discount.dto.RemoveDiscountDto;
import com.stpl.app.gcm.discount.logic.ComponentInfoTableLogic;
import com.stpl.app.gcm.discount.logic.DiscountLogic;
import com.stpl.app.gcm.discount.logic.SummaryTableLogic;
import com.stpl.app.gcm.itemmanagement.index.util.ConstantsUtil;
import com.stpl.app.gcm.itemmanagement.itemabstract.logic.AbstractLogic;
import com.stpl.app.gcm.security.StplSecurity;
import com.stpl.app.gcm.util.AbstractNotificationUtils;
import com.stpl.app.gcm.util.Constants;
import static com.stpl.app.gcm.util.Constants.DateFormatConstants.MMDDYYYY;
import com.stpl.app.gcm.util.HeaderUtils;
import com.stpl.app.gcm.util.ResponsiveUtils;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.ifs.ui.DateToStringConverter;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.constants.BooleanConstant;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.data.util.filter.SimpleStringFilter;
import com.vaadin.v7.ui.AbstractField;
import com.vaadin.ui.Button;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import org.asi.ui.extfilteringtable.ExtCustomTable;
import com.vaadin.v7.ui.Field;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.v7.ui.TextField;
import com.vaadin.v7.ui.VerticalLayout;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtContainer;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
import org.asi.ui.extfilteringtable.ExtFilterTreeTable;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.freezetable.FreezePagedTreeTable;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author mohamed.hameed
 */
public class Summary extends CustomComponent {

    @UiField("summaryLayout")
    private VerticalLayout summaryLayout;
    @UiField("discountLayout")
    private VerticalLayout discountLayout;
    @UiField("contractNo")
    private TextField contractNo;
    @UiField("contractName")
    private TextField contractName;
    @UiField("contractType")
    private TextField contractType;
    @UiField("contractStartDate")
    private TextField contractStartDate;
    @UiField("contractEndDate")
    private TextField contractEndDate;
    @UiField("rebuildBtn")
    private Button rebuildBtn;
    private final SummaryTableLogic tableLogic = new SummaryTableLogic();
    private final ComponentInfoTableLogic infoLogic = new ComponentInfoTableLogic();
    private final ExtPagedTable summaryResultsTable = new ExtPagedTable(infoLogic);
    private final FreezePagedTreeTable discountTable = new FreezePagedTreeTable(tableLogic);
    private final RemoveDiscountDto removeDiscountDto = new RemoveDiscountDto();
    private final BeanItemContainer<RemoveDiscountDto> promoteTpToChDtoResultsContainer = new BeanItemContainer<>(RemoveDiscountDto.class);
    public static final Logger LOGGER = LoggerFactory.getLogger(Summary.class);
    
    private final DiscountLogic discountLogic = new DiscountLogic();
    private final List contractList = new ArrayList();
    private final List rsList = new ArrayList();
    private List<RemoveDiscountDto> selecteditemList;
    private final StplSecurity stplSecurity = new StplSecurity();
    /**
     * The map left visible columns.
     */
    private ContractsDetailsDto dto;
    private RemoveDiscount removeDiscount;
    private final SimpleDateFormat dbDate = new SimpleDateFormat(MMDDYYYY.getConstant());
    
    public Summary() {
        super();
    }

    public Component getContent(List<RemoveDiscountDto> selecteditemList, ContractsDetailsDto dto, RemoveDiscount removeDiscount) {
        VerticalLayout vLayout = new VerticalLayout();
        this.removeDiscount = removeDiscount;
        this.selecteditemList = selecteditemList == null ? selecteditemList : new ArrayList<>(selecteditemList);
        this.dto = dto;
        vLayout.addComponent(Clara.create(getClass().getResourceAsStream("/discount/removeDiscountSummary.xml"), this));
        rebuildBtn = new Button(); // To resolve CEL-1223
        configureFields();
        configureSecurityPermissions();
        return vLayout;
    }

    public void configureFields() {
        LOGGER.debug("Entering summary configure Fields with {} " , dto.getRsSystemId());
        summaryLayout.removeAllComponents();
        summaryLayout.addComponent(summaryResultsTable);
        HorizontalLayout componentControls = ResponsiveUtils.getResponsiveControls(infoLogic.createControls());
        summaryLayout.addComponent(componentControls);
        discountLayout.addComponent(discountTable);
        setDefaultValues();
        configureSummaryTable();
        configureDiscountTable();
    }

    public void configureSummaryTable() {
        promoteTpToChDtoResultsContainer.removeAllItems();
        infoLogic.setContainerDataSource(promoteTpToChDtoResultsContainer);
        infoLogic.setPageLength(NumericConstants.TEN);
        infoLogic.sinkItemPerPageWithPageLength(false);
        summaryResultsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        summaryResultsTable.setSizeFull();
        summaryResultsTable.setHeight("370px");
        summaryResultsTable.setSelectable(true);
        summaryResultsTable.setVisibleColumns(Constants.getInstance().summarySearchColumns);
        summaryResultsTable.setColumnHeaders(Constants.getInstance().summarySearchHeaders);
        for (Object propertyId : summaryResultsTable.getVisibleColumns()) {
            summaryResultsTable.setColumnWidth(propertyId, NumericConstants.ONE_SIX_FIVE);
        }
        summaryResultsTable.setFilterBarVisible(true);
        summaryResultsTable.setFilterDecorator(new ExtDemoFilterDecorator());
        summaryResultsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        summaryResultsTable.addStyleName(ConstantsUtil.FILTERCOMBOBOX);
        for (Object propertyId : summaryResultsTable.getVisibleColumns()) {
            summaryResultsTable.setColumnWidth(propertyId, -1);
        }
        Object[] objColumn = Constants.getInstance().componentResultsColumns;
        for (Object objColumn1 : objColumn) {
            String value = objColumn1.toString();
            if (value.endsWith("Date")) {
                summaryResultsTable.setColumnAlignment(objColumn1, ExtCustomTable.Align.CENTER);
                summaryResultsTable.setConverter(value, new DateToStringConverter());
            }
        }
        summaryResultsTable.setFilterGenerator(new ExtFilterGenerator() {
            @Override
            public Container.Filter generateFilter(Object propertyId, Object value) {
                return null;
            }

            @Override
            public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {
                if (originatingField instanceof ComboBox) {
                    if (originatingField.getValue() != null) {
                        HelperDTO helperDto = (HelperDTO) originatingField.getValue();

                        return new SimpleStringFilter(propertyId, String.valueOf(helperDto.getDescription()), false, false);
                    } else {
                        return null;
                    }
                }
                return null;
            }

            @Override
            public void filterRemoved(Object propertyId) {
                return;
            }

            @Override
            public void filterAdded(Object propertyId, Class<? extends Container.Filter> filterType, Object value) {
                return;
            }

            @Override
            public Container.Filter filterGeneratorFailed(Exception reason, Object propertyId, Object value) {
                return null;
            }

            @Override
            public AbstractField<?> getCustomFilterComponent(Object propertyId) {
                AbstractLogic logic = AbstractLogic.getInstance();
                if ("contractStatus".equals(propertyId)) {
                    ComboBox marketTypeDdlb = new ComboBox();
                    logic.lazyLoadDdlb(marketTypeDdlb, "Load Contract Status Count", "Load Contract Status", BooleanConstant.getTrueFlag());
                    return marketTypeDdlb;
                }
                return null;
            }
        });
        summaryResultsTable.setFilterBarVisible(true);
        summaryResultsTable.setFilterDecorator(new ExtDemoFilterDecorator());
        Object[] objColumnSel = Constants.getInstance().summarySearchColumns;
        for (Object objColumn1 : objColumnSel) {
            String value = objColumn1.toString();
            if (value.endsWith("Date")) {
                summaryResultsTable.setColumnAlignment(objColumn1, ExtCustomTable.Align.CENTER);
                summaryResultsTable.setConverter(value, new DateToStringConverter());
            }
        }

        dto.setContractSid(selecteditemList.get(0).getContractSid());
        if (!Constants.ZEROSTRING.equals(dto.getRsSystemId()) && !Constants.NULL.equals(dto.getRsSystemId()) && !StringUtils.EMPTY.equals(dto.getRsSystemId())) {
            infoLogic.loadSetData(removeDiscount.getUserId(), removeDiscount.getSessionId(), Collections.EMPTY_LIST, "summary", dto, true);
        }
        for (RemoveDiscountDto remove : selecteditemList) {
            contractList.add(remove.getContractSid());
            rsList.add(remove.getRsSid());

        }
    }

    public void configureDiscountTable() {
        ExtFilterTreeTable leftTable;
        ExtFilterTreeTable rightTable;
        CustomTableHeaderDTO fullHeader = new CustomTableHeaderDTO();
        CustomTableHeaderDTO rightDTO;
        ExtTreeContainer<DiscountDTO> resultBean = new ExtTreeContainer<>(DiscountDTO.class, ExtContainer.DataStructureMode.MAP);
        Map<Object, Object[]> mapLeftVisibleColumns = new HashMap<>();
        Map<Object, Object[]> mapRightVisibleColumns = new HashMap<>();
        rightDTO = HeaderUtils.getSalesTabsRightTableColumns(fullHeader, Constants.QUARTERLY);
        CustomTableHeaderDTO leftDTO = HeaderUtils.getSalesTabLeftTableColumnsForPromoteTP(fullHeader);
        resultBean = new ExtTreeContainer<>(DiscountDTO.class, ExtContainer.DataStructureMode.MAP);
        resultBean.setColumnProperties(leftDTO.getProperties());
        resultBean.setColumnProperties(rightDTO.getProperties());
        tableLogic.setContainerDataSource(resultBean);
        discountTable.setSplitPosition(NumericConstants.FOUR_HUNDRED, Unit.PIXELS);
        leftTable = discountTable.getLeftFreezeAsTable();
        rightTable = discountTable.getRightFreezeAsTable();
        leftTable.markAsDirty();
        rightTable.markAsDirty();
        leftTable.setVisibleColumns(leftDTO.getSingleColumns().toArray());
        leftTable.setColumnHeaders(leftDTO.getSingleHeaders().toArray(new String[leftDTO.getSingleHeaders().size()]));
        leftTable.setDoubleHeaderVisible(true);
        leftTable.setDoubleHeaderVisibleColumns(leftDTO.getDoubleColumns().toArray());
        leftTable.setDoubleHeaderColumnHeaders(leftDTO.getDoubleHeaders().toArray(new String[leftDTO.getDoubleHeaders().size()]));
        leftTable.setColumnWidth("levelValue", NumericConstants.FOUR_HUNDRED);
        mapLeftVisibleColumns = leftDTO.getDoubleHeaderMaps();
        rightTable.setVisibleColumns(rightDTO.getSingleColumns().toArray());
        rightTable.setColumnHeaders(rightDTO.getSingleHeaders().toArray(new String[rightDTO.getSingleHeaders().size()]));
        for (int i = 0; i < rightDTO.getSingleColumns().size(); i++) {
            rightTable.setColumnAlignment(rightDTO.getSingleColumns().get(i), ExtCustomTable.Align.RIGHT);
        }
        rightTable.setDoubleHeaderVisible(true);
        rightTable.setDoubleHeaderVisibleColumns(rightDTO.getDoubleColumns().toArray());
        rightTable.setDoubleHeaderColumnHeaders(rightDTO.getDoubleHeaders().toArray(new String[rightDTO.getDoubleHeaders().size()]));
        for (int i = 0; i < rightDTO.getDoubleColumns().size(); i++) {
            rightTable.setColumnAlignment(rightDTO.getDoubleColumns().get(i), ExtCustomTable.Align.CENTER);
        }
        mapRightVisibleColumns = rightDTO.getDoubleHeaderMaps();
        discountTable.setDoubleHeaderMap(mapLeftVisibleColumns, mapRightVisibleColumns);
        loadResultTable();
    }

    /**
     * /**
     * Loads the results.
     */
    @SuppressWarnings("serial")
    private void loadResultTable() {
        LOGGER.debug("Entering loadResultTable with {} " , dto.getRsSystemId());
        List<RemoveDiscountDto> projDetails = discountLogic.getprojectionValues(removeDiscountDto, contractList, rsList);
        removeDiscountDto.setContractSid(Integer.parseInt(contractList.get(0).toString()));
        removeDiscountDto.setRsContractSid(dto.getRsSystemId());
        if (!projDetails.isEmpty() && dto.getRsSystemId() != null && !dto.getRsSystemId().equals(Constants.NULL) && !dto.getRsSystemId().equals(StringUtils.EMPTY) && !dto.getRsSystemId().equals(Constants.ZEROSTRING)) {
            tableLogic.setData(removeDiscountDto);
        }
        LOGGER.debug("Ended loadResultTable ");
    }

    public void setDefaultValues() {
        for (RemoveDiscountDto remove : selecteditemList) {
            contractNo.setValue(remove.getContractNo());
            contractName.setValue(remove.getContractName());
            contractType.setValue(remove.getMarketType());
            contractStartDate.setValue(remove.getContractstartDate() == null ? StringUtils.EMPTY : dbDate.format((Date) remove.getContractstartDate()));
            contractEndDate.setValue(remove.getContractendDate() == null ? StringUtils.EMPTY : dbDate.format((Date) remove.getContractendDate()));
        }
    }

    public List<String> removeRebate() {
        List<String> tempTransferList = new ArrayList<>();
        if (!discountLogic.getActuals(removeDiscountDto)) {
            discountLogic.updateRebate(dto.getRsSystemId()); // Removed Create projection logic over here because Projections will not get created on Rmeove Discount as per FD
        } else {
            AbstractNotificationUtils.getAlertNotification("Invalid rebate",
                    "The following Contract/RS Combination: " + contractName + " , " + dto.getRsName() + " has actual payments and cannot be removed");
        }
        return tempTransferList;
    }

    /**
     * rebuild btn logic.
     *
     * @param event the event
     */
    @UiHandler("rebuildBtn")
    public void rebuildBtnLogic(Button.ClickEvent event) {
        if (summaryResultsTable.getValue() != null) {
                new AbstractNotificationUtils() {
                    @Override
                    public void yesMethod() {
                        try {
                            RemoveDiscountDto removeDto = (RemoveDiscountDto) summaryResultsTable.getValue();
                            summaryResultsTable.removeItem(removeDto);
                        } catch (Exception e) {
                            LOGGER.error("",e);
                        }
                    }

                    @Override
                    public void noMethod() {
                        return;
                    }
                }.getConfirmationMessage("Confirmation", "Are you sure you want to keep this discount on contract ");

            } else {
                AbstractNotificationUtils.getErrorNotification("No Records Selected",
                        "Please select a record to Rebuild.");
            }
        }

    private void configureSecurityPermissions() {
        try {
            Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(String.valueOf(removeDiscount.getUserId()), "GCM-Customer Management", "Remove Discount", "Summary Tab");
            rebuildBtn.setVisible(CommonLogic.isButtonVisibleAccess("rebuildBtn", functionHM));
        } catch (Exception ex) {
            LOGGER.error("",ex);
        }
    }
}
