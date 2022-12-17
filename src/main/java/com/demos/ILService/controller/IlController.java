package com.demos.ILService.controller;

import com.demos.ILService.exception.IlAlreadyExistException;
import com.demos.ILService.exception.IlNotFoundException;
import com.demos.ILService.model.Il;
import com.demos.ILService.service.IlService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/iller")
@AllArgsConstructor
public class IlController {

    private final IlService ilService;

    @GetMapping
    public ResponseEntity<List<Il>> getIller(@RequestParam(required = false) String name){
        return new ResponseEntity<>(ilService.getIller(name),OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Il> getIl(@PathVariable int id){
            return new ResponseEntity<>(ilService.getIl(id),OK);
    }

    @PostMapping("/ekle")
    public ResponseEntity<Il> createIl(@RequestBody Il il){
        return new ResponseEntity<>(ilService.createIl(il),CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Il> updateIl(@RequestBody Il il, @PathVariable int id){
        return new ResponseEntity<>(ilService.updateIl(il,id),OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIl(@PathVariable int id){
        ilService.deleteIl(id);
        return new ResponseEntity<>(OK);
    }

    @ExceptionHandler(IlNotFoundException.class)
    public ResponseEntity<String> handlerIlNotFoundException(IlNotFoundException exception){
        return new ResponseEntity<>(exception.getMessage(),NOT_FOUND);
    }

    @ExceptionHandler(IlAlreadyExistException.class)
    public  ResponseEntity<String> handlerIlAlreadyExistException(IlAlreadyExistException exception){
        return new ResponseEntity<>(exception.getMessage(),CONFLICT);
    }
}
