package org.asi.ui.addons.lazycontainer;

import com.vaadin.v7.data.Container.Filter;
import com.vaadin.v7.data.Container;
import java.util.Set;

public abstract interface BeanSearchCriteria
{
  public abstract int getLastCount();
  
  public abstract void setLastCount(int paramInt);
  
  public abstract boolean isDirty();
  
  public abstract void setDirty(boolean paramBoolean);
  
  public abstract Set<Container.Filter> getFilters();
  
  public abstract void setFilters(Set<Container.Filter> paramSet);
}
