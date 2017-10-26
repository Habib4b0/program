/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.promotetptocontract.lazyload;

import com.stpl.app.gcm.promotetptocontract.logic.PromoteTPLogic;
import com.stpl.app.gcm.util.Constants;
import com.stpl.ifs.util.HelperDTO;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.vaadin.addons.lazycontainer.DAO;
import org.vaadin.addons.lazycontainer.OrderByColumn;
import org.vaadin.addons.lazycontainer.SearchCriteria;

/**
 *
 * @author alok.v
 */
public class LoadDropdownListDAO implements DAO<HelperDTO> {

    PromoteTPLogic logic = new PromoteTPLogic();
    String countFlag;
    String findFlag;

    public LoadDropdownListDAO(String countFlag, String findFlag) {
        this.countFlag = countFlag;
        this.findFlag = findFlag;
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
        if (startIndex != 0 && offset != 0) {
            offSetList.add(filterText);
            offSetList.add(String.valueOf(startIndex));
            offSetList.add(String.valueOf(offset));
            List<HelperDTO> resultLList = logic.getDdlbList(findFlag, offSetList);
            return resultLList;
        } else {
            List<HelperDTO> resultList = new ArrayList<HelperDTO>();
            HelperDTO defaultValue = new HelperDTO(0, Constants.IndicatorConstants.SELECT_ONE.getConstant());
            resultList.add(defaultValue);
            return resultList;

        }
        
    }
}
