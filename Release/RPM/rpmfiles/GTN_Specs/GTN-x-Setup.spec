%global _enable_debug_package 0
%global debug_package %{nil}
%global __os_install_post /usr/lib/rpm/brp-compress %{nil}
%define _binary_payload w6.gzdio
%global _binaries_in_noarch_packages_terminate_build 0
%define _servicename bpigtn
%define _wsservicename bpigtn_ws

Name:SPEC_NAME
Version:SPEC_VERSION
Release:        2%{?dist}
Summary:GTN JBOSS Setup
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

mkdir -p  $RPM_BUILD_ROOT%{prefix}/etl/staging
mkdir -p  $RPM_BUILD_ROOT%{prefix}/conf/"BPI Configuration"


cp -R  $RPM_BUILD_DIR/$RPM_PACKAGE_NAME-$RPM_PACKAGE_VERSION/JBoss_User_Configuration/*   $RPM_BUILD_ROOT%{prefix}/conf/
if [ -d "$RPM_BUILD_DIR/$RPM_PACKAGE_NAME-$RPM_PACKAGE_VERSION/GTNFrameworkProperties/"  ] 
then
cp -R  $RPM_BUILD_DIR/$RPM_PACKAGE_NAME-$RPM_PACKAGE_VERSION/GTNFrameworkProperties/* $RPM_BUILD_ROOT%{prefix}/conf/
fi


chmod -R 755 $RPM_BUILD_ROOT%{prefix}/*

touch $RPM_BUILD_ROOT%{prefix}/conf/jboss-as2/jboss-as-standalone.pid
chmod -R 777 $RPM_BUILD_ROOT%{prefix}/conf/jboss-as2/*




cp -R  $RPM_BUILD_DIR/$RPM_PACKAGE_NAME-$RPM_PACKAGE_VERSION/jboss-7.1.1/*   $RPM_BUILD_ROOT%{prefix}/

cp -R $RPM_BUILD_DIR/$RPM_PACKAGE_NAME-$RPM_PACKAGE_VERSION/JBoss_Configuration/*  $RPM_BUILD_ROOT%{prefix}/jboss-7.1.1/



#Copy Service File

mkdir -p  $RPM_BUILD_ROOT%{prefix}/etc/init.d
cp $RPM_BUILD_DIR/$RPM_PACKAGE_NAME-$RPM_PACKAGE_VERSION/Service_File/bpigtn  $RPM_BUILD_ROOT%{prefix}/etc/init.d/%{_servicename}
cp $RPM_BUILD_DIR/$RPM_PACKAGE_NAME-$RPM_PACKAGE_VERSION/Service_File/bpigtn_ws  $RPM_BUILD_ROOT%{prefix}/etc/init.d/%{_wsservicename}

# Check DB Script Exists

if [ -d "$RPM_BUILD_DIR/$RPM_PACKAGE_NAME-$RPM_PACKAGE_VERSION/DB_Script"  ] 
then
rm -rf %{prefix}/DB_Script/*
mkdir -p  $RPM_BUILD_ROOT%{prefix}/DB_Script/*
cp -R  $RPM_BUILD_DIR/$RPM_PACKAGE_NAME-$RPM_PACKAGE_VERSION/DB_Script/*  $RPM_BUILD_ROOT%{prefix}/DB_Script/
fi

chmod -R 755 $RPM_BUILD_ROOT%{prefix}

# Create Log directories

mkdir $RPM_BUILD_ROOT%{prefix}/logs/
mkdir $RPM_BUILD_ROOT%{prefix}/logs/boot_temp
mkdir  $RPM_BUILD_ROOT%{prefix}/logs/jboss-as2

%pre 
pre_install_path=$RPM_INSTALL_PREFIX
if [ -z "$RPM_INSTALL_PREFIX" ] 
then 
pre_install_path=%{prefix}
fi

   if [ -z "$DB_Host" ] 
    then 
   echo Required Environment variables not set Plese run . ./Profile.sh
   exit 1
   fi
if ! [ -x "$(command -v sqlcmd)" ]; then
  echo 'Error: sqlcmd is not installed. sqlcmd is required to run DB Scripts.Please install it and then install rpm' >&2
  exit 1
fi

%post

echo given prefix $RPM_INSTALL_PREFIX
install_path=$RPM_INSTALL_PREFIX
if [ -z "$RPM_INSTALL_PREFIX" ] 
then 
install_path=%{prefix}
fi
chmod -R 755  $install_path/*
chown -R $APP_User:$Chown  $install_path
server_name=$(basename $install_path)
INPUT=$install_path
base_path=$(echo $INPUT| cut -d'/' -f 2)

DB_File_Path=$install_path/DB_Script


#do_Standalone_xml_Config
standalone_xml_path=$install_path/jboss-7.1.1/standalone/configuration/standalone.xml
chmod 777 $install_path/jboss-7.1.1/standalone/configuration/standalone.xml
sed -i 's/Base_Path/'$base_path'/g' $install_path/jboss-7.1.1/standalone/configuration/standalone.xml
sed -i 's/Server_Name/'$server_name'/g' $install_path/jboss-7.1.1/standalone/configuration/standalone.xml
sed -i 's/DB_Host/'$DB_Host'/g' $install_path/jboss-7.1.1/standalone/configuration/standalone.xml
sed -i 's/DB_User_Name/'$DB_User_Name'/g' $install_path/jboss-7.1.1/standalone/configuration/standalone.xml
sed -i 's=DB_Password='$DB_Password'=g' $install_path/jboss-7.1.1/standalone/configuration/standalone.xml
sed -i 's/GTN_RPM_APP_DB/'$DB_APP_Name'/g' $install_path/jboss-7.1.1/standalone/configuration/standalone.xml
sed -i 's/GTN_RPM_SYS_DB/'$DB_SYS_Name'/g' $install_path/jboss-7.1.1/standalone/configuration/standalone.xml
sed -i 's/GTN_RPM_BPM_DB/'$DB_BPM_Name'/g' $install_path/jboss-7.1.1/standalone/configuration/standalone.xml
sed -i 's=GTN_FRAMEWORK_BASE_PATH='$Gtn_Framework_Base_path'=g' $install_path/jboss-7.1.1/standalone/configuration/standalone.xml
sed -i 's=CLIENT_NAME='$CLIENT_NAME'=g' $install_path/jboss-7.1.1/standalone/configuration/standalone.xml
sed -i 's=BUSINESS_PROCESS='$BUSINESS_PROCESS'=g' $install_path/jboss-7.1.1/standalone/configuration/standalone.xml

if [ -z "$PORT_OFFSET" ];
then 
PORT_OFFSET=0
fi
sed -i 's/PORT_OFFSET/'$PORT_OFFSET'/g' $install_path/jboss-7.1.1/standalone/configuration/standalone.xml


#do_GtnFrameworkProperties_for_Gtn_framework_server
gtn_prop_path=$install_path/conf/GtnFramework.properties
if [ -f "$gtn_prop_path"  ] 
then
chmod 777 $gtn_prop_path
sed -i 's=Web_Service_Url='$Web_Service_Url'=g' $gtn_prop_path
sed -i 's/DB_IP/'$DB_Host'/g' $gtn_prop_path
sed -i 's/DB_User_Name/'$DB_User_Name'/g' $gtn_prop_path
sed -i 's=DB_Password='$DB_Password'=g' $gtn_prop_path
sed -i 's/GTN_RPM_APP_DB/'$DB_APP_Name'/g' $gtn_prop_path
fi


#do_Jboss_Conf
jboss_env_conf=$install_path/conf/jboss-as2/jboss-as.conf
chmod 777  $jboss_env_conf
 sed -i 's/Server_Name/'$server_name'/g' $jboss_env_conf
 sed -i 's/Base_Path/'$base_path'/g' $jboss_env_conf
 sed -i 's/CELAPP/'$APP_User'/g' $jboss_env_conf



#do_BPI_DB_conf
#chmod 777 $install_path/conf/"BPI Configuration"/FTPConfiguration.properties
# sed -i 's/Server_Name/'$server_name'/g' $install_path/conf/"BPI Configuration"/FTPConfiguration.properties
# sed -i 's/Base_Path/'$base_path'/g' $install_path/conf/"BPI Configuration"/FTPConfiguration.properties



#do_Init_Service

echo service Name $server_name
echo web service Name $ ws_service_name
mv $install_path/etc/init.d/%{_servicename} /etc/init.d/$server_name
mv $install_path/etc/init.d/%{_wsservicename} /etc/init.d/$ws_service_name

rm -rf $install_path/etc/init.d/


chmod 770 /etc/init.d/$server_name
chmod 770 /etc/init.d/$ws_service_name

sed -i 's/Server_Name/'$server_name'/g' /etc/init.d/$server_name
sed -i 's/Base_Path/'$base_path'/g' /etc/init.d/$server_name
sed -i 's/ws_service_name/'$ws_service_name'/g' /etc/init.d/$server_name

sed -i 's/ws_service_name/'$ws_service_name'/g' /etc/init.d/$ws_service_name
sed -i 's/Base_Path/'$base_path'/g' /etc/init.d/$ws_service_name

# Replace Host for BPM in DB Script

if [ -f $DB_File_Path/Sys_Schema/Sql_Script/01_Sys/dbo.PortletPreferences.Table.sql ]
then
sed -i 's~celgene-test.sysbiz.org~allergan.bpitechnologies.com~g' $DB_File_Path/Sys_Schema/Sql_Script/01_Sys/dbo.PortletPreferences.Table.sql
echo $DB_File_Path/Sys_Schema/Sql_Script/01_Sys/dbo.PortletPreferences.Table.sql BPM Host Replaced  
else
echo Sys Schema not present so Host for BPM  not replaced
fi


# Execute DB SCRIPT

if [ -d "$DB_File_Path" ]; then
  echo "$DB_File_Path DB_File_Path exists"
  else
  echo "$DB_File_Path DB_File_Path does not exists"
  exit 1
fi
array=(App_Schema Staging_Schema Sys_Schema Alg_def_App_Schema)
for schema_name in ${array[@]}
do
DB_Schema_Name=$schema_name
echo executing  $schema_name

if [ ! -d "$DB_File_Path/$DB_Schema_Name" ]; then
  echo "$DB_File_Path/$DB_Schema_Name DB_File_Path does not exists"
  continue
fi
 echo -e "\n"

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
echo "ERROR $DATE Error in Executing file $filename.. For  More Info See the log File $log_file."
break
else
echo "INFO $DATE Successfully Executed file $filename...">>$log_file
echo " See the log File $log_file."
fi
done
done


# OWNER GROUP CHANGES

chmod -R 750 $install_path
chown -R $APP_User:$Chown $install_path
chown $APP_User:etl $install_path
chown $APP_User:etl $install_path/etl
chown -R $APP_User:etl $install_path/etl/staging
find $install_path/etl -d -name "Input" | xargs chmod 770 2>/dev/null
find $install_path/etl -d -name "GALDERMA_FILES_UPLOAD" | xargs chmod 770 2>/dev/null
chown -R $APP_User:etl $install_path/logs
chown -R $APP_User:etl $install_path/DB_Script
chown $APP_User:etl $install_path

%files

%{prefix}/




