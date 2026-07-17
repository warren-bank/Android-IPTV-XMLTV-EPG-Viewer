package com.github.warren_bank.epg_viewer.utils;

import com.github.warren_bank.epg_viewer.parsers.XmlTvParser;
import com.github.warren_bank.epg_viewer.utils.EPGDataImpl;

import se.kmdev.tvepg.epg.domain.EPGChannel;
import se.kmdev.tvepg.epg.domain.EPGEvent;

import android.content.Context;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FileUtils {
    private static String privateFileName = "epg.xmltv";

    public static boolean writeFile(InputStream inputStream, Context context) {
        try {
            writeFile(
                inputStream,
                context.openFileOutput(privateFileName, Context.MODE_PRIVATE)
            );
            return true;
        }
        catch(Exception e) {
            return false;
        }
    }

    public static void writeFile(InputStream inputStream, OutputStream outputStream) {
        try {
            byte[] buf;
            int length;

            buf = new byte[8192];
            while ((length = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, length);
            }

            inputStream.close();
            outputStream.close();
        }
        catch(Exception e) {}
    }

    public static EPGDataImpl readFile(Context context) {
        Map<EPGChannel, List<EPGEvent>> data;
        try {
            data = XmlTvParser.parseXml(
                context.openFileInput(privateFileName)
            );
        }
        catch(Exception e) {
            data = null;
        }
        return new EPGDataImpl(data);
    }
}
