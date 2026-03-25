package com.hp.employee.security;

import com.hp.employee.entity.Employee;
import com.hp.employee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerUserDetailsService implements UserDetailsService {

    private final EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        //ROLE
        authorities.add(
                new SimpleGrantedAuthority("ROLE_" + employee.getRole().name())
        );

        //Permissions
        RolePermissionMapping.getPermission(employee.getRole())
                .forEach(permission ->
                        authorities.add(
                                new SimpleGrantedAuthority(permission.getPermission())
                        ));

        return new User(
                employee.getEmail(),
                employee.getPassword(),
                authorities
        );
    }
}
