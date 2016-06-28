package com.stpl.app.model.impl;

import com.stpl.app.model.PhsProj;
import com.stpl.app.service.PhsProjLocalServiceUtil;

import com.stpl.portal.kernel.exception.SystemException;

/**
 * The extended model base implementation for the PhsProj service. Represents a row in the &quot;PHS_PROJ&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link PhsProjImpl}.
 * </p>
 *
 * @author
 * @see PhsProjImpl
 * @see com.stpl.app.model.PhsProj
 * @generated
 */
public abstract class PhsProjBaseImpl extends PhsProjModelImpl
    implements PhsProj {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a phs proj model instance should use the {@link PhsProj} interface instead.
     */
    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            PhsProjLocalServiceUtil.addPhsProj(this);
        } else {
            PhsProjLocalServiceUtil.updatePhsProj(this);
        }
    }
}
