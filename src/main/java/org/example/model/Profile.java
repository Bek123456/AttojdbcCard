package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.enums.StatusType;
import org.example.enums.UserRole;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Profile {
    private Integer id;
    private String name;
    private String surname;
    private String phone;
    private String password;
    private LocalDateTime created_date;
    private StatusType status;
    private UserRole role;
}
