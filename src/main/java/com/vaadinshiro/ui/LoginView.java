package com.vaadinshiro.ui;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadinshiro.shiro.ShiroConfig;

@Route("login")
public class LoginView extends VerticalLayout {

	@Autowired
	private ShiroConfig shiroConfig;
	
	private VerticalLayout layout;
	private H2 welcome;
	private H2 login;
	private TextField userName;
	private PasswordField password;
	private Button submit;
	
    public LoginView() {
    	this.login = new H2("Login");
    	this.welcome = new H2("Welcome to Vaadin+Shiro World");
    	this.layout = new VerticalLayout();
    	this.userName = new TextField("Username");
    	this.password = new PasswordField("Password");
    	this.submit = new Button("Submit");
    	
    	buildLayout();
    }

	private void buildLayout() {
		layout.add(welcome, login, userName, password, submit);
		add(layout);
		
		submit.addClickListener(click -> {
			shiroConfig.doAuthenticateAndProcess(userName.getValue(), password.getValue());
		});
		
	}

}
