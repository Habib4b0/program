package com.stpl.gtn.gtn2o.ui.framework.component.grid.component;

import java.time.LocalDateTime;
import java.util.Locale;

import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.bean.SessioBeanForVaadin8Components;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.data.HasValue.ValueChangeEvent;
import com.vaadin.data.HasValue.ValueChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.InlineDateTimeField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class DateFilterPopup {

	GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(DateFilterPopup.class);
	SessioBeanForVaadin8Components sessioBeanForVaadin8Components = SessioBeanForVaadin8Components.getInstance();

	private Button buttonFromGrid;
	private GtnUIFrameworkPagedTableConfig tableConfig;
	private String property;
	GtnUIFrameworkComponentConfig componentConfig;
	public DateFilterPopup(Button button,GtnUIFrameworkPagedTableConfig tableConfig,String property, GtnUIFrameworkComponentConfig componentConfig) {
		this.buttonFromGrid = button;
		this.tableConfig=tableConfig;
		this.property=property;
		this.componentConfig=componentConfig;
	}

	public Window getDateFilterPopup() {
		Window window = new Window();

		VerticalLayout verticalLayout = new VerticalLayout();
		HorizontalLayout horizontalLayout = new HorizontalLayout();
		HorizontalLayout horizontalLayoutForButton = new HorizontalLayout();
		horizontalLayout.setMargin(true);

		InlineDateTimeField inlineDateFieldStartDate = getDateField("Start date:", "inlineDateFieldStartDate");
		InlineDateTimeField inlineDateFieldEndDate = getDateField("End date:", "inlineDateFieldEndDate");

		Button setButton = getButton("Set", "setButton", window);
		Button clearButton = getButton("Clear", "clearButton", window);

		horizontalLayout.addComponents(inlineDateFieldStartDate, inlineDateFieldEndDate);
		horizontalLayoutForButton.addComponents(setButton, clearButton);
		verticalLayout.addComponents(horizontalLayout, horizontalLayoutForButton);

		window.setContent(verticalLayout);
		window.setResizable(false);
		window.setClosable(false);
		return window;
	}

	private void addValueChangeListenerForDate(InlineDateTimeField inlineDateField) {
		inlineDateField.addValueChangeListener(new ValueChangeListener<LocalDateTime>() {

			@Override
			public void valueChange(ValueChangeEvent<LocalDateTime> event) {
				if (inlineDateField.getId().equals("inlineDateFieldStartDate")) {
					sessioBeanForVaadin8Components.setStartDateForFilterGrid(event.getValue());
				} else {
					sessioBeanForVaadin8Components.setEndDateForFilterGrid(event.getValue());
				}
			}
		});
	}

	private void addClickListenerForButton(Button button, Window window) {
		button.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(Button.ClickEvent event) {
				String startDate = "";
				String endDate = "";
				window.close();
				if (button.getId().equals("setButton")) {
					if (!String.valueOf(sessioBeanForVaadin8Components.getStartDateForFilterGrid()).isEmpty()
							&& !String.valueOf(sessioBeanForVaadin8Components.getStartDateForFilterGrid())
									.equals("null")
							&& !String.valueOf(sessioBeanForVaadin8Components.getEndDateForFilterGrid()).isEmpty()
							&& !String.valueOf(sessioBeanForVaadin8Components.getEndDateForFilterGrid())
									.equals("null")) {
						startDate = (String.valueOf(sessioBeanForVaadin8Components.getStartDateForFilterGrid()));
						endDate = (String.valueOf(sessioBeanForVaadin8Components.getEndDateForFilterGrid()));

						buttonFromGrid.setCaption(startDate + "-" + endDate);

						tableConfig.getFilterValueMap().put(property,startDate + "-" + endDate);
						PagedGrid pagedGrid = new PagedGrid(tableConfig,componentConfig);
						pagedGrid.refreshGrid();
						return;
					}
					if (!String.valueOf(sessioBeanForVaadin8Components.getStartDateForFilterGrid()).isEmpty() && !String
							.valueOf(sessioBeanForVaadin8Components.getStartDateForFilterGrid()).equals("null")) {
						startDate = (String.valueOf(sessioBeanForVaadin8Components.getStartDateForFilterGrid()));
					}
					if (!String.valueOf(sessioBeanForVaadin8Components.getEndDateForFilterGrid()).isEmpty() && !String
							.valueOf(sessioBeanForVaadin8Components.getEndDateForFilterGrid()).equals("null")) {
						endDate = (String.valueOf(sessioBeanForVaadin8Components.getEndDateForFilterGrid()));
					}
					buttonFromGrid.setCaption(startDate + "-" + endDate);
				} else {
					buttonFromGrid.setCaption("Show all");
					sessioBeanForVaadin8Components.setStartDateForFilterGrid(null);
					sessioBeanForVaadin8Components.setEndDateForFilterGrid(null);
				}
			}
		});
	}

	private InlineDateTimeField getDateField(String caption, String id) {
		InlineDateTimeField inlineDateField = new InlineDateTimeField(caption);
		inlineDateField.setId(id);
		inlineDateField.setValue(LocalDateTime.now());
		inlineDateField.setLocale(Locale.US);
		addValueChangeListenerForDate(inlineDateField);
		return inlineDateField;
	}

	private Button getButton(String caption, String id, Window window) {
		Button button = new Button(caption);
		button.setId(id);
		addClickListenerForButton(button, window);
		return button;
	}
}
