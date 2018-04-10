%global _enable_debug_package 0
%global debug_package %{nil}
%global __os_install_post /usr/lib/rpm/brp-compress %{nil}
%define _binary_payload w6.gzdio
%global _binaries_in_noarch_packages_terminate_build 0
%define _unpackaged_files_terminate_build 0

Name:SPEC_NAME
Version:SPEC_VERSION
Release:        2%{?dist}
Summary: Webservice  Jboss and Migration RPM for GTN_Framework
BuildArchitectures: noarch
License:  GPLv3+
Source0:SPEC_SOURCE
Prefix: /opt/bpigtn_ws
BuildArch: noarch

%description
Test description

%prep

%setup -q


%build
echo "Inside Build Method"

echo %{prefix}
%install
echo installing
echo $RPM_BUILD_ROOT
echo $RPM_BUILD_DIR
echo %{prefix}

mkdir -p  $RPM_BUILD_ROOT%{prefix}/conf
mkdir -p  $RPM_BUILD_ROOT%{prefix}/jboss-7.1.1/standalone/deployments/
mkdir -p $RPM_BUILD_ROOT%{prefix}/tempdeploy
chmod -R 777 $RPM_BUILD_ROOT%{prefix}/tempdeploy
echo %{prefix}

if [ -d "$RPM_BUILD_DIR/$RPM_PACKAGE_NAME-$RPM_PACKAGE_VERSION/GTN_Framework_Server/"  ] ; then
cp -R  $RPM_BUILD_DIR/$RPM_PACKAGE_NAME-$RPM_PACKAGE_VERSION/GTN_Framework_Server/*   $RPM_BUILD_ROOT%{prefix}/
cp -R  $RPM_BUILD_DIR/$RPM_PACKAGE_NAME-$RPM_PACKAGE_VERSION/JBoss_User_Configuration/*   $RPM_BUILD_ROOT%{prefix}/conf/

#Moving JBOSS configurations

cp -R $RPM_BUILD_DIR/$RPM_PACKAGE_NAME-$RPM_PACKAGE_VERSION/JBoss_Configuration/*  $RPM_BUILD_ROOT%{prefix}/jboss-7.1.1/

# Create Log directories

mkdir $RPM_BUILD_ROOT%{prefix}/logs/
mkdir -p $RPM_BUILD_ROOT%{prefix}/logs/web_service/boot_temp
mkdir -p $RPM_BUILD_ROOT%{prefix}/logs/web_service/jboss-as2
chmod -R 755 $RPM_BUILD_ROOT%{prefix}/*

touch $RPM_BUILD_ROOT%{prefix}/conf/jboss-as2/jboss-as-standalone.pid
chmod -R 777 $RPM_BUILD_ROOT%{prefix}/conf/jboss-as2/*
cp -R $RPM_BUILD_DIR/$RPM_PACKAGE_NAME-$RPM_PACKAGE_VERSION/GTN_Framework_War/*  $RPM_BUILD_ROOT%{prefix}/tempdeploy
chmod -R 777 $RPM_BUILD_ROOT%{prefix}/jboss-7.1.1/*
else 

cp -R $RPM_BUILD_DIR/$RPM_PACKAGE_NAME-$RPM_PACKAGE_VERSION/GTN_Framework_War/*  $RPM_BUILD_ROOT%{prefix}/tempdeploy

fi

%pre 

   if [ -z "$DB_Host" ] 
    then 
   echo Required Environment variables not set Plese run . ./Profile_ws.sh
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
if grep -q GTN_FRAMEWORK_BASE_PATH "$install_path/jboss-7.1.1/standalone/configuration/standalone.xml"; then
chown -R $APP_User:$Chown  $install_path
fi


server_name=$(basename $install_path)
INPUT=$install_path
base_path=$(echo $INPUT| cut -d'/' -f 2)
FILES="$install_path/tempdeploy/*"
for f in $FILES
do
	echo "Moving  $f"
 currentfile=$(basename $f)
      
if [ -e  $install_path/jboss-7.1.1/standalone/deployments/$currentfile.deployed ];
then
mv  $install_path/jboss-7.1.1/standalone/deployments/$currentfile.deployed $install_path/jboss-7.1.1/standalone/deployments/$currentfile.undeployed
rm -rf $install_path/jboss-7.1.1/standalone/deployments/$currentfile.undeployed
rm -rf $install_path/jboss-7.1.1/standalone/deployments/$currentfile*
fi

while :
do
cp  $f  $install_path/jboss-7.1.1/standalone/deployments/
chown -R $APP_User:$Chown $install_path
   echo "deploying ...................." $currentfile
   sleep 1
   if [ -e  $install_path/jboss-7.1.1/standalone/deployments/$currentfile.deployed ];
    
   then
echo "deployed ***********************"$currentfile
	break
elif [ -e  $install_path/jboss-7.1.1/standalone/deployments/$currentfile.failed ];
then
echo "failed "$currentfile
break       	   
   fi
done
done

 
standalone_xml_path=$install_path/jboss-7.1.1/standalone/configuration/standalone.xml
if grep -q GTN_FRAMEWORK_BASE_PATH "$install_path/jboss-7.1.1/standalone/configuration/standalone.xml"; then
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
sed -i 's='0.0.0.0'='127.0.0.1'=g' $install_path/jboss-7.1.1/standalone/configuration/standalone.xml
sed -i 's='com.stpl.portal'='net.sourceforge.jtds'=g' $install_path/jboss-7.1.1/standalone/configuration/standalone.xml

if [ -z "$PORT_OFFSET" ];
then 
PORT_OFFSET=0
fi
sed -i 's/PORT_OFFSET/'$PORT_OFFSET'/g' $install_path/jboss-7.1.1/standalone/configuration/standalone.xml

#do_Jboss_Conf
jboss_env_conf=$install_path/conf/jboss-as2/jboss-as.conf
chmod 777  $jboss_env_conf
 sed -i 's/Server_Name/'$server_name'/g' $jboss_env_conf
 sed -i 's/Base_Path/'$base_path'/g' $jboss_env_conf
 sed -i 's/CELAPP/'$APP_User'/g' $jboss_env_conf

fi
rm -rf $install_path/jboss-7.1.1/standalone/deployments/ROOT.war*
chmod -R 750 $install_path
chown -R $APP_User:$Chown $install_path

%files

 %{prefix}/





