package com.coviam.cache;

import com.aerospike.client.Log;
import com.aerospike.client.async.AsyncClientPolicy;
import com.aerospike.client.policy.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class AeroSpikeConfig {

    public static ClientPolicy clientPolicy;
    public static AsyncClientPolicy asyncClientPolicy;
    protected static int timeOut = 0;
    protected ApplicationContext applicationContext;
    @Autowired
    AeroSpikeConfParams aeroSpikeConfParams;

    @Autowired
    public AeroSpikeConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        initialize();
        setDefaultClientPolicies();
        setAsyncClientPolicy();
        configureLog();
    }

    public void initialize() {
        aeroSpikeConfParams = (AeroSpikeConfParams) applicationContext.getBean("aeroSpikeConfParams");
    }

    protected void setDefaultClientPolicies() {
        clientPolicy = new ClientPolicy();
        clientPolicy.maxConnsPerNode = aeroSpikeConfParams.getMaxThreads();
        clientPolicy.queryPolicyDefault = getDefaultQueryPolicy();
        clientPolicy.readPolicyDefault = getDefaultReadPolicy();
        clientPolicy.writePolicyDefault = getDefaultWritePolicy();
        clientPolicy.scanPolicyDefault = getDefaultScanPolicy();
    }

    private ScanPolicy getDefaultScanPolicy() {
        return new ScanPolicy();

    }

    private Policy getDefaultReadPolicy() {
        Policy readPolicy = new Policy();
        readPolicy.maxRetries = 0;
        readPolicy.totalTimeout = timeOut;
        return readPolicy;
    }

    protected static WritePolicy getDefaultWritePolicy() {
        WritePolicy writePolicy = new WritePolicy();
        writePolicy.totalTimeout = timeOut;
        writePolicy.maxRetries = 0;
        return writePolicy;
    }

    protected QueryPolicy getDefaultQueryPolicy() {
        QueryPolicy readPolicy = new QueryPolicy();
        readPolicy.totalTimeout = timeOut;
        readPolicy.maxRetries = 0;
        return readPolicy;
    }


    public void setAsyncClientPolicy() {
        asyncClientPolicy = new AsyncClientPolicy();
        asyncClientPolicy.asyncMaxCommands = aeroSpikeConfParams.getAsyncMaxCommands();
        asyncClientPolicy.asyncWritePolicyDefault = getDefaultWritePolicy();
        asyncClientPolicy.asyncReadPolicyDefault = getDefaultQueryPolicy();
    }

    protected void configureLog() {
        if (aeroSpikeConfParams.isLogEnabled()) {
            Log aerospikelog = new Log();
            aerospikelog.log(Log.Level.INFO, "log is enabled in Info mode");
        }
    }

}
