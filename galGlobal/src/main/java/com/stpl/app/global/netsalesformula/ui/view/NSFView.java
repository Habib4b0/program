/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.netsalesformula.ui.view;

import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.global.netsalesformula.dto.NsfDto;
import com.stpl.app.global.netsalesformula.dto.SalesBasisDto;
import com.stpl.app.global.netsalesformula.logic.NsfLogic;
import com.stpl.app.global.netsalesformula.ui.form.NSFMainTab;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.VerticalLayout;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.jboss.logging.Logger;

/**
 *
 * @author karthikraja.k
 */
public class NSFView extends VerticalLayout implements View{
     /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(NSFView.class);
    /**
     * The View Name
     */
      public static final String NAME = "nsf-main-tab";
      /**
       * NSF Main Tab
       */
      NSFMainTab nsfForm;
       
      SessionDTO sessionDTO;
      
       /**
     * The rebate schedule master.
     */
    private NsfDto nsfDto = new NsfDto();
    
    SalesBasisDto salesBasisDto=new SalesBasisDto();
    
    static HelperDTO formCategory = null;
    /**
     * The binder.
     */
    private ErrorfulFieldGroup binder = new ErrorfulFieldGroup(
            new BeanItem<>(nsfDto));
    NsfLogic nsfLogic = new NsfLogic();
      /**
       * Constructor
       * @throws PortalException
       * @throws SystemException
       * @throws Exception 
       */

    public NSFView(SessionDTO sessionDTO) throws PortalException, SystemException, Exception {
        super();
        setStyleName("bootstrap-company");
        this.removeAllComponents();
        setSpacing(true);
         this.sessionDTO=sessionDTO;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        try {
            String mode = String.valueOf(sessionDTO.getMode());
            LOGGER.info("Enters enter method of  mode :" + mode);
            this.removeAllComponents();
            final Date tempDate = new Date();
            final SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
            final SimpleDateFormat fmtID = new SimpleDateFormat("MMddyyyyhhmmssms");
            sessionDTO.setSessionDate(fmt.format(tempDate));
            sessionDTO.setUiSessionId(fmtID.format(tempDate));

            binder = new ErrorfulFieldGroup(new BeanItem<>(
                    new NsfDto()));
            nsfDto = new NsfDto();

            if (null != mode) {
                switch (mode) {
                    case ConstantsUtils.ADD:
                        salesBasisDto=new SalesBasisDto();
                        nsfForm = new NSFMainTab(sessionDTO, binder, nsfDto,salesBasisDto);
                        break;
                    case ConstantsUtils.VIEW: {
                        final String idValue = String.valueOf(sessionDTO.getSystemId());
                        final int systemId = Integer.valueOf(idValue);
                 
                        nsfDto = nsfLogic.getNsfMasterById(systemId);
                        if ("Contract".equalsIgnoreCase(nsfDto.getFormulaType().getDescription())) {
                            nsfLogic.nsfInsert(sessionDTO, "tempDeductionInsertContract",nsfDto.isIsSelected(),"tempSbInsert");
                        } else {
                            nsfLogic.nsfInsert(sessionDTO, "tempDeductionInsert",nsfDto.isIsSelected(),"tempSbInsert");
                        }
                        binder.setItemDataSource(new BeanItem<>(nsfDto));
                        if (nsfDto.isIsSelected()) {
                            salesBasisDto = nsfLogic.getSalesBasisSelection(systemId);
                        }else{
                            salesBasisDto=new SalesBasisDto();
                        }
                        nsfForm = new NSFMainTab(sessionDTO, binder, nsfDto,salesBasisDto);
                        binder.setReadOnly(true);   
                        break;
                    }
                    case ConstantsUtils.EDIT: {
                        final String idValue = String.valueOf(sessionDTO.getSystemId());
                        final int systemId = Integer.valueOf(idValue);
                 
                        nsfDto = nsfLogic.getNsfMasterById(systemId);
                        if ("Contract".equalsIgnoreCase(nsfDto.getFormulaType().getDescription())) {
                            nsfLogic.nsfInsert(sessionDTO, "tempDeductionInsertContract",nsfDto.isIsSelected(),"tempSbInsert");
                        } else {
                            nsfLogic.nsfInsert(sessionDTO, "tempDeductionInsert",nsfDto.isIsSelected(),"tempSbInsert"); 
                        }
                        binder.setItemDataSource(new BeanItem<>(nsfDto));
                        if (nsfDto.isIsSelected()) {
                            salesBasisDto = nsfLogic.getSalesBasisSelection(systemId);
                        }else{
                            salesBasisDto=new SalesBasisDto();
                        }
                        nsfForm = new NSFMainTab(sessionDTO, binder, nsfDto,salesBasisDto);
                        break;
                    }
                    case ConstantsUtils.COPY: {
                        final String idValue = String.valueOf(sessionDTO.getSystemId());
                        final int systemId = Integer.valueOf(idValue);
                 
                        nsfDto = nsfLogic.getNsfMasterById(systemId);
                        if ("Contract".equalsIgnoreCase(nsfDto.getFormulaType().getDescription())) { 
                            nsfLogic.nsfInsert(sessionDTO, "copyTempDeductionContractInsert",nsfDto.isIsSelected(),"copyTempSbInsert");
                        } else {
                            nsfLogic.nsfInsert(sessionDTO, "copyTempDeductionInsert",nsfDto.isIsSelected(),"copyTempSbInsert"); 
                        }
                        NsfDto nsfNewDto = new NsfDto();
                        nsfNewDto.setFormulaCategory(formCategory);
                        binder.setItemDataSource(new BeanItem<>(nsfNewDto));
                        if (nsfDto.isIsSelected()) {
                            salesBasisDto = nsfLogic.getSalesBasisSelection(systemId);
                        }else{
                            salesBasisDto=new SalesBasisDto();
                        }
                        nsfForm = new NSFMainTab(sessionDTO, binder, nsfDto,salesBasisDto);
                        break;
                    }
                    default:
                        break;
                }
            }
            addComponent(nsfForm);

            LOGGER.info(" Ends enter method ");

        } catch (Exception exception) {
            LOGGER.error(exception);

        }

    }

    
    public static void setFormCategory(HelperDTO forCategory) {
        formCategory = forCategory;
    }

    public static HelperDTO getFormCategory() {
        return formCategory;
    }
 
}
