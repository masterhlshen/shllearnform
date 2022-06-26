package com.shl.deplayXc;

import java.util.Map;

public class DeplayCfgVO {

    private String serverPath;
    private Map<String, warCgfVO> appServer;
    private String resourceDir;

    public String getServerPath() {
        return serverPath;
    }

    public void setServerPath(String serverPath) {
        this.serverPath = serverPath;
    }

    public Map<String, warCgfVO> getAppServer() {
        return appServer;
    }

    public void setAppServer(Map<String, warCgfVO> appServer) {
        this.appServer = appServer;
    }

    public String getResourceDir() {
        return resourceDir;
    }

    public void setResourceDir(String resourceDir) {
        this.resourceDir = resourceDir;
    }
}

class warCgfVO {
    private String server;
    private String cfgDir;

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getCfgDir() {
        return cfgDir;
    }

    public void setCfgDir(String cfgDir) {
        this.cfgDir = cfgDir;
    }
}
