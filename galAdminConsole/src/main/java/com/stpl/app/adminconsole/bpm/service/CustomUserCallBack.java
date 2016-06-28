package com.stpl.app.adminconsole.bpm.service;

import com.stpl.app.adminconsole.util.ConstantsUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kie.internal.task.api.UserGroupCallback;

import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.model.Role;
import com.stpl.portal.model.User;
import com.stpl.portal.service.RoleLocalServiceUtil;
import com.stpl.portal.service.UserLocalServiceUtil;
import org.jboss.logging.Logger;

/**
 *
 * @author arulmurugan
 */
public class CustomUserCallBack implements UserGroupCallback {

    Map<String, User> userMap = new HashMap<String, User>();
    Map<String, Role> roleMap = new HashMap<String, Role>();
    private static final Logger LOGGER = Logger.getLogger(CustomUserCallBack.class);

    public CustomUserCallBack() {
        try {
            List<User> usersList = UserLocalServiceUtil.getUsers(0, UserLocalServiceUtil.getUsersCount());
            List<Role> groupList = RoleLocalServiceUtil.getRoles(0, RoleLocalServiceUtil.getRolesCount());
            for (User user : usersList) {
                userMap.put(user.getScreenName(), user);
            }
            for (Role role : groupList) {
                roleMap.put(role.getName(), role);
            }
            if (userMap.containsKey(ConstantsUtils.ADMINISTRATOR)) {
                userMap.remove(ConstantsUtils.ADMINISTRATOR);
            }
            if (roleMap.containsKey(ConstantsUtils.ADMINISTRATOR)) {
                roleMap.remove(ConstantsUtils.ADMINISTRATOR);
            }
        } catch (SystemException e) {
            LOGGER.error(e);
        }
    }

    public boolean existsUser(String userId) {
        return userMap.containsKey(userId) || userId.equals(ConstantsUtils.ADMINISTRATOR);
    }

    public boolean existsGroup(String groupId) {
        return roleMap.containsKey(groupId);
    }

    public List<String> getGroupsForUser(String userId, List<String> roleIds, List<String> allExistingGroupIds) {
        List<String> userRoles = new ArrayList<String>();
        List<Role> roles = null;
        if (userMap.containsKey(userId)) {
            try {
                roles = RoleLocalServiceUtil.getUserRoles(userMap.get(userId).getUserId());
            } catch (SystemException e) {
                LOGGER.error(e);
            }
            for (Role role : roles) {
                if (!ConstantsUtils.ADMINISTRATOR.equals(role.getName())) {
                    userRoles.add(role.getName());
                }
            }
        }
        return userRoles;
    }
}
