package com.stpl.app.model.impl;

import com.stpl.app.model.IvldInventoryWdProjMas;
import com.stpl.app.service.IvldInventoryWdProjMasLocalServiceUtil;

import com.stpl.portal.kernel.exception.SystemException;

/**
 * The extended model base implementation for the IvldInventoryWdProjMas service. Represents a row in the &quot;IVLD_INVENTORY_WD_PROJ_MAS&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link IvldInventoryWdProjMasImpl}.
 * </p>
 *
 * @author
 * @see IvldInventoryWdProjMasImpl
 * @see com.stpl.app.model.IvldInventoryWdProjMas
 * @generated
 */
public abstract class IvldInventoryWdProjMasBaseImpl
    extends IvldInventoryWdProjMasModelImpl implements IvldInventoryWdProjMas {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a ivld inventory wd proj mas model instance should use the {@link IvldInventoryWdProjMas} interface instead.
     */
    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            IvldInventoryWdProjMasLocalServiceUtil.addIvldInventoryWdProjMas(this);
        } else {
            IvldInventoryWdProjMasLocalServiceUtil.updateIvldInventoryWdProjMas(this);
        }
    }
}
