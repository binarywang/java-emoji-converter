# Java Emoji Converter （Emoji表情转换工具）

Emoji转换工具，便于各种规格客户端生成的Emoji字符串转换成另外一种格式。
A tool to convert emoji string among each type,  like softbank emoji, unicode emoji, alias emoji, html emoji.

[![Build Status](https://travis-ci.org/binarywang/java-emoji-converter.svg?branch=develop)](https://travis-ci.org/binarywang/java-emoji-converter)
![Maven Central](https://img.shields.io/maven-central/v/com.github.binarywang/java-emoji-converter.svg)

When converting softbank emoji to unicode, we utilize this file:
https://raw.githubusercontent.com/googlei18n/emoji4unicode/master/data/emoji4unicode.xml

## Quick Start

Add this in your maven pom file:

```xml
<dependency>
  <groupId>com.github.binarywang</groupId>
  <artifactId>java-emoji-converter</artifactId>
  <version>0.0.1</version>
</dependency>
```

## Usage (from junit test)：

    private EmojiConverter emojiConverter = EmojiConverter.getInstance();

    @Test
    public void testToAlias() {
        String str = "  An 😃😀awesome 😃😃string with a few 😃😉emojis!";
        String alias = this.emojiConverter.toAlias(str);
        System.out.println(str);
        System.out.println("EmojiConverterTest.testToAlias()=====>");
        System.out.println(alias);
        Assert.assertEquals(
            ":no_good: :ok_woman: :couple_with_heart:An :smiley::grinning:awesome :smiley::smiley:string with a few :smiley::wink:emojis!",
            alias);
    }

    @Test
    public void testToHtml() {
        String str = "  An 😀😃awesome 😃😃string with a few 😉😃emojis!";
        String result = this.emojiConverter.toHtml(str);
        System.out.println(str);
        System.out.println("EmojiConverterTest.testToHtml()=====>");
        System.out.println(result);
        Assert.assertEquals(
            "&#128581; &#128582; &#128145;An &#128512;&#128515;awesome &#128515;&#128515;string with a few &#128521;&#128515;emojis!",
            result);
    }

    @Test
    public void testToUnicode() {
        String str = "   :smiley: :grinning: :wink:";
        String result = this.emojiConverter.toUnicode(str);
        System.err.println(str);
        System.err.println("EmojiConverterTest.testToUnicode()=====>");
        System.err.println(result);
        Assert.assertEquals("🙅 🙆 💑 😃 😀 😉", result);
    }
