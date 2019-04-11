package com.github.binarywang.java.emoji;

import com.github.binarywang.java.emoji.model.Emoji4Unicode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

import java.util.List;
import java.util.Map;

import static org.testng.AssertJUnit.*;

public class EmojiReaderTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private EmojiReader emojiReader = new EmojiReader();

    @BeforeTest
    public void init(){
        BasicConfigurator.configure();
    }

    @Test
    public void testRead() {
        Emoji4Unicode a = this.emojiReader.read(false);
        this.logger.info(ToStringBuilder.reflectionToString(a));
        assertNotNull(a);
    }

    @Test
    public void testGetSb2UnicodeMap() {
        Map<List<Integer>, String> sb2UnicodeMap = this.emojiReader.getSb2UnicodeMap();

        this.logger.info(sb2UnicodeMap.toString());
        assertNotNull(sb2UnicodeMap);
    }

}
