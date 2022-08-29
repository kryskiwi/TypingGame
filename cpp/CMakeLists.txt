cmake_minimum_required(VERSION 3.20)

project(GameProject)

# Tell cmake to output library files to the "lib" folder and executables to the
# "bin" folder. Both of these folders should appear in the "build" directory.
set(CMAKE_ARCHIVE_OUTPUT_DIRECTORY ${CMAKE_BINARY_DIR}/lib)
set(CMAKE_LIBRARY_OUTPUT_DIRECTORY ${CMAKE_BINARY_DIR}/lib)
set(CMAKE_RUNTIME_OUTPUT_DIRECTORY ${CMAKE_BINARY_DIR}/bin)

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