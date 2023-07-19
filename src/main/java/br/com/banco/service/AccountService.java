package br.com.banco.service;

import br.com.banco.dto.AccountDTO;
import br.com.banco.exception.BadRequestException;
import br.com.banco.model.Account;
import br.com.banco.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final ModelMapper modelMapper;

    public Page<AccountDTO> findAll(Pageable pageable){
        return accountRepository.findAll(pageable).map(account -> modelMapper.map(account, AccountDTO.class));
    }

    public AccountDTO findById(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new BadRequestException("Account not found"));
        return modelMapper.map(account, AccountDTO.class);
    }

    public AccountDTO save(AccountDTO accountDTO) {
        Account account = modelMapper.map(accountDTO, Account.class);
        Account accountSaved = accountRepository.save(account);
        return modelMapper.map(accountSaved, AccountDTO.class);
    }

    public AccountDTO update(AccountDTO accountDTO, Long id) {
        AccountDTO account = findById(id);
        account.setName(accountDTO.getName());
        accountRepository.save(modelMapper.map(account, Account.class));
        return account;
    }

    public void delete(Long id) {
        findById(id); // verifica se ID existe
        accountRepository.deleteById(id);
    }
}
