package cn.binarywang.java.emoji;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import cn.binarywang.java.emoji.model.Emoji4Unicode;

public class EmojiReader {

    public static Emoji4Unicode read() {
        XStream xstream = new XStream(new DomDriver());
        xstream.processAnnotations(Emoji4Unicode.class);

        Emoji4Unicode a = (Emoji4Unicode) xstream.fromXML(
            ClassLoader.getSystemResourceAsStream("emoji4unicode.xml"));

        return a;
    }

    public static void main(String[] args) {
        Emoji4Unicode a = EmojiReader.read();
        System.err.println(ToStringBuilder.reflectionToString(a));
    }
}
