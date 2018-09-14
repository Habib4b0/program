/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.adjustmentrateconfiguration.ui.form.lookups;

import com.stpl.app.arm.adjustmentrateconfiguration.dto.LookUpDTO;
import com.stpl.app.arm.adjustmentrateconfiguration.logic.tablelogic.ExclustionLookupTableLogic;
import com.stpl.app.arm.dataselection.dto.ViewFilterGenerator;
import com.stpl.app.arm.utils.ARMUtils;
import static com.stpl.app.utils.ResponsiveUtils.getResponsiveControls;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.constants.ARMMessages;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.event.ItemClickEvent;
import com.vaadin.ui.Button;
import com.vaadin.v7.ui.TextField;
import com.vaadin.v7.ui.VerticalLayout;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.apache.commons.lang.StringUtils;
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
 * @author Gopinath.Mathiyalaga
 */
public class ExclusionAndInventoryRateLookUp extends Window {

    @UiField("resultsTableLayoutEX")
    private VerticalLayout resultsTableLayoutDS;

    @UiField("viewNameEX")
    private TextField viewNameEX;

    @UiField("searchButtonEX")
    private Button searchButtonEX;
    @UiField("selectButtonEX")
    private Button selectButtonEX;
    @UiField("resetButtonEX")
    private Button resetButtonEX;
    @UiField("closeButtonEX")
    private Button closeButtonEX;
    private ExclustionLookupTableLogic tableLogic = new ExclustionLookupTableLogic();

    private ExtPagedTable resultsTable = new ExtPagedTable(tableLogic);

    private BeanItemContainer<LookUpDTO> resultsContainer = new BeanItemContainer<>(LookUpDTO.class);

    protected String customerProductHier;

    protected String lookupName;

    private LookUpDTO exRateDTO = new LookUpDTO();
    private static boolean flag = false;

    private static final Logger LOGGER = LoggerFactory.getLogger(ExclusionAndInventoryRateLookUp.class);
    private String exclusionAndInclusionVal = StringUtils.EMPTY;
    private String inventCalculationVal = StringUtils.EMPTY;

    public ExclusionAndInventoryRateLookUp(String exclusionAndInclusionVal, String inventCalculationVal) {
        super();
        this.exclusionAndInclusionVal = exclusionAndInclusionVal;
        this.inventCalculationVal = inventCalculationVal;
        init();
        configureFields();
        configureTable();
    }

    private void init() {
        setContent(Clara.create(getClass().getResourceAsStream("/adjustment_rate_config/exclusion_inventory_ratePopup.xml"), this));
        addStyleName("bootstrap");
        addStyleName("bootstrap-bb");
    }

    private void configureFields() {
        setDraggable(true);
        setClosable(true);
        center();
        setModal(true);
        setCaption("Exclusion Details");
        setWidth("1200px");
        setHeight("700px");
    }

    private void configureTable() {
        resultsTable.setFilterDecorator(new ExtDemoFilterDecorator());
        resultsTable.setFilterGenerator(new ViewFilterGenerator());
        tableLogic.setContainerDataSource(resultsContainer);
        resultsTable.setVisibleColumns(ARMUtils.getExclusionRateLookupHeaders());
        resultsTable.setColumnHeaders(ARMUtils.getExclusionRateLookupColumns());
        resultsTable.setSelectable(true);
        resultsTable.setImmediate(true);
        resultsTable.setSizeFull();
        resultsTable.setPageLength(NumericConstants.TEN);
        selectButtonEX.setEnabled(false);
        resultsTable.setColumnAlignment("createdDate", ExtCustomTable.Align.CENTER);
        resultsTable.setFilterBarVisible(true);
        resultsTable.addStyleName("table-header-normal");
        resultsTable.addStyleName("filterbar");
        resultsTable.addStyleName("filtertable");
        resultsTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {

            @Override
            public void itemClick(ItemClickEvent event) {
                if (event.getItem() != null) {
                    selectButtonEX.setEnabled(true);
                }
            }
        });
        resultsTableLayoutDS.addComponent(resultsTable);
        resultsTableLayoutDS.addComponent(getResponsiveControls(tableLogic.createControls()));

    }

    @UiHandler("searchButtonEX")
    public void loadTableValues(Button.ClickEvent event) {
        if (!viewNameEX.getValue().trim().isEmpty()) {
            resultsContainer.removeAllItems();
            exRateDTO = new LookUpDTO();
            exRateDTO.setExclusionAndInclusionValue(exclusionAndInclusionVal);
            exRateDTO.setInventCalculationValue(inventCalculationVal);
            exRateDTO.setViewName(viewNameEX.getValue());
            if (!tableLogic.configureSearchData(exRateDTO, true)) {
                AbstractNotificationUtils.getErrorNotification("Error", ARMMessages.getNoResultsMessage());
            }
        } else {
            AbstractNotificationUtils.getErrorNotification("Error", ARMMessages.getSaveMessageID005());
        }
    }

    @UiHandler("resetButtonEX")
    public void resetFields(Button.ClickEvent event) {
        MessageBox.showPlain(Icon.QUESTION, "Confirmation", "Are you sure you want to Reset? All selected/entered values will be removed.", new MessageBoxListener() {
            /**
             * Called when a Button has been clicked .
             *
             */
            @SuppressWarnings("PMD")
            @Override
            public void buttonClicked(final ButtonId buttonId) {
                if ("yes".equalsIgnoreCase(buttonId.name())) {
                    resetFields();
                }
            }
        }, ButtonId.YES, ButtonId.NO);
    }

    @UiHandler("selectButtonEX")
    public void selectFields(Button.ClickEvent event) {
        if (resultsTable.getValue() != null) {
            LookUpDTO rateDTO = (LookUpDTO) resultsTable.getValue();
            rateDTO.setSelectFlag(true);
            setFlag(true);
            setExRateDTO(rateDTO);
            close();
        }
    }

    @UiHandler("closeButtonEX")
    public void resetTableFields(Button.ClickEvent event) {
        setFlag(false);
        exRateDTO = new LookUpDTO();
        close();
    }

    public void resetFields() {
        LOGGER.debug("Reset Fields");
        viewNameEX.setValue(StringUtils.EMPTY);
    }

    public void resetTable() {
        resultsContainer.removeAllItems();
    }

    public void reloadScreen(String inventCalculationVal) {
        viewNameEX.setValue(StringUtils.EMPTY);
        resultsContainer.removeAllItems();
        this.inventCalculationVal = inventCalculationVal;

    }

    public LookUpDTO getExRateDTO() {
        return exRateDTO;
    }

    public void setExRateDTO(LookUpDTO exRateDTO) {
        this.exRateDTO = exRateDTO;
    }

    public static boolean isFlag() {
        return flag;
    }

    public static void setFlag(boolean flag) {
        ExclusionAndInventoryRateLookUp.flag = flag;
    }

    private void writeObject(ObjectOutputStream exclInvDtoout) throws IOException {
        exclInvDtoout.defaultWriteObject();
    }

    private void readObject(ObjectInputStream exclInvDtoout) throws IOException, ClassNotFoundException {
        exclInvDtoout.defaultReadObject();
    }

    public static boolean getFlag() {
        return flag;
    }

}
