/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.framework.component.grid.utils;

import com.stpl.gtn.gtn2o.ui.framework.component.grid.component.PagedTreeGrid;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtreetable.GtnUIFrameworkPagedTreeTableConfig;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Karthik.Raja
 */
/**
 * *
 *
 * Instance creation not allowed ,Contains Only Static Methods
 */
final public class GridUtils {

    private static final GtnWSLogger gtnlogger = GtnWSLogger.getGTNLogger(GridUtils.class);

    private GridUtils() {
        throw new RuntimeException("Can not create object for this class " + GridUtils.class.getName());
    }

    public static int getLevelIndex(GtnWsRecordBean row) {
        return row==null?0:getInt(row.getAdditionalPropertyByIndex(4));
    }

    public static int getChildCount(GtnWsRecordBean row) {
        return row==null?0:getInt(row.getAdditionalPropertyByIndex(0));
    }

    public static int getTableIndex(GtnWsRecordBean row) {
        return  row==null?0:getInt(row.getAdditionalPropertyByIndex(5));
    }
    


    public static int getLevelNo(GtnWsRecordBean row) {
        return row==null?0:getInt(row.getAdditionalPropertyByIndex(1));
    }

    public static int getNodeIndex(GtnWsRecordBean row) {
        return row==null?0:getInt(row.getAdditionalPropertyByIndex(3));
    }

    public static String getHierarchyNo(GtnWsRecordBean row) {
        return String.valueOf(row.getAdditionalPropertyByIndex(2));
    }
   public static boolean hasChildren(GtnWsRecordBean item) {
        return getChildCount(item) > 0;
    }
    public static int getInt(Object value) {
        int i = 0;
        try {
            i = Integer.parseInt(String.valueOf(value));
        } catch (NumberFormatException e) {
            gtnlogger.error(e.getMessage());
        }
        return i;
    }

    public static GtnWsRecordBean getEmptyRow(PagedTreeGrid pagedTreeGrid) {
        GtnWsRecordBean emptyRow = new GtnWsRecordBean();
        emptyRow.setRecordHeader(pagedTreeGrid.getTableConfig().getVisibleColumns());
        emptyRow.setProperties(pagedTreeGrid.getTableConfig().getVisibleColumns());
        emptyRow.setAdditionalProperties(Arrays.asList(0, 0, "", 0, 0, 0,0));
        return emptyRow;
    }

    public static GtnWsSearchRequest getWsRequest(int start, int offset, boolean isCount, List<String> input, List<Object> inputValues, GtnUIFrameworkPagedTreeTableConfig tableConfig) {
        GtnWsSearchRequest request = new GtnWsSearchRequest();
        request.setTableRecordOffset(offset);
        request.setTableRecordStart(start);
        request.setCount(isCount);
        request.setQueryInput(input);
        request.setQueryInputList(inputValues);
        request.setRecordHeader(tableConfig.getVisibleColumns());

        return request;
    }
}
