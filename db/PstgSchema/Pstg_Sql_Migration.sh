#/bin/sh

file="./PstgDatabase.properties"

if [ -f "$file" ]
then
  echo "$file found."

  while IFS='=' read -r key value
  do
    key=$(echo $key | tr '.' '_')
    eval "${key}='${value}'"
  done < "$file"
    BPI_HOST=${PSTG_HOST}
     BPI_DB=${PSTG_DB}
     BPI_USER=${PSTG_USER}
     BPI_PASSWORD=${PSTG_PASSWORD}
else
  echo "$file not found."
fi
echo $PSTG_HOST
echo $PSTG_DB
echo $PSTG_PASSWORD
echo $PSTG_USER
#set timehour=%time:~0,2%
#echo "The current working directory: $PWD"

x=$(find ./SqlScript/ -type f -name "*.sql" -o -name "*.sh" |sort)


for  filename in $x

do
DATE=`date +%Y-%m-%d:%H:%M:%S`
LOGDATE=`date +%Y-%m-%d`
#echo $DATE
echo "$filename.."
echo "INFO $DATE Executing file $filename...">>Log_$LOGDATE.log
sqlcmd  -b -I -U $PSTG_USER -P $PSTG_PASSWORD -S $PSTG_HOST -d $PSTG_DB -i $filename  -b -m-1 1>>Log_$LOGDATE.log 
#-r1 2> Log_$LOGDATE.log 1> Log_$LOGDATE.log
if [ $? -eq 1 ]
then
echo "ERROR $DATE Error in Executing file $filename...">>Log_$LOGDATE.log
else 
echo "INFO $DATE Successfully Executed file $filename...">>Log_$LOGDATE.log
fi
done

