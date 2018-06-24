package org.bapcraft.baputilssponge;

import java.util.ArrayList;

import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
public class BadWordActionEntry {

	@Setting(value = "matches")
	public ArrayList<String> matches;
	
	@Setting(value = "ban_dur")
	public String banDuration;
	
	@Setting(value = "ban_dur")
	public String banMessage;
	
	@Setting("announcement_msg")
	public String announcement;
	
}
