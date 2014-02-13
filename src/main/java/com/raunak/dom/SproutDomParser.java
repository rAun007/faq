package com.raunak.dom;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

public class SproutDomParser {

    private static final LoadingCache<String, XPathExpression> XPATH_CACHE = CacheBuilder.newBuilder().build(
            new CacheLoader<String, XPathExpression>() {
                @Override
                public XPathExpression load(String key) throws Exception {

                    return XPathFactory.newInstance().newXPath().compile(key);
                }
            });

    public static void main(String[] args) {

        boolean video = false;
        boolean audio = false;
        boolean videoAutoPlay = false;
        boolean lock = false;
        boolean audioAutoPlay = false;

        Document document = getDocument();

        NodeList sourceNodes = getNodeList(document, "//elements/element/source");

        for (int i = 0; i < sourceNodes.getLength(); i++) {
            String sourceRef = sourceNodes.item(i).getTextContent();
            if ("com.sproutbuilder.components.video.VideoComponent".equals(sourceRef)) {
                video = true;
                if (!videoAutoPlay) {
                    NodeList autoPlayNodeList = getNodeList(sourceNodes.item(i), "../componentProperties/property[@name='autoplay']");
                    for (int j = 0; j < autoPlayNodeList.getLength(); j++) {
                        String value = autoPlayNodeList.item(j).getAttributes().getNamedItem("value").getTextContent();
                        if ("always".equals(value)) {
                            videoAutoPlay = true;
                            break;
                        }
                    }
                }
            } else if ("com.sproutbuilder.components.audio.AudioComponent".equals(sourceRef)) {
                audio = true;
                if (!audioAutoPlay) {
                    NodeList autoPlayNodeList = getNodeList(sourceNodes.item(i), "../componentProperties/property[@name='autoplay']");
                    for (int j = 0; j < autoPlayNodeList.getLength(); j++) {
                        String value = autoPlayNodeList.item(j).getAttributes().getNamedItem("value").getTextContent();
                        if ("always".equals(value)) {
                            audioAutoPlay = true;
                            break;
                        }
                    }
                }
            }
        }

        NodeList lockNodeList = getNodeList(document, "/Application/orientation");

        for (int i = 0; i < lockNodeList.getLength(); i++) {
            String lockValue = lockNodeList.item(i).getTextContent();
            if ("portrait".equalsIgnoreCase(lockValue) || "landscape".equalsIgnoreCase(lockValue)) {
                lock = true;
            }
        }
        
        NodeList format = getNodeList(document, "/Application/applicationWidth");
        
        for (int i = 0; i < format.getLength(); i++) {
            
            System.out.println(format.item(i).getTextContent());
        }

        System.out.println("video::" + video + ", audio::" + audio + ", videoAutoPlay::" + videoAutoPlay + ", audioAutoPlay::"
                + audioAutoPlay + ", lock::" + lock);
    }

    private static Document getDocument() {

        try {
            return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse("/home/raunak/Desktop/Sprout1.xml");
        } catch (SAXException | IOException | ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static NodeList getNodeList(Node node, String path) {

        try {
            return (NodeList) compile(path).evaluate(node, XPathConstants.NODESET);
        } catch (XPathExpressionException e) {
            throw new RuntimeException(e);
        }
    }

    private static XPathExpression compile(String path) {

        try {
            return XPATH_CACHE.get(path);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}