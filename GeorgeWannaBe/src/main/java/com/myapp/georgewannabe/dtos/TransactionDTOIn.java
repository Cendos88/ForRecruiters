package com.myapp.georgewannabe.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTOIn {
    private Long senderAccountId;
    private Long receiverAccountId;
    private Double amount;
    private String description;

}
