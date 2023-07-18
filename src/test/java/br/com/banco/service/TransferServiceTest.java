package br.com.banco.service;

import br.com.banco.dto.TransferDTO;
import br.com.banco.model.Transfer;
import br.com.banco.repository.TransferRepository;
import br.com.banco.util.Creator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class TransferServiceTest {

    @Mock
    private TransferRepository transferRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private TransferService transferService;

    @BeforeEach
    void setup() {
        Transfer transfer = Creator.createTransfer();
        TransferDTO transferDTO = Creator.createTransferDTO();

        PageImpl<Transfer> transferPage = new PageImpl<>(List.of(transfer));

        // Definir o comportamento do ModelMapper
        when(modelMapper.map(any(Transfer.class), eq(TransferDTO.class))).thenReturn(transferDTO);

        when(transferRepository.findAll()).thenReturn(List.of(transfer));
        when(transferRepository.findByOperatorNameContaining(anyString(), any(PageRequest.class))).thenReturn(transferPage);
        when(transferRepository.findByDateBetween(any(LocalDateTime.class), any(LocalDateTime.class), any(PageRequest.class))).thenReturn(transferPage);
        when(transferRepository.findByOperatorNameAndDateBetween(anyString(), any(LocalDateTime.class), any(LocalDateTime.class), any(Pageable.class))).thenReturn(transferPage);
    }


    @Test
    @DisplayName("findAll return a list of TransferDTO")
    void testFindAll() {
        List<TransferDTO> list = transferService.findAll();

        Assertions.assertFalse(list.isEmpty());
        Assertions.assertNotNull(list);
        Assertions.assertEquals(1, list.size());
    }

    @Test
    @DisplayName("findByOperatorName return a page of TransferDTO")
    void testFindByOperatorName() {
        Page<TransferDTO> page = transferService.findByOperatorName("", PageRequest.of(1, 1));

        Assertions.assertFalse(page.isEmpty());
        Assertions.assertEquals(1,page.toList().size());
        Assertions.assertEquals(1, page.getTotalPages());
        Assertions.assertEquals(1, page.getTotalElements());
    }

    @Test
    @DisplayName("findByDateBetween returns a page of TransferDTO")
    void testFindByDateBetween() {
        // Mock data
        String startDate = "2020-06-08";
        String endDate = "2023-06-08";
        Pageable pageable = PageRequest.of(0, 10);

        Page<TransferDTO> result = transferService.findByDateBetween(startDate, endDate, pageable);

        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(1, result.getTotalElements());
        Assertions.assertEquals(1L, result.getContent().get(0).getId());
        Assertions.assertEquals(100, result.getContent().get(0).getValue());
    }

    @Test
    @DisplayName("findByOperatorNameAndDateBetween returns a page of TransferDTO")
    void testFindByOperatorNameAndDateBetween() {
        // Mock data
        String startDate = "2020-06-08";
        String endDate = "2023-06-08";
        Pageable pageable = PageRequest.of(0, 10);

        Page<TransferDTO> result = transferService.findByOperatorNameAndDateBetween("", startDate, endDate, pageable);

        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(1, result.getTotalElements());
        Assertions.assertEquals(1L, result.getContent().get(0).getId());
        Assertions.assertEquals(100, result.getContent().get(0).getValue());
    }
}