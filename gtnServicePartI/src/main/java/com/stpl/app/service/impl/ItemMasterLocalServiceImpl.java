package com.stpl.app.service.impl;

import java.util.List;
import java.util.Map;

import com.stpl.app.service.base.ItemMasterLocalServiceBaseImpl;
import com.stpl.app.service.persistence.ItemMasterFinderUtil;

/**
 * The implementation of the item master local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link com.stpl.app.service.ItemMasterLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author
 * @see com.stpl.app.service.base.ItemMasterLocalServiceBaseImpl
 * @see com.stpl.app.service.ItemMasterLocalServiceUtil
 */
public class ItemMasterLocalServiceImpl extends ItemMasterLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 * 
	 * Never reference this interface directly. Always use {@link
	 * com.stpl.app.global.service.ItemMasterLocalServiceUtil} to access the
	 * item master local service.
	 */
	public List findItemMaster(String itemId, String itemNo, String itemName,
			String itemStatus, String itemType, String itemDesc,
			String manufacturerId, int identifierType, String identifier,
			String brand, String orderByColumn, Boolean sortOrder,
			final Map<String, Object> parameters) {

		return ItemMasterFinderUtil.findItemMaster(itemId, itemNo, itemName,
				itemStatus, itemType, itemDesc, manufacturerId, identifierType,
				identifier, brand, orderByColumn, sortOrder, parameters);

	}
}
