package webservice;


import javax.jws.WebMethod;
import javax.jws.WebService;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@WebService
public class  WebservicesDemo {

	@WebMethod
	public boolean createUser(String userid, String username, String password, String email, String age){
		
		Entity Employee =  new Entity("Employee",userid);
		Employee.setProperty("Userid"  , userid);
		Employee.setProperty("Username", username);
		Employee.setProperty("Password", password);
		Employee.setProperty("Email"   , email);
		Employee.setProperty("Age"     , age);
		System.out.println("Userid: "+userid+" Username: "+username+" Password: "+password+" Email: "+email+" Age: "+age);
		Utils.updateEntity(Employee);
		
		
		return true;
	}
	
	@WebMethod
	public UserPojo getUser(String name){
		Key Key = KeyFactory.createKey("Employee", name);
		UserPojo up = Utils.getEntity(Key);
		return up;
	}
}
