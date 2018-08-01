package com.stpl.addons.vaadin.chartjs.config;

import com.stpl.addons.vaadin.chartjs.data.Data;
import com.stpl.addons.vaadin.chartjs.options.types.RadarChartOptions;
import com.stpl.addons.vaadin.chartjs.utils.JUtils;

import elemental.json.Json;
import elemental.json.JsonObject;

/**
 * A radar chart is a way of showing multiple data points and the variation between them.
 * 
 * They are often useful for comparing the points of two or more different data sets.
 *
 * @author michael@byteowls.com
 */
public class RadarChartConfig implements ChartConfig {

    private static final long serialVersionUID = -6327042538315582431L;

    private Data<RadarChartConfig> data;
    private RadarChartOptions options;

    public Data<RadarChartConfig> data() {
        if (this.data == null) {
            this.data = new Data<>(this);
        }
        return this.data;
    }

    public RadarChartOptions options() {
        if (options == null) {
            options = new RadarChartOptions(this);
        }
        return options;
    }

    @Override
    public JsonObject buildJson() {
        JsonObject map = Json.createObject();
        JUtils.putNotNull(map, "type", "radar");
        // data
        if (data != null) {
            JUtils.putNotNull(map, "data", data.buildJson());
        }
        // options
        if (options != null) {
            JUtils.putNotNull(map, "options", options.buildJson());
        }
        return map;
    }
}
