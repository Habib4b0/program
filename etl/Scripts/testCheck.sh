#!/bin/sh
mkdir -p lock
touch lock/$1.processing
curl  http://10.4.48.48:8015/BPI_ETL/21232f297a57a5a743894a0e4a801fc3/CompanyMaster
rm -r lock/$1.processing
touch lock/$1.processed
