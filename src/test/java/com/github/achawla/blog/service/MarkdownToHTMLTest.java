package com.github.achawla.blog.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class MarkdownToHTMLTest {

    String TEST_MD_TEXT_1 = "This is *Sparta*";
    String TEST_MD_TEXT_2 = "# H1 hello  - \n\n Example\n=======\n\nSome more text";

    @Autowired
    MarkdownToHTML markdownToHTML;

    @Before
    public void setUp() throws Exception {
        markdownToHTML = new MarkdownToHTML();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void convertMDToHTML_1() {

        String output = markdownToHTML.convertMDToHTML(TEST_MD_TEXT_1);

        System.out.println(">>>>output is ****"+output);
        assertEquals("output doesn't matches","<p>This is <em>Sparta</em></p>",output.trim());
    }

    @Test
    public void convertMDToHTML_2() {

        String output = markdownToHTML.convertMDToHTML(TEST_MD_TEXT_2);

        System.out.println(">>>>output is ****"+output);
        assertEquals("output doesn't matches","<h1>H1 hello  -</h1>\n<h1>Example</h1>\n<p>Some more text</p>",output.trim());
    }
}