/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.generalsearch;

import java.util.List;
import java.util.Map;

/**
 *
 * @author anandh.karuppusamy
 */
public class GtnGeneralSearchBean {
    private Map<String,List<String>> generalMap;
    private String key;
    private List<String> list;
    private String Object[];
    private String query;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String[] getObject() {
        return Object;
    }

    public void setObject(String[] Object) {
        this.Object = Object;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
    public Map<String, List<String>> getGeneralMap() {
        return generalMap;
    }

    public void setGeneralMap(Map<String, List<String>> generalMap) {
        this.generalMap = generalMap;
    }
    
}
