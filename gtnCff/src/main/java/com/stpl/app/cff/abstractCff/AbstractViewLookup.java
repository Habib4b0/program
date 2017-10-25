/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.abstractCff;

import com.vaadin.server.Sizeable;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import java.util.ResourceBundle;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import com.stpl.app.cff.util.UiUtils;
import static com.stpl.app.cff.util.Constants.LabelConstants.*;
import com.stpl.ifs.ui.util.NumericConstants;

/**
 *
 * @author mohamed.hameed
 */
public abstract class AbstractViewLookup extends Window {

    /**
     * Object for ResourceBundle
     */
    private ResourceBundle resourceBundle;

    /**
     * Constructor for AbstractViewLookup
     *
     * @param windowName the window Name for lookup
     */
    public AbstractViewLookup(final String windowName) {
        super(windowName);
        setClosable(true);
        setModal(true);
        center();
    }

    /**
     * Layout template for private/public view lookup
     *
     * @param viewName The view name text field reference
     * @param btnSearch The search button reference
     * @param btnReset The reset button reference
     * @param btnSelect The select button reference
     * @param btnClose The close button reference
     * @param results The result table reference
     * @return Layout template for private/public view lookup
     */
    public VerticalLayout buildViewLookup(final TextField viewName, final Button btnSearch, final Button btnReset, final Button btnSelect, final Button btnClose, final ExtFilterTable results) {
        VerticalLayout mainLayout = (VerticalLayout) UiUtils.getLayout(VerticalLayout.class);
        mainLayout.addComponent(addSearchCriteria(viewName));
        mainLayout.addComponent(addSearchButtons(btnSearch, btnReset));
        mainLayout.addComponent(addResultSection(results));
        mainLayout.addComponent(addFooterButtons(btnSelect, btnClose));
        return mainLayout;
    }

    /**
     * Adds the search criteria section containing the view name textbox
     *
     * @param viewName The viewName text field
     * @return Panel containing the search criteria
     */
    private Panel addSearchCriteria(final TextField viewName) {
        Panel searchCriteria = UiUtils.addCommonPanel(VIEW_SEARCH.getConstant());
        HorizontalLayout hLayout = (HorizontalLayout) UiUtils.getLayout(HorizontalLayout.class);
        GridLayout layout = new GridLayout(NumericConstants.TWO, NumericConstants.ONE);
        Label viewNameLabel = UiUtils.makeLabel(VIEW_NAME.getConstant());
        layout.setMargin(false);
        layout.setSpacing(false);
        viewNameLabel.setWidth(NumericConstants.HUNDRED, Sizeable.Unit.PIXELS);
        layout.addComponent(viewNameLabel);
        layout.addComponent(viewName);
        hLayout.addComponent(layout);
        hLayout.setMargin(true);
        hLayout.setSpacing(false);
        searchCriteria.setContent(hLayout);
        return searchCriteria;
    }

    /**
     * Adds the search criteria buttons to the screen
     *
     * @param btnSearch The search button
     * @param btnReset The search criteria reset button
     * @return The layout containing both the buttons
     */
    private HorizontalLayout addSearchButtons(final Button btnSearch, final Button btnReset) {
        HorizontalLayout searchButtonsLayout = (HorizontalLayout) UiUtils.getLayout(HorizontalLayout.class);
        btnSearch.addClickListener(new Button.ClickListener() {

            public void buttonClick(Button.ClickEvent event) {
                btnSearchLogic();
            }
        });
        searchButtonsLayout.addComponent(btnSearch);
        searchButtonsLayout.addComponent(btnReset);
        searchButtonsLayout.setMargin(false);
        return searchButtonsLayout;
    }

    /**
     * Adds the result table to the screen
     *
     * @param results The table
     * @return The Panel containing the results table
     */
    private Panel addResultSection(final ExtFilterTable results) {
        Panel resultSection = UiUtils.addCommonPanel(RESULTS.getConstant());
        VerticalLayout layout = (VerticalLayout) UiUtils.getLayout(VerticalLayout.class);
        resourceBundle = ResourceBundle.getBundle("configurations/default");
        float tableWidth = Float.valueOf(resourceBundle.getString("view_table_width"));
        results.setWidth(tableWidth, Sizeable.Unit.valueOf(String.valueOf(resourceBundle.getString("default_unit"))));
        layout.setSizeFull();
        layout.addComponent(results);
        resultSection.setContent(layout);
        return resultSection;
    }

    /**
     * Adds the select and close buttons
     *
     * @param btnSelect The select button
     * @param btnClose The close button
     * @return The layout containing the footer buttons
     */
    private HorizontalLayout addFooterButtons(final Button btnSelect, final Button btnClose) {
        HorizontalLayout footerButtonsLayout = (HorizontalLayout) UiUtils.getLayout(HorizontalLayout.class);
        btnSelect.addClickListener(new Button.ClickListener() {

            public void buttonClick(Button.ClickEvent event) {
                btnSelectLogic();
            }
        });
        btnClose.addClickListener(new Button.ClickListener() {

            public void buttonClick(Button.ClickEvent event) {
                btnCloseLogic();
            }
        });
        footerButtonsLayout.addComponent(btnSelect);
        footerButtonsLayout.addComponent(btnClose);
        footerButtonsLayout.setMargin(false);
        return footerButtonsLayout;
    }

    /**
     * Search button logic
     */
    public abstract void btnSearchLogic();

    /**
     * Select button logic
     */
    public abstract void btnSelectLogic();

    /**
     * Close button logic
     */
    public abstract void btnCloseLogic();

    /**
     * Override this to configure the components in extending classes
     */
    protected abstract void configureField();
}
