import java.io.*;
import javax.sound.sampled.*;

public class Sound {
	Clip clip;
	boolean isStart = false;

	public Sound(String filePath) {

		try {
			AudioInputStream audio = 
				AudioSystem.getAudioInputStream(new File(filePath));

			AudioFormat format = audio.getFormat();

			DataLine.Info info = new DataLine.Info(Clip.class, format);

			clip = (Clip)AudioSystem.getLine(info);
			clip.open(audio);
		} catch(Exception e) { }
	}

	public void start() {
		this.isStart = true;
		clip.loop(0);
	}
}