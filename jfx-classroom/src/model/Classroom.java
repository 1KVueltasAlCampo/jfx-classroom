package model;

import java.util.ArrayList;
import java.util.List;

public class Classroom {
	private List<UserAccount> users;
	
	public Classroom() {
		users = new ArrayList<>();
	}
	public void addUsers(UserAccount uacc) {
		users.add(uacc);
	}
	public List<UserAccount> getUsers(){
		return users;
	}
	
}
