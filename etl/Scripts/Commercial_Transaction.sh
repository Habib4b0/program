#!/bin/sh
file="Server_Path/etl/Interface_Job/EtlConfiguration.properties"
var2=$(grep -E "ETL_PORT_NO" $file)
#echo $var2
ETL_PORT=`echo ${var2} | cut -d "=" -f2`
#echo $ETL_PORT
curl  http://localhost:$ETL_PORT/BPI_ETL/21232f297a57a5a743894a0e4a801fc3/ReturnsInterface
curl  http://localhost:$ETL_PORT/BPI_ETL/21232f297a57a5a743894a0e4a801fc3/ReturnReserveInterface
curl  http://localhost:$ETL_PORT/BPI_ETL/21232f297a57a5a743894a0e4a801fc3/MasterDataAttrb
curl  http://localhost:$ETL_PORT/BPI_ETL/21232f297a57a5a743894a0e4a801fc3/LotMaster
curl  http://localhost:$ETL_PORT/BPI_ETL/21232f297a57a5a743894a0e4a801fc3/ItemHierarchy
curl  http://localhost:$ETL_PORT/BPI_ETL/21232f297a57a5a743894a0e4a801fc3/ItemHierarchyDefn
curl  http://localhost:$ETL_PORT/BPI_ETL/21232f297a57a5a743894a0e4a801fc3/InventoryProjectedSummary
curl  http://localhost:$ETL_PORT/BPI_ETL/21232f297a57a5a743894a0e4a801fc3/InventoryProjectedDetail
curl  http://localhost:$ETL_PORT/BPI_ETL/21232f297a57a5a743894a0e4a801fc3/InventoryActualSummary
curl  http://localhost:$ETL_PORT/BPI_ETL/21232f297a57a5a743894a0e4a801fc3/InventoryActualDetail
curl  http://localhost:$ETL_PORT/BPI_ETL/21232f297a57a5a743894a0e4a801fc3/GL_Posting
curl  http://localhost:$ETL_PORT/BPI_ETL/21232f297a57a5a743894a0e4a801fc3/GLCostCenter
curl  http://localhost:$ETL_PORT/BPI_ETL/21232f297a57a5a743894a0e4a801fc3/GLBalance
curl  http://localhost:$ETL_PORT/BPI_ETL/21232f297a57a5a743894a0e4a801fc3/FormulaDetails
curl  http://localhost:$ETL_PORT/BPI_ETL/21232f297a57a5a743894a0e4a801fc3/ForecastingSales
curl  http://localhost:$ETL_PORT/BPI_ETL/21232f297a57a5a743894a0e4a801fc3/DemandForecast
curl  http://localhost:$ETL_PORT/BPI_ETL/21232f297a57a5a743894a0e4a801fc3/DemandActual
curl  http://localhost:$ETL_PORT/BPI_ETL/21232f297a57a5a743894a0e4a801fc3/CustomerGtsForecastInterface
curl  http://localhost:$ETL_PORT/BPI_ETL/21232f297a57a5a743894a0e4a801fc3/CustomerGtsActualInterface
curl  http://localhost:$ETL_PORT/BPI_ETL/21232f297a57a5a743894a0e4a801fc3/CPIIndex
curl  http://localhost:$ETL_PORT/BPI_ETL/21232f297a57a5a743894a0e4a801fc3/BestPrice
curl  http://localhost:$ETL_PORT/BPI_ETL/21232f297a57a5a743894a0e4a801fc3/AverageShelfLife
curl  http://localhost:$ETL_PORT/BPI_ETL/21232f297a57a5a743894a0e4a801fc3/AdjustedDemandForecastInterface
curl  http://localhost:$ETL_PORT/BPI_ETL/21232f297a57a5a743894a0e4a801fc3/AdjustedDemandActualInterface
curl  http://localhost:$ETL_PORT/BPI_ETL/21232f297a57a5a743894a0e4a801fc3/ActualMaster
curl  http://localhost:$ETL_PORT/BPI_ETL/21232f297a57a5a743894a0e4a801fc3/SalesMaster
