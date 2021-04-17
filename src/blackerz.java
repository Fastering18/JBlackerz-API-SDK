import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class blackerz {
    static JsonObject getBody(HttpsURLConnection con) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader((con.getInputStream())));
        StringBuilder sb = new StringBuilder();
        String output;
        while ((output = br.readLine()) != null) {
            sb.append(output);
        }
        JsonObject jsonObject = new JsonParser().parse(sb.toString()).getAsJsonObject();
        return jsonObject;
    }

    /**
     *
     * @param {String} BotId - Bot id from discord
     */
    public JsonObject GetBotInfo(String BotId) {
        try {
            URL url = new URL(String.format("https://blackerz.herokuapp.com/api/v1/bots/%s", BotId));
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json");
            int status = con.getResponseCode();
            if (status > 201) {
                return null;
            }
            JsonObject body = getBody(con);
            con.disconnect();
            return body;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     *
     * @param {String} ServerId - Server id from discord
     */
    public JsonObject GetServerInfo(String Serverid) {
        try {
            URL url = new URL(String.format("https://blackerz.herokuapp.com/api/v1/servers/%s", Serverid));
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json");
            int status = con.getResponseCode();
            if (status > 201) {
                return null;
            }
            JsonObject body = getBody(con);
            con.disconnect();
            return body;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
