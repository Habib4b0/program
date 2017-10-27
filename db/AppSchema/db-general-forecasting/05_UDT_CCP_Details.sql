IF NOT EXISTS (SELECT 1
               FROM   SYS.TYPES A
               WHERE  NAME = 'UDT_CCP_DETAILS'
                      AND IS_USER_DEFINED = 1)
  BEGIN
      CREATE TYPE [dbo].[UDT_CCP_DETAILS] AS TABLE(  [CONTRACT_MASTER_SID] [int] NULL, 
													 [COMPANY_MASTER_SID] [int] NULL,
													 [ITEM_MASTER_SID] [int] NULL, 
													 [PROJECTION_DETAILS_SID] [int] NULL, 
													 [DISCOUNT_ID] [varchar](50) NULL)
  END
GO