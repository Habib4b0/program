package com.stpl.app.transactional.common.ui.form;

import com.stpl.app.transactional.common.abstractForm.AbstractComponentCreater;
import com.stpl.app.transactional.common.ui.view.SearchView;
import org.jboss.logging.Logger;
import com.stpl.app.util.ConstantUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button.ClickEvent;
import java.text.ParseException;

/**
 * The Class ViewForm.
 */
public final class ViewForm extends AbstractComponentCreater {

    /**
     * The Constant NAME.
     */
    public static final String NAME = ConstantUtil.VIEW;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(ViewForm.class);

    /**
     * ViewForm
     *
     * @param ob
     * @param valueList
     * @throws PortalException
     * @throws SystemException
     */
    public ViewForm(final Object[] ob, Object[] valueList) throws PortalException, SystemException, ParseException {
        super(ob, valueList);
        LOGGER.debug("Entering ViewForm");
    }

    /**
     * Back Button Listener
     *
     * @param event
     * @throws Exception
     */
    @Override
    protected void clickListeners(ClickEvent event)  {
        if (ConstantUtil.INVALID_RECORD_COUNT.equalsIgnoreCase((String) VaadinSession.getCurrent().getAttribute(ConstantUtil.SCREEN_NAME))) {
        getUI().getWindows().iterator().next().close();
        }
        LOGGER.debug("Entering buttonClick for backButton");
        getUI().getNavigator().navigateTo(SearchView.NAME);
        LOGGER.debug("Ends buttonClick for backButton");

    }
}
