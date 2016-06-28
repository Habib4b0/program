package com.stpl.app.model.impl;

import com.stpl.app.model.CdrModel;
import com.stpl.app.service.CdrModelLocalServiceUtil;

import com.stpl.portal.kernel.exception.SystemException;

/**
 * The extended model base implementation for the CdrModel service. Represents a row in the &quot;CDR_MODEL&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CdrModelImpl}.
 * </p>
 *
 * @author
 * @see CdrModelImpl
 * @see com.stpl.app.model.CdrModel
 * @generated
 */
public abstract class CdrModelBaseImpl extends CdrModelModelImpl
    implements CdrModel {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a cdr model model instance should use the {@link CdrModel} interface instead.
     */
    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            CdrModelLocalServiceUtil.addCdrModel(this);
        } else {
            CdrModelLocalServiceUtil.updateCdrModel(this);
        }
    }
}
