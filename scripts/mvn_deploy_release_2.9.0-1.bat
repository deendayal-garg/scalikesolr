@echo off
cd %~dp0\../.
mvn -f pom_2.9.0-1.xml clean -DaltDeploymentRepository=release-repo::default::file:./mvn-repo/releases clean deploy
