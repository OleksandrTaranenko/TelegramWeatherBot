import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.util.Properties;
import java.util.Scanner;

public class Weather {
    private static String token;
    private static String urlOpenWeather;

    public static Model getWeather(String message) throws IOException {
        Properties props = new Properties();
        File propFile = new File("src/main/resources/myapp.properties");

        try(Reader reader = new FileReader(propFile)) {
            props.load(reader);
            token = props.getProperty("token.openweathermap");
            urlOpenWeather = props.getProperty("url.openweather");

        } catch (IOException e) {
            e.printStackTrace();
        }

        URL url = new URL(urlOpenWeather+"/data/2.5/weather?q=" + message + "&units=metric&appid=" + token);

        Scanner in = new Scanner((InputStream) url.getContent());
        String result = "";
        while (in.hasNext()) {
            result += in.nextLine();
        }

        Model model = new Model();

        JSONObject object = new JSONObject(result);
        model.setName(object.getString("name"));
        model.setCountry(object.getJSONObject("sys").getString("country"));
        model.setWind(object.getJSONObject("wind").getDouble("speed"));

        JSONObject main = object.getJSONObject("main");
        model.setTemperature(main.getDouble("temp"));
        model.setHumidity(main.getDouble("humidity"));

        JSONArray getArray = object.getJSONArray("weather");
        for (int i = 0; i < getArray.length(); i++) {
            JSONObject obj = getArray.getJSONObject(i);
            model.setIcon((String) obj.get("icon"));
            model.setMain((String) obj.get("main"));
        }
        return model;
    }
}
