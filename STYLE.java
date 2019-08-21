package ceng.ner;

public enum STYLE {
	//Display
	DISPLAY_NONE("display_none"),
	DISPLAY_TRANSPARENT("display-transparent"),
	DISPLAY_LIST_ITEM("display_list_item"),
	DISPLAY_FLEX("display_flex"),
	Z_INDEX_0("z_index_0"),
	Z_INDEX_1("z_index_1"),
	Z_INDEX_10("z_index_10"),
	Z_INDEX_100("z_index_100"),
	//Overflow
	OVERFLOW_X_AUTO("overflow_x_auto"),
	OVERFLOW_X_HIDDEN("overflow_x_hidden"),
	OVERFLOW_Y_AUTO("overflow_y_auto"),
	OVERFLOW_Y_HIDDEN("overflow_y_hidden"),
	TEXT_OVERFLOW_ELLIPSIS("text_overflow_ellipsis"),
	//Bottom
	BOTTOM_5PX("bottom5px"),
	BOTTOM_10PX("bottom10px"),
	BOTTOM_15PX("bottom15px"),
	//Paddings
	FORCE_PADDING_0PX("force_padding_0px"),
	FORCE_PADDING_5PX("force_padding_5px"),
	PADDING_0PX("padding_0px"),
	PADDING_5PX("padding_5px"),
	PADDING_10PX("padding_10px"),
	PADDING_15PX("padding_15px"),

	PADDING_TOP_0PX("padding_top_0px"),
	PADDING_TOP_5PX("padding_top_5px"),
	PADDING_TOP_10PX("padding_top_10px"),
	PADDING_TOP_15PX("padding_top_15px"),

	PADDING_BOTTOM_0PX("padding_bottom_0px"),
	PADDING_BOTTOM_5PX("padding_bottom_5px"),
	PADDING_BOTTOM_10PX("padding_bottom_10px"),
	PADDING_BOTTOM_15PX("padding_bottom_15px"),
	PADDING_BOTTOM_25PX("padding_bottom_25px"),

	PADDING_LEFT_0PX("padding_left_0px"),
	PADDING_LEFT_5PX("padding_left_5px"),
	PADDING_LEFT_10PX("padding_left_10px"),
	PADDING_LEFT_15PX("padding_left_15px"),

	PADDING_RIGHT_0PX("padding_right_0px"),
	PADDING_RIGHT_5PX("padding_right_5px"),
	PADDING_RIGHT_10PX("padding_right_10px"),
	PADDING_RIGHT_15PX("padding_right_15px"),
	//Margins
	MARGIN_0PX("margin0px"),
	MARGIN_5PX("margin5px"),
	MARGIN_10PX("margin10px"),
	MARGIN_15PX("margin15px"),

	MARGIN_TOP_MINUS_3PX("margin_top_minus_3px"),
	MARGIN_TOP_MINUS_5PX("margin_top_minus_5px"),
	MARGIN_TOP_MINUS_10PX("margin_top_minus_10px"),
	MARGIN_TOP_MINUS_15PX("margin_top_minus_15px"),
	MARGIN_TOP_MINUS_20PX("margin_top_minus_20px"),

	MARGIN_TOP_3PX("margin_top_3px"),
	MARGIN_TOP_5PX("margin_top_5px"),
	MARGIN_TOP_10PX("margin_top_10px"),
	MARGIN_TOP_15PX("margin_top_15px"),
	MARGIN_TOP_20PX("margin_top_20px"),
	MARGIN_TOP_25PX("margin_top_25px"),
	
	MARGIN_BOTTOM_5PX("margin_bottom_5px"),
	MARGIN_BOTTOM_10PX("margin_bottom_10px"),
	MARGIN_BOTTOM_15PX("margin_bottom_15px"),
	MARGIN_BOTTOM_25PX("margin_bottom_25px"),
	
	MARGIN_LEFT_5PX("margin_left_5px"),
	MARGIN_LEFT_10PX("margin_left_10px"),
	MARGIN_LEFT_15PX("margin_left_15px"),
	MARGIN_LEFT_20PX("margin_left_20px"),
	
	MARGIN_LEFT_MINUS_10PX("margin_left_minus_10px"),
	
	MARGIN_RIGHT_5PX("margin_right_5px"),
	MARGIN_RIGHT_10PX("margin_right_10px"),
	MARGIN_RIGHT_15PX("margin_right_15px"),
	
	MARGIN_RIGHT_MINUS_5PX("margin_right_minus_5px"),
	
	ICON_MARGIN_LEFT_MINUS_4PX("icon_margin_left_minus_4px"),
	
	//Border
	BORDER_RIGHT("border_right"),
	BORDER_NONE("border_none"),
	BORDER_SOFT("border_soft"),
	BORDER_COLOR_PRIMARY("border-colorprimary"),
	BORDER_COLOR_RED("border-color-red"),
	BORDER_COLOR_GREEN("border-color-green"),
	BORDER_RADIUS_OPX("border_radius_0px"),
	BORDER_RADIUS_4PX("border_radius_4px"),
	BORDER_RADIUS_10PX("border_radius_10px"),
	BORDER_2PX("border_2px"),
	//Float
	FLOAT_LEFT("float_left"),
	FLOAT_RIGHT("float_right"),
	SLOT_FLOAT_RIGHT("slot_float_right"),
	//Button
	BUTTON_ROUNDED("btn-rounded"),
	BUTTON_COLORED("btn-pspulsecolor"),
	BUTTON_NO_BORDER("btn_noborder"),
	BUTTON_NO_SHADOW_IN_ACTION("btn-noshadowaction"),
	//Position
	POSITION_FIXED("position_fixed"),
	//Text
	TEXT_ALIGN_CENTER("text_align_center"),
	TEXT_ALIGN_RIGHT("text_align_right"),
	TEXT_ALIGN_LEFT("text_align_left"),
	TEXT_ALIGN_UNSET("text_align_unset"),
	TEXT_DECORATION_UNDERLINE("text_decoration_underline"),
	TEXT_OVERFLOW_INHERIT("text_overflow_inherit"),
	//Font
	FONT_BOLD("font_weight_bold"),
	FONT_STYLE_ITALIC("font_style_italic"),
	FONT_SIZE_13PX("font_size_13px"),
	FONT_SIZE_11PX("font_size_11px"),
	FORCE_FONT_SIZE_15PX("force_font_size_15px"),
	FONT_SIZE_20PX("font_size_20px"),
	FONT_SIZE_23PX("font_size_23px"),
	FONT_SIZE_30PX("font_size_30px"),
	//Vertical Align
	VERTICAL_ALIGN_MIDDLE("vertical_align_middle"),
	VERTICAL_ALIGN_BASELINE("vertical_align_baseline"),
	VERTICAL_ALIGN_SUB("vertical_align_sub"),
	//Color
	COLOR_DARK_BLUE("color_dark_blue"),
	COLOR_BLUE("color_blue"),
	COLOR_CORAL("color_coral"),
	COLOR_TAN("color_tan"),
	COLOR_SALMON("calor_salmon"),
	COLOR_CADET_BLUE("color_cadet_blue"),
	COLOR_WHITE("color_white"),
	COLOR_GRAY("color_gray"),
	COLOR_GREEN("color_green"),
	COLOR_YELLOW("color_yellow"),
	COLOR_RED("color_red"),
	COLOR_PURPLE("color_purple"),
	COLOR_BROWN("color_brown"),
	COLOR_PULSE_PRIMARY("color_pulse_primary"),
	COLOR_PULSE_SECONDARY("color_pulse_secondary"),
	COLOR_PULSE_RED("color_pulse_red"),
	BACKGROUND_COLOR_PULSE_PRIMARY("color_pulse_theme"),
	BACKGROUND_COLOR_PULSE_SECONDARY("color_pulse_theme_secondary"),
	BACKGROUND_COLOR_PULSE_TERTIARY("background_pulse_theme_tertiary"),
	BACKGROUND_COLOR_PULSE_QUATERNARY("background_pulse_theme_quaternary"),
	COLOR_PULSE_GREEN("color_pulse_green"),
	BACKGROUND_WHITE("background_white"),
	BACKGROUND_GREEN("background_green"),
	BACKGROUND_RED("background_red"),
	BACKGROUND_YELLOW("background_yellow"),
	BACKGROUND_BROWN("background_brown"),
	BACKGROUND_PURPLE("background_purple"),
	BACKGROUND_BLUE("background_blue"),
	BACKGROUND_GRAY("background_gray"),
	BACKGROUND_CORAL("background_coral"),
	BACKGROUND_TAN("background_tan"),
	BACKGROUND_CADET_BLUE("background_cadet_blue"),
	BACKGROUND_SALMON("background_salmon"),
	BACKGROUND_LIGHT_GREEN("background_light_green"),
	BACKGROUND_DARK_CYAN("background_dark_cyan"),
	BACKGROUND_INHERIT("background_inherit"),
	BACKGROUND_LIGHT_GRAY("background_light_gray"),
	//Whitespace
	WHITE_SPACE_NORMAL("white_space_normal"),
	WHITE_SPACE_NOWRAP("white_space_nowrap"),
	WHITE_SPACE_PRE("white_space_pre"),
	WHITE_SPACE_PRE_LINE("white_space_pre_line"),
	//Spacing
	SPACING_HEIGHT_0PX("spacing_height_0px"),
	SPACING_HEIGHT_5PX("spacing_height_5px"),
	FORCE_SPACING_HEIGHT_5PX("force_spacing_height_5px"),
	FORCE_SPACING_WIDTH_0PX("force_spacing_width_0px"),
	SPACING_WIDTH_0PX("spacing_width_0px"),
	SPACING_WIDTH_5PX("spacing_width_5px"),
	SLOT_HEIGHT_AUTO("slot_height_auto"),
	//Height
	HEIGHT_100_PERCENTAGE("height_100_percentage"),
	MAX_HEIGHT_100_PERCENTAGE("max_height_100_percentage"),
	FORCE_HEIGHT_100_PERCENTAGE("force_height_100_percentage"),
	HEIGHT_AUTO("height_auto"),
	LINE_HEIGHT_1_2("line_height_1_2"),
	//Width
	SLOT_WIDTH_AUTO("width_auto"),
	WIDTH_100_PERCENTAGE("width_100_percentage"),
	WIDTH_AUTO("auto_width"),
	//Panel
	PANEL_CAPTION_RED("panel_caption_red"),
	PANEL_CAPTION_BLUE("panel_caption_blue"),
	PANEL_CAPTION_GREEN("panel_caption_green"),
	PANEL_CAPTION_YELLOW("panel_caption_yellow"),
	PANEL_CAPTION_PURPLE("panel_caption_purple"),
	PANEL_CAPTION_BROWN("panel_caption_brown"),
	//Cursor
	CURSOR_GRAB("cursor_grab"),
	CURSOR_GRABBING("cursor_grabbing"),
	BACKGROUND_METU("background-image-metu");
	private final String styleName;

	STYLE(String styleName) {

		this.styleName = styleName;
	}

	public String getStyleName() {

		return styleName;
	}

}
