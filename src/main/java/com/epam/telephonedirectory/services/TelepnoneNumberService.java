package com.epam.telephonedirectory.services;

import com.epam.telephonedirectory.entities.TelephoneCompany;
import com.epam.telephonedirectory.entities.TelephoneNumber;
import com.epam.telephonedirectory.exceptions.BusinesException;
import com.epam.telephonedirectory.repository.TelephoneNumberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class TelepnoneNumberService implements ITelepnoneCompanyService {

    private IUserAccountService userAccountService;
    private TelephoneNumberRepository telephoneNumberRepository;

    @Autowired
    public TelepnoneNumberService(IUserAccountService userAccountService, TelephoneNumberRepository telephoneNumberRepository) {
        this.userAccountService = userAccountService;
        this.telephoneNumberRepository = telephoneNumberRepository;
    }

    @Override
    @Transactional
    public void changeMobileOperator(TelephoneNumber telephoneNumber, TelephoneCompany telephoneCompany) {

        if (!telephoneNumber.getTelephoneCompany().equals(telephoneCompany)) {
            userAccountService.payMoney(telephoneNumber.getUser(), new BigDecimal(5));
            telephoneNumber.setTelephoneCompany(telephoneCompany);
            telephoneNumberRepository.save(telephoneNumber);
        } else {
            throw new BusinesException("telephoneCompanies are the same");
        }
    }
}
