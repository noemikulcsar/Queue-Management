# 🧮 Queue Management System

🚀 **Queue Management System** is a **Java-based application** that provides a **graphical interface** for managing and visualizing queues, clients, and tasks across multiple servers. The system enables the simulation of real-time client processing with task scheduling and dynamic queue monitoring.

---

## **🎯 Features**

### **📊 Real-Time Visualization**
- **Graphical interface** to view queues, servers, and client status.
- **Dynamic monitoring** of queue lengths and server activities.

### **🔄 Task Scheduling**
- Two task selection policies:
  - **Shortest queue assignment**: Assign tasks to the server with the least number of clients.
  - **Shortest processing time assignment**: Prioritize tasks based on their estimated service time.

### **⏱️ Real-Time Simulation**
- Clients are processed in real-time, showing their progress across queues.
- The status of each server is updated dynamically as tasks are processed.

### **⚙️ Configurable Parameters**
- Customizable parameters for simulation:
  - Number of clients
  - Number of servers
  - Minimum/maximum arrival and service time

---

## **🛠️ Technologies Used**

### **🔗 Backend – Java**
The system is built using **Java**, a powerful programming language known for its portability, performance, and strong threading capabilities for handling concurrent client tasks.  
✅ **Multi-threading support** for handling tasks simultaneously  
✅ **Object-oriented design** for scalability and modularity  
✅ **Java Swing** for GUI development  

### **💾 Task Management**
- Tasks are managed and tracked based on their **arrival time** and **service time**.
- Tasks are scheduled dynamically to servers based on their availability and the chosen policy.

### **🖥️ Frontend – Java Swing**
The frontend uses **Java Swing**, a **graphical user interface toolkit**, providing:
✅ **Cross-platform support**  
✅ **Real-time updates** of queue and server status  
✅ **Interactive interface** for user input and configuration  

---

## **🚀 Getting Started**

### **Prerequisites**
- Java Development Kit (JDK) 8 or higher
- Java Swing (included in JDK)

### **💻 Usage**
**Set simulation parameters**:
Number of clients
Number of servers
Minimum/maximum arrival and service time \
**Choose the scheduling strategy**: Select either Shortest Queue Assignment or Shortest Processing Time Assignment. \
**Start the simulation**: Begin processing tasks in real-time. \
**Monitor the simulation**: Observe the status of queues, clients, and servers in the graphical interface.

### **🔍 Testing**
The system has been tested under various scenarios, including:

- Basic functionality testing: Verifying that tasks are assigned and processed correctly.
- Boundary testing: Handling extreme values for arrival and service times.
- Data integrity: Ensuring that task data and server statuses are updated and displayed correctly.
- Dynamic testing: Observing the impact of changing server configurations and scheduling strategies.
