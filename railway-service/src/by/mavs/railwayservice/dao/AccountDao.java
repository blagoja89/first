package by.mavs.railwayservice.dao;

import by.mavs.railwayservice.entity.Account;

public class AccountDao extends AbstractDao<Account> {

	@Override
	public Class<Account> getEntityClass() {
		return Account.class;
	}
		
}
