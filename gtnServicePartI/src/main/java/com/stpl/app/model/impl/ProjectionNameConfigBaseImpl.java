package com.stpl.app.model.impl;

import com.stpl.app.model.ProjectionNameConfig;
import com.stpl.app.service.ProjectionNameConfigLocalServiceUtil;

import com.stpl.portal.kernel.exception.SystemException;

/**
 * The extended model base implementation for the ProjectionNameConfig service. Represents a row in the &quot;PROJECTION_NAME_CONFIG&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ProjectionNameConfigImpl}.
 * </p>
 *
 * @author
 * @see ProjectionNameConfigImpl
 * @see com.stpl.app.model.ProjectionNameConfig
 * @generated
 */
public abstract class ProjectionNameConfigBaseImpl
    extends ProjectionNameConfigModelImpl implements ProjectionNameConfig {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a projection name config model instance should use the {@link ProjectionNameConfig} interface instead.
     */
    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ProjectionNameConfigLocalServiceUtil.addProjectionNameConfig(this);
        } else {
            ProjectionNameConfigLocalServiceUtil.updateProjectionNameConfig(this);
        }
    }
}
