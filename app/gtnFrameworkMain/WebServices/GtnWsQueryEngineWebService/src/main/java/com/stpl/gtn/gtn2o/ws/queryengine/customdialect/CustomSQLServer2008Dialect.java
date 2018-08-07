package CustomSQLServer2008Dialect;

import org.hibernate.dialect.SQLServer2008Dialect;
import org.hibernate.dialect.function.StandardSQLFunction;

public class CustomSQLServer2008Dialect extends SQLServer2008Dialect{
	
	public CustomSQLServer2008Dialect() {
		super();
		registerFunction("replace", new StandardSQLFunction("replace"));
	}
}
