package com.collapsiblemodernchat;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.events.ClientTick;
import net.runelite.api.events.ScriptPostFired;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetInfo;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

import static java.lang.Integer.valueOf;

@Slf4j
@PluginDescriptor(
		name = "Collapsible Modern Chat DEMO",
		description = "Resizeable/Modern Layout only: Enable Transparent Chat",
		tags = {"chat", "resizeable", "modern", "collapse", "collapsible", "icon", "notification"}
)
public class collapsiblemodernchatplugin extends Plugin
{

	@Inject
	private Client client;

	@Subscribe
	public void onClientTick(ClientTick event) {

		updatePlugins();

	}

	@Inject
	private collapsiblemodernchatconfig config;

	@Subscribe
	public void onScriptPostFired(ScriptPostFired scriptPostFired) {
		updatePlugins();

	}

	int chatbuttonhidecheck = 0;
	int mainwidgetid = 0;
	int secondwidgetid = 0;
	int thirdwidgetid = 0;
	int fourthwidgetid = 0;
	int fifthwidgetid = 0;
	int sixthwidgetid = 0;
	int seventhwidgetid = 0;
	int eighthwidgetid = 0;

	private void updatePlugins() { //

		chatbuttonhidecheck = 0; /// reset

		// below is getting the chat button widget ids
		// sprites - which is going to be called again later
		final Widget resizableChatButtonWidgetAll = client.getWidget(valueOf(10616837));
		final Widget resizableChatButtonWidgetGame = client.getWidget(valueOf(10616840));
		final Widget resizableChatButtonWidgetPublic = client.getWidget(valueOf(10616844));
		final Widget resizableChatButtonWidgetPrivate = client.getWidget(valueOf(10616848));
		final Widget resizableChatButtonWidgetChannel = client.getWidget(valueOf(10616852));
		final Widget resizableChatButtonWidgetClan = client.getWidget(valueOf(10616856));
		final Widget resizableChatButtonWidgetTrade = client.getWidget(valueOf(10616860));

		// below checks if chat buttons are pressed or not

		if ((resizableChatButtonWidgetAll.getSpriteId() == 3053 || resizableChatButtonWidgetAll.getSpriteId() == 3054) ||
				(resizableChatButtonWidgetGame.getSpriteId() == 3053 || resizableChatButtonWidgetGame.getSpriteId() == 3054) ||
				(resizableChatButtonWidgetPublic.getSpriteId() == 3053 || resizableChatButtonWidgetPublic.getSpriteId() == 3054) ||
				(resizableChatButtonWidgetPrivate.getSpriteId() == 3053 || resizableChatButtonWidgetPrivate.getSpriteId() == 3054) ||
				(resizableChatButtonWidgetChannel.getSpriteId() == 3053 || resizableChatButtonWidgetChannel.getSpriteId() == 3054) ||
				(resizableChatButtonWidgetClan.getSpriteId() == 3053 || resizableChatButtonWidgetClan.getSpriteId() == 3054) ||
				(resizableChatButtonWidgetTrade.getSpriteId() == 3053 || resizableChatButtonWidgetTrade.getSpriteId() == 3054))
		{
			// Open Chat
			chatbuttonhidecheck = 1;

		} else {
			if ((resizableChatButtonWidgetAll.getSpriteId() == 3051 || resizableChatButtonWidgetAll.getSpriteId() == 3052) ||
					(resizableChatButtonWidgetGame.getSpriteId() == 3051 || resizableChatButtonWidgetGame.getSpriteId() == 3052) ||
					(resizableChatButtonWidgetPublic.getSpriteId() == 3051 || resizableChatButtonWidgetPublic.getSpriteId() == 3052) ||
					(resizableChatButtonWidgetPrivate.getSpriteId() == 3051 || resizableChatButtonWidgetPrivate.getSpriteId() == 3052) ||
					(resizableChatButtonWidgetChannel.getSpriteId() == 3051 || resizableChatButtonWidgetChannel.getSpriteId() == 3052) ||
					(resizableChatButtonWidgetClan.getSpriteId() == 3051 || resizableChatButtonWidgetClan.getSpriteId() == 3052) ||
					(resizableChatButtonWidgetTrade.getSpriteId() == 3051 || resizableChatButtonWidgetTrade.getSpriteId() == 3052))
			{
			//	Closed Chat
			if (config.DisableCollapsibleIcon() == false) {
				chatbuttonhidecheck = 0;
			}
			else
			{
				if (config.DisableCollapsibleIcon() == true) {
					chatbuttonhidecheck = 1;
				}
			}

			}

		}


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Checking for npcs/player dialogue and transparent chat background
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		// since chatbuttonhidecheck gets reset to 0 at beginning

		// check chat npc dialog
		final Widget ChatBoxDialog = client.getWidget(15138822);

		if (ChatBoxDialog != null && ChatBoxDialog.getText()!=null) {
			if (chatbuttonhidecheck == 0)
			{
				chatbuttonhidecheck = 1;
			}

		}


		// check chat Player dialog
		final Widget ChatBoxDialogPlayer = client.getWidget(14221318);

		if (ChatBoxDialogPlayer != null && ChatBoxDialogPlayer.getText()!=null) {
			if (chatbuttonhidecheck == 0)
			{
				chatbuttonhidecheck = 1;
			}

		}


		// check for transparent background
		final Widget ChatBoxBackgroundCheck = client.getWidget(10616867);

		if (ChatBoxBackgroundCheck != null && !ChatBoxBackgroundCheck.isSelfHidden()) {
			for (Widget widget : ChatBoxBackgroundCheck.getDynamicChildren()) {

				if (widget.getSpriteId()!=-1){
					chatbuttonhidecheck = 1;
				}

			}
		}


		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// set minimap to be hidden
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		if (config.HideMiniMapSetting()==true){
			if (chatbuttonhidecheck == 1) {
				final Widget resizableNormalWidget = client.getWidget(WidgetInfo.RESIZABLE_MINIMAP_WIDGET);

				if (resizableNormalWidget != null && !resizableNormalWidget.isSelfHidden()) {
					for (Widget widget : resizableNormalWidget.getStaticChildren()) {
						switch (config.MinimapChoices()) {
							case Closed:
								widget.setHidden(false);
								break;

							case Open:
								widget.setHidden(true);
								break;
						}

					}
				}


			}

			if (chatbuttonhidecheck == 0) {
				final Widget resizableNormalWidget = client.getWidget(WidgetInfo.RESIZABLE_MINIMAP_WIDGET);

				if (resizableNormalWidget != null && !resizableNormalWidget.isSelfHidden()) {
					for (Widget widget : resizableNormalWidget.getStaticChildren()) {

						switch (config.MinimapChoices()) {

							case Closed:
								widget.setHidden(true);
								break;

							case Open:
								widget.setHidden(false);
								break;
						}


					}
				}


			}
		}


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//Debug Settings and collapsible Parent
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


/// entire debug area //

		final Widget RealEntireDebugSpriteWidget = client.getWidget(10616832);

		if (chatbuttonhidecheck == 1) {
			if (config.EntireGroupDebugParent() == true ) {
				if (RealEntireDebugSpriteWidget != null) {
					RealEntireDebugSpriteWidget.setType(3);
					RealEntireDebugSpriteWidget.setFilled(true);
				}
			}
			else {
				if (config.EntireGroupDebugParent() == false ) {
					if (RealEntireDebugSpriteWidget != null) {
						RealEntireDebugSpriteWidget.setType(0);
						RealEntireDebugSpriteWidget.setFilled(false);
					}
				}
			}

		}


/// collapse debug area //

		final Widget CollapseDebugSpriteWidget = client.getWidget(10616833);

		if (chatbuttonhidecheck == 0) {
			if (config.CollapseDebugParent() == true ) {
				if (CollapseDebugSpriteWidget != null) {
					CollapseDebugSpriteWidget.setType(3);
					CollapseDebugSpriteWidget.setFilled(true);
				}
			}
			else {
				if (config.CollapseDebugParent() == false ) {
					if (CollapseDebugSpriteWidget != null) {
						CollapseDebugSpriteWidget.setType(0);
						CollapseDebugSpriteWidget.setFilled(false);
					}
				}
			}

		}//jjkhklhjkljkl

/// chat bar debug area //

		final Widget ChatBarDebugSpriteWidget = client.getWidget(10616833);

		if (chatbuttonhidecheck == 1) {
			if (config.ChatBarButtonDebugParent() == true ) {
				if (ChatBarDebugSpriteWidget != null) {
					ChatBarDebugSpriteWidget.setType(3);
					ChatBarDebugSpriteWidget.setFilled(true);
				}
			}
			else {
				if (config.ChatBarButtonDebugParent() == false ) {
					if (ChatBarDebugSpriteWidget != null) {
						ChatBarDebugSpriteWidget.setType(0);
						ChatBarDebugSpriteWidget.setFilled(false);
					}
				}
			}

		}

		/// chatbox debug area //

		final Widget ChatboxDebugSpriteWidget = client.getWidget(10616866);

		if (chatbuttonhidecheck == 1) {
			if (config.ChatBoxDebugParent() == true ) {
				if (ChatboxDebugSpriteWidget != null) {
					ChatboxDebugSpriteWidget.setType(3);
					ChatboxDebugSpriteWidget.setFilled(true);
				}
			}
			else {
				if (config.ChatBoxDebugParent() == false ) {
					if (ChatboxDebugSpriteWidget != null) {
						ChatboxDebugSpriteWidget.setType(0);
						ChatboxDebugSpriteWidget.setFilled(false);
					}
				}
			}

		}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


		// entire debug collision

		Widget[] EntireGroupParentArray = {
				client.getWidget(10747995),
				client.getWidget(10616832),

		};


// Adjusting the entire parent collision values
		String EntireCollisioninput = config.getEntireGroupValues();
		String[] EntireCollisionvalues = EntireCollisioninput.split(", ");

		int entiredebugwidth = Integer.parseInt(EntireCollisionvalues[0]);
		int entiredebugheight = Integer.parseInt(EntireCollisionvalues[1]);


		for (Widget EntireParentArrayLoop : EntireGroupParentArray) {
			if (EntireParentArrayLoop != null) {
				if (chatbuttonhidecheck == 1) {

					EntireParentArrayLoop.setWidth(entiredebugwidth);
					EntireParentArrayLoop.setHeight(entiredebugheight);

				}
			}
		}


		// chatbar coll debug collision

		Widget[] EhatbarcollParentArray = {
				client.getWidget(10616835),
				client.getWidget(10616834),
				client.getWidget(10616833)

		};

// Adjusting the chatbar parent collision values
		String chatbarcollinput = config.getChatBarButtonValues();
		String[] chatbarcollvalues = chatbarcollinput.split(", ");

		int chatbarcolldebugx = Integer.parseInt(chatbarcollvalues[0]);
		int chatbarcolldebugy = Integer.parseInt(chatbarcollvalues[1]);
		int chatbarcolldebugwidth = Integer.parseInt(chatbarcollvalues[2]);
		int chatbarcolldebugheight = Integer.parseInt(chatbarcollvalues[3]);


		for (Widget EhatbarcollParentLoop : EhatbarcollParentArray) {
			if (EhatbarcollParentLoop != null) {
				if (chatbuttonhidecheck == 1) {
					if (EhatbarcollParentLoop==client.getWidget(10616833))
					{
						EhatbarcollParentLoop.setRelativeX(chatbarcolldebugx);
						EhatbarcollParentLoop.setRelativeY(chatbarcolldebugy+142);
						EhatbarcollParentLoop.setWidth(chatbarcolldebugwidth);
						EhatbarcollParentLoop.setHeight(chatbarcolldebugheight);
					}
					else
					{
						EhatbarcollParentLoop.setRelativeX(0);
						EhatbarcollParentLoop.setRelativeY(0);
						EhatbarcollParentLoop.setWidth(chatbarcolldebugwidth);
						EhatbarcollParentLoop.setHeight(chatbarcolldebugheight);
					}



				}
			}
		}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


		Widget[] ChatboxButtonsParentArray = {
				client.getWidget(10616835),
				client.getWidget(10616834),
				client.getWidget(10616833)
		};


// Adjusting the collapsible parent collision values
		String CollapseCollisioninput = config.getCollapseCollisionValues();
		String[] CollapseCollisionvalues = CollapseCollisioninput.split(", ");

		int debugx = Integer.parseInt(CollapseCollisionvalues[0]);
		int debugy = Integer.parseInt(CollapseCollisionvalues[1]);
		int debugwidth = Integer.parseInt(CollapseCollisionvalues[2]);
		int debugheight = Integer.parseInt(CollapseCollisionvalues[3]);


		for (Widget ChatboxButtonsParentArrayLoop : ChatboxButtonsParentArray) {
			if (ChatboxButtonsParentArrayLoop != null) {
				if (chatbuttonhidecheck == 0) {
					if (ChatboxButtonsParentArrayLoop==client.getWidget(10616833)) {
						ChatboxButtonsParentArrayLoop.setRelativeX(debugx);
						ChatboxButtonsParentArrayLoop.setRelativeY(debugy);
					}
					ChatboxButtonsParentArrayLoop.setWidth(debugwidth);
					ChatboxButtonsParentArrayLoop.setHeight(debugheight);

				}
			}
		}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


		Widget[] ChatboxChatParentArray = {
				client.getWidget(10616866),

		};


// Adjusting the chatbox parent collision values
		String ChatboxChatCollisioninput = config.getChatBoxValues();
		String[] ChatboxChatCollisionvalues = ChatboxChatCollisioninput.split(", ");

		int debugxchatbox = Integer.parseInt(ChatboxChatCollisionvalues[0]);
		int debugychatbox = Integer.parseInt(ChatboxChatCollisionvalues[1]);
		int debugwidthchatbox = Integer.parseInt(ChatboxChatCollisionvalues[2]);
		int debugheightchatbox = Integer.parseInt(ChatboxChatCollisionvalues[3]);


		for (Widget ChatboxChatParentArrayLoop : ChatboxChatParentArray) {
			if (ChatboxChatParentArrayLoop != null) {
				if (chatbuttonhidecheck == 1) {

						ChatboxChatParentArrayLoop.setRelativeX(debugxchatbox);
						ChatboxChatParentArrayLoop.setRelativeY(debugychatbox);
						ChatboxChatParentArrayLoop.setWidth(debugwidthchatbox);
						ChatboxChatParentArrayLoop.setHeight(debugheightchatbox);


				}
			}
		}






//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Make collapsible Icon
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		int DisplayIconChoicesParent = 0;
		int DisplayIconChoicesSprite = 0;
		int DisplayIconChoicesText = 0;
		int DisplayIconChoicesTextAlt = 0;
		String DisplayIconChoicesTextInput = "";

		switch (config.DisplayIconChoices())
		{
			case ALL:
				DisplayIconChoicesParent=10616836;
				DisplayIconChoicesSprite = 10616837;
				DisplayIconChoicesText = 10616838;
				DisplayIconChoicesTextAlt = -1;
				DisplayIconChoicesTextInput = "All";

				mainwidgetid = 10616836;


				break;
			case GAME:
				DisplayIconChoicesParent=10616839;
				DisplayIconChoicesSprite = 10616840;
				DisplayIconChoicesText = 10616841;
				DisplayIconChoicesTextAlt = 10616842;
				DisplayIconChoicesTextInput = "Game";

				mainwidgetid = 10616839;
				break;
			case PUBLIC:
				DisplayIconChoicesParent=10616843;
				DisplayIconChoicesSprite = 10616844;
				DisplayIconChoicesText = 10616845;
				DisplayIconChoicesTextAlt = 10616846;
				DisplayIconChoicesTextInput = "Public";

				mainwidgetid = 10616843;
				break;
			case PRIVATE:
				DisplayIconChoicesParent = 10616847;
				DisplayIconChoicesSprite = 10616848;
				DisplayIconChoicesText = 10616849;
				DisplayIconChoicesTextAlt = 10616850;
				DisplayIconChoicesTextInput = "Private";

				mainwidgetid = 10616847;
				break;
			case CHANNEL:
				DisplayIconChoicesParent = 10616851;
				DisplayIconChoicesSprite = 10616852;
				DisplayIconChoicesText = 10616853;
				DisplayIconChoicesTextAlt = 10616854;
				DisplayIconChoicesTextInput = "Channel";
				mainwidgetid = 10616851;
				break;
			case CLAN:
				DisplayIconChoicesParent = 10616855;
				DisplayIconChoicesSprite = 10616856;
				DisplayIconChoicesText = 10616857;
				DisplayIconChoicesTextAlt = 10616858;
				DisplayIconChoicesTextInput = "Clan";
				mainwidgetid = 10616855;
				break;
			case TRADE:
				DisplayIconChoicesParent = 10616859;
				DisplayIconChoicesSprite = 10616860;
				DisplayIconChoicesText = 10616861;
				DisplayIconChoicesTextAlt = 10616862;
				DisplayIconChoicesTextInput = "Trade";
				mainwidgetid = 10616859;
				break;
			case REPORT: ///
				DisplayIconChoicesParent = 10616863;
				DisplayIconChoicesSprite = 10616864;
				DisplayIconChoicesText = 10616865;
				DisplayIconChoicesTextAlt = -1;
				DisplayIconChoicesTextInput = "Report";
				mainwidgetid = 10616863;
				break;
		}

		int DisplayIconChoicesParent2nd = 0;
		int DisplayIconChoicesSprite2nd = 0;
		int DisplayIconChoicesText2nd = 0;
		int DisplayIconChoicesText2ndAlt = 0;

		switch (config.DisplayIconChoices2nd())
		{
			case ALL:
				DisplayIconChoicesParent2nd = 10616836;
				DisplayIconChoicesSprite2nd = 10616837;
				DisplayIconChoicesText2nd = 10616838;
				DisplayIconChoicesText2ndAlt = -1;

				secondwidgetid = 10616836;
				break;
			case GAME:
				DisplayIconChoicesParent2nd = 10616839;
				DisplayIconChoicesSprite2nd = 10616840;
				DisplayIconChoicesText2nd = 10616841;
				DisplayIconChoicesText2ndAlt = 10616842;

				secondwidgetid = 10616839;
				break;
			case PUBLIC:
				DisplayIconChoicesParent2nd = 10616843;
				DisplayIconChoicesSprite2nd = 10616844;
				DisplayIconChoicesText2nd = 10616845;
				DisplayIconChoicesText2ndAlt = 10616846;

				secondwidgetid = 10616843;
				break;
			case PRIVATE:
				DisplayIconChoicesParent2nd = 10616847;
				DisplayIconChoicesSprite2nd = 10616848;
				DisplayIconChoicesText2nd = 10616849;
				DisplayIconChoicesText2ndAlt = 10616850;

				secondwidgetid = 10616847;
				break;
			case CHANNEL:
				DisplayIconChoicesParent2nd = 10616851;
				DisplayIconChoicesSprite2nd = 10616852;
				DisplayIconChoicesText2nd = 10616853;
				DisplayIconChoicesText2ndAlt = 10616854;
				secondwidgetid = 10616851;
				break;
			case CLAN:
				DisplayIconChoicesParent2nd = 10616855;
				DisplayIconChoicesSprite2nd = 10616856;
				DisplayIconChoicesText2nd = 10616857;
				DisplayIconChoicesText2ndAlt = 10616858;
				secondwidgetid = 10616855;
				break;
			case TRADE:
				DisplayIconChoicesParent2nd = 10616859;
				DisplayIconChoicesSprite2nd = 10616860;
				DisplayIconChoicesText2nd = 10616861;
				DisplayIconChoicesText2ndAlt = 10616862;
				secondwidgetid = 10616859;
				break;
			case REPORT:

				DisplayIconChoicesParent2nd = 10616863;
				DisplayIconChoicesSprite2nd = 10616864;
				DisplayIconChoicesText2nd = 10616865;
				DisplayIconChoicesText2ndAlt = -1;

				secondwidgetid = 10616863;
				break;
			case VOID:
				DisplayIconChoicesParent2nd = -1;
				DisplayIconChoicesSprite2nd = -1;
				DisplayIconChoicesText2nd = -1;
				DisplayIconChoicesText2ndAlt = -1;
				secondwidgetid = -1;
				break;

		}

		int DisplayIconChoicesParent3rd = 0;
		int DisplayIconChoicesSprite3rd = 0;
		int DisplayIconChoicesText3rd = 0;
		int DisplayIconChoicesText3rdAlt = 0;

		switch (config.DisplayIconChoices3rd())
		{
			case ALL:
				DisplayIconChoicesParent3rd = 10616836;
				DisplayIconChoicesSprite3rd = 10616837;
				DisplayIconChoicesText3rd = 10616838;
				DisplayIconChoicesText3rdAlt = -1;

				thirdwidgetid = 10616836;
				break;
			case GAME:
				DisplayIconChoicesParent3rd = 10616839;
				DisplayIconChoicesSprite3rd = 10616840;
				DisplayIconChoicesText3rd = 10616841;
				DisplayIconChoicesText3rdAlt = 10616842;

				thirdwidgetid = 10616839;
				break;
			case PUBLIC:
				DisplayIconChoicesParent3rd = 10616843;
				DisplayIconChoicesSprite3rd = 10616844;
				DisplayIconChoicesText3rd = 10616845;
				DisplayIconChoicesText3rdAlt = 10616846;

				thirdwidgetid = 10616843;
				break;
			case PRIVATE:
				DisplayIconChoicesParent3rd = 10616847;
				DisplayIconChoicesSprite3rd = 10616848;
				DisplayIconChoicesText3rd = 10616849;
				DisplayIconChoicesText3rdAlt = 10616850;

				thirdwidgetid = 10616847;
				break;
			case CHANNEL:
				DisplayIconChoicesParent3rd = 10616851;
				DisplayIconChoicesSprite3rd = 10616852;
				DisplayIconChoicesText3rd = 10616853;
				DisplayIconChoicesText3rdAlt = 10616854;
				thirdwidgetid = 10616851;
				break;
			case CLAN:
				DisplayIconChoicesParent3rd = 10616855;
				DisplayIconChoicesSprite3rd = 10616856;
				DisplayIconChoicesText3rd = 10616857;
				DisplayIconChoicesText3rdAlt = 10616858;
				thirdwidgetid = 10616855;
				break;
			case TRADE:
				DisplayIconChoicesParent3rd = 10616859;
				DisplayIconChoicesSprite3rd = 10616860;
				DisplayIconChoicesText3rd = 10616861;
				DisplayIconChoicesText3rdAlt = 10616862;
				thirdwidgetid = 10616859;
				break;
			case REPORT:

				DisplayIconChoicesParent3rd = 10616863;
				DisplayIconChoicesSprite3rd = 10616864;
				DisplayIconChoicesText3rd = 10616865;
				DisplayIconChoicesText3rdAlt = -1;

				thirdwidgetid = 10616863;
				break;
			case VOID:
				DisplayIconChoicesParent3rd = -1;
				DisplayIconChoicesSprite3rd = -1;
				DisplayIconChoicesText3rd = -1;
				DisplayIconChoicesText3rdAlt = -1;
				thirdwidgetid = -1;
				break;

		}

		int DisplayIconChoicesParent4th = 0;
		int DisplayIconChoicesSprite4th = 0;
		int DisplayIconChoicesText4th = 0;
		int DisplayIconChoicesText4thAlt = 0;

		switch (config.DisplayIconChoices4th())
		{
			case ALL:
				DisplayIconChoicesParent4th = 10616836;
				DisplayIconChoicesSprite4th = 10616837;
				DisplayIconChoicesText4th = 10616838;
				DisplayIconChoicesText4thAlt = -1;

				fourthwidgetid = 10616836;
				break;
			case GAME:
				DisplayIconChoicesParent4th = 10616839;
				DisplayIconChoicesSprite4th = 10616840;
				DisplayIconChoicesText4th = 10616841;
				DisplayIconChoicesText4thAlt = 10616842;

				fourthwidgetid = 10616839;
				break;
			case PUBLIC:
				DisplayIconChoicesParent4th = 10616843;
				DisplayIconChoicesSprite4th = 10616844;
				DisplayIconChoicesText4th = 10616845;
				DisplayIconChoicesText4thAlt = 10616846;

				fourthwidgetid = 10616843;
				break;
			case PRIVATE:
				DisplayIconChoicesParent4th = 10616847;
				DisplayIconChoicesSprite4th = 10616848;
				DisplayIconChoicesText4th = 10616849;
				DisplayIconChoicesText4thAlt = 10616850;

				fourthwidgetid = 10616847;
				break;
			case CHANNEL:
				DisplayIconChoicesParent4th = 10616851;
				DisplayIconChoicesSprite4th = 10616852;
				DisplayIconChoicesText4th = 10616853;
				DisplayIconChoicesText4thAlt = 10616854;
				fourthwidgetid = 10616851;
				break;
			case CLAN:
				DisplayIconChoicesParent4th = 10616855;
				DisplayIconChoicesSprite4th = 10616856;
				DisplayIconChoicesText4th = 10616857;
				DisplayIconChoicesText4thAlt = 10616858;
				fourthwidgetid = 10616855;
				break;
			case TRADE:

				DisplayIconChoicesParent4th = 10616859;
				DisplayIconChoicesSprite4th = 10616860;
				DisplayIconChoicesText4th = 10616861;
				DisplayIconChoicesText4thAlt = 10616862;
				fourthwidgetid = 10616859;
				break;
			case REPORT:

				DisplayIconChoicesParent4th = 10616863;
				DisplayIconChoicesSprite4th = 10616864;
				DisplayIconChoicesText4th = 10616865;
				DisplayIconChoicesText4thAlt = -1;

				fourthwidgetid = 10616863;
				break;
			case VOID:
				DisplayIconChoicesParent4th = -1;
				DisplayIconChoicesSprite4th = -1;
				DisplayIconChoicesText4th = -1;
				DisplayIconChoicesText4thAlt = -1;
				fourthwidgetid = -1;
				break;

		}

		int DisplayIconChoicesParent5th = 0;
		int DisplayIconChoicesSprite5th = 0;
		int DisplayIconChoicesText5th = 0;
		int DisplayIconChoicesText5thAlt = 0;

		switch (config.DisplayIconChoices5th())
		{
			case ALL:
				DisplayIconChoicesParent5th = 10616836;
				DisplayIconChoicesSprite5th = 10616837;
				DisplayIconChoicesText5th = 10616838;
				DisplayIconChoicesText5thAlt = -1;

				fifthwidgetid = 10616836;
				break;
			case GAME:
				DisplayIconChoicesParent5th = 10616839;
				DisplayIconChoicesSprite5th = 10616840;
				DisplayIconChoicesText5th = 10616841;
				DisplayIconChoicesText5thAlt = 10616842;

				fifthwidgetid = 10616839;
				break;
			case PUBLIC:
				DisplayIconChoicesParent5th = 10616843;
				DisplayIconChoicesSprite5th = 10616844;
				DisplayIconChoicesText5th = 10616845;
				DisplayIconChoicesText5thAlt = 10616846;

				fifthwidgetid = 10616843;
				break;
			case PRIVATE:
				DisplayIconChoicesParent5th = 10616847;
				DisplayIconChoicesSprite5th = 10616848;
				DisplayIconChoicesText5th = 10616849;
				DisplayIconChoicesText5thAlt = 10616850;

				fifthwidgetid = 10616847;
				break;
			case CHANNEL:
				DisplayIconChoicesParent5th = 10616851;
				DisplayIconChoicesSprite5th = 10616852;
				DisplayIconChoicesText5th = 10616853;
				DisplayIconChoicesText5thAlt = 10616854;
				fifthwidgetid = 10616851;
				break;
			case CLAN:
				DisplayIconChoicesParent5th = 10616855;
				DisplayIconChoicesSprite5th = 10616856;
				DisplayIconChoicesText5th = 10616857;
				DisplayIconChoicesText5thAlt = 10616858;
				fifthwidgetid = 10616855;
				break;
			case TRADE:

				DisplayIconChoicesParent5th = 10616859;
				DisplayIconChoicesSprite5th = 10616860;
				DisplayIconChoicesText5th = 10616861;
				DisplayIconChoicesText5thAlt = 10616862;
				fifthwidgetid = 10616859;
				break;
			case REPORT:

				DisplayIconChoicesParent5th = 10616863;
				DisplayIconChoicesSprite5th = 10616864;
				DisplayIconChoicesText5th = 10616865;
				DisplayIconChoicesText5thAlt = -1;

				fifthwidgetid = 10616863;
				break;
			case VOID:
				DisplayIconChoicesParent5th = -1;
				DisplayIconChoicesSprite5th = -1;
				DisplayIconChoicesText5th = -1;
				DisplayIconChoicesText5thAlt = -1;
				fifthwidgetid = -1;
				break;

		}

		int DisplayIconChoicesParent6th = 0;
		int DisplayIconChoicesSprite6th = 0;
		int DisplayIconChoicesText6th = 0;
		int DisplayIconChoicesText6thAlt = 0;

		switch (config.DisplayIconChoices6th())
		{
			case ALL:
				DisplayIconChoicesParent6th = 10616836;
				DisplayIconChoicesSprite6th = 10616837;
				DisplayIconChoicesText6th = 10616838;
				DisplayIconChoicesText6thAlt = -1;

				sixthwidgetid = 10616836;
				break;
			case GAME:
				DisplayIconChoicesParent6th = 10616839;
				DisplayIconChoicesSprite6th = 10616840;
				DisplayIconChoicesText6th = 10616841;
				DisplayIconChoicesText6thAlt = 10616842;

				sixthwidgetid = 10616839;
				break;
			case PUBLIC:
				DisplayIconChoicesParent6th = 10616843;
				DisplayIconChoicesSprite6th = 10616844;
				DisplayIconChoicesText6th = 10616845;
				DisplayIconChoicesText6thAlt = 10616846;

				sixthwidgetid = 10616843;
				break;
			case PRIVATE:
				DisplayIconChoicesParent6th = 10616847;
				DisplayIconChoicesSprite6th = 10616848;
				DisplayIconChoicesText6th = 10616849;
				DisplayIconChoicesText6thAlt = 10616850;

				sixthwidgetid = 10616847;
				break;
			case CHANNEL:
				DisplayIconChoicesParent6th = 10616851;
				DisplayIconChoicesSprite6th = 10616852;
				DisplayIconChoicesText6th = 10616853;
				DisplayIconChoicesText6thAlt = 10616854;
				sixthwidgetid = 10616851;
				break;
			case CLAN:
				DisplayIconChoicesParent6th = 10616855;
				DisplayIconChoicesSprite6th = 10616856;
				DisplayIconChoicesText6th = 10616857;
				DisplayIconChoicesText6thAlt = 10616858;
				sixthwidgetid = 10616855;
				break;
			case TRADE:

				DisplayIconChoicesParent6th = 10616859;
				DisplayIconChoicesSprite6th = 10616860;
				DisplayIconChoicesText6th = 10616861;
				DisplayIconChoicesText6thAlt = 10616862;
				sixthwidgetid = 10616859;
				break;
			case REPORT:

				DisplayIconChoicesParent6th = 10616863;
				DisplayIconChoicesSprite6th = 10616864;
				DisplayIconChoicesText6th = 10616865;
				DisplayIconChoicesText6thAlt = -1;

				sixthwidgetid = 10616863;
				break;
			case VOID:
				DisplayIconChoicesParent6th = -1;
				DisplayIconChoicesSprite6th = -1;
				DisplayIconChoicesText6th = -1;
				DisplayIconChoicesText6thAlt = -1;
				sixthwidgetid = -1;
				break;

		}

		int DisplayIconChoicesParent7th = 0;
		int DisplayIconChoicesSprite7th = 0;
		int DisplayIconChoicesText7th = 0;
		int DisplayIconChoicesText7thAlt = 0;

		switch (config.DisplayIconChoices7th())
		{
			case ALL:
				DisplayIconChoicesParent7th = 10616836;
				DisplayIconChoicesSprite7th = 10616837;
				DisplayIconChoicesText7th = 10616838;
				DisplayIconChoicesText7thAlt = -1;

				seventhwidgetid = 10616836;
				break;
			case GAME:
				DisplayIconChoicesParent7th = 10616839;
				DisplayIconChoicesSprite7th = 10616840;
				DisplayIconChoicesText7th = 10616841;
				DisplayIconChoicesText7thAlt = 10616842;

				seventhwidgetid = 10616839;
				break;
			case PUBLIC:
				DisplayIconChoicesParent7th = 10616843;
				DisplayIconChoicesSprite7th = 10616844;
				DisplayIconChoicesText7th = 10616845;
				DisplayIconChoicesText7thAlt = 10616846;

				seventhwidgetid = 10616843;
				break;
			case PRIVATE:
				DisplayIconChoicesParent7th = 10616847;
				DisplayIconChoicesSprite7th = 10616848;
				DisplayIconChoicesText7th = 10616849;
				DisplayIconChoicesText7thAlt = 10616850;

				seventhwidgetid = 10616847;
				break;
			case CHANNEL:
				DisplayIconChoicesParent7th = 10616851;
				DisplayIconChoicesSprite7th = 10616852;
				DisplayIconChoicesText7th = 10616853;
				DisplayIconChoicesText7thAlt = 10616854;
				seventhwidgetid = 10616851;
				break;
			case CLAN:
				DisplayIconChoicesParent7th = 10616855;
				DisplayIconChoicesSprite7th = 10616856;
				DisplayIconChoicesText7th = 10616857;
				DisplayIconChoicesText7thAlt = 10616858;
				seventhwidgetid = 10616855;
				break;
			case TRADE:

				DisplayIconChoicesParent7th = 10616859;
				DisplayIconChoicesSprite7th = 10616860;
				DisplayIconChoicesText7th = 10616861;
				DisplayIconChoicesText7thAlt = 10616862;
				seventhwidgetid = 10616859;
				break;
			case REPORT:

				DisplayIconChoicesParent7th = 10616863;
				DisplayIconChoicesSprite7th = 10616864;
				DisplayIconChoicesText7th = 10616865;
				DisplayIconChoicesText7thAlt = -1;

				seventhwidgetid = 10616863;
				break;
			case VOID:
				DisplayIconChoicesParent7th = -1;
				DisplayIconChoicesSprite7th = -1;
				DisplayIconChoicesText7th = -1;
				DisplayIconChoicesText7thAlt = -1;
				seventhwidgetid = -1;
				break;

		}

		int DisplayIconChoicesParent8th = 0;
		int DisplayIconChoicesSprite8th = 0;
		int DisplayIconChoicesText8th = 0;
		int DisplayIconChoicesText8thAlt = 0;

		switch (config.DisplayIconChoices8th())
		{
			case ALL:
				DisplayIconChoicesParent8th = 10616836;
				DisplayIconChoicesSprite8th = 10616837;
				DisplayIconChoicesText8th = 10616838;
				DisplayIconChoicesText8thAlt = -1;

				eighthwidgetid = 10616836;
				break;
			case GAME:
				DisplayIconChoicesParent8th = 10616839;
				DisplayIconChoicesSprite8th = 10616840;
				DisplayIconChoicesText8th = 10616841;
				DisplayIconChoicesText8thAlt = 10616842;

				eighthwidgetid = 10616839;
				break;
			case PUBLIC:
				DisplayIconChoicesParent8th = 10616843;
				DisplayIconChoicesSprite8th = 10616844;
				DisplayIconChoicesText8th = 10616845;
				DisplayIconChoicesText8thAlt = 10616846;

				eighthwidgetid = 10616843;
				break;
			case PRIVATE:
				DisplayIconChoicesParent8th = 10616847;
				DisplayIconChoicesSprite8th = 10616848;
				DisplayIconChoicesText8th = 10616849;
				DisplayIconChoicesText8thAlt = 10616850;

				eighthwidgetid = 10616847;
				break;
			case CHANNEL:
				DisplayIconChoicesParent8th = 10616851;
				DisplayIconChoicesSprite8th = 10616852;
				DisplayIconChoicesText8th = 10616853;
				DisplayIconChoicesText8thAlt = 10616854;
				eighthwidgetid = 10616851;
				break;
			case CLAN:
				DisplayIconChoicesParent8th = 10616855;
				DisplayIconChoicesSprite8th = 10616856;
				DisplayIconChoicesText8th = 10616857;
				DisplayIconChoicesText8thAlt = 10616858;
				eighthwidgetid = 10616855;
				break;
			case TRADE:
				DisplayIconChoicesParent8th = 10616859;
				DisplayIconChoicesSprite8th = 10616860;
				DisplayIconChoicesText8th = 10616861;
				DisplayIconChoicesText8thAlt = 10616862;
				eighthwidgetid = 10616859;
				break;
			case REPORT:

				DisplayIconChoicesParent8th = 10616863;
				DisplayIconChoicesSprite8th = 10616864;
				DisplayIconChoicesText8th = 10616865;
				DisplayIconChoicesText8thAlt = -1;

				eighthwidgetid = 10616863;
				break;
			case VOID:
				DisplayIconChoicesParent8th = -1;
				DisplayIconChoicesSprite8th = -1;
				DisplayIconChoicesText8th = -1;
				DisplayIconChoicesText8thAlt = -1;
				eighthwidgetid = -1;
				break;

		}


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		String input = config.getMainIconValues();
		String[] values = input.split(", ");

		int mainx = Integer.parseInt(values[0]);
		int mainy = Integer.parseInt(values[1]);
		int mainwidth = Integer.parseInt(values[2]);
		int mainheight = Integer.parseInt(values[3]);

		String inputSpr = config.getMainIconValuesSprite();
		String[] valuesSpr = inputSpr.split(", ");

		int mainxSpr = Integer.parseInt(valuesSpr[0]);
		int mainySpr = Integer.parseInt(valuesSpr[1]);
		int mainwidthSpr = Integer.parseInt(valuesSpr[2]);
		int mainheightSpr = Integer.parseInt(valuesSpr[3]);


		String inputText = config.getMainIconText();
		String[] valuesText = inputText.split(", ");

		String mainhoverText = valuesText[0];
		String maindefText = valuesText[1];
		String mainTextx = valuesText[2];
		String mainTexty = valuesText[3];


		final Widget ChatBarMainParent = client.getWidget(DisplayIconChoicesParent);


			if (ChatBarMainParent != null) {


				if (chatbuttonhidecheck == 0) {
					ChatBarMainParent.setRelativeY(mainy); ////


							ChatBarMainParent.setRelativeX(mainx); /////


					ChatBarMainParent.setWidth(mainwidth); /////
					ChatBarMainParent.setHeight(mainheight); /////
				}

			}


		final Widget ChatBarAllSprite = client.getWidget(DisplayIconChoicesSprite);
		if (ChatBarMainParent != null) {
			if (chatbuttonhidecheck == 0) {

				ChatBarAllSprite.setRelativeX(mainxSpr);
				ChatBarAllSprite.setRelativeY(mainySpr);
				ChatBarAllSprite.setWidth(mainwidthSpr);
				ChatBarAllSprite.setHeight(mainheightSpr);

			}

		}


		final Widget ChatBarMainParentText = client.getWidget(DisplayIconChoicesText);
		final Widget ChatBarMainParentTextAlt = client.getWidget(DisplayIconChoicesTextAlt);
		final Widget ChatBarMainParentSprite = client.getWidget(DisplayIconChoicesSprite);

		if (ChatBarMainParentText != null)  {

			if (chatbuttonhidecheck == 0) {

				ChatBarMainParentText.setRelativeX(Integer.parseInt(mainTextx)); // this needs to be user input
				ChatBarMainParentText.setRelativeY(Integer.parseInt(mainTexty)); // this needs to be user input

				if (ChatBarMainParentSprite.getSpriteId()==3051) {
					ChatBarMainParentText.setText(maindefText);
				}
				if (ChatBarMainParentSprite.getSpriteId()==3052) {
					ChatBarMainParentText.setText(mainhoverText);
				}
			}



		}
		if (ChatBarMainParentTextAlt != null)  {

			if (chatbuttonhidecheck == 0) {

				ChatBarMainParentTextAlt.setRelativeY(debugheight); // this needs to be user input

			}



		}


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// 2nd part of icon
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		String secinput = config.get2ndIconValues();
		String[] secvalues = secinput.split(", ");

		int secx = Integer.parseInt(secvalues[0]);
		int secy = Integer.parseInt(secvalues[1]);
		int secwidth = Integer.parseInt(secvalues[2]);
		int secheight = Integer.parseInt(secvalues[3]);

		String secinputSpr = config.get2ndIconValuesSprite();
		String[] secvaluesSpr = secinputSpr.split(", ");

		int secxSpr = Integer.parseInt(secvaluesSpr[0]);
		int secySpr = Integer.parseInt(secvaluesSpr[1]);
		int secwidthSpr = Integer.parseInt(secvaluesSpr[2]);
		int secheightSpr = Integer.parseInt(secvaluesSpr[3]);

		if (secondwidgetid != -1)
		{
				final Widget ChatBar2ndParent = client.getWidget(DisplayIconChoicesParent2nd);

				if (ChatBar2ndParent != null) {

					if (chatbuttonhidecheck == 0) {
						ChatBar2ndParent.setRelativeY(secy);
						ChatBar2ndParent.setRelativeX(secx);
						ChatBar2ndParent.setWidth(secwidth);
						ChatBar2ndParent.setHeight(secheight);
					}


				final Widget ChatBar2ndParentSprite = client.getWidget(DisplayIconChoicesSprite2nd);

				if (ChatBar2ndParentSprite != null) {

					if (chatbuttonhidecheck == 0) {
						ChatBar2ndParentSprite.setRelativeX(secxSpr);
						ChatBar2ndParentSprite.setRelativeY(secySpr);
						ChatBar2ndParentSprite.setWidth(secwidthSpr);
						ChatBar2ndParentSprite.setHeight(secheightSpr);

					}


				}

				final Widget ChatBar2ndParentText = client.getWidget(DisplayIconChoicesText2nd);
				final Widget ChatBar2ndParentTextAlt = client.getWidget(DisplayIconChoicesText2ndAlt);
				if (ChatBar2ndParentText != null) {

					if (chatbuttonhidecheck == 0) {
						ChatBar2ndParentText.setRelativeY(-debugheight);
						if(DisplayIconChoicesText2ndAlt!=-1)
						{
							ChatBar2ndParentTextAlt.setRelativeY(-debugheight);
						}
					}


				}

			}

		}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// 3rd part of icon
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		String thrinput = config.get3rdIconValues();
		String[] thrvalues = thrinput.split(", ");

		int thrx = Integer.parseInt(thrvalues[0]);
		int thry = Integer.parseInt(thrvalues[1]);
		int thrwidth = Integer.parseInt(thrvalues[2]);
		int thrheight = Integer.parseInt(thrvalues[3]);

		String thrinputSpr = config.get3rdIconValuesSprite();
		String[] thrvaluesSpr = thrinputSpr.split(", ");

		int thrxSpr = Integer.parseInt(thrvaluesSpr[0]);
		int thrySpr = Integer.parseInt(thrvaluesSpr[1]);
		int thrwidthSpr = Integer.parseInt(thrvaluesSpr[2]);
		int thrheightSpr = Integer.parseInt(thrvaluesSpr[3]);

		if (thirdwidgetid != -1)
		{
				final Widget ChatBar3rdParent = client.getWidget(DisplayIconChoicesParent3rd);

				if (ChatBar3rdParent != null) {

					if (chatbuttonhidecheck == 0) {
						ChatBar3rdParent.setRelativeY(thry);
						ChatBar3rdParent.setRelativeX(thrx);
						ChatBar3rdParent.setWidth(thrwidth);
						ChatBar3rdParent.setHeight(thrheight);
					}


				final Widget ChatBar3rdParentSprite = client.getWidget(DisplayIconChoicesSprite3rd);

				if (ChatBar3rdParentSprite != null) {

					if (chatbuttonhidecheck == 0) {
						ChatBar3rdParentSprite.setRelativeX(thrxSpr);
						ChatBar3rdParentSprite.setRelativeY(thrySpr);
						ChatBar3rdParentSprite.setWidth(thrwidthSpr);
						ChatBar3rdParentSprite.setHeight(thrheightSpr);

					}


				}

				final Widget ChatBar3rdParentText = client.getWidget(DisplayIconChoicesText3rd);
				final Widget ChatBar3rdParentTextAlt = client.getWidget(DisplayIconChoicesText3rdAlt);
				if (ChatBar3rdParentText != null) {

					if (chatbuttonhidecheck == 0) {
						ChatBar3rdParentText.setRelativeY(-debugheight);
						if (DisplayIconChoicesText3rdAlt != -1)
						{
							ChatBar3rdParentTextAlt.setRelativeY(-debugheight);
						}
					}


				}

			}

		}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// 4th part of icon
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		String forinput = config.get4thIconValues();
		String[] forvalues = forinput.split(", ");

		int forx = Integer.parseInt(forvalues[0]);
		int fory = Integer.parseInt(forvalues[1]);
		int forwidth = Integer.parseInt(forvalues[2]);
		int forheight = Integer.parseInt(forvalues[3]);

		String forinputSpr = config.get4thIconValuesSprite();
		String[] forvaluesSpr = forinputSpr.split(", ");

		int forxSpr = Integer.parseInt(forvaluesSpr[0]);
		int forySpr = Integer.parseInt(forvaluesSpr[1]);
		int forwidthSpr = Integer.parseInt(forvaluesSpr[2]);
		int forheightSpr = Integer.parseInt(forvaluesSpr[3]);

		if (fourthwidgetid != -1)
		{
				final Widget ChatBar4thParent = client.getWidget(DisplayIconChoicesParent4th);

				if (ChatBar4thParent != null) {

					if (chatbuttonhidecheck == 0) {
						ChatBar4thParent.setRelativeY(fory);
						ChatBar4thParent.setRelativeX(forx);
						ChatBar4thParent.setWidth(forwidth);
						ChatBar4thParent.setHeight(forheight);
					}


				final Widget ChatBar4thParentSprite = client.getWidget(DisplayIconChoicesSprite4th);

				if (ChatBar4thParentSprite != null) {

					if (chatbuttonhidecheck == 0) {
						ChatBar4thParentSprite.setRelativeX(forxSpr);
						ChatBar4thParentSprite.setRelativeY(forySpr);
						ChatBar4thParentSprite.setWidth(forwidthSpr);
						ChatBar4thParentSprite.setHeight(forheightSpr);

					}


				}

				final Widget ChatBar4thParentText = client.getWidget(DisplayIconChoicesText4th);
				final Widget ChatBar4thParentTextAlt = client.getWidget(DisplayIconChoicesText4thAlt);
				if (ChatBar4thParentText != null) {

					if (chatbuttonhidecheck == 0) {
						ChatBar4thParentText.setRelativeY(-debugheight);
						if (DisplayIconChoicesText4thAlt != -1)
						{
							ChatBar4thParentTextAlt.setRelativeY(-debugheight);
						}
					}


				}

			}

		}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// 5th part of icon
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		String fivinput = config.get5thIconValues();
		String[] fivvalues = fivinput.split(", ");

		int fivx = Integer.parseInt(fivvalues[0]);
		int fivy = Integer.parseInt(fivvalues[1]);
		int fivwidth = Integer.parseInt(fivvalues[2]);
		int fivheight = Integer.parseInt(fivvalues[3]);

		String fivinputSpr = config.get5thIconValuesSprite();
		String[] fivvaluesSpr = fivinputSpr.split(", ");

		int fivxSpr = Integer.parseInt(fivvaluesSpr[0]);
		int fivySpr = Integer.parseInt(fivvaluesSpr[1]);
		int fivwidthSpr = Integer.parseInt(fivvaluesSpr[2]);
		int fivheightSpr = Integer.parseInt(fivvaluesSpr[3]);

		if (fifthwidgetid != -1)
		{
				final Widget ChatBar5thParent = client.getWidget(DisplayIconChoicesParent5th);

				if (ChatBar5thParent != null) {

					if (chatbuttonhidecheck == 0) {
						ChatBar5thParent.setRelativeY(fivy);
						ChatBar5thParent.setRelativeX(fivx);
						ChatBar5thParent.setWidth(fivwidth);
						ChatBar5thParent.setHeight(fivheight);
					}


				final Widget ChatBar5thParentSprite = client.getWidget(DisplayIconChoicesSprite5th);

				if (ChatBar5thParentSprite != null) {

					if (chatbuttonhidecheck == 0) {
						ChatBar5thParentSprite.setRelativeX(fivxSpr);
						ChatBar5thParentSprite.setRelativeY(fivySpr);
						ChatBar5thParentSprite.setWidth(fivwidthSpr);
						ChatBar5thParentSprite.setHeight(fivheightSpr);

					}


				}

				final Widget ChatBar5thParentText = client.getWidget(DisplayIconChoicesText5th);
				final Widget ChatBar5thParentTextAlt = client.getWidget(DisplayIconChoicesText5thAlt);
				if (ChatBar5thParentText != null) {

					if (chatbuttonhidecheck == 0) {
						ChatBar5thParentText.setRelativeY(-debugheight);
						if (DisplayIconChoicesText5thAlt != -1)
						{
							ChatBar5thParentTextAlt.setRelativeY(-debugheight);
						}
					}


				}

			}

		}


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// 6th part of icon
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		String sixinput = config.get6thIconValues();
		String[] sixvalues = sixinput.split(", ");

		int sixx = Integer.parseInt(sixvalues[0]);
		int sixy = Integer.parseInt(sixvalues[1]);
		int sixwidth = Integer.parseInt(sixvalues[2]);
		int sixheight = Integer.parseInt(sixvalues[3]);

		String sixinputSpr = config.get6thIconValuesSprite();
		String[] sixvaluesSpr = sixinputSpr.split(", ");

		int sixxSpr = Integer.parseInt(sixvaluesSpr[0]);
		int sixySpr = Integer.parseInt(sixvaluesSpr[1]);
		int sixwidthSpr = Integer.parseInt(sixvaluesSpr[2]);
		int sixheightSpr = Integer.parseInt(sixvaluesSpr[3]);

		if (sixthwidgetid != -1)
		{
				final Widget ChatBar6thParent = client.getWidget(DisplayIconChoicesParent6th);

				if (ChatBar6thParent != null) {

					if (chatbuttonhidecheck == 0) {
						ChatBar6thParent.setRelativeY(sixy);
						ChatBar6thParent.setRelativeX(sixx);
						ChatBar6thParent.setWidth(sixwidth);
						ChatBar6thParent.setHeight(sixheight);
					}


				final Widget ChatBar6thParentSprite = client.getWidget(DisplayIconChoicesSprite6th);

				if (ChatBar6thParentSprite != null) {

					if (chatbuttonhidecheck == 0) {
						ChatBar6thParentSprite.setRelativeX(sixxSpr);
						ChatBar6thParentSprite.setRelativeY(sixySpr);
						ChatBar6thParentSprite.setWidth(sixwidthSpr);
						ChatBar6thParentSprite.setHeight(sixheightSpr);

					}


				}

				final Widget ChatBar6thParentText = client.getWidget(DisplayIconChoicesText6th);
				final Widget ChatBar6thParentTextAlt = client.getWidget(DisplayIconChoicesText6thAlt);
				if (ChatBar6thParentText != null) {

					if (chatbuttonhidecheck == 0) {
						ChatBar6thParentText.setRelativeY(-debugheight);
						if (DisplayIconChoicesText6thAlt != -1)
						{
							ChatBar6thParentTextAlt.setRelativeY(-debugheight);
						}
					}


				}

			}

		}


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// 7th part of icon
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		String sevinput = config.get7thIconValues();
		String[] sevvalues = sevinput.split(", ");

		int sevx = Integer.parseInt(sevvalues[0]);
		int sevy = Integer.parseInt(sevvalues[1]);
		int sevwidth = Integer.parseInt(sevvalues[2]);
		int sevheight = Integer.parseInt(sevvalues[3]);

		String sevinputSpr = config.get7thIconValuesSprite();
		String[] sevvaluesSpr = sevinputSpr.split(", ");

		int sevxSpr = Integer.parseInt(sevvaluesSpr[0]);
		int sevySpr = Integer.parseInt(sevvaluesSpr[1]);
		int sevwidthSpr = Integer.parseInt(sevvaluesSpr[2]);
		int sevheightSpr = Integer.parseInt(sevvaluesSpr[3]);

		if (seventhwidgetid != -1)
		{
				final Widget ChatBar7thParent = client.getWidget(DisplayIconChoicesParent7th);

				if (ChatBar7thParent != null) {

					if (chatbuttonhidecheck == 0) {
						ChatBar7thParent.setRelativeY(sevy);
						ChatBar7thParent.setRelativeX(sevx);
						ChatBar7thParent.setWidth(sevwidth);
						ChatBar7thParent.setHeight(sevheight);
					}


				final Widget ChatBar7thParentSprite = client.getWidget(DisplayIconChoicesSprite7th);

				if (ChatBar7thParentSprite != null) {

					if (chatbuttonhidecheck == 0) {
						ChatBar7thParentSprite.setRelativeX(sevxSpr);
						ChatBar7thParentSprite.setRelativeY(sevySpr);
						ChatBar7thParentSprite.setWidth(sevwidthSpr);
						ChatBar7thParentSprite.setHeight(sevheightSpr);

					}


				}

				final Widget ChatBar7thParentText = client.getWidget(DisplayIconChoicesText7th);
				final Widget ChatBar7thParentTextAlt = client.getWidget(DisplayIconChoicesText7thAlt);
				if (ChatBar7thParentText != null) {

					if (chatbuttonhidecheck == 0) {
						ChatBar7thParentText.setRelativeY(-debugheight);
						if (DisplayIconChoicesText7thAlt != -1)
						{
							ChatBar7thParentTextAlt.setRelativeY(-debugheight);
						}
					}


				}

			}

		}


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// 8th part of icon
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		String eiginput = config.get8thIconValues();
		String[] eigvalues = eiginput.split(", ");

		int eigx = Integer.parseInt(eigvalues[0]);
		int eigy = Integer.parseInt(eigvalues[1]);
		int eigwidth = Integer.parseInt(eigvalues[2]);
		int eigheight = Integer.parseInt(eigvalues[3]);

		String eiginputSpr = config.get8thIconValuesSprite();
		String[] eigvaluesSpr = eiginputSpr.split(", ");

		int eigxSpr = Integer.parseInt(eigvaluesSpr[0]);
		int eigySpr = Integer.parseInt(eigvaluesSpr[1]);
		int eigwidthSpr = Integer.parseInt(eigvaluesSpr[2]);
		int eigheightSpr = Integer.parseInt(eigvaluesSpr[3]);

		if (eighthwidgetid != -1)
		{
				final Widget ChatBar8thParent = client.getWidget(DisplayIconChoicesParent8th);

				if (ChatBar8thParent != null) {

					if (chatbuttonhidecheck == 0) {
						ChatBar8thParent.setRelativeY(eigy);
						ChatBar8thParent.setRelativeX(eigx);
						ChatBar8thParent.setWidth(eigwidth);
						ChatBar8thParent.setHeight(eigheight);
					}


				final Widget ChatBar8thParentSprite = client.getWidget(DisplayIconChoicesSprite8th);

				if (ChatBar8thParentSprite != null) {

					if (chatbuttonhidecheck == 0) {
						ChatBar8thParentSprite.setRelativeX(eigxSpr);
						ChatBar8thParentSprite.setRelativeY(eigySpr);
						ChatBar8thParentSprite.setWidth(eigwidthSpr);
						ChatBar8thParentSprite.setHeight(eigheightSpr);

					}


				}

				final Widget ChatBar8thParentText = client.getWidget(DisplayIconChoicesText8th);
				final Widget ChatBar8thParentTextAlt = client.getWidget(DisplayIconChoicesText8thAlt);
				if (ChatBar8thParentText != null) {

					if (chatbuttonhidecheck == 0) {
						ChatBar8thParentText.setRelativeY(-debugheight);
						if (DisplayIconChoicesText8thAlt != -1)
						{
							/// need to make not invis debugheight
							ChatBar8thParentTextAlt.setRelativeY(-debugheight);
						}
					}


				}

			}

		}




//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/// sets buttons to be out of bounds when chat is closed

		final int[] widgetIds = { 10616836, 10616839, 10616843, 10616847, 10616851, 10616855, 10616859, 10616863 };

		for (int widgetId : widgetIds) {
			Widget chatParentWidget = client.getWidget(widgetId);

			if ((mainwidgetid != widgetId)
					&& (secondwidgetid != widgetId) && (thirdwidgetid != widgetId) && (fourthwidgetid != widgetId) && (fifthwidgetid != widgetId) && (sixthwidgetid != widgetId) && (seventhwidgetid != widgetId) && (eighthwidgetid != widgetId)) {

				if (chatParentWidget != null) {

					if (chatbuttonhidecheck == 0) {
						chatParentWidget.setRelativeY(-debugheight);
						/// if widget id = game id then do game text and changes
					}

				}

			}
		}


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		// get parent widgets
		final Widget ChatParentWidgetAll = client.getWidget(10616836);
		final Widget ChatSpriteWidgetAll = client.getWidget(10616837);//
		final Widget ChatTextWidgetAll = client.getWidget(10616838); //

		final Widget ChatParentWidgetGame = client.getWidget(10616839);//
		final Widget ChatSpriteWidgetGame = client.getWidget(10616840);
		final Widget ChatTextWidgetGame = client.getWidget(10616841);
		final Widget ChatText2ndWidgetGame = client.getWidget(10616842);

		final Widget ChatParentWidgetPublic = client.getWidget(10616843);
		final Widget ChatSpriteWidgetPublic = client.getWidget(10616844);
		final Widget ChatTextWidgetPublic = client.getWidget(10616845);
		final Widget ChatText2ndWidgetPublic = client.getWidget(10616846);

		final Widget ChatParentWidgetPrivate = client.getWidget(10616847);
		final Widget ChatSpriteWidgetPrivate = client.getWidget(10616848);
		final Widget ChatTextWidgetPrivate = client.getWidget(10616849);
		final Widget ChatText2ndWidgetPrivate = client.getWidget(10616850);

		final Widget ChatParentWidgetChannel = client.getWidget(10616851);
		final Widget ChatSpriteWidgetChannel = client.getWidget(10616852);
		final Widget ChatTextWidgetChannel = client.getWidget(10616853);
		final Widget ChatText2ndWidgetChannel = client.getWidget(10616854);

		final Widget ChatParentWidgetClan = client.getWidget(10616855);
		final Widget ChatSpriteWidgetClan = client.getWidget(10616856);
		final Widget ChatTextWidgetClan = client.getWidget(10616857);
		final Widget ChatText2ndWidgetClan = client.getWidget(10616858);

		final Widget ChatParentWidgetTrade = client.getWidget(10616859);
		final Widget ChatSpriteWidgetTrade = client.getWidget(10616860);
		final Widget ChatTextWidgetTrade = client.getWidget(10616861);
		final Widget ChatText2ndWidgetTrade = client.getWidget(10616862);

		final Widget ChatParentWidgetReport = client.getWidget(10616863);
		final Widget ChatSpriteWidgetReport = client.getWidget(10616864);
		final Widget ChatTextWidgetReport = client.getWidget(10616865);


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// ALL button area
		// get strings

		String inputALL = config.getALLIconValues();
		String[] valuesALL = inputALL.split(", ");

		int ALLx = Integer.parseInt(valuesALL[0]);
		int ALLy = Integer.parseInt(valuesALL[1]);
		int ALLwidth = Integer.parseInt(valuesALL[2]);
		int ALLheight = Integer.parseInt(valuesALL[3]);

		String inputALLspr = config.getALLIconValuesSprite();
		String[] valuesALLspr = inputALLspr.split(", ");

		int ALLxspr = Integer.parseInt(valuesALLspr[0]);
		int ALLyspr = Integer.parseInt(valuesALLspr[1]);
		int ALLwidthspr = Integer.parseInt(valuesALLspr[2]);
		int ALLheightspr = Integer.parseInt(valuesALLspr[3]);

		String inputALLtxt = config.getALLIconText();
		String[] valuesALLtxt = inputALLtxt.split(", ");

		String ALLdeftxt = String.valueOf(valuesALLtxt[0]);
		int ALLxtxt = Integer.parseInt(valuesALLtxt[1]);
		int ALLytxt = Integer.parseInt(valuesALLtxt[2]);


		// opened chat area

		/// parent collision
		if (ChatParentWidgetAll != null) {

			if (chatbuttonhidecheck == 1) {

				ChatParentWidgetAll.setRelativeX(ALLx);
				ChatParentWidgetAll.setRelativeY(ALLy);
				ChatParentWidgetAll.setWidth(ALLwidth);
				ChatParentWidgetAll.setHeight(ALLheight);

			}

		}
		/// sprite
		if (ChatSpriteWidgetAll != null) {

			if (chatbuttonhidecheck == 1) {

				ChatSpriteWidgetAll.setRelativeX(ALLxspr);
				ChatSpriteWidgetAll.setRelativeY(ALLyspr);
				ChatSpriteWidgetAll.setWidth(ALLwidthspr);
				ChatSpriteWidgetAll.setHeight(ALLheightspr);

			}

		}
		/// txt
		if (ChatTextWidgetAll != null) {

			if (chatbuttonhidecheck == 1) {

				ChatTextWidgetAll.setText(String.valueOf(ALLdeftxt));
				ChatTextWidgetAll.setRelativeX(ALLxtxt);
				ChatTextWidgetAll.setRelativeY(ALLytxt);


			}

		}

		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// REPORT button area
// get strings

		String inputREPORT = config.getREPORTIconValues();
		String[] valuesREPORT = inputREPORT.split(", ");

		int REPORTx = Integer.parseInt(valuesREPORT[0]);
		int REPORTy = Integer.parseInt(valuesREPORT[1]);
		int REPORTwidth = Integer.parseInt(valuesREPORT[2]);
		int REPORTheight = Integer.parseInt(valuesREPORT[3]);

		String inputREPORTspr = config.getREPORTIconValuesSprite();
		String[] valuesREPORTspr = inputREPORTspr.split(", ");

		int REPORTxspr = Integer.parseInt(valuesREPORTspr[0]);
		int REPORTyspr = Integer.parseInt(valuesREPORTspr[1]);
		int REPORTwidthspr = Integer.parseInt(valuesREPORTspr[2]);
		int REPORTheightspr = Integer.parseInt(valuesREPORTspr[3]);

		String inputREPORTtxt = config.getREPORTIconText();
		String[] valuesREPORTtxt = inputREPORTtxt.split(", ");

		String REPORTdeftxt = String.valueOf(valuesREPORTtxt[0]);
		int REPORTxtxt = Integer.parseInt(valuesREPORTtxt[1]);
		int REPORTytxt = Integer.parseInt(valuesREPORTtxt[2]);
		String REPORTtxtenable = String.valueOf(valuesREPORTtxt[3]);


// opened chat area

/// parent collision
		if (ChatParentWidgetReport != null) {

			if (chatbuttonhidecheck == 1) {

				ChatParentWidgetReport.setRelativeX(REPORTx);
				ChatParentWidgetReport.setRelativeY(REPORTy);
				ChatParentWidgetReport.setWidth(REPORTwidth);
				ChatParentWidgetReport.setHeight(REPORTheight);

			}

		}
/// sprite
		if (ChatSpriteWidgetReport != null) {

			if (chatbuttonhidecheck == 1) {

				ChatSpriteWidgetReport.setRelativeX(REPORTxspr);
				ChatSpriteWidgetReport.setRelativeY(REPORTyspr);
				ChatSpriteWidgetReport.setWidth(REPORTwidthspr);
				ChatSpriteWidgetReport.setHeight(REPORTheightspr);

			}

		}
/// txt
		if (ChatTextWidgetReport != null) {

			if (chatbuttonhidecheck == 1) {
				if (REPORTtxtenable == "true")
				{
					ChatTextWidgetReport.setText(String.valueOf(REPORTdeftxt));
				}
				ChatTextWidgetReport.setRelativeX(REPORTxtxt);
				ChatTextWidgetReport.setRelativeY(REPORTytxt);


			}

		}


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// GAME button area
		// get strings
		String inputGAME = config.getGAMEIconValues();
		String[] valuesGAME = inputGAME.split(", ");

		int GAMEx = Integer.parseInt(valuesGAME[0]);
		int GAMEy = Integer.parseInt(valuesGAME[1]);
		int GAMEwidth = Integer.parseInt(valuesGAME[2]);
		int GAMEheight = Integer.parseInt(valuesGAME[3]);

		String inputGAMEspr = config.getGAMEIconValuesSprite();
		String[] valuesGAMEspr = inputGAMEspr.split(", ");

		int GAMExspr = Integer.parseInt(valuesGAMEspr[0]);
		int GAMEyspr = Integer.parseInt(valuesGAMEspr[1]);
		int GAMEwidthspr = Integer.parseInt(valuesGAMEspr[2]);
		int GAMEheightspr = Integer.parseInt(valuesGAMEspr[3]);

		String inputGAMEtxt = config.getGAMEIconText();
		String[] valuesGAMEtxt = inputGAMEtxt.split(", ");

		String GAMEdeftxt = String.valueOf(valuesGAMEtxt[0]);
		int GAMExtxt = Integer.parseInt(valuesGAMEtxt[1]);
		int GAMEytxt = Integer.parseInt(valuesGAMEtxt[2]);
		int GAMExtxt2nd = Integer.parseInt(valuesGAMEtxt[3]);
		int GAMEytxt2nd = Integer.parseInt(valuesGAMEtxt[4]);

// opened chat area

/// parent collision
		if (ChatParentWidgetGame != null) {

			if (chatbuttonhidecheck == 1) {

				ChatParentWidgetGame.setRelativeX(GAMEx);
				ChatParentWidgetGame.setRelativeY(GAMEy);
				ChatParentWidgetGame.setWidth(GAMEwidth);
				ChatParentWidgetGame.setHeight(GAMEheight);

			}

		}
/// sprite
		if (ChatSpriteWidgetGame != null) {

			if (chatbuttonhidecheck == 1) {

				ChatSpriteWidgetGame.setRelativeX(GAMExspr);
				ChatSpriteWidgetGame.setRelativeY(GAMEyspr);
				ChatSpriteWidgetGame.setWidth(GAMEwidthspr);
				ChatSpriteWidgetGame.setHeight(GAMEheightspr);

			}

		}
/// txt
		if (ChatTextWidgetGame != null) {

			if (chatbuttonhidecheck == 1) {

				ChatTextWidgetGame.setText(String.valueOf(GAMEdeftxt));
				ChatTextWidgetGame.setRelativeX(GAMExtxt);
				ChatTextWidgetGame.setRelativeY(GAMEytxt);

			}

		}
/// 2nd txt
		if (ChatText2ndWidgetGame != null) {

			if (chatbuttonhidecheck == 1) {


				ChatText2ndWidgetGame.setRelativeX(GAMExtxt2nd);
				ChatText2ndWidgetGame.setRelativeY(GAMEytxt2nd);

			}

		}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// PUBLIC button area
// get strings
		String inputPUBLIC = config.getPUBLICIconValues();
		String[] valuesPUBLIC = inputPUBLIC.split(", ");

		int PUBLICx = Integer.parseInt(valuesPUBLIC[0]);
		int PUBLICy = Integer.parseInt(valuesPUBLIC[1]);
		int PUBLICwidth = Integer.parseInt(valuesPUBLIC[2]);
		int PUBLICheight = Integer.parseInt(valuesPUBLIC[3]);

		String inputPUBLICspr = config.getPUBLICIconValuesSprite();
		String[] valuesPUBLICspr = inputPUBLICspr.split(", ");

		int PUBLICxspr = Integer.parseInt(valuesPUBLICspr[0]);
		int PUBLICyspr = Integer.parseInt(valuesPUBLICspr[1]);
		int PUBLICwidthspr = Integer.parseInt(valuesPUBLICspr[2]);
		int PUBLICheightspr = Integer.parseInt(valuesPUBLICspr[3]);

		String inputPUBLICtxt = config.getPUBLICIconText();
		String[] valuesPUBLICtxt = inputPUBLICtxt.split(", ");

		String PUBLICdeftxt = String.valueOf(valuesPUBLICtxt[0]);
		int PUBLICxtxt = Integer.parseInt(valuesPUBLICtxt[1]);
		int PUBLICytxt = Integer.parseInt(valuesPUBLICtxt[2]);
		int PUBLICxtxt2nd = Integer.parseInt(valuesPUBLICtxt[3]);
		int PUBLICytxt2nd = Integer.parseInt(valuesPUBLICtxt[4]);

// opened chat area

/// parent collision
		if (ChatParentWidgetPublic != null) {

			if (chatbuttonhidecheck == 1) {

				ChatParentWidgetPublic.setRelativeX(PUBLICx);
				ChatParentWidgetPublic.setRelativeY(PUBLICy);
				ChatParentWidgetPublic.setWidth(PUBLICwidth);
				ChatParentWidgetPublic.setHeight(PUBLICheight);

			}

		}
/// sprite
		if (ChatSpriteWidgetPublic != null) {

			if (chatbuttonhidecheck == 1) {

				ChatSpriteWidgetPublic.setRelativeX(PUBLICxspr);
				ChatSpriteWidgetPublic.setRelativeY(PUBLICyspr);
				ChatSpriteWidgetPublic.setWidth(PUBLICwidthspr);
				ChatSpriteWidgetPublic.setHeight(PUBLICheightspr);

			}

		}
/// txt
		if (ChatTextWidgetPublic != null) {

			if (chatbuttonhidecheck == 1) {

				ChatTextWidgetPublic.setText(String.valueOf(PUBLICdeftxt));
				ChatTextWidgetPublic.setRelativeX(PUBLICxtxt);
				ChatTextWidgetPublic.setRelativeY(PUBLICytxt);

			}

		}
/// 2nd txt
		if (ChatText2ndWidgetPublic != null) {

			if (chatbuttonhidecheck == 1) {

				ChatText2ndWidgetPublic.setRelativeX(PUBLICxtxt2nd);
				ChatText2ndWidgetPublic.setRelativeY(PUBLICytxt2nd);

			}

		}


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// PRIVATE button area
// get strings
		String inputPRIVATE = config.getPRIVATEIconValues();
		String[] valuesPRIVATE = inputPRIVATE.split(", ");

		int PRIVATEx = Integer.parseInt(valuesPRIVATE[0]);
		int PRIVATEy = Integer.parseInt(valuesPRIVATE[1]);
		int PRIVATEwidth = Integer.parseInt(valuesPRIVATE[2]);
		int PRIVATEheight = Integer.parseInt(valuesPRIVATE[3]);

		String inputPRIVATEspr = config.getPRIVATEIconValuesSprite();
		String[] valuesPRIVATEspr = inputPRIVATEspr.split(", ");

		int PRIVATExspr = Integer.parseInt(valuesPRIVATEspr[0]);
		int PRIVATEyspr = Integer.parseInt(valuesPRIVATEspr[1]);
		int PRIVATEwidthspr = Integer.parseInt(valuesPRIVATEspr[2]);
		int PRIVATEheightspr = Integer.parseInt(valuesPRIVATEspr[3]);

		String inputPRIVATEtxt = config.getPRIVATEIconText();
		String[] valuesPRIVATEtxt = inputPRIVATEtxt.split(", ");

		String PRIVATEdeftxt = String.valueOf(valuesPRIVATEtxt[0]);
		int PRIVATExtxt = Integer.parseInt(valuesPRIVATEtxt[1]);
		int PRIVATEytxt = Integer.parseInt(valuesPRIVATEtxt[2]);
		int PRIVATExtxt2nd = Integer.parseInt(valuesPRIVATEtxt[3]);
		int PRIVATEytxt2nd = Integer.parseInt(valuesPRIVATEtxt[4]);

// opened chat area

/// parent collision
		if (ChatParentWidgetPrivate != null) {

			if (chatbuttonhidecheck == 1) {

				ChatParentWidgetPrivate.setRelativeX(PRIVATEx);
				ChatParentWidgetPrivate.setRelativeY(PRIVATEy);
				ChatParentWidgetPrivate.setWidth(PRIVATEwidth);
				ChatParentWidgetPrivate.setHeight(PRIVATEheight);

			}

		}
/// sprite
		if (ChatSpriteWidgetPrivate != null) {

			if (chatbuttonhidecheck == 1) {

				ChatSpriteWidgetPrivate.setRelativeX(PRIVATExspr);
				ChatSpriteWidgetPrivate.setRelativeY(PRIVATEyspr);
				ChatSpriteWidgetPrivate.setWidth(PRIVATEwidthspr);
				ChatSpriteWidgetPrivate.setHeight(PRIVATEheightspr);

			}

		}
/// txt
		if (ChatTextWidgetPrivate != null) {

			if (chatbuttonhidecheck == 1) {

				ChatTextWidgetPrivate.setText(String.valueOf(PRIVATEdeftxt));
				ChatTextWidgetPrivate.setRelativeX(PRIVATExtxt);
				ChatTextWidgetPrivate.setRelativeY(PRIVATEytxt);

			}

		}
/// 2nd txt
		if (ChatText2ndWidgetPrivate != null) {

			if (chatbuttonhidecheck == 1) {

				ChatText2ndWidgetPrivate.setRelativeX(PRIVATExtxt2nd);
				ChatText2ndWidgetPrivate.setRelativeY(PRIVATEytxt2nd);

			}

		}

		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// CHANNEL button area
// get strings
		String inputCHANNEL = config.getCHANNELIconValues();
		String[] valuesCHANNEL = inputCHANNEL.split(", ");

		int CHANNELx = Integer.parseInt(valuesCHANNEL[0]);
		int CHANNELy = Integer.parseInt(valuesCHANNEL[1]);
		int CHANNELwidth = Integer.parseInt(valuesCHANNEL[2]);
		int CHANNELheight = Integer.parseInt(valuesCHANNEL[3]);

		String inputCHANNELspr = config.getCHANNELIconValuesSprite();
		String[] valuesCHANNELspr = inputCHANNELspr.split(", ");

		int CHANNELxspr = Integer.parseInt(valuesCHANNELspr[0]);
		int CHANNELyspr = Integer.parseInt(valuesCHANNELspr[1]);
		int CHANNELwidthspr = Integer.parseInt(valuesCHANNELspr[2]);
		int CHANNELheightspr = Integer.parseInt(valuesCHANNELspr[3]);

		String inputCHANNELtxt = config.getCHANNELIconText();
		String[] valuesCHANNELtxt = inputCHANNELtxt.split(", ");

		String CHANNELdeftxt = String.valueOf(valuesCHANNELtxt[0]);
		int CHANNELxtxt = Integer.parseInt(valuesCHANNELtxt[1]);
		int CHANNELytxt = Integer.parseInt(valuesCHANNELtxt[2]);
		int CHANNELxtxt2nd = Integer.parseInt(valuesCHANNELtxt[3]);
		int CHANNELytxt2nd = Integer.parseInt(valuesCHANNELtxt[4]);

// opened chat area

/// parent collision
		if (ChatParentWidgetChannel != null) {

			if (chatbuttonhidecheck == 1) {

				ChatParentWidgetChannel.setRelativeX(CHANNELx);
				ChatParentWidgetChannel.setRelativeY(CHANNELy);
				ChatParentWidgetChannel.setWidth(CHANNELwidth);
				ChatParentWidgetChannel.setHeight(CHANNELheight);

			}

		}
/// sprite
		if (ChatSpriteWidgetChannel != null) {

			if (chatbuttonhidecheck == 1) {

				ChatSpriteWidgetChannel.setRelativeX(CHANNELxspr);
				ChatSpriteWidgetChannel.setRelativeY(CHANNELyspr);
				ChatSpriteWidgetChannel.setWidth(CHANNELwidthspr);
				ChatSpriteWidgetChannel.setHeight(CHANNELheightspr);

			}

		}
/// txt
		if (ChatTextWidgetChannel != null) {

			if (chatbuttonhidecheck == 1) {

				ChatTextWidgetChannel.setText(String.valueOf(CHANNELdeftxt));
				ChatTextWidgetChannel.setRelativeX(CHANNELxtxt);
				ChatTextWidgetChannel.setRelativeY(CHANNELytxt);

			}

		}
/// 2nd txt
		if (ChatText2ndWidgetChannel != null) {

			if (chatbuttonhidecheck == 1) {

				ChatText2ndWidgetChannel.setRelativeX(CHANNELxtxt2nd);
				ChatText2ndWidgetChannel.setRelativeY(CHANNELytxt2nd);

			}

		}

		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// CLAN button area
// get strings
		String inputCLAN = config.getCLANIconValues();
		String[] valuesCLAN = inputCLAN.split(", ");

		int CLANx = Integer.parseInt(valuesCLAN[0]);
		int CLANy = Integer.parseInt(valuesCLAN[1]);
		int CLANwidth = Integer.parseInt(valuesCLAN[2]);
		int CLANheight = Integer.parseInt(valuesCLAN[3]);

		String inputCLANspr = config.getCLANIconValuesSprite();
		String[] valuesCLANspr = inputCLANspr.split(", ");

		int CLANxspr = Integer.parseInt(valuesCLANspr[0]);
		int CLANyspr = Integer.parseInt(valuesCLANspr[1]);
		int CLANwidthspr = Integer.parseInt(valuesCLANspr[2]);
		int CLANheightspr = Integer.parseInt(valuesCLANspr[3]);

		String inputCLANtxt = config.getCLANIconText();
		String[] valuesCLANtxt = inputCLANtxt.split(", ");

		String CLANdeftxt = String.valueOf(valuesCLANtxt[0]);
		int CLANxtxt = Integer.parseInt(valuesCLANtxt[1]);
		int CLANytxt = Integer.parseInt(valuesCLANtxt[2]);
		int CLANxtxt2nd = Integer.parseInt(valuesCLANtxt[3]);
		int CLANytxt2nd = Integer.parseInt(valuesCLANtxt[4]);

// opened chat area

/// parent collision
		if (ChatParentWidgetClan != null) {

			if (chatbuttonhidecheck == 1) {

				ChatParentWidgetClan.setRelativeX(CLANx);
				ChatParentWidgetClan.setRelativeY(CLANy);
				ChatParentWidgetClan.setWidth(CLANwidth);
				ChatParentWidgetClan.setHeight(CLANheight);

			}

		}
/// sprite
		if (ChatSpriteWidgetClan != null) {

			if (chatbuttonhidecheck == 1) {

				ChatSpriteWidgetClan.setRelativeX(CLANxspr);
				ChatSpriteWidgetClan.setRelativeY(CLANyspr);
				ChatSpriteWidgetClan.setWidth(CLANwidthspr);
				ChatSpriteWidgetClan.setHeight(CLANheightspr);

			}

		}
/// txt
		if (ChatTextWidgetClan != null) {

			if (chatbuttonhidecheck == 1) {

				ChatTextWidgetClan.setText(String.valueOf(CLANdeftxt));
				ChatTextWidgetClan.setRelativeX(CLANxtxt);
				ChatTextWidgetClan.setRelativeY(CLANytxt);

			}

		}
/// 2nd txt
		if (ChatText2ndWidgetClan != null) {

			if (chatbuttonhidecheck == 1) {

				ChatText2ndWidgetClan.setRelativeX(CLANxtxt2nd);
				ChatText2ndWidgetClan.setRelativeY(CLANytxt2nd);

			}

		}

		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// TRADE button area
// get strings
		String inputTRADE = config.getTRADEIconValues();
		String[] valuesTRADE = inputTRADE.split(", ");

		int TRADEx = Integer.parseInt(valuesTRADE[0]);
		int TRADEy = Integer.parseInt(valuesTRADE[1]);
		int TRADEwidth = Integer.parseInt(valuesTRADE[2]);
		int TRADEheight = Integer.parseInt(valuesTRADE[3]);

		String inputTRADEspr = config.getTRADEIconValuesSprite();
		String[] valuesTRADEspr = inputTRADEspr.split(", ");

		int TRADExspr = Integer.parseInt(valuesTRADEspr[0]);
		int TRADEyspr = Integer.parseInt(valuesTRADEspr[1]);
		int TRADEwidthspr = Integer.parseInt(valuesTRADEspr[2]);
		int TRADEheightspr = Integer.parseInt(valuesTRADEspr[3]);

		String inputTRADEtxt = config.getTRADEIconText();
		String[] valuesTRADEtxt = inputTRADEtxt.split(", ");

		String TRADEdeftxt = String.valueOf(valuesTRADEtxt[0]);
		int TRADExtxt = Integer.parseInt(valuesTRADEtxt[1]);
		int TRADEytxt = Integer.parseInt(valuesTRADEtxt[2]);
		int TRADExtxt2nd = Integer.parseInt(valuesTRADEtxt[3]);
		int TRADEytxt2nd = Integer.parseInt(valuesTRADEtxt[4]);

// opened chat area

/// parent collision
		if (ChatParentWidgetTrade != null) {

			if (chatbuttonhidecheck == 1) {

				ChatParentWidgetTrade.setRelativeX(TRADEx);
				ChatParentWidgetTrade.setRelativeY(TRADEy);
				ChatParentWidgetTrade.setWidth(TRADEwidth);
				ChatParentWidgetTrade.setHeight(TRADEheight);

			}

		}
/// sprite
		if (ChatSpriteWidgetTrade != null) {

			if (chatbuttonhidecheck == 1) {

				ChatSpriteWidgetTrade.setRelativeX(TRADExspr);
				ChatSpriteWidgetTrade.setRelativeY(TRADEyspr);
				ChatSpriteWidgetTrade.setWidth(TRADEwidthspr);
				ChatSpriteWidgetTrade.setHeight(TRADEheightspr);

			}

		}
/// txt
		if (ChatTextWidgetTrade != null) {

			if (chatbuttonhidecheck == 1) {

				ChatTextWidgetTrade.setText(String.valueOf(TRADEdeftxt));
				ChatTextWidgetTrade.setRelativeX(TRADExtxt);
				ChatTextWidgetTrade.setRelativeY(TRADEytxt);

			}

		}
/// 2nd txt
		if (ChatText2ndWidgetTrade != null) {

			if (chatbuttonhidecheck == 1) {

				ChatText2ndWidgetTrade.setRelativeX(TRADExtxt2nd);
				ChatText2ndWidgetTrade.setRelativeY(TRADEytxt2nd);

			}

		}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/// entire group area
		// get parent widgets
		/// chat box area
		// get parent widgets
		final Widget EntireParentComplete = client.getWidget(10747995);
		final Widget EntireParentSecond = client.getWidget(10616832);


		// 2nd txt
		if (EntireParentComplete != null) {

			if (chatbuttonhidecheck == 1) {

				//	ChatBoxParentComplete.setRelativeX(TRADExtxt2nd);
				//	ChatBoxParentComplete.setRelativeY(TRADEytxt2nd);

			}

		}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		/// chat box area
		// get parent widgets
		final Widget ChatBoxParentComplete = client.getWidget(10747995);
		final Widget ChatBoxParent = client.getWidget(10616832);
		final Widget ChatBox = client.getWidget(10616866);
		final Widget ChatBoxTransparentBG = client.getWidget(10616867);

		// 2nd txt
		if (ChatBoxParentComplete != null) {

			if (chatbuttonhidecheck == 1) {

			//	ChatBoxParentComplete.setRelativeX(TRADExtxt2nd);
			//	ChatBoxParentComplete.setRelativeY(TRADEytxt2nd);

			}

		}


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/// end of code





	}



//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/// clean up when chat is open and allow for customisations but first enable above


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



	@Override
	protected void shutDown() throws Exception
	{


		final Widget ChatBarWidgetFin = client.getWidget(10616833);

		if (ChatBarWidgetFin != null) {

			ChatBarWidgetFin.setWidth(519);


		}

		final Widget resizableNormalWidget = client.getWidget(WidgetInfo.RESIZABLE_MINIMAP_WIDGET);

		if (resizableNormalWidget != null && !resizableNormalWidget.isSelfHidden()) {
			for (Widget widget : resizableNormalWidget.getStaticChildren()) {

				widget.setHidden(false);

			}
		}

		// MINIMAP X
		final Widget MinimapX = client.getWidget(10747937);

		if (MinimapX != null)  {

			MinimapX.setHidden(false);

		}	// MINIMAP X
		final Widget MinimapX2 = client.getWidget(10747938);

		if (MinimapX2 != null)  {

			MinimapX2.setHidden(false);

		}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// making the collaspe part of chat
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Making chat buttons go on top of chat
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


		// top positioning of chat buttons
		final Widget ChatBoxButtons = client.getWidget(10616833);

		if (ChatBoxButtons != null)  {

			ChatBoxButtons.setRelativeY(142);

		}




//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Changes Chat box to be correct for look
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


// changing chat black bars to have more width

		// top parent
		final Widget ChatBoxTopParent = client.getWidget(10747995);

		if (ChatBoxTopParent != null)  {

			ChatBoxTopParent.setWidth(519);

		}
		// full parent
		final Widget ChatBoxFullParent = client.getWidget(10616866);

		if (ChatBoxFullParent != null)  {

			ChatBoxFullParent.setWidth(519);

		}
		// full parent
		final Widget ChatBoxOthParent = client.getWidget(10616832);

		if (ChatBoxOthParent != null)  {

			ChatBoxOthParent.setWidth(519);

		}
		// specific parent
		final Widget ChatBoxSpecParent = client.getWidget(10616867);

		if (ChatBoxSpecParent != null)  {

			ChatBoxSpecParent.setWidth(519);

		}
		// actual target widgets
		final Widget ChatBoxLineParent = client.getWidget(10616867);

		if (ChatBoxLineParent != null) {
			for (Widget ChatBoxLineParentchi : ChatBoxLineParent.getDynamicChildren()) {

				ChatBoxLineParentchi.setWidth(519);
			}
		}

		///////////////////////////////////////////////////////////////////////////////////
		// chat bar area v

		// full parent
		final Widget ChatBarFullParent = client.getWidget(10616833);

		if (ChatBarFullParent != null)  {

			ChatBarFullParent.setWidth(519);
			ChatBarFullParent.setHeight(23);
		}

		// specific parent
		final Widget ChatBarSpecParent = client.getWidget(10616834);

		if (ChatBarSpecParent != null)  {

			ChatBarSpecParent.setWidth(519);
			ChatBarSpecParent.setHeight(23);
		}


		// actual target
		final Widget ChatBarTarParent = client.getWidget(10616835);

		if (ChatBarTarParent != null)  {

			ChatBarTarParent.setWidth(519);
			ChatBarTarParent.setHeight(23);
			ChatBarTarParent.setRelativeY(0);
		}

		//////////////////////////////////////////////////////////////////////////////////////////////////////////
		// fixing chat relative y to be under chat bar

		final Widget ChatActualParent = client.getWidget(10616866);

		if (ChatActualParent != null)  {

			ChatActualParent.setRelativeY(0);
			ChatActualParent.setHeight(142);

		}



		///////////////////////////////////////////////////////////////////////////////////
		// realign chat bar buttons

		final Widget ChatBarAllParent = client.getWidget(10616836);

		if (ChatBarAllParent != null)  {


			ChatBarAllParent.setRelativeY(0);
			ChatBarAllParent.setRelativeX(3);
			ChatBarAllParent.setWidth(56);


		}
		final Widget ChatBarGameParent = client.getWidget(10616839);

		if (ChatBarGameParent != null)  {

			ChatBarGameParent.setRelativeY(0);
			ChatBarGameParent.setRelativeX(70-5);


		}
		final Widget ChatBarPubParent = client.getWidget(10616843);

		if (ChatBarPubParent != null)  {

			ChatBarPubParent.setRelativeY(0);
			ChatBarPubParent.setRelativeX(132-5);


		}
		final Widget ChatBarPriParent = client.getWidget(10616847);

		if (ChatBarPriParent != null)  {

			ChatBarPriParent.setRelativeY(0);
			ChatBarPriParent.setRelativeX(189);
			ChatBarPriParent.setWidth(56);
			ChatBarPriParent.setHeight(23);


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		}
		final Widget ChatBarPriParentSprite = client.getWidget(10616848);

		if (ChatBarPriParentSprite != null)  {

			ChatBarPriParentSprite.setRelativeX(0);





		}

		final Widget ChatBarPriParentText = client.getWidget(10616849);

		if (ChatBarPriParentText != null)  {

			ChatBarPriParentText.setHidden(false);




		}
		final Widget ChatBarAllParentText = client.getWidget(10616838);
		final Widget ChatBarAllParentSprite = client.getWidget(10616837);

		if (ChatBarAllParentText != null)  {


			ChatBarAllParentText.setRelativeX(0);
			ChatBarAllParentText.setText("All");

		}

		final Widget ChatBarChaParent = client.getWidget(10616851);

		if (ChatBarChaParent != null)  {

			ChatBarChaParent.setRelativeY(0);
			ChatBarChaParent.setRelativeX(256-5);



		}
		final Widget ChatBarClanParent = client.getWidget(10616855);

		if (ChatBarClanParent != null)  {

			ChatBarClanParent.setRelativeY(0);
			ChatBarClanParent.setRelativeX(318-5);


		}
		final Widget ChatBarTraParent = client.getWidget(10616859);

		if (ChatBarTraParent != null)  {

			ChatBarTraParent.setRelativeY(0);
			ChatBarTraParent.setRelativeX(380-5);



		}
		final Widget ChatBarRepParent = client.getWidget(10616863);

		if (ChatBarRepParent != null)  {

			ChatBarRepParent.setRelativeY(0);
			ChatBarRepParent.setRelativeX(442-5);
			ChatBarRepParent.setWidth(79);



		}
		final Widget ChatBarRepParentTarget = client.getWidget(10616864);

		if (ChatBarRepParentTarget != null)  {

			ChatBarRepParentTarget.setWidth(79);


		}
		final Widget ChatBarRepParentOther = client.getWidget(10616865);

		if (ChatBarRepParentOther != null)  {

			ChatBarRepParentOther.setWidth(79);


		}


		///////////////////////////////////////////////////////////////////////////////
		// fixing chat positioning
		// Id	10616887

		final Widget ChatBarPlayerTextParent = client.getWidget(10616887);

		if (ChatBarPlayerTextParent != null)  {

			ChatBarPlayerTextParent.setWidth(502);

		}
		final Widget ChatBarPlayerFullParent = client.getWidget(10616885);

		if (ChatBarPlayerFullParent != null)  {

			ChatBarPlayerFullParent.setWidth(519);

		}
		final Widget ChatBarPlayerOthParent = client.getWidget(10616886);

		if (ChatBarPlayerOthParent != null)  {

			ChatBarPlayerOthParent.setWidth(505);

		}

		final Widget ChatBarScrollBarParent = client.getWidget(10617389);

		if (ChatBarScrollBarParent != null)  {

			ChatBarScrollBarParent.setRelativeX(489);

		}

		final Widget ChatBoxParentFin = client.getWidget(10616866);

		if (ChatBoxParentFin != null) {

			ChatBoxParentFin.setHeight(142);

		}

	}

	@Provides
	collapsiblemodernchatconfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(collapsiblemodernchatconfig.class);
	}
}
