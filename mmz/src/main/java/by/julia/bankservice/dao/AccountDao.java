package by.julia.bankservice.dao;


import by.julia.bankservice.entity.Account;

public class AccountDao extends AbstractDao<Account> {

	@Override
	public Class<Account> getEntityClass() {
		return Account.class;
	}
		
}
