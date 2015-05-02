package exceptions;


public class PostconditionError extends ContractError {

	private static final long serialVersionUID = 1L;

	public PostconditionError(String message) {
		super("PreconditionError : " + message);
	}
}
