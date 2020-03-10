package com.epam.telephonedirectory;

import com.epam.telephonedirectory.entities.TelephoneCompany;
import com.epam.telephonedirectory.entities.TelephoneNumber;
import com.epam.telephonedirectory.repository.TelephoneCompanyRepository;
import com.epam.telephonedirectory.repository.TelephoneNumberRepository;
import com.epam.telephonedirectory.services.ITelephoneNumberService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TelepnoneNumberServiceTest {

    @Autowired
    private ITelephoneNumberService telephoneNumberService;

    @Autowired
    private TelephoneNumberRepository telephoneNumberRepository;

    @Autowired
    private TelephoneCompanyRepository telephoneCompanyRepository;

    @Test
    public void changeMobileOperator() {

        TelephoneNumber telephoneNumber = telephoneNumberRepository.findTopByOrderByUser();

        TelephoneCompany telephoneCompany = telephoneCompanyRepository.findFirstByIdNotIn(Collections.singletonList(telephoneNumber.getTelephoneCompany().getId()));

        telephoneNumberService.changeMobileOperator(telephoneNumber, telephoneCompany);

        Optional<TelephoneNumber> changedTelephoneNumber = telephoneNumberRepository.findById(telephoneNumber.getId());

        Assert.assertEquals(telephoneCompany, changedTelephoneNumber.map(TelephoneNumber::getTelephoneCompany).orElse(null));
    }
}
