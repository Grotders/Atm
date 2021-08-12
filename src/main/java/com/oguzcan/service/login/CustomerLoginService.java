package com.oguzcan.service.login;

import com.oguzcan.dao.AccountDAO;
import com.oguzcan.dao.CustomerDAO;
import com.oguzcan.dao.GenericDAO;
import com.oguzcan.dto.CustomerDTO;
import com.oguzcan.ex.NoSuchClientException;
import com.oguzcan.ex.WrongClientCredentialsException;

public class CustomerLoginService implements LoginService<CustomerDTO>{

	private GenericDAO<CustomerDTO> customerDao = new CustomerDAO();
	private AccountDAO accountDao = new AccountDAO();
	
	@Override
	public CustomerDTO login(String username, String password) 
			throws WrongClientCredentialsException, NoSuchClientException {
			
		CustomerDTO customer = customerDao.retrieve(username);
		loginCustomer(customer, password);
		customer.setAccountList(accountDao.retrieveAll(customer.getCustomerId()));
		return customer;
	}
	
	private void loginCustomer(CustomerDTO customer, String password) 
			throws WrongClientCredentialsException{
		
		checkCustomerPassword(customer, password);
		
	}
	
	private void checkCustomerPassword(CustomerDTO customer, String password) 
			throws WrongClientCredentialsException {
		
		if(!customer.getPassword().equals(password)) {
			throw new WrongClientCredentialsException("Parola Hatalı");
		}
	}
	

	@Override
	public void logout() {
		// TODO Auto-generated method stub
		
	}
	
}
