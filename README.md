# employeeShifttTracker


-> Employee Login
An employee logs in using their email and password.
Upon successful login, a new shift record is created with the start time.

-> Shift End
When an employee logs out, the shift's end time is recorded, and the actual hours are calculated.

-> Timesheet Entry
The employee fills out a timesheet with details of the work done during the shift.
Timesheet entries include project name, task name, and the duration of the task.

-> Reporting
Generate a report comparing the actual hours worked to the assigned shift hours.
This could be a simple API endpoint that aggregates and returns the data.
An api that will return the report of actual hours worked by employees, assigned hours , date , time , in excel format..

1️⃣ Employee → Shift

One employee can have multiple shifts

One shift belongs to one employee

2️⃣ Shift → Timesheet

One shift can have multiple timesheets

One timesheet belongs to one shift

3️⃣ Employee → Timesheet

One employee can have multiple timesheets

One timesheet belongs to one employee

4️⃣ Employee → Claims

One employee can have multiple claims

One claim belongs to one employee

