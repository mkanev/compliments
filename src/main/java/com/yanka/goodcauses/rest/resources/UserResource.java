package com.yanka.goodcauses.rest.resources;

import com.yanka.goodcauses.rest.TokenUtils;
import com.yanka.goodcauses.service.UserManager;
import com.yanka.goodcauses.transfer.UserTransfer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Component
@Path("/user")
public class UserResource {

    @Autowired
    private UserManager userManager;

    @Autowired
    @Qualifier("authenticationManager")
    private AuthenticationManager authManager;

    @Path("authenticate")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public UserTransfer authenticate(@FormParam("username") String username, @FormParam("password") String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = this.authManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Map<String, Boolean> roles = new HashMap<String, Boolean>();
                /*
                 * Reload user as password of authentication principal will be null after authorization and
		 * password is needed for token generation
		 */
        UserDetails userDetails = userManager.loadUserByUsername(username);
        for (GrantedAuthority authority : userDetails.getAuthorities()) {
            roles.put(authority.toString(), Boolean.TRUE);
        }
        return new UserTransfer(userDetails.getUsername(), roles, TokenUtils.createToken(userDetails));
    }

}
