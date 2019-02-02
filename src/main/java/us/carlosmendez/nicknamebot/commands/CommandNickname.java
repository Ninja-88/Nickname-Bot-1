package us.carlosmendez.nicknamebot.commands;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.exceptions.HierarchyException;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.GuildController;

import java.util.*;

public class CommandNickname extends ListenerAdapter {
    private Timer timer = new Timer();
    private HashMap<Member, Long> choosingNickname = new HashMap<>();

    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        String[] args = e.getMessage().getContentRaw().split(" ");

        if (e.getAuthor().isBot()) return;
        Member member = e.getMember();

        if (choosingNickname.containsKey(member)) {
            GuildController guildController = new GuildController(e.getGuild());

            choosingNickname.remove(member);

            try {
                guildController.setNickname(member, e.getMessage().getContentRaw()).queue();
            } catch (HierarchyException ex) {
                e.getChannel().sendMessage("I can't set the nickname of someone with a higher rank than me.").queue();
                return;
            }

            e.getChannel().sendMessage("You have set your nickname to `" + e.getMessage().getContentRaw() + "`").queue();
            return;
        }
        if (!args[0].equalsIgnoreCase(".nickname")) return;
        long start = System.currentTimeMillis();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (!choosingNickname.containsKey(member) || choosingNickname.get(member) != start) {
                    cancel();
                    return;
                }

                choosingNickname.remove(member);
                e.getChannel().sendMessage("You have ran out of time to choose your nickname!").queue();
                cancel();
            }
        }, 10000);

        choosingNickname.put(member, start);
        e.getChannel().sendMessage("Choose what you'd like your new nickname to be").queue();
    }
}
