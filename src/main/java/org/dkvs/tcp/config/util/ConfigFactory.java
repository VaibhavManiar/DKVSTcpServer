package org.dkvs.tcp.config.util;

import org.dkvs.tcp.config.Configuration;
import org.dkvs.tcp.config.exception.ConfigCreationException;
import org.dkvs.tcp.server.ServerProperties;

public final class ConfigFactory {
    public static Configuration<?, ?> create(ServerProperties serverProperties) {
        try {
            String configClassName = serverProperties.getConfigurationClassIfConfigured().orElseThrow(()->new RuntimeException("'configurationclass' is not mentioned in server properties."));
            return (Configuration<?, ?>) Class.forName(configClassName).getConstructor(ServerProperties.class).newInstance(serverProperties);
        } catch (Exception e) {
            throw new ConfigCreationException("Error while creating config : " + e.getMessage(), e);
        }
    }
}
