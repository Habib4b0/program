IF OBJECT_ID('PRC_M_PARITY_LOOKUP','P') IS NOT NULL
  DROP PROCEDURE PRC_M_PARITY_LOOKUP
  GO
CREATE PROCEDURE [dbo].[PRC_M_PARITY_LOOKUP](@CONTRACT_MASTER_SID INT,
                                     @ITEM_MASTER_SID     INT,
                                     @START_PERIOD_SID    INT,
                                     @END_PERIOD_SID      INT,
                                     @USER_ID             INT,
                                     @SESSION_ID          varchar(50))
AS
  BEGIN
      SET NOCOUNT ON

	  DECLARE @SQL NVARCHAR(MAX),
	  @PROJECTION_TABLE   VARCHAR(200)= Concat('ST_M_SUPPLEMENTAL_DISC_PROJ_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''))

      IF Object_id('tempdb..#TEMP') IS NOT NULL
        DROP TABLE #temp

      CREATE TABLE #temp
        (
           METHODOLOGY     VARCHAR(50),
           CONTRACT_PRICE  NUMERIC(22, 6),
           DISCOUNT_RATE_1 NUMERIC(22, 6),
           DISCOUNT_RATE_2 NUMERIC(22, 6),
           PERIOD_SID      INT
        )

      ---------------------------------------------------To check in current projection----------------------------------------------------------
     SET @SQL=CONCAT( 'INSERT INTO #temp
                  (METHODOLOGY,
                   CONTRACT_PRICE,
                   DISCOUNT_RATE_1,
                   DISCOUNT_RATE_2,
                   PERIOD_SID)
      SELECT DISTINCT SP.METHODOLOGY,
                      COALESCE(SP.CONTRACT_PRICE, 0),
                      COALESCE(SP.DISCOUNT_RATE_1, 0),
                      COALESCE(SP.DISCOUNT_RATE_2, 0),
                      SP.PERIOD_SID
      FROM    CCP_DETAILS CCP
             
             JOIN ',@PROJECTION_TABLE,' SP
               ON CCP.CCP_DETAILS_SID = SP.CCP_DETAILS_SID
      WHERE  CCP.CONTRACT_MASTER_SID = ',@CONTRACT_MASTER_SID,'
             AND CCP.ITEM_MASTER_SID = ',@ITEM_MASTER_SID,'
             AND SP.PERIOD_SID BETWEEN ',@START_PERIOD_SID,' AND ',@END_PERIOD_SID)
		
			 EXEC sp_executesql @SQL

      --------------------------------------------------To check in latest approved projection----------------------------------------------------
      IF NOT EXISTS(SELECT 1
                    FROM   #TEMP)
        BEGIN
            INSERT INTO #temp
                        (METHODOLOGY,
                         CONTRACT_PRICE,
                         DISCOUNT_RATE_1,
                         DISCOUNT_RATE_2,
                         PERIOD_SID)
            SELECT SP.METHODOLOGY,
                   COALESCE(SP.CONTRACT_PRICE, 0),
                   COALESCE(SP.DISCOUNT_RATE_1, 0),
                   COALESCE(SP.DISCOUNT_RATE_2, 0),
                   SP.PERIOD_SID
            FROM   (SELECT TOP 1 PM.PROJECTION_MASTER_SID,
                                 PD.PROJECTION_DETAILS_SID
                    FROM   PROJECTION_MASTER PM
                           JOIN WORKFLOW_MASTER WM
                             ON PM.PROJECTION_MASTER_SID = WM.PROJECTION_MASTER_SID
                                AND EXISTS (SELECT HELPER_TABLE_SID
                                                                            FROM   HELPER_TABLE H1
                                                                            WHERE  LIST_NAME = 'WORKFLOWSTATUS'
                                                                                   AND DESCRIPTION = 'APPROVED'
																				   AND H1.HELPER_TABLE_SID=WM.WORKFLOW_STATUS_ID)
                           JOIN PROJECTION_DETAILS PD
                             ON WM.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID
                    WHERE  EXISTS (SELECT CCP_DETAILS_SID 
                                                  FROM   CCP_DETAILS T
                                                  WHERE  CONTRACT_MASTER_SID = @CONTRACT_MASTER_SID
                                                         AND ITEM_MASTER_SID = @ITEM_MASTER_SID
														 AND PD.CCP_DETAILS_SID =T.CCP_DETAILS_SID)
                    ORDER  BY WM.MODIFIED_DATE DESC) OP
                   JOIN M_SUPPLEMENTAL_DISC_PROJ SP
                     ON OP.PROJECTION_DETAILS_SID = SP.PROJECTION_DETAILS_SID
            WHERE  SP.PERIOD_SID BETWEEN @START_PERIOD_SID AND @END_PERIOD_SID
        END

      ------------------------------------------------Output Result--------------------------------------------------------------------------------
      SELECT DISTINCT P.QUARTER,
                      P.YEAR,
                      A.METHODOLOGY,
                      A.CONTRACT_PRICE,
                      A.DISCOUNT_RATE_1,
                      A.DISCOUNT_RATE_2
      FROM   PERIOD P
             LEFT JOIN #TEMP A
                    ON P.PERIOD_SID = A.PERIOD_SID
      WHERE  P.PERIOD_SID BETWEEN @START_PERIOD_SID AND @END_PERIOD_SID
  END 
  GO