package com.epam.telephonedirectory.services;

import com.epam.telephonedirectory.entities.TelephoneCompany;
import com.epam.telephonedirectory.entities.TelephoneNumber;
import org.springframework.transaction.annotation.Transactional;

public interface ITelepnoneCompanyService {
    void changeMobileOperator(TelephoneNumber telephoneNumber, TelephoneCompany telephoneCompany);
}
