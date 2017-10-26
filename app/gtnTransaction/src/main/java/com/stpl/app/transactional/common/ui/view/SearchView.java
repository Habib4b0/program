package com.stpl.app.transactional.common.ui.view;

import com.stpl.app.transactional.common.logic.CommonLogic;
import com.stpl.app.transactional.common.dto.DetailsDTO;
import com.stpl.app.transactional.common.ui.form.SearchForm;
import com.stpl.app.util.ConstantUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.UserError;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.VerticalLayout;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 * The Class SearchView.
 */
public class SearchView extends VerticalLayout implements View {

	/**
	 * The Constant NAME.
	 */
	public static final String NAME = StringUtils.EMPTY;
	/**
	 * Initializing Logger.
	 */
	private static final Logger LOGGER = Logger.getLogger(SearchView.class);
        
        CommonLogic commonLogic = new CommonLogic();

	/**
	 * The Constructor.
	 */
	public SearchView() throws PortalException, SystemException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException{
		super();
		LOGGER.debug("Entering ForecastSearchView");
		setStyleName("bootstrap-company");
		setSpacing(true);
        Object[] ob = commonLogic.getFiledNames(ConstantUtil.INVENTORY_WITHDRAWAL_SUMMARY.equals((String) VaadinSession.getCurrent().getAttribute(ConstantUtil.SCREEN_NAME)) ? ConstantUtil.INVENTORY: (String) VaadinSession.getCurrent().getAttribute(ConstantUtil.SCREEN_NAME),ConstantUtil.SEARCH);
        VaadinSession.getCurrent().setAttribute(ConstantUtil.TABCOUNT, Integer.valueOf(ob[NumericConstants.TWO].toString()));
     
        List<DetailsDTO> primaryValueList = (List<DetailsDTO>) ob[NumericConstants.ONE];
        VaadinSession.getCurrent().setAttribute(ConstantUtil.TABLE_NAME, primaryValueList.get(0).getTableName());
        addComponent(new SearchForm((List<DetailsDTO>) ob[0], primaryValueList));
		setComponentError(new UserError(StringUtils.EMPTY));
		LOGGER.debug("Ends ForecastSearchView");
                
	}

	/**
	 * Default method.
	 *
     * @param event the event
	 */
	public void enter(final ViewChangeEvent event) {

		LOGGER.debug("Entering enter");
		LOGGER.debug("Ends enter");

	}
}
