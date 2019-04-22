package ie.gmit.sw.ai;

public class PlayerSprite extends Sprite {

	private double playerHealth;

//	public PlayerSprite(double playerHealth) {
//		this.playerHealth = playerHealth;
//	}

	public PlayerSprite(int anger, String name, int frames, String[] files) throws Exception {
		super(anger, name, frames, files);
		// TODO Auto-generated constructor stub
	}

	public double getPlayerHealth() {
		return playerHealth;
	}

	public void setPlayerHealth(double playerHealth) {
		this.playerHealth = playerHealth;
	}

}
