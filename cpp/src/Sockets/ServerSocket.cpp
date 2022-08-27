#include "Sockets/ServerSocket.h"

ServerSocket::ServerSocket()
{
    WSAStartup(MAKEWORD(2, 2), &wsaData);
    listenerSocket = socket(AF_INET, SOCK_STREAM, IPPROTO_TCP);
}

void ServerSocket::BindAndListen(Endpoint endpoint)
{
    struct sockaddr_in serverAddress = {};
    serverAddress.sin_family = AF_INET;
    serverAddress.sin_addr.s_addr = inet_addr(endpoint.ipAddress.c_str());
    serverAddress.sin_port = htons(endpoint.portNumber);

    bind(listenerSocket, (SOCKADDR*)&serverAddress, sizeof(serverAddress));

    listen(listenerSocket, 2);
}

SendReceiveSocket ServerSocket::Accept()
{
    return SendReceiveSocket(accept(listenerSocket, nullptr, nullptr));
}