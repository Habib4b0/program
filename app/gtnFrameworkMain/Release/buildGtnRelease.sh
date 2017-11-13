scriptname=$0
noOfArg=$#
scmusername=$1
scmbranchname=$2
scmrepositoryname=$3
buildfolder=$4
if [ -z $buildfolder ]; then
   buildfolder=/root/gtnFramework/build
fi
function exportParams(){
        export GIT_SSL_NO_VERIFY=true
        export GTN_BUILD_BASE=$buildfolder
        export GTN_BUILD_MAVEN_REPO=$GTN_BUILD_BASE/project/Repository
}

function usage(){
if [ $noOfArg -lt 3 ]; then
    echo "Usage: $scriptname <username> <branchname> <repository> [buildfolder]"
    echo "* username: scm username without .sysbiz.org"
    echo "* branch: branch from which build to be executed say develop"
    echo "* repository: repository name say contract-management"
    echo "* build folder: optional. By default /root/gtnFramework/build will be used "
fi
}

function printArg(){
        echo "==============Arguments========="
        echo scmusername = $scmusername
        echo scmbranchname = $scmbranchname
        echo scmrepositoryname = $scmrepositoryname
        echo buildfolder = $buildfolder
}

function buildRelease(){
        echo Removing project folder
        rm -rf $GTN_BUILD_BASE/project

        echo Cloning Git $scmbranchname branch
        git clone -b $scmbranchname  https://$scmusername%40sysbiz.org@scm.sysbiz.org/scm/gal/$scmrepositoryname.git $GTN_BUILD_BASE/project

        cd $GTN_BUILD_BASE/project/Release/WarReleasePlugin
        echo Build WarRelease
        mvn clean install -Dmaven.repo.local=$GTN_BUILD_MAVEN_REPO -DskipTests

        cd $GTN_BUILD_BASE/project

        echo Maven Building projects
        mvn -PRelease-Build -Dmaven.repo.local=$GTN_BUILD_MAVEN_REPO -DskipTests

}
usage
if [ $noOfArg -ge 3 ]; then
        printArg
        exportParams
        buildRelease
fi
