#!/usr/bin/env bash

project=$1
packageDir=$2

if [ -z "$project" ]
then
    project='none_project'
fi

if [ -z "$packageDir" ]
then
    packageDir='/opt/target'
fi

package=`ls $packageDir|grep "$projectName"|sort|tail -1`
tar -xzvf $packageDir/$package
unpackage=`echo "$package"|sed 's/\.tar\.gz//'`

if [ -e "/opt/$project" ]
then
    rm -rf "/opt/$project"
fi
ln -s "$unpackage" "$project"