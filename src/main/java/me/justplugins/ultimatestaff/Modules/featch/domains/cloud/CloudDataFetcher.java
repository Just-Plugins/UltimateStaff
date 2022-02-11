package me.justplugins.ultimatestaff.Modules.featch.domains.cloud;

import com.google.common.reflect.TypeToken;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;
import sun.misc.IOUtils;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CloudDataFetcher extends Thread {
    private static final String FETCH_URL = "http://api.ultimatestaff.xyz/domainexp.php";

    public static LinkedTreeMap<String, CloudDomains> current;


    public static LinkedTreeMap<String, CloudDomains> domains() throws IOException {
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create().fromJson(new URL(FETCH_URL).toString(), new TypeToken<Map<String, CloudDomains>>() {
                }.getType());
    }
    public List<CloudDomains> getCurrent() {
        return new ArrayList<>(current.values());
    }
}
