#!/usr/bin/env bash

set -xv

export FOLDER='ROUSSEAU_FERLICOT'
export BFOLDER=$FOLDER/'Insurance-back'
export FFOLDER=$FOLDER/'Insurance-front'
export ARCHIVES=$FOLDER/'Archives'

# clean

rm -rf {$FOLDER,$FOLDER.zip}

# Init out folder

mkdir $FOLDER

cp -R {README.md,deploy.sh,Insurance/web/sql} $FOLDER

# Init back folder

mkdir $BFOLDER

cp -R Insurance/{back,lib} $BFOLDER

# Init front folder

mkdir $FFOLDER

cp -R InsuranceFront/{lib,src,web}

# Init archives

mkdir $ARCHIVES

cp -R InsuranceFront/{lib/Insurance.jar,out/artifacts/InsuranceFront/InsuranceFront.war} $ARCHIVES

#Final cleaning

rm -rf $FOLDER/**/.DS_STORE

zip -r $FOLDER.zip $FOLDER
