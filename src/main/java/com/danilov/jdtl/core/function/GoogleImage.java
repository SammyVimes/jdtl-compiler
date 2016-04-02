package com.danilov.jdtl.core.function;

import com.danilov.jdtl.core.context.Context;
import com.danilov.jdtl.core.util.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Semyon on 02.04.2016.
 */
public class GoogleImage extends BaseFunction {

    private static final Logger LOGGER = LoggerFactory.getLogger(GoogleImage.class);

    private static final String QUERY = "q";
    private static Set<String> required = new HashSet<>(Arrays.asList(QUERY));

    @Nonnull
    @Override
    public Set<String> getRequiredArguments() {
        return required;
    }

    @Nonnull
    @Override
    public String name() {
        return "googleimage";
    }

    @Override
    public String process(@Nonnull final Map<String, String> args, @Nonnull final Context context) {
        String q = args.get(QUERY);

        final String htmlImg = "<img src=\"{}\"/>";

        try {
            URL url = new URL("http://api.duckduckgo.com/?q=" + q + "&format=json&pretty=1&t=jdtlcompiler");

            URLConnection connection = url.openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");

            String line;
            StringBuilder builder = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }

            JSONObject json = new JSONObject(builder.toString());
            TextUtils.format(htmlImg, json.getString("Image"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {

        }

        return "";
    }

}
