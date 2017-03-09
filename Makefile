# Env
UNAME_S      := $(shell sh -c 'uname -s 2>/dev/null || echo not')

# Sources File
NAME          = supinfo-2jva
MAIN_CLASS    = com.supinfo.Main
SRC           = $(shell find src | grep .java)
OUT_DIR       = ./out/production/supinfo-2jva/

# Class File
PATH_JAR      = out/artifacts/supinfo_2jva_jar/supinfo-2jva.jar
MANIFEST_FILE = out/production/supinfo-2jva/META-INF/MANIFEST.MF
CLASS         = $(shell find out/production/supinfo-2jva | grep .class)

# Commands
JAVAC         = javac
JAVAFLAGS     = -encoding utf8 -d $(OUT_DIR)
RM            = rm -rf

# Notifier
ifeq ($(UNAME_S),Darwin)
	NOTIFY = terminal-notifier -title Makefile  \
	         -subtitle "Job Finished"           \
	         -message "Check output"            \
	         -sound default                     \
	         -appIcon "https://code.visualstudio.com/images/favicon.ico"
endif

all: clean build

build:
	@echo "==> Build $(NAME) ...";
	$(JAVAC) $(JAVAFLAGS) $(SRC);

build-artifact:
	@echo "==> Build artifact $(NAME) ...";
	jar cvfm $(PATH_JAR) $(MANIFEST_FILE) $(CLASS);

clean:
	@echo "==> Clean $(NAME) ...";
	$(RM) $(OUT_DIR)*;

generate-keystore:
	@echo "==> Generate Keystore $(NAME) ...";
	keytool -genkey -alias com.supinfo -keyalg RSA -keystore KeyStore.jks -keysize 2048

notify:
	@echo "==> Notify $(NAME) ...";
	$(NOTIFY);

run:
	@echo "==> Run $(NAME) ...";
	java -classpath "$(shell pwd):$(OUT_DIR):$(shell pwd)/lib/mysql-connector-java-5.1.41-bin.jar" $(MAIN_CLASS)

signing:
	jarsigner -keystore KeyStore.jks $(PATH_JAR) com.supinfo

mysql:
	docker run --rm --name paradise -p 3306:3306 -e MYSQL_ROOT_PASSWORD=my-secret-pw -e MYSQL_DATABASE=paradise -d mysql

mysql-create:
	docker run --link paradise:mysql -v ${PWD}:/data --rm mysql sh -c         \
	'exec mysql -h"$$MYSQL_PORT_3306_TCP_ADDR" -P"$$MYSQL_PORT_3306_TCP_PORT" \
	-uroot -p"$$MYSQL_ENV_MYSQL_ROOT_PASSWORD" < /data/paradise.sql'

mysql-stop:
	docker stop paradise

.PHONY: all build build-artifact clean generate-keystore notify run signing mysql .PHONY