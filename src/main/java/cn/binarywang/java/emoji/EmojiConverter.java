package cn.binarywang.java.emoji;

import java.util.ArrayList;
import java.util.List;

import com.vdurmont.emoji.EmojiParser;

public class EmojiConverter {

    private static EmojiConverter instance = new EmojiConverter();
    private EmojiConverter() {
    }

    public static EmojiConverter getInstance() {
        return instance;
    }

    /**
     * convert emoji string with unicode/softbank to strings with aliases
     * 
     * @param input
     * @return
     */
    public String toAlias(String input) {
        if (EmojiUtils.containsSbEmoji(input)) {
            input = sb2Unicode(input);
        }

        String result = EmojiParser.parseToAliases(input);
        if (result.equals(EmojiParser.parseToAliases(result))) {
            return result;
        } else {
            return EmojiParser.parseToAliases(result);
        }

    }

    private static String sb2Unicode(String input) {
        StringBuilder result = new StringBuilder();
        int[] codePoints = toCodePointArray(input);
        for (int i = 0; i < codePoints.length; i++) {
            List<Integer> key2 = null;
            if (i + 1 < codePoints.length) {
                key2 = new ArrayList<>();
                key2.add(codePoints[i]);
                key2.add(codePoints[i + 1]);
                
                if (EmojiReader.readSb2UnicodeMap().containsKey(key2)) {
                    String value = EmojiReader.readSb2UnicodeMap().get(key2);
                    if (value != null) {
                        result.append(value);
                    }
                    
                    i++;
                    continue;
                }
            }

            List<Integer> key1 = new ArrayList<>();
            key1.add(codePoints[i]);
            if (EmojiReader.readSb2UnicodeMap().containsKey(key1)) {
                String value = EmojiReader.readSb2UnicodeMap().get(key1);
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
     * @param input
     * @return
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
     * @param input
     * @return
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
