/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.ui.form.lookups;


import com.stpl.app.forecastabstract.lookups.AbstractComparisonLookup;
import com.stpl.app.gtnforecasting.projectionvariance.dto.ComparisonLookupDTO;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;

import java.util.ArrayList;
import java.util.List;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    
    private ExtFilterTable searchTable;
    /**
     * The selected table.
     */
    private ExtFilterTable selectedTable;
    /**
     * The submit btn.
     */
    private final Button submitBtn = new Button();
    /**
     * The add line btn.
     */
    private final Button addLineBtn = new Button();
    /**
     * The remove line btn.
     */
    private final Button removeLineBtn = new Button();
    
    /**
     * The selected projection.
     */
    private final  ExtFilterTable selectedProjection = new ExtFilterTable();
    /**
     * projection id
     */
    private final int currentProjId;

    private final List<ComparisonLookupDTO> selectedList;
    private static final Logger LOGGER = LoggerFactory.getLogger(ComparisonLookup.class);
  
    public ComparisonLookup(final String windowName, final CustomTextField comparisonLookup,final int currentProjId, List<ComparisonLookupDTO> selectedList) {
        super(windowName, comparisonLookup);
        this.comparisonLookup = comparisonLookup;
        this.currentProjId= currentProjId;
        this.selectedList = selectedList == null ? selectedList : new ArrayList<>(selectedList);
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
                LOGGER.debug("Inside Overriden method");
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
        LOGGER.debug("Inside Overriden method:");
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
        LOGGER.debug("Inside Overriden method..");
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
    /**
     * Creates a new Reset button which resets the table content.
     *
     * @return A Reset button and it's listener implementation
     */
    @Override
    public ExtFilterTable getSelectedProjection() {
        return selectedProjection;
    }

    @Override
    protected void configureResultTable(ExtPagedTable results, String indicator) {
        LOGGER.debug("Inside Overriden method: do nothing");
    }
    
    
}
