package com.CollapsibleModernChat;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class CollapsibleModernChatTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(CollapsibleModernChatPlugin.class);
		RuneLite.main(args);
	}
}