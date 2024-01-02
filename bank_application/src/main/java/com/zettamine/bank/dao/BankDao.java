package com.zettamine.bank.dao;

import java.util.List;
import java.util.Optional;

import com.zettamine.bank.dto.BankDto;
import com.zettamine.bank.dto.SearchCriteria;

public interface BankDao<T extends BankDto,S extends SearchCriteria> {
	
	Optional<T> get(int id);
    
    List<T> getAll();
    
    List<T> getBySearchCriteria(S criteria);
    
    boolean save(T t);
    
    int update(T t, String...params);
    
    void delete(T t);

}
