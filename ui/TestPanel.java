package ceng.ner.ui;

import java.util.ArrayList;

import java.util.List;

import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

import ceng.ner.STYLE;
import ceng.ner.Sentence;
import ceng.ner.DoubleString;

public class TestPanel extends Panel {
	public TestPanel() {
		build();
	}

	private void build() {
		addStyleNames(STYLE.BACKGROUND_METU.getStyleName(), STYLE.TEXT_ALIGN_CENTER.getStyleName(),
				STYLE.BACKGROUND_LIGHT_GRAY.getStyleName());
		setHeight(100, Unit.PERCENTAGE);
		final TextArea taInput = new TextArea();
		taInput.setPlaceholder("Please enter a sentence...");
		taInput.setWidth(650, Unit.PIXELS);

		List<String> intentList = new ArrayList<String>();
		intentList.add("PFM");
		intentList.add("Money Transfer");
		intentList.add("Bill Payment");

		final ComboBox<String> cbIntent = new ComboBox<String>();
		cbIntent.addStyleName(STYLE.FONT_SIZE_13PX.getStyleName());
		cbIntent.setItems(intentList);
		cbIntent.setEmptySelectionAllowed(false);

		final VerticalLayout rightLayout = new VerticalLayout();
		rightLayout.setWidth(660, Unit.PIXELS);
		rightLayout.setHeight(100, Unit.PERCENTAGE);
		rightLayout.addStyleNames(STYLE.PADDING_0PX.getStyleName(), STYLE.OVERFLOW_X_HIDDEN.getStyleName(),
				STYLE.OVERFLOW_Y_AUTO.getStyleName(), STYLE.SLOT_WIDTH_AUTO.getStyleName());
		Label lblHistory = new Label("<b><u>" + "History" + "</b></u>", ContentMode.HTML);
		lblHistory.addStyleName(STYLE.SLOT_HEIGHT_AUTO.getStyleName());
		rightLayout.addComponent(lblHistory);
		rightLayout.setSpacing(false);

		VerticalLayout leftLayout = new VerticalLayout();
		leftLayout.setWidth(660, Unit.PIXELS);
		leftLayout.addStyleNames(STYLE.PADDING_0PX.getStyleName(), STYLE.PADDING_RIGHT_10PX.getStyleName(),
				STYLE.BORDER_RIGHT.getStyleName(), STYLE.SLOT_WIDTH_AUTO.getStyleName());

		final VerticalLayout resultLayout = new VerticalLayout();
		resultLayout.setHeight(400, Unit.PIXELS);
		resultLayout.setSpacing(false);
		resultLayout.addStyleNames(STYLE.PADDING_0PX.getStyleName(), STYLE.OVERFLOW_Y_AUTO.getStyleName(),
				STYLE.OVERFLOW_X_HIDDEN.getStyleName());
		Label lblResult = new Label("<b><u>" + "Results" + "</b></u>", ContentMode.HTML);
		lblResult.addStyleName(STYLE.SLOT_HEIGHT_AUTO.getStyleName());
		resultLayout.addComponent(lblResult);

		Button btnFindEntities = new Button("Find Entities");
		btnFindEntities.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {

				if (taInput.getValue().isEmpty()) {
					Notification.show("Please write a sentence and choose an intent.");
				} 
				else if(cbIntent.getValue() == null)
				{
					DoubleString ds = new DoubleString(taInput.getValue());
					String str2 = ds.Doubler();
					Label lbl2 = new Label(str2);
					rightLayout.addComponent(lbl2);
							/*
					String str = taInput.getValue();
					Label lbl = new Label(str);
					rightLayout.addComponent(lbl);
					*/
				}
				else {
					resultLayout.removeAllComponents();
					Sentence sentence = new Sentence(taInput.getValue(), cbIntent.getValue());
					for (String string : sentence.findEntities()) {
						Label lblWord = new Label(string);
						lblWord.addStyleName(STYLE.SLOT_HEIGHT_AUTO.getStyleName());
						lblWord.setHeight(30, Unit.PIXELS);
						resultLayout.addComponent(lblWord);
					}
					Label lblSentence = new Label("<b>" + ">" + "</b>" + sentence.getSentence(), ContentMode.HTML);
					lblSentence.setWidth(100, Unit.PERCENTAGE);
					lblSentence.addStyleName(STYLE.SLOT_HEIGHT_AUTO.getStyleName());

					rightLayout.addComponent(lblSentence);
					MyUI.getSentenceHistory().add(sentence);
				}

			}
		});

		HorizontalLayout buttonsLayout = new HorizontalLayout();
		buttonsLayout.addStyleNames(STYLE.FONT_SIZE_13PX.getStyleName(), STYLE.FLOAT_RIGHT.getStyleName());

		HorizontalLayout intentLayout = new HorizontalLayout();
		intentLayout.setWidth(100, Unit.PERCENTAGE);
		intentLayout.addStyleName(STYLE.PADDING_0PX.getStyleName());
		intentLayout.addComponents(cbIntent, buttonsLayout);

		leftLayout.addComponents(taInput, intentLayout, resultLayout);

		btnFindEntities.addStyleName(ValoTheme.BUTTON_PRIMARY);

		Button btnClear = new Button("Clear History");
		btnClear.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				MyUI.getSentenceHistory().clear();
				resultLayout.removeAllComponents();
				resultLayout.addComponent(lblResult);
				rightLayout.removeAllComponents();
				rightLayout.addComponent(lblHistory);
			}
		});
		btnClear.addStyleName(ValoTheme.BUTTON_DANGER);

		Button btnContact = new Button("Contact Us");
		btnContact.addStyleName(ValoTheme.BUTTON_FRIENDLY);
		btnContact.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				String message = "If you have a problem or question and you want to contact us, you can send "
						+ "an email to turkishner@metu.edu.tr .";
				Label lblContact = new Label(message);

				VerticalLayout contactLayout = new VerticalLayout();
				contactLayout.addComponent(lblContact);

				Window window = new Window("Contact");
				window.setContent(contactLayout);
				window.setModal(true);
				window.setDraggable(false);
				window.setResizable(false);
				window.center();

				getUI().addWindow(window);
			}
		});

		buttonsLayout.addComponents(btnContact, btnFindEntities, btnClear);

		HorizontalLayout topLayout = new HorizontalLayout();
		topLayout.setWidth(100, Unit.PERCENTAGE);
		topLayout.addComponents(leftLayout, rightLayout);

		VerticalLayout contentLayout = new VerticalLayout();
		contentLayout.addStyleName(STYLE.BACKGROUND_INHERIT.getStyleName());
		contentLayout.addComponents(topLayout);
		contentLayout.setSizeFull();

		this.setContent(contentLayout);
	}

}
