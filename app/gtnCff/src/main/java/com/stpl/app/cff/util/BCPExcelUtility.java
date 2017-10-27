/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.util;

import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.ui.util.StandaloneParser;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import org.jboss.logging.Logger;

/**
 *
 * @author 
 */
public class BCPExcelUtility {
    
     private static final Logger LOGGER = Logger.getLogger(BCPExcelUtility.class);

    public static String excelExport_bcpUtility(String moduleName, String[] header, String query, String outputFilePath) {
        String os_name = System.getProperty("os.name");
        boolean isWindows = os_name.startsWith("Windows");
        System.out.println("os_name :" + os_name);
        Path dataPath = Paths.get("tableData.csv");
        File data = dataPath.toFile();
        if (!data.exists()) {
            try {
                data.createNewFile();
                data.setExecutable(true, false);
                data.setWritable(true, false);
                data.setReadable(true, false);
            } catch (IOException ex) {
                LOGGER.error(ex);
            }
        }
        if (outputFilePath.length() == 0) {
            outputFilePath = "default.csv";
        }
        StandaloneParser credentials = StandaloneParser.getInstance();
        System.out.println("outputFilePath---" + outputFilePath);
        Path path = Paths.get(outputFilePath);
        String timeStamp = new Timestamp(new Date().getTime()).toString().replace("-", "").replace(":", "").replace(" ", "").replace(".", "");
        moduleName = moduleName.replace(" ", "") + timeStamp;
        String folderName;
        if (isWindows) {
            folderName = System.getProperty("java.io.tmpdir");
        } else {
            folderName = System.getProperty("java.io.stpl.excel.dir");
        }

        if (!folderName.endsWith(File.separator)) {
            folderName += File.separator;
        }
        folderName += moduleName;
        File dir = new File(folderName);
        try {
            System.out.println("BCP working directory :" + dir);
            if (!dir.exists()) {
                dir.mkdirs();
                dir.setExecutable(true, false);
                dir.setWritable(true, false);
                dir.setReadable(true, false);
            }
            Path headerPath = Paths.get(folderName + File.separator + "header.csv");
            File headerFile = headerPath.toFile();

            try (FileOutputStream out = new FileOutputStream(headerFile)) {
                out.write((Arrays.toString(header).replace("[", "").replace("]", "") + "\n").getBytes());
            }
            headerFile.setExecutable(true, false);
            headerFile.setWritable(true, false);
            headerFile.setReadable(true, false);
            ProcessBuilder builder = null;
            Process exec;
            if (isWindows) {
                String command = "cmd.exe /c bcp \"[$QUERY]\" queryout [$OUT_PATH] -c -d [$DATABASE] -t , -S [$SERVER] -U [$USER] -P \"[$PASSWORD]\" > log.log";
                command = command.replace("[$QUERY]", query);
                command = command.replace("[$OUT_PATH]", dataPath.toString());
                command = command.replace("[$DATABASE]", credentials.getSchema());
                command = command.replace("[$SERVER]", credentials.getServer());
                command = command.replace("[$USER]", credentials.getUser());
                command = command.replace("[$PASSWORD]", credentials.isIsPasswordEncrypted() ? getDecryptedPassword(credentials.getPassword()) : credentials.getPassword());
                exec = Runtime.getRuntime().exec(command,null,dir);
                System.out.println("command = " + command);
                
            } else {
                StringBuilder strb = new StringBuilder();
                strb.append("bcp ");
                strb.append("\"").append(query).append("\"");
                strb.append(" queryout ");
                strb.append(dataPath.toString());
                strb.append(" -c ");
                strb.append("-d ");
                strb.append(credentials.getSchema());
                strb.append(" -t ");
                strb.append(", ");
                strb.append("-S ");
                strb.append(credentials.getServer());
                strb.append(" -U ");
                strb.append(credentials.getUser());
                strb.append(" -P ");
                strb.append("'").append(credentials.isIsPasswordEncrypted() ? getDecryptedPassword(credentials.getPassword()) : credentials.getPassword()).append("'");

                File shellFile = new File(folderName + File.separator + "exec.sh");

                try (FileOutputStream outShell = new FileOutputStream(shellFile)) {
                    outShell.write(strb.toString().getBytes());
                    outShell.flush();
                }
                shellFile.setExecutable(true, false);
                shellFile.setWritable(true, false);
                shellFile.setReadable(true, false);
                builder = new ProcessBuilder(shellFile.getAbsolutePath());
                builder.directory(dir);
                exec = builder.start();
                System.out.println("BCP command :" + builder.command());
            }
            int errors =exec.waitFor();
            System.out.println("BCP process completed : with errors :" + errors);
            Path headerFile2 = Paths.get("header.csv");
            List<String> val = new ArrayList();
            if (os_name.startsWith("Windows")) {
                val.add("cmd.exe");
                val.add("/c");
                val.add("copy");
                val.add("/b");
                val.add(headerPath.toString());
                val.add("+");
                val.add(dataPath.toString());
                val.add(path.toString());
                if (builder == null) {
                    builder = new ProcessBuilder(val);
                    builder.directory(dir);
                } else {
                    builder.command(val);
                }
            } else {
                StringBuilder strb = new StringBuilder();
                strb.append("cat ");
                strb.append(headerFile2.toString()).append(" ");
                strb.append(dataPath.toString());
                strb.append(" > ");
                strb.append(path.toString());

                File shellFile = new File(folderName + File.separator + "Concat_exec.sh");

                try (FileOutputStream outShell = new FileOutputStream(shellFile)) {
                    outShell.write(strb.toString().getBytes());
                    outShell.flush();
                }
                shellFile.setExecutable(true, false);
                shellFile.setWritable(true, false);
                shellFile.setReadable(true, false);
                builder.command(shellFile.getAbsolutePath());

            }
            System.out.println("Concat Command :" + builder.command());
             errors = builder.start().waitFor();
            System.out.println("Concat process completed : with errors :" + errors);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return dir + File.separator + outputFilePath;
    }
    
    /**
     * This method will return decrypted password
     * @param secret
     * @return decrypted value
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException 
     */
    private static String getDecryptedPassword(String secret) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        byte[] kbytes = "jaas is the way".getBytes();
        SecretKeySpec key = new SecretKeySpec(kbytes, "Blowfish");
        BigInteger n = new BigInteger(secret, NumericConstants.SIXTEEN);
        byte[] encoding = n.toByteArray();
        if (encoding.length % NumericConstants.EIGHT != 0) {
            int length = encoding.length;
            int newLength = ((length / NumericConstants.EIGHT) + 1) * NumericConstants.EIGHT;
            int pad = newLength - length; //number of leading zeros
            byte[] old = encoding;
            encoding = new byte[newLength];
            for (int i = old.length - 1; i >= 0; i--) {
                encoding[i + pad] = old[i];
            }
            if (n.signum() == -1) {
                for (int i = 0; i < newLength - length; i++) {
                    encoding[i] = (byte) -1;
                }
            }
        }
        Cipher cipher = Cipher.getInstance("Blowfish");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decode = cipher.doFinal(encoding);
        return new String(decode);
    }
}
