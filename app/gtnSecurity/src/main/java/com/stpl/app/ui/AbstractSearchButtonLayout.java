/**
 * 
 */
package com.stpl.app.ui;

import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.vaadin.v7.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.v7.ui.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author arankumar
 *
 */
public abstract class AbstractSearchButtonLayout  extends HorizontalLayout{

	private static final long serialVersionUID = 1L;
        
        private static final Logger LOGGER = LoggerFactory.getLogger(AbstractSearchButtonLayout.class);

	
	private ErrorfulFieldGroup binder;
	
	private BeanItemContainer<?> searchResultbeans;
	private String okLabel;
	private String resetLabel;

	public AbstractSearchButtonLayout(ErrorfulFieldGroup binder,BeanItemContainer<?> searchResultbeans,Table table,String okLabel,String resetLabel) {
		super();
		this.binder = binder;
		this.searchResultbeans =searchResultbeans;
		this.okLabel = okLabel;
		this.resetLabel = resetLabel;
		init();
	}

	private void init(){
		this.setSpacing(true);
		SearchButton();
		ResetButton();
	}

	private void SearchButton() {
		// Commit button
		Button btnSearch = new Button(okLabel);
		btnSearch.setWidth("75");
		btnSearch.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

			
			public void buttonClick(ClickEvent event) {
				try {
					binder.getFields();
					binder.commit();
					
					searchResultbeans.removeAllItems();
					
					btnSearchLogic();
					
					Notification notif = new Notification("Search Completed",
							Notification.Type.HUMANIZED_MESSAGE);
					// Customize it
					notif.setPosition(Position.MIDDLE_CENTER);
					notif.setStyleName("mystyle");
					notif.show(Page.getCurrent());
				} catch (CommitException e) {
                                     LOGGER.error(e.getMessage());
					
				}
			}
		});
		this.addComponent(btnSearch);
	}

	private void ResetButton() {
		Button btnReset = new Button(resetLabel);
		btnReset.setWidth("75");
		btnReset.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

			public void buttonClick(ClickEvent event) {
				btnResetLogic();
			}
		});
		this.addComponent(btnReset);
	}
	
	/**
	 * 
	 * Call the Search logic here and searchResultbeans.addAll(<result List>);
	 * Also implement the identifier , identifier type column hide unhide based on the search type here
	 * @param searchResultbeans Bean container containing the result list
	 * @return
	 */
	protected abstract void btnSearchLogic();
	

	
	/**
	 * To empty the Search contents provide searchResultbeans.removeAllItems(); 
	 * Call either
	 * 1. binder.discard(); - to revert back to last valid values (or)
	 * 2. binder.setItemDataSource(new BeanItem<SearchDTO>(new SearchDTO())); - to empty the form 
	 * 
	 * 
	 * @param searchResultbeans Bean container containing the result list
	 * @return
	 */
	protected abstract void btnResetLogic();

}
