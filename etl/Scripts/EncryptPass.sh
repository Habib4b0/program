#!/bin/sh
cd ..
echo "Enter the Password to Encrypt:" 
read pass
curl  http://localhost:8015/BPI_ETL/21232f297a57a5a743894a0e4a801fc3/Encrypt/$pass