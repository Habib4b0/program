/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.itemmanagement.itemabstract.lazyload;

import com.stpl.app.gcm.itemmanagement.itemabstract.logic.AbstractLogic;
import com.stpl.ifs.util.HelperDTO;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.vaadin.addons.lazycontainer.DAO;
import org.vaadin.addons.lazycontainer.OrderByColumn;
import org.vaadin.addons.lazycontainer.SearchCriteria;

/**
 *
 * @author mohamed.hameed
 */
public class LoadDdlbDAO implements DAO<HelperDTO> {

    AbstractLogic logic = AbstractLogic.getInstance();
    String countFlag;
    String findFlag;
    Boolean isFilter;

    public LoadDdlbDAO(final List input, final boolean isFilter) {
        this.countFlag = String.valueOf(input.get(0));
        this.findFlag = String.valueOf(input.get(1));
        this.isFilter = isFilter;
    }

    public int count(SearchCriteria criteria) {
        List<String> filterTextList = new ArrayList<String>();
        String filterText = StringUtils.trimToEmpty(criteria.getFilter()) + "%";
        filterTextList.add(filterText);
        int count = logic.getDdlbCount(countFlag, filterTextList);
        return count;
    }

    public List<HelperDTO> find(SearchCriteria criteria, int startIndex, int offset, List<OrderByColumn> columns) {
        List<String> offSetList = new ArrayList<String>();
        String filterText = StringUtils.trimToEmpty(criteria.getFilter()) + "%";
        offSetList.add(filterText);
        offSetList.add(String.valueOf(startIndex));
        offSetList.add(String.valueOf(offset));
        List<HelperDTO> resultLList = logic.getDdlbList(findFlag, offSetList, isFilter);
        return resultLList;
    }
}
