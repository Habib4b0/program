package com.stpl.app.gtnforecasting.abstractforecast;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import static com.stpl.app.utils.Constants.ButtonConstants.BTN_APPROVE;
import static com.stpl.app.utils.Constants.ButtonConstants.BTN_CANCEL;
import static com.stpl.app.utils.Constants.ButtonConstants.BTN_CLOSE;
import static com.stpl.app.utils.Constants.ButtonConstants.BTN_REJECT;
import static com.stpl.app.utils.Constants.ButtonConstants.BTN_SAVE;
import static com.stpl.app.utils.Constants.ButtonConstants.BTN_SUBMIT;
import static com.stpl.app.utils.Constants.ButtonConstants.BTN_WITHDRAW;

import com.stpl.app.utils.UiUtils;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author soundarrajan
 */
public abstract class AbstractForm extends VerticalLayout {

	protected static Button btnSave;
	protected static Button btnSubmit;
	protected static Button btnNext;
	protected static Button btnPrev;
	protected static Button btnClose;
	private Button btnRefresh;
	protected Button btnApprove;
	protected Button btnReject;
	protected Button btnWithdraw;
	protected Button btnCancel;

	/**
	 * Call this method to add Save, Previous, Next, Close and Submit buttons to
	 * the bottom of the page
	 * 
	 * @return HorizontalLayout containing the buttons
	 */
	public HorizontalLayout addFooterButtons(Button btnNext, Button btnPrev, Button btnRefresh) {
		AbstractForm.btnNext = btnNext;
		AbstractForm.btnPrev = btnPrev;
		setBtnSave(new Button(BTN_SAVE.getConstant()));
		setBtnSubmit(new Button(BTN_SUBMIT.getConstant()));
		setBtnClose(new Button(BTN_CLOSE.getConstant()));
		setBtnApprove(new Button(BTN_APPROVE.getConstant()));
		setBtnCancel(new Button(BTN_CANCEL.getConstant()));
		setBtnReject(new Button(BTN_REJECT.getConstant()));
		setBtnWithdraw(new Button(BTN_WITHDRAW.getConstant()));
		HorizontalLayout footerLayout = (HorizontalLayout) UiUtils.getLayout(new HorizontalLayout());
		btnSave.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(Button.ClickEvent event) {
				btnSaveLogic();
			}
		});
		btnClose.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(Button.ClickEvent event) {
				btnCloseLogic();
			}
		});
		btnSubmit.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(Button.ClickEvent event) {
				btnSubmitLogic();
			}
		});
		btnApprove.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(Button.ClickEvent event) {
				btnApproveLogic();
			}

		});
		btnReject.addClickListener(new Button.ClickListener() {

                        @Override
			public void buttonClick(Button.ClickEvent event) {
				btnRejectLogic();
			}
		});
		btnWithdraw.addClickListener(new Button.ClickListener() {

                        @Override
			public void buttonClick(Button.ClickEvent event) {
				btnWithdrawLogic();
			}
		});
		btnCancel.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(Button.ClickEvent event) {
				btnCancelLogic();
			}
		});
		btnRefresh.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(Button.ClickEvent event) {
				btnRefreshLogic();
			}
		});
		footerLayout.addComponent(btnSave);
		footerLayout.addComponent(btnPrev);
		footerLayout.addComponent(btnNext);
		footerLayout.addComponent(btnClose);
		footerLayout.addComponent(btnSubmit);
		footerLayout.addComponent(btnRefresh);
		footerLayout.addComponent(btnWithdraw);
		footerLayout.addComponent(btnApprove);
		footerLayout.addComponent(btnReject);
		footerLayout.addComponent(btnCancel);
		btnPrev.setVisible(true);
		btnNext.setVisible(true);
		btnRefresh.setVisible(false);
		btnApprove.setVisible(false);
		btnReject.setVisible(false);
		btnCancel.setVisible(false);
		btnWithdraw.setVisible(false);
		footerLayout.setMargin(true);
		return footerLayout;
	}

	protected abstract void btnSaveLogic();

	protected abstract void btnCloseLogic();

	protected abstract void btnSubmitLogic();

	protected abstract void initializeTabs();

	protected abstract void btnApproveLogic();

	protected abstract void btnRejectLogic();

	protected abstract void btnWithdrawLogic();

	protected abstract void btnCancelLogic();

	protected abstract void buildScreen();

	protected abstract void onTabChange(int tabPosition);

	protected abstract void lazyLoadTab(int tabPosition);

	public static Button getBtnSave() {
		return btnSave;
	}

	public static void setBtnSave(Button btnSave) {
		AbstractForm.btnSave = btnSave;
	}

	public static Button getBtnSubmit() {
		return btnSubmit;
	}

	public static void setBtnSubmit(Button btnSubmit) {
		AbstractForm.btnSubmit = btnSubmit;
	}

	public static Button getBtnNext() {
		return btnNext;
	}

	public static void setBtnNext(Button btnNext) {
		AbstractForm.btnNext = btnNext;
	}

	public static Button getBtnPrev() {
		return btnPrev;
	}

	public static void setBtnPrev(Button btnPrev) {
		AbstractForm.btnPrev = btnPrev;
	}

	public static Button getBtnClose() {
		return btnClose;
	}

	public static void setBtnClose(Button btnClose) {
		AbstractForm.btnClose = btnClose;
	}

	public Button getBtnRefresh() {
		return btnRefresh;
	}

	public void setBtnRefresh(Button btnRefresh) {
		this.btnRefresh = btnRefresh;
	}

	public Button getBtnApprove() {
		return btnApprove;
	}

	public void setBtnApprove(Button btnApprove) {
		this.btnApprove = btnApprove;
	}

	public Button getBtnReject() {
		return btnReject;
	}

	public void setBtnReject(Button btnReject) {
		this.btnReject = btnReject;
	}

	public Button getBtnWithdraw() {
		return btnWithdraw;
	}

	public void setBtnWithdraw(Button btnWithdraw) {
		this.btnWithdraw = btnWithdraw;
	}

	public Button getBtnCancel() {
		return btnCancel;
	}

	public void setBtnCancel(Button btnCancel) {
		this.btnCancel = btnCancel;
	}

	protected abstract void btnRefreshLogic();

}
