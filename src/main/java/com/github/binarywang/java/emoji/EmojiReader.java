package com.github.binarywang.java.emoji;

import com.github.binarywang.java.emoji.model.Emoji4Unicode;
import com.github.binarywang.java.emoji.model.Emoji4Unicode.Category;
import com.github.binarywang.java.emoji.model.Emoji4Unicode.Element;
import com.github.binarywang.java.emoji.model.Emoji4Unicode.SubCategory;
import com.google.common.collect.Maps;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.joda.time.Interval;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Binary Wang
 */
public class EmojiReader {
    private static final Logger logger = LoggerFactory.getLogger(EmojiReader.class);
    private static final String URL = "https://raw.githubusercontent.com/googlei18n/emoji4unicode/master/data/emoji4unicode.xml";

    private static final String TRIM_PATTERN = "[^0-9A-F]*";
    private Emoji4Unicode emoji = null;
    private Map<List<Integer>, String> sb2UnicodeMap = null;

    /**
     * 从相关文件读取并解析得到Emoji4Unicode对象
     *
     * @param onlyFromLocal 是否只从本地读取
     * @return Emoji4Unicode对象
     */
    Emoji4Unicode read(boolean onlyFromLocal) {
        if (emoji != null) {
            return emoji;
        }

        long beginTime = System.currentTimeMillis();

        XStream xstream = new XStream(new DomDriver());
        xstream.processAnnotations(Emoji4Unicode.class);

        if (onlyFromLocal) {
            logger.info("==============from local file==============");
            emoji = (Emoji4Unicode) xstream.fromXML(EmojiReader.class.getResourceAsStream("/emoji4unicode.xml"));
            return emoji;
        }

        try {
            emoji = (Emoji4Unicode) xstream.fromXML(new URL(URL));
            logger.info("==============from url==============");
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (emoji != null) {
            String interval = new Interval(beginTime, System.currentTimeMillis())
                    .toPeriod().toString().replace("PT", "").replace("M", "分")
                    .replace("S", "秒");
            logger.info("从网络读取总耗时： " + interval);
            return emoji;
        }

        logger.info("==============from local file==============");
        emoji = (Emoji4Unicode) xstream.fromXML(
                EmojiReader.class.getResourceAsStream("/emoji4unicode.xml"));
        return emoji;

    }

    public Map<List<Integer>, String> getSb2UnicodeMap() {
        if (sb2UnicodeMap != null) {
            return sb2UnicodeMap;
        }

        sb2UnicodeMap = Maps.newHashMap();

        Emoji4Unicode emoji4Unicode = read(false);
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
