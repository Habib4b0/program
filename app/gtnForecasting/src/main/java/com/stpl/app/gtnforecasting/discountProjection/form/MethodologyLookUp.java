/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.discountProjection.form;

import com.stpl.app.gtnforecasting.discountProjection.dto.FormulaDTO;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.ifs.ui.util.CommonUIUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.ui.Button;
import com.vaadin.ui.Window;
import com.vaadin.v7.data.util.BeanItem;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.ui.Table;
import com.vaadin.v7.ui.VerticalLayout;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author Sibi
 */
public class MethodologyLookUp extends Window {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodologyLookUp.class);

    @UiField("tableLayout")
    private VerticalLayout tableLayout;

    private final Table resultsTable = new Table();

    private final BeanItemContainer<FormulaDTO> resultsContainer = new BeanItemContainer<>(FormulaDTO.class);

    private final Object formulaList;
    /**
     * Default Constructor to load the formula for Mass Update
     *
     * @throws Exception
     */
    public MethodologyLookUp(Object formulaList) {
      this.formulaList=formulaList;
        init();
    }

    /**
     * Configures the Window
     */
    private void init()  {
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
    private void configureTable() {
        resultsTable.setContainerDataSource(resultsContainer);
        resultsTable.setImmediate(true);
        resultsTable.setWidth("434px");
        resultsTable.setVisibleColumns(Constant.FORMULA_NAME);
        resultsTable.setColumnHeaders("Methodology Name");
        resultsTable.setColumnAlignment(Constant.FORMULA_NAME, Table.Align.LEFT);
        resultsTable.setColumnWidth(Constant.FORMULA_NAME, NumericConstants.FOUR_THREE_FOUR);
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
            LOGGER.error(ex.getMessage());
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
            CommonUIUtils.getSelectErrorMessage();            
        }
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
            targetItem = new BeanItem<>(
                    (FormulaDTO) obj);
        }
        return (FormulaDTO) targetItem.getBean();
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

}
