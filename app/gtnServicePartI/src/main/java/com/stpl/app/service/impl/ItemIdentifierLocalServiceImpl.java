package com.stpl.app.service.impl;

import com.stpl.app.service.base.ItemIdentifierLocalServiceBaseImpl;
import com.stpl.app.service.persistence.ItemIdentifierUtil;

/**
 * The implementation of the item identifier local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.stpl.app.service.ItemIdentifierLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author
 * @see com.stpl.app.service.base.ItemIdentifierLocalServiceBaseImpl
 * @see com.stpl.app.service.ItemIdentifierLocalServiceUtil
 */
public class ItemIdentifierLocalServiceImpl
    extends ItemIdentifierLocalServiceBaseImpl {
	 /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.stpl.app.global.service.ItemIdentifierLocalServiceUtil} to access the item identifier local service.
     */
    public java.util.List<com.stpl.app.model.ItemIdentifier> findByItemIrtIdentifierDetails(
        int itemMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return ItemIdentifierUtil.findByItemIrtIdentifierDetails(itemMasterSid);
    }
}
