package com.stpl.app.adminconsole.discount.ui.view;

import com.stpl.app.adminconsole.discount.dto.DiscountSearchDTO;
import com.stpl.app.adminconsole.discount.logic.DiscountLogic;
import org.jboss.logging.Logger;

import com.stpl.app.adminconsole.discount.ui.form.DiscountViewForm;
import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.VerticalLayout;
import java.util.ArrayList;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class DiscountView.
 */
public class DiscountView extends VerticalLayout implements View{
	/**
     * The Constant NAME.
     */
    public static final String NAME = "View";
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(DiscountView.class);
    
      /**
     * The selected results bean.
     */
    private BeanItemContainer<DiscountSearchDTO> resultsBean = new BeanItemContainer<DiscountSearchDTO>(DiscountSearchDTO.class);
    
     /**
     * The DiscountLogic.
     */
    DiscountLogic logic=new DiscountLogic();
    
        /**
     * The itemGroupDTO.
     */
    private DiscountSearchDTO deductionGroupDTO = new DiscountSearchDTO(); 
    
        /**
     * The Discount Add form.
     */
    private DiscountViewForm discountViewForm=new DiscountViewForm();
    
    /**
     * Instantiates a new discount view.
     */
    public DiscountView() {
    	 super();
    	 LOGGER.info("enter DiscountView constructor");
         setSpacing(true);
         this.discountViewForm=new DiscountViewForm(resultsBean);
         addComponent(discountViewForm);
         setStyleName("bootstrap");
         LOGGER.info("DiscountView constructor Ended");
	}

	/** (non-Javadoc)
	 * @see com.vaadin.navigator.View#enter(com.vaadin.navigator.ViewChangeListener.ViewChangeEvent)
	 */
    @Override
    public void enter(final ViewChangeEvent event) {
        // TODO Auto-generated method stub
        try {
            final DiscountLogic logic = new DiscountLogic();
            final int deductionGroupSId = (Integer) VaadinSession.getCurrent().getAttribute(ConstantsUtils.SYS_ID);
            resultsBean.removeAllItems();
            String pageName = String.valueOf(VaadinSession.getCurrent().getAttribute("logic"));
            if (pageName.equals("view")) {
                List<DiscountSearchDTO> selectedRebate = new ArrayList<DiscountSearchDTO>();
                selectedRebate = logic.getDeductionGroupDetails(deductionGroupSId);
                if (!selectedRebate.isEmpty()) {
                    resultsBean.addAll(selectedRebate);
                }
                deductionGroupDTO = logic.getDeductionGroupInfo(deductionGroupSId);
                discountViewForm.groupInfo(deductionGroupDTO);
            }
        } catch (SystemException ex) {
            LOGGER.error(ex);
        } catch (PortalException ex) {
            LOGGER.error(ex);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }
}