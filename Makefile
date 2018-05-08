Game.jar:
	javac -d out/production/JavaGameSandbox src/*.java
	cp src/*.png out/production/JavaGameSandbox
	cd out/production/JavaGameSandbox/; jar cvfe Game.jar Game *

