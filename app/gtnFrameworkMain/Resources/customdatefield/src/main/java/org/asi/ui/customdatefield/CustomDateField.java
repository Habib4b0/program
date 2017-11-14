package org.asi.ui.customdatefield;

import com.vaadin.data.Property;
import com.vaadin.shared.EventId;
import com.vaadin.ui.Component;
import com.vaadin.ui.PopupDateField;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

public class CustomDateField extends PopupDateField{
    public CustomDateField() {
        super();
    }

    public CustomDateField(Property dataSource) throws IllegalArgumentException {
        super(dataSource);
    }

    public CustomDateField(String caption, Date value) {
        super(caption, value);
    }

    public CustomDateField(String caption, Property dataSource) {
        super(caption, dataSource);
    }

    public CustomDateField(String caption) {
        super(caption);
    }    
   
     @Override
    public void changeVariables(Object source, Map<String, Object> variables) {
        if (variables.containsKey(EventId.CLICK_EVENT_IDENTIFIER)) {
                fireEvent(new ClickEvent(this));
            }
        super.changeVariables(source, variables);
    }
   

     public static class ClickEvent extends Component.Event {
public static final Method CLICK_METHOD;

        static {
            try {
                CLICK_METHOD = ClickListener.class
                        .getDeclaredMethod(EventId.CLICK_EVENT_IDENTIFIER,
                                new Class[] { ClickEvent.class });
            } catch (final java.lang.NoSuchMethodException e) {
                // This should never happen
                throw new java.lang.RuntimeException(e);
            }
        }
        public ClickEvent(Component source) {
            super(source);
        }
        
    }

     public interface ClickListener extends Serializable {
        public void click(ClickEvent event);
    }
    
      /**
     * Adds a click listener to the TextField.
     * 
     * @param listener
     *            The listener to attach to the TextField
     */
    public void addClickListener(ClickListener listener) {
        addListener(EventId.CLICK_EVENT_IDENTIFIER,
                ClickEvent.class, listener,
                ClickEvent.CLICK_METHOD);
    }

    /**
     * @deprecated As of 7.0, replaced by
     *             {@link #addClickListener(ClickListener)}
     **/
    @Deprecated
    public void addListener(ClickListener listener) {
        addClickListener(listener);
    }

    /**
     * Removes a click listener from the TextField.
     * 
     * @param listener
     *            The listener to remove
     */
    public void removeClickListener(ClickListener listener) {
        removeListener(EventId.CLICK_EVENT_IDENTIFIER,
                ClickEvent.class, listener);
    }

    /**
     * @deprecated As of 7.0, replaced by
     *             {@link #removeClickListener(ClickListener)}
     **/
    @Deprecated
    public void removeListener(ClickListener listener) {
        removeClickListener(listener);
    }
}
