#include "Sockets/ClientSocket.h"

ClientSocket::ClientSocket()
{
    WSAStartup(MAKEWORD(2, 2), &wsaData);
    connectedSocket = socket(AF_INET, SOCK_STREAM, IPPROTO_TCP);
}

void ClientSocket::ConnectToServer(const Endpoint& endpoint)
{
    struct sockaddr_in serverAddress = {};
    serverAddress.sin_family = AF_INET;
    serverAddress.sin_addr.s_addr = inet_addr(endpoint.ipAddress.c_str());
    serverAddress.sin_port = htons(endpoint.portNumber);
    
    connect(connectedSocket, (SOCKADDR*)&serverAddress, sizeof(serverAddress));
}