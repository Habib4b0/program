/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.dao;

import com.stpl.app.accountclose.common.CommonLogic;
import com.stpl.ifs.util.HelperDTO;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.DAO;
import org.vaadin.addons.lazycontainer.OrderByColumn;
import org.vaadin.addons.lazycontainer.SearchCriteria;

/**
 *
 * @author vigneshkanna
 */
public class LoadDdlbDAO implements DAO<HelperDTO> {

    CommonLogic logic = new CommonLogic();
    String countFlag;
    String findFlag;
    String filterValue;
    private static final Logger LOGGER = Logger.getLogger(LoadDdlbDAO.class);

    public LoadDdlbDAO(String countFlag, String findFlag, String filterValue) {
        this.countFlag = countFlag;
        this.findFlag = findFlag;
        this.filterValue = filterValue;
    }

    public int count(SearchCriteria criteria) {
        LOGGER.info("inside count method" + filterValue);
        List filterTextList = new ArrayList();
        String filterText = StringUtils.trimToEmpty(criteria.getFilter()) + "%";
        if (StringUtils.EMPTY.equals(filterValue)) {
            filterTextList.add(filterText);
        } else {
            filterTextList.add(filterValue);
        }
        int count = logic.getDdlbCount(countFlag, filterTextList);
        LOGGER.info("exiting count method" + count);
        return count;
    }

    public List<HelperDTO> find(SearchCriteria criteria, int startIndex, int offset, List<OrderByColumn> columns) {
        LOGGER.info("inside find method" + filterValue);
        List<String> offSetList = new ArrayList<String>();
        String filterText = StringUtils.trimToEmpty(criteria.getFilter()) + "%";
        if (StringUtils.EMPTY.equals(filterValue)) {
            offSetList.add(filterText);
        } else {
            offSetList.add(filterValue);
        }
        offSetList.add(String.valueOf(startIndex));
        offSetList.add(String.valueOf(offset));
        List<HelperDTO> resultLList = logic.getDdlbList(findFlag, offSetList,true);
        LOGGER.info("exiting find method" + resultLList.size());
        return resultLList;
    }
}
