#include <iostream>

#include "Sockets/ClientSocket.h"
#include "Sockets/SendReceiveSocket.h"

int main()
{
    Endpoint endpoint;
    endpoint.ipAddress = "127.0.0.1";
    endpoint.portNumber = 30000;

    ClientSocket sock;
    sock.ConnectToServer(endpoint);

    std::string str = "hello";
    std::vector<uint8_t> data(str.begin(), str.end());

    sock.SendData(data);
}