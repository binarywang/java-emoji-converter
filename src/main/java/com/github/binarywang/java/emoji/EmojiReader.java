package com.github.binarywang.java.emoji;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.joda.time.Interval;

import com.github.binarywang.java.emoji.model.Emoji4Unicode;
import com.github.binarywang.java.emoji.model.Emoji4Unicode.Category;
import com.github.binarywang.java.emoji.model.Emoji4Unicode.Element;
import com.github.binarywang.java.emoji.model.Emoji4Unicode.SubCategory;
import com.google.common.collect.Maps;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class EmojiReader {
    private static final String url = "https://raw.githubusercontent.com/googlei18n/emoji4unicode/master/data/emoji4unicode.xml";

    private static final String TRIM_PATTERN = "[^0-9A-F]*";
    private static Emoji4Unicode emoji = null;
    private static Map<List<Integer>, String> sb2UnicodeMap = null;

    public static Emoji4Unicode read() {
        if (emoji != null) {
            return emoji;
        }

        long beginTime = System.currentTimeMillis();

        XStream xstream = new XStream(new DomDriver());
        xstream.processAnnotations(Emoji4Unicode.class);

        try {
            emoji = (Emoji4Unicode) xstream.fromXML(new URL(url));
            System.err.println("==============from url==============");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        if (emoji == null) {
            System.err.println("==============from local file==============");
            return (Emoji4Unicode) xstream.fromXML(
                EmojiReader.class.getResourceAsStream("/emoji4unicode.xml"));
        }

        String interval = new Interval(beginTime, System.currentTimeMillis())
            .toPeriod().toString().replace("PT", "").replace("M", "分")
            .replace("S", "秒");
        System.err.println(" 耗时： " + interval);
        return emoji;
    }

    public static Map<List<Integer>, String> readSb2UnicodeMap() {
        if (sb2UnicodeMap != null) {
            return sb2UnicodeMap;
        }

        sb2UnicodeMap = Maps.newHashMap();

        Emoji4Unicode emoji4Unicode = read();
        for (Category category : emoji4Unicode.getCategories()) {
            List<SubCategory> subCategories = category.getSubCategories();
            if (subCategories == null) {
                continue;
            }

            for (SubCategory subCategory : subCategories) {
                List<Element> elements = subCategory.getElements();
                if (elements == null) {
                    continue;
                }

                for (Element element : elements) {
                    List<Integer> fromCodePoints = new ArrayList<>();
                    String fromValue = element.getSoftbank();
                    if (fromValue == null) {
                        continue;
                    }

                    if (fromValue.length() > 6) {
                        String[] froms = fromValue.split("\\+");
                        for (String part : froms) {
                            fromCodePoints.add(Integer.parseInt(
                                part.replaceAll(TRIM_PATTERN, ""), 16));
                        }
                    } else {
                        fromCodePoints.add(Integer.parseInt(
                            fromValue.replaceAll(TRIM_PATTERN, ""), 16));
                    }

                    String toValue = element.getUnicode();
                    if (toValue == null) {
                        sb2UnicodeMap.put(fromCodePoints, null);
                        continue;
                    }

                    StringBuilder toBuilder = new StringBuilder();
                    if (toValue.length() > 6) {
                        String[] tos = toValue.split("\\+");
                        for (String part : tos) {
                            toBuilder.append(Character.toChars(Integer.parseInt(
                                part.replaceAll(TRIM_PATTERN, ""), 16)));
                        }
                    } else {
                        toBuilder.append(Character.toChars(Integer.parseInt(
                            toValue.replaceAll(TRIM_PATTERN, ""), 16)));
                    }

                    sb2UnicodeMap.put(fromCodePoints, toBuilder.toString());
                }
            }

        }

        return sb2UnicodeMap;
    }

}
