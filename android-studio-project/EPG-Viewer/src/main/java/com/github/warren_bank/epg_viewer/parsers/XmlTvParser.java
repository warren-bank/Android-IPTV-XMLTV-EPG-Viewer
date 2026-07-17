package com.github.warren_bank.epg_viewer.parsers;

import se.kmdev.tvepg.epg.domain.EPGChannel;
import se.kmdev.tvepg.epg.domain.EPGEvent;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class XmlTvParser {
    // Standard XMLTV date format: 20260716171500 +0000
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss Z", Locale.US);

    public static Map<EPGChannel, List<EPGEvent>> parseXml(InputStream inputStream) throws Exception {
        Map<String, EPGChannel> channelMap = new LinkedHashMap<>();
        Map<EPGChannel, List<EPGEvent>> parsedData = new LinkedHashMap<>();
       
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        XmlPullParser parser = factory.newPullParser();
        parser.setInput(inputStream, null);

        int eventType = parser.getEventType();
        EPGChannel currentChannel = null;
        String currentChannelId = null;
        String currentTitle = null;
        long startTime = 0;
        long endTime = 0;

        while (eventType != XmlPullParser.END_DOCUMENT) {
            String tagName = parser.getName();
           
            switch (eventType) {
                case XmlPullParser.START_TAG:
                    if ("channel".equals(tagName)) {
                        String id = parser.getAttributeValue(null, "id");
                        currentChannel = new EPGChannel(id, null, null);
                        channelMap.put(id, currentChannel);
                    } else if ("display-name".equals(tagName) && currentChannel != null) {
                        currentChannel.setName(parser.nextText());
                    } else if ("icon".equals(tagName) && currentChannel != null) {
                        currentChannel.setImageURL(parser.getAttributeValue(null, "src"));
                    } else if ("programme".equals(tagName)) {
                        currentChannelId = parser.getAttributeValue(null, "channel");
                        startTime = dateFormat.parse(parser.getAttributeValue(null, "start")).getTime();
                        endTime = dateFormat.parse(parser.getAttributeValue(null, "stop")).getTime();
                    } else if ("title".equals(tagName) && currentChannelId != null) {
                        currentTitle = parser.nextText();
                    }
                    break;

                case XmlPullParser.END_TAG:
                    if ("programme".equals(tagName) && currentChannelId != null) {
                        EPGChannel channel = channelMap.get(currentChannelId);
                        if (channel != null) {
                            EPGEvent event = new EPGEvent(startTime, endTime, currentTitle);
                           
                            if (!parsedData.containsKey(channel)) {
                                parsedData.put(channel, new ArrayList<>());
                            }
                            parsedData.get(channel).add(event);
                        }
                        // Reset temporary programme state
                        currentChannelId = null;
                        currentTitle = null;
                    } else if ("channel".equals(tagName)) {
                        currentChannel = null;
                    }
                    break;
            }
            eventType = parser.next();
        }
        return parsedData;
    }
}
