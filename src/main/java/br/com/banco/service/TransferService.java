package br.com.banco.service;

import br.com.banco.dto.TransferDTO;
import br.com.banco.repository.TransferRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TransferService {

    private final TransferRepository transferRepository;
    private final ModelMapper modelMapper;

    public List<TransferDTO> findAll(){
        return transferRepository.findAll().stream().map(transfer -> modelMapper.map(transfer, TransferDTO.class)).toList();
    }
}
