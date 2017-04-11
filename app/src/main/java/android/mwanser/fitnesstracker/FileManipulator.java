package android.mwanser.fitnesstracker;

import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 * Created by Wanser on 4/10/17.
 */

public class FileManipulator {
    private boolean failedWrite=false;
    private boolean failedRead=false;
    private boolean failedOpen=false;
    private boolean fileNew=false;

    private File myFile=null;
    private FileOutputStream fos=null;
    private OutputStreamWriter outWriter=null;
    private FileInputStream fis=null;
    private BufferedReader reader=null;
    private ArrayList<String[]> theUsers= null;


    public FileManipulator(){

    }

    /**
     * constructor
     * @param fileName - file to open for manipulating
     */
    public FileManipulator(String fileName){
        boolean success=true;

        try {
            myFile = new File(Environment.getExternalStorageDirectory(), fileName);
        }catch(Exception e){
            Log.e("FileManipulator","Error opening file");
            e.printStackTrace();
            failedOpen=true;
        }
        if(!myFile.exists())
            try{ myFile.createNewFile(); fileNew=true; }
            catch (IOException e){
                e.printStackTrace();
                failedOpen=true;
            }
        try{ //try reading to the file
            theUsers=new ArrayList<>();
            String temp="";
            fis=new FileInputStream(myFile);
            reader = new BufferedReader(new InputStreamReader(fis));
            while((temp=reader.readLine())!=null) {
                theUsers.add(temp.split(","));

            }

        } //errors incurred while writing to file
        catch (FileNotFoundException e){
            e.printStackTrace();
            success=false;
        }
        catch (IOException e){
            e.printStackTrace();
            success=false;
        }
        finally { //close all the files and streams
            closeStreams();
        }
    }

    /**
     * function to close streams associated with object
     */
    public void closeStreams(){
        try {
            if (outWriter != null) outWriter.close();
            if (fos != null) fos.close();
            if (reader != null) reader.close();
            if (fis != null) fis.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * function to write user to file
     * @param email
     * @param password
     * @param weight
     * @param height
     * @param age
     * @param gender
     * @param unit
     * @return
     */
    public boolean writeToFile(String email, String password, Integer weight, String height,
                       Integer age, int gender, int unit ){
        boolean success=true;
        try{ //try writing to the file
            fos=new FileOutputStream(myFile,true);
            outWriter = new OutputStreamWriter(fos);

            outWriter.append(email+","+password+","+weight+","+height+","
                    +age+","+String.valueOf(gender)+","+unit+"\n");
            outWriter.flush();
            outWriter.close();
            fos.close();

        } //errors incurred while writing to file
        catch (FileNotFoundException e){
            e.printStackTrace();
            success=false;
        }
        catch (IOException e){
            e.printStackTrace();
            success=false;
        }
        return true;
    }

    /**
     *  checks if the user is correct
     * @param email
     * @param pw
     * @return
     */
    public int checkCredentials(String email, String pw){
        int line =0;
        for (String[] s : theUsers){
            if(email.equals(s[0].trim()))
                if(pw.equals(s[1].trim()))
                    return line;
                else
                    return -1;
            line++;
        }
        return -1;

    }
    public void printArray(){
        String temp="";
        Log.d("----users","there are:"+String.valueOf(theUsers.size()));
        for (String[] s: theUsers) {
            for (String item: s)
                temp= temp+item+" : ";
            Log.d("----",temp.toString());
            temp="";

        }
    }



}
