--For concept of taking the data backup for full load concept we were using [TABLE_NAME_2017_SEMI_ANNUAL_1/2].
--As as now we have moved the data backup concept to HISTORY_TABLE.
--So we are removeing the un used  [TABLE_NAME_2017_SEMI_ANNUAL_1/2] tables.
DECLARE @SQL             NVARCHAR(MAX),
        @TABLE_CONDITION VARCHAR(100) = 'SEMI_ANNUAL',
		@COUNT           INT
		

BEGIN
IF EXISTS (SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME LIKE  '%'+@TABLE_CONDITION+'%')
BEGIN
SET @SQL = (SELECT N'DROP TABLE '  + QUOTENAME(T.TABLE_NAME) + ' ; 'FROM INFORMATION_SCHEMA.TABLES AS T  WHERE T.TABLE_NAME LIKE  '%'+@TABLE_CONDITION+'%' FOR XML PATH(''))
EXEC (@SQL)
END
END
GO


