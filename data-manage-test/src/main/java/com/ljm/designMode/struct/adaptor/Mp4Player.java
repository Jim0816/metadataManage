package com.ljm.designMode.struct.adaptor;

/**
 * @ClassName Mp4Player
 * @Description
 * @Author Jim
 * @Date 2022/3/30 20:16
 **/
public class Mp4Player implements AdvancedPlayer{
    @Override
    public void playMp4() {
        System.out.println("播放MP4");
    }

    @Override
    public void playVlc() {
    }
}
