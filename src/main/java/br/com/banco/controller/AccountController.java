package br.com.banco.controller;

import br.com.banco.dto.AccountDTO;
import br.com.banco.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/all")
    public ResponseEntity<Page<AccountDTO>> findAll(Pageable pageable) {
        Page<AccountDTO> page = accountService.findAll(pageable);
        return ResponseEntity.ok().body(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> findById(@PathVariable Long id) {
        AccountDTO account = accountService.findById(id);
        return ResponseEntity.ok().body(account);
    }

    @PostMapping
    public ResponseEntity<AccountDTO> save(@RequestBody AccountDTO accountDTO) {
        AccountDTO account = accountService.save(accountDTO);
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(account);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountDTO> save(@RequestBody AccountDTO accountDTO, @PathVariable Long id) {
        AccountDTO account = accountService.update(accountDTO, id);
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(account);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> save(@PathVariable Long id) {
        accountService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT.value()).build();
    }
}
