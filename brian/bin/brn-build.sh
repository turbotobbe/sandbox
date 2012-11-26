#!/bin/bash

DIR_BIN=`dirname $0`
DIR_WWW=/home/tobbe/www/brian

DIR_LC=${DIR_WWW}/large-cap
DIR_MC=${DIR_WWW}/mid-cap
DIR_SC=${DIR_WWW}/small-cap

DIR_LC_QUOTES=${DIR_WWW}/large-cap/quotes
DIR_MC_QUOTES=${DIR_WWW}/mid-cap/quotes
DIR_SC_QUOTES=${DIR_WWW}/small-cap/quotes

function build() {
    local DIR=$1
    local DIR_QUOTES=$2
    local DATE=$3

    mkdir -p ${DIR_QUOTES}

    # dates
    echo "Dates:  (csv) ${DIR}/dates.csv"
    ls ${DIR_QUOTES} | egrep "[0-9]+-[0-9]+-[0-9]+" > ${DIR}/dates.csv

    local PAD=""
    echo "Dates: (json) ${DIR}/dates.json"
    echo -n "{\"dates\":[" > ${DIR}/dates.json
    while read LINE; do
	echo "$PAD" >> ${DIR}/dates.json
	echo -n "  \"$LINE\"" >> ${DIR}/dates.json
	PAD=","
    done < ${DIR}/dates.csv
    echo "]}" >> ${DIR}/dates.json

    # names
    if [ ! -s ${DIR}/names.csv ]; then
	echo "Names:  (csv) ${DIR}/names.csv"
	echo -n "" > /tmp/brn-build.csv
	for FILE in `find ${DIR} -name *.html`; do
	    /usr/bin/xsltproc --html ${DIR_BIN}/brn-build-csv.xsl ${FILE} >> /tmp/brn-build.csv
	done
	cat /tmp/brn-build.csv | sort | uniq > ${DIR}/names.csv
    fi

    if [ ! -s ${DIR}/names.json ]; then
	local PAD=""
	echo "Names: (json) ${DIR}/names.json"
	echo -n "{\"names\":[" > ${DIR}/names.json
	while read LINE; do
	    local KEY=`echo $LINE | cut -d ',' -f 1`
	    local NAME=`echo $LINE | cut -d ',' -f 2`
	    echo "$PAD" >> ${DIR}/names.json
	    echo -n "  {\"key\":\"$KEY\",\"name\":\"$NAME\"}" >> ${DIR}/names.json
	    PAD=","
	done < ${DIR}/names.csv
	echo "]}" >> ${DIR}/names.json
    fi

    # quotes
    echo "Build: (json) ${DIR_QUOTES}/${DATE}"
    while read KEY_AND_NAME; do
    	local KEY=`echo $KEY_AND_NAME | cut -d ',' -f 1`
    	local PAD=""
    	echo -n "{\"quotes\":[" > ${DIR_QUOTES}/${DATE}/${KEY}.json
    	while read LINE; do
    	    local FETCH_DATE=`echo $LINE | cut -d ',' -f 1`
    	    local FETCH_TIME=`echo $LINE | cut -d ',' -f 2`
    	    local TIME=`echo $LINE | cut -d ',' -f 3`
    	    local VOLUME=`echo $LINE | cut -d ',' -f 4`
    	    local LATEST=`echo $LINE | cut -d ',' -f 5`
    	    local BUY=`echo $LINE | cut -d ',' -f 6`
    	    local SELL=`echo $LINE | cut -d ',' -f 7`
    	    echo "$PAD" >> ${DIR_QUOTES}/${DATE}/${KEY}.json
    	    echo -n "  {\"fetch_date\":\"$FETCH_DATE\",\"fetch_time\":\"$FETCH_TIME\",\"time\":\"$TIME\",\"volume\":\"$VOLUME\",\"latest\":\"$LATEST\",\"buy\":\"$BUY\",\"sell\":\"$SELL\"}" >> ${DIR_QUOTES}/${DATE}/${KEY}.json
    	    PAD=","
    	done < ${DIR_QUOTES}/${DATE}/${KEY}.csv
    	echo "]}" >> ${DIR_QUOTES}/${DATE}/${KEY}.json
    done < ${DIR}/names.csv
}

if [ $# == 0 ]; then
    DATE=`date +%Y-%m-%d`
    build ${DIR_LC} ${DIR_LC_QUOTES} ${DATE}
    build ${DIR_MC} ${DIR_MC_QUOTES} ${DATE}
    build ${DIR_SC} ${DIR_SC_QUOTES} ${DATE}
else
    while [ "$1" ]; do
	DATE=$1
	build ${DIR_LC} ${DIR_LC_QUOTES} ${DATE}
	build ${DIR_MC} ${DIR_MC_QUOTES} ${DATE}
	build ${DIR_SC} ${DIR_SC_QUOTES} ${DATE}
	shift
    done
fi
