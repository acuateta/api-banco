FROM mysql:8.0
LABEL maintainer="acuateta@gmail.com"

ENV MYSQL_ROOT_PASSWORD=hooch
ENV MYSQL_DATABASE=bancoDB
ENV MYSQL_ROOT_HOST=%

COPY ./scripts-mysql/ /docker-entrypoint-initdb.d/
CMD ["--default-authentication-plugin=mysql_native_password"]

#EXPOSE 3306