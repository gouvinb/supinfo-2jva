# Env
UNAME_S   := $(shell sh -c 'uname -s 2>/dev/null || echo not')

# Sources File
NAME       = 2JVA
MAIN_CLASS = com.supinfo.Main
SRC        = $(shell find src | grep .java)
OUT_DIR    = ./out/production/2JVA/

# Commands
JAVAC      = javac
JAVAFLAGS  = -d $(OUT_DIR)
RM         = rm -rf

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

clean:
	@echo "==> Clean $(NAME) ...";
	$(RM) $(OUT_DIR)*;

notify:
	@echo "==> Notify $(NAME) ...";
	$(NOTIFY);

run:
	@echo "==> Run $(NAME) ...";
	java -classpath $(shell pwd):$(OUT_DIR) $(MAIN_CLASS)

.PHONY: all build clean notify run
