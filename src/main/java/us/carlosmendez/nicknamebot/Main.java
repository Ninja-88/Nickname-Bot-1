package us.carlosmendez.nicknamebot;

import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDABuilder;
import us.carlosmendez.nicknamebot.commands.CommandNickname;

import javax.security.auth.login.LoginException;

public class Main {

    public static void main(String[] args) throws LoginException {
        new JDABuilder(AccountType.BOT)
                .setToken("NzgxNDM5MzIzNDIwODg1MDIy.X79qFQ._RMH_JuYQfYtel5fI41DcQxR6lU")
                .addEventListeners(new CommandNickname())
                .build();
    }
}
