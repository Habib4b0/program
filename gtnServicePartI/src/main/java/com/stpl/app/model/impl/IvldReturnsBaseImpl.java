package com.stpl.app.model.impl;

import com.stpl.app.model.IvldReturns;
import com.stpl.app.service.IvldReturnsLocalServiceUtil;

import com.stpl.portal.kernel.exception.SystemException;

/**
 * The extended model base implementation for the IvldReturns service. Represents a row in the &quot;IVLD_RETURNS&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link IvldReturnsImpl}.
 * </p>
 *
 * @author
 * @see IvldReturnsImpl
 * @see com.stpl.app.model.IvldReturns
 * @generated
 */
public abstract class IvldReturnsBaseImpl extends IvldReturnsModelImpl
    implements IvldReturns {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a ivld returns model instance should use the {@link IvldReturns} interface instead.
     */
    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            IvldReturnsLocalServiceUtil.addIvldReturns(this);
        } else {
            IvldReturnsLocalServiceUtil.updateIvldReturns(this);
        }
    }
}
