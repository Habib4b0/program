/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.dto;

import com.stpl.app.cff.abstractcff.AbstractCustomTreeView;
import com.stpl.app.cff.logic.CommonLogic;
import com.stpl.app.cff.queryutils.CommonQueryUtils;
import com.stpl.app.cff.util.AbstractNotificationUtils;
import com.stpl.app.cff.util.Constants;
import com.stpl.app.parttwo.model.CffCustomViewMaster;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.vaadin.v7.data.util.AbstractContainer;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.ui.Table;
import java.util.ArrayList;
import java.util.List;
import org.asi.ui.container.ExtTreeContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * The Class CustomTreeBuild.
 *
 * @author maheshj
 */
public class CustomTreeBuild extends AbstractCustomTreeView {

    private SessionDTO session;
    private int customId = 0;
    private boolean isSelect = false;
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomTreeBuild.class);

    /**
     * The Constructor.
     *
     * @param string the string
     * @param projectionId the projection id
     */
    public CustomTreeBuild(SessionDTO session) {
        this(session, 0);
    }

    /**
     * The Constructor.
     *
     * @param string the string
     * @param projectionId the projection id
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
         LOGGER.debug("customTreeSelectLogic started");
        int returnBack = customTreeSaveLogic(viewName);

        if (returnBack != 0) {
            isSelect = true;
            customId = returnBack;
        }
         
        LOGGER.debug("customTreeSelectLogic ended= {}", returnBack);
        return returnBack;
    }

    /* (non-Javadoc)
     * @see com.stpl.app.forecastabstract.lookups.AbstractCustomTreeView#customTreeCloseLogic()
     */
    @Override
    protected void customTreeCloseLogic() {
        close();
    }

    /* (non-Javadoc)
     * @see com.stpl.app.forecastabstract.lookups.AbstractCustomTreeView#customTreeAddCustomerLogic()
     */
    @Override
    protected void customTreeAddCustomerLogic() {
        // Logic to be written here
        customTreeAddLogic(customerTable);
    }

    /* (non-Javadoc)
     * @see com.stpl.app.forecastabstract.lookups.AbstractCustomTreeView#customTreeAddProductLogic()
     */
    @Override
    protected void customTreeAddProductLogic() {
        // Logic to be written here
        customTreeAddLogic(productTable);
    }
    
    @Override
    protected void customTreeAddDeductionLogic() {
        // Logic to be written here
        customTreeAddLogic(deductionTable);
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
                        AbstractNotificationUtils.getErrorNotification(Constants.ERROR_NOTIFICATION_INVALID_STRUCTURE, Constants.ERROR_NOTIFICATION_INVALID_ADDITION + pitemId.getLevel()
                                + Constants.ERROR_NOTIFICATION_INVALID_CHILD + tlitemId.getLevel());
                    }

                } else {
                    AbstractNotificationUtils.getErrorNotification(Constants.ERROR_NOTIFICATION_INVALID_STRUCTURE, Constants.ERROR_NOTIFICATION_INVALID_ADDITION + pitemId.getLevel()
                            + Constants.ERROR_NOTIFICATION_INVALID_CHILD + titemId.getLevel());
                }
            } else {
                pitemId.setTreeLevelNo(1);
                treeTable.addItem(itemId);
                table.removeItem(itemId);
                treeTable.setChildrenAllowed(itemId, true);
            }
        } else {
            AbstractNotificationUtils.getErrorNotification("No Level Selected", "Please select a " + table.getCaption() + " level to move");
        }
    }

    /* (non-Javadoc)
     * @see com.stpl.app.forecastabstract.lookups.AbstractCustomTreeView#customTreeRemoveCustomerLogic()
     */
    @Override
    protected void customTreeRemoveCustomerLogic() {
        customTreeRemoveLogic(customerTable);
    }

    /* (non-Javadoc)
     * @see com.stpl.app.forecastabstract.lookups.AbstractCustomTreeView#customTreeRemoveProductLogic()
     */
    @Override
    protected void customTreeRemoveProductLogic() {
        customTreeRemoveLogic(productTable);
    }
    
    @Override
    protected void customTreeRemoveDeductionLogic() {
        customTreeRemoveLogic(deductionTable);
    }

    private void customTreeRemoveLogic(Table table) {
        Object treeItemId = treeTable.getValue();
        if (treeItemId == null) {
            AbstractNotificationUtils.getErrorNotification("No level selected", "Please select a level to remove from tree structure");
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
            AbstractNotificationUtils.getErrorNotification("Illegal level", "Level which is selected belogs to" + (table.getCaption().equals(customerTable.getCaption()) ? productTable.getCaption() : customerTable.getCaption()));
        }

    }

    private boolean isValidTree(Table table, String hierarchyIndicator) {
        Object treeLastItem = treeTable.lastItemId();
          LOGGER.debug("treeLastItem.getLevelNo()= {}", ((Leveldto) treeLastItem).getLevelNo());
        Object movingItem = table.getValue();
          
        if (movingItem != null && getLastLevelNo(hierarchyIndicator, treeLastItem) < ((Leveldto) movingItem).getLevelNo()) {
            LOGGER.debug("movingItem.getLevelNo()= {}", ((Leveldto) movingItem).getLevelNo());
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


    /* (non-Javadoc)
     * @see com.stpl.app.forecastabstract.lookups.AbstractCustomTreeView#customTreeSaveLogic(com.vaadin.data.util.AbstractContainer, java.lang.String)
     */
    @Override
    protected int customTreeSaveLogic(String viewName) {
        LOGGER.debug("customTreeSaveLogic started");
            return 0;

    }

    /* (non-Javadoc)
     * @see com.stpl.app.forecastabstract.lookups.AbstractCustomTreeView#loadCustomTree()
     */
    @Override
    protected void loadCustomTree() {
        if (treecontainer == null) {
            treecontainer = new ExtTreeContainer<Leveldto>(Leveldto.class);
        }
        treecontainer.removeAllItems();
    }

    /* (non-Javadoc)
     * @see com.stpl.app.forecastabstract.lookups.AbstractCustomTreeView#loadCustomers()
     */
    @Override
    protected void loadCustomers() {
        if (customerContainer == null) {
            customerContainer = new BeanItemContainer<Leveldto>(Leveldto.class);
        }
        LOGGER.debug("loadCustomers projectionId= {} and session level number= {}", session.getProjectionId(), session.getCustomerLevelNumber());
        List customerList = CommonLogic.getCustomerHierarchy(session.getProjectionId(), Integer.parseInt(session.getCustomerLevelNumber()),session.getCustomerRelationVersion());
        LOGGER.debug("loadCustomers customerList size= {}", customerList.size());
        int size = customerList.size();
        for (int i = 0; i < size; i++) {
            Object obj = customerList.get(i);
            if (isTreeitem(obj)) {
                customerList.remove(obj);
                size--;
                i--;
            }
        }

        LOGGER.debug("loadCustomers customerList size= {}", customerList.size());
        customerContainer.addAll(customerList);
    }

    /* (non-Javadoc)
     * @see com.stpl.app.forecastabstract.lookups.AbstractCustomTreeView#loadProducts()
     */
    @Override
    protected void loadProducts() {
        if (productContainer == null) {
            productContainer = new BeanItemContainer<Leveldto>(Leveldto.class);
        }
        LOGGER.debug("loadProducts projectionId= {} and Product level number= {} ", session.getProjectionId(), session.getProductLevelNumber());
        List productList = CommonLogic.getProductHierarchy(session.getProjectionId(), Integer.parseInt(session.getProductLevelNumber()),session.getProductRelationVersion());
        LOGGER.debug("loadProducts productList Start size= {}", productList.size());
        int size = productList.size();
        for (int i = 0; i < size; i++) {
            Object obj = productList.get(i);
            if (isTreeitem(obj)) {
                productList.remove(obj);
                size--;
                i--;
            }
        }
        LOGGER.debug("loadProducts productList End size= {}", productList.size());
        productContainer.addAll(productList);
    }
    
    @Override
    protected void loadDeductions() {
        try{
        if (deductionContainer == null) {
            deductionContainer = new BeanItemContainer<Leveldto>(Leveldto.class);
        }
        
        List input = new ArrayList<>();
        input.add(session.getProdRelationshipBuilderSid());
        String sql = CommonQueryUtils.getAppQuery(input, "DEDUCTION_LEVEL_NO");
        List list = HelperTableLocalServiceUtil.executeSelectQuery(sql);
        Object[] res = (Object[])list.get(0);
        List deductionList = CommonLogic.getDeductionHierarchy(session.getProjectionId(), Integer.parseInt(res[0].toString()),session.getDeductionRelationVersion());
        LOGGER.debug("load Deductions productList Start size= {}", deductionList.size());
        int size = deductionList.size();
        for (int i = 0; i < size; i++) {
            Object obj = deductionList.get(i);
            if (isTreeitem(obj)) {
                deductionList.remove(obj);
                size--;
                i--;
            }
        }
        LOGGER.debug("load Deductions productList End size= {}", deductionList.size());
        deductionContainer.addAll(deductionList);
        }catch(IllegalArgumentException e){
        	LOGGER.error(e.getMessage());
        }
    }

    @Override
    protected String getCustomMasterData() {
        String str = "";
        CffCustomViewMaster customView = CommonLogic.getCustomView(customId);
        customId = 0;
        if (customView != null) {
            str = customView.getViewName();
            customId = customView.getCffCustomViewMasterSid();
        }
        return str;
    }

    @Override
    protected AbstractContainer getProductsContainer() {
        if (productContainer == null) {
            productContainer = new BeanItemContainer<Leveldto>(Leveldto.class);
        }
        return productContainer;
    }

    @Override
    protected AbstractContainer getCustomersContainer() {
        if (customerContainer == null) {
            customerContainer = new BeanItemContainer<Leveldto>(Leveldto.class);
        }
        return customerContainer;
    }
    
    @Override
    protected AbstractContainer getDeductionsContainer() {
        if (deductionContainer == null) {
            deductionContainer = new BeanItemContainer<Leveldto>(Leveldto.class);
        }
        return deductionContainer;
    }

    @Override
    protected AbstractContainer getCustomTreeContainer() {
        if (treecontainer == null) {
            treecontainer = new ExtTreeContainer<Leveldto>(Leveldto.class);
        }
        return treecontainer;
    }

    public int getCustomId() {
        return customId;
    }

    public boolean isIsSelect() {
        return isSelect;
    }

}
