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
Source0: GtnSpecs
Prefix:/opt/bpigtn
BuildArch: noarch
%description
Test description

%prep

%setup -q


%build
echo "Inside Build Method"

%install

printIam()
{
  echo "I am Called"
}

printIam


%pre 

%post


%files
%{prefix}/
