#!/bin/bash

# Script to build and run the Phaser Android app using Docker

# Colors for output
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

echo -e "${YELLOW}Building Phaser Android app using Docker...${NC}"
echo -e "${YELLOW}This may take a few minutes for the first build.${NC}"

# Build the Docker image
echo -e "${GREEN}Building Docker image...${NC}"
docker build -t phaser-android .

# Build the app
echo -e "${GREEN}Building the Android app...${NC}"
docker run --rm -v "$PWD":/app -w /app phaser-android ./gradlew assembleDebug

# Check if build was successful
if [ $? -eq 0 ]; then
    echo -e "${GREEN}Build successful!${NC}"
    echo -e "${GREEN}APK location: app/build/outputs/apk/debug/app-debug.apk${NC}"
else
    echo -e "${YELLOW}Build failed. Please check the logs above for errors.${NC}"
    exit 1
fi

echo -e "${YELLOW}To run tests, use: docker run --rm -v \"$PWD\":/app -w /app phaser-android ./gradlew test${NC}"