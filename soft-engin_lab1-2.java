package LAB;

import com.sun.deploy.util.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LAB1 {
    public static void main(String[] args) {
        System.out.println("please input path");
        Scanner s1 = new Scanner(System.in);
        String path = s1.nextLine();

        Scanner s2 = new Scanner(System.in);
        System.out.println("please input level");
        int level = s2.nextInt();

        ReadTxt(path,level);
    }
    public static void ReadTxt (String path,int level) {
        StringBuilder sb1 = new StringBuilder("");

        try {

           // File file = new File("E:\\sf\\LAB1.cpp" + "");
            File file = new File(path + "");
            FileInputStream readIn = new FileInputStream(file);

            InputStreamReader read = new InputStreamReader(readIn, "utf-8");

            BufferedReader bufferedReader = new BufferedReader(read);

            String oneLine= null;

            while((oneLine= bufferedReader.readLine()) != null){
                sb1.append(oneLine);
             //   System.out.println(oneLine);
            }

            read.close();

        } catch (Exception e) {

            System.out.println("读取文件内容出错");

            e.printStackTrace();

        }

        String keyString = "abstract assert boolean break byte case catch "

                + "char class const continue default do double else enum"

                + " extends false final finally float for goto if implements "

                + "import instanceof int interface long native new null "

                + "package private protected public return short static "

                + "strictfp super switch synchronized this throw throws true "

                + "transient try void volatile while";
        //level1
         String[] keys = keyString.split(" ");
         String keyStr = StringUtils.join(Arrays.asList(keys), "|");

         //level2
         String sb=sb1.toString();
         String[] strs = sb.split("switch",3);//根据，切分字符串;切两份

    if(level==1){
        String regex = "("+keyStr+")";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(sb1);
        int count1=0;
        //all
        while(m.find()){
          //  System.out.println("|"+m.group()+"|");
            count1++;
        }
        System.out.println("total number:"+count1);
    }

        else if(level==2){
            String regex = "switch";
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(sb1);
            int count2=0;
            //switch
            while(m.find()){
                count2++;
            }
            System.out.println("switch number:"+count2);

            //case
            String regex2 = "case";
            Pattern p2 = Pattern.compile(regex2);
           for (int i=0;i<strs.length;i++){
               int count3=0;
               Matcher m2 = p2.matcher(strs[i]);
            //   System.out.println(strs[i]);
               while(m2.find()){
                   count3++;
               }
               if(count3!=0){
                   System.out.println("case number:"+count3);
               }
           }

        }



    }

}
