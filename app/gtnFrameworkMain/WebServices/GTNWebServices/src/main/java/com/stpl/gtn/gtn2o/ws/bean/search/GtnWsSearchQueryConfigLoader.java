/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.bean.search;

import java.util.Map;

import com.stpl.gtn.gtn2o.ws.config.GtnWsSearchQueryConfig;

/**
 *
 * @author Karthikeyan.Subraman
 */
public interface GtnWsSearchQueryConfigLoader {

	public Map<String, GtnWsSearchQueryConfig> getSearchQueryConfigMap();

}
