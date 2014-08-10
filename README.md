Port_Forward_Android
====================

Port Forward From Android Device on any Internet Gateway Device (Router) that has the UPNP option enabled.
First step in opening a port on a IGD si to enable the UPNP option, for this you need to access the router settings and select this option


Application screenshot:

<img alt="Main Application Activity" src="https://github.com/ManolescuSebastian/Port_Forward_Android/blob/master/screenshots/main__app_activity.png" height="400px" /> 
<img alt="Main Application Get External IP and IGD Friendly Name" src="https://github.com/ManolescuSebastian/Port_Forward_Android/blob/master/screenshots/search_progress_bar_main.png" height="400px"/>
<img alt="Add Port Activity" src="https://github.com/ManolescuSebastian/Port_Forward_Android/blob/master/screenshots/add_port_activity.png" height="400px"/>

For own implementation use upnplib-mobile.jar library.

First the IGD (Internet Gateway Device **Router**) using the following line of code.
`InternetGatewayDevice[] internetGatewayDevices = InternetGatewayDevice.getDevices(5000);`

If the `internetGatewayDevices` array is  > 1 get the first element and use the following methods.

                `firstIGDEmelent.addPortMapping(description, externalRouterIP, internalPort, externalRouterPort, internalIP, 0, "TCP");
                `firstIGDElement.addPortMapping(description, externalRouterIP, internalPort, externalRouterPort, internalIP, 0, "UDP");`

Both protocols (TPC & UDP) are required.

This library also has the remove implementation with the following methods.

                `firstIGDEmelent.deletePortMapping(externalIP, port, "TCP");`
                `firstIGDEmelent.deletePortMapping(externalIP, port, "UDP");`



For any questions please access http://software-include.ro website and use the contact section.


Application developement in progress.

