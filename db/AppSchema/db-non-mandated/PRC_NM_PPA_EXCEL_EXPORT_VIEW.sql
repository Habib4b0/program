IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'PRC_NM_PPA_EXCEL_EXPORT_VIEW'
                  AND ROUTINE_SCHEMA = 'DBO')
  BEGIN
      DROP PROCEDURE [DBO].[PRC_NM_PPA_EXCEL_EXPORT_VIEW]
  END

GO


CREATE PROCEDURE [dbo].[PRC_NM_PPA_EXCEL_EXPORT_VIEW]  @PROJECTION_SID         VARCHAR(500), 
                                                      @FREQUENCY              CHAR(1), 
                                                      @CP_INDICATOR           CHAR(1),
                                                      @GROUP_FILTER           VARCHAR(50),
													  @GROUP_FILTER_VALUE     VARCHAR(50),
                                                      @ORDER_FLAG             BIT, 
                                                      @HIERARCHY_NO           VARCHAR(500)=NULL,
                                                      @LEVEL_NO               INT=NULL, 
                                                      @CUSTOM_VIEW_MASTER_SID INT = NULL
WITH
EXEC AS CALLER AS
/**********************************************************************************************************
** FILE NAME		:	PRC_NM_PPA_EXCEL_EXPORT.SQL
** PROCEDURE NAME	:	PRC_NM_PPA_EXCEL_EXPORT
** DESCRIPTION		:	this procedure will calculate the ppa discount based on the hierarchy chosed in the screen
** INPUT PARAMETERS	:	@PROJECTION_SID	  - passing input as PROJECTION_MASTER_SID
						@USER_ID          - user id for the particular projection 
						@SESSION_ID       - session id for the particular projection 
						@FREQUENCY        - this will be the frequency in which used has choosen in screen(ppa results)
						@CP_INDICATOR			- this will determine which hierrachy was choosen by user in screen
						@GROUP_FILTER           - this will determine which GROUP_FILTER was choosen by user in screen
						@GROUP_FILTER_VALUE     - this will determine which GROUP_FILTER_VALUE was choosen by user in screen
						@ORDER_FLAG             - the result should be ordered or not
						@HIERARCHY_NO           - this will determine which hierrachy number was choosen by user in screen
						@LEVEL_NO               - this will determine which level number was choosen by user in screen
						@CUSTOM_VIEW_MASTER_SID - this will determine which customer hierrachy was choosen by user in screen
                        
** OUTPUT PARAMETERS:	na
** AUTHOR NAME		:	@Paul Perinchery and @Lakshmi
** CREATION DATE	:	 
** CALLED BY		:   This procedure will be called from UI in the excel button was clicked in ppa results screen.
**********************************************************************************************************
** CHANGE HISTORY
**********************************************************************************************************
** VER   DATE       LOCAL ALMTICKET NO      SUBTICKET NO        ONLINE ALM TICKET      AUTHOR                              DESCRIPTION 
** ---   --------      ---------             -------------        -------------     ----------------                       ------------------
** 1                                                                                   @Paul Perinchery and @Lakshmi   
*****************************************************************************************************/

  BEGIN
        SET NOCOUNT ON
		 -----------variable declaration----------------
        DECLARE @STARTFROM                     DATE, 
            @PROJECTION_DATE               DATE, 
            @CUST_RELATIONSHIP_BUILDER_SID INT, 
            @ACT_START_PERIOD_SID          INT, 
            @ACT_END_PERIOD_SID            INT, 
            @PROJ_END_PERIOD_SID           INT, 
            @PIVOT_START_PERIOD_SID        INT 
    DECLARE @FIRST_PROJ_SID                INT, 
            @PROJ_START_PERIOD_SID         INT, 
            @START_DATE                    DATETIME, 
            @PROD_RELATIONSHIP_BUILDER_SID INT, 
            @MIN_LEVEL                     INT, 
            @ACTUAL_START_DATE             DATE, 
            @ACTUAL_END_DATE               DATE
			 DECLARE @S_MASTER_TABLE     VARCHAR(200) ='NM_SALES_PROJECTION_MASTER',
              @S_ACTUAL_TABLE     VARCHAR(200) ='NM_ACTUAL_SALES',
              @S_PROJECTION_TABLE VARCHAR(200) ='NM_SALES_PROJECTION',
			  @P_PROJECTION_TABLE  VARCHAR(200)='NM_PPA_PROJECTION',
			  @P_MASTER_TABLE     VARCHAR(200) ='NM_PPA_PROJECTION',
			  @P_ACTUAL_TABLE     VARCHAR(200) ='NM_ACTUAL_PPA'
			   DECLARE @D_SQL     NVARCHAR(MAX) 

   
    IF OBJECT_ID('TEMPDB.DBO.#PERIOD', 'U') IS NOT NULL 
      DROP TABLE #PERIOD; 

    SELECT PERIOD_SID, 
           PERIOD_DATE, 
           MONTH, 
           QUARTER, 
           SEMI_ANNUAL, 
           YEAR, 
           CASE 
                      WHEN LEFT(@FREQUENCY, 1) = 'M' THEN MONTH 
                      WHEN LEFT(@FREQUENCY, 1) = 'Q' THEN QUARTER 
                      WHEN LEFT(@FREQUENCY, 1) = 'S' THEN SEMI_ANNUAL 
                      ELSE YEAR 
                    END AS PERIOD
    INTO   #PERIOD 
    FROM   PERIOD 

    SELECT @FIRST_PROJ_SID = @PROJECTION_SID 

    SELECT TOP 1 @STARTFROM = DATEADD(YY, DATEDIFF(YY, 0, DATEADD(YY, -3, 
                                                          GETDATE())), 0), 
                 @START_DATE = DATEADD(DD, 1, EOMONTH(FROM_DATE, -1)), 
                 @PROJECTION_DATE = DATEADD(DD, 1, EOMONTH(TO_DATE, -1)), 
                 @CUST_RELATIONSHIP_BUILDER_SID = CUST_RELATIONSHIP_BUILDER_SID, 
                 @PROD_RELATIONSHIP_BUILDER_SID = PROD_RELATIONSHIP_BUILDER_SID, 
                 @MIN_LEVEL = CASE @CP_INDICATOR 
                                WHEN 'C' THEN CUSTOMER_HIERARCHY_LEVEL 
                                WHEN 'P' THEN PRODUCT_HIERARCHY_LEVEL 
                              END 
    FROM   PROJECTION_MASTER 
    WHERE  PROJECTION_MASTER_SID = @FIRST_PROJ_SID
	ORDER BY PROJECTION_MASTER_SID  

    SELECT @ACTUAL_START_DATE = CAST(YEAR(GETDATE()) - 3 AS VARCHAR(4)) 
                                + '-01-01', 
           @ACTUAL_END_DATE = DATEADD(MM, -1, DATEADD(QQ, DATEDIFF(QQ, 0, 
                                                          GETDATE()) 
                                              , 0)) 

    SELECT @PROJ_END_PERIOD_SID = MAX(CASE 
                                        WHEN PERIOD_DATE = 
                                             DATEADD(MM, DATEDIFF(MM, 
                                                         0, 
                                                         @PROJECTION_DATE), 0 
                                             ) THEN 
                                        PERIOD_SID 
                                      END), 
           @PROJ_START_PERIOD_SID = MAX(CASE 
                                          WHEN PERIOD_DATE = @START_DATE THEN 
                                          PERIOD_SID 
                                        END), 
           @ACT_START_PERIOD_SID = MAX(CASE 
                                         WHEN PERIOD_DATE = @ACTUAL_START_DATE 
                                       THEN 
                                         PERIOD_SID 
                                       END), 
           @ACT_END_PERIOD_SID = MAX(CASE 
                                       WHEN PERIOD_DATE = @ACTUAL_END_DATE THEN 
                                       PERIOD_SID 
                                     END) 
    FROM   PERIOD 
    WHERE  PERIOD_DATE IN ( @STARTFROM, @PROJECTION_DATE, @START_DATE, 
                            @ACTUAL_END_DATE ) 

    SELECT @PIVOT_START_PERIOD_SID = PERIOD_SID 
    FROM   PERIOD 
    WHERE  PERIOD_DATE = @START_DATE 

	  ----------checking the hierrachy type choosen by the user --------
   
        DECLARE @CCP TABLE
        (
           CCP_DETAILS_SID INT,
           HIERARCHY_NO    VARCHAR(50),
           LEVEL_NAME      VARCHAR(100),
                 LEVEL_NO INT,
                 RELATIONSHIP_LEVEL_VALUES VARCHAR(50)
        )

      IF @CP_INDICATOR IN ( 'C', 'P' )
        BEGIN
            DECLARE @SQL1 NVARCHAR(MAX)

            SET @SQL1 = CONCAT ('SELECT DISTINCT LCCP.CCP_DETAILS_SID,
                LCCP.HIERARCHY_NO,
                LEVEL_NAME,
                           LEVEL_NO,
                           RELATIONSHIP_LEVEL_VALUES
FROM   (SELECT CCPMAP.CCP_DETAILS_SID,
              HLD.RELATIONSHIP_LEVEL_VALUES,
               HLD.HIERARCHY_NO,
               HLD.RELATIONSHIP_LEVEL_SID,
               LEVEL_NAME,
                        LEVEL_NO
        FROM   (SELECT RLD.RELATIONSHIP_LEVEL_VALUES,
                       RLD.HIERARCHY_NO,
                       CCP.CCP_DETAILS_SID
                FROM   RELATIONSHIP_LEVEL_DEFINITION RLD
                       JOIN CCP_MAP CCP
                         ON RLD.RELATIONSHIP_LEVEL_SID = CCP.RELATIONSHIP_LEVEL_SID
                            AND RLD.RELATIONSHIP_BUILDER_SID = ', CASE @CP_INDICATOR
                                                                                            WHEN 'C' THEN @CUST_RELATIONSHIP_BUILDER_SID
                                                                                            WHEN 'P' THEN @PROD_RELATIONSHIP_BUILDER_SID
                                                                                          END, '
                       JOIN PROJECTION_DETAILS PD
                         ON PD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
                            AND PD.PROJECTION_MASTER_SID = ', @FIRST_PROJ_SID, '
                        ', CASE @GROUP_FILTER
                                                     WHEN 'PPA' THEN ' 
                                         JOIN '+@S_MASTER_TABLE+' S ON S.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID'
                                                   END, ' 
                         
                WHERE  (S.USER_GROUP = ''', @GROUP_FILTER_VALUE, ''' OR ''ALL'' = ''', @GROUP_FILTER_VALUE, ''')
                      
                        ) CCPMAP,
               (SELECT RLD1.HIERARCHY_NO,
                       RLD1.RELATIONSHIP_LEVEL_SID,
                       LEVEL_NAME
                                     ,LEVEL_NO
                                     ,RLD1.RELATIONSHIP_LEVEL_VALUES
                FROM   RELATIONSHIP_LEVEL_DEFINITION RLD1
                       JOIN ', CASE @CP_INDICATOR
                                                         WHEN 'C' THEN 'PROJECTION_CUST_HIERARCHY'
                                                         WHEN 'P' THEN 'PROJECTION_PROD_HIERARCHY'
                                                       END, ' PCH
                         ON PCH.RELATIONSHIP_LEVEL_SID = RLD1.RELATIONSHIP_LEVEL_SID
                            AND PCH.PROJECTION_MASTER_SID = ', @FIRST_PROJ_SID, '
                                                ', CASE
                                    WHEN @LEVEL_NO <> 0 THEN 'WHERE  RLD1.HIERARCHY_NO LIKE '
                                                             + @HIERARCHY_NO + '%'
                                    ELSE ''
                                  END, '
               ) HLD
        WHERE  CCPMAP.HIERARCHY_NO LIKE HLD.HIERARCHY_NO + ''%'') LCCP
WHERE  LCCP.HIERARCHY_NO IN (SELECT RLD2.HIERARCHY_NO
                             FROM   RELATIONSHIP_LEVEL_DEFINITION RLD2
                                    JOIN '
                                       + CASE @CP_INDICATOR WHEN 'C' THEN 'PROJECTION_CUST_HIERARCHY' WHEN 'P' THEN 'PROJECTION_PROD_HIERARCHY' END
                                       + ' PCH2
                                      ON PCH2.RELATIONSHIP_LEVEL_SID = RLD2.RELATIONSHIP_LEVEL_SID
                                         AND PCH2.PROJECTION_MASTER_SID = '
                                       + CAST(@FIRST_PROJ_SID AS VARCHAR(20))
                                       + '
                             WHERE  RLD2.LEVEL_NO  '
                                       + CASE WHEN @LEVEL_NO = 0 THEN ' >= ' + CAST(@MIN_LEVEL AS VARCHAR(50)) ELSE '= ' + CAST(@LEVEL_NO AS VARCHAR(50)) END + ')')

            INSERT INTO @CCP
                        (CCP_DETAILS_SID,
               HIERARCHY_NO,
                LEVEL_NAME,
                           LEVEL_NO,
                           RELATIONSHIP_LEVEL_VALUES)
                     
    EXEC sp_executesql @SQL1

END 
ELSE 
  --------------------if custom hierarchy was choosen then we will custom related tables---------------------
  BEGIN 
  
                              

IF OBJECT_ID('TEMPDB..#TEMP') IS NOT NULL 
  DROP TABLE #TEMP 

CREATE TABLE #TEMP 
  ( 
     CCP_DETAILS_SID INT PRIMARY KEY 
  ) 

SET @D_SQL=CONCAT('INSERT INTO #TEMP 
            (CCP_DETAILS_SID) 
SELECT DISTINCT PD.CCP_DETAILS_SID 
FROM   PROJECTION_DETAILS PD 
       JOIN ',@P_MASTER_TABLE,' ST 
         ON PD.PROJECTION_DETAILS_SID = ST.PROJECTION_DETAILS_SID 
WHERE  PD.PROJECTION_MASTER_SID = ',@PROJECTION_SID 
)
EXEC sp_executesql @D_SQL

DECLARE @SQL_UNION        NVARCHAR(4000)='', 
        @TRADING_LEVEL    INT

IF OBJECT_ID('TEMPDB..#LEVEL_DETAILS') IS NOT NULL 
  DROP TABLE #LEVEL_DETAILS 

CREATE TABLE #LEVEL_DETAILS 
  ( 
     CCP_DETAILS_SID           INT, 
     CUSTOM_HIERARCHY_NO       VARCHAR(50), 
     LEVEL_NAME                VARCHAR(50), 
     LEVEL_NO                  INT, 
     RELATIONSHIP_LEVEL_VALUES VARCHAR(50)
  ) 

INSERT INTO #LEVEL_DETAILS 
            (CCP_DETAILS_SID, 
             CUSTOM_HIERARCHY_NO, 
             LEVEL_NAME, 
             LEVEL_NO, 
             RELATIONSHIP_LEVEL_VALUES) 
SELECT DISTINCT CCM.CCP_DETAILS_SID, 
                CRB.CUSTOM_HIERARCHY_NO, 
                RLD.LEVEL_NAME, 
                CVD.LEVEL_NO, 
                CRB.RELATIONSHIP_LEVEL_VALUES          
FROM   CUSTOM_RELATIONSHIP_BUILDER CRB 
       INNER JOIN CUSTOM_CCP_MAP CCM 
               ON CCM.CUSTOM_VIEW_DETAILS_SID = CRB.CUSTOM_VIEW_DETAILS_SID 
                  AND CCM.CUSTOM_HIERARCHY_NO = CRB.CUSTOM_HIERARCHY_NO 
       INNER JOIN CUSTOM_VIEW_DETAILS CVD 
               ON CVD.CUSTOM_VIEW_DETAILS_SID = CRB.CUSTOM_VIEW_DETAILS_SID 
       INNER JOIN CUSTOM_VIEW_MASTER CVM 
               ON CVM.CUSTOM_VIEW_MASTER_SID = CVD.CUSTOM_VIEW_MASTER_SID 
       INNER JOIN RELATIONSHIP_LEVEL_DEFINITION RLD 
               ON RLD.HIERARCHY_LEVEL_DEFINITION_SID = CVD.HIERARCHY_ID 
       JOIN #TEMP T 
         ON T.CCP_DETAILS_SID = CCM.CCP_DETAILS_SID 
WHERE  CVM.PROJECTION_MASTER_SID = @PROJECTION_SID 
       AND CVD.CUSTOM_VIEW_MASTER_SID = @CUSTOM_VIEW_MASTER_SID 
                AND ( CVD.LEVEL_NO = @LEVEL_NO 
               OR @LEVEL_NO IS NULL 
                   OR @LEVEL_NO = 0 ) 
DECLARE @CON BIT

          IF EXISTS(SELECT 1 FROM #LEVEL_DETAILS  WHERE LEVEL_NAME IN ('Trading Partner','Company','Customer'))
          BEGIN
          SET @CON=0
           END
              ELSE 
              BEGIN
             SET @CON=1
           END


DECLARE @I INT=1 

IF OBJECT_ID('TEMPDB..#TEMP_DATA') IS NOT NULL 
  DROP TABLE #TEMP_DATA 

CREATE TABLE #TEMP_DATA 
  ( 
     CCP_DETAILS_SID           INT, 
     CUSTOM_HIERARCHY_NO       VARCHAR(50), 
     LEVEL_NAME                VARCHAR(50), 
     LEVEL_NO                  INT, 
     RELATIONSHIP_LEVEL_VALUES VARCHAR(50) 
  ) 

DECLARE @SQL NVARCHAR(MAX) = '' 


SELECT @LEVEL_NO = MAX(LEVEL_NO) 
FROM   #LEVEL_DETAILS 

SELECT TOP 1 @TRADING_LEVEL=LEVEL_NO FROM #LEVEL_DETAILS WHERE LEVEL_NAME IN ('Trading Partner','Company','Customer') order by LEVEL_NAME


SET @SQL=';WITH CTE AS 
          (SELECT CCP_DETAILS_SID,
                        CUSTOM_HIERARCHY_NO,
                             LEVEL_NAME,
                             LEVEL_NO,
                             RELATIONSHIP_LEVEL_VALUES 
                 FROM #LEVEL_DETAILS),' 

WHILE ( @I <= @LEVEL_NO ) 
  BEGIN  
      SET @SQL_UNION+=' SELECT * FROM '+' LEVEL_' + CONVERT(VARCHAR(10), @I)+' UNION ALL'
      SET @SQL+= ' LEVEL_' + CONVERT(VARCHAR(10), @I) + ' AS 
         (SELECT * FROM CTE WHERE LEVEL_NO=' + CONVERT(VARCHAR(10), @I) + ')' + IIF(@I = @LEVEL_NO, '', ',') 
      SET @I+=1 
  END 
SET @SQL_UNION+=' SELECT * FROM ADD_LEVEL'
SET @SQL+=', ADD_LEVEL AS ('+CASE WHEN @TRADING_LEVEL>1 THEN  
                           ' SELECT DISTINCT 
                                  0 AS CCP_DETAILS_SID,
                                  CUSTOM_HIERARCHY_NO+''0.'' AS CUSTOM_HIERARCHY_NO,
                                  ''ALL PPA GROUP'' AS LEVEL_NAME,
                                  0 AS LEVEL_NO,
                                  ''ALL PPA GROUP'' AS RELATIONSHIP_LEVEL_VALUES
                     FROM CTE WHERE LEVEL_NO='+ CONVERT(VARCHAR(10), @TRADING_LEVEL-1) 
ELSE ' SELECT 0 AS CCP_DETAILS_SID,
''0.'' AS CUSTOM_HIERARCHY_NO,
''ALL PPA GROUP'' AS LEVEL_NAME,
0 AS LEVEL_NO,
''ALL PPA GROUP'' AS RELATIONSHIP_LEVEL_VALUES' END + ') ,ALLL AS ('+@SQL_UNION +') '+CASE WHEN @CON=1 THEN 'SELECT * FROM CTE' ELSE CASE WHEN @TRADING_LEVEL>1 THEN 
'SELECT CCP_DETAILS_SID,CUSTOM_HIERARCHY_NO,LEVEL_NAME,LEVEL_NO,
                                  RELATIONSHIP_LEVEL_VALUES
FROM (SELECT * FROM LEVEL_'+CONVERT(VARCHAR(10),@TRADING_LEVEL-1)+' UNION ALL SELECT * FROM ADD_LEVEL UNION ALL '+
'SELECT CCP_DETAILS_SID,SUBSTRING(CUSTOM_HIERARCHY_NO,1,DBO.INSTR(CUSTOM_HIERARCHY_NO, ''.'', 1, '+CONVERT(VARCHAR(10),@TRADING_LEVEL-1)+'))+''0''
                        +SUBSTRING(CUSTOM_HIERARCHY_NO,DBO.INSTR(CUSTOM_HIERARCHY_NO, ''.'', 1, '+CONVERT(VARCHAR(10),@TRADING_LEVEL-1)+'),
                                         LEN(CUSTOM_HIERARCHY_NO)-DBO.INSTR(CUSTOM_HIERARCHY_NO, ''.'', 1, '+CONVERT(VARCHAR(10),@TRADING_LEVEL-1)+')+1)  AS CUSTOM_HIERARCHY_NO,
                                  LEVEL_NAME,
                                  LEVEL_NO,
                                  RELATIONSHIP_LEVEL_VALUES 
  FROM ALLL WHERE LEVEL_NO>'+CONVERT(VARCHAR(10),@TRADING_LEVEL-1)+' AND LEVEL_NO<>0)A ORDER BY CAST(REPLACE(CUSTOM_HIERARCHY_NO,''.'',''0'') AS BIGINT)' 
  ELSE 'SELECT * FROM ADD_LEVEL UNION ALL 
   SELECT CCP_DETAILS_SID,SUBSTRING(CUSTOM_HIERARCHY_NO,1,DBO.INSTR(CUSTOM_HIERARCHY_NO, ''.'', 1, '+CONVERT(VARCHAR(10),@TRADING_LEVEL-1)+'))+''0''
                        +SUBSTRING(CUSTOM_HIERARCHY_NO,DBO.INSTR(CUSTOM_HIERARCHY_NO, ''.'', 1, '+CONVERT(VARCHAR(10),@TRADING_LEVEL-1)+'),
                                         LEN(CUSTOM_HIERARCHY_NO)-DBO.INSTR(CUSTOM_HIERARCHY_NO, ''.'', 1, '+CONVERT(VARCHAR(10),@TRADING_LEVEL-1)+')+1)  AS CUSTOM_HIERARCHY_NO,
                                  LEVEL_NAME,LEVEL_NO,
                                  RELATIONSHIP_LEVEL_VALUES FROM ALLL WHERE LEVEL_NO<>0' 
END
END
--PRINT @SQL 
---------------ccp were categorised based on the hierarchy no ----------------------------
        INSERT INTO @CCP
                        (CCP_DETAILS_SID,
               HIERARCHY_NO,
                LEVEL_NAME,
                           LEVEL_NO,
                           RELATIONSHIP_LEVEL_VALUES)
EXEC sp_executesql @SQL 



      --SELECT DISTINCT CCM.CCP_DETAILS_SID, 
      --                CRB.CUSTOM_HIERARCHY_NO, 
      --                RLD.LEVEL_NAME 
      --FROM   CUSTOM_RELATIONSHIP_BUILDER CRB 
      --       INNER JOIN CUSTOM_CCP_MAP CCM 
      --               ON CCM.CUSTOM_VIEW_DETAILS_SID = 
      --                  CRB.CUSTOM_VIEW_DETAILS_SID 
      --                  AND CCM.CUSTOM_HIERARCHY_NO = CRB.CUSTOM_HIERARCHY_NO 
      --       INNER JOIN CUSTOM_VIEW_DETAILS CVD 
      --               ON CVD.CUSTOM_VIEW_DETAILS_SID = 
      --                  CRB.CUSTOM_VIEW_DETAILS_SID 
      --       INNER JOIN CUSTOM_VIEW_MASTER CVM 
      --               ON CVM.CUSTOM_VIEW_MASTER_SID = CVD.CUSTOM_VIEW_MASTER_SID 
      --       INNER JOIN RELATIONSHIP_LEVEL_DEFINITION RLD 
      --               ON RLD.HIERARCHY_LEVEL_DEFINITION_SID = CVD.HIERARCHY_ID 
      --WHERE  CVM.PROJECTION_MASTER_SID = @PROJECTION_SID 
      --       AND CVD.CUSTOM_VIEW_MASTER_SID = @CUSTOM_VIEW_MASTER_SID 
      --       AND ( CVD.LEVEL_NO = @LEVEL_NO 
      --              OR @LEVEL_NO IS NULL 
      --              OR @LEVEL_NO = 0 ) 
  END 


    --IF OBJECT_ID('TEMPDB..#TEMP_CCP') IS NOT NULL 
    --  DROP TABLE #TEMP_CCP 

    --CREATE TABLE #TEMP_CCP 
    --  ( 
    --     COMPANY_MASTER_SID     INT, 
    --     CONTRACT_MASTER_SID    INT, 
    --     ITEM_MASTER_SID        INT, 
    --     PROJECTION_DETAILS_SID INT, 
    --     PROJECTION_MASTER_SID  INT, 
    --     HIERARCHY_NO           VARCHAR(100), 
    --     LEVEL_NAME             VARCHAR(100) 
    --  ) 

    --INSERT INTO #TEMP_CCP 
    --            (COMPANY_MASTER_SID, 
    --             CONTRACT_MASTER_SID, 
    --             ITEM_MASTER_SID, 
    --             PROJECTION_DETAILS_SID, 
    --             PROJECTION_MASTER_SID, 
    --             HIERARCHY_NO, 
    --             LEVEL_NAME) 
    --SELECT CCP.COMPANY_MASTER_SID, 
    --       CCP.CONTRACT_MASTER_SID, 
    --       CCP.ITEM_MASTER_SID, 
    --       PD.PROJECTION_DETAILS_SID, 
    --       PM.PROJECTION_MASTER_SID, 
    --       HIERARCHY_NO, 
    --       LEVEL_NAME 
    --FROM   CCP_DETAILS CCP 
    --       INNER JOIN PROJECTION_DETAILS PD 
    --               ON CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID 
    --       INNER JOIN PROJECTION_MASTER PM 
    --               ON PD.PROJECTION_MASTER_SID = PM.PROJECTION_MASTER_SID 
    --       INNER JOIN @CCP C 
    --               ON C.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID 
    --WHERE  PM.PROJECTION_MASTER_SID = @FIRST_PROJ_SID 

-------------------the following will be the table which results in hierarchy no period wise table------------------------
    IF OBJECT_ID('TEMPDB..#DATA_TABLE1') IS NOT NULL 
      DROP TABLE #DATA_TABLE1 

    SELECT HIERARCHY_NO, 
           LEVEL_NAME, 
                 LEVEL_NO,
                 RELATIONSHIP_LEVEL_VALUES ,
           PERIOD, 
           YEAR 
    INTO   #DATA_TABLE1 
    FROM   (SELECT DISTINCT HIERARCHY_NO, 
                            LEVEL_NAME,
                                                LEVEL_NO,
                                                RELATIONSHIP_LEVEL_VALUES 
            FROM   @CCP) C 
           CROSS JOIN (SELECT DISTINCT PERIOD, 
                                       YEAR 
                       FROM   #PERIOD 
                       WHERE  PERIOD_SID BETWEEN COALESCE(@ACT_START_PERIOD_SID, 
                                                 @PIVOT_START_PERIOD_SID) AND 
                                                         @PROJ_END_PERIOD_SID) P 
                                                                                                  ORDER BY HIERARCHY_NO, 
           LEVEL_NAME, 
                 LEVEL_NO,
                 RELATIONSHIP_LEVEL_VALUES ,P.YEAR,P.PERIOD

----------------based on the hierarchy number ccp were grouped----------------------------------------
    IF OBJECT_ID('TEMPDB..#CCP_INFO1') IS NOT NULL 
      DROP TABLE #CCP_INFO1 

    SELECT PD.PROJECTION_MASTER_SID, 
           PD.PROJECTION_DETAILS_SID, 
           PD.CCP_DETAILS_SID, 
           C.HIERARCHY_NO, 
           C.LEVEL_NAME,C.LEVEL_NO,
                 C.RELATIONSHIP_LEVEL_VALUES
    INTO   #CCP_INFO1 
    FROM   PROJECTION_DETAILS PD 
           INNER JOIN @CCP C 
                   ON C.CCP_DETAILS_SID = PD.CCP_DETAILS_SID 
    WHERE  PROJECTION_MASTER_SID = @FIRST_PROJ_SID 
-----------taking values from the respective projection table ccp, rs and period wise in a temp table--------------
      
    IF OBJECT_ID('TEMPDB..#NM_PPA_PROJECTION1') IS NOT NULL 
      DROP TABLE #NM_PPA_PROJECTION1 
	  CREATE TABLE #NM_PPA_PROJECTION1
  (
  PROJECTION_DISCOUNT_DOLLAR        NUMERIC(22,6),
  PPA_RPU_PROJECTED					NUMERIC(22,6),
  PPA_PROJECTION_SALES1				NUMERIC(22,6),
  PPA_PROJECTION_UNITS1				NUMERIC(22,6),
  PERIOD							VARCHAR(50),
  YEAR								INT,
   RS_NAME							 VARCHAR(50),
  HIERARCHY_NO						 VARCHAR(100), 
  
  LEVEL_NAME						VARCHAR(100),
  LEVEL_NO							INT,
  RELATIONSHIP_LEVEL_VALUES         VARCHAR(100)
 
  )

  SET @D_SQL=CONCAT(' INSERT INTO #NM_PPA_PROJECTION1
  (
   PROJECTION_DISCOUNT_DOLLAR    ,
   PPA_RPU_PROJECTED			,
   PPA_PROJECTION_SALES1		,
   PPA_PROJECTION_UNITS1		,
   PERIOD						 ,
   YEAR							 ,
    RS_NAME						 ,
   HIERARCHY_NO					 ,
   								 
   LEVEL_NAME					 ,
   LEVEL_NO						 ,
   RELATIONSHIP_LEVEL_VALUES    
  )
    SELECT SUM(NSP.PROJECTION_DISCOUNT_DOLLAR) PROJECTION_DISCOUNT_DOLLAR, 
           PPA_RPU_PROJECTED = SUM(NSP.PROJECTION_RATE), 
           PPA_PROJECTION_SALES1 = SUM(NS.PROJECTION_SALES), 
           PPA_PROJECTION_UNITS1 = SUM(NS.PROJECTION_UNITS), 
           PERIOD, 
           YEAR, 
           HIERARCHY_NO, 
           LEVEL_NAME,
                 LEVEL_NO,
                 RELATIONSHIP_LEVEL_VALUES
   
    FROM   ',@P_PROJECTION_TABLE,' NSP 
           INNER JOIN ',@P_MASTER_TABLE,' NSPP 
                   ON NSP.PROJECTION_DETAILS_SID = NSPP.PROJECTION_DETAILS_SID
                      --AND NSPP.RS_MODEL_SID = NSP.RS_MODEL_SID 
					  AND NSPP.RS_CONTRACT_SID = NSP.RS_CONTRACT_SID 

           INNER JOIN ',@S_MASTER_TABLE,' NS 
                   ON NSP.PROJECTION_DETAILS_SID = NS.PROJECTION_DETAILS_SID 
                      
                      AND NSP.PERIOD_SID = NS.PERIOD_SID 
           --INNER JOIN RS_MODEL R 
           --        ON NSPP.RS_MODEL_SID = R.RS_MODEL_SID
		   INNER JOIN RS_CONTRACT R 
                   ON NSPP.RS_CONTRACT_SID = R.RS_CONTRACT_SID  				   
           INNER JOIN #CCP_INFO1 C 
                   ON C.PROJECTION_DETAILS_SID = NSP.PROJECTION_DETAILS_SID 
                      AND NSP.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND 
                                                 @PROJ_END_PERIOD_SID 
           INNER JOIN #PERIOD P 
                   ON P.PERIOD_SID = NSP.PERIOD_SID 
                     
    GROUP  BY HIERARCHY_NO, 
              LEVEL_NAME, 
                          LEVEL_NO,
                 RELATIONSHIP_LEVEL_VALUES,
              PERIOD, 
              YEAR ')
			  EXEC sp_executesql @D_SQL
-----------taking values from the respective actual table ccp, rs and period wise in a temp table--------------
      			  
    IF OBJECT_ID('TEMPDB..#NM_ACTUAL_PPA1') IS NOT NULL 
      DROP TABLE #NM_ACTUAL_PPA1 
	  
	CREATE TABLE #NM_ACTUAL_PPA1
  (
  ACTUAL_DISCOUNT_DOLLAR        NUMERIC(22,6),
  PPA_RPU_ACTUAL					NUMERIC(22,6),
  PPA_ACTUAL_SALES1				NUMERIC(22,6),
  PPA_ACTUAL_UNITS1				NUMERIC(22,6),
  PERIOD							VARCHAR(50),
  YEAR								INT,
   RS_NAME							 VARCHAR(50),
  HIERARCHY_NO						 VARCHAR(100), 
  
  LEVEL_NAME						VARCHAR(100),
   LEVEL_NO							INT,
  RELATIONSHIP_LEVEL_VALUES         VARCHAR(100)

 
  )
  SET @D_SQL=CONCAT(' INSERT INTO #NM_ACTUAL_PPA1
  (
   ACTUAL_DISCOUNT_DOLLAR,
   PPA_RPU_ACTUAL		,
   PPA_ACTUAL_SALES1	,
   PPA_ACTUAL_UNITS1	,
   PERIOD					 ,
   YEAR						 ,
     RS_NAME					 ,
   HIERARCHY_NO				 ,
  
   LEVEL_NAME,
   LEVEL_NO,
                 RELATIONSHIP_LEVEL_VALUES				
  )

    SELECT SUM(NAD.ACTUAL_DISCOUNT_DOLLAR) ACTUAL_DISCOUNT_DOLLAR, 
           PPA_RPU_ACTUAL = SUM(NAD.ACTUAL_RATE), 
           PPA_ACTUAL_SALES1 = SUM(NS.ACTUAL_SALES), 
           PPA_ACTUAL_UNITS1 = SUM(NS.ACTUAL_UNITS), 
           PERIOD, 
           YEAR,
           HIERARCHY_NO, 
           LEVEL_NAME,
                    LEVEL_NO,
                 RELATIONSHIP_LEVEL_VALUES

    FROM  ',@P_ACTUAL_TABLE,'  NAD 
           INNER JOIN ',@P_MASTER_TABLE,' NSPP 
                   ON NAD.PROJECTION_DETAILS_SID = NSPP.PROJECTION_DETAILS_SID 
                     
                      --AND NSPP.RS_MODEL_SID = NAD.RS_MODEL_SID 
					  AND NSPP.RS_CONTRACT_SID = NAD.RS_CONTRACT_SID 
           INNER JOIN ',@S_ACTUAL_TABLE,' NS 
                   ON NAD.PROJECTION_DETAILS_SID = NS.PROJECTION_DETAILS_SID 
                      AND NAD.PERIOD_SID = NS.PERIOD_SID 
           --INNER JOIN RS_MODEL R 
           --       ON NSPP.RS_MODEL_SID = R.RS_MODEL_SID   
           INNER JOIN RS_CONTRACT R 
                   ON NSPP.RS_CONTRACT_SID = R.RS_CONTRACT_SID
           INNER JOIN #CCP_INFO1 C 
                   ON C.PROJECTION_DETAILS_SID = NAD.PROJECTION_DETAILS_SID 
                      
                     AND NAD.PERIOD_SID BETWEEN COALESCE(',@ACT_START_PERIOD_SID,',', @PROJ_START_PERIOD_SID,')
		AND ',@ACT_END_PERIOD_SID,'
           INNER JOIN #PERIOD P 
                   ON P.PERIOD_SID = NAD.PERIOD_SID 
    GROUP  BY HIERARCHY_NO, 
              LEVEL_NAME, 
                          LEVEL_NO,
                 RELATIONSHIP_LEVEL_VALUES,
              PERIOD, 
              YEAR ')
			  EXEC sp_executesql @D_SQL
 -----------taking sales projection values from the respective projection table ccp period wise in a temp table belong to a hierarchy no--------------
 			  
    IF OBJECT_ID('TEMPDB..#NM_SALES_PROJECTION1') IS NOT NULL 
      DROP TABLE #NM_SALES_PROJECTION1 
CREATE TABLE #NM_SALES_PROJECTION1
  (
  PROJECTION_SALES        NUMERIC(22,6),
  PROJECTION_UNITS					NUMERIC(22,6),
  HIERARCHY_NO						 VARCHAR(100), 

  LEVEL_NAME						VARCHAR(100),
   LEVEL_NO							INT,
  RELATIONSHIP_LEVEL_VALUES         VARCHAR(100),
  PERIOD							VARCHAR(50),
  YEAR								INT,
  
 
  )
SET @D_SQL=CONCAT('INSERT INTO #NM_SALES_PROJECTION1
(
  PROJECTION_SALES  ,
  PROJECTION_UNITS	,
   HIERARCHY_NO, 
              LEVEL_NAME, 
                          LEVEL_NO,
                 RELATIONSHIP_LEVEL_VALUES,
              PERIOD, 
              YEAR 	
)

    SELECT SUM(PROJECTION_SALES) PROJECTION_SALES, 
           SUM(PROJECTION_UNITS) PROJECTION_UNITS , 
           HIERARCHY_NO, 
              LEVEL_NAME, 
                          LEVEL_NO,
                 RELATIONSHIP_LEVEL_VALUES,
              PERIOD, 
              YEAR 
 
    FROM   ',@S_PROJECTION_TABLE,' NSP 
           INNER JOIN #CCP_INFO1 C 
                   ON C.PROJECTION_DETAILS_SID = NSP.PROJECTION_DETAILS_SID 
                       
                    AND NSP.PERIOD_SID BETWEEN', @PROJ_START_PERIOD_SID,'
		AND ',@PROJ_END_PERIOD_SID,'
           INNER JOIN CCP_DETAILS CC 
                   ON C.CCP_DETAILS_SID = CC.CCP_DETAILS_SID 
           INNER JOIN #PERIOD P 
                   ON P.PERIOD_SID = NSP.PERIOD_SID 
    GROUP  BY HIERARCHY_NO, 
              LEVEL_NAME, 
                          LEVEL_NO,
                 RELATIONSHIP_LEVEL_VALUES,
              PERIOD, 
              YEAR ')
			  EXEC sp_executesql @D_SQL
			  
-----------taking sales actual values from the respective actual table ccp period wise in a temp table belong to a hierarchy no--------------

    IF OBJECT_ID('TEMPDB..#NM_ACTUAL_SALES1') IS NOT NULL 
      DROP TABLE #NM_ACTUAL_SALES1 
	  CREATE TABLE #NM_ACTUAL_SALES1
  (
  ACTUAL_SALES						NUMERIC(22,6),
  ACTUAL_UNITS						NUMERIC(22,6),
  
  PERIOD							VARCHAR(50),
  YEAR								INT,
  HIERARCHY_NO						 VARCHAR(100), 

  LEVEL_NAME						VARCHAR(100),
   LEVEL_NO							INT,
  RELATIONSHIP_LEVEL_VALUES         VARCHAR(100),
  
 
  )
   
   
   SET @D_SQL=CONCAT(' INSERT INTO #NM_ACTUAL_SALES1
  (
  
   ACTUAL_SALES	,			
   ACTUAL_UNITS	,			
   
   PERIOD,					
   YEAR	,					
   HIERARCHY_NO	,			
  
   LEVEL_NAME	,			
    LEVEL_NO,					
   RELATIONSHIP_LEVEL_VALUES,  

  ) 
  SELECT SUM(ACTUAL_SALES) ACTUAL_SALES, 
           SUM(ACTUAL_UNITS) ACTUAL_UNITS, 
           PERIOD, 
           YEAR, 
           HIERARCHY_NO, 
           LEVEL_NAME,LEVEL_NO,RELATIONSHIP_LEVEL_VALUES

    FROM   ',@S_ACTUAL_TABLE,' NAD 
           INNER JOIN #CCP_INFO1 C 
                   ON C.PROJECTION_DETAILS_SID = NAD.PROJECTION_DETAILS_SID 
                      
                     AND NAD.PERIOD_SID BETWEEN COALESCE(',@ACT_START_PERIOD_SID,',', @PROJ_START_PERIOD_SID,')
		AND ',@ACT_END_PERIOD_SID,'
           INNER JOIN CCP_DETAILS CC 
                   ON C.CCP_DETAILS_SID = CC.CCP_DETAILS_SID 
           INNER JOIN #PERIOD P 
                   ON P.PERIOD_SID = NAD.PERIOD_SID
    GROUP  BY PERIOD, 
              YEAR, 
              HIERARCHY_NO, 
              LEVEL_NAME,
                       LEVEL_NO,RELATIONSHIP_LEVEL_VALUES')
					   EXEC sp_executesql @D_SQL
-----------------merging the actual and projected sales information in a temp table based on HIERARCHY_NO----------------------------------

    IF OBJECT_ID('TEMPDB..#SALES1') IS NOT NULL 
      DROP TABLE #SALES1 

    SELECT FD.YEAR, 
           FD.PERIOD, 
           FD.HIERARCHY_NO, 
           FD.LEVEL_NAME, 
                 FD.LEVEL_NO,
                 FD.RELATIONSHIP_LEVEL_VALUES,
           COALESCE(( NSP.PROJECTION_SALES ), 0) AS PROJECTION_SALES,  
           COALESCE(( NSP.PROJECTION_UNITS ), 0) AS PROJECTION_UNITS,  
           COALESCE(( NAS.ACTUAL_SALES ), 0) AS ACTUAL_SALES,  
           COALESCE(( NAS.ACTUAL_UNITS ), 0) AS ACTUAL_UNITS,  
           COALESCE(( NAS.ACTUAL_SALES ), 0) AS NM_ACTUAL_SALES, 
           COALESCE(( NSP.PROJECTION_SALES ), 0) AS NM_PROJECTED_SALES,  
           COALESCE(( NAS.ACTUAL_UNITS ), 0) AS NM_ACTUAL_UNITS,  
           COALESCE(( NSP.PROJECTION_UNITS ), 0) AS NM_PROJECTED_UNITS
    INTO   #SALES1 
    FROM   #DATA_TABLE1 FD 
           LEFT JOIN #NM_SALES_PROJECTION1 NSP 
                  ON NSP.HIERARCHY_NO = FD.HIERARCHY_NO 
                     AND NSP.LEVEL_NAME = FD.LEVEL_NAME 
                     AND NSP.YEAR = FD.YEAR 
                     AND NSP.PERIOD = FD.PERIOD 
                                   AND NSP.LEVEL_NO=FD.LEVEL_NO
                                  AND NSP.RELATIONSHIP_LEVEL_VALUES=FD.RELATIONSHIP_LEVEL_VALUES
           LEFT JOIN #NM_ACTUAL_SALES1 NAS 
                  ON NAS.HIERARCHY_NO = FD.HIERARCHY_NO 
                     AND NAS.LEVEL_NAME = FD.LEVEL_NAME 
                     AND NAS.YEAR = FD.YEAR 
                     AND NAS.PERIOD = FD.PERIOD 
                                    AND NAS.LEVEL_NO=FD.LEVEL_NO
                                  AND NAS.RELATIONSHIP_LEVEL_VALUES=FD.RELATIONSHIP_LEVEL_VALUES
-----------------merging the actual and projected ppa information in a temp table based on HIERARCHY_NO----------------------------------
    IF OBJECT_ID('TEMPDB..#PPA_SUPP_MANDATED1') IS NOT NULL 
      DROP TABLE #PPA_SUPP_MANDATED1 

    SELECT FD.YEAR, 
           FD.PERIOD, 
                 FD.HIERARCHY_NO, 
           FD.LEVEL_NAME, 
                 FD.LEVEL_NO,
                 FD.RELATIONSHIP_LEVEL_VALUES,
           COALESCE(( NSP.PROJECTION_DISCOUNT_DOLLAR ), 0) AS PPA_DISCOUNT_PROJECTED, 
           COALESCE(( NAS.ACTUAL_DISCOUNT_DOLLAR ), 0) AS PPA_ACTUAL_SALES,  
           COALESCE(( NSP.PPA_RPU_PROJECTED ), 0) AS PPA_RPU_PROJECTED, 
            COALESCE(( PPA_PROJECTION_SALES1 ), 0) AS PPA_PROJECTION_SALES1,  
           COALESCE(( PPA_PROJECTION_UNITS1 ), 0) AS PPA_PROJECTION_UNITS1, 
           COALESCE(( PPA_ACTUAL_SALES1 ), 0) AS PPA_ACTUAL_SALES1,  
           COALESCE(( PPA_ACTUAL_UNITS1 ), 0) AS PPA_ACTUAL_UNITS1
    INTO   #PPA_SUPP_MANDATED1 
    FROM   #DATA_TABLE1 FD 
           LEFT JOIN #NM_ACTUAL_PPA1 NAS 
                  ON NAS.HIERARCHY_NO = FD.HIERARCHY_NO 
                     AND NAS.LEVEL_NAME = FD.LEVEL_NAME 
                     AND NAS.YEAR = FD.YEAR 
                     AND NAS.PERIOD = FD.PERIOD 
                                    AND NAS.LEVEL_NO=FD.LEVEL_NO
                                    AND NAS.RELATIONSHIP_LEVEL_VALUES=FD.RELATIONSHIP_LEVEL_VALUES
           LEFT JOIN #NM_PPA_PROJECTION1 NSP 
                  ON NSP.HIERARCHY_NO = FD.HIERARCHY_NO 
                     AND NSP.LEVEL_NAME = FD.LEVEL_NAME 
                     AND NSP.YEAR = FD.YEAR 
                     AND NSP.PERIOD = FD.PERIOD 
                                    AND NSP.LEVEL_NO=FD.LEVEL_NO
                                    AND NSP.RELATIONSHIP_LEVEL_VALUES=FD.RELATIONSHIP_LEVEL_VALUES
-----------------based on hierarchy no and period wise the ppa rpu, ppa disocunt amount, ppa rate will be calulated and results will be stared in a temp table ----------------------------------

    IF OBJECT_ID('TEMPDB.DBO.#RESULT', 'U') IS NOT NULL 
      DROP TABLE #RESULT; 

    CREATE TABLE #RESULT 
      ( 
         PROJECTION_MASTER_SID               INT, 
         HIERARCHY_NO                        VARCHAR(100), 
         LEVEL_NAME                          VARCHAR(100), 
               LEVEL_NO INT,
              RELATIONSHIP_LEVEL_VALUES VARCHAR(50),
         CP_INDICATOR                        VARCHAR(10), 
         YEAR                                INT, 
         PERIOD                              INT, 
         ACTUAL_DISCOUNT                      NUMERIC(22, 6), 
         PROJECTED_DISCOUNT           NUMERIC(22, 6), 
         ACTUAL_DISCOUNT_PERCENTAGE           NUMERIC(22, 6), 
         PROJECTED_DISCOUNT_PERCENTAGE NUMERIC(22, 6), 
         ACTUAL_RPU                           NUMERIC(22, 6), 
         PROJECTION_RPU                 NUMERIC(22, 6), 
         ACTUAL_UNITS                        NUMERIC(22, 6), 
         PROJECTED_UNITS                     NUMERIC(22, 6) 
      ) 

    INSERT INTO #RESULT 
                (PROJECTION_MASTER_SID, 
                 HIERARCHY_NO, 
                 LEVEL_NAME, 
                            LEVEL_NO,
                           RELATIONSHIP_LEVEL_VALUES,
                 CP_INDICATOR, 
                 YEAR, 
                 PERIOD, 
                 ACTUAL_DISCOUNT, 
                 PROJECTED_DISCOUNT, 
                 ACTUAL_DISCOUNT_PERCENTAGE, 
                 PROJECTED_DISCOUNT_PERCENTAGE, 
                 ACTUAL_RPU, 
                 PROJECTION_RPU, 
                 ACTUAL_UNITS, 
                 PROJECTED_UNITS) 
    SELECT @FIRST_PROJ_SID, 
           RD.HIERARCHY_NO, 
           RD.LEVEL_NAME, 
                 RD.LEVEL_NO,
                 RD.RELATIONSHIP_LEVEL_VALUES,
           @CP_INDICATOR, 
           PD.YEAR, 
           PD.PERIOD,
           PD.PPA_ACTUAL_SALES, 
           PD.PPA_DISCOUNT_PROJECTED, 
           COALESCE(PD.PPA_ACTUAL_SALES / NULLIF(PD.PPA_ACTUAL_SALES1, 0), 0) * 
           100, 
           COALESCE(PD.PPA_DISCOUNT_PROJECTED / NULLIF(PD.PPA_PROJECTION_SALES1, 
                                                0), 
           0) * 
           100, 
           COALESCE(PD.PPA_ACTUAL_SALES / NULLIF(PD.PPA_ACTUAL_UNITS1, 0), 0), 
           COALESCE(PD.PPA_DISCOUNT_PROJECTED / NULLIF(PD.PPA_PROJECTION_UNITS1, 
                                                0), 
           0), 
           ISNULL(PD.PPA_ACTUAL_UNITS1, 0), 
           ISNULL(PD.PPA_PROJECTION_UNITS1, 0) 
    FROM   #PPA_SUPP_MANDATED1 PD 
           INNER JOIN #SALES1 RD 
                   ON RD.YEAR = PD.YEAR 
                      AND RD.PERIOD = PD.PERIOD 
                      AND RD.HIERARCHY_NO = PD.HIERARCHY_NO 
                      AND RD.LEVEL_NAME = PD.LEVEL_NAME 
                                    AND RD.LEVEL_NO=PD.LEVEL_NO
                                    AND RD.RELATIONSHIP_LEVEL_VALUES=PD.RELATIONSHIP_LEVEL_VALUES


       SELECT DISTINCT PROJECTION_MASTER_SID, 
                 HIERARCHY_NO, 
                 LEVEL_NAME, 
                            LEVEL_NO,
                           RELATIONSHIP_LEVEL_VALUES,
                 CP_INDICATOR, 
                 YEAR, 
                 PERIOD, 
                 ACTUAL_DISCOUNT, 
                 PROJECTED_DISCOUNT, 
                 ACTUAL_DISCOUNT_PERCENTAGE, 
                 PROJECTED_DISCOUNT_PERCENTAGE, 
                 ACTUAL_RPU, 
                 PROJECTION_RPU, 
                 ACTUAL_UNITS, 
                 PROJECTED_UNITS 
          FROM   #RESULT 
          ORDER  BY 
                     HIERARCHY_NO, 
                 RELATIONSHIP_LEVEL_VALUES,
                 LEVEL_NAME, 
                 LEVEL_NO,
                    [YEAR], 
                    PERIOD 
     
END





GO


