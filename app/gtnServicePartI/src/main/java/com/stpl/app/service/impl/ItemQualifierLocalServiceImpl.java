package com.stpl.app.service.impl;

import com.stpl.app.service.base.ItemQualifierLocalServiceBaseImpl;
import com.stpl.app.service.persistence.ItemQualifierUtil;

/**
 * The implementation of the item qualifier local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.stpl.app.service.ItemQualifierLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author
 * @see com.stpl.app.service.base.ItemQualifierLocalServiceBaseImpl
 * @see com.stpl.app.service.ItemQualifierLocalServiceUtil
 */
public class ItemQualifierLocalServiceImpl
    extends ItemQualifierLocalServiceBaseImpl {
	/*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.stpl.app.global.service.ItemQualifierLocalServiceUtil} to access the item qualifier local service.
     */
    public com.stpl.app.model.ItemQualifier findByItemIrtQualifierByName(
        java.lang.String itemQualifierValue)
        throws com.stpl.app.NoSuchItemQualifierException,
            com.stpl.portal.kernel.exception.SystemException {
        return ItemQualifierUtil.findByItemIrtQualifierByName(itemQualifierValue);
    }
}
