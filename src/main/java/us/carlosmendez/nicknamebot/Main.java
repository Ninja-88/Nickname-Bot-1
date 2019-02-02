package us.carlosmendez.nicknamebot;

import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDABuilder;
import us.carlosmendez.nicknamebot.commands.CommandNickname;

import javax.security.auth.login.LoginException;

public class Main {

    public static void main(String[] args) throws LoginException {
        new JDABuilder(AccountType.BOT)
                .setToken("NTQxMzU3NjQ3MDczNjQwNDQ4.DzeSIA.DRveIrtmJ6R4lmn15RLIgcPnOR0")
                .addEventListeners(new CommandNickname())
                .build();
    }
}