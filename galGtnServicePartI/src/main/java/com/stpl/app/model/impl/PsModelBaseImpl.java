package com.stpl.app.model.impl;

import com.stpl.app.model.PsModel;
import com.stpl.app.service.PsModelLocalServiceUtil;

import com.stpl.portal.kernel.exception.SystemException;

/**
 * The extended model base implementation for the PsModel service. Represents a row in the &quot;PS_MODEL&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link PsModelImpl}.
 * </p>
 *
 * @author
 * @see PsModelImpl
 * @see com.stpl.app.model.PsModel
 * @generated
 */
public abstract class PsModelBaseImpl extends PsModelModelImpl
    implements PsModel {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a ps model model instance should use the {@link PsModel} interface instead.
     */
    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            PsModelLocalServiceUtil.addPsModel(this);
        } else {
            PsModelLocalServiceUtil.updatePsModel(this);
        }
    }
}
