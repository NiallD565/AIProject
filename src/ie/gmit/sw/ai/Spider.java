package ie.gmit.sw.ai;

import ie.gmit.sw.ai.nn.NnFight;

public class Spider{

	private double health;
	private double anger;
	private NnFight nnfight;
	private ControlledSprite player;
	private double [] outcome;
	private Maze maze;

	public Spider(double health, double anger, ControlledSprite player, Maze maze) {
		this.health = health;
		this.anger = anger;
		this.player = player;
		this.maze = maze;
	}

	public void fight(double angerLevel, double weapon) {
		nnfight = new NnFight();
		try {
			outcome = nnfight.action(0, 0, 0);
		} catch (Exception e) {
		}
	}

	public double getHealth() {
		return health;
	}

	public void setHealth(double health) {
		this.health = health;
	}

	public double getAnger() {
		return anger;
	}

	public void setAnger(double anger) {
		this.anger = anger;
	}

//	public void run() {
//
//		while (this.isAlive()) {
//			try { // Slow down the thread to give the spider time to move/think
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			
//		}
//	}
	public boolean isAlive()
	{
		if(health > 0)
			return true;
		else 
			return false;
	}

}
