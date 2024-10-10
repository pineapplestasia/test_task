#!/bin/bash

./gradlew.bat clean
./gradlew.bat allureReport --depends-on-tests
./gradlew.bat allureServe
