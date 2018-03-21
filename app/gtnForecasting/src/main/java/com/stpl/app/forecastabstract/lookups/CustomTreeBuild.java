/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.forecastabstract.lookups;

/**
 *
 * @author karthikraja.k
 */
import com.stpl.app.gtnforecasting.logic.CommonLogic;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.model.CustomViewMaster;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.vaadin.v7.data.util.AbstractContainer;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.ui.Table;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.container.ExtTreeContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomTreeBuild extends AbstractCustomTreeView {

	/**
	 * The log.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomTreeBuild.class);
	protected SessionDTO session;
	protected int customId = 0;
	protected boolean isSelect = false;
	protected CustomViewMaster customView = null;

	/**
	 * The Constructor.
	 *
	 * @param string
	 *            the string
	 * @param projectionId
	 *            the projection id
	 */
	public CustomTreeBuild(SessionDTO session) {
		this(session, 0);

	}

	/**
	 * The Constructor.
	 *
	 * @param string
	 *            the string
	 * @param projectionId
	 *            the projection id
	 * @param customId
	 */
	public CustomTreeBuild(SessionDTO session, int customId) {
		super(session);
		this.session = session;
		this.customId = customId;
		if (customId != 0) {
			isSelect = true;
		}
		init();
	}

	@Override
	protected int customTreeSelectLogic(String viewName) {

		int returnBack = customTreeSaveLogic(viewName);

		if (returnBack != 0) {
			isSelect = true;
			customId = returnBack;
		}

		return returnBack;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.stpl.app.forecastabstract.lookups.AbstractCustomTreeView#
	 * customTreeCloseLogic()
	 */
	@Override
	protected void customTreeCloseLogic() {
		close();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.stpl.app.forecastabstract.lookups.AbstractCustomTreeView#
	 * customTreeAddCustomerLogic()
	 */
	@Override
	protected void customTreeAddCustomerLogic() {

		customTreeAddLogic(customerTable);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.stpl.app.forecastabstract.lookups.AbstractCustomTreeView#
	 * customTreeAddProductLogic()
	 */
	@Override
	protected void customTreeAddProductLogic() {

		customTreeAddLogic(productTable);
	}

	private void customTreeAddLogic(Table table) {
		Object itemId = table.getValue();
		Object treeItemId = treeTable.getValue();
		treeTable.setImmediate(true);
		if (itemId != null) {
			Leveldto pitemId = (Leveldto) itemId;
			if (treeTable.getItemIds().size() >= 1) {
				Object treeItemId1 = treeTable.lastItemId();
				Leveldto titemId = (Leveldto) treeItemId;
				Leveldto tlitemId = (Leveldto) treeItemId1;

				if (treeItemId == null || treeItemId.equals(treeItemId1)) {

					if (isValidTree(table, pitemId.getHierarchyIndicator())) {
						pitemId.setTreeLevelNo(treeTable.getItemIds().size() + 1);
						treeTable.addItem(itemId);
						table.removeItem(itemId);
						treeTable.setParent(itemId, treeItemId1);
						treeTable.setCollapsed(treeItemId1, false);
					} else {
						AbstractNotificationUtils.getErrorNotification("Invalid Structure",
								"You cannot add " + pitemId.getLevel() + " as a child to " + tlitemId.getLevel());
					}

				} else {
					AbstractNotificationUtils.getErrorNotification("Invalid Structure",
							"You cannot add " + pitemId.getLevel() + " as a child to " + titemId.getLevel());
				}
			} else {
				pitemId.setTreeLevelNo(1);
				treeTable.addItem(itemId);
				table.removeItem(itemId);
				treeTable.setChildrenAllowed(itemId, true);
			}
		} else {
			AbstractNotificationUtils.getErrorNotification("No Level Selected",
					"Please select a " + table.getCaption() + " level to move");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.stpl.app.forecastabstract.lookups.AbstractCustomTreeView#
	 * customTreeRemoveCustomerLogic()
	 */
	@Override
	protected void customTreeRemoveCustomerLogic() {
		customTreeRemoveLogic(customerTable);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.stpl.app.forecastabstract.lookups.AbstractCustomTreeView#
	 * customTreeRemoveProductLogic()
	 */
	@Override
	protected void customTreeRemoveProductLogic() {
		customTreeRemoveLogic(productTable);
	}

	private void customTreeRemoveLogic(Table table) {
		Object treeItemId = treeTable.getValue();
		if (treeItemId == null) {
			AbstractNotificationUtils.getErrorNotification("No level selected",
					"Please select a level to remove from tree structure");
		} else if (table.getCaption().contains(((Leveldto) treeItemId).getHierarchyIndicator())) {
			if (!treeTable.hasChildren(treeItemId)) {
				try {
					if (treeTable.removeItem(treeItemId)) {
						table.addItem(treeItemId);
					}
				} catch (UnsupportedOperationException e) {
					LOGGER.error(e.getMessage());
				}
			}
		} else {
			AbstractNotificationUtils.getErrorNotification("Illegal level",
					"Level which is selected belogs to"
							+ (table.getCaption().equals(customerTable.getCaption()) ? productTable.getCaption()
									: customerTable.getCaption()));
		}

	}

	private boolean isValidTree(Table table, String hierarchyIndicator) {
		Object treeLastItem = treeTable.lastItemId();

		Object movingItem = table.getValue();

		if ((movingItem != null)
				&& (getLastLevelNo(hierarchyIndicator, treeLastItem) < ((Leveldto) movingItem).getLevelNo())) {
			return true;
		}
		return false;
	}

	private boolean isTreeitem(Object item) {
		List itemids = treecontainer.getItemIds();
		for (Object item1 : itemids) {
			int treeHid = ((Leveldto) item1).getHierarchyId();
			int tableHid = ((Leveldto) item).getHierarchyId();
			String treeHind = ((Leveldto) item1).getHierarchyIndicator();
			String tableHind = ((Leveldto) item).getHierarchyIndicator();
			if (treeHid == tableHid && tableHind.equals(treeHind)) {
				return true;
			}
		}
		return false;
	}

	private int getLastLevelNo(String hierarchyIndicator, Object treeLastItem) {
		if (treeLastItem == null) {

			return 0;
		}
		Leveldto dto = (Leveldto) treeLastItem;

		if (dto.getHierarchyIndicator().equals(hierarchyIndicator)) {
			return dto.getLevelNo();
		} else {
			return getLastLevelNo(hierarchyIndicator, treeTable.getParent(treeLastItem));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.stpl.app.forecastabstract.lookups.AbstractCustomTreeView#
	 * customTreeSaveLogic(com.vaadin.data.util.AbstractContainer, java.lang.String)
	 */
	@Override
	protected int customTreeSaveLogic(String viewName) {

		int returnBack = 0;
		if (viewName != null && viewName.trim().length() > 0) {
			if (treecontainer.getItemIds().size() > 0) {
				if (CommonLogic.isValidViewName(session.getProjectionId(), viewName, customId)) {
					returnBack = CommonLogic.customViewSaveLogic(session, customId, viewName,
							treecontainer.getItemIds());
				} else {
					AbstractNotificationUtils.getErrorNotification("Save Name Check",
							"That view name is taken. Please enter a new View name.");
				}
			} else {
				AbstractNotificationUtils.getErrorNotification("Empty Hierarchy", "Please create a Hierarchy");
			}
		} else {
			AbstractNotificationUtils.getErrorNotification("Empty view name", "Please enter a view name");
		}
		customId = returnBack;

		return returnBack;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.stpl.app.forecastabstract.lookups.AbstractCustomTreeView#loadCustomTree()
	 */
	@Override
	protected void loadCustomTree() {
		if (treecontainer == null) {
			treecontainer = new ExtTreeContainer<>(Leveldto.class);
		}
		treecontainer.removeAllItems();
		List<Leveldto> customList = CommonLogic.getCustomTree(customId);
		Leveldto parent = null;
		for (Leveldto lvlDTO : customList) {
			treecontainer.addItem(lvlDTO);
			if (parent != null) {
				treecontainer.setParent(lvlDTO, parent);
				treeTable.setCollapsed(parent, false);
			}
			parent = lvlDTO;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.stpl.app.forecastabstract.lookups.AbstractCustomTreeView#loadCustomers()
	 */
	@Override
	protected void loadCustomers() {
		if (customerContainer == null) {
			customerContainer = new BeanItemContainer<>(Leveldto.class);
		}

		List customerList = CommonLogic.getCustomerHierarchy(session.getProjectionId(),
				Integer.parseInt(session.getCustomerLevelNumber()),session.getCustomerRelationVersion());

		int size = customerList.size();
		for (int i = 0; i < size; i++) {
			Object obj = customerList.get(i);
			if (isTreeitem(obj)) {
				customerList.remove(obj);
				size--;
				i--;
			}
		}

		customerContainer.addAll(customerList);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.stpl.app.forecastabstract.lookups.AbstractCustomTreeView#loadProducts()
	 */
	@Override
	protected void loadProducts() {
		if (productContainer == null) {
			productContainer = new BeanItemContainer<>(Leveldto.class);
		}

		List productList = CommonLogic.getProductHierarchy(session.getProjectionId(),
				session.getProductLevelNumber() != null ? Integer.parseInt(session.getProductLevelNumber()) : 1,session. getProductRelationVersion());

		int size = productList.size();
		for (int i = 0; i < size; i++) {
			Object obj = productList.get(i);
			if (isTreeitem(obj)) {
				productList.remove(obj);
				size--;
				i--;
			}
		}

		productContainer.addAll(productList);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.stpl.app.forecastabstract.lookups.AbstractCustomTreeView#loadDeductions()
	 */
	@Override
	protected void loadDeductions() {
		if (productContainer == null) {
			productContainer = new BeanItemContainer<>(Leveldto.class);
		}

		List productList = CommonLogic.getProductHierarchy(session.getProjectionId(),
				session.getProductLevelNumber() != null ? Integer.parseInt(session.getProductLevelNumber()) : 1,session.getDeductionRelationVersion());

		int size = productList.size();
		for (int i = 0; i < size; i++) {
			Object obj = productList.get(i);
			if (isTreeitem(obj)) {
				productList.remove(obj);
				size--;
				i--;
			}
		}

		productContainer.addAll(productList);
	}

	@Override
	protected String getCustomMasterData() {
		String str = StringUtils.EMPTY;
		customView = CommonLogic.getCustomView(customId);
		customId = 0;
		if (customView != null) {
			str = customView.getViewName();
			customId = customView.getCustomViewMasterSid();
		}
		return str;
	}

	@Override
	protected AbstractContainer getProductsContainer() {
		if (productContainer == null) {
			productContainer = new BeanItemContainer<>(Leveldto.class);
		}
		return productContainer;
	}

	@Override
	protected AbstractContainer getCustomersContainer() {
		if (customerContainer == null) {
			customerContainer = new BeanItemContainer<>(Leveldto.class);
		}
		return customerContainer;
	}

	@Override
	protected AbstractContainer getCustomTreeContainer() {
		if (treecontainer == null) {
			treecontainer = new ExtTreeContainer<>(Leveldto.class);
		}
		return treecontainer;
	}

	public int getCustomId() {
		return customId;
	}

	public boolean isIsSelect() {
		return isSelect;
	}

	@Override
	protected AbstractContainer getDeductionsContainer() {
		// To change body of generated methods, choose Tools | Templates.
		return null;
	}

	@Override
	protected void customTreeAddDeductionLogic() {
		// To change body of generated methods, choose Tools | Templates.
	}

	@Override
	protected void customTreeRemoveDeductionLogic() {
		// To change body of generated methods, choose Tools | Templates.
	}

}
