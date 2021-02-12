package com.vaadinshiro.ui;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("error")
public class ErrorView extends VerticalLayout {

	private H1 h1;
	private Button retry;
	private HorizontalLayout horizontalLayout;

	public ErrorView() {
		this.horizontalLayout = new HorizontalLayout();
		this.retry = new Button("Retry");
		this.h1 = new H1("Sorry, User not Authenticated!");

		horizontalLayout.add(h1, retry);
		
		add(horizontalLayout);

		retry.addClickListener(retry -> UI.getCurrent().navigate("login"));
	}
}
