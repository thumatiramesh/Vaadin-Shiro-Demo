package com.vaadinshiro.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.springframework.context.annotation.Configuration;

import com.vaadin.flow.component.UI;

@Configuration
public class ShiroConfig {

	public void doAuthenticateAndProcess(String userName, String password) {

		IniRealm iniRealm = new IniRealm("classpath:shiro.ini");
		SecurityManager securityManager = new DefaultSecurityManager(iniRealm);

		SecurityUtils.setSecurityManager(securityManager);
		Subject currentUser = SecurityUtils.getSubject();

		if (!currentUser.isAuthenticated()) {
			UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
			token.setRememberMe(true);
			try {
				
				currentUser.login(token);
				UI.getCurrent().navigate("userhome");
				
			} catch (UnknownAccountException uae) {
				UI.getCurrent().navigate("error");
			} catch (IncorrectCredentialsException ice) {
				UI.getCurrent().navigate("error");
			} catch (LockedAccountException lae) {
				UI.getCurrent().navigate("error");
			} catch (AuthenticationException ae) {
				UI.getCurrent().navigate("error");
			}
		}
		
	}
	
	public Subject getCurrentUser() {
		return SecurityUtils.getSubject();
	}
}
