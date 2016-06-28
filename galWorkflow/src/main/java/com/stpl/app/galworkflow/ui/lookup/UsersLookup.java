/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galworkflow.ui.lookup;

import com.stpl.app.galworkflow.dto.UserViewDTO;
import com.stpl.app.galworkflow.logic.WorkflowLogic;
import com.stpl.app.galworkflow.util.CommonUtils;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.util.List;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author manikandaprabu.g
 */
public final class UsersLookup extends Window {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(UsersLookup.class);
    /**
     * Bean creation Using DTO
     */
    BeanItemContainer<UserViewDTO> userLookupitemBean = new BeanItemContainer<UserViewDTO>(UserViewDTO.class);

    @UiField("lastName")
    private TextField lastName;
    @UiField("firstName")
    private TextField firstName;
    @UiField("searchBtn")
    private Button searchBtn;
    @UiField("resetBtn")
    private Button resetBtn;
    @UiField("selectBtn")
    private Button selectBtn;
    @UiField("closeBtn")
    private Button closeBtn;
    @UiField("resetTable")
    private Button resetTable;
    @UiField("tableLayout")
    private VerticalLayout tableLayout;
    @UiField("resultPanel")
    private Panel resultPanel;
    /**
     * Ext Filter Table Creation
     */
    ExtFilterTable table = new ExtFilterTable();
    UserViewDTO userdto = new UserViewDTO();
    /**
     * Object creation for Workflow Logic
     */
    WorkflowLogic logic = new WorkflowLogic();
    /**
     * Binder Creation using DTO
     */
    private CustomFieldGroup binder = new CustomFieldGroup(new BeanItem<UserViewDTO>(new UserViewDTO()));
   /**
    * Getter Setter Creation for Setting ID Value
    */
    UserViewDTO idValue;
    CommonUtils commonUtils = new CommonUtils();

    public UserViewDTO getIdValue() {
        return idValue;
    }

    public void setIdValue(UserViewDTO idValue) {
        this.idValue = idValue;
    }

    public UsersLookup(final String lookupname) {
        super(lookupname);
        addStyleName("bootstrap");
        addStyleName("bootstrap-bb");
        try {
            setContent(Clara.create(getClass().getResourceAsStream("/clara/UsersLookup.xml"), this));
            init();
        } catch (PortalException ex) {
            LOGGER.info(ex);
        } catch (Exception ex) {
            LOGGER.info(ex);
        }
    }

    /**
     * Initialize the the form.
     *
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    private void init() throws SystemException, PortalException, Exception {

        center();
        setClosable(true);
        setModal(true);
        setWidth("600px");
        setHeight("720px");
        addToTable();
        getBinder();
        configureFields();
    }

    /**
     * For configuring Fields and Button
     */
    public void configureFields() {
        tableLayout.addComponent(table);
        selectBtn.setEnabled(false);
        resultPanel.setWidth("100%");
        closeButton();
        selectButton();
        resetButton();
        searchButton();
        resetTable();
    }

    private CustomFieldGroup getBinder() {
        UserViewDTO bean = new UserViewDTO();
        binder = new CustomFieldGroup(new BeanItem<UserViewDTO>(bean));
        binder.setBuffered(true);
        binder.bindMemberFields(this);
        return binder;
    }

    /**
     * Configuring Table
     *
     * @throws SystemException
     * @throws PortalException
     */
    public void addToTable() throws SystemException, PortalException {
        LOGGER.info("Entering addToTable ");
        table.markAsDirty();
        table.setContainerDataSource(userLookupitemBean);
        table.setVisibleColumns(CommonUtils.VIEW_VISIBLE_COLUMN);
        table.setColumnHeaders(CommonUtils.VIEW_HEADER);
        table.setPageLength(6);
        table.setWidth("100%");
        table.setComponentError(null);
        table.setFilterBarVisible(true);
        table.setFilterDecorator(new ExtDemoFilterDecorator());
        table.setValidationVisible(false);
        table.addStyleName("filterbar");
        table.setSelectable(true);

        table.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void itemClick(ItemClickEvent event) {
                try {
                    selectBtn.setEnabled(true);
                } catch (Exception e) {

                     LOGGER.error(e);
                }

            }
        });

    }

    /**
     * Btn close logic.
     *
     * @return
     */
    public Button closeButton() {
        closeBtn.setImmediate(true);
        closeBtn.addClickListener(new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(Button.ClickEvent event) {
                close();
            }
        });
        return closeBtn;
    }

    /**
     * Btn Select logic.
     *
     * @return
     */
    public Button selectButton() {
        selectBtn.setImmediate(true);
        selectBtn.addClickListener(new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (table.getValue() != null) {
                    final UserViewDTO itemId = (UserViewDTO) table.getValue();
                    setIdValue(itemId);
                    close();
                }
            }
        });
        return selectBtn;
    }

    /**
     * Btn Reset logic.
     *
     * @return
     */
    public Button resetButton() {
        resetBtn.setImmediate(true);
        resetBtn.addClickListener(new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(Button.ClickEvent event) {
                MessageBox.showPlain(Icon.QUESTION, commonUtils.getConfirmResetMessage(), commonUtils.getResetMessage(), new MessageBoxListener() {
                    /**
                     * Called when a Button has been clicked .
                     *
                     */
                    @SuppressWarnings("PMD")
                    public void buttonClicked(final ButtonId buttonId) {
                        try {
                            if (buttonId.name().equals(CommonUtils.YES)) {
                                firstName.setValue(StringUtils.EMPTY);
                                lastName.setValue(StringUtils.EMPTY);
                            }
                        } catch (Exception ex) {
                            LOGGER.error(ex);
                        }
                    }
                }, ButtonId.YES, ButtonId.NO);

            }
        });
        return resetBtn;
    }

    /**
     * Btn Search logic.
     *
     * @return
     */
    public Button searchButton() {
        searchBtn.setImmediate(true);
        searchBtn.addClickListener(new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(Button.ClickEvent event) {
                try {
                    binder.commit();
                    userdto.setFirstName(firstName.getValue());
                    userdto.setLastName(lastName.getValue());
                    List<UserViewDTO> list = logic.UserSearch(userdto);
                    userLookupitemBean.removeAllItems();
                    userLookupitemBean.addAll(list);
                    if (list.size() > 0) {
                        CommonUtils.getMessageNotification(commonUtils.getSearchComplete());
                    } else {
                        CommonUtils.getMessageNotification(commonUtils.getNoResultMessage());
                    }
                } catch (Exception e) {
                    LOGGER.info(e);
                }
            }
        });
        return searchBtn;
    }

    /**
     * Btn Reset Table logic.
     *
     * @return
     */
    public Button resetTable() {
        resetTable.setImmediate(true);
        resetTable.addClickListener(new Button.ClickListener() {

            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(Button.ClickEvent event) {
                MessageBox.showPlain(Icon.QUESTION, commonUtils.getConfirmResetMessage(), commonUtils.getResetMessage(), new MessageBoxListener() {
                    /**
                     * Called when a Button has been clicked .
                     *
                     */
                    @SuppressWarnings("PMD")
                    public void buttonClicked(final ButtonId buttonId) {
                        try {
                            if (buttonId.name().equals(CommonUtils.YES)) {
                                table.removeAllItems();
                                table.setContainerDataSource(userLookupitemBean);
                                 table.setVisibleColumns(CommonUtils.VIEW_VISIBLE_COLUMN);
                                 table.setColumnHeaders(CommonUtils.VIEW_HEADER);
                                selectBtn.setEnabled(false);
                            }
                        } catch (Exception ex) {
                            LOGGER.error(ex);
                        }
                    }
                }, ButtonId.YES, ButtonId.NO);

            }
        });

        return resetTable;
    }

}
