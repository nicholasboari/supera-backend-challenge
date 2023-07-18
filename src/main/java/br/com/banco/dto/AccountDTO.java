package br.com.banco.dto;

import br.com.banco.model.TransferType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AccountDTO {

    private Long id;
    private LocalDateTime date;
    private Float value;
    private TransferType type;
    private String operatorName;
    private String accountId;
}
