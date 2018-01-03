/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.util;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.stpl.app.model.HelperTable;
import com.stpl.app.service.HelperTableLocalServiceUtil;

/**
 *
 * @author Santanukumar
 */
public class CommonUtils {
    public static HashMap<String, Long> getUserInfo() {

        List<User> users = new ArrayList<User>();
        DynamicQuery userGroupDynamicQuery = UserLocalServiceUtil.dynamicQuery();
        users = UserLocalServiceUtil.dynamicQuery(userGroupDynamicQuery);
        HashMap<String, Long> userMap = new HashMap<String, Long>();
        for (User user : users) {
                        userMap.put( user.getLastName()
                    + " " + user.getFirstName(),user.getUserId());
        }
        return userMap;
    }
    public static HashMap<Integer, String> getCategoryNameFromId() {

        List<HelperTable> category = new ArrayList<HelperTable>();
        DynamicQuery categoryDynamicQuery = HelperTableLocalServiceUtil.dynamicQuery();
        categoryDynamicQuery.add(RestrictionsFactoryUtil.ilike("listName", "MailNotificationCategory"));
        category = HelperTableLocalServiceUtil.dynamicQuery(categoryDynamicQuery);
        HashMap<Integer, String> categoryMap = new HashMap<Integer, String>();
        for (HelperTable sts : category) {
            categoryMap.put(sts.getHelperTableSid(), sts.getDescription());

        }
        return categoryMap;
    }
}
