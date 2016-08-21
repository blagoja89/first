package by.julia.bankservice.comand;

public enum CommandEnum {

	PAGE {
		{
			this.command = new PageCommand();
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
