package com.hp.employee.security;

import com.hp.employee.enums.Permission;
import com.hp.employee.enums.Role;

import static com.hp.employee.enums.Permission.*;
import static com.hp.employee.enums.Role.*;


import java.util.Map;
import java.util.Set;

public class RolePermissionMapping {

    private static final Map<Role, Set<Permission>> map =Map.of(
            SUPER_ADMIN, Set.of(
                    CLAIM_CREATE, CLAIM_UPDATE, CLAIM_DELETE, CLAIM_VIEW,
                    EMPLOYEE_READ, EMPLOYEE_UPDATE, EMPLOYEE_DELETE,
                    SHIFT_VIEW,
                    REPORT_VIEW, REPORT_DOWNLOAD, TIMESHEET_CREATE, TIMESHEET_VIEW,
                    TIMESHEET_UPDATE, TIMESHEET_DELETE
            ),

            MANAGER, Set.of(
                    CLAIM_CREATE, CLAIM_UPDATE, CLAIM_DELETE, CLAIM_VIEW,
                    EMPLOYEE_READ, EMPLOYEE_UPDATE, EMPLOYEE_DELETE,
                    SHIFT_VIEW,
                    REPORT_VIEW, REPORT_DOWNLOAD, TIMESHEET_CREATE, TIMESHEET_VIEW,
                    TIMESHEET_UPDATE, TIMESHEET_DELETE
            ),

            EMPLOYEE, Set.of(
                    TIMESHEET_CREATE, TIMESHEET_VIEW, CLAIM_VIEW, EMPLOYEE_READ
            )
    );

    public static Set<Permission> getPermission(Role role) {
        return map.get(role);
    }
}
