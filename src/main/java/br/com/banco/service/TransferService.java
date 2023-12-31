package br.com.banco.service;

import br.com.banco.dto.TransferDTO;
import br.com.banco.model.Transfer;
import br.com.banco.repository.TransferRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


@RequiredArgsConstructor
@Service
public class TransferService {

    private final TransferRepository transferRepository;
    private final ModelMapper modelMapper;

    public Page<TransferDTO> findAll(Pageable pageable) {
        return transferRepository.findAll(pageable).map(transfer -> modelMapper.map(transfer, TransferDTO.class));
    }

    public Page<TransferDTO> findByOperatorName(String name, Pageable pageable) {
        return transferRepository.findByOperatorNameContainingIgnoreCase(name, pageable).map(transfer -> modelMapper.map(transfer, TransferDTO.class));
    }

    public Page<TransferDTO> findByDateBetween(String startDate, String endDate, Pageable pageable) {
        // captura o date fornecido pelo usuario e passa para LocalDate
        LocalDate parsedStartDate = LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE);
        LocalDate parsedEndDate = LocalDate.parse(endDate, DateTimeFormatter.ISO_DATE);

        // passa o LocalDate para LocalDateTime
        LocalDateTime startDateTime = LocalDateTime.of(parsedStartDate, LocalTime.MIN);
        LocalDateTime endDateTime = LocalDateTime.of(parsedEndDate, LocalTime.MAX);

        return transferRepository.findByDateBetween(startDateTime, endDateTime, pageable).map(transfer -> modelMapper.map(transfer, TransferDTO.class));
    }

    public Page<TransferDTO> findByOperatorNameAndDateBetween(String operatorName, String startDate, String endDate, Pageable pageable) {
        LocalDate parsedStartDate = LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE);
        LocalDate parsedEndDate = LocalDate.parse(endDate, DateTimeFormatter.ISO_DATE);

        LocalDateTime startDateTime = LocalDateTime.of(parsedStartDate, LocalTime.MIN);
        LocalDateTime endDateTime = LocalDateTime.of(parsedEndDate, LocalTime.MAX);

        Page<Transfer> transferPage = transferRepository.findByOperatorNameAndDateBetween(operatorName, startDateTime, endDateTime, pageable);

        return transferPage.map(transfer -> modelMapper.map(transfer, TransferDTO.class));
    }
}
