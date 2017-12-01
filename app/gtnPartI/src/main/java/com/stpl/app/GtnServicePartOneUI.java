package com.stpl.app;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.stpl.app.model.UsergroupBusinessrole;
import com.stpl.app.service.UsergroupBusinessroleLocalServiceUtil;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

public class GtnServicePartOneUI extends UI {
    
    @Override
    protected void init(VaadinRequest request) {
        
        // TODO Auto-generated catch block
        System.out.println("Hi -----");
        VerticalLayout layout = new VerticalLayout();
        BundleContext bundleContext = FrameworkUtil.getBundle(
                GtnServicePartOneUI.class).getBundleContext();
        int count = 0;
//        ServiceTracker<CompanyMasterLocalService, CompanyMasterLocalService> tracker
//                = new ServiceTracker<>(bundleContext, CompanyMasterLocalService.class, null);
//
//        tracker.open();
//
//        CompanyMasterLocalService regionLocalService = tracker.getService();
//
//        try {
//            count = regionLocalService.getCompanyMastersCount();
//        } catch (Exception e) {
//             layout.addComponent(new Label("Expection is - " + e.toString()));
//            e.printStackTrace();
//        }
        User user = null;
        try {
            user = UserLocalServiceUtil.getUser(Long.parseLong(request.getRemoteUser()));
            final Collection<Integer> userGroupId = Arrays.asList(54219, 54211,
                    54215,
                    54219,
                    55515,
                    3201,
                    13324,
                    13354,
                    10467,
                    13610,
                    826211,
                    11457
            ) ;//            for (int i = 0; i < user.getUserGroups().size(); i++) {
                    //                final Long userGroup = user.getUserGroups().get(i).getUserGroupId();
                    //                userGroupId.add(Integer.parseInt(userGroup.toString()));
                    //                 }

            final DynamicQuery ugBusRoleDynamicQuery = UsergroupBusinessroleLocalServiceUtil.dynamicQuery();
            ugBusRoleDynamicQuery.add(RestrictionsFactoryUtil.in("usergroupId", userGroupId));
            List<UsergroupBusinessrole> roles = UsergroupBusinessroleLocalServiceUtil.dynamicQuery(ugBusRoleDynamicQuery);
            roles.stream().forEach(e -> layout.addComponent(new Label("No of Company - " + String.valueOf(e.getBusinessroleMasterSid()))));
//                layout.addComponent(new Label("No of Company - " + String.valueOf(count)));
            
        } catch (PortalException ex) {
            layout.addComponent(new Label("Exception is - " + ex.toString()));
        }
        
        System.out.println("Count is  -----" + count);
        layout.addComponent(new Label("No of Company - " + String.valueOf(count)));
        setContent(layout);
    }
    
}
