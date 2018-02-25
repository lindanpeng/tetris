package util;


import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JApplet;

public class Music extends JApplet{
	
	private AudioClip moveAudioClip;
	private AudioClip roundAudioClip;
	private AudioClip backgroundMusicAudioClip;
	private AudioClip overAudioClip;
	private AudioClip rmlineAudioClip;
	public Music() throws MalformedURLException{
		File backgroundMusicFile = new File("music/bgm.wav");
		URL backgroundMusicURL = backgroundMusicFile.toURL();
		File moveMusicFile = new File("music/move.wav");
		URL moveMusicURL = moveMusicFile.toURL();
		File roundMusicFile = new File("music/round.wav");
		URL roundMusicURL = roundMusicFile.toURL();
		File overMusicFile = new File("music/gameOver.wav");
		URL overMusicURL = overMusicFile.toURL();
		File rmlineMusicFile = new File("music/removeline.wav");
		URL rmlineMusicURL = rmlineMusicFile.toURL();
		moveAudioClip = Applet.newAudioClip(moveMusicURL);
		backgroundMusicAudioClip = Applet.newAudioClip(backgroundMusicURL);
		roundAudioClip = Applet.newAudioClip(roundMusicURL);
		overAudioClip = Applet.newAudioClip(overMusicURL);
		rmlineAudioClip = Applet.newAudioClip(rmlineMusicURL);
	}
	
	public void playBackgroundMusic(){
		this.backgroundMusicAudioClip.loop();
	}
	public void playRoundMusic(){
		this.roundAudioClip.play();
	}
	public void playMoveMusic(){
		this.moveAudioClip.play();
	}
    public void playOverMusic(){
    	this.overAudioClip.play();
    } 
    public void stopMusic(){
    	this.backgroundMusicAudioClip.stop();
    }
    public void playRmlineMusic(){
		this.rmlineAudioClip.play();
	}
   
}
