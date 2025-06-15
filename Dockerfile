# Use the official Android SDK image as a base
FROM openjdk:11-jdk

# Set environment variables
ENV ANDROID_HOME=/opt/android-sdk-linux
ENV PATH=${PATH}:${ANDROID_HOME}/tools:${ANDROID_HOME}/platform-tools:${ANDROID_HOME}/cmdline-tools/latest/bin

# Install necessary packages
RUN apt-get update && apt-get install -y \
    wget \
    unzip \
    git \
    curl \
    && rm -rf /var/lib/apt/lists/*

# Download and install Android SDK
RUN mkdir -p ${ANDROID_HOME} && \
    cd ${ANDROID_HOME} && \
    wget -q https://dl.google.com/android/repository/commandlinetools-linux-8512546_latest.zip -O android-sdk.zip && \
    unzip -q android-sdk.zip && \
    rm android-sdk.zip && \
    mkdir -p ${ANDROID_HOME}/cmdline-tools/latest && \
    mv ${ANDROID_HOME}/cmdline-tools/* ${ANDROID_HOME}/cmdline-tools/latest/ || true && \
    mv ${ANDROID_HOME}/cmdline-tools/latest/cmdline-tools/* ${ANDROID_HOME}/cmdline-tools/latest/ || true

# Accept licenses
RUN yes | sdkmanager --licenses

# Install required SDK components
RUN sdkmanager "platform-tools" "platforms;android-32" "build-tools;32.0.0" "extras;android;m2repository" "extras;google;m2repository"

# Set up working directory
WORKDIR /app

# Copy the project files
COPY . .

# Grant execution permission to gradlew
RUN chmod +x ./gradlew

# Build the project (skip tests to make the build faster)
RUN ./gradlew assembleDebug --no-daemon --stacktrace

# Default command
CMD ["./gradlew", "tasks"]

# Instructions for running the app
# To build the app: docker run --rm -v "$PWD":/app -w /app phaser-android ./gradlew assembleDebug
# To run tests: docker run --rm -v "$PWD":/app -w /app phaser-android ./gradlew test