package br.com.banco.repository;

import br.com.banco.model.Transfer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;


public interface TransferRepository extends JpaRepository<Transfer, Long> {

    Page<Transfer> findByOperatorNameContaining(String name, Pageable pageable);

    Page<Transfer> findByDateBetween(LocalDateTime date, LocalDateTime date2, Pageable pageable);
}
