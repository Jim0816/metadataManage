package com.ljm.designMode.struct.adaptor;


/**
 * @ClassName Test
 * @Description
 * @Author Jim
 * @Date 2022/3/30 20:21
 **/
public class Test {
    public static void main(String[] args) {
        MediaPlayer mediaPlayer = new AudioPlayer();
        mediaPlayer.play("mp3");
        mediaPlayer.play("mp4");
        mediaPlayer.play("vlc");
    }
}
