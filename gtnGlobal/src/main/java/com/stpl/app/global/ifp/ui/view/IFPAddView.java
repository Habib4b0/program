package com.stpl.app.global.ifp.ui.view;

import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.global.ifp.dto.IFPItemDTO;
import com.stpl.app.global.ifp.dto.ItemFamilyplanMasterDTO;
import com.stpl.app.global.ifp.logic.IFPLogic;
import com.stpl.app.global.ifp.ui.form.IFPTabsheetForm;
import com.stpl.app.global.item.dto.ItemMasterDTO;
import com.stpl.app.global.priceschedule.util.FieldNameUtils;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.ConstantsUtils;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.VerticalLayout;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.jboss.logging.Logger;

// TODO: Auto-generated Javadoc
/**
 * The Class IFPAddView.
 */
public class IFPAddView extends VerticalLayout implements View {

	/**
	 * The Constant LOGGER.
	 */
	private static final Logger LOGGER = Logger.getLogger(IFPAddView.class);
	/**
	 * The Constant NAME.
	 */
	public static final String NAME = ConstantsUtils.ADD;
	/**
	 * The available item result bean.
	 */
	private final BeanItemContainer<ItemMasterDTO> availableItemResultBean = new BeanItemContainer<ItemMasterDTO>(ItemMasterDTO.class);
	/**
	 * The selected item result bean.
	 */
	private final BeanItemContainer<ItemMasterDTO> selectedItemResultBean = new BeanItemContainer<ItemMasterDTO>(ItemMasterDTO.class);
	/**
	 * The item details results bean.
	 */
	private final BeanItemContainer<IFPItemDTO> itemDetailsResultsBean = new BeanItemContainer<IFPItemDTO>(IFPItemDTO.class);
	/**
	 * The ifp master.
	 */
	private ItemFamilyplanMasterDTO ifpMaster = new ItemFamilyplanMasterDTO();
	/**
	 * The binder.
	 */
	private ErrorfulFieldGroup binder = new ErrorfulFieldGroup(new BeanItem<ItemFamilyplanMasterDTO>(ifpMaster));

	private Map<Integer, Object[]> itemMap = new HashMap<Integer, Object[]>();

	IFPTabsheetForm addFormForIFP;
	private final IFPLogic ifpLogic;
        SessionDTO sessionDTO;

	/**
	 * Instantiates a new IFP add view.
	 */
	public IFPAddView(final SessionDTO sessionDTO){
		super();
                this.sessionDTO=sessionDTO;
                ifpLogic = new IFPLogic(this.sessionDTO);
		try {
			this.removeAllComponents();
			setSpacing(true);
			setStyleName("bootstrap-company");
		} catch (Exception e) {
			LOGGER.error(e);
		}
	}

	/**
	 * Method which will get call from form while page loading.
	 *
	 * @param event
	 *            the event
	 */
	public void enter(final ViewChangeEvent event) {
		try {

			String mode = (String) sessionDTO.getMode();
			LOGGER.debug("IFPTabsheetForm with mode :" + mode);
			final Date tempDate = new Date();
			final SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
			final SimpleDateFormat fmtID = new SimpleDateFormat("MMddyyyyhhmmssms");
			
                        sessionDTO.setSessionDate(fmt.format(tempDate));
                        sessionDTO.setUiSessionId(fmtID.format(tempDate));
                        
			this.removeAllComponents();
			binder = new ErrorfulFieldGroup(new BeanItem<ItemFamilyplanMasterDTO>(new ItemFamilyplanMasterDTO()));
			availableItemResultBean.removeAllItems();
			selectedItemResultBean.removeAllItems();
			itemDetailsResultsBean.removeAllItems();

			if (ConstantsUtils.ADD.equals(mode)) {
				ifpLogic.removeAllFromTempTable(true);
                                ifpMaster = new ItemFamilyplanMasterDTO();
				addFormForIFP = new IFPTabsheetForm(ifpMaster, binder, availableItemResultBean, selectedItemResultBean, itemDetailsResultsBean, itemMap, mode, sessionDTO);
                                addComponent(addFormForIFP);
			} else if (ConstantsUtils.EDIT.equals(mode)) {
				ifpLogic.removeAllFromTempTable(true);
				final int systemId = sessionDTO.getSystemId();
				ifpLogic.loadTempTable(systemId);
				// refreshing the Master model from data base
				ifpLogic.getIFPById(Integer.valueOf(systemId), ifpMaster);
                                binder.setItemDataSource(new BeanItem<ItemFamilyplanMasterDTO>(ifpMaster));
				addFormForIFP = new IFPTabsheetForm(ifpMaster, binder, availableItemResultBean, selectedItemResultBean, itemDetailsResultsBean, itemMap, mode, sessionDTO);
                                addComponent(addFormForIFP);
				addFormForIFP.getIfpItemAddition().loadSelectedTable();
				binder.getField(FieldNameUtils.INTERNAL_NOTES).setReadOnly(true);
                    } else if (mode.equals("View")) {
                        LOGGER.debug("IFPViewForm Method");
                        final int systemId = sessionDTO.getSystemId();
                        ifpLogic.getIFPById(Integer.valueOf(systemId),ifpMaster);
                        binder.setItemDataSource(new BeanItem<ItemFamilyplanMasterDTO>(ifpMaster));
                        addFormForIFP = new IFPTabsheetForm(ifpMaster, binder, availableItemResultBean, selectedItemResultBean, itemDetailsResultsBean, itemMap, mode, sessionDTO);
                        addComponent(addFormForIFP);

                        LOGGER.debug("Entering View in Add form");
                    }
			

		} catch (Exception e) {
			LOGGER.error(e);
		}

	}

	/**
	 * Gets the binder.
	 *
	 * @return the binder
	 */
	public ErrorfulFieldGroup getBinder() {
		return binder;
	}

	/**
	 * Sets the binder.
	 *
	 * @param binder
	 *            the new binder
	 */
	public void setBinder(final ErrorfulFieldGroup binder) {
		this.binder = binder;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name;
	 */
	public static String getName() {
		return NAME;
	}

	/**
	 * Gets the available item result bean.
	 *
	 * @return the available item result bean
	 */
	public BeanItemContainer<ItemMasterDTO> getAvailableItemResultBean() {
		return availableItemResultBean;
	}

	/**
	 * Gets the selected item result bean.
	 *
	 * @return the selected item result bean
	 */
	public BeanItemContainer<ItemMasterDTO> getSelectedItemResultBean() {
		return selectedItemResultBean;
	}

	/**
	 * Gets the item details results bean.
	 *
	 * @return the item details results bean
	 */
	public BeanItemContainer<IFPItemDTO> getItemDetailsResultsBean() {
		return itemDetailsResultsBean;
	}

	/**
	 * Gets the ifp master.
	 *
	 * @return the ifp master
	 */
	public ItemFamilyplanMasterDTO getIfpMaster() {
		return ifpMaster;
	}

}
