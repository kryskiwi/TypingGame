#ifndef CLIENT_SOCKET_H
#define CLIENT_SOCKET_H

#include "SendReceiveSocket.h"
#include "Endpoint.h"

#pragma comment (lib, "Ws2_32.lib")

class ClientSocket : public SendReceiveSocket
{
public:
    ClientSocket();
    void ConnectToServer(const Endpoint& endpoint);

private:
    WSAData wsaData;
};

#endif