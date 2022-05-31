# COOKING FEVER

We hope you have fun playing our new version of Cooking Fever!

!(The GIF goes Here)

## Description 

In this game, the goal is to collect as many coins as possible from all the customers. Make sure to complete as many orders as you can without sending away more than three customers, otherwise the game is over. There is a 5 second timer on the coffee machine, and a 5 second timer on the oven for the cakes. Drag the coffee, toppings, and cakes to complete the orders and don't forget to have fun!!!

## How to Play

- Press "tutorial" to review instructions
- Press "start" to begin
- Use mouse to drag the items
- Start the coffee machine timer by clicking on the machine
- Start the oven timer by dragging an unbaked cake to an open oven slot
- Exit out to restart 

## CLASSES

### Background Class

The Background class constructor contains a parameter to input the image URL, which helps paint the background images that remain consistent, such as the cafe setting and cafe counter.  
<img width="546" alt="Screen Shot 2022-05-30 at 4 55 45 PM" src="https://user-images.githubusercontent.com/90798447/171069442-869c5ce4-9ff2-4484-8247-6188f34c2ec4.png"> <img width="520" alt="Screen Shot 2022-05-30 at 4 59 01 PM" src="https://user-images.githubusercontent.com/90798447/171069459-25f09ce2-a5a3-40bf-8de3-a8aa899840b9.png">

### Customer Class

The Customer class contains the constructor for the customer object, with the parameter, custName, that allows the picture of the customer to be changed. There are four different custromer images, Daphne, Kyle, Francis, and Linda, and each object operates on a timer schedule. This timer determines when the customer enters, waits, and leaves and when the customer changes to another person's image. Additionally, there are getters and setters that are accessed in the OrderTimer class. 

![L1](https://user-images.githubusercontent.com/78383220/167712746-b9da7b1b-4720-40da-822c-47677847cd3a.png)  ![K1](https://user-images.githubusercontent.com/78383220/167712784-b68b83d2-fcc0-4dc6-9e77-3c28a87683cc.png) ![F1](https://user-images.githubusercontent.com/78383220/167712805-404e3f91-85fd-4ad2-9fdb-4b7c1311ab73.png) ![D1](https://user-images.githubusercontent.com/78383220/167712818-5678712d-7ed7-495a-a39b-3ad048ef59bf.png)

### Object Class

The Object class has a constructor that is made up of three parameters. The first two are ints to get the position of an object, and the last parameter is a string that represents the type of object, such as "Strawberry" or "VanillaBatter". The various methods included in this class are utilized in Runner to determine functions such as when fruit is added to cake batter or when cake is done baking in the oven. 

![CoffeeFull](https://user-images.githubusercontent.com/78383220/167712855-5b4a7ac7-a391-487e-85cf-ce8e58eed4f8.png)  ![VanBlueBake](https://user-images.githubusercontent.com/78383220/167712951-ed2df547-d8a3-4cb5-b75b-e61bf91e27af.png) ![ChocStrawBake](https://user-images.githubusercontent.com/78383220/167712999-2ba00bb5-d3ca-49f8-a752-d88a1d0958ab.png)

### Position Class

The Position class used to randomize the assignment of locations and orders, such as the waiting position of the person, the ordering position of a person, and the random order of a person. Additional boolean arrays prevent the duplication of location assignments to customers to prevent two customers in any one position.  

<img width="1190" alt="Screen Shot 2022-05-30 at 5 06 33 PM" src="https://user-images.githubusercontent.com/90798447/171069849-09dd94bc-724e-4fb5-98b6-dfaba3ab7bb4.png">
<img width="546" alt="Screen Shot 2022-05-30 at 5 14 50 PM" src="https://user-images.githubusercontent.com/90798447/171070342-08e82d58-12d5-4e96-84fa-70fdbe10cc2f.png">

### OrderTimer Class

The OrderTimer class is in charge of the timer that is used when the customer waits for their order to be completed. This class paints aspects like the order and timer while the customer is still waiting there, and it sets certain variables of its customer to true when the timer is over, which signals the customer to leave and to stop painting these elements. The timer is then reset, ready to start again when the customer is back to their ordering position, and their order is also newly generated. 

<img width="560" alt="Screen Shot 2022-05-30 at 5 23 30 PM" src="https://user-images.githubusercontent.com/90798447/171070857-5ce05069-becc-4d68-ab0f-cd48db5fcb39.png">
<img width="560" alt="Screen Shot 2022-05-30 at 5 23 45 PM" src="https://user-images.githubusercontent.com/90798447/171070805-231cb33d-19f2-47d2-8370-c3610dc52822.png">

## METHODS

### Paint Method

The paint method is an important part of the game, as it allows all the objects to show up on the screen with the specified positions and any other parameters that the constructors entail.

![paintMethod](https://user-images.githubusercontent.com/78383220/170363903-113d1495-26fc-4893-b2d0-2fa614b52582.PNG)

### Change Picture Method

The change picture method is located in every class, and it allows the images of the objects to change when a certain action has been completed, usually when a timer runs out, such as on the coffee machine or oven. 

![changePictureMethod](https://user-images.githubusercontent.com/78383220/170356743-baf2526e-8fe3-4b62-b31f-6903e8f27346.PNG)

## Help

Reopen or relaunch eclipse or the game if you run into any issues!

## Authors 

- Daniela Fisher: danielafisher05@gmail.com
- Jenny Zhu:  jenniferrzhuu@gmail.com
- Connie Zhu: connieyzhu3@gmail.com

## Version History 

1. 0.1
   * Initial Release
