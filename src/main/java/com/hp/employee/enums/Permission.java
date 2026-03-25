package com.hp.employee.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Permission {

    //CLAIMS
    CLAIM_CREATE("claim:create"),
    CLAIM_UPDATE("claim:update"),
    CLAIM_DELETE("claim:delete"),
    CLAIM_VIEW("claim:view"),

    //EMPLOYEE
    EMPLOYEE_READ("employee:read"),
    EMPLOYEE_UPDATE("employee:update"),
    EMPLOYEE_DELETE("employee:delete"),

    //SHIFT
    SHIFT_VIEW("shift:view"),

    //TIMESHEET
    TIMESHEET_CREATE("timesheet:create"),
    TIMESHEET_VIEW("timesheet:view"),
    TIMESHEET_UPDATE("timesheet:update"),
    TIMESHEET_DELETE("timesheet:delete"),
    TIMESHEET_MANAGE("timesheet:manage"),

    //REPORT
    REPORT_VIEW("report:view"),
    REPORT_DOWNLOAD("report:download");

    private final String permission;
}
