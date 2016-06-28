package com.stpl.app.galforecasting.ui.form.lookups;

import com.stpl.app.galforecasting.utils.Constant;
import com.stpl.app.utils.UiUtils;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Window;

/**
 *
 * @author sriram
 */
public class AddDiscount extends Window {
    
    /**
     * The Constructor.
     */
    public AddDiscount(String title) {
        super(title);
        addStyleName(Constant.BOOTSTRAP_NM);
        addStyleName(Constant.BOOTSTRAP);
        center();
        setModal(true);
        setClosable(true);
        setWidth("500px");
        setHeight("200px");
        setResizable(false);
        setContent(addToContent());
    }
    
    /**
     * Adds the to content.
     *
     * @return the panel
     */
    private HorizontalLayout addToContent() {
        final HorizontalLayout content = (HorizontalLayout) UiUtils.getLayout(HorizontalLayout.class);
        Label releaseLabel = new Label("<span style=\"text-align: center; font-size: 16px; font-weight: 600; color: #6495ED;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;This would be released by M8</span>");
        releaseLabel.setContentMode(ContentMode.HTML);
        content.addComponent(releaseLabel);
        content.setComponentAlignment(releaseLabel, Alignment.MIDDLE_CENTER);
        content.setHeight(100,Unit.PERCENTAGE);
        return content;
    }
}