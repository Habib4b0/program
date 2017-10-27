#!/bin/sh
file="Server_Path/etl/Interface_Job/EtlConfiguration.properties"
var2=$(grep -E "ETL_PORT_NO" $file)
#echo $var2
ETL_PORT=`echo ${var2} | cut -d "=" -f2`
echo $ETL_PORT
cd ..
echo "Enter the Password to Encrypt:" 
read pass
curl  http://localhost:$ETL_PORT/BPI_ETL/21232f297a57a5a743894a0e4a801fc3/Encrypt/$pass