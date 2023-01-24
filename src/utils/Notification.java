package utils;

public class Notification {
    public void showErrorNoti(String content){
        System.out.println(Color.RED_BACKGROUND_BRIGHT + Color.WHITE_BOLD_BRIGHT + content + Color.RESET);
    }

    public void showSuccessNoti(String content){
        System.out.println(Color.GREEN_BACKGROUND_BRIGHT + Color.WHITE_BOLD_BRIGHT + content + Color.RESET);
    }

}
