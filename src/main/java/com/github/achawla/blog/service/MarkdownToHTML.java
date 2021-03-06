package com.github.achawla.blog.service;

import org.commonmark.node.*;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

public class MarkdownToHTML {


    public String convertMDToHTML(String input){


        Parser parser = Parser.builder().build();
        Node document = parser.parse(input);
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        return renderer.render(document);  // "<p>This is <em>Sparta</em></p>\n"
    }
}
