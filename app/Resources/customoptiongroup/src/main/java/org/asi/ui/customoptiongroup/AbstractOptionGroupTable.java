/*
 * Copyright 2014 Abhiram.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.asi.ui.customoptiongroup;

import com.vaadin.v7.data.Container;
import com.vaadin.event.LayoutEvents;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.v7.ui.VerticalLayout;

/**
 *
 * @author Abhiram
 */
public class AbstractOptionGroupTable extends VerticalLayout {

		protected CustomOptionGroup customOptionGroup;

		protected LayoutEvents.LayoutClickListener layoutClickListener = new LayoutEvents.LayoutClickListener() {

			public void layoutClick(LayoutEvents.LayoutClickEvent event) {
				CustomOptionGroupItemComponent c = null;
				boolean allowUnselection = customOptionGroup.isMultiSelect();
				if (event.getChildComponent() instanceof CustomOptionGroupItemComponent) {
					c = (CustomOptionGroupItemComponent) event
							.getChildComponent();
				} else if (event.getChildComponent() instanceof AbstractComponent) {
					Object data = ((AbstractComponent) event
							.getChildComponent()).getData();
					if (data instanceof CustomOptionGroupItemComponent) {
						c = (CustomOptionGroupItemComponent) data;
					}
					if (event.getChildComponent() instanceof HorizontalLayout) {
						allowUnselection = false;
					}
				}
				if (c != null) {
					Object itemId = c.getItemId();
					if (customOptionGroup.isSelected(itemId)
							&& allowUnselection) {
						customOptionGroup.unselect(itemId);
					} else {
						customOptionGroup.select(itemId);
					}
				}
			}
		};

		public AbstractOptionGroupTable(Container container) {
			setMargin(true);
			customOptionGroup = new CustomOptionGroup(container);
		}
}
