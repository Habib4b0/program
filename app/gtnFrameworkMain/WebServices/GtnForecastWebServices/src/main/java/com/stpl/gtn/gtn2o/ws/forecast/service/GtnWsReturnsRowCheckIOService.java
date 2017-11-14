package com.stpl.gtn.gtn2o.ws.forecast.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastBean;
import com.stpl.gtn.gtn2o.ws.forecast.service.tree.GtnWsTreeNode;

/**
 *
 * @author Stpl
 */
@Service
@Scope("singleton")
public class GtnWsReturnsRowCheckIOService {

	@Autowired
	private GtnWsReturnsFileIOService fileService;

	public GtnWsReturnsFileIOService getFileService() {
		return fileService;
	}

	public void setFileService(GtnWsReturnsFileIOService fileService) {
		this.fileService = fileService;
	}

	public boolean getCheckedNodeForMethodologyValidation(GtnWsTreeNode rootNode, GtnForecastBean gtnForecastBean)
			throws GtnFrameworkGeneralException {

		GtnWsTreeNode checkedTreeNode = checkIsCheckedNodeForMethodologyValidation(rootNode, gtnForecastBean);

		if (checkedTreeNode != null) {
			if (checkedTreeNode.isCheckedNode()) {
				gtnForecastBean.setCheckedLeftTreeTable(true);
				return true;
			}
		}
		gtnForecastBean.setCheckedLeftTreeTable(false);
		return false;
	}

	private GtnWsTreeNode checkIsCheckedNodeForMethodologyValidation(GtnWsTreeNode rootNode,
			GtnForecastBean gtnForecastBean) {

		if (rootNode != null && rootNode.getChildren() != null) {

			List<GtnWsTreeNode> currentRootNode = rootNode.getChildren();

			for (GtnWsTreeNode currentGtnWsTreeNode : currentRootNode) {

				if (currentGtnWsTreeNode.isCheckedNode()) {
					return currentGtnWsTreeNode;
				}

				GtnWsTreeNode childNode = checkIsCheckedNodeForMethodologyValidation(currentGtnWsTreeNode,
						gtnForecastBean);

				if (childNode != null) {
					return childNode;
				}
			}
		}
		return null;
	}

	public boolean updateTreeNode(GtnWsTreeNode rootNode, GtnForecastBean gtnForecastBean, String finalFileName)
			throws GtnFrameworkGeneralException {
		GtnWsTreeNode checkedTreeNode = getCheckedNode(rootNode, gtnForecastBean.getSelectedHierarchyNo());
		updateCheckRecordInFile(checkedTreeNode, gtnForecastBean);
		updateCheckRecordInParentNode(checkedTreeNode);
		fileService.writeDataIntoFile(finalFileName, rootNode);
		return rootNode.isCheckedNode();
	}

	public GtnWsTreeNode getCheckedNode(final GtnWsTreeNode rootNode, final String hierarchyNumber) {
		if (rootNode != null && rootNode.getHierarchyNo().equals(hierarchyNumber)) {
			return rootNode;
		}
		if (rootNode != null && rootNode.getChildren() != null) {
			for (GtnWsTreeNode treeNode : rootNode.getChildren()) {
				GtnWsTreeNode gtnWsTreeNode = getCheckedNode(treeNode, hierarchyNumber);
				if (gtnWsTreeNode != null) {
					if (gtnWsTreeNode.getHierarchyNo().equals(hierarchyNumber)) {
						return gtnWsTreeNode;
					}
				}

			}
		}
		return null;
	}

	public void updateCheckRecordInFile(final GtnWsTreeNode rootNode, GtnForecastBean gtnForecastBean)
			throws GtnFrameworkGeneralException {

		rootNode.setCheckedNode(Boolean.parseBoolean(gtnForecastBean.getCheckValue()));
		if (rootNode.getChildren() != null) {
			for (GtnWsTreeNode treeNode : rootNode.getChildren()) {
				updateCheckRecordInFile(treeNode, gtnForecastBean);
			}
		}
	}

	private void updateCheckRecordInParentNode(final GtnWsTreeNode checkedNode) {
		GtnWsTreeNode parentNode = checkedNode.getParent();
		if (parentNode != null) {
			parentNode.setCheckedNode(checkForCheckedChildRecords(parentNode));
			updateCheckRecordInParentNode(parentNode);
		}
	}

	private boolean checkForCheckedChildRecords(GtnWsTreeNode parentNode) {
		if (parentNode.getChildren() != null) {
			for (GtnWsTreeNode childNode : parentNode.getChildren()) {
				if (!childNode.isCheckedNode()) {
					return childNode.isCheckedNode();
				}
			}
			return true;
		}
		return false;
	}

	public boolean updateAllRecords(GtnWsTreeNode rootNode, GtnForecastBean gtnForecastBean, String finalFileName)
			throws GtnFrameworkGeneralException {
		updateCheckRecordInFile(rootNode, gtnForecastBean);
		fileService.writeDataIntoFile(finalFileName, rootNode);
		return rootNode.isCheckedNode();
	}
}
