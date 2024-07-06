package com.collapsiblemodernchat;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.events.ClientTick;
import net.runelite.api.events.ScriptPostFired;
import net.runelite.api.widgets.ComponentID;
import net.runelite.api.widgets.Widget;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

import static com.collapsiblemodernchat.collapsiblemodernchatconfig.MinimapHidingTask.DEFAULT;
import static java.lang.Integer.valueOf;
import static net.runelite.api.widgets.ComponentID.RESIZABLE_VIEWPORT_BOTTOM_LINE_MINIMAP;

@Slf4j
@PluginDescriptor(
		name = "Collapsible Modern Chat",
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

	// From Minimap plugin
	// https://github.com/runelite/runelite/blob/d3cd4f1b8a8a25edca23e948444b3048c0db21d3/runelite-client/src/main/java/net/runelite/client/plugins/minimap/MinimapPlugin.java#L138
	private void updateMinimapWidgetVisibility(boolean enable) {
		final Widget resizableStonesWidget = client.getWidget(ComponentID.RESIZABLE_VIEWPORT_MINIMAP);

		if (resizableStonesWidget != null) {
			resizableStonesWidget.setHidden(enable);
		}

		final Widget resizableNormalWidget = client.getWidget(ComponentID.RESIZABLE_VIEWPORT_BOTTOM_LINE_MINIMAP);

		if (resizableNormalWidget != null && !resizableNormalWidget.isSelfHidden()) {
			for (Widget widget : resizableNormalWidget.getStaticChildren()) {
				if (widget.getId() != ComponentID.RESIZABLE_VIEWPORT_BOTTOM_LINE_LOGOUT_BUTTON_OVERLAY &&
						widget.getId() != ComponentID.RESIZABLE_VIEWPORT_BOTTOM_LINE_MINIMAP_LOGOUT_BUTTON) {
					widget.setHidden(enable);
				}
			}
		}
	}

	int chatbuttonhidecheck = 0;

	private void updatePlugins() {

		chatbuttonhidecheck = 0;
		//Id	10682368 private chat messages

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//this is for checking if chat buttons are clicked or not
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		//int new_chat_width = Integer.parseInt(config.NewChatWidth());

		// below is getting the chat button widget ids

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
				(resizableChatButtonWidgetTrade.getSpriteId() == 3053 || resizableChatButtonWidgetTrade.getSpriteId() == 3054)

		) {
			//   chatbuttonhide=widgethide+1;
			chatbuttonhidecheck = 1;

		} else {
			if ((resizableChatButtonWidgetAll.getSpriteId() == 3051 || resizableChatButtonWidgetAll.getSpriteId() == 3052) ||
					(resizableChatButtonWidgetGame.getSpriteId() == 3051 || resizableChatButtonWidgetGame.getSpriteId() == 3052) ||
					(resizableChatButtonWidgetPublic.getSpriteId() == 3051 || resizableChatButtonWidgetPublic.getSpriteId() == 3052) ||
					(resizableChatButtonWidgetPrivate.getSpriteId() == 3051 || resizableChatButtonWidgetPrivate.getSpriteId() == 3052) ||
					(resizableChatButtonWidgetChannel.getSpriteId() == 3051 || resizableChatButtonWidgetChannel.getSpriteId() == 3052) ||
					(resizableChatButtonWidgetClan.getSpriteId() == 3051 || resizableChatButtonWidgetClan.getSpriteId() == 3052) ||
					(resizableChatButtonWidgetTrade.getSpriteId() == 3051 || resizableChatButtonWidgetTrade.getSpriteId() == 3052)
			) {

				//  chatbuttonhide=widgethide;
				chatbuttonhidecheck = 0;

			}

		}



		// chat npc dialog
		final Widget ChatBoxDialog = client.getWidget(15138822);

		if (ChatBoxDialog != null && ChatBoxDialog.getText()!=null) {
			if (chatbuttonhidecheck == 0)
			{
				chatbuttonhidecheck = 1;
			}

		}
		else
		{
			//chatbuttonhidecheck = 0;
		}


		// chat dialog
		final Widget ChatBoxDialogPlayer = client.getWidget(14221318);

		if (ChatBoxDialogPlayer != null && ChatBoxDialogPlayer.getText()!=null) {
			if (chatbuttonhidecheck == 0)
			{
				chatbuttonhidecheck = 1;
			}

		}
		else
		{
			//chatbuttonhidecheck = 0;
		}





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

		if (config.HideMiniMapOnChat() == true) {
			this.updateMinimapWidgetVisibility(chatbuttonhidecheck == 0 ^ config.MinimapHidingTask() == DEFAULT);
		}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// making the collaspe part of chat
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Making chat buttons go on top of chat
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		if (config.TopChatBarset()==true) {
			// top positioning of chat buttons
			final Widget ChatBoxButtons = client.getWidget(10616833);

			if (ChatBoxButtons != null) {

				ChatBoxButtons.setRelativeY(0);

			}
		}
		else {
			final Widget ChatBoxButtons = client.getWidget(10616833);

			if (ChatBoxButtons != null) {

				ChatBoxButtons.setRelativeY(142-6);

			}
		}



//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Changes Chat box to be correct for look
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


// changing chat black bars to have more width

		// top parent
		final Widget ChatBoxTopParent = client.getWidget(10747995);

		if (ChatBoxTopParent != null)  {

			//	ChatBoxTopParent.setWidth(534);

		}
		// full parent
		final Widget ChatBoxFullParent = client.getWidget(10616866);

		if (ChatBoxFullParent != null)  {

			//	ChatBoxFullParent.setWidth(534);

		}
		// full parent
		final Widget ChatBoxOthParent = client.getWidget(10616832);

		if (ChatBoxOthParent != null)  {

			//	ChatBoxOthParent.setWidth(534);

		}
		// specific parent
		final Widget ChatBoxSpecParent = client.getWidget(10616867);

		if (ChatBoxSpecParent != null)  {

			//ChatBoxSpecParent.setWidth(534);

		}
		// actual target widgets
		final Widget ChatBoxLineParent = client.getWidget(10616867);

		if (ChatBoxLineParent != null) {
			for (Widget ChatBoxLineParentchi : ChatBoxLineParent.getDynamicChildren()) {

				//	ChatBoxLineParentchi.setWidth(534);
			}
		}

		///////////////////////////////////////////////////////////////////////////////////
		// chat bar area v

		// full parent
		final Widget ChatBarFullParent = client.getWidget(10616833);

		if (ChatBarFullParent != null)  {

			//	ChatBarFullParent.setWidth(534);
			ChatBarFullParent.setHeight(29);
		}

		// specific parent
		final Widget ChatBarSpecParent = client.getWidget(10616834);

		if (ChatBarSpecParent != null)  {

			//	ChatBarSpecParent.setWidth(534);
			ChatBarSpecParent.setHeight(29);
		}

		if (config.TopChatBarset()==true) {
			// actual target
			final Widget ChatBarTarParent = client.getWidget(10616835);

			if (ChatBarTarParent != null) {

				//	ChatBarTarParent.setWidth(534);
				ChatBarTarParent.setHeight(28);
				ChatBarTarParent.setRelativeY(0);
			}
		}
		else {
			final Widget ChatBarTarParent = client.getWidget(10616835);

			if (ChatBarTarParent != null) {

				//ChatBarTarParent.setWidth(534);
				ChatBarTarParent.setHeight(28);
				ChatBarTarParent.setRelativeY(2);
			}

		}
		//////////////////////////////////////////////////////////////////////////////////////////////////////////
		// fixing chat relative y to be under chat bar
		if (config.TopChatBarset()==true) {
			final Widget ChatActualParent = client.getWidget(10616866);

			if (ChatActualParent != null) {

				ChatActualParent.setRelativeY(28);
				ChatActualParent.setHeight(138);

			}
		}
		else {

			final Widget ChatActualParent = client.getWidget(10616866);

			if (ChatActualParent != null) {

				ChatActualParent.setRelativeY(0);
				ChatActualParent.setHeight(138);

			}

		}
// 504

		///////////////////////////////////////////////////////////////////////////////////
		// realign chat bar buttons

		final Widget ChatBarAllParent = client.getWidget(10616836);

		if (config.TopChatBarset()==true) {
			if (ChatBarAllParent != null) {

				if (chatbuttonhidecheck == 1) {
					ChatBarAllParent.setRelativeY(6);
					if (config.MirrorChatBarButtons()==true) {
						if (config.FlipCollIcon() == true) {
							ChatBarAllParent.setRelativeX(454);
						} else {
							ChatBarAllParent.setRelativeX(454);
						}
					}
					else {
						if (config.FlipCollIcon() == true) {
							ChatBarAllParent.setRelativeX(8);
						} else {
							ChatBarAllParent.setRelativeX(8);
						}
					}

					ChatBarAllParent.setWidth(56);
				}
				if (chatbuttonhidecheck == 0) {
					ChatBarAllParent.setRelativeY(6);
					if (config.FlipCollIcon()==true) {

							ChatBarAllParent.setRelativeX(14);

					}
					else {

							ChatBarAllParent.setRelativeX(8);

					}
					ChatBarAllParent.setWidth(23);
				}

			}
		}
		else {
			if (ChatBarAllParent != null) {

				if (chatbuttonhidecheck == 1) {
					ChatBarAllParent.setRelativeY(2);
					if (config.MirrorChatBarButtons()==true) {
						if (config.FlipCollIcon() == true) {
							ChatBarAllParent.setRelativeX(454);
						} else {
							ChatBarAllParent.setRelativeX(454);
						}
					}
					else {
						if (config.FlipCollIcon() == true) {
							ChatBarAllParent.setRelativeX(8);
						} else {
							ChatBarAllParent.setRelativeX(8);
						}
					}
					ChatBarAllParent.setWidth(56);
				}
				if (chatbuttonhidecheck == 0) {
					ChatBarAllParent.setRelativeY(2);
					if (config.FlipCollIcon()==true) {


							ChatBarAllParent.setRelativeX(14);

					}
					else {

							ChatBarAllParent.setRelativeX(8);

					}
					ChatBarAllParent.setWidth(23);
				}

			}
		}

		final Widget ChatBarAllSprite = client.getWidget(10616837);
		if (ChatBarAllParent != null) {
			if (chatbuttonhidecheck == 0) {

					//ChatBarAllSprite.setRelativeX(0);
				if (config.FlipCollIcon() == true) {
					ChatBarAllSprite.setRelativeX(-33);
				} else {
					ChatBarAllSprite.setRelativeX(0);
				}

			}
			else {
				if (config.FlipCollIcon() == true) {
					ChatBarAllSprite.setRelativeX(0);
				} else {
					ChatBarAllSprite.setRelativeX(0);
				}
			}
		}

		final Widget ChatBarGameParent = client.getWidget(10616839);
		if (config.TopChatBarset()==true) {
			if (ChatBarGameParent != null) {
				if (chatbuttonhidecheck == 1) {
					ChatBarGameParent.setRelativeY(6);
					if (config.MirrorChatBarButtons()==true) {
						ChatBarGameParent.setRelativeX(82);
					}
					else {
						ChatBarGameParent.setRelativeX(70);
					}
				}
				if (chatbuttonhidecheck == 0) {
					ChatBarGameParent.setRelativeY(-45);
					ChatBarGameParent.setRelativeX(70);
				}

			}
		}
		else {
			if (ChatBarGameParent != null) {
				if (chatbuttonhidecheck == 1) {
					ChatBarGameParent.setRelativeY(2);
					if (config.MirrorChatBarButtons()==true) {
						ChatBarGameParent.setRelativeX(82);
					}
					else {
						ChatBarGameParent.setRelativeX(70);
					}
				}
				if (chatbuttonhidecheck == 0) {
					ChatBarGameParent.setRelativeY(-45);
					ChatBarGameParent.setRelativeX(70);
				}

			}
		}

		if (config.TopChatBarset()==true) {
			final Widget ChatBarPubParent = client.getWidget(10616843);

			if (ChatBarPubParent != null) {
				if (chatbuttonhidecheck == 1) {
					ChatBarPubParent.setRelativeY(6);
					if (config.MirrorChatBarButtons()==true) {
						ChatBarPubParent.setRelativeX(144);
					}
					else {
						ChatBarPubParent.setRelativeX(132);
					}
				}
				if (chatbuttonhidecheck == 0) {
					ChatBarPubParent.setRelativeY(-45);
					ChatBarPubParent.setRelativeX(132);
				}

			}
		}
		else {
			final Widget ChatBarPubParent = client.getWidget(10616843);

			if (ChatBarPubParent != null) {
				if (chatbuttonhidecheck == 1) {
					ChatBarPubParent.setRelativeY(2);
					if (config.MirrorChatBarButtons()==true) {
						ChatBarPubParent.setRelativeX(144);
					}
					else {
						ChatBarPubParent.setRelativeX(132);
					}
				}
				if (chatbuttonhidecheck == 0) {
					ChatBarPubParent.setRelativeY(-45);
					ChatBarPubParent.setRelativeX(132);
				}

			}
		}

		if (config.TopChatBarset()==true) {
			final Widget ChatBarPriParent = client.getWidget(10616847);

			if (ChatBarPriParent != null) {
				if (chatbuttonhidecheck == 1) {
					ChatBarPriParent.setRelativeY(6);
					if (config.MirrorChatBarButtons()==true) {
						ChatBarPriParent.setRelativeX(206);
					}
					else {
						ChatBarPriParent.setRelativeX(194);
					}
					ChatBarPriParent.setWidth(56);
					ChatBarPriParent.setHeight(23);
				}
				if (chatbuttonhidecheck == 0) {
					ChatBarPriParent.setRelativeY(6);



						if (config.FlipCollIcon() == true) {
							ChatBarPriParent.setRelativeX(9); //
						} else {
							ChatBarPriParent.setRelativeX(30); //
						}


					ChatBarPriParent.setWidth(6);
					ChatBarPriParent.setHeight(23);
				}


			}


			final Widget ChatBarPriParentText = client.getWidget(10616849);

			if (ChatBarPriParentText != null) {
				if (chatbuttonhidecheck == 1) {
					ChatBarPriParentText.setHidden(false);
				}
				if (chatbuttonhidecheck == 0) {
					ChatBarPriParentText.setHidden(true);
				}


			}

			final Widget ChatBarPriParentSprite = client.getWidget(10616848);

			if (ChatBarPriParentSprite != null) {
				if (chatbuttonhidecheck == 1) {
					ChatBarPriParentSprite.setRelativeX(0);

				}
				if (chatbuttonhidecheck == 0) {
					ChatBarPriParentSprite.setRelativeX(-45);

				}


			}

		}
		else {

			final Widget ChatBarPriParent = client.getWidget(10616847);

			if (ChatBarPriParent != null) {
				if (chatbuttonhidecheck == 1) {
					ChatBarPriParent.setRelativeY(2);
					if (config.MirrorChatBarButtons()==true) {
						ChatBarPriParent.setRelativeX(206);
					}
					else {
						ChatBarPriParent.setRelativeX(194);
					}
					ChatBarPriParent.setWidth(56);
					ChatBarPriParent.setHeight(23);
				}
				if (chatbuttonhidecheck == 0) {
					ChatBarPriParent.setRelativeY(2);

						if (config.FlipCollIcon() == true) {
							ChatBarPriParent.setRelativeX(9); //
						} else {
							ChatBarPriParent.setRelativeX(30); //
						}



					ChatBarPriParent.setWidth(6);
					ChatBarPriParent.setHeight(23);
				}


			}
			final Widget ChatBarPriParentSprite = client.getWidget(10616848);

			if (ChatBarPriParentSprite != null) {
				if (chatbuttonhidecheck == 1) {
					ChatBarPriParentSprite.setRelativeX(0);

				}
				if (chatbuttonhidecheck == 0) {
					ChatBarPriParentSprite.setRelativeX(-45);

				}


			}

			final Widget ChatBarPriParentText = client.getWidget(10616849);

			if (ChatBarPriParentText != null) {
				if (chatbuttonhidecheck == 1) {
					ChatBarPriParentText.setHidden(false);
				}
				if (chatbuttonhidecheck == 0) {
					ChatBarPriParentText.setHidden(true);
				}


			}
			///////////////////////////////////////////////////////////////////////////

		}

		final Widget ChatBarAllParentText = client.getWidget(10616838);
		final Widget ChatBarAllParentSprite = client.getWidget(10616837);

		if (ChatBarAllParentText != null)  {
			if (chatbuttonhidecheck == 1) {

				ChatBarAllParentText.setRelativeX(0);
				ChatBarAllParentText.setText("All");
			}
			if (chatbuttonhidecheck == 0) {

				ChatBarAllParentText.setRelativeX(-16);

				if (ChatBarAllParentSprite.getSpriteId()==3051) {
					ChatBarAllParentText.setText("-");
				}
				if (ChatBarAllParentSprite.getSpriteId()==3052) {
					ChatBarAllParentText.setText("+");
				}
			}



		}
		if (config.TopChatBarset()==true) {
			final Widget ChatBarChaParent = client.getWidget(10616851);

			if (ChatBarChaParent != null) {
				if (chatbuttonhidecheck == 1) {
					ChatBarChaParent.setRelativeY(6);
					if (config.MirrorChatBarButtons()==true) {
						ChatBarChaParent.setRelativeX(268);
					}
					else {
						ChatBarChaParent.setRelativeX(256);
					}
				}
				if (chatbuttonhidecheck == 0) {
					ChatBarChaParent.setRelativeY(-45);
					ChatBarChaParent.setRelativeX(256);
				}

			}
		}
		else
		{
			final Widget ChatBarChaParent = client.getWidget(10616851);

			if (ChatBarChaParent != null) {
				if (chatbuttonhidecheck == 1) {
					ChatBarChaParent.setRelativeY(2);
					if (config.MirrorChatBarButtons()==true) {
						ChatBarChaParent.setRelativeX(268);
					}
					else {
						ChatBarChaParent.setRelativeX(256);
					}
				}
				if (chatbuttonhidecheck == 0) {
					ChatBarChaParent.setRelativeY(-45);
					ChatBarChaParent.setRelativeX(256);
				}

			}
		}

		if (config.TopChatBarset()==true) {
			final Widget ChatBarClanParent = client.getWidget(10616855);

			if (ChatBarClanParent != null) {
				if (chatbuttonhidecheck == 1) {
					ChatBarClanParent.setRelativeY(6);
					if (config.MirrorChatBarButtons()==true) {
						ChatBarClanParent.setRelativeX(330);
					}
					else {
						ChatBarClanParent.setRelativeX(318);
					}
				}
				if (chatbuttonhidecheck == 0) {
					ChatBarClanParent.setRelativeY(-45);
					if (config.MirrorChatBarButtons()==true) {
						ChatBarClanParent.setRelativeX(330);
					}
					else {
						ChatBarClanParent.setRelativeX(318);
					}
				}

			}
		}
		else {
			final Widget ChatBarClanParent = client.getWidget(10616855);

			if (ChatBarClanParent != null) {
				if (chatbuttonhidecheck == 1) {
					ChatBarClanParent.setRelativeY(2);
					if (config.MirrorChatBarButtons()==true) {
						ChatBarClanParent.setRelativeX(330);
					}
					else {
						ChatBarClanParent.setRelativeX(318);
					}
				}
				if (chatbuttonhidecheck == 0) {
					ChatBarClanParent.setRelativeY(-45);
					ChatBarClanParent.setRelativeX(318);
				}

			}
		}
		if (config.TopChatBarset()==true) {
			final Widget ChatBarTraParent = client.getWidget(10616859);

			if (ChatBarTraParent != null) {
				if (chatbuttonhidecheck == 1) {
					ChatBarTraParent.setRelativeY(6);
					if (config.MirrorChatBarButtons()==true) {
						ChatBarTraParent.setRelativeX(392);
					}
					else {
						ChatBarTraParent.setRelativeX(380);
					}
				}
				if (chatbuttonhidecheck == 0) {
					ChatBarTraParent.setRelativeY(-45);
					ChatBarTraParent.setRelativeX(380);
				}


			}
		}
		else {
			final Widget ChatBarTraParent = client.getWidget(10616859);

			if (ChatBarTraParent != null) {
				if (chatbuttonhidecheck == 1) {
					ChatBarTraParent.setRelativeY(2);
					if (config.MirrorChatBarButtons()==true) {
						ChatBarTraParent.setRelativeX(392);
					}
					else {
						ChatBarTraParent.setRelativeX(380);
					}
				}
				if (chatbuttonhidecheck == 0) {
					ChatBarTraParent.setRelativeY(-45);
					ChatBarTraParent.setRelativeX(380);
				}


			}
		}
		if (config.TopChatBarset()==true) {
			final Widget ChatBarRepParent = client.getWidget(10616863);

			if (ChatBarRepParent != null) {
				if (chatbuttonhidecheck == 1) {
					ChatBarRepParent.setRelativeY(6);
					if (config.MirrorChatBarButtons()==true) {
						ChatBarRepParent.setRelativeY(6);
						ChatBarRepParent.setRelativeX(8);
						ChatBarRepParent.setWidth(82);
					}
					else {
						ChatBarRepParent.setRelativeY(6);
						ChatBarRepParent.setRelativeX(442);
						ChatBarRepParent.setWidth(82);

					}
					ChatBarRepParent.setWidth(82);
				}
				if (chatbuttonhidecheck == 0) {
					ChatBarRepParent.setRelativeY(-45);
					ChatBarRepParent.setRelativeX(442);
					ChatBarRepParent.setWidth(82);
				}


			}
		}
		else {
			final Widget ChatBarRepParent = client.getWidget(10616863);

			if (ChatBarRepParent != null) {
				if (chatbuttonhidecheck == 1) {
					if (config.MirrorChatBarButtons()==true) {
						ChatBarRepParent.setRelativeY(2);
						ChatBarRepParent.setRelativeX(8);
						ChatBarRepParent.setWidth(82);
					}
					else {
						ChatBarRepParent.setRelativeY(2);
						ChatBarRepParent.setRelativeX(442);
						ChatBarRepParent.setWidth(82);

					}
				}
				if (chatbuttonhidecheck == 0) {
					ChatBarRepParent.setRelativeY(-45);
					ChatBarRepParent.setRelativeX(442);
					ChatBarRepParent.setWidth(82);
				}


			}
		}
		final Widget ChatBarRepParentTarget = client.getWidget(10616864);

		if (ChatBarRepParentTarget != null)  {

			ChatBarRepParentTarget.setWidth(68);


		}
		final Widget ChatBarRepParentOther = client.getWidget(10616865);

		if (ChatBarRepParentOther != null)  {

			ChatBarRepParentOther.setWidth(68);


		}


		///////////////////////////////////////////////////////////////////////////////
		// fixing chat positioning
		// Id	10616887

		final Widget ChatBarPlayerTextParent = client.getWidget(10616887);

		if (ChatBarPlayerTextParent != null)  {

			//ChatBarPlayerTextParent.setWidth(515);

		}
		final Widget ChatBarPlayerFullParent = client.getWidget(10616885);

		if (ChatBarPlayerFullParent != null)  {

			//ChatBarPlayerFullParent.setWidth(535);

		}
		final Widget ChatBarPlayerOthParent = client.getWidget(10616886);

		if (ChatBarPlayerOthParent != null)  {

			//ChatBarPlayerOthParent.setWidth(535);

		}

		final Widget ChatBarScrollBarParent = client.getWidget(10617389);

		if (ChatBarScrollBarParent != null)  {

			//ChatBarScrollBarParent.setRelativeX(500);

		}
		if (config.TopChatBarset()==false) {
			final Widget ChatBoxParentFin = client.getWidget(10616866);

			if (ChatBoxParentFin != null) {

				//ChatBoxParentFin.setHeight(138);

			}

		}
		else {
			final Widget ChatBoxParentFin = client.getWidget(10616866);

			if (ChatBoxParentFin != null) {

				//ChatBoxParentFin.setHeight(142);

			}
		}

		if (config.HideMiniMapOnChat() == false) {
			this.updateMinimapWidgetVisibility(false);
		}

		if (config.HideMiniMapX()==true) {
			// MINIMAP X
			final Widget MinimapX = client.getWidget(10747938);

			if (MinimapX != null) {

				MinimapX.setHidden(true);

			}    // MINIMAP X
			final Widget MinimapX2 = client.getWidget(10747939);

			if (MinimapX2 != null) {

				MinimapX2.setHidden(true);

			}
		}
		else {
			// MINIMAP X
			final Widget MinimapX = client.getWidget(10747938);

			if (MinimapX != null)  {

				MinimapX.setHidden(false);

			}	// MINIMAP X
			final Widget MinimapX2 = client.getWidget(10747939);

			if (MinimapX2 != null)  {

				MinimapX2.setHidden(false);

			}
		}

		final Widget ChatBarWidgetFin = client.getWidget(10616833);

		if (ChatBarWidgetFin != null) {
			if (chatbuttonhidecheck == 1) {
				ChatBarWidgetFin.setWidth(519);
				ChatBarWidgetFin.setRelativeX(0);
			}
			else
			{
				if (config.SetCollToRight()==true) {
					ChatBarWidgetFin.setRelativeX(473);
				}
				else
				{
					ChatBarWidgetFin.setRelativeX(0);
				}
				ChatBarWidgetFin.setWidth(40);
			}
		}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	}

	@Override
	protected void shutDown() throws Exception
	{


		final Widget ChatBarWidgetFin = client.getWidget(10616833);

		if (ChatBarWidgetFin != null) {

			ChatBarWidgetFin.setWidth(519);


		}

		final Widget resizableNormalWidget = client.getWidget(ComponentID.RESIZABLE_VIEWPORT_BOTTOM_LINE_MINIMAP);

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
