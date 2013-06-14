package stijgmachine.jti1a1.nl.objects;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class MusicPlayer {

	private Clip clip;

	public MusicPlayer() {

	}

	public void playSound(String url, int loopTimes) {
		try {
			stopPlay();
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(url));
			clip = AudioSystem.getClip();
			clip.open(inputStream);
			clip.start();
			clip.loop(loopTimes);
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	public void stopPlay() {
		if (clip != null) {
			clip.stop();
			clip.close();
			clip = null;
		}
	}
}
