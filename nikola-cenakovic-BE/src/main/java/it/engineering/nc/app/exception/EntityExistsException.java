package it.engineering.nc.app.exception;

public class EntityExistsException extends MyProjectException {
	private static final long serialVersionUID = 7862032566957888020L;

	private final Object entity;

	public EntityExistsException(Object entity, String message) {
		super(message);
		this.entity = entity;
	}

	public Object getEntity() {
		return entity;
	}
}
