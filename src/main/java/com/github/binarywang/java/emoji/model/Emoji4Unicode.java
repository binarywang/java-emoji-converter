package com.github.binarywang.java.emoji.model;


import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("emoji4unicode")
public class Emoji4Unicode {
    @XStreamImplicit(itemFieldName = "category")
    private List<Category> categories;

    @XStreamAlias("category")
    public static class Category {
        @XStreamImplicit(itemFieldName = "subcategory")
        private List<SubCategory> subCategories;

        @XStreamAsAttribute
        private String name;

        @Override
        public String toString() {
            return toSimpleString(this);
        }

        public List<SubCategory> getSubCategories() {
            return this.subCategories;
        }

        public void setSubCategories(List<SubCategory> subCategories) {
            this.subCategories = subCategories;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class SubCategory {
        @Override
        public String toString() {
            return toSimpleString(this);
        }

        @XStreamImplicit(itemFieldName = "e")
        private List<Element> elements;

        @XStreamAsAttribute
        private String name;

        public List<Element> getElements() {
            return this.elements;
        }

        public void setElements(List<Element> elements) {
            this.elements = elements;
        }

    }

    public static class Element {
        @Override
        public String toString() {
            return toSimpleString(this);
        }

        @XStreamAsAttribute
        private String name;

        @XStreamAsAttribute
        private String docomo;

        @XStreamAsAttribute
        private String google;

        @XStreamAsAttribute
        private String id;

        @XStreamAsAttribute
        private String kddi;

        @XStreamAsAttribute
        private String softbank;

        @XStreamAsAttribute
        private String unicode;

        @XStreamAsAttribute
        private String text_fallback;

        @XStreamAsAttribute
        private String text_repr;

        @XStreamAsAttribute
        private String img_from;

        @XStreamAsAttribute
        private String in_proposal;

        @XStreamAsAttribute
        private String glyphRefID;

        @XStreamAsAttribute
        private String oldname;

        @XStreamAsAttribute
        private String design;

        @XStreamImplicit(itemFieldName = "ann")
        private List<String> ann;

        @XStreamImplicit(itemFieldName = "desc")
        private List<String> desc;

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDocomo() {
            return this.docomo;
        }

        public void setDocomo(String docomo) {
            this.docomo = docomo;
        }

        public String getGoogle() {
            return this.google;
        }

        public void setGoogle(String google) {
            this.google = google;
        }

        public String getId() {
            return this.id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getKddi() {
            return this.kddi;
        }

        public void setKddi(String kddi) {
            this.kddi = kddi;
        }

        public String getSoftbank() {
            return this.softbank;
        }

        public void setSoftbank(String softbank) {
            this.softbank = softbank;
        }

        public String getUnicode() {
            return this.unicode;
        }

        public void setUnicode(String unicode) {
            this.unicode = unicode;
        }

        public String getText_fallback() {
            return this.text_fallback;
        }

        public void setText_fallback(String text_fallback) {
            this.text_fallback = text_fallback;
        }

        public String getText_repr() {
            return this.text_repr;
        }

        public void setText_repr(String text_repr) {
            this.text_repr = text_repr;
        }

        public String getImg_from() {
            return this.img_from;
        }

        public void setImg_from(String img_from) {
            this.img_from = img_from;
        }

        public String getIn_proposal() {
            return this.in_proposal;
        }

        public void setIn_proposal(String in_proposal) {
            this.in_proposal = in_proposal;
        }

        public String getGlyphRefID() {
            return this.glyphRefID;
        }

        public void setGlyphRefID(String glyphRefID) {
            this.glyphRefID = glyphRefID;
        }

        public String getOldname() {
            return this.oldname;
        }

        public void setOldname(String oldname) {
            this.oldname = oldname;
        }

        public List<String> getAnn() {
            return this.ann;
        }

        public void setAnn(List<String> ann) {
            this.ann = ann;
        }

        public List<String> getDesc() {
            return this.desc;
        }

        public void setDesc(List<String> desc) {
            this.desc = desc;
        }

    }

    public List<Category> getCategories() {
        return this.categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    private static final String LINE_SEPARATOR = "\n";

    public static String toSimpleString(Object obj) {
        String toStringResult = ToStringBuilder.reflectionToString(obj,
            new SimpleToStringStyle());
        String[] split = toStringResult.split(LINE_SEPARATOR);
        StringBuilder result = new StringBuilder();
        for (String string : split) {
            if (string.endsWith("<null>")) {
                continue;
            }
            result.append(string + LINE_SEPARATOR);
        }

        if (result.length() == 0) {
            return "";
        }

        return result.deleteCharAt(result.length() - 1).toString();
    }

    static class SimpleToStringStyle extends ToStringStyle {
        private static final long serialVersionUID = 7718004986394868800L;

        public SimpleToStringStyle() {
            this.setContentStart("[");
            this.setFieldSeparator(LINE_SEPARATOR + "  ");
            this.setFieldSeparatorAtStart(true);
            this.setContentEnd(LINE_SEPARATOR + "]");
            this.setUseShortClassName(true);
            this.setUseIdentityHashCode(false);
        }
    }
}

