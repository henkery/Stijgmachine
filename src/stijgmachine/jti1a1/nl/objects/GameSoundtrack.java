package stijgmachine.jti1a1.nl.objects;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class GameSoundtrack
{
	private static Clip soundtrack;
	private static Clip clip;
	
	public static void setPart(String s) throws LineUnavailableException, UnsupportedAudioFileException, IOException
	{
		if(s.equals("welcome")) setFile("welding.wav");
		else if(s.equals("power")) setFile("power.wav");
		else if(s.equals("transitionA")) setFile("transitionA.wav");	
		else if(s.equals("cooling")) setFile("cooling.wav");
		else if(s.equals("transitionB")) setFile("transitionB.wav");
		else if(s.equals("steam")) setFile("steam.wav");
		else if(s.equals("transitionC")) setFile("transitionC.wav");
		else if(s.equals("welding")) setFile("welding.wav");
		else if(s.equals("transitionD")) setFile("transitionD.wav");
		else if(s.equals("assembly")) setFile("assembly.wav");
		else if(s.equals("transitionE")) setFile("transitionE.wav");
		else if(s.equals("byebye")) setFile("byebye.wav");
	}
	
	public static void setFile(String s) throws LineUnavailableException, UnsupportedAudioFileException, IOException
	{
		clip = (Clip)AudioSystem.getLine(new DataLine.Info(Clip.class, AudioSystem.getAudioInputStream(new File(s)).getFormat()));
		clip.open(AudioSystem.getAudioInputStream(new File(s)));
	}
	
	public static void IntroTune() throws LineUnavailableException, UnsupportedAudioFileException, IOException
	{
		clip = (Clip)AudioSystem.getLine(new DataLine.Info(Clip.class, AudioSystem.getAudioInputStream(new File("src/res/intro_slatendo.wav")).getFormat()));
		clip.open(AudioSystem.getAudioInputStream(new File("src/res/intro_slatendo.wav")));
		clip.start();
	}
	
	public static void GameMusic() throws LineUnavailableException, UnsupportedAudioFileException, IOException
	{
		clip = (Clip)AudioSystem.getLine(new DataLine.Info(Clip.class, AudioSystem.getAudioInputStream(new File("src/res/soundTrack.wav")).getFormat()));
		clip.open(AudioSystem.getAudioInputStream(new File("src/res/soundTrack.wav")));
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		clip.start();
	}
	
	public static void VoiceActingWelcome() throws LineUnavailableException, UnsupportedAudioFileException, IOException
	{
		setPart("welcome");
		clip.start();
	}
	
	public static void VoiceActingPower() throws LineUnavailableException, UnsupportedAudioFileException, IOException
	{
		setPart("power");
		clip.start();
	}
	
	public static void VoiceActingTransitionA() throws LineUnavailableException, UnsupportedAudioFileException, IOException
	{
		setPart("transitionA");
		clip.start();
	}
	
	public static void VoiceActingCooling() throws LineUnavailableException, UnsupportedAudioFileException, IOException
	{
		setPart("cooling");
		clip.start();
	}
	
	public static void VoiceActingTransitionB() throws LineUnavailableException, UnsupportedAudioFileException, IOException
	{
		setPart("transitionB");
		clip.start();
	}
	
	public static void VoiceActingSteam() throws LineUnavailableException, UnsupportedAudioFileException, IOException
	{
		setPart("steam");
		clip.start();
	}
	
	public static void VoiceActingTransitionC() throws LineUnavailableException, UnsupportedAudioFileException, IOException
	{
		setPart("transitionC");
		clip.start();
	}
	
	public static void VoiceActingWelding() throws LineUnavailableException, UnsupportedAudioFileException, IOException
	{
		setPart("welding");
		clip.start();
	}
	
	public static void VoiceActingTransitionD() throws LineUnavailableException, UnsupportedAudioFileException, IOException
	{
		setPart("transitionD");
		clip.start();
	}
	
	public static void VoiceActingAssembly() throws LineUnavailableException, UnsupportedAudioFileException, IOException
	{
		setPart("assembly");
		clip.start();
	}
	
	public static void VoiceActingTransitionE() throws LineUnavailableException, UnsupportedAudioFileException, IOException
	{
		setPart("transitionE");
		clip.start();
	}
	
	public static void VoiceActingByeBye() throws LineUnavailableException, UnsupportedAudioFileException, IOException
	{
		setPart("byebye");
		clip.start();
	}
	
	
}
