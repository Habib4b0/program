/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.components.duallistbox.GtnWsDualListBoxFilterManager;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecast.service.GtnWsReturnsFileIOService;
import com.stpl.gtn.gtn2o.ws.forecast.service.duallistbox.GtnWsDualListBoxSerializingService;
import com.stpl.gtn.gtn2o.ws.forecast.service.tree.GtnWsTreeNode;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

/**
 *
 * @author Nimisha.Rakesh
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationServiceContext-ReturnsForecasting.xml" })
@Ignore
public class GtnWsReturnsFleIOControllerTest {

	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnWsReturnsFleIOControllerTest.class);

	@Autowired
	GtnWsReturnsFileIOService gtnWsReturnsFileIOService;

	@Autowired
	private GtnWsDualListBoxSerializingService serializingService;

	// path of .ser file
	private final String filePath = "D:\\gtn_data\\temp\\returns\\Apr_06_2017\\111111\\Data_Selection\\product_selection116.ser";

	// Need to give json file path here
	private final String jsonFilePath = "D:\\gtn_data\\temp\\returns\\Apr_05_2017\\111111\\RETURNS_FORECAST_ACTUAL.stpl";

	// path of TreeNode file
	private final String treefilePath = "D:\\gtn_data\\temp\\returns\\Apr_06_2017\\111111\\Data_Selection\\product_Tree.stpl";

	@Ignore
	public void testDataSelectionFile() throws IOException, GtnFrameworkGeneralException {

		readSerializedFile(filePath);

		readJsonDataFromFile(jsonFilePath);

		readDataFromTreeNodeFile(treefilePath);

	}

	@SuppressWarnings("unchecked")
	public void readSerializedFile(String filepath) {
		LOGGER.info("Value in Product Selection File---");
		try {
			GtnWsDualListBoxDataFilterTest filter = new GtnWsDualListBoxDataFilterTest();
			GtnWsDualListBoxFilterManager.setFilter(filter);
			Set<GtnWsRecordBean> resultSet = (HashSet<GtnWsRecordBean>) serializingService.deserialize(filepath);
			for (GtnWsRecordBean gtnWsDualListBoxRawData : resultSet) {
				System.out.println(Arrays.toString(gtnWsDualListBoxRawData.getRawObjectArray()));
			}

		} catch (IOException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}

	}

	public void readJsonDataFromFile(final String path) throws GtnFrameworkGeneralException {
		LOGGER.info("Value in Json File---");
		List<List<Object>> list = gtnWsReturnsFileIOService.readJsonDataFromFile(path);

		for (List<Object> resultList : list) {
			System.out.println("resultList = " + resultList);

		}
	}

	public void readDataFromTreeNodeFile(String fileName) throws GtnFrameworkGeneralException {
		LOGGER.info("Value in Product Tree File--");
		GtnWsTreeNode rootNode = gtnWsReturnsFileIOService.readDataFromFile(fileName, GtnWsTreeNode.class);
		printTree(rootNode);
	}

	private void printTree(GtnWsTreeNode rootNode) {
		for (int i = 0; i < rootNode.getLevelNumber(); i++) {
			System.out.print("\t");
		}
		System.out.print(rootNode);
		System.out.print("\n");
		if (rootNode.getChildren() != null) {
			for (GtnWsTreeNode treeNode : rootNode.getChildren()) {
				printTree(treeNode);
			}
		}
	}
}
