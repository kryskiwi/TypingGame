cmake_minimum_required(VERSION 3.20)

project(GameProject)

include_directories(include)

add_library(SocketsLibrary STATIC
    src/Sockets/ClientSocket.cpp
    src/Sockets/SendReceiveSocket.cpp
    src/Sockets/ServerSocket.cpp
)

add_executable(Server src/GameLogic/Server.cpp)
target_link_libraries(Server SocketsLibrary)

add_executable(Client src/GameLogic/Client.cpp)
target_link_libraries(Client SocketsLibrary)