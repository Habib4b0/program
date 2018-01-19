/*
 * Copyright 2014 Abhiram.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.asi.ui.extfilteringtable.paged;

import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.Property.ValueChangeListener;
import com.vaadin.v7.data.util.converter.StringToIntegerConverter;
import com.vaadin.v7.shared.ui.label.ContentMode;
import com.vaadin.v7.ui.AbstractSelect;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.v7.ui.Label;
import com.vaadin.v7.ui.TextField;
import com.vaadin.v7.ui.themes.Reindeer;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.asi.ui.container.ExtPagedFilterTableContainer;
import org.asi.ui.extfilteringtable.ExtFilterTable;


/**
 * The Class ExtPagedFilterTable.
 *
 * @author Abhiram
 * @param <T> the generic type
 */
@SuppressWarnings("serial")
public class ExtPagedFilterTable<T extends Container.Indexed & Container.Filterable & Container.ItemSetChangeNotifier>
        extends ExtFilterTable {

    /** The left table. */
    ExtPagedFilterTable leftTable = null;

    /**
     * The listener interface for receiving pageChange events.
     * The class that is interested in processing a pageChange
     * event implements this interface, and the object created
     * with that class is registered with a component using the
     * component's <code>addPageChangeListener<code> method. When
     * the pageChange event occurs, that object's appropriate
     * method is invoked.
     *
     * @see PageChangeEvent
     */
    public interface PageChangeListener extends Serializable {

        /**
         * Page changed.
         *
         * @param event the event
         */
        public void pageChanged(ExtPagedTableChangeEvent event);
    }

    /** The listeners. */
    private List<PageChangeListener> listeners = null;

    /** The container. */
    private ExtPagedFilterTableContainer<T> container;
    
    /** The config. */
    private final ExtPagedFilterControlConfig config = new ExtPagedFilterControlConfig();
    
    /** The first. */
    final Button first = new Button(config.getFirst());
    
    /** The last. */
    final Button last = new Button(config.getLast());
    
    /** The previous. */
    final Button previous = new Button(config.getPrevious());
    
    /** The next. */
    final Button next = new Button(config.getNext());
    
    /** The is link. */
    boolean isLink = true;
    
    /** The items per page. */
    private int itemsPerPage = 15;
    
    /** The is sink. */
    private boolean isSink = true;
    
    /** The editable sink. */
    private boolean editableSink = false;

    /**
     * Instantiates a new ext paged filter table.
     */
    public ExtPagedFilterTable() {
        this(null);
    }

    /**
     * Instantiates a new ext paged filter table.
     *
     * @param caption the caption
     */
    public ExtPagedFilterTable(String caption) {
        super(caption);
        setPageLength(15);
        setItemsPerPage(15);
        addStyleName("paged-filter-table");
    }

    /**
     * Creates the controls.
     *
     * @return the horizontal layout
     */
    public HorizontalLayout createControls() {
        return createTheControls(null);
    }

    /**
     * Creates the controls.
     *
     * @param leftTable the left table
     * @return the horizontal layout
     */
    public HorizontalLayout createControls(final ExtPagedFilterTable leftTable) {
        return createTheControls(leftTable);
    }

    /**
     * Gets the control config.
     *
     * @return the control config
     */
    public ExtPagedFilterControlConfig getControlConfig() {
        return config;
    }

    /**
     * Creates the the controls.
     *
     * @param table the table
     * @return the horizontal layout
     */
    private HorizontalLayout createTheControls(final ExtPagedFilterTable table) {
        this.leftTable = table;
        Label itemsPerPageLabel = new Label(config.getItemsPerPage(),
                ContentMode.HTML);
        itemsPerPageLabel.setSizeUndefined();
        final ComboBox itemsPerPageSelect = new ComboBox();
        configureItemPerPage(itemsPerPageSelect);
        itemsPerPageSelect.setImmediate(true);
        itemsPerPageSelect.setNullSelectionAllowed(false);
        itemsPerPageSelect.setWidth("70px");
        itemsPerPageSelect.setStyleName(Reindeer.TEXTFIELD_SMALL);
        itemsPerPageSelect.addValueChangeListener(new ValueChangeListener() {
            private static final long serialVersionUID = -2255853716069800092L;

            @Override
            public void valueChange(
                    com.vaadin.v7.data.Property.ValueChangeEvent event) {
                Property pr = event.getProperty();
                if (pr != null) {
                    if (pr.getValue() != null) {
                        int val = (Integer) pr.getValue();
                        if (val != getItemsPerPage()) {
                            if (leftTable != null) {
                                leftTable.setItemsPerPage(val);
                            }
                            setItemsPerPage(val);
                        }
                    }
                }
            }
        });
        itemsPerPageSelect.setNewItemsAllowed(true);
        itemsPerPageSelect.setNewItemHandler(new AbstractSelect.NewItemHandler() {

            @Override
            public void addNewItem(String newItemCaption) {
                try {
                    int x = Integer.valueOf(newItemCaption);
                    if ((!isSink && x > getPageLength()) || isSink) {
                        if (!itemsPerPageSelect.containsId(x)) {
                            config.addPageLength(x);
                            configureItemPerPage(itemsPerPageSelect);
                            itemsPerPageSelect.select(x);
                        }
                    }
                } catch (NumberFormatException e) {
                    itemsPerPageSelect.select(getItemsPerPage());
                }
            }
        });
        if (itemsPerPageSelect.containsId(getItemsPerPage())) {
            itemsPerPageSelect.select(getItemsPerPage());
        } else {
            itemsPerPageSelect.select(itemsPerPageSelect.getItemIds()
                    .iterator().next());
        }
        Label pageLabel = new Label(config.getPage(), ContentMode.HTML);
        final TextField currentPageTextField = new TextField();
        currentPageTextField.setValue(String.valueOf(getCurrentPage()));
        currentPageTextField.setConverter(new StringToIntegerConverter());
        Label separatorLabel = new Label("&nbsp;/&nbsp;", ContentMode.HTML);
        final Label totalPagesLabel = new Label(
                String.valueOf(getTotalAmountOfPages()), ContentMode.HTML);
        currentPageTextField.setStyleName(Reindeer.TEXTFIELD_SMALL);
        currentPageTextField.setImmediate(true);
        currentPageTextField.addValueChangeListener(new ValueChangeListener() {
            private static final long serialVersionUID = -2255853716069800092L;

            @Override
            public void valueChange(
                    com.vaadin.v7.data.Property.ValueChangeEvent event) {
                if (currentPageTextField.isValid()
                        && currentPageTextField.getValue() != null && !"null".equals(currentPageTextField.getValue())) { // added for GAL-8160

                    int page = Integer.valueOf(String
                            .valueOf(currentPageTextField.getValue()));
                    setCurrentPage(page);
                    if (leftTable != null) {
                        leftTable.setCurrentPage(page);
                    }
                } else {
                    currentPageTextField.setValue(String.valueOf(getCurrentPage()));
                }
            }
        });
        pageLabel.setWidth(null);
        currentPageTextField.setColumns(3);
        separatorLabel.setWidth(null);
        totalPagesLabel.setWidth(null);

        HorizontalLayout controlBar = new HorizontalLayout();
        HorizontalLayout pageSize = new HorizontalLayout();
        HorizontalLayout pageManagement = new HorizontalLayout();
        first.addClickListener(new ClickListener() {
            private static final long serialVersionUID = -355520120491283992L;

            @Override
            public void buttonClick(ClickEvent event) {
                setCurrentPage(0);
                if (leftTable != null) {
                    leftTable.setCurrentPage(0);
                }
            }
        });
        previous.addClickListener(new ClickListener() {
            private static final long serialVersionUID = -355520120491283992L;

            @Override
            public void buttonClick(ClickEvent event) {
                previousPage();
            }
        });
        next.addClickListener(new ClickListener() {
            private static final long serialVersionUID = -1927138212640638452L;

            @Override
            public void buttonClick(ClickEvent event) {
                nextPage();
            }
        });
        last.addClickListener(new ClickListener() {
            private static final long serialVersionUID = -355520120491283992L;

            @Override
            public void buttonClick(ClickEvent event) {
                setCurrentPage(getTotalAmountOfPages());
                if (leftTable != null) {
                    leftTable.setCurrentPage(getTotalAmountOfPages());
                }
            }
        });
        setControlButtonAsLink(isLink);
        itemsPerPageLabel.addStyleName("pagedtable-itemsperpagecaption");
        itemsPerPageSelect.addStyleName("pagedtable-itemsperpagecombobox");
        pageLabel.addStyleName("pagedtable-pagecaption");
        currentPageTextField.addStyleName("pagedtable-pagefield");
        separatorLabel.addStyleName("pagedtable-separator");
        totalPagesLabel.addStyleName("pagedtable-total");

        itemsPerPageLabel.addStyleName("pagedtable-label");
        itemsPerPageSelect.addStyleName("pagedtable-combobox");
        pageLabel.addStyleName("pagedtable-label");
        currentPageTextField.addStyleName("pagedtable-label");
        separatorLabel.addStyleName("pagedtable-label");
        totalPagesLabel.addStyleName("pagedtable-label");

        pageSize.addComponent(itemsPerPageLabel);
        pageSize.addComponent(itemsPerPageSelect);
        pageSize.setComponentAlignment(itemsPerPageLabel, Alignment.MIDDLE_LEFT);
        pageSize.setComponentAlignment(itemsPerPageSelect,
                Alignment.MIDDLE_LEFT);
        pageSize.setSpacing(true);
        pageManagement.addComponent(first);
        pageManagement.addComponent(previous);
        pageManagement.addComponent(pageLabel);
        pageManagement.addComponent(currentPageTextField);
        pageManagement.addComponent(separatorLabel);
        pageManagement.addComponent(totalPagesLabel);
        pageManagement.addComponent(next);
        pageManagement.addComponent(last);
        pageManagement.setComponentAlignment(first, Alignment.MIDDLE_LEFT);
        pageManagement.setComponentAlignment(previous, Alignment.MIDDLE_LEFT);
        pageManagement.setComponentAlignment(pageLabel, Alignment.MIDDLE_LEFT);
        pageManagement.setComponentAlignment(currentPageTextField,
                Alignment.MIDDLE_LEFT);
        pageManagement.setComponentAlignment(separatorLabel,
                Alignment.MIDDLE_LEFT);
        pageManagement.setComponentAlignment(totalPagesLabel,
                Alignment.MIDDLE_LEFT);
        pageManagement.setComponentAlignment(next, Alignment.MIDDLE_LEFT);
        pageManagement.setComponentAlignment(last, Alignment.MIDDLE_LEFT);
        pageManagement.setWidth(null);
        pageManagement.setSpacing(true);
//        controlBar.addComponent(pageSize);
        controlBar.addComponent(pageManagement);
//        controlBar.setComponentAlignment(pageManagement,
//                Alignment.MIDDLE_CENTER);
        controlBar.setWidth(100, Unit.PERCENTAGE);
//        controlBar.setExpandRatio(pageSize, 1);

        if (container != null) {
            first.setEnabled(container.getStartIndex() > 0);
            previous.setEnabled(container.getStartIndex() > 0);
            next.setEnabled(container.getStartIndex() < container.getRealSize()
                    - getItemsPerPage());
            last.setEnabled(container.getStartIndex() < container.getRealSize()
                    - getItemsPerPage());
        }

        addListener(new PageChangeListener() {
            private boolean inMiddleOfValueChange;

            @Override
            public void pageChanged(ExtPagedTableChangeEvent event) {
                if (!inMiddleOfValueChange) {
                    inMiddleOfValueChange = true;
                    first.setEnabled(container.getStartIndex() > 0);
                    previous.setEnabled(container.getStartIndex() > 0);
                    next.setEnabled(container.getStartIndex() < container
                            .getRealSize() - getItemsPerPage());
                    last.setEnabled(container.getStartIndex() < container
                            .getRealSize() - getItemsPerPage());
                    currentPageTextField.setValue(String
                            .valueOf(getCurrentPage()));
                    totalPagesLabel.setValue(Integer
                            .toString(getTotalAmountOfPages()));
                    itemsPerPageSelect
                            .setValue(getItemsPerPage());
                    inMiddleOfValueChange = false;
                }
            }
        });
        return controlBar;
    }
	
    /**
     * Configure item per page.
     *
     * @param itemsPerPageSelect the items per page select
     */
    private void configureItemPerPage(ComboBox itemsPerPageSelect) {
        itemsPerPageSelect.removeAllItems();
        for (Integer i : config.getPageLengths()) {
            itemsPerPageSelect.addItem(i);
            itemsPerPageSelect.setItemCaption(i, String.valueOf(i));
        }
    }

    /**
     * Sets the control button as link.
     *
     * @param isLink the new control button as link
     */
    public void setControlButtonAsLink(boolean isLink) {
        this.isLink = isLink;
        if (isLink) {
            first.setStyleName(Reindeer.BUTTON_LINK);
            previous.setStyleName(Reindeer.BUTTON_LINK);
            next.setStyleName(Reindeer.BUTTON_LINK);
            last.setStyleName(Reindeer.BUTTON_LINK);
        } else {
            first.setStyleName(Reindeer.BUTTON_DEFAULT);
            previous.setStyleName(Reindeer.BUTTON_DEFAULT);
            next.setStyleName(Reindeer.BUTTON_DEFAULT);
            last.setStyleName(Reindeer.BUTTON_DEFAULT);
        }
        first.addStyleName("pagedtable-button");
        previous.addStyleName("pagedtable-button");
        next.addStyleName("pagedtable-button");
        last.addStyleName("pagedtable-button");
        first.addStyleName("pagedtable-first");
        previous.addStyleName("pagedtable-previous");
        next.addStyleName("pagedtable-next");
        last.addStyleName("pagedtable-last");
    }

    /**
     * Gets the container data source.
     *
     * @return the container data source
     */
    @Override
    public ExtPagedFilterTableContainer<T> getContainerDataSource() {
        return container;
    }

    /**
     * Sets the container data source.
     *
     * @param newDataSource the new container data source
     */
    @Override
    @SuppressWarnings("unchecked")
    public void setContainerDataSource(Container newDataSource) {
        if (!(newDataSource instanceof Container.Indexed)
                || !(newDataSource instanceof Container.Filterable)) {
            throw new IllegalArgumentException(
                    "ExtPagedFilterTreeTable can only use containers that implement Container.Indexed AND Container.Filterable");
        }
        ExtPagedFilterTableContainer<T> pagedFilteringTableContainer = new ExtPagedFilterTableContainer<T>(
                (T) newDataSource);
        pagedFilteringTableContainer.setPageLength(getItemsPerPage());
        container = pagedFilteringTableContainer;
        super.setContainerDataSource(pagedFilteringTableContainer);
        firePagedChangedEvent();
    }

    /**
     * Sink item per page with page length.
     *
     * @param isSink the is sink
     */
    public void sinkItemPerPageWithPageLength(boolean isSink) {
        if (isSink) {
            setItemsPerPage(getPageLength());
        }
        this.isSink = isSink;
    }

    /**
     * Checks if is sink item per page with page length.
     *
     * @return true, if is sink item per page with page length
     */
    public boolean isSinkItemPerPageWithPageLength() {
        return isSink;
    }

    /**
     * Sets the page first index.
     *
     * @param firstIndex the new page first index
     */
    private void setPageFirstIndex(int firstIndex) {
        if (container != null) {
            if (firstIndex <= 0) {
                firstIndex = 0;
            }
            if (firstIndex > container.getRealSize() - 1) {
                int size = container.getRealSize() - 1;
                int pages = 0;
                if (getItemsPerPage() != 0) {
                    pages = (int) Math.floor(0.0 + size / getItemsPerPage());
                }
                firstIndex = pages * getItemsPerPage();
            }
            container.setStartIndex(firstIndex);
            containerItemSetChange(new Container.ItemSetChangeEvent() {
                private static final long serialVersionUID = -5083660879306951876L;

                @Override
                public Container getContainer() {
                    return container;
                }
            });
            if (alwaysRecalculateColumnWidths) {
                for (Object columnId : container.getContainerPropertyIds()) {
                    setColumnWidth(columnId, -1);
                }
            }
            firePagedChangedEvent();
        }
    }

    /**
     * Fire paged changed event.
     */
    public void firePagedChangedEvent() {
        if (listeners != null) {
            ExtPagedTableChangeEvent event = new ExtPagedTableChangeEvent(this);
            for (PageChangeListener listener : listeners) {
                listener.pageChanged(event);
            }
        }
    }

    /**
     * Sets the page length.
     *
     * @param pageLength the new page length
     */
    @Override
    public void setPageLength(int pageLength) {
        super.setPageLength(pageLength);
        if (isSink) {
            setItemsPerPage(pageLength);
        }
    }

    /**
     * Next page.
     */
    public void nextPage() {
        setPageFirstIndex(container.getStartIndex() + getItemsPerPage());
    }

    /**
     * Previous page.
     */
    public void previousPage() {
        setPageFirstIndex(container.getStartIndex() - getItemsPerPage());
    }

    /**
     * Gets the current page.
     *
     * @return the current page
     */
    public int getCurrentPage() {
        double pageLength = getItemsPerPage();
        int page = (int) Math.floor(container.getStartIndex() / pageLength) + 1;
        if (page < 1) {
            page = 1;
        }
        return page;
    }

    /**
     * Sets the current page.
     *
     * @param page the new current page
     */
    public void setCurrentPage(int page) {
        int newIndex = (page - 1) * getItemsPerPage();
        if (newIndex < 0) {
            newIndex = 0;
        }
        setPageFirstIndex(newIndex);
    }

    /**
     * Gets the total amount of pages.
     *
     * @return the total amount of pages
     */
    public int getTotalAmountOfPages() {
        int size = container.getContainer().size();
        double pageLength = getItemsPerPage();
        int pageCount = (int) Math.ceil(size / pageLength);
        if (pageCount < 1) {
            pageCount = 1;
        }
        return pageCount;
    }

    /**
     * Adds the listener.
     *
     * @param listener the listener
     */
    public void addListener(PageChangeListener listener) {
        if (listeners == null) {
            listeners = new ArrayList<PageChangeListener>();
        }
        listeners.add(listener);
    }

    /**
     * Removes the listener.
     *
     * @param listener the listener
     */
    public void removeListener(PageChangeListener listener) {
        if (listeners == null) {
            listeners = new ArrayList<PageChangeListener>();
        }
        listeners.remove(listener);
    }

    /**
     * Reset filters.
     */
    @Override
    public void resetFilters() {
        super.resetFilters();
        setCurrentPage(1);
    }

    /**
     * Gets the items per page.
     *
     * @return the items per page
     */
    public int getItemsPerPage() {
        return itemsPerPage;
    }

    /**
     * Sets the items per page.
     *
     * @param itemsPerPage the new items per page
     */
    public void setItemsPerPage(int itemsPerPage) {
        if (isSink) {
            super.setPageLength(itemsPerPage);
        }
        if (itemsPerPage < getPageLength()) {
            itemsPerPage = getPageLength();
        }
        if (itemsPerPage >= getPageLength()) {
            if (getItemsPerPage() != itemsPerPage) {
                this.itemsPerPage = itemsPerPage;
                config.addPageLength(itemsPerPage);
                container.setPageLength(itemsPerPage);
                refreshRowCache();
            }
            firePagedChangedEvent();

        }

    }

    /**
     * Checks if is editable sink.
     *
     * @return true, if is editable sink
     */
    public boolean isEditableSink() {
        return editableSink;
    }

    /**
     * Sets the editable sink.
     *
     * @param editableSink the new editable sink
     */
    public void setEditableSink(boolean editableSink) {
        this.editableSink = editableSink;
    }

    /**
     * Gets the manual page length.
     *
     * @return the manual page length
     */
    @Override
    protected int getManualPageLength() {
        int pageLength = super.getPageLength();
        if (isEditableSink()) {
            pageLength = container.getPageLength();
        }
        return pageLength;
    }

    /**
     * Gets the current page first item index.
     *
     * @return the current page first item index
     */
    @Override
    public int getCurrentPageFirstItemIndex() {
        int index = super.getCurrentPageFirstItemIndex();
        if (isEditableSink()) {
            index = 0;
        }
        return index;
    }

}
