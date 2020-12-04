import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;


public class app {
    public static void main(String[] args) {
        String token = "NzgzNzk2NTc5MDQzMTE1MDY5.X8f9cg.7MIZbcnyNQcpCnl5xED2_Fkgn4U";
        GatewayDiscordClient client = DiscordClientBuilder.create(token).build().login().block();

        try{
            client.getEventDispatcher().on(ReadyEvent.class)
                    .subscribe(readyEvent -> {
                        User self = readyEvent.getSelf();
                        System.out.printf("Logged in as %s#%s", self.getUsername() ,self.getDiscriminator());
                    });

        } catch (NullPointerException e){
            System.out.println("There has been an error: ");
            e.printStackTrace();
        }

        try{
            client.getEventDispatcher().on(MessageCreateEvent.class)
                    .map(MessageCreateEvent::getMessage)
                    .filter(message -> message.getAuthor().map(user -> !user.isBot()).orElse(false))
                    .filter(message -> message.getContent().startsWith("$random"))
                    .flatMap(Message::getChannel)
                    .flatMap(channel -> channel.createMessage(pokemon.generateRandomPokemon()))
                    .subscribe();
            }catch (NullPointerException e){
            System.out.println("There has been an error: ");
            e.printStackTrace();
        }

        try{
            client.getEventDispatcher().on(MessageCreateEvent.class)
                    .map(MessageCreateEvent::getMessage)
                    .filter(message -> message.getAuthor().map(user -> !user.isBot()).orElse(false))
                    .filter(message -> message.getContent().startsWith("$pokemon"))
                    .flatMap(message -> message.getChannel().flatMap(messageChannel -> messageChannel.createMessage(pokemon.generatePokemonByNumber(message.getContent()))))
                    .subscribe();

        }catch (NullPointerException e){
            System.out.println("There has been an error: ");
            e.printStackTrace();
        }

        client.onDisconnect().block();
    }

}

