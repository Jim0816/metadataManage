package com.ljm.designMode.struct.adaptor;

/**
 * @ClassName AudioPlayer
 * @Description
 * @Author Jim
 * @Date 2022/3/30 20:16
 **/
public class AudioPlayer implements MediaPlayer{
    private MediaAdapter mediaAdapter = null;

    public AudioPlayer(){
        this.mediaAdapter = new MediaAdapter();
    }
    @Override
    public void play(String audioType) {
        if ("mp3".equals(audioType)){
            System.out.println("播放mp3");
        }

        // 加上了适配器之后  增加了适配功能
        else if("mp4".equals(audioType) || "vlc".equals(audioType)){
            mediaAdapter.play(audioType);
        }
    }
}
