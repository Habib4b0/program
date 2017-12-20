package org.asi.ui.customoptiongroup;


import com.vaadin.server.Resource;
import com.vaadin.ui.AbstractComponent;
import org.asi.ui.customoptiongroup.client.CustomOptionGroupItemComponentServerRpc;
import org.asi.ui.customoptiongroup.client.CustomOptionGroupItemComponentState;

/**
 * {@link CustomOptionGroupItemComponent} represents a radio button or a check
 * box of an item in {@link CustomOptionGroup}.
 * 
 * @author Abhiram
 * 
 */
public class CustomOptionGroupItemComponent extends AbstractComponent {

	private final CustomOptionGroup owner;
	private final Object itemId;

	private CustomOptionGroupItemComponentServerRpc rpc = new CustomOptionGroupItemComponentServerRpc() {

                @Override
		public void selected(boolean selected) {
			if (selected) {
				owner.select(itemId);
			} else {
				owner.unselect(itemId);
			}
		}
	};

	protected CustomOptionGroupItemComponent(CustomOptionGroup owner,
			Object itemId) {
		this.owner = owner;
		this.itemId = itemId;
		registerRpc(rpc);
	}

	@Override
	public void beforeClientResponse(boolean initial) {
		super.beforeClientResponse(initial);
		if (!owner.containsId(itemId)) {
			throw new IllegalStateException(
					"The owner CustomOptionGroup does not contain an item with itemId '"
							+ itemId + "'.");
		}

		getState().ownerId = owner.id;
		getState().selected = owner.isSelected(itemId);
		getState().enabled = owner.isEnabled() && isEnabled();
		getState().multiSelect = owner.isMultiSelect();
		getState().readOnly = owner.isReadOnly();
	}

	/**
	 * Returns the itemId of this CustomOptionGroupItemComponent.
	 * 
	 * @return the itemId of this CustomOptionGroupItemComponent
	 */
	public Object getItemId() {
		return itemId;
	}

	/**
	 * Returns the owner CustomOptionGroup of this
	 * CustomOptionGroupItemComponent. Should never return null.
	 * 
	 * @return the owner CustomOptionGroup of this
	 *         CustomOptionGroupItemComponent
	 */
	public CustomOptionGroup getOwner() {
		return owner;
	}

	/**
	 * Sets the caption of this CustomOptionGroupItemComponent. The method
	 * does the same as calling getOwner().setItemCaption(getItemId(), caption).
	 * 
	 * @param caption
	 *            the caption of this CustomOptionGroupItemComponent
	 */
	@Override
	public void setCaption(String caption) {
		owner.setItemCaption(itemId, caption);
	}

	/**
	 * Returns the caption of this CustomOptionGroupItemComponent. The method
	 * returns the same value as calling getOwner().getItemCaption(getItemId()).
	 * 
	 * @return the caption of this CustomOptionGroupItemComponent
	 */
	@Override
	public String getCaption() {
		return owner.getItemCaption(itemId);
	}

	@Override
	public void setIcon(Resource icon) {
		owner.setItemIcon(itemId, icon);
	}

	@Override
	public Resource getIcon() {
		return owner.getItemIcon(itemId);
	}

	@Override
	public boolean isEnabled() {
		return owner.isItemEnabled(itemId);
	}

	@Override
	public void setEnabled(boolean enabled) {
		owner.setItemEnabled(itemId, enabled);
	}

	@Override
	public boolean isReadOnly() {
		return owner.isReadOnly();
	}

	@Override
	public void setReadOnly(boolean readOnly) {
		owner.setReadOnly(readOnly);
	}

	

	@Override
	public CustomOptionGroupItemComponentState getState() {
		return (CustomOptionGroupItemComponentState) super.getState();
	}
}