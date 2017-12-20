 package org.asi.ui.addons.lazycontainer;
 
 import com.vaadin.v7.data.Container;
 import com.vaadin.v7.data.Property;
 import com.vaadin.v7.data.util.BeanContainer;
 import com.vaadin.v7.data.util.BeanItem;
 import com.vaadin.v7.data.util.filter.SimpleStringFilter;
 import java.util.ArrayList;
 import java.util.LinkedList;
 import java.util.List;
 import java.util.Set;
 
 
 
 
 public class LazyContainer
   extends BeanContainer
   implements Container.PropertySetChangeNotifier, Property.ValueChangeNotifier, Container.Sortable, Cloneable, Container.Filterable, Container.SimpleFilterable
 {
   private SearchCriteria criteria;
   private DAO dao;
   private LinkedList<Property.ValueChangeListener> propertyValueChangeListeners = null;
   private List<OrderByColumn> orderByColumns = new ArrayList();
   
   private int minFilterLength;
   
 
   public LazyContainer(Class type, DAO dao, SearchCriteria criteria)
   {
     super(type);
     this.criteria = criteria;
     this.dao = dao;
     this.minFilterLength = 3;
   }
   
   public int size()
   {
     filterStringToSearchCriteria();
     if ((this.criteria.getLastCount() == 0) || (this.criteria.isDirty())) {
       getCount();
     } else if ((isFiltered()) && (this.criteria.getFilter() != null)) {
       getCount();
     }
     return this.criteria.getLastCount();
   }
   
   private void getCount() {
     int count = this.dao.count(this.criteria);
     this.criteria.setDirty(false);
     this.criteria.setLastCount(count);
   }
   
   public BeanItem getItem(Object itemId)
   {
     return new BeanItem(itemId);
   }
   
   public List<?> getItemIds(int startIndex, int numberOfIds)
   {
     filterStringToSearchCriteria();
     List<?> items = null;
     if ((isFiltered()) && (this.criteria.getFilter() != null)) {
       items = findItems(startIndex, numberOfIds);
       this.criteria.setFilter(null);
     } else if (!isFiltered()) {
       items = findItems(startIndex, numberOfIds);
     }
     return items;
   }
   
   private List<?> findItems(int startIndex, int numberOfIds)
   {
     List<?> items = this.dao.find(this.criteria, startIndex, numberOfIds, this.orderByColumns);
     return items;
   }
   
   private void filterStringToSearchCriteria() {
     if (isFiltered()) {
       Set<Container.Filter> filters = getFilters();
       for (Container.Filter filter : filters) {
         if ((filter instanceof SimpleStringFilter)) {
           SimpleStringFilter stringFilter = (SimpleStringFilter)filter;
           String filterString = stringFilter.getFilterString();
           if (filterString.length() > this.minFilterLength) {
             this.criteria.setFilter(filterString);
           } else {
             this.criteria.setFilter(null);
           }
         }
       }
     }
   }
   
   public void sort(Object[] propertyIds, boolean[] ascending)
   {
     this.orderByColumns.clear();
     for (int i = 0; i < propertyIds.length; i++) {
       Object propertyId = propertyIds[i];
       OrderByColumn.Type type = ascending[i]  ? OrderByColumn.Type.ASC : OrderByColumn.Type.DESC;
       String name = propertyId.toString();
       this.orderByColumns.add(new OrderByColumn(name, type));
     }
   }
   
 
   public boolean containsId(Object itemId)
   {
     return true;
   }
   
   public int getMinFilterLength() {
     return this.minFilterLength;
   }
   
   public void setMinFilterLength(int minFilterLength) {
     this.minFilterLength = minFilterLength;
   }
   
   public void addValueChangeListener(Property.ValueChangeListener listener)
   {
     if (this.propertyValueChangeListeners == null) {
       this.propertyValueChangeListeners = new LinkedList();
     }
     this.propertyValueChangeListeners.add(listener);
   }
   
   @Deprecated
   public void addListener(Property.ValueChangeListener listener)
   {
     addValueChangeListener(listener);
   }
   
   public void removeValueChangeListener(Property.ValueChangeListener listener)
   {
     if (this.propertyValueChangeListeners != null) {
       this.propertyValueChangeListeners.remove(listener);
     }
   }
   
   @Deprecated
   public void removeListener(Property.ValueChangeListener listener)
   {
     removeValueChangeListener(listener);
   }
 }
