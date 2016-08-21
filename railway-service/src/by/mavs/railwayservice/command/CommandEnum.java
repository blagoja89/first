package by.mavs.railwayservice.command;

public enum CommandEnum {

	PAGE {
		{
			this.command = new PageCommand();
		}
	},
	ORDER {
		{
			this.command = new OrderCommand();
		}
	},
	BUY_TICKET{
		{
			this.command = new BuyTicketCommand();
		}
	},
	CHANGE_PROFILE{
		{
			this.command = new ChangeProfileDataCommand();
		}
	},
	ADMIN{
		{
			this.command = new AdminCommand();
		}
	},
	FIND_ALL_TICKETS{
		{
			this.command = new FindAllTicketsCommand();
		}
	},
	PAYMENT_TICKET{
		{
			this.command = new PaymentTicketCommand();
		}
	},
	SELECT_TICKET_SET {
		{
			this.command = new SelectTicketSettingCommand();
		}
	},
	FIND_ORDER {
		{
			this.command = new FindByOrderCommand();
		}
	},
	LANGUAGE {
		{
			this.command = new ChangeLanguageCommand();
		}
	},
	LOGIN {
		{
			this.command = new LoginCommand();
		}

	},
	LOGOUT {
		{
			this.command = new LogoutCommand();
		}
	},
	REGISTRATION{
		{
			this.command = new RegistrationCommand();
		}
	},
	ADD_TO_BILL{
		{
			this.command = new AccountCommand();
		}
	},
	MESSAGE{
		{
			this.command = new MessageCommand();
		}
	}
	;

	ActionCommand command;

	public ActionCommand getCurrentCommand() {
		return command;
	}

}
