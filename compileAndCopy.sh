#!/bin/bash
./gradlew clean build

scp  ~/IdeaProjects/SpookyZombieGame/build/libs/SpookyZombieGame-1.0.jar justin@server.goliath.org:/opt/minecraft/homestead/plugins
