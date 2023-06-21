#!/bin/bash

# Set the necessary variables
MODULE_NAME="imu"
# OUTPUT_DIR="imu/libs"

# Clean the previous build outputs
../gradlew clean

# Build the AAR file
../gradlew :${MODULE_NAME}:assembleRelease

# Copy the AAR file to the output directory
# cp ${MODULE_NAME}/build/outputs/aar/${MODULE_NAME}-release.aar ${OUTPUT_DIR}/${MODULE_NAME}.aar

# Print success message
echo "AAR file created successfully: ${MODULE_NAME}.aar"
