/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.relationshipbuilder.ui.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.jboss.logging.Logger;
import com.stpl.app.adminconsole.bpm.logic.AutomaticTreeBuilder;
import com.stpl.app.adminconsole.bpm.logic.BpmLogic;
import com.stpl.app.adminconsole.common.dto.SessionDTO;
import com.stpl.app.adminconsole.common.util.CommonUIUtil;
import com.stpl.app.adminconsole.common.util.CommonUtil;
import com.stpl.app.adminconsole.dao.HierarchyBuilderLogicDAO;
import com.stpl.app.adminconsole.dao.impl.HierarchyBuilderLogicDAOImpl;
import com.stpl.app.adminconsole.itemgroup.util.UISecurityUtil;
import com.stpl.app.model.HelperTable;
import com.stpl.app.model.HierarchyDefinition;
import com.stpl.app.model.HierarchyLevelDefinition;
import com.stpl.app.model.HistHierarchyLevelDefn;
import com.stpl.app.adminconsole.relationshipbuilder.dto.HierarchyLevelsDTO;
import com.stpl.app.adminconsole.relationshipbuilder.dto.RelationshipBuilderDTO;
import com.stpl.app.adminconsole.relationshipbuilder.logic.RelationBuilderLogic;
import com.stpl.app.adminconsole.relationshipbuilder.ui.view.RelationshipBuilderView;
import com.stpl.app.adminconsole.util.CommonUtils;
import com.stpl.app.adminconsole.util.AbstractNotificationUtils;
import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.stpl.app.adminconsole.util.ErrorCodeUtil;
import com.stpl.app.adminconsole.util.ErrorCodes;
import com.stpl.app.model.RelationshipBuilder;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.ifs.ui.CommonSecurityLogic;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.errorhandling.ErrorLabel;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ErrorHandler;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Field;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;

import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;

// TODO: Auto-generated Javadoc
/**
 * The Class RelationshipBuilderTree.
 *
 * @author vishalakshi
 */
public class RelationshipBuilderTree extends CustomComponent implements View {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(RelationshipBuilderTree.class);
    /**
     * The space.
     */
    private Label space = new Label("&nbsp;", ContentMode.HTML);
    @UiField("relationshipBuilderPanel")
    Panel relationshipBuilderPanel;
    HorizontalSplitPanel hsplit = new HorizontalSplitPanel();
    /**
     * The error msg.
     */
    @UiField("errorMsg")
    private ErrorLabel errorMsg;
    /**
     * The relationship name.
     */
    @UiField("relationshipName")
    private TextField relationshipName;

    /**
     * The relationship desc.
     */
    @UiField("relationshipDesc")
    private TextField relationshipDesc;
    /**
     * The start date.
     */
    @UiField("startDate")
    private DateField startDate;
    /**
     * The hierarchy name ddlb.
     */
    @UiField("hierarchyNameDdlb")
    private ComboBox hierarchyNameDdlb;
    /**
     * The relationship type.
     */
    @UiField("relationshipType")
    private OptionGroup relationshipType;
    /**
     * version No.
     */
    @UiField("versionNo")
    private ComboBox versionNo;

    @UiField("layout2")
    private VerticalLayout layout2;

    /**
     * The save.
     */
    @UiField("save")
    private Button save;
    /**
     * The reset all.
     */
    @UiField("resetAll")
    private Button resetAll;
    /**
     * The back button.
     */
    @UiField("backButton")
    private Button backButton;
    /**
     * The relationship builder dto.
     */
    private RelationshipBuilderDTO relationshipBuilderDTO;
    /**
     * The relationship builder binder.
     */
    private CustomFieldGroup relationshipBuilderBinder;
    /**
     * The logic.
     */
    private RelationBuilderLogic logic;
    /**
     * The hierarchy.
     */
    private TextField hierarchy = new TextField();
    /**
     * The level panel.
     */
    @UiField("levelPanel")
    private Panel levelPanel;
    /**
     * The tree panel.
     */
    @UiField("treePanel")
    private Panel treePanel;
    /**
     * The available levels bean list.
     */
    private List<BeanItemContainer<HierarchyLevelsDTO>> availableLevelsBeanList = new ArrayList<BeanItemContainer<HierarchyLevelsDTO>>();
    /**
     * The selected levels bean list.
     */
    private List<BeanItemContainer<HierarchyLevelsDTO>> selectedLevelsBeanList = new ArrayList<BeanItemContainer<HierarchyLevelsDTO>>();
    /**
     * The available table list.
     */
    private List<ExtFilterTable> availableTableList = new ArrayList<ExtFilterTable>();
    /**
     * The layout.
     */
    private GridLayout layout;
    /**
     * The level layout.
     */
    private HorizontalLayout levelLayout;
    /**
     * The level values list.
     */
    private Map<String, List<HierarchyLevelsDTO>> levelValuesList;
    /**
     * The saved level values list.
     */
    private Map<String, List<HierarchyLevelsDTO>> savedLevelValuesList;
    /**
     * The remove.
     */
    private Button remove = new Button("Remove from Tree");
    /**
     * The hierarchy tree.
     */
    private Tree hierarchyTree = new Tree();
    /**
     * The tree bean id.
     */
    private Object treeBeanId;
    /**
     * The tree bean.
     */
    private HierarchyLevelsDTO treeBean;
    /**
     * The total levels.
     */
    private int totalLevels;
    /**
     * The final selected results bean.
     */
    private BeanItemContainer<HierarchyLevelsDTO> finalSelectedResultsBean = new BeanItemContainer<HierarchyLevelsDTO>(HierarchyLevelsDTO.class);
    /**
     * The dao.
     */
    private HierarchyBuilderLogicDAO dao = new HierarchyBuilderLogicDAOImpl();
    /**
     * The saveflag
     */
    boolean saveFlag;
    /**
     * The back flag
     */
    boolean backFlag;

    /**
     * The build type.
     */
    @UiField("buildType")
    private OptionGroup buildType;

    public Button buildAutomatic = new Button("BUILD TREE");
    @UiField("cssLayout")
    public CssLayout cssLayout;
    @UiField("cssLayout2")
    public CssLayout cssLayout2;
    CommonUtil commonUtil = new CommonUtil();
    @UiField("relationshipNameLB")
    public Label relationshipNameLB;
    @UiField("relationDescLB")
    public Label relationDescLB;
    @UiField("hierarchyNameDdlbLB")
    public Label hierarchyNameDdlbLB;
    @UiField("relationshipTypeLB")
    public Label relationshipTypeLB;
    @UiField("versionNoLB")
    public Label versionNoLB;
    @UiField("startDateLB")
    public Label startDateLB;
    @UiField("buildTypeLB")
    public Label buildTypeLB;

    CommonSecurityLogic commonSecurityLogic = new CommonSecurityLogic();
    /**
     * Flag added to check new Hierarchy
     */
    boolean hierarchyChangeFlag = true;

    SessionDTO sessionDTO;

    /**
     * Instantiates a new relationship builder tree.
     *
     * @param relationshipBuilderBinder the relationship builder binder
     * @param relationshipBuilderDTO the relationship builder dto
     * @param sessionDTO
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    public RelationshipBuilderTree(final CustomFieldGroup relationshipBuilderBinder, final RelationshipBuilderDTO relationshipBuilderDTO, final SessionDTO sessionDTO) throws SystemException, PortalException {
        super();
        LOGGER.debug("RelationshipBuilderTree Constructor started");
        this.relationshipBuilderDTO = relationshipBuilderDTO;
        this.relationshipBuilderBinder = relationshipBuilderBinder;
        this.sessionDTO = sessionDTO;
        logic = new RelationBuilderLogic(sessionDTO);
        init();
        LOGGER.debug("RelationshipBuilderTree Constructor Ended");
    }

    /**
     * Gets the version no.
     *
     * @return the version no
     */
    public ComboBox getVersionNo() {
        return versionNo;
    }

    /**
     * Sets the version no.
     *
     * @param versionNo the new version no
     */
    public void setVersionNo(final ComboBox versionNo) {
        this.versionNo = versionNo;
    }

    /**
     * Gets the space.
     *
     * @return the space
     */
    public Label getSpace() {
        return space;
    }

    /**
     * Sets the space.
     *
     * @param space the space to set
     */
    public void setSpace(final Label space) {
        this.space = space;
    }

    /**
     * Gets the error msg.
     *
     * @return the errorMsg
     */
    public ErrorLabel getErrorMsg() {
        return errorMsg;
    }

    /**
     * Sets the error msg.
     *
     * @param errorMsg the errorMsg to set
     */
    public void setErrorMsg(final ErrorLabel errorMsg) {
        this.errorMsg = errorMsg;
    }

    /**
     * Gets the relationship name.
     *
     * @return the relationshipName
     */
    public TextField getRelationshipName() {
        return relationshipName;
    }

    /**
     * Sets the relationship name.
     *
     * @param relationshipName the relationshipName to set
     */
    public void setRelationshipName(final TextField relationshipName) {
        this.relationshipName = relationshipName;
    }

    /**
     * Gets the relationship desc.
     *
     * @return the relationshipDesc
     */
    public TextField getRelationshipDesc() {
        return relationshipDesc;
    }

    /**
     * Sets the relationship desc.
     *
     * @param relationshipDesc the relationshipDesc to set
     */
    public void setRelationshipDesc(final TextField relationshipDesc) {
        this.relationshipDesc = relationshipDesc;
    }

    /**
     * Gets the start date.
     *
     * @return the startDate
     */
    public DateField getStartDate() {
        return startDate;
    }

    /**
     * Sets the start date.
     *
     * @param startDate the startDate to set
     */
    public void setStartDate(final DateField startDate) {
        this.startDate = startDate;
    }

    /**
     * Gets the hierarchy name ddlb.
     *
     * @return the hierarchyNameDdlb
     */
    public ComboBox getHierarchyNameDdlb() {
        return hierarchyNameDdlb;
    }

    /**
     * Sets the hierarchy name ddlb.
     *
     * @param hierarchyNameDdlb the hierarchyNameDdlb to set
     */
    public void setHierarchyNameDdlb(final ComboBox hierarchyNameDdlb) {
        this.hierarchyNameDdlb = hierarchyNameDdlb;
    }

    /**
     * Gets the relationship type.
     *
     * @return the relationshipType
     */
    public OptionGroup getRelationshipType() {
        return relationshipType;
    }

    /**
     * Sets the relationship type.
     *
     * @param relationshipType the relationshipType to set
     */
    public void setRelationshipType(final OptionGroup relationshipType) {
        this.relationshipType = relationshipType;
    }

    public OptionGroup getBuildType() {
        return buildType;
    }

    public void setBuildType(OptionGroup buildType) {
        this.buildType = buildType;
    }

    /**
     * Gets the save.
     *
     * @return the save
     */
    public Button getSave() {
        return save;
    }

    /**
     * Sets the save.
     *
     * @param save the save to set
     */
    public void setSave(final Button save) {
        this.save = save;
    }

    /**
     * Gets the reset all.
     *
     * @return the resetAll
     */
    public Button getResetAll() {
        return resetAll;
    }

    /**
     * Sets the reset all.
     *
     * @param resetAll the resetAll to set
     */
    public void setResetAll(final Button resetAll) {
        this.resetAll = resetAll;
    }

    /**
     * Gets the back button.
     *
     * @return the backButton
     */
    public Button getBackButton() {
        return backButton;
    }

    /**
     * Sets the back button.
     *
     * @param backButton the backButton to set
     */
    public void setBackButton(final Button backButton) {
        this.backButton = backButton;
    }

    /**
     * Gets the relationship builder dto.
     *
     * @return the relationshipBuilderDTO
     */
    public RelationshipBuilderDTO getRelationshipBuilderDTO() {
        return relationshipBuilderDTO;
    }

    /**
     * Sets the relationship builder dto.
     *
     * @param relationshipBuilderDTO the relationshipBuilderDTO to set
     */
    public void setRelationshipBuilderDTO(final RelationshipBuilderDTO relationshipBuilderDTO) {
        this.relationshipBuilderDTO = relationshipBuilderDTO;
    }

    /**
     * Gets the relationship builder binder.
     *
     * @return the relationshipBuilderBinder
     */
    public CustomFieldGroup getRelationshipBuilderBinder() {
        return relationshipBuilderBinder;
    }

    /**
     * Sets the relationship builder binder.
     *
     * @param relationshipBuilderBinder the relationshipBuilderBinder to set
     */
    public void setRelationshipBuilderBinder(final CustomFieldGroup relationshipBuilderBinder) {
        this.relationshipBuilderBinder = relationshipBuilderBinder;
    }

    /**
     * Gets the logic.
     *
     * @return the logic
     */
    public RelationBuilderLogic getLogic() {
        return logic;
    }

    /**
     * Sets the logic.
     *
     * @param logic the logic to set
     */
    public void setLogic(final RelationBuilderLogic logic) {
        this.logic = logic;
    }

    /**
     * Gets the hierarchy.
     *
     * @return the hierarchy
     */
    public TextField getHierarchy() {
        return hierarchy;
    }

    /**
     * Sets the hierarchy.
     *
     * @param hierarchy the hierarchy to set
     */
    public void setHierarchy(final TextField hierarchy) {
        this.hierarchy = hierarchy;
    }

    /**
     * Gets the level panel.
     *
     * @return the levelPanel
     */
    public Panel getLevelPanel() {
        return levelPanel;
    }

    /**
     * Sets the level panel.
     *
     * @param levelPanel the levelPanel to set
     */
    public void setLevelPanel(final Panel levelPanel) {
        this.levelPanel = levelPanel;
    }

    /**
     * Gets the tree panel.
     *
     * @return the treePanel
     */
    public Panel getTreePanel() {
        return treePanel;
    }

    /**
     * Sets the tree panel.
     *
     * @param treePanel the treePanel to set
     */
    public void setTreePanel(final Panel treePanel) {
        this.treePanel = treePanel;
    }

    /**
     * Gets the available levels bean list.
     *
     * @return the availableLevelsBeanList
     */
    public List<BeanItemContainer<HierarchyLevelsDTO>> getAvailableLevelsBeanList() {
        return availableLevelsBeanList;
    }

    /**
     * Sets the available levels bean list.
     *
     * @param availableLevelsBeanList the availableLevelsBeanList to set
     */
    public void setAvailableLevelsBeanList(final List<BeanItemContainer<HierarchyLevelsDTO>> availableLevelsBeanList) {
        this.availableLevelsBeanList = availableLevelsBeanList;
    }

    /**
     * Gets the selected levels bean list.
     *
     * @return the selectedLevelsBeanList
     */
    public List<BeanItemContainer<HierarchyLevelsDTO>> getSelectedLevelsBeanList() {
        return selectedLevelsBeanList;
    }

    /**
     * Sets the selected levels bean list.
     *
     * @param selectedLevelsBeanList the selectedLevelsBeanList to set
     */
    public void setSelectedLevelsBeanList(final List<BeanItemContainer<HierarchyLevelsDTO>> selectedLevelsBeanList) {
        this.selectedLevelsBeanList = selectedLevelsBeanList;
    }

    /**
     * Gets the available table list.
     *
     * @return the availableTableList
     */
    public List<ExtFilterTable> getAvailableTableList() {
        return availableTableList;
    }

    /**
     * Sets the available table list.
     *
     * @param availableTableList the availableTableList to set
     */
    public void setAvailableTableList(final List<ExtFilterTable> availableTableList) {
        this.availableTableList = availableTableList;
    }

    /**
     * Gets the layout.
     *
     * @return the layout
     */
    public GridLayout getLayout() {
        return layout;
    }

    /**
     * Sets the layout.
     *
     * @param layout the layout to set
     */
    public void setLayout(final GridLayout layout) {
        this.layout = layout;
    }

    /**
     * Gets the level layout.
     *
     * @return the levelLayout
     */
    public HorizontalLayout getLevelLayout() {
        return levelLayout;
    }

    /**
     * Sets the level layout.
     *
     * @param levelLayout the levelLayout to set
     */
    public void setLevelLayout(final HorizontalLayout levelLayout) {
        this.levelLayout = levelLayout;
    }

    /**
     * Gets the level values list.
     *
     * @return the levelValuesList
     */
    public Map<String, List<HierarchyLevelsDTO>> getLevelValuesList() {
        return levelValuesList;
    }

    /**
     * Sets the level values list.
     *
     * @param levelValuesList the levelValuesList to set
     */
    public void setLevelValuesList(final Map<String, List<HierarchyLevelsDTO>> levelValuesList) {
        this.levelValuesList = levelValuesList;
    }

    /**
     * Gets the saved level values list.
     *
     * @return the savedLevelValuesList
     */
    public Map<String, List<HierarchyLevelsDTO>> getSavedLevelValuesList() {
        return savedLevelValuesList;
    }

    /**
     * Sets the saved level values list.
     *
     * @param savedLevelValuesList the savedLevelValuesList to set
     */
    public void setSavedLevelValuesList(final Map<String, List<HierarchyLevelsDTO>> savedLevelValuesList) {
        this.savedLevelValuesList = savedLevelValuesList;
    }

    /**
     * Gets the removes the.
     *
     * @return the remove
     */
    public Button getRemove() {
        return remove;
    }

    /**
     * Sets the removes the.
     *
     * @param remove the remove to set
     */
    public void setRemove(final Button remove) {
        this.remove = remove;
    }

    /**
     * Gets the hierarchy tree.
     *
     * @return the hierarchyTree
     */
    public Tree getHierarchyTree() {
        return hierarchyTree;
    }

    /**
     * Sets the hierarchy tree.
     *
     * @param hierarchyTree the hierarchyTree to set
     */
    public void setHierarchyTree(final Tree hierarchyTree) {
        this.hierarchyTree = hierarchyTree;
    }

    /**
     * Gets the tree bean id.
     *
     * @return the treeBeanId
     */
    public Object getTreeBeanId() {
        return treeBeanId;
    }

    /**
     * Sets the tree bean id.
     *
     * @param treeBeanId the treeBeanId to set
     */
    public void setTreeBeanId(final Object treeBeanId) {
        this.treeBeanId = treeBeanId;
    }

    /**
     * Gets the tree bean.
     *
     * @return the treeBean
     */
    public HierarchyLevelsDTO getTreeBean() {
        return treeBean;
    }

    /**
     * Sets the tree bean.
     *
     * @param treeBean the treeBean to set
     */
    public void setTreeBean(final HierarchyLevelsDTO treeBean) {
        this.treeBean = treeBean;
    }

    /**
     * Gets the total levels.
     *
     * @return the totalLevels
     */
    public int getTotalLevels() {
        return totalLevels;
    }

    /**
     * Sets the total levels.
     *
     * @param totalLevels the totalLevels to set
     */
    public void setTotalLevels(final int totalLevels) {
        this.totalLevels = totalLevels;
    }

    /**
     * Gets the final selected results bean.
     *
     * @return the finalSelectedResultsBean
     */
    public BeanItemContainer<HierarchyLevelsDTO> getFinalSelectedResultsBean() {
        return finalSelectedResultsBean;
    }

    /**
     * Sets the final selected results bean.
     *
     * @param finalSelectedResultsBean the finalSelectedResultsBean to set
     */
    public void setFinalSelectedResultsBean(final BeanItemContainer<HierarchyLevelsDTO> finalSelectedResultsBean) {
        this.finalSelectedResultsBean = finalSelectedResultsBean;
    }

    /**
     * Gets the dao.
     *
     * @return the dao
     */
    public HierarchyBuilderLogicDAO getDao() {
        return dao;
    }

    /**
     * Sets the dao.
     *
     * @param dao the dao to set
     */
    public void setDao(final HierarchyBuilderLogicDAO dao) {
        this.dao = dao;
    }

    /**
     * Inits the.
     *
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    public final void init() throws SystemException, PortalException {
        LOGGER.debug("init Method started");
        space.setHeight("20");

        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/clara/RelationshipBuilder/relationshipBuilder.xml"), this));
        getResponsiveLayout();
        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = sessionDTO.getUserId();
        final Map<String, AppPermission> fieldItemHM = stplSecurity
                .getFieldOrColumnPermission(userId, UISecurityUtil.RELATIONSHIP_BUILDER + "," + "Functional Screen", false);
        final Map<String, AppPermission> functionCompanyHM = stplSecurity.getBusinessFunctionPermission(userId, UISecurityUtil.RELATIONSHIP_BUILDER + "," + "Functional Screen");

        getResponsiveFirstTab(cssLayout, fieldItemHM);
        getResponsiveFirstTab(cssLayout2, fieldItemHM);
        getButtonPermission(functionCompanyHM);
        addBuilderWindow();
        getRelationBuilderBinder();
        configureFields();
        LOGGER.debug("init Method Ended");
    }

    
    private void getResponsiveFirstTab(CssLayout cssLayout, final Map<String, AppPermission> fieldItemHM) {
        LOGGER.debug("Entering getFirstTab1");
        try {
            String mode = sessionDTO.getMode();

            List<Object> resultList = commonUtil.getFieldsForSecurity(UISecurityUtil.RELATIONSHIP_BUILDER, "Functional Screen");
            commonSecurityLogic.removeComponentOnPermission(resultList, cssLayout, fieldItemHM, mode);

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.debug("Ending getFirstTab1");
    }
    private void getButtonPermission(Map<String, AppPermission> functionCompanyHM) {
        backButton();

        if (functionCompanyHM.get(ConstantsUtils.RESET_BUTTON) != null && ((AppPermission) functionCompanyHM.get(ConstantsUtils.RESET_BUTTON)).isFunctionFlag()) {
            resetButton();
        } else {
            resetAll.setVisible(false);
        }
        if (functionCompanyHM.get(ConstantsUtils.SAVE_BUTTON) != null && ((AppPermission) functionCompanyHM.get(ConstantsUtils.SAVE_BUTTON)).isFunctionFlag()) {
            addSaveListener();
        } else {
            save.setVisible(false);
        }
    }

    /**
     * Gets the relation builder binder.
     *
     * @return the relation builder binder
     */
    private CustomFieldGroup getRelationBuilderBinder() {
        LOGGER.debug("getRelationBuilderBinder Method started");
        relationshipBuilderBinder.bindMemberFields(this);
        relationshipBuilderBinder.setItemDataSource(new BeanItem<RelationshipBuilderDTO>(relationshipBuilderDTO));
        relationshipBuilderBinder.setBuffered(true);
        relationshipBuilderBinder.setErrorDisplay(errorMsg);
        LOGGER.debug("getRelationBuilderBinder Method returns relationshipBuilderBinder");
        return relationshipBuilderBinder;
    }

    /**
     * Adds the builder window.
     *
     * @return the component
     */
    private void addBuilderWindow() {
        LOGGER.debug("addBuilderWindow Method started");
        addLevelBuilder();
        addHierarchyTree();
        LOGGER.debug("addBuilderWindow Method returns panel");
    }

    /**
     * Sets the default focus for Relationship Name TextBox.
     */
    public void setFocus() {
        relationshipName.setImmediate(true);
        relationshipName.focus();
    }

    /**
     * Configure fields.
     *
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    private void configureFields() throws SystemException {
        LOGGER.debug("configureFields Method started");
        relationshipName.setData("maxlengthvalidationhundred,maxlengthvalidationrelationshipname,null,null");
        relationshipName.setImmediate(true);
        relationshipName.setRequired(true);

        hierarchyTree.setMultiSelect(false);

        relationshipDesc.setData("maxlengthvalidationhundred,maxlengthvalidationrelationshipdescription,null,null");
        relationshipDesc.setImmediate(true);
        relationshipDesc.setRequired(true);

        relationshipName.setRequired(true);
        relationshipDesc.setRequired(true);
        relationshipName.setRequiredError("Please enter Relationship Name.");
        relationshipDesc.setRequiredError("Please enter Relationship Description.");
        relationshipName.focus();
        relationshipName.setImmediate(true);
        relationshipDesc.setImmediate(true);
        relationshipDesc.setRequired(true);

        versionNo.setImmediate(true);
        versionNo.setRequired(true);
        versionNo.setRequiredError("Please select Version No");
        versionNo.setNullSelectionItemId(0);
        versionNo.select(new HelperDTO(ConstantsUtils.SELECT_ONE));

        LOGGER.debug("In configureFields loadHierarchy started");
        hierarchyNameDdlb = logic.loadHierarchy(hierarchyNameDdlb, sessionDTO);
        LOGGER.debug("In configureFields loadHierarchy Ended");
        hierarchyNameDdlb.setImmediate(true);
        hierarchyNameDdlb.setRequired(true);
        hierarchyNameDdlb.setNullSelectionAllowed(true);
        hierarchyNameDdlb.setNullSelectionItemId(0);
        hierarchyNameDdlb.select(new HelperDTO(ConstantsUtils.SELECT_ONE));
        hierarchyNameDdlb.setRequiredError("Please select Hierarchy Name");

        startDate.setDateFormat("MM/dd/yyyy");
        startDate.setWidth(NumericConstants.FLOAT_THIRTEEN_SIX, DateField.UNITS_EM);

        startDate.setImmediate(true);

        addListeners();

        relationshipType.addItem(ConstantsUtils.PRIMARY);
        relationshipType.addItem("Secondary");
        relationshipType.select(ConstantsUtils.PRIMARY);
        relationshipType.setRequired(true);

        buildType.addItem(ConstantsUtils.MANUAL);
        buildType.addItem(ConstantsUtils.AUTOMATIC);
        buildType.select(ConstantsUtils.MANUAL);
        buildType.setRequired(true);
        buildType.setEnabled(false);

        buildAutomatic.setEnabled(false);
        hierarchyTree.setMultiSelect(true);

        remove.setVisible(false);
        LOGGER.debug("configureFields Method started");

        buildType.addValueChangeListener(new ValueChangeListener() {

            private static final long serialVersionUID = -499726769997636340L;

            @Override
            public void valueChange(ValueChangeEvent event) {
                String type = event.getProperty().getValue().toString();
                if (ConstantsUtils.AUTOMATIC.equals(type)) {
                    buildAutomatic.setEnabled(true);
                } else {
                    buildAutomatic.setEnabled(false);
                }
            }
        });
        buildAutomatic.addClickListener(new ClickListener() {

            private static final long serialVersionUID = 7743964558929839594L;

            @Override
            public void buttonClick(ClickEvent event) {
                String type = buildType.getValue().toString();
                if (ConstantsUtils.AUTOMATIC.equals(type)) {
                    AutomaticTreeBuilder.build(availableLevelsBeanList, hierarchyTree, sessionDTO);
                } else {
                    try {
                        loadHierarchyLevels(sessionDTO);
                    } catch (SystemException e) {
                        LOGGER.error(e);
                    } catch (PortalException e) {
                        LOGGER.error(e);
                    } catch (Exception e) {
                        LOGGER.error(e);
                    }
                }
            }
        });

    }

    void resetButton() {
        resetAll.addClickListener(new Button.ClickListener() {
            @SuppressWarnings("PMD")
            /**
             * Method called when reset all button is clicked
             */
            public void buttonClick(final Button.ClickEvent event) {
                LOGGER.debug("In configureFields resetAll.addClickListener started");
                MessageBox.showPlain(Icon.QUESTION, ConstantsUtils.CONFORMATION, "Are you sure you want to reset the page to default/previous values?", new MessageBoxListener() {
                    /**
                     * Click Listener for Message Box Buttons
                     */
                    @SuppressWarnings("PMD")
                    public void buttonClicked(final ButtonId buttonId) {
                        if (buttonId.name().equals("YES")) {
                            resetButtonClickLogic();
                        }
                    }
                }, ButtonId.YES, ButtonId.NO);
                LOGGER.debug("In configureFields resetAll.addClickListener Ended");
            }
        });
    }

    void backButton() {
        backButton.addClickListener(new Button.ClickListener() {
            /**
             * Method called when back button is clicked
             */
            public void buttonClick(final Button.ClickEvent event) {
                LOGGER.debug("In configureFields backButton.addClickListener started");


                getUI().getNavigator().navigateTo(RelationshipBuilderIndex.NAME);
                LOGGER.debug("In configureFields backButton.addClickListener Ended");
            }
        });
    }

    /**
     * Adds the save listener.
     */
    private void addSaveListener() {
        save.addClickListener(new Button.ClickListener() {
            @SuppressWarnings("PMD")
            /**
             * Method called when save button is clicked
             */
            public void buttonClick(final Button.ClickEvent event) {
                LOGGER.debug("In configureFields save.addClickListener started");
                saveButtonClickLogic(true);
                LOGGER.debug("In configureFields save.addClickListener Ended");
            }
        });
    }

    public void saveButtonClickLogic(boolean flag) {
        try {
            relationshipBuilderBinder.getErrorDisplay().clearError();
            relationshipBuilderBinder.commit();
            final Map<String, String> duplicateRelationName = logic.getExistingRelationshipNames();
            if (StringUtils.isBlank(relationshipName.getValue())) {
                relationshipBuilderBinder.getErrorDisplay().setError("Please enter Relationship Name.");

                return;
            } else {
                if (StringUtils.isBlank(relationshipDesc.getValue())) {
                    relationshipBuilderBinder.getErrorDisplay().setError("Please enter Relationship Description.");

                    return;
                } else {
                    if (StringUtils.isBlank(hierarchy.getValue())) {
                        relationshipBuilderBinder.getErrorDisplay().setError("Please select the Hierarchy Name.");

                        return;
                    } else {
                        if (startDate.getValue() == null) {
                            relationshipBuilderBinder.getErrorDisplay().setError("Please select Start Date.");
                            return;
                        } else {
                            final int rbSystemId = sessionDTO.getSystemId();
                                if (rbSystemId == ConstantsUtils.ZERO_NUM) {
                                    for (Map.Entry<String, String> entry : duplicateRelationName.entrySet()) {
                                    if(entry.getValue().equalsIgnoreCase(String.valueOf(relationshipName))) {
                                        MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "Entered Relationship Name already exists ", ButtonId.OK);
                                        return;
                                    }
                                }
                            } 
                        }
                    }
                }
            }
            if (finalSelectedResultsBean.size() == 0) {
                MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "Please make a tree. ", ButtonId.OK);
                return;
            }
            final List selectedItems = finalSelectedResultsBean.getItemIds();
            final int selectedHierarchyId = sessionDTO.getSelectedHierarchySessionId();
            final HierarchyDefinition hierarchy = dao.getHierarchyDefinition(selectedHierarchyId);
            final HelperTable savedCategory = dao.getCategory(hierarchy.getHierarchyCategory());
            if (CommonUtil.getDescriptionFromHelper(hierarchy.getHierarchyType()).equals(ConstantsUtils.PRIMARY) && savedCategory.getDescription().equals("Data Selection")) {
                final HierarchyLevelsDTO selectedCompany = (HierarchyLevelsDTO) selectedItems.get(0);
                final String companyValue = selectedCompany.getLevelValue();
                final boolean checkCompanyTreeExists = logic.checkForDuplicateTree(companyValue, sessionDTO);
                if (checkCompanyTreeExists) {
                    MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "Selected company already associated with other relationship. Please select different company for the level 1.",
                            ButtonId.OK);
                    return;
                }
            }
            boolean isRelationUsed = logic.relationshipIsUsed(sessionDTO.getSystemId());
            boolean isRelationCurrentlyUsing = logic.relationshipIsCurentlyInUse(sessionDTO.getSystemId());
            if(isRelationUsed) {
                MessageBox.showPlain(Icon.ERROR, "Edit", "Cannot Edit the relationship which is already associated with existing projection", ButtonId.OK);
            } else if (isRelationCurrentlyUsing){
                MessageBox.showPlain(Icon.ERROR, "Edit", "This relationship is currently being used in a projection. Please try again after sometime.", ButtonId.OK);
            } else {
                MessageBox.showPlain(Icon.QUESTION, ConstantsUtils.CONFORMATION, "Save record " + relationshipName + " ?", new MessageBoxListener() {
                    /**
                     * save button click logic
                     */
                    @SuppressWarnings("PMD")
                    public void buttonClicked(final ButtonId buttonId) {
                        if (buttonId.name().equals("YES")) {
                            try {
                                List<Integer> idList = logic.saveRelationshipBuilder(relationshipBuilderBinder, selectedItems, sessionDTO);
                                String page = sessionDTO.getFromViewPage();
                                backFlag = false;
                                    sessionDTO.setSystemId(idList.get(0));
                                    sessionDTO.setFromViewPage("Edit");
                                    sessionDTO.setVersionNo(idList.get(1));
                                    sessionDTO.setHierarchyVersion(idList.get(NumericConstants.TWO));
                                    if (page.equalsIgnoreCase(ConstantsUtils.ADD)) {
                                    getUI().getNavigator().navigateTo(RelationshipBuilderView.NAME);
                                }
                            } catch (SystemException ex) {
                                final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                                AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
                                LOGGER.error(ex);
                            } catch (Exception ex) {
                                AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4002));
                                LOGGER.error(ex);
                            }

                        }
                    }
                }, ButtonId.YES, ButtonId.NO);
            }
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

    /**
     * Adds the listeners.
     */
    private void addListeners() {
        /**
         * hierarchy name ddlb value change listener
         */
        hierarchyNameDdlb.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Called on Property Value Change
             */
            @SuppressWarnings("PMD")
            public void valueChange(final Property.ValueChangeEvent event) {
                LOGGER.debug("In configureFields hierarchyNameDdlb.addValueChangeListener started");
                try {
                    versionOnChangeEvent();

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
        /**
         * hierarchy name ddlb value change listener
         */
        versionNo.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Called on Property Value Change
             */
            @SuppressWarnings("PMD")
            public void valueChange(final Property.ValueChangeEvent event) {
                LOGGER.debug("In configureFields hierarchyNameDdlb.addValueChangeListener started");
                try {

                    if (versionNo.getValue() != null && !ConstantsUtils.NULL.equalsIgnoreCase(String.valueOf(versionNo.getValue())) && !ConstantsUtils.EMPTY.equalsIgnoreCase(String.valueOf(versionNo.getValue()))) {
                        final int version = Integer.valueOf(String.valueOf(versionNo.getValue()));

                        sessionDTO.setHierarchyVersion(version);

                        final int rbSystemId = sessionDTO.getSystemId();
                        if (rbSystemId != 0) {
                            RelationshipBuilder builder = dao.getRelationshipBuilder(rbSystemId);
                            final Object companyValue = ((HelperDTO) hierarchyNameDdlb.getValue()).getId();
                            final int selectedHierarchySysId = Integer.valueOf(companyValue.toString());
                            hierarchyChangeFlag = builder.getHierarchyDefinitionSid() == selectedHierarchySysId;
                        }
                        hierarchyOnChangeEvent();
                        enableBuildType();
                    }

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

        /**
         * startDate value change listener
         */
        startDate.addValueChangeListener(new Property.ValueChangeListener() {
            public void valueChange(final Property.ValueChangeEvent event) {
                try {
                    LOGGER.debug("In configureFields startDate.addValueChangeListener started");
                    startDate.setValue(com.stpl.app.adminconsole.relationshipbuilder.util.CommonUtils.convert2DigitTo4DigitYearFormat(startDate.getValue()));
                    LOGGER.debug("In configureFields startDate.addValueChangeListener Ended");
                } catch (SystemException ex) {
                    final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                    AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
                    LOGGER.error(ex);
                }
            }
        });

    }

    /**
     * Load hierarchy.
     *
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    private void loadHierarchy() throws SystemException {

    }

    /**
     * Hierarchy on change event.
     *
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    protected void hierarchyOnChangeEvent() throws SystemException, PortalException {
        LOGGER.debug("hierarchyOnChangeEvent Method started");
        if (hierarchyNameDdlb.getValue() == null) {
            hierarchy.setValue(null);
        } else {
            final Object companyValue = ((HelperDTO) hierarchyNameDdlb.getValue()).getId();
            hierarchy.setValue(((HelperDTO) hierarchyNameDdlb.getValue()).getDescription());
            final int selectedHierarchySysId = Integer.valueOf(companyValue.toString());
            sessionDTO.setSelectedHierarchySessionId(selectedHierarchySysId);
            loadHierarchyLevels(sessionDTO);
            levelPanel.setVisible(true);
            treePanel.setVisible(true);
        }
        LOGGER.debug("hierarchyOnChangeEvent Method Ended");
    }

    /**
     * Version on change event.
     *
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    protected void versionOnChangeEvent() throws SystemException, PortalException {
        if (hierarchyNameDdlb.getValue() == null || ConstantsUtils.NULL.equalsIgnoreCase(String.valueOf(hierarchyNameDdlb.getValue())) || ConstantsUtils.EMPTY.equalsIgnoreCase(String.valueOf(hierarchyNameDdlb.getValue()))) {
            versionNo.removeAllItems();
            versionNo.setValue(null);
            treePanel.setVisible(false);
            levelPanel.setVisible(false);
        } else {
            loadVersion();
        }
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
    private void addHierarchyTree() {
        LOGGER.debug("addHierarchyTree Method started");

        hierarchyTree.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            /**
             * Called on Item Click
             */
            @SuppressWarnings("PMD")
            public void itemClick(final ItemClickEvent event) {
                LOGGER.debug("In addHierarchyTree hierarchyTree.addItemClickListener started");
                treeBeanId = event.getItemId();
                BeanItem<HierarchyLevelsDTO> targetItem = null;
                if (treeBeanId instanceof HierarchyLevelsDTO) {
                    targetItem = new BeanItem<HierarchyLevelsDTO>((HierarchyLevelsDTO) treeBeanId);
                }
                treeBean = (HierarchyLevelsDTO) targetItem.getBean();
                int levelNo = Integer.valueOf(treeBean.getLevelNo());
                if (levelNo < availableLevelsBeanList.size()) {
                    Collection<?> childs = hierarchyTree.getChildren(treeBeanId);
                    final Object value = ((HelperDTO) hierarchyNameDdlb.getValue()).getId();
                    final int selectedHierarchySysId = Integer.valueOf(value.toString());
                    List<String> levelValues = new ArrayList<String>();
                    String levelValue = treeBean.getLevelValue();
                    levelValues.add(ConstantsUtils.EMPTY);
                    levelValues.add(levelValue);
                    List<String> primarykeyColumnList = new ArrayList<String>();
                    primarykeyColumnList.add(ConstantsUtils.EMPTY);
                    primarykeyColumnList.add(treeBean.getPrimaryKeyColumn());
                    List<String> primarySIDList = new ArrayList<String>();
                    primarySIDList.add(ConstantsUtils.EMPTY);
                    primarySIDList.add(treeBean.getHiddenId());
                    Object childItemId = treeBeanId;
                    for (int i = 1; i < levelNo; i++) {
                        Object parentItemId = hierarchyTree.getParent(childItemId);
                        HierarchyLevelsDTO parent;
                        BeanItem<HierarchyLevelsDTO> tempItem = null;
                        if (parentItemId instanceof HierarchyLevelsDTO) {
                            tempItem = new BeanItem<HierarchyLevelsDTO>((HierarchyLevelsDTO) parentItemId);
                        }
                        parent = (HierarchyLevelsDTO) tempItem.getBean();
                        levelValues.add(parent.getLevelValue());
                        primarykeyColumnList.add(parent.getPrimaryKeyColumn());
                        primarySIDList.add(parent.getHiddenId());
                        childItemId = parentItemId;
                    }

                    List<HierarchyLevelsDTO> filteredValues = BpmLogic.getFilteredValues(selectedHierarchySysId, levelNo, levelValues, primarykeyColumnList, primarySIDList);
                    if (filteredValues != null) {
                        availableLevelsBeanList.get(levelNo).removeAllItems();
                        availableLevelsBeanList.get(levelNo).addAll(filteredValues);
                        availableTableList.get(levelNo).setValue(null);
                    } else {
                        levelNo++;
                        Collections.reverse(levelValues);
                        Collections.reverse(primarykeyColumnList);
                        Collections.reverse(primarySIDList);
                        levelValues.add(ConstantsUtils.EMPTY);
                        primarykeyColumnList.add(ConstantsUtils.EMPTY);
                        primarySIDList.add(ConstantsUtils.EMPTY);
                        Collections.reverse(levelValues);
                        Collections.reverse(primarykeyColumnList);
                        Collections.reverse(primarySIDList);

                        outer:
                        for (; levelNo < availableLevelsBeanList.size(); levelNo++) {
                            LOGGER.debug("Going for next level");
                            filteredValues = BpmLogic.getFilteredValues(selectedHierarchySysId, levelNo, levelValues, primarykeyColumnList, primarySIDList);
                            if (filteredValues != null) {
                                availableLevelsBeanList.get(levelNo).removeAllItems();
                                availableLevelsBeanList.get(levelNo).addAll(filteredValues);
                                break outer;
                            } else {
                                Collections.reverse(levelValues);
                                Collections.reverse(primarykeyColumnList);
                                Collections.reverse(primarySIDList);
                                levelValues.add(ConstantsUtils.EMPTY);
                                primarykeyColumnList.add(ConstantsUtils.EMPTY);
                                primarySIDList.add(ConstantsUtils.EMPTY);
                                Collections.reverse(levelValues);
                                Collections.reverse(primarykeyColumnList);
                                Collections.reverse(primarySIDList);
                            }

                        }
                    }

                    manageHierarchyLevels(childs);
                }
                LOGGER.debug("In addHierarchyTree hierarchyTree.addItemClickListener Ended");
            }
        });
        layout2.addComponent(hierarchyTree);
        layout2.addComponent(remove);
        /**
         * Remove button click listener
         */
        remove.addClickListener(new Button.ClickListener() {
            public void buttonClick(final Button.ClickEvent event) {
                LOGGER.debug("In addHierarchyTree remove.addClickListener started");
                if (hierarchyTree.getItemIds().size() > ConstantsUtils.ZERO_NUM) {
                    if (treeBean == null) {
                        MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "Please select a node to remove", ButtonId.OK);
                        return;
                    } else {
                        int count = 0;

                        if (count > 0) {
                            MessageBox.showPlain(Icon.ERROR, "Delete", "Cannot Delete the Company group which are already associated with existing projection", ButtonId.OK);
                            return;
                        } else {
                            if (hierarchyTree.getValue() instanceof Collection) {

                                final java.util.Set<HierarchyLevelsDTO> hierarchyList = (java.util.Set<HierarchyLevelsDTO>) hierarchyTree.getValue();
                                for (final Iterator<HierarchyLevelsDTO> iterator = hierarchyList.iterator(); iterator.hasNext();) {
                                    final HierarchyLevelsDTO item = iterator.next();

                                    final Collection<Object> collection = (Collection<Object>) hierarchyTree.getChildren(item);
                                    if (collection == null) {
                                        final HierarchyLevelsDTO selectedNodeToRemove = item;
                                        final Collection<Object> parentChilds = (Collection<Object>) hierarchyTree.getChildren(hierarchyTree.getParent(item));
                                        finalSelectedResultsBean.removeItem(selectedNodeToRemove);
                                        hierarchyTree.removeItem(item);
                                        int levelNo = Integer.valueOf(selectedNodeToRemove.getLevelNo()) - 1;
                                        selectedNodeToRemove.setParentNode("0");
                                        final Object value = ((HelperDTO) hierarchyNameDdlb.getValue()).getId();
                                        final int selectedHierarchySysId = Integer.valueOf(value.toString());
                                        final int version = Integer.valueOf(String.valueOf(versionNo.getValue()));
                                        List<HierarchyLevelsDTO> filteredValues = BpmLogic.getOldValues(selectedHierarchySysId, version, levelNo + 1);
                                        if (filteredValues != null) {
                                            LOGGER.debug("Refreshing Level " + (levelNo + 1) + " first");
                                            availableLevelsBeanList.get(levelNo).removeAllItems();
                                            availableLevelsBeanList.get(levelNo).addAll(filteredValues);
                                            if (parentChilds != null && !parentChilds.isEmpty()) {
                                                LOGGER.debug("Already " + parentChilds.size() + " are attached");
                                                for (final Iterator<Object> iterator1 = parentChilds.iterator(); iterator1.hasNext();) {
                                                    final Object item1 = iterator1.next();
                                                    availableLevelsBeanList.get(levelNo).removeItem(item1);
                                                }
                                            }
                                            levelNo++;
                                            if (levelNo < availableLevelsBeanList.size()) {
                                                filteredValues = BpmLogic.getOldValues(selectedHierarchySysId, version, levelNo + 1);
                                                if (filteredValues != null) {
                                                    LOGGER.debug("Refreshing Level " + (levelNo + 1) + " second");
                                                    availableLevelsBeanList.get(levelNo).removeAllItems();
                                                    availableLevelsBeanList.get(levelNo).addAll(filteredValues);
                                                }
                                            }
                                        } else {
                                            availableLevelsBeanList.get(levelNo).addItem(selectedNodeToRemove);
                                        }
                                        if (hierarchyTree.getItemIds().isEmpty()) {
                                            remove.setVisible(false);
                                        }

                                    } else {
                                        MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "Please remove the child nodes of  " + treeBean.getLevelValue(), ButtonId.OK);
                                        return;
                                    }
                                }
                            } else {
                                final Collection<Object> collection = (Collection<Object>) hierarchyTree.getChildren(treeBeanId);
                                if (collection == null) {
                                    backFlag = true;
                                    final HierarchyLevelsDTO selectedNodeToRemove = (HierarchyLevelsDTO) treeBean;
                                    finalSelectedResultsBean.removeItem(selectedNodeToRemove);
                                    hierarchyTree.removeItem(treeBeanId);
                                    final int levelNo = Integer.valueOf(selectedNodeToRemove.getLevelNo()) - 1;
                                    selectedNodeToRemove.setParentNode("0");
                                    availableLevelsBeanList.get(levelNo).addItem(selectedNodeToRemove);
                                    if (hierarchyTree.getItemIds().isEmpty()) {
                                        remove.setVisible(false);
                                    }
                                } else {
                                    MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "Please remove the child nodes of  " + treeBean.getLevelValue(), ButtonId.OK);
                                    return;
                                }
                            }
                        }
                    }
                } else {
                    MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "No data to remove", ButtonId.OK);
                    return;
                }
                treeBean = null;
                LOGGER.debug("In addHierarchyTree remove.addClickListener Ended");
            }
        });

        LOGGER.debug("addHierarchyTree Method Ended");

    }

    protected void manageHierarchyLevels(Collection<?> ids){
        if(ids!=null && !ids.isEmpty()){
             int levelNo=-1;
             for (Object id : ids) {
                HierarchyLevelsDTO dto = (HierarchyLevelsDTO) id;                 
                levelNo = Integer.valueOf(dto.getLevelNo())-1;
                dto.setEquality(true);
                BeanItemContainer<HierarchyLevelsDTO> cont=availableLevelsBeanList.get(levelNo);
                if(cont!=null){
                    cont.removeItem(dto);
                }
                dto.setEquality(false);                
            }
             
        }
    }

    /**
     * Load hierarchy levels.
     *
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public void loadHierarchyLevels(final SessionDTO sessionDTO) throws SystemException, PortalException {
        LOGGER.debug("loadHierarchyLevels Method started");
        final int selectedHierarchyId = sessionDTO.getSelectedHierarchySessionId();
        final String fromViewPage = sessionDTO.getFromViewPage();
        if (selectedHierarchyId != ConstantsUtils.ZERO_NUM) {
            if (fromViewPage.equalsIgnoreCase(ConstantsUtils.SMALL_YES) || fromViewPage.equalsIgnoreCase(ConstantsUtils.ADD) || fromViewPage.equalsIgnoreCase(ConstantsUtils.EDIT)) {
                final List<HistHierarchyLevelDefn> listOfLevels = logic.getHistHierarchyLevels(selectedHierarchyId, sessionDTO);
                levelValuesList = logic.getHistAllLevelValues(listOfLevels, sessionDTO);
                final int levelCount = listOfLevels.size();
                totalLevels = levelCount;
                finalSelectedResultsBean.removeAllItems();
                hierarchyTree.removeAllItems();
                final int rbSystemId = sessionDTO.getSystemId();
                if (rbSystemId == ConstantsUtils.ZERO_NUM) {
                    remove.setVisible(false);
                } else if (hierarchyChangeFlag) {
                    final List<Map<String, List<HierarchyLevelsDTO>>> availSavedLevelValuesList = logic.getSavedHistLevelValuesList(levelValuesList, listOfLevels, sessionDTO);
                    levelValuesList.clear();
                    levelValuesList = availSavedLevelValuesList.get(0);
                    savedLevelValuesList = availSavedLevelValuesList.get(1);
                    final Map<String, List<HierarchyLevelsDTO>> finalSavedLevelsList = availSavedLevelValuesList.get(NumericConstants.TWO);
                    Collections.sort(finalSavedLevelsList.get(String.valueOf(rbSystemId)), new Comparator<HierarchyLevelsDTO>() {
                        @Override
                        public int compare(final HierarchyLevelsDTO lhs, HierarchyLevelsDTO rhs) {
                            try {
                                int lhsLevel = Integer.parseInt(lhs.getLevelNo());
                                int rhsLevel = Integer.parseInt(rhs.getLevelNo());
                                if (lhsLevel < rhsLevel) {
                                    return -1;
                                } else if (lhsLevel > rhsLevel) {
                                    return 1;
                                } else {
                                    String lhsStr = lhs.getHierarchyNo().endsWith(".") ? lhs.getHierarchyNo().substring(0, lhs.getHierarchyNo().lastIndexOf(".")) : lhs.getHierarchyNo();
                                    String rhsStr = rhs.getHierarchyNo().endsWith(".") ? rhs.getHierarchyNo().substring(0, rhs.getHierarchyNo().lastIndexOf(".")) : rhs.getHierarchyNo();
                                    int lhsHiddenId = Integer.parseInt(lhsStr.substring(lhsStr.lastIndexOf(".") + 1));
                                    int rhsHiddenId = Integer.parseInt(rhsStr.substring(rhsStr.lastIndexOf(".") + 1));
                                    if (lhsHiddenId < rhsHiddenId) {
                                        return -1;
                                    } else if (lhsHiddenId > rhsHiddenId) {
                                        return 1;
                                    }
                                }
                            } catch (Exception e) {
                                LOGGER.error(e);

                            }
                            return 0;
                        }
                    });
                    finalSelectedResultsBean.addAll(finalSavedLevelsList.get(String.valueOf(rbSystemId)));
                    List<HierarchyLevelsDTO> savedTree = finalSelectedResultsBean.getItemIds();
                    final Map<String, HierarchyLevelsDTO> finalSavedLevelsList1 = new HashMap<>();
                    for (int i = 0; i < savedTree.size(); i++) {
                        final HierarchyLevelsDTO savedTreeNode = savedTree.get(i);
                        finalSavedLevelsList1.put(savedTreeNode.getHierarchyNo(), savedTreeNode);
                    }
                    for (int i = 0; i < savedTree.size(); i++) {
                        final HierarchyLevelsDTO savedTreeNode = savedTree.get(i);
                        hierarchyTree.addItem(savedTreeNode);
                        String hierarchyNo = savedTreeNode.getHierarchyNo().substring(0, savedTreeNode.getHierarchyNo().length() - 1);
                        Object parent = null;
                        if (hierarchyNo.lastIndexOf('.') != -1) {
                            String parentHierrarchy = hierarchyNo.substring(0, hierarchyNo.lastIndexOf('.')+1);
                            parent = finalSavedLevelsList1.get(parentHierrarchy);
                        }
                        hierarchyTree.setParent(savedTreeNode, parent);
                        hierarchyTree.setItemCaption(savedTreeNode, savedTreeNode.getLevelValue());
                        if (Integer.valueOf(savedTreeNode.getLevelNo()) == totalLevels) {
                            hierarchyTree.setChildrenAllowed(savedTreeNode, false);
                        }
                    }

                    for (final Iterator<?> it = hierarchyTree.rootItemIds().iterator(); it.hasNext();) {
                        hierarchyTree.expandItemsRecursively(it.next());
                    }
                    final VerticalLayout content = new VerticalLayout();
                    content.addComponent(hierarchyTree);
                    content.addComponent(remove);
                    treePanel.setContent(content);
                }

                layout = new GridLayout(1, levelCount);
                for (int i = 0; i < levelCount; i++) {
                    levelLayout = new HorizontalLayout();
                    levelLayout.setSpacing(true);
                    final HistHierarchyLevelDefn levelObj = (HistHierarchyLevelDefn) listOfLevels.get(i);
                    List<HierarchyLevelsDTO> levelValues;
                    List<HierarchyLevelsDTO> savedLevelValues;
                    levelValues = levelValuesList.get(String.valueOf(levelObj.getHierarchyLevelDefinitionSid()));
                    if (levelValues == null) {
                        levelValues = new ArrayList<HierarchyLevelsDTO>();
                    }
                    if (rbSystemId == ConstantsUtils.ZERO_NUM) {
                        savedLevelValues = new ArrayList<HierarchyLevelsDTO>();
                    } else {
                        savedLevelValues = savedLevelValuesList.get(String.valueOf(levelObj.getHierarchyLevelDefinitionSid()));
                    }

                    final int levelNo = i + 1;
                    levelLayout.addComponent(addLevelStructure(levelObj.getLevelName(), levelValues, savedLevelValues, levelNo, i));
                    levelLayout.addComponent(addGridButtons(i, levelNo));
                    addSelectedLevelValues(savedLevelValues, levelNo);
                    layout.addComponent(levelLayout);

                }
            } else {
                final List<HierarchyLevelDefinition> listOfLevels = logic.getHierarchyLevels(selectedHierarchyId);
                levelValuesList = logic.getAllLevelValues(listOfLevels);
                final int levelCount = listOfLevels.size();
                totalLevels = levelCount;
                finalSelectedResultsBean.removeAllItems();
                hierarchyTree.removeAllItems();
                final int rbSystemId = sessionDTO.getSystemId();
                if (rbSystemId == ConstantsUtils.ZERO_NUM) {
                    remove.setVisible(false);
                } else {
                    final List<Map<String, List<HierarchyLevelsDTO>>> availSavedLevelValuesList = logic.getSavedLevelValuesList(levelValuesList, listOfLevels, sessionDTO);
                    levelValuesList.clear();
                    levelValuesList = availSavedLevelValuesList.get(0);
                    savedLevelValuesList = availSavedLevelValuesList.get(1);
                    final Map<String, List<HierarchyLevelsDTO>> finalSavedLevelsList = availSavedLevelValuesList.get(NumericConstants.TWO);
                    finalSelectedResultsBean.addAll(finalSavedLevelsList.get(String.valueOf(rbSystemId)));
                    final List<HierarchyLevelsDTO> savedTree = finalSelectedResultsBean.getItemIds();
                    final Map<String, HierarchyLevelsDTO> finalSavedLevelsList1 = new HashMap<String, HierarchyLevelsDTO>();
                    for (int i = 0; i < savedTree.size(); i++) {
                        final HierarchyLevelsDTO savedTreeNode = savedTree.get(i);
                        finalSavedLevelsList1.put(savedTreeNode.getHierarchyNo(), savedTreeNode);
                    }
                    for (int i = 0; i < savedTree.size(); i++) {
                        final HierarchyLevelsDTO savedTreeNode = savedTree.get(i);
                        hierarchyTree.addItem(savedTreeNode);
                         String hierarchyNo = savedTreeNode.getHierarchyNo().substring(0, savedTreeNode.getHierarchyNo().length() - 1);
                        Object parent = null;
                        if (hierarchyNo.lastIndexOf('.') != -1) {
                            String parentHierrarchy = hierarchyNo.substring(0, hierarchyNo.lastIndexOf('.')+1);
                            parent = finalSavedLevelsList1.get(parentHierrarchy);
                        }
                        hierarchyTree.setParent(savedTreeNode, parent);
                        hierarchyTree.setItemCaption(savedTreeNode, savedTreeNode.getLevelValue());
                        if (Integer.valueOf(savedTreeNode.getLevelNo()) == totalLevels) {
                            hierarchyTree.setChildrenAllowed(savedTreeNode, false);
                        }
                    }

                    for (final Iterator<?> it = hierarchyTree.rootItemIds().iterator(); it.hasNext();) {
                        hierarchyTree.expandItemsRecursively(it.next());
                    }
                    final VerticalLayout content = new VerticalLayout();
                    content.addComponent(hierarchyTree);
                    content.addComponent(remove);
                    treePanel.setContent(content);
                }

                layout = new GridLayout(1, levelCount);
                for (int i = 0; i < levelCount; i++) {
                    levelLayout = new HorizontalLayout();
                    levelLayout.setSpacing(true);
                    final HierarchyLevelDefinition levelObj = (HierarchyLevelDefinition) listOfLevels.get(i);
                    List<HierarchyLevelsDTO> levelValues;
                    List<HierarchyLevelsDTO> savedLevelValues;
                    levelValues = levelValuesList.get(String.valueOf(levelObj.getHierarchyLevelDefinitionSid()));
                    if (levelValues == null) {
                        levelValues = new ArrayList<HierarchyLevelsDTO>();
                    }
                    if (rbSystemId == ConstantsUtils.ZERO_NUM) {
                        savedLevelValues = new ArrayList<HierarchyLevelsDTO>();
                    } else {
                        savedLevelValues = savedLevelValuesList.get(String.valueOf(levelObj.getHierarchyLevelDefinitionSid()));
                    }

                    final int levelNo = i + 1;
                    levelLayout.addComponent(addLevelStructure(levelObj.getLevelName(), levelValues, savedLevelValues, levelNo, i));
                    levelLayout.addComponent(addGridButtons(i, levelNo));
                    addSelectedLevelValues(savedLevelValues, levelNo);
                    layout.addComponent(levelLayout);

                }
            }

        }
        levelPanel.setContent(layout);
        manageHierarchyLevels(hierarchyTree.rootItemIds());
    }

    /**
     * Adds the grid buttons.
     *
     * @param obj the i
     * @param levelNo the level no
     * @return the component
     */
    private Component addGridButtons(final int obj, final int levelNo) {
        final VerticalLayout content = new VerticalLayout();
        Button add;
        add = new Button(" Add To Tree ");
        add.addClickListener(new Button.ClickListener() {
            /**
             * Add button click logic
             */
            @SuppressWarnings("PMD")
            public void buttonClick(final Button.ClickEvent event) {
                LOGGER.debug("In addGridButtons add.addClickListener started");
                if (availableTableList.get(obj).getValue() == null) {
                    MessageBox.showPlain(Icon.INFO, "Halt", "Please select a row from Available <Level " + levelNo + ">", ButtonId.OK);
                } else {
                    addItemsButtonClick(event, obj, levelNo);
                }
            }
        });

        final String fromViewPage = sessionDTO.getFromViewPage();
        if (fromViewPage.equals(ConstantsUtils.SMALL_YES)) {
            add.setEnabled(false);
        }
        space.setHeight("20");
        add.setWidth("140px");
        content.addComponentAsFirst(space);
        content.setMargin(true);
        content.addComponent(add);
        return content;
    }

    /**
     * Adds the level structure.
     *
     * @param levelName the level name
     * @param levelValues the level values
     * @param savedLevelValues the saved level values
     * @param obj the i
     * @return the filter table
     */
    private Panel addLevelStructure(final String levelName, final List<HierarchyLevelsDTO> levelValues, final List<HierarchyLevelsDTO> savedLevelValues, final int obj, final int tableNo) {
        ExtFilterTable availableResults;
        BeanItemContainer<HierarchyLevelsDTO> availableResultsBean;
        if (obj == ConstantsUtils.ONE) {
            availableLevelsBeanList.clear();
        }
        availableResultsBean = new BeanItemContainer<HierarchyLevelsDTO>(HierarchyLevelsDTO.class);
        availableResultsBean.removeAllItems();
        availableResultsBean.addAll(levelValues);
        if (savedLevelValues != null && !savedLevelValues.isEmpty()) {
            for (int i = 0; i < savedLevelValues.size(); i++) {
                final HierarchyLevelsDTO itemId = savedLevelValues.get(i);
                availableResultsBean.removeItem(itemId);
            }
        }
        availableLevelsBeanList.add(obj - 1, availableResultsBean);
        availableResults = new ExtFilterTable();
        availableResults.markAsDirty();
        availableResults.setFilterBarVisible(true);
        availableResults.setFilterDecorator(new ExtDemoFilterDecorator());
        availableResults.setContainerDataSource(availableLevelsBeanList.get(obj - 1));
        availableResults.setVisibleColumns(CommonUIUtil.RB_AVAILABLE_LEVEL_COLUMN);
        availableResults.setColumnHeaders(levelName);
        availableResults.setPageLength(NumericConstants.FIVE);
        availableResults.setWidth("398px");
        availableResults.setImmediate(true);
        availableResults.setSelectable(true);
        availableResults.setMultiSelect(true);
        /**
         * Available results item click listener
         */
        availableResults.addListener(new ItemClickEvent.ItemClickListener() {
            /**
             * Called on Item Click
             */
            @SuppressWarnings("PMD")
            public void itemClick(final ItemClickEvent event) {
                if (event != null) {
                }
            }
        });
        /**
         * Available results value change listener
         */
        availableResults.addValueChangeListener(new Property.ValueChangeListener() {
            public void valueChange(final Property.ValueChangeEvent event) {

            }
        });
        /**
         * Available results error handler
         */
        availableResults.setErrorHandler(new ErrorHandler() {
            public void error(final com.vaadin.server.ErrorEvent event) {

            }
        });
        availableResults.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            /**
             * method to listen the action
             *
             */
            public void itemClick(final ItemClickEvent event) {
                if (event.isDoubleClick()) {
                    addItemsButtonClick(null, tableNo, obj);
                }
            }
        });

        availableTableList.add(obj - 1, availableResults);
        Panel availableResultsPanel = new Panel("Available &lt;Level " + obj + " &gt; :");
        availableResultsPanel.setWidth("400px");
        availableResultsPanel.setContent(availableTableList.get(obj - 1));
        return availableResultsPanel;
    }

    /**
     * Adds the selected level values.
     *
     * @param levelName the level name
     * @param savedLevelValues the saved level values
     * @param obj the i
     */
    private void addSelectedLevelValues( final List<HierarchyLevelsDTO> savedLevelValues, final int obj) {
        BeanItemContainer<HierarchyLevelsDTO> selectedResultsBean;
        if (obj == ConstantsUtils.ONE) {
            selectedLevelsBeanList.clear();
        }
        selectedResultsBean = new BeanItemContainer<HierarchyLevelsDTO>(HierarchyLevelsDTO.class);
        selectedResultsBean.removeAllItems();
        if (savedLevelValues != null && !savedLevelValues.isEmpty()) {
            selectedResultsBean.addAll(savedLevelValues);
        }

        selectedLevelsBeanList.add(obj - 1, selectedResultsBean);

    }

    /**
     * Adds the items button click.
     *
     * @param event the event
     * @param obj the i
     * @param levelNo the level no
     */
    protected void addItemsButtonClick(final Button.ClickEvent event, final int obj, final int levelNo) {
        LOGGER.debug("addItemsButtonClick Method started with i value:" + obj);
        final java.util.Set<HierarchyLevelsDTO> itemMasterDetailsList = (java.util.Set<HierarchyLevelsDTO>) availableTableList.get(obj).getValue();
        if (levelNo == 1 && itemMasterDetailsList.size() > 1) {
            return;
        }
        if (availableTableList.get(obj).getValue() instanceof Collection) {
            addMultipleItem(obj, levelNo);
        } else {
            addSingleItem(obj, levelNo);
        }
    }

    /**
     * addSingleValues
     * @param obj
     * @param levelNo
     */
    protected void addSingleItem(final int obj, final int levelNo) {
        if (levelNo != 1 && treeBean == null && hierarchyTree.getItemIds().isEmpty()) {
            MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "Please select level 1 as first Node", ButtonId.OK);
        } else if (!hierarchyTree.getItemIds().isEmpty() && treeBean == null) {
            MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "Please select the parent node ", ButtonId.OK);

        } else {
            final HierarchyLevelsDTO itemId = (HierarchyLevelsDTO) availableTableList.get(obj).getValue();
            if (hierarchyTree.getItemIds().isEmpty()) {
                backFlag = true;
                availableLevelsBeanList.get(obj).removeItem(itemId);

                if (levelNo == ConstantsUtils.ONE) {
                    itemId.setParentNode("0");
                    itemId.setHierarchyNo("1");

                } else {
                    itemId.setParentNode(treeBean.getLevelNo() + "~" + treeBean.getHiddenId());
                }
                hierarchyTree.addItem(itemId);
                hierarchyTree.setParent(itemId, treeBean);
                hierarchyTree.setItemCaption(itemId, itemId.getLevelValue());
                if (levelNo == totalLevels) {
                    hierarchyTree.setChildrenAllowed(itemId, false);
                }
              
                for (final Iterator<?> it = hierarchyTree.rootItemIds().iterator(); it.hasNext();) {
                    hierarchyTree.expandItemsRecursively(it.next());
                }
                remove.setVisible(true);
                finalSelectedResultsBean.addItem(itemId);
            } else {
                if (Integer.valueOf(treeBean.getLevelNo()) == Integer.valueOf(itemId.getLevelNo()) - 1) {
                    backFlag = true;
                    availableLevelsBeanList.get(obj).removeItem(itemId);
                    if (levelNo == ConstantsUtils.ONE) {
                        itemId.setParentNode("0");
                        itemId.setHierarchyNo("1");
                    } else {
                        if (hierarchyTree.getChildren(treeBeanId) == null || hierarchyTree.getChildren(treeBeanId).size() == 0) {
                            String hierrachy = treeBean.getHierarchyNo() + ".1";
                            itemId.setHierarchyNo(hierrachy);
                        } else {
                            int size = hierarchyTree.getChildren(treeBeanId).size();
                            size = size + 1;
                            String hierarchy1 = treeBean.getHierarchyNo();
                            String hierrachyNo = hierarchy1 + "." + size;
                            itemId.setHierarchyNo(hierrachyNo);
                        }
                        itemId.setParentNode(treeBean.getLevelNo() + "~" + treeBean.getHiddenId());

                    }
                    hierarchyTree.addItem(itemId);
                    hierarchyTree.setParent(itemId, treeBean);
                    hierarchyTree.setItemCaption(itemId, itemId.getLevelValue());
                    if (levelNo == totalLevels) {
                        hierarchyTree.setChildrenAllowed(itemId, false);
                    }
                
                    for (final Iterator<?> it = hierarchyTree.rootItemIds().iterator(); it.hasNext();) {
                        hierarchyTree.expandItemsRecursively(it.next());
                    }
                    remove.setVisible(true);

                } else {
                    MessageBox.showPlain(Icon.INFO, "Halt", "You have selected the incorrect parent node.Please select the correct parent node. ", ButtonId.OK);
                    return;
                }
                finalSelectedResultsBean.addItem(itemId);
            }
        }
        LOGGER.debug("addItemsButtonClick Method Ended");
    }

    /**
     * addSingleValues
     */
    protected void addMultipleItem(final int obj, final int levelNo) {
        if (levelNo != 1 && treeBean == null && hierarchyTree.getItemIds().isEmpty()) {
            MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "Please select level 1 as first Node", ButtonId.OK);
        } else if (!hierarchyTree.getItemIds().isEmpty() && treeBean == null) {
            MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "Please select the parent node ", ButtonId.OK);
           
        } else {
            final java.util.Set<HierarchyLevelsDTO> itemMasterDetailsList = (java.util.Set<HierarchyLevelsDTO>) availableTableList.get(obj).getValue();
            for (final Iterator<HierarchyLevelsDTO> iterator = itemMasterDetailsList.iterator(); iterator.hasNext();) {
                if (hierarchyTree.getItemIds().isEmpty()) {
                    final HierarchyLevelsDTO itemId = iterator.next();

                    availableLevelsBeanList.get(obj).removeItem(itemId);
                    if (levelNo == ConstantsUtils.ONE) {
                        itemId.setParentNode("0");
                        itemId.setHierarchyNo("1");

                    } else {
                        itemId.setParentNode(treeBean.getLevelNo() + "~" + treeBean.getHiddenId());
                    }
                    hierarchyTree.addItem(itemId);
                    hierarchyTree.setParent(itemId, treeBean);
                    hierarchyTree.setItemCaption(itemId, itemId.getLevelValue());
                    if (levelNo == totalLevels) {
                        hierarchyTree.setChildrenAllowed(itemId, false);
                    }
                    for (final Iterator<?> it = hierarchyTree.rootItemIds().iterator(); it.hasNext();) {
                        hierarchyTree.expandItemsRecursively(it.next());
                    }
                    remove.setVisible(true);
                    finalSelectedResultsBean.addItem(itemId);

                } else {
                    final HierarchyLevelsDTO itemId = iterator.next();
                    if (Integer.valueOf(treeBean.getLevelNo()) == Integer.valueOf(itemId.getLevelNo()) - 1) {
                            availableLevelsBeanList.get(obj).removeItem(itemId);
                            if (levelNo == ConstantsUtils.ONE) {
                                itemId.setParentNode("0");
                                itemId.setHierarchyNo("1");
                            } else {
                                if (hierarchyTree.getChildren(treeBeanId) == null || hierarchyTree.getChildren(treeBeanId).size() == 0) {
                                    String rbSystemId = String.valueOf(sessionDTO.getSystemId());
                                    String hierarchy1 = treeBean.getHierarchyNo();
                                    hierarchy1 = hierarchy1.endsWith(".") ? hierarchy1.substring(0, hierarchy1.lastIndexOf(".")) : hierarchy1;
                                    rbSystemId = rbSystemId + "-";
                                    hierarchy1 = hierarchy1.replace(rbSystemId, ConstantsUtils.EMPTY);
                                    String hierrachyNo = hierarchy1 + ".1";
                                    itemId.setHierarchyNo(hierrachyNo);
                                } else {
                                    String rbSystemId = String.valueOf(sessionDTO.getSystemId());
                                    Collection<?> lis = hierarchyTree.getChildren(treeBeanId);

                                    int s4 = setNewHierarchyNo(lis) + 1;
                                    String hierarchy1 = treeBean.getHierarchyNo();
                                    hierarchy1 = hierarchy1.endsWith(".") ? hierarchy1.substring(0, hierarchy1.lastIndexOf(".")) : hierarchy1;
                                    rbSystemId = rbSystemId + "-";
                                    hierarchy1 = hierarchy1.replace(rbSystemId,ConstantsUtils.EMPTY);
                                    String hierrachyNo = hierarchy1 + "." + s4;
                                    itemId.setHierarchyNo(hierrachyNo);
                                    LOGGER.debug("Hierarchy No Final --- >> " + hierrachyNo);

                                }
                                itemId.setParentNode(treeBean.getLevelNo() + "~" + treeBean.getHiddenId());

                            }
                            hierarchyTree.addItem(itemId);
                            hierarchyTree.setParent(itemId, treeBean);
                            hierarchyTree.setItemCaption(itemId, itemId.getLevelValue());
                            if (levelNo == totalLevels) {
                                hierarchyTree.setChildrenAllowed(itemId, false);
                            }

                            for (final Iterator<?> it = hierarchyTree.rootItemIds().iterator(); it.hasNext();) {
                                hierarchyTree.expandItemsRecursively(it.next());
                            }
                            remove.setVisible(true);
                    } else {
                        MessageBox.showPlain(Icon.INFO, "Halt", "You have selected the incorrect parent node.Please select the correct parent node. ", ButtonId.OK);
                        return;
                    }
                    finalSelectedResultsBean.addItem(itemId);
                }
            }
        }
        LOGGER.debug("addItemsButtonClick Method Ended");
    }

    /**
     * Reset button click logic.
     */
    protected void resetButtonClickLogic() {
        LOGGER.debug("resetButtonClickLogic Method started");
        try {
            relationshipBuilderBinder.getErrorDisplay().clearError();
            finalSelectedResultsBean.removeAllItems();
            levelPanel.setVisible(false);
            treePanel.setVisible(false);
            remove.setVisible(false);
            relationshipName.setValue(ConstantsUtils.EMPTY);
            relationshipDesc.setValue(ConstantsUtils.EMPTY);
            startDate.setValue(null);
            relationshipType.select(ConstantsUtils.PRIMARY);
            buildType.select(ConstantsUtils.MANUAL);
            hierarchyNameDdlb.setValue(new HelperDTO(ConstantsUtils.SELECT_ONE));
            hierarchyNameDdlb.setEnabled(true);

            final String fromViewPage = sessionDTO.getFromViewPage();
            if (fromViewPage.equals(ConstantsUtils.EDIT)) {
                relationshipBuilderDTO = new RelationBuilderLogic().getRelationBuilderInfo(sessionDTO);
                relationshipName.setValue(relationshipBuilderDTO.getRelationshipName());
                relationshipDesc.setValue(relationshipBuilderDTO.getRelationshipDesc());
                startDate.setValue(relationshipBuilderDTO.getStartDate());
                hierarchy.setValue(relationshipBuilderDTO.getHierarchy());

                hierarchyNameDdlb = logic.loadHierarchy(hierarchyNameDdlb, sessionDTO);
                final HelperDTO helperVal = new HelperDTO();
                helperVal.setId(Integer.valueOf(relationshipBuilderDTO.getHierarchy()));
                helperVal.setDescription(relationshipBuilderDTO.getHierarchyName());
                hierarchyNameDdlb.setValue(helperVal);
                hierarchyNameDdlb.select(helperVal);
                versionNo.select(null);
                versionNo.setValue(null);

                versionNo.select(relationshipBuilderDTO.getHierarchyVersionNo());
                versionNo.setValue(relationshipBuilderDTO.getHierarchyVersionNo());

                relationshipType.select(relationshipBuilderDTO.getRelationshipType());
                buildType.select(StringUtils.isNotBlank(relationshipBuilderDTO.getBuildType()) ? relationshipBuilderDTO.getBuildType() : ConstantsUtils.MANUAL);
                sessionDTO.setSelectedHierarchySessionId(Integer.valueOf(relationshipBuilderDTO.getHierarchy()));

                levelPanel.setVisible(true);
                treePanel.setVisible(true);
                remove.setVisible(true);
                remove.setEnabled(true);
                relationshipName.setEnabled(false);
                relationshipDesc.setEnabled(false);
                startDate.setEnabled(false);
                relationshipType.setEnabled(false);
                hierarchyNameDdlb.setEnabled(true);
                versionNo.setEnabled(true);
            }
        } catch (SystemException ex) {
            final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
            LOGGER.error(ex);
        } catch (PortalException ex) {
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4006));
            LOGGER.error(ex);
        } catch (Exception ex) {
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4006));
            LOGGER.error(ex);
        }
        LOGGER.debug("resetButtonClickLogic Method started");
    }

    /**
     * This method is always called before the view is shown on screen.
     *
     * @param event the event
     */
    public void enter(final ViewChangeListener.ViewChangeEvent event) {

    }

    /**
     * Entry.
     */
    public void entry() {
        try {
            LOGGER.debug("entry Started");
            final String fromViewPage = sessionDTO.getFromViewPage();
            final int rbSystemId = sessionDTO.getSystemId();
            if (rbSystemId != ConstantsUtils.ZERO_NUM) {
                relationshipBuilderDTO = new RelationBuilderLogic().getRelationBuilderInfo(sessionDTO);
                relationshipName.setValue(relationshipBuilderDTO.getRelationshipName());
                relationshipDesc.setValue(relationshipBuilderDTO.getRelationshipDesc());
                startDate.setValue(relationshipBuilderDTO.getStartDate());
                hierarchy.setValue(relationshipBuilderDTO.getHierarchy());
                loadHierarchy();
                final HelperDTO helperVal = new HelperDTO();
                helperVal.setId(Integer.valueOf(relationshipBuilderDTO.getHierarchy()));
                helperVal.setDescription(relationshipBuilderDTO.getHierarchyName());
                hierarchyNameDdlb.setValue(helperVal);
                hierarchyNameDdlb.select(helperVal);
                versionNo.select(null);
                versionNo.setValue(null);
                versionNo.select(relationshipBuilderDTO.getHierarchyVersionNo());
                versionNo.setValue(relationshipBuilderDTO.getHierarchyVersionNo());

                relationshipType.select(relationshipBuilderDTO.getRelationshipType());
                buildType.select(StringUtils.isNotBlank(relationshipBuilderDTO.getBuildType()) ? relationshipBuilderDTO.getBuildType() : ConstantsUtils.MANUAL);
                sessionDTO.setSelectedHierarchySessionId(Integer.valueOf(relationshipBuilderDTO.getHierarchy()));
                levelPanel.setVisible(true);
                treePanel.setVisible(true);
                remove.setVisible(true);
                relationshipName.setEnabled(false);
                relationshipDesc.setEnabled(false);
                startDate.setEnabled(false);
                relationshipType.setEnabled(false);
            }
            if (fromViewPage.equals(ConstantsUtils.SMALL_YES)) {
                save.setEnabled(false);
                resetAll.setEnabled(false);
                relationshipName.setEnabled(false);
                relationshipDesc.setEnabled(false);
                startDate.setEnabled(false);
                relationshipType.setEnabled(false);
                remove.setEnabled(false);
                hierarchyNameDdlb.setEnabled(false);
                versionNo.setEnabled(false);

            } else {
                save.setEnabled(true);
                resetAll.setEnabled(true);
                hierarchyNameDdlb.setEnabled(true);
                versionNo.setEnabled(true);
                remove.setEnabled(true);
                final String fromAddPage = sessionDTO.getFromViewPage();
                if ("Add".equals(fromAddPage)) {
                    relationshipName.setValue(ConstantsUtils.EMPTY);
                    relationshipDesc.setValue(ConstantsUtils.EMPTY);
                    startDate.setValue(null);
                    hierarchy.setValue(ConstantsUtils.EMPTY);
                    hierarchyNameDdlb.setValue(new HelperDTO(ConstantsUtils.SELECT_ONE));
                    relationshipType.select(ConstantsUtils.PRIMARY);
                    levelPanel.setVisible(false);
                    treePanel.setVisible(false);
                    hierarchyNameDdlb.setEnabled(true);
                    relationshipName.setEnabled(true);
                    relationshipDesc.setEnabled(true);
                    startDate.setEnabled(true);
                    relationshipType.setEnabled(true);
                    versionNo.setEnabled(true);
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

    /**
     * Load version.
     *
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    private void loadVersion() throws SystemException, PortalException {
        final int helperId = ((HelperDTO) hierarchyNameDdlb.getValue()).getId();

        final int version = logic.getExistingHierarchyVersion(helperId);
        versionNo.removeAllItems();
        versionNo.addItem(version);

    }

    protected void enableBuildType() {
        buildType.setEnabled(true);
    }

    /**
     * validateFields Method
     */
    public void validateFields() {
        Collection<Field<?>> collection = relationshipBuilderBinder.getFields();
        CommonUtils commonUtil = CommonUtils.getInstance();
        for (Field field : collection) {
            if (field instanceof TextField) {
                TextField textField = (TextField) field;
                commonUtil.textValidation(textField, textField.getData());
            }
        }
    }

    private void getResponsiveLayout() {

        relationshipBuilderPanel.setContent(hsplit);
        hsplit.setFirstComponent(levelPanel);
        hsplit.setSecondComponent(treePanel);

    }

    /**
     * Method to Add a hierarchy No
     *
     * @param lis
     * @return Integer
     */
    private int setNewHierarchyNo(Collection<?> lis) {
        if (lis.size() > 0) {
            List<String> hierarchyNoList = new ArrayList<>();
            for (Object object : lis) {
                HierarchyLevelsDTO dto = (HierarchyLevelsDTO) object;
                hierarchyNoList.add(dto.getHierarchyNo());
            }
            List<Integer> numberList = new ArrayList<>();
            for (String string : hierarchyNoList) {
                String[] splitted = string.split("\\.");
                numberList.add(Integer.valueOf(splitted[splitted.length - 1]));
            }
            Collections.sort(numberList);
            return numberList.get(numberList.size() - 1);
        } else {
            return 0;
        }
    }
}
