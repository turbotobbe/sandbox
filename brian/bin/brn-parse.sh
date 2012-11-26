#!/bin/bash

DIR_BIN=`dirname $0`
DIR_WWW=/home/tobbe/www/brian/data

DIR_LC_SAMPLES=${DIR_WWW}/large-cap/samples
DIR_MC_SAMPLES=${DIR_WWW}/mid-cap/samples
DIR_SC_SAMPLES=${DIR_WWW}/small-cap/samples

DIR_LC_QUOTES=${DIR_WWW}/large-cap/quotes
DIR_MC_QUOTES=${DIR_WWW}/mid-cap/quotes
DIR_SC_QUOTES=${DIR_WWW}/small-cap/quotes

DATE=$1
TIME=$2
TIME_MOD=`echo ${TIME} | tr '-' ':'`
function parse() {
    local FILE_HTML=$1
    local DIR_QUOTES=$2
    echo "Parse: ${FILE_HTML}"
    mkdir -p ${DIR_QUOTES}/${DATE}
    for LINE in `/usr/bin/xsltproc --html --stringparam fetch.date ${DATE} --stringparam fetch.time ${TIME_MOD} ${DIR_BIN}/brn-parse-csv.xsl ${FILE_HTML}`; do
	local KEY=`echo ${LINE} | cut -d ',' -f 8 | sed 's/"key":"//g' | sed 's/"//g'`
	echo ${LINE} | cut -d ',' -f 1-7 >> ${DIR_QUOTES}/${DATE}/${KEY}.csv
    done
}

parse ${DIR_LC_SAMPLES}/${DATE}/${TIME}.html ${DIR_LC_QUOTES}
parse ${DIR_MC_SAMPLES}/${DATE}/${TIME}.html ${DIR_MC_QUOTES}
parse ${DIR_SC_SAMPLES}/${DATE}/${TIME}.html ${DIR_SC_QUOTES}

${DIR_BIN}/brn-build.sh
