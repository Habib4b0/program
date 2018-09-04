
package com.stpl.app.gtnforecasting.discountProjection.form;

import com.stpl.app.gtnforecasting.discountProjection.dto.LookUpDTO;
import com.stpl.app.gtnforecasting.discountProjection.logic.SupplementalDiscountProjectionLogic;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.gtnforecasting.utils.HeaderUtils;
import com.stpl.app.ui.errorhandling.ErrorLabel;
import static com.stpl.app.utils.Constants.CommonConstants.SELECT_ONE;
import static com.stpl.app.utils.Constants.WindowMessagesName.CLOSE_CONFIRMATION;
import static com.stpl.app.utils.Constants.WindowMessagesName.RESET_CONFIRMATION;
import com.stpl.app.utils.ValidationUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.server.Sizeable;
import com.vaadin.shared.ui.MultiSelectMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Window;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.data.validator.RegexpValidator;
import com.vaadin.v7.data.validator.StringLengthValidator;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.v7.ui.TextField;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 * The Class ParityLookup.
 * 
 * @author Vinodhini
 */
public class ParityLookup extends Window {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ParityLookup.class);
	
	/** The contract ddlb. */
	@UiField("contractDdlb")
	private ComboBox contractDdlb;
	
	/** The ndc. */
	@UiField("ndc")
	private TextField ndc;
	
	/** The brand name. */
	@UiField("brandName")
	private TextField brandName;
	
	/** The ndc desc. */
	@UiField("ndcDesc")
	private TextField ndcDesc;
	
	/** The search btn. */
	@UiField("searchBtn")
	private Button searchBtn;
	
	/** The reset btn. */
	@UiField("resetBtn")
	private Button resetBtn;
	
	/** The results table. */
	@UiField("resultsTable")
	private ExtFilterTable resultsTable;
	
	/** The add btn. */
	@UiField("addBtn")
	private Button addBtn;
	
	/** The parity table. */
	@UiField("parityTable")
	private ExtFilterTable parityTable;
	
	/** The submit btn. */
	@UiField("submitBtn")
	private Button submitBtn;
	
	/** The product reset btn. */
	@UiField("productResetBtn")
	private Button productResetBtn;
	
	/** The close btn. */
	@UiField("closeBtn")
	private Button closeBtn;
	
	/** The remove btn. */
	@UiField("removeBtn")
	private Button removeBtn;
        
        @UiField("errorLB")
        private ErrorLabel errorMsg;

	/** The results container. */
	private final BeanItemContainer<LookUpDTO> resultsContainer = new BeanItemContainer<>(LookUpDTO.class);
	
	/** The parity container. */
	private final BeanItemContainer<LookUpDTO> parityContainer = new BeanItemContainer<>(LookUpDTO.class);
         
	private CustomTextField parity;
        
        private SessionDTO sessionDto;
        
        private final SupplementalDiscountProjectionLogic sdpLogic = new SupplementalDiscountProjectionLogic();
        
        private final LookUpDTO lookUpDTO = new LookUpDTO();
        
        private Object period = null;
        
        private boolean populateFlag= false;
        private List<LookUpDTO> dtoListValue ;

    public ParityLookup() {

      
    }

	/**
	 * Instantiates a new customer tree lookup.
         * @param parity
	 */
    public ParityLookup(CustomTextField parity, SessionDTO sessionDto, boolean populateFlag) {
        super("Parity Lookup");
        super.setCaption("Parity Reference Lookup");
        this.parity = parity;
        this.sessionDto = sessionDto;
        this.populateFlag = populateFlag;
        period = parity.getData();
        addStyleName(Constant.BOOTSTRAP_UI);
        addStyleName(Constant.BOOTSTRAP);
        addStyleName(Constant.BOOTSTRAP_FORECAST_BOOTSTRAP_NM);
        setClosable(true);
        center();
        setModal(true);
        setWidth(NumericConstants.FLOAT_FIFTY_NINE, Sizeable.Unit.PERCENTAGE);
        setHeight(NumericConstants.FLOAT_THIRTY_FIVE, Sizeable.Unit.PERCENTAGE);
        init();
        LOGGER.debug("ParityLookup constructor ends");   
    }

	/**
	 * Inits the.
	 */
	public final void init() {
		LOGGER.debug("ParityLookup init method started");
                setContent(Clara.create(getClass().getResourceAsStream("/mandated/ParityLookup.xml"), this));
		configureFields();
                contractDdlb.focus();
		LOGGER.debug("ParityLookup init method ends");
	}

	/**
	 * Configure fields.
	 */
    private void configureFields() {
        errorMsg.setVisible(false);
        ndc.setValidationVisible(true);
        ndc.addValidator(new RegexpValidator(
                ValidationUtils.ALPHANUMERIC_VALIDATION,
                ValidationUtils.NDC_VALIDATION_MSG));


        ndc.addValidator(new StringLengthValidator(
                "Ndc# should be less than 50 characters", 0, NumericConstants.FIFTY, true));

        ndcDesc.setValidationVisible(true);
        ndcDesc.addValidator(new RegexpValidator(
                ValidationUtils.TEXT_VALIDATION,
                ValidationUtils.NDC_DESC_VALIDATION_MSG));


        ndcDesc.addValidator(new StringLengthValidator(
                "Brand Name should be less than 100 characters", 0, NumericConstants.HUNDRED, true));

        brandName.setImmediate(true);
        brandName.addValidator(new RegexpValidator(
                ValidationUtils.TEXT_VALIDATION,
                ValidationUtils.BRAND_NAME_VALIDATION_MSG));


        ndc.addValidator(new StringLengthValidator(
                "Brand Name should be less than 50 characters", 0, NumericConstants.FIFTY, true));
        ndc.setImmediate(true);
        ndcDesc.setImmediate(true);
        resultsTable.setContainerDataSource(resultsContainer);
        resultsTable.setVisibleColumns(HeaderUtils.getParityCols());
        resultsTable.setColumnHeaders(HeaderUtils.getParityHeader());
        resultsTable.setFilterBarVisible(true);
        resultsTable.setSelectable(true);
        resultsTable.setMultiSelect(true);
        resultsTable.setMultiSelectMode(MultiSelectMode.DEFAULT);
        resultsTable.setSortEnabled(true);
        resultsTable.setFilterDecorator(new ExtDemoFilterDecorator());
        
        parityTable.setContainerDataSource(parityContainer);
        parityTable.setVisibleColumns(HeaderUtils.getParityCols());
        parityTable.setColumnHeaders(HeaderUtils.getParityHeader());
        parityTable.setFilterBarVisible(true);
        parityTable.setSelectable(true);
        parityTable.setMultiSelect(true);
        parityTable.setMultiSelectMode(MultiSelectMode.DEFAULT);
        parityTable.setSortEnabled(true);
        parityTable.setFilterDecorator(new ExtDemoFilterDecorator());
        
        contractDdlb.removeAllItems();
        sdpLogic.loadParityNativeSelect(contractDdlb,sessionDto,true);
        contractDdlb.setNullSelectionAllowed(true);
        contractDdlb.setNullSelectionItemId(SELECT_ONE);
        contractDdlb.select(SELECT_ONE);
      
    }

	/**
	 * Close button click listener.
	 *
	 * @param event the event
	 */
	@UiHandler("closeBtn")
	public void closeBtn(Button.ClickEvent event) {
		new AbstractNotificationUtils() {
                        @Override
			public void noMethod() {
			    return;
			}

			@Override
			/**
			 * The method is triggered when Yes button of the message
			 * box is pressed .
			 *
			 * @param buttonId The buttonId of the pressed button.
			 */
			public void yesMethod() {
				close();
			}
		}.getConfirmationMessage(CLOSE_CONFIRMATION.getConstant(), 
				"Are you sure you want to close the NDC Parity Lookup?");

	}

	/**
	 * Reset button click listener.
	 *
	 * @param event the event
	 */
	@UiHandler("resetBtn")
	public void resetBtn(ClickEvent event) {
		new AbstractNotificationUtils() {
                        @Override
			public void noMethod() {
                            return;
			}

			@Override
			/**
			 * The method is triggered when Yes button of the message
			 * box is pressed .
			 *
			 * @param buttonId The buttonId of the pressed button.
			 */
			public void yesMethod() {
				contractDdlb.setValue(null);
				ndc.setValue(StringUtils.EMPTY);
				ndcDesc.setValue(StringUtils.EMPTY);
				brandName.setValue(StringUtils.EMPTY);
			}
		}.getConfirmationMessage(RESET_CONFIRMATION.getConstant(), "Are you sure you want to reset the page to default/previous values?");
	}

	/**
	 * Product reset button click listener.
	 *
	 * @param event the event
	 */
	@UiHandler("productResetBtn")
	public void productResetBtn(ClickEvent event) {
		new AbstractNotificationUtils() {
                        @Override
			public void noMethod() {
				// do nothing
			}

			@Override
			/**
			 * The method is triggered when Yes button of the message
			 * box is pressed .
			 *
			 * @param buttonId The buttonId of the pressed button.
			 */
			public void yesMethod() {
				parityContainer.removeAllItems();
			}
	 }.getConfirmationMessage(RESET_CONFIRMATION.getConstant(), "Are you sure you want to reset the Parity Products list view?");
  }

	/**
	 * Search button click listener.
	 *
	 * @param event the event
	 */
	@UiHandler("searchBtn")
	public void searchBtn(Button.ClickEvent event) {    
            
                boolean valid= validateFields();    
                List<LookUpDTO> parityDtoList = new ArrayList<>();
                resultsContainer.removeAllItems();
                lookUpDTO.setContractName(contractDdlb.getValue()!=null?contractDdlb.getValue().toString():StringUtils.EMPTY);
                lookUpDTO.setBrandName(brandName.getValue()==StringUtils.EMPTY?StringUtils.EMPTY:brandName.getValue().replace(CommonUtils.CHAR_ASTERISK,
                        CommonUtils.CHAR_PERCENT));
                lookUpDTO.setItemNo(ndc.getValue()==StringUtils.EMPTY?StringUtils.EMPTY:ndc.getValue().replace(CommonUtils.CHAR_ASTERISK,
                        CommonUtils.CHAR_PERCENT));
                lookUpDTO.setItemDesc(ndcDesc.getValue()==StringUtils.EMPTY?StringUtils.EMPTY:ndcDesc.getValue().replace(CommonUtils.CHAR_ASTERISK,
                        CommonUtils.CHAR_PERCENT));
                if(StringUtils.isNotBlank(lookUpDTO.getContractName()) || StringUtils.isNotBlank(lookUpDTO.getBrandName())
                        || StringUtils.isNotBlank(lookUpDTO.getItemNo()) || StringUtils.isNotBlank(lookUpDTO.getItemDesc())){
                List<Object> parityList = sdpLogic.loadParityLookup(sessionDto,lookUpDTO,false);
                    if (parityList != null && !parityList.isEmpty()) {
                    for (Object list1 : parityList) {
                        final Object[] obj = (Object[]) list1;
                        LookUpDTO parityDto = new LookUpDTO();
                        parityDto.setItemMasterSid(Integer.parseInt(String.valueOf(obj[0])));
                        parityDto.setContractMasterSid(Integer.parseInt(String.valueOf(obj[1])));
                        parityDto.setContractName(String.valueOf(obj[NumericConstants.TWO]==null?StringUtils.EMPTY:obj[NumericConstants.TWO]));
                        parityDto.setBrandName(String.valueOf(obj[NumericConstants.THREE]==null?StringUtils.EMPTY:obj[NumericConstants.THREE]));
                        parityDto.setItemNo(String.valueOf(obj[NumericConstants.FOUR]==null?StringUtils.EMPTY:obj[NumericConstants.FOUR]));
                        parityDto.setItemDesc(String.valueOf(obj[NumericConstants.FIVE]==null?StringUtils.EMPTY:obj[NumericConstants.FIVE]));
                        parityDtoList.add(parityDto);
                    }
                }
   
                if(valid)
                {      
                   errorMsg.setVisible(false);
                }
                if(!parityDtoList.isEmpty() && valid)
                {
                 errorMsg.setVisible(false);
                 Notification.show("Search Completed");

                resultsContainer.addAll(parityDtoList);                 
                }
                else 
                AbstractNotificationUtils.getErrorNotification("No Value Found", "There are no results that match the search criteria. ");
                } else {
                AbstractNotificationUtils.getErrorNotification("Search Error", "Please provide valid data to search. ");    
                }    
            } 
	
       boolean validateFields() {
        StringLengthValidator sd = new StringLengthValidator("Brand Name Should be less than 50 characters", 0, NumericConstants.FIFTY, true);
        StringLengthValidator sd2 = new StringLengthValidator("NDC DESC Should be less than 100 characters", 0, NumericConstants.HUNDRED, true);
        RegexpValidator rg = new RegexpValidator("([a-z|A-Z|\\\\ |\\\\*])*", "Brand Name should be text");
        RegexpValidator rg2 = new RegexpValidator("([0-9|a-z|A-Z|\\\\ |\\\\*])*", "NDC should be AlphaNumeric");
        boolean check1;
        boolean check2;
        boolean check3;
        boolean check4;
        boolean check5;
        boolean check6;
           check1 = sd.isValid(brandName.getValue());
           check3 = sd.isValid(ndc.getValue());
           check2 = sd2.isValid(ndcDesc.getValue());
           check4 = rg.isValid(brandName.getValue());
           check5 = rg2.isValid(ndc.getValue());
           check6 = rg.isValid(ndcDesc.getValue());
        boolean ret = true;
           if (!check1) {
               errorMsg.setValue(sd.getErrorMessage());
               ret = false;

           }
           if (!check2) {
               errorMsg.setValue(sd2.getErrorMessage());
               ret = false;

           }
           if (!check3) {
               errorMsg.setValue("NDC Should be less than 50 characters");
               ret = false;

           }
           if (!check4) {
               errorMsg.setValue(rg.getErrorMessage());
               ret = false;

           }
           if (!check5) {
               errorMsg.setValue(rg2.getErrorMessage());
               ret = false;

           }
           if (!check6) {
               errorMsg.setValue("NDC Description Should be text");
               ret = false;
           }
        if (!ret) {
            errorMsg.addStyleName("red");
            errorMsg.addStyleName("align-left");
            errorMsg.setVisible(true);
        }
        brandName.setValidationVisible(true);
        ndc.setValidationVisible(true);
        ndcDesc.setValidationVisible(true);
        brandName.setImmediate(true);
        ndc.setImmediate(true);
        ndcDesc.setImmediate(true);
        return ret;

    }
    /**
     * Submit button click listener.
     *
     * @param event the event
     */
    @UiHandler("submitBtn")
    public void submitBtn(Button.ClickEvent event) {
        if (parityTable.size() > 0) {
            new AbstractNotificationUtils() {
                @Override
                public void noMethod() {
                    // do nothing
                }

                @Override
                /**
                 * The method is triggered when Yes button of the message box is
                 * pressed .
                 *
                 * @param buttonId The buttonId of the pressed button.
                 */
                public void yesMethod() {

                    List<LookUpDTO> dtoList = (List<LookUpDTO>) parityTable.getItemIds();

                    if (populateFlag) {
                        if (!dtoList.isEmpty()) {
                            parity.setValue(dtoList.size() > 1 ? Constant.MULTIPLE : dtoList.get(0).getItemNo());
                          setDtoListValue(dtoList);

                        }
                       
                        close();
                    } else {
                        Calendar now = Calendar.getInstance();
                     // Gets the current date and time

                        int curryear = now.get(Calendar.YEAR);
                        String splitPeriodYear = String.valueOf(period).replace("ParityReference", StringUtils.EMPTY).replace(Constant.Q_SMALL, StringUtils.EMPTY);
                        final String year = splitPeriodYear.substring(splitPeriodYear.length() - String.valueOf(curryear).length());
                        final String periodyear = splitPeriodYear.substring(0, splitPeriodYear.length() - String.valueOf(curryear).length());
                        String[] periodList = sdpLogic.getYearAndPeriod(periodyear, year);
                        List returnList = sdpLogic.callParityProcedure(dtoList, periodList, sessionDto);
                        if(!returnList.isEmpty() && returnList.size()>0){
                        parityNDCInsert((List<LookUpDTO>) returnList.get(0), (List<String>) returnList.get(1));
                        }
                        else{
                        AbstractNotificationUtils.getErrorNotification("Submit Error", "There is no  values for selected NDCs. ");
                        }
                        
                    }

                }

            }.getConfirmationMessage("Submit Confirmation", "Are you sure you want to Submit these selected NDC’s?");
        } 
    }

	/**
	 * Adds button click listener.
	 *
	 * @param event the event
	 */
  @UiHandler("addBtn")
    public void addBtn(ClickEvent event) {
        try {
            Set<Object> selectedItemIds = (Set<Object>) resultsTable.getValue();
            List<LookUpDTO> dtoList = (List<LookUpDTO>) parityTable.getItemIds();
            if (selectedItemIds.size() > 0) {
                for (Object selectedId : selectedItemIds) {
                    boolean valid = true;
                    LookUpDTO dto = (LookUpDTO) (selectedId);

                    for (Object checkid : dtoList) {
                        LookUpDTO dto2 = (LookUpDTO) (checkid);

                        if ((dto.getContractName().equals(dto2.getContractName())) && (dto.getItemNo().equals(dto2.getItemNo()))) {
                            valid = false;
                            Notification.show(StringUtils.EMPTY + dto2.getItemNo() + " Already added");
                            break;
                        }
                    }
                    if (valid) {
                        parityContainer.addItem(selectedId);
                        resultsContainer.removeItem(selectedId);

                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

	/**
	 * Remove button click listener.
	 *
	 * @param event the event
	 */
	@UiHandler("removeBtn")
	public void removeBtn(ClickEvent event) {
		try {
			Set<Object> selectedRemoveItemIds = (Set<Object>) parityTable.getValue();
			if (selectedRemoveItemIds.size() > 0) {
				for (Object itemId : selectedRemoveItemIds) {
					resultsContainer.addItem(itemId);
					parityContainer.removeItem(itemId);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}
  
   
    
    public void parityNDCInsert(final List<LookUpDTO> finalDtoList, List<String> notifyList ){
         StringBuilder tempNotify = new StringBuilder();
        if (notifyList.isEmpty()) {
                        if (!finalDtoList.isEmpty()) {
                            sdpLogic.insertInParity(finalDtoList,0);

                            if (finalDtoList.size() > 1) {
                                parity.setValue(Constant.MULTIPLE);
                            } else {
                                parity.setValue(finalDtoList.get(0).getItemNo());
                            }
                        }
                        close();
                    } else {
                        
                        for (int i = 0; i < notifyList.size(); i++) {
                            String temp = notifyList.get(i);
                            tempNotify.append(temp);
                            if (i != notifyList.size() - 1) {
                                tempNotify.append(", ");
                            }
                        }
                        
                        if(finalDtoList.isEmpty()){
                            AbstractNotificationUtils.getErrorNotification("Submit Error", "There is no prior values for selected NDCs. ");
                        } else {
                        new AbstractNotificationUtils() {

                            @Override
                            public void noMethod() {
                                
                                close();
                            }

                            @Override
                            public void yesMethod() {
                                if (!finalDtoList.isEmpty()) {
                                    sdpLogic.insertInParity(finalDtoList,0);

                                    if (finalDtoList.size() > 1) {
                                        parity.setValue(Constant.MULTIPLE);
                                    } else {
                                        parity.setValue(finalDtoList.get(0).getItemNo());
                                    }

                                }
                                close();
                            }
                        }.getConfirmationMessage("Submit Confirmation", "NDC " + tempNotify + " are not having any prior values.Do you wish to continue.?");
                
                    }
                }
    }

    public List<LookUpDTO> getDtoListValue() {
        return dtoListValue == null ? dtoListValue : new ArrayList<>(dtoListValue);
    }

    public void setDtoListValue(List<LookUpDTO> dtoListValue) {
        this.dtoListValue = dtoListValue == null ? dtoListValue : new ArrayList<>(dtoListValue);
    }
    
    
    public void processParity(final String year, final String period, List<LookUpDTO> dtoList) {
        String[] periodList = sdpLogic.getYearAndPeriod(period, year);
        List returnList = sdpLogic.callParityProcedure(dtoList, periodList, sessionDto);
        parityNDCInsert((List<LookUpDTO>) returnList.get(0), (List<String>) returnList.get(1));
    }

}

