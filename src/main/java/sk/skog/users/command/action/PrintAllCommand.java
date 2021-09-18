package sk.skog.users.command.action;

import sk.skog.users.service.PersistenceService;

/**
 * Command that prints all entities read from database of given type.
 * 
 * @author Robert Kristofic
 *
 */
public class PrintAllCommand extends Command {
	private Class<?> entityClass;
	
	public PrintAllCommand(Class<?> entityClass) {
		this.entityClass = entityClass;
	}

	@Override
	protected void executeCommand() {
		PersistenceService
			.getInstance()
			.findAll(entityClass)
			.forEach(System.out::println);
	}

}
