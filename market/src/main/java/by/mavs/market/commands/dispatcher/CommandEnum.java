package by.mavs.market.commands.dispatcher;




import by.mavs.market.commands.AddProductCommand;
import by.mavs.market.commands.ICommand;
import by.mavs.market.commands.SaveProductCommand;
import by.mavs.market.commands.ViewCategoriesCommand;
import by.mavs.market.commands.ViewProductsCommand;
import by.mavs.market.commands.ViewSubcategoriesCommand;


public enum CommandEnum {

	VIEW_CATEGORIES {
		{
			this.command = new ViewCategoriesCommand();
		}
	},
	VIEW_SUBCATEGORIES {
		{
			this.command = new ViewSubcategoriesCommand();
		}
	},
	VIEW_PRODUCT {
		{
			this.command = new ViewProductsCommand();
		}
	},
	ADD_PRODUCT {
		{
			this.command = new AddProductCommand();
		}
	},
	SAVE_PRODUCT {
		{
			this.command = new SaveProductCommand();
		}
	};

	ICommand command;

	public ICommand getCurrentCommand() {
		return command;
	}

}


