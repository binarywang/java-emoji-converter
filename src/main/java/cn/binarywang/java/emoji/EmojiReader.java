package cn.binarywang.java.emoji;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import cn.binarywang.java.emoji.model.Emoji4Unicode;

public class EmojiReader {
    private static final String url = "https://raw.githubusercontent.com/googlei18n/emoji4unicode/master/data/emoji4unicode.xml";

    public static Emoji4Unicode read() {
        XStream xstream = new XStream(new DomDriver());
        xstream.processAnnotations(Emoji4Unicode.class);

        Emoji4Unicode emoji = null;
        try {
            emoji = (Emoji4Unicode) xstream.fromXML(new URL(url));
            System.err.println("==============from url==============");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        if (emoji == null) {
            System.err.println("==============from local file==============");
            return  (Emoji4Unicode) xstream.fromXML(
            ClassLoader.getSystemResourceAsStream("emoji4unicode.xml"));
        }

        return emoji;
    }

    public static void main(String[] args) {
        Emoji4Unicode a = EmojiReader.read();
        System.err.println(ToStringBuilder.reflectionToString(a));
    }
}
