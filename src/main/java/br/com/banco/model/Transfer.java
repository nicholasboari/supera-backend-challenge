package br.com.banco.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tb_transfer")
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime date;
    private Float value;
    @Enumerated(value = EnumType.STRING)
    private TransferType type;
    private String operatorName;
    private Long accountId;
}
