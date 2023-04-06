package com.collapsiblemodernchat;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class collapsiblemodernchattest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(collapsiblemodernchatplugin.class);
		RuneLite.main(args);
	}
}