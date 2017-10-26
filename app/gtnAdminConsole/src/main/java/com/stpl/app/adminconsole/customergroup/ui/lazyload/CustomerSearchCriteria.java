/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.customergroup.ui.lazyload;

import org.vaadin.addons.lazycontainer.SearchCriteria;

/**
 *
 * @author mohamed
 */
public class CustomerSearchCriteria implements SearchCriteria {

    private int lastCount;
    private String filter;
    public int getLastCount() {
        return lastCount;
    }

    public void setLastCount(int lastCount) {
        this.lastCount = lastCount;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    @Override
    public boolean isDirty() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setDirty(boolean bln) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
