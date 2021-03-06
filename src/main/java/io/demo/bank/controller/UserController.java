package io.demo.bank.controller;

import java.security.Principal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import io.demo.bank.model.UserProfile;
import io.demo.bank.model.security.Users;
import io.demo.bank.service.UserService;
import io.demo.bank.util.Constants;

@Controller
@RequestMapping(Constants.URI_USER)
public class UserController extends CommonController {
	
	// Class Logger
	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
		  
	@Autowired
	private UserService userService;
		

	@GetMapping(Constants.URI_USR_PASSWORD)
	public String password(Principal principal, Model model) {
    
		// Set Display Defaults
		setDisplayDefaults(principal, model);
		    
		return Constants.VIEW_USR_PASSWORD;
	}
	
	@PostMapping(Constants.URI_USR_PASSWORD)
	public String password(Principal principal, Model model,
						   @ModelAttribute(MODEL_ATT_NEW_PASS) String newPassword,
						   @ModelAttribute(MODEL_ATT_CUR_PASS) String oldPassword) {
    
		// Set Display Defaults
		setDisplayDefaults(principal, model);
				
		Users user = userService.findByUsername(principal.getName());
		
		LOG.debug("Change Password: Validate Password Entries.");
		
		// Validate Password entries
		if (userService.passwordMatches(user, oldPassword)) {
			
			LOG.debug("Change Password: Current Password is correct.");
			
			if (newPassword.equals(oldPassword)) {
				
				// new password matches current password, throw error
				
				LOG.debug("Change Password: New Password is the same as the current password.");
				
				model.addAttribute(MODEL_ATT_ERROR_MSG, "New Password is the same as the current password. Please enter a new Password.");
				
			} else {
				
				// change password
				LOG.debug("Change Password: New Password is a actualy a new password. Update Password.");
				
				userService.changePassword(user, newPassword);
				model.addAttribute(MODEL_ATT_SUCCESS_MSG, "Password Updated Successfully.");
				
			}
			
		} else {
			
			LOG.debug("Change Password: Current Password is NOT correct.");
			
			// old password not correct, throw error
			model.addAttribute(MODEL_ATT_ERROR_MSG, "Current Password does not match what is on record. Please enter the correct current password.");
			
		}
		    
		return Constants.VIEW_USR_PASSWORD;
	}
	
	@GetMapping(Constants.URI_USR_PROFILE)
	public String profile(Principal principal, Model model) {
    
		
		// Set Display Defaults
		setDisplayDefaults(principal, model);
				
		Users user = userService.findByUsername(principal.getName());
		
		model.addAttribute(MODEL_ATT_USER_PROFILE, user.getUserProfile());
		    
		return Constants.VIEW_USR_PROFILE;
	}
	
	@PostMapping(Constants.URI_USR_PROFILE)
	public String profile(Principal principal, Model model,
			 			  @ModelAttribute(MODEL_ATT_USER_PROFILE) UserProfile updateProfile) {
    
		// Set Display Defaults
		setDisplayDefaults(principal, model);
				
		Users user = userService.findByUsername(principal.getName());
		
		UserProfile up = user.getUserProfile();
		
		
		LOG.debug("Update User Profile: " + updateProfile.toString());
		
		// Set updated fields into actual user profile
		up.setTitle(updateProfile.getTitle());
		up.setFirstName(updateProfile.getFirstName());
		up.setLastName(updateProfile.getLastName());
		up.setHomePhone(updateProfile.getHomePhone());
		up.setMobilePhone(updateProfile.getMobilePhone());
		up.setWorkPhone(updateProfile.getWorkPhone());
		up.setAddress(updateProfile.getAddress());
		up.setLocality(updateProfile.getLocality());
		up.setRegion(updateProfile.getRegion());
		up.setPostalCode(updateProfile.getPostalCode());
		up.setCountry(updateProfile.getCountry());
		
		
		// Save Profile Updates
		user.setUserProfile(up);
		userService.save(user);
		
		LOG.debug("Updated User Profile: " + user.getUserProfile().toString());
		
		
		model.addAttribute(MODEL_ATT_USER_PROFILE, user.getUserProfile());
		model.addAttribute(MODEL_ATT_SUCCESS_MSG, "Profile Updated Successfully.");
		    
		return Constants.VIEW_USR_PROFILE;
	}
}
