/**
 * BeforeUnload.java (BeforeUnload)
 *
 * Copyright 2013 Vaadin Ltd, Sami Viitanen <alump@vaadin.org>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.vaadin.alump.beforeunload;

import com.vaadin.event.ConnectorEvent;
import com.vaadin.server.AbstractExtension;
import com.vaadin.server.Extension;
import com.vaadin.ui.UI;
import java.io.Serializable;
import java.lang.reflect.Method;
import org.vaadin.alump.beforeunload.gwt.client.BeforeUnloadRpc;

/**
 * Simple extension that offers access to onBeforeUnload events and this way
 * adding verification dialogs when user is trying to exit/reload the
 * application page. In normal use case you want to call
 * BeforeUnload.setExitVerification(message) to enable verification and then
 * BeforeUnload.unsetExitVerification() to disable it.
 */
public class BeforeUnload extends AbstractExtension {
    UI currentUI;
    private final BeforeUnloadRpc rpc1 = () -> {
        fireEvent(new UnloadEvent(BeforeUnload.this));
    };

    private void setUI(UI currentUI) {
        this.currentUI=currentUI;
    }
    public static class UnloadEvent extends ConnectorEvent {
        public static final Method UNLOAD_METHOD;
        private static final long serialVersionUID = 1L;
 
        static {
            try {
                System.out.println("Enters the UNLOAD_METHOD variable part");
                UNLOAD_METHOD = UnloadListener.class
                        .getDeclaredMethod("unload",
                                new Class[]{UnloadEvent.class});
            } catch (final java.lang.NoSuchMethodException e) {
                // This should never happen
                throw new java.lang.RuntimeException(e);
            }
        }
        /**
         * Constructs a new event with the specified source component.
         * 
         * @param source
         *            the source component of the event
         */
        public UnloadEvent(Extension source) {
            super(source);
            System.out.println("Enters tis poart");
        }

        /**
         * Gets the component where the event occurred.
         * 
         * @return the source component of the event
         */
        public Extension getExtension() {
            return (Extension) getSource();
        }

    }
   

    public interface UnloadListener extends Serializable {

        public void unload(UnloadEvent event);
    }

    /**
     * Adds a click listener to the TextField.
     *
     * @param listener The listener to attach to the TextField
     */
    public void addUnloadListener(UnloadListener listener) {
        addListener("unload",
                UnloadEvent.class, listener,
                UnloadEvent.UNLOAD_METHOD);
    }

    /**
     * Removes a click listener from the TextField.
     *
     * @param listener The listener to remove
     */
    public void removeClickListener(UnloadListener listener) {
        removeListener("unload",
                UnloadEvent.class, listener);
    }

    /**
     * @deprecated As of 7.0, replaced by
     * {@link #addClickListener(ClickListener)}
     */
    @Deprecated
    public void addListener(UnloadListener listener) {
        addUnloadListener(listener);
    }

    /**
     * @deprecated As of 7.0, replaced by
     * {@link #removeClickListener(ClickListener)}
     */
    @Deprecated
    public void removeListener(UnloadListener listener) {
        removeClickListener(listener);
    }

    public BeforeUnload() {
        super();
        registerRpc(rpc1);
    }

    /**
     * Get current "singleton" instance of BeforeUnload
     * @param currentUI UI used to resolve instance
     * @return BeforeUnload instance
     */
    protected static BeforeUnload get(UI currentUI) {
        for(Extension extension : currentUI.getExtensions()) {
            if(extension instanceof BeforeUnload) {
                return (BeforeUnload)extension;
            }
        }
        BeforeUnload beforeUnload = new BeforeUnload();
        beforeUnload.extend(currentUI);
        return beforeUnload;
    }

    /**
     * Get current "singleton instance of BeforeUnload
     * @return BeforeUnload instance
     */
    protected static BeforeUnload get() {
        return get(UI.getCurrent());
    }
    
    public static BeforeUnload closeBeforeUnload(UI currentUI) {
        BeforeUnload ob=get(currentUI);
        ob.setUI(currentUI);
        return ob;
    }
}
