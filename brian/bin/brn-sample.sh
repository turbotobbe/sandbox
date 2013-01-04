#!/bin/bash

APP_NAME=`basename $0`
DIR_BIN=`dirname $0`
DIR_WWW=/home/tobbe/www/brian/data

DIR_LC_SAMPLES=${DIR_WWW}/large-cap/samples
DIR_MC_SAMPLES=${DIR_WWW}/mid-cap/samples
DIR_SC_SAMPLES=${DIR_WWW}/small-cap/samples

DIR_LC_QUOTES=${DIR_WWW}/large-cap/quotes
DIR_MC_QUOTES=${DIR_WWW}/mid-cap/quotes
DIR_SC_QUOTES=${DIR_WWW}/small-cap/quotes

URL_LC="http://ir.svenskborsinfo.se/sbiir/tidningar/nordiclist/sharelist.page?magic=%28cc%20%28list%20sselarge%29%29"
URL_MC="http://ir.svenskborsinfo.se/sbiir/tidningar/nordiclist/sharelist.page?magic=%28cc%20%28list%20norlistSEEMid%29%29"
URL_SC="http://ir.svenskborsinfo.se/sbiir/tidningar/nordiclist/sharelist.page?magic=%28cc%20%28list%20norlistSSESmall%29%29"

NOW_DATE=`date +%Y-%m-%d`
NOW_TIME=`date +%H-%M`

FILE_HTML_LC=${DIR_LC_SAMPLES}/${NOW_DATE}/${NOW_TIME}.html 
FILE_HTML_MC=${DIR_MC_SAMPLES}/${NOW_DATE}/${NOW_TIME}.html 
FILE_HTML_SC=${DIR_SC_SAMPLES}/${NOW_DATE}/${NOW_TIME}.html 

NOW_TIME=`echo $NOW_TIME | tr '-' ':'`

DIR_CSV_LC=${DIR_LC_QUOTES}/${NOW_DATE}
DIR_CSV_MC=${DIR_MC_QUOTES}/${NOW_DATE}
DIR_CSV_SC=${DIR_SC_QUOTES}/${NOW_DATE}

function msg() {
    local STAMP=`date "+%Y-%m-%d %H:%M:%S"`
    echo "[${APP_NAME} @ ${STAMP}] $1"
}

function fetch() {
    local URL=$1
    local FILE_HTML=$2
    msg "Fetch: ${FILE_HTML}"
    mkdir -p `dirname ${FILE_HTML}`
    /usr/bin/wget -q -O- ${URL} | /usr/bin/iconv -f ISO-8859-1 -t UTF-8 | sed 's/iso-8859-1/UTF-8/g' | sed "s/marquee/p/g" | tr '&' 'o'  > ${FILE_HTML}
}

function parse() {
    local FILE_HTML=$1
    local DIR_CSV=$2
    msg "Parse: ${FILE_HTML} > ${DIR_CSV}"
    mkdir -p ${DIR_CSV}
    for LINE in `/usr/bin/xsltproc --html --stringparam time ${NOW_TIME} ${DIR_BIN}/brn-sample-csv.xsl ${FILE_HTML}`; do
	KEY=${LINE%%,*}
	LINE=${LINE#*,}
	echo ${LINE} >> ${DIR_CSV}/${KEY}.csv
    done
}

fetch ${URL_LC} ${FILE_HTML_LC}
fetch ${URL_MC} ${FILE_HTML_MC}
fetch ${URL_SC} ${FILE_HTML_SC}

parse ${FILE_HTML_LC} ${DIR_CSV_LC}
parse ${FILE_HTML_MC} ${DIR_CSV_MC}
parse ${FILE_HTML_SC} ${DIR_CSV_SC}
