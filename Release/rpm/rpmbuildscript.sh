#Scripts Starts

GTNPathName=$1
Build_File_Path=$2
old_rpm=-AppRelease
rpmversion=1.0

rm -rf  $Build_File_Path/rpmbuild
mkdir -p $Build_File_Path/rpmbuild/{BUILD,BUILDROOT,RPMS,SOURCES,SPECS,SRPMS,tmp}

spec_path=$Build_File_Path/rpm/GtnSpecs/
filename=$(basename "$GTNPathName")
spec_name="${filename%.*}"

rpm_build_spec_path=$Build_File_Path/rpmbuild/SPECS/$spec_name.spec

if [[ $GTNPathName == *"GTN-"*"-Setup"* ]]; 
then
echo you have chosen GTN  Setup 
old_rpm=-Setup
cp $spec_path/GTN-x-Setup.spec $rpm_build_spec_path

elif [[ $GTNPathName == *"GTN-"*"-AppRelease"* ]]; 
then
old_rpm=-AppRelease
echo you have chosen GTN  App Release
cp $spec_path/GTN-x-AppRelease.spec $rpm_build_spec_path
elif [[ $GTNPathName == *"GTN-"*"-WsSetup"* ]]; 
then
old_rpm=-WsSetup
echo you have chosen GTN WS Setup 
cp $spec_path/GTN-x-WsSetup.spec $rpm_build_spec_path
elif [[ $GTNPathName == *"GTN-"*"-WsAppRelease"* ]]; 
then
old_rpm=-WsAppRelease
echo you have chosen GTN WS App Release
cp $spec_path/GTN-x-WsAppRelease.spec $rpm_build_spec_path
elif [[ $GTNPathName == *"GTN-"*"-ServiceRelease"* ]]; 
then
old_rpm=-ServiceRelease
echo you have chosen GTN Service Release
cp $spec_path/GTN-x-ServiceRelease.spec $rpm_build_spec_path

else 
echo Invalid input tar
exit 0
fi
#do_Custom_File_Changes

parentdir=$spec_name-1.0
changeddir=${parentdir%-*}$( cat /dev/urandom | tr -dc 'A-Z0-9' | fold -w 5 | head -n 1 )-${parentdir##*-}

if [ -z "$3" ]
  then

cp $GTNPathName $Build_File_Path/rpmbuild/SOURCES/    
sed -i 's/SPEC_NAME/'$spec_name'/g' $rpm_build_spec_path
sed -i 's/SPEC_VERSION/'$rpmversion'/g' $rpm_build_spec_path
sed -i '0,/SPEC_SOURCE/s//'${GTNPathName##*/}'/' $rpm_build_spec_path

else

cp $GTNPathName $Build_File_Path/rpmbuild/SOURCES/
unzip $Build_File_Path/rpmbuild/SOURCES/$spec_name.zip > /dev/null
rm -rf $Build_File_Path/rpmbuild/SOURCES/$spec_name.zip > /dev/null
mv $spec_name-1.0 $changeddir
zip -r -0 $Build_File_Path/rpmbuild/SOURCES/$spec_name.zip $changeddir
rm -rf $changeddir

sed -i 's/SPEC_NAME/'${changeddir%-*}'/g' $rpm_build_spec_path
sed -i 's/SPEC_VERSION/'${changeddir##*-}'/g' $rpm_build_spec_path
sed -i '0,/SPEC_SOURCE/s//'${GTNPathName##*/}'/' $rpm_build_spec_path

fi



rpmbuild --define "_topdir $Build_File_Path/rpmbuild " -bb $rpm_build_spec_path

rm -rf $Build_File_Path/rpm/Release/$old_rpm*

mkdir -p $Build_File_Path/rpm/Release/

cp $Build_File_Path/rpmbuild/RPMS/noarch/* $Build_File_Path/rpm/Release/

echo RPM File Created Successfully  $Build_File_Path/rpm/Release/
