# Bookverse 📚
**Refactoring a Monolithic Application into Event-Driven Microservices**

This project demonstrates the transformation of a legacy monolithic application into a modern, distributed system using **Microservices** and **Event-Driven Architecture**.

## 🚀 Key Features
* **Hybrid Architecture:** Coexistence of a Monolith and decoupled Microservices.
* **Event-Driven Communication:** Asynchronous messaging using **RabbitMQ**.
* **Cloud Integration:** Fully configurable to run with local RabbitMQ or **CloudAMQP**.
* **Containerization:** Full orchestration using **Docker** and **Docker Compose**.

---

## 🏗 System Architecture

The system consists of three main components:

1.  **Book Monolith (Publisher):**
    * Handles order processing logic.
    * Publishes an `order.created` event to the `order_events` exchange whenever a new order is placed.
    
2.  **Notification Service (Consumer):**
    * A standalone microservice built with Spring Boot.
    * Listens to the message queue, consumes the event, and simulates sending an email notification to the user.

3.  **User Service:**
    * A separate microservice responsible for user management (Strangler Fig Pattern implementation).

---

## 🛠 Tech Stack
* **Language:** Java 17
* **Framework:** Spring Boot 3
* **Message Broker:** RabbitMQ (AMQP) / CloudAMQP
* **Containerization:** Docker & Docker Compose
* **Build Tool:** Maven

---

## ⚙️ Setup & Installation

### 1. Clone the Repository
```bash
git clone [https://github.com/sanduvac/bookverse.git](https://github.com/sanduvac/bookverse.git)
cd bookverse
### 2. Configuration (Environment Variables)
The project supports **CloudAMQP** via environment variables. Create a `.env` file in the root directory (same level as `docker-compose.yml`) to keep your credentials safe.

**Create `.env` file:**
```properties
# If not provided, the system defaults to the local RabbitMQ container.
RABBITMQ_URL=amqps://your-user:your-password@hostname/vhost
### 3. Run with Docker
Build and start all services (Monolith, Notification Service, RabbitMQ) with a single command:

```bash
docker compose up -d --build
## 🧪 How to Test

### 1. Trigger an Order Event
You can simulate a new order by sending a request to the Monolith API.

**Via Terminal (cURL):**
```bash
curl -X POST "http://localhost:8080/api/orders?product=TheHobbit"
**Via Browser:**
Go to: `http://localhost:8080/api/orders?product=TheHobbit`

### 2. Verify Asynchronous Communication
Check the Docker logs to see the event flow:

* **Monolith Log:** `[x] Olay Gönderildi: 'order.created':'{"product":"TheHobbit"...}'`
* **Notification Service Log:** `[x] Olay Alındı: ... ==> Kullanıcıya E-Posta gönderiliyor... 📧`

---

## 🤝 Contribution
1.  Fork the repository.
2.  Create a feature branch (`git checkout -b feature/AmazingFeature`).
3.  Commit your changes (`git commit -m 'Add some AmazingFeature'`).
4.  Push to the branch (`git push origin feature/AmazingFeature`).
5.  Open a Pull Request.