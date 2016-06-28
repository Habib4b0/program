package com.stpl.app.adminconsole.discount.ui.view;

import com.stpl.app.adminconsole.abstractsearch.ui.AbstractSearchForm;
import com.stpl.app.adminconsole.common.dto.SessionDTO;
import com.stpl.app.adminconsole.discount.ui.form.DiscountSearchIndex;
import static com.stpl.app.adminconsole.filemanagement.ui.view.FileManagementIndexView.LOGGER;
import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.stpl.app.adminconsole.util.ErrorCodeUtil;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class DiscountSearchView.
 */
public class DiscountSearchView extends VerticalLayout implements View {

    /**
     * The Constant NAME.
     */
    public static final String NAME = ConstantsUtils.EMPTY;

    private DiscountSearchIndex discountSearchIndex;

    SessionDTO sessionDTO;

    /**
     * Instantiates a new discount search view.
     */
    public DiscountSearchView(SessionDTO sessionDTO) {
        super();
        try {
            setSpacing(true);
            this.sessionDTO = sessionDTO;
            addComponent(new AbstractSearchForm(ConstantsUtils.DEDUCTION_GROUPING, sessionDTO));
            setStyleName("bootstrap");
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    /**
     * (non-Javadoc)
     *
     * @see
     * com.vaadin.navigator.View#enter(com.vaadin.navigator.ViewChangeListener.ViewChangeEvent)
     */
    public void enter(final ViewChangeListener.ViewChangeEvent event) {

        try {
            setStyleName("bootstrap");
            setSpacing(true);

        } catch (Exception ex) {
            final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
            LOGGER.error(ex);
        }

    }

}
