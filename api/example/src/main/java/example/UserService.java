package example;

import java.util.Collection;

import org.omg.CORBA.UserException;

public interface UserService {
	public static void addUser (User user) {
		// TODO Auto-generated method stub
		
	}
    
    public Collection<User> getUsers ();
    public User getUser (String id);
     
    public User editUser (User user) 
      throws UserException;
     
    public void deleteUser (String id);
     
    public boolean userExist (String id);
}
