import java.util.ArrayList;

public class Model {
    private String name;
    private String country;
    private Double temperature;
    private Double humidity;
    private Double wind;
    private String icon;
    private String main;

    public String getIcon() {
        return "http://openweathermap.org/img/w/" + this.icon + ".png";
    }

    public String getAllData() {
        return  "Weather in "+ getName() +", "+getCountry()+ ":\n" +
                " temperature: " + getTemperature() + " C" + "\n" +
                " humidity: " + getHumidity() + " %" + "\n" +
                " wind: " + getWind() + " m/s" + "\n";
    }

    public Double getWind() {
        return wind;
    }

    public void setWind(Double wind) {
        this.wind = wind;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    public String getName() {
        return this.name;
    }

    public Double getTemperature() {
        return this.temperature;
    }

    public Double getHumidity() {
        return this.humidity;
    }



    public String getMain() {
        return this.main;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setMain(String main) {
        this.main = main;
    }
}
