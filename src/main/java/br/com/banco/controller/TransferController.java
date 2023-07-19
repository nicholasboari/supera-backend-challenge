package br.com.banco.controller;

import br.com.banco.dto.TransferDTO;
import br.com.banco.service.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/transfers")
@RequiredArgsConstructor
public class TransferController {

    private final TransferService transferService;

    @GetMapping("/all")
    public ResponseEntity<Page<TransferDTO>> findAll(Pageable pageable) {
        Page<TransferDTO> list = transferService.findAll(pageable);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping
    public ResponseEntity<Page<TransferDTO>> findByDate(
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate,
            Pageable pageable) {
        Page<TransferDTO> page = transferService.findByDateBetween(startDate, endDate, pageable);
        return ResponseEntity.ok().body(page);
    }

    @GetMapping("/find")
    public ResponseEntity<Page<TransferDTO>> findByOperatorName(@RequestParam String name, Pageable pageable) {
        Page<TransferDTO> page = transferService.findByOperatorName(name, pageable);
        return ResponseEntity.ok().body(page);
    }

    @GetMapping("/{operatorName}")
    public ResponseEntity<Page<TransferDTO>> findByOperatorNameAndDateBetween(
            @PathVariable String operatorName,
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate,
            Pageable pageable) {
        Page<TransferDTO> page = transferService.findByOperatorNameAndDateBetween(operatorName, startDate, endDate, pageable);
        return ResponseEntity.ok().body(page);
    }

}
