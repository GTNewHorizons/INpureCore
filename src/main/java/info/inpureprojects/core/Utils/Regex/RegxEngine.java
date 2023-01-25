package info.inpureprojects.core.Utils.Regex;

public class RegxEngine {
    public static boolean match(String regx, String candidate) {
        if (regx.isEmpty()) {
            return false;
        }
        if (regx.charAt(0) == '*') {

            if (regx.length() == 1) {
                return true;
            }
            return matchStar(regx.substring(1), candidate);
        }

        if (candidate.isEmpty()) return false;
        if (regx.charAt(0) == '.' || regx.charAt(0) == candidate.charAt(0)) {

            if (regx.length() == 1 && candidate.length() == 1) {
                return true;
            }

            return match(regx.substring(1), candidate.substring(1));
        }

        return false;
    }

    private static boolean matchStar(String regx, String candidate) {
        for (int i = 0; i < candidate.length(); i++) {
            if (match(regx, candidate.substring(i))) {
                return true;
            }
        }

        return false;
    }
}
