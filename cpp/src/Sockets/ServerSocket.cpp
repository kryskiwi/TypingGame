#include "Sockets/ServerSocket.h"
#include <iostream>

ServerSocket::ServerSocket()
{
    WSAStartup(MAKEWORD(2, 2), &wsaData);
    listenerSocket = socket(AF_INET, SOCK_STREAM, IPPROTO_TCP);

    if (listenerSocket == INVALID_SOCKET)
    {
        std::cout << "Socket failed to create " << WSAGetLastError() << "\n";
        WSACleanup(); 
        exit(1);
    }
}

void ServerSocket::BindAndListen(Endpoint endpoint)
{
    struct sockaddr_in serverAddress = {};
    serverAddress.sin_family = AF_INET;
    serverAddress.sin_addr.s_addr = inet_addr(endpoint.ipAddress.c_str());
    serverAddress.sin_port = htons(endpoint.portNumber);

    int bindResult = bind(listenerSocket, (SOCKADDR*)&serverAddress, sizeof(serverAddress));
    if (bindResult == SOCKET_ERROR)
    {
        std::cout << "Socket failed to bind " << WSAGetLastError() << "\n";
        closesocket(listenerSocket);
        WSACleanup();
        exit(1);
    }

    int listenResult = listen(listenerSocket, 2);
    if (listenResult == SOCKET_ERROR)
    {
        std::cout << "Socket failed to listen " << WSAGetLastError() << "\n";
        closesocket(listenerSocket);
        WSACleanup();
        exit(1);
    }
}

SendReceiveSocket ServerSocket::Accept()
{
    SOCKET connectedSocket = accept(listenerSocket, nullptr, nullptr);
    if (connectedSocket = INVALID_SOCKET)
    {
        std::cout << "Socket failed to accept " << WSAGetLastError() << "\n";
        closesocket(listenerSocket);
        WSACleanup();
        exit(1);
    }

    return SendReceiveSocket(connectedSocket);
}