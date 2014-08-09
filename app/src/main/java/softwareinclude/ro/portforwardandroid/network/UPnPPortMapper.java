package softwareinclude.ro.portforwardandroid.network;

import net.sbbi.upnp.impls.InternetGatewayDevice;
import net.sbbi.upnp.messages.UPNPResponseException;

import java.io.IOException;

import softwareinclude.ro.portforwardandroid.util.ApplicationConstants;

/**
 * Created by manolescusebastian on 8/3/14.
 */
public class UPnPPortMapper {

    private InternetGatewayDevice[] internetGatewayDevices;
    private InternetGatewayDevice foundGatewayDevice;

    /**
     * Search for IGD External Address
     * @return String
     */
    public String findExternalIPAddress () throws IOException, UPNPResponseException {

        /** Upnp devices - router search*/
        if(internetGatewayDevices == null){
            internetGatewayDevices = InternetGatewayDevice.getDevices(ApplicationConstants.SCAN_TIMEOUT);
        }
        if(internetGatewayDevices != null) {
            for (InternetGatewayDevice IGD : internetGatewayDevices) {
                foundGatewayDevice = IGD;
                return IGD.getExternalIPAddress().toString();
            }
        }
        return "No External IP Address Found";
    }

    /**
     * Search Found Internet Gateway Device Friendly Name
     * @return
     */
    public String findRouterName(){
        if(foundGatewayDevice != null){
           return foundGatewayDevice.getIGDRootDevice().getFriendlyName().toString();
        }
        return "No IGD Name Found";
    }

    /**
     * Open Router Port
     * IGD == Internet Gateway Device
     *
     * @param internalIP
     * @param internalPort
     * @param externalRouterIP
     * @param externalRouterPort
     * @param description
     * @return
     * @throws IOException
     * @throws UPNPResponseException
     */
    public boolean openRouterPort(String externalRouterIP,int externalRouterPort,
                                  String internalIP,int internalPort,
                                  String description)
                                  throws IOException, UPNPResponseException {

        /** Upnp devices - router search*/
        if(internetGatewayDevices == null){
            internetGatewayDevices = InternetGatewayDevice.getDevices(ApplicationConstants.SCAN_TIMEOUT);
        }

        if(internetGatewayDevices != null){
            for (InternetGatewayDevice addIGD : internetGatewayDevices) {
                /** Open port for TCP protocol and also for UDP protocol
                 *  Both protocols must be open - this is a MUST*/
                addIGD.addPortMapping(description, externalRouterIP, internalPort, externalRouterPort, internalIP, 0, ApplicationConstants.TCP_PROTOCOL);
                addIGD.addPortMapping(description, externalRouterIP, internalPort, externalRouterPort, internalIP, 0, ApplicationConstants.UDP_PROTOCOL);
            }
            return true;
        }else{
            return false;
        }
    }


    public boolean removePort(String externalIP,int port) throws IOException, UPNPResponseException{

        /** Upnp devices - router search*/
        if(internetGatewayDevices == null){
            internetGatewayDevices = InternetGatewayDevice.getDevices(ApplicationConstants.SCAN_TIMEOUT);
        }

        /**Remote port mapping for all routers*/
        if(internetGatewayDevices != null){
            for (InternetGatewayDevice removeIGD : internetGatewayDevices) {
                removeIGD.deletePortMapping(externalIP, port, ApplicationConstants.TCP_PROTOCOL);
                removeIGD.deletePortMapping(externalIP, port, ApplicationConstants.UDP_PROTOCOL);
            }
            return true;
        }else{
            return false;
        }
    }

}
