package ceng.ner.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import ceng.ner.Sentence;
import zemberek.morphology.analysis.tr.TurkishMorphology;

/**
 * This UI is the application entry point. A UI may either represent a browser
 * window (or tab) or some part of an HTML page where a Vaadin application is
 * embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is
 * intended to be overridden to add component to the user interface and
 * initialize non-component functionality.
 */
@Theme("mytheme")
@SpringUI
public class MyUI extends UI {
	private static List<Sentence> sentenceHistory;
	public static TurkishMorphology morphology;

	@Override
	protected void init(VaadinRequest request) {

		setHeight(100, Unit.PERCENTAGE);
		try {
			morphology = TurkishMorphology.createWithDefaults();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sentenceHistory = new ArrayList<Sentence>();
		setHeight(100, Unit.PERCENTAGE);
		/*
		 * TabSheet tabsheet = new TabSheet(); tabsheet.setSizeFull();
		 * tabsheet.addStyleName("tbsht-project"); tabsheet.addTab(new
		 * TestPanel(), "Test"); /*tabsheet.addTab(new MemberPanel(),
		 * "Members"); tabsheet.addTab(new ProjectPanel(), "About Project");
		 */
		// setContent(tabsheet);

		WelcomeLayout welcomeLayout = new WelcomeLayout() {

			@Override
			public void continueToProject() {
				setContent(new TestPanel());
			}
		};

		VerticalLayout viewContainer = new VerticalLayout();
		viewContainer.setHeight(100, Unit.PERCENTAGE);
		viewContainer.setWidth(100, Unit.PERCENTAGE);
		viewContainer.addComponent(welcomeLayout);

		setContent(welcomeLayout);
	}

	public static List<Sentence> getSentenceHistory() {
		return sentenceHistory;
	}

	public static TurkishMorphology getMorphology() {
		return morphology;
	}
}