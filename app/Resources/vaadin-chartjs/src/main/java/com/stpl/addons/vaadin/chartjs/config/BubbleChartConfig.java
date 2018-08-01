package com.stpl.addons.vaadin.chartjs.config;

import com.stpl.addons.vaadin.chartjs.data.Data;
import com.stpl.addons.vaadin.chartjs.options.types.BubbleChartOptions;
import com.stpl.addons.vaadin.chartjs.utils.JUtils;

import elemental.json.Json;
import elemental.json.JsonObject;

/**
 * A bubble chart is used to display three dimensions of data at the same time. 
 *
 * @author michael@byteowls.com
 */
public class BubbleChartConfig implements ChartConfig {

    private static final long serialVersionUID = -5108239251413089851L;

    private String type = "bubble";
    private Data<BubbleChartConfig> data;
    private BubbleChartOptions options;

    public Data<BubbleChartConfig> data() {
        if (this.data == null) {
            this.data = new Data<>(this);
        }
        return this.data;
    }

    /**
     * Switch to horizonal bar chart
     * @return
     */
    public BubbleChartConfig horizontal() {
        type = "horizontalBar";
        return this;
    }

    public BubbleChartOptions options() {
        if (options == null) {
            options = new BubbleChartOptions(this);
        }
        return options;
    }

    @Override
    public JsonObject buildJson() {
        JsonObject map = Json.createObject();
        JUtils.putNotNull(map, "type", type);
        if (data != null) {
            JUtils.putNotNull(map, "data", data.buildJson());
        }
        if (options != null) {
            JUtils.putNotNull(map, "options", options.buildJson());
        }
        return map;
    }
}
