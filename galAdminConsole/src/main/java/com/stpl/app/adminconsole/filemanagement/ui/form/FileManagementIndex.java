/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.filemanagement.ui.form;

import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.app.adminconsole.common.dto.SessionDTO;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;
import com.stpl.app.adminconsole.common.util.CommonUIUtil;
import com.stpl.app.adminconsole.common.util.CommonUtil;
import com.stpl.app.adminconsole.filemanagement.dto.FileManagementDTO;
import com.stpl.app.adminconsole.filemanagement.dto.FileManagementFilterGenerator;
import com.stpl.app.adminconsole.filemanagement.dto.FileMananagementResultDTO;
import com.stpl.app.adminconsole.filemanagement.logic.FileManagementLogic;
import com.stpl.app.adminconsole.filemanagement.logic.tablelogic.FileManagementTableLogic;
import com.stpl.app.adminconsole.itemgroup.util.UISecurityUtil;
import com.stpl.app.adminconsole.util.AbstractNotificationUtils;
import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.stpl.app.adminconsole.util.ErrorCodeUtil;
import com.stpl.app.adminconsole.util.ErrorCodes;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.ifs.ui.CommonSecurityLogic;
import com.stpl.ifs.ui.errorhandling.ErrorLabel;
import com.stpl.ifs.ui.util.CommonUIUtils;
import com.stpl.ifs.util.ExtCustomTableHolder;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import static com.vaadin.server.Sizeable.UNITS_PERCENTAGE;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import static com.stpl.app.adminconsole.util.ResponsiveUtils.getResponsiveControls;

/**
 * The Class FileManagementIndex.
 *
 * @author Elangovan
 */
public class FileManagementIndex extends CustomComponent implements View {

    @UiField("errorMsg")
    private ErrorLabel errorMsg;

    private final Label space = new Label("&nbsp;", ContentMode.HTML);

    @UiField("fileTypeCombo")
    private ComboBox fileType;

    @UiField("country")
    private ComboBox country;

    @UiField("currentFile")
    private TextField currentFile;

    private final TextField currentFileHidden = new TextField();

    @UiField("effectiveDateStr")
    private TextField effectiveDateStr;

    @UiField("selectFile")
    private CustomTextField selectFile;

    @UiField("saveBtn")
    private Button saveBtn;

    @UiField("resetBtn")
    private Button resetBtn;

    FileManagementTableLogic tableLogic = new FileManagementTableLogic();

    private ExtPagedTable fileHistoryTable = new ExtPagedTable(tableLogic);

    @UiField("excelExportDetail")
    private Button excelExportDetail;

    @UiField("autoUpdate")
    private Button autoUpdate;

    @UiField("processTracking")
    private Button processTracking;

    @UiField("tableLayout")
    VerticalLayout tableLayout;

    @UiField("cssLayout")
    CssLayout cssLayout;

    @UiField("selectFileCssLayout")
    CssLayout selectFileCssLayout;

    @UiField("fileTypeLb")
    Label fileTypeLb;

    @UiField("countryLb")
    Label countryLb;

    private HorizontalLayout controlLayout = new HorizontalLayout();

    FileManagementLogic logic = new FileManagementLogic();

    HelperDTO detailsFileType;

    String[] fileString = new String[2];

    private String currentFileVersion = ConstantsUtils.EMPTY;

    final Map<Integer, Boolean> reloadMap = new HashMap<Integer, Boolean>();

    private final BeanItemContainer<FileMananagementResultDTO> resultsBean = new BeanItemContainer<FileMananagementResultDTO>(FileMananagementResultDTO.class);

    private final FileMananagementResultDTO fileMgtDTO = new FileMananagementResultDTO();
    private ExtFilterTable excelTable;
    private BeanItemContainer<FileMananagementResultDTO> excelTableBean;

    CommonUtil commonUtil = new CommonUtil();

    private static final Logger LOGGER = Logger.getLogger(FileManagementIndex.class);
    CommonSecurityLogic commonSecurity = new CommonSecurityLogic();
    SessionDTO sessionDTO;

    public ErrorLabel getErrorMsg() {
        return errorMsg;
    }

    public Label getSpace() {
        return space;
    }

    public ComboBox getFileType() {
        return fileType;
    }

    public ComboBox getCountry() {
        return country;
    }

    public TextField getCurrentFile() {
        return currentFile;
    }

    public TextField getCurrentFileHidden() {
        return currentFileHidden;
    }

    public TextField getSelectFile() {
        return selectFile;
    }

    public Button getSaveBtn() {
        return saveBtn;
    }

    public Button getResetBtn() {
        return resetBtn;
    }

    public ExtFilterTable getFileHistoryTable() {
        return fileHistoryTable;
    }

    public Button getExcelExport() {
        return excelExportDetail;
    }

    public BeanItemContainer<FileMananagementResultDTO> getResultsBean() {
        return resultsBean;
    }

    public FileMananagementResultDTO getFileMgtDTO() {
        return fileMgtDTO;
    }

    public void setFileHistoryTable(final ExtPagedTable fileHistoryTable) {
        this.fileHistoryTable = fileHistoryTable;
    }

    private ErrorfulFieldGroup binder = new ErrorfulFieldGroup(new BeanItem<FileMananagementResultDTO>(fileMgtDTO));

    /**
     * Instantiates a new file management index.
     *
     * @throws Exception
     * @throws PortalException
     * @throws SystemException
     */
    public FileManagementIndex(final SessionDTO sessionDTO) {
        super();
        this.sessionDTO = sessionDTO;
        LOGGER.info("FileManagementIndex Constructor Entered ");
        try {
            init();
        } catch (PortalException ex) {
            LOGGER.error(ex);
        } catch (SystemException ex) {
            LOGGER.error(ex);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }

    }

    /**
     * Inits the.
     *
     * @throws Exception
     * @throws PortalException
     * @throws SystemException
     */
    private void init() throws PortalException, SystemException, Exception {
        LOGGER.info("init Method Started ");
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/clara/fileManagement.xml"), this));
        getBinder();
        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = String.valueOf(sessionDTO.getUserId());
        final Map<String, AppPermission> fieldItemHM;
        fieldItemHM = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.FILE_MANAGEMENT + "," + UISecurityUtil.LANDING_SCREEN, false);
        final Map<String, AppPermission> functionCompanyHM = stplSecurity.getBusinessFunctionPermission(userId, UISecurityUtil.FILE_MANAGEMENT + "," + UISecurityUtil.LANDING_SCREEN);

        getResponsiveFirstTab(fieldItemHM);
        getButtonPermission(functionCompanyHM);
        configureTable();
        configureFields();
        LOGGER.info("init Method Ended ");
    }

    private void getResponsiveFirstTab(final Map<String, AppPermission> fieldItemHM) {
        LOGGER.info("Entering getFirstTab1");
        try {
            List<Object> resultList = commonUtil.getFieldsForSecurity(UISecurityUtil.FILE_MANAGEMENT, UISecurityUtil.LANDING_SCREEN);

            commonSecurity.removeSearchComponentOnPermission(resultList, selectFileCssLayout, fieldItemHM);

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.info("Ending getFirstTab1");
    }

    /**
     * Gets the customer group binder.
     *
     * @return the customer group binder
     */
    private ErrorfulFieldGroup getBinder() {
        LOGGER.info("Entering getCustomerGroupBinder method");
        binder.bindMemberFields(this);
        binder.setItemDataSource(new BeanItem<FileMananagementResultDTO>(fileMgtDTO));
        binder.setBuffered(true);

        LOGGER.info("getCustomerGroupBinder method RETURNS customerGroupBinder ");
        return binder;
    }

    private void getButtonPermission(Map<String, AppPermission> functionCompanyHM) {

        if (functionCompanyHM.get(ConstantsUtils.PROCESS_TRACKING_BUTTON) != null && !((AppPermission) functionCompanyHM.get(ConstantsUtils.PROCESS_TRACKING_BUTTON)).isFunctionFlag()) {
            processTracking.setVisible(false);
        }
        if (functionCompanyHM.get(ConstantsUtils.SAVE_BUTTON) != null && !((AppPermission) functionCompanyHM.get(ConstantsUtils.SAVE_BUTTON)).isFunctionFlag()) {
            saveBtn.setVisible(false);
        }
        if (functionCompanyHM.get(ConstantsUtils.AUTO_UPDATE_BUTTON) != null && !((AppPermission) functionCompanyHM.get(ConstantsUtils.AUTO_UPDATE_BUTTON)).isFunctionFlag()) {
            autoUpdate.setVisible(false);
        }

        if (functionCompanyHM.get(ConstantsUtils.RESET_BUTTON) != null && !((AppPermission) functionCompanyHM.get(ConstantsUtils.RESET_BUTTON)).isFunctionFlag()) {
            resetBtn.setVisible(false);
        }

    }

    private void saveButtonLogic() {

        saveBtn.addClickListener(new Button.ClickListener() {
            /**
             * Called when a {@link Button} has been clicked. A reference to the
             * button is given by {@link ClickEvent#getButton()}.
             *
             * @param event An event containing information about the click.
             */
            public void buttonClick(final Button.ClickEvent event) {
                try {
                    LOGGER.info("In configureFields saveOnClick started");
                    saveOnClick();
                    LOGGER.info("In configureFields saveOnClick Ended");
                } catch (SystemException ex) {
                    LOGGER.error(ex);
                } catch (PortalException ex) {
                    LOGGER.error(ex);
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            }
        });

    }

    private void resetButtonLogic() {
        resetBtn.addClickListener(new Button.ClickListener() {
            /**
             * Called when a {@link Button} has been clicked. A reference to the
             * button is given by {@link ClickEvent#getButton()}.
             *
             * @param event An event containing information about the click.
             */
            public void buttonClick(final Button.ClickEvent event) {
                LOGGER.info("In configureFields resetBtn.addClickListener started");
                final AbstractNotificationUtils notificationUtils = new AbstractNotificationUtils() {
                    /**
                     * Executed by clicking yes .
                     */
                    @SuppressWarnings("PMD")
                    public void yesMethod() {
                        fileType.setValue(ConstantsUtils.SELECT_ONE);
                        country.setValue(null);
                        currentFile.setValue(ConstantsUtils.EMPTY);
                        effectiveDateStr.setValue(ConstantsUtils.EMPTY);
                        selectFile.setValue(ConstantsUtils.EMPTY);
                    }

                    /**
                     * Executed by clicking No
                     */
                    public void noMethod() {

                    }
                };
                notificationUtils.getConfirmationMessage(ConstantsUtils.CONFORMATION, "Are you sure you want to reset the page to default/previous values?");
                LOGGER.info("In configureFields resetBtn.addClickListener Ended");
            }
        });
    }

    private void excelExportBtnLogic() {
        excelExportDetail.addClickListener(new ClickListener() {

            public void buttonClick(final ClickEvent event) {
                LOGGER.info("In configureFields selectedResultsExcelExport.addClickListener started");
                try {
                    configureExcelResultTable();

                    loadExcelTable((HelperDTO) fileType.getValue(), String.valueOf(country.getValue()));
                    ExcelExport excel = new ExcelExport(new ExtCustomTableHolder(fileHistoryTable), "File Management History", "File Management History", "File Management History.xls", false);
                    excel.export();

                } catch (Exception e) {
                    LOGGER.error(e);
                }
                LOGGER.info("In configureFields selectedResultsExcelExport.addClickListener Ended");
            }
        });
    }

    private void autoUpdateBtnLogic() {

        autoUpdate.addClickListener(new Button.ClickListener() {

            public void buttonClick(final Button.ClickEvent event) {
                final Emailer email = new Emailer();

                final FileManagementDTO fileMgtDTO;
                try {
                    if (fileHistoryTable.getValue() == null) {
                        AbstractNotificationUtils.getErrorNotification("Error", "Please select a File to Auto-Update the Projection");
                    } else {

                        fileMgtDTO = logic.getCurrentFileInfo((com.stpl.ifs.util.HelperDTO) fileType.getValue());
                        String file = fileMgtDTO.getCurrentFile();
                        String version = fileMgtDTO.getForecastVersion();
                        FileMananagementResultDTO dto = (FileMananagementResultDTO) fileHistoryTable.getValue();
                        if (dto.getFile().equals(file) && dto.getVersion().equals(version)) {
                            MessageBox.showPlain(Icon.QUESTION, ConstantsUtils.CONFORMATION, "Do you want to Activate the file and automatically recalculate Forecasting Projections in Pending or Approved?", new MessageBoxListener() {

                                public void buttonClicked(ButtonId buttonId) {
                                    String msg = email.sendMail("support@bpitechnologies.com", "TestMail", "You clicked", false, StringUtils.EMPTY);
                                    if (msg.equals(ConstantsUtils.SUCCESS)) {

                                    }
                                }

                            }, ButtonId.YES, ButtonId.NO);

                        } else {
                            AbstractNotificationUtils.getErrorNotification("Error", "Please select current GTS file to auto-update the projection");
                        }
                    }

                } catch (SystemException ex) {
                    LOGGER.error(ex);
                } catch (PortalException ex) {
                    LOGGER.error(ex);
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }

            }
        });
    }

    private void processTrackingBtnLogic() {
        processTracking.addClickListener(new Button.ClickListener() {

            public void buttonClick(final Button.ClickEvent event) {
                final TrackingProgress lookUp = new TrackingProgress("Tracking Process Lookup", sessionDTO);
                lookUp.init();
                UI.getCurrent().addWindow(lookUp);
            }
        });
    }

    private void configureFields() throws SystemException, Exception {

        LOGGER.info("configureFields Method Started ");

        cssLayout.setSizeFull();

        excelExportDetail.setDescription(ConstantsUtils.EXCEL_EXPORT);
        fileType.focus();

        fileType();
        effectiveDateStr.setEnabled(true);
        effectiveDateStr.setValue(ConstantsUtils.EMPTY);
        currentFile.setEnabled(false);
        effectiveDateStr.setEnabled(false);
        country.addItem(ConstantsUtils.SELECT_ONE);
        country.addItem(ConstantsUtils.COUNTRY_US);
        country.addItem(ConstantsUtils.COUNTRY_PR);
        country.setNullSelectionAllowed(true);
        country.setNullSelectionItemId(ConstantsUtils.SELECT_ONE);
        country.select(ConstantsUtils.SELECT_ONE);
        country.setImmediate(true);

        excelExportDetail.setIcon(new ThemeResource("../../icons/excel.png"));
        excelExportDetail.setStyleName("link");
        selectFile.addStyleName("searchText-nonabsolute");
        selectFile.setWidth("250px");

        selectFile.addClickListener(new CustomTextField.ClickListener() {

            @SuppressWarnings("PMD")
            public void click(CustomTextField.ClickEvent event) {
                if (fileType.getValue() == null || fileType.getValue() == ConstantsUtils.EMPTY) {
                    AbstractNotificationUtils.getErrorNotification("Error", "Please select a File Type/Country");
                } else if (country.getValue() == null || country.getValue() == ConstantsUtils.EMPTY || country.getValue().toString().equals(ConstantsUtils.SELECT_ONE)) {
                    AbstractNotificationUtils.getErrorNotification("Error", "Please select a File Type/Country");
                } else {
                    final FileManagementLookup lookUp = new FileManagementLookup(fileMgtDTO, selectFile, fileType, String.valueOf(country.getValue()), sessionDTO,
                            ConstantsUtils.INVENTORY_WITHDRAWAL_DETAIL.equals(((HelperDTO) fileType.getValue()).getDescription()));
                    lookUp.init();

                    UI.getCurrent().addWindow(lookUp);

                    lookUp.addCloseListener(new Window.CloseListener() {

                        public void windowClose(final Window.CloseEvent e) {
                            saveBtn.focus();
                        }
                    });
                }
            }
        });

        country.addValueChangeListener(new Property.ValueChangeListener() {

            public void valueChange(final Property.ValueChangeEvent event) {
                LOGGER.info("In configureFields country.addValueChangeListener started");
                if (country.getValue() == null || StringUtils.EMPTY.equals(country.getValue())) {
                    tableLogic.clearAll();
                    tableLogic.setReset(true);
                    tableLogic.setRequiredCount(true);
                    tableLogic.setCurrentPage(1);
                    fileHistoryTable.setFilterDecorator(new ExtDemoFilterDecorator());
                    setTableDefaultConfig();
                }
                if (fileType.getValue() == null
                        || ConstantsUtils.SELECT_ONE.equals(fileType.getValue())
                        || "null".equals(fileType.getValue().toString().trim())) {
                } else {
                    tableLogic.configureSearchData(((HelperDTO) fileType.getValue()), String.valueOf(country.getValue()));
                    fileHistoryTable.setFilterDecorator(new ExtDemoFilterDecorator());
                    fileHistoryTable.setFilterGenerator(new FileManagementFilterGenerator());
                    fileHistoryTable.setImmediate(true);
                    fileHistoryTable.setWidth(99, UNITS_PERCENTAGE);
                    fileHistoryTable.addStyleName("TableCheckBox");
                    fileHistoryTable.setSelectable(true);
                    fileHistoryTable.markAsDirtyRecursive();

                }
                LOGGER.info("In configureFields country.addValueChangeListener Ended");
            }
        });
        fileType.addValueChangeListener(new Property.ValueChangeListener() {

            public void valueChange(final Property.ValueChangeEvent event) {
                LOGGER.info("In configureFields fileType.addValueChangeListener started" + fileType.getValue());
                if (fileType.getValue() == null
                        || ConstantsUtils.SELECT_ONE.equals(fileType.getValue())
                        || "null".equals(fileType.getValue().toString().trim())) {
                    resultsBean.removeAllItems();
                    currentFile.setValue(ConstantsUtils.EMPTY);
                    currentFileVersion = ConstantsUtils.EMPTY;
                    currentFileHidden.setValue(ConstantsUtils.EMPTY);
                    effectiveDateStr.setValue(ConstantsUtils.EMPTY);
                    selectFile.setValue(ConstantsUtils.EMPTY);
                } else {
                    final FileManagementLogic fileMgtLogic = new FileManagementLogic();
                    try {
                        fileString[1] = String.valueOf(country.getValue());
                        tableLogic.configureSearchData(((HelperDTO) fileType.getValue()), fileString[1]);
                        fileHistoryTable.setFilterDecorator(new ExtDemoFilterDecorator());
                        fileHistoryTable.setFilterGenerator(new FileManagementFilterGenerator());
                        fileHistoryTable.setImmediate(true);
                        fileHistoryTable.setWidth(99, UNITS_PERCENTAGE);
                        fileHistoryTable.addStyleName("TableCheckBox");
                        fileHistoryTable.setSelectable(true);
                        fileHistoryTable.markAsDirtyRecursive();

                        currentFile.setValue(ConstantsUtils.EMPTY);
                        currentFileVersion = ConstantsUtils.EMPTY;
                        currentFileHidden.setValue(ConstantsUtils.EMPTY);
                        effectiveDateStr.setValue(ConstantsUtils.EMPTY);
                        selectFile.setValue(ConstantsUtils.EMPTY);
                        final FileManagementDTO fileMgtDTO
                                = fileMgtLogic.getCurrentFileInfo(((HelperDTO) fileType.getValue()));
                        currentFile.setValue(fileMgtDTO.getCurrentFile());
                        currentFileVersion = fileMgtDTO.getForecastVersion();
                        currentFileHidden.setValue(fileMgtDTO.getCurrentFile());
                        effectiveDateStr.setValue(fileMgtDTO.getEffectiveDate());

                    } catch (SystemException e) {

                        final String errorMsg = ErrorCodeUtil.getErrorMessage(e);
                        LOGGER.error(e);
                        AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
                    } catch (PortalException e) {

                        LOGGER.error(e);
                        AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4007));
                    } catch (Exception e) {

                        LOGGER.error(e);
                        AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4007));
                    }
                }
                LOGGER.info("In configureFields fileType.addValueChangeListener Ended");
            }
        });

        LOGGER.info("configureFields Method Ended ");
        excelExportBtnLogic();
        processTrackingBtnLogic();
        saveButtonLogic();
        autoUpdateBtnLogic();
        resetButtonLogic();

    }

    private void fileType() throws Exception {
        List<HelperDTO> fileTypeList = new ArrayList<HelperDTO>();
        fileType.addItem(ConstantsUtils.SELECT_ONE);
        CommonUtil.getFileTypeComboBox(fileType, "FILE_TYPE", fileTypeList);
        fileType.setNullSelectionAllowed(true);
        fileType.setNullSelectionItemId(new HelperDTO(0, ConstantsUtils.SELECT_ONE));
        fileType.select(ConstantsUtils.SELECT_ONE);
        fileType.setImmediate(true);

    }

    /**
     * Save on click.
     *
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    protected void saveOnClick() throws SystemException, PortalException, Exception {
        LOGGER.info("saveOnClick method started");
        final FileManagementLogic fileMgtLogic = new FileManagementLogic();
        final FileManagementDTO fileMgtDTO1 = fileMgtLogic.getCurrentFileInfo((com.stpl.ifs.util.HelperDTO) fileType.getValue());
        if (ConstantsUtils.EMPTY.equals(selectFile.getValue())) {
            AbstractNotificationUtils.getErrorNotification("Save Error", "Please select a file using the Lookup icon feature");
        } else if (fileMgtDTO1.getCurrentFile().equals(selectFile.getValue()) && fileMgtDTO1.getForecastVersion().equals(fileMgtDTO.getVersion())) {
            AbstractNotificationUtils.getErrorNotification("Save Error", "The selected file is the current active file.Please select a different file");
        } else {
            MessageBox.showPlain(Icon.QUESTION, ConstantsUtils.CONFORMATION, "Save record " + selectFile.getValue() + "?", new MessageBoxListener() {

                @SuppressWarnings("PMD")
                public void buttonClicked(final ButtonId buttonId) {
                    if (buttonId.name().equals(ConstantsUtils.YES)) {
                        final FileManagementLogic fileMgtLogic = new FileManagementLogic();
                        try {
                            final String msg = fileMgtLogic.saveFileMgtHist(fileMgtDTO, (HelperDTO) fileType.getValue(), sessionDTO);
                            if (ConstantsUtils.SUCCESS.equals(msg)) {
                                CommonUIUtils.getMessageNotification("Saved succesfully");
                            } else {
                                CommonUIUtils.getMessageNotification("Save failed");
                            }
                            fileString[0] = String.valueOf(((com.stpl.ifs.util.HelperDTO) fileType.getValue()).getId());

                            fileString[1] = ConstantsUtils.EMPTY;

                            tableLogic.configureSearchData((HelperDTO) fileType.getValue(), fileString[1]);
                            fileHistoryTable.setFilterDecorator(new ExtDemoFilterDecorator());
                            fileHistoryTable.setFilterGenerator(new FileManagementFilterGenerator());
                            fileHistoryTable.setImmediate(true);
                            fileHistoryTable.setWidth(99, UNITS_PERCENTAGE);
                            fileHistoryTable.addStyleName("TableCheckBox");
                            fileHistoryTable.setSelectable(true);
                            fileHistoryTable.markAsDirtyRecursive();

                            currentFile.setValue(selectFile.getValue());
                            selectFile.setValue(StringUtils.EMPTY);
                            MessageBox.showPlain(Icon.QUESTION, ConstantsUtils.CONFORMATION, "Do you want to Activate the file and automatically recalculate Forecasting Projections in Pending or Approved?", new MessageBoxListener() {

                                @Override
                                public void buttonClicked(ButtonId buttonId) {

                                }

                            }, ButtonId.YES, ButtonId.NO);
                            Date gtsDate = new com.stpl.app.adminconsole.util.CommonUtils().getCurrentGTSToDate(((HelperDTO) fileType.getValue()).getDescription());
                            fileMgtLogic.updateAutoModeProcess(gtsDate);
                        } catch (SystemException e) {
                            final String errorMsg = ErrorCodeUtil.getErrorMessage(e);
                            LOGGER.error(e);
                            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
                        } catch (PortalException e) {
                            LOGGER.error(e);
                            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4002));
                        } catch (Exception e) {
                            LOGGER.error(e);
                            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4002));
                        }
                    }
                }
            }, ButtonId.YES, ButtonId.NO);
        }
        LOGGER.info("saveOnClick method Ended");
    }

    /**
     *
     * This method is always called before the view is shown on screen.
     *
     * @param event the event
     */
    public void enter(final ViewChangeListener.ViewChangeEvent event) {

    }

    private void configureTable() {

        tableLayout.addComponent(fileHistoryTable);
        getResponsiveControls(tableLogic.createControls(), controlLayout);
        tableLayout.addComponent(controlLayout);

        tableLogic.setContainerDataSource(resultsBean);
        tableLogic.setPageLength(100);
        tableLogic.sinkItemPerPageWithPageLength(false);
        setTableDefaultConfig();
        fileHistoryTable.setSelectable(true);
        fileHistoryTable.markAsDirty();
        fileHistoryTable.setComponentError(null);
        fileHistoryTable.setFilterBarVisible(true);
        fileHistoryTable.setFilterDecorator(new ExtDemoFilterDecorator());
        fileHistoryTable.setFilterGenerator(new FileManagementFilterGenerator());
        fileHistoryTable.setValidationVisible(false);
        fileHistoryTable.addStyleName("filterbar");

    }

    public void setTableDefaultConfig() {

        fileHistoryTable.setVisibleColumns(CommonUIUtil.FILE_MGT_HISTORY_RESULT_COLUMNS);
        fileHistoryTable.setColumnHeaders(CommonUIUtil.FILE_MGT_HISTORY_RESULT_HEADER);
        fileHistoryTable.markAsDirtyRecursive();
        fileHistoryTable.setImmediate(true);
        fileHistoryTable.setWidth(99, UNITS_PERCENTAGE);
        fileHistoryTable.setHeight("250px");

    }

    private void configureExcelResultTable() {
        excelTableBean = new BeanItemContainer<>(FileMananagementResultDTO.class);
        excelTable = new ExtFilterTable();
        tableLayout.addComponent(excelTable);
        excelTable.setVisible(false);
        excelTable.setContainerDataSource(excelTableBean);
        excelTable.setVisibleColumns(CommonUIUtil.FILE_MGT_HISTORY_RESULT_COLUMNS);
        excelTable.setColumnHeaders(CommonUIUtil.FILE_MGT_HISTORY_RESULT_HEADER);
        excelTable.markAsDirtyRecursive();

    }

    private void loadExcelTable(HelperDTO fileType, String country) throws Exception {
        excelTableBean.removeAllItems();
        if (fileHistoryTable.size() != 0) {
            int recordCount = (Integer) logic.getFileHistoryResults(fileType, country, 0, 0, null, null, true);
            List<FileMananagementResultDTO> resultList = (List<FileMananagementResultDTO>) logic.getFileHistoryResults(fileType, country, 0, recordCount, null, null, false);
            excelTableBean.addAll(resultList);
        }
    }
}
