# My Personal Project

## Introduction

### What will the application do
This application is a rogue-like game that allows you to control the character (a box) to fight against evil monsters. You can find many weapons along the journey and you can also learn some skills when you defeat strong monsters. Furthermore, you have a small room as your home where you can rest and decorate.

### Who will use it
Everyone who wants to have an enjoyable time.

### Why I interest in this project
Games are the ninth art. In reality, we are constrained by various principles. For example, as humans, we cannot fly without the help of equipment, cannot use magic in our daily life , and everything we create is equally bound by the laws of physics. However, in games, we can experience journeys that are impossible in the current real world — we can fight on *World War II* battlefields, feeling the brutality of war and the preciousness of life. We can rest in the tower of *Gryffindor*, witnessing one mesmerizing magic after another. We can leap from the towers of *Notre Dame in Paris*, experiencing the historical atmosphere of the French Revolution. We can also retrace the path that *Wukong* took to retrieve the Buddhist scriptures, appreciating the unique charm of Chinese culture.

In addition to giving us a tangible glimpse of things that exist in our imagination, games are also driving the *development of the computer field*. Nowadays, more and more games use cutting-edge technologies like motion capture and AI, pushing programmers to come up with more efficient and concise algorithms and graph theories, designing more diverse and enjoyable gameplay. Computers and games complement each other.

## User Stories
- As a user, I want to be able to add a weapon to my bag with its properties, like attack number, durability.
- As a user, I want to be able to view a list of weapons in my bag.
- As a user, I want to be able to sort my weapons on my bag based on the attack number.
- As a user, I want to be able to use my weapon to against the monster. 
- As a user, I want to be able to drop off a weapon from my bag.
- As a user, I want to be able to fix the durability of the weapon at home.
- As a user, I want to be able to build a weapon at home.
- As a user, I want to be able to have higher attribute number when my Ex is enough for me to update.
- As a user, when I select to quit the game, I want to be reminded to save my character state and the weapons on my bag and home to file, also I have the option to do it or not.
- As a user, when I start the application, I want to be given the option to load my character state and weapons from the file.

## Instruction for End User
- You can add weapon to bag by click *Build Weapon* button and *Yes* after.
- You can generate the first required action related to the user story "I want to be able to drop off a weapon" by click *Store the Weapon* button and choose weapons you want to drop out.
- You can generate the second required action related to the user story "I want to be able to sort my weapons on my bag" by click *Reorder Weapon (Attacking)* button.
- You can locate my visual component by finding the images on every page.
- You can save the state of my application by clicking the *Save the Game* button or choose *Yes* on the pumped window when you try to close the page.
- You can reload the state of my application by clicking the *Load the Game* button.
- You can view weapons in the bag by clicking the *View my weapon* button.

## Phase 4: Task 2
Logged Events:

Add Morning Star Wand to the bag.

Add Death Knight to the bag.

Add Death Knight to the bag.

Add Ru YI JIN GU BANG to the bag.

Add Morning Star Wand to the bag.

Add Morning Star Wand to the bag.

Store Death Knight to Box from Bag.

Store Morning Star Wand to Box from Bag.

Store Morning Star Wand to Box from Bag.

Store Death Knight to Box from Bag.

Store Morning Star Wand to Box from Bag.

Add Morning Star Wand to the bag.

Add Death Knight to the bag.

Add Morning Star Wand to the bag.

## Phase 4: Task 3
If I had more time, I would design several manager classes for the *Weapon* class to handle some of its functions. For instance, I would create a class named *WeaponGUIManager*, and the GUI classes that need to interact with the *Weapon* class would instead associate with this manager class, rather than building direct associations with the *Weapon* class.

The reason for this refactoring is that there are currently too many classes associated with the *Weapon* class, which makes it challenging to maintain. For example, if we need to modify something in the *Weapon* class, all related classes, including both model and GUI classes, would need to be updated, as they all rely on the *Weapon* class. By introducing several manager classes, we can reduce the coupling between the *Weapon* class and other parts of the system, thereby minimizing the amount of work required for future maintenance.
