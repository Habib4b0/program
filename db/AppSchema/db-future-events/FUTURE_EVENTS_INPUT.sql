IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_SCHEMA = 'dbo'
                      AND TABLE_NAME = 'FUTURE_EVENTS_INPUT')
  CREATE TABLE FUTURE_EVENTS_INPUT
    (
       TEMP_FUTURE_EVENTS_SID   INT IDENTITY (1, 1) NOT NULL,
       FROM_PROJECTION          INT NOT NULL,
       COPY_FROM_PROJECTION     INT NOT NULL,
       TO_PROJECTION            INT NOT NULL,
       COPY_TO_PROJECTION       INT NOT NULL,
       FROM_CONTRACT_MASTER_SID INT NOT NULL,
       TO_CONTRACT_MASTER_SID   INT NOT NULL,
       COMPANY_MASTER_SID       VARCHAR(4000) NULL,
       ITEM_MASTER_SID          VARCHAR(4000) NULL,
       FROM_END_DATE            DATETIME NULL,
       TO_START_DATE            DATETIME NULL,
       TRANSFER_FLAG            BIT NOT NULL,
       SESSION_ID               INT NOT NULL
    )

--Default Constraints
IF NOT EXISTS (SELECT 1
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  Object_name(PARENT_OBJECT_ID) = 'FUTURE_EVENTS_INPUT'
                      AND SCHEMA_NAME(SCHEMA_ID) = 'dbo'
                      AND NAME = 'DF_FUTURE_EVENTS_INPUT_TRANSFER_FLAG')
  BEGIN
      ALTER TABLE FUTURE_EVENTS_INPUT
        ADD CONSTRAINT DF_FUTURE_EVENTS_INPUT_TRANSFER_FLAG DEFAULT (0) FOR TRANSFER_FLAG
  END

GO 


IF NOT EXISTS (SELECT 1
           FROM   information_schema.columns
           WHERE  table_name = 'FUTURE_EVENTS_INPUT'
                  AND column_name = 'TRANSFER_SALES_FLAG'
                  AND table_schema = 'DBO')
  BEGIN


ALTER TABLE FUTURE_EVENTS_INPUT ADD TRANSFER_SALES_FLAG	BIT NOT NULL DEFAULT(1)

  END

GO