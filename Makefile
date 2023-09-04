.PHONY: run

run:
	@echo "change java version to 11"
	@export JAVA_HOME=$(/usr/libexec/java_home -v 11.0.18)
	@echo "Running..../gradlew docker"
	@./gradlew docker
	@echo "Running... docker-compose up -d"
	@docker-compose up -d