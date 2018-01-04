/**
 * 
 */
package com.stpl.ifs.ui;



import com.vaadin.navigator.View;
import com.vaadin.v7.shared.ui.label.ContentMode;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.v7.ui.Label;
import com.vaadin.v7.ui.Table;
import com.vaadin.v7.ui.VerticalLayout;
import org.asi.ui.addons.lazycontainer.LazyBeanContainer;

/**
 * Gives basic design for the search form
 * 
 * @author arankumar
 *
 */
public abstract class AbstractSearchForm  extends CustomComponent implements View{
	private static final long serialVersionUID = 1L;
	final Label space = new Label("&nbsp;", ContentMode.HTML);
	
	/**
	 * 
	 * @param searchResultbeans the results that will be displayed in table
	 * @param table
	 */
	public AbstractSearchForm(LazyBeanContainer searchResultbeans,Table table) {
		init();
	}
        /**
         * method to set space height and configures and adds the content of the form
         */
	private void init() {
		space.setHeight("30");
	}
	
	/**
         * method sets space between the component vertically
         * @return 
         */
	protected VerticalLayout addToContentAbstract() {
		final VerticalLayout content = new VerticalLayout();
		content.addComponentAsFirst(space);
		setCompositionRoot(content);
		return content;
	}
		
	/**
	 * Grid Layout to define your form in a grid Layout. Add your form components here 
     	 * @return GridLayout
	 */
	protected abstract GridLayout addToGrid(); 
	/**
	 * Configure your form fields here
	 */
	protected abstract void configureFields();
}