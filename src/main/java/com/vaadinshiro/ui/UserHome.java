package com.vaadinshiro.ui;

import org.apache.shiro.SecurityUtils;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadinshiro.shiro.ShiroConfig;

@Route("userhome")
public class UserHome extends VerticalLayout{
	
	private H2 h2;
	private H3 h3;
	private Button buttonLogout;
	
	
	public UserHome(ShiroConfig shiroConfig) {
		this.h2 = new H2("Welcome to Vaadin+Shiro World");
		this.h3 = new H3();
		this.buttonLogout = new Button("Logout");
		addLogoutButton();
		buildParagramWithUserName();
	}

	private void addLogoutButton() {
		add(buttonLogout, h2);
		buttonLogout.addClickListener( out -> {
			SecurityUtils.getSubject().logout();
			UI.getCurrent().navigate("login");
		});
	}

	private void buildParagramWithUserName() {
		h3.add("Hello "+ (String) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal());
		add(h3);
		
	}

}
