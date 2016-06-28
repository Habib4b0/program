package com.stpl.app.parttwo.model.impl;

import com.stpl.app.parttwo.model.CffAdditionalInfo;
import com.stpl.app.parttwo.service.CffAdditionalInfoLocalServiceUtil;

import com.stpl.portal.kernel.exception.SystemException;

/**
 * The extended model base implementation for the CffAdditionalInfo service. Represents a row in the &quot;CFF_ADDITIONAL_INFO&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CffAdditionalInfoImpl}.
 * </p>
 *
 * @author
 * @see CffAdditionalInfoImpl
 * @see com.stpl.app.parttwo.model.CffAdditionalInfo
 * @generated
 */
public abstract class CffAdditionalInfoBaseImpl
    extends CffAdditionalInfoModelImpl implements CffAdditionalInfo {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a cff additional info model instance should use the {@link CffAdditionalInfo} interface instead.
     */
    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            CffAdditionalInfoLocalServiceUtil.addCffAdditionalInfo(this);
        } else {
            CffAdditionalInfoLocalServiceUtil.updateCffAdditionalInfo(this);
        }
    }
}
