package com.balayar.statussaver;

import android.os.Environment;

import java.io.File;

public class Common {

    public static final int GRID_COUNT = 2;

    public static final File STATUS_DIRECTORY = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Android/media/com.whatsapp/WhatsApp/Media/.Statuses");

    public static String APP_DIR="/SavedStatus";

}
