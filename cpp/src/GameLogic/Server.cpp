#include <iostream>

#include "Sockets/ServerSocket.h"
#include "Sockets/SendReceiveSocket.h"

int main()
{
    Endpoint endpoint;
    endpoint.ipAddress = "127.0.0.1";
    endpoint.portNumber = 30000;

    ServerSocket sock;
    sock.BindAndListen(endpoint);

    SendReceiveSocket dataSocket = sock.Accept();
    std::vector<uint8_t> data = dataSocket.ReceiveData(15);

    std::cout << (char*)data.data() << "\n";
}