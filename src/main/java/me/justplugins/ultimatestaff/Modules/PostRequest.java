package me.justplugins.ultimatestaff.Modules;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class PostRequest {
    public void logs(Player player, String reason){
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost("https://api.ultimatestaff.xyz/AddReportLog.php");

// Request parameters and other properties.
        List<NameValuePair> params = new ArrayList<NameValuePair>(2);
        params.add(new BasicNameValuePair("name", player.getName()));
        params.add(new BasicNameValuePair("uuid", player.getUniqueId().toString()));
        params.add(new BasicNameValuePair("ip", player.getAddress().getAddress().getHostAddress()));
        params.add(new BasicNameValuePair("reason", reason));
        try {
            httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));


//Execute and get the response.
        HttpResponse response = httpclient.execute(httppost);
        HttpEntity entity = response.getEntity();

        if (entity != null) {
            try (InputStream instream = entity.getContent()) {
                // do something useful
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
