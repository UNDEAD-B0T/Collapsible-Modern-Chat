package com.collapsiblemodernchat;

import net.runelite.client.config.*;

@ConfigGroup("collapsiblechat")
public interface collapsiblemodernchatconfig extends Config
{

	@ConfigSection(
			name = "Extra Settings",
			description = "Extra options for more customisation",
			position = 0,
			closedByDefault = false

	)
	String extraSettings = "extraSettings";

	@ConfigSection(
			name = "Entire Group Settings",
			description = "Group options for more customisation",
			position = 10,
			closedByDefault = true

	)
	String entiregroupSettings = "EntireGroupSettings";


	@ConfigSection(
			name = "Collapsible Icon Settings",
			description = "Configuration for collapsed icon settings",
			position = 20,
			closedByDefault = true

	)
	String collapsibleSettings = "collapsibleSettings";

	@ConfigSection(
			name = "Chat Bar Icon Settings",
			description = "Configuration for Chat Bar icon settings",
			position = 30,
			closedByDefault = true

	)
	String chatbarsettings = "chatbarsettings";

	@ConfigSection(
			name = "Chat Box Settings",
			description = "Configuration for Chat Box settings",
			position = 40,
			closedByDefault = true

	)
	String chatboxsettings = "chatboxsettings";


	enum MinimapChoices
	{
		Open,
		Closed,

	}

	enum DisplayIconChoices
	{
		ALL,
		GAME,
		PUBLIC,
		PRIVATE,
		CHANNEL,
		CLAN,
		TRADE,
		REPORT
	}
	enum DisplayIconChoicesVoid
	{
		ALL,
		GAME,
		PUBLIC,
		PRIVATE,
		CHANNEL,
		CLAN,
		TRADE,
		REPORT,
		VOID
	}



	@ConfigItem(
			keyName = "DisplayIconChoices",
			name = "Main Collapsible Icon",
			description = "Configures which chat to make main button",
			position = 3,
			section = collapsibleSettings
	)
	default DisplayIconChoices DisplayIconChoices()
	{
		return DisplayIconChoices.ALL;
	}
	@ConfigItem(
			keyName = "DisplayIconChoices2nd",
			name = "2nd Collapsible Icon",
			description = "Configures which chat to make 2nd button",
			position = 8,
			section = collapsibleSettings
	)
	default DisplayIconChoicesVoid DisplayIconChoices2nd() {return DisplayIconChoicesVoid.PRIVATE;}

	@ConfigItem(
			keyName = "DisplayIconChoices3rd",
			name = "3rd Collapsible Icon",
			description = "Configures which chat to make 3rd button",
			position = 11,
			section = collapsibleSettings
	)
	default DisplayIconChoicesVoid DisplayIconChoices3rd() {return DisplayIconChoicesVoid.VOID;}


	@ConfigItem(
			keyName = "DisplayIconChoices4th",
			name = "4th Collapsible Icon",
			description = "Configures which chat to make 4th button",
			position = 14,
			section = collapsibleSettings
	)
	default DisplayIconChoicesVoid DisplayIconChoices4th() {return DisplayIconChoicesVoid.VOID;}

	@ConfigItem(
			keyName = "DisplayIconChoices5th",
			name = "5th Collapsible Icon",
			description = "Configures which chat to make 5th button",
			position = 17,
			section = collapsibleSettings
	)
	default DisplayIconChoicesVoid DisplayIconChoices5th() {return DisplayIconChoicesVoid.VOID;}
	@ConfigItem(
			keyName = "DisplayIconChoices6th",
			name = "6th Collapsible Icon",
			description = "Configures which chat to make 6th button",
			position = 20,
			section = collapsibleSettings
	)
	default DisplayIconChoicesVoid DisplayIconChoices6th() {return DisplayIconChoicesVoid.VOID;}

	@ConfigItem(
			keyName = "DisplayIconChoices7th",
			name = "7th Collapsible Icon",
			description = "Configures which chat to make 7th button",
			position = 23,
			section = collapsibleSettings
	)
	default DisplayIconChoicesVoid DisplayIconChoices7th() {return DisplayIconChoicesVoid.VOID;}

	@ConfigItem(
			keyName = "DisplayIconChoices8th",
			name = "8th Collapsible Icon",
			description = "Configures which chat to make 8th button",
			position = 26,
			section = collapsibleSettings
	)
	default DisplayIconChoicesVoid DisplayIconChoices8th() {return DisplayIconChoicesVoid.VOID;}
/* 8
	@ConfigItem(
			keyName = "DisplayIconChoices3rd",
			name = "Collapsible Icon 3rd",
			description = "Configures which chat to make 3rd button",
			position = 3,
			section = collapsibleSettings
	)
	default DisplayIconChoicesVoid DisplayIconChoices3rd() {return DisplayIconChoicesVoid.VOID;}
	@ConfigItem(
			keyName = "DisplayIconChoices4th",
			name = "Collapsible Icon 4th",
			description = "Configures which chat to make 4th button",
			position = 4,
			section = collapsibleSettings
	)
	default DisplayIconChoicesVoid DisplayIconChoices4th() {return DisplayIconChoicesVoid.VOID;}

	@ConfigItem(
			keyName = "DisplayIconChoices5th",
			name = "Collapsible Icon 5th",
			description = "Configures which chat to make 5th button",
			position = 4,
			section = collapsibleSettings
	)
	default DisplayIconChoicesVoid DisplayIconChoices5th() {return DisplayIconChoicesVoid.VOID;}
	@ConfigItem(
			keyName = "DisplayIconChoices6th",
			name = "Collapsible Icon 6th",
			description = "Configures which chat to make 6th button",
			position = 4,
			section = collapsibleSettings
	)
	default DisplayIconChoicesVoid DisplayIconChoices6th() {return DisplayIconChoicesVoid.VOID;}
	@ConfigItem(
			keyName = "DisplayIconChoices7th",
			name = "Collapsible Icon 7th",
			description = "Configures which chat to make 7th button",
			position = 4,
			section = collapsibleSettings
	)
	default DisplayIconChoicesVoid DisplayIconChoices7th() {return DisplayIconChoicesVoid.VOID;}
	@ConfigItem(
			keyName = "DisplayIconChoices8th",
			name = "Collapsible Icon 8th",
			description = "Configures which chat to make 8th button",
			position = 4,
			section = collapsibleSettings
	)
	default DisplayIconChoicesVoid DisplayIconChoices8th() {return DisplayIconChoicesVoid.VOID;}
/*/

	// Entire Group Area

	@ConfigItem(
			keyName = "Entire Group Collision (Values)",
			name = "Entire Group Collision (Values)",
			description = "Comma-separated list (width, height)",
			position = 0,
			section = entiregroupSettings
	)

	default String getEntireGroupValues() { return "519, 165"; }


	@ConfigItem(
			keyName = "Debug Entire Collision View",
			name = "Debug Entire Collision View",
			description = "Makes collision parent visible, buttons have to stay inside collisions",
			position = 1,
			section = entiregroupSettings
	)

	default boolean EntireGroupDebugParent()
	{
		return false;
	}



	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// Chat Bar Buttons Area

	@ConfigItem(
			keyName = "Chatbar Group Collision (Values)",
			name = "Chatbar Group Collision (Values)",
			description = "Comma-separated list (x, y, width, height)",
			position = 0,
			section = chatbarsettings
	)

	default String getChatBarButtonValues() { return "0, 0, 519, 23"; }


	@ConfigItem(
			keyName = "Debug Chat Bar Collision View",
			name = "Debug Group Collision View",
			description = "Makes collision parent visible, buttons have to stay inside collisions",
			position = 1,
			section = chatbarsettings
	)

	default boolean ChatBarButtonDebugParent()
	{
		return false;
	}

	// All
	@ConfigItem(
			keyName = "ALL Icon Collision (Values)",
			name = "ALL Icon Collision (Values)                     o",
			description = "Comma-separated list (x, y, width, height)",
			position = 3,
			section = chatbarsettings
	)
	default String getALLIconValues() { return "3, 0, 56, 23"; }

	@ConfigItem(
			keyName = "ALL Icon Sprite (Values)",
			name = "ALL Icon Sprite (Values)",
			description = "Comma-separated list (x, y, width, height)",
			position = 4,
			section = chatbarsettings
	)
	default String getALLIconValuesSprite() { return "0, 0, 56, 22"; }

	@ConfigItem(
			keyName = "ALL Icon Name (Text)",
			name = "ALL Icon Name (Text)",
			description = "Comma-separated list (default-text, x, y)",
			position = 5,
			section = chatbarsettings
	)
	default String getALLIconText() { return "All, 0, 0"; }

	// Game
	@ConfigItem(
			keyName = "GAME Icon Collision (Values)",
			name = "GAME Icon Collision (Values)                 o",
			description = "Comma-separated list (x, y, width, height)",
			position = 6,
			section = chatbarsettings
	)
	default String getGAMEIconValues() { return "65, 0, 56, 23"; }

	@ConfigItem(
			keyName = "GAME Icon Sprite (Values)",
			name = "GAME Icon Sprite (Values)",
			description = "Comma-separated list (x, y, width, height)",
			position = 7,
			section = chatbarsettings
	)
	default String getGAMEIconValuesSprite() { return "0, 0, 56, 22"; }

	@ConfigItem(
			keyName = "GAME Icon Name (Text)",
			name = "GAME Icon Name (Text)",
			description = "Comma-separated list (default-text, x, y, 2nd-text x, 2nd-text y)",
			position = 8,
			section = chatbarsettings
	)
	default String getGAMEIconText() { return "Game<br> , 0, 0, 0, 0"; }

// Public
	@ConfigItem(
			keyName = "PUBLIC Icon Collision (Values)",
			name = "PUBLIC Icon Collision (Values)               o",
			description = "Comma-separated list (x, y, width, height)",
			position = 9,
			section = chatbarsettings
	)
	default String getPUBLICIconValues() { return "127, 0, 56, 23"; }

	@ConfigItem(
			keyName = "PUBLIC Icon Sprite (Values)",
			name = "PUBLIC Icon Sprite (Values)",
			description = "Comma-separated list (x, y, width, height)",
			position = 10,
			section = chatbarsettings
	)
	default String getPUBLICIconValuesSprite() { return "0, 0, 56, 22"; }

	@ConfigItem(
			keyName = "PUBLIC Icon Name (Text)",
			name = "PUBLIC Icon Name (Text)",
			description = "Comma-separated list (default-text, x, y, 2nd-text x, 2nd-text y)",
			position = 11,
			section = chatbarsettings
	)
	default String getPUBLICIconText() { return "Public<br> , 0, 0, 0, 0"; }

// Private
	@ConfigItem(
			keyName = "PRIVATE Icon Collision (Values)",
			name = "PRIVATE Icon Collision (Values)             o",
			description = "Comma-separated list (x, y, width, height)",
			position = 12,
			section = chatbarsettings
	)
	default String getPRIVATEIconValues() { return "189, 0, 56, 23"; }

	@ConfigItem(
			keyName = "PRIVATE Icon Sprite (Values)",
			name = "PRIVATE Icon Sprite (Values)",
			description = "Comma-separated list (x, y, width, height)",
			position = 13,
			section = chatbarsettings
	)
	default String getPRIVATEIconValuesSprite() { return "0, 0, 56, 22"; }

	@ConfigItem(
			keyName = "PRIVATE Icon Name (Text)",
			name = "PRIVATE Icon Name (Text)",
			description = "Comma-separated list (default-text, x, y, 2nd-text x, 2nd-text y)",
			position = 14,
			section = chatbarsettings
	)
	default String getPRIVATEIconText() { return "Private<br> , 0, 0, 0, 0"; }


// Channel
	@ConfigItem(
			keyName = "CHANNEL Icon Collision (Values)",
			name = "CHANNEL Icon Collision (Values)           o",
			description = "Comma-separated list (x, y, width, height)",
			position = 15,
			section = chatbarsettings
	)
	default String getCHANNELIconValues() { return "251, 0, 56, 23"; }

	@ConfigItem(
			keyName = "CHANNEL Icon Sprite (Values)",
			name = "CHANNEL Icon Sprite (Values)",
			description = "Comma-separated list (x, y, width, height)",
			position = 16,
			section = chatbarsettings
	)
	default String getCHANNELIconValuesSprite() { return "0, 0, 56, 22"; }

	@ConfigItem(
			keyName = "CHANNEL Icon Name (Text)",
			name = "CHANNEL Icon Name (Text)",
			description = "Comma-separated list (default-text, x, y, 2nd-text x, 2nd-text y)",
			position = 17,
			section = chatbarsettings
	)
	default String getCHANNELIconText() { return "Channel<br> , 0, 0, 0, 0"; }

// Clan
	@ConfigItem(
			keyName = "CLAN Icon Collision (Values)",
			name = "CLAN Icon Collision (Values)                  o",
			description = "Comma-separated list (x, y, width, height)",
			position = 18,
			section = chatbarsettings
	)
	default String getCLANIconValues() { return "313, 0, 56, 23"; }

	@ConfigItem(
			keyName = "CLAN Icon Sprite (Values)",
			name = "CLAN Icon Sprite (Values)",
			description = "Comma-separated list (x, y, width, height)",
			position = 19,
			section = chatbarsettings
	)
	default String getCLANIconValuesSprite() { return "0, 0, 56, 22"; }

	@ConfigItem(
			keyName = "CLAN Icon Name (Text)",
			name = "CLAN Icon Name (Text)",
			description = "Comma-separated list (default-text, x, y, 2nd-text x, 2nd-text y)",
			position = 20,
			section = chatbarsettings
	)
	default String getCLANIconText() { return "Clan<br> , 0, 0, 0, 0"; }

// Trade
	@ConfigItem(
			keyName = "TRADE Icon Collision (Values)",
			name = "TRADE Icon Collision (Values)                o",
			description = "Comma-separated list (x, y, width, height)",
			position = 21,
			section = chatbarsettings
	)
	default String getTRADEIconValues() { return "375, 0, 56, 23"; }

	@ConfigItem(
			keyName = "TRADE Icon Sprite (Values)",
			name = "TRADE Icon Sprite (Values)",
			description = "Comma-separated list (x, y, width, height)",
			position = 22,
			section = chatbarsettings
	)
	default String getTRADEIconValuesSprite() { return "0, 0, 56, 22"; }

	@ConfigItem(
			keyName = "TRADE Icon Name (Text)",
			name = "TRADE Icon Name (Text)",
			description = "Comma-separated list (default-text, x, y, 2nd-text x, 2nd-text y)",
			position = 23,
			section = chatbarsettings
	)
	default String getTRADEIconText() { return "Trade<br> , 0, 0, 0, 0"; }

// Report
	@ConfigItem(
			keyName = "REPORT Icon Collision (Values)",
			name = "REPORT Icon Collision (Values)              o",
			description = "Comma-separated list (x, y, width, height)",
			position = 24,
			section = chatbarsettings
	)
	default String getREPORTIconValues() { return "437, 0, 79, 23"; }

	@ConfigItem(
			keyName = "REPORT Icon Sprite (Values)",
			name = "REPORT Icon Sprite (Values)",
			description = "Comma-separated list (x, y, width, height)",
			position = 25,
			section = chatbarsettings
	)
	default String getREPORTIconValuesSprite() { return "0, 0, 79, 22"; }

	@ConfigItem(
			keyName = "REPORT Icon Name (Text)",
			name = "REPORT Icon Name (Text)",
			description = "Comma-separated list (default-text, x, y, enable text true or false)",
			position = 26,
			section = chatbarsettings
	)
	default String getREPORTIconText() { return "Report, 0, 0, false"; }

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


	@ConfigItem(
			keyName = "Chatbox Group Collision (Values)",
			name = "Chatbox Group Collision (Values)",
			description = "Comma-separated list (x, y, width, height)",
			position = 3,
			section = chatboxsettings
	)

	default String getChatBoxValues() { return "0, 0, 519, 142"; }


	@ConfigItem(
			keyName = "Debug Chat Box Collision View",
			name = "Debug Chat Box Collision View",
			description = "Makes collision parent visible, buttons have to stay inside collisions",
			position = 4,
			section = chatboxsettings
	)

	default boolean ChatBoxDebugParent()
	{
		return false;
	}










	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/*
	@ConfigItem(
			keyName = "Enable Below Modifications!",
			name = "Enable Below Modifications!",
			description = "enable to change options below, else defaulted",
			position = 0,
			section = chatbarsettings
	)

	default boolean ModifyDefaultOptionsChatBarIcons()
	{
		return false;
	}
/*/


	@ConfigItem(
			keyName = "8th Icon Collision (Values)",
			name = "8th Icon Collision (Values)",
			description = "Comma-separated list (x, y, width, height)",
			position = 26,
			section = collapsibleSettings
	)
	default String get8thIconValues()
	{
		return "59, 1, 6, 23";
	}


	@ConfigItem(
			keyName = "8th Icon Sprite (Values)",
			name = "8th Icon Sprite (Values)",
			description = "Comma-separated list (x, y, width, height)",
			position = 27,
			section = collapsibleSettings
	)
	default String get8thIconValuesSprite()
	{
		return "0, 0, 6, 23";
	}

	@ConfigItem(
			keyName = "7th Icon Collision (Values)",
			name = "7th Icon Collision (Values)",
			description = "Comma-separated list (x, y, width, height)",
			position = 23,
			section = collapsibleSettings
	)
	default String get7thIconValues()
	{
		return "54, 1, 6, 23";
	}


	@ConfigItem(
			keyName = "7th Icon Sprite (Values)",
			name = "7th Icon Sprite (Values)",
			description = "Comma-separated list (x, y, width, height)",
			position = 24,
			section = collapsibleSettings
	)
	default String get7thIconValuesSprite()
	{
		return "0, 0, 6, 23";
	}

	@ConfigItem(
			keyName = "6th Icon Collision (Values)",
			name = "6th Icon Collision (Values)",
			description = "Comma-separated list (x, y, width, height)",
			position = 21,
			section = collapsibleSettings
	)
	default String get6thIconValues()
	{
		return "49, 1, 6, 23";
	}


	@ConfigItem(
			keyName = "6th Icon Sprite (Values)",
			name = "6th Icon Sprite (Values)",
			description = "Comma-separated list (x, y, width, height)",
			position = 22,
			section = collapsibleSettings
	)
	default String get6thIconValuesSprite()
	{
		return "0, 0, 6, 23";
	}

	@ConfigItem(
			keyName = "5th Icon Collision (Values)",
			name = "5th Icon Collision (Values)",
			description = "Comma-separated list (x, y, width, height)",
			position = 18,
			section = collapsibleSettings
	)
	default String get5thIconValues()
	{
		return "44, 1, 6, 23";
	}


	@ConfigItem(
			keyName = "5th Icon Sprite (Values)",
			name = "5th Icon Sprite (Values)",
			description = "Comma-separated list (x, y, width, height)",
			position = 19,
			section = collapsibleSettings
	)
	default String get5thIconValuesSprite()
	{
		return "0, 0, 6, 23";
	}

	@ConfigItem(
			keyName = "4th Icon Collision (Values)",
			name = "4th Icon Collision (Values)",
			description = "Comma-separated list (x, y, width, height)",
			position = 15,
			section = collapsibleSettings
	)
	default String get4thIconValues()
	{
		return "39, 1, 6, 23";
	}


	@ConfigItem(
			keyName = "4th Icon Sprite (Values)",
			name = "4th Icon Sprite (Values)",
			description = "Comma-separated list (x, y, width, height)",
			position = 16,
			section = collapsibleSettings
	)
	default String get4thIconValuesSprite()
	{
		return "0, 0, 6, 23";
	}

	@ConfigItem(
			keyName = "3rd Icon Collision (Values)",
			name = "3rd Icon Collision (Values)",
			description = "Comma-separated list (x, y, width, height)",
			position = 12,
			section = collapsibleSettings
	)
	default String get3rdIconValues()
	{
		return "34, 1, 6, 23";
	}


	@ConfigItem(
			keyName = "3rd Icon Sprite (Values)",
			name = "3rd Icon Sprite (Values)",
			description = "Comma-separated list (x, y, width, height)",
			position = 13,
			section = collapsibleSettings
	)
	default String get3rdIconValuesSprite()
	{
		return "0, 0, 6, 23";
	}

	@ConfigItem(
			keyName = "2nd Icon Collision (Values)",
			name = "2nd Icon Collision (Values)",
			description = "Comma-separated list (x, y, width, height)",
			position = 9,
			section = collapsibleSettings
	)
	default String get2ndIconValues()
	{
		return "29, 1, 6, 23";
	}


	@ConfigItem(
			keyName = "2nd Icon Sprite (Values)",
			name = "2nd Icon Sprite (Values)",
			description = "Comma-separated list (x, y, width, height)",
			position = 10,
			section = collapsibleSettings
	)
	default String get2ndIconValuesSprite()
	{
		return "0, 0, 6, 23";
	}

	@ConfigItem(
			keyName = "Main Icon Collision (Values)",
			name = "Main Icon Collision (Values)",
			description = "Comma-separated list (x, y, width, height)",
			position = 6,
			section = collapsibleSettings
	)
	default String getMainIconValues() { return "8, 1, 22, 23"; }

	@ConfigItem(
			keyName = "Main Icon Sprite (Values)",
			name = "Main Icon Sprite (Values)",
			description = "Comma-separated list (x, y, width, height)",
			position = 7,
			section = collapsibleSettings
	)
	default String getMainIconValuesSprite() { return "0, 0, 23, 23"; }

	@ConfigItem(
			keyName = "Main Icon Name (Text)",
			name = "Main Icon (Text)",
			description = "Comma-separated list (hover-text, default-text, x, y)",
			position = 5,
			section = collapsibleSettings
	)
	default String getMainIconText() { return "+, -, -16, 0"; }

	@ConfigItem(
			keyName = "Debug Collapse Collision View",
			name = "Debug Collapse Collision View",
			description = "Makes collision parent visible, buttons have to stay inside collisions",
			position = 2,
			section = collapsibleSettings
	)
	default boolean CollapseDebugParent()
	{
		return false;
	}

	@ConfigItem(
			keyName = "Collapse Collision (Values)",
			name = "Collapse Group Collision (Values)",
			description = "Comma-separated list (x, y, width, height)",
			position = 1,
			section = collapsibleSettings
	)
	default String getCollapseCollisionValues() { return "-6, 142, 70, 23"; }



//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// EXTRA SETTINGS AREA

	@ConfigItem(
			keyName = "Disable Collapsible Icon",
			name = "Disable Collapsible Icon",
			description = "sets collapsible icon to off, default to chat bar icon settings",
			position = 0,
			section = extraSettings
	)

	default boolean DisableCollapsibleIcon()
	{
		return false;
	}

	@ConfigItem(
			keyName = "Toggleable Chat Box Interface",
			name = "! Toggleable Chat Box Interface !",
			description = "chat box only opens when clicking on chat buttons, great for spacebar skilling",
			position = 0,
			section = extraSettings
	)

	default boolean ToggleableChatboxSetting()
	{
		return false;
	}


	@ConfigItem(
			keyName = "Hide Mini Map With Chat",
			name = "Hide Mini Map With Chat",
			description = "enables below modification to hide mini map with chat ",
			position = 2,
			section = extraSettings
	)

	default boolean HideMiniMapSetting()
	{
		return false;
	}

	@ConfigItem(
			keyName = "Hide Mini Map Setting",
			name = "- Hide Mini Map Setting",
			description = "closed = minimap hides when chat is closed, opened = minimap hides when chat is opened",
			position = 3,
			section = extraSettings
	)

	default MinimapChoices MinimapChoices()
	{
		return MinimapChoices.Closed;
	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////




//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/*
	@ConfigItem(
			keyName = "Width Of Chat Widget:",
			name = "Width Of Chat Widget:",
			description = "value for widget width and button offset"
	)
	default String NewChatWidth()
	{
		return "";
	}

 */

	/*
	@ConfigItem(
			keyName = "set logout 'x' to hidden",
			name = "Set Minimap Logout To Hidden",
			description = "sets logout 'x' to hidden"
	)
	default boolean HideLogout()
	{
		return false;
	}

	 */

}
