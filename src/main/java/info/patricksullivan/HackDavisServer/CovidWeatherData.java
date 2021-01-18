package info.patricksullivan.HackDavisServer;

public class CovidWeatherData {

//    enum Weather{
//        THUNDERSTORM,
//        DRIZZLE,
//        RAIN,
//        SNOW,
//        MIST,
//        SMOKE,
//        HAZE,
//        FOG,
//        SAND,
//        DUST,
//        ASH,
//        SQUALL,
//        TORNADO,
//        CLEAR,
    //      clouds
//    }

    int temp;
    String weather;
    int covidCases;
    int covidTests;
    int score;

    public void howMuchDoesItSuckToday(){
        int tempDif = Math.abs(22-temp);
        int weatherModifier = -1;
        switch (weather){
            case "Clear":
                weatherModifier = 0;
                break;
            case "Mist":
                weatherModifier = 7;
                break;
            case "Clouds":
                weatherModifier = 4;
                break;
            case "Snow":
                weatherModifier = 8;
                break;
            case "Fog" :
                weatherModifier = 7;
                break;
            case "Haze":
                weatherModifier = 3;
                break;
            case "Drizzle":
                weatherModifier = 10;
                break;
            default:
                weatherModifier = 100;
                break;
        }
        int covidModifier = (covidCases * 100 / covidTests);

        score = covidModifier + tempDif + weatherModifier;

    }

    public int getTemp() {
        return temp;
    }

    public String getWeather() {
        return weather;
    }

    public int getCovidCases() {
        return covidCases;
    }

    public int getCovidTests() {
        return covidTests;
    }

    public int getScore(){
        return score;
    }

    public CovidWeatherData(String weather, int temp, int covidCases, int covidTests) {
        this.weather = weather;
        this.temp = temp;
        this.covidCases = covidCases;
        this.covidTests = covidTests;
        howMuchDoesItSuckToday();
    }
}
