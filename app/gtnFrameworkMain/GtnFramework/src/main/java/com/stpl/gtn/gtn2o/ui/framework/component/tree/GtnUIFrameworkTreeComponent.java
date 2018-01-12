/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.framework.component.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import org.asi.container.ExtContainer;
import org.asi.container.ExtTreeContainer;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.data.Container;
import com.vaadin.event.DataBoundTransferable;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.event.dd.DragAndDropEvent;
import com.vaadin.event.dd.DropHandler;
import com.vaadin.event.dd.acceptcriteria.AcceptCriterion;
import com.vaadin.event.dd.acceptcriteria.Not;
import com.vaadin.event.dd.acceptcriteria.Or;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.AbstractSelect.VerticalLocationIs;
import com.vaadin.ui.Tree;
import com.vaadin.ui.Tree.CollapseListener;
import com.vaadin.ui.Tree.ExpandListener;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author Abhiram.Giri
 */
public class GtnUIFrameworkTreeComponent implements GtnUIFrameworkComponent {

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnUIFrameworkTreeComponent.class);

	@SuppressWarnings("rawtypes")
	@Override
	public AbstractComponent buildVaadinComponent(GtnUIFrameworkComponentConfig componentConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.info("Entering into the buildVaadinComponent() of GtnUIFrameworkTreeComponent");
		ExtTreeContainer<GtnWsRecordBean> container = new ExtTreeContainer<>(GtnWsRecordBean.class,
				ExtContainer.DataStructureMode.LIST);
		VerticalLayout resultLayout = new VerticalLayout();
		GtnUIFrameworkTreeConfig treeConfig = componentConfig.getGtnUIFrameworkTreeConfig();
		Tree tree = new Tree();
		tree.markAsDirty();
		tree.setImmediate(true);
		tree.setSelectable(treeConfig.isSelectable());
		tree.setMultiSelect(treeConfig.isMultiSelect());
		tree.setSizeFull();
		if (componentConfig.getComponentWidth() != null) {
			tree.setWidth(componentConfig.getComponentWidth());
		}
		if (componentConfig.getComponentHight() != null) {
			tree.setHeight(componentConfig.getComponentHight());
		}

		if (treeConfig.getItemCaptionPropertyId() != null) {
			Map<Object, Class> propertiesMap = new HashMap<>();
			propertiesMap.put(treeConfig.getItemCaptionPropertyId(), String.class);
			container.setColumnProperties(propertiesMap);
			Object value = treeConfig.getItemCaptionPropertyId();
			container.setRecordHeader(Arrays.asList(value));
			tree.setItemCaptionPropertyId(treeConfig.getItemCaptionPropertyId());
		}
		tree.setContainerDataSource(container);
		for (String style : componentConfig.getComponentStyle()) {
			tree.addStyleName(style);
		}
		resultLayout.setSizeFull();
		resultLayout.addComponent(tree);
		GtnUIFrameworkComponentData componentData = new GtnUIFrameworkComponentData();
		componentData.setCustomData(tree);
		resultLayout.setData(componentData);
		try {
			if (treeConfig.isDragMode()) {
				tree.setDragMode(Tree.TreeDragMode.NODE);
			}
			if (treeConfig.getItemClickActionConfigList() != null
					&& !treeConfig.getItemClickActionConfigList().isEmpty()) {
				tree.addItemClickListener(new TreeItemClickListener(componentConfig));
			}

			if (treeConfig.getExpandActionConfigList() != null && !treeConfig.getExpandActionConfigList().isEmpty()) {
				tree.addExpandListener(new TreeExpandListener(componentConfig));
			}
			if (treeConfig.getCollapseActionConfigList() != null
					&& !treeConfig.getCollapseActionConfigList().isEmpty()) {
				tree.addCollapseListener(new TreeCollapseListener(componentConfig));
			}
			if (treeConfig.getDropHandlerAction() != null) {
				tree.setDropHandler(new TreeDropHandler(componentConfig));

			}
		} catch (Exception ex) {
			gtnLogger.error(ex.getMessage(), ex);
		}
		gtnLogger.info("End into the buildVaadinComponent() of GtnUIFrameworkTreeComponent");
		return resultLayout;
	}

	@Override
	public void reloadComponent(GtnUIFrameworkActionType actionType, String dependentComponentId, String componentId,
			Object reloadInput) {
		return;

	}

	@Override
	public void resetToDefault(String componentId, GtnUIFrameworkComponentConfig componentConfig) {
		VerticalLayout resultLayout = (VerticalLayout) GtnUIFrameworkGlobalUI.getVaadinComponent(componentId);
		GtnUIFrameworkComponentData componentData = (GtnUIFrameworkComponentData) resultLayout.getData();
		Tree tree = (Tree) componentData.getCustomData();
		ExtTreeContainer<?> container = (ExtTreeContainer<?>) tree.getContainerDataSource();
		container.removeAllItems();
	}

	class TreeItemClickListener implements ItemClickListener {

		private static final long serialVersionUID = 1L;

		private GtnUIFrameworkComponentConfig componentConfig;

		public TreeItemClickListener(GtnUIFrameworkComponentConfig componentConfig) {
			this.componentConfig = componentConfig;
		}

		@Override
		public void itemClick(ItemClickEvent event) {
			try {
				for (GtnUIFrameWorkActionConfig actionConfig : componentConfig.getGtnUIFrameworkTreeConfig()
						.getItemClickActionConfigList()) {
					final GtnUIFrameWorkAction action = actionConfig.getActionType().getGtnUIFrameWorkAction();
					actionConfig.setEventParameter(event.getItemId());
					action.configureParams(actionConfig);
					action.doAction(componentConfig.getComponentId(), actionConfig);
				}

			} catch (GtnFrameworkGeneralException e) {
				gtnLogger.error(e.getMessage(), e);
			}
		}

	}

	class TreeDropHandler implements DropHandler {

		private static final long serialVersionUID = 1L;

		private GtnUIFrameworkComponentConfig componentConfig;

		public TreeDropHandler(GtnUIFrameworkComponentConfig componentConfig) {
			this.componentConfig = componentConfig;
		}

		@Override
		public void drop(DragAndDropEvent event) {
			try {
				DataBoundTransferable tran = (DataBoundTransferable) event.getTransferable();
				Tree.TreeTargetDetails target = (Tree.TreeTargetDetails) event.getTargetDetails();
				Object sourceItemId = tran.getData(GtnFrameworkCommonStringConstants.ITEM_ID);
				Object targetItemId = target.getItemIdOver();
				GtnUIFrameworkTreeConfig treeConfig = componentConfig.getGtnUIFrameworkTreeConfig();
				GtnUIFrameWorkAction action = treeConfig.getDropHandlerAction().getActionType()
						.getGtnUIFrameWorkAction();
				treeConfig.getDropHandlerAction().setEventParameter(Arrays.asList(sourceItemId, targetItemId));
				action.configureParams(treeConfig.getDropHandlerAction());
				action.doAction(componentConfig.getComponentId(), treeConfig.getDropHandlerAction());
			} catch (GtnFrameworkGeneralException e) {
				gtnLogger.error(e.getMessage(), e);
			}
		}

		@Override
		public AcceptCriterion getAcceptCriterion() {
			return new Or(Tree.TargetItemAllowsChildren.get(), new Not(VerticalLocationIs.MIDDLE));
		}

	}

	class TreeExpandListener implements ExpandListener {

		private static final long serialVersionUID = 1L;

		private GtnUIFrameworkComponentConfig componentConfig;

		public TreeExpandListener(GtnUIFrameworkComponentConfig componentConfig) {
			this.componentConfig = componentConfig;
		}

		@Override
		public void nodeExpand(Tree.ExpandEvent event) {
			try {
				for (GtnUIFrameWorkActionConfig actionConfig : componentConfig.getGtnUIFrameworkTreeConfig()
						.getExpandActionConfigList()) {
					final GtnUIFrameWorkAction action = actionConfig.getActionType().getGtnUIFrameWorkAction();
					actionConfig.setEventParameter(event.getItemId());
					action.configureParams(actionConfig);
					action.doAction(componentConfig.getComponentId(), actionConfig);
				}

			} catch (GtnFrameworkGeneralException e) {
				gtnLogger.error(e.getMessage(), e);
			}
		}

	}

	class TreeCollapseListener implements CollapseListener {

		private static final long serialVersionUID = 1L;

		private GtnUIFrameworkComponentConfig componentConfig;

		public TreeCollapseListener(GtnUIFrameworkComponentConfig componentConfig) {
			this.componentConfig = componentConfig;
		}

		@Override
		public void nodeCollapse(Tree.CollapseEvent event) {
			try {
				for (GtnUIFrameWorkActionConfig actionConfig : componentConfig.getGtnUIFrameworkTreeConfig()
						.getCollapseActionConfigList()) {
					final GtnUIFrameWorkAction action = actionConfig.getActionType().getGtnUIFrameWorkAction();
					actionConfig.setEventParameter(event.getItemId());
					action.configureParams(actionConfig);
					action.doAction(componentConfig.getComponentId(), actionConfig);
				}

			} catch (GtnFrameworkGeneralException e) {
				gtnLogger.error(e.getMessage(), e);
			}
		}

	}

	@SuppressWarnings("unchecked")
	public List<GtnWsRecordBean> removeSelectedTreeItems(Tree tree, String initialTableId, boolean hasMultipleTable)
			throws GtnFrameworkValidationFailedException {
		List<GtnWsRecordBean> returnList = null;
		Object selectedValue = tree.getValue();
		if (selectedValue == null) {
			return returnList;
		}
		List<GtnWsRecordBean> selectedItemList = new ArrayList<>();
		if (selectedValue instanceof Collection) {
			selectedItemList = new ArrayList<>((Set<GtnWsRecordBean>) selectedValue);
		} else {
			selectedItemList.add((GtnWsRecordBean) selectedValue);
		}
		if (selectedItemList.isEmpty()) {
			return returnList;
		}

		Collections.sort(selectedItemList, new Comparator<GtnWsRecordBean>() {
			@Override
			public int compare(GtnWsRecordBean o1, GtnWsRecordBean o2) {
				int treeLevelNo1 = Integer.parseInt(String.valueOf(o1.getAdditionalPropertyByIndex(0)));
				int treeLevelNo2 = Integer.parseInt(String.valueOf(o2.getAdditionalPropertyByIndex(0)));
				return treeLevelNo2 - treeLevelNo1;
			}
		});

		ListIterator<?> listIterator = selectedItemList.listIterator();

		while (listIterator.hasNext()) {
			Object item = listIterator.next();
			if (!tree.hasChildren(item)) {
				tree.getContainerDataSource().removeItem(item);
				String treeLevelNo = hasMultipleTable
						? String.valueOf(((GtnWsRecordBean) item).getAdditionalPropertyByIndex(0)) : "";
				GtnUIFrameworkBaseComponent tableBaseComponent = GtnUIFrameworkGlobalUI
						.getVaadinBaseComponent(initialTableId + treeLevelNo);
				tableBaseComponent.addItemToDataTable(item);
				tableBaseComponent.setTableValue(null);
				listIterator.remove();
			}
		}
		returnList = new ArrayList<>(selectedItemList);
		return returnList;
	}

	@SuppressWarnings("unchecked")
	public List<GtnWsRecordBean> removeParentAndChildTreeItems(Tree tree, String initialTableId,
			boolean hasMultipleTable) throws GtnFrameworkValidationFailedException {
		List<GtnWsRecordBean> returnListToRemove = null;
		Object selectedValueInTree = tree.getValue();

		if (selectedValueInTree == null) {
			return returnListToRemove;
		}
		List<GtnWsRecordBean> selectedItemListFromTree = new ArrayList<>();
		if (selectedValueInTree instanceof Collection) {
			selectedItemListFromTree = new ArrayList<>((Set<GtnWsRecordBean>) selectedValueInTree);
		} else {
			selectedItemListFromTree.add((GtnWsRecordBean) selectedValueInTree);
		}
		if (selectedItemListFromTree.isEmpty()) {
			return returnListToRemove;
		}

		Collections.sort(selectedItemListFromTree, new Comparator<GtnWsRecordBean>() {
			@Override
			public int compare(GtnWsRecordBean object1, GtnWsRecordBean object2) {
				int treeLevelNo1InTree = Integer.parseInt(String.valueOf(object1.getAdditionalPropertyByIndex(0)));
				int treeLevelNo2InTree = Integer.parseInt(String.valueOf(object2.getAdditionalPropertyByIndex(0)));
				return treeLevelNo2InTree - treeLevelNo1InTree;
			}
		});

		ListIterator<?> listIteratorForTree = selectedItemListFromTree.listIterator();
		while (listIteratorForTree.hasNext()) {
			Object itemToRemove = listIteratorForTree.next();
			Object selectedParent = itemToRemove;
			if (!tree.hasChildren(itemToRemove)) {
				tree.getContainerDataSource().removeItem(itemToRemove);
			} else {
				getChildren(itemToRemove, tree, selectedParent);
			}
			String treeLevelNoInTree = hasMultipleTable
					? String.valueOf(((GtnWsRecordBean) itemToRemove).getAdditionalPropertyByIndex(0)) : "";
			GtnUIFrameworkBaseComponent tableBaseComponent = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(initialTableId + treeLevelNoInTree);
			tableBaseComponent.addItemToDataTable(itemToRemove);
			tableBaseComponent.setTableValue(null);
			listIteratorForTree.remove();

		}

		returnListToRemove = new ArrayList<>(selectedItemListFromTree);

		return returnListToRemove;

	}

	private void getChildren(Object item, Tree tree, Object selectedParent) {
		if (tree.hasChildren(item)) {
			getChildren(tree.getChildren(item).iterator().next(), tree, selectedParent);
		} else {
			Object parent = tree.getParent(item);
			tree.getContainerDataSource().removeItem(item);
			if (!item.equals(selectedParent)) {
				getChildren(parent, tree, selectedParent);
			}
		}
	}

	public List<GtnWsRecordBean> getTreeNodes(AbstractSelect tree) {
		Container.Hierarchical container = (Container.Hierarchical) tree.getContainerDataSource();
		return getTreeNodesFromCollection(container.rootItemIds(), container);
	}

	public List<GtnWsRecordBean> getAllTreeNodes(AbstractSelect tree) {
		List<GtnWsRecordBean> treeNodes = new ArrayList<>();
		Container.Hierarchical container = (Container.Hierarchical) tree.getContainerDataSource();
		Collection<?> items = container.getItemIds();
		Iterator<?> itr = items.iterator();
		while (itr.hasNext()) {
			Object item = itr.next();
			GtnWsRecordBean dto = ((GtnWsRecordBean) item);
			GtnWsRecordBean node = new GtnWsRecordBean();
			node.setProperties(dto.getProperties());
			node.setAdditionalProperties(dto.getAdditionalProperties());
			node.setRecordHeader(dto.getRecordHeader());
			treeNodes.add(node);
		}
		return treeNodes;
	}

	private List<GtnWsRecordBean> getTreeNodesFromCollection(Collection<?> items, Container.Hierarchical container) {
		List<GtnWsRecordBean> nodelist = new ArrayList<>();
		Iterator<?> itr = items.iterator();
		while (itr.hasNext()) {
			Object item = itr.next();
			GtnWsRecordBean dto = ((GtnWsRecordBean) item);
			GtnWsRecordBean node = new GtnWsRecordBean();
			node.setProperties(dto.getProperties());
			node.setAdditionalProperties(dto.getAdditionalProperties());
			node.setRecordHeader(dto.getRecordHeader());
			if (container.hasChildren(item)) {
				node.setChildList(getTreeNodesFromCollection(container.getChildren(item), container));
			}
			nodelist.add(node);
		}

		return nodelist;
	}

	public void loadTreeFromTreeNode(Tree tree, List<GtnWsRecordBean> nodes, GtnWsRecordBean parentNode) {
		Container.Hierarchical container = (Container.Hierarchical) tree.getContainerDataSource();
		loadTreeFromTreeNode(nodes, parentNode, container);
		Collection<?> roots = container.rootItemIds();
		Iterator<?> itr = roots.iterator();
		while (itr.hasNext()) {
			tree.expandItemsRecursively(itr.next());
		}
	}

	private void loadTreeFromTreeNode(List<GtnWsRecordBean> nodes, GtnWsRecordBean parentDto,
			Container.Hierarchical container) {
		if (nodes != null && !nodes.isEmpty()) {
			container.setChildrenAllowed(parentDto, true);
			Iterator<?> itr = nodes.iterator();
			while (itr.hasNext()) {
				Object item = itr.next();
				GtnWsRecordBean node = ((GtnWsRecordBean) item);
				GtnWsRecordBean dto = new GtnWsRecordBean();
				dto.setProperties(node.getProperties());
				dto.setAdditionalProperties(node.getAdditionalProperties());
				dto.setRecordHeader(node.getRecordHeader());
				container.addItem(dto);
				container.setParent(dto, parentDto);
				container.setChildrenAllowed(dto, false);
				loadTreeFromTreeNode(node.getChildList(), dto, container);
			}
		}
	}

	public List<GtnWsRecordBean> getChildNodes(AbstractSelect tree, GtnWsRecordBean parent) {
		List<GtnWsRecordBean> treeNodes = new ArrayList<>();
		Container.Hierarchical container = (Container.Hierarchical) tree.getContainerDataSource();
		if (container.hasChildren(parent)) {
			Iterator<?> itr = container.getChildren(parent).iterator();
			while (itr.hasNext()) {
				Object item = itr.next();
				GtnWsRecordBean dto = ((GtnWsRecordBean) item);
				GtnWsRecordBean node = new GtnWsRecordBean();
				node.setProperties(dto.getProperties());
				node.setAdditionalProperties(dto.getAdditionalProperties());
				node.setRecordHeader(dto.getRecordHeader());
				treeNodes.add(node);
			}
		}
		return treeNodes;
	}

	public List<GtnWsRecordBean> getRootNodes(AbstractSelect tree) {
		List<GtnWsRecordBean> treeNodes = new ArrayList<>();
		Container.Hierarchical container = (Container.Hierarchical) tree.getContainerDataSource();
		Iterator<?> itr = container.rootItemIds().iterator();
		while (itr.hasNext()) {
			Object item = itr.next();
			GtnWsRecordBean dto = ((GtnWsRecordBean) item);
			GtnWsRecordBean node = new GtnWsRecordBean();
			node.setProperties(dto.getProperties());
			node.setAdditionalProperties(dto.getAdditionalProperties());
			node.setRecordHeader(dto.getRecordHeader());
			treeNodes.add(node);
		}
		return treeNodes;
	}

	public void addItemToTreeDataTable(AbstractSelect tree, Object parentItemId, Object itemId, boolean childrenAllowed)
			throws GtnFrameworkValidationFailedException {
		Container.Hierarchical container = (Container.Hierarchical) tree.getContainerDataSource();
		container.addItem(itemId);
		container.setChildrenAllowed(itemId, childrenAllowed);
		container.setParent(itemId, parentItemId);
	}

	public void addItemsToTreeDataTable(AbstractSelect tree, Object parentItemId, Collection<?> items,
			boolean childrenAllowed) {
		Container.Hierarchical container = (Container.Hierarchical) tree.getContainerDataSource();
		for (Object extListDTO : items) {
			container.addItem(extListDTO);
			container.setChildrenAllowed(extListDTO, childrenAllowed);
			if (parentItemId != null) {
				container.setChildrenAllowed(parentItemId, true);
				container.setParent(extListDTO, parentItemId);
			}
		}
	}
}
