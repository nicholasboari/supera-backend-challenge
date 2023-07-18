package br.com.banco.util;

import br.com.banco.dto.TransferDTO;
import br.com.banco.model.Account;
import br.com.banco.model.Transfer;

import java.time.LocalDateTime;

public abstract class Creator {
    public static Account createAccount(){
        Account account = new Account();
        account.setName("Joao");
        account.setId(1L);
        return account;
    }

    public static Transfer createTransfer(){
        Transfer transfer = new Transfer();
        transfer.setId(1L);
        transfer.setValue(100F);
        transfer.setDate(LocalDateTime.parse("2023-06-08T04:15:01"));
        transfer.setAccountId(1L);
        return transfer;
    }

    public static TransferDTO createTransferDTO() {
        TransferDTO transferDTO = new TransferDTO();
        transferDTO.setId(1L);
        transferDTO.setValue(100F);
        transferDTO.setDate(LocalDateTime.parse("2023-06-08T04:15:01"));
        transferDTO.setAccountId(1L);
        return transferDTO;
    }
}
