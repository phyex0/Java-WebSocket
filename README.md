
# Java-WebSocket

This repository demonstrates the implementation of WebSocket in Spring Boot using STOMP (Simple Text Oriented Messaging Protocol). WebSocket enables bidirectional communication between clients and servers, allowing you to create multiple topics and share data between various and single subscribers.

## Features

- **STOMP Protocol**: Facilitates messaging between clients and servers.
- **Pub/Sub with Redis**: Utilizes Redis as a message broker to support the Publish/Subscribe pattern.
- **Kubernetes Deployment**: Demonstrates how to deploy WebSocket applications in Kubernetes.

## Challenges

When deploying WebSocket applications in Kubernetes, one of the main challenges is ensuring that all pods can listen to WebSocket messages. Due to the nature of communication protocols, while you can manage incoming data traffic to a single line, outgoing traffic cannot be handled in the same way. This challenge is addressed using distributed data transferring messaging platforms like Redis, Kafka, and RabbitMQ through the Pub/Sub pattern.

## Project Structure

This repository contains two types of WebSocket implementations:

1. **Basic WebSocket Implementation**
2. **WebSocket with Redis Pub/Sub**

## Getting Started

### Prerequisites

- Java 11 or higher
- Maven
- Docker
- Kubernetes Cluster
- Redis

### Installation

1. Clone the repository:
    \`\`\`sh
    git clone https://github.com/phyex0/Java-WebSocket.git
    cd Java-WebSocket
    \`\`\`

2. Build the project:
    \`\`\`sh
    mvn clean install
    \`\`\`

### Running Locally

#### Basic WebSocket

1. Navigate to the \`basic-websocket\` directory:
    \`\`\`sh
    cd basic-websocket
    \`\`\`

2. Run the Spring Boot application:
    \`\`\`sh
    mvn spring-boot:run
    \`\`\`

#### WebSocket with Redis

1. Ensure Redis is running. You can use Docker to start a Redis container:
    \`\`\`sh
    docker run --name redis -p 6379:6379 -d redis
    \`\`\`

2. Navigate to the \`redis-websocket\` directory:
    \`\`\`sh
    cd redis-websocket
    \`\`\`

3. Run the Spring Boot application:
    \`\`\`sh
    mvn spring-boot:run
    \`\`\`

### Deploying to Kubernetes

1. Deploy Redis to your Kubernetes cluster:
    \`\`\`sh
    kubectl apply -f kubernetes/redis-deployment.yaml
    \`\`\`

2. Deploy the WebSocket application:
    \`\`\`sh
    kubectl apply -f kubernetes/websocket-deployment.yaml
    \`\`\`

3. Expose the WebSocket service:
    \`\`\`sh
    kubectl apply -f kubernetes/websocket-service.yaml
    \`\`\`

## Usage

- **Basic WebSocket**: The application exposes a WebSocket endpoint at \`/ws\`. You can connect to this endpoint using a WebSocket client.

- **WebSocket with Redis**: Messages are published to Redis channels and the application subscribes to these channels. Received messages are broadcasted to WebSocket clients connected to \`/ws\`.

## Contributing

Contributions are welcome! Please open an issue or submit a pull request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
