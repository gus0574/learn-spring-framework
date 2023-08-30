package com.in28minutes.learnspringframework;

import com.in28minutes.learnspringframework.game.GameRunner;
import com.in28minutes.learnspringframework.game.PacmanGame;

public class App01GamingBasicJava {

	public static void main(String[] args) {
		// Tightly or Loose Coupled
		
//		var game = new MarioGame();
//		var game = new SuperContraGame();
		var game = new PacmanGame();	//1. 객체 생성
		
		var gameRunner = new GameRunner(game);	//2. 객체생성 + 의존성 연결
		//게임 객체는 의존성이다. 게이밍콘솔 인터페이스는 게임러너 클래스의 의존성이다. 우리는 게임에 의존성을 주입하려는것이다.
		//현재는 우리가 JVM을 통해 수동으로 제어하고 있다. 하지만 이것을 스프링 프레임워크가 하도록 한다면 어떨까
		
		gameRunner.run();
	}

}
