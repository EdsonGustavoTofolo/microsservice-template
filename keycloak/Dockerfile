# NAO ESTA FUNCIONANDO

FROM quay.io/keycloak/keycloak:20.0.2 as builder

# Enable health and metrics support
ENV KC_HEALTH_ENABLED=true
ENV KC_METRICS_ENABLED=true

# Configure a database vendor
ENV KC_DB=mysql

WORKDIR /opt/keycloak
# for demonstration purposes only, please make sure to use proper certificates in production instead
RUN keytool -genkeypair -storepass password -storetype PKCS12 -keyalg RSA -keysize 2048 -dname "CN=server" -alias server -ext "SAN:c=DNS:localhost,IP:127.0.0.1" -keystore conf/server.keystore
RUN /opt/keycloak/bin/kc.sh build

FROM quay.io/keycloak/keycloak:20.0.2
COPY --from=builder /opt/keycloak/ /opt/keycloak/
WORKDIR /opt/keycloak

# Configure a database vendor
ENV KC_DB=mysql

# change these values to point to a running database instance
ENV KC_DB_URL=jdbc:mysql://localhost:3306
ENV KC_DB_SCHEMA=keycloak
ENV KC_DB_USERNAME=kc_user
ENV KC_DB_PASSWORD=kc_password
ENV KC_HOSTNAME=localhost

ENTRYPOINT ["/opt/keycloak/bin/kc.sh", "start-dev --health-enabled=true"]