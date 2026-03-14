## The Attendance Module file structure:

```
attendance
│
├── controller
│    └── AttendanceController
│
├── service
│    ├── AttendanceService
│    ├── AttendanceCalculationService
│    └── AttendanceHelperService
│
├── repository
│    ├── UserAttendanceRepository
│    └── UserInactiveTimeRepository
│
├── entity
│    ├── UserAttendance
│    └── UserInactiveTime
│
├── dto
│    ├── UserAttendanceResponseDTO
│    └── UserInactiveTimeDTO
│
└── mapper
     └── AttendanceMapper
```

## The Endpoints of Attendance Module:

``` 
POST /api/attendance/check-in
```
``` 
POST /api/attendance/check-out
```
``` 
POST /api/attendance/out-of-office
```
``` 
POST /api/attendance/back-in-office
```
``` 
GET /api/attendance/admin/attendance-view
```
``` 
GET /api/attendance/teamlead/attendance-view
```
``` 
GET /api/attendance/week-summary
```