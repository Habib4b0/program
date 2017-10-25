package com.stpl.app.model.impl;

import com.stpl.app.model.BestPriceMaster;
import com.stpl.app.service.BestPriceMasterLocalServiceUtil;

import com.stpl.portal.kernel.exception.SystemException;

/**
 * The extended model base implementation for the BestPriceMaster service. Represents a row in the &quot;BEST_PRICE_MASTER&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link BestPriceMasterImpl}.
 * </p>
 *
 * @author
 * @see BestPriceMasterImpl
 * @see com.stpl.app.model.BestPriceMaster
 * @generated
 */
public abstract class BestPriceMasterBaseImpl extends BestPriceMasterModelImpl
    implements BestPriceMaster {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a best price master model instance should use the {@link BestPriceMaster} interface instead.
     */
    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            BestPriceMasterLocalServiceUtil.addBestPriceMaster(this);
        } else {
            BestPriceMasterLocalServiceUtil.updateBestPriceMaster(this);
        }
    }
}
