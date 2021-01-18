package info.patricksullivan.HackDavisServer;

public class Request {
    private final long id;
    private final String content;
    private final int covidCases;
    private final int covidTests;
    private final String weather;
    private final int temp;


    public Request(long id, String content, int covidCases, int covidTests, String weather, int temp) {
        this.id = id;
        this.content = content;
        this.covidCases = covidCases;
        this.covidTests = covidTests;
        this.weather = weather;
        this.temp = temp;
    }


    //request id
    public long getId() {
        return id;
    }

    //user display text
    public String getContent() {
        return content;
    }

    public int getCovidCases() {
        return covidCases;
    }

    public int getCovidTests() {
        return covidTests;
    }

    public String getWeather() {
        return weather;
    }

    public int getTemp() {
        return temp;
    }
}
