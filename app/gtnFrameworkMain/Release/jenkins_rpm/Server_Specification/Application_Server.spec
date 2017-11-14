%global _enable_debug_package 0
%global debug_package %{nil}
%global __os_install_post /usr/lib/rpm/brp-compress %{nil}
%define _binary_payload w6.gzdio
%global _binaries_in_noarch_packages_terminate_build 0
%define _base_path /Base_Path_Name/App_Name

Name:SPEC_NAME
Version:SPEC_VERSION
Release:        2%{?dist}
Summary:Raja1 test
BuildArchitectures: noarch
License:  GPLv3+
Source0:SPEC_SOURCE
Prefix:/Base_Path_Name/App_Name
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

mkdir -p  $RPM_BUILD_ROOT%{_base_path}/etl/staging
mkdir -p  $RPM_BUILD_ROOT%{_base_path}/conf/"BPI Configuration"

mkdir -p  $RPM_BUILD_ROOT/etc/init.d
cp -R  $RPM_BUILD_DIR/$RPM_PACKAGE_NAME-$RPM_PACKAGE_VERSION/JBoss_EnvConfiguration/*   $RPM_BUILD_ROOT%{_base_path}/conf/
cp -R  $RPM_BUILD_DIR/$RPM_PACKAGE_NAME-$RPM_PACKAGE_VERSION/GTNFrameworkProperties/* $RPM_BUILD_ROOT%{_base_path}/conf/


chmod -R 755 $RPM_BUILD_ROOT%{_base_path}/*

touch $RPM_BUILD_ROOT%{_base_path}/conf/jboss-as2/jboss-as-standalone.pid
chmod -R 777 $RPM_BUILD_ROOT%{_base_path}/conf/jboss-as2/*


cp -R $RPM_BUILD_DIR/$RPM_PACKAGE_NAME-$RPM_PACKAGE_VERSION/BPI_Configuration/*    $RPM_BUILD_ROOT%{_base_path}/conf/

cp -R  $RPM_BUILD_DIR/$RPM_PACKAGE_NAME-$RPM_PACKAGE_VERSION/jboss-7.1.1/*   $RPM_BUILD_ROOT%{_base_path}/

cp -R  $RPM_BUILD_DIR/$RPM_PACKAGE_NAME-$RPM_PACKAGE_VERSION/BPM_Build/*   $RPM_BUILD_ROOT%{_base_path}/

cp -R  $RPM_BUILD_DIR/$RPM_PACKAGE_NAME-$RPM_PACKAGE_VERSION/BPM_Build/.index   $RPM_BUILD_ROOT%{_base_path}/
chmod -R 777 $RPM_BUILD_ROOT%{_base_path}/.index
cp -R  $RPM_BUILD_DIR/$RPM_PACKAGE_NAME-$RPM_PACKAGE_VERSION/BPM_Build/.niogit  $RPM_BUILD_ROOT%{_base_path}/
chmod -R 777 $RPM_BUILD_ROOT%{_base_path}/.niogit

cp -R $RPM_BUILD_DIR/$RPM_PACKAGE_NAME-$RPM_PACKAGE_VERSION/JBoss_Configuration/*  $RPM_BUILD_ROOT%{_base_path}/jboss-7.1.1/

cp -R $RPM_BUILD_DIR/$RPM_PACKAGE_NAME-$RPM_PACKAGE_VERSION/ETL_Build/* $RPM_BUILD_ROOT%{_base_path}/etl/

cp -R $RPM_BUILD_DIR/$RPM_PACKAGE_NAME-$RPM_PACKAGE_VERSION/etc/init.d/*  $RPM_BUILD_ROOT/etc/init.d/App_Name

mkdir -p  $RPM_BUILD_ROOT%{_base_path}/jboss-7.1.1/standalone/deployments/
cp -R $RPM_BUILD_DIR/$RPM_PACKAGE_NAME-$RPM_PACKAGE_VERSION/Default_War/*  $RPM_BUILD_ROOT%{_base_path}/jboss-7.1.1/standalone/deployments/

chmod -R 777 $RPM_BUILD_ROOT%{_base_path}/jboss-7.1.1/standalone/deployments/*
touch $RPM_BUILD_ROOT%{_base_path}/jboss-7.1.1/standalone/deployments/ROOT.war.dodeploy
chmod -R 777 $RPM_BUILD_ROOT%{_base_path}/jboss-7.1.1/standalone/deployments/ROOT.war.dodeploy

rm -rf /Base_Path_Name/App_Name/DB_Script/*
mkdir -p  $RPM_BUILD_ROOT%{_base_path}/DB_Script/*
cp -R  $RPM_BUILD_DIR/$RPM_PACKAGE_NAME-$RPM_PACKAGE_VERSION/DB_Script/*  $RPM_BUILD_ROOT%{_base_path}/DB_Script/
chmod -R 755 $RPM_BUILD_ROOT%{_base_path}
mkdir $RPM_BUILD_ROOT%{_base_path}/logs/

%post

chmod -R 755  %{prefix}/*


DB_File_Path=/Base_Path_Name/App_Name/DB_Script


#do_Standalone_xml_Config
standalone_xml_path=/Base_Path_Name/App_Name/jboss-7.1.1/standalone/configuration/standalone.xml
chmod 777 /Base_Path_Name/App_Name/jboss-7.1.1/standalone/configuration/standalone.xml
sed -i 's/Base_Path/'Base_Path_Name'/g' /Base_Path_Name/App_Name/jboss-7.1.1/standalone/configuration/standalone.xml
sed -i 's/Server_Name/'App_Name'/g' /Base_Path_Name/App_Name/jboss-7.1.1/standalone/configuration/standalone.xml
sed -i 's/DB_IP/'$DB_Host'/g' /Base_Path_Name/App_Name/jboss-7.1.1/standalone/configuration/standalone.xml
sed -i 's/DB_User_Name/'$DB_User_Name'/g' /Base_Path_Name/App_Name/jboss-7.1.1/standalone/configuration/standalone.xml
sed -i 's=DB_Password='$DB_Password'=g' /Base_Path_Name/App_Name/jboss-7.1.1/standalone/configuration/standalone.xml
sed -i 's/APP_TST/'$DB_APP_Name'/g' /Base_Path_Name/App_Name/jboss-7.1.1/standalone/configuration/standalone.xml
sed -i 's/SYS_TST/'$DB_SYS_Name'/g' /Base_Path_Name/App_Name/jboss-7.1.1/standalone/configuration/standalone.xml
sed -i 's/BPM_TST/'$DB_BPM_Name'/g' /Base_Path_Name/App_Name/jboss-7.1.1/standalone/configuration/standalone.xml
sed -i 's=GTN_FRAMEWORK_BASE_PATH='$Gtn_Framework_Base_path'=g' /Base_Path_Name/App_Name/jboss-7.1.1/standalone/configuration/standalone.xml


#do_GtnFrameworkProperties_for_Gtn_framework_server
gtn_prop_path=/Base_Path_Name/App_Name/conf/GtnFramework.properties
chmod 777 $gtn_prop_path
sed -i 's=Web_Service_Url='$Web_Service_Url'=g' $gtn_prop_path
sed -i 's/DB_IP/'$DB_Host'/g' $gtn_prop_path
sed -i 's/DB_User_Name/'$DB_User_Name'/g' $gtn_prop_path
sed -i 's=DB_Password='$DB_Password'=g' $gtn_prop_path
sed -i 's/APP_TST/'$DB_APP_Name'/g' $gtn_prop_path


#do_Jboss_Conf
jboss_env_conf=/Base_Path_Name/App_Name/conf/jboss-as2/jboss-as.conf
chmod 777  $jboss_env_conf
 sed -i 's/Server_Name/'App_Name'/g' $jboss_env_conf
 sed -i 's/Base_Path/'Base_Path_Name'/g' $jboss_env_conf
 sed -i 's/CELAPP/'$APP_User'/g' $jboss_env_conf



#do_BPI_DB_conf
chmod 777 /Base_Path_Name/App_Name/conf/"BPI Configuration"/FTPConfiguration.properties
 sed -i 's/Server_Name/'App_Name'/g' /Base_Path_Name/App_Name/conf/"BPI Configuration"/FTPConfiguration.properties
 sed -i 's/Base_Path/'Base_Path_Name'/g' /Base_Path_Name/App_Name/conf/"BPI Configuration"/FTPConfiguration.properties


#do_DB_ETL_Prop
etl_prop_path=/Base_Path_Name/App_Name/etl/Interface_Job/EtlConfiguration.properties
chmod 777 $etl_prop_path
 sed -i 's/Base_Path/'Base_Path_Name'/g' $etl_prop_path
 sed -i 's/Server_Name/'App_Name'/g' $etl_prop_path
 sed -i 's/DB_IP/'$DB_Host'/g' $etl_prop_path
 sed -i 's/DB_Name/'$ETL_DB_USER'/g' $etl_prop_path
 sed -i 's/DB_Password/'$ETL_DB_PWD'/g' $etl_prop_path
 sed -i 's/APP_DEV/'$DB_APP_Name'/g' $etl_prop_path
 sed -i 's/SYS_DEV/'$DB_SYS_Name'/g' $etl_prop_path
 sed -i 's/STAG_DEV/'$DB_STG_Name'/g' $etl_prop_path
 sed -i 's/DB_ETL_PASSWORD/'$ETL_DB_PASSPHRASE_PWD'/g' $etl_prop_path


#do_Init_Service
echo $File_Path
echo App_Name
if [ -e $DB_File_Path/Sys_Schema/Sql_Script/01_Sys/dbo.PortletPreferences.Table.sql ]
then
sed -i 's~celgene-test.sysbiz.org~allergan.bpitechnologies.com~g' $DB_File_Path/Sys_Schema/Sql_Script/01_Sys/dbo.PortletPreferences.Table.sql
else
echo BPM Host replaced for Sys Schema
fi

chmod 777 /etc/init.d/App_Name
sed -i 's/Server_Name/'App_Name'/g' /etc/init.d/App_Name
sed -i 's/Base_Path/'Base_Path_Name'/g' /etc/init.d/App_Name

#echo "$DB_File_Path"
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

%files
%attr(0755, Chown, Chown) /Base_Path_Name/App_Name/*
%attr(0755, Chown, Chown) /Base_Path_Name/App_Name
#%attr(775,bpigtn_allergan_dev,bpigtn_allergan_dev)
%{prefix}/conf/jboss-as2/jboss-as-standalone.pid
%{prefix}/conf/jboss-as2/jboss-as.conf
%{prefix}/jboss-7.1.1/WorkflowXML/BPIGeneratorIDs.xml
%{prefix}/Cumulative_File/*
%{prefix}/data/*
%{prefix}/repositories/*
%{prefix}/deploy/
%{prefix}/.index/*
%{prefix}/.niogit/*

%{prefix}/jboss-7.1.1/welcome-content/*
%{prefix}/jboss-7.1.1/bin/*
%{prefix}/jboss-7.1.1/modules/*
%{prefix}/jboss-7.1.1/bpmconfig/*
%{prefix}/jboss-7.1.1/standalone/*
%{prefix}/jboss-7.1.1/domain/*
%{prefix}/jboss-7.1.1/docs/*
%{prefix}/jboss-7.1.1/applicationProperties/
%{prefix}/jboss-7.1.1/bundles/*
%{prefix}/jboss-7.1.1/appclient/*
%{prefix}/jboss-7.1.1/data_dir_export/
%{prefix}/jboss-7.1.1/jboss-modules.jar
%{prefix}/jboss-7.1.1/LICENSE.txt
%{prefix}/jboss-7.1.1/copyright.txt
%{prefix}/jboss-7.1.1/README.txt
%{prefix}/conf/*
%{prefix}/jboss-7.1.1/applicationPropertiesll
/etc/init.d/*
%{prefix}/etl/*
%{prefix}/DB_Script/*
%attr(0755, Chown, Chown) /Base_Path_Name/App_Name/*
%attr(0755, Chown, Chown) /Base_Path_Name/App_Name



