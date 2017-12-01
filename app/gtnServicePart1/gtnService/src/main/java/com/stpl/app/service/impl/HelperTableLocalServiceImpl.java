/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.stpl.app.service.impl;

import com.stpl.app.service.base.HelperTableLocalServiceBaseImpl;
import java.util.List;

/**
 * The implementation of the helper table local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.stpl.app.service.HelperTableLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author
 * @see HelperTableLocalServiceBaseImpl
 * @see com.stpl.app.service.HelperTableLocalServiceUtil
 */
public class HelperTableLocalServiceImpl extends HelperTableLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link com.stpl.app.service.HelperTableLocalServiceUtil} to access the helper table local service.
	 */
    
     @Override
    public void executeUpdateQuery(String query) {
        helperTableFinder.executeUpdateQuery(query);
    }

    @Override
    public int executeUpdateQueryCount(String query) {
        return  helperTableFinder.executeUpdateQueryCount(query);
    }

    @Override
    public List executeSelectQuery(String query) {
      return  helperTableFinder.executeSelectQuery(query);  
    }
    
    @Override
    public Object executeUpdateEntitiy(Object  entity){
    return  helperTableFinder.executeUpdateEntitiy(entity);  
    };
}