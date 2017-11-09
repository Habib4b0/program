#Scripts Starts

GTNPathName=$1
Build_File_Path=$2
old_rpm=-AppRelease
rpmversion=1.0

rm -rf  $Build_File_Path/rpmbuild
mkdir -p $Build_File_Path/rpmbuild/{BUILD,BUILDROOT,RPMS,SOURCES,SPECS,SRPMS,tmp}
cp $GTNPathName $Build_File_Path/rpmbuild/SOURCES/

spec_path=$Build_File_Path/rpmfiles/GTN_Specs/
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


sed -i 's/SPEC_NAME/'$spec_name'/g' $rpm_build_spec_path
sed -i 's/SPEC_VERSION/'$rpmversion'/g' $rpm_build_spec_path
sed -i '0,/SPEC_SOURCE/s//'${GTNPathName##*/}'/' $rpm_build_spec_path

rpmbuild --define "_topdir $Build_File_Path/rpmbuild " -bb $rpm_build_spec_path

rm -rf $Build_File_Path/rpmfiles/Output_RPM/$old_rpm*

mkdir -p $Build_File_Path/rpmfiles/Output_RPM/

cp $Build_File_Path/rpmbuild/RPMS/noarch/* $Build_File_Path/rpmfiles/Output_RPM/

echo RPM File Created Successfully  $Build_File_Path/rpmfiles/Output_RPM/
