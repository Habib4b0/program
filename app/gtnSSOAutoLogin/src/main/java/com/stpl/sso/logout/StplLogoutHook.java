package com.stpl.sso.logout;

import java.io.IOException;

import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.LifecycleAction;
import com.liferay.portal.kernel.events.LifecycleEvent;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import com.stpl.sso.autologin.SSOConstants;
import com.stpl.sso.autologin.config.StplConfigReader;
import com.stpl.sso.saml.StplSAMLRequestGenerator;

import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(immediate = true, property = { "key=logout.events.pre" }, service = LifecycleAction.class)
public class StplLogoutHook implements LifecycleAction {
    private static Logger LOGGER = LoggerFactory.getLogger(StplSAMLRequestGenerator.class.getName());

    @Override
    public void processLifecycleEvent(LifecycleEvent lifecycleEvent) throws ActionException {
        if (SSOConstants.SAML.equals(StplConfigReader.getInstance().getPropertyBean().getSsoType())) {
            ThemeDisplay themeDisplay = (ThemeDisplay) lifecycleEvent.getRequest().getAttribute(WebKeys.THEME_DISPLAY);
            LOGGER.info("In logout " + themeDisplay.getUser().getDisplayEmailAddress());
            try {
                // new Stpl
                lifecycleEvent.getResponse().sendRedirect(
                        new StplSAMLRequestGenerator().getLognRequest(themeDisplay.getUser().getDisplayEmailAddress()));
            } catch (IOException e) {
                LOGGER.error("Logout error ", e);
            }
        }
    }
}