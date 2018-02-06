package com.coviam.cache;

import com.aerospike.client.*;
import com.aerospike.client.policy.ScanPolicy;
import com.aerospike.client.policy.WritePolicy;
import com.coviam.util.IOCompressUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@DependsOn(value = "aeroSpikeConfig")
public class CachingWrapper {
    protected static AerospikeClient client;
    protected AeroSpikeConfig aeroSpikeConfig;
    protected ApplicationContext applicationContext;
    final static private int timeToliveValueDefault = 2700;
    @Autowired
    AeroSpikeConfParams aeroSpikeConfParams;


    private static Logger log = Logger.getLogger(String.valueOf(CachingWrapper.class));

    @Autowired
    public CachingWrapper(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        initialize();
        try {
            Host[] hosts = aeroSpikeConfParams.getAerospikeHosts().stream()
                    .map(ht -> new Host(ht, aeroSpikeConfParams.getAerospikePort()))
                    .toArray(host -> new Host[host]);

            log.info("Aerospike client initialized here");
            client = new AerospikeClient(aeroSpikeConfig.clientPolicy, hosts);
        } catch (Exception e) {
            // log.error("Error in creating aerospike client, AerospikeHosts : {}, Aerospikeport :{}, Exception :{}", aeroSpikeConfParams.getAerospikeHosts(), aeroSpikeConfParams.getAerospikePort(), e);
        }
    }


    public void initialize() {
        aeroSpikeConfig = (AeroSpikeConfig) applicationContext.getBean("aeroSpikeConfig");
        aeroSpikeConfParams = (AeroSpikeConfParams) applicationContext.getBean("aeroSpikeConfParams");
    }


    public void writeWithoutCompression(String setName, String keyName, String colName, String colValue) {
        try {
            String timeToliveValue = aeroSpikeConfParams.getSetTimeToLive().get(setName).toString();
            WritePolicy writePolicy = aeroSpikeConfig.getDefaultWritePolicy();
            if (!StringUtils.isBlank(timeToliveValue)) {
                writePolicy.expiration = Integer.parseInt(timeToliveValue);
            } else {
                writePolicy.expiration = timeToliveValueDefault;
            }
            Key key = new Key(AeroSpikeConfParams.getNameSpace(), setName, keyName);
            Bin bin = new Bin(colName, colValue);
            client.put(writePolicy, key, bin);
            log.info("response got cached");
        } catch (Exception e) {
            // log.error("Exception while inserting for keySet {} with colName {} and into cache : {}", keyName, colName, e);
        }
    }

    public String readValue(String setName, String keyName, String colName) {
        Record record = null;
        String colValue = "";
        try {
            Key key = new Key(AeroSpikeConfParams.getNameSpace(), setName, keyName);
            record = client.get(aeroSpikeConfig.clientPolicy.readPolicyDefault, key);
            if (record != null && record.bins.size() != 0) {
                colValue = (String) record.getValue(colName);
                System.out.println("----------------------Response coming from cache----------------------------");
            }
            return colValue;
        } catch (Exception exception) {
            //   log.error("Error in getting value for set {} from cache : {}", setName, exception);
        }
        return colValue;
    }


}