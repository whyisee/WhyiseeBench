package com.base.sstring;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2019/7/9 14:37
 * @version 1.0
 * @Modified By:
 * @used in: WorkTest
 */
public class StringUtils {
    public int compareStringBySeparate(String str1,String str2 ,String separete){
        if(null==str1||str1.length()==0||null==str2||str2.length()==0){
            return 0;
        }
        String[] arr1=str1.split(separete);
        String[] arr2=str2.split(separete);

        for (int i=0;i<arr1.length;i++){
            for (int j=0;j<arr2.length;j++){
                if(arr1[i].equals(arr2[j])){
                    return 1;
                }
            }
        }
        return 0;
    }
    public int compareStringBySeparate(String str1,String str2 ) {
        return compareStringBySeparate(str1, str2, ",");
    }

}
