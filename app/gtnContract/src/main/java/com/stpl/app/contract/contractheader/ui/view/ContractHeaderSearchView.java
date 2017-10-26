package com.stpl.app.contract.contractheader.ui.view;

import com.stpl.app.contract.abstractsearch.ui.AbstractSearchForm;
import com.stpl.app.contract.abstractsearch.util.ConstantUtil;
import com.stpl.app.contract.common.dto.SessionDTO;
import com.stpl.app.contract.contractheader.dto.ContractHeaderCriteria;
import com.stpl.app.contract.contractheader.dto.ContractHeaderDAO;
import com.stpl.app.contract.contractheader.dto.ContractMasterDTO;
import com.stpl.app.contract.util.Constants;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.VerticalLayout;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.LazyBeanItemContainer;

/**
 * Class holds the searchForm and SearchResults form that is added to the UI.
 *
 * @author
 */
public class ContractHeaderSearchView extends VerticalLayout implements View {

    /**
     * The Constant NAME.
     */
    public static final String NAME = "";
    SessionDTO sessionDTO;
    public static boolean flag = false;
    /**
     * The search resultbeans.
     */
    private final LazyBeanItemContainer searchResultbeans = new LazyBeanItemContainer(ContractMasterDTO.class, new ContractHeaderDAO(), new ContractHeaderCriteria());
    /**
     * The table.
     */
    private final ExtFilterTable table = new ExtFilterTable();
    
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(ContractHeaderSearchView.class);

    /**
     * Gets the search resultbeans.
     *
     * @return the search resultbeans
     */
    public LazyBeanItemContainer getSearchResultbeans() {
        return searchResultbeans;
    }

    /**
     * Gets the table.
     *
     * @return the table
     */
    public ExtFilterTable getTable() {
        return table;
    }

    /**
     * Default constructor.
     */
    public ContractHeaderSearchView(final SessionDTO sessionDTO) throws SystemException, PortalException {
        super();
        LOGGER.debug("Enters ContractHeaderSearchView constructor");
        addStyleName("bootstrap");
        addStyleName("bootstrap-bb");
        setSpacing(true);
        this.sessionDTO=sessionDTO;
        addComponent(new AbstractSearchForm(ConstantUtil.CONTRACT_HEADER,this.sessionDTO));
        LOGGER.debug(" ContractHeaderSearchView constructor ends");
    }

    /**
     * Method over ridden while implementing View.Configures the table.
     *
     * @param event - ViewChangeEvent
     */
    public void enter(final ViewChangeEvent event) {
        VaadinSession.getCurrent().setAttribute(Constants.CONTRACT_MASTER_DTO, null);
        try {
            if (flag) {
                removeAllComponents();
                addComponent(new AbstractSearchForm(ConstantUtil.CONTRACT_HEADER, sessionDTO));
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }
}
