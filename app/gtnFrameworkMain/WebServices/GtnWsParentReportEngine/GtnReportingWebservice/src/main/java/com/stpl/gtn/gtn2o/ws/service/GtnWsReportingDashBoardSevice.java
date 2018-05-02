/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.gtn.gtn2o.ws.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn20.ws.report.engine.mongo.service.GtnWsMongoService;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.bean.GtnWsReportEngineTreeNode;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;
/**
 *
 * @author Karthik.Raja
 */
@Service
public class GtnWsReportingDashBoardSevice {
    
    
    GtnWsMongoService MONGO_SERVICE=GtnWsMongoService.getInstance();
//    public static void main(String[] args) {
////        new GtnWsReportingDashBoardSevice().getDashboardLeftData();
//    }
    public List<GtnWsRecordBean> getDashboardLeftData(  GtnWsSearchRequest gtnWsSearchRequest) {
        
        String inputs[] =(String[]) gtnWsSearchRequest.getQueryInput() .toArray();
        Object values[] = gtnWsSearchRequest.getQueryInputList().toArray();
        GtnWsReportEngineTreeNode treeNode = (GtnWsReportEngineTreeNode) MONGO_SERVICE.getTreeFromMongo("Tree", GtnWsReportEngineTreeNode.class, inputs, values);
        List<GtnWsRecordBean> data=new ArrayList<>();
        displayNodeValues(treeNode,data,gtnWsSearchRequest.getRecordHeader());
        return data;
    }

    public void displayNodeValues(GtnWsReportEngineTreeNode ccpNode,List<GtnWsRecordBean> data,List<Object> recordHeader) {
        
        for (GtnWsReportEngineTreeNode gtnWsTreeNode : ccpNode.getChildren()) {
            System.out.println(gtnWsTreeNode.toString());
            data.add(convertBean(gtnWsTreeNode,recordHeader));
            if (gtnWsTreeNode.getChildren() != null) {
                displayNodeValues(gtnWsTreeNode,data,recordHeader);
            }
        }
    }
    GtnWsRecordBean convertBean(GtnWsReportEngineTreeNode treeNode,List<Object> recordHeader){
        
        GtnWsRecordBean bean=new GtnWsRecordBean();
        bean.setRecordHeader(recordHeader);
        bean.addProperties("levelNumber", treeNode.getLevelNumber());
        bean.addProperties("hierarchyNo",treeNode.getHierarchyNo() );
        bean.addProperties("levelName", treeNode.getLevelName());
        bean.addProperties("levelValue", treeNode.getLevelValue());
        return bean;
    }
}
