package br.com.banco.service;

import br.com.banco.dto.TransferDTO;
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
import java.util.List;


@RequiredArgsConstructor
@Service
public class TransferService {

    private final TransferRepository transferRepository;
    private final ModelMapper modelMapper;

    public List<TransferDTO> findAll(){
        return transferRepository.findAll().stream().map(transfer -> modelMapper.map(transfer, TransferDTO.class)).toList();
    }

    public Page<TransferDTO> findByOperatorName(String name, Pageable pageable) {
        return transferRepository.findByOperatorNameContaining(name, pageable).map(transfer -> modelMapper.map(transfer, TransferDTO.class));
    }

    public Page<TransferDTO> findByDateBetween(String startDate, String endDate, Pageable pageable){
        // captura o date fornecido pelo usuario e passa para LocalDate
        LocalDate parsedStartDate = LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE);
        LocalDate parsedEndDate = LocalDate.parse(endDate, DateTimeFormatter.ISO_DATE);

        // passa o LocalDate para LocalDateTime
        LocalDateTime startDateTime = LocalDateTime.of(parsedStartDate, LocalTime.MIN);
        LocalDateTime endDateTime = LocalDateTime.of(parsedEndDate, LocalTime.MAX);

        return transferRepository.findByDateBetween(startDateTime, endDateTime, pageable).map(transfer -> modelMapper.map(transfer, TransferDTO.class));
    }
}
