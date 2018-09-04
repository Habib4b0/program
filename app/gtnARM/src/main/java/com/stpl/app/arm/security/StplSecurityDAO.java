/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.security;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.User;

/**
 * <!-- begin-UML-doc -->
 * Defines&nbsp;DAO&nbsp;operations&nbsp;for&nbsp;Security<br><br>@author&nbsp;shrihariharan<br>
 * <!-- end-UML-doc -->
 *
 * @author Sibi
 * @generated "UML to JPA
 * (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
 */
public interface StplSecurityDAO {

    /**
     * <!-- begin-UML-doc -->
     * Returns&nbsp;the&nbsp;user&nbsp;details&nbsp;corresponding&nbsp;to&nbsp;the&nbsp;userId<br><br>@param&nbsp;userId<br>@return&nbsp;User&nbsp;modal&nbsp;object<br>@throws&nbsp;SystemException<br>@throws&nbsp;PortalException
     * <!-- end-UML-doc -->
     *
     * @param userId
     * @return
     * @throws SystemException
     * @throws PortalException
     * @generated "UML to JPA
     * (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
     */
    User getUserByUserId(long userId) throws PortalException;
}
