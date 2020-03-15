# Cities Game, a test task for SmartfoxPro Company

    The game "Cities" allows to play a ping-pong with words when you should remember and
    enter the city name which begins from the last letter of a city name proposed to you
    by computer.
    
#### Technical details:
    - Used Spring Boot and Maven for project setup;
    - Spring Data and H2 inmemoryDB for persistant data;
    
#### To start and use the application:
    - Make sure you have installed H2 database in order to allow proper H2 db creation;
    - Make sure your Spring Boot launches with these default ULR and port:
    http://localhost:8080
    - Now you may run the project. Do GET request http://localhost:8080/begin. From this point you may
    consider the program has started the game: you'll be propose the city name word.
    - Then you should enter the city name which begins with the last letter of proposed word
    and send it via Get request to http://localhost:8080/next?word=$word, where $word - is your
    city name.
    - The program check whether the last letter of a word it proposed mathces the first letter
    of a city name you entered and forbids to play with the words you've already used;