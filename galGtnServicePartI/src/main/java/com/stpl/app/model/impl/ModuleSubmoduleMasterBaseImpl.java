package com.stpl.app.model.impl;

import com.stpl.app.model.ModuleSubmoduleMaster;
import com.stpl.app.service.ModuleSubmoduleMasterLocalServiceUtil;

import com.stpl.portal.kernel.exception.SystemException;

/**
 * The extended model base implementation for the ModuleSubmoduleMaster service. Represents a row in the &quot;MODULE_SUBMODULE_MASTER&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ModuleSubmoduleMasterImpl}.
 * </p>
 *
 * @author
 * @see ModuleSubmoduleMasterImpl
 * @see com.stpl.app.model.ModuleSubmoduleMaster
 * @generated
 */
public abstract class ModuleSubmoduleMasterBaseImpl
    extends ModuleSubmoduleMasterModelImpl implements ModuleSubmoduleMaster {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a module submodule master model instance should use the {@link ModuleSubmoduleMaster} interface instead.
     */
    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ModuleSubmoduleMasterLocalServiceUtil.addModuleSubmoduleMaster(this);
        } else {
            ModuleSubmoduleMasterLocalServiceUtil.updateModuleSubmoduleMaster(this);
        }
    }
}
