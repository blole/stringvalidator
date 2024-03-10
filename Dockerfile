# syntax=docker/dockerfile:1.4
FROM gradle:8.6.0-jdk17-jammy AS tools
SHELL ["/bin/bash", "-o", "pipefail", "-c"]
RUN --mount=type=cache,target=/var/lib/apt/lists,sharing=locked \
    --mount=type=cache,target=/var/cache/apt,sharing=locked \
    apt-get update && \
    apt-get install --no-install-recommends --no-install-suggests -yqq \
    ca-certificates curl

WORKDIR /repo/

RUN mkdir /output/ && \
    java --version |& tee /output/java-version.txt && \
    gradle --version |& tee /output/gradle-version.txt



FROM tools AS common-dependencies
COPY \
    gradle.properties \
    settings.gradle.kts \
    ./
COPY gradle/libs.versions.toml gradle/libs.versions.toml
ENV GRADLE_OPTS="-Dorg.gradle.daemon=false -Dorg.gradle.console=verbose"



FROM common-dependencies AS app
RUN mkdir -p /output/app/
COPY app/build.gradle.kts app/
COPY app/src/ app/src/

FROM app AS app-build
RUN gradle build |& tee /output/app/build.txt

FROM app AS app-test
RUN gradle test |& tee /output/app/test.txt



FROM scratch AS ci
COPY --from=app-build /output/ /
COPY --from=app-test /output/ /
