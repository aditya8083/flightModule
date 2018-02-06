package com.coviam.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@ConfigurationProperties(prefix = "aerospike-conf")
@Service
public class AeroSpikeConfParams {

    @Value("${aerospike-conf.nameSpace}")
    private static String nameSpace;

    private final List<String> aerospikeHosts;
    private final int aerospikePort;
    private final int maxThreads;
    private final int asyncMaxCommands;
    private final boolean logEnabled;
    private Map<String, Integer> setTimeToLive;


    @Autowired
    public AeroSpikeConfParams(@Value("#{'${aerospike-conf.aerospikeHosts}'.split(',')}") List<String> aerospikeHosts,
                               @Value("${aerospike-conf.aerospikePort}") int aerospikePort,
                               @Value("${aerospike-conf.maxThreads}") int maxThreads,
                               @Value("${aerospike-conf.asyncMaxCommands}") int asyncMaxCommands,
                               @Value("${aerospike-conf.logEnabled}") boolean logEnabled) {
        this.aerospikeHosts = aerospikeHosts;
        this.aerospikePort = aerospikePort;
        this.maxThreads = maxThreads;
        this.asyncMaxCommands = asyncMaxCommands;
        this.logEnabled = logEnabled;
    }

    public Map<String, Integer> getSetTimeToLive() {
        return setTimeToLive;
    }

    public void setSetTimeToLive(Map<String, Integer> setTimeToLive) {
        this.setTimeToLive = setTimeToLive;
    }

    public static String getNameSpace() {
        return nameSpace;
    }

    public static void setNameSpace(String nameSpace) {
        AeroSpikeConfParams.nameSpace = nameSpace;
    }

    public List<String> getAerospikeHosts() {
        return aerospikeHosts;
    }

    public int getAerospikePort() {
        return aerospikePort;
    }

    public int getMaxThreads() {
        return maxThreads;
    }

    public int getAsyncMaxCommands() {
        return asyncMaxCommands;
    }

    public boolean isLogEnabled() {
        return logEnabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AeroSpikeConfParams)) return false;
        AeroSpikeConfParams that = (AeroSpikeConfParams) o;
        return aerospikePort == that.aerospikePort &&
                maxThreads == that.maxThreads &&
                asyncMaxCommands == that.asyncMaxCommands &&
                logEnabled == that.logEnabled &&
                Objects.equals(aerospikeHosts, that.aerospikeHosts) &&
                Objects.equals(setTimeToLive, that.setTimeToLive);
    }

    @Override
    public int hashCode() {

        return Objects.hash(aerospikeHosts, aerospikePort, maxThreads, asyncMaxCommands, logEnabled, setTimeToLive);
    }

    @Override
    public String toString() {
        return "AeroSpikeConfParams{" +
                "aerospikeHosts=" + aerospikeHosts +
                ", aerospikePort=" + aerospikePort +
                ", maxThreads=" + maxThreads +
                ", asyncMaxCommands=" + asyncMaxCommands +
                ", logEnabled=" + logEnabled +
                ", setTimeToLive=" + setTimeToLive +
                '}';
    }
}


