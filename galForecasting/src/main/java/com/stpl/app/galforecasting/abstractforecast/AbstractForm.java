package com.stpl.app.galforecasting.abstractforecast;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import static com.stpl.app.utils.Constants.ButtonConstants.*;
import com.stpl.app.utils.UiUtils;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import java.util.logging.Logger;

/**
 *
 * @author soundarrajan
 */
public abstract class AbstractForm extends VerticalLayout {

    protected Button btnSave;
    protected Button btnSubmit;
    protected Button btnNext;
    protected Button btnPrev;
    protected Button btnClose;
    protected Button btnRefresh;
    protected Button btnApprove;
    protected Button btnReject;
    protected Button btnWithdraw;
    protected Button btnCancel;
    protected Logger log = Logger.getAnonymousLogger();

    /**
     * Call this method to add Save, Previous, Next, Close and Submit buttons to
     * the bottom of the page
     *
     * @return HorizontalLayout containing the buttons
     */
    public HorizontalLayout addFooterButtons(Button btnNext, Button btnPrev, Button btnRefresh) {
        this.btnNext = btnNext;
        this.btnPrev = btnPrev;
        setBtnSave(new Button(BTN_SAVE.getConstant()));
        setBtnSubmit(new Button(BTN_SUBMIT.getConstant()));
        setBtnClose(new Button(BTN_CLOSE.getConstant()));
        setBtnApprove(new Button(BTN_APPROVE.getConstant()));
        setBtnCancel(new Button(BTN_CANCEL.getConstant()));
        setBtnReject(new Button(BTN_REJECT.getConstant()));
        setBtnWithdraw(new Button(BTN_WITHDRAW.getConstant()));
        HorizontalLayout footerLayout = (HorizontalLayout) UiUtils.getLayout(HorizontalLayout.class);
        btnSave.addClickListener(new Button.ClickListener() {

            public void buttonClick(Button.ClickEvent event) {
                btnSaveLogic();
            }
        });
        btnClose.addClickListener(new Button.ClickListener() {

            public void buttonClick(Button.ClickEvent event) {
                btnCloseLogic();
            }
        });
        btnSubmit.addClickListener(new Button.ClickListener() {

            public void buttonClick(Button.ClickEvent event) {
                btnSubmitLogic();
            }
        });
        btnApprove.addClickListener(new Button.ClickListener() {

            public void buttonClick(Button.ClickEvent event) {
                btnApproveLogic();
            }

        });
        btnReject.addClickListener(new Button.ClickListener() {

            public void buttonClick(Button.ClickEvent event) {
                btnRejectLogic();
            }
        });
        btnWithdraw.addClickListener(new Button.ClickListener() {

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

    public Button getBtnSave() {
        return btnSave;
    }

    public void setBtnSave(Button btnSave) {
        this.btnSave = btnSave;
    }

    public Button getBtnSubmit() {
        return btnSubmit;
    }

    public void setBtnSubmit(Button btnSubmit) {
        this.btnSubmit = btnSubmit;
    }

    public Button getBtnNext() {
        return btnNext;
    }

    public void setBtnNext(Button btnNext) {
        this.btnNext = btnNext;
    }

    public Button getBtnPrev() {
        return btnPrev;
    }

    public void setBtnPrev(Button btnPrev) {
        this.btnPrev = btnPrev;
    }

    public Button getBtnClose() {
        return btnClose;
    }

    public void setBtnClose(Button btnClose) {
        this.btnClose = btnClose;
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
