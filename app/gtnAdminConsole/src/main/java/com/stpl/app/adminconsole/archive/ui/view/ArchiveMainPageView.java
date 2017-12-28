package com.stpl.app.adminconsole.archive.ui.view;

import com.stpl.app.adminconsole.archive.ui.form.ArchiveIndex;
import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.v7.ui.VerticalLayout;

public class ArchiveMainPageView extends VerticalLayout implements View {

    public static final String NAME = ConstantsUtils.EMPTY;

    public ArchiveMainPageView() {

        super();

        final ArchiveIndex archiveIndex = new ArchiveIndex();
        setSpacing(true);
        addComponent(archiveIndex);

    }

    @Override
    public void enter(final ViewChangeEvent event) {
        return;
    }

}
