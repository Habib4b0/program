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
import com.stpl.ifs.ui.util.NumericConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.ui.errorhandling.ErrorLabel;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.util.BeanItem;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.event.DataBoundTransferable;
import com.vaadin.v7.event.ItemClickEvent;
import com.vaadin.event.dd.DragAndDropEvent;
import com.vaadin.event.dd.DropHandler;
import com.vaadin.event.dd.acceptcriteria.AcceptAll;
import com.vaadin.event.dd.acceptcriteria.AcceptCriterion;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.v7.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.v7.ui.OptionGroup;
import com.vaadin.ui.Panel;
import com.vaadin.v7.ui.TextField;
import com.vaadin.v7.ui.VerticalLayout;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.asi.ui.extfilteringtable.ExtCustomTable;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO: Auto-generated Javadoc
/**
 * The Class ProjNameConfigAddForm.
 *
 * @author santanukumar
 */
public class ProjNameConfigAddForm extends CustomComponent implements View {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjNameConfigAddForm.class);

    public CustomFieldGroup nameBinder;

    private final ErrorLabel errorMsg = new ErrorLabel();

    private OptionGroup businessProcess = new OptionGroup();

    private Button saveBtn = new Button("Save");

    private Button resetBtn = new Button("Reset");

    private Button backBtn = new Button("Back");

    private Button remove = new Button("Remove");

    private ExtFilterTable attributesTable = new ExtFilterTable();

    private ExtFilterTable selectedResults = new ExtFilterTable();

    private Panel treePanel = new Panel("Naming Tree");

    private Object selectedBeanId;

    private Object availableBeanId;

    private ProjectionNameDTO treeBean;

    private TextField finalName = new TextField();

    public BeanItemContainer<ProjectionNameDTO> attributesTableResultsBean = new BeanItemContainer<>(ProjectionNameDTO.class);

    public BeanItemContainer<ProjectionNameDTO> selectedResultsBean = new BeanItemContainer<>(ProjectionNameDTO.class);

    final ProjectionNameDTO projectionNameDTO = new ProjectionNameDTO();

    final ProjNameConfigLogic nameConfigLogic = new ProjNameConfigLogic();

    private static final BeanItem<?> NULL_OBJECT = null;

    public final Object[] projectionNameTableColumns = new Object[]{
        "businessProcessType", "selectedAttributes", "versionNo", "createdBy", "modifiedBy"};

    public final String[] projectionNameTableHeader = new String[]{
        "Business Process Type", "Selected Attributes", "Version No", "Created By", "Modified By"};

    public final Object[] attributesTableColumns = new Object[]{
        "availableAttributes"};

    public final String[] attributesTableHeader = new String[]{
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

    public Object[] getForecastTableColumns() {
        return projectionNameTableColumns;
    }

    public String[] getForecastTableHeader() {
        return projectionNameTableHeader;
    }

    /**
     * Instantiates a new forecast search index.
     *
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public ProjNameConfigAddForm() throws SystemException, PortalException {

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
    private void init() {
        LOGGER.debug("ProjNameConfigAddForm init method is started");
        setCompositionRoot(addToContent());

        LOGGER.debug("ProjNameConfigAddForm init method is ended");
    }

    /**
     * Adds the toDate content.
     *
     * @return the component
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    private VerticalLayout addToContent() {
        LOGGER.debug("addToContent Method Started ");
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
        LOGGER.debug("addToContent Method ended ");
        return content;
    }

    /**
     * Adds the business process.
     *
     * @return the panel
     */
    private Panel addBusinessProcess() {
        LOGGER.debug("addBusinessProcess Method Started ");
        final CustomGridLayout gridLayout = new CustomGridLayout(NumericConstants.TWO, 1);
        final Panel businessPanel = new Panel();
        gridLayout.addComponent(new Label("Business Process :"));
        gridLayout.addComponent(businessProcess);
        businessPanel.setContent(gridLayout);
        LOGGER.debug("addBusinessProcess Method RETURNS panel ");

        return businessPanel;
    }

    /**
     * Adds the forecast interval.
     *
     * @return the grid layout
     */
    private Component addConfigurationPanel() {

        LOGGER.debug("addConfigurationPanel Method started");
        final HorizontalLayout layout = new HorizontalLayout();
        final Panel panel = new Panel();
        layout.setMargin(true);
        layout.setSpacing(true);
        layout.addComponent(addAttributesTableAndButton());

        layout.addComponent(addSelectedTable());
        panel.setContent(layout);
        panel.setSizeFull();
        panel.setCaption("Projection Name Configuration");
        LOGGER.debug("addConfigurationPanel Method returns panel");
        return panel;
    }

    /**
     * Adds the attributes table and button.
     *
     * @return the component
     */
    private Component addAttributesTableAndButton() {
        LOGGER.debug("addAttributesTableAndButton Method started");
        final HorizontalLayout layout = new HorizontalLayout();
        layout.addComponent(addAttributesTable());
        layout.addComponent(addToTreeButton());
        LOGGER.debug("addAttributesTableAndButton Method started");
        return layout;
    }

    /**
     * Adds the grid buttons.
     *
     * @return the component
     */
    private Component addToTreeButton() {
        final VerticalLayout content = new VerticalLayout();
        LOGGER.debug("addGridButtons Method started");
        Button add;
        add = new Button(" Add ");
        add.addClickListener(new Button.ClickListener() {

            @SuppressWarnings("PMD")
            public void buttonClick(final Button.ClickEvent event) {
                LOGGER.debug("In addGridButtons add.addClickListener started");
                if (attributesTable.getValue() == null) {
                    MessageBox.showPlain(Icon.INFO, "Halt", "Please select a row from Available  ", ButtonId.OK);
                } else {
                    addItemsButtonClick();
                }
                LOGGER.debug("In addGridButtons add.addClickListener completed");
            }
        });

        content.setMargin(true);
        content.addComponent(add);
        LOGGER.debug("addGridButtons Method Ended");
        return content;
    }

    /**
     * Adds the buttons.
     *
     * @return the component
     */
    private Component addButtons() {
        LOGGER.debug("addButtons method Started ");
        final HorizontalLayout layout = new HorizontalLayout();
        layout.setSpacing(true);
        layout.addComponent(saveBtn);
        saveBtn.setEnabled(true);
        layout.addComponent(backBtn);
        backBtn.setEnabled(true);
        layout.addComponent(resetBtn);
        resetBtn.setEnabled(true);
        LOGGER.debug("addButtons method ended ");

        return layout;
    }

    /**
     * Adds the attributesTable table.
     *
     * @return the filter table
     */
    private ExtFilterTable addAttributesTable() {
        LOGGER.debug("addResultsTables method started ");

        attributesTable.markAsDirty();
        attributesTable.setFilterBarVisible(true);
        attributesTable.setFilterDecorator(new ExtDemoFilterDecorator());
        attributesTable.setContainerDataSource(attributesTableResultsBean);
        attributesTable.setCaption("Attributes");
        attributesTable.setVisibleColumns(attributesTableColumns);
        attributesTable.setColumnHeaders(attributesTableHeader);
        attributesTable.setPageLength(NumericConstants.FIVE);
        attributesTable.setWidth("300px");
        attributesTable.setImmediate(true);
        attributesTable.setSelectable(true);
        attributesTable.setDragMode(ExtCustomTable.TableDragMode.ROW);
        attributesTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {

            @SuppressWarnings("PMD")
            public void itemClick(final ItemClickEvent event) {
                LOGGER.debug("In add form attributesTable.addItemClickListener started");
                selectedBeanId = event.getItemId();
                BeanItem<ProjectionNameDTO> targetItem = null;
                if (selectedBeanId instanceof ProjectionNameDTO) {
                    targetItem = new BeanItem<>((ProjectionNameDTO) selectedBeanId);
                }
                treeBean = (ProjectionNameDTO) targetItem.getBean();
                LOGGER.debug("In add form attributesTable.addItemClickListener Ended");
            }
        });
        LOGGER.debug("addResultsTable method RETURNS Table-results ");

        return attributesTable;
    }

    /**
     * Adds the selected table.
     *
     * @return the component
     */
    private Component addSelectedTable() {

        LOGGER.debug("addSelectedTable Method started");
        final VerticalLayout content = new VerticalLayout();
        selectedResults.markAsDirty();
        selectedResults.setFilterBarVisible(true);
        selectedResults.setFilterDecorator(new ExtDemoFilterDecorator());
        selectedResults.setContainerDataSource(selectedResultsBean);

        selectedResults.setVisibleColumns(attributesTableColumns);
        selectedResults.setColumnHeaders("Selected Items");
        selectedResults.setPageLength(NumericConstants.FIVE);
        selectedResults.setWidth("300px");
        selectedResults.setImmediate(true);
        selectedResults.setSelectable(true);
        selectedResults.setDragMode(ExtCustomTable.TableDragMode.ROW);
        selectedResults.addItemClickListener(new ItemClickEvent.ItemClickListener() {

            @SuppressWarnings("PMD")
            public void itemClick(final ItemClickEvent event) {
                LOGGER.debug("In addHierarchyTree hierarchyTree.addItemClickListener started");
                availableBeanId = event.getItemId();
                BeanItem<ProjectionNameDTO> targetItem = null;
                if (availableBeanId instanceof ProjectionNameDTO) {
                    targetItem = new BeanItem<>((ProjectionNameDTO) availableBeanId);
                }
                treeBean = (ProjectionNameDTO) targetItem.getBean();
                LOGGER.debug("In addHierarchyTree hierarchyTree.addItemClickListener Ended");
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


                // Get ids of the dragged item and the target item
                final Object sourceItemId = tran.getItemId();

                // On which side of the target the item was dropped

                BeanItem<?> beanItem = null;

                if (sourceItemId instanceof BeanItem<?>) {
                    beanItem = (BeanItem<?>) sourceItemId;
                } else if (sourceItemId instanceof ProjectionNameDTO) {
                    beanItem = new BeanItem<>((ProjectionNameDTO) sourceItemId);
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
                LOGGER.debug("In add form remove.addClickListener started");
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
                LOGGER.debug("In add form remove.addClickListener Ended");
            }
        });
        treePanel.setContent(content);
        treePanel.setWidth("500px");
        treePanel.setCaption("Selected Naming Attributes");
        LOGGER.debug("addSelectedTable Method Ended");
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
        return;
    }

    /**
     * Configure fields.
     */
    private void configureFields() {
        LOGGER.debug("configureFields Method Ended");
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
                                LOGGER.error(e.getMessage());
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
                                   LOGGER.error(ex.getMessage());
                                } 

                            }
                        }
                    }, ButtonId.YES, ButtonId.NO);

                } catch (Exception ex) {
                   LOGGER.error(ex.getMessage());
                }
            }
        });
        backBtn.addClickListener(new Button.ClickListener() {
            /**
             * The button click
             */
            public void buttonClick(final Button.ClickEvent event) {

                LOGGER.debug("In configureFields backButton.addClickListener navigateTo ProjNameConfigIndex");
                getUI().getNavigator().navigateTo(ProjNameConfigMainView.NAME);
                LOGGER.debug("In configureFields backButton.addClickListener Ended");

            }
        });

        LOGGER.debug("configureFields Method Ended");
    }

    /**
     * Save on click.
     *
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    private void saveButtonLogic() throws SystemException {
        LOGGER.debug("saveButtonLogic Method started ");
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
        LOGGER.debug("saveButtonLogic Method ended ");

    }

    private void resetBtnLogic() {
        LOGGER.debug("resetBtnLogic Method started ");
        businessProcess.setValue(ConstantsUtils.COMMERCIAL);
        selectedResults.removeAllItems();
        finalName.setValue(ConstantsUtils.EMPTY);
        LOGGER.debug("resetBtnLogic Method ended ");
    }

    private void loadBusinessProcessAttributes() {
        LOGGER.debug("loadBusinessProcessAttributes Method started ");
        attributesTableResultsBean.removeAllItems();
        final List<ProjectionNameDTO> availableResults = nameConfigLogic.getBusinessProcessAttributes();
        attributesTableResultsBean.addAll(availableResults);
        LOGGER.debug("loadBusinessProcessAttributes Method ended ");
    }

    /**
     * Adds the items button click.
     *
     * @param event the event
     *
     */
    protected void addItemsButtonClick() {
        LOGGER.debug("addItemsButtonClick Method started ");
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

        LOGGER.debug("addItemsButtonClick Method Ended");

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

        LOGGER.debug("Entering setTreeNode method");

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

        LOGGER.debug("End of setTreeNode method");
    }

    /**
     * Gets the bean from id.
     *
     * @param systemId the system id
     * @return the bean from id
     */
    public ProjectionNameDTO getBeanFromId(final Object systemId) {
        LOGGER.debug("Entering getBeanFromId method");

        BeanItem<?> targetItem;
        if (systemId instanceof BeanItem<?>) {
            targetItem = (BeanItem<?>) systemId;
        } else if (systemId instanceof ProjectionNameDTO) {
            targetItem = new BeanItem<>((ProjectionNameDTO) systemId);
        } else {

            targetItem = NULL_OBJECT;
        }
        LOGGER.debug("End of getBeanFromId method");
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
