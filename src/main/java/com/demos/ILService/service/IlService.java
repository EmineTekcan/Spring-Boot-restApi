package com.demos.ILService.service;

import com.demos.ILService.exception.IlAlreadyExistException;
import com.demos.ILService.exception.IlNotFoundException;
import com.demos.ILService.model.Il;
import com.demos.ILService.repository.IlRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class IlService {

    private IlRepository ilRepository;
    public List<Il> getIller(String name) {
        if(name==null)
            return ilRepository.findAll();
        return ilRepository.findByName(name);
    }

    public Il getIl(int id){
        return ilRepository.findById(id)
                .orElseThrow(()->new IlNotFoundException("Il not found with id: "+id));
    }

    public Il createIl(Il il) {

        Il il1=ilRepository.findById(il.getId()).orElse(null);
        if(il1 != null)
            throw new IlAlreadyExistException("Il already found");
        return ilRepository.save(il);
    }

    public Il updateIl(Il il, int id) {
        Il foundIl=ilRepository.findById(id).orElse(null);
        if(foundIl == null)
            return null;
        foundIl.setName(il.getName());
        foundIl.setCreateDate(il.getCreateDate());
        return ilRepository.save(foundIl);
    }

    public void deleteIl(int id) {
        ilRepository.deleteById(id);
    }
}
