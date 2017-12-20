 package org.asi.ui.addons.lazycontainer;
 
import com.vaadin.v7.data.Container;
 import com.vaadin.v7.data.Container.Filter;
 import java.util.Set;
 
 public class AbstractBeanSearchCriteria
   implements BeanSearchCriteria
 {
   private int lastCount;
   private boolean dirty;
   private Set<Container.Filter> filter;
   
   public int getLastCount()
   {
     return this.lastCount;
   }
   
   public void setLastCount(int lastCount)
   {
     this.lastCount = lastCount;
   }
   
   public boolean isDirty()
   {
     return this.dirty;
   }
   
   public void setDirty(boolean dirty)
   {
     this.dirty = dirty;
   }
   
   public Set<Container.Filter> getFilters()
   {
     return this.filter;
   }
   
   public void setFilters(Set<Container.Filter> filter)
   {
     this.filter = filter;
   }
 }
