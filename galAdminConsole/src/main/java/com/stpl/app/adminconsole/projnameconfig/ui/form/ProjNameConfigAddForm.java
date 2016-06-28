/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.projnameconfig.ui.form;

import com.stpl.app.adminconsole.projnameconfig.dto.ProjectionNameDTO;
import com.stpl.app.adminconsole.projnameconfig.logic.ProjNameConfigLogic;
import com.stpl.app.adminconsole.projnameconfig.ui.view.ProjNameConfigMainView;
import com.stpl.app.adminconsole.util.AbstractNotificationUtils;
import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.stpl.app.adminconsole.util.ErrorCodeUtil;
import com.stpl.app.adminconsole.util.ErrorCodes;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.CustomGridLayout;
import com.stpl.ifs.ui.errorhandling.ErrorLabel;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.DataBoundTransferable;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.dd.DragAndDropEvent;
import com.vaadin.event.dd.DropHandler;
import com.vaadin.event.dd.acceptcriteria.AcceptAll;
import com.vaadin.event.dd.acceptcriteria.AcceptCriterion;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.dd.VerticalDropLocation;
import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.jboss.logging.Logger;

// TODO: Auto-generated Javadoc
/**
 * The Class ProjNameConfigAddForm.
 *
 * @author santanukumar
 */
public class ProjNameConfigAddForm extends CustomComponent implements View {

    private static final Logger LOGGER = Logger.getLogger(ProjNameConfigAddForm.class);

    public CustomFieldGroup nameBinder;

    private final ErrorLabel errorMsg = new ErrorLabel();

    private OptionGroup businessProcess = new OptionGroup();

    private Button saveBtn = new Button("Save");

    private Button resetBtn = new Button("Reset");

    private Button backBtn = new Button("Back");

    private Button remove = new Button("Remove");

    private ExtFilterTable attributesTable = new ExtFilterTable();

    private ExtFilterTable selectedResults = new ExtFilterTable();

    private final Tree nameTree = new Tree();

    private Panel treePanel = new Panel("Naming Tree");

    private Object selectedBeanId;

    private Object availableBeanId;

    private Object editBeanId;

    private ProjectionNameDTO treeBean;

    private ProjectionNameDTO editBean;

    private TextField finalName = new TextField();

    public BeanItemContainer<ProjectionNameDTO> attributesTableResultsBean = new BeanItemContainer<ProjectionNameDTO>(ProjectionNameDTO.class);

    public BeanItemContainer<ProjectionNameDTO> selectedResultsBean = new BeanItemContainer<ProjectionNameDTO>(ProjectionNameDTO.class);

    final ProjectionNameDTO projectionNameDTO = new ProjectionNameDTO();

    final ProjNameConfigLogic nameConfigLogic = new ProjNameConfigLogic();

    private static final BeanItem<?> NULL_OBJECT = null;

    public static final Object[] PROJECTION_NAME_TABLE_COLUMNS = new Object[]{
        "businessProcessType", "selectedAttributes", "versionNo", "createdBy", "modifiedBy"};

    public static final String[] PROJECTION_NAME_TABLE_HEADER = new String[]{
        "Business Process Type", "Selected Attributes", "Version No", "Created By", "Modified By"};

    public static final Object[] ATTRIBUTES_TABLE_COLUMNS = new Object[]{
        "availableAttributes"};

    public static final String[] ATTRIBUTES_TABLE_HEADER = new String[]{
        "Available Attributes"};

    private String nameTemplate;

    public CustomFieldGroup getNameBinder() {
        return nameBinder;
    }

    public void setNameBinder(final CustomFieldGroup nameBinder) {
        this.nameBinder = nameBinder;
    }

    public OptionGroup getBusinessProcess() {
        return businessProcess;
    }

    public void setBusinessProcess(final OptionGroup businessProcess) {
        this.businessProcess = businessProcess;
    }

    public Button getSaveBtn() {
        return saveBtn;
    }

    public void setSaveBtn(final Button saveBtn) {
        this.saveBtn = saveBtn;
    }

    public Button getResetBtn() {
        return resetBtn;
    }

    public void setResetBtn(Button resetBtn) {
        this.resetBtn = resetBtn;
    }

    public ExtFilterTable getAttributesTable() {
        return attributesTable;
    }

    public void setAttributesTable(final ExtFilterTable attributesTable) {
        this.attributesTable = attributesTable;
    }

    public BeanItemContainer<ProjectionNameDTO> getAttributesTableResultsBean() {
        return attributesTableResultsBean;
    }

    public void setAttributesTableResultsBean(final BeanItemContainer<ProjectionNameDTO> attributesTableResultsBean) {
        this.attributesTableResultsBean = attributesTableResultsBean;
    }

    public static Logger getLogger() {
        return LOGGER;
    }

    public ErrorLabel getErrorMsg() {
        return errorMsg;
    }

    public static Object[] getForecastTableColumns() {
        return PROJECTION_NAME_TABLE_COLUMNS;
    }

    public static String[] getForecastTableHeader() {
        return PROJECTION_NAME_TABLE_HEADER;
    }

    /**
     * Instantiates a new forecast search index.
     *
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public ProjNameConfigAddForm() throws SystemException, PortalException, Exception {

        super();

        init();
        configureFields();
    }

    /**
     * Inits the.
     *
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    private void init() throws SystemException, PortalException, Exception {
        LOGGER.info("ProjNameConfigAddForm init method is started");
        setCompositionRoot(addToContent());

        LOGGER.info("ProjNameConfigAddForm init method is ended");
    }

    /**
     * Adds the toDate content.
     *
     * @return the component
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    private VerticalLayout addToContent() throws SystemException, PortalException, Exception {
        LOGGER.info("addToContent Method Started ");
        final VerticalLayout content = new VerticalLayout();
        content.setSpacing(true);
        content.setMargin(true);
        Panel panel = new Panel("Projection Naming Configuration");
        final VerticalLayout layout = new VerticalLayout();
        layout.setSpacing(true);
        layout.setMargin(true);
        layout.addComponent(errorMsg);
        layout.addComponent(addBusinessProcess());
        layout.addComponent(addConfigurationPanel());
        layout.addComponent(addButtons());
        panel.setContent(layout);
        content.addComponent(panel);
        LOGGER.info("addToContent Method ended ");
        return content;
    }

    /**
     * Adds the business process.
     *
     * @return the panel
     */
    private Panel addBusinessProcess() {
        LOGGER.info("addBusinessProcess Method Started ");
        final CustomGridLayout gridLayout = new CustomGridLayout(2, 1);
        final Panel businessPanel = new Panel();
        gridLayout.addComponent(new Label("Business Process :"));
        gridLayout.addComponent(businessProcess);
        businessPanel.setContent(gridLayout);
        LOGGER.info("addBusinessProcess Method RETURNS panel ");

        return businessPanel;
    }

    /**
     * Adds the forecast interval.
     *
     * @return the grid layout
     */
    private Component addConfigurationPanel() {

        LOGGER.info("addConfigurationPanel Method started");
        final HorizontalLayout layout = new HorizontalLayout();
        final Panel panel = new Panel();
        layout.setMargin(true);
        layout.setSpacing(true);
        layout.addComponent(addAttributesTableAndButton());

        layout.addComponent(addSelectedTable());
        panel.setContent(layout);
        panel.setSizeFull();
        panel.setCaption("Projection Name Configuration");
        LOGGER.info("addConfigurationPanel Method returns panel");
        return panel;
    }

    /**
     * Adds the attributes table and button.
     *
     * @return the component
     */
    private Component addAttributesTableAndButton() {
        LOGGER.info("addAttributesTableAndButton Method started");
        final HorizontalLayout layout = new HorizontalLayout();
        layout.addComponent(addAttributesTable());
        layout.addComponent(addToTreeButton());
        LOGGER.info("addAttributesTableAndButton Method started");
        return layout;
    }

    /**
     * Adds the grid buttons.
     *
     * @return the component
     */
    private Component addToTreeButton() {
        final VerticalLayout content = new VerticalLayout();
        LOGGER.info("addGridButtons Method started");
        Button add;
        add = new Button(" Add ");
        add.addClickListener(new Button.ClickListener() {

            @SuppressWarnings("PMD")
            public void buttonClick(final Button.ClickEvent event) {
                LOGGER.info("In addGridButtons add.addClickListener started");
                if (attributesTable.getValue() == null) {
                    MessageBox.showPlain(Icon.INFO, "Halt", "Please select a row from Available  ", ButtonId.OK);
                } else {
                    addItemsButtonClick(event);
                }
                LOGGER.info("In addGridButtons add.addClickListener completed");
            }
        });

        content.setMargin(true);
        content.addComponent(add);
        LOGGER.info("addGridButtons Method Ended");
        return content;
    }

    /**
     * Adds the buttons.
     *
     * @return the component
     */
    private Component addButtons() {
        LOGGER.info("addButtons method Started ");
        final HorizontalLayout layout = new HorizontalLayout();
        layout.setSpacing(true);
        layout.addComponent(saveBtn);
        saveBtn.setEnabled(true);
        layout.addComponent(backBtn);
        backBtn.setEnabled(true);
        layout.addComponent(resetBtn);
        resetBtn.setEnabled(true);
        LOGGER.info("addButtons method ended ");

        return layout;
    }

    /**
     * Adds the attributesTable table.
     *
     * @return the filter table
     */
    private ExtFilterTable addAttributesTable() {
        LOGGER.info("addResultsTables method started ");

        attributesTable.markAsDirty();
        attributesTable.setFilterBarVisible(true);
        attributesTable.setFilterDecorator(new ExtDemoFilterDecorator());
        attributesTable.setContainerDataSource(attributesTableResultsBean);
        attributesTable.setCaption("Attributes");
        attributesTable.setVisibleColumns(ATTRIBUTES_TABLE_COLUMNS);
        attributesTable.setColumnHeaders(ATTRIBUTES_TABLE_HEADER);
        attributesTable.setPageLength(5);
        attributesTable.setWidth("300px");
        attributesTable.setImmediate(true);
        attributesTable.setSelectable(true);
        attributesTable.setDragMode(ExtCustomTable.TableDragMode.ROW);
        attributesTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {

            @SuppressWarnings("PMD")
            public void itemClick(final ItemClickEvent event) {
                LOGGER.info("In add form attributesTable.addItemClickListener started");
                selectedBeanId = event.getItemId();
                BeanItem<ProjectionNameDTO> targetItem = null;
                if (selectedBeanId instanceof ProjectionNameDTO) {
                    targetItem = new BeanItem<ProjectionNameDTO>((ProjectionNameDTO) selectedBeanId);
                }
                treeBean = (ProjectionNameDTO) targetItem.getBean();
                LOGGER.info("In add form attributesTable.addItemClickListener Ended");
            }
        });
        LOGGER.info("addResultsTable method RETURNS Table-results ");

        return attributesTable;
    }

    /**
     * Adds the selected table.
     *
     * @return the component
     */
    private Component addSelectedTable() {

        LOGGER.info("addSelectedTable Method started");
        final VerticalLayout content = new VerticalLayout();
        selectedResults.markAsDirty();
        selectedResults.setFilterBarVisible(true);
        selectedResults.setFilterDecorator(new ExtDemoFilterDecorator());
        selectedResults.setContainerDataSource(selectedResultsBean);

        selectedResults.setVisibleColumns(ATTRIBUTES_TABLE_COLUMNS);
        selectedResults.setColumnHeaders("Selected Items");
        selectedResults.setPageLength(5);
        selectedResults.setWidth("300px");
        selectedResults.setImmediate(true);
        selectedResults.setSelectable(true);
        selectedResults.setDragMode(ExtCustomTable.TableDragMode.ROW);
        selectedResults.addItemClickListener(new ItemClickEvent.ItemClickListener() {

            @SuppressWarnings("PMD")
            public void itemClick(final ItemClickEvent event) {
                LOGGER.info("In addHierarchyTree hierarchyTree.addItemClickListener started");
                availableBeanId = event.getItemId();
                BeanItem<ProjectionNameDTO> targetItem = null;
                if (availableBeanId instanceof ProjectionNameDTO) {
                    targetItem = new BeanItem<ProjectionNameDTO>((ProjectionNameDTO) availableBeanId);
                }
                treeBean = (ProjectionNameDTO) targetItem.getBean();
                LOGGER.info("In addHierarchyTree hierarchyTree.addItemClickListener Ended");
            }
        });
        selectedResults.setDropHandler(new DropHandler() {

            public AcceptCriterion getAcceptCriterion() {
                // Accept drops in the middle of container items
                // and below and above all items.
                return AcceptAll.get();
            }

            /**
             * This method is to called drop
             */
            public void drop(final DragAndDropEvent event) {
                // Wrapper for the object that is dragged
                final DataBoundTransferable tran = (DataBoundTransferable) event.getTransferable();

                final AbstractSelect.AbstractSelectTargetDetails target = (AbstractSelect.AbstractSelectTargetDetails) event.getTargetDetails();

                // Get ids of the dragged item and the target item
                final Object sourceItemId = tran.getItemId();
                final Object targetItemId = target.getItemIdOver();

                // On which side of the target the item was dropped
                final VerticalDropLocation location = target.getDropLocation();

                BeanItem<?> beanItem = null;
                BeanItem<?> targetItem = null;

                if (sourceItemId instanceof BeanItem<?>) {
                    beanItem = (BeanItem<?>) sourceItemId;
                } else if (sourceItemId instanceof ProjectionNameDTO) {
                    beanItem = new BeanItem<ProjectionNameDTO>((ProjectionNameDTO) sourceItemId);
                }

                if (targetItemId instanceof BeanItem<?>) {
                    targetItem = (BeanItem<?>) targetItemId;
                } else if (targetItemId instanceof ProjectionNameDTO) {
                    targetItem = new BeanItem<ProjectionNameDTO>((ProjectionNameDTO) targetItemId);
                }

                final ProjectionNameDTO bean = (ProjectionNameDTO) beanItem.getBean();

                setToTarget(bean);

            }
        });
        content.addComponent(selectedResults);
        content.addComponent(remove);
        /**
         * Remove button click listener
         */
        remove.addClickListener(new Button.ClickListener() {
            public void buttonClick(final Button.ClickEvent event) {
                LOGGER.info("In add form remove.addClickListener started");
                if (selectedResults.getItemIds().size() > ConstantsUtils.ZERO_NUM) {
                    if (treeBean == null) {
                        MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "Please select a node to remove", ButtonId.OK);
                        return;
                    } else {
                        selectedResultsBean.removeItem(treeBean);
                        if (selectedResults.getItemIds().isEmpty()) {
                            remove.setVisible(false);
                        }
                    }

                } else {
                    MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "No data to remove", ButtonId.OK);
                    return;
                }
                LOGGER.info("In add form remove.addClickListener Ended");
            }
        });
        treePanel.setContent(content);
        treePanel.setWidth("500px");
        treePanel.setCaption("Selected Naming Attributes");
        LOGGER.info("addSelectedTable Method Ended");
        return treePanel;

    }

    /**
     * (non-Javadoc).
     *
     * @param event the event
     * @see
     * com.vaadin.navigator.View#enter(com.vaadin.navigator.ViewChangeListener.ViewChangeEvent)
     */
    public void enter(final ViewChangeListener.ViewChangeEvent event) {

    }

    /**
     * Configure fields.
     */
    private void configureFields() {
        LOGGER.info("configureFields Method Ended");
        businessProcess.addItem(ConstantsUtils.COMMERCIAL);
        businessProcess.addItem(ConstantsUtils.GOVERNMENT);
        businessProcess.addItem(ConstantsUtils.RETURNS);
        businessProcess.setValue(ConstantsUtils.COMMERCIAL);
        businessProcess.setImmediate(true);
        businessProcess.setRequired(true);
        businessProcess.setRequiredError("Please Select Business Process");
        businessProcess.setValidationVisible(true);
        businessProcess.addValueChangeListener(new Property.ValueChangeListener() {

            public void valueChange(Property.ValueChangeEvent event) {
                nameTemplate = businessProcess.getValue().toString();
            }
        });
        loadBusinessProcessAttributes();

        finalName.setEnabled(false);

        resetBtn.addClickListener(new Button.ClickListener() {
            /**
             * The Button click
             */
            public void buttonClick(final Button.ClickEvent event) {

                MessageBox.showPlain(Icon.QUESTION, ConstantsUtils.CONFORMATION, "Are you sure want to reset the fields to default " + ConstantsUtils.QUESTION_MARK, new MessageBoxListener() {
                    /**
                     * Method is called when save button is clicked.
                     */
                    @SuppressWarnings("PMD")
                    public void buttonClicked(final ButtonId buttonId) {
                        if (buttonId.name().equals(ConstantsUtils.YES)) {
                            try {
                                resetBtnLogic();

                            } catch (Exception e) {
                                LOGGER.error(e);
                                AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4002));
                            }
                        }
                    }
                }, ButtonId.YES, ButtonId.NO);

            }
        });

        saveBtn.addClickListener(new Button.ClickListener() {
            /**
             * The button click
             */
            public void buttonClick(final Button.ClickEvent event) {
                try {
                    if (selectedResults.getItemIds().isEmpty()) {
                        MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "Selected Attribute table is Empty.Please Choose the attributes. ", ButtonId.OK);
                        return;
                    }

                    MessageBox.showPlain(Icon.QUESTION, "Confirmation", "Are you sure you want to save " + " ?", new MessageBoxListener() {
                        /**
                         * save button click logic
                         */
                        @SuppressWarnings("PMD")
                        public void buttonClicked(final ButtonId buttonId) {
                            if (buttonId.name().equals("YES")) {
                                try {
                                    saveButtonLogic();
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

                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            }
        });
        backBtn.addClickListener(new Button.ClickListener() {
            /**
             * The button click
             */
            public void buttonClick(final Button.ClickEvent event) {

                LOGGER.info("In configureFields backButton.addClickListener navigateTo ProjNameConfigIndex");
                getUI().getNavigator().navigateTo(ProjNameConfigMainView.NAME);
                LOGGER.info("In configureFields backButton.addClickListener Ended");

            }
        });

        LOGGER.info("configureFields Method Ended");
    }

    /**
     * Save on click.
     *
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    private void saveButtonLogic() throws PortalException, Exception {
        LOGGER.info("saveButtonLogic Method started ");
        if (!selectedResults.getItemIds().isEmpty()) {
            nameTemplate = "<" + businessProcess.getValue().toString() + ">";
            for (final Iterator<?> it = selectedResultsBean.getItemIds().iterator(); it.hasNext();) {
                final Object idValue = it.next();
                final ProjectionNameDTO availableContract = getBeanFromId(idValue);
                nameTemplate += "~" + "<" + availableContract.getAvailableAttributes() + ">";
            }
        }

        final boolean check = nameConfigLogic.duplicateCheck("<" + businessProcess.getValue().toString() + ">", nameTemplate);

        if (check) {
            String status = nameConfigLogic.saveProjNameConfigDetails(nameTemplate);
            selectedResultsBean.removeAllItems();
            if (status.equalsIgnoreCase(ConstantsUtils.SUCCESS)) {
                Notification.show(businessProcess.getValue().toString() + " business process Saved Successfully.", Notification.Type.HUMANIZED_MESSAGE);
                getUI().getNavigator().navigateTo(ProjNameConfigMainView.NAME);
            }
        } else {
            MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "The Projection Name Template is already exist for the Business Process.", ButtonId.OK);
        }
        LOGGER.info("saveButtonLogic Method ended ");

    }

    private void resetBtnLogic() {
        LOGGER.info("resetBtnLogic Method started ");
        businessProcess.setValue(ConstantsUtils.COMMERCIAL);
        selectedResults.removeAllItems();
        finalName.setValue(ConstantsUtils.EMPTY);
        LOGGER.info("resetBtnLogic Method ended ");
    }

    private void loadBusinessProcessAttributes() {
        LOGGER.info("loadBusinessProcessAttributes Method started ");
        attributesTableResultsBean.removeAllItems();
        final List<ProjectionNameDTO> availableResults = nameConfigLogic.getBusinessProcessAttributes();
        attributesTableResultsBean.addAll(availableResults);
        LOGGER.info("loadBusinessProcessAttributes Method ended ");
    }

    /**
     * Adds the items button click.
     *
     * @param event the event
     *
     */
    protected void addItemsButtonClick(final Button.ClickEvent event) {
        LOGGER.info("addItemsButtonClick Method started ");
        selectedResultsBean.addBean(treeBean);

        if (!selectedResults.getItemIds().isEmpty()) {
            nameTemplate = "<" + businessProcess.getValue().toString() + ">";
            for (final Iterator<?> it = selectedResultsBean.getItemIds().iterator(); it.hasNext();) {
                final Object idValue = it.next();
                final ProjectionNameDTO availableContract = getBeanFromId(idValue);
                nameTemplate += "~" + "<" + availableContract.getAvailableAttributes() + ">";
            }
            finalName.setValue(nameTemplate);
            remove.setVisible(true);
        }

        LOGGER.info("addItemsButtonClick Method Ended");

    }

    /**
     * Sets the tree node.
     *
     * @param beanItem the bean item
     * @param bean the bean
     * @param location the location
     * @param targetItemId the target item id
     */
    private void setToTarget(final ProjectionNameDTO bean) {

        LOGGER.info("Entering setTreeNode method");

        remove.setVisible(true);
        final String dommyId = bean.getAvailableAttributes();
        final Collection list = selectedResults.getItemIds();
        boolean flag = false;
        for (final Iterator iterator = list.iterator(); iterator.hasNext();) {
            final Object idValue = iterator.next();
            final ProjectionNameDTO availableContract = getBeanFromId(idValue);
            final String treeCaption = availableContract.getAvailableAttributes();
            if (treeCaption.equals(dommyId)) {
                flag = true;
            }
        }
        if (flag) {
            Notification.show("Selected Attribute is already exist", Notification.Type.WARNING_MESSAGE);
        } else {

            selectedResultsBean.addItem(bean);

        }

        LOGGER.info("End of setTreeNode method");
    }

    /**
     * Gets the bean from id.
     *
     * @param systemId the system id
     * @return the bean from id
     */
    public ProjectionNameDTO getBeanFromId(final Object systemId) {
        LOGGER.info("Entering getBeanFromId method");

        BeanItem<?> targetItem;
        if (systemId instanceof BeanItem<?>) {
            targetItem = (BeanItem<?>) systemId;
        } else if (systemId instanceof ProjectionNameDTO) {
            targetItem = new BeanItem<ProjectionNameDTO>((ProjectionNameDTO) systemId);
        } else {

            targetItem = NULL_OBJECT;
        }
        LOGGER.info("End of getBeanFromId method");
        return (ProjectionNameDTO) targetItem.getBean();
    }
}
/** Dont delete
 * if (editBeanId != null) {
                    BeanItem<ProjectionNameDTO> targetItem = null;
                    if (editBeanId instanceof ProjectionNameDTO) {
                        targetItem = new BeanItem<ProjectionNameDTO>((ProjectionNameDTO) editBeanId);
                    }
                    editBean = (ProjectionNameDTO) targetItem.getBean();
                    final List<ProjectionNameDTO> nameDTOs=new ArrayList<ProjectionNameDTO>();
                    final String attributesFromDb=editBean.getSelectedAttributes().replaceAll("<", "").replaceAll(">", "");
                    final String businessProcess1=editBean.getBusinessProcessType().replaceAll("<", "").replaceAll(">", "").trim();
                    final String[] attributes=attributesFromDb.split("~");
                    for (String attribute : attributes) {
                        final ProjectionNameDTO dTO=new ProjectionNameDTO();
                        dTO.setAvailableAttributes(attribute);
                        nameDTOs.add(dTO);
                    }
                  //  nameDTO.setAvailableAttributes(editBean.getSelectedAttributes().replaceAll("<", "").replaceAll(">", "").trim());
                    businessProcess.setValue(businessProcess1);
                    selectedResultsBean.addAll(nameDTOs);
                    loadAvailableNameTemplate();
                     VaadinSession.getCurrent().setAttribute("projNameConfigSid", editBean.getProjectionNameCongigSid());
                }s
 */
