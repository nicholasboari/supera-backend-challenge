package br.com.banco.controller;

import br.com.banco.dto.TransferDTO;
import br.com.banco.service.TransferService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/transfers")
@RequiredArgsConstructor
public class TransferController {

    private final TransferService transferService;

    @Operation(summary = "return all transfers in a page")
    @GetMapping("/all")
    public ResponseEntity<Page<TransferDTO>> findAll(@ParameterObject Pageable pageable) {
        Page<TransferDTO> list = transferService.findAll(pageable);
        return ResponseEntity.ok().body(list);
    }

    @Operation(summary = "return transfers in a page by date")
    @GetMapping
    public ResponseEntity<Page<TransferDTO>> findByDate(
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate,
            @ParameterObject Pageable pageable) {
        Page<TransferDTO> page = transferService.findByDateBetween(startDate, endDate, pageable);
        return ResponseEntity.ok().body(page);
    }

    @Operation(summary = "return transfers in a page by Operator Name")
    @GetMapping("/find")
    public ResponseEntity<Page<TransferDTO>> findByOperatorName(@RequestParam(name = "operatorName") String name, @ParameterObject Pageable pageable) {
        Page<TransferDTO> page = transferService.findByOperatorName(name, pageable);
        return ResponseEntity.ok().body(page);
    }

    @Operation(summary = "return transfers in a page by Operator Name and Date")
    @GetMapping("/{operatorName}")
    public ResponseEntity<Page<TransferDTO>> findByOperatorNameAndDateBetween(
            @PathVariable String operatorName,
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate,
            @ParameterObject Pageable pageable) {
        Page<TransferDTO> page = transferService.findByOperatorNameAndDateBetween(operatorName, startDate, endDate, pageable);
        return ResponseEntity.ok().body(page);
    }

}
