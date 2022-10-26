package com.shl.deplayXc;

import java.util.Map;

public class DeplayCfgVO {

    private String serverPath;
    private Map<String, warCgfVO> appServer;
    private String resourceDir;

    private String newWar;

    private String newWarBackup;

    private String newWarBat;

    public String getNewWar() {
        return newWar;
    }

    public void setNewWar(String newWar) {
        this.newWar = newWar;
    }

    public String getNewWarBackup() {
        return newWarBackup;
    }

    public void setNewWarBackup(String newWarBackup) {
        this.newWarBackup = newWarBackup;
    }

    public String getNewWarBat() {
        return newWarBat;
    }

    public void setNewWarBat(String newWarBat) {
        this.newWarBat = newWarBat;
    }

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
    private String webApps;
    private String logDir;
    private String binDir;

    public String getWebApps() {
        return webApps;
    }

    public void setWebApps(String webApps) {
        this.webApps = webApps;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getLogDir() {
        return logDir;
    }

    public void setLogDir(String logDir) {
        this.logDir = logDir;
    }

    public String getBinDir() {
        return binDir;
    }

    public void setBinDir(String binDir) {
        this.binDir = binDir;
    }
}
