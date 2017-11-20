%global _enable_debug_package 0
%global debug_package %{nil}
%global __os_install_post /usr/lib/rpm/brp-compress %{nil}
%define _binary_payload w6.gzdio
%global _binaries_in_noarch_packages_terminate_build 0
Name:SPEC_NAME
Version:SPEC_VERSION
Release:        1%{?dist}
Summary:Raja1 test
BuildArchitectures: noarch
License:  GPLv3+
Source0:SPEC_SOURCE
Prefix:/opt/bpigtn
BuildArch: noarch
%description
Test description

%prep

%setup -q


%build
echo "Inside Build Method"

%install
echo installing
echo $RPM_BUILD_ROOT
echo $RPM_BUILD_DIR
#mkdir -p $RPM_BUILD_ROOT%{prefix}
folderexist="$RPM_BUILD_DIR/$RPM_PACKAGE_NAME-$RPM_PACKAGE_VERSION/"

 
if [ -d "$folderexist"ETL_Build ]
then
mkdir -p  $RPM_BUILD_ROOT%{prefix}/etl/staging
cp -R $RPM_BUILD_DIR/$RPM_PACKAGE_NAME-$RPM_PACKAGE_VERSION/ETL_Build/* $RPM_BUILD_ROOT%{prefix}/etl/
fi
if [ -d "$folderexist"Conf ]
then
mkdir -p  $RPM_BUILD_ROOT%{prefix}/conf
cp -R $RPM_BUILD_DIR/$RPM_PACKAGE_NAME-$RPM_PACKAGE_VERSION/Conf/* $RPM_BUILD_ROOT%{prefix}/conf/
fi

rm -rf %{prefix}/DB_Script/*
if [ -d "$folderexist"DB_Script ]
then
echo "======================================Inside DB Script====================================================="
mkdir -p  $RPM_BUILD_ROOT%{prefix}/DB_Script/*
cp -R  $RPM_BUILD_DIR/$RPM_PACKAGE_NAME-$RPM_PACKAGE_VERSION/DB_Script/*  $RPM_BUILD_ROOT%{prefix}/DB_Script/
echo "=======================================DB_SCRIPT==========================================================="
fi

if [ -d "$folderexist"Application_Build ]
then
mkdir -p  $RPM_BUILD_ROOT%{prefix}/jboss-7.1.1/applicationProperties
mkdir -p  $RPM_BUILD_ROOT%{prefix}/tempdeploy
mkdir -p  $RPM_BUILD_ROOT%{prefix}/jboss-7.1.1/modules/common/service/partone/main
mkdir -p  $RPM_BUILD_ROOT%{prefix}/jboss-7.1.1/modules/common/service/parttwo/main
if [ -e cp $RPM_BUILD_DIR/$RPM_PACKAGE_NAME-$RPM_PACKAGE_VERSION/Application_Build/gtnProperties.jar ]
then
  cp $RPM_BUILD_DIR/$RPM_PACKAGE_NAME-$RPM_PACKAGE_VERSION/Application_Build/gtnProperties.jar $RPM_BUILD_ROOT%{prefix}/jboss-7.1.1/applicationProperties/
chmod -R 777 $RPM_BUILD_ROOT%{prefix}/jboss-7.1.1/applicationProperties/gtnProperties.jar
fi
if [ -e $RPM_BUILD_DIR/$RPM_PACKAGE_NAME-$RPM_PACKAGE_VERSION/Application_Build/gtnServicePartI-1.0.jar ]
then
  cp $RPM_BUILD_DIR/$RPM_PACKAGE_NAME-$RPM_PACKAGE_VERSION/Application_Build/gtnServicePartI-1.0.jar $RPM_BUILD_ROOT%{prefix}/jboss-7.1.1/modules/common/service/partone/main
chmod -R 777 $RPM_BUILD_ROOT%{prefix}/jboss-7.1.1/modules/common/service/partone/main/gtnServicePartI-1.0.jar
fi
if [ -e $RPM_BUILD_DIR/$RPM_PACKAGE_NAME-$RPM_PACKAGE_VERSION/Application_Build/gtnServicePartII-1.0.jar ]
then
cp $RPM_BUILD_DIR/$RPM_PACKAGE_NAME-$RPM_PACKAGE_VERSION/Application_Build/gtnServicePartII-1.0.jar $RPM_BUILD_ROOT%{prefix}/jboss-7.1.1/modules/common/service/parttwo/main
chmod -R 777 $RPM_BUILD_ROOT%{prefix}/jboss-7.1.1/modules/common/service/parttwo/main/gtnServicePartII-1.0.jar
fi
echo "file in process"
cp $RPM_BUILD_DIR/$RPM_PACKAGE_NAME-$RPM_PACKAGE_VERSION/Application_Build/*.war $RPM_BUILD_ROOT%{prefix}/tempdeploy/
mkdir $RPM_BUILD_ROOT%{prefix}/deploy/
chmod 777 $RPM_BUILD_ROOT%{prefix}/deploy/
if [ -e $RPM_BUILD_DIR/$RPM_PACKAGE_NAME-$RPM_PACKAGE_VERSION/Application_Build/GtnFramework* ]
then 
cp  $RPM_BUILD_DIR/$RPM_PACKAGE_NAME-$RPM_PACKAGE_VERSION/Application_Build/GtnFramework* $RPM_BUILD_ROOT%{prefix}/deploy/
fi
chmod 777 $RPM_BUILD_ROOT%{prefix}/deploy/

fi
%pre 
pre_install_path=$RPM_INSTALL_PREFIX
if [ -z "$RPM_INSTALL_PREFIX" ] 
then 
pre_install_path=%{prefix}
fi
pre_DB_File_Path=$pre_install_path/DB_Script
if [ -d "$pre_DB_File_Path" ]; then
  echo "$pre_DB_File_Path pre_DB_File_Path exists"
   if [ -z "$DB_Host" ] 
    then 
   echo Required Environment variables not set Plese run . ./Profile.sh
   exit 1
   fi
  else
  echo "$pre_DB_File_Path pre_DB_File_Path does not exists"
fi

%post

echo given prefix $RPM_INSTALL_PREFIX
install_path=$RPM_INSTALL_PREFIX
if [ -z "$RPM_INSTALL_PREFIX" ] 
then 
install_path=%{prefix}
fi
chown -R $Chown:$Chown  $install_path
chmod -R 777 $install_path/deploy/
chmod -R 777 $install_path/jboss-7.1.1/standalone/deployments/

app_file= $install_path/tempdeploy/

if [ -d $app_file ]
then
## New Changes

declare -a array=("gtnSharedLibrary" "gtnPartI" "gtnPartII" "gtnForecasting" "gtnGlobal" "gtnContract" "gtnAdminConsole" "gtnTransaction" "gtnCff" "gtnWorkflow" "gtnARM" "gtnWorkflow" "gtnSecurity" "gtnGCM" "gtnUtilities" "GtnVaadinWidgetset" "default-theme")
# get length of an array
arraylength=${#array[@]}
# use for loop to read all values and indexes
for (( i=1; i<${arraylength}+1; i++ ));
## now loop through the above array
do
 echo "${array[$i-1]}"
echo $install_path
if [ -e $install_path/tempdeploy/${array[$i-1]}.war ]
then
cp  $install_path/tempdeploy/${array[$i-1]}.war $install_path/deploy/
else 
echo  ${array[$i-1]}.war not found in input tar.
continue
fi

chmod -R 777 $install_path/deploy/
chmod -R 777 $install_path/jboss-7.1.1/standalone/deployments/
 
file="$install_path/jboss-7.1.1/standalone/deployments/${array[$i-1]}"
echo "----------------------------------------------------"$file
if [ -f "$file.war.deployed" ]
then 
mv $install_path/jboss-7.1.1/standalone/deployments/${array[$i-1]}.war.deployed  $install_path/jboss-7.1.1/standalone/deployments/${array[$i-1]}.war.undeployed
#rm -rf $install_path/jboss-7.1.1/standalone/deployments/${array[$i-1]}.war
rm -rf $install_path/jboss-7.1.1/standalone/deployments/${array[$i-1]}.war.undeployed
fi

sleep 5s

while [ ! -f "$file.war.deployed" ]
do
echo "Inside While loop extend sleep ...."
if [ -f "$file.war.deployed" ]
then
echo "Exit loop"
break
elif [ -f "$file.war.failed" ]
then
echo "War file failed to deploy.."$file.war
break
else
echo "Inside of Else Part"
sleep 3s
fi
echo "End of While Loooop"
done
done

if [ -e $install_path/tempdeploy/BPM.war ]
then
cp $install_path/tempdeploy/BPM.war $install_path/jboss-7.1.1/standalone/deployments/
chmod -R  777 $install_path/jboss-7.1.1/standalone/deployments/BPM.war
else 
echo  BPM.war not found in input tar.
fi
rm -rf $install_path/tempdeploy
fi

DB_File_Path=$install_path/DB_Script
#DB_Script Changes
array=(App_Schema Staging_Schema Alg_def_App_Schema)
for schema_name in ${array[@]}
do
DB_Schema_Name=$schema_name
echo executing  $schema_name

if [ ! -d "$DB_File_Path/$DB_Schema_Name" ]; then
  echo "$DB_File_Path/$DB_Schema_Name DB_File_Path does not exists"
  continue
fi
 echo -e "\n"
  echo "List Of Folder Names :"
  echo "----------------------"
echo "1.Sql_Script"
echo "2.Verification_Script"
echo "3.Rollback_Script"


case "$DB_Script_Name" in
   "1") DB_Script_Name=Sql_Script
        echo you have choosen $DB_Script_Name folder
   ;;
   "2") DB_Script_Name=Verification_Script
        echo you have choosen $DB_Script_Name folder
   ;;
   "3") DB_Script_Name=Rollback_Script
        echo you have choosen $DB_Script_Name folder
   ;;
esac
if [ ! -d "$DB_File_Path/$DB_Schema_Name/$DB_Script_Name" ]; then
   echo DB SCRIPT  NOT EXISTS IN  PATH $DB_File_Path/$DB_Schema_Name/$DB_Script_Name
  continue 
fi
 echo -e "\n"

  echo "List Of Database :"
  echo "------------------"

pass="$(echo $DB_Migration_pwd | openssl enc -base64 -d)"

x=$(find $DB_File_Path/$DB_Schema_Name/$DB_Script_Name/ -type f -name "*.sql" -o -name "*.sh" |sort)
for  filename in $x
do
DATE=`date +%Y-%m-%d:%H:%M:%S`
LOGDATE=`date +%Y-%m-%d`
log_file="$DB_File_Path/$DB_Schema_Name/$DB_Script_Name/Log_"$DB_Script_Name"_$LOGDATE.log"
#echo $DATE
echo "$filename.."
echo "INFO $DATE Executing file $filename...">>$log_file
if [ $DB_Schema_Name == App_Schema ]
then
sqlcmd  -b -I -U $DB_Migration_User_Name -P $pass -S $DB_Host -d $DB_APP_Name -i $filename  -b -m-1 1>>$log_file
elif [ $DB_Schema_Name == Staging_Schema ] 
then
sqlcmd  -b -I -U $DB_Migration_User_Name -P $pass -S $DB_Host -d $DB_STG_Name -i $filename  -b -m-1 1>>$log_file
elif [ $DB_Schema_Name == Sys_Schema ] 
then
sqlcmd  -b -I -U $DB_Migration_User_Name -P $pass -S $DB_Host -d $DB_SYS_Name -i $filename  -b -m-1 1>>$log_file
elif [ $DB_Schema_Name == Alg_def_App_Schema ] 
then
sqlcmd  -b -I -U $DB_Migration_User_Name -P $pass -S $DB_Host -d $DB_APP_Name -i $filename  -b -m-1 1>>$log_file
else
echo Invalid DB Schema
fi

if [ $? -eq 1 ]
then
echo "ERROR $DATE Error in Executing file $filename...">>$log_file
else
echo "INFO $DATE Successfully Executed file $filename...">>$log_file
fi
done
done

chown -R $Chown:$Chown  $install_path
%files
%{prefix}/*


