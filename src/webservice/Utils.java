package webservice;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;


public class Utils {
	private static DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
	
	public static void updateEntity(Entity e){
		ds.put(e);
	}
	
	public static UserPojo getEntity(Key id){
		
		UserPojo up = new UserPojo();
		try {
			Entity e = ds.get(id);
			up.setUserid(e.getProperty("Userid").toString());
			up.setUsername(e.getProperty("Username").toString());
			up.setPassword(e.getProperty("Password").toString());
			up.setEmail(e.getProperty("Email").toString());
			up.setAge(e.getProperty("Age").toString());
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return up;
		
	}
	
}
