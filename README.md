# Office One – Employer Monitoring System

Office One is a backend system designed to monitor and manage employee activities within an organization. It provides functionality for attendance tracking, leave management, employee hierarchy management, and work-hour monitoring.

The project was originally implemented using **Django (Python)** and later **migrated to Spring Boot (Java)** to improve scalability, maintainability, and enterprise-level backend architecture.

---

# Project Overview

Office One enables organizations to track employee work activity through a centralized backend platform. Employees can record attendance, manage breaks, and request leave, while administrators and team leads can monitor attendance summaries and team productivity.

The system follows a **role-based access architecture** where different user roles have specific permissions.

### Supported Roles

- **Admin**
- **Team Lead**
- **Employee**

Each role has controlled access to system features.

---

# Key Features

## User Management
- User registration and authentication
- Role-based access control
- Supervisor–employee hierarchy
- Team structure management

## Attendance Management
- Employee daily check-in
- Employee daily check-out
- Out-of-office break tracking
- Resume work after break
- Daily working hours calculation
- Overtime calculation

## Leave Management
- Leave request submission
- Leave approval workflow
- Leave status tracking
- Leave records integrated with attendance system

## Attendance Monitoring
- Admin attendance dashboard
- Team lead attendance dashboard
- Weekly attendance summaries
- Working hours monitoring

## Employee Activity Monitoring
- Track employee working duration
- Track inactive periods
- Monitor overtime
- Weekly work summary reports

---

# System Architecture

The application follows a **layered architecture** to ensure maintainability and scalability.

```
Client Applications (Web / Mobile)
            │
            ▼
REST API Layer (Controllers)
            │
            ▼
Service Layer (Business Logic)
            │
            ▼
Repository Layer (Spring Data JPA)
            │
            ▼
Database (PostgreSQL)
```


---

# Technology Stack

## Backend
- Java
- Spring Boot
- Spring MVC
- Spring Security
- Spring Data JPA
- Hibernate

## Database
- PostgreSQL

## Build Tool
- Maven

## API Communication
- REST APIs
- JSON

## Authentication
- JWT-based authentication

---

# Project Structure
```
office_one
│
├── src
│ └── main
│ ├── java
│ │ └── com.officeone
│ │
│ │ ├── config
│ │ │ SecurityConfig.java
│ │ │ JwtConfig.java
│ │ │
│ │ ├── controller
│ │ │ AttendanceController.java
│ │ │ LeaveController.java
│ │ │ UserController.java
│ │ │
│ │ ├── service
│ │ │ AttendanceService.java
│ │ │ LeaveService.java
│ │ │ UserService.java
│ │ │
│ │ ├── repository
│ │ │ AttendanceRepository.java
│ │ │ LeaveRepository.java
│ │ │ UserRepository.java
│ │ │
│ │ ├── entity
│ │ │ User.java
│ │ │ UserAttendance.java
│ │ │ UserInactiveTime.java
│ │ │ UserLeaveRequest.java
│ │ │
│ │ ├── dto
│ │ │ AttendanceDTO.java
│ │ │ WeeklySummaryDTO.java
│ │ │
│ │ ├── enums
│ │ │ UserRole.java
│ │ │ LeaveStatus.java
│ │ │
│ │ └── helper
│ │ AttendanceHelper.java
│
└── resources
application.yml
```


---

# Attendance Module

The attendance module allows employees to record and track their working hours.

## Check-In
Employees start their workday by marking check-in.
 ``` 
 POST /api/attendance/check-in 
 ```

## Check-Out
Employees end their workday by marking check-out.
```
POST /api/attendance/check-out
```

## Out Of Office
Employees can temporarily pause their work session.
```
POST /api/attendance/out-of-office
```

## Back In Office
Employees resume work after a break.
```
POST /api/attendance/back-in-office
```

# Weekly Attendance Summary

Employees can view their weekly attendance summary including:

- Daily check-in time
- Daily check-out time
- Working hours
- Overtime
- Total weekly working hours
```
GET /api/attendance/week-summary
```


---

# Setup Instructions

## 1 Clone Repository

```
git clone "repo_url"
```


---

## 2 Navigate to Project Directory
``` 
cd office_one
```

---

## 3 Configure Database

Update `application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/office_one
    username: postgres
    password: your_password
```

---

## 4 Run Application
```shell
mvn spring-boot:run
```
**Application will start at:**
```shell
http://localhost:8080
```

# Use Cases
**Office One can be used for:**
- Corporate HR systems
- Workforce productivity monitoring
- Employee attendance tracking
- Remote team monitoring tools