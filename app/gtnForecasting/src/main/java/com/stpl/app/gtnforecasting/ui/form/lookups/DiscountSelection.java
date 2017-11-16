/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.ui.form.lookups;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.jboss.logging.Logger;

import com.stpl.app.gtnforecasting.dto.DiscountSelectionDTO;
import com.stpl.app.gtnforecasting.logic.DiscountProjectionLogic;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.utils.UiUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * The Class DiscountSelection.
 *
 * @author sriram
 */
public class DiscountSelection extends Window {

	/**
	 * The table.
	 */
	public ExtFilterTable table = new ExtFilterTable();

	/**
	 * The btn close.
	 */
	public Button btnClose = new Button("CLOSE");

	/**
	 * The btn select.
	 */
	public Button btnSelect = new Button("SELECT");

	/**
	 * The Boolean variable to check whether discount program must be shown or
	 * not
	 */
	boolean isProgram;

	/**
	 * The Session Dto
	 */
	SessionDTO session;
	/**
	 * Logic class for Discount
	 */
	DiscountProjectionLogic logic = new DiscountProjectionLogic();

	/**
	 * Count for the no of checked records
	 */
	int checkCount = 0;

	/**
	 * The branditem bean.
	 */
	private BeanItemContainer<DiscountSelectionDTO> discountSelectionBean = new BeanItemContainer<>(
			DiscountSelectionDTO.class);

	/**
	 * The Constant LOGGER.
	 */
	private static final Logger LOGGER = Logger.getLogger(DiscountSelection.class);

	/**
	 * Discount Names list
	 */
	List<String> selectedDiscounts = new ArrayList<>();

	/**
	 * Discount Names list
	 */
	List<String> selectedDiscountsNoList = new ArrayList<>();

	/**
	 * The Constructor.
	 */
	public DiscountSelection(SessionDTO session, List<String> selectedDiscounts, boolean discountProgram) {
		super("Discount Selection look Up");
		this.isProgram = discountProgram;
		this.selectedDiscounts = selectedDiscounts;
		this.session = session;
		init();
	}

	/**
	 * THe Init method
	 */
	private void init() {
		addStyleName(Constant.BOOTSTRAP_NM);
		addStyleName(Constant.BOOTSTRAP);
		center();
		setModal(true);
		setClosable(true);
		setWidth("750px");
		setHeight("550px");
		setResizable(false);
		setContent(addToContent());
		configureFields();
	}

	/**
	 * Adds the to content.
	 *
	 * @return the panel
	 */
	private VerticalLayout addToContent() {
		final VerticalLayout content = (VerticalLayout) UiUtils.getLayout(new VerticalLayout());
		content.addComponent(addTable());
		content.addComponent(addButtons());
		return content;
	}

	/**
	 * Adds the buttons.
	 *
	 * @return the horizontal layout
	 */
	private HorizontalLayout addButtons() {
		final HorizontalLayout layout = (HorizontalLayout) UiUtils.getLayout(new HorizontalLayout());
		layout.setMargin(false);
		layout.addComponent(btnSelect);
		layout.addComponent(btnClose);
		return layout;
	}

	/**
	 * Adds the table.
	 *
	 * @return the vertical layout
	 */
	private VerticalLayout addTable() {
		final VerticalLayout layout = (VerticalLayout) UiUtils.getLayout(new VerticalLayout());
		layout.setMargin(false);
		layout.addComponent(table);
		layout.addComponent(new Label(StringUtils.EMPTY));
		layout.setSizeFull();
		return layout;
	}

	/**
	 * Configure fields.
	 */
	private void configureFields() {
		Object[] visibleCols;
		String[] colHeader;

		if (isProgram) {
			visibleCols = new Object[] { Constant.CHECKRECORD, "discountNo", "discountName" };
			colHeader = new String[] { StringUtils.EMPTY, "Discount #", "Discount Name" };
		} else {
			visibleCols = new Object[] { Constant.CHECKRECORD, "discountName" };
			colHeader = new String[] { StringUtils.EMPTY, "Discount Type" };
		}
		table.setColumnWidth(visibleCols[0], NumericConstants.FIFTY);
		table.setCaption("Discount Selection");
		table.setWidth("100%");
		table.setHeight("100%");
		table.setContainerDataSource(discountSelectionBean);
		loadDiscounts();
		table.setVisibleColumns(visibleCols);
		table.setColumnHeaders(colHeader);
		table.setImmediate(true);
		table.setEditable(true);
		table.setPageLength(NumericConstants.SEVEN);
		table.setFilterBarVisible(true);
		table.setFilterDecorator(new ExtDemoFilterDecorator());
		table.setTableFieldFactory(new DefaultFieldFactory() {
			@Override
			public Field<?> createField(Container container, Object itemId, Object propertyId, Component uiContext) {

				if (propertyId.equals(Constant.CHECKRECORD)) {
					final CheckBox check = new CheckBox();
					check.setEnabled(true);
					check.setImmediate(true);
					check.addValueChangeListener(new ValueChangeListener() {

						@Override
						public void valueChange(Property.ValueChangeEvent event) {
							if ((Boolean) event.getProperty().getValue()) {
								checkCount++;
								if (checkCount > NumericConstants.FIVE) {
									check.setValue(false);
									LOGGER.info("You can select only 5 discounts maximum");
									AbstractNotificationUtils.getErrorNotification("Cannot select the discount",
											"You can select a maximum of 5 discounts");
								}
							} else {
								checkCount--;
							}
						}
					});
					return check;
				}
				return null;
			}
		});

		btnSelect.addClickListener(new Button.ClickListener() {
			/**
			 * Called when close button is clicked
			 */
			public void buttonClick(final Button.ClickEvent event) {
				List<List<String>> discountRSlist = new ArrayList<>();
				List<String> rsIdList = new ArrayList<>();
				List<String> rsNameList = new ArrayList<>();
				selectedDiscounts.clear();
				selectedDiscountsNoList.clear();
				for (DiscountSelectionDTO discountDto : discountSelectionBean.getItemIds()) {
					if (discountDto.isCheckRecord()) {

						selectedDiscountsNoList.add(discountDto.getDiscountNo());
						if (isProgram) {
							selectedDiscounts.add(discountDto.getDiscountName() + "~" + discountDto.getRsId());
							rsIdList.add(discountDto.getRsId());
							rsNameList.add(discountDto.getRsName());
						} else if (discountDto.getDiscountRSlist() != null
								&& !discountDto.getDiscountRSlist().isEmpty()) {
							selectedDiscounts.add(discountDto.getDiscountName());
							rsIdList.addAll(discountDto.getDiscountRSlist().get(0));
							rsNameList.addAll(discountDto.getDiscountRSlist().get(1));
						}
					}
				}

				if (!selectedDiscounts.isEmpty()) {
					discountRSlist.add(rsIdList);
					discountRSlist.add(rsNameList);
					discountRSlist.add(selectedDiscounts);
					session.setDiscountRSlist(discountRSlist);
					session.setDiscountRSlistUpdated(true);
					close();
				} else {
					LOGGER.info("No Discounts have been selected");
					AbstractNotificationUtils.getErrorNotification("No Values Selected",
							"No Discounts have been selected");
				}

			}
		});

		btnClose.addClickListener(new Button.ClickListener() {
			/**
			 * Called when close button is clicked
			 */
			public void buttonClick(final Button.ClickEvent event) {
				new AbstractNotificationUtils() {

					@Override
					public void noMethod() {
						return;
					}

					@Override
					public void yesMethod() {
						close();
					}
				}.getConfirmationMessage("Confirm Close",
						"Closing the popup will not submit the selected discounts back to the discount projection screen. Are you sure you want to continue?");

			}
		});
	}

	/**
	 * Load discounts.
	 */
	private void loadDiscounts() {
		List<DiscountSelectionDTO> discountList;
		String discountType = "DiscountType";
		if (isProgram) {
			discountType = "DiscountProgram";
		}

		discountList = logic.loadDiscountPrograms(session, selectedDiscounts, discountType);
		if (discountList != null && !discountList.isEmpty()) {
			for (DiscountSelectionDTO dto : discountList) {
				discountSelectionBean.addBean(dto);
			}
		}
	}

	public List<String> getSelectedDiscountsList() {
		return selectedDiscounts;
	}

	public List<String> getSelectedDiscountsNoList() {
		return selectedDiscountsNoList;
	}

}
