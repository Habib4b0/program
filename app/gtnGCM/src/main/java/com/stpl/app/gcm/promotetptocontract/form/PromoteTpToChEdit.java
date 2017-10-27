/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.promotetptocontract.form;

import com.stpl.app.gcm.promotetptocontract.dto.PromoteTpToChDto;
import com.stpl.app.gcm.sessionutils.SessionDTO;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.VerticalLayout;
import org.asi.ui.extfilteringtable.ExtFilterTable;

/**
 *
 * @author alok.v
 */
public class PromoteTpToChEdit extends VerticalLayout {

    SessionDTO session;
    /**
     * View name for navigation.
     */
    public static final String NAME = "PROMOTETP";
    /**
     * DTO object for DataSelection.
     */
    private PromoteTpToChDto promoteTpToChDto = new PromoteTpToChDto();
    /**
     * Binder for DataSelection.
     */
    private CustomFieldGroup promoteTpToChBinder = new CustomFieldGroup(new BeanItem<>(promoteTpToChDto));
    PromoteTPToChForm promoteTPToChForm;
    PromoteTpToChWindow editWindow;
    ExtFilterTable resultTable;

    /**
     *
     * @param session
     * @param promoteTpToChDto
     * @param editWindow
     * @param resultTable
     * @throws Exception
     */
    public PromoteTpToChEdit(final SessionDTO session, final PromoteTpToChDto promoteTpToChDto, final PromoteTpToChWindow editWindow, final ExtFilterTable resultTable) {
        this.session = session;
        this.promoteTpToChDto = promoteTpToChDto;
        this.editWindow = editWindow;
        this.resultTable = resultTable;
        promoteTPToChForm = new PromoteTPToChForm(promoteTpToChBinder, this.session, this.editWindow, this.resultTable);
        addComponent(promoteTPToChForm);
        enter();
    }

    /**
     * Enter Method
     *
     */
    public void enter() {
        promoteTpToChBinder.setItemDataSource(new BeanItem<>(promoteTpToChDto));
 
    }

}
