package tk.itiger.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExternalContextReader {
    
    public static Map<String, String> getDataContext(String fileName) {
        Map<String, String> map = new HashMap<>();
        try {
            String row = null;
            BufferedReader reader = new BufferedReader(new FileReader(".\\src\\main\\resources\\" + fileName));
            while ((row = reader.readLine()) != null){
                if (row.startsWith("#")){
                    continue;
                }else {
                    String[] properties = row.split("=");
                    map.put(properties[0].trim(), properties[1].trim());
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return map;
    }
 }
