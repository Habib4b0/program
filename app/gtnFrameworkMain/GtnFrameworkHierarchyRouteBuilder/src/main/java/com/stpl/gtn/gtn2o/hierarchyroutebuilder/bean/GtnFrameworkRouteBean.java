package com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GtnFrameworkRouteBean {

	private int routeFrom;
	private int routeTo;
	private List<Integer> pathList = new ArrayList<>();
	private int hops;

	public GtnFrameworkRouteBean() {
		super();
	}
	public List<Integer> getPathList() {
		return Collections.unmodifiableList(pathList);
	}

	public void addPathListValue(Integer pathValue) {

		for (Integer current : pathList) {
			if (current == pathValue) {
				return;
			}
		}
		pathList.add(pathValue);
	}

	public void removePathListLastValue() {
		if (!pathList.isEmpty())
			pathList.remove(pathList.size() - 1);
	}

	public int getRouteFrom() {
		return routeFrom;
	}

	public void setRouteFrom(int routeFrom) {
		this.routeFrom = routeFrom;
	}

	public int getRouteTo() {
		return routeTo;
	}

	public void setRouteTo(int routeTo) {
		this.routeTo = routeTo;
	}


	public void setHops(int hops) {
		this.hops = hops;
	}

	public int getHops() {
		return hops;
	}

	public void increaseHop() {
		hops++;
	}

	public void clearPathListBean() {
		pathList.clear();

	}

}
