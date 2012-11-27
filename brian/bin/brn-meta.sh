#!/bin/bash

APP_NAME=`basename $0`
DIR_BIN=`dirname $0`
DIR_WWW=/home/tobbe/www/brian/data

DIR_LC=${DIR_WWW}/large-cap
DIR_MC=${DIR_WWW}/mid-cap
DIR_SC=${DIR_WWW}/small-cap

DIR_LC_SAMPLE=${DIR_LC}/samples
DIR_MC_SAMPLE=${DIR_MC}/samples
DIR_SC_SAMPLE=${DIR_SC}/samples

NOW_DATE=`date +%Y-%m-%d`

function msg() {
    local STAMP=`date "+%Y-%m-%d %H:%M:%S"`
    echo "[${APP_NAME} @ ${STAMP}] $1"
}

function dates() {
    local DIR_SAMPLE=$1
    local DIR_TARGET=$2
    if [ ! -d ${DIR_SAMPLE} ]; then
	msg "Dates: Not Found! ${DIR_SAMPLE}"
	return
    else
	msg "Dates: ${DIR_TARGET}/dates.json"
    fi
    ls ${DIR_SAMPLE} | egrep "[0-9]+-[0-9]+-[0-9]+" | sort > ${DIR_TARGET}/dates.csv

    echo -n "{\"dates\":[" > ${DIR_TARGET}/dates.json
    PAD=""
    while read LINE; do
	echo -n "${PAD}\"${LINE}\"" >> ${DIR_TARGET}/dates.json
	PAD=","
    done < ${DIR_TARGET}/dates.csv
    echo -n "]}" >> ${DIR_TARGET}/dates.json
}

function names() {
    local DIR_SAMPLE=$1
    local DIR_TARGET=$2
    if [ ! -d ${DIR_SAMPLE} ]; then
	msg "Names: Not Found! ${DIR_SAMPLE}"
	return
    else
	msg "Names: ${DIR_TARGET}/names.json"
    fi
    echo -n "" >> /tmp/brn-meta.csv
    for FILE_HTML in `find ${DIR_SAMPLE} -name "*.html"`; do
	/usr/bin/xsltproc --html ${DIR_BIN}/brn-meta-csv.xsl ${FILE_HTML} >> /tmp/brn-meta.csv
    done
    cat /tmp/brn-meta.csv | sort | uniq > ${DIR_TARGET}/names.csv
    rm /tmp/brn-meta.csv

    echo -n "{\"names\":[" > ${DIR_TARGET}/names.json
    PAD=""
    while read LINE; do
	KEY=${LINE%%,*}
	LINE=${LINE#*,}
	echo -n "${PAD}{\"key\":\"$KEY\",\"name\":\"$LINE\"}" >> ${DIR_TARGET}/names.json
	PAD=","
    done < ${DIR_TARGET}/names.csv
    echo -n "]}" >> ${DIR_TARGET}/names.json
}

names ${DIR_LC_SAMPLE} ${DIR_LC}
names ${DIR_MC_SAMPLE} ${DIR_MC}
names ${DIR_SC_SAMPLE} ${DIR_SC}

dates ${DIR_LC_SAMPLE} ${DIR_LC}
dates ${DIR_MC_SAMPLE} ${DIR_MC}
dates ${DIR_SC_SAMPLE} ${DIR_SC}
