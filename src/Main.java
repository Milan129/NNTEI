import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static String html;

    public static void main(String[] args) {
        String imageResource = "<img.*?src=\"(.*?)\"";
        List<String> attributesToRemove = new LinkedList();
        attributesToRemove.add("<img (.*?)(src=\"(.*?))\"(.*?)[/]?>");
        attributesToRemove.add("<a (.*?)(href=\"(.*?))\"(.*?)[/]?>");
        try {
            html = loadHTML("https://www.piskelapp.com/");
            findImageURL(html, imageResource);
            html = removeAttributesFromHTML(html, attributesToRemove);
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println(html);
    }

    public static void findImageURL(String html, String rv) {
        Pattern pattern = Pattern.compile(rv);
        Matcher matcher = pattern.matcher(html);
        while (matcher.find()) {
            System.out.println(matcher.group(1));
        }
    }

    public static String removeAttributesFromHTML(String html, List<String> rvs) {
        for (String rv : rvs) {
            Pattern pattern = Pattern.compile(rv);
            Matcher matcher = pattern.matcher(html);
            while (matcher.find()) {
                System.out.println(matcher.group(1));
                System.out.println(matcher.group(4));
                html = html.replaceFirst(matcher.group(1), "");
                html = html.replaceFirst(matcher.group(4), "");
            }
        }
        return html;
    }

    public static String loadHTML(String address) {
        String html = "";
        try {
            URL url = new URL(address);
            Scanner scanner = new Scanner(url.openStream());
            while (scanner.hasNext()) {
                html += scanner.nextLine();
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return html;
    }
}