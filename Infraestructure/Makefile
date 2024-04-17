SHELL=/bin/bash

# define standard colors
ifneq (,$(findstring xterm,${TERM}))
	BLACK        := $(shell tput -Txterm setaf 0)
	RED          := $(shell tput -Txterm setaf 1)
	GREEN        := $(shell tput -Txterm setaf 2)
	YELLOW       := $(shell tput -Txterm setaf 3)
	LIGHTPURPLE  := $(shell tput -Txterm setaf 4)
	PURPLE       := $(shell tput -Txterm setaf 5)
	BLUE         := $(shell tput -Txterm setaf 6)
	WHITE        := $(shell tput -Txterm setaf 7)
	RESET := $(shell tput -Txterm sgr0)
else
	BLACK        := ""
	RED          := ""
	GREEN        := ""
	YELLOW       := ""
	LIGHTPURPLE  := ""
	PURPLE       := ""
	BLUE         := ""
	WHITE        := ""
	RESET        := ""
endif

# set target color
TARGET_COLOR := $(BLUE)

POUND = \#

.PHONY: no_targets__ info help build deploy doc
	no_targets__:

.DEFAULT_GOAL := help

# default human-talent routes
DOCKER_COMPOSE_FILE := ./docker-compose.yml
ANGULAR_APP_DIR := ../Frontend/HumanTalent
SPRING_BOOT_DIR := ../Backend/HumanTalent

docker_up:  ## Compile and deploy docker-compose file
	docker-compose -f $(DOCKER_COMPOSE_FILE) up -d

docker_stop:
	docker-compose stop

docker_down: ## Stop and delete docker containers
	docker-compose -f $(DOCKER_COMPOSE_FILE) down

compile_angular:
	cd $(ANGULAR_APP_DIR) && ng build --base-href ./

copy_angular_to_spring_boot:
	rm -rf $(SPRING_BOOT_PROJECT_DIR)/src/main/resources/static/*
	cp -r $(ANGULAR_APP_DIR)/dist/* $(SPRING_BOOT_PROJECT_DIR)/src/main/resources/static/

compile_spring_boot:
	cd $(SPRING_BOOT_PROJECT_DIR) && ./gradlew build

deploy: ## Start complete deployment
	docker_up compile_angular copy_angular_to_spring_boot compile_spring_boot

clean: ## Clean all compiled sources
	cd $(ANGULAR_APP_DIR) && ng clean
	cd $(SPRING_BOOT_PROJECT_DIR) && ./gradlew clean

help:
	@echo ""
	@echo "    ${YELLOW}[!] - ${GREEN}HumanTalent deployment${RESET}"
	@echo ""
	@echo "         Enjoy automation!"
	@echo ""
	@echo "${BLACK}-----------------------------------------------------------------${RESET}"
	@grep -E '^[a-zA-Z_0-9%-]+:.*?## .*$$' $(MAKEFILE_LIST) | sort | awk 'BEGIN {FS = ":.*?## "}; {printf "${TARGET_COLOR}%-30s${RESET} %s\n", $$1, $$2}'
