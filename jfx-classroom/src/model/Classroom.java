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
	
	public boolean logInVerification(String username,String password) {
		for(int i=0;i<users.size();i++) {
			if(username.equals(users.get(i).getUsername()) && password.equals(users.get(i).getPassword())){
				return true;
			}
		}
		return false;
	}
}
