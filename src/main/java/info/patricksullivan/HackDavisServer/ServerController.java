package info.patricksullivan.HackDavisServer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

@RestController //
public class ServerController {

    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/checkactivities")
    public Request request(@RequestParam(value = "fishing") int fishing,
                            @RequestParam(value = "hanggliding") int hanggliding, @RequestParam(value = "hiking") int hiking) throws IOException, InterruptedException {

        Server s = new Server();
        CovidWeatherData data = s.getWeatherAndCovidData();
        String weather = data.getWeather();
        int temp = data.getTemp();
        int covidCases = data.getCovidCases();
        int covidTests = data.getCovidTests();
        int score = data.getScore();

        String template;
        if(score < 20){
            template = "It's a wonderful day to go outdoors and enjoy %s! Be sure to wear a mask around others and wash your hands frequently.";
        }else if(score < 30){
            template = "It's a good day to be outdoors and do some %s! Be sure to wear a mask around others and wash your hands frequently.";
        }else if(score < 40){
            template = "You can go outdoors and do some %s if you want, but it's not the best day. If you do go out, be sure to wear a mask around others and wash your hands frequently.";
        }else{
            template = "Best to stay inside today!";
        }


        String activityOfChoice;
        if(fishing >= hanggliding && fishing >= hiking){
            activityOfChoice = "fishing";
        }else if(hanggliding > hiking){
            activityOfChoice = "hang gliding";
        }else{
            activityOfChoice = "hiking";
        }
        template = String.format(template, activityOfChoice);

        return new Request(counter.incrementAndGet(), template, covidCases, covidTests, weather, temp);
    }
}
