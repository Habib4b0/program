package com.stpl.app.model.impl;

import com.stpl.app.model.RebatePlanTier;
import com.stpl.app.service.RebatePlanTierLocalServiceUtil;

import com.stpl.portal.kernel.exception.SystemException;

/**
 * The extended model base implementation for the RebatePlanTier service. Represents a row in the &quot;REBATE_PLAN_TIER&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link RebatePlanTierImpl}.
 * </p>
 *
 * @author
 * @see RebatePlanTierImpl
 * @see com.stpl.app.model.RebatePlanTier
 * @generated
 */
public abstract class RebatePlanTierBaseImpl extends RebatePlanTierModelImpl
    implements RebatePlanTier {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a rebate plan tier model instance should use the {@link RebatePlanTier} interface instead.
     */
    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            RebatePlanTierLocalServiceUtil.addRebatePlanTier(this);
        } else {
            RebatePlanTierLocalServiceUtil.updateRebatePlanTier(this);
        }
    }
}
