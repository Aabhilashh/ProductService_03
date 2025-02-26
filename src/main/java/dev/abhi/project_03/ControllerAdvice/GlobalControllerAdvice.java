package dev.abhi.project_03.ControllerAdvice;

import dev.abhi.project_03.Exceptions.ProductNotFoundException;
import dev.abhi.project_03.dtos.ExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalControllerAdvice {

//    @ExceptionHandler(NullPointerException.class)
//    public ResponseEntity<String> NullPointerExceptionHandler() {
//        ResponseEntity<String> response = new ResponseEntity<>(
//                "Something went wrong, having Null pointer error",
//                HttpStatus.BAD_REQUEST
//        );
//        return response;
//    }

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<ExceptionDto> ArthematicExceptionHandler() {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("Arithmetic exception");
        exceptionDto.setSolution("I dont know, please try again");
        ResponseEntity<ExceptionDto> response = new ResponseEntity<>(
                exceptionDto,
                HttpStatus.BAD_REQUEST
        );
        return response;
    }

    @ExceptionHandler(IndexOutOfBoundsException.class)
    public ResponseEntity<String> IndexOutOfBoundsExceptionHandler() {
        ResponseEntity<String> response = new ResponseEntity<>(
                "Something went wrong, having Index out of bound error",
                HttpStatus.BAD_REQUEST
        );
        return response;
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<String> ExceptionHandler() {
//        ResponseEntity<String> response = new ResponseEntity<>(
//                "Something went wrong, just showing the exception ",
//                HttpStatus.BAD_REQUEST
//        );
//        return response;
//    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ExceptionDto> HandlerProductNotFoundException(){
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("Product not found");
        exceptionDto.setSolution("Try with another ProductId");
        ResponseEntity<ExceptionDto> response = new ResponseEntity<>(
                exceptionDto,
                HttpStatus.BAD_REQUEST
        );
        return response;

    }
}
