package com.mongodbdemo.propertysource;

import com.alibaba.fastjson.JSONObject;
import org.springframework.boot.env.OriginTrackedMapPropertySource;
import org.springframework.boot.env.PropertySourceLoader;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;

import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author: wr
 * @description:
 * @date: 2020-08-09 12:43
 * @since:
 */
public class JsonPropertySourceLoader implements PropertySourceLoader {

    public static final String JSON_FILE_EXTENSION = "json";

    @Override
    public String[] getFileExtensions() {
        return new String[]{"json"};
    }

    @Override
    public List<PropertySource<?>> load(String name, Resource resource) throws IOException {
        Map<String, ?> properties = loadProperties(resource);
        if (properties.isEmpty()) {
            return Collections.emptyList();
        }
        return Collections
                .singletonList(new OriginTrackedMapPropertySource(name, Collections.unmodifiableMap(properties), true));
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private Map<String, Object> loadProperties(Resource resource) throws IOException {
        String filename = resource.getFilename();
        if (filename != null && filename.endsWith(JSON_FILE_EXTENSION)) {
            // 解析json
          return parseJosn(resource);
        }
        return Collections.emptyMap();
    }

    private Map<String,Object> parseJosn(Resource resource) throws IOException {
        File file = resource.getFile();
        String jsonString = readJsonFile(file);

        JSONObject jsonObject = JSONObject.parseObject(jsonString);


        return jsonObject.getInnerMap();
    }

    public static String readJsonFile(File file) {
        String jsonStr = "";
        try {
            FileReader fileReader = new FileReader(file);
            Reader reader = new InputStreamReader(new FileInputStream(file),"utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
