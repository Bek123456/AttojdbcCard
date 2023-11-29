package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.enums.Type;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Transaction {
    private Integer id;
    private String card_number;
    private double amount;
     private String terminal_code;
     private Type type;
     private LocalDateTime created_date;
}
