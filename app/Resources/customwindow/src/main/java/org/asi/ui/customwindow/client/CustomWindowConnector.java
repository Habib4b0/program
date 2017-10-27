package org.asi.ui.customwindow.client;

import com.vaadin.client.ApplicationConnection;
import com.vaadin.client.UIDL;
import com.vaadin.client.ui.window.WindowConnector;
import com.vaadin.shared.ui.Connect;
import com.vaadin.shared.ui.window.WindowMode;
import org.asi.ui.customwindow.CustomWindow;

@Connect(CustomWindow.class)
public class CustomWindowConnector extends WindowConnector {
    @Override
    public void updateFromUIDL(UIDL uidl, ApplicationConnection client) {
        super.updateFromUIDL(uidl, client);
        getWidget().headerVisible=uidl.getBooleanAttribute("headervisible");
        getWidget().closeBtnVisible=uidl.getBooleanAttribute("closebtnvisible");
        getWidget().minimizeBtnVisible=uidl.getBooleanAttribute("minimizeBtnVisible");
        getWidget().minimizetray=uidl.getBooleanAttribute("minimizetray");
        getWidget().minimized=uidl.getBooleanAttribute("minimize");
        getWidget().minX=uidl.getIntAttribute("minimizeleft");
        getWidget().minY=uidl.getIntAttribute("minimizetop");
        getWidget().minwidth=uidl.getIntAttribute("minimizewidth");
        getWidget().clintId=getConnectorId();
        getWidget().updateMinimize();
        getWidget().minimizeRestoreBox
                .setId(getConnectorId() + "_window_minimizerestore");
//        if(getWidget().vaadinModality&&getWidget().clintId!=null){
//            Document.get().getBody().addClassName(MODAL_INDICATOR_NAME+"-"+getWidget().clintId);
//        }
        
    }

    @Override
    public VCustomWindow getWidget() {
        return (VCustomWindow) super.getWidget();
    }
    
    @Override
    protected void onMaximizeRestore() {
        if(!getWidget().minimized){
            super.onMaximizeRestore();
            getWidget().winState=(getState().windowMode==WindowMode.MAXIMIZED);
        }
    }
}
