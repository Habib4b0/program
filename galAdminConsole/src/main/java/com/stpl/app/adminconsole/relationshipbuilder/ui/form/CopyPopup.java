package com.stpl.app.adminconsole.relationshipbuilder.ui.form;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.stpl.app.adminconsole.relationshipbuilder.dto.RelationshipBuilderDTO;
import com.stpl.app.adminconsole.relationshipbuilder.logic.RelationBuilderLogic;
import com.stpl.app.adminconsole.relationshipbuilder.logic.RelationshipCopyLogic;
import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.stpl.app.adminconsole.util.ResponsiveUtils;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.errorhandling.ErrorLabel;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;

import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;

public class CopyPopup extends Window {

    private static final long serialVersionUID = 8360181110502388578L;

    private int relationshipBuilderSid;

    @UiField("relationshipName")
    private TextField relationshipName;

    @UiField("relationshipDesc")
    private TextField relationshipDesc;

    private Label space = new Label("&nbsp;", ContentMode.HTML);

    @UiField("save")
    private Button save;

    @UiField("cancel")
    private Button cancel;

    @UiField("errorMsg")
    private ErrorLabel errorMsg;

    private RelationshipBuilderDTO copyDto = new RelationshipBuilderDTO();

    private CustomFieldGroup binder = new CustomFieldGroup(new BeanItem<RelationshipBuilderDTO>(copyDto));

    private RelationBuilderLogic logic = new RelationBuilderLogic();

    private static final Logger LOGGER = Logger.getLogger(CopyPopup.class);

    public CopyPopup(int relationshipBuilderSid) {
        super("Copy Relation");
        setModal(true);
        setResizable(false);
        center();
        this.relationshipBuilderSid = relationshipBuilderSid;
        addStyleName("bootstrap");
        addStyleName("bootstrap-bb");
        addStyleName("bootstrap-admin");
        addStyleName("bootstrap-adminConsole");
        addStyleName("admin-reponsive");
        buildPopup();
    }

    private void buildPopup() {
        try {
            setContent(Clara.create(getClass().getResourceAsStream("/clara/RelationshipBuilder/copyRelationshipBuilder.xml"), this));
            getBinder();
            configureFields();

        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    private void getBinder() {
        binder.bindMemberFields(this);
        binder.setItemDataSource(new BeanItem<RelationshipBuilderDTO>(copyDto));
        binder.setBuffered(true);
        binder.setErrorDisplay(errorMsg);
    }

    private void configureFields() {
        // need to configure
        relationshipName.addValidator(new StringLengthValidator("Relationship Name length should be less than 100 characters", 0, 100, true));
        relationshipName.setImmediate(true);
        relationshipName.setRequired(true);
        relationshipName.setRequiredError("Please enter Relationship Name.");
        relationshipName.focus();

        relationshipDesc.addValidator(new StringLengthValidator("Relationship Description length should be less than 100 characters", 0, 100, true));
        relationshipDesc.setImmediate(true);
        relationshipDesc.setRequired(true);
        relationshipDesc.setRequiredError("Please enter Relationship Description.");

        save.addClickListener(new ClickListener() {

            private static final long serialVersionUID = 743225136129117287L;

            @Override
            public void buttonClick(ClickEvent event) {
                try {
                    binder.commit();
                    final Map duplicateRelationName = logic.getExistingRelationshipNames();
                    if (StringUtils.isBlank(relationshipName.getValue())) {
                        binder.getErrorDisplay().setError("Please enter Relationship Name.");
                        return;
                    } else {
                        if (StringUtils.isBlank(relationshipDesc.getValue())) {
                            binder.getErrorDisplay().setError("Please enter Relationship Description.");
                            return;
                        } else {
                            if (duplicateRelationName.containsValue(relationshipName.getValue())) {
                                MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "Entered Relationship Name already exists ", ButtonId.OK);
                                relationshipName.focus();
                                return;
                            } else {
                                RelationshipCopyLogic.copyRelationship(relationshipBuilderSid, relationshipName.getValue(), relationshipDesc.getValue());
                                close();
                                final Notification notif = new Notification(" '" + relationshipName.getValue() + "' has been created successfully", Notification.Type.HUMANIZED_MESSAGE);
                                notif.setPosition(Position.BOTTOM_CENTER);
                                notif.setStyleName(ConstantsUtils.MY_STYLE);
                                notif.setDelayMsec(3000);
                                notif.show(Page.getCurrent());
                            }
                        }
                    }
                } catch (CommitException e) {
                    LOGGER.error(e);
                } catch (SystemException e) {
                    LOGGER.error(e);
                } catch (Exception e) {
                    LOGGER.error(e);
                }

            }
        });
        cancel.addClickListener(new ClickListener() {

            private static final long serialVersionUID = 406070511876393048L;

            @Override
            public void buttonClick(ClickEvent event) {
                close();

            }
        });
    }

    private Component addActionButtons() {
        final HorizontalLayout layout = new HorizontalLayout();
        layout.setStyleName(ConstantsUtils.RESPONSIVE_TAB_GRID);
        layout.setSpacing(true);
        layout.addComponent(save);
        save.setEnabled(true);
        layout.addComponent(cancel);
        cancel.setEnabled(true);
        return layout;
    }

    private Component addCopyInfo() {
        final HorizontalLayout hlayout1 = new HorizontalLayout();
        hlayout1.setStyleName(ConstantsUtils.RESPONSIVE_TAB_GRID);
        final CssLayout cssLayout = new CssLayout();
        cssLayout.setSizeFull();
        cssLayout.addStyleName("minWidth50px");
        cssLayout.addComponent(ResponsiveUtils.makeLabel("Relationship Name :", true));
        cssLayout.addComponent(relationshipName);
        Label relDesc = ResponsiveUtils.makeLabel("Relationship Description :", true);
        relDesc.setWidth("200px");
        cssLayout.addComponent(relDesc);
        cssLayout.addComponent(relationshipDesc);
        hlayout1.addComponent(cssLayout);
        return hlayout1;
    }

}
