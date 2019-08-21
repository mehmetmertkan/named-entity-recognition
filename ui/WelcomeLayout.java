package ceng.ner.ui;

import com.vaadin.server.Sizeable.Unit;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import ceng.ner.STYLE;

public abstract class WelcomeLayout extends Panel {

	public WelcomeLayout() {
		String message = "This website belongs to METU Senior Project 'Turkish Named Entity Recognition.'";

		String message1 = " Group Members are;\n" + "Kerem DOGANYIGIT, \n" + "Fatima MAHMOUD, \n"
				+ "Mehmet Mert KAN, \n" + "Ugurcan SAHIN.";
		Label lblInfo = new Label("<b>" + message + "</b>", ContentMode.HTML);
		lblInfo.addStyleNames(STYLE.WHITE_SPACE_PRE_LINE.getStyleName(), STYLE.COLOR_WHITE.getStyleName());
		lblInfo.setWidth(100, Unit.PERCENTAGE);

		Label lblInfo1 = new Label("<b>" + message1 + "</b>", ContentMode.HTML);
		lblInfo1.addStyleNames(STYLE.WHITE_SPACE_PRE_LINE.getStyleName(), STYLE.COLOR_WHITE.getStyleName());
		lblInfo1.setWidth(100, Unit.PERCENTAGE);

		Button btnContinue = new Button("Continue To Project");
		btnContinue.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				continueToProject();
			}
		});
		btnContinue.addStyleName(ValoTheme.BUTTON_DANGER);

		VerticalLayout layout = new VerticalLayout();
		layout.setWidth(400, Unit.PIXELS);
		layout.addStyleNames(STYLE.BORDER_SOFT.getStyleName(), STYLE.TEXT_ALIGN_CENTER.getStyleName(),
				STYLE.BACKGROUND_COLOR_PULSE_PRIMARY.getStyleName());
		layout.addComponents(lblInfo, lblInfo1, btnContinue);
		layout.setComponentAlignment(btnContinue, Alignment.MIDDLE_CENTER);

		VerticalLayout containerLayout = new VerticalLayout();
		containerLayout.addStyleName(STYLE.TEXT_ALIGN_CENTER.getStyleName());
		containerLayout.setWidth(100, Unit.PERCENTAGE);
		containerLayout.setHeight(100, Unit.PERCENTAGE);
		containerLayout.addComponent(layout);

		setHeight(100, Unit.PERCENTAGE);
		setWidth(100, Unit.PERCENTAGE);
		addStyleName(STYLE.BACKGROUND_METU.getStyleName());
		setContent(containerLayout);
	}

	public abstract void continueToProject();

}
