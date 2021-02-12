package com.vaadinshiro.ui;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("")
public class WelcomeView extends VerticalLayout {

	private HorizontalLayout horizontalLayout;
	private Button login;
	private Button register;
	private H2 welcomeMsg;

	public WelcomeView() {

		this.welcomeMsg = new H2("Welcome to Vaadin+Shiro World");
		this.horizontalLayout = new HorizontalLayout();
		this.login = new Button("Login");
		this.register = new Button("Register");

		buildLayout();
	}

	private void buildLayout() {
		add(welcomeMsg);
		horizontalLayout.add(login, register);
		login.addClickListener(in -> UI.getCurrent().navigate("login"));
		add(horizontalLayout);
	}
}
