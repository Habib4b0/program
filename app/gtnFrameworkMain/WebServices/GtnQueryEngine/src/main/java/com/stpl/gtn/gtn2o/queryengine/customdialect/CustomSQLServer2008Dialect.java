package com.stpl.gtn.gtn2o.queryengine.customdialect;

import org.hibernate.dialect.SQLServer2008Dialect;
import org.hibernate.dialect.function.StandardSQLFunction;

/**
 * @author Nimisha.Rakesh
 *
 */

/**
 * 
 * Using this class we can add any sql function we want to use in hql Ex:
 * replace
 *
 */
public class CustomSQLServer2008Dialect extends SQLServer2008Dialect {

	public CustomSQLServer2008Dialect() {
		super();
		registerFunction("replace", new StandardSQLFunction("replace"));
	}
}
