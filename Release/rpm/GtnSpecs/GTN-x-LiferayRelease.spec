%global _enable_debug_package 0
%global debug_package %{nil}
%global __os_install_post /usr/lib/rpm/brp-compress %{nil}
%define _binary_payload w6.gzdio
%global _binaries_in_noarch_packages_terminate_build 0
Name:SPEC_NAME
Version:SPEC_VERSION
Release:        1%{?dist}
Summary:GTN Liferay Release
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
preProcessServerSetup(){
  echo "preProcessServerSetup "
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
        
      if grep -c '^'$APP_User':' /etc/passwd;then
      echo "$APP_User User already exists"
      else
      echo "$APP_User User does not exists"
      useradd $APP_User
      echo "$APP_User User has created"
      fi

      if grep -c '^etl:' /etc/group;then
      echo "etl group already exists"
      else
      echo "etl group does not exists"
      groupadd etl
      echo "etl group has created"
      usermod -a -G etl $APP_User
      echo "$APP_User user has added to etl group"
      fi

      if [ -f "/var/lib/rpm/.rpm.lock" ];then
      echo "rpm.lock exists"
      rm -f /var/lib/rpm/.rpm.lock
      else
      echo "rpm.lock does not exists"
      fi

      if which zip;then
      echo "zip tool already exists"
      else
      echo "zip tool does not exists"
      yum install -y zip
      echo "zip tool has installed"
      fi

      if which unzip;then
      echo "unzip tool already exists"
      else
      echo "unzip tool does not exists"
      yum install -y unzip
      echo "unzip tool has installed"
      fi

      if which tar;then
      echo "tar tool already exists"
      else
      echo "tar tool does not exists"
      yum install -y tar
      echo "tar tool has installed"
      fi

      if which service;then
      echo "service tool already exists"
      else
      echo "service tool does not exists"
      yum install -y initscripts
      echo "service tool has installed"
      fi

      if which httpd;then
      echo "httpd tool already exists"
      else
      echo "httpd tool does not exists"
      yum install -y httpd
      echo "httpd tool has installed"
      fi

      if which bcp;then
      echo "BCP tool already exists"
      else
      echo "BCP tool does not exists"
      curl https://packages.microsoft.com/config/rhel/7/prod.repo > /etc/yum.repos.d/mssql-release.repo
      yum remove unixODBC-utf16 unixODBC-utf16-devel
      ACCEPT_EULA=Y yum install -y msodbcsql17
      ACCEPT_EULA=Y yum install -y mssql-tools
      echo 'export PATH="$PATH:/opt/mssql-tools/bin"' >> ~/.bash_profile
      echo 'export PATH="$PATH:/opt/mssql-tools/bin"' >> ~/.bashrc
      source ~/.bashrc
      yum install -y unixODBC-devel
      ln -sfn /opt/mssql-tools/bin/sqlcmd /usr/bin/sqlcmd
      ln -sfn /opt/mssql-tools/bin/bcp /usr/bin/bcp  
      echo "BCP tool has installed"
      fi

      if [ -f "/etc/sysconfig/iptables" ];then
      echo "iptables already exists"
      else
      echo "iptables does not exists"
      yum install -y iptables
      yum install -y iptables-services
      echo "iptables has installed"
      fi

      if ! [ -x "$(command -v sqlcmd)" ]; then
        echo 'Error: sqlcmd is not installed. sqlcmd is required to run DB Scripts.Please install it and then install rpm' >&2
        exit 1
      fi
}

installServer(){
      echo installing server
      echo $RPM_BUILD_ROOT
      echo $RPM_BUILD_DIR

      mkdir -p $RPM_BUILD_ROOT%{prefix}/etl

      mkdir -p  $RPM_BUILD_ROOT%{prefix}/conf/"BPI Configuration"


      cp -R  $RPM_BUILD_DIR/$RPM_PACKAGE_NAME-$RPM_PACKAGE_VERSION/Wildfly_User_Configuration/*   $RPM_BUILD_ROOT%{prefix}/conf/
      if [ -d "$RPM_BUILD_DIR/$RPM_PACKAGE_NAME-$RPM_PACKAGE_VERSION/GTNFrameworkProperties/"  ] 
      then
      cp -R  $RPM_BUILD_DIR/$RPM_PACKAGE_NAME-$RPM_PACKAGE_VERSION/GTNFrameworkProperties/* $RPM_BUILD_ROOT%{prefix}/conf/
      fi


      chmod -R 755 $RPM_BUILD_ROOT%{prefix}/*

      touch $RPM_BUILD_ROOT%{prefix}/conf/wildfly/wildfly-as-standalone.pid
      chmod -R 777 $RPM_BUILD_ROOT%{prefix}/conf/wildfly/*

      mkdir -p  $RPM_BUILD_ROOT%{prefix}/tempdeploy/JAVA_Setup

      if [ -e  $RPM_BUILD_DIR/$RPM_PACKAGE_NAME-$RPM_PACKAGE_VERSION/JAVA_Setup/jdk-8u151-linux-x64.rpm ]
      then
        cp $RPM_BUILD_DIR/$RPM_PACKAGE_NAME-$RPM_PACKAGE_VERSION/JAVA_Setup/jdk-8u151-linux-x64.rpm  $RPM_BUILD_ROOT%{prefix}/tempdeploy/JAVA_Setup/
      chmod -R 777 $RPM_BUILD_ROOT%{prefix}/tempdeploy/JAVA_Setup/jdk-8u151-linux-x64.rpm
      fi

      if [ -e  $RPM_BUILD_DIR/$RPM_PACKAGE_NAME-$RPM_PACKAGE_VERSION/JAVA_Setup/jdk-7u80-linux-x64.tar.gz ]
      then
        cp $RPM_BUILD_DIR/$RPM_PACKAGE_NAME-$RPM_PACKAGE_VERSION/JAVA_Setup/jdk-7u80-linux-x64.tar.gz  $RPM_BUILD_ROOT%{prefix}/tempdeploy/JAVA_Setup/
      chmod -R 777 $RPM_BUILD_ROOT%{prefix}/tempdeploy/JAVA_Setup/jdk-7u80-linux-x64.tar.gz
      fi


      mkdir -p $RPM_BUILD_ROOT%{prefix}/data

      cp -R  $RPM_BUILD_DIR/$RPM_PACKAGE_NAME-$RPM_PACKAGE_VERSION/Liferay_bundles/*   $RPM_BUILD_ROOT%{prefix}/

      cp -R $RPM_BUILD_DIR/$RPM_PACKAGE_NAME-$RPM_PACKAGE_VERSION/Wildfly_Configuration/*  $RPM_BUILD_ROOT%{prefix}/wildfly-10.0.0/

      cp -a  $RPM_BUILD_DIR/$RPM_PACKAGE_NAME-$RPM_PACKAGE_VERSION/Liferay_Modules/. $RPM_BUILD_ROOT%{prefix}/osgi/modules/

      cp  -a $RPM_BUILD_DIR/$RPM_PACKAGE_NAME-$RPM_PACKAGE_VERSION/Wildfly_Configuration/data/.  $RPM_BUILD_ROOT%{prefix}/data/

      cp   $RPM_BUILD_DIR/$RPM_PACKAGE_NAME-$RPM_PACKAGE_VERSION/Liferay_Configuration/*.properties   $RPM_BUILD_ROOT%{prefix}/

      cp   $RPM_BUILD_DIR/$RPM_PACKAGE_NAME-$RPM_PACKAGE_VERSION/Liferay_Configuration/sqljdbc42.jar  $RPM_BUILD_ROOT%{prefix}/wildfly-10.0.0/standalone/lib/ext/

      cp   $RPM_BUILD_DIR/$RPM_PACKAGE_NAME-$RPM_PACKAGE_VERSION/Liferay_Configuration/liferay-portal-database-all-in-one-support-1.0.0.jar $RPM_BUILD_ROOT%{prefix}/wildfly-10.0.0/standalone/deployments/ROOT.war/WEB-INF/lib/

      #



      #Copy Service File

      mkdir -p  $RPM_BUILD_ROOT%{prefix}/etc/init.d
      cp $RPM_BUILD_DIR/$RPM_PACKAGE_NAME-$RPM_PACKAGE_VERSION/Service_File/bpigtn  $RPM_BUILD_ROOT%{prefix}/etc/init.d/%{_servicename}
      cp $RPM_BUILD_DIR/$RPM_PACKAGE_NAME-$RPM_PACKAGE_VERSION/Service_File/bpigtn_ws  $RPM_BUILD_ROOT%{prefix}/etc/init.d/%{_wsservicename}

      # Check DB Script Exists

      if [ -d "$RPM_BUILD_DIR/$RPM_PACKAGE_NAME-$RPM_PACKAGE_VERSION/DB_Script"  ] 
      then
      rm -rf %{prefix}/DB_Script/*
      mkdir -p  $RPM_BUILD_ROOT%{prefix}/DB_Script/*
      cp -a  $RPM_BUILD_DIR/$RPM_PACKAGE_NAME-$RPM_PACKAGE_VERSION/DB_Script/.  $RPM_BUILD_ROOT%{prefix}/DB_Script/
      fi

      chmod -R 755 $RPM_BUILD_ROOT%{prefix}
}

postProcessServerSetup(){
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


      # Create Log directories

      mkdir -p $GTN_APP_DATA_PATH/pid
      mkdir -p $GTN_APP_DATA_PATH/logs/
      mkdir -p $GTN_APP_DATA_PATH/logs/portal
      mkdir -p $GTN_APP_DATA_PATH/logs/portal/wildfly
      mkdir -p $GTN_APP_DATA_PATH/logs/portal/console
      mkdir -p $GTN_APP_DATA_PATH/logs/portal/boot_temp

      mkdir -p $GTN_APP_DATA_PATH/logs/web_service
      mkdir -p $GTN_APP_DATA_PATH/logs/web_service/jboss-as2
      mkdir -p $GTN_APP_DATA_PATH/logs/web_service/console
      mkdir -p $GTN_APP_DATA_PATH/logs/web_service/boot_temp

      mkdir -p $GTN_APP_DATA_PATH/logs/ETL
      mkdir -p $GTN_APP_DATA_PATH/logs/DB

      mkdir -p $GTN_APP_DATA_PATH/etl/staging
      mkdir -p $GTN_APP_DATA_PATH/etl/File_Process
      mkdir -p $GTN_APP_DATA_PATH/HierarchyQueries
      mkdir -p $GTN_APP_DATA_PATH/exceltransaction
      mkdir -p $GTN_APP_DATA_PATH/Cumulative_Logic
      mkdir -p $GTN_APP_DATA_PATH/GtnCsvExport

      DB_File_Path=$install_path/DB_Script


      #do_Standalone_xml_Config
      standalone_xml_path=$install_path/wildfly-10.0.0/standalone/configuration/standalone.xml
      chmod 777 $install_path/wildfly-10.0.0/standalone/configuration/standalone.xml
      sed -i 's/Base_Path/'$base_path'/g' $install_path/wildfly-10.0.0/standalone/configuration/standalone.xml
      sed -i 's/Server_Name/'$server_name'/g' $install_path/wildfly-10.0.0/standalone/configuration/standalone.xml
      sed -i 's/DB_Host/'$DB_Host'/g' $install_path/wildfly-10.0.0/standalone/configuration/standalone.xml
      sed -i 's/DB_User_Name/'$DB_User_Name'/g' $install_path/wildfly-10.0.0/standalone/configuration/standalone.xml
      sed -i 's=DB_Password='$DB_Password'=g' $install_path/wildfly-10.0.0/standalone/configuration/standalone.xml
      sed -i 's/GTN_RPM_APP_DB/'$DB_APP_Name'/g' $install_path/wildfly-10.0.0/standalone/configuration/standalone.xml
      sed -i 's/GTN_RPM_SYS_DB/'$DB_SYS_Name'/g' $install_path/wildfly-10.0.0/standalone/configuration/standalone.xml
      sed -i 's/GTN_RPM_BPM_DB/'$DB_BPM_Name'/g' $install_path/wildfly-10.0.0/standalone/configuration/standalone.xml
      sed -i 's=GTN_FRAMEWORK_BASE_PATH='$Gtn_Framework_Base_path'=g' $install_path/wildfly-10.0.0/standalone/configuration/standalone.xml
      sed -i 's=CLIENT_NAME='$CLIENT_NAME'=g' $install_path/wildfly-10.0.0/standalone/configuration/standalone.xml
      sed -i 's=BUSINESS_PROCESS='$BUSINESS_PROCESS'=g' $install_path/wildfly-10.0.0/standalone/configuration/standalone.xml
      sed -i 's=GTN_APP_DATA_PATH='$GTN_APP_DATA_PATH'=g' $install_path/wildfly-10.0.0/standalone/configuration/standalone.xml
      sed -i 's=Web_Service_Url='$Web_Service_Url'=g' $install_path/wildfly-10.0.0/standalone/configuration/standalone.xml

      if [ -z "$PORT_OFFSET" ];
      then 
      PORT_OFFSET=0
      fi
      sed -i 's/PORT_OFFSET/'$PORT_OFFSET'/g' $install_path/wildfly-10.0.0/standalone/configuration/standalone.xml

      #sed -i 's=JAVA8_HOME='$JAVA8_HOME'=g' $install_path/wildfly-10.0.0/bin/standalone.conf

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
      jboss_env_conf=$install_path/conf/wildfly/wildfly.conf
      chmod 777  $jboss_env_conf
      sed -i 's/Server_Name/'$server_name'/g' $jboss_env_conf
      sed -i 's/Base_Path/'$base_path'/g' $jboss_env_conf
      sed -i 's=GTN_APP_DATA_PATH='$GTN_APP_DATA_PATH'=g' $jboss_env_conf



      #do_ portal wizard
      chmod 777  $install_path/portal-setup-wizard.properties
      sed -i 's/Server_Name/'$server_name'/g' $install_path/portal-setup-wizard.properties
      sed -i 's/Base_Path/'$base_path'/g' $install_path/portal-setup-wizard.properties

      #do_ Resource Prop
      chmod 777  $install_path/conf/Resources.properties
      sed -i 's/Server_Name/'$server_name'/g'   $install_path/conf/Resources.properties
      sed -i 's/Base_Path/'$base_path'/g'  $install_path/conf/Resources.properties
      
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
      sed -i 's=GTN_APP_DATA_PATH='$GTN_APP_DATA_PATH'=g' /etc/init.d/$server_name
      sed -i 's=JAVA7_HOME='$JAVA7_HOME'=g' /etc/init.d/$server_name

      sed -i 's/ws_service_name/'$ws_service_name'/g' /etc/init.d/$ws_service_name
      sed -i 's/Base_Path/'$base_path'/g' /etc/init.d/$ws_service_name
      sed -i 's=GTN_APP_DATA_PATH='$GTN_APP_DATA_PATH'=g' /etc/init.d/$ws_service_name


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
      log_file="$GTN_APP_DATA_PATH/logs/DB/$DB_Schema_Name/$DB_Script_Name/Log_"$DB_Script_Name"_$LOGDATE.log"
      mkdir -p "$GTN_APP_DATA_PATH/logs/DB/$DB_Schema_Name/$DB_Script_Name"
      chmod 777 "$GTN_APP_DATA_PATH/logs/DB/$DB_Schema_Name/$DB_Script_Name"
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

      # Java installation

      if rpm -qa | grep java-1.8;then
      echo "JAVA 1.8 JDK software already exists"
      else
      echo "JAVA 1.8 JDK software does not exists"
      rpm -ivh $install_path/tempdeploy/JAVA_Setup/jdk-8u151-linux-x64.rpm
      echo "JAVA 1.8 JDK software has installed"
      fi

      if [ -d "/opt/jdk1.7.0_80" ];then
      echo "JAVA 1.7 JDK software tar file already exists"
      else
      echo "JAVA 1.7 JDK software tar file does not exists"
      tar -xvzf $install_path/tempdeploy/JAVA_Setup/jdk-7u80-linux-x64.tar.gz -C /opt/
      echo "JAVA 1.7 JDK software tar has extracted"
      fi

      # Change home dirctory
      usermod -m -d $install_path $APP_User  

      # tempdeploy cleanup

      if [ -d "$install_path/tempdeploy" ];then
      rm -rf $install_path/tempdeploy/JAVA_Setup
      fi

      if [ -f "$install_path/wildfly-10.0.0.Final.zip" ];then
      rm -rf $install_path/wildfly-10.0.0.Final.zip
      fi

      # OWNER GROUP CHANGES

      chmod -R 750 $install_path
      chown -R $APP_User:$APP_User $install_path

      chown -R $APP_User:etl $GTN_APP_DATA_PATH
      chmod -R 750 $GTN_APP_DATA_PATH
}

serverSetup(){
  preProcessServerSetup
  installServer
  postProcessServerSetup
}

serverSetup

preProcessAppRelease(){
        
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
}

installApp(){
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
        mkdir -p  $RPM_BUILD_ROOT%{prefix}/conf/
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
        if [ -e  $RPM_BUILD_DIR/$RPM_PACKAGE_NAME-$RPM_PACKAGE_VERSION/Application_Build/interfaceUrlMapping.properties ]
        then
        mkdir -p $RPM_BUILD_ROOT%{prefix}/conf/ETL-InterfaceUriConfig/
          cp $RPM_BUILD_DIR/$RPM_PACKAGE_NAME-$RPM_PACKAGE_VERSION/Application_Build/interfaceUrlMapping.properties  $RPM_BUILD_ROOT%{prefix}/conf/ETL-InterfaceUriConfig/
        chmod -R 777 $RPM_BUILD_ROOT%{prefix}/conf/ETL-InterfaceUriConfig/interfaceUrlMapping.properties
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
}

postProcessAppRelease(){
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
      sed -i -e "/BPI_SOURCE_PASSWORD=/ s/=.*/=$BPI_SOURCE_PASSWORD/" -e  "/ETL_PORT_NO=/ s/=.*/=$ETL_PORT_NO/" -e "/com_stpl_gtnframework_base_path=/ s/=.*/=$com_stpl_gtnframework_base_path/" $install_path/etl/Interface_Job/EtlConfiguration.properties
      sed -i -e "/ETL_LOG_PATH=/ s/=.*/=$ETL_LOG_PATH/"  -e "/BPI_PASSPHRASE=/ s/=.*/=$BPI_PASSPHRASE/" $install_path/etl/Interface_Job/EtlConfiguration.properties
      fi
      # Deleting Service jar index files ends

      #Moving Transaction JSON

      if [ -e $install_path/tempdeploy/GtnFrameworkTransaction.jar ];
      then
      mkdir -p $Gtn_Framework_Base_path/transaction_json
      cd $Gtn_Framework_Base_path
      jar -xvf $install_path/tempdeploy/GtnFrameworkTransaction.jar >/dev/null
      rm -rf $Gtn_Framework_Base_path/WEB-INF $Gtn_Framework_Base_path/META-INF $Gtn_Framework_Base_path/OSGI-INF $Gtn_Framework_Base_path/com 
      chown -R $APP_User:$Chown $Gtn_Framework_Base_path
      cd -
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
      log_file="$GTN_APP_DATA_PATH/logs/DB/$DB_Schema_Name/$DB_Script_Name/Log_"$DB_Script_Name"_$LOGDATE.log"
      mkdir -p "$GTN_APP_DATA_PATH/logs/DB/$DB_Schema_Name/$DB_Script_Name"
      chmod 777 "$GTN_APP_DATA_PATH/logs/DB/$DB_Schema_Name/$DB_Script_Name"
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

      chown -R $APP_User:etl $GTN_APP_DATA_PATH
      chmod -R 750 $GTN_APP_DATA_PATH
}

installAppRelease(){
  preProcessAppRelease
  installApp
  postProcessAppRelease
}

# installAppRelease


%pre 


%post


%files
%{prefix}/
