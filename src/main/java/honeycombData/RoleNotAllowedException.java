package honeycombData;

@SuppressWarnings("serial")
public class RoleNotAllowedException extends Exception
{
    public RoleNotAllowedException(Page page, String role) {
    	super("A page of class " + page.getClass() 
        + " cannot assume the role " + role);
    }
}
