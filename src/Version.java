public class Version {
    public static void main(String[] args) {
        String version1 = "5.1.7";
        String version2 = "5.1.7";

        System.out.println(versionCompare(version1, version2));
    }

    enum Result{
        GREATER, EQUAL, LESS;
    }

    public static Result versionCompare(String version1, String version2){
        String[] str1 = version1.split("\\.");
        String[] str2 = version2.split("\\.");


        int i;
        for (i = 0; i < str1.length && i < str2.length; i++){
            int v1 = Integer.parseInt(str1[i]);
            int v2 = Integer.parseInt(str2[i]);

            if(v1 > v2){
                return Result.GREATER;
            }else if(v1 < v2){
                return Result.LESS;
            }
        }


        if(i == str1.length && i < str2.length) return Result.LESS;
        else if(i == str2.length && i < str1.length) return Result.GREATER;

        return Result.EQUAL;

    }

}
