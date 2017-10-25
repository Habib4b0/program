package com.stpl.app.parttwo.model.impl;

import com.stpl.app.parttwo.model.AccClosureDetails;
import com.stpl.app.parttwo.service.AccClosureDetailsLocalServiceUtil;

import com.stpl.portal.kernel.exception.SystemException;

/**
 * The extended model base implementation for the AccClosureDetails service. Represents a row in the &quot;ACC_CLOSURE_DETAILS&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link AccClosureDetailsImpl}.
 * </p>
 *
 * @author
 * @see AccClosureDetailsImpl
 * @see com.stpl.app.parttwo.model.AccClosureDetails
 * @generated
 */
public abstract class AccClosureDetailsBaseImpl
    extends AccClosureDetailsModelImpl implements AccClosureDetails {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a acc closure details model instance should use the {@link AccClosureDetails} interface instead.
     */
    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            AccClosureDetailsLocalServiceUtil.addAccClosureDetails(this);
        } else {
            AccClosureDetailsLocalServiceUtil.updateAccClosureDetails(this);
        }
    }
}
