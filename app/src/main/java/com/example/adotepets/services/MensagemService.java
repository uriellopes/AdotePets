package com.example.adotepets.services;

import android.app.IntentService;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.adotepets.MainActivity;

public class MensagemService extends IntentService {

    public static final String MSG = "mensagem";
    //public String msg;

    public static final String CHANNEL_ID = "5463";
    public static final int NOTIFICATION_ID = 5463;


    public MensagemService(){
        super("MensagemAtrasadaService");
    }

    //execução do service
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        synchronized (this){
            try{
                wait(10000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
        String mensagem = intent.getExtras().getString(MSG);
        showMsg(mensagem);

    }

    private void criarCanalDeNotificacao(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            CharSequence name = "meu_canal";
            String description = "canalparaNotificacoes";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel = new NotificationChannel(this.CHANNEL_ID,name,importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

        }
    }

    public void showMsg(String mensagem){

        this.criarCanalDeNotificacao();

        NotificationCompat.Builder noteBuilder = new NotificationCompat.Builder(this,CHANNEL_ID)
                .setContentTitle("Amamos nosso pet!")
                .setContentText(mensagem)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setVibrate(new long[] {0, 300})
                .setAutoCancel(true)
                .setSmallIcon(android.R.drawable.sym_def_app_icon);

        Intent acaoAoClicarNaNotificacao = new Intent(this, MainActivity.class);

        PendingIntent acaoPendente = PendingIntent.getActivity(
                this,
                0,
                acaoAoClicarNaNotificacao,
                PendingIntent.FLAG_UPDATE_CURRENT
        );

        noteBuilder.setContentIntent(acaoPendente);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID,noteBuilder.build());
    }
}
