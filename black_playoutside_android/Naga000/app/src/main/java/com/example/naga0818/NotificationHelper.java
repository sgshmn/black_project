package com.example.naga0818;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

// 알림 기능
public class NotificationHelper extends ContextWrapper {

    // 오레오 이상은 반드시 채널을 설정해줘야 Notification 작동함 (채널 생성)
    public static final String channe1ID = "channel1ID";
    public static final String channe1Nm = "channel1Nm";
    public static final String channe2ID = "channel2ID";
    public static final String channe2Nm = "channel2Nm";

    private NotificationManager notificationManager;
    private NotificationCompat.Builder builder;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public NotificationHelper(Context base) {
        super(base);
        // 안드로이드 버전이 오레오거나 이상이면 채널 생성
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannels();
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void createChannels() {
        NotificationChannel channel1 = new NotificationChannel(channe1ID, channe1Nm, NotificationManager.IMPORTANCE_DEFAULT);
        channel1.enableLights(true);
        channel1.enableVibration(true);
        channel1.setLightColor(R.color.purple_200);
        channel1.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

        getManager().createNotificationChannel(channel1);

        NotificationChannel channel2 = new NotificationChannel(channe2ID, channe2Nm, NotificationManager.IMPORTANCE_DEFAULT);
        channel2.enableLights(true);
        channel2.enableVibration(true);
        channel2.setLightColor(com.google.android.material.R.color.design_default_color_on_primary);
        channel2.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

        getManager().createNotificationChannel(channel2);
    }
    public NotificationManager getManager() {
        if (notificationManager == null) {
            notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return notificationManager;
    }
    // NotificationCompat.Builder를 이용해서 알림 제목/내용/아이콘 입력하는 메소드 생성
    public NotificationCompat.Builder getChannelNotification(String title, String message) {
        return new NotificationCompat.Builder(getApplicationContext(), channe1ID)
                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(R.drawable.cute);
    }
    public NotificationCompat.Builder getChannel2Notification(String title, String message){
        return new NotificationCompat.Builder(getApplicationContext(), channe2ID)
                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(R.drawable.arrow);
    }
    public NotificationCompat.Builder getChannelNotification() {
        return new NotificationCompat.Builder(getApplicationContext(), channe1ID)
                .setContentTitle("담양 에코센터")
                .setContentText("체험활동 예약 시간이예요.")
                .setSmallIcon(R.drawable.cute);
    }
}
