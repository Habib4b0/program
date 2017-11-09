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



mkdir -p  $RPM_BUILD_ROOT%{prefix}/etc/init.d

cp $RPM_BUILD_DIR/$RPM_PACKAGE_NAME-$RPM_PACKAGE_VERSION/Service_File/bpigtn  $RPM_BUILD_ROOT%{prefix}/etc/init.d/%{_servicename}
cp $RPM_BUILD_DIR/$RPM_PACKAGE_NAME-$RPM_PACKAGE_VERSION/Service_File/bpigtn_ws  $RPM_BUILD_ROOT%{prefix}/etc/init.d/%{_wsservicename}


%pre 

%post
echo given prefix $RPM_INSTALL_PREFIX
install_path=$RPM_INSTALL_PREFIX
if [ -z "$RPM_INSTALL_PREFIX" ] 
then 
install_path=%{prefix}
fi
server_name=$(basename $install_path)


INPUT=$install_path
base_path=$(echo $INPUT| cut -d'/' -f 2)




#do_Init_Service

echo service Name $server_name
echo web service Name $ ws_service_name
mv $install_path/etc/init.d/%{_servicename} /etc/init.d/$server_name
mv $install_path/etc/init.d/%{_wsservicename} /etc/init.d/%{_wsservicename}

rm -rf $install_path/etc/init.d/


chmod 770 /etc/init.d/$server_name
chmod 770 /etc/init.d/%{_wsservicename}

sed -i 's/Server_Name/'$server_name'/g' /etc/init.d/$server_name
sed -i 's/Base_Path/'$base_path'/g' /etc/init.d/$server_name
sed -i 's/ws_service_name/'$ws_service_name'/g' /etc/init.d/$server_name

sed -i 's/ws_service_name/'$ws_service_name'/g' /etc/init.d/%{_wsservicename}
sed -i 's/Base_Path/'$base_path'/g' /etc/init.d/%{_wsservicename}


%files

%{prefix}/




