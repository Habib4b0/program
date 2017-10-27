IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'PRC_PRIOR_PROJ_PROD_FILE_DATA'
                  AND ROUTINE_SCHEMA = 'DBO')
  BEGIN
      DROP PROCEDURE [DBO].[PRC_PRIOR_PROJ_PROD_FILE_DATA]
  END

GO

CREATE PROCEDURE [dbo].[PRC_PRIOR_PROJ_PROD_FILE_DATA]    AS 
  BEGIN
   SET NOCOUNT ON
   BEGIN TRY
 DECLARE @FORECAST_NAME_GTS_CUST_PROD    VARCHAR(50),
                  @FORECAST_VERSION_GTS_CUST_PROD VARCHAR(50),
                  @FORECAST_NAME_DEMAND           VARCHAR(50),
                  @FORECAST_VERSION_DEMAND        VARCHAR(50),
                  @FORECAST_NAME_ADJ_DEMAND       VARCHAR(50),
                  @FORECAST_VERSION_ADJ_DEMAND    VARCHAR(50),
                  @FORECAST_NAME_EXFACTORY        VARCHAR(50),
                  @FORECAST_VERSION_EXFACTORY     VARCHAR(50),
                  @FORECAST_NAME_INV_CUST_PROD    VARCHAR(50),
                  @FORECAST_VERSION_INV_CUST_PROD VARCHAR(50),
                  @FORECAST_NAME_INV              VARCHAR(50),
                  @FORECAST_VERSION_INV           VARCHAR(50),
				  @COUNT						 INT
,@ACTUAL_PERIOD INT,@PROJECTION_END_PERIOD INT,@PROJECTION INT
 SELECT @ACTUAL_PERIOD = PERIOD_SID
		  FROM   PERIOD
		  WHERE  PERIOD_DATE = Dateadd(YY, Datediff(YY, 0, Getdate()) - 3, 0)

		  SELECT @PROJECTION_END_PERIOD = PERIOD_SID
		  FROM   PERIOD
		  WHERE  EXISTS (SELECT Dateadd(DAY, 1, Eomonth(PM1.TO_DATE, -1))
								FROM   #PROJECTION_MASTER PM JOIN PROJECTION_MASTER PM1 ON PM.PROJECTION_MASTER_SID=PM1.PROJECTION_MASTER_SID
								WHERE  PM.ID = 1
								AND PERIOD.PERIOD_DATE=Dateadd(DAY, 1, Eomonth(PM1.TO_DATE, -1)))



DECLARE @CUST_ITEM_UDT UDT_CUST_ITEM
DECLARE @I INT=2
DECLARE @ITEMID [DBO].[UDT_ITEM]
	, @PRICING_QUALIFIER              VARCHAR(50)='WAC',
     @ITEM_UOM                       VARCHAR(50) = 'EACH'

IF OBJECT_ID('TEMPDB..#FILE_INFO') IS NOT NULL
			DROP TABLE #FILE_INFO 

SELECT DISTINCT  B.ID,A.PROJECTION_MASTER_SID
,C.DESCRIPTION,FORECAST_NAME,VERSION as FILE_VERSION
--,DENSE_RANK() OVER(ORDER BY A.PROJECTION_MASTER_SID) RN
INTO #FILE_INFO
FROM PROJECTION_FILE_DETAILS A 
INNER JOIN #PROJECTION_MASTER B
ON A.PROJECTION_MASTER_SID=B.PROJECTION_MASTER_SID
INNER JOIN HELPER_TABLE C ON C.HELPER_TABLE_SID=A.FILE_TYPE
INNER JOIN  FILE_MANAGEMENT FM ON FM.FILE_MANAGEMENT_SID=A.FILE_MANAGEMENT_SID 
WHERE B.ID<>1


SELECT  @COUNT=COUNT( DISTINCT ID) FROM #PROJECTION_MASTER 


IF OBJECT_ID('TEMPDB..#PRODUCT_FILE_DATA') IS NOT NULL
			DROP TABLE #PRODUCT_FILE_DATA 

			CREATE TABLE #PRODUCT_FILE_DATA
			(PROJECTION_MASTER_SID           INT,
			 ITEM_MASTER_SID           	     INT,
             PERIOD_SID					     INT,
             EXFACTORY_ACTUAL_SALES		     NUMERIC(22,6),
             EXFACTORY_ACTUAL_UNITS		     NUMERIC(22,6),
             EXFACTORY_FORECAST_SALES	     NUMERIC(22,6),
             EXFACTORY_FORECAST_UNITS	     NUMERIC(22,6),
             DEMAND_ACTUAL_SALES		     NUMERIC(22,6),
             DEMAND_ACTUAL_UNITS		     NUMERIC(22,6),
             DEMAND_FORECAST_SALES		     NUMERIC(22,6),
             DEMAND_FORECAST_UNITS		     NUMERIC(22,6),
             ADJUSTED_DEMAND_ACTUAL_SALES    NUMERIC(22,6),
             ADJUSTED_DEMAND_ACTUAL_UNITS    NUMERIC(22,6),
             ADJUSTED_DEMAND_FORECAST_SALES  NUMERIC(22,6),
             ADJUSTED_DEMAND_FORECAST_UNITS  NUMERIC(22,6),
             INVENTORY_ACTUAL_SALES		     NUMERIC(22,6),
             INVENTORY_ACTUAL_UNITS		     NUMERIC(22,6),
             INVENTORY_FORECAST_SALES	     NUMERIC(22,6),
             INVENTORY_FORECAST_UNITS	     NUMERIC(22,6),
			 ITEM_PRICE					     NUMERIC(22,6),
			 EXFACTORY_CUST_ACTUAL_SALES     NUMERIC(22,6),
             EXFACTORY_CUST_ACTUAL_UNITS     NUMERIC(22,6),
             EXFACTORY_CUST_FORECAST_SALES   NUMERIC(22,6),
             EXFACTORY_CUST_FORECAST_UNITS   NUMERIC(22,6),
			 INVENTORY_CUST_ACTUAL_SALES     NUMERIC(22,6),
             INVENTORY_CUST_ACTUAL_UNITS     NUMERIC(22,6),
             INVENTORY_CUST_FORECAST_SALES   NUMERIC(22,6),
             INVENTORY_CUST_FORECAST_UNITS  NUMERIC(22,6))

WHILE (@I<=@COUNT)
BEGIN

SELECT @PROJECTION =Max(PROJECTION_MASTER_SID)
 ,@FORECAST_NAME_GTS_CUST_PROD = Max(CASE
                                                      WHEN DESCRIPTION = 'CUSTOMER SALES' THEN FORECAST_NAME
                                                    END),
                 @FORECAST_VERSION_GTS_CUST_PROD = Max(CASE
                                                         WHEN DESCRIPTION = 'CUSTOMER SALES' THEN FILE_VERSION
                                                       END),
                 @FORECAST_NAME_DEMAND = Max(CASE
                                               WHEN DESCRIPTION = 'DEMAND' THEN FORECAST_NAME
                                             END),
                 @FORECAST_VERSION_DEMAND = Max(CASE
                                                  WHEN DESCRIPTION = 'DEMAND' THEN FILE_VERSION
                                                END),
                 @FORECAST_NAME_ADJ_DEMAND = Max(CASE
                                                   WHEN DESCRIPTION = 'Adjusted Demand' THEN FORECAST_NAME
                                                 END),
                 @FORECAST_VERSION_ADJ_DEMAND = Max(CASE
                                                      WHEN DESCRIPTION = 'Adjusted Demand' THEN FILE_VERSION
                                                    END),
                 @FORECAST_NAME_EXFACTORY = Max(CASE
                                                  WHEN DESCRIPTION = 'EX-FACTORY SALES' THEN FORECAST_NAME
                                                END),
                 @FORECAST_VERSION_EXFACTORY = Max(CASE
                                                     WHEN DESCRIPTION = 'EX-FACTORY SALES' THEN FILE_VERSION
                                                   END),
                 @FORECAST_NAME_INV = Max(CASE
                                            WHEN DESCRIPTION = 'Inventory Withdrawal - Forecast Summary' THEN FORECAST_NAME
                                          END),
                 @FORECAST_VERSION_INV = Max(CASE
                                               WHEN DESCRIPTION = 'Inventory Withdrawal - Forecast Summary' THEN FILE_VERSION
                                             END),
                 @FORECAST_NAME_INV_CUST_PROD = Max(CASE
                                                      WHEN DESCRIPTION = 'Inventory Withdrawal - Forecast Detail' THEN FORECAST_NAME
                                                    END),
                 @FORECAST_VERSION_INV_CUST_PROD = Max(CASE
                                                         WHEN DESCRIPTION = 'Inventory Withdrawal - Forecast Detail' THEN FILE_VERSION
                                                       END)
          FROM   #FILE_INFO where ID =@I

		INSERT INTO @ITEMID
		SELECT DISTINCT IM.ITEM_MASTER_SID
		FROM ITEM_MASTER IM
		INNER JOIN CCP_DETAILS CD 
		ON CD.ITEM_MASTER_SID=IM.ITEM_MASTER_SID
		INNER JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID =CD.CCP_DETAILS_SID
		INNER JOIN  #FILE_INFO PM ON PD.PROJECTION_MASTER_SID =PM.PROJECTION_MASTER_SID
		WHERE PM.ID =@I AND EXISTS(
		SELECT IM1.ITEM_MASTER_SID FROM ITEM_MASTER IM1
		INNER JOIN CCP_DETAILS CD 
		ON CD.ITEM_MASTER_SID=IM1.ITEM_MASTER_SID
		INNER JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID =CD.CCP_DETAILS_SID
		INNER JOIN #PROJECTION_MASTER PM ON PD.PROJECTION_MASTER_SID =PM.PROJECTION_MASTER_SID
		WHERE PM.ID=1 AND IM.ITEM_MASTER_SID=IM1.ITEM_MASTER_SID)

INSERT INTO @CUST_ITEM_UDT
                      (ITEM_MASTER_SID)

          SELECT ITEM_MASTER_SID
          FROM   @ITEMID

 INSERT INTO #PRODUCT_FILE_DATA (PROJECTION_MASTER_SID,
			 ITEM_MASTER_SID,
             PERIOD_SID,
             EXFACTORY_ACTUAL_SALES,
             EXFACTORY_ACTUAL_UNITS,
             EXFACTORY_FORECAST_SALES,
             EXFACTORY_FORECAST_UNITS,
             DEMAND_ACTUAL_SALES,
             DEMAND_ACTUAL_UNITS,
             DEMAND_FORECAST_SALES,
             DEMAND_FORECAST_UNITS,
             ADJUSTED_DEMAND_ACTUAL_SALES,
             ADJUSTED_DEMAND_ACTUAL_UNITS,
             ADJUSTED_DEMAND_FORECAST_SALES,
             ADJUSTED_DEMAND_FORECAST_UNITS,
             INVENTORY_ACTUAL_SALES,
             INVENTORY_ACTUAL_UNITS,
             INVENTORY_FORECAST_SALES,
             INVENTORY_FORECAST_UNITS,
			 ITEM_PRICE,
			 EXFACTORY_CUST_ACTUAL_SALES,
             EXFACTORY_CUST_ACTUAL_UNITS,
             EXFACTORY_CUST_FORECAST_SALES,
             EXFACTORY_CUST_FORECAST_UNITS,
			 INVENTORY_CUST_ACTUAL_SALES,
             INVENTORY_CUST_ACTUAL_UNITS,
             INVENTORY_CUST_FORECAST_SALES,
             INVENTORY_CUST_FORECAST_UNITS)

SELECT @PROJECTION AS PROJECTION_MASTER_SID,
ITEM_MASTER_SID,
PERIOD_SID,
ACTUAL_GTS_SALES AS EXFACTORY_ACTUAL_SALES,
ACTUAL_GTS_UNITS AS EXFACTORY_ACTUAL_UNITS,
FORECAST_GTS_SALES AS EXFACTORY_FORECAST_SALES,
FORECAST_GTS_UNITS AS EXFACTORY_FORECAST_UNITS,
ACT_GROSS_AMOUNT_D AS DEMAND_ACTUAL_SALES,
ACT_GROSS_UNITS_D AS DEMAND_ACTUAL_UNITS,
FOR_GROSS_AMOUNT_D AS DEMAND_FORECAST_SALES,
FOR_GROSS_UNITS_D AS DEMAND_FORECAST_UNITS,
ACT_GROSS_AMOUNT AS ADJUSTED_DEMAND_ACTUAL_SALES,
ACT_GROSS_UNITS AS ADJUSTED_DEMAND_ACTUAL_UNITS,
FOR_GROSS_AMOUNT AS ADJUSTED_DEMAND_FORECAST_SALES,
FOR_GROSS_UNITS AS ADJUSTED_DEMAND_FORECAST_UNITS,
ACT_AMOUNT_WITHDRAWN AS INVENTORY_ACTUAL_SALES,
ACT_UNITS_WITHDRAWN AS INVENTORY_ACTUAL_UNITS,
FOR_AMOUNT_WITHDRAWN AS INVENTORY_FORECAST_SALES,
FOR_UNITS_WITHDRAWN AS INVENTORY_FORECAST_UNITS,
ITEM_PRICE,
EXFACTORY_CUST_ACTUAL_SALES,
EXFACTORY_CUST_ACTUAL_UNITS,
EXFACTORY_CUST_FORECAST_SALES,
EXFACTORY_CUST_FORECAST_UNITS,
INVENTORY_CUST_ACTUAL_SALES,
INVENTORY_CUST_ACTUAL_UNITS,
INVENTORY_CUST_FORECAST_SALES,
INVENTORY_CUST_FORECAST_UNITS

FROM 
( SELECT g.ITEM_MASTER_SID,
       p.PERIOD_SID,
       A.ACTUAL_GTS_SALES,
       A.ACTUAL_GTS_UNITS,
       A.FORECAST_GTS_SALES,
       A.FORECAST_GTS_UNITS,
       B.ACT_GROSS_AMOUNT ACT_GROSS_AMOUNT_D,
       B.ACT_GROSS_UNITS ACT_GROSS_UNITS_D,
       B.FOR_GROSS_AMOUNT FOR_GROSS_AMOUNT_D,
       B.FOR_GROSS_UNITS FOR_GROSS_UNITS_D,
       C.ACT_GROSS_AMOUNT,
       C.ACT_GROSS_UNITS,
       C.FOR_GROSS_AMOUNT,
       C.FOR_GROSS_UNITS,
       D.ACT_AMOUNT_WITHDRAWN,
       D.ACT_UNITS_WITHDRAWN,
       D.FOR_AMOUNT_WITHDRAWN,
       D.FOR_UNITS_WITHDRAWN,
	   E.ITEM_PRICE,
	   F.CUST_ACT_GTS_SALES AS  EXFACTORY_CUST_ACTUAL_SALES,    
	   F.CUST_ACT_GTS_UNITS AS EXFACTORY_CUST_ACTUAL_UNITS,
	   F.CUST_FORE_GTS_SALES EXFACTORY_CUST_FORECAST_SALES ,
	   F.CUST_FORE_GTS_UNITS EXFACTORY_CUST_FORECAST_UNITS,
	   h.ACT_AMOUNT_WITHDRAWN INVENTORY_CUST_ACTUAL_SALES,
	   h.ACT_UNITS_WITHDRAWN  INVENTORY_CUST_ACTUAL_UNITS,
	   h.FOR_AMOUNT_WITHDRAWN INVENTORY_CUST_FORECAST_SALES,
	   h.FOR_UNITS_WITHDRAWN INVENTORY_CUST_FORECAST_UNITS,
	   ROW_NUMBER() OVER(PARTITION BY A.ITEM_MASTER_SID,A.PERIOD_SID ORDER BY A.ITEM_MASTER_SID) RNO
FROM   
	@ITEMID G 
	CROSS JOIN PERIOD P 
	LEFT JOIN 
UDF_GTS_WAC(@ITEMID, @ACTUAL_PERIOD, @PROJECTION_END_PERIOD, @FORECAST_NAME_EXFACTORY, @FORECAST_VERSION_EXFACTORY) A
   ON A.ITEM_MASTER_SID=G.ITEM_MASTER_SID
   AND A.PERIOD_SID=P.PERIOD_SID
       LEFT JOIN UDF_DEMAND_WAC(@ITEMID, @ACTUAL_PERIOD, @PROJECTION_END_PERIOD, @FORECAST_NAME_DEMAND, @FORECAST_VERSION_DEMAND) B
         ON A.ITEM_MASTER_SID = B.ITEM_MASTER_SID
            AND A.PERIOD_SID = B.PERIOD_SID
       LEFT JOIN UDF_ADJUSTED_DEMAND_WAC(@ITEMID, @ACTUAL_PERIOD, @PROJECTION_END_PERIOD, @FORECAST_NAME_ADJ_DEMAND, @FORECAST_VERSION_ADJ_DEMAND) C
         ON C.ITEM_MASTER_SID = B.ITEM_MASTER_SID
            AND C.PERIOD_SID = B.PERIOD_SID
      LEFT  JOIN UDF_INVENTORY_WAC(@ITEMID, @ACTUAL_PERIOD, @PROJECTION_END_PERIOD, @FORECAST_NAME_INV, @FORECAST_VERSION_INV) D
         ON D.ITEM_MASTER_SID = B.ITEM_MASTER_SID
            AND D.PERIOD_SID = B.PERIOD_SID
	   LEFT JOIN   DBO.UDF_ITEM_PRICING(@ITEMID, @PRICING_QUALIFIER, @ACTUAL_PERIOD, @PROJECTION_END_PERIOD, @ITEM_UOM)E
	      ON E.ITEM_MASTER_SID = B.ITEM_MASTER_SID
            AND E.PERIOD_SID = B.PERIOD_SID
	  LEFT JOIN 
	  [UDF_CUST_GTS_WAC](@CUST_ITEM_UDT,@ACTUAL_PERIOD, @PROJECTION_END_PERIOD, @FORECAST_NAME_GTS_CUST_PROD, @FORECAST_VERSION_GTS_CUST_PROD) F
	  ON F.ITEM_MASTER_SID=G.ITEM_MASTER_SID
	  AND P.PERIOD_SID=F.PERIOD_SID
	 LEFT JOIN 
	 [DBO].[UDF_INVENTORY_WAC_DETAILS](@CUST_ITEM_UDT,@ACTUAL_PERIOD, @PROJECTION_END_PERIOD, @FORECAST_NAME_INV_CUST_PROD, @FORECAST_VERSION_INV_CUST_PROD) H
	 ON H.ITEM_MASTER_SID=G.ITEM_MASTER_SID
	   AND H.PERIOD_SID=P.PERIOD_SID
		WHERE P.PERIOD_SID BETWEEN @ACTUAL_PERIOD AND @PROJECTION_END_PERIOD)A WHERE RNO=1

	SET @I=@I+1
	DELETE FROM @CUST_ITEM_UDT
	DELETE FROM @ITEMID
	END

	SELECT PROJECTION_MASTER_SID,
			 ITEM_MASTER_SID,
             PERIOD_SID,
             EXFACTORY_ACTUAL_SALES,
             EXFACTORY_ACTUAL_UNITS,
             EXFACTORY_FORECAST_SALES,
             EXFACTORY_FORECAST_UNITS,
             DEMAND_ACTUAL_SALES,
             DEMAND_ACTUAL_UNITS,
             DEMAND_FORECAST_SALES,
             DEMAND_FORECAST_UNITS,
             ADJUSTED_DEMAND_ACTUAL_SALES,
             ADJUSTED_DEMAND_ACTUAL_UNITS,
             ADJUSTED_DEMAND_FORECAST_SALES,
             ADJUSTED_DEMAND_FORECAST_UNITS,
             INVENTORY_ACTUAL_SALES,
             INVENTORY_ACTUAL_UNITS,
             INVENTORY_FORECAST_SALES,
             INVENTORY_FORECAST_UNITS,
			 ITEM_PRICE,
			 EXFACTORY_CUST_ACTUAL_SALES,
             EXFACTORY_CUST_ACTUAL_UNITS,
             EXFACTORY_CUST_FORECAST_SALES,
             EXFACTORY_CUST_FORECAST_UNITS,
			 INVENTORY_CUST_ACTUAL_SALES,
             INVENTORY_CUST_ACTUAL_UNITS,
             INVENTORY_CUST_FORECAST_SALES,
             INVENTORY_CUST_FORECAST_UNITS FROM #PRODUCT_FILE_DATA
	END TRY

      BEGIN CATCH
          DECLARE @ERRORMESSAGE NVARCHAR(4000);
          DECLARE @ERRORSEVERITY INT;
          DECLARE @ERRORSTATE INT;
          DECLARE @ERRORNUMBER INT;
          DECLARE @ERRORPROCEDURE VARCHAR(200);
          DECLARE @ERRORLINE INT;

          EXEC Usperrorcollector

          SELECT @ERRORMESSAGE = Error_message(),
                 @ERRORSEVERITY = Error_severity(),
                 @ERRORSTATE = Error_state(),
                 @ERRORPROCEDURE = Error_procedure(),
                 @ERRORLINE = Error_line(),
                 @ERRORNUMBER = Error_number()

          RAISERROR ( @ERRORMESSAGE,-- MESSAGE TEXT.
                      @ERRORSEVERITY,-- SEVERITY.
                      @ERRORSTATE,-- STATE.
                      @ERRORPROCEDURE,-- PROCEDURE_NAME.
                      @ERRORNUMBER,-- ERRORNUMBER
                      @ERRORLINE -- ERRORLINE
          );
      END CATCH
 
  END 
