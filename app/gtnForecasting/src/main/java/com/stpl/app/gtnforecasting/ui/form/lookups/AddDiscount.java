//package com.stpl.app.gtnforecasting.ui.form.lookups;
//
//import com.stpl.app.gtnforecasting.utils.Constant;
//import com.stpl.app.utils.UiUtils;
//import com.stpl.ifs.ui.util.NumericConstants;
//import com.vaadin.ui.Alignment;
//import com.vaadin.ui.Window;
//import com.vaadin.v7.shared.ui.label.ContentMode;
//import com.vaadin.v7.ui.HorizontalLayout;
//import com.vaadin.v7.ui.Label;
//
///**
// *
// * @author sriram
// */
//public class AddDiscount extends Window {
//	
//	public AddDiscount(String title) {
//		super(title);
//		addStyleName(Constant.BOOTSTRAP_NM);
//		addStyleName(Constant.BOOTSTRAP);
//		center();
//		setModal(true);
//		setClosable(true);
//		setWidth("500px");
//		setHeight("200px");
//		setResizable(false);
//		setContent(addToContent());
//	}
//
//	/**
//	 * Adds the to content.
//	 *
//	 * @return the panel
//	 */
//	private HorizontalLayout addToContent() {
//		final HorizontalLayout content = (HorizontalLayout) UiUtils.getLayout(new HorizontalLayout());
//		Label releaseLabel = new Label(
//				"<span style=\"text-align: center; font-size: 16px; font-weight: 600; color: #6495ED;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;This would be released by M8</span>");
//		releaseLabel.setContentMode(ContentMode.HTML);
//		content.addComponent(releaseLabel);
//		content.setComponentAlignment(releaseLabel, Alignment.MIDDLE_CENTER);
//		content.setHeight(NumericConstants.HUNDRED, Unit.PERCENTAGE);
//		return content;
//	}
//}