
###Functions Starts
do_Dynamic_File_Changes(){
cd $Build_File_Path/rpmbuild/SOURCES
tar -xvf $Build_File_Path/rpmbuild/SOURCES/${GTNPathName##*/}
rm -rf $Build_File_Path/rpmbuild/SOURCES/*.tar
changeddir=${parentdir%-*}$( cat /dev/urandom | tr -dc 'a-zA-Z0-9' | fold -w 5 | head -n 1 )-${parentdir##*-}
mv $Build_File_Path/rpmbuild/SOURCES/$parentdir $Build_File_Path/rpmbuild/SOURCES/$changeddir
parentdir=$changeddir


chmod 777 $Build_File_Path/rpmbuild/SOURCES/
filename=$Build_File_Path/rpmbuild/SOURCES/$parentdir


cd $Build_File_Path/rpmbuild/SOURCES/
tar -cvf ${GTNPathName##*/} $parentdir
}
###Functions Starts
do_Dynamic_File_Changes_for_ws_jboss(){
cd $Build_File_Path/rpmbuild/SOURCES
tar -xvf $Build_File_Path/rpmbuild/SOURCES/${GTNPathName##*/}
rm -rf $Build_File_Path/rpmbuild/SOURCES/*.tar
changeddir=${parentdir%-*}$( cat /dev/urandom | tr -dc 'a-zA-Z0-9' | fold -w 5 | head -n 1 )-${parentdir##*-}
mv $Build_File_Path/rpmbuild/SOURCES/$parentdir $Build_File_Path/rpmbuild/SOURCES/$changeddir
parentdir=$changeddir
do_Standalone_xml_Config_for_Gtn_framework_server
chmod 777 $Build_File_Path/rpmbuild/SOURCES/
filename=$Build_File_Path/rpmbuild/SOURCES/$parentdir


cd $Build_File_Path/rpmbuild/SOURCES/
tar -cvf ${GTNPathName##*/} $parentdir
}



do_Custom_File_Changes(){
cd $Build_File_Path/rpmbuild/SOURCES
tar -xvf $Build_File_Path/rpmbuild/SOURCES/${GTNPathName##*/}
rm -rf $Build_File_Path/rpmbuild/SOURCES/*.tar
changeddir=${parentdir%-*}$( cat /dev/urandom | tr -dc 'a-zA-Z0-9' | fold -w 5 | head -n 1 )-${parentdir##*-}
mv $Build_File_Path/rpmbuild/SOURCES/$parentdir $Build_File_Path/rpmbuild/SOURCES/$changeddir
parentdir=$changeddir
cd $Build_File_Path/rpmbuild/SOURCES/
tar -cvf ${GTNPathName##*/} $parentdir
sed -i 's|SPEC_Build_Path|'${Build_File_Path}'|g' $Build_File_Path/rpmbuild/SPECS/*.spec
}



#Scripts Starts

GTNPathName=$1
Build_File_Path=$2
old_rpm=Default

#parentdir="${GTNPathName##*/}"
cd ${GTNPathName%/*}
parentdir=`tar -tf ${GTNPathName##*/} | head -1 | cut -f1 -d"/"`
echo $parentdir
echo ${parentdir%-*}
echo ${parentdir##*-}

extension="${GTNPathName##*.}"

rm -rf  $Build_File_Path/rpmbuild
mkdir -p $Build_File_Path/rpmbuild/{BUILD,BUILDROOT,RPMS,SOURCES,SPECS,SRPMS,tmp}
cp $GTNPathName $Build_File_Path/rpmbuild/SOURCES/

if [[ $GTNPathName == *"Application_Server"* ]]; 
then
echo you have chosen Intial Jboss Setup
old_rpm=Application_Server
cp $Build_File_Path/rpmfiles/Server_Specification/Application_Server.spec $Build_File_Path/rpmbuild/SPECS/
do_Dynamic_File_Changes
#do_Dynamic_DB_Build_Change

elif [[ $GTNPathName == *"gtnFrameworkServer"* ]]; 
then
old_rpm=gtnFrameworkServer
echo you have chosen GTN Framework Jboss Setup for Web Service
cp $Build_File_Path/rpmfiles/Server_Specification/gtnFrameworkServer.spec $Build_File_Path/rpmbuild/SPECS/
do_Dynamic_File_Changes_for_ws_jboss
else
echo you have chosen Deploy Application War
cp $Build_File_Path/rpmfiles/Server_Specification/Default_War.spec $Build_File_Path/rpmbuild/SPECS/
do_Custom_File_Changes
echo "File Type::"$1
fi

sed -i 's/SPEC_NAME/'${parentdir%-*}'/g' $Build_File_Path/rpmbuild/SPECS/*.spec
sed -i 's/SPEC_VERSION/'${parentdir##*-}'/g' $Build_File_Path/rpmbuild/SPECS/*.spec
sed -i '0,/SPEC_SOURCE/s//'${GTNPathName##*/}'/' $Build_File_Path/rpmbuild/SPECS/*.spec

rpmbuild --define "_topdir $Build_File_Path/rpmbuild " -bb $Build_File_Path/rpmbuild/SPECS/*.spec

rm -rf $Build_File_Path/rpmfiles/Output_RPM/$old_rpm*

cp $Build_File_Path/rpmbuild/RPMS/noarch/* $Build_File_Path/rpmfiles/Output_RPM/

echo RPM File Created Successfully  $Build_File_Path/rpmfiles/Output_RPM/
