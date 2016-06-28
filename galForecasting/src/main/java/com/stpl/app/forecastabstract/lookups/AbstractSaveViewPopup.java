/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.forecastabstract.lookups;

import com.stpl.app.galforecasting.utils.Constant;
import static com.stpl.app.utils.Constants.LabelConstants.*;
import com.stpl.app.utils.UiUtils;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import org.asi.ui.extfilteringtable.ExtFilterTable;

/**
 *
 * @author soundarrajan
 */
public abstract class AbstractSaveViewPopup extends AbstractLookup {

    private TextField viewName;

    private OptionGroup viewType;

    private Button viewAddButton;

    private Button viewUpdateButton;

    /**
     * Save view popup constructor
     *
     * @param windowName Window name for the popup
     */
    public AbstractSaveViewPopup(String windowName) {
        super(windowName);
    }

    public VerticalLayout buildPopupScreen(TextField viewName, OptionGroup viewType) {
        this.viewName = viewName;
        this.viewType = viewType;
        initializeComponents();
        return build();
    }

    private VerticalLayout build() {
        VerticalLayout verticalLayout = (VerticalLayout) UiUtils.getLayout(VerticalLayout.class);
        HorizontalLayout viewLayout = (HorizontalLayout) UiUtils.getLayout(HorizontalLayout.class);
        HorizontalLayout buttonLayout = (HorizontalLayout) UiUtils.getLayout(HorizontalLayout.class);
        verticalLayout.addComponent(viewType);
        viewLayout.addComponent(UiUtils.makeLabel(VIEW_NAME.getConstant()));
        viewLayout.addComponent(viewName);
        buttonLayout.addComponent(getCancelButton());
        buttonLayout.addComponent(viewAddButton);
        buttonLayout.addComponent(viewUpdateButton);
        viewLayout.setMargin(false);
        buttonLayout.setMargin(false);
        verticalLayout.addComponent(viewLayout);
        verticalLayout.addComponent(buttonLayout);
        return verticalLayout;
    }

    /**
     * Initializes and configures the components
     */
    private void initializeComponents() {

        initializePrivatePublic();
        setViewAddButton(getAddButton());
        setViewUpdateButton(getUpdateButton());
    }

    /**
     * Initializes and configures privatePublic option group
     */
    private void initializePrivatePublic() {

        viewType.addItem(PRIVATE.getConstant());
        viewType.addItem(PUBLIC.getConstant());
        viewType.select(PRIVATE.getConstant());
        viewType.setStyleName(Constant.HORIZONTAL);
    }


    /**
     * This method need not be implemented as no search needs to be performed
     */
    @Override
    protected void btnLookupSearchLogic() {
       
    }

    /**
     * This method need not be implemented as no import needs to be performed
     */
    @Override
    protected void btnImportLogic() {
       
    }

    /**
     * This method need not be implemented as no select needs to be performed
     */
    @Override
    protected void btnLookupSelectLogic() {
       
    }

    /**
     * This method need not be implemented as no result table needs to be added
     */
    @Override
    protected void configureResultTable(final ExtFilterTable results, final String indicator) {
      
    }

    /**
     * This method need not be implemented as no result table needs to be added
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

    public Button getViewAddButton() {
        return viewAddButton;
    }

    public void setViewAddButton(Button viewAddButton) {
        this.viewAddButton = viewAddButton;
    }

    public Button getViewUpdateButton() {
        return viewUpdateButton;
    }

    public void setViewUpdateButton(Button viewUpdateButton) {
        this.viewUpdateButton = viewUpdateButton;
    }
    
}
