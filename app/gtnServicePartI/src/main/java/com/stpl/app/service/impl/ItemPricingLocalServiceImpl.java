package com.stpl.app.service.impl;

import com.stpl.app.service.base.ItemPricingLocalServiceBaseImpl;
import com.stpl.app.service.persistence.ItemPricingUtil;

/**
 * The implementation of the item pricing local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.stpl.app.service.ItemPricingLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author
 * @see com.stpl.app.service.base.ItemPricingLocalServiceBaseImpl
 * @see com.stpl.app.service.ItemPricingLocalServiceUtil
 */
public class ItemPricingLocalServiceImpl extends ItemPricingLocalServiceBaseImpl {
	/*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.stpl.app.global.service.ItemPricingLocalServiceUtil} to access the item pricing local service.
     */
	 public  java.util.List<com.stpl.app.model.ItemPricing> findByItemPricingDetails(
		        int itemSystemId)
		        throws com.stpl.portal.kernel.exception.SystemException {
		        return ItemPricingUtil.findByItemPricingDetails(itemSystemId);
	 }
}
