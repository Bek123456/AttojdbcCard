package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.model.Card;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CuseCard {
    private boolean bor;
    private Card card;
}
