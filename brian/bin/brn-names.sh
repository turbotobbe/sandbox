#!/bin/bash

DIR_BIN=`dirname $0`
DIR_WWW=/home/tobbe/www/brian/data

DIR_LC=${DIR_WWW}/large-cap
DIR_MC=${DIR_WWW}/mid-cap
DIR_SC=${DIR_WWW}/small-cap

DIR_LC_QUOTES=${DIR_WWW}/large-cap/quotes
DIR_MC_QUOTES=${DIR_WWW}/mid-cap/quotes
DIR_SC_QUOTES=${DIR_WWW}/small-cap/quotes

function names() {
    local DIR=$1

    if [ ! -s ${DIR}/names.csv ]; then
	echo "Names:  (csv) ${DIR}/names.csv"
	echo -n "" > /tmp/brn-names.csv
	for FILE in `find ${DIR} -name "*.html"`; do
	    /usr/bin/xsltproc --html ${DIR_BIN}/brn-names-csv.xsl ${FILE} >> /tmp/brn-names.csv
	done
	cat /tmp/brn-names.csv | sort | uniq > ${DIR}/names.csv

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
}

names ${DIR_LC}
names ${DIR_MC}
names ${DIR_SC}
