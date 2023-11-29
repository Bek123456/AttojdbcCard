package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.enums.StatusType;

import java.time.LocalDateTime;
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Terminal {
    private Integer id;
    private String code;
    private String address;
    private StatusType status;
    private LocalDateTime created_date;
}
