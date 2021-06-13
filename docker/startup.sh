APPNAME=airlinescrawler
PORT=19086
docker build -t $APPNAME .
docker run -d --name $APPNAME -p $PORT:$PORT $APPNAME
