package com.stpl.app.service.impl;

import com.stpl.app.service.base.ItemGroupLocalServiceBaseImpl;
import com.stpl.app.service.persistence.ItemGroupFinderUtil;

/**
 * The implementation of the item group local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.stpl.app.service.ItemGroupLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author
 * @see com.stpl.app.service.base.ItemGroupLocalServiceBaseImpl
 * @see com.stpl.app.service.ItemGroupLocalServiceUtil
 */
public class ItemGroupLocalServiceImpl extends ItemGroupLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.stpl.app.service.ItemGroupLocalServiceUtil} to access the item group local service.
     */
    public java.util.List getItemGroupMaster(
        String itemGroupName) {
        return ItemGroupFinderUtil.getItemGroupMaster(itemGroupName);
    }
         public java.util.List getItemGroupDetails(java.lang.String itemType,
        java.lang.String itemDesc, java.lang.String brand,
        java.lang.String strength, java.lang.String itemNoCriteria,
        java.lang.String therapeuticCriteria, java.lang.String formCriteria,
        java.lang.String selectedItemIds){
             return ItemGroupFinderUtil.getItemGroupDetails(itemType, itemDesc, brand, strength, itemNoCriteria, therapeuticCriteria, formCriteria,selectedItemIds);
         }

    public java.util.List getAvailableSearchResults(
        java.lang.String finalCriteria){
        return ItemGroupFinderUtil.getAvailableSearchResults(finalCriteria);
    }
}
