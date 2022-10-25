package LAB;

import com.sun.deploy.util.StringUtils;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;

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
        long startTime = System.currentTimeMillis();
        ReadTxt(path, level);
        long endTime = System.currentTimeMillis();
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");
    }

    public static void ReadTxt(String path, int level) {
        StringBuilder sb1 = new StringBuilder("");

        try {

           // File file = new File("E:\\sf\\lab1\\LAB1.cpp" + "");
             File file = new File(path + "");
            FileInputStream readIn = new FileInputStream(file);

            InputStreamReader read = new InputStreamReader(readIn, "utf-8");

            BufferedReader bufferedReader = new BufferedReader(read);

            String oneLine = null;

            while ((oneLine = bufferedReader.readLine()) != null) {
                sb1.append(oneLine);
                //  System.out.println(oneLine);
            }

            read.close();

        } catch (Exception e) {

            System.out.println("读取文件内容出错");

            e.printStackTrace();

        }

        String keyString = "abstract assert boolean break byte case catch "

                + "char class const continue default double else enum"

                + " extends false final finally float for goto if implements "

                + "import instanceof int interface long native new null "

                + "package private protected public return short static "

                + "strictfp super switch synchronized this throw throws true "

                + "transient try void volatile while";
        //level1
        String[] keys = keyString.split(" ");
        String keyStr = StringUtils.join(Arrays.asList(keys), "|");

        leveluse(keyStr,level,sb1);

    }

  public static void leveluse(String keyStr,int level,StringBuilder sb1){
        if(level==1){
            level1(keyStr,sb1);
        }
      if(level==2){
          level1(keyStr,sb1);
          level2( sb1);
      }
      if(level==3){
         level1(keyStr,sb1);
          level2(sb1);
          level3( sb1);
      }
  }

public static void level1(String keyStr,StringBuilder sb1){
          String regex = "(" + keyStr + ")";
          Pattern p = Pattern.compile(regex);
          Matcher m = p.matcher(sb1);
          int count1 = 0;
          while (m.find()) {
              count1++;
          }
          System.out.println("total number:" + count1);

  }
public static void level2(StringBuilder sb1){
        String regex = "switch";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(sb1);
        int count2 = 0;
        //switch
        while (m.find()) {
            count2++;
        }
        System.out.println("switch number:" + count2);
        String sb = sb1.toString();
        String[] strs = sb.split("switch", count2 + 1);//根据，切分字符串;切两份
        //case
        String regex2 = "case";
        Pattern p2 = Pattern.compile(regex2);
        System.out.print("case number:");
        for (int i = 0; i < strs.length; i++) {
            int count3 = 0;
            Matcher m2 = p2.matcher(strs[i]);
            while (m2.find()) {
                count3++;
            }
            if (count3 != 0) {
                System.out.print( count3+" ");
            }
        }
    System.out.println(" ");

}
public static void level3(StringBuilder sb1){

    String s=sb1.toString();
    String test = s.replaceAll( "if", "A");
   String test2 = test.replaceAll( "else", "C");

    int if_num=0;
    int else_num=0;
    char[] chartest = test2 .toCharArray();
    int [] data =new int[100];
    int j=0;
    for(int i=0;i < test2.length();i++) {
      if(chartest[i]=='A'){
          data[j]=1;
          if_num++;
          j++;
      }
        if(chartest[i]=='C'){
            data[j]=2;
            else_num++;
            j++;
        }
    }

    int else_if_num=0;

    int if_elseif_num=0;
    int if_elseif_else_num=0;
    int elseif_num=0;
    int ifelse_num=0;
    int repeat_elseif_num=0;
    for(int i=0;i<j-1;i++){
      //  System.out.print(data[i]);
        if(data[i]==1&&data[i+1]==2&&data[i+2]==1){
            if_elseif_num++;
        }
        if(data[i]==2&&data[i+1]==1){
            elseif_num++;
        }
        if(data[i]==1&&data[i+1]==2){
            ifelse_num++;
        }
        if(data[i]==2&&data[i+1]==1&&data[i+2]==2&&data[i+3]==1&&data[i+4]==2){
             repeat_elseif_num=1;
        }
    }
    //delete repeat
    if_elseif_else_num=elseif_num-if_elseif_num;
    //get if_else
    ifelse_num=ifelse_num-if_elseif_else_num-repeat_elseif_num;

    System.out.println("if-else num:"+ ifelse_num);
    System.out.println("if-elseif-else num:"+ if_elseif_else_num);

}


}
