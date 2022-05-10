package com.ljm.designMode.struct.adaptor;

/**
 * @ClassName MediaAdapter
 * @Description
 * @Author Jim
 * @Date 2022/3/30 20:18
 **/
public class MediaAdapter implements MediaPlayer{
    private AdvancedPlayer advancedPlayer = null;
    @Override
    public void play(String audioType) {
        if ("mp4".equals(audioType)){
            advancedPlayer = new Mp4Player();
            advancedPlayer.playMp4();
        }else if ("vlc".equals(audioType)){
            advancedPlayer = new VlcPlayer();
            advancedPlayer.playVlc();
        }
    }
}
