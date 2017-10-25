package com.stpl.app.parttwo.model.impl;

import com.stpl.app.parttwo.model.VwCompanyTradeClass;
import com.stpl.app.parttwo.service.VwCompanyTradeClassLocalServiceUtil;

import com.stpl.portal.kernel.exception.SystemException;

/**
 * The extended model base implementation for the VwCompanyTradeClass service. Represents a row in the &quot;VW_COMPANY_TRADE_CLASS&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link VwCompanyTradeClassImpl}.
 * </p>
 *
 * @author
 * @see VwCompanyTradeClassImpl
 * @see com.stpl.app.parttwo.model.VwCompanyTradeClass
 * @generated
 */
public abstract class VwCompanyTradeClassBaseImpl
    extends VwCompanyTradeClassModelImpl implements VwCompanyTradeClass {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a vw company trade class model instance should use the {@link VwCompanyTradeClass} interface instead.
     */
    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            VwCompanyTradeClassLocalServiceUtil.addVwCompanyTradeClass(this);
        } else {
            VwCompanyTradeClassLocalServiceUtil.updateVwCompanyTradeClass(this);
        }
    }
}
