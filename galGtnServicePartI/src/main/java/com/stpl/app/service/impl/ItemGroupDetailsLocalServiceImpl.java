package com.stpl.app.service.impl;

import com.stpl.app.service.base.ItemGroupDetailsLocalServiceBaseImpl;
import com.stpl.app.service.persistence.ItemGroupDetailsFinderUtil;
import com.stpl.portal.kernel.dao.orm.Session;
import com.vaadin.data.Container;
import java.util.List;
import java.util.Set;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;

/**
 * The implementation of the item group details local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.stpl.app.service.ItemGroupDetailsLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author
 * @see com.stpl.app.service.base.ItemGroupDetailsLocalServiceBaseImpl
 * @see com.stpl.app.service.ItemGroupDetailsLocalServiceUtil
 */
public class ItemGroupDetailsLocalServiceImpl
    extends ItemGroupDetailsLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.stpl.app.service.ItemGroupDetailsLocalServiceUtil} to access the item group details local service.
     */
     public java.util.List getProductGroups(
        String productGroupNo, String productGroupName, String company, String segment,int startIndex, int offset, Set<Container.Filter> filters,List<SortByColumn> sortByColumns) {
        return ItemGroupDetailsFinderUtil.getProductGroups(productGroupNo, productGroupName, company, segment,startIndex, offset, filters, sortByColumns );
    }
       public List getItemMasterRecords(String query[]) {
       return ItemGroupDetailsFinderUtil.getItemMasterRecords(query);
  
     }
     
}
