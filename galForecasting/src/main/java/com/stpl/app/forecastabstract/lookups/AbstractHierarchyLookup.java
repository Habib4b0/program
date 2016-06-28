/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.forecastabstract.lookups;

import static com.stpl.app.utils.Constants.LabelConstants.*;
import com.vaadin.ui.Panel;
import com.stpl.app.utils.TableHeaderColumnsUtil;
import com.stpl.app.utils.UiUtils;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtFilterTable;

/**
 *
 * @author soundarrajan
 */
public abstract class AbstractHierarchyLookup extends AbstractLookup {

    private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(AbstractHierarchyLookup.class);

    /**
     * To indicate customer or product lookup
     */
    private String indicator;
    /**
     * To indicate Non Mandated or Mandated or Channels
     */
    private String moduleIndicator;

    /**
     * Button for search
     */
    private Button searchBtn;

    /**
     * Button for Reset
     */
    private Button resetBtn;
    private Button selectBtn;
    /**
     * OptionGroup for hierarchyType
     */
    private OptionGroup hierarchyType;

    public AbstractHierarchyLookup(final String indicator, final String windowName, final String moduleIndicator) {
        super(windowName);
        this.indicator = indicator;
        this.moduleIndicator = moduleIndicator;
        setWidth(1000, Unit.PIXELS);
        setHeight(845, Unit.PIXELS);
    }

    /**
     * Builds the lookup
     *
     * @param hierarchyName Hierarchy name textfield for search
     * @param results results table
     * @return Layout containing all the components
     */
    public VerticalLayout buildHierarchyLookup(final TextField hierarchyName, final OptionGroup hierarchyType, final ExtFilterTable results, final Button searchBtn, final Button resetBtn, final Button selectBtn)  {
        this.searchBtn = searchBtn;
        this.resetBtn = resetBtn;
        this.hierarchyType = hierarchyType;
        this.selectBtn = selectBtn;
        VerticalLayout mainLayout = (VerticalLayout) UiUtils.getLayout(VerticalLayout.class);
        mainLayout.addComponent(addSearchCriteria(hierarchyName));
        mainLayout.addComponent(getSearchCriteriaButtons());
        mainLayout.addComponent(addResultSection(results));
        mainLayout.addComponent(addFooterButtonsTypeOne(results, selectBtn));
        return mainLayout;
    }

    private HorizontalLayout getSearchCriteriaButtons() {
        HorizontalLayout buttonLayout = (HorizontalLayout) UiUtils.getLayout(HorizontalLayout.class);
        buttonLayout.addComponent(searchBtn);
        buttonLayout.addComponent(resetBtn);
        buttonLayout.setMargin(false);
        searchBtn.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                btnSearchLogic();
            }
        });

        resetBtn.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                btnResetLogic();
            }
        });
        return buttonLayout;
    }

    /**
     * Adds the search criteria
     *
     * @param hierarchyName Hierarchy name textfield for search
     * @return Panel with the search criteria
     */
    private Panel addSearchCriteria(final TextField hierarchyName) {
        Panel searchCriteria = UiUtils.addCommonPanel(SEARCH_CRITERIA.getConstant());
        HorizontalLayout hLayout = (HorizontalLayout) UiUtils.getLayout(HorizontalLayout.class);
        GridLayout criteriaLayout = new GridLayout(4, 1);
        Label hierarchyTypeLabel = UiUtils.makeLabel(HIERARCHY_TYPE.getConstant());
        Label hierarchyNameLabel = UiUtils.makeLabel(HIERARCHY_NAME.getConstant());
        hierarchyTypeLabel.setWidth(135, Unit.PIXELS);
        hierarchyNameLabel.setWidth(128, Unit.PIXELS);
        criteriaLayout.setMargin(false);
        criteriaLayout.setSpacing(false);
        initializeHierarchyType();
        criteriaLayout.addComponent(hierarchyTypeLabel);
        criteriaLayout.addComponent(hierarchyType);
        criteriaLayout.addComponent(hierarchyNameLabel);
        criteriaLayout.addComponent(hierarchyName);
        hLayout.addComponent(criteriaLayout);
        hLayout.setMargin(true);
        hLayout.setSpacing(false);
        searchCriteria.setContent(hLayout);
        return searchCriteria;
    }

    /**
     * Initializes and configures hierarchyType option group
     */
    private void initializeHierarchyType() {

        hierarchyType.addItem(PRIMARY.getConstant());
        hierarchyType.addItem(SECONDARY.getConstant());
        hierarchyType.select(PRIMARY.getConstant());
    }

    /**
     * Adds the result table
     *
     * @return Panel containing the result table
     */
    private Panel addResultSection(final ExtFilterTable results) {
        Panel resultsPanel = UiUtils.addCommonPanel(RESULTS.getConstant());
        HorizontalLayout resultLayout = (HorizontalLayout) UiUtils.getLayout(HorizontalLayout.class);
        resultLayout.setSizeFull();
        resultLayout.addComponent(results);
        resultsPanel.setContent(resultLayout);
        configureResultTable(results, StringUtils.EMPTY);
        return resultsPanel;
    }

    /**
     * Configures the result table
     *
     * @param results The search result table for group lookup
     * @param indicator to indicate different sections or module. Pass "" if not
     * needed
     */
    @Override
    protected void configureResultTable(final ExtFilterTable results, final String indicator) {
        results.setVisibleColumns(TableHeaderColumnsUtil.HIERARCHY_LOOKUP_COLUMNS);
        results.setColumnHeaders(TableHeaderColumnsUtil.HIERARCHY_LOOKUP_HEADERS);
        results.setWidth(100, Unit.PERCENTAGE);
    }

    /**
     * No need to implement this method.
     */
    @Override
    protected void btnImportLogic() {
       
    }

    /**
     * No need to implement this method.
     */
    @Override
    protected void btnUpdateLogic() {
     
    }

    /**
     * No need to implement this method.
     */
    @Override
    protected void btnAddLogic() {
       
    }

    /**
     * No need to implement this method.
     */
    @Override
    protected void btnLookupAnotherSearchLogic() {
       
    }

    /**
     * No need to implement this method.
     */
    @Override
    protected void btnSubmitLogic() {
       
    }

    /**
     * No need to implement this method.
     */
    @Override
    protected void btnRemoveLogic() {
      
    }

    public OptionGroup getHierarchyType() {
        return hierarchyType;
    }

    /**
     * Override this to customize search logic in the extending classes
     */
    protected abstract void btnSearchLogic();

    /**
     * Override this to customize reset logic in the extending classes
     */
    protected abstract void btnResetLogic();

    @Override
    protected void btnLookupSearchLogic() {
        
    }

}
