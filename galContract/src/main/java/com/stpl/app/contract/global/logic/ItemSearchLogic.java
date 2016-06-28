package com.stpl.app.contract.global.logic;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;

import com.stpl.app.contract.dao.impl.ItemSearchLogicDAOImpl;
import com.stpl.app.contract.global.dto.SearchItemForm;
import com.stpl.app.model.HelperTable;
import com.stpl.domain.contract.contractdashboard.ItemDAO;
import com.stpl.domain.contract.contractdashboard.globalcontract.ItemLogic;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.util.BeanItemContainer;


/**
 * Logic for Item Search.
 *
 * @author
 */
public class ItemSearchLogic extends BeanItemContainer<SearchItemForm> implements ItemLogic{

    /**
     * The dao.
     */
    private final ItemDAO dao = new ItemSearchLogicDAOImpl();
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(ItemSearchLogic.class);

    /**
     * Default Constructor.
     */
    public ItemSearchLogic() {
        super(SearchItemForm.class);

    }

    /**
     * to get Item Type
     *
     * @param listType
     * @return
     * @throws SystemException
     */
    public List<HelperDTO> getItemType(final String listType) throws SystemException {
        LOGGER.info("Entering getItemType()");
        final List<HelperDTO> helperList = new ArrayList<HelperDTO>();
        final List<HelperTable> list = dao.findByHelperTableDetails(listType);

        if (list != null) {
            for (int i = 0; i < list.size(); i++) {

                final HelperTable helperTable = (HelperTable) list.get(i);
                helperList.add(new HelperDTO(helperTable.getHelperTableSid(), helperTable.getDescription()));

            }
        }
        LOGGER.info("End of getItemType()");
        return helperList;
    }

    /**
     * Gets the dao.
     *
     * @return the dao
     */
    public ItemDAO getDao() {
        return dao;
    }
}
