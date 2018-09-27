#!/bin/sh
file="Server_Path/etl/Interface_Job/EtlConfiguration.properties"
var2=$(grep -E "ETL_PORT_NO" $file)
#echo $var2
ETL_PORT=`echo ${var2} | cut -d "=" -f2`
#echo $ETL_PORT
curl  http://localhost:$ETL_PORT/BPI_ETL/21232f297a57a5a743894a0e4a801fc3/DtxContractPPInterface