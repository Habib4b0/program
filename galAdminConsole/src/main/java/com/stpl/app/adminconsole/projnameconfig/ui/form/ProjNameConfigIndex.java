/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.projnameconfig.ui.form;

import com.stpl.app.adminconsole.projnameconfig.dto.ProjectionNameDTO;
import com.stpl.app.adminconsole.projnameconfig.logic.ProjNameConfigLogic;
import com.stpl.app.adminconsole.projnameconfig.ui.view.ProjNameConfigAddView;
import com.stpl.app.adminconsole.util.AbstractNotificationUtils;
import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.stpl.app.adminconsole.util.ErrorCodeUtil;
import com.stpl.app.adminconsole.util.ErrorCodes;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.CustomGridLayout;
import com.stpl.ifs.ui.errorhandling.ErrorLabel;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;

import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.util.List;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.jboss.logging.Logger;

/**
 * The Class ProjNameConfigIndex.
 *
 * @author santanukumar
 */
public class ProjNameConfigIndex extends CustomComponent implements View {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(ProjNameConfigIndex.class);
    /**
     * The forecast binder.
     */
    public CustomFieldGroup nameBinder;
    /**
     * The error msg.
     */
    private final ErrorLabel errorMsg = new ErrorLabel();

    /**
     * Option Group.
     */
    private OptionGroup businessProcess = new OptionGroup();

    /**
     * The save btn.
     */
    private Button searchBtn = new Button("Search");
    /**
     * The reset btn.
     */
    private Button resetBtn = new Button("Reset");
    /**
     * The edit btn.
     */
    private Button addBtn = new Button("Add");

    /**
     * The availableResults Table.
     */
    private ExtFilterTable availableResults = new ExtFilterTable();

    /**
     * The available results bean.
     */
    public BeanItemContainer<ProjectionNameDTO> availableResultsBean = new BeanItemContainer<ProjectionNameDTO>(ProjectionNameDTO.class);

    /**
     * The projection name dto.
     */
    final ProjectionNameDTO projectionNameDTO = new ProjectionNameDTO();

    /**
     * The name config logic.
     */
    final ProjNameConfigLogic nameConfigLogic = new ProjNameConfigLogic();

    /**
     * The Bean item Null.
     */
    private static final BeanItem<?> NULL_OBJECT = null;
    /**
     * The Constant PROJECTION_NAME_TABLE_COLUMNS.
     */
    public static final Object[] PROJECTION_NAME_TABLE_COLUMNS = new Object[]{
        "businessProcessType", "selectedAttributes", "createdBy", "modifiedBy"};
    /**
     * The Constant PROJECTION_NAME_TABLE_HEADER.
     */
    public static final String[] PROJECTION_NAME_TABLE_HEADER = new String[]{
        "Business Process Type", "Selected Attributes", "Created By", "Modified By"};

    /**
     * Gets the business process.
     *
     * @return the business process
     */
    public OptionGroup getBusinessProcess() {
        return businessProcess;
    }

    /**
     * Sets the business process.
     *
     * @param businessProcess the new business process
     */
    public void setBusinessProcess(final OptionGroup businessProcess) {
        this.businessProcess = businessProcess;
    }

    /**
     * Gets the save btn.
     *
     * @return the save btn
     */
    public Button getSaveBtn() {
        return searchBtn;
    }

    /**
     * Sets the save btn.
     *
     * @param searchBtn the new save btn
     */
    public void setSaveBtn(final Button searchBtn) {
        this.searchBtn = searchBtn;
    }

    /**
     * Gets the reset btn.
     *
     * @return the reset btn
     */
    public Button getResetBtn() {
        return resetBtn;
    }

    /**
     * Sets the reset btn.
     *
     * @param resetBtn the new reset btn
     */
    public void setResetBtn(Button resetBtn) {
        this.resetBtn = resetBtn;
    }

    /**
     * Gets the logger.
     *
     * @return the logger
     */
    public static Logger getLogger() {
        return LOGGER;
    }

    /**
     * Gets the error msg.
     *
     * @return the error msg
     */
    public ErrorLabel getErrorMsg() {
        return errorMsg;
    }

    /**
     * Gets the forecast table columns.
     *
     * @return the forecast table columns
     */
    public static Object[] getForecastTableColumns() {
        return PROJECTION_NAME_TABLE_COLUMNS;
    }

    /**
     * Gets the forecast table header.
     *
     * @return the forecast table header
     */
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
    public ProjNameConfigIndex() throws SystemException, PortalException, Exception {

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
        LOGGER.info("ProjNameConfigIndex init method is started");
        setCompositionRoot(addToContent());

        LOGGER.info("ProjNameConfigIndex init method is ended");
    }

    /**
     * Gets the binder.
     *
     * @return the binder
     */
    private CustomFieldGroup getBinder() {
        LOGGER.info("getBinder method started");
        nameBinder.bindMemberFields(this);
        nameBinder.setItemDataSource(new BeanItem<ProjectionNameDTO>(projectionNameDTO));
        nameBinder.setBuffered(true);
        nameBinder.setErrorDisplay(errorMsg);
        LOGGER.info("getBinder method returns getBinder");
        return nameBinder;
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
        LOGGER.info("ProjNameConfigIndex addToContent method is started");
        final VerticalLayout content = new VerticalLayout();
        content.setSpacing(true);
        content.setMargin(true);
        Panel panel = new Panel("Projection Naming Configuration");
        VerticalLayout layout = new VerticalLayout();
        layout.setSpacing(true);
        layout.setMargin(true);
        layout.addComponent(errorMsg);
        layout.addComponent(addBusinessProcess());
        layout.addComponent(addSearchButtons());
        layout.addComponent(addAvailableResultTable());
        layout.addComponent(addNavigationButton());
        panel.setContent(layout);
        content.addComponent(panel);
        LOGGER.info("ProjNameConfigIndex addToContent method is ended");
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
    private Panel addAvailableResultTable() {
        LOGGER.info("addAvailableResultTable Method Started ");
        final Panel availablePanel = new Panel("Available Naming Configuration");
        availableResults.markAsDirty();
        availableResults.setFilterBarVisible(true);
        availableResults.setFilterDecorator(new ExtDemoFilterDecorator());
        availableResults.setContainerDataSource(availableResultsBean);
        availableResults.setCaption("Results");
        availableResults.setVisibleColumns(PROJECTION_NAME_TABLE_COLUMNS);
        availableResults.setColumnHeaders(PROJECTION_NAME_TABLE_HEADER);
        availableResults.setPageLength(5);
        availableResults.setWidth("100%");
        availableResults.setImmediate(true);

        availablePanel.setContent(availableResults);
        LOGGER.info("addAvailableResultTable Method ended ");
        return availablePanel;
    }

    /**
     * Adds the navigation button.
     *
     * @return the component
     */
    private Component addNavigationButton() {
        LOGGER.info("addNavigationButton Method started ");
        final HorizontalLayout layout = new HorizontalLayout();
        layout.addComponent(addBtn);
        addBtn.setVisible(true);
        LOGGER.info("addNavigationButton Method ended ");
        return layout;
    }

    /**
     * Adds the buttons.
     *
     * @return the component
     */
    private Component addSearchButtons() {
        LOGGER.info("addSearchButtons method Started ");
        final HorizontalLayout layout = new HorizontalLayout();
        layout.setSpacing(true);
        layout.addComponent(searchBtn);
        searchBtn.setEnabled(true);
        layout.addComponent(resetBtn);
        resetBtn.setEnabled(true);
        LOGGER.info("addSearchButtons method ended ");
        return layout;
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
        LOGGER.info("Configurefields method started ");
        businessProcess.addItem(ConstantsUtils.COMMERCIAL);
        businessProcess.addItem(ConstantsUtils.GOVERNMENT);
        businessProcess.addItem(ConstantsUtils.RETURNS);
        businessProcess.setValue(ConstantsUtils.COMMERCIAL);
        businessProcess.setImmediate(true);
        businessProcess.setRequired(true);
        businessProcess.setRequiredError("Please Select Business Process");
        businessProcess.setValidationVisible(true);
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
                                availableResultsBean.removeAllItems();

                            } catch (Exception e) {
                                LOGGER.error(e);
                                AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4002));
                            }
                        }
                    }
                }, ButtonId.YES, ButtonId.NO);

            }
        });

        searchBtn.addClickListener(new Button.ClickListener() {
            /**
             * The button click
             */
            public void buttonClick(final Button.ClickEvent event) {
                try {
                    availableResultsBean.removeAllItems();
                    final String businessProces = "<" + businessProcess.getValue().toString() + ">";
                    final List<ProjectionNameDTO> availableNameResults = nameConfigLogic.getAvailableNameTemplate(businessProces);
                    availableResultsBean.addAll(availableNameResults);
                } catch (PortalException ex) {
                    LOGGER.error(ex);
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            }

        });
        addBtn.addClickListener(new Button.ClickListener() {
            /**
             * The button click
             */
            public void buttonClick(final Button.ClickEvent event) {
                getUI().getNavigator().navigateTo(ProjNameConfigAddView.NAME);
            }

        });
        LOGGER.info("Configurefields method ended ");
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
