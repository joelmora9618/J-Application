package com.example.jmora.webservicesoap.Utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.example.jmora.webservicesoap.Data.Constants;
import com.example.jmora.webservicesoap.Models.ProgressMessage;
import com.example.jmora.webservicesoap.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;

/**
 * Created by JMora on 22/08/2017.
 */

public class ProgressUtils {
    protected static Handler _progress;

    protected static void sendProgress(int nPrg, double coef, int rTittle) {

        int currentPrgress = (int) (nPrg * coef);

        ProgressMessage prgMsg = new ProgressMessage();
        prgMsg.nProgress = currentPrgress;
        prgMsg.tittle = rTittle;

        Message msg = new Message();
        msg.obj = prgMsg;
        _progress.sendMessage(msg);
    }

    private static int BUFFER_SIZE = 10;
    private static int SLEEP_TIME = 100;

    protected static String updateProgress(Reader reader, long totalLenght, double coef, int rTittle) throws IOException, InterruptedException {
        return updateProgress(reader,BUFFER_SIZE,totalLenght,coef,rTittle);
    }

    protected static String updateProgress(Reader reader,int bufferSize, long totalLenght, double coef, int rTittle) throws IOException, InterruptedException {
        StringBuilder sb = new StringBuilder();

        BufferedReader br = new BufferedReader(reader);
        int BUFFER_SIZE = bufferSize;

        CharBuffer buffer = CharBuffer.allocate(BUFFER_SIZE);
        double actualLenght = 0.0;

        while (br.read(buffer) >= 0) {
            //Do whatever you need with the bytes here
            sb.append(buffer.flip());

            actualLenght = actualLenght + (double) buffer.length();
            double nPrg = actualLenght /((double) totalLenght);
            nPrg = nPrg * 100.0 * Constants.COEF_DOWNLOAD;

            sendProgress((int) nPrg, coef, rTittle);

            buffer.clear();
            Thread.sleep(SLEEP_TIME);

        }

        br.close();
        return sb.toString();
    }

    public static ProgressDialog getDialog(Context context){
        final ProgressDialog progress = new ProgressDialog(context);
        progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progress.setIndeterminate(false);
        progress.setCancelable(false);
        progress.setTitle(R.string.waiting);
        progress.setMessage(context.getResources().getString(R.string.waiting));
        progress.setMax(100);
        progress.setProgress(0);
        return progress;
    }
}
