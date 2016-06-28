/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.itemgroup.ui.view;

import com.stpl.app.adminconsole.abstractsearch.ui.AbstractSearchForm;
import com.stpl.app.adminconsole.common.dto.SessionDTO;
import com.stpl.app.adminconsole.itemgroup.dto.ItemGroupDTO;
import org.jboss.logging.Logger;
import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.VerticalLayout;


/**
 * The Class ItemGroupSearchMain.
 *
 * @author vishalakshi
 */
public class ItemGroupSearchMain extends VerticalLayout implements View {

    
    private static final Logger LOGGER = Logger.getLogger(ItemGroupSearchMain.class);
    
    public static final String NAME = ConstantsUtils.EMPTY;
   
    private ItemGroupDTO itemGroupDTO = new ItemGroupDTO();
    
    private CustomFieldGroup itemGroupBinder = new CustomFieldGroup(new BeanItem<ItemGroupDTO>(itemGroupDTO));

   
    public CustomFieldGroup getItemGroupBinder() {
        return itemGroupBinder;
    }

   
    public void setItemGroupBinder(final CustomFieldGroup itemGroupBinder) {
        this.itemGroupBinder = itemGroupBinder;
    }

   
    public ItemGroupDTO getItemGroupDTO() {
        return itemGroupDTO;
    }

    AbstractSearchForm abstractSearchForm;

    SessionDTO sessionDTO;

    /**
     * Instantiates a new item group search main.
     *
     * @param sessionDTO
     * @throws Exception
     * @throws PortalException
     * @throws SystemException
     */
    public ItemGroupSearchMain(final SessionDTO sessionDTO) throws SystemException, PortalException, Exception {

        super();
        LOGGER.info("ItemGroupSearchMain Constructor started ");
        setSpacing(true);
        this.sessionDTO = sessionDTO;
        abstractSearchForm = new AbstractSearchForm(ConstantsUtils.ITEM_GROUP_MASTER, sessionDTO);
        addComponent(abstractSearchForm);
        setStyleName("bootstrap");
        LOGGER.info("ItemGroupSearchMain Constructor Ended ");
    }

   
    public void enter(final ViewChangeEvent event) {
        try {
            markAsDirty();

            setStyleName("bootstrap");
            setSpacing(true);

        } catch (Exception e) {
            LOGGER.error(e);
        }
    }
}
