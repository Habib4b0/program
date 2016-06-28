/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.global.abstractsearch.view;

import com.stpl.app.global.abstractsearch.ui.AbstractSearchForm;
import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.ui.AbstractView;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.jboss.logging.Logger;

/**
 *
 * @author nadhiya
 */
public class AbstractSearchView extends AbstractView{
    /** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(AbstractSearchView.class);

	/** The Constant NAME. */
	public static final String NAME = "";
        public String moduleName;

	/** The table. */
	private final ExtFilterTable table = new ExtFilterTable();
       public static boolean flag = false;
       SessionDTO sessionDTO; 

	/**
	 * Gets the table.
	 *
	 * @return the table
	 */
	public ExtFilterTable getTable() {
		return table;
	}

	/**
	 * The Constructor.
	 */
	public AbstractSearchView(String moduleName) throws PortalException, SystemException, Exception {
        super();
        this.moduleName = moduleName;
        setSpacing(true);
        setStyleName("bootstrap-company");
        setStyleName("bootstrap");
        setStyleName("bootstrap-bb");
        LOGGER.info("inside Search View");
        addComponent(new AbstractSearchForm(moduleName));

    }
	   public AbstractSearchView(String moduleName, final SessionDTO sessionDTO) throws PortalException, SystemException, Exception {
        super();
        this.moduleName = moduleName;
        this.sessionDTO=sessionDTO;
        setSpacing(true);
        setStyleName("bootstrap-company");
        setStyleName("bootstrap");
        setStyleName("bootstrap-bb");
        LOGGER.info("inside Search View");
        addComponent(new AbstractSearchForm(moduleName,sessionDTO));

    }

	/**
	 * Enter method is executed when page goes to form.
	 *
	 * @param event
	 *            the event
	 */
	   @Override
    public void enter(final ViewChangeEvent event) {
        try {
            final Date tempDate = new Date();
            final SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
            final SimpleDateFormat fmtID = new SimpleDateFormat("MMddyyyyhhmmssms");
            sessionDTO.setSessionDate(fmt.format(tempDate));
            sessionDTO.setUiSessionId(fmtID.format(tempDate));
              if (flag) {
            removeAllComponents();
            addComponent(new AbstractSearchForm(moduleName,sessionDTO));
              }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        table.setWidth(100, UNITS_PERCENTAGE);
    }

    public static boolean isFlag() {
        return flag;
    }

    public static void setFlag(boolean flag) {
        AbstractSearchView.flag = flag;
    }
    
}
