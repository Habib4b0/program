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

mkdir -p  $RPM_BUILD_ROOT%{prefix}/jboss-7.1.1/standalone/deployments/
echo %{prefix}

if [ -d "$RPM_BUILD_DIR/$RPM_PACKAGE_NAME-$RPM_PACKAGE_VERSION/GTN_Framework_Server/"  ] ; then
cp -R  $RPM_BUILD_DIR/$RPM_PACKAGE_NAME-$RPM_PACKAGE_VERSION/GTN_Framework_Server/*   $RPM_BUILD_ROOT%{prefix}/
cp -R $RPM_BUILD_DIR/$RPM_PACKAGE_NAME-$RPM_PACKAGE_VERSION/GTN_Framework_War/*  $RPM_BUILD_ROOT%{prefix}/jboss-7.1.1/standalone/deployments/
chmod -R 777 $RPM_BUILD_ROOT%{prefix}/jboss-7.1.1/*
else 

cp -R $RPM_BUILD_DIR/$RPM_PACKAGE_NAME-$RPM_PACKAGE_VERSION/GTN_Framework_War/*  $RPM_BUILD_ROOT%{prefix}/jboss-7.1.1/standalone/deployments/

fi


%post
echo given prefix $RPM_INSTALL_PREFIX
install_path=$RPM_INSTALL_PREFIX
if [ -z "$RPM_INSTALL_PREFIX" ] 
then 
install_path=%{prefix}
fi
chmod -R 755  $install_path/*
chown -R $Chown:$Chown  $install_path
if [ -z "$DB_Host" ] 
then 
echo "$DB_Host" not set
else

server_name=$(basename $install_path)
INPUT=$install_path
base_path=$(echo $INPUT| cut -d'/' -f 2)

standalone_xml_path=$install_path/jboss-7.1.1/standalone/configuration/standalone.xml
chmod 777 $install_path/jboss-7.1.1/standalone/configuration/standalone.xml
sed -i 's/Base_Path/'$base_path'/g' $install_path/jboss-7.1.1/standalone/configuration/standalone.xml
sed -i 's/Server_Name/'$server_name'/g' $install_path/jboss-7.1.1/standalone/configuration/standalone.xml
sed -i 's/DB_IP/'$DB_Host'/g' $install_path/jboss-7.1.1/standalone/configuration/standalone.xml
sed -i 's/DB_User_Name/'$DB_User_Name'/g' $install_path/jboss-7.1.1/standalone/configuration/standalone.xml
sed -i 's=DB_Password='$DB_Password'=g' $install_path/jboss-7.1.1/standalone/configuration/standalone.xml
sed -i 's/APP_TST/'$DB_APP_Name'/g' $install_path/jboss-7.1.1/standalone/configuration/standalone.xml
sed -i 's/SYS_TST/'$DB_SYS_Name'/g' $install_path/jboss-7.1.1/standalone/configuration/standalone.xml
sed -i 's/BPM_TST/'$DB_BPM_Name'/g' $install_path/jboss-7.1.1/standalone/configuration/standalone.xml
sed -i 's=GTN_FRAMEWORK_BASE_PATH='$Gtn_Framework_Base_path'=g' $install_path/jboss-7.1.1/standalone/configuration/standalone.xml
 fi
chown -R $Chown:$Chown  $install_path
%files

 %{prefix}/jboss-7.1.1/





