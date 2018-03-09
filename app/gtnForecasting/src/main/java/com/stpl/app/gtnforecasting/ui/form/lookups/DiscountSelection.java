/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.ui.form.lookups;

import com.stpl.app.gtnforecasting.dto.DiscountSelectionDTO;
import com.stpl.app.gtnforecasting.logic.DiscountProjectionLogic;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.utils.UiUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.constants.BooleanConstant;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Window;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.Property.ValueChangeListener;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.ui.CheckBox;
import com.vaadin.v7.ui.DefaultFieldFactory;
import com.vaadin.v7.ui.Field;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.v7.ui.Label;
import com.vaadin.v7.ui.VerticalLayout;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class DiscountSelection.
 *
 * @author sriram
 */
public class DiscountSelection extends Window {

	/**
	 * The table.
	 */
	private final ExtFilterTable table = new ExtFilterTable();
        
        

	/**
	 * The btn close.
	 */
	private final Button btnClose = new Button("CLOSE");

	/**
	 * The btn select.
	 */
	private final Button btnSelect = new Button("SELECT");

	/**
	 * The Boolean variable to check whether discount program must be shown or
	 * not
	 */
	private final boolean isProgram;

	/**
	 * The Session Dto
	 */
	private final SessionDTO session;
	/**
	 * Logic class for Discount
	 */
	private final DiscountProjectionLogic logic = new DiscountProjectionLogic();

	/**
	 * Count for the no of checked records
	 */
	private int checkCount = 0;

	private final BeanItemContainer<DiscountSelectionDTO> discountSelectionBean = new BeanItemContainer<>(
			DiscountSelectionDTO.class);

	/**
	 * The Constant LOGGER.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(DiscountSelection.class);

	/**
	 * Discount Names list
	 */
	private List<String> selectedDiscounts = new ArrayList<>();

	/**
	 * Discount Names list
	 */
	private final List<String> selectedDiscountsNoList = new ArrayList<>();

	/**
	 * The Constructor.
     * @param session
     * @param selectedDiscounts
     * @param discountProgram
	 */
	public DiscountSelection(SessionDTO session, List<String> selectedDiscounts, boolean discountProgram) {
		super("Discount Selection look Up");
		this.isProgram = discountProgram;
		this.selectedDiscounts = selectedDiscounts == null ? selectedDiscounts : new ArrayList<>(selectedDiscounts);
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
									check.setValue(BooleanConstant.getFalseFlag());
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
                        @Override
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
                        @Override
			public void buttonClick(final Button.ClickEvent event) {
				new AbstractNotificationUtils() {

					@Override
					public void noMethod() {
                                            LOGGER.debug("Inside overriden method: Do nothing");
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
		return selectedDiscounts == null ? selectedDiscountsNoList : new ArrayList<>(selectedDiscountsNoList);
	}

	public List<String> getSelectedDiscountsNoList() {
		return selectedDiscountsNoList == null ? selectedDiscountsNoList : new ArrayList<>(selectedDiscountsNoList);
	}

}
