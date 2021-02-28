package utils;

import javax.swing.text.MaskFormatter;

public class CpfFormat {
	public static String formatString(String mask, String value) {  
        try {  
            MaskFormatter formatter = new MaskFormatter(mask);  
            formatter.setValueContainsLiteralCharacters(false);  
            return formatter.valueToString(value);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return value;  
    }  
      
    private String desformatString(String mask, String value) {  
        try {  
            MaskFormatter desformatter = new MaskFormatter(mask);  
            desformatter.setValueContainsLiteralCharacters(false);  
            return desformatter.stringToValue(value).toString();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return "";  
    }  
}
