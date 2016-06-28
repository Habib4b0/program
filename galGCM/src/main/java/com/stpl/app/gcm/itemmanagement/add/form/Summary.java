package com.stpl.app.gcm.itemmanagement.add.form;

import com.stpl.app.customtreecontainer.CustomTreeContainer;
import com.stpl.app.gcm.common.CommonLogic;
import com.stpl.app.gcm.globalchange.dto.SelectionDTO;
import com.stpl.app.gcm.itemmanagement.add.dto.ComponentTableDTO;
import com.stpl.app.gcm.itemmanagement.add.dto.ContractDashboardDTO;
import com.stpl.app.gcm.itemmanagement.add.dto.SummaryDTO;
import com.stpl.app.gcm.itemmanagement.add.logic.SummaryLogic;
import com.stpl.app.gcm.itemmanagement.add.logic.tablelogic.ComponentDetailsTableLogic;
import com.stpl.app.gcm.itemmanagement.add.logic.tablelogic.ContractDashboardTableLogic;
import com.stpl.app.gcm.itemmanagement.itemabstract.logic.abstracttablelogic.ItemDetailsTableLogic;
import com.stpl.app.gcm.itemmanagement.index.dto.ItemIndexDto;
import com.stpl.app.gcm.itemmanagement.itemabstract.form.ComponentInfo;
import com.stpl.app.gcm.itemmanagement.itemabstract.logic.AbstractLogic;
import com.stpl.app.gcm.security.SecurityLogic;
import com.stpl.app.gcm.security.StplSecurity;
import com.stpl.app.gcm.util.AbstractNotificationUtils;
import com.stpl.app.gcm.util.Constants;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.ifs.ui.DateToStringConverter;
import com.stpl.ifs.util.ExcelExportforBB;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.server.Resource;
import com.vaadin.server.Sizeable;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;

import com.vaadin.ui.VerticalLayout;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
import org.asi.ui.extfilteringtable.ExtFilterTreeTable;
import org.asi.ui.extfilteringtable.freezetable.FreezePagedTreeTable;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 */
public class Summary extends CustomComponent {

    @UiField("selectionCriteria")
    Panel selectionCriteria;
    @UiField("itemNo")
    public TextField itemNo;
    @UiField("itemName")
    public TextField itemName;
    @UiField("itemDesc")
    public TextField itemDesc;
    @UiField("therapeuticClass")
    public TextField therapeuticClass;
    @UiField("brand")
    public TextField brand;
    @UiField("Contract Processing Dashboard")
    Panel ContractProcessingDashboard;
    @UiField("Add Trading Partner Results")
    Panel addTradingPartnerResults;
    @UiField("lay")
    VerticalLayout lay;
    @UiField("addremove")
    Button remove;
    @UiField("excelBtn")
    Button excelBtn;
    @UiField("contractDashboardResults")
    Panel ContractDashboardResults;
    @UiField("contractDashboardLay")
    VerticalLayout contractDashboardLay;
    Table table2;
    Button remove1 = new Button("REMOVE");
    Button populate = new Button("POPULATE");
    @UiField("Component Details")
    Panel ComponentDetails;
    @UiField("componentDetailsLay")
    VerticalLayout componentDetailsLay;
    ItemDetailsTableLogic contractTableLogic = new ItemDetailsTableLogic();
    public ExtPagedTable addContractTable = new ExtPagedTable(contractTableLogic);
    ComponentDetailsTableLogic componenttableLogic = new ComponentDetailsTableLogic();
    public ExtPagedTable componenttable = new ExtPagedTable(componenttableLogic);
    ContractDashboardTableLogic contractDashboardTableLogic = new ContractDashboardTableLogic();
    public FreezePagedTreeTable contractDashBoardtable = new FreezePagedTreeTable(contractDashboardTableLogic);
    public List<ItemIndexDto> selecteditemList;
    public static final Logger LOGGER = Logger.getLogger(AddContractSelection.class);
    Object[] visibleColumn = {Constants.CONTRACT_HOLDER, Constants.CONTRACT_NO, Constants.CONTRACT_NAME, Constants.MARKET_TYPE, Constants.START_DATE, Constants.END_DATE, "cfp", "ifp", "ps", "rs"};
    String[] columnHeader = {"Contract Holder", "Contract No", "Contract Name", "Market Type", "Start Date", "End Date", Constants.CFP, Constants.IFP, Constants.PS, Constants.RS};
    Object[] componentColumn = {"itemNo", "itemName", "therapyClass", "brand", "status", Constants.START_DATE, Constants.END_DATE, "rebatePlan", "formulaId"};
    String[] componentHeader = {Constants.ITEM_NO, Constants.ITEM_NAME, Constants.THERAPY_CLASS, Constants.BRAND, "Status", "Start Date", "End Date", "Rebate Plan", "Formula ID"};
    Object[] contractDashBoardColumn = {"component", "id", "number", "name"};
    String[] contractDashBoardHeader = {"Component", "ID", "Number", "Name"};
    CustomTreeContainer<ContractDashboardDTO> contractDashBoardContainer = new CustomTreeContainer<ContractDashboardDTO>(ContractDashboardDTO.class);
    SelectionDTO selection;
    ContractDashboardDTO componentInfoDTO = new ContractDashboardDTO();
    SummaryDTO summaryItem = new SummaryDTO();
    private final Resource excelExportImage = new ThemeResource("../../icons/excel.png");
    Object dashBoardItemId;
    Object contractItemId;
    BeanItemContainer<ComponentTableDTO> componentContainer = new BeanItemContainer<ComponentTableDTO>(ComponentTableDTO.class);
    BeanItemContainer<SummaryDTO> contractContainer = new BeanItemContainer<SummaryDTO>(SummaryDTO.class);
    ComponentInfo component;
    ExtFilterTreeTable leftTable;
    ExtFilterTreeTable rightTable;
    SummaryDTO summaryDTO = new SummaryDTO();
    ContractDashboardDTO dto = new ContractDashboardDTO();
    SummaryLogic logic = new SummaryLogic();
    AbstractLogic abstractLogic = AbstractLogic.getInstance();
    final StplSecurity stplSecurity = new StplSecurity();
    Map<String, AppPermission> functionHM = new HashMap<String, AppPermission>();
    private String userId = VaadinSession.getCurrent().getAttribute(Constants.USER_ID).toString();

    public Summary(List<ItemIndexDto> selecteditemList) {
        this.selecteditemList = selecteditemList;
    }

    public Component getContent(List<ItemIndexDto> selecteditemList, final SelectionDTO selection) throws Exception {
        this.selection = selection;
        this.selecteditemList = selecteditemList;
        VerticalLayout layout = new VerticalLayout();
        layout.addComponent(Clara.create(getClass().getResourceAsStream("/item/AddItemSummary.xml"), this));
        layout.setMargin(true);
        layout.setSpacing(true);
        Panel panel = new Panel();
        panel.setContent(layout);
        ConfigureTable();
        configureComponentDetailsTable();
        configureDashBoardTable();
        loadSelectionCriteria();
        loadItemResults();
        return panel;
    }

    private void loadSelectionCriteria() {
        try {
            if (selecteditemList != null && selecteditemList.size() == 1) {
                ItemIndexDto dto = selecteditemList.get(0);
                itemNo.setValue(dto.getItemNo());
                itemName.setValue(dto.getItemName());
                itemDesc.setValue(dto.getItemDesc());
                therapeuticClass.setValue(dto.getTherapeuticClass());
                brand.setValue(dto.getBrand());
                itemNo.setImmediate(true);
            }
            itemNo.setReadOnly(Boolean.TRUE);
            itemName.setReadOnly(Boolean.TRUE);
            itemDesc.setReadOnly(Boolean.TRUE);
            therapeuticClass.setReadOnly(Boolean.TRUE);
            brand.setReadOnly(Boolean.TRUE);

        } catch (Exception e) {
         LOGGER.error("loadSelectionCriteria ::: "+e.getMessage());
        }
    }

    @UiHandler("addremove")
    public void removeButtonContract(Button.ClickEvent event) throws SystemException, PortalException, Exception {
        if (addContractTable.getValue() != null) {
            new AbstractNotificationUtils() {
                @Override
                public void yesMethod() {
                    removeTreeItemLogic(false);
                }

                @Override
                public void noMethod() {
                }
            }.getConfirmationMessage("Confirmation", "Are you sure you want to remove the selected Contract from the Add ITEM process? It will be removed and added back to the Available List of Contracts in the Contract Selection screen");
        } else {
            MessageBox.showPlain(Icon.INFO, "Error", "Please select a value to remove. Then try again.", ButtonId.OK);
        }
    }

    @UiHandler("excelBtn")
    public void excelExport(Button.ClickEvent event) {
        try {
            List<SummaryDTO> list = logic.getContractResults(selection, 0, 1000000000);
            ExcelExportforBB.createWorkSheet(addContractTable.getColumnHeaders(), list.size(), this, UI.getCurrent(), "Item_Details");
        } catch (Exception e) {

            LOGGER.error(e.getMessage() + "at excel export");
        }
    }

    public void createWorkSheetContent(final Integer start, final Integer end, final PrintWriter printWriter) throws SystemException, PortalException, Exception {
        try {
            if (end != 0) {
                List<SummaryDTO> list = logic.getContractResults(selection, 0, 1000000000);
                ExcelExportforBB.createFileContent(addContractTable.getVisibleColumns(), list, printWriter);
            }
        } catch (Exception e) {

            LOGGER.error(e.getMessage());
        }
    }

    Component ConfigureTable() {
        contractTableLogic.setContainerDataSource(contractContainer);
        addContractTable.setVisibleColumns(visibleColumn);
        addContractTable.setColumnHeaders(columnHeader);
        addContractTable.setFilterBarVisible(true);
        addContractTable.setWidth(100, Unit.PERCENTAGE);
        addContractTable.setHeight(400, Unit.PIXELS);
        addContractTable.setPageLength(5);
        addContractTable.addStyleName("filterbar");
        for (Object propertyId : addContractTable.getVisibleColumns()) {
            addContractTable.setColumnWidth(propertyId, -1);
        }
        addContractTable.setFilterGenerator(new ExtFilterGenerator() {
            public Container.Filter generateFilter(Object propertyId, Object value) {
                return null;
            }

            public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {
                if (originatingField instanceof ComboBox) {
                    if (originatingField.getValue() != null) {
                        HelperDTO dto = (HelperDTO) originatingField.getValue();

                        return new SimpleStringFilter(propertyId, String.valueOf(dto.getId()), false, false);
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
                if (Constants.MARKET_TYPE.equals(propertyId)) {
                    ComboBox marketTypeDdlb = new ComboBox();
                    abstractLogic.LazyLoadDdlb(marketTypeDdlb, "Load Market Type Count", "Load Market Type", true);
                    return marketTypeDdlb;
                }
                return null;
            }
        });
        addContractTable.setFilterBarVisible(true);
        addContractTable.setFilterDecorator(new ExtDemoFilterDecorator());
        addContractTable.setSizeFull();
        addContractTable.setSelectable(true);
        contractTableLogic.setPageLength(5);
        HorizontalLayout controls = contractTableLogic.createControls();
        HorizontalLayout controlLayout = CommonLogic.getResponsiveControls(controls);
        lay.addComponent(addContractTable);
        lay.addComponent(controlLayout);
        excelBtn.setIcon(excelExportImage, "Excel Export");
        addContractTable.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Method called when available results value is changed.
             */
            @SuppressWarnings("PMD")
            public void valueChange(final Property.ValueChangeEvent event) {
                contractItemClick(event.getProperty().getValue());
                SummaryDTO dto = (SummaryDTO) event.getProperty().getValue();
                selection.setReset(false);
                contractDashboardTableLogic.loadSetData(selection, loadDashBoardSid());

            }
        });
        addContractTable.setConverter(Constants.START_DATE, new DateToStringConverter());
        addContractTable.setConverter(Constants.END_DATE, new DateToStringConverter());
        return addContractTable;
    }

    Component configureComponentDetailsTable() {
        component = new ComponentInfo(Constants.RS, selection);
        componentDetailsLay.addComponent(component);
        return componentDetailsLay;
    }

    Component configureDashBoardTable() {
        leftTable = contractDashBoardtable.getLeftFreezeAsTable();
        rightTable = contractDashBoardtable.getRightFreezeAsTable();
        leftTable.markAsDirty();
        rightTable.markAsDirty();
        rightTable.setVisible(false);
        contractDashBoardtable.setSplitPosition(1000, Sizeable.Unit.PIXELS);
        contractDashboardTableLogic.setContainerDataSource(contractDashBoardContainer);
        contractDashboardTableLogic.setPageLength(5);
        contractDashboardTableLogic.sinkItemPerPageWithPageLength(false);
        leftTable.setVisibleColumns(contractDashBoardColumn);
        leftTable.setColumnHeaders(contractDashBoardHeader);
        leftTable.setWidth("100%");
        leftTable.setSelectable(true);
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setSpacing(true);
        remove1.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                if (leftTable.getValue() != null) {
                    new AbstractNotificationUtils() {
                        @Override
                        public void yesMethod() {
                            removeTreeItemLogic(true);
                        }

                        @Override
                        public void noMethod() {
                        }
                    }.getConfirmationMessage("Confirmation", "Are you sure you want to remove the selected Contract from the Add ITEM process? It will be removed and added back to the Available List of Contracts in the Contract Selection screen.");
                } else {
                    MessageBox.showPlain(Icon.INFO, "Error", "Please select a value to remove. Then try again. ", ButtonId.OK);
                }

            }
        });
        populate.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                if (leftTable.getValue() != null) {
                    populateBtnLogic();
                } else {
                    MessageBox.showPlain(Icon.INFO, "Error", "Please select a value in the Contract structure. Then try again.  ", ButtonId.OK);
                }
            }
        });
        horizontalLayout.addComponent(remove1);
        horizontalLayout.addComponent(populate);
        contractDashboardLay.addComponent(contractDashBoardtable);
        HorizontalLayout controls = contractDashboardTableLogic.createControls();
        HorizontalLayout controlLayout = CommonLogic.getResponsiveControls(controls);
        contractDashboardLay.addComponent(controlLayout);
        contractDashboardLay.addComponent(horizontalLayout);
        leftTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            public void itemClick(ItemClickEvent event) {
                resultsItemClick(event.getItemId());
            }
        });
        return contractDashBoardtable;
    }

    ContractDashboardDTO loadTempId() {
        ContractDashboardDTO dto = new ContractDashboardDTO();
        dto.setContractSid("2");
        dto.setCfpSid("1");
        dto.setIfpSid("1");
        dto.setPsSid("1");
        dto.setRsSid("1");
        return dto;
    }

    ContractDashboardDTO loadDashBoardSid() {
        if (summaryItem != null) {
            dto.setContractSid(summaryItem.getContractSid());
            dto.setCfpSid(summaryItem.getCfpSid());
            dto.setIfpSid(summaryItem.getIfpSid());
            dto.setPsSid(summaryItem.getPsSid());
            dto.setRsSid(summaryItem.getRsSid());
        }
        return dto;
    }

    /**
     * Results item click.
     *
     * @param obj the id
     * @throws PortalException the portal exception
     */
    protected void resultsItemClick(final Object obj) {
        if (obj == null) {
            componentInfoDTO = null;
            dashBoardItemId = null;
        } else {
            componentInfoDTO = (ContractDashboardDTO) obj;
            dashBoardItemId = obj;
        }
    }

    /**
     * Results item click.
     *
     * @param obj the id
     * @throws PortalException the portal exception
     */
    protected void contractItemClick(final Object obj) {
        if (obj == null) {
            summaryItem = null;
            contractItemId = null;
        } else {
            summaryItem = (SummaryDTO) obj;
            contractItemId = obj;
        }
    }

    /**
     * populate Logic
     */
    private void populateBtnLogic() {
        String flag = StringUtils.EMPTY;
        if (componentInfoDTO.getLevelNo() == 2) {
            flag = Constants.CFP;
            selection.setCfpContractSid(componentInfoDTO.getMasterSid() == null ? 0 : Integer.valueOf(componentInfoDTO.getMasterSid()));
            component.fireComponentListener(Constants.RS, selection);
        } else if (componentInfoDTO.getLevelNo() == 3) {
            flag = Constants.IFP;
            selection.setIfpConteractSid(componentInfoDTO.getMasterSid() == null ? 0 : Integer.valueOf(componentInfoDTO.getMasterSid()));
            component.fireComponentListener(Constants.RS, selection);
        } else if (componentInfoDTO.getLevelNo() == 4) {
            flag = Constants.PS;
            selection.setPsContractSid(componentInfoDTO.getMasterSid() == null ? 0 : Integer.valueOf(componentInfoDTO.getMasterSid()));
            component.fireComponentListener(Constants.RS, selection);
        } else if (componentInfoDTO.getLevelNo() == 5) {
            flag = Constants.RS;
            selection.setRsContractSid(componentInfoDTO.getMasterSid() == null ? 0 : Integer.valueOf(componentInfoDTO.getMasterSid()));
        }
        component.fireComponentListener(flag, selection);
        component.setVisibleComponents(flag);
    }

    /**
     * Remove logic
     *
     * @param isTreeRemove - to identify
     */
    private void removeTreeItemLogic(boolean isTreeRemove) {
        Object contractSid;
        if (isTreeRemove) {
            contractSid = componentInfoDTO.getContractSid();
        } else {
            contractSid = summaryItem.getContractSid();
        }
        logic.deleteContractTree(selection, contractSid);
        if (isTreeRemove) {
            contractDashboardTableLogic.loadSetData(selection, loadDashBoardSid());
            contractTableLogic.loadSetData(selection, Boolean.TRUE);
        } else {
            contractTableLogic.loadSetData(selection, Boolean.TRUE);
        }
    }

    public void loadItemResults() {
        contractTableLogic.loadSetData(selection, Boolean.TRUE);

        for (Object object : addContractTable.getVisibleColumns()) {
            if (String.valueOf(object).contains("Date")) {
                addContractTable.setColumnAlignment(object, ExtCustomTable.Align.CENTER);
                addContractTable.setConverter(object, new DateToStringConverter());
            }
        }
    }

    /**
     * Security
     */
    private void setButtonSecurity() {
        SecurityLogic.isPermitted(functionHM, "remove1", remove1);
        SecurityLogic.isPermitted(functionHM, "populate", populate);
        SecurityLogic.isPermitted(functionHM, "addremove", remove);
        SecurityLogic.isPermitted(functionHM, "excelBtn", excelBtn);
    }

    public boolean isItemSelected() {
        return addContractTable.getItemIds().isEmpty();
    }
}
