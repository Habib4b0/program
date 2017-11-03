package org.asi.ui.customoptiongroup;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.vaadin.data.Container;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.server.PaintException;
import com.vaadin.server.PaintTarget;
import com.vaadin.shared.AbstractFieldState;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HasComponents;
import com.vaadin.ui.OptionGroup;

/**
 * The goal of {@link CustomOptionGroup} is to provide a very flexible way to
 * layout OptionGroup radio buttons or check boxes. The
 * {@link CustomOptionGroup} component itself cannot be added to to any layout
 * ({@link ComponentContainer} ). Well, you can, but it will throw an
 * UnsupportedOperationException. Instead, add
 * {@link CustomOptionGroupItemComponent}s to your layout. Each
 * {@link CustomOptionGroupItemComponent} represents a radio button or a check
 * box of an item.
 * 
 * To get {@link CustomOptionGroupItemComponent}s you can use the
 * {@link #getItemComponentIterator()} that returns an Iterator or use the
 * {@link #getItemComponent(Object itemId)} to get a
 * {@link CustomOptionGroupItemComponent} for a specific itemId. For the same
 * itemId, the same instance of {@link CustomOptionGroupItemComponent} is
 * always returned.
 * 
 * 
 * @author Abhiram
 * 
 */
public class CustomOptionGroup extends OptionGroup {

	protected Map<Object, CustomOptionGroupItemComponent> itemComponentMap = new HashMap<Object, CustomOptionGroupItemComponent>();
	private static int nextId = 0;
	final int id;

	/**
	 * Creates an empty {@link CustomOptionGroup}.
	 */
	public CustomOptionGroup() {
		id = nextId++;
		setContainerDataSource(new IndexedContainer());
	}

	/**
	 * Creates a {@link CustomOptionGroup} and binds it to the given
	 * Container.
	 * 
	 * @param dataSource
	 */
	public CustomOptionGroup(Container dataSource) {
		id = nextId++;
		setContainerDataSource(dataSource);
	}

	public CustomOptionGroup(Collection<?> options) {
		id = nextId++;
		final Container c = new IndexedContainer();
		if (options != null) {
			for (final Iterator<?> i = options.iterator(); i.hasNext();) {
				c.addItem(i.next());
			}
		}
		setContainerDataSource(c);
	}

	@Override
	public void setContainerDataSource(Container newDataSource) {
		super.setContainerDataSource(newDataSource);
		if (itemComponentMap != null) {
			itemComponentMap.clear();
		}
	}

	protected CustomOptionGroupItemComponent getCustomOptionGroupItem(
			Object itemId) {
		if (!itemComponentMap.containsKey(itemId)) {
			CustomOptionGroupItemComponent customOptionGroupItemComponent = new CustomOptionGroupItemComponent(
					this, itemId);
			itemComponentMap.put(itemId, customOptionGroupItemComponent);
			return customOptionGroupItemComponent;
		} else {
			return itemComponentMap.get(itemId);
		}
	}

	@Override
	public boolean removeItem(Object itemId)
			throws UnsupportedOperationException {
		boolean returnValue = super.removeItem(itemId);
		if (returnValue) {
			itemComponentMap.remove(itemId);
		}
		return returnValue;
	}

	@Override
	public boolean removeAllItems() throws UnsupportedOperationException {
		boolean returnValue = super.removeAllItems();
		if (returnValue) {
			itemComponentMap.clear();
		}
		return returnValue;
	}

	@Override
	public void setParent(HasComponents parent) {
		throw new UnsupportedOperationException(
				"The CustomOptionGroup component cannot be attached to an Application.");
	}

	@Override
	public void paintContent(PaintTarget target) throws PaintException {
	}

	@Override
	public void changeVariables(Object source, Map<String, Object> variables) {
	}

	public CustomOptionGroupItemComponent getItemComponent(Object itemId) {
		if (!containsId(itemId)) {
			throw new IllegalArgumentException("");
		}
		return getCustomOptionGroupItem(itemId);
	}

	public Iterator<CustomOptionGroupItemComponent> getItemComponentIterator() {

		return new Iterator<CustomOptionGroupItemComponent>() {

			private Iterator<?> iterator = getItemIds().iterator();

			public boolean hasNext() {
				return iterator.hasNext();
			}

			public CustomOptionGroupItemComponent next() {
				return getCustomOptionGroupItem(iterator.next());
			}

			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}

	@Override
	public void markAsDirty() {
		super.markAsDirty();
		markItemComponentsAsDirty();
	}

	@Override
	protected AbstractFieldState getState(boolean markAsDirty) {
		if (markAsDirty) {
			markItemComponentsAsDirty();
		}
		return super.getState(markAsDirty);
	}

	private void markItemComponentsAsDirty() {
		if (itemComponentMap != null) {
			for (Entry<Object, CustomOptionGroupItemComponent> e : itemComponentMap
					.entrySet()) {
				e.getValue().markAsDirty();
			}
		}
	}

}
