import java.util.*;
import java.io.*;
import java.lang.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.lang.NumberFormatException;
import java.util.ArrayList;

public class PasswdValidator{
 
  private static String error = "";
  private static String warning = "";
  private static ArrayList<Integer> uids = new ArrayList<Integer>();
 
  public static void main(String[] args) throws Exception{
    ArrayList<String> lines = new ArrayList<String>();
    Scanner sc = new Scanner(new File("/etc/passwd"));
    while(sc.hasNextLine()){
      String line = sc.nextLine();
      lines.add(line);
    }
    for(int i = 0;i < lines.size();i++){
      String line = lines.get(i);
      if(line.charAt(0) != '#'){
      String[] info = line.split(":");
      eval_username(info[0], i);
      if (eval_password(info[1], i) ==1) break;
      if (eval_uid(info[2], i) == 1) break;
      if (eval_gid(info[3], i) == 1) break;
      eval_gecos(info[4], i);
      eval_directory(info[5], i);
      eval_shell(info[6], i);
      }
    }
  }
 
  public static void eval_username(String str, int i){
    String thePattern = "Blahblah";
  }
 
  public static int eval_password(String str, int i){
    if (str.length() != 1 || str.charAt(0) != 'x' || str.charAt(0) != '*'){
      set_error("- password must be x or *", i);
      return 1;
    }
    return 0;
  }
 
  public static int eval_uid(String str, int i){
    if (str.length() == 0) {
      set_error("- uid shouldn't be empty", i);
      return 1;
    }
    int uid = -1;
    try {
      uid = Integer.parseInt(str);
    } catch (NumberFormatException e){
      set_error("- uid must be a non-negative integer", i);
    }
    if (uid < 0) {
      set_error("- uid must be a non-negative integer", i);
      return 1;
    }
    if (str.charAt(0) == '0' && str.length() > 1) {
      set_error("- uid must have no leading 0s", i);
      return 1;
    }
    for (int a = 0; a < uids.size(); a++){
      if (uids.get(a) == uid){
        set_warning("- uid is the same as a previous entry on line" + a, i);
      }
    }
    uids.add(uid);
    return 0;
  }
 
  public static int eval_gid(String str, int i){
    if (str.length() == 0) {
      set_error("- gid shouldn't be empty", i);
      return 1;
    }
    int gid = -1;
    try {
      gid = Integer.parseInt(str);
    } catch (NumberFormatException e){
      set_error("- gid must be a non-negative integer", i);
    }
    if (gid < 0) {
      set_error("- gid must be a non-negative integer", i);
      return 1;
    }
    if (str.charAt(0) == '0' && str.length() > 1) {
      set_error("- gid must have no leading 0s", i);
      return 1;
    }
    return 0;
  }
 
  public static int eval_gecos(String str, int i){
    if (str.length() == 0) {
      set_error("- gecos must have at least a name", i);
      return 1;
    }
    String [] info = str.split(",");
    if (info[0].length() == 0) {
      set_error("- gecos must have at least a name", i);
      return 1;
    }
    
    
    if (info.length > 1){
      if (info[1].length() == 0){
        set_error("- gecos must non empty name for office", i);
        return 1;
      }else {
        
      }
    }
    if (info.length > 2){
      if (info[2].length() == 0){
        set_error("- gecos must non empty phone number", i);
        return 1;
      }else {
        
      }
    }
    
    
    return 0;
  }
 
  public static void eval_directory(String str, int i){
    
  }
 
  public static void eval_shell(String str, int i){
    
  }
 
  public static void set_error(String reason, int i){
    error = i + ": ERROR "+ reason;
  }
 
  public static void set_warning(String reason, int i){
    warning = i+": WARNING "+reason;
  }
 
//  public static int strResult(String in){
//    in = in.trim();
//    String dnaPattern = ". [N|E|S|W|n|e|s|w]{4} .{4}";
//    String stepPattern = "\\d+";
//    String commentPattern = "[#]+.*";
//    String blankPattern = "[ \t\n]+";
//    Pattern blank = Pattern.compile(blankPattern);
//    Pattern comment = Pattern.compile(commentPattern);
//    Pattern step = Pattern.compile(stepPattern);
//    Pattern dna = Pattern.compile(dnaPattern);
//    Matcher a = blank.matcher(in);
//    Matcher b = comment.matcher(in);
//    Matcher c = step.matcher(in);
//    Matcher d = dna.matcher(in);
//    if(in.length() == 0){
//      return 4;
//    }else if (a.matches()){
//      return 4;
//    }else if (b.matches()){
//      return 3;
//    }else if (c.matches()){
//      return 2;
//    }else if (d.matches()){
//      return 1;
//    }else{
//      return 0;
//    }
//  }
}