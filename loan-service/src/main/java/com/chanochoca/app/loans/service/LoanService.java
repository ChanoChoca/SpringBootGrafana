package com.chanochoca.app.loans.service;

import com.chanochoca.app.loans.repository.LoanRepository;
import com.chanochoca.app.loans.client.FraudDetectionClient;
import com.chanochoca.app.loans.dto.LoanDto;
import com.chanochoca.app.loans.entity.Loan;
import com.chanochoca.app.loans.entity.LoanStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanService {

    private final FraudDetectionClient fraudDetectionClient;
    private final LoanRepository loanRepository;

    public List<LoanDto> listAllLoans() {
        return loanRepository.findAll()
                .stream()
                .map(LoanDto::from)
                .toList();
    }

    public String applyLoan(LoanDto loanDto) {
        var loan = Loan.from(loanDto);
        LoanStatus loanStatus = fraudDetectionClient.evaluateLoan(loan.getCustomerId());
        loan.setLoanStatus(loanStatus);
        if (loanStatus.equals(LoanStatus.APPROVED)) {
            loanRepository.save(loan);
            return "Loan applied successfully";
        }
        return "Sorry! Your loan was not approved";
    }
}
