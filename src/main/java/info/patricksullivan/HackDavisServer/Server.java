package info.patricksullivan.HackDavisServer;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.util.HashSet;
import java.util.Set;

public class Server {
    Set<User> userSet = new HashSet<>();

    //call this when request is made, can also remove
//    public void process(User user){
//        userSet.remove(user); //remove user before processing if he happens to be in set
//        User bestCandidate = null;
//        List<User> oldUsers = new ArrayList<>();
//        for(User u: userSet){
//            long diff = SECONDS.between(u.getTime(), LocalDateTime.now()); //remove old users here
//            if(Math.abs(diff) > 7200){
//                oldUsers.add(u);
//            }else{//
//
//            }
//        }
//        for(User u: oldUsers){
//            userSet.remove(u);
//        }
//
//        if(bestCandidate == null){
//            userSet.add(user);
//        }else{
//            //
//        }
//
//    }

    //-
    public CovidWeatherData getWeatherAndCovidData() throws IOException, InterruptedException {
        HttpRequest covidRequests = HttpRequest.newBuilder()
                .uri(URI.create("https://api.covidtracking.com/v1/states/ca/current.json"))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> covidResponse = HttpClient.newHttpClient().send(covidRequests, HttpResponse.BodyHandlers.ofString());
        System.out.println(covidResponse.body());
        JSONObject covidJSON = new JSONObject(covidResponse.body());
        int covidCases = (Integer) covidJSON.get("positiveIncrease");
        int covidTests = covidCases + (Integer) covidJSON.get("negativeIncrease");




        HttpRequest weatherRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://community-open-weather-map.p.rapidapi.com/weather?q=Davis%2CCA%2CUSA&lon=0&callback=test&id=2172797&lang=null&units=metric&mode=xml%2C%20html"))
                .header("x-rapidapi-key", "389b821bc2msh227973c0f824276p141703jsn0c5ab677b855")
                .header("x-rapidapi-host", "community-open-weather-map.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> weatherResponse = HttpClient.newHttpClient().send(weatherRequest, HttpResponse.BodyHandlers.ofString());
//        System.out.println(weatherResponse.body());
        String rb = weatherResponse.body();
        JSONObject weatherJSON = new JSONObject(rb.substring(5,rb.length()-1));
        JSONArray weatherArr = (JSONArray) weatherJSON.get("weather");
        JSONObject weather = (JSONObject) weatherArr.get(0);
        int temp = ((BigDecimal)((JSONObject) weatherJSON.get("main")).get("temp")).setScale(0, RoundingMode.HALF_UP).intValue();
//        System.out.println(temp);
//        System.out.println(weather.get("main"));


        System.out.println(weather.get("main").toString());
        System.out.println(temp);
        System.out.println(covidCases); //covidCases
        System.out.println(covidTests); //covidTests
        //String weather, int temp, int covidCases, int covidTests
        return new CovidWeatherData(weather.get("main").toString(), temp, covidCases, covidTests);
    }


    public static void main(String[] args) throws IOException, InterruptedException {
        Server s = new Server();
        s.getWeatherAndCovidData();
    }
}
