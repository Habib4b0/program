package de.steinwedel.messagebox;

import java.util.ArrayList;

import com.vaadin.server.Resource;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Window;
import com.vaadin.ui.UI;

import com.vaadin.ui.VerticalLayout;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;



/**
 * <p><b>DESCRIPTION AND FEATURES</b></p>
 * <p>MessageBox is a flexible utility class for generating different styles of messageboxes. 
 * The messagebox is typically a modal dialog, with an icon on the left side, 
 * a message on the right of the icon and some buttons on the bottom of the dialog.
 * </p> 
 * <p>E.g. you can define/customize</p>
 * <ul>
 * <li>the window caption</li>
 * <li>different types of icons (see {@link Icon})</li>
 * <li>a message as plain string or HTML or with a custom component</li>
 * <li>which and how many buttons are placed on the dialog</li>
 * <li>the icon and appearance of the buttons</li>
 * <li>the buttons alignment (left, centered, right)</li>
 * <li>one simple event listener (see {@link MessageBoxListener}) for all buttons</li>
 * </ul>
 * <p>Now, real I18n is supported via {@code java.util.ResourceBundle}. Even a new feature is, that 
 * the dialog size is determined automatically from the message itself - but it can be overridden explicitly, if required.
 * You need less code to show a messagebox in compare to version 1.x of this add-on. Even the naming, packaging and javadoc has been improved.  
 * </p>
 * <p><b>USAGE</b></p>
 * <p>
 * <code>MessageBox.showPlain(Icon.INFO, "Example 1", "Hello World!", ButtonId.OK);</code>
 * This example shows a simple message dialog, with "Example 1" as dialog 
 * caption, an info icon, "Hello World!" as message and an "Ok" labeled button. The 
 * dialog is displayed modally. To receive an event of the pressed button, add an event listener (see 
 * {@link MessageBoxListener}). I have added a demo class (Demo.java) with many examples, to show most of the possible use cases.
 * There are many more ways to display and customize a messagebox.</p>
 * <br>
 * <p><b>LICENSE</b></p>
 * <p>The licenses are suitable for commercial usage.</p>
 * 
 * <p>Code license: Apache License 2.0</p>
 * 
 * <p>Dialog icons:</p>
 * <ul><li>Author: Dieter Steinwedel</li>
 * <li>License: Creative Commons Attribution 2.5 License</li></ul>
 * 
 * <p>Button icons:</p>
 * <ul><li>Author: Mark James</li>
 * <li>URL: <a href="http://www.famfamfam.com/lab/icons/silk/">http://www.famfamfam.com/lab/icons/silk/</a></li>
 * <li>License: Creative Commons Attribution 2.5 License</li></ul>
 * 
 * @author Dieter Steinwedel
 */
public class MessageBox implements ClickListener {
	
	private static final long serialVersionUID = 1L;
	
	// default configurations =================================================
	
	/**
	 * You can override the class {@link ResourceFactory} to customize the default icons. Even the I18n for button captions is implemented in this class. 
	 */
	public static ResourceFactory RESOURCE_FACTORY = new ResourceFactory();

	/**
	 * Switches the default behavior the window between modal and none-modal.
	 * The default value is <code>true</code>.
	 */
	public static boolean DEFAULT_MODAL = true;
	
	/**
	 * Configures the default behavior, if the dialog is automatically closed or not after a dialog button is clicked.
	 * The default value is <code>true</code>.
	 */
	public boolean DEFAULT_AUTO_CLOSE = true;
	
	/**
	 * Configures the default button alignment.
	 * The default value is <code>Alignment.MIDDLE_RIGHT</code>.
	 */
	public Alignment DEFAULT_BUTTON_ALIGNMENT = Alignment.MIDDLE_RIGHT;
	
	/**
	 * You can implement transitions inside the {@link TransitionListener}. 
	 */
	public static TransitionListener TRANSITION_LISTENER;
	
	/**
	 * Defines the default width of the dialog icon. It is recommended to set it unequal '-1px', 
	 * because otherwise there is re-rendering of dialog recognizable when the dialog is displayed.
	 * The cause is, that the icon is lazy loaded. 
	 */
	public static String DEFAULT_ICON_WIDTH = "48px";
	
	/**
	 * Defines the default height of the dialog icon. It is recommended to set it unequal '-1px', 
	 * because otherwise there is re-rendering of dialog recognizable when the dialog is displayed.
	 * The cause is, that the icon is lazy loaded. 
	 */
	public static String DEFAULT_ICON_HEIGHT = "48px";
	
	// dialog specific configurations =========================================
	
	/**
	 * Keeps the reference to the window of the messagebox.
	 */
	protected Window window;
	
	/**
	 * The main layout for the messagebox.
	 */
	protected VerticalLayout mainLayout;
	
	/**
	 * The content layout for the messagebox. It is the first item in the {@link #mainLayout}.
	 */
	protected HorizontalLayout contentLayout;
	
	/**
	 * The button layout for the messagebox. It is the second item in the {@link #mainLayout}.
	 */
	protected HorizontalLayout buttonLayout;
	
	/**
	 * The dialog icon for the message box. It is typically the first item in the {@link #contentLayout}.
	 */
	protected Embedded iconEmbedded;
	
	/**
	 * The title of the messagebox.
	 */
	protected String title;
	
	/**
	 * The component, that displays message. By default it is a label. Typically, this component is the second item in the {@link #contentLayout}.
	 */
	protected Component messageComponent;
	
	/**
	 * The listener is triggered, if set and a button of the messagebox is pressed.
	 */
	protected MessageBoxListener listener;
	
	/**
	 * The configured buttons (and spacers) for the messagebox. They are displayed in the {@link #buttonLayout}.
	 */
	protected ArrayList<Component> buttons;
	
	/**
	 * Keeps the status, if the dialog should be closed automatically after pressing a button or not.
	 */
	protected boolean autoClose;
	
	// constructors ===========================================================
	
	/**
	 * The constructor to initialize the dialog.
	 * @param iconResource The icon resource.
	 * @param title	The title of the dialog
	 * @param messageComponent The component, that displays the message.
	 * @param listener The listener triggered on clicking a button. 
	 * @param buttonIds A list of to displaying buttons.
	 */
	protected MessageBox(Resource iconResource, String title, Component messageComponent, MessageBoxListener listener, ButtonId... buttonIds) {
		if (iconResource != null) {
			this.iconEmbedded = new Embedded(null, iconResource);
		}
		this.title = title;
		this.messageComponent = messageComponent;
		this.listener = listener;
		this.autoClose = DEFAULT_AUTO_CLOSE;
		
		buttons = new ArrayList<Component>();
		for (ButtonId id : buttonIds) {
			buttons.add(RESOURCE_FACTORY.getButton(id));
		}
	}
	
	// methods for customizing the dialog =====================================
	
	/**
	 * Returns the corresponding Button to the buttonId. This is useful, if you want to customize the button.
	 * @param buttonId The button with the corresponding {@link ButtonId}
	 * @return Returns the <a href="http://vaadin.com/api/com/vaadin/ui/Button.html">com.vaadin.ui.Button</a>, if a corresponding button was found. Otherwise null is returned.
	 */
	public Button getButton(ButtonId buttonId) {
		for (Component c : buttons) {
			if (c instanceof Button) {
				Button b = (Button) c;
				if (buttonId.equals(b.getData())) {
					return b;
				}
			}
		}
		return null;
	}
	
	/**
	 * Forces a width for the messagebox.
	 * @param width The forced width.
	 * @return The {@link MessageBox} instance itself.
	 */
	public MessageBox setWidth(String width) {
		window.setWidth(width);
		if (-1f != window.getWidth()) {
			mainLayout.setWidth("100%");
		} else {
			mainLayout.setWidth(-1f, Unit.PIXELS);
		}
		return this;
	}
	
	/**
	 * Forces a width for the messagebox.
	 * @param width The forced width.
	 * @param unit The unit for the width.
	 * @return The {@link MessageBox} instance itself.
	 */
	public MessageBox setWidth(float width, Unit unit) {
		window.setWidth(width, unit);
		if (-1f != window.getWidth()) {
			mainLayout.setWidth("100%");
		} else {
			mainLayout.setWidth(-1f, Unit.PIXELS);
		}
		return this;
	}
	
	/**
	 * Forces a height for the messagebox.
	 * @param height The forced height.
	 * @return The {@link MessageBox} instance itself.
	 */
	public MessageBox setHeight(String height) {
		window.setHeight(height);
		if (-1f != window.getHeight()) {
			mainLayout.setHeight("100%");
		} else {
			mainLayout.setHeight(-1f, Unit.PIXELS);
		}
		return this;
	}
	
	/**
	 * Forces a height for the messagebox.
	 * @param height The forced height.
	 * @param unit The unit for the height.
	 * @return The {@link MessageBox} instance itself.
	 */
	public MessageBox setHeight(float height, Unit unit) {
		window.setHeight(height, unit);
		if (-1f != window.getHeight()) {
			mainLayout.setHeight("100%");
		} else {
			mainLayout.setHeight(-1f, Unit.PIXELS);
		}
		return this;
	}
	
	/**
	 * Switches, if the dialog is shown modal or not.
	 * @param modal If set to <code>true</code>, the dialog is shown modal.
	 * @return The {@link MessageBox} instance itself.
	 */
	public MessageBox setModal(boolean modal) {
		window.setModal(modal);
		return this;
	}
	
	/**
	 * Switches, if the dialog is automatically closed after clicking a button.
	 * @param autoClose Close the dialog automatically, if set to <code>true</code>.
	 * @return The {@link MessageBox} instance itself.
	 */
	public MessageBox setAutoClose(boolean autoClose) {
		this.autoClose = autoClose;
		return this;
	}
	
	/**
	 * Customizes the button alignment. 
	 * @param alignment The new button alignment.
	 * @return The {@link MessageBox} instance itself.
	 */
	public MessageBox setButtonAlignment(Alignment alignment) {
		mainLayout.setComponentAlignment(buttonLayout, alignment);
		return this;
	}
	
	/**
	 * Sets the button width of all buttons for a symmetric appearance.
	 * @param width The button width.
	 * @return The {@link MessageBox} instance itself.
	 */
	public MessageBox setButtonWidth(String width) {
		for (Component c : buttons) {
			if (c instanceof Button) {
				Button b = (Button) c;
				b.setWidth(width);
			}
		}
		return this;
	}
	
	/**
	 * Sets the button width of all buttons for a symmetric appearance.
	 * @param width The button width.
	 * @param unit The Unit.
	 * @return The {@link MessageBox} instance itself.
	 */
	public MessageBox setButtonWidth(float width, Unit unit) {
		for (Component c : buttons) {
			if (c instanceof Button) {
				Button b = (Button) c;
				b.setWidth(width, unit);
			}
		}
		return this;
	}
	
	/**
	 * Returns the <code>Window</code> of the dialog.
	 * @return The <code>Window</code> of the dialog.
	 */
	public Window getWindow() {
		return window;
	}
	
	/**
	 * Returns the <code>Embedded</code>, if the dialog uses an icon.
	 * @return The <code>Embedded</code>, if the dialog uses an icon. 
	 */
	public Embedded getEmbedded() {
		return iconEmbedded;
	}
		
	// methods for showing and closing the dialog =============================
	
	/**
	 * Displays a messagebox with a message formatted as plain text.
	 * @param icon The icon, that should be displayed.
	 * @param title The title of the dialog.
	 * @param plainTextMessage The message as plain text.
	 * @param buttonIds The displayed buttons.
	 * @return The {@link MessageBox} instance itself.
	 */
	public static MessageBox showPlain(Icon icon, String title, String plainTextMessage, ButtonId... buttonIds) {
		return showPlain(icon, title, plainTextMessage, null, buttonIds);
	}
	
	/**
	 * Displays a messagebox with a message formatted as plain text.
	 * @param iconResource The icon as resource.
	 * @param title The title of the dialog.
	 * @param plainTextMessage The message as plain text.
	 * @param buttonIds The displayed buttons
	 * @return The {@link MessageBox} instance itself.
	 */
	public static MessageBox showPlain(Resource iconResource, String title, String plainTextMessage, ButtonId... buttonIds) {
		return showPlain(iconResource, title, plainTextMessage, null, buttonIds);
	}
	
	/**
	 * Displays a messagebox with a message formatted as plain text.
	 * @param icon The icon, that should be displayed.
	 * @param title The title of the dialog.
	 * @param plainTextMessage The message as plain text.
	 * @param listener The event listener, that is triggered on clicking a button.
	 * @param buttonIds The displayed buttons.
	 * @return The {@link MessageBox} instance itself.
	 */
	public static MessageBox showPlain(Icon icon, String title, String plainTextMessage, MessageBoxListener listener, ButtonId... buttonIds) {
		return showHTML(icon, title, encodeToHtml(plainTextMessage), listener, buttonIds);
	}
	
	/**
	 * Displays a messagebox with a message formatted as plain text.
	 * @param iconResource The icon as resource.
	 * @param title The title of the dialog.
	 * @param plainTextMessage The message as plain text.
	 * @param listener The event listener, that is triggered on clicking a button.
	 * @param buttonIds The displayed buttons.
	 * @return The {@link MessageBox} instance itself.
	 */
	public static MessageBox showPlain(Resource iconResource, String title, String plainTextMessage, MessageBoxListener listener, ButtonId... buttonIds) {
		return showHTML(iconResource, title, encodeToHtml(plainTextMessage), listener, buttonIds);
	}
	
	/**
	 * Displays a messagebox with a message formatted as HTML.
	 * @param icon The icon, that should be displayed.
	 * @param title The title of the dialog.
	 * @param htmlMessage The message as HTML.
	 * @param buttonIds The displayed buttons.
	 * @return The {@link MessageBox} instance itself.
	 */
	public static MessageBox showHTML(Icon icon, String title, String htmlMessage, ButtonId... buttonIds) {
		return showHTML(icon, title, htmlMessage, null, buttonIds);
	}
	
	/**
	 * Displays a messagebox with a message formatted as HTML.
	 * @param iconResource The icon as resource.
	 * @param title The title of the dialog.
	 * @param htmlMessage The message as HTML.
	 * @param buttonIds The displayed buttons.
	 * @return The {@link MessageBox} instance itself.
	 */
	public static MessageBox showHTML(Resource iconResource, String title, String htmlMessage, ButtonId... buttonIds) {
		return showHTML(iconResource, title, htmlMessage, null, buttonIds);
	}

	/**
	 * Displays a messagebox with a message formatted as HTML.
	 * @param icon The icon, that should be displayed.
	 * @param title The title of the dialog.
	 * @param htmlMessage The message as HTML.
	 * @param listener The event listener, that is triggered on clicking a button.
	 * @param buttonIds The displayed buttons.
	 * @return The {@link MessageBox} instance itself.
	 */
	public static MessageBox showHTML(Icon icon, String title, String htmlMessage, MessageBoxListener listener, ButtonId... buttonIds) {
		return showCustomized(icon, title, new Label(htmlMessage, ContentMode.HTML), listener, buttonIds);
	}
	
	/**
	 * Displays a messagebox with a message formatted as HTML.
	 * @param iconResource The icon as resource.
	 * @param title The title of the dialog.
	 * @param htmlMessage The message as HTML.
	 * @param listener The event listener, that is triggered on clicking a button.
	 * @param buttonIds The displayed buttons.
	 * @return The {@link MessageBox} instance itself.
	 */
	public static MessageBox showHTML(Resource iconResource, String title, String htmlMessage, MessageBoxListener listener, ButtonId... buttonIds) {
		return showCustomized(iconResource, title, new Label(htmlMessage, ContentMode.HTML), listener, buttonIds);
	}
	
	/**
	 * Displays a messagebox with a custom component for message.
	 * @param icon The icon, that should be displayed.
	 * @param title The title of the dialog.
	 * @param messageComponent The custom component for the message.
	 * @param buttonIds The displayed buttons.
	 * @return The {@link MessageBox} instance itself.
	 */
	public static MessageBox showCustomized(Icon icon, String title, Component messageComponent, ButtonId... buttonIds) {
		return showCustomized(icon, title, messageComponent, null, buttonIds);
	}
	
	
	/**
	 * Displays a messagebox with a custom component for message.
	 * @param icon The icon, that should be displayed.
	 * @param title The title of the dialog.
	 * @param messageComponent The custom component for the message.
	 * @param listener The event listener, that is triggered on clicking a button.
	 * @param buttonIds The displayed buttons.
	 * @return The {@link MessageBox} instance itself.
	 */
	public static MessageBox showCustomized(Icon icon, String title, Component messageComponent, MessageBoxListener listener, ButtonId... buttonIds) {
		MessageBox result = showCustomized(RESOURCE_FACTORY.getIcon(icon), title, messageComponent, listener, buttonIds);
		// Following avoids re-rendering of the window
		if (result.getEmbedded() != null) {
			result.getEmbedded().setWidth(DEFAULT_ICON_WIDTH); 
			result.getEmbedded().setHeight(DEFAULT_ICON_HEIGHT);
		}
		return result;
	}
	
	/**
	 * Displays a messagebox with a custom component for message.
	 * @param iconResource The icon as resource.
	 * @param title The title of the dialog.
	 * @param messageComponent The custom component for the message.
	 * @param listener The event listener, that is triggered on clicking a button.
	 * @param buttonIds The displayed buttons.
	 * @return The {@link MessageBox} instance itself.
	 */
	public static MessageBox showCustomized(Resource iconResource, String title, Component messageComponent, MessageBoxListener listener, ButtonId... buttonIds) {
		MessageBox result = new MessageBox(iconResource, title, messageComponent, listener, buttonIds);
		result.open();
		return result;
	}
	
	/**
	 * Translates plain text to HTML formatted text with corresponding escape sequences.
	 * @param plainText		The plain text to translates.
	 * @return The HTML formatted text.
	 */
	protected static String encodeToHtml(String plainText) {
	    StringBuilder builder = new StringBuilder();
	    boolean previousWasASpace = false;
	    for( char c : plainText.toCharArray() ) {
	        if( c == ' ' ) {
	            if( previousWasASpace ) {
	                builder.append("&nbsp;");
	                previousWasASpace = false;
	                continue;
	            }
	            previousWasASpace = true;
	        } else {
	            previousWasASpace = false;
	        }
	        switch(c) {
	            case '<': builder.append("&lt;"); break;
	            case '>': builder.append("&gt;"); break;
	            case '&': builder.append("&amp;"); break;
	            case '"': builder.append("&quot;"); break;
	            case '\n': builder.append("<br>"); break;
	            case '\t': builder.append("&nbsp; &nbsp; &nbsp;"); break;  
	            default:
	                if( c < 128 ) {
	                    builder.append(c);
	                } else {
	                    builder.append("&#").append((int)c).append(";");
	                }    
	        }
	    }
	    return builder.toString();
	}
	
	/**
	 * Creates and opens the window for the messagebox. 
	 */
	protected void open() {
		// Create window
		window = new Window(title);
		window.setClosable(false);
		window.setModal(DEFAULT_MODAL);
		window.setResizable(false);
		window.setSizeUndefined();
		
		// Create the top-level layout of the window
		mainLayout = new VerticalLayout();
		mainLayout.setSizeUndefined();
		mainLayout.setMargin(true);
		mainLayout.setSpacing(true);
		window.setContent(mainLayout);
		
		// Layout for the dialog body (icon & message)
		contentLayout = new HorizontalLayout();
		mainLayout.addComponent(contentLayout);
		mainLayout.setExpandRatio(contentLayout, 1.0f);
		contentLayout.setSizeFull();
		contentLayout.setMargin(false);
		contentLayout.setSpacing(true);
		
		// Add icon component
		if (iconEmbedded != null) {
			contentLayout.addComponent(iconEmbedded);
			contentLayout.setComponentAlignment(iconEmbedded, Alignment.MIDDLE_CENTER);
		}
		
		// Add message component
		messageComponent.setSizeFull();		
		contentLayout.addComponent(messageComponent);
		contentLayout.setExpandRatio(messageComponent, 1.0f);		
		contentLayout.setComponentAlignment(messageComponent, Alignment.MIDDLE_CENTER);
		
		// Create layout for the buttons 
		buttonLayout = new HorizontalLayout();
		buttonLayout.setSizeUndefined();
		buttonLayout.setMargin(false);
		buttonLayout.setSpacing(true);
		mainLayout.addComponent(buttonLayout);
		mainLayout.setComponentAlignment(buttonLayout, Alignment.MIDDLE_RIGHT);
		
		// Add buttons
		for (Component c : buttons) {
			buttonLayout.addComponent(c);
			if (c instanceof Button) {
				Button b = (Button) c;
				b.addClickListener(this);
			}
			buttonLayout.setComponentAlignment(c, Alignment.MIDDLE_CENTER);
		}		
		
		// Add window to the UI
		if (TRANSITION_LISTENER == null || (TRANSITION_LISTENER != null && TRANSITION_LISTENER.show(this))) {
			UI.getCurrent().addWindow(window);
		}		
	}
	
	/**
	 * Closes the window if open.
	 */
	public void close() {
		if (TRANSITION_LISTENER == null || (TRANSITION_LISTENER != null && TRANSITION_LISTENER.close(this))) {
			UI.getCurrent().removeWindow(window);
		}
	}

	// ClickListener implementation ===========================================
	
	/**
	 * See <a href="http://vaadin.com/api/com/vaadin/ui/Button.ClickListener.html#buttonClick(com.vaadin.ui.Button.ClickEvent)">com.vaadin.ui.Button.ClickListener.buttonClick(com.vaadin.ui.Button.ClickEvent)</a>
	 */
	@Override
	public void buttonClick(ClickEvent event) {
		ButtonId buttonId = (ButtonId) event.getButton().getData();
		if (listener != null) {
			listener.buttonClicked(buttonId);
		}
		if (autoClose && !ButtonId.HELP.equals(buttonId)) {
			close();
		}
		
	}
	
}
