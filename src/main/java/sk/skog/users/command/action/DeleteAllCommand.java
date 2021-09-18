package sk.skog.users.command.action;

import sk.skog.users.service.PersistenceService;

/**
 * Command that deletes all entities with given type (entity class) from database.
 * 
 * @author Robert Kristofic
 */
public class DeleteAllCommand extends Command {
	private Class<?> entityClass;
	
	public DeleteAllCommand(Class<?> entityClass) {
		this.entityClass = entityClass;
	}
	
	@Override
	protected void executeCommand() {
		PersistenceService
			.getInstance()
			.deleteAll(entityClass);
	}

}
