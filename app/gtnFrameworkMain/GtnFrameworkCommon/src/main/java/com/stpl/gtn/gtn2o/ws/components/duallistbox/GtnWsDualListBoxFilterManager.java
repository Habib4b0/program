package com.stpl.gtn.gtn2o.ws.components.duallistbox;

public class GtnWsDualListBoxFilterManager {
    private GtnWsDualListBoxFilterManager(){
        /**
         * empty constructor
         */
    }

	private static final ThreadLocal<GtnWsDualListBoxFilter> filter = new ThreadLocal<>();

	public static GtnWsDualListBoxFilter getFilter() {
		return filter.get();
	}

	public static void setFilter(GtnWsDualListBoxFilter filter) {
		GtnWsDualListBoxFilterManager.filter.set(filter);
	}
}
