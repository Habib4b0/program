---------------------------------------------SERVICE_REGISTRY---------------------------------------------

IF NOT EXISTS ( SELECT 'X'
                FROM     INFORMATION_SCHEMA.TABLES
                WHERE    TABLE_NAME = 'SERVICE_REGISTRY'
                         AND TABLE_SCHEMA = 'DBO')
BEGIN
CREATE TABLE [dbo].[SERVICE_REGISTRY]
    (
        [WEB_SERVICE_END_POINT_URL]     VARCHAR(100) NOT NULL,
        [REGISTERED_WEB_CONTEXT]        VARCHAR(100) NOT NULL,
    )
END

GO
