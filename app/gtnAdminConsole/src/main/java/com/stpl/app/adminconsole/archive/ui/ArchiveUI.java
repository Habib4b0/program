package com.stpl.app.adminconsole.archive.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.stpl.app.adminconsole.archive.ui.view.ArchiveMainPageView;
import com.stpl.app.adminconsole.util.AbstractNotificationUtils;
import com.stpl.app.adminconsole.util.ErrorCodeUtil;
import com.stpl.app.adminconsole.util.ErrorCodes;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

public class ArchiveUI extends UI {

    @AutoGenerated
    private Navigator navigator;

    private static final Logger LOGGER = LoggerFactory.getLogger(ArchiveUI.class);

    @Override
    public void init(final VaadinRequest request) {
        LOGGER.info("init method started in ui");
        try {
            addStyleName("bootstrap");
            addStyleName("bootstrap-bb");
            addStyleName("bootstrap-admin");

            navigator = new Navigator(this, this);
            navigator.addView(ArchiveMainPageView.NAME, new ArchiveMainPageView());

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4011));
        }
        LOGGER.info("init method Ended");
    }

    public Navigator getNavigator() {
        return navigator;
    }

    public void setNavigator(final Navigator navigator) {
        this.navigator = navigator;
    }
}
