package com.eazybytes.accounts.service;

import com.eazybytes.accounts.dto.CustomerDto;

public interface IAccountsService {




    /**
     *
     * @param customerDto the customer details for account creation
     */
    void createAccount(CustomerDto customerDto);


    /**
     * @param mobileNumber - Input Mobile Number
     * @return Accounts Details based on a given mobile number
     */
    CustomerDto fetchAccount(String mobileNumber);


    /**
     *
     * @param customerDto
     * @return
     */
    boolean updateAccount(CustomerDto customerDto);


    /**
     *
     * @param mobileNumber - Input Mobile Number
     * @return boolean indicating if the delete of Account is successful or not.
     */

    boolean deleteAccount(String mobileNumber);




}
