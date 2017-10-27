IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'UDF_MUL')
  BEGIN
      DROP FUNCTION [DBO].[UDF_MUL]
  END

GO

CREATE FUNCTION [DBO].[UDF_MUL](@VALUE XML)
RETURNS NUMERIC(38, 15)
WITH SCHEMABINDING
AS
  BEGIN
      DECLARE @mul_value FLOAT
      DECLARE @values AS TABLE
        (
           id      INT PRIMARY KEY IDENTITY(1, 1),
           [value] FLOAT
        );

      INSERT INTO @values
                  ([value])
      SELECT t.c.value('.', 'FLOAT') AS [value]
      FROM   @value.nodes('//ROOT/row') AS t(c);

  ;
      WITH RC
           AS (SELECT ID,
                      [value],
                      [value] AS MUL
               FROM   @values
               WHERE  ID = 1
               UNION ALL
               SELECT C.ID,
                      C.[value],
                      Cast(c.[value] * R.MUL AS FLOAT) AS MUL
               FROM   RC R
                      INNER JOIN @values C
                              ON C.ID = R.ID + 1)
      --select * from rc
      SELECT TOP 1 @mul_value = MUL
      FROM   RC
      ORDER  BY id DESC
      OPTION (maxrecursion 0)

      RETURN @mul_value
  END 
  GO