package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.enums.StatusType;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Card {
    private Integer id;
    private String number;
    private String exp_date;
    private double balance;
    private StatusType status;
    private String phone;
    private LocalDateTime create_date;
}
