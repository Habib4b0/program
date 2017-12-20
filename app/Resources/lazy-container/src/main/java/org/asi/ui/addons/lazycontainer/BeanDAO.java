package org.asi.ui.addons.lazycontainer;

import java.util.List;

public abstract interface BeanDAO<MODEL>
{
  public abstract int count(BeanSearchCriteria paramBeanSearchCriteria);
  
  public abstract List<MODEL> find(BeanSearchCriteria paramBeanSearchCriteria, int paramInt1, int paramInt2, List<OrderByColumn> paramList);
}


/* Location:              C:\Users\Abishek.ram\.m2\repository\org\vaadin\addons\lazy-container\0.1\lazy-container-0.1.jar!\org\vaadin\addons\lazycontainer\BeanDAO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */