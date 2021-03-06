package android.mwanser.fitnesstracker;

import android.mwanser.PreferenceUtils;
import android.mwanser.fitnessmodel.Person;
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

public class LoginFileManipulator {
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
    private Person current=null;


    public LoginFileManipulator(){

    }

    /**
     * constructor
     * @param fileName - file to open for manipulating
     */
    //TODO should be internal storage
    public LoginFileManipulator(String fileName){
        boolean success=true;

        try {
            myFile = new File(Environment.getExternalStorageDirectory(), fileName);
        }catch(Exception e){
            Log.e("LoginFileManipulator","Error opening file");
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
            //users = new ArrayList<>();
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
    public boolean appendPerson(Person p){
        boolean success= true;
        try{
            fos=new FileOutputStream(myFile,true);
            outWriter = new OutputStreamWriter(fos);
            outWriter.append(p.write());
            outWriter.flush();
            outWriter.close();
            fos.close();
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
            success=false;
        }
        catch (IOException e){
            e.printStackTrace();
            success=false;
        }
        return success;

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
        return success;
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
                if(pw.equals(s[1].trim())){
                    current= new Person(
                            Integer.parseInt(s[4]),
                            Integer.parseInt(s[8]),
                            Integer.parseInt(s[2]),
                            s[3],
                            Integer.parseInt(s[7]),
                            Integer.parseInt(s[6]),
                            s[0],
                            s[1],
                            Integer.parseInt(s[5]));
                    return line;
            }
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
    public void rewriteFile(Person u, int i){
        overwrite(u,i);

    }
    private void overwrite(Person u, int i){
        int line=0;
        try{
            fos=new FileOutputStream(myFile,false);
            outWriter = new OutputStreamWriter(fos);
            for(String[] s : theUsers){
                if(line==i){
                    outWriter.write(u.write());
                }
                else{
                    outWriter.write(s[0]);
                    outWriter.write(",");
                    outWriter.write(s[1]);
                    outWriter.write(",");
                    outWriter.write(s[2]);
                    outWriter.write(",");
                    outWriter.write(s[3]);
                    outWriter.write(",");
                    outWriter.write(s[4]);
                    outWriter.write(",");
                    outWriter.write(s[5]);
                    outWriter.write(",");
                    outWriter.write(s[6]);
                    outWriter.write(",");
                    outWriter.write(s[7]);
                    outWriter.write(",");
                    outWriter.write(s[8]);
                    outWriter.write(",");
                    outWriter.write(s[9]);
                    outWriter.write("\n");
                }
                line++;

            }

            outWriter.flush();
            outWriter.close();
            fos.close();

        } catch (FileNotFoundException e){
            e.printStackTrace();

        }
        catch (IOException e){
            e.printStackTrace();
        }
        

    }
    public boolean getCurrentPassword(String p){
        if(current!=null) if (p == current.getPassword()) return true;
        else {
            return false;
        }
        else return  false;
    }
    public Person getPerson(){

        return current;

    }



}
