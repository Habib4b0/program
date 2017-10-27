package org.asi.ui.customoptiongroup.client;


import com.vaadin.shared.communication.ServerRpc;

public interface CustomOptionGroupItemComponentServerRpc extends ServerRpc {

	public void selected(boolean selected);
}
