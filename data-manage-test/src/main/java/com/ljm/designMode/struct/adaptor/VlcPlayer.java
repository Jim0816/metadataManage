package com.ljm.designMode.struct.adaptor;

/**
 * @ClassName VlcPlayer
 * @Description
 * @Author Jim
 * @Date 2022/3/30 20:15
 **/
public class VlcPlayer implements AdvancedPlayer{
    @Override
    public void playMp4() {

    }

    @Override
    public void playVlc() {
        System.out.println("播放VLC");
    }
}
