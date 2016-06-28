package com.stpl.app.model.impl;

import com.stpl.app.model.CfpModel;
import com.stpl.app.service.CfpModelLocalServiceUtil;

import com.stpl.portal.kernel.exception.SystemException;

/**
 * The extended model base implementation for the CfpModel service. Represents a row in the &quot;CFP_MODEL&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CfpModelImpl}.
 * </p>
 *
 * @author
 * @see CfpModelImpl
 * @see com.stpl.app.model.CfpModel
 * @generated
 */
public abstract class CfpModelBaseImpl extends CfpModelModelImpl
    implements CfpModel {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a cfp model model instance should use the {@link CfpModel} interface instead.
     */
    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            CfpModelLocalServiceUtil.addCfpModel(this);
        } else {
            CfpModelLocalServiceUtil.updateCfpModel(this);
        }
    }
}
