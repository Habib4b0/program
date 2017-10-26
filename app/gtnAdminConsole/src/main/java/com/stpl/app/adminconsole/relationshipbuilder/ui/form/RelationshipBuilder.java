/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.relationshipbuilder.ui.form;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.jboss.logging.Logger;
import com.stpl.app.adminconsole.common.util.CommonUIUtil;
import com.stpl.app.model.HierarchyLevelDefinition;
import com.stpl.app.model.HistHierarchyLevelDefn;
import com.stpl.app.adminconsole.relationshipbuilder.dto.HierarchyLevelsDTO;
import com.stpl.app.adminconsole.relationshipbuilder.dto.RelationshipBuilderDTO;
import com.stpl.app.adminconsole.relationshipbuilder.logic.RelationBuilderLogic;
import com.stpl.app.adminconsole.relationshipbuilder.ui.view.RelationshipBuilderView;
import com.stpl.app.adminconsole.relationshipbuilder.util.CommonUtils;
import com.stpl.app.adminconsole.util.AbstractNotificationUtils;
import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.stpl.app.adminconsole.util.ErrorCodeUtil;
import com.stpl.app.adminconsole.util.ErrorCodes;
import com.stpl.app.adminconsole.util.ValidationUtils;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.CustomGridLayout;
import com.stpl.ifs.ui.errorhandling.ErrorLabel;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.Position;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Notification;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;

import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;


/**
 * The Class RelationshipBuilder.
 *
 * @author vishalakshi
 */
public class RelationshipBuilder extends CustomComponent implements View {

   
    private static final Logger LOGGER = Logger.getLogger(RelationshipBuilder.class);
   
    private Label space = new Label("&nbsp;", ContentMode.HTML);
   
    private ErrorLabel errorMsg = new ErrorLabel();
   
    private TextField relationshipName = new TextField();
   
    private TextField relationshipDesc = new TextField();
   
    private PopupDateField startDate = new PopupDateField();
   
    private NativeSelect hierarchyNameDdlb = new NativeSelect();
   
    private OptionGroup relationshipType = new OptionGroup();
   
    private Button save = new Button("SAVE");
   
    private Button resetAll = new Button("RESET");
   
    private Button backButton = new Button("BACK");
   
    private RelationshipBuilderDTO relationshipBuilderDTO;
    
    private CustomFieldGroup relationshipBuilderBinder;
    
    private RelationBuilderLogic logic = new RelationBuilderLogic();
   
    private TextField hierarchy = new TextField();
    
    private ExtFilterTable selectedResults;
    
    private Panel levelPanel = new Panel();
    
    private List<BeanItemContainer<HierarchyLevelsDTO>> availableLevelsBeanList = new ArrayList<BeanItemContainer<HierarchyLevelsDTO>>();
   
    private List<BeanItemContainer<HierarchyLevelsDTO>> selectedLevelsBeanList = new ArrayList<BeanItemContainer<HierarchyLevelsDTO>>();
  
    private List<ExtFilterTable> availableTableList = new ArrayList<ExtFilterTable>();
   
    private List<ExtFilterTable> selectedTableList = new ArrayList<ExtFilterTable>();
   
    private GridLayout layout;
   
    private HorizontalLayout levelLayout;
    
    private Map<String, List<HierarchyLevelsDTO>> levelValuesList;
    
    private Map<String, List<HierarchyLevelsDTO>> savedLevelValuesList;
    
    private Tree hierarchyTree = new Tree();
   
    private String currentTreeStructure = StringUtils.EMPTY;
    
    private BeanItemContainer<HierarchyLevelsDTO> finalSelectedResultsBean = new BeanItemContainer<HierarchyLevelsDTO>(HierarchyLevelsDTO.class);

    /**
     * Instantiates a new relationship builder.
     *
     * @param relationshipBuilderBinder the relationship builder binder
     * @param relationshipBuilderDTO the relationship builder dto
     * @throws Exception
     * @throws SystemException
     */
    public RelationshipBuilder(final CustomFieldGroup relationshipBuilderBinder, final RelationshipBuilderDTO relationshipBuilderDTO) throws SystemException {
        super();
        LOGGER.debug("inside RelationshipBuilder constructor");
        this.relationshipBuilderDTO = relationshipBuilderDTO;
        this.relationshipBuilderBinder = relationshipBuilderBinder;
        init();
        LOGGER.debug(" RelationshipBuilder constructor ends");
    }

    /**
     * @return the space
     */
    public Label getSpace() {
        return space;
    }

    /**
     * @param space the space to set
     */
    public void setSpace(final Label space) {
        this.space = space;
    }

    /**
     * @return the errorMsg
     */
    public ErrorLabel getErrorMsg() {
        return errorMsg;
    }

    /**
     * @param errorMsg the errorMsg to set
     */
    public void setErrorMsg(final ErrorLabel errorMsg) {
        this.errorMsg = errorMsg;
    }

    /**
     * @return the relationshipName
     */
    public TextField getRelationshipName() {
        return relationshipName;
    }

    /**
     * @param relationshipName the relationshipName to set
     */
    public void setRelationshipName(final TextField relationshipName) {
        this.relationshipName = relationshipName;
    }

    /**
     * @return the relationshipDesc
     */
    public TextField getRelationshipDesc() {
        return relationshipDesc;
    }

    /**
     * @param relationshipDesc the relationshipDesc to set
     */
    public void setRelationshipDesc(final TextField relationshipDesc) {
        this.relationshipDesc = relationshipDesc;
    }

    /**
     * @return the startDate
     */
    public DateField getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(final PopupDateField startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the hierarchyNameDdlb
     */
    public NativeSelect getHierarchyNameDdlb() {
        return hierarchyNameDdlb;
    }

    /**
     * @param hierarchyNameDdlb the hierarchyNameDdlb to set
     */
    public void setHierarchyNameDdlb(final NativeSelect hierarchyNameDdlb) {
        this.hierarchyNameDdlb = hierarchyNameDdlb;
    }

    /**
     * @return the relationshipType
     */
    public OptionGroup getRelationshipType() {
        return relationshipType;
    }

    /**
     * @param relationshipType the relationshipType to set
     */
    public void setRelationshipType(final OptionGroup relationshipType) {
        this.relationshipType = relationshipType;
    }

    /**
     * @return the save
     */
    public Button getSave() {
        return save;
    }

    /**
     * @param save the save to set
     */
    public void setSave(final Button save) {
        this.save = save;
    }

    /**
     * @return the resetAll
     */
    public Button getResetAll() {
        return resetAll;
    }

    /**
     * @param resetAll the resetAll to set
     */
    public void setResetAll(final Button resetAll) {
        this.resetAll = resetAll;
    }

    /**
     * @return the backButton
     */
    public Button getBackButton() {
        return backButton;
    }

    /**
     * @param backButton the backButton to set
     */
    public void setBackButton(final Button backButton) {
        this.backButton = backButton;
    }

    /**
     * @return the relationshipBuilderDTO
     */
    public RelationshipBuilderDTO getRelationshipBuilderDTO() {
        return relationshipBuilderDTO;
    }

    /**
     * @param relationshipBuilderDTO the relationshipBuilderDTO to set
     */
    public void setRelationshipBuilderDTO(final RelationshipBuilderDTO relationshipBuilderDTO) {
        this.relationshipBuilderDTO = relationshipBuilderDTO;
    }

    /**
     * @return the relationshipBuilderBinder
     */
    public CustomFieldGroup getRelationshipBuilderBinder() {
        return relationshipBuilderBinder;
    }

    /**
     * @param relationshipBuilderBinder the relationshipBuilderBinder to set
     */
    public void setRelationshipBuilderBinder(final CustomFieldGroup relationshipBuilderBinder) {
        this.relationshipBuilderBinder = relationshipBuilderBinder;
    }

    /**
     * @return the logic
     */
    public RelationBuilderLogic getLogic() {
        return logic;
    }

    /**
     * @param logic the logic to set
     */
    public void setLogic(final RelationBuilderLogic logic) {
        this.logic = logic;
    }

    /**
     * @return the hierarchy
     */
    public TextField getHierarchy() {
        return hierarchy;
    }

    /**
     * @param hierarchy the hierarchy to set
     */
    public void setHierarchy(final TextField hierarchy) {
        this.hierarchy = hierarchy;
    }

    /**
     * @return the selectedResults
     */
    public ExtFilterTable getSelectedResults() {
        return selectedResults;
    }

    /**
     * @param selectedResults the selectedResults to set
     */
    public void setSelectedResults(final ExtFilterTable selectedResults) {
        this.selectedResults = selectedResults;
    }

    /**
     * @return the levelPanel
     */
    public Panel getLevelPanel() {
        return levelPanel;
    }

    /**
     * @param levelPanel the levelPanel to set
     */
    public void setLevelPanel(final Panel levelPanel) {
        this.levelPanel = levelPanel;
    }

    /**
     * @return the availableLevelsBeanList
     */
    public List<BeanItemContainer<HierarchyLevelsDTO>> getAvailableLevelsBeanList() {
        return availableLevelsBeanList;
    }

    /**
     * @param availableLevelsBeanList the availableLevelsBeanList to set
     */
    public void setAvailableLevelsBeanList(final List<BeanItemContainer<HierarchyLevelsDTO>> availableLevelsBeanList) {
        this.availableLevelsBeanList = availableLevelsBeanList;
    }

    /**
     * @return the selectedLevelsBeanList
     */
    public List<BeanItemContainer<HierarchyLevelsDTO>> getSelectedLevelsBeanList() {
        return selectedLevelsBeanList;
    }

    /**
     * @param selectedLevelsBeanList the selectedLevelsBeanList to set
     */
    public void setSelectedLevelsBeanList(final List<BeanItemContainer<HierarchyLevelsDTO>> selectedLevelsBeanList) {
        this.selectedLevelsBeanList = selectedLevelsBeanList;
    }

    /**
     * @return the availableTableList
     */
    public List<ExtFilterTable> getAvailableTableList() {
        return availableTableList;
    }

    /**
     * @param availableTableList the availableTableList to set
     */
    public void setAvailableTableList(final List<ExtFilterTable> availableTableList) {
        this.availableTableList = availableTableList;
    }

    /**
     * @return the selectedTableList
     */
    public List<ExtFilterTable> getSelectedTableList() {
        return selectedTableList;
    }

    /**
     * @param selectedTableList the selectedTableList to set
     */
    public void setSelectedTableList(final List<ExtFilterTable> selectedTableList) {
        this.selectedTableList = selectedTableList;
    }

    /**
     * @return the layout
     */
    public GridLayout getLayout() {
        return layout;
    }

    /**
     * @param layout the layout to set
     */
    public void setLayout(final GridLayout layout) {
        this.layout = layout;
    }

    /**
     * @return the levelLayout
     */
    public HorizontalLayout getLevelLayout() {
        return levelLayout;
    }

    /**
     * @param levelLayout the levelLayout to set
     */
    public void setLevelLayout(final HorizontalLayout levelLayout) {
        this.levelLayout = levelLayout;
    }

    /**
     * @return the levelValuesList
     */
    public Map<String, List<HierarchyLevelsDTO>> getLevelValuesList() {
        return levelValuesList;
    }

    /**
     * @param levelValuesList the levelValuesList to set
     */
    public void setLevelValuesList(final Map<String, List<HierarchyLevelsDTO>> levelValuesList) {
        this.levelValuesList = levelValuesList;
    }

    /**
     * @return the savedLevelValuesList
     */
    public Map<String, List<HierarchyLevelsDTO>> getSavedLevelValuesList() {
        return savedLevelValuesList;
    }

    /**
     * @param savedLevelValuesList the savedLevelValuesList to set
     */
    public void setSavedLevelValuesList(final Map<String, List<HierarchyLevelsDTO>> savedLevelValuesList) {
        this.savedLevelValuesList = savedLevelValuesList;
    }

    /**
     * @return the hierarchyTree
     */
    public Tree getHierarchyTree() {
        return hierarchyTree;
    }

    /**
     * @param hierarchyTree the hierarchyTree to set
     */
    public void setHierarchyTree(final Tree hierarchyTree) {
        this.hierarchyTree = hierarchyTree;
    }

    /**
     * @return the currentTreeStructure
     */
    public String getCurrentTreeStructure() {
        return currentTreeStructure;
    }

    /**
     * @param currentTreeStructure the currentTreeStructure to set
     */
    public void setCurrentTreeStructure(final String currentTreeStructure) {
        this.currentTreeStructure = currentTreeStructure;
    }

    /**
     * @return the finalSelectedResultsBean
     */
    public BeanItemContainer<HierarchyLevelsDTO> getFinalSelectedResultsBean() {
        return finalSelectedResultsBean;
    }

    /**
     * @param finalSelectedResultsBean the finalSelectedResultsBean to set
     */
    public void setFinalSelectedResultsBean(final BeanItemContainer<HierarchyLevelsDTO> finalSelectedResultsBean) {
        this.finalSelectedResultsBean = finalSelectedResultsBean;
    }

    /**
     * Inits the.
     *
     * @throws Exception
     * @throws SystemException
     */
    public final void init() throws SystemException {
        LOGGER.debug("Entering init method");
        space.setHeight("20");
        setCompositionRoot(addToContent());
        LOGGER.debug("init method ends");
    }

    /**
     * Adds the to content.
     *
     * @return the component
     * @throws Exception
     * @throws SystemException
     */
    private Component addToContent() throws SystemException {
        LOGGER.debug("Entering addToContent method");
        final VerticalLayout content = new VerticalLayout();
        space.setHeight("20");
        content.addComponentAsFirst(space);
        content.addComponent(errorMsg);
        content.setMargin(true);
        content.addComponent(addRelationshipInfo());
        content.addComponent(addBuilderWindow());
        content.addComponent(addActionButtons());
        getRelationBuilderBinder();
        configureFields();
        LOGGER.debug("addToContent method returns component");
        return content;
    }

    /**
     * Gets the relation builder binder.
     *
     * @return the relation builder binder
     */
    private CustomFieldGroup getRelationBuilderBinder() {
        LOGGER.debug("Entering getRelationBuilderBinder method");
        relationshipBuilderBinder.bindMemberFields(this);
        relationshipBuilderBinder.setItemDataSource(new BeanItem<RelationshipBuilderDTO>(relationshipBuilderDTO));
        relationshipBuilderBinder.setBuffered(true);
        relationshipBuilderBinder.setErrorDisplay(errorMsg);
        LOGGER.debug("getRelationBuilderBinder method returns CustonFieldGroup");
        return relationshipBuilderBinder;
    }

    /**
     * Adds the relationship info.
     *
     * @return the panel
     */
    private Panel addRelationshipInfo() {
        LOGGER.debug("Entering addRelationshipInfo methods");
        final Panel panel = new Panel();
        final CustomGridLayout gridLayout = new CustomGridLayout(6, NumericConstants.TWO);
        gridLayout.setMargin(true);
        gridLayout.setSpacing(true);
        gridLayout.addComponent(new Label("Relationship Name :"));
        gridLayout.addComponent(relationshipName);
        gridLayout.addComponent(new Label("Hierarchy Name :"));
        gridLayout.addComponent(hierarchyNameDdlb);
        gridLayout.addComponent(new Label("Start Date :"));
        gridLayout.addComponent(startDate);
        gridLayout.addComponent(new Label("Relationship Description :"));
        gridLayout.addComponent(relationshipDesc);
        gridLayout.addComponent(new Label("Relationship Type :"));
        gridLayout.addComponent(relationshipType);

        panel.setContent(gridLayout);
        panel.setSizeFull();
        panel.setCaption("Relationship");
        LOGGER.debug("addRelationshipInfo methods returns panel");
        return panel;
    }

    /**
     * Adds the action buttons.
     *
     * @return the component
     */
    private Component addActionButtons() {
        LOGGER.debug("Entering addActionButtons methods");
        final HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setMargin(true);
        horizontalLayout.setSpacing(true);
        horizontalLayout.addComponent(backButton);
        horizontalLayout.addComponent(save);
        save.setEnabled(true);
        horizontalLayout.addComponent(resetAll);
        resetAll.setEnabled(true);
        LOGGER.debug("addActionButtons methods returns Layout");
        return horizontalLayout;
    }

    /**
     * Adds the builder window.
     *
     * @return the component
     */
    private Component addBuilderWindow() {
        LOGGER.debug("Entering addActionButtons methods");
        final Panel panel = new Panel();
        final HorizontalLayout layout = new HorizontalLayout();
        layout.setMargin(true);
        layout.setSpacing(true);
        layout.addComponent(addHierarchyTree());
        layout.addComponent(addLevelBuilder());
        panel.setContent(layout);
        panel.setSizeFull();
        panel.setCaption("Relationship Builder");
        LOGGER.debug("addActionButtons methods Returns Layout");
        return panel;
    }

    /**
     * Configure fields.
     *
     * @throws Exception
     * @throws SystemException
     */
    private void configureFields() throws SystemException {
        LOGGER.debug("Entering configureFields methods");
        relationshipName.focus();
        relationshipName.addValidator(new RegexpValidator(ValidationUtils.QUALIFIER_SPCL_CHARS, "RelationShip Name can contain only alphabets and numbers "));
        relationshipName.setValidationVisible(true);
        relationshipName.addValidator(new StringLengthValidator("Relationship Name length should be less than 100 characters", 0, NumericConstants.HUNDRED, true));
        relationshipName.setImmediate(true);
        relationshipName.setRequired(true);

        relationshipName.setRequired(true);
        relationshipDesc.setRequired(true);
        relationshipName.setRequiredError("Please enter Relationship Name");
        relationshipDesc.setRequiredError("Please enter Relationship Description");
        relationshipName.focus();
        relationshipName.setTabIndex(1);
        relationshipName.setImmediate(true);
        relationshipDesc.setRequired(true);

        hierarchyNameDdlb.setNullSelectionAllowed(true);
        hierarchyNameDdlb.setNullSelectionItemId("- Select One -");
        loadHierarchy();
        hierarchyNameDdlb.setImmediate(true);
        /**
         * value change listener for name ddlb .
         */
        hierarchyNameDdlb.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Called on Property Change
             */
            @SuppressWarnings("PMD")
            public void valueChange(final Property.ValueChangeEvent event) {
                LOGGER.debug("In configureFields hierarchyNameDdlb.addValueChangeListener started");
                try {
                    hierarchyOnChangeEvent(event.getProperty().getValue());
                } catch (SystemException ex) {
                    final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                    AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
                    LOGGER.error(ex);
                } catch (PortalException ex) {
                    AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4011));
                    LOGGER.error(ex);
                } catch (Exception ex) {
                    AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4011));
                    LOGGER.error(ex);
                }
                LOGGER.debug("In configureFields hierarchyNameDdlb.addValueChangeListener Ended");
            }
        });
        hierarchyNameDdlb.setRequired(true);
        hierarchyNameDdlb.setRequiredError("Please select Hierarchy Name");

        startDate.setDateFormat("MM/dd/yyyy");
        startDate.setImmediate(true);
        /**
         * Click listener for starte date
         */
        startDate.addValueChangeListener(new Property.ValueChangeListener() {
            public void valueChange(final Property.ValueChangeEvent event) {
                try {
                    LOGGER.debug("In configureFields startDate.addValueChangeListener started");
                    startDate.setValue(CommonUtils.convert2DigitTo4DigitYearFormat(startDate.getValue()));
                    LOGGER.debug("In configureFields startDate.addValueChangeListener Ended");
                } catch (SystemException ex) {
                    final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                    LOGGER.error(ex);
                    AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
                }
            }
        });
        startDate.setRequired(true);
        startDate.setRequiredError("Please select Start Date");

        relationshipType.addItem(ConstantsUtils.PRIMARY);
        relationshipType.addItem("Secondary");
        relationshipType.select(ConstantsUtils.PRIMARY);
        relationshipType.setRequired(true);
        /**
         * Click listener for save button
         */
        save.addClickListener(new Button.ClickListener() {
            public void buttonClick(final Button.ClickEvent event) {
                LOGGER.debug("In configureFields save.addValueChangeListener started");
                try {
                    relationshipBuilderBinder.commit();
                    if (selectedLevelsBeanList.size() > ConstantsUtils.ZERO_NUM) {
                        for (int k = 0; k < selectedLevelsBeanList.size(); k++) {
                            final BeanItemContainer<HierarchyLevelsDTO> selectedBean = selectedLevelsBeanList.get(k);
                            if (selectedBean.size() == ConstantsUtils.ZERO_NUM) {
                                MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "Please select the Values for the Level No - " + (k + 1), ButtonId.OK);
                                return;
                            }
                        }
                    }

                    final Map duplicateRelationName = logic.getExistingRelationshipNames();
                    if (duplicateRelationName.containsValue(String.valueOf(relationshipName))) {
                        final int rbSystemId = (Integer) VaadinSession.getCurrent().getAttribute(ConstantsUtils.RELATIONSHIP_SYSTEMID);
                        if (rbSystemId == ConstantsUtils.ZERO_NUM) {
                            MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "Entered Relationship Name already exists ", ButtonId.OK);
                            return;

                        } else {
                            if (!duplicateRelationName.containsKey(String.valueOf(rbSystemId))) {
                                MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "Entered Relationship Name already exists ", ButtonId.OK);
                                return;
                            }
                        }
                    }

                    MessageBox.showPlain(Icon.QUESTION, "Confirmation", "Are you sure you want to save the Relationship " + " ?", new MessageBoxListener() {
                        /**
                         * click listener for message box .
                         */
                        @SuppressWarnings("PMD")
                        public void buttonClicked(final ButtonId buttonId) {
                            if (buttonId.name().equals("YES")) {
                                try {
                                    final List selectedItems = finalSelectedResultsBean.getItemIds();
                                    List<Integer> idList = logic.saveRelationshipBuilder(relationshipBuilderBinder, selectedItems, null);
                                    String page = String.valueOf(VaadinSession.getCurrent().getAttribute("fromViewPage"));

                                    if (page.equalsIgnoreCase(ConstantsUtils.ADD)) {
                                        VaadinSession.getCurrent().setAttribute("relationshipSystemId", idList.get(0));
                                        VaadinSession.getCurrent().setAttribute("fromViewPage", "Edit");
                                        VaadinSession.getCurrent().setAttribute(ConstantsUtils.VERSIONNO, idList.get(1));
                                        VaadinSession.getCurrent().setAttribute("hierarchyVersion", idList.get(NumericConstants.TWO));
                                        getUI().getNavigator().navigateTo(RelationshipBuilderView.NAME);
                                    }
                                    final Notification notif = new Notification(" '" + relationshipName + "' has been saved successfully", Notification.Type.HUMANIZED_MESSAGE);
                                    notif.setPosition(Position.BOTTOM_CENTER);
                                    notif.setStyleName(ConstantsUtils.MY_STYLE);
                                    notif.setDelayMsec(NumericConstants.THREE_THOUSAND);
                                    notif.show(Page.getCurrent());

                                } catch (SystemException ex) {
                                    LOGGER.error(ex);
                                } catch (PortalException ex) {
                                    LOGGER.error(ex);
                                } catch (Exception ex) {
                                    LOGGER.error(ex);
                                }
                            }
                        }
                    }, ButtonId.YES, ButtonId.NO);
                    LOGGER.debug("In configureFields save.addValueChangeListener Ended");
                } catch (CustomFieldGroup.CommitException ex) {
                    LOGGER.error(ex);
                } catch (SystemException ex) {
                    final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                    AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
                    LOGGER.error(ex);
                } catch (Exception ex) {
                    AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4002));
                    LOGGER.error(ex);
                }
            }
        });
        /**
         * click listener for reset all button
         */
        resetAll.addClickListener(new Button.ClickListener() {
            public void buttonClick(final Button.ClickEvent event) {
                LOGGER.debug("In configureFields resetAll.addValueChangeListener started");
                MessageBox.showPlain(Icon.QUESTION, "Confirmation", "Are you sure you want to reset the information on the screen" + " ?", new MessageBoxListener() {
                    /**
                     * click listener for Message Box.
                     */
                    @SuppressWarnings("PMD")
                    public void buttonClicked(final ButtonId buttonId) {
                        if (buttonId.name().equals("YES")) {
                            resetButtonClickLogic();
                        }
                    }
                }, ButtonId.YES, ButtonId.NO);
                LOGGER.debug("In configureFields resetAll.addValueChangeListener Ended");
            }
        });
        /**
         * click listener for Back button
         */
        backButton.addClickListener(new Button.ClickListener() {
            public void buttonClick(final Button.ClickEvent event) {
                LOGGER.debug("In configureFields backButton.addValueChangeListener started");
                getUI().getNavigator().navigateTo(RelationshipBuilderIndex.NAME);
                LOGGER.debug("In configureFields backButton.addValueChangeListener Ended");
            }
        });
    }

    /**
     * Load hierarchy.
     *
     * @throws Exception
     * @throws SystemException
     */
    private void loadHierarchy() throws SystemException {
        LOGGER.debug("Entering loadHierarchy method");
    }

    /**
     * Hierarchy on change event.
     *
     * @param company
     * @throws Exception
     * @throws PortalException
     * @throws SystemException
     */
    protected void hierarchyOnChangeEvent(final Object company) throws SystemException, PortalException {
        LOGGER.debug("Entering hierarchyOnChangeEvent method with companyValue " + (company));

        if (hierarchyNameDdlb.getValue() == null) {
            hierarchy.setValue(null);
        } else {
            final Object companyValue = ((HelperDTO) hierarchyNameDdlb.getValue()).getId();
            hierarchy.setValue(((HelperDTO) hierarchyNameDdlb.getValue()).getDescription());
            final int selectedHierarchySysId = Integer.valueOf(companyValue.toString());
            VaadinSession.getCurrent().setAttribute(ConstantsUtils.SELECTED_HIERARCHY_SESSION_ID, selectedHierarchySysId);
            loadHierarchyLevels();
            levelPanel.setVisible(true);

        }
        LOGGER.debug(" hierarchyOnChangeEvent method Ended");
    }

    /**
     * Adds the level builder.
     *
     * @return the component
     */
    private Component addLevelBuilder() {
        return levelPanel;
    }

    /**
     * Adds the hierarchy tree.
     *
     * @return the component
     */
    private Component addHierarchyTree() {
        return hierarchyTree;
    }

    /**
     * Load hierarchy levels.
     *
     * @throws Exception
     * @throws PortalException
     * @throws SystemException
     */
    public void loadHierarchyLevels() throws SystemException, PortalException {
        LOGGER.debug("Entering loadHierarchyLevels method");

        final int selectedHierarchyId = (Integer) VaadinSession.getCurrent().getAttribute(ConstantsUtils.SELECTED_HIERARCHY_SESSION_ID);
        final String fromViewPage = (String) VaadinSession.getCurrent().getAttribute(ConstantsUtils.FRONT_VIEW_PAGE);
        if (selectedHierarchyId != ConstantsUtils.ZERO_NUM) {

            if ("Yes".equalsIgnoreCase(fromViewPage)) {
                final List<HistHierarchyLevelDefn> listOfLevels = logic.getHistHierarchyLevels(selectedHierarchyId, null);
                levelValuesList = logic.getHistAllLevelValues(listOfLevels, null);
                final int rbSystemId = (Integer) VaadinSession.getCurrent().getAttribute(ConstantsUtils.RELATIONSHIP_SYSTEMID);
                if (rbSystemId != ConstantsUtils.ZERO_NUM) {
                    final List<Map<String, List<HierarchyLevelsDTO>>> availSavedLevelValuesList = logic.getSavedHistLevelValuesList(levelValuesList, listOfLevels, null);

                    levelValuesList.clear();
                    levelValuesList = availSavedLevelValuesList.get(0);
                    savedLevelValuesList = availSavedLevelValuesList.get(1);
                    final Map<String, List<HierarchyLevelsDTO>> finalSavedLevelsList = availSavedLevelValuesList.get(NumericConstants.TWO);
                    finalSelectedResultsBean.addAll(finalSavedLevelsList.get(String.valueOf(rbSystemId)));
                }
                final int levelCount = listOfLevels.size();
                layout = new GridLayout(1, levelCount);
                for (int i = 0; i < levelCount; i++) {
                    levelLayout = new HorizontalLayout();
                    levelLayout.setSpacing(true);
                    final HistHierarchyLevelDefn levelObj = (HistHierarchyLevelDefn) listOfLevels.get(i);
                    List<HierarchyLevelsDTO> levelValues;
                    List<HierarchyLevelsDTO> savedLevelValues;
                    levelValues = levelValuesList.get(String.valueOf(levelObj.getHierarchyLevelDefinitionSid()));
                    if (rbSystemId == ConstantsUtils.ZERO_NUM) {
                        savedLevelValues = new ArrayList<HierarchyLevelsDTO>();
                    } else {
                        savedLevelValues = savedLevelValuesList.get(String.valueOf(levelObj.getHierarchyLevelDefinitionSid()));
                    }

                    final int levelNo = i + 1;
                    levelLayout.addComponent(addLevelStructure(levelObj.getLevelName(), levelValues, levelNo));
                    levelLayout.addComponent(addGridButtons(i, levelCount, levelNo));
                    levelLayout.addComponent(addSelectedLevelValues(levelObj.getLevelName(), savedLevelValues, levelNo));
                    layout.addComponent(levelLayout);

                }

            } else {
                final List<HierarchyLevelDefinition> listOfLevels = logic.getHierarchyLevels(selectedHierarchyId);

                levelValuesList = logic.getAllLevelValues(listOfLevels);

                final int rbSystemId = (Integer) VaadinSession.getCurrent().getAttribute(ConstantsUtils.RELATIONSHIP_SYSTEMID);
                if (rbSystemId != ConstantsUtils.ZERO_NUM) {
                    final List<Map<String, List<HierarchyLevelsDTO>>> availSavedLevelValuesList = logic.getSavedLevelValuesList(levelValuesList, listOfLevels, null);

                    levelValuesList.clear();
                    levelValuesList = availSavedLevelValuesList.get(0);
                    savedLevelValuesList = availSavedLevelValuesList.get(1);
                    final Map<String, List<HierarchyLevelsDTO>> finalSavedLevelsList = availSavedLevelValuesList.get(NumericConstants.TWO);
                    finalSelectedResultsBean.addAll(finalSavedLevelsList.get(String.valueOf(rbSystemId)));
                }
                final int levelCount = listOfLevels.size();
                layout = new GridLayout(1, levelCount);
                for (int i = 0; i < levelCount; i++) {
                    levelLayout = new HorizontalLayout();
                    levelLayout.setSpacing(true);
                    final HierarchyLevelDefinition levelObj = (HierarchyLevelDefinition) listOfLevels.get(i);
                    List<HierarchyLevelsDTO> levelValues;
                    List<HierarchyLevelsDTO> savedLevelValues;
                    levelValues = levelValuesList.get(String.valueOf(levelObj.getHierarchyLevelDefinitionSid()));
                    if (rbSystemId == ConstantsUtils.ZERO_NUM) {
                        savedLevelValues = new ArrayList<HierarchyLevelsDTO>();
                    } else {
                        savedLevelValues = savedLevelValuesList.get(String.valueOf(levelObj.getHierarchyLevelDefinitionSid()));
                    }

                    final int levelNo = i + 1;
                    levelLayout.addComponent(addLevelStructure(levelObj.getLevelName(), levelValues, levelNo));
                    levelLayout.addComponent(addGridButtons(i, levelCount, levelNo));
                    levelLayout.addComponent(addSelectedLevelValues(levelObj.getLevelName(), savedLevelValues, levelNo));
                    layout.addComponent(levelLayout);

                }
            }
        }

        levelPanel.setContent(layout);
        LOGGER.debug(" loadHierarchyLevels method Ended");
    }

    /**
     * Adds the grid buttons.
     *
     * @param i the i
     * @param levelCount the level count
     * @param levelNo the level no
     * @return the component
     */
    private Component addGridButtons(final int values, final int levelCount, final int levelNo) {
        LOGGER.debug("Entering addGridButtons with i value:" + values + "and levelcount:" + levelCount + "and levelNo :" + levelNo);
        final VerticalLayout content = new VerticalLayout();
        Button remove;
        /**
         * The add.
         */
        Button add;
        add = new Button(" > ");
        /**
         * click listener for add button .
         */
        add.addClickListener(new Button.ClickListener() {
            /**
             * Click listener for Button
             */
            @SuppressWarnings("PMD")
            public void buttonClick(final Button.ClickEvent event) {
                if (availableTableList.get(values).getValue() == null) {
                    MessageBox.showPlain(Icon.INFO, "Halt", "Please select a row from Available <Level " + levelNo + ">", ButtonId.OK);

                } else {
                    if (selectedLevelsBeanList.get(values).size() >= ConstantsUtils.ONE) {
                        if (values == ConstantsUtils.ONE) {
                            MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "Multiple values cannot be allowed at the first level", ButtonId.OK);
                        } else {
                            if (levelNo == currentTreeStructure.split("~").length) {
                                addItemsButtonClick(event, values, levelNo);

                            } else {
                                MessageBox.showPlain(Icon.INFO, "Oops!", "Tree Structure not completed, Please select values for all the sub levels", ButtonId.OK);
                            }

                        }
                    } else {
                        addItemsButtonClick(event, values, levelNo);
                    }

                }
            }
        });
        remove = new Button(" < ");
        /**
         * click listener for remove button
         */
        remove.addClickListener(new Button.ClickListener() {
            public void buttonClick(final Button.ClickEvent event) {
                if (selectedTableList.get(values).getValue() == null) {
                    MessageBox.showPlain(Icon.INFO, "Halt", "Please select a row from Selected <Level " + levelNo + ">", ButtonId.OK);

                } else {
                    removeItemsButtonClick(event, values);
                }
            }
        });
        final String fromViewPage = (String) VaadinSession.getCurrent().getAttribute(ConstantsUtils.FRONT_VIEW_PAGE);
        if (fromViewPage.equals(ConstantsUtils.SMALL_YES)) {
            add.setEnabled(false);
            remove.setEnabled(false);
        }
        space.setHeight("20");

        content.addComponentAsFirst(space);
        content.setMargin(true);
        content.addComponent(add);
        content.addComponent(remove);
        LOGGER.debug("addGridButtons method returns layout");
        return content;
    }

    /**
     * Adds the level structure.
     *
     * @param levelName the level name
     * @param levelValues the level values
     * @param i the i
     * @return the filter table
     */
    private ExtFilterTable addLevelStructure(final String levelName, final List<HierarchyLevelsDTO> levelValues, final int value) {
        LOGGER.debug("Entering addLevelStructure method");
        if (value == ConstantsUtils.ONE) {
            availableLevelsBeanList.clear();
            finalSelectedResultsBean.removeAllItems();
        }
        ExtFilterTable availableResults;
        BeanItemContainer<HierarchyLevelsDTO> availableResultsBean;
        availableResultsBean = new BeanItemContainer<HierarchyLevelsDTO>(HierarchyLevelsDTO.class);
        availableResultsBean.removeAllItems();
        availableResultsBean.addAll(levelValues);
        availableLevelsBeanList.add(value - 1, availableResultsBean);
        availableResults = new ExtFilterTable();
        availableResults.markAsDirty();
        availableResults.setFilterBarVisible(true);
        availableResults.setFilterDecorator(new ExtDemoFilterDecorator());
        availableResults.setContainerDataSource(availableLevelsBeanList.get(value - 1));
        availableResults.setCaption("Available <Level " + value + " > :");
        availableResults.setVisibleColumns(CommonUIUtil.RB_AVAILABLE_LEVEL_COLUMN);
        availableResults.setColumnHeaders(levelName);
        availableResults.setPageLength(NumericConstants.FIVE);
        availableResults.setWidth("100%");
        availableResults.setImmediate(true);
        availableResults.setSelectable(true);
        /**
         * Click listener for available bean table .
         */
        availableResults.addListener(new ItemClickEvent.ItemClickListener() {
            /**
             * Called on Item Click
             */
            @SuppressWarnings("PMD")
            public void itemClick(final ItemClickEvent event) {
                if (event != null && event.isDoubleClick()) {
                        LOGGER.debug("In addLevelStructure availableResults.addListener started");
                        addItemsOnDoubleClick(event, value - 1);
                        LOGGER.debug("In addLevelStructure availableResults.addListener Ended");
                    }
            }
        });
        /**
         * Click listener for available results table
         */
        availableResults.setErrorHandler(new ErrorHandler() {
            public void error(final com.vaadin.server.ErrorEvent event) {

            }
        });
        availableTableList.add(value - 1, availableResults);
        LOGGER.debug("addLevelStructure method Returns");
        return availableTableList.get(value - 1);
    }

    /**
     * Adds the selected level values.
     *
     * @param levelName the level name
     * @param savedLevelValues the saved level values
     * @param i the i
     * @return the filter table
     */
    private ExtFilterTable addSelectedLevelValues(final String levelName, final List<HierarchyLevelsDTO> savedLevelValues, final int name) {
        LOGGER.debug("Entering addSelectedLevelValues method with levelName " + levelName + "savedLevelValues list size:" + savedLevelValues.size() + "i value:" + name);
        if (name == ConstantsUtils.ONE) {
            selectedLevelsBeanList.clear();
        }
        BeanItemContainer<HierarchyLevelsDTO> selectedResultsBean;
        selectedResultsBean = new BeanItemContainer<HierarchyLevelsDTO>(HierarchyLevelsDTO.class);
        selectedResultsBean.removeAllItems();
        if (!savedLevelValues.isEmpty()) {
            selectedResultsBean.addAll(savedLevelValues);
        }
        selectedResults = new ExtFilterTable();
        selectedLevelsBeanList.add(name - 1, selectedResultsBean);
        selectedResults.markAsDirty();
        selectedResults.setFilterBarVisible(true);
        selectedResults.setFilterDecorator(new ExtDemoFilterDecorator());
        selectedResults.setContainerDataSource(selectedLevelsBeanList.get(name - 1));
        selectedResults.setCaption("Selected <Level " + name + " > :");
        selectedResults.setVisibleColumns(CommonUIUtil.RB_SELECTED_LEVEL_COLUMN);
        selectedResults.setColumnHeaders(levelName);
        selectedResults.setPageLength(NumericConstants.FIVE);
        selectedResults.setWidth("100%");
        selectedResults.setImmediate(true);
        selectedResults.setSelectable(true);
        /**
         * Listener for selected results .
         */
        selectedResults.addListener(new ItemClickEvent.ItemClickListener() {
            /**
             * Called on Item Click
             */
            @SuppressWarnings("PMD")
            public void itemClick(final ItemClickEvent event) {
                if (event != null && event.isDoubleClick()) {
                        LOGGER.debug("In addSelectedLevelValues selectedResults.addListener started");
                        removeItemsOnDoubleClick(event, name - 1);
                        LOGGER.debug("In addSelectedLevelValues selectedResults.addListener Ended");
                }
            }
        });
        /**
         * setting error handler for selected resluts table
         */
        selectedResults.setErrorHandler(new ErrorHandler() {
            public void error(final com.vaadin.server.ErrorEvent event) {

            }
        });

        selectedTableList.add(name - 1, selectedResults);
        LOGGER.debug(" addSelectedLevelValues method Returns ");
        return selectedTableList.get(name - 1);
    }

    /**
     * Adds the items button click.
     *
     * @param event the event
     * @param value
     * @param i the i
     */
    protected void addItemsButtonClick(final Button.ClickEvent event, final int value, final int levelNo) {
        LOGGER.debug("Entering addItemsButtonClick with i value " + value + " and leval no" + levelNo);

        final HierarchyLevelsDTO itemId = (HierarchyLevelsDTO) availableTableList.get(value).getValue();
        availableLevelsBeanList.get(value).removeItem(itemId);
        if (levelNo == ConstantsUtils.ONE) {
            final int treeNo = selectedLevelsBeanList.get(value).size() + 1;
            currentTreeStructure = "T" + treeNo;

            itemId.setParentNode(currentTreeStructure);
        } else {
            if (levelNo == currentTreeStructure.split("~").length) {
                String[] treeArr = currentTreeStructure.split("~");
                treeArr[levelNo - 1] = String.valueOf(selectedLevelsBeanList.get(value).size() + 1);
                currentTreeStructure = ConstantsUtils.EMPTY;
                for (int i = 0; i < treeArr.length; i++) {
                    final String tempStr = treeArr[i];
                    if (StringUtils.isEmpty(tempStr)) {
                        currentTreeStructure = tempStr;
                    } else {
                        currentTreeStructure = currentTreeStructure + "~" + tempStr;
                    }
                }

            } else {
                currentTreeStructure = currentTreeStructure + "~" + (selectedLevelsBeanList.get(value).size() + 1);
            }

            itemId.setParentNode(currentTreeStructure);
        }
        selectedLevelsBeanList.get(value).addItem(itemId);

        finalSelectedResultsBean.addItem(itemId);
        LOGGER.debug("addItemsButtonClick Method Ends here");
    }

    /**
     * Removes the items button click.
     *
     * @param event the event
     * @param value
     * @param i the i
     */
    protected void removeItemsButtonClick(final Button.ClickEvent event, final int value) {
        LOGGER.debug("Entering removeItemsButtonClick method with i value:" + value);
        final Object itemId = selectedTableList.get(value).getValue();
        availableLevelsBeanList.get(value).addItem(itemId);
        selectedLevelsBeanList.get(value).removeItem(itemId);
        finalSelectedResultsBean.removeItem(itemId);
        LOGGER.debug("removeItemsButtonClick method Ended");
    }

    /**
     * ADDED By VISHA for double click.
     *
     * @param event the event
     * @param event1
     */
    protected void addItemsOnDoubleClick(final ItemClickEvent event, final int event1) {

        LOGGER.debug("Entering addItemsOnDoubleClick method with i value:" + event1);
        final Object itemId = availableTableList.get(event1).getValue();
        selectedTableList.get(event1).addItem(itemId);
        availableTableList.get(event1).removeItem(itemId);
        finalSelectedResultsBean.addItem(itemId);

    }

    /**
     * Removes the items on double click.
     *
     * @param event the event
     * @param values
     * @param i the i
     */
    protected void removeItemsOnDoubleClick(final ItemClickEvent event, final int values) {
        LOGGER.debug("Entering removeItemsOnDoubleClick method with i value:" + values);
        final Object itemId = selectedTableList.get(values).getValue();
        availableTableList.get(values).addItem(itemId);
        selectedTableList.get(values).removeItem(itemId);
        finalSelectedResultsBean.removeItem(itemId);
    }

    /**
     * ENDS.
     */
    protected void resetButtonClickLogic() {
        LOGGER.debug("Entering resetButtonClickLogic method");
        try {
            relationshipBuilderBinder.getErrorDisplay().clearError();
            finalSelectedResultsBean.removeAllItems();
            levelPanel.setVisible(false);
            relationshipName.setValue(ConstantsUtils.EMPTY);
            relationshipDesc.setValue(ConstantsUtils.EMPTY);
            startDate.setValue(null);
            relationshipType.select(ConstantsUtils.PRIMARY);
            hierarchyNameDdlb.setValue("- Select One -");
            hierarchyNameDdlb.setEnabled(true);

            final String fromViewPage = (String) VaadinSession.getCurrent().getAttribute(ConstantsUtils.FRONT_VIEW_PAGE);
            if (fromViewPage.equals(ConstantsUtils.EDIT)) {
                relationshipBuilderDTO = new RelationBuilderLogic().getRelationBuilderInfo(null);
                relationshipName.setValue(relationshipBuilderDTO.getRelationshipName());
                relationshipDesc.setValue(relationshipBuilderDTO.getRelationshipDesc());
                startDate.setValue(relationshipBuilderDTO.getStartDate());
                hierarchy.setValue(relationshipBuilderDTO.getHierarchy());
                loadHierarchy();
                final HelperDTO helperVal = new HelperDTO();
                helperVal.setId(Integer.valueOf(relationshipBuilderDTO.getHierarchy()));
                helperVal.setDescription(relationshipBuilderDTO.getHierarchyName());
                hierarchyNameDdlb.setValue(helperVal);
                hierarchyNameDdlb.setEnabled(false);
                relationshipType.select(relationshipBuilderDTO.getRelationshipType());
                VaadinSession.getCurrent().setAttribute(ConstantsUtils.SELECTED_HIERARCHY_SESSION_ID, Integer.valueOf(relationshipBuilderDTO.getHierarchy()));
                loadHierarchyLevels();
                levelPanel.setVisible(true);
                relationshipName.setEnabled(false);
                relationshipDesc.setEnabled(false);
                startDate.setEnabled(false);
                relationshipType.setEnabled(false);
            }
        } catch (SystemException ex) {
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getErrorMessage(ex));
            LOGGER.error(ex);
        } catch (PortalException ex) {
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4006));
            LOGGER.error(ex);
        } catch (Exception ex) {
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4006));
            LOGGER.error(ex);
        }
        LOGGER.debug("resetButtonClickLogic method Ends here");
    }

    /**
     * (non-Javadoc)
     *
     * @see
     * com.vaadin.navigator.View#enter(com.vaadin.navigator.ViewChangeListener.ViewChangeEvent)
     * .
     */
    public void enter(final ViewChangeListener.ViewChangeEvent event) {

    }

    /**
     * Entry.
     */
    public void entry() {
        try {
            LOGGER.debug("entry Started");
            final String fromViewPage = (String) VaadinSession.getCurrent().getAttribute(ConstantsUtils.FRONT_VIEW_PAGE);
            final int rbSystemId = (Integer) VaadinSession.getCurrent().getAttribute(ConstantsUtils.RELATIONSHIP_SYSTEMID);
            if (rbSystemId != ConstantsUtils.ZERO_NUM) {
                relationshipBuilderDTO = new RelationBuilderLogic().getRelationBuilderInfo(null);
                resetButtonClickLogic();
                relationshipName.setValue(relationshipBuilderDTO.getRelationshipName());
                relationshipDesc.setValue(relationshipBuilderDTO.getRelationshipDesc());
                startDate.setValue(relationshipBuilderDTO.getStartDate());
                hierarchy.setValue(relationshipBuilderDTO.getHierarchy());
                loadHierarchy();
                final HelperDTO helperVal = new HelperDTO();
                helperVal.setId(Integer.valueOf(relationshipBuilderDTO.getHierarchy()));
                helperVal.setDescription(relationshipBuilderDTO.getHierarchyName());
                hierarchyNameDdlb.setValue(helperVal);
                hierarchyNameDdlb.setEnabled(false);
                relationshipType.select(relationshipBuilderDTO.getRelationshipType());
                VaadinSession.getCurrent().setAttribute(ConstantsUtils.SELECTED_HIERARCHY_SESSION_ID, Integer.valueOf(relationshipBuilderDTO.getHierarchy()));
                loadHierarchyLevels();
                levelPanel.setVisible(true);
                relationshipName.setEnabled(false);
                relationshipDesc.setEnabled(false);
                startDate.setEnabled(false);
                relationshipType.setEnabled(false);
            }
            if (fromViewPage.equals(ConstantsUtils.SMALL_YES)) {
                save.setEnabled(false);
                resetAll.setEnabled(false);
                selectedResults.setReadOnly(true);
                relationshipName.setEnabled(false);
                relationshipDesc.setEnabled(false);
                startDate.setEnabled(false);
                relationshipType.setEnabled(false);
            } else {
                save.setEnabled(true);
                resetAll.setEnabled(true);
                final String fromAddPage = (String) VaadinSession.getCurrent().getAttribute(ConstantsUtils.FRONT_VIEW_PAGE);
                if (fromAddPage.equals(ConstantsUtils.ADD)) {
                    relationshipName.setValue(ConstantsUtils.EMPTY);
                    relationshipDesc.setValue(ConstantsUtils.EMPTY);
                    startDate.setValue(null);
                    hierarchy.setValue(ConstantsUtils.EMPTY);
                    hierarchyNameDdlb.setValue("- Select One -");
                    relationshipType.select(ConstantsUtils.PRIMARY);
                    levelPanel.setVisible(false);
                    hierarchyNameDdlb.setEnabled(true);
                    relationshipName.setEnabled(true);
                    relationshipDesc.setEnabled(true);
                    startDate.setEnabled(true);
                    relationshipType.setEnabled(true);
                }
            }
            LOGGER.debug("entry Ended");
        } catch (SystemException ex) {
            final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
            LOGGER.error(ex);
        } catch (PortalException ex) {
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4011));
            LOGGER.error(ex);
        } catch (Exception ex) {
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4011));
            LOGGER.error(ex);
        }
    }
}
