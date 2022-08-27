#ifndef SERVER_SOCKET_H
#define SERVER_SOCKET_H

#include <WinSock2.h>
#include <WS2tcpip.h>
#include "Endpoint.h"
#include "SendReceiveSocket.h"

#pragma comment (lib, "Ws2_32.lib")

class ServerSocket
{
public:
    ServerSocket();
    void BindAndListen(Endpoint endpoint);
    SendReceiveSocket Accept();

private:
    WSADATA wsaData;
    SOCKET listenerSocket;
};

#endif