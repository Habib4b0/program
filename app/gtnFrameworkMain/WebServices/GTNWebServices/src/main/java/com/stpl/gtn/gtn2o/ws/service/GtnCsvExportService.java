package com.stpl.gtn.gtn2o.ws.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.GtnFileNameUtils;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsCsvExportBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnWsNumericConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsCsvExportRequest;
import com.stpl.gtn.gtn2o.ws.util.GtnCommonUtil;

@Service
@Scope("singleton")
public class GtnCsvExportService {

	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	@SuppressWarnings({ "unchecked" })
	public String getCsvExportFile(GtnUIFrameworkWebserviceRequest csvExportRequest)
			throws GtnFrameworkGeneralException {
		GtnWsCsvExportRequest csvRequest = csvExportRequest.getGtnWsCsvExportRequest();
		GtnWsCsvExportBean csvExportBean = csvRequest.getGtnWsCsvExportBean();
		String csvExportPath = createFilePath(csvExportBean.getExportName());
		String fileName = csvExportBean.getExportName().replaceAll(" ", "")
				+ GtnFrameworkCommonStringConstants.UNDERSCORE + csvExportRequest.getGtnWsGeneralRequest().getUserId()
				+ GtnFrameworkCommonStringConstants.UNDERSCORE
				+ csvExportRequest.getGtnWsGeneralRequest().getSessionId()
				+ GtnFrameworkCommonStringConstants.CSV_EXTENSION;

		File tempFile = GtnFileNameUtils.getFile(csvExportPath + fileName);
		try (FileWriter writer = new FileWriter(tempFile, true); PrintWriter printWriter = new PrintWriter(writer)) {
			GtnCommonUtil.createHeaderRow(printWriter, csvExportBean.getHeaderList());
			int count = gtnSqlQueryEngine.executeCountQuery(csvExportBean.getCountQuery());
			int offset = GtnWsNumericConstants.BATCH_COUNT;
			int start = 0;
			int loopCount = count / GtnWsNumericConstants.BATCH_COUNT + 1;
			GtnFrameworkDataType[] dataType = { GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.INTEGER };
			for (int i = 1; i <= loopCount; i++) {
				if (i == loopCount) {
					offset = count - start;
				}
				Object[] data = { start, offset };
				List<Object[]> resultList = (List<Object[]>) gtnSqlQueryEngine
						.executeSelectQuery(csvExportBean.getDataQuery(), data, dataType);
				GtnCommonUtil.createDataRows(printWriter, resultList);
				printWriter.flush();
				start = start + offset;
			}

		} catch (IOException exception) {
			throw new GtnFrameworkGeneralException("CSV Export Exception", exception);
		}

		return tempFile.getAbsolutePath();
	}

	private String createFilePath(String exportName) throws GtnFrameworkGeneralException {
		StringBuilder csvPath = new StringBuilder(System.getProperty(GtnFrameworkCommonStringConstants.GTN_BASE_PATH));
		csvPath.append("/");
		csvPath.append(GtnFrameworkCommonStringConstants.CSV_EXPORT_PATH);
		csvPath.append("/");
		csvPath.append(exportName);
		csvPath.append("/");
		Path path = Paths.get(csvPath.toString());
		if (!Files.exists(path)) {
			try {
				Files.createDirectories(path);
			} catch (IOException e) {
				throw new GtnFrameworkGeneralException(e);
			}
		}
		return csvPath.toString();
	}

	public GtnFrameworkSqlQueryEngine getGtnSqlQueryEngine() {
		return gtnSqlQueryEngine;
	}

	public void setGtnSqlQueryEngine(GtnFrameworkSqlQueryEngine gtnSqlQueryEngine) {
		this.gtnSqlQueryEngine = gtnSqlQueryEngine;
	}
}
