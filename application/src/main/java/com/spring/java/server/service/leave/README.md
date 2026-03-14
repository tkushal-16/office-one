## The flow of the leave module is shown below

```
Controller
    ↓
Request DTO
    ↓
Service
    ↓
Entity
    ↓
Repository
    ↓
Database
    ↓
Entity
    ↓
Mapper
    ↓
Response DTO
```


## The below is the Leave-module files structure:

```
leave
 ├── controller
 │      LeaveController
 │      LeaveSummaryController
 │
 ├── service
 │      LeaveService
 │      LeaveSummaryService
 │      LeaveCalculationService
 │
 ├── repository
 │      UserLeaveRequestRepository
 │      UserLeavesRecordRepository
 │
 ├── entity
 │      UserLeaveRequest
 │      UserLeavesRecord
 │
 ├── dto
 │      UserLeaveRequestDTO
 │      UserLeaveResponseDTO
 │
 └── mapper
        LeaveMapper
```