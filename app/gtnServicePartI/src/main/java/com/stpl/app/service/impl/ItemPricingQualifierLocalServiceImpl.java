package com.stpl.app.service.impl;

import com.stpl.app.service.base.ItemPricingQualifierLocalServiceBaseImpl;
import com.stpl.app.service.persistence.ItemPricingQualifierUtil;

/**
 * The implementation of the item pricing qualifier local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.stpl.app.service.ItemPricingQualifierLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author
 * @see com.stpl.app.service.base.ItemPricingQualifierLocalServiceBaseImpl
 * @see com.stpl.app.service.ItemPricingQualifierLocalServiceUtil
 */
public class ItemPricingQualifierLocalServiceImpl
    extends ItemPricingQualifierLocalServiceBaseImpl {
	/*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.stpl.app.global.service.ItemPricingQualifierLocalServiceUtil} to access the item pricing qualifier local service.
     */
	public  com.stpl.app.model.ItemPricingQualifier findByitemPricingCodeQualifierByName(
	        java.lang.String itemPricingCodeQualifierName)
	        throws com.stpl.portal.kernel.exception.SystemException,
	            com.stpl.app.NoSuchItemPricingQualifierException {
	        return ItemPricingQualifierUtil
	                   .findByitemPricingCodeQualifierByName(itemPricingCodeQualifierName);
	    }
}
