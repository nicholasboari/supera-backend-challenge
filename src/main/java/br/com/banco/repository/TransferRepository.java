package br.com.banco.repository;

import br.com.banco.model.Transfer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;


public interface TransferRepository extends JpaRepository<Transfer, Long> {

    Page<Transfer> findByOperatorNameContainingIgnoreCase(String name, Pageable pageable);

    Page<Transfer> findByDateBetween(LocalDateTime date, LocalDateTime date2, Pageable pageable);

    @Query("SELECT t FROM Transfer t WHERE UPPER(t.operatorName) LIKE UPPER(concat('%', :name, '%')) AND t.date BETWEEN :startDate AND :endDate")
    Page<Transfer> findByOperatorNameAndDateBetween(String name, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);
}
