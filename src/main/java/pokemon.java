import me.sargunvohra.lib.pokekotlin.client.PokeApi;
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient;
import me.sargunvohra.lib.pokekotlin.model.Pokemon;
import me.sargunvohra.lib.pokekotlin.model.PokemonStat;

import java.util.List;
import java.util.Random;

public class pokemon{

    public static int generateNumber(){
        Random random = new Random();
        return random.nextInt(898);

    }

    private static String callAPI(int number){
        PokeApi poke = new PokeApiClient();
        //Generates a random pokemon object
        Pokemon userPokemon = poke.getPokemon(number);
        //Now pick out the key parts you want to use
        String pokeName = userPokemon.getName();
        String sprite = userPokemon.getSprites().getFrontDefault();
        List<PokemonStat> stats = userPokemon.getStats();

        String res = "Pokemon #" + number+ " is " + pokeName.toUpperCase() + "\n";

        StringBuilder finalStr = new StringBuilder();
        finalStr.append("\n").append("BASE STATS: ").append( "\n");
        for (PokemonStat stat : stats) {
            finalStr.append(stat.getStat().component1()).append( " = ") .append( stat.getBaseStat()).append("\n");
        }
        return res + finalStr + sprite;
    }


    public static String generateRandomPokemon(){
        int number = generateNumber();
        return callAPI(number);

    }

    //Split the string and get the int then get pokemon information
    public static String generatePokemonByNumber(String msg){
        String[] arr = msg.split(" ");
        if(!isNumeric(arr[1])){
            return "Hey enter a number after $pokemon to get a specific Pokemon";
        }

        return callAPI(Integer.parseInt(arr[1]));
    }

    public static boolean isNumeric(String strNum){
        if(strNum == null){
            return false;
        }
        try{
            Integer.parseInt(strNum);
        }catch (NumberFormatException nfe){
            return false;
        }
        return true;
    }

//For testing purposes
    public static void main(String[] args) {
        System.out.println(generateRandomPokemon());
        System.out.println(generatePokemonByNumber("$pokemon 543"));
    }
}
