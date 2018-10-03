/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.ui.form.checkprocedurecompletion;

import com.stpl.app.gtnforecasting.logic.DataSelectionLogic;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.utils.CommonUtil;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.util.service.thread.ThreadPool;
import java.util.concurrent.Future;

/**
 *
 * @author mekalai.madhappa
 */
public class WaitForProcedureCallProduct implements WaitForCustomerProcedureCompletion {

    private final SessionDTO session;
    private ThreadPool waitCustomThreadPool = ThreadPool.getInstance();

    public WaitForProcedureCallProduct(SessionDTO session) {
        this.session = session;
    }

    @Override
    public void waitforStatusTableCOmpletion() {
        session.addFutureMap(Constant.CUSTOMER_VIEW_DISCOUNT_POPULATION_CALL,
                new Future[]{waitCustomThreadPool.submitRunnable(createRunnable(Constant.PRC_VIEWS_CALL,
                                    Constant.PRODUCT_VIEW_DISCOUNT_POPULATION_CALL, session.getFunctionMode(), Constant.DISCOUNT3, "P", "null", "null", session))});
    }

    private Runnable createRunnable(final Object... inputs) {
        Runnable run = new Runnable() {

            @Override
            public void run() {
                CommonUtil.getInstance().waitsForOtherThreadsToComplete(session.getFutureValue("Check_Product"));
                Thread.currentThread().setName(inputs[1].toString());
                new DataSelectionLogic().callViewInsertProcedureForNm((SessionDTO) inputs[7], inputs[3].toString(), inputs[4].toString(), inputs[5].toString());
            }
        };
        return run;

    }

}
