/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.dataselection.ui.lookups;

import com.stpl.app.arm.dataselection.dto.CalcultionProfileTableGenerator;
import com.stpl.app.arm.dataselection.dto.CalculationProfileDTO;
import com.stpl.app.arm.dataselection.dto.DataSelectionDTO;
import com.stpl.app.arm.dataselection.dto.ViewDTO;
import com.stpl.app.arm.dataselection.logic.CalculationProfileLogic;
import com.stpl.app.arm.dataselection.logic.tablelogic.CalculationProfileSearchTableLogic;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.serviceUtils.ConstantsUtils;
import com.stpl.app.utils.CommonUtils;
import static com.stpl.app.utils.ResponsiveUtils.getResponsiveControls;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.server.Sizeable;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;
import org.asi.ui.customwindow.CustomWindow;
import org.asi.ui.customwindow.CustomWindowConstant;

/**
 *
 * @author Vinodhini.Chandrasek
 */
public class CalculationProfileLookUp extends CustomWindow {

    @UiField("viewLookUp")
    private CustomTextField viewTextField;

    @UiField("profileName")
    private TextField profileName;

    @UiField("profileDescription")
    private TextField profileDescription;

    @UiField("searchBtn")
    private Button searchBtn;

    @UiField("resetBtn")
    private Button resetBtn;

    @UiField("submitBtn")
    private Button submitBtn;

    @UiField("saveBtn")
    private Button saveBtn;

    @UiField("resetTableBtn")
    private Button resetTableBtn;

    @UiField("saveViewBtn")
    private Button saveViewBtn;

    @UiField("deleteViewBtn")
    private Button deleteViewBtn;

    @UiField("upBtn")
    private Button upBtn;

    @UiField("downBtn")
    private Button downBtn;

    @UiField("resultsTableLayout")
    private VerticalLayout resultsTableLayout;

    @UiField("modeOptionGroup")
    private OptionGroup modeOptionGroup;

    @UiField("calculationTable")
    private ExtFilterTable calculationTable;

    public CalculationProfileSearchTableLogic tableLogic = new CalculationProfileSearchTableLogic();

    BeanItemContainer<CalculationProfileDTO> resultsContainer = new BeanItemContainer<>(CalculationProfileDTO.class);

    BeanItemContainer<CalculationProfileDTO> calculationContainer = new BeanItemContainer<>(CalculationProfileDTO.class);

    public ExtPagedTable resultsTable = new ExtPagedTable(tableLogic);
    private CalculationProfileDTO calculationProfileDTO = new CalculationProfileDTO();
    public static final Logger LOGGER = Logger.getLogger(CalculationProfileLookUp.class);
    boolean selected;
    String screenName;

    CalculationProfileLogic calculationProfileLogic = new CalculationProfileLogic();
    List<CalculationProfileDTO> calculationList = new ArrayList<>();
    ViewSearchLookUp viewLookUp;
    ViewDTO selectedLookUp = new ViewDTO();
    String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
    /**
     * The src table bean id.
     */
    private Object srcTableBeanId;
    private String masterSid = StringUtils.EMPTY;

    public CalculationProfileLookUp(String indicator, String screenName) {
        this.screenName = screenName;
        init();
        configureLookUp();
        configureTable();
        configuerFields();
    }

    private void init() {
        setContent(Clara.create(getClass().getResourceAsStream("/data_selection/calculationProfileLookup.xml"), this));
    }

    private void configureLookUp() {
        modeOptionGroup.focus();
        setClosable(true);
        center();
        setModal(true);
//        setMinimizeBtnVisible(false);
        addStyleName(CustomWindowConstant.VALO_THEME_CUSTOMWINDOW);
        addStyleName("bootstrap-ui");
        addStyleName("bootstrap");
        addStyleName("bootstrap-forecast bootstrap-nm");
        setCaption("Calculation Profile");
        setWidth(70f, Sizeable.Unit.PERCENTAGE);
        setHeight(95f, Sizeable.Unit.PERCENTAGE);
        deleteViewBtn.setEnabled(false);
    }

    @UiHandler("searchBtn")
    public void searchButtonLogic(Button.ClickEvent event) {
        if (StringUtils.isBlank(String.valueOf(profileName.getValue())) && StringUtils.isBlank(String.valueOf(profileDescription.getValue()))) {
            AbstractNotificationUtils.getErrorNotification("No Search Values Entered",
                    "No search criteria has been entered.  Please try again.");
        } else {
            loadResultsTable();
            if (resultsTable.size() == 0) {
                AbstractNotificationUtils.getErrorNotification("No Matching Results",
                        "There are no records that match the search criteria.  Please try again.");
                resultsTable.removeAllItems();
            } else {
                CommonUtils.successNotification("Search Completed");
            }
        }
    }

    @UiHandler("resetBtn")
    public void resetButtonLogic(Button.ClickEvent event) {
        MessageBox.showPlain(Icon.QUESTION, "Reset Confirmation", "Are you sure you want to reset the Profile Options group box?", new MessageBoxListener() {
            /**
             * Called when a Button has been clicked .
             *
             */
            @SuppressWarnings("PMD")
            public void buttonClicked(final ButtonId buttonId) {
                if (buttonId.name().equalsIgnoreCase("yes")) {
                    LOGGER.debug("Entering Reset operation");
                    resetFields();
                    LOGGER.debug("Ending Reset operation");
                }
            }
        }, ButtonId.YES, ButtonId.NO);
    }

    @UiHandler("deleteViewBtn")
    public void deleteViewBtnClickLogic(Button.ClickEvent event) {

        try {

            if (StringUtils.isEmpty(viewTextField.getValue()) || StringUtils.isEmpty(selectedLookUp.getCalculationProfileMasterSid())) {
                AbstractNotificationUtils.getErrorNotification("Delete View Confirmation",
                        "Please first select a saved view to delete.");
            } else if (selectedLookUp.getViewCreatedBy() != Integer.parseInt(userId)) {
                AbstractNotificationUtils.getErrorNotification("Delete View Confirmation",
                        "You can only delete Views that were created by you.");
            } else {
                new AbstractNotificationUtils() {
                    @Override
                    public void yesMethod() {
                        if (calculationProfileLogic.deleteViewLogic(Integer.valueOf(selectedLookUp.getCalculationProfileMasterSid()))) {
                            resetPopup();
                            CommonUtils.successNotification(selectedLookUp.getViewName() + " View Deleted Successfully");
                        }
                    }

                    @Override
                    public void noMethod() {
                    }
                }.getConfirmationMessage("Delete View Confirmation", "Are you sure you want to delete the View?");
            }

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    @UiHandler("submitBtn")
    public void submitBtnLogic(Button.ClickEvent event) {
        try {
            LOGGER.debug("submitBtn starts ");

            if (!("0").equals(calculationProfileDTO.getCalculationProfileId())) {
                Collection<?> saveCalculationList = calculationTable.getItemIds();
                if (calculationProfileLogic.checkAndSaveCalculationProfile(calculationProfileDTO, (List<CalculationProfileDTO>) saveCalculationList, false)) {
                    AbstractNotificationUtils.getErrorNotification("Missing Information", "Please select all mandatory fields.");
                } else {
                    new AbstractNotificationUtils() {
                        @Override
                        public void yesMethod() {
                            calculationProfileLogic.insert(calculationProfileLogic.getUpdateQuery(calculationProfileDTO.getUpdateViewQuery(), calculationProfileDTO));
                            setSelected(Boolean.TRUE);
                            close();
                        }
                        @Override
                        public void noMethod() {
                        }
                    }.getConfirmationMessage("Submit Confirmation", "Are you sure you want to submit these Adjustment Types for inclusion?");
                }
            } else if (saveLogic(false)) {
                AbstractNotificationUtils.getErrorNotification("Missing Information", "Please select all mandatory fields.");
            } else if (isDuplicateProfile(profileName.getValue())) {
                boolean value = isResultsTableIdentical(calculationContainer.getItemIds());
                if (value) {
                    AbstractNotificationUtils.getErrorNotification("Existing Profile Name",
                            "The entered Profile Name already exists.  Please enter a different Profile Name.");
                } else {
                    submitLogic(false);
                }
            } else {
                submitLogic(true);
            }
            LOGGER.debug("submitBtn ends ");
        } catch (Exception ex) {
            LOGGER.error(ex);
        }

    }

    @UiHandler("closeBtn")
    public void closeButtonLogic(Button.ClickEvent event) {

        new AbstractNotificationUtils() {
            @Override
            public void yesMethod() {
                setSelected(Boolean.FALSE);
                close();
            }

            @Override
            public void noMethod() {
            }
        }.getConfirmationMessage("Close Confirmation", "Are you sure you want to close the pop-up? Nothing will be submitted. ");

    }

    private void configureTable() {
        tableLogic.setTempPageLength(10);
        tableLogic.setItemsPerPage(10);
        tableLogic.sinkItemPerPageWithPageLength(false);
        tableLogic.setContainerDataSource(resultsContainer);
        resultsTable.setVisibleColumns(new Object[]{"profileName", "profileDesc"});
        resultsTable.setColumnHeaders(new String[]{"Profile Name", "Profile Description"});

        resultsTable.setSelectable(true);
        resultsTable.addStyleName(ARMUtils.FILTER_TABLE);
        resultsTable.addStyleName("table-header-normal");
        resultsTable.setFilterBarVisible(true);
        resultsTable.setFilterDecorator(new ExtDemoFilterDecorator());
        resultsTable.setImmediate(true);
        resultsTable.setSizeFull();

        resultsTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {

            @Override
            public void itemClick(ItemClickEvent event) {
             
                srcTableBeanId = event.getItemId();
                BeanItem<?> targetItem;
                if (srcTableBeanId instanceof BeanItem<?>) {
                    targetItem = (BeanItem<?>) srcTableBeanId;
                } else if (srcTableBeanId instanceof CalculationProfileDTO) {
                    targetItem = new BeanItem<>((CalculationProfileDTO) srcTableBeanId);
                } else {
                    targetItem = null;
                }
                calculationProfileDTO = (CalculationProfileDTO) targetItem.getBean();
                if (calculationProfileDTO != null) {
                        calculationTable.removeAllItems();
                        calculationList = calculationProfileLogic.loadSelectedCalculation(calculationProfileDTO.getCalculationProfileId());
                        calculationContainer.addAll(calculationList);
                    }
              
            }
        });
        resultsTableLayout.addComponent(resultsTable);
        resultsTableLayout.addComponent(getResponsiveControls(tableLogic.createControls()));
        tableLogic.configureSearchData(false, StringUtils.EMPTY, "");
        addCalculationTable();
    }

    public CalculationProfileDTO getCalculationProfileDTO() {
        return calculationProfileDTO;
    }

    public void resetFields() {
        modeOptionGroup.select("Add");
        viewTextField.setValue(StringUtils.EMPTY);
        profileName.setValue(StringUtils.EMPTY);
        profileDescription.setValue(StringUtils.EMPTY);
        calculationProfileDTO = new CalculationProfileDTO();
        
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    void loadResultsTable() {
        LOGGER.debug("Entering Load Results Table");
        tableLogic.configureSearchData(true, profileName.getValue(), profileDescription.getValue());
        resultsTable.addStyleName(ARMUtils.FILTER_TABLE);
        resultsTable.addStyleName("table-header-normal");
        resultsTable.setFilterDecorator(new ExtDemoFilterDecorator());
        resultsTable.setImmediate(true);

        resultsTable.setSelectable(true);
        resultsTable.markAsDirtyRecursive();
        LOGGER.debug("Ending Load Results Table");

    }

    private void configuerFields() {
        modeOptionGroup.addItem("Add");
        modeOptionGroup.addItem("Search");
        modeOptionGroup.select("Add");
        viewTextField.addClickListener(new CustomTextField.ClickListener() {

            @Override
            public void click(CustomTextField.ClickEvent event) {
                loadViewLookUp();
                if (StringUtils.isBlank(String.valueOf(viewTextField))) {
                    deleteViewBtn.setEnabled(false);
            }
            }
        });
    }

    public void addCalculationTable() {
        LOGGER.debug("Entering addCalculationTable ");
        try {

            calculationTable.markAsDirty();
            calculationTable.setContainerDataSource(calculationContainer);
            calculationTable.setVisibleColumns(new Object[]{"adjustmentType", "accountType", "include", "indicator"});
            calculationTable.setColumnHeaders(new String[]{"Adjustment Type", "Account Type", "Include", "+/- Indicator"});
            calculationTable.setPageLength(6);

            calculationTable.setSizeFull();
            calculationTable.setTableFieldFactory(new CalcultionProfileTableGenerator());
            calculationTable.setEditable(true);
            calculationTable.setImmediate(true);
            calculationTable.setSelectable(true);

            calculationTable.setFilterBarVisible(false);
//            calculationTable.setFilterDecorator(new ExtDemoFilterDecorator());
            calculationTable.setValidationVisible(false);
            calculationTable.addStyleName(ARMUtils.FILTER_TABLE);
            calculationTable.addStyleName("table-header-normal");

            calculationList = calculationProfileLogic.loadCalculationTable();

            calculationContainer.addAll(calculationList);

            LOGGER.debug(" Ends addCalculationTable  ");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void loadViewLookUp() {
        viewLookUp = new ViewSearchLookUp("Calculation Profile");
        getUI().addWindow(viewLookUp);
        viewLookupCloseListener();
    }

    public void viewLookupCloseListener() {
        viewLookUp.addCloseListener(closeListener);
    }
    Window.CloseListener closeListener = new Window.CloseListener() {

        @Override
        public void windowClose(Window.CloseEvent e) {
            ViewSearchLookUp lookUp = (ViewSearchLookUp) e.getWindow();
            selectedLookUp = lookUp.getViewDTO();
            if (lookUp.isSelected()) {
                viewTextField.setValue(lookUp.getViewDTO().getViewName());
                profileName.setValue(lookUp.getViewDTO().getCalculationProfileName());
                profileDescription.setValue(lookUp.getViewDTO().getCalculationProfileDescrition());
                calculationTable.removeAllItems();
                resultsTable.removeAllItems();
                calculationProfileDTO.setProfileName(lookUp.getViewDTO().getCalculationProfileName());
                calculationProfileDTO.setProfileDesc(lookUp.getViewDTO().getCalculationProfileDescrition());
                calculationProfileDTO.setCalculationProfileId(lookUp.getViewDTO().getCalculationProfileMasterSid());
                calculationList = calculationProfileLogic.loadSelectedCalculation(lookUp.getViewDTO().getCalculationProfileMasterSid());
                calculationContainer.addAll(calculationList);
                 deleteViewBtn.setEnabled(true);
                
            }
        }
    };

    @UiHandler("upBtn")
    public void upBtnLogic(Button.ClickEvent event) {
        try {
            if (calculationTable.getValue() != null) {
                upDownBtnLogic(0);
            } else {
                AbstractNotificationUtils.getErrorNotification("Up", "Please select a row to move up in the list");
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }

    }

    @UiHandler("downBtn")
    public void downBtnLogic(Button.ClickEvent event) {
        try {
            if (calculationTable.getValue() != null) {
                int calcListSize = calculationList.size() - 1;
                upDownBtnLogic(calcListSize);
            } else {
                AbstractNotificationUtils.getErrorNotification("Down", "Please select a row to move down in the list.");
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }

    }

    void upDownBtnLogic(int calcListPosition) {
        calculationProfileDTO = (CalculationProfileDTO) calculationTable.getValue();
        calculationList.remove(calculationProfileDTO);
        calculationList.add(calcListPosition, calculationProfileDTO);
        calculationContainer.removeAllItems();
        calculationContainer.addAll(calculationList);
    }

    @UiHandler("saveBtn")
    public void saveBtnLogic(Button.ClickEvent event) {
        try {
            LOGGER.debug("saveBtnLogic starts ");
            if (!("0").equals(calculationProfileDTO.getCalculationProfileId())) {

                Collection<?> saveCalculationList = calculationTable.getItemIds();

                if (calculationProfileLogic.checkAndSaveCalculationProfile(calculationProfileDTO, (List<CalculationProfileDTO>) saveCalculationList, false)) {
                    AbstractNotificationUtils.getErrorNotification("Missing Information", "Please select all mandatory fields.");
                } else {

                    new AbstractNotificationUtils() {
                        @Override
                        public void yesMethod() {
                            calculationProfileLogic.insert(calculationProfileLogic.getUpdateQuery(calculationProfileDTO.getUpdateViewQuery(), calculationProfileDTO));

                        }

                        @Override
                        public void noMethod() {
                        }
                    }.getConfirmationMessage("Save Confirmation", "Are you sure you want to save this Calculation Profile?");
                }
            }else {
              if (saveLogic(false)) {
                    AbstractNotificationUtils.getErrorNotification("Missing Information", "Please select all mandatory fields.");
                } else if (("0").equals(calculationProfileDTO.getCalculationProfileId()) && isDuplicateProfile(profileName.getValue())) {
                    AbstractNotificationUtils.getErrorNotification("Existing Profile Name",
                            "The entered Profile Name already exists.  Please enter a different Profile Name.");
                } else {

                
                    new AbstractNotificationUtils() {
                        @Override
                        public void yesMethod() {
                            calculationProfileLogic.insert(calculationProfileDTO.getQuery());
                        }

                        @Override
                        public void noMethod() {
                        }
                    }.getConfirmationMessage("Save Confirmation", "Are you sure you want to save this Calculation Profile?");
                }
            }
            
            LOGGER.debug("saveBtnLogic ends ");
        } catch (Exception ex) {
            LOGGER.error(ex);
        }

    }

    @UiHandler("saveViewBtn")
    public void saveViewBtnLogic(Button.ClickEvent event) {
        try {
            LOGGER.debug("saveViewBtn starts ");
            if (saveLogic(true)) {
                AbstractNotificationUtils.getErrorNotification("Save View Confirmation", "Please enter/select all mandatory fields.");
            } else {

                new AbstractNotificationUtils() {
                    @Override
                    public void yesMethod() {
                        calculationProfileDTO.setAddUpdateFlag(StringUtils.isEmpty(viewTextField.getValue()));
                        CalculationProfileSaveViewLookUp saveViewLookUp = new CalculationProfileSaveViewLookUp(selectedLookUp, calculationProfileDTO);
                        getUI().addWindow(saveViewLookUp);

                    }

                    @Override
                    public void noMethod() {
                    }
                }.getConfirmationMessage("Save View Confirmation", "Are you sure you want to save the View?");
            }
            LOGGER.debug("saveViewBtn ends ");
        } catch (Exception ex) {
            LOGGER.error(ex);
        }

    }

    boolean saveLogic(boolean isView) {
        boolean missingInfo = true;
        Collection<?> saveCalculationList = calculationTable.getItemIds();
        calculationProfileDTO.setQuery(StringUtils.EMPTY);
        if (StringUtils.isNotBlank(profileName.getValue())) {
            calculationProfileDTO.setProfileName(profileName.getValue());
        } else {
            return missingInfo;
        }
        if (StringUtils.isNotBlank(profileDescription.getValue())) {
            calculationProfileDTO.setProfileDesc(profileDescription.getValue());
        } else {
            return missingInfo;
        }

        missingInfo = calculationProfileLogic.checkAndSaveCalculationProfile(calculationProfileDTO, (List<CalculationProfileDTO>) saveCalculationList, isView);
        return missingInfo;
    }

    /**
     * Mode option.
     *
     * @param event the event
     */
    @UiHandler("modeOptionGroup")
    public void modeOptionGroup(Property.ValueChangeEvent event) {
        try {
            LOGGER.debug("Inside Add or Search option value change listener");
            modeOptionGroup.setImmediate(true);
            if ("Add".equals(String.valueOf(modeOptionGroup.getValue()))) {
                searchBtn.setImmediate(true);
                searchBtn.setEnabled(false);
                viewTextField.setImmediate(true);
                viewTextField.setEnabled(true);

            } else {
                viewTextField.setImmediate(true);
                viewTextField.setEnabled(false);
                searchBtn.setImmediate(true);
                searchBtn.setEnabled(true);

            }

            LOGGER.debug("Add or Search option value change listener ends");

        } catch (Exception e) {
            LOGGER.error(e);
        }
    }
    
      private boolean isDuplicateProfile(final String viewName) throws SystemException {
        LOGGER.debug("Entering isDuplicateView method with viewName " + viewName);
        int val = calculationProfileLogic.isDuplicateProfile(viewName);
        masterSid = val == 0 ? StringUtils.EMPTY : String.valueOf(val);
        return !masterSid.isEmpty();
    }
  
    @UiHandler("resetTableBtn")
    public void resetTableBtnLogic(Button.ClickEvent event) {
        MessageBox.showPlain(Icon.QUESTION, "Reset Confirmation", "Are you sure you want to reset the pop-up?", new MessageBoxListener() {
            /**
             * Called when a Button has been clicked .
             *
             */
            @SuppressWarnings("PMD")
            public void buttonClicked(final ButtonId buttonId) {
                if (buttonId.name().equalsIgnoreCase("yes")) {
                    LOGGER.debug("Entering Reset operation");
                    resetPopup();
                    LOGGER.debug("Ending Reset operation");
                }
            }
        }, ButtonId.YES, ButtonId.NO);
    }
    
    public void resetPopup() {
        resetFields();
        resultsTable.removeAllItems();
        resultsContainer.removeAllItems();
        calculationTable.removeAllItems();
        calculationContainer.removeAllItems();
        calculationList.clear();
        calculationList = calculationProfileLogic.loadCalculationTable();
        calculationContainer.addAll(calculationList);
    }

    public void loadSummarySetup(DataSelectionDTO dataSelectionDTO) {
        masterSid = dataSelectionDTO.getCalculationProfileMasterSid();
        Object[] obj = calculationProfileLogic.getProfileNameAndDesc(masterSid);
        profileName.setValue(String.valueOf(obj[0]));
        profileDescription.setValue(String.valueOf(obj[1]));
        calculationTable.removeAllItems();
        calculationList = calculationProfileLogic.loadSelectedCalculation(masterSid);
        calculationContainer.addAll(calculationList);
    }

    private boolean isResultsTableIdentical(List<CalculationProfileDTO> calculationList) {
        LOGGER.debug("Entering isResultsTableIdentical method");
        return calculationProfileLogic.isResultsTableIdentical(calculationList, masterSid);
    }
    
    public void submitLogic(final boolean alreadySavedFlag) {
        new AbstractNotificationUtils() {
            @Override
            public void yesMethod() {
                if (alreadySavedFlag) {
                    String calcProfileSid = calculationProfileLogic.submitAndSelectCalcProfileMasterId(calculationProfileDTO.getQuery());
                    calculationProfileDTO.setCalculationProfileId(calcProfileSid);
                } else {
                    calculationProfileDTO.setCalculationProfileId(masterSid);
                }
                setSelected(Boolean.TRUE);
                close();
            }

            @Override
            public void noMethod() {
            }
        }.getConfirmationMessage("Submit Confirmation", "Are you sure you want to submit these Adjustment Types for inclusion?");
    }
}
