package org.dkvs.tcp.server;

import org.dkvs.tcp.exception.PropertyLoadingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

public class ServerProperties {
    private final static Logger logger = LoggerFactory.getLogger(ServerProperties.class);
    private final Properties properties;
    private String serverPropertiesFile;
    private final String configurationClass;
    private final String port;

    public ServerProperties(String profile) {
        properties = load(profile);
        configurationClass = properties.getProperty("configurationclass");
        port = properties.getProperty("port");
    }

    public ServerProperties() {
        this("");
    }

    public Properties getProperties() {
        return properties;
    }

    public String getServerPropertiesFile() {
        return serverPropertiesFile;
    }

    public Optional<String> getConfigurationClassIfConfigured() {
        return Optional.ofNullable(configurationClass);
    }

    public Optional<String> getPortIfConfigured() {
        return Optional.ofNullable(port);
    }

    private Class<?> requestMessageType() throws ClassNotFoundException {
        String reqType = properties.getProperty("requesttype");
        if (reqType == null || reqType.isEmpty()) {
            throw new PropertyLoadingException("Error while fetching 'requesttype' property from file : [" + serverPropertiesFile + "]");
        }
        return Class.forName(reqType);
    }

    private Class<?> responseMessageType() throws ClassNotFoundException {
        String resType = properties.getProperty("responsetype");
        if (resType == null || resType.isEmpty()) {
            throw new PropertyLoadingException("Error while fetching 'responsetype' property from file : [" + serverPropertiesFile + "]");
        }
        return Class.forName(resType);
    }

    private Properties load(String profile) {
        serverPropertiesFile = propFile(profile);
        logger.debug("Loading properties for profile: [{}] from classpath file: [{}]", profile, serverPropertiesFile);
        try (InputStream inputStream = new FileInputStream("src/main/resources/"+serverPropertiesFile)) {
            Properties properties = new Properties();
            properties.load(inputStream);
            logger.debug("Properties loaded from classpath file : {}", serverPropertiesFile);
            return properties;
        } catch (IOException e) {
            throw new PropertyLoadingException("Error while loading properties from file : [" + serverPropertiesFile + "]");
        }
    }

    private String propFile(String profile) {
        if (profile == null || profile.isEmpty()) {
            return "server.properties";
        } else {
            return "server-" + profile + ".properties";
        }
    }
}
