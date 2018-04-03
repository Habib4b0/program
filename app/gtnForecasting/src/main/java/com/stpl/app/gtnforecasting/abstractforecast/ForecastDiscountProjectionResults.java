
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.abstractforecast;

import com.stpl.app.gtnforecasting.discountprojectionresults.dto.DiscountProjectionResultsDTO;
import com.stpl.app.gtnforecasting.discountprojectionresults.logic.DPRLogic;
import com.stpl.app.gtnforecasting.discountprojectionresults.logic.NMDPRLogic;
import com.stpl.app.gtnforecasting.discountprojectionresults.logic.tablelogic.DPRTableLogic;
import com.stpl.app.gtnforecasting.logic.CommonLogic;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.ui.form.lookups.CustomTreeBuild;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.model.CustomViewMaster;
import static com.stpl.app.utils.Constants.ResourceConstants.EXCEL_IMAGE_PATH;
import static com.stpl.app.utils.Constants.ResourceConstants.GRAPH_IMAGE_PATH;
import com.stpl.ifs.ui.extfilteringtable.FreezePagedTreeTable;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.vaadin.navigator.View;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.v7.ui.Label;
import com.vaadin.v7.ui.NativeSelect;
import com.vaadin.v7.ui.OptionGroup;
import com.vaadin.v7.ui.VerticalLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.asi.container.ExtContainer;
import org.asi.container.ExtTreeContainer;
import org.asi.ui.custommenubar.CustomMenuBar;
import org.asi.ui.extfilteringtable.ExtCustomTreeTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author pvinoth
 */
public abstract class ForecastDiscountProjectionResults extends CustomComponent implements View {

    public static final Logger LOGGER = LoggerFactory
            .getLogger(ForecastDiscountProjectionResults.class);
    protected final String flavourName;

    @UiField("discountProjectionResultsSelection")
    protected Panel discountProjectionResultsSelection;

    @UiField("tableVerticalLayout")
    protected VerticalLayout tableVerticalLayout;

    @UiField("tablePanel")
    protected Panel tablePanel;

    @UiField("groupDdlbLabel")
    protected Label groupDdlbLabel;

    @UiField("discountOpgLabel")
    protected Label discountOpgLabel;

    @UiField("frequencyDdlb")
    protected ComboBox frequencyDdlb;

    @UiField("historyDdlb")
    protected ComboBox historyDdlb;

    @UiField("levelDdlb")
    protected ComboBox levelDdlb;

    @UiField("groupDdlb")
    protected NativeSelect groupDdlb;

    @UiField("customDdlb")
    protected ComboBox customDdlb;

    @UiField("levelFilterDdlb")
    protected ComboBox levelFilterDdlb;

    @UiField("periodOrderOpg")
    protected OptionGroup periodOrderOpg;

    @UiField("actualOrProjectionsOpg")
    protected OptionGroup actualOrProjectionsOpg;

    @UiField("pivotViewOpg")
    protected OptionGroup pivotViewOpg;

    @UiField("discountOpg")
    protected OptionGroup discountOpg;

    @UiField("viewOpg")
    protected OptionGroup viewOpg;

    @UiField("generateBtn")
    protected Button generateBtn;

    @UiField("resetBtn")
    protected Button resetBtn;

    @UiField("expandBtn")
    protected Button expandBtn;

    @UiField("collapseBtn")
    protected Button collapseBtn;

    @UiField("newBtn")
    protected Button newBtn;

    @UiField("editBtn")
    protected Button editBtn;

    @UiField("excelBtn")
    protected Button excelBtn;

    @UiField("graphBtn")
    protected Button graphBtn;

    @UiField("dprGridLayoutScreen")
    protected GridLayout dprGridLayoutScreen;
    @UiField("labelActualsProjections")
    protected Label labelActualsProjections;
    @UiField("labelPivotView")
    protected Label labelPivotView;
    @UiField("labelBrand")
    protected Label labelBrand;
    @UiField("labelTherapeutic")
    protected Label labelTherapeutic;
    @UiField("brand")
    protected ComboBox brand;
    @UiField("therapeuticClass")
    protected ComboBox therapeuticClass;
    @UiField("verticalLayout")
    protected VerticalLayout layout;
    @UiField("optionalLayout")
    protected HorizontalLayout optionalLayout;
    protected SessionDTO session;
    protected VerticalLayout verticalLayout;
     
     @UiField("variableMenu")
    protected CustomMenuBar variableMenu;

    protected CustomMenuBar.CustomMenuItem customMenuItem;

    @UiField("labelVar")
    protected Label labelVar;
    @UiField("levelLab")
    protected Label levelLab;
    @UiField("levelFilterLab")
    protected Label levelFilterLab;
    @UiField("viewLab")
    protected Label viewLab;
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

    private int projectionId;

    private DPRTableLogic tableLogic = new DPRTableLogic();
    private FreezePagedTreeTable resultsTable = new FreezePagedTreeTable(getTableLogic());
    /**
     * The max split position.
     */
    public static final float MAX_SPLIT_POSITION = 1000;

    /**
     * The min split position.
     */
    public static final float MIN_SPLIT_POSITION = 200;

    /**
     * The split position.
     */
    public static final float SPLIT_POSITION = 300;

    private CustomTableHeaderDTO leftHeader = new CustomTableHeaderDTO();
    private CustomTableHeaderDTO rightHeader = new CustomTableHeaderDTO();
    private CustomTableHeaderDTO fullHeader = new CustomTableHeaderDTO();
    private ExtTreeContainer<DiscountProjectionResultsDTO> resultBeanContainer = new ExtTreeContainer<>(DiscountProjectionResultsDTO.class, ExtContainer.DataStructureMode.MAP);
    private ExtTreeContainer<DiscountProjectionResultsDTO> excelResultBeanContainer = new ExtTreeContainer<>(DiscountProjectionResultsDTO.class, ExtContainer.DataStructureMode.MAP);
    private DPRLogic dprLogic = new DPRLogic();
    private NMDPRLogic nmDPRLogic = new NMDPRLogic();
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
        LOGGER.debug("Entering Load Frequency");
        loadFrequency();
        LOGGER.debug("Ending Load Frequency");
    }

    /**
     * Reset button logic.
     *
     * @param event the event
     */
    @UiHandler("resetBtn")
    public void resetButton(Button.ClickEvent event) {
        LOGGER.debug("Entering resetButton");
        resetButtonLogic();
        LOGGER.debug("Ending resetButton");
    }

    /**
     * Generate button logic.
     *
     * @param event the event
     */
    @UiHandler("generateBtn")
    public void generateButton(Button.ClickEvent event) {
        LOGGER.debug("Entering generateButton");
            generateButtonLogic();
        LOGGER.debug("Ending generateButton");
    }

    @UiHandler("newBtn")
    public void newCustomHierarchhy(Button.ClickEvent event) {
        LOGGER.debug("Entering newCustomHierarchhy");
        newCustomHierarchyLogic();
        LOGGER.debug("Ending newCustomHierarchhy");
    }

    @UiHandler("excelBtn")
    public void excelButton(Button.ClickEvent event) {
        LOGGER.debug("Entering excelButton");
        excelButtonLogic();
        LOGGER.debug("Ending excelButton");
    }

    @UiHandler("editBtn")
    public void editHierarchyBtn(Button.ClickEvent event) {
        LOGGER.debug("Entering editHierarchyBtn");
        editHierarchyBtnLogic();
        LOGGER.debug("Ending editHierarchyBtn");
    }

    @UiHandler("customDdlb")
    public void customDdlbChangeOption(Property.ValueChangeEvent event) {
        LOGGER.debug("Entering customDdlbChangeOption");
        customDdlbChangeOptionLogic();
        LOGGER.debug("Ending customDdlbChangeOption");
    }

    @UiHandler("graphBtn")
    public void graphExport(Button.ClickEvent event) {
        LOGGER.debug("Entering graphExport");
        graphExportLogics();
        LOGGER.debug("Ending graphExport");
    }

    @UiHandler("expandBtn")
    public void expandButton(Button.ClickEvent event) {
        LOGGER.debug("Entering expandButton");
        expandButtonLogic();
        LOGGER.debug("Ending expandButton");
    }

    @UiHandler("collapseBtn")
    public void collapseButton(Button.ClickEvent event) {
        LOGGER.debug("Entering collapseButtonlogic");
        collapseButtonLogic();
        LOGGER.debug("Ending collapseButtonlogic");
    }

    protected abstract void loadFrequency();

    protected abstract void resetButtonLogic();

    protected abstract void generateButtonLogic();

    protected void newCustomHierarchyLogic() {
        LOGGER.debug("newCustomHierarchhy clickEvent method starts");
        final CustomTreeBuild customTree = new CustomTreeBuild(session);
        customTree.addCloseListener(new Window.CloseListener() {

            @Override
            public void windowClose(Window.CloseEvent e) {
                if (customTree.isIsSelect()) {
                    customIdToSelect = customTree.getCustomId();
                }
       session.setCustomerViewList(CommonLogic.getCustomViewList(session.getProjectionId()));            
                loadCustomDDLB();
            }
        });
        UI.getCurrent().addWindow(customTree);
        LOGGER.debug("newCustomHierarchhy clickEvent method ends");
    }

    protected abstract void excelButtonLogic();

    protected void editHierarchyBtnLogic() {
        LOGGER.debug("Entering editHierarchyBtn");
        if (CommonLogic.editButtonValidation(customDdlb, customViewList)) {
            final CustomTreeBuild customTree = new CustomTreeBuild(session, customId);
            customTree.addCloseListener(new Window.CloseListener() {

                @Override
                public void windowClose(Window.CloseEvent e) {
                    customIdToSelect = customTree.getCustomId();
           session.setCustomerViewList(CommonLogic.getCustomViewList(session.getProjectionId()));   
                    loadCustomDDLB();
                }
            });
            UI.getCurrent().addWindow(customTree);
        }
        LOGGER.debug(" Ending editHierarchyBtn ");
    }

    protected abstract void customDdlbChangeOptionLogic();

    protected abstract void graphExportLogics();

    protected abstract void expandButtonLogic();

    protected abstract void collapseButtonLogic();

    private void replaceComponent() {
        if (CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED.equalsIgnoreCase(flavourName)) {
            if (Constant.MM.equalsIgnoreCase(session.getMarketTypeValue()) || Constant.MANAGED_MEDICAID.equalsIgnoreCase(session.getMarketTypeValue())) {
              dprGridLayoutScreen.replaceComponent(labelVar, labelPivotView);
              dprGridLayoutScreen.replaceComponent(variableMenu, pivotViewOpg);
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
                variableMenu.setVisible(false);
                labelVar.setVisible(false);
                
                
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
                labelVar.setVisible(false);
                dprGridLayoutScreen.replaceComponent(labelVar, labelPivotView);
                dprGridLayoutScreen.replaceComponent(variableMenu, pivotViewOpg);
            }
        } else if (CommonUtils.BUSINESS_PROCESS_TYPE_CHANNELS.equalsIgnoreCase(flavourName) || CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equalsIgnoreCase(flavourName)) {
            dprGridLayoutScreen.replaceComponent(discountOpgLabel, labelActualsProjections);
            dprGridLayoutScreen.replaceComponent(discountOpg, actualOrProjectionsOpg);
            dprGridLayoutScreen.replaceComponent(discountOpgLabel, labelVar);
            dprGridLayoutScreen.replaceComponent(discountOpg, variableMenu);
            dprGridLayoutScreen.replaceComponent(discountOpgLabel, labelPivotView);
            dprGridLayoutScreen.replaceComponent(discountOpg, pivotViewOpg);
            discountOpgLabel.setVisible(false);
            discountOpg.setVisible(false);
        }
    }
    
    protected List getCheckedValues() {
        List<String> result = new ArrayList<>(); 
        if (customMenuItem != null && customMenuItem.getSize() > 0) {
            List<CustomMenuBar.CustomMenuItem> items = customMenuItem.getChildren();
            for (Iterator<CustomMenuBar.CustomMenuItem> it = items.iterator(); it.hasNext();) {
                CustomMenuBar.CustomMenuItem customMenuItem1 = it.next();
                if (customMenuItem1.isChecked()) {
                    result.add(customMenuItem1.getMenuItem().getCaption());
                }
            }
            return result;
        }
        return Collections.EMPTY_LIST;
    }
     
     

    protected abstract void loadCustomDDLB();

	public int getProjectionId() {
		return projectionId;
	}

	public void setProjectionId(int projectionId) {
		this.projectionId = projectionId;
	}

	public ExtTreeContainer<DiscountProjectionResultsDTO> getResultBeanContainer() {
		return resultBeanContainer;
	}

	public void setResultBeanContainer(ExtTreeContainer<DiscountProjectionResultsDTO> resultBeanContainer) {
		this.resultBeanContainer = resultBeanContainer;
	}

	public DPRTableLogic getTableLogic() {
		return tableLogic;
	}

	public void setTableLogic(DPRTableLogic tableLogic) {
		this.tableLogic = tableLogic;
	}

	public FreezePagedTreeTable getResultsTable() {
		return resultsTable;
	}

	public void setResultsTable(FreezePagedTreeTable resultsTable) {
		this.resultsTable = resultsTable;
	}

	public CustomTableHeaderDTO getFullHeader() {
		return fullHeader;
	}

	public void setFullHeader(CustomTableHeaderDTO fullHeader) {
		this.fullHeader = fullHeader;
	}

	public CustomTableHeaderDTO getLeftHeader() {
		return leftHeader;
	}

	public void setLeftHeader(CustomTableHeaderDTO leftHeader) {
		this.leftHeader = leftHeader;
	}

	public CustomTableHeaderDTO getRightHeader() {
		return rightHeader;
	}

	public void setRightHeader(CustomTableHeaderDTO rightHeader) {
		this.rightHeader = rightHeader;
	}

	public ExtTreeContainer<DiscountProjectionResultsDTO> getExcelResultBeanContainer() {
		return excelResultBeanContainer;
	}

	public void setExcelResultBeanContainer(ExtTreeContainer<DiscountProjectionResultsDTO> excelResultBeanContainer) {
		this.excelResultBeanContainer = excelResultBeanContainer;
	}

	public DPRLogic getDprLogic() {
		return dprLogic;
	}

	public void setDprLogic(DPRLogic dprLogic) {
		this.dprLogic = dprLogic;
	}

	public NMDPRLogic getNmDPRLogic() {
		return nmDPRLogic;
	}

	public void setNmDPRLogic(NMDPRLogic nmDPRLogic) {
		this.nmDPRLogic = nmDPRLogic;
	}
}
