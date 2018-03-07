%global _enable_debug_package 0
%global debug_package %{nil}
%global __os_install_post /usr/lib/rpm/brp-compress %{nil}
%define _binary_payload w6.gzdio
%global _binaries_in_noarch_packages_terminate_build 0
Name:SPEC_NAME
Version:SPEC_VERSION
Release:        1%{?dist}
Summary:GTN APP Release
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

mkdir -p $RPM_BUILD_ROOT%{prefix}
 
if [ -d "$folderexist"ETL_Build ]
then
mkdir -p  $RPM_BUILD_ROOT%{prefix}/etl/
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
touch $RPM_BUILD_ROOT%{prefix}/DB_Script/exec_db_marker.txt
cp -R  $RPM_BUILD_DIR/$RPM_PACKAGE_NAME-$RPM_PACKAGE_VERSION/DB_Script/*  $RPM_BUILD_ROOT%{prefix}/DB_Script/
echo "=======================================DB_SCRIPT==========================================================="
fi

if [ -d "$folderexist"Application_Build ]
then
mkdir -p  $RPM_BUILD_ROOT%{prefix}/tempdeploy

mkdir -p $RPM_BUILD_ROOT%{prefix}/etl/Interface_Job

if [ -e  $RPM_BUILD_DIR/$RPM_PACKAGE_NAME-$RPM_PACKAGE_VERSION/Application_Build/R2_ETL.jar ]
then
  cp $RPM_BUILD_DIR/$RPM_PACKAGE_NAME-$RPM_PACKAGE_VERSION/Application_Build/R2_ETL.jar  $RPM_BUILD_ROOT%{prefix}/etl/Interface_Job/
chmod -R 777 $RPM_BUILD_ROOT%{prefix}/etl/Interface_Job/R2_ETL.jar
fi

if [ -e  $RPM_BUILD_DIR/$RPM_PACKAGE_NAME-$RPM_PACKAGE_VERSION/Application_Build/gtnProperties.jar ]
then
mkdir -p $RPM_BUILD_ROOT%{prefix}/wildfly-10.0.0/applicationProperties
  cp $RPM_BUILD_DIR/$RPM_PACKAGE_NAME-$RPM_PACKAGE_VERSION/Application_Build/gtnProperties.jar  $RPM_BUILD_ROOT%{prefix}/wildfly-10.0.0/applicationProperties/gtnProperties.jar
chmod -R 777 $RPM_BUILD_ROOT%{prefix}/wildfly-10.0.0/applicationProperties/gtnProperties.jar
fi

if [ -e  $RPM_BUILD_DIR/$RPM_PACKAGE_NAME-$RPM_PACKAGE_VERSION/Application_Build/ETLProcedureInput.properties ]
then
  cp $RPM_BUILD_DIR/$RPM_PACKAGE_NAME-$RPM_PACKAGE_VERSION/Application_Build/ETLProcedureInput.properties  $RPM_BUILD_ROOT%{prefix}/etl/Interface_Job/
chmod -R 777 $RPM_BUILD_ROOT%{prefix}/etl/Interface_Job/ETLProcedureInput.properties
fi
if [ -e  $RPM_BUILD_DIR/$RPM_PACKAGE_NAME-$RPM_PACKAGE_VERSION/Application_Build/EtlConfiguration.properties ]
then
  cp $RPM_BUILD_DIR/$RPM_PACKAGE_NAME-$RPM_PACKAGE_VERSION/Application_Build/EtlConfiguration.properties  $RPM_BUILD_ROOT%{prefix}/etl/Interface_Job/
touch $RPM_BUILD_ROOT%{prefix}/etl/Interface_Job/replace_etl_prop.txt
chmod -R 777 $RPM_BUILD_ROOT%{prefix}/etl/Interface_Job/EtlConfiguration.properties
fi
if [ -e  $RPM_BUILD_DIR/$RPM_PACKAGE_NAME-$RPM_PACKAGE_VERSION/Application_Build/dir_struct.sh ]
then
  cp $RPM_BUILD_DIR/$RPM_PACKAGE_NAME-$RPM_PACKAGE_VERSION/Application_Build/dir_struct.sh  $RPM_BUILD_ROOT%{prefix}/etl/
touch $RPM_BUILD_ROOT%{prefix}/etl/Interface_Job/replace_dir_stu.txt
chmod -R 777 $RPM_BUILD_ROOT%{prefix}/etl/dir_struct.sh
fi

echo "file in process"
count=`ls -1 $RPM_BUILD_DIR/$RPM_PACKAGE_NAME-$RPM_PACKAGE_VERSION/Application_Build/*.jar 2>/dev/null | wc -l`
if [ $count != 0 ]
then 
cp $RPM_BUILD_DIR/$RPM_PACKAGE_NAME-$RPM_PACKAGE_VERSION/Application_Build/*.jar $RPM_BUILD_ROOT%{prefix}/tempdeploy/
fi
count=`ls -1 $RPM_BUILD_DIR/$RPM_PACKAGE_NAME-$RPM_PACKAGE_VERSION/Application_Build/*.esa 2>/dev/null | wc -l`
if [ $count != 0 ]
then 
cp $RPM_BUILD_DIR/$RPM_PACKAGE_NAME-$RPM_PACKAGE_VERSION/Application_Build/*.esa $RPM_BUILD_ROOT%{prefix}/tempdeploy/
fi
count=`ls -1 $RPM_BUILD_DIR/$RPM_PACKAGE_NAME-$RPM_PACKAGE_VERSION/Application_Build/*.war 2>/dev/null | wc -l`
if [ $count != 0 ]
then
cp $RPM_BUILD_DIR/$RPM_PACKAGE_NAME-$RPM_PACKAGE_VERSION/Application_Build/stpl-theme.war $RPM_BUILD_ROOT%{prefix}/tempdeploy/
fi

mkdir $RPM_BUILD_ROOT%{prefix}/deploy/
chmod 777 $RPM_BUILD_ROOT%{prefix}/deploy/


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
chown -R $APP_User:$Chown  $install_path
chmod -R 777 $install_path/deploy/
chmod -R 777 $install_path/wildfly-10.0.0/standalone/deployments/


# Replacing ETL Properties
sed -i 's=Server_Path='$install_path'=g' $install_path/etl/Interface_Job/Scripts/*

if [ -e $install_path/etl/Interface_Job/replace_dir_stu.txt ];
then
mkdir -p "$install_path"_data/etl/staging

sh $install_path/etl/dir_struct.sh "$install_path"_data/etl
rm -rf $install_path/etl/Interface_Job/replace_dir_stu.txt
fi
if [ -e $install_path/etl/Interface_Job/replace_etl_prop.txt ];
then
rm -rf $install_path/etl/Interface_Job/replace_etl_prop.txt
echo Replacing ETL Properties
sed -i -e "/BPI_HOST=/ s/=.*/=$BPI_HOST/" -e  "/BPI_DB=/ s/=.*/=$BPI_DB/" -e "/BPI_USER=/ s/=.*/=$BPI_USER/" -e "/BPI_PASSWORD=/ s/=.*/=$BPI_PASSWORD/" $install_path/etl/Interface_Job/EtlConfiguration.properties
sed -i -e  "/FILE_LOCAL_PATH=/ s/=.*/=$FILE_LOCAL_PATH/" -e  "/INBOUND_FILE_PATH=/ s/=.*/=$INBOUND_FILE_PATH/" -e "/OUTBOUND_FILE_PATH=/ s/=.*/=$OUTBOUND_FILE_PATH/" -e "/MAIL_FILE_PATH=/ s/=.*/=$MAIL_FILE_PATH/" $install_path/etl/Interface_Job/EtlConfiguration.properties
sed -i -e "/COMMON_LOGIC_PATH=/ s/=.*/=$COMMON_LOGIC_PATH/" -e  "/SERVER_NAME=/ s/=.*/=$SERVER_NAME/" -e "/SERVER_PASSWORD=/ s/=.*/=$SERVER_PASSWORD/" -e  "/SERVER_PORT=/ s/=.*/=$SERVER_PORT/" $install_path/etl/Interface_Job/EtlConfiguration.properties
sed -i -e "/SERVER_USERNAME=/ s/=.*/=$SERVER_USERNAME/" -e  "/BPI_SYS_HOST=/ s/=.*/=$BPI_SYS_HOST/" -e "/BPI_SYS_DB=/ s/=.*/=$BPI_SYS_DB/" -e "/BPI_SYS_USER=/ s/=.*/=$BPI_SYS_USER/" $install_path/etl/Interface_Job/EtlConfiguration.properties
sed -i -e "/BPI_SYS_PASSWORD=/ s/=.*/=$BPI_SYS_PASSWORD/" -e  "/STAGING_HOST=/ s/=.*/=$STAGING_HOST/" -e  "/STAGING_DB=/ s/=.*/=$STAGING_DB/" -e "/STAGING_USER=/ s/=.*/=$STAGING_USER/" $install_path/etl/Interface_Job/EtlConfiguration.properties
sed -i -e "/STAGING_PASSWORD=/ s/=.*/=$STAGING_PASSWORD/" -e "/BPI_SOURCE_HOST=/ s/=.*/=$BPI_SOURCE_HOST/" -e "/BPI_SOURCE_DB=/ s/=.*/=$BPI_SOURCE_DB/" -e "/BPI_SOURCE_USER=/ s/=.*/=$BPI_SOURCE_USER/" $install_path/etl/Interface_Job/EtlConfiguration.properties
sed -i -e "/BPI_SOURCE_PASSWORD=/ s/=.*/=$BPI_SOURCE_PASSWORD/" -e  "/ETL_PORT_NO=/ s/=.*/=$ETL_PORT_NO/" -e "/com_stpl_gtnframework_base_path=/ s/=.*/=$Gtn_Framework_Base_path/" $install_path/etl/Interface_Job/EtlConfiguration.properties
fi
# Deleting Service jar index files ends

#Moving Transaction JSON

if [ -e $install_path/tempdeploy/GtnFrameworkTransaction.jar ];
then
mkdir -p $Gtn_Framework_Base_path/transaction_json
jar xvf $install_path/tempdeploy/GtnFrameworkTransaction.jar >/dev/null
cp  -R /WEB-INF/classes/transaction_json $Gtn_Framework_Base_path
rm -rf /WEB-INF /META-INF
chown -R $APP_User:$Chown $Gtn_Framework_Base_path
fi


#  Moving Gtn Framework Wars
app_file=$install_path/tempdeploy/
FILES="$install_path/tempdeploy/*.esa"
for f in $FILES
do
if [ -e $f ] 
then
        echo "Deploying $f"
 currentfile=$(basename $f)
if [ -e  $install_path/osgi/modules/$currentfile.esa ];
then

rm -rf $install_path/osgi/modules/$currentfile.esa
sleep 8s

fi
cp $f $install_path/osgi/modules/
fi
done

cp $install_path/tempdeploy/stpl-theme.war $install_path/osgi/war/stpl-theme.war

# Moving Gtn Framework wars ends


#  Executing DB Scripts 
if [ -e $install_path/DB_Script/exec_db_marker.txt ]; then
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
log_file="$install_path/logs/$DB_Schema_Name/$DB_Script_Name/Log_"$DB_Script_Name"_$LOGDATE.log"
mkdir -p "$install_path/logs/$DB_Schema_Name/$DB_Script_Name"
chmod 777 "$install_path/logs/$DB_Schema_Name/$DB_Script_Name"
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
rm -rf $install_path/DB_Script/exec_db_marker.txt
fi

# OWNER GROUP CHANGES

chmod -R 750 $install_path
chown -R $APP_User:$Chown $install_path
chown $APP_User:etl $install_path
chown $APP_User:etl $install_path/etl
chown -R $APP_User:etl $install_path/etl/staging
find $install_path/etl -d -name "Input" | xargs chmod 770  2>/dev/null
find $install_path/etl -d -name "GALDERMA_FILES_UPLOAD" | xargs chmod 770 2>/dev/null
chown -R $APP_User:etl $install_path/logs
chown -R $APP_User:etl $install_path/DB_Script
chown $APP_User:etl $install_path

%files
%{prefix}/
