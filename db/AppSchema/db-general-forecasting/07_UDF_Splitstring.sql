if exists (select 'x'
           from   information_schema.routines
           where  routine_name = 'udf_splitstring'
                  and routine_schema = 'dbo')
  begin
      drop function [dbo].[udf_splitstring]
  end

go

create function [dbo].[udf_splitstring] (@tokens    varchar(max),
                                         @delimiter varchar(5))
returns @split table (
  token varchar(200) not null )
as
  begin
      declare @list xml

      select @list = cast('<a>'
                          + replace(@tokens, @delimiter, '</a><a>')
                          + '</a>' as xml)

      insert into @split
                  (token)
      select ltrim(t.value('.', 'varchar(200)')) as data
      from   @list.nodes('/a') as x(t)

      return
  end

go 
