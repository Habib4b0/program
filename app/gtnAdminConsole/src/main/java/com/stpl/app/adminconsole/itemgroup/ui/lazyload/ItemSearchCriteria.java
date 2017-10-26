/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.itemgroup.ui.lazyload;

import com.vaadin.data.Container;
import java.util.Set;
import org.vaadin.addons.lazycontainer.BeanSearchCriteria;

/**
 *
 * @author mohamed
 */
public class ItemSearchCriteria implements BeanSearchCriteria {

    private boolean customDirty;
    private String searchText;
    private String description;
    private int lastCount;
    private Set<Container.Filter> filter;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    @Override
    public int getLastCount() {
        return lastCount;
    }

    @Override
    public void setLastCount(int lastCount) {
        this.lastCount = lastCount;
    }

    @Override
    public Set<Container.Filter> getFilters() {
        return filter;
    }

    @Override
    public void setFilters(Set<Container.Filter> filter) {

        this.filter = filter;
    }

    public boolean isCustomDirty() {
        return customDirty;
    }

    public void setCustomDirty(boolean customDirty) {
        this.customDirty = customDirty;
    }

    public Set<Container.Filter> getFilter() {
        return filter;
    }

    public void setFilter(Set<Container.Filter> filter) {
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
