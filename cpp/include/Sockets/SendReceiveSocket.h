#ifndef SEND_RECEIVE_SOCKET_H
#define SEND_RECEIVE_SOCKET_H

#include <WinSock2.h>
#include <array>
#include <vector>

class SendReceiveSocket
{
public:
    SendReceiveSocket();
    SendReceiveSocket(SOCKET acceptedSocket);
    std::vector<uint8_t> ReceiveData(size_t numberOfBytes);
    void SendData(const std::vector<uint8_t>& dataBytesToSend);
    ~SendReceiveSocket();

private:
    static constexpr size_t SOCKET_BUFFER_LENGTH = 1024;
    
protected:
    SOCKET connectedSocket;
};

#endif