/*Update Script to update variance information for the respective Tx-7 existing projection*/
IF EXISTS(SELECT 1
          FROM   ARM_DISTRIBUTION_FEES_RATE
          WHERE  [OVERRIDE] IS NULL
                 AND [TEMP_OVERRIDE] IS NULL
                 AND ( [CURRENT_BALANCE] - [CALCULATED_ADJUSTMENT] ) <> [VARIANCE])
  BEGIN
      IF Object_id('TEMPDB..#CHANGE_RECORDS') IS NOT NULL
        DROP TABLE #CHANGE_RECORDS

      SELECT ARM_ADJUSTMENT_DETAILS_SID,
             PERIOD_SID,
             Sum([CURRENT_BALANCE] - [CALCULATED_ADJUSTMENT]) AS CURRENT_BALANCE,
             CASE
               WHEN Sum([CURRENT_BALANCE] - [CALCULATED_ADJUSTMENT]) > 0 THEN 0
               WHEN Sum([CURRENT_BALANCE] - [CALCULATED_ADJUSTMENT]) < 0 THEN 1
             END                                              AS INDICATOR
      INTO   #CHANGE_RECORDS
      FROM   ARM_DISTRIBUTION_FEES_RATE
      WHERE  [OVERRIDE] IS NULL
             AND [TEMP_OVERRIDE] IS NULL
             AND ( [CURRENT_BALANCE] - [CALCULATED_ADJUSTMENT] ) <> [VARIANCE]
      GROUP  BY ARM_ADJUSTMENT_DETAILS_SID,
                PERIOD_SID
      HAVING CASE
               WHEN Sum([CURRENT_BALANCE] - [CALCULATED_ADJUSTMENT]) > 0 THEN 0
               WHEN Sum([CURRENT_BALANCE] - [CALCULATED_ADJUSTMENT]) < 0 THEN 1
             END <> 0

      UPDATE ARM_DISTRIBUTION_FEES_RATE
      SET    [VARIANCE] = ( [CURRENT_BALANCE] - [CALCULATED_ADJUSTMENT] )
      WHERE  [OVERRIDE] IS NULL
             AND [TEMP_OVERRIDE] IS NULL
             AND ( [CURRENT_BALANCE] - [CALCULATED_ADJUSTMENT] ) <> [VARIANCE]

      UPDATE AA
      SET    CREDIT = BB.CREDIT,
             DEBIT = BB.DEBIT
      FROM   ARM_ADJUSTMENTS AA
             JOIN (SELECT TAM.ARM_ADJUSTMENT_DETAILS_SID,
                          TAM.PERIOD_SID               AS PERIOD_SID,
                          AAC.ADJUSTMENT_TYPE,
                          AAC.ACCOUNT,
                          Sum(Abs(A1.CURRENT_BALANCE)) AS CREDIT,
                          Sum(Abs(A2.CURRENT_BALANCE)) AS DEBIT
                   FROM   #CHANGE_RECORDS TAM
                          LEFT JOIN ARM_ADJ_RES_CCP AAC
                                 ON TAM.ARM_ADJUSTMENT_DETAILS_SID = AAC.ARM_ADJUSTMENT_DETAILS_SID
                          LEFT JOIN #CHANGE_RECORDS A1
                                 ON AAC.ARM_ADJUSTMENT_DETAILS_SID = A1.ARM_ADJUSTMENT_DETAILS_SID
                                    AND AAC.CREDIT = A1.INDICATOR
                          LEFT JOIN #CHANGE_RECORDS A2
                                 ON AAC.ARM_ADJUSTMENT_DETAILS_SID = A2.ARM_ADJUSTMENT_DETAILS_SID
                                    AND AAC.DEBIT = A2.INDICATOR
                          LEFT JOIN PERIOD P
                                 ON P.PERIOD_SID = Isnull(A1.PERIOD_SID, A2.PERIOD_SID)
                                    AND P.PERIOD_SID = TAM.PERIOD_SID
                   WHERE  AAC.CREDIT IS NOT NULL
                          AND AAC.DEBIT IS NOT NULL
                          AND EXISTS (SELECT 1
                                      FROM   ARM_ADJUSTMENT_CONFIG AG
                                             JOIN HELPER_TABLE HT
                                               ON HT.HELPER_TABLE_SID = AG.METHODOLGY
                                      WHERE  HT.DESCRIPTION = 'TRANSACTION 7 - DISTRIBUTION FEES'
                                             AND AAC.ADJUSTMENT_TYPE = AG.ARM_ADJUSTMENT_CONFIG_SID)
                   GROUP  BY TAM.ARM_ADJUSTMENT_DETAILS_SID,
                             AAC.ADJUSTMENT_TYPE,
                             TAM.PERIOD_SID,
                             AAC.ACCOUNT) BB
               ON BB.ADJUSTMENT_TYPE = AA.ADJUSTMENT_TYPE
                  AND AA.ACCOUNT = BB.ACCOUNT
                  AND AA.ARM_ADJUSTMENT_DETAILS_SID = BB.ARM_ADJUSTMENT_DETAILS_SID
                  AND AA.PERIOD_SID = BB.PERIOD_SID
  END

GO 
