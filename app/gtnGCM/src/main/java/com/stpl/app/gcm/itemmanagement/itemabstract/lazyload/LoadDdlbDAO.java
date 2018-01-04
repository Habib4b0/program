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
import org.asi.ui.addons.lazycontainer.DAO;
import org.asi.ui.addons.lazycontainer.OrderByColumn;
import org.asi.ui.addons.lazycontainer.SearchCriteria;

/**
 *
 * @author mohamed.hameed
 */
public class LoadDdlbDAO implements DAO<HelperDTO> {

    private AbstractLogic logic = AbstractLogic.getInstance();
    private String countFlag;
    private String findFlag;
    private Boolean isFilter;

    public LoadDdlbDAO(final List input, final boolean isFilter) {
        this.countFlag = String.valueOf(input.get(0));
        this.findFlag = String.valueOf(input.get(1));
        this.isFilter = isFilter;
    }

    @Override
    public int count(SearchCriteria criteria) {
        List<String> filterTextList = new ArrayList<>();
        String filterText = StringUtils.trimToEmpty(criteria.getFilter()) + "%";
        filterTextList.add(filterText);
        int count = logic.getDdlbCount(countFlag, filterTextList);
        return count;
    }

    @Override
    public List<HelperDTO> find(SearchCriteria criteria, int startIndex, int offset, List<OrderByColumn> columns) {
        List<String> offSetList = new ArrayList<>();
        String filterText = StringUtils.trimToEmpty(criteria.getFilter()) + "%";
        offSetList.add(filterText);
        offSetList.add(String.valueOf(startIndex));
        offSetList.add(String.valueOf(offset));
        List<HelperDTO> resultLList = logic.getDdlbList(findFlag, offSetList, isFilter);
        return resultLList;
    }
}
