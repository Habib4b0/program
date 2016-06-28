package com.stpl.app.global.rebateschedule.ui.view;

import com.stpl.app.global.abstractsearch.ui.AbstractSearchForm;
import com.stpl.app.global.abstractsearch.util.ConstantUtil;
import org.jboss.logging.Logger;

import com.stpl.app.ui.AbstractView;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import org.asi.ui.extfilteringtable.ExtFilterTable;

// TODO: Auto-generated Javadoc
/**
 * The Class to implement the Rebate Schedule Search View.
 */
public class RebateScheduleSearchView extends AbstractView {

    /**
     * The Constant NAME.
     */
    public static final String NAME = "";

    /**
     * The table.
     */
    private final ExtFilterTable table = new ExtFilterTable();

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(RebateScheduleAddView.class.getName());

    /**
     * Instantiates a new rebate schedule search view.
     */
    public RebateScheduleSearchView() throws SystemException, PortalException, Exception {

        super();
        setStyleName("bootstrap-company");
        setStyleName("bootstrap");
        setStyleName("bootstrap-bb");
        LOGGER.info("inside Search View");
        setSpacing(true);
        table.setWidth(100, UNITS_PERCENTAGE);
        addComponent(new AbstractSearchForm(ConstantUtil.REBATE_SCHEDULE_MASTER));
    }

    /**
     * @return the table
     */
    public ExtFilterTable getTable() {
        return table;
    }

    /**
     * Method used for enter method.
     *
     * @param event the event
     */
    public void enter(final ViewChangeEvent event) {
        table.setWidth(100, UNITS_PERCENTAGE);
    }

}
