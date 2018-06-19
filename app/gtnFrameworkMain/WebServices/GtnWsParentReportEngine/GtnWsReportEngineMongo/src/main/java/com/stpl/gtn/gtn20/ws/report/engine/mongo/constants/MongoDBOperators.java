package com.stpl.gtn.gtn20.ws.report.engine.mongo.constants;

public enum MongoDBOperators {

	IN("$in"), SUM("$sum"), GROUP("$group"), PROJECT("$project"), SORT("$sort"), UNWIND("$unwind"), MATCH(
			"$match"), SUBTRACT("$subtract"), DIVIDE(
					"$divide"), COND("$cond"), GT("$gt"), MULTIPLY("$multiply"), PUSH("$push"), MAX("$max");

	private String operators;

	private MongoDBOperators(String operators) {
		this.operators = operators;
	}

	public String getMongoOperators() {
		return operators;
	}

	@Override
	public String toString() {
		return operators;
	}

}
