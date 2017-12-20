package org.asi.ui.addons.lazycontainer;

import java.util.List;

public abstract interface DAO<MODEL>
{
  public abstract int count(SearchCriteria paramSearchCriteria);
  
  public abstract List<MODEL> find(SearchCriteria paramSearchCriteria, int paramInt1, int paramInt2, List<OrderByColumn> paramList);
}


/* Location:              C:\Users\Abishek.ram\.m2\repository\org\vaadin\addons\lazy-container\0.1\lazy-container-0.1.jar!\org\vaadin\addons\lazycontainer\DAO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */