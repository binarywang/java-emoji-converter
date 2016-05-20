package cn.binarywang.java.emoji;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.junit.Assert;
import org.junit.Test;

import cn.binarywang.java.emoji.model.Emoji4Unicode;

public class EmojiReaderTest {

    @Test
    public void testRead() {
        Emoji4Unicode a = EmojiReader.read();
        System.err.println(ToStringBuilder.reflectionToString(a));
        Assert.assertNotNull(a);
    }

    @Test
    public void testReadSb2UnicodeMap() {
        Map<List<Integer>, String> sb2UnicodeMap = EmojiReader
            .readSb2UnicodeMap();

        System.err.println(sb2UnicodeMap);
        Assert.assertNotNull(sb2UnicodeMap);
    }

}
