package io.demo.bank.config;

import java.text.SimpleDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import io.demo.bank.model.UserProfile;
import io.demo.bank.model.security.Users;
import io.demo.bank.service.UserService;



@Component
public class SampleData implements CommandLineRunner, Ordered {

	private static final Logger LOG = LoggerFactory.getLogger(SampleData.class);
	
	@Autowired
	private UserService userService;
	
	@Override
	public int getOrder() {
		return 2;
	}

	@Override
	public void run(String... args) throws Exception {
		
		LOG.info("*********************************");
		LOG.info("***** Checking Sample Data ******");
		
		// If the sample user data does not exist, then create it.
		if (!userService.checkEmailAdressExists("jsmith@demo.io") && !userService.checkSsnExists("123-45-6789")) {
			
			LOG.info("** Loading Sample User...");
			
			Users user = new Users("jsmith@demo.io", "Demo123!");
			UserProfile userProfile = new UserProfile();
			SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD");
			
			userProfile.setEmailAddress("jsmith@demo.io");
			userProfile.setFirstName("Josh");
			userProfile.setLastName("Smith");
			userProfile.setTitle("Mr.");
			userProfile.setGender("M");
			userProfile.setDob(dateFormat.parse("1985-02-15"));
			userProfile.setSsn("123-45-6789");
			userProfile.setAddress("123 Digital Lane");
			userProfile.setCountry("United States");
			userProfile.setLocality("Internet City");
			userProfile.setPostalCode("94302");
			userProfile.setRegion("CA");
			userProfile.setHomePhone("123-456-7890");
			userProfile.setMobilePhone("123-456-7890");
			userProfile.setWorkPhone("123-456-7890");
			
			user.setUserProfile(userProfile);
			userService.createUser(user);
			
			LOG.info("** Username: jsmith@demo.io");
			LOG.info("** Password: Demo123!");
			
			// Create a second user for testing
			user = new Users("nsmith@demo.io", "Demo123!");
			userProfile = new UserProfile();
			
			userProfile.setEmailAddress("nsmith@demo.io");
			userProfile.setFirstName("Nicole");
			userProfile.setLastName("Smith");
			userProfile.setTitle("Mrs.");
			userProfile.setGender("F");
			userProfile.setDob(dateFormat.parse("1985-02-15"));
			userProfile.setSsn("123-45-6710");
			userProfile.setAddress("456 Digital Lane");
			userProfile.setCountry("United States");
			userProfile.setLocality("Internet City");
			userProfile.setPostalCode("94302");
			userProfile.setRegion("CA");
			userProfile.setHomePhone("123-456-7810");
			userProfile.setMobilePhone("123-456-7810");
			userProfile.setWorkPhone("123-456-7810");
			
			user.setUserProfile(userProfile);
			userService.createUser(user);
			
		}
		
		LOG.info("*********************************");
	}

}
