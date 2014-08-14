/*
 ImpressiveCode Depress Framework
 Copyright (C) 2013  ImpressiveCode contributors

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.impressivecode.depress.its.jira;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.impressivecode.depress.support.sourcecrawler.SourceCrawlerXMLTest;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;
/**
 * @author Maciej Borkowski, Capgemini Poland
 */
public class JiraFileParserTest {
    private final static String testedFilePath = JiraFileParserTest.class.getResource("test.xml").getPath();
    private JiraFileParser parser;
    private File file ;
    private String expression;
    
    @Before
    public void setUp() throws JAXBException {
        parser = new JiraFileParser();
        file = new File(testedFilePath);
        expression = "/rss/channel/item/priority[not(preceding::priority/. = .)]";
    }
    
    @Test
    public void shouldParseFile() throws XPathExpressionException, SAXException, IOException, ParserConfigurationException {
        //when
        List<String> list = parser.parseXPath(file, expression);
        //then
        assertTrue(list.contains("ONE"));
        assertTrue(list.contains("TWO"));
        assertTrue(list.contains("THREE"));
    }
    
    @Test
    public void shouldParseUnique() throws XPathExpressionException, SAXException, IOException, ParserConfigurationException {
        //when
        List<String> list = parser.parseXPath(file, expression);
        //then
        assertTrue(list.size() == 3);
    }
}
