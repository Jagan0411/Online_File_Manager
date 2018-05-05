package hw6;

import java.util.ArrayList;

public class Users {

	String user_name;
	String pass;
	int user_id;
	ArrayList<File> user_files;
	
	public Users (int user_id,String user_name,String pass)
	{
		this.user_id = user_id;
		this.user_name = user_name;
		this.pass = pass;
		user_files = new ArrayList<File>();
	}

	public ArrayList<File> getUser_files() {
		return user_files;
	}

	public void setUser_files(ArrayList<File> user_files) {
		this.user_files = user_files;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
}
