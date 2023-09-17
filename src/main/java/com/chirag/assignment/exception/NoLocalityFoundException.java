package com.chirag.assignment.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.NOT_FOUND)
// HTTP response status code 404 to be returned when this exception is thrown
// custom exception that is thrown when no Locality is found.
public class NoLocalityFoundException extends RuntimeException{
    // makes it an unchecked exception.
    private String message;
    public NoLocalityFoundException() {
    }

    public NoLocalityFoundException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "{" +
            " message='" + getMessage() + "'" +
            "}";
    }

}
