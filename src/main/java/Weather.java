import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class Weather {
    private static final String token = "c9454a869bd719acd306d2f3f4eaf62e";

    public static Model getWeather(String message) throws IOException {
        URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + message + "&units=metric&appid=" + token);

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
