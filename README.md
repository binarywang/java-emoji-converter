[![码云Gitee](https://gitee.com/binary/java-emoji-converter/badge/star.svg?theme=blue)](https://gitee.com/binary/java-emoji-converter)
[![Github](http://github-svg-buttons.herokuapp.com/star.svg?user=binarywang&repo=java-emoji-converter&style=flat&background=1081C1)](https://github.com/binarywang/java-emoji-converter)
[![Build Status](https://travis-ci.org/binarywang/java-emoji-converter.svg?branch=master)](https://travis-ci.org/binarywang/java-emoji-converter)
![Maven Central](https://img.shields.io/maven-central/v/com.github.binarywang/java-emoji-converter.svg)

# Java Emoji Converter（Emoji 表情转换工具）

Emoji转换工具，便于各种规格客户端生成的Emoji字符串转换成另外一种格式。
A tool to convert emoji string among each type,  like softbank emoji, unicode emoji, alias emoji, html emoji.

When converting softbank emoji to unicode, we utilize this file:
https://raw.githubusercontent.com/googlei18n/emoji4unicode/master/data/emoji4unicode.xml

## Quick Start 快速入门

Add this in your maven pom file（将以下内容加入你的maven的pom文件中）：
```xml
<dependency>
  <groupId>com.github.binarywang</groupId>
  <artifactId>java-emoji-converter</artifactId>
  <version>1.0.0</version>
</dependency>
```

## Usage (from junit test)：用法（摘自单元测试代码）

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
