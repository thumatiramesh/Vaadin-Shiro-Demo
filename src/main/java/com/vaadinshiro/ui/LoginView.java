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
	private H2 h2;
	private TextField userName;
	private PasswordField password;
	private Button submit;
	private Label label;
	
    public LoginView() {
    	this.label = new Label("Login");
    	this.h2 = new H2("Welcome to Vaadin+Shiro World");
    	this.layout = new VerticalLayout();
    	this.userName = new TextField("Username");
    	this.password = new PasswordField("Password");
    	this.submit = new Button("Login");
    	
    	buildLayout();
    }

	private void buildLayout() {
		
		layout.add(label, userName, password, submit);
		add(layout);
		
		submit.addClickListener(click -> {
			shiroConfig.doAuthenticateAndProcess(userName.getValue(), password.getValue());
		});
		
	}

}
