/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.asi.ui.extfilteringtable.paged.logic;

import com.vaadin.data.Container;
import com.vaadin.data.Container.Hierarchical;
import com.vaadin.data.Container.Indexed;
import com.vaadin.data.Property;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.asi.ui.extfilteringtable.paged.ExtPagedFilterTreeTable;
import org.asi.ui.extfilteringtable.paged.PagedTreeTableBase;


/**
 * The Class ContainerLogic.
 *
 * @author Abhiram
 * @param <T> the generic type
 */
public abstract class ContainerLogic<T> extends PageTreeLogicBase {

    /** The level value. */
    private int levelValue = 1;

    /** The level list. */
    private List levelList = new ArrayList();

    /** The start. */
    private int start = 0;
    
    /** The end. */
    private int end = tempPageLength;

    /** The extra itemlist. */
    private List extraItemlist = new ArrayList();
    
    /** The default load extra item. */
    private boolean defaultLoadExtraItem = false;
    
    /** The static expandable level list. */
    private List staticExpandableLevelList = new ArrayList();
    
    /** The static last parent. */
    private Object staticLastParent = new Object();
    
    /** The static data possible. */
    private boolean staticDataPossible = false;
    
    /** The static count. */
    private int staticCount = 0;
    
    /** The need static count. */
    private boolean needStaticCount = true;

    /**
     * Load data.
     *
     * @param start the start
     * @param offset the offset
     * @return the list
     * @throws SQLException the SQL exception
     */
    public abstract List<T> loadData(int start, int offset) throws SQLException;

    /**
     * Configure bean container.
     *
     * @param itemId the item id
     * @param datasource the datasource
     */
    public abstract void configureBeanContainer(Object itemId, Container datasource);

    /**
     * Load static data.
     *
     * @param start the start
     * @param offset the offset
     * @return the list
     * @throws SQLException the SQL exception
     */
    protected List<T> loadStaticData(int start, int offset) throws SQLException {
        return Collections.EMPTY_LIST;
    }

    /**
     * Gets the static data count.
     *
     * @return the static data count
     * @throws SQLException the SQL exception
     */
    protected int getStaticDataCount() throws SQLException {
        return 0;
    }

    /**
     * Need to add static list.
     *
     * @param itemId the item id
     * @return true, if successful
     */
    protected boolean needToAddStaticList(Object itemId) {
        return false;
    }

    /**
     * Gets the control table.
     *
     * @return the control table
     */
    @Override
    public ExtPagedFilterTreeTable getControlTable() {
        return (ExtPagedFilterTreeTable) super.getControlTable();
    }

    /**
     * Extra current page logic.
     *
     * @param recordPos the record pos
     */
    public void extraCurrentPageLogic(int recordPos) {
        if (isDefaultLoadExtraItem()) {
            loadExtraContainer(recordPos);
        }
    }

    /**
     * Creates the current page.
     *
     * @param currentPage the current page
     * @return the int
     */
    @Override
    protected int createCurrentPage(int currentPage) {
        recordCount = 0;
        createStaticCount();
        recordCount += getStaticCount();
        if (levelList != null) {
            recordCount += levelList.size();
        }
        dataCount = getCount();
        recordCount += dataCount;
        int recordPos = (int) recordCount;
        if (isDefaultLoadExtraItem()) {
            recordCount += getExtraItemListAfterParentAddition().size();
        }
        int x = getTotalAmountOfPages();
        totalPagesLabel.setValue(String.valueOf(x));
        if (currentPage <= x) {
            this.currentPage = currentPage;
        } else if (currentPage <= 0) {
            this.currentPage = 1;
        } else {
            this.currentPage = x;
        }
        return recordPos;
    }
    
    /**
     * Load current page.
     *
     * @param recordPos the record pos
     */
    @Override
    protected void loadCurrentPage(int recordPos) {
        if (levelList != null) {
            int mayBeAdded = 0;
            int lvlstrt = 0;
            int lvlend = 0;
            for (int i = 1; i < getCurrentPage(); i++) {
                lvlstrt = lvlstrt + getItemsPerPage();
            }
            if (isStaticDataPossible()) {
                if (staticCount > 0) {
                    int lvl = staticCount;
                    if (lvlstrt < lvl) {
                        lvlend = lvl - lvlstrt;
                        if (lvlend > getItemsPerPage()) {
                            lvlend = getItemsPerPage();
                        }
                        if (lvlend > 0) {
                            loadStaticDataContainer(lvlstrt, lvlend);
                        }
                    }
                    mayBeAdded += staticCount;
                }
            }
            if (getControlTable().size() < getItemsPerPage()) {
                int lvlstartList = lvlstrt - mayBeAdded;
                if (lvlstartList < 0) {
                    lvlstartList = 0;
                }
                int lvl = levelList.size();
                int offset = getItemsPerPage() - getControlTable().size();
                if (lvlstartList < lvl) {
                    lvlend = lvl - lvlstartList;
                    if (lvlend > offset) {
                        lvlend = offset;
                    }
                    if (lvlend > 0) {
                        loadParentHierarchy(lvlstartList, lvlend);
                    }
                }
                mayBeAdded += levelList.size();
                if (getControlTable().size() < getItemsPerPage()) {
                    if (dataCount > 0) {
                        offset = getItemsPerPage() - getControlTable().size();
                        int lvlstartData = lvlstrt - mayBeAdded;
                        if (lvlstartData < 0) {
                            lvlstartData = 0;
                        }
                        if (lvlstartData < dataCount) {
                            lvlend = dataCount - lvlstartData;
                            if (lvlend > offset) {
                                lvlend = offset;
                            }
                            if (lvlend > 0) {
                                loadDataContainer(lvlstartData, lvlend);
                            }
                        }
                    }
                }
            }
        }
        extraCurrentPageLogic(recordPos);
    }

    /**
     * Load static data container.
     *
     * @param start the start
     * @param offset the offset
     */
    private void loadStaticDataContainer(int start, int offset) {
        try {
            staticExpandableLevelList.clear();
            List list = loadStaticData(start, offset);
            if (list != null) {
                for (int i = 0; i < list.size() && i < offset; i++) {
                    Object object = list.get(i);
                    Object itemId = configureContainer(object, container);
                    boolean needed = needToAddStaticList(itemId);
                    if (needed) {
                        addStaticExpandableLevel(itemId);
                    }
                }
            }
        } catch (Property.ReadOnlyException ex) {
            ex.printStackTrace();
        } catch (UnsupportedOperationException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Load data container.
     *
     * @param start the start
     * @param offset the offset
     */
    private void loadDataContainer(int start, int offset) {
        try {
            List list = loadData(start, offset);
            if (list != null) {
                for (int i = 0; i < list.size() && i < offset; i++) {
                    Object object = list.get(i);
                    Object itemId = configureContainer(object, container);
                    if (lastParent != null) {
                        ((Container.Hierarchical) container).setParent(itemId, lastParent);
                    }
                }
            }
            setStart(start);
            setEnd(offset);
        } catch (Property.ReadOnlyException ex) {
            ex.printStackTrace();
        } catch (UnsupportedOperationException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * adds the parent hierarchy for the pagination purpose.
     *
     * @param start the start
     * @param offset the offset
     * @return the int
     */
    private int loadParentHierarchy(int start, int offset) {

        int total = 0;
        if (levelList != null) {
            if (!levelList.isEmpty()) {
                if ((levelList.size() - start) < offset) {
                    offset = levelList.size() - start;
                }
                if (this.controlTable != null) {
                    this.controlTable.removeExpandListener(expandListener);
                    this.controlTable.removeCollapseListener(collapseListener);
                }
                for (int i = start; i < (start + offset); i++) {
                    Object itemId = levelList.get(i);
                    configureBeanContainer(itemId, container);
                    total++;
                    if (i != 0) {
                        ((Container.Hierarchical) container).setParent(itemId, levelList.get(i - 1));
                    }
                    setTablesItemColapsed(itemId, false, true);
                }
                if (this.controlTable != null) {
                    this.controlTable.addExpandListener(expandListener);
                    this.controlTable.addCollapseListener(collapseListener);
                }
            }
        }
        return total;
    }

    /**
     * Gets the start.
     *
     * @return the start
     */
    public int getStart() {
        return start;
    }

    /**
     * Sets the start.
     *
     * @param start the new start
     */
    public void setStart(int start) {
        this.start = start;
    }

    /**
     * Gets the end.
     *
     * @return the end
     */
    public int getEnd() {
        return end;
    }

    /**
     * Sets the end.
     *
     * @param end the new end
     */
    public void setEnd(int end) {
        this.end = end;
    }

    /**
     * Static row expand logic.
     *
     * @param object the object
     */
    protected void staticRowExpandLogic(Object object) {
        if (isStaticDataPossible()) {
            if (getStaticExpandableLevelList().contains(object)) {
                int index = ((Container.Indexed) container).indexOfId(object);
                int page = getCurrentPage();
                for (int i = 1; i < page; i++) {
                    index += getItemsPerPage();
                }
                setStaticCount(index);
                setNeedStaticCount(false);
            }
        }
    }

    /**
     * Expand logic.
     *
     * @param object the object
     * @param isManualLevel the is manual level
     */
    @Override
    protected void expandLogic(Object object, boolean isManualLevel) {
        int startWith = 0;
        if (isManualLevel) {
            startWith = staticCount + levelList.size();
        } else {
            staticRowExpandLogic(object);
            startWith = staticCount + configureLevel(object, true);
        }
        this.lastParent = object;
        configurePage(startWith, startWith);
    }

    /**
     * Collapse logic.
     *
     * @param object the object
     * @param isManualLevel the is manual level
     */
    @Override
    protected void collapseLogic(Object object, boolean isManualLevel) {
        int startWith = 1;
        this.lastParent = null;
        if (isManualLevel) {
            startWith = levelList.size();
            levelList.remove(object);
            if (levelList.size() > 0) {
                this.lastParent = levelList.get(levelList.size() - 1);
            }
        } else {
            boolean conf = false;
            if (((Hierarchical) container).getParent(object) != null) {
                startWith = configureLevel(object, false);
                levelList.remove(object);
                conf = true;
                this.lastParent = levelList.get(levelList.size() - 1);
            }
            if (!conf && levelList.size() > 0) {
                for (int i = levelList.size() - 1; i > levelList.indexOf(object); i--) {
                    levelList.remove(i);
                }
                startWith = levelList.size();
                levelList.remove(object);
                if (levelList.size() > 0) {
                    this.lastParent = levelList.get(levelList.size() - 1);
                }
            }
        }
        LevelMap lvlMap = removelevelMap("" + startWith);
        int pos = lvlMap.getPageLength() * (lvlMap.getPage() - 1) + lvlMap.getIndex();
        for (PagedTreeTableBase tbl : getTables()) {
            ((ExtPagedFilterTreeTable) tbl).resetFilters();
            ((ExtPagedFilterTreeTable) tbl).removeFilterValueChangeListeners();
        }
        setColumnIdToFilterMap(lvlMap.getColumnIdToFilterValue());
        for (PagedTreeTableBase tbl : getTables()) {
            Object[] arr = ((ExtPagedFilterTreeTable) tbl).getVisibleColumns();
            for (Object prop : getColumnIdToFilterMap().keySet()) {
                if (Arrays.asList(arr).contains(prop)) {
                    ((ExtPagedFilterTreeTable) tbl).setFilterFieldValue(prop, getColumnIdToFilterValue(prop));
                }
            }
        }
        startWith += staticCount;
        setNeedStaticCount(isStaticDataPossible());
        configurePage(startWith, pos);
        for (PagedTreeTableBase tbl : getTables()) {
            ((ExtPagedFilterTreeTable) tbl).addFilterValueChangeListeners();
        }
    }

    /**
     * Configure page.
     *
     * @param startWith the start with
     * @param pos the pos
     */
    public void configurePage(int startWith, int pos) {
        for (int i = getLevelMapList().size() - levelList.size(); i > 0; i--) {
            String s = "" + (startWith + i);
            if (getLevelMapList().containsKey(s)) {
                getLevelMapList().remove(s);
            }
        }
        int curPage = 1;
        for (int i = getItemsPerPage(); i <= pos; i += getItemsPerPage()) {
            curPage++;
        }
        setCurrentPage(curPage);
    }

    /**
     * Method calculates the hierarchy level of the table and adds the itemId of
     * particular level to a list. After calling this method only levelValue and
     * levelList fields can be used.
     *
     * @param object Item Id passed as argument on expand event.
     * @param expand the expand
     * @return int - Hierarchy Level in the table.
     */
    protected int configureLevel(Object object, boolean expand) {
        levelValue = 1;
        Object itemId = object;
        if (getCurrentPage() != 1) {
            if (expand) {
                levelList.add(itemId);
            } else {
                for (int i = levelList.size() - 1; i > levelList.indexOf(itemId); i--) {
                    levelList.remove(i);
                }
            }
        } else {
            levelList.clear();
            while (!((Hierarchical) container).isRoot(itemId)) {
                levelList.add(0, itemId);
                itemId = ((Hierarchical) container).getParent(itemId);
            }
            levelList.add(0, itemId);

        }
        levelValue = levelList.size();
        if (expand) {
            addlevelMap("" + levelValue, new LevelMap(start, end, getCurrentPage(), getItemsPerPage(), ((Indexed) container).indexOfId(object), getColumnIdToFilterMap()));
        }
        return levelValue;
    }

    /**
     * Update add extra item.
     *
     * @param extraItemId the extra item id
     * @return true, if successful
     */
    private boolean updateAddExtraItem(Object extraItemId) {
        if (extraItemlist.add(extraItemId)) {
            updateContainer();
            return true;
        }
        return false;
    }

    /**
     * Update remove extra item.
     *
     * @param extraItemId the extra item id
     * @return true, if successful
     */
    private boolean updateRemoveExtraItem(Object extraItemId) {
        if (extraItemlist.remove(extraItemId)) {
            updateContainer();
            return true;
        }
        return false;
    }

    /**
     * Update container.
     */
    private void updateContainer() {
        if (isDefaultLoadExtraItem()) {
            setCurrentPage(getCurrentPage());
        }
    }

    /**
     * Gets the extra itemlist.
     *
     * @return the extra itemlist
     */
    public List getExtraItemlist() {
        return extraItemlist;
    }

    /**
     * Sets the extra itemlist.
     *
     * @param extraItemlist the new extra itemlist
     */
    public void setExtraItemlist(List extraItemlist) {
        if (this.extraItemlist == null) {
            this.extraItemlist = new ArrayList();
        }
        for (int i = 0; i < getExtraItemlist().size(); i++) {
            Object itemId = getExtraItemlist().get(i);
            if (!levelList.contains(itemId)) {
                this.extraItemlist.remove(itemId);
                i--;
            }
        }
        this.extraItemlist.addAll(extraItemlist);
        updateContainer();
    }

    /**
     * Adds the extra item.
     *
     * @param extraItemId the extra item id
     * @return true, if successful
     */
    public boolean addExtraItem(Object extraItemId) {
        return updateAddExtraItem(extraItemId);
    }

    /**
     * Removes the extra item.
     *
     * @param extraItemId the extra item id
     * @return true, if successful
     */
    public boolean removeExtraItem(Object extraItemId) {
        return updateRemoveExtraItem(extraItemId);
    }

    /**
     * Checks if is default load extra item.
     *
     * @return true, if is default load extra item
     */
    public boolean isDefaultLoadExtraItem() {
        return defaultLoadExtraItem;
    }

    /**
     * Sets the default load extra item.
     *
     * @param defaultLoadExtraItem the new default load extra item
     */
    public void setDefaultLoadExtraItem(boolean defaultLoadExtraItem) {
        this.defaultLoadExtraItem = defaultLoadExtraItem;
    }

    /**
     * Gets the extra item list after parent addition.
     *
     * @return the extra item list after parent addition
     */
    public List getExtraItemListAfterParentAddition() {
        List extraItems = new ArrayList();

        if (getExtraItemlist() != null) {
            for (int i = 0; i < getExtraItemlist().size(); i++) {
                Object itemId = getExtraItemlist().get(i);
                if (!levelList.contains(itemId)) {
                    extraItems.add(itemId);
                }
            }
        }
        return extraItems;
    }

    /**
     * Gets the parent extra item list.
     *
     * @return the parent extra item list
     */
    public List getParentExtraItemList() {
        List extraItems = new ArrayList();

        if (getExtraItemlist() != null) {
            for (int i = 0; i < getExtraItemlist().size(); i++) {
                Object itemId = getExtraItemlist().get(i);
                if (levelList.contains(itemId)) {
                    extraItems.add(itemId);
                }
            }
        }
        return extraItems;
    }

    /**
     * Load extra container.
     *
     * @param start the start
     * @param offset the offset
     * @return the int
     */
    private int loadExtraContainer(int start, int offset) {
        int total = 0;
        int newOffset = offset;
        if (getExtraItemlist() != null) {
            if (!getExtraItemListAfterParentAddition().isEmpty()) {
                if ((getExtraItemListAfterParentAddition().size() - start) < newOffset) {
                    newOffset = getExtraItemListAfterParentAddition().size() - start;
                }
                for (int i = start; i < start + newOffset; i++) {
                    Object itemId = getExtraItemListAfterParentAddition().get(i);
                    configureBeanContainer(itemId, container);
                    total++;
                    if (lastParent != null) {
                        ((Hierarchical) container).setParent(itemId, lastParent);
                    }
                }
            }
        }
        return total;
    }

    /**
     * Load extra container.
     *
     * @param recordPos the record pos
     */
    public void loadExtraContainer(int recordPos) {
        if (getControlTable().size() < getItemsPerPage()) {
            int offset = getItemsPerPage() - getControlTable().size();

            if (getExtraItemlist() != null) {
                int lvl = getExtraItemListAfterParentAddition().size();
                int pos = 0;
                int lvlstrt = 0;
                for (int i = 1; i < getCurrentPage(); i++) {
                    pos = pos + getItemsPerPage();
                }
                if (recordPos < pos) {
                    lvlstrt = pos - recordPos;
                }
                if (lvlstrt < lvl) {
                    if (offset > (lvl - lvlstrt)) {
                        offset = lvl - lvlstrt;
                    }
                    loadExtraContainer(lvlstrt, offset);
                }

            }

        }
    }

    /**
     * Creates the static count.
     */
    public void createStaticCount() {
        if (isStaticDataPossible() && isNeedStaticCount()) {
            int count = 0;
            try {
                count = getStaticDataCount();
            } catch (SQLException ex) {
                Logger.getLogger(ContainerLogic.class.getName()).log(Level.SEVERE, null, ex);
            }
            setStaticCount(count);
        }

    }

    /**
     * Gets the static count.
     *
     * @return the static count
     */
    protected int getStaticCount() {
        return staticCount;
    }

    /**
     * Sets the static count.
     *
     * @param staticCount the new static count
     */
    protected void setStaticCount(int staticCount) {
        this.staticCount = staticCount;
    }

    /**
     * Clear all.
     */
    @Override
    public void clearAll() {
        super.clearAll();
        clearContainer();
        clearLevelList();
    }

   

    /**
     * Clear level list.
     */
    public void clearLevelList() {
        levelList.clear();
    }

    

    /**
     * Gets the level list.
     *
     * @return the level list
     */
    public List getLevelList() {
        return levelList;
    }

    /**
     * Sets the level list.
     *
     * @param levelList the new level list
     */
    public void setLevelList(List levelList) {
        this.levelList = levelList;
    }    

    /**
     * Gets the static last parent.
     *
     * @return the static last parent
     */
    public Object getStaticLastParent() {
        return staticLastParent;
    }

    /**
     * Sets the static last parent.
     *
     * @param staticLastParent the new static last parent
     */
    public void setStaticLastParent(Object staticLastParent) {
        this.staticLastParent = staticLastParent;
    }

    /**
     * Checks if is static data possible.
     *
     * @return true, if is static data possible
     */
    public boolean isStaticDataPossible() {
        return staticDataPossible;
    }

    /**
     * Sets the static data possible.
     *
     * @param staticDataPossible the new static data possible
     */
    public void setStaticDataPossible(boolean staticDataPossible) {
        this.staticDataPossible = staticDataPossible;
        setNeedStaticCount(staticDataPossible);
        if (!staticDataPossible) {
            setStaticCount(0);
        }
    }

    /**
     * Gets the static expandable level list.
     *
     * @return the static expandable level list
     */
    protected List getStaticExpandableLevelList() {
        return staticExpandableLevelList;
    }

    /**
     * Sets the static expandable level list.
     *
     * @param staticExpandableLevelList the new static expandable level list
     */
    protected void setStaticExpandableLevelList(List staticExpandableLevelList) {
        this.staticExpandableLevelList = staticExpandableLevelList;
    }

    /**
     * Adds the static expandable level.
     *
     * @param itemId the item id
     */
    protected void addStaticExpandableLevel(Object itemId) {
        this.staticExpandableLevelList.add(itemId);
    }

    /**
     * Checks if is need static count.
     *
     * @return true, if is need static count
     */
    protected boolean isNeedStaticCount() {
        return needStaticCount;
    }

    /**
     * Sets the need static count.
     *
     * @param needStaticCount the new need static count
     */
    protected void setNeedStaticCount(boolean needStaticCount) {
        this.needStaticCount = needStaticCount;

    }

    /**
     * Handle filter change.
     */
    @Override
    public void handleFilterChange() {
        container.removeAllItems();
        int page = getCurrentPage();
        int itemNo = getLevelList().size();
        int lastPage = (itemNo / getItemsPerPage()) + 1;
        int cur = lastPage;
        if (page < lastPage) {
            cur = page;
        }
        setCurrentPage(cur);
    }

}
