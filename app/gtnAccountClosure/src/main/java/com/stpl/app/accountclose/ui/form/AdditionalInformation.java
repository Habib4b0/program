/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.ui.form;

import com.stpl.app.accountclose.common.CommonLogic;
import com.stpl.app.accountclose.dto.AttachmentDTO;
import com.stpl.app.accountclose.gtnbalancereport.logic.ViewLogic;
import com.stpl.app.accountclose.logic.AdditionalInfoLogic;
import com.stpl.app.accountclose.sessionutils.SessionDTO;
import com.stpl.app.accountclose.utils.AbstractNotificationUtils;
import com.stpl.app.accountclose.utils.Constants;
import com.stpl.app.accountclose.utils.ErrorCodeUtil;
import com.stpl.app.accountclose.utils.ErrorCodes;
import com.stpl.app.accountclose.utils.FileUploader;
import com.stpl.ifs.ui.errorhandling.ErrorLabel;
import com.stpl.ifs.ui.util.CommonUIUtils;
import com.stpl.ifs.util.CommonUtil;
import com.stpl.ifs.util.ExportPdf;
import com.stpl.ifs.util.ExportWord;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.model.User;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
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
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.JavaScript;
import com.vaadin.ui.JavaScriptFunction;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
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
 * The Class AdditionalInformation.
 *
 * @author santanukumar
 */
public class AdditionalInformation extends CustomComponent implements View {

    /**
     * The excel export image.
     */
    private static final Logger LOGGER = Logger.getLogger(AdditionalInformation.class);
    /**
     * The close flag.
     */
    private boolean closeFlag;
    /**
     * The generate flag.
     */
    private boolean generateFlag;
    /**
     * The module name.
     */
    private final static String GTNMODULENAME = "BaseRate";
    /**
     * The error msg.
     */
    private ErrorLabel errorMsg = new ErrorLabel();
    /**
     * The space.
     */
    private Label space = new Label(Constants.ADD_SPACE, ContentMode.HTML);
    /**
     * The new note.
     */
    @UiField("newNote")
    private TextArea newNote;
    /**
     * The add note.
     */
    @UiField("addNotesBtn")
    private Button addNote;
    /**
     * The notes history.
     */
    @UiField("notesHistory")
    private TextArea notesHistory;
    /**
     * The uploader.
     */
    private TextField uploader = new TextField();
    /**
     * The table.
     */
    @UiField("resultsTable")
    private Table table;
    /**
     * The remove.
     */
    @UiField("removeBtn")
    private Button remove;
    /**
     * The file size.
     */
    private Double fileSize = 0.00;
    /**
     * The user name.
     */
    private String userName = StringUtils.EMPTY;
    /**
     * The user.
     */
    private User user;
    /**
     * The user id.
     */
    private String userId = (String) VaadinSession.getCurrent().getAttribute(Constants.USER_ID);
    /**
     * The attachments list bean.
     */
    private BeanItemContainer<AttachmentDTO> attachmentsListBean = new BeanItemContainer<AttachmentDTO>(AttachmentDTO.class);
    /**
     * The common logic.
     */
    private AdditionalInfoLogic commonLogic = new AdditionalInfoLogic();
    /**
     * The table bean.
     */
    private AttachmentDTO tableBean;
    /**
     * The table bean id.
     */
    private Object tableBeanId;
    /**
     * The file downloader.
     */
    private FileDownloader fileDownloader = new FileDownloader(new FileResource(new File("tst")));
    /**
     * The word png resource.
     */
    private Resource wordPngResource = new ThemeResource("../../icons/word.png");
    /**
     * The pdf png resource.
     */
    private Resource pdfPngResource = new ThemeResource("../../icons/pdf.png");
    SessionDTO session;
    /**
     * The word png image.
     */
    @UiField("wordPngImage")
    private Button wordPngImage;
    /**
     * The pdf png image.
     */
    @UiField("pdfPngImage")
    private Button pdfPngImage;
    /**
     * reasonCodeDdlb
     */
    @UiField("reasonCodeDdlb")
    private ComboBox reasonCodeDdlb;
    /**
     * The file.
     */
    /**
     * The basepath.
     */
    private String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath() == null ? StringUtils.EMPTY : VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
    /**
     * The notes history.
     */
    private static final String NOTES_HISTORY = "Notes History";
    /**
     * The logo.
     */
    private File logo = new File(basepath + "/WEB-INF/images/company_logo.png");
    /**
     * The file name.
     */
    private final static String FILENAME = "Notes_History_BR";
    /**
     * The move back
     */
    /**
     * The file path.
     */
    private final String moveBack = "../";
    final File filePath = new File(basepath + File.separator + moveBack + moveBack + moveBack + File.separator + "Documents" + File.separator + GTNMODULENAME + File.separator + userId);
    /**
     * The word file.
     */
    private File wordFile = new File(filePath + File.separator + FILENAME + ExportWord.DOC_EXT);
    /**
     * The pdf file.
     */
    private File pdfFile = new File(filePath + File.separator + FILENAME + ExportPdf.PDF_EXT);
    /**
     * The upload receiver.
     */
    private FileUploader uploadReceiver = new FileUploader(basepath, GTNMODULENAME);
    /**
     * The add attachment.
     */
    private Upload addAttachment = new Upload(null, uploadReceiver);
    /**
     * The word downloader.
     */
    private FileDownloader wordDownloader;
    /**
     * The pdf downloader.
     */
    private FileDownloader pdfDownloader;
    /**
     * The attachmentLayout.
     */
    @UiField("layout")
    private VerticalLayout attachmentLayout;
    private Date date = new Date();
    private SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSS");
    private String dateValue = format.format(date);
    private String sessionId = (String) VaadinSession.getCurrent().getAttribute(Constants.SESSION_ID);

    /**
     * The layout.
     */
    /**
     * Checks if is close flag.
     *
     * @return the closeFlag
     */
    public boolean isCloseFlag() {
        return closeFlag;
    }

    /**
     * The layout.
     */
    /**
     * Sets the close flag.
     *
     * @param closeFlag the closeFlag to set
     */
    public void setCloseFlag(final boolean closeFlag) {
        this.closeFlag = closeFlag;
    }

    /**
     * Checks if is generate flag.
     *
     * @return the generateFlag
     */
    public boolean isGenerateFlag() {
        return generateFlag;
    }

    /**
     * Sets the generate flag.
     *
     * @param generateFlag the generateFlag to set
     */
    public void setGenerateFlag(final boolean generateFlag) {
        this.generateFlag = generateFlag;
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
     * Gets the new note.
     *
     * @return the newNote
     */
    public TextArea getNewNote() {
        return newNote;
    }

    /**
     * Sets the new note.
     *
     * @param newNote the newNote to set
     */
    public void setNewNote(final TextArea newNote) {
        this.newNote = newNote;
    }

    /**
     * Gets the adds the note.
     *
     * @return the addNote
     */
    public Button getAddNote() {
        return addNote;
    }

    /**
     * Sets the adds the note.
     *
     * @param addNote the addNote to set
     */
    public void setAddNote(final Button addNote) {
        this.addNote = addNote;
    }

    /**
     * Gets the notes history.
     *
     * @return the notesHistory
     */
    public TextArea getNotesHistory() {
        return notesHistory;
    }

    /**
     * Sets the notes history.
     *
     * @param notesHistory the notesHistory to set
     */
    public void setNotesHistory(final TextArea notesHistory) {
        this.notesHistory = notesHistory;
    }

    /**
     * Gets the uploader.
     *
     * @return the uploader
     */
    public TextField getUploader() {
        return uploader;
    }

    /**
     * Sets the uploader.
     *
     * @param uploader the uploader to set
     */
    public void setUploader(final TextField uploader) {
        this.uploader = uploader;
    }

    /**
     * Gets the table.
     *
     * @return the table
     */
    public Table getTable() {
        return table;
    }

    /**
     * Sets the table.
     *
     * @param table the table to set
     */
    public void setTable(final Table table) {
        this.table = table;
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
     * Gets the file size.
     *
     * @return the fileSize
     */
    public Double getFileSize() {
        return fileSize;
    }

    /**
     * Sets the file size.
     *
     * @param fileSize the fileSize to set
     */
    public void setFileSize(final Double fileSize) {
        this.fileSize = fileSize;
    }

    /**
     * Gets the user name.
     *
     * @return the userName
     */
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

    /**
     * Gets the user.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the user.
     *
     * @param user the user to set
     */
    public void setUser(final User user) {
        this.user = user;
    }

    /**
     * Gets the user id.
     *
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Sets the user id.
     *
     * @param userId the userId to set
     */
    public void setUserId(final String userId) {
        this.userId = userId;
    }

    /**
     * Gets the attachments list bean.
     *
     * @return the attachmentsListBean
     */
    public BeanItemContainer<AttachmentDTO> getAttachmentsListBean() {
        return attachmentsListBean;
    }

    /**
     * Sets the attachments list bean.
     *
     * @param attachmentsListBean the attachmentsListBean to set
     */
    public void setAttachmentsListBean(final BeanItemContainer<AttachmentDTO> attachmentsListBean) {
        this.attachmentsListBean = attachmentsListBean;
    }

    /**
     * Gets the common logic.
     *
     * @return the commonLogic
     */
    public AdditionalInfoLogic getCommonLogic() {
        return commonLogic;
    }

    /**
     * Sets the common logic.
     *
     * @param commonLogic the commonLogic to set
     */
    public void setCommonLogic(final AdditionalInfoLogic commonLogic) {
        this.commonLogic = commonLogic;
    }

    /**
     * Gets the table bean.
     *
     * @return the tableBean
     */
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

    /**
     * Gets the table bean id.
     *
     * @return the tableBeanId
     */
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

    /**
     * Gets the file downloader.
     *
     * @return the fileDownloader
     */
    public FileDownloader getFileDownloader() {
        return fileDownloader;
    }

    /**
     * Sets the file downloader.
     *
     * @param fileDownloader the fileDownloader to set
     */
    public void setFileDownloader(final FileDownloader fileDownloader) {
        this.fileDownloader = fileDownloader;
    }

    /**
     * Gets the word png resource.
     *
     * @return the wordPngResource
     */
    public Resource getWordPngResource() {
        return wordPngResource;
    }

    /**
     * Sets the word png resource.
     *
     * @param wordPngResource the wordPngResource to set
     */
    public void setWordPngResource(final Resource wordPngResource) {
        this.wordPngResource = wordPngResource;
    }

    /**
     * Gets the pdf png resource.
     *
     * @return the pdfPngResource
     */
    public Resource getPdfPngResource() {
        return pdfPngResource;
    }

    /**
     * Sets the pdf png resource.
     *
     * @param pdfPngResource the pdfPngResource to set
     */
    public void setPdfPngResource(final Resource pdfPngResource) {
        this.pdfPngResource = pdfPngResource;
    }

    /**
     * Gets the word png image.
     *
     * @return the wordPngImage
     */
    public Button getWordPngImage() {
        return wordPngImage;
    }

    /**
     * Sets the word png image.
     *
     * @param wordPngImage the wordPngImage to set
     */
    public void setWordPngImage(final Button wordPngImage) {
        this.wordPngImage = wordPngImage;
    }

    /**
     * Gets the pdf png image.
     *
     * @return the pdfPngImage
     */
    public Button getPdfPngImage() {
        return pdfPngImage;
    }

    /**
     * Sets the pdf png image.
     *
     * @param pdfPngImage the pdfPngImage to set
     */
    public void setPdfPngImage(final Button pdfPngImage) {
        this.pdfPngImage = pdfPngImage;
    }

    /**
     * Gets the basepath.
     *
     * @return the basepath
     */
    public String getBasepath() {
        return basepath;
    }

    /**
     * Sets the basepath.
     *
     * @param basepath the basepath to set
     */
    public void setBasepath(final String basepath) {
        this.basepath = basepath;
    }

    /**
     * Gets the logo.
     *
     * @return the logo
     */
    public File getLogo() {
        return logo;
    }

    /**
     * Sets the logo.
     *
     * @param logo the logo to set
     */
    public void setLogo(final File logo) {
        this.logo = logo;
    }

    /**
     * Gets the word file.
     *
     * @return the wordFile
     */
    public File getWordFile() {
        return wordFile;
    }

    /**
     * Sets the word file.
     *
     * @param wordFile the wordFile to set
     */
    public void setWordFile(final File wordFile) {
        this.wordFile = wordFile;
    }

    /**
     * Gets the pdf file.
     *
     * @return the pdfFile
     */
    public File getPdfFile() {
        return pdfFile;
    }

    /**
     * Sets the pdf file.
     *
     * @param pdfFile the pdfFile to set
     */
    public void setPdfFile(final File pdfFile) {
        this.pdfFile = pdfFile;
    }

    /**
     * Gets the upload receiver.
     *
     * @return the uploadReceiver
     */
    public FileUploader getUploadReceiver() {
        return uploadReceiver;
    }

    /**
     * Sets the upload receiver.
     *
     * @param uploadReceiver the uploadReceiver to set
     */
    public void setUploadReceiver(final FileUploader uploadReceiver) {
        this.uploadReceiver = uploadReceiver;
    }

    /**
     * Gets the adds the attachment.
     *
     * @return the addAttachment
     */
    public Upload getAddAttachment() {
        return addAttachment;
    }

    /**
     * Sets the adds the attachment.
     *
     * @param addAttachment the addAttachment to set
     */
    public void setAddAttachment(final Upload addAttachment) {
        this.addAttachment = addAttachment;
    }

    /**
     * Gets the word downloader.
     *
     * @return the wordDownloader
     */
    public FileDownloader getWordDownloader() {
        return wordDownloader;
    }

    /**
     * Sets the word downloader.
     *
     * @param wordDownloader the wordDownloader to set
     */
    public void setWordDownloader(final FileDownloader wordDownloader) {
        this.wordDownloader = wordDownloader;
    }

    /**
     * Gets the pdf downloader.
     *
     * @return the pdfDownloader
     */
    public FileDownloader getPdfDownloader() {
        return pdfDownloader;
    }

    /**
     * Sets the pdf downloader.
     *
     * @param pdfDownloader the pdfDownloader to set
     */
    public void setPdfDownloader(final FileDownloader pdfDownloader) {
        this.pdfDownloader = pdfDownloader;
    }

    /**
     * Gets the attachmentLayout.
     *
     * @return the attachmentLayout
     */
    public VerticalLayout getAttachmentLayout() {
        return attachmentLayout;
    }

    /**
     * Sets the attachmentLayout.
     *
     * @param attachmentLayout the attachmentLayout to set
     */
    public void setAttachmentLayout(final VerticalLayout attachmentLayout) {
        this.attachmentLayout = attachmentLayout;
    }

    /**
     * The Constructor.
     */
    public AdditionalInformation(SessionDTO session) throws IOException {
        super();
        LOGGER.info("Inside AdditionalInformation constructor");
        this.session = session;
        LOGGER.info("Ends AdditionalInformation constructor");

    }

    public Component getContent() {
        VerticalLayout layout = new VerticalLayout();
        layout.addComponent(Clara.create(getClass().getResourceAsStream("/additionalInformation.xml"), this));
        attachmentLayout.addComponent(uploader);
        attachmentLayout.addComponent(addAttachment);
        Panel panel = new Panel();
        panel.setContent(layout);
        try {
            configureFields();
            createExportDocs();
        } catch (IOException e) {
            LOGGER.error(e);
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_5025));
        } catch (Exception e) {
            LOGGER.error(e);
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_5025));
        }

        return panel;
    }

    /**
     * Returns the caption.
     *
     * @return the caption
     */
    public String getCaption() {
        LOGGER.info("Inside AdditionalInformation getCaption");

        return "Additional Information";

    }

    /**
     * Download file.
     *
     * @param uploadedFile the uploaded file
     */
    /**
     * Creates the export docs.
     *
     * @throws IOException , Exception
     * @throws Exception the exception
     */
    private void createExportDocs() throws IOException, Exception {

        Resource wordRes;
        Resource pdfRes;
        LOGGER.info("Inside AdditionalInformation create Export Docs mehtod" + filePath);
        if (filePath.isDirectory() == false) {
            filePath.mkdirs();
        }
        if (wordFile.exists() != true) {

            wordFile.createNewFile();

        }
        if (pdfFile.exists() != true) {

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

    /**
     * Configure fields.
     */
    private void configureFields() {
        try {
            LOGGER.info("Indide AdditionalInformation configure Fields methods");

            table.markAsDirty();

            reasonCodeDdlb.setImmediate(true);
            reasonCodeDdlb.setNullSelectionAllowed(false);
            reasonCodeDdlb.addItem(Constants.SELECT_ONE);
            reasonCodeDdlb.setValue(Constants.SELECT_ONE);
            loadReasonCode();
            table.setImmediate(true);
            table.setPageLength(3);
            table.setContainerDataSource(attachmentsListBean);
            table.setVisibleColumns(Constants.ATTACHMENT_COLUMNS);
            table.setColumnHeaders(Constants.ATTACHMENT_HEADER);
            table.setSelectable(true);
            table.addItemClickListener(new ItemClickEvent.ItemClickListener() {
                /**
                 * Will execute,when we click an item in the table.
                 */
                @Override
                public void itemClick(final ItemClickEvent event) {
                    LOGGER.info("Indide AdditionalInformation  add Table itemClick method");

                    tableBeanId = event.getItemId();

                    BeanItem<?> targetItem = null;
                    if (tableBeanId instanceof BeanItem<?>) {
                        targetItem = (BeanItem<?>) tableBeanId;
                    } else if (tableBeanId instanceof AttachmentDTO) {
                        targetItem = new BeanItem<AttachmentDTO>((AttachmentDTO) tableBeanId);
                    }
                    tableBean = (AttachmentDTO) targetItem.getBean();
                    StringBuilder sb = new StringBuilder(tableBean.getDocumentName());
                    sb.insert(tableBean.getDocumentName().lastIndexOf("."), "_" + String.valueOf(session.getProjectionId()));
                    final File uploadedFile = new File(CommonUtil.getGtnDataPath()+"/Attachments/" + File.separator + GTNMODULENAME + File.separator
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
                    final String value = String.valueOf(arguments.get(0).asString());
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
                    final MessageBox msgBox = AbstractNotificationUtils.getErrorNotification("Add New Attachment", "Please select an attachment to add.");
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
            newNote.setImmediate(true);
            newNote.setMaxLength(200);
            newNote.addValidator(new StringLengthValidator(" New Note Should be less than 200 characters", 0, 200, true));
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
                            AbstractNotificationUtils.getErrorNotification("Add New Attachment", "Please select an attachment to add.");
                        } else {
                            if (!(filename != null && (filename.endsWith(".doc") || filename.endsWith(".docx") || filename.endsWith(".ppt") || filename.endsWith(".xls") || filename.endsWith(".xlsx") || filename.endsWith(".pdf") || filename.endsWith(".txt") || filename.endsWith(".csv") || filename.endsWith(".jpeg")))) {
                                addAttachment.interruptUpload();
                                AbstractNotificationUtils.getErrorNotification("Invalid File", "File Not Supported");
                                uploader.setValue(StringUtils.EMPTY);
                            } else if (fileExists(event.getFilename())) {
                                addAttachment.interruptUpload();
                                AbstractNotificationUtils.getWarningNotification("Duplicate File", "File already exists");
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

                    final int projectionId = session.getAcctCloserMasterId();

                    closeFlag = true;

                    try {
                        user = ViewLogic.getUserById(userId);

                        userName = user.getLastName() + "," + user.getFirstName();

                        StringBuilder sb = new StringBuilder(event.getFilename());
                        sb.insert(event.getFilename().lastIndexOf("."), "_" + String.valueOf(session.getProjectionId()));
                        if (commonLogic.saveUploadedFile(projectionId, sb.toString(), userName, fileSize, GTNMODULENAME)) {
                            CommonUIUtils.getMessageNotification(event.getFilename() + " uploaded successfully");
                            attachmentsListBean.removeAllItems();
                            final List<AttachmentDTO> allFiles = commonLogic.getAttachmentDTOList(projectionId, GTNMODULENAME);
                            uploader.setValue(StringUtils.EMPTY);
                            attachmentsListBean.addAll(allFiles);
                        }
                        final File file = new File(CommonUtil.getGtnDataPath()+"/Attachments/" + File.separator + GTNMODULENAME + File.separator + userId + File.separator + event.getFilename());
                        final File newFile = new File(CommonUtil.getGtnDataPath()+"/Attachments/" + File.separator + GTNMODULENAME + File.separator + userId + File.separator + sb.toString());
                        file.renameTo(newFile);
                    } catch (SystemException e) {
                        final String errorMsg = ErrorCodeUtil.getErrorMessage(e);
                        LOGGER.error(errorMsg);
                        AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
                    } catch (Exception e) {
                        LOGGER.error(e);
                        AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_5019));
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

            newNote.setInputPrompt("- Enter New Note -");
            final String userId = (String) VaadinSession.getCurrent().getAttribute(Constants.USER_ID);
            final User user = ViewLogic.getUserById(userId);
            userName = user.getFullName();
            userName = user.getLastName() + "," + user.getFirstName();
            LOGGER.info("End of AdditionalInformation configure fields");
        } catch (Exception e) {
            LOGGER.error(e);
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_5004));
        }

    }

    /**
     * Document exporter.
     */
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

    /**
     * Adds the entered notes.
     *
     * @param event the event
     */
    @UiHandler("addNotesBtn")
    public void addEnteredNotes(final Button.ClickEvent event) {
        LOGGER.info("Inside AdditionalInformation  add Entered Notes method");

        final String enteredvalue = newNote.getValue();
        final Window.CloseListener windowListener = new Window.CloseListener() {
            /**
             * Will execute,when we close the window.
             */
            @Override
            public void windowClose(final Window.CloseEvent event) {
                newNote.focus();
            }
        };

        if (StringUtils.EMPTY.equals(enteredvalue.trim())) {
            final MessageBox msgBox = AbstractNotificationUtils.getInfoNotification("Enter New Note", "Please enter a new note.");
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
                        int projectionId = session.getAcctCloserMasterId();
                        String enteredvalue = newNote.getValue();
                        if (commonLogic.saveNotes(projectionId, userName, enteredvalue, GTNMODULENAME)) {

                            DateFormat dateFormat = new SimpleDateFormat("<hh:mm:ss a> <MM/dd/yyyy>");
                            Date date = new Date();
                            notesHistory.setValue(notesHistory.getValue() + "<" + userName + ">" + " " + dateFormat.format(date) + " : " + enteredvalue + "\n");
                            newNote.setValue(StringUtils.EMPTY);
                            documentExporter();
                        }
                    } catch (SystemException e) {
                        final String errorMsg = ErrorCodeUtil.getErrorMessage(e);
                        LOGGER.error(errorMsg);
                        AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
                    } catch (Exception e) {
                        LOGGER.error(e);
                        AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_5027));
                    }
                }

                @Override
                public void noMethod() {
                    notesHistory.getValue();
                }
            }.getConfirmationMessage("New note confirmation", "Are you sure you want to add this note?");
            msgBox.getWindow().addCloseListener(windowListener);

        }
        LOGGER.info("End of AdditionalInformation add Entered Notes Method");
    }

    public boolean onAdvance() {
        LOGGER.info("Inside of AdditionalInformation onAdvance Method");
        return false;
    }

    public boolean onBack() {
        LOGGER.info("Inside of AdditionalInformation onBack Method");
        return true;
    }

    public void setValues() throws SystemException, PortalException, Exception {
        LOGGER.info("Inside of AdditionalInformation setValues Method");
        generateFlag = true;

        attachmentsListBean.removeAllItems();
        final int projectionId = session.getAcctCloserMasterId();
        final List<AttachmentDTO> allFiles = new AdditionalInfoLogic().getAttachmentDTOList(projectionId, GTNMODULENAME);
        user = ViewLogic.getUserById(userId);

        attachmentsListBean.addAll(commonLogic.addUserFile(allFiles, userName));
        notesHistory.setValue(new AdditionalInfoLogic().getNotes(projectionId, GTNMODULENAME, userName));
        newNote.setValue(StringUtils.EMPTY);
        LOGGER.info("Ends of AdditionalInformation setValues Method");
    }

    @Override
    public void enter(final ViewChangeListener.ViewChangeEvent event) {
        event.toString();
    }

    public boolean isFlag() {
        LOGGER.info("Inside of AdditionalInformation getFlag Method");
        return closeFlag;
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

    public void callJavaScriptForButton() {
        LOGGER.info("Inside of AdditionalInformation callJavaScriptForButton Method");

        uploader.setDebugId("textId");
        addAttachment.setDebugId("fileUpload");
        Page.getCurrent()
                .getJavaScript()
                .execute(
                        "var clk=document.getElementById('fileUpload').firstChild.children[2];" + "clk.addEventListener('click', function(){" + "var val1=document.getElementById('textId').value;"
                        + "if(val1==null||val1.length<=1||val1==''){buttonClicked();}" + "}, false);");
        LOGGER.info("Ends of AdditionalInformation callJavaScriptForButton Method");
    }

    public void setUploderValue() {
        LOGGER.info("Inside of AdditionalInformation setUploderValue Method");

        uploader.setValue(StringUtils.EMPTY);
        LOGGER.info("Ends of AdditionalInformation setUploderValue Method");
    }

    public int getTabNumber() {
        return 2;
    }

    private void loadReasonCode() throws Exception {
        LOGGER.info("Entering loadTypeDdlb method ");
        reasonCodeDdlb = CommonLogic.getComboBox(reasonCodeDdlb, CommonLogic.getDropDownList("REASON_CODES"));
        LOGGER.info("Entering loadTypeDdlb method ");
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
                    remove.focus();
                } catch (Exception ex) {
                    LOGGER.error(ex);
                    AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_5025));

                }
            }
        };
        if (tableBeanId == null) {
            final MessageBox msgBox = AbstractNotificationUtils.getInfoNotification("Remove Attachment", "Please select an attachment to remove.");
            msgBox.getWindow().addCloseListener(windowCloseListener);
        } else {

            if (tableBean.getUserName().equalsIgnoreCase(userName)) {
                final MessageBox msgBox = MessageBox.showPlain(Icon.QUESTION, "Remove Attachment", "Are you sure you want to delete this Attachment" + " ?", new MessageBoxListener() {
                    @Override
                    public void buttonClicked(final ButtonId buttonId) {
                        try {
                            if (buttonId.name().equals("YES")) {
                                closeFlag = true;
                                String moveBack = "../";

                                StringBuilder sb = new StringBuilder(tableBean.getDocumentName());
                                sb.insert(tableBean.getDocumentName().lastIndexOf("."), "_" + String.valueOf(session.getProjectionId()));
                                File file = new File(basepath + File.separator + moveBack + moveBack + moveBack + File.separator + "Documents" + File.separator + GTNMODULENAME
                                        + File.separator + userId + File.separator + sb.toString());

                                if (commonLogic.deleteUploadedFile(tableBean.getDocDetailsId(), file)) {

                                    table.removeItem(tableBeanId);
                                    tableBeanId = Constants.NULL_OBJECT;
                                    tableBean = (AttachmentDTO) Constants.NULL_OBJECT;
                                }
                            }
                        } catch (Exception e) {
                            LOGGER.error(e);
                            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_5011));
                        }
                    }
                }, ButtonId.YES, ButtonId.NO);
                msgBox.getWindow().addCloseListener(windowCloseListener);
            } else {
                final MessageBox msgBox = AbstractNotificationUtils.getInfoNotification("Remove Attachment", "You can only remove attachments that you have uploaded.");
                msgBox.getWindow().addCloseListener(windowCloseListener);
            }
        }
    }

    public void downloadFile(final File uploadedFile) {
        LOGGER.info("Inside AdditionalInformation  download mehtod");

        if (uploadedFile.exists()) {
            final Resource res = new FileResource(uploadedFile);
            Page.getCurrent().open(res, "_blank", true);
        }

        LOGGER.info("Ends AdditionalInformation  download mehtod");
    }

    public void saveButtonLogic() {
    }
}
