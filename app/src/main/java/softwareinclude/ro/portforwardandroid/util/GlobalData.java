package softwareinclude.ro.portforwardandroid.util;

/**
 * Created by manolescusebastian on 8/3/14.
 */
public enum GlobalData {
    Data;

    private String networkStatus;
    private String externalIP;
    private String friendlyName;

    public String getNetworkStatus() {
        return networkStatus;
    }

    public void setNetworkStatus(String networkStatus) {
        this.networkStatus = networkStatus;
    }

    public String getExternalIP() {
        return externalIP;
    }

    public void setExternalIP(String externalIP) {
        this.externalIP = externalIP;
    }

    public String getFriendlyName() {
        return friendlyName;
    }

    public void setFriendlyName(String friendlyName) {
        this.friendlyName = friendlyName;
    }
}
