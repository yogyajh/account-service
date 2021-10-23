package com.bank.account.controller;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.bank.account.util.exception.AccountsNotFoundException;
import com.bank.account.util.exception.ErrorMessage;

/**
*
* @author Yogya Hewavidana
*
*/

@RestControllerAdvice
public class AcctExceptionHandler {

    @ExceptionHandler({EntityNotFoundException.class, AccountsNotFoundException.class})
    public ResponseEntity<ErrorMessage> handleInvalidParameter(EntityNotFoundException ex, WebRequest request){
    	
	  return new ResponseEntity<>(new ErrorMessage(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
