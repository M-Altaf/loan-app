package com.loan.app.TestServices;

import com.loan.app.entity.LoanEntity;
import com.loan.app.repository.LoanRepository;
import com.loan.app.rest.dto.Loandto.LoanRequestDto;
import com.loan.app.rest.dto.Loandto.LoanResponseDto;
import com.loan.app.service.impl.LoanServiceImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class LoanTest {

    @Mock
    LoanRepository loanRepository;

    @InjectMocks
    private LoanServiceImpl loanService;

    @Test
    void addLoan_ShouldReturnLoanRequestDto() {

        // Arrange
        LoanRequestDto dto =
                new LoanRequestDto("muhammad", 3000.0, 4.4, 3);

        LoanEntity savedLoan = new LoanEntity();
        savedLoan.setLoanTakenFrom("muhammad");
        savedLoan.setAmount(3000.0);
        savedLoan.setProfitRate(4.4);
        savedLoan.setDurationInMonths(3);

        when(loanRepository.save(any(LoanEntity.class)))
                .thenReturn(savedLoan);

        // Act
        LoanResponseDto result = loanService.addLoan(dto);

        // Assert
        assertNotNull(result);
        assertEquals("muhammad", result.getLoanTakenFrom());
        assertEquals(3000.0, result.getAmount());

        verify(loanRepository).save(any(LoanEntity.class));
    }

//           For get Loan by id ******
    @Test
    void getLoanById_ShouldReturnLoan() {

        LoanEntity loan = new LoanEntity();
        loan.setId(1L);
        loan.setLoanTakenFrom("Ali");
        loan.setAmount(5000.0);

        when(loanRepository.findById(1L))
                .thenReturn(Optional.of(loan));

        LoanResponseDto result = loanService.getLoanById(1L);

        assertNotNull(result);
        assertEquals("Ali", result.getLoanTakenFrom());

        verify(loanRepository, times(1)).findById(1L);
    }



//    this is for Get all loan
@Test
void getAllLoans_ShouldReturnList() {

    LoanEntity loan1 = new LoanEntity();
    loan1.setLoanTakenFrom("Ali");

    LoanEntity loan2 = new LoanEntity();
    loan2.setLoanTakenFrom("Ahmed");

    when(loanRepository.findAll())
            .thenReturn(List.of(loan1, loan2));

    List<LoanResponseDto> result = loanService.getAllLoans();

    assertEquals(2, result.size());
    verify(loanRepository).findAll();
}


// getLoan  by id condition
@Test
void getLoanById_ShouldThrowException_WhenNotFound() {

    when(loanRepository.findById(1L))
            .thenReturn(Optional.empty());

    assertThrows(RuntimeException.class, () -> {
        loanService.getLoanById(1L);
    });

    verify(loanRepository).findById(1L);
}

//  update by id Laon
@Test
void updateLoan_ShouldUpdateAndReturnLoan() {

    LoanEntity existingLoan = new LoanEntity();
    existingLoan.setId(1L);
    existingLoan.setLoanTakenFrom("Old Name");

    LoanRequestDto dto =
            new LoanRequestDto("New Name", 8000.0, 5.0, 12);

    when(loanRepository.findById(1L))
            .thenReturn(Optional.of(existingLoan));

    when(loanRepository.save(any(LoanEntity.class)))
            .thenReturn(existingLoan);

    LoanResponseDto result =
            loanService.updateLoan(1L, dto);

    assertEquals("New Name", result.getLoanTakenFrom());

    verify(loanRepository).save(any());
}


// tis is for patch by id Loan
@Test
void patchLoan_ShouldUpdatePartialFields() {

    LoanEntity existingLoan = new LoanEntity();
    existingLoan.setId(1L);
    existingLoan.setLoanTakenFrom("Ali");

    LoanRequestDto dto =
            new LoanRequestDto(null, 9000.0, null, null);

    when(loanRepository.findById(1L))
            .thenReturn(Optional.of(existingLoan));

    when(loanRepository.save(any()))
            .thenReturn(existingLoan);

    LoanResponseDto result =
            loanService.patchLoan(1L, dto);

    assertEquals(9000.0, result.getAmount());

    verify(loanRepository).save(any());
}

// this is for delete by id
@Test
void deleteLoan_ShouldDeleteLoan() {

    when(loanRepository.existsById(1L))
            .thenReturn(true);

    loanService.deleteLoan(1L);

    verify(loanRepository, times(1))
            .deleteById(1L);
}
}
