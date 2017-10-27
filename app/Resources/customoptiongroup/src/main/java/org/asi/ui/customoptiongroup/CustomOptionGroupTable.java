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

import com.vaadin.data.Container;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.Table;

/**
 *
 * @author Abhiram
 */
public class CustomOptionGroupTable extends AbstractOptionGroupTable {

    AbstractSelect table;
    String className;
    String caption;
    Container container;
    boolean selectable;
    public CustomOptionGroupTable(String className, Container container, String caption) {
        super(container);
        this.container = container;
        this.className = className;
        this.caption = caption;
        createTable();

    }

    public CustomOptionGroupTable(Container container, String caption) {
        super(container);
        this.container = container;
        this.caption = caption;
        createTable();

    }

    public CustomOptionGroupTable(String className, Container container) {
        super(container);
        this.container = container;
        this.className = className;
        createTable();

    }

    public CustomOptionGroupTable(Container container) {
        super(container);
        this.container = container;
        createTable();

    }

    public AbstractSelect getTableObject() {
        return this.table;
    }   
    public void setSelectable(boolean select){
        selectable=select;
        if(!select){
            table.addStyleName("non-selectable");
        }
        else{
            table.removeStyleName("non-selectable");
        }
    }
    public void setMultiSelect(boolean select){
            customOptionGroup.setMultiSelect(select);
      
    }
    public CustomOptionGroup getCustomOptionGroupObject() {
        return customOptionGroup;
    }

    private void createTable() {
        if (className == null) {
            table = new Table();
        } else {
            try {
                table = (AbstractSelect) Class.forName(className).newInstance();
            } catch (Exception e) {
            }
        }
        if (caption != null) {
            table.setCaption(caption);
        }
        table.setContainerDataSource(customOptionGroup.getContainerDataSource());

        customOptionGroup = new CustomOptionGroup(container) {
            @Override
            public void setImmediate(boolean immediate) {
                super.setImmediate(immediate);
                table.setImmediate(true);
            }

            @Override
            public void setMultiSelect(boolean multiSelect) {
                super.setMultiSelect(multiSelect);
                if(selectable){
                    table.setMultiSelect(multiSelect);
            }
            }

            @Override
            public void setEnabled(boolean enabled) {
                super.setEnabled(enabled);
                table.setEnabled(enabled);
            }

            @Override
            public void setReadOnly(boolean readOnly) {
                super.setReadOnly(readOnly);
                table.setReadOnly(readOnly);
            }
        };

        customOptionGroup.setImmediate(true);
        customOptionGroup
                .setPropertyDataSource(new ObjectProperty<Object>(null,
                                Object.class));

        table.setPropertyDataSource(customOptionGroup
                .getPropertyDataSource());
        addComponent(table);
    }
}
