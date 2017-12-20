package org.asi.ui.customoptiongroup.client;


import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.communication.RpcProxy;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.v7.client.ui.AbstractFieldConnector;
import com.vaadin.shared.ui.Connect;
import com.vaadin.v7.shared.AbstractFieldState;
import org.asi.ui.customoptiongroup.CustomOptionGroupItemComponent;
import org.asi.ui.customoptiongroup.client.VCustomOptionGroupItemComponent.ComponentCheckedListener;

@Connect(CustomOptionGroupItemComponent.class)
public class CustomOptionGroupItemComponentConnector extends
		AbstractFieldConnector implements ComponentCheckedListener {

	private CustomOptionGroupItemComponentServerRpc rpc = RpcProxy.create(
			CustomOptionGroupItemComponentServerRpc.class, this);

	@Override
	protected Widget createWidget() {
		VCustomOptionGroupItemComponent widget = GWT
				.create(VCustomOptionGroupItemComponent.class);
		widget.setCheckedListener(this);
		return widget;
	}

	@Override
	public VCustomOptionGroupItemComponent getWidget() {
		return (VCustomOptionGroupItemComponent) super.getWidget();
	}

	@Override
	public AbstractFieldState getState() {
		return (CustomOptionGroupItemComponentState) super.getState();
	}
	

	@Override
	public boolean delegateCaptionHandling() {
		return false;
	}

	@Override
	public void onStateChanged(StateChangeEvent stateChangeEvent) {
		super.onStateChanged(stateChangeEvent);
		getWidget().setMultiSelect(((CustomOptionGroupItemComponentState)getState()).multiSelect);
		getWidget().setOwnerId("" + ((CustomOptionGroupItemComponentState)getState()).ownerId);
		getWidget().setSelected(((CustomOptionGroupItemComponentState)getState()).selected);
		getWidget().setEnabled(isEnabled() && !isReadOnly());

	}

	public void checked(boolean checked) {
		rpc.selected(checked);
	}
}
