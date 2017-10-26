/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.stpl.app.model.HelperTable;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.model.User;
import com.stpl.portal.service.UserLocalServiceUtil;

/**
 *
 * @author Santanukumar
 */
public class CommonUtils {
    private static final Logger LOGGER = LogManager
            .getLogger(CommonUtils.class);
    public static HashMap<String, Long> getUserInfo() {

        List<User> users = new ArrayList<User>();
        DynamicQuery userGroupDynamicQuery = DynamicQueryFactoryUtil
                .forClass(User.class);
        try {
            users = UserLocalServiceUtil.dynamicQuery(userGroupDynamicQuery);
        } catch (SystemException ex) {
            LOGGER.error(ex);
        }
        HashMap<String, Long> userMap = new HashMap<String, Long>();
        for (User user : users) {
                        userMap.put( user.getLastName()
                    + " " + user.getFirstName(),user.getUserId());
        }
        return userMap;
    }
    public static HashMap<Integer, String> getCategoryNameFromId() {

        List<HelperTable> category = new ArrayList<HelperTable>();
        DynamicQuery categoryDynamicQuery = DynamicQueryFactoryUtil
                .forClass(HelperTable.class);
        categoryDynamicQuery.add(RestrictionsFactoryUtil.ilike("listName", "MailNotificationCategory"));
        try {
            category = HelperTableLocalServiceUtil.dynamicQuery(categoryDynamicQuery);
        } catch (SystemException ex) {
            LOGGER.error(ex);
        }
        HashMap<Integer, String> categoryMap = new HashMap<Integer, String>();
        for (HelperTable sts : category) {
            categoryMap.put(sts.getHelperTableSid(), sts.getDescription());

        }
        return categoryMap;
    }
}
