package com.stpl.app.service.impl;

import com.stpl.app.service.base.MasterDataAttributeLocalServiceBaseImpl;
import com.stpl.app.service.persistence.MasterDataAttributeFinderUtil;

/**
 * The implementation of the master data attribute local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.stpl.app.service.MasterDataAttributeLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author
 * @see com.stpl.app.service.base.MasterDataAttributeLocalServiceBaseImpl
 * @see com.stpl.app.service.MasterDataAttributeLocalServiceUtil
 */
public class MasterDataAttributeLocalServiceImpl
    extends MasterDataAttributeLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.stpl.app.service.MasterDataAttributeLocalServiceUtil} to access the master data attribute local service.
     */
    
      public  java.util.List getTotalLives(java.lang.Object[] inputs) {
        return MasterDataAttributeFinderUtil.getTotalLives(inputs);
    }

    public  java.util.List getLives(java.lang.Object[] inputs) {
        return MasterDataAttributeFinderUtil.getLives(inputs);
    }
}
