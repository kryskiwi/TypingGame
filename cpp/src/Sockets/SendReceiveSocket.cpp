#include "Sockets/SendReceiveSocket.h"

SendReceiveSocket::SendReceiveSocket()
{
    
}

SendReceiveSocket::SendReceiveSocket(SOCKET acceptedSocket)
: connectedSocket(acceptedSocket)
{

}

void SendReceiveSocket::SendData(const std::vector<uint8_t>& dataToSend)
{
    size_t numberOfBytes = dataToSend.size();
    size_t numberOfBytesSent = 0;

    while (numberOfBytesSent < numberOfBytes)
    {
        size_t bytesToTryAndSend = numberOfBytes - numberOfBytesSent > SOCKET_BUFFER_LENGTH
            ? SOCKET_BUFFER_LENGTH
            : numberOfBytes - numberOfBytesSent;

        char* pointerToIndexInData = (char*)(dataToSend.data() + numberOfBytesSent);

        numberOfBytesSent += send(connectedSocket, pointerToIndexInData, bytesToTryAndSend, 0);
    }
}

std::vector<uint8_t> SendReceiveSocket::ReceiveData(size_t numberOfBytes)
{
    size_t numberOfBytesRead = 0;
    std::vector<uint8_t> readData(numberOfBytes);
    
    while (numberOfBytesRead < numberOfBytes)
    {
        size_t bytesToTryAndRead = numberOfBytes - numberOfBytesRead > SOCKET_BUFFER_LENGTH
            ? SOCKET_BUFFER_LENGTH
            : numberOfBytes - numberOfBytesRead;

        char* pointerToIndexInData = (char*)(readData.data() + numberOfBytesRead);

        numberOfBytesRead += recv(connectedSocket, pointerToIndexInData, bytesToTryAndRead, 0);
    }
    
    return readData;
}