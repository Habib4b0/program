/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.ui.form;

import com.stpl.app.accountclose.common.CommonLogic;
import com.stpl.app.accountclose.common.CommonUtil;
import com.stpl.app.accountclose.dto.AttachmentDTO;
import com.stpl.app.accountclose.dto.FixedDollarDTO;
import com.stpl.app.accountclose.logic.AdditionalInfoLogic;
import com.stpl.app.accountclose.logic.FixedDollarDataSelectionLogic;
import com.stpl.app.accountclose.sessionutils.SessionDTO;
import com.stpl.app.accountclose.utils.AbstractNotificationUtils;
import com.stpl.app.accountclose.utils.Constants;
import com.stpl.app.accountclose.utils.ErrorCodeUtil;
import com.stpl.app.accountclose.utils.ErrorCodes;
import com.stpl.app.accountclose.utils.FileUploader;
import com.stpl.app.accountclose.utils.HeaderUtils;
import com.stpl.ifs.ui.util.CommonUIUtils;
import com.stpl.ifs.util.ExportPdf;
import com.stpl.ifs.util.ExportWord;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.model.User;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.event.FieldEvents;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.LayoutEvents;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FileDownloader;
import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.JavaScript;
import com.vaadin.ui.JavaScriptFunction;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Upload;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.Reindeer;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author alok.v
 */
public class FixedDollarAdditionalInformation extends CustomComponent implements View {

    public static final Logger LOGGER = Logger.getLogger(FixedDollarAdditionalInformation.class);
    SessionDTO session;
    private FixedDollarDTO fixedDollarDTO;
    /**
     * The new notes.
     */
    @UiField("newNotes")
    public TextArea newNotes;
    /**
     * The excelBtn btn.
     */
    @UiField("wordPngImage")
    public Button wordPngImage;
    private boolean closeFlag;
    /**
     * The excel export image.
     */
    public User user;
    private final Resource wordImage = new ThemeResource("../../icons/word.png");
    private final static String FILENAME = "Notes_History_FDA";
    /**
     * The graph image.
     */
    private final Resource pdfImage = new ThemeResource("../../icons/pdf.png");
    public String sessionId = (String) VaadinSession.getCurrent().getAttribute(Constants.SESSION_ID);
    /**
     * The pdf btn.
     */
    @UiField("pdfPngImage")
    public Button pdfPngImage;
    private TextField uploader = new TextField();
    private AdditionalInfoLogic commonLogic = new AdditionalInfoLogic();
    CommonLogic logic = new CommonLogic();
    /**
     * The layout.
     */
    @UiField("layout")
    VerticalLayout layout;
    /**
     * The results table.
     */
    @UiField("table")
    public Table table;
    @UiField("removeBtn")
    private Button removeBtn;
    @UiField("addNotesBtn")
    private Button addNotesBtn;
    /**
     * The notes history.
     */
    @UiField("notesHistory")
    private TextArea notesHistory;
    public static final String alphaNumericChars = "([0-9|a-z|A-Z|\\ |\\*])*";
    @UiField("reasonCode")
    public ComboBox reasonCode;
    public Date date = new Date();
    private Resource wordPngResource = new ThemeResource("../../icons/word.png");
    private Resource pdfPngResource = new ThemeResource("../../icons/pdf.png");
    public SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSS");
    private String basepath = (VaadinService.getCurrent().getBaseDirectory().getAbsolutePath() == null ? StringUtils.EMPTY : VaadinService.getCurrent().getBaseDirectory().getAbsolutePath());
    public String dateValue = format.format(date);
    private String userName = StringUtils.EMPTY;
    /**
     * /**
     * The attachments list bean.
     */
    private BeanItemContainer<AttachmentDTO> attachmentsListBean = new BeanItemContainer<AttachmentDTO>(AttachmentDTO.class);
    /**
     * The file downloader.
     */
    private FileDownloader fileDownloader = new FileDownloader(new FileResource(new File("tst")));
    /**
     * The move back.
     */
    private final String moveBack = "../";
    /**
     * The user id.
     */
    final String userId = (String) VaadinSession.getCurrent().getAttribute(Constants.USER_ID);
    private final static String MODULE_NAME = "FixedDollarAdjustment";
    /**
     * The file size.
     */
    private Double fileSize = 0.00;
    /**
     * The file path.
     */
    final File filePath = new File(basepath + File.separator + moveBack + moveBack + moveBack + File.separator + "Documents" + File.separator + MODULE_NAME + File.separator + userId);
    /**
     * The upload receiver.
     */
    private FileUploader uploadReceiver = new FileUploader(basepath, MODULE_NAME);
    /**
     * The add attachment.
     */
    private Upload addAttachment = new Upload(null, uploadReceiver);
    /**
     * The notes history.
     */
    private static final String NOTES_HISTORY = "Notes History";
    /**
     * The file name.
     */

    /**
     * The word file.
     */
    private File wordFile = new File(filePath + File.separator + FILENAME + ExportWord.DOC_EXT);
    /**
     * The pdf file.
     */
    private File pdfFile = new File(filePath + File.separator + FILENAME + ExportPdf.PDF_EXT);
    /**
     * The word downloader.
     */
    private Object tableBeanId;
    private FileDownloader wordDownloader;
    private AttachmentDTO tableBean;
    private File logo = new File(basepath + "/WEB-INF/images/company_logo.png");
    /**
     * The pdf downloader.
     */
    private FileDownloader pdfDownloader;
    public List<AttachmentDTO> removedDetailsList = new ArrayList<AttachmentDTO>();
    FixedDollarDataSelectionLogic FixedDollarDataSelectionLogic = new FixedDollarDataSelectionLogic();
    @UiField("layout")
    private VerticalLayout attachmentLayout;
    CommonUtil commonUtil = CommonUtil.getInstance();

    /**
     *
     * @param session
     * @param baseRateDTO
     */
    public FixedDollarAdditionalInformation(final SessionDTO session, final FixedDollarDTO fixedDollarDTO) {
        try {
            this.session = session;
            this.fixedDollarDTO = fixedDollarDTO;
            setCompositionRoot(Clara.create(getClass().getResourceAsStream("/fixedDollarAdditionalInformation.xml"), this));
            layout.addComponent(uploader);
            layout.addComponent(addAttachment);
            createExportDocs();
            configureFields();
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    public Object getTableBeanId() {
        return tableBeanId;
    }

    /**
     * Sets the table bean id.
     *
     * @param tableBeanId the tableBeanId to set
     */
    public void setTableBeanId(final Object tableBeanId) {
        this.tableBeanId = tableBeanId;
    }

    public AttachmentDTO getTableBean() {
        return tableBean;
    }

    /**
     * Sets the table bean.
     *
     * @param tableBean the tableBean to set
     */
    public void setTableBean(final AttachmentDTO tableBean) {
        this.tableBean = tableBean;
    }

    public boolean isCloseFlag() {
        return closeFlag;
    }

    /**
     * Sets the close flag.
     *
     * @param closeFlag the closeFlag to set
     */
    public void setCloseFlag(final boolean closeFlag) {
        this.closeFlag = closeFlag;
    }

    public String getUserName() {
        return userName;
    }

    /**
     * Sets the user name.
     *
     * @param userName the userName to set
     */
    public void setUserName(final String userName) {
        this.userName = userName;
    }

    private void configureFields() {
        try {
            LOGGER.info("Indide AdditionalInformation configure Fields methods");
            table.markAsDirty();
            table.setImmediate(true);
            table.setPageLength(3);
            table.setContainerDataSource(attachmentsListBean);
            table.setVisibleColumns(Constants.ATTACHMENT_COLUMNS);
            table.setColumnHeaders(Constants.ATTACHMENT_HEADER);
            table.setSelectable(true);
            reasonCode.setNullSelectionAllowed(true);
            reasonCode.setNullSelectionItemId("-Select One-");
            reasonCode.setNullSelectionItemId(Constants.ZEROSTRING);
            FixedDollarDataSelectionLogic.loadComboBox(reasonCode, FixedDollarDataSelectionLogic.getDropDownList("REASON_CODES"));
            reasonCode.select(Constants.ZEROSTRING);
            reasonCode.setImmediate(true);
            table.addItemClickListener(new ItemClickEvent.ItemClickListener() {
                /**
                 * Will execute,when we click an item in the table.
                 */
                public void itemClick(final ItemClickEvent event) {
                    LOGGER.info("Indide AdditionalInformation  add Table itemClick method");

                    final String moveBack = "../";
                    tableBeanId = event.getItemId();
                    final int projectionId = 0;
                    BeanItem<?> targetItem = null;
                    if (tableBeanId instanceof BeanItem<?>) {
                        targetItem = (BeanItem<?>) tableBeanId;
                    } else if (tableBeanId instanceof AttachmentDTO) {
                        targetItem = new BeanItem<AttachmentDTO>((AttachmentDTO) tableBeanId);
                    }
                    tableBean = (AttachmentDTO) targetItem.getBean();
                    StringBuilder sb = new StringBuilder(tableBean.getDocumentName());
                    sb.insert(tableBean.getDocumentName().lastIndexOf("."), "_" + String.valueOf("0"));
                    final File uploadedFile = new File("../../../../var/Attachments/" + File.separator + "FixedDollarAdjustment" + File.separator
                            + userId + File.separator + sb.toString());

                    final Resource res = new FileResource(uploadedFile);
                    fileDownloader.setFileDownloadResource(res);

                    if (event.isDoubleClick()) {
                        downloadFile(uploadedFile);

                    }

                    LOGGER.info("Ends AdditionalInformation  add Table itemClick method");
                }
            });
            wordPngImage.setDescription(Constants.WORD_EXPORT);
            wordPngImage.setIconAlternateText(Constants.WORD_EXPORT);
            pdfPngImage.setDescription(Constants.PDF_EXPORT);
            pdfPngImage.setIconAlternateText(Constants.PDF_EXPORT);
            uploader.setStyleName("searchText");
            attachmentLayout.setStyleName("uploadId");
            table.addStyleName(Constants.TABLE_HEADER_STYLE);
            wordPngImage.addStyleName(Reindeer.BUTTON_LINK);
            wordPngImage.setIcon(wordPngResource);
            pdfPngImage.addStyleName(Reindeer.BUTTON_LINK);
            pdfPngImage.setIcon(pdfPngResource);
            JavaScript.getCurrent().addFunction("callJava", new JavaScriptFunction() {
                @Override
                /**
                 * Call
                 */
                public void call(final org.json.JSONArray arguments) throws org.json.JSONException {
                    File fileUpload;
                    final String value = String.valueOf(arguments.get(0));
                    fileUpload = new File(value);
                    final String name = fileUpload.getAbsolutePath();
                    if (name.contains("\\")) {
                        final String replace = name.replace("\\", ",");
                        final String[] array = replace.split(",");
                        final String filename = array[array.length - 1];
                        uploader.setValue(filename);
                    } else if (name.contains("/")) {
                        final String replace = name.replace("/", ",");
                        final String[] array = replace.split(",");
                        final String filename = array[array.length - 1];
                        uploader.setValue(filename);
                    } else {
                        uploader.setValue(name);
                    }
                    uploader.focus();
                }
            });
            JavaScript.getCurrent().addFunction("buttonClicked", new JavaScriptFunction() {
                @Override
                /**
                 * Used to call the JSONArray
                 */
                public void call(final org.json.JSONArray arguments) throws org.json.JSONException {
                    final MessageBox msgBox = AbstractNotificationUtils.getErrorNotification(CommonUtil.getMessage("FDA_MSG_1021"), CommonUtil.getMessage("FDA_MSG_1022"));
                    msgBox.getWindow().addCloseListener(new Window.CloseListener() {
                        /**
                         * Will execute,when we close the window.
                         */
                        @Override
                        public void windowClose(final Window.CloseEvent e) {
                            table.focus();
                        }
                    });
                }
            });

            attachmentLayout.addLayoutClickListener(new LayoutEvents.LayoutClickListener() {
                /**
                 * Will execute,when we click attachmentLayout.
                 */
                @Override
                public void layoutClick(final LayoutEvents.LayoutClickEvent event) {

                    callJavaScript();
                }
            });
            uploader.addFocusListener(new FieldEvents.FocusListener() {
                /**
                 * Will execute,when we click an uploader.
                 */
                @Override
                public void focus(final FieldEvents.FocusEvent event) {
                    addAttachment.focus();
                }
            });
            addAttachment.addChangeListener(new Upload.ChangeListener() {
                @Override
                public void filenameChanged(final Upload.ChangeEvent event) {
                    File fileUpload;
                    final String value = event.getFilename();
                    fileUpload = new File(value);
                    final String name = fileUpload.getAbsolutePath();
                    final String sessionreplace = sessionId.replace(".", ",");
                    final String[] sessionArray = sessionreplace.split(",");
                    final String sessionValue = sessionArray[0].substring(0, 15);
                    if (name.contains("\\")) {
                        final String replace = name.replace("\\", ",");
                        final String[] array = replace.split(",");
                        final String filename = array[array.length - 1];
                        final String replacefile = filename.replace(".", ",");
                        final String[] arrayvalue = replacefile.split(",");
                        final String splittedfilename = arrayvalue[0].concat(sessionValue).concat(dateValue);
                        final String finalFileName = splittedfilename.concat(".").concat(arrayvalue[1]);
                        uploader.setValue(finalFileName);
                    } else if (name.contains("/")) {
                        final String replace = name.replace("/", ",");
                        final String[] array = replace.split(",");
                        final String filename = array[array.length - 1];
                        final String replacefile = filename.replace(".", ",");
                        final String[] arrayvalue = replacefile.split(",");
                        final String splittedfilename = arrayvalue[0].concat(sessionValue).concat(dateValue);
                        final String finalFileName = splittedfilename.concat(".").concat(arrayvalue[1]);
                        uploader.setValue(finalFileName);
                    } else {
                        final String replacefile = name.replace(".", ",");
                        final String[] arrayvalue = replacefile.split(",");
                        final String splittedfilename = arrayvalue[0].concat(sessionValue).concat(dateValue);
                        final String finalFileName = splittedfilename.concat(".").concat(arrayvalue[1]);
                        uploader.setValue(name.concat(finalFileName));
                    }
                    uploader.focus();
                }
            });
            newNotes.setImmediate(true);
            newNotes.setMaxLength(200);
            newNotes.addValidator(new StringLengthValidator(" New Note Should be less than 200 characters", 0, 200, true));
            notesHistory.markAsDirty();
            notesHistory.setImmediate(true);
            notesHistory.setEnabled(false);
            addAttachment.setButtonCaption(Constants.ADD);
            addAttachment.addStartedListener(new Upload.StartedListener() {
                @Override
                public void uploadStarted(final Upload.StartedEvent event) {
                    try {
                        final Window.CloseListener windowListener = new Window.CloseListener() {
                            /**
                             * Will execute,when we close the window.
                             */
                            @Override
                            public void windowClose(final Window.CloseEvent event) {
                                addAttachment.focus();
                            }
                        };
                        String filename = event.getFilename().toLowerCase();
                        if (StringUtils.isBlank(event.getFilename())) {
                            addAttachment.interruptUpload();
                            AbstractNotificationUtils.getErrorNotification(CommonUtil.getMessage("FDA_MSG_1023"), commonUtil.getMessage("FDA_MSG_1024"));
                        } else {
                            if (!(filename != null && (filename.endsWith(".doc") || filename.endsWith(".docx") || filename.endsWith(".ppt") || filename.endsWith(".xls") || filename.endsWith(".xlsx") || filename.endsWith(".pdf") || filename.endsWith(".txt") || filename.endsWith(".csv") || filename.endsWith(".jpeg")))) {
                                addAttachment.interruptUpload();
                                AbstractNotificationUtils.getErrorNotification(CommonUtil.getMessage("FDA_MSG_1023"), CommonUtil.getMessage("FDA_MSG_1025"));
                                uploader.setValue(StringUtils.EMPTY);
                            } else if (fileExists(event.getFilename())) {
                                addAttachment.interruptUpload();
                                AbstractNotificationUtils.getWarningNotification(CommonUtil.getMessage("FDA_MSG_1026"), CommonUtil.getMessage("FDA_MSG_1027"));
                                uploader.setValue(StringUtils.EMPTY);
                            } else {
                                uploader.setValue(StringUtils.EMPTY);
                            }
                        }
                    } catch (Exception e) {
                        LOGGER.error(e);
                    }
                }
            });
            addAttachment.addSucceededListener(new Upload.SucceededListener() {
                @Override
                public void uploadSucceeded(final Upload.SucceededEvent event) {

                    final int projectionId = 0;
                    closeFlag = true;

                    try {
                        user = logic.getUserById(userId);
                        userName = user.getLastName() + "," + user.getFirstName();
                        final String filename = event.getFilename();
                        StringBuilder sb = new StringBuilder(event.getFilename());
                        sb.insert(event.getFilename().lastIndexOf("."), "_" + String.valueOf("0"));
                        if (commonLogic.saveUploadedFile(projectionId, sb.toString(), userName, fileSize, "FixedDollarAdjustment")) {
                            CommonUIUtils.getMessageNotification(event.getFilename() + " uploaded successfully");
                            attachmentsListBean.removeAllItems();
                            final List<AttachmentDTO> allFiles = commonLogic.getAttachmentDTOList(projectionId, "FixedDollarAdjustment");
                            uploader.setValue(StringUtils.EMPTY);
                            attachmentsListBean.addAll(allFiles);
                        }
                        final File file = new File("../../../../var/Attachments/" + File.separator + "FixedDollarAdjustment" + File.separator + userId + File.separator + event.getFilename());
                        final File newFile = new File("../../../../var/Attachments/" + File.separator + "FixedDollarAdjustment" + File.separator + userId + File.separator + sb.toString());
                        file.renameTo(newFile);
                    } catch (SystemException e) {
                        final String errorMsg = ErrorCodeUtil.getErrorMessage(e);
                        LOGGER.error(errorMsg);
                        AbstractNotificationUtils.getErrorNotification(CommonUtil.getMessage("FDA_MSG_1001"), errorMsg);
                    } catch (PortalException e) {
                        LOGGER.error(e.getMessage());
                        AbstractNotificationUtils.getErrorNotification(CommonUtil.getMessage("FDA_MSG_1001"), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_5019));
                    } catch (Exception e) {
                        LOGGER.error(e.getMessage());
                        AbstractNotificationUtils.getErrorNotification(CommonUtil.getMessage("FDA_MSG_1001"), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_5019));
                    }
                }
            });
            addAttachment.addProgressListener(new Upload.ProgressListener() {
                /**
                 * Will execute,when we add an attachment.
                 */
                @Override
                public void updateProgress(final long readBytes, final long contentLength) {
                    fileSize = Double.valueOf(contentLength) / 1024;
                }
            });

            newNotes.setInputPrompt("- Enter New Note -");
            final String userId = (String) VaadinSession.getCurrent().getAttribute(Constants.USER_ID);
            final User user = logic.getUserById(userId);
            userName = user.getFullName();
            userName = user.getLastName() + "," + user.getFirstName();
            LOGGER.info("End of AdditionalInformation configure fields");
        } catch (SystemException e) {
            final String errorMsg = ErrorCodeUtil.getErrorMessage(e);
            LOGGER.error(errorMsg);
            AbstractNotificationUtils.getErrorNotification(CommonUtil.getMessage("FDA_MSG_1001"), errorMsg);
        } catch (PortalException e) {
            LOGGER.error(e.getMessage());
            AbstractNotificationUtils.getErrorNotification(CommonUtil.getMessage("FDA_MSG_1001"), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_5019));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            AbstractNotificationUtils.getErrorNotification(CommonUtil.getMessage("FDA_MSG_1001"), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_5019));
        }

    }

    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

    /**
     * Creates the export docs.
     */
    private void createExportDocs() throws IOException, Exception {
        Resource wordRes;
        Resource pdfRes;
        LOGGER.info("Inside AdditionalInformation create Export Docs mehtod" + filePath);
        if (filePath.isDirectory() == Constants.FALSE) {
            filePath.mkdirs();
        }
        if (wordFile.exists() != Constants.TRUE) {

            wordFile.createNewFile();

        }
        if (pdfFile.exists() != Constants.TRUE) {

            pdfFile.createNewFile();

        }

        wordRes = new FileResource(wordFile);
        pdfRes = new FileResource(pdfFile);
        wordDownloader = new FileDownloader(wordRes);
        pdfDownloader = new FileDownloader(pdfRes);
        wordDownloader.extend(wordPngImage);
        pdfDownloader.extend(pdfPngImage);
        LOGGER.info("End of AdditionalInformation create export docs method");
    }

    public void callJavaScript() {
        LOGGER.info("Inside of AdditionalInformation callJavaScript Method");

        uploader.setDebugId("textId");
        attachmentLayout.setDebugId("layoutId");

        Page.getCurrent()
                .getJavaScript()
                .execute(
                        "var f=document.getElementById('layoutId').children[1];" + "var f2=f.firstChild.firstChild.children[1]; " + "f2.addEventListener('change', function(){" + "var f3=f2.value;"
                        + "callJava(f3)" + "}, false);");
        LOGGER.info("Ends of AdditionalInformation callJavaScript Method");
    }

    public void downloadFile(final File uploadedFile) {
        LOGGER.info("Inside AdditionalInformation  download mehtod");

        if (uploadedFile.exists()) {
            final Resource res = new FileResource(uploadedFile);
            Page.getCurrent().open(res, "_blank", true);
        }

        LOGGER.info("Ends AdditionalInformation  download mehtod");
    }

    public boolean fileExists(String fileName) {
        List<AttachmentDTO> uploadedFiles = getUploadedData();
        List<String> fileList = new ArrayList<String>();
        for (AttachmentDTO uploadedFile : uploadedFiles) {
            fileList.add(uploadedFile.getDocumentName());
        }
        return fileList.contains(fileName);
    }

    public List<AttachmentDTO> getUploadedData() {
        return attachmentsListBean.getItemIds();
    }

    @UiHandler("removeBtn")
    public void removeButtonLogic(final Button.ClickEvent event) {
        final Window.CloseListener windowCloseListener = new Window.CloseListener() {
            @Override
            public void windowClose(final Window.CloseEvent close) {
                try {
                    removeBtn.focus();
                } catch (Exception ex) {
                    LOGGER.error(ex.getMessage());
                    AbstractNotificationUtils.getErrorNotification(CommonUtil.getMessage("FDA_MSG_1001"), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_5025));

                }
            }
        };
        if (tableBeanId == null) {
            final MessageBox msgBox = AbstractNotificationUtils.getInfoNotification(CommonUtil.getMessage("FDA_MSG_1028"), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1029));
            msgBox.getWindow().addCloseListener(windowCloseListener);
        } else {

            if (tableBean.getUserName().equalsIgnoreCase(userName)) {
                final MessageBox msgBox = MessageBox.showPlain(Icon.QUESTION, "Remove Attachment", "Are you sure you want to delete this Attachment" + " ?", new MessageBoxListener() {
                    /**
                     * Will execute,when we remove the attachment which we
                     * added.
                     */
                    @Override
                    public void buttonClicked(final ButtonId buttonId) {
                        try {
                            if (buttonId.name().equals("OK")) {
                                closeFlag = true;
                                String moveBack = "../";
                                StringBuilder sb = new StringBuilder(tableBean.getDocumentName());
                                sb.insert(tableBean.getDocumentName().lastIndexOf("."), "_" + String.valueOf("0"));
                                File file = new File(basepath + File.separator + moveBack + moveBack + moveBack + File.separator + "Documents" + File.separator + "FixedDollarAdjustment"
                                        + File.separator + userId + File.separator + sb.toString());
                                if (commonLogic.deleteUploadedFile(tableBean.getDocDetailsId(), file)) {
                                    table.removeItem(tableBeanId);
                                    tableBeanId = Constants.NULL_OBJECT;
                                    tableBean = (AttachmentDTO) Constants.NULL_OBJECT;
                                }
                            }
                        } catch (SystemException e) {
                            final String errorMsg = ErrorCodeUtil.getErrorMessage(e);
                            LOGGER.error(errorMsg);
                            AbstractNotificationUtils.getErrorNotification(CommonUtil.getMessage("FDA_MSG_1001"), errorMsg);
                        } catch (PortalException e) {
                            LOGGER.error(e.getMessage());
                            AbstractNotificationUtils.getErrorNotification(CommonUtil.getMessage("FDA_MSG_1001"), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_5011));
                        } catch (Exception e) {
                            LOGGER.error(e.getMessage());
                            AbstractNotificationUtils.getErrorNotification(CommonUtil.getMessage("FDA_MSG_1001"), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_5011));
                        }
                    }
                }, ButtonId.OK, ButtonId.CANCEL);
                msgBox.getWindow().addCloseListener(windowCloseListener);
            } else {
                final MessageBox msgBox = AbstractNotificationUtils.getInfoNotification(CommonUtil.getMessage("FDA_MSG_1028"), CommonUtil.getMessage("FDA_MSG_1030"));
                msgBox.getWindow().addCloseListener(windowCloseListener);
            }
        }
    }

    @UiHandler("addNotesBtn")
    public void addEnteredNotes(final Button.ClickEvent event) {
        LOGGER.info("Inside AdditionalInformation  add Entered Notes method");

        final String enteredvalue = newNotes.getValue();
        final Window.CloseListener windowListener = new Window.CloseListener() {
            /**
             * Will execute,when we close the window.
             */
            @Override
            public void windowClose(final Window.CloseEvent event) {
                newNotes.focus();
            }
        };

        if (StringUtils.EMPTY.equals(enteredvalue.trim())) {
            final MessageBox msgBox = AbstractNotificationUtils.getErrorNotification(CommonUtil.getMessage("FDA_MSG_1031"), CommonUtil.getMessage("FDA_MSG_1032"));
            msgBox.getWindow().addCloseListener(windowListener);

        } else {

            final MessageBox msgBox = new AbstractNotificationUtils() {
                /**
                 * Will execute when we click Yes in message.
                 */
                @Override
                public void yesMethod() {
                    try {
                        closeFlag = true;
                        int projectionId = 0;
                        String enteredvalue = newNotes.getValue();
                        if (commonLogic.saveNotes(projectionId, userName, enteredvalue, "Fixed Dollar Adjustment")) {
                            DateFormat dateFormat = new SimpleDateFormat("<hh:mm:ss a> <MM/dd/yyyy> ");
                            Date date = new Date();
                            notesHistory.setValue(notesHistory.getValue() + "<" + userName + ">" + " " + dateFormat.format(date) + " : " + enteredvalue + "\n");
                            newNotes.setValue(StringUtils.EMPTY);
                            documentExporter();
                        }
                    } catch (SystemException e) {
                        final String errorMsg = ErrorCodeUtil.getErrorMessage(e);
                        LOGGER.error(errorMsg);
                        AbstractNotificationUtils.getErrorNotification(CommonUtil.getMessage("FDA_MSG_1001"), errorMsg);
                    } catch (Exception e) {
                        LOGGER.error(e.getMessage());
                        AbstractNotificationUtils.getErrorNotification(CommonUtil.getMessage("FDA_MSG_1001"), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_5027));
                    }
                }

                /**
                 * Will execute,when we click an No in the confirmation message.
                 */
                @Override
                public void noMethod() {
                    
                }
            }.getConfirmationMessage("New note confirmation", "Are you sure you want to add this note?");
            msgBox.getWindow().addCloseListener(windowListener);

        }
        LOGGER.info("End of AdditionalInformation add Entered Notes Method");
    }

    public void documentExporter() {
        LOGGER.info("Indide AdditionalInformation document Exporter");

        final ExportWord exportWord = new ExportWord(filePath, wordFile);
        exportWord.export(notesHistory.getValue());
        final Resource wordResOnEdit = new FileResource(new File(filePath + File.separator + FILENAME + ExportWord.DOC_EXT));
        wordDownloader.setFileDownloadResource(wordResOnEdit);

        final ExportPdf exportPdf = new ExportPdf(NOTES_HISTORY, filePath, logo, pdfFile);
        exportPdf.export(notesHistory.getValue());
        final Resource pdfResOnEdit = new FileResource(new File(filePath + File.separator + FILENAME + ExportPdf.PDF_EXT));
        pdfDownloader.setFileDownloadResource(pdfResOnEdit);

        LOGGER.info("Ends AdditionalInformation document Exporter");
    }

    public ComboBox getReasonCode() {
        return reasonCode;
    }

    public void setReasonCode(ComboBox reasonCode) {
        this.reasonCode = reasonCode;
    }

    public TextArea getNotesHistory() {
        return notesHistory;
    }

    public void setNotesHistory(TextArea notesHistory) {
        this.notesHistory = notesHistory;
    }

}
