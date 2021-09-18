package sk.skog.users.command.action;

import sk.skog.users.service.PersistenceService;

/**
 * Command that takes entity and persist it to the database.
 * 
 * @author Robert Kristofic
 */
public class AddCommand extends Command {
	private Object entity;
	
	public AddCommand(Object entity) {
		this.entity = entity;
	}
	
	@Override
	protected void executeCommand() {
		PersistenceService
			.getInstance()
			.add(entity);
	}
	
}
