package com.example.onlineshop.controller.closed;

import com.example.onlineshop.model.transaction.Transaction;
import com.example.onlineshop.service.transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.Collection;

@Validated
@RestController
@RequestMapping("api/v1/private/transactions")
public class TransactionRestController {

    private final TransactionService service;

    @Autowired
    public TransactionRestController(TransactionService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Collection<Transaction>> findCustomerTransactions(@Min(value = 1, message = "ID must be equal or greater than 1")
                                                                                @PathVariable Long id){
        Collection<Transaction> collection = this.service.findCustomerTransactions(id);
        return new ResponseEntity<>(collection, HttpStatus.OK);
    }

    @PostMapping("/commit")
    public ResponseEntity<Transaction> doTransaction(@Valid @RequestBody TransactionDTO transactionDTO){
        Transaction entity = this.service.doTransaction(transactionDTO.getCustomerId(),
                transactionDTO.getAmount(),transactionDTO.getTransactionType());
        return new ResponseEntity<>(entity,HttpStatus.OK);
    }

}
