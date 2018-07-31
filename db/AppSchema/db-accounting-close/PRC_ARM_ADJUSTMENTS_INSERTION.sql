IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'PRC_ARM_ADJUSTMENTS_INSERTION'
                  AND ROUTINE_SCHEMA = 'DBO')
  BEGIN
      DROP PROCEDURE [DBO].[PRC_ARM_ADJUSTMENTS_INSERTION]
  END

GO


CREATE PROCEDURE [dbo].[PRC_ARM_ADJUSTMENTS_INSERTION] (@PROJECTION_MASTER_SID INT,
                                                @ADJUSTMENT_TYPE       INT,
                                                @SUMMARY_TABLE         VARCHAR(200),
                                                @ARM_ADJUSTMENTS       VARCHAR(200))
AS
  BEGIN
      DECLARE @SQL NVARCHAR(max)=''

      IF Object_id('TEMPDB..#ARM_ADJ_RES_CCP') IS NOT NULL
        DROP TABLE #ARM_ADJ_RES_CCP

      SELECT ARM_ADJUSTMENT_DETAILS_SID,
             ACCOUNT,
             CREDIT,
             DEBIT
      INTO   #ARM_ADJ_RES_CCP
      FROM   ARM_ADJ_RES_CCP AC
      WHERE  CREDIT IS NOT NULL
             AND DEBIT IS NOT NULL
             AND ADJUSTMENT_TYPE = @ADJUSTMENT_TYPE
             AND EXISTS(SELECT 1
                        FROM   ARM_ADJUSTMENT_DETAILS AR
                        WHERE  AR.ARM_ADJUSTMENT_DETAILS_SID = AC.ARM_ADJUSTMENT_DETAILS_SID
                               AND AR.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID)

      SET @SQL = Concat('IF EXISTS (SELECT 1 FROM ', @ARM_ADJUSTMENTS, ' )  
					                   BEGIN 
												TRUNCATE TABLE ', @ARM_ADJUSTMENTS, '  
									   END
							
				INSERT INTO ', @ARM_ADJUSTMENTS, ' with (tablock)
                             (ARM_ADJUSTMENT_DETAILS_SID,
							 PERIOD_SID, 
                             ADJUSTMENT_TYPE,
							 ACCOUNT,
                             CREDIT,
							 DEBIT
							 )
                	SELECT TAM.ARM_ADJUSTMENT_DETAILS_SID, 
					   TAM.PERIOD_SID AS PERIOD_SID,
					    ', @ADJUSTMENT_TYPE, '  AS ADJUSTMENT_TYPE,
					   AAC.ACCOUNT,
       CASE
         WHEN TAM.INDICATOR = AAC.CREDIT THEN TAM.VARIANCE
       END CREDIT,
       CASE
         WHEN TAM.INDICATOR = AAC.DEBIT THEN TAM.VARIANCE
       END DEBIT
FROM   (SELECT ARM_ADJUSTMENT_DETAILS_SID,
               PERIOD_SID,
               Abs(VARIANCE) AS VARIANCE,
               CASE
                 WHEN ( VARIANCE ) > 0 THEN 0
                 WHEN ( VARIANCE ) < 0 THEN 1
                 ELSE NULL
               END           INDICATOR
        FROM   ', @SUMMARY_TABLE, '  )TAM
       INNER JOIN #ARM_ADJ_RES_CCP AAC
               ON TAM.ARM_ADJUSTMENT_DETAILS_SID = AAC.ARM_ADJUSTMENT_DETAILS_SID')

      EXEC Sp_executesql
        @SQL
  END 


GO