/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.itemgroup.ui.lazyload;

import com.stpl.app.adminconsole.itemgroup.dto.ItemGroupDTO;
import com.stpl.app.adminconsole.itemgroup.logic.ItemGroupLogic;
import com.stpl.ifs.ui.CustomFieldGroup;
import java.util.ArrayList;
import java.util.List;
import org.vaadin.addons.lazycontainer.BeanDAO;
import org.vaadin.addons.lazycontainer.BeanSearchCriteria;
import org.vaadin.addons.lazycontainer.OrderByColumn;

/**
 *
 * @author mohamed
 */
public class ItemGroupDAO implements BeanDAO<ItemGroupDTO> {

    CustomFieldGroup itemGroupForm;
    String flag;
    int count;

    private final ItemGroupLogic logic = new ItemGroupLogic();

    private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(ItemGroupDAO.class);

    public ItemGroupDAO(final CustomFieldGroup itemGroupForm, final String flag) {
        this.itemGroupForm = itemGroupForm;
        this.flag = flag;

    }

    public int count(BeanSearchCriteria criteria) {
        try {

            itemGroupForm.commit();
            count = logic.getLazySearchCount(itemGroupForm, flag, criteria);
            return count;

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return 0;
    }

    /**
     * find
     *
     * @param criteria
     * @param startIndex
     * @param offset
     * @param columns
     * @return list
     */
    public List<ItemGroupDTO> find(BeanSearchCriteria criteria, int startIndex, int offset, List<OrderByColumn> columns) {
        try {
            final List<ItemGroupDTO> searchResults = new ArrayList<>();
            return searchResults;
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return new ArrayList<>();
    }
}
