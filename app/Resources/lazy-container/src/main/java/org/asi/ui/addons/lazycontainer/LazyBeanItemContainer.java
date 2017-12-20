 package org.asi.ui.addons.lazycontainer;
 
 import com.vaadin.v7.data.Container;
 import com.vaadin.v7.data.util.BeanItem;
 import com.vaadin.v7.data.util.BeanItemContainer;
 import java.util.ArrayList;
 import java.util.List;
 import java.util.Set;
 

 public class LazyBeanItemContainer<BEANTYPE>
   extends BeanItemContainer<BEANTYPE>
 {
   private static final long serialVersionUID = 1L;
   private BeanSearchCriteria criteria;
   private BeanDAO dao;
   private List<OrderByColumn> orderByColumns = new ArrayList();
   
   private int minFilterLength;
   
   public LazyBeanItemContainer(Class type, BeanDAO dao, BeanSearchCriteria criteria)
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
     } else if ((isFiltered()) && (this.criteria.getFilters() != null)) {
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
   
   public List<BEANTYPE> getItemIds(int startIndex, int numberOfIds)
   {
     filterStringToSearchCriteria();
     List<BEANTYPE> items = null;
     if ((isFiltered()) && (this.criteria.getFilters() != null)) {
       items = findItems(startIndex, numberOfIds);
       this.criteria.setFilters(null);
     } else if (!isFiltered()) {
       items = findItems(startIndex, numberOfIds);
     }
     return items;
   }
   
   private List<BEANTYPE> findItems(int startIndex, int numberOfIds)
   {
     List<BEANTYPE> items = this.dao.find(this.criteria, startIndex, numberOfIds, this.orderByColumns);
     return items;
   }
   
   private void filterStringToSearchCriteria() {
     if (isFiltered()) {
       Set<Container.Filter> filters = getFilters();
       
       this.criteria.setFilters(filters);
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
 }
