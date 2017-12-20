package org.asi.ui.addons.lazycontainer;

public abstract interface SearchCriteria
{
  public abstract int getLastCount();
  
  public abstract void setLastCount(int paramInt);
  
  public abstract boolean isDirty();
  
  public abstract void setDirty(boolean paramBoolean);
  
  public abstract String getFilter();
  
  public abstract void setFilter(String paramString);
}


/* Location:              C:\Users\Abishek.ram\.m2\repository\org\vaadin\addons\lazy-container\0.1\lazy-container-0.1.jar!\org\vaadin\addons\lazycontainer\SearchCriteria.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */