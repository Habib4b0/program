/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.contract.dashboard.ui.lookup;


import com.stpl.app.contract.abstractsearch.util.ConstantUtil;
import com.stpl.app.contract.common.dto.SessionDTO;
import java.util.List;
import java.util.Set;

import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;

import com.stpl.app.contract.dashboard.dto.ImtdContRsDetailsFrDTO;
import com.stpl.app.contract.dashboard.dto.RSFormulaDTO;
import com.stpl.app.contract.global.logic.RebateScheduleLogic;
import com.stpl.app.contract.util.AbstractNotificationUtils;
import com.stpl.app.contract.util.Constants;
import com.stpl.app.contract.util.ErrorCodeUtil;
import com.stpl.app.contract.util.ErrorCodes;
import com.stpl.app.contract.util.ResponsiveUtils;
import com.stpl.app.model.FormulaDetailsMaster;
import com.stpl.app.service.FormulaDetailsMasterLocalServiceUtil;
import com.stpl.ifs.ui.CustomePagedFilterTable;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.server.ErrorHandler;
import com.vaadin.shared.ui.MultiSelectMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import org.asi.ui.extfilteringtable.paged.ExtPagedFilterTable;

/**
 *
 * @author Shrihariharan
 */
public class RebateFormulaPopup extends Window {
    
    /** The Constant LOGGER. */
    private static final Logger LOGGER = Logger.getLogger(RebateFormulaPopup.class);

    @UiField("btnMove")
    private Button btnMove;

    @UiField("btnRemove")
    private Button btnRemove;

    @UiField("btnMoveAll")
    private Button btnMoveAll;

    @UiField("btnRemoveAll")
    private Button btnRemoveAll;

    @UiField("submitButton")
    private Button saveBtn;

    @UiField("availableTableLayout")
    private VerticalLayout availableTableLayout;

    @UiField("selectedTableLayout")
    private VerticalLayout selectedTableLayout;

    @UiField("availableTable")
    private ExtPagedFilterTable availableTable;

    @UiField("selectedTable")
    private CustomePagedFilterTable selectedTable;    
    
    private final RebateScheduleLogic logic;
    
    
    private int imtdContRsdSid;
    
    
    
    private int itemSid;
    
    SessionDTO sessionDTO;
    
    BeanItemContainer<RSFormulaDTO> availableBeanItemContainer;
    BeanItemContainer<ImtdContRsDetailsFrDTO> selectedBeanItemContainer;
    public RebateFormulaPopup(int imtdContRsdSid,int itemSid, final SessionDTO sessionDTO){
        super();
        this.imtdContRsdSid=imtdContRsdSid;
        this.itemSid=itemSid;
        this.sessionDTO=sessionDTO;
        logic = new RebateScheduleLogic(this.sessionDTO);
        init();
    }
    
    public void init() {
        try {
            addStyleName("bootstrap");
            addStyleName("bootstrap-bb");
            setCaption("Formula LookUp");
            center();
            setClosable(true);
            setModal(true);            
            setWidth("950px");
            setHeight("660px");
            setContent(Clara.create(getClass().getResourceAsStream("/declarative-ui/contract-dashboard/rebate-formula-lookup.xml"), this));
            addToContent();
        } catch (Exception e) {
            LOGGER.error(e);

        }
    }
    
    private void addToContent(){
            getTable();
            addSaveButton();        
    }
    
    
    /**
     * Method to add item family plan table component.
     *
     * @return the ifp table
     */
    private void getTable() {
        LOGGER.debug("Entering getTables");
        addAvailableTable();
        addSelectedTable();
        addButtons();
        LOGGER.debug("Ending getTables");
    }
    
    public void addAvailableTable() {
        try{
      LOGGER.debug("Entering addAvailableTable");        
      availableTable.markAsDirty();
      availableBeanItemContainer=new BeanItemContainer<>(RSFormulaDTO.class);
      availableBeanItemContainer.addAll(logic.getLoadForecastingFormula());
      availableTable.setContainerDataSource(availableBeanItemContainer);
      availableTable.setVisibleColumns("formulaNo","formulaName");
      availableTable.setColumnHeaders("Formula No","Formula Name");
      availableTable.setPageLength(NumericConstants.TEN);
      availableTable.setNullSelectionAllowed(true);
      availableTable.sinkItemPerPageWithPageLength(false);
      availableTable.setImmediate(true);
      availableTable.setSelectable(true);
      availableTable.setMultiSelect(true);
      availableTable.setMultiSelectMode(MultiSelectMode.DEFAULT);
      availableTable.setWidth("390px");
      HorizontalLayout layout = ResponsiveUtils.getResponsiveControls(availableTable.createControls(),true);
      availableTableLayout.addComponent(layout);
      availableTable.addItemClickListener(new ItemClickListener() {

          /**
           * Called when a item has been clicked.
           */
          @Override
          public void itemClick(ItemClickEvent event) {
              return;
          }
      });
      availableTable.setErrorHandler(new ErrorHandler() {
          /**
           * Invoked when an error occurs.
           */
          public void error(final com.vaadin.server.ErrorEvent event) {
             LOGGER.error("Error in avalable table");
          }
      });
      LOGGER.debug("Ending addAvailableTable");
        }catch(Exception ex){
            LOGGER.error(ex);
        }
    }
    
    /**
     * Add buttons to layout.
     *
     * @return the vertical layout
     */
    public void addButtons() {
        LOGGER.debug("Entering addButtons");
        add();
        remove();
        addAll();
        removeAll();
        LOGGER.debug("Ending addButtons");
    }  
    
     public Button add() {

       
        btnMove.setDescription("Move selected items to right");
        btnMove.setImmediate(true);
        btnMove.setWidth("70");
        btnMove.setErrorHandler(new ErrorHandler() {
            /**
             * Invoked when an error occurs.
             */
            @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {
                LOGGER.debug("Error in Single record move button");
            }
        });
        
        btnMove.addClickListener(new Button.ClickListener() {

			/**
			 * Called when a Button has been clicked.
			 */
			@SuppressWarnings("unchecked")
			public void buttonClick(final Button.ClickEvent event) {
                            LOGGER.debug("Entering inside  MOVE_RIGHT  ");
                               final Set<RSFormulaDTO> formulaList =  (Set<RSFormulaDTO>) availableTable.getValue();
                                for(RSFormulaDTO formula:formulaList){ 
                                         DynamicQuery query = DynamicQueryFactoryUtil.forClass(FormulaDetailsMaster.class);
                                          query.add(RestrictionsFactoryUtil.ilike(ConstantUtil.FORMULA_DESC,formula.getFormulaName()));
                                          try { 
                                              List<FormulaDetailsMaster> list= FormulaDetailsMasterLocalServiceUtil.dynamicQuery(query);
                                              if(list!=null && !list.isEmpty()&& Integer.valueOf(list.get(0).getFormulaId())!=0){

                                              }else{
                                                 AbstractNotificationUtils.getErrorNotification(Constants.ERROR,"Please select valid Formula"); 
                                                 return;
                                              }
                                          } catch (SystemException ex) {
                                              LOGGER.error(ex);
                                          }
                                      }
                                for(RSFormulaDTO formula:formulaList){  
                                try {
                                     boolean flag=true;
                                            if(formula!=null){     
                                                 if(availableTable.getValue()!= null){
                                                    List<ImtdContRsDetailsFrDTO> selectedList=selectedBeanItemContainer.getItemIds();
                                                    for(int i=0;i<selectedList.size();i++){
                                                        DynamicQuery query = DynamicQueryFactoryUtil.forClass(FormulaDetailsMaster.class);
                                                                query.add(RestrictionsFactoryUtil.ilike(ConstantUtil.FORMULA_DESC,formula.getFormulaName()));
                                                                List<FormulaDetailsMaster> list= FormulaDetailsMasterLocalServiceUtil.dynamicQuery(query);
                                                        if(Integer.valueOf(list.get(0).getFormulaId())==selectedList.get(i).getFormulaId()){
                                                                 flag=false;
                                                            } else {
                                                               
                                                            }
                                                         
                                                    }
                                                    if(flag){    
                                                        selectedBeanItemContainer.addBean(logic.addToSelectedFormulaList(formula, imtdContRsdSid, 0, 0, itemSid));
                                                    }                                           
                                                 }else{
                                                        AbstractNotificationUtils.getErrorNotification(Constants.ERROR, ConstantUtil.PLEASE_SELECT_ATLEAST_ONE_ITEM);
                                                  }
                                            }    
                                    } catch (Exception exception) {
					AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1004));
					LOGGER.error(exception);

                                    }
                        }  
                      selectedTable.setCurrentPage(selectedTable.getCurrentPage());
                      LOGGER.debug("Ending MOVE_RIGHT  method");

			}
		});
        
        return btnMove;
     }
     
     public Button remove() {

       
        btnRemove.setDescription("Move selected items to left");
        btnRemove.setWidth("70");
        btnRemove.setErrorHandler(new ErrorHandler() {
            /**
             *
             */
            @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {
                LOGGER.debug("Error in Single record remove button");
            }
        });
        
        btnRemove.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                Set<ImtdContRsDetailsFrDTO> list=(Set<ImtdContRsDetailsFrDTO>)selectedTable.getValue();
                   for(ImtdContRsDetailsFrDTO dto:list){   
                       try {
                                if(dto!=null){ 
                                  if(selectedTable.getValue()!= null){
                                   
                                      selectedBeanItemContainer.removeItem(dto);
                                  }else{
                                      AbstractNotificationUtils.getErrorNotification(Constants.ERROR, ConstantUtil.PLEASE_SELECT_ATLEAST_ONE_ITEM);
                                  }
                                }  
                            }catch (Exception ex) {
                                final  String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                                LOGGER.error(errorMsg);
                                AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
                            } 
				LOGGER.debug("Ending  MOVE_LEFT  method ");
                  }
                selectedTable.setCurrentPage(selectedTable.getCurrentPage());
            }
            
        });
        
        return btnRemove;
     }
     
     public Button addAll() {
        
        btnMoveAll.setDescription("Move all items to right");
        btnMoveAll.setImmediate(true);
        btnMoveAll.setWidth("70");
        btnMoveAll.setErrorHandler(new ErrorHandler() {
            /**
             * Invoked when an error occurs.
             */
            @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {
               LOGGER.error("Error in All button");
            }
        }); 
        
         btnMoveAll.addClickListener(new Button.ClickListener() {

			/**
			 * Called when a Button has been clicked.
			 */
			@SuppressWarnings("unchecked")
			public void buttonClick(final Button.ClickEvent event) {
                             LOGGER.debug("Entering inside  MOVE_RIGHT ALL  method ");
                             
                                        
                                  final List<RSFormulaDTO> formulaList =  availableBeanItemContainer.getItemIds();
                                for(RSFormulaDTO formula:formulaList){ 
                                         DynamicQuery query = DynamicQueryFactoryUtil.forClass(FormulaDetailsMaster.class);
                                          query.add(RestrictionsFactoryUtil.ilike(ConstantUtil.FORMULA_DESC,formula.getFormulaName()));
                                          try { 
                                              List<FormulaDetailsMaster> list= FormulaDetailsMasterLocalServiceUtil.dynamicQuery(query);
                                              if(list!=null && !list.isEmpty()&& Integer.valueOf(list.get(0).getFormulaId())!=0){

                                              }else{
                                                 AbstractNotificationUtils.getErrorNotification(Constants.ERROR,"Please select valid Formula"); 
                                                 return;
                                              }
                                          } catch (SystemException ex) {
                                              LOGGER.error(ex);
                                          }
                                      }
                                for(RSFormulaDTO formula:formulaList){  
                                try { 
                                    boolean flag=true;
                                            if(formula!=null){     
                                                 if(availableTable.getValue()!= null){
                                                    List<ImtdContRsDetailsFrDTO> selectedList=selectedBeanItemContainer.getItemIds();
                                                    for(int i=0;i<selectedList.size();i++){
                                                        DynamicQuery query = DynamicQueryFactoryUtil.forClass(FormulaDetailsMaster.class);
                                                                query.add(RestrictionsFactoryUtil.ilike(ConstantUtil.FORMULA_DESC,formula.getFormulaName()));
                                                                List<FormulaDetailsMaster> list= FormulaDetailsMasterLocalServiceUtil.dynamicQuery(query);
                                                            if(list!=null && !list.isEmpty() && Integer.valueOf(list.get(0).getFormulaId())==selectedList.get(i).getFormulaId())  {  
                                                                    flag=false;
                                                            }
                                                    }
                                                    if(flag){    
                                                        selectedBeanItemContainer.addBean(logic.addToSelectedFormulaList(formula, imtdContRsdSid, 0, 0, itemSid));
                                                    }                                           
                                                 }else{
                                                        AbstractNotificationUtils.getErrorNotification(Constants.ERROR, ConstantUtil.PLEASE_SELECT_ATLEAST_ONE_ITEM);
                                                  }
                                            }  
                                   
                                    } catch (Exception exception) {
					AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1004));
					LOGGER.error(exception);

                                    }
                        LOGGER.debug("Ending MOVE_RIGHT ALL method ");

                                }
                        selectedTable.setCurrentPage(selectedTable.getCurrentPage());

			}
		});
      return  btnMoveAll; 
     } 
     
     public Button removeAll() {
        
        btnRemoveAll.setDescription("Move all items to left");
        btnRemoveAll.setWidth("70");
        btnRemoveAll.setErrorHandler(new ErrorHandler() {
            @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {
               LOGGER.error("Error in Remove All button");
            }
        });
        btnRemoveAll.addClickListener(new Button.ClickListener() {

			/**
			 * Called when a Button has been clicked.
			 */
			@SuppressWarnings("unchecked")
			public void buttonClick(final Button.ClickEvent event) {
                            LOGGER.debug("Entering inside  MOVE_RIGHT  method ");
                               try {
                                        
                                   selectedBeanItemContainer.removeAllItems();
                                   
                                    } catch (Exception exception) {
					AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1004));
					LOGGER.error(exception);

                                    }
                         selectedTable.setCurrentPage(selectedTable.getCurrentPage());
                      
                      LOGGER.debug("Ending MOVE_RIGHT  method ");

			}
		});
      return btnRemoveAll;
     }    
    public void addSelectedTable() {
        LOGGER.debug("Entering addAvailableTable");           
        selectedTable.markAsDirty();
        selectedBeanItemContainer = new BeanItemContainer<>(ImtdContRsDetailsFrDTO.class);
        selectedBeanItemContainer.addAll(logic.getLoadImtdContRsDetailsFr(itemSid));
        selectedTable.setContainerDataSource(selectedBeanItemContainer);
        selectedTable.setVisibleColumns("formulaId","formulaName");
        selectedTable.setColumnHeaders("Formula ID","Formula Name");
        selectedTable.setPageLength(NumericConstants.TEN);
        selectedTable.setNullSelectionAllowed(true);
        selectedTable.sinkItemPerPageWithPageLength(false);
        selectedTable.setImmediate(true);
        selectedTable.setSelectable(true);
        selectedTable.setMultiSelect(true);
        selectedTable.setMultiSelectMode(MultiSelectMode.DEFAULT);
        selectedTable.setWidth("390px");        
        HorizontalLayout layout = ResponsiveUtils.getResponsiveControls(selectedTable.createControls(),true);
        selectedTableLayout.addComponent(layout);
        selectedTable.addItemClickListener(new ItemClickListener() {

            /**
             * Called when a item has been clicked.
             */
            @Override
            public void itemClick(ItemClickEvent event) {
                return;
            }
        });
        selectedTable.setErrorHandler(new ErrorHandler() {
            /**
             * Invoked when an error occurs.
             */
            public void error(final com.vaadin.server.ErrorEvent event) {
               LOGGER.error("Error in avalable table");
            }
        });
        LOGGER.debug("Ending addAvailableTable");        
    }  
       public Button addSaveButton() {
        
        saveBtn.setImmediate(true);
        saveBtn.setErrorHandler(new ErrorHandler() {
            /**
             * Invoked when an error occurs.
             */
            @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {
                LOGGER.debug("Error in Single record move button");
            }
        });
        
        saveBtn.addClickListener(new Button.ClickListener() {

			/**
			 * Called when a Button has been clicked.
			 */
			@SuppressWarnings("unchecked")
			public void buttonClick(final Button.ClickEvent event) {
                            LOGGER.debug("Entering inside  MOVE_RIGHT  method ");
                            logic.removeAllItemsFormula(itemSid);       
                            final List<ImtdContRsDetailsFrDTO> selectedList =  selectedBeanItemContainer.getItemIds();
                                for(ImtdContRsDetailsFrDTO formula:selectedList){  
                                        try {
                                                if(formula!=null){     
                                                     logic.saveToImtdFormulaRsDetails(formula, imtdContRsdSid, 0, 0, itemSid);
                                                }    
                                        } catch (Exception exception) {
                                            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1004));
                                            LOGGER.error(exception);

                                        }
                                }  
                            LOGGER.debug("Ending MOVE_RIGHT  method ");
                      close();
			}
		});
        
        return saveBtn;
     }
      
       
}
