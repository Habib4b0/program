package com.stpl.library;


import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.jboss.logging.Logger;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Table;

@SuppressWarnings("serial")
public class GTNSharedLibraryUI extends UI{
    private static final Logger LOGGER = Logger.getLogger(GTNSharedLibraryUI.class);
    protected static BeanItemContainer<GTNSharedLibraryInfoDTO> container=new BeanItemContainer<GTNSharedLibraryInfoDTO>(GTNSharedLibraryInfoDTO.class);
    @Override
    protected void init(VaadinRequest request) {
        final VerticalLayout layout = new VerticalLayout();
        layout.setSpacing(false);
        setContent(layout);
        Table table=new Table("GTN Shared Library Information");
        table.setImmediate(true);
        table.setContainerDataSource(container);
        table.setVisibleColumns(new Object[]{"libraryName","jarName","jarPath"});
        table.setColumnHeaders(new String[]{"Library Name","Jar Name","Jar Path"});
        layout.addComponent(table);
        table.setPageLength(20);
        table.setSizeFull();
    }
}
