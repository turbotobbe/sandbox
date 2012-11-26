#!/bin/bash

DIR_BIN=`dirname $0`
DIR_WWW=/home/tobbe/www/brian/data

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

    ${DIR_BIN}/brn-names.sh

    if [ -s ${DIR}/names.csv ]; then

	mkdir -p ${DIR_QUOTES}

        # dates
	echo "Dates:  (csv) ${DIR}/dates.csv"
	ls ${DIR_QUOTES} | egrep "[0-9]+-[0-9]+-[0-9]+" > ${DIR}/dates.csv

	echo "Dates: (json) ${DIR}/dates.json"
	local PAD=""
	echo -n "{\"dates\":[" > ${DIR}/dates.json
	while read LINE; do
	    echo "$PAD" >> ${DIR}/dates.json
	    echo -n "  \"$LINE\"" >> ${DIR}/dates.json
	    PAD=","
	done < ${DIR}/dates.csv
	echo "]}" >> ${DIR}/dates.json

        # quotes
	echo "Build: (json) ${DIR_QUOTES}/${DATE}"
	for KEY in `cat ${DIR}/names.csv | cut -d ',' -f 1`; do
	    #echo "Build: (json) ${DIR_QUOTES}/${DATE}/${KEY}.json"
    	    local PAD=""
    	    echo -n "{\"quotes\":[" > ${DIR_QUOTES}/${DATE}/${KEY}.json
	    for LINE in `cat ${DIR_QUOTES}/${DATE}/${KEY}.csv`; do
    		echo "$PAD" >> ${DIR_QUOTES}/${DATE}/${KEY}.json
    		echo -n "{$LINE}" >> ${DIR_QUOTES}/${DATE}/${KEY}.json
    		PAD=","
    	    done
    	    echo "]}" >> ${DIR_QUOTES}/${DATE}/${KEY}.json
	done
    fi
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
