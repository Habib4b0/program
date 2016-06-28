/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.lazyload;

import com.stpl.app.accountclose.dto.MarketTypeDdlbDto;
import java.util.List;
import org.vaadin.addons.lazycontainer.DAO;
import org.vaadin.addons.lazycontainer.OrderByColumn;
import org.vaadin.addons.lazycontainer.SearchCriteria;

/**
 *
 * @author alok.v
 */
public class MarketTypeDdlbDao implements DAO<MarketTypeDdlbDto> {

    public int count(SearchCriteria sc) {
       throw new UnsupportedOperationException("Not supported yet."); 
    }

    public List<MarketTypeDdlbDto> find(SearchCriteria sc, int i, int i1, List<OrderByColumn> list) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
}
