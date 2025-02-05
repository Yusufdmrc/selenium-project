    package util;

    public class LoginHelper {
        public static String getUserName(String username) {
            switch (username) {
                case "correctTcID":
                    return Constants.CORRECT_TC_ID;
                case "correctAccountNo":
                    return Constants.CORRECT_ACCOUNT_NO;
                case "correctEmail":
                    return Constants.CORRECT_EMAIL;
                case "empty":
                    return " ";
                default:
                    return username;
            }
        }

        public static String getPassword(String password) {
            switch (password) {
                case "correctPassword":
                    return Constants.CORRECT_PASSWORD;
                case "empty":
                    return "";
                default:
                    return password;
            }
        }
    }
