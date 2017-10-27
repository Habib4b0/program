IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'PRC_ACTUAL_DETAILS_POPULATION'
                  AND ROUTINE_SCHEMA = 'DBO')
  BEGIN
      DROP PROCEDURE [dbo].[PRC_ACTUAL_DETAILS_POPULATION]
  END
GO


CREATE  PROCEDURE [DBO].[PRC_ACTUAL_DETAILS_POPULATION]


AS

/****************************************************************************************************************************
** FILE NAME		:	PRC_ACTUAL_DETAILS_POPULATION.SQL
** PROCEDURE NAME	:	PRC_ACTUAL_DETAILS_POPULATION
** DESCRIPTION		:	TO INSERT ACTUALS INTO ACTUALS_DETAILS FROM ACTUALS_MASTER FOR THE NEWLY CREATED CCPS FROM THE UI.											
** OUTPUT PARAMETERS:	NA
** AUTHOR NAME		:	@THARUNB
** CREATION DATE	:	22/05/2017 - MM/DD/YYYY
** CALLED BY		:   CALLED BY APPLICATION ON THE COMPLETION OF PRC_CCP_POPULATION PROCEDURE 
*****************************************************************************************************************************
** CHANGE HISTORY
*****************************************************************************************************************************
** VER   DATE      TICKET NO          AUTHOR           DESCRIPTION 
** ---   --------  -----------        ----------       -----------------------------------------------
** NA    NA        NA                 NA               NA
*****************************************************************************************************************************/


BEGIN


-- THIS INSERT STATEMENT INSERTS ACTUALS FROM ACTUAL_MASTER TO ACTUALS_DETAILS FOR THE CCPS THAT ARE CREATED FROM APPLICATION
INSERT INTO ACTUALS_DETAILS
(
 [PERIOD_SID]         
 ,[RS_MODEL_SID]      
 ,[CCP_DETAILS_SID]   
 ,[SALES]             
 ,[QUANTITY]          
 ,[DISCOUNT]          
 ,[QUANTITY_INCLUSION]
 ,[ACTUAL_ID]  
)


SELECT       
A.PERIOD_SID, 
RM.RS_MODEL_SID, 
CCP.CCP_DETAILS_SID ,
ISNULL((A.SALES), 0)               AS SALES, 
ISNULL((A.QUANTITY), 0)            AS QUANTITY, 
ISNULL((A.DISCOUNT), 0)            AS DISCOUNT, 
ISNULL((A.QUANTITY_INCLUSION), 'Y')AS QUANTITY_INCLUSION,
A.ACTUAL_ID
FROM   (SELECT CONTRACT_MASTER_SID, 
                     COMPANY_MASTER_SID, 
                     ITEM_MASTER_SID, 
                     PROVISION_ID, 
                     PERIOD_SID, 
                     YEAR, 
                     MONTH, 
                     QUARTER, 
                     PERIOD_DATE, 
                     SALES, 
                     QUANTITY, 
                     DISCOUNT, 
                     QUANTITY_INCLUSION ,
					 B.ACTUAL_ID
              FROM   PERIOD A 
                     JOIN (SELECT PROVISION_ID, 
                                  ACCRUAL_ACTUAL_START_DATE   START_DATE, 
                                  ACCRUAL_ACTUAL_END_DATE     END_DATE, 
                                  QUANTITY_INCLUSION, 
                                  A1.CONTRACT_MASTER_SID, 
                                  A1.COMPANY_MASTER_SID, 
                                  A1.ITEM_MASTER_SID, 
                                  (SALES_AMOUNT) / ( DATEDIFF(MM, ACCRUAL_ACTUAL_START_DATE, ACCRUAL_ACTUAL_END_DATE)
                                                        + 1 ) SALES, 
                                  (QUANTITY) / ( DATEDIFF(MM, ACCRUAL_ACTUAL_START_DATE, ACCRUAL_ACTUAL_END_DATE)
                                                    + 1 )     QUANTITY, 
                                  (AMOUNT) / ( DATEDIFF(MM, ACCRUAL_ACTUAL_START_DATE, ACCRUAL_ACTUAL_END_DATE)
                                                  + 1 )       DISCOUNT 
												  ,A1.ACTUAL_ID
                           FROM   ACTUALS_MASTER A1) B 
             ON A.PERIOD_DATE BETWEEN B.START_DATE AND B.END_DATE)A  
             JOIN RS_MODEL RM 
               ON RM.RS_ID = A.PROVISION_ID 
             JOIN CCP_DETAILS CCP 
               ON CCP.COMPANY_MASTER_SID = A.COMPANY_MASTER_SID 
                  AND CCP.ITEM_MASTER_SID = A.ITEM_MASTER_SID 
                  AND CCP.CONTRACT_MASTER_SID = A.CONTRACT_MASTER_SID
              AND NOT EXISTS (SELECT 1 FROM ACTUALS_DETAILS AD WHERE A.ACTUAL_ID = AD.ACTUAL_ID )
    
END





GO


