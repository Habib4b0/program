package com.stpl.app.global.ifp.dto;

import com.stpl.app.global.cfp.logic.CFPSearchLogic;
import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.global.common.util.CommonUtil;
import com.stpl.app.global.company.util.QueryUtils;
import com.stpl.app.util.CommonUIUtils;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.ErrorCodeUtil;
import com.stpl.app.util.ErrorCodes;
import com.stpl.ifs.ui.CustomePagedFilterTable;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.PopupDateField;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

// TODO: Auto-generated Javadoc
/**
 * The Class TableGenerator.
 */
public class TableGenerator extends DefaultFieldFactory {

    private BeanItemContainer saveContainer;
    /**
     * Constant Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(TableGenerator.class);

    private CommonUtil commonUtil = CommonUtil.getInstance();
    CustomePagedFilterTable table;
    
   
    private String sessionId;
    List checkUpdate = new ArrayList();
    List checkSelect = new ArrayList();
    private int check;
    SessionDTO sessionDTO;

    public TableGenerator(BeanItemContainer saveContainer, CustomePagedFilterTable table, SessionDTO sessionDTO) {
        this.saveContainer = saveContainer;
        this.table = table;
        this.sessionDTO = sessionDTO;
    }

    /**
     * Creates fields.
     *
     * @param container the container
     * @param itemId the item id
     * @param propertyId the property id
     * @param uiContext the ui context
     * @return the field
     */
    @Override
    public Field<?> createField(final Container container, final Object itemId, final Object propertyId, final Component uiContext) {
        try {
            final IFPItemDTO dto = (IFPItemDTO) itemId;
            if (ConstantsUtils.CHECK_BOX.equals(propertyId)) {
                final CheckBox checkbox = new CheckBox();
                checkbox.setReadOnly(false);
                checkbox.setValue(dto.getCheckbox());
                checkbox.setImmediate(true);
                final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
                sessionId = sessionDTO.getUiSessionId();
                checkbox.addValueChangeListener(new Property.ValueChangeListener() {

                    public void valueChange(Property.ValueChangeEvent event) {

                        if (dto.getCheckbox() != null) {
                            saveContainer.addItem(itemId);
                            check = dto.getCheckbox() ? 1 : 0;
                            checkUpdate = new ArrayList();

                            checkUpdate.add(check);
                            checkUpdate.add(sessionId);
                            checkUpdate.add(userId);
                            checkUpdate.add(dto.getItemId());

                            QueryUtils.updateAppData(checkUpdate, "IFPCheckUpdate");

                            checkUpdate = new ArrayList();

                            checkUpdate.add(sessionId);
                            checkUpdate.add(userId);

                            checkSelect = new ArrayList();

                            checkSelect = QueryUtils.getAppData(checkUpdate, "IFPCheckSelect", null);

                            if (checkSelect.isEmpty()) {
                                table.setColumnCheckBox(ConstantsUtils.CHECK_BOX, true, true);
                            } else {
                                table.setColumnCheckBox(ConstantsUtils.CHECK_BOX, true, false);
                            }
                        }
                    }
                });
                return checkbox;
            }
            if (ConstantsUtils.IFP_STATUS.equals(propertyId)) {
                final ComboBox itemFamilyplanStatus = new ComboBox();
                commonUtil.loadComboBox(itemFamilyplanStatus, "STATUS", false);
                itemFamilyplanStatus.setImmediate(true);
                itemFamilyplanStatus.setValidationVisible(true);
                itemFamilyplanStatus.setRequired(true);
                itemFamilyplanStatus.setRequiredError("Item Family plan Status should  be present");
                itemFamilyplanStatus.addValueChangeListener(new Property.ValueChangeListener() {

                    public void valueChange(Property.ValueChangeEvent event) {
                        if (event.getProperty().getValue() == null || ConstantsUtils.NULL.equals(event.getProperty().getValue()) || StringUtils.isBlank(event.getProperty().getValue().toString())) {
                            itemFamilyplanStatus.select(ConstantsUtils.ZERO_INT);
                        } else {
                            dto.setCheckFlag(true);
                            saveContainer.addItem(itemId);
                        }

                    }
                });
                return itemFamilyplanStatus;
            }

            if (ConstantsUtils.IFP_START_DATE.equals(propertyId)) {

                final PopupDateField ifpStartDate = new PopupDateField();
                ifpStartDate.setDescription(ConstantsUtils.DATE_DES);
                ifpStartDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
                ifpStartDate.setImmediate(true);
                ifpStartDate.setRequired(true);
                ifpStartDate.setImmediate(true);
                ifpStartDate.setValue(dto.getItemFamilyplanStartDate());
                ifpStartDate.setRequiredError("IFP Start Date should  be present");
                ifpStartDate.addValueChangeListener(new Property.ValueChangeListener() {

                    public void valueChange(Property.ValueChangeEvent event) {
                        dto.setCheckFlag(true);
                        saveContainer.addItem(itemId);
                        ifpStartDate.setDescription(CommonUIUtils.convert2DigitTo4DigitYear(ifpStartDate.getValue()));
                    }
                });

                return ifpStartDate;
            }

            if (ConstantsUtils.IFP_END_DATE.equals(propertyId)) {

                final PopupDateField ifpEndDate = new PopupDateField();
                ifpEndDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
                ifpEndDate.setDescription(ConstantsUtils.DATE_DES);
                ifpEndDate.setImmediate(true);
                ifpEndDate.setValue(dto.getItemFamilyplanEndDate());
                ifpEndDate.addValueChangeListener(new Property.ValueChangeListener() {

                    public void valueChange(Property.ValueChangeEvent event) {
                        dto.setCheckFlag(true);
                        saveContainer.addItem(itemId);
                        ifpEndDate.setDescription(CommonUIUtils.convert2DigitTo4DigitYear(ifpEndDate.getValue()));
                    }
                });

                return ifpEndDate;
            }

        } catch (Exception exception) {
            final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1010), new MessageBoxListener() {
                /**
                 * The method is triggered when a button of the message box is
                 * pressed .
                 *
                 * @param buttonId The buttonId of the pressed button.
                 */
                @SuppressWarnings("PMD")
                public void buttonClicked(final ButtonId buttonId) {
                    // Do Nothing
                }
            }, ButtonId.OK);
            msg.getButton(ButtonId.OK).focus();
            LOGGER.error(exception);
        }
        return null;
    }

}
