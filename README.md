# Football Fixtures (EPL Fixtures) Using Genetic Algorithms - Team 105 <br/>
 ## Final Project - Program Structures and Algorithms ( INFO 6205 ) <br/>
 Team Members : Aditi Jalkote, Mitali Salvi, Shubham Sharma <br/>

### Problem Description
Here we are drawing Football fixture (EPL Fixtures) using Genetic Algorithm. Genetic Algorithm is a method of solving optimisation problem inspired by natural selection where fittest  individual are selected for mating in order to produce healthy new generation. <br/>
Operators in Genetic Algorithm <br/>
* Initial Population <br/>
* Fitness Function <br/>
* Selection <br/>
* Crossover <br/>
* Mutation <br/> <br/>
While scheduling fixtures Following criteria are being satisfied: <br/>
* A team cannot play with itself <br/>
* Each team plays exactly 2(I.e. number of rounds) matches against each team in the league <br/>
* Each team plays [total number of matches – number of rounds] matches in the league <br/>
* Each team plays exactly one match at its own home ground and one match at opponent’s home ground. (One – home, One - away) <br/>
* Two matches cannot take place on the same day and same location <br/>
* A team cannot play 2 matches on the same day <br/>

####  Parallel processing is done in project to initialize initital population.


### How to run the program <br/>
* Run FixturesMain.java to run the algorithm  
* Build the application with the test cases provided in FixturesTest.java
