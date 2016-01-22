package webservice;

import webservice.jaxws.CreateUser;
import webservice.jaxws.CreateUserResponse;
import webservice.jaxws.GetUser;
import webservice.jaxws.GetUserResponse;

public class CreateUserAdapter {
WebservicesDemo ws= new WebservicesDemo();
public CreateUserResponse createUser(CreateUser request){
	
	String userid   = request.getArg0();
	String username = request.getArg1();
	String password = request.getArg2();
	String email    = request.getArg3();
	String age      = request.getArg4();
	boolean b = ws.createUser(userid, username, password, email, age);
	CreateUserResponse resp = new CreateUserResponse();
	resp.setReturn(b);
	return resp;
}
public GetUserResponse getUser(GetUser request){
	String king = request.getArg0();
	UserPojo up = ws.getUser(king);
	GetUserResponse resp = new GetUserResponse();
	resp.setReturn(up);
	return resp;
	
	
}
}
