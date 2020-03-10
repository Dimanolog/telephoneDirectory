package com.epam.telephonedirectory.repository;

import com.epam.telephonedirectory.entities.TelephoneCompany;
import com.epam.telephonedirectory.entities.TelephoneNumber;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface  TelephoneCompanyRepository extends CrudRepository<TelephoneCompany, Long> {
    
    TelephoneCompany findFirstByIdNotIn(List<Long> notIn);
    
}
