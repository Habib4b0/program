/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.ui.form.checkProcedureCompletion;

import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.utils.CommonUtil;
import com.stpl.app.serviceUtils.Constants;
import java.util.concurrent.Future;

/**
 *
 * @author mekalai.madhappa
 */
public class CheckProductCompletion implements CompletionCheckOnTabChange {

    private final SessionDTO session;

    public CheckProductCompletion(SessionDTO session) {
        this.session = session;
    }

    @Override
    public void checkProcedureCompletion() {
        session.addFutureMap("Check_Product", new Future[]{service.submit(createRunnable())});

    }

    private Runnable createRunnable() {
        Runnable run = new Runnable() {

            @Override
            public void run() {
                Thread.currentThread().setName("Check_Product");
                CommonUtil.getInstance().isProcedureCompleted("Discount", Constants.PRODUCT, session);
            }
        };
        return run;
    }
}
