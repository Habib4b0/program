package com.stpl.app.security.notificationMgmt.ui.form;

import com.stpl.app.security.notificationMgmt.dto.NotificationMgmtIndexDTO;
import com.stpl.app.security.notificationMgmt.logic.NotificationMgmtLogic;
import com.stpl.app.ui.errorhandling.ErrorLabel;
import com.stpl.app.util.CommonUIUtils;
import com.stpl.app.util.MessageUtils;
import com.stpl.app.util.NotificationUtils;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.CustomGridLayout;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.util.BeanItem;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.data.util.IndexedContainer;
import com.vaadin.v7.event.ItemClickEvent;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.v7.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.v7.ui.Label;
import com.vaadin.v7.ui.NativeSelect;
import com.vaadin.v7.ui.OptionGroup;
import com.vaadin.ui.Panel;
import com.vaadin.v7.ui.Table;
import com.vaadin.v7.ui.TextArea;
import com.vaadin.v7.ui.TextField;
import com.vaadin.v7.ui.VerticalLayout;
import java.util.List;
import org.apache.commons.validator.routines.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("serial")
public class NotificationMgmtIndex extends CustomComponent implements View {

	private final Label space = new Label("&nbsp;", ContentMode.HTML);
	private final NativeSelect categoryddlb = new NativeSelect();
	private final OptionGroup mode = new OptionGroup();
	private final Table table = new Table();
	private final NativeSelect businessProcess = new NativeSelect();
	private final TextField fromMailId = new TextField();
	private final TextArea toMailId = new TextArea();
	private final TextArea ccMailId = new TextArea();
	private final TextField subject = new TextField();
	private final TextArea body = new TextArea();
	private final Button reset = new Button("RESET");
	private final Button add = new Button("ADD");
	private final Button update = new Button("UPDATE");
	private final Button delete = new Button("DELETE");
	private final ErrorLabel errorMsg = new ErrorLabel();
	private int mailNotificationSystemId;
	private final NotificationMgmtIndexDTO notificationMgmtIndexDto = new NotificationMgmtIndexDTO();
	private final CustomFieldGroup notificationMgmtBinder = new CustomFieldGroup(new BeanItem<NotificationMgmtIndexDTO>(notificationMgmtIndexDto));
	private final BeanItemContainer<NotificationMgmtIndexDTO> notificationIndexBean = new BeanItemContainer<>(
            NotificationMgmtIndexDTO.class);
	private final NotificationMgmtLogic notificationLogic = new NotificationMgmtLogic();
    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationMgmtIndex.class);

    public NotificationMgmtIndex() {
        init();
        getBinder();
    }

    private void init() {

        addToContent();
        configureFields();
    }

    public void addToContent() {
        space.setHeight("20");
        final VerticalLayout content = new VerticalLayout();
        content.setMargin(true);
        content.addComponent(space);
        content.setSpacing(true);

        content.addComponent(addCategory());
        content.addComponent(addSpace());
        content.addComponent(addSelection());
        content.addComponent(addSpace());
        content.addComponent(addDetails());
        content.addComponent(addButtons());


        setCompositionRoot(content);

    }

    private static CustomGridLayout addSpace() {
        CustomGridLayout gridLayout = new CustomGridLayout(1, 1);
        gridLayout.addComponent(new Label(""));
        return gridLayout;
    }

    private Panel addCategory() {
        Panel categoryPanel = new Panel();
        categoryPanel.setCaption("Category");
        categoryPanel.setContent(categoryddlb);

        return categoryPanel;
    }

    private Panel addSelection() {
        Panel selectionPanel = new Panel();
        selectionPanel.setCaption("Selection");
        VerticalLayout selectionCriteria = new VerticalLayout();
        selectionCriteria.setMargin(true);
        selectionCriteria.setSpacing(true);
        HorizontalLayout layout = new HorizontalLayout();
        layout.setMargin(true);
        layout.setSpacing(true);
        layout.addComponent(addOptionGroup());
        selectionCriteria.addComponent(layout);
        selectionCriteria.addComponent(addTable());
        selectionPanel.setContent(selectionCriteria);
        return selectionPanel;
    }

    private OptionGroup addOptionGroup() {
        mode.addItem("New");
        mode.addItem("Edit");
        mode.addItem(CommonUIUtils.DELETE);
        mode.setValue("Edit");
        return mode;
    }

    private Table addTable() {
        table.setContainerDataSource(notificationIndexBean);
        table.setVisibleColumns(new CommonUIUtils().notificationColumns);
        table.setColumnHeaders(new CommonUIUtils().notificationHeaders);
        table.setWidth("98%");
        table.setPageLength(NumericConstants.SEVEN);
        table.setImmediate(true);
        table.setSelectable(true);
        table.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void itemClick(ItemClickEvent event) {
                try {
                    if (event.getItem() != null) {
                        NotificationMgmtIndexDTO vObjNotificationIndexDto=resultsItemClick(event.getItemId());
                        mailNotificationSystemId = vObjNotificationIndexDto.getMailNotificationSystemId();
                        
                        notificationMgmtBinder.setItemDataSource(new BeanItem<NotificationMgmtIndexDTO>(vObjNotificationIndexDto));

                    }
                } catch (Exception e) {
                    LOGGER.error(e.getMessage());
                }

            }
        });
        return table;
    }

    private Panel addDetails() {
        Panel detailsPanel = new Panel();
        detailsPanel.setCaption("Details");
        HorizontalLayout horizontal = new HorizontalLayout();
        horizontal.setMargin(true);
        horizontal.setSpacing(true);
        GridLayout grigForleftVertical = new GridLayout(NumericConstants.TWO, NumericConstants.FOUR);
        grigForleftVertical.setMargin(true);
        grigForleftVertical.setSpacing(true);
        grigForleftVertical.setWidth("95%");
        grigForleftVertical.addComponent(new Label("Business Process:"));
        businessProcess.setWidth(CommonUIUtils.THREE_FIFTY_PIXELS);
        grigForleftVertical.addComponent(businessProcess);
        grigForleftVertical.addComponent(new Label("From:"));
        fromMailId.setWidth(CommonUIUtils.THREE_FIFTY_PIXELS);
        grigForleftVertical.addComponent(fromMailId);
        grigForleftVertical.addComponent(new Label("To:"));
        toMailId.setWidth(CommonUIUtils.THREE_FIFTY_PIXELS);
        grigForleftVertical.addComponent(toMailId);
        grigForleftVertical.addComponent(new Label("Cc:"));
        ccMailId.setWidth(CommonUIUtils.THREE_FIFTY_PIXELS);
        grigForleftVertical.addComponent(ccMailId);
        GridLayout grigForrightVertical = new GridLayout(NumericConstants.TWO, NumericConstants.TWO);
        grigForrightVertical.setWidth("95%");
        grigForrightVertical.setMargin(true);
        grigForrightVertical.setSpacing(true);
        grigForrightVertical.addComponent(new Label("Subject:"));
        subject.setWidth(CommonUIUtils.THREE_FIFTY_PIXELS);
        grigForrightVertical.addComponent(subject);
        grigForrightVertical.addComponent(new Label("Body:"));
        body.setWidth(CommonUIUtils.THREE_FIFTY_PIXELS);
        body.setHeight("200px");
        grigForrightVertical.addComponent(body);
        horizontal.setWidth("95%");
        horizontal.addComponent(grigForleftVertical);
        horizontal.addComponent(grigForrightVertical);
        detailsPanel.setWidth("95%");
        detailsPanel.setContent(horizontal);

        return detailsPanel;
    }

    private Panel addButtons() {
        Panel buttonPanel = new Panel();

        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setMargin(true);
        buttonLayout.setSpacing(true);
        buttonLayout.addComponent(reset);
        buttonLayout.addComponent(add);
        buttonLayout.addComponent(update);
        buttonLayout.addComponent(delete);
        buttonPanel.setContent(buttonLayout);

        return buttonPanel;
    }

    private void configureFields() {

        add.setEnabled(false);
        delete.setEnabled(false);


        businessProcess.setImmediate(true);
        businessProcess.setNullSelectionAllowed(true);
        businessProcess.markAsDirty();
        businessProcess.setNullSelectionItemId(CommonUIUtils.SELECT_ONE);
        businessProcess.setRequired(true);

        categoryddlb.setImmediate(true);
        categoryddlb.setNullSelectionAllowed(true);
        categoryddlb.markAsDirty();
        categoryddlb.setNullSelectionItemId(CommonUIUtils.SELECT_ONE);
        categoryddlb.setRequired(true);


        fromMailId.setImmediate(true);
        fromMailId.setRequired(true);


        toMailId.setImmediate(true);
        toMailId.setRequired(true);

        ccMailId.setImmediate(true);
        ccMailId.setRequired(true);

        subject.setImmediate(true);
        subject.setRequired(true);

        body.setImmediate(true);
        body.setRequired(true);



        mode.setImmediate(true);
        mode.addValueChangeListener(new Property.ValueChangeListener() {
            private static final long serialVersionUID = 1172963944103056571L;

            @Override
            public void valueChange(final Property.ValueChangeEvent event) {
                if (mode.getValue() == "Edit") {

                    add.setEnabled(false);
                    delete.setEnabled(false);
                    update.setEnabled(true);
                    notificationIndexBean.removeAllItems();
                    loadTable(mode.getValue());
                }
                if (mode.getValue() == "New") {

                    add.setEnabled(true);
                    delete.setEnabled(false);
                    update.setEnabled(false);
                    table.removeAllItems();
                }
                if (mode.getValue() == CommonUIUtils.DELETE) {

                    add.setEnabled(false);
                    delete.setEnabled(true);
                    update.setEnabled(false);
                    notificationIndexBean.removeAllItems();
                    loadTable(mode.getValue());
                }
            }
        });
        add.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                boolean check = validatingFields();
                if (check) {
                    loadDataIntoNotificationDTO();
                    String result = notificationLogic.saveNotification(notificationMgmtIndexDto);
                    if ("success".equals(result)) {
                        loadTableAfterAdd();
                        MessageUtils.getMessageNotification("Saved Successfully");
                        
                    }
                }

            }
        });
        update.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                boolean check = validatingFields();
                if (check) {
                    loadDataIntoNotificationDTO();
                    String result = notificationLogic.updateNotification(notificationMgmtIndexDto, mailNotificationSystemId);
                    if("success".equals(result)){
                         MessageUtils.getMessageNotification("Updated Successfully");
                          loadTableAfterAdd();
                    }
                }
                

            }
        });
        delete.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                boolean result = notificationLogic.deleteNotification(mailNotificationSystemId);
                if (result) {
                    MessageUtils.getMessageNotification("Deleted Successfully");
                    notificationIndexBean.removeAllItems();
                    loadTableAfterAdd();
                    
                }
            }
        });
        reset.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                resetButtonLogic();
            }
        });

        loadCategory();
        loadBusinessProcess();
        loadTable(mode.getValue());

    }

    public void resetButtonLogic() {
        categoryddlb.setValue(CommonUIUtils.SELECT_ONE);
        businessProcess.setValue(CommonUIUtils.SELECT_ONE);
        fromMailId.setValue("");
        toMailId.setValue("");
        ccMailId.setValue("");
        subject.setValue("");
        body.setValue("");
        mode.select("Edit");
    }

    public void loadBusinessProcess() {
        List<String> result = new NotificationMgmtLogic().loadBusinessProcess();
        businessProcess.setContainerDataSource(new IndexedContainer(result));
    }

    public void loadCategory() {
        List<HelperDTO> result = new NotificationMgmtLogic().loadCategory();
        categoryddlb.setContainerDataSource(new IndexedContainer(result));
    }

    public void loadTableAfterAdd() {
        notificationIndexBean.removeAllItems();
        List<NotificationMgmtIndexDTO> resultBean = notificationLogic.loadTable();
        notificationIndexBean.addAll(resultBean);


    }

    public void loadTable(Object mode) {
        String editORDelete = String.valueOf(mode);
        if ("Edit".equals(editORDelete) || CommonUIUtils.DELETE.equals(editORDelete)) {
            List<NotificationMgmtIndexDTO> resultBean = notificationLogic.loadTable();
            notificationIndexBean.addAll(resultBean);
        }

    }

    public void loadDataIntoNotificationDTO() {
        if (categoryddlb.getValue() != null && !"".equals(categoryddlb.getValue()) && !"null".equals(categoryddlb.getValue())) {
            notificationMgmtIndexDto.setCategoryId(((HelperDTO) categoryddlb.getValue()).getId());
        }
        if (businessProcess.getValue() != null) {
            notificationMgmtIndexDto.setBusinessProcess(String.valueOf(businessProcess.getValue()));
        }
        notificationMgmtIndexDto.setFromMailId(fromMailId.getValue().trim());
        notificationMgmtIndexDto.setToMailId(toMailId.getValue().trim());
        notificationMgmtIndexDto.setCcMailId(ccMailId.getValue().trim());
        notificationMgmtIndexDto.setSubject(subject.getValue().trim());
        notificationMgmtIndexDto.setBody(body.getValue().trim());
        String userId = String.valueOf(VaadinSession.getCurrent().getAttribute("userId"));
        notificationMgmtIndexDto.setCreatedById(!"".equals(userId) || userId != null   ? Integer.parseInt(userId) : 0);
        notificationMgmtIndexDto.setModifiedById(!"".equals(userId) || userId != null  ? Integer.parseInt(userId) : 0);
    }

    private CustomFieldGroup getBinder() {

        notificationMgmtBinder.setItemDataSource(new BeanItem<NotificationMgmtIndexDTO>(
                notificationMgmtIndexDto));
        notificationMgmtBinder.bindMemberFields(this);
        notificationMgmtBinder.setBuffered(true);
        notificationMgmtBinder.setErrorDisplay(errorMsg);
        return notificationMgmtBinder;
    }

    public boolean validatingFields() {
        String category = "";
        String bussProcess = "";
        String emailFrom = "";
        String emailTo = "";
        String emailCc = "";
        String emailSubj = "";
        String emailBody = "";
        category = String.valueOf(this.categoryddlb.getValue());
        bussProcess = String.valueOf(this.businessProcess);
        emailFrom = fromMailId.getValue();
        emailTo = toMailId.getValue();
        emailCc = ccMailId.getValue();
        emailSubj = this.subject.getValue();
        emailBody = this.body.getValue();
        if (category == null || "".equals(category) || "null".equals(category)) {
            NotificationUtils.getErrorNotification("Category Empty", "You didn't select a category. \n Please select again");
            return false;
        }
        if (bussProcess == null || "".equals(bussProcess) || "null".equals(bussProcess)) {
            NotificationUtils.getErrorNotification("businessProcess Empty", "You didn't select a businessProcess. \n Please select again");
            return false;
        }
        if (emailSubj == null || "".equals(emailSubj) || "null".equals(emailSubj)) {
            NotificationUtils.getErrorNotification("subject Empty", "Subject field should not be empty .");
            return false;
        }
        if (emailBody == null || "".equals(emailBody) || "null".equals(emailBody)) {
            NotificationUtils.getErrorNotification("Message body Empty", "Message body field should not be empty .");
            return false;
        }
        if (emailFrom == null || "".equals(emailFrom) || "null".equals(emailFrom)) {
            NotificationUtils.getErrorNotification("From  Empty", " From field should not be empty .");
            return false;
        } else {

             String[] emails=emailFrom.split(",");
             if(emails.length==1){
            if (!(EmailValidator.getInstance().isValid(emailFrom))) {
                NotificationUtils.getErrorNotification("Email Invalid in From", "Please enter a valid E-mail for From");
                return false;
            }
             }
             else{
                NotificationUtils.getErrorNotification("One From Email", "Please enter only one E-mail for From");
                return false;
             }
        }
        if (emailTo == null || "".equals(emailTo) || "null".equals(emailTo)) {
            NotificationUtils.getErrorNotification("To Empty", " To field should not be empty .");
            return false;
        } else {
            String[] emails=emailTo.split(",");
        for(String email:emails){
        if (!(EmailValidator.getInstance().isValid(email))) {

                NotificationUtils.getErrorNotification("Email Invalid in To", "Please enter a valid E-mail for TO");
                return false;
            }
        }   
        }
        if (emailCc == null || "".equals(emailCc) || "null".equals(emailCc)) {
            NotificationUtils.getErrorNotification("Cc Empty", " Cc field should not be empty .");
            return false;
        } else {
             String[] emails=emailCc.split(",");
        for(String email:emails){
            if (!(EmailValidator.getInstance().isValid(email))) {

                NotificationUtils.getErrorNotification("Email Invalid in Cc", "Please enter a valid E-mail for Cc");
                return false;
            }
        }
        }
        return true;
    }

    protected NotificationMgmtIndexDTO resultsItemClick(Object id) {
        if (id != null) {
            BeanItem<?> targetItem = null;
            if (id instanceof BeanItem<?>) {
                targetItem = (BeanItem<?>) id;
            } else if (id instanceof NotificationMgmtIndexDTO) {
                targetItem = new BeanItem<>(
                        (NotificationMgmtIndexDTO) id);
            }
            
            NotificationMgmtIndexDTO notiMgmtIndexDto = new NotificationMgmtIndexDTO();
            if (targetItem != null) {
                notiMgmtIndexDto = (NotificationMgmtIndexDTO) targetItem.getBean();
                notiMgmtIndexDto.setCategoryddlb(((NotificationMgmtIndexDTO) targetItem.getBean()).getCategoryddlb());
            }
            return notiMgmtIndexDto;
        }
       return null;
    }

        @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        return;
    }
}
