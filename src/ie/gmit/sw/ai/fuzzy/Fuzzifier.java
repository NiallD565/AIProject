package ie.gmit.sw.ai.fuzzy;

import java.util.ArrayList;
import java.util.Random;

import ie.gmit.sw.ai.nn.NnFight;
import ie.gmit.sw.ai.Sprite;
import ie.gmit.sw.ai.player;
import ie.gmit.sw.ai.traversers.AStarTraversator;
import ie.gmit.sw.ai.traversers.Node;
import ie.gmit.sw.ai.traversers.Traversator;

public class Fuzzifier extends Sprite implements Runnable {

	// variables 
	private int row;
	private int col;
	private int feature;

	private Node node = new Node(row, col, feature);
	private Object exectr;
	private Node[][] maze;

	private Random random = new Random();
	private Node lastpos;
	private Node lastNode;
	private player player;
	private Traversator traverse;
	private Node nextPosition;
	private boolean moveable;
	private NnFight nnfight;
	private int outcome;


	// search stuff
	public Fuzzifier(int row, int col, int feature, Object lock, Node[][] maze, player p, NnFight f) {
		// TODO Auto-generated constructor stub
		this.row = row;
		this.col = col;
		this.feature = feature;
		// player
		this.player = p;

		node.setCol(col);
		node.setRow(row);
		node.setType(feature);

		this.exectr = lock;
		this.maze = maze;
		
		p.setPlayerHealth(100);

		this.nnfight = f;

		if(feature == 6) {
			// assign a search 
			traverse = new AStarTraversator(p);
		}

	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			try {
				Thread.sleep(1000);
				
				if(feature == 6){
					traverse(node.getRow(), node.getCol(), traverse);
				}
				
				// fuzzy stuff
				if(node.getHeuristic(player) <= 1) {
					System.out.println("Touching spiders");
					System.out.println(super.getAnger());
					fight(super.getAnger());
				}else if(node.getHeuristic(player) < 10){
					//System.out.println("Player close");
					followPlayer();     
				}else {
					move();
				}

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/// move around

	private void followPlayer() {
		// TODO Auto-generated method stub
		if(nextPosition != null){
			synchronized(exectr){
				// Find all the surrounding nodes
				Node[] surroundingNodes = node.adjacentNodes(maze);
				// List of empty surrounding nodes
				ArrayList<Node> emptySurroundingNodes = new ArrayList<>();
				// Check if they are empty
				for(Node n : surroundingNodes){
					if(n.getType() == -1)
					{
						emptySurroundingNodes.add(n);
					}
				}

				// Check if they are empty
				for(Node n : emptySurroundingNodes){
					if(nextPosition.equals(n) )
					{		
						//New position of the object
						int newPositionX, newPositionY;
						//Previous position of the object
						int previousPositonX = node.getRow(), previousPositionY = node.getCol();

						System.out.println();
						newPositionX = nextPosition.getRow();
						newPositionY = nextPosition.getCol();

						node.setRow(newPositionX);
						node.setCol(newPositionY);

						maze[newPositionX][newPositionY] = node;
						maze[previousPositonX][previousPositionY] = new Node(previousPositonX, previousPositionY, -1);

						nextPosition = null;
						moveable = false;
						return;
					}	
				}
				// Move to random in empty
				move();

				nextPosition = null;
				moveable = false;
				return;
			}
		}
		else{
			move();

			moveable = false;
		}
	}


	public void move() {

		synchronized(exectr){
			// Figure out all the nodes around
			Node[] surroundingNodes = node.adjacentNodes(maze);
			//List of empty surrounding nodes
			ArrayList<Node> emptySurroundingNodes = new ArrayList<>();


			// Check if they are empty
			for(Node n : surroundingNodes){
				if(n.getType() == -1 && n != lastNode)
				{
					emptySurroundingNodes.add(n);
				}
			}

			if(emptySurroundingNodes.size() > 0){


				int position = random.nextInt(emptySurroundingNodes.size());

				//New position of the object
				int newPositionX, newPositionY;
				//Previous position of the object
				int previousPositonX = node.getRow(), previousPositionY = node.getCol();
				newPositionX = emptySurroundingNodes.get(position).getRow();//nextPosition.getRow();
				newPositionY = emptySurroundingNodes.get(position).getCol();//nextPosition.getCol();
				node.setRow(newPositionX);
				node.setCol(newPositionY);

				lastNode = new Node(previousPositonX, previousPositionY, -1);
				maze[newPositionX][newPositionY] = node;
				maze[previousPositonX][previousPositionY] = lastNode;
			}
		}

	}


	public void fight(int attack) {

		Fight f = new Fight();
		
		double health = player.getPlayerHealth();
		double newHealth = f.PlayerHealth(50, attack);
		double hello = health - newHealth;
		System.out.println(hello);
		player.setPlayerHealth(hello);
		System.out.println(player.getPlayerHealth());
	
	}
	
	public void traverse(int row, int col, Traversator t){
		t.traverse(maze, maze[row][col]);
		nextPosition = t.getNextNode();
		if(nextPosition != null){
			moveable = true;
		} else {
			moveable = false;
		}
	}
public void fightNn(double health, double angerLevel, double weapon) {
		
		if(health < 30) {
			health = 0;
		}else if(health > 30 && health < 60) {
			health = 1;
		}else if(health > 60) {
			health = 2;
		}
		
		if(angerLevel < 30) {
			angerLevel = 0;
		}else if(angerLevel > 30 && angerLevel < 60) {
			angerLevel = 1;
		}else if(angerLevel > 60) {
			angerLevel = 2;
		}
		
		try {
//			while(checkToRetrain) {
				//outcome = nnfight.action(health, weapon, angerLevel);
//				checkToRetrain =false;
				if(outcome == 1) {
					System.out.println("panic");
					move();
				}
				else if(outcome == 2) {
					System.out.println("attack");
					followPlayer();  
				}
				else if (outcome == 3)
					System.out.println("hide");
				
//			}
			
		} catch (Exception e) {
		}
	}

}



