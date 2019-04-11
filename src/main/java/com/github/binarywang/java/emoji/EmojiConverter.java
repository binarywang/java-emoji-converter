package com.github.binarywang.java.emoji;

import com.github.binarywang.java.emoji.util.EmojiUtils;
import com.vdurmont.emoji.EmojiParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Binary Wang
 */
public class EmojiConverter {
    private EmojiReader emojiReader = null;
    private final static EmojiConverter INSTANCE = new EmojiConverter();

    private EmojiConverter() {
    }

    public static EmojiConverter getInstance() {
        return INSTANCE;
    }

    /**
     * convert emoji string with unicode/softbank to strings with aliases
     *
     * @param input 输入的字符串内容
     * @return result string
     */
    public String toAlias(String input) {
        if (EmojiUtils.containsSbEmoji(input)) {
            input = this.sb2Unicode(input);
        }

        String result = EmojiParser.parseToAliases(input);
        if (result.equals(EmojiParser.parseToAliases(result))) {
            return result;
        } else {
            return EmojiParser.parseToAliases(result);
        }

    }

    private String sb2Unicode(String input) {
        if (this.emojiReader == null) {
            this.emojiReader = new EmojiReader();
        }

        final Map<List<Integer>, String> sb2UnicodeMap = this.emojiReader.getSb2UnicodeMap();

        StringBuilder result = new StringBuilder();
        int[] codePoints = toCodePointArray(input);
        for (int i = 0; i < codePoints.length; i++) {
            List<Integer> key2;
            if (i + 1 < codePoints.length) {
                key2 = new ArrayList<>();
                key2.add(codePoints[i]);
                key2.add(codePoints[i + 1]);

                if (sb2UnicodeMap.containsKey(key2)) {
                    String value = sb2UnicodeMap.get(key2);
                    if (value != null) {
                        result.append(value);
                    }

                    i++;
                    continue;
                }
            }

            List<Integer> key1 = new ArrayList<>();
            key1.add(codePoints[i]);
            if (sb2UnicodeMap.containsKey(key1)) {
                String value = sb2UnicodeMap.get(key1);
                if (value != null) {
                    result.append(value);
                }
                continue;
            }

            result.append(Character.toChars(codePoints[i]));

        }

        return result.toString();
    }

    private static int[] toCodePointArray(String str) {
        char[] ach = str.toCharArray();
        int len = ach.length;
        int[] acp = new int[Character.codePointCount(ach, 0, len)];
        int j = 0;

        for (int i = 0, cp; i < len; i += Character.charCount(cp)) {
            cp = Character.codePointAt(ach, i);
            acp[j++] = cp;
        }

        return acp;
    }

    /**
     * convert emoji string with unicode/softbank to strings with html code
     *
     * @param input 输入的字符串内容
     * @return result string
     */
    public String toHtml(String input) {
        if (EmojiUtils.containsSbEmoji(input)) {
            input = sb2Unicode(input);
        }

        String result = EmojiParser.parseToHtmlDecimal(input);
        if (result.equals(EmojiParser.parseToHtmlDecimal(result))) {
            return result;
        } else {
            return EmojiParser.parseToHtmlDecimal(result);
        }
    }

    /**
     * convert string with softbank/alias emoji to strings with unicode
     *
     * @param input 输入的字符串内容
     * @return result string
     */
    public String toUnicode(String input) {
        if (EmojiUtils.containsSbEmoji(input)) {
            input = sb2Unicode(input);
        }

        String result = EmojiParser.parseToUnicode(input);
        if (result.equals(EmojiParser.parseToUnicode(result))) {
            return result;
        } else {
            return EmojiParser.parseToUnicode(result);
        }
    }
}
