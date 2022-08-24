#include <winsock2.h>
#include <ws2tcpip.h>
#include <iostream>
#pragma comment(lib, "Ws2_32.lib")

#define DEFAULT_PORT "15000"

int main(){
    WSADATA wsadata;
    // Starting up socket library stuff
    int wsresult = WSAStartup(MAKEWORD(2,2), &wsadata);
    if (wsresult != 0) {
        printf("WSAStartup failed: %d\n", wsresult); }

    // Set up addrinfo stuff
    struct addrinfo *result = NULL, *ptr = NULL, hints;
    ZeroMemory(&hints, sizeof(hints));
    hints.ai_family = AF_INET;
    hints.ai_socktype = SOCK_STREAM;
    hints.ai_protocol = IPPROTO_TCP;
    hints.ai_flags = AI_PASSIVE;    
    

    // resolve local addr and port for server
    wsresult = getaddrinfo(NULL, DEFAULT_PORT, &hints, &result);
    if (wsresult != 0) {
        std::cout << "getaddrinfo failed" << wsresult;

    }

    SOCKET socks = INVALID_SOCKET;
    socks = socket(result -> ai_family, result -> ai_socktype, result -> ai_protocol);

    if (socks == INVALID_SOCKET) {
        std::cout << "Error at socket()" << WSAGetLastError();
        freeaddrinfo(result);
        WSACleanup();
        return 1;
    }
    
    // bind the socket
    wsresult = bind( socks, result->ai_addr, (int)result->ai_addrlen);
    if (wsresult == SOCKET_ERROR) {
        std::cout << "bind failed w/ error" << WSAGetLastError();
        freeaddrinfo(result);
        closesocket(socks);
        WSACleanup();
        return 1;
    }

    freeaddrinfo(result);

    // listen on socket
    if ( listen(socks, SOMAXCONN) == SOCKET_ERROR){
        printf( "Listen failed with error: %ld\n", WSAGetLastError() );
        closesocket(socks);
        WSACleanup();
        return 1;
    }

    // accept a connection
    SOCKET ClientSocket = INVALID_SOCKET;
    ClientSocket = accept(socks, NULL, NULL);
    if (ClientSocket == INVALID_SOCKET) {
        printf("accept failed: %d\n", WSAGetLastError());
        closesocket(socks);
        WSACleanup();
        return 1;
    }


    std::cout << "Henlo Nyick";
    return 0;
}

/*
1. Open a socket
2. 
*/