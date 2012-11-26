#!/bin/bash

DIR_BIN=`dirname $0`
DIR_WWW=/home/tobbe/www/brian

DIR_LC_SAMPLES=${DIR_WWW}/large-cap/samples
DIR_MC_SAMPLES=${DIR_WWW}/mid-cap/samples
DIR_SC_SAMPLES=${DIR_WWW}/small-cap/samples

#DIR_LC_QUOTES=${DIR_WWW}/large-cap/quotes
#DIR_MC_QUOTES=${DIR_WWW}/mid-cap/quotes
#DIR_SC_QUOTES=${DIR_WWW}/small-cap/quotes

URL_LC="http://ir.svenskborsinfo.se/sbiir/tidningar/nordiclist/sharelist.page?magic=%28cc%20%28list%20sselarge%29%29"
URL_MC="http://ir.svenskborsinfo.se/sbiir/tidningar/nordiclist/sharelist.page?magic=%28cc%20%28list%20norlistSEEMid%29%29"
URL_SC="http://ir.svenskborsinfo.se/sbiir/tidningar/nordiclist/sharelist.page?magic=%28cc%20%28list%20norlistSSESmall%29%29"

DATE=`date +%Y-%m-%d`
TIME=`date +%H-%M`

function fetch() {
    local FILE_HTML=$1
    local URL=$2
    echo "Fetch: ${FILE_HTML}"
    mkdir -p `dirname ${FILE_HTML}`
    /usr/bin/wget -q -O- ${URL} \
	| /usr/bin/iconv -f ISO-8859-1 -t UTF-8 \
	| sed 's/iso-8859-1/UTF-8/g' | sed "s/marquee/p/g" \
	| tr '&' 'o'  > ${FILE_HTML}
}

fetch ${DIR_LC_SAMPLES}/${DATE}/${TIME}.html ${URL_LC}
fetch ${DIR_MC_SAMPLES}/${DATE}/${TIME}.html ${URL_MC}
fetch ${DIR_SC_SAMPLES}/${DATE}/${TIME}.html ${URL_SC}

${DIR_BIN}/brn-parse.sh ${DATE} ${TIME}
