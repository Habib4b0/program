package com.stpl.app.gtnforecasting.bpm.service;

import com.stpl.app.gtnforecasting.utils.Constant;
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

    protected Map<String, User> userMap = new HashMap<>();
    protected Map<String, Role> roleMap = new HashMap<>();
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
            if (userMap.containsKey(Constant.ADMINISTRATOR)) {
                userMap.remove(Constant.ADMINISTRATOR);
            }
            if (roleMap.containsKey(Constant.ADMINISTRATOR)) {
                roleMap.remove(Constant.ADMINISTRATOR);
            }
        } catch (SystemException e) {

            LOGGER.error(e);
        }
    }

    @Override
    public boolean existsUser(String userId) {
        return userMap.containsKey(userId) || userId.equals(Constant.ADMINISTRATOR);
    }

    @Override
    public boolean existsGroup(String groupId) {
        if (groupId.contains(",")) {
            String[] groups = groupId.split(",");
            if (groups != null) {
                for (int i = 0; i < groups.length; i++) {
                    if (roleMap.containsKey(groups[i].trim())) {
                        return true;
                    }

                }
            }
        }
        return roleMap.containsKey(groupId);
    }

    @Override
    public List<String> getGroupsForUser(String userId, List<String> roleIds, List<String> allExistingGroupIds) {
        List<String> userRoles = new ArrayList<>();
        List<Role> roles = null;
        if (userMap.containsKey(userId)) {
            try {
                roles = RoleLocalServiceUtil.getUserRoles(userMap.get(userId).getUserId());
            } catch (SystemException e) {
                LOGGER.error(e);
            }
            for (Role role : roles) {
                if (!Constant.ADMINISTRATOR.equals(role.getName())) {
                    userRoles.add(role.getName());
                }
            }
        }
        return userRoles;
    }
}
