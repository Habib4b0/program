
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.abstractforecast;

import com.stpl.app.customtreecontainer.CustomTreeContainer;
import com.stpl.app.galforecasting.discountprojectionresults.dto.DiscountProjectionResultsDTO;
import com.stpl.app.galforecasting.discountprojectionresults.logic.DPRLogic;
import com.stpl.app.galforecasting.discountprojectionresults.logic.NMDPRLogic;
import com.stpl.app.galforecasting.discountprojectionresults.logic.tablelogic.DPRTableLogic;
import com.stpl.app.galforecasting.logic.CommonLogic;
import com.stpl.app.galforecasting.sessionutils.SessionDTO;
import com.stpl.app.galforecasting.ui.form.lookups.CustomTreeBuild;
import com.stpl.app.galforecasting.utils.CommonUtils;
import com.stpl.app.galforecasting.utils.Constant;
import com.stpl.app.model.CustomViewMaster;
import static com.stpl.app.utils.Constants.ResourceConstants.EXCEL_IMAGE_PATH;
import static com.stpl.app.utils.Constants.ResourceConstants.GRAPH_IMAGE_PATH;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.vaadin.data.Property;
import com.vaadin.navigator.View;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.ExtCustomTreeTable;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import java.util.ArrayList;
import java.util.List;
import org.asi.ui.extfilteringtable.freezetable.FreezePagedTreeTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author pvinoth
 */
public abstract class ForecastDiscountProjectionResults extends CustomComponent implements View {

    public static final Logger LOGGER = Logger
            .getLogger(ForecastDiscountProjectionResults.class);
    private String flavourName;

    @UiField("discountProjectionResultsSelection")
    public Panel discountProjectionResultsSelection;

    @UiField("tableVerticalLayout")
    public VerticalLayout tableVerticalLayout;

    @UiField("tablePanel")
    public Panel tablePanel;

    @UiField("groupDdlbLabel")
    public Label groupDdlbLabel;

    @UiField("discountOpgLabel")
    public Label discountOpgLabel;

    @UiField("frequencyDdlb")
    public ComboBox frequencyDdlb;

    @UiField("historyDdlb")
    public ComboBox historyDdlb;

    @UiField("levelDdlb")
    public ComboBox levelDdlb;

    @UiField("groupDdlb")
    public NativeSelect groupDdlb;

    @UiField("customDdlb")
    public ComboBox customDdlb;

    @UiField("levelFilterDdlb")
    public ComboBox levelFilterDdlb;

    @UiField("periodOrderOpg")
    public OptionGroup periodOrderOpg;

    @UiField("actualOrProjectionsOpg")
    public OptionGroup actualOrProjectionsOpg;

    @UiField("pivotViewOpg")
    public OptionGroup pivotViewOpg;

    @UiField("discountOpg")
    public OptionGroup discountOpg;

    @UiField("viewOpg")
    public OptionGroup viewOpg;

    @UiField("generateBtn")
    public Button generateBtn;

    @UiField("resetBtn")
    public Button resetBtn;

    @UiField("expandBtn")
    public Button expandBtn;

    @UiField("collapseBtn")
    public Button collapseBtn;

    @UiField("newBtn")
    public Button newBtn;

    @UiField("editBtn")
    public Button editBtn;

    @UiField("excelBtn")
    public Button excelBtn;

    @UiField("graphBtn")
    public Button graphBtn;

    @UiField("dprGridLayoutScreen")
    public GridLayout dprGridLayoutScreen;
    @UiField("labelActualsProjections")
    public Label labelActualsProjections;
    @UiField("labelPivotView")
    public Label labelPivotView;
    @UiField("labelBrand")
    public Label labelBrand;
    @UiField("labelTherapeutic")
    public Label labelTherapeutic;
    @UiField("brand")
    public ComboBox brand;
    @UiField("therapeuticClass")
    public ComboBox therapeuticClass;
    @UiField("verticalLayout")
    public VerticalLayout layout;
    @UiField("optionalLayout")
    public HorizontalLayout optionalLayout;
    SessionDTO session;
    protected VerticalLayout verticalLayout;

    @UiField("variables")
    public OptionGroup variables;
    @UiField("labelVarView")
    public Label labelVarView;
    @UiField("levelLab")
    public Label levelLab;
    @UiField("levelFilterLab")
    public Label levelFilterLab;
    @UiField("viewLab")
    public Label viewLab;
    protected int customIdToSelect = 0;
    protected int customId = 0;

    /**
     * The excel export image.
     */
    public final Resource excelExportImage = new ThemeResource(
            EXCEL_IMAGE_PATH.getConstant());

    /**
     * The graph image.
     */
    public final Resource graphImage = new ThemeResource(
            GRAPH_IMAGE_PATH.getConstant());

    public int projectionId;

    public DPRTableLogic tableLogic = new DPRTableLogic();
    public FreezePagedTreeTable resultsTable = new FreezePagedTreeTable(tableLogic);
    /**
     * The max split position.
     */
    public final float maxSplitPosition = 1000;

    /**
     * The min split position.
     */
    public final float minSplitPosition = 200;

    /**
     * The split position.
     */
    public final float splitPosition = 300;

    public CustomTableHeaderDTO leftHeader = new CustomTableHeaderDTO();
    public CustomTableHeaderDTO rightHeader = new CustomTableHeaderDTO();
    public CustomTableHeaderDTO fullHeader = new CustomTableHeaderDTO();
    public CustomTreeContainer<DiscountProjectionResultsDTO> resultBeanContainer = new CustomTreeContainer<DiscountProjectionResultsDTO>(DiscountProjectionResultsDTO.class);
    public CustomTreeContainer<DiscountProjectionResultsDTO> excelResultBeanContainer = new CustomTreeContainer<DiscountProjectionResultsDTO>(DiscountProjectionResultsDTO.class);
    public DPRLogic dprLogic = new DPRLogic();
    public NMDPRLogic nmDPRLogic = new NMDPRLogic();
    protected List<CustomViewMaster> customViewList = new ArrayList<>();
    protected ExtCustomTreeTable exceltable = new ExtCustomTreeTable();

    public ForecastDiscountProjectionResults(String flavourName, SessionDTO session) {
        this.flavourName = flavourName;
        this.session = session;
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/abstractforecast/forecast-discount-projection-results.xml"), this));
        replaceComponent();
        groupDdlb.setVisible(false);
        groupDdlbLabel.setVisible(false);

    }

    @UiHandler("frequencyDdlb")
    public void loadFrequencyddlb(Property.ValueChangeEvent event) {
        LOGGER.info("Entering Load Frequency");
        loadFrequency();
        LOGGER.info("Ending Load Frequency");
    }

    /**
     * Reset button logic.
     *
     * @param event the event
     */
    @UiHandler("resetBtn")
    public void resetButton(Button.ClickEvent event) {
        LOGGER.info("Entering resetButton");
        resetButtonLogic();
        LOGGER.info("Ending resetButton");
    }

    /**
     * Generate button logic.
     *
     * @param event the event
     */
    @UiHandler("generateBtn")
    public void generateButton(Button.ClickEvent event) {
        LOGGER.info("Entering generateButton");
        generateButtonLogic();
        LOGGER.info("Ending generateButton");
    }

    @UiHandler("newBtn")
    public void newCustomHierarchhy(Button.ClickEvent event) {
        LOGGER.info("Entering newCustomHierarchhy");
        newCustomHierarchyLogic();
        LOGGER.info("Ending newCustomHierarchhy");
    }

    @UiHandler("excelBtn")
    public void excelButton(Button.ClickEvent event) {
        LOGGER.info("Entering excelButton");
        excelButtonLogic();
        LOGGER.info("Ending excelButton");
    }

    @UiHandler("editBtn")
    public void editHierarchyBtn(Button.ClickEvent event) {
        LOGGER.info("Entering editHierarchyBtn");
        editHierarchyBtnLogic();
        LOGGER.info("Ending editHierarchyBtn");
    }

    @UiHandler("customDdlb")
    public void customDdlbChangeOption(Property.ValueChangeEvent event) {
        LOGGER.info("Entering customDdlbChangeOption");
        customDdlbChangeOptionLogic();
        LOGGER.info("Ending customDdlbChangeOption");
    }

    @UiHandler("graphBtn")
    public void graphExport(Button.ClickEvent event) {
        LOGGER.info("Entering graphExport");
        graphExportLogics();
        LOGGER.info("Ending graphExport");
    }

    @UiHandler("expandBtn")
    public void expandButton(Button.ClickEvent event) {
        LOGGER.info("Entering expandButton");
        expandButtonLogic();
        LOGGER.info("Ending expandButton");
    }

    @UiHandler("collapseBtn")
    public void collapseButton(Button.ClickEvent event) {
        LOGGER.info("Entering collapseButtonlogic");
        collapseButtonLogic();
        LOGGER.info("Ending collapseButtonlogic");
    }

    protected abstract void loadFrequency();

    protected abstract void resetButtonLogic();

    protected abstract void generateButtonLogic();

    protected void newCustomHierarchyLogic() {
        LOGGER.info("newCustomHierarchhy clickEvent method starts");
        final CustomTreeBuild customTree = new CustomTreeBuild(Constant.ADD_FULL_SMALL, session);
        customTree.addCloseListener(new Window.CloseListener() {

            @Override
            public void windowClose(Window.CloseEvent e) {
                if (customTree.isIsSelect()) {
                    customIdToSelect = customTree.getCustomId();
                }
                loadCustomDDLB();
            }
        });
        UI.getCurrent().addWindow(customTree);
        LOGGER.info("newCustomHierarchhy clickEvent method ends");
    }

    protected abstract void excelButtonLogic();

    protected void editHierarchyBtnLogic() {
        LOGGER.info("Entering editHierarchyBtn");
        if (CommonLogic.editButtonValidation(customDdlb, customViewList)) {
            final CustomTreeBuild customTree = new CustomTreeBuild(Constant.EDIT, session, customId);
            customTree.addCloseListener(new Window.CloseListener() {

                @Override
                public void windowClose(Window.CloseEvent e) {
                    customIdToSelect = customTree.getCustomId();
                    loadCustomDDLB();
                }
            });
            UI.getCurrent().addWindow(customTree);
        }
        LOGGER.info(" Ending editHierarchyBtn ");
    }

    protected abstract void customDdlbChangeOptionLogic();

    protected abstract void graphExportLogics();

    protected abstract void expandButtonLogic();

    protected abstract void collapseButtonLogic();

    private void replaceComponent() {
        if (CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED.equalsIgnoreCase(flavourName)) {
            if (Constant.MM.equalsIgnoreCase(session.getMarketTypeValue()) || Constant.Managed_Medicaid.equalsIgnoreCase(session.getMarketTypeValue())) {
              dprGridLayoutScreen.replaceComponent(labelVarView, labelPivotView);
              dprGridLayoutScreen.replaceComponent(variables, pivotViewOpg);
                discountOpgLabel.setVisible(true);
                discountOpg.setVisible(true);
                labelTherapeutic.setVisible(false);
                labelBrand.setVisible(false);
                therapeuticClass.setVisible(false);
                brand.setVisible(false);
                optionalLayout.setVisible(false);
                graphBtn.setVisible(false);
                excelBtn.setVisible(true);
                groupDdlb.setVisible(false);
                groupDdlbLabel.setVisible(false);
                levelDdlb.setVisible(false);
                levelFilterDdlb.setVisible(false);
                expandBtn.setVisible(false);
                collapseBtn.setVisible(false);
                viewOpg.setVisible(false);
                newBtn.setVisible(false);
                levelLab.setVisible(false);
                levelFilterLab.setVisible(false);
                viewLab.setVisible(false);
                variables.setVisible(false);
                labelVarView.setVisible(false);
                
                
            } else {
                discountOpgLabel.setVisible(true);
                discountOpg.setVisible(true);
                optionalLayout.setVisible(true);
                labelTherapeutic.setVisible(false);
                labelBrand.setVisible(false);
                therapeuticClass.setVisible(false);
                brand.setVisible(false);
                graphBtn.setVisible(true);
                excelBtn.setVisible(true);
                groupDdlb.setVisible(false);
                groupDdlbLabel.setVisible(false);
                labelVarView.setVisible(false);
                dprGridLayoutScreen.replaceComponent(labelVarView, labelPivotView);
                dprGridLayoutScreen.replaceComponent(variables, pivotViewOpg);
            }
        } else if (CommonUtils.BUSINESS_PROCESS_TYPE_CHANNELS.equalsIgnoreCase(flavourName) || CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equalsIgnoreCase(flavourName)) {

            dprGridLayoutScreen.replaceComponent(discountOpgLabel, labelActualsProjections);
            dprGridLayoutScreen.replaceComponent(discountOpg, actualOrProjectionsOpg);
            dprGridLayoutScreen.replaceComponent(discountOpgLabel, labelVarView);
            dprGridLayoutScreen.replaceComponent(discountOpg, variables);
            dprGridLayoutScreen.replaceComponent(discountOpgLabel, labelPivotView);
            dprGridLayoutScreen.replaceComponent(discountOpg, pivotViewOpg);
            discountOpgLabel.setVisible(false);
            discountOpg.setVisible(false);
        }
    }

    protected abstract void loadCustomDDLB();
}
