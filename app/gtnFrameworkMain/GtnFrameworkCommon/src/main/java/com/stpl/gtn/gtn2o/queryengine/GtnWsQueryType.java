package com.stpl.gtn.gtn2o.queryengine;

public enum GtnWsQueryType {
	SELECT("select"), COUNT("count");
	
	private final String type;
	
	private GtnWsQueryType(String type){
		this.type = type;
	}

	@Override
	public String toString() {
		return type;
	}
	
	public static GtnWsQueryType fromString(String type) {
		for (GtnWsQueryType queryType : values()) {
			if (queryType.type.equals(type)) {
				return queryType;
			}
		}
		throw new IllegalArgumentException("No type found for " + type);
	}
}
