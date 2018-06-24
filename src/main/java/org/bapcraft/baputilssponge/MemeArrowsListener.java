package org.bapcraft.baputilssponge;

import java.util.List;

import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.message.MessageChannelEvent;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class MemeArrowsListener {

	@Listener
	public void onChatMessage(MessageChannelEvent.Chat event) {

		if (event.getCause().containsType(Player.class)) {

			Player p = event.getCause().first(Player.class).get();
			if (!p.hasPermission(Perms.PERM_MEME_ARROWS)) {
				return;
			}

			Text om = event.getOriginalMessage();
			List<Text> components = om.getChildren();
			Text last = components.get(components.size() - 1);

			// >implying
			if (last.toPlain().startsWith(">")) {

				Text.Builder tb = Text.builder();
				for (int i = 0; i < components.size() - 1; i++) {
					tb.append(components.get(i));
				}

				Text.Builder greener = Text.builder();
				greener.color(TextColors.GREEN);
				greener.append(Text.of(last.toPlain()));
				tb.append(greener.build());

				event.setMessage(tb.build());

			}

		}

	}

}
