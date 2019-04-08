package tk.itiger.htmlparser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlParser {

    public static String getUserInfo(String url) throws IOException {
        int count = 0;
        StringBuilder result = new StringBuilder();
        Document document = Jsoup.connect(url).get();
        Elements[] elements = {
                document.getElementsByClass("user-profile-card__name"),
                document.getElementsByClass("user-profile-card__level")
        };
        String[] patterns = {"[A-Za-zА-Яа-я0-9 ]+</h2>","\\d{1,2}</span> уровень"};
        for(String pattern : patterns) {
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(elements[count++].toString());
            while (m.find()){
                result.append(m.group().split("</")[0]).append(" ");
            }
        }
        result.append("уровень");
        return result.toString();
    }
}
