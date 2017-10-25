/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.discountProjection.form;

import com.stpl.app.gtnforecasting.discountProjection.dto.FormulaDTO;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.util.List;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author Sibi
 */
public class MethodologyLookUp extends Window {

    private static final Logger LOGGER = Logger.getLogger(MethodologyLookUp.class);

    @UiField("tableLayout")
    private VerticalLayout tableLayout;

    private final Table resultsTable = new Table();

    BeanItemContainer<FormulaDTO> resultsContainer = new BeanItemContainer<>(FormulaDTO.class);

    Object formulaList;
    /**
     * Default Constructor to load the formula for Mass Update
     *
     * @throws Exception
     */
    public MethodologyLookUp(Object formulaList) throws SystemException{
      this.formulaList=formulaList;
        init();
    }

    /**
     * Configures the Window
     */
    private void init() throws SystemException {
        this.setModal(true);
        this.setClosable(true);
        this.center();
        setCaption("Methodology Lookup");
        addStyleName(Constant.BOOTSTRAP);
        addStyleName("bootstrap-bb");
        setContent(Clara.create(getClass().getResourceAsStream("/mandated/MethodologyLookUp.xml"), this));
        configureTable();
        searchLogic();
    }

    /**
     * Configures the table logic and result table.
     */
    private void configureTable() throws SystemException {
        resultsTable.setContainerDataSource(resultsContainer);
        resultsTable.setImmediate(true);
        resultsTable.setWidth("434px");
        resultsTable.setVisibleColumns("formulaName");
        resultsTable.setColumnHeaders("Methodology Name");
        resultsTable.setColumnAlignment("formulaName", Table.Align.LEFT);
        resultsTable.setColumnWidth("formulaName", NumericConstants.FOUR_THREE_FOUR);
        resultsTable.setSelectable(true);
        tableLayout.setWidth("434px");
        tableLayout.addComponent(resultsTable);
    }

    public void searchLogic() {
        try {
            List<FormulaDTO> resultList = null;

                resultList = (List<FormulaDTO>) formulaList;
                resultsContainer.addAll(resultList);

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    /**
     * Selects the item in the table and closes the window.
     *
     * @param event
     */
    @UiHandler("selectBtn")
    public void selectLogic(Button.ClickEvent event) {
        if (null != resultsTable.getValue()) {
            this.close();
        } else {
            final MessageBox msg = MessageBox.showPlain(Icon.WARN, "Select error", "Please select a record.", new MessageBoxListener() {

                /**
                 * The method is triggered when a button of the message box is
                 * pressed .
                 *
                 * @param buttonId The buttonId of the pressed button.
                 */
                @SuppressWarnings("PMD")
                public void buttonClicked(final ButtonId buttonId) {
                  
                }
            }, ButtonId.OK);
            msg.getButton(ButtonId.OK).focus();
        }
    }

    /**
     * Closes the window.
     *
     * @param event
     */
    @UiHandler("closeBtn")
    public void closeLogic(Button.ClickEvent event) {
        this.close();
    }

    /**
     * Returns the selected Item from the table.
     *
     * @return
     */
    public FormulaDTO getSelectedItem() {
        if (resultsTable.getValue() != null) {
            return getBeanFromId(resultsTable.getValue());
        }
        return null;
    }

    /**
     *
     * @param obj
     * @return
     */
    public FormulaDTO getBeanFromId(Object obj) {

        BeanItem<?> targetItem = null;
        if (obj instanceof BeanItem<?>) {
            targetItem = (BeanItem<?>) obj;
        } else if (obj instanceof FormulaDTO) {
            targetItem = new BeanItem<FormulaDTO>(
                    (FormulaDTO) obj);
        }
        return (FormulaDTO) targetItem.getBean();
    }

}
