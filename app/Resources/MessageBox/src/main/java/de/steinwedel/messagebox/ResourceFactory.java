package de.steinwedel.messagebox;

import java.io.Serializable;
import java.util.Locale;
import java.util.ResourceBundle;

import com.vaadin.server.ClassResource;
import com.vaadin.server.Resource;
import com.vaadin.v7.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.v7.ui.Label;

import de.steinwedel.messagebox.i18n.MBResource;

/**
 * Manages the loading of default resources like icons and button captions. You can override the method <code>getIcon</code> to use customized icons.
 * The overridden <code>DefaultResources</code> must be set to the static member {@link MessageBox#RESOURCE_FACTORY}.
 * You can change the default captions of the buttons by using a different {@link java.util.ResourceBundle}. 
 * You can assign the new {@link java.util.ResourceBundle} with the method {@link #setResourceBundle(java.lang.String)} or {@link #setResourceBundle(java.lang.String, java.util.Locale)} . 
 * 
 * @author Dieter Steinwedel
 */
public class ResourceFactory implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/**
	 * The basename of <code>ResourceBundle</code> for displaying localized button captions. Is set to {@link MBResource} as default.
	 */
	private String basename = MBResource.class.getName();
	
	/**
	 * The <code>ResourceBundle</code> for displaying localized button captions.
	 */
	private ResourceBundle resourceBundle;
	
	/**
	 * Default Constructor.
	 */
	public ResourceFactory() {
		resourceBundle = ResourceBundle.getBundle(basename);
	}
	
	/**
	 * Returns the specified icon as resource.
	 * @param icon				The requested icon
	 * @return					The loaded resource
	 */
	public Resource getIcon(Icon icon) {
		switch (icon) {
		case QUESTION:
			return new ClassResource(ResourceFactory.class, "icons/question.png");
		case INFO:
			return new ClassResource(ResourceFactory.class, "icons/info.png");
		case WARN:
			return new ClassResource(ResourceFactory.class, "icons/warn.png");
		case ERROR:
			return new ClassResource(ResourceFactory.class, "icons/error.png");
		default:
			return null;
		}
	}
	
	/**
	 * Returns to the specified buttonId a corresponding resource.
	 * @param buttonId			The buttonId
	 * @return					The corresponding resource to the buttonId
	 */
	public Resource getIcon(ButtonId buttonId) {
		switch (buttonId) {
		case OK:
			return new ClassResource(ResourceFactory.class, "icons/famfamfam/tick.png");
		case ABORT:
			return new ClassResource(ResourceFactory.class, "icons/famfamfam/cross.png");
		case CANCEL:
			return new ClassResource(ResourceFactory.class, "icons/famfamfam/cross.png");
		case YES:
			return new ClassResource(ResourceFactory.class, "icons/famfamfam/tick.png");
		case NO:
			return new ClassResource(ResourceFactory.class, "icons/famfamfam/cross.png");
		case CLOSE:
			return new ClassResource(ResourceFactory.class, "icons/famfamfam/door.png");
		case SAVE:
			return new ClassResource(ResourceFactory.class, "icons/famfamfam/disk.png");
		case RETRY:
			return new ClassResource(ResourceFactory.class, "icons/famfamfam/arrow_refresh.png");
		case IGNORE:
			return new ClassResource(ResourceFactory.class, "icons/famfamfam/lightning_go.png");
		case HELP:
			return new ClassResource(ResourceFactory.class, "icons/famfamfam/lightbulb.png");
		default:
			return null;
		}
	}
	
	/**
	 * Returns to the specified buttonId a corresponding component. It is normally a button.
	 * @param buttonId			The buttonId
	 * @return					The corresponding component to the buttonId
	 */
	public Component getButton(ButtonId buttonId) {
		if (buttonId == null) throw new NullPointerException("The buttonId should not be null.");
				
		if (ButtonId.SPACER.equals(buttonId)) {
			return new Label("&nbsp;", ContentMode.HTML);
		} else {
			Button result = new Button();
			result.setData(buttonId);
			result.setIcon(getIcon(buttonId));
			result.setCaption(resourceBundle.getString(buttonId.toString()));
			return result;
		}
	}
	
	/**
	 * Does a lookup for <code>ResourceBundle</code> with the new basename. 
	 * @param basename 	The new basename to look up.
	 */
	public void setResourceBundle(String basename) {
		this.basename = basename;
		resourceBundle = ResourceBundle.getBundle(basename);
	}
	
	/**
	 * Does a lookup for <code>ResourceBundle</code> with the new basename an a specific locale. 
	 * @param basename 	The new basename to look up.
	 * @param locale    The specific locale.
	 */
	public void setResourceBundle(String basename, Locale locale) {
		this.basename = basename;
		resourceBundle = ResourceBundle.getBundle(basename, locale);
	}

	/**
	 * Forces a specific locale on the current used <code>ResourceBundle</code>.
	 * @param locale The new locale
	 */
	public void setResourceLocale(Locale locale) {
		resourceBundle = ResourceBundle.getBundle(basename, locale);
	}
	
}
