package com.oa.core.utils.win;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 调用windows命令
 * <p/>
 * Created by [张渊]
 * 2017/8/10 11:31
 */
public class CmdUtil {

    private CmdUtil() {
    }

    public static boolean execute(String batName) {

        Process process = null;
        BufferedReader br = null;
        try {
            process = Runtime.getRuntime().exec(batName);
            br = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

            if (process.waitFor() != 0) {
                System.out.println("fail");
                return false;
            }

            System.out.println(batName + " run successful");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (process != null) {
                process.destroy();
            }
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return true;
    }
}
