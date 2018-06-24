package org.bapcraft.baputilssponge;

import java.util.ArrayList;

import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
public class BapUtilsConfig {

	@Setting(value = "greentext")
	public boolean greentext;

	@Setting(value = "bad_phrases")
	public ArrayList<BadWordActionEntry> badPhrases;

}
