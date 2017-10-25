if [ -d ".extract" ]; then
rm -rf .extract/
fi
mkdir -p temp 
nohup java -Xms1280m -Xmx1280m -jar R2_ETL.jar -httpPort 8015 > temp/temp 2>&1 &
echo "Service Started Successfully"
