package voice;

import org.junit.Test;

import javax.sound.sampled.*;
import java.io.*;

/**
 * @author zl
 * @Version 1.0
 * @Description TODO
 * @date 2019/01/29  15:45
 */
public class PcmPlayer {
    @Test
    public void testPlayPcm() {
        String filePath = "/Users/zl/Desktop/voice.pcm";
        playPcm(new File(filePath));
    }

    public static void playPcm(File file) {
        try {
            int offset = 0;
            int bufferSize = Integer.valueOf(String.valueOf(file.length()));
            byte[] audioData = new byte[bufferSize];
            InputStream in = new FileInputStream(file);
            in.read(audioData);

            float sampleRate = 16000;
            int sampleSizeInBits = 16;
            int channels = 1;
            boolean signed = true;
            boolean bigEndian = false;
            // sampleRate - 每秒的样本数
            // sampleSizeInBits - 每个样本中的位数
            // channels - 声道数（单声道 1 个，立体声 2 个）
            // signed - 指示数据是有符号的，还是无符号的
            // bigEndian - 指示是否以 big-endian 字节顺序存储单个样本中的数据（false 意味着
            // little-endian）。
            AudioFormat af = new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);

            SourceDataLine.Info info = new DataLine.Info(SourceDataLine.class, af, bufferSize);
            SourceDataLine sdl = (SourceDataLine)AudioSystem.getLine(info);
            sdl.open(af);
            sdl.start();
            while (offset < audioData.length) {
                offset += sdl.write(audioData, offset, bufferSize);
            }
        } catch (LineUnavailableException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
