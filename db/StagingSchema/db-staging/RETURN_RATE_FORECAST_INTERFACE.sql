----------------------------------RETURN_RATE_FORECAST_INTERFACE------------------------------------------
 
 IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'RETURN_RATE_FORECAST_INTERFACE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[RETURN_RATE_FORECAST_INTERFACE]
        (                     
          [RETURN_RATE_FORECAST_INTERFACE_ID]         NUMERIC(38, 0) NOT NULL,             
           [ITEM_ID]                     VARCHAR(38) NOT NULL,
           [ITEM_NO]                     VARCHAR(50) NULL,
           [ITEM_NAME]                   VARCHAR(100) NULL,
           [FORECAST_YEAR]               VARCHAR(5) NULL,
           [FORECAST_MONTH]              VARCHAR(25) NULL,
           [RATE]                        NUMERIC(22, 6) NULL,
           [FORECAST_NAME]               VARCHAR(100) NULL,
           [FORECAST_VER]                VARCHAR(15) NULL,
           [CREATED_BY]                  VARCHAR(50) NULL,
           [CREATED_DATE]                DATETIME NULL,
           [MODIFIED_BY]                 VARCHAR(50) NULL,
           [MODIFIED_DATE]               DATETIME NULL,
           [ADD_CHG_DEL_INDICATOR]       VARCHAR(10) NOT NULL,
           [BATCH_ID]                    VARCHAR(38) NOT NULL,
           [SOURCE]                      VARCHAR(50) NULL,
     )
  END

 GO