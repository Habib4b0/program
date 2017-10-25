/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.ui.form.lookups;


import org.asi.ui.customtextfield.CustomTextField;

import com.stpl.app.forecastabstract.lookups.AbstractComparisonLookup;
import com.stpl.app.gtnforecasting.projectionvariance.dto.ComparisonLookupDTO;
import com.stpl.ifs.ui.forecastds.dto.DataSelectionDTO;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import java.util.List;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;

// TODO: Auto-generated Javadoc
/**
 * The Class ComparisonLookup.
 *
 * @author soundarrajan
 */
public class ComparisonLookup extends AbstractComparisonLookup {

    /**
     * The results.
     */
    private final ExtFilterTable results = new ExtFilterTable();
    /**
     * The market type.
     */
    /**
     * The workflow status.
     */
    /**
     * The brand.
     */
    /**
     * The proj name.
     */
    /**
     * The cont hldr.
     */
    /**
     * The ndc no.
     */
    /**
     * The ndc name.
     */
    /**
     * The desc.
     */
    /**
     * The contract.
     */
    /**
     * The from.
     */
    /**
     * The to.
     */
    /**
     * The search table.
     */
    private ExtFilterTable searchTable;
    /**
     * The selected table.
     */
    private ExtFilterTable selectedTable;
    /**
     * The submit btn.
     */
    private Button submitBtn = new Button();
    /**
     * The add line btn.
     */
    private Button addLineBtn = new Button();
    /**
     * The remove line btn.
     */
    private Button removeLineBtn = new Button();
    /**
     * The comparison lookup.
     */
    CustomTextField comparisonLookup;
    /**
     * The selected projection.
     */
    private  ExtFilterTable selectedProjection = new ExtFilterTable();
    /**
     * projection id
     */
    public int currentProjId;

    List<ComparisonLookupDTO> selectedList;
  

    /**
     * Constructor for ComparisonLookup.
     *
     * @param windowName Name of the window
     * @param moduleIndicator Indicates the module. NonMandated or Mandated or
     * Channels
     * @param comparisonLookup Textfield which opens this lookup
     */
    public ComparisonLookup(final String windowName, final String moduleIndicator, final CustomTextField comparisonLookup,final int currentProjId, List<ComparisonLookupDTO> selectedList) {
        super(windowName, moduleIndicator, comparisonLookup);
        this.comparisonLookup = comparisonLookup;
        this.currentProjId= currentProjId;
        this.selectedList = selectedList;
        buildLookup();
        results.setSelectable(true);
        selectedProjection.setSelectable(true);
    }

    /**
     * Builds the window.
     */
    private void buildLookup() {
        setContent(buildComparisonLookup(results, selectedProjection,currentProjId,selectedList));

        addLineBtn.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                Object item = searchTable.getItem(searchTable.getValue());
                searchTable.removeItem(item);
                selectedTable.addItem(item);
            }
        });

        removeLineBtn.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                Object item = selectedTable.getItem(selectedTable.getValue());
                selectedTable.removeItem(item);
                searchTable.addItem(item);
            }
        });

        submitBtn.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {

          
            }
        });

    }

    /**
     * Submit logic.
     */
    @Override
    /**
     * Logic for Submit button
     */
    protected void btnSubmitLogic() {
        // Logic for Submit button
    }

    /**
     * Remove logic.
     */
    @Override
    /**
     * Logic for Remove button
     */
    protected void btnRemoveLogic() {
        // Logic for Remove button
    }

    /**
     * Adds the line.
     *
     * @param itemId the item id
     */

    /**
     * Removes the line.
     *
     * @param itemId the item id
     */

    /**
     * Search button logic.
     *
     * @return the search button
     */
    /**
     * Creates a new Reset button which resets the search criteria.
     *
     * @return A Reset button and it's listener implementation
     */
    /**
     * The container.
     */
    BeanItemContainer<DataSelectionDTO> container;

    /**
     * Creates a new Reset button which resets the table content.
     *
     * @return A Reset button and it's listener implementation
     */
    public ExtFilterTable getSelectedProjection() {
        return selectedProjection;
    }

    @Override
    protected void configureResultTable(ExtPagedTable results, String indicator) {

    }
    
    
}
