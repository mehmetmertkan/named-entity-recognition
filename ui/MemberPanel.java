
/****************************************************************/////////
package ceng.ner.ui;

import java.io.File;

import com.vaadin.server.FileResource;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

import ceng.ner.STYLE;

public class MemberPanel extends Panel {

	public MemberPanel() {
		build();
	}

	private void build() {
		addStyleNames(STYLE.BACKGROUND_LIGHT_GRAY.getStyleName(), STYLE.BACKGROUND_METU.getStyleName());
		setSizeFull();
		setHeight(100, Unit.PERCENTAGE);
		HorizontalLayout membersLayout = buildMembersLayout();
		HorizontalLayout supervisorLayout = buildSupervisorLayout();
		/*
		 * Label lblLink = new Label(
		 * "<a href= https://www.linkedin.com/in/u%C4%9Furcan-%C5%9Fahin-a3a846147/> LinkedIn Profile</a>"
		 * , ContentMode.HTML);
		 */
		TabSheet tabsheet = new TabSheet();
		tabsheet.setSizeFull();
		tabsheet.addStyleName("tbsht-member");
		tabsheet.addTab(membersLayout, "Group Members");
		tabsheet.addTab(supervisorLayout, "Supervisors");
		setContent(tabsheet);
	}

	private HorizontalLayout buildMembersLayout() {

		VerticalLayout fatimaLayout = new VerticalLayout();
		fatimaLayout.setHeight(100, Unit.PERCENTAGE);
		fatimaLayout.addStyleName(STYLE.BORDER_RIGHT.getStyleName());

		FileResource fatimaResource = new FileResource(
				new File("C:/Users/ugurcan35/workspace/ner/src/main/resources/user.png"));
		Image fatimeImg = new Image("", fatimaResource);
		fatimeImg.setHeight(100, Unit.PIXELS);
		fatimeImg.setWidth(100, Unit.PIXELS);
		fatimeImg.addStyleName(STYLE.SLOT_HEIGHT_AUTO.getStyleName());
		fatimaLayout.addComponent(fatimeImg);

		VerticalLayout keremLayout = new VerticalLayout();
		keremLayout.setHeight(100, Unit.PERCENTAGE);
		keremLayout.addStyleName(STYLE.BORDER_RIGHT.getStyleName());

		FileResource keremResource = new FileResource(
				new File("C:/Users/ugurcan35/workspace/ner/src/main/resources/user.png"));
		Image keremImg = new Image("", keremResource);
		keremImg.addStyleName(STYLE.SLOT_HEIGHT_AUTO.getStyleName());
		keremImg.setHeight(100, Unit.PIXELS);
		keremImg.setWidth(100, Unit.PIXELS);
		keremLayout.addComponent(keremImg);

		VerticalLayout mertLayout = new VerticalLayout();
		mertLayout.setHeight(100, Unit.PERCENTAGE);
		mertLayout.addStyleName(STYLE.BORDER_RIGHT.getStyleName());

		FileResource mertResource = new FileResource(
				new File("C:/Users/ugurcan35/workspace/ner/src/main/resources/user.png"));
		Image mertImg = new Image("", mertResource);
		mertImg.addStyleName(STYLE.SLOT_HEIGHT_AUTO.getStyleName());
		mertImg.setHeight(100, Unit.PIXELS);
		mertImg.setWidth(100, Unit.PIXELS);
		mertLayout.addComponent(mertImg);

		VerticalLayout ugurcanLayout = new VerticalLayout();
		ugurcanLayout.setHeight(100, Unit.PERCENTAGE);

		FileResource ugurcanResource = new FileResource(
				new File("C:/Users/ugurcan35/workspace/ner/src/main/resources/user.png"));
		Image ugurcanImg = new Image("", ugurcanResource);
		ugurcanImg.addStyleName(STYLE.SLOT_HEIGHT_AUTO.getStyleName());
		ugurcanImg.setHeight(100, Unit.PIXELS);
		ugurcanImg.setWidth(100, Unit.PIXELS);
		ugurcanLayout.addComponent(ugurcanImg);

		HorizontalLayout membersLayout = new HorizontalLayout();
		membersLayout.setWidth(100, Unit.PERCENTAGE);
		membersLayout.setHeight(100, Unit.PERCENTAGE);
		membersLayout.addComponents(fatimaLayout, keremLayout, mertLayout, ugurcanLayout);

		for (int i = 0; i < membersLayout.getComponentCount(); i++) {

			Label lblNameTitle = new Label("Name:");
			lblNameTitle.addStyleName(STYLE.FONT_BOLD.getStyleName());
			Label lblNameContent = new Label("Fatima Ahmed Mahmoud");
			HorizontalLayout nameLayout = new HorizontalLayout();
			nameLayout.addStyleName(STYLE.SLOT_HEIGHT_AUTO.getStyleName());
			nameLayout.addComponents(lblNameTitle, lblNameContent);

			Label lblDepartmentTitle = new Label("Department:");
			lblDepartmentTitle.addStyleName(STYLE.FONT_BOLD.getStyleName());
			Label lblDepartmentContent = new Label("Computer Engineering");
			HorizontalLayout departmentLayout = new HorizontalLayout();
			departmentLayout.addStyleName(STYLE.SLOT_HEIGHT_AUTO.getStyleName());
			departmentLayout.addComponents(lblDepartmentTitle, lblDepartmentContent);

			Label lblGradeTitle = new Label("Year:");
			lblGradeTitle.addStyleName(STYLE.FONT_BOLD.getStyleName());
			Label lblGradeContent = new Label("4");
			HorizontalLayout gradeLayout = new HorizontalLayout();
			gradeLayout.addStyleName(STYLE.SLOT_HEIGHT_AUTO.getStyleName());
			gradeLayout.addComponents(lblGradeTitle, lblGradeContent);

	/*		Label lblThoughtsContent = new Label(
					"<b>Thoughts about the project:</b>"
							+ "This project gave me the opportunity to work with other people. I improved my Java skills and Web Application knowledge.",
					ContentMode.HTML);
			lblThoughtsContent.setWidth(100, Unit.PERCENTAGE);
			lblThoughtsContent.addStyleName(STYLE.WHITE_SPACE_PRE_LINE.getStyleName());
			HorizontalLayout thoughtsLayout = new HorizontalLayout();
			thoughtsLayout.setWidth(100, Unit.PERCENTAGE);
			thoughtsLayout.addStyleName(STYLE.SLOT_HEIGHT_AUTO.getStyleName());
			thoughtsLayout.addComponents(lblThoughtsContent);
*/
			membersLayout.getComponent(i).addStyleNames(STYLE.TEXT_ALIGN_CENTER.getStyleName(),
					STYLE.PADDING_10PX.getStyleName());
			((VerticalLayout) membersLayout.getComponent(i)).addComponents(nameLayout, departmentLayout, gradeLayout
					/*,thoughtsLayout*/);

		}

		return membersLayout;
	}

	private HorizontalLayout buildSupervisorLayout() {

		VerticalLayout aysenurLayout = new VerticalLayout();
		aysenurLayout.setHeight(100, Unit.PERCENTAGE);
		aysenurLayout.addStyleName(STYLE.BORDER_RIGHT.getStyleName());

		FileResource aysenurResource = new FileResource(
				new File("C:/Users/ugurcan35/workspace/ner/src/main/resources/user.png"));
		Image aysenurImg = new Image("", aysenurResource);
		aysenurImg.setHeight(100, Unit.PIXELS);
		aysenurImg.setWidth(100, Unit.PIXELS);
		aysenurImg.addStyleName(STYLE.SLOT_HEIGHT_AUTO.getStyleName());

		Label lblNameTitle = new Label("Name:");
		lblNameTitle.addStyleName(STYLE.FONT_BOLD.getStyleName());
		Label lblNameContent = new Label("Ayşenur Birtürk");
		HorizontalLayout nameLayout = new HorizontalLayout();
		nameLayout.addStyleName(STYLE.SLOT_HEIGHT_AUTO.getStyleName());
		nameLayout.addComponents(lblNameTitle, lblNameContent);

		aysenurLayout.addComponents(aysenurImg, nameLayout);

		VerticalLayout caglarLayout = new VerticalLayout();
		caglarLayout.setHeight(100, Unit.PERCENTAGE);
		caglarLayout.addStyleName(STYLE.BORDER_RIGHT.getStyleName());

		FileResource caglarResource = new FileResource(
				new File("C:/Users/ugurcan35/workspace/ner/src/main/resources/user.png"));
		Image caglarImg = new Image("", caglarResource);
		caglarImg.setHeight(100, Unit.PIXELS);
		caglarImg.setWidth(100, Unit.PIXELS);
		caglarImg.addStyleName(STYLE.SLOT_HEIGHT_AUTO.getStyleName());

		Label lblNameTitle1 = new Label("Name:");
		lblNameTitle1.addStyleName(STYLE.FONT_BOLD.getStyleName());
		Label lblNameContent1 = new Label("Çağlar Seylan");
		HorizontalLayout nameLayout1 = new HorizontalLayout();
		nameLayout1.addStyleName(STYLE.SLOT_HEIGHT_AUTO.getStyleName());
		nameLayout1.addComponents(lblNameTitle1, lblNameContent1);

		caglarLayout.addComponents(caglarImg, nameLayout1);

		HorizontalLayout supervisorLayout = new HorizontalLayout();
		supervisorLayout.setHeight(100, Unit.PERCENTAGE);
		supervisorLayout.setWidth(100, Unit.PERCENTAGE);
		supervisorLayout.addComponents(aysenurLayout, caglarLayout);

		return supervisorLayout;

	}
}