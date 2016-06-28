package com.stpl.app.model.impl;

import com.stpl.app.model.DocDetails;
import com.stpl.app.service.DocDetailsLocalServiceUtil;

import com.stpl.portal.kernel.exception.SystemException;

/**
 * The extended model base implementation for the DocDetails service. Represents a row in the &quot;DOC_DETAILS&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link DocDetailsImpl}.
 * </p>
 *
 * @author
 * @see DocDetailsImpl
 * @see com.stpl.app.model.DocDetails
 * @generated
 */
public abstract class DocDetailsBaseImpl extends DocDetailsModelImpl
    implements DocDetails {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a doc details model instance should use the {@link DocDetails} interface instead.
     */
    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            DocDetailsLocalServiceUtil.addDocDetails(this);
        } else {
            DocDetailsLocalServiceUtil.updateDocDetails(this);
        }
    }
}
