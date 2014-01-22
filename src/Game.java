import processing.core.*;
public class Game extends PApplet {
	int counter = 0;
	int timesMousePressed = 0;
	float [] car = {250, 0};
	int cameraMen [][]= new int[3][2];
	int numClicks = 0;
	boolean allCameraMen = false;
	int []child = {230,300};
	final int childMoveSpeed = 2;
	final int carMoveSpeed = 1;
	boolean spacebarPressed = false;
	int rockThrowingStart = 0;
	boolean rocksStarted = false;
	boolean photos = false;
	int tempCheck; 
	final int dodgeSpeed = 2;
	final int crashRadius = 5;
	boolean crashed = false;
	
	public void setup() {
	    size(500,500);
	    background(255);
	}

	public void draw() {
		background(255);
	    //stroke(0);
	    //draw vertical line where car is
		counter++;
		fill(255);
	    rect(car[0], car[1],10,10);
	    if(counter%2==0){
	    	car[1]+= carMoveSpeed;
	    }
	    if (allCameraMen){
			fill(153);
	    	for(int i = 0;i<3;i++){
	    		rect(cameraMen[i][0],cameraMen[i][1],10,10);
	    	}
	    }
	    fill(0);
	    ellipse(child[0], child[1], 10, 10);
	    carDrive();
	    crashed();
	    if (crashed){
	    	System.out.println("Crashed!!!");
	    }
//	    if (mousePressed) {
//	      line(mouseX,mouseY,pmouseX,pmouseY);
//	      counter++;
//	    }
//	    
//	    if (counter == 500){
//	    	counter =0;
//	    	clear();
//	    }
	    //clear();
	}
//	public void clear(){
//		background(255);
//		rect(car[0], car[1],10,10);
//		if (allCameraMen){
//			fill(153);
//	    	for(int i = 0;i<3;i++){
//	    		rect(cameraMen[i][0],cameraMen[i][1],10,10);
//	    	}
//	    }
//		
//	}
	public void mouseClicked(){
		//if there are no camera men, draw them. if there are camera men switch the last one.
		if (numClicks%3 == 0){
			cameraMen[0][0]= mouseX;
			cameraMen[0][1]= mouseY;
		}
		if (numClicks%3 == 1){
			cameraMen[1][0]= mouseX;
			cameraMen[1][1]= mouseY;
		}
		if (numClicks%3 == 2){
			cameraMen[2][0]= mouseX;
			cameraMen[2][1]= mouseY;
			allCameraMen = true;
		}
		numClicks++;
	}
	public void keyPressed() {
		if (key == CODED) {
		    if (keyCode == RIGHT) {
		    	child[0]+=childMoveSpeed;	
	    		if(((car[0]+20)>child[0])&&((car[0]-20)<child[0])){
		    		
		    		if((car[0]>child[0])&&(car[0]<300))
		    			car[0]+=dodgeSpeed;
		    		else
		    			car[0]-=dodgeSpeed;
    			}
		    		
	    	}
		    	
		  	else if (keyCode == LEFT) {
		  		child[0]-=childMoveSpeed;
		  		if(((car[0]+20)>child[0])&&((car[0]-20)<child[0])){
		    		
		    		if((car[0]>child[0])&&(car[0]<300))
		    			car[0]+=dodgeSpeed;
		    		else
		    			car[0]-=dodgeSpeed;
		  		}
		    }
		}
		if (key == ' '){
			if (spacebarPressed == false){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					System.out.println("Oops! Something went wrong!");
				}
				spacebarPressed = true;
				rocksStarted = true;
			}
			else{
				photos = true;
			}
		}
		//check if spacebar pressed and set spacebarPressed to true
	}
	
	public void carDrive(){
		if (spacebarPressed&&rocksStarted){
			//first car stops... then speeds up around player
			car[1]+=.5;
		}
		if (photos){
			//check diagonals
			for (int i = 0; i < 3; i++){
				tempCheck = cameraMen[i][0];
				double slope = (car[1]-cameraMen[i][1])/(car[0]-cameraMen[i][0]);
				//System.out.println(slope);
				float y = car[1];
				for (float x = car[0]; x<500; x++){
					y+=slope;
				  if(
					  	((((x+1>cameraMen[0][0])&&(x-1<cameraMen[0][0]))&&(i!=0))||
						(((x+1>cameraMen[1][0])&&(x-1<cameraMen[1][0]))&&(i!=1))||
					    (((x+1>cameraMen[2][0])&&(x-1<cameraMen[2][0]))&&i!=2))
					    &&
					    (((y+1>cameraMen[0][1])&&(y-1<cameraMen[0][1]))||
						((y+1>cameraMen[1][1])&&(y-1<cameraMen[1][1]))||
						((y+1>cameraMen[2][1])&&(y-1<cameraMen[2][1])))
					    
				    )//if cameramen are in each others lines
				  {
//					  try {
//						//fill(0);
//						//rect(200,200,200,200);
//						//Thread.sleep(1000);
					//System.out.println("A cameraman got in the way!");
//						
//					  } catch (InterruptedException e) {
//						  // TODO Auto-generated catch block
//						  e.printStackTrace();
//					  }
				  }
				
				}
				y = car[1];
				for (float x = car[0]; x>0; x--){
					y-=slope;
				  if(
					  	((((x+1>cameraMen[0][0])&&(x-1<cameraMen[0][0]))&&(i!=0))||
						(((x+1>cameraMen[1][0])&&(x-1<cameraMen[1][0]))&&(i!=1))||
					    (((x+1>cameraMen[2][0])&&(x-1<cameraMen[2][0]))&&i!=2))
					    &&
					    (((y+1>cameraMen[0][1])&&(y-1<cameraMen[0][1]))||
						((y+1>cameraMen[1][1])&&(y-1<cameraMen[1][1]))||
						((y+1>cameraMen[2][1])&&(y-1<cameraMen[2][1])))
					    
				    )//if cameramen are in each others lines
				  {
					  System.out.println("A cameraman got in the way!");
				  }
				}
			}//end outer for loop 
		}//end of photos
		
		
		
	}
	public void crashed(){
		if((car[0]+crashRadius>child[0])&&(car[0]-crashRadius<child[0])&&(car[1]==child[1]))
			crashed=true;	
	}
		
}//END OF CLASS
