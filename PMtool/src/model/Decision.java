package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Decision {

	public boolean isInt(String str){

    //判定するパターンを生成
    Pattern p = Pattern.compile("^[0-9]*$");
    Matcher m = p.matcher(str);

   return m.find();
	}

}
