GAUGE_LATEST=`curl -w "%{url_effective}\n" -L -s -S https://github.com/getgauge/gauge/releases/latest -o /dev/null`

GAUGE_LATEST_VERSION=`echo $GAUGE_LATEST | sed 's/.*\/v//'`

BIT=`uname -m`

if [ "$BIT"=="x86_64" ];
then 
	GAUGE_FILE_NAME="gauge-$GAUGE_LATEST_VERSION-linux.x86_64.zip"
else
	GAUGE_FILE_NAME="gauge-$GAUGE_LATEST_VERSION-linux.x86.zip"
fi

GAUGE_DOWNLOAD_URL="https://github.com/getgauge/gauge/releases/download/v$GAUGE_LATEST_VERSION/$GAUGE_FILE_NAME"

wget $GAUGE_DOWNLOAD_URL

OUTPUT_DIR="./gauge_$GAUGE_LATEST_VERSION"

unzip $GAUGE_FILE_NAME -d $OUTPUT_DIR

cd $OUTPUT_DIR

/bin/bash install.sh
